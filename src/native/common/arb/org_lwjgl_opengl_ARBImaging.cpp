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

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glColorTable
 * Signature: (IIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorTable
  (JNIEnv * env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glColorTable(target, internalFormat, width, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glColorSubTable
 * Signature: (IIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorSubTable
  (JNIEnv * env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glColorSubTable(target, start, count, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetColorTable
 * Signature: (IIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetColorTable
  (JNIEnv * env, jclass clazz, jint target, jint format, jint type, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetColorTable(target, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetColorTableParameteriv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameteriv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameteriv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetColorTableParameterfv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameterfv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameterfv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glColorTableParameteriv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glColorTableParameteriv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glColorTableParameterfv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glColorTableParameterfv(target, pname, address);
	
}


/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glCopyColorSubTable
 * Signature: (IIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyColorSubTable
  (JNIEnv *env, jclass clazz, jint target, jint start, jint x, jint y, jint width)
{
	glCopyColorSubTable(target, start, x, y, width);
	
}


/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glCopyColorTable
 * Signature: (IIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyColorTable
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	glCopyColorTable(target, internalformat, x, y, width);
	
}


/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glBlendEquation
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glBlendEquation
  (JNIEnv *env, jclass clazz, jint mode)
{
	glBlendEquation(mode);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glBlendColor
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glBlendColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glHistogram
 * Signature: (IIIZ)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glHistogram
  (JNIEnv *env, jclass clazz, jint target, jint width, jint internalformat, jboolean sink)
{
	glHistogram(target, width, internalformat, sink);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glResetHistogram
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glResetHistogram
  (JNIEnv *env, jclass clazz, jint target)
{
	glResetHistogram(target);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetHistogram
 * Signature: (IZIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetHistogram
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetHistogram(target, reset, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetHistogramParameterfv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameterfv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetHistogramParameteriv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameteriv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glMinmax
 * Signature: (IIZ)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glMinmax
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jboolean sink)
{
	glMinmax(target, internalformat, sink);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glResetMinmax
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glResetMinmax
  (JNIEnv *env, jclass clazz, jint target)
{
	glResetMinmax(target);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetMinmax
 * Signature: (IZIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetMinmax
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetMinmax(target, reset, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetMinmaxParameterfv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameterfv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetMinmaxParameteriv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameteriv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glConvolutionFilter1D
 * Signature: (IIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint format, jint type, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glConvolutionFilter1D(target, internalformat, width, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glConvolutionFilter2D
 * Signature: (IIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glConvolutionFilter2D(target, internalformat, width, height, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glConvolutionParameterf
 * Signature: (IIF)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameterf
  (JNIEnv *env, jclass clazz, jint target, jint pname, jfloat params)
{
	glConvolutionParameterf(target, pname, params);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glConvolutionParameterfv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameterfv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glConvolutionParameteri
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameteri
  (JNIEnv *env, jclass clazz, jint target, jint pname, jint params)
{
	glConvolutionParameteri(target, pname, params);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glConvolutionParameteriv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameteriv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glCopyConvolutionFilter1D
 * Signature: (IIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glCopyConvolutionFilter2D
 * Signature: (IIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width, jint height)
{
	glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetConvolutionFilter
 * Signature: (IIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetConvolutionFilter(target, format, type, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetConvolutionParameterfv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameterfv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetConvolutionParameteriv
 * Signature: (III)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameteriv(target, pname, address);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glSeparableFilter2D
 * Signature: (IIIIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglSeparableFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject row, jint row_offset, jobject column, jint column_offset)
{
	const void *address = (const void *)(row_offset + (const GLbyte *)env->GetDirectBufferAddress(row));
	const void *address2 = (const void *)(column_offset + (const GLbyte *)env->GetDirectBufferAddress(column));
	glSeparableFilter2D(target, internalformat, width, height, format, type, address, address2);
	
}

/*
 * Class:     org_lwjgl_opengl_ARBImaging
 * Method:    glGetSeparableFilter
 * Signature: (IIIIII)V
 */
static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetSeparableFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject row, jint row_offset, jobject column, jint column_offset, jobject span, jint span_offset)
{
	void *address = (void *)(row_offset + (GLbyte *)env->GetDirectBufferAddress(row));
	void *address2 = (void *)(column_offset + (GLbyte *)env->GetDirectBufferAddress(column));
	void *address3 = (void *)(span_offset + (GLbyte *)env->GetDirectBufferAddress(span));
	glGetSeparableFilter(target, format, type, address, address2, address3);
	
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBImaging_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglColorTable", "(IIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglColorTable, "glColorTable", (void**)&glColorTable},
		{"nglColorSubTable", "(IIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglColorSubTable, "glColorSubTable", (void**)&glColorSubTable},
		{"nglColorTableParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameteriv, "glColorTableParameteriv", (void**)&glColorTableParameteriv},
		{"nglColorTableParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameterfv, "glColorTableParameterfv", (void**)&glColorTableParameterfv},
		{"glCopyColorSubTable", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glCopyColorSubTable, "glCopyColorSubTable", (void**)&glCopyColorSubTable},
		{"glCopyColorTable", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glCopyColorTable, "glCopyColorTable", (void**)&glCopyColorTable},
		{"nglGetColorTable", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetColorTable, "glGetColorTable", (void**)&glGetColorTable},
		{"nglGetColorTableParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameteriv, "glGetColorTableParameteriv", (void**)&glGetColorTableParameteriv},
		{"nglGetColorTableParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameterfv, "glGetColorTableParameterfv", (void**)&glGetColorTableParameterfv},
		{"glBlendEquation", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glBlendEquation, "glBlendEquation", (void**)&glBlendEquation},
		{"glBlendColor", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glBlendColor, "glBlendColor", (void**)&glBlendColor},
		{"glHistogram", "(IIIZ)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glHistogram, "glHistogram", (void**)&glHistogram},
		{"glResetHistogram", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glResetHistogram, "glResetHistogram", (void**)&glResetHistogram},
		{"nglGetHistogram", "(IZIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetHistogram, "glGetHistogram", (void**)&glGetHistogram},
		{"nglGetHistogramParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameterfv, "glGetHistogramParameterfv", (void**)&glGetHistogramParameterfv},
		{"nglGetHistogramParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameteriv, "glGetHistogramParameteriv", (void**)&glGetHistogramParameteriv},
		{"glMinmax", "(IIZ)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glMinmax, "glMinmax", (void**)&glMinmax},
		{"glResetMinmax", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glResetMinmax, "glResetMinmax", (void**)&glResetMinmax},
		{"nglGetMinmax", "(IZIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetMinmax, "glGetMinmax", (void**)&glGetMinmax},
		{"nglGetMinmaxParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameterfv, "glGetMinmaxParameterfv", (void**)&glGetMinmaxParameterfv},
		{"nglGetMinmaxParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameteriv, "glGetMinmaxParameteriv", (void**)&glGetMinmaxParameteriv},
		{"nglConvolutionFilter1D", "(IIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter1D, "glConvolutionFilter1D", (void**)&glConvolutionFilter1D},
		{"nglConvolutionFilter2D", "(IIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter2D, "glConvolutionFilter2D", (void**)&glConvolutionFilter2D},
		{"glConvolutionParameterf", "(IIF)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameterf, "glConvolutionParameterf", (void**)&glConvolutionParameterf},
		{"nglConvolutionParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameterfv, "glConvolutionParameterfv", (void**)&glConvolutionParameterfv},
		{"glConvolutionParameteri", "(III)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameteri, "glConvolutionParameteri", (void**)&glConvolutionParameteri},
		{"nglConvolutionParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameteriv, "glConvolutionParameteriv", (void**)&glConvolutionParameteriv},
		{"glCopyConvolutionFilter1D", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter1D, "glCopyConvolutionFilter1D", (void**)&glCopyConvolutionFilter1D},
		{"glCopyConvolutionFilter2D", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter2D, "glCopyConvolutionFilter2D", (void**)&glCopyConvolutionFilter2D},
		{"nglGetConvolutionFilter", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionFilter, "glGetConvolutionFilter", (void**)&glGetConvolutionFilter},
		{"nglGetConvolutionParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameterfv, "glGetConvolutionParameterfv", (void**)&glGetConvolutionParameterfv},
		{"nglGetConvolutionParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameteriv, "glGetConvolutionParameteriv", (void**)&glGetConvolutionParameteriv},
		{"nglSeparableFilter2D", "(IIIIIILjava/nio/Buffer;ILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglSeparableFilter2D, "glSeparableFilter2D", (void**)&glSeparableFilter2D},
		{"nglGetSeparableFilter", "(IIILjava/nio/Buffer;ILjava/nio/Buffer;ILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBImaging_nglGetSeparableFilter, "glGetSeparableFilter", (void**)&glGetSeparableFilter}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}

