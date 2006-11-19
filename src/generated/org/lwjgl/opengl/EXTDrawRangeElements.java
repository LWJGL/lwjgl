/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTDrawRangeElements {
	public static final int GL_MAX_ELEMENTS_VERTICES_EXT = 0x80e8;
	public static final int GL_MAX_ELEMENTS_INDICES_EXT = 0x80e9;

	private EXTDrawRangeElements() {
	}


	public static void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_range_elements_glDrawRangeElementsEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled(caps);
		BufferChecks.checkDirect(pIndices);
		nglDrawRangeElementsEXT(mode, start, end, (pIndices.remaining()), GL11.GL_UNSIGNED_BYTE, pIndices, pIndices.position(), function_pointer);
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_range_elements_glDrawRangeElementsEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled(caps);
		BufferChecks.checkDirect(pIndices);
		nglDrawRangeElementsEXT(mode, start, end, (pIndices.remaining()), GL11.GL_UNSIGNED_INT, pIndices, pIndices.position() << 2, function_pointer);
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_range_elements_glDrawRangeElementsEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled(caps);
		BufferChecks.checkDirect(pIndices);
		nglDrawRangeElementsEXT(mode, start, end, (pIndices.remaining()), GL11.GL_UNSIGNED_SHORT, pIndices, pIndices.position() << 1, function_pointer);
	}
	private static native void nglDrawRangeElementsEXT(int mode, int start, int end, int count, int type, Buffer pIndices, int pIndices_position, long function_pointer);
	public static void glDrawRangeElementsEXT(int mode, int start, int end, int count, int type, long pIndices_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_draw_range_elements_glDrawRangeElementsEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOenabled(caps);
		nglDrawRangeElementsEXTBO(mode, start, end, count, type, pIndices_buffer_offset, function_pointer);
	}
	private static native void nglDrawRangeElementsEXTBO(int mode, int start, int end, int count, int type, long pIndices_buffer_offset, long function_pointer);
}
