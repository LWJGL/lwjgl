/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetVariantArrayObjectivATIPROC) (GLuint id, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetVariantArrayObjectfvATIPROC) (GLuint id, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glVariantArrayObjectATIPROC) (GLuint id, GLenum type, GLsizei stride, GLuint buffer, GLuint offset);
typedef void (APIENTRY *glGetArrayObjectivATIPROC) (GLenum array, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetArrayObjectfvATIPROC) (GLenum array, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glArrayObjectATIPROC) (GLenum array, GLint size, GLenum type, GLsizei stride, GLuint buffer, GLuint offset);
typedef void (APIENTRY *glFreeObjectBufferATIPROC) (GLuint buffer);
typedef void (APIENTRY *glGetObjectBufferivATIPROC) (GLuint buffer, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetObjectBufferfvATIPROC) (GLuint buffer, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glUpdateObjectBufferATIPROC) (GLuint buffer, GLuint offset, GLsizei size, const GLvoid * pPointer, GLenum preserve);
typedef GLboolean (APIENTRY *glIsObjectBufferATIPROC) (GLuint buffer);
typedef GLuint (APIENTRY *glNewObjectBufferATIPROC) (GLsizei size, const GLvoid * pPointer, GLenum usage);

static glGetVariantArrayObjectivATIPROC glGetVariantArrayObjectivATI;
static glGetVariantArrayObjectfvATIPROC glGetVariantArrayObjectfvATI;
static glVariantArrayObjectATIPROC glVariantArrayObjectATI;
static glGetArrayObjectivATIPROC glGetArrayObjectivATI;
static glGetArrayObjectfvATIPROC glGetArrayObjectfvATI;
static glArrayObjectATIPROC glArrayObjectATI;
static glFreeObjectBufferATIPROC glFreeObjectBufferATI;
static glGetObjectBufferivATIPROC glGetObjectBufferivATI;
static glGetObjectBufferfvATIPROC glGetObjectBufferfvATI;
static glUpdateObjectBufferATIPROC glUpdateObjectBufferATI;
static glIsObjectBufferATIPROC glIsObjectBufferATI;
static glNewObjectBufferATIPROC glNewObjectBufferATI;

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectivATI(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVariantArrayObjectivATI(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectfvATI(JNIEnv *env, jclass clazz, jint id, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVariantArrayObjectfvATI(id, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glVariantArrayObjectATI(JNIEnv *env, jclass clazz, jint id, jint type, jint stride, jint buffer, jint offset) {
	glVariantArrayObjectATI(id, type, stride, buffer, offset);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectivATI(JNIEnv *env, jclass clazz, jint array, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetArrayObjectivATI(array, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectfvATI(JNIEnv *env, jclass clazz, jint array, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetArrayObjectfvATI(array, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glArrayObjectATI(JNIEnv *env, jclass clazz, jint array, jint size, jint type, jint stride, jint buffer, jint offset) {
	glArrayObjectATI(array, size, type, stride, buffer, offset);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glFreeObjectBufferATI(JNIEnv *env, jclass clazz, jint buffer) {
	glFreeObjectBufferATI(buffer);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferivATI(JNIEnv *env, jclass clazz, jint buffer, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetObjectBufferivATI(buffer, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferfvATI(JNIEnv *env, jclass clazz, jint buffer, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetObjectBufferfvATI(buffer, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglUpdateObjectBufferATI(JNIEnv *env, jclass clazz, jint buffer, jint offset, jint size, jobject pPointer, jint pPointer_position, jint preserve) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glUpdateObjectBufferATI(buffer, offset, size, pPointer_address, preserve);
}

static jboolean JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glIsObjectBufferATI(JNIEnv *env, jclass clazz, jint buffer) {
	GLboolean __result = glIsObjectBufferATI(buffer);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglNewObjectBufferATI(JNIEnv *env, jclass clazz, jint size, jobject pPointer, jint pPointer_position, jint usage) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)safeGetBufferAddress(env, pPointer)) + pPointer_position));
	GLuint __result = glNewObjectBufferATI(size, pPointer_address, usage);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetVariantArrayObjectivATI", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectivATI, "glGetVariantArrayObjectivATI", (void *)&glGetVariantArrayObjectivATI},
		{"nglGetVariantArrayObjectfvATI", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectfvATI, "glGetVariantArrayObjectfvATI", (void *)&glGetVariantArrayObjectfvATI},
		{"glVariantArrayObjectATI", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glVariantArrayObjectATI, "glVariantArrayObjectATI", (void *)&glVariantArrayObjectATI},
		{"nglGetArrayObjectivATI", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectivATI, "glGetArrayObjectivATI", (void *)&glGetArrayObjectivATI},
		{"nglGetArrayObjectfvATI", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectfvATI, "glGetArrayObjectfvATI", (void *)&glGetArrayObjectfvATI},
		{"glArrayObjectATI", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glArrayObjectATI, "glArrayObjectATI", (void *)&glArrayObjectATI},
		{"glFreeObjectBufferATI", "(I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glFreeObjectBufferATI, "glFreeObjectBufferATI", (void *)&glFreeObjectBufferATI},
		{"nglGetObjectBufferivATI", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferivATI, "glGetObjectBufferivATI", (void *)&glGetObjectBufferivATI},
		{"nglGetObjectBufferfvATI", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferfvATI, "glGetObjectBufferfvATI", (void *)&glGetObjectBufferfvATI},
		{"nglUpdateObjectBufferATI", "(IIILjava/nio/Buffer;II)V", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglUpdateObjectBufferATI, "glUpdateObjectBufferATI", (void *)&glUpdateObjectBufferATI},
		{"glIsObjectBufferATI", "(I)Z", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glIsObjectBufferATI, "glIsObjectBufferATI", (void *)&glIsObjectBufferATI},
		{"nglNewObjectBufferATI", "(ILjava/nio/Buffer;II)I", (void *)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglNewObjectBufferATI, "glNewObjectBufferATI", (void *)&glNewObjectBufferATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
