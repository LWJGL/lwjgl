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

#include "org_lwjgl_opengl_GL12.h"
#include "checkGLerror.h"
#include "extgl.h"

typedef void (APIENTRY * glBlendColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha );
typedef void (APIENTRY * glBlendEquationPROC) (GLenum mode );
typedef void (APIENTRY * glColorTablePROC) (GLenum target, GLenum internalformat, GLsizei width, GLenum format, GLenum type, const GLvoid *table );
typedef void (APIENTRY * glColorTableParameterfvPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glColorTableParameterivPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glCopyColorTablePROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width );
typedef void (APIENTRY * glGetColorTablePROC) (GLenum target, GLenum format, GLenum type, GLvoid *table );
typedef void (APIENTRY * glGetColorTableParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetColorTableParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glColorSubTablePROC) (GLenum target, GLsizei start, GLsizei count, GLenum format, GLenum type, const GLvoid *data );
typedef void (APIENTRY * glCopyColorSubTablePROC) (GLenum target, GLsizei start, GLint x, GLint y, GLsizei width );
typedef void (APIENTRY * glConvolutionFilter1DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLenum format, GLenum type, const GLvoid *image );
typedef void (APIENTRY * glConvolutionFilter2DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *image );
typedef void (APIENTRY * glConvolutionParameterfPROC) (GLenum target, GLenum pname, GLfloat params );
typedef void (APIENTRY * glConvolutionParameterfvPROC) (GLenum target, GLenum pname, const GLfloat *params );
typedef void (APIENTRY * glConvolutionParameteriPROC) (GLenum target, GLenum pname, GLint params );
typedef void (APIENTRY * glConvolutionParameterivPROC) (GLenum target, GLenum pname, const GLint *params );
typedef void (APIENTRY * glCopyConvolutionFilter1DPROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width );
typedef void (APIENTRY * glCopyConvolutionFilter2DPROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY * glGetConvolutionFilterPROC) (GLenum target, GLenum format, GLenum type, GLvoid *image );
typedef void (APIENTRY * glGetConvolutionParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetConvolutionParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glSeparableFilter2DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *row, const GLvoid *column );
typedef void (APIENTRY * glGetHistogramPROC) (GLenum target, GLboolean reset, GLenum format, GLenum type, GLvoid *values );
typedef void (APIENTRY * glGetHistogramParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetHistogramParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glGetMinmaxPROC) (GLenum target, GLboolean reset, GLenum format, GLenum types, GLvoid *values );
typedef void (APIENTRY * glGetMinmaxParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetMinmaxParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glHistogramPROC) (GLenum target, GLsizei width, GLenum internalformat, GLboolean sink );
typedef void (APIENTRY * glResetHistogramPROC) (GLenum target );
typedef void (APIENTRY * glMinmaxPROC) (GLenum target, GLenum internalformat, GLboolean sink );
typedef void (APIENTRY * glResetMinmaxPROC) (GLenum target );
typedef void (APIENTRY * glGetSeparableFilterPROC) (GLenum target, GLenum format, GLenum type, GLvoid *row, GLvoid *column, GLvoid *span );
typedef void (APIENTRY * glDrawRangeElementsPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid *indices );
typedef void (APIENTRY * glTexImage3DPROC) (GLenum target, GLint level, GLint internalFormat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLenum format, GLenum type, const GLvoid *pixels );
typedef void (APIENTRY * glTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glCopyTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLint x, GLint y, GLsizei width, GLsizei height );

static glDrawRangeElementsPROC glDrawRangeElements;
static glTexImage3DPROC glTexImage3D;
static glTexSubImage3DPROC glTexSubImage3D;
static glCopyTexSubImage3DPROC glCopyTexSubImage3D;

