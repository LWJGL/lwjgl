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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVProgram
// ----------------------------------

#include "extgl.h"


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

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	nglLoadProgramNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglLoadProgramNV
	(JNIEnv * env, jclass clazz, jint target, jint programID, jint length, jobject string, jint stringOffset)
{
	const GLubyte *string_ptr = (const GLubyte *)env->GetDirectBufferAddress(string) + stringOffset;
	glLoadProgramNV(target, programID, length, string_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	glBindProgramNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVProgram_glBindProgramNV
	(JNIEnv * env, jclass clazz, jint target, jint programID)
{
	glBindProgramNV(target, programID);
	
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	nglDeleteProgramsNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglDeleteProgramsNV
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	GLuint *programs_ptr = (GLuint *)env->GetDirectBufferAddress(programs) + programsOffset;
	glDeleteProgramsNV(n, programs_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	nglGenProgramsNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglGenProgramsNV
	(JNIEnv * env, jclass clazz, jint n, jobject programs, jint programsOffset)
{
	GLuint *programs_ptr = (GLuint *)env->GetDirectBufferAddress(programs) + programsOffset;
	glGenProgramsNV(n, programs_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	nglGetProgramivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglGetProgramivNV
	(JNIEnv * env, jclass clazz, jint programID, jint parameterName, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramivNV(programID, parameterName, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	nglGetProgramStringNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglGetProgramStringNV
	(JNIEnv * env, jclass clazz, jint programID, jint parameterName, jobject paramString, jint paramStringOffset)
{
	GLubyte *paramString_ptr = (GLubyte *)env->GetDirectBufferAddress(paramString) + paramStringOffset;
	glGetProgramStringNV(programID, parameterName, paramString_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	glIsProgramNV
 */
static jboolean JNICALL Java_org_lwjgl_opengl_NVProgram_glIsProgramNV
	(JNIEnv * env, jclass clazz, jint programID)
{
	GLboolean result = glIsProgramNV(programID);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	nglAreProgramsResidentNV
 */
static jboolean JNICALL Java_org_lwjgl_opengl_NVProgram_nglAreProgramsResidentNV
	(JNIEnv * env, jclass clazz, jint n, jobject programIDs, jint programIDsOffset, jobject programResidences, jint programResidencesOffset)
{
	GLuint *programIDs_ptr = (GLuint *)env->GetDirectBufferAddress(programIDs) + programIDsOffset;
	GLubyte *programResidences_ptr = (GLubyte *)env->GetDirectBufferAddress(programResidences) + programResidencesOffset;
	GLboolean result = glAreProgramsResidentNV(n, programIDs_ptr, programResidences_ptr);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.NVProgram
 * Method:	nglRequestResidentProgramsNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVProgram_nglRequestResidentProgramsNV
	(JNIEnv * env, jclass clazz, jint n, jobject programIDs, jint programIDsOffset)
{
	GLuint *programIDs_ptr = (GLuint *)env->GetDirectBufferAddress(programIDs) + programIDsOffset;
	glRequestResidentProgramsNV(n, programIDs_ptr);
	
}

void extgl_InitNVProgram(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"nglLoadProgramNV", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_NVProgram_nglLoadProgramNV, "glLoadProgramNV", (void**)&glLoadProgramNV},
		{"glBindProgramNV", "(II)V", (void*)&Java_org_lwjgl_opengl_NVProgram_glBindProgramNV, "glBindProgramNV", (void**)&glBindProgramNV},
		{"nglDeleteProgramsNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVProgram_nglDeleteProgramsNV, "glDeleteProgramsNV", (void**)&glDeleteProgramsNV},
		{"nglGenProgramsNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVProgram_nglGenProgramsNV, "glGenProgramsNV", (void**)&glGenProgramsNV},
		{"nglGetProgramivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVProgram_nglGetProgramivNV, "glGetProgramivNV", (void**)&glGetProgramivNV},
		{"nglGetProgramStringNV", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_NVProgram_nglGetProgramStringNV, "glGetProgramStringNV", (void**)&glGetProgramStringNV},
		{"glIsProgramNV", "(I)Z", (void*)&Java_org_lwjgl_opengl_NVProgram_glIsProgramNV, "glIsProgramNV", (void**)&glIsProgramNV},
		{"nglAreProgramsResidentNV", "(ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)Z", (void*)&Java_org_lwjgl_opengl_NVProgram_nglAreProgramsResidentNV, "glAreProgramsResidentNV", (void**)&glAreProgramsResidentNV},
		{"nglRequestResidentProgramsNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVProgram_nglRequestResidentProgramsNV, "glRequestResidentProgramsNV", (void**)&glRequestResidentProgramsNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/NVProgram");
	extgl_InitializeClass(env, clazz, NULL, "<NVProgram>", num_functions, functions);
}
