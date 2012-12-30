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

import java.nio.*;

public interface GL30 {

	// ----------------------------------------------------------
	// ----------------------[ OpenGL 3.0 ]----------------------
	// ----------------------------------------------------------

	int GL_MAJOR_VERSION = 0x821B;
	int GL_MINOR_VERSION = 0x821C;
	int GL_NUM_EXTENSIONS = 0x821D;

	int GL_CONTEXT_FLAGS = 0x821E;
	int GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT = 0x0001;

	int GL_DEPTH_BUFFER = 0x8223;
	int GL_STENCIL_BUFFER = 0x8224;

	int GL_COMPRESSED_RED = 0x8225;
	int GL_COMPRESSED_RG = 0x8226;

	int GL_COMPARE_REF_TO_TEXTURE = ARB_shadow.GL_COMPARE_R_TO_TEXTURE_ARB;

	int GL_CLIP_DISTANCE0 = GL11.GL_CLIP_PLANE0;
	int GL_CLIP_DISTANCE1 = GL11.GL_CLIP_PLANE1;
	int GL_CLIP_DISTANCE2 = GL11.GL_CLIP_PLANE2;
	int GL_CLIP_DISTANCE3 = GL11.GL_CLIP_PLANE3;
	int GL_CLIP_DISTANCE4 = GL11.GL_CLIP_PLANE4;
	int GL_CLIP_DISTANCE5 = GL11.GL_CLIP_PLANE5;
	int GL_CLIP_DISTANCE6 = 0x3006;
	int GL_CLIP_DISTANCE7 = 0x3007;

	int GL_MAX_CLIP_DISTANCES = GL11.GL_MAX_CLIP_PLANES;

	int GL_MAX_VARYING_COMPONENTS = GL20.GL_MAX_VARYING_FLOATS;

	int GL_BUFFER_ACCESS_FLAGS = 0x911F;
	int GL_BUFFER_MAP_LENGTH = 0x9120;
	int GL_BUFFER_MAP_OFFSET = 0x9121;

	String glGetStringi(@GLenum int name, @GLuint int index);

	@StripPostfix("value")
	void glClearBufferfv(@GLenum int buffer, int drawbuffer, @Const @Check("4") FloatBuffer value);

	@StripPostfix("value")
	void glClearBufferiv(@GLenum int buffer, int drawbuffer, @Const @Check("4") IntBuffer value);

	@StripPostfix("value")
	void glClearBufferuiv(@GLenum int buffer, int drawbuffer, @Const @Check("4") IntBuffer value);

	void glClearBufferfi(@GLenum int buffer, int drawbuffer, float depth, int stencil);

	// ---------------------------------------------------------------
	// ----------------------[ EXT_gpu_shader4 ]----------------------
	// ---------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetVertexAttribdv,
	 * GetVertexAttribfv, GetVertexAttribiv, GetVertexAttribIiv, and
	 * GetVertexAttribIuiv:
	 */
	int GL_VERTEX_ATTRIB_ARRAY_INTEGER = 0x88FD;

	/** Returned by the &lt;type&gt; parameter of GetActiveUniform: */

	int GL_SAMPLER_BUFFER = 0x8DC2;
	int GL_SAMPLER_CUBE_SHADOW = 0x8DC5;
	int GL_UNSIGNED_INT_VEC2 = 0x8DC6;
	int GL_UNSIGNED_INT_VEC3 = 0x8DC7;
	int GL_UNSIGNED_INT_VEC4 = 0x8DC8;
	int GL_INT_SAMPLER_1D = 0x8DC9;
	int GL_INT_SAMPLER_2D = 0x8DCA;
	int GL_INT_SAMPLER_3D = 0x8DCB;
	int GL_INT_SAMPLER_CUBE = 0x8DCC;
	int GL_INT_SAMPLER_2D_RECT = 0x8DCD;
	int GL_INT_SAMPLER_1D_ARRAY = 0x8DCE;
	int GL_INT_SAMPLER_2D_ARRAY = 0x8DCF;
	int GL_INT_SAMPLER_BUFFER = 0x8DD0;

	int GL_UNSIGNED_INT_SAMPLER_1D = 0x8DD1;
	int GL_UNSIGNED_INT_SAMPLER_2D = 0x8DD2;
	int GL_UNSIGNED_INT_SAMPLER_3D = 0x8DD3;
	int GL_UNSIGNED_INT_SAMPLER_CUBE = 0x8DD4;
	int GL_UNSIGNED_INT_SAMPLER_2D_RECT = 0x8DD5;
	int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY = 0x8DD6;
	int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 0x8DD7;
	int GL_UNSIGNED_INT_SAMPLER_BUFFER = 0x8DD8;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_MIN_PROGRAM_TEXEL_OFFSET = 0x8904;
	int GL_MAX_PROGRAM_TEXEL_OFFSET = 0x8905;

	@NoErrorCheck
	void glVertexAttribI1i(@GLuint int index, int x);

	@NoErrorCheck
	void glVertexAttribI2i(@GLuint int index, int x, int y);

	@NoErrorCheck
	void glVertexAttribI3i(@GLuint int index, int x, int y, int z);

	@NoErrorCheck
	void glVertexAttribI4i(@GLuint int index, int x, int y, int z, int w);

	@NoErrorCheck
	void glVertexAttribI1ui(@GLuint int index, @GLuint int x);

