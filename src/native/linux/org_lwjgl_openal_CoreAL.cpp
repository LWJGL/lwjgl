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
 * This is the actual JNI implementation of the OpenAL core. It handles 
 * whatever is needed for proper OpenAL support via using Java.
 * 
 * @author  elias_naur <elias_naur@matzon.dk>
 * @version $Revision$
 */
#include "org_lwjgl_openal_CoreAL.h"
#include "checkALerror.h"

/* OpenAL includes */
#include <AL/al.h>

/**
 * This function enables a feature of the OpenAL driver.
 *
 * C Specification:
 * ALvoid alEnable(ALenum capability);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_enable (JNIEnv *env, jobject obj, jint capability) {
	alEnable((ALenum) capability);
	CHECK_AL_ERROR
}

/**
 * This function disables a feature of the OpenAL driver.
 *
 * C Specification:
 * ALvoid alDisable(ALenum capability);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_disable (JNIEnv *env, jobject obj, jint capability) {
	alDisable((ALenum) capability);
	CHECK_AL_ERROR
}

/**
 * This function returns a boolean indicating if a specific feature is enabled in the OpenAL driver.
 * 
 * C Specification:
 * Alboolean alIsEnabled(ALenum capability);
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_CoreAL_isEnabled (JNIEnv *env, jobject obj, jint capability) {
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
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_hint (JNIEnv *env, jobject obj, jint target, jint mode) {
	//alHint((ALint)target, (ALint)mode);
	//cannot link with above statement
	return;
}


/**
 * This function returns a boolean OpenAL state.
 * 
 * C Specification:
 * Alboolean alGetBoolean(ALenum pname);
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_CoreAL_getBoolean (JNIEnv *env, jobject obj, jint pname) {
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
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_CoreAL_getInteger (JNIEnv *env, jobject obj, jint pname) {
	jint result = (jint) alGetInteger((ALenum) pname);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function returns a floating point OpenAL state.
 * 
 * C Specification:
 * ALfloat alGetFloat(ALenum pname);
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_openal_CoreAL_getFloat (JNIEnv *env, jobject obj, jint pname) {
	jfloat result = (jfloat) alGetFloat((ALenum) pname);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function returns a double precision floating point OpenAL state.
 *
 * C Specification:
 * Aldouble alGetDouble(ALenum pname);
 */
