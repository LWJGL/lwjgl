/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetCompressedTexImageARBPROC) (GLenum target, GLint lod, GLvoid * pImg);
typedef void (APIENTRY *glCompressedTexSubImage3DARBPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLsizei imageSize, const GLvoid * pData);
typedef void (APIENTRY *glCompressedTexSubImage2DARBPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const GLvoid * pData);
typedef void (APIENTRY *glCompressedTexSubImage1DARBPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLsizei imageSize, const GLvoid * pData);
typedef void (APIENTRY *glCompressedTexImage3DARBPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLsizei imageSize, const GLvoid * pData);
typedef void (APIENTRY *glCompressedTexImage2DARBPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const GLvoid * pData);
typedef void (APIENTRY *glCompressedTexImage1DARBPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLint border, GLsizei imageSize, const GLvoid * pData);

static glGetCompressedTexImageARBPROC glGetCompressedTexImageARB;
static glCompressedTexSubImage3DARBPROC glCompressedTexSubImage3DARB;
static glCompressedTexSubImage2DARBPROC glCompressedTexSubImage2DARB;
static glCompressedTexSubImage1DARBPROC glCompressedTexSubImage1DARB;
static glCompressedTexImage3DARBPROC glCompressedTexImage3DARB;
static glCompressedTexImage2DARBPROC glCompressedTexImage2DARB;
static glCompressedTexImage1DARBPROC glCompressedTexImage1DARB;

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglGetCompressedTexImageARB(JNIEnv *env, jclass clazz, jint target, jint lod, jobject pImg, jint pImg_position) {
	GLvoid *pImg_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pImg)) + pImg_position));
	glGetCompressedTexImageARB(target, lod, pImg_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglGetCompressedTexImageARBBO(JNIEnv *env, jclass clazz, jint target, jint lod, jint pImg_buffer_offset) {
	GLvoid *pImg_address = ((GLvoid *)offsetToPointer(pImg_buffer_offset));
	glGetCompressedTexImageARB(target, lod, pImg_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage3DARB(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imageSize, jobject pData, jint pData_position) {
	const GLvoid *pData_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pData)) + pData_position));
	glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage3DARBBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imageSize, jint pData_buffer_offset) {
	const GLvoid *pData_address = ((const GLvoid *)offsetToPointer(pData_buffer_offset));
	glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage2DARB(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imageSize, jobject pData, jint pData_position) {
	const GLvoid *pData_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pData)) + pData_position));
	glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, format, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage2DARBBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imageSize, jint pData_buffer_offset) {
	const GLvoid *pData_address = ((const GLvoid *)offsetToPointer(pData_buffer_offset));
	glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, format, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage1DARB(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imageSize, jobject pData, jint pData_position) {
	const GLvoid *pData_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pData)) + pData_position));
	glCompressedTexSubImage1DARB(target, level, xoffset, width, format, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage1DARBBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imageSize, jint pData_buffer_offset) {
	const GLvoid *pData_address = ((const GLvoid *)offsetToPointer(pData_buffer_offset));
	glCompressedTexSubImage1DARB(target, level, xoffset, width, format, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage3DARB(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imageSize, jobject pData, jint pData_position) {
	const GLvoid *pData_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pData)) + pData_position));
	glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage3DARBBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imageSize, jint pData_buffer_offset) {
	const GLvoid *pData_address = ((const GLvoid *)offsetToPointer(pData_buffer_offset));
	glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage2DARB(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imageSize, jobject pData, jint pData_position) {
	const GLvoid *pData_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pData)) + pData_position));
	glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage2DARBBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imageSize, jint pData_buffer_offset) {
	const GLvoid *pData_address = ((const GLvoid *)offsetToPointer(pData_buffer_offset));
	glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage1DARB(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imageSize, jobject pData, jint pData_position) {
	const GLvoid *pData_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pData)) + pData_position));
	glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage1DARBBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imageSize, jint pData_buffer_offset) {
	const GLvoid *pData_address = ((const GLvoid *)offsetToPointer(pData_buffer_offset));
	glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBTextureCompression_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetCompressedTexImageARB", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglGetCompressedTexImageARB, "glGetCompressedTexImageARB", (void *)&glGetCompressedTexImageARB},
		{"nglGetCompressedTexImageARBBO", "(III)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglGetCompressedTexImageARBBO, "glGetCompressedTexImageARB", (void *)&glGetCompressedTexImageARB},
		{"nglCompressedTexSubImage3DARB", "(IIIIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage3DARB, "glCompressedTexSubImage3DARB", (void *)&glCompressedTexSubImage3DARB},
		{"nglCompressedTexSubImage3DARBBO", "(IIIIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage3DARBBO, "glCompressedTexSubImage3DARB", (void *)&glCompressedTexSubImage3DARB},
		{"nglCompressedTexSubImage2DARB", "(IIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage2DARB, "glCompressedTexSubImage2DARB", (void *)&glCompressedTexSubImage2DARB},
		{"nglCompressedTexSubImage2DARBBO", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage2DARBBO, "glCompressedTexSubImage2DARB", (void *)&glCompressedTexSubImage2DARB},
		{"nglCompressedTexSubImage1DARB", "(IIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage1DARB, "glCompressedTexSubImage1DARB", (void *)&glCompressedTexSubImage1DARB},
		{"nglCompressedTexSubImage1DARBBO", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexSubImage1DARBBO, "glCompressedTexSubImage1DARB", (void *)&glCompressedTexSubImage1DARB},
		{"nglCompressedTexImage3DARB", "(IIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage3DARB, "glCompressedTexImage3DARB", (void *)&glCompressedTexImage3DARB},
		{"nglCompressedTexImage3DARBBO", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage3DARBBO, "glCompressedTexImage3DARB", (void *)&glCompressedTexImage3DARB},
		{"nglCompressedTexImage2DARB", "(IIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage2DARB, "glCompressedTexImage2DARB", (void *)&glCompressedTexImage2DARB},
		{"nglCompressedTexImage2DARBBO", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage2DARBBO, "glCompressedTexImage2DARB", (void *)&glCompressedTexImage2DARB},
		{"nglCompressedTexImage1DARB", "(IIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage1DARB, "glCompressedTexImage1DARB", (void *)&glCompressedTexImage1DARB},
		{"nglCompressedTexImage1DARBBO", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBTextureCompression_nglCompressedTexImage1DARBBO, "glCompressedTexImage1DARB", (void *)&glCompressedTexImage1DARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
