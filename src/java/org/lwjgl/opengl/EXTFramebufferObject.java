/*
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;

import java.nio.IntBuffer;

public final class EXTFramebufferObject {


	/**
	 * Accepted by the <target> parameter of BindFramebufferEXT,
	 * CheckFramebufferStatusEXT, FramebufferTexture{1D|2D|3D}EXT, and
	 * FramebufferRenderbufferEXT:
	 */
	public static final int GL_FRAMEBUFFER_EXT = 0x8D40;

	/**
	 * Accepted by the <target> parameter of BindRenderbufferEXT,
	 * RenderbufferStorageEXT, and GetRenderbufferParameterivEXT, and
	 * returned by GetFramebufferAttachmentParameterivEXT:
	 */
	public static final int GL_RENDERBUFFER_EXT = 0x8D41;

	/**
	 * Accepted by the <internalformat> parameter of
	 * RenderbufferStorageEXT:
	 */
	public static final int GL_STENCIL_INDEX_EXT = 0x8D45;
	public static final int GL_STENCIL_INDEX1_EXT = 0x8D46;
	public static final int GL_STENCIL_INDEX4_EXT = 0x8D47;
	public static final int GL_STENCIL_INDEX8_EXT = 0x8D48;
	public static final int GL_STENCIL_INDEX16_EXT = 0x8D49;

	/**
	 * Accepted by the <pname> parameter of GetRenderbufferParameterivEXT:
	 */
	public static final int GL_RENDERBUFFER_WIDTH_EXT = 0x8D42;
	public static final int GL_RENDERBUFFER_HEIGHT_EXT = 0x8D43;
	public static final int GL_RENDERBUFFER_INTERNAL_FORMAT_EXT = 0x8D44;

	/**
	 * Accepted by the <pname> parameter of
	 * GetFramebufferAttachmentParameterivEXT:
	 */
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT = 0x8CD0;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT = 0x8CD1;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT = 0x8CD2;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT = 0x8CD3;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET_EXT = 0x8CD4;

	/**
	 * Accepted by the <attachment> parameter of
	 * FramebufferTexture{1D|2D|3D}EXT, FramebufferRenderbufferEXT, and
	 * GetFramebufferAttachmentParameterivEXT
	 */
	public static final int GL_COLOR_ATTACHMENT0_EXT = 0x8CE0;
	public static final int GL_COLOR_ATTACHMENT1_EXT = 0x8CE1;
	public static final int GL_COLOR_ATTACHMENT2_EXT = 0x8CE2;
	public static final int GL_COLOR_ATTACHMENT3_EXT = 0x8CE3;
	public static final int GL_COLOR_ATTACHMENT4_EXT = 0x8CE4;
	public static final int GL_COLOR_ATTACHMENT5_EXT = 0x8CE5;
	public static final int GL_COLOR_ATTACHMENT6_EXT = 0x8CE6;
	public static final int GL_COLOR_ATTACHMENT7_EXT = 0x8CE7;
	public static final int GL_COLOR_ATTACHMENT8_EXT = 0x8CE8;
	public static final int GL_COLOR_ATTACHMENT9_EXT = 0x8CE9;
	public static final int GL_COLOR_ATTACHMENT10_EXT = 0x8CEA;
	public static final int GL_COLOR_ATTACHMENT11_EXT = 0x8CEB;
	public static final int GL_COLOR_ATTACHMENT12_EXT = 0x8CEC;
	public static final int GL_COLOR_ATTACHMENT13_EXT = 0x8CED;
	public static final int GL_COLOR_ATTACHMENT14_EXT = 0x8CEE;
	public static final int GL_COLOR_ATTACHMENT15_EXT = 0x8CEF;
	public static final int GL_DEPTH_ATTACHMENT_EXT = 0x8D00;
	public static final int GL_STENCIL_ATTACHMENT_EXT = 0x8D20;

	/**
	 * Returned by CheckFramebufferStatusEXT():
	 */
	public static final int GL_FRAMEBUFFER_COMPLETE_EXT = 0x8CD5;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT = 0x8CD6;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT = 0x8CD7;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DUPLICATE_ATTACHMENT_EXT = 0x8CD8;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT = 0x8CD9;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT = 0x8CDA;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT = 0x8CDB;
	public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT = 0x8CDC;
	public static final int GL_FRAMEBUFFER_UNSUPPORTED_EXT = 0x8CDD;
	public static final int GL_FRAMEBUFFER_STATUS_ERROR_EXT = 0x8CDE;

	/**
	 * Accepted by GetIntegerv():
	 */
	public static final int GL_FRAMEBUFFER_BINDING_EXT = 0x8CA6;
	public static final int GL_RENDERBUFFER_BINDING_EXT = 0x8CA7;
	public static final int GL_MAX_COLOR_ATTACHMENTS_EXT = 0x8CDF;
	public static final int GL_MAX_RENDERBUFFER_SIZE_EXT = 0x84E8;

	/**
	 * Returned by GetError():
	 */
	public static final int GL_INVALID_FRAMEBUFFER_OPERATION_EXT = 0x0506;

	private EXTFramebufferObject() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native boolean glIsRenderbufferEXT(int renderbuffer);

	public static native void glBindRenderbufferEXT(int target, int renderbuffer);

	// ---------------------------
	public static void glDeleteRenderbuffersEXT(IntBuffer renderbuffers) {
		BufferChecks.checkDirect(renderbuffers);
		nglDeleteRenderbuffersEXT(renderbuffers.remaining(), renderbuffers, renderbuffers.position());
	}
	private static native void nglDeleteRenderbuffersEXT(int n, IntBuffer renderbuffers, int offset);
	// ---------------------------

	// ---------------------------
	public static void glGenRenderbuffersEXT(IntBuffer renderbuffers) {
		BufferChecks.checkDirect(renderbuffers);
		nglGenRenderbuffersEXT(renderbuffers.remaining(), renderbuffers, renderbuffers.position());
	}
	private static native void nglGenRenderbuffersEXT(int n, IntBuffer renderbuffers, int offset);
	// ---------------------------

	public static native void glRenderbufferStorageEXT(int target, int internalformat, int width, int height);

	// ---------------------------
	public static void glGetRenderbufferParameterivEXT(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetRenderbufferParameterivEXT(target, pname, params, params.position());
	}
	private static native void nglGetRenderbufferParameterivEXT(int target, int pname, IntBuffer params, int offset);
	// ---------------------------

	public static native boolean glIsFramebufferEXT(int framebuffer);

	public static native void glBindFramebufferEXT(int target, int framebuffer);

	// ---------------------------
	public static void glDeleteFramebuffersEXT(IntBuffer framebuffers) {
		BufferChecks.checkDirect(framebuffers);
		nglDeleteFramebuffersEXT(framebuffers.remaining(), framebuffers, framebuffers.position());
	}
	private static native void nglDeleteFramebuffersEXT(int n, IntBuffer framebuffers, int offset);
	// ---------------------------

	// ---------------------------
	public static void glGenFramebuffersEXT(IntBuffer framebuffers) {
		BufferChecks.checkDirect(framebuffers);
		nglGenFramebuffersEXT(framebuffers.remaining(), framebuffers, framebuffers.position());
	}
	private static native void nglGenFramebuffersEXT(int n, IntBuffer framebuffers, int offset);
	// ---------------------------

	public static native int glCheckFramebufferStatusEXT(int target);

	public static native void glFramebufferTexture1DEXT(int target, int attachment, int textarget, int texture, int level);

	public static native void glFramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level);

	public static native void glFramebufferTexture3DEXT(int target, int attachment, int textarget, int texture, int level, int zoffset);

	public static native void glFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer);

	// ---------------------------
	public static void glGetFramebufferAttachmentParameterivEXT(int target, int attachment, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetFramebufferAttachmentParameterivEXT(target, attachment, pname, params, params.position());
	}
	private static native void nglGetFramebufferAttachmentParameterivEXT(int target, int attachment, int pname, IntBuffer params, int offset);
	// ---------------------------

	public static native void glGenerateMipmapEXT(int target);

}