/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTSecondaryColor {
	public static final int GL_SECONDARY_COLOR_ARRAY_EXT = 0x845e;
	public static final int GL_SECONDARY_COLOR_ARRAY_POINTER_EXT = 0x845d;
	public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE_EXT = 0x845c;
	public static final int GL_SECONDARY_COLOR_ARRAY_TYPE_EXT = 0x845b;
	public static final int GL_SECONDARY_COLOR_ARRAY_SIZE_EXT = 0x845a;
	public static final int GL_CURRENT_SECONDARY_COLOR_EXT = 0x8459;
	public static final int GL_COLOR_SUM_EXT = 0x8458;

	private EXTSecondaryColor() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glSecondaryColorPointerEXT(int size, int stride, FloatBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglSecondaryColorPointerEXT(size, GL11.GL_FLOAT, stride, pPointer, pPointer.position() << 2);
	}
	public static void glSecondaryColorPointerEXT(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pPointer);
		nglSecondaryColorPointerEXT(size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, pPointer, pPointer.position());
	}
	private static native void nglSecondaryColorPointerEXT(int size, int type, int stride, Buffer pPointer, int pPointer_position);
	public static void glSecondaryColorPointerEXT(int size, int type, int stride, int pPointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglSecondaryColorPointerEXTBO(size, type, stride, pPointer_buffer_offset);
	}
	private static native void nglSecondaryColorPointerEXTBO(int size, int type, int stride, int pPointer_buffer_offset);

	public static native void glSecondaryColor3ubEXT(byte red, byte green, byte blue);

	public static native void glSecondaryColor3fEXT(float red, float green, float blue);

	public static native void glSecondaryColor3bEXT(byte red, byte green, byte blue);
}
