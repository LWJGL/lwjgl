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

import java.nio.*;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.Buffer;

/**
 * $Id: CoreGL.java,v 1.23 2003/07/23 14:51:19 elias_naur Exp $
 *
 * The core OpenGL1.2.1 API, with the imaging subset.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision: 1.23 $
 */
public class CoreGL12 extends CoreGL11 implements CoreGL12Constants {
	
	/**
	 * A helper function which is used to get the byte offset in an arbitrary buffer
	 * based on its position
	 * @return the position of the buffer, in BYTES
	 */
	static int getOffset(Buffer buffer) {
		if (buffer instanceof FloatBuffer || buffer instanceof IntBuffer)
			return buffer.position() << 2;
		else if (buffer instanceof ShortBuffer || buffer instanceof CharBuffer)
			return buffer.position() << 1;
		else if (buffer instanceof DoubleBuffer || buffer instanceof LongBuffer)
			return buffer.position() << 3;
		else
			return buffer.position();
	}
	
	
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data) {
		nglColorTable(target, internalFormat, width, format, type, data, data.position());
	}
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
		nglColorTable(target, internalFormat, width, format, type, data, data.position() << 2);
	}
	private static native void nglColorTable(int target, int internalFormat, int width, int format, int type, Buffer data, int data_offset);
	public static void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data) {
		nglColorSubTable(target, start, count, format, type, data, data.position());
	}
	public static void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data) {
		nglColorSubTable(target, start, count, format, type, data, data.position() << 2);
	}
	private static native void nglColorSubTable(int target, int start, int count, int format, int type, Buffer data, int data_offset);
	public static void glColorTableParameter(int target, int pname, IntBuffer params) {
		nglColorTableParameteriv(target, pname, params, params.position());
	}
	private static native void nglColorTableParameteriv(int target, int pname, IntBuffer params, int data_offset);
	public static void glColorTableParameter(int target, int pname, FloatBuffer params) {
		nglColorTableParameterfv(target, pname, params, params.position());		
	}
	private static native void nglColorTableParameterfv(int target, int pname, FloatBuffer params, int data_offset);
	public static native void glCopyColorSubTable(int target, int start, int x, int y, int width);
	public static native void glCopyColorTable(int target, int internalformat, int x, int y, int width);
	public static void glGetColorTable(int target, int format, int type, ByteBuffer data) {
		nglGetColorTable(target, format, type, data, data.position());
	}
	public static void glGetColorTable(int target, int format, int type, FloatBuffer data) {
		nglGetColorTable(target, format, type, data, data.position());
	}
	private static native void nglGetColorTable(int target, int format, int type, Buffer data, int data_offset);
	public static void glGetColorTableParameter(int target, int pname, IntBuffer params) {
		nglGetColorTableParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetColorTableParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static void glGetColorTableParameter(int target, int pname, FloatBuffer params) {
		nglGetColorTableParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetColorTableParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static native void glBlendEquation(int mode);
	public static native void glBlendColor(float red, float green, float blue, float alpha);
	public static native void glHistogram(int target, int width, int internalformat, boolean sink);
	public static native void glResetHistogram(int target);
	public static void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
		nglGetHistogram(target, reset, format, type, values, values.position());
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values) {
		nglGetHistogram(target, reset, format, type, values, values.position() << 1);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values) {
		nglGetHistogram(target, reset, format, type, values, values.position() << 2);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values) {
		nglGetHistogram(target, reset, format, type, values, values.position() << 2);
	}
	private static native void nglGetHistogram(int target, boolean reset, int format, int type, Buffer values, int values_offset);
	public static void glGetHistogramParameter(int target, int pname, FloatBuffer params) {
		nglGetHistogramParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetHistogramParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glGetHistogramParameter(int target, int pname, IntBuffer params) {
		nglGetHistogramParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetHistogramParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static native void glMinmax(int target, int internalformat, boolean sink);
	public static native void glResetMinmax(int target);
	public static void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values) {
		nglGetMinmax(target, reset, format, types, values, values.position());
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values) {
		nglGetMinmax(target, reset, format, types, values, values.position() << 1);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values) {
		nglGetMinmax(target, reset, format, types, values, values.position() << 2);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values) {
		nglGetMinmax(target, reset, format, types, values, values.position() << 2);
	}
	private static native void nglGetMinmax(int target, boolean reset, int format, int types, Buffer values, int values_offset);
	public static void glGetMinmaxParameter(int target, int pname, FloatBuffer params) {
		nglGetMinmaxParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetMinmaxParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glGetMinmaxParameter(int target, int pname, IntBuffer params) {
		nglGetMinmaxParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetMinmaxParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image) {
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image) {
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image) {
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image) {
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position());
	}
	private static native void nglConvolutionFilter1D(int target, int internalformat, int width, int format, int type, Buffer image, int image_offset);
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer image) {
	}
	private static native void nglConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer image, int image_offset);
	public static native void glConvolutionParameterf(int target, int pname, float params);
	public static void glConvolutionParameter(int target, int pname, FloatBuffer params) {
	}
	private static native void nglConvolutionParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static native void glConvolutionParameteri(int target, int pname, int params);
	public static void glConvolutionParameteriv(int target, int pname, IntBuffer params) {
		nglConvolutionParameteriv(target, pname, params, params.position());
	}
	private static native void nglConvolutionParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static native void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width);
	public static native void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height);
	public static void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image) {
		nglGetConvolutionFilter(target, format, type, image, image.position());
	}
	public static void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image) {
		nglGetConvolutionFilter(target, format, type, image, image.position() << 1);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, IntBuffer image) {
		nglGetConvolutionFilter(target, format, type, image, image.position() << 2);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image) {
		nglGetConvolutionFilter(target, format, type, image, image.position() << 2);
	}
	private static native void nglGetConvolutionFilter(int target, int format, int type, Buffer image, int image_offset);
	public static void glGetConvolutionParameter(int target, int pname, FloatBuffer params) {
		nglGetConvolutionParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetConvolutionParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glGetConvolutionParameter(int target, int pname, IntBuffer params) {
		nglGetConvolutionParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetConvolutionParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, Buffer column) {
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, getOffset(row), column, getOffset(column));
	}
	private static native void nglSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, int row_offset, Buffer column, int column_offset);
	public static void glGetSeparableFilter(int target, int format, int type, Buffer row, Buffer column, Buffer span) {
		nglGetSeparableFilter(target, format, type, row, getOffset(row), column, getOffset(column), span, getOffset(span));
	}
	private static native void nglGetSeparableFilter(int target, int format, int type, Buffer row, int row_offset, Buffer column, int column_offset, Buffer span, int span_offset);
	public static void glDrawRangeElements(int mode, int start, int end, int count, int type, ByteBuffer indices) {
		nglDrawRangeElements(mode, start, end, count, type, indices, indices.position());
	}
	public static void glDrawRangeElements(int mode, int start, int end, int count, int type, ShortBuffer indices) {
		nglDrawRangeElements(mode, start, end, count, type, indices, indices.position() << 1);
	}
	public static void glDrawRangeElements(int mode, int start, int end, int count, int type, IntBuffer indices) {
		nglDrawRangeElements(mode, start, end, count, type, indices, indices.position() << 2);
	}
	private static native void nglDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices, int indices_offset);
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position());
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, Buffer pixels, int pixels_offset);
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position());
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, Buffer pixels, int pixels_offset);
	public static native void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);
}


