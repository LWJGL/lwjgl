/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVPixelDataRange {
	public static final int GL_READ_PIXEL_DATA_RANGE_POINTER_NV = 0x887d;
	public static final int GL_WRITE_PIXEL_DATA_RANGE_POINTER_NV = 0x887c;
	public static final int GL_READ_PIXEL_DATA_RANGE_LENGTH_NV = 0x887b;
	public static final int GL_WRITE_PIXEL_DATA_RANGE_LENGTH_NV = 0x887a;
	public static final int GL_READ_PIXEL_DATA_RANGE_NV = 0x8879;
	public static final int GL_WRITE_PIXEL_DATA_RANGE_NV = 0x8878;

	private NVPixelDataRange() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glFlushPixelDataRangeNV(int target);

	public static void glPixelDataRangeNV(int target, ByteBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining()), data, data.position());
	}
	public static void glPixelDataRangeNV(int target, IntBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining() << 2), data, data.position() << 2);
	}
	public static void glPixelDataRangeNV(int target, FloatBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining() << 2), data, data.position() << 2);
	}
	public static void glPixelDataRangeNV(int target, ShortBuffer data) {
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining() << 1), data, data.position() << 1);
	}
	private static native void nglPixelDataRangeNV(int target, int length, Buffer data, int data_position);
}
