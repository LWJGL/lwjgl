/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import java.nio.IntBuffer;

import org.lwjgl.BufferChecks;

public final class EXTMultiDrawArrays {

	private EXTMultiDrawArrays() {
	}


	public static void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount) {
		if (piFirst.remaining() != piCount.remaining()) {
			throw new IllegalArgumentException("piFirst.remaining() != piCount.remaining()");
		}
		long function_pointer = GLContext.getCapabilities().EXT_multi_draw_arrays_glMultiDrawArraysEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(piFirst);
		BufferChecks.checkDirect(piCount);
		nglMultiDrawArraysEXT(mode, piFirst, piFirst.position(), piCount, piCount.position(), (piFirst.remaining()), function_pointer);
	}
	private static native void nglMultiDrawArraysEXT(int mode, IntBuffer piFirst, int piFirst_position, IntBuffer piCount, int piCount_position, int primcount, long function_pointer);
}
