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

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 *          $Id$
 */
final class WindowsContextImplementation implements ContextImplementation {

	public ByteBuffer create(PeerInfo peer_info, IntBuffer attribs, ByteBuffer shared_context_handle) throws LWJGLException {
		ByteBuffer peer_handle = peer_info.lockAndGetHandle();
		try {
			return nCreate(peer_handle, attribs, shared_context_handle);
		} finally {
			peer_info.unlock();
		}
	}

	private static native ByteBuffer nCreate(ByteBuffer peer_handle, IntBuffer attribs_handle, ByteBuffer shared_context_handle) throws LWJGLException;

	native long getHGLRC(ByteBuffer context_handle);

	native long getHDC(ByteBuffer peer_info_handle);

	public void swapBuffers() throws LWJGLException {
		ContextGL current_context = ContextGL.getCurrentContext();
		if ( current_context == null )
			throw new IllegalStateException("No context is current");
		synchronized ( current_context ) {
			PeerInfo current_peer_info = current_context.getPeerInfo();
			ByteBuffer peer_handle = current_peer_info.lockAndGetHandle();
			try {
				nSwapBuffers(peer_handle);
			} finally {
				current_peer_info.unlock();
			}
		}
	}

	private static native void nSwapBuffers(ByteBuffer peer_info_handle) throws LWJGLException;

	public void releaseDrawable(ByteBuffer context_handle) throws LWJGLException {
	}

	public void update(ByteBuffer context_handle) {
	}

	public void releaseCurrentContext() throws LWJGLException {
		nReleaseCurrentContext();
	}

	private static native void nReleaseCurrentContext() throws LWJGLException;

	public void makeCurrent(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
		ByteBuffer peer_handle = peer_info.lockAndGetHandle();
		try {
			nMakeCurrent(peer_handle, handle);
		} finally {
			peer_info.unlock();
		}
	}

	private static native void nMakeCurrent(ByteBuffer peer_handle, ByteBuffer context_handle) throws LWJGLException;

	public boolean isCurrent(ByteBuffer handle) throws LWJGLException {
		boolean result = nIsCurrent(handle);
		return result;
	}

	private static native boolean nIsCurrent(ByteBuffer context_handle) throws LWJGLException;

	public void setSwapInterval(int value) {
		boolean success = nSetSwapInterval(value);
		if ( !success )
			LWJGLUtil.log("Failed to set swap interval");
		Util.checkGLError();
	}

	private static native boolean nSetSwapInterval(int value);

	public void destroy(PeerInfo peer_info, ByteBuffer handle) throws LWJGLException {
		nDestroy(handle);
	}

	private static native void nDestroy(ByteBuffer context_handle) throws LWJGLException;
}
