/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetQueryObjectuivARBPROC) (GLuint id, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetQueryObjectivARBPROC) (GLuint id, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetQueryivARBPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glEndQueryARBPROC) (GLenum target);
typedef void (APIENTRY *glBeginQueryARBPROC) (GLenum target, GLuint id);
typedef GLboolean (APIENTRY *glIsQueryARBPROC) (GLuint id);
typedef void (APIENTRY *glDeleteQueriesARBPROC) (GLsizei n, GLuint * ids);
typedef void (APIENTRY *glGenQueriesARBPROC) (GLsizei n, GLuint * ids);

static glGetQueryObjectuivARBPROC glGetQueryObjectuivARB;
static glGetQueryObjectivARBPROC glGetQueryObjectivARB;
static glGetQueryivARBPROC glGetQueryivARB;
static glEndQueryARBPROC glEndQueryARB;
static glBeginQueryARBPROC glBeginQueryARB;
static glIsQueryARBPROC glIsQueryARB;
static glDeleteQueriesARBPROC glDeleteQueriesARB;
static glGenQueriesARBPROC glGenQueriesARB;

static void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectuivARB(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetQueryObjectuivARB(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectivARB(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetQueryObjectivARB(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryivARB(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetQueryivARB(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_glEndQueryARB(JNIEnv *env, jclass clazz, jint target) {
	glEndQueryARB(target);
}

static void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_glBeginQueryARB(JNIEnv *env, jclass clazz, jint target, jint id) {
	glBeginQueryARB(target, id);
}

static jboolean JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_glIsQueryARB(JNIEnv *env, jclass clazz, jint id) {
	GLboolean __result = glIsQueryARB(id);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglDeleteQueriesARB(JNIEnv *env, jclass clazz, jint n, jobject ids, jint ids_position) {
	GLuint *ids_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, ids)) + ids_position;
	glDeleteQueriesARB(n, ids_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGenQueriesARB(JNIEnv *env, jclass clazz, jint n, jobject ids, jint ids_position) {
	GLuint *ids_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, ids)) + ids_position;
	glGenQueriesARB(n, ids_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetQueryObjectuivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectuivARB, "glGetQueryObjectuivARB", (void *)&glGetQueryObjectuivARB},
		{"nglGetQueryObjectivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectivARB, "glGetQueryObjectivARB", (void *)&glGetQueryObjectivARB},
		{"nglGetQueryivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryivARB, "glGetQueryivARB", (void *)&glGetQueryivARB},
		{"glEndQueryARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_glEndQueryARB, "glEndQueryARB", (void *)&glEndQueryARB},
		{"glBeginQueryARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_glBeginQueryARB, "glBeginQueryARB", (void *)&glBeginQueryARB},
		{"glIsQueryARB", "(I)Z", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_glIsQueryARB, "glIsQueryARB", (void *)&glIsQueryARB},
		{"nglDeleteQueriesARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglDeleteQueriesARB, "glDeleteQueriesARB", (void *)&glDeleteQueriesARB},
		{"nglGenQueriesARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGenQueriesARB, "glGenQueriesARB", (void *)&glGenQueriesARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
