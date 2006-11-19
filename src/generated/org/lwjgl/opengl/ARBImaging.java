/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBImaging {
	public static final int GL_CONSTANT_COLOR = 0x8001;
	public static final int GL_ONE_MINUS_CONSTANT_COLOR = 0x8002;
	public static final int GL_CONSTANT_ALPHA = 0x8003;
	public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004;
	public static final int GL_BLEND_COLOR = 0x8005;
	public static final int GL_FUNC_ADD = 0x8006;
	public static final int GL_MIN = 0x8007;
	public static final int GL_MAX = 0x8008;
	public static final int GL_BLEND_EQUATION = 0x8009;
	public static final int GL_FUNC_SUBTRACT = 0x800a;
	public static final int GL_FUNC_REVERSE_SUBTRACT = 0x800b;
	public static final int GL_COLOR_MATRIX = 0x80b1;
	public static final int GL_COLOR_MATRIX_STACK_DEPTH = 0x80b2;
	public static final int GL_MAX_COLOR_MATRIX_STACK_DEPTH = 0x80b3;
	public static final int GL_POST_COLOR_MATRIX_RED_SCALE = 0x80b4;
	public static final int GL_POST_COLOR_MATRIX_GREEN_SCALE = 0x80b5;
	public static final int GL_POST_COLOR_MATRIX_BLUE_SCALE = 0x80b6;
	public static final int GL_POST_COLOR_MATRIX_ALPHA_SCALE = 0x80b7;
	public static final int GL_POST_COLOR_MATRIX_RED_BIAS = 0x80b8;
	public static final int GL_POST_COLOR_MATRIX_GREEN_BIAS = 0x80b9;
	public static final int GL_POST_COLOR_MATRIX_BLUE_BIAS = 0x80ba;
	public static final int GL_POST_COLOR_MATRIX_ALPHA_BIAS = 0x80bb;
	public static final int GL_COLOR_TABLE = 0x80d0;
	public static final int GL_POST_CONVOLUTION_COLOR_TABLE = 0x80d1;
	public static final int GL_POST_COLOR_MATRIX_COLOR_TABLE = 0x80d2;
	public static final int GL_PROXY_COLOR_TABLE = 0x80d3;
	public static final int GL_PROXY_POST_CONVOLUTION_COLOR_TABLE = 0x80d4;
	public static final int GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE = 0x80d5;
	public static final int GL_COLOR_TABLE_SCALE = 0x80d6;
	public static final int GL_COLOR_TABLE_BIAS = 0x80d7;
	public static final int GL_COLOR_TABLE_FORMAT = 0x80d8;
	public static final int GL_COLOR_TABLE_WIDTH = 0x80d9;
	public static final int GL_COLOR_TABLE_RED_SIZE = 0x80da;
	public static final int GL_COLOR_TABLE_GREEN_SIZE = 0x80db;
	public static final int GL_COLOR_TABLE_BLUE_SIZE = 0x80dc;
	public static final int GL_COLOR_TABLE_ALPHA_SIZE = 0x80dd;
	public static final int GL_COLOR_TABLE_LUMINANCE_SIZE = 0x80de;
	public static final int GL_COLOR_TABLE_INTENSITY_SIZE = 0x80df;
	public static final int GL_CONVOLUTION_1D = 0x8010;
	public static final int GL_CONVOLUTION_2D = 0x8011;
	public static final int GL_SEPARABLE_2D = 0x8012;
	public static final int GL_CONVOLUTION_BORDER_MODE = 0x8013;
	public static final int GL_CONVOLUTION_FILTER_SCALE = 0x8014;
	public static final int GL_CONVOLUTION_FILTER_BIAS = 0x8015;
	public static final int GL_REDUCE = 0x8016;
	public static final int GL_CONVOLUTION_FORMAT = 0x8017;
	public static final int GL_CONVOLUTION_WIDTH = 0x8018;
	public static final int GL_CONVOLUTION_HEIGHT = 0x8019;
	public static final int GL_MAX_CONVOLUTION_WIDTH = 0x801a;
	public static final int GL_MAX_CONVOLUTION_HEIGHT = 0x801b;
	public static final int GL_POST_CONVOLUTION_RED_SCALE = 0x801c;
	public static final int GL_POST_CONVOLUTION_GREEN_SCALE = 0x801d;
	public static final int GL_POST_CONVOLUTION_BLUE_SCALE = 0x801e;
	public static final int GL_POST_CONVOLUTION_ALPHA_SCALE = 0x801f;
	public static final int GL_POST_CONVOLUTION_RED_BIAS = 0x8020;
	public static final int GL_POST_CONVOLUTION_GREEN_BIAS = 0x8021;
	public static final int GL_POST_CONVOLUTION_BLUE_BIAS = 0x8022;
	public static final int GL_POST_CONVOLUTION_ALPHA_BIAS = 0x8023;
	public static final int GL_IGNORE_BORDER = 0x8150;
	public static final int GL_CONSTANT_BORDER = 0x8151;
	public static final int GL_REPLICATE_BORDER = 0x8153;
	public static final int GL_CONVOLUTION_BORDER_COLOR = 0x8154;
	public static final int GL_HISTOGRAM = 0x8024;
	public static final int GL_PROXY_HISTOGRAM = 0x8025;
	public static final int GL_HISTOGRAM_WIDTH = 0x8026;
	public static final int GL_HISTOGRAM_FORMAT = 0x8027;
	public static final int GL_HISTOGRAM_RED_SIZE = 0x8028;
	public static final int GL_HISTOGRAM_GREEN_SIZE = 0x8029;
	public static final int GL_HISTOGRAM_BLUE_SIZE = 0x802a;
	public static final int GL_HISTOGRAM_ALPHA_SIZE = 0x802b;
	public static final int GL_HISTOGRAM_LUMINANCE_SIZE = 0x802c;
	public static final int GL_HISTOGRAM_SINK = 0x802d;
	public static final int GL_MINMAX = 0x802e;
	public static final int GL_MINMAX_FORMAT = 0x802f;
	public static final int GL_MINMAX_SINK = 0x8030;

	private ARBImaging() {
	}


	public static void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(data, 256);
		nglColorTable(target, internalFormat, width, format, type, data, data.position(), function_pointer);
	}
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, DoubleBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(data, 256);
		nglColorTable(target, internalFormat, width, format, type, data, data.position() << 3, function_pointer);
	}
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(data, 256);
		nglColorTable(target, internalFormat, width, format, type, data, data.position() << 2, function_pointer);
	}
	private static native void nglColorTable(int target, int internalFormat, int width, int format, int type, Buffer data, int data_position, long function_pointer);
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglColorTableBO(target, internalFormat, width, format, type, data_buffer_offset, function_pointer);
	}
	private static native void nglColorTableBO(int target, int internalFormat, int width, int format, int type, long data_buffer_offset, long function_pointer);

	public static void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorSubTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(data, 256);
		nglColorSubTable(target, start, count, format, type, data, data.position(), function_pointer);
	}
	public static void glColorSubTable(int target, int start, int count, int format, int type, DoubleBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorSubTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(data, 256);
		nglColorSubTable(target, start, count, format, type, data, data.position() << 3, function_pointer);
	}
	public static void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorSubTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(data, 256);
		nglColorSubTable(target, start, count, format, type, data, data.position() << 2, function_pointer);
	}
	private static native void nglColorSubTable(int target, int start, int count, int format, int type, Buffer data, int data_position, long function_pointer);
	public static void glColorSubTable(int target, int start, int count, int format, int type, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorSubTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglColorSubTableBO(target, start, count, format, type, data_buffer_offset, function_pointer);
	}
	private static native void nglColorSubTableBO(int target, int start, int count, int format, int type, long data_buffer_offset, long function_pointer);

	public static void glColorTableParameter(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorTableParameteriv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglColorTableParameteriv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglColorTableParameteriv(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glColorTableParameter(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glColorTableParameterfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglColorTableParameterfv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglColorTableParameterfv(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glCopyColorSubTable(int target, int start, int x, int y, int width) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glCopyColorSubTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCopyColorSubTable(target, start, x, y, width, function_pointer);
	}
	private static native void nglCopyColorSubTable(int target, int start, int x, int y, int width, long function_pointer);

	public static void glCopyColorTable(int target, int internalformat, int x, int y, int width) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glCopyColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCopyColorTable(target, internalformat, x, y, width, function_pointer);
	}
	private static native void nglCopyColorTable(int target, int internalformat, int x, int y, int width, long function_pointer);

	public static void glGetColorTable(int target, int format, int type, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, 256);
		nglGetColorTable(target, format, type, data, data.position(), function_pointer);
	}
	public static void glGetColorTable(int target, int format, int type, DoubleBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, 256);
		nglGetColorTable(target, format, type, data, data.position() << 3, function_pointer);
	}
	public static void glGetColorTable(int target, int format, int type, FloatBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetColorTable_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, 256);
		nglGetColorTable(target, format, type, data, data.position() << 2, function_pointer);
	}
	private static native void nglGetColorTable(int target, int format, int type, Buffer data, int data_position, long function_pointer);

	public static void glGetColorTableParameter(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetColorTableParameteriv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetColorTableParameteriv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetColorTableParameteriv(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetColorTableParameter(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetColorTableParameterfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetColorTableParameterfv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetColorTableParameterfv(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glBlendEquation(int mode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glBlendEquation_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquation(mode, function_pointer);
	}
	private static native void nglBlendEquation(int mode, long function_pointer);

	public static void glBlendColor(float red, float green, float blue, float alpha) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glBlendColor_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendColor(red, green, blue, alpha, function_pointer);
	}
	private static native void nglBlendColor(float red, float green, float blue, float alpha, long function_pointer);

	public static void glHistogram(int target, int width, int internalformat, boolean sink) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglHistogram(target, width, internalformat, sink, function_pointer);
	}
	private static native void nglHistogram(int target, int width, int internalformat, boolean sink, long function_pointer);

	public static void glResetHistogram(int target) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glResetHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglResetHistogram(target, function_pointer);
	}
	private static native void nglResetHistogram(int target, long function_pointer);

	public static void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 256);
		nglGetHistogram(target, reset, format, type, values, values.position(), function_pointer);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, DoubleBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 256);
		nglGetHistogram(target, reset, format, type, values, values.position() << 3, function_pointer);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 256);
		nglGetHistogram(target, reset, format, type, values, values.position() << 2, function_pointer);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 256);
		nglGetHistogram(target, reset, format, type, values, values.position() << 2, function_pointer);
	}
	public static void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 256);
		nglGetHistogram(target, reset, format, type, values, values.position() << 1, function_pointer);
	}
	private static native void nglGetHistogram(int target, boolean reset, int format, int type, Buffer values, int values_position, long function_pointer);
	public static void glGetHistogram(int target, boolean reset, int format, int type, long values_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOenabled(caps);
		nglGetHistogramBO(target, reset, format, type, values_buffer_offset, function_pointer);
	}
	private static native void nglGetHistogramBO(int target, boolean reset, int format, int type, long values_buffer_offset, long function_pointer);

	public static void glGetHistogramParameter(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogramParameterfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 256);
		nglGetHistogramParameterfv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetHistogramParameterfv(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetHistogramParameter(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetHistogramParameteriv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 256);
		nglGetHistogramParameteriv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetHistogramParameteriv(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glMinmax(int target, int internalformat, boolean sink) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMinmax(target, internalformat, sink, function_pointer);
	}
	private static native void nglMinmax(int target, int internalformat, boolean sink, long function_pointer);

	public static void glResetMinmax(int target) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glResetMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglResetMinmax(target, function_pointer);
	}
	private static native void nglResetMinmax(int target, long function_pointer);

	public static void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 4);
		nglGetMinmax(target, reset, format, types, values, values.position(), function_pointer);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, DoubleBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 4);
		nglGetMinmax(target, reset, format, types, values, values.position() << 3, function_pointer);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 4);
		nglGetMinmax(target, reset, format, types, values, values.position() << 2, function_pointer);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 4);
		nglGetMinmax(target, reset, format, types, values, values.position() << 2, function_pointer);
	}
	public static void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkBuffer(values, 4);
		nglGetMinmax(target, reset, format, types, values, values.position() << 1, function_pointer);
	}
	private static native void nglGetMinmax(int target, boolean reset, int format, int types, Buffer values, int values_position, long function_pointer);
	public static void glGetMinmax(int target, boolean reset, int format, int types, long values_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmax_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOenabled(caps);
		nglGetMinmaxBO(target, reset, format, types, values_buffer_offset, function_pointer);
	}
	private static native void nglGetMinmaxBO(int target, boolean reset, int format, int types, long values_buffer_offset, long function_pointer);

	public static void glGetMinmaxParameter(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmaxParameterfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetMinmaxParameterfv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetMinmaxParameterfv(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetMinmaxParameter(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetMinmaxParameteriv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetMinmaxParameteriv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetMinmaxParameteriv(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position(), function_pointer);
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, DoubleBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position() << 3, function_pointer);
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position() << 2, function_pointer);
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position() << 2, function_pointer);
	}
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
		nglConvolutionFilter1D(target, internalformat, width, format, type, image, image.position() << 1, function_pointer);
	}
	private static native void nglConvolutionFilter1D(int target, int internalformat, int width, int format, int type, Buffer image, int image_position, long function_pointer);
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, long image_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglConvolutionFilter1DBO(target, internalformat, width, format, type, image_buffer_offset, function_pointer);
	}
	private static native void nglConvolutionFilter1DBO(int target, int internalformat, int width, int format, int type, long image_buffer_offset, long function_pointer);

	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
		nglConvolutionFilter2D(target, internalformat, width, height, format, type, image, image.position(), function_pointer);
	}
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
		nglConvolutionFilter2D(target, internalformat, width, height, format, type, image, image.position() << 2, function_pointer);
	}
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
		nglConvolutionFilter2D(target, internalformat, width, height, format, type, image, image.position() << 1, function_pointer);
	}
	private static native void nglConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer image, int image_position, long function_pointer);
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, long image_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglConvolutionFilter2DBO(target, internalformat, width, height, format, type, image_buffer_offset, function_pointer);
	}
	private static native void nglConvolutionFilter2DBO(int target, int internalformat, int width, int height, int format, int type, long image_buffer_offset, long function_pointer);

	public static void glConvolutionParameterf(int target, int pname, float params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionParameterf_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglConvolutionParameterf(target, pname, params, function_pointer);
	}
	private static native void nglConvolutionParameterf(int target, int pname, float params, long function_pointer);

	public static void glConvolutionParameter(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionParameterfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglConvolutionParameterfv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglConvolutionParameterfv(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glConvolutionParameteri(int target, int pname, int params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionParameteri_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglConvolutionParameteri(target, pname, params, function_pointer);
	}
	private static native void nglConvolutionParameteri(int target, int pname, int params, long function_pointer);

	public static void glConvolutionParameter(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glConvolutionParameteriv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglConvolutionParameteriv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglConvolutionParameteriv(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glCopyConvolutionFilter1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCopyConvolutionFilter1D(target, internalformat, x, y, width, function_pointer);
	}
	private static native void nglCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width, long function_pointer);

	public static void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glCopyConvolutionFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCopyConvolutionFilter2D(target, internalformat, x, y, width, height, function_pointer);
	}
	private static native void nglCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height, long function_pointer);

	public static void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(image);
		nglGetConvolutionFilter(target, format, type, image, image.position(), function_pointer);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, DoubleBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(image);
		nglGetConvolutionFilter(target, format, type, image, image.position() << 3, function_pointer);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(image);
		nglGetConvolutionFilter(target, format, type, image, image.position() << 2, function_pointer);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, IntBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(image);
		nglGetConvolutionFilter(target, format, type, image, image.position() << 2, function_pointer);
	}
	public static void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(image);
		nglGetConvolutionFilter(target, format, type, image, image.position() << 1, function_pointer);
	}
	private static native void nglGetConvolutionFilter(int target, int format, int type, Buffer image, int image_position, long function_pointer);
	public static void glGetConvolutionFilter(int target, int format, int type, long image_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOenabled(caps);
		nglGetConvolutionFilterBO(target, format, type, image_buffer_offset, function_pointer);
	}
	private static native void nglGetConvolutionFilterBO(int target, int format, int type, long image_buffer_offset, long function_pointer);

	public static void glGetConvolutionParameter(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionParameterfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetConvolutionParameterfv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetConvolutionParameterfv(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetConvolutionParameter(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetConvolutionParameteriv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetConvolutionParameteriv(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetConvolutionParameteriv(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, ByteBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position(), column, column.position(), function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, DoubleBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position(), column, column.position() << 3, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, FloatBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position(), column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, IntBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position(), column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, ShortBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position(), column, column.position() << 1, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, ByteBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 3, column, column.position(), function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, DoubleBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 3, column, column.position() << 3, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, FloatBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 3, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, IntBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 3, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, ShortBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 3, column, column.position() << 1, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, ByteBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position(), function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, DoubleBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 3, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, FloatBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, IntBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, ShortBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 1, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, ByteBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position(), function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, DoubleBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 3, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, FloatBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, IntBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, ShortBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 2, column, column.position() << 1, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, ByteBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 1, column, column.position(), function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, DoubleBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 1, column, column.position() << 3, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, FloatBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 1, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, IntBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 1, column, column.position() << 2, function_pointer);
	}
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, ShortBuffer column) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		nglSeparableFilter2D(target, internalformat, width, height, format, type, row, row.position() << 1, column, column.position() << 1, function_pointer);
	}
	private static native void nglSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, int row_position, Buffer column, int column_position, long function_pointer);
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, long row_buffer_offset, long column_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glSeparableFilter2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglSeparableFilter2DBO(target, internalformat, width, height, format, type, row_buffer_offset, column_buffer_offset, function_pointer);
	}
	private static native void nglSeparableFilter2DBO(int target, int internalformat, int width, int height, int format, int type, long row_buffer_offset, long column_buffer_offset, long function_pointer);

	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position(), span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position(), span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position(), span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position(), span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 3, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 3, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 3, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 3, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 2, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 2, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 2, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 2, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 1, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 1, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 1, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position(), column, column.position() << 1, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position(), span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position(), span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position(), span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position(), span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 3, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 3, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 3, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 3, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 2, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 2, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 2, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 2, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 1, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 1, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 1, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 3, column, column.position() << 1, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position(), span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 3, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 2, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 2, column, column.position() << 1, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position(), span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position(), span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position(), span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position(), span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 3, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 3, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 3, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 3, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 2, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 2, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 2, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 2, span, span.position() << 1, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, ByteBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 1, span, span.position(), function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, DoubleBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 1, span, span.position() << 3, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, IntBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 1, span, span.position() << 2, function_pointer);
	}
	public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, ShortBuffer span) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(row);
		BufferChecks.checkDirect(column);
		BufferChecks.checkDirect(span);
		nglGetSeparableFilter(target, format, type, row, row.position() << 1, column, column.position() << 1, span, span.position() << 1, function_pointer);
	}
	private static native void nglGetSeparableFilter(int target, int format, int type, Buffer row, int row_position, Buffer column, int column_position, Buffer span, int span_position, long function_pointer);
	public static void glGetSeparableFilter(int target, int format, int type, long row_buffer_offset, long column_buffer_offset, long span_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_imaging_glGetSeparableFilter_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOenabled(caps);
		nglGetSeparableFilterBO(target, format, type, row_buffer_offset, column_buffer_offset, span_buffer_offset, function_pointer);
	}
	private static native void nglGetSeparableFilterBO(int target, int format, int type, long row_buffer_offset, long column_buffer_offset, long span_buffer_offset, long function_pointer);
}
