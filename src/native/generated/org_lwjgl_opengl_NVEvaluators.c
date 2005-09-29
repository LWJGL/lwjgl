/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLboolean packed, const GLvoid * pPoints);
typedef void (APIENTRY *glMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLint uorder, GLint vorder, GLboolean packed, const GLvoid * pPoints);
typedef void (APIENTRY *glMapParameterfvNVPROC) (GLenum target, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glMapParameterivNVPROC) (GLenum target, GLenum pname, const GLint * params);
typedef void (APIENTRY *glGetMapParameterfvNVPROC) (GLenum target, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glGetMapParameterivNVPROC) (GLenum target, GLenum pname, const GLint * params);
typedef void (APIENTRY *glGetMapAttribParameterfvNVPROC) (GLenum target, GLuint index, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetMapAttribParameterivNVPROC) (GLenum target, GLuint index, GLenum pname, GLint * params);
typedef void (APIENTRY *glEvalMapsNVPROC) (GLenum target, GLenum mode);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapControlPointsNV(JNIEnv *env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jboolean packed, jobject pPoints, jint pPoints_position, jlong function_pointer) {
	const GLvoid *pPoints_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPoints)) + pPoints_position));
	glGetMapControlPointsNVPROC glGetMapControlPointsNV = (glGetMapControlPointsNVPROC)((intptr_t)function_pointer);
	glGetMapControlPointsNV(target, index, type, ustride, vstride, packed, pPoints_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapControlPointsNV(JNIEnv *env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jint uorder, jint vorder, jboolean packed, jobject pPoints, jint pPoints_position, jlong function_pointer) {
	const GLvoid *pPoints_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPoints)) + pPoints_position));
	glMapControlPointsNVPROC glMapControlPointsNV = (glMapControlPointsNVPROC)((intptr_t)function_pointer);
	glMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, pPoints_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position, jlong function_pointer) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glMapParameterfvNVPROC glMapParameterfvNV = (glMapParameterfvNVPROC)((intptr_t)function_pointer);
	glMapParameterfvNV(target, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterivNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position, jlong function_pointer) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glMapParameterivNVPROC glMapParameterivNV = (glMapParameterivNVPROC)((intptr_t)function_pointer);
	glMapParameterivNV(target, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position, jlong function_pointer) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapParameterfvNVPROC glGetMapParameterfvNV = (glGetMapParameterfvNVPROC)((intptr_t)function_pointer);
	glGetMapParameterfvNV(target, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterivNV(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position, jlong function_pointer) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapParameterivNVPROC glGetMapParameterivNV = (glGetMapParameterivNVPROC)((intptr_t)function_pointer);
	glGetMapParameterivNV(target, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint index, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapAttribParameterfvNVPROC glGetMapAttribParameterfvNV = (glGetMapAttribParameterfvNVPROC)((intptr_t)function_pointer);
	glGetMapAttribParameterfvNV(target, index, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterivNV(JNIEnv *env, jclass clazz, jint target, jint index, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMapAttribParameterivNVPROC glGetMapAttribParameterivNV = (glGetMapAttribParameterivNVPROC)((intptr_t)function_pointer);
	glGetMapAttribParameterivNV(target, index, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglEvalMapsNV(JNIEnv *env, jclass clazz, jint target, jint mode, jlong function_pointer) {
	glEvalMapsNVPROC glEvalMapsNV = (glEvalMapsNVPROC)((intptr_t)function_pointer);
	glEvalMapsNV(target, mode);
}

