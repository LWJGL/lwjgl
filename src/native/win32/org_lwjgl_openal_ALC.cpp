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
 * This is the actual JNI implementation of the OpenAL context/device library. 
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
#include "org_lwjgl_openal_ALC.h"
#include "checkALerror.h"

/* OpenAL includes */
#include <alc.h>

/**
 * This function returns strings related to the context.
 *
 * C Specification:
 * ALubyte * alcGetString(ALCdevice *device, ALenum token);
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_openal_ALC_getString (JNIEnv *env, jobject obj, jobject device, jint token) {
	jclass class_device		= env->GetObjectClass(device);
	jfieldID field_device	= env->GetFieldID(class_device, "device", "I");
	jint deviceaddress = env->GetIntField(device, field_device);

	const char* alcString = (const char*) alcGetString((ALCdevice*) deviceaddress, (ALenum) token);
	if(alcString == NULL) {
		return NULL;
	}

	jstring string = env->NewStringUTF(alcString);

	CHECK_ALC_ERROR
	return string;
}

/**
 * This function returns integers related to the context.
 * 
 * C Specification:
 * ALvoid alcGetIntegerv(ALCdevice *device, ALenum token, ALsizei size, ALint *dest);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALC_getIntegerv (JNIEnv *env, jobject obj, jobject device, jint token, jint size, jint dest) {
	jclass device_class		= env->GetObjectClass(device);
	jfieldID device_field	= env->GetFieldID(device_class, "device", "I");
	jint deviceaddress		= env->GetIntField(device, device_field);

	alcGetIntegerv((ALCdevice*) deviceaddress, (ALenum) token, (ALsizei) size, (ALint*) dest);
	CHECK_ALC_ERROR
}

/**
 * This function opens a device by name.
 * 
 * C Specification:
 * ALCdevice *alcOpenDevice( const ALubyte *tokstr );
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_openal_ALC_openDevice (JNIEnv *env, jobject obj, jstring tokstr) {
	ALubyte* tokenstring;

	if(tokstr != NULL) {
		tokenstring = (ALubyte*) (env->GetStringUTFChars(tokstr, 0));
	} else {
		tokenstring = NULL;
	}

	/* get device */
	ALCdevice* device = alcOpenDevice(tokenstring);

	/* if error - cleanup and get out */
	if(device == NULL) {
		if(tokenstring != NULL) {
			env->ReleaseStringUTFChars((jstring)tokenstring, 0);
		}
		return NULL;
	}

	/* get ready to create ALCdevice instance */
	jobject alcDevice_object	= NULL;
	jclass alcDevice_class		= NULL;
	jmethodID alcDevice_method	= NULL;

	/* find class and constructor */
	alcDevice_class		= env->FindClass("org/lwjgl/openal/ALCdevice");
	alcDevice_method	= env->GetMethodID(alcDevice_class, "<init>", "(I)V");

	/* create instance */
	alcDevice_object = env->NewObject(alcDevice_class, alcDevice_method, (int) device);

	/* clean up */
	env->ReleaseStringUTFChars((jstring)tokenstring, 0);

	return alcDevice_object;
}

