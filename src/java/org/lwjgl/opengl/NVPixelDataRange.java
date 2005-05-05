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


	public static void glFlushPixelDataRangeNV(int target) {
		long function_pointer = GLContext.getCapabilities().NV_pixel_data_range_glFlushPixelDataRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFlushPixelDataRangeNV(target, function_pointer);
	}
	private static native void nglFlushPixelDataRangeNV(int target, long function_pointer);

	public static void glPixelDataRangeNV(int target, ByteBuffer data) {
		long function_pointer = GLContext.getCapabilities().NV_pixel_data_range_glPixelDataRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining()), data, data.position(), function_pointer);
	}
	public static void glPixelDataRangeNV(int target, FloatBuffer data) {
		long function_pointer = GLContext.getCapabilities().NV_pixel_data_range_glPixelDataRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining() << 2), data, data.position() << 2, function_pointer);
	}
	public static void glPixelDataRangeNV(int target, IntBuffer data) {
		long function_pointer = GLContext.getCapabilities().NV_pixel_data_range_glPixelDataRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining() << 2), data, data.position() << 2, function_pointer);
	}
	public static void glPixelDataRangeNV(int target, ShortBuffer data) {
		long function_pointer = GLContext.getCapabilities().NV_pixel_data_range_glPixelDataRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglPixelDataRangeNV(target, (data.remaining() << 1), data, data.position() << 1, function_pointer);
	}
	private static native void nglPixelDataRangeNV(int target, int length, Buffer data, int data_position, long function_pointer);
}
