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
#include "common_tools.h"
#include "org_lwjgl_input_Mouse.h"
#include "common_tools.h"

#define NUM_BUTTONS 7

static jfieldID fid_dx;
static jfieldID fid_dy;
static jfieldID fid_dwheel;
static jfieldID fid_buttons;

static unsigned char button_states[NUM_BUTTONS];
/*static int last_x;
static int last_y;*/
static bool native_cursor;
static bool created;
static bool buffer_enabled;
static IOHIDDeviceInterface **device_interface;
static IOHIDQueueInterface **device_queue;
static IOHIDElementCookie x_axis_cookie;
static IOHIDElementCookie y_axis_cookie;
static IOHIDElementCookie z_axis_cookie;
static IOHIDElementCookie button_cookies[NUM_BUTTONS];

static event_queue_t event_queue;

static int last_dx;
static int last_dy;
static int last_dz;

static void searchDictionary(CFDictionaryRef dict);
static void searchObject(CFTypeRef object);

bool isMouseCreatedAndNotNativeCursor(void) {
	return created && !native_cursor;
}

/*static pascal OSStatus doMouseMoved(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse moved\n");
	return eventNotHandledErr; // allow the event to propagate
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
*/
/*static void printCFString(CFStringRef str) {
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
*/
static bool getLongProperty(CFDictionaryRef dict, CFStringRef key, long *key_value) {
	CFTypeRef val = CFDictionaryGetValue(dict, key);
	if (val != NULL) {
		CFTypeID type = CFGetTypeID(val);
		if (type == CFNumberGetTypeID())
			if (CFNumberGetValue((CFNumberRef)val, kCFNumberLongType, key_value))
				return true;
	}
	return false;
}

/*static void printProperty(CFDictionaryRef dict, CFStringRef key) {
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
*/
static void closeDevice(void) {
	(*device_queue)->dispose(device_queue);
	(*device_queue)->Release(device_queue);
	(*device_interface)->close(device_interface);
}

static void shutdownDevice(void) {
	(*device_queue)->stop(device_queue);
	closeDevice();
}

static bool allocDeviceQueue(void) {
	device_queue = (*device_interface)->allocQueue(device_interface);
	if (device_queue == NULL)
		return false;
	HRESULT err = (*device_queue)->create(device_queue, 0, EVENT_BUFFER_SIZE);
	if (err != S_OK) {
		(*device_queue)->Release(device_queue);
		return false;
	}
	return true;
}

static void searchArrayElement(const void * value, void * parameter) {
	searchObject((CFTypeRef)value);
}

static void searchArray(CFArrayRef array) {
	CFRange range = {0, CFArrayGetCount(array)};
	CFArrayApplyFunction(array, range, searchArrayElement, 0);
}

static void searchObject(CFTypeRef object) {
	CFTypeID type = CFGetTypeID(object);
	if      (type == CFArrayGetTypeID()) searchArray((CFArrayRef)object);
	else if (type == CFDictionaryGetTypeID()) searchDictionary((CFDictionaryRef)object);
	else printf("<unknown object>\n");
}

static void searchDictionaryElement(CFDictionaryRef dict, CFStringRef key) {
	CFTypeRef object = CFDictionaryGetValue(dict, key);
	if (object != NULL)
		searchObject(object);
}

static void addToDeviceQueue(IOHIDElementCookie cookie) {
	HRESULT result = (*device_queue)->addElement(device_queue, cookie, 0);
	if (result != S_OK) {
#ifdef _DEBUG
		printf("Could not add cookie to queue\n");
#endif
	}
}

static void testCookie(long usage_page, long usage, IOHIDElementCookie cookie, IOHIDElementCookie *store_cookie, long desired_usage_page, long desired_usage) {
	if (usage_page == desired_usage_page && usage == desired_usage && *store_cookie == NULL) {
		*store_cookie = cookie;
		addToDeviceQueue(cookie);
	}
}

static void searchDictionary(CFDictionaryRef dict) {
	searchDictionaryElement(dict, CFSTR(kIOHIDElementKey));
	long cookie_num;
	long usage;
	long usage_page;
	if (!getLongProperty(dict, CFSTR(kIOHIDElementCookieKey), &cookie_num) ||
	    !getLongProperty(dict, CFSTR(kIOHIDElementUsageKey), &usage) ||
	    !getLongProperty(dict, CFSTR(kIOHIDElementUsagePageKey), &usage_page))
		return;
	testCookie(usage_page, usage, (IOHIDElementCookie)cookie_num, &x_axis_cookie, kHIDPage_GenericDesktop, kHIDUsage_GD_X);
	testCookie(usage_page, usage, (IOHIDElementCookie)cookie_num, &y_axis_cookie, kHIDPage_GenericDesktop, kHIDUsage_GD_Y);
	testCookie(usage_page, usage, (IOHIDElementCookie)cookie_num, &z_axis_cookie, kHIDPage_GenericDesktop, kHIDUsage_GD_Wheel);
	for (int i = 0; i < NUM_BUTTONS; i++)
		testCookie(usage_page, usage, (IOHIDElementCookie)cookie_num, &button_cookies[i], kHIDPage_Button, i + 1);
}

