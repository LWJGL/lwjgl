/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVHalfFloat {
	public static final int GL_HALF_FLOAT_NV = 0x140b;

	private NVHalfFloat() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glVertexAttribs4NV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs4hvNV(index, (attribs.remaining()) >> 2, attribs, attribs.position());
	}
	private static native void nglVertexAttribs4hvNV(int index, int n, ShortBuffer attribs, int attribs_position);

	public static void glVertexAttribs3NV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs3hvNV(index, (attribs.remaining()) / 3, attribs, attribs.position());
	}
	private static native void nglVertexAttribs3hvNV(int index, int n, ShortBuffer attribs, int attribs_position);

	public static void glVertexAttribs2NV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs2hvNV(index, (attribs.remaining()) >> 1, attribs, attribs.position());
	}
	private static native void nglVertexAttribs2hvNV(int index, int n, ShortBuffer attribs, int attribs_position);

	public static void glVertexAttribs1NV(int index, ShortBuffer attribs) {
		BufferChecks.checkDirect(attribs);
		nglVertexAttribs1hvNV(index, (attribs.remaining()), attribs, attribs.position());
	}
	private static native void nglVertexAttribs1hvNV(int index, int n, ShortBuffer attribs, int attribs_position);

	public static native void glVertexAttrib4hNV(int index, short x, short y, short z, short w);

	public static native void glVertexAttrib3hNV(int index, short x, short y, short z);

	public static native void glVertexAttrib2hNV(int index, short x, short y);

	public static native void glVertexAttrib1hNV(int index, short x);

	public static native void glSecondaryColor3hNV(short red, short green, short blue);

	public static native void glFogCoordhNV(short fog);

	public static native void glMultiTexCoord4hNV(int target, short s, short t, short r, short q);

	public static native void glMultiTexCoord3hNV(int target, short s, short t, short r);

	public static native void glMultiTexCoord2hNV(int target, short s, short t);

	public static native void glMultiTexCoord1hNV(int target, short s);

	public static native void glTexCoord4hNV(short s, short t, short r, short q);

	public static native void glTexCoord3hNV(short s, short t, short r);

	public static native void glTexCoord2hNV(short s, short t);

	public static native void glTexCoord1hNV(short s);

	public static native void glColor4hNV(short red, short green, short blue, short alpha);

	public static native void glColor3hNV(short red, short green, short blue);

	public static native void glNormal3hNV(short nx, short ny, short nz);

	public static native void glVertex4hNV(short x, short y, short z, short w);

	public static native void glVertex3hNV(short x, short y, short z);

	public static native void glVertex2hNV(short x, short y);
}
