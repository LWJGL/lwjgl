/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetFenceivNVPROC) (GLuint fence, GLenum pname, GLint * piParams);
typedef GLboolean (APIENTRY *glIsFenceNVPROC) (GLuint fence);
typedef void (APIENTRY *glFinishFenceNVPROC) (GLuint fence);
typedef GLboolean (APIENTRY *glTestFenceNVPROC) (GLuint fence);
typedef void (APIENTRY *glSetFenceNVPROC) (GLuint fence, GLenum condition);
typedef void (APIENTRY *glDeleteFencesNVPROC) (GLsizei n, const GLuint * piFences);
typedef void (APIENTRY *glGenFencesNVPROC) (GLsizei n, GLuint * piFences);

static glGetFenceivNVPROC glGetFenceivNV;
static glIsFenceNVPROC glIsFenceNV;
static glFinishFenceNVPROC glFinishFenceNV;
static glTestFenceNVPROC glTestFenceNV;
static glSetFenceNVPROC glSetFenceNV;
static glDeleteFencesNVPROC glDeleteFencesNV;
static glGenFencesNVPROC glGenFencesNV;

static void JNICALL Java_org_lwjgl_opengl_NVFence_nglGetFenceivNV(JNIEnv *env, jclass clazz, jint fence, jint pname, jobject piParams, jint piParams_position) {
	GLint *piParams_address = ((GLint *)(*env)->GetDirectBufferAddress(env, piParams)) + piParams_position;
	glGetFenceivNV(fence, pname, piParams_address);
}

static jboolean JNICALL Java_org_lwjgl_opengl_NVFence_glIsFenceNV(JNIEnv *env, jclass clazz, jint fence) {
	GLboolean __result = glIsFenceNV(fence);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_NVFence_glFinishFenceNV(JNIEnv *env, jclass clazz, jint fence) {
	glFinishFenceNV(fence);
}

static jboolean JNICALL Java_org_lwjgl_opengl_NVFence_glTestFenceNV(JNIEnv *env, jclass clazz, jint fence) {
	GLboolean __result = glTestFenceNV(fence);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_NVFence_glSetFenceNV(JNIEnv *env, jclass clazz, jint fence, jint condition) {
	glSetFenceNV(fence, condition);
}

static void JNICALL Java_org_lwjgl_opengl_NVFence_nglDeleteFencesNV(JNIEnv *env, jclass clazz, jint n, jobject piFences, jint piFences_position) {
	const GLuint *piFences_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, piFences)) + piFences_position;
	glDeleteFencesNV(n, piFences_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVFence_nglGenFencesNV(JNIEnv *env, jclass clazz, jint n, jobject piFences, jint piFences_position) {
	GLuint *piFences_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, piFences)) + piFences_position;
	glGenFencesNV(n, piFences_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVFence_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetFenceivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVFence_nglGetFenceivNV, "glGetFenceivNV", (void *)&glGetFenceivNV},
		{"glIsFenceNV", "(I)Z", (void *)&Java_org_lwjgl_opengl_NVFence_glIsFenceNV, "glIsFenceNV", (void *)&glIsFenceNV},
		{"glFinishFenceNV", "(I)V", (void *)&Java_org_lwjgl_opengl_NVFence_glFinishFenceNV, "glFinishFenceNV", (void *)&glFinishFenceNV},
		{"glTestFenceNV", "(I)Z", (void *)&Java_org_lwjgl_opengl_NVFence_glTestFenceNV, "glTestFenceNV", (void *)&glTestFenceNV},
		{"glSetFenceNV", "(II)V", (void *)&Java_org_lwjgl_opengl_NVFence_glSetFenceNV, "glSetFenceNV", (void *)&glSetFenceNV},
		{"nglDeleteFencesNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVFence_nglDeleteFencesNV, "glDeleteFencesNV", (void *)&glDeleteFencesNV},
		{"nglGenFencesNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVFence_nglGenFencesNV, "glGenFencesNV", (void *)&glGenFencesNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
