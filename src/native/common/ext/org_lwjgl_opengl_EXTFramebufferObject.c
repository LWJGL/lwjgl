/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGenerateMipmapEXTPROC) (GLenum target);
typedef void (APIENTRY *glGetFramebufferAttachmentParameterivEXTPROC) (GLenum target, GLenum attachment, GLenum pname, GLint * params);
typedef void (APIENTRY *glFramebufferRenderbufferEXTPROC) (GLenum target, GLenum attachment, GLenum renderbuffertarget, GLuint renderbuffer);
typedef void (APIENTRY *glFramebufferTexture3DEXTPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level, GLint zoffset);
typedef void (APIENTRY *glFramebufferTexture2DEXTPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level);
typedef void (APIENTRY *glFramebufferTexture1DEXTPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level);
typedef GLenum (APIENTRY *glCheckFramebufferStatusEXTPROC) (GLenum target);
typedef void (APIENTRY *glGenFramebuffersEXTPROC) (GLint n, const GLuint * framebuffers);
typedef void (APIENTRY *glDeleteFramebuffersEXTPROC) (GLint n, const GLuint * framebuffers);
typedef void (APIENTRY *glBindFramebufferEXTPROC) (GLenum target, GLuint framebuffer);
typedef GLboolean (APIENTRY *glIsFramebufferEXTPROC) (GLuint framebuffer);
typedef void (APIENTRY *glGetRenderbufferParameterivEXTPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glRenderbufferStorageEXTPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height);
typedef void (APIENTRY *glGenRenderbuffersEXTPROC) (GLint n, GLuint * renderbuffers);
typedef void (APIENTRY *glDeleteRenderbuffersEXTPROC) (GLint n, const GLuint * renderbuffers);
typedef void (APIENTRY *glBindRenderbufferEXTPROC) (GLenum target, GLuint renderbuffer);
typedef GLboolean (APIENTRY *glIsRenderbufferEXTPROC) (GLuint renderbuffer);

