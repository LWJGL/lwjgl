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
 * Win32 analogue joystick handling.
 *
 * @author Brian Matzon <brian@matzon.com>
 * @version $Revision$
 */
#define WIN32_LEAN_AND_MEAN

#include "org_lwjgl_input_Joystick.h"

#include <windows.h>
#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0500
#include <dinput.h>

#define JOYMAX 1000					// Maxmimum range to which we'll gauge the swing
#define JOYMIN -1000				// Minimum range to which we'll gauge the swing

extern HWND hwnd;					// Handle to window

IDirectInput* lpDI;					// DI instance
IDirectInputDevice2* lpDIDevice;	// DI Device instance
DIJOYSTATE2 js;						// State of joystick

int buttoncount = 0;				// Temporary buttoncount
bool hasz;							// Temporary zaxis check
bool haspov;						// Temporary pov check

JNIEnv* environment;				// JNIEnvironment copy

bool create_success;				// bool used to determine successfull creation

// Cached fields of Joystick.java
jclass clsJoystick;
jobject objJoystick;
jfieldID fidButtonCount;
jfieldID fidHasZAxis;
jfieldID fidHasPOV;
jfieldID fidButtons;
jfieldID fidX;
jfieldID fidY;
jfieldID fidZ;
jfieldID fidPOV;

// Function prototypes (defined in the cpp file, since header file is generic across platforms
void EnumerateCapabilities();
void EnumerateJoysticks();
BOOL CALLBACK EnumJoystickCallback(LPCDIDEVICEINSTANCE pdinst, LPVOID pvRef);
BOOL CALLBACK EnumJoystickObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef);
void Shutdown();
void CreateJoystick(LPCDIDEVICEINSTANCE lpddi);
void SetupJoystick();
void InitializeFields();
void UpdateFields();
void SetCapabilities();
void CacheFields(jobject obj);
void PrintError(HRESULT error);

/**
 * Initializes any field ids
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Joystick_initIDs(JNIEnv * env, jclass clazz) {
	environment = env;
}

/**
 * Called when the Joystick instance is to be created
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Joystick_nCreate(JNIEnv *env, jobject obj) {
	/* Cache fields in Joystick */
	CacheFields(obj);

	// Create the DirectInput object. 
	HRESULT hr;
	hr = DirectInputCreate(GetModuleHandle(NULL), DIRECTINPUT_VERSION, &lpDI, NULL); 
	if (FAILED(hr)) {
		Shutdown();
		return JNI_FALSE;
	}

	/*  Find all joysticks */
	EnumerateJoysticks();
	if (!create_success) {
		Shutdown();
		return JNI_FALSE;
	}

	/* Enumerate capabilities of joystick */
	EnumerateCapabilities();
	if (!create_success) {
		Shutdown();
		return JNI_FALSE;
	}

	/* Initialize any fields on the Joystick */
	InitializeFields();

	/* Set capabilities */
	SetCapabilities();

	/* Aquire the joystick */
	hr = lpDIDevice->Acquire();
	if(FAILED(hr)) {
		Shutdown();
		return JNI_FALSE;
	}

	return create_success;
}

/*
 * Class:     org_lwjgl_input_Joystick
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Joystick_nDestroy(JNIEnv *env, jobject obj) {
	Shutdown();
}

/*
 * Class:     org_lwjgl_input_Joystick
 * Method:    nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Joystick_nPoll(JNIEnv * env, jobject obj) {
	lpDIDevice->Poll();
	UpdateFields();
}

/**
 * Shutdown DI
 */
void Shutdown() {
	// release DI instance
	if (lpDI != NULL) {
		// release device
		if (lpDIDevice != NULL) {
			lpDIDevice->Unacquire();
			lpDIDevice->Release();
			lpDIDevice = NULL;
		}
		lpDI->Release();
		lpDI = NULL;
	}

	// Delete are global reference to the Joystick
	if(objJoystick != NULL) {
		environment->DeleteGlobalRef(objJoystick);
	}
}

/**
 * Enumerates the capabilities of the joystick attached to the system
 */
void EnumerateCapabilities() {
	HRESULT hr;
	hr = lpDIDevice->EnumObjects(EnumJoystickObjectsCallback, NULL, DIDFT_ALL);
	if FAILED(hr) { 
		create_success = false;
		return;
	}
	create_success = true;
}

/**
 * Enumerates the joysticks attached to the system
 */
void EnumerateJoysticks() {
	HRESULT hr;
	hr = lpDI->EnumDevices(DIDEVTYPE_JOYSTICK, EnumJoystickCallback, 0, DIEDFL_ATTACHEDONLY);
	if FAILED(hr) { 
		create_success = false;
		return;
	} 
	create_success = true;
}

/**
 * Callback from EnumDevices. Called for each joystick attached to the system
 */
BOOL CALLBACK EnumJoystickCallback(LPCDIDEVICEINSTANCE pdinst, LPVOID pvRef) {
	/* Add the joystick */
	CreateJoystick(pdinst);
	if(create_success) {
		/* Do setup of joystick */
		SetupJoystick();
	}

	/* just stop after 1st joystick */
	return DIENUM_STOP;
}

/**
 * Callback from EnumObjects. Called for each "object" on the joystick.
 */
BOOL CALLBACK EnumJoystickObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
	if(lpddoi->guidType == GUID_Button) {
		buttoncount++;
	} else if(lpddoi->guidType == GUID_XAxis || lpddoi->guidType == GUID_YAxis) {
		//do nothing
	} else if(lpddoi->guidType == GUID_ZAxis || strcmp("Throttle", lpddoi->tszName) == 0){
		hasz = true;
	} else if (lpddoi->guidType == GUID_POV){
		haspov = true;
	} else {
#ifdef _DEBUG
		printf("Unhandled object found: %s\n", lpddoi->tszName);
#endif
	}
	return DIENUM_CONTINUE;
}

