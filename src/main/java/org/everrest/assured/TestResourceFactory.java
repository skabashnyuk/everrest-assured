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
package org.everrest.assured;

import org.everrest.core.ApplicationContext;
import org.everrest.core.ComponentLifecycleScope;
import org.everrest.core.ObjectFactory;
import org.everrest.core.impl.resource.AbstractResourceDescriptorImpl;
import org.everrest.core.resource.AbstractResourceDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Get instance of the REST resource from test class in request time.
 */
public class TestResourceFactory implements ObjectFactory<AbstractResourceDescriptor>
{

   private static final Logger LOG = LoggerFactory.getLogger(TestResourceFactory.class);

   private final Object testParent;

   private final Field resourceField;

   private final Class<?> resourceClass;

   public TestResourceFactory(Class<?> resourceClass, Object testParent, Field resourceField)
   {
      this.resourceClass = resourceClass;
      this.testParent = testParent;
      this.resourceField = resourceField;
   }

   /**
    * @see org.everrest.core.ObjectFactory#getInstance(org.everrest.core.ApplicationContext)
    */
   @Override
   public Object getInstance(ApplicationContext context)
   {
      try
      {
         resourceField.setAccessible(true);
         return resourceField.get(testParent);
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

   /**
    * @see org.everrest.core.ObjectFactory#getObjectModel()
    */
   @Override
   public AbstractResourceDescriptor getObjectModel()
   {
      return new AbstractResourceDescriptorImpl(resourceClass, ComponentLifecycleScope.PER_REQUEST);
   }

}
