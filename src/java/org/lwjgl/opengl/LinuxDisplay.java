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

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Keyboard;

final class LinuxDisplay implements DisplayImplementation {
	/** Window mode enum */
	private static final int FULLSCREEN_LEGACY = 1;
	private static final int FULLSCREEN_NETWM = 2;
	private static final int WINDOWED = 3;

	/** Current window mode */
	private static int current_window_mode = WINDOWED;
	
	/** Display mode switching API */
	private static final int XRANDR = 10;
	private static final int XF86VIDMODE = 11;
	private static final int NONE = 12;
	
	/** Current mode swithcing API */
	private static int current_displaymode_extension = NONE;

	private static final int NUM_BUTTONS = 3;

	/** Keep track on the current awt lock owner to avoid
	 * depending on JAWT locking to be re-entrant (This is a
	 * problem with GCJ). JAWT locking is not that well specified
	 * anyway so it is probably best to avoid assuming too much
	 * about it.
	 */
	private static Thread current_awt_lock_owner;
	private static int awt_lock_count;
	
	private static int display_connection_usage_count = 0;

	private static PeerInfo peer_info;

	private static int getBestDisplayModeExtension() throws LWJGLException {
		if (System.getenv("LWJGL_DISABLE_XRANDR") == null && isXrandrSupported()) {
			LWJGLUtil.log("Using Xrandr for display mode switching");
			return XRANDR;
		} else if (isXF86VidModeSupported()) {
			LWJGLUtil.log("Using XF86VidMode for display mode switching");
			return XF86VIDMODE;
		} else {
			LWJGLUtil.log("No display mode extensions available");
			return NONE;
		}

	}
	private static native boolean isXrandrSupported() throws LWJGLException;
	private static native boolean isXF86VidModeSupported() throws LWJGLException;

	private static boolean isNetWMFullscreenSupported() throws LWJGLException {
		if (System.getenv("LWJGL_DISABLE_NETWM") != null)
			return false;
		lockAWT();
		try {
			incDisplay();
			boolean supported = nIsNetWMFullscreenSupported();
			decDisplay();
			return supported;
		} finally {
			unlockAWT();
		}
	}
	private static native boolean nIsNetWMFullscreenSupported();
	
	/* Since Xlib is not guaranteed to be thread safe, we need a way to synchronize LWJGL
	 * Xlib calls with AWT Xlib calls. Fortunately, JAWT implements Lock()/Unlock() to
	 * do just that.
	 */
	static synchronized void lockAWT() {
		Thread this_thread = Thread.currentThread();
		while (current_awt_lock_owner != null && current_awt_lock_owner != this_thread) {
			try {
				LinuxDisplay.class.wait();
			} catch (InterruptedException e) {
				LWJGLUtil.log("Interrupted while waiting for awt lock: " + e);
			}
		}
		if (awt_lock_count == 0) {
			current_awt_lock_owner = this_thread;
			try {
				nLockAWT();
			} catch (LWJGLException e) {
				LWJGLUtil.log("Caught exception while locking AWT: " + e);
			}
		}
		awt_lock_count++;
	}
	private static native void nLockAWT() throws LWJGLException;
	
	static synchronized void unlockAWT() {
		if (awt_lock_count <= 0)
			throw new IllegalStateException("AWT not locked!");
		if (Thread.currentThread() != current_awt_lock_owner)
			throw new IllegalStateException("AWT already locked by " + current_awt_lock_owner);
		awt_lock_count--;
		if (awt_lock_count == 0) {
			try {
				nUnlockAWT();
			} catch (LWJGLException e) {
				LWJGLUtil.log("Caught exception while unlocking AWT: " + e);
			}
			current_awt_lock_owner = null;
			LinuxDisplay.class.notify();
		}
	}
	private static native void nUnlockAWT() throws LWJGLException;

	/**
	 * increment and decrement display usage.
	 */
	static void incDisplay() throws LWJGLException {
		if (display_connection_usage_count == 0) {
			GLContext.loadOpenGLLibrary();
			openDisplay();
		}
		display_connection_usage_count++;
	}
	
	static void decDisplay() {
		display_connection_usage_count--;
		if (display_connection_usage_count < 0)
			throw new InternalError("display_connection_usage_count < 0: " + display_connection_usage_count);
		if (display_connection_usage_count == 0) {
			closeDisplay();
			GLContext.unloadOpenGLLibrary();	
		}
	}

	private static native void openDisplay() throws LWJGLException;
	private static native void closeDisplay();

