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

public final class NVFloatBuffer {

	/*
	 * Accepted by the <internalformat> parameter of TexImage2D and
	 * CopyTexImage2D:
	*/
	public static final int GL_FLOAT_R_NV = 0x8880;
	public static final int GL_FLOAT_RG_NV = 0x8881;
	public static final int GL_FLOAT_RGB_NV = 0x8882;
	public static final int GL_FLOAT_RGBA_NV = 0x8883;
	public static final int GL_FLOAT_R16_NV = 0x8884;
	public static final int GL_FLOAT_R32_NV = 0x8885;
	public static final int GL_FLOAT_RG16_NV = 0x8886;
	public static final int GL_FLOAT_RG32_NV = 0x8887;
	public static final int GL_FLOAT_RGB16_NV = 0x8888;
	public static final int GL_FLOAT_RGB32_NV = 0x8889;
	public static final int GL_FLOAT_RGBA16_NV = 0x888A;
	public static final int GL_FLOAT_RGBA32_NV = 0x888B;

	/*
	* Accepted by the <pname> parameter of GetTexLevelParameterfv and
	* GetTexLevelParameteriv:
	*/
	public static final int GL_TEXTURE_FLOAT_COMPONENTS_NV = 0x888C;

	/*
	* Accepted by the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv,
	* and GetDoublev:
	*/
	public static final int GL_FLOAT_CLEAR_COLOR_VALUE_NV = 0x888D;
	public static final int GL_FLOAT_RGBA_MODE_NV = 0x888E;

	/*
	* Accepted in the <piAttributes> array of wglGetPixelFormatAttribivARB and
	* wglGetPixelFormatAttribfvARB and in the <piAttribIList> and
	* <pfAttribFList> arrays of wglChoosePixelFormatARB:
	*/
	public static final int GL_WGL_FLOAT_COMPONENTS_NV = 0x20B0;
	public static final int GL_WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_R_NV = 0x20B1;
	public static final int GL_WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_RG_NV = 0x20B2;
	public static final int GL_WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_RGB_NV = 0x20B3;
	public static final int GL_WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_RGBA_NV = 0x20B4;

	/*
	* Accepted in the <piAttribIList> array of wglCreatePbufferARB and returned
	* in the <value> parameter of wglQueryPbufferARB when <iAttribute> is
	* WGL_TEXTURE_FORMAT_ARB:
	*/
	public static final int GL_WGL_TEXTURE_FLOAT_R_NV = 0x20B5;
	public static final int GL_WGL_TEXTURE_FLOAT_RG_NV = 0x20B6;
	public static final int GL_WGL_TEXTURE_FLOAT_RGB_NV = 0x20B7;
	public static final int GL_WGL_TEXTURE_FLOAT_RGBA_NV = 0x20B8;

}