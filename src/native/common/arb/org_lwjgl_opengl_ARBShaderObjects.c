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

static glGetShaderSourceARBPROC glGetShaderSourceARB;
static glGetUniformivARBPROC glGetUniformivARB;
static glGetUniformfvARBPROC glGetUniformfvARB;
static glGetActiveUniformARBPROC glGetActiveUniformARB;
static glGetUniformLocationARBPROC glGetUniformLocationARB;
static glGetAttachedObjectsARBPROC glGetAttachedObjectsARB;
static glGetInfoLogARBPROC glGetInfoLogARB;
static glGetObjectParameterivARBPROC glGetObjectParameterivARB;
static glGetObjectParameterfvARBPROC glGetObjectParameterfvARB;
static glUniformMatrix4fvARBPROC glUniformMatrix4fvARB;
static glUniformMatrix3fvARBPROC glUniformMatrix3fvARB;
static glUniformMatrix2fvARBPROC glUniformMatrix2fvARB;
static glUniform4ivARBPROC glUniform4ivARB;
static glUniform3ivARBPROC glUniform3ivARB;
static glUniform2ivARBPROC glUniform2ivARB;
static glUniform1ivARBPROC glUniform1ivARB;
static glUniform4fvARBPROC glUniform4fvARB;
static glUniform3fvARBPROC glUniform3fvARB;
static glUniform2fvARBPROC glUniform2fvARB;
static glUniform1fvARBPROC glUniform1fvARB;
static glUniform4iARBPROC glUniform4iARB;
static glUniform3iARBPROC glUniform3iARB;
static glUniform2iARBPROC glUniform2iARB;
static glUniform1iARBPROC glUniform1iARB;
static glUniform4fARBPROC glUniform4fARB;
static glUniform3fARBPROC glUniform3fARB;
static glUniform2fARBPROC glUniform2fARB;
static glUniform1fARBPROC glUniform1fARB;
static glValidateProgramARBPROC glValidateProgramARB;
static glUseProgramObjectARBPROC glUseProgramObjectARB;
static glLinkProgramARBPROC glLinkProgramARB;
static glAttachObjectARBPROC glAttachObjectARB;
static glCreateProgramObjectARBPROC glCreateProgramObjectARB;
static glCompileShaderARBPROC glCompileShaderARB;
static glShaderSourceARBPROC glShaderSourceARB;
static glCreateShaderObjectARBPROC glCreateShaderObjectARB;
static glDetachObjectARBPROC glDetachObjectARB;
static glGetHandleARBPROC glGetHandleARB;
static glDeleteObjectARBPROC glDeleteObjectARB;

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetShaderSourceARB(JNIEnv *env, jclass clazz, jint obj, jint maxLength, jobject length, jint length_position, jobject source, jint source_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLcharARB *source_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, source)) + source_position;
	glGetShaderSourceARB(obj, maxLength, length_address, source_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformivARB(JNIEnv *env, jclass clazz, jint programObj, jint location, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetUniformivARB(programObj, location, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformfvARB(JNIEnv *env, jclass clazz, jint programObj, jint location, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetUniformfvARB(programObj, location, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetActiveUniformARB(JNIEnv *env, jclass clazz, jint programObj, jint index, jint maxLength, jobject length, jint length_position, jobject size, jint size_position, jobject type, jint type_position, jobject name, jint name_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLint *size_address = ((GLint *)(*env)->GetDirectBufferAddress(env, size)) + size_position;
	GLenum *type_address = ((GLenum *)(*env)->GetDirectBufferAddress(env, type)) + type_position;
	GLcharARB *name_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetActiveUniformARB(programObj, index, maxLength, length_address, size_address, type_address, name_address);
}

static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformLocationARB(JNIEnv *env, jclass clazz, jint programObj, jobject name, jint name_position) {
	const GLcharARB *name_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	GLint __result = glGetUniformLocationARB(programObj, name_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetAttachedObjectsARB(JNIEnv *env, jclass clazz, jint containerObj, jint maxCount, jobject count, jint count_position, jobject obj, jint obj_position) {
	GLsizei *count_address = ((GLsizei *)safeGetBufferAddress(env, count)) + count_position;
	GLhandleARB *obj_address = ((GLhandleARB *)(*env)->GetDirectBufferAddress(env, obj)) + obj_position;
	glGetAttachedObjectsARB(containerObj, maxCount, count_address, obj_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetInfoLogARB(JNIEnv *env, jclass clazz, jint obj, jint maxLength, jobject length, jint length_position, jobject infoLog, jint infoLog_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLcharARB *infoLog_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, infoLog)) + infoLog_position;
	glGetInfoLogARB(obj, maxLength, length_address, infoLog_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterivARB(JNIEnv *env, jclass clazz, jint obj, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetObjectParameterivARB(obj, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterfvARB(JNIEnv *env, jclass clazz, jint obj, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetObjectParameterfvARB(obj, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix4fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix4fvARB(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix3fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix3fvARB(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix2fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix2fvARB(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform4ivARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform3ivARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform2ivARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1ivARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform1ivARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform4fvARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform3fvARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform2fvARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fvARB(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform1fvARB(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4iARB(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1, jint v2, jint v3) {
	glUniform4iARB(location, v0, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3iARB(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1, jint v2) {
	glUniform3iARB(location, v0, v1, v2);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2iARB(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1) {
	glUniform2iARB(location, v0, v1);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1iARB(JNIEnv *env, jclass clazz, jint location, jint v0) {
	glUniform1iARB(location, v0);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2, jfloat v3) {
	glUniform4fARB(location, v0, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2) {
	glUniform3fARB(location, v0, v1, v2);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1) {
	glUniform2fARB(location, v0, v1);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1fARB(JNIEnv *env, jclass clazz, jint location, jfloat v0) {
	glUniform1fARB(location, v0);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glValidateProgramARB(JNIEnv *env, jclass clazz, jint programObj) {
	glValidateProgramARB(programObj);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUseProgramObjectARB(JNIEnv *env, jclass clazz, jint programObj) {
	glUseProgramObjectARB(programObj);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glLinkProgramARB(JNIEnv *env, jclass clazz, jint programObj) {
	glLinkProgramARB(programObj);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glAttachObjectARB(JNIEnv *env, jclass clazz, jint containerObj, jint obj) {
	glAttachObjectARB(containerObj, obj);
}

static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateProgramObjectARB(JNIEnv *env, jclass clazz) {
	GLhandleARB __result = glCreateProgramObjectARB();
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCompileShaderARB(JNIEnv *env, jclass clazz, jint shaderObj) {
	glCompileShaderARB(shaderObj);
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglShaderSourceARB(JNIEnv *env, jclass clazz, jint shader, jint count, jobject string, jint string_position, jint length) {
	const GLcharARB *string_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, string)) + string_position;
	glShaderSourceARB(shader, count, &string_address, &length);
}

static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateShaderObjectARB(JNIEnv *env, jclass clazz, jint shaderType) {
	GLhandleARB __result = glCreateShaderObjectARB(shaderType);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDetachObjectARB(JNIEnv *env, jclass clazz, jint containerObj, jint attachedObj) {
	glDetachObjectARB(containerObj, attachedObj);
}

static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glGetHandleARB(JNIEnv *env, jclass clazz, jint pname) {
	GLhandleARB __result = glGetHandleARB(pname);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDeleteObjectARB(JNIEnv *env, jclass clazz, jint obj) {
	glDeleteObjectARB(obj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetShaderSourceARB", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetShaderSourceARB, "glGetShaderSourceARB", (void *)&glGetShaderSourceARB},
		{"nglGetUniformivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformivARB, "glGetUniformivARB", (void *)&glGetUniformivARB},
		{"nglGetUniformfvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformfvARB, "glGetUniformfvARB", (void *)&glGetUniformfvARB},
		{"nglGetActiveUniformARB", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetActiveUniformARB, "glGetActiveUniformARB", (void *)&glGetActiveUniformARB},
		{"nglGetUniformLocationARB", "(ILjava/nio/ByteBuffer;I)I", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformLocationARB, "glGetUniformLocationARB", (void *)&glGetUniformLocationARB},
		{"nglGetAttachedObjectsARB", "(IILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetAttachedObjectsARB, "glGetAttachedObjectsARB", (void *)&glGetAttachedObjectsARB},
		{"nglGetInfoLogARB", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetInfoLogARB, "glGetInfoLogARB", (void *)&glGetInfoLogARB},
		{"nglGetObjectParameterivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterivARB, "glGetObjectParameterivARB", (void *)&glGetObjectParameterivARB},
		{"nglGetObjectParameterfvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterfvARB, "glGetObjectParameterfvARB", (void *)&glGetObjectParameterfvARB},
		{"nglUniformMatrix4fvARB", "(IIZLjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix4fvARB, "glUniformMatrix4fvARB", (void *)&glUniformMatrix4fvARB},
		{"nglUniformMatrix3fvARB", "(IIZLjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix3fvARB, "glUniformMatrix3fvARB", (void *)&glUniformMatrix3fvARB},
		{"nglUniformMatrix2fvARB", "(IIZLjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix2fvARB, "glUniformMatrix2fvARB", (void *)&glUniformMatrix2fvARB},
		{"nglUniform4ivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4ivARB, "glUniform4ivARB", (void *)&glUniform4ivARB},
		{"nglUniform3ivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3ivARB, "glUniform3ivARB", (void *)&glUniform3ivARB},
		{"nglUniform2ivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2ivARB, "glUniform2ivARB", (void *)&glUniform2ivARB},
		{"nglUniform1ivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1ivARB, "glUniform1ivARB", (void *)&glUniform1ivARB},
		{"nglUniform4fvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fvARB, "glUniform4fvARB", (void *)&glUniform4fvARB},
		{"nglUniform3fvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fvARB, "glUniform3fvARB", (void *)&glUniform3fvARB},
		{"nglUniform2fvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fvARB, "glUniform2fvARB", (void *)&glUniform2fvARB},
		{"nglUniform1fvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fvARB, "glUniform1fvARB", (void *)&glUniform1fvARB},
		{"glUniform4iARB", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4iARB, "glUniform4iARB", (void *)&glUniform4iARB},
		{"glUniform3iARB", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3iARB, "glUniform3iARB", (void *)&glUniform3iARB},
		{"glUniform2iARB", "(III)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2iARB, "glUniform2iARB", (void *)&glUniform2iARB},
		{"glUniform1iARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1iARB, "glUniform1iARB", (void *)&glUniform1iARB},
		{"glUniform4fARB", "(IFFFF)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4fARB, "glUniform4fARB", (void *)&glUniform4fARB},
		{"glUniform3fARB", "(IFFF)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3fARB, "glUniform3fARB", (void *)&glUniform3fARB},
		{"glUniform2fARB", "(IFF)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2fARB, "glUniform2fARB", (void *)&glUniform2fARB},
		{"glUniform1fARB", "(IF)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1fARB, "glUniform1fARB", (void *)&glUniform1fARB},
		{"glValidateProgramARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glValidateProgramARB, "glValidateProgramARB", (void *)&glValidateProgramARB},
		{"glUseProgramObjectARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glUseProgramObjectARB, "glUseProgramObjectARB", (void *)&glUseProgramObjectARB},
		{"glLinkProgramARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glLinkProgramARB, "glLinkProgramARB", (void *)&glLinkProgramARB},
		{"glAttachObjectARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glAttachObjectARB, "glAttachObjectARB", (void *)&glAttachObjectARB},
		{"glCreateProgramObjectARB", "()I", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glCreateProgramObjectARB, "glCreateProgramObjectARB", (void *)&glCreateProgramObjectARB},
		{"glCompileShaderARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glCompileShaderARB, "glCompileShaderARB", (void *)&glCompileShaderARB},
		{"nglShaderSourceARB", "(IILjava/nio/ByteBuffer;II)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_nglShaderSourceARB, "glShaderSourceARB", (void *)&glShaderSourceARB},
		{"glCreateShaderObjectARB", "(I)I", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glCreateShaderObjectARB, "glCreateShaderObjectARB", (void *)&glCreateShaderObjectARB},
		{"glDetachObjectARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glDetachObjectARB, "glDetachObjectARB", (void *)&glDetachObjectARB},
		{"glGetHandleARB", "(I)I", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glGetHandleARB, "glGetHandleARB", (void *)&glGetHandleARB},
		{"glDeleteObjectARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBShaderObjects_glDeleteObjectARB, "glDeleteObjectARB", (void *)&glDeleteObjectARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
