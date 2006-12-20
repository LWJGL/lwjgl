/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVVertexArrayRange {
	public static final int GL_VERTEX_ARRAY_RANGE_NV = 0x851d;
	public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_NV = 0x851e;
	public static final int GL_VERTEX_ARRAY_RANGE_VALID_NV = 0x851f;
	public static final int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV = 0x8520;
	public static final int GL_VERTEX_ARRAY_RANGE_POINTER_NV = 0x8521;

	private NVVertexArrayRange() {
	}


	public static void glVertexArrayRangeNV(ByteBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining()), pPointer, pPointer.position(), function_pointer);
	}
	public static void glVertexArrayRangeNV(DoubleBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 3), pPointer, pPointer.position() << 3, function_pointer);
	}
	public static void glVertexArrayRangeNV(FloatBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, function_pointer);
	}
	public static void glVertexArrayRangeNV(IntBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, function_pointer);
	}
	public static void glVertexArrayRangeNV(ShortBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 1), pPointer, pPointer.position() << 1, function_pointer);
	}
	private static native void nglVertexArrayRangeNV(int size, Buffer pPointer, int pPointer_position, long function_pointer);

	public static void glFlushVertexArrayRangeNV() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glFlushVertexArrayRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFlushVertexArrayRangeNV(function_pointer);
	}
	private static native void nglFlushVertexArrayRangeNV(long function_pointer);

	public static java.nio.ByteBuffer glAllocateMemoryNV(int size, float readFrequency, float writeFrequency, float priority) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glAllocateMemoryNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		java.nio.ByteBuffer __result = nglAllocateMemoryNV(size, readFrequency, writeFrequency, priority, size, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglAllocateMemoryNV(int size, float readFrequency, float writeFrequency, float priority, long result_size, long function_pointer);

	public static void glFreeMemoryNV(ByteBuffer pointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_vertex_array_range_glFreeMemoryNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pointer);
		nglFreeMemoryNV(pointer, pointer.position(), function_pointer);
	}
	private static native void nglFreeMemoryNV(Buffer pointer, int pointer_position, long function_pointer);
}
