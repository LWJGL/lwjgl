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

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@Extension(postfix = "")
public interface ARB_uniform_buffer_object {

	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, and
	 * GetBufferPointerv:
	 */
	int GL_UNIFORM_BUFFER = 0x8A11;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegeri_v, GetBooleanv,
	 * GetIntegerv, GetFloatv, and GetDoublev:
	 */
	int GL_UNIFORM_BUFFER_BINDING = 0x8A28;

	/** Accepted by the &lt;pname&gt; parameter of GetIntegeri_v: */
	int GL_UNIFORM_BUFFER_START = 0x8A29;
	int GL_UNIFORM_BUFFER_SIZE = 0x8A2A;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_MAX_VERTEX_UNIFORM_BLOCKS = 0x8A2B;
	int GL_MAX_GEOMETRY_UNIFORM_BLOCKS = 0x8A2C;
	int GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 0x8A2D;
	int GL_MAX_COMBINED_UNIFORM_BLOCKS = 0x8A2E;
	int GL_MAX_UNIFORM_BUFFER_BINDINGS = 0x8A2F;
	int GL_MAX_UNIFORM_BLOCK_SIZE = 0x8A30;
	int GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 0x8A31;
	int GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 0x8A32;
	int GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 0x8A33;
	int GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 0x8A34;

	/** Accepted by the &lt;pname&gt; parameter of GetProgramiv: */
	int GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 0x8A35;
	int GL_ACTIVE_UNIFORM_BLOCKS = 0x8A36;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveUniformsivARB: */
	int GL_UNIFORM_TYPE = 0x8A37;
	int GL_UNIFORM_SIZE = 0x8A38;
	int GL_UNIFORM_NAME_LENGTH = 0x8A39;
	int GL_UNIFORM_BLOCK_INDEX = 0x8A3A;
	int GL_UNIFORM_OFFSET = 0x8A3B;
	int GL_UNIFORM_ARRAY_STRIDE = 0x8A3C;
	int GL_UNIFORM_MATRIX_STRIDE = 0x8A3D;
	int GL_UNIFORM_IS_ROW_MAJOR = 0x8A3E;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveUniformBlockivARB: */
	int GL_UNIFORM_BLOCK_BINDING = 0x8A3F;
	int GL_UNIFORM_BLOCK_DATA_SIZE = 0x8A40;
	int GL_UNIFORM_BLOCK_NAME_LENGTH = 0x8A41;
	int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 0x8A42;
	int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 0x8A43;
	int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 0x8A44;
	int GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 0x8A45;
	int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 0x8A46;

	/** Returned by GetActiveUniformsivARB and GetUniformBlockIndexARB */
	int GL_INVALID_INDEX = 0xFFFFFFFF;

	void glGetUniformIndices(@GLuint int program, @AutoSize("uniformIndices") @GLsizei int uniformCount,
	                         @Const @NullTerminated @GLchar ByteBuffer uniformNames,
	                         @OutParameter @GLuint IntBuffer uniformIndices);

	@StripPostfix("params")
	void glGetActiveUniformsiv(@GLuint int program, @AutoSize("uniformIndices") @GLsizei int uniformCount,
	                           @Const @GLuint IntBuffer uniformIndices,
	                           @GLenum int pname,
	                           @Check(value = "1") @GLint IntBuffer params); // TODO: We need to AutoSize "params" using "uniformCount"

	void glGetActiveUniformName(@GLuint int program, @GLuint int uniformIndex, @AutoSize("uniformName") @GLsizei int bufSize,
	                            @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                            @GLchar ByteBuffer uniformName);

	@GLuint
	int glGetUniformBlockIndex(@GLuint int program, @Const @NullTerminated @GLchar ByteBuffer uniformBlockName);

	@StripPostfix("params")
	void glGetActiveUniformBlockiv(@GLuint int program, @GLuint int uniformBlockIndex, @GLenum int pname,
	                               @OutParameter @Check(value = "16") @GLint IntBuffer params);

	void glGetActiveUniformBlockName(@GLuint int program, @GLuint int uniformBlockIndex, @AutoSize("uniformBlockName") @GLsizei int bufSize,
	                                 @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                                 @GLchar ByteBuffer uniformBlockName);

	void glBindBufferRange(@GLenum int target, @GLuint int index, @GLuint int buffer, @GLintptr long offset, @GLsizeiptr long size);

	void glBindBufferBase(@GLenum int target, @GLuint int index, @GLuint int buffer);

	@StripPostfix(value = "data", extension = "")
	void glGetIntegeri_v(@GLenum int value, @GLuint int index, @OutParameter @Check("4") IntBuffer data);

	void glUniformBlockBinding(@GLuint int program, @GLuint int uniformBlockIndex, @GLuint int uniformBlockBinding);

}