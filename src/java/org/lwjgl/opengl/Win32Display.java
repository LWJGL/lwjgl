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

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;

final class Win32Display implements DisplayImplementation {
	private static final int CURSOR_HANDLE_SIZE = 8;

	public native void createWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException;
	public native void destroyWindow();
	public native void switchDisplayMode(DisplayMode mode) throws LWJGLException;
	public native void resetDisplayMode();
	public native int getGammaRampLength();
	public native void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;
	public native String getAdapter();
	public native String getVersion();
	public native DisplayMode init();
	public native void setTitle(String title);
	public native boolean isCloseRequested();
	public native boolean isVisible();
	public native boolean isActive();
	public native boolean isDirty();
	public native void swapBuffers();
	public native void makeCurrent() throws LWJGLException;
	public native void createContext(PixelFormat pixel_format) throws LWJGLException;
	public native void destroyContext();
	public native void update();
	public native void setVSyncEnabled(boolean sync);
	public native void reshape(int x, int y, int width, int height);
	public native DisplayMode[] getAvailableDisplayModes();

	/* Mouse */
	public native boolean hasWheel();
	public native int getButtonCount();
	public native void createMouse();
	public native void destroyMouse();
	public native void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons);
	public native void enableMouseBuffer() throws LWJGLException;
	public native int readMouse(IntBuffer buffer, int buffer_position);
	public native void grabMouse(boolean grab);
	public native int getNativeCursorCaps();
	public native void setNativeCursor(Object handle) throws LWJGLException;
	public native int getMinCursorSize();
	public native int getMaxCursorSize();

	/* Keyboard */
	public native void createKeyboard() throws LWJGLException;
	public native void destroyKeyboard();
	public native void pollKeyboard(ByteBuffer keyDownBuffer);
	public native int readKeyboard(IntBuffer buffer, int buffer_position);
	public native void enableTranslation() throws LWJGLException;
	public native void enableKeyboardBuffer() throws LWJGLException;
	public native int isStateKeySet(int key);

	public native void nCreateCursor(ByteBuffer handle, int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, int images_offset, IntBuffer delays, int delays_offset) throws LWJGLException;

	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		ByteBuffer handle = BufferUtils.createByteBuffer(CURSOR_HANDLE_SIZE);
		nCreateCursor(handle, width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
		return handle;
	}

	public native void destroyCursor(Object cursorHandle);
	public native int getPbufferCaps();
	public boolean openURL(String url) {
		nOpenURL(url);
		return true;
	}

	private native void nOpenURL(String url);
}
