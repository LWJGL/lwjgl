/*
 * Created on Jun 23, 2011
 */

package org.lwjgl.util.mapped;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;
import static org.objectweb.asm.Opcodes.*;

/**
 * @author Riven
 */

public class MappedObjectTransformer
{
   static final boolean                        PRINT_TIMING   = true;
   static final boolean                        PRINT_ACTIVITY = false;
   static final boolean                        PRINT_BYTECODE = false;
   static final Map<String, MappedSubtypeInfo> className_to_subtype;

   static
   {
      className_to_subtype = new HashMap<String, MappedSubtypeInfo>();

      {
         // HACK: required for mapped.view++
         //
         // because the compiler generates:
         // => GETFIELD MappedObject.view
         // => ICONST_1
         // => IADD
         // => PUTFIELD MyMappedType.view
         //
         // instead of:
         // => GETFIELD MyMappedType.view
         // => ICONST_1
         // => IADD
         // => PUTFIELD MyMappedType.view
         //
         MappedSubtypeInfo info = new MappedSubtypeInfo(jvmClassName(MappedObject.class), -1, -1);
         className_to_subtype.put(info.className, info);
      }

      String vmName = System.getProperty("java.vm.name");
      if (vmName != null && !vmName.contains("Server"))
      {
         System.err.println("Warning: " + MappedObject.class.getSimpleName() + "s have inferiour performance on Client VMs, please consider switching to a Server VM.");
      }
   }

   public static void register(Class< ? > type)
   {
      if (MappedObjectClassLoader.FORKED)
         return;

      MappedType mapped = type.getAnnotation(MappedType.class);
      if (mapped == null)
         throw new InternalError("missing " + MappedType.class.getName() + " annotation");

      String className = jvmClassName(type);

      MappedSubtypeInfo mappedType = new MappedSubtypeInfo(className, mapped.sizeof(), mapped.align());

      int advancingOffset = 0;

      for (Field field : type.getDeclaredFields())
      {
         // static fields are never mapped
         if ((field.getModifiers() & Modifier.STATIC) != 0)
            continue;

         // we only support primitives and ByteBuffers
         if (!field.getType().isPrimitive() && field.getType() != ByteBuffer.class)
            throw new InternalError("field '" + className + "." + field.getName() + "' not supported: " + field.getType());

         MappedField meta = field.getAnnotation(MappedField.class);
         if (meta == null && !mapped.autoGenerateOffsets())
            throw new InternalError("field '" + className + "." + field.getName() + "' missing annotation " + MappedField.class.getName() + ": " + className);

         // quick hack
         long byteOffset = meta == null ? advancingOffset : meta.byteOffset();
         long byteLength;
         if (field.getType() == long.class || field.getType() == double.class)
            byteLength = 8;
         else if (field.getType() == int.class || field.getType() == float.class)
            byteLength = 4;
         else if (field.getType() == char.class || field.getType() == short.class)
            byteLength = 2;
         else if (field.getType() == byte.class)
            byteLength = 1;
         else if (field.getType() == ByteBuffer.class)
            byteLength = meta.byteLength();
         else
            throw new IllegalStateException(field.getType().getName());

         if (field.getType() != ByteBuffer.class)
            if ((advancingOffset % byteLength) != 0)
               throw new IllegalStateException("misaligned mapped type: " + className + "." + field.getName());

         if (PRINT_ACTIVITY)
            System.out.println(MappedObjectTransformer.class.getSimpleName() + ": " + className + "." + field.getName() + " [type=" + field.getType().getSimpleName() + ", offset=" + byteOffset + "]");

         mappedType.fieldToOffset.put(field.getName(), Long.valueOf(byteOffset));
         mappedType.fieldToLength.put(field.getName(), Long.valueOf(byteLength));

         advancingOffset += byteLength;
      }

      if (className_to_subtype.put(className, mappedType) != null)
      {
         throw new InternalError("duplicate mapped type: " + className);
      }
   }

