/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public class NVProgram {
	public static final int GL_PROGRAM_ERROR_STRING_NV = 0x8874;
	public static final int GL_PROGRAM_ERROR_POSITION_NV = 0x864b;
	public static final int GL_PROGRAM_STRING_NV = 0x8628;
	public static final int GL_PROGRAM_RESIDENT_NV = 0x8647;
	public static final int GL_PROGRAM_LENGTH_NV = 0x8627;
	public static final int GL_PROGRAM_TARGET_NV = 0x8646;

	static native void initNativeStubs() throws LWJGLException;

	public static void glRequestResidentProgramsNV(IntBuffer programIDs) {
		BufferChecks.checkDirect(programIDs);
		nglRequestResidentProgramsNV((programIDs.remaining()), programIDs, programIDs.position());
	}
	private static native void nglRequestResidentProgramsNV(int n, IntBuffer programIDs, int programIDs_position);

	public static boolean glAreProgramsResidentNV(IntBuffer programIDs, ByteBuffer programResidences) {
		BufferChecks.checkDirect(programIDs);
		BufferChecks.checkDirect(programResidences);
		if (programIDs.remaining() != programResidences.remaining())
			throw new IllegalArgumentException("programIDs.remaining() != programResidences.remaining()");
		boolean __result = nglAreProgramsResidentNV((programIDs.remaining()), programIDs, programIDs.position(), programResidences, programResidences.position());
		return __result;
	}
	private static native boolean nglAreProgramsResidentNV(int n, IntBuffer programIDs, int programIDs_position, ByteBuffer programResidences, int programResidences_position);

	public static native boolean glIsProgramNV(int programID);

	public static void glGetProgramStringNV(int programID, int parameterName, ByteBuffer paramString) {
		BufferChecks.checkDirect(paramString);
		nglGetProgramStringNV(programID, parameterName, paramString, paramString.position());
	}
	private static native void nglGetProgramStringNV(int programID, int parameterName, Buffer paramString, int paramString_position);

	public static void glGetProgramNV(int programID, int parameterName, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetProgramivNV(programID, parameterName, params, params.position());
	}
	private static native void nglGetProgramivNV(int programID, int parameterName, IntBuffer params, int params_position);

	public static void glGenProgramsNV(IntBuffer programs) {
		BufferChecks.checkDirect(programs);
		nglGenProgramsNV((programs.remaining()), programs, programs.position());
	}
	private static native void nglGenProgramsNV(int n, IntBuffer programs, int programs_position);

	public static void glDeleteProgramsNV(IntBuffer programs) {
		BufferChecks.checkDirect(programs);
		nglDeleteProgramsNV((programs.remaining()), programs, programs.position());
	}
	private static native void nglDeleteProgramsNV(int n, IntBuffer programs, int programs_position);

	public static native void glBindProgramNV(int target, int programID);

	public static void glLoadProgramNV(int target, int programID, ByteBuffer string) {
		BufferChecks.checkDirect(string);
		nglLoadProgramNV(target, programID, (string.remaining()), string, string.position());
	}
	private static native void nglLoadProgramNV(int target, int programID, int length, Buffer string, int string_position);
}
