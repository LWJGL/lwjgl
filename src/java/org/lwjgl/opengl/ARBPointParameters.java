/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBPointParameters {
	public static final int GL_POINT_DISTANCE_ATTENUATION_ARB = 0x8129;
	public static final int GL_POINT_FADE_THRESHOLD_SIZE_ARB = 0x8128;
	public static final int GL_POINT_SIZE_MAX_ARB = 0x8127;
	public static final int GL_POINT_SIZE_MIN_ARB = 0x8126;

	private ARBPointParameters() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glPointParameterARB(int pname, FloatBuffer pfParams) {
		BufferChecks.checkBuffer(pfParams, 4);
		nglPointParameterfvARB(pname, pfParams, pfParams.position());
	}
	private static native void nglPointParameterfvARB(int pname, FloatBuffer pfParams, int pfParams_position);

	public static native void glPointParameterfARB(int pname, float param);
}
