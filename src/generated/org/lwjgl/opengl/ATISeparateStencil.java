/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATISeparateStencil {
	public static final int GL_STENCIL_BACK_FUNC_ATI = 0x8800;
	public static final int GL_STENCIL_BACK_FAIL_ATI = 0x8801;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL_ATI = 0x8802;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS_ATI = 0x8803;

	private ATISeparateStencil() {
	}


	public static void glStencilOpSeparateATI(int face, int sfail, int dpfail, int dppass) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_separate_stencil_glStencilOpSeparateATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglStencilOpSeparateATI(face, sfail, dpfail, dppass, function_pointer);
	}
	private static native void nglStencilOpSeparateATI(int face, int sfail, int dpfail, int dppass, long function_pointer);

	public static void glStencilFuncSeparateATI(int frontfunc, int backfunc, int ref, int mask) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_separate_stencil_glStencilFuncSeparateATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglStencilFuncSeparateATI(frontfunc, backfunc, ref, mask, function_pointer);
	}
	private static native void nglStencilFuncSeparateATI(int frontfunc, int backfunc, int ref, int mask, long function_pointer);
}
