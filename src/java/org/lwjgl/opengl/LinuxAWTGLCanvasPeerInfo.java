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

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import java.awt.Canvas;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
final class LinuxAWTGLCanvasPeerInfo extends LinuxPeerInfo {
	private final Canvas component;
	private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
	private int screen = -1;

	LinuxAWTGLCanvasPeerInfo(Canvas component) {
		this.component = component;
	}

	protected void doLockAndInitHandle() throws LWJGLException {
		ByteBuffer surface_handle = awt_surface.lockAndGetHandle(component);
		if (screen == -1) {
			try {
				screen = getScreenFromSurfaceInfo(surface_handle);
			} catch (LWJGLException e) {
				LWJGLUtil.log("Got exception while trying to determine screen: " + e);
				screen = 0;
			}
		}
		nInitHandle(screen, surface_handle, getHandle());
	}
	private static native int getScreenFromSurfaceInfo(ByteBuffer surface_handle) throws LWJGLException;
	private static native void nInitHandle(int screen, ByteBuffer surface_buffer, ByteBuffer peer_info_handle) throws LWJGLException;

	protected void doUnlock() throws LWJGLException {
		awt_surface.unlock();
	}
}
