/*
 * Copyright (c) 2002 Lightweight Java Game Library Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *	 notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *	 notice, this list of conditions and the following disclaimer in the
 *	 documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
 *	 its contributors may be used to endorse or promote products derived 
 *	 from this software without specific prior written permission.
 * * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
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
 * Win32 controller handling.
 *
 * @author Brian Matzon <brian@matzon.com>
 * @version $Revision$
 */


#define WIN32_LEAN_AND_MEAN
#include "org_lwjgl_input_Controller.h"
#include <windows.h>
#undef	DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0500
#include <dinput.h>
#include "Window.h"
#include "common_tools.h"

#define CONTROLLER_AXISMAX 1000			 // Maxmimum range to which we'll gauge the swing
#define CONTROLLER_AXISMIN -1000			// Minimum range to which we'll gauge the swing

extern HINSTANCE dll_handle;
extern HWND hwnd;

static IDirectInput* cDI;									 // DI instance
static IDirectInputDevice2* cDIDevice;			 // DI Device instance
static DIJOYSTATE2 cJS;											// State of Controller

static int cButtoncount = 0;								 // Temporary buttoncount
static bool cHasx;													 // Temporary xaxis check
static bool cHasrx;													// Temporary rotational xaxis check
static bool cHasy;													 // Temporary yaxis check
static bool cHasry;													// Temporary rotational yaxis check
static bool cHasz;													 // Temporary zaxis check
static bool cHasrz;													// Temporary rotational zaxis check
static bool cHaspov;												 // Temporary pov check
static bool cHasslider;											// Temporary slider check

static bool cCreate_success;								 // bool used to determine successfull creation
static bool cFirstTimeInitialization = true; // boolean to determine first time initialization

// Cached fields of Controller.java
static jfieldID fidCButtonCount;
static jfieldID fidCHasXAxis;
static jfieldID fidCHasRXAxis;
static jfieldID fidCHasYAxis;
static jfieldID fidCHasRYAxis;
static jfieldID fidCHasZAxis;
static jfieldID fidCHasRZAxis;
static jfieldID fidCHasPOV;
static jfieldID fidCHasSlider;
static jfieldID fidCButtons;
static jfieldID fidCX;
static jfieldID fidCRX;
static jfieldID fidCY;
static jfieldID fidCRY;
static jfieldID fidCZ;
static jfieldID fidCRZ;
static jfieldID fidCPOV;
static jfieldID fidCSlider;

// Function prototypes (defined in the cpp file, since header file is generic across platforms
void EnumerateControllerCapabilities();
void EnumerateControllers();
BOOL CALLBACK EnumControllerCallback(LPCDIDEVICEINSTANCE pdinst, LPVOID pvRef);
BOOL CALLBACK EnumControllerObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef);
void ShutdownController();
void CreateController(LPCDIDEVICEINSTANCE lpddi);
void SetupController();
void InitializeControllerFields(JNIEnv *env, jclass clsController);
void CacheControllerFields(JNIEnv *env, jclass clsController);
void UpdateControllerFields(JNIEnv *env, jclass clsController);
void SetControllerCapabilities(JNIEnv *env, jclass clsController);

/**
 * Initializes any field ids
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Controller_initIDs(JNIEnv * env, jclass clazz) {
	/* Cache fields in Controller */
	CacheControllerFields(env, clazz);
}

/**
 * Called when the Controller instance is to be created
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Controller_nCreate(JNIEnv *env, jclass clazz) {
	// Create the DirectInput object. 
	HRESULT hr;
	hr = DirectInputCreate(dll_handle, DIRECTINPUT_VERSION, &cDI, NULL); 
	if (FAILED(hr)) {
		printfDebug(org_lwjgl_Sys_DEBUG, "DirectInputCreate failed\n");
		ShutdownController();
		return;
	}

	/*	Find all Controllers */
	EnumerateControllers();
	if (!cCreate_success) {
		throwException(env, "Failed to enumerate.");
		ShutdownController();
		return;
	}

	/* check that we got at least 1 controller */
	if (cDIDevice == NULL) {
		throwException(env, "No devices found.");
		ShutdownController();
		return;
	}

	//check for first time initialization - need to detect capabilities
	if (cFirstTimeInitialization) {
		cFirstTimeInitialization = false;

		/* Enumerate capabilities of Controller */
		EnumerateControllerCapabilities();
		if (!cCreate_success) {
			throwException(env, "Falied to enumerate capabilities.");
			ShutdownController();
			return;
		}

		/* Do setup of Controller */
		SetupController();

		/* Initialize any fields on the Controller */
		InitializeControllerFields(env, clazz);

		/* Set capabilities */
		SetControllerCapabilities(env, clazz);
	} else {
		if(cCreate_success) {
			/* Do setup of Controller */
			SetupController();
			
			/* Initialize any fields on the Controller */
			InitializeControllerFields(env, clazz);
		}
	}

	/* Aquire the Controller */
	hr = cDIDevice->Acquire();
	if(FAILED(hr)) {
		throwException(env, "Acquire failed");
		ShutdownController();
		return;
	}
}

