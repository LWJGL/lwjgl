/* 
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

public abstract class NVProgram {

	/*
	   Accepted by the <pname> parameter of GetProgramivNV:
	 */
	public static final int GL_PROGRAM_TARGET_NV = 0x8646;
	public static final int GL_PROGRAM_LENGTH_NV = 0x8627;
	public static final int GL_PROGRAM_RESIDENT_NV = 0x8647;

	/*
	   Accepted by the <pname> parameter of GetProgramStringNV:
	 */
	public static final int GL_PROGRAM_STRING_NV = 0x8628;

	/*
	   Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	   GetFloatv, and GetDoublev:
	 */
	public static final int GL_PROGRAM_ERROR_POSITION_NV = 0x864B;

	/*
	   Accepted by the <name> parameter of GetString:
	 */
	public static final int GL_PROGRAM_ERROR_STRING_NV = 0x8874;

	static native void initNativeStubs() throws LWJGLException;

	// ---------------------------
	public static void glLoadProgramNV(int target, int programID, ByteBuffer string) {
		BufferChecks.checkDirect(string);
		nglLoadProgramNV(target, programID, string.remaining(), string, string.position());
	}

	private static native void nglLoadProgramNV(int target, int programID, int length, Buffer string, int stringOffset);

	// ---------------------------
	public static native void glBindProgramNV(int target, int programID);
	// ---------------------------

	public static void glDeleteProgramsNV(IntBuffer programs) {
		BufferChecks.checkDirect(programs);
		nglDeleteProgramsNV(programs.remaining(), programs, programs.position());
	}

	private static native void nglDeleteProgramsNV(int n, IntBuffer programs, int programsOffset);

	// ---------------------------

	// ---------------------------
	public static void glGenProgramsNV(IntBuffer programs) {
		BufferChecks.checkDirect(programs);
		nglGenProgramsNV(programs.remaining(), programs, programs.position());
	}

	private static native void nglGenProgramsNV(int n, IntBuffer programs, int programsOffset);

	// ---------------------------

	// ---------------------------
	public static void glGetProgramNV(int programID, int parameterName, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetProgramivNV(programID, parameterName, params, params.position());
	}

	private static native void nglGetProgramivNV(int programID, int parameterName, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetProgramStringNV(int programID, int parameterName, ByteBuffer paramString) {
		BufferChecks.checkDirect(paramString);
		nglGetProgramStringNV(programID, parameterName, paramString, paramString.position());
	}

	private static native void nglGetProgramStringNV(int programID, int parameterName, Buffer paramString, int paramStringOffset);
	// ---------------------------

	public static native boolean glIsProgramNV(int programID);

	// ---------------------------
	public static boolean glAreProgramsResidentNV(IntBuffer programIDs, ByteBuffer programResidences) {
		BufferChecks.checkDirect(programIDs);
		BufferChecks.checkDirect(programResidences);
		if ( programIDs.remaining() != programResidences.remaining() )
			throw new IllegalArgumentException("programIDs.remaining() != programResidences.remaining()");
		return nglAreProgramsResidentNV(programIDs.remaining(),
		                                programIDs,
		                                programIDs.position(),
		                                programResidences,
		                                programResidences.position());
	}

	private static native boolean nglAreProgramsResidentNV(int n,
	                                                       IntBuffer programIDs,
	                                                       int programIDsOffset,
	                                                       ByteBuffer programResidences,
	                                                       int programResidencesOffset);
	// ---------------------------

	// ---------------------------
	public static void glRequestResidentProgramsNV(IntBuffer programIDs) {
		BufferChecks.checkDirect(programIDs);
		nglRequestResidentProgramsNV(programIDs.remaining(), programIDs, programIDs.position());
	}

	private static native void nglRequestResidentProgramsNV(int n, IntBuffer programIDs, int programIDsOffset);
	// ---------------------------
}

