/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVOcclusionQuery {
	public static final int GL_PIXEL_COUNT_AVAILABLE_NV = 0x8867;
	public static final int GL_PIXEL_COUNT_NV = 0x8866;
	public static final int GL_CURRENT_OCCLUSION_QUERY_ID_NV = 0x8865;
	public static final int GL_PIXEL_COUNTER_BITS_NV = 0x8864;
	public static final int GL_OCCLUSION_TEST_RESULT_HP = 0x8166;
	public static final int GL_OCCLUSION_TEST_HP = 0x8165;

	private NVOcclusionQuery() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetOcclusionQueryNV(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		long function_pointer = GLContext.getCapabilities().NV_occlusion_query_glGetOcclusionQueryivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglGetOcclusionQueryivNV(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetOcclusionQueryivNV(int id, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetOcclusionQueryuNV(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		long function_pointer = GLContext.getCapabilities().NV_occlusion_query_glGetOcclusionQueryuivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglGetOcclusionQueryuivNV(id, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetOcclusionQueryuivNV(int id, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glEndOcclusionQueryNV() {
		long function_pointer = GLContext.getCapabilities().NV_occlusion_query_glEndOcclusionQueryNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEndOcclusionQueryNV(function_pointer);
	}
	private static native void nglEndOcclusionQueryNV(long function_pointer);

	public static void glBeginOcclusionQueryNV(int id) {
		long function_pointer = GLContext.getCapabilities().NV_occlusion_query_glBeginOcclusionQueryNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBeginOcclusionQueryNV(id, function_pointer);
	}
	private static native void nglBeginOcclusionQueryNV(int id, long function_pointer);

	public static boolean glIsOcclusionQueryNV(int id) {
		long function_pointer = GLContext.getCapabilities().NV_occlusion_query_glIsOcclusionQueryNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsOcclusionQueryNV(id, function_pointer);
		return __result;
	}
	private static native boolean nglIsOcclusionQueryNV(int id, long function_pointer);

	public static void glDeleteOcclusionQueriesNV(IntBuffer piIDs) {
		BufferChecks.checkDirect(piIDs);
		long function_pointer = GLContext.getCapabilities().NV_occlusion_query_glDeleteOcclusionQueriesNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteOcclusionQueriesNV((piIDs.remaining()), piIDs, piIDs.position(), function_pointer);
	}
	private static native void nglDeleteOcclusionQueriesNV(int n, IntBuffer piIDs, int piIDs_position, long function_pointer);

	public static void glGenOcclusionQueriesNV(IntBuffer piIDs) {
		BufferChecks.checkDirect(piIDs);
		long function_pointer = GLContext.getCapabilities().NV_occlusion_query_glGenOcclusionQueriesNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglGenOcclusionQueriesNV((piIDs.remaining()), piIDs, piIDs.position(), function_pointer);
	}
	private static native void nglGenOcclusionQueriesNV(int n, IntBuffer piIDs, int piIDs_position, long function_pointer);
}
