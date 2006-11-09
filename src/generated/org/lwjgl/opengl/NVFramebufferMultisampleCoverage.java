/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVFramebufferMultisampleCoverage {
	/**
	 *Accepted by the &lt;pname&gt; parameter of GetRenderbufferParameterivEXT: 
	 */
	public static final int GL_RENDERBUFFER_COVERAGE_SAMPLES_NV = 0x8cab;
	public static final int GL_RENDERBUFFER_COLOR_SAMPLES_NV = 0x8e10;

	private NVFramebufferMultisampleCoverage() {
	}


	public static void glRenderbufferStorageMultsampleCoverageNV(int target, int coverageSamples, int colorSamples, int internalformat, int width, int height) {
		long function_pointer = GLContext.getCapabilities().NV_framebuffer_multisample_coverage_glRenderbufferStorageMultsampleCoverageNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglRenderbufferStorageMultsampleCoverageNV(target, coverageSamples, colorSamples, internalformat, width, height, function_pointer);
	}
	private static native void nglRenderbufferStorageMultsampleCoverageNV(int target, int coverageSamples, int colorSamples, int internalformat, int width, int height, long function_pointer);
}
