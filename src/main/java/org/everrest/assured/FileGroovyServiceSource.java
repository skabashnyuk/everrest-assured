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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *  
 */
public class FileGroovyServiceSource implements GroovyServiceSource
{

   private static final Logger LOG = LoggerFactory.getLogger(FileGroovyServiceSource.class);

   private final String fileName;

   /**
    * @param fileName
    */
   public FileGroovyServiceSource(String fileName)
   {
      super();
      this.fileName = fileName;
   }

   /**
    * @see org.everrest.assured.GroovyServiceSource#getSource()
    */
   @Override
   public String getSource()
   {

      InputStream stream = null;
      try
      {
         File file = new File(fileName);
         if (file.isFile() && file.exists())
         {
            stream = new FileInputStream(file);
         }
         else
         {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
         }
         Reader reader = new BufferedReader(new InputStreamReader(stream));
         StringBuilder builder = new StringBuilder();
         char[] buffer = new char[8192];
         int read;
         while ((read = reader.read(buffer, 0, buffer.length)) > 0)
         {
            builder.append(buffer, 0, read);
         }
         return builder.toString();
      }
      catch (IOException e)
      {
         LOG.error(e.getLocalizedMessage(), e);

      }
      finally
      {
         if (stream != null)
         {
            try
            {
               stream.close();
            }
            catch (IOException e)
            {
               LOG.error(e.getLocalizedMessage(), e);
            }
         }
      }
      return "";
   }

   /**
    * @see org.everrest.assured.GroovyServiceSource#getResourceId()
    */
   @Override
   public String getResourceId()
   {
      return fileName;
   }

}
