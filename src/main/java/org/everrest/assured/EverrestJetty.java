package org.everrest.assured;

import org.everrest.core.Filter;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class EverrestJetty implements ITestListener
{
   public final static String JETTY_PORT = "jetty-port";

   private final static String JETTY_SERVER = "jetty-server";

   /**
    * Init fields with annotated fields for instances that are annotated with
    * the TestNG listener {@link MockitoTestNGListener}.
    * 
    * @param context
    *           TestContext
    */
   public void onStart(ITestContext context)
   {
      if (context.getAllTestMethods() == null)
      {
         return;
      }

      List testServices = new ArrayList();

      for (Object instance : allTestInstancesWithEverrestJetty(context.getAllTestMethods()))
      {
         Field[] fields = instance.getClass().getDeclaredFields();
         for (Field field : fields)
         {
            field.setAccessible(true);
            try
            {
               Object fieldInstance = field.get(instance);
               if (fieldInstance != null)
               {
                  Class<? extends Object> fieldClass = fieldInstance.getClass();
                  if (fieldClass.isAnnotationPresent(Path.class) || fieldClass.isAnnotationPresent(Provider.class)
                     || fieldClass.isAnnotationPresent(Filter.class))
                  {
                     testServices.add(fieldInstance);
                  }
               }
            }
            catch (IllegalArgumentException e)
            {
               throw new RuntimeException(e.getLocalizedMessage(), e);
            }
            catch (IllegalAccessException e)
            {
               throw new RuntimeException(e.getLocalizedMessage(), e);
            }

         }
      }

      JettyHttpServer httpServer = new JettyHttpServer(testServices.toArray());
      context.setAttribute(JETTY_PORT, httpServer.getPort());
      context.setAttribute(JETTY_SERVER, httpServer);
      httpServer.start();

   }

   private Set<Object> allTestInstancesWithEverrestJetty(ITestNGMethod[] testMethods)
   {
      Set<Object> testInstancesWithMockitoListener = new HashSet<Object>();
      for (Object testInstance : allTestInstances(testMethods))
      {
         if (hasEverrestJettyListener(testInstance))
         {
            testInstancesWithMockitoListener.add(testInstance);
         }
      }
      return testInstancesWithMockitoListener;
   }

   private boolean hasEverrestJettyListener(Object testInstance)
   {
      Listeners listeners = testInstance.getClass().getAnnotation(Listeners.class);
      if (listeners == null)
      {
         return false;
      }

      for (Class<? extends ITestNGListener> listenerClass : listeners.value())
      {
         if (EverrestJetty.class == listenerClass)
         {
            return true;
         }
      }
      return false;
   }

   private Set<Object> allTestInstances(ITestNGMethod[] testMethods)
   {
      Set<Object> allTestInstances = new HashSet<Object>();
      for (ITestNGMethod testMethod : testMethods)
      {
         allTestInstances.add(testMethod.getInstance());
      }
      return allTestInstances;
   }

   public void onFinish(ITestContext context)
   {
      JettyHttpServer httpServer = (JettyHttpServer)context.getAttribute(JETTY_SERVER);
      httpServer.stop();
   }

   /**
    * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
    */
   @Override
   public void onTestStart(ITestResult result)
   {
      // TODO Auto-generated method stub

   }

   /**
    * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
    */
   @Override
   public void onTestSuccess(ITestResult result)
   {
      // TODO Auto-generated method stub

   }

   /**
    * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
    */
   @Override
   public void onTestFailure(ITestResult result)
   {
      // TODO Auto-generated method stub

   }

   /**
    * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
    */
   @Override
   public void onTestSkipped(ITestResult result)
   {
      // TODO Auto-generated method stub

   }

   /**
    * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
    */
   @Override
   public void onTestFailedButWithinSuccessPercentage(ITestResult result)
   {
      // TODO Auto-generated method stub

   }
}
