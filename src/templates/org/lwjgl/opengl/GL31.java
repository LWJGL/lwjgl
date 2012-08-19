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
import org.lwjgl.util.generator.Alternate;
import org.lwjgl.util.generator.opengl.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface GL31 {

	// ----------------------------------------------------------
	// ----------------------[ OpenGL 3.1 ]----------------------
	// ----------------------------------------------------------

	int GL_RED_SNORM = 0x8F90;
	int GL_RG_SNORM = 0x8F91;
	int GL_RGB_SNORM = 0x8F92;
	int GL_RGBA_SNORM = 0x8F93;
	int GL_R8_SNORM = 0x8F94;
	int GL_RG8_SNORM = 0x8F95;
	int GL_RGB8_SNORM = 0x8F96;
	int GL_RGBA8_SNORM = 0x8F97;
	int GL_R16_SNORM = 0x8F98;
	int GL_RG16_SNORM = 0x8F99;
	int GL_RGB16_SNORM = 0x8F9A;
	int GL_RGBA16_SNORM = 0x8F9B;
	int GL_SIGNED_NORMALIZED = 0x8F9C;

	// ------------------------------------------------------------------
	// ----------------------[ ARB_draw_instanced ]----------------------
	// ------------------------------------------------------------------

	void glDrawArraysInstanced(@GLenum int mode, int first, @GLsizei int count, @GLsizei int primcount);

	void glDrawElementsInstanced(@GLenum int mode, @AutoSize("indices") @GLsizei int count, @AutoType("indices") @GLenum int type,
	                             @BufferObject(BufferKind.ElementVBO)
	                             @Const
	                             @GLubyte
	                             @GLushort
	                             @GLuint Buffer indices, @GLsizei int primcount);

	// ---------------------------------------------------------------
	// ----------------------[ EXT_copy_buffer ]----------------------
	// ---------------------------------------------------------------

	int GL_COPY_READ_BUFFER_BINDING = 0x8F36;
	int GL_COPY_WRITE_BUFFER_BINDING = 0x8F37;

	int GL_COPY_READ_BUFFER = GL_COPY_READ_BUFFER_BINDING;
	int GL_COPY_WRITE_BUFFER = GL_COPY_WRITE_BUFFER_BINDING;

	void glCopyBufferSubData(@GLenum int readtarget, @GLenum int writetarget, @GLintptr long readoffset, @GLintptr long writeoffset, @GLsizeiptr long size);

	// --------------------------------------------------------------------
	// ----------------------[ NV_primitive_restart ]----------------------
	// --------------------------------------------------------------------

	/**
	 * Accepted by the &lt;cap&gt; parameter of IsEnabled, and by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	int GL_PRIMITIVE_RESTART = 0x8F9D;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_PRIMITIVE_RESTART_INDEX = 0x8F9E;

	void glPrimitiveRestartIndex(@GLuint int index);

	// -------------------------------------------------------------------------
	// ----------------------[ ARB_texture_buffer_object ]----------------------
	// -------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, MapBufferRange, BindTexture, UnmapBuffer,
	 * GetBufferSubData, GetBufferParameteriv, GetBufferPointerv, and TexBuffer,
	 * and the <pname> parameter of GetBooleanv, GetDoublev, GetFloatv, and
	 * GetIntegerv:
	 */
	int GL_TEXTURE_BUFFER = 0x8C2A;

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetBooleanv, GetDoublev,
	 * GetFloatv, and GetIntegerv:
	 */
	int GL_MAX_TEXTURE_BUFFER_SIZE = 0x8C2B;
	int GL_TEXTURE_BINDING_BUFFER = 0x8C2C;
	int GL_TEXTURE_BUFFER_DATA_STORE_BINDING = 0x8C2D;
	int GL_TEXTURE_BUFFER_FORMAT = 0x8C2E;

	void glTexBuffer(@GLenum int target, @GLenum int internalformat, @GLuint int buffer);

	// ---------------------------------------------------------------------
	// ----------------------[ ARB_texture_rectangle ]----------------------
	// ---------------------------------------------------------------------

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable and IsEnabled;
	 * by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv
	 * and GetDoublev; and by the &lt;target&gt; parameter of BindTexture,
	 * GetTexParameterfv, GetTexParameteriv, TexParameterf, TexParameteri,
	 * TexParameterfv and TexParameteriv:
	 * Accepted by the &lt;target&gt; parameter of GetTexImage,
	 * GetTexLevelParameteriv, GetTexLevelParameterfv, TexImage2D,
	 * CopyTexImage2D, TexSubImage2D and CopySubTexImage2D:
	 */
	int GL_TEXTURE_RECTANGLE = 0x84F5;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv and GetDoublev:
	 */
	int GL_TEXTURE_BINDING_RECTANGLE = 0x84F6;

	/**
	 * Accepted by the &lt;target&gt; parameter of GetTexLevelParameteriv,
	 * GetTexLevelParameterfv, GetTexParameteriv and TexImage2D:
	 */
	int GL_PROXY_TEXTURE_RECTANGLE = 0x84F7;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev,
	 * GetIntegerv and GetFloatv:
	 */
	int GL_MAX_RECTANGLE_TEXTURE_SIZE = 0x84F8;

	/**
	 * Returned by &lt;type&gt; parameter of GetActiveUniform when the location
	 * &lt;index&gt; for program object &lt;program&gt; is of type sampler2DRect:
	 */
	int GL_SAMPLER_2D_RECT = 0x8B63;

	/**
	 * Returned by &lt;type&gt; parameter of GetActiveUniform when the location
	 * &lt;index&gt; for program object &lt;program&gt; is of type sampler2DRectShadow:
	 */
	int GL_SAMPLER_2D_RECT_SHADOW = 0x8B64;

	// -------------------------------------------------------------------------
	// ----------------------[ ARB_uniform_buffer_object ]----------------------
	// -------------------------------------------------------------------------

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

	/** Accepted by the &lt;pname&gt; parameter of GetActiveUniformsiv: */
	int GL_UNIFORM_TYPE = 0x8A37;
	int GL_UNIFORM_SIZE = 0x8A38;
	int GL_UNIFORM_NAME_LENGTH = 0x8A39;
	int GL_UNIFORM_BLOCK_INDEX = 0x8A3A;
	int GL_UNIFORM_OFFSET = 0x8A3B;
	int GL_UNIFORM_ARRAY_STRIDE = 0x8A3C;
	int GL_UNIFORM_MATRIX_STRIDE = 0x8A3D;
	int GL_UNIFORM_IS_ROW_MAJOR = 0x8A3E;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveUniformBlockiv: */
	int GL_UNIFORM_BLOCK_BINDING = 0x8A3F;
	int GL_UNIFORM_BLOCK_DATA_SIZE = 0x8A40;
	int GL_UNIFORM_BLOCK_NAME_LENGTH = 0x8A41;
	int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 0x8A42;
	int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 0x8A43;
	int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 0x8A44;
	int GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 0x8A45;
	int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 0x8A46;

	/** Returned by GetActiveUniformsiv and GetUniformBlockIndex */
	int GL_INVALID_INDEX = 0xFFFFFFFF;

	void glGetUniformIndices(@GLuint int program, @AutoSize("uniformIndices") @GLsizei int uniformCount,
	                         @Const @NullTerminated("uniformIndices.remaining()") @GLchar @PointerArray("uniformCount") ByteBuffer uniformNames,
	                         @OutParameter @GLuint IntBuffer uniformIndices);

	@Alternate("glGetUniformIndices")
	void glGetUniformIndices(@GLuint int program, @Constant("uniformNames.length") @GLsizei int uniformCount,
	                         @Const @NullTerminated @PointerArray("uniformCount") CharSequence[] uniformNames,
	                         @OutParameter @Check("uniformNames.length") @GLuint IntBuffer uniformIndices);

	@StripPostfix("params")
	void glGetActiveUniformsiv(@GLuint int program, @AutoSize("uniformIndices") @GLsizei int uniformCount,
	                           @Const @GLuint IntBuffer uniformIndices,
	                           @GLenum int pname,
	                           @OutParameter @Check("uniformIndices.remaining()") @GLint IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetActiveUniformsi} instead. */
	@Alternate("glGetActiveUniformsiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL31", method = "glGetActiveUniformsi")
	@Deprecated
	void glGetActiveUniformsiv(@GLuint int program, @Constant("1") @GLsizei int uniformCount,
	                           @Constant(value = "MemoryUtil.getAddress(params.put(1, uniformIndex), 1)", keepParam = true) int uniformIndex, // Reuse params buffer
	                           @GLenum int pname,
	                           @OutParameter @GLint IntBuffer params);

	@Alternate("glGetActiveUniformsiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetActiveUniformsiv2(@GLuint int program, @Constant("1") @GLsizei int uniformCount,
	                            @Constant(value = "MemoryUtil.getAddress(params.put(1, uniformIndex), 1)", keepParam = true) int uniformIndex, // Reuse params buffer
	                            @GLenum int pname,
	                            @OutParameter @GLint IntBuffer params);

	void glGetActiveUniformName(@GLuint int program, @GLuint int uniformIndex, @AutoSize("uniformName") @GLsizei int bufSize,
	                            @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                            @OutParameter @GLchar ByteBuffer uniformName);

	@Alternate("glGetActiveUniformName")
	@GLreturn(value = "uniformName", maxLength = "bufSize")
	void glGetActiveUniformName2(@GLuint int program, @GLuint int uniformIndex, @GLsizei int bufSize,
	                             @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(uniformName_length)") IntBuffer length,
	                             @OutParameter @GLchar ByteBuffer uniformName);

	@GLuint
	int glGetUniformBlockIndex(@GLuint int program, @Const @NullTerminated @GLchar ByteBuffer uniformBlockName);

	@Alternate("glGetUniformBlockIndex")
	@GLuint
	int glGetUniformBlockIndex(@GLuint int program, @NullTerminated CharSequence uniformBlockName);

	@StripPostfix("params")
	void glGetActiveUniformBlockiv(@GLuint int program, @GLuint int uniformBlockIndex, @GLenum int pname,
	                               @OutParameter @Check(value = "16") @GLint IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetActiveUniformBlocki} instead. */
	@Alternate("glGetActiveUniformBlockiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL31", method = "glGetActiveUniformBlocki")
	@Deprecated
	void glGetActiveUniformBlockiv2(@GLuint int program, @GLuint int uniformBlockIndex, @GLenum int pname,
	                                @OutParameter @GLint IntBuffer params);

	@Alternate("glGetActiveUniformBlockiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetActiveUniformBlockiv3(@GLuint int program, @GLuint int uniformBlockIndex, @GLenum int pname,
	                                @OutParameter @GLint IntBuffer params);

	void glGetActiveUniformBlockName(@GLuint int program, @GLuint int uniformBlockIndex, @AutoSize("uniformBlockName") @GLsizei int bufSize,
	                                 @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                                 @OutParameter @GLchar ByteBuffer uniformBlockName);

	@Alternate("glGetActiveUniformBlockName")
	@GLreturn(value = "uniformBlockName", maxLength = "bufSize")
	void glGetActiveUniformBlockName2(@GLuint int program, @GLuint int uniformBlockIndex, @GLsizei int bufSize,
	                                  @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(uniformBlockName_length)") IntBuffer length,
	                                  @OutParameter @GLchar ByteBuffer uniformBlockName);

	void glUniformBlockBinding(@GLuint int program, @GLuint int uniformBlockIndex, @GLuint int uniformBlockBinding);

}