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
 * Mac OS X mouse handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <IOKit/IOKitLib.h>
#include <IOKit/hid/IOHIDKeys.h>
#include <IOKit/hid/IOHIDLib.h>
#include <IOKit/hid/IOHIDUsageTables.h>
#include <IOKit/IOCFPlugIn.h>
#include "Window.h"
#include "tools.h"
#include "org_lwjgl_input_Mouse.h"

#define NUM_BUTTONS 7

static jfieldID fid_dx;
static jfieldID fid_dy;
static jfieldID fid_dwheel;
static jfieldID fid_buttons;

static unsigned char button_state[NUM_BUTTONS];
static int last_x;
static int last_y;
static int wheel_dz;
static bool native_cursor;
static bool created;
static IOHIDDeviceInterface **device_interface;

/*static pascal OSStatus doMouseMoved(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse moved\n");
	return eventNotHandledErr; // allow the event to propagate
}
*/
bool isMouseCreatedAndNotNativeCursor(void) {
	return created && !native_cursor;
}

static pascal OSStatus doMouseDown(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse down\n");
	lock();
	unlock();
	return eventNotHandledErr; // allow the event to propagate
}

static pascal OSStatus doMouseUp(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse up\n");
	lock();
	unlock();
	return eventNotHandledErr; // allow the event to propagate
}

static pascal OSStatus doMouseWheel(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse wheel\n");
	lock();
	unlock();
	return noErr;
}

bool registerMouseHandler(JNIEnv* env, WindowRef win_ref) {
	bool error = registerHandler(env, win_ref, doMouseDown, kEventClassMouse, kEventMouseDown);
	error = error || registerHandler(env, win_ref, doMouseUp, kEventClassMouse, kEventMouseUp);
	//error = error || registerHandler(env, win_ref, doMouseMoved, kEventClassMouse, kEventMouseMoved);
	error = error || registerHandler(env, win_ref, doMouseWheel, kEventClassMouse, kEventMouseWheelMoved);
	return !error;
}

static void printCFString(CFStringRef str) {
	CFIndex buffer_size = CFStringGetLength(str) + 1;
	char * buffer = (char *)malloc(buffer_size);
	if (buffer != NULL) {
		if (CFStringGetCString(str, buffer, buffer_size, CFStringGetSystemEncoding()))
			printf("%s", buffer);
		free(buffer);
	}
}

static void printCFNumber(CFNumberRef num) {
	long number;

	if (CFNumberGetValue(num, kCFNumberLongType, &number))
		printf("0x%lx (%ld)", number, number);
}

static bool getIntProperty(CFDictionaryRef dict, CFStringRef key, int *key_value) {
	CFTypeRef val = CFDictionaryGetValue(dict, key);
	if (val != NULL) {
		CFTypeID type = CFGetTypeID(val);
		if (type == CFNumberGetTypeID())
			if (CFNumberGetValue((CFNumberRef)val, kCFNumberIntType, key_value))
				return true;
	}
	return false;
}

static void printProperty(CFDictionaryRef dict, CFStringRef key) {
	CFTypeRef val = CFDictionaryGetValue(dict, key);
	if (val != NULL) {
		CFTypeID type = CFGetTypeID(val);
		if (type == CFArrayGetTypeID()) printf("array\n");
		else if (type == CFBooleanGetTypeID()) printf("boolean\n");
		else if (type == CFDictionaryGetTypeID()) printf("dictionary\n");
		else if (type == CFNumberGetTypeID()) printCFNumber((CFNumberRef)val);
		else if (type == CFStringGetTypeID()) printCFString((CFStringRef)val);
		else printf("<unknown object type>\n");
	}
}

static void releaseDevice(void) {
	(*device_interface)->close(device_interface);
}

static bool initDevice(io_object_t hid_device) {
	io_name_t class_name;
	IOCFPlugInInterface **plugin_interface;
	SInt32 score;
	IOReturn io_err = IOObjectGetClass(hid_device, class_name);
	if (io_err != kIOReturnSuccess)
		return false;
printf("Found device type %s\n", class_name);
	io_err = IOCreatePlugInInterfaceForService(hid_device, kIOHIDDeviceUserClientTypeID, kIOCFPlugInInterfaceID, &plugin_interface, &score);
	if (io_err != kIOReturnSuccess)
		return false;
	HRESULT plugin_err = (*plugin_interface)->QueryInterface(plugin_interface, 
			CFUUIDGetUUIDBytes(kIOHIDDeviceInterfaceID), 
			device_interface
			);
	(*plugin_interface)->Release(plugin_interface);
	if (plugin_err != S_OK)
		return false;
	io_err = (*device_interface)->open(device_interface, 0);
	if (io_err != kIOReturnSuccess) {
		releaseDevice();
		return false;
	} else
		return true;
}

