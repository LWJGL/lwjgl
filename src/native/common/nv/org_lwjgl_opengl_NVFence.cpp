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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVFence
// ----------------------------------

#include "extgl.h"


typedef void (APIENTRY * glGenFencesNVPROC) (GLsizei n, GLuint *fences);
typedef void (APIENTRY * glDeleteFencesNVPROC) (GLsizei n, const GLuint *fences);
typedef void (APIENTRY * glSetFenceNVPROC) (GLuint fence, GLenum condition);
typedef GLboolean (APIENTRY * glTestFenceNVPROC) (GLuint fence);
typedef void (APIENTRY * glFinishFenceNVPROC) (GLuint fence);
typedef GLboolean (APIENTRY * glIsFenceNVPROC) (GLuint fence);
typedef void (APIENTRY * glGetFenceivNVPROC) (GLuint fence, GLenum pname, GLint *params);

static glGenFencesNVPROC glGenFencesNV;
static glDeleteFencesNVPROC glDeleteFencesNV;
static glSetFenceNVPROC glSetFenceNV;
static glTestFenceNVPROC glTestFenceNV;
static glFinishFenceNVPROC glFinishFenceNV;
static glIsFenceNVPROC glIsFenceNV;
static glGetFenceivNVPROC glGetFenceivNV;

/*
 * Class:	org.lwjgl.opengl.NVFence
 * Method:	nglGenFencesNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVFence_nglGenFencesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piFences, jint piFences_offset)
{
	GLuint *piFences_ptr = (GLuint *)env->GetDirectBufferAddress(piFences) + piFences_offset;
	glGenFencesNV(n, piFences_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVFence
 * Method:	nglDeleteFencesNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVFence_nglDeleteFencesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piFences, jint piFences_offset)
{
	GLuint *piFences_ptr = (GLuint *)env->GetDirectBufferAddress(piFences) + piFences_offset;
	glDeleteFencesNV(n, piFences_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVFence
 * Method:	glSetFenceNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVFence_glSetFenceNV
	(JNIEnv * env, jclass clazz, jint fence, jint condition)
{
	glSetFenceNV(fence, condition);
	
}

/*
 * Class:	org.lwjgl.opengl.NVFence
 * Method:	glTestFenceNV
 */
static jboolean JNICALL Java_org_lwjgl_opengl_NVFence_glTestFenceNV
	(JNIEnv * env, jclass clazz, jint fence)
{
	GLboolean result = glTestFenceNV(fence);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.NVFence
 * Method:	glFinishFenceNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVFence_glFinishFenceNV
	(JNIEnv * env, jclass clazz, jint fence)
{
	glFinishFenceNV(fence);
	
}

/*
 * Class:	org.lwjgl.opengl.NVFence
 * Method:	glIsFenceNV
 */
static jboolean JNICALL Java_org_lwjgl_opengl_NVFence_glIsFenceNV
	(JNIEnv * env, jclass clazz, jint fence)
{
	GLboolean result = glIsFenceNV(fence);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.NVFence
 * Method:	nglGetFenceivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVFence_nglGetFenceivNV
	(JNIEnv * env, jclass clazz, jint fence, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetFenceivNV(fence, pname, piParams_ptr);
	
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVFence_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGenFencesNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVFence_nglGenFencesNV, "glGenFencesNV", (void**)&glGenFencesNV},
		{"nglDeleteFencesNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVFence_nglDeleteFencesNV, "glDeleteFencesNV", (void**)&glDeleteFencesNV},
		{"glSetFenceNV", "(II)V", (void*)&Java_org_lwjgl_opengl_NVFence_glSetFenceNV, "glSetFenceNV", (void**)&glSetFenceNV},
		{"glTestFenceNV", "(I)Z", (void*)&Java_org_lwjgl_opengl_NVFence_glTestFenceNV, "glTestFenceNV", (void**)&glTestFenceNV},
		{"glFinishFenceNV", "(I)V", (void*)&Java_org_lwjgl_opengl_NVFence_glFinishFenceNV, "glFinishFenceNV", (void**)&glFinishFenceNV},
		{"glIsFenceNV", "(I)Z", (void*)&Java_org_lwjgl_opengl_NVFence_glIsFenceNV, "glIsFenceNV", (void**)&glIsFenceNV},
		{"nglGetFenceivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVFence_nglGetFenceivNV, "glGetFenceivNV", (void**)&glGetFenceivNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}
