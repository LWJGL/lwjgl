/*
 * org_lwjgl_input_Mouse.cpp
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
#include "org_lwjgl_input_Mouse.h"
#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0300
#include <dinput.h>

extern LPDIRECTINPUT lpdi;
LPDIRECTINPUTDEVICE		lpdiMouse;
extern HWND				hwnd; // The display, which must have been created
jfieldID fid_button;
jfieldID fid_dx;
jfieldID fid_dy;
jfieldID fid_dz;

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs
  (JNIEnv * env, jclass clazz)
{
	// Get a global class instance, just to be sure
	static jobject globalClassLock = NULL;

	if (globalClassLock == NULL) {
		globalClassLock = env->NewGlobalRef(clazz);
	}

	// Now cache the field IDs:
	if (fid_button == NULL) {
		fid_button = env->GetStaticFieldID(clazz, "button", "[Z");
	}
	if (fid_dx == NULL) {
		fid_dx = env->GetStaticFieldID(clazz, "dx", "I");
	}
	if (fid_dy == NULL) {
		fid_dy = env->GetStaticFieldID(clazz, "dy", "I");
	}
	if (fid_dz == NULL) {
		fid_dz = env->GetStaticFieldID(clazz, "dz", "I");
	}
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nCreate
  (JNIEnv * env, jclass clazz)
{
	// Check to see if we're already initialized
	if (lpdiMouse != NULL) {
		printf("Mouse already created.\n");
		return JNI_FALSE;
	}

	if (hwnd == NULL) {
		printf("No window\n");
		return JNI_FALSE;
	}

	// First get reference to directinput:
	if (lpdi == NULL) {
		HRESULT ret = DirectInputCreate((HINSTANCE)GetCurrentProcess(), DIRECTINPUT_VERSION, &lpdi, NULL);
		if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION ) {
			printf("Failed to create directinput\n");
			return JNI_FALSE;
		}
	}

	// Get mouse device
	if (lpdi->CreateDevice(GUID_SysMouse, &lpdiMouse, NULL) != DI_OK) {
		printf("Failed to create mouse\n");
		return JNI_FALSE;
	}

	// Grab non-exclusive foreground access to device
	if (lpdiMouse->SetCooperativeLevel(hwnd, DISCL_NONEXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
		printf("Failed to set mouse coop\n");
		return JNI_FALSE;
	}

	// Tell 'em wot format to be in (the default "you are a mouse and keyboard" format)
	lpdiMouse->SetDataFormat(&c_dfDIMouse);

	HRESULT ret = lpdiMouse->Acquire();
	if (ret != DI_OK && ret != S_FALSE) {
#ifdef _DEBUG
		printf("Failed to acquire mouse\n");
#endif
	}
	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy
  (JNIEnv * env, jclass clazz)
{
	
	// Release mouse
	if (lpdiMouse != NULL) {
		lpdiMouse->Unacquire();
		lpdiMouse->Release();
		lpdiMouse = NULL;
	}

	// Release directinput
	if (lpdi != NULL) {
		// Release directinput
		lpdi->Release();
		lpdi = NULL;
	}
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll
  (JNIEnv * env, jclass clazz)
{
	DIMOUSESTATE diMouseState;
	HRESULT ret;
	while (ret = lpdiMouse->GetDeviceState(sizeof(diMouseState), &diMouseState) != DI_OK) {
		ret = lpdiMouse->Acquire();
		if (ret != DI_OK && ret != S_FALSE) {
#ifdef _DEBUG
			printf("Failed to acquire mouse\n");
#endif
			return;
		}
	}

	if (ret == DI_OK) {
		env->SetStaticIntField(clazz, fid_dx, (jint)diMouseState.lX);
		env->SetStaticIntField(clazz, fid_dy, (jint)diMouseState.lY);
		env->SetStaticIntField(clazz, fid_dz, (jint)diMouseState.lZ);
		jbooleanArray buttonsArray = (jbooleanArray) env->GetStaticObjectField(clazz, fid_button);
		BYTE * buttons = (BYTE *) env->GetPrimitiveArrayCritical(buttonsArray, NULL);
		memcpy(buttons, diMouseState.rgbButtons, 4);
		env->ReleasePrimitiveArrayCritical(buttonsArray, buttons, 0);
	} else {
#ifdef _DEBUG
		printf("Failed to get mouse device state\n");
#endif
	}

}
