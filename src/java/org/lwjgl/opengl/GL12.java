/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * $Id$
 *
 * The core OpenGL1.2.1 API, with the imaging subset.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

public abstract class GL12 extends GL11 {
	public static final int GL_RESCALE_NORMAL                 = 0x803A;
	public static final int GL_CLAMP_TO_EDGE                  = 0x812F;
	public static final int GL_MAX_ELEMENTS_VERTICES          = 0x80E8;
	public static final int GL_MAX_ELEMENTS_INDICES           = 0x80E9;
	public static final int GL_BGR                            = 0x80E0;
	public static final int GL_BGRA                           = 0x80E1;
	public static final int GL_UNSIGNED_BYTE_3_3_2            = 0x8032;
	public static final int GL_UNSIGNED_BYTE_2_3_3_REV        = 0x8362;
	public static final int GL_UNSIGNED_SHORT_5_6_5           = 0x8363;
	public static final int GL_UNSIGNED_SHORT_5_6_5_REV       = 0x8364;
	public static final int GL_UNSIGNED_SHORT_4_4_4_4         = 0x8033;
	public static final int GL_UNSIGNED_SHORT_4_4_4_4_REV     = 0x8365;
	public static final int GL_UNSIGNED_SHORT_5_5_5_1         = 0x8034;
	public static final int GL_UNSIGNED_SHORT_1_5_5_5_REV     = 0x8366;
	public static final int GL_UNSIGNED_INT_8_8_8_8           = 0x8035;
	public static final int GL_UNSIGNED_INT_8_8_8_8_REV       = 0x8367;
	public static final int GL_UNSIGNED_INT_10_10_10_2        = 0x8036;
	public static final int GL_UNSIGNED_INT_2_10_10_10_REV    = 0x8368;
	public static final int GL_LIGHT_MODEL_COLOR_CONTROL      = 0x81F8;
	public static final int GL_SINGLE_COLOR                   = 0x81F9;
	public static final int GL_SEPARATE_SPECULAR_COLOR        = 0x81FA;
	public static final int GL_TEXTURE_MIN_LOD                = 0x813A;
	public static final int GL_TEXTURE_MAX_LOD                = 0x813B;
	public static final int GL_TEXTURE_BASE_LEVEL             = 0x813C;
	public static final int GL_TEXTURE_MAX_LEVEL              = 0x813D;
	public static final int GL_SMOOTH_POINT_SIZE_RANGE        = 0x0B12;
	public static final int GL_SMOOTH_POINT_SIZE_GRANULARITY  = 0x0B13;
	public static final int GL_SMOOTH_LINE_WIDTH_RANGE        = 0x0B22;
	public static final int GL_SMOOTH_LINE_WIDTH_GRANULARITY  = 0x0B23;
	public static final int GL_ALIASED_POINT_SIZE_RANGE       = 0x846D;
	public static final int GL_ALIASED_LINE_WIDTH_RANGE       = 0x846E;
	public static final int GL_PACK_SKIP_IMAGES               = 0x806B;
	public static final int GL_PACK_IMAGE_HEIGHT              = 0x806C;
	public static final int GL_UNPACK_SKIP_IMAGES             = 0x806D;
	public static final int GL_UNPACK_IMAGE_HEIGHT            = 0x806E;
	public static final int GL_TEXTURE_3D                     = 0x806F;
	public static final int GL_PROXY_TEXTURE_3D               = 0x8070;
	public static final int GL_TEXTURE_DEPTH                  = 0x8071;
	public static final int GL_TEXTURE_WRAP_R                 = 0x8072;
	public static final int GL_MAX_3D_TEXTURE_SIZE            = 0x8073;
	public static final int GL_TEXTURE_BINDING_3D             = 0x806A;
	public static final int GL_COLOR_TABLE                    = 0x80D0;
	public static final int GL_POST_CONVOLUTION_COLOR_TABLE   = 0x80D1;
	public static final int GL_POST_COLOR_MATRIX_COLOR_TABLE  = 0x80D2;
	public static final int GL_PROXY_COLOR_TABLE              = 0x80D3;
	public static final int GL_PROXY_POST_CONVOLUTION_COLOR_TABLE = 0x80D4;
	public static final int GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE = 0x80D5;
	public static final int GL_COLOR_TABLE_SCALE              = 0x80D6;
	public static final int GL_COLOR_TABLE_BIAS               = 0x80D7;
	public static final int GL_COLOR_TABLE_FORMAT             = 0x80D8;
	public static final int GL_COLOR_TABLE_WIDTH              = 0x80D9;
	public static final int GL_COLOR_TABLE_RED_SIZE           = 0x80DA;
	public static final int GL_COLOR_TABLE_GREEN_SIZE         = 0x80DB;
	public static final int GL_COLOR_TABLE_BLUE_SIZE          = 0x80DC;
	public static final int GL_COLOR_TABLE_ALPHA_SIZE         = 0x80DD;
	public static final int GL_COLOR_TABLE_LUMINANCE_SIZE     = 0x80DE;
	public static final int GL_COLOR_TABLE_INTENSITY_SIZE     = 0x80DF;
	public static final int GL_CONVOLUTION_1D                 = 0x8010;
	public static final int GL_CONVOLUTION_2D                 = 0x8011;
	public static final int GL_SEPARABLE_2D                   = 0x8012;
	public static final int GL_CONVOLUTION_BORDER_MODE        = 0x8013;
	public static final int GL_CONVOLUTION_FILTER_SCALE       = 0x8014;
	public static final int GL_CONVOLUTION_FILTER_BIAS        = 0x8015;
	public static final int GL_REDUCE                         = 0x8016;
	public static final int GL_CONVOLUTION_FORMAT             = 0x8017;
	public static final int GL_CONVOLUTION_WIDTH              = 0x8018;
	public static final int GL_CONVOLUTION_HEIGHT             = 0x8019;
	public static final int GL_MAX_CONVOLUTION_WIDTH          = 0x801A;
	public static final int GL_MAX_CONVOLUTION_HEIGHT         = 0x801B;
	public static final int GL_POST_CONVOLUTION_RED_SCALE     = 0x801C;
	public static final int GL_POST_CONVOLUTION_GREEN_SCALE   = 0x801D;
	public static final int GL_POST_CONVOLUTION_BLUE_SCALE    = 0x801E;
	public static final int GL_POST_CONVOLUTION_ALPHA_SCALE   = 0x801F;
	public static final int GL_POST_CONVOLUTION_RED_BIAS      = 0x8020;
	public static final int GL_POST_CONVOLUTION_GREEN_BIAS    = 0x8021;
	public static final int GL_POST_CONVOLUTION_BLUE_BIAS     = 0x8022;
	public static final int GL_POST_CONVOLUTION_ALPHA_BIAS    = 0x8023;
	public static final int GL_CONSTANT_BORDER                = 0x8151;
	public static final int GL_REPLICATE_BORDER               = 0x8153;
	public static final int GL_CONVOLUTION_BORDER_COLOR       = 0x8154;
	public static final int GL_COLOR_MATRIX                   = 0x80B1;
	public static final int GL_COLOR_MATRIX_STACK_DEPTH       = 0x80B2;
	public static final int GL_MAX_COLOR_MATRIX_STACK_DEPTH   = 0x80B3;
	public static final int GL_POST_COLOR_MATRIX_RED_SCALE    = 0x80B4;
	public static final int GL_POST_COLOR_MATRIX_GREEN_SCALE  = 0x80B5;
	public static final int GL_POST_COLOR_MATRIX_BLUE_SCALE   = 0x80B6;
	public static final int GL_POST_COLOR_MATRIX_ALPHA_SCALE  = 0x80B7;
	public static final int GL_POST_COLOR_MATRIX_RED_BIAS     = 0x80B8;
	public static final int GL_POST_COLOR_MATRIX_GREEN_BIAS   = 0x80B9;
	public static final int GL_POST_COLOR_MATRIX_BLUE_BIAS    = 0x80BA;
	public static final int GL_POST_COLOR_MATRIX_ALPHA_BIAS   = 0x80BB;
	public static final int GL_HISTOGRAM                      = 0x8024;
	public static final int GL_PROXY_HISTOGRAM                = 0x8025;
	public static final int GL_HISTOGRAM_WIDTH                = 0x8026;
	public static final int GL_HISTOGRAM_FORMAT               = 0x8027;
	public static final int GL_HISTOGRAM_RED_SIZE             = 0x8028;
	public static final int GL_HISTOGRAM_GREEN_SIZE           = 0x8029;
	public static final int GL_HISTOGRAM_BLUE_SIZE            = 0x802A;
	public static final int GL_HISTOGRAM_ALPHA_SIZE           = 0x802B;
	public static final int GL_HISTOGRAM_LUMINANCE_SIZE       = 0x802C;
	public static final int GL_HISTOGRAM_SINK                 = 0x802D;
	public static final int GL_MINMAX                         = 0x802E;
	public static final int GL_MINMAX_FORMAT                  = 0x802F;
	public static final int GL_MINMAX_SINK                    = 0x8030;
	public static final int GL_TABLE_TOO_LARGE                = 0x8031;
	public static final int GL_BLEND_EQUATION                 = 0x8009;
	public static final int GL_MIN                            = 0x8007;
	public static final int GL_MAX                            = 0x8008;
	public static final int GL_FUNC_ADD                       = 0x8006;
	public static final int GL_FUNC_SUBTRACT                  = 0x800A;
	public static final int GL_FUNC_REVERSE_SUBTRACT          = 0x800B;
	public static final int GL_BLEND_COLOR                    = 0x8005;
	
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data) {
		// TODO: check buffer size valid
		nglColorTable(target, internalFormat, width, format, type, data, data.position());
	}
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
		// TODO: check buffer size valid
		nglColorTable(target, internalFormat, width, format, type, data, data.position() << 2);
	}
	private static native void nglColorTable(int target, int internalFormat, int width, int format, int type, Buffer data, int data_offset);
	public static void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data) {
		// TODO: check buffer size valid
		nglColorSubTable(target, start, count, format, type, data, data.position());
	}
	public static void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data) {
		// TODO: check buffer size valid
		nglColorSubTable(target, start, count, format, type, data, data.position() << 2);
	}
	private static native void nglColorSubTable(int target, int start, int count, int format, int type, Buffer data, int data_offset);
	public static void glColorTableParameter(int target, int pname, IntBuffer params) {
		// TODO: check buffer size valid
		nglColorTableParameteriv(target, pname, params, params.position());
	}
	private static native void nglColorTableParameteriv(int target, int pname, IntBuffer params, int data_offset);
	public static void glColorTableParameter(int target, int pname, FloatBuffer params) {
		// TODO: check buffer size valid
		nglColorTableParameterfv(target, pname, params, params.position());		
	}
	private static native void nglColorTableParameterfv(int target, int pname, FloatBuffer params, int data_offset);
	public static native void glCopyColorSubTable(int target, int start, int x, int y, int width);
	public static native void glCopyColorTable(int target, int internalformat, int x, int y, int width);
	public static void glGetColorTable(int target, int format, int type, ByteBuffer data) {
		// TODO: check buffer size valid
		nglGetColorTable(target, format, type, data, data.position());
	}
	public static void glGetColorTable(int target, int format, int type, FloatBuffer data) {
		// TODO: check buffer size valid
		nglGetColorTable(target, format, type, data, data.position());
	}
	private static native void nglGetColorTable(int target, int format, int type, Buffer data, int data_offset);
	public static void glGetColorTableParameter(int target, int pname, IntBuffer params) {
		// TODO: check buffer size valid
		nglGetColorTableParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetColorTableParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static void glGetColorTableParameter(int target, int pname, FloatBuffer params) {
		// TODO: check buffer size valid
		nglGetColorTableParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetColorTableParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static native void glBlendEquation(int mode);
	public static native void glBlendColor(float red, float green, float blue, float alpha);
	public static native void glHistogram(int target, int width, int internalformat, boolean sink);
	public static native void glResetHistogram(int target);
	public static void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
		// TODO: check buffer size valid
		nglGetHistogram(target, reset, format, type, values, values.position());
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values) {
		// TODO: check buffer size valid
		nglGetHistogram(target, reset, format, type, values, values.position() << 1);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values) {
		// TODO: check buffer size valid
		nglGetHistogram(target, reset, format, type, values, values.position() << 2);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values) {
		// TODO: check buffer size valid
		nglGetHistogram(target, reset, format, type, values, values.position() << 2);
	}
	private static native void nglGetHistogram(int target, boolean reset, int format, int type, Buffer values, int values_offset);
	public static void glGetHistogramParameter(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetHistogramParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetHistogramParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glGetHistogramParameter(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetHistogramParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetHistogramParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static native void glMinmax(int target, int internalformat, boolean sink);
	public static native void glResetMinmax(int target);
	public static void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values) {
		BufferChecks.checkBuffer(values);
		nglGetMinmax(target, reset, format, types, values, values.position());
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values) {
		BufferChecks.checkBuffer(values);
		nglGetMinmax(target, reset, format, types, values, values.position() << 1);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values) {
		BufferChecks.checkBuffer(values);
		nglGetMinmax(target, reset, format, types, values, values.position() << 2);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values) {
		BufferChecks.checkBuffer(values);
		nglGetMinmax(target, reset, format, types, values, values.position() << 2);
	}
	private static native void nglGetMinmax(int target, boolean reset, int format, int types, Buffer values, int values_offset);
	public static void glGetMinmaxParameter(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetMinmaxParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetMinmaxParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glGetMinmaxParameter(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetMinmaxParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetMinmaxParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image) {
		// TODO: check buffer size valid
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image) {
		// TODO: check buffer size valid
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image) {
		// TODO: check buffer size valid
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image) {
		// TODO: check buffer size valid
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	private static native void nglConvolutionFilter1D(int target, int internalformat, int width, int format, int type, Buffer image, int image_offset);
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer image) {
	}
	private static native void nglConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer image, int image_offset);
	public static native void glConvolutionParameterf(int target, int pname, float params);
	public static void glConvolutionParameter(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglConvolutionParameterfv(target, pname, params, params.position());
	}
	private static native void nglConvolutionParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static native void glConvolutionParameteri(int target, int pname, int params);
	public static void glConvolutionParameteriv(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglConvolutionParameteriv(target, pname, params, params.position());
	}
	private static native void nglConvolutionParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static native void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width);
	public static native void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height);
	public static void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image) {
		// TODO: check buffer size valid
		nglGetConvolutionFilter(target, format, type, image, image.position());
	}
	public static void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image) {
		// TODO: check buffer size valid
		nglGetConvolutionFilter(target, format, type, image, image.position() << 1);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, IntBuffer image) {
		// TODO: check buffer size valid
		nglGetConvolutionFilter(target, format, type, image, image.position() << 2);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image) {
		// TODO: check buffer size valid
		nglGetConvolutionFilter(target, format, type, image, image.position() << 2);
	}
	private static native void nglGetConvolutionFilter(int target, int format, int type, Buffer image, int image_offset);
	public static void glGetConvolutionParameter(int target, int pname, FloatBuffer params) {
		// TODO: check buffer size valid
		nglGetConvolutionParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetConvolutionParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glGetConvolutionParameter(int target, int pname, IntBuffer params) {
		// TODO: check buffer size valid
		nglGetConvolutionParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetConvolutionParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, Buffer column) {
		// TODO: check buffer size valid
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, Util.getOffset(row), column, Util.getOffset(column));
	}
	private static native void nglSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, int row_offset, Buffer column, int column_offset);
	public static void glGetSeparableFilter(int target, int format, int type, Buffer row, Buffer column, Buffer span) {
		// TODO: check buffer size valid
		nglGetSeparableFilter(target, format, type, row, Util.getOffset(row), column, Util.getOffset(column), span, Util.getOffset(span));
	}
	private static native void nglGetSeparableFilter(int target, int format, int type, Buffer row, int row_offset, Buffer column, int column_offset, Buffer span, int span_offset);
	public static void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices) {
		BufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElements(mode, start, end, indices.remaining(), GL_UNSIGNED_BYTE, indices, indices.position());
	}
	public static void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) {
		BufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElements(mode, start, end, indices.remaining(), GL_UNSIGNED_SHORT, indices, indices.position() << 1);
	}
	public static void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) {
		BufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElements(mode, start, end, indices.remaining(), GL_UNSIGNED_INT, indices, indices.position() << 2);
	}
	private static native void nglDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices, int indices_offset);
	public static void glDrawRangeElements(int mode, int start, int end, int count, int type, int buffer_offset) {
		BufferChecks.ensureElementVBOenabled();
		nglDrawRangeElementsVBO(mode, start, end, count, type, buffer_offset);
	}
	private static native void nglDrawRangeElementsVBO(int mode, int start, int end, int count, int type, int buffer_offset);
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
		if (pixels.remaining() < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position());
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
		if (pixels.remaining() * 2 < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
		if (pixels.remaining() * 4 < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
		if (pixels.remaining() * 4 < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, Buffer pixels, int pixels_offset);
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
		if (pixels.remaining() < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position());
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
		if (pixels.remaining() * 2 < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
		if (pixels.remaining() * 4 < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
		if (pixels.remaining() * 4 < BufferChecks.calculateImageStorage(format, type, width, height, depth)) {
			throw new BufferUnderflowException();
		}
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, Buffer pixels, int pixels_offset);
	public static native void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);
}

