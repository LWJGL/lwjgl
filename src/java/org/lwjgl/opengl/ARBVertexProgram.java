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


	public static java.nio.ByteBuffer glGetVertexAttribPointerARB(int index, int pname, int result_size) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glGetVertexAttribPointervARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		java.nio.ByteBuffer __result = nglGetVertexAttribPointervARB(index, pname, result_size, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetVertexAttribPointervARB(int index, int pname, int result_size, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glGetVertexAttribivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribivARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribivARB(int index, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glGetVertexAttribfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribfvARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribfvARB(int index, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glDisableVertexAttribArrayARB(int index) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glDisableVertexAttribArrayARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDisableVertexAttribArrayARB(index, function_pointer);
	}
	private static native void nglDisableVertexAttribArrayARB(int index, long function_pointer);

	public static void glEnableVertexAttribArrayARB(int index) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glEnableVertexAttribArrayARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEnableVertexAttribArrayARB(index, function_pointer);
	}
	private static native void nglEnableVertexAttribArrayARB(int index, long function_pointer);

	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences().ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences().ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position(), function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences().ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences().ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1, function_pointer);
	}
	private static native void nglVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int buffer_position, long function_pointer);
	public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, int buffer_buffer_offset) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled();
		nglVertexAttribPointerARBBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer);
	}
	private static native void nglVertexAttribPointerARBBO(int index, int size, int type, boolean normalized, int stride, int buffer_buffer_offset, long function_pointer);

	public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib4NubARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4NubARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w, long function_pointer);

	public static void glVertexAttrib4fARB(int index, float x, float y, float z, float w) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib4fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4fARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4fARB(int index, float x, float y, float z, float w, long function_pointer);

	public static void glVertexAttrib4sARB(int index, short x, short y, short z, short w) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib4sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4sARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4sARB(int index, short x, short y, short z, short w, long function_pointer);

	public static void glVertexAttrib3fARB(int index, float x, float y, float z) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib3fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3fARB(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3fARB(int index, float x, float y, float z, long function_pointer);

	public static void glVertexAttrib3sARB(int index, short x, short y, short z) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib3sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3sARB(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3sARB(int index, short x, short y, short z, long function_pointer);

	public static void glVertexAttrib2fARB(int index, float x, float y) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib2fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2fARB(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2fARB(int index, float x, float y, long function_pointer);

	public static void glVertexAttrib2sARB(int index, short x, short y) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib2sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2sARB(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2sARB(int index, short x, short y, long function_pointer);

	public static void glVertexAttrib1fARB(int index, float x) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib1fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1fARB(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1fARB(int index, float x, long function_pointer);

	public static void glVertexAttrib1sARB(int index, short x) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_program_glVertexAttrib1sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1sARB(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1sARB(int index, short x, long function_pointer);
}