   public static final String view_constructor_method = "_construct_view_";

   public static byte[] transformFieldAccess(final String className, byte[] bytecode)
   {
      int flags = ClassWriter.COMPUTE_FRAMES;

      ClassWriter writer = new ClassWriter(flags);

      ClassAdapter adapter = new ClassAdapter(writer)
      {
         @Override
         public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
         {
            {
               MappedSubtypeInfo mappedSubtype = className_to_subtype.get(className);

               if (mappedSubtype != null && !mappedSubtype.className.equals(jvmClassName(MappedObject.class)))
               {
                  if (name.equals("<init>"))
                  {
                     if (!desc.equals("()V"))
                        throw new IllegalStateException(className + " can only have a default constructor, found: " + desc);

                     MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                     mv.visitCode();
                     mv.visitVarInsn(ALOAD, 0);
                     mv.visitMethodInsn(INVOKESPECIAL, jvmClassName(MappedObject.class), "<init>", "()V");
                     mv.visitInsn(RETURN);
                     mv.visitMaxs(1, 1);
                     mv.visitEnd();

                     // put the method body in another method
                     name = view_constructor_method;
                  }
               }
            }

            return new MappedInstanceMethodAdapter(className, name, desc, super.visitMethod(access, name, desc, signature, exceptions));
         }

         @Override
         public FieldVisitor visitField(int access, String fieldName, String typeName, String dunno, Object value)
         {
            // remove redirected fields
            MappedSubtypeInfo mappedSubtype = className_to_subtype.get(className);

            if (mappedSubtype != null && mappedSubtype.fieldToOffset.containsKey(fieldName))
            {
               if (PRINT_ACTIVITY)
                  System.out.println(MappedObjectTransformer.class.getSimpleName() + ": discarding field: " + className + "." + fieldName + ":" + typeName);
               return null;
            }

            return super.visitField(access, fieldName, typeName, dunno, value);
         }
      };

      new ClassReader(bytecode).accept(adapter, 0);
      bytecode = writer.toByteArray();

      if (PRINT_BYTECODE)
         printBytecode(bytecode, adapter);

      return bytecode;
   }

   static void printBytecode(byte[] bytecode, ClassAdapter adapter)
   {
      StringWriter sw = new StringWriter();
      ClassVisitor tracer = new TraceClassVisitor(adapter, new PrintWriter(sw));
      new ClassReader(bytecode).accept(tracer, 0);
      String dump = sw.toString();

      System.out.println(dump);
   }

   static void throwException0(MethodVisitor visitor, Class< ? > type, String msg)
   {
      String errorType = jvmClassName(type);

      visitor.visitTypeInsn(NEW, errorType);
      visitor.visitInsn(DUP);
      visitor.visitLdcInsn(msg);
      visitor.visitMethodInsn(INVOKESPECIAL, errorType, "<init>", "(Ljava/lang/String;)V");
      visitor.visitInsn(ATHROW);
   }

   static class MappedInstanceMethodAdapter extends MethodAdapter
   {
      private final String className;
      private final String methodName;
      private final String descr;

      public MappedInstanceMethodAdapter(String className, String methodName, String descr, MethodVisitor backing)
      {
         super(backing);
         this.className = className;
         this.methodName = methodName;
         this.descr = descr;
      }

      @Override
      public void visitTypeInsn(int opcode, String typeName)
      {
         if (opcode == NEW && className_to_subtype.containsKey(typeName))
         {
            throw new IllegalAccessError("must not manually create instances of " + typeName + " in method " + this.className + "." + this.methodName + this.descr);
         }

         super.visitTypeInsn(opcode, typeName);
      }

      @Override
      public void visitMethodInsn(int opcode, String className, String methodName, String signature)
      {
         if (opcode == INVOKESPECIAL && className.equals(jvmClassName(MappedObject.class)) && methodName.equals("<init>") && signature.equals("()V"))
         {
            // stack: instance
            visitInsn(POP);
            // stack: -
            return;
         }

         for (MappedSubtypeInfo mappedType : className_to_subtype.values())
         {
            boolean isMapDirectMethod = (opcode == INVOKESTATIC && methodName.equals("map") && className.equals(mappedType.className) && signature.equals("(JI)L" + jvmClassName(MappedObject.class) + ";"));
            boolean isMapBufferMethod = (opcode == INVOKESTATIC && methodName.equals("map") && className.equals(mappedType.className) && signature.equals("(Ljava/nio/ByteBuffer;)L" + jvmClassName(MappedObject.class) + ";"));
            boolean isMallocMethod = (opcode == INVOKESTATIC && methodName.equals("malloc") && className.equals(mappedType.className) && signature.equals("(I)L" + jvmClassName(MappedObject.class) + ";"));

            if ((isMapDirectMethod || isMapBufferMethod) || isMallocMethod)
            {
               if (isMallocMethod)
               {
                  // stack: count
                  pushInt(super.mv, mappedType.sizeof);
                  // stack: sizeof, count
                  super.visitInsn(IMUL);
                  // stack: bytes
                  super.visitMethodInsn(INVOKESTATIC, jvmClassName(ByteBuffer.class), "allocateDirect", "(I)L" + jvmClassName(ByteBuffer.class) + ";");
                  // stack: buffer
               }
               else if (isMapDirectMethod)
               {
                  // stack: capacity, address
                  super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";");
                  // stack: buffer
               }

               // stack: buffer
               super.visitTypeInsn(NEW, className);
               // stack: new, buffer
               super.visitInsn(DUP);
               // stack: new, new, buffer
               super.visitMethodInsn(INVOKESPECIAL, className, "<init>", "()V");
               // stack: new, buffer
               super.visitInsn(DUP_X1);
               // stack: new, buffer, new
               super.visitInsn(SWAP);
               // stack: buffer, new, new
               pushInt(super.mv, mappedType.align);
               // stack: int, buffer, new, new
               pushInt(super.mv, mappedType.sizeof);
               // stack: int, int, buffer, new, new
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "setup", "(L" + jvmClassName(MappedObject.class) + ";Ljava/nio/ByteBuffer;II)V");
               // stack: new
               return;
            }

            if (opcode == INVOKEVIRTUAL && methodName.equals("dup") && className.equals(mappedType.className) && signature.equals("()L" + jvmClassName(MappedObject.class) + ";"))
            {
               // stack: this
               super.visitTypeInsn(NEW, className);
               // stack: new, this
               super.visitInsn(DUP);
               // stack: new, new, this
               super.visitMethodInsn(INVOKESPECIAL, className, "<init>", "()V");
               // stack: new, this
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "dup", "(L" + jvmClassName(MappedObject.class) + ";L" + jvmClassName(MappedObject.class) + ";)L" + jvmClassName(MappedObject.class) + ";");
               // stack: new
               return;
            }

            if (opcode == INVOKEVIRTUAL && methodName.equals("slice") && className.equals(mappedType.className) && signature.equals("()L" + jvmClassName(MappedObject.class) + ";"))
            {
               // stack: this
               super.visitTypeInsn(NEW, className);
               // stack: new, this
               super.visitInsn(DUP);
               // stack: new, new, this
               super.visitMethodInsn(INVOKESPECIAL, className, "<init>", "()V");
               // stack: new, this
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "slice", "(L" + jvmClassName(MappedObject.class) + ";L" + jvmClassName(MappedObject.class) + ";)L" + jvmClassName(MappedObject.class) + ";");
               // stack: new
               return;
            }

            //

            if (opcode == INVOKEVIRTUAL && methodName.equals("runViewConstructor") && className.equals(mappedType.className) && signature.equals("()V"))
            {
               // stack: this
               super.visitInsn(DUP);
               // stack: this, this
               super.visitMethodInsn(INVOKEVIRTUAL, className, view_constructor_method, "()V");
               // stack: this
               return;
            }

            //

            if (opcode == INVOKEVIRTUAL && methodName.equals("copyTo") && className.equals(mappedType.className) && signature.equals("(L" + jvmClassName(MappedObject.class) + ";)V"))
            {
               // stack: target, this
               pushInt(super.mv, mappedType.sizeof);
               // stack: sizeof, target, this
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "copy", "(L" + jvmClassName(MappedObject.class) + ";L" + jvmClassName(MappedObject.class) + ";I)V");
               // stack: -
               return;
            }

            if (opcode == INVOKEVIRTUAL && methodName.equals("copyRange") && className.equals(mappedType.className) && signature.equals("(L" + jvmClassName(MappedObject.class) + ";I)V"))
            {
               // stack: instances, target, this
               pushInt(super.mv, mappedType.sizeof);
               // stack: sizeof, instances, target, this
               super.visitInsn(IMUL);
               // stack: bytes, target, this
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "copy", "(L" + jvmClassName(MappedObject.class) + ";L" + jvmClassName(MappedObject.class) + ";I)V");
               // stack: -
               return;
            }
         }

         super.visitMethodInsn(opcode, className, methodName, signature);
      }

      static void throwAccessErrorOnReadOnlyField(String className, String fieldName)
      {
         throw new IllegalAccessError("field '" + className + "." + fieldName + "' is final");
      }

      @Override
      public void visitFieldInsn(int opcode, String className, String fieldName, String typeName)
      {
         MappedSubtypeInfo mappedSubtype = className_to_subtype.get(className);
         if (mappedSubtype == null)
         {
            String mappedSetPrefix = jvmClassName(MappedSet.class);

            outer: if (fieldName.equals("view") && className.startsWith(mappedSetPrefix))
            {
               if (opcode == GETFIELD)
                  throwAccessErrorOnReadOnlyField(className, fieldName);
               if (opcode != PUTFIELD)
                  break outer;

               // stack: index, this
               if (false)
                  break outer;
               else if (className.equals(jvmClassName(MappedSet2.class)))
                  super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "put_views", "(L" + jvmClassName(MappedSet2.class) + ";I)V");
               else if (className.equals(jvmClassName(MappedSet3.class)))
                  super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "put_views", "(L" + jvmClassName(MappedSet3.class) + ";I)V");
               else if (className.equals(jvmClassName(MappedSet4.class)))
                  super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "put_views", "(L" + jvmClassName(MappedSet4.class) + ";I)V");
               else
                  break outer;
               // stack: -
               return;
            }

            // early out
            super.visitFieldInsn(opcode, className, fieldName, typeName);
            return;
         }

         if (fieldName.equals("SIZEOF"))
         {
            if (!typeName.equals("I"))
               throw new IllegalStateException();

            if (opcode == GETSTATIC)
            {
               pushInt(super.mv, mappedSubtype.sizeof);
               return;
            }
            if (opcode == PUTSTATIC)
            {
               throwAccessErrorOnReadOnlyField(className, fieldName);
            }
         }

         if (fieldName.equals("view"))
         {
            if (!typeName.equals("I"))
               throw new IllegalStateException();

            if (opcode == GETFIELD)
            {
               // stack: instance
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "get_view", "(L" + jvmClassName(MappedObject.class) + ";)I");
               return;
            }
            if (opcode == PUTFIELD)
            {
               // stack: int, instance
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "put_view", "(L" + jvmClassName(MappedObject.class) + ";I)V");
               return;
            }
         }

         if (fieldName.equals("align"))
         {
            if (!typeName.equals("I"))
               throw new IllegalStateException();

            if (opcode == GETFIELD)
            {
               // stack: instance
               super.visitInsn(POP);
               // stack: -
               pushInt(super.mv, mappedSubtype.align);
               // stack: int
               return;
            }
            if (opcode == PUTFIELD)
            {
               throwAccessErrorOnReadOnlyField(className, fieldName);
            }
         }

         if (fieldName.equals("stride"))
         {
            if (!typeName.equals("I"))
               throw new IllegalStateException();

            if (opcode == GETFIELD)
            {
               // do not change a thing
            }
            if (opcode == PUTFIELD)
            {
               throwAccessErrorOnReadOnlyField(className, fieldName);
            }
         }

         if (fieldName.equals("baseAddress") || fieldName.equals("viewAddress"))
         {
            if (!typeName.equals("J"))
               throw new IllegalStateException();

            if (opcode == GETFIELD)
            {
               // do not change a thing
            }
            if (opcode == PUTFIELD)
            {
               throwAccessErrorOnReadOnlyField(className, fieldName);
            }
         }

         Long fieldOffset = mappedSubtype.fieldToOffset.get(fieldName);
         if (fieldOffset == null)
         {
            // early out
            super.visitFieldInsn(opcode, className, fieldName, typeName);
            return;
         }

         if (typeName.equals("L" + jvmClassName(ByteBuffer.class) + ";"))
         {
            if (opcode == PUTFIELD)
            {
               throwAccessErrorOnReadOnlyField(className, fieldName);
            }
            if (opcode == GETFIELD)
            {
               Long fieldLength = mappedSubtype.fieldToLength.get(fieldName);

               super.visitFieldInsn(GETFIELD, mappedSubtype.className, "viewAddress", "J");
               super.visitLdcInsn(fieldOffset);
               super.visitInsn(LADD);
               super.visitLdcInsn(fieldLength);
               super.visitInsn(L2I);
               super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";");

               return;
            }
         }

         if (opcode == PUTFIELD)
         {
            super.visitInsn(SWAP);
            super.visitFieldInsn(GETFIELD, mappedSubtype.className, "viewAddress", "J");
            super.visitLdcInsn(fieldOffset);
            super.visitInsn(LADD);
            super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), typeName.toLowerCase() + "put", "(" + typeName + "J)V");
            return;
         }
         if (opcode == GETFIELD)
         {
            super.visitFieldInsn(GETFIELD, mappedSubtype.className, "viewAddress", "J");
            super.visitLdcInsn(fieldOffset);
            super.visitInsn(LADD);
            super.visitMethodInsn(INVOKESTATIC, jvmClassName(MappedHelper.class), typeName.toLowerCase() + "get", "(J)" + typeName);
            return;
         }

         // original field access
         super.visitFieldInsn(opcode, className, fieldName, typeName);
         return;
      }
   }

   static void pushInt(MethodVisitor mv, int value)
   {
      if (value == -1)
         mv.visitInsn(ICONST_M1);
      else if (value == 0)
         mv.visitInsn(ICONST_0);
      else if (value == 1)
         mv.visitInsn(ICONST_1);
      else if (value == 2)
         mv.visitInsn(ICONST_2);
      else if (value == 3)
         mv.visitInsn(ICONST_3);
      else if (value == 4)
         mv.visitInsn(ICONST_4);
      else if (value == 5)
         mv.visitInsn(ICONST_5);
      else if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
         mv.visitIntInsn(BIPUSH, value);
      else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
         mv.visitIntInsn(SIPUSH, value);
      else
         mv.visitLdcInsn(Integer.valueOf(value));
   }

   static String jvmClassName(Class< ? > type)
   {
      return type.getName().replace('.', '/');
   }

   static class MappedSubtypeInfo
   {
      public final String      className;

      public int               sizeof;
      public int               align;

      public Map<String, Long> fieldToOffset;
      public Map<String, Long> fieldToLength;

      public MappedSubtypeInfo(String className, int sizeof, int align)
      {
         this.className = className;

         this.sizeof = sizeof;
         this.align = align;

         this.fieldToOffset = new HashMap<String, Long>();
         this.fieldToLength = new HashMap<String, Long>();
      }
   }
}