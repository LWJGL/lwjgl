/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTGpuProgramParameters {

	private EXTGpuProgramParameters() {
	}


	public static void glProgramEnvParameters4EXT(int target, int index, int count, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_program_parameters_glProgramEnvParameters4fvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, count << 2);
		nglProgramEnvParameters4fvEXT(target, index, count, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParameters4fvEXT(int target, int index, int count, FloatBuffer params, int params_position, long function_pointer);

	public static void glProgramLocalParameters4EXT(int target, int index, int count, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_program_parameters_glProgramLocalParameters4fvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, count << 2);
		nglProgramLocalParameters4fvEXT(target, index, count, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParameters4fvEXT(int target, int index, int count, FloatBuffer params, int params_position, long function_pointer);
}
