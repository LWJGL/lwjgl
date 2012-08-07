/*
 * Copyright (c) 2002-2012 LWJGL Project
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
import org.lwjgl.util.generator.opengl.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface ARB_debug_label {

	/**
	 * Tokens accepted or provided by the &lt;identifier&gt; parameters of
	 * ObjectLabel and GetObjectLabel:
	 */
	int GL_BUFFER           = 0x82E0,
		GL_SHADER           = 0x82E1,
		GL_PROGRAM          = 0x82E2,
		GL_QUERY            = 0x82E3,
		GL_PROGRAM_PIPELINE = 0x82E4,
		GL_SAMPLER          = 0x82E6,
		GL_DISPLAY_LIST     = 0x82E7;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, GetDoublev, and GetInteger64v:
	 */
	int GL_MAX_LABEL_LENGTH = 0x82E8;

	@Reuse("GL43")
	void glObjectLabel(@GLenum int identifier, @GLuint int name, @AutoSize(value = "label", canBeNull = true) @GLsizei int length,
	                   @Check(canBeNull = true) @Const @GLchar ByteBuffer label);

	@Reuse("GL43")
	@Alternate("glObjectLabel")
	void glObjectLabel(@GLenum int identifier, @GLuint int name, @Constant("label.length()") @GLsizei int length,
	                   CharSequence label);

	@Reuse("GL43")
	void glGetObjectLabel(@GLenum int identifier, @GLuint int name, @AutoSize("label") @GLsizei int bufSize,
	                      @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                      @OutParameter @GLchar ByteBuffer label);

	@Reuse("GL43")
	@Alternate("glGetObjectLabel")
	@GLreturn(value = "label", maxLength = "bufSize")
	void glGetObjectLabel2(@GLenum int identifier, @GLuint int name, @GLsizei int bufSize,
	                       @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(label_length)") IntBuffer length,
	                       @OutParameter @GLchar ByteBuffer label);

	@Reuse("GL43")
	void glObjectPtrLabel(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @AutoSize(value = "label", canBeNull = true) @GLsizei int length,
	                      @Check(canBeNull = true) @Const @GLchar ByteBuffer label);

	@Reuse("GL43")
	@Alternate("glObjectPtrLabel")
	void glObjectPtrLabel(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @Constant("label.length()") @GLsizei int length,
	                      CharSequence label);

	@Reuse("GL43")
	void glGetObjectPtrLabel(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @AutoSize("label") @GLsizei int bufSize,
	                         @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                         @OutParameter @GLchar ByteBuffer label);

	@Reuse("GL43")
	@Alternate("glGetObjectPtrLabel")
	@GLreturn(value = "label", maxLength = "bufSize")
	void glGetObjectPtrLabel2(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @GLsizei int bufSize,
	                          @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(label_length)") IntBuffer length,
	                          @OutParameter @GLchar ByteBuffer label);

}