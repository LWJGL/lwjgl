/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVVertexArrayRange {
	public static final int GL_VERTEX_ARRAY_RANGE_POINTER_NV = 0x8521;
	public static final int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV = 0x8520;
	public static final int GL_VERTEX_ARRAY_RANGE_VALID_NV = 0x851f;
	public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_NV = 0x851e;
	public static final int GL_VERTEX_ARRAY_RANGE_NV = 0x851d;

	private NVVertexArrayRange() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glFreeMemoryNV(ByteBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		long function_pointer = GLContext.getCapabilities().NV_vertex_array_range_glFreeMemoryNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFreeMemoryNV(pointer, pointer.position(), function_pointer);
	}
	private static native void nglFreeMemoryNV(Buffer pointer, int pointer_position, long function_pointer);

	public static java.nio.ByteBuffer glAllocateMemoryNV(int size, float readFrequency, float writeFrequency, float priority) {
		long function_pointer = GLContext.getCapabilities().NV_vertex_array_range_glAllocateMemoryNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		java.nio.ByteBuffer __result = nglAllocateMemoryNV(size, readFrequency, writeFrequency, priority, size, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglAllocateMemoryNV(int size, float readFrequency, float writeFrequency, float priority, int result_size, long function_pointer);

	public static void glFlushVertexArrayRangeNV() {
		long function_pointer = GLContext.getCapabilities().NV_vertex_array_range_glFlushVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFlushVertexArrayRangeNV(function_pointer);
	}
	private static native void nglFlushVertexArrayRangeNV(long function_pointer);

	public static void glVertexArrayRangeNV(IntBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		long function_pointer = GLContext.getCapabilities().NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, function_pointer);
	}
	public static void glVertexArrayRangeNV(ByteBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		long function_pointer = GLContext.getCapabilities().NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexArrayRangeNV((pPointer.remaining()), pPointer, pPointer.position(), function_pointer);
	}
	public static void glVertexArrayRangeNV(ShortBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		long function_pointer = GLContext.getCapabilities().NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 1), pPointer, pPointer.position() << 1, function_pointer);
	}
	public static void glVertexArrayRangeNV(FloatBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		long function_pointer = GLContext.getCapabilities().NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, function_pointer);
	}
	private static native void nglVertexArrayRangeNV(int size, Buffer pPointer, int pPointer_position, long function_pointer);
}
