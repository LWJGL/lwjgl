/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

import java.nio.*;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.Buffer;

/**
 * $Id: CoreGL.java,v 1.23 2003/07/23 14:51:19 elias_naur Exp $
 *
 * The core OpenGL1.4 API.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision: 1.23 $
 */
public class CoreGL14 extends CoreGL13 implements CoreGL14Constants {
	public static native void glFogCoordf (float coord);
	public static void glFogCoordPointer (int stride, FloatBuffer data) {
		nglFogCoordPointer(GL_FLOAT, stride, data, data.position() << 2);
	}
	private static native void nglFogCoordPointer (int type, int stride, Buffer data, int data_offset);
	public static void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount, int primcount) {
		nglMultiDrawArrays(mode, piFirst, piFirst.position(), piCount, piCount.position(), primcount);
	}
	private static native void nglMultiDrawArrays(int mode, IntBuffer piFirst, int piFirst_offset, IntBuffer piCount, int piCount_offset, int primcount);
/*        public static native void glMultiDrawElements(int mode, int piCount, int type, int pIndices, int primcount);*/
	public static native void glPointParameterf (int pname, float param);
	public static void glPointParameter(int pname, FloatBuffer params) {
		nglPointParameterfv(pname, params, params.position());
	}
	private static native void nglPointParameterfv(int pname, FloatBuffer params, int params_offset);
	public static native void glSecondaryColor3b (byte red, byte green, byte blue);
	public static native void glSecondaryColor3f (float red, float green, float blue);
	public static native void glSecondaryColor3ub (byte red, byte green, byte blue);
	public static void glSecondaryColorPointer (int size, boolean unsigned, int stride, ByteBuffer data) {
		nglSecondaryColorPointer(size, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, stride, data, data.position());
	}
	public static void glSecondaryColorPointer (int size, int stride, FloatBuffer data) {
		nglSecondaryColorPointer(size, GL_FLOAT, stride, data, data.position() << 2);
	}
	private static native void nglSecondaryColorPointer (int size, int type, int stride, Buffer data, int data_offset);
	public static native void glBlendFuncSeparate (int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);
	public static native void glWindowPos2f (float x, float y);
	public static native void glWindowPos2i (int x, int y);
	public static native void glWindowPos3f (float x, float y, float z);
	public static native void glWindowPos3i (int x, int y, int z);
}


