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

#ifdef _WIN32
#include <windows.h>
#endif

#include "org_lwjgl_opengl_CoreGL13.h"
#include "checkGLerror.h"
#include "extgl.h"

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glActiveTexture)
	glActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glClientActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glClientActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glClientActiveTexture)
	glClientActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glCompressedTexImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexImage1D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage1D(target, level, internalformat, width, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexImage2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glCompressedTexImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexImage2D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glCompressedTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexSubImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glCompressedTexSubImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexSubImage1D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexSubImage2D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glCompressedTexSubImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexSubImage2D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glCompressedTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexSubImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glGetCompressedTexImage
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glGetCompressedTexImage
  (JNIEnv *env, jclass clazz, jint target, jint lod, jobject buffer)
{
	CHECK_EXISTS(glGetCompressedTexImage)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetCompressedTexImage(target, lod, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord1d
 * Signature: (ID)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord1d
  (JNIEnv *env, jclass clazz, jint target, jdouble s)
{
	CHECK_EXISTS(glMultiTexCoord1d)
	glMultiTexCoord1d(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord1f
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord1f
  (JNIEnv *env, jclass clazz, jint target, jfloat s)
{
	CHECK_EXISTS(glMultiTexCoord1f)
	glMultiTexCoord1f(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord1i
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord1i
  (JNIEnv *env, jclass clazz, jint target, jint s)
{
	CHECK_EXISTS(glMultiTexCoord1i)
	glMultiTexCoord1i(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord1s
 * Signature: (IS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord1s
  (JNIEnv *env, jclass clazz, jint target, jshort s)
{
	CHECK_EXISTS(glMultiTexCoord1s)
	glMultiTexCoord1s(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord2d
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord2d
  (JNIEnv *env, jclass clazz, jint target, jdouble s, jdouble t)
{
	CHECK_EXISTS(glMultiTexCoord2d)
	glMultiTexCoord2d(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord2f
 * Signature: (IFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord2f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t)
{
	CHECK_EXISTS(glMultiTexCoord2f)
	glMultiTexCoord2f(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord2i
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord2i
  (JNIEnv *env, jclass clazz, jint target, jint s, jint t)
{
	CHECK_EXISTS(glMultiTexCoord2i)
	glMultiTexCoord2i(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord2s
 * Signature: (ISS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord2s
  (JNIEnv *env, jclass clazz, jint target, jshort s, jshort t)
{
	CHECK_EXISTS(glMultiTexCoord2s)
	glMultiTexCoord2s(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord3d
 * Signature: (IDDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord3d
  (JNIEnv *env, jclass clazz, jint target, jdouble s, jdouble t, jdouble r)
{
	CHECK_EXISTS(glMultiTexCoord3d)
	glMultiTexCoord3d(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord3f
 * Signature: (IFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord3f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r)
{
	CHECK_EXISTS(glMultiTexCoord3f)
	glMultiTexCoord3f(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord3i
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord3i
  (JNIEnv *env, jclass clazz, jint target, jint s, jint t, jint r)
{
	CHECK_EXISTS(glMultiTexCoord3i)
	glMultiTexCoord3i(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord3s
 * Signature: (ISSS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord3s
  (JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r)
{
	CHECK_EXISTS(glMultiTexCoord3s)
	glMultiTexCoord3s(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord4d
 * Signature: (IDDDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord4d
  (JNIEnv *env, jclass clazz, jint target, jdouble s, jdouble t, jdouble r, jdouble q)
{
	CHECK_EXISTS(glMultiTexCoord4d)
	glMultiTexCoord4d(target, s, t, r, q);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord4f
 * Signature: (IFFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord4f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q)
{
	CHECK_EXISTS(glMultiTexCoord4f)
	glMultiTexCoord4f(target, s, t, r, q);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord4i
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord4i
  (JNIEnv *env, jclass clazz, jint target, jint s, jint t, jint r, jint q)
{
	CHECK_EXISTS(glMultiTexCoord4i)
	glMultiTexCoord4i(target, s, t, r, q);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultiTexCoord4s
 * Signature: (ISSSS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultiTexCoord4s
  (JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q)
{
	CHECK_EXISTS(glMultiTexCoord4s)
	glMultiTexCoord4s(target, s, t, r, q);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glLoadTransposeMatrixd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glLoadTransposeMatrixd
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glLoadTransposeMatrixd)
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glLoadTransposeMatrixd(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glLoadTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glLoadTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glLoadTransposeMatrixf)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadTransposeMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultTransposeMatrixd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultTransposeMatrixd
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glMultTransposeMatrixd)
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glMultTransposeMatrixd(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glMultTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glMultTransposeMatrixf)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMultTransposeMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glSampleCoverage
 * Signature: (FZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_glSampleCoverage
  (JNIEnv *env, jclass clazz, jfloat value, jboolean invert)
{
	CHECK_EXISTS(glSampleCoverage)
	glSampleCoverage(value, invert);
	CHECK_GL_ERROR
}
