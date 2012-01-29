/*
 * Copyright (c) 2002-2008 LWJGL Project
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
 * This is the Windows implementation of the Mouse.
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Mouse;

final class WindowsMouse {
	private final long hwnd;

	private final int mouse_button_count;
	private final boolean has_wheel;

	private final EventQueue event_queue = new EventQueue(Mouse.EVENT_SIZE);

	private final ByteBuffer mouse_event = ByteBuffer.allocate(Mouse.EVENT_SIZE);
	private final Object blank_cursor;

	private boolean mouse_grabbed;
	private byte[] button_states;
	private int accum_dx;
	private int accum_dy;
	private int accum_dwheel;
	private int last_x;
	private int last_y;

	WindowsMouse(long hwnd) throws LWJGLException {
		this.hwnd = hwnd;
		this.mouse_button_count = Math.min(5, WindowsDisplay.getSystemMetrics(WindowsDisplay.SM_CMOUSEBUTTONS));
		this.has_wheel = WindowsDisplay.getSystemMetrics(WindowsDisplay.SM_MOUSEWHEELPRESENT) != 0;
		this.blank_cursor = createBlankCursor();
		this.button_states = new byte[mouse_button_count];
	}

	private Object createBlankCursor() throws LWJGLException {
		int width = WindowsDisplay.getSystemMetrics(WindowsDisplay.SM_CXCURSOR);
		int height = WindowsDisplay.getSystemMetrics(WindowsDisplay.SM_CYCURSOR);
		IntBuffer pixels = BufferUtils.createIntBuffer(width*height);
		return WindowsDisplay.doCreateCursor(width, height, 0, 0, 1, pixels, null);
	}

	public boolean isGrabbed() {
		return mouse_grabbed;
	}

	public boolean hasWheel() {
		return has_wheel;
	}

	public int getButtonCount() {
		return mouse_button_count;
	}

	public void poll(IntBuffer coord_buffer, ByteBuffer buttons) {
		for (int i = 0; i < coord_buffer.remaining(); i++)
			coord_buffer.put(coord_buffer.position() + i, 0);
		int num_buttons = mouse_button_count;
		coord_buffer.put(coord_buffer.position() + 2, accum_dwheel);
		if (num_buttons > button_states.length)
			num_buttons = button_states.length;
		for (int j = 0; j < num_buttons; j++) {
			buttons.put(buttons.position() + j, button_states[j]);
		}
		if (isGrabbed()) {
			coord_buffer.put(coord_buffer.position() + 0, accum_dx);
			coord_buffer.put(coord_buffer.position() + 1, accum_dy);
		} else {
			coord_buffer.put(coord_buffer.position() + 0, last_x);
			coord_buffer.put(coord_buffer.position() + 1, last_y);
		}
		accum_dx = accum_dy = accum_dwheel = 0;
	}

	private void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
		mouse_event.clear();
		mouse_event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
		mouse_event.flip();
		event_queue.putEvent(mouse_event);
	}

	private void putMouseEvent(byte button, byte state, int dz, long nanos) {
		if (mouse_grabbed)
			putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
		else
			putMouseEventWithCoords(button, state, last_x, last_y, dz, nanos);
	}

	public void read(ByteBuffer buffer) {
		event_queue.copyEvents(buffer);
	}

	public Object getBlankCursor() {
		return blank_cursor;
	}

	public void grab(boolean grab, boolean should_center) {
		if (grab) {
			if (!mouse_grabbed) {
				mouse_grabbed = true;
				if (should_center) {
					try {
						WindowsDisplay.setupCursorClipping(hwnd);
					} catch (LWJGLException e) {
						LWJGLUtil.log("Failed to setup cursor clipping: " + e);
					}
					centerCursor();
				}
			}
		} else {
			if (mouse_grabbed) {
				mouse_grabbed = false;
				WindowsDisplay.resetCursorClipping();
			}
		}
		event_queue.clearEvents();
	}

	public void handleMouseScrolled(int event_dwheel, long millis) {
		accum_dwheel += event_dwheel;
		putMouseEvent((byte)-1, (byte)0, event_dwheel, millis*1000000);
	}

	private void centerCursor() {
		WindowsDisplay.centerCursor(hwnd);
	}

	public void setPosition(int x, int y) {
		this.last_x = x;
		this.last_y = y;
	}

	public void destroy() {
		WindowsDisplay.doDestroyCursor(blank_cursor);
	}

	public void handleMouseMoved(int x, int y, long millis, boolean should_center) {
		int dx = x - last_x;
		int dy = y - last_y;
		if (dx != 0 || dy != 0) {
			accum_dx += dx;
			accum_dy += dy;
			last_x = x;
			last_y = y;
			long nanos = millis*1000000;
			if (mouse_grabbed) {
				putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
				if (should_center)
					centerCursor();
			} else {
				putMouseEventWithCoords((byte)-1, (byte)0, x, y, 0, nanos);
			}
		}
	}

	public void handleMouseButton(byte button, byte state, long millis) {
		putMouseEvent(button, state, 0, millis*1000000);
		if (button < button_states.length)
			button_states[button] = state != 0 ? (byte)1 : (byte)0;
	}
}
