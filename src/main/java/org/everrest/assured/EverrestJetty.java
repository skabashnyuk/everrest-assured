package org.everrest.assured;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
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

   private static final Logger LOG = LoggerFactory.getLogger(EverrestJetty.class);

   public final static String JETTY_PORT = "jetty-port";

   public final static String JETTY_SERVER = "jetty-server";

   private JettyHttpServer httpServer;

   public void onStart(ITestContext context)
   {
      ITestNGMethod[] allTestMethods = context.getAllTestMethods();
      if (allTestMethods == null)
      {
         return;
      }
      if (httpServer == null)
      {
         List<ObjectFactory<AbstractResourceDescriptor>> factories = getResourcesFactories(allTestMethods);
         List<GroovyServiceSource> groovySercices = getGroovyServices(allTestMethods);

         if (factories.size() > 0 || groovySercices.size() > 0 || hasDataProviderAnnotation(allTestMethods))
         {
            httpServer = new JettyHttpServer();
            context.setAttribute(JETTY_PORT, httpServer.getPort());
            context.setAttribute(JETTY_SERVER, httpServer);
            httpServer.start();
         }
      }
   }

   public void onFinish(ITestContext context)
   {
      JettyHttpServer httpServer = (JettyHttpServer)context.getAttribute(JETTY_SERVER);
      if (httpServer != null)
      {
         httpServer.stop();
         httpServer = null;
      }
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
    * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
    */
   @Override
   public void onTestFailedButWithinSuccessPercentage(ITestResult result)
   {
   }

   private List<ObjectFactory<AbstractResourceDescriptor>> getResourcesFactories(ITestNGMethod... testMethods)
   {
      List<ObjectFactory<AbstractResourceDescriptor>> factories =
         new ArrayList<ObjectFactory<AbstractResourceDescriptor>>();
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

   private boolean hasDataProviderAnnotation(ITestNGMethod... testMethods)
   {
      List<ObjectFactory<AbstractResourceDescriptor>> factories =
         new ArrayList<ObjectFactory<AbstractResourceDescriptor>>();
      for (ITestNGMethod testMethod : testMethods)
      {
         Object instance = testMethod.getInstance();
         if (hasEverrestJettyListenerTestHierarchy(instance.getClass()))
         {
            Method[] methods = instance.getClass().getMethods();
            for (Method method : methods)
            {
               if (method.isAnnotationPresent(DataProvider.class))
               {
                  return true;
               }
            }

         }
      }
      return false;
   }

   private List<GroovyServiceSource> getGroovyServices(ITestNGMethod... testMethods)
   {
      List<GroovyServiceSource> factories =
         new ArrayList<GroovyServiceSource>();
      for (ITestNGMethod testMethod : testMethods)
      {
         Object instance = testMethod.getInstance();
         if (hasEverrestJettyListenerTestHierarchy(instance.getClass()))
         {
            Field[] fields = instance.getClass().getDeclaredFields();
            for (Field field : fields)
            {
               if (GroovyServiceSource.class.isAssignableFrom(field.getType()))
               {
                  try
                  {
                     field.setAccessible(true);
                     factories.add((GroovyServiceSource)field.get(instance));
                  }
                  catch (SecurityException e)
                  {
                     LOG.error(e.getLocalizedMessage(), e);
                     throw new RuntimeException(e.getLocalizedMessage(), e);
                  }
                  catch (IllegalArgumentException e)
                  {
                     LOG.error(e.getLocalizedMessage(), e);
                     throw new RuntimeException(e.getLocalizedMessage(), e);
                  }
                  catch (IllegalAccessException e)
                  {
                     LOG.error(e.getLocalizedMessage(), e);
                     throw new RuntimeException(e.getLocalizedMessage(), e);
                  }

               }
            }

         }
      }
      return factories;
   }

   private boolean isRestResource(Class<? extends Object> resourceClass)
   {
      return resourceClass.isAnnotationPresent(Path.class) || resourceClass.isAnnotationPresent(Provider.class)
         || resourceClass.isAnnotationPresent(Filter.class);
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

   /**
    * @see org.testng.IInvokedMethodListener#beforeInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
   {
      if (httpServer != null && hasEverrestJettyListener(method.getTestMethod().getInstance().getClass()))
      {
         List<ObjectFactory<AbstractResourceDescriptor>> factories = getResourcesFactories(method.getTestMethod());
         List<GroovyServiceSource> groovySercices = getGroovyServices(method.getTestMethod());
         httpServer.resetFactories();
         httpServer.setFactories(factories);
         httpServer.setGroovyServices(groovySercices);
      }
   }

   /**
    * @see org.testng.IInvokedMethodListener#afterInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void afterInvocation(IInvokedMethod method, ITestResult testResult)
   {
      if (httpServer != null && hasEverrestJettyListener(method.getTestMethod().getInstance().getClass()))
      {
         List<ObjectFactory<AbstractResourceDescriptor>> factories = getResourcesFactories(method.getTestMethod());
         httpServer.resetFactories();
      }

   }

}
