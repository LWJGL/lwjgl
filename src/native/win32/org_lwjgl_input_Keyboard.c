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
 * Win32 keyboard handling.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0300
#include "Window.h"
#include <dinput.h>
#include "org_lwjgl_opengl_Win32Display.h"
#include "org_lwjgl_input_Keyboard.h"

#include "common_tools.h"

#define KEYBOARD_BUFFER_SIZE 50

extern HINSTANCE	dll_handle;							        // Handle to the LWJGL dll

static LPDIRECTINPUT		lpdi = NULL;						          // DirectInput
static LPDIRECTINPUTDEVICE lpdiKeyboard		= NULL;

static bool useUnicode;

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_createKeyboard
  (JNIEnv * env, jobject self)
{
	OSVERSIONINFO osvi;
	DIPROPDWORD dipropdw;
	// Create input
	HRESULT ret = DirectInputCreate(dll_handle, DIRECTINPUT_VERSION, &lpdi, NULL);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION) {
		throwException(env, "Failed to create DirectInput");
		return;
	}

	// Check to see if we're already initialized
	if (lpdiKeyboard != NULL) {
		throwException(env, "Keyboard already created.");
		return;
	}

	// Create a keyboard device
        if (IDirectInput_CreateDevice(lpdi, &GUID_SysKeyboard, &lpdiKeyboard, NULL) != DI_OK) {
		throwException(env, "Failed to create keyboard.");
		return;
	}

        if (IDirectInputDevice_SetCooperativeLevel(lpdiKeyboard, getCurrentHWND(), DISCL_NONEXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
		throwException(env, "Failed to set keyboard cooperation mode.");
		return;
	}

	// Tell 'em wot format to be in (the default "you are a mouse and keyboard" format)
        IDirectInputDevice_SetDataFormat(lpdiKeyboard, &c_dfDIKeyboard);

	dipropdw.diph.dwSize = sizeof(DIPROPDWORD);
	dipropdw.diph.dwHeaderSize = sizeof(DIPROPHEADER);
	dipropdw.diph.dwObj = 0;
	dipropdw.diph.dwHow = DIPH_DEVICE;
	dipropdw.dwData = KEYBOARD_BUFFER_SIZE;
        IDirectInputDevice_SetProperty(lpdiKeyboard, DIPROP_BUFFERSIZE, &dipropdw.diph);

        ret = IDirectInputDevice_Acquire(lpdiKeyboard);
	if(FAILED(ret)) {
		printfDebug("Failed to acquire keyboard\n");
	}
	osvi.dwOSVersionInfoSize = sizeof(osvi);
	GetVersionEx(&osvi);
	
	if (osvi.dwPlatformId == VER_PLATFORM_WIN32_NT) {
		useUnicode = true;
	} else {
		useUnicode = false;
	}
}

}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_destroyKeyboard
  (JNIEnv * env, jobject self)
{
	// Release keyboard
	if (lpdiKeyboard != NULL) {
                IDirectInputDevice_Unacquire(lpdiKeyboard);
                IDirectInputDevice_Release(lpdiKeyboard);
		lpdiKeyboard = NULL;
	}
	// Release DirectInput
	if (lpdi != NULL) {
		printfDebug("Destroying directinput\n");
                IDirectInput_Release(lpdi);
		lpdi = NULL;
	}
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nPoll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_pollKeyboard
  (JNIEnv * env, jobject self, jobject buffer)
{
	HRESULT ret;
        void *keyboardBuffer;
        jlong buffer_size;
	do {
                ret = IDirectInputDevice_Acquire(lpdiKeyboard);
		if (ret == DIERR_INPUTLOST) {
			printf("Input lost\n");
			return;
		} else if (ret == DIERR_NOTACQUIRED) {
			printf("not acquired\n");
			return;
		} else if (ret == DIERR_INVALIDPARAM) {
			printf("invalid parameter\n");
			return;
		} else if (ret == DIERR_NOTBUFFERED) {
			printf("not buffered\n");
			return;
		} else if (ret == DIERR_NOTINITIALIZED) {
			printf("not inited\n");
			return;
		} else if (ret != DI_OK && ret != S_FALSE) {
			//printf("unknown keyboard error\n");
			return;
		}
	} while (ret != DI_OK && ret != S_FALSE);
	
        keyboardBuffer = (void *)(*env)->GetDirectBufferAddress(env, buffer);
        buffer_size = (*env)->GetDirectBufferCapacity(env, buffer);
        IDirectInputDevice_GetDeviceState(lpdiKeyboard, (DWORD)buffer_size, keyboardBuffer);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Win32Display_readKeyboard
  (JNIEnv * env, jobject self, jobject buffer_obj, jint buffer_position)
{
        UINT scan_code;
        UINT virt_key;
        bool key_down;
        jint * buf;
        jint ch_int;
        int buffer_size;
        int index = 0;
        int event_size = 3;
        DWORD current_di_event = 0;
        DIDEVICEOBJECTDATA rgdod[KEYBOARD_BUFFER_SIZE];
	wchar_t transBufUnicode[KEYBOARD_BUFFER_SIZE];
	WORD transBufAscii[KEYBOARD_BUFFER_SIZE];

	BYTE state[256];
	DWORD num_di_events = KEYBOARD_BUFFER_SIZE;

	HRESULT ret;
	int num_chars;
	int num_events = 0;

        ret = IDirectInputDevice_Acquire(lpdiKeyboard);
	if (ret != DI_OK && ret != S_FALSE)
		return 0;

        ret = IDirectInputDevice_GetDeviceData(lpdiKeyboard,
		sizeof(DIDEVICEOBJECTDATA), 
		rgdod, 
		&num_di_events,
		0); 

	if (ret == DI_OK) {
                buf = buffer_position + (jint *)(*env)->GetDirectBufferAddress(env, buffer_obj);
                buffer_size = ((int)(*env)->GetDirectBufferCapacity(env, buffer_obj))/sizeof(jint) - buffer_position;
		while (index + event_size <= buffer_size && current_di_event < num_di_events) {
			num_events++;
			buf[index++] = (unsigned char) rgdod[current_di_event].dwOfs;
			buf[index++] = (unsigned char) rgdod[current_di_event].dwData;
                        key_down = (rgdod[current_di_event].dwData & 0x80) != 0;
			if (key_down) {
                                scan_code = rgdod[current_di_event].dwOfs;
                                virt_key = MapVirtualKey(scan_code, 1);
				if (virt_key != 0 && GetKeyboardState(state)) {
					// Mark key down in the scan code
					scan_code = scan_code & 0x7fff;
					if (useUnicode) {
						num_chars = ToUnicode(virt_key, 
											  scan_code,
											  state,
											  transBufUnicode,
											  KEYBOARD_BUFFER_SIZE, 0);
					} else {
						num_chars = ToAscii(virt_key,
											scan_code,
											state,
											transBufAscii,
											0);
					}
					if (num_chars > 0) {
						int current_char = 0;
						do {
							if (current_char >= 1) {
								num_events++;
								buf[index++] = 0;
								buf[index++] = 0;
							}
							if (useUnicode) {
								wchar_t ch = transBufUnicode[current_char];
								ch_int = ((int)ch) & 0xFFFF;
							} else {
								unsigned char ch = (unsigned char)transBufAscii[current_char];
								ch_int = ((int)ch) & 0xFF;
							}
							buf[index++] = ch_int;
							current_char++;
						} while (index + event_size <= buffer_size && current_char < num_chars);
					} else {
						buf[index++] = 0;
					}
				} else {
					buf[index++] = 0;
				}
			} else
				buf[index++] = 0;
			current_di_event++;
		}
	} else if (ret == DI_BUFFEROVERFLOW) { 
		printfDebug("Keyboard buffer overflowed\n");
	} else if (ret == DIERR_INPUTLOST) {
		printfDebug("Input lost\n");
	} else if (ret == DIERR_NOTACQUIRED) {
		printfDebug("not acquired\n");
	} else if (ret == DIERR_INVALIDPARAM) {
		printfDebug("invalid parameter\n");
	} else if (ret == DIERR_NOTBUFFERED) {
		printfDebug("not buffered\n");
	} else if (ret == DIERR_NOTINITIALIZED) {
		printfDebug("not inited\n");
	} else {
		printfDebug("unknown keyboard error\n");
	}
	return num_events;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Win32Display_isStateKeySet(JNIEnv *env, jobject self, jint key)
{
  int state = org_lwjgl_input_Keyboard_STATE_UNKNOWN;
  switch(key) {
    case org_lwjgl_input_Keyboard_KEY_CAPITAL: 
      state = GetKeyState(VK_CAPITAL) ? org_lwjgl_input_Keyboard_STATE_ON : org_lwjgl_input_Keyboard_STATE_OFF;
      break;
    case org_lwjgl_input_Keyboard_KEY_NUMLOCK: 
      state = GetKeyState(VK_NUMLOCK) ? org_lwjgl_input_Keyboard_STATE_ON : org_lwjgl_input_Keyboard_STATE_OFF;
      break;
    case org_lwjgl_input_Keyboard_KEY_SCROLL: 
      state = GetKeyState(VK_SCROLL) ? org_lwjgl_input_Keyboard_STATE_ON : org_lwjgl_input_Keyboard_STATE_OFF;
      break;
  }
  
  return state;
}