/**
 * This function closes a device by name.
 * 
 * C Specification:
 * void alcCloseDevice( ALCdevice *dev );
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALC_closeDevice (JNIEnv *env, jobject obj, jobject device) {
	jclass device_class		= env->GetObjectClass(device);
	jfieldID device_field	= env->GetFieldID(device_class, "device", "I");
	jint deviceaddress		= env->GetIntField(device, device_field);

	alcCloseDevice((ALCdevice*) deviceaddress);
	CHECK_ALC_ERROR
}

/**
 * This function creates a context using a specified device.
 * 
 * C Specification:
 * ALCcontext* alcCreateContext( ALCdevice *dev, ALint* attrlist );
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_openal_ALC_createContext (JNIEnv *env, jobject obj, jobject device, jint attrlist) {
	/* get device address */
	jclass device_class		= env->GetObjectClass(device);
	jfieldID device_field	= env->GetFieldID(device_class, "device", "I");
	jint deviceaddress		= env->GetIntField(device, device_field);

	ALCcontext* context = alcCreateContext((ALCdevice*) deviceaddress, (ALint*) attrlist); 
	
	/* if error - get out */
	if(context == NULL) {
		return NULL;
	}

	/* get ready to create ALCcontext instance */
	jobject alcContext_object	= NULL;
	jclass alcContext_class		= NULL;
	jmethodID alcContext_method	= NULL;

	/* find class and constructor */
	alcContext_class	= env->FindClass("org/lwjgl/openal/ALCcontext");
	alcContext_method	= env->GetMethodID(alcContext_class, "<init>", "(I)V");

	/* create instance */
	alcContext_object = env->NewObject(alcContext_class, alcContext_method, (int) context);

	CHECK_ALC_ERROR
	return alcContext_object;
}

/**
 * This function makes a specified context the current context.
 *
 * C Specification:
 * ALCboolean alcMakeContextCurrent(ALCcontext *context);
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_ALC_makeContextCurrent (JNIEnv *env, jobject obj, jobject context) {
	/* get context address */
	jclass context_class	= env->GetObjectClass(context);
	jfieldID context_field	= env->GetFieldID(context_class, "context", "I");
	jint contextaddress		= env->GetIntField(context, context_field);

	return alcMakeContextCurrent((ALCcontext*) contextaddress);
}

/**
 * This function tells a context to begin processing.
 * 
 * C Specification:
 * void alcProcessContext(ALCcontext *context);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALC_processContext (JNIEnv *env, jobject obj, jobject context) {
	/* get context address */
	jclass context_class	= env->GetObjectClass(context);
	jfieldID context_field	= env->GetFieldID(context_class, "context", "I");
	jint contextaddress		= env->GetIntField(context, context_field);

	alcProcessContext((ALCcontext*) contextaddress);
}

/**
 * This function retrieves the current context.
 * 
 * C Specification:
 * ALCcontext* alcGetCurrentContext( ALvoid );
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_openal_ALC_getCurrentContext (JNIEnv *env, jobject obj) {

	ALCcontext* context = alcGetCurrentContext();
	if(context == NULL) {
		return NULL;
	}

	/* get ready to create ALCcontext instance */
	jobject alcContext_object	= NULL;
	jclass alcContext_class		= NULL;
	jmethodID alcContext_method	= NULL;

	/* find class and constructor */
	alcContext_class	= env->FindClass("org/lwjgl/openal/ALCcontext");
	alcContext_method	= env->GetMethodID(alcContext_class, "<init>", "(I)V");

	/* create instance */
	alcContext_object = env->NewObject(alcContext_class, alcContext_method, (int) context);

	return alcContext_object;
}

/**
 * This function retrieves the specified contexts device
 * 
 * C Specification:
 * ALCdevice* alcGetContextsDevice(ALCcontext *context);
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_openal_ALC_getContextsDevice (JNIEnv *env, jobject obj, jobject context) {

	/* get context address */
	jclass context_class	= env->GetObjectClass(context);
	jfieldID context_field	= env->GetFieldID(context_class, "context", "I");
	jint contextaddress		= env->GetIntField(context, context_field);

	ALCdevice* device = alcGetContextsDevice((ALCcontext*) contextaddress); 
	if(device == NULL) {
		return NULL;
	}

	/* get ready to create ALCdevice instance */
	jobject alcDevice_object	= NULL;
	jclass alcDevice_class		= NULL;
	jmethodID alcDevice_method	= NULL;

	/* find class and constructor */
	alcDevice_class		= env->FindClass("org/lwjgl/openal/ALCdevice");
	alcDevice_method	= env->GetMethodID(alcDevice_class, "<init>", "(I)V");

	/* create instance */
	alcDevice_object = env->NewObject(alcDevice_class, alcDevice_method, (int) device);

	return alcDevice_object;
}

