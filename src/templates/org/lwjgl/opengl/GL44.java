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

import org.lwjgl.PointerBuffer;
import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opengl.*;

import java.nio.Buffer;
import java.nio.IntBuffer;

public interface GL44 {

	/** Implementation-dependent state which constrains the maximum value of stride parameters to vertex array pointer-setting commands. */
	int GL_MAX_VERTEX_ATTRIB_STRIDE = 0x82E5;

	// ------------------------------------------------------------------
	// ----------------------[ ARB_buffer_storage ]----------------------
	// ------------------------------------------------------------------

	/**
	 * Accepted in the &lt;flags&gt; parameter of BufferStorage and
	 * NamedBufferStorageEXT:
	 */
	int GL_MAP_PERSISTENT_BIT  = 0x0040,
		GL_MAP_COHERENT_BIT    = 0x0080,
		GL_DYNAMIC_STORAGE_BIT = 0x0100,
		GL_CLIENT_STORAGE_BIT  = 0x0200;

	/** Accepted by the &lt;pname&gt; parameter of GetBufferParameter{i|i64}v:\ */

	int GL_BUFFER_IMMUTABLE_STORAGE = 0x821F,
		GL_BUFFER_STORAGE_FLAGS     = 0x8220;

	/** Accepted by the &lt;barriers&gt; parameter of MemoryBarrier: */
	int GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT = 0x00004000;

	void glBufferStorage(@GLenum int target,
	                     @AutoSize("data") @GLsizeiptr long size,
	                     @Const
	                     @GLbyte
	                     @GLshort
	                     @GLint
	                     @GLuint64
	                     @GLfloat
	                     @GLdouble Buffer data,
	                     @GLbitfield int flags);

	@Alternate("glBufferStorage")
	void glBufferStorage2(@GLenum int target,
	                      @GLsizeiptr long size,
	                      @Constant("0L") @Const Buffer data,
	                      @GLbitfield int flags);

	// -----------------------------------------------------------------
	// ----------------------[ ARB_clear_texture ]----------------------
	// -----------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter for GetInternalformativ and
	 * GetInternalformati64v:
	 */
	int GL_CLEAR_TEXTURE = 0x9365;

	void glClearTexImage(@GLuint int texture, int level,
	                     @GLenum int format, @GLenum int type,
	                     @Check(value = "1", canBeNull = true)
	                     @Const
	                     @GLbyte
	                     @GLshort
	                     @GLint
	                     @GLuint64
	                     @GLfloat
	                     @GLdouble Buffer data);

	void glClearTexSubImage(@GLuint int texture, int level,
	                        int xoffset, int yoffset, int zoffset,
	                        @GLsizei int width, @GLsizei int height, @GLsizei int depth,
	                        @GLenum int format, @GLenum int type,
	                        @Check(value = "1", canBeNull = true)
	                        @Const
	                        @GLbyte
	                        @GLshort
	                        @GLint
	                        @GLuint64
	                        @GLfloat
	                        @GLdouble Buffer data);

	// --------------------------------------------------------------------
	// ----------------------[ ARB_enhanced_layouts ]----------------------
	// --------------------------------------------------------------------

	/** Accepted in the &lt;props&gt; array of GetProgramResourceiv: */
	int GL_LOCATION_COMPONENT               = 0x934A,
		GL_TRANSFORM_FEEDBACK_BUFFER_INDEX  = 0x934B,
		GL_TRANSFORM_FEEDBACK_BUFFER_STRIDE = 0x934C;

	// --------------------------------------------------------------
	// ----------------------[ ARB_multi_bind ]----------------------
	// --------------------------------------------------------------

	void glBindBuffersBase(@GLenum int target, @GLuint int first, @GLsizei int count, @Check(value = "count", canBeNull = true) @Const @GLuint IntBuffer buffers);

	void glBindBuffersRange(@GLenum int target, @GLuint int first, @GLsizei int count,
	                        @Check(value = "count", canBeNull = true) @Const @GLuint IntBuffer buffers,
	                        @Check(value = "count", canBeNull = true) @Const @GLintptr PointerBuffer offsets,
	                        @Check(value = "count", canBeNull = true) @Const @GLsizeiptr PointerBuffer sizes);

	void glBindTextures(@GLuint int first, @GLsizei int count, @Check(value = "count", canBeNull = true) @Const @GLuint IntBuffer textures);

	void glBindSamplers(@GLuint int first, @GLsizei int count, @Check(value = "count", canBeNull = true) @Const @GLuint IntBuffer samplers);

	void glBindImageTextures(@GLuint int first, @GLsizei int count, @Check(value = "count", canBeNull = true) @Const @GLuint IntBuffer textures);

	void glBindVertexBuffers(@GLuint int first, @GLsizei int count,
	                         @Check(value = "count", canBeNull = true) @Const @GLuint IntBuffer buffers,
	                         @Check(value = "count", canBeNull = true) @Const @GLintptr PointerBuffer offsets,
	                         @Check(value = "count", canBeNull = true) @Const @GLsizei IntBuffer strides);

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_query_buffer_object ]----------------------
	// -----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetQueryObjectiv, GetQueryObjectuiv,
	 * GetQueryObjecti64v and GetQueryObjectui64v:
	 */
	int GL_QUERY_RESULT_NO_WAIT = 0x9194;

	/**
	 * Accepted by the &lt;target&gt; parameter of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, MapBufferRange, GetBufferSubData,
	 * GetBufferParameteriv, GetBufferParameteri64v, GetBufferPointerv,
	 * ClearBufferSubData, and the &lt;readtarget&gt; and &lt;writetarget&gt; parameters of
	 * CopyBufferSubData:
	 */
	int GL_QUERY_BUFFER = 0x9192;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_QUERY_BUFFER_BINDING = 0x9193;

	/** Accepted in the &lt;barriers&gt; bitfield in MemoryBarrier: */
	int GL_QUERY_BUFFER_BARRIER_BIT = 0x00008000;

	// --------------------------------------------------------------------------------
	// ----------------------[ ARB_texture_mirror_clamp_to_edge ]----------------------
	// --------------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;param&gt; parameter of TexParameter{if}, SamplerParameter{if}
	 * and SamplerParameter{if}v, and by the &lt;params&gt; parameter of
	 * TexParameter{if}v, TexParameterI{i ui}v and SamplerParameterI{i ui}v when
	 * their &lt;pname&gt; parameter is TEXTURE_WRAP_S, TEXTURE_WRAP_T, or
	 * TEXTURE_WRAP_R:
	 */
	int GL_MIRROR_CLAMP_TO_EDGE = 0x8743;

}