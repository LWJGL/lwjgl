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

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

@DeprecatedGL
public interface GL33 {

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_blend_func_extended ]----------------------
	// -----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;src&gt; and &lt;dst&gt; parameters of BlendFunc and
	 * BlendFunci, and by the &lt;srcRGB&gt;, &lt;dstRGB&gt;, &lt;srcAlpha&gt; and &lt;dstAlpha&gt;
	 * parameters of BlendFuncSeparate and BlendFuncSeparatei:
	 */
	int GL_SRC1_COLOR = 0x88F9;
	int GL_SRC1_ALPHA = GL15.GL_SRC1_ALPHA;
	int GL_ONE_MINUS_SRC1_COLOR = 0x88FA;
	int GL_ONE_MINUS_SRC1_ALPHA = 0x88FB;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv
	 * and GetDoublev:
	 */
	int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 0x88FC;

	void glBindFragDataLocationIndexed(@GLuint int program, @GLuint int colorNumber, @GLuint int index, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glBindFragDataLocationIndexed")
	void glBindFragDataLocationIndexed(@GLuint int program, @GLuint int colorNumber, @GLuint int index, @NullTerminated CharSequence name);

	int glGetFragDataIndex(@GLuint int program, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetFragDataIndex")
	int glGetFragDataIndex(@GLuint int program, @NullTerminated CharSequence name);

	// --------------------------------------------------------------------
	// ----------------------[ ARB_occlusion_query2 ]----------------------
	// --------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQuery, EndQuery,
	 * and GetQueryiv:
	 */
	int GL_ANY_SAMPLES_PASSED = 0x8C2F;

	// -------------------------------------------------------------------
	// ----------------------[ ARB_sampler_objects ]----------------------
	// -------------------------------------------------------------------

	/**
	 * Accepted by the &lt;value&gt; parameter of the GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv and GetDoublev functions:
	 */
	int GL_SAMPLER_BINDING = 0x8919;

	void glGenSamplers(@AutoSize("samplers") @GLsizei int count, @OutParameter @GLuint IntBuffer samplers);

	@Alternate("glGenSamplers")
	@GLreturn("samplers")
	void glGenSamplers2(@Constant("1") @GLsizei int count, @OutParameter @GLuint IntBuffer samplers);

	void glDeleteSamplers(@AutoSize("samplers") @GLsizei int count, @Const @GLuint IntBuffer samplers);

	@Alternate("glDeleteSamplers")
	void glDeleteSamplers(@Constant("1") @GLsizei int count, @Constant(value = "APIUtil.getInt(caps, sampler)", keepParam = true) int sampler);

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

	@Alternate("glGetSamplerParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetSamplerParameteriv2(@GLuint int sampler, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetSamplerParameterfv(@GLuint int sampler, @GLenum int pname, @Check("4") @OutParameter FloatBuffer params);

	@Alternate("glGetSamplerParameterfv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetSamplerParameterfv2(@GLuint int sampler, @GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetSamplerParameterIiv(@GLuint int sampler, @GLenum int pname, @Check("4") @OutParameter IntBuffer params);

	@Alternate("glGetSamplerParameterIiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetSamplerParameterIiv2(@GLuint int sampler, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetSamplerParameterIuiv(@GLuint int sampler, @GLenum int pname, @Check("4") @OutParameter IntBuffer params);

	@Alternate("glGetSamplerParameterIuiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetSamplerParameterIuiv2(@GLuint int sampler, @GLenum int pname, @OutParameter IntBuffer params);

	// -------------------------------------------------------------------
	// ----------------------[ ARB_texture_rgb10_a2ui ]----------------------
	// -------------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalFormat&gt; parameter of TexImage1D, TexImage2D,
	 * TexImage3D, CopyTexImage1D, CopyTexImage2D, RenderbufferStorage and
	 * RenderbufferStorageMultisample:
	 */
	int GL_RGB10_A2UI = 0x906F;

	// -------------------------------------------------------------------
	// ----------------------[ ARB_texture_swizzle ]----------------------
	// -------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameters of TexParameteri,
	 * TexParameterf, TexParameteriv, TexParameterfv,
	 * GetTexParameterfv, and GetTexParameteriv:
	 */
	int GL_TEXTURE_SWIZZLE_R = 0x8E42;
	int GL_TEXTURE_SWIZZLE_G = 0x8E43;
	int GL_TEXTURE_SWIZZLE_B = 0x8E44;
	int GL_TEXTURE_SWIZZLE_A = 0x8E45;

	/**
	 * Accepted by the &lt;pname&gt; parameters of TexParameteriv,
	 * TexParameterfv, GetTexParameterfv, and GetTexParameteriv:
	 */
	int GL_TEXTURE_SWIZZLE_RGBA = 0x8E46;

	// ---------------------------------------------------------------
	// ----------------------[ ARB_timer_query ]----------------------
	// ---------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQuery, EndQuery, and
	 * GetQueryiv:
	 */
	int GL_TIME_ELAPSED = 0x88BF;

	/**
	 * Accepted by the &lt;target&gt; parameter of GetQueryiv and QueryCounter.
	 * Accepted by the &lt;value&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_TIMESTAMP = 0x8E28;

	void glQueryCounter(@GLuint int id, @GLenum int target);

	@StripPostfix("params")
	void glGetQueryObjecti64v(@GLuint int id, @GLenum int pname, @OutParameter @Check("1") @GLint64 LongBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetQueryObjecti64} instead. */
	@Alternate("glGetQueryObjecti64v")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL33", method = "glGetQueryObjecti64")
	@Deprecated
	void glGetQueryObjecti64v2(@GLuint int id, @GLenum int pname, @OutParameter @GLint64 LongBuffer params);

	@Alternate("glGetQueryObjecti64v")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryObjecti64v3(@GLuint int id, @GLenum int pname, @OutParameter @GLint64 LongBuffer params);

	@StripPostfix("params")
	void glGetQueryObjectui64v(@GLuint int id, @GLenum int pname, @OutParameter @Check("1") @GLuint64 LongBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetQueryObjectui64} instead. */
	@Alternate("glGetQueryObjectui64v")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL33", method = "glGetQueryObjectui64")
	@Deprecated
	void glGetQueryObjectui64v2(@GLuint int id, @GLenum int pname, @OutParameter @GLuint64 LongBuffer params);

	@Alternate("glGetQueryObjectui64v")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryObjectui64v3(@GLuint int id, @GLenum int pname, @OutParameter @GLuint64 LongBuffer params);

	// --------------------------------------------------------------------
	// ----------------------[ ARB_instanced_arrays ]----------------------
	// --------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetVertexAttribdv,
	 * GetVertexAttribfv, and GetVertexAttribiv:
	 */
	int GL_VERTEX_ATTRIB_ARRAY_DIVISOR = 0x88FE;

	void glVertexAttribDivisor(@GLuint int index, @GLuint int divisor);

	// ------------------------------------------------------------------------------
	// ----------------------[ ARB_vertex_type_2_10_10_10_rev ]----------------------
	// ------------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;type&gt; parameter of VertexAttribPointer, VertexPointer,
	 * NormalPointer, ColorPointer, SecondaryColorPointer, TexCoordPointer,
	 * VertexAttribP{1234}ui, VertexP*, TexCoordP*, MultiTexCoordP*, NormalP3ui,
	 * ColorP*, SecondaryColorP* and VertexAttribP*
	 */
	int GL_INT_2_10_10_10_REV = 0x8D9F;

	@NoErrorCheck
	@DeprecatedGL
	void glVertexP2ui(@GLenum int type, @GLuint int value);

	@NoErrorCheck
	@DeprecatedGL
	void glVertexP3ui(@GLenum int type, @GLuint int value);

	@NoErrorCheck
	@DeprecatedGL
	void glVertexP4ui(@GLenum int type, @GLuint int value);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("value")
	void glVertexP2uiv(@GLenum int type, @Check("2") @Const @GLuint IntBuffer value);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("value")
	void glVertexP3uiv(@GLenum int type, @Check("3") @Const @GLuint IntBuffer value);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("value")
	void glVertexP4uiv(@GLenum int type, @Check("4") @Const @GLuint IntBuffer value);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoordP1ui(@GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoordP2ui(@GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoordP3ui(@GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoordP4ui(@GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glTexCoordP1uiv(@GLenum int type, @Check("1") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glTexCoordP2uiv(@GLenum int type, @Check("2") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glTexCoordP3uiv(@GLenum int type, @Check("3") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glTexCoordP4uiv(@GLenum int type, @Check("4") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoordP1ui(@GLenum int texture, @GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoordP2ui(@GLenum int texture, @GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoordP3ui(@GLenum int texture, @GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoordP4ui(@GLenum int texture, @GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glMultiTexCoordP1uiv(@GLenum int texture, @GLenum int type, @Check("1") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glMultiTexCoordP2uiv(@GLenum int texture, @GLenum int type, @Check("2") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glMultiTexCoordP3uiv(@GLenum int texture, @GLenum int type, @Check("3") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glMultiTexCoordP4uiv(@GLenum int texture, @GLenum int type, @Check("4") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	void glNormalP3ui(@GLenum int type, @GLuint int coords);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("coords")
	void glNormalP3uiv(@GLenum int type, @Check("3") @Const @GLuint IntBuffer coords);

	@NoErrorCheck
	@DeprecatedGL
	void glColorP3ui(@GLenum int type, @GLuint int color);

	@NoErrorCheck
	@DeprecatedGL
	void glColorP4ui(@GLenum int type, @GLuint int color);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("color")
	void glColorP3uiv(@GLenum int type, @Check("3") @Const @GLuint IntBuffer color);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("color")
	void glColorP4uiv(@GLenum int type, @Check("4") @Const @GLuint IntBuffer color);

	@NoErrorCheck
	@DeprecatedGL
	void glSecondaryColorP3ui(@GLenum int type, @GLuint int color);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("color")
	void glSecondaryColorP3uiv(@GLenum int type, @Check("3") @Const @GLuint IntBuffer color);

	@NoErrorCheck
	@DeprecatedGL
	void glVertexAttribP1ui(@GLuint int index, @GLenum int type, boolean normalized, @GLuint int value);

	@NoErrorCheck
	@DeprecatedGL
	void glVertexAttribP2ui(@GLuint int index, @GLenum int type, boolean normalized, @GLuint int value);

	@NoErrorCheck
	@DeprecatedGL
	void glVertexAttribP3ui(@GLuint int index, @GLenum int type, boolean normalized, @GLuint int value);

	@NoErrorCheck
	@DeprecatedGL
	void glVertexAttribP4ui(@GLuint int index, @GLenum int type, boolean normalized, @GLuint int value);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("value")
	void glVertexAttribP1uiv(@GLuint int index, @GLenum int type, boolean normalized, @Check("1") @Const @GLuint IntBuffer value);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("value")
	void glVertexAttribP2uiv(@GLuint int index, @GLenum int type, boolean normalized, @Check("2") @Const @GLuint IntBuffer value);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("value")
	void glVertexAttribP3uiv(@GLuint int index, @GLenum int type, boolean normalized, @Check("3") @Const @GLuint IntBuffer value);

	@NoErrorCheck
	@DeprecatedGL
	@StripPostfix("value")
	void glVertexAttribP4uiv(@GLuint int index, @GLenum int type, boolean normalized, @Check("4") @Const @GLuint IntBuffer value);

}