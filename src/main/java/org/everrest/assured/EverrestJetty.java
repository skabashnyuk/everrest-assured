package org.everrest.assured;

import com.jayway.restassured.RestAssured;

import org.everrest.core.Filter;
import org.everrest.core.ObjectFactory;
import org.everrest.core.resource.AbstractResourceDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.lang.reflect.Field;
import java.util.*;
import javax.ws.rs.Path;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/*
 * Copyright (C) 2012 eXo Platform SAS.
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

/**
 *
 */
public class EverrestJetty implements ITestListener, IInvokedMethodListener
{

   public final static String JETTY_PORT = "jetty-port";

   public final static String JETTY_SERVER = "jetty-server";

   private static final Logger LOG = LoggerFactory.getLogger(EverrestJetty.class);

   private JettyHttpServer httpServer;

   /**
    * @see org.testng.IInvokedMethodListener#afterInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void afterInvocation(IInvokedMethod method, ITestResult testResult)
   {
      if (httpServer != null && hasEverrestJettyListener(method.getTestMethod().getInstance().getClass()))
      {
         httpServer.resetFactories();
      }

   }

   /**
    * @see org.testng.IInvokedMethodListener#beforeInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
   {
      if (httpServer != null && hasEverrestJettyListener(method.getTestMethod().getInstance().getClass()))
      {
         List<ObjectFactory<AbstractResourceDescriptor>> factories = new ArrayList(getResourcesFactories(method.getTestMethod()));
         httpServer.resetFactories();
         httpServer.setFactories(factories);
      }
   }

   public void onFinish(ITestContext context)
   {
      JettyHttpServer httpServer = (JettyHttpServer)context.getAttribute(JETTY_SERVER);
      if (httpServer != null)
      {
         try
         {
            httpServer.stop();
            httpServer = null;
         }
         catch (Exception e)
         {
            LOG.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage(), e);
         }

      }
   }

   public void onStart(ITestContext context)
   {
      ITestNGMethod[] allTestMethods = context.getAllTestMethods();
      if (allTestMethods == null)
      {
         return;
      }
      if (httpServer == null && hasEverrestJettyListenerTestHierarchy(allTestMethods))
      {
         httpServer = new JettyHttpServer();

         context.setAttribute(JETTY_PORT, httpServer.getPort());
         context.setAttribute(JETTY_SERVER, httpServer);

         try
         {
            httpServer.start();

            httpServer.setExceptionMappers(new ArrayList<ExceptionMapper>(getExceptionMappers(allTestMethods)));
            RestAssured.port = httpServer.getPort();
            RestAssured.basePath = JettyHttpServer.UNSECURE_REST;
         }
         catch (Exception e)
         {
            LOG.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage(), e);
         }
      }
   }

   /**
    * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
    */
   @Override
   public void onTestFailedButWithinSuccessPercentage(ITestResult result)
   {
   }

   /**
    * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
    */
   @Override
   public void onTestFailure(ITestResult result)
   {
   }

   /**
    * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
    */
   @Override
   public void onTestSkipped(ITestResult result)
   {
   }

   /**
    * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
    */
   @Override
   public void onTestStart(ITestResult result)
   {
   }

   /**
    * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
    */
   @Override
   public void onTestSuccess(ITestResult result)
   {
   }

   private Set<ObjectFactory<AbstractResourceDescriptor>> getResourcesFactories(ITestNGMethod... testMethods)
   {
       Set<ObjectFactory<AbstractResourceDescriptor>> factories = new
               HashSet<ObjectFactory<AbstractResourceDescriptor>>();
      for (ITestNGMethod testMethod : testMethods)
      {
         Object instance = testMethod.getInstance();
         if (hasEverrestJettyListenerTestHierarchy(instance.getClass()))
         {
            Field[] fields = instance.getClass().getDeclaredFields();
            for (Field field : fields)
            {
               if (isRestResource(field.getType()))
               {
                  factories.add(new TestResourceFactory(field.getType(), instance, field));
               }
            }

         }
      }
      return factories;
   }

   private Set<ExceptionMapper> getExceptionMappers(ITestNGMethod... testMethods)
   {
       Map<String,ExceptionMapper> factories = new HashMap<String, ExceptionMapper>();
      for (ITestNGMethod testMethod : testMethods)
      {
         Object instance = testMethod.getInstance();
         if (hasEverrestJettyListenerTestHierarchy(instance.getClass()))
         {
            Field[] fields = instance.getClass().getDeclaredFields();
            for (Field field : fields)
            {
               if (field.getType().isAssignableFrom(ExceptionMapper.class))
               {
                  field.setAccessible(true);
                  try
                  {
                      ExceptionMapper exceptionMapper = (ExceptionMapper)field.get(instance);
                      factories.put(exceptionMapper.getClass().getCanonicalName(), exceptionMapper);
                  }
                  catch (IllegalAccessException e)
                  {
                     LOG.error(e.getLocalizedMessage(), e);
                  }
                  ;
               }
            }

         }
      }
      return new HashSet<ExceptionMapper>(factories.values());
   }

   private boolean hasEverrestJettyListener(Class<?> clazz)
   {
      Listeners listeners = clazz.getAnnotation(Listeners.class);
      if (listeners == null)
      {
         return false;
      }

      for (Class<? extends ITestNGListener> listenerClass : listeners.value())
      {
         if (EverrestJetty.class.isAssignableFrom(listenerClass))
         {
            return true;
         }
      }
      return false;
   }

   private boolean hasEverrestJettyListenerTestHierarchy(Class<?> testClass)
   {
      for (Class<?> clazz = testClass; clazz != Object.class; clazz = clazz.getSuperclass())
      {
         if (hasEverrestJettyListener(clazz))
         {
            return true;
         }
      }
      return false;
   }

   private boolean hasEverrestJettyListenerTestHierarchy(ITestNGMethod... testMethods)
   {
      for (ITestNGMethod testMethod : testMethods)
      {
         Object instance = testMethod.getInstance();
         if (hasEverrestJettyListenerTestHierarchy(instance.getClass()))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isRestResource(Class<? extends Object> resourceClass)
   {
      return resourceClass.isAnnotationPresent(Path.class) || resourceClass.isAnnotationPresent(Provider.class) ||
         resourceClass.isAnnotationPresent(Filter.class);
   }

}
