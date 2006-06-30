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
 
/**
 * $Id$
 *
 * Linux keyboard handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/keysym.h>
#include <string.h>
#include <assert.h>
#include <iconv.h>
#include <errno.h>
#include "Window.h"
#include "common_tools.h"
#include "org_lwjgl_input_Keyboard.h"
#include "org_lwjgl_opengl_LinuxDisplay.h"

#define KEYBOARD_BUFFER_SIZE 50

static jbyte key_buf[org_lwjgl_input_Keyboard_KEYBOARD_SIZE];
static int numlock_mask;
static int modeswitch_mask;
static int caps_lock_mask;
static int shift_lock_mask;

static event_queue_t event_queue;

static bool keyboard_grabbed;
static bool created = false;

// X input manager values
static iconv_t iconv_descriptor = (iconv_t)-1;
static XIM xim = NULL;
static XIC xic = NULL;

static void grabKeyboard(void) {
	if (!keyboard_grabbed) {
		int result = XGrabKeyboard(getDisplay(), getCurrentWindow(), False, GrabModeAsync, GrabModeAsync, CurrentTime);
		if (result == GrabSuccess)
			keyboard_grabbed = true;
	}
}

static void ungrabKeyboard(void) {
	if (keyboard_grabbed) {
		keyboard_grabbed = false;
		XUngrabKeyboard(getDisplay(), CurrentTime);
	}
}

void updateKeyboardGrab(jint window_mode) {
	if (!created)
		return;
	if (isLegacyFullscreen(window_mode)) {
		grabKeyboard();
	} else {
		ungrabKeyboard();
	}
}

static void cleanup() {
	if (iconv_descriptor != (iconv_t)-1) {
		iconv_close(iconv_descriptor);
		iconv_descriptor = (iconv_t)-1;
	}
	if (xic != NULL) {
		XDestroyIC(xic);
		xic = NULL;
	}
	if (xim != NULL) {
		XCloseIM(xim);
		xim = NULL;
	}
}

static void setupIMEventMask() {
	long im_event_mask;
	XWindowAttributes win_attributes;
	
	XGetWindowAttributes(getDisplay(), getCurrentWindow(), &win_attributes);
	XGetICValues(xic, XNFilterEvents, &im_event_mask, NULL);
	XSelectInput(getDisplay(), getCurrentWindow(), win_attributes.your_event_mask | im_event_mask);
	XSetICFocus(xic);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateKeyboard
  (JNIEnv * env, jclass clazz, jint window_mode)
{
	memset(key_buf, 0, org_lwjgl_input_Keyboard_KEYBOARD_SIZE*sizeof(jbyte));
	created = true;
	keyboard_grabbed = false;
	initEventQueue(&event_queue, 3);
	updateKeyboardGrab(window_mode);
	XModifierKeymap *modifier_map = XGetModifierMapping(getDisplay());
	numlock_mask = 0;
	modeswitch_mask = 0;
	caps_lock_mask = 0;
	shift_lock_mask = 0;
	if (modifier_map != NULL) {
		// Find modifier masks
		int i, j;
		for (i = 0; i < 8; i++) {
			for (j = 0; j < modifier_map->max_keypermod; j++) {
				KeyCode key_code = modifier_map->modifiermap[i*modifier_map->max_keypermod + j];
				KeySym key_sym = XKeycodeToKeysym(getDisplay(), key_code, 0);
				int mask = 1 << i;
				switch (key_sym) {
					case XK_Num_Lock:
						numlock_mask |= mask;
						break;
					case XK_Mode_switch:
						modeswitch_mask |= mask;
						break;
					case XK_Caps_Lock:
						if (i == LockMapIndex) {
							caps_lock_mask = mask;
							shift_lock_mask = 0;
						}
						break;
					case XK_Shift_Lock:
						if (i == LockMapIndex && caps_lock_mask == 0)
							shift_lock_mask = mask;
						break;
					default:
						break;
				}
			}
		}
		XFreeModifiermap(modifier_map);
	}

	// Allocate unicode structures
	iconv_descriptor = iconv_open("UCS-2", "UTF-8");
	if (iconv_descriptor != (iconv_t)-1) {
		xim = XOpenIM(getDisplay(), NULL, NULL, NULL);
		if (xim != NULL) {
			xic = XCreateIC(xim,  XNClientWindow, getCurrentWindow(), XNFocusWindow, getCurrentWindow(), XNInputStyle, XIMPreeditNothing | XIMStatusNothing, NULL);
			if (xic != NULL) {
				setupIMEventMask();
			} else {
				cleanup();
			}
		} else
			cleanup();
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nDestroyKeyboard
  (JNIEnv * env, jclass clazz)
{
	cleanup();
	ungrabKeyboard();
	created = false;
}

static unsigned char mapKeySymToLWJGLKeyCode(KeySym keysym) {
	switch (keysym) {
		case XK_BackSpace:
			return org_lwjgl_input_Keyboard_KEY_BACK;
		case XK_ISO_Left_Tab:
		case XK_Tab:
			return org_lwjgl_input_Keyboard_KEY_TAB;
		case XK_Return:
			return org_lwjgl_input_Keyboard_KEY_RETURN;
		case XK_Pause:
			return org_lwjgl_input_Keyboard_KEY_PAUSE;
		case XK_Scroll_Lock:
			return org_lwjgl_input_Keyboard_KEY_SCROLL;
		case XK_Sys_Req:
			return org_lwjgl_input_Keyboard_KEY_SYSRQ;
		case XK_Escape:
			return org_lwjgl_input_Keyboard_KEY_ESCAPE;
		case XK_Delete:
			return org_lwjgl_input_Keyboard_KEY_DELETE;

/* Japanese keyboard support */

		case XK_Kanji:
			return org_lwjgl_input_Keyboard_KEY_KANJI;

