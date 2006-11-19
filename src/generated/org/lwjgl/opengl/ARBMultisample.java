/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBMultisample {
	public static final int GL_MULTISAMPLE_ARB = 0x809d;
	public static final int GL_SAMPLE_ALPHA_TO_COVERAGE_ARB = 0x809e;
	public static final int GL_SAMPLE_ALPHA_TO_ONE_ARB = 0x809f;
	public static final int GL_SAMPLE_COVERAGE_ARB = 0x80a0;
	public static final int GL_SAMPLE_BUFFERS_ARB = 0x80a8;
	public static final int GL_SAMPLES_ARB = 0x80a9;
	public static final int GL_SAMPLE_COVERAGE_VALUE_ARB = 0x80aa;
	public static final int GL_SAMPLE_COVERAGE_INVERT_ARB = 0x80ab;
	public static final int GL_MULTISAMPLE_BIT_ARB = 0x20000000;

	private ARBMultisample() {
	}


	public static void glSampleCoverageARB(float value, boolean invert) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multisample_glSampleCoverageARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSampleCoverageARB(value, invert, function_pointer);
	}
	private static native void nglSampleCoverageARB(float value, boolean invert, long function_pointer);
}
