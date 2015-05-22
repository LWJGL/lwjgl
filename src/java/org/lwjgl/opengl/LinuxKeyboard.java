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
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Keyboard;

final class LinuxKeyboard {
	private static final int LockMapIndex                      = 1;
	private static final long NoSymbol = 0;
	private static final long ShiftMask = 1 << 0;
	private static final long LockMask = 1 << 1;
	private static final int XLookupChars            = 2;
	private static final int XLookupBoth             = 4;

	private static final int KEYBOARD_BUFFER_SIZE = 50;

	private final long xim;
	private final long xic;

	private final int numlock_mask;
	private final int modeswitch_mask;
	private final int caps_lock_mask;
	private final int shift_lock_mask;

	private final ByteBuffer compose_status;

	private final byte[] key_down_buffer = new byte[Keyboard.KEYBOARD_SIZE];
	private final EventQueue event_queue = new EventQueue(Keyboard.EVENT_SIZE);

	private final ByteBuffer tmp_event = ByteBuffer.allocate(Keyboard.EVENT_SIZE);
	private final int[] temp_translation_buffer = new int[KEYBOARD_BUFFER_SIZE];
	private final ByteBuffer native_translation_buffer = BufferUtils.createByteBuffer(KEYBOARD_BUFFER_SIZE);
	private final CharsetDecoder utf8_decoder = Charset.forName("UTF-8").newDecoder();
	private final CharBuffer char_buffer = CharBuffer.allocate(KEYBOARD_BUFFER_SIZE);

	// Deferred key released event, to detect key repeat
	private boolean has_deferred_event;
	private int deferred_keycode;
	private int deferred_event_keycode;
	private long deferred_nanos;
	private byte deferred_key_state;

	LinuxKeyboard(long display, long window) {
		long modifier_map = getModifierMapping(display);
		int tmp_numlock_mask = 0;
		int tmp_modeswitch_mask = 0;
		int tmp_caps_lock_mask = 0;
		int tmp_shift_lock_mask = 0;
		if (modifier_map != 0) {
			int max_keypermod = getMaxKeyPerMod(modifier_map);
			// Find modifier masks
			int i, j;
			for (i = 0; i < 8; i++) {
				for (j = 0; j < max_keypermod; j++) {
					int key_code = lookupModifierMap(modifier_map, i*max_keypermod + j);
					int key_sym = (int)keycodeToKeySym(display, key_code);
					int mask = 1 << i;
					switch (key_sym) {
						case LinuxKeycodes.XK_Num_Lock:
							tmp_numlock_mask |= mask;
							break;
						case LinuxKeycodes.XK_Mode_switch:
							tmp_modeswitch_mask |= mask;
							break;
						case LinuxKeycodes.XK_Caps_Lock:
							if (i == LockMapIndex) {
								tmp_caps_lock_mask = mask;
								tmp_shift_lock_mask = 0;
							}
							break;
						case LinuxKeycodes.XK_Shift_Lock:
							if (i == LockMapIndex && tmp_caps_lock_mask == 0)
								tmp_shift_lock_mask = mask;
							break;
						default:
							break;
					}
				}
			}
			freeModifierMapping(modifier_map);
		}
		numlock_mask = tmp_numlock_mask;
		modeswitch_mask = tmp_modeswitch_mask;
		caps_lock_mask = tmp_caps_lock_mask;
		shift_lock_mask = tmp_shift_lock_mask;
		setDetectableKeyRepeat(display, true);
		xim = openIM(display);
		if (xim != 0) {
			xic = createIC(xim, window);
			if (xic != 0) {
				setupIMEventMask(display, window, xic);
			} else {
				destroy(display);
			}
		} else {
			xic = 0;
		}
		compose_status = allocateComposeStatus();
	}
	private static native long getModifierMapping(long display);
	private static native void freeModifierMapping(long modifier_map);
	private static native int getMaxKeyPerMod(long modifier_map);
	private static native int lookupModifierMap(long modifier_map, int index);
	private static native long keycodeToKeySym(long display, int key_code);

