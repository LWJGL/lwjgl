/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glBlendFuncSeparateEXTPROC) (GLenum sfactorRGB, GLenum dfactorRGB, GLenum sfactorAlpha, GLenum dfactorAlpha);

static glBlendFuncSeparateEXTPROC glBlendFuncSeparateEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTBlendFuncSeparate_glBlendFuncSeparateEXT(JNIEnv *env, jclass clazz, jint sfactorRGB, jint dfactorRGB, jint sfactorAlpha, jint dfactorAlpha) {
	glBlendFuncSeparateEXT(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTBlendFuncSeparate_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glBlendFuncSeparateEXT", "(IIII)V", (void *)&Java_org_lwjgl_opengl_EXTBlendFuncSeparate_glBlendFuncSeparateEXT, "glBlendFuncSeparateEXT", (void *)&glBlendFuncSeparateEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
