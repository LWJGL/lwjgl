/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glPixelDataRangeNVPROC) (GLenum target, GLsizei length, GLvoid * data);
typedef void (APIENTRY *glFlushPixelDataRangeNVPROC) (GLenum target);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPixelDataRange_nglPixelDataRangeNV(JNIEnv *env, jclass clazz, jint target, jint length, jobject data, jint data_position, jlong function_pointer) {
	GLvoid *data_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glPixelDataRangeNVPROC glPixelDataRangeNV = (glPixelDataRangeNVPROC)((intptr_t)function_pointer);
	glPixelDataRangeNV(target, length, data_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPixelDataRange_nglFlushPixelDataRangeNV(JNIEnv *env, jclass clazz, jint target, jlong function_pointer) {
	glFlushPixelDataRangeNVPROC glFlushPixelDataRangeNV = (glFlushPixelDataRangeNVPROC)((intptr_t)function_pointer);
	glFlushPixelDataRangeNV(target);
}

