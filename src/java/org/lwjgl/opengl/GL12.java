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
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

/**
 * $Id$
 *
 * The core OpenGL1.2.1 API, with the imaging subset.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

public final class GL12 {
	/* Error codes */
	public static final int GL_TABLE_TOO_LARGE                = 0x8031;

	/* Enums */
	public static final int GL_PACK_SKIP_IMAGES               = 0x806B;
	public static final int GL_PACK_IMAGE_HEIGHT              = 0x806C;
	public static final int GL_UNPACK_SKIP_IMAGES             = 0x806D;
	public static final int GL_UNPACK_IMAGE_HEIGHT            = 0x806E;
	public static final int GL_TEXTURE_3D                     = 0x806F;
	public static final int GL_PROXY_TEXTURE_3D               = 0x8070;
	public static final int GL_TEXTURE_DEPTH                  = 0x8071;
	public static final int GL_TEXTURE_WRAP_R                 = 0x8072;
	public static final int GL_MAX_3D_TEXTURE_SIZE            = 0x8073;
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
	public static final int GL_RESCALE_NORMAL                 = 0x803A;
	public static final int GL_LIGHT_MODEL_COLOR_CONTROL      = 0x81F8;
	public static final int GL_SINGLE_COLOR                   = 0x81F9;
	public static final int GL_SEPARATE_SPECULAR_COLOR        = 0x81FA;
	public static final int GL_CLAMP_TO_EDGE                  = 0x812F;
	public static final int GL_TEXTURE_MIN_LOD                = 0x813A;
	public static final int GL_TEXTURE_MAX_LOD                = 0x813B;
	public static final int GL_TEXTURE_BASE_LEVEL             = 0x813C;
	public static final int GL_TEXTURE_MAX_LEVEL              = 0x813D;
	public static final int GL_MAX_ELEMENTS_VERTICES          = 0x80E8;
	public static final int GL_MAX_ELEMENTS_INDICES           = 0x80E9;
	public static final int GL_ALIASED_POINT_SIZE_RANGE       = 0x846D;
	public static final int GL_ALIASED_LINE_WIDTH_RANGE       = 0x846E;

	private GL12() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices) {
		BufferChecks.checkDirect(indices);
		GLBufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElements(mode, start, end, indices.remaining(), GL11.GL_UNSIGNED_BYTE, indices, indices.position());
	}
	public static void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) {
		BufferChecks.checkDirect(indices);
		GLBufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElements(mode, start, end, indices.remaining(), GL11.GL_UNSIGNED_SHORT, indices, indices.position() << 1);
	}
	public static void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) {
		BufferChecks.checkDirect(indices);
		GLBufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElements(mode, start, end, indices.remaining(), GL11.GL_UNSIGNED_INT, indices, indices.position() << 2);
	}
	private static native void nglDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices, int indices_offset);
	public static void glDrawRangeElements(int mode, int start, int end, int count, int type, int buffer_offset) {
		GLBufferChecks.ensureElementVBOenabled();
		nglDrawRangeElementsVBO(mode, start, end, count, type, buffer_offset);
	}
	private static native void nglDrawRangeElementsVBO(int mode, int start, int end, int count, int type, int buffer_offset);
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth));
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position());
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth)>>1);
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth)>>2);
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth)>>2);
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, Buffer pixels, int pixels_offset);
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth));
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position());
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth)>>1);
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth)>>2);
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, depth)>>2);
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, Buffer pixels, int pixels_offset);
	public static native void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);
}

