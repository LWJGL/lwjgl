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

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.generator.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

/**
 * $Id$
 * <p/>
 * The core OpenGL1.4 API.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public interface GL14 {
	public static final int GL_GENERATE_MIPMAP = 0x8191;
	public static final int GL_GENERATE_MIPMAP_HINT = 0x8192;
	public static final int GL_DEPTH_COMPONENT16 = 0x81A5;
	public static final int GL_DEPTH_COMPONENT24 = 0x81A6;
	public static final int GL_DEPTH_COMPONENT32 = 0x81A7;
	public static final int GL_TEXTURE_DEPTH_SIZE = 0x884A;
	public static final int GL_DEPTH_TEXTURE_MODE = 0x884B;
	public static final int GL_TEXTURE_COMPARE_MODE = 0x884C;
	public static final int GL_TEXTURE_COMPARE_FUNC = 0x884D;
	public static final int GL_COMPARE_R_TO_TEXTURE = 0x884E;
	public static final int GL_FOG_COORDINATE_SOURCE = 0x8450;
	public static final int GL_FOG_COORDINATE = 0x8451;
	public static final int GL_FRAGMENT_DEPTH = 0x8452;
	public static final int GL_CURRENT_FOG_COORDINATE = 0x8453;
	public static final int GL_FOG_COORDINATE_ARRAY_TYPE = 0x8454;
	public static final int GL_FOG_COORDINATE_ARRAY_STRIDE = 0x8455;
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER = 0x8456;
	public static final int GL_FOG_COORDINATE_ARRAY = 0x8457;
	public static final int GL_POINT_SIZE_MIN = 0x8126;
	public static final int GL_POINT_SIZE_MAX = 0x8127;
	public static final int GL_POINT_FADE_THRESHOLD_SIZE = 0x8128;
	public static final int GL_POINT_DISTANCE_ATTENUATION = 0x8129;
	public static final int GL_COLOR_SUM = 0x8458;
	public static final int GL_CURRENT_SECONDARY_COLOR = 0x8459;
	public static final int GL_SECONDARY_COLOR_ARRAY_SIZE = 0x845A;
	public static final int GL_SECONDARY_COLOR_ARRAY_TYPE = 0x845B;
	public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE = 0x845C;
	public static final int GL_SECONDARY_COLOR_ARRAY_POINTER = 0x845D;
	public static final int GL_SECONDARY_COLOR_ARRAY = 0x845E;
	public static final int GL_BLEND_DST_RGB = 0x80C8;
	public static final int GL_BLEND_SRC_RGB = 0x80C9;
	public static final int GL_BLEND_DST_ALPHA = 0x80CA;
	public static final int GL_BLEND_SRC_ALPHA = 0x80CB;
	public static final int GL_INCR_WRAP = 0x8507;
	public static final int GL_DECR_WRAP = 0x8508;
	public static final int GL_TEXTURE_FILTER_CONTROL = 0x8500;
	public static final int GL_TEXTURE_LOD_BIAS = 0x8501;
	public static final int GL_MAX_TEXTURE_LOD_BIAS = 0x84FD;
	public static final int GL_GL_MIRRORED_REPEAT = 0x8370;

	public void glBlendEquation(@GLenum int mode);

	public void glBlendColor(@GLclampf float red, @GLclampf float green, @GLclampf float blue, @GLclampf float alpha);

	public void glFogCoordf(float coord);

	public void glFogCoordPointer(@AutoType("data") @GLenum int type, @GLsizei int stride,
			@BufferObject(BufferKind.ArrayVBO)
			@Check
			@Const
			@GLfloat
			Buffer data);

	@Code(	"		if (piFirst.remaining() != piCount.remaining()) {\n" +
			"			throw new IllegalArgumentException(\"piFirst.remaining() != piCount.remaining()\");\n" +
			"		}")
	public void glMultiDrawArrays(@GLenum int mode, IntBuffer piFirst, @Check @GLsizei IntBuffer piCount, @AutoSize("piFirst") @GLsizei int primcount);

	//public static native void glMultiDrawElements(int mode, int piCount, int type, int pIndices, int primcount);

	public void glPointParameteri(@GLenum int pname, int param);
	public void glPointParameterf(@GLenum int pname, float param);
	@StripPostfix("params")
	public void glPointParameteriv(@GLenum int pname, @Check("4") IntBuffer params);
	@StripPostfix("params")
	public void glPointParameterfv(@GLenum int pname, @Check("4") FloatBuffer params);

	public void glSecondaryColor3b(byte red, byte green, byte blue);
	public void glSecondaryColor3f(float red, float green, float blue);
	public void glSecondaryColor3ub(@GLubyte byte red, @GLubyte byte green, @GLubyte byte blue);

	public void glSecondaryColorPointer(int size, @AutoType("data") @GLenum int type, @GLsizei int stride,
			@BufferObject(BufferKind.ArrayVBO)
			@Check
			@Const
			@GLbyte
			@GLubyte
			@GLfloat
			Buffer data);

	public void glBlendFuncSeparate(@GLenum int sfactorRGB, @GLenum int dfactorRGB, @GLenum int sfactorAlpha, @GLenum int dfactorAlpha);
	public void glWindowPos2f(float x, float y);
	public void glWindowPos2i(int x, int y);
	public void glWindowPos3f(float x, float y, float z);
	public void glWindowPos3i(int x, int y, int z);
}

