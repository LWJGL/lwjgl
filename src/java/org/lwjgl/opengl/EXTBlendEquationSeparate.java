/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class EXTBlendEquationSeparate {
	public static final int GL_BLEND_EQUATION_ALPHA_EXT = 0x883d;
	public static final int GL_BLEND_EQUATION_RGB_EXT = 0x8009;

	private EXTBlendEquationSeparate() {
	}


	public static void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha) {
		long function_pointer = GLContext.getCapabilities().EXT_blend_equation_separate_glBlendEquationSeparateEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquationSeparateEXT(modeRGB, modeAlpha, function_pointer);
	}
	private static native void nglBlendEquationSeparateEXT(int modeRGB, int modeAlpha, long function_pointer);
}