/* Cursor control & motion */

		case XK_Home:
			return org_lwjgl_input_Keyboard_KEY_HOME;
		case XK_Left:
			return org_lwjgl_input_Keyboard_KEY_LEFT;
		case XK_Up:
			return org_lwjgl_input_Keyboard_KEY_UP;
		case XK_Right:
			return org_lwjgl_input_Keyboard_KEY_RIGHT;
		case XK_Down:
			return org_lwjgl_input_Keyboard_KEY_DOWN;
		case XK_Page_Up:
			return org_lwjgl_input_Keyboard_KEY_PRIOR;
		case XK_Page_Down:
			return org_lwjgl_input_Keyboard_KEY_NEXT;
		case XK_End:
			return org_lwjgl_input_Keyboard_KEY_END;


/* Misc Functions */

		case XK_Break:
			return org_lwjgl_input_Keyboard_KEY_PAUSE;
		case XK_Insert:
			return org_lwjgl_input_Keyboard_KEY_INSERT;
		case XK_Num_Lock:
			return org_lwjgl_input_Keyboard_KEY_NUMLOCK;

/* Keypad Functions, keypad numbers cleverly chosen to map to ascii */

		case XK_KP_Space:
			return org_lwjgl_input_Keyboard_KEY_SPACE;
		case XK_KP_Tab:
			return org_lwjgl_input_Keyboard_KEY_TAB;
		case XK_KP_Enter:
			return org_lwjgl_input_Keyboard_KEY_NUMPADENTER;
		case XK_KP_F1:
			return org_lwjgl_input_Keyboard_KEY_F1;
		case XK_KP_F2:
			return org_lwjgl_input_Keyboard_KEY_F2;
		case XK_KP_F3:
			return org_lwjgl_input_Keyboard_KEY_F3;
		case XK_KP_F4:
			return org_lwjgl_input_Keyboard_KEY_F4;
		case XK_KP_Home:
			return org_lwjgl_input_Keyboard_KEY_HOME;
		case XK_KP_Left:
			return org_lwjgl_input_Keyboard_KEY_LEFT;
		case XK_KP_Up:
			return org_lwjgl_input_Keyboard_KEY_UP;
		case XK_KP_Right:
			return org_lwjgl_input_Keyboard_KEY_RIGHT;
		case XK_KP_Down:
			return org_lwjgl_input_Keyboard_KEY_DOWN;
		case XK_KP_Page_Up:
			return org_lwjgl_input_Keyboard_KEY_PRIOR;
		case XK_KP_Page_Down:
			return org_lwjgl_input_Keyboard_KEY_NEXT;
		case XK_KP_End:
			return org_lwjgl_input_Keyboard_KEY_END;
		case XK_KP_Insert:
			return org_lwjgl_input_Keyboard_KEY_INSERT;
		case XK_KP_Delete:
			return org_lwjgl_input_Keyboard_KEY_DELETE;
		case XK_KP_Equal:
			return org_lwjgl_input_Keyboard_KEY_NUMPADEQUALS;
		case XK_KP_Multiply:
			return org_lwjgl_input_Keyboard_KEY_MULTIPLY;
		case XK_KP_Add:
			return org_lwjgl_input_Keyboard_KEY_ADD;
		case XK_KP_Subtract:
			return org_lwjgl_input_Keyboard_KEY_SUBTRACT;
		case XK_KP_Decimal:
			return org_lwjgl_input_Keyboard_KEY_DECIMAL;
		case XK_KP_Divide:
			return org_lwjgl_input_Keyboard_KEY_DIVIDE;

		case XK_KP_0:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD0;
		case XK_KP_1:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD1;
		case XK_KP_2:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD2;
		case XK_KP_3:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD3;
		case XK_KP_4:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD4;
		case XK_KP_5:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD5;
		case XK_KP_6:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD6;
		case XK_KP_7:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD7;
		case XK_KP_8:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD8;
		case XK_KP_9:
			return org_lwjgl_input_Keyboard_KEY_NUMPAD9;

/*
 * Auxilliary Functions; note the duplicate definitions for left and right
 * function keys;  Sun keyboards and a few other manufactures have such
 * function key groups on the left and/or right sides of the keyboard.
 * We've not found a keyboard with more than 35 function keys total.
 */

		case XK_F1:
			return org_lwjgl_input_Keyboard_KEY_F1;
		case XK_F2:
			return org_lwjgl_input_Keyboard_KEY_F2;
		case XK_F3:
			return org_lwjgl_input_Keyboard_KEY_F3;
		case XK_F4:
			return org_lwjgl_input_Keyboard_KEY_F4;
		case XK_F5:
			return org_lwjgl_input_Keyboard_KEY_F5;
		case XK_F6:
			return org_lwjgl_input_Keyboard_KEY_F6;
		case XK_F7:
			return org_lwjgl_input_Keyboard_KEY_F7;
		case XK_F8:
			return org_lwjgl_input_Keyboard_KEY_F8;
		case XK_F9:
			return org_lwjgl_input_Keyboard_KEY_F9;
		case XK_F10:
			return org_lwjgl_input_Keyboard_KEY_F10;
		case XK_F11:
			return org_lwjgl_input_Keyboard_KEY_F11;
		case XK_F12:
			return org_lwjgl_input_Keyboard_KEY_F12;
		case XK_F13:
			return org_lwjgl_input_Keyboard_KEY_F13;
		case XK_F14:
			return org_lwjgl_input_Keyboard_KEY_F14;
		case XK_F15:
			return org_lwjgl_input_Keyboard_KEY_F15;

/* Modifiers */

		case XK_Shift_L:
			return org_lwjgl_input_Keyboard_KEY_LSHIFT;
		case XK_Shift_R:
			return org_lwjgl_input_Keyboard_KEY_RSHIFT;
		case XK_Control_L:
			return org_lwjgl_input_Keyboard_KEY_LCONTROL;
		case XK_Control_R:
			return org_lwjgl_input_Keyboard_KEY_RCONTROL;
		case XK_Caps_Lock:
			return org_lwjgl_input_Keyboard_KEY_CAPITAL;

		case XK_Meta_L:
			return org_lwjgl_input_Keyboard_KEY_LMENU;
		case XK_Meta_R:
			return org_lwjgl_input_Keyboard_KEY_RMENU;
		case XK_Alt_L:
			return org_lwjgl_input_Keyboard_KEY_LMENU;
		case XK_Alt_R:
			return org_lwjgl_input_Keyboard_KEY_RMENU;

		case XK_dead_grave:
			return org_lwjgl_input_Keyboard_KEY_GRAVE;
		case XK_dead_circumflex:
			return org_lwjgl_input_Keyboard_KEY_CIRCUMFLEX;

		/*
		 *  Latin 1
		 *  Byte 3 = 0
		 */
		case XK_space:
			return org_lwjgl_input_Keyboard_KEY_SPACE;
		case XK_apostrophe:
			return org_lwjgl_input_Keyboard_KEY_APOSTROPHE;
		case XK_comma:
			return org_lwjgl_input_Keyboard_KEY_COMMA;
		case XK_minus:
			return org_lwjgl_input_Keyboard_KEY_MINUS;
		case XK_period:
			return org_lwjgl_input_Keyboard_KEY_PERIOD;
		case XK_slash:
			return org_lwjgl_input_Keyboard_KEY_SLASH;
		case XK_0:
			return org_lwjgl_input_Keyboard_KEY_0;
		case XK_1:
			return org_lwjgl_input_Keyboard_KEY_1;
		case XK_2:
			return org_lwjgl_input_Keyboard_KEY_2;
		case XK_3:
			return org_lwjgl_input_Keyboard_KEY_3;
		case XK_4:
			return org_lwjgl_input_Keyboard_KEY_4;
		case XK_5:
			return org_lwjgl_input_Keyboard_KEY_5;
		case XK_6:
			return org_lwjgl_input_Keyboard_KEY_6;
		case XK_7:
			return org_lwjgl_input_Keyboard_KEY_7;
		case XK_8:
			return org_lwjgl_input_Keyboard_KEY_8;
		case XK_9:
			return org_lwjgl_input_Keyboard_KEY_9;
		case XK_colon:
			return org_lwjgl_input_Keyboard_KEY_COLON;
		case XK_semicolon:
			return org_lwjgl_input_Keyboard_KEY_SEMICOLON;
		case XK_equal:
			return org_lwjgl_input_Keyboard_KEY_EQUALS;
		case XK_at:
			return org_lwjgl_input_Keyboard_KEY_AT;
		case XK_bracketleft:
			return org_lwjgl_input_Keyboard_KEY_LBRACKET;
		case XK_bracketright:
			return org_lwjgl_input_Keyboard_KEY_RBRACKET;
		case XK_asciicircum:
			return org_lwjgl_input_Keyboard_KEY_CIRCUMFLEX;
		case XK_underscore:
			return org_lwjgl_input_Keyboard_KEY_UNDERLINE;
		case XK_grave:
			return org_lwjgl_input_Keyboard_KEY_GRAVE;
		case XK_a:
		case XK_A:
			return org_lwjgl_input_Keyboard_KEY_A;
		case XK_b:
		case XK_B:
			return org_lwjgl_input_Keyboard_KEY_B;
		case XK_c:
		case XK_C:
			return org_lwjgl_input_Keyboard_KEY_C;
		case XK_d:
		case XK_D:
			return org_lwjgl_input_Keyboard_KEY_D;
		case XK_e:
		case XK_E:
			return org_lwjgl_input_Keyboard_KEY_E;
		case XK_f:
		case XK_F:
			return org_lwjgl_input_Keyboard_KEY_F;
		case XK_g:
		case XK_G:
			return org_lwjgl_input_Keyboard_KEY_G;
		case XK_h:
		case XK_H:
			return org_lwjgl_input_Keyboard_KEY_H;
		case XK_i:
		case XK_I:
			return org_lwjgl_input_Keyboard_KEY_I;
		case XK_j:
		case XK_J:
			return org_lwjgl_input_Keyboard_KEY_J;
		case XK_k:
		case XK_K:
			return org_lwjgl_input_Keyboard_KEY_K;
		case XK_l:
		case XK_L:
			return org_lwjgl_input_Keyboard_KEY_L;
		case XK_m:
		case XK_M:
			return org_lwjgl_input_Keyboard_KEY_M;
		case XK_n:
		case XK_N:
			return org_lwjgl_input_Keyboard_KEY_N;
		case XK_o:
		case XK_O:
			return org_lwjgl_input_Keyboard_KEY_O;
		case XK_p:
		case XK_P:
			return org_lwjgl_input_Keyboard_KEY_P;
		case XK_q:
		case XK_Q:
			return org_lwjgl_input_Keyboard_KEY_Q;
		case XK_r:
		case XK_R:
			return org_lwjgl_input_Keyboard_KEY_R;
		case XK_s:
		case XK_S:
			return org_lwjgl_input_Keyboard_KEY_S;
		case XK_t:
		case XK_T:
			return org_lwjgl_input_Keyboard_KEY_T;
		case XK_u:
		case XK_U:
			return org_lwjgl_input_Keyboard_KEY_U;
		case XK_v:
		case XK_V:
			return org_lwjgl_input_Keyboard_KEY_V;
		case XK_w:
		case XK_W:
			return org_lwjgl_input_Keyboard_KEY_W;
		case XK_x:
		case XK_X:
			return org_lwjgl_input_Keyboard_KEY_X;
		case XK_y:
		case XK_Y:
			return org_lwjgl_input_Keyboard_KEY_Y;
		case XK_z:
		case XK_Z:
			return org_lwjgl_input_Keyboard_KEY_Z;
		default:
			return org_lwjgl_input_Keyboard_KEY_NONE;
	}
}

