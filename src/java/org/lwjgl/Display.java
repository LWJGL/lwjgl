/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

package org.lwjgl;

/**
 * $Id$
 *
 * Encapsulates everything you need for game display.
 * It must be created before any input devices are created.
 * The game display has NO mouse cursor or any other window decorations.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

public final class Display {

	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	/** Has the display been created? */
	private static boolean created;

	/** The current display mode, if created */
	private static DisplayMode mode;
	
	/** A pointer to the native display window */
	private static int handle;

	/**
	 * No construction allowed.
	 */
	private Display() {
		super();
	}

	/**
	 * Returns the entire list of display modes as an array, in no
	 * particular order. Any given mode is not guaranteed to be available and
	 * the only certain way to check is to call create() and make sure it works.
	 * Only non-palette-indexed modes are returned (ie. bpp will be 16, 24, or 32).
	 * 
	 * @return an array of all display modes the system reckons it can handle.
	 */
	public static native DisplayMode[] getAvailableDisplayModes();

	/**
	 * Create a display with the specified display mode. If the display is
	 * already created then no action is taken - the display must first be
	 * destroyed.
	 * 
	 * @param displayMode a display mode to choose
	 * @param fullscreen whether to create the display fullscreen
	 * @throws Exception if the display mode could not be set
	 * @see #destroy()
	 */
	public static void create(
		DisplayMode displayMode,
		boolean fullscreen)
		throws Exception {

		if (created)
			return;

		if (!nCreate(displayMode.width,
			displayMode.height,
			displayMode.bpp,
			displayMode.freq,
			fullscreen))
			throw new Exception("Failed to set display mode to " + displayMode);

		created = true;
		mode = displayMode;
	}

	/**
	 * Native method to create the display. This will set the handle if it is
	 * successful.
	 * @return true if the display was successfully created
	 * @see #create(org.lwjgl.DisplayMode, boolean)
	 */
	private static native boolean nCreate(
		int width,
		int height,
		int bpp,
		int freq,
		boolean fullscreen);

	/**
	 * Destroy the display and return it to normal. If the display has not yet
	 * been created no action is taken.
	 */
	public static void destroy() {
		if (!created)
			return;
		nDestroy();
		created = false;
		mode = null;
	}
	
	/**
	 * Native method to destroy the display. This will reset the handle.
	 */
	private static native void nDestroy();

	/**
	 * @return the current display width.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getWidth() {
		assert created : "The display has not been created yet.";
		return mode.width;
	}

	/**
	 * @return the current display height.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getHeight() {
		assert created : "The display has not been created yet.";
		return mode.height;
	}

	/**
	 * @return the current display depth.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getDepth() {
		assert created : "The display has not been created yet.";
		return mode.bpp;
	}

	/**
	 * @return the current display frequency.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getFrequency() {
		assert created : "The display has not been created yet.";
		return mode.freq;
	}
	
	/**
	 * @return the current display mode, or null if the display is not yet created
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static DisplayMode getDisplayMode() {
		assert created : "The display has not been created yet.";
		return mode;
	}
	
	/**
	 * @return the native handle
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getHandle() {
		assert created : "The display has not been created yet.";
		return handle;
	}
	
	/**
	 * @return true if the display has been created
	 */
	public static boolean isCreated() {
		return created;
	}

}
