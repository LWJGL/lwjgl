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
#include <alut.h>

/**
 * This function initializes OpenAL.
 * 
 * C Specification:
 * void alutInit(int *argc, char *argv[]);
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALUT_init (JNIEnv *env, jobject obj, jobjectArray jargv) {
	/* obtain the size the array  */
	jsize argc = (*env)->GetArrayLength(env, jargv);

	/* Declare a char array for argv */
	const char* argv[128];
	int i;

	for (i=0;i<argc;i++) {
		/* obtain the current object from the object array */
		jobject string = (*env)->GetObjectArrayElement(env, jargv, i);

		/* Convert the object just obtained into a String */
		const char *str = (*env)->GetStringUTFChars(env, string, 0);

		/* Build the argv array */
		argv[i] = str;

		/* Free up memory to prevent memory leaks */
		(*env)->ReleaseStringUTFChars(env, string, str); 
	}

	/* Increment argc to adjust the difference between Java and C arguments */
	argc++;

	/* call the actual implementation */
	alutInit(&((int)argc),(char**) argv);
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

	/* method id - will be set to constructor of alutLoadWAVFile */
	jmethodID methodID = NULL;
	
	/* sound data vars */
	jint format, size, freq;
	jboolean loop;
	void* data;
	ALbyte* filename = (ALbyte*) ((*env)->GetStringUTFChars(env, file, 0));
	
	/* load wave file */
	alutLoadWAVFile(filename, &format, (void**) &data, &size, &freq, &loop);

	/* get class */
	alutLoadWAVFile_class = (*env)->FindClass(env, "org/lwjgl/openal/ALUTLoadWAVFile");

	/* get constructor */
	methodID = (*env)->GetMethodID(env, alutLoadWAVFile_class, "<init>", "(IIIIZ)V");

	/* create object */
	alutLoadWAVFile_object = (*env)->NewObject(env, alutLoadWAVFile_class, methodID, format, (int) data, size, freq, loop);

	return alutLoadWAVFile_object;
}
/**
 * This function loads a WAV file into memory from another memory location.
 *
 * C Specification:
 * ALvoid alutLoadWAVMemory(ALbyte *memory,ALenum *format,ALvoid **data,ALsizei
 * *size,ALsizei *freq,ALboolean *loop)
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_ALUT_unloadWAV (JNIEnv *env, jobject obj, jint format, jint data, jint size, jint freq) {
	alutUnloadWAV(format, (void**) data, size, freq);
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