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
 * A java implementation of a LWJGL compatible Mouse event queue.
 * Currently only used by the Mac OS X implementation.
 * @author elias_naur
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

final class MouseEventQueue extends EventQueue implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static final int WHEEL_SCALE = 120;
	public static final int NUM_BUTTONS = 3;
	private static final int EVENT_SIZE = 5;

	private final int width;
	private final int height;

	private boolean grabbed;

	private final IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);

	/** The accumulated mouse deltas returned by poll() */
	private int accum_dx;
	private int accum_dy;
	private int accum_dz;

	/** The last mouse position */
	private int last_x;
	private int last_y;

	/** Saved control key state for ctrl-click right button emulation */
	private boolean saved_control_state;

	/** Event scratch array */
	private final int[] event = new int[EVENT_SIZE];

	/** Buttons array */
	private final byte[] buttons = new byte[NUM_BUTTONS];

	MouseEventQueue(int width, int height) {
		super(EVENT_SIZE);
		this.width = width;
		this.height = height;
		resetCursorToCenter();
	}

	public synchronized void setGrabbed(boolean grabbed) {
		this.grabbed = grabbed;
		resetCursorToCenter();
	}

	public synchronized boolean isGrabbed() {
		return grabbed;
	}

	private int transformY(int y) {
		return height - 1 - y;
	}

	private void resetCursorToCenter() {
		clearEvents();
		accum_dx = accum_dy = 0;
		/* Clear accumulated deltas */
		((MacOSXDisplay)Display.getImplementation()).getMouseDeltas(delta_buffer);
	}

	private boolean putMouseEvent(int button, int state, int dz) {
		if ( grabbed )
			return putMouseEventWithCoords(button, state, 0, 0, dz);
		else
			return putMouseEventWithCoords(button, state, last_x, last_y, dz);
	}

	private boolean putMouseEventWithCoords(int button, int state, int coord1, int coord2, int dz) {
		event[0] = button;
		event[1] = state;
		event[2] = coord1;
		event[3] = coord2;
		event[4] = dz;
		return putEvent(event);
	}

	public synchronized void poll(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
		if ( grabbed ) {
			coord_buffer.put(0, accum_dx);
			coord_buffer.put(1, accum_dy);
		} else {
			coord_buffer.put(0, last_x);
			coord_buffer.put(1, last_y);
		}
		coord_buffer.put(2, accum_dz);
		accum_dx = accum_dy = accum_dz = 0;
		int old_position = buttons_buffer.position();
		buttons_buffer.put(buttons, 0, buttons.length);
		buttons_buffer.position(old_position);
	}

	private synchronized void setCursorPos(int x, int y) {
		y = transformY(y);
		if ( grabbed )
			return;
		int dx = x - last_x;
		int dy = y - last_y;
		accum_dx += dx;
		accum_dy += dy;
		last_x = x;
		last_y = y;
		putMouseEventWithCoords(-1, 0, x, y, 0);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	private void handleButton(MouseEvent e) {
		byte state;
		switch (e.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				state = 1;
				break;
			case MouseEvent.MOUSE_RELEASED:
				state = 0;
				break;
			default:
				throw new IllegalArgumentException("Not a valid event ID: " + e.getID());
		}
		byte button;
		switch (e.getButton()) {
			case MouseEvent.NOBUTTON:
				// Nothing to do, so return
				return;
			case MouseEvent.BUTTON1:
				// Emulate right click if ctrl is down
				if (state == 1)
					saved_control_state = e.isControlDown();
				if (saved_control_state) {
					if (buttons[1] == state)
						return; // ignore
					button = (byte)1;
				} else {
					button = (byte)0;
				}
				break;
			case MouseEvent.BUTTON2:
				button = (byte)2;
				break;
			case MouseEvent.BUTTON3:
				if (buttons[1] == state)
					return; // ignore
				button = (byte)1;
				break;
			default:
				throw new IllegalArgumentException("Not a valid button: " + e.getButton());
		}
		setButton(button, state);
	}

	public void mousePressed(MouseEvent e) {
		handleButton(e);
	}

	private synchronized void setButton(byte button, byte state) {
		buttons[button] = state;
		putMouseEvent(button, state, 0);
	}

	public void mouseReleased(MouseEvent e) {
		handleButton(e);
	}

	private void handleMotion(MouseEvent e) {
		if ( grabbed ) {
			updateDeltas();
		} else {
			setCursorPos(e.getX(), e.getY());
		}
	}

	public void mouseDragged(MouseEvent e) {
		handleMotion(e);
	}

	public void mouseMoved(MouseEvent e) {
		handleMotion(e);
	}

	private synchronized void handleWheel(int amount) {
		accum_dz += amount;
		putMouseEvent(-1, 0, amount);
	}

	private void updateDeltas() {
		if ( !grabbed )
			return;
		synchronized ( this ) {
			((MacOSXDisplay)Display.getImplementation()).getMouseDeltas(delta_buffer);
			int dx = delta_buffer.get(0);
			int dy = -delta_buffer.get(1);
			if ( dx != 0 || dy != 0 ) {
				putMouseEventWithCoords(-1, 0, dx, dy, 0);
				accum_dx += dx;
				accum_dy += dy;
			}
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		int wheel_amount = -e.getWheelRotation() * WHEEL_SCALE;
		handleWheel(wheel_amount);
	}
}
