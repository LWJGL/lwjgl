/*
* Copyright (c) 2002 Lightweight Java Game Library Project
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
* * Neither the name of 'Light Weight Java Game Library' nor the names of
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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.arb.ARBTextureCompression
// ----------------------------------

#include "org_lwjgl_opengl_arb_ARBTextureCompression.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glCompressedTexImage1DARBPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexImage2DARBPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexImage3DARBPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage1DARBPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage2DARBPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage3DARBPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glGetCompressedTexImageARBPROC) (GLenum target, GLint lod, GLvoid *img );

static glCompressedTexImage3DARBPROC glCompressedTexImage3DARB;
static glCompressedTexImage2DARBPROC glCompressedTexImage2DARB;
static glCompressedTexImage1DARBPROC glCompressedTexImage1DARB; 
static glCompressedTexSubImage3DARBPROC glCompressedTexSubImage3DARB;
static glCompressedTexSubImage2DARBPROC glCompressedTexSubImage2DARB;
static glCompressedTexSubImage1DARBPROC glCompressedTexSubImage1DARB;
static glGetCompressedTexImageARBPROC glGetCompressedTexImageARB;

void extgl_InitARBTextureCompression(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_texture_compression)
		return;
	glCompressedTexImage3DARB = (glCompressedTexImage3DARBPROC) extgl_GetProcAddress("glCompressedTexImage3DARB");
	glCompressedTexImage2DARB = (glCompressedTexImage2DARBPROC) extgl_GetProcAddress("glCompressedTexImage2DARB");
	glCompressedTexImage1DARB = (glCompressedTexImage1DARBPROC) extgl_GetProcAddress("glCompressedTexImage1DARB");
	glCompressedTexSubImage3DARB = (glCompressedTexSubImage3DARBPROC) extgl_GetProcAddress("glCompressedTexSubImage3DARB");
	glCompressedTexSubImage2DARB = (glCompressedTexSubImage2DARBPROC) extgl_GetProcAddress("glCompressedTexSubImage2DARB");
	glCompressedTexSubImage1DARB = (glCompressedTexSubImage1DARBPROC) extgl_GetProcAddress("glCompressedTexSubImage1DARB");
	glGetCompressedTexImageARB = (glGetCompressedTexImageARBPROC) extgl_GetProcAddress("glGetCompressedTexImageARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_texture_compression)
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBTextureCompression
 * Method:	nglCompressedTexImage1DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBTextureCompression_nglCompressedTexImage1DARB
	(JNIEnv * env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imageSize, jobject pData, jint pData_offset)
{
	CHECK_EXISTS(glCompressedTexImage1DARB)
	GLvoid *pData_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pData) + pData_offset);
	glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBTextureCompression
 * Method:	nglCompressedTexImage2DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBTextureCompression_nglCompressedTexImage2DARB
	(JNIEnv * env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imageSize, jobject pData, jint pData_offset)
{
	CHECK_EXISTS(glCompressedTexImage2DARB)
	GLvoid *pData_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pData) + pData_offset);
	glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBTextureCompression
 * Method:	nglCompressedTexImage3DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBTextureCompression_nglCompressedTexImage3DARB
	(JNIEnv * env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imageSize, jobject pData, jint pData_offset)
{
	CHECK_EXISTS(glCompressedTexImage3DARB)
	GLvoid *pData_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pData) + pData_offset);
	glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBTextureCompression
 * Method:	nglCompressedTexSubImage1DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBTextureCompression_nglCompressedTexSubImage1DARB
	(JNIEnv * env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint border, jint imageSize, jobject pData, jint pData_offset)
{
	CHECK_EXISTS(glCompressedTexSubImage1DARB)
	GLvoid *pData_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pData) + pData_offset);
	glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBTextureCompression
 * Method:	nglCompressedTexSubImage2DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBTextureCompression_nglCompressedTexSubImage2DARB
	(JNIEnv * env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint border, jint imageSize, jobject pData, jint pData_offset)
{
	CHECK_EXISTS(glCompressedTexSubImage2DARB)
	GLvoid *pData_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pData) + pData_offset);
	glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBTextureCompression
 * Method:	nglCompressedTexSubImage3DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBTextureCompression_nglCompressedTexSubImage3DARB
	(JNIEnv * env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint border, jint imageSize, jobject pData, jint pData_offset)
{
	CHECK_EXISTS(glCompressedTexSubImage3DARB)
	GLvoid *pData_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pData) + pData_offset);
	glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBTextureCompression
 * Method:	nglGetCompressedTexImageARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBTextureCompression_nglGetCompressedTexImageARB
	(JNIEnv * env, jclass clazz, jint target, jint lod, jobject pImg, jint pImg_offset)
{
	CHECK_EXISTS(glGetCompressedTexImageARB)
	GLvoid *pImg_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pImg) + pImg_offset);
	glGetCompressedTexImageARB(target, lod, pImg_ptr);
	CHECK_GL_ERROR
}
