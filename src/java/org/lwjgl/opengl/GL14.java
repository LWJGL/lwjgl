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

/**
 * $Id$
 *
 * The core OpenGL1.4 API.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public final class GL14 {
	public static final int GL_GENERATE_MIPMAP                                      = 0x8191;
	public static final int GL_GENERATE_MIPMAP_HINT                                 = 0x8192;
	public static final int GL_DEPTH_COMPONENT16                                    = 0x81A5;
	public static final int GL_DEPTH_COMPONENT24                                    = 0x81A6;
	public static final int GL_DEPTH_COMPONENT32                                    = 0x81A7;
	public static final int GL_TEXTURE_DEPTH_SIZE                                   = 0x884A;
	public static final int GL_DEPTH_TEXTURE_MODE                                   = 0x884B;
	public static final int GL_TEXTURE_COMPARE_MODE                                 = 0x884C;
	public static final int GL_TEXTURE_COMPARE_FUNC                                 = 0x884D;
	public static final int GL_COMPARE_R_TO_TEXTURE                                 = 0x884E;
	public static final int GL_FOG_COORDINATE_SOURCE                                = 0x8450;
	public static final int GL_FOG_COORDINATE                                       = 0x8451;
	public static final int GL_FRAGMENT_DEPTH                                       = 0x8452;
	public static final int GL_CURRENT_FOG_COORDINATE                               = 0x8453;
	public static final int GL_FOG_COORDINATE_ARRAY_TYPE                            = 0x8454;
	public static final int GL_FOG_COORDINATE_ARRAY_STRIDE                          = 0x8455;
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER                         = 0x8456;
	public static final int GL_FOG_COORDINATE_ARRAY                                 = 0x8457;
	public static final int GL_POINT_SIZE_MIN                                       = 0x8126;
	public static final int GL_POINT_SIZE_MAX                                       = 0x8127;
	public static final int GL_POINT_FADE_THRESHOLD_SIZE                            = 0x8128;
	public static final int GL_POINT_DISTANCE_ATTENUATION                           = 0x8129;
	public static final int GL_COLOR_SUM                                            = 0x8458;
	public static final int GL_CURRENT_SECONDARY_COLOR                              = 0x8459;
	public static final int GL_SECONDARY_COLOR_ARRAY_SIZE                           = 0x845A;
	public static final int GL_SECONDARY_COLOR_ARRAY_TYPE                           = 0x845B;
	public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE                         = 0x845C;
	public static final int GL_SECONDARY_COLOR_ARRAY_POINTER                        = 0x845D;
	public static final int GL_SECONDARY_COLOR_ARRAY                                = 0x845E;
	public static final int GL_BLEND_DST_RGB                                        = 0x80C8;
	public static final int GL_BLEND_SRC_RGB                                        = 0x80C9;
	public static final int GL_BLEND_DST_ALPHA                                      = 0x80CA;
	public static final int GL_BLEND_SRC_ALPHA                                      = 0x80CB;
	public static final int GL_INCR_WRAP                                            = 0x8507;
	public static final int GL_DECR_WRAP                                            = 0x8508;
	public static final int GL_TEXTURE_FILTER_CONTROL                               = 0x8500;
	public static final int GL_TEXTURE_LOD_BIAS                                     = 0x8501;
	public static final int GL_MAX_TEXTURE_LOD_BIAS                                 = 0x84FD;
	public static final int GL_GL_MIRRORED_REPEAT                                   = 0x8370;

	static native void initNativeStubs();

        public static native void glBlendEquation(int mode);
        public static native void glBlendColor(float red, float green, float blue, float alpha);
	public static native void glFogCoordf(float coord);
	public static void glFogCoordPointer(int stride, FloatBuffer data) {
		BufferChecks.ensureArrayVBOdisabled();
		nglFogCoordPointer(GL11.GL_FLOAT, stride, data, data.position() << 2);
	}
	private static native void nglFogCoordPointer(int type, int stride, Buffer data, int data_offset);
	public static void glFogCoordPointer(int type, int stride, int buffer_offset) {
		BufferChecks.ensureArrayVBOenabled();
		nglFogCoordPointerVBO(type, stride, buffer_offset);
	}
	private static native void nglFogCoordPointerVBO(int type, int stride, int buffer_offset);
	public static void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount) {
		if (piFirst.remaining() != piCount.remaining()) {
			throw new IllegalArgumentException("piFirst.remaining() != piCount.remaining()");
		}
		nglMultiDrawArrays(mode, piFirst, piFirst.position(), piCount, piCount.position(), piFirst.remaining());
	}
	private static native void nglMultiDrawArrays(int mode, IntBuffer piFirst, int piFirst_offset, IntBuffer piCount, int piCount_offset, int primcount);
/*        public static native void glMultiDrawElements(int mode, int piCount, int type, int pIndices, int primcount);*/
	public static native void glPointParameterf (int pname, float param);
	public static void glPointParameter(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglPointParameterfv(pname, params, params.position());
	}
	private static native void nglPointParameterfv(int pname, FloatBuffer params, int params_offset);
	public static native void glSecondaryColor3b (byte red, byte green, byte blue);
	public static native void glSecondaryColor3f (float red, float green, float blue);
	public static native void glSecondaryColor3ub (byte red, byte green, byte blue);
	public static void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data) {
		BufferChecks.ensureArrayVBOdisabled();
		nglSecondaryColorPointer(size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, data, data.position());
	}
	public static void glSecondaryColorPointer(int size, int stride, FloatBuffer data) {
		BufferChecks.ensureArrayVBOdisabled();
		nglSecondaryColorPointer(size, GL11.GL_FLOAT, stride, data, data.position() << 2);
	}
	private static native void nglSecondaryColorPointer (int size, int type, int stride, Buffer data, int data_offset);
	public static void glSecondaryColorPointer(int size, int type, int stride, int buffer_offset) {
		BufferChecks.ensureArrayVBOenabled();
		nglSecondaryColorPointerVBO(size, type, stride, buffer_offset);
	}
	private static native void nglSecondaryColorPointerVBO(int size, int type, int stride, int buffer_offset);
	public static native void glBlendFuncSeparate (int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);
	public static native void glWindowPos2f (float x, float y);
	public static native void glWindowPos2i (int x, int y);
	public static native void glWindowPos3f (float x, float y, float z);
	public static native void glWindowPos3i (int x, int y, int z);
}

