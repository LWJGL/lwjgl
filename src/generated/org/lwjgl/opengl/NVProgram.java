/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public class NVProgram {
	/**
	 *         Accepted by the &lt;pname&gt; parameter of GetProgramivNV:
	 */
	public static final int GL_PROGRAM_TARGET_NV = 0x8646;
	public static final int GL_PROGRAM_LENGTH_NV = 0x8627;
	public static final int GL_PROGRAM_RESIDENT_NV = 0x8647;
	/**
	 *         Accepted by the &lt;pname&gt; parameter of GetProgramStringNV:
	 */
	public static final int GL_PROGRAM_STRING_NV = 0x8628;
	/**
	 *         Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 *         GetFloatv, and GetDoublev:
	 */
	public static final int GL_PROGRAM_ERROR_POSITION_NV = 0x864b;
	/**
	 *         Accepted by the &lt;name&gt; parameter of GetString:
	 */
	public static final int GL_PROGRAM_ERROR_STRING_NV = 0x8874;


	public static void glLoadProgramNV(int target, int programID, ByteBuffer string) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glLoadProgramNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(string);
		nglLoadProgramNV(target, programID, (string.remaining()), string, string.position(), function_pointer);
	}
	private static native void nglLoadProgramNV(int target, int programID, int length, Buffer string, int string_position, long function_pointer);

	public static void glBindProgramNV(int target, int programID) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glBindProgramNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindProgramNV(target, programID, function_pointer);
	}
	private static native void nglBindProgramNV(int target, int programID, long function_pointer);

	public static void glDeleteProgramsNV(IntBuffer programs) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glDeleteProgramsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(programs);
		nglDeleteProgramsNV((programs.remaining()), programs, programs.position(), function_pointer);
	}
	private static native void nglDeleteProgramsNV(int n, IntBuffer programs, int programs_position, long function_pointer);

	public static void glGenProgramsNV(IntBuffer programs) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glGenProgramsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(programs);
		nglGenProgramsNV((programs.remaining()), programs, programs.position(), function_pointer);
	}
	private static native void nglGenProgramsNV(int n, IntBuffer programs, int programs_position, long function_pointer);

	public static void glGetProgramNV(int programID, int parameterName, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glGetProgramivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetProgramivNV(programID, parameterName, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramivNV(int programID, int parameterName, IntBuffer params, int params_position, long function_pointer);

	public static void glGetProgramStringNV(int programID, int parameterName, ByteBuffer paramString) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glGetProgramStringNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(paramString);
		nglGetProgramStringNV(programID, parameterName, paramString, paramString.position(), function_pointer);
	}
	private static native void nglGetProgramStringNV(int programID, int parameterName, Buffer paramString, int paramString_position, long function_pointer);

	public static boolean glIsProgramNV(int programID) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glIsProgramNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsProgramNV(programID, function_pointer);
		return __result;
	}
	private static native boolean nglIsProgramNV(int programID, long function_pointer);

	public static boolean glAreProgramsResidentNV(IntBuffer programIDs, ByteBuffer programResidences) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glAreProgramsResidentNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (programIDs.remaining() != programResidences.remaining())
			throw new IllegalArgumentException("programIDs.remaining() != programResidences.remaining()");
		BufferChecks.checkDirect(programIDs);
		BufferChecks.checkDirect(programResidences);
		boolean __result = nglAreProgramsResidentNV((programIDs.remaining()), programIDs, programIDs.position(), programResidences, programResidences.position(), function_pointer);
		return __result;
	}
	private static native boolean nglAreProgramsResidentNV(int n, IntBuffer programIDs, int programIDs_position, ByteBuffer programResidences, int programResidences_position, long function_pointer);

	public static void glRequestResidentProgramsNV(IntBuffer programIDs) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_program_glRequestResidentProgramsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(programIDs);
		nglRequestResidentProgramsNV((programIDs.remaining()), programIDs, programIDs.position(), function_pointer);
	}
	private static native void nglRequestResidentProgramsNV(int n, IntBuffer programIDs, int programIDs_position, long function_pointer);
}
