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
package org.everrest.assured.http;

import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

/**
 *
 */
public class TestSpecefication
{
   private final ResponseSpecification responseSpecification;

   private final RequestSpecification requestSpecification;

   /**
    * @param responseSpecification
    * @param requestSpecification
    */
   public TestSpecefication(ResponseSpecification responseSpecification, RequestSpecification requestSpecification)
   {
      super();
      this.responseSpecification = responseSpecification;
      this.requestSpecification = requestSpecification;
   }

   /**
    * @return
    */
   public ResponseSpecification getResponseSpecification()
   {
      return responseSpecification;
   }

}
