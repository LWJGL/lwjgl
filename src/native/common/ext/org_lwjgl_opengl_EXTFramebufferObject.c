/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include <inttypes.h>
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

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenerateMipmapEXT(JNIEnv *env, jclass clazz, jint target, jlong function_pointer) {
	glGenerateMipmapEXTPROC glGenerateMipmapEXT = (glGenerateMipmapEXTPROC)((intptr_t)function_pointer);
	glGenerateMipmapEXT(target);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetFramebufferAttachmentParameterivEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetFramebufferAttachmentParameterivEXTPROC glGetFramebufferAttachmentParameterivEXT = (glGetFramebufferAttachmentParameterivEXTPROC)((intptr_t)function_pointer);
	glGetFramebufferAttachmentParameterivEXT(target, attachment, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglFramebufferRenderbufferEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint renderbuffertarget, jint renderbuffer, jlong function_pointer) {
	glFramebufferRenderbufferEXTPROC glFramebufferRenderbufferEXT = (glFramebufferRenderbufferEXTPROC)((intptr_t)function_pointer);
	glFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglFramebufferTexture3DEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jint zoffset, jlong function_pointer) {
	glFramebufferTexture3DEXTPROC glFramebufferTexture3DEXT = (glFramebufferTexture3DEXTPROC)((intptr_t)function_pointer);
	glFramebufferTexture3DEXT(target, attachment, textarget, texture, level, zoffset);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglFramebufferTexture2DEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jlong function_pointer) {
	glFramebufferTexture2DEXTPROC glFramebufferTexture2DEXT = (glFramebufferTexture2DEXTPROC)((intptr_t)function_pointer);
	glFramebufferTexture2DEXT(target, attachment, textarget, texture, level);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglFramebufferTexture1DEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jlong function_pointer) {
	glFramebufferTexture1DEXTPROC glFramebufferTexture1DEXT = (glFramebufferTexture1DEXTPROC)((intptr_t)function_pointer);
	glFramebufferTexture1DEXT(target, attachment, textarget, texture, level);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglCheckFramebufferStatusEXT(JNIEnv *env, jclass clazz, jint target, jlong function_pointer) {
	glCheckFramebufferStatusEXTPROC glCheckFramebufferStatusEXT = (glCheckFramebufferStatusEXTPROC)((intptr_t)function_pointer);
	GLenum __result = glCheckFramebufferStatusEXT(target);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenFramebuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject framebuffers, jint framebuffers_position, jlong function_pointer) {
	const GLuint *framebuffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, framebuffers)) + framebuffers_position;
	glGenFramebuffersEXTPROC glGenFramebuffersEXT = (glGenFramebuffersEXTPROC)((intptr_t)function_pointer);
	glGenFramebuffersEXT(n, framebuffers_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteFramebuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject framebuffers, jint framebuffers_position, jlong function_pointer) {
	const GLuint *framebuffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, framebuffers)) + framebuffers_position;
	glDeleteFramebuffersEXTPROC glDeleteFramebuffersEXT = (glDeleteFramebuffersEXTPROC)((intptr_t)function_pointer);
	glDeleteFramebuffersEXT(n, framebuffers_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglBindFramebufferEXT(JNIEnv *env, jclass clazz, jint target, jint framebuffer, jlong function_pointer) {
	glBindFramebufferEXTPROC glBindFramebufferEXT = (glBindFramebufferEXTPROC)((intptr_t)function_pointer);
	glBindFramebufferEXT(target, framebuffer);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglIsFramebufferEXT(JNIEnv *env, jclass clazz, jint framebuffer, jlong function_pointer) {
	glIsFramebufferEXTPROC glIsFramebufferEXT = (glIsFramebufferEXTPROC)((intptr_t)function_pointer);
	GLboolean __result = glIsFramebufferEXT(framebuffer);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetRenderbufferParameterivEXT(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position, jlong function_pointer) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetRenderbufferParameterivEXTPROC glGetRenderbufferParameterivEXT = (glGetRenderbufferParameterivEXTPROC)((intptr_t)function_pointer);
	glGetRenderbufferParameterivEXT(target, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglRenderbufferStorageEXT(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jlong function_pointer) {
	glRenderbufferStorageEXTPROC glRenderbufferStorageEXT = (glRenderbufferStorageEXTPROC)((intptr_t)function_pointer);
	glRenderbufferStorageEXT(target, internalformat, width, height);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenRenderbuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject renderbuffers, jint renderbuffers_position, jlong function_pointer) {
	GLuint *renderbuffers_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, renderbuffers)) + renderbuffers_position;
	glGenRenderbuffersEXTPROC glGenRenderbuffersEXT = (glGenRenderbuffersEXTPROC)((intptr_t)function_pointer);
	glGenRenderbuffersEXT(n, renderbuffers_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteRenderbuffersEXT(JNIEnv *env, jclass clazz, jint n, jobject renderbuffers, jint renderbuffers_position, jlong function_pointer) {
	const GLuint *renderbuffers_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, renderbuffers)) + renderbuffers_position;
	glDeleteRenderbuffersEXTPROC glDeleteRenderbuffersEXT = (glDeleteRenderbuffersEXTPROC)((intptr_t)function_pointer);
	glDeleteRenderbuffersEXT(n, renderbuffers_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglBindRenderbufferEXT(JNIEnv *env, jclass clazz, jint target, jint renderbuffer, jlong function_pointer) {
	glBindRenderbufferEXTPROC glBindRenderbufferEXT = (glBindRenderbufferEXTPROC)((intptr_t)function_pointer);
	glBindRenderbufferEXT(target, renderbuffer);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglIsRenderbufferEXT(JNIEnv *env, jclass clazz, jint renderbuffer, jlong function_pointer) {
	glIsRenderbufferEXTPROC glIsRenderbufferEXT = (glIsRenderbufferEXTPROC)((intptr_t)function_pointer);
	GLboolean __result = glIsRenderbufferEXT(renderbuffer);
	return __result;
}

