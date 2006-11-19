/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBPointParameters {
	public static final int GL_POINT_SIZE_MIN_ARB = 0x8126;
	public static final int GL_POINT_SIZE_MAX_ARB = 0x8127;
	public static final int GL_POINT_FADE_THRESHOLD_SIZE_ARB = 0x8128;
	public static final int GL_POINT_DISTANCE_ATTENUATION_ARB = 0x8129;

	private ARBPointParameters() {
	}


	public static void glPointParameterfARB(int pname, float param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_point_parameters_glPointParameterfARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPointParameterfARB(pname, param, function_pointer);
	}
	private static native void nglPointParameterfARB(int pname, float param, long function_pointer);

	public static void glPointParameterARB(int pname, FloatBuffer pfParams) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_point_parameters_glPointParameterfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pfParams, 4);
		nglPointParameterfvARB(pname, pfParams, pfParams.position(), function_pointer);
	}
	private static native void nglPointParameterfvARB(int pname, FloatBuffer pfParams, int pfParams_position, long function_pointer);
}
