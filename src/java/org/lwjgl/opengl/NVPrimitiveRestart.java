/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVPrimitiveRestart {
	public static final int GL_PRIMITIVE_RESTART_INDEX_NV = 0x8559;
	public static final int GL_PRIMITIVE_RESTART_NV = 0x8558;

	private NVPrimitiveRestart() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glPrimitiveRestartIndexNV(int index);

	public static native void glPrimitiveRestartNV();
}
