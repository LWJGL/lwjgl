/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetBufferPointervARBPROC) (GLenum target, GLenum pname, GLvoid ** pointer);
typedef void (APIENTRY *glGetBufferParameterivARBPROC) (GLenum target, GLenum pname, GLint * params);
typedef GLboolean (APIENTRY *glUnmapBufferARBPROC) (GLenum target);
typedef GLvoid * (APIENTRY *glMapBufferARBPROC) (GLenum target, GLenum access);
typedef void (APIENTRY *glGetBufferSubDataARBPROC) (GLenum target, GLintptrARB offset, GLsizeiptrARB size, GLvoid * data);
typedef void (APIENTRY *glBufferSubDataARBPROC) (GLenum target, GLintptrARB offset, GLsizeiptrARB size, const GLvoid * data);
typedef void (APIENTRY *glBufferDataARBPROC) (GLenum target, GLsizeiptrARB size, const GLvoid * data, GLenum usage);
typedef GLboolean (APIENTRY *glIsBufferARBPROC) (GLuint buffer);
typedef void (APIENTRY *glGenBuffersARBPROC) (GLint n, GLuint * buffers);
typedef void (APIENTRY *glDeleteBuffersARBPROC) (GLsizei n, const GLuint * buffers);
typedef void (APIENTRY *glBindBufferARBPROC) (GLenum target, GLuint buffer);

static glGetBufferPointervARBPROC glGetBufferPointervARB;
static glGetBufferParameterivARBPROC glGetBufferParameterivARB;
static glUnmapBufferARBPROC glUnmapBufferARB;
static glMapBufferARBPROC glMapBufferARB;
static glGetBufferSubDataARBPROC glGetBufferSubDataARB;
static glBufferSubDataARBPROC glBufferSubDataARB;
static glBufferDataARBPROC glBufferDataARB;
static glIsBufferARBPROC glIsBufferARB;
static glGenBuffersARBPROC glGenBuffersARB;
static glDeleteBuffersARBPROC glDeleteBuffersARB;
static glBindBufferARBPROC glBindBufferARB;

static jobject JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferPointervARB(JNIEnv *env, jclass clazz, jint target, jint pname, jint result_size) {
	GLvoid * __result;
	glGetBufferPointervARB(target, pname, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferParameterivARB(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetBufferParameterivARB(target, pname, params_address);
}

static jboolean JNICALL Java_org_lwjgl_opengl_ARBBufferObject_glUnmapBufferARB(JNIEnv *env, jclass clazz, jint target) {
	GLboolean __result = glUnmapBufferARB(target);
	return __result;
}

static jobject JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglMapBufferARB(JNIEnv *env, jclass clazz, jint target, jint access, jint result_size, jobject old_buffer) {
	GLvoid * __result = glMapBufferARB(target, access);
	return safeNewBufferCached(env, __result, result_size, old_buffer);
}

static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferSubDataARB(JNIEnv *env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_position) {
	GLvoid *data_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glGetBufferSubDataARB(target, offset, size, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglBufferSubDataARB(JNIEnv *env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glBufferSubDataARB(target, offset, size, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglBufferDataARB(JNIEnv *env, jclass clazz, jint target, jint size, jobject data, jint data_position, jint usage) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)safeGetBufferAddress(env, data)) + data_position));
	glBufferDataARB(target, size, data_address, usage);
}

static jboolean JNICALL Java_org_lwjgl_opengl_ARBBufferObject_glIsBufferARB(JNIEnv *env, jclass clazz, jint buffer) {
	GLboolean __result = glIsBufferARB(buffer);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglGenBuffersARB(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	GLuint *buffers_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glGenBuffersARB(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglDeleteBuffersARB(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	const GLuint *buffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glDeleteBuffersARB(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglBindBufferARB(JNIEnv *env, jclass clazz, jint target, jint buffer) {
	glBindBufferARB(target, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetBufferPointervARB", "(III)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferPointervARB, "glGetBufferPointervARB", (void *)&glGetBufferPointervARB},
		{"nglGetBufferParameterivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferParameterivARB, "glGetBufferParameterivARB", (void *)&glGetBufferParameterivARB},
		{"glUnmapBufferARB", "(I)Z", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_glUnmapBufferARB, "glUnmapBufferARB", (void *)&glUnmapBufferARB},
		{"nglMapBufferARB", "(IIILjava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglMapBufferARB, "glMapBufferARB", (void *)&glMapBufferARB},
		{"nglGetBufferSubDataARB", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferSubDataARB, "glGetBufferSubDataARB", (void *)&glGetBufferSubDataARB},
		{"nglBufferSubDataARB", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglBufferSubDataARB, "glBufferSubDataARB", (void *)&glBufferSubDataARB},
		{"nglBufferDataARB", "(IILjava/nio/Buffer;II)V", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglBufferDataARB, "glBufferDataARB", (void *)&glBufferDataARB},
		{"glIsBufferARB", "(I)Z", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_glIsBufferARB, "glIsBufferARB", (void *)&glIsBufferARB},
		{"nglGenBuffersARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglGenBuffersARB, "glGenBuffersARB", (void *)&glGenBuffersARB},
		{"nglDeleteBuffersARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglDeleteBuffersARB, "glDeleteBuffersARB", (void *)&glDeleteBuffersARB},
		{"nglBindBufferARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBBufferObject_nglBindBufferARB, "glBindBufferARB", (void *)&glBindBufferARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
