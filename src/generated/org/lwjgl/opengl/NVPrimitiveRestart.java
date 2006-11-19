/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVPrimitiveRestart {
	/**
	 * Accepted by the &lt;array&gt; parameter of EnableClientState and
	 * DisableClientState, by the &lt;cap&gt; parameter of IsEnabled, and by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	public static final int GL_PRIMITIVE_RESTART_NV = 0x8558;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_PRIMITIVE_RESTART_INDEX_NV = 0x8559;

	private NVPrimitiveRestart() {
	}


	public static void glPrimitiveRestartNV() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_primitive_restart_glPrimitiveRestartNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPrimitiveRestartNV(function_pointer);
	}
	private static native void nglPrimitiveRestartNV(long function_pointer);

	public static void glPrimitiveRestartIndexNV(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_primitive_restart_glPrimitiveRestartIndexNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPrimitiveRestartIndexNV(index, function_pointer);
	}
	private static native void nglPrimitiveRestartIndexNV(int index, long function_pointer);
}
