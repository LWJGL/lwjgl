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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglCompressedTexImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexImage1D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexImage1D(target, level, internalformat, width, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexImage2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglCompressedTexImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexImage2D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglCompressedTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexSubImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglCompressedTexSubImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexSubImage1D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexSubImage2D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglCompressedTexSubImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexSubImage2D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glCompressedTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglCompressedTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imagesize, jobject buffer, jint offset)
{
	CHECK_EXISTS(glCompressedTexSubImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glGetCompressedTexImage
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglGetCompressedTexImage
  (JNIEnv *env, jclass clazz, jint target, jint lod, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetCompressedTexImage)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetCompressedTexImage(target, lod, address);
	CHECK_GL_ERROR
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
 * Method:    glLoadTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglLoadTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer, jint offset)
{
	CHECK_EXISTS(glLoadTransposeMatrixf)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadTransposeMatrixf(address);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_CoreGL13
 * Method:    glMultTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL13_nglMultTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer, jint offset)
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