static bool isKeypadKeysym(KeySym keysym) {
	return (0xFF80 <= keysym && keysym <= 0xFFBD) ||
		(0x11000000 <= keysym && keysym <= 0x1100FFFF);
}

static bool isNoSymbolOrVendorSpecific(KeySym keysym) {
	return keysym == NoSymbol || (keysym & (1 << 28)) != 0;
}

static KeySym getKeySym(XKeyEvent *event, int group, int index) {
	KeySym keysym = XLookupKeysym(event, group*2 + index);
	if (isNoSymbolOrVendorSpecific(keysym) && index == 1) {
		keysym = XLookupKeysym(event, group*2 + 0);
	}
	if (isNoSymbolOrVendorSpecific(keysym) && group == 1)
		keysym = getKeySym(event, 0, index);
	return keysym;
}

static KeySym toUpper(KeySym keysym) {
	KeySym lower_case, upper_case;
	XConvertCase(keysym, &lower_case, &upper_case);
	return upper_case;
}

/* Map an event to a KeySym. Use the rules described in
 * http://tronche.com/gui/x/xlib/input/keyboard-encoding.html
 */
static KeySym mapEventToKeySym(XKeyEvent *event) {
	int group;
	KeySym keysym;
	if ((event->state & modeswitch_mask) != 0)
		group = 1;
	else
		group = 0;
	if ((event->state & numlock_mask) != 0 && isKeypadKeysym(keysym = getKeySym(event, group, 1))) {
		if ((event->state & (ShiftMask | shift_lock_mask)) != 0) {
			return getKeySym(event, group, 0);
		} else {
			return keysym;
		}
	} else if ((event->state & (ShiftMask | LockMask)) == 0) {
		return getKeySym(event, group, 0);
	} else if ((event->state & ShiftMask) == 0) {
		KeySym keysym = getKeySym(event, group, 0);
		if ((event->state & caps_lock_mask) != 0)
			keysym = toUpper(keysym);
		return keysym;
	} else {
		KeySym keysym = getKeySym(event, group, 1);
		if ((event->state & caps_lock_mask) != 0)
			keysym = toUpper(keysym);
		return keysym;
	}
}

static unsigned char getKeycode(XKeyEvent *event) {
	unsigned char keycode;
	KeySym keysym = mapEventToKeySym(event);
	keycode = mapKeySymToLWJGLKeyCode(keysym);
	if (keycode == org_lwjgl_input_Keyboard_KEY_NONE) {
		// Try unshifted keysym mapping
		keysym = XLookupKeysym(event, 0);
		keycode = mapKeySymToLWJGLKeyCode(keysym);
	}
	return keycode;
}

static void putKeyboardEvent(jint keycode, jint state, jint ch) {
	int event_list[] = {keycode, state, ch};
	putEvent(&event_queue, event_list);
}

static int lookupStringISO88591(XKeyEvent *event, jint *translation_buffer) {
	static XComposeStatus status;
	char char_translation_buffer[KEYBOARD_BUFFER_SIZE];
	int i;
	
	int num_chars = XLookupString(event, char_translation_buffer, KEYBOARD_BUFFER_SIZE*sizeof(char), NULL, &status);
	for (i = 0; i < num_chars; i++)
		translation_buffer[i] = ((jint)char_translation_buffer[i]) & 0xff;
	return num_chars;
}

