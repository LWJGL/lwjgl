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
 * GL extensions library.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#ifdef _WIN32
#include <windows.h>
#endif

#include "org_lwjgl_opengl_GL.h"
#include "extgl.h"
#include "checkGLerror.h"

#ifdef _WIN32
extern HDC hdc;
#endif

static inline void * safeGetBufferAddress(JNIEnv *env, jobject buffer) {
	if (buffer == NULL)
		return NULL;
	else
		return env->GetDirectBufferAddress(buffer);
}

static inline jobject safeNewBuffer(JNIEnv *env, void *p, int size) {
	if (p == NULL)
		return NULL;
	else
		return env->NewDirectByteBuffer(p, size);
}

static inline const void *offsetToPointer(jint offset) {
        return (const char *)NULL + offset;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glActiveStencilFaceEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glActiveStencilFaceEXT(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glActiveStencilFaceEXT)
	glActiveStencilFaceEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glActiveTextureARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glActiveTextureARB(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glActiveTextureARB)
	glActiveTextureARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glAlphaFragmentOp1ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glAlphaFragmentOp1ATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	CHECK_EXISTS(glAlphaFragmentOp1ATI)
	glAlphaFragmentOp1ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glAlphaFragmentOp2ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glAlphaFragmentOp2ATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	CHECK_EXISTS(glAlphaFragmentOp2ATI)
	glAlphaFragmentOp2ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glAlphaFragmentOp3ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glAlphaFragmentOp3ATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9, jint p10, jint p11)
{
	CHECK_EXISTS(glAlphaFragmentOp3ATI)
	glAlphaFragmentOp3ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9, (GLint) p10, (GLint) p11);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglAreProgramsResidentNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_nglAreProgramsResidentNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset, jobject buffer2, jint buffer2_offset)
{
	CHECK_EXISTS(glAreProgramsResidentNV)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	GLboolean *address2 = (GLboolean *)env->GetDirectBufferAddress(buffer2);
	jboolean ret = (jboolean) glAreProgramsResidentNV((GLint) p0, address + buffer_offset, address2 + buffer2_offset);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glArrayObjectATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glArrayObjectATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	CHECK_EXISTS(glArrayObjectATI)
	glArrayObjectATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBeginFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glBeginFragmentShaderATI(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glBeginFragmentShaderATI)
	glBeginFragmentShaderATI();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBeginOcclusionQueryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glBeginOcclusionQueryNV(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glBeginOcclusionQueryNV)
	glBeginOcclusionQueryNV((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBeginVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glBeginVertexShaderEXT(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glBeginVertexShaderEXT)
	glBeginVertexShaderEXT();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glBindFragmentShaderATI(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glBindFragmentShaderATI)
	glBindFragmentShaderATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindLightParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glBindLightParameterEXT(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glBindLightParameterEXT)
	jint ret = (jint) glBindLightParameterEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindMaterialParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glBindMaterialParameterEXT(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glBindMaterialParameterEXT)
	jint ret = (jint) glBindMaterialParameterEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glBindParameterEXT(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glBindParameterEXT)
	jint ret = (jint) glBindParameterEXT((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindProgramARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glBindProgramARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glBindProgramARB)
	glBindProgramARB((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glBindProgramNV(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glBindProgramNV)
	glBindProgramNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindTexGenParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glBindTexGenParameterEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glBindTexGenParameterEXT)
	jint ret = (jint) glBindTexGenParameterEXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindTextureUnitParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glBindTextureUnitParameterEXT(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glBindTextureUnitParameterEXT)
	jint ret = (jint) glBindTextureUnitParameterEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBindVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glBindVertexShaderEXT(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glBindVertexShaderEXT)
	glBindVertexShaderEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glClientActiveTextureARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glClientActiveTextureARB(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glClientActiveTextureARB)
	glClientActiveTextureARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glClientActiveVertexStreamATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glClientActiveVertexStreamATI(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glClientActiveVertexStreamATI)
	glClientActiveVertexStreamATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glColorFragmentOp1ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glColorFragmentOp1ATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	CHECK_EXISTS(glColorFragmentOp1ATI)
	glColorFragmentOp1ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glColorFragmentOp2ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glColorFragmentOp2ATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9)
{
	CHECK_EXISTS(glColorFragmentOp2ATI)
	glColorFragmentOp2ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glColorFragmentOp3ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glColorFragmentOp3ATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9, jint p10, jint p11, jint p12)
{
	CHECK_EXISTS(glColorFragmentOp3ATI)
	glColorFragmentOp3ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9, (GLint) p10, (GLint) p11, (GLint) p12);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glCombinerInputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glCombinerInputNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	CHECK_EXISTS(glCombinerInputNV)
	glCombinerInputNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glCombinerOutputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glCombinerOutputNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jboolean p7, jboolean p8, jboolean p9)
{
	CHECK_EXISTS(glCombinerOutputNV)
	glCombinerOutputNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLboolean) p7, (GLboolean) p8, (GLboolean) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glCombinerParameterfNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glCombinerParameterfNV(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glCombinerParameterfNV)
	glCombinerParameterfNV((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCombinerParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCombinerParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCombinerParameterfvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glCombinerParameterfvNV((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glCombinerParameteriNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glCombinerParameteriNV(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glCombinerParameteriNV)
	glCombinerParameteriNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCombinerParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCombinerParameterivNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCombinerParameterivNV)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glCombinerParameterivNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCombinerStageParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCombinerStageParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCombinerStageParameterfvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glCombinerStageParameterfvNV((GLint) p0, (GLint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCompressedTexImage1DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCompressedTexImage1DARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCompressedTexImage1DARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage1DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCompressedTexImage2DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCompressedTexImage2DARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCompressedTexImage2DARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage2DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCompressedTexImage3DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCompressedTexImage3DARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCompressedTexImage3DARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glCompressedTexImage3DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCompressedTexSubImage1DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCompressedTexSubImage1DARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCompressedTexSubImage1DARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage1DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCompressedTexSubImage2DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCompressedTexSubImage2DARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCompressedTexSubImage2DARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage2DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglCompressedTexSubImage3DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglCompressedTexSubImage3DARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glCompressedTexSubImage3DARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glCompressedTexSubImage3DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glCurrentPaletteMatrixARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glCurrentPaletteMatrixARB(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glCurrentPaletteMatrixARB)
	glCurrentPaletteMatrixARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglDeleteFencesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglDeleteFencesNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glDeleteFencesNV)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glDeleteFencesNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glDeleteFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glDeleteFragmentShaderATI(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glDeleteFragmentShaderATI)
	glDeleteFragmentShaderATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglDeleteOcclusionQueriesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglDeleteOcclusionQueriesNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glDeleteOcclusionQueriesNV)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glDeleteOcclusionQueriesNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglDeleteProgramsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglDeleteProgramsARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glDeleteProgramsARB)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glDeleteProgramsARB((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglDeleteProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglDeleteProgramsNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glDeleteProgramsNV)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glDeleteProgramsNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glDeleteVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glDeleteVertexShaderEXT(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glDeleteVertexShaderEXT)
	glDeleteVertexShaderEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glDisableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glDisableVariantClientStateEXT(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glDisableVariantClientStateEXT)
	glDisableVariantClientStateEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glDisableVertexAttribArrayARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glDisableVertexAttribArrayARB(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glDisableVertexAttribArrayARB)
	glDisableVertexAttribArrayARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glDrawElementArrayATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glDrawElementArrayATI(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glDrawElementArrayATI)
	glDrawElementArrayATI((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glDrawRangeElementArrayATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glDrawRangeElementArrayATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glDrawRangeElementArrayATI)
	glDrawRangeElementArrayATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglDrawRangeElementsEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglDrawRangeElementsEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glDrawRangeElementsEXT)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glDrawRangeElementsEXT((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint) p3, (GLuint) p4, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglElementPointerATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglElementPointerATI(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glElementPointerATI)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glElementPointerATI((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glEnableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glEnableVariantClientStateEXT(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glEnableVariantClientStateEXT)
	glEnableVariantClientStateEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glEnableVertexAttribArrayARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glEnableVertexAttribArrayARB(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glEnableVertexAttribArrayARB)
	glEnableVertexAttribArrayARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glEndFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glEndFragmentShaderATI(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glEndFragmentShaderATI)
	glEndFragmentShaderATI();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glEndOcclusionQueryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glEndOcclusionQueryNV(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glEndOcclusionQueryNV)
	glEndOcclusionQueryNV();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glEndVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glEndVertexShaderEXT(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glEndVertexShaderEXT)
	glEndVertexShaderEXT();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glEvalMapsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glEvalMapsNV(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glEvalMapsNV)
	glEvalMapsNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglExecuteProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglExecuteProgramNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glExecuteProgramNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glExecuteProgramNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glExtractComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glExtractComponentEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glExtractComponentEXT)
	glExtractComponentEXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glFinalCombinerInputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glFinalCombinerInputNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glFinalCombinerInputNV)
	glFinalCombinerInputNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glFinishFenceNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glFinishFenceNV(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glFinishFenceNV)
	glFinishFenceNV((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glFlushVertexArrayRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glFlushVertexArrayRangeNV(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glFlushVertexArrayRangeNV)
	glFlushVertexArrayRangeNV();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glFogCoordfEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glFogCoordfEXT(JNIEnv * env, jclass clazz, jfloat p0)
{
	CHECK_EXISTS(glFogCoordfEXT)
	glFogCoordfEXT((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglFogCoordPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglFogCoordPointerEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glFogCoordPointerEXT)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glFogCoordPointerEXT((GLuint) p0, (GLint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glFreeObjectBufferATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glFreeObjectBufferATI(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glFreeObjectBufferATI)
	glFreeObjectBufferATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGenFencesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGenFencesNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGenFencesNV)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenFencesNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGenFragmentShadersATI
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glGenFragmentShadersATI(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glGenFragmentShadersATI)
	jint ret = (jint) glGenFragmentShadersATI((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGenOcclusionQueriesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGenOcclusionQueriesNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGenOcclusionQueriesNV)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenOcclusionQueriesNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGenProgramsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGenProgramsARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGenProgramsARB)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenProgramsARB((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGenProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGenProgramsNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGenProgramsNV)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenProgramsNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGenSymbolsEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glGenSymbolsEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glGenSymbolsEXT)
	jint ret = (jint) glGenSymbolsEXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGenVertexShadersEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_glGenVertexShadersEXT(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glGenVertexShadersEXT)
	jint ret = (jint) glGenVertexShadersEXT((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetArrayObjectfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetArrayObjectfvATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetArrayObjectfvATI)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetArrayObjectfvATI((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetArrayObjectivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetArrayObjectivATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetArrayObjectivATI)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetArrayObjectivATI((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetCombinerInputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetCombinerInputParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetCombinerInputParameterfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetCombinerInputParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLuint) p3, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetCombinerInputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetCombinerInputParameterivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetCombinerInputParameterivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetCombinerInputParameterivNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLuint) p3, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetCombinerOutputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetCombinerOutputParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetCombinerOutputParameterfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetCombinerOutputParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetCombinerOutputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetCombinerOutputParameterivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetCombinerOutputParameterivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetCombinerOutputParameterivNV((GLuint) p0, (GLuint) p1, (GLuint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetCombinerStageParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetCombinerStageParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetCombinerStageParameterfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetCombinerStageParameterfvNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetCompressedTexImageARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetCompressedTexImageARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetCompressedTexImageARB)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetCompressedTexImageARB((GLuint) p0, (GLint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetFenceivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetFenceivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetFenceivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetFenceivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetFinalCombinerInputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetFinalCombinerInputParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetFinalCombinerInputParameterfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetFinalCombinerInputParameterfvNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetFinalCombinerInputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetFinalCombinerInputParameterivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetFinalCombinerInputParameterivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetFinalCombinerInputParameterivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetInvariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetInvariantBooleanvEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetInvariantBooleanvEXT)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetInvariantBooleanvEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetInvariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetInvariantFloatvEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetInvariantFloatvEXT)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetInvariantFloatvEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetInvariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetInvariantIntegervEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetInvariantIntegervEXT)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetInvariantIntegervEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetLocalConstantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetLocalConstantBooleanvEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetLocalConstantBooleanvEXT)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetLocalConstantBooleanvEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetLocalConstantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetLocalConstantFloatvEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetLocalConstantFloatvEXT)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetLocalConstantFloatvEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetLocalConstantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetLocalConstantIntegervEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetLocalConstantIntegervEXT)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetLocalConstantIntegervEXT((GLint) p0, (GLint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetMapAttribParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetMapAttribParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetMapAttribParameterfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMapAttribParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetMapAttribParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetMapAttribParameterivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetMapAttribParameterivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMapAttribParameterivNV((GLint) p0, (GLint) p1, (GLint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetMapControlPointsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetMapControlPointsNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jboolean p5, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetMapControlPointsNV)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetMapControlPointsNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint) p3, (GLint) p4, (GLboolean) p5, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetMapParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetMapParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetMapParameterfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMapParameterfvNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetMapParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetMapParameterivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetMapParameterivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMapParameterivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetObjectBufferfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetObjectBufferfvATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetObjectBufferfvATI)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetObjectBufferfvATI((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetObjectBufferivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetObjectBufferivATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetObjectBufferivATI)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetObjectBufferivATI((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetOcclusionQueryivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetOcclusionQueryivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetOcclusionQueryivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetOcclusionQueryivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetOcclusionQueryuivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetOcclusionQueryuivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetOcclusionQueryuivNV)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGetOcclusionQueryuivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetProgramEnvParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetProgramEnvParameterfvARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetProgramEnvParameterfvARB)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetProgramEnvParameterfvARB((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetProgramivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetProgramivARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetProgramivARB)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetProgramivARB((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetProgramivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetProgramivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetProgramivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetProgramivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetProgramLocalParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetProgramLocalParameterfvARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetProgramLocalParameterfvARB)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetProgramLocalParameterfvARB((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetProgramParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetProgramParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetProgramParameterfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetProgramParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetProgramStringARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetProgramStringARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetProgramStringARB)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetProgramStringARB((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetProgramStringNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetProgramStringNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetProgramStringNV)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetProgramStringNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetTexBumpParameterfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetTexBumpParameterfvATI(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetTexBumpParameterfvATI)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexBumpParameterfvATI((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetTexBumpParameterivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetTexBumpParameterivATI(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetTexBumpParameterivATI)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexBumpParameterivATI((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetTrackMatrixivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetTrackMatrixivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetTrackMatrixivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTrackMatrixivNV((GLuint) p0, (GLuint) p1, (GLuint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVariantArrayObjectfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVariantArrayObjectfvATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVariantArrayObjectfvATI)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetVariantArrayObjectfvATI((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVariantArrayObjectivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVariantArrayObjectivATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVariantArrayObjectivATI)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetVariantArrayObjectivATI((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVariantBooleanvEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVariantBooleanvEXT)
	GLboolean *address = (GLboolean *)env->GetDirectBufferAddress(buffer);
	glGetVariantBooleanvEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVariantFloatvEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVariantFloatvEXT)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetVariantFloatvEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVariantIntegervEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVariantIntegervEXT)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetVariantIntegervEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGetVariantPointerEXT
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL_glGetVariantPointerEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint size)
{
	CHECK_EXISTS(glGetVariantPointervEXT)
	void *address;
	glGetVariantPointervEXT((GLuint) p0, (GLuint) p1, &address);
	CHECK_GL_ERROR
	return safeNewBuffer(env, address, size);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVertexAttribfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVertexAttribfvARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVertexAttribfvARB)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetVertexAttribfvARB((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVertexAttribfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVertexAttribfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVertexAttribfvNV)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetVertexAttribfvNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVertexAttribivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVertexAttribivARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVertexAttribivARB)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetVertexAttribivARB((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglGetVertexAttribivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetVertexAttribivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetVertexAttribivNV)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetVertexAttribivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGetVertexAttribPointerARB
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL_glGetVertexAttribPointerARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint size)
{
	CHECK_EXISTS(glGetVertexAttribPointervARB)
	void *address;
	glGetVertexAttribPointervARB((GLuint) p0, (GLuint) p1, &address);
	CHECK_GL_ERROR
	return safeNewBuffer(env, address, size);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGetVertexAttribPointerNV
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL_glGetVertexAttribPointerNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint size)
{
	CHECK_EXISTS(glGetVertexAttribPointervNV)
	void *address;
	glGetVertexAttribPointervNV((GLuint) p0, (GLuint) p1, &address);
	CHECK_GL_ERROR
	return safeNewBuffer(env, address, size);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glInsertComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glInsertComponentEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glInsertComponentEXT)
	glInsertComponentEXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glIsFenceNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glIsFenceNV(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glIsFenceNV)
	jboolean ret = (jboolean) glIsFenceNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glIsObjectBufferATI
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glIsObjectBufferATI(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glIsObjectBufferATI)
	jboolean ret = (jboolean) glIsObjectBufferATI((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glIsOcclusionQueryNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glIsOcclusionQueryNV(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glIsOcclusionQueryNV)
	jboolean ret = (jboolean) glIsOcclusionQueryNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glIsProgramARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glIsProgramARB(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glIsProgramARB)
	jboolean ret = (jboolean) glIsProgramARB((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glIsProgramNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glIsProgramNV(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glIsProgramNV)
	jboolean ret = (jboolean) glIsProgramNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glIsVariantEnabledEXT
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glIsVariantEnabledEXT(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glIsVariantEnabledEXT)
	jboolean ret = (jboolean) glIsVariantEnabledEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglLoadProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglLoadProgramNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glLoadProgramNV)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glLoadProgramNV((GLuint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglLoadTransposeMatrixfARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglLoadTransposeMatrixfARB(JNIEnv * env, jclass clazz, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glLoadTransposeMatrixfARB)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadTransposeMatrixfARB(address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glLockArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glLockArraysEXT(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glLockArraysEXT)
	glLockArraysEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMapControlPointsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMapControlPointsNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jboolean p7, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMapControlPointsNV)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glMapControlPointsNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLboolean) p7, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMapParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMapParameterfvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMapParameterfvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMapParameterfvNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMapParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMapParameterivNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMapParameterivNV)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glMapParameterivNV((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMatrixIndexPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMatrixIndexPointerARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMatrixIndexPointerARB)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glMatrixIndexPointerARB((GLint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMatrixIndexubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMatrixIndexubvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMatrixIndexubvARB)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glMatrixIndexubvARB((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMatrixIndexuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMatrixIndexuivARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMatrixIndexuivARB)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glMatrixIndexuivARB((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMatrixIndexusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMatrixIndexusvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMatrixIndexusvARB)
	GLushort *address = (GLushort *)env->GetDirectBufferAddress(buffer);
	glMatrixIndexusvARB((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMultiDrawArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMultiDrawArraysEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset, jobject buffer2, jint buffer2_offset, jint p3)
{
	CHECK_EXISTS(glMultiDrawArraysEXT)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	GLsizei *address2 = (GLsizei *)env->GetDirectBufferAddress(buffer2);
	glMultiDrawArraysEXT((GLuint) p0, address + buffer_offset, address2 + buffer_offset, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiDrawElementsEXT
 */
/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiDrawElementsEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint p2, jobject buffer2, jint p4)
{
	CHECK_EXISTS(glMultiDrawElementsEXT)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	const GLuint *address2 = (const GLuint *)env->GetDirectBufferAddress(buffer2);
	glMultiDrawElementsEXT((GLuint) p0, (GLint *) p1, (GLuint) p2, (const void **) p3, (GLint) p4);
	CHECK_GL_ERROR
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord1fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glMultiTexCoord1fARB)
	glMultiTexCoord1fARB((GLuint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord1iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord1iARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glMultiTexCoord1iARB)
	glMultiTexCoord1iARB((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord1sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord1sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1)
{
	CHECK_EXISTS(glMultiTexCoord1sARB)
	glMultiTexCoord1sARB((GLint) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord2fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	CHECK_EXISTS(glMultiTexCoord2fARB)
	glMultiTexCoord2fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord2iARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glMultiTexCoord2iARB)
	glMultiTexCoord2iARB((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord2sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2)
{
	CHECK_EXISTS(glMultiTexCoord2sARB)
	glMultiTexCoord2sARB((GLint) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord3fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	CHECK_EXISTS(glMultiTexCoord3fARB)
	glMultiTexCoord3fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord3iARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glMultiTexCoord3iARB)
	glMultiTexCoord3iARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord3sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3)
{
	CHECK_EXISTS(glMultiTexCoord3sARB)
	glMultiTexCoord3sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord4fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	CHECK_EXISTS(glMultiTexCoord4fARB)
	glMultiTexCoord4fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord4iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord4iARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	CHECK_EXISTS(glMultiTexCoord4iARB)
	glMultiTexCoord4iARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMultiTexCoord4sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glMultiTexCoord4sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	CHECK_EXISTS(glMultiTexCoord4sARB)
	glMultiTexCoord4sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglMultTransposeMatrixfARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglMultTransposeMatrixfARB(JNIEnv * env, jclass clazz, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glMultTransposeMatrixfARB)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMultTransposeMatrixfARB(address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglNewObjectBufferATI
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_nglNewObjectBufferATI(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset, jint p2)
{
	CHECK_EXISTS(glNewObjectBufferATI)
	const GLubyte *address = (const GLubyte *)safeGetBufferAddress(env, buffer);
	jint ret = (jint) glNewObjectBufferATI((GLint) p0, address + buffer_offset, (GLuint) p2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glNormalStream3bATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glNormalStream3bATI(JNIEnv * env, jclass clazz, jint p0, jbyte p1, jbyte p2, jbyte p3)
{
	CHECK_EXISTS(glNormalStream3bATI)
	glNormalStream3bATI((GLint) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glNormalStream3fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glNormalStream3fATI(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	CHECK_EXISTS(glNormalStream3fATI)
	glNormalStream3fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glNormalStream3iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glNormalStream3iATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glNormalStream3iATI)
	glNormalStream3iATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glNormalStream3sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glNormalStream3sATI(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3)
{
	CHECK_EXISTS(glNormalStream3sATI)
	glNormalStream3sATI((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glPassTexCoordATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glPassTexCoordATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glPassTexCoordATI)
	glPassTexCoordATI((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glPNTrianglesfATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glPNTrianglesfATI(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glPNTrianglesfATI)
	glPNTrianglesfATI((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glPNTrianglesiATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glPNTrianglesiATI(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glPNTrianglesiATI)
	glPNTrianglesiATI((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glStencilOpSeparateATI
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glStencilOpSeparateATI
  (JNIEnv *env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glStencilOpSeparateATI)
	glStencilOpSeparateATI(p0, p1, p2, p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glStencilFuncSeparateATI
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glStencilFuncSeparateATI
  (JNIEnv *env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glStencilFuncSeparateATI)
	glStencilFuncSeparateATI(p0, p1, p2, p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glPointParameterfARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glPointParameterfARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glPointParameterfARB)
	glPointParameterfARB((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glPointParameterfEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glPointParameterfEXT(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glPointParameterfEXT)
	glPointParameterfEXT((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglPointParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglPointParameterfvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glPointParameterfvARB)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glPointParameterfvARB((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglPointParameterfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglPointParameterfvEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glPointParameterfvEXT)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glPointParameterfvEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glPointParameteriNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glPointParameteriNV(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glPointParameteriNV)
	glPointParameteriNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglPointParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglPointParameterivNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glPointParameterivNV)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glPointParameterivNV((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glProgramEnvParameter4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glProgramEnvParameter4fARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5)
{
	CHECK_EXISTS(glProgramEnvParameter4fARB)
	glProgramEnvParameter4fARB((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glProgramLocalParameter4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glProgramLocalParameter4fARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5)
{
	CHECK_EXISTS(glProgramLocalParameter4fARB)
	glProgramLocalParameter4fARB((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glProgramParameter4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glProgramParameter4fNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5)
{
	CHECK_EXISTS(glProgramParameter4fNV)
	glProgramParameter4fNV((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglProgramParameters4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglProgramParameters4fvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glProgramParameters4fvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glProgramParameters4fvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglProgramStringARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglProgramStringARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glProgramStringARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glProgramStringARB((GLuint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglRequestResidentProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglRequestResidentProgramsNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glRequestResidentProgramsNV)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glRequestResidentProgramsNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glSampleCoverageARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glSampleCoverageARB(JNIEnv * env, jclass clazz, jfloat p0, jboolean p1)
{
	CHECK_EXISTS(glSampleCoverageARB)
	glSampleCoverageARB((GLfloat) p0, (GLboolean) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glSampleMapATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glSampleMapATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glSampleMapATI)
	glSampleMapATI((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glSecondaryColor3bEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glSecondaryColor3bEXT(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	CHECK_EXISTS(glSecondaryColor3bEXT)
	glSecondaryColor3bEXT((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glSecondaryColor3fEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glSecondaryColor3fEXT(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	CHECK_EXISTS(glSecondaryColor3fEXT)
	glSecondaryColor3fEXT((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glSecondaryColor3ubEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glSecondaryColor3ubEXT(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	CHECK_EXISTS(glSecondaryColor3ubEXT)
	glSecondaryColor3ubEXT((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglSecondaryColorPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglSecondaryColorPointerEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glSecondaryColorPointerEXT)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glSecondaryColorPointerEXT((GLint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glSetFenceNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glSetFenceNV(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glSetFenceNV)
	glSetFenceNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglSetFragmentShaderConstantATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglSetFragmentShaderConstantATI(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glSetFragmentShaderConstantATI)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glSetFragmentShaderConstantATI((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglSetInvariantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglSetInvariantEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glSetInvariantEXT)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glSetInvariantEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglSetLocalConstantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglSetLocalConstantEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glSetLocalConstantEXT)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glSetLocalConstantEXT((GLuint) p0, (GLuint) p1, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glShaderOp1EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glShaderOp1EXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glShaderOp1EXT)
	glShaderOp1EXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glShaderOp2EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glShaderOp2EXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glShaderOp2EXT)
	glShaderOp2EXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glShaderOp3EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glShaderOp3EXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	CHECK_EXISTS(glShaderOp3EXT)
	glShaderOp3EXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glSwizzleEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glSwizzleEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	CHECK_EXISTS(glSwizzleEXT)
	glSwizzleEXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glTestFenceNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glTestFenceNV(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glTestFenceNV)
	jboolean ret = (jboolean) glTestFenceNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglTexBumpParameterfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglTexBumpParameterfvATI(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glTexBumpParameterfvATI)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glTexBumpParameterfvATI((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglTexBumpParameterivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglTexBumpParameterivATI(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glTexBumpParameterivATI)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glTexBumpParameterivATI((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glTrackMatrixNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glTrackMatrixNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glTrackMatrixNV)
	glTrackMatrixNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glUnlockArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glUnlockArraysEXT(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glUnlockArraysEXT)
	glUnlockArraysEXT();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglUpdateObjectBufferATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglUpdateObjectBufferATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset, jint p4)
{
	CHECK_EXISTS(glUpdateObjectBufferATI)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glUpdateObjectBufferATI((GLuint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset, (GLuint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVariantArrayObjectATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVariantArrayObjectATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	CHECK_EXISTS(glVariantArrayObjectATI)
	glVariantArrayObjectATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantbvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantbvEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantbvEXT)
	GLbyte *address = (GLbyte *)env->GetDirectBufferAddress(buffer);
	glVariantbvEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantfvEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantfvEXT)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glVariantfvEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantivEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantivEXT)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glVariantivEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantPointerEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantPointerEXT)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glVariantPointerEXT((GLuint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantsvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantsvEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantsvEXT)
	GLshort *address = (GLshort *)env->GetDirectBufferAddress(buffer);
	glVariantsvEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantubvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantubvEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantubvEXT)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glVariantubvEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantuivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantuivEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantuivEXT)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glVariantuivEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVariantusvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVariantusvEXT(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVariantusvEXT)
	GLushort *address = (GLushort *)env->GetDirectBufferAddress(buffer);
	glVariantusvEXT((GLuint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexArrayRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexArrayRangeNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexArrayRangeNV)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexArrayRangeNV((GLint) p0, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib1fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glVertexAttrib1fARB)
	glVertexAttrib1fARB((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib1fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib1fNV(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glVertexAttrib1fNV)
	glVertexAttrib1fNV((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib1sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib1sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1)
{
	CHECK_EXISTS(glVertexAttrib1sARB)
	glVertexAttrib1sARB((GLint) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib1sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib1sNV(JNIEnv * env, jclass clazz, jint p0, jshort p1)
{
	CHECK_EXISTS(glVertexAttrib1sNV)
	glVertexAttrib1sNV((GLint) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib2fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	CHECK_EXISTS(glVertexAttrib2fARB)
	glVertexAttrib2fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib2fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib2fNV(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	CHECK_EXISTS(glVertexAttrib2fNV)
	glVertexAttrib2fNV((GLint) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib2sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2)
{
	CHECK_EXISTS(glVertexAttrib2sARB)
	glVertexAttrib2sARB((GLint) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib2sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib2sNV(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2)
{
	CHECK_EXISTS(glVertexAttrib2sNV)
	glVertexAttrib2sNV((GLint) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib3fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	CHECK_EXISTS(glVertexAttrib3fARB)
	glVertexAttrib3fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib3fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib3fNV(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	CHECK_EXISTS(glVertexAttrib3fNV)
	glVertexAttrib3fNV((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib3sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3)
{
	CHECK_EXISTS(glVertexAttrib3sARB)
	glVertexAttrib3sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib3sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib3sNV(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3)
{
	CHECK_EXISTS(glVertexAttrib3sNV)
	glVertexAttrib3sNV((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4bvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4bvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4bvARB)
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4bvARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib4fARB(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	CHECK_EXISTS(glVertexAttrib4fARB)
	glVertexAttrib4fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib4fNV(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	CHECK_EXISTS(glVertexAttrib4fNV)
	glVertexAttrib4fNV((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4ivARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4ivARB)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4ivARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4NbvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4NbvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4NbvARB)
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4NbvARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4NivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4NivARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4NivARB)
	const GLint *address = (const GLint *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4NivARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4NsvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4NsvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4NsvARB)
	const GLshort *address = (const GLshort *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4NsvARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib4NubARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib4NubARB(JNIEnv * env, jclass clazz, jint p0, jbyte p1, jbyte p2, jbyte p3, jbyte p4)
{
	CHECK_EXISTS(glVertexAttrib4NubARB)
	glVertexAttrib4NubARB((GLint) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3, (GLbyte) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4NubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4NubvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4NubvARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4NubvARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4NuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4NuivARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4NuivARB)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4NuivARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4NusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4NusvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4NusvARB)
	const GLushort *address = (const GLushort *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4NusvARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib4sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib4sARB(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	CHECK_EXISTS(glVertexAttrib4sARB)
	glVertexAttrib4sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib4sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib4sNV(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	CHECK_EXISTS(glVertexAttrib4sNV)
	glVertexAttrib4sNV((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexAttrib4ubNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexAttrib4ubNV(JNIEnv * env, jclass clazz, jint p0, jbyte p1, jbyte p2, jbyte p3, jbyte p4)
{
	CHECK_EXISTS(glVertexAttrib4ubNV)
	glVertexAttrib4ubNV((GLint) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3, (GLbyte) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4ubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4ubvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4ubvARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4ubvARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4ubvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4ubvNV(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4ubvNV)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4ubvNV((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4uivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4uivARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4uivARB)
	const GLuint *address = (const GLuint *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4uivARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttrib4usvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttrib4usvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttrib4usvARB)
	const GLushort *address = (const GLushort *)env->GetDirectBufferAddress(buffer);
	glVertexAttrib4usvARB((GLuint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribPointerARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jboolean p3, jint p4, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribPointerARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttribPointerARB((GLuint) p0, (GLint) p1, (GLuint) p2, (GLboolean) p3, (GLint) p4, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribPointerARBVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribPointerARBVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jboolean p3, jint p4, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribPointerARB)
	glVertexAttribPointerARB((GLuint) p0, (GLint) p1, (GLuint) p2, (GLboolean) p3, (GLint) p4, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribPointerNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribPointerNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribPointerNV)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttribPointerNV((GLuint) p0, (GLint) p1, (GLuint) p2, (GLint) p3, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs1fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs1fvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs1fvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs1fvNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs1svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs1svNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs1svNV)
	const GLshort *address = (const GLshort *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs1svNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs2fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs2fvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs2fvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs2fvNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs2svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs2svNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs2svNV)
	const GLshort *address = (const GLshort *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs2svNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs3fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs3fvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs3fvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs3fvNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs3svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs3svNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs3svNV)
	const GLshort *address = (const GLshort *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs3svNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs4fvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs4fvNV)
	const GLfloat *address = (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs4fvNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs4svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs4svNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs4svNV)
	const GLshort *address = (const GLshort *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs4svNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexAttribs4ubvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexAttribs4ubvNV(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexAttribs4ubvNV)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexAttribs4ubvNV((GLuint) p0, (GLint) p1, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexBlendARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexBlendARB(JNIEnv * env, jclass clazz, jint p0)
{
	CHECK_EXISTS(glVertexBlendARB)
	glVertexBlendARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexBlendEnvfATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexBlendEnvfATI(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	CHECK_EXISTS(glVertexBlendEnvfATI)
	glVertexBlendEnvfATI((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexBlendEnviATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexBlendEnviATI(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glVertexBlendEnviATI)
	glVertexBlendEnviATI((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream2fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream2fATI(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	CHECK_EXISTS(glVertexStream2fATI)
	glVertexStream2fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream2iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream2iATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glVertexStream2iATI)
	glVertexStream2iATI((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream2sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream2sATI(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2)
{
	CHECK_EXISTS(glVertexStream2sATI)
	glVertexStream2sATI((GLint) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream3fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream3fATI(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	CHECK_EXISTS(glVertexStream3fATI)
	glVertexStream3fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream3iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream3iATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	CHECK_EXISTS(glVertexStream3iATI)
	glVertexStream3iATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream3sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream3sATI(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3)
{
	CHECK_EXISTS(glVertexStream3sATI)
	glVertexStream3sATI((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream4fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream4fATI(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	CHECK_EXISTS(glVertexStream4fATI)
	glVertexStream4fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream4iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream4iATI(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	CHECK_EXISTS(glVertexStream4iATI)
	glVertexStream4iATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexStream4sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexStream4sATI(JNIEnv * env, jclass clazz, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	CHECK_EXISTS(glVertexStream4sATI)
	glVertexStream4sATI((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glVertexWeightfEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glVertexWeightfEXT(JNIEnv * env, jclass clazz, jfloat p0)
{
	CHECK_EXISTS(glVertexWeightfEXT)
	glVertexWeightfEXT((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglVertexWeightPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglVertexWeightPointerEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glVertexWeightPointerEXT)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glVertexWeightPointerEXT((GLint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightbvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightbvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightbvARB)
	GLbyte *address = (GLbyte *)env->GetDirectBufferAddress(buffer);
	glWeightbvARB((GLint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightfvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightfvARB)
	GLfloat *address = (GLfloat *)env->GetDirectBufferAddress(buffer);
	glWeightfvARB((GLint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightivARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightivARB)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glWeightivARB((GLint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightPointerARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightPointerARB)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glWeightPointerARB((GLint) p0, (GLuint) p1, (GLint) p2, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightPointerARBVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightPointerARBVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	CHECK_EXISTS(glWeightPointerARB)
	glWeightPointerARB((GLint) p0, (GLuint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightsvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightsvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightsvARB)
	GLshort *address = (GLshort *)env->GetDirectBufferAddress(buffer);
	glWeightsvARB((GLint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightubvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightubvARB)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glWeightubvARB((GLint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightuivARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightuivARB)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glWeightuivARB((GLint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglWeightusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglWeightusvARB(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glWeightusvARB)
	GLushort *address = (GLushort *)env->GetDirectBufferAddress(buffer);
	glWeightusvARB((GLint) p0, address + buffer_offset);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glXAllocateMemoryNV
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL_glXAllocateMemoryNV(JNIEnv * env, jclass clazz, jint size, jfloat p1, jfloat p2, jfloat p3)
{
#ifdef _X11
	CHECK_EXISTS(glXAllocateMemoryNV)
	void *mem_address = glXAllocateMemoryNV((GLint) size, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	return safeNewBuffer(env, mem_address, size);
#else
	CHECK_EXISTS(NULL)
	return 0;
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglFreeMemoryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glXFreeMemoryNV(JNIEnv * env, jclass clazz, jobject buffer)
{
#ifdef _X11
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	glXFreeMemoryNV(address);
#else
	CHECK_EXISTS(NULL)
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglAllocateMemoryNV
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL_wglAllocateMemoryNV(JNIEnv * env, jclass clazz, jint size, jfloat p1, jfloat p2, jfloat p3)
{
#ifdef _WIN32
	CHECK_EXISTS(wglAllocateMemoryNV)
	void *mem_address = wglAllocateMemoryNV((GLint) size, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	return safeNewBuffer(env, mem_address, size);
#else
	CHECK_EXISTS(NULL)
	return 0;
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglBindTexImageARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglBindTexImageARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
#ifdef _WIN32
	CHECK_EXISTS(wglBindTexImageARB)
	jboolean ret = (jboolean) wglBindTexImageARB((HPBUFFERARB) p0, (GLint) p1);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglChoosePixelFormatARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglChoosePixelFormatARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
#ifdef _WIN32
	CHECK_EXISTS(wglChoosePixelFormatARB)
	jboolean ret = (jboolean) wglChoosePixelFormatARB((HDC) p0, (const GLint *) p1, (const GLfloat *) p2, (GLuint) p3, (GLint *) p4, (GLuint *) p5);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglCreateBufferRegionARB
 */
/*JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglCreateBufferRegionARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
#ifdef _WIN32
	CHECK_EXISTS(wglCreateBufferRegionARB)
	jint ret = (jint) wglCreateBufferRegionARB((HDC) p0, (GLint) p1, (GLint) p2);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return 0;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglCreatePbufferARB
 */
/*JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglCreatePbufferARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
#ifdef _WIN32
	CHECK_EXISTS(wglCreatePbufferARB)
	jint ret = (jint) wglCreatePbufferARB((HDC) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const GLint *) p4);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return 0;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglDeleteBufferRegionARB
 */
/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_wglDeleteBufferRegionARB(JNIEnv * env, jclass clazz, jobject buffer)
{
#ifdef _WIN32
	CHECK_EXISTS(wglDeleteBufferRegionARB)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	wglDeleteBufferRegionARB(address);
#else
	CHECK_EXISTS(NULL)
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglDestroyPbufferARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglDestroyPbufferARB(JNIEnv * env, jclass clazz, jint p0)
{
#ifdef _WIN32
	CHECK_EXISTS(wglDestroyPbufferARB)
	jboolean ret = (jboolean) wglDestroyPbufferARB((HPBUFFERARB) p0);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglFreeMemoryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_wglFreeMemoryNV(JNIEnv * env, jclass clazz, jobject buffer)
{
#ifdef _WIN32
	CHECK_EXISTS(wglFreeMemoryNV)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	wglFreeMemoryNV(address);
#else
	CHECK_EXISTS(NULL)
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetCurrentReadDCARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglGetCurrentReadDCARB(JNIEnv * env, jclass clazz)
{
#ifdef _WIN32
	CHECK_EXISTS(wglGetCurrentReadDCARB)
	jint ret = (jint) wglGetCurrentReadDCARB();
	return ret;
#else
	CHECK_EXISTS(NULL)
	return 0;
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetExtensionsStringARB
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GL_wglGetExtensionsStringARB(JNIEnv * env, jclass clazz)
{
#ifdef _WIN32
	if (wglGetExtensionsStringARB)
		return env->NewStringUTF(wglGetExtensionsStringARB(hdc));
	else
		return NULL;
#else
	CHECK_EXISTS(NULL)
	return NULL;
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetExtensionsStringEXT
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GL_wglGetExtensionsStringEXT(JNIEnv * env, jclass clazz)
{
#ifdef _WIN32
	CHECK_EXISTS(wglGetExtensionsStringEXT)
	jstring ret = env->NewStringUTF(wglGetExtensionsStringEXT());
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetPbufferDCARB
 */
/*JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglGetPbufferDCARB(JNIEnv * env, jclass clazz, jint p0)
{
#ifdef _WIN32
	CHECK_EXISTS(wglGetPbufferDCARB)
	jint ret = (jint) wglGetPbufferDCARB((HPBUFFERARB) p0);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetPixelFormatAttribfvARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglGetPixelFormatAttribfvARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
#ifdef _WIN32
	CHECK_EXISTS(wglGetPixelFormatAttribfvARB)
	jboolean ret = (jboolean) wglGetPixelFormatAttribfvARB((HDC) p0, (GLint) p1, (GLint) p2, (GLuint) p3, (const GLint *) p4, (GLfloat *) p5);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetPixelFormatAttribivARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglGetPixelFormatAttribivARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
#ifdef _WIN32
	CHECK_EXISTS(wglGetPixelFormatAttribivARB)
	jboolean ret = (jboolean) wglGetPixelFormatAttribivARB((HDC) p0, (GLint) p1, (GLint) p2, (GLuint) p3, (const GLint *) p4, (GLint *) p5);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetSwapIntervalEXT
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglGetSwapIntervalEXT(JNIEnv * env, jclass clazz)
{
#ifdef _WIN32
	CHECK_EXISTS(wglGetSwapIntervalEXT)
	jint ret = (jint) wglGetSwapIntervalEXT();
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
 */

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglMakeContextCurrentARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglMakeContextCurrentARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
#ifdef _WIN32
	CHECK_EXISTS(wglMakeContextCurrentARB)
	jboolean ret = (jboolean) wglMakeContextCurrentARB((HDC) p0, (HDC) p1, (HGLRC) p2);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglQueryPbufferARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglQueryPbufferARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
#ifdef _WIN32
	CHECK_EXISTS(wglQueryPbufferARB)
	jboolean ret = (jboolean) wglQueryPbufferARB((HPBUFFERARB) p0, (GLint) p1, (GLint *) p2);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglReleasePbufferDCARB
 */
/*JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglReleasePbufferDCARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
#ifdef _WIN32
	CHECK_EXISTS(wglReleasePbufferDCARB)
	jint ret = (jint) wglReleasePbufferDCARB((HPBUFFERARB) p0, (HDC) p1);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglReleaseTexImageARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglReleaseTexImageARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
#ifdef _WIN32
	CHECK_EXISTS(wglReleaseTexImageARB)
	jboolean ret = (jboolean) wglReleaseTexImageARB((HPBUFFERARB) p0, (GLint) p1);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglRestoreBufferRegionARB
 */

/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglRestoreBufferRegionARB(JNIEnv * env, jclass clazz, jobject buffer, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
#ifdef _WIN32
	CHECK_EXISTS(wglRestoreBufferRegionARB)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	jboolean ret = (jboolean) wglRestoreBufferRegionARB(address, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglSaveBufferRegionARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglSaveBufferRegionARB(JNIEnv * env, jclass clazz, jobject buffer, jint p1, jint p2, jint p3, jint p4)
{
#ifdef _WIN32
	CHECK_EXISTS(wglSaveBufferRegionARB)
	void *address = (void *)env->GetDirectBufferAddress(buffer);
	jboolean ret = (jboolean) wglSaveBufferRegionARB(address, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglSetPbufferAttribARB
 */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglSetPbufferAttribARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
#ifdef _WIN32
	CHECK_EXISTS(wglSetPbufferAttribARB)
	jboolean ret = (jboolean) wglSetPbufferAttribARB((HPBUFFERARB) p0, (const GLint *) p1);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
*/
/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglSwapIntervalEXT
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglSwapIntervalEXT(JNIEnv * env, jclass clazz, jint p0)
{
#ifdef _WIN32
	CHECK_EXISTS(wglSwapIntervalEXT)
	jboolean ret = (jboolean) wglSwapIntervalEXT((GLint) p0);
	return ret;
#else
	CHECK_EXISTS(NULL)
	return JNI_FALSE;
#endif
}
 */

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glWindowPos2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glWindowPos2fARB(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	CHECK_EXISTS(glWindowPos2fARB)
	glWindowPos2fARB((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glWindowPos2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glWindowPos2iARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	CHECK_EXISTS(glWindowPos2iARB)
	glWindowPos2iARB((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glWindowPos2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glWindowPos2sARB(JNIEnv * env, jclass clazz, jshort p0, jshort p1)
{
	CHECK_EXISTS(glWindowPos2sARB)
	glWindowPos2sARB((GLshort) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glWindowPos3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glWindowPos3fARB(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	CHECK_EXISTS(glWindowPos3fARB)
	glWindowPos3fARB((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glWindowPos3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glWindowPos3iARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	CHECK_EXISTS(glWindowPos3iARB)
	glWindowPos3iARB((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glWindowPos3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glWindowPos3sARB(JNIEnv * env, jclass clazz, jshort p0, jshort p1, jshort p2)
{
	CHECK_EXISTS(glWindowPos3sARB)
	glWindowPos3sARB((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glWriteMaskEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_glWriteMaskEXT(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	CHECK_EXISTS(glWriteMaskEXT)
	glWriteMaskEXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    nglBindBufferARB
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglBindBufferARB(JNIEnv *env, jclass clazz, jint target, jint buffer)
{
	CHECK_EXISTS(glBindBufferARB)
	glBindBufferARB((GLenum) target, (GLuint) buffer);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glDeleteBuffersARB
 * Signature: n(II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglDeleteBuffersARB(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffer_offset)
{
	CHECK_EXISTS(glDeleteBuffersARB)
	const GLuint *buffers_address = (GLuint *)env->GetDirectBufferAddress(buffers);
	glDeleteBuffersARB((GLsizei)n, buffers_address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGenBuffersARB
 * Signature: n(II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGenBuffersARB(JNIEnv *env, jclass clazz, jint n, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGenBuffersARB)
	GLuint *address = (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenBuffersARB((GLsizei)n, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glIsBufferARB
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glIsBufferARB(JNIEnv *env, jclass clazz, jint buffer)
{
	CHECK_EXISTS(glIsBufferARB)
	jboolean ret = glIsBufferARB((GLuint)buffer);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBufferDataARB
 * Signature: n(IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglBufferDataARB(JNIEnv *env, jclass clazz, jint target, jint size, jobject buffer, jint buffer_offset, jint usage)
{
	CHECK_EXISTS(glBufferDataARB)
	const GLbyte *address = (const GLbyte *)safeGetBufferAddress(env, buffer);
	glBufferDataARB((GLenum)target, (GLsizeiptrARB)size, address + buffer_offset, (GLenum)usage);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glBufferSubDataARB
 * Signature: n(IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglBufferSubDataARB(JNIEnv *env, jclass clazz, jint target, jint offset, jint size, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glBufferSubDataARB)
	const GLubyte *address = (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glBufferSubDataARB((GLenum)target, (GLintptrARB)offset, (GLsizeiptrARB)size, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGetBufferSubDataARB
 * Signature: n(IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetBufferSubDataARB(JNIEnv *env, jclass clazz, jint target, jint offset, jint size, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetBufferSubDataARB)
	GLubyte *address = (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetBufferSubDataARB((GLenum)target, (GLintptrARB)offset, (GLsizeiptrARB)size, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glMapBufferARB
 * Signature: (II)I
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL_glMapBufferARB(JNIEnv *env, jclass clazz, jint target, jint access, jint size, jobject old_buffer)
{
	CHECK_EXISTS(glMapBufferARB)
	void *buffer_address = glMapBufferARB((GLenum)target, (GLenum)access);
	CHECK_GL_ERROR
	if (old_buffer != NULL) {
		void *old_buffer_address = env->GetDirectBufferAddress(old_buffer);
		if (old_buffer_address == buffer_address)
			return old_buffer;
	}
	return safeNewBuffer(env, buffer_address, size);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glUnmapBufferARB
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_glUnmapBufferARB(JNIEnv *env, jclass clazz, jint target)
{
	CHECK_EXISTS(glUnmapBufferARB)
	jboolean ret = glUnmapBufferARB((GLenum)target);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGetBufferParameterivARB
 * Signature: n(III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_nglGetBufferParameterivARB(JNIEnv *env, jclass clazz, jint target, jint pname, jobject buffer, jint buffer_offset)
{
	CHECK_EXISTS(glGetBufferParameterivARB)
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	glGetBufferParameterivARB((GLenum)target, (GLenum)pname, address + buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    glGetBufferPointervARB
 * Signature: (III)V
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL_glGetBufferPointerARB(JNIEnv *env, jclass clazz, jint target, jint pname, jint size)
{
	CHECK_EXISTS(glGetBufferPointervARB)
	void *pointer;
	glGetBufferPointervARB((GLenum)target, (GLenum)pname, &pointer);
	CHECK_GL_ERROR
	return safeNewBuffer(env, pointer, size);
}
