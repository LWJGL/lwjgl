/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glSampleCoveragePROC) (GLclampf value, GLboolean invert);
typedef void (APIENTRY *glMultTransposeMatrixfPROC) (const GLfloat * m);
typedef void (APIENTRY *glLoadTransposeMatrixfPROC) (const GLfloat * m);
typedef void (APIENTRY *glMultiTexCoord4fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r, GLfloat q);
typedef void (APIENTRY *glMultiTexCoord3fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r);
typedef void (APIENTRY *glMultiTexCoord2fPROC) (GLenum target, GLfloat s, GLfloat t);
typedef void (APIENTRY *glMultiTexCoord1fPROC) (GLenum target, GLfloat s);
typedef void (APIENTRY *glGetCompressedTexImagePROC) (GLenum target, GLint lod, GLvoid * img);
typedef void (APIENTRY *glCompressedTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLsizei imageSize, const GLvoid * data);
typedef void (APIENTRY *glCompressedTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const GLvoid * data);
typedef void (APIENTRY *glCompressedTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLsizei imageSize, const GLvoid * data);
typedef void (APIENTRY *glCompressedTexImage3DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLsizei imageSize, const GLvoid * data);
typedef void (APIENTRY *glCompressedTexImage2DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const GLvoid * data);
typedef void (APIENTRY *glCompressedTexImage1DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLint border, GLsizei imageSize, const GLvoid * data);
typedef void (APIENTRY *glClientActiveTexturePROC) (GLenum texture);
typedef void (APIENTRY *glActiveTexturePROC) (GLenum texture);

static glSampleCoveragePROC glSampleCoverage;
static glMultTransposeMatrixfPROC glMultTransposeMatrixf;
static glLoadTransposeMatrixfPROC glLoadTransposeMatrixf;
static glMultiTexCoord4fPROC glMultiTexCoord4f;
static glMultiTexCoord3fPROC glMultiTexCoord3f;
static glMultiTexCoord2fPROC glMultiTexCoord2f;
static glMultiTexCoord1fPROC glMultiTexCoord1f;
static glGetCompressedTexImagePROC glGetCompressedTexImage;
static glCompressedTexSubImage3DPROC glCompressedTexSubImage3D;
static glCompressedTexSubImage2DPROC glCompressedTexSubImage2D;
static glCompressedTexSubImage1DPROC glCompressedTexSubImage1D;
static glCompressedTexImage3DPROC glCompressedTexImage3D;
static glCompressedTexImage2DPROC glCompressedTexImage2D;
static glCompressedTexImage1DPROC glCompressedTexImage1D;
static glClientActiveTexturePROC glClientActiveTexture;
static glActiveTexturePROC glActiveTexture;

static void JNICALL Java_org_lwjgl_opengl_GL13_glSampleCoverage(JNIEnv *env, jclass clazz, jfloat value, jboolean invert) {
	glSampleCoverage(value, invert);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglMultTransposeMatrixf(JNIEnv *env, jclass clazz, jobject m, jint m_position) {
	const GLfloat *m_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, m)) + m_position;
	glMultTransposeMatrixf(m_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglLoadTransposeMatrixf(JNIEnv *env, jclass clazz, jobject m, jint m_position) {
	const GLfloat *m_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, m)) + m_position;
	glLoadTransposeMatrixf(m_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord4f(JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q) {
	glMultiTexCoord4f(target, s, t, r, q);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord3f(JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r) {
	glMultiTexCoord3f(target, s, t, r);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord2f(JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t) {
	glMultiTexCoord2f(target, s, t);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord1f(JNIEnv *env, jclass clazz, jint target, jfloat s) {
	glMultiTexCoord1f(target, s);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglGetCompressedTexImage(JNIEnv *env, jclass clazz, jint target, jint lod, jobject img, jint img_position) {
	GLvoid *img_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, img)) + img_position));
	glGetCompressedTexImage(target, lod, img_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglGetCompressedTexImageBO(JNIEnv *env, jclass clazz, jint target, jint lod, jint img_buffer_offset) {
	GLvoid *img_address = ((GLvoid *)offsetToPointer(img_buffer_offset));
	glGetCompressedTexImage(target, lod, img_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage3D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imageSize, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage3DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imageSize, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imageSize, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage2DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imageSize, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage1D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imageSize, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage1DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imageSize, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage3D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imageSize, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage3DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imageSize, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imageSize, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage2DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imageSize, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage1D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imageSize, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage1DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imageSize, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_glClientActiveTexture(JNIEnv *env, jclass clazz, jint texture) {
	glClientActiveTexture(texture);
}

static void JNICALL Java_org_lwjgl_opengl_GL13_glActiveTexture(JNIEnv *env, jclass clazz, jint texture) {
	glActiveTexture(texture);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glSampleCoverage", "(FZ)V", (void *)&Java_org_lwjgl_opengl_GL13_glSampleCoverage, "glSampleCoverage", (void *)&glSampleCoverage},
		{"nglMultTransposeMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglMultTransposeMatrixf, "glMultTransposeMatrixf", (void *)&glMultTransposeMatrixf},
		{"nglLoadTransposeMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglLoadTransposeMatrixf, "glLoadTransposeMatrixf", (void *)&glLoadTransposeMatrixf},
		{"glMultiTexCoord4f", "(IFFFF)V", (void *)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord4f, "glMultiTexCoord4f", (void *)&glMultiTexCoord4f},
		{"glMultiTexCoord3f", "(IFFF)V", (void *)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord3f, "glMultiTexCoord3f", (void *)&glMultiTexCoord3f},
		{"glMultiTexCoord2f", "(IFF)V", (void *)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord2f, "glMultiTexCoord2f", (void *)&glMultiTexCoord2f},
		{"glMultiTexCoord1f", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord1f, "glMultiTexCoord1f", (void *)&glMultiTexCoord1f},
		{"nglGetCompressedTexImage", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglGetCompressedTexImage, "glGetCompressedTexImage", (void *)&glGetCompressedTexImage},
		{"nglGetCompressedTexImageBO", "(III)V", (void *)&Java_org_lwjgl_opengl_GL13_nglGetCompressedTexImageBO, "glGetCompressedTexImage", (void *)&glGetCompressedTexImage},
		{"nglCompressedTexSubImage3D", "(IIIIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage3D, "glCompressedTexSubImage3D", (void *)&glCompressedTexSubImage3D},
		{"nglCompressedTexSubImage3DBO", "(IIIIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage3DBO, "glCompressedTexSubImage3D", (void *)&glCompressedTexSubImage3D},
		{"nglCompressedTexSubImage2D", "(IIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage2D, "glCompressedTexSubImage2D", (void *)&glCompressedTexSubImage2D},
		{"nglCompressedTexSubImage2DBO", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage2DBO, "glCompressedTexSubImage2D", (void *)&glCompressedTexSubImage2D},
		{"nglCompressedTexSubImage1D", "(IIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage1D, "glCompressedTexSubImage1D", (void *)&glCompressedTexSubImage1D},
		{"nglCompressedTexSubImage1DBO", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage1DBO, "glCompressedTexSubImage1D", (void *)&glCompressedTexSubImage1D},
		{"nglCompressedTexImage3D", "(IIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage3D, "glCompressedTexImage3D", (void *)&glCompressedTexImage3D},
		{"nglCompressedTexImage3DBO", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage3DBO, "glCompressedTexImage3D", (void *)&glCompressedTexImage3D},
		{"nglCompressedTexImage2D", "(IIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage2D, "glCompressedTexImage2D", (void *)&glCompressedTexImage2D},
		{"nglCompressedTexImage2DBO", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage2DBO, "glCompressedTexImage2D", (void *)&glCompressedTexImage2D},
		{"nglCompressedTexImage1D", "(IIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage1D, "glCompressedTexImage1D", (void *)&glCompressedTexImage1D},
		{"nglCompressedTexImage1DBO", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage1DBO, "glCompressedTexImage1D", (void *)&glCompressedTexImage1D},
		{"glClientActiveTexture", "(I)V", (void *)&Java_org_lwjgl_opengl_GL13_glClientActiveTexture, "glClientActiveTexture", (void *)&glClientActiveTexture},
		{"glActiveTexture", "(I)V", (void *)&Java_org_lwjgl_opengl_GL13_glActiveTexture, "glActiveTexture", (void *)&glActiveTexture}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
