/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glCopyTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY *glTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLenum type, const GLvoid * pixels);
typedef void (APIENTRY *glTexImage3DPROC) (GLenum target, GLint level, GLint internalFormat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLenum format, GLenum type, const GLvoid * pixels);
typedef void (APIENTRY *glDrawRangeElementsPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid * indices);

static glCopyTexSubImage3DPROC glCopyTexSubImage3D;
static glTexSubImage3DPROC glTexSubImage3D;
static glTexImage3DPROC glTexImage3D;
static glDrawRangeElementsPROC glDrawRangeElements;

static void JNICALL Java_org_lwjgl_opengl_GL12_glCopyTexSubImage3D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint x, jint y, jint width, jint height) {
	glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
}

static void JNICALL Java_org_lwjgl_opengl_GL12_nglTexSubImage3D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jobject pixels, jint pixels_position) {
	const GLvoid *pixels_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pixels)) + pixels_position));
	glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL12_nglTexSubImage3DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jint pixels_buffer_offset) {
	const GLvoid *pixels_address = ((const GLvoid *)offsetToPointer(pixels_buffer_offset));
	glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL12_nglTexImage3D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalFormat, jint width, jint height, jint depth, jint border, jint format, jint type, jobject pixels, jint pixels_position) {
	const GLvoid *pixels_address = ((const GLvoid *)(((char *)safeGetBufferAddress(env, pixels)) + pixels_position));
	glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL12_nglTexImage3DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalFormat, jint width, jint height, jint depth, jint border, jint format, jint type, jint pixels_buffer_offset) {
	const GLvoid *pixels_address = ((const GLvoid *)offsetToPointer(pixels_buffer_offset));
	glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL12_nglDrawRangeElements(JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject indices, jint indices_position) {
	const GLvoid *indices_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, indices)) + indices_position));
	glDrawRangeElements(mode, start, end, count, type, indices_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL12_nglDrawRangeElementsBO(JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jint indices_buffer_offset) {
	const GLvoid *indices_address = ((const GLvoid *)offsetToPointer(indices_buffer_offset));
	glDrawRangeElements(mode, start, end, count, type, indices_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glCopyTexSubImage3D", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL12_glCopyTexSubImage3D, "glCopyTexSubImage3D", (void *)&glCopyTexSubImage3D},
		{"nglTexSubImage3D", "(IIIIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL12_nglTexSubImage3D, "glTexSubImage3D", (void *)&glTexSubImage3D},
		{"nglTexSubImage3DBO", "(IIIIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL12_nglTexSubImage3DBO, "glTexSubImage3D", (void *)&glTexSubImage3D},
		{"nglTexImage3D", "(IIIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL12_nglTexImage3D, "glTexImage3D", (void *)&glTexImage3D},
		{"nglTexImage3DBO", "(IIIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL12_nglTexImage3DBO, "glTexImage3D", (void *)&glTexImage3D},
		{"nglDrawRangeElements", "(IIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL12_nglDrawRangeElements, "glDrawRangeElements", (void *)&glDrawRangeElements},
		{"nglDrawRangeElementsBO", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_GL12_nglDrawRangeElementsBO, "glDrawRangeElements", (void *)&glDrawRangeElements}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
