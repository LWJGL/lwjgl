/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glUnlockArraysEXTPROC) ();
typedef void (APIENTRY *glLockArraysEXTPROC) (GLint first, GLsizei count);

static glUnlockArraysEXTPROC glUnlockArraysEXT;
static glLockArraysEXTPROC glLockArraysEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTCompiledVertexArray_glUnlockArraysEXT(JNIEnv *env, jclass clazz) {
	glUnlockArraysEXT();
}

static void JNICALL Java_org_lwjgl_opengl_EXTCompiledVertexArray_glLockArraysEXT(JNIEnv *env, jclass clazz, jint first, jint count) {
	glLockArraysEXT(first, count);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTCompiledVertexArray_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glUnlockArraysEXT", "()V", (void *)&Java_org_lwjgl_opengl_EXTCompiledVertexArray_glUnlockArraysEXT, "glUnlockArraysEXT", (void *)&glUnlockArraysEXT},
		{"glLockArraysEXT", "(II)V", (void *)&Java_org_lwjgl_opengl_EXTCompiledVertexArray_glLockArraysEXT, "glLockArraysEXT", (void *)&glLockArraysEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
