/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIVertexAttribArrayObject {

	private ATIVertexAttribArrayObject() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetVertexAttribArrayObjectATI(int index, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribArrayObjectivATI(index, pname, params, params.position());
	}
	private static native void nglGetVertexAttribArrayObjectivATI(int index, int pname, IntBuffer params, int params_position);

	public static void glGetVertexAttribArrayObjectATI(int index, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribArrayObjectfvATI(index, pname, params, params.position());
	}
	private static native void nglGetVertexAttribArrayObjectfvATI(int index, int pname, FloatBuffer params, int params_position);

	public static native void glVertexAttribArrayObjectATI(int index, int size, int type, boolean normalized, int stride, int buffer, int offset);
}
