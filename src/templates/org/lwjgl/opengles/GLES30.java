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

import org.lwjgl.opengl.GLSync;
import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opengl.*;

import java.nio.*;

public interface GLES30 {

	int  GL_READ_BUFFER                                   = 0x0C02;
	int  GL_UNPACK_ROW_LENGTH                             = 0x0CF2;
	int  GL_UNPACK_SKIP_ROWS                              = 0x0CF3;
	int  GL_UNPACK_SKIP_PIXELS                            = 0x0CF4;
	int  GL_PACK_ROW_LENGTH                               = 0x0D02;
	int  GL_PACK_SKIP_ROWS                                = 0x0D03;
	int  GL_PACK_SKIP_PIXELS                              = 0x0D04;
	int  GL_COLOR                                         = 0x1800;
	int  GL_DEPTH                                         = 0x1801;
	int  GL_STENCIL                                       = 0x1802;
	int  GL_RED                                           = 0x1903;
	int  GL_RGB8                                          = 0x8051;
	int  GL_RGBA8                                         = 0x8058;
	int  GL_RGB10_A2                                      = 0x8059;
	int  GL_TEXTURE_BINDING_3D                            = 0x806A;
	int  GL_PACK_SKIP_IMAGES                              = 0x806B;
	int  GL_PACK_IMAGE_HEIGHT                             = 0x806C;
	int  GL_UNPACK_SKIP_IMAGES                            = 0x806D;
	int  GL_UNPACK_IMAGE_HEIGHT                           = 0x806E;
	int  GL_TEXTURE_3D                                    = 0x806F;
	int  GL_TEXTURE_WRAP_R                                = 0x8072;
	int  GL_MAX_3D_TEXTURE_SIZE                           = 0x8073;
	int  GL_UNSIGNED_INT_2_10_10_10_REV                   = 0x8368;
	int  GL_MAX_ELEMENTS_VERTICES                         = 0x80E8;
	int  GL_MAX_ELEMENTS_INDICES                          = 0x80E9;
	int  GL_TEXTURE_MIN_LOD                               = 0x813A;
	int  GL_TEXTURE_MAX_LOD                               = 0x813B;
	int  GL_TEXTURE_BASE_LEVEL                            = 0x813C;
	int  GL_TEXTURE_MAX_LEVEL                             = 0x813D;
	int  GL_MIN                                           = 0x8007;
	int  GL_MAX                                           = 0x8008;
	int  GL_DEPTH_COMPONENT24                             = 0x81A6;
	int  GL_MAX_TEXTURE_LOD_BIAS                          = 0x84FD;
	int  GL_TEXTURE_COMPARE_MODE                          = 0x884C;
	int  GL_TEXTURE_COMPARE_FUNC                          = 0x884D;
	int  GL_CURRENT_QUERY                                 = 0x8865;
	int  GL_QUERY_RESULT                                  = 0x8866;
	int  GL_QUERY_RESULT_AVAILABLE                        = 0x8867;
	int  GL_BUFFER_MAPPED                                 = 0x88BC;
	int  GL_BUFFER_MAP_POINTER                            = 0x88BD;
	int  GL_STREAM_READ                                   = 0x88E1;
	int  GL_STREAM_COPY                                   = 0x88E2;
	int  GL_STATIC_READ                                   = 0x88E5;
	int  GL_STATIC_COPY                                   = 0x88E6;
	int  GL_DYNAMIC_READ                                  = 0x88E9;
	int  GL_DYNAMIC_COPY                                  = 0x88EA;
	int  GL_MAX_DRAW_BUFFERS                              = 0x8824;
	int  GL_DRAW_BUFFER0                                  = 0x8825;
	int  GL_DRAW_BUFFER1                                  = 0x8826;
	int  GL_DRAW_BUFFER2                                  = 0x8827;
	int  GL_DRAW_BUFFER3                                  = 0x8828;
	int  GL_DRAW_BUFFER4                                  = 0x8829;
	int  GL_DRAW_BUFFER5                                  = 0x882A;
	int  GL_DRAW_BUFFER6                                  = 0x882B;
	int  GL_DRAW_BUFFER7                                  = 0x882C;
	int  GL_DRAW_BUFFER8                                  = 0x882D;
	int  GL_DRAW_BUFFER9                                  = 0x882E;
	int  GL_DRAW_BUFFER10                                 = 0x882F;
	int  GL_DRAW_BUFFER11                                 = 0x8830;
	int  GL_DRAW_BUFFER12                                 = 0x8831;
	int  GL_DRAW_BUFFER13                                 = 0x8832;
	int  GL_DRAW_BUFFER14                                 = 0x8833;
	int  GL_DRAW_BUFFER15                                 = 0x8834;
	int  GL_MAX_FRAGMENT_UNIFORM_COMPONENTS               = 0x8B49;
	int  GL_MAX_VERTEX_UNIFORM_COMPONENTS                 = 0x8B4A;
	int  GL_SAMPLER_3D                                    = 0x8B5F;
	int  GL_SAMPLER_2D_SHADOW                             = 0x8B62;
	int  GL_FRAGMENT_SHADER_DERIVATIVE_HINT               = 0x8B8B;
	int  GL_PIXEL_PACK_BUFFER                             = 0x88EB;
	int  GL_PIXEL_UNPACK_BUFFER                           = 0x88EC;
	int  GL_PIXEL_PACK_BUFFER_BINDING                     = 0x88ED;
	int  GL_PIXEL_UNPACK_BUFFER_BINDING                   = 0x88EF;
	int  GL_FLOAT_MAT2x3                                  = 0x8B65;
	int  GL_FLOAT_MAT2x4                                  = 0x8B66;
	int  GL_FLOAT_MAT3x2                                  = 0x8B67;
	int  GL_FLOAT_MAT3x4                                  = 0x8B68;
	int  GL_FLOAT_MAT4x2                                  = 0x8B69;
	int  GL_FLOAT_MAT4x3                                  = 0x8B6A;
	int  GL_SRGB                                          = 0x8C40;
	int  GL_SRGB8                                         = 0x8C41;
	int  GL_SRGB8_ALPHA8                                  = 0x8C43;
	int  GL_COMPARE_REF_TO_TEXTURE                        = 0x884E;
	int  GL_MAJOR_VERSION                                 = 0x821B;
	int  GL_MINOR_VERSION                                 = 0x821C;
	int  GL_NUM_EXTENSIONS                                = 0x821D;
	int  GL_RGBA32F                                       = 0x8814;
	int  GL_RGB32F                                        = 0x8815;
	int  GL_RGBA16F                                       = 0x881A;
	int  GL_RGB16F                                        = 0x881B;
	int  GL_VERTEX_ATTRIB_ARRAY_INTEGER                   = 0x88FD;
	int  GL_MAX_ARRAY_TEXTURE_LAYERS                      = 0x88FF;
	int  GL_MIN_PROGRAM_TEXEL_OFFSET                      = 0x8904;
	int  GL_MAX_PROGRAM_TEXEL_OFFSET                      = 0x8905;
	int  GL_MAX_VARYING_COMPONENTS                        = 0x8B4B;
	int  GL_TEXTURE_2D_ARRAY                              = 0x8C1A;
	int  GL_TEXTURE_BINDING_2D_ARRAY                      = 0x8C1D;
	int  GL_R11F_G11F_B10F                                = 0x8C3A;
	int  GL_UNSIGNED_INT_10F_11F_11F_REV                  = 0x8C3B;
	int  GL_RGB9_E5                                       = 0x8C3D;
	int  GL_UNSIGNED_INT_5_9_9_9_REV                      = 0x8C3E;
	int  GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH         = 0x8C76;
	int  GL_TRANSFORM_FEEDBACK_BUFFER_MODE                = 0x8C7F;
	int  GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS    = 0x8C80;
	int  GL_TRANSFORM_FEEDBACK_VARYINGS                   = 0x8C83;
	int  GL_TRANSFORM_FEEDBACK_BUFFER_START               = 0x8C84;
	int  GL_TRANSFORM_FEEDBACK_BUFFER_SIZE                = 0x8C85;
	int  GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN         = 0x8C88;
	int  GL_RASTERIZER_DISCARD                            = 0x8C89;
	int  GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 0x8C8A;
	int  GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS       = 0x8C8B;
	int  GL_INTERLEAVED_ATTRIBS                           = 0x8C8C;
	int  GL_SEPARATE_ATTRIBS                              = 0x8C8D;
	int  GL_TRANSFORM_FEEDBACK_BUFFER                     = 0x8C8E;
	int  GL_TRANSFORM_FEEDBACK_BUFFER_BINDING             = 0x8C8F;
	int  GL_RGBA32UI                                      = 0x8D70;
	int  GL_RGB32UI                                       = 0x8D71;
	int  GL_RGBA16UI                                      = 0x8D76;
	int  GL_RGB16UI                                       = 0x8D77;
	int  GL_RGBA8UI                                       = 0x8D7C;
	int  GL_RGB8UI                                        = 0x8D7D;
	int  GL_RGBA32I                                       = 0x8D82;
	int  GL_RGB32I                                        = 0x8D83;
	int  GL_RGBA16I                                       = 0x8D88;
	int  GL_RGB16I                                        = 0x8D89;
	int  GL_RGBA8I                                        = 0x8D8E;
	int  GL_RGB8I                                         = 0x8D8F;
	int  GL_RED_INTEGER                                   = 0x8D94;
	int  GL_RGB_INTEGER                                   = 0x8D98;
	int  GL_RGBA_INTEGER                                  = 0x8D99;
	int  GL_SAMPLER_2D_ARRAY                              = 0x8DC1;
	int  GL_SAMPLER_2D_ARRAY_SHADOW                       = 0x8DC4;
	int  GL_SAMPLER_CUBE_SHADOW                           = 0x8DC5;
	int  GL_UNSIGNED_INT_VEC2                             = 0x8DC6;
	int  GL_UNSIGNED_INT_VEC3                             = 0x8DC7;
	int  GL_UNSIGNED_INT_VEC4                             = 0x8DC8;
	int  GL_INT_SAMPLER_2D                                = 0x8DCA;
	int  GL_INT_SAMPLER_3D                                = 0x8DCB;
	int  GL_INT_SAMPLER_CUBE                              = 0x8DCC;
	int  GL_INT_SAMPLER_2D_ARRAY                          = 0x8DCF;
	int  GL_UNSIGNED_INT_SAMPLER_2D                       = 0x8DD2;
	int  GL_UNSIGNED_INT_SAMPLER_3D                       = 0x8DD3;
	int  GL_UNSIGNED_INT_SAMPLER_CUBE                     = 0x8DD4;
	int  GL_UNSIGNED_INT_SAMPLER_2D_ARRAY                 = 0x8DD7;
	int  GL_BUFFER_ACCESS_FLAGS                           = 0x911F;
	int  GL_BUFFER_MAP_LENGTH                             = 0x9120;
	int  GL_BUFFER_MAP_OFFSET                             = 0x9121;
	int  GL_DEPTH_COMPONENT32F                            = 0x8CAC;
	int  GL_DEPTH32F_STENCIL8                             = 0x8CAD;
	int  GL_FLOAT_32_UNSIGNED_INT_24_8_REV                = 0x8DAD;
	int  GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING         = 0x8210;
	int  GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE         = 0x8211;
	int  GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE               = 0x8212;
	int  GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE             = 0x8213;
	int  GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE              = 0x8214;
	int  GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE             = 0x8215;
	int  GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE             = 0x8216;
	int  GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE           = 0x8217;
	int  GL_FRAMEBUFFER_DEFAULT                           = 0x8218;
	int  GL_FRAMEBUFFER_UNDEFINED                         = 0x8219;
	int  GL_DEPTH_STENCIL_ATTACHMENT                      = 0x821A;
	int  GL_DEPTH_STENCIL                                 = 0x84F9;
	int  GL_UNSIGNED_INT_24_8                             = 0x84FA;
	int  GL_DEPTH24_STENCIL8                              = 0x88F0;
	int  GL_UNSIGNED_NORMALIZED                           = 0x8C17;
	int  GL_DRAW_FRAMEBUFFER_BINDING                      = GLES20.GL_FRAMEBUFFER_BINDING;
	int  GL_READ_FRAMEBUFFER                              = 0x8CA8;
	int  GL_DRAW_FRAMEBUFFER                              = 0x8CA9;
	int  GL_READ_FRAMEBUFFER_BINDING                      = 0x8CAA;
	int  GL_RENDERBUFFER_SAMPLES                          = 0x8CAB;
	int  GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER          = 0x8CD4;
	int  GL_MAX_COLOR_ATTACHMENTS                         = 0x8CDF;
	int  GL_COLOR_ATTACHMENT1                             = 0x8CE1;
	int  GL_COLOR_ATTACHMENT2                             = 0x8CE2;
	int  GL_COLOR_ATTACHMENT3                             = 0x8CE3;
	int  GL_COLOR_ATTACHMENT4                             = 0x8CE4;
	int  GL_COLOR_ATTACHMENT5                             = 0x8CE5;
	int  GL_COLOR_ATTACHMENT6                             = 0x8CE6;
	int  GL_COLOR_ATTACHMENT7                             = 0x8CE7;
	int  GL_COLOR_ATTACHMENT8                             = 0x8CE8;
	int  GL_COLOR_ATTACHMENT9                             = 0x8CE9;
	int  GL_COLOR_ATTACHMENT10                            = 0x8CEA;
	int  GL_COLOR_ATTACHMENT11                            = 0x8CEB;
	int  GL_COLOR_ATTACHMENT12                            = 0x8CEC;
	int  GL_COLOR_ATTACHMENT13                            = 0x8CED;
	int  GL_COLOR_ATTACHMENT14                            = 0x8CEE;
	int  GL_COLOR_ATTACHMENT15                            = 0x8CEF;
	int  GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE            = 0x8D56;
	int  GL_MAX_SAMPLES                                   = 0x8D57;
	int  GL_HALF_FLOAT                                    = 0x140B;
	int  GL_MAP_READ_BIT                                  = 0x0001;
	int  GL_MAP_WRITE_BIT                                 = 0x0002;
	int  GL_MAP_INVALIDATE_RANGE_BIT                      = 0x0004;
	int  GL_MAP_INVALIDATE_BUFFER_BIT                     = 0x0008;
	int  GL_MAP_FLUSH_EXPLICIT_BIT                        = 0x0010;
	int  GL_MAP_UNSYNCHRONIZED_BIT                        = 0x0020;
	int  GL_RG                                            = 0x8227;
	int  GL_RG_INTEGER                                    = 0x8228;
	int  GL_R8                                            = 0x8229;
	int  GL_RG8                                           = 0x822B;
	int  GL_R16F                                          = 0x822D;
	int  GL_R32F                                          = 0x822E;
	int  GL_RG16F                                         = 0x822F;
	int  GL_RG32F                                         = 0x8230;
	int  GL_R8I                                           = 0x8231;
	int  GL_R8UI                                          = 0x8232;
	int  GL_R16I                                          = 0x8233;
	int  GL_R16UI                                         = 0x8234;
	int  GL_R32I                                          = 0x8235;
	int  GL_R32UI                                         = 0x8236;
	int  GL_RG8I                                          = 0x8237;
	int  GL_RG8UI                                         = 0x8238;
	int  GL_RG16I                                         = 0x8239;
	int  GL_RG16UI                                        = 0x823A;
	int  GL_RG32I                                         = 0x823B;
	int  GL_RG32UI                                        = 0x823C;
	int  GL_VERTEX_ARRAY_BINDING                          = 0x85B5;
	int  GL_R8_SNORM                                      = 0x8F94;
	int  GL_RG8_SNORM                                     = 0x8F95;
	int  GL_RGB8_SNORM                                    = 0x8F96;
	int  GL_RGBA8_SNORM                                   = 0x8F97;
	int  GL_SIGNED_NORMALIZED                             = 0x8F9C;
	int  GL_PRIMITIVE_RESTART_FIXED_INDEX                 = 0x8D69;
	int  GL_COPY_READ_BUFFER                              = 0x8F36;
	int  GL_COPY_WRITE_BUFFER                             = 0x8F37;
	int  GL_COPY_READ_BUFFER_BINDING                      = GL_COPY_READ_BUFFER;
	int  GL_COPY_WRITE_BUFFER_BINDING                     = GL_COPY_WRITE_BUFFER;
	int  GL_UNIFORM_BUFFER                                = 0x8A11;
	int  GL_UNIFORM_BUFFER_BINDING                        = 0x8A28;
	int  GL_UNIFORM_BUFFER_START                          = 0x8A29;
	int  GL_UNIFORM_BUFFER_SIZE                           = 0x8A2A;
	int  GL_MAX_VERTEX_UNIFORM_BLOCKS                     = 0x8A2B;
	int  GL_MAX_FRAGMENT_UNIFORM_BLOCKS                   = 0x8A2D;
	int  GL_MAX_COMBINED_UNIFORM_BLOCKS                   = 0x8A2E;
	int  GL_MAX_UNIFORM_BUFFER_BINDINGS                   = 0x8A2F;
	int  GL_MAX_UNIFORM_BLOCK_SIZE                        = 0x8A30;
	int  GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS        = 0x8A31;
	int  GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS      = 0x8A33;
	int  GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT               = 0x8A34;
	int  GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH          = 0x8A35;
	int  GL_ACTIVE_UNIFORM_BLOCKS                         = 0x8A36;
	int  GL_UNIFORM_TYPE                                  = 0x8A37;
	int  GL_UNIFORM_SIZE                                  = 0x8A38;
	int  GL_UNIFORM_NAME_LENGTH                           = 0x8A39;
	int  GL_UNIFORM_BLOCK_INDEX                           = 0x8A3A;
	int  GL_UNIFORM_OFFSET                                = 0x8A3B;
	int  GL_UNIFORM_ARRAY_STRIDE                          = 0x8A3C;
	int  GL_UNIFORM_MATRIX_STRIDE                         = 0x8A3D;
	int  GL_UNIFORM_IS_ROW_MAJOR                          = 0x8A3E;
	int  GL_UNIFORM_BLOCK_BINDING                         = 0x8A3F;
	int  GL_UNIFORM_BLOCK_DATA_SIZE                       = 0x8A40;
	int  GL_UNIFORM_BLOCK_NAME_LENGTH                     = 0x8A41;
	int  GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS                 = 0x8A42;
	int  GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES          = 0x8A43;
	int  GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER     = 0x8A44;
	int  GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER   = 0x8A46;
	int  GL_INVALID_INDEX                                 = 0xFFFFFFFF;
	int  GL_MAX_VERTEX_OUTPUT_COMPONENTS                  = 0x9122;
	int  GL_MAX_FRAGMENT_INPUT_COMPONENTS                 = 0x9125;
	int  GL_MAX_SERVER_WAIT_TIMEOUT                       = 0x9111;
	int  GL_OBJECT_TYPE                                   = 0x9112;
	int  GL_SYNC_CONDITION                                = 0x9113;
	int  GL_SYNC_STATUS                                   = 0x9114;
	int  GL_SYNC_FLAGS                                    = 0x9115;
	int  GL_SYNC_FENCE                                    = 0x9116;
	int  GL_SYNC_GPU_COMMANDS_COMPLETE                    = 0x9117;
	int  GL_UNSIGNALED                                    = 0x9118;
	int  GL_SIGNALED                                      = 0x9119;
	int  GL_ALREADY_SIGNALED                              = 0x911A;
	int  GL_TIMEOUT_EXPIRED                               = 0x911B;
	int  GL_CONDITION_SATISFIED                           = 0x911C;
	int  GL_WAIT_FAILED                                   = 0x911D;
	int  GL_SYNC_FLUSH_COMMANDS_BIT                       = 0x00000001;
	long GL_TIMEOUT_IGNORED                               = 0xFFFFFFFFFFFFFFFFl;
	int  GL_VERTEX_ATTRIB_ARRAY_DIVISOR                   = 0x88FE;
	int  GL_ANY_SAMPLES_PASSED                            = 0x8C2F;
	int  GL_ANY_SAMPLES_PASSED_CONSERVATIVE               = 0x8D6A;
	int  GL_SAMPLER_BINDING                               = 0x8919;
	int  GL_RGB10_A2UI                                    = 0x906F;
	int  GL_TEXTURE_SWIZZLE_R                             = 0x8E42;
	int  GL_TEXTURE_SWIZZLE_G                             = 0x8E43;
	int  GL_TEXTURE_SWIZZLE_B                             = 0x8E44;
	int  GL_TEXTURE_SWIZZLE_A                             = 0x8E45;
	int  GL_GREEN                                         = 0x1904;
	int  GL_BLUE                                          = 0x1905;
	int  GL_INT_2_10_10_10_REV                            = 0x8D9F;
	int  GL_TRANSFORM_FEEDBACK                            = 0x8E22;
	int  GL_TRANSFORM_FEEDBACK_PAUSED                     = 0x8E23;
	int  GL_TRANSFORM_FEEDBACK_ACTIVE                     = 0x8E24;
	int  GL_TRANSFORM_FEEDBACK_BINDING                    = 0x8E25;
	int  GL_PROGRAM_BINARY_RETRIEVABLE_HINT               = 0x8257;
	int  GL_PROGRAM_BINARY_LENGTH                         = 0x8741;
	int  GL_NUM_PROGRAM_BINARY_FORMATS                    = 0x87FE;
	int  GL_PROGRAM_BINARY_FORMATS                        = 0x87FF;
	int  GL_COMPRESSED_R11_EAC                            = 0x9270;
	int  GL_COMPRESSED_SIGNED_R11_EAC                     = 0x9271;
	int  GL_COMPRESSED_RG11_EAC                           = 0x9272;
	int  GL_COMPRESSED_SIGNED_RG11_EAC                    = 0x9273;
	int  GL_COMPRESSED_RGB8_ETC2                          = 0x9274;
	int  GL_COMPRESSED_SRGB8_ETC2                         = 0x9275;
	int  GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2      = 0x9276;
	int  GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2     = 0x9277;
	int  GL_COMPRESSED_RGBA8_ETC2_EAC                     = 0x9278;
	int  GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC              = 0x9279;
	int  GL_TEXTURE_IMMUTABLE_FORMAT                      = 0x912F;
	int  GL_MAX_ELEMENT_INDEX                             = 0x8D6B;
	int  GL_NUM_SAMPLE_COUNTS                             = 0x9380;

