/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glClampColorARBPROC) (GLenum target, GLenum clamp);

static glClampColorARBPROC glClampColorARB;

static void JNICALL Java_org_lwjgl_opengl_ARBColorBufferFloat_glClampColorARB(JNIEnv *env, jclass clazz, jint target, jint clamp) {
	glClampColorARB(target, clamp);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBColorBufferFloat_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glClampColorARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBColorBufferFloat_glClampColorARB, "glClampColorARB", (void *)&glClampColorARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
