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

import org.lwjgl.Display;
import org.lwjgl.Sys;

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

	static {
		initialize();
	}

	/**
	 * Mouse cannot be constructed.
	 */
	private Mouse() {
	}

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
		if (!Display.isCreated())
			throw new Exception("The display has not yet been created.");
		if (!nCreate())
			throw new Exception("The mouse could not be created.");
		created = true;
    
    //set mouse buttons
    buttons = new boolean[buttonCount];
	}

	/**
	 * Native method to create the mouse
	 * 
	 * @return true if the mouse was created
	 */
	private static native boolean nCreate();

	/**
	 * "Destroy" the mouse
	 */
	public static void destroy() {
		if (!created)
			return;
		created = false;
    buttons = null;
    
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