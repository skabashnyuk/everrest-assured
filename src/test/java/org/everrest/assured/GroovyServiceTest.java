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

import static com.jayway.restassured.RestAssured.given;
import static org.everrest.assured.EverrestJetty.JETTY_PORT;

import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 */
@Listeners(value = {EverrestJetty.class})
public class GroovyServiceTest
{
   private final GroovyServiceSource groovySource = new FileGroovyServiceSource("a/b/GroovyResource1.groovy");

   @Test
   public void testName(ITestContext context) throws Exception
   {

      given()
         //.log().all(true)
         .port((Integer)context.getAttribute(JETTY_PORT))
         .auth().basic("cldadmin", "tomcat")

         .expect()
         .statusCode(200)
         .body(Matchers.containsString("GroovyResource1"))

         .when()
         //.log().all()
         .get("/rest/a/b");
   }
}