static glBlendColorPROC glBlendColor;
static glBlendEquationPROC glBlendEquation;
static glColorTablePROC glColorTable;
static glColorTableParameterfvPROC glColorTableParameterfv;
static glColorTableParameterivPROC glColorTableParameteriv;
static glCopyColorTablePROC glCopyColorTable;
static glGetColorTablePROC glGetColorTable;
static glGetColorTableParameterfvPROC glGetColorTableParameterfv;
static glGetColorTableParameterivPROC glGetColorTableParameteriv;
static glColorSubTablePROC glColorSubTable;
static glCopyColorSubTablePROC glCopyColorSubTable;
static glConvolutionFilter1DPROC glConvolutionFilter1D;
static glConvolutionFilter2DPROC glConvolutionFilter2D;
static glConvolutionParameterfPROC glConvolutionParameterf;
static glConvolutionParameterfvPROC glConvolutionParameterfv;
static glConvolutionParameteriPROC glConvolutionParameteri;
static glConvolutionParameterivPROC glConvolutionParameteriv;
static glCopyConvolutionFilter1DPROC glCopyConvolutionFilter1D;
static glCopyConvolutionFilter2DPROC glCopyConvolutionFilter2D;
static glGetConvolutionFilterPROC glGetConvolutionFilter;
static glGetConvolutionParameterfvPROC glGetConvolutionParameterfv;
static glGetConvolutionParameterivPROC glGetConvolutionParameteriv;
static glGetSeparableFilterPROC glGetSeparableFilter;
static glSeparableFilter2DPROC glSeparableFilter2D;
static glGetHistogramPROC glGetHistogram;
static glGetHistogramParameterfvPROC glGetHistogramParameterfv;
static glGetHistogramParameterivPROC glGetHistogramParameteriv;
static glGetMinmaxPROC glGetMinmax;
static glGetMinmaxParameterfvPROC glGetMinmaxParameterfv;
static glGetMinmaxParameterivPROC glGetMinmaxParameteriv;
static glHistogramPROC glHistogram;
static glMinmaxPROC glMinmax;
static glResetHistogramPROC glResetHistogram;
static glResetMinmaxPROC glResetMinmax;

void extgl_InitOpenGL1_2(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.OpenGL12)
		return;
	glTexImage3D = (glTexImage3DPROC) extgl_GetProcAddress("glTexImage3D");
	glTexSubImage3D = (glTexSubImage3DPROC) extgl_GetProcAddress("glTexSubImage3D");
	glCopyTexSubImage3D = (glCopyTexSubImage3DPROC) extgl_GetProcAddress("glCopyTexSubImage3D");
	glDrawRangeElements = (glDrawRangeElementsPROC) extgl_GetProcAddress("glDrawRangeElements");
	EXTGL_SANITY_CHECK(env, ext_set, OpenGL12)
}

