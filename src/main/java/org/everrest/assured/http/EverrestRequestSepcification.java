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

import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.mapper.ObjectMapper;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Cookies;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.AuthenticationSpecification;
import com.jayway.restassured.specification.RedirectSpecification;
import com.jayway.restassured.specification.RequestLogSpecification;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import groovyx.net.http.ContentType;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class EverrestRequestSepcification implements RequestSpecification
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
    * @see com.jayway.restassured.specification.RequestSpecification#body(java.lang.String)
    */
   @Override
   public RequestSpecification body(String body)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#body(byte[])
    */
   @Override
   public RequestSpecification body(byte[] body)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#body(java.lang.Object)
    */
   @Override
   public RequestSpecification body(Object object)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#body(java.lang.Object,
    *      com.jayway.restassured.mapper.ObjectMapper)
    */
   @Override
   public RequestSpecification body(Object object, ObjectMapper mapper)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#content(java.lang.String)
    */
   @Override
   public RequestSpecification content(String content)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#content(byte[])
    */
   @Override
   public RequestSpecification content(byte[] content)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#content(java.lang.Object)
    */
   @Override
   public RequestSpecification content(Object object)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#content(java.lang.Object,
    *      com.jayway.restassured.mapper.ObjectMapper)
    */
   @Override
   public RequestSpecification content(Object object, ObjectMapper mapper)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#redirects()
    */
   @Override
   public RedirectSpecification redirects()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#cookies(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification cookies(String firstCookieName, Object firstCookieValue, Object... cookieNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#cookies(java.util.Map)
    */
   @Override
   public RequestSpecification cookies(Map<String, ?> cookies)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#cookies(com.jayway.restassured.response.Cookies)
    */
   @Override
   public RequestSpecification cookies(Cookies cookies)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#cookie(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification cookie(String cookieName, Object value, Object... additionalValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#cookie(java.lang.String)
    */
   @Override
   public RequestSpecification cookie(String cookieName)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#cookie(com.jayway.restassured.response.Cookie)
    */
   @Override
   public RequestSpecification cookie(Cookie cookie)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#parameters(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification parameters(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#parameters(java.util.Map)
    */
   @Override
   public RequestSpecification parameters(Map<String, ?> parametersMap)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#parameter(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification parameter(String parameterName, Object parameterValue,
      Object... additionalParameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#parameter(java.lang.String,
    *      java.util.List)
    */
   @Override
   public RequestSpecification parameter(String parameterName, List<?> parameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#params(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification params(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#params(java.util.Map)
    */
   @Override
   public RequestSpecification params(Map<String, ?> parametersMap)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#param(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification param(String parameterName, Object parameterValue, Object... additionalParameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#param(java.lang.String,
    *      java.util.List)
    */
   @Override
   public RequestSpecification param(String parameterName, List<?> parameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParameters(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification queryParameters(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParameters(java.util.Map)
    */
   @Override
   public RequestSpecification queryParameters(Map<String, ?> parametersMap)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParameter(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification queryParameter(String parameterName, Object parameterValue,
      Object... additionalParameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParameter(java.lang.String,
    *      java.util.List)
    */
   @Override
   public RequestSpecification queryParameter(String parameterName, List<?> parameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParams(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification queryParams(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParams(java.util.Map)
    */
   @Override
   public RequestSpecification queryParams(Map<String, ?> parametersMap)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParam(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification queryParam(String parameterName, Object parameterValue,
      Object... additionalParameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#queryParam(java.lang.String,
    *      java.util.List)
    */
   @Override
   public RequestSpecification queryParam(String parameterName, List<?> parameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParameters(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification formParameters(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParameters(java.util.Map)
    */
   @Override
   public RequestSpecification formParameters(Map<String, ?> parametersMap)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParameter(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification formParameter(String parameterName, Object parameterValue,
      Object... additionalParameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParameter(java.lang.String,
    *      java.util.List)
    */
   @Override
   public RequestSpecification formParameter(String parameterName, List<?> parameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParams(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification formParams(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParams(java.util.Map)
    */
   @Override
   public RequestSpecification formParams(Map<String, ?> parametersMap)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParam(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification formParam(String parameterName, Object parameterValue,
      Object... additionalParameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#formParam(java.lang.String,
    *      java.util.List)
    */
   @Override
   public RequestSpecification formParam(String parameterName, List<?> parameterValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#pathParameter(java.lang.String,
    *      java.lang.Object)
    */
   @Override
   public RequestSpecification pathParameter(String parameterName, Object parameterValue)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#pathParameters(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification pathParameters(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#pathParameters(java.util.Map)
    */
   @Override
   public RequestSpecification pathParameters(Map<String, ?> parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#pathParam(java.lang.String,
    *      java.lang.Object)
    */
   @Override
   public RequestSpecification pathParam(String parameterName, Object parameterValue)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#pathParams(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification pathParams(String firstParameterName, Object firstParameterValue,
      Object... parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#pathParams(java.util.Map)
    */
   @Override
   public RequestSpecification pathParams(Map<String, ?> parameterNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#config(com.jayway.restassured.config.RestAssuredConfig)
    */
   @Override
   public RequestSpecification config(RestAssuredConfig config)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#keystore(java.lang.String,
    *      java.lang.String)
    */
   @Override
   public RequestSpecification keystore(String pathToJks, String password)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#keystore(java.io.File,
    *      java.lang.String)
    */
   @Override
   public RequestSpecification keystore(File pathToJks, String password)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#headers(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification headers(String firstHeaderName, Object firstHeaderValue, Object... headerNameValuePairs)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#headers(java.util.Map)
    */
   @Override
   public RequestSpecification headers(Map<String, ?> headers)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#headers(com.jayway.restassured.response.Headers)
    */
   @Override
   public RequestSpecification headers(Headers headers)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#header(java.lang.String,
    *      java.lang.Object, java.lang.Object[])
    */
   @Override
   public RequestSpecification header(String headerName, Object headerValue, Object... additionalHeaderValues)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#header(com.jayway.restassured.response.Header)
    */
   @Override
   public RequestSpecification header(Header header)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#contentType(groovyx.net.http.ContentType)
    */
   @Override
   public RequestSpecification contentType(ContentType contentType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#contentType(java.lang.String)
    */
   @Override
   public RequestSpecification contentType(String contentType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.io.File)
    */
   @Override
   public RequestSpecification multiPart(File file)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.io.File)
    */
   @Override
   public RequestSpecification multiPart(String controlName, File file)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.io.File, java.lang.String)
    */
   @Override
   public RequestSpecification multiPart(String controlName, File file, String mimeType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.lang.String, byte[])
    */
   @Override
   public RequestSpecification multiPart(String controlName, String fileName, byte[] bytes)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.lang.String, byte[], java.lang.String)
    */
   @Override
   public RequestSpecification multiPart(String controlName, String fileName, byte[] bytes, String mimeType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.lang.String, java.io.InputStream)
    */
   @Override
   public RequestSpecification multiPart(String controlName, String fileName, InputStream stream)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.lang.String, java.io.InputStream, java.lang.String)
    */
   @Override
   public RequestSpecification multiPart(String controlName, String fileName, InputStream stream, String mimeType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.lang.String)
    */
   @Override
   public RequestSpecification multiPart(String controlName, String contentBody)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#multiPart(java.lang.String,
    *      java.lang.String, java.lang.String)
    */
   @Override
   public RequestSpecification multiPart(String controlName, String contentBody, String mimeType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#authentication()
    */
   @Override
   public AuthenticationSpecification authentication()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#auth()
    */
   @Override
   public AuthenticationSpecification auth()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#port(int)
    */
   @Override
   public RequestSpecification port(int port)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#spec(com.jayway.restassured.specification.RequestSpecification)
    */
   @Override
   public RequestSpecification spec(RequestSpecification requestSpecificationToMerge)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#specification(com.jayway.restassured.specification.RequestSpecification)
    */
   @Override
   public RequestSpecification specification(RequestSpecification requestSpecificationToMerge)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#urlEncodingEnabled(boolean)
    */
   @Override
   public RequestSpecification urlEncodingEnabled(boolean isEnabled)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#filter(com.jayway.restassured.filter.Filter)
    */
   @Override
   public RequestSpecification filter(Filter filter)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#filters(java.util.List)
    */
   @Override
   public RequestSpecification filters(List<Filter> filters)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#filters(com.jayway.restassured.filter.Filter,
    *      com.jayway.restassured.filter.Filter[])
    */
   @Override
   public RequestSpecification filters(Filter filter, Filter... additionalFilter)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#noFilters()
    */
   @Override
   public RequestSpecification noFilters()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#noFiltersOfType(java.lang.Class)
    */
   @Override
   public <T extends Filter> RequestSpecification noFiltersOfType(Class<T> filterType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#log()
    */
   @Override
   public RequestLogSpecification log()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#response()
    */
   @Override
   public ResponseSpecification response()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#and()
    */
   @Override
   public RequestSpecification and()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#with()
    */
   @Override
   public RequestSpecification with()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#then()
    */
   @Override
   public ResponseSpecification then()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#expect()
    */
   @Override
   public ResponseSpecification expect()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#when()
    */
   @Override
   public RequestSpecification when()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#given()
    */
   @Override
   public RequestSpecification given()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#that()
    */
   @Override
   public RequestSpecification that()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see com.jayway.restassured.specification.RequestSpecification#request()
    */
   @Override
   public RequestSpecification request()
   {
      // TODO Auto-generated method stub
      return null;
   }

}
