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
#include "Window.h"
#include "common_tools.h"
#include <dinput.h>

static BYTE readBuffer[EVENT_BUFFER_SIZE];

static LPDIRECTINPUTDEVICE mDIDevice;				// DI Device instance
static int mButtoncount = 0;								 // Temporary buttoncount
static bool mHaswheel;											 // Temporary wheel check

static bool mCreate_success;								 // bool used to determine successfull creation
static bool mFirstTimeInitialization = true; // boolean to determine first time initialization

// Cached fields of Mouse.java
static jfieldID fidMButtons;
static jfieldID fidMDX;
static jfieldID fidMDY;
static jfieldID fidMDWheel;

static POINT cursorPos;
static RECT windowRect;
static bool usingNativeCursor;

// Function prototypes (defined in the cpp file, since header file is generic across platforms
void EnumerateMouseCapabilities();
BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef);
void ShutdownMouse();
void CreateMouse();
void SetupMouse();
void InitializeMouseFields();
void CacheMouseFields(JNIEnv *env, jclass clsMouse);
void UpdateMouseFields(JNIEnv *env, jclass clsMouse);

static void getScreenClientRect(RECT* clientRect, RECT* windowRect)
{
	GetClientRect(hwnd, clientRect);
	// transform clientRect to screen coordinates
	clientRect->top = -clientSize.top + windowRect->top;
	clientRect->left = -clientSize.left + windowRect->left;
	clientRect->bottom += clientRect->top;
	clientRect->right += clientRect->left;
}

/**
 * Initializes any field ids
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs(JNIEnv * env, jclass clazz) {
	/* Cache fields in Mouse */
	CacheMouseFields(env, clazz);
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

	ShowCursor(FALSE);
	CacheMouseFields(env, clazz);

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
		printfDebug(org_lwjgl_Sys_DEBUG, "Failed to acquire mouse\n");
	}
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer(JNIEnv * env, jclass clazz) {
	jobject newBuffer = env->NewDirectByteBuffer(&readBuffer, EVENT_BUFFER_SIZE);
	return newBuffer;
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

static int bufferButtons(int count, DIDEVICEOBJECTDATA *di_buffer) {
	int buffer_index = 0;
	for (int i = 0; i < count; i++) {
		unsigned char button = mapButton(di_buffer[i].dwOfs);
		if (button >= 0 && button < mButtoncount) {
			unsigned char state = (unsigned char)di_buffer[i].dwData & 0x80;
			if (state != 0)
				state = 1;
			readBuffer[buffer_index++] = button;
			readBuffer[buffer_index++] = state;
			if (buffer_index == EVENT_BUFFER_SIZE)
				break;
		}
	}
	return buffer_index/2;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nRead
	(JNIEnv * env, jclass clazz)
{
	
	static DIDEVICEOBJECTDATA rgdod[EVENT_BUFFER_SIZE];
	DWORD bufsize = EVENT_BUFFER_SIZE;

	HRESULT ret;

	ret = mDIDevice->Acquire();
	if (ret != DI_OK && ret != S_FALSE)
		return 0;

	ret = mDIDevice->GetDeviceData( 
		sizeof(DIDEVICEOBJECTDATA), 
		rgdod, 
		&bufsize,
		0); 

	if (ret == DI_OK) {
		return bufferButtons(bufsize, rgdod);
	} else if (ret == DI_BUFFEROVERFLOW) { 
		printfDebug(org_lwjgl_Sys_DEBUG, "Buffer overflowed\n");
	} else if (ret == DIERR_INPUTLOST) {
		printfDebug(org_lwjgl_Sys_DEBUG, "Input lost\n");
	} else if (ret == DIERR_NOTACQUIRED) {
		printfDebug(org_lwjgl_Sys_DEBUG, "not acquired\n");
	} else if (ret == DIERR_INVALIDPARAM) {
		printfDebug(org_lwjgl_Sys_DEBUG, "invalid parameter\n");
	} else if (ret == DIERR_NOTBUFFERED) {
		printfDebug(org_lwjgl_Sys_DEBUG, "not buffered\n");
	} else if (ret == DIERR_NOTINITIALIZED) {
		printfDebug(org_lwjgl_Sys_DEBUG, "not inited\n");
	} else {
		printfDebug(org_lwjgl_Sys_DEBUG, "unknown keyboard error\n");
	}
	return 0;
}

/*
 * Class:		 org_lwjgl_input_Mouse
 * Method:		nIsNativeCursorSupported
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNativeCursorCaps
	(JNIEnv *env, jclass clazz)
{
	return org_lwjgl_input_Mouse_CURSOR_ONE_BIT_TRANSPARENCY;
}

/*
 * Class:		 org_lwjgl_input_Mouse
 * Method:		nSetNativeCursor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor
	(JNIEnv *env, jclass clazz, jlong cursor_handle)
{
	if (mDIDevice == NULL)
		throwException(env, "null device!");
	if (cursor_handle != 0) {
		HCURSOR cursor = (HCURSOR)cursor_handle;
		SetClassLong(hwnd, GCL_HCURSOR, (LONG)cursor);
		SetCursor(cursor);
		if (!usingNativeCursor) {
			mDIDevice->Unacquire();
			if(mDIDevice->SetCooperativeLevel(hwnd, DISCL_NONEXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
				throwException(env, "Could not set the CooperativeLevel.");
				return;
			}
			/* Reset cursor position to middle of the window */
			RECT clientRect;
			GetWindowRect(hwnd, &windowRect);
			getScreenClientRect(&clientRect, &windowRect);
			cursorPos.x = (clientRect.left + clientRect.right)/2;
			cursorPos.y = clientRect.bottom - 1 - (clientRect.bottom - clientRect.top)/2;
			SetCursorPos(cursorPos.x, cursorPos.y);
			ShowCursor(TRUE);
			usingNativeCursor = true;
		}
	} else {
		if (usingNativeCursor) {
			SetClassLong(hwnd, GCL_HCURSOR, (LONG)NULL);
			SetCursor(NULL);
			mDIDevice->Unacquire();
			if(mDIDevice->SetCooperativeLevel(hwnd, DISCL_EXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
				throwException(env, "Could not set the CooperativeLevel.");
				return;
			}
			ShowCursor(FALSE);
			usingNativeCursor = false;
			mDIDevice->Acquire();
		}
	}
}

