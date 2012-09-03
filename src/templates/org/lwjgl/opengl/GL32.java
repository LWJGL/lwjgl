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
import org.lwjgl.util.generator.opengl.*;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface GL32 {

	// ----------------------------------------------------------
	// ----------------------[ OpenGL 3.2 ]----------------------
	// ----------------------------------------------------------

	int GL_CONTEXT_PROFILE_MASK              = 0x9126;
	int GL_CONTEXT_CORE_PROFILE_BIT          = 0x00000001;
	int GL_CONTEXT_COMPATIBILITY_PROFILE_BIT = 0x00000002;

	int GL_MAX_VERTEX_OUTPUT_COMPONENTS   = 0x9122;
	int GL_MAX_GEOMETRY_INPUT_COMPONENTS  = 0x9123;
	int GL_MAX_GEOMETRY_OUTPUT_COMPONENTS = 0x9124;
	int GL_MAX_FRAGMENT_INPUT_COMPONENTS  = 0x9125;

	@StripPostfix("params")
	void glGetBufferParameteri64v(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") LongBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetBufferParameteri64} instead. */
	@Alternate("glGetBufferParameteri64v")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL32", method = "glGetBufferParameteri64")
	@Deprecated
	void glGetBufferParameteri64v2(@GLenum int target, @GLenum int pname, @OutParameter LongBuffer params);

	@Alternate("glGetBufferParameteri64v")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetBufferParameteri64v3(@GLenum int target, @GLenum int pname, @OutParameter LongBuffer params);

	// -----------------------------------------------------------------------------
	// ----------------------[ ARB_draw_elements_base_vertex ]----------------------
	// -----------------------------------------------------------------------------

	void glDrawElementsBaseVertex(@GLenum int mode, @AutoSize("indices") @GLsizei int count, @AutoType("indices") @GLenum int type,
	                              @BufferObject(BufferKind.ElementVBO)
	                              @Const
	                              @GLubyte
	                              @GLushort
	                              @GLuint Buffer indices, int basevertex);

	void glDrawRangeElementsBaseVertex(@GLenum int mode, @GLuint int start, @GLuint int end, @AutoSize("indices") @GLsizei int count, @AutoType("indices") @GLenum int type,
	                                   @BufferObject(BufferKind.ElementVBO)
	                                   @Const
	                                   @GLubyte
	                                   @GLushort
	                                   @GLuint Buffer indices, int basevertex);

	void glDrawElementsInstancedBaseVertex(@GLenum int mode, @AutoSize("indices") @GLsizei int count, @AutoType("indices") @GLenum int type,
	                                       @BufferObject(BufferKind.ElementVBO)
	                                       @Const
	                                       @GLubyte
	                                       @GLushort
	                                       @GLuint Buffer indices, @GLsizei int primcount, int basevertex);

	//void glMultiDrawElementsBaseVertex(@GLenum int mode, @GLsizei*count, @GLenum int type, void**indices, @GLsizei int primcount, int*basevertex)

	// --------------------------------------------------------------------
	// ----------------------[ ARB_provoking_vertex ]----------------------
	// --------------------------------------------------------------------

	/** Accepted by the &lt;mode&gt; parameter of ProvokingVertex: */
	int GL_FIRST_VERTEX_CONVENTION = 0x8E4D;
	int GL_LAST_VERTEX_CONVENTION  = 0x8E4E;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_PROVOKING_VERTEX                         = 0x8E4F;
	int GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 0x8E4C;

	void glProvokingVertex(@GLenum int mode);

	// ---------------------------------------------------------------------
	// ----------------------[ ARB_seamless_cube_map ]----------------------
	// ---------------------------------------------------------------------

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable and IsEnabled,
	 * and by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv
	 * and GetDoublev:
	 */
	int GL_TEXTURE_CUBE_MAP_SEAMLESS = 0x884F;

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_texture_multisample ]----------------------
	// -----------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetMultisamplefv: */
	int GL_SAMPLE_POSITION = 0x8E50;

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled, and by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	int GL_SAMPLE_MASK = 0x8E51;

	/**
	 * Accepted by the &lt;target&gt; parameter of GetBooleani_v and
	 * GetIntegeri_v:
	 */
	int GL_SAMPLE_MASK_VALUE = 0x8E52;

	/**
	 * Accepted by the &lt;target&gt; parameter of BindTexture and
	 * TexImage2DMultisample:
	 */
	int GL_TEXTURE_2D_MULTISAMPLE = 0x9100;

	/** Accepted by the &lt;target&gt; parameter of TexImage2DMultisample: */
	int GL_PROXY_TEXTURE_2D_MULTISAMPLE = 0x9101;

	/**
	 * Accepted by the &lt;target&gt; parameter of BindTexture and
	 * TexImage3DMultisample:
	 */
	int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 0x9102;

	/** Accepted by the &lt;target&gt; parameter of TexImage3DMultisample: */
	int GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 0x9103;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv,
	 * and GetFloatv:
	 */
	int GL_MAX_SAMPLE_MASK_WORDS                = 0x8E59;
	int GL_MAX_COLOR_TEXTURE_SAMPLES            = 0x910E;
	int GL_MAX_DEPTH_TEXTURE_SAMPLES            = 0x910F;
	int GL_MAX_INTEGER_SAMPLES                  = 0x9110;
	int GL_TEXTURE_BINDING_2D_MULTISAMPLE       = 0x9104;
	int GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 0x9105;

	/** Accepted by the &lt;pname&gt; parameter of GetTexLevelParameter */
	int GL_TEXTURE_SAMPLES                = 0x9106;
	int GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 0x9107;

	/** Returned by the &lt;type&gt; parameter of GetActiveUniform: */
	int GL_SAMPLER_2D_MULTISAMPLE                    = 0x9108;
	int GL_INT_SAMPLER_2D_MULTISAMPLE                = 0x9109;
	int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE       = 0x910A;
	int GL_SAMPLER_2D_MULTISAMPLE_ARRAY              = 0x910B;
	int GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY          = 0x910C;
	int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 0x910D;

	void glTexImage2DMultisample(@GLenum int target, @GLsizei int samples, int internalformat,
	                             @GLsizei int width, @GLsizei int height,
	                             boolean fixedsamplelocations);

	void glTexImage3DMultisample(@GLenum int target, @GLsizei int samples, int internalformat,
	                             @GLsizei int width, @GLsizei int height, @GLsizei int depth,
	                             boolean fixedsamplelocations);

	@StripPostfix("val")
	void glGetMultisamplefv(@GLenum int pname, @GLuint int index, @OutParameter @Check("2") FloatBuffer val);

	void glSampleMaski(@GLuint int index, @GLbitfield int mask);

	// ---------------------------------------------------------------
	// ----------------------[ ARB_depth_clamp ]----------------------
	// ---------------------------------------------------------------

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled,
	 * and by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_DEPTH_CLAMP = 0x864F;

	// --------------------------------------------------------------------
	// ----------------------[ ARB_geometry_shader4 ]----------------------
	// --------------------------------------------------------------------

	/**
	 * Accepted by the &lt;type&gt; parameter of CreateShader and returned by the
	 * &lt;params&gt; parameter of GetShaderiv:
	 */
	int GL_GEOMETRY_SHADER = 0x8DD9;

	/**
	 * Accepted by the &lt;pname&gt; parameter of ProgramParameteriEXT and
	 * GetProgramiv:
	 */
	int GL_GEOMETRY_VERTICES_OUT = 0x8DDA;
	int GL_GEOMETRY_INPUT_TYPE   = 0x8DDB;
	int GL_GEOMETRY_OUTPUT_TYPE  = 0x8DDC;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS     = 0x8C29;
	//int GL_MAX_GEOMETRY_VARYING_COMPONENTS = 0x8DDD; -- Missing from 3.2 spec
	//int GL_MAX_VERTEX_VARYING_COMPONENTS = 0x8DDE; -- Missing from 3.2 spec
	int GL_MAX_VARYING_COMPONENTS               = 0x8B4B;
	int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS      = 0x8DDF;
	int GL_MAX_GEOMETRY_OUTPUT_VERTICES         = 0x8DE0;
	int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS = 0x8DE1;

	/**
	 * Accepted by the &lt;mode&gt; parameter of Begin, DrawArrays,
	 * MultiDrawArrays, DrawElements, MultiDrawElements, and
	 * DrawRangeElements:
	 */
	int GL_LINES_ADJACENCY          = 0xA;
	int GL_LINE_STRIP_ADJACENCY     = 0xB;
	int GL_TRIANGLES_ADJACENCY      = 0xC;
	int GL_TRIANGLE_STRIP_ADJACENCY = 0xD;

	/** Returned by CheckFramebufferStatusEXT: */
	int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS = 0x8DA8;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetFramebufferAttachment-
	 * ParameterivEXT:
	 */
	int GL_FRAMEBUFFER_ATTACHMENT_LAYERED       = 0x8DA7;
	int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 0x8CD4;

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled,
	 * and by the &lt;pname&gt; parameter of GetIntegerv, GetFloatv, GetDoublev,
	 * and GetBooleanv:
	 */
	int GL_PROGRAM_POINT_SIZE = 0x8642;

	void glFramebufferTexture(@GLenum int target, @GLenum int attachment, @GLuint int texture, int level);

	// --------------------------------------------------------
	// ----------------------[ ARB_sync ]----------------------
	// --------------------------------------------------------

	/** Accepted as the &lt;pname&gt; parameter of GetInteger64v: */
	int GL_MAX_SERVER_WAIT_TIMEOUT = 0x9111;

	/** Accepted as the &lt;pname&gt; parameter of GetSynciv: */
	int GL_OBJECT_TYPE    = 0x9112;
	int GL_SYNC_CONDITION = 0x9113;
	int GL_SYNC_STATUS    = 0x9114;
	int GL_SYNC_FLAGS     = 0x9115;

	/** Returned in &lt;values&gt; for GetSynciv &lt;pname&gt; OBJECT_TYPE: */
	int GL_SYNC_FENCE = 0x9116;

	/** Returned in &lt;values&gt; for GetSynciv &lt;pname&gt; SYNC_CONDITION: */
	int GL_SYNC_GPU_COMMANDS_COMPLETE = 0x9117;

	/** Returned in &lt;values&gt; for GetSynciv &lt;pname&gt; SYNC_STATUS: */
	int GL_UNSIGNALED = 0x9118;
	int GL_SIGNALED   = 0x9119;

	/** Accepted in the &lt;flags&gt; parameter of ClientWaitSync: */
	int GL_SYNC_FLUSH_COMMANDS_BIT = 0x00000001;

	/** Accepted in the &lt;timeout&gt; parameter of WaitSync: */
	long GL_TIMEOUT_IGNORED = 0xFFFFFFFFFFFFFFFFl;

	/** Returned by ClientWaitSync: */
	int GL_ALREADY_SIGNALED    = 0x911A;
	int GL_TIMEOUT_EXPIRED     = 0x911B;
	int GL_CONDITION_SATISFIED = 0x911C;
	int GL_WAIT_FAILED         = 0x911D;

	@PointerWrapper("GLsync")
	GLSync glFenceSync(@GLenum int condition, @GLbitfield int flags);

	boolean glIsSync(@PointerWrapper("GLsync") GLSync sync);

	void glDeleteSync(@PointerWrapper("GLsync") GLSync sync);

	@GLenum
	int glClientWaitSync(@PointerWrapper("GLsync") GLSync sync, @GLbitfield int flags, @GLuint64 long timeout);

	void glWaitSync(@PointerWrapper("GLsync") GLSync sync, @GLbitfield int flags, @GLuint64 long timeout);

	@StripPostfix("data")
	void glGetInteger64v(@GLenum int pname, @OutParameter @Check("1") @GLint64 LongBuffer data);

	@Alternate("glGetInteger64v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetInteger64v2(@GLenum int pname, @OutParameter @GLint64 LongBuffer data);

	@StripPostfix("data")
	@Optional(reason = "NV's 3.2 implementation does not expose this (last driver checked: 19?.??)")
	void glGetInteger64i_v(@GLenum int value, @GLuint int index, @OutParameter @Check("4") @GLint64 LongBuffer data);

	@Alternate("glGetInteger64i_v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetInteger64i_v2(@GLenum int value, @GLuint int index, @OutParameter @GLint64 LongBuffer data);

	@StripPostfix("values")
	void glGetSynciv(@PointerWrapper("GLsync") GLSync sync, @GLenum int pname, @AutoSize("values") @GLsizei int bufSize,
	                 @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                 @OutParameter IntBuffer values);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetSynci} instead. */
	@Alternate("glGetSynciv")
	@GLreturn("values")
	@StripPostfix("values")
	@Reuse(value = "GL32", method = "glGetSynci")
	@Deprecated
	void glGetSynciv2(@PointerWrapper("GLsync") GLSync sync, @GLenum int pname, @Constant("1") @GLsizei int bufSize,
	                  @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                  @OutParameter IntBuffer values);

	@Alternate("glGetSynciv")
	@GLreturn("values")
	@StripPostfix(value = "values", postfix = "v")
	void glGetSynciv3(@PointerWrapper("GLsync") GLSync sync, @GLenum int pname, @Constant("1") @GLsizei int bufSize,
	                  @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                  @OutParameter IntBuffer values);
}