void extgl_InitARBImaging(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_imaging)
		return;
	glBlendColor = (glBlendColorPROC) extgl_GetProcAddress("glBlendColor");
	glBlendEquation = (glBlendEquationPROC) extgl_GetProcAddress("glBlendEquation");
	glColorTable = (glColorTablePROC) extgl_GetProcAddress("glColorTable");
	glColorTableParameterfv = (glColorTableParameterfvPROC) extgl_GetProcAddress("glColorTableParameterfv");
	glColorTableParameteriv = (glColorTableParameterivPROC) extgl_GetProcAddress("glColorTableParameteriv");
	glCopyColorTable = (glCopyColorTablePROC) extgl_GetProcAddress("glCopyColorTable");
	glGetColorTable = (glGetColorTablePROC) extgl_GetProcAddress("glGetColorTable");
	glGetColorTableParameterfv = (glGetColorTableParameterfvPROC) extgl_GetProcAddress("glGetColorTableParameterfv");
	glGetColorTableParameteriv = (glGetColorTableParameterivPROC) extgl_GetProcAddress("glGetColorTableParameteriv");
	glColorSubTable = (glColorSubTablePROC) extgl_GetProcAddress("glColorSubTable");
	glCopyColorSubTable = (glCopyColorSubTablePROC) extgl_GetProcAddress("glCopyColorSubTable");
	glConvolutionFilter1D = (glConvolutionFilter1DPROC) extgl_GetProcAddress("glConvolutionFilter1D");
	glConvolutionFilter2D = (glConvolutionFilter2DPROC) extgl_GetProcAddress("glConvolutionFilter2D");
	glConvolutionParameterf = (glConvolutionParameterfPROC) extgl_GetProcAddress("glConvolutionParameterf");
	glConvolutionParameterfv = (glConvolutionParameterfvPROC) extgl_GetProcAddress("glConvolutionParameterfv");
	glConvolutionParameteri = (glConvolutionParameteriPROC) extgl_GetProcAddress("glConvolutionParameteri");
	glConvolutionParameteriv = (glConvolutionParameterivPROC) extgl_GetProcAddress("glConvolutionParameteriv");
	glCopyConvolutionFilter1D = (glCopyConvolutionFilter1DPROC) extgl_GetProcAddress("glCopyConvolutionFilter1D");
	glCopyConvolutionFilter2D = (glCopyConvolutionFilter2DPROC) extgl_GetProcAddress("glCopyConvolutionFilter2D");
	glGetConvolutionFilter = (glGetConvolutionFilterPROC) extgl_GetProcAddress("glGetConvolutionFilter");
	glGetConvolutionParameterfv = (glGetConvolutionParameterfvPROC) extgl_GetProcAddress("glGetConvolutionParameterfv");
	glGetConvolutionParameteriv = (glGetConvolutionParameterivPROC) extgl_GetProcAddress("glGetConvolutionParameteriv");
	glGetSeparableFilter = (glGetSeparableFilterPROC) extgl_GetProcAddress("glGetSeparableFilter");
	glSeparableFilter2D = (glSeparableFilter2DPROC) extgl_GetProcAddress("glSeparableFilter2D");
	glGetHistogram = (glGetHistogramPROC) extgl_GetProcAddress("glGetHistogram");
	glGetHistogramParameterfv = (glGetHistogramParameterfvPROC) extgl_GetProcAddress("glGetHistogramParameterfv");
	glGetHistogramParameteriv = (glGetHistogramParameterivPROC) extgl_GetProcAddress("glGetHistogramParameteriv");
	glGetMinmax = (glGetMinmaxPROC) extgl_GetProcAddress("glGetMinmax");
	glGetMinmaxParameterfv = (glGetMinmaxParameterfvPROC) extgl_GetProcAddress("glGetMinmaxParameterfv");
	glGetMinmaxParameteriv = (glGetMinmaxParameterivPROC) extgl_GetProcAddress("glGetMinmaxParameteriv");
	glHistogram = (glHistogramPROC) extgl_GetProcAddress("glHistogram");
	glMinmax = (glMinmaxPROC) extgl_GetProcAddress("glMinmax");
	glResetHistogram = (glResetHistogramPROC) extgl_GetProcAddress("glResetHistogram");
	glResetMinmax = (glResetMinmaxPROC) extgl_GetProcAddress("glResetMinmax");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_imaging)
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glColorTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglColorTable
  (JNIEnv * env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorTable)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glColorTable(target, internalFormat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glColorSubTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglColorSubTable
  (JNIEnv * env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorSubTable)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glColorSubTable(target, start, count, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetColorTable
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetColorTable
  (JNIEnv * env, jclass clazz, jint target, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetColorTable)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetColorTable(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetColorTableParameteriv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetColorTableParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetColorTableParameterfv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetColorTableParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglColorTableParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorTableParameteriv)
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglColorTableParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glColorTableParameterfv)
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glColorTableParameterfv(target, pname, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glCopyColorSubTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glCopyColorSubTable
  (JNIEnv *env, jclass clazz, jint target, jint start, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyColorSubTable)
	glCopyColorSubTable(target, start, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glCopyColorTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glCopyColorTable
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyColorTable)
	glCopyColorTable(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glBlendEquation
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glBlendEquation
  (JNIEnv *env, jclass clazz, jint mode)
{
	CHECK_EXISTS(glBlendEquation)
	glBlendEquation(mode);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glBlendColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glBlendColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	CHECK_EXISTS(glBlendColor)
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glHistogram
 * Signature: (IIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glHistogram
  (JNIEnv *env, jclass clazz, jint target, jint width, jint internalformat, jboolean sink)
{
	CHECK_EXISTS(glHistogram)
	glHistogram(target, width, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glResetHistogram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glResetHistogram
  (JNIEnv *env, jclass clazz, jint target)
{
	CHECK_EXISTS(glResetHistogram)
	glResetHistogram(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetHistogram
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetHistogram
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetHistogram)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetHistogram(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetHistogramParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetHistogramParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetHistogramParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetHistogramParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetHistogramParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetHistogramParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glMinmax
 * Signature: (IIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glMinmax
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jboolean sink)
{
	CHECK_EXISTS(glMinmax)
	glMinmax(target, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glResetMinmax
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glResetMinmax
  (JNIEnv *env, jclass clazz, jint target)
{
	CHECK_EXISTS(glResetMinmax)
	glResetMinmax(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetMinmax
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetMinmax
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetMinmax)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetMinmax(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetMinmaxParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetMinmaxParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetMinmaxParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetMinmaxParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetMinmaxParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetMinmaxParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glConvolutionFilter1D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionFilter1D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glConvolutionFilter1D(target, internalformat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glConvolutionFilter2D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionFilter2D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glConvolutionFilter2D(target, internalformat, width, height, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glConvolutionParameterf
 * Signature: (IIF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glConvolutionParameterf
  (JNIEnv *env, jclass clazz, jint target, jint pname, jfloat params)
{
	CHECK_EXISTS(glConvolutionParameterf)
	glConvolutionParameterf(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionParameterfv)
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glConvolutionParameteri
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glConvolutionParameteri
  (JNIEnv *env, jclass clazz, jint target, jint pname, jint params)
{
	CHECK_EXISTS(glConvolutionParameteri)
	glConvolutionParameteri(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glConvolutionParameteriv)
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glCopyConvolutionFilter1D
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glCopyConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyConvolutionFilter1D)
	glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glCopyConvolutionFilter2D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glCopyConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width, jint height)
{
	CHECK_EXISTS(glCopyConvolutionFilter2D)
	glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetConvolutionFilter
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetConvolutionFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetConvolutionFilter)
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetConvolutionFilter(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetConvolutionParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	CHECK_EXISTS(glGetConvolutionParameteriv)
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glSeparableFilter2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglSeparableFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject row, jint row_offset, jobject column, jint column_offset)
{
	CHECK_EXISTS(glSeparableFilter2D)
	const void *address = (const void *)(row_offset + (const GLbyte *)env->GetDirectBufferAddress(row));
	const void *address2 = (const void *)(column_offset + (const GLbyte *)env->GetDirectBufferAddress(column));
	glSeparableFilter2D(target, internalformat, width, height, format, type, address, address2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glGetSeparableFilter
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglGetSeparableFilter
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
 * Class:     org_lwjgl_opengl_GL12
 * Method:    nglDrawRangeElements
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglDrawRangeElements
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glDrawRangeElements)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glDrawRangeElements(mode, start, end, count, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    nglDrawRangeElementsVBO
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglDrawRangeElementsVBO
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jint buffer_offset)
{
	CHECK_EXISTS(glDrawRangeElements)
	glDrawRangeElements(mode, start, end, count, type, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glTexImage3D
 * Signature: (IIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glTexImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_nglTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jobject buffer, jint offset)
{
	CHECK_EXISTS(glTexSubImage3D)
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL12
 * Method:    glCopyTexSubImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL12_glCopyTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint x, jint y, jint width, jint height)
{
	CHECK_EXISTS(glCopyTexSubImage3D)
	glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
	CHECK_GL_ERROR
}

