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

// ----------------------------------
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVRegisterCombiners
// ----------------------------------

#include "extgl.h"


typedef void (APIENTRY * glCombinerParameterfvNVPROC) (GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glCombinerParameterfNVPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glCombinerParameterivNVPROC) (GLenum pname, const GLint *params);
typedef void (APIENTRY * glCombinerParameteriNVPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glCombinerInputNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY * glCombinerOutputNVPROC) (GLenum stage, GLenum portion, GLenum abOutput, GLenum cdOutput, GLenum sumOutput, GLenum scale, GLenum bias, GLboolean abDotProduct, GLboolean cdDotProduct, GLboolean muxSum);
typedef void (APIENTRY * glFinalCombinerInputNVPROC) (GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY * glGetCombinerInputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetCombinerInputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetCombinerOutputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetCombinerOutputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetFinalCombinerInputParameterfvNVPROC) (GLenum variable, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetFinalCombinerInputParameterivNVPROC) (GLenum variable, GLenum pname, GLint *params);

static glCombinerParameterfvNVPROC glCombinerParameterfvNV;
static glCombinerParameterfNVPROC  glCombinerParameterfNV;
static glCombinerParameterivNVPROC glCombinerParameterivNV;
static glCombinerParameteriNVPROC glCombinerParameteriNV;
static glCombinerInputNVPROC glCombinerInputNV;
static glCombinerOutputNVPROC glCombinerOutputNV;
static glFinalCombinerInputNVPROC glFinalCombinerInputNV;
static glGetCombinerInputParameterfvNVPROC glGetCombinerInputParameterfvNV;
static glGetCombinerInputParameterivNVPROC glGetCombinerInputParameterivNV;
static glGetCombinerOutputParameterfvNVPROC glGetCombinerOutputParameterfvNV;
static glGetCombinerOutputParameterivNVPROC glGetCombinerOutputParameterivNV;
static glGetFinalCombinerInputParameterfvNVPROC glGetFinalCombinerInputParameterfvNV;
static glGetFinalCombinerInputParameterivNVPROC glGetFinalCombinerInputParameterivNV;

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	glCombinerParameterfNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameterfNV
	(JNIEnv * env, jclass clazz, jint pname, jfloat param)
{
	glCombinerParameterfNV(pname, param);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglCombinerParameterfvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterfvNV
	(JNIEnv * env, jclass clazz, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glCombinerParameterfvNV(pname, pfParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	glCombinerParameteriNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameteriNV
	(JNIEnv * env, jclass clazz, jint pname, jint param)
{
	glCombinerParameteriNV(pname, param);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglCombinerParameterivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterivNV
	(JNIEnv * env, jclass clazz, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glCombinerParameterivNV(pname, piParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	glCombinerInputNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerInputNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint variable, jint input, jint mapping, jint componentUsage)
{
	glCombinerInputNV(stage, portion, variable, input, mapping, componentUsage);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	glCombinerOutputNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerOutputNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint abOutput, jint cdOutput, jint sumOutput, jint scale, jint bias, jboolean abDotProduct, jboolean cdDotProduct, jboolean muxSum)
{
	glCombinerOutputNV(stage, portion, abOutput, cdOutput, sumOutput, scale, bias, abDotProduct, cdDotProduct, muxSum);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	glFinalCombinerInputNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glFinalCombinerInputNV
	(JNIEnv * env, jclass clazz, jint variable, jint input, jint mapping, jint componentUsage)
{
	glFinalCombinerInputNV(variable, input, mapping, componentUsage);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglGetCombinerInputParameterfvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterfvNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetCombinerInputParameterfvNV(stage, portion, variable, pname, pfParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglGetCombinerInputParameterivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterivNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetCombinerInputParameterivNV(stage, portion, variable, pname, piParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglGetCombinerOutputParameterfvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterfvNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetCombinerOutputParameterfvNV(stage, portion, pname, pfParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglGetCombinerOutputParameterivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterivNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint pname, jobject piParams, jint pfParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + pfParams_offset;
	glGetCombinerOutputParameterivNV(stage, portion, pname, piParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglGetFinalCombinerInputParameterfvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterfvNV
	(JNIEnv * env, jclass clazz, jint variable, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetFinalCombinerInputParameterfvNV(variable, pname, pfParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVRegisterCombiners
 * Method:	nglGetFinalCombinerInputParameterivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterivNV
	(JNIEnv * env, jclass clazz, jint variable, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetFinalCombinerInputParameterivNV(variable, pname, piParams_ptr);
	
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glCombinerParameterfNV", "(IF)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameterfNV, "glCombinerParameterfNV", (void**)&glCombinerParameterfNV},
		{"nglCombinerParameterfvNV", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterfvNV, "glCombinerParameterfvNV", (void**)&glCombinerParameterfvNV},
		{"glCombinerParameteriNV", "(II)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameteriNV, "glCombinerParameteriNV", (void**)&glCombinerParameteriNV},
		{"nglCombinerParameterivNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterivNV, "glCombinerParameterivNV", (void**)&glCombinerParameterivNV},
		{"glCombinerInputNV", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerInputNV, "glCombinerInputNV", (void**)&glCombinerInputNV},
		{"glCombinerOutputNV", "(IIIIIIIZZZ)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerOutputNV, "glCombinerOutputNV", (void**)&glCombinerOutputNV},
		{"glFinalCombinerInputNV", "(IIII)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_glFinalCombinerInputNV, "glFinalCombinerInputNV", (void**)&glFinalCombinerInputNV},
		{"nglGetCombinerInputParameterfvNV", "(IIIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterfvNV, "glGetCombinerInputParameterfvNV", (void**)&glGetCombinerInputParameterfvNV},
		{"nglGetCombinerInputParameterivNV", "(IIIILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterivNV, "glGetCombinerInputParameterivNV", (void**)&glGetCombinerInputParameterivNV},
		{"nglGetCombinerOutputParameterfvNV", "(IIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterfvNV, "glGetCombinerOutputParameterfvNV", (void**)&glGetCombinerOutputParameterfvNV},
		{"nglGetCombinerOutputParameterivNV", "(IIILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterivNV, "glGetCombinerOutputParameterivNV", (void**)&glGetCombinerOutputParameterivNV},
		{"nglGetFinalCombinerInputParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterfvNV, "glGetFinalCombinerInputParameterfvNV", (void**)&glGetFinalCombinerInputParameterfvNV},
		{"nglGetFinalCombinerInputParameterivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterivNV, "glGetFinalCombinerInputParameterivNV", (void**)&glGetFinalCombinerInputParameterivNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}

