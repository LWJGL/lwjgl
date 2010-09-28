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

import java.awt.Canvas;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
final class WindowsAWTGLCanvasPeerInfo extends WindowsPeerInfo {
	private final Canvas component;
	private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
	private final PixelFormat pixel_format;
	private boolean has_pixel_format;

	WindowsAWTGLCanvasPeerInfo(Canvas component, PixelFormat pixel_format) {
		this.component = component;
		this.pixel_format = pixel_format;
	}

	protected void doLockAndInitHandle() throws LWJGLException {
		nInitHandle(awt_surface.lockAndGetHandle(component), getHandle());
		if (!has_pixel_format && pixel_format != null) {
			// If we haven't applied a pixel format yet, do it now
			int format = choosePixelFormat(getHdc(), component.getX(), component.getY(), pixel_format, null, true, true, false, true);
			setPixelFormat(getHdc(), format);
			has_pixel_format = true;
		}
	}
	private static native void nInitHandle(ByteBuffer surface_buffer, ByteBuffer peer_info_handle) throws LWJGLException;

	protected void doUnlock() throws LWJGLException {
		awt_surface.unlock();
	}
}
