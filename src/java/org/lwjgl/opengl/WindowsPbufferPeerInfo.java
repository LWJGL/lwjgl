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

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.LWJGLException;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
final class WindowsPbufferPeerInfo extends WindowsPeerInfo {
	WindowsPbufferPeerInfo(int width, int height, PixelFormat pixel_format, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs) throws LWJGLException {
		nCreate(getHandle(), width, height, pixel_format, pixelFormatCaps, pBufferAttribs);
	}
	private static native void nCreate(ByteBuffer handle, int width, int height, PixelFormat pixel_format, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs) throws LWJGLException;

	public boolean isBufferLost() {
		return nIsBufferLost(getHandle());
	}
	private static native boolean nIsBufferLost(ByteBuffer handle);

	public void setPbufferAttrib(int attrib, int value) {
		nSetPbufferAttrib(getHandle(), attrib, value);
	}
	private static native void nSetPbufferAttrib(ByteBuffer handle, int attrib, int value);

	public void bindTexImageToPbuffer(int buffer) {
		nBindTexImageToPbuffer(getHandle(), buffer);
	}
	private static native void nBindTexImageToPbuffer(ByteBuffer handle, int buffer);

	public void releaseTexImageFromPbuffer(int buffer) {
		nReleaseTexImageFromPbuffer(getHandle(), buffer);
	}
	private static native void nReleaseTexImageFromPbuffer(ByteBuffer handle, int buffer);

	public void destroy() {
		nDestroy(getHandle());
	}
	private static native void nDestroy(ByteBuffer handle);

	protected void doLockAndInitHandle() throws LWJGLException {
		// NO-OP
	}

	protected void doUnlock() throws LWJGLException {
		// NO-OP
	}
}
