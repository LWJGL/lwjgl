/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIElementArray {
	public static final int GL_ELEMENT_ARRAY_POINTER_ATI = 0x876a;
	public static final int GL_ELEMENT_ARRAY_TYPE_ATI = 0x8769;
	public static final int GL_ELEMENT_ARRAY_ATI = 0x8768;

	private ATIElementArray() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glDrawRangeElementArrayATI(int mode, int start, int end, int count);

	public static native void glDrawElementArrayATI(int mode, int count);

	public static void glElementPointerATI(IntBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglElementPointerATI(GL11.GL_UNSIGNED_INT, pPointer, pPointer.position() << 2);
	}
	public static void glElementPointerATI(ByteBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglElementPointerATI(GL11.GL_UNSIGNED_BYTE, pPointer, pPointer.position());
	}
	public static void glElementPointerATI(ShortBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglElementPointerATI(GL11.GL_UNSIGNED_SHORT, pPointer, pPointer.position() << 1);
	}
	private static native void nglElementPointerATI(int type, Buffer pPointer, int pPointer_position);
	public static void glElementPointerATI(int type, int pPointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglElementPointerATIBO(type, pPointer_buffer_offset);
	}
	private static native void nglElementPointerATIBO(int type, int pPointer_buffer_offset);
}
