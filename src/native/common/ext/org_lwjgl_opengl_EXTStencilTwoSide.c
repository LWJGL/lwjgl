/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glActiveStencilFaceEXTPROC) (GLenum face);

static glActiveStencilFaceEXTPROC glActiveStencilFaceEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTStencilTwoSide_glActiveStencilFaceEXT(JNIEnv *env, jclass clazz, jint face) {
	glActiveStencilFaceEXT(face);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTStencilTwoSide_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glActiveStencilFaceEXT", "(I)V", (void *)&Java_org_lwjgl_opengl_EXTStencilTwoSide_glActiveStencilFaceEXT, "glActiveStencilFaceEXT", (void *)&glActiveStencilFaceEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
