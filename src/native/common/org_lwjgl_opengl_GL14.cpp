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

#include "org_lwjgl_opengl_GL14.h"
#include "checkGLerror.h"
#include "extgl.h"

typedef void (APIENTRY * glBlendColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha );
typedef void (APIENTRY * glBlendEquationPROC) (GLenum mode );
typedef void (APIENTRY * glFogCoordfPROC) (GLfloat coord);
typedef void (APIENTRY * glFogCoordfvPROC) (const GLfloat *coord);
typedef void (APIENTRY * glFogCoorddPROC) (GLdouble coord);
typedef void (APIENTRY * glFogCoorddvPROC) (const GLdouble *coord);
typedef void (APIENTRY * glFogCoordPointerPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glMultiDrawArraysPROC) (GLenum mode, GLint *first, GLsizei *count, GLsizei primcount);
typedef void (APIENTRY * glMultiDrawElementsPROC) (GLenum mode, GLsizei *count, GLenum type, const GLvoid **indices, GLsizei primcount);
typedef void (APIENTRY * glPointParameterfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPointParameterfvPROC) (GLenum pname, GLfloat *params);
typedef void (APIENTRY * glSecondaryColor3bPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY * glSecondaryColor3bvPROC) (const GLbyte *v);
typedef void (APIENTRY * glSecondaryColor3dPROC) (GLdouble red, GLdouble green, GLdouble blue);
typedef void (APIENTRY * glSecondaryColor3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glSecondaryColor3fPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY * glSecondaryColor3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glSecondaryColor3iPROC) (GLint red, GLint green, GLint blue);
typedef void (APIENTRY * glSecondaryColor3ivPROC) (const GLint *v);
typedef void (APIENTRY * glSecondaryColor3sPROC) (GLshort red, GLshort green, GLshort blue);
typedef void (APIENTRY * glSecondaryColor3svPROC) (const GLshort *v);
typedef void (APIENTRY * glSecondaryColor3ubPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY * glSecondaryColor3ubvPROC) (const GLubyte *v);
typedef void (APIENTRY * glSecondaryColor3uiPROC) (GLuint red, GLuint green, GLuint blue);
typedef void (APIENTRY * glSecondaryColor3uivPROC) (const GLuint *v);
typedef void (APIENTRY * glSecondaryColor3usPROC) (GLushort red, GLushort green, GLushort blue);
typedef void (APIENTRY * glSecondaryColor3usvPROC) (const GLushort *v);
typedef void (APIENTRY * glSecondaryColorPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glBlendFuncSeparatePROC) (GLenum sfactorRGB, GLenum dfactorRGB, GLenum sfactorAlpha, GLenum dfactorAlpha);
typedef void (APIENTRY * glWindowPos2dPROC) (GLdouble x, GLdouble y);
typedef void (APIENTRY * glWindowPos2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glWindowPos2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glWindowPos2sPROC) (GLshort x, GLshort y);
typedef void (APIENTRY * glWindowPos2dvPROC) (const GLdouble *p);
typedef void (APIENTRY * glWindowPos2fvPROC) (const GLfloat *p);
typedef void (APIENTRY * glWindowPos2ivPROC) (const GLint *p);
typedef void (APIENTRY * glWindowPos2svPROC) (const GLshort *p);
typedef void (APIENTRY * glWindowPos3dPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glWindowPos3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glWindowPos3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY * glWindowPos3sPROC) (GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glWindowPos3dvPROC) (const GLdouble *p);
typedef void (APIENTRY * glWindowPos3fvPROC) (const GLfloat *p);
typedef void (APIENTRY * glWindowPos3ivPROC) (const GLint *p);
typedef void (APIENTRY * glWindowPos3svPROC) (const GLshort *p);

