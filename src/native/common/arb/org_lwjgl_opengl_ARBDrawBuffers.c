/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glDrawBuffersARBPROC) (GLsizei size, const GLenum * buffers);

static glDrawBuffersARBPROC glDrawBuffersARB;

static void JNICALL Java_org_lwjgl_opengl_ARBDrawBuffers_nglDrawBuffersARB(JNIEnv *env, jclass clazz, jint size, jobject buffers, jint buffers_position) {
	const GLenum *buffers_address = ((const GLenum *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glDrawBuffersARB(size, buffers_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBDrawBuffers_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglDrawBuffersARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBDrawBuffers_nglDrawBuffersARB, "glDrawBuffersARB", (void *)&glDrawBuffersARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
