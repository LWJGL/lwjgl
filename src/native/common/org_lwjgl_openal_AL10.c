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
 * This is the actual JNI implementation of the OpenAL core. It handles 
 * whatever is needed for proper OpenAL support via using Java.
 * 
 * @author  Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */

/* OpenAL includes */
#include "checkALerror.h"
#include "extal.h"

//al
typedef ALvoid	    (ALAPIENTRY *alDisablePROC)( ALenum capability ); 
typedef ALboolean   (ALAPIENTRY *alIsEnabledPROC)( ALenum capability ); 
//typedef ALvoid	    (ALAPIENTRY *alHintPROC)( ALenum target, ALenum mode );
typedef ALboolean   (ALAPIENTRY *alGetBooleanPROC)( ALenum param );
typedef ALint		(ALAPIENTRY *alGetIntegerPROC)( ALenum param );
typedef ALfloat	    (ALAPIENTRY *alGetFloatPROC)( ALenum param );
//typedef ALvoid	    (ALAPIENTRY *alGetBooleanvPROC)( ALenum param, ALboolean* data );
typedef ALvoid	    (ALAPIENTRY *alGetIntegervPROC)( ALenum param, ALint* data );
typedef ALvoid	    (ALAPIENTRY *alGetFloatvPROC)( ALenum param, ALfloat* data );
typedef ALenum	    (ALAPIENTRY *alGetEnumValuePROC)( ALubyte* ename );
typedef ALvoid	    (ALAPIENTRY *alListeneriPROC)( ALenum param, ALint value );
typedef ALvoid	    (ALAPIENTRY *alListenerfPROC)( ALenum param, ALfloat value );
typedef ALvoid	    (ALAPIENTRY *alListener3fPROC)( ALenum param, ALfloat v1, ALfloat v2, ALfloat v3 ); 
typedef ALvoid	    (ALAPIENTRY *alListenerfvPROC)( ALenum param, ALfloat* values ); 
typedef ALvoid	    (ALAPIENTRY *alGetListeneriPROC)( ALenum param, ALint* value );
typedef ALvoid	    (ALAPIENTRY *alGetListenerfPROC)( ALenum param, ALfloat* value );
//typedef ALvoid	    (ALAPIENTRY *alGetListener3fPROC)( ALenum param, ALfloat* v1, ALfloat* v2, ALfloat* v3 ); 
typedef ALvoid	    (ALAPIENTRY *alGetListenerfvPROC)( ALenum param, ALfloat* values ); 
typedef ALvoid	    (ALAPIENTRY *alGenSourcesPROC)( ALsizei n, ALuint* sources ); 
typedef ALvoid	    (ALAPIENTRY *alDeleteSourcesPROC)( ALsizei n, ALuint* sources );
typedef ALboolean   (ALAPIENTRY *alIsSourcePROC)( ALuint id ); 
typedef ALvoid	    (ALAPIENTRY *alSourceiPROC)( ALuint source, ALenum param, ALint value ); 
typedef ALvoid	    (ALAPIENTRY *alSourcefPROC)( ALuint source, ALenum param, ALfloat value ); 
typedef ALvoid	    (ALAPIENTRY *alSource3fPROC)( ALuint source, ALenum param, ALfloat v1, ALfloat v2, ALfloat v3 );
typedef ALvoid	    (ALAPIENTRY *alSourcefvPROC)( ALuint source, ALenum param, ALfloat* values ); 
typedef ALvoid	    (ALAPIENTRY *alGetSourceiPROC)( ALuint source,  ALenum param, ALint* value );
typedef ALvoid	    (ALAPIENTRY *alGetSourcefPROC)( ALuint source,  ALenum param, ALfloat* value );
//typedef ALvoid	    (ALAPIENTRY *alGetSource3fPROC)( ALuint source,  ALenum param, ALfloat* v1, ALfloat* v2, ALfloat* v3 );
typedef ALvoid	    (ALAPIENTRY *alGetSourcefvPROC)( ALuint source, ALenum param, ALfloat* values );
typedef ALvoid	    (ALAPIENTRY *alSourcePlayvPROC)( ALsizei n, ALuint *sources );
typedef ALvoid	    (ALAPIENTRY *alSourcePausevPROC)( ALsizei n, ALuint *sources );
typedef ALvoid	    (ALAPIENTRY *alSourceStopvPROC)( ALsizei n, ALuint *sources );
typedef ALvoid	    (ALAPIENTRY *alSourceRewindvPROC)(ALsizei n,ALuint *sources);
typedef ALvoid	    (ALAPIENTRY *alSourcePlayPROC)( ALuint source );
typedef ALvoid	    (ALAPIENTRY *alSourcePausePROC)( ALuint source );
typedef ALvoid	    (ALAPIENTRY *alSourceStopPROC)( ALuint source );
typedef ALvoid	    (ALAPIENTRY *alSourceRewindPROC)( ALuint source );
typedef ALvoid 	    (ALAPIENTRY *alGenBuffersPROC)( ALsizei n, ALuint* buffers );
typedef ALvoid	    (ALAPIENTRY *alDeleteBuffersPROC)( ALsizei n, ALuint* buffers );
typedef ALboolean   (ALAPIENTRY *alIsBufferPROC)( ALuint buffer );
typedef ALvoid	    (ALAPIENTRY *alBufferDataPROC)( ALuint   buffer,
										 ALenum   format,
										 ALvoid*  data,
										 ALsizei  size,
										 ALsizei  freq );