static glFogCoordfPROC glFogCoordf;
static glFogCoordfvPROC glFogCoordfv;
static glFogCoorddPROC glFogCoordd;
static glFogCoorddvPROC glFogCoorddv;
static glFogCoordPointerPROC glFogCoordPointer;
static glMultiDrawArraysPROC glMultiDrawArrays;
static glMultiDrawElementsPROC glMultiDrawElements;
static glPointParameterfPROC glPointParameterf;
static glPointParameterfvPROC glPointParameterfv;
static glSecondaryColor3bPROC glSecondaryColor3b;
static glSecondaryColor3bvPROC glSecondaryColor3bv;
static glSecondaryColor3dPROC glSecondaryColor3d;
static glSecondaryColor3dvPROC glSecondaryColor3dv;
static glSecondaryColor3fPROC glSecondaryColor3f;
static glSecondaryColor3fvPROC glSecondaryColor3fv;
static glSecondaryColor3iPROC glSecondaryColor3i;
static glSecondaryColor3ivPROC glSecondaryColor3iv;
static glSecondaryColor3sPROC glSecondaryColor3s;
static glSecondaryColor3svPROC glSecondaryColor3sv;
static glSecondaryColor3ubPROC glSecondaryColor3ub;
static glSecondaryColor3ubvPROC glSecondaryColor3ubv;
static glSecondaryColor3uiPROC glSecondaryColor3ui;
static glSecondaryColor3uivPROC glSecondaryColor3uiv;
static glSecondaryColor3usPROC glSecondaryColor3us;
static glSecondaryColor3usvPROC glSecondaryColor3usv;
static glSecondaryColorPointerPROC glSecondaryColorPointer;
static glBlendFuncSeparatePROC glBlendFuncSeparate;
static glWindowPos2dPROC glWindowPos2d;
static glWindowPos2fPROC glWindowPos2f;
static glWindowPos2iPROC glWindowPos2i;
static glWindowPos2sPROC glWindowPos2s;
static glWindowPos2dvPROC glWindowPos2dv;
static glWindowPos2fvPROC glWindowPos2fv;
static glWindowPos2ivPROC glWindowPos2iv;
static glWindowPos2svPROC glWindowPos2sv;
static glWindowPos3dPROC glWindowPos3d;
static glWindowPos3fPROC glWindowPos3f;
static glWindowPos3iPROC glWindowPos3i;
static glWindowPos3sPROC glWindowPos3s;
static glWindowPos3dvPROC glWindowPos3dv;
static glWindowPos3fvPROC glWindowPos3fv;
static glWindowPos3ivPROC glWindowPos3iv;
static glWindowPos3svPROC glWindowPos3sv;
static glBlendColorPROC glBlendColor;
static glBlendEquationPROC glBlendEquation;

