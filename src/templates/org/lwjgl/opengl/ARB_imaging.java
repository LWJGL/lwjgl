/*
 * Copyright (c) 2002-2004 LWJGL Project
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

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferChecks;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.generator.*;

/**
 * $Id$
 * <p/>
 * The GL12 imaging subset extension.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

@Extension(postfix="")
public interface ARB_imaging {
	public static final int GL_CONSTANT_COLOR = 0x8001;
	public static final int GL_ONE_MINUS_CONSTANT_COLOR = 0x8002;
	public static final int GL_CONSTANT_ALPHA = 0x8003;
	public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004;
	public static final int GL_BLEND_COLOR = 0x8005;
	public static final int GL_FUNC_ADD = 0x8006;
	public static final int GL_MIN = 0x8007;
	public static final int GL_MAX = 0x8008;
	public static final int GL_BLEND_EQUATION = 0x8009;
	public static final int GL_FUNC_SUBTRACT = 0x800A;
	public static final int GL_FUNC_REVERSE_SUBTRACT = 0x800B;
	public static final int GL_COLOR_MATRIX = 0x80B1;
	public static final int GL_COLOR_MATRIX_STACK_DEPTH = 0x80B2;
	public static final int GL_MAX_COLOR_MATRIX_STACK_DEPTH = 0x80B3;
	public static final int GL_POST_COLOR_MATRIX_RED_SCALE = 0x80B4;
	public static final int GL_POST_COLOR_MATRIX_GREEN_SCALE = 0x80B5;
	public static final int GL_POST_COLOR_MATRIX_BLUE_SCALE = 0x80B6;
	public static final int GL_POST_COLOR_MATRIX_ALPHA_SCALE = 0x80B7;
	public static final int GL_POST_COLOR_MATRIX_RED_BIAS = 0x80B8;
	public static final int GL_POST_COLOR_MATRIX_GREEN_BIAS = 0x80B9;
	public static final int GL_POST_COLOR_MATRIX_BLUE_BIAS = 0x80BA;
	public static final int GL_POST_COLOR_MATRIX_ALPHA_BIAS = 0x80BB;
	public static final int GL_COLOR_TABLE = 0x80D0;
	public static final int GL_POST_CONVOLUTION_COLOR_TABLE = 0x80D1;
	public static final int GL_POST_COLOR_MATRIX_COLOR_TABLE = 0x80D2;
	public static final int GL_PROXY_COLOR_TABLE = 0x80D3;
	public static final int GL_PROXY_POST_CONVOLUTION_COLOR_TABLE = 0x80D4;
	public static final int GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE = 0x80D5;
	public static final int GL_COLOR_TABLE_SCALE = 0x80D6;
	public static final int GL_COLOR_TABLE_BIAS = 0x80D7;
	public static final int GL_COLOR_TABLE_FORMAT = 0x80D8;
	public static final int GL_COLOR_TABLE_WIDTH = 0x80D9;
	public static final int GL_COLOR_TABLE_RED_SIZE = 0x80DA;
	public static final int GL_COLOR_TABLE_GREEN_SIZE = 0x80DB;
	public static final int GL_COLOR_TABLE_BLUE_SIZE = 0x80DC;
	public static final int GL_COLOR_TABLE_ALPHA_SIZE = 0x80DD;
	public static final int GL_COLOR_TABLE_LUMINANCE_SIZE = 0x80DE;
	public static final int GL_COLOR_TABLE_INTENSITY_SIZE = 0x80DF;
	public static final int GL_CONVOLUTION_1D = 0x8010;
	public static final int GL_CONVOLUTION_2D = 0x8011;
	public static final int GL_SEPARABLE_2D = 0x8012;
	public static final int GL_CONVOLUTION_BORDER_MODE = 0x8013;
	public static final int GL_CONVOLUTION_FILTER_SCALE = 0x8014;
	public static final int GL_CONVOLUTION_FILTER_BIAS = 0x8015;
	public static final int GL_REDUCE = 0x8016;
	public static final int GL_CONVOLUTION_FORMAT = 0x8017;
	public static final int GL_CONVOLUTION_WIDTH = 0x8018;
	public static final int GL_CONVOLUTION_HEIGHT = 0x8019;
	public static final int GL_MAX_CONVOLUTION_WIDTH = 0x801A;
	public static final int GL_MAX_CONVOLUTION_HEIGHT = 0x801B;
	public static final int GL_POST_CONVOLUTION_RED_SCALE = 0x801C;
	public static final int GL_POST_CONVOLUTION_GREEN_SCALE = 0x801D;
	public static final int GL_POST_CONVOLUTION_BLUE_SCALE = 0x801E;
	public static final int GL_POST_CONVOLUTION_ALPHA_SCALE = 0x801F;
	public static final int GL_POST_CONVOLUTION_RED_BIAS = 0x8020;
	public static final int GL_POST_CONVOLUTION_GREEN_BIAS = 0x8021;
	public static final int GL_POST_CONVOLUTION_BLUE_BIAS = 0x8022;
	public static final int GL_POST_CONVOLUTION_ALPHA_BIAS = 0x8023;
	public static final int GL_IGNORE_BORDER = 0x8150;
	public static final int GL_CONSTANT_BORDER = 0x8151;
	public static final int GL_REPLICATE_BORDER = 0x8153;
	public static final int GL_CONVOLUTION_BORDER_COLOR = 0x8154;
	public static final int GL_HISTOGRAM = 0x8024;
	public static final int GL_PROXY_HISTOGRAM = 0x8025;
	public static final int GL_HISTOGRAM_WIDTH = 0x8026;
	public static final int GL_HISTOGRAM_FORMAT = 0x8027;
	public static final int GL_HISTOGRAM_RED_SIZE = 0x8028;
	public static final int GL_HISTOGRAM_GREEN_SIZE = 0x8029;
	public static final int GL_HISTOGRAM_BLUE_SIZE = 0x802A;
	public static final int GL_HISTOGRAM_ALPHA_SIZE = 0x802B;
	public static final int GL_HISTOGRAM_LUMINANCE_SIZE = 0x802C;
	public static final int GL_HISTOGRAM_SINK = 0x802D;
	public static final int GL_MINMAX = 0x802E;
	public static final int GL_MINMAX_FORMAT = 0x802F;
	public static final int GL_MINMAX_SINK = 0x8030;

	public void glColorTable(@GLenum int target, @GLenum int internalFormat, @GLsizei int width, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.UnpackPBO)
			@Check("256")
			@Const
			@GLbyte
			@GLfloat
			Buffer data);
	
	public void glColorSubTable(@GLenum int target, @GLsizei int start, @GLsizei int count, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.UnpackPBO)
			@Check("256")
			@Const
			@GLbyte
			@GLfloat
			Buffer data);

	@StripPostfix("params")
	public void glColorTableParameteriv(@GLenum int target, @GLenum int pname, @Check("4") IntBuffer params);

	@StripPostfix("params")
	public void glColorTableParameterfv(@GLenum int target, @GLenum int pname, @Check("4") FloatBuffer params);

	public void glCopyColorSubTable(@GLenum int target, @GLsizei int start, int x, int y, @GLsizei int width);

	public void glCopyColorTable(@GLenum int target, @GLenum int internalformat, int x, int y, @GLsizei int width);

	public void glGetColorTable(@GLenum int target, @GLenum int format, @GLenum int type,
			@Check("256")
			@GLbyte
			@GLfloat
			Buffer data);

	@StripPostfix("params")
	public void glGetColorTableParameteriv(@GLenum int target, @GLenum int pname, @Check("4") IntBuffer params);
	@StripPostfix("params")
	public void glGetColorTableParameterfv(@GLenum int target, @GLenum int pname, @Check("4") FloatBuffer params);

	public void glBlendEquation(@GLenum int mode);

	public void glBlendColor(@GLclampf float red, @GLclampf float green, @GLclampf float blue, @GLclampf float alpha);

	public void glHistogram(@GLenum int target, @GLsizei int width, @GLenum int internalformat, boolean sink);

	public void glResetHistogram(@GLenum int target);

	public void glGetHistogram(@GLenum int target, boolean reset, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.PackPBO)
			@Check("256")
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer values);

	@StripPostfix("params")
	public void glGetHistogramParameterfv(@GLenum int target, @GLenum int pname, @Check("256") FloatBuffer params);
	@StripPostfix("params")
	public void glGetHistogramParameteriv(@GLenum int target, @GLenum int pname, @Check("256") IntBuffer params);

	public void glMinmax(@GLenum int target, @GLenum int internalformat, boolean sink);

	public void glResetMinmax(@GLenum int target);

	public void glGetMinmax(@GLenum int target, boolean reset, @GLenum int format, @GLenum int types,
			@BufferObject(BufferKind.PackPBO)
			@Check("4")
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer values);
	
	@StripPostfix("params")
	public void glGetMinmaxParameterfv(@GLenum int target, @GLenum int pname, @Check("4") FloatBuffer params);
	@StripPostfix("params")
	public void glGetMinmaxParameteriv(@GLenum int target, @GLenum int pname, @Check("4") IntBuffer params);

	public void glConvolutionFilter1D(@GLenum int target, @GLenum int internalformat, @GLsizei int width, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.UnpackPBO)
			@Check("GLBufferChecks.calculateImageStorage(image, format, type, width, 1, 1)")
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer image);

	public void glConvolutionFilter2D(@GLenum int target, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.UnpackPBO)
			@Check("GLBufferChecks.calculateImageStorage(image, format, type, width, height, 1)")
			@GLbyte
			@GLshort
			@GLint
			Buffer image);

	public void glConvolutionParameterf(@GLenum int target, @GLenum int pname, float params);
	
	@StripPostfix("params")
	public void glConvolutionParameterfv(@GLenum int target, @GLenum int pname, @Check("4") @Const FloatBuffer params);
	
	public void glConvolutionParameteri(@GLenum int target, @GLenum int pname, int params);
	
	@StripPostfix("params")
	public void glConvolutionParameteriv(@GLenum int target, @GLenum int pname, @Check("4") @Const IntBuffer params);

	public void glCopyConvolutionFilter1D(@GLenum int target, @GLenum int internalformat, int x, int y, @GLsizei int width);

	public void glCopyConvolutionFilter2D(@GLenum int target, @GLenum int internalformat, int x, int y, @GLsizei int width, @GLsizei int height);

	// TODO: check buffer size valid
	public void glGetConvolutionFilter(@GLenum int target, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.PackPBO)
			@Check
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer image);

	@StripPostfix("params")
	public void glGetConvolutionParameterfv(@GLenum int target, @GLenum int pname, @Check("4") FloatBuffer params);
	@StripPostfix("params")
	public void glGetConvolutionParameteriv(@GLenum int target, @GLenum int pname, @Check("4") IntBuffer params);

	// TODO: check buffer size valid
	public void glSeparableFilter2D(@GLenum int target, @GLenum int internalformat, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.UnpackPBO)
			@Check
			@Const
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer row,
			@BufferObject(BufferKind.UnpackPBO)
			@Check
			@Const
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer column);

	// TODO: check buffer size valid
	public void glGetSeparableFilter(@GLenum int target, @GLenum int format, @GLenum int type,
			@BufferObject(BufferKind.PackPBO)
			@Check
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer row,
			@BufferObject(BufferKind.PackPBO)
			@Check
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer column,
			@BufferObject(BufferKind.PackPBO)
			@Check
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer span);
}

