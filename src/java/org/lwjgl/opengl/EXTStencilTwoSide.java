/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTStencilTwoSide {
	public static final int GL_ACTIVE_STENCIL_FACE_EXT = 0x8911;
	public static final int GL_STENCIL_TEST_TWO_SIDE_EXT = 0x8910;

	private EXTStencilTwoSide() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glActiveStencilFaceEXT(int face) {
		long function_pointer = GLContext.getCapabilities().EXT_stencil_two_side_glActiveStencilFaceEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglActiveStencilFaceEXT(face, function_pointer);
	}
	private static native void nglActiveStencilFaceEXT(int face, long function_pointer);
}
