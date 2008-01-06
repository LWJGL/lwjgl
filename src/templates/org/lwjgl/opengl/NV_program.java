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

import org.lwjgl.util.generator.*;

import java.nio.*;

@Extension(postfix = "NV", isFinal = false)
public interface NV_program {

	/**
	 Accepted by the &lt;pname&gt; parameter of GetProgramivNV:
	 */
	int GL_PROGRAM_TARGET_NV = 0x8646;
	int GL_PROGRAM_LENGTH_NV = 0x8627;
	int GL_PROGRAM_RESIDENT_NV = 0x8647;

	/**
	 Accepted by the &lt;pname&gt; parameter of GetProgramStringNV:
	 */
	int GL_PROGRAM_STRING_NV = 0x8628;

	/**
	 Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 GetFloatv, and GetDoublev:
	 */
	int GL_PROGRAM_ERROR_POSITION_NV = 0x864B;

	/**
	 Accepted by the &lt;name&gt; parameter of GetString:
	 */
	int GL_PROGRAM_ERROR_STRING_NV = 0x8874;

	void glLoadProgramNV(@GLenum int target, @GLuint int programID, @AutoSize("string") @GLsizei int length, @Const @GLubyte Buffer string);

	void glBindProgramNV(@GLenum int target, @GLuint int programID);

	void glDeleteProgramsNV(@AutoSize("programs") @GLsizei int n, @Const @GLuint IntBuffer programs);

	void glGenProgramsNV(@AutoSize("programs") @GLsizei int n, @OutParameter @GLuint IntBuffer programs);

	@StripPostfix("params")
	void glGetProgramivNV(@GLuint int programID, @GLenum int parameterName, @OutParameter @Check @GLint IntBuffer params);

	void glGetProgramStringNV(@GLuint int programID, @GLenum int parameterName, @OutParameter @Check @GLubyte Buffer paramString);

	boolean glIsProgramNV(@GLuint int programID);

	@Code("		if (programIDs.remaining() != programResidences.remaining())\n" +
	      "			throw new IllegalArgumentException(\"programIDs.remaining() != programResidences.remaining()\");")
	boolean glAreProgramsResidentNV(@AutoSize("programIDs") @GLsizei int n,
	                                @Const
	                                @GLuint IntBuffer programIDs,
	                                @Check
	                                @GLboolean ByteBuffer programResidences);

	void glRequestResidentProgramsNV(@AutoSize("programIDs") @GLsizei int n, @GLuint IntBuffer programIDs);
}

