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
 * This is the Windows implementation of the Mouse.
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.CharBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;

final class WindowsMouse {
	private final static int BUFFER_SIZE = 50;
	private final static int BUTTON_STATES_SIZE = 7;

	private final static int DIMOFS_X = 0;
	private final static int DIMOFS_Y = 4;
	private final static int DIMOFS_Z = 8;
	private final static int DIMOFS_BUTTON0 = 12;
	private final static int DIMOFS_BUTTON1 = 13;
	private final static int DIMOFS_BUTTON2 = 14;
	private final static int DIMOFS_BUTTON3 = 15;

	private final long hwnd;
	private final WindowsDirectInput dinput;
	private final WindowsDirectInputDevice mouse;

	private final int mouse_button_count;
	private final boolean has_wheel;

	private final EventQueue event_queue = new EventQueue(Mouse.EVENT_SIZE);
	/* Buffer to hold a DIMOUSESTATE */
	private final ByteBuffer mouse_state;
	private final IntBuffer temp_data_buffer;

	private final ByteBuffer mouse_event = ByteBuffer.allocate(Mouse.EVENT_SIZE);

	private boolean mouse_grabbed;
	private byte[] win32_message_button_states = new byte[BUTTON_STATES_SIZE];
	private int accum_dwheel;
	private int last_x;
	private int last_y;

	public WindowsMouse(WindowsDirectInput dinput, long hwnd) throws LWJGLException {
		this.hwnd = hwnd;
		this.dinput = dinput;
		try {
			mouse = dinput.createDevice(WindowsDirectInput.MOUSE_TYPE);
			try {
				mouse.setDataFormat(WindowsDirectInput.MOUSE_TYPE);
				mouse.setBufferSize(BUFFER_SIZE);
				if (!acquireNonExclusive())
					throw new LWJGLException("Failed to acquire mouse non-exclusive");
			} catch (LWJGLException e) {
				mouse.release();
				throw e;
			}
		} catch (LWJGLException e) {
			dinput.release();
			throw e;
		}
		MouseEnumerator enumerator = new MouseEnumerator();
		mouse.enumObjects(enumerator);
		this.mouse_button_count = Math.min(enumerator.getButtonCount(), 4);
		this.has_wheel = enumerator.hasWheel();
		mouse_state = BufferUtils.createByteBuffer(3*4 + 4);
		temp_data_buffer = BufferUtils.createIntBuffer(BUFFER_SIZE*WindowsDirectInputDevice.DATA_SIZE);
	}

	public boolean hasWheel() {
		return has_wheel;
	}

	public int getButtonCount() {
		return mouse_button_count;
	}

	private boolean acquire(int flags) {
		try {
			mouse.setCooperateLevel(hwnd, flags);
			mouse.acquire();
			return true;
		} catch (LWJGLException e) {
			LWJGLUtil.log("Failed to acquire mouse: " + e);
			return false;
		}
	}

	private boolean acquireNonExclusive() {
		return acquire(WindowsDirectInputDevice.DISCL_NONEXCLUSIVE | WindowsDirectInputDevice.DISCL_FOREGROUND) ||
			acquire(WindowsDirectInputDevice.DISCL_NONEXCLUSIVE | WindowsDirectInputDevice.DISCL_BACKGROUND);
	}

	public void destroy() {
		mouse.unacquire();
		mouse.release();
		dinput.release();
	}

