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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBProgram
// ----------------------------------

#include "extgl.h"

typedef void (APIENTRY * glProgramStringARBPROC) (GLenum target, GLenum format, GLsizei len, const GLvoid *string);
typedef void (APIENTRY * glBindProgramARBPROC) (GLenum target, GLuint program);
typedef void (APIENTRY * glDeleteProgramsARBPROC) (GLsizei n, const GLuint *programs);
typedef void (APIENTRY * glGenProgramsARBPROC) (GLsizei n, GLuint *programs);
typedef void (APIENTRY * glProgramEnvParameter4fARBPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramEnvParameter4fvARBPROC) (GLenum target, GLuint index, const GLfloat *params);
typedef void (APIENTRY * glProgramLocalParameter4fARBPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramLocalParameter4fvARBPROC) (GLenum target, GLuint index, const GLfloat *params);
typedef void (APIENTRY * glGetProgramEnvParameterfvARBPROC) (GLenum target, GLuint index, GLfloat *params);
typedef void (APIENTRY * glGetProgramLocalParameterfvARBPROC) (GLenum target, GLuint index, GLfloat *params);
typedef void (APIENTRY * glGetProgramivARBPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetProgramStringARBPROC) (GLenum target, GLenum pname, GLvoid *string);
typedef GLboolean (APIENTRY * glIsProgramARBPROC) (GLuint program);

static glProgramStringARBPROC glProgramStringARB;
static glBindProgramARBPROC glBindProgramARB;
static glDeleteProgramsARBPROC glDeleteProgramsARB;
static glGenProgramsARBPROC glGenProgramsARB;

static glProgramEnvParameter4fARBPROC glProgramEnvParameter4fARB;
static glProgramEnvParameter4fvARBPROC glProgramEnvParameter4fvARB;
static glProgramLocalParameter4fARBPROC glProgramLocalParameter4fARB;
static glProgramLocalParameter4fvARBPROC glProgramLocalParameter4fvARB;
static glGetProgramEnvParameterfvARBPROC glGetProgramEnvParameterfvARB;
static glGetProgramLocalParameterfvARBPROC glGetProgramLocalParameterfvARB;
static glGetProgramivARBPROC glGetProgramivARB;
static glGetProgramStringARBPROC glGetProgramStringARB;
static glIsProgramARBPROC glIsProgramARB;

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglProgramStringARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglProgramStringARB
	(JNIEnv * env, jclass clazz, jint target, jint format, jint length, jobject string, jint stringOffset)
{
	GLvoid *string_ptr = (GLvoid *)((GLubyte *)(*env)->GetDirectBufferAddress(env, string) + stringOffset);
	glProgramStringARB(target, format, length, string_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	glBindProgramARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_glBindProgramARB
	(JNIEnv * env, jclass clazz, jint target, jint program)
{
	glBindProgramARB(target, program);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglDeleteProgramsARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglDeleteProgramsARB
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	GLuint *programs_ptr = (GLuint *)(*env)->GetDirectBufferAddress(env, programs) + programsOffset;
	glDeleteProgramsARB(n, programs_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglGenProgramsARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGenProgramsARB
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	GLuint *programs_ptr = (GLuint *)(*env)->GetDirectBufferAddress(env, programs) + programsOffset;
	glGenProgramsARB(n, programs_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	glProgramEnvParameter4fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_glProgramEnvParameter4fARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glProgramEnvParameter4fARB(target, index, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglProgramEnvParameter4fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglProgramEnvParameter4fvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, params) + paramsOffset;
	glProgramEnvParameter4fvARB(target, index, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	glProgramLocalParameter4fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_glProgramLocalParameter4fARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glProgramLocalParameter4fARB(target, index, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglProgramLocalParameter4fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglProgramLocalParameter4fvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, params) + paramsOffset;
	glProgramLocalParameter4fvARB(target, index, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglGetProgramEnvParameterfvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramEnvParameterfvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, params) + paramsOffset;
	glGetProgramEnvParameterfvARB(target, index, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglGetProgramLocalParameterfvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramLocalParameterfvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, params) + paramsOffset;
	glGetProgramLocalParameterfvARB(target, index, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglGetProgramivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramivARB
	(JNIEnv * env, jclass clazz, jint target, jint parameterName, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)(*env)->GetDirectBufferAddress(env, params) + paramsOffset;
	glGetProgramivARB(target, parameterName, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	nglGetProgramStringARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBProgram_nglGetProgramStringARB
	(JNIEnv * env, jclass clazz, jint target, jint parameterName, jobject paramString, jint paramStringOffset)
{
	GLvoid *paramString_ptr = (GLvoid *)((GLubyte *)(*env)->GetDirectBufferAddress(env, paramString) + paramStringOffset);
	glGetProgramStringARB(target, parameterName, paramString_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBProgram
 * Method:	glIsProgramARB
 */
static jboolean JNICALL Java_org_lwjgl_opengl_ARBProgram_glIsProgramARB
	(JNIEnv * env, jclass clazz, jint program)
{
	GLboolean result = glIsProgramARB(program);
	
	return result;
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBProgram_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglProgramStringARB", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglProgramStringARB, "glProgramStringARB", (void*)&glProgramStringARB},
		{"glBindProgramARB", "(II)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_glBindProgramARB, "glBindProgramARB", (void*)&glBindProgramARB},
		{"nglDeleteProgramsARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglDeleteProgramsARB, "glDeleteProgramsARB", (void*)&glDeleteProgramsARB},
		{"nglGenProgramsARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglGenProgramsARB, "glGenProgramsARB", (void*)&glGenProgramsARB},
		{"glProgramEnvParameter4fARB", "(IIFFFF)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_glProgramEnvParameter4fARB, "glProgramEnvParameter4fARB", (void*)&glProgramEnvParameter4fARB},
		{"nglProgramEnvParameter4fvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglProgramEnvParameter4fvARB, "glProgramEnvParameter4fvARB", (void*)&glProgramEnvParameter4fvARB},
		{"glProgramLocalParameter4fARB", "(IIFFFF)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_glProgramLocalParameter4fARB, "glProgramLocalParameter4fARB", (void*)&glProgramLocalParameter4fARB},
		{"nglProgramLocalParameter4fvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglProgramLocalParameter4fvARB, "glProgramLocalParameter4fvARB", (void*)&glProgramLocalParameter4fvARB},
		{"nglGetProgramEnvParameterfvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramEnvParameterfvARB, "glGetProgramEnvParameterfvARB", (void*)&glGetProgramEnvParameterfvARB},
		{"nglGetProgramLocalParameterfvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramLocalParameterfvARB, "glGetProgramLocalParameterfvARB", (void*)&glGetProgramLocalParameterfvARB},
		{"nglGetProgramivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramivARB, "glGetProgramivARB", (void*)&glGetProgramivARB},
		{"nglGetProgramStringARB", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBProgram_nglGetProgramStringARB, "glGetProgramStringARB", (void*)&glGetProgramStringARB},
		{"glIsProgramARB", "(I)Z", (void*)&Java_org_lwjgl_opengl_ARBProgram_glIsProgramARB, "glIsProgramARB", (void*)&glIsProgramARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef _cplusplus
}
#endif

