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
 * This is the Windows implementation of the Keyboard.
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

final class WindowsKeyboard {

	private final byte[] key_down_buffer = new byte[Keyboard.KEYBOARD_SIZE];
	private final byte[] virt_key_down_buffer = new byte[Keyboard.KEYBOARD_SIZE];
	private final EventQueue event_queue = new EventQueue(Keyboard.EVENT_SIZE);
	private final ByteBuffer tmp_event = ByteBuffer.allocate(Keyboard.EVENT_SIZE);

	private boolean has_retained_event; // Indicates if we're waiting for a WM_CHAR
	private int retained_key_code;
	private byte retained_state;
	private int retained_char;
	private long retained_millis;
	private boolean retained_repeat;

	WindowsKeyboard() throws LWJGLException {
	}

	private static native boolean isWindowsNT();

	boolean isKeyDown(int lwjgl_keycode) {
		return key_down_buffer[lwjgl_keycode] == 1;
	}

	void poll(ByteBuffer keyDownBuffer) {
		// Handle shift key release while both are pressed.
		// Windows will not send an up event for the first button that was released in this case.
		// There will only be one up event, for the last button only. We handle this problem
		// here, using asynchronous state queries.
		if ( isKeyDown(Keyboard.KEY_LSHIFT) && isKeyDown(Keyboard.KEY_RSHIFT) ) {
			if ( !isKeyPressedAsync(WindowsKeycodes.VK_LSHIFT) ) handleKey(WindowsKeycodes.VK_SHIFT, Keyboard.KEY_LSHIFT, false, (byte)0, 0L, false);
			if ( !isKeyPressedAsync(WindowsKeycodes.VK_RSHIFT) ) handleKey(WindowsKeycodes.VK_SHIFT, Keyboard.KEY_RSHIFT, false, (byte)0, 0L, false);
		}

		int old_position = keyDownBuffer.position();
		keyDownBuffer.put(key_down_buffer);
		keyDownBuffer.position(old_position);
	}

	private static native int MapVirtualKey(int uCode, int uMapType);
	private static native int ToUnicode(int wVirtKey, int wScanCode, ByteBuffer lpKeyState, CharBuffer pwszBuff, int cchBuff, int flags);
	private static native int ToAscii(int wVirtKey, int wScanCode, ByteBuffer lpKeyState, ByteBuffer lpChar, int flags);
	private static native int GetKeyboardState(ByteBuffer lpKeyState);
	private static native short GetKeyState(int virt_key);
	private static native short GetAsyncKeyState(int virt_key);

	private void putEvent(int keycode, byte state, int ch, long millis, boolean repeat) {
		tmp_event.clear();
		tmp_event.putInt(keycode).put(state).putInt(ch).putLong(millis*1000000).put(repeat ? (byte)1 : (byte)0);
		tmp_event.flip();
		event_queue.putEvent(tmp_event);
	}

	private static int translateExtended(int virt_key, int scan_code, boolean extended) {
		switch (virt_key) {
			case WindowsKeycodes.VK_SHIFT:
				return scan_code == 0x36 ? WindowsKeycodes.VK_RSHIFT : WindowsKeycodes.VK_LSHIFT;
			case WindowsKeycodes.VK_CONTROL:
				return extended ? WindowsKeycodes.VK_RCONTROL : WindowsKeycodes.VK_LCONTROL;
			case WindowsKeycodes.VK_MENU:
				return extended ? WindowsKeycodes.VK_RMENU : WindowsKeycodes.VK_LMENU;
			default:
				return virt_key;
		}
	}

	private void flushRetained() {
		if (has_retained_event) {
			has_retained_event = false;
			putEvent(retained_key_code, retained_state, retained_char, retained_millis, retained_repeat);
		}
	}

	private static boolean isKeyPressed(int state) {
		return (state & 1) == 1;
	}

	private static boolean isKeyPressedAsync(int virt_key) {
		return (GetAsyncKeyState(virt_key) & 0x8000) != 0;
	}

	void handleKey(int virt_key, int scan_code, boolean extended, byte event_state, long millis, boolean repeat) {
		virt_key = translateExtended(virt_key, scan_code, extended);
		if ( !repeat && isKeyPressed(event_state) == isKeyPressed(virt_key_down_buffer[virt_key]) )
			return;

		flushRetained();
		has_retained_event = true;
		int keycode = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
		if (keycode < key_down_buffer.length) {
			key_down_buffer[keycode] = event_state;
			virt_key_down_buffer[virt_key] = event_state;
		}
		retained_key_code = keycode;
		retained_state = event_state;
		retained_millis = millis;
		retained_char = 0;
		retained_repeat = repeat;
	}

	void fireLostKeyEvents() {
		for ( int i = 0; i < virt_key_down_buffer.length; i++ ) {
			if ( isKeyPressed(virt_key_down_buffer[i]) && !isKeyPressedAsync(i) )
				handleKey(i, 0, false, (byte)0, 0L, false);
		}
	}

	void handleChar(int event_char, long millis, boolean repeat) {
		if (has_retained_event && retained_char != 0)
			flushRetained();
		if (!has_retained_event) {
			putEvent(0, (byte)0, event_char, millis, repeat);
		} else
			retained_char = event_char;
	}

	void read(ByteBuffer buffer) {
		flushRetained();
		event_queue.copyEvents(buffer);
	}
}
