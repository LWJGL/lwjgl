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
	public void createWindow(DisplayMode mode, boolean fullscreen, int x, int y) throws LWJGLException;
	public void destroyWindow();

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException;

	/**
	 * Reset the display mode to whatever it was when LWJGL was initialized.
	 * Fails silently.
	 */
	public void resetDisplayMode();

	/**
	 * Return the length of the gamma ramp arrays. Returns 0 if gamma settings are
	 * unsupported.
	 *
	 * @return the length of each gamma ramp array, or 0 if gamma settings are unsupported.
	 */
	public int getGammaRampLength();

	/**
	 * Native method to set the gamma ramp.
	 */
	public void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;

	/**
	 * Get the driver adapter string. This is a unique string describing the actual card's hardware, eg. "Geforce2", "PS2",
	 * "Radeon9700". If the adapter cannot be determined, this function returns null.
	 * @return a String
	 */
	public String getAdapter();

	/**
	 * Get the driver version. This is a vendor/adapter specific version string. If the version cannot be determined,
	 * this function returns null.
	 * @return a String
	 */
	public String getVersion();

	/**
	 * Initialize and return the current display mode.
	 */
	public DisplayMode init();

	/**
	 * Native implementation of setTitle(). This will read the window's title member
	 * and stash it in the native title of the window.
	 */
	public void setTitle(String title);

	public boolean isCloseRequested();

	public boolean isVisible();
	public boolean isActive();

	public boolean isDirty();

	/**
	 * Swap double buffers.
	 */
	public void swapBuffers();

	/**
	 * Make the window the current rendering context for GL calls.
	 */
	public void makeCurrent() throws LWJGLException;

	/**
	 * Create the native OpenGL context.
	 * @throws LWJGLException
	 */
	public void createContext(PixelFormat pixel_format) throws LWJGLException;

	public void destroyContext();

	/**
	 * Updates the windows internal state. This must be called at least once per video frame
	 * to handle window close requests, moves, paints, etc.
	 */
	public void update();

	public void setVSyncEnabled(boolean sync);

	public void reshape(int x, int y, int width, int height);

	/**
	 * Native method for getting displaymodes
	 */
	public DisplayMode[] getAvailableDisplayModes();

	/*
	 * Mouse methods
	 */
	/** Native query of wheel support */
	public boolean hasWheel();

	/** Native query of button count */
	public int getButtonCount();
	
	/**
	 * Native method to create the mouse.
	 * 
	 * @return true if the mouse was created
	 */
	public void createMouse();

	/**
	 * Native method the destroy the mouse
	 */
	public void destroyMouse();

	/**
	 * Native method to poll the mouse
	 */
	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons);

	/**
	 * Native method to enable the buffer
	 * @return the event buffer,
	 * or null if no buffer can be allocated
	 */
	public void enableMouseBuffer() throws LWJGLException;

	/**
	 * Native method to read the keyboard buffer
	 * @return the total number of events read.
	 */
	public int readMouse(IntBuffer buffer, int buffer_position);

	public void grabMouse(boolean grab);

	/**
	 * Native function to determine native cursor support
	 */
	public int getNativeCursorCaps();

	/** Native method to set the native cursor */
	public void setNativeCursor(Object handle) throws LWJGLException;

	/** Native method returning the minimum cursor size */
	public int getMinCursorSize();

	/** Native method returning the maximum cursor size */
	public int getMaxCursorSize();

	/*
	 * Keyboard methods
	 */
	
	/**
	 * Native method to create the keyboard
	 */
	public void createKeyboard() throws LWJGLException;

	/**
	 * Native method to destroy the keyboard
	 */
	public void destroyKeyboard();

	/**
	 * Native method to poll the keyboard.
	 * 
	 * @param keyDownBufferAddress the address of a 256-byte buffer to place
	 * key states in.
	 */
	public void pollKeyboard(ByteBuffer keyDownBuffer);
	
	/**
	 * Native method to read the keyboard buffer
	 * @return the total number of events read.
	 */
	public int readKeyboard(IntBuffer buffer, int buffer_position);
	
	/**
	 * Native method to enable the translation buffer
	 */
	public void enableTranslation() throws LWJGLException;
	
	/**
	 * Native method to enable the buffer
	 * @return the event buffer,
	 * or null if no buffer can be allocated
	 */
	public void enableKeyboardBuffer() throws LWJGLException;
	
	public int isStateKeySet(int key);
	
	/** Native cursor handles */
	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException;

	public void destroyCursor(Object cursor_handle);

	/* Pbuffer caps */
	public int getPbufferCaps();
}