static bool initDevice(io_object_t hid_device, CFDictionaryRef dict) {
	io_name_t class_name;
	IOCFPlugInInterface **plugin_interface;
	SInt32 score;
	IOReturn io_err = IOObjectGetClass(hid_device, class_name);
	if (io_err != kIOReturnSuccess)
		return false;
	io_err = IOCreatePlugInInterfaceForService(hid_device, kIOHIDDeviceUserClientTypeID, kIOCFPlugInInterfaceID, &plugin_interface, &score);
	if (io_err != kIOReturnSuccess)
		return false;
	HRESULT plugin_err = (*plugin_interface)->QueryInterface(plugin_interface, CFUUIDGetUUIDBytes(kIOHIDDeviceInterfaceID), (LPVOID *)(&device_interface));
	(*plugin_interface)->Release(plugin_interface);
	if (plugin_err != S_OK)
		return false;
	io_err = (*device_interface)->open(device_interface, 0);
	if (io_err != kIOReturnSuccess)
		return false;
	if (!allocDeviceQueue()) {
		(*device_interface)->close(device_interface);
		return false;
	}
	searchDictionary(dict);
	HRESULT err = (*device_queue)->start(device_queue);
	if (err != S_OK) {
		closeDevice();
		return false;
	}
	return true;
}

static bool findDevice(void) {
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
			long usage;
			long usage_page;
			if (getLongProperty(dev_props, CFSTR(kIOHIDPrimaryUsageKey), &usage) &&
			    getLongProperty(dev_props, CFSTR(kIOHIDPrimaryUsagePageKey), &usage_page) &&
			    usage_page == kHIDPage_GenericDesktop && usage == kHIDUsage_GD_Mouse) {
				success = initDevice(hid_device, dev_props);
			}
			CFRelease(dev_props);
		}
		IOObjectRelease(hid_device);
		if (success)
			break;
	}
	IOObjectRelease(device_iterator);
	return success;
}

static void handleButton(unsigned char button_index, unsigned char state) {
	button_states[button_index] = state;
	putEventElement(&event_queue, button_index);
	putEventElement(&event_queue, state);
}

static void pollDevice() {
	IOHIDEventStruct event;
	AbsoluteTime zero_time = {0, 0};
cont:
	while ((*device_queue)->getNextEvent(device_queue, &event, zero_time, 0) == S_OK) {
		IOHIDElementCookie cookie = event.elementCookie;
		if (cookie == x_axis_cookie) {
			last_dx += event.value;
			continue;
		}
		if (cookie == y_axis_cookie) {
			last_dy += event.value;
			continue;
		}
		if (cookie == z_axis_cookie) {
			last_dz += event.value;
			continue;
		}
		for (int i = 0; i < NUM_BUTTONS; i++) {
			if (cookie == button_cookies[i]) {
				if (event.value != 0)
					handleButton(i, 1);
				else
					handleButton(i, 0);
				goto cont;
			}
		}
#ifdef _DEBUG
		printf("Recieved an unknown HID device event\n");
#endif
	}
}

static void resetDeltas(void) {
	last_dx = 0;
	last_dy = 0;
	last_dz = 0;
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
	fid_buttons = env->GetStaticFieldID(clazz, "buttons", "[B");
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
	native_cursor = false;
	buffer_enabled = false;
	x_axis_cookie = NULL;
	y_axis_cookie = NULL;
	z_axis_cookie = NULL;
	resetDeltas();
	for (int i = 0; i < NUM_BUTTONS; i++) {
		button_states[i] = 0;
		button_cookies[i] = NULL;
	}
	initEventQueue(&event_queue);
	if (!findDevice()) {
		throwException(env, "Could not find HID muse device");
		return;
	}
	CGAssociateMouseAndMouseCursorPosition(FALSE);
	created = true;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy(JNIEnv * env, jclass clazz) {
	shutdownDevice();
	if (!native_cursor)
		CGAssociateMouseAndMouseCursorPosition(TRUE);
	created = false;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz) {
	int dx, dy, dz;
	pollDevice();
	dz = last_dz;
	if (!native_cursor) {
		dx = last_dx;
		dy = -last_dy;
	} else {
	/*	Point cursor_pos;
		GetMouse(&cursor_pos);
		dx = cursor_pos.v - last_x;
		dy = cursor_pos.h - last_y;
		last_x += dx;
		last_y += dy;*/
	}
	env->SetStaticIntField(clazz, fid_dx, (jint)dx);
	env->SetStaticIntField(clazz, fid_dy, (jint)dy);
	env->SetStaticIntField(clazz, fid_dwheel, (jint)dz);
	jbooleanArray buttons_array = (jbooleanArray)env->GetStaticObjectField(clazz, fid_buttons);
	env->SetBooleanArrayRegion(buttons_array, 0, NUM_BUTTONS, button_states);
	resetDeltas();
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer(JNIEnv *env, jclass clazz) {
	jobject newBuffer = env->NewDirectByteBuffer(getOutputList(&event_queue), getEventBufferSize(&event_queue));
	buffer_enabled = true;
	return newBuffer;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nRead(JNIEnv *env, jclass clazz) {
	pollDevice();
	return copyEvents(&event_queue, 2);
}
