/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTVertexWeighting {
	public static final int GL_VERTEX_WEIGHT_ARRAY_POINTER_EXT = 0x8510;
	public static final int GL_VERTEX_WEIGHT_ARRAY_STRIDE_EXT = 0x850f;
	public static final int GL_VERTEX_WEIGHT_ARRAY_TYPE_EXT = 0x850e;
	public static final int GL_VERTEX_WEIGHT_ARRAY_SIZE_EXT = 0x850d;
	public static final int GL_VERTEX_WEIGHT_ARRAY_EXT = 0x850c;
	public static final int GL_CURRENT_VERTEX_WEIGHT_EXT = 0x850b;
	public static final int GL_MODELVIEW1_EXT = 0x850a;
	public static final int GL_MODELVIEW0_EXT = 0x1700;
	public static final int GL_VERTEX_WEIGHTING_EXT = 0x8509;
	public static final int GL_MODELVIEW1_MATRIX_EXT = 0x8506;
	public static final int GL_MODELVIEW0_MATRIX_EXT = 0xba6;
	public static final int GL_MODELVIEW1_STACK_DEPTH_EXT = 0x8502;
	public static final int GL_MODELVIEW0_STACK_DEPTH_EXT = 0xba3;

	private EXTVertexWeighting() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glVertexWeightPointerEXT(int size, int stride, FloatBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglVertexWeightPointerEXT(size, GL11.GL_FLOAT, stride, pPointer, pPointer.position() << 2);
	}
	private static native void nglVertexWeightPointerEXT(int size, int type, int stride, Buffer pPointer, int pPointer_position);
	public static void glVertexWeightPointerEXT(int size, int type, int stride, int pPointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglVertexWeightPointerEXTBO(size, type, stride, pPointer_buffer_offset);
	}
	private static native void nglVertexWeightPointerEXTBO(int size, int type, int stride, int pPointer_buffer_offset);

	public static native void glVertexWeightfEXT(float weight);
}
