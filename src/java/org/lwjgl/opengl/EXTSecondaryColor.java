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

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

public final class EXTSecondaryColor {
	public static final int GL_COLOR_SUM_EXT                                        = 0x8458;
	public static final int GL_CURRENT_SECONDARY_COLOR_EXT                          = 0x8459;
	public static final int GL_SECONDARY_COLOR_ARRAY_SIZE_EXT                       = 0x845A;
	public static final int GL_SECONDARY_COLOR_ARRAY_TYPE_EXT                       = 0x845B;
	public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE_EXT                     = 0x845C;
	public static final int GL_SECONDARY_COLOR_ARRAY_POINTER_EXT                    = 0x845D;
	public static final int GL_SECONDARY_COLOR_ARRAY_EXT                            = 0x845E;
	
	private EXTSecondaryColor() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glSecondaryColor3bEXT(byte red, byte green, byte blue);

	public static native void glSecondaryColor3fEXT(float red, float green, float blue);

	public static native void glSecondaryColor3ubEXT(byte red, byte green, byte blue);

	public static void glSecondaryColorPointerEXT(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglSecondaryColorPointerEXT(size, unsigned ? GL11.GL_UNSIGNED_BYTE: GL11.GL_BYTE, stride, pPointer, pPointer.position());
	}
	public static void glSecondaryColorPointerEXT(int size, int stride, FloatBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglSecondaryColorPointerEXT(size, GL11.GL_FLOAT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglSecondaryColorPointerEXT(int size, int type, int stride, Buffer pPointer, int pPointer_offset);

	public static void glSecondaryColorPointerEXT(int size, int type, int stride, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglSecondaryColorPointerEXTVBO(size, type, stride, buffer_offset);
	}
	private static native void nglSecondaryColorPointerEXTVBO(int size, int type, int stride, int buffer_offset);
}