static glGenerateMipmapEXTPROC glGenerateMipmapEXT;
static glGetFramebufferAttachmentParameterivEXTPROC glGetFramebufferAttachmentParameterivEXT;
static glFramebufferRenderbufferEXTPROC glFramebufferRenderbufferEXT;
static glFramebufferTexture3DEXTPROC glFramebufferTexture3DEXT;
static glFramebufferTexture2DEXTPROC glFramebufferTexture2DEXT;
static glFramebufferTexture1DEXTPROC glFramebufferTexture1DEXT;
static glCheckFramebufferStatusEXTPROC glCheckFramebufferStatusEXT;
static glGenFramebuffersEXTPROC glGenFramebuffersEXT;
static glDeleteFramebuffersEXTPROC glDeleteFramebuffersEXT;
static glBindFramebufferEXTPROC glBindFramebufferEXT;
static glIsFramebufferEXTPROC glIsFramebufferEXT;
static glGetRenderbufferParameterivEXTPROC glGetRenderbufferParameterivEXT;
static glRenderbufferStorageEXTPROC glRenderbufferStorageEXT;
static glGenRenderbuffersEXTPROC glGenRenderbuffersEXT;
static glDeleteRenderbuffersEXTPROC glDeleteRenderbuffersEXT;
static glBindRenderbufferEXTPROC glBindRenderbufferEXT;
static glIsRenderbufferEXTPROC glIsRenderbufferEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glGenerateMipmapEXT(JNIEnv *env, jclass clazz, jint target) {
	glGenerateMipmapEXT(target);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetFramebufferAttachmentParameterivEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetFramebufferAttachmentParameterivEXT(target, attachment, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferRenderbufferEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint renderbuffertarget, jint renderbuffer) {
	glFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture3DEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jint zoffset) {
	glFramebufferTexture3DEXT(target, attachment, textarget, texture, level, zoffset);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture2DEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level) {
	glFramebufferTexture2DEXT(target, attachment, textarget, texture, level);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture1DEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level) {
	glFramebufferTexture1DEXT(target, attachment, textarget, texture, level);
}

static jint JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glCheckFramebufferStatusEXT(JNIEnv *env, jclass clazz, jint target) {
	GLenum __result = glCheckFramebufferStatusEXT(target);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenFramebuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject framebuffers, jint framebuffers_position) {
	const GLuint *framebuffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, framebuffers)) + framebuffers_position;
	glGenFramebuffersEXT(n, framebuffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteFramebuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject framebuffers, jint framebuffers_position) {
	const GLuint *framebuffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, framebuffers)) + framebuffers_position;
	glDeleteFramebuffersEXT(n, framebuffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glBindFramebufferEXT(JNIEnv *env, jclass clazz, jint target, jint framebuffer) {
	glBindFramebufferEXT(target, framebuffer);
}

static jboolean JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glIsFramebufferEXT(JNIEnv *env, jclass clazz, jint framebuffer) {
	GLboolean __result = glIsFramebufferEXT(framebuffer);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetRenderbufferParameterivEXT(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetRenderbufferParameterivEXT(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glRenderbufferStorageEXT(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height) {
	glRenderbufferStorageEXT(target, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenRenderbuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject renderbuffers, jint renderbuffers_position) {
	GLuint *renderbuffers_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, renderbuffers)) + renderbuffers_position;
	glGenRenderbuffersEXT(n, renderbuffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteRenderbuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject renderbuffers, jint renderbuffers_position) {
	const GLuint *renderbuffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, renderbuffers)) + renderbuffers_position;
	glDeleteRenderbuffersEXT(n, renderbuffers_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glBindRenderbufferEXT(JNIEnv *env, jclass clazz, jint target, jint renderbuffer) {
	glBindRenderbufferEXT(target, renderbuffer);
}

static jboolean JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glIsRenderbufferEXT(JNIEnv *env, jclass clazz, jint renderbuffer) {
	GLboolean __result = glIsRenderbufferEXT(renderbuffer);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glGenerateMipmapEXT", "(I)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glGenerateMipmapEXT, "glGenerateMipmapEXT", (void *)&glGenerateMipmapEXT},
		{"nglGetFramebufferAttachmentParameterivEXT", "(IIILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetFramebufferAttachmentParameterivEXT, "glGetFramebufferAttachmentParameterivEXT", (void *)&glGetFramebufferAttachmentParameterivEXT},
		{"glFramebufferRenderbufferEXT", "(IIII)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferRenderbufferEXT, "glFramebufferRenderbufferEXT", (void *)&glFramebufferRenderbufferEXT},
		{"glFramebufferTexture3DEXT", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture3DEXT, "glFramebufferTexture3DEXT", (void *)&glFramebufferTexture3DEXT},
		{"glFramebufferTexture2DEXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture2DEXT, "glFramebufferTexture2DEXT", (void *)&glFramebufferTexture2DEXT},
		{"glFramebufferTexture1DEXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture1DEXT, "glFramebufferTexture1DEXT", (void *)&glFramebufferTexture1DEXT},
		{"glCheckFramebufferStatusEXT", "(I)I", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glCheckFramebufferStatusEXT, "glCheckFramebufferStatusEXT", (void *)&glCheckFramebufferStatusEXT},
		{"nglGenFramebuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenFramebuffersEXT, "glGenFramebuffersEXT", (void *)&glGenFramebuffersEXT},
		{"nglDeleteFramebuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteFramebuffersEXT, "glDeleteFramebuffersEXT", (void *)&glDeleteFramebuffersEXT},
		{"glBindFramebufferEXT", "(II)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glBindFramebufferEXT, "glBindFramebufferEXT", (void *)&glBindFramebufferEXT},
		{"glIsFramebufferEXT", "(I)Z", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glIsFramebufferEXT, "glIsFramebufferEXT", (void *)&glIsFramebufferEXT},
		{"nglGetRenderbufferParameterivEXT", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetRenderbufferParameterivEXT, "glGetRenderbufferParameterivEXT", (void *)&glGetRenderbufferParameterivEXT},
		{"glRenderbufferStorageEXT", "(IIII)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glRenderbufferStorageEXT, "glRenderbufferStorageEXT", (void *)&glRenderbufferStorageEXT},
		{"nglGenRenderbuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenRenderbuffersEXT, "glGenRenderbuffersEXT", (void *)&glGenRenderbuffersEXT},
		{"nglDeleteRenderbuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteRenderbuffersEXT, "glDeleteRenderbuffersEXT", (void *)&glDeleteRenderbuffersEXT},
		{"glBindRenderbufferEXT", "(II)V", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glBindRenderbufferEXT, "glBindRenderbufferEXT", (void *)&glBindRenderbufferEXT},
		{"glIsRenderbufferEXT", "(I)Z", (void *)&Java_org_lwjgl_opengl_EXTFramebufferObject_glIsRenderbufferEXT, "glIsRenderbufferEXT", (void *)&glIsRenderbufferEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