/*
 * Class:		 org_lwjgl_input_Mouse
 * Method:		nGetMaxCursorSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMaxCursorSize
	(JNIEnv *env, jclass clazz)
{
	return GetSystemMetrics(SM_CXCURSOR);
}

/*
 * Class:		 org_lwjgl_input_Mouse
 * Method:		nGetMaxCursorSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMinCursorSize
	(JNIEnv *env, jclass clazz)
{
	return GetSystemMetrics(SM_CXCURSOR);
}

/*
 * Class:		 org_lwjgl_input_Mouse
 * Method:		nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy(JNIEnv *env, jclass clazz) {
	ShowCursor(TRUE);
	ShutdownMouse();
}

/*
 * Class:		 org_lwjgl_input_Controller
 * Method:		nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz) {
	mDIDevice->Acquire();
	UpdateMouseFields(env, clazz);
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
		printfDebug(org_lwjgl_Sys_DEBUG, "EnumObjects failed\n");
		mCreate_success = false;
		return;
	}
	
	//check for > 4 buttons - need to clamp since we're using dx 5
	if(mButtoncount > 4) {
		mButtoncount = 4;
		printfDebug(org_lwjgl_Sys_DEBUG, "WARNING: Clamping to 4 mouse buttons\n");
	}
	
	mCreate_success = true;
}

/**
 * Callback from EnumObjects. Called for each "object" on the Mouse.
 */
BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
	printfDebug(org_lwjgl_Sys_DEBUG, "found %s\n", lpddoi->tszName);
	if(lpddoi->guidType == GUID_Button) {
		mButtoncount++;
	} else if(lpddoi->guidType == GUID_XAxis) {
	} else if(lpddoi->guidType == GUID_YAxis) {
	} else if(lpddoi->guidType == GUID_ZAxis) {
		mHaswheel = true;
	} else {
		printfDebug(org_lwjgl_Sys_DEBUG, "Unhandled object found: %s\n", lpddoi->tszName);
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
		printfDebug(org_lwjgl_Sys_DEBUG, "CreateDevice failed\n");
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
		printfDebug(org_lwjgl_Sys_DEBUG, "SetDataFormat failed\n");
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
	if(mDIDevice->SetCooperativeLevel(hwnd, DISCL_EXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
		printfDebug(org_lwjgl_Sys_DEBUG, "SetCooperativeLevel failed\n");
		mCreate_success = false;
		return;
	}
	mCreate_success = true;
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
	int dx = 0;
	int dy = 0;

	POINT newCursorPos;
	GetCursorPos(&newCursorPos);
	RECT clientRect;
	RECT newWindowRect;
	GetWindowRect(hwnd, &newWindowRect);
	cursorPos.x += newWindowRect.left - windowRect.left;
	cursorPos.y += newWindowRect.top - windowRect.top;
	windowRect = newWindowRect;
	getScreenClientRect(&clientRect, &windowRect);
	// Clip the position to the client rect
	newCursorPos.x = cap(newCursorPos.x, clientRect.left, clientRect.right - 1);
	newCursorPos.y = cap(newCursorPos.y, clientRect.top, clientRect.bottom - 1);
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
static void UpdateMouseFields(JNIEnv *env, jclass clsMouse) {
	HRESULT								 hRes; 
	DIMOUSESTATE diMouseState;						// State of Mouse
	int dx, dy;

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
		} else {
			printfDebug(org_lwjgl_Sys_DEBUG, "Error getting mouse state: %d\n", hRes);
		}
	}

	if (usingNativeCursor) {
		getGDICursorDelta(&dx, &dy);
	} else {
		dx = diMouseState.lX;
		dy = diMouseState.lY;
	}
	dy = -dy;

	env->SetStaticIntField(clsMouse, fidMDX, (jint)dx);
	env->SetStaticIntField(clsMouse, fidMDY, (jint)dy);
	env->SetStaticIntField(clsMouse, fidMDWheel, (jint)diMouseState.lZ);
	
	for (int i = 0; i < mButtoncount; i++) {
		if (diMouseState.rgbButtons[i] != 0) {
			diMouseState.rgbButtons[i] = JNI_TRUE;
		} else {
			diMouseState.rgbButtons[i] = JNI_FALSE;
		}
	}
	jbyteArray mButtonsArray = (jbyteArray) env->GetStaticObjectField(clsMouse, fidMButtons);
	env->SetByteArrayRegion(mButtonsArray, 0, mButtoncount, (const signed char *) diMouseState.rgbButtons);
}

/**
 * Caches the field ids for quicker access
 */
void CacheMouseFields(JNIEnv* env, jclass clsMouse) {
	fidMButtons	= env->GetStaticFieldID(clsMouse, "buttons", "[B");
	fidMDX		= env->GetStaticFieldID(clsMouse, "dx", "I");
	fidMDY		= env->GetStaticFieldID(clsMouse, "dy", "I");
	fidMDWheel	= env->GetStaticFieldID(clsMouse, "dwheel", "I");
}
