/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glMultiTexCoord4sARBPROC) (GLenum target, GLshort s, GLshort t, GLshort r, GLshort q);
typedef void (APIENTRY *glMultiTexCoord4iARBPROC) (GLenum target, GLint s, GLint t, GLint r, GLint q);
typedef void (APIENTRY *glMultiTexCoord4fARBPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r, GLfloat q);
typedef void (APIENTRY *glMultiTexCoord3sARBPROC) (GLenum target, GLshort s, GLshort t, GLshort r);
typedef void (APIENTRY *glMultiTexCoord3iARBPROC) (GLenum target, GLint s, GLint t, GLint r);
typedef void (APIENTRY *glMultiTexCoord3fARBPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r);
typedef void (APIENTRY *glMultiTexCoord2sARBPROC) (GLenum target, GLshort s, GLshort t);
typedef void (APIENTRY *glMultiTexCoord2iARBPROC) (GLenum target, GLint s, GLint t);
typedef void (APIENTRY *glMultiTexCoord2fARBPROC) (GLenum target, GLfloat s, GLfloat t);
typedef void (APIENTRY *glMultiTexCoord1sARBPROC) (GLenum target, GLshort s);
typedef void (APIENTRY *glMultiTexCoord1iARBPROC) (GLenum target, GLint s);
typedef void (APIENTRY *glMultiTexCoord1fARBPROC) (GLenum target, GLfloat s);
typedef void (APIENTRY *glActiveTextureARBPROC) (GLenum texture);
typedef void (APIENTRY *glClientActiveTextureARBPROC) (GLenum texture);

static glMultiTexCoord4sARBPROC glMultiTexCoord4sARB;
static glMultiTexCoord4iARBPROC glMultiTexCoord4iARB;
static glMultiTexCoord4fARBPROC glMultiTexCoord4fARB;
static glMultiTexCoord3sARBPROC glMultiTexCoord3sARB;
static glMultiTexCoord3iARBPROC glMultiTexCoord3iARB;
static glMultiTexCoord3fARBPROC glMultiTexCoord3fARB;
static glMultiTexCoord2sARBPROC glMultiTexCoord2sARB;
static glMultiTexCoord2iARBPROC glMultiTexCoord2iARB;
static glMultiTexCoord2fARBPROC glMultiTexCoord2fARB;
static glMultiTexCoord1sARBPROC glMultiTexCoord1sARB;
static glMultiTexCoord1iARBPROC glMultiTexCoord1iARB;
static glMultiTexCoord1fARBPROC glMultiTexCoord1fARB;
static glActiveTextureARBPROC glActiveTextureARB;
static glClientActiveTextureARBPROC glClientActiveTextureARB;

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4sARB(JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q) {
	glMultiTexCoord4sARB(target, s, t, r, q);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4iARB(JNIEnv *env, jclass clazz, jint target, jint s, jint t, jint r, jint q) {
	glMultiTexCoord4iARB(target, s, t, r, q);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4fARB(JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q) {
	glMultiTexCoord4fARB(target, s, t, r, q);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3sARB(JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r) {
	glMultiTexCoord3sARB(target, s, t, r);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3iARB(JNIEnv *env, jclass clazz, jint target, jint s, jint t, jint r) {
	glMultiTexCoord3iARB(target, s, t, r);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3fARB(JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r) {
	glMultiTexCoord3fARB(target, s, t, r);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2sARB(JNIEnv *env, jclass clazz, jint target, jshort s, jshort t) {
	glMultiTexCoord2sARB(target, s, t);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2iARB(JNIEnv *env, jclass clazz, jint target, jint s, jint t) {
	glMultiTexCoord2iARB(target, s, t);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2fARB(JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t) {
	glMultiTexCoord2fARB(target, s, t);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1sARB(JNIEnv *env, jclass clazz, jint target, jshort s) {
	glMultiTexCoord1sARB(target, s);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1iARB(JNIEnv *env, jclass clazz, jint target, jint s) {
	glMultiTexCoord1iARB(target, s);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1fARB(JNIEnv *env, jclass clazz, jint target, jfloat s) {
	glMultiTexCoord1fARB(target, s);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glActiveTextureARB(JNIEnv *env, jclass clazz, jint texture) {
	glActiveTextureARB(texture);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glClientActiveTextureARB(JNIEnv *env, jclass clazz, jint texture) {
	glClientActiveTextureARB(texture);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glMultiTexCoord4sARB", "(ISSSS)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4sARB, "glMultiTexCoord4sARB", (void *)&glMultiTexCoord4sARB},
		{"glMultiTexCoord4iARB", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4iARB, "glMultiTexCoord4iARB", (void *)&glMultiTexCoord4iARB},
		{"glMultiTexCoord4fARB", "(IFFFF)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4fARB, "glMultiTexCoord4fARB", (void *)&glMultiTexCoord4fARB},
		{"glMultiTexCoord3sARB", "(ISSS)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3sARB, "glMultiTexCoord3sARB", (void *)&glMultiTexCoord3sARB},
		{"glMultiTexCoord3iARB", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3iARB, "glMultiTexCoord3iARB", (void *)&glMultiTexCoord3iARB},
		{"glMultiTexCoord3fARB", "(IFFF)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3fARB, "glMultiTexCoord3fARB", (void *)&glMultiTexCoord3fARB},
		{"glMultiTexCoord2sARB", "(ISS)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2sARB, "glMultiTexCoord2sARB", (void *)&glMultiTexCoord2sARB},
		{"glMultiTexCoord2iARB", "(III)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2iARB, "glMultiTexCoord2iARB", (void *)&glMultiTexCoord2iARB},
		{"glMultiTexCoord2fARB", "(IFF)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2fARB, "glMultiTexCoord2fARB", (void *)&glMultiTexCoord2fARB},
		{"glMultiTexCoord1sARB", "(IS)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1sARB, "glMultiTexCoord1sARB", (void *)&glMultiTexCoord1sARB},
		{"glMultiTexCoord1iARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1iARB, "glMultiTexCoord1iARB", (void *)&glMultiTexCoord1iARB},
		{"glMultiTexCoord1fARB", "(IF)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1fARB, "glMultiTexCoord1fARB", (void *)&glMultiTexCoord1fARB},
		{"glActiveTextureARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glActiveTextureARB, "glActiveTextureARB", (void *)&glActiveTextureARB},
		{"glClientActiveTextureARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBMultitexture_glClientActiveTextureARB, "glClientActiveTextureARB", (void *)&glClientActiveTextureARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
