/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTFramebufferObject {
	/**
	 * Returned by GetError():
	 */
	public static final int GL_INVALID_FRAMEBUFFER_OPERATION_EXT = 0x506;
	public static final int GL_MAX_RENDERBUFFER_SIZE_EXT = 0x84e8;
	public static final int GL_MAX_COLOR_ATTACHMENTS_EXT = 0x8cdf;
	public static final int GL_RENDERBUFFER_BINDING_EXT = 0x8ca7;
	/**
	 * Accepted by GetIntegerv():
	 */
	public static final int GL_FRAMEBUFFER_BINDING_EXT = 0x8ca6;
	public static final int GL_FRAMEBUFFER_STATUS_ERROR_EXT = 0x8cde;
	public static final int GL_FRAMEBUFFER_UNSUPPORTED_EXT = 0x8cdd;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT = 0x8cdc;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT = 0x8cdb;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT = 0x8cda;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT = 0x8cd9;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DUPLICATE_ATTACHMENT_EXT = 0x8cd8;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT = 0x8cd7;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT = 0x8cd6;
	/**
	 * Returned by CheckFramebufferStatusEXT():
	 */
	public static final int GL_FRAMEBUFFER_COMPLETE_EXT = 0x8cd5;
	public static final int GL_STENCIL_ATTACHMENT_EXT = 0x8d20;
	public static final int GL_DEPTH_ATTACHMENT_EXT = 0x8d00;
	public static final int GL_COLOR_ATTACHMENT15_EXT = 0x8cef;
	public static final int GL_COLOR_ATTACHMENT14_EXT = 0x8cee;
	public static final int GL_COLOR_ATTACHMENT13_EXT = 0x8ced;
	public static final int GL_COLOR_ATTACHMENT12_EXT = 0x8cec;
	public static final int GL_COLOR_ATTACHMENT11_EXT = 0x8ceb;
	public static final int GL_COLOR_ATTACHMENT10_EXT = 0x8cea;
	public static final int GL_COLOR_ATTACHMENT9_EXT = 0x8ce9;
	public static final int GL_COLOR_ATTACHMENT8_EXT = 0x8ce8;
	public static final int GL_COLOR_ATTACHMENT7_EXT = 0x8ce7;
	public static final int GL_COLOR_ATTACHMENT6_EXT = 0x8ce6;
	public static final int GL_COLOR_ATTACHMENT5_EXT = 0x8ce5;
	public static final int GL_COLOR_ATTACHMENT4_EXT = 0x8ce4;
	public static final int GL_COLOR_ATTACHMENT3_EXT = 0x8ce3;
	public static final int GL_COLOR_ATTACHMENT2_EXT = 0x8ce2;
	public static final int GL_COLOR_ATTACHMENT1_EXT = 0x8ce1;
	public static final int GL_COLOR_ATTACHMENT0_EXT = 0x8ce0;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET_EXT = 0x8cd4;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT = 0x8cd3;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT = 0x8cd2;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT = 0x8cd1;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT = 0x8cd0;
	public static final int GL_RENDERBUFFER_INTERNAL_FORMAT_EXT = 0x8d44;
	public static final int GL_RENDERBUFFER_HEIGHT_EXT = 0x8d43;
	public static final int GL_RENDERBUFFER_WIDTH_EXT = 0x8d42;
	public static final int GL_STENCIL_INDEX16_EXT = 0x8d49;
	public static final int GL_STENCIL_INDEX8_EXT = 0x8d48;
	public static final int GL_STENCIL_INDEX4_EXT = 0x8d47;
	public static final int GL_STENCIL_INDEX1_EXT = 0x8d46;
	public static final int GL_STENCIL_INDEX_EXT = 0x8d45;
	public static final int GL_RENDERBUFFER_EXT = 0x8d41;
	public static final int GL_FRAMEBUFFER_EXT = 0x8d40;

	private EXTFramebufferObject() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glGenerateMipmapEXT(int target);

	public static void glGetFramebufferAttachmentParameterEXT(int target, int attachment, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetFramebufferAttachmentParameterivEXT(target, attachment, pname, params, params.position());
	}
	private static native void nglGetFramebufferAttachmentParameterivEXT(int target, int attachment, int pname, IntBuffer params, int params_position);

	public static native void glFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer);

	public static native void glFramebufferTexture3DEXT(int target, int attachment, int textarget, int texture, int level, int zoffset);

	public static native void glFramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level);

	public static native void glFramebufferTexture1DEXT(int target, int attachment, int textarget, int texture, int level);

	public static native int glCheckFramebufferStatusEXT(int target);

	public static void glGenFramebuffersEXT(IntBuffer framebuffers) {
		BufferChecks.checkDirect(framebuffers);
		nglGenFramebuffersEXT((framebuffers.remaining()), framebuffers, framebuffers.position());
	}
	private static native void nglGenFramebuffersEXT(int n, IntBuffer framebuffers, int framebuffers_position);

	public static void glDeleteFramebuffersEXT(IntBuffer framebuffers) {
		BufferChecks.checkDirect(framebuffers);
		nglDeleteFramebuffersEXT((framebuffers.remaining()), framebuffers, framebuffers.position());
	}
	private static native void nglDeleteFramebuffersEXT(int n, IntBuffer framebuffers, int framebuffers_position);

	public static native void glBindFramebufferEXT(int target, int framebuffer);

	public static native boolean glIsFramebufferEXT(int framebuffer);

	public static void glGetRenderbufferParameterEXT(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetRenderbufferParameterivEXT(target, pname, params, params.position());
	}
	private static native void nglGetRenderbufferParameterivEXT(int target, int pname, IntBuffer params, int params_position);

	public static native void glRenderbufferStorageEXT(int target, int internalformat, int width, int height);

	public static void glGenRenderbuffersEXT(IntBuffer renderbuffers) {
		BufferChecks.checkDirect(renderbuffers);
		nglGenRenderbuffersEXT((renderbuffers.remaining()), renderbuffers, renderbuffers.position());
	}
	private static native void nglGenRenderbuffersEXT(int n, IntBuffer renderbuffers, int renderbuffers_position);

	public static void glDeleteRenderbuffersEXT(IntBuffer renderbuffers) {
		BufferChecks.checkDirect(renderbuffers);
		nglDeleteRenderbuffersEXT((renderbuffers.remaining()), renderbuffers, renderbuffers.position());
	}
	private static native void nglDeleteRenderbuffersEXT(int n, IntBuffer renderbuffers, int renderbuffers_position);

	public static native void glBindRenderbufferEXT(int target, int renderbuffer);

	public static native boolean glIsRenderbufferEXT(int renderbuffer);
}
