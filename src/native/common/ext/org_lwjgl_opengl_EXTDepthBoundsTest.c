/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glDepthBoundsEXTPROC) (GLclampd zmin, GLclampd zmax);

static glDepthBoundsEXTPROC glDepthBoundsEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTDepthBoundsTest_glDepthBoundsEXT(JNIEnv *env, jclass clazz, jdouble zmin, jdouble zmax) {
	glDepthBoundsEXT(zmin, zmax);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTDepthBoundsTest_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glDepthBoundsEXT", "(DD)V", (void *)&Java_org_lwjgl_opengl_EXTDepthBoundsTest_glDepthBoundsEXT, "glDepthBoundsEXT", (void *)&glDepthBoundsEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
