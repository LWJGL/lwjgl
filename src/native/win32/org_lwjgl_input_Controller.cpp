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
 * Win32 controller handling.
 *
 * @author Brian Matzon <brian@matzon.com>
 * @version $Revision$
 */

#define WIN32_LEAN_AND_MEAN
#include "org_lwjgl_input_Controller.h"
#include <windows.h>
#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0500
#include <dinput.h>

#define AXISMAX 1000                 // Maxmimum range to which we'll gauge the swing
#define AXISMIN -1000                // Minimum range to which we'll gauge the swing

extern HWND hwnd;                   // Handle to window

IDirectInput* lpDI;                 // DI instance
IDirectInputDevice2* lpDIDevice;    // DI Device instance
DIJOYSTATE2 js;                     // State of Controller

int buttoncount = 0;                // Temporary buttoncount
bool hasx;                          // Temporary xaxis check
bool hasrx;                          // Temporary rotational xaxis check
bool hasy;                          // Temporary yaxis check
bool hasry;                          // Temporary rotational yaxis check
bool hasz;                          // Temporary zaxis check
bool hasrz;                          // Temporary rotational zaxis check
bool haspov;                        // Temporary pov check
bool hasslider;                     // Temporary slider check

JNIEnv* environment;                // JNIEnvironment copy

bool create_success;                // bool used to determine successfull creation

// Cached fields of Controller.java
jclass clsController;
jfieldID fidButtonCount;
jfieldID fidHasXAxis;
jfieldID fidHasRXAxis;
jfieldID fidHasYAxis;
jfieldID fidHasRYAxis;
jfieldID fidHasZAxis;
jfieldID fidHasRZAxis;
jfieldID fidHasPOV;
jfieldID fidHasSlider;
jfieldID fidButtons;
jfieldID fidX;
jfieldID fidRX;
jfieldID fidY;
jfieldID fidRY;
jfieldID fidZ;
jfieldID fidRZ;
jfieldID fidPOV;
jfieldID fidSlider;

// Function prototypes (defined in the cpp file, since header file is generic across platforms
void EnumerateCapabilities();
void EnumerateControllers();
BOOL CALLBACK EnumControllerCallback(LPCDIDEVICEINSTANCE pdinst, LPVOID pvRef);
BOOL CALLBACK EnumControllerObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef);
void Shutdown();
void CreateController(LPCDIDEVICEINSTANCE lpddi);
void SetupController();
void InitializeFields();
void CacheFields();
void UpdateFields();
void SetCapabilities();
void PrintError(HRESULT error);

/**
 * Initializes any field ids
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Controller_initIDs(JNIEnv * env, jclass clazz) {
  environment = env;
  clsController = clazz;

  /* Cache fields in Controller */
  CacheFields();
}

/**
 * Called when the Controller instance is to be created
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Controller_nCreate(JNIEnv *env, jclass clazz) {
  // Create the DirectInput object. 
  HRESULT hr;
  hr = DirectInputCreate(GetModuleHandle(NULL), DIRECTINPUT_VERSION, &lpDI, NULL); 
  if (FAILED(hr)) {
#if _DEBUG
    printf("DirectInputCreate failed\n");
#endif
    Shutdown();
    return JNI_FALSE;
  }

  /*  Find all Controllers */
  EnumerateControllers();
  if (!create_success) {
#if _DEBUG
    printf("EnumerateControllers failed\n");
#endif
    Shutdown();
    return JNI_FALSE;
  }

  /* Enumerate capabilities of Controller */
  EnumerateCapabilities();
  if (!create_success) {
#if _DEBUG
    printf("EnumerateCapabilities failed\n");
#endif
    Shutdown();
    return JNI_FALSE;
  }

  if(create_success) {
    /* Do setup of Controller */
    SetupController();
  }

  /* Initialize any fields on the Controller */
  InitializeFields();

  /* Set capabilities */
  SetCapabilities();

  /* Aquire the Controller */
  hr = lpDIDevice->Acquire();
  if(FAILED(hr)) {
#if _DEBUG
    printf("Acquire failed\n");
#endif
    Shutdown();
    return JNI_FALSE;
  }
  return create_success;
}

/*
 * Class:     org_lwjgl_input_Controller
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Controller_nDestroy(JNIEnv *env, jclass clazz) {
  Shutdown();
}

/*
 * Class:     org_lwjgl_input_Controller
 * Method:    nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Controller_nPoll(JNIEnv * env, jclass clazz) {
  HRESULT hRes;

  // poll the Controller to read the current state
  hRes = lpDIDevice->Poll();
  if (FAILED(hRes)) {
#if _DEBUG
    printf("Poll fail\n");
#endif
    return;
  }

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
}

/**
 * Enumerates the capabilities of the Controller attached to the system
 */
void EnumerateCapabilities() {
  HRESULT hr;
  hr = lpDIDevice->EnumObjects(EnumControllerObjectsCallback, NULL, DIDFT_ALL);
  if FAILED(hr) { 
#if _DEBUG
    printf("EnumObjects failed\n");
#endif
    create_success = false;
    return;
  }
  create_success = true;
}

