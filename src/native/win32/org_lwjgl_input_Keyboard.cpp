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

#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0300
#include <dinput.h>
#include "org_lwjgl_input_Keyboard.h"
#include "Window.h"

#include "common_tools.h"

#define KEYBOARD_BUFFER_SIZE 50

extern HINSTANCE	dll_handle;							        // Handle to the LWJGL dll

static LPDIRECTINPUT		lpdi = NULL;						          // DirectInput
static LPDIRECTINPUTDEVICE lpdiKeyboard		= NULL;
static bool translationEnabled;

static bool useUnicode;

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nCreate
  (JNIEnv * env, jclass clazz)
{
	// Create input
	HRESULT ret = DirectInputCreate(dll_handle, DIRECTINPUT_VERSION, &lpdi, NULL);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION) {
		throwException(env, "Failed to create DirectInput");
		return;
	}

	translationEnabled = false;
	// Check to see if we're already initialized
	if (lpdiKeyboard != NULL) {
		throwException(env, "Keyboard already created.");
		return;
	}

	if (display_hwnd == NULL) {
		throwException(env, "No window.");
		return;
	}

	// Create a keyboard device
	if (lpdi->CreateDevice(GUID_SysKeyboard, &lpdiKeyboard, NULL) != DI_OK) {
		throwException(env, "Failed to create keyboard.");
		return;
	}

	if (lpdiKeyboard->SetCooperativeLevel(display_hwnd, DISCL_NONEXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
		throwException(env, "Failed to set keyboard cooperation mode.");
		return;
	}

	// Tell 'em wot format to be in (the default "you are a mouse and keyboard" format)
	lpdiKeyboard->SetDataFormat(&c_dfDIKeyboard);

	DIPROPDWORD dipropdw;
	dipropdw.diph.dwSize = sizeof(DIPROPDWORD);
	dipropdw.diph.dwHeaderSize = sizeof(DIPROPHEADER);
	dipropdw.diph.dwObj = 0;
	dipropdw.diph.dwHow = DIPH_DEVICE;
	dipropdw.dwData = KEYBOARD_BUFFER_SIZE;
	lpdiKeyboard->SetProperty(DIPROP_BUFFERSIZE, &dipropdw.diph);

	ret = lpdiKeyboard->Acquire();
	if(FAILED(ret)) {
		printfDebug("Failed to acquire keyboard\n");
	}
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy
  (JNIEnv * env, jclass clazz)
{
	// Release keyboard
	if (lpdiKeyboard != NULL) {
		lpdiKeyboard->Unacquire();
		lpdiKeyboard->Release();
		lpdiKeyboard = NULL;
	}
	// Release DirectInput
	if (lpdi != NULL) {
		printfDebug("Destroying directinput\n");
		lpdi->Release();
		lpdi = NULL;
	}
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nPoll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll
  (JNIEnv * env, jclass clazz, jobject buffer)
{
	HRESULT ret;
	do {
		ret = lpdiKeyboard->Acquire();
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
	
	void *keyboardBuffer = (void *)env->GetDirectBufferAddress(buffer);
	jlong buffer_size = env->GetDirectBufferCapacity(buffer);
	lpdiKeyboard->GetDeviceState((DWORD)buffer_size, keyboardBuffer);
}


/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nRead
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead
  (JNIEnv * env, jclass clazz, jobject buffer_obj, jint buffer_position)
{
	static DIDEVICEOBJECTDATA rgdod[KEYBOARD_BUFFER_SIZE];
	wchar_t transBufUnicode[KEYBOARD_BUFFER_SIZE];
	WORD transBufAscii[KEYBOARD_BUFFER_SIZE];

	BYTE state[256];
	DWORD num_di_events = KEYBOARD_BUFFER_SIZE;

	HRESULT ret;
	int num_chars;
	int num_events = 0;

	ret = lpdiKeyboard->Acquire();
	if (ret != DI_OK && ret != S_FALSE)
		return 0;

	ret = lpdiKeyboard->GetDeviceData( 
		sizeof(DIDEVICEOBJECTDATA), 
		rgdod, 
		&num_di_events,
		0); 

	if (ret == DI_OK) {
		unsigned char * buf = buffer_position + (unsigned char *)env->GetDirectBufferAddress(buffer_obj);
		int buffer_size = (int)env->GetDirectBufferCapacity(buffer_obj) - buffer_position;
		int index = 0;
		int event_size = translationEnabled ? 4 : 2;
		DWORD current_di_event = 0;
		while (index + event_size <= buffer_size && current_di_event < num_di_events) {
			num_events++;
			buf[index++] = (unsigned char) rgdod[current_di_event].dwOfs;
			buf[index++] = (unsigned char) rgdod[current_di_event].dwData;
			if (translationEnabled) {
				UINT virt_key = MapVirtualKey(rgdod[current_di_event].dwOfs, 1);
				if (virt_key != 0 && GetKeyboardState(state)) {
					if (useUnicode) {
						num_chars = ToUnicode(virt_key, 
											  rgdod[current_di_event].dwOfs,
											  state,
											  transBufUnicode,
											  KEYBOARD_BUFFER_SIZE, 0);
					} else {
						num_chars = ToAscii(virt_key,
											rgdod[current_di_event].dwOfs,
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
								buf[index++] = (unsigned char) (ch & 0xff);
								buf[index++] = (unsigned char) ((ch & 0xff00) >> 8);
							} else {
								buf[index++] = (unsigned char)transBufAscii[current_char];
								buf[index++] = 0;
							}
							current_char++;
						} while (index + event_size <= buffer_size && current_char < num_chars);
					} else {
						buf[index++] = 0;
						buf[index++] = 0;
					}
				} else {
					buf[index++] = 0;
					buf[index++] = 0;
				}
			}
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

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableTranslation
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableTranslation
  (JNIEnv *, jclass)
{
	// We can't do translation on DOS boxes it seems so we'll have to throw a wobbler
	// here:
	OSVERSIONINFO osvi;

	osvi.dwOSVersionInfoSize = sizeof(osvi);
	GetVersionEx(&osvi);
	
	if (osvi.dwPlatformId == VER_PLATFORM_WIN32_NT) {
		useUnicode = true;
	} else {
		useUnicode = false;
	}
	translationEnabled = true;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nisStateKeySet
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nisStateKeySet(JNIEnv *env, jclass clazz, jint key)
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
