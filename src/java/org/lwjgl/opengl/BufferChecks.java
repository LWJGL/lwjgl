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

import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.util.HashMap;
import java.util.Map;

/**
 * $Id$
 * A class to check buffer boundaries in GL methods. Many GL methods read data from the GL
 * into a native Buffer at its current position. If there is unsufficient space in the buffer
 * when the call is made then a buffer overflow would otherwise occur and cause unexpected
 * behaviour, a crash, or worse, a security risk. Therefore in those methods where GL reads
 * data back into a buffer, we will call a bounds check method from this class to ensure that
 * there is sufficient space in the buffer.
 *
 * Thrown by the debug build library of the LWJGL if any OpenGL operation
 * causes an error.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
class BufferChecks {
	
	/** Static methods only! */
	private BufferChecks() {}
	
	private static Map pixelMapMap = new HashMap();
	private static Map getMap = new HashMap();
	private static final Util.IntValue scratchInt = new Util.IntValue(0);
	
	static void putPixelMap(int from, int to) {
		pixelMapMap.put(new Util.IntValue(from), new Util.IntValue(to));
	}
	
	static void putGetMap(int enum, int size) {
		getMap.put(new Util.IntValue(enum), new Util.IntValue(size));
	}
	
	/**
	 * Ensure that a pixel map buffer is big enough
	 */
	static void checkPixelMapBuffer(int map, Buffer buf) {
		scratchInt.value = map;
		Util.IntValue ret = (Util.IntValue) pixelMapMap.get(scratchInt);
		if (ret == null) {
			throw new OpenGLException("Unknown pixel map value "+map);
		} else {
			GL11.glGetInteger(ret.value, Util.int_buffer);
			int size = Util.int_buffer.get(0);
			if (buf.remaining() < size) {
				throw new BufferOverflowException();
			}
		}
	}
	
	/**
	 * Ensure that a buffer for glGet is big enough
	 */
	static void checkGetBuffer(int enum, Buffer buf) {
		scratchInt.value = enum;
		Util.IntValue ret = (Util.IntValue) getMap.get(scratchInt);
		if (ret == null) {
                        // TODO: add missing enums before re-enabling this anal check
			//throw new OpenGLException("Unknown enum glGet* "+enum);
		} else if (buf.remaining() < ret.value) {
			throw new BufferOverflowException();
		}
	}

	/**
	 * Helper method to ensure that vertex buffer objects are disabled.
	 * If they are enabled, we'll throw an OpenGLException
	 */
	static void checkVBOdisabled() {
		if (VBOTracker.getVBOArrayStack().getState() != 0) {
			throw new OpenGLException("Cannot use Buffers when VBO is enabled");
		}
	}

	/**
	 * Helper method to ensure that vertex buffer objects are enabled.
	 * If they are disabled, we'll throw an OpenGLException
	 */
	static void checkVBOenabled() {
		if (VBOTracker.getVBOArrayStack().getState() == 0) {
			throw new OpenGLException("Cannot use offsets when VBO is disabled");
		}
	}
	
}
