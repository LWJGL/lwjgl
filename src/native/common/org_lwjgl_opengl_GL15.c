/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetQueryObjectuivPROC) (GLenum id, GLenum pname, GLuint * params);
typedef void (APIENTRY *glGetQueryObjectivPROC) (GLenum id, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetQueryivPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glEndQueryPROC) (GLenum target);
typedef void (APIENTRY *glBeginQueryPROC) (GLenum target, GLuint id);
typedef GLboolean (APIENTRY *glIsQueryPROC) (GLuint id);
typedef void (APIENTRY *glDeleteQueriesPROC) (GLsizei n, GLuint * ids);
typedef void (APIENTRY *glGenQueriesPROC) (GLsizei n, GLuint * ids);
typedef void (APIENTRY *glGetBufferPointervPROC) (GLenum target, GLenum pname, GLvoid ** pointer);
typedef void (APIENTRY *glGetBufferParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef GLboolean (APIENTRY *glUnmapBufferPROC) (GLenum target);
typedef GLvoid * (APIENTRY *glMapBufferPROC) (GLenum target, GLenum access);
typedef void (APIENTRY *glGetBufferSubDataPROC) (GLenum target, GLintptr offset, GLsizeiptr size, GLvoid * data);
typedef void (APIENTRY *glBufferSubDataPROC) (GLenum target, GLintptr offset, GLsizeiptr size, const GLvoid * data);
typedef void (APIENTRY *glBufferDataPROC) (GLenum target, GLsizeiptr size, const GLvoid * data, GLenum usage);
typedef GLboolean (APIENTRY *glIsBufferPROC) (GLuint buffer);
typedef void (APIENTRY *glGenBuffersPROC) (GLsizei n, GLuint * buffers);
typedef void (APIENTRY *glDeleteBuffersPROC) (GLsizei n, const GLuint * buffers);
typedef void (APIENTRY *glBindBufferPROC) (GLenum target, GLuint buffer);

static glGetQueryObjectuivPROC glGetQueryObjectuiv;
static glGetQueryObjectivPROC glGetQueryObjectiv;
static glGetQueryivPROC glGetQueryiv;
static glEndQueryPROC glEndQuery;
static glBeginQueryPROC glBeginQuery;
static glIsQueryPROC glIsQuery;
static glDeleteQueriesPROC glDeleteQueries;
static glGenQueriesPROC glGenQueries;
static glGetBufferPointervPROC glGetBufferPointerv;
static glGetBufferParameterivPROC glGetBufferParameteriv;
static glUnmapBufferPROC glUnmapBuffer;
static glMapBufferPROC glMapBuffer;
static glGetBufferSubDataPROC glGetBufferSubData;
static glBufferSubDataPROC glBufferSubData;
static glBufferDataPROC glBufferData;
static glIsBufferPROC glIsBuffer;
static glGenBuffersPROC glGenBuffers;
static glDeleteBuffersPROC glDeleteBuffers;
static glBindBufferPROC glBindBuffer;

static void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryObjectuiv(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLuint *params_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetQueryObjectuiv(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryObjectiv(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetQueryObjectiv(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryiv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetQueryiv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_glEndQuery(JNIEnv *env, jclass clazz, jint target) {
	glEndQuery(target);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_glBeginQuery(JNIEnv *env, jclass clazz, jint target, jint id) {
	glBeginQuery(target, id);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL15_glIsQuery(JNIEnv *env, jclass clazz, jint id) {
	GLboolean __result = glIsQuery(id);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglDeleteQueries(JNIEnv *env, jclass clazz, jint n, jobject ids, jint ids_position) {
	GLuint *ids_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, ids)) + ids_position;
	glDeleteQueries(n, ids_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglGenQueries(JNIEnv *env, jclass clazz, jint n, jobject ids, jint ids_position) {
	GLuint *ids_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, ids)) + ids_position;
	glGenQueries(n, ids_address);
}

static jobject JNICALL Java_org_lwjgl_opengl_GL15_nglGetBufferPointerv(JNIEnv *env, jclass clazz, jint target, jint pname, jint result_size) {
	GLvoid * __result;
	glGetBufferPointerv(target, pname, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglGetBufferParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetBufferParameteriv(target, pname, params_address);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL15_glUnmapBuffer(JNIEnv *env, jclass clazz, jint target) {
	GLboolean __result = glUnmapBuffer(target);
	return __result;
}

static jobject JNICALL Java_org_lwjgl_opengl_GL15_nglMapBuffer(JNIEnv *env, jclass clazz, jint target, jint access, jint result_size, jobject old_buffer) {
	GLvoid * __result = glMapBuffer(target, access);
	return safeNewBufferCached(env, __result, result_size, old_buffer);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglGetBufferSubData(JNIEnv *env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_position) {
	GLvoid *data_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glGetBufferSubData(target, offset, size, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglBufferSubData(JNIEnv *env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glBufferSubData(target, offset, size, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglBufferData(JNIEnv *env, jclass clazz, jint target, jint size, jobject data, jint data_position, jint usage) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)safeGetBufferAddress(env, data)) + data_position));
	glBufferData(target, size, data_address, usage);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL15_glIsBuffer(JNIEnv *env, jclass clazz, jint buffer) {
	GLboolean __result = glIsBuffer(buffer);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglGenBuffers(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	GLuint *buffers_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glGenBuffers(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglDeleteBuffers(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	const GLuint *buffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glDeleteBuffers(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL15_nglBindBuffer(JNIEnv *env, jclass clazz, jint target, jint buffer) {
	glBindBuffer(target, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetQueryObjectuiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglGetQueryObjectuiv, "glGetQueryObjectuiv", (void *)&glGetQueryObjectuiv},
		{"nglGetQueryObjectiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglGetQueryObjectiv, "glGetQueryObjectiv", (void *)&glGetQueryObjectiv},
		{"nglGetQueryiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglGetQueryiv, "glGetQueryiv", (void *)&glGetQueryiv},
		{"glEndQuery", "(I)V", (void *)&Java_org_lwjgl_opengl_GL15_glEndQuery, "glEndQuery", (void *)&glEndQuery},
		{"glBeginQuery", "(II)V", (void *)&Java_org_lwjgl_opengl_GL15_glBeginQuery, "glBeginQuery", (void *)&glBeginQuery},
		{"glIsQuery", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL15_glIsQuery, "glIsQuery", (void *)&glIsQuery},
		{"nglDeleteQueries", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglDeleteQueries, "glDeleteQueries", (void *)&glDeleteQueries},
		{"nglGenQueries", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglGenQueries, "glGenQueries", (void *)&glGenQueries},
		{"nglGetBufferPointerv", "(III)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_GL15_nglGetBufferPointerv, "glGetBufferPointerv", (void *)&glGetBufferPointerv},
		{"nglGetBufferParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglGetBufferParameteriv, "glGetBufferParameteriv", (void *)&glGetBufferParameteriv},
		{"glUnmapBuffer", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL15_glUnmapBuffer, "glUnmapBuffer", (void *)&glUnmapBuffer},
		{"nglMapBuffer", "(IIILjava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_GL15_nglMapBuffer, "glMapBuffer", (void *)&glMapBuffer},
		{"nglGetBufferSubData", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglGetBufferSubData, "glGetBufferSubData", (void *)&glGetBufferSubData},
		{"nglBufferSubData", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglBufferSubData, "glBufferSubData", (void *)&glBufferSubData},
		{"nglBufferData", "(IILjava/nio/Buffer;II)V", (void *)&Java_org_lwjgl_opengl_GL15_nglBufferData, "glBufferData", (void *)&glBufferData},
		{"glIsBuffer", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL15_glIsBuffer, "glIsBuffer", (void *)&glIsBuffer},
		{"nglGenBuffers", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglGenBuffers, "glGenBuffers", (void *)&glGenBuffers},
		{"nglDeleteBuffers", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL15_nglDeleteBuffers, "glDeleteBuffers", (void *)&glDeleteBuffers},
		{"nglBindBuffer", "(II)V", (void *)&Java_org_lwjgl_opengl_GL15_nglBindBuffer, "glBindBuffer", (void *)&glBindBuffer}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
