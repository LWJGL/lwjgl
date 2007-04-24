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
 * This is the Windows implementation of the Keyboard.
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.CharBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;

final class WindowsKeyboard {
	private final static int MAPVK_VK_TO_VSC = 0;

	private final static int BUFFER_SIZE = 50;

	private final long hwnd;
	private final ByteBuffer keyboard_state;
	private final byte[] key_down_buffer = new byte[Keyboard.KEYBOARD_SIZE];
	private final EventQueue event_queue = new EventQueue(Keyboard.EVENT_SIZE);
	private final ByteBuffer tmp_event = ByteBuffer.allocate(Keyboard.EVENT_SIZE);
	private final boolean unicode;
	private final CharBuffer unicode_buffer;
	private final ByteBuffer ascii_buffer;

	private boolean grabbed;

	public WindowsKeyboard(long hwnd) throws LWJGLException {
		this.hwnd = hwnd;
		keyboard_state = BufferUtils.createByteBuffer(256);
		unicode = isWindowsNT();
		if (unicode) {
			unicode_buffer = BufferUtils.createCharBuffer(BUFFER_SIZE);
			ascii_buffer = null;
		} else {
			unicode_buffer = null;
			// ToAscii returns at most 2 characters
			ascii_buffer = BufferUtils.createByteBuffer(2);
		}
	}
	private static native boolean isWindowsNT();

	public void destroy() {
	}
	
	public void grab(boolean grab) {
		if(grab) {
			if (!grabbed) {
				grabbed = true;
			}
		} else {
			if (grabbed) {
				grabbed = false;
			}	
		}
	}

	public void poll(ByteBuffer keyDownBuffer) {
		int old_position = keyDownBuffer.position();
		keyDownBuffer.put(key_down_buffer);
		keyDownBuffer.position(old_position);
	}
	
	private void translate(int virt_key, byte state, long nanos) {
		int keycode = MapVirtualKey(virt_key, MAPVK_VK_TO_VSC); 
		if (state != 0) {
			if (virt_key != 0 && GetKeyboardState(keyboard_state) != 0) {
				// Mark key down in the scan code
				int key_down_code = keycode & 0x7fff;
				int num_chars;
				if (unicode) {
					unicode_buffer.clear();
					num_chars = ToUnicode(virt_key, 
							key_down_code,
							keyboard_state,
							unicode_buffer,
							unicode_buffer.capacity(), 0);
				} else {
					ascii_buffer.clear();
					num_chars = ToAscii(virt_key, 
							key_down_code,
							keyboard_state,
							ascii_buffer,
							0);
				}
				if (num_chars > 0) {
					int current_char = 0;
					do {
						int char_int;
						if (unicode) {
							char_int = ((int)unicode_buffer.get()) & 0xFFFF;
						} else {
							char_int = ((int)ascii_buffer.get()) & 0xFF;
						}
						if (current_char >= 1) {
							putEvent(0, (byte)0, char_int, nanos);
						} else {
							putEvent(virt_key, state, char_int, nanos);
						}
						current_char++;
					} while (current_char < num_chars);
				} else {
					putEvent(virt_key, state, 0, nanos);
				}
			} else {
				putEvent(virt_key, state, 0, nanos);
			}
		} else {
			putEvent(virt_key, state, 0, nanos);
		}
	}
	private static native int MapVirtualKey(int uCode, int uMapType);
	private static native int ToUnicode(int wVirtKey, int wScanCode, ByteBuffer lpKeyState, CharBuffer pwszBuff, int cchBuff, int flags);
	private static native int ToAscii(int wVirtKey, int wScanCode, ByteBuffer lpKeyState, ByteBuffer lpChar, int flags);
	private static native int GetKeyboardState(ByteBuffer lpKeyState);
	private static native int GetKeyState(int virt_key);

	private void putEvent(int virt_key, byte state, int ch, long nanos) {
		int keycode = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
System.out.println("virt_key = " + Integer.toHexString(virt_key) + " = " + virt_key + " | keycode = " + Keyboard.getKeyName(keycode));
		if (keycode < key_down_buffer.length)
			key_down_buffer[keycode] = state;
		tmp_event.clear();
		tmp_event.putInt(keycode).put(state).putInt(ch).putLong(nanos);
		tmp_event.flip();
		event_queue.putEvent(tmp_event);
	}

	private boolean checkShiftKey(int virt_key, byte state) {
		int key_state = (GetKeyState(virt_key) >>> 15) & 0x1; 
		int lwjgl_code = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
		return (key_down_buffer[lwjgl_code] == 1 - state) && (key_state == state);
	}

	private int translateShift(int scan_code, byte state) {
		int state_mask = state != 0 ? 0x8000 : 0x0000;
		if (checkShiftKey(WindowsKeycodes.VK_LSHIFT, state)) {
			return WindowsKeycodes.VK_LSHIFT;
		} else if (checkShiftKey(WindowsKeycodes.VK_RSHIFT, state)) {
			return WindowsKeycodes.VK_RSHIFT;
		} else {
			if (scan_code== 0x2A)
				return WindowsKeycodes.VK_LSHIFT;
			else {
				if (scan_code == 0x36)
					return WindowsKeycodes.VK_RSHIFT;
				else
					return WindowsKeycodes.VK_LSHIFT;
			}
		}
	}

	private int translateExtended(int virt_key, int scan_code, byte state, boolean extended) {
		switch (virt_key) {
			case WindowsKeycodes.VK_SHIFT:
				return translateShift(scan_code, state);
			case WindowsKeycodes.VK_CONTROL:
				return extended ? WindowsKeycodes.VK_RCONTROL : WindowsKeycodes.VK_LCONTROL;
			case WindowsKeycodes.VK_MENU:
				return extended ? WindowsKeycodes.VK_RMENU : WindowsKeycodes.VK_LMENU;
			default:
				return virt_key;
		}
	}

	public void handleKey(int virt_key, int scan_code, boolean extended, byte event_state, long millis) {
		if (isWindowsNT())
			virt_key = translateExtended(virt_key, scan_code, event_state, extended);
		translate(virt_key, event_state, millis*1000000);
	}

	public void read(ByteBuffer buffer) {
		event_queue.copyEvents(buffer);
	}
}
