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

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import org.lwjgl.LWJGLException;

public interface DisplayImplementation {

	void createWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException;

	void destroyWindow();

	void switchDisplayMode(DisplayMode mode) throws LWJGLException;

	/**
	 * Reset the display mode to whatever it was when LWJGL was initialized.
	 * Fails silently.
	 */
	void resetDisplayMode();

	/**
	 * Return the length of the gamma ramp arrays. Returns 0 if gamma settings are
	 * unsupported.
	 *
	 * @return the length of each gamma ramp array, or 0 if gamma settings are unsupported.
	 */
	int getGammaRampLength();

	/**
	 * Native method to set the gamma ramp.
	 */
	void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;

	/**
	 * Get the driver adapter string. This is a unique string describing the actual card's hardware, eg. "Geforce2", "PS2",
	 * "Radeon9700". If the adapter cannot be determined, this function returns null.
	 * @return a String
	 */
	String getAdapter();

	/**
	 * Get the driver version. This is a vendor/adapter specific version string. If the version cannot be determined,
	 * this function returns null.
	 * @return a String
	 */
	String getVersion();

	/**
	 * Initialize and return the current display mode.
	 */
	DisplayMode init();

	/**
	 * Native implementation of setTitle(). This will read the window's title member
	 * and stash it in the native title of the window.
	 */
	void setTitle(String title);

	boolean isCloseRequested();

	boolean isVisible();
	boolean isActive();

	boolean isDirty();

	/**
	 * Swap double buffers.
	 */
	void swapBuffers();

	/**
	 * Make the window the current rendering context for GL calls.
	 */
	void makeCurrent() throws LWJGLException;

	/**
	 * Create the native OpenGL context.
	 * @throws LWJGLException
	 */
	void createContext(PixelFormat pixel_format) throws LWJGLException;

	void destroyContext();

	/**
	 * Updates the windows internal state. This must be called at least once per video frame
	 * to handle window close requests, moves, paints, etc.
	 */
	void update();

	void setVSyncEnabled(boolean sync);

	void reshape(int x, int y, int width, int height);

	/**
	 * Native method for getting displaymodes
	 */
	DisplayMode[] getAvailableDisplayModes();

	/*
	 * Mouse methods
	 */
	/** Native query of wheel support */
	boolean hasWheel();

	/** Native query of button count */
	int getButtonCount();

	/**
	 * Native method to create the mouse.
	 */
	void createMouse();

	/**
	 * Native method the destroy the mouse
	 */
	void destroyMouse();

	/**
	 * Native method to poll the mouse
	 */
	void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons);

	/**
	 * Native method to enable the buffer
	 */
	void enableMouseBuffer() throws LWJGLException;

	/**
	 * Native method to read the keyboard buffer
	 *
	 * @return the total number of events read.
	 */
	int readMouse(IntBuffer buffer, int buffer_position);


	void grabMouse(boolean grab);

	/**
	 * Native function to determine native cursor support
	 */
	int getNativeCursorCaps();

	/** Native method to set the native cursor */
	void setNativeCursor(Object handle) throws LWJGLException;

	/** Native method returning the minimum cursor size */
	int getMinCursorSize();

	/** Native method returning the maximum cursor size */
	int getMaxCursorSize();

	/*
	 * Keyboard methods
	 */

	/**
	 * Native method to create the keyboard
	 */
	void createKeyboard() throws LWJGLException;

	/**
	 * Native method to destroy the keyboard
	 */
	void destroyKeyboard();

	/**
	 * Native method to poll the keyboard.
	 *
	 * @param keyDownBuffer the address of a 256-byte buffer to place
	 * key states in.
	 */
	void pollKeyboard(ByteBuffer keyDownBuffer);

	/**
	 * Native method to read the keyboard buffer
	 * @return the total number of events read.
	 */
	int readKeyboard(IntBuffer buffer, int buffer_position);

	/**
	 * Native method to enable the translation buffer
	 */
	void enableTranslation() throws LWJGLException;

	/**
	 * Native method to enable the buffer
	 */
	void enableKeyboardBuffer() throws LWJGLException;

	int isStateKeySet(int key);

	/** Native cursor handles */
	Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException;

	void destroyCursor(Object cursor_handle);

	/* Pbuffer */
	int getPbufferCaps();

	/**
	 * Method to test for buffer integrity
	 */
	public boolean isBufferLost(ByteBuffer handle);

	/**
	 * Method to make a pbuffer current.
	 */
	public void makePbufferCurrent(ByteBuffer handle) throws LWJGLException;

	/**
	 * Method to create a Pbuffer
	 */
	public ByteBuffer createPbuffer(int width, int height, PixelFormat pixel_format,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs, ByteBuffer shared_pbuffer_handle) throws LWJGLException;

	/**
	 * Destroy pbuffer
	 */
	public void destroyPbuffer(ByteBuffer handle);

	public void setPbufferAttrib(ByteBuffer handle, int attrib, int value);

	public void bindTexImageToPbuffer(ByteBuffer handle, int buffer);

	public void releaseTexImageFromPbuffer(ByteBuffer handle, int buffer);

	boolean openURL(String url);
}
