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

#include <windows.h>
#include "org_lwjgl_opengl_GL.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    activeStencilFaceEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_activeStencilFaceEXT(JNIEnv * env, jobject obj, jint p0)
{
	glActiveStencilFaceEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    activeTextureARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_activeTextureARB(JNIEnv * env, jobject obj, jint p0)
{
	glActiveTextureARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    alphaFragmentOp1ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_alphaFragmentOp1ATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glAlphaFragmentOp1ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    alphaFragmentOp2ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_alphaFragmentOp2ATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glAlphaFragmentOp2ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    alphaFragmentOp3ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_alphaFragmentOp3ATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9, jint p10, jint p11)
{
	glAlphaFragmentOp3ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9, (GLint) p10, (GLint) p11);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    areProgramsResidentNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_areProgramsResidentNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	jboolean ret = (jboolean) glAreProgramsResidentNV((GLint) p0, (const GLuint *) p1, (GLubyte *) p2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    arrayObjectATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_arrayObjectATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glArrayObjectATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    beginFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_beginFragmentShaderATI(JNIEnv * env, jobject obj)
{
	glBeginFragmentShaderATI();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    beginOcclusionQueryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_beginOcclusionQueryNV(JNIEnv * env, jobject obj, jint p0)
{
	glBeginOcclusionQueryNV((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    beginVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_beginVertexShaderEXT(JNIEnv * env, jobject obj)
{
	glBeginVertexShaderEXT();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_bindFragmentShaderATI(JNIEnv * env, jobject obj, jint p0)
{
	glBindFragmentShaderATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindLightParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_bindLightParameterEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	jint ret = (jint) glBindLightParameterEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindMaterialParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_bindMaterialParameterEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	jint ret = (jint) glBindMaterialParameterEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_bindParameterEXT(JNIEnv * env, jobject obj, jint p0)
{
	jint ret = (jint) glBindParameterEXT((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindProgramARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_bindProgramARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBindProgramARB((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_bindProgramNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBindProgramNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindTexGenParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_bindTexGenParameterEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	jint ret = (jint) glBindTexGenParameterEXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindTextureUnitParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_bindTextureUnitParameterEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	jint ret = (jint) glBindTextureUnitParameterEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    bindVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_bindVertexShaderEXT(JNIEnv * env, jobject obj, jint p0)
{
	glBindVertexShaderEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    clientActiveTextureARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_clientActiveTextureARB(JNIEnv * env, jobject obj, jint p0)
{
	glClientActiveTextureARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    clientActiveVertexStreamATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_clientActiveVertexStreamATI(JNIEnv * env, jobject obj, jint p0)
{
	glClientActiveVertexStreamATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    colorFragmentOp1ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_colorFragmentOp1ATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glColorFragmentOp1ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    colorFragmentOp2ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_colorFragmentOp2ATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9)
{
	glColorFragmentOp2ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    colorFragmentOp3ATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_colorFragmentOp3ATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9, jint p10, jint p11, jint p12)
{
	glColorFragmentOp3ATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9, (GLint) p10, (GLint) p11, (GLint) p12);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    combinerInputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_combinerInputNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCombinerInputNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    combinerOutputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_combinerOutputNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jboolean p7, jboolean p8, jboolean p9)
{
	glCombinerOutputNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLboolean) p7, (GLboolean) p8, (GLboolean) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    combinerParameterfNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_combinerParameterfNV(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glCombinerParameterfNV((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    combinerParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_combinerParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glCombinerParameterfvNV((GLuint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    combinerParameteriNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_combinerParameteriNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glCombinerParameteriNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    combinerParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_combinerParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glCombinerParameterivNV((GLint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    combinerStageParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_combinerStageParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glCombinerStageParameterfvNV((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    compressedTexImage1DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_compressedTexImage1DARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCompressedTexImage1DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (const void *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    compressedTexImage2DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_compressedTexImage2DARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCompressedTexImage2DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (const void *) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    compressedTexImage3DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_compressedTexImage3DARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glCompressedTexImage3DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    compressedTexSubImage1DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_compressedTexSubImage1DARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCompressedTexSubImage1DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (const void *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    compressedTexSubImage2DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_compressedTexSubImage2DARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glCompressedTexSubImage2DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    compressedTexSubImage3DARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_compressedTexSubImage3DARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8, jint p9, jint p10)
{
	glCompressedTexSubImage3DARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (GLint) p8, (GLint) p9, (const void *) p10);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    currentPaletteMatrixARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_currentPaletteMatrixARB(JNIEnv * env, jobject obj, jint p0)
{
	glCurrentPaletteMatrixARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    deleteFencesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_deleteFencesNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteFencesNV((GLint) p0, (const GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    deleteFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_deleteFragmentShaderATI(JNIEnv * env, jobject obj, jint p0)
{
	glDeleteFragmentShaderATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    deleteOcclusionQueriesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_deleteOcclusionQueriesNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteOcclusionQueriesNV((GLint) p0, (const GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    deleteProgramsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_deleteProgramsARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteProgramsARB((GLint) p0, (const GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    deleteProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_deleteProgramsNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteProgramsNV((GLint) p0, (const GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    deleteVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_deleteVertexShaderEXT(JNIEnv * env, jobject obj, jint p0)
{
	glDeleteVertexShaderEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    disableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_disableVariantClientStateEXT(JNIEnv * env, jobject obj, jint p0)
{
	glDisableVariantClientStateEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    disableVertexAttribArrayARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_disableVertexAttribArrayARB(JNIEnv * env, jobject obj, jint p0)
{
	glDisableVertexAttribArrayARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    drawElementArrayATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_drawElementArrayATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDrawElementArrayATI((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    drawRangeElementArrayATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_drawRangeElementArrayATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glDrawRangeElementArrayATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    drawRangeElementsEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_drawRangeElementsEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glDrawRangeElementsEXT((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint) p3, (GLuint) p4, (const void *) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    elementPointerATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_elementPointerATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glElementPointerATI((GLint) p0, (const void *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    enableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_enableVariantClientStateEXT(JNIEnv * env, jobject obj, jint p0)
{
	glEnableVariantClientStateEXT((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    enableVertexAttribArrayARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_enableVertexAttribArrayARB(JNIEnv * env, jobject obj, jint p0)
{
	glEnableVertexAttribArrayARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    endFragmentShaderATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_endFragmentShaderATI(JNIEnv * env, jobject obj)
{
	glEndFragmentShaderATI();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    endOcclusionQueryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_endOcclusionQueryNV(JNIEnv * env, jobject obj)
{
	glEndOcclusionQueryNV();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    endVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_endVertexShaderEXT(JNIEnv * env, jobject obj)
{
	glEndVertexShaderEXT();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    evalMapsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_evalMapsNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glEvalMapsNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    executeProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_executeProgramNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glExecuteProgramNV((GLuint) p0, (GLuint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    extractComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_extractComponentEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glExtractComponentEXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    finalCombinerInputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_finalCombinerInputNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glFinalCombinerInputNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    finishFenceNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_finishFenceNV(JNIEnv * env, jobject obj, jint p0)
{
	glFinishFenceNV((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    flushVertexArrayRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_flushVertexArrayRangeNV(JNIEnv * env, jobject obj)
{
	glFlushVertexArrayRangeNV();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    fogCoorddEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_fogCoorddEXT(JNIEnv * env, jobject obj, jdouble p0)
{
	glFogCoorddEXT((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    fogCoorddvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_fogCoorddvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glFogCoorddvEXT((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    fogCoordfEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_fogCoordfEXT(JNIEnv * env, jobject obj, jfloat p0)
{
	glFogCoordfEXT((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    fogCoordfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_fogCoordfvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glFogCoordfvEXT((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    fogCoordPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_fogCoordPointerEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glFogCoordPointerEXT((GLuint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    freeObjectBufferATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_freeObjectBufferATI(JNIEnv * env, jobject obj, jint p0)
{
	glFreeObjectBufferATI((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    genFencesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_genFencesNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGenFencesNV((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    genFragmentShadersATI
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_genFragmentShadersATI(JNIEnv * env, jobject obj, jint p0)
{
	jint ret = (jint) glGenFragmentShadersATI((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    genOcclusionQueriesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_genOcclusionQueriesNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGenOcclusionQueriesNV((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    genProgramsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_genProgramsARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGenProgramsARB((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    genProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_genProgramsNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGenProgramsNV((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    genSymbolsEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_genSymbolsEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	jint ret = (jint) glGenSymbolsEXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    genVertexShadersEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_genVertexShadersEXT(JNIEnv * env, jobject obj, jint p0)
{
	jint ret = (jint) glGenVertexShadersEXT((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getArrayObjectfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getArrayObjectfvATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetArrayObjectfvATI((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getArrayObjectivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getArrayObjectivATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetArrayObjectivATI((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getCombinerInputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getCombinerInputParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glGetCombinerInputParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLuint) p3, (GLfloat *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getCombinerInputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getCombinerInputParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glGetCombinerInputParameterivNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLuint) p3, (GLint *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getCombinerOutputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getCombinerOutputParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetCombinerOutputParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLfloat *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getCombinerOutputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getCombinerOutputParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetCombinerOutputParameterivNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getCombinerStageParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getCombinerStageParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetCombinerStageParameterfvNV((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getCompressedTexImageARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getCompressedTexImageARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetCompressedTexImageARB((GLuint) p0, (GLint) p1, (void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getFenceivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getFenceivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetFenceivNV((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getFinalCombinerInputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getFinalCombinerInputParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetFinalCombinerInputParameterfvNV((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getFinalCombinerInputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getFinalCombinerInputParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetFinalCombinerInputParameterivNV((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getInvariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getInvariantBooleanvEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetInvariantBooleanvEXT((GLuint) p0, (GLuint) p1, (GLubyte *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getInvariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getInvariantFloatvEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetInvariantFloatvEXT((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getInvariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getInvariantIntegervEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetInvariantIntegervEXT((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getLocalConstantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getLocalConstantBooleanvEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLocalConstantBooleanvEXT((GLuint) p0, (GLuint) p1, (GLubyte *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getLocalConstantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getLocalConstantFloatvEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLocalConstantFloatvEXT((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getLocalConstantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getLocalConstantIntegervEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLocalConstantIntegervEXT((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getMapAttribParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getMapAttribParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetMapAttribParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLfloat *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getMapAttribParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getMapAttribParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetMapAttribParameterivNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getMapControlPointsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getMapControlPointsNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jboolean p5, jint p6)
{
	glGetMapControlPointsNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint) p3, (GLint) p4, (GLboolean) p5, (void *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getMapParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getMapParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapParameterfvNV((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getMapParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getMapParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapParameterivNV((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getObjectBufferfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getObjectBufferfvATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetObjectBufferfvATI((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getObjectBufferivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getObjectBufferivATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetObjectBufferivATI((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getOcclusionQueryivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getOcclusionQueryivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetOcclusionQueryivNV((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getOcclusionQueryuivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getOcclusionQueryuivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetOcclusionQueryuivNV((GLuint) p0, (GLuint) p1, (GLuint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramEnvParameterdvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramEnvParameterdvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramEnvParameterdvARB((GLuint) p0, (GLuint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramEnvParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramEnvParameterfvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramEnvParameterfvARB((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramivARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramivARB((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramivNV((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramLocalParameterdvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramLocalParameterdvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramLocalParameterdvARB((GLuint) p0, (GLuint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramLocalParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramLocalParameterfvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramLocalParameterfvARB((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramParameterdvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramParameterdvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetProgramParameterdvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLdouble *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetProgramParameterfvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLfloat *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramStringARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramStringARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramStringARB((GLuint) p0, (GLuint) p1, (void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getProgramStringNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getProgramStringNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetProgramStringNV((GLuint) p0, (GLuint) p1, (GLubyte *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getTexBumpParameterfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getTexBumpParameterfvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetTexBumpParameterfvATI((GLuint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getTexBumpParameterivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getTexBumpParameterivATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetTexBumpParameterivATI((GLuint) p0, (GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getTrackMatrixivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getTrackMatrixivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetTrackMatrixivNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVariantArrayObjectfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVariantArrayObjectfvATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVariantArrayObjectfvATI((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVariantArrayObjectivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVariantArrayObjectivATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVariantArrayObjectivATI((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVariantBooleanvEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVariantBooleanvEXT((GLuint) p0, (GLuint) p1, (GLboolean *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVariantFloatvEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVariantFloatvEXT((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVariantIntegervEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVariantIntegervEXT((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVariantPointervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVariantPointervEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVariantPointervEXT((GLuint) p0, (GLuint) p1, (void **) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribdvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribdvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribdvARB((GLuint) p0, (GLuint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribdvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribdvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribdvNV((GLuint) p0, (GLuint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribfvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribfvARB((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribfvNV((GLuint) p0, (GLuint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribivARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribivARB((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribivNV((GLuint) p0, (GLuint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribPointervARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribPointervARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribPointervARB((GLuint) p0, (GLuint) p1, (void **) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    getVertexAttribPointervNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_getVertexAttribPointervNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetVertexAttribPointervNV((GLuint) p0, (GLuint) p1, (void **) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    insertComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_insertComponentEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glInsertComponentEXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    isFenceNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_isFenceNV(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsFenceNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    isObjectBufferATI
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_isObjectBufferATI(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsObjectBufferATI((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    isOcclusionQueryNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_isOcclusionQueryNV(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsOcclusionQueryNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    isProgramARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_isProgramARB(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsProgramARB((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    isProgramNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_isProgramNV(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsProgramNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    isVariantEnabledEXT
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_isVariantEnabledEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	jboolean ret = (jboolean) glIsVariantEnabledEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    loadProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_loadProgramNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glLoadProgramNV((GLuint) p0, (GLuint) p1, (GLint) p2, (const GLubyte *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    loadTransposeMatrixdARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_loadTransposeMatrixdARB(JNIEnv * env, jobject obj, jint p0)
{
	glLoadTransposeMatrixdARB((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    loadTransposeMatrixfARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_loadTransposeMatrixfARB(JNIEnv * env, jobject obj, jint p0)
{
	glLoadTransposeMatrixfARB((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    lockArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_lockArraysEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLockArraysEXT((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    mapControlPointsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_mapControlPointsNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jboolean p7, jint p8)
{
	glMapControlPointsNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLboolean) p7, (const void *) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    mapParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_mapParameterfvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMapParameterfvNV((GLuint) p0, (GLuint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    mapParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_mapParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMapParameterivNV((GLuint) p0, (GLuint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    matrixIndexPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_matrixIndexPointerARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glMatrixIndexPointerARB((GLint) p0, (GLuint) p1, (GLint) p2, (void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    matrixIndexubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_matrixIndexubvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMatrixIndexubvARB((GLint) p0, (GLubyte *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    matrixIndexuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_matrixIndexuivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMatrixIndexuivARB((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    matrixIndexusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_matrixIndexusvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMatrixIndexusvARB((GLint) p0, (GLushort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiDrawArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiDrawArraysEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glMultiDrawArraysEXT((GLuint) p0, (GLint *) p1, (GLint *) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiDrawElementsEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiDrawElementsEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glMultiDrawElementsEXT((GLuint) p0, (GLint *) p1, (GLuint) p2, (const void **) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1)
{
	glMultiTexCoord1dARB((GLint) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord1dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glMultiTexCoord1fARB((GLuint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord1fvARB((GLint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1iARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord1iARB((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1ivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord1ivARB((GLuint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1sARB(JNIEnv * env, jobject obj, jint p0, jshort p1)
{
	glMultiTexCoord1sARB((GLint) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord1svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord1svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord1svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2)
{
	glMultiTexCoord2dARB((GLint) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord2dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2)
{
	glMultiTexCoord2fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord2fvARB((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2iARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMultiTexCoord2iARB((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2ivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord2ivARB((GLuint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2sARB(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2)
{
	glMultiTexCoord2sARB((GLint) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord2svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord2svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord2svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3)
{
	glMultiTexCoord3dARB((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord3dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	glMultiTexCoord3fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord3fvARB((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3iARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glMultiTexCoord3iARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3ivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord3ivARB((GLuint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3sARB(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3)
{
	glMultiTexCoord3sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord3svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord3svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord3svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4)
{
	glMultiTexCoord4dARB((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord4dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	glMultiTexCoord4fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord4fvARB((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4iARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glMultiTexCoord4iARB((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4ivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord4ivARB((GLuint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4sARB(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	glMultiTexCoord4sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multiTexCoord4svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multiTexCoord4svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glMultiTexCoord4svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multTransposeMatrixdARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multTransposeMatrixdARB(JNIEnv * env, jobject obj, jint p0)
{
	glMultTransposeMatrixdARB((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    multTransposeMatrixfARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_multTransposeMatrixfARB(JNIEnv * env, jobject obj, jint p0)
{
	glMultTransposeMatrixfARB((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    newObjectBufferATI
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_newObjectBufferATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	jint ret = (jint) glNewObjectBufferATI((GLint) p0, (const void *) p1, (GLuint) p2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3bATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3bATI(JNIEnv * env, jobject obj, jint p0, jbyte p1, jbyte p2, jbyte p3)
{
	glNormalStream3bATI((GLint) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3bvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3bvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNormalStream3bvATI((GLint) p0, (const GLbyte *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3dATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3dATI(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3)
{
	glNormalStream3dATI((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3dvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3dvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNormalStream3dvATI((GLuint) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3fATI(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	glNormalStream3fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3fvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3fvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNormalStream3fvATI((GLuint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3iATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glNormalStream3iATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3ivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3ivATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNormalStream3ivATI((GLuint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3sATI(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3)
{
	glNormalStream3sATI((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    normalStream3svATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_normalStream3svATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNormalStream3svATI((GLuint) p0, (const GLshort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    passTexCoordATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_passTexCoordATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPassTexCoordATI((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    PNTrianglesfATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_PNTrianglesfATI(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPNTrianglesfATI((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    PNTrianglesiATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_PNTrianglesiATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPNTrianglesiATI((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    pointParameterfARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_pointParameterfARB(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPointParameterfARB((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    pointParameterfEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_pointParameterfEXT(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPointParameterfEXT((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    pointParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_pointParameterfvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPointParameterfvARB((GLuint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    pointParameterfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_pointParameterfvEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPointParameterfvEXT((GLuint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    pointParameteriNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_pointParameteriNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPointParameteriNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    pointParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_pointParameterivNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPointParameterivNV((GLuint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programEnvParameter4dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programEnvParameter4dARB(JNIEnv * env, jobject obj, jint p0, jint p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glProgramEnvParameter4dARB((GLint) p0, (GLint) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programEnvParameter4dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programEnvParameter4dvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glProgramEnvParameter4dvARB((GLuint) p0, (GLuint) p1, (const GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programEnvParameter4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programEnvParameter4fARB(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5)
{
	glProgramEnvParameter4fARB((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programEnvParameter4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programEnvParameter4fvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glProgramEnvParameter4fvARB((GLuint) p0, (GLuint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programLocalParameter4dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programLocalParameter4dARB(JNIEnv * env, jobject obj, jint p0, jint p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glProgramLocalParameter4dARB((GLint) p0, (GLint) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programLocalParameter4dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programLocalParameter4dvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glProgramLocalParameter4dvARB((GLuint) p0, (GLuint) p1, (const GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programLocalParameter4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programLocalParameter4fARB(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5)
{
	glProgramLocalParameter4fARB((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programLocalParameter4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programLocalParameter4fvARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glProgramLocalParameter4fvARB((GLuint) p0, (GLuint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programParameter4dNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programParameter4dNV(JNIEnv * env, jobject obj, jint p0, jint p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glProgramParameter4dNV((GLint) p0, (GLint) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programParameter4dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programParameter4dvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glProgramParameter4dvNV((GLuint) p0, (GLuint) p1, (const GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programParameter4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programParameter4fNV(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5)
{
	glProgramParameter4fNV((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programParameter4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programParameter4fvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glProgramParameter4fvNV((GLuint) p0, (GLuint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programParameters4dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programParameters4dvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glProgramParameters4dvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (const GLdouble *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programParameters4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programParameters4fvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glProgramParameters4fvNV((GLuint) p0, (GLuint) p1, (GLuint) p2, (const GLfloat *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    programStringARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_programStringARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glProgramStringARB((GLuint) p0, (GLuint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    requestResidentProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_requestResidentProgramsNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRequestResidentProgramsNV((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    sampleCoverageARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_sampleCoverageARB(JNIEnv * env, jobject obj, jfloat p0, jboolean p1)
{
	glSampleCoverageARB((GLfloat) p0, (GLboolean) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    sampleMapATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_sampleMapATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glSampleMapATI((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3bEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3bEXT(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glSecondaryColor3bEXT((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3bvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3bvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3bvEXT((const GLbyte *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3dEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3dEXT(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glSecondaryColor3dEXT((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3dvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3dvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3dvEXT((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3fEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3fEXT(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glSecondaryColor3fEXT((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3fvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3fvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3fvEXT((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3iEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3iEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glSecondaryColor3iEXT((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3ivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3ivEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3ivEXT((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3sEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3sEXT(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glSecondaryColor3sEXT((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3svEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3svEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3svEXT((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3ubEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3ubEXT(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glSecondaryColor3ubEXT((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3ubvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3ubvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3ubvEXT((const GLubyte *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3uiEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3uiEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glSecondaryColor3uiEXT((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3uivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3uivEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3uivEXT((const GLuint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3usEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3usEXT(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glSecondaryColor3usEXT((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColor3usvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColor3usvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glSecondaryColor3usvEXT((const GLushort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    secondaryColorPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_secondaryColorPointerEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glSecondaryColorPointerEXT((GLint) p0, (GLuint) p1, (GLint) p2, (void *) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    setFenceNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_setFenceNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glSetFenceNV((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    setFragmentShaderConstantATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_setFragmentShaderConstantATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glSetFragmentShaderConstantATI((GLuint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    setInvariantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_setInvariantEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glSetInvariantEXT((GLuint) p0, (GLuint) p1, (void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    setLocalConstantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_setLocalConstantEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glSetLocalConstantEXT((GLuint) p0, (GLuint) p1, (void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    shaderOp1EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_shaderOp1EXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glShaderOp1EXT((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    shaderOp2EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_shaderOp2EXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glShaderOp2EXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    shaderOp3EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_shaderOp3EXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glShaderOp3EXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    swizzleEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_swizzleEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glSwizzleEXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    testFenceNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_testFenceNV(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glTestFenceNV((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    texBumpParameterfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_texBumpParameterfvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glTexBumpParameterfvATI((GLuint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    texBumpParameterivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_texBumpParameterivATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glTexBumpParameterivATI((GLuint) p0, (GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    trackMatrixNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_trackMatrixNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glTrackMatrixNV((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    unlockArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_unlockArraysEXT(JNIEnv * env, jobject obj)
{
	glUnlockArraysEXT();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    updateObjectBufferATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_updateObjectBufferATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glUpdateObjectBufferATI((GLuint) p0, (GLuint) p1, (GLint) p2, (const void *) p3, (GLuint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantArrayObjectATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantArrayObjectATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glVariantArrayObjectATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantbvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantbvEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantbvEXT((GLuint) p0, (GLbyte *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantdvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantdvEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantdvEXT((GLuint) p0, (GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantfvEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantfvEXT((GLuint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantivEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantivEXT((GLuint) p0, (GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantPointerEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVariantPointerEXT((GLuint) p0, (GLuint) p1, (GLint) p2, (void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantsvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantsvEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantsvEXT((GLuint) p0, (GLshort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantubvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantubvEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantubvEXT((GLuint) p0, (GLubyte *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantuivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantuivEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantuivEXT((GLuint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    variantusvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_variantusvEXT(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVariantusvEXT((GLuint) p0, (GLushort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexArrayRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexArrayRangeNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexArrayRangeNV((GLint) p0, (const void *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1)
{
	glVertexAttrib1dARB((GLint) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1dNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1dNV(JNIEnv * env, jobject obj, jint p0, jdouble p1)
{
	glVertexAttrib1dNV((GLint) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib1dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1dvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib1dvNV((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glVertexAttrib1fARB((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1fNV(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glVertexAttrib1fNV((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib1fvARB((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1fvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib1fvNV((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1sARB(JNIEnv * env, jobject obj, jint p0, jshort p1)
{
	glVertexAttrib1sARB((GLint) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1sNV(JNIEnv * env, jobject obj, jint p0, jshort p1)
{
	glVertexAttrib1sNV((GLint) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib1svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib1svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib1svNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib1svNV((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2)
{
	glVertexAttrib2dARB((GLint) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2dNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2dNV(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2)
{
	glVertexAttrib2dNV((GLint) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib2dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2dvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib2dvNV((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2)
{
	glVertexAttrib2fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2fNV(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2)
{
	glVertexAttrib2fNV((GLint) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib2fvARB((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2fvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib2fvNV((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2sARB(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2)
{
	glVertexAttrib2sARB((GLint) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2sNV(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2)
{
	glVertexAttrib2sNV((GLint) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib2svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib2svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib2svNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib2svNV((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3)
{
	glVertexAttrib3dARB((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3dNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3dNV(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3)
{
	glVertexAttrib3dNV((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib3dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3dvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib3dvNV((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertexAttrib3fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3fNV(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertexAttrib3fNV((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib3fvARB((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3fvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib3fvNV((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3sARB(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3)
{
	glVertexAttrib3sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3sNV(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3)
{
	glVertexAttrib3sNV((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib3svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib3svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib3svNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib3svNV((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4bvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4bvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4bvARB((GLuint) p0, (const GLbyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4dARB(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4)
{
	glVertexAttrib4dARB((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4dNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4dNV(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4)
{
	glVertexAttrib4dNV((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4dvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4dvARB((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4dvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4dvNV((GLuint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4fARB(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	glVertexAttrib4fARB((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4fNV(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	glVertexAttrib4fNV((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4fvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4fvARB((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4fvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4fvNV((GLuint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4ivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4ivARB((GLuint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4NbvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4NbvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4NbvARB((GLuint) p0, (const GLbyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4NivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4NivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4NivARB((GLuint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4NsvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4NsvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4NsvARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4NubARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4NubARB(JNIEnv * env, jobject obj, jint p0, jbyte p1, jbyte p2, jbyte p3, jbyte p4)
{
	glVertexAttrib4NubARB((GLint) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3, (GLbyte) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4NubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4NubvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4NubvARB((GLuint) p0, (const GLubyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4NuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4NuivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4NuivARB((GLuint) p0, (const GLuint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4NusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4NusvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4NusvARB((GLuint) p0, (const GLushort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4sARB(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	glVertexAttrib4sARB((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4sNV(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	glVertexAttrib4sNV((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4svARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4svARB((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4svNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4svNV((GLuint) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4ubNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4ubNV(JNIEnv * env, jobject obj, jint p0, jbyte p1, jbyte p2, jbyte p3, jbyte p4)
{
	glVertexAttrib4ubNV((GLint) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3, (GLbyte) p4);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4ubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4ubvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4ubvARB((GLuint) p0, (const GLubyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4ubvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4ubvNV(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4ubvNV((GLuint) p0, (const GLubyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4uivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4uivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4uivARB((GLuint) p0, (const GLuint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttrib4usvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttrib4usvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexAttrib4usvARB((GLuint) p0, (const GLushort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribPointerARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jboolean p3, jint p4, jint p5)
{
	glVertexAttribPointerARB((GLuint) p0, (GLint) p1, (GLuint) p2, (GLboolean) p3, (GLint) p4, (const void *) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribPointerNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribPointerNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glVertexAttribPointerNV((GLuint) p0, (GLint) p1, (GLuint) p2, (GLint) p3, (const void *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs1dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs1dvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs1dvNV((GLuint) p0, (GLint) p1, (const GLdouble *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs1fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs1fvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs1fvNV((GLuint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs1svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs1svNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs1svNV((GLuint) p0, (GLint) p1, (const GLshort *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs2dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs2dvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs2dvNV((GLuint) p0, (GLint) p1, (const GLdouble *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs2fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs2fvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs2fvNV((GLuint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs2svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs2svNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs2svNV((GLuint) p0, (GLint) p1, (const GLshort *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs3dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs3dvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs3dvNV((GLuint) p0, (GLint) p1, (const GLdouble *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs3fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs3fvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs3fvNV((GLuint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs3svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs3svNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs3svNV((GLuint) p0, (GLint) p1, (const GLshort *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs4dvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs4dvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs4dvNV((GLuint) p0, (GLint) p1, (const GLdouble *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs4fvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs4fvNV((GLuint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs4svNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs4svNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs4svNV((GLuint) p0, (GLint) p1, (const GLshort *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexAttribs4ubvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexAttribs4ubvNV(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexAttribs4ubvNV((GLuint) p0, (GLint) p1, (const GLubyte *) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexBlendARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexBlendARB(JNIEnv * env, jobject obj, jint p0)
{
	glVertexBlendARB((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexBlendEnvfATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexBlendEnvfATI(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glVertexBlendEnvfATI((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexBlendEnviATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexBlendEnviATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexBlendEnviATI((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2dATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2dATI(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2)
{
	glVertexStream2dATI((GLint) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2dvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2dvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream2dvATI((GLuint) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2fATI(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2)
{
	glVertexStream2fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2fvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2fvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream2fvATI((GLuint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2iATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertexStream2iATI((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2ivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2ivATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream2ivATI((GLuint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2sATI(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2)
{
	glVertexStream2sATI((GLint) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream2svATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream2svATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream2svATI((GLuint) p0, (const GLshort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3dATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3dATI(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3)
{
	glVertexStream3dATI((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3dvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3dvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream3dvATI((GLuint) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3fATI(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertexStream3fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3fvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3fvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream3fvATI((GLuint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3iATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertexStream3iATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3ivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3ivATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream3ivATI((GLuint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3sATI(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3)
{
	glVertexStream3sATI((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream3svATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream3svATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream3svATI((GLuint) p0, (const GLshort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4dATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4dATI(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4)
{
	glVertexStream4dATI((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4dvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4dvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream4dvATI((GLuint) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4fATI(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jfloat p3, jfloat p4)
{
	glVertexStream4fATI((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4fvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4fvATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream4fvATI((GLuint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4iATI(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glVertexStream4iATI((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4ivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4ivATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream4ivATI((GLuint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4sATI(JNIEnv * env, jobject obj, jint p0, jshort p1, jshort p2, jshort p3, jshort p4)
{
	glVertexStream4sATI((GLint) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3, (GLshort) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexStream4svATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexStream4svATI(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertexStream4svATI((GLuint) p0, (const GLshort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexWeightfEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexWeightfEXT(JNIEnv * env, jobject obj, jfloat p0)
{
	glVertexWeightfEXT((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexWeightfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexWeightfvEXT(JNIEnv * env, jobject obj, jint p0)
{
	glVertexWeightfvEXT((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    vertexWeightPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_vertexWeightPointerEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertexWeightPointerEXT((GLint) p0, (GLuint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightbvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightbvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightbvARB((GLint) p0, (GLbyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightdvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightdvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightdvARB((GLint) p0, (GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightfvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightfvARB((GLint) p0, (GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightivARB((GLint) p0, (GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightPointerARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glWeightPointerARB((GLint) p0, (GLuint) p1, (GLint) p2, (void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightsvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightsvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightsvARB((GLint) p0, (GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightubvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightubvARB((GLint) p0, (GLubyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightuivARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightuivARB((GLint) p0, (GLuint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    weightusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_weightusvARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWeightusvARB((GLint) p0, (GLushort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglAllocateMemoryNV
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglAllocateMemoryNV(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jfloat p3)
{
	jint ret = (jint) wglAllocateMemoryNV((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglBindTexImageARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglBindTexImageARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	jboolean ret = (jboolean) wglBindTexImageARB((HPBUFFERARB) p0, (GLint) p1);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglChoosePixelFormatARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglChoosePixelFormatARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	jboolean ret = (jboolean) wglChoosePixelFormatARB((HDC) p0, (const GLint *) p1, (const GLfloat *) p2, (GLuint) p3, (GLint *) p4, (GLuint *) p5);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglCreateBufferRegionARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglCreateBufferRegionARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	jint ret = (jint) wglCreateBufferRegionARB((HDC) p0, (GLint) p1, (GLint) p2);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglCreatePbufferARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglCreatePbufferARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	jint ret = (jint) wglCreatePbufferARB((HDC) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const GLint *) p4);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglDeleteBufferRegionARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_wglDeleteBufferRegionARB(JNIEnv * env, jclass clazz, jint p0)
{
	wglDeleteBufferRegionARB((void *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglDestroyPbufferARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglDestroyPbufferARB(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) wglDestroyPbufferARB((HPBUFFERARB) p0);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglFreeMemoryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_wglFreeMemoryNV(JNIEnv * env, jclass clazz, jint p0)
{
	wglFreeMemoryNV((void *) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetCurrentReadDCARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglGetCurrentReadDCARB(JNIEnv * env, jclass clazz)
{
	jint ret = (jint) wglGetCurrentReadDCARB();
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetExtensionsStringARB
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GL_wglGetExtensionsStringARB(JNIEnv * env, jclass clazz, jint p0)
{
	if (wglGetExtensionsStringARB)
		return env->NewStringUTF(wglGetExtensionsStringARB(GetDC((HWND) p0)));
	else
		return NULL;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetExtensionsStringEXT
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GL_wglGetExtensionsStringEXT(JNIEnv * env, jclass clazz)
{
	jstring ret = env->NewStringUTF(wglGetExtensionsStringEXT());
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetPbufferDCARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglGetPbufferDCARB(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) wglGetPbufferDCARB((HPBUFFERARB) p0);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetPixelFormatAttribfvARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglGetPixelFormatAttribfvARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	jboolean ret = (jboolean) wglGetPixelFormatAttribfvARB((HDC) p0, (GLint) p1, (GLint) p2, (GLuint) p3, (const GLint *) p4, (GLfloat *) p5);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetPixelFormatAttribivARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglGetPixelFormatAttribivARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	jboolean ret = (jboolean) wglGetPixelFormatAttribivARB((HDC) p0, (GLint) p1, (GLint) p2, (GLuint) p3, (const GLint *) p4, (GLint *) p5);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglGetSwapIntervalEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglGetSwapIntervalEXT(JNIEnv * env, jclass clazz)
{
	jint ret = (jint) wglGetSwapIntervalEXT();
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglMakeContextCurrentARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglMakeContextCurrentARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	jboolean ret = (jboolean) wglMakeContextCurrentARB((HDC) p0, (HDC) p1, (HGLRC) p2);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglQueryPbufferARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglQueryPbufferARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	jboolean ret = (jboolean) wglQueryPbufferARB((HPBUFFERARB) p0, (GLint) p1, (GLint *) p2);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglReleasePbufferDCARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL_wglReleasePbufferDCARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	jint ret = (jint) wglReleasePbufferDCARB((HPBUFFERARB) p0, (HDC) p1);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglReleaseTexImageARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglReleaseTexImageARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	jboolean ret = (jboolean) wglReleaseTexImageARB((HPBUFFERARB) p0, (GLint) p1);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglRestoreBufferRegionARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglRestoreBufferRegionARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	jboolean ret = (jboolean) wglRestoreBufferRegionARB((void *) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglSaveBufferRegionARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglSaveBufferRegionARB(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	jboolean ret = (jboolean) wglSaveBufferRegionARB((void *) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglSetPbufferAttribARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglSetPbufferAttribARB(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	jboolean ret = (jboolean) wglSetPbufferAttribARB((HPBUFFERARB) p0, (const GLint *) p1);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    wglSwapIntervalEXT
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL_wglSwapIntervalEXT(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) wglSwapIntervalEXT((GLint) p0);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2dARB(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glWindowPos2dARB((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2dvARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos2dvARB((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2fARB(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glWindowPos2fARB((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2fvARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos2fvARB((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2iARB(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glWindowPos2iARB((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2ivARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos2ivARB((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2sARB(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glWindowPos2sARB((GLshort) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos2svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos2svARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos2svARB((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3dARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3dARB(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glWindowPos3dARB((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3dvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3dvARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos3dvARB((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3fARB(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glWindowPos3fARB((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3fvARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos3fvARB((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3iARB(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glWindowPos3iARB((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3ivARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos3ivARB((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3sARB(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glWindowPos3sARB((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    windowPos3svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_windowPos3svARB(JNIEnv * env, jobject obj, jint p0)
{
	glWindowPos3svARB((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    writeMaskEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_writeMaskEXT(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glWriteMaskEXT((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL
 * Method:    checkWGLExtensionsString
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL_checkWGLExtensionsString(JNIEnv * env, jclass clazz)
{

	jfieldID fid_WGL_ARB_extensions_string = env->GetStaticFieldID(clazz, "WGL_ARB_extensions_string", "Z");
	jfieldID fid_WGL_EXT_extensions_string = env->GetStaticFieldID(clazz, "WGL_EXT_extensions_string", "Z");

	if (wglGetExtensionsStringARB)
		env->SetStaticBooleanField(clazz, fid_WGL_ARB_extensions_string, JNI_TRUE);
	if (wglGetExtensionsStringEXT)
		env->SetStaticBooleanField(clazz, fid_WGL_EXT_extensions_string, JNI_TRUE);

}