	public void poll(IntBuffer coord_buffer, ByteBuffer buttons) {
		int ret = mouse.acquire();
		if (ret != WindowsDirectInput.DI_OK && ret != WindowsDirectInput.DI_NOEFFECT)
			return;
		mouse.poll();
		for (int i = 0; i < coord_buffer.remaining(); i++)
			coord_buffer.put(coord_buffer.position() + i, 0);
		mouse_state.clear();
		ret = mouse.getDeviceState(mouse_state);
		int mouse_state_lx = mouse_state.getInt();
		int mouse_state_ly = mouse_state.getInt();
		int mouse_state_lz = mouse_state.getInt();
		int num_buttons = mouse_button_count;
		if (mouse_grabbed || ret == WindowsDirectInput.DI_OK) {
			if (ret != WindowsDirectInput.DI_OK) { 
				LWJGLUtil.log("Error getting mouse state: (0x" + Integer.toHexString(ret) + ")");
				return;
			}

			coord_buffer.put(coord_buffer.position() + 2, mouse_state_lz);
			if (num_buttons > buttons.remaining())
				num_buttons = buttons.remaining();
			for (int j = 0; j < num_buttons; j++) {
				byte button_state = (mouse_state.get() & 0x80) != 0 ? (byte)1 : (byte)0;
				buttons.put(buttons.position() + j, button_state);
				// track the button state in the windows message buffer state array
				// to get accurate button information when releasing a grab
				win32_message_button_states[j] = button_state;
			}
		} else {
			coord_buffer.put(coord_buffer.position() + 2, accum_dwheel);
			if (num_buttons > win32_message_button_states.length)
				num_buttons = win32_message_button_states.length;
			for (int j = 0; j < num_buttons; j++) {
				buttons.put(buttons.position() + j, win32_message_button_states[j]);
			}
		}
		accum_dwheel = 0;
		if (mouse_grabbed) {
			coord_buffer.put(coord_buffer.position() + 0, mouse_state_lx);
			coord_buffer.put(coord_buffer.position() + 1, -mouse_state_ly);
		} else {
			coord_buffer.put(coord_buffer.position() + 0, last_x);
			coord_buffer.put(coord_buffer.position() + 1, last_y);
		}
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

	private void copyDXEvents(IntBuffer buffer) {
		int buffer_index = 0;
		int dx = 0, dy = 0, dwheel = 0;
		byte button_state;
		int i;
		long nanos = 0;
		while (buffer.hasRemaining()) {
			int dwOfs = buffer.get();
			int dwData = buffer.get();
			long dwTimeStamp = ((long)buffer.get()) & 0xFFFFFFFF;
			nanos = dwTimeStamp*1000000;
			button_state = (dwData & 0x80) != 0 ? (byte)1 : (byte)0;
			switch (dwOfs) {
				case DIMOFS_BUTTON0:
					putMouseEventWithCoords((byte)0, button_state, dx, -dy, dwheel, nanos);
					dx = dy = dwheel = 0;
					break;
				case DIMOFS_BUTTON1:
					putMouseEventWithCoords((byte)1, button_state, dx, -dy, dwheel, nanos);
					dx = dy = dwheel = 0;
					break;
				case DIMOFS_BUTTON2:
					putMouseEventWithCoords((byte)2, button_state, dx, -dy, dwheel, nanos);
					dx = dy = dwheel = 0;
					break;
				case DIMOFS_BUTTON3:
					putMouseEventWithCoords((byte)3, button_state, dx, -dy, dwheel, nanos);
					dx = dy = dwheel = 0;
					break;
				case DIMOFS_X:
					dx += dwData;
					break;
				case DIMOFS_Y:
					dy += dwData;
					break;
				case DIMOFS_Z:
					dwheel += dwData;
					break;
			}
		}
		if (dx != 0 || dy != 0 || dwheel != 0)
			putMouseEventWithCoords((byte)-1, (byte)0, dx, -dy, dwheel, nanos);
	}

	private void readDXBuffer() {
		int ret = mouse.acquire();
		if (ret != WindowsDirectInput.DI_OK && ret != WindowsDirectInput.DI_NOEFFECT)
			return;
		mouse.poll();
		temp_data_buffer.clear();
		ret = mouse.getDeviceData(temp_data_buffer);

		switch (ret) {
			case WindowsDirectInput.DI_OK:
				break;
			case WindowsDirectInput.DI_BUFFEROVERFLOW:
				LWJGLUtil.log("Mouse buffer overflowed");
				break;
			case WindowsDirectInput.DIERR_INPUTLOST:
				LWJGLUtil.log("Mouse input lost");
				break;
			case WindowsDirectInput.DIERR_NOTACQUIRED:
				LWJGLUtil.log("Mouse not acquired");
				break;
			default:
				LWJGLUtil.log("unknown mouse error (" + Integer.toHexString(ret) + ")");
				break;
		}
	}

	public final void flush() {
		readDXBuffer();
		temp_data_buffer.clear();
	}

	public void read(ByteBuffer buffer) {
		readDXBuffer();
		if (mouse_grabbed) {
			temp_data_buffer.flip();
			copyDXEvents(temp_data_buffer);
		}
		event_queue.copyEvents(buffer);
	}
		
	public void grab(boolean grab) {
		if(grab) {
			if (!mouse_grabbed) {
				flush();
				mouse_grabbed = true;
				mouse.unacquire();
				if (!acquire(WindowsDirectInputDevice.DISCL_EXCLUSIVE | WindowsDirectInputDevice.DISCL_FOREGROUND))
					LWJGLUtil.log("Failed to reset cooperative mode");
			}
		} else {
			if (mouse_grabbed) {
				mouse_grabbed = false;
				mouse.unacquire();
				acquireNonExclusive();
			}	
		}
		event_queue.clearEvents();
	}

	public void handleMouseScrolled(int event_dwheel, long millis) {
		accum_dwheel += event_dwheel;
		putMouseEvent((byte)-1, (byte)0, event_dwheel, millis*1000000);
	}

	public void handleMouseMoved(int x, int y, long millis) {
		int dx;
		int dy;
		dx = x - last_x;
		dy = y - last_y;
		last_x = x;
		last_y = y;
		long nanos = millis*1000000;
		if (mouse_grabbed) {
			putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
		} else {
			putMouseEventWithCoords((byte)-1, (byte)0, x, y, 0, nanos);
		}		
	}

	public void handleMouseButton(byte button, byte state, long millis) {
		putMouseEvent(button, state, 0, millis*1000000);
		if (button < BUTTON_STATES_SIZE)
			win32_message_button_states[button] = state != 0 ? (byte)1 : (byte)0;
	}

	private static class MouseEnumerator implements WindowsDirectInputDeviceObjectCallback {
		private int button_count;
		private boolean has_wheel;

		public int getButtonCount() {
			return button_count;
		}

		public boolean hasWheel() {
			return has_wheel;
		}

		public boolean nextObject(int type, String name) {
			LWJGLUtil.log("Found mouse object: " + name);
			switch (type) {
				case WindowsDirectInputDevice.GUID_ZAxis:
					has_wheel = true;
					break;
				case WindowsDirectInputDevice.GUID_Button:
					button_count++;
					break;
				default:
					break;
			}
			return true;
		}
	}
}
