/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glDrawBuffersATIPROC) (GLsizei size, const GLenum * buffers);

static glDrawBuffersATIPROC glDrawBuffersATI;

static void JNICALL Java_org_lwjgl_opengl_ATIDrawBuffers_nglDrawBuffersATI(JNIEnv *env, jclass clazz, jint size, jobject buffers, jint buffers_position) {
	const GLenum *buffers_address = ((const GLenum *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glDrawBuffersATI(size, buffers_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIDrawBuffers_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglDrawBuffersATI", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIDrawBuffers_nglDrawBuffersATI, "glDrawBuffersATI", (void *)&glDrawBuffersATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
