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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVFragmentProgram
// ----------------------------------

#include "extgl.h"

typedef void (APIENTRY * glProgramNamedParameter4fNVPROC) (GLuint id, GLsizei len, const GLubyte *name, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glGetProgramNamedParameterfvNVPROC) (GLuint id, GLsizei len, const GLubyte *name, GLfloat *params);

static glProgramNamedParameter4fNVPROC glProgramNamedParameter4fNV;
static glGetProgramNamedParameterfvNVPROC glGetProgramNamedParameterfvNV;

/*
 * Class:	org.lwjgl.opengl.NVFragmentProgram
 * Method:	nglProgramNamedParameter4fNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVFragmentProgram_nglProgramNamedParameter4fNV
	(JNIEnv * env, jclass clazz, jint id, jint length, jobject name, jint nameOffset, jfloat x, jfloat y, jfloat z, jfloat w)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	glProgramNamedParameter4fNV(id, length, name_ptr, x, y, z, w);
}

/*
 * Class:	org.lwjgl.opengl.NVFragmentProgram
 * Method:	nglGetProgramNamedParameterfvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVFragmentProgram_nglGetProgramNamedParameterfvNV
	(JNIEnv * env, jclass clazz, jint id, jint length, jobject name, jint nameOffset, jobject params, jint paramsOffset)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramNamedParameterfvNV(id, length, name_ptr, params_ptr);
}

void extgl_InitNVFragmentProgram(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"nglProgramNamedParameter4fNV", "(IILjava/nio/ByteBuffer;IFFFF)V", (void*)&Java_org_lwjgl_opengl_NVFragmentProgram_nglProgramNamedParameter4fNV, "glProgramNamedParameter4fNV", (void**)&glProgramNamedParameter4fNV},
		{"nglGetProgramNamedParameterfvNV", "(IILjava/nio/ByteBuffer;ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVFragmentProgram_nglGetProgramNamedParameterfvNV, "glGetProgramNamedParameterfvNV", (void**)&glGetProgramNamedParameterfvNV},
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/NVFragmentProgram");
	if (extgl_Extensions.GL_NV_fragment_program)
		extgl_InitializeClass(env, clazz, ext_set, "GL_NV_fragment_program", num_functions, functions);
}
