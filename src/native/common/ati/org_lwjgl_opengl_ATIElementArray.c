/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glDrawRangeElementArrayATIPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count);
typedef void (APIENTRY *glDrawElementArrayATIPROC) (GLenum mode, GLsizei count);
typedef void (APIENTRY *glElementPointerATIPROC) (GLenum type, const GLvoid * pPointer);

static glDrawRangeElementArrayATIPROC glDrawRangeElementArrayATI;
static glDrawElementArrayATIPROC glDrawElementArrayATI;
static glElementPointerATIPROC glElementPointerATI;

static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_glDrawRangeElementArrayATI(JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count) {
	glDrawRangeElementArrayATI(mode, start, end, count);
}

static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_glDrawElementArrayATI(JNIEnv *env, jclass clazz, jint mode, jint count) {
	glDrawElementArrayATI(mode, count);
}

static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATI(JNIEnv *env, jclass clazz, jint type, jobject pPointer, jint pPointer_position) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glElementPointerATI(type, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATIBO(JNIEnv *env, jclass clazz, jint type, jint pPointer_buffer_offset) {
	const GLvoid *pPointer_address = ((const GLvoid *)offsetToPointer(pPointer_buffer_offset));
	glElementPointerATI(type, pPointer_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIElementArray_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glDrawRangeElementArrayATI", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ATIElementArray_glDrawRangeElementArrayATI, "glDrawRangeElementArrayATI", (void *)&glDrawRangeElementArrayATI},
		{"glDrawElementArrayATI", "(II)V", (void *)&Java_org_lwjgl_opengl_ATIElementArray_glDrawElementArrayATI, "glDrawElementArrayATI", (void *)&glDrawElementArrayATI},
		{"nglElementPointerATI", "(ILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATI, "glElementPointerATI", (void *)&glElementPointerATI},
		{"nglElementPointerATIBO", "(II)V", (void *)&Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATIBO, "glElementPointerATI", (void *)&glElementPointerATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
