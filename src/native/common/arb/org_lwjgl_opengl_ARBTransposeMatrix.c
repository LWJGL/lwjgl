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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBTransposeMatrix
// ----------------------------------

#include "extgl.h"

typedef void (APIENTRY * glLoadTransposeMatrixfARBPROC) (const GLfloat m[16] );
typedef void (APIENTRY * glMultTransposeMatrixfARBPROC) (const GLfloat m[16] );

static glLoadTransposeMatrixfARBPROC glLoadTransposeMatrixfARB;
static glMultTransposeMatrixfARBPROC glMultTransposeMatrixfARB;

/*
 * Class:	org.lwjgl.opengl.ARBTransposeMatrix
 * Method:	nglLoadTransposeMatrixfARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBTransposeMatrix_nglLoadTransposeMatrixfARB
	(JNIEnv * env, jclass clazz, jobject pfMtx, jint pfMtx_offset)
{
	GLfloat *pfMtx_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, pfMtx) + pfMtx_offset;
	glLoadTransposeMatrixfARB(pfMtx_ptr);
}

/*
 * Class:	org.lwjgl.opengl.ARBTransposeMatrix
 * Method:	nglMultTransposeMatrixfARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBTransposeMatrix_nglMultTransposeMatrixfARB
	(JNIEnv * env, jclass clazz, jobject pfMtx, jint pfMtx_offset)
{
	GLfloat *pfMtx_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, pfMtx) + pfMtx_offset;
	glMultTransposeMatrixfARB(pfMtx_ptr);
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBTransposeMatrix_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglLoadTransposeMatrixfARB", "(Ljava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBTransposeMatrix_nglLoadTransposeMatrixfARB, "glLoadTransposeMatrixfARB", (void*)&glLoadTransposeMatrixfARB},
		{"nglMultTransposeMatrixfARB", "(Ljava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBTransposeMatrix_nglMultTransposeMatrixfARB, "glMultTransposeMatrixfARB", (void*)&glMultTransposeMatrixfARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif

