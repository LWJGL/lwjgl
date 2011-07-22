/*
 * Copyright (c) 2002-2011 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.util.mapped;

import org.lwjgl.LWJGLUtil;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.objectweb.asm.ClassWriter.*;
import static org.objectweb.asm.Opcodes.*;

/**
 * This class implements the bytecode transformation that mapped object go through.
 * Mapped object classes need to first be registered with the transformer, see {@link #register(Class)}.
 * <p/>
 * The transformer supports some debugging tools, enabled through JVM system properties:<br/>
 * org.lwjgl.util.mapped.PrintTiming=true, prints timing information for the transformation step.<br/>
 * org.lwjgl.util.mapped.PrintActivity=true, prints activity information.<br/>
 * org.lwjgl.util.mapped.PrintBytecode=true, prints the transformed bytecode.<br/>
 * org.lwjgl.util.Debug must also be set to true for the above to work.
 *
 * @author Riven
 */
public class MappedObjectTransformer {

	static final boolean PRINT_TIMING   = LWJGLUtil.DEBUG && LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintTiming");
	static final boolean PRINT_ACTIVITY = LWJGLUtil.DEBUG && LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintActivity");
	static final boolean PRINT_BYTECODE = LWJGLUtil.DEBUG && LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintBytecode");

	static final Map<String, MappedSubtypeInfo> className_to_subtype;

	static final String MAPPED_OBJECT_JVM = jvmClassName(MappedObject.class);
	static final String MAPPED_HELPER_JVM = jvmClassName(MappedHelper.class);

	static final String MAPPEDSET_PREFIX = jvmClassName(MappedSet.class);
	static final String MAPPED_SET2_JVM  = jvmClassName(MappedSet2.class);
	static final String MAPPED_SET3_JVM  = jvmClassName(MappedSet3.class);
	static final String MAPPED_SET4_JVM  = jvmClassName(MappedSet4.class);

	static final String LENGTH_METHOD_NAME      = "length$LWJGL";
	static final String VIEWADDRESS_METHOD_NAME = "getViewAddress$LWJGL";
	static final String VIEW_CONSTRUCTOR_NAME   = "constructView$LWJGL";

	static final Map<Integer, String> OPCODE_TO_NAME   = new HashMap<Integer, String>();
	static final Map<Integer, String> INSNTYPE_TO_NAME = new HashMap<Integer, String>();

	static boolean is_currently_computing_frames;

	static {
		getClassEnums(Opcodes.class, OPCODE_TO_NAME, "V1_", "ACC_", "T_", "F_", "MH_");
		getClassEnums(AbstractInsnNode.class, INSNTYPE_TO_NAME);

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
			className_to_subtype.put(MAPPED_OBJECT_JVM, new MappedSubtypeInfo(MAPPED_OBJECT_JVM, -1, -1));
		}

