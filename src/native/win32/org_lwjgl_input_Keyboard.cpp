/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

#define KEYBOARD_BUFFER_SIZE 50

static BYTE readBuffer[KEYBOARD_BUFFER_SIZE*4];
static LPDIRECTINPUTDEVICE lpdiKeyboard		= NULL;
static jfieldID fid_readBuffer;
static bool translationEnabled;
static bool useUnicode;


/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs
  (JNIEnv * env, jclass clazz)
{
	/*
	// Get a global class instance, just to be sure
	static jobject globalClassLock = NULL;

	if (globalClassLock == NULL) {
		globalClassLock = env->NewGlobalRef(clazz);
	}
	*/
	fid_readBuffer = env->GetStaticFieldID(clazz, "readBuffer", "Ljava/nio/ByteBuffer;");
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Keyboard_nCreate
  (JNIEnv * env, jclass clazz)
{
	translationEnabled = false;
	// Check to see if we're already initialized
	if (lpdiKeyboard != NULL) {
		printf("Keyboard already created.\n");
		return 1;
	}

	if (hwnd == NULL) {
		printf("No window\n");
		return JNI_FALSE;
	}

	// Create a keyboard device
	if (lpdi->CreateDevice(GUID_SysKeyboard, &lpdiKeyboard, NULL) != DI_OK) {
		printf("Failed to create keyboard\n");
		return JNI_FALSE;
	}

	if (lpdiKeyboard->SetCooperativeLevel(hwnd, DISCL_NONEXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
		printf("Failed to keyboard coop\n");
		return JNI_FALSE;
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

	HRESULT ret = lpdiKeyboard->Acquire();
	if(FAILED(ret)) {
#if _DEBUG
    printf("Failed to acquire keyboard\n");
#endif	
	}

	return JNI_TRUE;

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
	lpdiKeyboard->GetDeviceState(256, keyboardBuffer);
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nRead
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead
  (JNIEnv * env, jclass clazz)
{
	
	static DIDEVICEOBJECTDATA rgdod[KEYBOARD_BUFFER_SIZE];
	wchar_t transBufUnicode[KEYBOARD_BUFFER_SIZE];
	WORD transBufAscii[KEYBOARD_BUFFER_SIZE];

	BYTE state[256];
	DWORD bufsize = KEYBOARD_BUFFER_SIZE;

	HRESULT ret;
	int num_chars;
	int num_events = 0;

	ret = lpdiKeyboard->Acquire();
	if (ret != DI_OK && ret != S_FALSE)
		return 0;

	ret = lpdiKeyboard->GetDeviceData( 
		sizeof(DIDEVICEOBJECTDATA), 
		rgdod, 
		&bufsize,
		0); 

	if (ret == DI_OK) {
		unsigned char * buf = readBuffer;
//#ifdef _DEBUG
//		if (bufsize > 0) {
//			printf("Got %d keyboard events.\n", bufsize);
//		}
//#endif
		for (unsigned int i = 0; i < bufsize; i ++) {
			num_events++;
			*buf++ = (unsigned char) rgdod[i].dwOfs;
			*buf++ = (unsigned char) rgdod[i].dwData;
			if (translationEnabled) {
				UINT virt_key = MapVirtualKey(rgdod[i].dwOfs, 1);
				if (virt_key != 0 && GetKeyboardState(state)) {
					if (useUnicode) {
						num_chars = ToUnicode(virt_key, 
											  rgdod[i].dwOfs,
											  state,
											  transBufUnicode,
											  KEYBOARD_BUFFER_SIZE, 0);
					} else {
						num_chars = ToAscii(virt_key,
											rgdod[i].dwOfs,
											state,
											transBufAscii,
											0);
					}
					if (num_chars > 0) {
						for (int i = 0; i < num_chars; i++) {
							if (i >= 1) {
								num_events++;
								*buf++ = 0;
								*buf++ = 0;
							}
							if (useUnicode) {
								wchar_t ch = transBufUnicode[i];
								*buf++ = (unsigned char) (ch & 0xff);
								*buf++ = (unsigned char) ((ch & 0xff00) >> 8);
							} else {
								*buf++ = (unsigned char)transBufAscii[0];
								*buf++ = 0;
							}
						}
					} else {
						*buf++ = 0;
						*buf++ = 0;
					}
				} else {
					*buf++ = 0;
					*buf++ = 0;
				}
			}
		}
	} else if (ret == DI_BUFFEROVERFLOW) { 
#ifdef _DEBUG
		printf("Keyboard buffer overflowed\n");
#endif
	} else if (ret == DIERR_INPUTLOST) {
#ifdef _DEBUG
		printf("Input lost\n");
#endif
	} else if (ret == DIERR_NOTACQUIRED) {
#ifdef _DEBUG
		printf("not acquired\n");
#endif
	} else if (ret == DIERR_INVALIDPARAM) {
#ifdef _DEBUG
		printf("invalid parameter\n");
#endif
	} else if (ret == DIERR_NOTBUFFERED) {
#ifdef _DEBUG
		printf("not buffered\n");
#endif
	} else if (ret == DIERR_NOTINITIALIZED) {
#ifdef _DEBUG
		printf("not inited\n");
#endif
	} else {
#ifdef _DEBUG
		printf("unknown keyboard error\n");
#endif
	}
	return num_events;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableTranslation
 * Signature: ()V
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Keyboard_nEnableTranslation
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
	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer
  (JNIEnv * env, jclass clazz)
{
	jobject newBuffer = env->NewDirectByteBuffer(&readBuffer, KEYBOARD_BUFFER_SIZE*4);
	env->SetStaticObjectField(clazz, fid_readBuffer, newBuffer);
	return KEYBOARD_BUFFER_SIZE;
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