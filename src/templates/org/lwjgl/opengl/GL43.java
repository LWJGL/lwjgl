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
import java.nio.LongBuffer;

import com.sun.mirror.type.PrimitiveType;

public interface GL43 {

	/** No. of supported Shading Language Versions. Accepted by the &lt;pname&gt; parameter of GetIntegerv. */
	int GL_NUM_SHADING_LANGUAGE_VERSIONS = 0x82E9;

	/** Vertex attrib array has unconverted doubles. Accepted by the &lt;pname&gt; parameter of GetVertexAttribiv. */
	int GL_VERTEX_ATTRIB_ARRAY_LONG = 0x874E;

	// ---------------------------------------------------------------------
	// ----------------------[ ARB_ES3_compatibility ]----------------------
	// ---------------------------------------------------------------------

	/** Accepted by the &lt;internalformat&gt; parameter of CompressedTexImage2D */
	int GL_COMPRESSED_RGB8_ETC2                      = 0x9274,
		GL_COMPRESSED_SRGB8_ETC2                     = 0x9275,
		GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2  = 0x9276,
		GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9277,
		GL_COMPRESSED_RGBA8_ETC2_EAC                 = 0x9278,
		GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC          = 0x9279,
		GL_COMPRESSED_R11_EAC                        = 0x9270,
		GL_COMPRESSED_SIGNED_R11_EAC                 = 0x9271,
		GL_COMPRESSED_RG11_EAC                       = 0x9272,
		GL_COMPRESSED_SIGNED_RG11_EAC                = 0x9273;

	/** Accepted by the &lt;target&gt; parameter of Enable and Disable: */
	int GL_PRIMITIVE_RESTART_FIXED_INDEX = 0x8D69;

	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQuery, EndQuery,
	 * GetQueryIndexediv and GetQueryiv:
	 */
	int GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 0x8D6A;

