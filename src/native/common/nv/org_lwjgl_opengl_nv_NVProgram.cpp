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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVProgram
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVProgram.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glLoadProgramNVPROC) (GLenum target, GLuint id, GLsizei len, const GLubyte *program);
typedef void (APIENTRY * glBindProgramNVPROC) (GLenum target, GLuint id);
typedef void (APIENTRY * glDeleteProgramsNVPROC) (GLsizei n, const GLuint *ids);
typedef void (APIENTRY * glGenProgramsNVPROC) (GLsizei n, GLuint *ids);
typedef void (APIENTRY * glGetProgramStringNVPROC) (GLuint id, GLenum pname, GLubyte *program);
typedef GLboolean (APIENTRY * glIsProgramNVPROC) (GLuint id);
typedef GLboolean (APIENTRY * glAreProgramsResidentNVPROC) (GLsizei n, const GLuint *ids, GLboolean *residences);
typedef void (APIENTRY * glRequestResidentProgramsNVPROC) (GLsizei n, GLuint *ids);
typedef void (APIENTRY * glGetProgramivNVPROC) (GLuint id, GLenum pname, GLint *params);

static glLoadProgramNVPROC glLoadProgramNV;
static glBindProgramNVPROC glBindProgramNV;
static glDeleteProgramsNVPROC glDeleteProgramsNV;
static glGenProgramsNVPROC glGenProgramsNV;
static glGetProgramStringNVPROC glGetProgramStringNV;
static glIsProgramNVPROC glIsProgramNV;
static glAreProgramsResidentNVPROC glAreProgramsResidentNV;
static glRequestResidentProgramsNVPROC glRequestResidentProgramsNV;
static glGetProgramivNVPROC glGetProgramivNV;

void extgl_InitNVProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_vertex_program)
		return;
	glLoadProgramNV = (glLoadProgramNVPROC) extgl_GetProcAddress("glLoadProgramNV");
	glBindProgramNV = (glBindProgramNVPROC) extgl_GetProcAddress("glBindProgramNV");
	glDeleteProgramsNV = (glDeleteProgramsNVPROC) extgl_GetProcAddress("glDeleteProgramsNV");
	glGenProgramsNV = (glGenProgramsNVPROC) extgl_GetProcAddress("glGenProgramsNV");
	glGetProgramivNV = (glGetProgramivNVPROC) extgl_GetProcAddress("glGetProgramivNV");
	glGetProgramStringNV = (glGetProgramStringNVPROC) extgl_GetProcAddress("glGetProgramStringNV");
	glIsProgramNV = (glIsProgramNVPROC) extgl_GetProcAddress("glIsProgramNV");
	glAreProgramsResidentNV = (glAreProgramsResidentNVPROC) extgl_GetProcAddress("glAreProgramsResidentNV");
	glRequestResidentProgramsNV = (glRequestResidentProgramsNVPROC) extgl_GetProcAddress("glRequestResidentProgramsNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_vertex_program)
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglLoadProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglLoadProgramNV
	(JNIEnv * env, jclass clazz, jint target, jint programID, jint length, jobject string, jint stringOffset)
{
	CHECK_EXISTS(glLoadProgramNV)
	const GLubyte *string_ptr = (const GLubyte *)env->GetDirectBufferAddress(string) + stringOffset;
	glLoadProgramNV(target, programID, length, string_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	glBindProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_glBindProgramNV
	(JNIEnv * env, jclass clazz, jint target, jint programID)
{
	CHECK_EXISTS(glBindProgramNV)
	glBindProgramNV(target, programID);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglDeleteProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglDeleteProgramsNV
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	CHECK_EXISTS(glDeleteProgramsNV)
	GLuint *programs_ptr = (GLuint *)env->GetDirectBufferAddress(programs) + programsOffset;
	glDeleteProgramsNV(n, programs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglGenProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglGenProgramsNV
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	CHECK_EXISTS(glGenProgramsNV)
	GLuint *programs_ptr = (GLuint *)env->GetDirectBufferAddress(programs) + programsOffset;
	glGenProgramsNV(n, programs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglGetProgramivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglGetProgramivNV
	(JNIEnv * env, jclass clazz, jint programID, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetProgramivNV)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramivNV(programID, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglGetProgramStringNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglGetProgramStringNV
	(JNIEnv * env, jclass clazz, jint programID, jint parameterName, jobject paramString, jint paramStringOffset)
{
	CHECK_EXISTS(glGetProgramStringNV)
	GLubyte *paramString_ptr = (GLubyte *)env->GetDirectBufferAddress(paramString) + paramStringOffset;
	glGetProgramStringNV(programID, parameterName, paramString_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	glIsProgramNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_nv_NVProgram_glIsProgramNV
	(JNIEnv * env, jclass clazz, jint programID)
{
	CHECK_EXISTS(glIsProgramNV)
	GLboolean result = glIsProgramNV(programID);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglAreProgramsResidentNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglAreProgramsResidentNV
	(JNIEnv * env, jclass clazz, jint n, jobject programIDs, jint programIDsOffset, jobject programResidences, jint programResidencesOffset)
{
	CHECK_EXISTS(glAreProgramsResidentNV)
	GLuint *programIDs_ptr = (GLuint *)env->GetDirectBufferAddress(programIDs) + programIDsOffset;
	GLubyte *programResidences_ptr = (GLubyte *)env->GetDirectBufferAddress(programResidences) + programResidencesOffset;
	GLboolean result = glAreProgramsResidentNV(n, programIDs_ptr, programResidences_ptr);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglRequestResidentProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglRequestResidentProgramsNV
	(JNIEnv * env, jclass clazz, jint n, jobject programIDs, jint programIDsOffset)
{
	CHECK_EXISTS(glRequestResidentProgramsNV)
	GLuint *programIDs_ptr = (GLuint *)env->GetDirectBufferAddress(programIDs) + programIDsOffset;
	glRequestResidentProgramsNV(n, programIDs_ptr);
	CHECK_GL_ERROR
}
