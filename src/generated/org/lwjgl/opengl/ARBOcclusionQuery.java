/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBOcclusionQuery {
	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQueryARB, EndQueryARB,
	 * and GetQueryivARB:
	 */
	public static final int GL_SAMPLES_PASSED_ARB = 0x8914;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetQueryivARB:
	 */
	public static final int GL_QUERY_COUNTER_BITS_ARB = 0x8864;
	public static final int GL_CURRENT_QUERY_ARB = 0x8865;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetQueryObjectivARB and
	 * GetQueryObjectuivARB:
	 */
	public static final int GL_QUERY_RESULT_ARB = 0x8866;
	public static final int GL_QUERY_RESULT_AVAILABLE_ARB = 0x8867;

	private ARBOcclusionQuery() {
	}


	public static void glGenQueriesARB(IntBuffer ids) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glGenQueriesARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(ids);
		nglGenQueriesARB((ids.remaining()), ids, ids.position(), function_pointer);
	}
	private static native void nglGenQueriesARB(int n, IntBuffer ids, int ids_position, long function_pointer);

	public static void glDeleteQueriesARB(IntBuffer ids) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glDeleteQueriesARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(ids);
		nglDeleteQueriesARB((ids.remaining()), ids, ids.position(), function_pointer);
	}
	private static native void nglDeleteQueriesARB(int n, IntBuffer ids, int ids_position, long function_pointer);

	public static boolean glIsQueryARB(int id) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glIsQueryARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsQueryARB(id, function_pointer);
		return __result;
	}
	private static native boolean nglIsQueryARB(int id, long function_pointer);

	public static void glBeginQueryARB(int target, int id) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glBeginQueryARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBeginQueryARB(target, id, function_pointer);
	}
	private static native void nglBeginQueryARB(int target, int id, long function_pointer);

	public static void glEndQueryARB(int target) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glEndQueryARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEndQueryARB(target, function_pointer);
	}
	private static native void nglEndQueryARB(int target, long function_pointer);

	public static void glGetQueryARB(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glGetQueryivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 1);
		nglGetQueryivARB(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetQueryivARB(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetQueryObjectARB(int id, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glGetQueryObjectivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 1);
		nglGetQueryObjectivARB(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetQueryObjectivARB(int id, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetQueryObjectuARB(int id, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_occlusion_query_glGetQueryObjectuivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 1);
		nglGetQueryObjectuivARB(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetQueryObjectuivARB(int id, int pname, IntBuffer params, int params_position, long function_pointer);
}
