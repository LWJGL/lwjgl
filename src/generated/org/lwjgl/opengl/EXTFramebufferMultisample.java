/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTFramebufferMultisample {
	/**
	 *         Accepted by the &lt;pname&gt; parameter of GetRenderbufferParameterivEXT.
	 */
	public static final int GL_RENDERBUFFER_SAMPLES_EXT = 0x8cab;

	private EXTFramebufferMultisample() {
	}


	/**
	 *         Establishes the data storage, format, dimensions, and number of
	 *         samples of a renderbuffer object's image.
	 */
	public static void glRenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_multisample_glRenderbufferStorageMultisampleEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglRenderbufferStorageMultisampleEXT(target, samples, internalformat, width, height, function_pointer);
	}
	private static native void nglRenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height, long function_pointer);
}
