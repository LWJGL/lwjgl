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
 * A native implementation of a LWJGL compatible Keyboard event queue.
 * @author elias_naur
 * @author mojang
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.awt.Component;
import java.nio.ByteBuffer;

import org.lwjgl.input.Keyboard;

final class MacOSXNativeKeyboard extends EventQueue {
	private final byte[] key_states = new byte[Keyboard.KEYBOARD_SIZE];

	/** Event scratch array */
	private final ByteBuffer event = ByteBuffer.allocate(Keyboard.EVENT_SIZE);

    private ByteBuffer window_handle;

	private boolean has_deferred_event;
	private long deferred_nanos;
	private int deferred_key_code;
	private byte deferred_key_state;
	private int deferred_character;

    private HashMap<Short, Integer> nativeToLwjglMap;
    
	MacOSXNativeKeyboard(ByteBuffer window_handle) {
		super(Keyboard.EVENT_SIZE);
        nativeToLwjglMap = new HashMap<Short, Integer>();
        initKeyboardMappings();
        this.window_handle = window_handle;
	}

	private native void nRegisterKeyListener(ByteBuffer window_handle);

	private native void nUnregisterKeyListener(ByteBuffer window_handle);
    
	// These are from: <HIToolbox/Events.h>
    private void initKeyboardMappings() {
        nativeToLwjglMap.put((Short)(short)0x1D, Keyboard.KEY_0);
        nativeToLwjglMap.put((Short)(short)0x12, Keyboard.KEY_1);
        nativeToLwjglMap.put((Short)(short)0x13, Keyboard.KEY_2);
        nativeToLwjglMap.put((Short)(short)0x14, Keyboard.KEY_3);
        nativeToLwjglMap.put((Short)(short)0x15, Keyboard.KEY_4);
        nativeToLwjglMap.put((Short)(short)0x17, Keyboard.KEY_5);
        nativeToLwjglMap.put((Short)(short)0x16, Keyboard.KEY_6);
        nativeToLwjglMap.put((Short)(short)0x1A, Keyboard.KEY_7);
        nativeToLwjglMap.put((Short)(short)0x1C, Keyboard.KEY_8);
        nativeToLwjglMap.put((Short)(short)0x19, Keyboard.KEY_9);
        nativeToLwjglMap.put((Short)(short)0x00, Keyboard.KEY_A);
        nativeToLwjglMap.put((Short)(short)0x0B, Keyboard.KEY_B);
        nativeToLwjglMap.put((Short)(short)0x08, Keyboard.KEY_C);
        nativeToLwjglMap.put((Short)(short)0x02, Keyboard.KEY_D);
        nativeToLwjglMap.put((Short)(short)0x0E, Keyboard.KEY_E);
        nativeToLwjglMap.put((Short)(short)0x03, Keyboard.KEY_F);
        nativeToLwjglMap.put((Short)(short)0x05, Keyboard.KEY_G);
        nativeToLwjglMap.put((Short)(short)0x04, Keyboard.KEY_H);
        nativeToLwjglMap.put((Short)(short)0x22, Keyboard.KEY_I);
        nativeToLwjglMap.put((Short)(short)0x26, Keyboard.KEY_J);
        nativeToLwjglMap.put((Short)(short)0x28, Keyboard.KEY_K);
        nativeToLwjglMap.put((Short)(short)0x25, Keyboard.KEY_L);
        nativeToLwjglMap.put((Short)(short)0x2E, Keyboard.KEY_M);
        nativeToLwjglMap.put((Short)(short)0x2D, Keyboard.KEY_N);
        nativeToLwjglMap.put((Short)(short)0x1F, Keyboard.KEY_O);
        nativeToLwjglMap.put((Short)(short)0x23, Keyboard.KEY_P);
        nativeToLwjglMap.put((Short)(short)0x0C, Keyboard.KEY_Q);
        nativeToLwjglMap.put((Short)(short)0x0F, Keyboard.KEY_R);
        nativeToLwjglMap.put((Short)(short)0x01, Keyboard.KEY_S);
        nativeToLwjglMap.put((Short)(short)0x11, Keyboard.KEY_T);
        nativeToLwjglMap.put((Short)(short)0x20, Keyboard.KEY_U);
        nativeToLwjglMap.put((Short)(short)0x09, Keyboard.KEY_V);
        nativeToLwjglMap.put((Short)(short)0x0D, Keyboard.KEY_W);
        nativeToLwjglMap.put((Short)(short)0x07, Keyboard.KEY_X);
        nativeToLwjglMap.put((Short)(short)0x10, Keyboard.KEY_Y);
        nativeToLwjglMap.put((Short)(short)0x06, Keyboard.KEY_Z);

        nativeToLwjglMap.put((Short)(short)0x2A, Keyboard.KEY_BACKSLASH);
        nativeToLwjglMap.put((Short)(short)0x2B, Keyboard.KEY_COMMA);
        nativeToLwjglMap.put((Short)(short)0x18, Keyboard.KEY_EQUALS);
        nativeToLwjglMap.put((Short)(short)0x21, Keyboard.KEY_LBRACKET);
        nativeToLwjglMap.put((Short)(short)0x1B, Keyboard.KEY_MINUS);
        nativeToLwjglMap.put((Short)(short)0x27, Keyboard.KEY_APOSTROPHE);
        nativeToLwjglMap.put((Short)(short)0x1E, Keyboard.KEY_RBRACKET);
        nativeToLwjglMap.put((Short)(short)0x29, Keyboard.KEY_SEMICOLON);
        nativeToLwjglMap.put((Short)(short)0x2C, Keyboard.KEY_SLASH);
        nativeToLwjglMap.put((Short)(short)0x2F, Keyboard.KEY_PERIOD);
        nativeToLwjglMap.put((Short)(short)0x32, Keyboard.KEY_CIRCUMFLEX);

        nativeToLwjglMap.put((Short)(short)0x41, Keyboard.KEY_DECIMAL);
        nativeToLwjglMap.put((Short)(short)0x43, Keyboard.KEY_MULTIPLY);
        nativeToLwjglMap.put((Short)(short)0x45, Keyboard.KEY_ADD);
        nativeToLwjglMap.put((Short)(short)0x47, Keyboard.KEY_CLEAR);
        nativeToLwjglMap.put((Short)(short)0x4B, Keyboard.KEY_DIVIDE);
        nativeToLwjglMap.put((Short)(short)0x4C, Keyboard.KEY_NUMPADENTER);
        nativeToLwjglMap.put((Short)(short)0x4E, Keyboard.KEY_SUBTRACT);
        nativeToLwjglMap.put((Short)(short)0x51, Keyboard.KEY_NUMPADEQUALS);

        nativeToLwjglMap.put((Short)(short)0x52, Keyboard.KEY_NUMPAD0);
        nativeToLwjglMap.put((Short)(short)0x53, Keyboard.KEY_NUMPAD1);
        nativeToLwjglMap.put((Short)(short)0x54, Keyboard.KEY_NUMPAD2);
        nativeToLwjglMap.put((Short)(short)0x55, Keyboard.KEY_NUMPAD3);
        nativeToLwjglMap.put((Short)(short)0x56, Keyboard.KEY_NUMPAD4);
        nativeToLwjglMap.put((Short)(short)0x57, Keyboard.KEY_NUMPAD5);
        nativeToLwjglMap.put((Short)(short)0x58, Keyboard.KEY_NUMPAD6);
        nativeToLwjglMap.put((Short)(short)0x59, Keyboard.KEY_NUMPAD7);
        nativeToLwjglMap.put((Short)(short)0x5B, Keyboard.KEY_NUMPAD8);
        nativeToLwjglMap.put((Short)(short)0x5C, Keyboard.KEY_NUMPAD9);


        nativeToLwjglMap.put((Short)(short)0x24, Keyboard.KEY_RETURN);
        nativeToLwjglMap.put((Short)(short)0x30, Keyboard.KEY_TAB);
        nativeToLwjglMap.put((Short)(short)0x31, Keyboard.KEY_SPACE);
        nativeToLwjglMap.put((Short)(short)0x33, Keyboard.KEY_BACK);
        nativeToLwjglMap.put((Short)(short)0x35, Keyboard.KEY_ESCAPE);
        nativeToLwjglMap.put((Short)(short)0x36, Keyboard.KEY_RMETA); // not in Events.h - works on MBP
        nativeToLwjglMap.put((Short)(short)0x37, Keyboard.KEY_LMETA);
        nativeToLwjglMap.put((Short)(short)0x38, Keyboard.KEY_LSHIFT);
        nativeToLwjglMap.put((Short)(short)0x39, Keyboard.KEY_CAPITAL);
        nativeToLwjglMap.put((Short)(short)0x3A, Keyboard.KEY_LMENU);
        nativeToLwjglMap.put((Short)(short)0x3B, Keyboard.KEY_LCONTROL);
        nativeToLwjglMap.put((Short)(short)0x3C, Keyboard.KEY_RSHIFT);
        nativeToLwjglMap.put((Short)(short)0x3D, Keyboard.KEY_RMENU);
        nativeToLwjglMap.put((Short)(short)0x3E, Keyboard.KEY_RCONTROL);

        nativeToLwjglMap.put((Short)(short)0x3F, Keyboard.KEY_FUNCTION);
        nativeToLwjglMap.put((Short)(short)0x77, Keyboard.KEY_END);

        nativeToLwjglMap.put((Short)(short)0x7A, Keyboard.KEY_F1);
        nativeToLwjglMap.put((Short)(short)0x78, Keyboard.KEY_F2);
        nativeToLwjglMap.put((Short)(short)0x63, Keyboard.KEY_F3);
        nativeToLwjglMap.put((Short)(short)0x76, Keyboard.KEY_F4);
        nativeToLwjglMap.put((Short)(short)0x60, Keyboard.KEY_F5);
        nativeToLwjglMap.put((Short)(short)0x61, Keyboard.KEY_F6);
        nativeToLwjglMap.put((Short)(short)0x62, Keyboard.KEY_F7);
        nativeToLwjglMap.put((Short)(short)0x64, Keyboard.KEY_F8);
        nativeToLwjglMap.put((Short)(short)0x65, Keyboard.KEY_F9);
        nativeToLwjglMap.put((Short)(short)0x6D, Keyboard.KEY_F10);
        nativeToLwjglMap.put((Short)(short)0x67, Keyboard.KEY_F11);
        nativeToLwjglMap.put((Short)(short)0x6F, Keyboard.KEY_F12);
        nativeToLwjglMap.put((Short)(short)0x69, Keyboard.KEY_F13);
        nativeToLwjglMap.put((Short)(short)0x6B, Keyboard.KEY_F14);
        nativeToLwjglMap.put((Short)(short)0x71, Keyboard.KEY_F15);
        nativeToLwjglMap.put((Short)(short)0x6A, Keyboard.KEY_F16);
        nativeToLwjglMap.put((Short)(short)0x40, Keyboard.KEY_F17);
        nativeToLwjglMap.put((Short)(short)0x4F, Keyboard.KEY_F18);
        nativeToLwjglMap.put((Short)(short)0x50, Keyboard.KEY_F19);
     // nativeToLwjglMap.put((Short)(short)0x5A, Keyboard.KEY_F20);

        nativeToLwjglMap.put((Short)(short)0x75, Keyboard.KEY_DELETE);
        nativeToLwjglMap.put((Short)(short)0x72, Keyboard.KEY_INSERT); // 'Help' in Events.h
        nativeToLwjglMap.put((Short)(short)0x73, Keyboard.KEY_HOME);
     // nativeToLwjglMap.put((Short)(short)0xA4, Keyboard.KEY_MUTE);
        nativeToLwjglMap.put((Short)(short)0x79, Keyboard.KEY_NEXT);
        nativeToLwjglMap.put((Short)(short)0x74, Keyboard.KEY_PRIOR);
     // nativeToLwjglMap.put((Short)(short)0x49, Keyboard.KEY_VOLUMEDOWN);
     // nativeToLwjglMap.put((Short)(short)0x48, Keyboard.KEY_VOLUMEUP);
        nativeToLwjglMap.put((Short)(short)0x7B, Keyboard.KEY_LEFT);
        nativeToLwjglMap.put((Short)(short)0x7C, Keyboard.KEY_RIGHT);
        nativeToLwjglMap.put((Short)(short)0x7D, Keyboard.KEY_DOWN);
        nativeToLwjglMap.put((Short)(short)0x7E, Keyboard.KEY_UP);

        nativeToLwjglMap.put((Short)(short)0x0A, Keyboard.KEY_SECTION);

        nativeToLwjglMap.put((Short)(short)0x6E, Keyboard.KEY_APPS); // not in Events.h
        nativeToLwjglMap.put((Short)(short)0x129, Keyboard.KEY_COLON); // not in Events.h -- do we need it?
    }

