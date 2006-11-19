/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTDrawBuffers2 {

	private EXTDrawBuffers2() {
	}


	public static void glColorMaskIndexedEXT(int buf, boolean r, boolean g, boolean b, boolean a) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_buffers2_glColorMaskIndexedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglColorMaskIndexedEXT(buf, r, g, b, a, function_pointer);
	}
	private static native void nglColorMaskIndexedEXT(int buf, boolean r, boolean g, boolean b, boolean a, long function_pointer);

	public static void glGetBooleanIndexedEXT(int value, int index, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_buffers2_glGetBooleanIndexedvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, 4);
		nglGetBooleanIndexedvEXT(value, index, data, data.position(), function_pointer);
	}
	private static native void nglGetBooleanIndexedvEXT(int value, int index, ByteBuffer data, int data_position, long function_pointer);

	public static void glGetIntegerIndexedEXT(int value, int index, IntBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_buffers2_glGetIntegerIndexedvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, 4);
		nglGetIntegerIndexedvEXT(value, index, data, data.position(), function_pointer);
	}
	private static native void nglGetIntegerIndexedvEXT(int value, int index, IntBuffer data, int data_position, long function_pointer);

	public static void glEnableIndexedEXT(int target, int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_buffers2_glEnableIndexedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEnableIndexedEXT(target, index, function_pointer);
	}
	private static native void nglEnableIndexedEXT(int target, int index, long function_pointer);

	public static void glDisableIndexedEXT(int target, int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_buffers2_glDisableIndexedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDisableIndexedEXT(target, index, function_pointer);
	}
	private static native void nglDisableIndexedEXT(int target, int index, long function_pointer);

	public static boolean glIsEnabledIndexedEXT(int target, int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_buffers2_glIsEnabledIndexedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsEnabledIndexedEXT(target, index, function_pointer);
		return __result;
	}
	private static native boolean nglIsEnabledIndexedEXT(int target, int index, long function_pointer);
}
