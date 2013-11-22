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
 * 
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author mojang
 * @author kappaOne <one.kappa@gmail.com>
 */

import java.awt.Canvas;
import java.awt.Robot;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Cursor;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.BufferUtils;
import org.lwjgl.MemoryUtil;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

import static org.lwjgl.opengl.GL11.*;

final class MacOSXDisplay implements DisplayImplementation {
	private static final int PBUFFER_HANDLE_SIZE = 24;
	private static final int GAMMA_LENGTH = 256;

	//private MacOSXCanvasListener canvas_listener;
	private Canvas canvas;
	private Robot robot;
	private MacOSXMouseEventQueue mouse_queue;
	private KeyboardEventQueue keyboard_queue;
	private DisplayMode requested_mode;
	
	/* Members for native window use */
	private MacOSXNativeMouse mouse;
	private MacOSXNativeKeyboard keyboard;
	private ByteBuffer window;
	private ByteBuffer context;
	
	private boolean skipViewportValue = false;
	private static final IntBuffer current_viewport = BufferUtils.createIntBuffer(16);
	
	private boolean mouseInsideWindow;
	
	private boolean close_requested;
	
	private boolean native_mode = true;
	
	private boolean updateNativeCursor = false;
	
	private long currentNativeCursor = 0;
	
	private boolean enableHighDPI = false;
	
	private float scaleFactor = 1.0f;

	MacOSXDisplay() {
		
	}

	private native ByteBuffer nCreateWindow(int x, int y, int width, int height, boolean fullscreen, boolean undecorated, boolean resizable, boolean parented, boolean enableFullscreenModeAPI, boolean enableHighDPI, ByteBuffer peer_info_handle, ByteBuffer window_handle) throws LWJGLException;

	private native Object nGetCurrentDisplayMode();
	
	private native void nGetDisplayModes(Object modesList);
	
	private native boolean nIsMiniaturized(ByteBuffer window_handle);
    
	private native boolean nIsFocused(ByteBuffer window_handle);
    
	private native void nSetResizable(ByteBuffer window_handle, boolean resizable);
    
	private native void nResizeWindow(ByteBuffer window_handle, int x, int y, int width, int height);
    
	private native boolean nWasResized(ByteBuffer window_handle);
	
	private native int nGetX(ByteBuffer window_handle);
	
	private native int nGetY(ByteBuffer window_handle);
	
	private native int nGetWidth(ByteBuffer window_handle);
	
	private native int nGetHeight(ByteBuffer window_handle);
	
	private native boolean nIsNativeMode(ByteBuffer peer_info_handle);
    
	private static boolean isUndecorated() {
		return Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
	}
    
	public void createWindow(final DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
		boolean fullscreen = Display.isFullscreen();
		boolean resizable = Display.isResizable();
		boolean parented = (parent != null) && !fullscreen;
		
		// OS X fullscreen mode API is only available on OS X 10.7+
		boolean enableFullscreenModeAPI = LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 7) && parent == null &&
											!Display.getPrivilegedBoolean("org.lwjgl.opengl.Display.disableOSXFullscreenModeAPI");
		
