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

public final class ARBVertexProgram extends ARBProgram {

	/*
	* Accepted by the <cap> parameter of Disable, Enable, and IsEnabled, by the
	* <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev,
	* and by the <target> parameter of ProgramStringARB, BindProgramARB,
	* ProgramEnvParameter4[df][v]ARB, ProgramLocalParameter4[df][v]ARB,
	* GetProgramEnvParameter[df]vARB, GetProgramLocalParameter[df]vARB,
	* GetProgramivARB, and GetProgramStringARB.
	*/
	public static final int GL_VERTEX_PROGRAM_ARB = 0x8620;

	/*
	* Accepted by the <cap> parameter of Disable, Enable, and IsEnabled, and by
	* the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	* GetDoublev:
	*/
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 0x8642;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 0x8643;
	public static final int GL_COLOR_SUM_ARB = 0x8458;

	/*
	* Accepted by the <pname> parameter of GetVertexAttrib[dfi]vARB:
	*/
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 0x8622;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 0x886A;
	public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 0x8626;

	/*
	* Accepted by the <pname> parameter of GetVertexAttribPointervARB:
	*/
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 0x8645;

	/*
	* Accepted by the <pname> parameter of GetProgramivARB:
	*/
	public static final int GL_PROGRAM_ADDRESS_REGISTERS_ARB = 0x88B0;
	public static final int GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB = 0x88B1;
	public static final int GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 0x88B2;
	public static final int GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 0x88B3;

	/*
	* Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	* GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 0x8869;

	public static native void glVertexAttrib1sARB(int index, short x);

	public static native void glVertexAttrib1fARB(int index, float x);

	public static native void glVertexAttrib2sARB(int index, short x, short y);

	public static native void glVertexAttrib2fARB(int index, float x, float y);

	public static native void glVertexAttrib3sARB(int index, short x, short y, short z);

	public static native void glVertexAttrib3fARB(int index, float x, float y, float z);

	public static native void glVertexAttrib4sARB(int index, short x, short y, short z, short w);

	public static native void glVertexAttrib4fARB(int index, float x, float y, float z, float w);

	public static native void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w);

	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position());
	}

	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1);
	}

	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointerARB(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2);
	}

	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2);
	}

	private static native void nglVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int bufferOffset);

	// ---------------------------
	public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, int bufferOffset) {
		BufferChecks.ensureArrayVBOenabled();
		nglVertexAttribPointerARBVBO(index, size, type, normalized, stride, bufferOffset);
	}

	private static native void nglVertexAttribPointerARBVBO(int index, int size, int type, boolean normalized, int stride, int bufferOffset);
	// ---------------------------

	public static native void glEnableVertexAttribArrayARB(int index);

	public static native void glDisableVertexAttribArrayARB(int index);

	// ---------------------------
	public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetVertexAttribfvARB(index, pname, params, params.position());
	}

	private static native void nglGetVertexAttribfvARB(int index, int pname, FloatBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetVertexAttribivARB(index, pname, params, params.position());
	}

	private static native void nglGetVertexAttribivARB(int index, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	public static native ByteBuffer glGetVertexAttribPointerARB(int index, int pname, int size);

}
