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

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.Buffer;

/**
 * $Id: CoreGL.java,v 1.23 2003/07/23 14:51:19 elias_naur Exp $
 *
 * The core OpenGL1.3 API.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision: 1.23 $
 */
public class CoreGL13 extends CoreGL12 implements CoreGL13Constants {
	public static native void glActiveTexture(int texture);
	public static native void glClientActiveTexture(int texture);
	public static native void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, Buffer data);
	public static native void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data);
	public static native void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, Buffer data);
	public static native void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, Buffer data);
	public static native void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data);
	public static native void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, Buffer data);
	public static native void glGetCompressedTexImage(int target, int lod, Buffer img);
	public static native void glMultiTexCoord1d(int target, double s);
	public static native void glMultiTexCoord1f(int target, float s);
	public static native void glMultiTexCoord1i(int target, int s);
	public static native void glMultiTexCoord1s(int target, short s);
	public static native void glMultiTexCoord2d(int target, double s, double t);
	public static native void glMultiTexCoord2f(int target, float s, float t);
	public static native void glMultiTexCoord2i(int target, int s, int t);
	public static native void glMultiTexCoord2s(int target, short s, short t);
	public static native void glMultiTexCoord3d(int target, double s, double t, double r);
	public static native void glMultiTexCoord3f(int target, float s, float t, float r);
	public static native void glMultiTexCoord3i(int target, int s, int t, int r);
	public static native void glMultiTexCoord3s(int target, short s, short t, short r);
	public static native void glMultiTexCoord4d(int target, double s, double t, double r, double q);
	public static native void glMultiTexCoord4f(int target, float s, float t, float r, float q);
	public static native void glMultiTexCoord4i(int target, int s, int t, int r, int q);
	public static native void glMultiTexCoord4s(int target, short s, short t, short r, short q);
	public static native void glLoadTransposeMatrixd(DoubleBuffer m);
	public static native void glLoadTransposeMatrixf(FloatBuffer m);
	public static native void glMultTransposeMatrixd(DoubleBuffer m);
	public static native void glMultTransposeMatrixf(FloatBuffer m);
	public static native void glSampleCoverage(float value, boolean invert);
}


