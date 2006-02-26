/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glElementPointerATIPROC) (GLenum type, const GLvoid * pPointer);
typedef void (APIENTRY *glDrawElementArrayATIPROC) (GLenum mode, GLsizei count);
typedef void (APIENTRY *glDrawRangeElementArrayATIPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATI(JNIEnv *env, jclass clazz, jint type, jobject pPointer, jint pPointer_position, jlong function_pointer) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glElementPointerATIPROC glElementPointerATI = (glElementPointerATIPROC)((intptr_t)function_pointer);
	glElementPointerATI(type, pPointer_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATIBO(JNIEnv *env, jclass clazz, jint type, jlong pPointer_buffer_offset, jlong function_pointer) {
	const GLvoid *pPointer_address = ((const GLvoid *)offsetToPointer(pPointer_buffer_offset));
	glElementPointerATIPROC glElementPointerATI = (glElementPointerATIPROC)((intptr_t)function_pointer);
	glElementPointerATI(type, pPointer_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglDrawElementArrayATI(JNIEnv *env, jclass clazz, jint mode, jint count, jlong function_pointer) {
	glDrawElementArrayATIPROC glDrawElementArrayATI = (glDrawElementArrayATIPROC)((intptr_t)function_pointer);
	glDrawElementArrayATI(mode, count);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglDrawRangeElementArrayATI(JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jlong function_pointer) {
	glDrawRangeElementArrayATIPROC glDrawRangeElementArrayATI = (glDrawRangeElementArrayATIPROC)((intptr_t)function_pointer);
	glDrawRangeElementArrayATI(mode, start, end, count);
}

