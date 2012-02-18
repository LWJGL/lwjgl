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
 * This is the Display implementation interface. Display delegates
 * to implementors of this interface. There is one DisplayImplementation
 * for each supported platform.
 * @author elias_naur
 */

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Robot;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

import com.apple.eawt.Application;
import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;

import static org.lwjgl.opengl.GL11.*;

final class MacOSXDisplay implements DisplayImplementation {
	private static final int PBUFFER_HANDLE_SIZE = 24;
	private static final int GAMMA_LENGTH = 256;

	private MacOSXCanvasListener canvas_listener;
	private MacOSXFrame frame;
	private Canvas canvas;
	private Robot robot;
	private MacOSXMouseEventQueue mouse_queue;
	private KeyboardEventQueue keyboard_queue;
	private java.awt.DisplayMode requested_mode;

	/* States */
	private boolean close_requested;

	MacOSXDisplay() {
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
				public Object run() throws Exception {
					Application.getApplication().addApplicationListener(new ApplicationAdapter() {
						public void handleQuit(ApplicationEvent event) {
							doHandleQuit();
						}
					});
					return null;
				}
			});
		} catch (Throwable e) {
			/**
			 * In an applet environment, referencing com.apple.eawt.Application can fail with
			 * a native exception. So log any exceptions instead of re-throwing.
			 */
			LWJGLUtil.log("Failed to register quit handler: " + e.getMessage());
		}
	}

	public void createWindow(final DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
		boolean fullscreen = Display.isFullscreen();
		hideUI(fullscreen);
		close_requested = false;
		try {
			if (parent == null) {
				frame = new MacOSXFrame(mode, requested_mode, fullscreen, x, y);
				canvas = frame.getCanvas();
			} else {
				frame = null;
				canvas = parent;
			}
			canvas_listener = new MacOSXCanvasListener(canvas);
			robot = AWTUtil.createRobot(canvas);
		} catch (LWJGLException e) {
			destroyWindow();
			throw e;
		}
	}

	private void doHandleQuit() {
		synchronized (this) {
			close_requested = true;
		}
	}

	public void destroyWindow() {
		if (canvas_listener != null) {
			canvas_listener.disableListeners();
			canvas_listener = null;
		}
		if (frame != null) {
			AccessController.doPrivileged(new PrivilegedAction<Object>() {
				public Object run() {
					if (MacOSXFrame.getDevice().getFullScreenWindow() == frame)
						MacOSXFrame.getDevice().setFullScreenWindow(null);
					return null;
				}
			});
			if (frame.isDisplayable())
				frame.dispose();
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

	private static boolean equals(java.awt.DisplayMode awt_mode, DisplayMode mode) {
		return awt_mode.getWidth() == mode.getWidth() && awt_mode.getHeight() == mode.getHeight()
			&& awt_mode.getBitDepth() == mode.getBitsPerPixel() && awt_mode.getRefreshRate() == mode.getFrequency();
	}

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		java.awt.DisplayMode[] awt_modes = MacOSXFrame.getDevice().getDisplayModes();
		for ( java.awt.DisplayMode awt_mode : awt_modes ) {
			if (equals(awt_mode, mode)) {
				requested_mode = awt_mode;
				return;
			}
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

	private static DisplayMode createLWJGLDisplayMode(java.awt.DisplayMode awt_mode) {
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

	public DisplayMode init() throws LWJGLException {
		return createLWJGLDisplayMode(MacOSXFrame.getDevice().getDisplayMode());
	}

	public DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
		java.awt.DisplayMode[] awt_modes = MacOSXFrame.getDevice().getDisplayModes();
		List<DisplayMode> modes = new ArrayList<DisplayMode>();
		for ( java.awt.DisplayMode awt_mode : awt_modes )
			if ( awt_mode.getBitDepth() >= 16 )
				modes.add(createLWJGLDisplayMode(awt_mode));
		return modes.toArray(new DisplayMode[modes.size()]);
	}

	public void setTitle(String title) {
		if (frame != null)
			frame.setTitle(title);
	}

	public boolean isCloseRequested() {
		boolean result;
		synchronized (this) {
			result = close_requested || (frame != null && frame.syncIsCloseRequested());
			close_requested = false;
		}
		return result;
	}

	public boolean isVisible() {
		return frame == null || frame.syncIsVisible();
	}

	public boolean isActive() {
		return canvas.isFocusOwner();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public boolean isDirty() {
		return frame != null && frame.getCanvas().syncIsDirty();
	}

	public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
		try {
			return new MacOSXDisplayPeerInfo(pixel_format, attribs, true);
		} catch (LWJGLException e) {
			return new MacOSXDisplayPeerInfo(pixel_format, attribs, false);
		}
	}

	private static final IntBuffer current_viewport = BufferUtils.createIntBuffer(16);
	public void update() {
		boolean should_update = canvas_listener.syncShouldUpdateContext();
		/*
		 * Workaround for the "white screen in fullscreen mode" problem
		 *
		 * Sometimes, switching from windowed mode to fullscreen or simply creating the Display
		 * in fullscreen mode will result in the context not being bound to the window correctly.
		 * The program runs fine, but the canvas background (white) is shown instead of the context
		 * front buffer.
		 *
		 * I've discovered that re-binding the context with another setView() won't fix the problem, while a
		 * clearDrawable() followed by a setView() does work. A straightforward workaround would be
		 * to simply run the combination at every update(). This is not sufficient, since the clearDrawable()
		 * call makes the the background appear shortly, causing visual artifacts.
		 * What we really need is a way to detect that a setView() failed, and then do the magic combo. I've not
		 * been able to find such a detection so alternatively I'm triggering the combo if the display is fullscreen
		 * (I've not seen the white screen problem in windowed mode) and if the canvas has gotten a paint message or
		 * if its update flag was set.
		 *
		 * My testing seems to indicate that the workaround works, since I've not seen the problem after the fix.
		 *
		 * - elias
		 */
		DrawableGL drawable = (DrawableGL)Display.getDrawable();
		if (Display.isFullscreen() && (frame != null && frame.getCanvas().syncCanvasPainted() || should_update)) {
			try {
				MacOSXContextImplementation.resetView(drawable.peer_info, drawable.context);
			} catch (LWJGLException e) {
				LWJGLUtil.log("Failed to reset context: " + e);
			}
		}
		if (should_update) {
			drawable.context.update();
			/* This is necessary to make sure the context won't "forget" about the view size */
			glGetInteger(GL_VIEWPORT, current_viewport);
			glViewport(current_viewport.get(0), current_viewport.get(1), current_viewport.get(2), current_viewport.get(3));
		}
		if (frame != null && mouse_queue != null) {
			if (frame.syncShouldReleaseCursor())
				MacOSXMouseEventQueue.nGrabMouse(false);
			if (frame.syncShouldWarpCursor())
				mouse_queue.warpCursor();
		}
	}

	/**
	 * This is an interface to the native Carbon call
	 * SetSystemUIMode. It is used to hide the dock in a way
	 * that will prevent AWT from shifting the fullscreen window
	 *
	 * The workaround is not necessary on 10.4, and since 10.4 shows
	 * instability problems calling SetSystemUIMode, we'll only call it
	 * when the OS version is 10.3 or lower.
	 */
	private void hideUI(boolean hide) {
		if (!LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 4))
			nHideUI(hide);
	}

	private native void nHideUI(boolean hide);

	public void reshape(int x, int y, int width, int height) {
		if (frame != null)
			frame.resize(x, y, width, height);
	}

	/* Mouse */
	public boolean hasWheel() {
		return AWTUtil.hasWheel();
	}

	public int getButtonCount() {
		return AWTUtil.getButtonCount();
	}

	public void createMouse() throws LWJGLException {
		this.mouse_queue = new MacOSXMouseEventQueue(canvas);
		mouse_queue.register();
	}

	public void destroyMouse() {
		if (mouse_queue != null) {
			MacOSXMouseEventQueue.nGrabMouse(false);
			mouse_queue.unregister();
		}
		this.mouse_queue = null;
	}

	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
		mouse_queue.poll(coord_buffer, buttons_buffer);
	}

	public void readMouse(ByteBuffer buffer) {
		mouse_queue.copyEvents(buffer);
	}

	public void grabMouse(boolean grab) {
		mouse_queue.setGrabbed(grab);
	}

	public int getNativeCursorCapabilities() {
		return AWTUtil.getNativeCursorCapabilities();
	}

	public void setCursorPosition(int x, int y) {
		AWTUtil.setCursorPosition(canvas, robot, x, y);
	}

	public void setNativeCursor(Object handle) throws LWJGLException {
		Cursor awt_cursor = (Cursor)handle;
		if (frame != null)
			frame.setCursor(awt_cursor);
	}

	public int getMinCursorSize() {
		return AWTUtil.getMinCursorSize();
	}

	public int getMaxCursorSize() {
		return AWTUtil.getMaxCursorSize();
	}

	/* Keyboard */
	public void createKeyboard() throws LWJGLException {
		this.keyboard_queue = new KeyboardEventQueue(canvas);
		keyboard_queue.register();
	}

	public void destroyKeyboard() {
		if (keyboard_queue != null)
			keyboard_queue.unregister();
		this.keyboard_queue = null;
	}

	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		keyboard_queue.poll(keyDownBuffer);
	}

	public void readKeyboard(ByteBuffer buffer) {
		keyboard_queue.copyEvents(buffer);
	}

