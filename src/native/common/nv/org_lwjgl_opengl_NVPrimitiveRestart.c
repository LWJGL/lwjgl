/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glPrimitiveRestartIndexNVPROC) (GLuint index);
typedef void (APIENTRY *glPrimitiveRestartNVPROC) ();

static glPrimitiveRestartIndexNVPROC glPrimitiveRestartIndexNV;
static glPrimitiveRestartNVPROC glPrimitiveRestartNV;

static void JNICALL Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartIndexNV(JNIEnv *env, jclass clazz, jint index) {
	glPrimitiveRestartIndexNV(index);
}

static void JNICALL Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartNV(JNIEnv *env, jclass clazz) {
	glPrimitiveRestartNV();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPrimitiveRestart_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glPrimitiveRestartIndexNV", "(I)V", (void *)&Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartIndexNV, "glPrimitiveRestartIndexNV", (void *)&glPrimitiveRestartIndexNV},
		{"glPrimitiveRestartNV", "()V", (void *)&Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartNV, "glPrimitiveRestartNV", (void *)&glPrimitiveRestartNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
