/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include <inttypes.h>
#include "extgl.h"

typedef void (APIENTRY *glFogCoordPointerEXTPROC) (GLenum type, GLsizei stride, const GLvoid * data);
typedef void (APIENTRY *glFogCoordfEXTPROC) (GLfloat coord);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFogCoord_nglFogCoordPointerEXT(JNIEnv *env, jclass clazz, jint type, jint stride, jobject data, jint data_position, jlong function_pointer) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glFogCoordPointerEXTPROC glFogCoordPointerEXT = (glFogCoordPointerEXTPROC)((intptr_t)function_pointer);
	glFogCoordPointerEXT(type, stride, data_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFogCoord_nglFogCoordPointerEXTBO(JNIEnv *env, jclass clazz, jint type, jint stride, jint data_buffer_offset, jlong function_pointer) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glFogCoordPointerEXTPROC glFogCoordPointerEXT = (glFogCoordPointerEXTPROC)((intptr_t)function_pointer);
	glFogCoordPointerEXT(type, stride, data_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFogCoord_nglFogCoordfEXT(JNIEnv *env, jclass clazz, jfloat coord, jlong function_pointer) {
	glFogCoordfEXTPROC glFogCoordfEXT = (glFogCoordfEXTPROC)((intptr_t)function_pointer);
	glFogCoordfEXT(coord);
}

