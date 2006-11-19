/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIVertexArrayObject {
	public static final int GL_STATIC_ATI = 0x8760;
	public static final int GL_DYNAMIC_ATI = 0x8761;
	public static final int GL_PRESERVE_ATI = 0x8762;
	public static final int GL_DISCARD_ATI = 0x8763;
	public static final int GL_OBJECT_BUFFER_SIZE_ATI = 0x8764;
	public static final int GL_OBJECT_BUFFER_USAGE_ATI = 0x8765;
	public static final int GL_ARRAY_OBJECT_BUFFER_ATI = 0x8766;
	public static final int GL_ARRAY_OBJECT_OFFSET_ATI = 0x8767;

	private ATIVertexArrayObject() {
	}


	public static int glNewObjectBufferATI(int size, int usage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glNewObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglNewObjectBufferATI(size, null, 0, usage, function_pointer);
		return __result;
	}
	public static int glNewObjectBufferATI(ByteBuffer pPointer, int usage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glNewObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining()), pPointer, pPointer.position(), usage, function_pointer);
		return __result;
	}
	public static int glNewObjectBufferATI(DoubleBuffer pPointer, int usage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glNewObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining() << 3), pPointer, pPointer.position() << 3, usage, function_pointer);
		return __result;
	}
	public static int glNewObjectBufferATI(FloatBuffer pPointer, int usage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glNewObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, usage, function_pointer);
		return __result;
	}
	public static int glNewObjectBufferATI(IntBuffer pPointer, int usage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glNewObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, usage, function_pointer);
		return __result;
	}
	public static int glNewObjectBufferATI(ShortBuffer pPointer, int usage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glNewObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining() << 1), pPointer, pPointer.position() << 1, usage, function_pointer);
		return __result;
	}
	private static native int nglNewObjectBufferATI(int size, Buffer pPointer, int pPointer_position, int usage, long function_pointer);

	public static boolean glIsObjectBufferATI(int buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glIsObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsObjectBufferATI(buffer, function_pointer);
		return __result;
	}
	private static native boolean nglIsObjectBufferATI(int buffer, long function_pointer);

	public static void glUpdateObjectBufferATI(int buffer, int offset, ByteBuffer pPointer, int preserve) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glUpdateObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining()), pPointer, pPointer.position(), preserve, function_pointer);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, DoubleBuffer pPointer, int preserve) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glUpdateObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining() << 3), pPointer, pPointer.position() << 3, preserve, function_pointer);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, FloatBuffer pPointer, int preserve) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glUpdateObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining() << 2), pPointer, pPointer.position() << 2, preserve, function_pointer);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, IntBuffer pPointer, int preserve) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glUpdateObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining() << 2), pPointer, pPointer.position() << 2, preserve, function_pointer);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, ShortBuffer pPointer, int preserve) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glUpdateObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining() << 1), pPointer, pPointer.position() << 1, preserve, function_pointer);
	}
	private static native void nglUpdateObjectBufferATI(int buffer, int offset, int size, Buffer pPointer, int pPointer_position, int preserve, long function_pointer);

	public static void glGetObjectBufferATI(int buffer, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glGetObjectBufferfvATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetObjectBufferfvATI(buffer, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetObjectBufferfvATI(int buffer, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetObjectBufferATI(int buffer, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glGetObjectBufferivATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetObjectBufferivATI(buffer, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetObjectBufferivATI(int buffer, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glFreeObjectBufferATI(int buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glFreeObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFreeObjectBufferATI(buffer, function_pointer);
	}
	private static native void nglFreeObjectBufferATI(int buffer, long function_pointer);

	public static void glArrayObjectATI(int array, int size, int type, int stride, int buffer, int offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glArrayObjectATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglArrayObjectATI(array, size, type, stride, buffer, offset, function_pointer);
	}
	private static native void nglArrayObjectATI(int array, int size, int type, int stride, int buffer, int offset, long function_pointer);

	public static void glGetArrayObjectATI(int array, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glGetArrayObjectfvATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetArrayObjectfvATI(array, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetArrayObjectfvATI(int array, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetArrayObjectATI(int array, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glGetArrayObjectivATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetArrayObjectivATI(array, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetArrayObjectivATI(int array, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glVariantArrayObjectATI(int id, int type, int stride, int buffer, int offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glVariantArrayObjectATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVariantArrayObjectATI(id, type, stride, buffer, offset, function_pointer);
	}
	private static native void nglVariantArrayObjectATI(int id, int type, int stride, int buffer, int offset, long function_pointer);

	public static void glGetVariantArrayObjectATI(int id, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glGetVariantArrayObjectfvATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetVariantArrayObjectfvATI(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVariantArrayObjectfvATI(int id, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetVariantArrayObjectATI(int id, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_vertex_array_object_glGetVariantArrayObjectivATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetVariantArrayObjectivATI(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVariantArrayObjectivATI(int id, int pname, IntBuffer params, int params_position, long function_pointer);
}
