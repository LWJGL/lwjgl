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
 * A raw Joystick interface. This can be used to poll the current state of the
 * joystick buttons, and determine the joystick position. The joystick position
 * is returned as floats in the range -1.0f to 1.0f.
 * 
 * No buffering is available.
 * 
 * Up to 8 buttons are available. A scrolly wheel or paddle, if present, is the z
 * value. This will be in the range of 0.0f to 1.0f.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class Joystick {

	static {
		initialize();
	}
	
	/** Has the joystick been created? */
	private static boolean created;
	
	/** The joystick buttons status from the last poll */
	private static final boolean[] button = new boolean[8];
	
	/** X position, range -1.0f to 1.0f */
	public static float x;
	
	/** Y position, range -1.0f to 1.0f */
	public static float y;
	
	/** Z position, range 0.0f to 1.0f */
	public static float z;

	/**
	 * The joystick events from the last read: a sequence of Events
	 */
	private static ByteBuffer readBuffer;
	
	/** Address of the read buffer */
	private static int readBufferAddress;
	
	/** The size in bytes of a single joystick event */
	private static final int JOYSTICK_EVENT_SIZE = 20;
	
	/** The stride in bytes of a single joystick event */
	private static final int JOYSTICK_EVENT_STRIDE = 32;
		
	/**
	 * Joystick cannot be constructed.
	 */
	private Joystick() {
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
	 * "Create" the joystick. The display must first have been created.
	 * @throws Exception if the joystick could not be created for any reason
	 */
	public static void create() throws Exception {
		if (created)
			return;
		if (!Display.isCreated())
			throw new Exception("The display has not yet been created.");
		if (!nCreate())
			throw new Exception("The joystick could not be created.");
		created = true;
	}
	
	/**
	 * Native method to create the joystick
	 * 
	 * @return true if the joystick was created
	 */
	private static native boolean nCreate();

	/**
	 * "Destroy" the joystick
	 */
	public static void destroy() {
		if (!created)
			return;
		created = false;
		nDestroy();
	}
	
	/**
	 * Native method the destroy the joystick
	 */
	private static native void nDestroy();

	/**
	 * Polls the joystick.
	 */
	public static void poll() {
		assert created : "The joystick has not been created.";
		nPoll();
	}		
	
	/**
	 * Native method to poll the joystick
	 */
	private static native void nPoll();
	
	/**
	 * Queries the number of buttons the joystick has
	 * @return the number of buttons the joystick has
	 */
	public static int getNumButtons() {
		assert created : "The joystick has not been created.";
		return nGetNumButtons();
	}
	
	/**
	 * Native implementation of getNumButtons()
	 */
	private static native int nGetNumButtons();
	
	/**
	 * Queries whether the joystick has a Z value
	 * @return true if the joystick has a Z value
	 */
	public static boolean hasZValue() {
		assert created : "The joystick has not been created.";
		return nHasZValue();
	}
	
	/**
	 * See if a particular mouse button is down.
	 * 
	 * @param button The index of the button you wish to test (0..getNumButtons())
	 * @return true if the specified button is down
	 * @see #getNumButtons()
	 */
	public static boolean isButtonDown(int button) {
		assert created : "The joystick has not been created.";
		return Joystick.button[button];
	}
	
	
	/**
	 * Native implementation of hasZValue()
	 */
	private static native boolean nHasZValue();

	/**
	 * Enable joystick buffering. Must be called after the joystick is created.
	 * @return the size of the joystick buffer in events, or 0 if no buffering
	 * can be enabled for any reason
	 */
	public static int enableBuffer() {
		assert created : "The joystick has not been created.";
		return nEnableBuffer();
	}
	
	/**
	 * Native method to read the joystick buffer
	 * 
	 * @param readBufferAddress the address of the joystick buffer
	 * @return the number of joystick events read
	 */
	private static native int nRead(int readBufferAddress);
	
	/**
	 * Reads the joystick buffer.
	 */
	public static void read() {
		assert created : "The joystick has not been created.";
		assert readBuffer != null : "Joystick buffering has not been enabled.";
		readBuffer.clear();
		readBuffer.limit(nRead(readBufferAddress) << 1);
	}
	
	/**
	 * Native method to enable the buffer
	 * @return the size of the buffer allocated, in events (1 event is 2 bytes),
	 * or 0 if no buffer can be allocated
	 */
	private static native int nEnableBuffer();

	/**
	 * Gets the next joystick event. This returns its results as if a poll() had
	 * been called.
	 * 
	 * @return true if a joystick event was read, false otherwise
	 */
	public static boolean next() {
		assert created : "The joystick has not been created.";
		assert readBuffer != null : "Joystick buffering has not been enabled.";
		
		if (readBuffer.hasRemaining()) {
			x = readBuffer.getFloat();
			y = readBuffer.getFloat();
			z = readBuffer.getFloat();
			for (int i = 0; i < button.length; i ++)
				button[i] = readBuffer.get() != (byte)0;
			readBuffer.position(readBuffer.position() + (JOYSTICK_EVENT_STRIDE - JOYSTICK_EVENT_SIZE));
			return true;
		} else
			return false;
		
	}




}