/**
 * Enumerates the Controllers attached to the system
 */
void EnumerateControllers() {
  HRESULT hr;
  hr = lpDI->EnumDevices(DIDEVTYPE_JOYSTICK, EnumControllerCallback, 0, DIEDFL_ATTACHEDONLY);
  if FAILED(hr) { 
#if _DEBUG
    printf("EnumDevices failed\n");
#endif
    create_success = false;
    return;
  } 
  create_success = true;
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
#if _DEBUG
  printf("found %s\n", lpddoi->tszName);
#endif
  if(lpddoi->guidType == GUID_Button) {
    buttoncount++;
  } else if(lpddoi->guidType == GUID_XAxis) {
    hasx = true;
  } else if(lpddoi->guidType == GUID_YAxis) {
	  hasy = true;
  } else if(lpddoi->guidType == GUID_ZAxis){
    hasz = true;
  } else if (lpddoi->guidType == GUID_POV){
    haspov = true;
  } else if (lpddoi->guidType == GUID_Slider){
	  hasslider = true;
  } else if (lpddoi->guidType == GUID_RxAxis) {
    hasrx = true;
  } else if (lpddoi->guidType == GUID_RyAxis) {
    hasry = true;
  } else if (lpddoi->guidType == GUID_RzAxis) {
    hasrz = true;
#if _DEBUG
  } else {
    printf("Unhandled object found: %s\n", lpddoi->tszName);
#endif
  }
  return DIENUM_CONTINUE;
}

/**
 * Creates the specified device as a Controller
 */
void CreateController(LPCDIDEVICEINSTANCE lpddi) {
  HRESULT hr;
  hr = lpDI->CreateDevice(lpddi->guidInstance, (LPDIRECTINPUTDEVICE*) &lpDIDevice, NULL);
  if FAILED(hr) {	
#if _DEBUG
    printf("CreateDevice failed\n");
#endif
    create_success = false;
    return;
  }
  create_success = true;
}

/**
 * Sets up the Controller properties
 */ 
void SetupController() {
  // set Controller data format
  if(lpDIDevice->SetDataFormat(&c_dfDIJoystick2) != DI_OK) {
#if _DEBUG
    printf("SetDataFormat failed\n");
#endif
    create_success = false;
    return;
  }

  // set the cooperative level
  if(lpDIDevice->SetCooperativeLevel(hwnd, DISCL_NONEXCLUSIVE | DISCL_BACKGROUND) != DI_OK) {
#if _DEBUG
    printf("SetCooperativeLevel failed\n");
#endif
    create_success = false;
    return;
  }
  
  // set range to (-1000 ... +1000)
  // This lets us test against 0 to see which way the stick is pointed.
  DIPROPRANGE diprg;
  diprg.diph.dwSize       = sizeof(diprg);
  diprg.diph.dwHeaderSize = sizeof(diprg.diph);
  diprg.diph.dwHow        = DIPH_BYOFFSET;
  diprg.lMin              = AXISMIN;
  diprg.lMax              = AXISMAX;

  // set X-axis
  if(hasx) {
    diprg.diph.dwObj        = DIJOFS_X;
	  if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
#if _DEBUG
    printf("SetProperty(DIJOFS_X) failed\n");
#endif
		  create_success = false;
		  return;
	  }
  }

  // set RX-axis
  if(hasrx) {
    diprg.diph.dwObj        = DIJOFS_RX;
	  if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
#if _DEBUG
    printf("SetProperty(DIJOFS_RX) failed\n");
#endif
		  create_success = false;
		  return;
	  }
  }


  // set Y-axis
  if(hasy) {
	  diprg.diph.dwObj        = DIJOFS_Y;
	  if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
#if _DEBUG
    printf("SetProperty(DIJOFS_Y) failed\n");
#endif
		  create_success = false;
		  return;
	  }
  }

  // set RY-axis
  if(hasry) {
	  diprg.diph.dwObj        = DIJOFS_RY;
	  if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
#if _DEBUG
    printf("SetProperty(DIJOFS_RY) failed\n");
#endif
		  create_success = false;
		  return;
	  }
  }

  // set Z-axis
  if(hasz) {
	  diprg.diph.dwObj        = DIJOFS_Z;
	  if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
#if _DEBUG
    printf("SetProperty(DIJOFS_Z) failed\n");
#endif
		  create_success = false;
		  return;
	  }
  }


  // set RZ-axis
  if(hasrz) {
	  diprg.diph.dwObj        = DIJOFS_RZ;
	  if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
#if _DEBUG
    printf("SetProperty(DIJOFS_RZ) failed\n");
#endif
		  create_success = false;
		  return;
	  }
  }

  //
  // Lastly slider
  // using z axis since we're running dx 5
  //
  if(hasslider) {
    diprg.diph.dwObj        = DIJOFS_Z;
	  if(lpDIDevice->SetProperty(DIPROP_RANGE, &diprg.diph) != DI_OK) {
#if _DEBUG
    printf("SetProperty(DIJOFS_Z(SLIDER)) failed\n");
#endif
		  create_success = false;
		  return;
	  }
  }
  create_success = true;
}

/**
 * Sets the fields on the Controller
 */
void InitializeFields() {
  //set buttons array
  jbooleanArray buttonsArray = environment->NewBooleanArray(buttoncount);
  environment->SetStaticObjectField(clsController, fidButtons, buttonsArray);
}

/**
 * Updates the fields on the Controller
 */
void UpdateFields() {
  HRESULT                 hRes; 

  // get data from the Controller 
  hRes = lpDIDevice->GetDeviceState(sizeof(DIJOYSTATE2), &js); 

  if (hRes != DI_OK) { 
    // did the read fail because we lost input for some reason? 
    // if so, then attempt to reacquire. 
    if(hRes == DIERR_INPUTLOST) {
      lpDIDevice->Acquire();
#if _DEBUG
      printf("DIERR_INPUTLOST, reaquiring input : create_success=%d\n", create_success);
#endif
    }
    return;
  }

  //axis's
  if(hasx) {
	  environment->SetStaticIntField(clsController, fidX, js.lX);
  }

  if(hasy) {
	  environment->SetStaticIntField(clsController, fidY, js.lY);
  }

  if(hasz) {
    environment->SetStaticIntField(clsController, fidZ, js.lZ);
  }

  //rotational axis
  if(hasrx) {
	  environment->SetStaticIntField(clsController, fidRX, js.lRx);
  }

  if(hasry) {
	  environment->SetStaticIntField(clsController, fidRY, js.lRy);
  }

  if(hasrz) {
    environment->SetStaticIntField(clsController, fidRZ, js.lRz);
  }

  //buttons
  jbooleanArray buttonsArray = (jbooleanArray) environment->GetStaticObjectField(clsController, fidButtons);
  BYTE * buttons = (BYTE *) environment->GetPrimitiveArrayCritical(buttonsArray, NULL);
  memcpy(buttons, js.rgbButtons, buttoncount);
  environment->ReleasePrimitiveArrayCritical(buttonsArray, buttons, 0);

  //pov
  if(haspov) {
    environment->SetStaticIntField(clsController, fidPOV, js.rgdwPOV[0]);
  }

  //slider
  if(hasslider) {
    environment->SetStaticIntField(clsController, fidSlider, js.lZ);
  }
}

/**
 * Sets the capabilities of the Controller
 */
void SetCapabilities() {
  //set buttoncount
  environment->SetStaticIntField(clsController, fidButtonCount, buttoncount);

  //set axis
  environment->SetStaticIntField(clsController, fidHasXAxis, hasx);
  environment->SetStaticIntField(clsController, fidHasYAxis, hasy);
  environment->SetStaticIntField(clsController, fidHasZAxis, hasz);

  //set rotational axis
  environment->SetStaticIntField(clsController, fidHasRXAxis, hasrx);
  environment->SetStaticIntField(clsController, fidHasRYAxis, hasry);
  environment->SetStaticIntField(clsController, fidHasRZAxis, hasrz);

  //set pov
  environment->SetStaticIntField(clsController, fidHasPOV, haspov);

  //set slider
  environment->SetStaticIntField(clsController, fidHasSlider, hasslider);
}

/**
 * Caches the field ids for quicker access
 */
void CacheFields() {
  fidButtonCount  = environment->GetStaticFieldID(clsController, "buttonCount", "I");
  fidHasXAxis     = environment->GetStaticFieldID(clsController, "hasXAxis", "Z");
  fidHasRXAxis     = environment->GetStaticFieldID(clsController, "hasRXAxis", "Z");
  fidHasYAxis     = environment->GetStaticFieldID(clsController, "hasYAxis", "Z");
  fidHasRYAxis     = environment->GetStaticFieldID(clsController, "hasRYAxis", "Z");
  fidHasZAxis     = environment->GetStaticFieldID(clsController, "hasZAxis", "Z");
  fidHasRZAxis     = environment->GetStaticFieldID(clsController, "hasRZAxis", "Z");
  fidHasPOV       = environment->GetStaticFieldID(clsController, "hasPOV", "Z");
  fidHasSlider    = environment->GetStaticFieldID(clsController, "hasSlider", "Z");
  fidButtons      = environment->GetStaticFieldID(clsController, "buttons", "[Z");
  fidX            = environment->GetStaticFieldID(clsController, "x", "I");
  fidRX            = environment->GetStaticFieldID(clsController, "rx", "I");
  fidY            = environment->GetStaticFieldID(clsController, "y", "I");
  fidRY            = environment->GetStaticFieldID(clsController, "ry", "I");
  fidZ            = environment->GetStaticFieldID(clsController, "z", "I");
  fidRZ            = environment->GetStaticFieldID(clsController, "rz", "I");
  fidPOV          = environment->GetStaticFieldID(clsController, "pov", "I");
  fidSlider       = environment->GetStaticFieldID(clsController, "slider", "I");
}