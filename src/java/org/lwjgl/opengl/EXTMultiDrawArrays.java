/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTMultiDrawArrays {

	private EXTMultiDrawArrays() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount) {
		BufferChecks.checkDirect(piFirst);
		BufferChecks.checkDirect(piCount);
		if (piFirst.remaining() != piCount.remaining()) {
			throw new IllegalArgumentException("piFirst.remaining() != piCount.remaining()");
		}
		long function_pointer = GLContext.getCapabilities().EXT_multi_draw_arrays_glMultiDrawArraysEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiDrawArraysEXT(mode, piFirst, piFirst.position(), piCount, piCount.position(), (piFirst.remaining()), function_pointer);
	}
	private static native void nglMultiDrawArraysEXT(int mode, IntBuffer piFirst, int piFirst_position, IntBuffer piCount, int piCount_position, int primcount, long function_pointer);
}
