/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glProgramBufferParametersfvNVPROC) (GLenum target, GLuint buffer, GLuint index, GLsizei count, const GLfloat * params);
typedef void (APIENTRY *glProgramBufferParametersIivNVPROC) (GLenum target, GLuint buffer, GLuint index, GLsizei count, const GLint * params);
typedef void (APIENTRY *glProgramBufferParametersIuivNVPROC) (GLenum target, GLuint buffer, GLuint index, GLuint count, const GLuint * params);

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

