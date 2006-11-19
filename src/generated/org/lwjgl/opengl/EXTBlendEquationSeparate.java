/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTBlendEquationSeparate {
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_BLEND_EQUATION_RGB_EXT = 0x8009;
	public static final int GL_BLEND_EQUATION_ALPHA_EXT = 0x883d;

	private EXTBlendEquationSeparate() {
	}


	public static void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_blend_equation_separate_glBlendEquationSeparateEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquationSeparateEXT(modeRGB, modeAlpha, function_pointer);
	}
	private static native void nglBlendEquationSeparateEXT(int modeRGB, int modeAlpha, long function_pointer);
}
