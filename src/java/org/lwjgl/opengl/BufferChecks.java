/*
 * Copyright (c) 2002 Lightweight Java Game Library Project All rights
 * reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *  * Neither the name of 'Light Weight Java Game Library' nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;
import java.nio.Buffer;

/**
 * $Id$ A class to
 * check buffer boundaries in GL methods. Many GL methods read data from the GL
 * into a native Buffer at its current position. If there is unsufficient space
 * in the buffer when the call is made then a buffer overflow would otherwise
 * occur and cause unexpected behaviour, a crash, or worse, a security risk.
 * Therefore in those methods where GL reads data back into a buffer, we will
 * call a bounds check method from this class to ensure that there is
 * sufficient space in the buffer.
 * 
 * Thrown by the debug build library of the LWJGL if any OpenGL operation
 * causes an error.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
class BufferChecks {
	/** Static methods only! */
	private BufferChecks() {
	}

	/**
	 * Default buffer size for most buffer checks.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 4;

	/**
	 * Helper method to ensure a buffer is big enough to receive data from a
	 * glGet* operation.
	 * 
	 * @param buf
	 *            The buffer to check
	 * @param size
	 * 			  The minimum buffer size
	 * @throws BufferOverflowException
	 */
	static void checkBuffer(Buffer buf, int size) {
		if (buf.remaining() < size) {
			throw new IllegalArgumentException("Number of remaining buffer elements is " + buf.remaining() + ", must be at least " + size);
		}
	}
	/**
	 * Helper method to ensure a buffer is big enough to receive data from a
	 * glGet* operation. To avoid unnecessarily complex buffer size checking
	 * we've just set the bar artificially high and insist that any receiving
	 * buffer has at least 4 remaining().
	 * 
	 * @param buf
	 *            The buffer to check
	 * @throws BufferOverflowException
	 */
	static void checkBuffer(Buffer buf) {
		checkBuffer(buf, DEFAULT_BUFFER_SIZE);
	}
	
	/**
	 * Helper method to ensure that vertex buffer objects are disabled. If they
	 * are enabled, we'll throw an OpenGLException
	 */
	static void ensureArrayVBOdisabled() {
		if (VBOTracker.getVBOArrayStack().getState() != 0) {
			throw new OpenGLException("Cannot use Buffers when VBO is enabled");
		}
	}
	/**
	 * Helper method to ensure that vertex buffer objects are enabled. If they
	 * are disabled, we'll throw an OpenGLException
	 */
	static void ensureArrayVBOenabled() {
		if (VBOTracker.getVBOArrayStack().getState() == 0) {
			throw new OpenGLException("Cannot use offsets when VBO is disabled");
		}
	}
	/**
	 * Helper method to ensure that vertex buffer objects are disabled. If they
	 * are enabled, we'll throw an OpenGLException
	 */
	static void ensureElementVBOdisabled() {
		if (VBOTracker.getVBOElementStack().getState() != 0) {
			throw new OpenGLException("Cannot use Buffers when VBO is enabled");
		}
	}
	/**
	 * Helper method to ensure that vertex buffer objects are enabled. If they
	 * are disabled, we'll throw an OpenGLException
	 */
	static void ensureElementVBOenabled() {
		if (VBOTracker.getVBOElementStack().getState() == 0) {
			throw new OpenGLException("Cannot use offsets when VBO is disabled");
		}
	}
	/**
	 * Calculate the storage required for an image.
	 * 
	 * @param format
	 *            The format of the image (example: GL_RGBA)
	 * @param type
	 *            The type of the image elements (example: GL_UNSIGNED_BYTE)
	 * @param width
	 *            The width of the image
	 * @param height
	 *            The height of the image (1 for 1D images)
	 * @param depth
	 *            The depth of the image (1 for 2D images)
	 * @return the size, in bytes, of the image
	 */
	static int calculateImageStorage(int format, int type, int width,
			int height, int depth) {
		int bpe;
		switch (type) {
			case GL11.GL_UNSIGNED_BYTE :
			case GL11.GL_BYTE :
				bpe = 1;
				break;
			case GL11.GL_UNSIGNED_SHORT :
			case GL11.GL_SHORT :
				bpe = 2;
				break;
			case GL11.GL_UNSIGNED_INT :
			case GL11.GL_INT :
			case GL11.GL_FLOAT :
				bpe = 4;
				break;
			default :
				// TODO: Add more types (like the GL12 types GL_UNSIGNED_INT_8_8_8_8
				return 0;
		//		throw new IllegalArgumentException("Unknown type " + type);
		}
		int epp;
		switch (format) {
			case GL11.GL_LUMINANCE:
			case GL11.GL_ALPHA:
				epp = 1;
				break;

			case GL11.GL_LUMINANCE_ALPHA:
				epp = 2;
				break;
			case GL11.GL_RGB :
			case EXTBgra.GL_BGR_EXT :
				epp = 3;
				break;
			case GL11.GL_RGBA :
			case EXTAbgr.GL_ABGR_EXT :
			case EXTBgra.GL_BGRA_EXT :
				epp = 4;
				break;
			default :
				// TODO: Add more formats. Assuming 4 is too wasteful on buffer sizes where e.g. 1 is enough (like GL_DEPTH_COMPONENT)
				return 0;
/*				// Assume 4 elements per pixel
				epp = 4;*/
		}
		return epp * bpe * width * height * depth;
	}
}