/*	public int isStateKeySet(int key) {
		int awt_key;
		switch (key) {
			case Keyboard.KEY_CAPITAL:
				awt_key = KeyEvent.VK_CAPS_LOCK;
				break;
			case Keyboard.KEY_NUMLOCK:
				awt_key = KeyEvent.VK_NUM_LOCK;
				break;
			case Keyboard.KEY_SYSRQ:
				awt_key = KeyEvent.VK_SCROLL_LOCK;
				break;
			default:
				return Keyboard.STATE_UNKNOWN;
		}
		try {
			boolean state = Toolkit.getDefaultToolkit().getLockingKeyState(awt_key);
			return state ? Keyboard.STATE_ON : Keyboard.STATE_OFF;
		} catch (Exception e) {
			LWJGLUtil.log("Failed to query key state: " + e);
			return Keyboard.STATE_UNKNOWN;
		}
	}
*/
	/** Native cursor handles */
	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		return AWTUtil.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
	}

	public void destroyCursor(Object cursor_handle) {
	}

	public int getPbufferCapabilities() {
		if (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 3))
			return Pbuffer.PBUFFER_SUPPORTED;
		else
			return 0;
	}

	public boolean isBufferLost(PeerInfo handle) {
		return false;
	}

	public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs) throws LWJGLException {
		return new MacOSXPbufferPeerInfo(width, height, pixel_format, attribs);
	}

	public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
		throw new UnsupportedOperationException();
	}

	public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
		throw new UnsupportedOperationException();
	}

	public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets one or more icons for the Display.
	 * <ul>
	 * <li>On Windows you should supply at least one 16x16 icon and one 32x32.</li>
	 * <li>Linux (and similar platforms) expect one 32x32 icon.</li>
	 * <li>Mac OS X should be supplied one 128x128 icon</li>
	 * </ul>
	 * The implementation will use the supplied ByteBuffers with image data in RGBA and perform any conversions nescesarry for the specific platform.
	 *
	 * @param icons Array of icons in RGBA mode
	 * @return number of icons used.
	 */
	public int setIcon(ByteBuffer[] icons) {
/*		int size = 0;
		int biggest = -1;

		for (int i=0;i<icons.length;i++) {
			if (icons[i].remaining() > size) {
				biggest = i;
				size = icons[i].remaining();
			}
		}

		if (biggest == -1) {
			return 0;
		}

		int width;
		int height;

		IntBuffer biggest_icon = icons[biggest].asIntBuffer();
		int[] imageData = new int[biggest_icon.remaining()];
		width = height = (int) Math.sqrt(imageData.length);
		biggest_icon.get(imageData);

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, width, height, imageData, 0, width);
		frame.setIconImage(img);

		return 1;*/
		// Don't use any icon, since Mac OS X windows don't have window icons
		return 0;
	}
	
	public int getX() {
		return frame.getX();
	}

	public int getY() {
		return frame.getY();
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getHeight() {
		return frame.getHeight();
	}

    public boolean isInsideWindow() {
    		return true;
    }

    public void setResizable(boolean resizable) {
		frame.setResizable(resizable);
	}

	public boolean wasResized() {
		return canvas_listener.wasResized();
	}

}
