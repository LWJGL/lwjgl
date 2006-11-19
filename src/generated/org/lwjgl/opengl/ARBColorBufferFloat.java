/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBColorBufferFloat {
	/**
	 * Accepted by the &lt;pname&gt; parameters of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_RGBA_FLOAT_MODE_ARB = 0x8820;
	/**
	 * Accepted by the &lt;target&gt; parameter of ClampColorARB and the &lt;pname&gt;
	 * parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev.
	 */
	public static final int GL_CLAMP_VERTEX_COLOR_ARB = 0x891a;
	public static final int GL_CLAMP_FRAGMENT_COLOR_ARB = 0x891b;
	public static final int GL_CLAMP_READ_COLOR_ARB = 0x891c;
	/**
	 * Accepted by the &lt;clamp&gt; parameter of ClampColorARB.
	 */
	public static final int GL_FIXED_ONLY_ARB = 0x891d;

	private ARBColorBufferFloat() {
	}


	/**
	 * Accepted as a bit set in the GLX_RENDER_TYPE variable
	 */
	public static void glClampColorARB(int target, int clamp) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_color_buffer_float_glClampColorARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglClampColorARB(target, clamp, function_pointer);
	}
	private static native void nglClampColorARB(int target, int clamp, long function_pointer);
}
