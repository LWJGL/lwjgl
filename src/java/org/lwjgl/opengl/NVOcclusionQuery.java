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
		nglGetOcclusionQueryivNV(id, pname, params, params.position());
	}
	private static native void nglGetOcclusionQueryivNV(int id, int pname, IntBuffer params, int params_position);

	public static void glGetOcclusionQueryuNV(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetOcclusionQueryuivNV(id, pname, params, params.position());
	}
	private static native void nglGetOcclusionQueryuivNV(int id, int pname, IntBuffer params, int params_position);

	public static native void glEndOcclusionQueryNV();

	public static native void glBeginOcclusionQueryNV(int id);

	public static native boolean glIsOcclusionQueryNV(int id);

	public static void glDeleteOcclusionQueriesNV(IntBuffer piIDs) {
		BufferChecks.checkDirect(piIDs);
		nglDeleteOcclusionQueriesNV((piIDs.remaining()), piIDs, piIDs.position());
	}
	private static native void nglDeleteOcclusionQueriesNV(int n, IntBuffer piIDs, int piIDs_position);

	public static void glGenOcclusionQueriesNV(IntBuffer piIDs) {
		BufferChecks.checkDirect(piIDs);
		nglGenOcclusionQueriesNV((piIDs.remaining()), piIDs, piIDs.position());
	}
	private static native void nglGenOcclusionQueriesNV(int n, IntBuffer piIDs, int piIDs_position);
}
