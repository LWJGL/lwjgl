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