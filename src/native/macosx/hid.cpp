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

#include "hid.h"
#include "tools.h"

static void searchDictionary(CFDictionaryRef dict, hid_device_t *hid_dev, int num_cookies, hid_cookie_t *hid_cookies);
static void searchObject(CFTypeRef object, hid_device_t *hid_dev, int num_cookies, hid_cookie_t *hid_cookies);

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

static void closeDeviceAndQueue(hid_device_t *hid_dev) {
	(*hid_dev->device_queue)->dispose(hid_dev->device_queue);
	(*hid_dev->device_queue)->Release(hid_dev->device_queue);
	(*hid_dev->device_interface)->close(hid_dev->device_interface);
}

static void closeDeviceAll(hid_device_t *hid_dev) {
	closeDeviceAndQueue(hid_dev);
	CFRelease(hid_dev->cookie_map);
}

static IOHIDQueueInterface **allocDeviceQueue(IOHIDDeviceInterface **device_interface, int buffer_size) {
	IOHIDQueueInterface **device_queue = (*device_interface)->allocQueue(device_interface);
	if (device_queue == NULL)
		return false;
	HRESULT err = (*device_queue)->create(device_queue, 0, buffer_size);
	if (err != S_OK) {
		(*device_queue)->Release(device_queue);
		return NULL;
	}
	return device_queue;
}

static void searchArray(CFArrayRef array, hid_device_t *hid_dev, int num_cookies, hid_cookie_t *hid_cookies) {
	int size = CFArrayGetCount(array);
	for (int i = 0; i < size; i++) {
		CFTypeRef value = (CFTypeRef)CFArrayGetValueAtIndex(array, i);
		searchObject(value, hid_dev, num_cookies, hid_cookies);
	}
}

static void searchObject(CFTypeRef object, hid_device_t *hid_dev, int num_cookies, hid_cookie_t *hid_cookies) {
	CFTypeID type = CFGetTypeID(object);
	if      (type == CFArrayGetTypeID()) searchArray((CFArrayRef)object, hid_dev, num_cookies, hid_cookies);
	else if (type == CFDictionaryGetTypeID()) searchDictionary((CFDictionaryRef)object, hid_dev, num_cookies, hid_cookies);
	else printf("<unknown object>\n");
}

static void searchDictionaryElement(CFDictionaryRef dict, CFStringRef key, hid_device_t *hid_dev, int num_cookies, hid_cookie_t *hid_cookies) {
	CFTypeRef object = CFDictionaryGetValue(dict, key);
	if (object != NULL)
		searchObject(object, hid_dev, num_cookies, hid_cookies);
}

static void addToDeviceQueue(hid_device_t *hid_dev, IOHIDElementCookie cookie, int index) {
	HRESULT result = (*hid_dev->device_queue)->addElement(hid_dev->device_queue, cookie, 0);
	if (result != S_OK) {
#ifdef _DEBUG
		printf("Could not add cookie to queue\n");
#endif
		return;
	}
	CFDictionaryAddValue(hid_dev->cookie_map, cookie, (void *)index);
}

static void searchDictionary(CFDictionaryRef dict, hid_device_t *hid_dev, int num_cookies, hid_cookie_t *hid_cookies) {
	searchDictionaryElement(dict, CFSTR(kIOHIDElementKey), hid_dev, num_cookies, hid_cookies);
	long cookie_num;
	long usage;
	long usage_page;
	if (!getDictLong(dict, CFSTR(kIOHIDElementCookieKey), &cookie_num) ||
	    !getDictLong(dict, CFSTR(kIOHIDElementUsageKey), &usage) ||
	    !getDictLong(dict, CFSTR(kIOHIDElementUsagePageKey), &usage_page))
		return;
	for (int i = 0; i < num_cookies; i++) {
		if (hid_cookies[i].usage_page != kHIDPage_Undefined && hid_cookies[i].usage != kHIDUsage_Undefined &&
		    usage_page == hid_cookies[i].usage_page && usage == hid_cookies[i].usage) {
			addToDeviceQueue(hid_dev, (IOHIDElementCookie)cookie_num, i);
		}
	}
}

