/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glBlendEquationSeparateEXTPROC) (GLenum modeRGB, GLenum modeAlpha);

static glBlendEquationSeparateEXTPROC glBlendEquationSeparateEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTBlendEquationSeparate_glBlendEquationSeparateEXT(JNIEnv *env, jclass clazz, jint modeRGB, jint modeAlpha) {
	glBlendEquationSeparateEXT(modeRGB, modeAlpha);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTBlendEquationSeparate_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glBlendEquationSeparateEXT", "(II)V", (void *)&Java_org_lwjgl_opengl_EXTBlendEquationSeparate_glBlendEquationSeparateEXT, "glBlendEquationSeparateEXT", (void *)&glBlendEquationSeparateEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
