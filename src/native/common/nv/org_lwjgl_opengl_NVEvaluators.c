/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glEvalMapsNVPROC) (GLenum target, GLenum mode);
typedef void (APIENTRY *glGetMapAttribParameterivNVPROC) (GLenum target, GLuint index, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetMapAttribParameterfvNVPROC) (GLenum target, GLuint index, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetMapParameterivNVPROC) (GLenum target, GLenum pname, const GLint * params);
typedef void (APIENTRY *glGetMapParameterfvNVPROC) (GLenum target, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glMapParameterivNVPROC) (GLenum target, GLenum pname, const GLint * params);
typedef void (APIENTRY *glMapParameterfvNVPROC) (GLenum target, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLint uorder, GLint vorder, GLboolean packed, const GLvoid * pPoints);
typedef void (APIENTRY *glGetMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLboolean packed, const GLvoid * pPoints);

static glEvalMapsNVPROC glEvalMapsNV;
static glGetMapAttribParameterivNVPROC glGetMapAttribParameterivNV;
static glGetMapAttribParameterfvNVPROC glGetMapAttribParameterfvNV;
static glGetMapParameterivNVPROC glGetMapParameterivNV;
static glGetMapParameterfvNVPROC glGetMapParameterfvNV;
static glMapParameterivNVPROC glMapParameterivNV;
static glMapParameterfvNVPROC glMapParameterfvNV;
static glMapControlPointsNVPROC glMapControlPointsNV;
static glGetMapControlPointsNVPROC glGetMapControlPointsNV;

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_glEvalMapsNV(JNIEnv *env, jclass clazz, jint target, jint mode) {
	glEvalMapsNV(target, mode);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterivNV(JNIEnv *env, jclass clazz, jint target, jint index, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapAttribParameterivNV(target, index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint index, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapAttribParameterfvNV(target, index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterivNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapParameterivNV(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapParameterfvNV(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterivNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glMapParameterivNV(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glMapParameterfvNV(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapControlPointsNV(JNIEnv *env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jint uorder, jint vorder, jboolean packed, jobject pPoints, jint pPoints_position) {
	const GLvoid *pPoints_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPoints)) + pPoints_position));
	glMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, pPoints_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapControlPointsNV(JNIEnv *env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jboolean packed, jobject pPoints, jint pPoints_position) {
	const GLvoid *pPoints_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPoints)) + pPoints_position));
	glGetMapControlPointsNV(target, index, type, ustride, vstride, packed, pPoints_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glEvalMapsNV", "(II)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_glEvalMapsNV, "glEvalMapsNV", (void *)&glEvalMapsNV},
		{"nglGetMapAttribParameterivNV", "(IIILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterivNV, "glGetMapAttribParameterivNV", (void *)&glGetMapAttribParameterivNV},
		{"nglGetMapAttribParameterfvNV", "(IIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterfvNV, "glGetMapAttribParameterfvNV", (void *)&glGetMapAttribParameterfvNV},
		{"nglGetMapParameterivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterivNV, "glGetMapParameterivNV", (void *)&glGetMapParameterivNV},
		{"nglGetMapParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterfvNV, "glGetMapParameterfvNV", (void *)&glGetMapParameterfvNV},
		{"nglMapParameterivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterivNV, "glMapParameterivNV", (void *)&glMapParameterivNV},
		{"nglMapParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterfvNV, "glMapParameterfvNV", (void *)&glMapParameterfvNV},
		{"nglMapControlPointsNV", "(IIIIIIIZLjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglMapControlPointsNV, "glMapControlPointsNV", (void *)&glMapControlPointsNV},
		{"nglGetMapControlPointsNV", "(IIIIIZLjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapControlPointsNV, "glGetMapControlPointsNV", (void *)&glGetMapControlPointsNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