	private static native long openIM(long display);
	private static native long createIC(long xim, long window);
	private static native void setupIMEventMask(long display, long window, long xic);
	private static native ByteBuffer allocateComposeStatus();

	private static void setDetectableKeyRepeat(long display, boolean enabled) {
		boolean success = nSetDetectableKeyRepeat(display, enabled);
		if (!success)
			LWJGLUtil.log("Failed to set detectable key repeat to " + enabled);
	}
	private static native boolean nSetDetectableKeyRepeat(long display, boolean enabled);

	public void destroy(long display) {
		if (xic != 0)
			destroyIC(xic);
		if (xim != 0)
			closeIM(xim);
		setDetectableKeyRepeat(display, false);
	}
	private static native void destroyIC(long xic);
	private static native void closeIM(long xim);

	public void read(ByteBuffer buffer) {
		flushDeferredEvent();
		event_queue.copyEvents(buffer);
	}

	public void poll(ByteBuffer keyDownBuffer) {
		flushDeferredEvent();
		int old_position = keyDownBuffer.position();
		keyDownBuffer.put(key_down_buffer);
		keyDownBuffer.position(old_position);
	}

	private void putKeyboardEvent(int keycode, byte state, int ch, long nanos, boolean repeat) {
		tmp_event.clear();
		tmp_event.putInt(keycode).put(state).putInt(ch).putLong(nanos).put(repeat ? (byte)1 : (byte)0);
		tmp_event.flip();
		event_queue.putEvent(tmp_event);
	}

	private int lookupStringISO88591(long event_ptr, int[] translation_buffer) {
		int i;

		int num_chars = lookupString(event_ptr, native_translation_buffer, compose_status);
		for (i = 0; i < num_chars; i++) {
			translation_buffer[i] = ((int)native_translation_buffer.get(i)) & 0xff;
		}
		return num_chars;
	}
	private static native int lookupString(long event_ptr, ByteBuffer buffer, ByteBuffer compose_status);

	private int lookupStringUnicode(long event_ptr, int[] translation_buffer) {
		int status = utf8LookupString(xic, event_ptr, native_translation_buffer, native_translation_buffer.position(), native_translation_buffer.remaining());
		if (status != XLookupChars && status != XLookupBoth)
			return 0;
		native_translation_buffer.flip();
		utf8_decoder.decode(native_translation_buffer, char_buffer, true);
		native_translation_buffer.compact();
		char_buffer.flip();
		int i = 0;
		while (char_buffer.hasRemaining() && i < translation_buffer.length) {
			translation_buffer[i++] = char_buffer.get();
		}
		char_buffer.compact();
		return i;
	}
	private static native int utf8LookupString(long xic, long event_ptr, ByteBuffer buffer, int pos, int size);

	private int lookupString(long event_ptr, int[] translation_buffer) {
		if (xic != 0) {
			return lookupStringUnicode(event_ptr, translation_buffer);
		} else
			return lookupStringISO88591(event_ptr, translation_buffer);
	}

	private void translateEvent(long event_ptr, int keycode, byte key_state, long nanos, boolean repeat) {
		int num_chars, i;
		int ch;

		num_chars = lookupString(event_ptr, temp_translation_buffer);
		if (num_chars > 0) {
			ch = temp_translation_buffer[0];
			putKeyboardEvent(keycode, key_state, ch, nanos, repeat);
			for (i = 1; i < num_chars; i++) {
				ch = temp_translation_buffer[i];
				putKeyboardEvent(0, (byte)0, ch, nanos, repeat);
			}
		} else {
			putKeyboardEvent(keycode, key_state, 0, nanos, repeat);
		}
	}

	private static boolean isKeypadKeysym(long keysym) {
		return (0xFF80 <= keysym && keysym <= 0xFFBD) ||
			(0x11000000 <= keysym && keysym <= 0x1100FFFF);
	}

