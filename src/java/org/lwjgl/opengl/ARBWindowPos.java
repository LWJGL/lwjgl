/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBWindowPos {

	private ARBWindowPos() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glWindowPos3sARB(short x, short y, short z) {
		long function_pointer = GLContext.getCapabilities().ARB_window_pos_glWindowPos3sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos3sARB(x, y, z, function_pointer);
	}
	private static native void nglWindowPos3sARB(short x, short y, short z, long function_pointer);

	public static void glWindowPos3iARB(int x, int y, int z) {
		long function_pointer = GLContext.getCapabilities().ARB_window_pos_glWindowPos3iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos3iARB(x, y, z, function_pointer);
	}
	private static native void nglWindowPos3iARB(int x, int y, int z, long function_pointer);

	public static void glWindowPos3fARB(float x, float y, float z) {
		long function_pointer = GLContext.getCapabilities().ARB_window_pos_glWindowPos3fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos3fARB(x, y, z, function_pointer);
	}
	private static native void nglWindowPos3fARB(float x, float y, float z, long function_pointer);

	public static void glWindowPos2sARB(short x, short y) {
		long function_pointer = GLContext.getCapabilities().ARB_window_pos_glWindowPos2sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos2sARB(x, y, function_pointer);
	}
	private static native void nglWindowPos2sARB(short x, short y, long function_pointer);

	public static void glWindowPos2iARB(int x, int y) {
		long function_pointer = GLContext.getCapabilities().ARB_window_pos_glWindowPos2iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos2iARB(x, y, function_pointer);
	}
	private static native void nglWindowPos2iARB(int x, int y, long function_pointer);

	public static void glWindowPos2fARB(float x, float y) {
		long function_pointer = GLContext.getCapabilities().ARB_window_pos_glWindowPos2fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos2fARB(x, y, function_pointer);
	}
	private static native void nglWindowPos2fARB(float x, float y, long function_pointer);
}
