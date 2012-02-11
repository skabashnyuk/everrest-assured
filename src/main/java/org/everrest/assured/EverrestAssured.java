/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.everrest.assured;

import com.jayway.restassured.specification.ResponseSpecification;

import org.everrest.assured.http.EverrestRequestSepcification;
import org.everrest.assured.http.EverrestResponseSpecification;
import org.everrest.assured.http.TestSpecefication;

public class EverrestAssured
{
   /**
    * Start building the response part of the test
    * com.jayway.restassured.specification. E.g.
    * 
    * <pre>
    * expect().body(&quot;lotto.lottoId&quot;, equalTo(5)).when().get(&quot;/lotto&quot;);
    * </pre>
    * 
    * will expect that the response body for the GET request to "/lotto" should
    * contain JSON or XML which has a lottoId equal to 5.
    * 
    * @return A response com.jayway.restassured.specification.
    */
   public static ResponseSpecification expect()
   {
      return createTestSpec().getResponseSpecification();
   }

   /**
    * @return
    */
   private static TestSpecefication createTestSpec()
   {
      return new TestSpecefication(new EverrestResponseSpecification(), new EverrestRequestSepcification());
   }
}
