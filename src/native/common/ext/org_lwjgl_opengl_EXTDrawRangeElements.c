/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glDrawRangeElementsEXTPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid * pIndices);

static glDrawRangeElementsEXTPROC glDrawRangeElementsEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXT(JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject pIndices, jint pIndices_position) {
	const GLvoid *pIndices_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pIndices)) + pIndices_position));
	glDrawRangeElementsEXT(mode, start, end, count, type, pIndices_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXTBO(JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jint pIndices_buffer_offset) {
	const GLvoid *pIndices_address = ((const GLvoid *)offsetToPointer(pIndices_buffer_offset));
	glDrawRangeElementsEXT(mode, start, end, count, type, pIndices_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTDrawRangeElements_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglDrawRangeElementsEXT", "(IIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXT, "glDrawRangeElementsEXT", (void *)&glDrawRangeElementsEXT},
		{"nglDrawRangeElementsEXTBO", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXTBO, "glDrawRangeElementsEXT", (void *)&glDrawRangeElementsEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
