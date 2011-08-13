/*
 * Copyright (c) 2002-2011 LWJGL Project
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
import java.nio.IntBuffer;

public interface GL42 {

	// ----------------------------------------------------------------------------
	// ----------------------[ ARB_texture_compression_bptc ]----------------------
	// ----------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage2D, TexImage3D,
	 * CopyTexImage2D, CopyTexImage3D, CompressedTexImage2DARB, and
	 * CompressedTexImage3DARB and the &lt;format&gt; parameter of
	 * CompressedTexSubImage2DARB and CompressedTexSubImage3DARB:
	 */
	int GL_COMPRESSED_RGBA_BPTC_UNORM         = 0x8E8C;
	int GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM   = 0x8E8D;
	int GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT   = 0x8E8E;
	int GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT = 0x8E8F;

	// ------------------------------------------------------------------------------------
	// ----------------------[ ARB_compressed_texture_pixel_storage ]----------------------
	// ------------------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of PixelStore[fi], GetBooleanv,
	 * GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_UNPACK_COMPRESSED_BLOCK_WIDTH  = 0x9127,
		GL_UNPACK_COMPRESSED_BLOCK_HEIGHT = 0x9128,
		GL_UNPACK_COMPRESSED_BLOCK_DEPTH  = 0x9129,
		GL_UNPACK_COMPRESSED_BLOCK_SIZE   = 0x912A,
		GL_PACK_COMPRESSED_BLOCK_WIDTH    = 0x912B,
		GL_PACK_COMPRESSED_BLOCK_HEIGHT   = 0x912C,
		GL_PACK_COMPRESSED_BLOCK_DEPTH    = 0x912D,
		GL_PACK_COMPRESSED_BLOCK_SIZE     = 0x912E;

	// --------------------------------------------------------------------------
	// ----------------------[ ARB_shader_atomic_counters ]----------------------
	// --------------------------------------------------------------------------

	/** Accepted by the &lt;target&gt; parameter of BindBufferBase and BindBufferRange: */
	int GL_ATOMIC_COUNTER_BUFFER = 0x92C0;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleani_v, GetIntegeri_v,
	 * GetFloati_v, GetDoublei_v, GetInteger64i_v, GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, GetDoublev, and GetActiveAtomicCounterBufferiv:
	 */
	int GL_ATOMIC_COUNTER_BUFFER_BINDING = 0x92C1;

	/** Accepted by the &lt;pname&gt; parameter of GetIntegeri_64v: */
	int GL_ATOMIC_COUNTER_BUFFER_START = 0x92C2,
		GL_ATOMIC_COUNTER_BUFFER_SIZE  = 0x92C3;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveAtomicCounterBufferiv: */
	int GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE                            = 0x92C4,
		GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS               = 0x92C5,
		GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES        = 0x92C6,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER          = 0x92C7,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER    = 0x92C8,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x92C9,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER        = 0x92CA,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER        = 0x92CB;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS          = 0x92CC,
		GL_MAX_TESS_CONTROL_ATOMIC_COUNTER_BUFFERS    = 0x92CD,
		GL_MAX_TESS_EVALUATION_ATOMIC_COUNTER_BUFFERS = 0x92CE,
		GL_MAX_GEOMETRY_ATOMIC_COUNTER_BUFFERS        = 0x92CF,
		GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS        = 0x92D0,
		GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS        = 0x92D1,
		GL_MAX_VERTEX_ATOMIC_COUNTERS                 = 0x92D2,
		GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS           = 0x92D3,
		GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS        = 0x92D4,
		GL_MAX_GEOMETRY_ATOMIC_COUNTERS               = 0x92D5,
		GL_MAX_FRAGMENT_ATOMIC_COUNTERS               = 0x92D6,
		GL_MAX_COMBINED_ATOMIC_COUNTERS               = 0x92D7,
		GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE             = 0x92D8,
		GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS         = 0x92DC;

	/** Accepted by the &lt;pname&gt; parameter of GetProgramiv: */
	int GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 0x92D9;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveUniformsiv: */
	int GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX = 0x92DA;

	/** Returned in &lt;params&gt; by GetActiveUniform and GetActiveUniformsiv: */
	int GL_UNSIGNED_INT_ATOMIC_COUNTER = 0x92DB;

	@Optional(reason = "AMD's beta 4.2 driver (11.8) does not expose this")
	@StripPostfix("params")
	void glGetActiveAtomicCounterBufferiv(@GLuint int program, @GLuint int bufferIndex, @GLenum int pname, @Check("1") @OutParameter IntBuffer params);

	@Alternate("glGetActiveAtomicCounterBufferiv")
	@StripPostfix("params")
	@GLreturn("params")
	void glGetActiveAtomicCounterBufferiv2(@GLuint int program, @GLuint int bufferIndex, @GLenum int pname, @OutParameter IntBuffer params);

	// -------------------------------------------------------------------
	// ----------------------[ ARB_texture_storage ]----------------------
	// -------------------------------------------------------------------

	/** Accepted by the &lt;value&gt; parameter of GetTexParameter{if}v: */
	int GL_TEXTURE_IMMUTABLE_FORMAT = 0x912F;

	void glTexStorage1D(@GLenum int target, @GLsizei int levels,
	                    @GLenum int internalformat,
	                    @GLsizei int width);

	void glTexStorage2D(@GLenum int target, @GLsizei int levels,
	                    @GLenum int internalformat,
	                    @GLsizei int width, @GLsizei int height);

	void glTexStorage3D(@GLenum int target, @GLsizei int levels,
	                    @GLenum int internalformat,
	                    @GLsizei int width, @GLsizei int height, @GLsizei int depth);

	// --------------------------------------------------------------------------------
	// ----------------------[ ARB_transform_feedback_instanced ]----------------------
	// --------------------------------------------------------------------------------

	void glDrawTransformFeedbackInstanced(@GLenum int mode, @GLuint int id, @GLsizei int primcount);

	void glDrawTransformFeedbackStreamInstanced(@GLenum int mode, @GLuint int id, @GLuint int stream, @GLsizei int primcount);

	// -----------------------------------------------------------------
	// ----------------------[ ARB_base_instance ]----------------------
	// -----------------------------------------------------------------

	void glDrawArraysInstancedBaseInstance(@GLenum int mode,
	                                       int first,
	                                       @GLsizei int count,
	                                       @GLsizei int primcount,
	                                       @GLuint int baseinstance);

	void glDrawElementsInstancedBaseInstance(@GLenum int mode,
	                                         @AutoSize("indices") @GLsizei int count,
	                                         @AutoType("indices") @GLenum int type,
	                                         @Const
	                                         @BufferObject(BufferKind.ElementVBO)
	                                         @GLubyte
	                                         @GLushort
	                                         @GLuint Buffer indices,
	                                         @GLsizei int primcount,
	                                         @GLuint int baseinstance);

	void glDrawElementsInstancedBaseVertexBaseInstance(@GLenum int mode,
	                                                   @AutoSize("indices") @GLsizei int count,
	                                                   @AutoType("indices") @GLenum int type,
	                                                   @Const
	                                                   @BufferObject(BufferKind.ElementVBO)
	                                                   @GLubyte
	                                                   @GLushort
	                                                   @GLuint Buffer indices,
	                                                   @GLsizei int primcount,
	                                                   int basevertex,
	                                                   @GLuint int baseinstance);

	// ---------------------------------------------------------------------------
	// ----------------------[ ARB_shader_image_load_store ]----------------------
	// ---------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, GetDoublev, and GetInteger64v:
	 */
	int GL_MAX_IMAGE_UNITS                               = 0x8F38,
		GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS = 0x8F39,
		GL_MAX_IMAGE_SAMPLES                             = 0x906D,
		GL_MAX_VERTEX_IMAGE_UNIFORMS                     = 0x90CA,
		GL_MAX_TESS_CONTROL_IMAGE_UNIFORMS               = 0x90CB,
		GL_MAX_TESS_EVALUATION_IMAGE_UNIFORMS            = 0x90CC,
		GL_MAX_GEOMETRY_IMAGE_UNIFORMS                   = 0x90CD,
		GL_MAX_FRAGMENT_IMAGE_UNIFORMS                   = 0x90CE,
		GL_MAX_COMBINED_IMAGE_UNIFORMS                   = 0x90CF;

	/** Accepted by the &lt;target&gt; parameter of GetIntegeri_v and GetBooleani_v: */
	int GL_IMAGE_BINDING_NAME    = 0x8F3A,
		GL_IMAGE_BINDING_LEVEL   = 0x8F3B,
		GL_IMAGE_BINDING_LAYERED = 0x8F3C,
		GL_IMAGE_BINDING_LAYER   = 0x8F3D,
		GL_IMAGE_BINDING_ACCESS  = 0x8F3E,
		GL_IMAGE_BINDING_FORMAT  = 0x906E;

	/** Accepted by the &lt;barriers&gt; parameter of MemoryBarrier: */
	int GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 0x00000001,
		GL_ELEMENT_ARRAY_BARRIER_BIT       = 0x00000002,
		GL_UNIFORM_BARRIER_BIT             = 0x00000004,
		GL_TEXTURE_FETCH_BARRIER_BIT       = 0x00000008,
		GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 0x00000020,
		GL_COMMAND_BARRIER_BIT             = 0x00000040,
		GL_PIXEL_BUFFER_BARRIER_BIT        = 0x00000080,
		GL_TEXTURE_UPDATE_BARRIER_BIT      = 0x00000100,
		GL_BUFFER_UPDATE_BARRIER_BIT       = 0x00000200,
		GL_FRAMEBUFFER_BARRIER_BIT         = 0x00000400,
		GL_TRANSFORM_FEEDBACK_BARRIER_BIT  = 0x00000800,
		GL_ATOMIC_COUNTER_BARRIER_BIT      = 0x00001000,
		GL_ALL_BARRIER_BITS                = 0xFFFFFFFF;

	/** Returned by the &lt;type&gt; parameter of GetActiveUniform: */
	int GL_IMAGE_1D                                = 0x904C,
		GL_IMAGE_2D                                = 0x904D,
		GL_IMAGE_3D                                = 0x904E,
		GL_IMAGE_2D_RECT                           = 0x904F,
		GL_IMAGE_CUBE                              = 0x9050,
		GL_IMAGE_BUFFER                            = 0x9051,
		GL_IMAGE_1D_ARRAY                          = 0x9052,
		GL_IMAGE_2D_ARRAY                          = 0x9053,
		GL_IMAGE_CUBE_MAP_ARRAY                    = 0x9054,
		GL_IMAGE_2D_MULTISAMPLE                    = 0x9055,
		GL_IMAGE_2D_MULTISAMPLE_ARRAY              = 0x9056,
		GL_INT_IMAGE_1D                            = 0x9057,
		GL_INT_IMAGE_2D                            = 0x9058,
		GL_INT_IMAGE_3D                            = 0x9059,
		GL_INT_IMAGE_2D_RECT                       = 0x905A,
		GL_INT_IMAGE_CUBE                          = 0x905B,
		GL_INT_IMAGE_BUFFER                        = 0x905C,
		GL_INT_IMAGE_1D_ARRAY                      = 0x905D,
		GL_INT_IMAGE_2D_ARRAY                      = 0x905E,
		GL_INT_IMAGE_CUBE_MAP_ARRAY                = 0x905F,
		GL_INT_IMAGE_2D_MULTISAMPLE                = 0x9060,
		GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY          = 0x9061,
		GL_UNSIGNED_INT_IMAGE_1D                   = 0x9062,
		GL_UNSIGNED_INT_IMAGE_2D                   = 0x9063,
		GL_UNSIGNED_INT_IMAGE_3D                   = 0x9064,
		GL_UNSIGNED_INT_IMAGE_2D_RECT              = 0x9065,
		GL_UNSIGNED_INT_IMAGE_CUBE                 = 0x9066,
		GL_UNSIGNED_INT_IMAGE_BUFFER               = 0x9067,
		GL_UNSIGNED_INT_IMAGE_1D_ARRAY             = 0x9068,
		GL_UNSIGNED_INT_IMAGE_2D_ARRAY             = 0x9069,
		GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY       = 0x906A,
		GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE       = 0x906B,
		GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 0x906C;

	/**
	 * Accepted by the &lt;value&gt; parameter of GetTexParameteriv, GetTexParameterfv,
	 * GetTexParameterIiv, and GetTexParameterIuiv:
	 */
	int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 0x90C7;

	/**
	 * Returned in the &lt;data&gt; parameter of GetTexParameteriv, GetTexParameterfv,
	 * GetTexParameterIiv, and GetTexParameterIuiv when &lt;value&gt; is
	 * IMAGE_FORMAT_COMPATIBILITY_TYPE:
	 */
	int GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE = 0x90C8,
		IMAGE_FORMAT_COMPATIBILITY_BY_CLASS   = 0x90C9;

	void glBindImageTexture(@GLuint int unit, @GLuint int texture, int level,
	                        boolean layered, int layer, @GLenum int access,
	                        @GLenum int format);

	void glMemoryBarrier(@GLbitfield int barriers);

	// -------------------------------------------------------------------------
	// ----------------------[ ARB_internal_format_query ]----------------------
	// -------------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetInternalformativ: */
	int GL_NUM_SAMPLE_COUNTS = 0x9380;

	@StripPostfix("params")
	void glGetInternalformativ(@GLenum int target, @GLenum int internalformat,
	                           @GLenum int pname, @AutoSize("params") @GLsizei int bufSize, @OutParameter IntBuffer params);

	@Alternate("glGetInternalformativ")
	@StripPostfix("params")
	@GLreturn("params")
	void glGetInternalformativ2(@GLenum int target, @GLenum int internalformat,
	                            @GLenum int pname, @Constant("1") @GLsizei int bufSize, @OutParameter IntBuffer params);

	// ------------------------------------------------------------------------
	// ----------------------[ ARB_map_buffer_alignment ]----------------------
	// ------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_MIN_MAP_BUFFER_ALIGNMENT = 0x90BC;

}