/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBMultitexture {
	public static final int GL_TEXTURE0_ARB = 0x84c0;
	public static final int GL_TEXTURE1_ARB = 0x84c1;
	public static final int GL_TEXTURE2_ARB = 0x84c2;
	public static final int GL_TEXTURE3_ARB = 0x84c3;
	public static final int GL_TEXTURE4_ARB = 0x84c4;
	public static final int GL_TEXTURE5_ARB = 0x84c5;
	public static final int GL_TEXTURE6_ARB = 0x84c6;
	public static final int GL_TEXTURE7_ARB = 0x84c7;
	public static final int GL_TEXTURE8_ARB = 0x84c8;
	public static final int GL_TEXTURE9_ARB = 0x84c9;
	public static final int GL_TEXTURE10_ARB = 0x84ca;
	public static final int GL_TEXTURE11_ARB = 0x84cb;
	public static final int GL_TEXTURE12_ARB = 0x84cc;
	public static final int GL_TEXTURE13_ARB = 0x84cd;
	public static final int GL_TEXTURE14_ARB = 0x84ce;
	public static final int GL_TEXTURE15_ARB = 0x84cf;
	public static final int GL_TEXTURE16_ARB = 0x84d0;
	public static final int GL_TEXTURE17_ARB = 0x84d1;
	public static final int GL_TEXTURE18_ARB = 0x84d2;
	public static final int GL_TEXTURE19_ARB = 0x84d3;
	public static final int GL_TEXTURE20_ARB = 0x84d4;
	public static final int GL_TEXTURE21_ARB = 0x84d5;
	public static final int GL_TEXTURE22_ARB = 0x84d6;
	public static final int GL_TEXTURE23_ARB = 0x84d7;
	public static final int GL_TEXTURE24_ARB = 0x84d8;
	public static final int GL_TEXTURE25_ARB = 0x84d9;
	public static final int GL_TEXTURE26_ARB = 0x84da;
	public static final int GL_TEXTURE27_ARB = 0x84db;
	public static final int GL_TEXTURE28_ARB = 0x84dc;
	public static final int GL_TEXTURE29_ARB = 0x84dd;
	public static final int GL_TEXTURE30_ARB = 0x84de;
	public static final int GL_TEXTURE31_ARB = 0x84df;
	public static final int GL_ACTIVE_TEXTURE_ARB = 0x84e0;
	public static final int GL_CLIENT_ACTIVE_TEXTURE_ARB = 0x84e1;
	public static final int GL_MAX_TEXTURE_UNITS_ARB = 0x84e2;

	private ARBMultitexture() {
	}


	public static void glClientActiveTextureARB(int texture) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glClientActiveTextureARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglClientActiveTextureARB(texture, function_pointer);
	}
	private static native void nglClientActiveTextureARB(int texture, long function_pointer);

	public static void glActiveTextureARB(int texture) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glActiveTextureARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglActiveTextureARB(texture, function_pointer);
	}
	private static native void nglActiveTextureARB(int texture, long function_pointer);

	public static void glMultiTexCoord1fARB(int target, float s) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord1fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord1fARB(target, s, function_pointer);
	}
	private static native void nglMultiTexCoord1fARB(int target, float s, long function_pointer);

	public static void glMultiTexCoord1dARB(int target, double s) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord1dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord1dARB(target, s, function_pointer);
	}
	private static native void nglMultiTexCoord1dARB(int target, double s, long function_pointer);

	public static void glMultiTexCoord1iARB(int target, int s) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord1iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord1iARB(target, s, function_pointer);
	}
	private static native void nglMultiTexCoord1iARB(int target, int s, long function_pointer);

	public static void glMultiTexCoord1sARB(int target, short s) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord1sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord1sARB(target, s, function_pointer);
	}
	private static native void nglMultiTexCoord1sARB(int target, short s, long function_pointer);

	public static void glMultiTexCoord2fARB(int target, float s, float t) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord2fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord2fARB(target, s, t, function_pointer);
	}
	private static native void nglMultiTexCoord2fARB(int target, float s, float t, long function_pointer);

	public static void glMultiTexCoord2dARB(int target, double s, double t) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord2dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord2dARB(target, s, t, function_pointer);
	}
	private static native void nglMultiTexCoord2dARB(int target, double s, double t, long function_pointer);

	public static void glMultiTexCoord2iARB(int target, int s, int t) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord2iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord2iARB(target, s, t, function_pointer);
	}
	private static native void nglMultiTexCoord2iARB(int target, int s, int t, long function_pointer);

	public static void glMultiTexCoord2sARB(int target, short s, short t) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord2sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord2sARB(target, s, t, function_pointer);
	}
	private static native void nglMultiTexCoord2sARB(int target, short s, short t, long function_pointer);

	public static void glMultiTexCoord3fARB(int target, float s, float t, float r) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord3fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord3fARB(target, s, t, r, function_pointer);
	}
	private static native void nglMultiTexCoord3fARB(int target, float s, float t, float r, long function_pointer);

	public static void glMultiTexCoord3dARB(int target, double s, double t, double r) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord3dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord3dARB(target, s, t, r, function_pointer);
	}
	private static native void nglMultiTexCoord3dARB(int target, double s, double t, double r, long function_pointer);

	public static void glMultiTexCoord3iARB(int target, int s, int t, int r) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord3iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord3iARB(target, s, t, r, function_pointer);
	}
	private static native void nglMultiTexCoord3iARB(int target, int s, int t, int r, long function_pointer);

	public static void glMultiTexCoord3sARB(int target, short s, short t, short r) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord3sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord3sARB(target, s, t, r, function_pointer);
	}
	private static native void nglMultiTexCoord3sARB(int target, short s, short t, short r, long function_pointer);

	public static void glMultiTexCoord4fARB(int target, float s, float t, float r, float q) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord4fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord4fARB(target, s, t, r, q, function_pointer);
	}
	private static native void nglMultiTexCoord4fARB(int target, float s, float t, float r, float q, long function_pointer);

	public static void glMultiTexCoord4dARB(int target, double s, double t, double r, double q) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord4dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord4dARB(target, s, t, r, q, function_pointer);
	}
	private static native void nglMultiTexCoord4dARB(int target, double s, double t, double r, double q, long function_pointer);

	public static void glMultiTexCoord4iARB(int target, int s, int t, int r, int q) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord4iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord4iARB(target, s, t, r, q, function_pointer);
	}
	private static native void nglMultiTexCoord4iARB(int target, int s, int t, int r, int q, long function_pointer);

	public static void glMultiTexCoord4sARB(int target, short s, short t, short r, short q) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_multitexture_glMultiTexCoord4sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord4sARB(target, s, t, r, q, function_pointer);
	}
	private static native void nglMultiTexCoord4sARB(int target, short s, short t, short r, short q, long function_pointer);
}
