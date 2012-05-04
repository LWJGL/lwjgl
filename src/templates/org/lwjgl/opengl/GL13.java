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

import java.nio.*;

/**
 * <p/>
 * The core OpenGL1.3 API.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
@DeprecatedGL
public interface GL13 {
	int GL_TEXTURE0 = 0x84C0;
	int GL_TEXTURE1 = 0x84C1;
	int GL_TEXTURE2 = 0x84C2;
	int GL_TEXTURE3 = 0x84C3;
	int GL_TEXTURE4 = 0x84C4;
	int GL_TEXTURE5 = 0x84C5;
	int GL_TEXTURE6 = 0x84C6;
	int GL_TEXTURE7 = 0x84C7;
	int GL_TEXTURE8 = 0x84C8;
	int GL_TEXTURE9 = 0x84C9;
	int GL_TEXTURE10 = 0x84CA;
	int GL_TEXTURE11 = 0x84CB;
	int GL_TEXTURE12 = 0x84CC;
	int GL_TEXTURE13 = 0x84CD;
	int GL_TEXTURE14 = 0x84CE;
	int GL_TEXTURE15 = 0x84CF;
	int GL_TEXTURE16 = 0x84D0;
	int GL_TEXTURE17 = 0x84D1;
	int GL_TEXTURE18 = 0x84D2;
	int GL_TEXTURE19 = 0x84D3;
	int GL_TEXTURE20 = 0x84D4;
	int GL_TEXTURE21 = 0x84D5;
	int GL_TEXTURE22 = 0x84D6;
	int GL_TEXTURE23 = 0x84D7;
	int GL_TEXTURE24 = 0x84D8;
	int GL_TEXTURE25 = 0x84D9;
	int GL_TEXTURE26 = 0x84DA;
	int GL_TEXTURE27 = 0x84DB;
	int GL_TEXTURE28 = 0x84DC;
	int GL_TEXTURE29 = 0x84DD;
	int GL_TEXTURE30 = 0x84DE;
	int GL_TEXTURE31 = 0x84DF;
	int GL_ACTIVE_TEXTURE = 0x84E0;
	int GL_CLIENT_ACTIVE_TEXTURE = 0x84E1;
	int GL_MAX_TEXTURE_UNITS = 0x84E2;

	int GL_NORMAL_MAP = 0x8511;
	int GL_REFLECTION_MAP = 0x8512;
	int GL_TEXTURE_CUBE_MAP = 0x8513;
	int GL_TEXTURE_BINDING_CUBE_MAP = 0x8514;
	int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515;
	int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516;
	int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517;
	int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518;
	int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519;
	int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851A;
	int GL_PROXY_TEXTURE_CUBE_MAP = 0x851B;
	int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 0x851C;

	int GL_COMPRESSED_ALPHA = 0x84E9;
	int GL_COMPRESSED_LUMINANCE = 0x84EA;
	int GL_COMPRESSED_LUMINANCE_ALPHA = 0x84EB;
	int GL_COMPRESSED_INTENSITY = 0x84EC;
	int GL_COMPRESSED_RGB = 0x84ED;
	int GL_COMPRESSED_RGBA = 0x84EE;
	int GL_TEXTURE_COMPRESSION_HINT = 0x84EF;
	int GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 0x86A0;
	int GL_TEXTURE_COMPRESSED = 0x86A1;
	int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2;
	int GL_COMPRESSED_TEXTURE_FORMATS = 0x86A3;

	int GL_MULTISAMPLE = 0x809D;
	int GL_SAMPLE_ALPHA_TO_COVERAGE = 0x809E;
	int GL_SAMPLE_ALPHA_TO_ONE = 0x809F;
	int GL_SAMPLE_COVERAGE = 0x80A0;
	int GL_SAMPLE_BUFFERS = 0x80A8;
	int GL_SAMPLES = 0x80A9;
	int GL_SAMPLE_COVERAGE_VALUE = 0x80AA;
	int GL_SAMPLE_COVERAGE_INVERT = 0x80AB;
	int GL_MULTISAMPLE_BIT = 0x20000000;

	int GL_TRANSPOSE_MODELVIEW_MATRIX = 0x84E3;
	int GL_TRANSPOSE_PROJECTION_MATRIX = 0x84E4;
	int GL_TRANSPOSE_TEXTURE_MATRIX = 0x84E5;
	int GL_TRANSPOSE_COLOR_MATRIX = 0x84E6;

	int GL_COMBINE = 0x8570;
	int GL_COMBINE_RGB = 0x8571;
	int GL_COMBINE_ALPHA = 0x8572;
	int GL_SOURCE0_RGB = 0x8580;
	int GL_SOURCE1_RGB = 0x8581;
	int GL_SOURCE2_RGB = 0x8582;
	int GL_SOURCE0_ALPHA = 0x8588;
	int GL_SOURCE1_ALPHA = 0x8589;
	int GL_SOURCE2_ALPHA = 0x858A;
	int GL_OPERAND0_RGB = 0x8590;
	int GL_OPERAND1_RGB = 0x8591;
	int GL_OPERAND2_RGB = 0x8592;
	int GL_OPERAND0_ALPHA = 0x8598;
	int GL_OPERAND1_ALPHA = 0x8599;
	int GL_OPERAND2_ALPHA = 0x859A;
	int GL_RGB_SCALE = 0x8573;
	int GL_ADD_SIGNED = 0x8574;
	int GL_INTERPOLATE = 0x8575;
	int GL_SUBTRACT = 0x84E7;
	int GL_CONSTANT = 0x8576;
	int GL_PRIMARY_COLOR = 0x8577;
	int GL_PREVIOUS = 0x8578;
	int GL_DOT3_RGB = 0x86AE;
	int GL_DOT3_RGBA = 0x86AF;
	int GL_CLAMP_TO_BORDER = 0x812D;

	void glActiveTexture(@GLenum int texture);

    @Code("\t\tStateTracker.getReferences(caps).glClientActiveTexture = texture - GL_TEXTURE0;")
    @DeprecatedGL
	void glClientActiveTexture(@GLenum int texture);

	void glCompressedTexImage1D(@GLenum int target, int level, @GLenum int internalformat, @GLsizei int width, int border, @AutoSize("data") @GLsizei int imageSize,
	                            @BufferObject(BufferKind.UnpackPBO)
	                            @Check
	                            @Const
	                            @GLvoid
	                            Buffer data);

	void glCompressedTexImage2D(@GLenum int target, int level, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, int border, @AutoSize("data") @GLsizei int imageSize,
	                            @BufferObject(BufferKind.UnpackPBO)
	                            @Check
	                            @Const
	                            @GLvoid
	                            ByteBuffer data);

	void glCompressedTexImage3D(@GLenum int target, int level, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, @GLsizei int depth, int border, @AutoSize("data") @GLsizei int imageSize,
	                            @BufferObject(BufferKind.UnpackPBO)
	                            @Check
	                            @Const
	                            @GLvoid
	                            ByteBuffer data);

	void glCompressedTexSubImage1D(@GLenum int target, int level, int xoffset, @GLsizei int width, @GLenum int format, @AutoSize("data") @GLsizei int imageSize,
	                               @BufferObject(BufferKind.UnpackPBO)
	                               @Check
	                               @Const
	                               @GLvoid
	                               ByteBuffer data);

	void glCompressedTexSubImage2D(@GLenum int target, int level, int xoffset, int yoffset, @GLsizei int width, @GLsizei int height, @GLenum int format, @AutoSize("data") @GLsizei int imageSize,
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

	// TODO: check buffer size valid
	void glGetCompressedTexImage(@GLenum int target, int lod,
			                     @OutParameter
	                             @BufferObject(BufferKind.PackPBO)
	                             @Check
	                             @GLbyte
	                             @GLshort
	                             @GLint Buffer img);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord1f(@GLenum int target, float s);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord1d(@GLenum int target, double s);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord2f(@GLenum int target, float s, float t);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord2d(@GLenum int target, double s, double t);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord3f(@GLenum int target, float s, float t, float r);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord3d(@GLenum int target, double s, double t, double r);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord4f(@GLenum int target, float s, float t, float r, float q);

	@NoErrorCheck
	@DeprecatedGL
	void glMultiTexCoord4d(@GLenum int target, double s, double t, double r, double q);

	@StripPostfix("m")
	@DeprecatedGL
	void glLoadTransposeMatrixf(@Check("16") @Const FloatBuffer m);

	@StripPostfix("m")
	@DeprecatedGL
	void glLoadTransposeMatrixd(@Check("16") @Const DoubleBuffer m);

	@StripPostfix("m")
	@DeprecatedGL
	void glMultTransposeMatrixf(@Check("16") @Const FloatBuffer m);

	@StripPostfix("m")
	@DeprecatedGL
	void glMultTransposeMatrixd(@Check("16") @Const DoubleBuffer m);

	void glSampleCoverage(float value, boolean invert);
}

