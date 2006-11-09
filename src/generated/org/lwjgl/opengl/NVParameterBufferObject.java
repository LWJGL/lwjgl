/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVParameterBufferObject {
	/**
	 *Accepted by the &lt;pname&gt; parameter of GetProgramivARB: 
	 */
	public static final int GL_MAX_PROGRAM_PARAMETER_BUFFER_BINDINGS_NV = 0x8da0;
	public static final int GL_MAX_PROGRAM_PARAMETER_BUFFER_SIZE_NV = 0x8da1;
	/**
	 * Accepted by the &lt;target&gt; parameter of ProgramBufferParametersfvNV,
	 * ProgramBufferParametersIivNV, and ProgramBufferParametersIuivNV,
	 * BindBufferRangeNV, BindBufferOffsetNV, BindBufferBaseNV, and BindBuffer
	 * and the &lt;value&gt; parameter of GetIntegerIndexedvEXT:
	 */
	public static final int GL_VERTEX_PROGRAM_PARAMETER_BUFFER_NV = 0x8da2;
	public static final int GL_GEOMETRY_PROGRAM_PARAMETER_BUFFER_NV = 0x8da3;
	public static final int GL_FRAGMENT_PROGRAM_PARAMETER_BUFFER_NV = 0x8da4;

	private NVParameterBufferObject() {
	}


	public static void glBindBufferRangeNV(int target, int index, int buffer, long offset, long size) {
		long function_pointer = GLContext.getCapabilities().NV_parameter_buffer_object_glBindBufferRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindBufferRangeNV(target, index, buffer, offset, size, function_pointer);
	}
	private static native void nglBindBufferRangeNV(int target, int index, int buffer, long offset, long size, long function_pointer);

	public static void glBindBufferOffsetNV(int target, int index, int buffer, long offset) {
		long function_pointer = GLContext.getCapabilities().NV_parameter_buffer_object_glBindBufferOffsetNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindBufferOffsetNV(target, index, buffer, offset, function_pointer);
	}
	private static native void nglBindBufferOffsetNV(int target, int index, int buffer, long offset, long function_pointer);

	public static void glBindBufferBaseNV(int target, int index, int buffer) {
		long function_pointer = GLContext.getCapabilities().NV_parameter_buffer_object_glBindBufferBaseNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindBufferBaseNV(target, index, buffer, function_pointer);
	}
	private static native void nglBindBufferBaseNV(int target, int index, int buffer, long function_pointer);

	public static void glProgramBufferParametersNV(int target, int buffer, int index, FloatBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_parameter_buffer_object_glProgramBufferParametersfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglProgramBufferParametersfvNV(target, buffer, index, (params.remaining()) >> 2, params, params.position(), function_pointer);
	}
	private static native void nglProgramBufferParametersfvNV(int target, int buffer, int index, int count, FloatBuffer params, int params_position, long function_pointer);

	public static void glProgramBufferParametersINV(int target, int buffer, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_parameter_buffer_object_glProgramBufferParametersIivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglProgramBufferParametersIivNV(target, buffer, index, (params.remaining()) >> 2, params, params.position(), function_pointer);
	}
	private static native void nglProgramBufferParametersIivNV(int target, int buffer, int index, int count, IntBuffer params, int params_position, long function_pointer);

	public static void glProgramBufferParametersIuNV(int target, int buffer, int index, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().NV_parameter_buffer_object_glProgramBufferParametersIuivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglProgramBufferParametersIuivNV(target, buffer, index, (params.remaining()) >> 2, params, params.position(), function_pointer);
	}
	private static native void nglProgramBufferParametersIuivNV(int target, int buffer, int index, int count, IntBuffer params, int params_position, long function_pointer);
}
