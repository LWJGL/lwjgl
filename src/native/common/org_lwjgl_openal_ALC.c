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
 * This is the actual JNI implementation of the OpenAL context/device library. 
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */

/* OpenAL includes */
#include "checkALerror.h"
#include "extal.h"

//alc
typedef ALCubyte*   (ALCAPIENTRY *alcGetStringPROC)(ALCdevice *device,ALCenum param);
typedef ALCvoid     (ALCAPIENTRY *alcGetIntegervPROC)(ALCdevice *device,ALCenum param,ALCsizei size,ALCint *data);
typedef ALCdevice*  (ALCAPIENTRY *alcOpenDevicePROC)(ALCubyte *deviceName);
typedef ALCvoid     (ALCAPIENTRY *alcCloseDevicePROC)(ALCdevice *device);
typedef ALCcontext* (ALCAPIENTRY *alcCreateContextPROC)(ALCdevice *device,ALCint *attrList);
typedef ALCenum  (ALCAPIENTRY *alcMakeContextCurrentPROC)(ALCcontext *context);
typedef ALCvoid	    (ALCAPIENTRY *alcProcessContextPROC)(ALCcontext *context);
typedef ALCdevice*  (ALCAPIENTRY *alcGetContextsDevicePROC)(ALCcontext *context);
typedef ALCvoid	    (ALCAPIENTRY *alcSuspendContextPROC)(ALCcontext *context);
typedef ALCvoid     (ALCAPIENTRY *alcDestroyContextPROC)(ALCcontext *context);
typedef ALCenum	    (ALCAPIENTRY *alcGetErrorPROC)(ALCdevice *device);
typedef ALCboolean  (ALCAPIENTRY *alcIsExtensionPresentPROC)(ALCdevice *device,ALCubyte *extName);
//typedef ALCvoid*    (ALCAPIENTRY *alcGetProcAddressPROC)(ALCdevice *device,ALCubyte *funcName);
typedef ALCenum	    (ALCAPIENTRY *alcGetEnumValuePROC)(ALCdevice *device,ALCubyte *enumName);

alcGetCurrentContextPROC alcGetCurrentContext = NULL;
static alcGetStringPROC alcGetString;
static alcGetIntegervPROC alcGetIntegerv;
static alcOpenDevicePROC alcOpenDevice;
static alcCloseDevicePROC alcCloseDevice;
static alcCreateContextPROC alcCreateContext;
static alcMakeContextCurrentPROC alcMakeContextCurrent;
static alcProcessContextPROC alcProcessContext;
static alcGetContextsDevicePROC alcGetContextsDevice;
static alcSuspendContextPROC alcSuspendContext;
static alcDestroyContextPROC alcDestroyContext;
static alcGetErrorPROC alcGetError;
static alcIsExtensionPresentPROC alcIsExtensionPresent;
//static alcGetProcAddressPROC alcGetProcAddress;
static alcGetEnumValuePROC alcGetEnumValue;

/**
 * This function returns strings related to the context.
 *
 * C Specification:
 * ALubyte * alcGetString(ALCdevice *device, ALenum token);
 */
static jstring JNICALL Java_org_lwjgl_openal_ALC_nalcGetString (JNIEnv *env, jclass clazz, jint deviceaddress, jint token) {
	const char* alcString = (const char*) alcGetString((ALCdevice*) deviceaddress, (ALenum) token);
	jstring string;

	if(alcString == NULL) {
		return NULL;
	}

	string = NewStringNative(env, alcString);

	CHECK_ALC_ERROR
	return string;
}

/**
 * This function returns integers related to the context.
 * 
 * C Specification:
 * ALvoid alcGetIntegerv(ALCdevice *device, ALenum token, ALsizei size, ALint *dest);
 */
