/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBMatrixPalette {
	public static final int GL_MATRIX_INDEX_ARRAY_POINTER_ARB = 0x8849;
	public static final int GL_MATRIX_INDEX_ARRAY_STRIDE_ARB = 0x8848;
	public static final int GL_MATRIX_INDEX_ARRAY_TYPE_ARB = 0x8847;
	public static final int GL_MATRIX_INDEX_ARRAY_SIZE_ARB = 0x8846;
	public static final int GL_CURRENT_MATRIX_INDEX_ARB = 0x8845;
	public static final int GL_MATRIX_INDEX_ARRAY_ARB = 0x8844;
	public static final int GL_CURRENT_PALETTE_MATRIX_ARB = 0x8843;
	public static final int GL_MAX_PALETTE_MATRICES_ARB = 0x8842;
	public static final int GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB = 0x8841;
	public static final int GL_MATRIX_PALETTE_ARB = 0x8840;

	private ARBMatrixPalette() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glMatrixIndexuARB(IntBuffer pIndices) {
		BufferChecks.checkDirect(pIndices);
		nglMatrixIndexuivARB((pIndices.remaining()), pIndices, pIndices.position());
	}
	private static native void nglMatrixIndexuivARB(int size, IntBuffer pIndices, int pIndices_position);

	public static void glMatrixIndexuARB(ShortBuffer pIndices) {
		BufferChecks.checkDirect(pIndices);
		nglMatrixIndexusvARB((pIndices.remaining()), pIndices, pIndices.position());
	}
	private static native void nglMatrixIndexusvARB(int size, ShortBuffer pIndices, int pIndices_position);

	public static void glMatrixIndexuARB(ByteBuffer pIndices) {
		BufferChecks.checkDirect(pIndices);
		nglMatrixIndexubvARB((pIndices.remaining()), pIndices, pIndices.position());
	}
	private static native void nglMatrixIndexubvARB(int size, ByteBuffer pIndices, int pIndices_position);

	public static void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_BYTE, stride, pPointer, pPointer.position());
	}
	public static void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_INT, stride, pPointer, pPointer.position() << 2);
	}
	public static void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_SHORT, stride, pPointer, pPointer.position() << 1);
	}
	private static native void nglMatrixIndexPointerARB(int size, int type, int stride, Buffer pPointer, int pPointer_position);
	public static void glMatrixIndexPointerARB(int size, int type, int stride, int pPointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglMatrixIndexPointerARBBO(size, type, stride, pPointer_buffer_offset);
	}
	private static native void nglMatrixIndexPointerARBBO(int size, int type, int stride, int pPointer_buffer_offset);

	public static native void glCurrentPaletteMatrixARB(int index);
}
