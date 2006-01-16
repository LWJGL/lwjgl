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

#undef	DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0300
#include "Window.h"
#include <dinput.h>
#include "common_tools.h"
#include "org_lwjgl_opengl_Win32Display.h"
#include "org_lwjgl_input_Mouse.h"

#define EVENT_SIZE 5

#define BUTTON_STATES_SIZE 7

extern HINSTANCE	dll_handle;							        // Handle to the LWJGL dll
static LPDIRECTINPUT		lpdi = NULL;						          // DirectInput
static LPDIRECTINPUTDEVICE mDIDevice;				// DI Device instance
static int mButtoncount = 0;								 // Temporary buttoncount
static bool mHaswheel;											 // Temporary wheel check

static bool mFirstTimeInitialization = true; // boolean to determine first time initialization
static bool created = false;

static jboolean win32_message_button_states[BUTTON_STATES_SIZE];

static bool mouse_grabbed;

/* These accumulated deltas track the cursor position from Windows messages */
static int accum_dx;
static int accum_dy;
static int accum_dwheel;
static int last_x;
static int last_y;

static event_queue_t event_queue;

// Function prototypes (defined in the cpp file, since header file is generic across platforms
BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef);
void ShutdownMouse(JNIEnv *env);
void InitializeMouseFields();
void UpdateMouseFields(JNIEnv *env, jobject coord_buffer_obj, jobject button_buffer_obj);

static int transformY(int y) {
	RECT clientRect;
	GetClientRect(getCurrentHWND(), &clientRect);
	return (clientRect.bottom - clientRect.top) - 1 - y;
}

static bool putMouseEventWithCoords(jint button, jint state, jint coord1, jint coord2, jint dz) {
	jint event[] = {button, state, coord1, coord2, dz};
	return putEvent(&event_queue, event);
}

static bool putMouseEvent(jint button, jint state, jint dz) {
	if (mouse_grabbed)
		return putMouseEventWithCoords(button, state, 0, 0, dz);
	else
		return putMouseEventWithCoords(button, state, last_x, last_y, dz);
}

