/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glUnmapObjectBufferATIPROC) (GLuint buffer);
typedef GLvoid * (APIENTRY *glMapObjectBufferATIPROC) (GLuint buffer);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIMapObjectBuffer_nglUnmapObjectBufferATI(JNIEnv *env, jclass clazz, jint buffer, jlong function_pointer) {
	glUnmapObjectBufferATIPROC glUnmapObjectBufferATI = (glUnmapObjectBufferATIPROC)((intptr_t)function_pointer);
	glUnmapObjectBufferATI(buffer);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_ATIMapObjectBuffer_nglMapObjectBufferATI(JNIEnv *env, jclass clazz, jint buffer, jint result_size, jobject old_buffer, jlong function_pointer) {
	glMapObjectBufferATIPROC glMapObjectBufferATI = (glMapObjectBufferATIPROC)((intptr_t)function_pointer);
	GLvoid * __result = glMapObjectBufferATI(buffer);
	return safeNewBufferCached(env, __result, result_size, old_buffer);
}

