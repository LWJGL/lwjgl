/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTGpuProgramParameters {

	private EXTGpuProgramParameters() {
	}


	public static void glProgramEnvParameter4EXT(int target, int index, int count, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_program_parameters_glProgramEnvParameter4fvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, count << 2);
		nglProgramEnvParameter4fvEXT(target, index, count, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParameter4fvEXT(int target, int index, int count, FloatBuffer params, int params_position, long function_pointer);

	public static void glProgramLocalParameter4EXT(int target, int index, int count, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_program_parameters_glProgramLocalParameter4fvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, count << 2);
		nglProgramLocalParameter4fvEXT(target, index, count, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParameter4fvEXT(int target, int index, int count, FloatBuffer params, int params_position, long function_pointer);
}
