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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVVertexProgram
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVVertexProgram.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glExecuteProgramNVPROC) (GLenum target, GLuint id, const GLfloat *params);
typedef void (APIENTRY * glGetProgramParameterfvNVPROC) (GLenum target, GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTrackMatrixivNVPROC) (GLenum target, GLuint address, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribfvNVPROC) (GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVertexAttribivNVPROC) (GLuint index, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribPointervNVPROC) (GLuint index, GLenum pname, GLvoid **pointer);
typedef void (APIENTRY * glProgramParameter4fNVPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramParameter4fvNVPROC) (GLenum target, GLuint index, const GLfloat *params);
typedef void (APIENTRY * glProgramParameters4fvNVPROC) (GLenum target, GLuint index, GLuint num, const GLfloat *params);
typedef void (APIENTRY * glTrackMatrixNVPROC) (GLenum target, GLuint address, GLenum matrix, GLenum transform);
typedef void (APIENTRY * glVertexAttribPointerNVPROC) (GLuint index, GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glVertexAttrib1sNVPROC) (GLuint index, GLshort x);
typedef void (APIENTRY * glVertexAttrib1fNVPROC) (GLuint index, GLfloat x);
typedef void (APIENTRY * glVertexAttrib2sNVPROC) (GLuint index, GLshort x, GLshort y);
typedef void (APIENTRY * glVertexAttrib2fNVPROC) (GLuint index, GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertexAttrib3sNVPROC) (GLuint index, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertexAttrib3fNVPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertexAttrib4sNVPROC) (GLuint index, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertexAttrib4fNVPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertexAttrib4ubNVPROC) (GLuint index, GLubyte x, GLubyte y, GLubyte z, GLubyte w);
typedef void (APIENTRY * glVertexAttrib1svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib1fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib2svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib2fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib3svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib3fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib4svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib4fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib4ubvNVPROC) (GLuint index, const GLubyte *v);
typedef void (APIENTRY * glVertexAttribs1svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs1fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs2svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs2fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs3svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs3fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs4svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs4fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs4ubvNVPROC) (GLuint index, GLsizei n, const GLubyte *v);

static glExecuteProgramNVPROC glExecuteProgramNV;
static glGetProgramParameterfvNVPROC glGetProgramParameterfvNV;
static glGetTrackMatrixivNVPROC glGetTrackMatrixivNV;
static glGetVertexAttribfvNVPROC glGetVertexAttribfvNV;
static glGetVertexAttribivNVPROC glGetVertexAttribivNV;
static glGetVertexAttribPointervNVPROC glGetVertexAttribPointervNV;
static glProgramParameter4fNVPROC glProgramParameter4fNV;
static glProgramParameter4fvNVPROC glProgramParameter4fvNV;
static glProgramParameters4fvNVPROC glProgramParameters4fvNV;
static glTrackMatrixNVPROC glTrackMatrixNV;
static glVertexAttribPointerNVPROC glVertexAttribPointerNV;
static glVertexAttrib1sNVPROC glVertexAttrib1sNV;
static glVertexAttrib1fNVPROC glVertexAttrib1fNV;
static glVertexAttrib2sNVPROC glVertexAttrib2sNV;
static glVertexAttrib2fNVPROC glVertexAttrib2fNV;
static glVertexAttrib3sNVPROC glVertexAttrib3sNV;
static glVertexAttrib3fNVPROC glVertexAttrib3fNV;
static glVertexAttrib4sNVPROC glVertexAttrib4sNV;
static glVertexAttrib4fNVPROC glVertexAttrib4fNV;
static glVertexAttrib4ubNVPROC glVertexAttrib4ubNV;
static glVertexAttrib1svNVPROC glVertexAttrib1svNV;
static glVertexAttrib1fvNVPROC glVertexAttrib1fvNV;
static glVertexAttrib2svNVPROC glVertexAttrib2svNV;
static glVertexAttrib2fvNVPROC glVertexAttrib2fvNV;
static glVertexAttrib3svNVPROC glVertexAttrib3svNV;
static glVertexAttrib3fvNVPROC glVertexAttrib3fvNV;
static glVertexAttrib4svNVPROC glVertexAttrib4svNV;
static glVertexAttrib4fvNVPROC glVertexAttrib4fvNV;
static glVertexAttrib4ubvNVPROC glVertexAttrib4ubvNV;
static glVertexAttribs1svNVPROC glVertexAttribs1svNV;
static glVertexAttribs1fvNVPROC glVertexAttribs1fvNV;
static glVertexAttribs2svNVPROC glVertexAttribs2svNV;
static glVertexAttribs2fvNVPROC glVertexAttribs2fvNV;
static glVertexAttribs3svNVPROC glVertexAttribs3svNV;
static glVertexAttribs3fvNVPROC glVertexAttribs3fvNV;
static glVertexAttribs4svNVPROC glVertexAttribs4svNV;
static glVertexAttribs4fvNVPROC glVertexAttribs4fvNV;
static glVertexAttribs4ubvNVPROC glVertexAttribs4ubvNV;

