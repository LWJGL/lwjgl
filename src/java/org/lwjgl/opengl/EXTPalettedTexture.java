/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTPalettedTexture {
	public static final int GL_TEXTURE_INDEX_SIZE_EXT = 0x80ed;
	public static final int GL_COLOR_TABLE_INTENSITY_SIZE_EXT = 0x80df;
	public static final int GL_COLOR_TABLE_LUMINANCE_SIZE_EXT = 0x80de;
	public static final int GL_COLOR_TABLE_ALPHA_SIZE_EXT = 0x80dd;
	public static final int GL_COLOR_TABLE_BLUE_SIZE_EXT = 0x80dc;
	public static final int GL_COLOR_TABLE_GREEN_SIZE_EXT = 0x80db;
	public static final int GL_COLOR_TABLE_RED_SIZE_EXT = 0x80da;
	public static final int GL_COLOR_TABLE_WIDTH_EXT = 0x80d9;
	public static final int GL_COLOR_TABLE_FORMAT_EXT = 0x80d8;
	public static final int GL_COLOR_INDEX16_EXT = 0x80e7;
	public static final int GL_COLOR_INDEX12_EXT = 0x80e6;
	public static final int GL_COLOR_INDEX8_EXT = 0x80e5;
	public static final int GL_COLOR_INDEX4_EXT = 0x80e4;
	public static final int GL_COLOR_INDEX2_EXT = 0x80e3;
	public static final int GL_COLOR_INDEX1_EXT = 0x80e2;

	private EXTPalettedTexture() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetColorTableParameterEXT(int target, int pname, FloatBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glGetColorTableParameterfvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetColorTableParameterfvEXT(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetColorTableParameterfvEXT(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetColorTableParameterEXT(int target, int pname, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glGetColorTableParameterivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetColorTableParameterivEXT(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetColorTableParameterivEXT(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetColorTableEXT(int target, int format, int type, ByteBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glGetColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglGetColorTableEXT(target, format, type, data, data.position(), function_pointer);
	}
	public static void glGetColorTableEXT(int target, int format, int type, FloatBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glGetColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglGetColorTableEXT(target, format, type, data, data.position() << 2, function_pointer);
	}
	public static void glGetColorTableEXT(int target, int format, int type, IntBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glGetColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglGetColorTableEXT(target, format, type, data, data.position() << 2, function_pointer);
	}
	public static void glGetColorTableEXT(int target, int format, int type, ShortBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glGetColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		nglGetColorTableEXT(target, format, type, data, data.position() << 1, function_pointer);
	}
	private static native void nglGetColorTableEXT(int target, int format, int type, Buffer data, int data_position, long function_pointer);

	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, ByteBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorSubTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
		nglColorSubTableEXT(target, start, count, format, type, data, data.position(), function_pointer);
	}
	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, FloatBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorSubTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
		nglColorSubTableEXT(target, start, count, format, type, data, data.position() << 2, function_pointer);
	}
	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, IntBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorSubTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
		nglColorSubTableEXT(target, start, count, format, type, data, data.position() << 2, function_pointer);
	}
	public static void glColorSubTableEXT(int target, int start, int count, int format, int type, ShortBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorSubTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
		nglColorSubTableEXT(target, start, count, format, type, data, data.position() << 1, function_pointer);
	}
	private static native void nglColorSubTableEXT(int target, int start, int count, int format, int type, Buffer data, int data_position, long function_pointer);

	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, ByteBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position(), function_pointer);
	}
	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position() << 2, function_pointer);
	}
	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, IntBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position() << 2, function_pointer);
	}
	public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, ShortBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_paletted_texture_glColorTableEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
		nglColorTableEXT(target, internalFormat, width, format, type, data, data.position() << 1, function_pointer);
	}
	private static native void nglColorTableEXT(int target, int internalFormat, int width, int format, int type, Buffer data, int data_position, long function_pointer);
}