	/** Accepted by the &lt;value&gt; parameter of the GetInteger* functions: */
	int GL_MAX_ELEMENT_INDEX = 0x8D6B;

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_clear_buffer_object ]----------------------
	// -----------------------------------------------------------------------

	void glClearBufferData(@GLenum int target,
	                       @GLenum int internalformat,
	                       @GLenum int format,
	                       @GLenum int type,
	                       @Check("1") @Const @GLvoid ByteBuffer data);

	void glClearBufferSubData(@GLenum int target,
	                          @GLenum int internalformat,
	                          @GLintptr long offset,
	                          @AutoSize("data") @GLsizeiptr long size,
	                          @GLenum int format,
	                          @GLenum int type,
	                          @Const @GLvoid ByteBuffer data);

	// ------------------------------------------------------------------
	// ----------------------[ ARB_compute_shader ]----------------------
	// ------------------------------------------------------------------

	/**
	 * Accepted by the &lt;type&gt; parameter of CreateShader and returned in the
	 * &lt;params&gt; parameter by GetShaderiv:
	 */
	int GL_COMPUTE_SHADER = 0x91B9;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegerv, GetBooleanv, GetFloatv,
	 * GetDoublev and GetInteger64v:
	 */
	int GL_MAX_COMPUTE_UNIFORM_BLOCKS              = 0x91BB,
		GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS         = 0x91BC,
		GL_MAX_COMPUTE_IMAGE_UNIFORMS              = 0x91BD,
		GL_MAX_COMPUTE_SHARED_MEMORY_SIZE          = 0x8262,
		GL_MAX_COMPUTE_UNIFORM_COMPONENTS          = 0x8263,
		GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS      = 0x8264,
		GL_MAX_COMPUTE_ATOMIC_COUNTERS             = 0x8265,
		GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 0x8266,
		GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS      = 0x90EB;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegeri_v, GetBooleani_v,
	 * GetFloati_v, GetDoublei_v and GetInteger64i_v:
	 */

	int GL_MAX_COMPUTE_WORK_GROUP_COUNT = 0x91BE,
		GL_MAX_COMPUTE_WORK_GROUP_SIZE  = 0x91BF;

	/** Accepted by the &lt;pname&gt; parameter of GetProgramiv: */
	int GL_COMPUTE_WORK_GROUP_SIZE = 0x8267;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveUniformBlockiv: */
	int GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 0x90EC;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveAtomicCounterBufferiv: */
	int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 0x90ED;

	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, and
	 * GetBufferPointerv:
	 */
	int GL_DISPATCH_INDIRECT_BUFFER = 0x90EE;

	/**
	 * Accepted by the &lt;value&gt; parameter of GetIntegerv, GetBooleanv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_DISPATCH_INDIRECT_BUFFER_BINDING = 0x90EF;

	/** Accepted by the &lt;stages&gt; parameter of UseProgramStages: */
	int GL_COMPUTE_SHADER_BIT = 0x00000020;

	void glDispatchCompute(@GLuint int num_groups_x,
	                       @GLuint int num_groups_y,
	                       @GLuint int num_groups_z);

	void glDispatchComputeIndirect(@GLintptr long indirect);

	// --------------------------------------------------------------
	// ----------------------[ ARB_copy_image ]----------------------
	// --------------------------------------------------------------

	void glCopyImageSubData(
		@GLuint int srcName, @GLenum int srcTarget, int srcLevel,
		int srcX, int srcY, int srcZ,
		@GLuint int dstName, @GLenum int dstTarget, int dstLevel,
		int dstX, int dstY, int dstZ,
		@GLsizei int srcWidth, @GLsizei int srcHeight, @GLsizei int srcDepth);

	// ----------------------------------------------------------------
	// ----------------------[ KHR_debug_output ]----------------------
	// ----------------------[ ARB_debug_output2 ]---------------------
	// ----------------------[ ARB_debug_group ]-----------------------
	// ----------------------[ ARB_debug_label ]-----------------------
	// ----------------------------------------------------------------

	/**
	 * Tokens accepted by the &lt;target&gt; parameters of Enable, Disable, and
	 * IsEnabled:
	 */
	int GL_DEBUG_OUTPUT             = 0x92E0,
		GL_DEBUG_OUTPUT_SYNCHRONOUS = 0x8242;

	/** Returned by GetIntegerv when &lt;pname&gt; is CONTEXT_FLAGS: */
	int GL_CONTEXT_FLAG_DEBUG_BIT = 0x00000002;

	/**
	 * Tokens accepted by the &lt;value&gt; parameters of GetBooleanv, GetIntegerv,
	 * GetFloatv, GetDoublev and GetInteger64v:
	 */
	int GL_MAX_DEBUG_MESSAGE_LENGTH         = 0x9143,
		GL_MAX_DEBUG_LOGGED_MESSAGES        = 0x9144,
		GL_DEBUG_LOGGED_MESSAGES            = 0x9145,
		GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 0x8243,
		GL_MAX_DEBUG_GROUP_STACK_DEPTH      = 0x826C,
		GL_DEBUG_GROUP_STACK_DEPTH          = 0x826D,
		GL_MAX_LABEL_LENGTH                 = 0x82E8;

	/** Tokens accepted by the &lt;pname&gt; parameter of GetPointerv: */
	int GL_DEBUG_CALLBACK_FUNCTION   = 0x8244,
		GL_DEBUG_CALLBACK_USER_PARAM = 0x8245;

	/**
	 * Tokens accepted or provided by the &lt;source&gt; parameters of
	 * DebugMessageControl, DebugMessageInsert and DEBUGPROC, and the &lt;sources&gt;
	 * parameter of GetDebugMessageLog:
	 */
	int GL_DEBUG_SOURCE_API             = 0x8246,
		GL_DEBUG_SOURCE_WINDOW_SYSTEM   = 0x8247,
		GL_DEBUG_SOURCE_SHADER_COMPILER = 0x8248,
		GL_DEBUG_SOURCE_THIRD_PARTY     = 0x8249,
		GL_DEBUG_SOURCE_APPLICATION     = 0x824A,
		GL_DEBUG_SOURCE_OTHER           = 0x824B;

	/**
	 * Tokens accepted or provided by the &lt;type&gt; parameters of
	 * DebugMessageControl, DebugMessageInsert and DEBUGPROC, and the &lt;types&gt;
	 * parameter of GetDebugMessageLog:
	 */
	int GL_DEBUG_TYPE_ERROR               = 0x824C,
		GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 0x824D,
		GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR  = 0x824E,
		GL_DEBUG_TYPE_PORTABILITY         = 0x824F,
		GL_DEBUG_TYPE_PERFORMANCE         = 0x8250,
		GL_DEBUG_TYPE_OTHER               = 0x8251,
		GL_DEBUG_TYPE_MARKER              = 0x8268;

	/**
	 * Tokens accepted or provided by the &lt;type&gt; parameters of
	 * DebugMessageControl and DEBUGPROC, and the &lt;types&gt; parameter of
	 * GetDebugMessageLog:
	 */
	int GL_DEBUG_TYPE_PUSH_GROUP = 0x8269,
		GL_DEBUG_TYPE_POP_GROUP  = 0x826A;

	/**
	 * Tokens accepted or provided by the &lt;severity&gt; parameters of
	 * DebugMessageControl, DebugMessageInsert and DEBUGPROC callback functions,
	 * and the &lt;severities&gt; parameter of GetDebugMessageLog:
	 */
	int GL_DEBUG_SEVERITY_HIGH         = 0x9146,
		GL_DEBUG_SEVERITY_MEDIUM       = 0x9147,
		GL_DEBUG_SEVERITY_LOW          = 0x9148,
		GL_DEBUG_SEVERITY_NOTIFICATION = 0x826B;

	/** Returned by GetError: */
	int GL_STACK_UNDERFLOW = 0x0504,
		GL_STACK_OVERFLOW  = 0x0503;

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

	// -----------------------------

	void glDebugMessageControl(@GLenum int source,
	                           @GLenum int type,
	                           @GLenum int severity,
	                           @AutoSize(value = "ids", canBeNull = true) @GLsizei int count,
	                           @Check(canBeNull = true) @Const @GLuint IntBuffer ids,
	                           boolean enabled);

	void glDebugMessageInsert(@GLenum int source,
	                          @GLenum int type,
	                          @GLuint int id,
	                          @GLenum int severity,
	                          @AutoSize("buf") @GLsizei int length,
	                          @Const @GLchar ByteBuffer buf);

	@Alternate("glDebugMessageInsert")
	void glDebugMessageInsert(@GLenum int source,
	                          @GLenum int type,
	                          @GLuint int id,
	                          @GLenum int severity,
	                          @Constant("buf.length()") @GLsizei int length,
	                          CharSequence buf);

	/**
	 * The {@code KHRDebugCallback.Handler} implementation passed to this method will be used for
	 * KHR_debug messages. If callback is null, any previously registered handler for the current
	 * thread will be unregistered and stop receiving messages.
	 *
	 * @param callback the callback function to use
	 */
	@Code(
		// Create a GlobalRef to the callback object and register it with the current context.
		javaBeforeNative = "\t\tlong userParam = callback == null ? 0 : CallbackUtil.createGlobalRef(callback.getHandler());\n" +
		                   "\t\tCallbackUtil.registerContextCallbackKHR(userParam);"
	)
	void glDebugMessageCallback(@PointerWrapper(value = "GLDEBUGPROC", canBeNull = true) KHRDebugCallback callback,
	                            @Constant("userParam") @PointerWrapper("GLvoid *") long userParam);

	@GLuint
	int glGetDebugMessageLog(@GLuint int count,
	                         @AutoSize(value = "messageLog", canBeNull = true) @GLsizei int bufsize,
	                         @Check(value = "count", canBeNull = true) @GLenum IntBuffer sources,
	                         @Check(value = "count", canBeNull = true) @GLenum IntBuffer types,
	                         @Check(value = "count", canBeNull = true) @GLuint IntBuffer ids,
	                         @Check(value = "count", canBeNull = true) @GLenum IntBuffer severities,
	                         @Check(value = "count", canBeNull = true) @GLsizei IntBuffer lengths,
	                         @Check(canBeNull = true) @OutParameter @GLchar ByteBuffer messageLog);

	void glPushDebugGroup(@GLenum int source, @GLuint int id, @AutoSize("message") @GLsizei int length,
	                      @Const @GLchar ByteBuffer message);

	@Alternate("glPushDebugGroup")
	void glPushDebugGroup(@GLenum int source, @GLuint int id, @Constant("message.length()") @GLsizei int length,
	                      CharSequence message);

	void glPopDebugGroup();

	void glObjectLabel(@GLenum int identifier, @GLuint int name, @AutoSize(value = "label", canBeNull = true) @GLsizei int length,
	                   @Check(canBeNull = true) @Const @GLchar ByteBuffer label);

	@Alternate("glObjectLabel")
	void glObjectLabel(@GLenum int identifier, @GLuint int name, @Constant("label.length()") @GLsizei int length,
	                   CharSequence label);

	void glGetObjectLabel(@GLenum int identifier, @GLuint int name, @AutoSize("label") @GLsizei int bufSize,
	                      @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                      @OutParameter @GLchar ByteBuffer label);

	@Alternate("glGetObjectLabel")
	@GLreturn(value = "label", maxLength = "bufSize")
	void glGetObjectLabel2(@GLenum int identifier, @GLuint int name, @GLsizei int bufSize,
	                       @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(label_length)") IntBuffer length,
	                       @OutParameter @GLchar ByteBuffer label);

	void glObjectPtrLabel(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @AutoSize(value = "label", canBeNull = true) @GLsizei int length,
	                      @Check(canBeNull = true) @Const @GLchar ByteBuffer label);

	@Alternate("glObjectPtrLabel")
	void glObjectPtrLabel(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @Constant("label.length()") @GLsizei int length,
	                      CharSequence label);

	void glGetObjectPtrLabel(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @AutoSize("label") @GLsizei int bufSize,
	                         @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                         @OutParameter @GLchar ByteBuffer label);

	@Alternate("glGetObjectPtrLabel")
	@GLreturn(value = "label", maxLength = "bufSize")
	void glGetObjectPtrLabel2(@PointerWrapper("GLvoid *") org.lwjgl.PointerWrapper ptr, @GLsizei int bufSize,
	                          @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(label_length)") IntBuffer length,
	                          @OutParameter @GLchar ByteBuffer label);

	// -----------------------------------------------------------------------------
	// ----------------------[ ARB_explicit_uniform_location ]----------------------
	// -----------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, GetDoublev, and GetInteger64v:
	 */
	int GL_MAX_UNIFORM_LOCATIONS = 0x826E;

	// -----------------------------------------------------------------------------
	// ----------------------[ ARB_framebuffer_no_attachment ]----------------------
	// -----------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of FramebufferParameteri,
	 * GetFramebufferParameteriv, NamedFramebufferParameteriEXT, and
	 * GetNamedFramebufferParameterivEXT:
	 */
	int GL_FRAMEBUFFER_DEFAULT_WIDTH                  = 0x9310,
		GL_FRAMEBUFFER_DEFAULT_HEIGHT                 = 0x9311,
		GL_FRAMEBUFFER_DEFAULT_LAYERS                 = 0x9312,
		GL_FRAMEBUFFER_DEFAULT_SAMPLES                = 0x9313,
		GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 0x9314;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegerv, GetBooleanv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_MAX_FRAMEBUFFER_WIDTH   = 0x9315,
		GL_MAX_FRAMEBUFFER_HEIGHT  = 0x9316,
		GL_MAX_FRAMEBUFFER_LAYERS  = 0x9317,
		GL_MAX_FRAMEBUFFER_SAMPLES = 0x9318;

	void glFramebufferParameteri(@GLenum int target, @GLenum int pname, int param);

	@StripPostfix("params")
	void glGetFramebufferParameteriv(@GLenum int target, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetFramebufferParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetFramebufferParameteriv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	// -----------------------------------------------------------------------------
	// ----------------------[ ARB_internalformat_query2 ]----------------------
	// -----------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of GetInternalformativ
	 * and GetInternalformati64v:
	 */
	int GL_TEXTURE_1D                   = 0x0DE0,
		GL_TEXTURE_1D_ARRAY             = 0x8C18,
		GL_TEXTURE_2D                   = 0x0DE1,
		GL_TEXTURE_2D_ARRAY             = 0x8C1A,
		GL_TEXTURE_3D                   = 0x806F,
		GL_TEXTURE_CUBE_MAP             = 0x8513,
		GL_TEXTURE_CUBE_MAP_ARRAY       = 0x9009,
		GL_TEXTURE_RECTANGLE            = 0x84F5,
		GL_TEXTURE_BUFFER               = 0x8C2A,
		GL_RENDERBUFFER                 = 0x8D41,
		GL_TEXTURE_2D_MULTISAMPLE       = 0x9100,
		GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 0x9102;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetInternalformativ
	 * and GetInternalformati64v:
	 */
	int GL_SAMPLES                                = 0x80A9,
		GL_NUM_SAMPLE_COUNTS                      = 0x9380,
		GL_INTERNALFORMAT_SUPPORTED               = 0x826F,
		GL_INTERNALFORMAT_PREFERRED               = 0x8270,
		GL_INTERNALFORMAT_RED_SIZE                = 0x8271,
		GL_INTERNALFORMAT_GREEN_SIZE              = 0x8272,
		GL_INTERNALFORMAT_BLUE_SIZE               = 0x8273,
		GL_INTERNALFORMAT_ALPHA_SIZE              = 0x8274,
		GL_INTERNALFORMAT_DEPTH_SIZE              = 0x8275,
		GL_INTERNALFORMAT_STENCIL_SIZE            = 0x8276,
		GL_INTERNALFORMAT_SHARED_SIZE             = 0x8277,
		GL_INTERNALFORMAT_RED_TYPE                = 0x8278,
		GL_INTERNALFORMAT_GREEN_TYPE              = 0x8279,
		GL_INTERNALFORMAT_BLUE_TYPE               = 0x827A,
		GL_INTERNALFORMAT_ALPHA_TYPE              = 0x827B,
		GL_INTERNALFORMAT_DEPTH_TYPE              = 0x827C,
		GL_INTERNALFORMAT_STENCIL_TYPE            = 0x827D,
		GL_MAX_WIDTH                              = 0x827E,
		GL_MAX_HEIGHT                             = 0x827F,
		GL_MAX_DEPTH                              = 0x8280,
		GL_MAX_LAYERS                             = 0x8281,
		GL_MAX_COMBINED_DIMENSIONS                = 0x8282,
		GL_COLOR_COMPONENTS                       = 0x8283,
		GL_DEPTH_COMPONENTS                       = 0x8284,
		GL_STENCIL_COMPONENTS                     = 0x8285,
		GL_COLOR_RENDERABLE                       = 0x8286,
		GL_DEPTH_RENDERABLE                       = 0x8287,
		GL_STENCIL_RENDERABLE                     = 0x8288,
		GL_FRAMEBUFFER_RENDERABLE                 = 0x8289,
		GL_FRAMEBUFFER_RENDERABLE_LAYERED         = 0x828A,
		GL_FRAMEBUFFER_BLEND                      = 0x828B,
		GL_READ_PIXELS                            = 0x828C,
		GL_READ_PIXELS_FORMAT                     = 0x828D,
		GL_READ_PIXELS_TYPE                       = 0x828E,
		GL_TEXTURE_IMAGE_FORMAT                   = 0x828F,
		GL_TEXTURE_IMAGE_TYPE                     = 0x8290,
		GL_GET_TEXTURE_IMAGE_FORMAT               = 0x8291,
		GL_GET_TEXTURE_IMAGE_TYPE                 = 0x8292,
		GL_MIPMAP                                 = 0x8293,
		GL_MANUAL_GENERATE_MIPMAP                 = 0x8294,
		GL_AUTO_GENERATE_MIPMAP                   = 0x8295,
		GL_COLOR_ENCODING                         = 0x8296,
		GL_SRGB_READ                              = 0x8297,
		GL_SRGB_WRITE                             = 0x8298,
		GL_SRGB_DECODE_ARB                        = 0x8299,
		GL_FILTER                                 = 0x829A,
		GL_VERTEX_TEXTURE                         = 0x829B,
		GL_TESS_CONTROL_TEXTURE                   = 0x829C,
		GL_TESS_EVALUATION_TEXTURE                = 0x829D,
		GL_GEOMETRY_TEXTURE                       = 0x829E,
		GL_FRAGMENT_TEXTURE                       = 0x829F,
		GL_COMPUTE_TEXTURE                        = 0x82A0,
		GL_TEXTURE_SHADOW                         = 0x82A1,
		GL_TEXTURE_GATHER                         = 0x82A2,
		GL_TEXTURE_GATHER_SHADOW                  = 0x82A3,
		GL_SHADER_IMAGE_LOAD                      = 0x82A4,
		GL_SHADER_IMAGE_STORE                     = 0x82A5,
		GL_SHADER_IMAGE_ATOMIC                    = 0x82A6,
		GL_IMAGE_TEXEL_SIZE                       = 0x82A7,
		GL_IMAGE_COMPATIBILITY_CLASS              = 0x82A8,
		GL_IMAGE_PIXEL_FORMAT                     = 0x82A9,
		GL_IMAGE_PIXEL_TYPE                       = 0x82AA,
		GL_IMAGE_FORMAT_COMPATIBILITY_TYPE        = 0x90C7,
		GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST    = 0x82AC,
		GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST  = 0x82AD,
		GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE   = 0x82AE,
		GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 0x82AF,
		GL_TEXTURE_COMPRESSED                     = 0x86A1,
		GL_TEXTURE_COMPRESSED_BLOCK_WIDTH         = 0x82B1,
		GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT        = 0x82B2,
		GL_TEXTURE_COMPRESSED_BLOCK_SIZE          = 0x82B3,
		GL_CLEAR_BUFFER                           = 0x82B4,
		GL_TEXTURE_VIEW                           = 0x82B5,
		GL_VIEW_COMPATIBILITY_CLASS               = 0x82B6;

	/**
	 * Returned as possible responses for various &lt;pname&gt; queries
	 * to GetInternalformativ and GetInternalformati64v
	 */
	int GL_FULL_SUPPORT              = 0x82B7,
		GL_CAVEAT_SUPPORT            = 0x82B8,
		GL_IMAGE_CLASS_4_X_32        = 0x82B9,
		GL_IMAGE_CLASS_2_X_32        = 0x82BA,
		GL_IMAGE_CLASS_1_X_32        = 0x82BB,
		GL_IMAGE_CLASS_4_X_16        = 0x82BC,
		GL_IMAGE_CLASS_2_X_16        = 0x82BD,
		GL_IMAGE_CLASS_1_X_16        = 0x82BE,
		GL_IMAGE_CLASS_4_X_8         = 0x82BF,
		GL_IMAGE_CLASS_2_X_8         = 0x82C0,
		GL_IMAGE_CLASS_1_X_8         = 0x82C1,
		GL_IMAGE_CLASS_11_11_10      = 0x82C2,
		GL_IMAGE_CLASS_10_10_10_2    = 0x82C3,
		GL_VIEW_CLASS_128_BITS       = 0x82C4,
		GL_VIEW_CLASS_96_BITS        = 0x82C5,
		GL_VIEW_CLASS_64_BITS        = 0x82C6,
		GL_VIEW_CLASS_48_BITS        = 0x82C7,
		GL_VIEW_CLASS_32_BITS        = 0x82C8,
		GL_VIEW_CLASS_24_BITS        = 0x82C9,
		GL_VIEW_CLASS_16_BITS        = 0x82CA,
		GL_VIEW_CLASS_8_BITS         = 0x82CB,
		GL_VIEW_CLASS_S3TC_DXT1_RGB  = 0x82CC,
		GL_VIEW_CLASS_S3TC_DXT1_RGBA = 0x82CD,
		GL_VIEW_CLASS_S3TC_DXT3_RGBA = 0x82CE,
		GL_VIEW_CLASS_S3TC_DXT5_RGBA = 0x82CF,
		GL_VIEW_CLASS_RGTC1_RED      = 0x82D0,
		GL_VIEW_CLASS_RGTC2_RG       = 0x82D1,
		GL_VIEW_CLASS_BPTC_UNORM     = 0x82D2,
		GL_VIEW_CLASS_BPTC_FLOAT     = 0x82D3;

	@StripPostfix("params")
	void glGetInternalformati64v(@GLenum int target, @GLenum int internalformat,
	                             @GLenum int pname, @AutoSize("params") @GLsizei int bufSize, @OutParameter @GLint64 LongBuffer params);

	@Alternate("glGetInternalformati64v")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetInternalformati64v2(@GLenum int target, @GLenum int internalformat,
	                              @GLenum int pname, @Constant("1") @GLsizei int bufSize, @OutParameter @GLint64 LongBuffer params);

	// ----------------------------------------------------------------------
	// ----------------------[ ARB_invalidate_subdata ]----------------------
	// ----------------------------------------------------------------------

	void glInvalidateTexSubImage(@GLuint int texture, int level,
	                             int xoffset, int yoffset, int zoffset,
	                             @GLsizei int width, @GLsizei int height, @GLsizei int depth);

	void glInvalidateTexImage(@GLuint int texture, int level);

	void glInvalidateBufferSubData(@GLuint int buffer, @GLintptr long offset, @GLsizeiptr long length);

	void glInvalidateBufferData(@GLuint int buffer);

	void glInvalidateFramebuffer(@GLenum int target,
	                             @AutoSize("attachments") @GLsizei int numAttachments,
	                             @Const @GLenum IntBuffer attachments);

	void glInvalidateSubFramebuffer(@GLenum int target,
	                                @AutoSize("attachments") @GLsizei int numAttachments,
	                                @Const @GLenum IntBuffer attachments,
	                                int x, int y, @GLsizei int width, @GLsizei int height);

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_multi_draw_indirect ]----------------------
	// -----------------------------------------------------------------------

	void glMultiDrawArraysIndirect(@GLenum int mode,
	                               @BufferObject(BufferKind.IndirectBO) @Check("(stride == 0 ? 4 * 4 : stride) * primcount") @Const @GLvoid ByteBuffer indirect,
	                               @GLsizei int primcount,
	                               @GLsizei int stride);

	@Alternate("glMultiDrawArraysIndirect")
	void glMultiDrawArraysIndirect(@GLenum int mode,
	                               @BufferObject(BufferKind.IndirectBO) @Check("(stride == 0 ? 4 : stride >> 2) * primcount") @Const @GLvoid(PrimitiveType.Kind.INT) IntBuffer indirect,
	                               @GLsizei int primcount,
	                               @GLsizei int stride);

	void glMultiDrawElementsIndirect(@GLenum int mode,
	                                 @GLenum int type,
	                                 @BufferObject(BufferKind.IndirectBO) @Check("(stride == 0 ? 5 * 4 : stride) * primcount") @Const @GLvoid ByteBuffer indirect,
	                                 @GLsizei int primcount,
	                                 @GLsizei int stride);

	@Alternate("glMultiDrawElementsIndirect")
	void glMultiDrawElementsIndirect(@GLenum int mode,
	                                 @GLenum int type,
	                                 @BufferObject(BufferKind.IndirectBO) @Check("(stride == 0 ? 5 : stride >> 2) * primcount") @Const @GLvoid(PrimitiveType.Kind.INT) IntBuffer indirect,
	                                 @GLsizei int primcount,
	                                 @GLsizei int stride);

	// ---------------------------------------------------------------------------
	// ----------------------[ ARB_program_interface_query ]----------------------
	// ---------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;programInterface&gt; parameter of GetProgramInterfaceiv,
	 * GetProgramResourceIndex, GetProgramResourceName, GetProgramResourceiv,
	 * GetProgramResourceLocation, and GetProgramResourceLocationIndex:
	 */
	int GL_UNIFORM                            = 0x92E1,
		GL_UNIFORM_BLOCK                      = 0x92E2,
		GL_PROGRAM_INPUT                      = 0x92E3,
		GL_PROGRAM_OUTPUT                     = 0x92E4,
		GL_BUFFER_VARIABLE                    = 0x92E5,
		GL_SHADER_STORAGE_BLOCK               = 0x92E6,
		GL_VERTEX_SUBROUTINE                  = 0x92E8,
		GL_TESS_CONTROL_SUBROUTINE            = 0x92E9,
		GL_TESS_EVALUATION_SUBROUTINE         = 0x92EA,
		GL_GEOMETRY_SUBROUTINE                = 0x92EB,
		GL_FRAGMENT_SUBROUTINE                = 0x92EC,
		GL_COMPUTE_SUBROUTINE                 = 0x92ED,
		GL_VERTEX_SUBROUTINE_UNIFORM          = 0x92EE,
		GL_TESS_CONTROL_SUBROUTINE_UNIFORM    = 0x92EF,
		GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 0x92F0,
		GL_GEOMETRY_SUBROUTINE_UNIFORM        = 0x92F1,
		GL_FRAGMENT_SUBROUTINE_UNIFORM        = 0x92F2,
		GL_COMPUTE_SUBROUTINE_UNIFORM         = 0x92F3,
		GL_TRANSFORM_FEEDBACK_VARYING         = 0x92F4;

	/** Accepted by the &lt;pname&gt; parameter of GetProgramInterfaceiv: */
	int GL_ACTIVE_RESOURCES               = 0x92F5,
		GL_MAX_NAME_LENGTH                = 0x92F6,
		GL_MAX_NUM_ACTIVE_VARIABLES       = 0x92F7,
		GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 0x92F8;

	/** Accepted in the &lt;props&gt; array of GetProgramResourceiv: */
	int GL_NAME_LENGTH                          = 0x92F9,
		GL_TYPE                                 = 0x92FA,
		GL_ARRAY_SIZE                           = 0x92FB,
		GL_OFFSET                               = 0x92FC,
		GL_BLOCK_INDEX                          = 0x92FD,
		GL_ARRAY_STRIDE                         = 0x92FE,
		GL_MATRIX_STRIDE                        = 0x92FF,
		GL_IS_ROW_MAJOR                         = 0x9300,
		GL_ATOMIC_COUNTER_BUFFER_INDEX          = 0x9301,
		GL_BUFFER_BINDING                       = 0x9302,
		GL_BUFFER_DATA_SIZE                     = 0x9303,
		GL_NUM_ACTIVE_VARIABLES                 = 0x9304,
		GL_ACTIVE_VARIABLES                     = 0x9305,
		GL_REFERENCED_BY_VERTEX_SHADER          = 0x9306,
		GL_REFERENCED_BY_TESS_CONTROL_SHADER    = 0x9307,
		GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x9308,
		GL_REFERENCED_BY_GEOMETRY_SHADER        = 0x9309,
		GL_REFERENCED_BY_FRAGMENT_SHADER        = 0x930A,
		GL_REFERENCED_BY_COMPUTE_SHADER         = 0x930B,
		GL_TOP_LEVEL_ARRAY_SIZE                 = 0x930C,
		GL_TOP_LEVEL_ARRAY_STRIDE               = 0x930D,
		GL_LOCATION                             = 0x930E,
		GL_LOCATION_INDEX                       = 0x930F,
		GL_IS_PER_PATCH                         = 0x92E7;

	@StripPostfix("params")
	void glGetProgramInterfaceiv(@GLuint int program, @GLenum int programInterface,
	                             @GLenum int pname, @Check("1") @OutParameter IntBuffer params);

	@Alternate("glGetProgramInterfaceiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetProgramInterfaceiv2(@GLuint int program, @GLenum int programInterface,
	                              @GLenum int pname, @OutParameter IntBuffer params);

	@GLuint
	int glGetProgramResourceIndex(@GLuint int program, @GLenum int programInterface,
	                              @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetProgramResourceIndex")
	@GLuint
	int glGetProgramResourceIndex(@GLuint int program, @GLenum int programInterface,
	                              @NullTerminated CharSequence name);

	void glGetProgramResourceName(@GLuint int program, @GLenum int programInterface,
	                              @GLuint int index, @AutoSize(value = "name", canBeNull = true) @GLsizei int bufSize, @Check(value = "1", canBeNull = true) @OutParameter @GLsizei IntBuffer length,
	                              @Check(canBeNull = true) @OutParameter @GLchar ByteBuffer name);

	@Alternate("glGetProgramResourceName")
	@GLreturn(value = "name", maxLength = "bufSize")
	void glGetProgramResourceName2(@GLuint int program, @GLenum int programInterface,
	                               @GLuint int index, @GLsizei int bufSize,
	                               @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length)") IntBuffer length,
	                               @OutParameter @GLchar ByteBuffer name);

	@StripPostfix("params")
	void glGetProgramResourceiv(@GLuint int program, @GLenum int programInterface,
	                            @GLuint int index, @AutoSize("props") @GLsizei int propCount,
	                            @Const @GLenum IntBuffer props, @AutoSize("params") @GLsizei int bufSize,
	                            @Check(value = "1", canBeNull = true) @OutParameter @GLsizei IntBuffer length, @OutParameter IntBuffer params);

	int glGetProgramResourceLocation(@GLuint int program, @GLenum int programInterface,
	                                 @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetProgramResourceLocation")
	int glGetProgramResourceLocation(@GLuint int program, @GLenum int programInterface,
	                                 @NullTerminated CharSequence name);

	int glGetProgramResourceLocationIndex(@GLuint int program, @GLenum int programInterface,
	                                      @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetProgramResourceLocationIndex")
	int glGetProgramResourceLocationIndex(@GLuint int program, @GLenum int programInterface,
	                                      @NullTerminated CharSequence name);

	// --------------------------------------------------------------------------------
	// ----------------------[ ARB_shader_storage_buffer_object ]----------------------
	// --------------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, and
	 * GetBufferPointerv:
	 */
	int GL_SHADER_STORAGE_BUFFER = 0x90D2;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegerv, GetIntegeri_v,
	 * GetBooleanv, GetInteger64v, GetFloatv, GetDoublev, GetBooleani_v,
	 * GetIntegeri_v, GetFloati_v, GetDoublei_v, and GetInteger64i_v:
	 */
	int GL_SHADER_STORAGE_BUFFER_BINDING = 0x90D3;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegeri_v, GetBooleani_v,
	 * GetIntegeri_v, GetFloati_v, GetDoublei_v, and GetInteger64i_v:
	 */
	int GL_SHADER_STORAGE_BUFFER_START = 0x90D4,
		GL_SHADER_STORAGE_BUFFER_SIZE  = 0x90D5;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegerv, GetBooleanv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS          = 0x90D6,
		GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS        = 0x90D7,
		GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS    = 0x90D8,
		GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 0x90D9,
		GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS        = 0x90DA,
		GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS         = 0x90DB,
		GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS        = 0x90DC,
		GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS        = 0x90DD,
		GL_MAX_SHADER_STORAGE_BLOCK_SIZE             = 0x90DE,
		GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT    = 0x90DF;

	/** Accepted in the &lt;barriers&gt; bitfield in glMemoryBarrier: */
	int GL_SHADER_STORAGE_BARRIER_BIT = 0x2000;

	/**
	 * Alias for the existing token
	 * MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS:
	 */
	int GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 0x8F39;

	void glShaderStorageBlockBinding(@GLuint int program, @GLuint int storageBlockIndex,
	                                 @GLuint int storageBlockBinding);

	// ---------------------------------------------------------------------
	// ----------------------[ ARB_stencil_texturing ]----------------------
	// ---------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of TexParameter* and GetTexParameter*: */
	int GL_DEPTH_STENCIL_TEXTURE_MODE = 0x90EA;

	// ------------------------------------------------------------------------
	// ----------------------[ ARB_texture_buffer_range ]----------------------
	// ------------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetTexLevelParameter: */
	int GL_TEXTURE_BUFFER_OFFSET = 0x919D,
		GL_TEXTURE_BUFFER_SIZE   = 0x919E;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 0x919F;

	void glTexBufferRange(@GLenum int target,
	                      @GLenum int internalformat,
	                      @GLuint int buffer,
	                      @GLintptr long offset,
	                      @GLsizeiptr long size);

	// -------------------------------------------------------------------------------
	// ----------------------[ ARB_texture_storage_multisample ]----------------------
	// -------------------------------------------------------------------------------

	void glTexStorage2DMultisample(@GLenum int target,
	                               @GLsizei int samples,
	                               @GLenum int internalformat,
	                               @GLsizei int width,
	                               @GLsizei int height,
	                               boolean fixedsamplelocations);

	void glTexStorage3DMultisample(@GLenum int target,
	                               @GLsizei int samples,
	                               @GLenum int internalformat,
	                               @GLsizei int width,
	                               @GLsizei int height,
	                               @GLsizei int depth,
	                               boolean fixedsamplelocations);

	// ----------------------------------------------------------------
	// ----------------------[ ARB_texture_view ]----------------------
	// ----------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetTexParameterfv and
	 * GetTexParameteriv:
	 */
	int GL_TEXTURE_VIEW_MIN_LEVEL   = 0x82DB,
		GL_TEXTURE_VIEW_NUM_LEVELS  = 0x82DC,
		GL_TEXTURE_VIEW_MIN_LAYER   = 0x82DD,
		GL_TEXTURE_VIEW_NUM_LAYERS  = 0x82DE,
		GL_TEXTURE_IMMUTABLE_LEVELS = 0x82DF;

	void glTextureView(@GLuint int texture, @GLenum int target, @GLuint int origtexture,
	                   @GLenum int internalformat,
	                   @GLuint int minlevel, @GLuint int numlevels,
	                   @GLuint int minlayer, @GLuint int numlayers);

	// -------------------------------------------------------------------------
	// ----------------------[ ARB_vertex_attrib_binding ]----------------------
	// -------------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetVertexAttrib*v: */
	int GL_VERTEX_ATTRIB_BINDING         = 0x82D4,
		GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D5;

	/**
	 * Accepted by the &lt;target&gt; parameter of GetBooleani_v, GetIntegeri_v,
	 * GetFloati_v, GetDoublei_v, and GetInteger64i_v:
	 */
	int GL_VERTEX_BINDING_DIVISOR = 0x82D6,
		GL_VERTEX_BINDING_OFFSET  = 0x82D7,
		GL_VERTEX_BINDING_STRIDE  = 0x82D8;

	/** Accepted by the &lt;pname&gt; parameter of GetIntegerv, ... */
	int GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D9,
		GL_MAX_VERTEX_ATTRIB_BINDINGS        = 0x82DA;

	void glBindVertexBuffer(@GLuint int bindingindex, @GLuint int buffer, @GLintptr long offset,
	                        @GLsizei int stride);

	void glVertexAttribFormat(@GLuint int attribindex, int size, @GLenum int type,
	                          boolean normalized, @GLuint int relativeoffset);

	void glVertexAttribIFormat(@GLuint int attribindex, int size, @GLenum int type,
	                           @GLuint int relativeoffset);

	void glVertexAttribLFormat(@GLuint int attribindex, int size, @GLenum int type,
	                           @GLuint int relativeoffset);

	void glVertexAttribBinding(@GLuint int attribindex, @GLuint int bindingindex);

	void glVertexBindingDivisor(@GLuint int bindingindex, @GLuint int divisor);

}