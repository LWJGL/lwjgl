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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBVertexBlend
// ----------------------------------

#include "extgl.h"


typedef void (APIENTRY * glWeightbvARBPROC) (GLint size, GLbyte *weights);
typedef void (APIENTRY * glWeightsvARBPROC) (GLint size, GLshort *weights);
typedef void (APIENTRY * glWeightivARBPROC) (GLint size, GLint *weights);
typedef void (APIENTRY * glWeightfvARBPROC) (GLint size, GLfloat *weights);
typedef void (APIENTRY * glWeightubvARBPROC) (GLint size, GLubyte *weights);
typedef void (APIENTRY * glWeightusvARBPROC) (GLint size, GLushort *weights);
typedef void (APIENTRY * glWeightuivARBPROC) (GLint size, GLuint *weights);
typedef void (APIENTRY * glWeightPointerARBPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glVertexBlendARBPROC) (GLint count);

static glWeightbvARBPROC glWeightbvARB;
static glWeightsvARBPROC glWeightsvARB;
static glWeightivARBPROC glWeightivARB;
static glWeightfvARBPROC glWeightfvARB;
static glWeightubvARBPROC glWeightubvARB;
static glWeightusvARBPROC glWeightusvARB;
static glWeightuivARBPROC glWeightuivARB;
static glWeightPointerARBPROC glWeightPointerARB;
static glVertexBlendARBPROC glVertexBlendARB;

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightbvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightbvARB
	(JNIEnv * env, jclass clazz, jint size, jobject pWeights, jint pWeights_offset)
{
	GLbyte *pWeights_ptr = (GLbyte *)env->GetDirectBufferAddress(pWeights) + pWeights_offset;
	glWeightbvARB(size, pWeights_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightfvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightfvARB
	(JNIEnv * env, jclass clazz, jint size, jobject pfWeights, jint pfWeights_offset)
{
	GLfloat *pfWeights_ptr = (GLfloat *)env->GetDirectBufferAddress(pfWeights) + pfWeights_offset;
	glWeightfvARB(size, pfWeights_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightivARB
	(JNIEnv * env, jclass clazz, jint size, jobject piWeights, jint piWeights_offset)
{
	GLint *piWeights_ptr = (GLint *)env->GetDirectBufferAddress(piWeights) + piWeights_offset;
	glWeightivARB(size, piWeights_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightsvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightsvARB
	(JNIEnv * env, jclass clazz, jint size, jobject psWeights, jint psWeights_offset)
{
	GLshort *psWeights_ptr = (GLshort *)env->GetDirectBufferAddress(psWeights) + psWeights_offset;
	glWeightsvARB(size, psWeights_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightubvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightubvARB
	(JNIEnv * env, jclass clazz, jint size, jobject pWeights, jint pWeights_offset)
{
	GLubyte *pWeights_ptr = (GLubyte *)env->GetDirectBufferAddress(pWeights) + pWeights_offset;
	glWeightubvARB(size, pWeights_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightuivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightuivARB
	(JNIEnv * env, jclass clazz, jint size, jobject piWeights, jint piWeights_offset)
{
	GLuint *piWeights_ptr = (GLuint *)env->GetDirectBufferAddress(piWeights) + piWeights_offset;
	glWeightuivARB(size, piWeights_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightusvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightusvARB
	(JNIEnv * env, jclass clazz, jint size, jobject psWeights, jint psWeights_offset)
{
	GLushort *psWeights_ptr = (GLushort *)env->GetDirectBufferAddress(psWeights) + psWeights_offset;
	glWeightusvARB(size, psWeights_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightPointerARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARB
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_offset)
{
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glWeightPointerARB(size, type, stride, pPointer_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	nglWeightPointerARBVBO
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARBVBO
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jint buffer_offset)
{
	glWeightPointerARB(size, type, stride, (GLvoid *)buffer_offset);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexBlend
 * Method:	glVertexBlendARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_glVertexBlendARB
	(JNIEnv * env, jclass clazz, jint count)
{
	glVertexBlendARB(count);
	
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglWeightbvARB", "(ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightbvARB, "glWeightbvARB", (void**)&glWeightbvARB},
		{"nglWeightfvARB", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightfvARB, "glWeightfvARB", (void**)&glWeightfvARB},
		{"nglWeightivARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightivARB, "glWeightivARB", (void**)&glWeightivARB},
		{"nglWeightsvARB", "(ILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightsvARB, "glWeightsvARB", (void**)&glWeightsvARB},
		{"nglWeightubvARB", "(ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightubvARB, "glWeightubvARB", (void**)&glWeightubvARB},
		{"nglWeightuivARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightuivARB, "glWeightuivARB", (void**)&glWeightuivARB},
		{"nglWeightusvARB", "(ILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightusvARB, "glWeightusvARB", (void**)&glWeightusvARB},
		{"nglWeightPointerARB", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARB, "glWeightPointerARB", (void**)&glWeightPointerARB},
		{"nglWeightPointerARBVBO", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARBVBO, NULL, NULL},
		{"glVertexBlendARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexBlend_glVertexBlendARB, "glVertexBlendARB", (void**)&glVertexBlendARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}