/**
 * Creates the specified device as a joystick
 */
void CreateJoystick(LPCDIDEVICEINSTANCE lpddi) {
	HRESULT hr;
	hr = lpDI->CreateDevice(lpddi->guidInstance, (LPDIRECTINPUTDEVICE*) &lpDIDevice, NULL);
	if FAILED(hr) {	
		create_success = false;
		return;
	}
	create_success = true;
}

/**
 * Sets up the joystick properties
 */ 
void SetupJoystick() {
	// set joystick data format
	if(lpDIDevice->SetDataFormat(&c_dfDIJoystick2) != DI_OK) {
		create_success = false;
		return;
	}

	// set the cooperative level
	if(lpDIDevice->SetCooperativeLevel(hwnd, DISCL_NONEXCLUSIVE | DISCL_BACKGROUND) != DI_OK) {
		create_success = false;
		return;
	}

	// set X-axis range to (-1000 ... +1000)
	// This lets us test against 0 to see which way the stick is pointed.
	DIPROPRANGE diprg;
	diprg.diph.dwSize       = sizeof(diprg);
	diprg.diph.dwHeaderSize = sizeof(diprg.diph);
	diprg.diph.dwObj        = DIJOFS_X;
	diprg.diph.dwHow        = DIPH_BYOFFSET;
	diprg.lMin              = JOYMIN;
	diprg.lMax              = JOYMAX;

	if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		create_success = false;
		return;
	}

	//
	// And again for Y-axis range
	//
	diprg.diph.dwObj        = DIJOFS_Y;

	if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK)
	{
		create_success = false;
		return;
	}

	//
	// And again for z-axis range (throttle)
	//
	diprg.diph.dwObj        = DIJOFS_Z;

	if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		create_success = false;
		return;
	}
	create_success = true;
}

/**
 * Aquires the Joystick
 */
bool AcquireInput(void) { 
    HRESULT hRes; 
 
    // if we have a device 
    if (lpDIDevice != NULL) { 
       // acquire the device 
       hRes = lpDIDevice->Acquire();
 
       if (SUCCEEDED(hRes)) { 
          // acquisition successful 
          return TRUE; 
       } else { 
          // acquisition failed 
          return FALSE; 
       } 
    } else { 
       // we don't have a current device 
       return FALSE; 
    } 
}

/**
 * Sets the fields on the Joystick
 */
void InitializeFields() {
	//set buttons array
	jbooleanArray buttonsArray = environment->NewBooleanArray(buttoncount);
	environment->SetObjectField(objJoystick, fidButtons, buttonsArray);
}

/**
 * Updates the fields on the Joystick
 */
void UpdateFields() {
	HRESULT                 hRes; 

    // poll the joystick to read the current state
    hRes = lpDIDevice->Poll();
	if (FAILED(hRes)) {
#if _DEBUG
		printf("Poll fail\n");
#endif
		return;
	}
 
    // get data from the joystick 
    hRes = lpDIDevice->GetDeviceState(sizeof(DIJOYSTATE2), &js); 
 
    if (hRes != DI_OK) { 
		// did the read fail because we lost input for some reason? 
		// if so, then attempt to reacquire. 
		if(hRes == DIERR_INPUTLOST) {
			AcquireInput();
#if _DEBUG
			printf("DIERR_INPUTLOST, reaquiring input\n");
#endif
		}
		return;
    }
	
	//axis's
	environment->SetIntField(objJoystick, fidX, js.lX);
	environment->SetIntField(objJoystick, fidY, js.lY);
	if(hasz) {
		environment->SetIntField(objJoystick, fidZ, js.lZ);
	}

	//buttons
	jbooleanArray buttonsArray = (jbooleanArray) environment->GetObjectField(objJoystick, fidButtons);
	BYTE * buttons = (BYTE *) environment->GetPrimitiveArrayCritical(buttonsArray, NULL);
	memcpy(buttons, js.rgbButtons, 4);
	environment->ReleasePrimitiveArrayCritical(buttonsArray, buttons, 0);

	//pov
	if(haspov) {
		environment->SetIntField(objJoystick, fidPOV, js.rgdwPOV[0]);
	}
}

/**
 * Sets the capabilities of the joystick
 */
void SetCapabilities() {
	//set buttoncount
	environment->SetIntField(objJoystick, fidButtonCount, buttoncount);

	//set z axis
	environment->SetIntField(objJoystick, fidHasZAxis, hasz);

	//set pov
	environment->SetIntField(objJoystick, fidHasPOV, haspov);
}

/**
 * Caches the field ids for quicker access
 */
void CacheFields(jobject obj) {
	/* make certain that we're caching from a global object, and not a local */
	objJoystick = environment->NewGlobalRef(obj);
	clsJoystick = environment->GetObjectClass(objJoystick);

    /* cache fields */
	fidButtonCount	= environment->GetFieldID(clsJoystick, "buttonCount", "I");
	fidHasZAxis		= environment->GetFieldID(clsJoystick, "hasZAxis", "Z");
	fidHasPOV		= environment->GetFieldID(clsJoystick, "hasPOV", "Z");
	fidButtons		= environment->GetFieldID(clsJoystick, "buttons", "[Z");
	fidX			= environment->GetFieldID(clsJoystick, "x", "I");
	fidY			= environment->GetFieldID(clsJoystick, "y", "I");
    fidZ			= environment->GetFieldID(clsJoystick, "z", "I");
	fidPOV			= environment->GetFieldID(clsJoystick, "pov", "I");
}