/**
 * This function suspends processing on a specified context.
 *
 * C Specification:
 * void alcSuspendContext(ALCcontext *context);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALC_suspendContext (JNIEnv *env, jobject obj, jobject context) {
	/* get context address */
	jclass context_class	= env->GetObjectClass(context);
	jfieldID context_field	= env->GetFieldID(context_class, "context", "I");
	jint contextaddress		= env->GetIntField(context, context_field);

	alcSuspendContext((ALCcontext*) contextaddress);
}

/**
 * This function destroys a context.
 * 
 * C Specification:
 * void alcDestroyContext(ALCcontext *context);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALC_destroyContext (JNIEnv *env, jobject obj, jobject context) {
	/* get context address */
	jclass context_class	= env->GetObjectClass(context);
	jfieldID context_field	= env->GetFieldID(context_class, "context", "I");
	jint contextaddress		= env->GetIntField(context, context_field);

	alcDestroyContext((ALCcontext*) contextaddress);
}

/**
 * This function retrieves the specified devices context error state.
 * 
 * C Specification:
 * ALCenum alcGetError(ALCdevice *device);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_ALC_getError (JNIEnv *env, jobject obj, jobject device) {
	/* get device address */
	jclass device_class		= env->GetObjectClass(device);
	jfieldID device_field	= env->GetFieldID(device_class, "device", "I");
	jint deviceaddress		= env->GetIntField(device, device_field);

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
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_ALC_isExtensionPresent (JNIEnv *env, jobject obj, jobject device, jstring extName) {
	/* get device address */
	jclass device_class		= env->GetObjectClass(device);
	jfieldID device_field	= env->GetFieldID(device_class, "device", "I");
	jint deviceaddress		= env->GetIntField(device, device_field);
	
	/* get extension */
	ALubyte* functionname = (ALubyte*) (env->GetStringUTFChars(extName, 0));
	
	jboolean result = (jboolean) alcIsExtensionPresent((ALCdevice*) deviceaddress, functionname);
	
	env->ReleaseStringUTFChars((jstring)functionname, 0);
	
	CHECK_ALC_ERROR
	return result;
}

/**
 * This function retrieves the address of a specified context extension function.
 *
 * C Specification:
 * ALvoid * alcGetProcAddress(ALCdevice *device, ALubyte *funcName);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_ALC_getProcAddress (JNIEnv *env, jobject obj, jobject device, jstring funcName) {
	/* get device address */
	jclass device_class		= env->GetObjectClass(device);
	jfieldID device_field	= env->GetFieldID(device_class, "device", "I");
	jint deviceaddress		= env->GetIntField(device, device_field);
	
	/* get extension */
	ALubyte* functionname = (ALubyte*) (env->GetStringUTFChars(funcName, 0));
	
	jint result = (jint) alcGetProcAddress((ALCdevice*) deviceaddress, functionname);
	
	env->ReleaseStringUTFChars((jstring)functionname, 0);
	
	CHECK_ALC_ERROR
	return result;
}

/**
 * This function retrieves the enum value for a specified enumeration name.
 *
 * C Specification:
 * ALenum alcGetEnumValue(ALCdevice *device, ALubyte *enumName);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_ALC_getEnumValue (JNIEnv *env, jobject obj, jobject device, jstring enumName) {
	/* get device address */
	jclass device_class		= env->GetObjectClass(device);
	jfieldID device_field	= env->GetFieldID(device_class, "device", "I");
	jint deviceaddress		= env->GetIntField(device, device_field);
	
	/* get extension */
	ALubyte* enumerationname = (ALubyte*) (env->GetStringUTFChars(enumName, 0));
	
	jint result = (jint) alcGetEnumValue((ALCdevice*) deviceaddress, enumerationname);
	
	env->ReleaseStringUTFChars((jstring)enumerationname, 0);
	
	CHECK_ALC_ERROR
	return result;
}