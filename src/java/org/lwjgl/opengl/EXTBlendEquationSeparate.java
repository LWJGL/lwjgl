/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTBlendEquationSeparate {
	public static final int GL_BLEND_EQUATION_ALPHA_EXT = 0x883d;
	public static final int GL_BLEND_EQUATION_RGB_EXT = 0x8009;

	private EXTBlendEquationSeparate() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha);
}
