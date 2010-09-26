/*
 * Copyright (c) 2002-2008 LWJGL Project
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

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opengl.*;

import java.nio.Buffer;

/**
 * <p/>
 * The core OpenGL1.2.1 API, with the imaging subset.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 *          $Id$
 */

public interface GL12 {

	int GL_TEXTURE_BINDING_3D = 0x806A;
	int GL_PACK_SKIP_IMAGES = 0x806B;
	int GL_PACK_IMAGE_HEIGHT = 0x806C;
	int GL_UNPACK_SKIP_IMAGES = 0x806D;
	int GL_UNPACK_IMAGE_HEIGHT = 0x806E;
	int GL_TEXTURE_3D = 0x806F;
	int GL_PROXY_TEXTURE_3D = 0x8070;
	int GL_TEXTURE_DEPTH = 0x8071;
	int GL_TEXTURE_WRAP_R = 0x8072;
	int GL_MAX_3D_TEXTURE_SIZE = 0x8073;
	int GL_BGR = 0x80E0;
	int GL_BGRA = 0x80E1;
	int GL_UNSIGNED_BYTE_3_3_2 = 0x8032;
	int GL_UNSIGNED_BYTE_2_3_3_REV = 0x8362;
	int GL_UNSIGNED_SHORT_5_6_5 = 0x8363;
	int GL_UNSIGNED_SHORT_5_6_5_REV = 0x8364;
	int GL_UNSIGNED_SHORT_4_4_4_4 = 0x8033;
	int GL_UNSIGNED_SHORT_4_4_4_4_REV = 0x8365;
	int GL_UNSIGNED_SHORT_5_5_5_1 = 0x8034;
	int GL_UNSIGNED_SHORT_1_5_5_5_REV = 0x8366;
	int GL_UNSIGNED_INT_8_8_8_8 = 0x8035;
	int GL_UNSIGNED_INT_8_8_8_8_REV = 0x8367;
	int GL_UNSIGNED_INT_10_10_10_2 = 0x8036;
	int GL_UNSIGNED_INT_2_10_10_10_REV = 0x8368;
	int GL_RESCALE_NORMAL = 0x803A;
	int GL_LIGHT_MODEL_COLOR_CONTROL = 0x81F8;
	int GL_SINGLE_COLOR = 0x81F9;
	int GL_SEPARATE_SPECULAR_COLOR = 0x81FA;
	int GL_CLAMP_TO_EDGE = 0x812F;
	int GL_TEXTURE_MIN_LOD = 0x813A;
	int GL_TEXTURE_MAX_LOD = 0x813B;
	int GL_TEXTURE_BASE_LEVEL = 0x813C;
	int GL_TEXTURE_MAX_LEVEL = 0x813D;
	int GL_MAX_ELEMENTS_VERTICES = 0x80E8;
	int GL_MAX_ELEMENTS_INDICES = 0x80E9;
	int GL_ALIASED_POINT_SIZE_RANGE = 0x846D;
	int GL_ALIASED_LINE_WIDTH_RANGE = 0x846E;

	int GL_SMOOTH_POINT_SIZE_RANGE = 0x0B12;
	int GL_SMOOTH_POINT_SIZE_GRANULARITY = 0x0B13;
	int GL_SMOOTH_LINE_WIDTH_RANGE = 0x0B22;
	int GL_SMOOTH_LINE_WIDTH_GRANULARITY = 0x0B23;

	void glDrawRangeElements(@GLenum int mode, @GLuint int start, @GLuint int end, @AutoSize("indices") @GLsizei int count,
	                         @AutoType("indices")
	                         @GLenum int type,
	                         @BufferObject(BufferKind.ElementVBO)
	                         @Const
	                         @GLubyte
	                         @GLushort
	                         @GLuint Buffer indices);

	void glTexImage3D(@GLenum int target, int level, int internalFormat, @GLsizei int width, @GLsizei int height, @GLsizei int depth, int border, @GLenum int format, @GLenum int type,
	                  @BufferObject(BufferKind.UnpackPBO)
	                  @Check(value = "GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth)", canBeNull = true)
	                  @Const
	                  @GLbyte
	                  @GLshort
	                  @GLint
	                  @GLfloat
	                  @GLdouble Buffer pixels);

	void glTexSubImage3D(@GLenum int target, int level, int xoffset, int yoffset, int zoffset, @GLsizei int width, @GLsizei int height, @GLsizei int depth, @GLenum int format, @GLenum int type,
	                     @BufferObject(BufferKind.UnpackPBO)
	                     @Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, depth)")
	                     @Const
	                     @GLbyte
	                     @GLshort
	                     @GLint
	                     @GLfloat
	                     @GLdouble Buffer pixels);

	void glCopyTexSubImage3D(@GLenum int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, @GLsizei int width, @GLsizei int height);
}