/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTDepthBoundsTest {
	public static final int GL_DEPTH_BOUNDS_EXT = 0x8891;
	public static final int GL_DEPTH_BOUNDS_TEST_EXT = 0x8890;

	private EXTDepthBoundsTest() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glDepthBoundsEXT(double zmin, double zmax);
}
