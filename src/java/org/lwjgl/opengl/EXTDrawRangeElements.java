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
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


public final class EXTDrawRangeElements {
	public static final int GL_MAX_ELEMENTS_VERTICES_EXT                            = 0x80E8;
	public static final int GL_MAX_ELEMENTS_INDICES_EXT                             = 0x80E9;

	static native void initNativeStubs();

	public static void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices) {
		BufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), GL11.GL_UNSIGNED_BYTE, pIndices, pIndices.position());
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) {
		BufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), GL11.GL_UNSIGNED_SHORT, pIndices, pIndices.position()<<1);
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) {
		BufferChecks.ensureElementVBOdisabled();
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), GL11.GL_UNSIGNED_INT, pIndices, pIndices.position()<<2);
	}
	private static native void nglDrawRangeElementsEXT(int mode, int start, int end, int count, int type, Buffer pIndices, int pIndices_offset);

	public static void glDrawRangeElementsEXT(int mode, int start, int end, int count, int type, int buffer_offset) {
		BufferChecks.ensureElementVBOenabled();
		nglDrawRangeElementsEXTVBO(mode, start, end, count, type, buffer_offset);
	}
	private static native void nglDrawRangeElementsEXTVBO(int mode, int start, int end, int count, int type, int buffer_offset);
}
