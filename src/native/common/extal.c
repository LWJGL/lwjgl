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
EAXSet	eaxSet;																				 // EAXSet function, ret$
EAXGet	eaxGet;																				 // EAXGet function, ret$

/* Handle to OpenAL Library */
HMODULE handleOAL;
#endif
#ifdef _X11
void* handleOGG;
void* handleVorbis;
void* handleVorbisFile;
void* handleOAL;
#endif
#ifdef _AGL
#include <mach-o/dyld.h>
#include <stdlib.h>
#include <string.h>
const struct mach_header* handleOAL;
#endif

alGetProcAddressPROC alGetProcAddress;

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
#ifdef _AGL
	char *mac_symbol_name = (char *)malloc((strlen(function) + 2)*sizeof(char));
	if (mac_symbol_name == NULL)
		return NULL;
	mac_symbol_name[0] = '_';
	strcpy(&(mac_symbol_name[1]), function);
	NSSymbol symbol = NSLookupSymbolInImage(handleOAL, mac_symbol_name, NSLOOKUPSYMBOLINIMAGE_OPTION_RETURN_ON_ERROR);
	free(mac_symbol_name);
	if (symbol == NULL)
		return NULL;
	return NSAddressOfSymbol(symbol);
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

/**
 * Concatenate two strings
 */
static char *concatenate(const char *str1, const char *str2) {
	int length1 = strlen(str1);
	int length2 = strlen(str2);
	char *str = (char *)calloc(length1 + length2 + 1, sizeof(char));
	strncpy(str, str1, length1);
	strncpy(str + length1, str2, length2 + 1);
	return str;
}

#ifdef _X11
static void closeVorbisLibs(void) {
	if (handleOGG != NULL) {
		dlclose(handleOGG);
		handleOGG = NULL;
	}
	if (handleVorbis != NULL) {
		dlclose(handleVorbis);
		handleVorbis = NULL;
	}
	if (handleVorbisFile != NULL) {
		dlclose(handleVorbisFile);
		handleVorbisFile = NULL;
	}
}
#endif

/**
 * Loads the OpenAL Library
 */
static bool LoadOpenAL(JNIEnv *env, jobjectArray oalPaths) {

	jsize pathcount = (*env)->GetArrayLength(env, oalPaths);
	int i;
	jstring path;
	const char *path_str;
	char *lib_str;

	printfDebug("Found %d OpenAL paths\n", (int)pathcount);
	for(i=0;i<pathcount;i++) {
		path = (jstring) (*env)->GetObjectArrayElement(env, oalPaths, i);
		path_str = (*env)->GetStringUTFChars(env, path, NULL);
		printfDebug("Testing '%s'\n", path_str);
#ifdef _WIN32
		lib_str = concatenate(path_str, "lwjglaudio.dll");
		handleOAL = LoadLibrary(lib_str);
                free(lib_str);
#endif
#ifdef _X11
		char *lib_str = concatenate(path_str, "libogg.so.0");
		handleOGG = dlopen(lib_str, RTLD_LAZY);
                free(lib_str);

		lib_str = concatenate(path_str, "libvorbis.so.0");
		handleVorbis = dlopen(lib_str, RTLD_LAZY);
                free(lib_str);

		lib_str = concatenate(path_str, "libvorbisfile.so.3");
		handleVorbisFile = dlopen(lib_str, RTLD_LAZY);
                free(lib_str);

		lib_str = concatenate(path_str, "libopenal.so");
		handleOAL = dlopen(lib_str, RTLD_LAZY);
                free(lib_str);

		if (handleOAL == NULL) {
			closeVorbisLibs();
		}
#endif
#ifdef _AGL
		char *lib_str = concatenate(path_str, "openal.dylib");
		handleOAL = NSAddImage(lib_str, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
                free(lib_str);
#endif
		if (handleOAL != NULL) {
			printfDebug("Found OpenAL at '%s'\n", path_str);
			return true;
		}
		(*env)->ReleaseStringUTFChars(env, path, path_str);
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
	closeVorbisLibs();
#endif
#ifdef _AGL
	// Cannot remove the image
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

