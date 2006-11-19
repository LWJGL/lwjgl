/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTPointParameters {
	public static final int GL_POINT_SIZE_MIN_EXT = 0x8126;
	public static final int GL_POINT_SIZE_MAX_EXT = 0x8127;
	public static final int GL_POINT_FADE_THRESHOLD_SIZE_EXT = 0x8128;
	public static final int GL_DISTANCE_ATTENUATION_EXT = 0x8129;

	private EXTPointParameters() {
	}


	public static void glPointParameterfEXT(int pname, float param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_point_parameters_glPointParameterfEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPointParameterfEXT(pname, param, function_pointer);
	}
	private static native void nglPointParameterfEXT(int pname, float param, long function_pointer);

	public static void glPointParameterEXT(int pname, FloatBuffer pfParams) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_point_parameters_glPointParameterfvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pfParams, 4);
		nglPointParameterfvEXT(pname, pfParams, pfParams.position(), function_pointer);
	}
	private static native void nglPointParameterfvEXT(int pname, FloatBuffer pfParams, int pfParams_position, long function_pointer);
}
