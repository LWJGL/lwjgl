/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVPointSprite {
	public static final int GL_POINT_SPRITE_R_MODE_NV = 0x8863;
	public static final int GL_COORD_REPLACE_NV = 0x8862;
	public static final int GL_POINT_SPRITE_NV = 0x8861;

	private NVPointSprite() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glPointParameterNV(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglPointParameterivNV(pname, params, params.position());
	}
	private static native void nglPointParameterivNV(int pname, IntBuffer params, int params_position);

	public static native void glPointParameteriNV(int pname, int param);
}
