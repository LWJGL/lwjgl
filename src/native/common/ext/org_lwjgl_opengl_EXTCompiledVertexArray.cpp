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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.EXTCompiledVertexArray
// ----------------------------------

#include "extgl.h"


typedef void (APIENTRY * glLockArraysEXTPROC) (GLint first, GLsizei count);
typedef void (APIENTRY * glUnlockArraysEXTPROC) ();

static glLockArraysEXTPROC glLockArraysEXT;
static glUnlockArraysEXTPROC glUnlockArraysEXT;

/*
 * Class:	org.lwjgl.opengl.EXTCompiledVertexArray
 * Method:	glLockArraysEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTCompiledVertexArray_glLockArraysEXT
	(JNIEnv * env, jclass clazz, jint first, jint count)
{
	glLockArraysEXT(first, count);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTCompiledVertexArray
 * Method:	glUnlockArraysEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTCompiledVertexArray_glUnlockArraysEXT
	(JNIEnv * env, jclass clazz)
{
	glUnlockArraysEXT();
	
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTCompiledVertexArray_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glLockArraysEXT", "(II)V", (void*)&Java_org_lwjgl_opengl_EXTCompiledVertexArray_glLockArraysEXT, "glLockArraysEXT", (void**)&glLockArraysEXT},
		{"glUnlockArraysEXT", "()V", (void*)&Java_org_lwjgl_opengl_EXTCompiledVertexArray_glUnlockArraysEXT, "glUnlockArraysEXT", (void**)&glUnlockArraysEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}

