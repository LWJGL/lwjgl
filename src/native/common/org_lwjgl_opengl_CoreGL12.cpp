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

static inline const void *offsetToPointer(jint offset) {
	return (const char *)NULL + offset;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglColorTable
  (JNIEnv * env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorTable)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glColorTable(target, internalFormat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorSubTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglColorSubTable
  (JNIEnv * env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorSubTable)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glColorSubTable(target, start, count, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetColorTable
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetColorTable
  (JNIEnv * env, jclass clazz, jint target, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetColorTable)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetColorTable(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetColorTableParameteriv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetColorTableParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetColorTableParameterfv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetColorTableParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglColorTableParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorTableParameteriv)
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglColorTableParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorTableParameterfv)
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
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
	CHECK_EXISTS(glBlendColor)
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetHistogram
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetHistogram)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetHistogram(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetHistogramParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetHistogramParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetHistogramParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetHistogramParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetHistogramParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetHistogramParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetMinmax
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetMinmax)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetMinmax(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetMinmaxParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetMinmaxParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetMinmaxParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetMinmaxParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetMinmaxParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetMinmaxParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionFilter1D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionFilter1D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glConvolutionFilter1D(target, internalformat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glConvolutionFilter2D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionFilter2D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionParameterfv)
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionParameteriv)
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetConvolutionFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetConvolutionFilter)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetConvolutionFilter(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetConvolutionParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetConvolutionParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glSeparableFilter2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglSeparableFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject row, jint row_offset, jobject column, jint column_offset)
{
	CHECK_EXISTS(glSeparableFilter2D)
	const void *address = (const void *)(row_offset + (const GLbyte *)env->GetDirectBufferAddress(row));
	const void *address2 = (const void *)(column_offset + (const GLbyte *)env->GetDirectBufferAddress(column));
	glSeparableFilter2D(target, internalformat, width, height, format, type, address, address2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glGetSeparableFilter
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglGetSeparableFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject row, jint row_offset, jobject column, jint column_offset, jobject span, jint span_offset)
{
	CHECK_EXISTS(glGetSeparableFilter)
	void *address = (void *)(row_offset + (GLbyte *)env->GetDirectBufferAddress(row));
	void *address2 = (void *)(column_offset + (GLbyte *)env->GetDirectBufferAddress(column));
	void *address3 = (void *)(span_offset + (GLbyte *)env->GetDirectBufferAddress(span));
	glGetSeparableFilter(target, format, type, address, address2, address3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    nglDrawRangeElements
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglDrawRangeElements
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glDrawRangeElements)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glDrawRangeElements(mode, start, end, count, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    nglDrawRangeElementsVBO
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglDrawRangeElementsVBO
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jint buffer_offset)
{
	CHECK_EXISTS(glDrawRangeElements)
	glDrawRangeElements(mode, start, end, count, type, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glTexImage3D
 * Signature: (IIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glTexImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL12
 * Method:    glTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL12_nglTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glTexSubImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
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