JNIEXPORT jdouble JNICALL Java_org_lwjgl_openal_CoreAL_getDouble (JNIEnv *env, jobject obj, jint pname) {
	jdouble result = (jdouble) alGetDouble((ALenum) pname);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function retrieves a boolean OpenAL state.
 * 
 * C Specification:
 * ALvoid alGetBooleanv(ALenum pname,ALboolean *data);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getBooleanv (JNIEnv *env, jobject obj, jint pname, jint data) {
	alGetBooleanv((ALenum) pname, (ALboolean*) data);
	CHECK_AL_ERROR
}

/**
 * This function retrieves an integer OpenAL state.
 * 
 * C Specification:
 * ALvoid alGetIntegerv(ALenum pname,ALint *data);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getIntegerv (JNIEnv *env, jobject obj, jint pname, jint data) {
	alGetIntegerv((ALenum) pname, (ALint*) data);
	CHECK_AL_ERROR
}

/** 
 * This function retrieves a floating point OpenAL state.
 * 
 * C Specification:
 * ALvoid alGetFloatv(ALenum pname,ALfloat *data);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getFloatv (JNIEnv *env, jobject obj, jint pname, jint data) {
	alGetFloatv((ALenum) pname, (ALfloat*) data);
	CHECK_AL_ERROR
}

/**
 * This function retrieves a double precision floating point OpenAL state.
 * 
 * C Specification:
 * ALvoid alGetDoublev(ALenum pname,ALdouble *data);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getDoublev (JNIEnv *env, jobject obj, jint pname, jint data) {
	alGetDoublev((ALenum) pname, (ALdouble*) data);
	CHECK_AL_ERROR
}

/**
 * This function retrieves an OpenAL string property.
 * 
 * C Specification:
 * ALubyte * alGetString(ALenum pname);
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_openal_CoreAL_getString (JNIEnv *env, jobject obj, jint param) {
	jstring string = (jstring) env->NewStringUTF((const char*) alGetString((ALenum)param));

	CHECK_AL_ERROR
	return string;
}

/**
 * This function returns the current error state and then clears the error state.
 * 
 * C Specification:
 * ALenum alGetError(ALvoid);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_CoreAL_getError (JNIEnv *env, jobject obj) {
	jint result = (jint) alGetError();

	CHECK_AL_ERROR
	return result;
}

/**
 * This function tests if a specific extension is available for the OpenAL driver.
 * 
 * C Specification:
 * ALboolean alIsExtensionPresent(ALubyte *extName);
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_CoreAL_isExtensionPresent (JNIEnv *env, jobject obj, jstring fname) {
	ALubyte* functionname = (ALubyte*) (env->GetStringUTFChars(fname, 0));
	jboolean result = (jboolean) alIsExtensionPresent(functionname);
	env->ReleaseStringUTFChars((jstring)functionname, 0);
	
	CHECK_AL_ERROR
	return result;
}

/**
 * This function returns the enumeration value of an OpenAL enum described by a string.
 * 
 * C Specification:
 * ALenum alGetEnumValue(ALubyte *enumName);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_CoreAL_getEnumValue (JNIEnv *env, jobject obj, jstring ename) {
	ALubyte* functionname = (ALubyte*) (env->GetStringUTFChars(ename, 0));
	jint result = (jint) alGetEnumValue(functionname);
	env->ReleaseStringUTFChars((jstring)functionname, 0);

	CHECK_AL_ERROR
	return result;
}

/**
 * This function sets an integer property of the listener.
 * 
 * C Specification:
 * ALvoid alListeneri(ALenum pname,ALint value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_listeneri (JNIEnv *env, jobject obj, jint pname, jint value) {
	alListeneri((ALenum) pname, (ALint) value);
	CHECK_AL_ERROR
}

/**
 * This function sets a floating point property for the listener.
 * 
 * C Specification:
 * ALvoid alListenerf(ALenum pname,ALfloat value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_listenerf (JNIEnv *env, jobject obj, jint pname, jfloat value) {
	alListenerf((ALenum) pname, (ALfloat) value);
	CHECK_AL_ERROR
}

/**
 * This function sets a floating point property for the listener.
 *
 * C Specification:
 * ALvoid alListener3f(ALenum pname,ALfloat v1,ALfloat v2,ALfloat v3);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_listener3f (JNIEnv *env, jobject obj, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alListener3f((ALenum) pname, (ALfloat) v1, (ALfloat) v2, (ALfloat) v3);
	CHECK_AL_ERROR
}

/**
 * This function sets a floating point-vector property of the listener.
 * 
 * C Specification:
 * ALvoid alListenerfv(ALenum pname,ALfloat *values); 
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_listenerfv (JNIEnv *env, jobject obj, jint pname, jint values) {
	alListenerfv((ALenum) pname, (ALfloat*) values);
	CHECK_AL_ERROR
}

/**
 * This function retrieves an integer property of the listener.
 * 
 * C Specification:
 * ALvoid alGetListeneri(ALenum pname,ALint *value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getListeneri (JNIEnv *env, jobject obj, jint pname, jint value) {
	alGetListeneri((ALenum) pname, (ALint*) value);
	CHECK_AL_ERROR
}

/**
 * This function retrieves a floating point property of the listener.
 * 
 * C Specification:
 * ALvoid alGetListenerf(ALenum pname,ALfloat *value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getListenerf (JNIEnv *env, jobject obj, jint pname, jint value) {
	alGetListenerf((ALenum) pname, (ALfloat*) value);
	CHECK_AL_ERROR
}

/**
 * This function retrieves a set of three floating point values from a property of the listener.
 *
 * C Specification:
 * ALvoid alGetListener3f(ALenum pname,ALfloat *v1,ALfloat *v2,ALfloat *v3);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getListener3f (JNIEnv *env, jobject obj, jint pname, jint v1, jint v2, jint v3) {
	alGetListener3f((ALenum) pname, (ALfloat*) v1, (ALfloat*) v2, (ALfloat*) v3);
	CHECK_AL_ERROR
}

/**
 * This function retrieves a floating point-vector property of the listener.
 *
 * C Specification:
 * ALvoid alGetListenerfv(ALenum pname,ALfloat *values);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getListenerfv (JNIEnv *env, jobject obj, jint pname, jint values) {
	alGetListenerfv((ALenum) pname, (ALfloat*) values);
	CHECK_AL_ERROR
}

/**
 * This function generates one or more sources.
 * 
 * C Specification:
 * ALvoid alGenSources(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_genSources (JNIEnv *env, jobject obj, jint n, jint sources) {
	alGenSources(n, (ALuint*) sources);
	CHECK_AL_ERROR
}

/**
 * This function deletes one or more sources.
 *
 * C Specification:
 * ALvoid alDeleteSources(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_deleteSources (JNIEnv *env, jobject obj, jint n, jint sources) {
	alDeleteSources(n, (ALuint*) sources);
	CHECK_AL_ERROR
}

/**
 * This function tests if a source name is valid.
 *
 * C Specification:
 * Alboolean alIsSource(ALuint source);
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_CoreAL_isSource (JNIEnv *env, jobject obj, jint source) {
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
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcei (JNIEnv *env, jobject obj, jint source, jint pname, jint value) {
	alSourcei((ALuint) source, (ALenum) pname, (ALint) value);
	CHECK_AL_ERROR
}

/**
 * This function sets a floating point property of a source.
 * 
 * C Specification:
 * ALvoid alSourcef(ALuint source,ALenum pname,ALfloat value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcef (JNIEnv *env, jobject obj, jint source, jint pname, jfloat value) {
	alSourcef((ALuint) source, (ALenum) pname, (ALfloat) value);
	CHECK_AL_ERROR
}

/**
 * This function sets a source property requiring three floating point values.
 * C Specification:
 * ALvoid alSource3f(ALuint source,ALenum pname,ALfloat v1,ALfloat v2,ALfloat v3);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_source3f (JNIEnv *env, jobject obj, jint source, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alSource3f((ALuint) source, (ALenum) pname, (ALfloat) v1, (ALfloat) v2, (ALfloat) v3);
	CHECK_AL_ERROR
}

/**
 * This function sets a floating point-vector property of a source.
 *
 * C Specification:
 * ALvoid alSourcefv(ALuint source,ALenum pname,ALfloat *values);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcefv (JNIEnv *env, jobject obj, jint source, jint pname, jint values) {
	alSourcefv((ALuint) source, (ALenum) pname, (ALfloat*) values);
	CHECK_AL_ERROR
}

/**
 * This function retrieves an integer property of a source.
 * C Specification:
 * ALvoid alGetSourcei(ALuint source,ALenum pname,ALint *value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getSourcei (JNIEnv *env, jobject obj, jint source, jint pname, jint value) {
	alGetSourcei((ALuint) source, (ALenum) pname, (ALint*) value);
	CHECK_AL_ERROR
}

/**
 * This function retrieves a floating point property of a source.
 * 
 * C Specification:
 * ALvoid alGetSourcef(ALuint source,ALenum pname,ALfloat *value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getSourcef (JNIEnv *env, jobject obj, jint source, jint pname, jint value) {
	alGetSourcef((ALuint) source, (ALenum) pname, (ALfloat*) value);
	CHECK_AL_ERROR
}

/**
 * This function retrieves a floating point-vector property of a source.
 *
 * C Specification:
 * ALvoid alGetSourcefv(ALuint source,ALenum pname,ALfloat *values);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getSourcefv (JNIEnv *env, jobject obj, jint source, jint pname, jint values) {
	alGetSourcefv((ALuint) source, (ALenum) pname, (ALfloat*) values);
	CHECK_AL_ERROR
}

/**
 * This function plays a set of sources.
 *
 * C Specification:
 * ALvoid alSourcePlayv(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcePlayv (JNIEnv *env, jobject obj, jint n, jint sources) {
	alSourcePlayv(n, (ALuint*) sources);
	CHECK_AL_ERROR
}

/**
 * This function pauses a set of sources.
 *
 * C Specification:
 * ALvoid alSourcePausev(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcePausev (JNIEnv *env, jobject obj, jint n, jint sources) {
	alSourcePausev(n, (ALuint*) sources);
	CHECK_AL_ERROR
}

/**
 * This function stops a set of sources.
 * 
 * C Specification:
 * ALvoid alSourceStopv(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourceStopv (JNIEnv *env, jobject obj, jint n, jint sources) {
	alSourceStopv(n, (ALuint*) sources);
	CHECK_AL_ERROR
}

/**
 * This function stops a set of sources and sets all their states to AL_INITIAL.
 * 
 * C Specification:
 * ALvoid alSourceRewindv(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourceRewindv (JNIEnv *env, jobject obj, jint n, jint sources) {
	alSourceRewindv(n, (ALuint*) sources);
	CHECK_AL_ERROR
}

/**
 * This function plays a source.
 * 
 * C Specification:
 * ALvoid alSourcePlay(ALuint source);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcePlay (JNIEnv *env, jobject obj, jint source) {
	alSourcePlay((ALuint) source);
	CHECK_AL_ERROR
}

/*
 * This function pauses a source.
 * 
 * C Specification:
 * ALvoid alSourcePause(ALuint source);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcePause (JNIEnv *env, jobject obj, jint source) {
	alSourcePause((ALuint) source);
	CHECK_AL_ERROR
}

/**
 * This function stops a source.
 * 
 * C Specification:
 * ALvoid alSourceStop(ALuint source);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourceStop (JNIEnv *env, jobject obj, jint source) {
	alSourceStop((ALuint) source);
	CHECK_AL_ERROR
}

/**
 * This function stops the source and sets its state to AL_INITIAL.
 *
 * C Specification:
 * ALvoid alSourceRewind(ALuint source);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourceRewind (JNIEnv *env, jobject obj, jint source) {
	alSourceRewind((ALuint) source);
	CHECK_AL_ERROR
}

/**
 * This function generates one or more buffers.
 *
 * C Specification:
 * ALvoid alGenBuffers(ALsizei n,ALuint *buffers);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_genBuffers (JNIEnv *env, jobject obj, jint n, jint buffers) {
	alGenBuffers(n, (ALuint*) buffers);
	CHECK_AL_ERROR
}

/**
 * This function deletes one or more buffers.
 * 
 * C Specification:
 * ALvoid alDeleteBuffers(ALsizei n,ALuint *buffers);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_deleteBuffers (JNIEnv *env, jobject obj, jint n, jint buffers) {
	alDeleteBuffers(n, (ALuint*) buffers);
	CHECK_AL_ERROR
}

/** 
 * This function tests if a buffer name is valid.
 * 
 * C Specification:
 * Alboolean alIsBuffer(ALuint buffer);
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_CoreAL_isBuffer (JNIEnv *env, jobject obj, jint buffer) {
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
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_bufferData (JNIEnv *env, jobject obj, jint buffer, jint format, jint data, jint size, jint freq) {
	alBufferData(buffer, format, (void**) data, size, freq);
	CHECK_AL_ERROR
}

/**
 * This function retrieves an integer property of a buffer.
 * 
 * C Specification:
 * ALvoid alGetBufferi(ALuint buffer,ALenum pname,ALint *value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getBufferi (JNIEnv *env, jobject obj, jint buffer, jint pname, jint value) {
	alGetBufferi((ALuint) buffer, (ALenum) pname, (ALint*) value);
	CHECK_AL_ERROR
}

/**
 * This function retrieves a floating point property of a buffer.
 *
 * C Specification:
 * ALvoid alGetBufferf(ALuint buffer,ALenum pname,ALfloat *value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_getBufferf (JNIEnv *env, jobject obj, jint buffer, jint pname, jint value) {
	alGetBufferf((ALuint) buffer, (ALenum) pname, (ALfloat*) value);
	CHECK_AL_ERROR
}

/**
 * This function queues a set of buffers on a source.
 *
 * C Specification:
 * ALvoid alSourceQueueBuffers( ALuint source, ALsizei n, ALuint* buffers );
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourceQueueBuffers (JNIEnv *env, jobject obj, jint source, jint n, jint buffers) {
	alSourceQueueBuffers((ALuint) source, (ALsizei) n, (ALuint*) buffers);
	CHECK_AL_ERROR
}

/**
 * This function unqueues a set of buffers attached to a source.
 *
 * C Specification:
 * ALvoid alSourceUnqueueBuffers( ALuint source, ALsizei n, ALuint* buffers );
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourceUnqueueBuffers (JNIEnv *env, jobject obj, jint source, jint n, jint buffers) {
	alSourceUnqueueBuffers((ALuint) source, (ALsizei) n, (ALuint*) buffers);
	CHECK_AL_ERROR
}

/**
 * This function selects the OpenAL distance model.
 * 
 * C Specification:
 * ALvoid alDistanceModel( ALenum value );
 */

JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_distanceModel (JNIEnv *env, jobject obj, jint value) {
	alDistanceModel((ALenum) value);
	CHECK_AL_ERROR
}

/**
 * This function selects the OpenAL Doppler factor value.
 *
 * C Specification:
 * ALvoid alDopplerFactor( ALfloat value );
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_dopplerFactor (JNIEnv *env, jobject obj, jfloat value) {
	alDopplerFactor((ALfloat) value);
	CHECK_AL_ERROR
}

/**
 * This function selects the OpenAL Doppler velocity value.
 *
 * C Specification:
 * ALvoid alDopplerVelocity( ALfloat value );
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_dopplerVelocity (JNIEnv *env, jobject obj, jfloat value) {
	alDopplerVelocity((ALfloat) value);
	CHECK_AL_ERROR
}
