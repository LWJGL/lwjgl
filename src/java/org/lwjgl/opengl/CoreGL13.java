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

/**
 * $Id: CoreGL.java,v 1.23 2003/07/23 14:51:19 elias_naur Exp $
 *
 * The core OpenGL1.3 API.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision: 1.23 $
 */
public abstract class CoreGL13 extends CoreGL12 implements CoreGL13Constants {
	public static native void glActiveTexture(int texture);
	public static native void glClientActiveTexture(int texture);
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer data) {
		nglCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data, data.position());
	}
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer data) {
		nglCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data, data.position() << 1);
	}
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer data) {
		nglCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data, data.position() << 2);
	}
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer data) {
		nglCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data, data.position() << 2);
	}
	private static native void nglCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, Buffer data, int data_offset);
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer data) {
		nglCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data, data.position());
	}
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer data) {
		nglCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data, data.position() << 1);
	}
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer data) {
		nglCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data, data.position() << 2);
	}
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer data) {
		nglCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data, data.position() << 2);
	}
	private static native void nglCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data, int data_offset);
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer data) {
		nglCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data, data.position());
	}
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer data) {
		nglCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data, data.position() << 1);
	}
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer data) {
		nglCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data, data.position() << 2);
	}
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer data) {
		nglCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data, data.position() << 2);
	}
	private static native void nglCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, Buffer data, int data_offset);
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ByteBuffer data) {
		nglCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data, data.position());
	}
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ShortBuffer data) {
		nglCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data, data.position() << 1);
	}
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, IntBuffer data) {
		nglCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data, data.position() << 2);
	}
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, FloatBuffer data) {
		nglCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data, data.position() << 2);
	}
	private static native void nglCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, Buffer data, int data_offset);
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ByteBuffer data) {
		nglCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data, data.position());
	}
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ShortBuffer data) {
		nglCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data, data.position() << 1);
	}
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, IntBuffer data) {
		nglCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data, data.position() << 2);
	}
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, FloatBuffer data) {
		nglCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data, data.position() << 2);
	}
	private static native void nglCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data, int data_offset);
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ByteBuffer data) {
		nglCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data, data.position());
	}
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ShortBuffer data) {
		nglCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data, data.position() << 1);
	}
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, IntBuffer data) {
		nglCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data, data.position() << 2);
	}
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, FloatBuffer data) {
		nglCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data, data.position() << 2);
	}
	private static native void nglCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, Buffer data, int data_offset);
	public static void glGetCompressedTexImage(int target, int lod, ByteBuffer img) {
		nglGetCompressedTexImage(target, lod, img, img.position());
	}
	public static void glGetCompressedTexImage(int target, int lod, ShortBuffer img) {
		nglGetCompressedTexImage(target, lod, img, img.position() << 1);
	}
	public static void glGetCompressedTexImage(int target, int lod, IntBuffer img) {
		nglGetCompressedTexImage(target, lod, img, img.position() << 2);
	}
	private static native void nglGetCompressedTexImage(int target, int lod, Buffer img, int img_offset);
	public static native void glMultiTexCoord1f(int target, float s);
	public static native void glMultiTexCoord2f(int target, float s, float t);
	public static native void glMultiTexCoord3f(int target, float s, float t, float r);
	public static native void glMultiTexCoord4f(int target, float s, float t, float r, float q);
	public static void glLoadTransposeMatrix(FloatBuffer m) {
		nglLoadTransposeMatrixf(m, m.position());
	}
	private static native void nglLoadTransposeMatrixf(FloatBuffer m, int m_offset);
	public static void glMultTransposeMatrix(FloatBuffer m) {
		nglMultTransposeMatrixf(m, m.position());
	}
	private static native void nglMultTransposeMatrixf(FloatBuffer m, int m_offset);
	public static native void glSampleCoverage(float value, boolean invert);
}


