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
 * This is the actual JNI implementation of the OpenAL utility library. 
 * It handles whatever is needed for proper OpenAL support via using Java.
 * 
 * @author  Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
#include "org_lwjgl_openal_ALUT.h"
/* OpenAL includes */
#include "extal.h"

#include "checkALerror.h"


#include <stdlib.h>

/*
 * Class:     org_lwjgl_openal_ALUT
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_openal_ALUT_nCreate (JNIEnv *env, jobject obj) {
	return true;
}

/*
 * Class:     org_lwjgl_openal_ALUT
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALUT_nDestroy (JNIEnv *env, jobject obj) {
}

/**
 * This function initializes OpenAL.
 * 
 * C Specification:
 * void alutInit(int *argc, char *argv[]);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALUT_init (JNIEnv *env, jobject obj, jobjectArray jargv) {
	/* obtain the size the array  */
	jsize argc = env->GetArrayLength(jargv);

	/* Declare a char array for argv */
	const char* argv[128];
	int i;

	for (i=0;i<argc;i++) {
		/* obtain the current object from the object array */
		jstring string = (jstring) env->GetObjectArrayElement(jargv, i);

		/* Convert the object just obtained into a String */
		const char *str = env->GetStringUTFChars(string, 0);

		/* Build the argv array */
		argv[i] = str;

		/* Free up memory to prevent memory leaks */
		env->ReleaseStringUTFChars(string, str); 
	}

	/* Increment argc to adjust the difference between Java and C arguments */
	argc++;

	/* call the actual implementation */
	alutInit((ALint*) &argc,(char**) argv);
	CHECK_AL_ERROR
}
/*
 * This function loads a WAV file into memory from a file.
 *
 * C Specification:
 * ALboolean alutLoadWAVFile(const char *fname, ALsizei *format, ALsizei *size, ALsizei *bits,
 * ALsizei *freq, ALboolean *loop );
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_openal_ALUT_loadWAVFile (JNIEnv *env, jobject obj, jstring file) {
	
	/*
	 * NOTE: Since Java doesn't support modification of supplied 
	 * variables (pass by value). We will return a class that 
	 * holds what is needed to unload the file again.
	 * The data field contains the offset at whcih the data
	 * segment begins (ie. a pointer). This will *not* work
	 * on 64 bit platforms, since we use an jint for this.
	 */

	/* actual file info object */
	jobject alutLoadWAVFile_object = NULL;

	/* class type to find */
	jclass alutLoadWAVFile_class = NULL;

	/* method id - will be set to constructor of alutLoadWAVData */
	jmethodID methodID = NULL;
	
	/* sound data vars */
	jint format, size, freq;
	jboolean loop;
	void* data;
	ALbyte* filename = (ALbyte*) (env->GetStringUTFChars(file, 0));
	
	/* load wave file */
	alutLoadWAVFile(filename, (ALenum*) &format, (void**) &data, (ALsizei*) &size, (ALsizei*) &freq, (ALboolean*) &loop);

	/* get class */
	alutLoadWAVFile_class = env->FindClass("org/lwjgl/openal/ALUTLoadWAVData");

	/* get constructor */
	methodID = env->GetMethodID(alutLoadWAVFile_class, "<init>", "(IIIIZ)V");

	/* create object */
	alutLoadWAVFile_object = env->NewObject(alutLoadWAVFile_class, methodID, format, (int) data, size, freq, loop);
	
	/* release chars */
	env->ReleaseStringUTFChars((jstring)filename, 0);

	CHECK_AL_ERROR
	return alutLoadWAVFile_object;
}

/**
 * This function loads a WAV file into memory from another memory location.
 *
 * C Specification:
 * ALvoid alutLoadWAVMemory(ALbyte *memory,ALenum *format,ALvoid **data,ALsizei
 * *size,ALsizei *freq,ALboolean *loop)
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_openal_ALUT_loadWAVMemory (JNIEnv *env, jobject obj, jint buffer) {
	
	/*
	 * NOTE: Since Java doesn't support modification of supplied 
	 * variables (pass by value). We will return a class that 
	 * holds what is needed to unload the file again.
	 * The data field contains the offset at whcih the data
	 * segment begins (ie. a pointer). This will *not* work
	 * on 64 bit platforms, since we use an jint for this.
	 */

	/* actual file info object */
	jobject alutLoadWAVFile_object = NULL;

	/* class type to find */
	jclass alutLoadWAVFile_class = NULL;

	/* method id - will be set to constructor of alutLoadWAVData */
	jmethodID methodID = NULL;
	
	/* sound data vars */
	jint format, size, freq;
	jboolean loop;
	void* data;

	/* load wave from mem */
	alutLoadWAVMemory((ALbyte*) buffer, (ALenum*) &format, (void**) &data, (ALsizei*) &size, (ALsizei*) &freq, (ALboolean*) &loop);

	/* get class */
	alutLoadWAVFile_class = env->FindClass("org/lwjgl/openal/ALUTLoadWAVData");

	/* get constructor */
	methodID = env->GetMethodID(alutLoadWAVFile_class, "<init>", "(IIIIZ)V");

	/* create object */
	alutLoadWAVFile_object = env->NewObject(alutLoadWAVFile_class, methodID, format, (int) data, size, freq, loop);

	CHECK_AL_ERROR
	return alutLoadWAVFile_object;
}

/**
 * This function unloads a WAV file from memory and is normally used after copying the data into a buffer
 * after an alutLoad* function.
 * 
 * C Specification:
 * ALvoid alutUnloadWAV(ALenum format, ALvoid *data, ALsizei size, ALsizei freq)
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALUT_unloadWAV (JNIEnv *env, jobject obj, jint format, jint data, jint size, jint freq) {
	alutUnloadWAV(format, (void**) data, size, freq);
	CHECK_AL_ERROR
}

/**
 * This function exits OpenAL.
 * 
 * C Specification:
 * void alutExit(ALvoid);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALUT_exit (JNIEnv *env, jobject obj) {
	alutExit();
}
