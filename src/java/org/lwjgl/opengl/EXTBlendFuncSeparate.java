/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class EXTBlendFuncSeparate {
	public static final int GL_BLEND_SRC_ALPHA_EXT = 0x80cb;
	public static final int GL_BLEND_DST_ALPHA_EXT = 0x80ca;
	public static final int GL_BLEND_SRC_RGB_EXT = 0x80c9;
	public static final int GL_BLEND_DST_RGB_EXT = 0x80c8;

	private EXTBlendFuncSeparate() {
	}


	public static void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
		long function_pointer = GLContext.getCapabilities().EXT_blend_func_separate_glBlendFuncSeparateEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendFuncSeparateEXT(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha, function_pointer);
	}
	private static native void nglBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha, long function_pointer);
}
