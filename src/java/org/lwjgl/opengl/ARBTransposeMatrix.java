/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBTransposeMatrix {
	public static final int GL_TRANSPOSE_COLOR_MATRIX_ARB = 0x84e6;
	public static final int GL_TRANSPOSE_TEXTURE_MATRIX_ARB = 0x84e5;
	public static final int GL_TRANSPOSE_PROJECTION_MATRIX_ARB = 0x84e4;
	public static final int GL_TRANSPOSE_MODELVIEW_MATRIX_ARB = 0x84e3;

	private ARBTransposeMatrix() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glMultTransposeMatrixARB(FloatBuffer pfMtx) {
		BufferChecks.checkBuffer(pfMtx, 16);
		nglMultTransposeMatrixfARB(pfMtx, pfMtx.position());
	}
	private static native void nglMultTransposeMatrixfARB(FloatBuffer pfMtx, int pfMtx_position);

	public static void glLoadTransposeMatrixARB(FloatBuffer pfMtx) {
		BufferChecks.checkBuffer(pfMtx, 16);
		nglLoadTransposeMatrixfARB(pfMtx, pfMtx.position());
	}
	private static native void nglLoadTransposeMatrixfARB(FloatBuffer pfMtx, int pfMtx_position);
}
