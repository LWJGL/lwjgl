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
 * @author  Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
#include "org_lwjgl_openal_CoreAL.h"

/* OpenAL includes */
#include <al.h>

/**
 * This function returns the current error state and then clears the error state.
 * 
 * C Specification:
 * ALenum alGetError(ALvoid);
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_CoreAL_getError (JNIEnv *env, jobject obj) {
	return alGetError();
}
/**
 * This function retrieves an OpenAL string property.
 * 
 * C Specification:
 * ALubyte * alGetString(ALenum pname);
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_openal_CoreAL_getString (JNIEnv *env, jobject obj, jint param) {
	return (*env)->NewStringUTF(env, alGetString(param));
}

/**
 * This function generates one or more buffers.
 *
 * C Specification:
 * ALvoid alGenBuffers(ALsizei n,ALuint *buffers);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_genBuffers (JNIEnv *env, jobject obj, jint n, jintArray buffers) {
	int* array = (*env)->GetIntArrayElements(env, buffers, 0);
	alGenBuffers(n, array);
	(*env)->ReleaseIntArrayElements(env, buffers, array, 0);
}
/**
 * This function generates one or more sources.
 * 
 * C Specification:
 * ALvoid alGenSources(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_genSources (JNIEnv *env, jobject obj, jint n, jintArray sources) {
	int* array = (*env)->GetIntArrayElements(env, sources, 0);
	alGenSources(n, array);
	(*env)->ReleaseIntArrayElements(env, sources, array, 0);
}

/**
 * This function fills a buffer with audio data.
 *
 * C Specification:
 * ALvoid alBufferData(ALuint buffer,ALenum format,ALvoid *data,ALsizei size,ALsizei freq);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_bufferData (JNIEnv *env, jobject obj, jint buffer, jint format, jint data, jint size, jint freq) {
	alBufferData(buffer, format, (void**) data, size, freq);
}

/**
 * This function sets an integer property of a source.
 *
 * C Specification:
 * ALvoid alSourcei(ALuint source,ALenum pname,ALint value);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcei (JNIEnv *env, jobject obj, jint source, jint param, jint value) {
	alSourcei(source, param, value);
}

/**
 * This function plays a source.
 * 
 * C Specification:
 * ALvoid alSourcePlay(ALuint source);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourcePlay (JNIEnv *env, jobject obj, jint source) {
	alSourcePlay(source);
}

/**
 * This function stops a source.
 * 
 * C Specification:
 * ALvoid alSourceStop(ALuint source);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_sourceStop (JNIEnv *env, jobject obj, jint source) {
	alSourceStop(source);
}

/**
 * This function deletes one or more sources.
 *
 * C Specification:
 * ALvoid alDeleteSources(ALsizei n,ALuint *sources);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_deleteSources (JNIEnv *env, jobject obj, jint n, jintArray source) {
	int* array = (*env)->GetIntArrayElements(env, source, 0);
	alDeleteSources(n, array);
	(*env)->ReleaseIntArrayElements(env, source, array, 0);	
}

/**
 * This function deletes one or more buffers.
 * 
 * C Specification:
 * ALvoid alDeleteBuffers(ALsizei n,ALuint *buffers);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_CoreAL_deleteBuffers (JNIEnv *env, jobject obj, jint n, jintArray buffer) {
	int* array = (*env)->GetIntArrayElements(env, buffer, 0);
	alDeleteBuffers(n, array);
	(*env)->ReleaseIntArrayElements(env, buffer, array, 0);	
}