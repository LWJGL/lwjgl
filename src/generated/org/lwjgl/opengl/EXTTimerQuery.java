/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTTimerQuery {
	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQuery, EndQuery, and
	 * GetQueryiv:
	 */
	public static final int GL_TIME_ELAPSED_EXT = 0x88bf;

	private EXTTimerQuery() {
	}


	public static void glGetQueryObjectEXT(int id, int pname, LongBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_timer_query_glGetQueryObjecti64vEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 1);
		nglGetQueryObjecti64vEXT(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetQueryObjecti64vEXT(int id, int pname, LongBuffer params, int params_position, long function_pointer);

	public static void glGetQueryObjectuEXT(int id, int pname, LongBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_timer_query_glGetQueryObjectui64vEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 1);
		nglGetQueryObjectui64vEXT(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetQueryObjectui64vEXT(int id, int pname, LongBuffer params, int params_position, long function_pointer);
}
