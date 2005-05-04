/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class ARBColorBufferFloat {
	public static final int GL_FIXED_ONLY_ARB = 0x891d;
	public static final int GL_CLAMP_READ_COLOR_ARB = 0x891c;
	public static final int GL_CLAMP_FRAGMENT_COLOR_ARB = 0x891b;
	public static final int GL_CLAMP_VERTEX_COLOR_ARB = 0x891a;
	public static final int GL_RGBA_FLOAT_MODE_ARB = 0x8820;

	private ARBColorBufferFloat() {
	}


	public static void glClampColorARB(int target, int clamp) {
		long function_pointer = GLContext.getCapabilities().ARB_color_buffer_float_glClampColorARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglClampColorARB(target, clamp, function_pointer);
	}
	private static native void nglClampColorARB(int target, int clamp, long function_pointer);
}
