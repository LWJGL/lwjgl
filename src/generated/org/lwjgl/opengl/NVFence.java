/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVFence {
	public static final int GL_ALL_COMPLETED_NV = 0x84f2;
	public static final int GL_FENCE_STATUS_NV = 0x84f3;
	public static final int GL_FENCE_CONDITION_NV = 0x84f4;

	private NVFence() {
	}


	public static void glGenFencesNV(IntBuffer piFences) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_fence_glGenFencesNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(piFences);
		nglGenFencesNV((piFences.remaining()), piFences, piFences.position(), function_pointer);
	}
	private static native void nglGenFencesNV(int n, IntBuffer piFences, int piFences_position, long function_pointer);

	public static void glDeleteFencesNV(IntBuffer piFences) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_fence_glDeleteFencesNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(piFences);
		nglDeleteFencesNV((piFences.remaining()), piFences, piFences.position(), function_pointer);
	}
	private static native void nglDeleteFencesNV(int n, IntBuffer piFences, int piFences_position, long function_pointer);

	public static void glSetFenceNV(int fence, int condition) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_fence_glSetFenceNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSetFenceNV(fence, condition, function_pointer);
	}
	private static native void nglSetFenceNV(int fence, int condition, long function_pointer);

	public static boolean glTestFenceNV(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_fence_glTestFenceNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglTestFenceNV(fence, function_pointer);
		return __result;
	}
	private static native boolean nglTestFenceNV(int fence, long function_pointer);

	public static void glFinishFenceNV(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_fence_glFinishFenceNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFinishFenceNV(fence, function_pointer);
	}
	private static native void nglFinishFenceNV(int fence, long function_pointer);

	public static boolean glIsFenceNV(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_fence_glIsFenceNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsFenceNV(fence, function_pointer);
		return __result;
	}
	private static native boolean nglIsFenceNV(int fence, long function_pointer);

	public static void glGetFenceivNV(int fence, int pname, IntBuffer piParams) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_fence_glGetFenceivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(piParams, 4);
		nglGetFenceivNV(fence, pname, piParams, piParams.position(), function_pointer);
	}
	private static native void nglGetFenceivNV(int fence, int pname, IntBuffer piParams, int piParams_position, long function_pointer);
}
