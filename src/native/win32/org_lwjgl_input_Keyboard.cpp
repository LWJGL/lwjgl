/*
 * org_lwjgl_input_Keyboard.cpp
 *
 * Win32 specific library
 *
 * (C) 2002 Shaven Puppy Ltd
 *
 * Created 10 August 2002 by foo
 *
 */

#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <dinput.h>
#include "org_lwjgl_input_Keyboard.h"

#define KEYBOARD_BUFFER_SIZE 50
BYTE readBuffer[KEYBOARD_BUFFER_SIZE];
LPDIRECTINPUTDEVICE lpdiKeyboard		= NULL;
jfieldID fid_readBuffer;
jfieldID fid_readBufferAddress;
extern LPDIRECTINPUT lpdi;
extern HWND hwnd;

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs
  (JNIEnv * env, jclass clazz)
{
	// Get a global class instance, just to be sure
	static jobject globalClassLock = NULL;

	if (globalClassLock == NULL) {
		globalClassLock = env->NewGlobalRef(clazz);
	}

	fid_readBuffer = env->GetFieldID(clazz, "readBuffer", "Ljava/nio/ByteBuffer;");
	fid_readBufferAddress = env->GetFieldID(clazz, "readBufferAddress", "I");
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Keyboard_nCreate
  (JNIEnv * env, jclass clazz)
{
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
	if (ret != DI_OK && ret != S_FALSE) {
		printf("Failed to acquire keyboard\n");
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

	// Release directinput if the mouse is not present
	if (lpdi != NULL) {
		// Release directinput
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
  (JNIEnv * env, jclass clazz, jint buf)
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
	
	lpdiKeyboard->GetDeviceState(256, (void *)buf);
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nRead
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead
  (JNIEnv * env, jclass clazz, jint keys)
{
	
	static DIDEVICEOBJECTDATA rgdod[KEYBOARD_BUFFER_SIZE];
	DWORD bufsize = KEYBOARD_BUFFER_SIZE;

	HRESULT ret;
	do {
		ret = lpdiKeyboard->Acquire();
	} while (ret != DI_OK && ret != S_FALSE);

	ret = lpdiKeyboard->GetDeviceData( 
		sizeof(DIDEVICEOBJECTDATA), 
		rgdod, 
		&bufsize,
		0); 

	if (ret == DI_OK) {
		unsigned char * buf = (unsigned char *) keys;
		for (unsigned int i = 0; i < bufsize; i ++) {
			*buf++ = (unsigned char) rgdod[i].dwOfs;
			*buf++ = (unsigned char) rgdod[i].dwData;
		}
		return bufsize;
	} if (ret == DI_BUFFEROVERFLOW) { 
#ifdef _DEBUG
		printf("Keyboard buffer overflowed\n");
#endif
		return -1;
	} else if (ret == DIERR_INPUTLOST) {
#ifdef _DEBUG
		printf("Input lost\n");
#endif
		return -1;
	} else if (ret == DIERR_NOTACQUIRED) {
#ifdef _DEBUG
		printf("not acquired\n");
#endif
		return -1;
	} else if (ret == DIERR_INVALIDPARAM) {
#ifdef _DEBUG
		printf("invalid parameter\n");
#endif
		return -1;
	} else if (ret == DIERR_NOTBUFFERED) {
#ifdef _DEBUG
		printf("not buffered\n");
#endif
		return -1;
	} else if (ret == DIERR_NOTINITIALIZED) {
#ifdef _DEBUG
		printf("not inited\n");
#endif
		return -1;
	} else {
#ifdef _DEBUG
		printf("unknown keyboard error\n");
#endif
		return -1;
	}
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer
  (JNIEnv * env, jclass clazz)
{
	jobject newBuffer = env->NewDirectByteBuffer(&readBuffer, KEYBOARD_BUFFER_SIZE);
	env->SetStaticObjectField(clazz, fid_readBuffer, newBuffer);
	env->SetStaticIntField(clazz, fid_readBufferAddress, (jint) (&readBuffer));
	return KEYBOARD_BUFFER_SIZE;
}