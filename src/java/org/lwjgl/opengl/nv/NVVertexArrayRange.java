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
/*
 * Created by IntelliJ IDEA.
 * User: nj
 * Date: 12-08-2002
 * Time: 15:18:16
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.nv;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class NVVertexArrayRange {
	public static final int GL_VERTEX_ARRAY_RANGE_NV                                = 0x851D;
	public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_NV                         = 0x851E;
	public static final int GL_VERTEX_ARRAY_RANGE_VALID_NV                          = 0x851F;
	public static final int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV                    = 0x8520;
	public static final int GL_VERTEX_ARRAY_RANGE_POINTER_NV                        = 0x8521;

	public static void glVertexArrayRangeNV(ByteBuffer pPointer) {
		nglVertexArrayRangeNV(pPointer.remaining(), pPointer, pPointer.position());
	}
	private static native void nglVertexArrayRangeNV(int size, Buffer pPointer, int pPointer_offset);
	public static native void glFlushVertexArrayRangeNV();

	public static native ByteBuffer glXAllocateMemoryNV(
		int size,
		float readFrequency,
		float writeFrequency,
		float priority);

	private static native void glXFreeMemoryNV(ByteBuffer pointer);

	// #ifdef _WIN32

	public static native ByteBuffer wglAllocateMemoryNV(
		int size,
		float readFrequency,
		float writeFrequency,
		float priority);
	public static native void wglFreeMemoryNV(ByteBuffer pointer);
}
