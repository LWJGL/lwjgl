/*
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 
/**
 * $Id$
 *
 * Core OpenGL functions.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include "org_lwjgl_opengl_GL13.h"
#include "checkGLerror.h"
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
typedef void (APIENTRY * glMultiTexCoord1dPROC) (GLenum target, GLdouble s );
typedef void (APIENTRY * glMultiTexCoord1dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord1fPROC) (GLenum target, GLfloat s );
typedef void (APIENTRY * glMultiTexCoord1fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord1iPROC) (GLenum target, GLint s );
typedef void (APIENTRY * glMultiTexCoord1ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord1sPROC) (GLenum target, GLshort s );
typedef void (APIENTRY * glMultiTexCoord1svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glMultiTexCoord2dPROC) (GLenum target, GLdouble s, GLdouble t );
typedef void (APIENTRY * glMultiTexCoord2dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord2fPROC) (GLenum target, GLfloat s, GLfloat t );
typedef void (APIENTRY * glMultiTexCoord2fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord2iPROC) (GLenum target, GLint s, GLint t );
typedef void (APIENTRY * glMultiTexCoord2ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord2sPROC) (GLenum target, GLshort s, GLshort t );
typedef void (APIENTRY * glMultiTexCoord2svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glMultiTexCoord3dPROC) (GLenum target, GLdouble s, GLdouble t, GLdouble r );
typedef void (APIENTRY * glMultiTexCoord3dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord3fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r );
typedef void (APIENTRY * glMultiTexCoord3fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord3iPROC) (GLenum target, GLint s, GLint t, GLint r );
typedef void (APIENTRY * glMultiTexCoord3ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord3sPROC) (GLenum target, GLshort s, GLshort t, GLshort r );
typedef void (APIENTRY * glMultiTexCoord3svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glMultiTexCoord4dPROC) (GLenum target, GLdouble s, GLdouble t, GLdouble r, GLdouble q );
typedef void (APIENTRY * glMultiTexCoord4dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord4fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r, GLfloat q );
typedef void (APIENTRY * glMultiTexCoord4fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord4iPROC) (GLenum target, GLint s, GLint t, GLint r, GLint q );
typedef void (APIENTRY * glMultiTexCoord4ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord4sPROC) (GLenum target, GLshort s, GLshort t, GLshort r, GLshort q );
typedef void (APIENTRY * glMultiTexCoord4svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glLoadTransposeMatrixdPROC) (const GLdouble m[16] );
typedef void (APIENTRY * glLoadTransposeMatrixfPROC) (const GLfloat m[16] );
typedef void (APIENTRY * glMultTransposeMatrixdPROC) (const GLdouble m[16] );
typedef void (APIENTRY * glMultTransposeMatrixfPROC) (const GLfloat m[16] );
typedef void (APIENTRY * glSampleCoveragePROC) (GLclampf value, GLboolean invert );

static glActiveTexturePROC glActiveTexture;
static glClientActiveTexturePROC glClientActiveTexture;
static glMultiTexCoord1dPROC glMultiTexCoord1d;
static glMultiTexCoord1dvPROC glMultiTexCoord1dv;
static glMultiTexCoord1fPROC glMultiTexCoord1f;
static glMultiTexCoord1fvPROC glMultiTexCoord1fv;
static glMultiTexCoord1iPROC glMultiTexCoord1i;
static glMultiTexCoord1ivPROC glMultiTexCoord1iv;
static glMultiTexCoord1sPROC glMultiTexCoord1s;
static glMultiTexCoord1svPROC glMultiTexCoord1sv;
static glMultiTexCoord2dPROC glMultiTexCoord2d;
static glMultiTexCoord2dvPROC glMultiTexCoord2dv;
static glMultiTexCoord2fPROC glMultiTexCoord2f;
static glMultiTexCoord2fvPROC glMultiTexCoord2fv;
static glMultiTexCoord2iPROC glMultiTexCoord2i;
static glMultiTexCoord2ivPROC glMultiTexCoord2iv;
static glMultiTexCoord2sPROC glMultiTexCoord2s;
static glMultiTexCoord2svPROC glMultiTexCoord2sv;
static glMultiTexCoord3dPROC glMultiTexCoord3d;
static glMultiTexCoord3dvPROC glMultiTexCoord3dv;
static glMultiTexCoord3fPROC glMultiTexCoord3f;
static glMultiTexCoord3fvPROC glMultiTexCoord3fv;
static glMultiTexCoord3iPROC glMultiTexCoord3i;
static glMultiTexCoord3ivPROC glMultiTexCoord3iv;
static glMultiTexCoord3sPROC glMultiTexCoord3s;
static glMultiTexCoord3svPROC glMultiTexCoord3sv;
static glMultiTexCoord4dPROC glMultiTexCoord4d;
static glMultiTexCoord4dvPROC glMultiTexCoord4dv;
static glMultiTexCoord4fPROC glMultiTexCoord4f;
static glMultiTexCoord4fvPROC glMultiTexCoord4fv;
static glMultiTexCoord4iPROC glMultiTexCoord4i;
static glMultiTexCoord4ivPROC glMultiTexCoord4iv;
static glMultiTexCoord4sPROC glMultiTexCoord4s;
static glMultiTexCoord4svPROC glMultiTexCoord4sv;
static glLoadTransposeMatrixfPROC glLoadTransposeMatrixf;
static glLoadTransposeMatrixdPROC glLoadTransposeMatrixd;
static glMultTransposeMatrixfPROC glMultTransposeMatrixf;
static glMultTransposeMatrixdPROC glMultTransposeMatrixd;
static glCompressedTexImage3DPROC glCompressedTexImage3D;
static glCompressedTexImage2DPROC glCompressedTexImage2D;
static glCompressedTexImage1DPROC glCompressedTexImage1D;
static glCompressedTexSubImage3DPROC glCompressedTexSubImage3D;
static glCompressedTexSubImage2DPROC glCompressedTexSubImage2D;
static glCompressedTexSubImage1DPROC glCompressedTexSubImage1D;
static glGetCompressedTexImagePROC glGetCompressedTexImage;
static glSampleCoveragePROC glSampleCoverage;

void extgl_InitOpenGL1_3(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.OpenGL13)
		return;
	glActiveTexture = (glActiveTexturePROC) extgl_GetProcAddress("glActiveTexture");
	glClientActiveTexture = (glClientActiveTexturePROC) extgl_GetProcAddress("glClientActiveTexture");

	glMultiTexCoord1d = (glMultiTexCoord1dPROC) extgl_GetProcAddress("glMultiTexCoord1d");
	glMultiTexCoord1dv = (glMultiTexCoord1dvPROC) extgl_GetProcAddress("glMultiTexCoord1dv");
	glMultiTexCoord1f = (glMultiTexCoord1fPROC) extgl_GetProcAddress("glMultiTexCoord1f");
	glMultiTexCoord1fv = (glMultiTexCoord1fvPROC) extgl_GetProcAddress("glMultiTexCoord1fv");
	glMultiTexCoord1i = (glMultiTexCoord1iPROC) extgl_GetProcAddress("glMultiTexCoord1i");
	glMultiTexCoord1iv = (glMultiTexCoord1ivPROC) extgl_GetProcAddress("glMultiTexCoord1iv");
	glMultiTexCoord1s = (glMultiTexCoord1sPROC) extgl_GetProcAddress("glMultiTexCoord1s");
	glMultiTexCoord1sv = (glMultiTexCoord1svPROC) extgl_GetProcAddress("glMultiTexCoord1sv");

	glMultiTexCoord2d = (glMultiTexCoord2dPROC) extgl_GetProcAddress("glMultiTexCoord2d");
	glMultiTexCoord2dv = (glMultiTexCoord2dvPROC) extgl_GetProcAddress("glMultiTexCoord2dv");
	glMultiTexCoord2f = (glMultiTexCoord2fPROC) extgl_GetProcAddress("glMultiTexCoord2f");
	glMultiTexCoord2fv = (glMultiTexCoord2fvPROC) extgl_GetProcAddress("glMultiTexCoord2fv");
	glMultiTexCoord2i = (glMultiTexCoord2iPROC) extgl_GetProcAddress("glMultiTexCoord2i");
	glMultiTexCoord2iv = (glMultiTexCoord2ivPROC) extgl_GetProcAddress("glMultiTexCoord2iv");
	glMultiTexCoord2s = (glMultiTexCoord2sPROC) extgl_GetProcAddress("glMultiTexCoord2s");
	glMultiTexCoord2sv = (glMultiTexCoord2svPROC) extgl_GetProcAddress("glMultiTexCoord2sv");

	glMultiTexCoord3d = (glMultiTexCoord3dPROC) extgl_GetProcAddress("glMultiTexCoord3d");
	glMultiTexCoord3dv = (glMultiTexCoord3dvPROC) extgl_GetProcAddress("glMultiTexCoord3dv");
	glMultiTexCoord3f = (glMultiTexCoord3fPROC) extgl_GetProcAddress("glMultiTexCoord3f");
	glMultiTexCoord3fv = (glMultiTexCoord3fvPROC) extgl_GetProcAddress("glMultiTexCoord3fv");
	glMultiTexCoord3i = (glMultiTexCoord3iPROC) extgl_GetProcAddress("glMultiTexCoord3i");
	glMultiTexCoord3iv = (glMultiTexCoord3ivPROC) extgl_GetProcAddress("glMultiTexCoord3iv");
	glMultiTexCoord3s = (glMultiTexCoord3sPROC) extgl_GetProcAddress("glMultiTexCoord3s");
	glMultiTexCoord3sv = (glMultiTexCoord3svPROC) extgl_GetProcAddress("glMultiTexCoord3sv");

	glMultiTexCoord4d = (glMultiTexCoord4dPROC) extgl_GetProcAddress("glMultiTexCoord4d");
	glMultiTexCoord4dv = (glMultiTexCoord4dvPROC) extgl_GetProcAddress("glMultiTexCoord4dv");
	glMultiTexCoord4f = (glMultiTexCoord4fPROC) extgl_GetProcAddress("glMultiTexCoord4f");
	glMultiTexCoord4fv = (glMultiTexCoord4fvPROC) extgl_GetProcAddress("glMultiTexCoord4fv");
	glMultiTexCoord4i = (glMultiTexCoord4iPROC) extgl_GetProcAddress("glMultiTexCoord4i");
	glMultiTexCoord4iv = (glMultiTexCoord4ivPROC) extgl_GetProcAddress("glMultiTexCoord4iv");
	glMultiTexCoord4s = (glMultiTexCoord4sPROC) extgl_GetProcAddress("glMultiTexCoord4s");
	glMultiTexCoord4sv = (glMultiTexCoord4svPROC) extgl_GetProcAddress("glMultiTexCoord4sv");

	glLoadTransposeMatrixf = (glLoadTransposeMatrixfPROC) extgl_GetProcAddress("glLoadTransposeMatrixf");
	glLoadTransposeMatrixd = (glLoadTransposeMatrixdPROC) extgl_GetProcAddress("glLoadTransposeMatrixd");
	glMultTransposeMatrixf = (glMultTransposeMatrixfPROC) extgl_GetProcAddress("glMultTransposeMatrixf");
	glMultTransposeMatrixd = (glMultTransposeMatrixdPROC) extgl_GetProcAddress("glMultTransposeMatrixd");
	glCompressedTexImage3D = (glCompressedTexImage3DPROC) extgl_GetProcAddress("glCompressedTexImage3D");
	glCompressedTexImage2D = (glCompressedTexImage2DPROC) extgl_GetProcAddress("glCompressedTexImage2D");
	glCompressedTexImage1D = (glCompressedTexImage1DPROC) extgl_GetProcAddress("glCompressedTexImage1D");
	glCompressedTexSubImage3D = (glCompressedTexSubImage3DPROC) extgl_GetProcAddress("glCompressedTexSubImage3D");
	glCompressedTexSubImage2D = (glCompressedTexSubImage2DPROC) extgl_GetProcAddress("glCompressedTexSubImage2D");
	glCompressedTexSubImage1D = (glCompressedTexSubImage1DPROC) extgl_GetProcAddress("glCompressedTexSubImage1D");
	glGetCompressedTexImage = (glGetCompressedTexImagePROC) extgl_GetProcAddress("glGetCompressedTexImage");

	glSampleCoverage = (glSampleCoveragePROC) extgl_GetProcAddress("glSampleCoverage");
	EXTGL_SANITY_CHECK(env, ext_set, OpenGL13)
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_glActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glActiveTexture)
	glActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glClientActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_glClientActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glClientActiveTexture)
	glClientActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexImage1D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexImage1D(target, level, internalformat, width, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexImage2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexImage2D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexSubImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexSubImage1D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexSubImage2D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexSubImage2D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glCompressedTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglCompressedTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexSubImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glGetCompressedTexImage
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglGetCompressedTexImage
  (JNIEnv *env, jclass clazz, jint target, jint lod, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetCompressedTexImage)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetCompressedTexImage(target, lod, address);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord1f
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord1f
  (JNIEnv *env, jclass clazz, jint target, jfloat s)
{
	CHECK_EXISTS(glMultiTexCoord1f)
	glMultiTexCoord1f(target, s);
	
}


/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord2f
 * Signature: (IFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord2f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t)
{
	CHECK_EXISTS(glMultiTexCoord2f)
	glMultiTexCoord2f(target, s, t);
	
}



/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord3f
 * Signature: (IFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord3f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r)
{
	CHECK_EXISTS(glMultiTexCoord3f)
	glMultiTexCoord3f(target, s, t, r);
	
}


/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultiTexCoord4f
 * Signature: (IFFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_glMultiTexCoord4f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q)
{
	CHECK_EXISTS(glMultiTexCoord4f)
	glMultiTexCoord4f(target, s, t, r, q);
	
}


/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glLoadTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglLoadTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer, jint offset)
{
	CHECK_EXISTS(glLoadTransposeMatrixf)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadTransposeMatrixf(address);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glMultTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_nglMultTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer, jint offset)
{
	CHECK_EXISTS(glMultTransposeMatrixf)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMultTransposeMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL13
 * Method:    glSampleCoverage
 * Signature: (FZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL13_glSampleCoverage
  (JNIEnv *env, jclass clazz, jfloat value, jboolean invert)
{
	CHECK_EXISTS(glSampleCoverage)
	glSampleCoverage(value, invert);
	CHECK_GL_ERROR
}
