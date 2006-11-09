/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTDrawInstanced {

	private EXTDrawInstanced() {
	}


	public static void glDrawArraysInstancedEXT(int mode, int first, int count, int primcount) {
		long function_pointer = GLContext.getCapabilities().EXT_draw_instanced_glDrawArraysInstancedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDrawArraysInstancedEXT(mode, first, count, primcount, function_pointer);
	}
	private static native void nglDrawArraysInstancedEXT(int mode, int first, int count, int primcount, long function_pointer);

	public static void glDrawElementsInstancedEXT(int mode, ByteBuffer indices, int primcount) {
		long function_pointer = GLContext.getCapabilities().EXT_draw_instanced_glDrawElementsInstancedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled();
		BufferChecks.checkDirect(indices);
		nglDrawElementsInstancedEXT(mode, (indices.remaining()), GL11.GL_UNSIGNED_BYTE, indices, indices.position(), primcount, function_pointer);
	}
	public static void glDrawElementsInstancedEXT(int mode, IntBuffer indices, int primcount) {
		long function_pointer = GLContext.getCapabilities().EXT_draw_instanced_glDrawElementsInstancedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled();
		BufferChecks.checkDirect(indices);
		nglDrawElementsInstancedEXT(mode, (indices.remaining()), GL11.GL_UNSIGNED_INT, indices, indices.position() << 2, primcount, function_pointer);
	}
	public static void glDrawElementsInstancedEXT(int mode, ShortBuffer indices, int primcount) {
		long function_pointer = GLContext.getCapabilities().EXT_draw_instanced_glDrawElementsInstancedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled();
		BufferChecks.checkDirect(indices);
		nglDrawElementsInstancedEXT(mode, (indices.remaining()), GL11.GL_UNSIGNED_SHORT, indices, indices.position() << 1, primcount, function_pointer);
	}
	private static native void nglDrawElementsInstancedEXT(int mode, int count, int type, Buffer indices, int indices_position, int primcount, long function_pointer);
	public static void glDrawElementsInstancedEXT(int mode, int count, int type, long indices_buffer_offset, int primcount) {
		long function_pointer = GLContext.getCapabilities().EXT_draw_instanced_glDrawElementsInstancedEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOenabled();
		nglDrawElementsInstancedEXTBO(mode, count, type, indices_buffer_offset, primcount, function_pointer);
	}
	private static native void nglDrawElementsInstancedEXTBO(int mode, int count, int type, long indices_buffer_offset, int primcount, long function_pointer);
}
