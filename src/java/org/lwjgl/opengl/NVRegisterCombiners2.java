/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import java.nio.FloatBuffer;

import org.lwjgl.BufferChecks;

public final class NVRegisterCombiners2 {
	public static final int GL_PER_STAGE_CONSTANTS_NV = 0x8535;

	private NVRegisterCombiners2() {
	}


	public static void glGetCombinerStageParameterNV(int stage, int pname, FloatBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_register_combiners2_glGetCombinerStageParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetCombinerStageParameterfvNV(stage, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetCombinerStageParameterfvNV(int stage, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glCombinerStageParameterNV(int stage, int pname, FloatBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_register_combiners2_glCombinerStageParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglCombinerStageParameterfvNV(stage, pname, params, params.position(), function_pointer);
	}
	private static native void nglCombinerStageParameterfvNV(int stage, int pname, FloatBuffer params, int params_position, long function_pointer);
}
