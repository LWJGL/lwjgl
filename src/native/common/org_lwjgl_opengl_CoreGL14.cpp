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

#include "org_lwjgl_opengl_CoreGL14.h"
#include "checkGLerror.h"
#include "extgl.h"

static inline const void *offsetToPointer(jint offset) {
        return (const char *)NULL + offset;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glFogCoordf
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glFogCoordf
  (JNIEnv *env, jclass clazz, jfloat f) {
	CHECK_EXISTS(glFogCoordf)
	glFogCoordf(f);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    nglFogCoordPointer
 * Signature: (IILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_nglFogCoordPointer
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jobject buffer, jint offset) {
	CHECK_EXISTS(glFogCoordPointer)
	GLvoid *address = (GLvoid *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glFogCoordPointer(p1, p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    nglFogCoordPointerVBO
 * Signature: (IILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_nglFogCoordPointerVBO
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint buffer_offset) {
	CHECK_EXISTS(glFogCoordPointer)
	glFogCoordPointer(p1, p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glMultiDrawArrays
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_nglMultiDrawArrays
  (JNIEnv *env, jclass clazz, jint mode, jobject first, jint first_offset, jobject count, jint count_offset, jint primcount) {
	CHECK_EXISTS(glMultiDrawArrays)
	GLint *address = first_offset + (GLint *)env->GetDirectBufferAddress(first);
	GLsizei *address2 = count_offset + (GLsizei *)env->GetDirectBufferAddress(count);
	glMultiDrawArrays(mode, address, address2, (GLsizei)primcount);
	CHECK_GL_ERROR
}
  
/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glMultiDrawElements
 * Signature: (IIIII)V
 */
/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glMultiDrawElements
  (JNIEnv *env, jclass clazz, jint mode, jobject count, jint type, jobject indices, jint primcount) {
	CHECK_EXISTS(glMultiDrawElements)
	const GLsizei *address = (const GLsizei *)env->GetDirectBufferAddress(count);
	const void *address2 = (const void *)env->GetDirectBufferAddress(indices);
	glMultiDrawElements(mode, address, type, address2, primcount);
	CHECK_GL_ERROR
}
*/  

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glPointParameterf
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glPointParameterf
  (JNIEnv *env, jclass clazz, jint p1, jfloat p2) {
	CHECK_EXISTS(glPointParameterf)
	glPointParameterf(p1, p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glPointParameterfv
 * Signature: (ILjava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_nglPointParameterfv
  (JNIEnv *env, jclass clazz, jint p1, jobject buffer, jint offset) {
	CHECK_EXISTS(glPointParameterfv)
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glPointParameterfv(p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glSecondaryColor3b
 * Signature: (BBB)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glSecondaryColor3b
  (JNIEnv *env, jclass clazz, jbyte p1, jbyte p2, jbyte p3) {
	CHECK_EXISTS(glSecondaryColor3b)
	glSecondaryColor3b(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glSecondaryColor3f
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glSecondaryColor3f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2, jfloat p3) {
	CHECK_EXISTS(glSecondaryColor3f)
	glSecondaryColor3f(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glSecondaryColor3ub
 * Signature: (BBB)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glSecondaryColor3ub
  (JNIEnv *env, jclass clazz, jbyte p1, jbyte p2, jbyte p3) {
	CHECK_EXISTS(glSecondaryColor3ub)
	glSecondaryColor3ub(p1, p2, p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    nglSecondaryColorPointer
 * Signature: (IIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_nglSecondaryColorPointer
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jobject buffer, jint offset) {
	CHECK_EXISTS(glSecondaryColorPointer)
	const GLvoid *address = (const GLvoid *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glSecondaryColorPointer(p1, p2, p3, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    nglSecondaryColorPointerVBO
 * Signature: (IIILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_nglSecondaryColorPointerVBO
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jint buffer_offset) {
	CHECK_EXISTS(glSecondaryColorPointer)
	glSecondaryColorPointer(p1, p2, p3, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glBlendFuncSeparate
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glBlendFuncSeparate
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3, jint p4) {
	CHECK_EXISTS(glBlendFuncSeparate)
	glBlendFuncSeparate(p1, p2, p3, p4);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glWindowPos2f
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glWindowPos2f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2) {
        CHECK_EXISTS(glWindowPos2f);
        glWindowPos2f(p1, p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glWindowPos2i
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glWindowPos2i
  (JNIEnv *env, jclass clazz, jint p1, jint p2) {
        CHECK_EXISTS(glWindowPos2i)
        glWindowPos2i(p1, p2);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glWindowPos3f
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glWindowPos3f
  (JNIEnv *env, jclass clazz, jfloat p1, jfloat p2, jfloat p3) {
        CHECK_EXISTS(glWindowPos3f)
        glWindowPos3f(p1, p2, p3);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL14
 * Method:    glWindowPos3i
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL14_glWindowPos3i
  (JNIEnv *env, jclass clazz, jint p1, jint p2, jint p3) {
        CHECK_EXISTS(glWindowPos3i)
        glWindowPos3i(p1, p2, p3);
}
