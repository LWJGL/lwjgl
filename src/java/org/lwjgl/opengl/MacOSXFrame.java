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

/**
 * This is the Mac OS X AWT Frame. It contains thread safe
 * methods to manipulateit from non-AWT threads
 * @author elias_naur
 */

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.lwjgl.LWJGLException;

final class MacOSXFrame extends Frame implements WindowListener, ComponentListener {

	private static final long serialVersionUID = -5823294716668988777L;

	private final MacOSXGLCanvas canvas;
	private boolean close_requested;

	/* States */
	private Rectangle bounds;
	private boolean active;
	private boolean minimized;
	private boolean should_warp_cursor;
	private boolean should_release_cursor;

	MacOSXFrame(DisplayMode mode, final java.awt.DisplayMode requested_mode, boolean fullscreen, int x, int y) throws LWJGLException {
		setResizable(Display.isResizable());
		addWindowListener(this);
		addComponentListener(this);
		canvas = new MacOSXGLCanvas();
		canvas.setFocusTraversalKeysEnabled(false);
		add(canvas, BorderLayout.CENTER);
		boolean undecorated = Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
		setUndecorated(fullscreen || undecorated);
		if ( fullscreen ) {
			try {
				AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
					public Object run() throws Exception {
						getDevice().setFullScreenWindow(MacOSXFrame.this);
						getDevice().setDisplayMode(requested_mode);
						java.awt.DisplayMode real_mode = getDevice().getDisplayMode();
						/** For some strange reason, the display mode is sometimes silently capped even though the mode is reported as supported */
						if ( requested_mode.getWidth() != real_mode.getWidth() || requested_mode.getHeight() != real_mode.getHeight() ) {
							getDevice().setFullScreenWindow(null);
							if (isDisplayable())
								dispose();
							throw new LWJGLException("AWT capped mode: requested mode = " + requested_mode.getWidth() + "x" + requested_mode.getHeight() +
								" but got " + real_mode.getWidth() + " " + real_mode.getHeight());
						}
						return null;
					}
				});
			} catch (PrivilegedActionException e) {
				throw new LWJGLException(e);
			}
		}
		pack();
		resize(x, y, mode.getWidth(), mode.getHeight());
		setVisible(true);
		requestFocus();
		canvas.requestFocus();
		updateBounds();
	}

	public void resize(int x, int y, int width, int height) {
		Insets insets = getInsets();
		setBounds(x, y, width + insets.left + insets.right, height + insets.top + insets.bottom);
	}
	
	public int getWidth() {
		Insets insets = getInsets();
		return super.getWidth() - insets.left - insets.right;
	}

	public int getHeight() {
		Insets insets = getInsets();
		return super.getHeight() - insets.top - insets.bottom;
	}

	public Rectangle syncGetBounds() {
		synchronized ( this ) {
			return bounds;
		}
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	private void updateBounds() {
		synchronized ( this ) {
			bounds = getBounds();
		}
	}

	public void componentResized(ComponentEvent e) {
		updateBounds();
	}

	public void componentMoved(ComponentEvent e) {
		updateBounds();
	}

	public static GraphicsDevice getDevice() {
		GraphicsEnvironment g_env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = g_env.getDefaultScreenDevice();
		return device;
	}

	public void windowIconified(WindowEvent e) {
		synchronized ( this ) {
			minimized = true;
		}
	}

	public void windowDeiconified(WindowEvent e) {
		synchronized ( this ) {
			minimized = false;
		}
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		synchronized ( this ) {
			close_requested = true;
		}
	}

	public void windowDeactivated(WindowEvent e) {
		synchronized ( this ) {
			active = false;
			should_release_cursor = true;
			should_warp_cursor = false;
		}
	}

	public void windowActivated(WindowEvent e) {
		synchronized ( this ) {
			active = true;
			should_warp_cursor = true;
			should_release_cursor = false;
		}
	}

	public boolean syncIsCloseRequested() {
		boolean result;
		synchronized ( this ) {
			result = close_requested;
			close_requested = false;
		}
		return result;
	}

	public boolean syncIsVisible() {
		synchronized ( this ) {
			return !minimized;
		}
	}

	public boolean syncIsActive() {
		synchronized ( this ) {
			return active;
		}
	}

	public MacOSXGLCanvas getCanvas() {
		return canvas;
	}

	public boolean syncShouldReleaseCursor() {
		boolean result;
		synchronized ( this ) {
			result = should_release_cursor;
			should_release_cursor = false;
		}
		return result;
	}

	public boolean syncShouldWarpCursor() {
		boolean result;
		synchronized ( this ) {
			result = should_warp_cursor;
			should_warp_cursor = false;
		}
		return result;
	}
}
