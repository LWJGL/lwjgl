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

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "extal.h"
#include "common_tools.h"

#ifdef _X11
#include <dlfcn.h>
#endif

/**
 * $Id$
 *
 * This file contains the AL extension assigning mechanism
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
#ifdef _WIN32
/* Handle to OpenAL Library */
static HMODULE handleOAL;
#endif
#ifdef _X11
static void* handleOAL;
#endif
#ifdef _MACOSX
#include <mach-o/dyld.h>
#include <stdlib.h>
#include <string.h>
#include <CoreFoundation/CoreFoundation.h>

static const struct mach_header* handleOAL = NULL;
static CFBundleRef openal_bundle = NULL;
#endif

typedef ALvoid* (ALAPIENTRY *alGetProcAddressPROC)( ALubyte* fname );
static alGetProcAddressPROC alGetProcAddress = NULL;

/* Loads OpenAL */
static bool LoadOpenAL(JNIEnv *env, jobjectArray oalPaths);

/* Unloads OpenAL */
static void UnLoadOpenAL(void);

static void *NativeGetFunctionPointer(const char *function) {
#ifdef _WIN32
	return GetProcAddress(handleOAL, function);
#endif
#ifdef _X11
	return dlsym(handleOAL, function);
#endif
#ifdef _MACOSX
	char *mac_symbol_name = (char *)malloc((strlen(function) + 2)*sizeof(char));
	void *address = NULL;
	if (mac_symbol_name == NULL)
		return NULL;
	mac_symbol_name[0] = '_';
	strcpy(&(mac_symbol_name[1]), function);
	if (handleOAL != NULL) {
		NSSymbol symbol = NSLookupSymbolInImage(handleOAL, mac_symbol_name, NSLOOKUPSYMBOLINIMAGE_OPTION_RETURN_ON_ERROR);
		if (symbol != NULL) {
			address = NSAddressOfSymbol(symbol);
		}
	} else if (openal_bundle != NULL) {
		CFStringRef cf_function = CFStringCreateWithCString(NULL, function, kCFStringEncodingUTF8);
		address = CFBundleGetFunctionPointerForName(openal_bundle, cf_function);
		CFRelease(cf_function);
	}
	free(mac_symbol_name);
	return address;
#endif
}

/**
 * Retrieves a pointer to the named function
 *
 * @param function Name of function
 * @return pointer to named function, or NULL if not found
 */
static void* extal_GetProcAddress(const char* function) {
	void *p = NativeGetFunctionPointer(function);
	if (p == NULL) {
		printfDebug("Could not locate symbol %s\n", function);
	}
	return p;
}

#ifdef _MACOSX
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
#endif

static bool tryLoadLibrary(JNIEnv *env, jstring path) {
#ifdef _WIN32
		char *path_str = GetStringNativeChars(env, path);
		printfDebugJava(env, "Testing '%s'", path_str);
		handleOAL = LoadLibrary(path_str);
		if (handleOAL != NULL) {
			printfDebugJava(env, "Found OpenAL at '%s'", path_str);
		}
		free(path_str);
		return handleOAL != NULL;
#endif
#ifdef _X11
		char *path_str = GetStringNativeChars(env, path);
		printfDebugJava(env, "Testing '%s'", path_str);
		handleOAL = dlopen(path_str, RTLD_LAZY);
		if (handleOAL != NULL) {
			printfDebugJava(env, "Found OpenAL at '%s'", path_str);
		}
		free(path_str);
		return handleOAL != NULL;
#endif
#ifdef _MACOSX
		const char *path_str = (*env)->GetStringUTFChars(env, path, NULL);
		printfDebugJava(env, "Testing '%s'", path_str);
		handleOAL = NSAddImage(path_str, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
		if (handleOAL != NULL) {
			printfDebugJava(env, "Found OpenAL at '%s'", path_str);
		}
		(*env)->ReleaseStringUTFChars(env, path, path_str);
		openal_bundle = tryLoadFramework(env);
		if (openal_bundle != NULL)
			printfDebugJava(env, "Found OpenAL Bundle");
		return handleOAL != NULL || openal_bundle != NULL;
#endif
}

/**
 * Loads the OpenAL Library
 */
static bool LoadOpenAL(JNIEnv *env, jobjectArray oalPaths) {
	jsize pathcount = (*env)->GetArrayLength(env, oalPaths);
	int i;
	jstring path;

	for(i=0;i<pathcount;i++) {
		path = (jstring) (*env)->GetObjectArrayElement(env, oalPaths, i);
		if (tryLoadLibrary(env, path)) {
			return true;
		}
	}
	throwException(env, "Could not load openal library.");
	return false;
}

/**
 * Unloads the OpenAL Library
 */
static void UnLoadOpenAL() {
#ifdef _WIN32
	FreeLibrary(handleOAL);
#endif
#ifdef _X11
	if (handleOAL != NULL) {
		dlclose(handleOAL);
		handleOAL = NULL;
	}
#endif
#ifdef _MACOSX
	if (openal_bundle != NULL) {
		CFRelease(openal_bundle);
		openal_bundle = NULL;
	}
#endif
}

/**
 * Initializes OpenAL by loading the library
 */
void InitializeOpenAL(JNIEnv *env, jobjectArray oalPaths) {
	if(handleOAL != NULL) {
		return;
	}

	//load our library
	if (!LoadOpenAL(env, oalPaths)) {
		return;
	}
	alGetProcAddress = (alGetProcAddressPROC)extal_GetProcAddress("alGetProcAddress");
	if (alGetProcAddress == NULL) {
		DeInitializeOpenAL();
		throwException(env, "Could not load alGetProcAddress function pointer.");
		return;
	}
}

/**
 * Called to deinitialize OpenAL
 */
void DeInitializeOpenAL() {
	UnLoadOpenAL();
	handleOAL = 0;
}

void extal_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions) {
	ext_InitializeClass(env, clazz, &extal_GetProcAddress, num_functions, functions);
}

