/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetVertexAttribPointervARBPROC) (GLuint index, GLenum pname, GLvoid ** result);
typedef void (APIENTRY *glGetVertexAttribivARBPROC) (GLuint index, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetVertexAttribfvARBPROC) (GLuint index, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glDisableVertexAttribArrayARBPROC) (GLuint index);
typedef void (APIENTRY *glEnableVertexAttribArrayARBPROC) (GLuint index);
typedef void (APIENTRY *glVertexAttribPointerARBPROC) (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const GLvoid * buffer);
typedef void (APIENTRY *glVertexAttrib4NubARBPROC) (GLuint index, GLubyte x, GLubyte y, GLubyte z, GLubyte w);
typedef void (APIENTRY *glVertexAttrib4fARBPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glVertexAttrib4sARBPROC) (GLuint index, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY *glVertexAttrib3fARBPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glVertexAttrib3sARBPROC) (GLuint index, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY *glVertexAttrib2fARBPROC) (GLuint index, GLfloat x, GLfloat y);
typedef void (APIENTRY *glVertexAttrib2sARBPROC) (GLuint index, GLshort x, GLshort y);
typedef void (APIENTRY *glVertexAttrib1fARBPROC) (GLuint index, GLfloat x);
typedef void (APIENTRY *glVertexAttrib1sARBPROC) (GLuint index, GLshort x);

static glGetVertexAttribPointervARBPROC glGetVertexAttribPointervARB;
static glGetVertexAttribivARBPROC glGetVertexAttribivARB;
static glGetVertexAttribfvARBPROC glGetVertexAttribfvARB;
static glDisableVertexAttribArrayARBPROC glDisableVertexAttribArrayARB;
static glEnableVertexAttribArrayARBPROC glEnableVertexAttribArrayARB;
static glVertexAttribPointerARBPROC glVertexAttribPointerARB;
static glVertexAttrib4NubARBPROC glVertexAttrib4NubARB;
static glVertexAttrib4fARBPROC glVertexAttrib4fARB;
static glVertexAttrib4sARBPROC glVertexAttrib4sARB;
static glVertexAttrib3fARBPROC glVertexAttrib3fARB;
static glVertexAttrib3sARBPROC glVertexAttrib3sARB;
static glVertexAttrib2fARBPROC glVertexAttrib2fARB;
static glVertexAttrib2sARBPROC glVertexAttrib2sARB;
static glVertexAttrib1fARBPROC glVertexAttrib1fARB;
static glVertexAttrib1sARBPROC glVertexAttrib1sARB;

static jobject JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribPointervARB(JNIEnv *env, jclass clazz, jint index, jint pname, jint result_size) {
	GLvoid * __result;
	glGetVertexAttribPointervARB(index, pname, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribivARB(JNIEnv *env, jclass clazz, jint index, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribivARB(index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribfvARB(JNIEnv *env, jclass clazz, jint index, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribfvARB(index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glDisableVertexAttribArrayARB(JNIEnv *env, jclass clazz, jint index) {
	glDisableVertexAttribArrayARB(index);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glEnableVertexAttribArrayARB(JNIEnv *env, jclass clazz, jint index) {
	glEnableVertexAttribArrayARB(index);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARB(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jobject buffer, jint buffer_position) {
	const GLvoid *buffer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, buffer)) + buffer_position));
	glVertexAttribPointerARB(index, size, type, normalized, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARBBO(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jint buffer_buffer_offset) {
	const GLvoid *buffer_address = ((const GLvoid *)offsetToPointer(buffer_buffer_offset));
	glVertexAttribPointerARB(index, size, type, normalized, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4NubARB(JNIEnv *env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w) {
	glVertexAttrib4NubARB(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4fARB(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w) {
	glVertexAttrib4fARB(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4sARB(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w) {
	glVertexAttrib4sARB(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3fARB(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z) {
	glVertexAttrib3fARB(index, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3sARB(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z) {
	glVertexAttrib3sARB(index, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2fARB(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y) {
	glVertexAttrib2fARB(index, x, y);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2sARB(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y) {
	glVertexAttrib2sARB(index, x, y);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1fARB(JNIEnv *env, jclass clazz, jint index, jfloat x) {
	glVertexAttrib1fARB(index, x);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1sARB(JNIEnv *env, jclass clazz, jint index, jshort x) {
	glVertexAttrib1sARB(index, x);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetVertexAttribPointervARB", "(III)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribPointervARB, "glGetVertexAttribPointervARB", (void *)&glGetVertexAttribPointervARB},
		{"nglGetVertexAttribivARB", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribivARB, "glGetVertexAttribivARB", (void *)&glGetVertexAttribivARB},
		{"nglGetVertexAttribfvARB", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribfvARB, "glGetVertexAttribfvARB", (void *)&glGetVertexAttribfvARB},
		{"glDisableVertexAttribArrayARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glDisableVertexAttribArrayARB, "glDisableVertexAttribArrayARB", (void *)&glDisableVertexAttribArrayARB},
		{"glEnableVertexAttribArrayARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glEnableVertexAttribArrayARB, "glEnableVertexAttribArrayARB", (void *)&glEnableVertexAttribArrayARB},
		{"nglVertexAttribPointerARB", "(IIIZILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARB, "glVertexAttribPointerARB", (void *)&glVertexAttribPointerARB},
		{"nglVertexAttribPointerARBBO", "(IIIZII)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARBBO, "glVertexAttribPointerARB", (void *)&glVertexAttribPointerARB},
		{"glVertexAttrib4NubARB", "(IBBBB)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4NubARB, "glVertexAttrib4NubARB", (void *)&glVertexAttrib4NubARB},
		{"glVertexAttrib4fARB", "(IFFFF)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4fARB, "glVertexAttrib4fARB", (void *)&glVertexAttrib4fARB},
		{"glVertexAttrib4sARB", "(ISSSS)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4sARB, "glVertexAttrib4sARB", (void *)&glVertexAttrib4sARB},
		{"glVertexAttrib3fARB", "(IFFF)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3fARB, "glVertexAttrib3fARB", (void *)&glVertexAttrib3fARB},
		{"glVertexAttrib3sARB", "(ISSS)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3sARB, "glVertexAttrib3sARB", (void *)&glVertexAttrib3sARB},
		{"glVertexAttrib2fARB", "(IFF)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2fARB, "glVertexAttrib2fARB", (void *)&glVertexAttrib2fARB},
		{"glVertexAttrib2sARB", "(ISS)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2sARB, "glVertexAttrib2sARB", (void *)&glVertexAttrib2sARB},
		{"glVertexAttrib1fARB", "(IF)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1fARB, "glVertexAttrib1fARB", (void *)&glVertexAttrib1fARB},
		{"glVertexAttrib1sARB", "(IS)V", (void *)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1sARB, "glVertexAttrib1sARB", (void *)&glVertexAttrib1sARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
