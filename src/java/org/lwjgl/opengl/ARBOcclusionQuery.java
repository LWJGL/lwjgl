/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBOcclusionQuery {
	public static final int GL_QUERY_RESULT_AVAILABLE_ARB = 0x8867;
	public static final int GL_QUERY_RESULT_ARB = 0x8866;
	public static final int GL_CURRENT_QUERY_ARB = 0x8865;
	public static final int GL_QUERY_COUNTER_BITS_ARB = 0x8864;
	public static final int GL_SAMPLES_PASSED_ARB = 0x8914;

	private ARBOcclusionQuery() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetQueryObjectuARB(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetQueryObjectuivARB(id, pname, params, params.position());
	}
	private static native void nglGetQueryObjectuivARB(int id, int pname, IntBuffer params, int params_position);

	public static void glGetQueryObjectARB(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetQueryObjectivARB(id, pname, params, params.position());
	}
	private static native void nglGetQueryObjectivARB(int id, int pname, IntBuffer params, int params_position);

	public static void glGetQueryARB(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetQueryivARB(target, pname, params, params.position());
	}
	private static native void nglGetQueryivARB(int target, int pname, IntBuffer params, int params_position);

	public static native void glEndQueryARB(int target);

	public static native void glBeginQueryARB(int target, int id);

	public static native boolean glIsQueryARB(int id);

	public static void glDeleteQueriesARB(IntBuffer ids) {
		BufferChecks.checkDirect(ids);
		nglDeleteQueriesARB((ids.remaining()), ids, ids.position());
	}
	private static native void nglDeleteQueriesARB(int n, IntBuffer ids, int ids_position);

	public static void glGenQueriesARB(IntBuffer ids) {
		BufferChecks.checkDirect(ids);
		nglGenQueriesARB((ids.remaining()), ids, ids.position());
	}
	private static native void nglGenQueriesARB(int n, IntBuffer ids, int ids_position);
}
