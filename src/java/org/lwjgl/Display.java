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

import java.util.HashSet;
import java.util.Arrays;

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
		init();
	}

	/** Has the display been created? */
	private static boolean created;

	/** The current display mode, if created */
	private static DisplayMode mode;

	/** A pointer to the native display window. On Windows this will be an hWnd. */
	private static int handle;

	/** Whether or not the display has been requested to shutdown by the user */
	private static boolean closeRequested = false;

	/*
	 * Platforms. This will let you determine which platform you are running
	 * on, which is handy to know for some GL context calls.
	 */

	/** Windows platform */	
	public static final int PLATFORM_WGL = 0;
	
	/** GLX (Linux/Unix) platform */
	public static final int PLATFORM_GLX = 1;
	
	/** MacOSX platform */
	public static final int PLATFORM_AGL = 2;

	/**
	 * No construction allowed.
	 */
	private Display() {
		super();
	}
	
	/**
	 * Initialize. This determines, natively, the current display mode and stashes
	 * it back in the mode static member.
	 */
	private static native void init();

	/**
	 * Returns the entire list of display modes as an array, in no
	 * particular order. Any given mode is not guaranteed to be available and
	 * the only certain way to check is to call create() and make sure it works.
	 * Only non-palette-indexed modes are returned (ie. bpp will be 16, 24, or 32).
	 * 
	 * @return an array of all display modes the system reckons it can handle.
	 */
	public static DisplayMode[] getAvailableDisplayModes() {
		DisplayMode[] unfilteredModes = nGetAvailableDisplayModes();

		if (unfilteredModes == null) {
			return new DisplayMode[0];
		}

		// We'll use a HashSet to filter out the duplicated modes
		HashSet modes = new HashSet(unfilteredModes.length);

		modes.addAll(Arrays.asList(unfilteredModes));
		DisplayMode[] filteredModes = new DisplayMode[modes.size()];
		modes.toArray(filteredModes);

		if (Sys.DEBUG) {
			System.out.println("Removed " + (unfilteredModes.length - filteredModes.length) + " duplicate displaymodes");
		}

		return filteredModes;
	}

	/** 
	 * Native method for getting displaymodes
	 */
	private static native DisplayMode[] nGetAvailableDisplayModes();
	
	/**
	 * Set the current display mode. The underlying OS may not use an exact match for
	 * the specified display mode. After successfully calling setDisplayMode() you will
	 * still need to query the display's characteristics using getDisplayMode().
	 * @param newMode The new display mode to set
	 * @throws Exception if the display mode could not be set
	 */
	public static native void setDisplayMode(DisplayMode mode) throws Exception;
	
	/**
	 * Reset the display mode to whatever it was when LWJGL was initialized.
	 * Fails silently.
	 */
	public static native void resetDisplayMode() throws Exception;

	/**
	 * Retrieves the width of the created display
	 * 
	 * @return the current display width.
	 */
	public static int getWidth() {
		return mode.width;
	}

	/**
	 * Retrieves the height of the created display
	 * 
	 * @return the current display height.
	 */
	public static int getHeight() {
		return mode.height;
	}

	/**
	 * Retrieves the current display depth of the created display
	 * 
	 * @return the current display depth.
	 */
	public static int getDepth() {
		return mode.bpp;
	}

	/**
	 * Retrieves the current display frequency of the created display
	 * 
	 * @return the current display frequency.
	 */
	public static int getFrequency() {
		return mode.freq;
	}

	/**
	 * Retrieves the native handle to the created window. The meaning of this value
	 * is platform specific. Under Win32, it is an HWND.
	 * 
	 * @return the native handle
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getHandle() {
		assert created : "The display has not been created yet.";
		return handle;
	}

	/**
	 * Returns the operating system windowing platform. This will be one of the
	 * constants defined above. There is no "unknown" platform; a native library port
	 * has to provide a unique platform number for this mechanism to work. If the LWJGL
	 * is ported to, say, QNX, we will have a PLATFORM_QNX at the ready.
	 * 
	 * @return the windowing system
	 */
	public static native int getPlatform();

	/**
	 * Obtains the display's gamma ramp. The gamma ramp returned is an array of
	 * integers in the range 0..255. If gamma is not supported by the underlying
	 * hardware then null is returned.
	 * @return an array of ints, or null
	 */
	public static native int[] getGammaRamp();
	
	/**
	 * Sets the display's gamma ramp. The gamma ramp should be an array of ints
	 * in the range 0...255. The length of the array should match the length of the
	 * array returned by getGammaRamp().
	 * 
	 * If the underlying hardware does not support gamma then this command is a no-op.
	 */
	public static native void setGammaRamp(int[] gamma);

}
