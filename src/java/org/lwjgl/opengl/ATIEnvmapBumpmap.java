/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIEnvmapBumpmap {
	public static final int GL_BUMP_TARGET_ATI = 0x877c;
	public static final int GL_BUMP_ENVMAP_ATI = 0x877b;
	public static final int GL_DU8DV8_ATI = 0x877a;
	public static final int GL_DUDV_ATI = 0x8779;
	public static final int GL_BUMP_TEX_UNITS_ATI = 0x8778;
	public static final int GL_BUMP_NUM_TEX_UNITS_ATI = 0x8777;
	public static final int GL_BUMP_ROT_MATRIX_SIZE_ATI = 0x8776;
	public static final int GL_BUMP_ROT_MATRIX_ATI = 0x8775;

	private ATIEnvmapBumpmap() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetTexBumpParameterATI(int pname, IntBuffer param) {
		BufferChecks.checkBuffer(param, 4);
		nglGetTexBumpParameterivATI(pname, param, param.position());
	}
	private static native void nglGetTexBumpParameterivATI(int pname, IntBuffer param, int param_position);

	public static void glGetTexBumpParameterATI(int pname, FloatBuffer param) {
		BufferChecks.checkBuffer(param, 4);
		nglGetTexBumpParameterfvATI(pname, param, param.position());
	}
	private static native void nglGetTexBumpParameterfvATI(int pname, FloatBuffer param, int param_position);

	public static void glTexBumpParameterATI(int pname, IntBuffer param) {
		BufferChecks.checkBuffer(param, 4);
		nglTexBumpParameterivATI(pname, param, param.position());
	}
	private static native void nglTexBumpParameterivATI(int pname, IntBuffer param, int param_position);

	public static void glTexBumpParameterATI(int pname, FloatBuffer param) {
		BufferChecks.checkBuffer(param, 4);
		nglTexBumpParameterfvATI(pname, param, param.position());
	}
	private static native void nglTexBumpParameterfvATI(int pname, FloatBuffer param, int param_position);
}
