/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVFence {
	public static final int GL_FENCE_CONDITION_NV = 0x84f4;
	public static final int GL_FENCE_STATUS_NV = 0x84f3;
	public static final int GL_ALL_COMPLETED_NV = 0x84f2;

	private NVFence() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetFenceivNV(int fence, int pname, IntBuffer piParams) {
		BufferChecks.checkBuffer(piParams, 4);
		nglGetFenceivNV(fence, pname, piParams, piParams.position());
	}
	private static native void nglGetFenceivNV(int fence, int pname, IntBuffer piParams, int piParams_position);

	public static native boolean glIsFenceNV(int fence);

	public static native void glFinishFenceNV(int fence);

	public static native boolean glTestFenceNV(int fence);

	public static native void glSetFenceNV(int fence, int condition);

	public static void glDeleteFencesNV(IntBuffer piFences) {
		BufferChecks.checkDirect(piFences);
		nglDeleteFencesNV((piFences.remaining()), piFences, piFences.position());
	}
	private static native void nglDeleteFencesNV(int n, IntBuffer piFences, int piFences_position);

	public static void glGenFencesNV(IntBuffer piFences) {
		BufferChecks.checkDirect(piFences);
		nglGenFencesNV((piFences.remaining()), piFences, piFences.position());
	}
	private static native void nglGenFencesNV(int n, IntBuffer piFences, int piFences_position);
}
