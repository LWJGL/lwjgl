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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVPrimitiveRestart
// ----------------------------------

#include "extgl.h"


typedef void (APIENTRY * glPrimitiveRestartNVPROC) (GLvoid);
typedef void (APIENTRY * glPrimitiveRestartIndexNVPROC) (GLuint index);

static glPrimitiveRestartNVPROC glPrimitiveRestartNV;
static glPrimitiveRestartIndexNVPROC glPrimitiveRestartIndexNV;

/*
 * Class:	org.lwjgl.opengl.NVPrimitiveRestart
 * Method:	glPrimitiveRestartNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartNV
	(JNIEnv * env, jclass clazz)
{
	glPrimitiveRestartNV();
	
}

/*
 * Class:	org.lwjgl.opengl.NVPrimitiveRestart
 * Method:	glPrimitiveRestartIndexNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartIndexNV
	(JNIEnv * env, jclass clazz, jint index)
{
	glPrimitiveRestartIndexNV(index);
	
}

void extgl_InitNVPrimitiveRestart(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"glPrimitiveRestartNV", "()V", (void*)&Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartNV, "glPrimitiveRestartNV", (void**)&glPrimitiveRestartNV},
		{"glPrimitiveRestartIndexNV", "(I)V", (void*)&Java_org_lwjgl_opengl_NVPrimitiveRestart_glPrimitiveRestartIndexNV, "glPrimitiveRestartIndexNV", (void**)&glPrimitiveRestartIndexNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/NVPrimitiveRestart");
	if (extgl_Extensions.GL_NV_primitive_restart)
		extgl_InitializeClass(env, clazz, ext_set, "GL_NV_primitive_restart", num_functions, functions);
}
