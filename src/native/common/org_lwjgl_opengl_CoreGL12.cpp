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

#include "org_lwjgl_opengl_CoreGL12.h"
#include "checkGLerror.h"
#include "extgl.h"

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glColorTable
  (JNIEnv * env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glColorTable(target, internalFormat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorSubTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glColorSubTable
  (JNIEnv * env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glColorSubTable(target, start, count, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetColorTable
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetColorTable
  (JNIEnv * env, jclass clazz, jint target, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetColorTable)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetColorTable(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetColorTableParameteriv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetColorTableParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetColorTableParameterfv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetColorTableParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glColorTableParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glColorTableParameteriv)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glColorTableParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glColorTableParameterfv)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glColorTableParameterfv(target, pname, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glCopyColorSubTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glCopyColorSubTable
  (JNIEnv *env, jclass clazz, jint target, jint start, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyColorSubTable)
	glCopyColorSubTable(target, start, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glCopyColorTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glCopyColorTable
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyColorTable)
	glCopyColorTable(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glBlendEquation
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glBlendEquation
  (JNIEnv *env, jclass clazz, jint mode)
{
	CHECK_EXISTS(glBlendEquation)
	glBlendEquation(mode);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glBlendColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glBlendColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glHistogram
 * Signature: (IIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glHistogram
  (JNIEnv *env, jclass clazz, jint target, jint width, jint internalformat, jboolean sink)
{
	CHECK_EXISTS(glHistogram)
	glHistogram(target, width, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glResetHistogram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glResetHistogram
  (JNIEnv *env, jclass clazz, jint target)
{
	CHECK_EXISTS(glResetHistogram)
	glResetHistogram(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetHistogram
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetHistogram
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetHistogram)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetHistogram(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetHistogramParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetHistogramParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetHistogramParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetHistogramParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetHistogramParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetHistogramParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glMinmax
 * Signature: (IIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glMinmax
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jboolean sink)
{
	CHECK_EXISTS(glMinmax)
	glMinmax(target, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glResetMinmax
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glResetMinmax
  (JNIEnv *env, jclass clazz, jint target)
{
	CHECK_EXISTS(glResetMinmax)
	glResetMinmax(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetMinmax
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetMinmax
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetMinmax)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetMinmax(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetMinmaxParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetMinmaxParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetMinmaxParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetMinmaxParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetMinmaxParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetMinmaxParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionFilter1D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glConvolutionFilter1D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glConvolutionFilter1D(target, internalformat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionFilter2D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glConvolutionFilter2D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glConvolutionFilter2D(target, internalformat, width, height, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionParameterf
 * Signature: (IIF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glConvolutionParameterf
  (JNIEnv *env, jclass clazz, jint target, jint pname, jfloat params)
{
	CHECK_EXISTS(glConvolutionParameterf)
	glConvolutionParameterf(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glConvolutionParameterfv)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionParameteri
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glConvolutionParameteri
  (JNIEnv *env, jclass clazz, jint target, jint pname, jint params)
{
	CHECK_EXISTS(glConvolutionParameteri)
	glConvolutionParameteri(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glConvolutionParameteriv)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glCopyConvolutionFilter1D
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glCopyConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyConvolutionFilter1D)
	glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glCopyConvolutionFilter2D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glCopyConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width, jint height)
{
	CHECK_EXISTS(glCopyConvolutionFilter2D)
	glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetConvolutionFilter
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetConvolutionFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetConvolutionFilter)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionFilter(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetConvolutionParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetConvolutionParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glSeparableFilter2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glSeparableFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject row, jobject column)
{
	CHECK_EXISTS(glSeparableFilter2D)
	const void *address = (const void *)env->GetDirectBufferAddress(row);
	const void *address2 = env->GetDirectBufferAddress(column);
	glSeparableFilter2D(target, internalformat, width, height, format, type, address, address2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetSeparableFilter
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glGetSeparableFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject row, jobject column, jobject span)
{
	CHECK_EXISTS(glGetSeparableFilter)
	void *address = (void *)env->GetDirectBufferAddress(row);
	void *address2 = (void *)env->GetDirectBufferAddress(column);
	void *address3 = (void *)env->GetDirectBufferAddress(span);
	glGetSeparableFilter(target, format, type, address, address2, address3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glDrawRangeElements
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glDrawRangeElements
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject buffer)
{
	CHECK_EXISTS(glDrawRangeElements)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glDrawRangeElements(mode, start, end, count, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glTexImage3D
 * Signature: (IIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glTexImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glTexSubImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glCopyTexSubImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_glCopyTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint x, jint y, jint width, jint height)
{
	CHECK_EXISTS(glCopyTexSubImage3D)
	glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
	CHECK_GL_ERROR
}

