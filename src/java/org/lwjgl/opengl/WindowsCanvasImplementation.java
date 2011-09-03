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

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.Canvas;
import java.security.PrivilegedAction;
import java.security.AccessController;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
final class WindowsCanvasImplementation implements AWTCanvasImplementation {
	static {
		// Make sure the awt stuff is properly initialised (the jawt library in particular)
		Toolkit.getDefaultToolkit();
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				try {
					System.loadLibrary("jawt");
				} catch (UnsatisfiedLinkError e) {
					/* It is probably already loaded, potentially by a different ClassLoader
					 * so just log the exception and continue
					 */
					LWJGLUtil.log("Failed to load jawt: " + e.getMessage());
				}
				return null;
			}
		});
	}

	public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
		return new WindowsAWTGLCanvasPeerInfo(component, pixel_format);
	}

	/**
	 * Find a proper GraphicsConfiguration from the given GraphicsDevice and PixelFormat.
	 *
	 * @return The GraphicsConfiguration corresponding to a visual that matches the pixel format.
	 */
	public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format) throws LWJGLException {
		/*
		 * It seems like the best way is to simply return null and
		 * use SetPixelFormat in JNI later.
		 */
		return null;
	}
}
