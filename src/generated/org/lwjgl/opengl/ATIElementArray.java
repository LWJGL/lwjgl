/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIElementArray {
	public static final int GL_ELEMENT_ARRAY_ATI = 0x8768;
	public static final int GL_ELEMENT_ARRAY_TYPE_ATI = 0x8769;
	public static final int GL_ELEMENT_ARRAY_POINTER_ATI = 0x876a;

	private ATIElementArray() {
	}


	public static void glElementPointerATI(ByteBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_element_array_glElementPointerATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ATI_element_array_glElementPointerATI_pPointer = pPointer;
		nglElementPointerATI(GL11.GL_UNSIGNED_BYTE, pPointer, pPointer.position(), function_pointer);
	}
	public static void glElementPointerATI(IntBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_element_array_glElementPointerATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ATI_element_array_glElementPointerATI_pPointer = pPointer;
		nglElementPointerATI(GL11.GL_UNSIGNED_INT, pPointer, pPointer.position() << 2, function_pointer);
	}
	public static void glElementPointerATI(ShortBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_element_array_glElementPointerATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ATI_element_array_glElementPointerATI_pPointer = pPointer;
		nglElementPointerATI(GL11.GL_UNSIGNED_SHORT, pPointer, pPointer.position() << 1, function_pointer);
	}
	private static native void nglElementPointerATI(int type, Buffer pPointer, int pPointer_position, long function_pointer);
	public static void glElementPointerATI(int type, long pPointer_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_element_array_glElementPointerATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglElementPointerATIBO(type, pPointer_buffer_offset, function_pointer);
	}
	private static native void nglElementPointerATIBO(int type, long pPointer_buffer_offset, long function_pointer);

	public static void glDrawElementArrayATI(int mode, int count) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_element_array_glDrawElementArrayATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDrawElementArrayATI(mode, count, function_pointer);
	}
	private static native void nglDrawElementArrayATI(int mode, int count, long function_pointer);

	public static void glDrawRangeElementArrayATI(int mode, int start, int end, int count) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_element_array_glDrawRangeElementArrayATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDrawRangeElementArrayATI(mode, start, end, count, function_pointer);
	}
	private static native void nglDrawRangeElementArrayATI(int mode, int start, int end, int count, long function_pointer);
}
