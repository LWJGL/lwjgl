/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATISeparateStencil {
	public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS_ATI = 0x8803;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL_ATI = 0x8802;
	public static final int GL_STENCIL_BACK_FAIL_ATI = 0x8801;
	public static final int GL_STENCIL_BACK_FUNC_ATI = 0x8800;

	private ATISeparateStencil() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glStencilFuncSeparateATI(int frontfunc, int backfunc, int ref, int mask);

	public static native void glStencilOpSeparateATI(int face, int sfail, int dpfail, int dppass);
}