typedef ALvoid	    (ALAPIENTRY *alGetBufferiPROC)( ALuint buffer, ALenum param, ALint*   value );
typedef ALvoid	    (ALAPIENTRY *alGetBufferfPROC)( ALuint buffer, ALenum param, ALfloat* value );
typedef ALvoid	    (ALAPIENTRY *alSourceQueueBuffersPROC)( ALuint source, ALsizei n, ALuint* buffers );
typedef ALvoid	    (ALAPIENTRY *alSourceUnqueueBuffersPROC)( ALuint source, ALsizei n, ALuint* buffers );
typedef ALvoid	    (ALAPIENTRY *alDistanceModelPROC)( ALenum value );
typedef ALvoid	    (ALAPIENTRY *alDopplerFactorPROC)( ALfloat value );
typedef ALvoid	    (ALAPIENTRY *alDopplerVelocityPROC)( ALfloat value );

alIsExtensionPresentPROC alIsExtensionPresent = NULL;
alGetStringPROC alGetString = NULL;
alGetErrorPROC alGetError = NULL;

alEnablePROC alEnable = NULL;
static alDisablePROC alDisable;
static alIsEnabledPROC alIsEnabled;
//static alHintPROC alHint;
static alGetBooleanPROC alGetBoolean;
static alGetIntegerPROC alGetInteger;
static alGetFloatPROC alGetFloat;
//static alGetBooleanvPROC alGetBooleanv;
static alGetIntegervPROC alGetIntegerv;
static alGetFloatvPROC alGetFloatv;
static alGetEnumValuePROC alGetEnumValue;
static alListeneriPROC alListeneri;
static alListenerfPROC alListenerf;
static alListener3fPROC alListener3f;
static alListenerfvPROC alListenerfv;
static alGetListeneriPROC alGetListeneri;
static alGetListenerfPROC alGetListenerf;
//static alGetListener3fPROC alGetListener3f;
static alGetListenerfvPROC alGetListenerfv;
static alGenSourcesPROC alGenSources;
static alDeleteSourcesPROC alDeleteSources;
static alIsSourcePROC alIsSource;
static alSourceiPROC alSourcei;
static alSourcefPROC alSourcef;
static alSource3fPROC alSource3f;
static alSourcefvPROC alSourcefv;
static alGetSourceiPROC alGetSourcei;
static alGetSourcefPROC alGetSourcef;
//static alGetSource3fPROC alGetSource3f;
static alGetSourcefvPROC alGetSourcefv;
static alSourcePlayvPROC alSourcePlayv;
static alSourcePausevPROC alSourcePausev;
static alSourceStopvPROC alSourceStopv;
static alSourceRewindvPROC alSourceRewindv;
static alSourcePlayPROC alSourcePlay;
static alSourcePausePROC alSourcePause;
static alSourceStopPROC alSourceStop;
static alSourceRewindPROC alSourceRewind;
static alGenBuffersPROC alGenBuffers;
static alDeleteBuffersPROC alDeleteBuffers;
static alIsBufferPROC alIsBuffer;
static alBufferDataPROC alBufferData;
static alGetBufferiPROC alGetBufferi;
static alGetBufferfPROC alGetBufferf;
static alSourceQueueBuffersPROC alSourceQueueBuffers;
static alSourceUnqueueBuffersPROC alSourceUnqueueBuffers;
static alDistanceModelPROC alDistanceModel;
static alDopplerFactorPROC alDopplerFactor;
static alDopplerVelocityPROC alDopplerVelocity;

