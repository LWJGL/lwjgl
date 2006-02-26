/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glVertexWeightfEXTPROC) (GLfloat weight);
typedef void (APIENTRY *glVertexWeightPointerEXTPROC) (GLsizei size, GLenum type, GLsizei stride, const GLvoid * pPointer);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexWeighting_nglVertexWeightfEXT(JNIEnv *env, jclass clazz, jfloat weight, jlong function_pointer) {
	glVertexWeightfEXTPROC glVertexWeightfEXT = (glVertexWeightfEXTPROC)((intptr_t)function_pointer);
	glVertexWeightfEXT(weight);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexWeighting_nglVertexWeightPointerEXT(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_position, jlong function_pointer) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glVertexWeightPointerEXTPROC glVertexWeightPointerEXT = (glVertexWeightPointerEXTPROC)((intptr_t)function_pointer);
	glVertexWeightPointerEXT(size, type, stride, pPointer_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexWeighting_nglVertexWeightPointerEXTBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jlong pPointer_buffer_offset, jlong function_pointer) {
	const GLvoid *pPointer_address = ((const GLvoid *)offsetToPointer(pPointer_buffer_offset));
	glVertexWeightPointerEXTPROC glVertexWeightPointerEXT = (glVertexWeightPointerEXTPROC)((intptr_t)function_pointer);
	glVertexWeightPointerEXT(size, type, stride, pPointer_address);
}

