/*
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

// ----------------------------------
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.EXTFramebufferObject
// ----------------------------------

#include "extgl.h"

typedef GLboolean (APIENTRY * glIsRenderbufferEXTPROC) (GLuint renderbuffer);
typedef GLvoid (APIENTRY * glBindRenderbufferEXTPROC) (GLenum target, GLuint renderbuffer);
typedef GLvoid (APIENTRY * glDeleteRenderbuffersEXTPROC) (GLsizei n, const GLuint *renderbuffers);
typedef GLvoid (APIENTRY * glGenRenderbuffersEXTPROC) (GLsizei n, GLuint *renderbuffers);
typedef GLvoid (APIENTRY * glRenderbufferStorageEXTPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height);
typedef GLvoid (APIENTRY * glGetRenderbufferParameterivEXTPROC) (GLenum target, GLenum pname, GLint* params);
typedef GLboolean (APIENTRY * glIsFramebufferEXTPROC) (GLuint framebuffer);
typedef GLvoid (APIENTRY * glBindFramebufferEXTPROC) (GLenum target, GLuint framebuffer);
typedef GLvoid (APIENTRY * glDeleteFramebuffersEXTPROC) (GLsizei n, const GLuint *framebuffers);
typedef GLvoid (APIENTRY * glGenFramebuffersEXTPROC) (GLsizei n, GLuint *framebuffers);
typedef GLenum (APIENTRY * glCheckFramebufferStatusEXTPROC) (GLenum target);
typedef GLvoid (APIENTRY * glFramebufferTexture1DEXTPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level);
typedef GLvoid (APIENTRY * glFramebufferTexture2DEXTPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level);
typedef GLvoid (APIENTRY * glFramebufferTexture3DEXTPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level, GLint zoffset);
typedef GLvoid (APIENTRY * glFramebufferRenderbufferEXTPROC) (GLenum target, GLenum attachment, GLenum renderbuffertarget, GLuint renderbuffer);
typedef GLvoid (APIENTRY * glGetFramebufferAttachmentParameterivEXTPROC) (GLenum target, GLenum attachment, GLenum pname, GLint *params);
typedef GLvoid (APIENTRY * glGenerateMipmapEXTPROC) (GLenum target);

static glIsRenderbufferEXTPROC glIsRenderbufferEXT;
static glBindRenderbufferEXTPROC glBindRenderbufferEXT;
static glDeleteRenderbuffersEXTPROC glDeleteRenderbuffersEXT;
static glGenRenderbuffersEXTPROC glGenRenderbuffersEXT;
static glRenderbufferStorageEXTPROC glRenderbufferStorageEXT;
static glGetRenderbufferParameterivEXTPROC glGetRenderbufferParameterivEXT;
static glIsFramebufferEXTPROC glIsFramebufferEXT;
static glBindFramebufferEXTPROC glBindFramebufferEXT;
static glDeleteFramebuffersEXTPROC glDeleteFramebuffersEXT;
static glGenFramebuffersEXTPROC glGenFramebuffersEXT;
static glCheckFramebufferStatusEXTPROC glCheckFramebufferStatusEXT;
static glFramebufferTexture1DEXTPROC glFramebufferTexture1DEXT;
static glFramebufferTexture2DEXTPROC glFramebufferTexture2DEXT;
static glFramebufferTexture3DEXTPROC glFramebufferTexture3DEXT;
static glFramebufferRenderbufferEXTPROC glFramebufferRenderbufferEXT;
static glGetFramebufferAttachmentParameterivEXTPROC glGetFramebufferAttachmentParameterivEXT;
static glGenerateMipmapEXTPROC glGenerateMipmapEXT;

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glIsRenderbufferEXT
 */
