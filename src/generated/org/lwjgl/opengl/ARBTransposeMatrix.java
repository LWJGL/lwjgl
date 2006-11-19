/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBTransposeMatrix {
	public static final int GL_TRANSPOSE_MODELVIEW_MATRIX_ARB = 0x84e3;
	public static final int GL_TRANSPOSE_PROJECTION_MATRIX_ARB = 0x84e4;
	public static final int GL_TRANSPOSE_TEXTURE_MATRIX_ARB = 0x84e5;
	public static final int GL_TRANSPOSE_COLOR_MATRIX_ARB = 0x84e6;

	private ARBTransposeMatrix() {
	}


	public static void glLoadTransposeMatrixARB(FloatBuffer pfMtx) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_transpose_matrix_glLoadTransposeMatrixfARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pfMtx, 16);
		nglLoadTransposeMatrixfARB(pfMtx, pfMtx.position(), function_pointer);
	}
	private static native void nglLoadTransposeMatrixfARB(FloatBuffer pfMtx, int pfMtx_position, long function_pointer);

	public static void glMultTransposeMatrixARB(FloatBuffer pfMtx) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_transpose_matrix_glMultTransposeMatrixfARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pfMtx, 16);
		nglMultTransposeMatrixfARB(pfMtx, pfMtx.position(), function_pointer);
	}
	private static native void nglMultTransposeMatrixfARB(FloatBuffer pfMtx, int pfMtx_position, long function_pointer);
}
