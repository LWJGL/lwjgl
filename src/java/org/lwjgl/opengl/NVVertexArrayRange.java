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
		nglFreeMemoryNV(pointer, pointer.position());
	}
	private static native void nglFreeMemoryNV(Buffer pointer, int pointer_position);

	public static native java.nio.ByteBuffer glAllocateMemoryNV(int size, float readFrequency, float writeFrequency, float priority, int result_size);

	public static native void glFlushVertexArrayRangeNV();

	public static void glVertexArrayRangeNV(ShortBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 1), pPointer, pPointer.position() << 1);
	}
	public static void glVertexArrayRangeNV(IntBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 2), pPointer, pPointer.position() << 2);
	}
	public static void glVertexArrayRangeNV(ByteBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining()), pPointer, pPointer.position());
	}
	public static void glVertexArrayRangeNV(FloatBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		nglVertexArrayRangeNV((pPointer.remaining() << 2), pPointer, pPointer.position() << 2);
	}
	private static native void nglVertexArrayRangeNV(int size, Buffer pPointer, int pPointer_position);
}
