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

typedef void (APIENTRY * glActiveTexturePROC) (GLenum texture );
typedef void (APIENTRY * glClientActiveTexturePROC) (GLenum texture );
typedef void (APIENTRY * glCompressedTexImage1DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexImage2DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexImage3DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glGetCompressedTexImagePROC) (GLenum target, GLint lod, GLvoid *img );
typedef void (APIENTRY * glMultiTexCoord1fPROC) (GLenum target, GLfloat s );
typedef void (APIENTRY * glMultiTexCoord2fPROC) (GLenum target, GLfloat s, GLfloat t );
typedef void (APIENTRY * glMultiTexCoord3fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r );
typedef void (APIENTRY * glMultiTexCoord4fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r, GLfloat q );
typedef void (APIENTRY * glLoadTransposeMatrixfPROC) (const GLfloat m[16] );
typedef void (APIENTRY * glMultTransposeMatrixfPROC) (const GLfloat m[16] );
typedef void (APIENTRY * glSampleCoveragePROC) (GLclampf value, GLboolean invert );

static glActiveTexturePROC glActiveTexture;
static glClientActiveTexturePROC glClientActiveTexture;
static glMultiTexCoord1fPROC glMultiTexCoord1f;
static glMultiTexCoord2fPROC glMultiTexCoord2f;
static glMultiTexCoord3fPROC glMultiTexCoord3f;
static glMultiTexCoord4fPROC glMultiTexCoord4f;
static glLoadTransposeMatrixfPROC glLoadTransposeMatrixf;
static glMultTransposeMatrixfPROC glMultTransposeMatrixf;
static glCompressedTexImage3DPROC glCompressedTexImage3D;
static glCompressedTexImage2DPROC glCompressedTexImage2D;
static glCompressedTexImage1DPROC glCompressedTexImage1D;
static glCompressedTexSubImage3DPROC glCompressedTexSubImage3D;
static glCompressedTexSubImage2DPROC glCompressedTexSubImage2D;
static glCompressedTexSubImage1DPROC glCompressedTexSubImage1D;
static glGetCompressedTexImagePROC glGetCompressedTexImage;
static glSampleCoveragePROC glSampleCoverage;

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glActiveTexture
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_glActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	glActiveTexture(texture);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glClientActiveTexture
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_glClientActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	glClientActiveTexture(texture);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexImage1D
 * Signature: (IIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imagesize, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glCompressedTexImage1D(target, level, internalformat, width, border, imagesize, address);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexImage2D
 * Signature: (IIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imagesize, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imagesize, address);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexImage3D
 * Signature: (IIIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imagesize, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imagesize, address);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexSubImage1D
 * Signature: (IIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imagesize, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imagesize, address);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexSubImage2D
 * Signature: (IIIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imagesize, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imagesize, address);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imagesize, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imagesize, address);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glGetCompressedTexImage
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglGetCompressedTexImage
  (JNIEnv *env, jclass clazz, jint target, jint lod, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glGetCompressedTexImage(target, lod, address);
	
}



/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord1f
 * Signature: (IF)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord1f
  (JNIEnv *env, jclass clazz, jint target, jfloat s)
{
	glMultiTexCoord1f(target, s);
	
}


/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord2f
 * Signature: (IFF)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord2f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t)
{
	glMultiTexCoord2f(target, s, t);
	
}



/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord3f
 * Signature: (IFFF)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord3f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r)
{
	glMultiTexCoord3f(target, s, t, r);
	
}


/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord4f
 * Signature: (IFFFF)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord4f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q)
{
	glMultiTexCoord4f(target, s, t, r, q);
	
}


/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glLoadTransposeMatrixf
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglLoadTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glLoadTransposeMatrixf(address);
	
}



/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultTransposeMatrixf
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_nglMultTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glMultTransposeMatrixf(address);
	
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glSampleCoverage
 * Signature: (FZ)V
 */
static void JNICALL Java_org_lwjgl_opengl_GL13_glSampleCoverage
  (JNIEnv *env, jclass clazz, jfloat value, jboolean invert)
{
	glSampleCoverage(value, invert);
	
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glActiveTexture", "(I)V", (void*)&Java_org_lwjgl_opengl_GL13_glActiveTexture, "glActiveTexture", (void*)&glActiveTexture},
		{"glClientActiveTexture", "(I)V", (void*)&Java_org_lwjgl_opengl_GL13_glClientActiveTexture, "glClientActiveTexture", (void*)&glClientActiveTexture},
		{"nglCompressedTexImage1D", "(IIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage1D, "glCompressedTexImage1D", (void*)&glCompressedTexImage1D},
		{"nglCompressedTexImage2D", "(IIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage2D, "glCompressedTexImage2D", (void*)&glCompressedTexImage2D},
		{"nglCompressedTexImage3D", "(IIIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglCompressedTexImage3D, "glCompressedTexImage3D", (void*)&glCompressedTexImage3D},
		{"nglCompressedTexSubImage1D", "(IIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage1D, "glCompressedTexSubImage1D", (void*)&glCompressedTexSubImage1D},
		{"nglCompressedTexSubImage2D", "(IIIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage2D, "glCompressedTexSubImage2D", (void*)&glCompressedTexSubImage2D},
		{"nglCompressedTexSubImage3D", "(IIIIIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage3D, "glCompressedTexSubImage3D", (void*)&glCompressedTexSubImage3D},
		{"nglGetCompressedTexImage", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglGetCompressedTexImage, "glGetCompressedTexImage", (void*)&glGetCompressedTexImage},
		{"glMultiTexCoord1f", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord1f, "glMultiTexCoord1f", (void*)&glMultiTexCoord1f},
		{"glMultiTexCoord2f", "(IFF)V", (void*)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord2f, "glMultiTexCoord2f", (void*)&glMultiTexCoord2f},
		{"glMultiTexCoord3f", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord3f, "glMultiTexCoord3f", (void*)&glMultiTexCoord3f},
		{"glMultiTexCoord4f", "(IFFFF)V", (void*)&Java_org_lwjgl_opengl_GL13_glMultiTexCoord4f, "glMultiTexCoord4f", (void*)&glMultiTexCoord4f},
		{"nglLoadTransposeMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglLoadTransposeMatrixf, "glLoadTransposeMatrixf", (void*)&glLoadTransposeMatrixf},
		{"nglMultTransposeMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL13_nglMultTransposeMatrixf, "glMultTransposeMatrixf", (void*)&glMultTransposeMatrixf},
		{"glSampleCoverage", "(FZ)V", (void*)&Java_org_lwjgl_opengl_GL13_glSampleCoverage, "glSampleCoverage", (void*)&glSampleCoverage}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif

