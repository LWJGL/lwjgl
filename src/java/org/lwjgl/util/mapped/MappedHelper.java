/*
 * Created on Jun 28, 2011
 */

package org.lwjgl.util.mapped;

import java.nio.ByteBuffer;

/**
 * @author Riven
 */

public class MappedHelper
{
   public static void setup(MappedObject mo, ByteBuffer buffer, int align, int sizeof)
   {
      if (mo.baseAddress != 0L)
         throw new IllegalStateException("this method should not be called by user-code");

      if (buffer == null)
         throw new NullPointerException("buffer");
      if (!buffer.isDirect())
         throw new IllegalArgumentException("bytebuffer must be direct");
      mo.preventGC = buffer;

      if (align <= 0)
         throw new IllegalArgumentException("invalid alignment");
      mo.align = align;

      if (sizeof % align != 0)
         throw new IllegalStateException("sizeof not a multiple of alignment");
      mo.stride = sizeof;

      long addr = MappedObjectUnsafe.getBufferBaseAddress(buffer);
      if (addr % align != 0)
         throw new IllegalStateException("buffer address not aligned on " + align + " bytes");

      mo.baseAddress = mo.viewAddress = addr;
   }

   public static void put_views(MappedSet2 set, int view)
   {
      set.view(view);
   }

   public static void put_views(MappedSet3 set, int view)
   {
      set.view(view);
   }

   public static void put_views(MappedSet4 set, int view)
   {
      set.view(view);
   }

   public static void put_view(MappedObject mapped, int view)
   {
      mapped.viewAddress = mapped.baseAddress + view * mapped.stride;
   }

   public static int get_view(MappedObject mapped)
   {
      return (int) (mapped.viewAddress - mapped.baseAddress) / mapped.stride;
   }

   public static MappedObject dup(MappedObject src, MappedObject dst)
   {
      dst.baseAddress = src.baseAddress;
      dst.viewAddress = src.viewAddress;
      dst.stride = src.stride;
      dst.align = src.align;
      dst.preventGC = src.preventGC;
      return dst;
   }

   public static MappedObject slice(MappedObject src, MappedObject dst)
   {
      dst.baseAddress = src.viewAddress; // !
      dst.viewAddress = src.viewAddress;
      dst.stride = src.stride;
      dst.align = src.align;
      dst.preventGC = src.preventGC;
      return dst;
   }

   public static void copy(MappedObject src, MappedObject dst, int bytes)
   {
      MappedObjectUnsafe.INSTANCE.copyMemory(src.viewAddress, dst.viewAddress, bytes);
   }

   public static ByteBuffer newBuffer(long address, int capacity)
   {
      return MappedObjectUnsafe.newBuffer(address, capacity);
   }

   // ---- primitive fields read/write

   // byte

   public static void bput(byte value, long addr)
   {
      MappedObjectUnsafe.INSTANCE.putByte(addr, value);
   }

   public static byte bget(long addr)
   {
      return MappedObjectUnsafe.INSTANCE.getByte(addr);
   }

   // short

   public static void sput(short value, long addr)
   {
      MappedObjectUnsafe.INSTANCE.putShort(addr, value);
   }

   public static short sget(long addr)
   {
      return MappedObjectUnsafe.INSTANCE.getShort(addr);
   }

   // char

   public static void cput(char value, long addr)
   {
      MappedObjectUnsafe.INSTANCE.putChar(addr, value);
   }

   public static char cget(long addr)
   {
      return MappedObjectUnsafe.INSTANCE.getChar(addr);
   }

   // int

   public static void iput(int value, long addr)
   {
      MappedObjectUnsafe.INSTANCE.putInt(addr, value);
   }

   public static int iget(long addr)
   {
      return MappedObjectUnsafe.INSTANCE.getInt(addr);
   }

   // float

   public static void fput(float value, long addr)
   {
      MappedObjectUnsafe.INSTANCE.putFloat(addr, value);
   }

   public static float fget(long addr)
   {
      return MappedObjectUnsafe.INSTANCE.getFloat(addr);
   }

   // long

   public static void jput(long value, long addr)
   {
      MappedObjectUnsafe.INSTANCE.putLong(addr, value);
   }

   public static long jget(long addr)
   {
      return MappedObjectUnsafe.INSTANCE.getLong(addr);
   }

   // double

   public static void dput(double value, long addr)
   {
      MappedObjectUnsafe.INSTANCE.putDouble(addr, value);
   }

   public static double dget(long addr)
   {
      return MappedObjectUnsafe.INSTANCE.getDouble(addr);
   }
}