void extgl_InitNVVertexProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_vertex_program)
		return;
	glExecuteProgramNV = (glExecuteProgramNVPROC) extgl_GetProcAddress("glExecuteProgramNV");
	glGetProgramParameterfvNV = (glGetProgramParameterfvNVPROC) extgl_GetProcAddress("glGetProgramParameterfvNV");
	glGetTrackMatrixivNV = (glGetTrackMatrixivNVPROC) extgl_GetProcAddress("glGetTrackMatrixivNV");
	glGetVertexAttribfvNV = (glGetVertexAttribfvNVPROC) extgl_GetProcAddress("glGetVertexAttribfvNV");
	glGetVertexAttribivNV = (glGetVertexAttribivNVPROC) extgl_GetProcAddress("glGetVertexAttribivNV");
	glGetVertexAttribPointervNV = (glGetVertexAttribPointervNVPROC) extgl_GetProcAddress("glGetVertexAttribPointervNV");
	glProgramParameter4fNV = (glProgramParameter4fNVPROC) extgl_GetProcAddress("glProgramParameter4fNV");
	glProgramParameter4fvNV = (glProgramParameter4fvNVPROC) extgl_GetProcAddress("glProgramParameter4fvNV");
	glProgramParameters4fvNV = (glProgramParameters4fvNVPROC) extgl_GetProcAddress("glProgramParameters4fvNV");
	glTrackMatrixNV = (glTrackMatrixNVPROC) extgl_GetProcAddress("glTrackMatrixNV");
	glVertexAttribPointerNV = (glVertexAttribPointerNVPROC) extgl_GetProcAddress("glVertexAttribPointerNV");
	glVertexAttrib1sNV = (glVertexAttrib1sNVPROC) extgl_GetProcAddress("glVertexAttrib1sNV");
	glVertexAttrib1fNV = (glVertexAttrib1fNVPROC) extgl_GetProcAddress("glVertexAttrib1fNV");
	glVertexAttrib2sNV = (glVertexAttrib2sNVPROC) extgl_GetProcAddress("glVertexAttrib2sNV");
	glVertexAttrib2fNV = (glVertexAttrib2fNVPROC) extgl_GetProcAddress("glVertexAttrib2fNV");
	glVertexAttrib3sNV = (glVertexAttrib3sNVPROC) extgl_GetProcAddress("glVertexAttrib3sNV");
	glVertexAttrib3fNV = (glVertexAttrib3fNVPROC) extgl_GetProcAddress("glVertexAttrib3fNV");
	glVertexAttrib4sNV = (glVertexAttrib4sNVPROC) extgl_GetProcAddress("glVertexAttrib4sNV");
	glVertexAttrib4fNV = (glVertexAttrib4fNVPROC) extgl_GetProcAddress("glVertexAttrib4fNV");
	glVertexAttrib4ubNV = (glVertexAttrib4ubNVPROC) extgl_GetProcAddress("glVertexAttrib4ubNV");
	glVertexAttrib1svNV = (glVertexAttrib1svNVPROC) extgl_GetProcAddress("glVertexAttrib1svNV");
	glVertexAttrib1fvNV = (glVertexAttrib1fvNVPROC) extgl_GetProcAddress("glVertexAttrib1fvNV");
	glVertexAttrib2svNV = (glVertexAttrib2svNVPROC) extgl_GetProcAddress("glVertexAttrib2svNV");
	glVertexAttrib2fvNV = (glVertexAttrib2fvNVPROC) extgl_GetProcAddress("glVertexAttrib2fvNV");
	glVertexAttrib3svNV = (glVertexAttrib3svNVPROC) extgl_GetProcAddress("glVertexAttrib3svNV");
	glVertexAttrib3fvNV = (glVertexAttrib3fvNVPROC) extgl_GetProcAddress("glVertexAttrib3fvNV");
	glVertexAttrib4svNV = (glVertexAttrib4svNVPROC) extgl_GetProcAddress("glVertexAttrib4svNV");
	glVertexAttrib4fvNV = (glVertexAttrib4fvNVPROC) extgl_GetProcAddress("glVertexAttrib4fvNV");
	glVertexAttrib4ubvNV = (glVertexAttrib4ubvNVPROC) extgl_GetProcAddress("glVertexAttrib4ubvNV");
	glVertexAttribs1svNV = (glVertexAttribs1svNVPROC) extgl_GetProcAddress("glVertexAttribs1svNV");
	glVertexAttribs1fvNV = (glVertexAttribs1fvNVPROC) extgl_GetProcAddress("glVertexAttribs1fvNV");
	glVertexAttribs2svNV = (glVertexAttribs2svNVPROC) extgl_GetProcAddress("glVertexAttribs2svNV");
	glVertexAttribs2fvNV = (glVertexAttribs2fvNVPROC) extgl_GetProcAddress("glVertexAttribs2fvNV");
	glVertexAttribs3svNV = (glVertexAttribs3svNVPROC) extgl_GetProcAddress("glVertexAttribs3svNV");
	glVertexAttribs3fvNV = (glVertexAttribs3fvNVPROC) extgl_GetProcAddress("glVertexAttribs3fvNV");
	glVertexAttribs4svNV = (glVertexAttribs4svNVPROC) extgl_GetProcAddress("glVertexAttribs4svNV");
	glVertexAttribs4fvNV = (glVertexAttribs4fvNVPROC) extgl_GetProcAddress("glVertexAttribs4fvNV");
	glVertexAttribs4ubvNV = (glVertexAttribs4ubvNVPROC) extgl_GetProcAddress("glVertexAttribs4ubvNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_vertex_program)
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglExecuteProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglExecuteProgramNV
	(JNIEnv * env, jclass clazz, jint target, jint id, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glExecuteProgramNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glExecuteProgramNV(target, id, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetProgramParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetProgramParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetProgramParameterfvNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramParameterfvNV(target, index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetTrackMatrixivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetTrackMatrixivNV
	(JNIEnv * env, jclass clazz, jint target, jint address, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetTrackMatrixivNV)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetTrackMatrixivNV(target, address, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetVertexAttribfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetVertexAttribfvNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribfvNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribfvNV(index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetVertexAttribivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetVertexAttribivNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribivNV)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribivNV(index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glGetVertexAttribPointerNV
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glGetVertexAttribPointerNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jint size)
{
        CHECK_EXISTS(glGetVertexAttribPointervNV)
        void *address;
        glGetVertexAttribPointervNV((GLuint)index, (GLuint)parameterName, &address);
        CHECK_GL_ERROR
        return safeNewBuffer(env, address, size);
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glProgramParameter4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glProgramParameter4fNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glProgramParameter4fNV)
	glProgramParameter4fNV(target, index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglProgramParameters4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglProgramParameters4fvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint count, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glProgramParameters4fvNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glProgramParameters4fvNV(target, index, count, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glTrackMatrixNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glTrackMatrixNV
	(JNIEnv * env, jclass clazz, jint target, jint address, jint matrix, jint transform)
{
	CHECK_EXISTS(glTrackMatrixNV)
	glTrackMatrixNV(target, address, matrix, transform);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglVertexAttribPointerNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglVertexAttribPointerNV
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jint stride, jobject buffer, jint bufferOffset)
{
	CHECK_EXISTS(glVertexAttribPointerNV)
	GLvoid *buffer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(buffer) + bufferOffset);
	glVertexAttribPointerNV(index, size, type, stride, buffer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglVertexAttribPointerNVVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglVertexAttribPointerNVVBO
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jint stride, jint bufferOffset)
{
	CHECK_EXISTS(glVertexAttribPointerNV)
	glVertexAttribPointerNV(index, size, type, stride, (GLvoid *)bufferOffset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib1sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib1sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x)
{
	CHECK_EXISTS(glVertexAttrib1sNV)
	glVertexAttrib1sNV(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib1fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib1fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x)
{
	CHECK_EXISTS(glVertexAttrib1fNV)
	glVertexAttrib1fNV(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib2sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib2sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y)
{
	CHECK_EXISTS(glVertexAttrib2sNV)
	glVertexAttrib2sNV(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib2fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib2fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y)
{
	CHECK_EXISTS(glVertexAttrib2fNV)
	glVertexAttrib2fNV(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib3sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib3sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glVertexAttrib3sNV)
	glVertexAttrib3sNV(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib3fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib3fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z)
{
	CHECK_EXISTS(glVertexAttrib3fNV)
	glVertexAttrib3fNV(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib4sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib4sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w)
{
	CHECK_EXISTS(glVertexAttrib4sNV)
	glVertexAttrib4sNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib4fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glVertexAttrib4fNV)
	glVertexAttrib4fNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib4ubNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib4ubNV
	(JNIEnv * env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w)
{
	CHECK_EXISTS(glVertexAttrib4ubNV)
	glVertexAttrib4ubNV(index, x, y, z, w);
	CHECK_GL_ERROR
}