/**
 * This function enables a feature of the OpenAL driver.
 *
 * C Specification:
 * ALvoid alEnable(ALenum capability);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alEnable (JNIEnv *env, jclass clazz, jint capability) {
	alEnable((ALenum) capability);
	CHECK_AL_ERROR
}

/**
 * This function disables a feature of the OpenAL driver.
 *
 * C Specification:
 * ALvoid alDisable(ALenum capability);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alDisable (JNIEnv *env, jclass clazz, jint capability) {
	alDisable((ALenum) capability);
	CHECK_AL_ERROR
}

/**
 * This function returns a boolean indicating if a specific feature is enabled in the OpenAL driver.
 * 
 * C Specification:
 * Alboolean alIsEnabled(ALenum capability);
 */
static jboolean JNICALL Java_org_lwjgl_openal_AL10_alIsEnabled (JNIEnv *env, jclass clazz, jint capability) {
	jboolean result = (jboolean) alIsEnabled((ALenum) capability);
	
	CHECK_AL_ERROR
	return result;
}

/**
 * This function Enables a feature of the OpenAL driver.
 *
 * C Specification
 * ALvoid alHint(ALenum target, ALenum mode);
 */
/*static void JNICALL Java_org_lwjgl_openal_AL10_alHint (JNIEnv *env, jclass clazz, jint target, jint mode) {
	alHint((ALint)target, (ALint)mode);
	//cannot link with above statement
	return;
}*/


/**
 * This function returns a boolean OpenAL state.
 * 
 * C Specification:
 * Alboolean alGetBoolean(ALenum pname);
 */
