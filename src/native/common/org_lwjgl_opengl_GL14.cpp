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

#include "checkGLerror.h"
#include "extgl.h"

typedef void (APIENTRY * glBlendColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef void (APIENTRY * glBlendEquationPROC) (GLenum mode);
typedef void (APIENTRY * glFogCoordfPROC) (GLfloat coord);
typedef void (APIENTRY * glFogCoordPointerPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glMultiDrawArraysPROC) (GLenum mode, GLint *first, GLsizei *count, GLsizei primcount);
typedef void (APIENTRY * glPointParameterfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPointParameterfvPROC) (GLenum pname, GLfloat *params);
typedef void (APIENTRY * glSecondaryColor3bPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY * glSecondaryColor3fPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY * glSecondaryColor3ubPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY * glSecondaryColorPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glBlendFuncSeparatePROC) (GLenum sfactorRGB, GLenum dfactorRGB, GLenum sfactorAlpha, GLenum dfactorAlpha);
typedef void (APIENTRY * glWindowPos2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glWindowPos2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glWindowPos3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glWindowPos3iPROC) (GLint x, GLint y, GLint z);

static glFogCoordfPROC glFogCoordf;
static glFogCoordPointerPROC glFogCoordPointer;
static glMultiDrawArraysPROC glMultiDrawArrays;
static glPointParameterfPROC glPointParameterf;
static glPointParameterfvPROC glPointParameterfv;
static glSecondaryColor3bPROC glSecondaryColor3b;
static glSecondaryColor3fPROC glSecondaryColor3f;
static glSecondaryColor3ubPROC glSecondaryColor3ub;
static glSecondaryColorPointerPROC glSecondaryColorPointer;
static glBlendFuncSeparatePROC glBlendFuncSeparate;
static glWindowPos2fPROC glWindowPos2f;
static glWindowPos2iPROC glWindowPos2i;
static glWindowPos3fPROC glWindowPos3f;
static glWindowPos3iPROC glWindowPos3i;
static glBlendColorPROC glBlendColor;
static glBlendEquationPROC glBlendEquation;

static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glBlendEquation
  (JNIEnv *env, jclass clazz, jint mode)
{
	glBlendEquation(mode);
	CHECK_GL_ERROR
}


static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glBlendColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glFogCoordf
 * Signature: (F)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glFogCoordf
  (JNIEnv *env, jclass clazz, jfloat f) {
	glFogCoordf(f);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglFogCoordPointer
 * Signature: (IILjava/nio/Buffer;)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglFogCoordPointer
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jobject buffer, jint offset) {
	GLvoid *address = (GLvoid *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glFogCoordPointer(p1, p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglFogCoordPointerVBO
 * Signature: (IILjava/nio/Buffer;)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglFogCoordPointerVBO
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint buffer_offset) {
	glFogCoordPointer(p1, p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glMultiDrawArrays
 * Signature: (IIII)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglMultiDrawArrays
  (JNIEnv *env, jclass clazz, jint mode, jobject first, jint first_offset, jobject count, jint count_offset, jint primcount) {
	GLint *address = first_offset + (GLint *)env->GetDirectBufferAddress(first);
	GLsizei *address2 = count_offset + (GLsizei *)env->GetDirectBufferAddress(count);
	glMultiDrawArrays(mode, address, address2, (GLsizei)primcount);
	CHECK_GL_ERROR
}
  
/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glPointParameterf
 * Signature: (IF)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glPointParameterf
  (JNIEnv *env, jclass clazz, jint p1, jfloat p2) {
	glPointParameterf(p1, p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glPointParameterfv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglPointParameterfv
  (JNIEnv *env, jclass clazz, jint p1, jobject buffer, jint offset) {
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glPointParameterfv(p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glSecondaryColor3b
 * Signature: (BBB)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3b
  (JNIEnv *env, jclass clazz, jbyte p1, jbyte p2, jbyte p3) {
	glSecondaryColor3b(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glSecondaryColor3f
 * Signature: (FFF)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2, jfloat p3) {
	glSecondaryColor3f(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glSecondaryColor3ub
 * Signature: (BBB)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3ub
  (JNIEnv *env, jclass clazz, jbyte p1, jbyte p2, jbyte p3) {
	glSecondaryColor3ub(p1, p2, p3);
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglSecondaryColorPointer
 * Signature: (IIILjava/nio/Buffer;)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointer
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jobject buffer, jint offset) {
	const GLvoid *address = (const GLvoid *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glSecondaryColorPointer(p1, p2, p3, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    nglSecondaryColorPointerVBO
 * Signature: (IIILjava/nio/Buffer;)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointerVBO
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jint buffer_offset) {
	glSecondaryColorPointer(p1, p2, p3, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glBlendFuncSeparate
 * Signature: (IIII)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glBlendFuncSeparate
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jint p4) {
	glBlendFuncSeparate(p1, p2, p3, p4);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos2f
 * Signature: (FF)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos2f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2) {
        glWindowPos2f(p1, p2);
}

/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos2i
 * Signature: (II)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos2i
  (JNIEnv *env, jclass clazz, jint p1, jint p2) {
        glWindowPos2i(p1, p2);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos3f
 * Signature: (FFF)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos3f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2, jfloat p3) {
        glWindowPos3f(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_GL14
 * Method:    glWindowPos3i
 * Signature: (III)V
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos3i
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3) {
        glWindowPos3i(p1, p2, p3);
}

void extgl_InitOpenGL1_4(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"glBlendEquation", "(I)V", (void*)&Java_org_lwjgl_opengl_GL14_glBlendEquation, "glBlendEquation", (void**)&glBlendEquation},
		{"glBlendColor", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL14_glBlendColor, "glBlendColor", (void**)&glBlendColor},
		{"glFogCoordf", "(F)V", (void*)&Java_org_lwjgl_opengl_GL14_glFogCoordf, "glFogCoordf", (void**)&glFogCoordf},
		{"nglFogCoordPointer", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL14_nglFogCoordPointer, "glFogCoordPointer", (void**)&glFogCoordPointer},
		{"nglFogCoordPointerVBO", "(III)V", (void*)&Java_org_lwjgl_opengl_GL14_nglFogCoordPointerVBO, NULL, NULL},
		{"nglMultiDrawArrays", "(ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;II)V", (void*)&Java_org_lwjgl_opengl_GL14_nglMultiDrawArrays, "glMultiDrawArrays", (void**)&glMultiDrawArrays},
		{"glPointParameterf", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL14_glPointParameterf, "glPointParameterf", (void**)&glPointParameterf},
		{"nglPointParameterfv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL14_nglPointParameterfv, "glPointParameterfv", (void**)&glPointParameterfv},
		{"glSecondaryColor3b", "(BBB)V", (void*)&Java_org_lwjgl_opengl_GL14_glSecondaryColor3b, "glSecondaryColor3b", (void**)&glSecondaryColor3b},
		{"glSecondaryColor3f", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL14_glSecondaryColor3f, "glSecondaryColor3f", (void**)&glSecondaryColor3f},
		{"glSecondaryColor3ub", "(BBB)V", (void*)&Java_org_lwjgl_opengl_GL14_glSecondaryColor3ub, "glSecondaryColor3ub", (void**)&glSecondaryColor3ub},
		{"nglSecondaryColorPointer", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointer, "glSecondaryColorPointer", (void**)&glSecondaryColorPointer},
		{"nglSecondaryColorPointerVBO", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointerVBO, NULL, NULL},
		{"glBlendFuncSeparate", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL14_glBlendFuncSeparate, "glBlendFuncSeparate", (void**)&glBlendFuncSeparate},
		{"glWindowPos2f", "(FF)V", (void*)&Java_org_lwjgl_opengl_GL14_glWindowPos2f, "glWindowPos2f", (void**)&glWindowPos2f},
		{"glWindowPos2i", "(II)V", (void*)&Java_org_lwjgl_opengl_GL14_glWindowPos2i, "glWindowPos2i", (void**)&glWindowPos2i},
		{"glWindowPos3f", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL14_glWindowPos3f, "glWindowPos3f", (void**)&glWindowPos3f},
		{"glWindowPos3i", "(III)V", (void*)&Java_org_lwjgl_opengl_GL14_glWindowPos3i, "glWindowPos3i", (void**)&glWindowPos3i}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/GL14");
	if (extgl_Extensions.OpenGL14)
		extgl_InitializeClass(env, clazz, ext_set, "OpenGL14", num_functions, functions);
}

