/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBVertexProgram extends ARBProgram {
	public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 0x8869;
	public static final int GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 0x88b3;
	public static final int GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 0x88b2;
	public static final int GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB = 0x88b1;
	public static final int GL_PROGRAM_ADDRESS_REGISTERS_ARB = 0x88b0;
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 0x8645;
	public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 0x8626;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 0x886a;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 0x8622;
	public static final int GL_COLOR_SUM_ARB = 0x8458;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 0x8643;
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 0x8642;
	public static final int GL_VERTEX_PROGRAM_ARB = 0x8620;

	private ARBVertexProgram() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static java.nio.ByteBuffer glGetVertexAttribPointerARB(int index, int pname, int result_size) {
		java.nio.ByteBuffer __result = nglGetVertexAttribPointervARB(index, pname, result_size);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetVertexAttribPointervARB(int index, int pname, int result_size);

	public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetVertexAttribivARB(index, pname, params, params.position());
	}
	private static native void nglGetVertexAttribivARB(int index, int pname, IntBuffer params, int params_position);

	public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetVertexAttribfvARB(index, pname, params, params.position());
	}
	private static native void nglGetVertexAttribfvARB(int index, int pname, FloatBuffer params, int params_position);

	public static native void glDisableVertexAttribArrayARB(int index);

	public static native void glEnableVertexAttribArrayARB(int index);

	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position());
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointerARB(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1);
	}
	private static native void nglVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int buffer_position);
	public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, int buffer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglVertexAttribPointerARBBO(index, size, type, normalized, stride, buffer_buffer_offset);
	}
	private static native void nglVertexAttribPointerARBBO(int index, int size, int type, boolean normalized, int stride, int buffer_buffer_offset);

	public static native void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w);

	public static native void glVertexAttrib4fARB(int index, float x, float y, float z, float w);

	public static native void glVertexAttrib4sARB(int index, short x, short y, short z, short w);

	public static native void glVertexAttrib3fARB(int index, float x, float y, float z);

	public static native void glVertexAttrib3sARB(int index, short x, short y, short z);

	public static native void glVertexAttrib2fARB(int index, float x, float y);

	public static native void glVertexAttrib2sARB(int index, short x, short y);

	public static native void glVertexAttrib1fARB(int index, float x);

	public static native void glVertexAttrib1sARB(int index, short x);
}