static void JNICALL Java_org_lwjgl_openal_ALC_nalcGetIntegerv (JNIEnv *env, jclass clazz, jint deviceaddress, jint token, jint size, jobject dest, jint offset) {
  ALint* address = NULL;
  if (dest != NULL) {
    address = offset + (ALint*) (*env)->GetDirectBufferAddress(env, dest);
  }
	alcGetIntegerv((ALCdevice*) deviceaddress, (ALenum) token, (ALsizei) size, address);
	CHECK_ALC_ERROR
}

/**
 * This function opens a device by name.
 * 
 * C Specification:
 * ALCdevice *alcOpenDevice( const ALubyte *tokstr );
 */
static jobject JNICALL Java_org_lwjgl_openal_ALC_alcOpenDevice (JNIEnv *env, jclass clazz, jstring tokstr) {
	char * tokenstring;
	ALCdevice* device;
	/* get ready to create ALCdevice instance */
	jobject alcDevice_object	= NULL;
	jclass alcDevice_class		= NULL;
	jmethodID alcDevice_method	= NULL;

	if(tokstr != NULL) {
		tokenstring = GetStringNativeChars(env, tokstr);
	} else {
		tokenstring = NULL;
	}

	/* get device */
	device = alcOpenDevice((ALubyte *) tokenstring);

	/* if error - cleanup and get out */
	if(device == NULL) {
		if(tokenstring != NULL) {
			free(tokenstring);
		}
		return NULL;
	}

	/* find class and constructor */
	alcDevice_class		= (*env)->FindClass(env, "org/lwjgl/openal/ALCdevice");
	alcDevice_method	= (*env)->GetMethodID(env, alcDevice_class, "<init>", "(I)V");

	/* create instance */
	alcDevice_object = (*env)->NewObject(env, alcDevice_class, alcDevice_method, (int) device);

	/* clean up */
	if (tokenstring != NULL)
		free(tokenstring);

	return alcDevice_object;
}

/**
 * This function closes a device by name.
 * 
 * C Specification:
 * void alcCloseDevice( ALCdevice *dev );
 */
static void JNICALL Java_org_lwjgl_openal_ALC_alcCloseDevice (JNIEnv *env, jclass clazz, jint deviceaddress) {
	alcCloseDevice((ALCdevice*) deviceaddress);
}

/**
 * This function creates a context using a specified device.
 * 
 * C Specification:
 * ALCcontext* alcCreateContext( ALCdevice *dev, ALint* attrlist );
 */
static jobject JNICALL Java_org_lwjgl_openal_ALC_alcCreateContext (JNIEnv *env, jclass clazz, jint deviceaddress, jobject attrlist) {
	ALint* address = NULL;
	ALCcontext* context;
	/* get ready to create ALCcontext instance */
	jobject alcContext_object	= NULL;
	jclass alcContext_class		= NULL;
	jmethodID alcContext_method	= NULL;

	if (attrlist != NULL) {
		address = (ALint*) (*env)->GetDirectBufferAddress(env, attrlist);
	}
	context = alcCreateContext((ALCdevice*) deviceaddress, address); 
	
	/* if error - get out */
	if(context == NULL) {
		return NULL;
	}

	/* find class and constructor */
	alcContext_class	= (*env)->FindClass(env, "org/lwjgl/openal/ALCcontext");
	alcContext_method	= (*env)->GetMethodID(env, alcContext_class, "<init>", "(I)V");

	/* create instance */
	alcContext_object = (*env)->NewObject(env, alcContext_class, alcContext_method, (int) context);

	CHECK_ALC_ERROR
	return alcContext_object;
}

/**
 * This function makes a specified context the current context.
 *
 * C Specification:
 * ALCboolean alcMakeContextCurrent(ALCcontext *context);
 */
static jint JNICALL Java_org_lwjgl_openal_ALC_alcMakeContextCurrent (JNIEnv *env, jclass clazz, jint contextaddress) {
	ALCcontext* context = (ALCcontext*) contextaddress;
	ALCenum result;
	if(context == NULL) {
		result = alcMakeContextCurrent(NULL);
	} else {
		result = alcMakeContextCurrent(context);
	}
	return result;
}