static bool initDevice(hid_device_t *hid_dev, io_object_t hid_device, CFDictionaryRef dict, int num_cookies, hid_cookie_t *hid_cookies, int buffer_size) {
	io_name_t class_name;
	IOCFPlugInInterface **plugin_interface;
	SInt32 score;
	IOReturn io_err = IOObjectGetClass(hid_device, class_name);
	if (io_err != kIOReturnSuccess)
		return false;
	io_err = IOCreatePlugInInterfaceForService(hid_device, kIOHIDDeviceUserClientTypeID, kIOCFPlugInInterfaceID, &plugin_interface, &score);
	if (io_err != kIOReturnSuccess)
		return false;
	HRESULT plugin_err = (*plugin_interface)->QueryInterface(plugin_interface, CFUUIDGetUUIDBytes(kIOHIDDeviceInterfaceID), (LPVOID *)(&(hid_dev->device_interface)));
	(*plugin_interface)->Release(plugin_interface);
	if (plugin_err != S_OK)
		return false;
	io_err = (*hid_dev->device_interface)->open(hid_dev->device_interface, 0);
	if (io_err != kIOReturnSuccess)
		return false;
	hid_dev->device_queue = allocDeviceQueue(hid_dev->device_interface, buffer_size);
	if (hid_dev->device_queue == NULL) {
		(*hid_dev->device_interface)->close(hid_dev->device_interface);
		return false;
	}
	hid_dev->cookie_map = CFDictionaryCreateMutable(NULL, 0, NULL, NULL);
	if (hid_dev->cookie_map == NULL) {
		closeDeviceAndQueue(hid_dev);
		return false;
	}
	searchDictionary(dict, hid_dev, num_cookies, hid_cookies);
	HRESULT err = (*hid_dev->device_queue)->start(hid_dev->device_queue);
	if (err != S_OK) {
		closeDeviceAll(hid_dev);
		return false;
	}
	return true;
}

bool findDevice(hid_device_t *hid_dev, long device_usage_page, long device_usage, int num_cookies, hid_cookie_t *hid_cookies, int buffer_size) {
	io_iterator_t device_iterator;
	io_object_t hid_device;
	kern_return_t kern_err;
	bool success = false;
	CFMutableDictionaryRef dev_props;
	CFDictionaryRef matching_dic = IOServiceMatching(kIOHIDDeviceKey);
	IOReturn err = IOServiceGetMatchingServices(kIOMasterPortDefault, matching_dic, &device_iterator);
	if (err != kIOReturnSuccess) {
#ifdef _DEBUG
		printf("Could not find matching devices\n");
#endif
		return false;
	}
	while (!success && (hid_device = IOIteratorNext(device_iterator)) != NULL) {
		kern_err = IORegistryEntryCreateCFProperties(hid_device, &dev_props, kCFAllocatorDefault, kNilOptions);
		if (kern_err == KERN_SUCCESS && dev_props != NULL) {
			long usage;
			long usage_page;
			if (getDictLong(dev_props, CFSTR(kIOHIDPrimaryUsageKey), &usage) &&
			    getDictLong(dev_props, CFSTR(kIOHIDPrimaryUsagePageKey), &usage_page) &&
			    usage_page == device_usage_page && usage == device_usage) {
				success = initDevice(hid_dev, hid_device, dev_props, num_cookies, hid_cookies, buffer_size);
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

void shutdownDevice(hid_device_t *hid_dev) {
	(*hid_dev->device_queue)->stop(hid_dev->device_queue);
	closeDeviceAll(hid_dev);
}

bool nextDeviceEvent(hid_device_t *hid_dev, hid_event_t *hid_event) {
	IOHIDEventStruct event;
	AbsoluteTime zero_time = {0, 0};
	HRESULT err = (*hid_dev->device_queue)->getNextEvent(hid_dev->device_queue, &event, zero_time, 0);
	if (err != S_OK)
		return false;
	const void *mapped_index = CFDictionaryGetValue(hid_dev->cookie_map, event.elementCookie);
	hid_event->cookie_index = mapped_index;
	hid_event->value = (int)event.value;
	return true;
}
