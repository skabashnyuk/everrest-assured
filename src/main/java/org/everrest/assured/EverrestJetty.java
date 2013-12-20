/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2013] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */

package org.everrest.assured;

import com.jayway.restassured.RestAssured;

import org.everrest.core.Filter;
import org.everrest.core.ObjectFactory;
import org.everrest.core.resource.AbstractResourceDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.annotations.Listeners;

import javax.ws.rs.Path;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Field;
import java.util.*;


public class EverrestJetty implements ITestListener, IInvokedMethodListener {

    public final static  String JETTY_PORT   = "jetty-port";
    public final static  String JETTY_SERVER = "jetty-server";
    private static final Logger LOG          = LoggerFactory.getLogger(EverrestJetty.class);
    private JettyHttpServer httpServer;

    /**
     * @see org.testng.IInvokedMethodListener#afterInvocation(org.testng.IInvokedMethod,
     *      org.testng.ITestResult)
     */
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (httpServer != null && hasEverrestJettyListener(method.getTestMethod().getInstance().getClass())) {
            httpServer.resetFactories();
        }

    }

    /**
     * @see org.testng.IInvokedMethodListener#beforeInvocation(org.testng.IInvokedMethod,
     *      org.testng.ITestResult)
     */
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (httpServer != null && hasEverrestJettyListener(method.getTestMethod().getInstance().getClass())) {
            List<ObjectFactory<AbstractResourceDescriptor>> factories = new ArrayList(getResourcesFactories(method.getTestMethod()));
            httpServer.resetFactories();
            httpServer.setFactories(factories);
        }
    }

    public void onFinish(ITestContext context) {
        JettyHttpServer httpServer = (JettyHttpServer)context.getAttribute(JETTY_SERVER);
        if (httpServer != null) {
            try {
                httpServer.stop();
                httpServer = null;
            } catch (Exception e) {
                LOG.error(e.getLocalizedMessage(), e);
                throw new RuntimeException(e.getLocalizedMessage(), e);
            }

        }
    }

    public void onStart(ITestContext context) {
        ITestNGMethod[] allTestMethods = context.getAllTestMethods();
        if (allTestMethods == null) {
            return;
        }
        if (httpServer == null && hasEverrestJettyListenerTestHierarchy(allTestMethods)) {
            httpServer = new JettyHttpServer();

            context.setAttribute(JETTY_PORT, httpServer.getPort());
            context.setAttribute(JETTY_SERVER, httpServer);

            try {
                httpServer.start();

                httpServer.setExceptionMappers(new ArrayList<>(getExceptionMappers(allTestMethods)));
                RestAssured.port = httpServer.getPort();
                RestAssured.basePath = JettyHttpServer.UNSECURE_REST;
            } catch (Exception e) {
                LOG.error(e.getLocalizedMessage(), e);
                throw new RuntimeException(e.getLocalizedMessage(), e);
            }
        }
    }

    /** @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult) */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    /** @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult) */
    @Override
    public void onTestFailure(ITestResult result) {
    }

    /** @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult) */
    @Override
    public void onTestSkipped(ITestResult result) {
    }

    /** @see org.testng.ITestListener#onTestStart(org.testng.ITestResult) */
    @Override
    public void onTestStart(ITestResult result) {
    }

    /** @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult) */
    @Override
    public void onTestSuccess(ITestResult result) {
    }

    private Set<ObjectFactory<AbstractResourceDescriptor>> getResourcesFactories(ITestNGMethod... testMethods) {
        Set<ObjectFactory<AbstractResourceDescriptor>> factories = new HashSet();
        for (ITestNGMethod testMethod : testMethods) {
            Object instance = testMethod.getInstance();
            if (hasEverrestJettyListenerTestHierarchy(instance.getClass())) {
                Field[] fields = instance.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (isRestResource(field.getType())) {
                        factories.add(new TestResourceFactory(field.getType(), instance, field));
                    }
                }

            }
        }
        return factories;
    }

    private Set<ExceptionMapper> getExceptionMappers(ITestNGMethod... testMethods) {
        Map<String, ExceptionMapper> factories = new HashMap();
        for (ITestNGMethod testMethod : testMethods) {
            Object instance = testMethod.getInstance();
            if (hasEverrestJettyListenerTestHierarchy(instance.getClass())) {
                Field[] fields = instance.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getType().isAssignableFrom(ExceptionMapper.class)) {
                        field.setAccessible(true);
                        try {
                            ExceptionMapper exceptionMapper = (ExceptionMapper)field.get(instance);
                            factories.put(exceptionMapper.getClass().getCanonicalName(), exceptionMapper);
                        } catch (IllegalAccessException e) {
                            LOG.error(e.getLocalizedMessage(), e);
                        }
                        ;
                    }
                }

            }
        }
        return new HashSet<>(factories.values());
    }

    private boolean hasEverrestJettyListener(Class<?> clazz) {
        Listeners listeners = clazz.getAnnotation(Listeners.class);
        if (listeners == null) {
            return false;
        }

        for (Class<? extends ITestNGListener> listenerClass : listeners.value()) {
            if (EverrestJetty.class.isAssignableFrom(listenerClass)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasEverrestJettyListenerTestHierarchy(Class<?> testClass) {
        for (Class<?> clazz = testClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
            if (hasEverrestJettyListener(clazz)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasEverrestJettyListenerTestHierarchy(ITestNGMethod... testMethods) {
        for (ITestNGMethod testMethod : testMethods) {
            Object instance = testMethod.getInstance();
            if (hasEverrestJettyListenerTestHierarchy(instance.getClass())) {
                return true;
            }
        }
        return false;
    }

    private boolean isRestResource(Class<? extends Object> resourceClass) {
        return resourceClass.isAnnotationPresent(Path.class) || resourceClass.isAnnotationPresent(Provider.class) ||
               resourceClass.isAnnotationPresent(Filter.class);
    }

}