static jboolean JNICALL Java_org_lwjgl_openal_AL10_alGetBoolean (JNIEnv *env, jclass clazz, jint pname) {
	jboolean result = (jboolean) alGetBoolean((ALenum) pname);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function returns an integer OpenAL state.
 * 
 * C Specification:
 * Alint alGetInteger(ALenum pname);
 */
static jint JNICALL Java_org_lwjgl_openal_AL10_alGetInteger (JNIEnv *env, jclass clazz, jint pname) {
	jint result = (jint) alGetInteger((ALenum) pname);

	CHECK_AL_ERROR
	return result;
}

/** 
 * This function retrieves a integer OpenAL state.
 * 
 * C Specification:
 * ALvoid nalGetIntegerv(ALenum pname,ALint *data);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalGetIntegerv (JNIEnv *env, jclass clazz, jint pname, jobject data, int offset) {
	alGetIntegerv((ALenum) pname, offset + (ALint*) (*env)->GetDirectBufferAddress(env, data));
	CHECK_AL_ERROR
}

/**
 * This function returns a floating point OpenAL state.
 * 
 * C Specification:
 * ALfloat alGetFloat(ALenum pname);
 */
static jfloat JNICALL Java_org_lwjgl_openal_AL10_alGetFloat (JNIEnv *env, jclass clazz, jint pname) {
	jfloat result = (jfloat) alGetFloat((ALenum) pname);

	CHECK_AL_ERROR
	return result;
}

/** 
 * This function retrieves a floating point OpenAL state.
 * 
 * C Specification:
 * ALvoid alGetFloatv(ALenum pname,ALfloat *data);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalGetFloatv (JNIEnv *env, jclass clazz, jint pname, jobject data, int offset) {
	alGetFloatv((ALenum) pname, offset + (ALfloat*) (*env)->GetDirectBufferAddress(env, data));
	CHECK_AL_ERROR
}

/**
 * This function retrieves an OpenAL string property.
 * 
 * C Specification:
 * ALubyte * alGetString(ALenum pname);
 */
static jstring JNICALL Java_org_lwjgl_openal_AL10_alGetString (JNIEnv *env, jclass clazz, jint param) {
  jstring string = NewStringNative(env, (char*) alGetString(param));
	CHECK_AL_ERROR
	return string;
}

/**
 * This function returns the current error state and then clears the error state.
 * 
 * C Specification:
 * ALenum alGetError(ALvoid);
 */
static jint JNICALL Java_org_lwjgl_openal_AL10_alGetError (JNIEnv *env, jclass clazz) {
	jint result = (jint) alGetError();

	// Don't think we should be checking for errors here..?
//	CHECK_AL_ERROR
	return result;
}

/**
 * This function tests if a specific extension is available for the OpenAL driver.
 * 
 * C Specification:
 * ALboolean alIsExtensionPresent(ALubyte *extName);
 */
static jboolean JNICALL Java_org_lwjgl_openal_AL10_alIsExtensionPresent (JNIEnv *env, jclass clazz, jstring fname) {
	ALubyte* functionname = (ALubyte*) GetStringNativeChars(env, fname);
	jboolean result = (jboolean) alIsExtensionPresent(functionname);
	free(functionname);
	
	CHECK_AL_ERROR
	return result;
}

/**
 * This function returns the enumeration value of an OpenAL enum described by a string.
 * 
 * C Specification:
 * ALenum alGetEnumValue(ALubyte *enumName);
 */
static jint JNICALL Java_org_lwjgl_openal_AL10_alGetEnumValue (JNIEnv *env, jclass clazz, jstring ename) {
	ALubyte* functionname = (ALubyte*) GetStringNativeChars(env, ename);
	jint result = (jint) alGetEnumValue(functionname);
	free(functionname);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function sets an integer property of the listener.
 * 
 * C Specification:
 * ALvoid alListeneri(ALenum pname,ALint value);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alListeneri (JNIEnv *env, jclass clazz, jint pname, jint value) {
	alListeneri((ALenum) pname, (ALint) value);
	CHECK_AL_ERROR
}

/**
 * This function sets a floating point property for the listener.
 * 
 * C Specification:
 * ALvoid alListenerf(ALenum pname,ALfloat value);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alListenerf (JNIEnv *env, jclass clazz, jint pname, jfloat value) {
	alListenerf((ALenum) pname, (ALfloat) value);
	CHECK_AL_ERROR
}

/*
 * Class:     org_lwjgl_openal_AL10
 * Method:    nalListenerf
 * Signature: (ILjava/nio/FloatBuffer;I)V
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalListenerfv (JNIEnv *env, jclass clazz, jint pname, jobject values, jint offset) {
  alListenerfv((ALenum) pname, offset + (ALfloat*) (*env)->GetDirectBufferAddress(env, values));
}

/**
 * This function sets a floating point property for the listener.
 *
 * C Specification:
 * ALvoid alListener3f(ALenum pname,ALfloat v1,ALfloat v2,ALfloat v3);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alListener3f (JNIEnv *env, jclass clazz, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alListener3f((ALenum) pname, (ALfloat) v1, (ALfloat) v2, (ALfloat) v3);
	CHECK_AL_ERROR
}


/**
 * This function retrieves an integer property of the listener.
 * 
 * C Specification:
 * ALvoid alGetListeneri(ALenum pname,ALint *value);
 */
static jint JNICALL Java_org_lwjgl_openal_AL10_alGetListeneri (JNIEnv *env, jclass clazz, jint pname) {
	ALint value = 0;
	alGetListeneri((ALenum) pname, &value);
	CHECK_AL_ERROR
	return value;
}

/**
 * This function retrieves a floating point property of the listener.
 * 
 * C Specification:
 * ALvoid alGetListenerf(ALenum pname,ALfloat *value);
 */
static jfloat JNICALL Java_org_lwjgl_openal_AL10_alGetListenerf (JNIEnv *env, jclass clazz, jint pname) {
	ALfloat value = 0.0f;
	alGetListenerf((ALenum) pname, &value);
	CHECK_AL_ERROR
	return value;
}

/**
 * This function retrieves a floating point-vector property of the listener.
 *
 * C Specification:
 * ALvoid alGetListenerfv(ALenum pname,ALfloat *values);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalGetListenerfv (JNIEnv *env, jclass clazz, jint pname, jobject values, jint offset) {
	alGetListenerfv((ALenum) pname, offset + (ALfloat*) (*env)->GetDirectBufferAddress(env, values));
	CHECK_AL_ERROR
}

/**
 * This function generates one or more sources.
 * 
 * C Specification:
 * ALvoid alGenSources(ALsizei n,ALuint *sources);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalGenSources (JNIEnv *env, jclass clazz, jint n, jobject sources, jint offset) {
	alGenSources(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, sources));
	CHECK_AL_ERROR
}

/**
 * This function deletes one or more sources.
 *
 * C Specification:
 * ALvoid alDeleteSources(ALsizei n,ALuint *sources);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalDeleteSources (JNIEnv *env, jclass clazz, jint n, jobject sources, jint offset) {
	alDeleteSources(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, sources));
	CHECK_AL_ERROR
}

/**
 * This function tests if a source name is valid.
 *
 * C Specification:
 * Alboolean alIsSource(ALuint source);
 */
static jboolean JNICALL Java_org_lwjgl_openal_AL10_alIsSource (JNIEnv *env, jclass clazz, jint source) {
	jboolean result = (jboolean) alIsSource((ALuint) source);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function sets an integer property of a source.
 *
 * C Specification:
 * ALvoid alSourcei(ALuint source,ALenum pname,ALint value);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alSourcei (JNIEnv *env, jclass clazz, jint source, jint pname, jint value) {
	alSourcei((ALuint) source, (ALenum) pname, (ALint) value);
	CHECK_AL_ERROR
}

/**
 * This function sets a floating point property of a source.
 * 
 * C Specification:
 * ALvoid alSourcef(ALuint source,ALenum pname,ALfloat value);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alSourcef (JNIEnv *env, jclass clazz, jint source, jint pname, jfloat value) {
	alSourcef((ALuint) source, (ALenum) pname, (ALfloat) value);
	CHECK_AL_ERROR
}

/*
 * Class:     org_lwjgl_openal_AL10
 * Method:    nalSourcefv
 * Signature: (IILjava/nio/FloatBuffer;I)V
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcefv (JNIEnv *env, jclass clazz, jint source, jint pname, jobject values, jint offset) {
  alSourcefv((ALuint) source, (ALenum) pname, offset + (ALfloat*) (*env)->GetDirectBufferAddress(env, values));
	CHECK_AL_ERROR
}


/**
 * This function sets a source property requiring three floating point values.
 * C Specification:
 * ALvoid alSource3f(ALuint source,ALenum pname,ALfloat v1,ALfloat v2,ALfloat v3);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alSource3f (JNIEnv *env, jclass clazz, jint source, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alSource3f((ALuint) source, (ALenum) pname, (ALfloat) v1, (ALfloat) v2, (ALfloat) v3);
	CHECK_AL_ERROR
}


/**
 * This function retrieves an integer property of a source.
 * C Specification:
 * ALvoid alGetSourcei(ALuint source,ALenum pname,ALint *value);
 */
static jint JNICALL Java_org_lwjgl_openal_AL10_alGetSourcei (JNIEnv *env, jclass clazz, jint source, jint pname) {
	ALint value = 0;
	alGetSourcei((ALuint) source, (ALenum) pname, &value);
	CHECK_AL_ERROR
	return value;
}

/**
 * This function retrieves a floating point property of a source.
 * 
 * C Specification:
 * ALvoid alGetSourcef(ALuint source,ALenum pname,ALfloat *value);
 */
static jfloat JNICALL Java_org_lwjgl_openal_AL10_alGetSourcef (JNIEnv *env, jclass clazz, jint source, jint pname) {
	ALfloat value = 0.0f;
	alGetSourcef((ALuint) source, (ALenum) pname, &value);
	CHECK_AL_ERROR
	return value;
}

/**
 * This function retrieves a floating point-vector property of a source.
 *
 * C Specification:
 * ALvoid alGetSourcefv(ALuint source,ALenum pname,ALfloat *values);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalGetSourcefv (JNIEnv *env, jclass clazz, jint source, jint pname, jobject values, jint offset) {
	alGetSourcefv((ALuint) source, (ALenum) pname, offset + (ALfloat*) (*env)->GetDirectBufferAddress(env, values));
	CHECK_AL_ERROR
}

/**
 * This function plays a set of sources.
 *
 * C Specification:
 * ALvoid alSourcePlayv(ALsizei n,ALuint *sources);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePlayv (JNIEnv *env, jclass clazz, jint n, jobject sources, jint offset) {
	alSourcePlayv(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, sources));
	CHECK_AL_ERROR
}

/**
 * This function pauses a set of sources.
 *
 * C Specification:
 * ALvoid alSourcePausev(ALsizei n,ALuint *sources);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePausev (JNIEnv *env, jclass clazz, jint n, jobject sources, jint offset) {
	alSourcePausev(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, sources));
	CHECK_AL_ERROR
}

/**
 * This function stops a set of sources.
 * 
 * C Specification:
 * ALvoid alSourceStopv(ALsizei n,ALuint *sources);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceStopv (JNIEnv *env, jclass clazz, jint n, jobject sources, jint offset) {
	alSourceStopv(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, sources));
	CHECK_AL_ERROR
}

/**
 * This function stops a set of sources and sets all their states to AL_INITIAL.
 * 
 * C Specification:
 * ALvoid alSourceRewindv(ALsizei n,ALuint *sources);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceRewindv (JNIEnv *env, jclass clazz, jint n, jobject sources, jint offset) {
	alSourceRewindv(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, sources));
	CHECK_AL_ERROR
}

/**
 * This function plays a source.
 * 
 * C Specification:
 * ALvoid alSourcePlay(ALuint source);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alSourcePlay (JNIEnv *env, jclass clazz, jint source) {
	alSourcePlay((ALuint) source);
	CHECK_AL_ERROR
}

/*
 * This function pauses a source.
 * 
 * C Specification:
 * ALvoid alSourcePause(ALuint source);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alSourcePause (JNIEnv *env, jclass clazz, jint source) {
	alSourcePause((ALuint) source);
	CHECK_AL_ERROR
}

/**
 * This function stops a source.
 * 
 * C Specification:
 * ALvoid alSourceStop(ALuint source);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alSourceStop (JNIEnv *env, jclass clazz, jint source) {
	alSourceStop((ALuint) source);
	CHECK_AL_ERROR
}

/**
 * This function stops the source and sets its state to AL_INITIAL.
 *
 * C Specification:
 * ALvoid alSourceRewind(ALuint source);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alSourceRewind (JNIEnv *env, jclass clazz, jint source) {
	alSourceRewind((ALuint) source);
	CHECK_AL_ERROR
}

/**
 * This function generates one or more buffers.
 *
 * C Specification:
 * ALvoid alGenBuffers(ALsizei n,ALuint *buffers);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalGenBuffers (JNIEnv *env, jclass clazz, jint n, jobject buffers, jint offset) {
	alGenBuffers(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, buffers));
	CHECK_AL_ERROR
}

/**
 * This function deletes one or more buffers.
 * 
 * C Specification:
 * ALvoid alDeleteBuffers(ALsizei n,ALuint *buffers);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalDeleteBuffers (JNIEnv *env, jclass clazz, jint n, jobject buffers, jint offset) {
	alDeleteBuffers(n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, buffers));
	CHECK_AL_ERROR
}

/** 
 * This function tests if a buffer name is valid.
 * 
 * C Specification:
 * Alboolean alIsBuffer(ALuint buffer);
 */
static jboolean JNICALL Java_org_lwjgl_openal_AL10_alIsBuffer (JNIEnv *env, jclass clazz, jint buffer) {
	jboolean result = (jboolean) alIsBuffer((ALuint) buffer);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function fills a buffer with audio data.
 *
 * C Specification:
 * ALvoid alBufferData(ALuint buffer,ALenum format,ALvoid *data,ALsizei size,ALsizei freq);
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalBufferData (JNIEnv *env, jclass clazz, jint buffer, jint format, jobject data, jint offset, jint size, jint freq) {
	alBufferData(buffer, format, (void*) (offset + (ALubyte *)(*env)->GetDirectBufferAddress(env, data)), size, freq);
	CHECK_AL_ERROR
}

/**
 * This function retrieves an integer property of a buffer.
 * 
 * C Specification:
 * ALvoid alGetBufferi(ALuint buffer,ALenum pname,ALint *value);
 */
static jint JNICALL Java_org_lwjgl_openal_AL10_alGetBufferi (JNIEnv *env, jclass clazz, jint buffer, jint pname) {
	ALint value = 0;
	alGetBufferi((ALuint) buffer, (ALenum) pname, &value);
	CHECK_AL_ERROR
	return value;
}

/**
 * This function retrieves a floating point property of a buffer.
 *
 * C Specification:
 * ALvoid alGetBufferf(ALuint buffer,ALenum pname,ALfloat *value);
 */
static jfloat JNICALL Java_org_lwjgl_openal_AL10_alGetBufferf (JNIEnv *env, jclass clazz, jint buffer, jint pname) {
	ALfloat value = 0.0f;
	alGetBufferf((ALuint) buffer, (ALenum) pname, &value);
	CHECK_AL_ERROR
	return value;
}

/**
 * This function queues a set of buffers on a source.
 *
 * C Specification:
 * ALvoid alSourceQueueBuffers( ALuint source, ALsizei n, ALuint* buffers );
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceQueueBuffers (JNIEnv *env, jclass clazz, jint source, jint n, jobject buffers, jint offset) {
	alSourceQueueBuffers((ALuint) source, (ALsizei) n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, buffers));
	CHECK_AL_ERROR
}

/**
 * This function unqueues a set of buffers attached to a source.
 *
 * C Specification:
 * ALvoid alSourceUnqueueBuffers( ALuint source, ALsizei n, ALuint* buffers );
 */
static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceUnqueueBuffers (JNIEnv *env, jclass clazz, jint source, jint n, jobject buffers, jint offset) {
	alSourceUnqueueBuffers((ALuint) source, (ALsizei) n, offset + (ALuint*) (*env)->GetDirectBufferAddress(env, buffers));
	CHECK_AL_ERROR
}

/**
 * This function selects the OpenAL distance model.
 * 
 * C Specification:
 * ALvoid alDistanceModel( ALenum value );
 */

static void JNICALL Java_org_lwjgl_openal_AL10_alDistanceModel (JNIEnv *env, jclass clazz, jint value) {
	alDistanceModel((ALenum) value);
	CHECK_AL_ERROR
}

/**
 * This function selects the OpenAL Doppler factor value.
 *
 * C Specification:
 * ALvoid alDopplerFactor( ALfloat value );
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alDopplerFactor (JNIEnv *env, jclass clazz, jfloat value) {
	alDopplerFactor((ALfloat) value);
	CHECK_AL_ERROR
}

/**
 * This function selects the OpenAL Doppler velocity value.
 *
 * C Specification:
 * ALvoid alDopplerVelocity( ALfloat value );
 */
static void JNICALL Java_org_lwjgl_openal_AL10_alDopplerVelocity (JNIEnv *env, jclass clazz, jfloat value) {
	alDopplerVelocity((ALfloat) value);
	CHECK_AL_ERROR
}

/**
 * Loads the basic OpenAL functions
 *
 * @return true if all methods were loaded, false if one of the methods could not be loaded
 */
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_openal_AL10_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"alEnable", "(I)V", (void*)&Java_org_lwjgl_openal_AL10_alEnable, "alEnable", (void*)&alEnable},
		{"alDisable", "(I)V", (void*)&Java_org_lwjgl_openal_AL10_alDisable, "alDisable", (void*)&alDisable},
		{"alIsEnabled", "(I)Z", (void*)&Java_org_lwjgl_openal_AL10_alIsEnabled, "alIsEnabled", (void*)&alIsEnabled},
//		{"alHint", "(II)V", (void*)Java_org_lwjgl_openal_AL10_alHint, "alHint", (void*)alHint},
		{"alGetBoolean", "(I)Z", (void*)&Java_org_lwjgl_openal_AL10_alGetBoolean, "alGetBoolean", (void*)&alGetBoolean},
		{"alGetInteger", "(I)I", (void*)&Java_org_lwjgl_openal_AL10_alGetInteger, "alGetInteger", (void*)&alGetInteger},
		{"alGetFloat", "(I)F", (void*)&Java_org_lwjgl_openal_AL10_alGetFloat, "alGetFloat", (void*)&alGetFloat},
		{"nalGetIntegerv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalGetIntegerv, "alGetIntegerv", (void*)&alGetIntegerv},
		{"nalGetFloatv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalGetFloatv, "alGetFloatv", (void*)&alGetFloatv},
		{"alGetString", "(I)Ljava/lang/String;", (void*)&Java_org_lwjgl_openal_AL10_alGetString, "alGetString", (void*)&alGetString},
		{"alGetError", "()I", (void*)&Java_org_lwjgl_openal_AL10_alGetError, "alGetError", (void*)&alGetError},
		{"alIsExtensionPresent", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_openal_AL10_alIsExtensionPresent, "alIsExtensionPresent", (void*)&alIsExtensionPresent},
		{"alGetEnumValue", "(Ljava/lang/String;)I", (void*)&Java_org_lwjgl_openal_AL10_alGetEnumValue, "alGetEnumValue", (void*)&alGetEnumValue},
		{"alListeneri", "(II)V", (void*)&Java_org_lwjgl_openal_AL10_alListeneri, "alListeneri", (void*)&alListeneri},
		{"alListenerf", "(IF)V", (void*)&Java_org_lwjgl_openal_AL10_alListenerf, "alListenerf", (void*)&alListenerf},
		{"nalListenerfv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalListenerfv, "alListenerfv", (void*)&alListenerfv},
		{"alListener3f", "(IFFF)V", (void*)&Java_org_lwjgl_openal_AL10_alListener3f, "alListener3f", (void*)&alListener3f},
		{"alGetListeneri", "(I)I", (void*)&Java_org_lwjgl_openal_AL10_alGetListeneri, "alGetListeneri", (void*)&alGetListeneri},
		{"alGetListenerf", "(I)F", (void*)&Java_org_lwjgl_openal_AL10_alGetListenerf, "alGetListenerf", (void*)&alGetListenerf},
		{"nalGetListenerfv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalGetListenerfv, "alGetListenerfv", (void*)&alGetListenerfv},
		{"nalGenSources", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalGenSources, "alGenSources", (void*)&alGenSources},
		{"nalDeleteSources", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalDeleteSources, "alDeleteSources", (void*)&alDeleteSources},
		{"alIsSource", "(I)Z", (void*)&Java_org_lwjgl_openal_AL10_alIsSource, "alIsSource", (void*)&alIsSource},
		{"alSourcei", "(III)V", (void*)&Java_org_lwjgl_openal_AL10_alSourcei, "alSourcei", (void*)&alSourcei},
		{"alSourcef", "(IIF)V", (void*)&Java_org_lwjgl_openal_AL10_alSourcef, "alSourcef", (void*)&alSourcef},
		{"nalSourcefv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalSourcefv, "alSourcefv", (void*)&alSourcefv},
		{"alSource3f", "(IIFFF)V", (void*)&Java_org_lwjgl_openal_AL10_alSource3f, "alSource3f", (void*)&alSource3f},
		{"alGetSourcei", "(II)I", (void*)&Java_org_lwjgl_openal_AL10_alGetSourcei, "alGetSourcei", (void*)&alGetSourcei},
		{"alGetSourcef", "(II)F", (void*)&Java_org_lwjgl_openal_AL10_alGetSourcef, "alGetSourcef", (void*)&alGetSourcef},
		{"nalGetSourcefv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalGetSourcefv, "alGetSourcefv", (void*)&alGetSourcefv},
		{"alSourcePlay", "(I)V", (void*)&Java_org_lwjgl_openal_AL10_alSourcePlay, "alSourcePlay", (void*)&alSourcePlay},
		{"nalSourcePlayv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalSourcePlayv, "alSourcePlayv", (void*)&alSourcePlayv},
		{"alSourcePause", "(I)V", (void*)&Java_org_lwjgl_openal_AL10_alSourcePause, "alSourcePause", (void*)&alSourcePause},
		{"nalSourcePausev", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalSourcePausev, "alSourcePausev", (void*)&alSourcePausev},
		{"alSourceStop", "(I)V", (void*)&Java_org_lwjgl_openal_AL10_alSourceStop, "alSourceStop", (void*)&alSourceStop},
		{"nalSourceStopv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalSourceStopv, "alSourceStopv", (void*)&alSourceStopv},
		{"alSourceRewind", "(I)V", (void*)&Java_org_lwjgl_openal_AL10_alSourceRewind, "alSourceRewind", (void*)&alSourceRewind},
		{"nalSourceRewindv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalSourceRewindv, "alSourceRewindv", (void*)&alSourceRewindv},
		{"nalGenBuffers", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalGenBuffers, "alGenBuffers", (void*)&alGenBuffers},
		{"nalDeleteBuffers", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalDeleteBuffers, "alDeleteBuffers", (void*)&alDeleteBuffers},
		{"alIsBuffer", "(I)Z", (void*)&Java_org_lwjgl_openal_AL10_alIsBuffer, "alIsBuffer", (void*)&alIsBuffer},
		{"nalBufferData", "(IILjava/nio/Buffer;III)V", (void*)&Java_org_lwjgl_openal_AL10_nalBufferData, "alBufferData", (void*)&alBufferData},
		{"alGetBufferi", "(II)I", (void*)&Java_org_lwjgl_openal_AL10_alGetBufferi, "alGetBufferi", (void*)&alGetBufferi},
		{"alGetBufferf", "(II)F", (void*)&Java_org_lwjgl_openal_AL10_alGetBufferf, "alGetBufferf", (void*)&alGetBufferf},
		{"nalSourceQueueBuffers", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalSourceQueueBuffers, "alSourceQueueBuffers", (void*)&alSourceQueueBuffers},
		{"nalSourceUnqueueBuffers", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_openal_AL10_nalSourceUnqueueBuffers, "alSourceUnqueueBuffers", (void*)&alSourceUnqueueBuffers},
		{"alDistanceModel", "(I)V", (void*)&Java_org_lwjgl_openal_AL10_alDistanceModel, "alDistanceModel", (void*)&alDistanceModel},
		{"alDopplerFactor", "(F)V", (void*)&Java_org_lwjgl_openal_AL10_alDopplerFactor, "alDopplerFactor", (void*)&alDopplerFactor},
		{"alDopplerVelocity", "(F)V", (void*)&Java_org_lwjgl_openal_AL10_alDopplerVelocity, "alDopplerVelocity", (void*)&alDopplerVelocity}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extal_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif
