/*
 * Copyright (c) 2002-2004 LWJGL Project
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
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
final class LinuxCanvasImplementation implements AWTCanvasImplementation {
	static int getScreenFromDevice(final GraphicsDevice device) throws LWJGLException {
		try {
			Method getScreen_method = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					return device.getClass().getMethod("getScreen", null);
				}
			});
			Integer screen = (Integer)getScreen_method.invoke(device, null);
			return screen.intValue();
		} catch (Exception e) {
			throw new LWJGLException(e);
		}
	}

	private static int getVisualIDFromConfiguration(final GraphicsConfiguration configuration) throws LWJGLException {
		try {
			Method getVisual_method = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					return configuration.getClass().getMethod("getVisual", null);
				}
			});
			Integer visual = (Integer)getVisual_method.invoke(configuration, null);
			return visual.intValue();
		} catch (Exception e) {
			throw new LWJGLException(e);
		}
	}

	public PeerInfo createPeerInfo(AWTGLCanvas canvas, PixelFormat pixel_format) throws LWJGLException {
		return new LinuxAWTGLCanvasPeerInfo(canvas);
	}

	/**
	 * Find a proper GraphicsConfiguration from the given GraphicsDevice and PixelFormat.
	 *
	 * @return The GraphicsConfiguration corresponding to a visual that matches the pixel format.
	 */
	public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format) throws LWJGLException {
		try {
			int screen = getScreenFromDevice(device);
			int visual_id_matching_format = findVisualIDFromFormat(screen, pixel_format);
			GraphicsConfiguration[] configurations = device.getConfigurations();
			for (int i = 0; i < configurations.length; i++) {
				int visual_id = getVisualIDFromConfiguration(configurations[i]);
				if (visual_id == visual_id_matching_format)
					return configurations[i];
			}
		} catch (LWJGLException e) {
			LWJGLUtil.log("Got exception while trying to determine configuration: " + e);
		}
		return null; // In case we failed to locate the visual, or if we're not on a SUN JDK
	}

	private static int findVisualIDFromFormat(int screen, PixelFormat pixel_format) throws LWJGLException {
		try {
			LinuxDisplay.lockAWT();
			try {
				GLContext.loadOpenGLLibrary();
				try {
					LinuxDisplay.incDisplay();
					return nFindVisualIDFromFormat(LinuxDisplay.getDisplay(), screen, pixel_format);
				} finally {
					LinuxDisplay.decDisplay();
				}
			} finally {
				GLContext.unloadOpenGLLibrary();
			}
		} finally {
			LinuxDisplay.unlockAWT();
		}
	}
	private static native int nFindVisualIDFromFormat(long display, int screen, PixelFormat pixel_format) throws LWJGLException;
}
