/*
 * Created on Jun 25, 2011
 */

package org.lwjgl.util.mapped;

import java.nio.ByteBuffer;

/**
 * @author Riven
 */

public class MappedObject
{
   public MappedObject()
   {
      //
   }

   // these fields are not assignable/writable by user-code  
   public long       baseAddress;
   public long       viewAddress;
   public int        stride;
   public int        align;

   /**
    * Holds the value of sizeof of the sub-type of this MappedObject<br>
    * <br>
    * The behavior of this (transformed) method does not follow the normal Java behavior.<br>
    * <code>Vec2.SIZEOF</code> will yield 8 (2 floats)<br>
    * <code>Vec3.SIZEOF</code> will yield 12 (3 floats)<br>
    * This (required) notation might cause compiler warnings, which can be suppressed with @SuppressWarnings("static-access").<br>
    * Using Java 5.0's static-import on this method will break functionality.
    */

   // any method that calls these field will have its call-site modified
   public static int SIZEOF = -1; // 'final' per subtype
   public int        view;       // read/write

   public final void next()
   {
      this.viewAddress += this.stride;
   }

   /**
    * Creates a MappedObject instance, mapping the memory region of the specified direct ByteBuffer.<br>
    * <br>
    * The behavior of this (transformed) method does not follow the normal Java behavior.<br>
    * <code>Vec2.map(buffer)</code> will return a mapped Vec2 instance.<br>
    * <code>Vec3.map(buffer)</code> will return a mapped Vec3 instance.<br>
    * This (required) notation might cause compiler warnings, which can be suppressed with @SuppressWarnings("static-access").<br>
    * Using Java 5.0's static-import on this method will break functionality.
    */

   @SuppressWarnings("unused")
   public static <T extends MappedObject> T map(ByteBuffer bb)
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   @SuppressWarnings("unused")
   public static <T extends MappedObject> T map(long address, int capacity)
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   /**
    * Creates a MappedObject instance, mapping the memory region of an allocated direct ByteBuffer with a capacity of <code>elementCount*SIZEOF</code> 
    * <br>
    * The behavior of this (transformed) method does not follow the normal Java behavior.<br>
    * <code>Vec2.malloc(int)</code> will return a mapped Vec2 instance.<br>
    * <code>Vec3.malloc(int)</code> will return a mapped Vec3 instance.<br>
    * This (required) notation might cause compiler warnings, which can be suppressed with @SuppressWarnings("static-access").<br>
    * Using Java 5.0's static-import on this method will break functionality.
    */

   @SuppressWarnings("unused")
   public static <T extends MappedObject> T malloc(int elementCount)
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   /**
    * Creates an identical new MappedObject instance, comparable to the
    * contract of ByteBuffer.duplicate()
    */

   public final <T extends MappedObject> T dup()
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   /**
    * Creates a new MappedObject instance, with a base offset equal to
    * the offset of the current view, comparable to the contract of ByteBuffer.slice()
    */

   public final <T extends MappedObject> T slice()
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   public final <T extends MappedObject> void runViewConstructor()
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   /**
    * Copies and amount of <code>SIZEOF</code> bytes, from the current
    * mapped object, to the specified mapped object.
    */

   @SuppressWarnings("unused")
   public final <T extends MappedObject> void copyTo(T target)
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   /**
    * Copies and amount of <code>SIZEOF*instances<c/ode> bytes, from the
    * current mapped object, to the specified mapped object.
    */

   @SuppressWarnings("unused")
   public final <T extends MappedObject> void copyRange(T target, int instances)
   {
      // any method that calls this method will have its call-site modified
      throw new InternalError("type not registered");
   }

   /**
    * Creates an Iterable<MappedObject> that will step through
    * <code>elementCount</code> views, leaving the <code>view</code> at
    * the last valid value.<br>
    * <br>
    * For convenience you are encouraged to static-import this specific method:
    * <code>import static org.lwjgl.util.mapped.MappedObject.foreach;</code>
    */

   public static <T extends MappedObject> MappedForeach<T> foreach(T mapped, int elementCount)
   {
      return new MappedForeach<T>(mapped, elementCount);
   }

   /**
    * Configures a newly initiated mapped object with the specified stride and offset.
    * @throws IllegalStateException if view is not at index 0 
    */

   public static final <T extends MappedObject> T configure(T mapped, int stride, int offset)
   {
      if (mapped.baseAddress != mapped.viewAddress)
         throw new IllegalStateException("view must be zero");

      if (offset < 0)
         throw new IllegalStateException("offset must not be negative: " + offset);
      if (offset % mapped.align != 0)
         throw new IllegalStateException("offset not a multiple of alignment: " + offset);

      if (stride < mapped.stride)
         throw new IllegalStateException("new stride must not be smaller than current stride: " + stride);
      if (stride % mapped.align != 0)
         throw new IllegalStateException("stride not a multiple of alignment: " + stride);

      mapped.baseAddress += offset;
      mapped.viewAddress += offset;
      mapped.stride = stride;

      return mapped;
   }

   ByteBuffer preventGC;

   public ByteBuffer backingByteBuffer()
   {
      return this.preventGC;
   }
}
