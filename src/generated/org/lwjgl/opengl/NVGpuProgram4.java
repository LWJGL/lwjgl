/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVGpuProgram4 {
	/**
	 *Accepted by the &lt;pname&gt; parameter of GetProgramivARB: 
	 */
	public static final int GL_PROGRAM_ATTRIB_COMPONENTS_NV = 0x8906;
	public static final int GL_PROGRAM_RESULT_COMPONENTS_NV = 0x8907;
	public static final int GL_MAX_PROGRAM_ATTRIB_COMPONENTS_NV = 0x8908;
	public static final int GL_MAX_PROGRAM_RESULT_COMPONENTS_NV = 0x8909;
	public static final int GL_MAX_PROGRAM_GENERIC_ATTRIBS_NV = 0x8da5;
	public static final int GL_MAX_PROGRAM_GENERIC_RESULTS_NV = 0x8da6;

	private NVGpuProgram4() {
	}


	public static void glProgramLocalParameterI4iNV(int target, int index, int x, int y, int z, int w) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramLocalParameterI4iNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramLocalParameterI4iNV(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramLocalParameterI4iNV(int target, int index, int x, int y, int z, int w, long function_pointer);

	public static void glProgramLocalParameterI4NV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramLocalParameterI4ivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramLocalParameterI4ivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParameterI4ivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramLocalParametersI4NV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramLocalParametersI4ivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglProgramLocalParametersI4ivNV(target, index, (params.remaining()) >> 2, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParametersI4ivNV(int target, int index, int count, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramLocalParameterI4uiNV(int target, int index, int x, int y, int z, int w) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramLocalParameterI4uiNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramLocalParameterI4uiNV(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramLocalParameterI4uiNV(int target, int index, int x, int y, int z, int w, long function_pointer);

	public static void glProgramLocalParameterI4uNV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramLocalParameterI4uivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramLocalParameterI4uivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParameterI4uivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramLocalParametersI4uNV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramLocalParametersI4uivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglProgramLocalParametersI4uivNV(target, index, (params.remaining()) >> 2, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParametersI4uivNV(int target, int index, int count, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramEnvParameterI4iNV(int target, int index, int x, int y, int z, int w) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramEnvParameterI4iNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramEnvParameterI4iNV(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramEnvParameterI4iNV(int target, int index, int x, int y, int z, int w, long function_pointer);

	public static void glProgramEnvParameterI4NV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramEnvParameterI4ivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramEnvParameterI4ivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParameterI4ivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramEnvParametersI4NV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramEnvParametersI4ivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglProgramEnvParametersI4ivNV(target, index, (params.remaining()) >> 2, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParametersI4ivNV(int target, int index, int count, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramEnvParameterI4uiNV(int target, int index, int x, int y, int z, int w) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramEnvParameterI4uiNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramEnvParameterI4uiNV(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramEnvParameterI4uiNV(int target, int index, int x, int y, int z, int w, long function_pointer);

	public static void glProgramEnvParameterI4uNV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramEnvParameterI4uivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramEnvParameterI4uivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParameterI4uivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramEnvParametersI4uNV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glProgramEnvParametersI4uivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglProgramEnvParametersI4uivNV(target, index, (params.remaining()) >> 2, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParametersI4uivNV(int target, int index, int count, IntBuffer params, int params_position, long function_pointer);

	public static void glGetProgramLocalParameterINV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glGetProgramLocalParameterIivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramLocalParameterIivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramLocalParameterIivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);

	public static void glGetProgramLocalParameterIuNV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glGetProgramLocalParameterIuivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramLocalParameterIuivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramLocalParameterIuivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);

	public static void glGetProgramEnvParameterINV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glGetProgramEnvParameterIivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramEnvParameterIivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramEnvParameterIivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);

	public static void glGetProgramEnvParameterIuNV(int target, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_gpu_program4_glGetProgramEnvParameterIuivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramEnvParameterIuivNV(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramEnvParameterIuivNV(int target, int index, IntBuffer params, int params_position, long function_pointer);
}
