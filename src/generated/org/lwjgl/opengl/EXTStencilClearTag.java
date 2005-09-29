/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTStencilClearTag {
	public static final int GL_STENCIL_TAG_BITS_EXT = 0x88f2;
	public static final int GL_STENCIL_CLEAR_TAG_VALUE_EXT = 0x88f3;

	private EXTStencilClearTag() {
	}


	public static void glStencilClearTagEXT(int stencilTagBits, int stencilClearTag) {
		long function_pointer = GLContext.getCapabilities().EXT_stencil_clear_tag_glStencilClearTagEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglStencilClearTagEXT(stencilTagBits, stencilClearTag, function_pointer);
	}
	private static native void nglStencilClearTagEXT(int stencilTagBits, int stencilClearTag, long function_pointer);
}