/*
 * Class:		 org_lwjgl_input_Controller
 * Method:		nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Controller_nDestroy(JNIEnv *env, jclass clazz) {
	ShutdownController();
}

/*
 * Class:		 org_lwjgl_input_Controller
 * Method:		nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Controller_nPoll(JNIEnv * env, jclass clazz) {
	HRESULT hRes;

	// poll the Controller to read the current state
	hRes = cDIDevice->Poll();
	if (FAILED(hRes)) {
		printfDebug(org_lwjgl_Sys_DEBUG, "Poll fail\n");

		//check if we need to reaquire
		if(hRes == DIERR_INPUTLOST || hRes == DIERR_NOTACQUIRED) {
			cDIDevice->Acquire();
			printfDebug(org_lwjgl_Sys_DEBUG, "DIERR_INPUTLOST, reaquiring input : cCreate_success=%d\n", cCreate_success);
			}
		return;
	}

	UpdateControllerFields(env, clazz);
}

/**
 * Shutdown DI
 */
static void ShutdownController() {
	// release device
	if (cDIDevice != NULL) {
		cDIDevice->Unacquire();
		cDIDevice->Release();
		cDIDevice = NULL;
	}
}

/**
 * Enumerates the capabilities of the Controller attached to the system
 */
static void EnumerateControllerCapabilities() {
	HRESULT hr;
	hr = cDIDevice->EnumObjects(EnumControllerObjectsCallback, NULL, DIDFT_ALL);
	if FAILED(hr) { 
		printfDebug(org_lwjgl_Sys_DEBUG, "EnumObjects failed\n");
		cCreate_success = false;
		return;
	}
	cCreate_success = true;
}

/**
 * Enumerates the Controllers attached to the system
 */
static void EnumerateControllers() {
	HRESULT hr;
	hr = cDI->EnumDevices(DIDEVTYPE_JOYSTICK, EnumControllerCallback, 0, DIEDFL_ATTACHEDONLY);
	if FAILED(hr) { 
		printfDebug(org_lwjgl_Sys_DEBUG, "EnumDevices failed\n");
		cCreate_success = false;
		return;
	} 
	cCreate_success = true;
}

/**
 * Callback from EnumDevices. Called for each Controller attached to the system
 */
BOOL CALLBACK EnumControllerCallback(LPCDIDEVICEINSTANCE pdinst, LPVOID pvRef) {
	/* Add the Controller */
	CreateController(pdinst);

	/* just stop after 1st Controller */
	return DIENUM_STOP;
}

/**
 * Callback from EnumObjects. Called for each "object" on the Controller.
 */
BOOL CALLBACK EnumControllerObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
	printfDebug(org_lwjgl_Sys_DEBUG, "found %s\n", lpddoi->tszName);
	if(lpddoi->guidType == GUID_Button) {
		cButtoncount++;
	} else if(lpddoi->guidType == GUID_XAxis) {
		cHasx = true;
	} else if(lpddoi->guidType == GUID_YAxis) {
		cHasy = true;
	} else if(lpddoi->guidType == GUID_ZAxis){
		cHasz = true;
	} else if (lpddoi->guidType == GUID_POV){
		cHaspov = true;
	} else if (lpddoi->guidType == GUID_Slider){
		cHasslider = true;
	} else if (lpddoi->guidType == GUID_RxAxis) {
		cHasrx = true;
	} else if (lpddoi->guidType == GUID_RyAxis) {
		cHasry = true;
	} else if (lpddoi->guidType == GUID_RzAxis) {
		cHasrz = true;
	} else {
		printfDebug(org_lwjgl_Sys_DEBUG, "Unhandled object found: %s\n", lpddoi->tszName);
	}
	return DIENUM_CONTINUE;
}

