/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glVertexAttribs4fvNVPROC) (GLuint index, GLsizei n, const GLfloat * v);
typedef void (APIENTRY *glVertexAttribs4svNVPROC) (GLuint index, GLsizei n, const GLshort * v);
typedef void (APIENTRY *glVertexAttribs3fvNVPROC) (GLuint index, GLsizei n, const GLfloat * v);
typedef void (APIENTRY *glVertexAttribs3svNVPROC) (GLuint index, GLsizei n, const GLshort * v);
typedef void (APIENTRY *glVertexAttribs2fvNVPROC) (GLuint index, GLsizei n, const GLfloat * v);
typedef void (APIENTRY *glVertexAttribs2svNVPROC) (GLuint index, GLsizei n, const GLshort * v);
typedef void (APIENTRY *glVertexAttribs1fvNVPROC) (GLuint index, GLsizei n, const GLfloat * v);
typedef void (APIENTRY *glVertexAttribs1svNVPROC) (GLuint index, GLsizei n, const GLshort * v);
typedef void (APIENTRY *glVertexAttrib4ubNVPROC) (GLuint index, GLubyte x, GLubyte y, GLubyte z, GLubyte w);
typedef void (APIENTRY *glVertexAttrib4fNVPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glVertexAttrib4sNVPROC) (GLuint index, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY *glVertexAttrib3fNVPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glVertexAttrib3sNVPROC) (GLuint index, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY *glVertexAttrib2fNVPROC) (GLuint index, GLfloat x, GLfloat y);
typedef void (APIENTRY *glVertexAttrib2sNVPROC) (GLuint index, GLshort x, GLshort y);
typedef void (APIENTRY *glVertexAttrib1fNVPROC) (GLuint index, GLfloat x);
typedef void (APIENTRY *glVertexAttrib1sNVPROC) (GLuint index, GLshort x);
typedef void (APIENTRY *glVertexAttribPointerNVPROC) (GLuint index, GLint size, GLenum type, GLsizei stride, const GLvoid * buffer);
typedef void (APIENTRY *glTrackMatrixNVPROC) (GLenum target, GLuint address, GLenum matrix, GLenum transform);
typedef void (APIENTRY *glProgramParameters4fvNVPROC) (GLenum target, GLuint index, GLuint count, const GLfloat * params);
typedef void (APIENTRY *glProgramParameter4fNVPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glGetVertexAttribPointervNVPROC) (GLuint index, GLenum parameterName, GLvoid ** pointer);
typedef void (APIENTRY *glGetVertexAttribivNVPROC) (GLuint index, GLenum parameterName, GLint * params);
typedef void (APIENTRY *glGetVertexAttribfvNVPROC) (GLuint index, GLenum parameterName, GLfloat * params);
typedef void (APIENTRY *glGetTrackMatrixivNVPROC) (GLenum target, GLuint address, GLenum parameterName, GLint * params);
typedef void (APIENTRY *glGetProgramParameterfvNVPROC) (GLenum target, GLuint index, GLenum parameterName, GLfloat * params);
typedef void (APIENTRY *glExecuteProgramNVPROC) (GLenum target, GLuint id, const GLfloat * params);