	void glReadBuffer(@GLenum int mode);

	void glDrawRangeElements(@GLenum int mode, @GLuint int start, @GLuint int end, @AutoSize("indices") @GLsizei int count,
	                         @AutoType("indices")
	                         @GLenum int type,
	                         @BufferObject(BufferKind.ElementVBO)
	                         @Const
	                         @GLubyte
	                         @GLushort
	                         @GLuint Buffer indices);

	void glTexImage3D(@GLenum int target, int level, int internalFormat, @GLsizei int width, @GLsizei int height, @GLsizei int depth, int border, @GLenum int format, @GLenum int type,
	                  @BufferObject(BufferKind.UnpackPBO)
	                  @Check(value = "GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth)", canBeNull = true)
	                  @Const
	                  @GLbyte
	                  @GLshort
	                  @GLint
	                  @GLfloat Buffer pixels);

	void glTexSubImage3D(@GLenum int target, int level, int xoffset, int yoffset, int zoffset, @GLsizei int width, @GLsizei int height, @GLsizei int depth, @GLenum int format, @GLenum int type,
	                     @BufferObject(BufferKind.UnpackPBO)
	                     @Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, depth)")
	                     @Const
	                     @GLbyte
	                     @GLshort
	                     @GLint
	                     @GLfloat Buffer pixels);

	void glCopyTexSubImage3D(@GLenum int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, @GLsizei int width, @GLsizei int height);

	void glCompressedTexImage3D(@GLenum int target, int level, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, @GLsizei int depth, int border, @AutoSize("data") @GLsizei int imageSize,
	                            @BufferObject(BufferKind.UnpackPBO)
	                            @Check
	                            @Const
	                            @GLvoid
	                            ByteBuffer data);

	void glCompressedTexSubImage3D(@GLenum int target, int level, int xoffset, int yoffset, int zoffset, @GLsizei int width, @GLsizei int height, @GLsizei int depth, @GLenum int format, @AutoSize("data") @GLsizei int imageSize,
	                               @BufferObject(BufferKind.UnpackPBO)
	                               @Check
	                               @Const
	                               @GLvoid
	                               ByteBuffer data);

	void glGenQueries(@AutoSize("ids") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	@Alternate("glGenQueries")
	@GLreturn("ids")
	void glGenQueries2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	void glDeleteQueries(@AutoSize("ids") @GLsizei int n, @GLuint IntBuffer ids);

	@Alternate("glDeleteQueries")
	void glDeleteQueries(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(id)", keepParam = true) int id);

	boolean glIsQuery(@GLuint int id);

	void glBeginQuery(@GLenum int target, @GLuint int id);

	void glEndQuery(@GLenum int target);

	@StripPostfix("params")
	void glGetQueryiv(@GLenum int target, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetQueryiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryiv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetQueryObjectuiv(@GLenum int id, @GLenum int pname, @OutParameter @Check("1") @GLuint IntBuffer params);

	@Alternate("glGetQueryObjectuiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryObjectuiv2(@GLenum int id, @GLenum int pname, @OutParameter @GLuint IntBuffer params);

	boolean glUnmapBuffer(@GLenum int target);

	@StripPostfix("pointer")
	@AutoSize("GLChecks.getBufferObjectSize(target)")
	void glGetBufferPointerv(@GLenum int target, @GLenum int pname, @OutParameter @Result @GLvoid ByteBuffer pointer);

	void glDrawBuffers(@AutoSize("buffers") @GLsizei int size, @Const @GLenum IntBuffer buffers);

	@Alternate("glDrawBuffers")
	void glDrawBuffers(@Constant("1") @GLsizei int size, @Constant(value = "APIUtil.getInt(buffer)", keepParam = true) int buffer);

	@StripPostfix("matrices")
	void glUniformMatrix2x3fv(int location, @AutoSize(value = "matrices", expression = " / (2 * 3)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix3x2fv(int location, @AutoSize(value = "matrices", expression = " / (3 * 2)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix2x4fv(int location, @AutoSize(value = "matrices", expression = " >> 3") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix4x2fv(int location, @AutoSize(value = "matrices", expression = " >> 3") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix3x4fv(int location, @AutoSize(value = "matrices", expression = " / (3 * 4)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix4x3fv(int location, @AutoSize(value = "matrices", expression = " / (4 * 3)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

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

	/**
	 * Establishes the data storage, format, dimensions, and number of
	 * samples of a renderbuffer object's image.
	 */
	void glRenderbufferStorageMultisample(
		@GLenum int target, @GLsizei int samples,
		@GLenum int internalformat,
		@GLsizei int width, @GLsizei int height);

	void glFramebufferTextureLayer(@GLenum int target, @GLenum int attachment, @GLuint int texture, int level, int layer);

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

	@Code("		StateTracker.bindVAO(array);")
	void glBindVertexArray(@GLuint int array);

	@Code("		StateTracker.deleteVAO(arrays);")
	void glDeleteVertexArrays(@AutoSize("arrays") @GLsizei int n, @Const @GLuint IntBuffer arrays);

	@Alternate("glDeleteVertexArrays")
	@Code("		StateTracker.deleteVAO(array);")
	void glDeleteVertexArrays(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(array)", keepParam = true) int array);

	void glGenVertexArrays(@AutoSize("arrays") @GLsizei int n, @OutParameter @GLuint IntBuffer arrays);

	@Alternate("glGenVertexArrays")
	@GLreturn("arrays")
	void glGenVertexArrays2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer arrays);

	boolean glIsVertexArray(@GLuint int array);

	@StripPostfix("data")
	void glGetIntegeri_v(@GLenum int value, @GLuint int index, @OutParameter @Check("4") IntBuffer data);

	@Alternate("glGetIntegeri_v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetIntegeri_v2(@GLenum int value, @GLuint int index, @OutParameter IntBuffer data);

	void glBeginTransformFeedback(@GLenum int primitiveMode);

	void glEndTransformFeedback();

	void glBindBufferRange(@GLenum int target, @GLuint int index, @GLuint int buffer, @GLintptr long offset, @GLsizeiptr long size);

	void glBindBufferBase(@GLenum int target, @GLuint int index, @GLuint int buffer);

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

	@NoErrorCheck
	void glVertexAttribI4i(@GLuint int index, int x, int y, int z, int w);

	@NoErrorCheck
	void glVertexAttribI4ui(@GLuint int index, @GLuint int x, @GLuint int y, @GLuint int z, @GLuint int w);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4iv(@GLuint int index, @Check("4") @Const IntBuffer v);

	@NoErrorCheck
	@StripPostfix("v")
	void glVertexAttribI4uiv(@GLuint int index, @Check("4") @Const @GLuint IntBuffer v);

	@StripPostfix("params")
	void glGetUniformuiv(@GLuint int program, int location, @OutParameter @Check @GLuint IntBuffer params);

	int glGetFragDataLocation(@GLuint int program, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetFragDataLocation")
	int glGetFragDataLocation(@GLuint int program, @NullTerminated CharSequence name);

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

	@StripPostfix("value")
	void glClearBufferfv(@GLenum int buffer, int drawbuffer, @Const @Check("4") FloatBuffer value);

	@StripPostfix("value")
	void glClearBufferiv(@GLenum int buffer, int drawbuffer, @Const @Check("4") IntBuffer value);

	@StripPostfix("value")
	void glClearBufferuiv(@GLenum int buffer, int drawbuffer, @Const @Check("4") IntBuffer value);

	void glClearBufferfi(@GLenum int buffer, int drawbuffer, float depth, int stencil);

	String glGetStringi(@GLenum int name, @GLuint int index);

	void glCopyBufferSubData(@GLenum int readtarget, @GLenum int writetarget, @GLintptr long readoffset, @GLintptr long writeoffset, @GLsizeiptr long size);

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

	@Alternate("glGetActiveUniformsiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetActiveUniformsiv(@GLuint int program, @Constant("1") @GLsizei int uniformCount,
	                           @Constant(value = "MemoryUtil.getAddress(params.put(1, uniformIndex), 1)", keepParam = true) int uniformIndex, // Reuse params buffer
	                           @GLenum int pname,
	                           @OutParameter @GLint IntBuffer params);

	@GLuint
	int glGetUniformBlockIndex(@GLuint int program, @Const @NullTerminated @GLchar ByteBuffer uniformBlockName);

	@Alternate("glGetUniformBlockIndex")
	@GLuint
	int glGetUniformBlockIndex(@GLuint int program, @NullTerminated CharSequence uniformBlockName);

	@StripPostfix("params")
	void glGetActiveUniformBlockiv(@GLuint int program, @GLuint int uniformBlockIndex, @GLenum int pname,
	                               @OutParameter @Check(value = "16") @GLint IntBuffer params);

	@Alternate("glGetActiveUniformBlockiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetActiveUniformBlockiv2(@GLuint int program, @GLuint int uniformBlockIndex, @GLenum int pname,
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

	void glDrawArraysInstanced(@GLenum int mode, int first, @GLsizei int count, @GLsizei int primcount);

	void glDrawElementsInstanced(@GLenum int mode, @AutoSize("indices") @GLsizei int count, @AutoType("indices") @GLenum int type,
	                             @BufferObject(BufferKind.ElementVBO)
	                             @Const
	                             @GLubyte
	                             @GLushort
	                             @GLuint Buffer indices, @GLsizei int primcount);

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

	@Alternate("glGetSynciv")
	@GLreturn("values")
	@StripPostfix(value = "values", postfix = "v")
	void glGetSynciv2(@PointerWrapper("GLsync") GLSync sync, @GLenum int pname, @Constant("1") @GLsizei int bufSize,
	                  @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                  @OutParameter IntBuffer values);

	@StripPostfix("params")
	void glGetBufferParameteri64v(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") LongBuffer params);

	@Alternate("glGetBufferParameteri64v")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetBufferParameteri64v2(@GLenum int target, @GLenum int pname, @OutParameter LongBuffer params);

	void glGenSamplers(@AutoSize("samplers") @GLsizei int count, @OutParameter @GLuint IntBuffer samplers);

	@Alternate("glGenSamplers")
	@GLreturn("samplers")
	void glGenSamplers2(@Constant("1") @GLsizei int count, @OutParameter @GLuint IntBuffer samplers);

	void glDeleteSamplers(@AutoSize("samplers") @GLsizei int count, @Const @GLuint IntBuffer samplers);

	@Alternate("glDeleteSamplers")
	void glDeleteSamplers(@Constant("1") @GLsizei int count, @Constant(value = "APIUtil.getInt(sampler)", keepParam = true) int sampler);

	boolean glIsSampler(@GLuint int sampler);

	void glBindSampler(@GLenum int unit, @GLuint int sampler);

	void glSamplerParameteri(@GLuint int sampler, @GLenum int pname, int param);

	void glSamplerParameterf(@GLuint int sampler, @GLenum int pname, float param);

	@StripPostfix("params")
	void glSamplerParameteriv(@GLuint int sampler, @GLenum int pname, @Check("4") @Const IntBuffer params);

	@StripPostfix("params")
	void glSamplerParameterfv(@GLuint int sampler, @GLenum int pname, @Check("4") @Const FloatBuffer params);

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

	void glVertexAttribDivisor(@GLuint int index, @GLuint int divisor);

	void glBindTransformFeedback(@GLenum int target, @GLuint int id);

	void glDeleteTransformFeedbacks(@AutoSize("ids") @GLsizei int n, @Const @GLuint IntBuffer ids);

	@Alternate("glDeleteTransformFeedbacks")
	void glDeleteTransformFeedbacks(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(id)", keepParam = true) int id);

	void glGenTransformFeedbacks(@AutoSize("ids") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	@Alternate("glGenTransformFeedbacks")
	@GLreturn("ids")
	void glGenTransformFeedbacks2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	boolean glIsTransformFeedback(@GLuint int id);

	void glPauseTransformFeedback();

	void glResumeTransformFeedback();

	void glGetProgramBinary(@GLuint int program, @AutoSize("binary") @GLsizei int bufSize,
	                        @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                        @Check("1") @GLenum IntBuffer binaryFormat,
	                        @OutParameter @GLvoid ByteBuffer binary);

	void glProgramBinary(@GLuint int program, @GLenum int binaryFormat, @Const @GLvoid ByteBuffer binary, @AutoSize("binary") @GLsizei int length);

	void glProgramParameteri(@GLuint int program, @GLenum int pname, int value);

	void glInvalidateFramebuffer(@GLenum int target,
	                             @AutoSize("attachments") @GLsizei int numAttachments,
	                             @Const @GLenum IntBuffer attachments);

	void glInvalidateSubFramebuffer(@GLenum int target,
	                                @AutoSize("attachments") @GLsizei int numAttachments,
	                                @Const @GLenum IntBuffer attachments,
	                                int x, int y, @GLsizei int width, @GLsizei int height);

	void glTexStorage2D(@GLenum int target, @GLsizei int levels,
	                    @GLenum int internalformat,
	                    @GLsizei int width, @GLsizei int height);

	void glTexStorage3D(@GLenum int target, @GLsizei int levels,
	                    @GLenum int internalformat,
	                    @GLsizei int width, @GLsizei int height, @GLsizei int depth);

	@StripPostfix("params")
	void glGetInternalformativ(@GLenum int target, @GLenum int internalformat,
	                           @GLenum int pname, @AutoSize("params") @GLsizei int bufSize, @OutParameter IntBuffer params);

	@Alternate("glGetInternalformativ")
	@StripPostfix("params")
	@GLreturn("params")
	void glGetInternalformativ2(@GLenum int target, @GLenum int internalformat,
	                            @GLenum int pname, @Constant("1") @GLsizei int bufSize, @OutParameter IntBuffer params);

}