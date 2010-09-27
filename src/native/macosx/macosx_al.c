/*
 * Copyright (c) 2002-2008 LWJGL Project
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

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mach-o/dyld.h>
#include <stdlib.h>
#include <string.h>
#include <CoreFoundation/CoreFoundation.h>
#include "extal.h"
#include "common_tools.h"

/**
 * $Id$
 *
 * This file contains the AL extension assigning mechanism
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */

static const struct mach_header* handleOAL = NULL;
static CFBundleRef openal_bundle = NULL;

void *extal_NativeGetFunctionPointer(const char *function) {
	void *address = NULL;
	if (handleOAL != NULL) {
		char *mac_symbol_name = (char *)malloc((strlen(function) + 2)*sizeof(char));
		if (mac_symbol_name == NULL)
			return NULL;
		mac_symbol_name[0] = '_';
		strcpy(&(mac_symbol_name[1]), function);
		NSSymbol symbol = NSLookupSymbolInImage(handleOAL, mac_symbol_name, NSLOOKUPSYMBOLINIMAGE_OPTION_RETURN_ON_ERROR);
		free(mac_symbol_name);
		if (symbol != NULL) {
			address = NSAddressOfSymbol(symbol);
		}
	} else if (openal_bundle != NULL) {
		CFStringRef cf_function = CFStringCreateWithCString(NULL, function, kCFStringEncodingUTF8);
		address = CFBundleGetFunctionPointerForName(openal_bundle, cf_function);
		CFRelease(cf_function);
	}
	return address;
}

static CFBundleRef tryLoadFramework(JNIEnv *env) {
	CFStringRef framework_path = CFSTR("/System/Library/Frameworks/OpenAL.framework");
	if (framework_path == NULL) {
		printfDebugJava(env, "Failed to allocate string");
		return NULL;
	}
	CFURLRef url = CFURLCreateWithFileSystemPath(NULL, framework_path, kCFURLPOSIXPathStyle, TRUE);
	if (url == NULL) {
		printfDebugJava(env, "Failed to allocate URL");
		return NULL;
	}
	CFBundleRef openal_bundle = CFBundleCreate(NULL, url);
	CFRelease(url);
	return openal_bundle;
}

void extal_LoadLibrary(JNIEnv *env, jstring path) {
	const char *path_str = (*env)->GetStringUTFChars(env, path, NULL);
	printfDebugJava(env, "Testing '%s'", path_str);
	handleOAL = NSAddImage(path_str, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
	if (handleOAL != NULL) {
		printfDebugJava(env, "Found OpenAL at '%s'", path_str);
	} else {
		throwException(env, "Could not load OpenAL library");
	}
	(*env)->ReleaseStringUTFChars(env, path, path_str);
}

/**
 * Unloads the OpenAL Library
 */
void extal_UnloadLibrary() {
	if (openal_bundle != NULL) {
		CFRelease(openal_bundle);
		openal_bundle = NULL;
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_openal_AL_nCreateDefault(JNIEnv *env, jclass clazz) {
	openal_bundle = tryLoadFramework(env);
	if (openal_bundle != NULL)
		printfDebugJava(env, "Found OpenAL Bundle");
	else
		throwException(env, "Could not load OpenAL framework");
}