		final String vmName = System.getProperty("java.vm.name");
		if ( vmName != null && !vmName.contains("Server") ) {
			System.err.println("Warning: " + MappedObject.class.getSimpleName() + "s have inferiour performance on Client VMs, please consider switching to a Server VM.");
		}
	}

	/**
	 * Registers a class as a mapped object.
	 * The class must extend {@link org.lwjgl.util.mapped.MappedObject} and be annotated with {@link org.lwjgl.util.mapped.MappedField}.
	 *
	 * @param type the mapped object class.
	 */
	public static void register(Class<?> type) {
		if ( MappedObjectClassLoader.FORKED )
			return;

		final MappedType mapped = type.getAnnotation(MappedType.class);
		if ( mapped == null )
			throw new InternalError("missing " + MappedType.class.getName() + " annotation");

		if ( type.getEnclosingClass() != null && !Modifier.isStatic(type.getModifiers()) )
			throw new InternalError("only top-level or static inner classes are allowed");

		final MappedSubtypeInfo mappedType = new MappedSubtypeInfo(jvmClassName(type), mapped.sizeof(), mapped.align());

		int advancingOffset = 0;
		for ( Field field : type.getDeclaredFields() )
			advancingOffset += registerField(mapped, mappedType.className, mappedType, advancingOffset, field);

		if ( className_to_subtype.put(mappedType.className, mappedType) != null )
			throw new InternalError("duplicate mapped type: " + mappedType.className);
	}

	private static int registerField(final MappedType mapped, final String className, final MappedSubtypeInfo mappedType, int advancingOffset, final Field field) {
		if ( Modifier.isStatic(field.getModifiers()) ) // static fields are never mapped
			return 0;

		// we only support primitives and ByteBuffers
		if ( !field.getType().isPrimitive() && field.getType() != ByteBuffer.class )
			throw new InternalError("field '" + className + "." + field.getName() + "' not supported: " + field.getType());

		MappedField meta = field.getAnnotation(MappedField.class);
		if ( meta == null && !mapped.autoGenerateOffsets() )
			throw new InternalError("field '" + className + "." + field.getName() + "' missing annotation " + MappedField.class.getName() + ": " + className);

		// quick hack
		long byteOffset = meta == null ? advancingOffset : meta.byteOffset();
		long byteLength;
		if ( field.getType() == long.class || field.getType() == double.class )
			byteLength = 8;
		else if ( field.getType() == int.class || field.getType() == float.class )
			byteLength = 4;
		else if ( field.getType() == char.class || field.getType() == short.class )
			byteLength = 2;
		else if ( field.getType() == byte.class )
			byteLength = 1;
		else if ( field.getType() == ByteBuffer.class ) {
			byteLength = meta.byteLength();
			if ( byteLength < 0 )
				throw new IllegalStateException("invalid byte length for mapped ByteBuffer field: " + className + "." + field.getName() + " [length=" + byteLength + "]");
		} else
			throw new InternalError(field.getType().getName());

		if ( field.getType() != ByteBuffer.class && (advancingOffset % byteLength) != 0 )
			throw new IllegalStateException("misaligned mapped type: " + className + "." + field.getName());

		if ( PRINT_ACTIVITY )
			LWJGLUtil.log(MappedObjectTransformer.class.getSimpleName() + ": " + className + "." + field.getName() + " [type=" + field.getType().getSimpleName() + ", offset=" + byteOffset + "]");

		mappedType.fieldToOffset.put(field.getName(), byteOffset);
		mappedType.fieldToLength.put(field.getName(), byteLength);
		mappedType.fieldToType.put(field.getName(), Type.getType(field.getType()));

		return (int)byteLength;
	}

	static byte[] transformMappedAPI(final String className, byte[] bytecode) {
		final ClassWriter cw = new ClassWriter(COMPUTE_FRAMES) {

			@Override
			protected String getCommonSuperClass(String a, String b) {
				// HACK: prevent user-code static-initialization-blocks to be executed
				if ( is_currently_computing_frames && !a.startsWith("java/") || !b.startsWith("java/") )
					return "java/lang/Object";

				return super.getCommonSuperClass(a, b);
			}

		};

		ClassVisitor cv = getTransformationAdapter(className, cw);
		if ( className_to_subtype.containsKey(className) ) // Do a first pass to generate address getters
			cv = getMethodGenAdapter(className, cv);

		//cr.accept(cv, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
		new ClassReader(bytecode).accept(cv, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
		bytecode = cw.toByteArray();

		if ( PRINT_BYTECODE )
			printBytecode(bytecode);

		return bytecode;
	}

	private static ClassAdapter getMethodGenAdapter(final String className, final ClassVisitor cv) {
		return new ClassAdapter(cv) {

			@Override
			public void visitEnd() {
				generateViewAddressGetter();
				generateLengthGetter();

				final MappedSubtypeInfo mappedSubtype = className_to_subtype.get(className);

				for ( String fieldName : mappedSubtype.fieldToOffset.keySet() ) {
					final Type type = mappedSubtype.fieldToType.get(fieldName);

					if ( type.getDescriptor().length() > 1 ) {  // ByteBuffer, getter only
						generateByteBufferGetter(mappedSubtype, fieldName, type);
					} else {
						generateFieldGetter(mappedSubtype, fieldName, type);
						generateFieldSetter(mappedSubtype, fieldName, type);
					}
				}

				super.visitEnd();
			}

			private void generateViewAddressGetter() {
				MethodVisitor mv = super.visitMethod(ACC_PUBLIC | ACC_STATIC, VIEWADDRESS_METHOD_NAME, "(L" + className + ";I)J", null, null);
				mv.visitCode();
				mv.visitVarInsn(ALOAD, 0);
				mv.visitFieldInsn(GETFIELD, MAPPED_OBJECT_JVM, "baseAddress", "J");
				mv.visitVarInsn(ILOAD, 1);
				mv.visitFieldInsn(GETSTATIC, className, "SIZEOF", "I");
				mv.visitInsn(IMUL);
				mv.visitInsn(I2L);
				mv.visitInsn(LADD);
				if ( MappedObject.CHECKS ) {
					mv.visitVarInsn(LSTORE, 2);
					mv.visitVarInsn(ALOAD, 0);
					mv.visitVarInsn(LLOAD, 2);
					mv.visitMethodInsn(INVOKESTATIC, MAPPED_HELPER_JVM, "checkAddress", "(L" + MAPPED_OBJECT_JVM + ";J)V");
					mv.visitVarInsn(LLOAD, 2);
				}
				mv.visitInsn(LRETURN);
				if ( MappedObject.CHECKS )
					mv.visitMaxs(3, 4);
				else
					mv.visitMaxs(3, 2);
				mv.visitEnd();
			}

			private void generateLengthGetter() {
				MethodVisitor mv = super.visitMethod(ACC_PUBLIC | ACC_STATIC, LENGTH_METHOD_NAME, "(L" + className + ";)I", null, null);
				mv.visitCode();
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKEVIRTUAL, className, "backingByteBuffer", "()L" + jvmClassName(ByteBuffer.class) + ";");
				mv.visitMethodInsn(INVOKEVIRTUAL, jvmClassName(ByteBuffer.class), "capacity", "()I");
				mv.visitFieldInsn(GETSTATIC, className, "SIZEOF", "I");
				mv.visitInsn(IDIV);
				mv.visitInsn(IRETURN);
				mv.visitMaxs(2, 1);
				mv.visitEnd();
			}

			private void generateByteBufferGetter(final MappedSubtypeInfo mappedSubtype, final String fieldName, final Type type) {
				MethodVisitor mv = super.visitMethod(ACC_PUBLIC | ACC_STATIC, getterName(fieldName), "(L" + className + ";I)" + type.getDescriptor(), null, null);
				mv.visitCode();
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitMethodInsn(INVOKESTATIC, className, VIEWADDRESS_METHOD_NAME, "(L" + className + ";I)J");
				visitIntNode(mv, mappedSubtype.fieldToOffset.get(fieldName).intValue());
				mv.visitInsn(I2L);
				mv.visitInsn(LADD);
				visitIntNode(mv, mappedSubtype.fieldToLength.get(fieldName).intValue());
				mv.visitMethodInsn(INVOKESTATIC, MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";");
				mv.visitInsn(ARETURN);
				mv.visitMaxs(4, 2);
				mv.visitEnd();
			}

			private void generateFieldGetter(final MappedSubtypeInfo mappedSubtype, final String fieldName, final Type type) {
				MethodVisitor mv = super.visitMethod(ACC_PUBLIC | ACC_STATIC, getterName(fieldName), "(L" + className + ";I)" + type.getDescriptor(), null, null);
				mv.visitCode();
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitMethodInsn(INVOKESTATIC, className, VIEWADDRESS_METHOD_NAME, "(L" + className + ";I)J");
				visitIntNode(mv, mappedSubtype.fieldToOffset.get(fieldName).intValue());
				mv.visitInsn(I2L);
				mv.visitInsn(LADD);
				mv.visitMethodInsn(INVOKESTATIC, MAPPED_HELPER_JVM, type.getDescriptor().toLowerCase() + "get", "(J)" + type.getDescriptor());
				mv.visitInsn(type.getOpcode(IRETURN));
				mv.visitMaxs(2, 2);
				mv.visitEnd();
			}

			private void generateFieldSetter(final MappedSubtypeInfo mappedSubtype, final String fieldName, final Type type) {
				MethodVisitor mv = super.visitMethod(ACC_PUBLIC | ACC_STATIC, setterName(fieldName), "(L" + className + ";I" + type.getDescriptor() + ")V", null, null);
				mv.visitCode();
				int load = 0;
				switch ( type.getSort() ) {
					case Type.BOOLEAN:
					case Type.CHAR:
					case Type.BYTE:
					case Type.SHORT:
					case Type.INT:
						load = ILOAD;
						break;
					case Type.FLOAT:
						load = FLOAD;
						break;
					case Type.LONG:
						load = LLOAD;
						break;
					case Type.DOUBLE:
						load = DLOAD;
						break;
				}
				mv.visitVarInsn(load, 2);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitMethodInsn(INVOKESTATIC, className, VIEWADDRESS_METHOD_NAME, "(L" + className + ";I)J");
				visitIntNode(mv, mappedSubtype.fieldToOffset.get(fieldName).intValue());
				mv.visitInsn(I2L);
				mv.visitInsn(LADD);
				mv.visitMethodInsn(INVOKESTATIC, MAPPED_HELPER_JVM, type.getDescriptor().toLowerCase() + "put", "(" + type.getDescriptor() + "J)V");
				mv.visitInsn(RETURN);
				mv.visitMaxs(3, 3);
				mv.visitEnd();
			}

		};
	}

	private static ClassAdapter getTransformationAdapter(final String className, final ClassWriter cw) {
		return new ClassAdapter(cw) {

			@Override
			public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value) {
				// remove redirected fields
				final MappedSubtypeInfo mappedSubtype = className_to_subtype.get(className);
				if ( mappedSubtype != null && mappedSubtype.fieldToOffset.containsKey(name) ) {
					if ( PRINT_ACTIVITY )
						LWJGLUtil.log(MappedObjectTransformer.class.getSimpleName() + ": discarding field: " + className + "." + name + ":" + desc);
					return null;
				}

				return super.visitField(access, name, desc, signature, value);
			}

			@Override
			public MethodVisitor visitMethod(final int access, String name, final String desc, final String signature, final String[] exceptions) {
				// Move MappedSubtype constructors to another method
				if ( "<init>".equals(name) ) {
					final MappedSubtypeInfo mappedSubtype = className_to_subtype.get(className);
					if ( mappedSubtype != null ) {
						if ( !"()V".equals(desc) )
							throw new ClassFormatError(className + " can only have a default constructor, found: " + desc);

						final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
						mv.visitVarInsn(ALOAD, 0);
						mv.visitMethodInsn(INVOKESPECIAL, MAPPED_OBJECT_JVM, "<init>", "()V");
						mv.visitInsn(RETURN);
						mv.visitMaxs(0, 0);

						// put the method body in another method
						name = VIEW_CONSTRUCTOR_NAME;
					}
				}

				final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
				return new MethodNode(access, name, desc, signature, exceptions) {

					/** When true, the method has touched a mapped object and needs to be transformed. We track this
					 * so we can skip the expensive frame analysis and tree API usage. */
					boolean needsTransformation;

					@Override
					public void visitMaxs(int a, int b) {
						try {
							is_currently_computing_frames = true;
							super.visitMaxs(a, b);
						} finally {
							is_currently_computing_frames = false;
						}
					}

					@Override
					public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc) {
						if ( className_to_subtype.containsKey(owner) || owner.startsWith(MAPPEDSET_PREFIX) )
							needsTransformation = true;

						super.visitFieldInsn(opcode, owner, name, desc);
					}

					@Override
					public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc) {
						if ( className_to_subtype.containsKey(owner) )
							needsTransformation = true;

						super.visitMethodInsn(opcode, owner, name, desc);
					}

					@Override
					public void visitEnd() {
						if ( needsTransformation ) // Early-out for methods that do not touch a mapped object.
							try {
								transformMethod(analyse());
							} catch (Exception e) {
								throw new RuntimeException(e);
							}

						// Pass the instruction stream to the adapter's MethodVisitor
						accept(mv);
					}

					private Frame<BasicValue>[] analyse() throws AnalyzerException {
						final Analyzer<BasicValue> a = new Analyzer<BasicValue>(new SimpleVerifier());
						a.analyze(className, this);
						return a.getFrames();
					}

					private void transformMethod(final Frame<BasicValue>[] frames) {
						//System.err.println("\nTRANSFORMING: " + className + " - " + name + desc);

						final InsnList instructions = this.instructions;

						final Map<Integer, MappedSubtypeInfo> arrayVars = new HashMap<Integer, MappedSubtypeInfo>();

						/*
						We need this map because we insert/remove instructions from the stream and we need a way
						to match each original instruction with the corresponding frame.
						TODO: Can we keep track of everything more efficiently without a map?
						 */
						final Map<AbstractInsnNode, Frame<BasicValue>> frameMap = new HashMap<AbstractInsnNode, Frame<BasicValue>>();
						for ( int i = 0; i < frames.length; i++ )
							frameMap.put(instructions.get(i), frames[i]);

						for ( int i = 0; i < instructions.size(); i++ ) { // f is a separate cursor for frames
							final AbstractInsnNode instruction = instructions.get(i);

							//System.out.println("MAIN LOOP #" + i + " - " + getOpcodeName(instruction));

							switch ( instruction.getType() ) {
								case AbstractInsnNode.VAR_INSN:
									if ( instruction.getOpcode() == ALOAD ) {
										VarInsnNode varInsn = (VarInsnNode)instruction;
										final MappedSubtypeInfo mappedSubtype = arrayVars.get(varInsn.var);
										if ( mappedSubtype != null )
											i = transformArrayAccess(instructions, i, frameMap, varInsn, mappedSubtype, varInsn.var);
									}
									break;
								case AbstractInsnNode.FIELD_INSN:
									FieldInsnNode fieldInsn = (FieldInsnNode)instruction;

									final InsnList list = transformFieldAccess(fieldInsn);
									if ( list != null )
										i = replace(instructions, i, instruction, list);

									break;
								case AbstractInsnNode.METHOD_INSN:
									MethodInsnNode methodInsn = (MethodInsnNode)instruction;
									final MappedSubtypeInfo mappedType = className_to_subtype.get(methodInsn.owner);
									if ( mappedType != null )
										i = transformMethodCall(instructions, i, frameMap, methodInsn, mappedType, arrayVars);
									break;
							}
						}
					}
				};
			}
		};
	}

	static int transformMethodCall(final InsnList instructions, int i, final Map<AbstractInsnNode, Frame<BasicValue>> frameMap, final MethodInsnNode methodInsn, final MappedSubtypeInfo mappedType, final Map<Integer, MappedSubtypeInfo> arrayVars) {
		switch ( methodInsn.getOpcode() ) {
			case INVOKEVIRTUAL:
				if ( "asArray".equals(methodInsn.name) && methodInsn.desc.equals("()[L" + MAPPED_OBJECT_JVM + ";") ) {
					// Go forward and store the local variable index.
					// We only allow this pattern: INVOKEVIRTUAL -> CHECKCAST -> ASTORE.
					// We remove the first two and store the target MappedSubtype in the ASTORE variable
					AbstractInsnNode nextInstruction;
					checkInsnAfterIsArray(nextInstruction = methodInsn.getNext(), CHECKCAST);
					checkInsnAfterIsArray(nextInstruction = nextInstruction.getNext(), ASTORE);

					final Frame<BasicValue> frame = frameMap.get(nextInstruction);
					final String targetType = frame.getStack(frame.getStackSize() - 1).getType().getElementType().getInternalName();
					if ( !methodInsn.owner.equals(targetType) ) {
						/*
						This may happen with the current API, like so:
							MappedA foo = MappedA.malloc(...);
							MappedB[] cursor = foo.asArray();
						We have to parameterize MappedObject to avoid this.
						 */
						throw new ClassCastException("Source: " + methodInsn.owner + " - Target: " + targetType);
					}

					final VarInsnNode varInstruction = (VarInsnNode)nextInstruction;

					arrayVars.put(varInstruction.var, mappedType);

					instructions.remove(methodInsn.getNext()); // Remove CHECKCAST
					instructions.remove(methodInsn); // Remove INVOKEVIRTUAL
				}

				if ( "dup".equals(methodInsn.name) && methodInsn.desc.equals("()L" + MAPPED_OBJECT_JVM + ";") ) {
					i = replace(instructions, i, methodInsn, generateDupInstructions(methodInsn));
					break;
				}

				if ( "slice".equals(methodInsn.name) && methodInsn.desc.equals("()L" + MAPPED_OBJECT_JVM + ";") ) {
					i = replace(instructions, i, methodInsn, generateSliceInstructions(methodInsn));
					break;
				}

				if ( "runViewConstructor".equals(methodInsn.name) && "()V".equals(methodInsn.desc) ) {
					i = replace(instructions, i, methodInsn, generateRunViewConstructorInstructions(methodInsn));
					break;
				}

				if ( "copyTo".equals(methodInsn.name) && methodInsn.desc.equals("(L" + MAPPED_OBJECT_JVM + ";)V") ) {
					i = replace(instructions, i, methodInsn, generateCopyToInstructions(mappedType));
					break;
				}

				if ( "copyRange".equals(methodInsn.name) && methodInsn.desc.equals("(L" + MAPPED_OBJECT_JVM + ";I)V") ) {
					i = replace(instructions, i, methodInsn, generateCopyRangeInstructions(mappedType));
					break;
				}

				if ( "next".equals(methodInsn.name) && "()V".equals(methodInsn.desc) ) {
					i = replace(instructions, i, methodInsn, generateNextInstructions(mappedType));
					break;
				}
				break;
			case INVOKESPECIAL:
				// super() in VIEW_CONSTRUCTOR_NAME, remove
				if ( methodInsn.owner.equals(MAPPED_OBJECT_JVM) && "<init>".equals(methodInsn.name) && "()V".equals(methodInsn.desc) ) {
					instructions.remove(methodInsn.getPrevious()); // ALOAD
					instructions.remove(methodInsn); // INVOKESPECIAL

					i -= 2;
				}
				break;
			case INVOKESTATIC:
				boolean isMapDirectMethod = "map".equals(methodInsn.name) && methodInsn.desc.equals("(JI)L" + MAPPED_OBJECT_JVM + ";");
				boolean isMapBufferMethod = "map".equals(methodInsn.name) && methodInsn.desc.equals("(Ljava/nio/ByteBuffer;)L" + MAPPED_OBJECT_JVM + ";");
				boolean isMallocMethod = "malloc".equals(methodInsn.name) && methodInsn.desc.equals("(I)L" + MAPPED_OBJECT_JVM + ";");

				if ( (isMapDirectMethod || isMapBufferMethod) || isMallocMethod )
					i = replace(instructions, i, methodInsn, generateMapInstructions(mappedType, methodInsn.owner, isMapDirectMethod, isMallocMethod));
				break;
		}

		return i;
	}

	private static InsnList generateNextInstructions(final MappedSubtypeInfo mappedType) {
		final InsnList list = new InsnList();

		// stack: this
		list.add(getIntNode(mappedType.sizeof));
		// stack: sizeof, this
		list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "put_view_next", "(L" + MAPPED_OBJECT_JVM + ";I)V"));
		// stack: -

		return list;
	}

	private static InsnList generateCopyRangeInstructions(final MappedSubtypeInfo mappedType) {
		final InsnList list = new InsnList();

		// stack: instances, target, this
		list.add(getIntNode(mappedType.sizeof));
		// stack: sizeof, instances, target, this
		list.add(new InsnNode(IMUL));
		// stack: bytes, target, this
		list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "copy", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";I)V"));
		// stack: -

		return list;
	}

	private static InsnList generateCopyToInstructions(final MappedSubtypeInfo mappedType) {
		final InsnList list = new InsnList();

		// stack: target, this
		list.add(getIntNode(mappedType.sizeof));
		// stack: sizeof, target, this
		list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "copy", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";I)V"));
		// stack: -

		return list;
	}

	private static InsnList generateRunViewConstructorInstructions(final MethodInsnNode methodInsn) {
		final InsnList list = new InsnList();

		// stack: this
		list.add(new InsnNode(DUP));
		// stack: this, this
		list.add(new MethodInsnNode(INVOKEVIRTUAL, methodInsn.owner, VIEW_CONSTRUCTOR_NAME, "()V"));
		// stack: this

		return list;
	}

	private static InsnList generateSliceInstructions(final MethodInsnNode methodInsn) {
		final InsnList list = new InsnList();

		// stack: this
		list.add(new TypeInsnNode(NEW, methodInsn.owner));
		// stack: new, this
		list.add(new InsnNode(DUP));
		// stack: new, new, this
		list.add(new MethodInsnNode(INVOKESPECIAL, methodInsn.owner, "<init>", "()V"));
		// stack: new, this
		list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "slice", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";)L" + MAPPED_OBJECT_JVM + ";"));
		// stack: new

		return list;
	}

	private static InsnList generateDupInstructions(final MethodInsnNode methodInsn) {
		final InsnList list = new InsnList();

		// stack: this
		list.add(new TypeInsnNode(NEW, methodInsn.owner));
		// stack: new, this
		list.add(new InsnNode(DUP));
		// stack: new, new, this
		list.add(new MethodInsnNode(INVOKESPECIAL, methodInsn.owner, "<init>", "()V"));
		// stack: new, this
		list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "dup", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";)L" + MAPPED_OBJECT_JVM + ";"));
		// stack: new

		return list;
	}

	private static InsnList generateMapInstructions(final MappedSubtypeInfo mappedType, final String className, final boolean mapDirectMethod, final boolean mallocMethod) {
		final InsnList trg = new InsnList();

		if ( mallocMethod ) {
			// stack: count
			trg.add(getIntNode(mappedType.sizeof));
			// stack: sizeof, count
			trg.add(new InsnNode(IMUL));
			// stack: bytes
			trg.add(new MethodInsnNode(INVOKESTATIC, jvmClassName(ByteBuffer.class), "allocateDirect", "(I)L" + jvmClassName(ByteBuffer.class) + ";"));
			// stack: buffer
		} else if ( mapDirectMethod ) {
			// stack: capacity, address
			trg.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";"));
			// stack: buffer
		}

		// stack: buffer
		trg.add(new TypeInsnNode(NEW, className));
		// stack: new, buffer
		trg.add(new InsnNode(DUP));
		// stack: new, new, buffer
		trg.add(new MethodInsnNode(INVOKESPECIAL, className, "<init>", "()V"));
		// stack: new, buffer
		trg.add(new InsnNode(DUP_X1));
		// stack: new, buffer, new
		trg.add(new InsnNode(SWAP));
		// stack: buffer, new, new
		trg.add(getIntNode(mappedType.align));
		// stack: int, buffer, new, new
		trg.add(getIntNode(mappedType.sizeof));
		// stack: int, int, buffer, new, new
		trg.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "setup", "(L" + MAPPED_OBJECT_JVM + ";Ljava/nio/ByteBuffer;II)V"));
		// stack: new

		return trg;
	}

	static InsnList transformFieldAccess(final FieldInsnNode fieldInsn) {
		final MappedSubtypeInfo mappedSubtype;
		mappedSubtype = className_to_subtype.get(fieldInsn.owner);
		if ( mappedSubtype == null ) { // early out
			// MappedSet.view
			outer:
			if ( "view".equals(fieldInsn.name) && fieldInsn.owner.startsWith(MAPPEDSET_PREFIX) )
				return generateSetViewInstructions(fieldInsn);

			return null; // early out
		}

		if ( "SIZEOF".equals(fieldInsn.name) )
			return generateSIZEOFInstructions(fieldInsn, mappedSubtype);

		if ( "view".equals(fieldInsn.name) )
			return generateViewInstructions(fieldInsn, mappedSubtype);

		if ( "align".equals(fieldInsn.name) || "sizeof".equals(fieldInsn.name) )
			return generateAlignSizeofInstructions(fieldInsn, mappedSubtype);

		if ( "baseAddress".equals(fieldInsn.name) || "viewAddress".equals(fieldInsn.name) ) {
			return generateAddressInstructions(fieldInsn);
		}

		final Long fieldOffset = mappedSubtype.fieldToOffset.get(fieldInsn.name);
		if ( fieldOffset == null ) // early out
			return null;

		// now we're going to transform ByteBuffer-typed field access
		if ( fieldInsn.desc.equals("L" + jvmClassName(ByteBuffer.class) + ";") )
			return generateByteBufferInstructions(fieldInsn, mappedSubtype, fieldOffset);

		// we're now going to transform the field access
		return generateFieldInstructions(fieldInsn, fieldOffset);
	}

	private static InsnList generateSetViewInstructions(final FieldInsnNode fieldInsn) {
		if ( fieldInsn.getOpcode() == GETFIELD )
			throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
		if ( fieldInsn.getOpcode() != PUTFIELD )
			throw new InternalError();

		final InsnList list = new InsnList();

		// stack: index, this
		if ( MAPPED_SET2_JVM.equals(fieldInsn.owner) )
			list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET2_JVM + ";I)V"));
		else if ( MAPPED_SET3_JVM.equals(fieldInsn.owner) )
			list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET3_JVM + ";I)V"));
		else if ( MAPPED_SET4_JVM.equals(fieldInsn.owner) )
			list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET4_JVM + ";I)V"));
		else
			throw new InternalError();
		// stack: -

		return list;
	}

	private static InsnList generateSIZEOFInstructions(final FieldInsnNode fieldInsn, final MappedSubtypeInfo mappedSubtype) {
		if ( !"I".equals(fieldInsn.desc) )
			throw new InternalError();

		final InsnList list = new InsnList();

		if ( fieldInsn.getOpcode() == GETSTATIC ) {
			list.add(getIntNode(mappedSubtype.sizeof));
			return list;
		}

		if ( fieldInsn.getOpcode() == PUTSTATIC )
			throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);

		throw new InternalError();
	}

	private static InsnList generateViewInstructions(final FieldInsnNode fieldInsn, final MappedSubtypeInfo mappedSubtype) {
		if ( !"I".equals(fieldInsn.desc) )
			throw new InternalError();

		final InsnList list = new InsnList();

		if ( fieldInsn.getOpcode() == GETFIELD ) {
			if ( mappedSubtype.sizeof_shift != 0 ) {
				// stack: instance
				list.add(getIntNode(mappedSubtype.sizeof_shift));
				// stack: sizeof, instance
				list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "get_view_shift", "(L" + MAPPED_OBJECT_JVM + ";I)I"));
				// stack: view
			} else {
				// stack: instance
				list.add(getIntNode(mappedSubtype.sizeof));
				// stack: sizeof, instance
				list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "get_view", "(L" + MAPPED_OBJECT_JVM + ";I)I"));
				// stack: view
			}
			return list;
		}

		if ( fieldInsn.getOpcode() == PUTFIELD ) {
			if ( mappedSubtype.sizeof_shift != 0 ) {
				// stack: view, instance
				list.add(getIntNode(mappedSubtype.sizeof_shift));
				// stack: sizeof, view, instance
				list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "put_view_shift", "(L" + MAPPED_OBJECT_JVM + ";II)V"));
				// stack: -
			} else {
				// stack: view, instance
				list.add(getIntNode(mappedSubtype.sizeof));
				// stack: sizeof, view, instance
				list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "put_view", "(L" + MAPPED_OBJECT_JVM + ";II)V"));
				// stack: -
			}
			return list;
		}

		throw new InternalError();
	}

	private static InsnList generateAlignSizeofInstructions(final FieldInsnNode fieldInsn, final MappedSubtypeInfo mappedSubtype) {
		if ( !"I".equals(fieldInsn.desc) )
			throw new InternalError();

		if ( fieldInsn.getOpcode() == GETFIELD ) {
			final InsnList list = new InsnList();

			// stack: instance
			list.add(new InsnNode(POP));
			// stack: -
			if ( "sizeof".equals(fieldInsn.name) )
				list.add(getIntNode(mappedSubtype.sizeof));
			else if ( "align".equals(fieldInsn.name) )
				list.add(getIntNode(mappedSubtype.align));
			// stack: int
			return list;
		}

		if ( fieldInsn.getOpcode() == PUTFIELD )
			throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
		throw new InternalError();
	}

	private static InsnList generateAddressInstructions(final FieldInsnNode fieldInsn) {
		if ( !"J".equals(fieldInsn.desc) )
			throw new IllegalStateException();

		if ( fieldInsn.getOpcode() == GETFIELD ) // do not change a thing
			return null;

		if ( fieldInsn.getOpcode() == PUTFIELD )
			throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);

		throw new InternalError();
	}

	private static InsnList generateByteBufferInstructions(final FieldInsnNode fieldInsn, final MappedSubtypeInfo mappedSubtype, final Long fieldOffset) {
		if ( fieldInsn.getOpcode() == PUTFIELD )
			throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);

		if ( fieldInsn.getOpcode() == GETFIELD ) {
			final Long fieldLength = mappedSubtype.fieldToLength.get(fieldInsn.name);

			final InsnList list = new InsnList();

			// stack: ref
			list.add(new FieldInsnNode(GETFIELD, mappedSubtype.className, "viewAddress", "J"));
			// stack: long
			list.add(new LdcInsnNode(fieldOffset));
			// stack: long, long
			list.add(new InsnNode(LADD));
			// stack: long
			list.add(new LdcInsnNode(fieldLength));
			// stack: long, long
			list.add(new InsnNode(L2I));
			// stack: int, long
			list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";"));
			// stack: buffer

			return list;
		}

		throw new InternalError();
	}

	private static InsnList generateFieldInstructions(final FieldInsnNode fieldInsn, final Long fieldOffset) {
		final InsnList list = new InsnList();

		if ( fieldInsn.getOpcode() == PUTFIELD ) {
			// stack: value, ref
			list.add(getIntNode(fieldOffset.intValue()));
			// stack: fieldOffset, value, ref
			list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, fieldInsn.desc.toLowerCase() + "put", "(L" + MAPPED_OBJECT_JVM + ";" + fieldInsn.desc + "I)V"));
			// stack -
			return list;
		}

		if ( fieldInsn.getOpcode() == GETFIELD ) {
			// stack: ref
			list.add(getIntNode(fieldOffset.intValue()));
			// stack: fieldOffset, ref
			list.add(new MethodInsnNode(INVOKESTATIC, MAPPED_HELPER_JVM, fieldInsn.desc.toLowerCase() + "get", "(L" + MAPPED_OBJECT_JVM + ";I)" + fieldInsn.desc));
			// stack: -
			return list;
		}

		throw new InternalError();
	}

	static int transformArrayAccess(final InsnList instructions, int i, final Map<AbstractInsnNode, Frame<BasicValue>> frameMap, final VarInsnNode loadInsn, final MappedSubtypeInfo mappedSubtype, final int var) {
		// We need to go forward in time to find how we use the array var
		final int loadStackSize = frameMap.get(loadInsn).getStackSize() + 1;

		AbstractInsnNode nextInsn = loadInsn;

		while ( true ) {
			nextInsn = nextInsn.getNext();
			if ( nextInsn == null )
				throw new InternalError();

			Frame<BasicValue> frame = frameMap.get(nextInsn);
			if ( frame == null )
				continue;

			int stackSize = frame.getStackSize();

			if ( stackSize == loadStackSize + 1 && nextInsn.getOpcode() == AALOAD ) {
				final AbstractInsnNode aaLoadInsn = nextInsn;

				while ( true ) {
					nextInsn = nextInsn.getNext();
					if ( nextInsn == null )
						break;

					frame = frameMap.get(nextInsn);
					if ( frame == null )
						continue;
					stackSize = frame.getStackSize();

					if ( stackSize == loadStackSize + 1 && nextInsn.getOpcode() == PUTFIELD ) {
						final FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn;

						// stack: value, view, ref
						instructions.insert(nextInsn, new MethodInsnNode(INVOKESTATIC, mappedSubtype.className, setterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I" + fieldInsn.desc + ")V"));
						// stack: -
						instructions.remove(nextInsn);

						break;
					} else if ( stackSize == loadStackSize && nextInsn.getOpcode() == GETFIELD ) {
						final FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn;

						// stack: view, ref
						instructions.insert(nextInsn, new MethodInsnNode(INVOKESTATIC, mappedSubtype.className, getterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I)" + fieldInsn.desc));
						// stack: value
						instructions.remove(nextInsn);

						break;
					} else if ( stackSize == loadStackSize && nextInsn.getOpcode() == DUP && nextInsn.getNext().getOpcode() == GETFIELD ) {
						// May happen with operator+assignment (e.g. cursor[i].value += 10)
						final FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn.getNext();

						final MethodInsnNode getter = new MethodInsnNode(INVOKESTATIC, mappedSubtype.className, getterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I)" + fieldInsn.desc);

						// stack: view, ref
						instructions.insert(nextInsn, new InsnNode(DUP2));
						// stack: view, ref, view, ref
						instructions.insert(nextInsn.getNext(), getter);
						// stack: value, view, ref

						instructions.remove(nextInsn);
						instructions.remove(fieldInsn);

						nextInsn = getter;
						continue;
					} else if ( stackSize < loadStackSize )
						throw new ClassFormatError("Invalid .asArray() usage detected: " + getOpcodeName(nextInsn));
				}

				instructions.remove(aaLoadInsn);

				return i;
			} else if ( stackSize == loadStackSize && nextInsn.getOpcode() == ARRAYLENGTH ) {
				if ( LWJGLUtil.DEBUG && loadInsn.getNext() != nextInsn )
					throw new InternalError();

				instructions.remove(nextInsn);
				loadInsn.var = var;
				instructions.insert(loadInsn, new MethodInsnNode(INVOKESTATIC, mappedSubtype.className, LENGTH_METHOD_NAME, "(L" + mappedSubtype.className + ";)I"));

				return i + 1;
			} else if ( stackSize < loadStackSize ) // Consumed by something other than AALOAD or ARRAYLENGTH
				throw new ClassFormatError("Invalid " + mappedSubtype.className + " view array usage detected: " + getOpcodeName(nextInsn));
		}
	}

	private static class MappedSubtypeInfo {

		public final String className;

		public int sizeof;
		public int sizeof_shift;
		public int align;

		public Map<String, Long> fieldToOffset;
		public Map<String, Long> fieldToLength;
		public Map<String, Type> fieldToType;

		MappedSubtypeInfo(String className, int sizeof, int align) {
			this.className = className;

			this.sizeof = sizeof;
			if ( ((sizeof - 1) & sizeof) == 0 )
				this.sizeof_shift = getPoT(sizeof);
			this.align = align;

			this.fieldToOffset = new HashMap<String, Long>();
			this.fieldToLength = new HashMap<String, Long>();
			this.fieldToType = new HashMap<String, Type>();
		}

		private static int getPoT(int value) {
			int pot = -1;
			while ( value > 0 ) {
				pot++;
				value >>= 1;
			}
			return pot;
		}

	}

	// -------------------------------------------------------
	// -------------------[ MACROS & UTILS ]------------------
	// -------------------------------------------------------

	private static void getClassEnums(final Class clazz, final Map<Integer, String> map, final String... prefixFilters) {
		try {
			OUTER:
			for ( Field field : clazz.getFields() ) {
				if ( !Modifier.isStatic(field.getModifiers()) || field.getType() != int.class )
					continue;

				for ( String filter : prefixFilters ) {
					if ( field.getName().startsWith(filter) )
						continue OUTER;
				}

				if ( map.put((Integer)field.get(null), field.getName()) != null )
					throw new IllegalStateException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String getOpcodeName(final AbstractInsnNode insn) {
		final String op = OPCODE_TO_NAME.get(insn.getOpcode());
		return INSNTYPE_TO_NAME.get(insn.getType()) + ": " + insn.getOpcode() + (op == null ? "" : " [" + OPCODE_TO_NAME.get(insn.getOpcode()) + "]");
	}

	static String jvmClassName(Class<?> type) {
		return type.getName().replace('.', '/');
	}

	static String getterName(final String fieldName) {
		return "get$" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "$LWJGL";
	}

	static String setterName(final String fieldName) {
		return "set$" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "$LWJGL";
	}

	private static void checkInsnAfterIsArray(final AbstractInsnNode instruction, final int opcode) {
		if ( instruction == null )
			throw new ClassFormatError("Unexpected end of instructions after .asArray() method.");

		if ( instruction.getOpcode() != opcode )
			throw new ClassFormatError("The result of .asArray() must be stored to a local variable. Found: " + getOpcodeName(instruction));
	}

	static AbstractInsnNode getIntNode(final int value) {
		if ( value <= 5 && -1 <= value )
			return new InsnNode(ICONST_M1 + value + 1);

		if ( value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE )
			return new IntInsnNode(BIPUSH, value);

		if ( value >= Short.MIN_VALUE && value <= Short.MAX_VALUE )
			return new IntInsnNode(SIPUSH, value);

		return new LdcInsnNode(value);
	}

	static void visitIntNode(final MethodVisitor mv, final int value) {
		if ( value <= 5 && -1 <= value )
			mv.visitInsn(ICONST_M1 + value + 1);
		else if ( value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE )
			mv.visitIntInsn(BIPUSH, value);
		else if ( value >= Short.MIN_VALUE && value <= Short.MAX_VALUE )
			mv.visitIntInsn(SIPUSH, value);
		else
			mv.visitLdcInsn(value);
	}

	/** Replace an instruction with a list of instructions. */
	static int replace(final InsnList instructions, final int i, final AbstractInsnNode location, final InsnList list) {
		final int size = list.size();

		instructions.insert(location, list);
		instructions.remove(location);

		return i + (size - 1);
	}

	private static void throwAccessErrorOnReadOnlyField(String className, String fieldName) {
		throw new IllegalAccessError("The " + className + "." + fieldName + " field is final.");
	}

	private static void printBytecode(byte[] bytecode) {
		StringWriter sw = new StringWriter();
		ClassVisitor tracer = new TraceClassVisitor(new ClassWriter(0), new PrintWriter(sw));
		new ClassReader(bytecode).accept(tracer, 0);
		String dump = sw.toString();

		LWJGLUtil.log(dump);
	}

}