/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL12 {
	public static final int GL_TABLE_TOO_LARGE = 0x8031;
	public static final int GL_PACK_SKIP_IMAGES = 0x806b;
	public static final int GL_PACK_IMAGE_HEIGHT = 0x806c;
	public static final int GL_UNPACK_SKIP_IMAGES = 0x806d;
	public static final int GL_UNPACK_IMAGE_HEIGHT = 0x806e;
	public static final int GL_TEXTURE_3D = 0x806f;
	public static final int GL_PROXY_TEXTURE_3D = 0x8070;
	public static final int GL_TEXTURE_DEPTH = 0x8071;
	public static final int GL_TEXTURE_WRAP_R = 0x8072;
	public static final int GL_MAX_3D_TEXTURE_SIZE = 0x8073;
	public static final int GL_BGR = 0x80e0;
	public static final int GL_BGRA = 0x80e1;
	public static final int GL_UNSIGNED_BYTE_3_3_2 = 0x8032;
	public static final int GL_UNSIGNED_BYTE_2_3_3_REV = 0x8362;
	public static final int GL_UNSIGNED_SHORT_5_6_5 = 0x8363;
	public static final int GL_UNSIGNED_SHORT_5_6_5_REV = 0x8364;
	public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 0x8033;
	public static final int GL_UNSIGNED_SHORT_4_4_4_4_REV = 0x8365;
	public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 0x8034;
	public static final int GL_UNSIGNED_SHORT_1_5_5_5_REV = 0x8366;
	public static final int GL_UNSIGNED_INT_8_8_8_8 = 0x8035;
	public static final int GL_UNSIGNED_INT_8_8_8_8_REV = 0x8367;
	public static final int GL_UNSIGNED_INT_10_10_10_2 = 0x8036;
	public static final int GL_UNSIGNED_INT_2_10_10_10_REV = 0x8368;
	public static final int GL_RESCALE_NORMAL = 0x803a;
	public static final int GL_LIGHT_MODEL_COLOR_CONTROL = 0x81f8;
	public static final int GL_SINGLE_COLOR = 0x81f9;
	public static final int GL_SEPARATE_SPECULAR_COLOR = 0x81fa;
	public static final int GL_CLAMP_TO_EDGE = 0x812f;
	public static final int GL_TEXTURE_MIN_LOD = 0x813a;
	public static final int GL_TEXTURE_MAX_LOD = 0x813b;
	public static final int GL_TEXTURE_BASE_LEVEL = 0x813c;
	public static final int GL_TEXTURE_MAX_LEVEL = 0x813d;
	public static final int GL_MAX_ELEMENTS_VERTICES = 0x80e8;
	public static final int GL_MAX_ELEMENTS_INDICES = 0x80e9;
	public static final int GL_ALIASED_POINT_SIZE_RANGE = 0x846d;
	public static final int GL_ALIASED_LINE_WIDTH_RANGE = 0x846e;

	private GL12() {
	}


	public static void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glDrawRangeElements_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled(caps);
		BufferChecks.checkDirect(indices);
		nglDrawRangeElements(mode, start, end, (indices.remaining()), GL11.GL_UNSIGNED_BYTE, indices, indices.position(), function_pointer);
	}
	public static void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glDrawRangeElements_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled(caps);
		BufferChecks.checkDirect(indices);
		nglDrawRangeElements(mode, start, end, (indices.remaining()), GL11.GL_UNSIGNED_INT, indices, indices.position() << 2, function_pointer);
	}
	public static void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glDrawRangeElements_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOdisabled(caps);
		BufferChecks.checkDirect(indices);
		nglDrawRangeElements(mode, start, end, (indices.remaining()), GL11.GL_UNSIGNED_SHORT, indices, indices.position() << 1, function_pointer);
	}
	private static native void nglDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices, int indices_position, long function_pointer);
	public static void glDrawRangeElements(int mode, int start, int end, int count, int type, long indices_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glDrawRangeElements_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureElementVBOenabled(caps);
		nglDrawRangeElementsBO(mode, start, end, count, type, indices_buffer_offset, function_pointer);
	}
	private static native void nglDrawRangeElementsBO(int mode, int start, int end, int count, int type, long indices_buffer_offset, long function_pointer);

	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth, border));
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels != null ? pixels.position() : 0, function_pointer);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth, border));
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels != null ? pixels.position() << 3 : 0, function_pointer);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth, border));
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0, function_pointer);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth, border));
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0, function_pointer);
	}
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth, border));
		nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels, pixels != null ? pixels.position() << 1 : 0, function_pointer);
	}
	private static native void nglTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, Buffer pixels, int pixels_position, long function_pointer);
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglTexImage3DBO(target, level, internalFormat, width, height, depth, border, format, type, pixels_buffer_offset, function_pointer);
	}
	private static native void nglTexImage3DBO(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset, long function_pointer);

	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position(), function_pointer);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 3, function_pointer);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 2, function_pointer);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 2, function_pointer);
	}
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
		nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels, pixels.position() << 1, function_pointer);
	}
	private static native void nglTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, Buffer pixels, int pixels_position, long function_pointer);
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglTexSubImage3DBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset, function_pointer);
	}
	private static native void nglTexSubImage3DBO(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset, long function_pointer);

	public static void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL12_glCopyTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height, function_pointer);
	}
	private static native void nglCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height, long function_pointer);
}
