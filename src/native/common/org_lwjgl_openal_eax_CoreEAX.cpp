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
 * This is the actual JNI implementation of the OpenAL EAX library. 
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
#include "org_lwjgl_openal_eax_CoreEAX.h"
#include "extal.h"
#include "checkALerror.h"

/**
 * Throws an OAL exception with the specified message
 */
void ThrowException(JNIEnv *env, const char* message) {
	jclass cls = env->FindClass("org/lwjgl/openal/OpenALException");
	env->ThrowNew(cls, message);
}

/*
 * Determines available EAX extensions
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_eax_CoreEAX_determineAvailableExtensions (JNIEnv *env, jobject obj) {
#ifdef _WIN32    
	bool EAXSupported = false;

	//check that we have a current context
	if(alcGetCurrentContext() == NULL) {
		ThrowException(env, "Unable to determine EAX Extensions: No current context");
	}

	//check for extension, and assign if available
	if(alIsExtensionPresent((ALubyte*) "EAX")) {
		eaxSet = (EAXSet)alGetProcAddress((ALubyte*) "EAXSet");
		eaxGet = (EAXGet)alGetProcAddress((ALubyte*) "EAXGet");
		EAXSupported = (eaxSet != NULL && eaxGet != NULL);
	}

	//throw exception if no EAX support
	if(EAXSupported != true) {
		ThrowException(env, "Unable to determine EAX Extensions");
	}
#else
	ThrowException(env, "EAX extensions not supported");
#endif
}

JNIEXPORT void JNICALL Java_org_lwjgl_openal_eax_CoreEAX_setGUID (JNIEnv *env, jobject obj) {
#ifdef _WIN32
	//get class/fields
	jclass eax_class			= env->FindClass("org/lwjgl/openal/eax/CoreEAX");
	jfieldID eaxBuffer_field	= env->GetStaticFieldID(eax_class, "BUFFER_GUID", "I");
	jfieldID eaxListener_field	= env->GetStaticFieldID(eax_class, "LISTENER_GUID", "I");

	//set fields
	env->SetStaticIntField(eax_class, eaxBuffer_field, (jint) &DSPROPSETID_EAX20_BufferProperties);
	env->SetStaticIntField(eax_class, eaxListener_field, (jint) &DSPROPSETID_EAX20_ListenerProperties);
#else
	ThrowException(env, "EAX extensions not supported");
#endif
}

/*
 * This function retrieves an EAX value.
 * 
 * C Specification:
 * ALenum EAXGet(const struct _GUID *propertySetID,ALuint property,ALuint source,ALvoid
 * *value,ALuint size);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_eax_CoreEAX_eaxGet (JNIEnv *env, jobject obj, jint propertySetID, jint property, jint source, jint value, jint size) {
#ifdef _WIN32
	jint result = (jint) eaxGet((const struct _GUID*)propertySetID, (ALuint) property, (ALuint) source, (ALvoid*) value, (ALuint) size);
	CHECK_AL_ERROR

	return result;
#else
	ThrowException(env, "EAX extensions not supported");
#endif
}

/*
 * This function sets an EAX value.
 * 
 * C Specification:
 * ALenum EAXSet(const struct _GUID *propertySetID,ALuint property,ALuint source,ALvoid
 * *value,ALuint size);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_eax_CoreEAX_eaxSet (JNIEnv *env, jobject obj, jint propertySetID, jint property, jint source, jint value, jint size) {
#ifdef _WIN32
	jint result = (jint) eaxSet((const struct _GUID*)propertySetID, (ALuint) property, (ALuint) source, (ALvoid*) value, (ALuint) size);
	CHECK_AL_ERROR
	
	return result;
#else
	ThrowException(env, "EAX extensions not supported");
#endif
}