static jboolean JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glIsRenderbufferEXT
	(JNIEnv * env, jclass clazz, jint renderbuffer)
{
	return glIsRenderbufferEXT(renderbuffer);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glBindRenderbufferEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glBindRenderbufferEXT
	(JNIEnv * env, jclass clazz, jint target, jint renderbuffer)
{
	glBindRenderbufferEXT(target, renderbuffer);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	nglDeleteRenderbuffersEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteRenderbuffersEXT
	(JNIEnv * env, jclass clazz, jint n, jobject renderbuffers, jint offset)
{
	const GLuint *address = (const GLuint *)(*env)->GetDirectBufferAddress(env, renderbuffers) + offset;
	glDeleteRenderbuffersEXT(n, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	nglGenRenderbuffersEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenRenderbuffersEXT
	(JNIEnv * env, jclass clazz, jint n, jobject renderbuffers, jint offset)
{
	GLuint *address = (GLuint *)(*env)->GetDirectBufferAddress(env, renderbuffers) + offset;
	glGenRenderbuffersEXT(n, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glRenderbufferStorageEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glRenderbufferStorageEXT
	(JNIEnv * env, jclass clazz, jint target, jint internalformat, jint width, jint height)
{
	glRenderbufferStorageEXT(target, internalformat, width, height);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	nglGetRenderbufferParameterivEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetRenderbufferParameterivEXT
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint offset)
{
	GLint *address = (GLint *)(*env)->GetDirectBufferAddress(env, params) + offset;
	glGetRenderbufferParameterivEXT(target, pname, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glIsFramebufferEXT
 */
static jboolean JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glIsFramebufferEXT
	(JNIEnv * env, jclass clazz, jint framebuffer)
{
	return glIsFramebufferEXT(framebuffer);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glBindFramebufferEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glBindFramebufferEXT
	(JNIEnv * env, jclass clazz, jint target, jint framebuffer)
{
	glBindFramebufferEXT(target, framebuffer);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	nglDeleteFramebuffersEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteFramebuffersEXT
	(JNIEnv * env, jclass clazz, jint n, jobject framebuffers, jint offset)
{
	const GLuint *address = (const GLuint *)(*env)->GetDirectBufferAddress(env, framebuffers) + offset;
	glDeleteFramebuffersEXT(n, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	nglGenFramebuffersEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenFramebuffersEXT
	(JNIEnv * env, jclass clazz, jint n, jobject framebuffers, jint offset)
{
	GLuint *address = (GLuint *)(*env)->GetDirectBufferAddress(env, framebuffers) + offset;
	glGenFramebuffersEXT(n, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glCheckFramebufferStatusEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glCheckFramebufferStatusEXT
	(JNIEnv * env, jclass clazz, jint target)
{
	return glCheckFramebufferStatusEXT(target);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glFramebufferTexture1DEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture1DEXT
	(JNIEnv * env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level)
{
	glFramebufferTexture1DEXT(target, attachment, textarget, texture, level);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glFramebufferTexture2DEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture2DEXT
	(JNIEnv * env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level)
{
	glFramebufferTexture2DEXT(target, attachment, textarget, texture, level);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glFramebufferTexture3DEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture3DEXT
	(JNIEnv * env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jint zoffset)
{
	glFramebufferTexture3DEXT(target, attachment, textarget, texture, level, zoffset);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glFramebufferRenderbufferEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferRenderbufferEXT
	(JNIEnv * env, jclass clazz, jint target, jint attachment, jint renderbuffertarget, jint renderbuffer)
{
	glFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	nglGetFramebufferAttachmentParameterivEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetFramebufferAttachmentParameterivEXT
	(JNIEnv * env, jclass clazz, jint target, jint attachment, jint pname, jobject params, jint offset)
{
	GLint *address = (GLint *)(*env)->GetDirectBufferAddress(env, params) + offset;
	glGetFramebufferAttachmentParameterivEXT(target, attachment, pname, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTFramebufferObject
 * Method:	glGenerateMipmapEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_glGenerateMipmapEXT
	(JNIEnv * env, jclass clazz, jint target)
{
	glGenerateMipmapEXT(target);
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFramebufferObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glIsRenderbufferEXT", "(I)Z", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glIsRenderbufferEXT, "glIsRenderbufferEXT", (void*)&glIsRenderbufferEXT},
		{"glBindRenderbufferEXT", "(II)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glBindRenderbufferEXT, "glBindRenderbufferEXT", (void*)&glBindRenderbufferEXT},
		{"nglDeleteRenderbuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteRenderbuffersEXT, "glDeleteRenderbuffersEXT", (void*)&glDeleteRenderbuffersEXT},
		{"nglGenRenderbuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenRenderbuffersEXT, "glGenRenderbuffersEXT", (void*)&glGenRenderbuffersEXT},
		{"glRenderbufferStorageEXT", "(IIII)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glRenderbufferStorageEXT, "glRenderbufferStorageEXT", (void*)&glRenderbufferStorageEXT},
		{"nglGetRenderbufferParameterivEXT", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetRenderbufferParameterivEXT, "glGetRenderbufferParameterivEXT", (void*)&glGetRenderbufferParameterivEXT},
		{"glIsFramebufferEXT", "(I)Z", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glIsFramebufferEXT, "glIsFramebufferEXT", (void*)&glIsFramebufferEXT},
		{"glBindFramebufferEXT", "(II)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glBindFramebufferEXT, "glBindFramebufferEXT", (void*)&glBindFramebufferEXT},
		{"nglDeleteFramebuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglDeleteFramebuffersEXT, "glDeleteFramebuffersEXT", (void*)&glDeleteFramebuffersEXT},
		{"nglGenFramebuffersEXT", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGenFramebuffersEXT, "glGenFramebuffersEXT", (void*)&glGenFramebuffersEXT},
		{"glCheckFramebufferStatusEXT", "(I)I", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glCheckFramebufferStatusEXT, "glCheckFramebufferStatusEXT", (void*)&glCheckFramebufferStatusEXT},
		{"glFramebufferTexture1DEXT", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture1DEXT, "glFramebufferTexture1DEXT", (void*)&glFramebufferTexture1DEXT},
		{"glFramebufferTexture2DEXT", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture2DEXT, "glFramebufferTexture2DEXT", (void*)&glFramebufferTexture2DEXT},
		{"glFramebufferTexture3DEXT", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferTexture3DEXT, "glFramebufferTexture3DEXT", (void*)&glFramebufferTexture3DEXT},
		{"glFramebufferRenderbufferEXT", "(IIII)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glFramebufferRenderbufferEXT, "glFramebufferRenderbufferEXT", (void*)&glFramebufferRenderbufferEXT},
		{"nglGetFramebufferAttachmentParameterivEXT", "(IIILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_nglGetFramebufferAttachmentParameterivEXT, "glGetFramebufferAttachmentParameterivEXT", (void*)&glGetFramebufferAttachmentParameterivEXT},
		{"glGenerateMipmapEXT", "(I)V", (void*)&Java_org_lwjgl_opengl_EXTFramebufferObject_glGenerateMipmapEXT, "glGenerateMipmapEXT", (void*)&glGenerateMipmapEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif