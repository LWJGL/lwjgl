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

public final class ATIVertexArrayObject {
	public static final int GL_STATIC_ATI                                           = 0x8760;
	public static final int GL_DYNAMIC_ATI                                          = 0x8761;
	public static final int GL_PRESERVE_ATI                                         = 0x8762;
	public static final int GL_DISCARD_ATI                                          = 0x8763;
	public static final int GL_OBJECT_BUFFER_SIZE_ATI                               = 0x8764;
	public static final int GL_OBJECT_BUFFER_USAGE_ATI                              = 0x8765;
	public static final int GL_ARRAY_OBJECT_BUFFER_ATI                              = 0x8766;
	public static final int GL_ARRAY_OBJECT_OFFSET_ATI                              = 0x8767;
	
	static native void initNativeStubs() throws LWJGLException;

	public static int glNewObjectBufferATI(int size, ByteBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position() : 0, usage);
	}
	public static int glNewObjectBufferATI(int size, ShortBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position()<<1 : 0, usage);
	}
	public static int glNewObjectBufferATI(int size, FloatBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position()<<2 : 0, usage);
	}
	public static int glNewObjectBufferATI(int size, IntBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position()<<2 : 0, usage);
	}
	private static native int nglNewObjectBufferATI(int size, Buffer pPointer, int pPointer_offset, int usage);
	public static native boolean glIsObjectBufferATI(int buffer);

	public static void glUpdateObjectBufferATI(int buffer, int offset, ByteBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining(), pPointer, pPointer.position(), preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, ShortBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining()<<1, pPointer, pPointer.position()<<1, preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, FloatBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining()<<2, pPointer, pPointer.position()<<2, preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, IntBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining()<<2, pPointer, pPointer.position()<<2, preserve);
	}
	private static native void nglUpdateObjectBufferATI(int buffer, int offset, int size, Buffer pPointer, int pPointer_offset, int preserve);

	public static void glGetObjectBufferATI(int buffer, int pname, FloatBuffer pfParams) {
		nglGetObjectBufferfvATI(buffer, pname, pfParams, pfParams.position());
	}
	private static native void nglGetObjectBufferfvATI(int buffer, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetObjectBufferATI(int buffer, int pname, IntBuffer piParams) {
		nglGetObjectBufferivATI(buffer, pname, piParams, piParams.position());
	}
	private static native void nglGetObjectBufferivATI(int buffer, int pname, IntBuffer piParams, int piParams_offset);
	public static native void glFreeObjectBufferATI(int buffer);
	public static native void glArrayObjectATI(
		int array,
		int size,
		int type,
		int stride,
		int buffer,
		int offset);

	public static void glGetArrayObjectATI(int array, int pname, FloatBuffer pfParams) {
		BufferChecks.checkBuffer(pfParams);
		nglGetArrayObjectfvATI(array, pname, pfParams, pfParams.position());
	}
	private static native void nglGetArrayObjectfvATI(int array, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetArrayObjectATI(int array, int pname, IntBuffer piParams) {
		BufferChecks.checkBuffer(piParams);
		nglGetArrayObjectivATI(array, pname, piParams, piParams.position());
	}
	private static native void nglGetArrayObjectivATI(int array, int pname, IntBuffer piParams, int piParams_offset);

	public static native void glVariantArrayObjectATI(
		int id,
		int type,
		int stride,
		int buffer,
		int offset);

	public static void glGetVariantArrayObjectATI(int id, int pname, FloatBuffer pfParams) {
		BufferChecks.checkBuffer(pfParams);
		nglGetVariantArrayObjectfvATI(id, pname, pfParams, pfParams.position());
	}
	private static native void nglGetVariantArrayObjectfvATI(int id, int pname, FloatBuffer pfParams, int pfParams_offset_offset);

	public static void glGetVariantArrayObjectATI(int id, int pname, IntBuffer piParams) {
		BufferChecks.checkBuffer(piParams);
		nglGetVariantArrayObjectivATI(id, pname, piParams, piParams.position());
	}
	private static native void nglGetVariantArrayObjectivATI(int id, int pname, IntBuffer piParams, int piParams_offset);
}
