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


public final class ARBMatrixPalette {
	public static final int GL_MATRIX_PALETTE_ARB                                   = 0x8840;
	public static final int GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB                   = 0x8841;
	public static final int GL_MAX_PALETTE_MATRICES_ARB                             = 0x8842;
	public static final int GL_CURRENT_PALETTE_MATRIX_ARB                           = 0x8843;
	public static final int GL_MATRIX_INDEX_ARRAY_ARB                               = 0x8844;
	public static final int GL_CURRENT_MATRIX_INDEX_ARB                             = 0x8845;
	public static final int GL_MATRIX_INDEX_ARRAY_SIZE_ARB                          = 0x8846;
	public static final int GL_MATRIX_INDEX_ARRAY_TYPE_ARB                          = 0x8847;
	public static final int GL_MATRIX_INDEX_ARRAY_STRIDE_ARB                        = 0x8848;
	public static final int GL_MATRIX_INDEX_ARRAY_POINTER_ARB                       = 0x8849;

	static native void initNativeStubs();

	public static native void glCurrentPaletteMatrixARB(int index);
	public static void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_BYTE, stride, pPointer, pPointer.position());
	}
	public static void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_SHORT, stride, pPointer, pPointer.position()<<1);
	}
	public static void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglMatrixIndexPointerARB(size, GL11.GL_UNSIGNED_INT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglMatrixIndexPointerARB(int size, int type, int stride, Buffer pPointer, int pPointer_offset);
	public static void glMatrixIndexPointerARB(int size, int type, int stride, int buffer_offset) {
		BufferChecks.ensureArrayVBOenabled();
		nglMatrixIndexPointerARBVBO(size, type, stride, buffer_offset);
	}
	private static native void nglMatrixIndexPointerARBVBO(int size, int type, int stride, int buffer_offset);

	public static void glMatrixIndexuARB(ByteBuffer pIndices) {
		nglMatrixIndexubvARB(pIndices.remaining(), pIndices, pIndices.position());
	}
	private static native void nglMatrixIndexubvARB(int size, ByteBuffer pIndices, int pIndices_offset);

	public static void glMatrixIndexuARB(IntBuffer piIndices) {
		nglMatrixIndexuivARB(piIndices.remaining(), piIndices, piIndices.position());
	}
	private static native void nglMatrixIndexuivARB(int size, IntBuffer piIndices, int piIndices_offset);

	public static void glMatrixIndexuARB(ShortBuffer psIndices) {
		nglMatrixIndexusvARB(psIndices.remaining(), psIndices, psIndices.position());
	}
	private static native void nglMatrixIndexusvARB(int size, ShortBuffer psIndices, int psIndices_offset);
}
