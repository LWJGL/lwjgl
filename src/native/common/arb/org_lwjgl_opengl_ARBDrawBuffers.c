/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glDrawBuffersARBPROC) (GLsizei size, const GLenum * buffers);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBDrawBuffers_nglDrawBuffersARB(JNIEnv *env, jclass clazz, jint size, jobject buffers, jint buffers_position, jlong function_pointer) {
	const GLenum *buffers_address = ((const GLenum *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glDrawBuffersARBPROC glDrawBuffersARB = (glDrawBuffersARBPROC)((intptr_t)function_pointer);
	glDrawBuffersARB(size, buffers_address);
}

