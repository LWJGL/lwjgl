/* 
 * Copyright (c) 2002-2004 Lightweight Java Game Library Project
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
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Window;
import org.lwjgl.LWJGLException;

/**
 * $Id$
 * <br>
 * A raw Mouse interface. This can be used to poll the current state of the
 * mouse buttons, and determine the mouse movement delta since the last poll.
 * 
 * n buttons supported, n being a native limit. A scrolly wheel is also
 * supported, if one such is available. Movement is reported as delta from
 * last position or as an absolute position. If the window has been created
 * the absolute position will be clamped to 0 - Window (width | height)
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class Mouse {
	
	/** 1 bit transparency for native cursor */
	public final static int CURSOR_ONE_BIT_TRANSPARENCY = 1;
	
	/** 8 bit alhpa native cursor */
	public final static int CURSOR_8_BIT_ALPHA = 2;
	
	/** animation native cursor */
	public final static int CURSOR_ANIMATION = 4;

	/** Has the mouse been created? */
	private static boolean created;

	/** The mouse buttons status from the last poll */
	private static ByteBuffer buttons;

	/** X */
	private static int x;

	/** Y */
	private static int y;
	
	/** Buffer to hold the deltas dx, dy and dwheel */
	private static IntBuffer coord_buffer;

	/** Delta X */
	private static int dx;

	/** Delta Y */
	private static int dy;

	/** Delta Z */
	private static int dwheel;

	/** Number of buttons supported by the mouse */
	private static int buttonCount = -1;

	/** Does this mouse support a scroll wheel */
	private static boolean hasWheel = false;

	/** The current native cursor, if any */
	private static Cursor currentCursor;

	/** Button names. These are set upon create(), to names like BUTTON0, BUTTON1, etc. */
	private static String[] buttonName;

	/** hashmap of button names, for fast lookup */
	private static final Map buttonMap = new HashMap(16);

	/** Lazy initialization */
	private static boolean initialized;

	/** The mouse button events from the last read */
	private static ByteBuffer readBuffer = null;

	/** The current mouse event button being examined */
	private static int eventButton;

	/** The current state of the button being examined in the event queue */
	private static boolean eventState;

	/** Buffer size in events */
	private final static int BUFFER_SIZE = 50;

	/**
	 * Mouse cannot be constructed.
	 */
	private Mouse() {
	}

	/**
	 * Gets the currently bound native cursor, if any.
	 *
	 * @return the currently bound native cursor, if any.
	 */
	public static Cursor getNativeCursor() {
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
	 * The initial position of the cursor is the middle of the window,
	 * that is, {window_width/2, window_height/2}.
	 * The cursor will be moved to this origin when a
	 * native cursor is set and the previous cursor is null.
	 *
	 * @param cursor the native cursor object to bind. May be null.
	 * @return The previous Cursor object set, or null.
	 * @throws LWJGLException if the cursor could not be set for any reason
	 */
	public static Cursor setNativeCursor(Cursor cursor) throws LWJGLException {
		if (!created)
			throw new IllegalStateException("Create the Mouse before setting the native cursor");
		if ((getNativeCursorCaps() & CURSOR_ONE_BIT_TRANSPARENCY) == 0)
			throw new IllegalStateException("Mouse doesn't support native cursors");
		Cursor oldCursor = currentCursor;
		currentCursor = cursor;
		if (currentCursor != null) {
			nSetNativeCursor(currentCursor.getHandle());
	 		currentCursor.setTimeout();
		x = Window.getWidth() / 2;
		y = Window.getHeight() / 2;      
		} else {
			nSetNativeCursor(0);
		}
		return oldCursor;
	}

	/** Native method to set the native cursor */
	private static native void nSetNativeCursor(long handle) throws LWJGLException;

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
		Sys.initialize();
		
		// Assign names to all the buttons
		buttonName = new String[16];
		for (int i = 0; i < 16; i ++) {
			buttonName[i] = "BUTTON" + i;
			buttonMap.put(buttonName[i], new Integer(i));
		}
		
		initialized = true;
	}

	/**
	 * "Create" the mouse. The display must first have been created.
	 * 
	 * @throws LWJGLException if the mouse could not be created for any reason
	 */
	public static void create() throws LWJGLException {
		
		if (!Window.isCreated())
			throw new IllegalStateException("Window must be created prior to creating mouse");
		
		initialize();
		if (created) {
			return;
		}
		nCreate();
		hasWheel = nHasWheel();
		created = true;
		currentCursor = null;
		dx = dy = dwheel = 0;

		// set mouse buttons
		buttonCount = nGetButtonCount();
		buttons = BufferUtils.createByteBuffer(buttonCount);
		coord_buffer = BufferUtils.createIntBuffer(3);
	}

	/** Native query of wheel support */
	private static native boolean nHasWheel();

	/** Native query of button count */
	private static native int nGetButtonCount();

	/**
	 * Native method to create the mouse.
	 * 
	 * @return true if the mouse was created
	 */
	private static native void nCreate();

	/**
	 * @return true if the mouse has been created
	 */
	public static boolean isCreated() {
		return created;
	}
	
	/**
	 * @return true if buffering is enabled
	 */
	public static boolean isBuffered() {
		return readBuffer != null;
	}

	/**
	 * "Destroy" the mouse.
	 */
	public static void destroy() {
		if (currentCursor != null) {
			try {
				setNativeCursor(null);
			} catch (LWJGLException e) {
				if (Sys.DEBUG)
					e.printStackTrace();
			}
		}
		if (!created)
			return;
		created = false;
		buttons = null;
		coord_buffer = null;
		currentCursor = null;

		nDestroy();
	}

	/**
	 * Native method the destroy the mouse
	 */
	private static native void nDestroy();

	/**
	 * Polls the mouse for its current state. Access the polled values using the
	 * get<value> methods.
	 * By using this method, it is possible to "miss" mouse click events if you don't
	 * poll fast enough. To receive all button events, enable buffering by calling 
	 * <code>enableBuffer</code>, and read those events by calling <code>read</code>
	 * 
	 * @see org.lwjgl.input.Mouse#isButtonDown(int button) 
	 * @see org.lwjgl.input.Mouse#getX() 
	 * @see org.lwjgl.input.Mouse#getY() 
	 * @see org.lwjgl.input.Mouse#getDX() 
	 * @see org.lwjgl.input.Mouse#getDY() 
	 * @see org.lwjgl.input.Mouse#getDWheel() 
	 * @see org.lwjgl.input.Mouse#enableBuffer()
	 * @see org.lwjgl.input.Mouse#read()	
	 */
	public static void poll() {
		if (!created)
			throw new IllegalStateException("Mouse must be created before you can poll it");
		nPoll(coord_buffer, buttons);
		
		int poll_dx = coord_buffer.get(0);
		int poll_dy = coord_buffer.get(1);
		int poll_dwheel = coord_buffer.get(2);
		// set absolute position
		x += poll_dx;
		y += poll_dy;
		dx += poll_dx;
		dy += poll_dy;
		dwheel += poll_dwheel;

		// if window has been created, clamp to edges
		if(Window.isCreated()) {
			// clamp x, y
			if (x < 0) {
				x = 0;
			} else if (x > Window.getWidth()) {
				x = Window.getWidth();
			}
			
			if (y < 0) {
				y = 0;
			} else if (y > Window.getHeight()) {
				y = Window.getHeight();
			}
		}
	}

	/**
	 * Native method to poll the mouse
	 */
	private static native void nPoll(IntBuffer coord_buffer, ByteBuffer buttons);

	/**
	 * See if a particular mouse button is down.
	 * 
	 * @param button The index of the button you wish to test (0..getButtonCount-1)
	 * @return true if the specified button is down
	 */
	public static boolean isButtonDown(int button) {
		if (!created)
			throw new IllegalStateException("Mouse must be created before you can poll the button state");
		if (button >= buttonCount || button < 0)
			return false;
		else
			return buttons.get(button) == 1;
	}
	
	/**
	 * Gets a button's name
	 * @param button The button
	 * @return a String with the button's human readable name in it or null if the button is unnamed
	 */
	public static String getButtonName(int button) {
		if (button >= buttonName.length || button < 0)
			return null;
		else
			return buttonName[button];
	}
	
	/**
	 * Get's a button's index. If the button is unrecognised then -1 is returned.
	 * @param buttonName The button name
	 */
	public static int getButtonIndex(String buttonName) {
		Integer ret = (Integer) buttonMap.get(buttonName);
		if (ret == null)
			return -1;
		else
			return ret.intValue();
	}

	/**
	 * Enable mouse button buffering. Must be called after the mouse is created.
	 */
	public static void enableBuffer() throws LWJGLException {
		if (!created)
			throw new IllegalStateException("Mouse must be created before you can enable buffering");
		readBuffer = BufferUtils.createByteBuffer(2*BUFFER_SIZE);
		readBuffer.limit(0);
		nEnableBuffer();
	}

	/**
	 * Native method to enable the buffer
	 * @return the event buffer,
	 * or null if no buffer can be allocated
	 */
	private static native void nEnableBuffer() throws LWJGLException;

	/**
	 * Reads all button events since last read.
	 * To use these values, you have to call <code>next</code> for each event you
	 * want to read. You can query which button caused the event by using 
	 * <code>getEventButton</code>. To get the state of that button, for that event, use
	 * <code>getEventButtonState</code>.
	 * 
	 * @see org.lwjgl.input.Mouse#next()
	 * @see org.lwjgl.input.Mouse#enableBuffer()
	 * @see org.lwjgl.input.Mouse#getEventButton()
	 * @see org.lwjgl.input.Mouse#getEventButtonState()
	 */
	public static void read() {
		if (!created)
			throw new IllegalStateException("Mouse must be created before you can read events");
		if (readBuffer == null)
			throw new IllegalStateException("Event buffering must be enabled before you can read events");
		readBuffer.compact();
		int numEvents = nRead(readBuffer, readBuffer.position());
		readBuffer.position(readBuffer.position() + numEvents*2);
		readBuffer.flip();
	}

	/**
	 * Native method to read the keyboard buffer
	 * @return the total number of events read.
	 */
	private static native int nRead(ByteBuffer buffer, int buffer_position);

	/**
	 * Gets the next mouse event. You can query which button caused the event by using 
	 * <code>getEventButton()</code>. To get the state of that key, for that event, use
	 * <code>getEventButtonState</code>.
	 * @see org.lwjgl.input.Mouse#getEventButton()
	 * @see org.lwjgl.input.Mouse#getEventButtonState()
	 * @return true if a mouse event was read, false otherwise
	 */
	public static boolean next() {
		if (!created)
			throw new IllegalStateException("Mouse must be created before you can read events");
		if (readBuffer == null)
			throw new IllegalStateException("Event buffering must be enabled before you can read events");

		if (readBuffer.hasRemaining()) {
			eventButton = readBuffer.get() & 0xFF;
			eventState = readBuffer.get() != 0;
			return true;
		} else
			return false;
	}
	
	/**
	 * @return Current events button
	 */
	public static int getEventButton() {
		return eventButton;
	}
	
	/**
	 * @return Current events button state
	 */
	public static boolean getEventButtonState() {
		return eventState;
	}

	/**
	 * Retrieves the absolute position. If the Window has been created
	 * x will be clamped to 0 - Window.getWidth().
	 * 
	 * @return Absolute x axis position of mouse
	 */
	public static int getX() {
		return x;
	}

	/**
	 * Retrieves the absolute position. If the Window has been created
	 * y will be clamped to 0 - Window.getHeight().
	 *
	 * @return Absolute y axis position of mouse
	 */
	public static int getY() {
		return y;
	}	
	
	/**
	 * @return Movement on the x axis since last time getDX() was called
	 */
	public static int getDX() {
		int result = dx;
		dx = 0;
		return result;
	}

	/**
	 * @return Movement on the y axis since last time getDY() was called
	 */
	public static int getDY() {
		int result = dy;
		dy = 0;
		return result;
	}

	/**
	 * @return Movement of the wheel since last time getDWheel() was called
	 */
	public static int getDWheel() {
		int result = dwheel;
		dwheel = 0;
		return result;
	}

	/**
	 * @return Number of buttons on this mouse
	 */
	public static int getButtonCount() {
		return buttonCount;
	}

	/**
	 * @return Whether or not this mouse has wheel support
	 */
	public static boolean hasWheel() {
		return hasWheel;
	}

	/**
	 * Updates the cursor, so that animation can be changed if needed.
	 * This method is called automatically by the window on its update. 
	 */
	public static void updateCursor() {
		if(currentCursor != null && currentCursor.hasTimedOut()) {
			currentCursor.nextCursor();
			try {
				setNativeCursor(currentCursor);
			} catch (LWJGLException e) {
				if (Sys.DEBUG)
					e.printStackTrace();
			}
		}
	}
}
