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
 * Win32 mouse handling.
 *
 * @author Brian Matzon <brian@matzon.com>
 * @version $Revision$
 */

#define WIN32_LEAN_AND_MEAN
#include "org_lwjgl_input_Mouse.h"
#include <windows.h>
#undef	DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0300
#include <dinput.h>
#include "Window.h"
#include "common_tools.h"

extern HINSTANCE	dll_handle;							        // Handle to the LWJGL dll
static LPDIRECTINPUT		lpdi = NULL;						          // DirectInput
static LPDIRECTINPUTDEVICE mDIDevice;				// DI Device instance
static int mButtoncount = 0;								 // Temporary buttoncount
static bool mHaswheel;											 // Temporary wheel check

static bool mCreate_success;								 // bool used to determine successfull creation
static bool mFirstTimeInitialization = true; // boolean to determine first time initialization

static POINT cursorPos;
static bool mouse_grabbed;
static int mouseMask = DISCL_NONEXCLUSIVE | DISCL_FOREGROUND;

// Function prototypes (defined in the cpp file, since header file is generic across platforms
void EnumerateMouseCapabilities();
BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef);
void ShutdownMouse();
void CreateMouse();
void SetupMouse();
void InitializeMouseFields();
void UpdateMouseFields(JNIEnv *env, jclass clsMouse, jobject coord_buffer_obj, jobject button_buffer_obj);

