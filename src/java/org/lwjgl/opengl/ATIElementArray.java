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

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

public final class ATIElementArray {

	public static final int GL_ELEMENT_ARRAY_ATI = 0x8768;
	public static final int GL_ELEMENT_ARRAY_TYPE_ATI = 0x8769;
	public static final int GL_ELEMENT_ARRAY_POINTER_ATI = 0x876A;

	private ATIElementArray() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glElementPointerATI(ByteBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglElementPointerATI(GL11.GL_UNSIGNED_BYTE, pPointer, pPointer.position());
	}

	public static void glElementPointerATI(ShortBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglElementPointerATI(GL11.GL_UNSIGNED_SHORT, pPointer, pPointer.position() << 1);
	}

	public static void glElementPointerATI(IntBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglElementPointerATI(GL11.GL_UNSIGNED_INT, pPointer, pPointer.position() << 2);
	}

	private static native void nglElementPointerATI(int type, Buffer pPointer, int pPointer_offset);

	public static void glElementPointerATI(int type, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglElementPointerATIVBO(type, buffer_offset);
	}

	private static native void nglElementPointerATIVBO(int type, int buffer_offset);

	public static native void glDrawElementArrayATI(int mode, int count);

	public static native void glDrawRangeElementArrayATI(int mode, int start, int end, int count);
}
