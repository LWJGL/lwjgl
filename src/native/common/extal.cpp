/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
alEnablePROC alEnable = NULL;
alDisablePROC alDisable = NULL;
alIsEnabledPROC alIsEnabled = NULL;
alHintPROC alHint = NULL;
alGetBooleanPROC alGetBoolean = NULL;
alGetIntegerPROC alGetInteger = NULL;
alGetFloatPROC alGetFloat = NULL;
alGetDoublePROC alGetDouble = NULL;
alGetBooleanvPROC alGetBooleanv = NULL;
alGetIntegervPROC alGetIntegerv = NULL;
alGetFloatvPROC alGetFloatv = NULL;
alGetDoublevPROC alGetDoublev = NULL;
alGetStringPROC alGetString = NULL;
alGetErrorPROC alGetError = NULL;
alIsExtensionPresentPROC alIsExtensionPresent = NULL;
alGetProcAddressPROC alGetProcAddress = NULL;
alGetEnumValuePROC alGetEnumValue = NULL;
alListeneriPROC alListeneri = NULL;
alListenerfPROC alListenerf = NULL;
alListener3fPROC alListener3f = NULL;
alListenerfvPROC alListenerfv = NULL;
alGetListeneriPROC alGetListeneri = NULL;
alGetListenerfPROC alGetListenerf = NULL;
alGetListener3fPROC alGetListener3f = NULL;
alGetListenerfvPROC alGetListenerfv = NULL;
alGenSourcesPROC alGenSources = NULL;
alDeleteSourcesPROC alDeleteSources = NULL;
alIsSourcePROC alIsSource = NULL;
alSourceiPROC alSourcei = NULL;
alSourcefPROC alSourcef = NULL;
alSource3fPROC alSource3f = NULL;
alSourcefvPROC alSourcefv = NULL;
alGetSourceiPROC alGetSourcei = NULL;
alGetSourcefPROC alGetSourcef = NULL;
alGetSource3fPROC alGetSource3f = NULL;
alGetSourcefvPROC alGetSourcefv = NULL;
alSourcePlayvPROC alSourcePlayv = NULL;
alSourcePausevPROC alSourcePausev = NULL;
alSourceStopvPROC alSourceStopv = NULL;
alSourceRewindvPROC alSourceRewindv = NULL;
alSourcePlayPROC alSourcePlay = NULL;
alSourcePausePROC alSourcePause = NULL;
alSourceStopPROC alSourceStop = NULL;
alSourceRewindPROC alSourceRewind = NULL;
alGenBuffersPROC alGenBuffers = NULL;
alDeleteBuffersPROC alDeleteBuffers = NULL;
alIsBufferPROC alIsBuffer = NULL;
alBufferDataPROC alBufferData = NULL;
alGetBufferiPROC alGetBufferi = NULL;
alGetBufferfPROC alGetBufferf = NULL;
alSourceQueueBuffersPROC alSourceQueueBuffers = NULL;
alSourceUnqueueBuffersPROC alSourceUnqueueBuffers = NULL;
alDistanceModelPROC alDistanceModel = NULL;
alDopplerFactorPROC alDopplerFactor = NULL;
alDopplerVelocityPROC alDopplerVelocity = NULL;
alcGetStringPROC alcGetString = NULL;
alcGetIntegervPROC alcGetIntegerv = NULL;
alcOpenDevicePROC alcOpenDevice = NULL;
alcCloseDevicePROC alcCloseDevice = NULL;
alcCreateContextPROC alcCreateContext = NULL;
alcMakeContextCurrentPROC alcMakeContextCurrent = NULL;
alcProcessContextPROC alcProcessContext = NULL;
alcGetCurrentContextPROC alcGetCurrentContext = NULL;
alcGetContextsDevicePROC alcGetContextsDevice = NULL;
alcSuspendContextPROC alcSuspendContext = NULL;
alcDestroyContextPROC alcDestroyContext = NULL;
alcGetErrorPROC alcGetError = NULL;
alcIsExtensionPresentPROC alcIsExtensionPresent = NULL;
alcGetProcAddressPROC alcGetProcAddress = NULL;
alcGetEnumValuePROC alcGetEnumValue = NULL;

#ifdef _WIN32
EAXSet	eaxSet;																				 // EAXSet function, ret$
EAXGet	eaxGet;																				 // EAXGet function, ret$

