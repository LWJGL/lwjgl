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

import org.lwjgl.LWJGLException;

public final class ARBMultitexture {
	public static final int GL_TEXTURE0_ARB                                         = 0x84C0;
	public static final int GL_TEXTURE1_ARB                                         = 0x84C1;
	public static final int GL_TEXTURE2_ARB                                         = 0x84C2;
	public static final int GL_TEXTURE3_ARB                                         = 0x84C3;
	public static final int GL_TEXTURE4_ARB                                         = 0x84C4;
	public static final int GL_TEXTURE5_ARB                                         = 0x84C5;
	public static final int GL_TEXTURE6_ARB                                         = 0x84C6;
	public static final int GL_TEXTURE7_ARB                                         = 0x84C7;
	public static final int GL_TEXTURE8_ARB                                         = 0x84C8;
	public static final int GL_TEXTURE9_ARB                                         = 0x84C9;
	public static final int GL_TEXTURE10_ARB                                        = 0x84CA;
	public static final int GL_TEXTURE11_ARB                                        = 0x84CB;
	public static final int GL_TEXTURE12_ARB                                        = 0x84CC;
	public static final int GL_TEXTURE13_ARB                                        = 0x84CD;
	public static final int GL_TEXTURE14_ARB                                        = 0x84CE;
	public static final int GL_TEXTURE15_ARB                                        = 0x84CF;
	public static final int GL_TEXTURE16_ARB                                        = 0x84D0;
	public static final int GL_TEXTURE17_ARB                                        = 0x84D1;
	public static final int GL_TEXTURE18_ARB                                        = 0x84D2;
	public static final int GL_TEXTURE19_ARB                                        = 0x84D3;
	public static final int GL_TEXTURE20_ARB                                        = 0x84D4;
	public static final int GL_TEXTURE21_ARB                                        = 0x84D5;
	public static final int GL_TEXTURE22_ARB                                        = 0x84D6;
	public static final int GL_TEXTURE23_ARB                                        = 0x84D7;
	public static final int GL_TEXTURE24_ARB                                        = 0x84D8;
	public static final int GL_TEXTURE25_ARB                                        = 0x84D9;
	public static final int GL_TEXTURE26_ARB                                        = 0x84DA;
	public static final int GL_TEXTURE27_ARB                                        = 0x84DB;
	public static final int GL_TEXTURE28_ARB                                        = 0x84DC;
	public static final int GL_TEXTURE29_ARB                                        = 0x84DD;
	public static final int GL_TEXTURE30_ARB                                        = 0x84DE;
	public static final int GL_TEXTURE31_ARB                                        = 0x84DF;
	public static final int GL_ACTIVE_TEXTURE_ARB                                   = 0x84E0;
	public static final int GL_CLIENT_ACTIVE_TEXTURE_ARB                            = 0x84E1;
	public static final int GL_MAX_TEXTURE_UNITS_ARB                                = 0x84E2;
	
	static native void initNativeStubs() throws LWJGLException;

	public static native void glClientActiveTextureARB(int texture);

	public static native void glActiveTextureARB(int texture);

	public static native void glMultiTexCoord1fARB(int target, float s);

	public static native void glMultiTexCoord1iARB(int target, int s);

	public static native void glMultiTexCoord1sARB(int target, short s);

	public static native void glMultiTexCoord2fARB(int target, float s, float t);

	public static native void glMultiTexCoord2iARB(int target, int s, int t);

	public static native void glMultiTexCoord2sARB(int target, short s, short t);

	public static native void glMultiTexCoord3fARB(
		int target,
		float s,
		float t,
		float r);

	public static native void glMultiTexCoord3iARB(int target, int s, int t, int r);

	public static native void glMultiTexCoord3sARB(
		int target,
		short s,
		short t,
		short r);

	public static native void glMultiTexCoord4fARB(
		int target,
		float s,
		float t,
		float r,
		float q);

	public static native void glMultiTexCoord4iARB(
		int target,
		int s,
		int t,
		int r,
		int q);

	public static native void glMultiTexCoord4sARB(
		int target,
		short s,
		short t,
		short r,
		short q);
}