/**
 * Creates the specified device as a Controller
 */
static void CreateController(LPCDIDEVICEINSTANCE lpddi) {
	HRESULT hr;
	hr = cDI->CreateDevice(lpddi->guidInstance, (LPDIRECTINPUTDEVICE*) &cDIDevice, NULL);
	if FAILED(hr) {	
		printfDebug(org_lwjgl_Sys_DEBUG, "CreateDevice failed\n");
		cCreate_success = false;
		return;
	}
	cCreate_success = true;
}

/**
 * Sets up the Controller properties
 */ 
static void SetupController() {
	// set Controller data format
	if(cDIDevice->SetDataFormat(&c_dfDIJoystick2) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetDataFormat failed\n");
		cCreate_success = false;
		return;
	}

	// set the cooperative level
	if(cDIDevice->SetCooperativeLevel(hwnd, DISCL_EXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetCooperativeLevel failed\n");
		cCreate_success = false;
		return;
	}
	
	// set range to (-1000 ... +1000)
	// This lets us test against 0 to see which way the stick is pointed.
	DIPROPRANGE diprg;
	diprg.diph.dwSize			 = sizeof(diprg);
	diprg.diph.dwHeaderSize = sizeof(diprg.diph);
	diprg.diph.dwHow				= DIPH_BYOFFSET;
	diprg.lMin							= CONTROLLER_AXISMIN;
	diprg.lMax							= CONTROLLER_AXISMAX;

	// set X-axis
	if(cHasx) {
		diprg.diph.dwObj				= DIJOFS_X;
		if(cDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetProperty(DIJOFS_X) failed\n");
			cCreate_success = false;
			return;
		}
	}

	// set RX-axis
	if(cHasrx) {
		diprg.diph.dwObj				= DIJOFS_RX;
		if(cDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetProperty(DIJOFS_RX) failed\n");
			cCreate_success = false;
			return;
		}
	}


	// set Y-axis
	if(cHasy) {
		diprg.diph.dwObj				= DIJOFS_Y;
		if(cDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetProperty(DIJOFS_Y) failed\n");
			cCreate_success = false;
			return;
		}
	}

	// set RY-axis
	if(cHasry) {
		diprg.diph.dwObj				= DIJOFS_RY;
		if(cDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetProperty(DIJOFS_RY) failed\n");
			cCreate_success = false;
			return;
		}
	}

	// set Z-axis
	if(cHasz) {
		diprg.diph.dwObj				= DIJOFS_Z;
		if(cDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetProperty(DIJOFS_Z) failed\n");
			cCreate_success = false;
			return;
		}
	}


	// set RZ-axis
	if(cHasrz) {
		diprg.diph.dwObj				= DIJOFS_RZ;
		if(cDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetProperty(DIJOFS_RZ) failed\n");
			cCreate_success = false;
			return;
		}
	}

	//
	// Lastly slider
	// using z axis since we're running dx 5
	//
	if(cHasslider) {
		diprg.diph.dwObj				= DIJOFS_Z;
		if(cDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetProperty(DIJOFS_Z(SLIDER)) failed\n");
			cCreate_success = false;
			return;
		}
	}
	cCreate_success = true;
}

/**
 * Sets the fields on the Controller
 */
static void InitializeControllerFields(JNIEnv *env, jclass clsController) {
	//create buttons array
	jbooleanArray cButtonsArray = env->NewBooleanArray(cButtoncount);
	
	//set buttons array	
	env->SetStaticObjectField(clsController, fidCButtons, cButtonsArray);
}

/**
 * Updates the fields on the Controller
 */
static void UpdateControllerFields(JNIEnv *env, jclass clsController) {
	HRESULT hRes; 

	// get data from the Controller 
	hRes = cDIDevice->GetDeviceState(sizeof(DIJOYSTATE2), &cJS); 

	if (hRes != DI_OK) { 
		// did the read fail because we lost input for some reason? 
		// if so, then attempt to reacquire. 
		if(hRes == DIERR_INPUTLOST || hRes == DIERR_NOTACQUIRED) {
			cDIDevice->Acquire();
			printfDebug(org_lwjgl_Sys_DEBUG, "DIERR_INPUTLOST, reaquiring input : cCreate_success=%d\n", cCreate_success);
		}
		printfDebug(org_lwjgl_Sys_DEBUG, "Error getting controller state: %d\n", hRes);
		return;
	}

	//axis's
	if(cHasx) {
		env->SetStaticIntField(clsController, fidCX, cJS.lX);
	}

	if(cHasy) {
		env->SetStaticIntField(clsController, fidCY, cJS.lY);
	}

	if(cHasz) {
		env->SetStaticIntField(clsController, fidCZ, cJS.lZ);
	}

	//rotational axis
	if(cHasrx) {
		env->SetStaticIntField(clsController, fidCRX, cJS.lRx);
	}

	if(cHasry) {
		env->SetStaticIntField(clsController, fidCRY, cJS.lRy);
	}

	if(cHasrz) {
		env->SetStaticIntField(clsController, fidCRZ, cJS.lRz);
	}

	//buttons
	for (int i = 0; i < cButtoncount; i++) {
		if (cJS.rgbButtons[i] != 0) {
			cJS.rgbButtons[i] = 1;
		} else {
			cJS.rgbButtons[i] = 0;
		}
	}
	jbyteArray buttonsArray = (jbyteArray) env->GetStaticObjectField(clsController, fidCButtons);
	env->SetByteArrayRegion(buttonsArray, 0, cButtoncount, (jbyte *)cJS.rgbButtons);	

	//pov
	if(cHaspov) {
		env->SetStaticIntField(clsController, fidCPOV, cJS.rgdwPOV[0]);
	}

	//slider
	if(cHasslider) {
		env->SetStaticIntField(clsController, fidCSlider, cJS.lZ);
	}
}

/**
 * Sets the capabilities of the Controller
 */
static void SetControllerCapabilities(JNIEnv *env, jclass clsController) {
	//set buttoncount
	env->SetStaticIntField(clsController, fidCButtonCount, cButtoncount);

	//set axis
	env->SetStaticBooleanField(clsController, fidCHasXAxis, cHasx);
	env->SetStaticBooleanField(clsController, fidCHasYAxis, cHasy);
	env->SetStaticBooleanField(clsController, fidCHasZAxis, cHasz);

	//set rotational axis
	env->SetStaticBooleanField(clsController, fidCHasRXAxis, cHasrx);
	env->SetStaticBooleanField(clsController, fidCHasRYAxis, cHasry);
	env->SetStaticBooleanField(clsController, fidCHasRZAxis, cHasrz);

	//set pov
	env->SetStaticBooleanField(clsController, fidCHasPOV, cHaspov);

	//set slider
	env->SetStaticBooleanField(clsController, fidCHasSlider, cHasslider);
}

/**
 * Caches the field ids for quicker access
 */
static void CacheControllerFields(JNIEnv *env, jclass clsController) {
	fidCButtonCount	= env->GetStaticFieldID(clsController, "buttonCount", "I");
	fidCHasXAxis	= env->GetStaticFieldID(clsController, "hasXAxis", "Z");
	fidCHasRXAxis	= env->GetStaticFieldID(clsController, "hasRXAxis", "Z");
	fidCHasYAxis	= env->GetStaticFieldID(clsController, "hasYAxis", "Z");
	fidCHasRYAxis	= env->GetStaticFieldID(clsController, "hasRYAxis", "Z");
	fidCHasZAxis	= env->GetStaticFieldID(clsController, "hasZAxis", "Z");
	fidCHasRZAxis	= env->GetStaticFieldID(clsController, "hasRZAxis", "Z");
	fidCHasPOV	= env->GetStaticFieldID(clsController, "hasPOV", "Z");
	fidCHasSlider	= env->GetStaticFieldID(clsController, "hasSlider", "Z");
	fidCButtons	= env->GetStaticFieldID(clsController, "buttons", "[Z");
	fidCX		= env->GetStaticFieldID(clsController, "x", "I");
	fidCRX		= env->GetStaticFieldID(clsController, "rx", "I");
	fidCY		= env->GetStaticFieldID(clsController, "y", "I");
	fidCRY		= env->GetStaticFieldID(clsController, "ry", "I");
	fidCZ		= env->GetStaticFieldID(clsController, "z", "I");
	fidCRZ		= env->GetStaticFieldID(clsController, "rz", "I");
	fidCPOV		= env->GetStaticFieldID(clsController, "pov", "I");
	fidCSlider	= env->GetStaticFieldID(clsController, "slider", "I");
}
