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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVFragmentProgram
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVFragmentProgram.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glProgramNamedParameter4fNVPROC) (GLuint id, GLsizei len, const GLubyte *name, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramNamedParameter4fvNVPROC) (GLuint id, GLsizei len, const GLubyte *name, const GLfloat v[]);
typedef void (APIENTRY * glProgramLocalParameter4fARBPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramLocalParameter4fvARBPROC) (GLenum target, GLuint index, const GLfloat *params);
typedef void (APIENTRY * glGetProgramNamedParameterfvNVPROC) (GLuint id, GLsizei len, const GLubyte *name, GLfloat *params);
typedef void (APIENTRY * glGetProgramLocalParameterfvARBPROC) (GLenum target, GLuint index, GLfloat *params);

static glProgramNamedParameter4fNVPROC glProgramNamedParameter4fNV;
static glProgramNamedParameter4fvNVPROC glProgramNamedParameter4fvNV;
static glProgramLocalParameter4fARBPROC glProgramLocalParameter4fARB;
static glProgramLocalParameter4fvARBPROC glProgramLocalParameter4fvARB;
static glGetProgramNamedParameterfvNVPROC glGetProgramNamedParameterfvNV;
static glGetProgramLocalParameterfvARBPROC glGetProgramLocalParameterfvARB;

void extgl_InitNVFragmentProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_fragment_program)
		return;
	glProgramNamedParameter4fNV = (glProgramNamedParameter4fNVPROC) extgl_GetProcAddress("glProgramNamedParameter4fNV");
	glProgramNamedParameter4fvNV = (glProgramNamedParameter4fvNVPROC) extgl_GetProcAddress("glProgramNamedParameter4fvNV");
	glGetProgramNamedParameterfvNV = (glGetProgramNamedParameterfvNVPROC) extgl_GetProcAddress("glGetProgramNamedParameterfvNV");
	glProgramLocalParameter4fARB = (glProgramLocalParameter4fARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fARB");
	glProgramLocalParameter4fvARB = (glProgramLocalParameter4fvARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fvARB");
	glGetProgramLocalParameterfvARB = (glGetProgramLocalParameterfvARBPROC) extgl_GetProcAddress("glGetProgramLocalParameterfvARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_fragment_program)
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFragmentProgram
 * Method:	nglProgramNamedParameter4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFragmentProgram_nglProgramNamedParameter4fNV
	(JNIEnv * env, jclass clazz, jint id, jint length, jobject name, jint nameOffset, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glProgramNamedParameter4fNV)
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	glProgramNamedParameter4fNV(id, length, name_ptr, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFragmentProgram
 * Method:	nglGetProgramNamedParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFragmentProgram_nglGetProgramNamedParameterfvNV
	(JNIEnv * env, jclass clazz, jint id, jint length, jobject name, jint nameOffset, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetProgramNamedParameterfvNV)
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramNamedParameterfvNV(id, length, name_ptr, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFragmentProgram
 * Method:	glProgramLocalParameter4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFragmentProgram_glProgramLocalParameter4fARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glProgramLocalParameter4fARB)
	glProgramLocalParameter4fARB(target, index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFragmentProgram
 * Method:	nglGetProgramLocalParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFragmentProgram_nglGetProgramLocalParameterfvARB
	(JNIEnv * env, jclass clazz, jint target, jint index, jobject params, jint params_offset)
{
	CHECK_EXISTS(glGetProgramLocalParameterfvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + params_offset;
	glGetProgramLocalParameterfvARB(target, index, params_ptr);
	CHECK_GL_ERROR
}