static glVertexAttribs4fvNVPROC glVertexAttribs4fvNV;
static glVertexAttribs4svNVPROC glVertexAttribs4svNV;
static glVertexAttribs3fvNVPROC glVertexAttribs3fvNV;
static glVertexAttribs3svNVPROC glVertexAttribs3svNV;
static glVertexAttribs2fvNVPROC glVertexAttribs2fvNV;
static glVertexAttribs2svNVPROC glVertexAttribs2svNV;
static glVertexAttribs1fvNVPROC glVertexAttribs1fvNV;
static glVertexAttribs1svNVPROC glVertexAttribs1svNV;
static glVertexAttrib4ubNVPROC glVertexAttrib4ubNV;
static glVertexAttrib4fNVPROC glVertexAttrib4fNV;
static glVertexAttrib4sNVPROC glVertexAttrib4sNV;
static glVertexAttrib3fNVPROC glVertexAttrib3fNV;
static glVertexAttrib3sNVPROC glVertexAttrib3sNV;
static glVertexAttrib2fNVPROC glVertexAttrib2fNV;
static glVertexAttrib2sNVPROC glVertexAttrib2sNV;
static glVertexAttrib1fNVPROC glVertexAttrib1fNV;
static glVertexAttrib1sNVPROC glVertexAttrib1sNV;
static glVertexAttribPointerNVPROC glVertexAttribPointerNV;
static glTrackMatrixNVPROC glTrackMatrixNV;
static glProgramParameters4fvNVPROC glProgramParameters4fvNV;
static glProgramParameter4fNVPROC glProgramParameter4fNV;
static glGetVertexAttribPointervNVPROC glGetVertexAttribPointervNV;
static glGetVertexAttribivNVPROC glGetVertexAttribivNV;
static glGetVertexAttribfvNVPROC glGetVertexAttribfvNV;
static glGetTrackMatrixivNVPROC glGetTrackMatrixivNV;
static glGetProgramParameterfvNVPROC glGetProgramParameterfvNV;
static glExecuteProgramNVPROC glExecuteProgramNV;

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4fvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLfloat *v_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs4fvNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4svNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLshort *v_address = ((const GLshort *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs4svNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3fvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLfloat *v_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs3fvNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3svNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLshort *v_address = ((const GLshort *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs3svNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2fvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLfloat *v_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs2fvNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2svNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLshort *v_address = ((const GLshort *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs2svNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1fvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLfloat *v_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs1fvNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1svNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject v, jint v_position) {
	const GLshort *v_address = ((const GLshort *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glVertexAttribs1svNV(index, n, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4ubNV(JNIEnv *env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w) {
	glVertexAttrib4ubNV(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4fNV(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w) {
	glVertexAttrib4fNV(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4sNV(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w) {
	glVertexAttrib4sNV(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3fNV(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z) {
	glVertexAttrib3fNV(index, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3sNV(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z) {
	glVertexAttrib3sNV(index, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2fNV(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y) {
	glVertexAttrib2fNV(index, x, y);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2sNV(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y) {
	glVertexAttrib2sNV(index, x, y);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1fNV(JNIEnv *env, jclass clazz, jint index, jfloat x) {
	glVertexAttrib1fNV(index, x);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1sNV(JNIEnv *env, jclass clazz, jint index, jshort x) {
	glVertexAttrib1sNV(index, x);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNV(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jint stride, jobject buffer, jint buffer_position) {
	const GLvoid *buffer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, buffer)) + buffer_position));
	glVertexAttribPointerNV(index, size, type, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNVBO(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jint stride, jint buffer_buffer_offset) {
	const GLvoid *buffer_address = ((const GLvoid *)offsetToPointer(buffer_buffer_offset));
	glVertexAttribPointerNV(index, size, type, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glTrackMatrixNV(JNIEnv *env, jclass clazz, jint target, jint address, jint matrix, jint transform) {
	glTrackMatrixNV(target, address, matrix, transform);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglProgramParameters4fvNV(JNIEnv *env, jclass clazz, jint target, jint index, jint count, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramParameters4fvNV(target, index, count, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glProgramParameter4fNV(JNIEnv *env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w) {
	glProgramParameter4fNV(target, index, x, y, z, w);
}

static jobject JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribPointervNV(JNIEnv *env, jclass clazz, jint index, jint parameterName, jint result_size) {
	GLvoid * __result;
	glGetVertexAttribPointervNV(index, parameterName, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribivNV(JNIEnv *env, jclass clazz, jint index, jint parameterName, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribivNV(index, parameterName, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribfvNV(JNIEnv *env, jclass clazz, jint index, jint parameterName, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribfvNV(index, parameterName, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetTrackMatrixivNV(JNIEnv *env, jclass clazz, jint target, jint address, jint parameterName, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTrackMatrixivNV(target, address, parameterName, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetProgramParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint index, jint parameterName, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramParameterfvNV(target, index, parameterName, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglExecuteProgramNV(JNIEnv *env, jclass clazz, jint target, jint id, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glExecuteProgramNV(target, id, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglVertexAttribs4fvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4fvNV, "glVertexAttribs4fvNV", (void *)&glVertexAttribs4fvNV},
		{"nglVertexAttribs4svNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4svNV, "glVertexAttribs4svNV", (void *)&glVertexAttribs4svNV},
		{"nglVertexAttribs3fvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3fvNV, "glVertexAttribs3fvNV", (void *)&glVertexAttribs3fvNV},
		{"nglVertexAttribs3svNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3svNV, "glVertexAttribs3svNV", (void *)&glVertexAttribs3svNV},
		{"nglVertexAttribs2fvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2fvNV, "glVertexAttribs2fvNV", (void *)&glVertexAttribs2fvNV},
		{"nglVertexAttribs2svNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2svNV, "glVertexAttribs2svNV", (void *)&glVertexAttribs2svNV},
		{"nglVertexAttribs1fvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1fvNV, "glVertexAttribs1fvNV", (void *)&glVertexAttribs1fvNV},
		{"nglVertexAttribs1svNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1svNV, "glVertexAttribs1svNV", (void *)&glVertexAttribs1svNV},
		{"glVertexAttrib4ubNV", "(IBBBB)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4ubNV, "glVertexAttrib4ubNV", (void *)&glVertexAttrib4ubNV},
		{"glVertexAttrib4fNV", "(IFFFF)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4fNV, "glVertexAttrib4fNV", (void *)&glVertexAttrib4fNV},
		{"glVertexAttrib4sNV", "(ISSSS)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4sNV, "glVertexAttrib4sNV", (void *)&glVertexAttrib4sNV},
		{"glVertexAttrib3fNV", "(IFFF)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3fNV, "glVertexAttrib3fNV", (void *)&glVertexAttrib3fNV},
		{"glVertexAttrib3sNV", "(ISSS)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3sNV, "glVertexAttrib3sNV", (void *)&glVertexAttrib3sNV},
		{"glVertexAttrib2fNV", "(IFF)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2fNV, "glVertexAttrib2fNV", (void *)&glVertexAttrib2fNV},
		{"glVertexAttrib2sNV", "(ISS)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2sNV, "glVertexAttrib2sNV", (void *)&glVertexAttrib2sNV},
		{"glVertexAttrib1fNV", "(IF)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1fNV, "glVertexAttrib1fNV", (void *)&glVertexAttrib1fNV},
		{"glVertexAttrib1sNV", "(IS)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1sNV, "glVertexAttrib1sNV", (void *)&glVertexAttrib1sNV},
		{"nglVertexAttribPointerNV", "(IIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNV, "glVertexAttribPointerNV", (void *)&glVertexAttribPointerNV},
		{"nglVertexAttribPointerNVBO", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNVBO, "glVertexAttribPointerNV", (void *)&glVertexAttribPointerNV},
		{"glTrackMatrixNV", "(IIII)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glTrackMatrixNV, "glTrackMatrixNV", (void *)&glTrackMatrixNV},
		{"nglProgramParameters4fvNV", "(IIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglProgramParameters4fvNV, "glProgramParameters4fvNV", (void *)&glProgramParameters4fvNV},
		{"glProgramParameter4fNV", "(IIFFFF)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_glProgramParameter4fNV, "glProgramParameter4fNV", (void *)&glProgramParameter4fNV},
		{"nglGetVertexAttribPointervNV", "(III)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribPointervNV, "glGetVertexAttribPointervNV", (void *)&glGetVertexAttribPointervNV},
		{"nglGetVertexAttribivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribivNV, "glGetVertexAttribivNV", (void *)&glGetVertexAttribivNV},
		{"nglGetVertexAttribfvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribfvNV, "glGetVertexAttribfvNV", (void *)&glGetVertexAttribfvNV},
		{"nglGetTrackMatrixivNV", "(IIILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetTrackMatrixivNV, "glGetTrackMatrixivNV", (void *)&glGetTrackMatrixivNV},
		{"nglGetProgramParameterfvNV", "(IIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetProgramParameterfvNV, "glGetProgramParameterfvNV", (void *)&glGetProgramParameterfvNV},
		{"nglExecuteProgramNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexProgram_nglExecuteProgramNV, "glExecuteProgramNV", (void *)&glExecuteProgramNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
