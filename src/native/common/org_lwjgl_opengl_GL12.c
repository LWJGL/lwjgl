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

/**
 * $Id$
 *
 * Core OpenGL functions.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include "extgl.h"
#include "common_tools.h"

typedef void (APIENTRY * glDrawRangeElementsPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid *indices );
typedef void (APIENTRY * glTexImage3DPROC) (GLenum target, GLint level, GLint internalFormat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLenum format, GLenum type, const GLvoid *pixels );
typedef void (APIENTRY * glTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glCopyTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLint x, GLint y, GLsizei width, GLsizei height );

static glDrawRangeElementsPROC glDrawRangeElements;
static glTexImage3DPROC glTexImage3D;
static glTexSubImage3DPROC glTexSubImage3D;
static glCopyTexSubImage3DPROC glCopyTexSubImage3D;

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    nglDrawRangeElements
 * Signature: (IIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL12_nglDrawRangeElements
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glDrawRangeElements(mode, start, end, count, type, address);

}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    nglDrawRangeElementsVBO
 * Signature: (IIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL12_nglDrawRangeElementsVBO
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jint buffer_offset)
{
	glDrawRangeElements(mode, start, end, count, type, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glTexImage3D
 * Signature: (IIIIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL12_nglTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint format, jint type, jobject buffer, jint offset)
{
	GLvoid *buffer_ptr = (GLvoid *)safeGetBufferAddress(env, buffer, offset);
	glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, buffer_ptr);

}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL12_nglTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, address);

}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glCopyTexSubImage3D
 * Signature: (IIIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL12_glCopyTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint x, jint y, jint width, jint height)
{
	glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);

}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglDrawRangeElements", "(IIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL12_nglDrawRangeElements, "glDrawRangeElements", (void*)&glDrawRangeElements},
		{"nglDrawRangeElementsVBO", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_GL12_nglDrawRangeElementsVBO, NULL, NULL},
		{"nglTexImage3D", "(IIIIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL12_nglTexImage3D, "glTexImage3D", (void*)&glTexImage3D},
		{"nglTexSubImage3D", "(IIIIIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL12_nglTexSubImage3D, "glTexSubImage3D", (void*)&glTexSubImage3D},
		{"glCopyTexSubImage3D", "(IIIIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL12_glCopyTexSubImage3D, "glCopyTexSubImage3D", (void*)&glCopyTexSubImage3D}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif
