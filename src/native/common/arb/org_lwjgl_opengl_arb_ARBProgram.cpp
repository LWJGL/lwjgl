/*
* Copyright (c) 2002 Lightweight Java Game Library Project
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

// ----------------------------------
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.arb.ARBProgram
// ----------------------------------

#include "org_lwjgl_opengl_arb_ARBProgram.h"
#include "extgl.h"
#include "checkGLerror.h"

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

void extgl_InitARBProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_fragment_program)
		return;
	glProgramStringARB = (glProgramStringARBPROC) extgl_GetProcAddress("glProgramStringARB");
	glBindProgramARB = (glBindProgramARBPROC) extgl_GetProcAddress("glBindProgramARB");
	glDeleteProgramsARB = (glDeleteProgramsARBPROC) extgl_GetProcAddress("glDeleteProgramsARB");
	glGenProgramsARB = (glGenProgramsARBPROC) extgl_GetProcAddress("glGenProgramsARB");
	glProgramEnvParameter4fARB = (glProgramEnvParameter4fARBPROC) extgl_GetProcAddress("glProgramEnvParameter4fARB");
	glProgramEnvParameter4fvARB = (glProgramEnvParameter4fvARBPROC) extgl_GetProcAddress("glProgramEnvParameter4fvARB");
	glProgramLocalParameter4fARB = (glProgramLocalParameter4fARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fARB");
	glProgramLocalParameter4fvARB = (glProgramLocalParameter4fvARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fvARB");
	glGetProgramEnvParameterfvARB = (glGetProgramEnvParameterfvARBPROC) extgl_GetProcAddress("glGetProgramEnvParameterfvARB");
	glGetProgramLocalParameterfvARB = (glGetProgramLocalParameterfvARBPROC) extgl_GetProcAddress("glGetProgramLocalParameterfvARB");
	glGetProgramivARB = (glGetProgramivARBPROC) extgl_GetProcAddress("glGetProgramivARB");
	glGetProgramStringARB = (glGetProgramStringARBPROC) extgl_GetProcAddress("glGetProgramStringARB");
	glIsProgramARB = (glIsProgramARBPROC) extgl_GetProcAddress("glIsProgramARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_fragment_program)
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglProgramStringARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglProgramStringARB
	(JNIEnv * env, jclass clazz, jint target, jint format, jint length, jobject string, jint stringOffset)
{
	CHECK_EXISTS(glProgramStringARB)
	GLvoid *string_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(string) + stringOffset);
	glProgramStringARB(target, format, length, string_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	glBindProgramARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_glBindProgramARB
	(JNIEnv * env, jclass clazz, jint target, jint program)
{
	CHECK_EXISTS(glBindProgramARB)
	glBindProgramARB(target, program);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglDeleteProgramsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglDeleteProgramsARB
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	CHECK_EXISTS(glDeleteProgramsARB)
	GLuint *programs_ptr = (GLuint *)env->GetDirectBufferAddress(programs) + programsOffset;
	glDeleteProgramsARB(n, programs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglGenProgramsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglGenProgramsARB
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	CHECK_EXISTS(glGenProgramsARB)
	GLuint *programs_ptr = (GLuint *)env->GetDirectBufferAddress(programs) + programsOffset;
	glGenProgramsARB(n, programs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	glProgramEnvParameter4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_glProgramEnvParameter4fARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glProgramEnvParameter4fARB)
	glProgramEnvParameter4fARB(target, index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglProgramEnvParameter4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglProgramEnvParameter4fvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glProgramEnvParameter4fvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glProgramEnvParameter4fvARB(target, index, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	glProgramLocalParameter4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_glProgramLocalParameter4fARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glProgramLocalParameter4fARB)
	glProgramLocalParameter4fARB(target, index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglProgramLocalParameter4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglProgramLocalParameter4fvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glProgramLocalParameter4fvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glProgramLocalParameter4fvARB(target, index, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglGetProgramEnvParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglGetProgramEnvParameterfvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetProgramEnvParameterfvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramEnvParameterfvARB(target, index, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglGetProgramLocalParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglGetProgramLocalParameterfvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetProgramLocalParameterfvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramLocalParameterfvARB(target, index, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglGetProgramivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglGetProgramivARB
	(JNIEnv * env, jclass clazz, jint target, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetProgramivARB)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramivARB(target, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	nglGetProgramStringARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_nglGetProgramStringARB
	(JNIEnv * env, jclass clazz, jint target, jint parameterName, jobject paramString, jint paramStringOffset)
{
	CHECK_EXISTS(glGetProgramStringARB)
	GLvoid *paramString_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(paramString) + paramStringOffset);
	glGetProgramStringARB(target, parameterName, paramString_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBProgram
 * Method:	glIsProgramARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_arb_ARBProgram_glIsProgramARB
	(JNIEnv * env, jclass clazz, jint program)
{
	CHECK_EXISTS(glIsProgramARB)
	GLboolean result = glIsProgramARB(program);
	CHECK_GL_ERROR
	return result;
}
