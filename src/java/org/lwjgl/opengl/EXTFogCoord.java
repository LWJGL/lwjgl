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
/*
 * Created by IntelliJ IDEA.
 * User: nj
 * Date: 12-08-2002
 * Time: 15:17:44
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;


public class EXTFogCoord {
	public static final int GL_FOG_COORDINATE_SOURCE_EXT                            = 0x8450;
	public static final int GL_FOG_COORDINATE_EXT                                   = 0x8451;
	public static final int GL_FRAGMENT_DEPTH_EXT                                   = 0x8452;
	public static final int GL_CURRENT_FOG_COORDINATE_EXT                           = 0x8453;
	public static final int GL_FOG_COORDINATE_ARRAY_TYPE_EXT                        = 0x8454;
	public static final int GL_FOG_COORDINATE_ARRAY_STRIDE_EXT                      = 0x8455;
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER_EXT                     = 0x8456;
	public static final int GL_FOG_COORDINATE_ARRAY_EXT                             = 0x8457;

	public static native void glFogCoordfEXT(float coord);
	public static void glFogCoordPointerEXT(int stride, FloatBuffer data) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglFogCoordPointerEXT(GL11.GL_FLOAT, stride, data, data.position() << 2);
	}
	private static native void nglFogCoordPointerEXT(int type, int stride, Buffer data, int data_offset);
	public static void glFogCoordPointerEXT(int type, int stride, int buffer_offset) {
		assert VBOTracker.getVBOArrayStack().getState() != 0: "Cannot use int offsets when VBO is disabled";
		nglFogCoordPointerEXTVBO(type, stride, buffer_offset);
	}
	private static native void nglFogCoordPointerEXTVBO(int type, int stride, int buffer_offset);
}
