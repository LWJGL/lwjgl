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

import java.nio.ShortBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

public final class NVHalfFloat {

	/*
	 * Accepted by the <type> argument of VertexPointer, NormalPointer,
	 * ColorPointer, TexCoordPointer, FogCoordPointerEXT,
	 * SecondaryColorPointerEXT, VertexWeightPointerEXT, VertexAttribPointerNV,
	 * DrawPixels, ReadPixels, TexImage1D, TexImage2D, TexImage3D, TexSubImage1D,
	 * TexSubImage2D, TexSubImage3D, and GetTexImage:
	*/
	public static final int GL_HALF_FLOAT_NV = 0x140B;

	private NVHalfFloat() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glVertex2hNV(short x, short y);

	public static native void glVertex3hNV(short x, short y, short z);

	public static native void glVertex4hNV(short x, short y, short z, short w);

	public static native void glNormal3hNV(short nx, short ny, short nz);

	public static native void glColor3hNV(short red, short green, short blue);

	public static native void glColor4hNV(short red, short green, short blue, short alpha);

	public static native void glTexCoord1hNV(short s);

	public static native void glTexCoord2hNV(short s, short t);

	public static native void glTexCoord3hNV(short s, short t, short r);

	public static native void glTexCoord4hNV(short s, short t, short r, short q);

	public static native void glMultiTexCoord1hNV(int target, short s);

	public static native void glMultiTexCoord2hNV(int target, short s, short t);

	public static native void glMultiTexCoord3hNV(int target, short s, short t, short r);

	public static native void glMultiTexCoord4hNV(int target, short s, short t, short r, short q);

	public static native void glFogCoordhNV(short fog);

	public static native void glSecondaryColor3hNV(short red, short green, short blue);

	public static native void glVertexAttrib1hNV(int index, short x);

	public static native void glVertexAttrib2hNV(int index, short x, short y);

	public static native void glVertexAttrib3hNV(int index, short x, short y, short z);

	public static native void glVertexAttrib4hNV(int index, short x, short y, short z, short w);

	// ---------------------------
	public static void glVertexAttribs1hNV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs1hvNV(index, attribs.remaining(), attribs, attribs.position());
	}

	private static native void nglVertexAttribs1hvNV(int index, int n, ShortBuffer attribs, int attribsOffset);
	// ---------------------------

	// ---------------------------
	public static void glVertexAttribs2hNV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs2hvNV(index, attribs.remaining() >> 1, attribs, attribs.position());
	}

	private static native void nglVertexAttribs2hvNV(int index, int n, ShortBuffer attribs, int attribsOffset);
	// ---------------------------

	// ---------------------------
	public static void glVertexAttribs3hNV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs3hvNV(index, attribs.remaining() / 3, attribs, attribs.position());
	}

	private static native void nglVertexAttribs3hvNV(int index, int n, ShortBuffer attribs, int attribsOffset);
	// ---------------------------

	// ---------------------------
	public static void glVertexAttribs4hNV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs4hvNV(index, attribs.remaining() >> 2, attribs, attribs.position());
	}

	private static native void nglVertexAttribs4hvNV(int index, int n, ShortBuffer attribs, int attribsOffset);
	// ---------------------------

}