void extgl_InitOpenGL1_4(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.OpenGL14)
		return;
	glBlendColor = (glBlendColorPROC) extgl_GetProcAddress("glBlendColor");
	glBlendEquation = (glBlendEquationPROC) extgl_GetProcAddress("glBlendEquation");
	glFogCoordf = (glFogCoordfPROC) extgl_GetProcAddress("glFogCoordf");
	glFogCoordfv = (glFogCoordfvPROC) extgl_GetProcAddress("glFogCoordfv");
	glFogCoordd = (glFogCoorddPROC) extgl_GetProcAddress("glFogCoordd");
	glFogCoorddv = (glFogCoorddvPROC) extgl_GetProcAddress("glFogCoorddv");
	glFogCoordPointer = (glFogCoordPointerPROC) extgl_GetProcAddress("glFogCoordPointer");
	glMultiDrawArrays = (glMultiDrawArraysPROC) extgl_GetProcAddress("glMultiDrawArrays");
	glMultiDrawElements = (glMultiDrawElementsPROC) extgl_GetProcAddress("glMultiDrawElements");
	glPointParameterf = (glPointParameterfPROC) extgl_GetProcAddress("glPointParameterf");
	glPointParameterfv = (glPointParameterfvPROC) extgl_GetProcAddress("glPointParameterfv");
	glSecondaryColor3b = (glSecondaryColor3bPROC) extgl_GetProcAddress("glSecondaryColor3b");
	glSecondaryColor3bv = (glSecondaryColor3bvPROC) extgl_GetProcAddress("glSecondaryColor3bv");
	glSecondaryColor3d = (glSecondaryColor3dPROC) extgl_GetProcAddress("glSecondaryColor3d");
	glSecondaryColor3dv = (glSecondaryColor3dvPROC) extgl_GetProcAddress("glSecondaryColor3dv");
	glSecondaryColor3f = (glSecondaryColor3fPROC) extgl_GetProcAddress("glSecondaryColor3f");
	glSecondaryColor3fv = (glSecondaryColor3fvPROC) extgl_GetProcAddress("glSecondaryColor3fv");
	glSecondaryColor3i = (glSecondaryColor3iPROC) extgl_GetProcAddress("glSecondaryColor3i");
	glSecondaryColor3iv = (glSecondaryColor3ivPROC) extgl_GetProcAddress("glSecondaryColor3iv");
	glSecondaryColor3s = (glSecondaryColor3sPROC) extgl_GetProcAddress("glSecondaryColor3s");
	glSecondaryColor3sv = (glSecondaryColor3svPROC) extgl_GetProcAddress("glSecondaryColor3sv");
	glSecondaryColor3ub = (glSecondaryColor3ubPROC) extgl_GetProcAddress("glSecondaryColor3ub");
	glSecondaryColor3ubv = (glSecondaryColor3ubvPROC) extgl_GetProcAddress("glSecondaryColor3ubv");
	glSecondaryColor3ui = (glSecondaryColor3uiPROC) extgl_GetProcAddress("glSecondaryColor3ui");
	glSecondaryColor3uiv = (glSecondaryColor3uivPROC) extgl_GetProcAddress("glSecondaryColor3uiv");
	glSecondaryColor3us = (glSecondaryColor3usPROC) extgl_GetProcAddress("glSecondaryColor3us");
	glSecondaryColor3usv = (glSecondaryColor3usvPROC) extgl_GetProcAddress("glSecondaryColor3usv");
	glSecondaryColorPointer = (glSecondaryColorPointerPROC) extgl_GetProcAddress("glSecondaryColorPointer");
	glBlendFuncSeparate = (glBlendFuncSeparatePROC) extgl_GetProcAddress("glBlendFuncSeparate");
	glWindowPos2d = (glWindowPos2dPROC) extgl_GetProcAddress("glWindowPos2d");
	glWindowPos2f = (glWindowPos2fPROC) extgl_GetProcAddress("glWindowPos2f");
	glWindowPos2i = (glWindowPos2iPROC) extgl_GetProcAddress("glWindowPos2i");
	glWindowPos2s = (glWindowPos2sPROC) extgl_GetProcAddress("glWindowPos2s");
	glWindowPos2dv = (glWindowPos2dvPROC) extgl_GetProcAddress("glWindowPos2dv");
	glWindowPos2fv = (glWindowPos2fvPROC) extgl_GetProcAddress("glWindowPos2fv");
	glWindowPos2iv = (glWindowPos2ivPROC) extgl_GetProcAddress("glWindowPos2iv");
	glWindowPos2sv = (glWindowPos2svPROC) extgl_GetProcAddress("glWindowPos2sv");
	glWindowPos3d = (glWindowPos3dPROC) extgl_GetProcAddress("glWindowPos3d");
	glWindowPos3f = (glWindowPos3fPROC) extgl_GetProcAddress("glWindowPos3f");
	glWindowPos3i = (glWindowPos3iPROC) extgl_GetProcAddress("glWindowPos3i");
	glWindowPos3s = (glWindowPos3sPROC) extgl_GetProcAddress("glWindowPos3s");
	glWindowPos3dv = (glWindowPos3dvPROC) extgl_GetProcAddress("glWindowPos3dv");
	glWindowPos3fv = (glWindowPos3fvPROC) extgl_GetProcAddress("glWindowPos3fv");
	glWindowPos3iv = (glWindowPos3ivPROC) extgl_GetProcAddress("glWindowPos3iv");
	glWindowPos3sv = (glWindowPos3svPROC) extgl_GetProcAddress("glWindowPos3sv");
	EXTGL_SANITY_CHECK(env, ext_set, OpenGL14)
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glFogCoordf
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glFogCoordf
  (JNIEnv *env, jclass clazz, jfloat f) {
	CHECK_EXISTS(glFogCoordf)
	glFogCoordf(f);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglFogCoordPointer
 * Signature: (IILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglFogCoordPointer
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jobject buffer, jint offset) {
	CHECK_EXISTS(glFogCoordPointer)
	GLvoid *address = (GLvoid *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glFogCoordPointer(p1, p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglFogCoordPointerVBO
 * Signature: (IILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglFogCoordPointerVBO
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint buffer_offset) {
	CHECK_EXISTS(glFogCoordPointer)
	glFogCoordPointer(p1, p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glMultiDrawArrays
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglMultiDrawArrays
  (JNIEnv *env, jclass clazz, jint mode, jobject first, jint first_offset, jobject count, jint count_offset, jint primcount) {
	CHECK_EXISTS(glMultiDrawArrays)
	GLint *address = first_offset + (GLint *)env->GetDirectBufferAddress(first);
	GLsizei *address2 = count_offset + (GLsizei *)env->GetDirectBufferAddress(count);
	glMultiDrawArrays(mode, address, address2, (GLsizei)primcount);
	CHECK_GL_ERROR
}
  
/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glMultiDrawElements
 * Signature: (IIIII)V
 */
/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glMultiDrawElements
  (JNIEnv *env, jclass clazz, jint mode, jobject count, jint type, jobject indices, jint primcount) {
	CHECK_EXISTS(glMultiDrawElements)
	const GLsizei *address = (const GLsizei *)env->GetDirectBufferAddress(count);
	const void *address2 = (const void *)env->GetDirectBufferAddress(indices);
	glMultiDrawElements(mode, address, type, address2, primcount);
	CHECK_GL_ERROR
}
*/  

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glPointParameterf
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glPointParameterf
  (JNIEnv *env, jclass clazz, jint p1, jfloat p2) {
	CHECK_EXISTS(glPointParameterf)
	glPointParameterf(p1, p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glPointParameterfv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglPointParameterfv
  (JNIEnv *env, jclass clazz, jint p1, jobject buffer, jint offset) {
	CHECK_EXISTS(glPointParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glPointParameterfv(p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glSecondaryColor3b
 * Signature: (BBB)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3b
  (JNIEnv *env, jclass clazz, jbyte p1, jbyte p2, jbyte p3) {
	CHECK_EXISTS(glSecondaryColor3b)
	glSecondaryColor3b(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glSecondaryColor3f
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2, jfloat p3) {
	CHECK_EXISTS(glSecondaryColor3f)
	glSecondaryColor3f(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glSecondaryColor3ub
 * Signature: (BBB)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3ub
  (JNIEnv *env, jclass clazz, jbyte p1, jbyte p2, jbyte p3) {
	CHECK_EXISTS(glSecondaryColor3ub)
	glSecondaryColor3ub(p1, p2, p3);
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglSecondaryColorPointer
 * Signature: (IIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointer
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jobject buffer, jint offset) {
	CHECK_EXISTS(glSecondaryColorPointer)
	const GLvoid *address = (const GLvoid *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glSecondaryColorPointer(p1, p2, p3, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglSecondaryColorPointerVBO
 * Signature: (IIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointerVBO
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jint buffer_offset) {
	CHECK_EXISTS(glSecondaryColorPointer)
	glSecondaryColorPointer(p1, p2, p3, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glBlendFuncSeparate
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glBlendFuncSeparate
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jint p4) {
	CHECK_EXISTS(glBlendFuncSeparate)
	glBlendFuncSeparate(p1, p2, p3, p4);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos2f
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos2f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2) {
        CHECK_EXISTS(glWindowPos2f);
        glWindowPos2f(p1, p2);
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos2i
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos2i
  (JNIEnv *env, jclass clazz, jint p1, jint p2) {
        CHECK_EXISTS(glWindowPos2i)
        glWindowPos2i(p1, p2);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos3f
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos3f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2, jfloat p3) {
        CHECK_EXISTS(glWindowPos3f)
        glWindowPos3f(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos3i
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos3i
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3) {
        CHECK_EXISTS(glWindowPos3i)
        glWindowPos3i(p1, p2, p3);
}