/**
 * This function tells a context to begin processing.
 * 
 * C Specification:
 * void alcProcessContext(ALCcontext *context);
 */
static void JNICALL Java_org_lwjgl_openal_ALC_nalcProcessContext (JNIEnv *env, jclass clazz, jint contextaddress) {
	alcProcessContext((ALCcontext*) contextaddress);
}

/**
 * This function retrieves the current context.
 * 
 * C Specification:
 * ALCcontext* alcGetCurrentContext( ALvoid );
 */
static jobject JNICALL Java_org_lwjgl_openal_ALC_alcGetCurrentContext (JNIEnv *env, jclass clazz) {
	ALCcontext* context = alcGetCurrentContext();

	/* get ready to create ALCcontext instance */
	jobject alcContext_object	= NULL;
	jclass alcContext_class		= NULL;
	jmethodID alcContext_method	= NULL;

	if(context == NULL) {
		return NULL;
	}
	/* find class and constructor */
	alcContext_class	= (*env)->FindClass(env, "org/lwjgl/openal/ALCcontext");
	alcContext_method	= (*env)->GetMethodID(env, alcContext_class, "<init>", "(I)V");

	/* create instance */
	alcContext_object = (*env)->NewObject(env, alcContext_class, alcContext_method, (int) context);

	return alcContext_object;
}

/**
 * This function retrieves the specified contexts device
 * 
 * C Specification:
 * ALCdevice* alcGetContextsDevice(ALCcontext *context);
 */
static jobject JNICALL Java_org_lwjgl_openal_ALC_alcGetContextsDevice (JNIEnv *env, jclass clazz, jint contextaddress) {

	ALCdevice* device = alcGetContextsDevice((ALCcontext*) contextaddress); 
	/* get ready to create ALCdevice instance */
	jobject alcDevice_object	= NULL;
	jclass alcDevice_class		= NULL;
	jmethodID alcDevice_method	= NULL;

	if(device == NULL) {
		return NULL;
	}

	/* find class and constructor */
	alcDevice_class		= (*env)->FindClass(env, "org/lwjgl/openal/ALCdevice");
	alcDevice_method	= (*env)->GetMethodID(env, alcDevice_class, "<init>", "(I)V");

	/* create instance */
	alcDevice_object = (*env)->NewObject(env, alcDevice_class, alcDevice_method, (int) device);

	return alcDevice_object;
}

/**
 * This function suspends processing on a specified context.
 *
 * C Specification:
 * void alcSuspendContext(ALCcontext *context);
 */
static void JNICALL Java_org_lwjgl_openal_ALC_nalcSuspendContext (JNIEnv *env, jclass clazz, jint contextaddress) {
	alcSuspendContext((ALCcontext*) contextaddress);
}

/**
 * This function destroys a context.
 * 
 * C Specification:
 * void alcDestroyContext(ALCcontext *context);
 */
static void JNICALL Java_org_lwjgl_openal_ALC_alcDestroyContext (JNIEnv *env, jclass clazz, jint contextaddress) {
	alcDestroyContext((ALCcontext*) contextaddress);
}

/**
 * This function retrieves the specified devices context error state.
 * 
 * C Specification:
 * ALCenum alcGetError(ALCdevice *device);
 */
static jint JNICALL Java_org_lwjgl_openal_ALC_nalcGetError (JNIEnv *env, jclass clazz, jint deviceaddress) {
	jint result = alcGetError((ALCdevice*) deviceaddress);
	CHECK_ALC_ERROR
	return result;
}

/**
 * This function queries if a specified context extension is available.
 * 
 * C Specification:
 * ALboolean alcIsExtensionPresent(ALCdevice *device, ALubyte *extName);
 */
static jboolean JNICALL Java_org_lwjgl_openal_ALC_nalcIsExtensionPresent (JNIEnv *env, jclass clazz, jint deviceaddress, jstring extName) {
	/* get extension */
	ALubyte* functionname = (ALubyte*) GetStringNativeChars(env, extName);
	
	jboolean result = (jboolean) alcIsExtensionPresent((ALCdevice*) deviceaddress, functionname);
	
	free(functionname);
	
	CHECK_ALC_ERROR
	return result;
}

