/*
 * Copyright (C) 2011 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.everrest.assured;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Password;
import org.everrest.core.DependencySupplier;
import org.everrest.core.ResourceBinder;
import org.everrest.core.impl.ApplicationProviderBinder;
import org.everrest.core.impl.EnvironmentContext;
import org.everrest.core.impl.EverrestConfiguration;
import org.everrest.core.impl.RequestDispatcher;
import org.everrest.core.impl.RequestHandlerImpl;
import org.everrest.core.servlet.EverrestInitializedListener;
import org.everrest.core.servlet.EverrestServlet;
import org.everrest.core.tools.DummySecurityContext;
import org.everrest.core.tools.ResourceLauncher;
import org.everrest.test.mock.MockPrincipal;

import java.security.Principal;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.SecurityContext;

/**
 * 
 */
public class JettyHttpServer
{

   protected ServletContextHandler context;

   protected final int port;

   protected final Server server;

   public final static String ADMIN_USER_NAME = "cldadmin";

   public final static String ADMIN_USER_PASSWORD = "tomcat";

   public final static String MANAGER_USER_NAME = "cldmanager";

   public final static String MANAGER_USER_PASSWORD = "manager";

   public final static String UNAUTHORIZED_USER = "user";

   private List<Object> restServices;

   /**
    * 
    */
   public JettyHttpServer()
   {
      this(AvailablePortFinder.getNextAvailable(3000));
   }

   public JettyHttpServer(int port)
   {
      this.port = port;
      this.server = new Server(port);
      this.context = null;
   }

   public int getPort()
   {
      return port;
   }

   public void start()
   {
      RequestLogHandler handler = new RequestLogHandler();

      if (context == null)
      {
         context = new ServletContextHandler(handler, getContextPath(), ServletContextHandler.SESSIONS);
      }

      context.setEventListeners(getEventListeners());
      ServletHolder servletHolder = new ServletHolder(new EverrestServlet());

      initParams(context.getInitParams());

      context.addServlet(servletHolder, "/rest/*");

      context.addServlet(servletHolder, "/rest/private/*");

      setContextAttributes(context);

      SecurityHandler securityHandler = getSecurityHandler();

      if (securityHandler != null)
      {
         context.setSecurityHandler(securityHandler);
      }

      server.setHandler(handler);
      try
      {
         server.start();

      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new RuntimeException(e.getLocalizedMessage(), e);
      }

   }

   public void setServices(List<Object> restServices)
   {
      restServices = restServices;
      ResourceBinder binder = (ResourceBinder)context.getServletContext().getAttribute(ResourceBinder.class.getName());
      for (Object resource : restServices)
      {
         binder.addResource(resource, null);
      }
   }

   public void resetServices()
   {
      ResourceBinder binder = (ResourceBinder)context.getServletContext().getAttribute(ResourceBinder.class.getName());
      if (restServices != null)
      {
         for (Object service : restServices)
         {
            binder.removeResource(service.getClass());
         }
      }
   }

   public ResourceLauncher getResourceLauncher()
   {
      ResourceBinder binder = (ResourceBinder)context.getServletContext().getAttribute(ResourceBinder.class.getName());
      DependencySupplier suppier =
         (DependencySupplier)context.getServletContext().getAttribute(DependencySupplier.class.getName());
      ApplicationProviderBinder providerBinder =
         (ApplicationProviderBinder)context.getServletContext().getAttribute(ApplicationProviderBinder.class.getName());

      return new ResourceLauncher(new RequestHandlerImpl(new RequestDispatcher(binder), providerBinder, suppier,
         new EverrestConfiguration()));
   }

   public void stop()
   {
      context = null;
      try
      {
         server.stop();
      }
      catch (Exception e)
      {
         throw new RuntimeException(e.getLocalizedMessage(), e);
      }
   }

   public boolean isStopped()
   {
      return server.isStopped();
   }

   public boolean isRunning()
   {
      return server.isRunning();
   }

   public boolean isStarted()
   {
      return server.isStarted();
   }

   protected String getContextPath()
   {
      return "/";
   }

   protected void setContextAttributes(ServletContextHandler context)
   {
   }

   protected EventListener[] getEventListeners()
   {
      return new EventListener[]{new EverrestInitializedListener()};
   }

   protected SecurityHandler getSecurityHandler()
   {
      //set up security
      Constraint constraint = new Constraint();
      constraint.setName(Constraint.__BASIC_AUTH);
      constraint.setRoles(new String[]{"cloud-admin"});
      constraint.setAuthenticate(true);

      ConstraintMapping constraintMapping = new ConstraintMapping();
      constraintMapping.setConstraint(constraint);
      constraintMapping.setPathSpec("/" + getSecureRestPath() + "/*");

      ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
      securityHandler.addConstraintMapping(constraintMapping);

      HashLoginService loginService = new HashLoginService();
      loginService.putUser(ADMIN_USER_NAME, new Password(ADMIN_USER_PASSWORD), new String[]{"cloud-admin"});

      loginService.putUser(MANAGER_USER_NAME, new Password(MANAGER_USER_PASSWORD), new String[]{"cloud-admin"});

      securityHandler.setLoginService(loginService);
      securityHandler.setAuthenticator(new BasicAuthenticator());
      return securityHandler;
   }

   protected String getSecureRestPath()
   {
      return "rest";
   }

   public String getRestUri(boolean secure)
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append("http://localhost:");
      buffer.append(port);
      buffer.append(getContextPath());
      if (secure)
      {
         buffer.append(getSecureRestPath());
      }
      else
      {
         buffer.append("rest");
      }

      return buffer.toString();
   }

   public String getRestUri()
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append("http://localhost:");
      buffer.append(port);
      buffer.append(getContextPath());
      buffer.append("rest");
      return buffer.toString();
   }

   protected void initParams(Map<String, String> params)
   {
   }

   public EnvironmentContext getUnsecureEnvironment()
   {
      Principal userPrincipal = new MockPrincipal("user");
      Set<String> userRoles = new HashSet<String>();
      userRoles.add("users");

      SecurityContext userSctx = new DummySecurityContext(userPrincipal, userRoles);
      EnvironmentContext unsecureEnvironment = new EnvironmentContext();
      unsecureEnvironment.put(SecurityContext.class, userSctx);
      return unsecureEnvironment;
   }

   public EnvironmentContext getSecureEnvironment()
   {
      Principal adminPrincipal = new MockPrincipal("cldadmin");
      Set<String> adminRoles = new HashSet<String>();
      adminRoles.add("users");
      adminRoles.add("cloud-admin");

      SecurityContext adminSctx = new DummySecurityContext(adminPrincipal, adminRoles);
      EnvironmentContext secureEnvironment = new EnvironmentContext();
      secureEnvironment.put(SecurityContext.class, adminSctx);
      return secureEnvironment;
   }

}
