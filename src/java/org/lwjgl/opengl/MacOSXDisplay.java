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

/**
 * This is the Display implementation interface. Display delegates
 * to implementors of this interface. There is one DisplayImplementation
 * for each supported platform.
 * @author elias_naur
 */

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;

final class MacOSXDisplay implements DisplayImplementation {
	private static final int PBUFFER_HANDLE_SIZE = 24;
	private static final int GAMMA_LENGTH = 256;

	private MacOSXFrame frame;
	private MouseEventQueue mouse_queue;
	private KeyboardEventQueue keyboard_queue;
	private java.awt.DisplayMode requested_mode;

	/* States */
	private boolean close_requested;

	MacOSXDisplay() {
		new MacOSXApplicationListener();
	}

	public void createWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException {
		hideUI(fullscreen);
		close_requested = false;
		try {
			frame = new MacOSXFrame(mode, requested_mode, fullscreen, x, y);
		} catch (LWJGLException e) {
			destroyWindow();
			throw e;
		}
	}

	private void handleQuit() {
		synchronized (this) {
			close_requested = true;
		}
	}

	public void destroyWindow() {
		if (MacOSXFrame.getDevice().getFullScreenWindow() != null)
			MacOSXFrame.getDevice().setFullScreenWindow(null);
		if (frame != null) {
			setView(null);
			frame.syncDispose();
			frame = null;
		}
		hideUI(false);
	}

	public int getGammaRampLength() {
		return GAMMA_LENGTH;
	}

	public native void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;

	public String getAdapter() {
		return null;
	}

	public String getVersion() {
		return null;
	}

