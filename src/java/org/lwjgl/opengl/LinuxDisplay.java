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
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;

final class LinuxDisplay implements DisplayImplementation {
	private static final int CURSOR_HANDLE_SIZE = 8;
	private static final int PBUFFER_HANDLE_SIZE = 24;

	/* Since Xlib is not guaranteed to be thread safe, we need a way to synchronize LWJGL
	 * Xlib calls with AWT Xlib calls. Fortunately, JAWT implements LockAWT and UnlockAWT(), to
	 * do just that.
	 */
	private native void lockAWT();
	private native void unlockAWT();
	
	public void createWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException {
		lockAWT();
		nCreateWindow(mode, fullscreen, x, y);
		unlockAWT();
	}
	public native void nCreateWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException;


	public void destroyWindow() {
		lockAWT();
		nDestroyWindow();
		unlockAWT();
	}
	public native void nDestroyWindow();

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		lockAWT();
		nSwitchDisplayMode(mode);
		unlockAWT();
	}
	public native void nSwitchDisplayMode(DisplayMode mode) throws LWJGLException;
	
	public void resetDisplayMode() {
		lockAWT();
		nResetDisplayMode();
		unlockAWT();
	}
	public native void nResetDisplayMode();

	public int getGammaRampLength() {
		lockAWT();
		int length = nGetGammaRampLength();
		unlockAWT();
		return length;
	}
	public native int nGetGammaRampLength();
	
	public void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException {
		lockAWT();
		nSetGammaRamp(gammaRamp);
		unlockAWT();
	}
	public native void nSetGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;

	public String getAdapter() {
		return null;
	}
	
	public String getVersion() {
		return null;
	}
	
	public DisplayMode init() {
		lockAWT();
		DisplayMode mode = nInit();
		unlockAWT();
		return mode;
	}
	public native DisplayMode nInit();

	public void setTitle(String title) {
		lockAWT();
		nSetTitle(title);
		unlockAWT();
	}
	public native void nSetTitle(String title);
	
	public boolean isCloseRequested() {
		lockAWT();
		boolean result = nIsCloseRequested();
		unlockAWT();
		return result;
	}
	public native boolean nIsCloseRequested();
	
	public boolean isVisible() {
		lockAWT();
		boolean result = nIsVisible();
		unlockAWT();
		return result;
	}
	public native boolean nIsVisible();
	
	public boolean isActive() {
		lockAWT();
		boolean result = nIsActive();
		unlockAWT();
		return result;
	}
	public native boolean nIsActive();
	
	public boolean isDirty() {
		lockAWT();
		boolean result = nIsDirty();
		unlockAWT();
		return result;
	}
	public native boolean nIsDirty();
	
	public void swapBuffers() {
		lockAWT();
		nSwapBuffers();
		unlockAWT();
	}
	public native void nSwapBuffers();

	public void makeCurrent() throws LWJGLException {
		lockAWT();
		nMakeCurrent();
		unlockAWT();
	}
	public native void nMakeCurrent() throws LWJGLException;

	public void createContext(PixelFormat pixel_format) throws LWJGLException {
		lockAWT();
		nCreateContext(pixel_format);
		unlockAWT();
	}
	public native void nCreateContext(PixelFormat pixel_format) throws LWJGLException;
	
	public void destroyContext() {
		lockAWT();
		nDestroyContext();
		unlockAWT();
	}
	public native void nDestroyContext();

	public void update() {
		lockAWT();
		nUpdate();
		unlockAWT();
	}
	public native void nUpdate();

	public void setVSyncEnabled(boolean sync) {
		lockAWT();
		nSetVSyncEnabled(sync);
		unlockAWT();
	}
	public native void nSetVSyncEnabled(boolean sync);

	public void reshape(int x, int y, int width, int height) {
		lockAWT();
		nReshape(x, y, width, height);
		unlockAWT();
	}
	public native void nReshape(int x, int y, int width, int height);

	public DisplayMode[] getAvailableDisplayModes() {
		lockAWT();
		DisplayMode[] modes = nGetAvailableDisplayModes();
		unlockAWT();
		return modes;
	}
	public native DisplayMode[] nGetAvailableDisplayModes();

	/* Mouse */
	public boolean hasWheel() {
		return true;
	}

	public int getButtonCount() {
		lockAWT();
		int num_buttons = nGetButtonCount();
		unlockAWT();
		return num_buttons;
	}
	public native int nGetButtonCount();

	public void createMouse() {
		lockAWT();
		nCreateMouse();
		unlockAWT();
	}
	public native void nCreateMouse();
	public void destroyMouse() {
		lockAWT();
		nDestroyMouse();
		unlockAWT();
	}
	public native void nDestroyMouse();
	
	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
		lockAWT();
		nPollMouse(coord_buffer, buttons);
		unlockAWT();
	}
	public native void nPollMouse(IntBuffer coord_buffer, ByteBuffer buttons);
	
	public void enableMouseBuffer() throws LWJGLException {
		lockAWT();
		nEnableMouseBuffer();
		unlockAWT();
	}
	public native void nEnableMouseBuffer() throws LWJGLException;

	public int readMouse(IntBuffer buffer, int buffer_position) {
		lockAWT();
		int count = nReadMouse(buffer, buffer_position);
		unlockAWT();
		return count;
	}
	public native int nReadMouse(IntBuffer buffer, int buffer_position);
	
	public void grabMouse(boolean grab) {
		lockAWT();
		nGrabMouse(grab);
		unlockAWT();
	}
	public native void nGrabMouse(boolean grab);
	
	public int getNativeCursorCaps() {
		lockAWT();
		int caps = nGetNativeCursorCaps();
		unlockAWT();
		return caps;
	}
	public native int nGetNativeCursorCaps();

	public void setNativeCursor(Object handle) throws LWJGLException {
		lockAWT();
		nSetNativeCursor(handle);
		unlockAWT();
	}
	public native void nSetNativeCursor(Object handle) throws LWJGLException;
	
	public int getMinCursorSize() {
		lockAWT();
		int min_size = nGetMinCursorSize();
		unlockAWT();
		return min_size;
	}
	public native int nGetMinCursorSize();

	public int getMaxCursorSize() {
		lockAWT();
		int max_size = nGetMaxCursorSize();
		unlockAWT();
		return max_size;
	}
	public native int nGetMaxCursorSize();
	
	/* Keyboard */
	public void createKeyboard() throws LWJGLException {
		lockAWT();
		nCreateKeyboard();
		unlockAWT();
	}
	public native void nCreateKeyboard() throws LWJGLException;
	
	public void destroyKeyboard() {
		lockAWT();
		nDestroyKeyboard();
		unlockAWT();
	}
	public native void nDestroyKeyboard();
	
	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		lockAWT();
		nPollKeyboard(keyDownBuffer);
		unlockAWT();
	}
	public native void nPollKeyboard(ByteBuffer keyDownBuffer);

	public int readKeyboard(IntBuffer buffer, int buffer_position) {
		lockAWT();
		int count = nReadKeyboard(buffer, buffer_position);
		unlockAWT();
		return count;
	}
	public native int nReadKeyboard(IntBuffer buffer, int buffer_position);
	
	public void enableTranslation() throws LWJGLException {
		lockAWT();
		nEnableTranslation();
		unlockAWT();
	}
	public native void nEnableTranslation() throws LWJGLException;
	
	public void enableKeyboardBuffer() throws LWJGLException {
		lockAWT();
		nEnableKeyboardBuffer();
		unlockAWT();
	}
	public native void nEnableKeyboardBuffer() throws LWJGLException;

	public int isStateKeySet(int key) {
		return Keyboard.STATE_UNKNOWN;
	}

	public native void nCreateCursor(ByteBuffer handle, int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, int images_offset, IntBuffer delays, int delays_offset) throws LWJGLException;

	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		lockAWT();
		ByteBuffer handle = BufferUtils.createByteBuffer(CURSOR_HANDLE_SIZE);
		nCreateCursor(handle, width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
		unlockAWT();
		return handle;
	}

	public void destroyCursor(Object cursorHandle) {
		lockAWT();
		nDestroyCursor(cursorHandle);
		unlockAWT();
	}
	public native void nDestroyCursor(Object cursorHandle);
	
	public int getPbufferCaps() {
		lockAWT();
		int caps = nGetPbufferCaps();
		unlockAWT();
		return caps;
	}
	public native int nGetPbufferCaps();

	public boolean isBufferLost(ByteBuffer handle) {
		return false;
	}

	public void makePbufferCurrent(ByteBuffer handle) throws LWJGLException {
		lockAWT();
		nMakePbufferCurrent(handle);
		unlockAWT();
	}
	
	public native void nMakePbufferCurrent(ByteBuffer handle) throws LWJGLException;

	public ByteBuffer createPbuffer(int width, int height, PixelFormat pixel_format,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs, ByteBuffer shared_pbuffer_handle) throws LWJGLException {
		lockAWT();
		ByteBuffer handle = BufferUtils.createByteBuffer(PBUFFER_HANDLE_SIZE);
		nCreatePbuffer(handle, width, height, pixel_format, pixelFormatCaps, pBufferAttribs, shared_pbuffer_handle);
		unlockAWT();
		return handle;
	}

	private native void nCreatePbuffer(ByteBuffer handle, int width, int height, PixelFormat pixel_format,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs, ByteBuffer shared_pbuffer_handle) throws LWJGLException;

	public void destroyPbuffer(ByteBuffer handle) {
		lockAWT();
		nDestroyPbuffer(handle);
		unlockAWT();
	}
	public native void nDestroyPbuffer(ByteBuffer handle);

	public void setPbufferAttrib(ByteBuffer handle, int attrib, int value) {
		throw new UnsupportedOperationException();
	}

	public void bindTexImageToPbuffer(ByteBuffer handle, int buffer) {
		throw new UnsupportedOperationException();
	}

	public void releaseTexImageFromPbuffer(ByteBuffer handle, int buffer) {
		throw new UnsupportedOperationException();
	}

	public boolean openURL(String url) {
		// Linux may as well resort to pure Java hackery, as there's no Linux native way of doing it
		// right anyway.

		String[] browsers = {"mozilla", "opera", "konqueror", "nautilus", "galeon", "netscape"};

		for (int i = 0; i < browsers.length; i ++) {
			try {
				Runtime.getRuntime().exec(new String[] { browsers[i], url });
				return true;
			} catch (IOException e) {
				// Ignore
				e.printStackTrace(System.err);
			}
		}

		// Seems to have failed
		return false;
	}
}
