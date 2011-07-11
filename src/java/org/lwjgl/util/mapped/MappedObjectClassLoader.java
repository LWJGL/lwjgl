/*
 * Created on Jun 24, 2011
 */

package org.lwjgl.util.mapped;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @author Riven
 */

public class MappedObjectClassLoader extends URLClassLoader
{
   static final String MAPPEDOBJECT_PACKAGE_PREFIX;

   static
   {
      MAPPEDOBJECT_PACKAGE_PREFIX = MappedObjectClassLoader.class.getPackage().getName() + ".";
   }

   static boolean      FORKED = false;

   public static boolean fork(Class< ? > mainClass, String[] args)
   {
      if (FORKED)
      {
         return false;
      }

      FORKED = true;

      try
      {
         URLClassLoader loader = new MappedObjectClassLoader(mainClass);
         Class< ? > replacedMainClass = loader.loadClass(mainClass.getName());
         Method mainMethod = replacedMainClass.getMethod("main", String[].class);
         mainMethod.invoke(null, new Object[] { args });
      }
      catch (InvocationTargetException exc)
      {
         Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), exc.getCause());
      }
      catch (Throwable cause)
      {
         throw new Error("failed to fork", cause);
      }

      return true;
   }

   private MappedObjectClassLoader(Class< ? > mainClass)
   {
      super(((URLClassLoader) mainClass.getClassLoader()).getURLs());
   }

   private static long total_time_transforming;

   @Override
   protected synchronized Class< ? > loadClass(String name, boolean resolve) throws ClassNotFoundException
   {
      if (name.startsWith("java."))
         return super.loadClass(name, resolve);
      if (name.startsWith("javax."))
         return super.loadClass(name, resolve);

      if (name.startsWith("sun."))
         return super.loadClass(name, resolve);
      if (name.startsWith("sunw."))
         return super.loadClass(name, resolve);

      // never transform classes in this very package, sub-packages should be transformed
      if (name.startsWith(MAPPEDOBJECT_PACKAGE_PREFIX))
         if (name.substring(MAPPEDOBJECT_PACKAGE_PREFIX.length()).indexOf('.') == -1)
            return super.loadClass(name, resolve);

      String className = name.replace('.', '/');

      if (MappedObjectTransformer.PRINT_ACTIVITY)
         System.out.println(MappedObjectClassLoader.class.getSimpleName() + ": " + className);

      byte[] bytecode = readStream(this.getResourceAsStream(className.concat(".class")));

      long t0 = System.nanoTime();
      bytecode = MappedObjectTransformer.transformFieldAccess(className, bytecode);
      long t1 = System.nanoTime();
      total_time_transforming += (t1 - t0);

      if (MappedObjectTransformer.PRINT_TIMING)
         System.out.println("transforming " + className + " took " + (t1 - t0) / 1000 + " micros (total: " + (total_time_transforming / 1000 / 1000) + "ms)");

      Class< ? > clazz = super.defineClass(name, bytecode, 0, bytecode.length);
      if (resolve)
         resolveClass(clazz);
      return clazz;
   }

   static byte[] readStream(InputStream in)
   {
      byte[] bytecode = new byte[256];
      int len = 0;
      try
      {
         while (true)
         {
            if (bytecode.length == len)
               bytecode = Arrays.copyOf(bytecode, len * 2);
            int got = in.read(bytecode, len, bytecode.length - len);
            if (got == -1)
               break;
            len += got;
         }
      }
      catch (IOException exc)
      {
         // stop!
      }
      finally
      {
         try
         {
            in.close();
         }
         catch (IOException exc)
         {
            // ignore...
         }
      }
      return Arrays.copyOf(bytecode, len);
   }
}