	private boolean equals(java.awt.DisplayMode awt_mode, DisplayMode mode) {
		return awt_mode.getWidth() == mode.getWidth() && awt_mode.getHeight() == mode.getHeight()
			&& awt_mode.getBitDepth() == mode.getBitsPerPixel() && awt_mode.getRefreshRate() == mode.getFrequency();
	}

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		java.awt.DisplayMode[] awt_modes = MacOSXFrame.getDevice().getDisplayModes();
		for (int i = 0; i < awt_modes.length; i++)
			if (equals(awt_modes[i], mode)) {
				requested_mode = awt_modes[i];
				return;
			}
		throw new LWJGLException(mode + " is not supported");
	}

	public void resetDisplayMode() {
		if (MacOSXFrame.getDevice().getFullScreenWindow() != null)
			MacOSXFrame.getDevice().setFullScreenWindow(null);
		requested_mode = null;
		restoreGamma();
	}

	private native void restoreGamma();

	private DisplayMode createLWJGLDisplayMode(java.awt.DisplayMode awt_mode) {
		int bit_depth;
		int refresh_rate;
		int awt_bit_depth = awt_mode.getBitDepth();
		int awt_refresh_rate = awt_mode.getRefreshRate();
		if (awt_bit_depth != java.awt.DisplayMode.BIT_DEPTH_MULTI)
			bit_depth = awt_bit_depth;
		else
			bit_depth = 32; // Assume the best bit depth
		if (awt_refresh_rate != java.awt.DisplayMode.REFRESH_RATE_UNKNOWN)
			refresh_rate = awt_refresh_rate;
		else
			refresh_rate = 0;
		return new DisplayMode(awt_mode.getWidth(), awt_mode.getHeight(), bit_depth, refresh_rate);
	}

	public DisplayMode init() {
		return createLWJGLDisplayMode(MacOSXFrame.getDevice().getDisplayMode());
	}

	public DisplayMode[] getAvailableDisplayModes() {
		java.awt.DisplayMode[] awt_modes = MacOSXFrame.getDevice().getDisplayModes();
		List modes = new ArrayList();
		for (int i = 0; i < awt_modes.length; i++)
			if (awt_modes[i].getBitDepth() >= 16)
				modes.add(createLWJGLDisplayMode(awt_modes[i]));
		DisplayMode[] mode_list = new DisplayMode[modes.size()];
		modes.toArray(mode_list);
		return mode_list;
	}

	public void setTitle(String title) {
		frame.syncSetTitle(title);
	}

	public boolean isCloseRequested() {
		boolean result;
		synchronized (this) {
			result = close_requested;
			close_requested = false;
		}
		return result;
	}

	public boolean isVisible() {
		return frame.syncIsVisible();
	}

	public boolean isActive() {
		return frame.syncIsActive();
	}

	public boolean isDirty() {
		return frame.getCanvas().syncIsDirty();
	}

	public native void setView(MacOSXGLCanvas canvas);

	public native void swapBuffers();

	public native void makeCurrent() throws LWJGLException;

	public native void createContext(PixelFormat pixel_format) throws LWJGLException;

	public native void destroyContext();

	public void update() {
		if (frame.syncShouldUpdateContext()) {
			updateContext();
			/* This is necessary to make sure the context won't "forget" about the view size */
			GL11.glViewport(0, 0, frame.getCanvas().syncGetWidth(), frame.getCanvas().syncGetHeight());
			warpCursor();
		}
	}

	private void warpCursor() {
		if (mouse_queue != null && mouse_queue.isGrabbed()) {
			Rectangle bounds = frame.syncGetBounds();
			int x = bounds.x + bounds.width/2;
			int y = bounds.y + bounds.height/2;
			nWarpCursor(x, y);
		}
	}

	/**
	 * This is an interface to the native Carbon call
	 * SetSystemUIMode. It is used to hide the dock in a way
	 * that will prevent AWT from shifting the fullscreen window
	 */
	private native void hideUI(boolean hide);

	native void getMouseDeltas(IntBuffer delta_buffer);

	private native void updateContext();

	public native void setVSyncEnabled(boolean sync);

	public void reshape(int x, int y, int width, int height) {
		frame.syncReshape(x, y, width, height);
	}

	/* Mouse */
	public boolean hasWheel() {
		return true;
	}

	public int getButtonCount() {
		return MouseEventQueue.NUM_BUTTONS;
	}

	public void createMouse() {
		MacOSXGLCanvas canvas = frame.getCanvas();
		this.mouse_queue = new MouseEventQueue(canvas.getWidth(), canvas.getHeight());
		canvas.addMouseListener(mouse_queue);
		canvas.addMouseMotionListener(mouse_queue);
		canvas.addMouseWheelListener(mouse_queue);
	}

	public void destroyMouse() {
		MacOSXGLCanvas canvas = frame.getCanvas();
		canvas.removeMouseListener(mouse_queue);
		canvas.removeMouseWheelListener(mouse_queue);
		canvas.removeMouseMotionListener(mouse_queue);
		this.mouse_queue = null;
	}

	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
		mouse_queue.poll(coord_buffer, buttons_buffer);
	}

	public int readMouse(IntBuffer buffer, int buffer_position) {
		assert buffer_position == buffer.position();
		return mouse_queue.copyEvents(buffer);
	}

	public void grabMouse(boolean grab) {
		mouse_queue.setGrabbed(grab);
		warpCursor();
		nGrabMouse(grab);
	}

	private native void nWarpCursor(int x, int y);

	private native void nGrabMouse(boolean grab);

	public int getNativeCursorCapabilities() {
		/*
		int cursor_colors = Toolkit.getDefaultToolkit().getMaximumCursorColors();
		boolean supported = cursor_colors >= Short.MAX_VALUE && getMaxCursorSize() > 0;
		int caps = supported ? Mouse.CURSOR_8_BIT_ALPHA | Mouse.CURSOR_ONE_BIT_TRANSPARENCY: 0;
		return caps;
		*/
		/* Return no capability, as there are two unsolved bugs (both reported to apple along with
		   minimal test case):
		   1. When a custom cursor (or some standard) java.awt.Cursor is assigned to a
		      Componennt, it is reset to the default pointer cursor when the window is de-
			  activated and the re-activated. The Cursor can not be reset to the custom cursor,
			  with another setCursor.
		   2. When the cursor is moving in the top pixel row (y = 0 in AWT coordinates) in fullscreen
		   	  mode, no mouse moved events are reported, even though mouse pressed/released and dragged
			  events are reported
		*/
		return 0;
	}

	public void setNativeCursor(Object handle) throws LWJGLException {
		Cursor awt_cursor = (Cursor)handle;
		frame.syncSetCursor(awt_cursor);
	}

	public int getMinCursorSize() {
		Dimension min_size = Toolkit.getDefaultToolkit().getBestCursorSize(0, 0);
		return Math.max(min_size.width, min_size.height);
	}

	public int getMaxCursorSize() {
		Dimension max_size = Toolkit.getDefaultToolkit().getBestCursorSize(10000, 10000);
		return Math.min(max_size.width, max_size.height);
	}

	/* Keyboard */
	public void createKeyboard() throws LWJGLException {
		MacOSXGLCanvas canvas = frame.getCanvas();
		this.keyboard_queue = new KeyboardEventQueue();
		canvas.addKeyListener(keyboard_queue);
	}

	public void destroyKeyboard() {
		/*
		 * This line is commented out to work around AWT bug 4867453:
		 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4867453
		 */
//		frame.getCanvas().removeKeyListener(keyboard_queue);

		this.keyboard_queue = null;
	}

	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		keyboard_queue.poll(keyDownBuffer);
	}

	public int readKeyboard(IntBuffer buffer, int buffer_position) {
		assert buffer_position == buffer.position();
		return keyboard_queue.copyEvents(buffer);
	}

	public int isStateKeySet(int key) {
		return Keyboard.STATE_UNKNOWN;
	}

	/** Native cursor handles */
	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		BufferedImage cursor_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int[] pixels = new int[images.remaining()];
		int old_position = images.position();
		images.get(pixels);
		images.position(old_position);
		cursor_image.setRGB(0, 0, width, height, pixels, 0, width);
		return Toolkit.getDefaultToolkit().createCustomCursor(cursor_image, new Point(xHotspot, yHotspot), "LWJGL Custom cursor");
	}

	public void destroyCursor(Object cursor_handle) {
	}

	public int getPbufferCapabilities() {
		return GL11.glGetString(GL11.GL_EXTENSIONS).indexOf("GL_APPLE_pixel_buffer") != -1 ? Pbuffer.PBUFFER_SUPPORTED : 0;
	}

	/* Use the com.apple.eio.FileManager Mac OS X extension to show the given URL */
	public boolean openURL(String url) {
		try {
			Class com_apple_eio_FileManager = Class.forName("com.apple.eio.FileManager");
			Method openURL_method = com_apple_eio_FileManager.getMethod("openURL", new Class[]{String.class});
			openURL_method.invoke(null, new Object[]{url});
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This class captures com.apple.eawt.ApplicationEvents through reflection
	 * to enable compilation on other platforms than Mac OS X
	 */
	private class MacOSXApplicationListener implements InvocationHandler {
		private final Method handleQuit;

		public MacOSXApplicationListener() {
			try {
				/* Get the com.apple.eawt.Application class */
				Class com_apple_eawt_Application = Class.forName("com.apple.eawt.Application");
				/* Call the static Application.getApplication() method */
				Object application = com_apple_eawt_Application.getMethod("getApplication", null).invoke(null, null);
				/* Create a proxy implementing com.apple.eawt.ApplicationListener */
				Class com_apple_eawt_ApplicationListener = Class.forName("com.apple.eawt.ApplicationListener");
				Object listener_proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {com_apple_eawt_ApplicationListener}, this);
				/* Invoke the method application.addApplicationListener(proxy) */
				Method addApplicationListener = com_apple_eawt_Application.getMethod("addApplicationListener", new Class[]{com_apple_eawt_ApplicationListener});
				addApplicationListener.invoke(application, new Object[]{listener_proxy});
				/* Finally, get the handleQuit method we want to react to */
				Class com_apple_eawt_ApplicationEvent = Class.forName("com.apple.eawt.ApplicationEvent");
				handleQuit = com_apple_eawt_ApplicationListener.getMethod("handleQuit", new Class[]{com_apple_eawt_ApplicationEvent});
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		public Object invoke(Object proxy, Method method, Object[] args) {
			if (method.equals(handleQuit))
				handleQuit();
			return null;
		}
	}

	public boolean isBufferLost(ByteBuffer handle) {
		return false;
	}
	
	public native void makePbufferCurrent(ByteBuffer handle) throws LWJGLException;

	public ByteBuffer createPbuffer(int width, int height, PixelFormat pixel_format,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs, ByteBuffer shared_pbuffer_handle) throws LWJGLException {
		ByteBuffer handle = BufferUtils.createByteBuffer(PBUFFER_HANDLE_SIZE);
		nCreatePbuffer(handle, width, height, pixel_format, pixelFormatCaps, pBufferAttribs, shared_pbuffer_handle);
		return handle;
	}

	private native void nCreatePbuffer(ByteBuffer handle, int width, int height, PixelFormat pixel_format,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs, ByteBuffer shared_pbuffer_handle) throws LWJGLException;

	public native void destroyPbuffer(ByteBuffer handle);

	public void setPbufferAttrib(ByteBuffer handle, int attrib, int value) {
		throw new UnsupportedOperationException();
	}

	public void bindTexImageToPbuffer(ByteBuffer handle, int buffer) {
		throw new UnsupportedOperationException();
	}

	public void releaseTexImageFromPbuffer(ByteBuffer handle, int buffer) {
		throw new UnsupportedOperationException();
	}
}