	private static int getWindowMode(boolean fullscreen) throws LWJGLException {
		if (fullscreen) {
			if (current_displaymode_extension == XRANDR && isNetWMFullscreenSupported()) {
				LWJGLUtil.log("Using NetWM for fullscreen window");
				return FULLSCREEN_NETWM;
			} else {
				LWJGLUtil.log("Using legacy mode for fullscreen window");
				return FULLSCREEN_LEGACY;
			}
		} else
			return WINDOWED;
	}
	
	public void createWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException {
		lockAWT();
		try {
			ByteBuffer handle = peer_info.lockAndGetHandle();
			try {
				current_window_mode = getWindowMode(fullscreen);
				nCreateWindow(handle, mode, current_window_mode, x, y);
			} finally {
				peer_info.unlock();
			}
		} finally {
			unlockAWT();
		}
	}
	private static native void nCreateWindow(ByteBuffer peer_info_handle, DisplayMode mode, int window_mode, int x, int y) throws LWJGLException;

	public void destroyWindow() {
		lockAWT();
		nDestroyWindow();
		unlockAWT();
	}
	private static native void nDestroyWindow();

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		lockAWT();
		try {
			nSwitchDisplayMode(current_displaymode_extension, mode);
		} finally {
			unlockAWT();
		}
	}
	private static native void nSwitchDisplayMode(int extension, DisplayMode mode) throws LWJGLException;

	public void resetDisplayMode() {
		lockAWT();
		try {
			nResetDisplayMode(current_displaymode_extension);
		} finally {
			unlockAWT();
		}
	}
	private static native void nResetDisplayMode(int extension);

	public int getGammaRampLength() {
		lockAWT();
		int length = nGetGammaRampLength();
		unlockAWT();
		return length;
	}
	private static native int nGetGammaRampLength();

	public void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException {
		lockAWT();
		try {
			nSetGammaRamp(gammaRamp);
		} finally {
			unlockAWT();
		}
	}
	private static native void nSetGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;

	public String getAdapter() {
		return null;
	}

	public String getVersion() {
		return null;
	}

	public DisplayMode init() throws LWJGLException {
		lockAWT();
		try {
			current_displaymode_extension = getBestDisplayModeExtension();
			if (current_displaymode_extension == NONE)
				throw new LWJGLException("No display mode extension is available");
			DisplayMode mode = nInit(current_displaymode_extension);
			return mode;
		} finally {
			unlockAWT();
		}
	}
	/** Assumes extension != NONE */
	private static native DisplayMode nInit(int extension) throws LWJGLException;

	public void setTitle(String title) {
		lockAWT();
		nSetTitle(title);
		unlockAWT();
	}
	private static native void nSetTitle(String title);

	public boolean isCloseRequested() {
		lockAWT();
		boolean result = nIsCloseRequested();
		unlockAWT();
		return result;
	}
	private static native boolean nIsCloseRequested();

	public boolean isVisible() {
		lockAWT();
		boolean result = nIsVisible();
		unlockAWT();
		return result;
	}
	private static native boolean nIsVisible();

	public boolean isActive() {
		lockAWT();
		boolean result = nIsActive(current_window_mode);
		unlockAWT();
		return result;
	}
	private static native boolean nIsActive(int window_mode);

	public boolean isDirty() {
		lockAWT();
		boolean result = nIsDirty();
		unlockAWT();
		return result;
	}
	private static native boolean nIsDirty();

	public PeerInfo createPeerInfo(PixelFormat pixel_format) throws LWJGLException {
		peer_info = new LinuxDisplayPeerInfo(pixel_format);
		return peer_info;
	}
	
	public void update() {
		lockAWT();
		nUpdate(current_displaymode_extension, current_window_mode);
		unlockAWT();
	}
	private static native void nUpdate(int extension, int current_window_mode);

	public void reshape(int x, int y, int width, int height) {
		lockAWT();
		nReshape(x, y, width, height);
		unlockAWT();
	}
	private static native void nReshape(int x, int y, int width, int height);

	public DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
		lockAWT();
		try {
			DisplayMode[] modes = nGetAvailableDisplayModes(current_displaymode_extension);
			return modes;
		} finally {
			unlockAWT();
		}
	}
	private static native DisplayMode[] nGetAvailableDisplayModes(int extension) throws LWJGLException;

	/* Mouse */
	public boolean hasWheel() {
		return true;
	}

	public int getButtonCount() {
		return NUM_BUTTONS;
	}

	public void createMouse() {
		lockAWT();
		nCreateMouse(current_window_mode);
		unlockAWT();
	}
	private static native void nCreateMouse(int window_mode);

	public void destroyMouse() {
		lockAWT();
		nDestroyMouse();
		unlockAWT();
	}
	private static native void nDestroyMouse();
	
	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
		update();
		lockAWT();
		nPollMouse(coord_buffer, buttons);
		unlockAWT();
	}
	private static native void nPollMouse(IntBuffer coord_buffer, ByteBuffer buttons);
	
	public int readMouse(IntBuffer buffer, int buffer_position) {
		update();
		lockAWT();
		int count = nReadMouse(buffer, buffer_position);
		unlockAWT();
		return count;
	}
	private static native int nReadMouse(IntBuffer buffer, int buffer_position);
	
	public void setCursorPosition(int x, int y) {
		lockAWT();
		nSetCursorPosition(x, y);
		unlockAWT();
	}
	private native void nSetCursorPosition(int x, int y);
	
	public void grabMouse(boolean grab) {
		lockAWT();
		nGrabMouse(current_window_mode, grab);
		unlockAWT();
	}
	private static native void nGrabMouse(int window_mode, boolean grab);
	
	public int getNativeCursorCapabilities() {
		lockAWT();
		try {
			incDisplay();
			int caps = nGetNativeCursorCapabilities();
			decDisplay();
			return caps;
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		} finally {
			unlockAWT();
		}
	}
	private static native int nGetNativeCursorCapabilities() throws LWJGLException;

	public void setNativeCursor(Object handle) throws LWJGLException {
		lockAWT();
		nSetNativeCursor(handle);
		unlockAWT();
	}
	private static native void nSetNativeCursor(Object handle) throws LWJGLException;
	
	public int getMinCursorSize() {
		lockAWT();
		try {
			incDisplay();
			int min_size = nGetMinCursorSize();
			decDisplay();
			return min_size;
		} catch (LWJGLException e) {
			LWJGLUtil.log("Exception occurred in getMinCursorSize: " + e);
			return 0;
		} finally {
			unlockAWT();
		}
	}
	private static native int nGetMinCursorSize();

	public int getMaxCursorSize() {
		lockAWT();
		try {
			incDisplay();
			int max_size = nGetMaxCursorSize();
			decDisplay();
			return max_size;
		} catch (LWJGLException e) {
			LWJGLUtil.log("Exception occurred in getMaxCursorSize: " + e);
			return 0;
		} finally {
			unlockAWT();
		}
	}
	private static native int nGetMaxCursorSize();
	
	/* Keyboard */
	public void createKeyboard() throws LWJGLException {
		lockAWT();
		try {
			nCreateKeyboard(current_window_mode);
		} finally {
			unlockAWT();
		}
	}
	private static native void nCreateKeyboard(int window_mode) throws LWJGLException;
	
	public void destroyKeyboard() {
		lockAWT();
		nDestroyKeyboard();
		unlockAWT();
	}
	private static native void nDestroyKeyboard();
	
	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		update();
		lockAWT();
		nPollKeyboard(keyDownBuffer);
		unlockAWT();
	}
	private static native void nPollKeyboard(ByteBuffer keyDownBuffer);

	public int readKeyboard(IntBuffer buffer, int buffer_position) {
		update();
		lockAWT();
		int count = nReadKeyboard(buffer, buffer_position);
		unlockAWT();
		return count;
	}
	private static native int nReadKeyboard(IntBuffer buffer, int buffer_position);
	
	public int isStateKeySet(int key) {
		return Keyboard.STATE_UNKNOWN;
	}

	private static native ByteBuffer nCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, int images_offset, IntBuffer delays, int delays_offset) throws LWJGLException;

	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		lockAWT();
		try {
			incDisplay();
			try {
				return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
			} catch (LWJGLException e) {
				decDisplay();
				throw e;
			}
		} finally {
			unlockAWT();
		}
	}

	public void destroyCursor(Object cursorHandle) {
		lockAWT();
		nDestroyCursor(cursorHandle);
		decDisplay();
		unlockAWT();
	}
	private static native void nDestroyCursor(Object cursorHandle);
	
	public int getPbufferCapabilities() {
		lockAWT();
		try {
			incDisplay();
			int caps = nGetPbufferCapabilities();
			decDisplay();
			return caps;
		} catch (LWJGLException e) {
			LWJGLUtil.log("Exception occurred in getPbufferCapabilities: " + e);
			return 0;
		} finally {
			unlockAWT();
		}
	}
	private static native int nGetPbufferCapabilities();

	public boolean isBufferLost(PeerInfo handle) {
		return false;
	}

	public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs) throws LWJGLException {
		return new LinuxPbufferPeerInfo(width, height, pixel_format);
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
		for (int i=0;i<icons.length;i++) {
			int size = icons[i].limit() / 4;
			
			if (((int) Math.sqrt(size)) == 32) {
				nSetWindowIcon(icons[i]);
				return 1;
			}
		}
		
		return 0;
	}
	
	private static native int nSetWindowIcon(ByteBuffer icon);
}
