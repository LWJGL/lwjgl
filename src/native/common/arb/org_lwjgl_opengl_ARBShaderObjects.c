/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetShaderSourceARBPROC) (GLhandleARB obj, GLsizei maxLength, GLsizei * length, GLcharARB * source);
typedef void (APIENTRY *glGetUniformivARBPROC) (GLhandleARB programObj, GLint location, GLint * params);
typedef void (APIENTRY *glGetUniformfvARBPROC) (GLhandleARB programObj, GLint location, GLfloat * params);
typedef void (APIENTRY *glGetActiveUniformARBPROC) (GLhandleARB programObj, GLuint index, GLsizei maxLength, GLsizei * length, GLint * size, GLenum * type, GLcharARB * name);
typedef GLint (APIENTRY *glGetUniformLocationARBPROC) (GLhandleARB programObj, const GLcharARB * name);
typedef void (APIENTRY *glGetAttachedObjectsARBPROC) (GLhandleARB containerObj, GLsizei maxCount, GLsizei * count, GLhandleARB * obj);
typedef void (APIENTRY *glGetInfoLogARBPROC) (GLhandleARB obj, GLsizei maxLength, GLsizei * length, GLcharARB * infoLog);
typedef void (APIENTRY *glGetObjectParameterivARBPROC) (GLhandleARB obj, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetObjectParameterfvARBPROC) (GLhandleARB obj, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glUniformMatrix4fvARBPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat * matrices);
typedef void (APIENTRY *glUniformMatrix3fvARBPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat * matrices);
typedef void (APIENTRY *glUniformMatrix2fvARBPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat * matrices);
typedef void (APIENTRY *glUniform4ivARBPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform3ivARBPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform2ivARBPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform1ivARBPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform4fvARBPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform3fvARBPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform2fvARBPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform1fvARBPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform4iARBPROC) (GLint location, GLint v0, GLint v1, GLint v2, GLint v3);
typedef void (APIENTRY *glUniform3iARBPROC) (GLint location, GLint v0, GLint v1, GLint v2);
typedef void (APIENTRY *glUniform2iARBPROC) (GLint location, GLint v0, GLint v1);
typedef void (APIENTRY *glUniform1iARBPROC) (GLint location, GLint v0);
typedef void (APIENTRY *glUniform4fARBPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2, GLfloat v3);
typedef void (APIENTRY *glUniform3fARBPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2);
typedef void (APIENTRY *glUniform2fARBPROC) (GLint location, GLfloat v0, GLfloat v1);
typedef void (APIENTRY *glUniform1fARBPROC) (GLint location, GLfloat v0);
typedef void (APIENTRY *glValidateProgramARBPROC) (GLhandleARB programObj);
typedef void (APIENTRY *glUseProgramObjectARBPROC) (GLhandleARB programObj);
typedef void (APIENTRY *glLinkProgramARBPROC) (GLhandleARB programObj);
typedef void (APIENTRY *glAttachObjectARBPROC) (GLhandleARB containerObj, GLhandleARB obj);
typedef GLhandleARB (APIENTRY *glCreateProgramObjectARBPROC) ();
typedef void (APIENTRY *glCompileShaderARBPROC) (GLhandleARB shaderObj);
typedef void (APIENTRY *glShaderSourceARBPROC) (GLhandleARB shader, GLsizei count, const GLcharARB ** string, const GLint* length);
typedef GLhandleARB (APIENTRY *glCreateShaderObjectARBPROC) (GLenum shaderType);
typedef void (APIENTRY *glDetachObjectARBPROC) (GLhandleARB containerObj, GLhandleARB attachedObj);
typedef GLhandleARB (APIENTRY *glGetHandleARBPROC) (GLenum pname);
typedef void (APIENTRY *glDeleteObjectARBPROC) (GLhandleARB obj);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetShaderSourceARB(JNIEnv *env, jclass clazz, jint obj, jint maxLength, jobject length, jint length_position, jobject source, jint source_position, jlong function_pointer) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLcharARB *source_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, source)) + source_position;
	glGetShaderSourceARBPROC glGetShaderSourceARB = (glGetShaderSourceARBPROC)((intptr_t)function_pointer);
	glGetShaderSourceARB(obj, maxLength, length_address, source_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformivARB(JNIEnv *env, jclass clazz, jint programObj, jint location, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetUniformivARBPROC glGetUniformivARB = (glGetUniformivARBPROC)((intptr_t)function_pointer);
	glGetUniformivARB(programObj, location, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformfvARB(JNIEnv *env, jclass clazz, jint programObj, jint location, jobject params, jint params_position, jlong function_pointer) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetUniformfvARBPROC glGetUniformfvARB = (glGetUniformfvARBPROC)((intptr_t)function_pointer);
	glGetUniformfvARB(programObj, location, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetActiveUniformARB(JNIEnv *env, jclass clazz, jint programObj, jint index, jint maxLength, jobject length, jint length_position, jobject size, jint size_position, jobject type, jint type_position, jobject name, jint name_position, jlong function_pointer) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLint *size_address = ((GLint *)(*env)->GetDirectBufferAddress(env, size)) + size_position;
	GLenum *type_address = ((GLenum *)(*env)->GetDirectBufferAddress(env, type)) + type_position;
	GLcharARB *name_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetActiveUniformARBPROC glGetActiveUniformARB = (glGetActiveUniformARBPROC)((intptr_t)function_pointer);
	glGetActiveUniformARB(programObj, index, maxLength, length_address, size_address, type_address, name_address);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformLocationARB(JNIEnv *env, jclass clazz, jint programObj, jobject name, jint name_position, jlong function_pointer) {
	const GLcharARB *name_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetUniformLocationARBPROC glGetUniformLocationARB = (glGetUniformLocationARBPROC)((intptr_t)function_pointer);
	GLint __result = glGetUniformLocationARB(programObj, name_address);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetAttachedObjectsARB(JNIEnv *env, jclass clazz, jint containerObj, jint maxCount, jobject count, jint count_position, jobject obj, jint obj_position, jlong function_pointer) {
	GLsizei *count_address = ((GLsizei *)safeGetBufferAddress(env, count)) + count_position;
	GLhandleARB *obj_address = ((GLhandleARB *)(*env)->GetDirectBufferAddress(env, obj)) + obj_position;
	glGetAttachedObjectsARBPROC glGetAttachedObjectsARB = (glGetAttachedObjectsARBPROC)((intptr_t)function_pointer);
	glGetAttachedObjectsARB(containerObj, maxCount, count_address, obj_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetInfoLogARB(JNIEnv *env, jclass clazz, jint obj, jint maxLength, jobject length, jint length_position, jobject infoLog, jint infoLog_position, jlong function_pointer) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLcharARB *infoLog_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, infoLog)) + infoLog_position;
	glGetInfoLogARBPROC glGetInfoLogARB = (glGetInfoLogARBPROC)((intptr_t)function_pointer);
	glGetInfoLogARB(obj, maxLength, length_address, infoLog_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterivARB(JNIEnv *env, jclass clazz, jint obj, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetObjectParameterivARBPROC glGetObjectParameterivARB = (glGetObjectParameterivARBPROC)((intptr_t)function_pointer);
	glGetObjectParameterivARB(obj, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterfvARB(JNIEnv *env, jclass clazz, jint obj, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetObjectParameterfvARBPROC glGetObjectParameterfvARB = (glGetObjectParameterfvARBPROC)((intptr_t)function_pointer);
	glGetObjectParameterfvARB(obj, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix4fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position, jlong function_pointer) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix4fvARBPROC glUniformMatrix4fvARB = (glUniformMatrix4fvARBPROC)((intptr_t)function_pointer);
	glUniformMatrix4fvARB(location, count, transpose, matrices_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix3fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position, jlong function_pointer) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix3fvARBPROC glUniformMatrix3fvARB = (glUniformMatrix3fvARBPROC)((intptr_t)function_pointer);
	glUniformMatrix3fvARB(location, count, transpose, matrices_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix2fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position, jlong function_pointer) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix2fvARBPROC glUniformMatrix2fvARB = (glUniformMatrix2fvARBPROC)((intptr_t)function_pointer);
	glUniformMatrix2fvARB(location, count, transpose, matrices_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform4ivARBPROC glUniform4ivARB = (glUniform4ivARBPROC)((intptr_t)function_pointer);
	glUniform4ivARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform3ivARBPROC glUniform3ivARB = (glUniform3ivARBPROC)((intptr_t)function_pointer);
	glUniform3ivARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform2ivARBPROC glUniform2ivARB = (glUniform2ivARBPROC)((intptr_t)function_pointer);
	glUniform2ivARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform1ivARBPROC glUniform1ivARB = (glUniform1ivARBPROC)((intptr_t)function_pointer);
	glUniform1ivARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform4fvARBPROC glUniform4fvARB = (glUniform4fvARBPROC)((intptr_t)function_pointer);
	glUniform4fvARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform3fvARBPROC glUniform3fvARB = (glUniform3fvARBPROC)((intptr_t)function_pointer);
	glUniform3fvARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform2fvARBPROC glUniform2fvARB = (glUniform2fvARBPROC)((intptr_t)function_pointer);
	glUniform2fvARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position, jlong function_pointer) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform1fvARBPROC glUniform1fvARB = (glUniform1fvARBPROC)((intptr_t)function_pointer);
	glUniform1fvARB(location, count, values_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4iARB(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1, jint v2, jint v3, jlong function_pointer) {
	glUniform4iARBPROC glUniform4iARB = (glUniform4iARBPROC)((intptr_t)function_pointer);
	glUniform4iARB(location, v0, v1, v2, v3);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3iARB(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1, jint v2, jlong function_pointer) {
	glUniform3iARBPROC glUniform3iARB = (glUniform3iARBPROC)((intptr_t)function_pointer);
	glUniform3iARB(location, v0, v1, v2);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2iARB(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1, jlong function_pointer) {
	glUniform2iARBPROC glUniform2iARB = (glUniform2iARBPROC)((intptr_t)function_pointer);
	glUniform2iARB(location, v0, v1);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1iARB(JNIEnv *env, jclass clazz, jint location, jint v0, jlong function_pointer) {
	glUniform1iARBPROC glUniform1iARB = (glUniform1iARBPROC)((intptr_t)function_pointer);
	glUniform1iARB(location, v0);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2, jfloat v3, jlong function_pointer) {
	glUniform4fARBPROC glUniform4fARB = (glUniform4fARBPROC)((intptr_t)function_pointer);
	glUniform4fARB(location, v0, v1, v2, v3);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2, jlong function_pointer) {
	glUniform3fARBPROC glUniform3fARB = (glUniform3fARBPROC)((intptr_t)function_pointer);
	glUniform3fARB(location, v0, v1, v2);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1, jlong function_pointer) {
	glUniform2fARBPROC glUniform2fARB = (glUniform2fARBPROC)((intptr_t)function_pointer);
	glUniform2fARB(location, v0, v1);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0, jlong function_pointer) {
	glUniform1fARBPROC glUniform1fARB = (glUniform1fARBPROC)((intptr_t)function_pointer);
	glUniform1fARB(location, v0);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglValidateProgramARB(JNIEnv *env, jclass clazz, jint programObj, jlong function_pointer) {
	glValidateProgramARBPROC glValidateProgramARB = (glValidateProgramARBPROC)((intptr_t)function_pointer);
	glValidateProgramARB(programObj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUseProgramObjectARB(JNIEnv *env, jclass clazz, jint programObj, jlong function_pointer) {
	glUseProgramObjectARBPROC glUseProgramObjectARB = (glUseProgramObjectARBPROC)((intptr_t)function_pointer);
	glUseProgramObjectARB(programObj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglLinkProgramARB(JNIEnv *env, jclass clazz, jint programObj, jlong function_pointer) {
	glLinkProgramARBPROC glLinkProgramARB = (glLinkProgramARBPROC)((intptr_t)function_pointer);
	glLinkProgramARB(programObj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglAttachObjectARB(JNIEnv *env, jclass clazz, jint containerObj, jint obj, jlong function_pointer) {
	glAttachObjectARBPROC glAttachObjectARB = (glAttachObjectARBPROC)((intptr_t)function_pointer);
	glAttachObjectARB(containerObj, obj);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglCreateProgramObjectARB(JNIEnv *env, jclass clazz, jlong function_pointer) {
	glCreateProgramObjectARBPROC glCreateProgramObjectARB = (glCreateProgramObjectARBPROC)((intptr_t)function_pointer);
	GLhandleARB __result = glCreateProgramObjectARB();
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglCompileShaderARB(JNIEnv *env, jclass clazz, jint shaderObj, jlong function_pointer) {
	glCompileShaderARBPROC glCompileShaderARB = (glCompileShaderARBPROC)((intptr_t)function_pointer);
	glCompileShaderARB(shaderObj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglShaderSourceARB(JNIEnv *env, jclass clazz, jint shader, jint count, jobject string, jint string_position, jint length, jlong function_pointer) {
	const GLcharARB *string_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, string)) + string_position;
	glShaderSourceARBPROC glShaderSourceARB = (glShaderSourceARBPROC)((intptr_t)function_pointer);
	glShaderSourceARB(shader, count, &string_address, &length);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglCreateShaderObjectARB(JNIEnv *env, jclass clazz, jint shaderType, jlong function_pointer) {
	glCreateShaderObjectARBPROC glCreateShaderObjectARB = (glCreateShaderObjectARBPROC)((intptr_t)function_pointer);
	GLhandleARB __result = glCreateShaderObjectARB(shaderType);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglDetachObjectARB(JNIEnv *env, jclass clazz, jint containerObj, jint attachedObj, jlong function_pointer) {
	glDetachObjectARBPROC glDetachObjectARB = (glDetachObjectARBPROC)((intptr_t)function_pointer);
	glDetachObjectARB(containerObj, attachedObj);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetHandleARB(JNIEnv *env, jclass clazz, jint pname, jlong function_pointer) {
	glGetHandleARBPROC glGetHandleARB = (glGetHandleARBPROC)((intptr_t)function_pointer);
	GLhandleARB __result = glGetHandleARB(pname);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglDeleteObjectARB(JNIEnv *env, jclass clazz, jint obj, jlong function_pointer) {
	glDeleteObjectARBPROC glDeleteObjectARB = (glDeleteObjectARBPROC)((intptr_t)function_pointer);
	glDeleteObjectARB(obj);
}

