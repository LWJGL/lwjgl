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
	public static native void glColorTable(int target, int internalFormat, int width, int format, int type, Buffer data);
	public static native void glColorSubTable(int target, int start, int count, int format, int type, Buffer data);
	public static native void glColorTableParameteriv(int target, int pname, IntBuffer params);
	public static native void glColorTableParameterfv(int target, int pname, FloatBuffer params);
	public static native void glCopyColorSubTable(int target, int start, int x, int y, int width);
	public static native void glCopyColorTable(int target, int internalformat, int x, int y, int width);
	public static native void glGetColorTable(int target, int format, int type, Buffer data);
	public static native void glGetColorTableParameteriv(int target, int pname, IntBuffer params);
	public static native void glGetColorTableParameterfv(int target, int pname, FloatBuffer params);
	public static native void glBlendEquation(int mode);
	public static native void glBlendColor(float red, float green, float blue, float alpha);
	public static native void glHistogram(int target, int width, int internalformat, boolean sink);
	public static native void glResetHistogram(int target);
	public static native void glGetHistogram(int target, boolean reset, int format, int type, Buffer values);
	public static native void glGetHistogramParameterfv(int target, int pname, FloatBuffer params);
	public static native void glGetHistogramParameteriv(int target, int pname, IntBuffer params);
	public static native void glMinmax(int target, int internalformat, boolean sink);
	public static native void glResetMinmax(int target);
	public static native void glGetMinmax(int target, boolean reset, int format, int types, Buffer values);
	public static native void glGetMinmaxParameterfv(int target, int pname, FloatBuffer params);
	public static native void glGetMinmaxParameteriv(int target, int pname, IntBuffer params);
	public static native void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, Buffer image);
	public static native void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer image);
	public static native void glConvolutionParameterf(int target, int pname, float params);
	public static native void glConvolutionParameterfv(int target, int pname, FloatBuffer params);
	public static native void glConvolutionParameteri(int target, int pname, int params);
	public static native void glConvolutionParameteriv(int target, int pname, IntBuffer params);
	public static native void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width);
	public static native void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height);
	public static native void glGetConvolutionFilter(int target, int format, int type, Buffer image);
	public static native void glGetConvolutionParameterfv(int target, int pname, FloatBuffer params);
	public static native void glGetConvolutionParameteriv(int target, int pname, IntBuffer params);
	public static native void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, Buffer column);
	public static native void glGetSeparableFilter(int target, int format, int type, Buffer row, Buffer column, Buffer span);
	public static native void glDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices);
	public static native void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, Buffer pixels);
	public static native void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, Buffer pixels);
	public static native void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);
}


