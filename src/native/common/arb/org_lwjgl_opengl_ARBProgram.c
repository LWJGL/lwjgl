/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GLboolean (APIENTRY *glIsProgramARBPROC) (GLuint program);
typedef void (APIENTRY *glGetProgramStringARBPROC) (GLenum target, GLenum parameterName, GLvoid * paramString);
typedef void (APIENTRY *glGetProgramivARBPROC) (GLenum target, GLenum parameterName, GLint * params);
typedef void (APIENTRY *glGetProgramLocalParameterfvARBPROC) (GLenum target, GLuint index, GLfloat * params);
typedef void (APIENTRY *glGetProgramEnvParameterfvARBPROC) (GLenum target, GLuint index, GLfloat * params);
typedef void (APIENTRY *glProgramLocalParameter4fvARBPROC) (GLenum target, GLuint index, const GLfloat * params);
typedef void (APIENTRY *glProgramLocalParameter4fARBPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glProgramEnvParameter4fvARBPROC) (GLenum target, GLuint index, const GLfloat * params);
typedef void (APIENTRY *glProgramEnvParameter4fARBPROC) (GLint target, GLint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glGenProgramsARBPROC) (GLsizei n, GLuint * programs);
typedef void (APIENTRY *glDeleteProgramsARBPROC) (GLsizei n, const GLuint * programs);
typedef void (APIENTRY *glBindProgramARBPROC) (GLenum target, GLuint program);
typedef void (APIENTRY *glProgramStringARBPROC) (GLenum target, GLenum format, GLsizei length, const GLvoid * string);

static glIsProgramARBPROC glIsProgramARB;
static glGetProgramStringARBPROC glGetProgramStringARB;
static glGetProgramivARBPROC glGetProgramivARB;
static glGetProgramLocalParameterfvARBPROC glGetProgramLocalParameterfvARB;
static glGetProgramEnvParameterfvARBPROC glGetProgramEnvParameterfvARB;
static glProgramLocalParameter4fvARBPROC glProgramLocalParameter4fvARB;
static glProgramLocalParameter4fARBPROC glProgramLocalParameter4fARB;
static glProgramEnvParameter4fvARBPROC glProgramEnvParameter4fvARB;
static glProgramEnvParameter4fARBPROC glProgramEnvParameter4fARB;
static glGenProgramsARBPROC glGenProgramsARB;
static glDeleteProgramsARBPROC glDeleteProgramsARB;
static glBindProgramARBPROC glBindProgramARB;
static glProgramStringARBPROC glProgramStringARB;

static jboolean JNICALL Java_org_lwjgl_opengl_ARBProgram_glIsProgramARB(JNIEnv *env, jclass clazz, jint program) {
	GLboolean __result = glIsProgramARB(program);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramStringARB(JNIEnv *env, jclass clazz, jint target, jint parameterName, jobject paramString, jint paramString_position) {
	GLvoid *paramString_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, paramString)) + paramString_position));
	glGetProgramStringARB(target, parameterName, paramString_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramivARB(JNIEnv *env, jclass clazz, jint target, jint parameterName, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramivARB(target, parameterName, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramLocalParameterfvARB(JNIEnv *env, jclass clazz, jint target, jint index, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramLocalParameterfvARB(target, index, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramEnvParameterfvARB(JNIEnv *env, jclass clazz, jint target, jint index, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramEnvParameterfvARB(target, index, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglProgramLocalParameter4fvARB(JNIEnv *env, jclass clazz, jint target, jint index, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramLocalParameter4fvARB(target, index, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_glProgramLocalParameter4fARB(JNIEnv *env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w) {
	glProgramLocalParameter4fARB(target, index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglProgramEnvParameter4fvARB(JNIEnv *env, jclass clazz, jint target, jint index, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramEnvParameter4fvARB(target, index, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_glProgramEnvParameter4fARB(JNIEnv *env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w) {
	glProgramEnvParameter4fARB(target, index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGenProgramsARB(JNIEnv *env, jclass clazz, jint n, jobject programs, jint programs_position) {
	GLuint *programs_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, programs)) + programs_position;
	glGenProgramsARB(n, programs_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglDeleteProgramsARB(JNIEnv *env, jclass clazz, jint n, jobject programs, jint programs_position) {
	const GLuint *programs_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, programs)) + programs_position;
	glDeleteProgramsARB(n, programs_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_glBindProgramARB(JNIEnv *env, jclass clazz, jint target, jint program) {
	glBindProgramARB(target, program);
}

static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglProgramStringARB(JNIEnv *env, jclass clazz, jint target, jint format, jint length, jobject string, jint string_position) {
	const GLvoid *string_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, string)) + string_position));
	glProgramStringARB(target, format, length, string_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBProgram_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glIsProgramARB", "(I)Z", (void *)&Java_org_lwjgl_opengl_ARBProgram_glIsProgramARB, "glIsProgramARB", (void *)&glIsProgramARB},
		{"nglGetProgramStringARB", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramStringARB, "glGetProgramStringARB", (void *)&glGetProgramStringARB},
		{"nglGetProgramivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramivARB, "glGetProgramivARB", (void *)&glGetProgramivARB},
		{"nglGetProgramLocalParameterfvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramLocalParameterfvARB, "glGetProgramLocalParameterfvARB", (void *)&glGetProgramLocalParameterfvARB},
		{"nglGetProgramEnvParameterfvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramEnvParameterfvARB, "glGetProgramEnvParameterfvARB", (void *)&glGetProgramEnvParameterfvARB},
		{"nglProgramLocalParameter4fvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglProgramLocalParameter4fvARB, "glProgramLocalParameter4fvARB", (void *)&glProgramLocalParameter4fvARB},
		{"glProgramLocalParameter4fARB", "(IIFFFF)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_glProgramLocalParameter4fARB, "glProgramLocalParameter4fARB", (void *)&glProgramLocalParameter4fARB},
		{"nglProgramEnvParameter4fvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglProgramEnvParameter4fvARB, "glProgramEnvParameter4fvARB", (void *)&glProgramEnvParameter4fvARB},
		{"glProgramEnvParameter4fARB", "(IIFFFF)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_glProgramEnvParameter4fARB, "glProgramEnvParameter4fARB", (void *)&glProgramEnvParameter4fARB},
		{"nglGenProgramsARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglGenProgramsARB, "glGenProgramsARB", (void *)&glGenProgramsARB},
		{"nglDeleteProgramsARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglDeleteProgramsARB, "glDeleteProgramsARB", (void *)&glDeleteProgramsARB},
		{"glBindProgramARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_glBindProgramARB, "glBindProgramARB", (void *)&glBindProgramARB},
		{"nglProgramStringARB", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBProgram_nglProgramStringARB, "glProgramStringARB", (void *)&glProgramStringARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
