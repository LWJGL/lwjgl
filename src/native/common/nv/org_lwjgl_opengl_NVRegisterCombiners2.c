/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetCombinerStageParameterfvNVPROC) (GLenum stage, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glCombinerStageParameterfvNVPROC) (GLenum stage, GLenum pname, const GLfloat * params);

static glGetCombinerStageParameterfvNVPROC glGetCombinerStageParameterfvNV;
static glCombinerStageParameterfvNVPROC glCombinerStageParameterfvNV;

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners2_nglGetCombinerStageParameterfvNV(JNIEnv *env, jclass clazz, jint stage, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerStageParameterfvNV(stage, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners2_nglCombinerStageParameterfvNV(JNIEnv *env, jclass clazz, jint stage, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glCombinerStageParameterfvNV(stage, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners2_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetCombinerStageParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners2_nglGetCombinerStageParameterfvNV, "glGetCombinerStageParameterfvNV", (void *)&glGetCombinerStageParameterfvNV},
		{"nglCombinerStageParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners2_nglCombinerStageParameterfvNV, "glCombinerStageParameterfvNV", (void *)&glCombinerStageParameterfvNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
