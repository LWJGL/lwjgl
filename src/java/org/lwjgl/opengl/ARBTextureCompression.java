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

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

public final class ARBTextureCompression {
	public static final int GL_COMPRESSED_ALPHA_ARB                                 = 0x84E9;
	public static final int GL_COMPRESSED_LUMINANCE_ARB                             = 0x84EA;
	public static final int GL_COMPRESSED_LUMINANCE_ALPHA_ARB                       = 0x84EB;
	public static final int GL_COMPRESSED_INTENSITY_ARB                             = 0x84EC;
	public static final int GL_COMPRESSED_RGB_ARB                                   = 0x84ED;
	public static final int GL_COMPRESSED_RGBA_ARB                                  = 0x84EE;
	public static final int GL_TEXTURE_COMPRESSION_HINT_ARB                         = 0x84EF;
	public static final int GL_TEXTURE_IMAGE_SIZE_ARB                               = 0x86A0;
	public static final int GL_TEXTURE_COMPRESSED_ARB                               = 0x86A1;
	public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS_ARB                   = 0x86A2;
	public static final int GL_COMPRESSED_TEXTURE_FORMATS_ARB                       = 0x86A3;

	private ARBTextureCompression() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ByteBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ShortBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, IntBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, FloatBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ByteBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ShortBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, IntBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, FloatBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, Buffer pData, int pData_offset);
	public static void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg) {
		BufferChecks.checkDirect(pImg);
		nglGetCompressedTexImageARB(target, lod, pImg, pImg.position());
	}
	public static void glGetCompressedTexImageARB(int target, int lod, ShortBuffer pImg) {
		BufferChecks.checkDirect(pImg);
		nglGetCompressedTexImageARB(target, lod, pImg, pImg.position()<<1);
	}
	public static void glGetCompressedTexImageARB(int target, int lod, IntBuffer pImg) {
		BufferChecks.checkDirect(pImg);
		nglGetCompressedTexImageARB(target, lod, pImg, pImg.position()<<2);
	}
	private static native void nglGetCompressedTexImageARB(int target, int lod, Buffer pImg, int pImg_offset);
}
