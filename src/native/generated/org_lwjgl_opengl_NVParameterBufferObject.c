/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glBindBufferRangeNVPROC) (GLenum target, GLuint index, GLuint buffer, GLintptr offset, GLsizeiptr size);
typedef void (APIENTRY *glBindBufferOffsetNVPROC) (GLenum target, GLuint index, GLuint buffer, GLintptr offset);
typedef void (APIENTRY *glBindBufferBaseNVPROC) (GLenum target, GLuint index, GLuint buffer);
typedef void (APIENTRY *glProgramBufferParametersfvNVPROC) (GLenum target, GLuint buffer, GLuint index, GLsizei count, const GLfloat * params);
typedef void (APIENTRY *glProgramBufferParametersIivNVPROC) (GLenum target, GLuint buffer, GLuint index, GLsizei count, const GLint * params);
typedef void (APIENTRY *glProgramBufferParametersIuivNVPROC) (GLenum target, GLuint buffer, GLuint index, GLuint count, const GLuint * params);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVParameterBufferObject_nglBindBufferRangeNV(JNIEnv *env, jclass clazz, jint target, jint index, jint buffer, jlong offset, jlong size, jlong function_pointer) {
	glBindBufferRangeNVPROC glBindBufferRangeNV = (glBindBufferRangeNVPROC)((intptr_t)function_pointer);
	glBindBufferRangeNV(target, index, buffer, offset, size);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVParameterBufferObject_nglBindBufferOffsetNV(JNIEnv *env, jclass clazz, jint target, jint index, jint buffer, jlong offset, jlong function_pointer) {
	glBindBufferOffsetNVPROC glBindBufferOffsetNV = (glBindBufferOffsetNVPROC)((intptr_t)function_pointer);
	glBindBufferOffsetNV(target, index, buffer, offset);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVParameterBufferObject_nglBindBufferBaseNV(JNIEnv *env, jclass clazz, jint target, jint index, jint buffer, jlong function_pointer) {
	glBindBufferBaseNVPROC glBindBufferBaseNV = (glBindBufferBaseNVPROC)((intptr_t)function_pointer);
	glBindBufferBaseNV(target, index, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVParameterBufferObject_nglProgramBufferParametersfvNV(JNIEnv *env, jclass clazz, jint target, jint buffer, jint index, jint count, jobject params, jint params_position, jlong function_pointer) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramBufferParametersfvNVPROC glProgramBufferParametersfvNV = (glProgramBufferParametersfvNVPROC)((intptr_t)function_pointer);
	glProgramBufferParametersfvNV(target, buffer, index, count, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVParameterBufferObject_nglProgramBufferParametersIivNV(JNIEnv *env, jclass clazz, jint target, jint buffer, jint index, jint count, jobject params, jint params_position, jlong function_pointer) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramBufferParametersIivNVPROC glProgramBufferParametersIivNV = (glProgramBufferParametersIivNVPROC)((intptr_t)function_pointer);
	glProgramBufferParametersIivNV(target, buffer, index, count, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVParameterBufferObject_nglProgramBufferParametersIuivNV(JNIEnv *env, jclass clazz, jint target, jint buffer, jint index, jint count, jobject params, jint params_position, jlong function_pointer) {
	const GLuint *params_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramBufferParametersIuivNVPROC glProgramBufferParametersIuivNV = (glProgramBufferParametersIuivNVPROC)((intptr_t)function_pointer);
	glProgramBufferParametersIuivNV(target, buffer, index, count, params_address);
}

