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

public final class NVPixelDataRange {

	/*
	* Accepted by the <target> parameter of PixelDataRangeNV and
	* FlushPixelDataRangeNV, and by the <cap> parameter of
	* EnableClientState, DisableClientState, and IsEnabled:
	*/
	public static final int GL_WRITE_PIXEL_DATA_RANGE_NV = 0x8878;
	public static final int GL_READ_PIXEL_DATA_RANGE_NV = 0x8879;

	/*
	* Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	* GetFloatv, and GetDoublev:
	*/
	public static final int GL_WRITE_PIXEL_DATA_RANGE_LENGTH_NV = 0x887A;
	public static final int GL_READ_PIXEL_DATA_RANGE_LENGTH_NV = 0x887B;

	/*
	* Accepted by the <pname> parameter of GetPointerv:
	*/
	public static final int GL_WRITE_PIXEL_DATA_RANGE_POINTER_NV = 0x887C;
	public static final int GL_READ_PIXEL_DATA_RANGE_POINTER_NV = 0x887D;

	static native void initNativeStubs() throws LWJGLException;

	// ---------------------------
	public static void glPixelDataRangeNV(int target, ByteBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, data.remaining(), data, data.position());
	}

	public static void glPixelDataRangeNV(int target, ShortBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, data.remaining() << 1, data, data.position() << 1);
	}

	public static void glPixelDataRangeNV(int target, IntBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, data.remaining() << 2, data, data.position() << 2);
	}

	public static void glPixelDataRangeNV(int target, FloatBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, data.remaining() << 2, data, data.position() << 2);
	}

	private static native void nglPixelDataRangeNV(int target, int length, Buffer data,
	                                               int dataOffset);
	// ---------------------------

	public static native void glFlushPixelDataRangeNV(int target);

}
