/*
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * Win32 mouse handling.
 *
 * @author Brian Matzon <brian@matzon.com>
 * @version $Revision$
 */

#define WIN32_LEAN_AND_MEAN
#include "org_lwjgl_input_Mouse.h"
#include <windows.h>
#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0300
#include <dinput.h>

extern HINSTANCE dll_handle;

extern HWND hwnd;                     // Handle to window

extern LPDIRECTINPUT lpdi;            // DI instance
LPDIRECTINPUTDEVICE mDIDevice;        // DI Device instance
DIMOUSESTATE diMouseState;            // State of Mouse

int mButtoncount = 0;                 // Temporary buttoncount
bool mHaswheel;                       // Temporary wheel check

JNIEnv* mEnvironment;                 // JNIEnvironment copy

bool mCreate_success;                 // bool used to determine successfull creation
bool mFirstTimeInitialization = true; // boolean to determine first time initialization

// Cached fields of Mouse.java
jclass clsMouse;
jfieldID fidMButtonCount;
jfieldID fidMButtons;
jfieldID fidMDX;
jfieldID fidMDY;
jfieldID fidMDWheel;
jfieldID fidMHasWheel;

// Function prototypes (defined in the cpp file, since header file is generic across platforms
void EnumerateMouseCapabilities();
BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef);
void ShutdownMouse();
void CreateMouse();
void SetupMouse();
void InitializeMouseFields();
void CacheMouseFields();
void UpdateMouseFields();
void SetMouseCapabilities();

/**
 * Initializes any field ids
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs(JNIEnv * env, jclass clazz) {
  mEnvironment = env;
  clsMouse = clazz;

  /* Cache fields in Mouse */
  CacheMouseFields();
}

/**
 * Called when the Mouse instance is to be created
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nCreate(JNIEnv *env, jclass clazz) {
  HRESULT hr;

  /* skip enumeration, since we only want system mouse */
  CreateMouse();

  //check for first time initialization - need to detect capabilities
  if (mFirstTimeInitialization) {
    mFirstTimeInitialization = false;

    /* Enumerate capabilities of Mouse */
    EnumerateMouseCapabilities();
    if (!mCreate_success) {
  #if _DEBUG
      printf("EnumerateMouseCapabilities failed\n");
  #endif
      ShutdownMouse();
      return JNI_FALSE;
    }

	/* Do setup of Mouse */
	SetupMouse();

    /* Initialize any fields on the Mouse */
    InitializeMouseFields();

    /* Set capabilities */
    SetMouseCapabilities();
  } else {
    if(mCreate_success) {
      /* Do setup of Mouse */
      SetupMouse();
      
      /* Initialize any fields on the Mouse */
      InitializeMouseFields();      
    }
  }

  /* Aquire the Mouse */
  hr = mDIDevice->Acquire();
  if(FAILED(hr)) {
#if _DEBUG
    printf("Failed to acquire mouse\n");
#endif
  }

  return mCreate_success ? JNI_TRUE : JNI_FALSE;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy(JNIEnv *env, jclass clazz) {
  ShutdownMouse();
}

/*
 * Class:     org_lwjgl_input_Controller
 * Method:    nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz) {
  mDIDevice->Acquire();
  UpdateMouseFields();
}

/**
 * Shutdown DI
 */
void ShutdownMouse() {
  // release device
  if (mDIDevice != NULL) {
    mDIDevice->Unacquire();
    mDIDevice->Release();
	mDIDevice = NULL;
  }
}
/**
 * Enumerates the capabilities of the Mouse attached to the system
 */
void EnumerateMouseCapabilities() {
  HRESULT hr;
  hr = mDIDevice->EnumObjects(EnumMouseObjectsCallback, NULL, DIDFT_ALL);
  if FAILED(hr) { 
#if _DEBUG
    printf("EnumObjects failed\n");
#endif
    mCreate_success = false;
    return;
  }
  mCreate_success = true;
}

/**
 * Callback from EnumObjects. Called for each "object" on the Mouse.
 */
BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
#if _DEBUG
  printf("found %s\n", lpddoi->tszName);
#endif
  if(lpddoi->guidType == GUID_Button) {
    mButtoncount++;
  } else if(lpddoi->guidType == GUID_XAxis) {
  } else if(lpddoi->guidType == GUID_YAxis) {
  } else if(lpddoi->guidType == GUID_ZAxis) {
    mHaswheel = true;
#if _DEBUG
  } else {
    printf("Unhandled object found: %s\n", lpddoi->tszName);
#endif
  }
  return DIENUM_CONTINUE;
}

