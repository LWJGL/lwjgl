/*
 * Created on Jun 24, 2011
 */

package org.lwjgl.util.mapped;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import sun.misc.Unsafe;

/**
 * @author Riven
 */

public class MappedObjectUnsafe
{
   public static final Unsafe    INSTANCE               = getUnsafeInstance();
   public static final int       ADDRESS_SIZE           = INSTANCE.addressSize();
   private static final Object[] ARRAY                  = new Object[1];
   private static final long     ARRAY_ELEMENT_OFFSET   = INSTANCE.arrayBaseOffset(ARRAY.getClass());

   private static final long     BUFFER_ADDRESS_OFFSET  = getObjectFieldOffset(ByteBuffer.class, "address");
   private static final long     BUFFER_CAPACITY_OFFSET = getObjectFieldOffset(ByteBuffer.class, "capacity");

   //

   public static long getBufferBaseAddress(Buffer buffer)
   {
      return INSTANCE.getLong(buffer, BUFFER_ADDRESS_OFFSET);
   }

   private static final ByteBuffer global = ByteBuffer.allocateDirect(4 * 1024);

   public static ByteBuffer newBuffer(long address, int capacity)
   {
      if (address <= 0L || capacity < 0)
         throw new IllegalStateException("you almost crashed the jvm");

      ByteBuffer buffer = global.duplicate();
      INSTANCE.putLong(buffer, BUFFER_ADDRESS_OFFSET, address);
      INSTANCE.putInt(buffer, BUFFER_CAPACITY_OFFSET, capacity);
      buffer.position(0);
      buffer.limit(capacity);
      return buffer;
   }

  

   private static long getObjectFieldOffset(Class< ? > type, String fieldName)
   {
      while (type != null)
      {
         try
         {
            return INSTANCE.objectFieldOffset(type.getDeclaredField(fieldName));
         }
         catch (Throwable t)
         {
            type = type.getSuperclass();
         }
      }
      throw new InternalError();
   }

   private static Unsafe getUnsafeInstance()
   {
      try
      {
         ByteBuffer buffer = ByteBuffer.allocateDirect(1);
         Field unsafeField = buffer.getClass().getDeclaredField("unsafe");
         unsafeField.setAccessible(true);
         Unsafe instance = (Unsafe) unsafeField.get(buffer);
         buffer.flip(); // prevented 'buffer' from being gc'ed
         return instance;
      }
      catch (Exception exc)
      {
         throw new InternalError();
      }
   }
}
