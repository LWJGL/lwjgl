/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetOcclusionQueryivNVPROC) (GLuint id, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetOcclusionQueryuivNVPROC) (GLuint id, GLenum pname, GLuint * params);
typedef void (APIENTRY *glEndOcclusionQueryNVPROC) ();
typedef void (APIENTRY *glBeginOcclusionQueryNVPROC) (GLuint id);
typedef GLboolean (APIENTRY *glIsOcclusionQueryNVPROC) (GLuint id);
typedef void (APIENTRY *glDeleteOcclusionQueriesNVPROC) (GLsizei n, const GLuint * piIDs);
typedef void (APIENTRY *glGenOcclusionQueriesNVPROC) (GLsizei n, GLuint * piIDs);

static glGetOcclusionQueryivNVPROC glGetOcclusionQueryivNV;
static glGetOcclusionQueryuivNVPROC glGetOcclusionQueryuivNV;
static glEndOcclusionQueryNVPROC glEndOcclusionQueryNV;
static glBeginOcclusionQueryNVPROC glBeginOcclusionQueryNV;
static glIsOcclusionQueryNVPROC glIsOcclusionQueryNV;
static glDeleteOcclusionQueriesNVPROC glDeleteOcclusionQueriesNV;
static glGenOcclusionQueriesNVPROC glGenOcclusionQueriesNV;

static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryivNV(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetOcclusionQueryivNV(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryuivNV(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLuint *params_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetOcclusionQueryuivNV(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_glEndOcclusionQueryNV(JNIEnv *env, jclass clazz) {
	glEndOcclusionQueryNV();
}

static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_glBeginOcclusionQueryNV(JNIEnv *env, jclass clazz, jint id) {
	glBeginOcclusionQueryNV(id);
}

static jboolean JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_glIsOcclusionQueryNV(JNIEnv *env, jclass clazz, jint id) {
	GLboolean __result = glIsOcclusionQueryNV(id);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglDeleteOcclusionQueriesNV(JNIEnv *env, jclass clazz, jint n, jobject piIDs, jint piIDs_position) {
	const GLuint *piIDs_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, piIDs)) + piIDs_position;
	glDeleteOcclusionQueriesNV(n, piIDs_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglGenOcclusionQueriesNV(JNIEnv *env, jclass clazz, jint n, jobject piIDs, jint piIDs_position) {
	GLuint *piIDs_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, piIDs)) + piIDs_position;
	glGenOcclusionQueriesNV(n, piIDs_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetOcclusionQueryivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryivNV, "glGetOcclusionQueryivNV", (void *)&glGetOcclusionQueryivNV},
		{"nglGetOcclusionQueryuivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryuivNV, "glGetOcclusionQueryuivNV", (void *)&glGetOcclusionQueryuivNV},
		{"glEndOcclusionQueryNV", "()V", (void *)&Java_org_lwjgl_opengl_NVOcclusionQuery_glEndOcclusionQueryNV, "glEndOcclusionQueryNV", (void *)&glEndOcclusionQueryNV},
		{"glBeginOcclusionQueryNV", "(I)V", (void *)&Java_org_lwjgl_opengl_NVOcclusionQuery_glBeginOcclusionQueryNV, "glBeginOcclusionQueryNV", (void *)&glBeginOcclusionQueryNV},
		{"glIsOcclusionQueryNV", "(I)Z", (void *)&Java_org_lwjgl_opengl_NVOcclusionQuery_glIsOcclusionQueryNV, "glIsOcclusionQueryNV", (void *)&glIsOcclusionQueryNV},
		{"nglDeleteOcclusionQueriesNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglDeleteOcclusionQueriesNV, "glDeleteOcclusionQueriesNV", (void *)&glDeleteOcclusionQueriesNV},
		{"nglGenOcclusionQueriesNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglGenOcclusionQueriesNV, "glGenOcclusionQueriesNV", (void *)&glGenOcclusionQueriesNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
