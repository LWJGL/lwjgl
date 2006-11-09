/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTTextureInteger {
	/**
	 * Accepted by the &lt;pname&gt; parameters of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_RGBA_INTEGER_MODE_EXT = 0x8d9e;
	/**
	 * Accepted by the &lt;internalFormat&gt; parameter of TexImage1D,
	 * TexImage2D, and TexImage3D:
	 */
	public static final int GL_RGBA32UI_EXT = 0x8d70;
	public static final int GL_RGB32UI_EXT = 0x8d71;
	public static final int GL_ALPHA32UI_EXT = 0x8d72;
	public static final int GL_INTENSITY32UI_EXT = 0x8d73;
	public static final int GL_LUMINANCE32UI_EXT = 0x8d74;
	public static final int GL_LUMINANCE_ALPHA32UI_EXT = 0x8d75;
	public static final int GL_RGBA16UI_EXT = 0x8d76;
	public static final int GL_RGB16UI_EXT = 0x8d77;
	public static final int GL_ALPHA16UI_EXT = 0x8d78;
	public static final int GL_INTENSITY16UI_EXT = 0x8d79;
	public static final int GL_LUMINANCE16UI_EXT = 0x8d7a;
	public static final int GL_LUMINANCE_ALPHA16UI_EXT = 0x8d7b;
	public static final int GL_RGBA8UI_EXT = 0x8d7c;
	public static final int GL_RGB8UI_EXT = 0x8d7d;
	public static final int GL_ALPHA8UI_EXT = 0x8d7e;
	public static final int GL_INTENSITY8UI_EXT = 0x8d7f;
	public static final int GL_LUMINANCE8UI_EXT = 0x8d80;
	public static final int GL_LUMINANCE_ALPHA8UI_EXT = 0x8d81;
	public static final int GL_RGBA32I_EXT = 0x8d82;
	public static final int GL_RGB32I_EXT = 0x8d83;
	public static final int GL_ALPHA32I_EXT = 0x8d84;
	public static final int GL_INTENSITY32I_EXT = 0x8d85;
	public static final int GL_LUMINANCE32I_EXT = 0x8d86;
	public static final int GL_LUMINANCE_ALPHA32I_EXT = 0x8d87;
	public static final int GL_RGBA16I_EXT = 0x8d88;
	public static final int GL_RGB16I_EXT = 0x8d89;
	public static final int GL_ALPHA16I_EXT = 0x8d8a;
	public static final int GL_INTENSITY16I_EXT = 0x8d8b;
	public static final int GL_LUMINANCE16I_EXT = 0x8d8c;
	public static final int GL_LUMINANCE_ALPHA16I_EXT = 0x8d8d;
	public static final int GL_RGBA8I_EXT = 0x8d8e;
	public static final int GL_RGB8I_EXT = 0x8d8f;
	public static final int GL_ALPHA8I_EXT = 0x8d90;
	public static final int GL_INTENSITY8I_EXT = 0x8d91;
	public static final int GL_LUMINANCE8I_EXT = 0x8d92;
	public static final int GL_LUMINANCE_ALPHA8I_EXT = 0x8d93;
	/**
	 * Accepted by the &lt;format&gt; parameter of TexImage1D, TexImage2D,
	 * TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D,
	 * DrawPixels and ReadPixels:
	 */
	public static final int GL_RED_INTEGER_EXT = 0x8d94;
	public static final int GL_GREEN_INTEGER_EXT = 0x8d95;
	public static final int GL_BLUE_INTEGER_EXT = 0x8d96;
	public static final int GL_ALPHA_INTEGER_EXT = 0x8d97;
	public static final int GL_RGB_INTEGER_EXT = 0x8d98;
	public static final int GL_RGBA_INTEGER_EXT = 0x8d99;
	public static final int GL_BGR_INTEGER_EXT = 0x8d9a;
	public static final int GL_BGRA_INTEGER_EXT = 0x8d9b;
	public static final int GL_LUMINANCE_INTEGER_EXT = 0x8d9c;
	public static final int GL_LUMINANCE_ALPHA_INTEGER_EXT = 0x8d9d;

	private EXTTextureInteger() {
	}


	public static void glClearColorIiEXT(int r, int g, int b, int a) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_integer_glClearColorIiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglClearColorIiEXT(r, g, b, a, function_pointer);
	}
	private static native void nglClearColorIiEXT(int r, int g, int b, int a, long function_pointer);

	public static void glClearColorIuiEXT(int r, int g, int b, int a) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_integer_glClearColorIuiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglClearColorIuiEXT(r, g, b, a, function_pointer);
	}
	private static native void nglClearColorIuiEXT(int r, int g, int b, int a, long function_pointer);

	public static void glTexParameterIEXT(int target, int pname, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_integer_glTexParameterIivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglTexParameterIivEXT(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglTexParameterIivEXT(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glTexParameterIuEXT(int target, int pname, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_integer_glTexParameterIuivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglTexParameterIuivEXT(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglTexParameterIuivEXT(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetTexParameterIEXT(int target, int pname, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_integer_glGetTexParameterIivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetTexParameterIivEXT(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetTexParameterIivEXT(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetTexParameterIuEXT(int target, int pname, IntBuffer params) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_integer_glGetTexParameterIuivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetTexParameterIuivEXT(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetTexParameterIuivEXT(int target, int pname, IntBuffer params, int params_position, long function_pointer);
}
