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
 
package org.lwjgl.input;

import java.nio.ByteBuffer;

import org.lwjgl.Display;
import org.lwjgl.Sys;

/**
 * $Id$
 *
 * A raw GamePad interface. This can be used to poll the current state of the
 * buttons, or read all the gamepad presses / releases since the last read.
 * Buffering must be explicitly enabled; the size of the buffer is determined
 * by the native implementation at its discretion.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class GamePad {
	
	// Button codes
	public static int PAD_UP = 1;
	public static int PAD_DOWN = 2;
	public static int PAD_LEFT = 3;
	public static int PAD_RIGHT = 4;
	public static int PAD_BUTTON0 = 5;
	public static int PAD_BUTTON1 = 6;
	public static int PAD_BUTTON2 = 7;
	public static int PAD_BUTTON3 = 8;
	public static int PAD_BUTTON4 = 9;
	public static int PAD_BUTTON5 = 10;
	public static int PAD_BUTTON6 = 11;
	public static int PAD_BUTTON7 = 12;
	public static int PAD_BUTTON8 = 13;
	
	static {
		initialize();
	}
	
	/** Has the gamepad been created? */
	private static boolean created;
	
	/** The buttons status from the last poll */
	private static final ByteBuffer buttonDownBuffer = ByteBuffer.allocateDirect(256);
	
	/** Address of the buttonDown buffer */
	private static final int buttonDownAddress = Sys.getDirectBufferAddress(buttonDownBuffer);
	
	/**
	 * The button events from the last read: a sequence of pairs of button number,
	 * followed by state.
	 */
	private static ByteBuffer readBuffer;
	
	/** Address of the read buffer */
	private static int readBufferAddress;
	
	/** The current gamepad event button being examined */
	public static int button;
	
	/** The current state of the button being examined in the event queue */
	public static boolean state;
	
	/**
	 * GamePad cannot be constructed.
	 */
	private GamePad() {
	}
	
	/**
	 * Static initialization
	 */
	private static void initialize() {
		System.loadLibrary(Sys.LIBRARY_NAME);
		initIDs();
	}
	
	/**
	 * Register fields with the native library
	 */
	private static native void initIDs();
	
	/**
	 * "Create" the gamepad. The display must first have been created. The
	 * reason for this is so the gamepad has a window to "focus" in.
	 * 
	 * @throw Exception if the gamepad could not be created for any reason
	 */
	public static void create() throws Exception {
		if (created)
			return;
		if (!Display.isCreated())
			throw new Exception("The display has not yet been created.");
		if (!nCreate())
			throw new Exception("The gamepad could not be created.");
		created = true;
	}
	
	/**
	 * Native method to create the gamepad
	 * 
	 * @return true if the gamepad was created
	 */
	private static native boolean nCreate();

	/**
	 * "Destroy" the gamepad
	 */
	public static void destroy() {
		if (!created)
			return;
		created = false;
		nDestroy();
	}
	
	/**
	 * Native method the destroy the gamepad
	 */
	private static native void nDestroy();

	/**
	 * Polls the gamepad.
	 */
	public static void poll() {
		assert created : "The gamepad has not been created.";
		nPoll(buttonDownAddress);
	}
	
	/**
	 * Native method to poll the gamepad.
	 * 
	 * @param keyDownBufferAddress the address of a 256-byte buffer to place
	 * key states in.
	 */
	private static native void nPoll(int keyDownBufferAddress);
	
	/**
	 * Reads the gamepad buffer.
	 */
	public static void read() {
		assert created : "The gamepad has not been created.";
		assert readBuffer != null : "GamePad buffering has not been enabled.";
		readBuffer.clear();
		readBuffer.limit(nRead(readBufferAddress) << 1);
	}
	
	/**
	 * Native method to read the gamepad buffer
	 * 
	 * @param readBufferAddress the address of the gamepad buffer
	 * @return the number of gamepad events read
	 */
	private static native int nRead(int readBufferAddress);
	
	/**
	 * Enable gamepad buffering. Must be called after the gamepad is created.
	 * @return the size of the gamepad buffer in events, or 0 if no buffering
	 * can be enabled for any reason
	 */
	public static int enableBuffer() {
		assert created : "The gamepad has not been created.";
		return nEnableBuffer();
	}
	
	/**
	 * Native method to enable the buffer
	 * @return the size of the buffer allocated, in events (1 event is 2 bytes),
	 * or 0 if no buffer can be allocated
	 */
	private static native int nEnableBuffer();
	
	/**
	 * Checks to see if a button is down.
	 * @param button The button code to check
	 * @return true if the button is down according to the last poll()
	 */
	public static boolean isButtonDown(int button) {
		assert created : "The gamepad has not been created.";
		return buttonDownBuffer.get(button) != 0;
	}
	
	/**
	 * Gets the number of gamepad events waiting after doing a read().
	 * @return the number of gamepad events
	 */
	public static int getNumGamePadEvents() {
		return readBuffer.limit() >> 1;
	}
	
	/**
	 * Gets the next gamepad event. This is stored in the publicly accessible
	 * static fields button and state.
	 * @return true if a gamepad event was read, false otherwise
	 */
	public static boolean next() {
		assert created : "The gamepad has not been created.";
		assert readBuffer != null : "GamePad buffering has not been enabled.";
		
		if (readBuffer.hasRemaining()) {
			button = readBuffer.get();
			state = readBuffer.get() != 0;
			return true;
		} else
			return false;
		
	}

	/**
	 * Queries the number of buttons the gamepad has (excluding up, down, left, right)
	 * @return the number of buttons the gamepad has
	 */
	public static int getNumButtons() {
		assert created : "The gamepad has not been created.";
		return nGetNumButtons();
	}
	
	/**
	 * Native implementation of getNumButtons()
	 */
	private static native int nGetNumButtons();
	

}
