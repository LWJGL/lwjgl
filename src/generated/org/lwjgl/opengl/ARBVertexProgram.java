/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBVertexProgram extends ARBProgram {
	/**
	 * Accepted by the &lt;cap&gt; parameter of Disable, Enable, and IsEnabled, by the
	 * &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev,
	 * and by the &lt;target&gt; parameter of ProgramStringARB, BindProgramARB,
	 * ProgramEnvParameter4[df][v]ARB, ProgramLocalParameter4[df][v]ARB,
	 * GetProgramEnvParameter[df]vARB, GetProgramLocalParameter[df]vARB,
	 * GetProgramivARB, and GetProgramStringARB.
	 */
	public static final int GL_VERTEX_PROGRAM_ARB = 0x8620;
	/**
	 * Accepted by the &lt;cap&gt; parameter of Disable, Enable, and IsEnabled, and by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 0x8642;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 0x8643;
	public static final int GL_COLOR_SUM_ARB = 0x8458;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetVertexAttrib[dfi]vARB:
	 */
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 0x8622;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 0x886a;
	public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 0x8626;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetVertexAttribPointervARB:
	 */
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 0x8645;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetProgramivARB:
	 */
	public static final int GL_PROGRAM_ADDRESS_REGISTERS_ARB = 0x88b0;
	public static final int GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB = 0x88b1;
	public static final int GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 0x88b2;
	public static final int GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 0x88b3;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 0x8869;

	private ARBVertexProgram() {
	}


	public static void glVertexAttrib1sARB(int index, short x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib1sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1sARB(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1sARB(int index, short x, long function_pointer);

	public static void glVertexAttrib1fARB(int index, float x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib1fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1fARB(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1fARB(int index, float x, long function_pointer);

	public static void glVertexAttrib1dARB(int index, double x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib1dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1dARB(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1dARB(int index, double x, long function_pointer);

	public static void glVertexAttrib2sARB(int index, short x, short y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib2sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2sARB(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2sARB(int index, short x, short y, long function_pointer);

	public static void glVertexAttrib2fARB(int index, float x, float y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib2fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2fARB(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2fARB(int index, float x, float y, long function_pointer);

	public static void glVertexAttrib2dARB(int index, double x, double y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib2dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2dARB(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2dARB(int index, double x, double y, long function_pointer);

	public static void glVertexAttrib3sARB(int index, short x, short y, short z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib3sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3sARB(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3sARB(int index, short x, short y, short z, long function_pointer);

	public static void glVertexAttrib3fARB(int index, float x, float y, float z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib3fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3fARB(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3fARB(int index, float x, float y, float z, long function_pointer);

	public static void glVertexAttrib3dARB(int index, double x, double y, double z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib3dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3dARB(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3dARB(int index, double x, double y, double z, long function_pointer);

	public static void glVertexAttrib4sARB(int index, short x, short y, short z, short w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib4sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4sARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4sARB(int index, short x, short y, short z, short w, long function_pointer);

	public static void glVertexAttrib4fARB(int index, float x, float y, float z, float w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib4fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4fARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4fARB(int index, float x, float y, float z, float w, long function_pointer);

	public static void glVertexAttrib4dARB(int index, double x, double y, double z, double w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib4dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4dARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4dARB(int index, double x, double y, double z, double w, long function_pointer);

	public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttrib4NubARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4NubARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w, long function_pointer);

	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, GL11.GL_DOUBLE, normalized, stride, buffer, buffer.position() << 3, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position(), function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_program_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1, function_pointer);
	}
	private static native void nglVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int buffer_position, long function_pointer);
	public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglVertexAttribPointerARBBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer);
	}
	private static native void nglVertexAttribPointerARBBO(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset, long function_pointer);

	public static void glEnableVertexAttribArrayARB(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glEnableVertexAttribArrayARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEnableVertexAttribArrayARB(index, function_pointer);
	}
	private static native void nglEnableVertexAttribArrayARB(int index, long function_pointer);

	public static void glDisableVertexAttribArrayARB(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glDisableVertexAttribArrayARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDisableVertexAttribArrayARB(index, function_pointer);
	}
	private static native void nglDisableVertexAttribArrayARB(int index, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glGetVertexAttribfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribfvARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribfvARB(int index, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, DoubleBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glGetVertexAttribdvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribdvARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribdvARB(int index, int pname, DoubleBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glGetVertexAttribivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribivARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribivARB(int index, int pname, IntBuffer params, int params_position, long function_pointer);

	public static java.nio.ByteBuffer glGetVertexAttribPointerARB(int index, int pname, long result_size) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_program_glGetVertexAttribPointervARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		java.nio.ByteBuffer __result = nglGetVertexAttribPointervARB(index, pname, result_size, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetVertexAttribPointervARB(int index, int pname, long result_size, long function_pointer);
}
