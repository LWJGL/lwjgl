/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBMatrixPalette {
	public static final int GL_MATRIX_PALETTE_ARB = 0x8840;
	public static final int GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB = 0x8841;
	public static final int GL_MAX_PALETTE_MATRICES_ARB = 0x8842;
	public static final int GL_CURRENT_PALETTE_MATRIX_ARB = 0x8843;
	public static final int GL_MATRIX_INDEX_ARRAY_ARB = 0x8844;
	public static final int GL_CURRENT_MATRIX_INDEX_ARB = 0x8845;
	public static final int GL_MATRIX_INDEX_ARRAY_SIZE_ARB = 0x8846;
	public static final int GL_MATRIX_INDEX_ARRAY_TYPE_ARB = 0x8847;
	public static final int GL_MATRIX_INDEX_ARRAY_STRIDE_ARB = 0x8848;
	public static final int GL_MATRIX_INDEX_ARRAY_POINTER_ARB = 0x8849;

	private ARBMatrixPalette() {
	}


	public static void glCurrentPaletteMatrixARB(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glCurrentPaletteMatrixARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCurrentPaletteMatrixARB(index, function_pointer);
	}
	private static native void nglCurrentPaletteMatrixARB(int index, long function_pointer);

	public static void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glMatrixIndexPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_BYTE, stride, pPointer, pPointer.position(), function_pointer);
	}
	public static void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glMatrixIndexPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_INT, stride, pPointer, pPointer.position() << 2, function_pointer);
	}
	public static void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glMatrixIndexPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_SHORT, stride, pPointer, pPointer.position() << 1, function_pointer);
	}
	private static native void nglMatrixIndexPointerARB(int size, int type, int stride, Buffer pPointer, int pPointer_position, long function_pointer);
	public static void glMatrixIndexPointerARB(int size, int type, int stride, long pPointer_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glMatrixIndexPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglMatrixIndexPointerARBBO(size, type, stride, pPointer_buffer_offset, function_pointer);
	}
	private static native void nglMatrixIndexPointerARBBO(int size, int type, int stride, long pPointer_buffer_offset, long function_pointer);

	public static void glMatrixIndexuARB(ByteBuffer pIndices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glMatrixIndexubvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pIndices);
		nglMatrixIndexubvARB((pIndices.remaining()), pIndices, pIndices.position(), function_pointer);
	}
	private static native void nglMatrixIndexubvARB(int size, ByteBuffer pIndices, int pIndices_position, long function_pointer);

	public static void glMatrixIndexuARB(ShortBuffer pIndices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glMatrixIndexusvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pIndices);
		nglMatrixIndexusvARB((pIndices.remaining()), pIndices, pIndices.position(), function_pointer);
	}
	private static native void nglMatrixIndexusvARB(int size, ShortBuffer pIndices, int pIndices_position, long function_pointer);

	public static void glMatrixIndexuARB(IntBuffer pIndices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_matrix_palette_glMatrixIndexuivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pIndices);
		nglMatrixIndexuivARB((pIndices.remaining()), pIndices, pIndices.position(), function_pointer);
	}
	private static native void nglMatrixIndexuivARB(int size, IntBuffer pIndices, int pIndices_position, long function_pointer);
}