	private static boolean isNoSymbolOrVendorSpecific(long keysym) {
		return keysym == NoSymbol || (keysym & (1 << 28)) != 0;
	}

	private static long getKeySym(long event_ptr, int group, int index) {
		long keysym = lookupKeysym(event_ptr, group*2 + index);
		if (isNoSymbolOrVendorSpecific(keysym) && index == 1) {
			keysym = lookupKeysym(event_ptr, group*2 + 0);
		}
		if (isNoSymbolOrVendorSpecific(keysym) && group == 1)
			keysym = getKeySym(event_ptr, 0, index);
		return keysym;
	}
	private static native long lookupKeysym(long event_ptr, int index);
	private static native long toUpper(long keysym);

	private int getKeycode(long event_ptr, int event_state) {
		boolean shift = (event_state & (ShiftMask | shift_lock_mask)) != 0;
		int group = (event_state & modeswitch_mask) != 0 ? 1 : 0;
		long keysym;
		if ((event_state & numlock_mask) != 0 && isKeypadKeysym(keysym = getKeySym(event_ptr, group, 1))) {
			if ( shift )
				keysym = getKeySym(event_ptr, group, 0);
		} else {
			keysym = getKeySym(event_ptr, group, 0);
			if ( shift ^ ((event_state & caps_lock_mask) != 0) )
				keysym = toUpper(keysym);
		}
		return LinuxKeycodes.mapKeySymToLWJGLKeyCode(keysym);
	}

	private static byte getKeyState(int event_type) {
		switch (event_type) {
			case LinuxEvent.KeyPress:
				return 1;
			case LinuxEvent.KeyRelease:
				return 0;
			default:
				throw new IllegalArgumentException("Unknown event_type: " + event_type);
		}
	}

	/** This is called when the window loses focus: we release all currently pressed keys. */
	void releaseAll() {
		for ( int i = 0; i < key_down_buffer.length; i++ ) {
			if ( key_down_buffer[i] != 0 ) {
				key_down_buffer[i] = 0;
				putKeyboardEvent(i, (byte)0, 0, 0L, false);
			}
		}
	}

	private void handleKeyEvent(long event_ptr, long millis, int event_type, int event_keycode, int event_state) {
		int keycode = getKeycode(event_ptr, event_state);
		byte key_state = getKeyState(event_type);
		boolean repeat = key_state == key_down_buffer[keycode];
		if ( repeat && event_type == LinuxEvent.KeyRelease ) // This can happen for modifier keys after losing and regaining focus.
			return;
		key_down_buffer[keycode] = key_state;
		long nanos = millis*1000000;
		if (event_type == LinuxEvent.KeyPress) {
			if (has_deferred_event) {
				if (nanos == deferred_nanos && event_keycode == deferred_event_keycode) {
					has_deferred_event = false;
					repeat = true; // Repeated event
				} else
					flushDeferredEvent();
			}
			translateEvent(event_ptr, keycode, key_state, nanos, repeat);
		} else {
			flushDeferredEvent();
			has_deferred_event = true;
			deferred_keycode = keycode;
			deferred_event_keycode = event_keycode;
			deferred_nanos = nanos;
			deferred_key_state = key_state;
		}
	}

	private void flushDeferredEvent() {
		if (has_deferred_event) {
			putKeyboardEvent(deferred_keycode, deferred_key_state, 0, deferred_nanos, false);
			has_deferred_event = false;
		}
	}

	public boolean filterEvent(LinuxEvent event) {
		switch (event.getType()) {
			case LinuxEvent.KeyPress: /* Fall through */
			case LinuxEvent.KeyRelease:
				handleKeyEvent(event.getKeyAddress(), event.getKeyTime(), event.getKeyType(), event.getKeyKeyCode(), event.getKeyState());
				return true;
			default:
				break;
		}
		return false;
	}
}
