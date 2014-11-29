/*
 * Copyright (c) 2002-2014 LWJGL Project
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

import java.nio.*;

public interface GL45 {

	// ----------------------------------------------------------------
	// ----------------------[ ARB_clip_control ]----------------------
	// ----------------------------------------------------------------

	/** Accepted by the &lt;depth&gt; parameter of ClipControl: */
	int GL_NEGATIVE_ONE_TO_ONE = 0x935E,
		GL_ZERO_TO_ONE         = 0x935F;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_CLIP_ORIGIN     = 0x935C,
		GL_CLIP_DEPTH_MODE = 0x935D;

	void glClipControl(@GLenum int origin, @GLenum int depth);

	// -------------------------------------------------------------------------------
	// ----------------------[ ARB_conditional_render_inverted ]----------------------
	// -------------------------------------------------------------------------------

	/** Accepted by the &lt;mode&gt; parameter of BeginConditionalRender: */
	int GL_QUERY_WAIT_INVERTED              = 0x8E17,
		GL_QUERY_NO_WAIT_INVERTED           = 0x8E18,
		GL_QUERY_BY_REGION_WAIT_INVERTED    = 0x8E19,
		GL_QUERY_BY_REGION_NO_WAIT_INVERTED = 0x8E1A;

	// -----------------------------------------------------------------
	// ----------------------[ ARB_cull_distance ]----------------------
	// -----------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetBooeleanv, GetDoublev, GetFloatv GetIntegerv, and GetInteger64v: */
	int GL_MAX_CULL_DISTANCES                   = 0x82F9,
		GL_MAX_COMBINED_CLIP_AND_CULL_DISTANCES = 0x82FA;

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_direct_state_access ]----------------------
	// -----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetTextureParameter{if}v and
	 * GetTextureParameterI{i ui}v:
	 */
	int GL_TEXTURE_TARGET = 0x1006;

	/** Accepted by the &lt;pname&gt; parameter of GetQueryObjectiv: */
	int GL_QUERY_TARGET = 0x82EA;

	/** Accepted by the &lt;pname&gt; parameter of GetIntegeri_v: */
	int GL_TEXTURE_BINDING = 0x82EB;

	// Transform Feedback object functions

	void glCreateTransformFeedbacks(@AutoSize("ids") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	@Alternate("glCreateTransformFeedbacks")
	@GLreturn("ids")
	void glCreateTransformFeedbacks2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	void glTransformFeedbackBufferBase(@GLuint int xfb, @GLuint int index, @GLuint int buffer);

	void glTransformFeedbackBufferRange(@GLuint int xfb, @GLuint int index, @GLuint int buffer, @GLintptr long offset, @GLsizeiptr long size);

	@StripPostfix("param")
	void glGetTransformFeedbackiv(@GLuint int xfb, @GLenum int pname, @OutParameter @Check("1") IntBuffer param);

	@Alternate("glGetTransformFeedbackiv")
	@GLreturn("param")
	@StripPostfix(value = "param", hasPostfix = false)
	void glGetTransformFeedbackiv2(@GLuint int xfb, @GLenum int pname, @OutParameter IntBuffer param);

	@StripPostfix("param")
	void glGetTransformFeedbacki_v(@GLuint int xfb, @GLenum int pname, @GLuint int index, @OutParameter @Check("1") IntBuffer param);

	@Alternate("glGetTransformFeedbacki_v")
	@GLreturn("param")
	@StripPostfix(value = "param", postfix = "_v")
	void glGetTransformFeedbacki_v2(@GLuint int xfb, @GLenum int pname, @GLuint int index, @OutParameter IntBuffer param);

	@StripPostfix("param")
	void glGetTransformFeedbacki64_v(@GLuint int xfb, @GLenum int pname, @GLuint int index, @OutParameter @Check("1") @GLint64 LongBuffer param);

	@Alternate("glGetTransformFeedbacki64_v")
	@GLreturn("param")
	@StripPostfix(value = "param", postfix = "_v")
	void glGetTransformFeedbacki64_v2(@GLuint int xfb, @GLenum int pname, @GLuint int index, @OutParameter @GLint64 LongBuffer param);

	// Buffer object functions

	void glCreateBuffers(@AutoSize("buffers") @GLsizei int n, @OutParameter @GLuint IntBuffer buffers);

	@Alternate("glCreateBuffers")
	@GLreturn("buffers")
	void glCreateBuffers2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer buffers);

	void glNamedBufferStorage(@GLuint int buffer, @AutoSize("data") @GLsizeiptr long size,
		@Const
		@GLbyte
		@GLshort
		@GLint
		@GLuint64
		@GLfloat
		@GLdouble Buffer data,
		@GLbitfield int flags);

	@Alternate("glNamedBufferStorage")
	void glNamedBufferStorage2(@GLuint int buffer, @GLsizeiptr long size, @Constant("0L") @Const Buffer data, @GLbitfield int flags);

	@GenerateAutos
	void glNamedBufferData(@GLuint int buffer, @AutoSize("data") @GLsizeiptr long size,
		@Check
		@Const
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer data,
		@GLenum int usage);

	void glNamedBufferSubData(@GLuint int buffer, @GLintptr long offset, @AutoSize("data") @GLsizeiptr long size,
		@Check
		@Const
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer data);

	void glCopyNamedBufferSubData(@GLuint int readBuffer, @GLuint int writeBuffer, @GLintptr long readOffset, @GLintptr long writeOffset, @GLsizeiptr long size);

	void glClearNamedBufferData(@GLuint int buffer, @GLenum int internalformat, @GLenum int format, @GLenum int type, @Check("1") @Const @GLvoid ByteBuffer data);

	void glClearNamedBufferSubData(@GLuint int buffer, @GLenum int internalformat, @GLintptr long offset, @GLsizeiptr long size, @GLenum int format, @GLenum int type, @Check("1") @Const @GLvoid ByteBuffer data);

	/**
	 * Maps a buffer object's data store.
	 * <p/>
	 * <b>LWJGL note</b>: This method comes in 2 flavors:
	 * <ol>
	 * <li>{@link #glMapNamedBuffer(int, int, ByteBuffer)} - Calls {@link #glGetNamedBufferParameteri} to retrieve the buffer size and the {@code old_buffer} parameter is reused if the returned size and pointer match the buffer capacity and address, respectively.</li>
	 * <li>{@link #glMapNamedBuffer(int, int, int, ByteBuffer)} - The buffer size is explicitly specified and the {@code old_buffer} parameter is reused if {@code size} and the returned pointer match the buffer capacity and address, respectively. This is the most efficient method.</li>
	 * </ol>
	 *
	 * @param buffer the buffer object being mapped
	 * @param access the access policy, indicating whether it will be possible to read from, write to, or both read from and write to the buffer object's mapped data store
	 */
	@CachedResult
	@GLvoid
	@AutoSize("glGetNamedBufferParameteri(buffer, GL15.GL_BUFFER_SIZE)")
	ByteBuffer glMapNamedBuffer(@GLuint int buffer, @GLenum int access);

	@CachedResult(isRange = true)
	@GLvoid
	@AutoSize("length")
	ByteBuffer glMapNamedBufferRange(@GLuint int buffer, @GLintptr long offset, @GLsizeiptr long length, @GLbitfield int access);

	boolean glUnmapNamedBuffer(@GLuint int buffer);

	void glFlushMappedNamedBufferRange(@GLuint int buffer, @GLintptr long offset, @GLsizeiptr long length);

	@StripPostfix("params")
	void glGetNamedBufferParameteriv(@GLuint int buffer, @GLenum int pname, @OutParameter @Check IntBuffer params);

	@Alternate("glGetNamedBufferParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetNamedBufferParameteriv2(@GLuint int buffer, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetNamedBufferParameteri64v(@GLuint int buffer, @GLenum int pname, @OutParameter @Check("1") @GLint64 LongBuffer params);

	@Alternate("glGetNamedBufferParameteri64v")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetNamedBufferParameteri64v2(@GLuint int buffer, @GLenum int pname, @OutParameter @GLint64 LongBuffer params);

	@StripPostfix("params")
	@AutoSize("glGetNamedBufferParameteri(buffer, GL15.GL_BUFFER_SIZE)")
	void glGetNamedBufferPointerv(@GLuint int buffer, @GLenum int pname, @Result @GLvoid ByteBuffer params);

	void glGetNamedBufferSubData(@GLuint int buffer, @GLintptr long offset, @AutoSize("data") @GLsizeiptr long size,
		@OutParameter
		@Check
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer data);

	// Framebuffer object functions

	void glCreateFramebuffers(@AutoSize("framebuffers") @GLsizei int n, @OutParameter @GLuint IntBuffer framebuffers);

	@Alternate("glCreateFramebuffers")
	@GLreturn("framebuffers")
	void glCreateFramebuffers2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer framebuffers);

	void glNamedFramebufferRenderbuffer(@GLuint int framebuffer, @GLenum int attachment, @GLenum int renderbuffertarget, @GLuint int renderbuffer);

	void glNamedFramebufferParameteri(@GLuint int framebuffer, @GLenum int pname, int param);

	void glNamedFramebufferTexture(@GLuint int framebuffer, @GLenum int attachment, @GLuint int texture, int level);

	void glNamedFramebufferTextureLayer(@GLuint int framebuffer, @GLenum int attachment, @GLuint int texture, int level, int layer);

	void glNamedFramebufferDrawBuffer(@GLuint int framebuffer, @GLenum int mode);

	void glNamedFramebufferDrawBuffers(@GLuint int framebuffer, @AutoSize("bufs") @GLsizei int n, @Const @GLenum IntBuffer bufs);

	void glNamedFramebufferReadBuffer(@GLuint int framebuffer, @GLenum int mode);

	void glInvalidateNamedFramebufferData(@GLuint int framebuffer, @AutoSize("attachments") @GLsizei int numAttachments, @Const @GLenum IntBuffer attachments);

	void glInvalidateNamedFramebufferSubData(@GLuint int framebuffer, @AutoSize("attachments") @GLsizei int numAttachments, @Const @GLenum IntBuffer attachments, int x, int y, @GLsizei int width, @GLsizei int height);

	@StripPostfix("value")
	void glClearNamedFramebufferiv(@GLuint int framebuffer, @GLenum int buffer, int drawbuffer, @Const @Check("1") IntBuffer value);

	@StripPostfix("value")
	void glClearNamedFramebufferuiv(@GLuint int framebuffer, @GLenum int buffer, int drawbuffer, @Const @Check("4") @GLuint IntBuffer value);

	@StripPostfix("value")
	void glClearNamedFramebufferfv(@GLuint int framebuffer, @GLenum int buffer, int drawbuffer, @Const @Check("1") FloatBuffer value);

	void glClearNamedFramebufferfi(@GLuint int framebuffer, @GLenum int buffer, float depth, int stencil);

	void glBlitNamedFramebuffer(
		@GLuint int readFramebuffer, @GLuint int drawFramebuffer,
		int srcX0, int srcY0, int srcX1, int srcY1,
		int dstX0, int dstY0, int dstX1, int dstY1,
		@GLbitfield int mask, @GLenum int filter);

	@GLenum
	int glCheckNamedFramebufferStatus(@GLuint int framebuffer, @GLenum int target);

	@StripPostfix("params")
	void glGetNamedFramebufferParameteriv(@GLuint int framebuffer, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetNamedFramebufferParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetNamedFramebufferParameteriv2(@GLuint int framebuffer, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetNamedFramebufferAttachmentParameteriv(@GLuint int framebuffer, @GLenum int attachment, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetNamedFramebufferAttachmentParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetNamedFramebufferAttachmentParameteriv2(@GLuint int framebuffer, @GLenum int attachment, @GLenum int pname, @OutParameter IntBuffer params);

	// Renderbuffer object functions

	void glCreateRenderbuffers(@AutoSize("renderbuffers") @GLsizei int n, @OutParameter @GLuint IntBuffer renderbuffers);

	@Alternate("glCreateRenderbuffers")
	@GLreturn("renderbuffers")
	void glCreateRenderbuffers2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer renderbuffers);

	void glNamedRenderbufferStorage(@GLuint int renderbuffer, @GLenum int internalformat, @GLsizei int width, @GLsizei int height);

	void glNamedRenderbufferStorageMultisample(@GLuint int renderbuffer, @GLsizei int samples, @GLenum int internalformat, @GLsizei int width, @GLsizei int height);

	@StripPostfix("params")
	void glGetNamedRenderbufferParameteriv(@GLuint int renderbuffer, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetNamedRenderbufferParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetNamedRenderbufferParameteriv2(@GLuint int renderbuffer, @GLenum int pname, @OutParameter IntBuffer params);

	// Texture object functions

	void glCreateTextures(@GLenum int target, @AutoSize("textures") @GLsizei int n, @OutParameter @GLuint IntBuffer textures);

	@Alternate("glCreateTextures")
	@GLreturn("textures")
	void glCreateTextures2(@GLenum int target, @Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer textures);

	void glTextureBuffer(@GLuint int texture, @GLenum int internalformat, @GLuint int buffer);

	void glTextureBufferRange(@GLuint int texture, @GLenum int internalformat, @GLuint int buffer, @GLintptr long offset, @GLsizeiptr long size);

	void glTextureStorage1D(@GLuint int texture, @GLsizei int levels, @GLenum int internalformat, @GLsizei int width);

	void glTextureStorage2D(@GLuint int texture, @GLsizei int levels, @GLenum int internalformat, @GLsizei int width, @GLsizei int height);

	void glTextureStorage3D(@GLuint int texture, @GLsizei int levels, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, @GLsizei int depth);

	void glTextureStorage2DMultisample(@GLuint int texture, @GLsizei int samples, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, boolean fixedsamplelocations);

	void glTextureStorage3DMultisample(@GLuint int texture, @GLsizei int samples, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, @GLsizei int depth, boolean fixedsamplelocations);

	void glTextureSubImage1D(@GLuint int texture, int level, int xoffset, @GLsizei int width, @GLenum int format, @GLenum int type,
		@BufferObject(BufferKind.UnpackPBO)
		@Check("GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1)")
		@Const
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer pixels);

	void glTextureSubImage2D(@GLuint int texture, int level, int xoffset, int yoffset, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
		@BufferObject(BufferKind.UnpackPBO)
		@Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, 1)")
		@Const
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer pixels);

	void glTextureSubImage3D(@GLuint int texture, int level, int xoffset, int yoffset, int zoffset, @GLsizei int width, @GLsizei int height, @GLsizei int depth, @GLenum int format, @GLenum int type,
		@BufferObject(BufferKind.UnpackPBO)
		@Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, depth)")
		@Const
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer pixels);

	void glCompressedTextureSubImage1D(@GLuint int texture, int level, int xoffset, @GLsizei int width, @GLenum int format, @AutoSize("data") @GLsizei int imageSize,
		@BufferObject(BufferKind.UnpackPBO)
		@Check
		@Const
		@GLvoid
		ByteBuffer data);

	void glCompressedTextureSubImage2D(@GLuint int texture, int level, int xoffset, int yoffset, @GLsizei int width, @GLsizei int height, @GLenum int format, @AutoSize("data") @GLsizei int imageSize,
		@BufferObject(BufferKind.UnpackPBO)
		@Check
		@Const
		@GLvoid
		ByteBuffer data);

	void glCompressedTextureSubImage3D(@GLuint int texture, int level, int xoffset, int yoffset, int zoffset, @GLsizei int width, @GLsizei int height, @GLsizei int depth, @GLenum int format, @GLsizei int imageSize,
		@BufferObject(BufferKind.UnpackPBO)
		@Check
		@Const
		@GLvoid
		ByteBuffer data);

	void glCopyTextureSubImage1D(@GLuint int texture, int level, int xoffset, int x, int y, @GLsizei int width);

	void glCopyTextureSubImage2D(@GLuint int texture, int level, int xoffset, int yoffset, int x, int y, @GLsizei int width, @GLsizei int height);

	void glCopyTextureSubImage3D(@GLuint int texture, int level, int xoffset, int yoffset, int zoffset, int x, int y, @GLsizei int width, @GLsizei int height);

	void glTextureParameterf(@GLuint int texture, @GLenum int pname, float param);

	@StripPostfix("params")
	void glTextureParameterfv(@GLuint int texture, @GLenum int pname, @Const @Check("4") FloatBuffer params);

	void glTextureParameteri(@GLuint int texture, @GLenum int pname, int param);

	@StripPostfix("params")
	void glTextureParameterIiv(@GLuint int texture, @GLenum int pname, @Const @Check("1") IntBuffer params);

	@StripPostfix("params")
	void glTextureParameterIuiv(@GLuint int texture, @GLenum int pname, @Const @Check("1") @GLuint IntBuffer params);

	@StripPostfix("params")
	void glTextureParameteriv(@GLuint int texture, @GLenum int pname, @Const @Check("4") IntBuffer params);

	void glGenerateTextureMipmap(@GLuint int texture);

	void glBindTextureUnit(@GLuint int unit, @GLuint int texture);

	void glGetTextureImage(@GLuint int texture, int level, @GLenum int format, @GLenum int type, @AutoSize("pixels") @GLsizei int bufSize,
		@OutParameter
		@BufferObject(BufferKind.PackPBO)
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer pixels);

	void glGetCompressedTextureImage(@GLuint int texture, int level, @AutoSize("pixels") @GLsizei int bufSize,
		@OutParameter
		@BufferObject(BufferKind.PackPBO)
		@Check
		@GLbyte
		@GLshort
		@GLint Buffer pixels);

	@StripPostfix("params")
	void glGetTextureLevelParameterfv(@GLuint int texture, int level, @GLenum int pname, @OutParameter @Check("1") FloatBuffer params);

	@Alternate("glGetTextureLevelParameterfv")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetTextureLevelParameterfv2(@GLuint int texture, int level, @GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetTextureLevelParameteriv(@GLuint int texture, int level, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetTextureLevelParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetTextureLevelParameteriv2(@GLuint int texture, int level, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetTextureParameterfv(@GLuint int texture, @GLenum int pname, @OutParameter @Check("1") FloatBuffer params);

	@Alternate("glGetTextureParameterfv")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetTextureParameterfv2(@GLuint int texture, @GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetTextureParameterIiv(@GLuint int texture, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetTextureParameterIiv")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetTextureParameterIiv2(@GLuint int texture, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetTextureParameterIuiv(@GLuint int texture, @GLenum int pname, @OutParameter @Check("1") @GLuint IntBuffer params);

	@Alternate("glGetTextureParameterIuiv")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetTextureParameterIuiv2(@GLuint int texture, @GLenum int pname, @OutParameter @GLuint IntBuffer params);

	@StripPostfix("params")
	void glGetTextureParameteriv(@GLuint int texture, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetTextureParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", hasPostfix = false)
	void glGetTextureParameteriv2(@GLuint int texture, @GLenum int pname, @OutParameter IntBuffer params);

	// Vertex Array object functions

	void glCreateVertexArrays(@AutoSize("arrays") @GLsizei int n, @OutParameter @GLuint IntBuffer arrays);

	@Alternate("glCreateVertexArrays")
	@GLreturn("arrays")
	void glCreateVertexArrays2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer arrays);

	void glDisableVertexArrayAttrib(@GLuint int vaobj, @GLuint int index);

	void glEnableVertexArrayAttrib(@GLuint int vaobj, @GLuint int index);

	void glVertexArrayElementBuffer(@GLuint int vaobj, @GLuint int buffer);

	void glVertexArrayVertexBuffer(@GLuint int vaobj, @GLuint int bindingindex, @GLuint int buffer, @GLintptr long offset, @GLsizei int stride);

	void glVertexArrayVertexBuffers(@GLuint int vaobj, @GLuint int first, @GLsizei int count,
		@Check(value = "count", canBeNull = true) @Const @GLuint IntBuffer buffers,
		@Check(value = "count", canBeNull = true) @Const @GLintptr PointerBuffer offsets,
		@Check(value = "count", canBeNull = true) @Const @GLsizei IntBuffer strides);

	void glVertexArrayAttribFormat(@GLuint int vaobj, @GLuint int attribindex, int size, @GLenum int type, boolean normalized, @GLuint int relativeoffset);

	void glVertexArrayAttribIFormat(@GLuint int vaobj, @GLuint int attribindex, int size, @GLenum int type, @GLuint int relativeoffset);

	void glVertexArrayAttribLFormat(@GLuint int vaobj, @GLuint int attribindex, int size, @GLenum int type, @GLuint int relativeoffset);

	void glVertexArrayAttribBinding(@GLuint int vaobj, @GLuint int attribindex, @GLuint int bindingindex);

	void glVertexArrayBindingDivisor(@GLuint int vaobj, @GLuint int bindingindex, @GLuint int divisor);

	@StripPostfix("param")
	void glGetVertexArrayiv(@GLuint int vaobj, @GLenum int pname, @OutParameter @Check("1") IntBuffer param);

	@Alternate("glGetVertexArrayiv")
	@GLreturn("param")
	@StripPostfix("param")
	void glGetVertexArrayiv2(@GLuint int vaobj, @GLenum int pname, @OutParameter IntBuffer param);

	@StripPostfix("param")
	void glGetVertexArrayIndexediv(@GLuint int vaobj, @GLuint int index, @GLenum int pname, @OutParameter @Check("1") IntBuffer param);

	@Alternate("glGetVertexArrayIndexediv")
	@GLreturn("param")
	@StripPostfix("param")
	void glGetVertexArrayIndexediv2(@GLuint int vaobj, @GLuint int index, @GLenum int pname, @OutParameter IntBuffer param);

	@StripPostfix("param")
	void glGetVertexArrayIndexed64iv(@GLuint int vaobj, @GLuint int index, @GLenum int pname, @OutParameter @Check("1") @GLint64 LongBuffer param);

	@Alternate("glGetVertexArrayIndexed64iv")
	@GLreturn("param")
	@StripPostfix("param")
	void glGetVertexArrayIndexed64iv2(@GLuint int vaobj, @GLuint int index, @GLenum int pname, @OutParameter @GLint64 LongBuffer param);

	// Sampler object functions

	void glCreateSamplers(@AutoSize("samplers") @GLsizei int n, @OutParameter @GLuint IntBuffer samplers);

	@Alternate("glCreateSamplers")
	@GLreturn("samplers")
	void glCreateSamplers2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer samplers);

	// Program Pipeline object functions

	void glCreateProgramPipelines(@AutoSize("pipelines") @GLsizei int n, @OutParameter @GLuint IntBuffer pipelines);

	@Alternate("glCreateProgramPipelines")
	@GLreturn("pipelines")
	void glCreateProgramPipelines2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer pipelines);

	// Query object functions

	void glCreateQueries(@GLenum int target, @AutoSize("ids") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	@Alternate("glCreateQueries")
	@GLreturn("ids")
	void glCreateQueries2(@GLenum int target, @Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_ES3_1_compatibility ]----------------------
	// -----------------------------------------------------------------------

	void glMemoryBarrierByRegion(@GLbitfield int barriers);

	// -------------------------------------------------------------------------
	// ----------------------[ ARB_get_texture_sub_image ]----------------------
	// -------------------------------------------------------------------------

	void glGetTextureSubImage(
		@GLuint int texture, int level, int xoffset, int yoffset, int zoffset,
		@GLsizei int width, @GLsizei int height, @GLsizei int depth,
		@GLenum int format, @GLenum int type,
		@AutoSize("pixels") @GLsizei int bufSize,
		@OutParameter
		@BufferObject(BufferKind.PackPBO)
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer pixels
	);

	void glGetCompressedTextureSubImage(
		@GLuint int texture, int level, int xoffset, int yoffset, int zoffset,
		@GLsizei int width, @GLsizei int height, @GLsizei int depth,
		@AutoSize("pixels") @GLsizei int bufSize,
		@OutParameter
		@BufferObject(BufferKind.PackPBO)
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer pixels
	);

	// -------------------------------------------------------------------
	// ----------------------[ ARB_texture_barrier ]----------------------
	// -------------------------------------------------------------------

	void glTextureBarrier();

	// -------------------------------------------------------------------------
	// ----------------------[ KHR_context_flush_control ]----------------------
	// -------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegerv, GetFloatv, GetBooleanv
	 * GetDoublev and GetInteger64v:
	 */
	int GL_CONTEXT_RELEASE_BEHAVIOR = 0x82FB;

	/**
	 * Returned in &lt;data&gt; by GetIntegerv, GetFloatv, GetBooleanv
	 * GetDoublev and GetInteger64v when &lt;pname&gt; is
	 * GL_CONTEXT_RELEASE_BEHAVIOR:
	 */
	int GL_CONTEXT_RELEASE_BEHAVIOR_FLUSH = 0x82FC;

	// --------------------------------------------------------------
	// ----------------------[ KHR_robustness ]----------------------
	// --------------------------------------------------------------

	/** Returned by GetGraphicsResetStatus: */
	int GL_GUILTY_CONTEXT_RESET   = 0x8253,
		GL_INNOCENT_CONTEXT_RESET = 0x8254,
		GL_UNKNOWN_CONTEXT_RESET  = 0x8255;

	/**
	 * Accepted by the &lt;value&gt; parameter of GetBooleanv, GetIntegerv,
	 * and GetFloatv:
	 */
	int GL_CONTEXT_ROBUST_ACCESS       = 0x90F3,
		GL_RESET_NOTIFICATION_STRATEGY = 0x8256;

	/**
	 * Returned by GetIntegerv and related simple queries when &lt;value&gt; is
	 * RESET_NOTIFICATION_STRATEGY:
	 */
	int GL_LOSE_CONTEXT_ON_RESET = 0x8252,
		GL_NO_RESET_NOTIFICATION = 0x8261;

	/** Returned by GetError: */
	int GL_CONTEXT_LOST = 0x0507;

	@GLenum
	int glGetGraphicsResetStatus();

	void glReadnPixels(int x, int y, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type, @AutoSize("pixels") @GLsizei int bufSize,
		@OutParameter
		@BufferObject(BufferKind.PackPBO)
		@GLbyte
		@GLshort
		@GLint
		@GLfloat
		@GLdouble Buffer pixels);

	@StripPostfix("params")
	void glGetnUniformfv(@GLuint int program, int location, @AutoSize("params") @GLsizei int bufSize, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetnUniformiv(@GLuint int program, int location, @AutoSize("params") @GLsizei int bufSize, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetnUniformuiv(@GLuint int program, int location, @AutoSize("params") @GLsizei int bufSize, @OutParameter @GLuint IntBuffer params);

}