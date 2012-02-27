package org.everrest.assured;

import org.everrest.core.Filter;
import org.mockito.testng.MockitoTestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.lang.reflect.Field;
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
public class EverrestJettyWithMockitoListener extends MockitoTestNGListener implements ITestListener
{

   private static final Logger LOG = LoggerFactory.getLogger(EverrestJettyWithMockitoListener.class);

   public final static String JETTY_PORT = "jetty-port";

   private final static String JETTY_SERVER = "jetty-server";

   private JettyHttpServer httpServer;

   public void onStart(ITestContext context)
   {
      if (context.getAllTestMethods() == null)
      {
         return;
      }

      httpServer = new JettyHttpServer();
      context.setAttribute(JETTY_PORT, httpServer.getPort());
      context.setAttribute(JETTY_SERVER, httpServer);
      httpServer.start();

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

   private List<Object> getRestServices(ITestNGMethod testMethod)
   {
      List<Object> result = new ArrayList<Object>();
      Object instance = testMethod.getInstance();
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
                  result.add(fieldInstance);
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
      return result;
   }

   /**
    * @see org.testng.IInvokedMethodListener#beforeInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
   {
      super.beforeInvocation(method, testResult);
      if (method.isTestMethod())
      {
         List<Object> restServices = getRestServices(method.getTestMethod());
         LOG.debug("deploing services {}", restServices.size());
         httpServer.setServices(restServices);
      }
   }

   /**
    * @see org.testng.IInvokedMethodListener#afterInvocation(org.testng.IInvokedMethod,
    *      org.testng.ITestResult)
    */
   @Override
   public void afterInvocation(IInvokedMethod method, ITestResult testResult)
   {
      if (method.isTestMethod())
      {
         LOG.debug("Reset rest services");
         httpServer.resetServices();
      }
      super.afterInvocation(method, testResult);
   }

}
