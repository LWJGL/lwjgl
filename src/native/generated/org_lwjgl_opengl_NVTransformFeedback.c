/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glBindBufferRangeNVPROC) (GLenum target, GLuint index, GLuint buffer, GLintptr offset, GLsizeiptr size);
typedef void (APIENTRY *glBindBufferOffsetNVPROC) (GLenum target, GLuint index, GLuint buffer, GLintptr offset);
typedef void (APIENTRY *glBindBufferBaseNVPROC) (GLenum target, GLuint index, GLuint buffer);
typedef void (APIENTRY *glTransformFeedbackAttribsNVPROC) (GLsizei count, const GLint * attribs, GLenum bufferMode);
typedef void (APIENTRY *glTransformFeedbackVaryingsNVPROC) (GLuint program, GLsizei count, const GLint * locations, GLenum bufferMode);
typedef void (APIENTRY *glBeginTransformFeedbackNVPROC) (GLenum primitiveMode);
typedef void (APIENTRY *glEndTransformFeedbackNVPROC) ();
typedef GLint (APIENTRY *glGetVaryingLocationNVPROC) (GLuint program, const GLchar * name);
typedef void (APIENTRY *glGetActiveVaryingNVPROC) (GLuint program, GLuint index, GLsizei bufSize, GLsizei * length, GLsizei * size, GLenum * type, GLchar * name);
typedef void (APIENTRY *glActiveVaryingNVPROC) (GLuint program, const GLchar * name);
typedef void (APIENTRY *glGetTransformFeedbackVaryingNVPROC) (GLuint program, GLuint index, GLint * location);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglBindBufferRangeNV(JNIEnv *env, jclass clazz, jint target, jint index, jint buffer, jlong offset, jlong size, jlong function_pointer) {
	glBindBufferRangeNVPROC glBindBufferRangeNV = (glBindBufferRangeNVPROC)((intptr_t)function_pointer);
	glBindBufferRangeNV(target, index, buffer, offset, size);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglBindBufferOffsetNV(JNIEnv *env, jclass clazz, jint target, jint index, jint buffer, jlong offset, jlong function_pointer) {
	glBindBufferOffsetNVPROC glBindBufferOffsetNV = (glBindBufferOffsetNVPROC)((intptr_t)function_pointer);
	glBindBufferOffsetNV(target, index, buffer, offset);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglBindBufferBaseNV(JNIEnv *env, jclass clazz, jint target, jint index, jint buffer, jlong function_pointer) {
	glBindBufferBaseNVPROC glBindBufferBaseNV = (glBindBufferBaseNVPROC)((intptr_t)function_pointer);
	glBindBufferBaseNV(target, index, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglTransformFeedbackAttribsNV(JNIEnv *env, jclass clazz, jint count, jobject attribs, jint attribs_position, jint bufferMode, jlong function_pointer) {
	const GLint *attribs_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, attribs)) + attribs_position;
	glTransformFeedbackAttribsNVPROC glTransformFeedbackAttribsNV = (glTransformFeedbackAttribsNVPROC)((intptr_t)function_pointer);
	glTransformFeedbackAttribsNV(count, attribs_address, bufferMode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglTransformFeedbackVaryingsNV(JNIEnv *env, jclass clazz, jint program, jint count, jobject locations, jint locations_position, jint bufferMode, jlong function_pointer) {
	const GLint *locations_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, locations)) + locations_position;
	glTransformFeedbackVaryingsNVPROC glTransformFeedbackVaryingsNV = (glTransformFeedbackVaryingsNVPROC)((intptr_t)function_pointer);
	glTransformFeedbackVaryingsNV(program, count, locations_address, bufferMode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglBeginTransformFeedbackNV(JNIEnv *env, jclass clazz, jint primitiveMode, jlong function_pointer) {
	glBeginTransformFeedbackNVPROC glBeginTransformFeedbackNV = (glBeginTransformFeedbackNVPROC)((intptr_t)function_pointer);
	glBeginTransformFeedbackNV(primitiveMode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglEndTransformFeedbackNV(JNIEnv *env, jclass clazz, jlong function_pointer) {
	glEndTransformFeedbackNVPROC glEndTransformFeedbackNV = (glEndTransformFeedbackNVPROC)((intptr_t)function_pointer);
	glEndTransformFeedbackNV();
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglGetVaryingLocationNV(JNIEnv *env, jclass clazz, jint program, jobject name, jint name_position, jlong function_pointer) {
	const GLchar *name_address = ((const GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetVaryingLocationNVPROC glGetVaryingLocationNV = (glGetVaryingLocationNVPROC)((intptr_t)function_pointer);
	GLint __result = glGetVaryingLocationNV(program, name_address);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglGetActiveVaryingNV(JNIEnv *env, jclass clazz, jint program, jint index, jint bufSize, jobject length, jint length_position, jobject size, jint size_position, jobject type, jint type_position, jobject name, jint name_position, jlong function_pointer) {
	GLsizei *length_address = ((GLsizei *)(*env)->GetDirectBufferAddress(env, length)) + length_position;
	GLsizei *size_address = ((GLsizei *)(*env)->GetDirectBufferAddress(env, size)) + size_position;
	GLenum *type_address = ((GLenum *)(*env)->GetDirectBufferAddress(env, type)) + type_position;
	GLchar *name_address = ((GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetActiveVaryingNVPROC glGetActiveVaryingNV = (glGetActiveVaryingNVPROC)((intptr_t)function_pointer);
	glGetActiveVaryingNV(program, index, bufSize, length_address, size_address, type_address, name_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglActiveVaryingNV(JNIEnv *env, jclass clazz, jint program, jobject name, jint name_position, jlong function_pointer) {
	const GLchar *name_address = ((const GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glActiveVaryingNVPROC glActiveVaryingNV = (glActiveVaryingNVPROC)((intptr_t)function_pointer);
	glActiveVaryingNV(program, name_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVTransformFeedback_nglGetTransformFeedbackVaryingNV(JNIEnv *env, jclass clazz, jint program, jint index, jobject location, jint location_position, jlong function_pointer) {
	GLint *location_address = ((GLint *)(*env)->GetDirectBufferAddress(env, location)) + location_position;
	glGetTransformFeedbackVaryingNVPROC glGetTransformFeedbackVaryingNV = (glGetTransformFeedbackVaryingNVPROC)((intptr_t)function_pointer);
	glGetTransformFeedbackVaryingNV(program, index, location_address);
}

