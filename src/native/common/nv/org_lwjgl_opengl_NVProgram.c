/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glRequestResidentProgramsNVPROC) (GLsizei n, GLuint * programIDs);
typedef GLboolean (APIENTRY *glAreProgramsResidentNVPROC) (GLsizei n, const GLuint * programIDs, GLboolean * programResidences);
typedef GLboolean (APIENTRY *glIsProgramNVPROC) (GLuint programID);
typedef void (APIENTRY *glGetProgramStringNVPROC) (GLuint programID, GLenum parameterName, GLvoid * paramString);
typedef void (APIENTRY *glGetProgramivNVPROC) (GLuint programID, GLenum parameterName, GLint * params);
typedef void (APIENTRY *glGenProgramsNVPROC) (GLsizei n, GLuint * programs);
typedef void (APIENTRY *glDeleteProgramsNVPROC) (GLsizei n, const GLuint * programs);
typedef void (APIENTRY *glBindProgramNVPROC) (GLenum target, GLuint programID);
typedef void (APIENTRY *glLoadProgramNVPROC) (GLenum target, GLuint programID, GLsizei length, const GLvoid * string);

static glRequestResidentProgramsNVPROC glRequestResidentProgramsNV;
static glAreProgramsResidentNVPROC glAreProgramsResidentNV;
static glIsProgramNVPROC glIsProgramNV;
static glGetProgramStringNVPROC glGetProgramStringNV;
static glGetProgramivNVPROC glGetProgramivNV;
static glGenProgramsNVPROC glGenProgramsNV;
static glDeleteProgramsNVPROC glDeleteProgramsNV;
static glBindProgramNVPROC glBindProgramNV;
static glLoadProgramNVPROC glLoadProgramNV;

static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglRequestResidentProgramsNV(JNIEnv *env, jclass clazz, jint n, jobject programIDs, jint programIDs_position) {
	GLuint *programIDs_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, programIDs)) + programIDs_position;
	glRequestResidentProgramsNV(n, programIDs_address);
}

static jboolean JNICALL Java_org_lwjgl_opengl_NVProgram_nglAreProgramsResidentNV(JNIEnv *env, jclass clazz, jint n, jobject programIDs, jint programIDs_position, jobject programResidences, jint programResidences_position) {
	const GLuint *programIDs_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, programIDs)) + programIDs_position;
	GLboolean *programResidences_address = ((GLboolean *)(*env)->GetDirectBufferAddress(env, programResidences)) + programResidences_position;
	GLboolean __result = glAreProgramsResidentNV(n, programIDs_address, programResidences_address);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_opengl_NVProgram_glIsProgramNV(JNIEnv *env, jclass clazz, jint programID) {
	GLboolean __result = glIsProgramNV(programID);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglGetProgramStringNV(JNIEnv *env, jclass clazz, jint programID, jint parameterName, jobject paramString, jint paramString_position) {
	GLvoid *paramString_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, paramString)) + paramString_position));
	glGetProgramStringNV(programID, parameterName, paramString_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglGetProgramivNV(JNIEnv *env, jclass clazz, jint programID, jint parameterName, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramivNV(programID, parameterName, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglGenProgramsNV(JNIEnv *env, jclass clazz, jint n, jobject programs, jint programs_position) {
	GLuint *programs_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, programs)) + programs_position;
	glGenProgramsNV(n, programs_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglDeleteProgramsNV(JNIEnv *env, jclass clazz, jint n, jobject programs, jint programs_position) {
	const GLuint *programs_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, programs)) + programs_position;
	glDeleteProgramsNV(n, programs_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVProgram_glBindProgramNV(JNIEnv *env, jclass clazz, jint target, jint programID) {
	glBindProgramNV(target, programID);
}

static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglLoadProgramNV(JNIEnv *env, jclass clazz, jint target, jint programID, jint length, jobject string, jint string_position) {
	const GLvoid *string_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, string)) + string_position));
	glLoadProgramNV(target, programID, length, string_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVProgram_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglRequestResidentProgramsNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVProgram_nglRequestResidentProgramsNV, "glRequestResidentProgramsNV", (void *)&glRequestResidentProgramsNV},
		{"nglAreProgramsResidentNV", "(ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)Z", (void *)&Java_org_lwjgl_opengl_NVProgram_nglAreProgramsResidentNV, "glAreProgramsResidentNV", (void *)&glAreProgramsResidentNV},
		{"glIsProgramNV", "(I)Z", (void *)&Java_org_lwjgl_opengl_NVProgram_glIsProgramNV, "glIsProgramNV", (void *)&glIsProgramNV},
		{"nglGetProgramStringNV", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVProgram_nglGetProgramStringNV, "glGetProgramStringNV", (void *)&glGetProgramStringNV},
		{"nglGetProgramivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVProgram_nglGetProgramivNV, "glGetProgramivNV", (void *)&glGetProgramivNV},
		{"nglGenProgramsNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVProgram_nglGenProgramsNV, "glGenProgramsNV", (void *)&glGenProgramsNV},
		{"nglDeleteProgramsNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVProgram_nglDeleteProgramsNV, "glDeleteProgramsNV", (void *)&glDeleteProgramsNV},
		{"glBindProgramNV", "(II)V", (void *)&Java_org_lwjgl_opengl_NVProgram_glBindProgramNV, "glBindProgramNV", (void *)&glBindProgramNV},
		{"nglLoadProgramNV", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVProgram_nglLoadProgramNV, "glLoadProgramNV", (void *)&glLoadProgramNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
