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
 * Time: 16:00:36
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.ext;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.VBOTracker;
import org.lwjgl.opengl.CoreGL11Constants;

public class EXTDrawRangeElements {
	public static final int GL_MAX_ELEMENTS_VERTICES_EXT                            = 0x80E8;
	public static final int GL_MAX_ELEMENTS_INDICES_EXT                             = 0x80E9;

	public static void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices) {
		assert VBOTracker.getVBOElementStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), CoreGL11Constants.GL_UNSIGNED_BYTE, pIndices, pIndices.position());
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) {
		assert VBOTracker.getVBOElementStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), CoreGL11Constants.GL_UNSIGNED_SHORT, pIndices, pIndices.position()<<1);
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) {
		assert VBOTracker.getVBOElementStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), CoreGL11Constants.GL_UNSIGNED_INT, pIndices, pIndices.position()<<2);
	}
	private static native void nglDrawRangeElementsEXT(int mode, int start, int end, int count, int type, Buffer pIndices, int pIndices_offset);

	public static void glDrawRangeElementsEXT(int mode, int start, int end, int count, int type, int buffer_offset) {
		assert VBOTracker.getVBOElementStack().getState() != 0: "Cannot use int offsets when VBO is disabled";
		nglDrawRangeElementsEXTVBO(mode, start, end, count, type, buffer_offset);
	}
	private static native void nglDrawRangeElementsEXTVBO(int mode, int start, int end, int count, int type, int buffer_offset);
}
