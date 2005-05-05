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


	public static void glDepthBoundsEXT(double zmin, double zmax) {
		long function_pointer = GLContext.getCapabilities().EXT_depth_bounds_test_glDepthBoundsEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDepthBoundsEXT(zmin, zmax, function_pointer);
	}
	private static native void nglDepthBoundsEXT(double zmin, double zmax, long function_pointer);
}
