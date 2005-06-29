/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glCombinerParameterfNVPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY *glCombinerParameterfvNVPROC) (GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glCombinerParameteriNVPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glCombinerParameterivNVPROC) (GLenum pname, const GLint * params);
typedef void (APIENTRY *glCombinerInputNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY *glCombinerOutputNVPROC) (GLenum stage, GLenum portion, GLenum abOutput, GLenum cdOutput, GLenum sumOutput, GLenum scale, GLenum bias, GLboolean abDotProduct, GLboolean cdDotProduct, GLboolean muxSum);
typedef void (APIENTRY *glFinalCombinerInputNVPROC) (GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY *glGetCombinerInputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetCombinerInputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetCombinerOutputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetCombinerOutputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetFinalCombinerInputParameterfvNVPROC) (GLenum variable, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetFinalCombinerInputParameterivNVPROC) (GLenum variable, GLenum pname, GLint * params);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterfNV(JNIEnv *env, jclass clazz, jint pname, jfloat param, jlong function_pointer) {
	glCombinerParameterfNVPROC glCombinerParameterfNV = (glCombinerParameterfNVPROC)((intptr_t)function_pointer);
	glCombinerParameterfNV(pname, param);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterfvNV(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position, jlong function_pointer) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glCombinerParameterfvNVPROC glCombinerParameterfvNV = (glCombinerParameterfvNVPROC)((intptr_t)function_pointer);
	glCombinerParameterfvNV(pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameteriNV(JNIEnv *env, jclass clazz, jint pname, jint param, jlong function_pointer) {
	glCombinerParameteriNVPROC glCombinerParameteriNV = (glCombinerParameteriNVPROC)((intptr_t)function_pointer);
	glCombinerParameteriNV(pname, param);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterivNV(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position, jlong function_pointer) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glCombinerParameterivNVPROC glCombinerParameterivNV = (glCombinerParameterivNVPROC)((intptr_t)function_pointer);
	glCombinerParameterivNV(pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerInputNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint variable, jint input, jint mapping, jint componentUsage, jlong function_pointer) {
	glCombinerInputNVPROC glCombinerInputNV = (glCombinerInputNVPROC)((intptr_t)function_pointer);
	glCombinerInputNV(stage, portion, variable, input, mapping, componentUsage);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerOutputNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint abOutput, jint cdOutput, jint sumOutput, jint scale, jint bias, jboolean abDotProduct, jboolean cdDotProduct, jboolean muxSum, jlong function_pointer) {
	glCombinerOutputNVPROC glCombinerOutputNV = (glCombinerOutputNVPROC)((intptr_t)function_pointer);
	glCombinerOutputNV(stage, portion, abOutput, cdOutput, sumOutput, scale, bias, abDotProduct, cdDotProduct, muxSum);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglFinalCombinerInputNV(JNIEnv *env, jclass clazz, jint variable, jint input, jint mapping, jint componentUsage, jlong function_pointer) {
	glFinalCombinerInputNVPROC glFinalCombinerInputNV = (glFinalCombinerInputNVPROC)((intptr_t)function_pointer);
	glFinalCombinerInputNV(variable, input, mapping, componentUsage);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterfvNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerInputParameterfvNVPROC glGetCombinerInputParameterfvNV = (glGetCombinerInputParameterfvNVPROC)((intptr_t)function_pointer);
	glGetCombinerInputParameterfvNV(stage, portion, variable, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterivNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerInputParameterivNVPROC glGetCombinerInputParameterivNV = (glGetCombinerInputParameterivNVPROC)((intptr_t)function_pointer);
	glGetCombinerInputParameterivNV(stage, portion, variable, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterfvNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerOutputParameterfvNVPROC glGetCombinerOutputParameterfvNV = (glGetCombinerOutputParameterfvNVPROC)((intptr_t)function_pointer);
	glGetCombinerOutputParameterfvNV(stage, portion, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterivNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerOutputParameterivNVPROC glGetCombinerOutputParameterivNV = (glGetCombinerOutputParameterivNVPROC)((intptr_t)function_pointer);
	glGetCombinerOutputParameterivNV(stage, portion, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterfvNV(JNIEnv *env, jclass clazz, jint variable, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetFinalCombinerInputParameterfvNVPROC glGetFinalCombinerInputParameterfvNV = (glGetFinalCombinerInputParameterfvNVPROC)((intptr_t)function_pointer);
	glGetFinalCombinerInputParameterfvNV(variable, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterivNV(JNIEnv *env, jclass clazz, jint variable, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetFinalCombinerInputParameterivNVPROC glGetFinalCombinerInputParameterivNV = (glGetFinalCombinerInputParameterivNVPROC)((intptr_t)function_pointer);
	glGetFinalCombinerInputParameterivNV(variable, pname, params_address);
}

