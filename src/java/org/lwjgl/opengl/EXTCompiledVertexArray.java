/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class EXTCompiledVertexArray {
	public static final int GL_ARRAY_ELEMENT_LOCK_COUNT_EXT = 0x81a9;
	public static final int GL_ARRAY_ELEMENT_LOCK_FIRST_EXT = 0x81a8;

	private EXTCompiledVertexArray() {
	}


	public static void glUnlockArraysEXT() {
		long function_pointer = GLContext.getCapabilities().EXT_compiled_vertex_array_glUnlockArraysEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUnlockArraysEXT(function_pointer);
	}
	private static native void nglUnlockArraysEXT(long function_pointer);

	public static void glLockArraysEXT(int first, int count) {
		long function_pointer = GLContext.getCapabilities().EXT_compiled_vertex_array_glLockArraysEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglLockArraysEXT(first, count, function_pointer);
	}
	private static native void nglLockArraysEXT(int first, int count, long function_pointer);
}
