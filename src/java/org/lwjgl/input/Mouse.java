/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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

package org.lwjgl.input;

import org.lwjgl.*;

/**
 * $Id$
 *
 * A raw Mouse interface. This can be used to poll the current state of the
 * mouse buttons, and determine the mouse movement delta since the last poll.
 * 
 * n buttons supported, n being a native limit. A scrolly wheel is also
 * supported, if one such is available. All movement is reported as delta from
 * last position.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class Mouse {
	public final static int CURSOR_ONE_BIT_TRANSPARANCY = 1;
	public final static int CURSOR_8_BIT_ALPHA = 2;
	public final static int CURSOR_ANIMATION = 4;

	/** Has the mouse been created? */
	private static boolean created;

	/** The mouse buttons status from the last poll */
	private static boolean[] buttons;

	/** Delta X */
	public static int dx;

	/** Delta Y */
	public static int dy;

	/** Delta Z */
	public static int dwheel;

	/** Number of buttons supported by the mouse */
	public static int buttonCount = -1;

	/** Does this mouse support a scroll wheel */
	public static boolean hasWheel = false;

	/** The current native cursor, if any */
	private static Cursor currentCursor;

	static {
		initialize();
	}

	/**
	 * Mouse cannot be constructed.
	 */
	private Mouse() {
	}

	/**
	 * Gets the currently bound native cursor, if any.
	 *
	 * @return the currently bound native cursor, if any.
	public Cursor getNativeCursor() {
		return currentCursor;
	}

	/**
	 * Get the capabilities of the native cursor. Return a bit mask of the native cursor capabilities.
	 * The CURSOR_ONE_BIT_TRANSPARANCY indicates support for cursors with one bit transparancy,
	 * the CURSOR_8_BIT_ALPHA indicates support for 8 bit alpha and CURSOR_ANIMATION indicates
	 * support for cursor animations.
	 * 
	 * @return A bit mask with native cursor capabilities.
	 */
	public static int getNativeCursorCaps() {
		return nGetNativeCursorCaps();
	}

	/**
	 * Native function to determine native cursor support
	 */
	private static native int nGetNativeCursorCaps();

	/**
	 * Binds a native cursor. If the cursor argument is null, the
	 * native cursor is disabled, as if native cursors were not supported.
	 * The Mouse must be created before a native cursor can be bound.
	 *
	 * NOTE: The native cursor is not constrained to the window, but
	 * relative events will not be generated if the cursor is outside.
	 * The initial position of the cursor is in the upper left corner of
	 * the window, and the cursor will be moved to this origin when a
	 * native cursor is set and the previous cursor is null.
	 *
	 * @param cursor the native cursor object to bind. May be null.
	 * @return The previous Cursor object set, or null.
         * @throws Exception if the cursor could not be set for any reason
	 */
	public static Cursor setNativeCursor(Cursor cursor) throws Exception {
		assert created && ((getNativeCursorCaps() | CURSOR_ONE_BIT_TRANSPARANCY) != 0);
		Cursor oldCursor = currentCursor;
		currentCursor = cursor;
		if (currentCursor != null) {
			nSetNativeCursor(currentCursor.getHandle());
		} else {
			nSetNativeCursor(0);
		}
		return oldCursor;
	}

	/** Native method to set the native cursor */
	private static native void nSetNativeCursor(int handle);

	/**
	 * Gets the minimum size of a native cursor. Can only be called if
	 * The Mouse is created and cursor caps includes at least
	 * CURSOR_ONE_BIT_TRANSPARANCY.
	 *
	 * @return the maximum size of a native cursor
	 */
	public static int getMinCursorSize() {
		return nGetMinCursorSize();
	}

	/** Native method returning the minimum cursor size */
	private static native int nGetMinCursorSize();

	/**
	 * Gets the maximum size of a native cursor. Can only be called if
	 * The Mouse is created and cursor caps includes at least
	 * CURSOR_ONE_BIT_TRANSPARANCY.
	 *
	 * @return the maximum size of a native cursor
	 */
	public static int getMaxCursorSize() {
		return nGetMaxCursorSize();
	}

	/** Native method returning the maximum cursor size */
	private static native int nGetMaxCursorSize();

	/**
	 * Static initialization
	 */
	private static void initialize() {
		System.loadLibrary(Sys.getLibraryName());
		initIDs();
	}

	/**
	 * Register fields with the native library
	 */
	private static native void initIDs();

	/**
	 * "Create" the mouse. The display must first have been created.
	* 
	 * @throws Exception if the mouse could not be created for any reason
	 */
	public static void create() throws Exception {
		if (created)
			return;
		if (!nCreate())
			throw new Exception("The mouse could not be created.");
		created = true;
		currentCursor = null;

		//set mouse buttons
		buttons = new boolean[buttonCount];
	}

	/**
	 * Native method to create the mouse.
	 * 
	 * @return true if the mouse was created
	 */
	private static native boolean nCreate();

	/**
	 * @return true if the mouse has been created
	 */
	public static boolean isCreated() {
		return created;
	}

	/**
	 * "Destroy" the mouse. Remember to reset the native cursor if
	 * setNativeCursor() has been called with anything else than null.
	 */
	public static void destroy() {
		assert currentCursor == null;
		if (!created)
			return;
		created = false;
		buttons = null;
		currentCursor = null;

		nDestroy();
	}

	/**
	 * Native method the destroy the mouse
	 */
	private static native void nDestroy();

	/**
	 * Polls the mouse.
	 */
	public static void poll() {
		assert created : "The mouse has not been created.";
		nPoll();
	}

	/**
	 * Native method to poll the mouse
	 */
	private static native void nPoll();

	/**
	 * See if a particular mouse button is down.
	 * 
	 * @param button The index of the button you wish to test (0..buttonCount-1)
	 * @return true if the specified button is down
	 */
	public static boolean isButtonDown(int button) {
		assert created : "The mouse has not been created.";
		return buttons[button];
	}
}