static void resetCursorPos(void) {
	accum_dx = accum_dy = 0;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32Display_hasWheel(JNIEnv *env, jobject self) {
	return mHaswheel;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Win32Display_getButtonCount(JNIEnv *env, jobject self) {
	return mButtoncount;
}

/**
 * Enumerates the capabilities of the Mouse attached to the system
 */
static bool EnumerateMouseCapabilities(JNIEnv *env) {
	HRESULT hr;
	hr = IDirectInputDevice_EnumObjects(mDIDevice, EnumMouseObjectsCallback, NULL, DIDFT_ALL);
	if FAILED(hr) { 
		throwException(env, "EnumObjects failed");
		return false;
	}

	//check for > 4 buttons - need to clamp since we're using dx 5
	if(mButtoncount > 4) {
		mButtoncount = 4;
		printfDebugJava(env, "WARNING: Clamping to 4 mouse buttons\n");
	}
	return true;	
}

/**
 * Creates the specified device as a Mouse
 */
static bool CreateMouse(JNIEnv *env) {
	HRESULT hr;
        hr = IDirectInput_CreateDevice(lpdi, &GUID_SysMouse, &mDIDevice, NULL);
	if FAILED(hr) {	
		throwException(env, "CreateDevice failed");
		return false;
	} else
		return true;
}

/**
 * Sets up the Mouse properties
 */ 
static bool SetupMouse(JNIEnv *env) {
	DIPROPDWORD dipropdw;
	// set Mouse data format
	if(IDirectInputDevice_SetDataFormat(mDIDevice, &c_dfDIMouse) != DI_OK) {
		throwException(env, "SetDataFormat failed");
		return false;
	}

	dipropdw.diph.dwSize = sizeof(DIPROPDWORD);
	dipropdw.diph.dwHeaderSize = sizeof(DIPROPHEADER);
	dipropdw.diph.dwObj = 0;
	dipropdw.diph.dwHow = DIPH_DEVICE;
	dipropdw.dwData = EVENT_BUFFER_SIZE;
	IDirectInputDevice_SetProperty(mDIDevice, DIPROP_BUFFERSIZE, &dipropdw.diph);

	// set the cooperative level
	if (IDirectInputDevice_SetCooperativeLevel(mDIDevice, getCurrentHWND(), DISCL_EXCLUSIVE | DISCL_FOREGROUND) != DI_OK) {
		throwException(env, "SetCooperativeLevel failed");
		return false;
	}
	resetCursorPos();
	return true;
}

/**
 * Called when the Mouse instance is to be created
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_createMouse(JNIEnv *env, jobject self) {
	HRESULT ret;

	initEventQueue(&event_queue, EVENT_SIZE);

	last_x = last_y = accum_dx = accum_dy = accum_dwheel = 0;
	mouse_grabbed = false;

	// Create input
	ret = DirectInputCreate(dll_handle, DIRECTINPUT_VERSION, &lpdi, NULL);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION) {
		throwException(env, "Failed to create DirectInput");
		return;
	}

	/* skip enumeration, since we only want system mouse */
	if (!CreateMouse(env))
		return;

	//check for first time initialization - need to detect capabilities
	if (mFirstTimeInitialization) {
		/* Enumerate capabilities of Mouse */
		if (!EnumerateMouseCapabilities(env)) {
			ShutdownMouse(env);
			return;
		}
		mFirstTimeInitialization = false;
	}
	/* Do setup of Mouse */
	if (!SetupMouse(env)) {
		ShutdownMouse(env);
		return;
	}
	created = true;
}

void handleMouseScrolled(int event_dwheel) {
	if(created) {
		accum_dwheel += event_dwheel;
		putMouseEvent(-1, 0, event_dwheel);
	}
}

void handleMouseMoved(int x, int y) {
	int dx;
	int dy;
	if(created) {
		y = transformY(y);
		dx = x - last_x;
		dy = y - last_y;
		accum_dx += dx;
		accum_dy += dy;
		last_x = x;
		last_y = y;
		if (mouse_grabbed) {
			putMouseEventWithCoords(-1, 0, dx, dy, 0);
		} else {
			putMouseEventWithCoords(-1, 0, x, y, 0);
		}		
	}
}

void handleMouseButton(int button, int state) {
	if(created) {
		putMouseEvent(button, state, 0);
		if (button < BUTTON_STATES_SIZE)
			win32_message_button_states[button] = state != 0 ? JNI_TRUE: JNI_FALSE;
	}
}

static void copyDXEvents(int num_di_events, DIDEVICEOBJECTDATA *di_buffer) {
	int buffer_index = 0;
	int dx = 0, dy = 0, dwheel = 0;
        int button_state;
        int i;
        for (i = 0; i < num_di_events; i++) {
                button_state = (di_buffer[i].dwData & 0x80) != 0 ? 1 : 0;
		switch (di_buffer[i].dwOfs) {
			case DIMOFS_BUTTON0:
				putMouseEventWithCoords(0, button_state, dx, -dy, dwheel);
				dx = dy = dwheel = 0;
				break;
			case DIMOFS_BUTTON1:
				putMouseEventWithCoords(1, button_state, dx, -dy, dwheel);
				dx = dy = dwheel = 0;
				break;
			case DIMOFS_BUTTON2:
				putMouseEventWithCoords(2, button_state, dx, -dy, dwheel);
				dx = dy = dwheel = 0;
				break;
			case DIMOFS_BUTTON3:
				putMouseEventWithCoords(3, button_state, dx, -dy, dwheel);
				dx = dy = dwheel = 0;
				break;
			case DIMOFS_X:
				dx += di_buffer[i].dwData;
				break;
			case DIMOFS_Y:
				dy += di_buffer[i].dwData;
				break;
			case DIMOFS_Z:
				dwheel += di_buffer[i].dwData;
				break;
		}
	}
	if (dx != 0 || dy != 0 || dwheel != 0)
		putMouseEventWithCoords(-1, 0, dx, -dy, dwheel);
}

static void readDXBuffer(JNIEnv *env) {
	DIDEVICEOBJECTDATA rgdod[EVENT_BUFFER_SIZE];
	DWORD num_di_events = EVENT_BUFFER_SIZE;

	HRESULT ret;

	ret = IDirectInputDevice_Acquire(mDIDevice);
	if (ret != DI_OK && ret != S_FALSE)
		return;

	ret = IDirectInputDevice_GetDeviceData(mDIDevice,
			sizeof(DIDEVICEOBJECTDATA), 
			rgdod, 
			&num_di_events,
			0); 

	if (ret == DI_OK) {
		copyDXEvents(num_di_events, rgdod);
	} else if (ret == DI_BUFFEROVERFLOW) { 
		printfDebugJava(env, "Buffer overflowed");
	} else if (ret == DIERR_INPUTLOST) {
		printfDebugJava(env, "Input lost");
	} else if (ret == DIERR_NOTACQUIRED) {
		printfDebugJava(env, "not acquired");
	} else if (ret == DIERR_INVALIDPARAM) {
		printfDebugJava(env, "invalid parameter");
	} else if (ret == DIERR_NOTBUFFERED) {
		printfDebugJava(env, "not buffered");
	} else if (ret == DIERR_NOTINITIALIZED) {
		printfDebugJava(env, "not inited");
	} else {
		printfDebugJava(env, "unknown keyboard error");
	}
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Win32Display_nReadMouse
	(JNIEnv * env, jobject self, jobject buffer_obj, jint buffer_position)
{
	jint* buffer_ptr = (jint *)(*env)->GetDirectBufferAddress(env, buffer_obj) + buffer_position;
	int buffer_size = ((*env)->GetDirectBufferCapacity(env, buffer_obj))/sizeof(jint) - buffer_position;
	if (mouse_grabbed) {
		readDXBuffer(env);
	}
	return copyEvents(&event_queue, buffer_ptr, buffer_size);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_setNativeCursor
	(JNIEnv *env, jobject self, jobject handle_buffer)
{
	HCURSOR *cursor_handle;
	HCURSOR cursor;
	if (handle_buffer != NULL) {
		cursor_handle = (HCURSOR *)(*env)->GetDirectBufferAddress(env, handle_buffer);
		cursor = *cursor_handle;
		SetClassLongPtr(getCurrentHWND(), GCL_HCURSOR, (LONG_PTR)cursor);
		SetCursor(cursor);
	} else {
		SetClassLongPtr(getCurrentHWND(), GCL_HCURSOR, (LONG_PTR)NULL);
		SetCursor(LoadCursor(NULL, IDC_ARROW));
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_destroyMouse(JNIEnv *env, jobject self) {
	ShutdownMouse(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_nPollMouse(JNIEnv * env, jobject self, jobject coord_buffer_obj, jobject button_buffer_obj) {
	UpdateMouseFields(env, coord_buffer_obj, button_buffer_obj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_setCursorPosition
(JNIEnv * env, jobject self, jint x, jint y) {
	DWORD windowflags, exstyle;
	int transformed_x, transformed_y;
	RECT window_rect;
	RECT client_rect;
	RECT adjusted_client_rect;

	int left_border_width;
	int bottom_border_width;

	getWindowFlags(&windowflags, &exstyle, getCurrentFullscreen(), getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated"));
	if (!GetClientRect(getCurrentHWND(), &client_rect)) {
		printfDebugJava(env, "GetClientRect failed");
		return;
	}

	adjusted_client_rect = client_rect;
	if (!AdjustWindowRectEx(&adjusted_client_rect, windowflags, FALSE, exstyle)) {
		printfDebugJava(env, "AdjustWindowRectEx failed");
		return;
	}
	
	if (!GetWindowRect(getCurrentHWND(), &window_rect)) {
		printfDebugJava(env, "GetWindowRect failed");
		return;
	}
	left_border_width = -adjusted_client_rect.left;
	bottom_border_width = adjusted_client_rect.bottom - client_rect.bottom;
	
	transformed_x = window_rect.left + left_border_width + x;
	transformed_y = window_rect.bottom - bottom_border_width - 1 - y;
	if (!SetCursorPos(transformed_x, transformed_y))
		printfDebugJava(env, "SetCursorPos failed");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_grabMouse
(JNIEnv * env, jobject self, jboolean grab) {
	HRESULT di_res;
	if(grab) {
		if (!mouse_grabbed) {
			mouse_grabbed = true;
			IDirectInputDevice_Unacquire(mDIDevice);
			if (IDirectInputDevice_SetCooperativeLevel(mDIDevice, getCurrentHWND(), DISCL_EXCLUSIVE | DISCL_FOREGROUND) != DI_OK)
				printfDebugJava(env, "Failed to reset cooperative mode");
			IDirectInputDevice_Acquire(mDIDevice);
		}
	} else {
		if (mouse_grabbed) {
			mouse_grabbed = false;
			IDirectInputDevice_Unacquire(mDIDevice);
			if (IDirectInputDevice_SetCooperativeLevel(mDIDevice, getCurrentHWND(), DISCL_NONEXCLUSIVE | DISCL_FOREGROUND) == DI_OK ||
					IDirectInputDevice_SetCooperativeLevel(mDIDevice, getCurrentHWND(), DISCL_NONEXCLUSIVE | DISCL_BACKGROUND) == DI_OK) {
				IDirectInputDevice_Acquire(mDIDevice);
			}
		}	
	}
	initEventQueue(&event_queue, EVENT_SIZE);
}

/**
 * Shutdown DI
 */
static void ShutdownMouse(JNIEnv *env) {
	// release device
	if (mDIDevice != NULL) {
		printfDebugJava(env, "Releasing mouse DI device");
		IDirectInputDevice_Unacquire(mDIDevice);
		IDirectInputDevice_Release(mDIDevice);
		mDIDevice = NULL;
	}
	// Release DirectInput
	if (lpdi != NULL) {
		printfDebugJava(env, "Releasing directinput");
		IDirectInput_Release(lpdi);
		lpdi = NULL;
	}
	created = false;
}

/**
 * Callback from EnumObjects. Called for each "object" on the Mouse.
 */
static BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
	printfDebug("found %s\n", lpddoi->tszName);
	if(IsEqualGUID(&lpddoi->guidType, &GUID_Button)) {
		mButtoncount++;
	} else if(IsEqualGUID(&lpddoi->guidType, &GUID_XAxis)) {
	} else if(IsEqualGUID(&lpddoi->guidType, &GUID_YAxis)) {
	} else if(IsEqualGUID(&lpddoi->guidType, &GUID_ZAxis)) {
		mHaswheel = true;
	} else {
		printfDebug("Unhandled object found: %s\n", lpddoi->tszName);
	}
	return DIENUM_CONTINUE;
}

static int cap(int val, int min, int max) {
	if (val < min)
		return min;
	else if (val > max)
		return max;
	else
		return val;
}

/**
 * Updates the fields on the Mouse
 */
static void UpdateMouseFields(JNIEnv *env, jobject coord_buffer_obj, jobject button_buffer_obj) {
	HRESULT								 hRes; 
	DIMOUSESTATE diMouseState;						// State of Mouse
	int i, j;

	int *coords = (int *)(*env)->GetDirectBufferAddress(env, coord_buffer_obj);
	int coords_length = (int)(*env)->GetDirectBufferCapacity(env, coord_buffer_obj);
	unsigned char *buttons_buffer = (unsigned char *)(*env)->GetDirectBufferAddress(env, button_buffer_obj);
	int num_buttons;
	int buttons_length = (int)(*env)->GetDirectBufferCapacity(env, button_buffer_obj);
	if (coords_length < 3) {
		printfDebugJava(env, "ERROR: Not enough space in coords array: %d < 3", coords_length);
		return;
	}

	hRes = IDirectInputDevice_GetDeviceState(mDIDevice, sizeof(DIMOUSESTATE), &diMouseState);
	if (mouse_grabbed || hRes == DI_OK) {
		if (hRes != DI_OK) { 
			// Don't allow the mouse to drift when failed
			diMouseState.lX = 0;
			diMouseState.lY = 0;
			diMouseState.lZ = 0;
			// did the read fail because we lost input for some reason? 
			// if so, then attempt to reacquire. 
			if(hRes == DIERR_INPUTLOST || hRes == DIERR_NOTACQUIRED) {
				hRes = IDirectInputDevice_Acquire(mDIDevice);
				if (hRes != DI_OK)
					return;
			} else {
				printfDebugJava(env, "Error getting mouse state: %d", hRes);
				return;
			}
		}

		coords[0] = diMouseState.lX;
		coords[1] = -diMouseState.lY;
		coords[2] = diMouseState.lZ;
		num_buttons = mButtoncount;
		if (num_buttons > buttons_length)
			num_buttons = buttons_length;
		for (j = 0; j < num_buttons; j++)
			buttons_buffer[j] = diMouseState.rgbButtons[j] != 0 ? JNI_TRUE : JNI_FALSE;
	} else {
		coords[0] = last_x;
		coords[1] = last_y;
		coords[2] = accum_dwheel;
		accum_dx = accum_dy = accum_dwheel = 0;
		num_buttons = mButtoncount;
		if (num_buttons > BUTTON_STATES_SIZE)
			num_buttons = BUTTON_STATES_SIZE;
		for (j = 0; j < num_buttons; j++)
			buttons_buffer[j] = win32_message_button_states[j];
	}
}
