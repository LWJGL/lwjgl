/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVPrimitiveRestart {
	public static final int GL_PRIMITIVE_RESTART_INDEX_NV = 0x8559;
	public static final int GL_PRIMITIVE_RESTART_NV = 0x8558;

	private NVPrimitiveRestart() {
	}


	public static void glPrimitiveRestartIndexNV(int index) {
		long function_pointer = GLContext.getCapabilities().NV_primitive_restart_glPrimitiveRestartIndexNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPrimitiveRestartIndexNV(index, function_pointer);
	}
	private static native void nglPrimitiveRestartIndexNV(int index, long function_pointer);

	public static void glPrimitiveRestartNV() {
		long function_pointer = GLContext.getCapabilities().NV_primitive_restart_glPrimitiveRestartNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPrimitiveRestartNV(function_pointer);
	}
	private static native void nglPrimitiveRestartNV(long function_pointer);
}
