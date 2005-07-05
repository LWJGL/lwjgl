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
	private static final int NUM_BUTTONS = 3;

	private static int display_connection_usage_count = 0;

	private static PeerInfo peer_info;

	/* Since Xlib is not guaranteed to be thread safe, we need a way to synchronize LWJGL
	 * Xlib calls with AWT Xlib calls. Fortunately, JAWT implements LockAWT and UnlockAWT() to
	 * do just that.
	 */
	static native void lockAWT();
	static native void unlockAWT();

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

	public void createWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException {
		lockAWT();
		try {
			ByteBuffer handle = peer_info.lockAndGetHandle();
			try {
				nCreateWindow(handle, mode, fullscreen, x, y);
			} finally {
				peer_info.unlock();
			}
		} finally {
			unlockAWT();
		}
	}
	private static native void nCreateWindow(ByteBuffer peer_info_handle, DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException;

	public void destroyWindow() {
		lockAWT();
		nDestroyWindow();
		unlockAWT();
	}
	private static native void nDestroyWindow();

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		lockAWT();
		try {
			nSwitchDisplayMode(mode);
		} finally {
			unlockAWT();
		}
	}
	private static native void nSwitchDisplayMode(DisplayMode mode) throws LWJGLException;

	public void resetDisplayMode() {
		lockAWT();
		try {
			nResetDisplayMode();
		} finally {
			unlockAWT();
		}
	}
	private static native void nResetDisplayMode();

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
			DisplayMode mode = nInit();
			return mode;
		} finally {
			unlockAWT();
		}
	}
	private static native DisplayMode nInit() throws LWJGLException;

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
		boolean result = nIsActive();
		unlockAWT();
		return result;
	}
	private static native boolean nIsActive();

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
		nUpdate();
		unlockAWT();
	}
	private static native void nUpdate();

	public void reshape(int x, int y, int width, int height) {
		lockAWT();
		nReshape(x, y, width, height);
		unlockAWT();
	}
	private static native void nReshape(int x, int y, int width, int height);

	public DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
		lockAWT();
		try {
			DisplayMode[] modes = nGetAvailableDisplayModes();
			return modes;
		} finally {
			unlockAWT();
		}
	}
	private static native DisplayMode[] nGetAvailableDisplayModes() throws LWJGLException;

	/* Mouse */
	public boolean hasWheel() {
		return true;
	}

	public int getButtonCount() {
		return NUM_BUTTONS;
	}

	public void createMouse() {
		lockAWT();
		nCreateMouse();
		unlockAWT();
	}
	private static native void nCreateMouse();
	public void destroyMouse() {
		lockAWT();
		nDestroyMouse();
		unlockAWT();
	}
	private static native void nDestroyMouse();
	
	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
		lockAWT();
		nPollMouse(coord_buffer, buttons);
		unlockAWT();
	}
	private static native void nPollMouse(IntBuffer coord_buffer, ByteBuffer buttons);
	
	public int readMouse(IntBuffer buffer, int buffer_position) {
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
		nGrabMouse(grab);
		unlockAWT();
	}
	private static native void nGrabMouse(boolean grab);
	
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
			nCreateKeyboard();
		} finally {
			unlockAWT();
		}
	}
	private static native void nCreateKeyboard() throws LWJGLException;
	
	public void destroyKeyboard() {
		lockAWT();
		nDestroyKeyboard();
		unlockAWT();
	}
	private static native void nDestroyKeyboard();
	
	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		lockAWT();
		nPollKeyboard(keyDownBuffer);
		unlockAWT();
	}
	private static native void nPollKeyboard(ByteBuffer keyDownBuffer);

	public int readKeyboard(IntBuffer buffer, int buffer_position) {
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
			int size = icons[i].limit();
			
			if (((int) Math.sqrt(size)) == 32) {
				nSetWindowIcon(icons[i]);
				return 1;
			}
		}
		
		return 0;
	}
	
	private static native int nSetWindowIcon(ByteBuffer icon);
}