	public void register() {
        nRegisterKeyListener(window_handle);
	}

	public void unregister() {
        nUnregisterKeyListener(window_handle);
	}

	public void putKeyboardEvent(int key_code, byte state, int character, long nanos, boolean repeat) {
		event.clear();
        event.putInt(key_code).put(state).putInt(character).putLong(nanos).put(repeat ? (byte)1 : (byte)0);
		event.flip();
		putEvent(event);
	}

	public synchronized void poll(ByteBuffer key_down_buffer) {
		flushDeferredEvent();
		int old_position = key_down_buffer.position();
		key_down_buffer.put(key_states);
		key_down_buffer.position(old_position);
	}

	public synchronized void copyEvents(ByteBuffer dest) {
		flushDeferredEvent();
		super.copyEvents(dest);
	}

	private synchronized void handleKey(int key_code, byte state, int character, long nanos) {
		if (character == KeyEvent.CHAR_UNDEFINED)
			character = Keyboard.CHAR_NONE;
		if (state == 1) {
			boolean repeat = false;
			if (has_deferred_event) {
				if ((nanos == deferred_nanos && deferred_key_code == key_code)) {
					has_deferred_event = false;
					repeat = true; // Repeat event
				} else
					flushDeferredEvent();
			}
			putKeyEvent(key_code, state, character, nanos, repeat);
		} else {
			flushDeferredEvent();
			has_deferred_event = true;
			deferred_nanos = nanos;
			deferred_key_code = key_code;
			deferred_key_state = state;
			deferred_character = character;
		}
	}

