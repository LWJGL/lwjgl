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
package org.lwjgl.input;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

/**
 * $Id$
 * <br>
 * A raw Mouse interface. This can be used to poll the current state of the
 * mouse buttons, and determine the mouse movement delta since the last poll.
 * 
 * n buttons supported, n being a native limit. A scrolly wheel is also
 * supported, if one such is available. Movement is reported as delta from
 * last position or as an absolute position. If the window has been created
 * the absolute position will be clamped to 0 - Display (width | height)
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class Mouse {

	/** 1 bit transparency for native cursor */
	public final static int		CURSOR_ONE_BIT_TRANSPARENCY	= 1;

	/** 8 bit alhpa native cursor */
	public final static int		CURSOR_8_BIT_ALPHA					= 2;

	/** animation native cursor */
	public final static int		CURSOR_ANIMATION						= 4;

	/** Mouse minimum and maximum sensitivity */
	public static final int MAX_SENSITIVITY = 8;
	public static final int MIN_SENSITIVITY = 1;

	/** Mouse sensitivity: 1...8 */
	private static int sensitivity = MAX_SENSITIVITY;

	/** Mouse constraint */
	private static int width, height;
	
	/** Has the mouse been created? */
	private static boolean		created;

	/** The mouse buttons status from the last poll */
	private static ByteBuffer	buttons;

	/** Mouse absolute X position in 16:16FP */
	private static int				x;

	/** Mouse absolute Y position in 16:16FP */
	private static int				y;

	/** Mouse X scroll position in 16:16FP */
	private static int				scrollX;

	/** Mouse Y scroll position in 16:16FP */
	private static int				scrollY;

	/** Buffer to hold the deltas dx, dy and dwheel */
	private static IntBuffer	coord_buffer;

	/** Delta X */
	private static int				dx;

	/** Delta Y */
	private static int				dy;

	/** Delta Z */
	private static int				dwheel;

	/** Number of buttons supported by the mouse */
	private static int			buttonCount									= -1;

	/** Does this mouse support a scroll wheel */
	private static boolean		hasWheel									= false;

	/** The current native cursor, if any */
	private static Cursor		currentCursor;

	/** Button names. These are set upon create(), to names like BUTTON0, BUTTON1, etc. */
	private static String[]		buttonName;

	/** hashmap of button names, for fast lookup */
	private static final Map	buttonMap									= new HashMap(16);

	/** Lazy initialization */
	private static boolean		initialized;

	/** The mouse button events from the last read */
	private static ByteBuffer	readBuffer									= null;

	/** The current mouse event button being examined */
	private static int				eventButton;

	/** The current state of the button being examined in the event queue */
	private static boolean		eventState;

	/** Buffer size in events */
	private final static int	BUFFER_SIZE									= 50;

	private static boolean		isGrabbed;

	/** Whether absolute mouse tracking is enabled */
	private static boolean		trackingEnabled								= true;
	
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
		if ((getNativeCursorCaps() & CURSOR_ONE_BIT_TRANSPARENCY) == 0)
				throw new IllegalStateException("Mouse doesn't support native cursors");
		Cursor oldCursor = currentCursor;
		currentCursor = cursor;
		if (isCreated()) {
			if (currentCursor != null) {
				nSetNativeCursor(currentCursor.getHandle());
				currentCursor.setTimeout();
			} else {
				nSetNativeCursor(null);
			}
		}
		return oldCursor;
	}

	/** Native method to set the native cursor */
	private static native void nSetNativeCursor(ByteBuffer handle) throws LWJGLException;

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
		for (int i = 0; i < 16; i++) {
			buttonName[i] = "BUTTON" + i;
			buttonMap.put(buttonName[i], new Integer(i));
		}

		initialized = true;
	}

	private static void resetMouse() {
		dx = dy = dwheel = 0;
		width = Display.getDisplayMode().getWidth() << 16;
		height = Display.getDisplayMode().getHeight() << 16;
		x = width / 2;
		y = height / 2;
	}
  
	/**
	 * "Create" the mouse. The display must first have been created.
	 * Initially, the mouse is not grabbed and the delta values are reported
	 * with respect to the center of the display.
	 * 
	 * @throws LWJGLException if the mouse could not be created for any reason
	 */
	public static void create() throws LWJGLException {
		if (!Display.isCreated()) throw new IllegalStateException("Display must be created prior to creating mouse");

		if (!initialized)
			initialize();
		if (created) { return; }
		nCreate();
		hasWheel = nHasWheel();
		created = true;
    
		// set mouse buttons
		buttonCount = nGetButtonCount();
		buttons = BufferUtils.createByteBuffer(buttonCount);
		coord_buffer = BufferUtils.createIntBuffer(3);
		setNativeCursor(currentCursor);
		setGrabbed(isGrabbed);
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
		if (!created) return;
		created = false;
		buttons = null;
		coord_buffer = null;

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
	 * <code>enableBuffer</code>.
	 * 
	 * If buffering is enabled, this method also reads all button events since last read.
	 * To use these values, you have to call <code>next</code> for each event you
	 * want to read. You can query which button caused the event by using 
	 * <code>getEventButton</code>. To get the state of that button, for that event, use
	 * <code>getEventButtonState</code>.
	 * 
	 * @see org.lwjgl.input.Mouse#next()
	 * @see org.lwjgl.input.Mouse#getEventButton()
	 * @see org.lwjgl.input.Mouse#getEventButtonState()
	 * @see org.lwjgl.input.Mouse#isButtonDown(int button) 
	 * @see org.lwjgl.input.Mouse#getX() 
	 * @see org.lwjgl.input.Mouse#getY() 
	 * @see org.lwjgl.input.Mouse#getDX() 
	 * @see org.lwjgl.input.Mouse#getDY() 
	 * @see org.lwjgl.input.Mouse#getDWheel() 
	 * @see org.lwjgl.input.Mouse#enableBuffer()
	 */
	public static void poll() {
		if (!created) throw new IllegalStateException("Mouse must be created before you can poll it");
		nPoll(coord_buffer, buttons);

		int poll_dx = coord_buffer.get(0);
		int poll_dy = coord_buffer.get(1);
		int poll_dwheel = coord_buffer.get(2);

		dx += poll_dx;
		dy += poll_dy;
		dwheel += poll_dwheel;

		// Calculate the new absolute position unless tracking is disabled
		if (trackingEnabled) {
			x += ((poll_dx * sensitivity) << 16) / MAX_SENSITIVITY;
			y += ((poll_dy * sensitivity) << 16) / MAX_SENSITIVITY;

			// clamp x, y
			if (x < 0) {
				scrollX = x;
				x = 0;
			} else if (x >= width) {
				scrollX = x - width;
				x = width - 1;
			} else {
				scrollX = 0;
			}

			if (y < 0) {
				scrollY = y;
				y = 0;
			} else if (y >= height) {
				scrollY = y - height;
				y = height - 1;
			} else {
				scrollY = 0;
			}
		} else {
			scrollX = 0;
			scrollY = 0;
		}
		
		if (readBuffer != null) {
			read();
		}
	}

	private static void read() {
		readBuffer.compact();
		int numEvents = nRead(readBuffer, readBuffer.position());
		readBuffer.position(readBuffer.position() + numEvents * 2);
		readBuffer.flip();
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
		if (!created) throw new IllegalStateException("Mouse must be created before you can poll the button state");
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
		if (!created) throw new IllegalStateException("Mouse must be created before you can enable buffering");
		readBuffer = BufferUtils.createByteBuffer(2 * BUFFER_SIZE);
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
		if (!created) throw new IllegalStateException("Mouse must be created before you can read events");
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
	 * Retrieves the absolute position. If the Display has been created
	 * x will be clamped to 0...width-1.
	 * 
	 * @return Absolute x axis position of mouse
	 */
	public static int getX() {
		return x >> 16;
	}

	/**
	 * Retrieves the absolute position. If the Display has been created
	 * y will be clamped to 0...height-1.
	 *
	 * @return Absolute y axis position of mouse
	 */
	public static int getY() {
		return y >> 16;
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
	 * @return the amount the mouse tried to move past its constraints on the X axis since the last poll
	 */
	public static int getScrollX() {
		return scrollX >> 16;
	}

	/**
	 * @return the amount the mouse tried to move past its constraints on the Y axis since the last poll
	 */
	public static int getScrollY() {
		return scrollY >> 16;
	}

	/**
	 * @return Whether or not this mouse has wheel support
	 */
	public static boolean hasWheel() {
		return hasWheel;
	}

	/**
	 * @return whether or not the mouse has grabbed the cursor
	 */
	public static boolean isGrabbed() {
		return isGrabbed;
	}

	/**
	 * Sets whether or not the mouse has grabbed the cursor 
	 * (and thus hidden). If grab is false, the getX() and getY()
	 * will return delta movement in pixels clamped to the display
	 * dimensions, from the center of the display.
	 *
	 * @param grab whether the mouse should be grabbed
	 */
	public static void setGrabbed(boolean grab) {
		isGrabbed = grab;
		if (isCreated()) {
			nGrabMouse(isGrabbed);
			resetMouse();
		}
	}

	private static native void nGrabMouse(boolean grab);

	/**
	 * Updates the cursor, so that animation can be changed if needed.
	 * This method is called automatically by the window on its update, and 
	 * shouldn't be called otherwise
	 */
	public static void updateCursor() {
		if (System.getProperty("os.name").startsWith("Win") && currentCursor != null && currentCursor.hasTimedOut()) {
			currentCursor.nextCursor();
			try {
				setNativeCursor(currentCursor);
			} catch (LWJGLException e) {
				if (Sys.DEBUG) e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sets the mouse sensitivity, which is expressed as a value from 1 to 8.
	 * Values outside this range are clamped to [1..8]. 8 is the most sensitive;
	 * other values slow down the mouse to a minimum of 1/8th its original speed.
	 * @param newSensitivity The mouse sensitivity
	 */
	public static void setSensitivity(int newSensitivity) {
		sensitivity = Math.min(MAX_SENSITIVITY, Math.max(MIN_SENSITIVITY, newSensitivity));
	}

	/**
	 * @return the current mouse sensitivity (guaranteed in the range 1..8)
	 */
	public static int getSensitivity() {
		return sensitivity;
	}
	
	/**
	 * Sets the absolute position of the mouse. The position is capped to the
	 * current size.
	 * 
	 * @param newx
	 * @param newy
	 */
	public static void setPosition(int newx, int newy) {
		x = Math.min(Math.max(0, newx << 16), width - 1);
		y = Math.min(Math.max(0, newy << 16), height - 1);
	}
	

	/**
	 * Sets the dimensions of the mouse's constraint.
	 * @param width
	 * @param height
	 */
	public static void setDimensions(int width, int height) {
		Mouse.width = width << 16;
		Mouse.height = height << 16;
		
		// Clamp the mouse absolute coordinates just in case
		if (x >= Mouse.width) {
			x = Mouse.width - 1;
		}
		if (y >= Mouse.height) {
			y = Mouse.height - 1;
		}
	}

	/**
	 * Enable or disable absolute mouse coordinate tracking.
	 * @param enabled
	 */
	public static void setTrackingEnabled(boolean enabled) {
		Mouse.trackingEnabled = enabled;
	}
	
	/**
	 * Determine if mouse coordinate tracking is enabled
	 * @return boolean
	 */
	public static boolean isTrackingEnabled() {
		return trackingEnabled;
	}
}
