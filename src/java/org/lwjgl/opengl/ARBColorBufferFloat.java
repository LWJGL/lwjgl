/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBColorBufferFloat {
	public static final int GLX_RGBA_FLOAT_BIT = 0x4;
	public static final int GLX_RGBA_FLOAT_TYPE = 0x20b9;
	public static final int WGL_TYPE_RGBA_FLOAT_ARB = 0x21a0;
	public static final int FIXED_ONLY_ARB = 0x891d;
	public static final int CLAMP_READ_COLOR_ARB = 0x891c;
	public static final int CLAMP_FRAGMENT_COLOR_ARB = 0x891b;
	public static final int CLAMP_VERTEX_COLOR_ARB = 0x891a;
	public static final int RGBA_FLOAT_MODE_ARB = 0x8820;

	private ARBColorBufferFloat() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glClampColorARB(int target, int clamp);
}
