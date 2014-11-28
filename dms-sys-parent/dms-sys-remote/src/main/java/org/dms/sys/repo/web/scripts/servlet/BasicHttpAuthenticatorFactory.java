/*
 * Copyright (C) 2005-2010 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dms.sys.repo.web.scripts.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dms.sys.repo.security.authentication.AuthenticationException;
import org.dms.sys.repo.security.authentication.Authorization;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.surf.util.Base64;
import org.springframework.extensions.webscripts.Authenticator;
import org.springframework.extensions.webscripts.Description.RequiredAuthentication;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.servlet.ServletAuthenticatorFactory;
import org.springframework.extensions.webscripts.servlet.WebScriptServletRequest;
import org.springframework.extensions.webscripts.servlet.WebScriptServletResponse;

/**
 * HTTP Basic Authentication.
 *
 * @author davidc
 */
public class BasicHttpAuthenticatorFactory implements ServletAuthenticatorFactory
{
    // Logger
    /** The logger. */
    private static Log logger = LogFactory.getLog(BasicHttpAuthenticator.class);

    /**
     * Creates the.
     *
     * @param req the req
     * @param res the res
     * @return the authenticator
     * @see org.alfresco.web.scripts.servlet.ServletAuthenticatorFactory#create(org.alfresco.web.scripts.servlet.WebScriptServletRequest, org.alfresco.web.scripts.servlet.WebScriptServletResponse)
     */
    public Authenticator create(WebScriptServletRequest req, WebScriptServletResponse res)
    {
        return new BasicHttpAuthenticator(req, res);
    }
    
    
    /**
     * HTTP Basic Authentication.
     *
     * @author davidc
     */
    public class BasicHttpAuthenticator implements Authenticator
    {
        // dependencies
        /** The servlet req. */
        private WebScriptServletRequest servletReq;
        
        /** The servlet res. */
        private WebScriptServletResponse servletRes;
        
        /** The authorization. */
        private String authorization;
        
        /** The ticket. */
        private String ticket;
        
        /**
         * Construct.
         *
         * @param req the req
         * @param res the res
         */
        public BasicHttpAuthenticator(WebScriptServletRequest req, WebScriptServletResponse res)
        {
            this.servletReq = req;
            this.servletRes = res;
            
            HttpServletRequest httpReq = servletReq.getHttpServletRequest();
            
            this.authorization = httpReq.getHeader("Authorization");
            this.ticket = httpReq.getParameter("alf_ticket");
        }
    
        /**
         * Authenticate.
         *
         * @param required the required
         * @param isGuest the is guest
         * @return true, if successful
         * @see org.alfresco.web.scripts.Authenticator#authenticate(org.alfresco.web.scripts.Description.RequiredAuthentication, boolean)
         */
        public boolean authenticate(RequiredAuthentication required, boolean isGuest)
        {
            boolean authorized = false;
    
            //
            // validate credentials
            // 
            
            HttpServletResponse res = servletRes.getHttpServletResponse();
            
            if (logger.isDebugEnabled())
            {
                logger.debug("HTTP Authorization provided: " + (authorization != null && authorization.length() > 0));
                logger.debug("URL ticket provided: " + (ticket != null && ticket.length() > 0));
            }
            
            // If they requested explicit guest authentication,
            //  Authenticate as guest (if allowed)
            if (isGuest && RequiredAuthentication.guest == required)
            {
                if (logger.isDebugEnabled())
                    logger.debug("Authenticating as Guest");
                    authorized = true;
            }
            
            // authenticate as specified by explicit ticket on url
            else if (ticket != null && ticket.length() > 0)
            {
                    if (logger.isDebugEnabled())
                        logger.debug("Authenticating (URL argument) ticket " + ticket);
        
                    // assume a ticket has been passed
                    authorized = true;
            }
            
            // authenticate as specified by HTTP Basic Authentication
            else if (authorization != null && authorization.length() > 0)
            {
                try
                {
                    String[] authorizationParts = authorization.split(" ");
                    if (!authorizationParts[0].equalsIgnoreCase("basic"))
                    {
                        throw new WebScriptException("Authorization '" + authorizationParts[0] + "' not supported.");
                    }
                    
                    String decodedAuthorisation = new String(Base64.decode(authorizationParts[1]));
                    Authorization auth = new Authorization(decodedAuthorisation);
                    if (auth.isTicket())
                    {
                        if (logger.isDebugEnabled())
                            logger.debug("Authenticating (BASIC HTTP) ticket " + auth.getTicket());
    
                        // assume a ticket has been passed
                        authorized = true;
                    }
                    else
                    {
                        if (logger.isDebugEnabled())
                            logger.debug("Authenticating (BASIC HTTP) user " + auth.getUserName());
    
                        // No longer need a special call to authenticate as guest
                        // Leave guest name resolution up to the services
                        authorized = true;
                    }
                }
                catch(AuthenticationException e)
                {
                    // failed authentication
                }
            }
    
            //
            // request credentials if not authorized
            //
            
            if (!authorized)
            {
                if (logger.isDebugEnabled())
                    logger.debug("Requesting authorization credentials");
                
                res.setStatus(401);
                res.setHeader("WWW-Authenticate", "Basic realm=\"Alfresco\"");
            }
            return authorized;
        }
        
        /**
         * Empty credentials.
         *
         * @return true, if successful
         * @see org.alfresco.web.scripts.Authenticator#emptyCredentials()
         */
        public boolean emptyCredentials()
        {
            return ((ticket == null || ticket.length() == 0) && (authorization == null || authorization.length() == 0));
        }
    }

}