static int lookupStringUnicode(XKeyEvent *event, jint *translation_buffer) {
	char utf8_translation_buffer[KEYBOARD_BUFFER_SIZE];
	
	jchar ucs2_translation_buffer[KEYBOARD_BUFFER_SIZE];
	char *utf8_buf = utf8_translation_buffer;
	jchar *ucs2_buf = ucs2_translation_buffer;
	size_t ucs2_bytes = KEYBOARD_BUFFER_SIZE*sizeof(jchar); 
	Status status;
	int i;
	
	size_t utf8_bytes = Xutf8LookupString(xic, event, utf8_translation_buffer, KEYBOARD_BUFFER_SIZE*sizeof(char), NULL, &status);
	if (status != XLookupChars && status != XLookupBoth)
		return 0;
	// reset converter
	iconv(iconv_descriptor, NULL, NULL, NULL, NULL);
	// convert from UTF-8 to UCS-2
	size_t iconv_result = iconv(iconv_descriptor, &utf8_buf, &utf8_bytes, (void *)&ucs2_buf, &ucs2_bytes);
	// compute number of characters converted
	int num_chars_converted = KEYBOARD_BUFFER_SIZE - ucs2_bytes/sizeof(jchar);
	if (iconv_result == (size_t)-1) {
		errno = 0; // ignore conversion error and return no chars converted
		return 0;
	}
	for (i = 0; i < num_chars_converted; i++) {
		translation_buffer[i] = ((jint)ucs2_translation_buffer[i]) & 0xffff;
	}
	return num_chars_converted;
}

static int lookupString(XKeyEvent *event, jint *translation_buffer) {
	if (xic != NULL) {
		return lookupStringUnicode(event, translation_buffer);
	} else
		return lookupStringISO88591(event, translation_buffer);
}
				
static void translateEvent(XKeyEvent *event, jint keycode, jint state) {
	jint temp_translation_buffer[KEYBOARD_BUFFER_SIZE];
	int num_chars, i;
	jint ch;

	if (event->type == KeyRelease) {
		putKeyboardEvent(keycode, state, 0);
		return;
	}
	num_chars = lookupString(event, temp_translation_buffer);
	if (num_chars > 0) {
		ch = temp_translation_buffer[0];
		putKeyboardEvent(keycode, state, ch);
		for (i = 1; i < num_chars; i++) {
			ch = temp_translation_buffer[i];
			putKeyboardEvent(0, 0, ch);
		}
	} else {
		putKeyboardEvent(keycode, state, 0);
	}
}

static jbyte eventState(XKeyEvent *event) {
	if (event->type == KeyPress) {
		return 1;
	} else if (event->type == KeyRelease) {
		return 0;
	} else
		assert(0);
}

static void bufferEvent(XKeyEvent *key_event) {
	unsigned char keycode = getKeycode(key_event);
	unsigned char state = eventState(key_event);
	translateEvent(key_event, keycode, state);
}

void handleKeyEvent(XKeyEvent *event) {
	unsigned char keycode = getKeycode(event);
	jbyte state = eventState(event);
	key_buf[keycode] = state;
	bufferEvent(event);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nPollKeyboard(JNIEnv * env, jclass clazz, jobject buffer) {
	jbyte *new_keyboard_buffer = (jbyte *)(*env)->GetDirectBufferAddress(env, buffer);
	memcpy(new_keyboard_buffer, key_buf, org_lwjgl_input_Keyboard_KEYBOARD_SIZE*sizeof(jbyte));
}

JNIEXPORT int JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nReadKeyboard(JNIEnv * env, jclass clazz, jobject buffer, jint buffer_position) {
	jint* buffer_ptr = (jint *)(*env)->GetDirectBufferAddress(env, buffer);
	int buffer_size = ((*env)->GetDirectBufferCapacity(env, buffer))/sizeof(jint) - buffer_position;
	return copyEvents(&event_queue, buffer_ptr + buffer_position, buffer_size);
}
