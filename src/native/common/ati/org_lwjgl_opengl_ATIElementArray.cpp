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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ATIElementArray
// ----------------------------------

#include "extgl.h"


typedef void (APIENTRY * glElementPointerATIPROC) (GLenum type, const GLvoid *pointer);
typedef void (APIENTRY * glDrawElementArrayATIPROC) (GLenum mode, GLsizei count);
typedef void (APIENTRY * glDrawRangeElementArrayATIPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count);

static glElementPointerATIPROC glElementPointerATI;
static glDrawElementArrayATIPROC glDrawElementArrayATI;
static glDrawRangeElementArrayATIPROC glDrawRangeElementArrayATI;

/*
 * Class:	org.lwjgl.opengl.ATIElementArray
 * Method:	nglElementPointerATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATI
	(JNIEnv * env, jclass clazz, jint type, jobject pPointer, jint pPointer_offset)
{
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glElementPointerATI(type, pPointer_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIElementArray
 * Method:	nglElementPointerATIVBO
 */
static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATIVBO
	(JNIEnv * env, jclass clazz, jint type, jint buffer_offset)
{
	glElementPointerATI(type, (GLvoid *)buffer_offset);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIElementArray
 * Method:	glDrawElementArrayATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_glDrawElementArrayATI
	(JNIEnv * env, jclass clazz, jint mode, jint count)
{
	glDrawElementArrayATI(mode, count);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIElementArray
 * Method:	glDrawRangeElementArrayATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIElementArray_glDrawRangeElementArrayATI
	(JNIEnv * env, jclass clazz, jint mode, jint start, jint end, jint count)
{
	glDrawRangeElementArrayATI(mode, start, end, count);
	
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIElementArray_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglElementPointerATI", "(ILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATI, "glElementPointerATI", (void**)&glElementPointerATI},
		{"nglElementPointerATIVBO", "(II)V", (void*)&Java_org_lwjgl_opengl_ATIElementArray_nglElementPointerATIVBO, NULL, NULL},
		{"glDrawElementArrayATI", "(II)V", (void*)&Java_org_lwjgl_opengl_ATIElementArray_glDrawElementArrayATI, "glDrawElementArrayATI", (void**)&glDrawElementArrayATI},
		{"glDrawRangeElementArrayATI", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ATIElementArray_glDrawRangeElementArrayATI, "glDrawRangeElementArrayATI", (void**)&glDrawRangeElementArrayATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}

