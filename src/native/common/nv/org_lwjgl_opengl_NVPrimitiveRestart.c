/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include <inttypes.h>
#include "extgl.h"

typedef void (APIENTRY *glPrimitiveRestartIndexNVPROC) (GLuint index);
typedef void (APIENTRY *glPrimitiveRestartNVPROC) ();

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPrimitiveRestart_nglPrimitiveRestartIndexNV(JNIEnv *env, jclass clazz, jint index, jlong function_pointer) {
	glPrimitiveRestartIndexNVPROC glPrimitiveRestartIndexNV = (glPrimitiveRestartIndexNVPROC)((intptr_t)function_pointer);
	glPrimitiveRestartIndexNV(index);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPrimitiveRestart_nglPrimitiveRestartNV(JNIEnv *env, jclass clazz, jlong function_pointer) {
	glPrimitiveRestartNVPROC glPrimitiveRestartNV = (glPrimitiveRestartNVPROC)((intptr_t)function_pointer);
	glPrimitiveRestartNV();
}

