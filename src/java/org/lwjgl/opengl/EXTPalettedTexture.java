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

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

import java.nio.*;

public final class EXTPalettedTexture {

	/*
	 * Accepted by the internalformat parameter of TexImage1D, TexImage2D and
	 * TexImage3DEXT:
	 */
	public static final int GL_COLOR_INDEX1_EXT = 0x80E2;
	public static final int GL_COLOR_INDEX2_EXT = 0x80E3;
	public static final int GL_COLOR_INDEX4_EXT = 0x80E4;
	public static final int GL_COLOR_INDEX8_EXT = 0x80E5;
	public static final int GL_COLOR_INDEX12_EXT = 0x80E6;
	public static final int GL_COLOR_INDEX16_EXT = 0x80E7;

	/*
	 * Accepted by the pname parameter of GetColorTableParameterivEXT and
	 * GetColorTableParameterfvEXT:
	 */
	public static final int GL_COLOR_TABLE_FORMAT_EXT = 0x80D8;
	public static final int GL_COLOR_TABLE_WIDTH_EXT = 0x80D9;
	public static final int GL_COLOR_TABLE_RED_SIZE_EXT = 0x80DA;
	public static final int GL_COLOR_TABLE_GREEN_SIZE_EXT = 0x80DB;
	public static final int GL_COLOR_TABLE_BLUE_SIZE_EXT = 0x80DC;
	public static final int GL_COLOR_TABLE_ALPHA_SIZE_EXT = 0x80DD;
	public static final int GL_COLOR_TABLE_LUMINANCE_SIZE_EXT = 0x80DE;
	public static final int GL_COLOR_TABLE_INTENSITY_SIZE_EXT = 0x80DF;

	/*
	 * Accepted by the value parameter of GetTexLevelParameter{if}v:
	 */
	public static final int GL_TEXTURE_INDEX_SIZE_EXT = 0x80ED;

	private EXTPalettedTexture() {
	}

	static native void initNativeStubs() throws LWJGLException;

	// ---------------------------
	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, ByteBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1));
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position());
	}

	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, ShortBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1) >> 1);
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position() << 1);
	}

	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, IntBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1) >> 2);
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position() << 2);
	}

	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1) >> 2);
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position() << 2);
	}

	private static native void nglColorTableEXT(int target, int internalFormat, int width, int format, int type,
	                                            Buffer data, int dataOffset);
	// ---------------------------

	// ---------------------------
	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, ByteBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, count, 1, 1));
		nglColorSubTableEXT(target, start, count, format, type, data, data.position());
	}

	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, ShortBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, count, 1, 1) << 1);
		nglColorSubTableEXT(target, start, count, format, type, data, data.position() << 1);
	}

	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, IntBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, count, 1, 1) << 2);
		nglColorSubTableEXT(target, start, count, format, type, data, data.position() << 2);
	}

	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, FloatBuffer data) {
		BufferChecks.checkBuffer(data, GLBufferChecks.calculateImageStorage(format, type, count, 1, 1) << 2);
		nglColorSubTableEXT(target, start, count, format, type, data, data.position() << 2);
	}

	private static native void nglColorSubTableEXT(int target, int start, int count, int format, int type,
	                                               Buffer data, int dataOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetColorTableEXT(int target, int format, int type, ByteBuffer data) {
		nglGetColorTableEXT(target, format, type, data, data.position());
	}

	public static void glGetColorTableEXT(int target, int format, int type, ShortBuffer data) {
		nglGetColorTableEXT(target, format, type, data, data.position() << 1);
	}

	public static void glGetColorTableEXT(int target, int format, int type, IntBuffer data) {
		nglGetColorTableEXT(target, format, type, data, data.position() << 2);
	}

	public static void glGetColorTableEXT(int target, int format, int type, FloatBuffer data) {
		nglGetColorTableEXT(target, format, type, data, data.position() << 2);
	}

	private static native void nglGetColorTableEXT(int target, int format, int type, Buffer data, int dataOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetColorTableParameterivEXT(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetColorTableParameterivEXT(target, pname, params, params.position());
	}

	private static native void nglGetColorTableParameterivEXT(int target, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetColorTableParameterfvEXT(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetColorTableParameterfvEXT(target, pname, params, params.position());
	}

	private static native void nglGetColorTableParameterfvEXT(int target, int pname, FloatBuffer params, int paramsOffset);
	// ---------------------------

}