	private void flushDeferredEvent() {
		if (has_deferred_event) {
			putKeyEvent(deferred_key_code, deferred_key_state, deferred_character, deferred_nanos, false);
			has_deferred_event = false;
		}
	}

	public void putKeyEvent(int key_code, byte state, int character, long nanos, boolean repeat) {
		/* Ignore repeating presses */
        int mapped_code = getMappedKeyCode((short)key_code);
        if (mapped_code < 0) {
            System.out.println("Unrecognized keycode: " + key_code);
            /* Unrecognized / unmapped code, do nothing */
            return;
        }
		if ( key_states[mapped_code] == state )
			repeat = true;
		key_states[mapped_code] = state;
		int key_int_char = character & 0xffff;
		putKeyboardEvent(mapped_code, state, key_int_char, nanos, repeat);
	}

	private int getMappedKeyCode(short key_code) {
        if (nativeToLwjglMap.containsKey(key_code)) {
            return nativeToLwjglMap.get(key_code);
        }
        return -1;
	}

	public void keyPressed(int key_code, String chars, long nanos) {
		// use only first character of chars returned for key press
		int character = (chars == null || chars.length() == 0) ? 0 : (int)chars.charAt(0);
		handleKey(key_code, (byte)1, character, nanos);
	}

	public void keyReleased(int key_code, String chars, long nanos) {
		// use only first character of chars returned for key release
		int character = (chars == null || chars.length() == 0) ? 0 : (int)chars.charAt(0);
		handleKey(key_code, (byte)0, character, nanos);
	}

	public void keyTyped(KeyEvent e) {
	}
}
