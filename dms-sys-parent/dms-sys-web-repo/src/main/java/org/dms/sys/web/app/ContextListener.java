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

package org.dms.sys.web.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * ServletContextListener implementation that initialises the application.
 * 
 * NOTE: This class must appear after the Spring context loader listener
 * 
 * @author gavinc
 */
public class ContextListener implements ServletContextListener, HttpSessionListener
{
   
   /** The logger. */
   private static Log logger = LogFactory.getLog(ContextListener.class);

   /** The servlet context. */
   private ServletContext servletContext;
   
   /** The enterprise listener. */
   private ServletContextListener enterpriseListener;
   
   /** The enterprise listener class. */
   private String enterpriseListenerClass = "org.alfresco.enterprise.repo.EnterpriseContextListener";

   /**
    * Context initialized.
    *
    * @param event the event
    * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
    */
   public void contextInitialized(ServletContextEvent event)
   {
       // make sure that the spaces store in the repository exists
      this.servletContext = event.getServletContext();
      WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
      
      // If no context has been initialised, exit silently so config changes can be made
      if (ctx == null)
      {
          return;
      }

      synchronized(this)
      {
         findEnterpriseListener();
         if (enterpriseListener != null)
         {
             // Perform any extra context initialisation required for enterprise.
             enterpriseListener.contextInitialized(event);
         }
      }
   }

   /**
    * Find enterprise listener.
    */
   protected void findEnterpriseListener()
   {
       try
       {
          Class<?> c = Class.forName(enterpriseListenerClass);
          enterpriseListener = (ServletContextListener) c.newInstance();
       }
       catch (ClassNotFoundException e)
       {
          // It's OK not to have the enterprise context destroyer available.
       }
       catch (InstantiationException e)
       {
          logger.error("Failed to instantiate enterprise ServletContextListener.", e);
       }
       catch (IllegalAccessException e)
       {
          logger.error("Failed to instantiate enterprise ServletContextListener.", e);
       }
   }
   
   /**
    * {@inheritDoc}
    */
   public void contextDestroyed(ServletContextEvent event)
   {
       synchronized(this)
       {
          if (enterpriseListener != null)
          {
              // Perform any extra destruction required for enterprise.
              enterpriseListener.contextDestroyed(event);
          }
       }
   }

   /**
    * Session created listener.
    *
    * @param event the event
    */
   public void sessionCreated(HttpSessionEvent event)
   {
      if (logger.isDebugEnabled())
         logger.debug("HTTP session created: " + event.getSession().getId());
   }

   /**
    * Session destroyed listener.
    *
    * @param event the event
    */
   public void sessionDestroyed(HttpSessionEvent event)
   {
      if (logger.isDebugEnabled())
         logger.debug("HTTP session destroyed: " + event.getSession().getId());
   }

   /**
    * Inject a different class name (from the default) for the enterprise ServletContextListener.
    * <p>
    * Useful for testing.
    *  
    * @param listenerClass Class name to use.
    */
   protected void setEnterpriseListenerClass(String listenerClass)
   {
      this.enterpriseListenerClass = listenerClass;
   }
}