/**
 * This function retrieves the enum value for a specified enumeration name.
 *
 * C Specification:
 * ALenum alcGetEnumValue(ALCdevice *device, ALubyte *enumName);
 */
static jint JNICALL Java_org_lwjgl_openal_ALC_nalcGetEnumValue (JNIEnv *env, jclass clazz, jint deviceaddress, jstring enumName) {	
	/* get extension */
	ALubyte* enumerationname = (ALubyte*) GetStringNativeChars(env, enumName);
	
	jint result = (jint) alcGetEnumValue((ALCdevice*) deviceaddress, enumerationname);
	
	free(enumerationname);
	
	CHECK_ALC_ERROR
	return result;
}

/**
 * Loads the context OpenAL functions
 *
 * @return true if all methods were loaded, false if one of the methods could not be loaded
 */
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALC_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nalcGetString", "(II)Ljava/lang/String;", (void*)&Java_org_lwjgl_openal_ALC_nalcGetString, "alcGetString", (void*)&alcGetString},
		{"nalcGetIntegerv", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_openal_ALC_nalcGetIntegerv, "alcGetIntegerv", (void*)&alcGetIntegerv},
		{"alcOpenDevice", "(Ljava/lang/String;)Lorg/lwjgl/openal/ALCdevice;", (void*)&Java_org_lwjgl_openal_ALC_alcOpenDevice, "alcOpenDevice", (void*)&alcOpenDevice},
		{"alcCloseDevice", "(I)V", (void*)&Java_org_lwjgl_openal_ALC_alcCloseDevice, "alcCloseDevice", (void*)&alcCloseDevice},
		{"alcCreateContext", "(ILjava/nio/IntBuffer;)Lorg/lwjgl/openal/ALCcontext;", (void*)&Java_org_lwjgl_openal_ALC_alcCreateContext, "alcCreateContext", (void*)&alcCreateContext},
		{"alcMakeContextCurrent", "(I)I", (void*)&Java_org_lwjgl_openal_ALC_alcMakeContextCurrent, "alcMakeContextCurrent", (void*)&alcMakeContextCurrent},
		{"nalcProcessContext", "(I)V", (void*)&Java_org_lwjgl_openal_ALC_nalcProcessContext, "alcProcessContext", (void*)&alcProcessContext},
		{"alcGetCurrentContext", "()Lorg/lwjgl/openal/ALCcontext;", (void*)&Java_org_lwjgl_openal_ALC_alcGetCurrentContext, "alcGetCurrentContext", (void*)&alcGetCurrentContext},
		{"alcGetContextsDevice", "(I)Lorg/lwjgl/openal/ALCdevice;", (void*)&Java_org_lwjgl_openal_ALC_alcGetContextsDevice, "alcGetContextsDevice", (void*)&alcGetContextsDevice},
		{"nalcSuspendContext", "(I)V", (void*)&Java_org_lwjgl_openal_ALC_nalcSuspendContext, "alcSuspendContext", (void*)&alcSuspendContext},
		{"alcDestroyContext", "(I)V", (void*)&Java_org_lwjgl_openal_ALC_alcDestroyContext, "alcDestroyContext", (void*)&alcDestroyContext},
		{"nalcGetError", "(I)I", (void*)&Java_org_lwjgl_openal_ALC_nalcGetError, "alcGetError", (void*)&alcGetError},
		{"nalcIsExtensionPresent", "(ILjava/lang/String;)Z", (void*)&Java_org_lwjgl_openal_ALC_nalcIsExtensionPresent, "alcIsExtensionPresent", (void*)&alcIsExtensionPresent},
		{"nalcGetEnumValue", "(ILjava/lang/String;)I", (void*)&Java_org_lwjgl_openal_ALC_nalcGetEnumValue, "alcGetEnumValue", (void*)&alcGetEnumValue}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extal_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif
