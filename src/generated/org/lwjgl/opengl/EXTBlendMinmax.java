/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTBlendMinmax {
	/**
	 *Accepted by the &lt;mode&gt; parameter of BlendEquationEXT. 
	 */
	public static final int GL_FUNC_ADD_EXT = 0x8006;
	public static final int GL_MIN_EXT = 0x8007;
	public static final int GL_MAX_EXT = 0x8008;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev.
	 */
	public static final int GL_BLEND_EQUATION_EXT = 0x8009;

	private EXTBlendMinmax() {
	}


	public static void glBlendEquationEXT(int mode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_blend_minmax_glBlendEquationEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquationEXT(mode, function_pointer);
	}
	private static native void nglBlendEquationEXT(int mode, long function_pointer);
}