/**
 * Creates the specified device as a Mouse
 */
void CreateMouse() {
  HRESULT hr;
  hr = lpdi->CreateDevice(GUID_SysMouse, &mDIDevice, NULL);
  if FAILED(hr) {	
#if _DEBUG
    printf("CreateDevice failed\n");
#endif
    mCreate_success = false;
    return;
  }
  mCreate_success = true;
}

/**
 * Sets up the Mouse properties
 */ 
void SetupMouse() {
  // set Mouse data format
  if(mDIDevice->SetDataFormat(&c_dfDIMouse) != DI_OK) {
#if _DEBUG
    printf("SetDataFormat failed\n");
#endif
    mCreate_success = false;
    return;
  }

  // set the cooperative level
  if(mDIDevice->SetCooperativeLevel(hwnd, DISCL_EXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
#if _DEBUG
    printf("SetCooperativeLevel failed\n");
#endif
    mCreate_success = false;
    return;
  }
  mCreate_success = true;
}

/**
 * Sets the fields on the Mouse
 */
void InitializeMouseFields() {
  //create buttons array
  jbooleanArray mButtonsArray = mEnvironment->NewBooleanArray(mButtoncount);
  
  //set buttons array  
  mEnvironment->SetStaticObjectField(clsMouse, fidMButtons, (jbooleanArray) mButtonsReference);
}

/**
 * Updates the fields on the Mouse
 */
void UpdateMouseFields() {
  HRESULT                 hRes; 

  // get data from the Mouse 
  hRes = mDIDevice->GetDeviceState(sizeof(DIMOUSESTATE), &diMouseState);
  if (hRes != DI_OK) { 
    // Don't allow the mouse to drift when failed
    diMouseState.lX = 0;
    diMouseState.lY = 0;
    diMouseState.lZ = 0;
    // did the read fail because we lost input for some reason? 
    // if so, then attempt to reacquire. 
    if(hRes == DIERR_INPUTLOST || hRes == DIERR_NOTACQUIRED) {
      mDIDevice->Acquire();
#if _DEBUG
      printf("DIERR_INPUTLOST, reaquiring input : mCreate_success=%d\n", mCreate_success);
#endif
    } else {
#if _DEBUG
      printf("Error getting mouse state: %d\n", hRes);
#endif
    }
  }

  mEnvironment->SetStaticIntField(clsMouse, fidMDX, (jint) diMouseState.lX);
	mEnvironment->SetStaticIntField(clsMouse, fidMDY, (jint) diMouseState.lY);
	mEnvironment->SetStaticIntField(clsMouse, fidMDWheel, (jint) diMouseState.lZ);
	
  for (int i = 0; i < mButtoncount; i++) {
    if (diMouseState.rgbButtons[i] != 0) {
      diMouseState.rgbButtons[i] = JNI_TRUE;
    } else {
				diMouseState.rgbButtons[i] = JNI_FALSE;
    }
  }
  mEnvironment->SetBooleanArrayRegion((jbooleanArray) mButtonsReference, 0, mButtoncount, diMouseState.rgbButtons);
}

/**
 * Sets the capabilities of the Mouse
 */
void SetMouseCapabilities() {
  //set buttoncount
  mEnvironment->SetStaticIntField(clsMouse, fidMButtonCount, mButtoncount);

  //set wheel
  mEnvironment->SetStaticBooleanField(clsMouse, fidMHasWheel, mHaswheel);
}

/**
 * Caches the field ids for quicker access
 */
void CacheMouseFields() {
  fidMButtonCount  = mEnvironment->GetStaticFieldID(clsMouse, "buttonCount", "I");
  fidMHasWheel     = mEnvironment->GetStaticFieldID(clsMouse, "hasWheel", "Z");
  fidMButtons      = mEnvironment->GetStaticFieldID(clsMouse, "buttons", "[Z");
  fidMDX           = mEnvironment->GetStaticFieldID(clsMouse, "dx", "I");
  fidMDY           = mEnvironment->GetStaticFieldID(clsMouse, "dy", "I");
  fidMDWheel       = mEnvironment->GetStaticFieldID(clsMouse, "dwheel", "I");
}