		// OS X high DPI mode is only available on OS X 10.7+
		enableHighDPI = LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 7) && parent == null &&
											(Display.getPrivilegedBoolean("org.lwjgl.opengl.Display.enableHighDPI") || fullscreen);
		
		if (parented) this.canvas = parent;
		else this.canvas = null;
		
		close_requested = false;
		
		DrawableGL gl_drawable = (DrawableGL)Display.getDrawable();
		PeerInfo peer_info = gl_drawable.peer_info;
		ByteBuffer peer_handle = peer_info.lockAndGetHandle();
		ByteBuffer window_handle = parented ? ((MacOSXCanvasPeerInfo)peer_info).window_handle : window;
		
		try {
			
			window = nCreateWindow(x, y, mode.getWidth(), mode.getHeight(),
					fullscreen, isUndecorated(), resizable,
					parented, enableFullscreenModeAPI, enableHighDPI, peer_handle, window_handle);
            
			if (fullscreen) {
				// when going to fullscreen viewport is set to screen size by Cocoa, ignore this value
				skipViewportValue = true;
				// if starting in fullscreen then set initial viewport to displaymode size
				if (current_viewport.get(2) == 0 && current_viewport.get(3) == 0) {
					current_viewport.put(2, mode.getWidth());
					current_viewport.put(3, mode.getHeight());
				}
			}
			
			native_mode = nIsNativeMode(peer_handle);
			
			if (!native_mode) {
				robot = AWTUtil.createRobot(canvas);
			}
			
		} catch (LWJGLException e) {
			destroyWindow();
			throw e;
		} finally {
			peer_info.unlock();
		}
	}

	public void doHandleQuit() {
		synchronized (this) {
			close_requested = true;
		}
	}
	
	public void mouseInsideWindow(boolean inside) {
		synchronized (this) {
			mouseInsideWindow = inside;
		}
		updateNativeCursor = true;
	}
	
	public void setScaleFactor(float scale) {
		synchronized (this) {
			scaleFactor = scale;
		}
	}

	public native void nDestroyCALayer(ByteBuffer peer_info_handle);
	
	public native void nDestroyWindow(ByteBuffer window_handle);

	public void destroyWindow() {
		
		if (!native_mode) {
			DrawableGL gl_drawable = (DrawableGL)Display.getDrawable();
			PeerInfo peer_info = gl_drawable.peer_info;
			if (peer_info != null) {
				ByteBuffer peer_handle = peer_info.getHandle();
				nDestroyCALayer(peer_handle);
			}
			robot = null;
		}
		
		nDestroyWindow(window);
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
	
	private static boolean equals(DisplayMode mode1, DisplayMode mode2) {
		return mode1.getWidth() == mode2.getWidth() && mode1.getHeight() == mode2.getHeight()
			&& mode1.getBitsPerPixel() == mode2.getBitsPerPixel() && mode1.getFrequency() == mode2.getFrequency();
	}

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		DisplayMode[] modes = getAvailableDisplayModes();
		
		for (DisplayMode available_mode : modes) {
			if (equals(available_mode, mode)) {
				requested_mode = available_mode;
				return;
			}
		}
		
		throw new LWJGLException(mode + " is not supported");
	}

	public void resetDisplayMode() {
        requested_mode = null;
		restoreGamma();
	}

	private native void restoreGamma();

	public Object createDisplayMode(int width, int height, int bitsPerPixel, int refreshRate) {
		return new DisplayMode(width, height, bitsPerPixel, refreshRate);
	}
	
	public DisplayMode init() throws LWJGLException {
		return (DisplayMode) nGetCurrentDisplayMode();
	}
	
	public void addDisplayMode(Object modesList, int width, int height, int bitsPerPixel, int refreshRate) {
		List<DisplayMode> modes = (List<DisplayMode>) modesList;
		DisplayMode displayMode = new DisplayMode(width, height, bitsPerPixel, refreshRate);
		modes.add(displayMode);
	}

	public DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
		List<DisplayMode> modes = new ArrayList<DisplayMode>();
		nGetDisplayModes(modes); // will populate the above list
		modes.add(Display.getDesktopDisplayMode()); // add desktop resolution as scaled resolutions do not appear
		return modes.toArray(new DisplayMode[modes.size()]);
	}

	private native void nSetTitle(ByteBuffer window_handle, ByteBuffer title_buffer);

	public void setTitle(String title) {
		ByteBuffer buffer = MemoryUtil.encodeUTF8(title);
		nSetTitle(window, buffer);
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
        return true;
	}

	public boolean isActive() {
		if (native_mode) {
			return nIsFocused(window);
		}
		else {
			return Display.getParent().hasFocus();
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public boolean isDirty() {
		return false;
	}

	public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
		try {
			return new MacOSXDisplayPeerInfo(pixel_format, attribs, true);
		} catch (LWJGLException e) {
			return new MacOSXDisplayPeerInfo(pixel_format, attribs, false);
		}
	}

	public void update() {
		boolean should_update = true;
		
		DrawableGL drawable = (DrawableGL)Display.getDrawable();
		if (should_update) {
			drawable.context.update();
			/* This is necessary to make sure the context won't "forget" about the view size */
			if (skipViewportValue) skipViewportValue = false;
			else glGetInteger(GL_VIEWPORT, current_viewport);
			glViewport(current_viewport.get(0), current_viewport.get(1), current_viewport.get(2), current_viewport.get(3));
		}
		
		if (native_mode && updateNativeCursor) {
			updateNativeCursor = false;
			try {
				setNativeCursor(currentNativeCursor);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
	}

	public void reshape(int x, int y, int width, int height) {
        //if (native_mode) {
        //    nResizeWindow(window, x, y, width, height);
        //}
	}

	/* Mouse */
	public boolean hasWheel() {
		return AWTUtil.hasWheel();
	}

	public int getButtonCount() {
		return AWTUtil.getButtonCount();
	}

	public void createMouse() throws LWJGLException {
		if (native_mode) {
			mouse = new MacOSXNativeMouse(this, window);
			mouse.register();
		}
		else {
			this.mouse_queue = new MacOSXMouseEventQueue(canvas);
			mouse_queue.register();
		}
	}

	public void destroyMouse() {
		if (native_mode) {
			// restore default native cursor
			try {
				MacOSXNativeMouse.setCursor(0);
			} catch (LWJGLException e) {};
			
			// release any mouse grab
			grabMouse(false);
			
			if (mouse != null) {
				mouse.unregister();
			}
			mouse = null;
		}
		else {
			if (mouse_queue != null) {
				MacOSXMouseEventQueue.nGrabMouse(false);
				mouse_queue.unregister();
			}
			this.mouse_queue = null;
		}
	}

	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
		if (native_mode) {
			mouse.poll(coord_buffer, buttons_buffer);
		}
		else {
			mouse_queue.poll(coord_buffer, buttons_buffer);
		}
	}

	public void readMouse(ByteBuffer buffer) {
		if (native_mode) {
			mouse.copyEvents(buffer);
		}
		else {
			mouse_queue.copyEvents(buffer);
		}
	}

	public void grabMouse(boolean grab) {
		if (native_mode) {
			mouse.setGrabbed(grab);
		}
		else {
			mouse_queue.setGrabbed(grab);
		}
	}

	public int getNativeCursorCapabilities() {
		if (native_mode) {
			return Cursor.CURSOR_ONE_BIT_TRANSPARENCY | Cursor.CURSOR_8_BIT_ALPHA | Cursor.CURSOR_ANIMATION;
		}
		
		return AWTUtil.getNativeCursorCapabilities();
	}

	public void setCursorPosition(int x, int y) {
		if (native_mode) {
			if (mouse != null) {
				mouse.setCursorPosition(x, y);
			}
		}
		//else {
			//MacOSXMouseEventQueue.nWarpCursor(x, y);
		//}
	}

	public void setNativeCursor(Object handle) throws LWJGLException {
		if (native_mode) {
			currentNativeCursor = getCursorHandle(handle);
			if (Display.isCreated()) {
				if (mouseInsideWindow) MacOSXNativeMouse.setCursor(currentNativeCursor);
				else MacOSXNativeMouse.setCursor(0); // restore default cursor if outside Display
			}
		}
	}

	public int getMinCursorSize() {
		return 1;
	}

	public int getMaxCursorSize() {
		// as there is no max cursor size limit on OS X
		// return the max cursor size as half the screen resolution
		DisplayMode dm = Display.getDesktopDisplayMode();
		return Math.min(dm.getWidth(), dm.getHeight()) / 2;
	}

	/* Keyboard */
	public void createKeyboard() throws LWJGLException {
		if (native_mode) {
			this.keyboard = new MacOSXNativeKeyboard(window);
			keyboard.register();
		}
		else {
			this.keyboard_queue = new KeyboardEventQueue(canvas);
			keyboard_queue.register();
		}
	}

	public void destroyKeyboard() {
		if (native_mode) {
			if (keyboard != null) {
				keyboard.unregister();
			}
			keyboard = null;
		}
		else {
			if (keyboard_queue != null) {
				keyboard_queue.unregister();
			}
			this.keyboard_queue = null;
		}
	}

	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		if (native_mode) {
			keyboard.poll(keyDownBuffer);
		}
		else {
			keyboard_queue.poll(keyDownBuffer);
		}
	}

	public void readKeyboard(ByteBuffer buffer) {
		if (native_mode) {
			keyboard.copyEvents(buffer);
		}
		else {
			keyboard_queue.copyEvents(buffer);
		}
	}
	
	/** Native cursor handles */
	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		if (native_mode) {
			long cursor = MacOSXNativeMouse.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
			return cursor;
		}
		else {
			return AWTUtil.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
		}
	}

	public void destroyCursor(Object cursor_handle) {
		long handle = getCursorHandle(cursor_handle);
		
		// reset current cursor if same
		if (currentNativeCursor == handle) {
			currentNativeCursor = 0;
		}
		
		MacOSXNativeMouse.destroyCursor(handle);
	}
	
	private static long getCursorHandle(Object cursor_handle) {
		return cursor_handle != null ? (Long)cursor_handle : 0;
	}

	public int getPbufferCapabilities() {
		return Pbuffer.PBUFFER_SUPPORTED;
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
		/*int size = 0;
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
		return nGetX(window);
	}

	public int getY() {
		return nGetY(window);
	}

	public int getWidth() {
		return nGetWidth(window);
	}
	
	public int getHeight() {
		return nGetHeight(window);
	}

	public boolean isInsideWindow() {
		return mouseInsideWindow;
	}

	public void setResizable(boolean resizable) {
		nSetResizable(window, resizable);
	}

	public boolean wasResized() {
		return nWasResized(window);
	}
	
	public float getPixelScaleFactor() {
		return (enableHighDPI && !Display.isFullscreen()) ? scaleFactor : 1f;
	}

}