static void findDevice(void) {
	io_iterator_t device_iterator;
	io_object_t hid_device;
	kern_return_t kern_err;
	bool success = false;
	CFMutableDictionaryRef dev_props;
	CFMutableDictionaryRef matching_dic = IOServiceMatching(kIOHIDDeviceKey);
	IOReturn err = IOServiceGetMatchingServices(kIOMasterPortDefault, matching_dic, &device_iterator);
	if (err != kIOReturnSuccess) {
#ifdef _DEBUG
		printf("Could not find matching devices\n");
#endif
		return;
	}
	while (!success && (hid_device = IOIteratorNext(device_iterator)) != NULL) {
		kern_err = IORegistryEntryCreateCFProperties(hid_device, &dev_props, kCFAllocatorDefault, kNilOptions);
		if (kern_err == KERN_SUCCESS && dev_props != NULL) {
			/*printf(" usage ");
			printProperty(dev_props, CFSTR(kIOHIDPrimaryUsageKey));
			printf(" usage page ");
			printProperty(dev_props, CFSTR(kIOHIDPrimaryUsagePageKey));*/
			int usage;
			int usage_page;
			if (getIntProperty(dev_props, CFSTR(kIOHIDPrimaryUsageKey), &usage) &&
			    getIntProperty(dev_props, CFSTR(kIOHIDPrimaryUsagePageKey), &usage_page) &&
			    usage_page == kHIDPage_GenericDesktop && usage == kHIDUsage_GD_Mouse) {
				printProperty(dev_props, CFSTR(kIOHIDProductKey));
				printf("\n");
				success = initDevice(hid_device);
if (success)
	printf("Device found: ");
			}
			CFRelease(dev_props);
		}
		IOObjectRelease(hid_device);
	}
	IOObjectRelease(device_iterator);
}

static void pollDevice(void) {
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nHasWheel(JNIEnv *, jclass) {
	return JNI_TRUE;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetButtonCount(JNIEnv *, jclass) {
	return NUM_BUTTONS;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs(JNIEnv * env, jclass clazz) {
	fid_dx = env->GetStaticFieldID(clazz, "dx", "I");
	fid_dy = env->GetStaticFieldID(clazz, "dy", "I");
	fid_dwheel = env->GetStaticFieldID(clazz, "dwheel", "I");
	fid_buttons = env->GetStaticFieldID(clazz, "buttons", "[Z");
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNativeCursorCaps(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor(JNIEnv *env, jclass clazz, jlong cursor_handle) {
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMinCursorSize(JNIEnv *env, jclass clazz) {
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMaxCursorSize(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nCreate(JNIEnv * env, jclass clazz) {
	last_x = 0;
	last_y = 0;
	wheel_dz = 0;
	native_cursor = false;
	findDevice();
	//CGAssociateMouseAndMouseCursorPosition(FALSE);
	created = true;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy(JNIEnv * env, jclass clazz) {
	if (!native_cursor)
		CGAssociateMouseAndMouseCursorPosition(TRUE);
	created = false;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz) {
	lock();
	int dx;
	int dy;
	if (!native_cursor) {
		pollDevice();
	} else {
		Point cursor_pos;
		GetMouse(&cursor_pos);
		dx = cursor_pos.v - last_x;
		dy = cursor_pos.h - last_y;
		last_x += dx;
		last_y += dy;
	}
	env->SetStaticIntField(clazz, fid_dx, (jint)dx);
	env->SetStaticIntField(clazz, fid_dy, (jint)dy);
	env->SetStaticIntField(clazz, fid_dwheel, (jint)wheel_dz);
	jbooleanArray buttons_array = (jbooleanArray)env->GetStaticObjectField(clazz, fid_buttons);
	env->SetBooleanArrayRegion(buttons_array, 0, NUM_BUTTONS, button_state);
	wheel_dz = 0;
	/*if (dx != 0 || dy != 0)
		printf("dx %d dy %d, lx %d ly %d\n", dx, dy, last_x, last_y);*/
	unlock();
}