	@NoErrorCheck
	void glVertexAttribI2ui(@GLuint int index, @GLuint int x, @GLuint int y);

	@NoErrorCheck
	void glVertexAttribI3ui(@GLuint int index, @GLuint int x, @GLuint int y, @GLuint int z);

	@NoErrorCheck
	void glVertexAttribI4ui(@GLuint int index, @GLuint int x, @GLuint int y, @GLuint int z, @GLuint int w);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI1iv(@GLuint int index, @Check("1") @Const IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI2iv(@GLuint int index, @Check("2") @Const IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI3iv(@GLuint int index, @Check("3") @Const IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4iv(@GLuint int index, @Check("4") @Const IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI1uiv(@GLuint int index, @Check("1") @Const @GLuint IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI2uiv(@GLuint int index, @Check("2") @Const @GLuint IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI3uiv(@GLuint int index, @Check("3") @Const @GLuint IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4uiv(@GLuint int index, @Check("4") @Const @GLuint IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4bv(@GLuint int index, @Check("4") @Const ByteBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4sv(@GLuint int index, @Check("4") @Const ShortBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4ubv(@GLuint int index, @Check("4") @Const @GLubyte ByteBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4usv(@GLuint int index, @Check("4") @Const @GLushort ShortBuffer v);

	void glVertexAttribIPointer(@GLuint int index, int size, @GLenum int type, @GLsizei int stride,
	                            @CachedReference(index = "index", name = "glVertexAttribPointer_buffer")
	                            @BufferObject(BufferKind.ArrayVBO)
	                            @Check
	                            @Const
	                            @GLbyte
	                            @GLubyte
	                            @GLshort
	                            @GLushort
	                            @GLint
	                            @GLuint Buffer buffer);

	@StripPostfix("params")
	void glGetVertexAttribIiv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@StripPostfix("params")
	void glGetVertexAttribIuiv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") @GLuint IntBuffer params);

	void glUniform1ui(int location, @GLuint int v0);

	void glUniform2ui(int location, @GLuint int v0, @GLuint int v1);

	void glUniform3ui(int location, @GLuint int v0, @GLuint int v1, @GLuint int v2);

	void glUniform4ui(int location, @GLuint int v0, @GLuint int v1, @GLuint int v2, @GLuint int v3);

	@StripPostfix("value")
	void glUniform1uiv(int location, @AutoSize("value") @GLsizei int count, @Const @GLuint IntBuffer value);

	@StripPostfix("value")
	void glUniform2uiv(int location, @AutoSize(value = "value", expression = " >> 1") @GLsizei int count, @Const @GLuint IntBuffer value);

	@StripPostfix("value")
	void glUniform3uiv(int location, @AutoSize(value = "value", expression = " / 3") @GLsizei int count, @Const @GLuint IntBuffer value);

	@StripPostfix("value")
	void glUniform4uiv(int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, @Const @GLuint IntBuffer value);

	@StripPostfix("params")
	void glGetUniformuiv(@GLuint int program, int location, @OutParameter @Check @GLuint IntBuffer params);

	void glBindFragDataLocation(@GLuint int program, @GLuint int colorNumber, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glBindFragDataLocation")
	void glBindFragDataLocation(@GLuint int program, @GLuint int colorNumber, @NullTerminated CharSequence name);

	int glGetFragDataLocation(@GLuint int program, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetFragDataLocation")
	int glGetFragDataLocation(@GLuint int program, @NullTerminated CharSequence name);

	// ---------------------------------------------------------------------
	// ----------------------[ NV_conditional_render ]----------------------
	// ---------------------------------------------------------------------

	/** Accepted by the &lt;mode&gt; parameter of BeginConditionalRender: */
	int GL_QUERY_WAIT = 0x8E13;
	int GL_QUERY_NO_WAIT = 0x8E14;
	int GL_QUERY_BY_REGION_WAIT = 0x8E15;
	int GL_QUERY_BY_REGION_NO_WAIT = 0x8E16;

	void glBeginConditionalRender(@GLuint int id, @GLenum int mode);

	void glEndConditionalRender();

	// --------------------------------------------------------------------
	// ----------------------[ ARB_map_buffer_range ]----------------------
	// --------------------------------------------------------------------

	/** Accepted by the &lt;access&gt; parameter of MapBufferRange: */
	int GL_MAP_READ_BIT = 0x0001;
	int GL_MAP_WRITE_BIT = 0x0002;
	int GL_MAP_INVALIDATE_RANGE_BIT = 0x0004;
	int GL_MAP_INVALIDATE_BUFFER_BIT = 0x0008;
	int GL_MAP_FLUSH_EXPLICIT_BIT = 0x0010;
	int GL_MAP_UNSYNCHRONIZED_BIT = 0x0020;

	/**
	 * glMapBufferRange maps a GL buffer object range to a ByteBuffer. The old_buffer argument can be null,
	 * in which case a new ByteBuffer will be created, pointing to the returned memory. If old_buffer is non-null,
	 * it will be returned if it points to the same mapped memory and has the same capacity as the buffer object,
	 * otherwise a new ByteBuffer is created. That way, an application will normally use glMapBufferRange like this:
	 * <p/>
	 * ByteBuffer mapped_buffer; mapped_buffer = glMapBufferRange(..., ..., ..., ..., null); ... // Another map on the same buffer mapped_buffer = glMapBufferRange(..., ..., ..., ..., mapped_buffer);
	 * <p/>
	 * Only ByteBuffers returned from this method are to be passed as the old_buffer argument. User-created ByteBuffers cannot be reused.
	 *
	 * @param old_buffer A ByteBuffer. If this argument points to the same address and has the same capacity as the new mapping, it will be returned and no new buffer will be created.
	 *
	 * @return A ByteBuffer representing the mapped buffer memory.
	 */
	@CachedResult(isRange = true)
	@GLvoid
	@AutoSize("length")
	ByteBuffer glMapBufferRange(@GLenum int target, @GLintptr long offset, @GLsizeiptr long length, @GLbitfield int access);

	void glFlushMappedBufferRange(@GLenum int target, @GLintptr long offset, @GLsizeiptr long length);

	// ----------------------------------------------------------------------
	// ----------------------[ ARB_color_buffer_float ]----------------------
	// ----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of ClampColor and the &lt;pname&gt;
	 * parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev.
	 */
	int GL_CLAMP_VERTEX_COLOR = 0x891A;
	int GL_CLAMP_FRAGMENT_COLOR = 0x891B;
	int GL_CLAMP_READ_COLOR = 0x891C;

	/** Accepted by the &lt;clamp&gt; parameter of ClampColor. */
	int GL_FIXED_ONLY = 0x891D;

	void glClampColor(@GLenum int target, @GLenum int clamp);

	// ----------------------------------------------------------------------
	// ----------------------[ ARB_depth_buffer_float ]----------------------
	// ----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage1D, TexImage2D,
	 * TexImage3D, CopyTexImage1D, CopyTexImage2D, and RenderbufferStorageEXT,
	 * and returned in the &lt;data&gt; parameter of GetTexLevelParameter and
	 * GetRenderbufferParameterivEXT:
	 */
	int GL_DEPTH_COMPONENT32F = 0x8CAC;
	int GL_DEPTH32F_STENCIL8 = 0x8CAD;

	/**
	 * Accepted by the &lt;type&gt; parameter of DrawPixels, ReadPixels, TexImage1D,
	 * TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D, and
	 * GetTexImage:
	 */
	int GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 0x8DAD;

	// -----------------------------------------------------------------
	// ----------------------[ ARB_texture_float ]----------------------
	// -----------------------------------------------------------------

	/** Accepted by the &lt;value&gt; parameter of GetTexLevelParameter: */
	int GL_TEXTURE_RED_TYPE = 0x8C10;
	int GL_TEXTURE_GREEN_TYPE = 0x8C11;
	int GL_TEXTURE_BLUE_TYPE = 0x8C12;
	int GL_TEXTURE_ALPHA_TYPE = 0x8C13;
	int GL_TEXTURE_LUMINANCE_TYPE = 0x8C14;
	int GL_TEXTURE_INTENSITY_TYPE = 0x8C15;
	int GL_TEXTURE_DEPTH_TYPE = 0x8C16;

	/** Returned by the &lt;params&gt; parameter of GetTexLevelParameter: */
	int GL_UNSIGNED_NORMALIZED = 0x8C17;

	/**
	 * Accepted by the &lt;internalFormat&gt; parameter of TexImage1D,
	 * TexImage2D, and TexImage3D:
	 */
	int GL_RGBA32F = 0x8814;
	int GL_RGB32F = 0x8815;
	int GL_ALPHA32F = 0x8816;
	int GL_RGBA16F = 0x881A;
	int GL_RGB16F = 0x881B;
	int GL_ALPHA16F = 0x881C;

	// ----------------------------------------------------------------
	// ----------------------[ EXT_packed_float ]----------------------
	// ----------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage1D,
	 * TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and
	 * RenderbufferStorage:
	 */
	int GL_R11F_G11F_B10F = 0x8C3A;

	/**
	 * Accepted by the &lt;type&gt; parameter of DrawPixels, ReadPixels,
	 * TexImage1D, TexImage2D, GetTexImage, TexImage3D, TexSubImage1D,
	 * TexSubImage2D, TexSubImage3D, GetHistogram, GetMinmax,
	 * ConvolutionFilter1D, ConvolutionFilter2D, ConvolutionFilter3D,
	 * GetConvolutionFilter, SeparableFilter2D, GetSeparableFilter,
	 * ColorTable, ColorSubTable, and GetColorTable:
	 */
	int GL_UNSIGNED_INT_10F_11F_11F_REV = 0x8C3B;

	// ---------------------------------------------------------------------------
	// ----------------------[ EXT_texture_shared_exponent ]----------------------
	// ---------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage1D,
	 * TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and
	 * RenderbufferStorage:
	 */
	int GL_RGB9_E5 = 0x8C3D;

	/**
	 * Accepted by the &lt;type&gt; parameter of DrawPixels, ReadPixels,
	 * TexImage1D, TexImage2D, GetTexImage, TexImage3D, TexSubImage1D,
	 * TexSubImage2D, TexSubImage3D, GetHistogram, GetMinmax,
	 * ConvolutionFilter1D, ConvolutionFilter2D, ConvolutionFilter3D,
	 * GetConvolutionFilter, SeparableFilter2D, GetSeparableFilter,
	 * ColorTable, ColorSubTable, and GetColorTable:
	 */
	int GL_UNSIGNED_INT_5_9_9_9_REV = 0x8C3E;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetTexLevelParameterfv and
	 * GetTexLevelParameteriv:
	 */
	int GL_TEXTURE_SHARED_SIZE = 0x8C3F;

	// ----------------------------------------------------------------------
	// ----------------------[ ARB_framebuffer_object ]----------------------
	// ----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of BindFramebuffer,
	 * CheckFramebufferStatus, FramebufferTexture{1D|2D|3D},
	 * FramebufferRenderbuffer, and
	 * GetFramebufferAttachmentParameteriv:
	 */
	int GL_FRAMEBUFFER = 0x8D40;
	int GL_READ_FRAMEBUFFER = 0x8CA8;
	int GL_DRAW_FRAMEBUFFER = 0x8CA9;

	/**
	 * Accepted by the &lt;target&gt; parameter of BindRenderbuffer,
	 * RenderbufferStorage, and GetRenderbufferParameteriv, and
	 * returned by GetFramebufferAttachmentParameteriv:
	 */
	int GL_RENDERBUFFER = 0x8D41;

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of
	 * RenderbufferStorage:
	 */
	int GL_STENCIL_INDEX1 = 0x8D46;
	int GL_STENCIL_INDEX4 = 0x8D47;
	int GL_STENCIL_INDEX8 = 0x8D48;
	int GL_STENCIL_INDEX16 = 0x8D49;

	/** Accepted by the &lt;pname&gt; parameter of GetRenderbufferParameteriv: */
	int GL_RENDERBUFFER_WIDTH = 0x8D42;
	int GL_RENDERBUFFER_HEIGHT = 0x8D43;
	int GL_RENDERBUFFER_INTERNAL_FORMAT = 0x8D44;
	int GL_RENDERBUFFER_RED_SIZE = 0x8D50;
	int GL_RENDERBUFFER_GREEN_SIZE = 0x8D51;
	int GL_RENDERBUFFER_BLUE_SIZE = 0x8D52;
	int GL_RENDERBUFFER_ALPHA_SIZE = 0x8D53;
	int GL_RENDERBUFFER_DEPTH_SIZE = 0x8D54;
	int GL_RENDERBUFFER_STENCIL_SIZE = 0x8D55;

	/**
	 * Accepted by the &lt;pname&gt; parameter of
	 * GetFramebufferAttachmentParameteriv:
	 */
	int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 0x8CD0;
	int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 0x8CD1;
	int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 0x8CD2;
	int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 0x8CD3;
	int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 0x8210;
	int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 0x8211;
	int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 0x8212;
	int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 0x8213;
	int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 0x8214;
	int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 0x8215;
	int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 0x8216;
	int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 0x8217;

	/** Returned in &lt;params&gt; by GetFramebufferAttachmentParameteriv: */
	int GL_FRAMEBUFFER_DEFAULT = 0x8218;
	int GL_INDEX = 0x8222;

	/**
	 * Accepted by the &lt;attachment&gt; parameter of
	 * FramebufferTexture{1D|2D|3D}, FramebufferRenderbuffer, and
	 * GetFramebufferAttachmentParameteriv
	 */
	int GL_COLOR_ATTACHMENT0 = 0x8CE0;
	int GL_COLOR_ATTACHMENT1 = 0x8CE1;
	int GL_COLOR_ATTACHMENT2 = 0x8CE2;
	int GL_COLOR_ATTACHMENT3 = 0x8CE3;
	int GL_COLOR_ATTACHMENT4 = 0x8CE4;
	int GL_COLOR_ATTACHMENT5 = 0x8CE5;
	int GL_COLOR_ATTACHMENT6 = 0x8CE6;
	int GL_COLOR_ATTACHMENT7 = 0x8CE7;
	int GL_COLOR_ATTACHMENT8 = 0x8CE8;
	int GL_COLOR_ATTACHMENT9 = 0x8CE9;
	int GL_COLOR_ATTACHMENT10 = 0x8CEA;
	int GL_COLOR_ATTACHMENT11 = 0x8CEB;
	int GL_COLOR_ATTACHMENT12 = 0x8CEC;
	int GL_COLOR_ATTACHMENT13 = 0x8CED;
	int GL_COLOR_ATTACHMENT14 = 0x8CEE;
	int GL_COLOR_ATTACHMENT15 = 0x8CEF;
	int GL_DEPTH_ATTACHMENT = 0x8D00;
	int GL_STENCIL_ATTACHMENT = 0x8D20;
	int GL_DEPTH_STENCIL_ATTACHMENT = 0x821A;

	/** Returned by CheckFramebufferStatus(): */
	int GL_FRAMEBUFFER_COMPLETE = 0x8CD5;
	int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 0x8CD6;
	int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 0x8CD7;
	int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 0x8CDB;
	int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 0x8CDC;
	int GL_FRAMEBUFFER_UNSUPPORTED = 0x8CDD;
	int GL_FRAMEBUFFER_UNDEFINED = 0x8219;

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_FRAMEBUFFER_BINDING = 0x8CA6; // alias DRAW_FRAMEBUFFER_BINDING
	int GL_RENDERBUFFER_BINDING = 0x8CA7;
	int GL_MAX_COLOR_ATTACHMENTS = 0x8CDF;
	int GL_MAX_RENDERBUFFER_SIZE = 0x84E8;

	/** Returned by GetError(): */
	int GL_INVALID_FRAMEBUFFER_OPERATION = 0x0506;

	boolean glIsRenderbuffer(@GLuint int renderbuffer);

	void glBindRenderbuffer(@GLenum int target, @GLuint int renderbuffer);

	void glDeleteRenderbuffers(@AutoSize("renderbuffers") int n, @Const @GLuint IntBuffer renderbuffers);

	@Alternate("glDeleteRenderbuffers")
	void glDeleteRenderbuffers(@Constant("1") int n, @Constant(value = "APIUtil.getInt(caps, renderbuffer)", keepParam = true) int renderbuffer);

	void glGenRenderbuffers(@AutoSize("renderbuffers") int n, @OutParameter @GLuint IntBuffer renderbuffers);

	@Alternate("glGenRenderbuffers")
	@GLreturn("renderbuffers")
	void glGenRenderbuffers2(@Constant("1") int n, @OutParameter @GLuint IntBuffer renderbuffers);

	void glRenderbufferStorage(@GLenum int target, @GLenum int internalformat, @GLsizei int width, @GLsizei int height);

	@StripPostfix("params")
	void glGetRenderbufferParameteriv(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetRenderbufferParameteri} instead. */
	@Alternate("glGetRenderbufferParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL30", method = "glGetRenderbufferParameteri")
	@Deprecated
	void glGetRenderbufferParameteriv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetRenderbufferParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetRenderbufferParameteriv3(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	boolean glIsFramebuffer(@GLuint int framebuffer);

	void glBindFramebuffer(@GLenum int target, @GLuint int framebuffer);

	void glDeleteFramebuffers(@AutoSize("framebuffers") int n, @Const @GLuint IntBuffer framebuffers);

	@Alternate("glDeleteFramebuffers")
	void glDeleteFramebuffers(@Constant("1") int n, @Constant(value = "APIUtil.getInt(caps, framebuffer)", keepParam = true) int framebuffer);

	void glGenFramebuffers(@AutoSize("framebuffers") int n, @OutParameter @GLuint IntBuffer framebuffers);

	@Alternate("glGenFramebuffers")
	@GLreturn("framebuffers")
	void glGenFramebuffers2(@Constant("1") int n, @OutParameter @GLuint IntBuffer framebuffers);

	@GLenum
	int glCheckFramebufferStatus(@GLenum int target);

	void glFramebufferTexture1D(@GLenum int target, @GLenum int attachment, @GLenum int textarget, @GLuint int texture, int level);

	void glFramebufferTexture2D(@GLenum int target, @GLenum int attachment, @GLenum int textarget, @GLuint int texture, int level);

	void glFramebufferTexture3D(@GLenum int target, @GLenum int attachment, @GLenum int textarget, @GLuint int texture, int level, int zoffset);

	void glFramebufferRenderbuffer(@GLenum int target, @GLenum int attachment, @GLenum int renderbuffertarget, @GLuint int renderbuffer);

	@StripPostfix("params")
	void glGetFramebufferAttachmentParameteriv(@GLenum int target, @GLenum int attachment, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetFramebufferAttachmentParameteri} instead. */
	@Alternate("glGetFramebufferAttachmentParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL30", method = "glGetFramebufferAttachmentParameteri")
	@Deprecated
	void glGetFramebufferAttachmentParameteriv2(@GLenum int target, @GLenum int attachment, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetFramebufferAttachmentParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetFramebufferAttachmentParameteriv3(@GLenum int target, @GLenum int attachment, @GLenum int pname, @OutParameter IntBuffer params);

	void glGenerateMipmap(@GLenum int target);

	// --------------------------------------------------------------------------------------------
	// ----------------------[ ARB_half_float_vertex & ARB_half_float_pixel ]----------------------
	// --------------------------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;type&gt; parameter of DrawPixels, ReadPixels,
	 * TexImage1D, TexImage2D, TexImage3D, GetTexImage, TexSubImage1D,
	 * TexSubImage2D, TexSubImage3D, GetHistogram, GetMinmax,
	 * ConvolutionFilter1D, ConvolutionFilter2D, GetConvolutionFilter,
	 * SeparableFilter2D, GetSeparableFilter, ColorTable, ColorSubTable,
	 * and GetColorTable:
	 * <p/>
	 * Accepted by the &lt;type&gt; argument of VertexPointer, NormalPointer,
	 * ColorPointer, SecondaryColorPointer, FogCoordPointer, TexCoordPointer,
	 * and VertexAttribPointer:
	 */
	int GL_HALF_FLOAT = 0x140B;

	// ---------------------------------------------------------------------------
	// ----------------------[ EXT_framebuffer_multisample ]----------------------
	// ---------------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetRenderbufferParameteriv. */
	int GL_RENDERBUFFER_SAMPLES = 0x8CAB;

	/** Returned by CheckFramebufferStatus. */
	int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 0x8D56;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev.
	 */
	int GL_MAX_SAMPLES = 0x8D57;

	/**
	 * Establishes the data storage, format, dimensions, and number of
	 * samples of a renderbuffer object's image.
	 */
	void glRenderbufferStorageMultisample(
			@GLenum int target, @GLsizei int samples,
			@GLenum int internalformat,
			@GLsizei int width, @GLsizei int height);

	// --------------------------------------------------------------------
	// ----------------------[ EXT_framebuffer_blit ]----------------------
	// --------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameters of GetIntegerv, GetFloatv, and GetDoublev. */
	int GL_DRAW_FRAMEBUFFER_BINDING = 0x8CA6; // alias FRAMEBUFFER_BINDING
	int GL_READ_FRAMEBUFFER_BINDING = 0x8CAA;

	/**
	 * Transfers a rectangle of pixel values from one
	 * region of the read framebuffer to another in the draw framebuffer.
	 * &lt;mask&gt; is the bitwise OR of a number of values indicating which
	 * buffers are to be copied. The values are COLOR_BUFFER_BIT,
	 * DEPTH_BUFFER_BIT, and STENCIL_BUFFER_BIT.
	 * The pixels corresponding to these buffers are
	 * copied from the source rectangle, bound by the locations (srcX0,
	 * srcY0) and (srcX1, srcY1) inclusive, to the destination rectangle,
	 * bound by the locations (dstX0, dstY0) and (dstX1, dstY1)
	 * inclusive.
	 * If the source and destination rectangle dimensions do not match,
	 * the source image is stretched to fit the destination
	 * rectangle. &lt;filter&gt; must be LINEAR or NEAREST and specifies the
	 * method of interpolation to be applied if the image is
	 * stretched.
	 */
	void glBlitFramebuffer(
			@GLint int srcX0, @GLint int srcY0, @GLint int srcX1, @GLint int srcY1,
			@GLint int dstX0, @GLint int dstY0, @GLint int dstX1, @GLint int dstY1,
			@GLbitfield int mask, @GLenum int filter);

	// -------------------------------------------------------------------
	// ----------------------[ EXT_texture_integer ]----------------------
	// -------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_RGBA_INTEGER_MODE = 0x8D9E;

	/**
	 * Accepted by the &lt;internalFormat&gt; parameter of TexImage1D,
	 * TexImage2D, and TexImage3D:
	 */
	int GL_RGBA32UI = 0x8D70;
	int GL_RGB32UI = 0x8D71;
	int GL_ALPHA32UI = 0x8D72;

	int GL_RGBA16UI = 0x8D76;
	int GL_RGB16UI = 0x8D77;
	int GL_ALPHA16UI = 0x8D78;

	int GL_RGBA8UI = 0x8D7C;
	int GL_RGB8UI = 0x8D7D;
	int GL_ALPHA8UI = 0x8D7E;

	int GL_RGBA32I = 0x8D82;
	int GL_RGB32I = 0x8D83;
	int GL_ALPHA32I = 0x8D84;

	int GL_RGBA16I = 0x8D88;
	int GL_RGB16I = 0x8D89;
	int GL_ALPHA16I = 0x8D8A;

	int GL_RGBA8I = 0x8D8E;
	int GL_RGB8I = 0x8D8F;
	int GL_ALPHA8I = 0x8D90;

	/**
	 * Accepted by the &lt;format&gt; parameter of TexImage1D, TexImage2D,
	 * TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D,
	 * DrawPixels and ReadPixels:
	 */
	int GL_RED_INTEGER = 0x8D94;
	int GL_GREEN_INTEGER = 0x8D95;
	int GL_BLUE_INTEGER = 0x8D96;
	int GL_ALPHA_INTEGER = 0x8D97;
	int GL_RGB_INTEGER = 0x8D98;
	int GL_RGBA_INTEGER = 0x8D99;
	int GL_BGR_INTEGER = 0x8D9A;
	int GL_BGRA_INTEGER = 0x8D9B;

	@StripPostfix("params")
	void glTexParameterIiv(@GLenum int target, @GLenum int pname, @Check("4") IntBuffer params);

	@Alternate("glTexParameterIiv")
	@StripPostfix(value = "param", postfix = "v")
	void glTexParameterIiv(@GLenum int target, @GLenum int pname, @Constant(value = "APIUtil.getInt(caps, param)", keepParam = true) int param);

	@StripPostfix("params")
	void glTexParameterIuiv(@GLenum int target, @GLenum int pname, @Check("4") @GLuint IntBuffer params);

	@Alternate("glTexParameterIuiv")
	@StripPostfix(value = "param", postfix = "v")
	void glTexParameterIuiv(@GLenum int target, @GLenum int pname, @Constant(value = "APIUtil.getInt(caps, param)", keepParam = true) int param);

	@StripPostfix("params")
	void glGetTexParameterIiv(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@Alternate("glGetTexParameterIiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexParameterIiv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetTexParameterIuiv(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") @GLuint IntBuffer params);

	@Alternate("glGetTexParameterIuiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexParameterIuiv2(@GLenum int target, @GLenum int pname, @OutParameter @GLuint IntBuffer params);

	// -----------------------------------------------------------------
	// ----------------------[ EXT_texture_array ]----------------------
	// -----------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of TexParameteri, TexParameteriv,
	 * TexParameterf, TexParameterfv, and BindTexture:
	 */
	int GL_TEXTURE_1D_ARRAY = 0x8C18;
	int GL_TEXTURE_2D_ARRAY = 0x8C1A;

	/**
	 * Accepted by the &lt;target&gt; parameter of TexImage3D, TexSubImage3D,
	 * CopyTexSubImage3D, CompressedTexImage3D, and CompressedTexSubImage3D:
	 */
	int GL_PROXY_TEXTURE_2D_ARRAY = 0x8C1B;

	/**
	 * Accepted by the &lt;target&gt; parameter of TexImage2D, TexSubImage2D,
	 * CopyTexImage2D, CopyTexSubImage2D, CompressedTexImage2D, and
	 * CompressedTexSubImage2D:
	 */
	int GL_PROXY_TEXTURE_1D_ARRAY = 0x8C19;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv
	 * and GetFloatv:
	 */
	int GL_TEXTURE_BINDING_1D_ARRAY = 0x8C1C;
	int GL_TEXTURE_BINDING_2D_ARRAY = 0x8C1D;
	int GL_MAX_ARRAY_TEXTURE_LAYERS = 0x88FF;

	/**
	 * Accepted by the &lt;param&gt; parameter of TexParameterf, TexParameteri,
	 * TexParameterfv, and TexParameteriv when the &lt;pname&gt; parameter is
	 * TEXTURE_COMPARE_MODE_ARB:
	 */
	int GL_COMPARE_REF_DEPTH_TO_TEXTURE = 0x884E;

	/**
	 * Accepted by the &lt;pname&gt; parameter of
	 * GetFramebufferAttachmentParameteriv:
	 */
	int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 0x8CD4;

	/** Returned by the &lt;type&gt; parameter of GetActiveUniform: */
	int GL_SAMPLER_1D_ARRAY = 0x8DC0;
	int GL_SAMPLER_2D_ARRAY = 0x8DC1;
	int GL_SAMPLER_1D_ARRAY_SHADOW = 0x8DC3;
	int GL_SAMPLER_2D_ARRAY_SHADOW = 0x8DC4;

	void glFramebufferTextureLayer(@GLenum int target, @GLenum int attachment, @GLuint int texture, int level, int layer);

	// ------------------------------------------------------------------------
	// ----------------------[ EXT_packed_depth_stencil ]----------------------
	// ------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;format&gt; parameter of DrawPixels, ReadPixels,
	 * TexImage1D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D,
	 * TexSubImage3D, and GetTexImage, by the &lt;type&gt; parameter of
	 * CopyPixels, by the &lt;internalformat&gt; parameter of TexImage1D,
	 * TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and
	 * RenderbufferStorage, and returned in the &lt;data&gt; parameter of
	 * GetTexLevelParameter and GetRenderbufferParameteriv.
	 */
	int GL_DEPTH_STENCIL = 0x84F9;

	/**
	 * Accepted by the &lt;type&gt; parameter of DrawPixels, ReadPixels,
	 * TexImage1D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D,
	 * TexSubImage3D, and GetTexImage.
	 */
	int GL_UNSIGNED_INT_24_8 = 0x84FA;

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage1D,
	 * TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and
	 * RenderbufferStorage, and returned in the &lt;data&gt; parameter of
	 * GetTexLevelParameter and GetRenderbufferParameteriv.
	 */
	int GL_DEPTH24_STENCIL8 = 0x88F0;

	/** Accepted by the &lt;value&gt; parameter of GetTexLevelParameter. */
	int GL_TEXTURE_STENCIL_SIZE = 0x88F1;

	// -----------------------------------------------------------------
	// ----------------------[ EXT_draw_buffers2 ]----------------------
	// -----------------------------------------------------------------

	void glColorMaski(@GLuint int buf, boolean r, boolean g, boolean b, boolean a);

	@StripPostfix(value = "data", hasPostfix = false)
	void glGetBooleani_v(@GLenum int value, @GLuint int index, @OutParameter @Check("4") @GLboolean ByteBuffer data);

	@Alternate("glGetBooleani_v")
	@GLreturn("data")
	@StripPostfix(value = "data", hasPostfix = false)
	void glGetBooleani_v2(@GLenum int value, @GLuint int index, @OutParameter @GLboolean ByteBuffer data);

	@StripPostfix("data")
	void glGetIntegeri_v(@GLenum int value, @GLuint int index, @OutParameter @Check("4") IntBuffer data);

	@Alternate("glGetIntegeri_v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetIntegeri_v2(@GLenum int value, @GLuint int index, @OutParameter IntBuffer data);

	void glEnablei(@GLenum int target, @GLuint int index);

	void glDisablei(@GLenum int target, @GLuint int index);

	boolean glIsEnabledi(@GLenum int target, @GLuint int index);

	// ----------------------------------------------------------------------------
	// ----------------------[ ARB_texture_compression_rgtc ]----------------------
	// ----------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage2D,
	 * CopyTexImage2D, and CompressedTexImage2D and the &lt;format&gt; parameter
	 * of CompressedTexSubImage2D:
	 */
	int GL_COMPRESSED_RED_RGTC1        = 0x8DBB,
		GL_COMPRESSED_SIGNED_RED_RGTC1 = 0x8DBC,
		GL_COMPRESSED_RG_RGTC2         = 0x8DBD,
		GL_COMPRESSED_SIGNED_RG_RGTC2  = 0x8DBE;

	// --------------------------------------------------------------
	// ----------------------[ ARB_texture_rg ]----------------------
	// --------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalFormat&gt; parameter of TexImage1D, TexImage2D,
	 * TexImage3D, CopyTexImage1D, and CopyTexImage2D:
	 */
	int GL_R8 = 0x8229;
	int GL_R16 = 0x822A;

	int GL_RG8 = 0x822B;
	int GL_RG16 = 0x822C;

	int GL_R16F = 0x822D;
	int GL_R32F = 0x822E;

	int GL_RG16F = 0x822F;
	int GL_RG32F = 0x8230;

	int GL_R8I = 0x8231;
	int GL_R8UI = 0x8232;
	int GL_R16I = 0x8233;
	int GL_R16UI = 0x8234;
	int GL_R32I = 0x8235;
	int GL_R32UI = 0x8236;

	int GL_RG8I = 0x8237;
	int GL_RG8UI = 0x8238;
	int GL_RG16I = 0x8239;
	int GL_RG16UI = 0x823A;
	int GL_RG32I = 0x823B;
	int GL_RG32UI = 0x823C;

	/**
	 * Accepted by the &lt;format&gt; parameter of TexImage3D, TexImage2D,
	 * TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D,
	 * DrawPixels and ReadPixels:
	 */
	int GL_RG = 0x8227;
	int GL_RG_INTEGER = 0x8228;

	// ----------------------------------------------------------------------
	// ----------------------[ EXT_transform_feedback ]----------------------
	// ----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData,
	 * GetBufferPointerv, BindBufferRange, BindBufferOffset and
	 * BindBufferBase:
	 */
	int GL_TRANSFORM_FEEDBACK_BUFFER = 0x8C8E;

	/**
	 * Accepted by the &lt;param&gt; parameter of GetIntegerIndexedv and
	 * GetBooleanIndexedv:
	 */
	int GL_TRANSFORM_FEEDBACK_BUFFER_START = 0x8C84;
	int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE = 0x8C85;

	/**
	 * Accepted by the &lt;param&gt; parameter of GetIntegerIndexedv and
	 * GetBooleanIndexedv, and by the &lt;pname&gt; parameter of GetBooleanv,
	 * GetDoublev, GetIntegerv, and GetFloatv:
	 */
	int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 0x8C8F;

	/** Accepted by the &lt;bufferMode&gt; parameter of TransformFeedbackVaryings: */
	int GL_INTERLEAVED_ATTRIBS = 0x8C8C;
	int GL_SEPARATE_ATTRIBS = 0x8C8D;

	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQuery, EndQuery, and
	 * GetQueryiv:
	 */
	int GL_PRIMITIVES_GENERATED = 0x8C87;
	int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 0x8C88;

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled, and by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	int GL_RASTERIZER_DISCARD = 0x8C89;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv,
	 * and GetFloatv:
	 */
	int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 0x8C8A;
	int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 0x8C8B;
	int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS = 0x8C80;

	/** Accepted by the &lt;pname&gt; parameter of GetProgramiv: */
	int GL_TRANSFORM_FEEDBACK_VARYINGS = 0x8C83;
	int GL_TRANSFORM_FEEDBACK_BUFFER_MODE = 0x8C7F;
	int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 0x8C76;

	void glBindBufferRange(@GLenum int target, @GLuint int index, @GLuint int buffer, @GLintptr long offset, @GLsizeiptr long size);

	void glBindBufferBase(@GLenum int target, @GLuint int index, @GLuint int buffer);

	void glBeginTransformFeedback(@GLenum int primitiveMode);

	void glEndTransformFeedback();

	void glTransformFeedbackVaryings(@GLuint int program, @GLsizei int count,
	                                 @Const @NullTerminated("count") @GLchar @PointerArray("count") ByteBuffer varyings,
	                                 @GLenum int bufferMode);

	@Alternate("glTransformFeedbackVaryings")
	void glTransformFeedbackVaryings(@GLuint int program, @Constant("varyings.length") @GLsizei int count,
	                                 @Const @NullTerminated @PointerArray("count") CharSequence[] varyings,
	                                 @GLenum int bufferMode);

	void glGetTransformFeedbackVarying(@GLuint int program, @GLuint int index, @AutoSize("name") @GLsizei int bufSize,
	                                   @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                                   @OutParameter @GLsizei @Check("1") IntBuffer size,
	                                   @OutParameter @GLenum @Check("1") IntBuffer type,
	                                   @OutParameter @GLchar ByteBuffer name);

	@Alternate("glGetTransformFeedbackVarying")
	@GLreturn(value = "name", maxLength = "bufSize")
	void glGetTransformFeedbackVarying2(@GLuint int program, @GLuint int index, @GLsizei int bufSize,
	                                    @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length)") IntBuffer length,
	                                    @OutParameter @GLsizei @Check("1") IntBuffer size,
	                                    @OutParameter @GLenum @Check("1") IntBuffer type,
	                                    @OutParameter @GLchar ByteBuffer name);

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_vertex_array_object ]----------------------
	// -----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_VERTEX_ARRAY_BINDING = 0x85B5;

	@Code("		StateTracker.bindVAO(caps, array);")
	void glBindVertexArray(@GLuint int array);

	@Code("		StateTracker.deleteVAO(caps, arrays);")
	void glDeleteVertexArrays(@AutoSize("arrays") @GLsizei int n, @Const @GLuint IntBuffer arrays);

	@Alternate("glDeleteVertexArrays")
	@Code("		StateTracker.deleteVAO(caps, array);")
	void glDeleteVertexArrays(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(caps, array)", keepParam = true) int array);

	void glGenVertexArrays(@AutoSize("arrays") @GLsizei int n, @OutParameter @GLuint IntBuffer arrays);

	@Alternate("glGenVertexArrays")
	@GLreturn("arrays")
	void glGenVertexArrays2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer arrays);

	boolean glIsVertexArray(@GLuint int array);

	// --------------------------------------------------------------------
	// ----------------------[ ARB_framebuffer_sRGB ]----------------------
	// --------------------------------------------------------------------

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled,
	 * and by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_FRAMEBUFFER_SRGB = 0x8DB9;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_FRAMEBUFFER_SRGB_CAPABLE = 0x8DBA;

}