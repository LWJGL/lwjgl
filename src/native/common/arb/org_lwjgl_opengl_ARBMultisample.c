/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glSampleCoverageARBPROC) (GLclampf value, GLboolean invert);

static glSampleCoverageARBPROC glSampleCoverageARB;

static void JNICALL Java_org_lwjgl_opengl_ARBMultisample_glSampleCoverageARB(JNIEnv *env, jclass clazz, jfloat value, jboolean invert) {
	glSampleCoverageARB(value, invert);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMultisample_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glSampleCoverageARB", "(FZ)V", (void *)&Java_org_lwjgl_opengl_ARBMultisample_glSampleCoverageARB, "glSampleCoverageARB", (void *)&glSampleCoverageARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