static void resetCursorPos(void) {
	/* Reset cursor position to middle of the window */
	RECT clientRect;
	GetClientRect(getCurrentHWND(), &clientRect);
	cursorPos.x = (clientRect.left + clientRect.right)/2;
	cursorPos.y = clientRect.bottom - 1 - (clientRect.bottom - clientRect.top)/2;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nHasWheel(JNIEnv *, jclass) {
	return mHaswheel;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetButtonCount(JNIEnv *, jclass) {
	return mButtoncount;
}

/**
 * Called when the Mouse instance is to be created
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nCreate(JNIEnv *env, jclass clazz) {
	HRESULT hr;

	// Create input
	HRESULT ret = DirectInputCreate(dll_handle, DIRECTINPUT_VERSION, &lpdi, NULL);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION) {
		throwException(env, "Failed to create DirectInput");
		return;
	}

	/* skip enumeration, since we only want system mouse */
	CreateMouse();

	//check for first time initialization - need to detect capabilities
	if (mFirstTimeInitialization) {
		mFirstTimeInitialization = false;
		/* Enumerate capabilities of Mouse */
		EnumerateMouseCapabilities();
		if (!mCreate_success) {
			throwException(env, "Failed to enumerate.");
			ShutdownMouse();
			return;
		}
		/* Do setup of Mouse */
		SetupMouse();
	} else {
		if(mCreate_success) {
			/* Do setup of Mouse */
			SetupMouse();	 
		}
	}
	/* Aquire the Mouse */
	hr = mDIDevice->Acquire();
	if(FAILED(hr)) {
		printfDebug("Failed to acquire mouse\n");
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer(JNIEnv * env, jclass clazz) {
}

static unsigned char mapButton(DWORD button_id) {
	switch (button_id) {
	case DIMOFS_BUTTON0: return 0;
	case DIMOFS_BUTTON1: return 1;
	case DIMOFS_BUTTON2: return 2;
	case DIMOFS_BUTTON3: return 3;
/*	case DIMOFS_BUTTON4: return 4;
	case DIMOFS_BUTTON5: return 5;
	case DIMOFS_BUTTON6: return 6;
	case DIMOFS_BUTTON7: return 7;*/
	default: return mButtoncount;
	}
}

static int bufferButtons(int num_di_events, DIDEVICEOBJECTDATA *di_buffer, unsigned char *buffer, int buffer_size) {
	int buffer_index = 0;
	for (int i = 0; i < num_di_events; i++) {
		unsigned char button = mapButton(di_buffer[i].dwOfs);
		if (button >= 0 && button < mButtoncount) {
			unsigned char state = (unsigned char)di_buffer[i].dwData & 0x80;
			if (state != 0)
				state = 1;
			if (buffer_index == buffer_size)
				break;
			buffer[buffer_index++] = button;
			buffer[buffer_index++] = state;
		}
	}
	return buffer_index/2;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nRead
	(JNIEnv * env, jclass clazz, jobject buffer_obj, jint buffer_position)
{
	
	static DIDEVICEOBJECTDATA rgdod[EVENT_BUFFER_SIZE];
	DWORD num_di_events = EVENT_BUFFER_SIZE;

	HRESULT ret;

	ret = mDIDevice->Acquire();
	if (ret != DI_OK && ret != S_FALSE)
		return 0;

	ret = mDIDevice->GetDeviceData( 
		sizeof(DIDEVICEOBJECTDATA), 
		rgdod, 
		&num_di_events,
		0); 

	if (ret == DI_OK) {
		unsigned char *buffer_ptr = buffer_position + (unsigned char*)env->GetDirectBufferAddress(buffer_obj);
		int buffer_size = (int)env->GetDirectBufferCapacity(buffer_obj) - buffer_position;
		return bufferButtons(num_di_events, rgdod, buffer_ptr, buffer_size);
	} else if (ret == DI_BUFFEROVERFLOW) { 
		printfDebug("Buffer overflowed\n");
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
	return 0;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNativeCursorCaps
	(JNIEnv *env, jclass clazz)
{
	return org_lwjgl_input_Mouse_CURSOR_ONE_BIT_TRANSPARENCY;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor
	(JNIEnv *env, jclass clazz, jobject handle_buffer)
{
	if (mDIDevice == NULL)
		throwException(env, "null device!");
	if (handle_buffer != NULL) {
		HCURSOR *cursor_handle = (HCURSOR *)env->GetDirectBufferAddress(handle_buffer);
		HCURSOR cursor = *cursor_handle;
		SetClassLong(getCurrentHWND(), GCL_HCURSOR, (LONG)cursor);
		SetCursor(cursor);
	} else {
		SetClassLong(getCurrentHWND(), GCL_HCURSOR, (LONG)NULL);
		SetCursor(LoadCursor(NULL, IDC_ARROW));
	}
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMaxCursorSize
	(JNIEnv *env, jclass clazz)
{
	return GetSystemMetrics(SM_CXCURSOR);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMinCursorSize
	(JNIEnv *env, jclass clazz)
{
	return GetSystemMetrics(SM_CXCURSOR);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy(JNIEnv *env, jclass clazz) {
	ShutdownMouse();
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz, jobject coord_buffer_obj, jobject button_buffer_obj) {
	mDIDevice->Acquire();
	UpdateMouseFields(env, clazz, coord_buffer_obj, button_buffer_obj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nGrabMouse
  (JNIEnv * env, jclass clazz, jboolean grab) {
  
	mDIDevice->Unacquire();
	if(grab) {
		if (!mouse_grabbed) {
			mouse_grabbed = true;
			ShowCursor(false);
			mouseMask = DISCL_EXCLUSIVE | DISCL_FOREGROUND;
		}
	} else {
		if (mouse_grabbed) {
			mouse_grabbed = false;
			ShowCursor(true);
			mouseMask = DISCL_NONEXCLUSIVE | DISCL_FOREGROUND;
		}	
		resetCursorPos();
	}
	mDIDevice->Unacquire();
	if(mDIDevice->SetCooperativeLevel(getCurrentHWND(), mouseMask) != DI_OK) {
	  throwException(env, "Could not set the CooperativeLevel.");
		return;
	}
	mDIDevice->Acquire();  
}

/**
 * Shutdown DI
 */
static void ShutdownMouse() {
	// release device
	if (mDIDevice != NULL) {
		mDIDevice->Unacquire();
		mDIDevice->Release();
		mDIDevice = NULL;
	}
	// Release DirectInput
	if (lpdi != NULL) {
		printfDebug("Destroying directinput\n");
		lpdi->Release();
		lpdi = NULL;
	}
}
/**
 * Enumerates the capabilities of the Mouse attached to the system
 */
static void EnumerateMouseCapabilities() {
	HRESULT hr;
	hr = mDIDevice->EnumObjects(EnumMouseObjectsCallback, NULL, DIDFT_ALL);
	if FAILED(hr) { 
		printfDebug("EnumObjects failed\n");
		mCreate_success = false;
		return;
	}
	
	//check for > 4 buttons - need to clamp since we're using dx 5
	if(mButtoncount > 4) {
		mButtoncount = 4;
		printfDebug("WARNING: Clamping to 4 mouse buttons\n");
	}
	
	mCreate_success = true;
}

/**
 * Callback from EnumObjects. Called for each "object" on the Mouse.
 */
static BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
	printfDebug("found %s\n", lpddoi->tszName);
	if(lpddoi->guidType == GUID_Button) {
		mButtoncount++;
	} else if(lpddoi->guidType == GUID_XAxis) {
	} else if(lpddoi->guidType == GUID_YAxis) {
	} else if(lpddoi->guidType == GUID_ZAxis) {
		mHaswheel = true;
	} else {
		printfDebug("Unhandled object found: %s\n", lpddoi->tszName);
	}
	return DIENUM_CONTINUE;
}

/**
 * Creates the specified device as a Mouse
 */
static void CreateMouse() {
	HRESULT hr;
	hr = lpdi->CreateDevice(GUID_SysMouse, &mDIDevice, NULL);
	if FAILED(hr) {	
		printfDebug("CreateDevice failed\n");
		mCreate_success = false;
		return;
	}
	mCreate_success = true;
}

/**
 * Sets up the Mouse properties
 */ 
static void SetupMouse() {
	// set Mouse data format
	if(mDIDevice->SetDataFormat(&c_dfDIMouse) != DI_OK) {
		printfDebug("SetDataFormat failed\n");
		mCreate_success = false;
		return;
	}

	DIPROPDWORD dipropdw;
	dipropdw.diph.dwSize = sizeof(DIPROPDWORD);
	dipropdw.diph.dwHeaderSize = sizeof(DIPROPHEADER);
	dipropdw.diph.dwObj = 0;
	dipropdw.diph.dwHow = DIPH_DEVICE;
	dipropdw.dwData = EVENT_BUFFER_SIZE;
	mDIDevice->SetProperty(DIPROP_BUFFERSIZE, &dipropdw.diph);

	// set the cooperative level
	if(mDIDevice->SetCooperativeLevel(getCurrentHWND(), mouseMask) != DI_OK) {
		printfDebug("SetCooperativeLevel failed\n");
		mCreate_success = false;
		return;
	}
	mCreate_success = true;
	resetCursorPos();
}

static int cap(int val, int min, int max) {
	if (val < min)
		return min;
	else if (val > max)
		return max;
	else
		return val;
}

static void getGDICursorDelta(int* return_dx, int* return_dy) {
	int dx;
	int dy;

	POINT newCursorPos;
	RECT clientRect;
	GetCursorPos(&newCursorPos);
	ScreenToClient(getCurrentHWND(), &newCursorPos);
	GetClientRect(getCurrentHWND(), &clientRect);
	newCursorPos.x = cap(newCursorPos.x, clientRect.left, clientRect.right - 1);
	newCursorPos.y = cap(newCursorPos.y, clientRect.top, clientRect.bottom - 1);
	// Clip the position to the client rect
	dx = newCursorPos.x - cursorPos.x;
	dy = newCursorPos.y - cursorPos.y;
	cursorPos.x += dx;
	cursorPos.y += dy;
	*return_dx = dx;
	*return_dy = dy;
}

/**
 * Updates the fields on the Mouse
 */
static void UpdateMouseFields(JNIEnv *env, jclass clsMouse, jobject coord_buffer_obj, jobject button_buffer_obj) {
	HRESULT								 hRes; 
	DIMOUSESTATE diMouseState;						// State of Mouse
	int dx, dy;

	int *coords = (int *)env->GetDirectBufferAddress(coord_buffer_obj);
	int coords_length = (int)env->GetDirectBufferCapacity(coord_buffer_obj);
	unsigned char *buttons_buffer = (unsigned char *)env->GetDirectBufferAddress(button_buffer_obj);
	int buttons_length = (int)env->GetDirectBufferCapacity(button_buffer_obj);
	if (coords_length < 3) {
		printfDebug("ERROR: Not enough space in coords array: %d < 3\n", coords_length);
		return;
	}

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
			hRes = mDIDevice->Acquire();
			if (hRes != DI_OK)
				return;
		} else {
			printfDebug("Error getting mouse state: %d\n", hRes);
			return;
		}
	}

	if (mouse_grabbed) {
		dx = diMouseState.lX;
		dy = diMouseState.lY;
	} else {
		getGDICursorDelta(&dx, &dy);
	}
	dy = -dy;

	coords[0] = dx;
	coords[1] = dy;
	coords[2] = diMouseState.lZ;
	
	for (int i = 0; i < mButtoncount; i++) {
		if (diMouseState.rgbButtons[i] != 0) {
			diMouseState.rgbButtons[i] = JNI_TRUE;
		} else {
			diMouseState.rgbButtons[i] = JNI_FALSE;
		}
	}
	int num_buttons = mButtoncount;
	if (num_buttons > buttons_length)
		num_buttons = buttons_length;
	for (int j = 0; j < num_buttons; j++)
		buttons_buffer[j] = (unsigned char)diMouseState.rgbButtons[j];
}
