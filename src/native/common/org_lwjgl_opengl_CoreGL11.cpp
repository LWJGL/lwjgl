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

#include "org_lwjgl_opengl_CoreGL11.h"
#include "checkGLerror.h"
#include "extgl.h"

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glAccum(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAccum((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glAlphaFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glAlphaFunc(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAlphaFunc((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearAccum(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearAccum((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClear
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClear(JNIEnv * env, jclass clazz, jint p0)
{
	glClear((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCallLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglCallLists(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject lists_buffer, jint lists_offset)
{
	GLbyte *lists = (GLbyte *)env->GetDirectBufferAddress(lists_buffer);
	glCallLists((GLint) p0, (GLint) p1, (const void *)(lists + lists_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCallList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCallList(JNIEnv * env, jclass clazz, jint p0)
{
	glCallList((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBlendFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBlendFunc(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBlendFunc((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBitmap
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglBitmap(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jobject image_buffer, jint image_offset)
{
	const GLubyte *image = (const GLubyte *)env->GetDirectBufferAddress(image_buffer);
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, image + image_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBindTexture
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBindTexture(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBindTexture((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBegin
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBegin(JNIEnv * env, jclass clazz, jint p0)
{
	glBegin((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEnd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEnd(JNIEnv * env, jclass clazz)
{
	glEnd();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glArrayElement
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glArrayElement(JNIEnv * env, jclass clazz, jint p0)
{
	glArrayElement((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearDepth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearDepth(JNIEnv * env, jclass clazz, jdouble p0)
{
	glClearDepth((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDeleteLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDeleteLists(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glDeleteLists((GLuint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDeleteTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglDeleteTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLuint *address = (const GLuint *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glDeleteTextures((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCullFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCullFace(JNIEnv * env, jclass clazz, jint p0)
{
	glCullFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCopyTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCopyTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glCopyPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglColorPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglColorPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) (address + buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglColorPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglColorPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColorMaterial
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColorMaterial(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glColorMaterial((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColorMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColorMask(JNIEnv * env, jclass clazz, jboolean p0, jboolean p1, jboolean p2, jboolean p3)
{
	glColorMask((GLboolean) p0, (GLboolean) p1, (GLboolean) p2, (GLboolean) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glColor3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glColor4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLdouble *address = offset + (const GLdouble *)env->GetDirectBufferAddress(buffer);	
	glClipPlane((GLint) p0, address + offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearStencil
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearStencil(JNIEnv * env, jclass clazz, jint p0)
{
	glClearStencil((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearIndex
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearIndex(JNIEnv * env, jclass clazz, jfloat p0)
{
	glClearIndex((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalPoint1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalPoint1(JNIEnv * env, jclass clazz, jint p0)
{
	glEvalPoint1((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalPoint2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalPoint2(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glEvalPoint2((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalMesh1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalMesh1(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glEvalMesh1((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalMesh2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalMesh2(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glEvalMesh2((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glEvalCoord1f((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glEvalCoord2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEnableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEnableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glEnableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDisableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDisableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glDisableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEnable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEnable(JNIEnv * env, jclass clazz, jint p0)
{
	glEnable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDisable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDisable(JNIEnv * env, jclass clazz, jint p0)
{
	glDisable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglEdgeFlagPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglEdgeFlagPointer(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLbyte *address = offset + (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glEdgeFlagPointer((GLint) p0, (const void *)address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglEdgeFlagPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglEdgeFlagPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint buffer_offset)
{
	glEdgeFlagPointer((GLint) p0, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEdgeFlag
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEdgeFlag(JNIEnv * env, jclass clazz, jboolean p0)
{
	glEdgeFlag((GLboolean) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDrawPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglDrawPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint offset)
{
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const void *)(address + offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglDrawElements
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglDrawElements(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, (const void *)(address + offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglDrawElementsVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglDrawElementsVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDrawBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDrawBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glDrawBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDrawArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDrawArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glDrawArrays((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDepthRange
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDepthRange(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glDepthRange((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDepthMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDepthMask(JNIEnv * env, jclass clazz, jboolean p0)
{
	glDepthMask((GLboolean) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDepthFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDepthFunc(JNIEnv * env, jclass clazz, jint p0)
{
	glDepthFunc((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFeedbackBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglFeedbackBuffer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glFeedbackBuffer((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapuiv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLushort *address = (GLushort *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapusv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMaterialfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMaterialiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMapfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMapiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetMapiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMapiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetLightfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetLightiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetError
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetError(JNIEnv * env, jclass clazz)
{
	jint ret = (jint) glGetError();
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLdouble *address = offset + (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetClipPlane((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetBooleanv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetBooleanv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLubyte *address = offset + (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetBooleanv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetDoublev
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetDoublev(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLdouble *address = offset + (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetDoublev((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetFloatv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetFloatv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetFloatv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetIntegerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetIntegerv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetIntegerv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGenTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGenTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenTextures((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGenLists
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL11_glGenLists(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glGenLists((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFrustum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFrustum(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glFrustum((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFrontFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFrontFace(JNIEnv * env, jclass clazz, jint p0)
{
	glFrontFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFogf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glFogf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFogi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glFogi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglFogfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glFogfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglFogiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glFogiv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFlush
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFlush(JNIEnv * env, jclass clazz)
{
	glFlush();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFinish
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFinish(JNIEnv * env, jclass clazz)
{
	glFinish();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPointerv
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetPointerv(JNIEnv * env, jclass clazz, jint p0, int size)
{
	void *pointer;
	glGetPointerv((GLint) p0, &pointer);
	CHECK_GL_ERROR
	return safeNewBuffer(env, pointer, size);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIsEnabled
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL11_glIsEnabled(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsEnabled((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglInterleavedArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglInterleavedArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glInterleavedArrays((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglInterleavedArraysVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglInterleavedArraysVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glInterleavedArrays((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glInitNames
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glInitNames(JNIEnv * env, jclass clazz)
{
	glInitNames();
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glHint
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glHint(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glHint((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexParameterfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexParameteriv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexLevelParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexLevelParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexLevelParameterfv((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexLevelParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexLevelParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexLevelParameteriv((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexImage
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexImage(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexGenfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexEnvfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexEnviv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetString(JNIEnv * env, jclass clazz, jint p0)
{
	const GLubyte * string = glGetString((GLenum) p0);
	return env->NewStringUTF((const char *) string);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglGetPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	GLubyte *address = offset + (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetPolygonStipple(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIsList
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL11_glIsList(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsList((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMaterialf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMaterialf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glMaterialf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMateriali
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMateriali(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glMateriali((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMaterialfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glMaterialiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMapGrid1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMapGrid1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	glMapGrid1f((GLint) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMapGrid2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMapGrid2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jfloat p4, jfloat p5)
{
	glMapGrid2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMap2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglMap2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jfloat p5, jfloat p6, jint p7, jint p8, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMap2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (GLfloat) p5, (GLfloat) p6, (GLint) p7, (GLint) p8, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMap1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglMap1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMap1f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLogicOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLogicOp(JNIEnv * env, jclass clazz, jint p0)
{
	glLogicOp((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLoadName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLoadName(JNIEnv * env, jclass clazz, jint p0)
{
	glLoadName((GLint) p0);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLoadMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglLoadMatrixf(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLoadIdentity
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLoadIdentity(JNIEnv * env, jclass clazz)
{
	glLoadIdentity();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glListBase
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glListBase(JNIEnv * env, jclass clazz, jint p0)
{
	glListBase((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLineWidth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLineWidth(JNIEnv * env, jclass clazz, jfloat p0)
{
	glLineWidth((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLineStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLineStipple(JNIEnv * env, jclass clazz, jint p0, jshort p1)
{
	glLineStipple((GLint) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModelf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightModelf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glLightModelf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModeli
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightModeli(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glLightModeli((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModelfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglLightModelfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLightModelfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModeliv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglLightModeliv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glLightModeliv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glLightf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLighti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLighti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glLighti((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLightfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glLightiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIsTexture
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL11_glIsTexture(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsTexture((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMatrixMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMatrixMode(JNIEnv * env, jclass clazz, jint p0)
{
	glMatrixMode((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLubyte *address = offset + (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glPolygonStipple(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPolygonOffset
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPolygonOffset(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPolygonOffset((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPolygonMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPolygonMode(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPolygonMode((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPointSize
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPointSize(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPointSize((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelZoom
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelZoom(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPixelZoom((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelTransferf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelTransferf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelTransferf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelTransferi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelTransferi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelTransferi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelStoref
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelStoref(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelStoref((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelStorei
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelStorei(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelStorei((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glPixelMapfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLuint *address = (const GLuint *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glPixelMapuiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLushort *address = (const GLushort *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glPixelMapusv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPassThrough
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPassThrough(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPassThrough((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glOrtho
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glOrtho(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glOrtho((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglNormalPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglNormalPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glNormalPointer((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglNormalPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglNormalPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glNormalPointer((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glNormal3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glNormal3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glNormal3i((GLint) p0, (GLint) p1, (GLint) p2);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNewList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNewList(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glNewList((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEndList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEndList(JNIEnv * env, jclass clazz)
{
	glEndList();
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMultMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglMultMatrixf(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMultMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glShadeModel
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glShadeModel(JNIEnv * env, jclass clazz, jint p0)
{
	glShadeModel((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glSelectBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglSelectBuffer(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)env->GetDirectBufferAddress(buffer);
	glSelectBuffer((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glScissor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glScissor(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glScissor((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glScalef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glScalef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glScalef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRotatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRotatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRotatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRenderMode
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL11_glRenderMode(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glRenderMode((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRectf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRectf(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRectf((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRecti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRecti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRecti((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glReadPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglReadPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glReadBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glReadBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glReadBuffer((GLint) p0);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glRasterPos2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glRasterPos2i((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glRasterPos3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glRasterPos3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRasterPos4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRasterPos4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPushName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPushName(JNIEnv * env, jclass clazz, jint p0)
{
	glPushName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPopName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPopName(JNIEnv * env, jclass clazz)
{
	glPopName();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPushMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPushMatrix(JNIEnv * env, jclass clazz)
{
	glPushMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPopMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPopMatrix(JNIEnv * env, jclass clazz)
{
	glPopMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglPushClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglPushClientAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushClientAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglPopClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglPopClientAttrib(JNIEnv * env, jclass clazz)
{
	glPopClientAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPushAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPushAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPopAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPopAttrib(JNIEnv * env, jclass clazz)
{
	glPopAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glStencilFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glStencilFunc(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilFunc((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglVertexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglVertexPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglVertexPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglVertexPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glVertex2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glVertex2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glVertex3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glVertex3i((GLint) p0, (GLint) p1, (GLint) p2);
}



/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertex4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glVertex4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTranslatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTranslatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTranslatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexParameterf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexParameterf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexParameterf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexParameteri
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexParameteri(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexParameteri((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGenf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGenf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexGenf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glTexGenfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGeni
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGeni(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexGeni((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexGeniv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glTexGeniv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnvf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexEnvf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexEnvf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnvi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexEnvi(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexEnvi((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glTexEnvfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glTexEnviv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglTexCoordPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexCoordPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    nglTexCoordPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_nglTexCoordPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glTexCoord1f((GLfloat) p0);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glTexCoord2f((GLfloat) p0, (GLfloat) p1);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTexCoord3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glTexCoord4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glStencilOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glStencilOp(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilOp((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glStencilMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glStencilMask(JNIEnv * env, jclass clazz, jint p0)
{
	glStencilMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glViewport
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glViewport(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glViewport((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

