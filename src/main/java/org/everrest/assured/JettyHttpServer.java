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
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Password;
import org.everrest.assured.util.AvailablePortFinder;
import org.everrest.assured.util.IoUtil;
import org.everrest.core.DependencySupplier;
import org.everrest.core.ObjectFactory;
import org.everrest.core.ResourceBinder;
import org.everrest.core.impl.ApplicationProviderBinder;
import org.everrest.core.impl.EverrestConfiguration;
import org.everrest.core.impl.ProviderBinder;
import org.everrest.core.impl.RequestDispatcher;
import org.everrest.core.impl.RequestHandlerImpl;
import org.everrest.core.impl.ResourceBinderImpl;
import org.everrest.core.resource.AbstractResourceDescriptor;
import org.everrest.core.servlet.EverrestInitializedListener;
import org.everrest.core.servlet.EverrestServlet;
import org.everrest.core.tools.ResourceLauncher;
import org.everrest.groovy.BaseResourceId;
import org.everrest.groovy.GroovyResourcePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EventListener;
import java.util.List;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 
 */
public class JettyHttpServer
{

   private static final Logger LOG = LoggerFactory.getLogger(JettyHttpServer.class);

   public static final String UNSECURE_REST = "/rest";

   public static final String UNSECURE_PATH_SPEC = UNSECURE_REST + "/*";

   public static final String SECURE_PATH = "/private";

   public static final String SECURE_REST = UNSECURE_REST + SECURE_PATH;

   public static final String SECURE_PATH_SPEC = SECURE_REST + "/*";

   protected ServletContextHandler context;

   protected final int port;

   protected final Server server;

   public final static String ADMIN_USER_NAME = "cldadmin";

   public final static String ADMIN_USER_PASSWORD = "tomcat";

   public final static String MANAGER_USER_NAME = "cldmanager";

   public final static String MANAGER_USER_PASSWORD = "manager";

   public final static String UNAUTHORIZED_USER = "user";

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

   public void start() throws Exception
   {
      RequestLogHandler handler = new RequestLogHandler();

      if (context == null)
      {
         context = new ServletContextHandler(handler, "/", ServletContextHandler.SESSIONS);
      }

      context.setEventListeners(new EventListener[]{new EverrestInitializedListener()});
      ServletHolder servletHolder = new ServletHolder(new EverrestServlet());

      context.addServlet(servletHolder, UNSECURE_PATH_SPEC);
      context.addServlet(servletHolder, SECURE_PATH_SPEC);

      //set up security
      Constraint constraint = new Constraint();
      constraint.setName(Constraint.__BASIC_AUTH);
      constraint.setRoles(new String[]{"cloud-admin"});
      constraint.setAuthenticate(true);

      ConstraintMapping constraintMapping = new ConstraintMapping();
      constraintMapping.setConstraint(constraint);
      constraintMapping.setPathSpec(SECURE_PATH_SPEC);

      ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
      securityHandler.addConstraintMapping(constraintMapping);

      HashLoginService loginService = new HashLoginService();
      loginService.putUser(ADMIN_USER_NAME, new Password(ADMIN_USER_PASSWORD), new String[]{"cloud-admin"});
      loginService.putUser(MANAGER_USER_NAME, new Password(MANAGER_USER_PASSWORD), new String[]{"cloud-admin"});

      securityHandler.setLoginService(loginService);
      securityHandler.setAuthenticator(new BasicAuthenticator());

      context.setSecurityHandler(securityHandler);

      server.setHandler(handler);

      server.start();
      ResourceBinder binder =
         (ResourceBinder)context.getServletContext().getAttribute(ResourceBinder.class.getName());
      DependencySupplier dependencies =
         (DependencySupplier)context.getServletContext().getAttribute(DependencySupplier.class.getName());
      GroovyResourcePublisher groovyPublisher = new GroovyResourcePublisher(binder, dependencies);
      context.getServletContext().setAttribute(GroovyResourcePublisher.class.getName(), groovyPublisher);

   }

   public void stop() throws Exception
   {
      context = null;
      server.stop();

   }

   public void setFactories(List<ObjectFactory<AbstractResourceDescriptor>> factories)
   {
      ResourceBinder binder = (ResourceBinder)context.getServletContext().getAttribute(ResourceBinder.class.getName());
      for (ObjectFactory<AbstractResourceDescriptor> resource : factories)
      {
         binder.addResource(resource);
      }
   }



   public void setExceptionMappers(List<ExceptionMapper> mappers)
   {
      ProviderBinder providers = (ProviderBinder)context.getServletContext().getAttribute(ApplicationProviderBinder.class.getName());
      for (ExceptionMapper mapper : mappers)
      {
         providers.addExceptionMapper(mapper);
      }
   }

   public void publishPerRequestGroovyScript(String resourcePath, String name)
   {
      GroovyResourcePublisher groovyPublisher =
         (GroovyResourcePublisher)context.getServletContext().getAttribute(GroovyResourcePublisher.class.getName());

      BaseResourceId publishedResourceId = new BaseResourceId(name);
      groovyPublisher.publishPerRequest(IoUtil.getResource(resourcePath), publishedResourceId, null, null, null);
   }

   /**
    * @return the context
    */
   public ServletContextHandler getContext()
   {
      return context;
   }

   public void resetFactories()
   {
      ResourceBinder binder = (ResourceBinder)context.getServletContext().getAttribute(ResourceBinder.class.getName());
      ((ResourceBinderImpl)binder).clear();
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

}
