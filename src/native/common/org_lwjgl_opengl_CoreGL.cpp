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

#include "org_lwjgl_opengl_CoreGL.h"
#include "checkGLerror.h"
#include "extgl.h"

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glAccum(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAccum((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glAlphaFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glAlphaFunc(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAlphaFunc((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearAccum(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearAccum((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClear
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClear(JNIEnv * env, jclass clazz, jint p0)
{
	glClear((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCallLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCallLists(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject lists_buffer)
{
	const void *lists = (const void *)env->GetDirectBufferAddress(lists_buffer);
	glCallLists((GLint) p0, (GLint) p1, lists);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCallList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCallList(JNIEnv * env, jclass clazz, jint p0)
{
	glCallList((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBlendFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBlendFunc(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBlendFunc((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBlendColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBlendColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBitmap
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBitmap(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jobject image_buffer)
{
	const GLubyte *image = (const GLubyte *)env->GetDirectBufferAddress(image_buffer);
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, image);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBindTexture
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBindTexture(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBindTexture((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBegin
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBegin(JNIEnv * env, jclass clazz, jint p0)
{
	glBegin((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEnd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEnd(JNIEnv * env, jclass clazz)
{
	glEnd();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glArrayElement
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glArrayElement(JNIEnv * env, jclass clazz, jint p0)
{
	glArrayElement((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glAreTexturesResident
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glAreTexturesResident(JNIEnv * env, jclass clazz, jint p0, jobject names_buffer, jobject residences_buffer)
{
	const GLuint *names = (const GLuint *)env->GetDirectBufferAddress(names_buffer);
	GLboolean *residences = (GLboolean *)env->GetDirectBufferAddress(residences_buffer);
	jboolean ret = (jboolean) glAreTexturesResident((GLint) p0, names, residences);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearDepth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearDepth(JNIEnv * env, jclass clazz, jdouble p0)
{
	glClearDepth((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDeleteLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDeleteLists(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glDeleteLists((GLuint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDeleteTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDeleteTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glDeleteTextures((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCullFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCullFace(JNIEnv * env, jclass clazz, jint p0)
{
	glCullFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCopyTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCopyTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glCopyPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorMaterial
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorMaterial(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glColorMaterial((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorMask(JNIEnv * env, jclass clazz, jboolean p0, jboolean p1, jboolean p2, jboolean p3)
{
	glColorMask((GLboolean) p0, (GLboolean) p1, (GLboolean) p2, (GLboolean) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2)
{
	glColor3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glColor3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glColor3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2)
{
	glColor3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3ui(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glColor3ui((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3us(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2)
{
	glColor3us((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glColor4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glColor4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glColor4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4ui(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glColor4ui((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4us(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4us((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);	
	glClipPlane((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearStencil
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearStencil(JNIEnv * env, jclass clazz, jint p0)
{
	glClearStencil((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearIndex
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearIndex(JNIEnv * env, jclass clazz, jfloat p0)
{
	glClearIndex((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalPoint1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalPoint1(JNIEnv * env, jclass clazz, jint p0)
{
	glEvalPoint1((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalPoint2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalPoint2(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glEvalPoint2((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalMesh1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalMesh1(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glEvalMesh1((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalMesh2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalMesh2(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glEvalMesh2((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord1d(JNIEnv * env, jclass clazz, jdouble p0)
{
	glEvalCoord1d((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glEvalCoord1f((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord2d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glEvalCoord2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glEvalCoord2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEnableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEnableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glEnableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDisableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDisableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glDisableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEnable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEnable(JNIEnv * env, jclass clazz, jint p0)
{
	glEnable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDisable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDisable(JNIEnv * env, jclass clazz, jint p0)
{
	glDisable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEdgeFlagPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEdgeFlagPointer(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glEdgeFlagPointer((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEdgeFlag
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEdgeFlag(JNIEnv * env, jclass clazz, jboolean p0)
{
	glEdgeFlag((GLboolean) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawElements
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawElements(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glDrawBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glDrawArrays((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDepthRange
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDepthRange(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glDepthRange((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDepthMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDepthMask(JNIEnv * env, jclass clazz, jboolean p0)
{
	glDepthMask((GLboolean) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDepthFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDepthFunc(JNIEnv * env, jclass clazz, jint p0)
{
	glDepthFunc((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFeedbackBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFeedbackBuffer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glFeedbackBuffer((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapuiv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLushort *address = (GLushort *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapusv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMaterialfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMaterialiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMapdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMapdv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLdouble *address = (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetMapdv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMapfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMapiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMapiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMapiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetLightfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetLightiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetError
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_glGetError(JNIEnv * env, jclass clazz)
{
	jint ret = (jint) glGetError();
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLdouble *address = (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetClipPlane((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetBooleanv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetBooleanv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetBooleanv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetDoublev
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetDoublev(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLdouble *address = (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetDoublev((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetFloatv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetFloatv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetFloatv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetIntegerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetIntegerv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetIntegerv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGenTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGenTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenTextures((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGenLists
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_glGenLists(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glGenLists((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFrustum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFrustum(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glFrustum((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFrontFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFrontFace(JNIEnv * env, jclass clazz, jint p0)
{
	glFrontFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glFogf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glFogi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glFogfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glFogiv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFlush
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFlush(JNIEnv * env, jclass clazz)
{
	glFlush();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFinish
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFinish(JNIEnv * env, jclass clazz)
{
	glFinish();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPointerv
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPointerv(JNIEnv * env, jclass clazz, jint p0, int size)
{
	void *pointer;
	glGetPointerv((GLint) p0, &pointer);
	CHECK_GL_ERROR
	return env->NewDirectByteBuffer(pointer, size);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIsEnabled
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glIsEnabled(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsEnabled((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glInterleavedArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glInterleavedArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glInterleavedArrays((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glInitNames
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glInitNames(JNIEnv * env, jclass clazz)
{
	glInitNames();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glIndexPointer((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexMask(JNIEnv * env, jclass clazz, jint p0)
{
	glIndexMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexd(JNIEnv * env, jclass clazz, jdouble p0)
{
	glIndexd((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexf(JNIEnv * env, jclass clazz, jfloat p0)
{
	glIndexf((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexi(JNIEnv * env, jclass clazz, jint p0)
{
	glIndexi((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexs
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexs(JNIEnv * env, jclass clazz, jshort p0)
{
	glIndexs((GLshort) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexub(JNIEnv * env, jclass clazz, jbyte p0)
{
	glIndexub((GLbyte) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glHint
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glHint(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glHint((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexParameterfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexParameteriv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexLevelParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexLevelParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexLevelParameterfv((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexLevelParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexLevelParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexLevelParameteriv((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexImage
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexImage(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer)
{
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexGendv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLdouble *address = (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetTexGendv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexGenfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexGeniv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexGeniv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexEnvfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexEnviv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_CoreGL_glGetString(JNIEnv * env, jclass clazz, jint p0)
{
	const GLubyte * string = glGetString((GLenum) p0);
	return env->NewStringUTF((const char *) string);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer)
{
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetPolygonStipple(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIsList
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glIsList(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsList((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMaterialf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMaterialf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glMaterialf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMateriali
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMateriali(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glMateriali((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMaterialfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glMaterialiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid1d(JNIEnv * env, jclass clazz, jint p0, jdouble p1, jdouble p2)
{
	glMapGrid1d((GLint) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	glMapGrid1f((GLint) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid2d(JNIEnv * env, jclass clazz, jint p0, jdouble p1, jdouble p2, jint p3, jdouble p4, jdouble p5)
{
	glMapGrid2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jfloat p4, jfloat p5)
{
	glMapGrid2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap2d(JNIEnv * env, jclass clazz, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jdouble p5, jdouble p6, jint p7, jint p8, jobject buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glMap2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, (GLdouble) p5, (GLdouble) p6, (GLint) p7, (GLint) p8, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jfloat p5, jfloat p6, jint p7, jint p8, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMap2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (GLfloat) p5, (GLfloat) p6, (GLint) p7, (GLint) p8, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap1d(JNIEnv * env, jclass clazz, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jobject buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glMap1d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMap1f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLogicOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLogicOp(JNIEnv * env, jclass clazz, jint p0)
{
	glLogicOp((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadName(JNIEnv * env, jclass clazz, jint p0)
{
	glLoadName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadMatrixd(JNIEnv * env, jclass clazz, jobject buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glLoadMatrixd(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadMatrixf(JNIEnv * env, jclass clazz, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadIdentity
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadIdentity(JNIEnv * env, jclass clazz)
{
	glLoadIdentity();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glListBase
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glListBase(JNIEnv * env, jclass clazz, jint p0)
{
	glListBase((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLineWidth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLineWidth(JNIEnv * env, jclass clazz, jfloat p0)
{
	glLineWidth((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLineStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLineStipple(JNIEnv * env, jclass clazz, jint p0, jshort p1)
{
	glLineStipple((GLint) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModelf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModelf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glLightModelf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModeli
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModeli(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glLightModeli((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModelfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModelfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLightModelfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModeliv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModeliv(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glLightModeliv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glLightf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLighti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLighti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glLighti((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLightfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glLightiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIsTexture
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glIsTexture(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsTexture((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMatrixMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMatrixMode(JNIEnv * env, jclass clazz, jint p0)
{
	glMatrixMode((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer)
{
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glPolygonStipple(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPolygonOffset
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPolygonOffset(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPolygonOffset((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPolygonMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPolygonMode(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPolygonMode((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPointSize
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPointSize(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPointSize((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelZoom
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelZoom(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPixelZoom((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelTransferf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelTransferf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelTransferf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelTransferi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelTransferi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelTransferi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelStoref
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelStoref(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelStoref((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelStorei
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelStorei(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelStorei((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glPixelMapfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glPixelMapuiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLushort *address = (const GLushort *)env->GetDirectBufferAddress(buffer);
	glPixelMapusv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPassThrough
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPassThrough(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPassThrough((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glOrtho
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glOrtho(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glOrtho((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormalPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormalPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glNormalPointer((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glNormal3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2)
{
	glNormal3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glNormal3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glNormal3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2)
{
	glNormal3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNewList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNewList(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glNewList((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEndList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEndList(JNIEnv * env, jclass clazz)
{
	glEndList();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultMatrixd(JNIEnv * env, jclass clazz, jobject buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glMultMatrixd(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultMatrixf(JNIEnv * env, jclass clazz, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMultMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPrioritizeTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPrioritizeTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jobject buffer2)
{
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	const GLfloat *address2 = (const GLfloat *)env->GetDirectBufferAddress(buffer2);
	glPrioritizeTextures((GLint) p0, address, address2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glShadeModel
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glShadeModel(JNIEnv * env, jclass clazz, jint p0)
{
	glShadeModel((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glSelectBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glSelectBuffer(JNIEnv * env, jclass clazz, jint p0, jobject buffer)
{
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glSelectBuffer((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glScissor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glScissor(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glScissor((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glScaled
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glScaled(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2)
{
	glScaled((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glScalef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glScalef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glScalef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRotated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRotated(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRotated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRotatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRotatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRotatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRenderMode
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_glRenderMode(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glRenderMode((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectd(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRectd((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectf(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRectf((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRecti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRecti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRecti((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRects
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRects(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRects((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glReadPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glReadPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer)
{
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glReadBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glReadBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glReadBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glRasterPos2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glRasterPos2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glRasterPos2i((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2s(JNIEnv * env, jclass clazz, jshort p0, jshort p1)
{
	glRasterPos2s((GLshort) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2)
{
	glRasterPos3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glRasterPos3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glRasterPos3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2)
{
	glRasterPos3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRasterPos4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRasterPos4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRasterPos4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRasterPos4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushName(JNIEnv * env, jclass clazz, jint p0)
{
	glPushName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopName(JNIEnv * env, jclass clazz)
{
	glPopName();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushMatrix(JNIEnv * env, jclass clazz)
{
	glPushMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopMatrix(JNIEnv * env, jclass clazz)
{
	glPopMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushClientAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushClientAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopClientAttrib(JNIEnv * env, jclass clazz)
{
	glPopClientAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopAttrib(JNIEnv * env, jclass clazz)
{
	glPopAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glStencilFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glStencilFunc(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilFunc((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertexPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glVertex2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glVertex2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glVertex2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2s(JNIEnv * env, jclass clazz, jshort p0, jshort p1)
{
	glVertex2s((GLshort) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2)
{
	glVertex3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glVertex3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glVertex3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2)
{
	glVertex3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glVertex4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertex4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glVertex4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glVertex4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTranslated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTranslated(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2)
{
	glTranslated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTranslatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTranslatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTranslatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexParameterf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexParameterf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexParameterf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexParameteri
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexParameteri(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexParameteri((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGend
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGend(JNIEnv * env, jclass clazz, jint p0, jint p1, jdouble p2)
{
	glTexGend((GLint) p0, (GLint) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGenf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGenf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexGenf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGeni
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGeni(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexGeni((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGendv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glTexGendv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glTexGenfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGeniv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glTexGeniv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnvf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnvf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexEnvf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnvi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnvi(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexEnvi((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glTexEnvfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer)
{
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glTexEnviv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoordPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoordPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1d(JNIEnv * env, jclass clazz, jdouble p0)
{
	glTexCoord1d((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glTexCoord1f((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1i(JNIEnv * env, jclass clazz, jint p0)
{
	glTexCoord1i((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1s(JNIEnv * env, jclass clazz, jshort p0)
{
	glTexCoord1s((GLshort) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glTexCoord2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glTexCoord2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glTexCoord2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2s(JNIEnv * env, jclass clazz, jshort p0, jshort p1)
{
	glTexCoord2s((GLshort) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2)
{
	glTexCoord3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTexCoord3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexCoord3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2)
{
	glTexCoord3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4d(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glTexCoord4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glTexCoord4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glTexCoord4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4s(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glTexCoord4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glStencilOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glStencilOp(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilOp((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glStencilMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glStencilMask(JNIEnv * env, jclass clazz, jint p0)
{
	glStencilMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glViewport
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glViewport(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glViewport((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorTable
  (JNIEnv * env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glColorTable(target, internalFormat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorSubTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorSubTable
  (JNIEnv * env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glColorSubTable(target, start, count, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetColorTable
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetColorTable
  (JNIEnv * env, jclass clazz, jint target, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetColorTable)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetColorTable(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetColorTableParameteriv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetColorTableParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetColorTableParameterfv
  (JNIEnv * env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetColorTableParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetColorTableParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorTableParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glColorTableParameteriv)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glColorTableParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorTableParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glColorTableParameterfv)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glColorTableParameterfv(target, pname, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyColorSubTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyColorSubTable
  (JNIEnv *env, jclass clazz, jint target, jint start, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyColorSubTable)
	glCopyColorSubTable(target, start, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyColorTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyColorTable
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyColorTable)
	glCopyColorTable(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBlendEquation
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBlendEquation
  (JNIEnv *env, jclass clazz, jint mode)
{
	CHECK_EXISTS(glBlendEquation)
	glBlendEquation(mode);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glHistogram
 * Signature: (IIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glHistogram
  (JNIEnv *env, jclass clazz, jint target, jint width, jint internalformat, jboolean sink)
{
	CHECK_EXISTS(glHistogram)
	glHistogram(target, width, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glResetHistogram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glResetHistogram
  (JNIEnv *env, jclass clazz, jint target)
{
	CHECK_EXISTS(glResetHistogram)
	glResetHistogram(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetHistogram
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetHistogram
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetHistogram)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetHistogram(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetHistogramParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetHistogramParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetHistogramParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetHistogramParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetHistogramParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetHistogramParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetHistogramParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMinmax
 * Signature: (IIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMinmax
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jboolean sink)
{
	CHECK_EXISTS(glMinmax)
	glMinmax(target, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glResetMinmax
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glResetMinmax
  (JNIEnv *env, jclass clazz, jint target)
{
	CHECK_EXISTS(glResetMinmax)
	glResetMinmax(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMinmax
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMinmax
  (JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetMinmax)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetMinmax(target, reset, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMinmaxParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMinmaxParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetMinmaxParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMinmaxParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMinmaxParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetMinmaxParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMinmaxParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glConvolutionFilter1D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glConvolutionFilter1D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glConvolutionFilter1D(target, internalformat, width, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glConvolutionFilter2D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glConvolutionFilter2D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glConvolutionFilter2D(target, internalformat, width, height, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glConvolutionParameterf
 * Signature: (IIF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glConvolutionParameterf
  (JNIEnv *env, jclass clazz, jint target, jint pname, jfloat params)
{
	CHECK_EXISTS(glConvolutionParameterf)
	glConvolutionParameterf(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glConvolutionParameterfv)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glConvolutionParameteri
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glConvolutionParameteri
  (JNIEnv *env, jclass clazz, jint target, jint pname, jint params)
{
	CHECK_EXISTS(glConvolutionParameteri)
	glConvolutionParameteri(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glConvolutionParameteriv)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glConvolutionParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyConvolutionFilter1D
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyConvolutionFilter1D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width)
{
	CHECK_EXISTS(glCopyConvolutionFilter1D)
	glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyConvolutionFilter2D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyConvolutionFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width, jint height)
{
	CHECK_EXISTS(glCopyConvolutionFilter2D)
	glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetConvolutionFilter
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetConvolutionFilter
  (JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glGetConvolutionFilter)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionFilter(target, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetConvolutionParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetConvolutionParameterfv)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameterfv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetConvolutionParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer)
{
	CHECK_EXISTS(glGetConvolutionParameteriv)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetConvolutionParameteriv(target, pname, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glSeparableFilter2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glSeparableFilter2D
  (JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject row, jobject column)
{
	CHECK_EXISTS(glSeparableFilter2D)
	const void *address = (const void *)env->GetDirectBufferAddress(row);
	const void *address2 = env->GetDirectBufferAddress(column);
	glSeparableFilter2D(target, internalformat, width, height, format, type, address, address2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetSeparableFilter
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetSeparableFilter
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
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawRangeElements
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawRangeElements
  (JNIEnv *env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject buffer)
{
	CHECK_EXISTS(glDrawRangeElements)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glDrawRangeElements(mode, start, end, count, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexImage3D
 * Signature: (IIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glTexImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jobject buffer)
{
	CHECK_EXISTS(glTexSubImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexSubImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint x, jint y, jint width, jint height)
{
	CHECK_EXISTS(glCopyTexSubImage3D)
	glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glActiveTexture)
	glActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClientActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClientActiveTexture
  (JNIEnv *env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glClientActiveTexture)
	glClientActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCompressedTexImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCompressedTexImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexImage1D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage1D(target, level, internalformat, width, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCompressedTexImage2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCompressedTexImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexImage2D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCompressedTexImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCompressedTexImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCompressedTexSubImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCompressedTexSubImage1D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexSubImage1D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCompressedTexSubImage2D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCompressedTexSubImage2D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexSubImage2D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCompressedTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCompressedTexSubImage3D
  (JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imagesize, jobject buffer)
{
	CHECK_EXISTS(glCompressedTexSubImage3D)
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imagesize, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetCompressedTexImage
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetCompressedTexImage
  (JNIEnv *env, jclass clazz, jint target, jint lod, jobject buffer)
{
	CHECK_EXISTS(glGetCompressedTexImage)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glGetCompressedTexImage(target, lod, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiDrawArrays
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiDrawArrays
  (JNIEnv * env, jclass clazz, jint mode, jobject first, jobject count, jint primcount) {
	CHECK_EXISTS(glMultiDrawArrays)
	GLint *address = (GLint *)env->GetDirectBufferAddress(first);
	GLsizei *address2 = (GLsizei *)env->GetDirectBufferAddress(count);
	glMultiDrawArrays(mode, address, address2, (GLsizei)primcount);
	CHECK_GL_ERROR
}
  
/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiDrawElements
 * Signature: (IIIII)V
 */
/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiDrawElements
  (JNIEnv * env, jobject, jint mode, jobject count, jint type, jobject indices, jint primcount) {
	CHECK_EXISTS(glMultiDrawElements)
	const GLsizei *address = (const GLsizei *)env->GetDirectBufferAddress(count);
	const void *address2 = (const void *)env->GetDirectBufferAddress(indices);
	glMultiDrawElements(mode, address, type, address2, primcount);
	CHECK_GL_ERROR
}
*/  
    
/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord1d
 * Signature: (ID)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord1d
  (JNIEnv *env, jclass clazz, jint target, jdouble s)
{
	CHECK_EXISTS(glMultiTexCoord1d)
	glMultiTexCoord1d(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord1f
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord1f
  (JNIEnv *env, jclass clazz, jint target, jfloat s)
{
	CHECK_EXISTS(glMultiTexCoord1f)
	glMultiTexCoord1f(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord1i
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord1i
  (JNIEnv *env, jclass clazz, jint target, jint s)
{
	CHECK_EXISTS(glMultiTexCoord1i)
	glMultiTexCoord1i(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord1s
 * Signature: (IS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord1s
  (JNIEnv *env, jclass clazz, jint target, jshort s)
{
	CHECK_EXISTS(glMultiTexCoord1s)
	glMultiTexCoord1s(target, s);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord2d
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord2d
  (JNIEnv *env, jclass clazz, jint target, jdouble s, jdouble t)
{
	CHECK_EXISTS(glMultiTexCoord2d)
	glMultiTexCoord2d(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord2f
 * Signature: (IFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord2f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t)
{
	CHECK_EXISTS(glMultiTexCoord2f)
	glMultiTexCoord2f(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord2i
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord2i
  (JNIEnv *env, jclass clazz, jint target, jint s, jint t)
{
	CHECK_EXISTS(glMultiTexCoord2i)
	glMultiTexCoord2i(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord2s
 * Signature: (ISS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord2s
  (JNIEnv *env, jclass clazz, jint target, jshort s, jshort t)
{
	CHECK_EXISTS(glMultiTexCoord2s)
	glMultiTexCoord2s(target, s, t);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord3d
 * Signature: (IDDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord3d
  (JNIEnv *env, jclass clazz, jint target, jdouble s, jdouble t, jdouble r)
{
	CHECK_EXISTS(glMultiTexCoord3d)
	glMultiTexCoord3d(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord3f
 * Signature: (IFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord3f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r)
{
	CHECK_EXISTS(glMultiTexCoord3f)
	glMultiTexCoord3f(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord3i
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord3i
  (JNIEnv *env, jclass clazz, jint target, jint s, jint t, jint r)
{
	CHECK_EXISTS(glMultiTexCoord3i)
	glMultiTexCoord3i(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord3s
 * Signature: (ISSS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord3s
  (JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r)
{
	CHECK_EXISTS(glMultiTexCoord3s)
	glMultiTexCoord3s(target, s, t, r);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord4d
 * Signature: (IDDDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord4d
  (JNIEnv *env, jclass clazz, jint target, jdouble s, jdouble t, jdouble r, jdouble q)
{
	CHECK_EXISTS(glMultiTexCoord4d)
	glMultiTexCoord4d(target, s, t, r, q);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord4f
 * Signature: (IFFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord4f
  (JNIEnv *env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q)
{
	CHECK_EXISTS(glMultiTexCoord4f)
	glMultiTexCoord4f(target, s, t, r, q);
	
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord4i
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord4i
  (JNIEnv *env, jclass clazz, jint target, jint s, jint t, jint r, jint q)
{
	CHECK_EXISTS(glMultiTexCoord4i)
	glMultiTexCoord4i(target, s, t, r, q);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultiTexCoord4s
 * Signature: (ISSSS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultiTexCoord4s
  (JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q)
{
	CHECK_EXISTS(glMultiTexCoord4s)
	glMultiTexCoord4s(target, s, t, r, q);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadTransposeMatrixd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadTransposeMatrixd
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glLoadTransposeMatrixd)
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glLoadTransposeMatrixd(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glLoadTransposeMatrixf)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadTransposeMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultTransposeMatrixd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultTransposeMatrixd
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glMultTransposeMatrixd)
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	glMultTransposeMatrixd(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultTransposeMatrixf
  (JNIEnv *env, jclass clazz, jobject buffer)
{
	CHECK_EXISTS(glMultTransposeMatrixf)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMultTransposeMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glSampleCoverage
 * Signature: (FZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glSampleCoverage
  (JNIEnv *env, jclass clazz, jfloat value, jboolean invert)
{
	CHECK_EXISTS(glSampleCoverage)
	glSampleCoverage(value, invert);
	CHECK_GL_ERROR
}

