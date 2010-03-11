/*
 * Copyright (c) 2002-2008 LWJGL Project
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

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@Extension(postfix = "")
public interface ARB_sampler_objects {

	/**
	 * Accepted by the &lt;value&gt; parameter of the GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv and GetDoublev functions:
	 */
	int GL_SAMPLER_BINDING = 0x8919;

	void glGenSamplers(@AutoSize("samplers") @GLsizei int count, @OutParameter @GLuint IntBuffer samplers);

	void glDeleteSamplers(@AutoSize("samplers") @GLsizei int count, @Const @GLuint IntBuffer samplers);

	boolean glIsSampler(@GLuint int sampler);

	void glBindSampler(@GLenum int unit, @GLuint int sampler);

	void glSamplerParameteri(@GLuint int sampler, @GLenum int pname, int param);

	void glSamplerParameterf(@GLuint int sampler, @GLenum int pname, float param);

	@StripPostfix("params")
	void glSamplerParameteriv(@GLuint int sampler, @GLenum int pname, @Check("4") @Const IntBuffer params);

	@StripPostfix("params")
	void glSamplerParameterfv(@GLuint int sampler, @GLenum int pname, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	void glSamplerParameterIiv(@GLuint int sampler, @GLenum int pname, @Check("4") @Const IntBuffer params);

	@StripPostfix("params")
	void glSamplerParameterIuiv(@GLuint int sampler, @GLenum int pname, @Check("4") @Const @GLuint IntBuffer params);

	@StripPostfix("params")
	void glGetSamplerParameteriv(@GLuint int sampler, @GLenum int pname, @Check("4") @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetSamplerParameterfv(@GLuint int sampler, @GLenum int pname, @Check("4") @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetSamplerParameterIiv(@GLuint int sampler, @GLenum int pname, @Check("4") @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetSamplerParameterIfv(@GLuint int sampler, @GLenum int pname, @Check("4") @OutParameter FloatBuffer params);

}