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

import org.lwjgl.generator.*;

import java.nio.IntBuffer;

public interface EXT_framebuffer_object {
	/*
	 * Accepted by the &lt;target&gt; parameter of BindFramebufferEXT,
	 * CheckFramebufferStatusEXT, FramebufferTexture{1D|2D|3D}EXT, and
	 * FramebufferRenderbufferEXT:
	 */
	public static final int GL_FRAMEBUFFER_EXT = 0x8D40;

	/*
	 * Accepted by the &lt;target&gt; parameter of BindRenderbufferEXT,
	 * RenderbufferStorageEXT, and GetRenderbufferParameterivEXT, and
	 * returned by GetFramebufferAttachmentParameterivEXT:
	 */
	public static final int GL_RENDERBUFFER_EXT = 0x8D41;

	/*
	 * Accepted by the &lt;internalformat&gt; parameter of
	 * RenderbufferStorageEXT:
	 */
	public static final int GL_STENCIL_INDEX_EXT = 0x8D45;
	public static final int GL_STENCIL_INDEX1_EXT = 0x8D46;
	public static final int GL_STENCIL_INDEX4_EXT = 0x8D47;
	public static final int GL_STENCIL_INDEX8_EXT = 0x8D48;
	public static final int GL_STENCIL_INDEX16_EXT = 0x8D49;

	/*
	 * Accepted by the &lt;pname&gt; parameter of GetRenderbufferParameterivEXT:
	 */
	public static final int GL_RENDERBUFFER_WIDTH_EXT = 0x8D42;
	public static final int GL_RENDERBUFFER_HEIGHT_EXT = 0x8D43;
	public static final int GL_RENDERBUFFER_INTERNAL_FORMAT_EXT = 0x8D44;

	/*
	 * Accepted by the &lt;pname&gt; parameter of
	 * GetFramebufferAttachmentParameterivEXT:
	 */
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT = 0x8CD0;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT = 0x8CD1;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT = 0x8CD2;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT = 0x8CD3;
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET_EXT = 0x8CD4;

	/*
	 * Accepted by the &lt;attachment&gt; parameter of
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

	public boolean glIsRenderbufferEXT(@GLuint int renderbuffer);

	public void glBindRenderbufferEXT(@GLenum int target, @GLuint int renderbuffer);

	public void glDeleteRenderbuffersEXT(@AutoSize("renderbuffers") int n, @Const @GLuint IntBuffer renderbuffers);
	public void glGenRenderbuffersEXT(@AutoSize("renderbuffers") int n, @GLuint IntBuffer renderbuffers);

	public void glRenderbufferStorageEXT(@GLenum int target, @GLenum int internalformat, @GLsizei int width, @GLsizei int height);

	@StripPostfix("params")
	public void glGetRenderbufferParameterivEXT(@GLenum int target, @GLenum int pname, @Check("4") IntBuffer params);

	public boolean glIsFramebufferEXT(@GLuint int framebuffer);

	public void glBindFramebufferEXT(@GLenum int target, @GLuint int framebuffer);

	public void glDeleteFramebuffersEXT(@AutoSize("framebuffers") int n, @Const @GLuint IntBuffer framebuffers);
	public void glGenFramebuffersEXT(@AutoSize("framebuffers") int n, @Const @GLuint IntBuffer framebuffers);

	public @GLenum int glCheckFramebufferStatusEXT(@GLenum int target);

	public void glFramebufferTexture1DEXT(@GLenum int target, @GLenum int attachment, @GLenum int textarget, @GLuint int texture, int level);

	public void glFramebufferTexture2DEXT(@GLenum int target, @GLenum int attachment, @GLenum int textarget, @GLuint int texture, int level);

	public void glFramebufferTexture3DEXT(@GLenum int target, @GLenum int attachment, @GLenum int textarget, @GLuint int texture, int level, int zoffset);

	public void glFramebufferRenderbufferEXT(@GLenum int target, @GLenum int attachment, @GLenum int renderbuffertarget, @GLuint int renderbuffer);

	@StripPostfix("params")
	public void glGetFramebufferAttachmentParameterivEXT(@GLenum int target, @GLenum int attachment, @GLenum int pname, @Check("4") IntBuffer params);

	public void glGenerateMipmapEXT(@GLenum int target);
}
