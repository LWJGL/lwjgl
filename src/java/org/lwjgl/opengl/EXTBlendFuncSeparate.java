/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTBlendFuncSeparate {
	public static final int GL_BLEND_SRC_ALPHA_EXT = 0x80cb;
	public static final int GL_BLEND_DST_ALPHA_EXT = 0x80ca;
	public static final int GL_BLEND_SRC_RGB_EXT = 0x80c9;
	public static final int GL_BLEND_DST_RGB_EXT = 0x80c8;

	private EXTBlendFuncSeparate() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);
}
