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
package org.dms.sys.repo.security.authentication;

import org.dms.sys.service.cmr.security.PermissionService;
import org.dms.sys.util.ParameterCheck;

/**
 * Helper to process username / password pairs passed to the remote tier
 * 
 * Identifies whether username / password is a ticket.
 * 
 * Is ticket, if one of the following is true:
 * 
 * a) Username == "ROLE_TICKET" (in any case) b) Username is not specified (i.e.
 * null) c) Username is zero length
 */
public class Authorization
{
    
    /** The ticket userid. */
    public static String TICKET_USERID = PermissionService.ROLE_PREFIX + "TICKET";

    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The ticket. */
    private String ticket;

    /**
     * Construct.
     *
     * @param authorization the authorization
     */
    public Authorization(String authorization)
    { 
        ParameterCheck.mandatoryString("authorization", authorization);
        if (authorization.length() == 0)
        {
            throw new IllegalArgumentException("authorization does not consist of username and password");
        }

        int idx = authorization.indexOf(':');

        if (idx == -1)
        {
            setUser(null, authorization);
        }
        else
        {
            setUser(authorization.substring(0, idx), authorization.substring(idx + 1));
        }
    }

    /**
     * Construct.
     *
     * @param username the username
     * @param password the password
     */
    public Authorization(String username, String password)
    {
        setUser(username, password);
    }

    /**
     * Sets the user.
     *
     * @param username the username
     * @param password the password
     */
    private void setUser(String username, String password)
    {
        this.username = username;
        this.password = password;
        if (username == null || username.length() == 0 || username.equalsIgnoreCase(TICKET_USERID))
        {
            this.ticket = password;
        }
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName()
    {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Gets the password char array.
     *
     * @return the password char array
     */
    public char[] getPasswordCharArray()
    {
        return password == null ? null : password.toCharArray();
    }

    /**
     * Checks if is ticket.
     *
     * @return true, if is ticket
     */
    public boolean isTicket()
    {
        return ticket != null;
    }

    /**
     * Gets the ticket.
     *
     * @return the ticket
     */
    public String getTicket()
    {
        return ticket;
    }

}