/* Handle to OpenAL Library */
HMODULE handleOAL;
#endif
#ifdef _X11
void* handleOAL;
#endif
#ifdef _AGL
#include <mach-o/dyld.h>
#include <stdlib.h>
#include <string.h>
const struct mach_header* handleOAL;
#endif

/* Loads OpenAL */
static bool LoadOpenAL(JNIEnv *env, jobjectArray oalPaths);

/* Unloads OpenAL */
static void UnLoadOpenAL(void);

/* Gets a pointer to the named function */
static void* GetFunctionPointer(const char* function);

/* Loads OpenAL basic functions */
static bool LoadAL(void);

/* Loads OpenAL ALC functions */
static bool LoadALC(void);

/* Loads any extensions to OpenAL */
static bool LoadALExtensions(void);

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
static void* GetFunctionPointer(const char* function) {
	void *p = NativeGetFunctionPointer(function);
	if (p == NULL) {
		printfDebug(org_lwjgl_Sys_DEBUG, "Could not locate symbol %s\n", function);
	}
	return p;
}

/**
 * Loads the OpenAL Library
 */
static bool LoadOpenAL(JNIEnv *env, jobjectArray oalPaths) {

	jsize pathcount = env->GetArrayLength(oalPaths);
	printfDebug(org_lwjgl_Sys_DEBUG, "Found %d OpenAL paths\n", (int)pathcount);
	for(int i=0;i<pathcount;i++) {
		jstring path = (jstring) env->GetObjectArrayElement(oalPaths, i);
		const char *path_str = env->GetStringUTFChars(path, NULL);
		printfDebug(org_lwjgl_Sys_DEBUG, "Testing '%s'\n", path_str);
#ifdef _WIN32
		handleOAL = LoadLibrary(path_str);
#endif
#ifdef _X11
		handleOAL = dlopen(path_str, RTLD_LAZY);
#endif
#ifdef _AGL
		handleOAL = NSAddImage(path_str, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
#endif
		if (handleOAL != NULL) {
			printfDebug(org_lwjgl_Sys_DEBUG, "Found OpenAL at '%s'\n", path_str);
			return true;
		}
		env->ReleaseStringUTFChars(path, path_str);
	}
	throwOpenALException(env, "Could not load openal library.");
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
	dlclose(handleOAL);
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

	//load basic OpenAL functions
	if(!LoadAL()) {
		throwOpenALException(env, "Could not load OpenAL function pointers.");
		return;
	}

	//load OpenAL context functions
	if(!LoadALC()) {
		throwOpenALException(env, "Could not load ALC function pointers.");
		return;
	}

	//load OpenAL extensions
	if(!LoadALExtensions()) {
		throwOpenALException(env, "Could not load AL extension function pointers.");
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

/**
 * Loads the basic OpenAL functions
 *
 * @return true if all methods were loaded, false if one of the methods could not be loaded
 */
static bool LoadAL() {
	alEnable = (alEnablePROC) GetFunctionPointer("alEnable");
	alDisable = (alDisablePROC) GetFunctionPointer("alDisable");
	alIsEnabled = (alIsEnabledPROC) GetFunctionPointer("alIsEnabled");
	//alHint = (alHintPROC) GetFunctionPointer("alHint");
	alGetBoolean = (alGetBooleanPROC) GetFunctionPointer("alGetBoolean");
	alGetInteger = (alGetIntegerPROC) GetFunctionPointer("alGetInteger");
	alGetFloat = (alGetFloatPROC) GetFunctionPointer("alGetFloat");
	alGetDouble = (alGetDoublePROC) GetFunctionPointer("alGetDouble");
	alGetBooleanv = (alGetBooleanvPROC) GetFunctionPointer("alGetBooleanv");
	alGetIntegerv = (alGetIntegervPROC) GetFunctionPointer("alGetIntegerv");
	alGetFloatv = (alGetFloatvPROC) GetFunctionPointer("alGetFloatv");
	alGetDoublev = (alGetDoublevPROC) GetFunctionPointer("alGetDoublev");
	alGetString = (alGetStringPROC) GetFunctionPointer("alGetString");
	alGetError = (alGetErrorPROC) GetFunctionPointer("alGetError");
	alIsExtensionPresent = (alIsExtensionPresentPROC) GetFunctionPointer("alIsExtensionPresent");
	alGetProcAddress = (alGetProcAddressPROC) GetFunctionPointer("alGetProcAddress");
	alGetEnumValue = (alGetEnumValuePROC) GetFunctionPointer("alGetEnumValue");
	alListeneri = (alListeneriPROC) GetFunctionPointer("alListeneri");
	alListenerf = (alListenerfPROC) GetFunctionPointer("alListenerf");
	alListener3f = (alListener3fPROC) GetFunctionPointer("alListener3f");
	alListenerfv = (alListenerfvPROC) GetFunctionPointer("alListenerfv");
	alGetListeneri = (alGetListeneriPROC) GetFunctionPointer("alGetListeneri");
	alGetListenerf = (alGetListenerfPROC) GetFunctionPointer("alGetListenerf");
	alGetListener3f = (alGetListener3fPROC) GetFunctionPointer("alGetListener3f");
	alGetListenerfv = (alGetListenerfvPROC) GetFunctionPointer("alGetListenerfv");
	alGenSources = (alGenSourcesPROC) GetFunctionPointer("alGenSources");
	alDeleteSources = (alDeleteSourcesPROC) GetFunctionPointer("alDeleteSources");
	alIsSource = (alIsSourcePROC) GetFunctionPointer("alIsSource");
	alSourcei = (alSourceiPROC) GetFunctionPointer("alSourcei");
	alSourcef = (alSourcefPROC) GetFunctionPointer("alSourcef");
	alSource3f = (alSource3fPROC) GetFunctionPointer("alSource3f");
	alSourcefv = (alSourcefvPROC) GetFunctionPointer("alSourcefv");
	alGetSourcei = (alGetSourceiPROC) GetFunctionPointer("alGetSourcei");
	alGetSourcef = (alGetSourcefPROC) GetFunctionPointer("alGetSourcef");
	alGetSource3f = (alGetSource3fPROC) GetFunctionPointer("alGetSource3f");
	alGetSourcefv = (alGetSourcefvPROC) GetFunctionPointer("alGetSourcefv");
	alSourcePlayv = (alSourcePlayvPROC) GetFunctionPointer("alSourcePlayv");
	alSourcePausev = (alSourcePausevPROC) GetFunctionPointer("alSourcePausev");
	alSourceStopv = (alSourceStopvPROC) GetFunctionPointer("alSourceStopv");
	alSourceRewindv = (alSourceRewindvPROC) GetFunctionPointer("alSourceRewindv");
	alSourcePlay = (alSourcePlayPROC) GetFunctionPointer("alSourcePlay");
	alSourcePause = (alSourcePausePROC) GetFunctionPointer("alSourcePause");
	alSourceStop = (alSourceStopPROC) GetFunctionPointer("alSourceStop");
	alSourceRewind = (alSourceRewindPROC) GetFunctionPointer("alSourceRewind");
	alGenBuffers = (alGenBuffersPROC) GetFunctionPointer("alGenBuffers");
	alDeleteBuffers = (alDeleteBuffersPROC) GetFunctionPointer("alDeleteBuffers");
	alIsBuffer = (alIsBufferPROC) GetFunctionPointer("alIsBuffer");
	alBufferData = (alBufferDataPROC) GetFunctionPointer("alBufferData");
	alGetBufferi = (alGetBufferiPROC) GetFunctionPointer("alGetBufferi");
	alGetBufferf = (alGetBufferfPROC) GetFunctionPointer("alGetBufferf");
	alSourceQueueBuffers = (alSourceQueueBuffersPROC) GetFunctionPointer("alSourceQueueBuffers");
	alSourceUnqueueBuffers = (alSourceUnqueueBuffersPROC) GetFunctionPointer("alSourceUnqueueBuffers");
	alDistanceModel = (alDistanceModelPROC) GetFunctionPointer("alDistanceModel");
	alDopplerFactor = (alDopplerFactorPROC) GetFunctionPointer("alDopplerFactor");
	alDopplerVelocity = (alDopplerVelocityPROC) GetFunctionPointer("alDopplerVelocity");

	return 
		alEnable != NULL &&
		alDisable != NULL &&
		alIsEnabled != NULL &&
		//alHint != NULL &&
		alGetBoolean != NULL &&
		alGetInteger != NULL &&
		alGetFloat != NULL &&
		alGetDouble != NULL &&
		alGetBooleanv != NULL &&
		alGetIntegerv != NULL &&
		alGetFloatv != NULL &&
		alGetDoublev != NULL &&
		alGetString != NULL &&
		alGetError != NULL &&
		alIsExtensionPresent != NULL &&
		alGetProcAddress != NULL &&
		alGetEnumValue != NULL &&
		alListeneri != NULL &&
		alListenerf != NULL &&
		alListener3f != NULL &&
		alListenerfv != NULL &&
		alGetListeneri != NULL &&
		alGetListenerf != NULL &&
		alGetListener3f != NULL &&
		alGetListenerfv != NULL &&
		alGenSources != NULL &&
		alDeleteSources != NULL &&
		alIsSource != NULL &&
		alSourcei != NULL &&
		alSourcef != NULL &&
		alSource3f != NULL &&
		alSourcefv != NULL &&
		alGetSourcei != NULL &&
		alGetSourcef != NULL &&
		alGetSource3f != NULL &&
		alGetSourcefv != NULL &&
		alSourcePlayv != NULL &&
		alSourcePausev != NULL &&
		alSourceStopv != NULL &&
		alSourceRewindv != NULL &&
		alSourcePlay != NULL &&
		alSourcePause != NULL &&
		alSourceStop != NULL &&
		alSourceRewind != NULL &&
		alGenBuffers != NULL &&
		alDeleteBuffers != NULL &&
		alIsBuffer != NULL &&
		alBufferData != NULL &&
		alGetBufferi != NULL &&
		alGetBufferf != NULL &&
		alSourceQueueBuffers != NULL &&
		alSourceUnqueueBuffers != NULL &&
		alDistanceModel != NULL &&
		alDopplerFactor != NULL &&
		alDopplerVelocity != NULL;
}

/**
 * Loads the context OpenAL functions
 *
 * @return true if all methods were loaded, false if one of the methods could not be loaded
 */
static bool LoadALC() {
	alcGetString						= (alcGetStringPROC) GetFunctionPointer("alcGetString");
	alcGetIntegerv					= (alcGetIntegervPROC) GetFunctionPointer("alcGetIntegerv");
	alcOpenDevice					 = (alcOpenDevicePROC) GetFunctionPointer("alcOpenDevice");
	alcCloseDevice					= (alcCloseDevicePROC) GetFunctionPointer("alcCloseDevice");
	alcCreateContext				= (alcCreateContextPROC) GetFunctionPointer("alcCreateContext");
	alcMakeContextCurrent	 = (alcMakeContextCurrentPROC) GetFunctionPointer("alcMakeContextCurrent");
	alcProcessContext			 = (alcProcessContextPROC) GetFunctionPointer("alcProcessContext");
	alcGetCurrentContext		= (alcGetCurrentContextPROC) GetFunctionPointer("alcGetCurrentContext");
	alcGetContextsDevice		= (alcGetContextsDevicePROC) GetFunctionPointer("alcGetContextsDevice");
	alcSuspendContext			 = (alcSuspendContextPROC) GetFunctionPointer("alcSuspendContext");
	alcDestroyContext			 = (alcDestroyContextPROC) GetFunctionPointer("alcDestroyContext");
	alcGetError						 = (alcGetErrorPROC) GetFunctionPointer("alcGetError");
	alcIsExtensionPresent	 = (alcIsExtensionPresentPROC) GetFunctionPointer("alcIsExtensionPresent");
	alcGetProcAddress			 = (alcGetProcAddressPROC) GetFunctionPointer("alcGetProcAddress");
	alcGetEnumValue				 = (alcGetEnumValuePROC) GetFunctionPointer("alcGetEnumValue");

	return	
		alcGetString != NULL &&
		alcGetIntegerv != NULL &&
		alcOpenDevice != NULL &&
		alcCloseDevice != NULL &&
		alcCreateContext != NULL &&
		alcMakeContextCurrent != NULL &&
		alcProcessContext != NULL &&
		alcGetCurrentContext != NULL &&
		alcGetContextsDevice != NULL &&
		alcSuspendContext != NULL &&
		alcDestroyContext != NULL &&
		alcGetError != NULL &&
		alcIsExtensionPresent != NULL &&
		alcGetProcAddress != NULL &&
		alcGetEnumValue != NULL;
}

/**
 * Loads the OpenAL extensions functions
 *
 * @return true if all methods were loaded, false if one of the methods could not be loaded
 */
static bool LoadALExtensions() {
	return true;
}
