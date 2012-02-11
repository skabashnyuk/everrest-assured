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

import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.Argument;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseLogSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import groovyx.net.http.ContentType;

import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class EverrestResponseSpecification implements ResponseSpecification
{

   /**
    * @see com.jayway.restassured.specification.RequestSender#get(java.lang.String,
    *      java.lang.Object[])
    */
   @Override
   public Response get(String path, Object... pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#get(java.lang.String,
    *      java.util.Map)
    */
   @Override
   public Response get(String path, Map<String, ?> pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#post(java.lang.String,
    *      java.lang.Object[])
    */
   @Override
   public Response post(String path, Object... pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#post(java.lang.String,
    *      java.util.Map)
    */
   @Override
   public Response post(String path, Map<String, ?> pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#put(java.lang.String,
    *      java.lang.Object[])
    */
   @Override
   public Response put(String path, Object... pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#put(java.lang.String,
    *      java.util.Map)
    */
   @Override
   public Response put(String path, Map<String, ?> pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#delete(java.lang.String,
    *      java.lang.Object[])
    */
   @Override
   public Response delete(String path, Object... pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#delete(java.lang.String,
    *      java.util.Map)
    */
   @Override
   public Response delete(String path, Map<String, ?> pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#head(java.lang.String,
    *      java.lang.Object[])
    */
   @Override
   public Response head(String path, Object... pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSender#head(java.lang.String,
    *      java.util.Map)
    */
   @Override
   public Response head(String path, Map<String, ?> pathParams)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see 
    *      com.jayway.restassured.specification.ResponseSpecification#content(org
    *      .hamcrest.Matcher, org.hamcrest.Matcher<?>[])
    */
   @Override
   public ResponseSpecification content(Matcher<?> matcher, Matcher<?>... additionalMatchers)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#content(java.lang.String,
    *      org.hamcrest.Matcher, java.lang.Object[])
    */
   @Override
   public ResponseSpecification content(String key, Matcher<?> matcher, Object... additionalKeyMatcherPairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#body(java.lang.String,
    *      java.util.List, org.hamcrest.Matcher, java.lang.Object[])
    */
   @Override
   public ResponseSpecification body(String key, List<Argument> arguments, Matcher matcher,
      Object... additionalKeyMatcherPairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#statusCode(org.hamcrest.Matcher)
    */
   @Override
   public ResponseSpecification statusCode(Matcher<Integer> expectedStatusCode)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#statusCode(int)
    */
   @Override
   public ResponseSpecification statusCode(int expectedStatusCode)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#statusLine(org.hamcrest.Matcher)
    */
   @Override
   public ResponseSpecification statusLine(Matcher<String> expectedStatusLine)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#statusLine(java.lang.String)
    */
   @Override
   public ResponseSpecification statusLine(String expectedStatusLine)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#headers(java.util.Map)
    */
   @Override
   public ResponseSpecification headers(Map<String, ?> expectedHeaders)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#headers(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public ResponseSpecification headers(String firstExpectedHeaderName, Object firstExpectedHeaderValue,
      Object... expectedHeaders)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#header(java.lang.String,
    *      org.hamcrest.Matcher)
    */
   @Override
   public ResponseSpecification header(String headerName, Matcher<?> expectedValueMatcher)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#header(java.lang.String,
    *      java.lang.String)
    */
   @Override
   public ResponseSpecification header(String headerName, String expectedValue)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#cookies(java.util.Map)
    */
   @Override
   public ResponseSpecification cookies(Map<String, ?> expectedCookies)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#cookie(java.lang.String)
    */
   @Override
   public ResponseSpecification cookie(String cookieName)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#cookies(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public ResponseSpecification cookies(String firstExpectedCookieName, Object firstExpectedCookieValue,
      Object... expectedCookieNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#cookie(java.lang.String,
    *      org.hamcrest.Matcher)
    */
   @Override
   public ResponseSpecification cookie(String cookieName, Matcher<?> expectedValueMatcher)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#cookie(java.lang.String,
    *      java.lang.Object)
    */
   @Override
   public ResponseSpecification cookie(String cookieName, Object expectedValue)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#log()
    */
   @Override
   public ResponseLogSpecification log()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#rootPath(java.lang.String)
    */
   @Override
   public ResponseSpecification rootPath(String rootPath)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#root(java.lang.String)
    */
   @Override
   public ResponseSpecification root(String rootPath)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#contentType(groovyx.net.http.ContentType)
    */
   @Override
   public ResponseSpecification contentType(ContentType contentType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#contentType(java.lang.String)
    */
   @Override
   public ResponseSpecification contentType(String contentType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#body(org.
    *      hamcrest.Matcher, org.hamcrest.Matcher<?>[])
    */
   @Override
   public ResponseSpecification body(Matcher<?> matcher, Matcher<?>... additionalMatchers)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#body(java.lang.String,
    *      org.hamcrest.Matcher, java.lang.Object[])
    */
   @Override
   public ResponseSpecification body(String key, Matcher<?> matcher, Object... additionalKeyMatcherPairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#content(java.lang.String,
    *      java.util.List, org.hamcrest.Matcher, java.lang.Object[])
    */
   @Override
   public ResponseSpecification content(String key, List<Argument> arguments, Matcher matcher,
      Object... additionalKeyMatcherPairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#when()
    */
   @Override
   public ResponseSpecification when()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#given()
    */
   @Override
   public RequestSpecification given()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#that()
    */
   @Override
   public ResponseSpecification that()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#request()
    */
   @Override
   public RequestSpecification request()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#response()
    */
   @Override
   public ResponseSpecification response()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#and()
    */
   @Override
   public ResponseSpecification and()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#with()
    */
   @Override
   public RequestSpecification with()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#then()
    */
   @Override
   public ResponseSpecification then()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#expect()
    */
   @Override
   public ResponseSpecification expect()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#spec(com.jayway.restassured.specification.ResponseSpecification)
    */
   @Override
   public ResponseSpecification spec(ResponseSpecification responseSpecificationToMerge)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#specification(com.jayway.restassured.specification.ResponseSpecification)
    */
   @Override
   public ResponseSpecification specification(ResponseSpecification responseSpecificationToMerge)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#parser(java.lang.String,
    *      com.jayway.restassured.parsing.Parser)
    */
   @Override
   public ResponseSpecification parser(String contentType, Parser parser)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.ResponseSpecification#defaultParser(com.jayway.restassured.parsing.Parser)
    */
   @Override
   public ResponseSpecification defaultParser(Parser parser)
   {
      // TODO Auto-generated method stub
      return null;
   }

}
