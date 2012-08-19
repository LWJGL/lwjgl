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
package org.lwjgl.opengles;

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opengl.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface GLES20 {

	/** ClearBufferMask */
	int GL_DEPTH_BUFFER_BIT   = 0x00000100,
		GL_STENCIL_BUFFER_BIT = 0x00000400,
		GL_COLOR_BUFFER_BIT   = 0x00004000;

	/** Boolean */
	int GL_FALSE = 0,
		GL_TRUE  = 1;

	/** BeginMode */
	int GL_POINTS         = 0x0000,
		GL_LINES          = 0x0001,
		GL_LINE_LOOP      = 0x0002,
		GL_LINE_STRIP     = 0x0003,
		GL_TRIANGLES      = 0x0004,
		GL_TRIANGLE_STRIP = 0x0005,
		GL_TRIANGLE_FAN   = 0x0006;

	/** BlendingFactorDest */
	int GL_ZERO                = 0,
		GL_ONE                 = 1,
		GL_SRC_COLOR           = 0x0300,
		GL_ONE_MINUS_SRC_COLOR = 0x0301,
		GL_SRC_ALPHA           = 0x0302,
		GL_ONE_MINUS_SRC_ALPHA = 0x0303,
		GL_DST_ALPHA           = 0x0304,
		GL_ONE_MINUS_DST_ALPHA = 0x0305;

	/** BlendingFactorSrc */
	int
/*      GL_ZERO */
/*      GL_ONE */
		GL_DST_COLOR           = 0x0306,
		GL_ONE_MINUS_DST_COLOR = 0x0307,
		GL_SRC_ALPHA_SATURATE  = 0x0308;
/*      GL_SRC_ALPHA */
/*      GL_ONE_MINUS_SRC_ALPHA */
/*      GL_DST_ALPHA */
/*      GL_ONE_MINUS_DST_ALPHA */

	/** BlendEquationSeparate */
	int GL_FUNC_ADD             = 0x8006,
		GL_BLEND_EQUATION       = 0x8009,
		GL_BLEND_EQUATION_RGB   = 0x8009,    /* same as BLEND_EQUATION */
		GL_BLEND_EQUATION_ALPHA = 0x883D;

	/** BlendSubtract */
	int GL_FUNC_SUBTRACT         = 0x800A,
		GL_FUNC_REVERSE_SUBTRACT = 0x800B;

	/** Separate Blend Functions */
	int GL_BLEND_DST_RGB            = 0x80C8,
		GL_BLEND_SRC_RGB            = 0x80C9,
		GL_BLEND_DST_ALPHA          = 0x80CA,
		GL_BLEND_SRC_ALPHA          = 0x80CB,
		GL_CONSTANT_COLOR           = 0x8001,
		GL_ONE_MINUS_CONSTANT_COLOR = 0x8002,
		GL_CONSTANT_ALPHA           = 0x8003,
		GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004,
		GL_BLEND_COLOR              = 0x8005;

	/** Buffer Objects */
	int GL_ARRAY_BUFFER                 = 0x8892,
		GL_ELEMENT_ARRAY_BUFFER         = 0x8893,
		GL_ARRAY_BUFFER_BINDING         = 0x8894,
		GL_ELEMENT_ARRAY_BUFFER_BINDING = 0x8895,
		GL_STREAM_DRAW                  = 0x88E0,
		GL_STATIC_DRAW                  = 0x88E4,
		GL_DYNAMIC_DRAW                 = 0x88E8,
		GL_BUFFER_SIZE                  = 0x8764,
		GL_BUFFER_USAGE                 = 0x8765,
		GL_CURRENT_VERTEX_ATTRIB        = 0x8626;

	/** CullFaceMode */
	int GL_FRONT          = 0x0404,
		GL_BACK           = 0x0405,
		GL_FRONT_AND_BACK = 0x0408;

/** DepthFunction */
/*      GL_NEVER */
/*      GL_LESS */
/*      GL_EQUAL */
/*      GL_LEQUAL */
/*      GL_GREATER */
/*      GL_NOTEQUAL */
/*      GL_GEQUAL */
/*      GL_ALWAYS */

	/** EnableCap */
	int GL_TEXTURE_2D               = 0x0DE1,
		GL_CULL_FACE                = 0x0B44,
		GL_BLEND                    = 0x0BE2,
		GL_DITHER                   = 0x0BD0,
		GL_STENCIL_TEST             = 0x0B90,
		GL_DEPTH_TEST               = 0x0B71,
		GL_SCISSOR_TEST             = 0x0C11,
		GL_POLYGON_OFFSET_FILL      = 0x8037,
		GL_SAMPLE_ALPHA_TO_COVERAGE = 0x809E,
		GL_SAMPLE_COVERAGE          = 0x80A0;

	/** ErrorCode */
	int GL_NO_ERROR          = 0,
		GL_INVALID_ENUM      = 0x0500,
		GL_INVALID_VALUE     = 0x0501,
		GL_INVALID_OPERATION = 0x0502,
		GL_OUT_OF_MEMORY     = 0x0505;

	/** FrontFaceDirection */
	int GL_CW  = 0x0900,
		GL_CCW = 0x0901;

	/** GetPName */
	int GL_LINE_WIDTH                   = 0x0B21,
		GL_ALIASED_POINT_SIZE_RANGE     = 0x846D,
		GL_ALIASED_LINE_WIDTH_RANGE     = 0x846E,
		GL_CULL_FACE_MODE               = 0x0B45,
		GL_FRONT_FACE                   = 0x0B46,
		GL_DEPTH_RANGE                  = 0x0B70,
		GL_DEPTH_WRITEMASK              = 0x0B72,
		GL_DEPTH_CLEAR_VALUE            = 0x0B73,
		GL_DEPTH_FUNC                   = 0x0B74,
		GL_STENCIL_CLEAR_VALUE          = 0x0B91,
		GL_STENCIL_FUNC                 = 0x0B92,
		GL_STENCIL_FAIL                 = 0x0B94,
		GL_STENCIL_PASS_DEPTH_FAIL      = 0x0B95,
		GL_STENCIL_PASS_DEPTH_PASS      = 0x0B96,
		GL_STENCIL_REF                  = 0x0B97,
		GL_STENCIL_VALUE_MASK           = 0x0B93,
		GL_STENCIL_WRITEMASK            = 0x0B98,
		GL_STENCIL_BACK_FUNC            = 0x8800,
		GL_STENCIL_BACK_FAIL            = 0x8801,
		GL_STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802,
		GL_STENCIL_BACK_PASS_DEPTH_PASS = 0x8803,
		GL_STENCIL_BACK_REF             = 0x8CA3,
		GL_STENCIL_BACK_VALUE_MASK      = 0x8CA4,
		GL_STENCIL_BACK_WRITEMASK       = 0x8CA5,
		GL_VIEWPORT                     = 0x0BA2,
		GL_SCISSOR_BOX                  = 0x0C10,
		/*      GL_SCISSOR_TEST */
		GL_COLOR_CLEAR_VALUE            = 0x0C22,
		GL_COLOR_WRITEMASK              = 0x0C23,
		GL_UNPACK_ALIGNMENT             = 0x0CF5,
		GL_PACK_ALIGNMENT               = 0x0D05,
		GL_MAX_TEXTURE_SIZE             = 0x0D33,
		GL_MAX_VIEWPORT_DIMS            = 0x0D3A,
		GL_SUBPIXEL_BITS                = 0x0D50,
		GL_RED_BITS                     = 0x0D52,
		GL_GREEN_BITS                   = 0x0D53,
		GL_BLUE_BITS                    = 0x0D54,
		GL_ALPHA_BITS                   = 0x0D55,
		GL_DEPTH_BITS                   = 0x0D56,
		GL_STENCIL_BITS                 = 0x0D57,
		GL_POLYGON_OFFSET_UNITS         = 0x2A00,
		/*      GL_POLYGON_OFFSET_FILL */
		GL_POLYGON_OFFSET_FACTOR        = 0x8038,
		GL_TEXTURE_BINDING_2D           = 0x8069,
		GL_SAMPLE_BUFFERS               = 0x80A8,
		GL_SAMPLES                      = 0x80A9,
		GL_SAMPLE_COVERAGE_VALUE        = 0x80AA,
		GL_SAMPLE_COVERAGE_INVERT       = 0x80AB;

	/** GetTextureParameter */
	int
/*      GL_TEXTURE_MAG_FILTER */
/*      GL_TEXTURE_MIN_FILTER */
/*      GL_TEXTURE_WRAP_S */
/*      GL_TEXTURE_WRAP_T */
		GL_NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2,
		GL_COMPRESSED_TEXTURE_FORMATS     = 0x86A3;

	/** HintMode */
	int GL_DONT_CARE = 0x1100,
		GL_FASTEST   = 0x1101,
		GL_NICEST    = 0x1102;

	/** HintTarget */
	int GL_GENERATE_MIPMAP_HINT = 0x8192;

	/** DataType */
	int GL_BYTE           = 0x1400,
		GL_UNSIGNED_BYTE  = 0x1401,
		GL_SHORT          = 0x1402,
		GL_UNSIGNED_SHORT = 0x1403,
		GL_INT            = 0x1404,
		GL_UNSIGNED_INT   = 0x1405,
		GL_FLOAT          = 0x1406,
		GL_FIXED          = 0x140C;

	/** PixelFormat */
	int GL_DEPTH_COMPONENT = 0x1902,
		GL_ALPHA           = 0x1906,
		GL_RGB             = 0x1907,
		GL_RGBA            = 0x1908,
		GL_LUMINANCE       = 0x1909,
		GL_LUMINANCE_ALPHA = 0x190A;

	/** PixelType */
	int
/*      GL_UNSIGNED_BYTE */
		GL_UNSIGNED_SHORT_4_4_4_4 = 0x8033,
		GL_UNSIGNED_SHORT_5_5_5_1 = 0x8034,
		GL_UNSIGNED_SHORT_5_6_5   = 0x8363;

	/** Shaders */
	int GL_FRAGMENT_SHADER                  = 0x8B30,
		GL_VERTEX_SHADER                    = 0x8B31,
		GL_MAX_VERTEX_ATTRIBS               = 0x8869,
		GL_MAX_VERTEX_UNIFORM_VECTORS       = 0x8DFB,
		GL_MAX_VARYING_VECTORS              = 0x8DFC,
		GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8B4D,
		GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS   = 0x8B4C,
		GL_MAX_TEXTURE_IMAGE_UNITS          = 0x8872,
		GL_MAX_FRAGMENT_UNIFORM_VECTORS     = 0x8DFD,
		GL_SHADER_TYPE                      = 0x8B4F,
		GL_DELETE_STATUS                    = 0x8B80,
		GL_LINK_STATUS                      = 0x8B82,
		GL_VALIDATE_STATUS                  = 0x8B83,
		GL_ATTACHED_SHADERS                 = 0x8B85,
		GL_ACTIVE_UNIFORMS                  = 0x8B86,
		GL_ACTIVE_UNIFORM_MAX_LENGTH        = 0x8B87,
		GL_ACTIVE_ATTRIBUTES                = 0x8B89,
		GL_ACTIVE_ATTRIBUTE_MAX_LENGTH      = 0x8B8A,
		GL_SHADING_LANGUAGE_VERSION         = 0x8B8C,
		GL_CURRENT_PROGRAM                  = 0x8B8D;

	/** StencilFunction */
	int GL_NEVER    = 0x0200,
		GL_LESS     = 0x0201,
		GL_EQUAL    = 0x0202,
		GL_LEQUAL   = 0x0203,
		GL_GREATER  = 0x0204,
		GL_NOTEQUAL = 0x0205,
		GL_GEQUAL   = 0x0206,
		GL_ALWAYS   = 0x0207;

	/** StencilOp */
	int
/*      GL_ZERO */
		GL_KEEP      = 0x1E00,
		GL_REPLACE   = 0x1E01,
		GL_INCR      = 0x1E02,
		GL_DECR      = 0x1E03,
		GL_INVERT    = 0x150A,
		GL_INCR_WRAP = 0x8507,
		GL_DECR_WRAP = 0x8508;

	/** StringName */
	int GL_VENDOR     = 0x1F00,
		GL_RENDERER   = 0x1F01,
		GL_VERSION    = 0x1F02,
		GL_EXTENSIONS = 0x1F03;

	/** TextureMagFilter */
	int GL_NEAREST = 0x2600,
		GL_LINEAR  = 0x2601;

	/** TextureMinFilter */
	int
/*      GL_NEAREST */
/*      GL_LINEAR */
		GL_NEAREST_MIPMAP_NEAREST = 0x2700,
		GL_LINEAR_MIPMAP_NEAREST  = 0x2701,
		GL_NEAREST_MIPMAP_LINEAR  = 0x2702,
		GL_LINEAR_MIPMAP_LINEAR   = 0x2703;

	/** TextureParameterName */
	int GL_TEXTURE_MAG_FILTER = 0x2800,
		GL_TEXTURE_MIN_FILTER = 0x2801,
		GL_TEXTURE_WRAP_S     = 0x2802,
		GL_TEXTURE_WRAP_T     = 0x2803;

	/** TextureTarget */
	int
/*      GL_TEXTURE_2D */
		GL_TEXTURE                     = 0x1702,
		GL_TEXTURE_CUBE_MAP            = 0x8513,
		GL_TEXTURE_BINDING_CUBE_MAP    = 0x8514,
		GL_TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515,
		GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516,
		GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517,
		GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518,
		GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519,
		GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851A,
		GL_MAX_CUBE_MAP_TEXTURE_SIZE   = 0x851C;

	/** TextureUnit */
	int GL_TEXTURE0       = 0x84C0,
		GL_TEXTURE1       = 0x84C1,
		GL_TEXTURE2       = 0x84C2,
		GL_TEXTURE3       = 0x84C3,
		GL_TEXTURE4       = 0x84C4,
		GL_TEXTURE5       = 0x84C5,
		GL_TEXTURE6       = 0x84C6,
		GL_TEXTURE7       = 0x84C7,
		GL_TEXTURE8       = 0x84C8,
		GL_TEXTURE9       = 0x84C9,
		GL_TEXTURE10      = 0x84CA,
		GL_TEXTURE11      = 0x84CB,
		GL_TEXTURE12      = 0x84CC,
		GL_TEXTURE13      = 0x84CD,
		GL_TEXTURE14      = 0x84CE,
		GL_TEXTURE15      = 0x84CF,
		GL_TEXTURE16      = 0x84D0,
		GL_TEXTURE17      = 0x84D1,
		GL_TEXTURE18      = 0x84D2,
		GL_TEXTURE19      = 0x84D3,
		GL_TEXTURE20      = 0x84D4,
		GL_TEXTURE21      = 0x84D5,
		GL_TEXTURE22      = 0x84D6,
		GL_TEXTURE23      = 0x84D7,
		GL_TEXTURE24      = 0x84D8,
		GL_TEXTURE25      = 0x84D9,
		GL_TEXTURE26      = 0x84DA,
		GL_TEXTURE27      = 0x84DB,
		GL_TEXTURE28      = 0x84DC,
		GL_TEXTURE29      = 0x84DD,
		GL_TEXTURE30      = 0x84DE,
		GL_TEXTURE31      = 0x84DF,
		GL_ACTIVE_TEXTURE = 0x84E0;

	/** TextureWrapMode */
	int GL_REPEAT          = 0x2901,
		GL_CLAMP_TO_EDGE   = 0x812F,
		GL_MIRRORED_REPEAT = 0x8370;

	/** Uniform Types */
	int GL_FLOAT_VEC2   = 0x8B50,
		GL_FLOAT_VEC3   = 0x8B51,
		GL_FLOAT_VEC4   = 0x8B52,
		GL_INT_VEC2     = 0x8B53,
		GL_INT_VEC3     = 0x8B54,
		GL_INT_VEC4     = 0x8B55,
		GL_BOOL         = 0x8B56,
		GL_BOOL_VEC2    = 0x8B57,
		GL_BOOL_VEC3    = 0x8B58,
		GL_BOOL_VEC4    = 0x8B59,
		GL_FLOAT_MAT2   = 0x8B5A,
		GL_FLOAT_MAT3   = 0x8B5B,
		GL_FLOAT_MAT4   = 0x8B5C,
		GL_SAMPLER_2D   = 0x8B5E,
		GL_SAMPLER_CUBE = 0x8B60;

	/** Vertex Arrays */
	int GL_VERTEX_ATTRIB_ARRAY_ENABLED        = 0x8622,
		GL_VERTEX_ATTRIB_ARRAY_SIZE           = 0x8623,
		GL_VERTEX_ATTRIB_ARRAY_STRIDE         = 0x8624,
		GL_VERTEX_ATTRIB_ARRAY_TYPE           = 0x8625,
		GL_VERTEX_ATTRIB_ARRAY_NORMALIZED     = 0x886A,
		GL_VERTEX_ATTRIB_ARRAY_POINTER        = 0x8645,
		GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F;

	/** Read Format */
	int GL_IMPLEMENTATION_COLOR_READ_TYPE   = 0x8B9A,
		GL_IMPLEMENTATION_COLOR_READ_FORMAT = 0x8B9B;

	/** Shader Source */
	int GL_COMPILE_STATUS       = 0x8B81,
		GL_INFO_LOG_LENGTH      = 0x8B84,
		GL_SHADER_SOURCE_LENGTH = 0x8B88,
		GL_SHADER_COMPILER      = 0x8DFA;

	/** Shader Binary */
	int GL_SHADER_BINARY_FORMATS     = 0x8DF8,
		GL_NUM_SHADER_BINARY_FORMATS = 0x8DF9;

	/** Shader Precision-Specified Types */
	int GL_LOW_FLOAT    = 0x8DF0,
		GL_MEDIUM_FLOAT = 0x8DF1,
		GL_HIGH_FLOAT   = 0x8DF2,
		GL_LOW_INT      = 0x8DF3,
		GL_MEDIUM_INT   = 0x8DF4,
		GL_HIGH_INT     = 0x8DF5;

	/** Framebuffer Object. */
	int GL_FRAMEBUFFER                                  = 0x8D40,
		GL_RENDERBUFFER                                 = 0x8D41,
		GL_RGBA4                                        = 0x8056,
		GL_RGB5_A1                                      = 0x8057,
		GL_RGB565                                       = 0x8D62,
		GL_DEPTH_COMPONENT16                            = 0x81A5,
		GL_STENCIL_INDEX                                = 0x1901,
		GL_STENCIL_INDEX8                               = 0x8D48,
		GL_RENDERBUFFER_WIDTH                           = 0x8D42,
		GL_RENDERBUFFER_HEIGHT                          = 0x8D43,
		GL_RENDERBUFFER_INTERNAL_FORMAT                 = 0x8D44,
		GL_RENDERBUFFER_RED_SIZE                        = 0x8D50,
		GL_RENDERBUFFER_GREEN_SIZE                      = 0x8D51,
		GL_RENDERBUFFER_BLUE_SIZE                       = 0x8D52,
		GL_RENDERBUFFER_ALPHA_SIZE                      = 0x8D53,
		GL_RENDERBUFFER_DEPTH_SIZE                      = 0x8D54,
		GL_RENDERBUFFER_STENCIL_SIZE                    = 0x8D55,
		GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE           = 0x8CD0,
		GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME           = 0x8CD1,
		GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL         = 0x8CD2,
		GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 0x8CD3,
		GL_COLOR_ATTACHMENT0                            = 0x8CE0,
		GL_DEPTH_ATTACHMENT                             = 0x8D00,
		GL_STENCIL_ATTACHMENT                           = 0x8D20,
		GL_NONE                                         = 0,
		GL_FRAMEBUFFER_COMPLETE                         = 0x8CD5,
		GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT            = 0x8CD6,
		GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT    = 0x8CD7,
		GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS            = 0x8CD9,
		GL_FRAMEBUFFER_UNSUPPORTED                      = 0x8CDD,
		GL_FRAMEBUFFER_BINDING                          = 0x8CA6,
		GL_RENDERBUFFER_BINDING                         = 0x8CA7,
		GL_MAX_RENDERBUFFER_SIZE                        = 0x84E8,
		GL_INVALID_FRAMEBUFFER_OPERATION                = 0x0506;

	void glActiveTexture(@GLenum int texture);

	void glAttachShader(@GLuint int program, @GLuint int shader);

	void glBindAttribLocation(@GLuint int program, @GLuint int index, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glBindAttribLocation")
	void glBindAttribLocation(@GLuint int program, @GLuint int index, @NullTerminated CharSequence name);

	@Code("		StateTracker.bindBuffer(target, buffer);")
	void glBindBuffer(@GLenum int target, @GLuint int buffer);

	void glBindFramebuffer(@GLenum int target, @GLuint int framebuffer);

	void glBindRenderbuffer(@GLenum int target, @GLuint int renderbuffer);

	void glBindTexture(@GLenum int target, @GLuint int texture);

	void glBlendColor(@GLclampf float red, @GLclampf float green, @GLclampf float blue, @GLclampf float alpha);

	void glBlendEquation(@GLenum int mode);

	void glBlendEquationSeparate(@GLenum int modeRGB, @GLenum int modeAlpha);

	void glBlendFunc(@GLenum int sfactor, @GLenum int dfactor);

	void glBlendFuncSeparate(@GLenum int srcRGB, @GLenum int dstRGB, @GLenum int srcAlpha, @GLenum int dstAlpha);

	@GenerateAutos
	void glBufferData(@GLenum int target, @AutoSize("data") @GLsizeiptr long size,
	                  @Check @Const @GLbyte @GLshort @GLint @GLfloat Buffer data, @GLenum int usage);

	void glBufferSubData(@GLenum int target, @GLintptr long offset, @AutoSize("data") @GLsizeiptr long size,
	                     @Check @Const @GLbyte @GLshort @GLint @GLfloat Buffer data);

	@GLenum
	int glCheckFramebufferStatus(@GLenum int target);

	void glClear(@GLbitfield int mask);

	void glClearColor(@GLclampf float red, @GLclampf float green, @GLclampf float blue, @GLclampf float alpha);

	void glClearDepthf(@GLclampf float depth);

	void glClearStencil(@GLint int s);

	void glColorMask(@GLboolean boolean red, @GLboolean boolean green, @GLboolean boolean blue, @GLboolean boolean alpha);

	void glCompileShader(@GLuint int shader);

	void glCompressedTexImage2D(@GLenum int target, int level, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, int border, @AutoSize("data") @GLsizei int imageSize,
	                            @Check @Const @GLvoid ByteBuffer data);

	void glCompressedTexSubImage2D(@GLenum int target, int level, int xoffset, int yoffset, @GLsizei int width, @GLsizei int height, @GLenum int format, @AutoSize("data") @GLsizei int imageSize,
	                               @Check @Const @GLvoid ByteBuffer data);

	void glCopyTexImage2D(@GLenum int target, @GLint int level, @GLenum int internalformat, @GLint int x, @GLint int y, @GLsizei int width, @GLsizei int height, @GLint int border);

	void glCopyTexSubImage2D(@GLenum int target, @GLint int level, @GLint int xoffset, @GLint int yoffset, @GLint int x, @GLint int y, @GLsizei int width, @GLsizei int height);

	@GLuint
	int glCreateProgram();

	@GLuint
	int glCreateShader(@GLenum int type);

	void glCullFace(@GLenum int mode);

	void glDeleteBuffers(@AutoSize("buffers") @GLsizei int n, @Const @GLuint IntBuffer buffers);

	@Alternate("glDeleteBuffers")
	void glDeleteBuffers(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(buffer)", keepParam = true) int buffer);

	void glDeleteFramebuffers(@AutoSize("framebuffers") int n, @Const @GLuint IntBuffer framebuffers);

	@Alternate("glDeleteFramebuffers")
	void glDeleteFramebuffers(@Constant("1") int n, @Constant(value = "APIUtil.getInt(framebuffer)", keepParam = true) int framebuffer);

	void glDeleteProgram(@GLuint int program);

	void glDeleteRenderbuffers(@AutoSize("renderbuffers") int n, @Const @GLuint IntBuffer renderbuffers);

	@Alternate("glDeleteRenderbuffers")
	void glDeleteRenderbuffers(@Constant("1") int n, @Constant(value = "APIUtil.getInt(renderbuffer)", keepParam = true) int renderbuffer);

	void glDeleteShader(@GLuint int shader);

	void glDeleteTextures(@AutoSize("textures") @GLsizei int n, @Const @GLuint IntBuffer textures);

	@Alternate("glDeleteTextures")
	void glDeleteTextures(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(texture)", keepParam = true) int texture);

	void glDepthFunc(@GLenum int func);

	void glDepthMask(@GLboolean boolean flag);

	void glDepthRangef(@GLclampf float zNear, @GLclampf float zFar);

	void glDetachShader(@GLuint int program, @GLuint int shader);

	void glDisable(@GLenum int cap);

	void glDisableVertexAttribArray(@GLuint int index);

	void glDrawArrays(@GLenum int mode, @GLint int first, @GLsizei int count);

	void glDrawElements(@GLenum int mode, @AutoSize("indices") @GLsizei int count, @AutoType("indices") @GLenum int type,
	                    @BufferObject(BufferKind.ElementVBO) @Const @GLubyte @GLushort @GLuint Buffer indices);

	void glEnable(@GLenum int cap);

	void glEnableVertexAttribArray(@GLuint int index);

	void glFinish();

	void glFlush();

	void glFramebufferRenderbuffer(@GLenum int target, @GLenum int attachment, @GLenum int renderbuffertarget, @GLuint int renderbuffer);

	void glFramebufferTexture2D(@GLenum int target, @GLenum int attachment, @GLenum int textarget, @GLuint int texture, @GLint int level);

	void glFrontFace(@GLenum int mode);

	void glGenBuffers(@AutoSize("buffers") @GLsizei int n, @OutParameter @GLuint IntBuffer buffers);

	@Alternate("glGenBuffers")
	@GLreturn("buffers")
	void glGenBuffers2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer buffers);

	void glGenerateMipmap(@GLenum int target);

	void glGenFramebuffers(@AutoSize("framebuffers") int n, @OutParameter @GLuint IntBuffer framebuffers);

	@Alternate("glGenFramebuffers")
	@GLreturn("framebuffers")
	void glGenFramebuffers2(@Constant("1") int n, @OutParameter @GLuint IntBuffer framebuffers);

	void glGenRenderbuffers(@AutoSize("renderbuffers") int n, @OutParameter @GLuint IntBuffer renderbuffers);

	@Alternate("glGenRenderbuffers")
	@GLreturn("renderbuffers")
	void glGenRenderbuffers2(@Constant("1") int n, @OutParameter @GLuint IntBuffer renderbuffers);

	void glGenTextures(@AutoSize("textures") @GLsizei int n, @OutParameter @GLuint IntBuffer textures);

	@Alternate("glGenTextures")
	@GLreturn("textures")
	void glGenTextures2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer textures);

	void glGetActiveAttrib(@GLuint int program, @GLuint int index, @AutoSize("name") @GLsizei int bufsize,
	                       @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                       @OutParameter @Check("1") IntBuffer size,
	                       @OutParameter @Check("1") @GLenum IntBuffer type,
	                       @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveAttrib. This version returns both size and type in the sizeType buffer (at .position() and .position() + 1). */
	@Alternate("glGetActiveAttrib")
	@GLreturn(value = "name", maxLength = "bufsize")
	void glGetActiveAttrib2(@GLuint int program, @GLuint int index, @GLsizei int bufsize,
	                        @OutParameter @Constant("MemoryUtil.getAddress0(name_length)") @GLsizei IntBuffer length,
	                        @OutParameter @Check("2") IntBuffer sizeType,
	                        @OutParameter @Constant("MemoryUtil.getAddress(sizeType, sizeType.position() + 1)") @GLenum IntBuffer type,
	                        @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveAttrib. This version returns only the attrib name. */
	@Alternate(value = "glGetActiveAttrib", javaAlt = true)
	@GLreturn(value = "name", maxLength = "bufsize")
	void glGetActiveAttrib(@GLuint int program, @GLuint int index, @GLsizei int bufsize,
	                       @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt()), MemoryUtil.getAddress(APIUtil.getBufferInt(), 1)") IntBuffer length,
	                       @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveAttrib. This version returns only the attrib size. */
	@Alternate(value = "glGetActiveAttrib", javaAlt = true)
	@GLreturn(value = "size")
	void glGetActiveAttribSize(@GLuint int program, @GLuint int index, @Constant("0") @GLsizei int bufsize,
	                           @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                           @OutParameter IntBuffer size,
	                           @OutParameter @GLenum @Constant("MemoryUtil.getAddress(size, 1)") IntBuffer type, // Reuse size buffer and ignore
	                           @OutParameter @GLchar @Constant("APIUtil.getBufferByte0()") ByteBuffer name);

	/** Overloads glGetActiveAttrib. This version returns only the attrib type. */
	@Alternate(value = "glGetActiveAttrib", javaAlt = true)
	@GLreturn(value = "type")
	void glGetActiveAttribType(@GLuint int program, @GLuint int index, @Constant("0") @GLsizei int bufsize,
	                           @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                           @OutParameter @Constant("MemoryUtil.getAddress(type, 1)") IntBuffer size, // Reuse type buffer and ignore
	                           @OutParameter @GLenum IntBuffer type,
	                           @OutParameter @GLchar @Constant("APIUtil.getBufferByte0()") ByteBuffer name);

	void glGetActiveUniform(@GLuint int program, @GLuint int index, @AutoSize("name") @GLsizei int bufsize,
	                        @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                        @OutParameter @Check("1") @GLsizei IntBuffer size,
	                        @OutParameter @Check("1") @GLenum IntBuffer type,
	                        @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveUniform. This version returns both size and type in the sizeType buffer (at .position() and .position() + 1). */
	@Alternate("glGetActiveUniform")
	@GLreturn(value = "name", maxLength = "bufsize")
	void glGetActiveUniform2(@GLuint int program, @GLuint int index, @GLsizei int bufsize,
	                         @OutParameter @Constant("MemoryUtil.getAddress0(name_length)") @GLsizei IntBuffer length,
	                         @OutParameter @Check("2") IntBuffer sizeType,
	                         @OutParameter @Constant("MemoryUtil.getAddress(sizeType, sizeType.position() + 1)") @GLenum IntBuffer type,
	                         @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveUniformARB. This version returns only the uniform name. */
	@Alternate(value = "glGetActiveUniform", javaAlt = true)
	@GLreturn(value = "name", maxLength = "bufsize")
	void glGetActiveUniform(@GLuint int program, @GLuint int index, @GLsizei int bufsize,
	                        @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt()), MemoryUtil.getAddress(APIUtil.getBufferInt(), 1)") IntBuffer length,
	                        @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveUniform. This version returns only the uniform size. */
	@Alternate(value = "glGetActiveUniform", javaAlt = true)
	@GLreturn(value = "size")
	void glGetActiveUniformSize(@GLuint int program, @GLuint int index, @Constant("0") @GLsizei int bufsize,
	                            @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                            @OutParameter IntBuffer size,
	                            @OutParameter @GLenum @Constant("MemoryUtil.getAddress(size, 1)") IntBuffer type, // Reuse size buffer and ignore
	                            @OutParameter @GLchar @Constant("APIUtil.getBufferByte0()") ByteBuffer name);

	/** Overloads glGetActiveUniform. This version returns only the uniform type. */
	@Alternate(value = "glGetActiveUniform", javaAlt = true)
	@GLreturn(value = "type")
	void glGetActiveUniformType(@GLuint int program, @GLuint int index, @Constant("0") @GLsizei int bufsize,
	                            @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                            @OutParameter @Constant("MemoryUtil.getAddress(type, 1)") IntBuffer size, // Reuse type buffer and ignore
	                            @OutParameter @GLenum IntBuffer type,
	                            @OutParameter @GLchar @Constant("APIUtil.getBufferByte0()") ByteBuffer name);

	void glGetAttachedShaders(@GLuint int program, @AutoSize("shaders") @GLsizei int maxCount,
	                          @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer count,
	                          @OutParameter @GLuint IntBuffer shaders);

	int glGetAttribLocation(@GLuint int program, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetAttribLocation")
	int glGetAttribLocation(@GLuint int program, @NullTerminated CharSequence name);

	@StripPostfix("params")
	void glGetBooleanv(@GLenum int pname, @OutParameter @Check("1") @GLboolean ByteBuffer params);

	@Alternate("glGetBooleanv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetBooleanv2(@GLenum int pname, @OutParameter @GLboolean ByteBuffer params);

	@StripPostfix("params")
	void glGetBufferParameteriv(@GLenum int target, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetBufferParameteri} instead. */
	@Alternate("glGetBufferParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GLES20", method = "glGetBufferParameteri")
	@Deprecated
	void glGetBufferParameteriv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetBufferParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetBufferParameteriv3(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@NoErrorCheck
	@GLenum
	int glGetError();

	@StripPostfix("params")
	void glGetFloatv(@GLenum int pname, @OutParameter @Check("1") FloatBuffer params);

	@Alternate("glGetFloatv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetFloatv2(@GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetFramebufferAttachmentParameteriv(@GLenum int target, @GLenum int attachment, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetFramebufferAttachmentParameteri} instead. */
	@Alternate("glGetFramebufferAttachmentParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GLES20", method = "glGetFramebufferAttachmentParameteri")
	@Deprecated
	void glGetFramebufferAttachmentParameteriv2(@GLenum int target, @GLenum int attachment, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetFramebufferAttachmentParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetFramebufferAttachmentParameteriv3(@GLenum int target, @GLenum int attachment, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetIntegerv(@GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetIntegerv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetIntegerv2(@GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetProgramiv(@GLuint int program, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetProgrami} instead. */
	@Alternate("glGetProgramiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GLES20", method = "glGetProgrami")
	@Deprecated
	void glGetProgramiv2(@GLuint int program, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetProgramiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetProgramiv3(@GLuint int program, @GLenum int pname, @OutParameter IntBuffer params);

	void glGetProgramInfoLog(@GLuint int program, @AutoSize("infoLog") @GLsizei int bufsize,
	                         @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                         @OutParameter @GLchar ByteBuffer infoLog);

	@Alternate("glGetProgramInfoLog")
	@GLreturn(value = "infoLog", maxLength = "bufsize")
	void glGetProgramInfoLog2(@GLuint int program, @GLsizei int bufsize,
	                          @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(infoLog_length)") IntBuffer length,
	                          @OutParameter @GLchar ByteBuffer infoLog);

	@StripPostfix("params")
	void glGetRenderbufferParameteriv(@GLenum int target, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetRenderbufferParameteri} instead. */
	@Alternate("glGetRenderbufferParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GLES20", method = "glGetRenderbufferParameteri")
	@Deprecated
	void glGetRenderbufferParameteriv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetRenderbufferParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetRenderbufferParameteriv3(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetShaderiv(@GLuint int shader, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetShaderi} instead. */
	@Alternate("glGetShaderiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GLES20", method = "glGetShaderi")
	@Deprecated
	void glGetShaderiv2(@GLuint int shader, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetShaderiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetShaderiv3(@GLuint int shader, @GLenum int pname, @OutParameter IntBuffer params);

	void glGetShaderInfoLog(@GLuint int shader, @AutoSize("infoLog") @GLsizei int bufsize,
	                        @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                        @OutParameter @GLchar ByteBuffer infoLog);

	@Alternate("glGetShaderInfoLog")
	@GLreturn(value = "infoLog", maxLength = "bufsize")
	void glGetShaderInfoLog2(@GLuint int shader, @GLsizei int bufsize,
	                         @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(infoLog_length)") IntBuffer length,
	                         @OutParameter @GLchar ByteBuffer infoLog);

	void glGetShaderPrecisionFormat(@GLenum int shadertype, @GLenum int precisiontype, @OutParameter @GLint @Check("2") IntBuffer range, @OutParameter @Check("1") @GLint IntBuffer precision);

	void glGetShaderSource(@GLuint int shader, @AutoSize("source") @GLsizei int bufsize,
	                       @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                       @OutParameter @GLchar ByteBuffer source);

	@Alternate("glGetShaderSource")
	@GLreturn(value = "source", maxLength = "bufsize")
	void glGetShaderSource2(@GLuint int shader, @GLsizei int bufsize,
	                        @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(source_length)") IntBuffer length,
	                        @OutParameter @GLchar ByteBuffer source);

	@Const
	String glGetString(@GLenum int name);

	@StripPostfix("params")
	void glGetTexParameterfv(@GLenum int target, @GLenum int pname, @OutParameter @Check("1") FloatBuffer params);

	@Alternate("glGetTexParameterfv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexParameterfv2(@GLenum int target, @GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetTexParameteriv(@GLenum int target, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetTexParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexParameteriv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetUniformfv(@GLuint int program, int location, @OutParameter @Check("1") FloatBuffer params);

	@StripPostfix("params")
	void glGetUniformiv(@GLuint int program, int location, @OutParameter @Check("1") IntBuffer params);

	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a
	 * <b>null-terminated</b> string.
	 *
	 * @param program
	 * @param name
	 */
	int glGetUniformLocation(@GLuint int program, @NullTerminated @Check("1") @Const @GLchar ByteBuffer name);

	@Alternate("glGetUniformLocation")
	int glGetUniformLocation(@GLuint int program, @NullTerminated CharSequence name);

	@StripPostfix("params")
	void glGetVertexAttribfv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@StripPostfix("params")
	void glGetVertexAttribiv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@StripPostfix("pointer")
	void glGetVertexAttribPointerv(@GLuint int index, @GLenum int pname, @Result @GLvoid ByteBuffer pointer);

	void glHint(@GLenum int target, @GLenum int mode);

	@GLboolean
	boolean glIsBuffer(@GLuint int buffer);

	@GLboolean
	boolean glIsEnabled(@GLenum int cap);

	@GLboolean
	boolean glIsFramebuffer(@GLuint int framebuffer);

	@GLboolean
	boolean glIsProgram(@GLuint int program);

	@GLboolean
	boolean glIsRenderbuffer(@GLuint int renderbuffer);

	@GLboolean
	boolean glIsShader(@GLuint int shader);

	@GLboolean
	boolean glIsTexture(@GLuint int texture);

	void glLineWidth(@GLfloat float width);

	void glLinkProgram(@GLuint int program);

	void glPixelStorei(@GLenum int pname, @GLint int param);

	void glPolygonOffset(@GLfloat float factor, @GLfloat float units);

	void glReadPixels(int x, int y, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
	                  @Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, 1)")
	                  @OutParameter @GLbyte @GLshort @GLint @GLfloat Buffer pixels);

	void glReleaseShaderCompiler();

	void glRenderbufferStorage(@GLenum int target, @GLenum int internalformat, @GLsizei int width, @GLsizei int height);

	void glSampleCoverage(@GLclampf float value, @GLboolean boolean invert);

	void glScissor(@GLint int x, @GLint int y, @GLsizei int width, @GLsizei int height);

	void glShaderBinary(@AutoSize("shaders") @GLsizei int n, @Const @GLuint IntBuffer shaders, @GLenum int binaryformat, @Const @GLvoid ByteBuffer binary, @AutoSize("binary") @GLsizei int length);

	/**
	 * glShaderSource allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 *
	 * @param shader
	 * @param string
	 */
	void glShaderSource(@GLuint int shader, @Constant("1") @GLsizei int count,
	                    @Indirect @Const @Check @GLchar ByteBuffer string,
	                    @AutoSize("string") @Indirect @Const @GLint int length);

	@Alternate("glShaderSource")
	void glShaderSource2(@GLuint int shader, @Constant("1") @GLsizei int count, CharSequence string, @Constant("string.length()") @Indirect @Const int length);

	@Alternate(value = "glShaderSource", nativeAlt = true)
	void glShaderSource3(@GLuint int shader, @Constant("strings.length") @GLsizei int count,
	                     @Const @PointerArray(value = "count", lengths = "length") CharSequence[] strings,
	                     @Constant("APIUtil.getLengths(strings)") @Const IntBuffer length);

	void glStencilFunc(@GLenum int func, @GLint int ref, @GLuint int mask);

	void glStencilFuncSeparate(@GLenum int face, @GLenum int func, @GLint int ref, @GLuint int mask);

	void glStencilMask(@GLuint int mask);

	void glStencilMaskSeparate(@GLenum int face, @GLuint int mask);

	void glStencilOp(@GLenum int fail, @GLenum int zfail, @GLenum int zpass);

	void glStencilOpSeparate(@GLenum int face, @GLenum int fail, @GLenum int zfail, @GLenum int zpass);

	void glTexImage2D(@GLenum int target, int level, int internalformat, int width, int height, int border, @GLenum int format, @GLenum int type,
	                  @Check(value = "GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height)", canBeNull = true)
	                  @Const @GLbyte @GLshort @GLint @GLfloat Buffer pixels);

	void glTexParameterf(@GLenum int target, @GLenum int pname, @GLfloat float param);

	@StripPostfix("param")
	void glTexParameterfv(@GLenum int target, @GLenum int pname, @Check("4") @Const FloatBuffer param);

	void glTexParameteri(@GLenum int target, @GLenum int pname, @GLint int param);

	@StripPostfix("param")
	void glTexParameteriv(@GLenum int target, @GLenum int pname, @Check("4") @Const IntBuffer param);

	void glTexSubImage2D(@GLenum int target, int level, int xoffset, int yoffset, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
	                     @Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, 1)")
	                     @Const @GLbyte @GLshort @GLint @GLfloat Buffer pixels);

	void glUniform1f(@GLint int location, @GLfloat float x);

	@StripPostfix("v")
	void glUniform1fv(int location, @AutoSize("v") @GLsizei int count, @Const FloatBuffer v);

	void glUniform1i(@GLint int location, @GLint int x);

	@StripPostfix("v")
	void glUniform1iv(int location, @AutoSize("v") @GLsizei int count, @Const IntBuffer v);

	void glUniform2f(@GLint int location, @GLfloat float x, @GLfloat float y);

	@StripPostfix("v")
	void glUniform2fv(int location, @AutoSize(value = "v", expression = " >> 1") @GLsizei int count, @Const FloatBuffer v);

	void glUniform2i(@GLint int location, @GLint int x, @GLint int y);

	@StripPostfix("v")
	void glUniform2iv(int location, @AutoSize(value = "v", expression = " >> 1") @GLsizei int count, @Const IntBuffer v);

	void glUniform3f(@GLint int location, @GLfloat float x, @GLfloat float y, @GLfloat float z);

	@StripPostfix("v")
	void glUniform3fv(int location, @AutoSize(value = "v", expression = " / 3") @GLsizei int count, @Const FloatBuffer v);

	void glUniform3i(@GLint int location, @GLint int x, @GLint int y, @GLint int z);

	@StripPostfix("v")
	void glUniform3iv(int location, @AutoSize(value = "v", expression = " / 3") @GLsizei int count, @Const IntBuffer v);

	void glUniform4f(@GLint int location, @GLfloat float x, @GLfloat float y, @GLfloat float z, @GLfloat float w);

	@StripPostfix("v")
	void glUniform4fv(int location, @AutoSize(value = "v", expression = " >> 2") @GLsizei int count, @Const FloatBuffer v);

	void glUniform4i(@GLint int location, @GLint int x, @GLint int y, @GLint int z, @GLint int w);

	@StripPostfix("v")
	void glUniform4iv(int location, @AutoSize(value = "v", expression = " >> 2") @GLsizei int count, @Const IntBuffer v);

	@StripPostfix("matrices")
	void glUniformMatrix2fv(int location, @AutoSize(value = "matrices", expression = " >> 2") @GLsizei int count, @GLboolean boolean transpose, @Const FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix3fv(int location, @AutoSize(value = "matrices", expression = " / (3 * 3)") @GLsizei int count, @GLboolean boolean transpose, @Const FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix4fv(int location, @AutoSize(value = "matrices", expression = " >> 4") @GLsizei int count, @GLboolean boolean transpose, @Const FloatBuffer matrices);

	void glUseProgram(@GLuint int program);

	void glValidateProgram(@GLuint int program);

	void glVertexAttrib1f(@GLuint int indx, @GLfloat float x);

	@StripPostfix("values")
	void glVertexAttrib1fv(@GLuint int indx, @Const @Check("1") FloatBuffer values);

	void glVertexAttrib2f(@GLuint int indx, @GLfloat float x, @GLfloat float y);

	@StripPostfix("values")
	void glVertexAttrib2fv(@GLuint int indx, @Const @Check("2") FloatBuffer values);

	void glVertexAttrib3f(@GLuint int indx, @GLfloat float x, @GLfloat float y, @GLfloat float z);

	@StripPostfix("values")
	void glVertexAttrib3fv(@GLuint int indx, @Const @Check("3") FloatBuffer values);

	void glVertexAttrib4f(@GLuint int indx, @GLfloat float x, @GLfloat float y, @GLfloat float z, @GLfloat float w);

	@StripPostfix("values")
	void glVertexAttrib4fv(@GLuint int indx, @Const @Check("4") FloatBuffer values);

	void glVertexAttribPointer(@GLuint int index, int size, @AutoType("buffer") @GLenum int type, @GLboolean boolean normalized, @GLsizei int stride,
	                           @CachedReference(index = "index", name = "glVertexAttribPointer_buffer")
	                           @BufferObject(BufferKind.ArrayVBO) @Check @Const @GLubyte @GLbyte @GLshort @GLushort @GLint @GLuint @GLfloat Buffer buffer);

	void glViewport(@GLint int x, @GLint int y, @GLsizei int width, @GLsizei int height);
}