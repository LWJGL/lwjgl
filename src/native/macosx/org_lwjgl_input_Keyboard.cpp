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
 * Mac OS X keyboard handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <IOKit/IOKitLib.h>
#include <IOKit/hid/IOHIDKeys.h>
#include <CoreFoundation/CoreFoundation.h>
#include <stdlib.h>
#include "tools.h"
#include "org_lwjgl_input_Keyboard.h"

#define KEYBOARD_BUFFER_SIZE 50
#define KEYBOARD_SIZE 256
#define KEY_EVENT_BACKLOG 40

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs
  (JNIEnv * env, jclass clazz)
{
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

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nCreate
  (JNIEnv * env, jclass clazz)
{
	io_iterator_t device_iterator;
	io_object_t hid_device;
	kern_return_t kern_err;
	CFMutableDictionaryRef dev_props;
	CFMutableDictionaryRef matching_dic = IOServiceMatching(kIOHIDDeviceKey);
	IOReturn err = IOServiceGetMatchingServices(kIOMasterPortDefault, matching_dic, &device_iterator);
	if (err != kIOReturnSuccess) {
		throwException(env, "Could not find matching devices");
		return;
	}
	while ((hid_device = IOIteratorNext(device_iterator)) != NULL) {
		kern_err = IORegistryEntryCreateCFProperties(hid_device, &dev_props, kCFAllocatorDefault, kNilOptions);
		IOObjectRelease(hid_device);
		if (kern_err == KERN_SUCCESS && dev_props != NULL) {
			printf("Device found: ");
			printProperty(dev_props, CFSTR(kIOHIDProductKey));
			printf(" usage ");
			printProperty(dev_props, CFSTR(kIOHIDPrimaryUsageKey));
			printf(" usage page ");
			printProperty(dev_props, CFSTR(kIOHIDPrimaryUsagePageKey));
			printf("\n");
			CFRelease(dev_props);
		}
	}
	IOObjectRelease(device_iterator);
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nPoll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll
  (JNIEnv * env, jclass clazz, jobject buffer)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nRead
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableTranslation
 * Signature: ()I
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableTranslation
  (JNIEnv *env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nisStateKeySet
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nisStateKeySet(JNIEnv *env, jclass clazz, jint key)
{
  return org_lwjgl_input_Keyboard_STATE_UNKNOWN;
}
