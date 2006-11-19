/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTFramebufferObject {
	/**
	 * Accepted by the &lt;target&gt; parameter of BindFramebufferEXT,
	 * CheckFramebufferStatusEXT, FramebufferTexture{1D|2D|3D}EXT, and
	 * FramebufferRenderbufferEXT:
	 */
	public static final int GL_FRAMEBUFFER_EXT = 0x8d40;
	/**
	 * Accepted by the &lt;target&gt; parameter of BindRenderbufferEXT,
	 * RenderbufferStorageEXT, and GetRenderbufferParameterivEXT, and
	 * returned by GetFramebufferAttachmentParameterivEXT:
	 */
	public static final int GL_RENDERBUFFER_EXT = 0x8d41;
	/**
	 * Accepted by the &lt;internalformat&gt; parameter of
	 * RenderbufferStorageEXT:
	 */
	public static final int GL_STENCIL_INDEX1_EXT = 0x8d46;
	public static final int GL_STENCIL_INDEX4_EXT = 0x8d47;
	public static final int GL_STENCIL_INDEX8_EXT = 0x8d48;
	public static final int GL_STENCIL_INDEX16_EXT = 0x8d49;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetRenderbufferParameterivEXT:
	 */
	public static final int GL_RENDERBUFFER_WIDTH_EXT = 0x8d42;
	public static final int GL_RENDERBUFFER_HEIGHT_EXT = 0x8d43;
	public static final int GL_RENDERBUFFER_INTERNAL_FORMAT_EXT = 0x8d44;
	public static final int GL_RENDERBUFFER_RED_SIZE_EXT = 0x8d50;
	public static final int GL_RENDERBUFFER_GREEN_SIZE_EXT = 0x8d51;
	public static final int GL_RENDERBUFFER_BLUE_SIZE_EXT = 0x8d52;
	public static final int GL_RENDERBUFFER_ALPHA_SIZE_EXT = 0x8d53;
	public static final int GL_RENDERBUFFER_DEPTH_SIZE_EXT = 0x8d54;
	public static final int GL_RENDERBUFFER_STENCIL_SIZE_EXT = 0x8d55;
	/**
	 * Accepted by the &lt;pname&gt; parameter of
	 * GetFramebufferAttachmentParameterivEXT:
	 */
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT = 0x8cd0;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT = 0x8cd1;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT = 0x8cd2;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT = 0x8cd3;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET_EXT = 0x8cd4;
	/**
	 * Accepted by the &lt;attachment&gt; parameter of
	 * FramebufferTexture{1D|2D|3D}EXT, FramebufferRenderbufferEXT, and
	 * GetFramebufferAttachmentParameterivEXT
	 */
	public static final int GL_COLOR_ATTACHMENT0_EXT = 0x8ce0;
	public static final int GL_COLOR_ATTACHMENT1_EXT = 0x8ce1;
	public static final int GL_COLOR_ATTACHMENT2_EXT = 0x8ce2;
	public static final int GL_COLOR_ATTACHMENT3_EXT = 0x8ce3;
	public static final int GL_COLOR_ATTACHMENT4_EXT = 0x8ce4;
	public static final int GL_COLOR_ATTACHMENT5_EXT = 0x8ce5;
	public static final int GL_COLOR_ATTACHMENT6_EXT = 0x8ce6;
	public static final int GL_COLOR_ATTACHMENT7_EXT = 0x8ce7;
	public static final int GL_COLOR_ATTACHMENT8_EXT = 0x8ce8;
	public static final int GL_COLOR_ATTACHMENT9_EXT = 0x8ce9;
	public static final int GL_COLOR_ATTACHMENT10_EXT = 0x8cea;
	public static final int GL_COLOR_ATTACHMENT11_EXT = 0x8ceb;
	public static final int GL_COLOR_ATTACHMENT12_EXT = 0x8cec;
	public static final int GL_COLOR_ATTACHMENT13_EXT = 0x8ced;
	public static final int GL_COLOR_ATTACHMENT14_EXT = 0x8cee;
	public static final int GL_COLOR_ATTACHMENT15_EXT = 0x8cef;
	public static final int GL_DEPTH_ATTACHMENT_EXT = 0x8d00;
	public static final int GL_STENCIL_ATTACHMENT_EXT = 0x8d20;
	/**
	 * Returned by CheckFramebufferStatusEXT():
	 */
	public static final int GL_FRAMEBUFFER_COMPLETE_EXT = 0x8cd5;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT = 0x8cd6;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT = 0x8cd7;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT = 0x8cd9;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT = 0x8cda;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT = 0x8cdb;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT = 0x8cdc;
	public static final int GL_FRAMEBUFFER_UNSUPPORTED_EXT = 0x8cdd;
	/**
	 * Accepted by GetIntegerv():
	 */
	public static final int GL_FRAMEBUFFER_BINDING_EXT = 0x8ca6;
	public static final int GL_RENDERBUFFER_BINDING_EXT = 0x8ca7;
	public static final int GL_MAX_COLOR_ATTACHMENTS_EXT = 0x8cdf;
	public static final int GL_MAX_RENDERBUFFER_SIZE_EXT = 0x84e8;
	/**
	 * Returned by GetError():
	 */
	public static final int GL_INVALID_FRAMEBUFFER_OPERATION_EXT = 0x506;

	private EXTFramebufferObject() {
	}


	public static boolean glIsRenderbufferEXT(int renderbuffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glIsRenderbufferEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsRenderbufferEXT(renderbuffer, function_pointer);
		return __result;
	}
	private static native boolean nglIsRenderbufferEXT(int renderbuffer, long function_pointer);

	public static void glBindRenderbufferEXT(int target, int renderbuffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glBindRenderbufferEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindRenderbufferEXT(target, renderbuffer, function_pointer);
	}
	private static native void nglBindRenderbufferEXT(int target, int renderbuffer, long function_pointer);

	public static void glDeleteRenderbuffersEXT(IntBuffer renderbuffers) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glDeleteRenderbuffersEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(renderbuffers);
		nglDeleteRenderbuffersEXT((renderbuffers.remaining()), renderbuffers, renderbuffers.position(), function_pointer);
	}
	private static native void nglDeleteRenderbuffersEXT(int n, IntBuffer renderbuffers, int renderbuffers_position, long function_pointer);

	public static void glGenRenderbuffersEXT(IntBuffer renderbuffers) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glGenRenderbuffersEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(renderbuffers);
		nglGenRenderbuffersEXT((renderbuffers.remaining()), renderbuffers, renderbuffers.position(), function_pointer);
	}
	private static native void nglGenRenderbuffersEXT(int n, IntBuffer renderbuffers, int renderbuffers_position, long function_pointer);

	public static void glRenderbufferStorageEXT(int target, int internalformat, int width, int height) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glRenderbufferStorageEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglRenderbufferStorageEXT(target, internalformat, width, height, function_pointer);
	}
	private static native void nglRenderbufferStorageEXT(int target, int internalformat, int width, int height, long function_pointer);

	public static void glGetRenderbufferParameterEXT(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glGetRenderbufferParameterivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetRenderbufferParameterivEXT(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetRenderbufferParameterivEXT(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static boolean glIsFramebufferEXT(int framebuffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glIsFramebufferEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsFramebufferEXT(framebuffer, function_pointer);
		return __result;
	}
	private static native boolean nglIsFramebufferEXT(int framebuffer, long function_pointer);

	public static void glBindFramebufferEXT(int target, int framebuffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glBindFramebufferEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindFramebufferEXT(target, framebuffer, function_pointer);
	}
	private static native void nglBindFramebufferEXT(int target, int framebuffer, long function_pointer);

	public static void glDeleteFramebuffersEXT(IntBuffer framebuffers) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glDeleteFramebuffersEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(framebuffers);
		nglDeleteFramebuffersEXT((framebuffers.remaining()), framebuffers, framebuffers.position(), function_pointer);
	}
	private static native void nglDeleteFramebuffersEXT(int n, IntBuffer framebuffers, int framebuffers_position, long function_pointer);

	public static void glGenFramebuffersEXT(IntBuffer framebuffers) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glGenFramebuffersEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(framebuffers);
		nglGenFramebuffersEXT((framebuffers.remaining()), framebuffers, framebuffers.position(), function_pointer);
	}
	private static native void nglGenFramebuffersEXT(int n, IntBuffer framebuffers, int framebuffers_position, long function_pointer);

	public static int glCheckFramebufferStatusEXT(int target) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glCheckFramebufferStatusEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglCheckFramebufferStatusEXT(target, function_pointer);
		return __result;
	}
	private static native int nglCheckFramebufferStatusEXT(int target, long function_pointer);

	public static void glFramebufferTexture1DEXT(int target, int attachment, int textarget, int texture, int level) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glFramebufferTexture1DEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFramebufferTexture1DEXT(target, attachment, textarget, texture, level, function_pointer);
	}
	private static native void nglFramebufferTexture1DEXT(int target, int attachment, int textarget, int texture, int level, long function_pointer);

	public static void glFramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glFramebufferTexture2DEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFramebufferTexture2DEXT(target, attachment, textarget, texture, level, function_pointer);
	}
	private static native void nglFramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level, long function_pointer);

	public static void glFramebufferTexture3DEXT(int target, int attachment, int textarget, int texture, int level, int zoffset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glFramebufferTexture3DEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFramebufferTexture3DEXT(target, attachment, textarget, texture, level, zoffset, function_pointer);
	}
	private static native void nglFramebufferTexture3DEXT(int target, int attachment, int textarget, int texture, int level, int zoffset, long function_pointer);

	public static void glFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glFramebufferRenderbufferEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer, function_pointer);
	}
	private static native void nglFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer, long function_pointer);

	public static void glGetFramebufferAttachmentParameterEXT(int target, int attachment, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glGetFramebufferAttachmentParameterivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetFramebufferAttachmentParameterivEXT(target, attachment, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetFramebufferAttachmentParameterivEXT(int target, int attachment, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGenerateMipmapEXT(int target) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_framebuffer_object_glGenerateMipmapEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglGenerateMipmapEXT(target, function_pointer);
	}
	private static native void nglGenerateMipmapEXT(int target, long function_pointer);
}
