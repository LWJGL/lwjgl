/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetColorTableParameterfvEXTPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetColorTableParameterivEXTPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetColorTableEXTPROC) (GLenum target, GLenum format, GLenum type, GLvoid * data);
typedef void (APIENTRY *glColorSubTableEXTPROC) (GLenum target, GLsizei start, GLsizei count, GLenum format, GLenum type, const GLvoid * data);
typedef void (APIENTRY *glColorTableEXTPROC) (GLenum target, GLenum internalFormat, GLsizei width, GLenum format, GLenum type, const GLvoid * data);

static glGetColorTableParameterfvEXTPROC glGetColorTableParameterfvEXT;
static glGetColorTableParameterivEXTPROC glGetColorTableParameterivEXT;
static glGetColorTableEXTPROC glGetColorTableEXT;
static glColorSubTableEXTPROC glColorSubTableEXT;
static glColorTableEXTPROC glColorTableEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterfvEXT(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetColorTableParameterfvEXT(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterivEXT(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetColorTableParameterivEXT(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableEXT(JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject data, jint data_position) {
	GLvoid *data_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glGetColorTableEXT(target, format, type, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorSubTableEXT(JNIEnv *env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glColorSubTableEXT(target, start, count, format, type, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorTableEXT(JNIEnv *env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glColorTableEXT(target, internalFormat, width, format, type, data_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetColorTableParameterfvEXT", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterfvEXT, "glGetColorTableParameterfvEXT", (void *)&glGetColorTableParameterfvEXT},
		{"nglGetColorTableParameterivEXT", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterivEXT, "glGetColorTableParameterivEXT", (void *)&glGetColorTableParameterivEXT},
		{"nglGetColorTableEXT", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableEXT, "glGetColorTableEXT", (void *)&glGetColorTableEXT},
		{"nglColorSubTableEXT", "(IIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorSubTableEXT, "glColorSubTableEXT", (void *)&glColorSubTableEXT},
		{"nglColorTableEXT", "(IIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorTableEXT, "glColorTableEXT", (void *)&glColorTableEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
