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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVVertexProgram
// ----------------------------------

#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glExecuteProgramNVPROC) (GLenum target, GLuint id, const GLfloat *params);
typedef void (APIENTRY * glGetProgramParameterfvNVPROC) (GLenum target, GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTrackMatrixivNVPROC) (GLenum target, GLuint address, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribfvNVPROC) (GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVertexAttribivNVPROC) (GLuint index, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribPointervNVPROC) (GLuint index, GLenum pname, GLvoid **pointer);
typedef void (APIENTRY * glProgramParameter4fNVPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
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
static glVertexAttribs1svNVPROC glVertexAttribs1svNV;
static glVertexAttribs1fvNVPROC glVertexAttribs1fvNV;
static glVertexAttribs2svNVPROC glVertexAttribs2svNV;
static glVertexAttribs2fvNVPROC glVertexAttribs2fvNV;
static glVertexAttribs3svNVPROC glVertexAttribs3svNV;
static glVertexAttribs3fvNVPROC glVertexAttribs3fvNV;
static glVertexAttribs4svNVPROC glVertexAttribs4svNV;
static glVertexAttribs4fvNVPROC glVertexAttribs4fvNV;
static glVertexAttribs4ubvNVPROC glVertexAttribs4ubvNV;

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglExecuteProgramNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglExecuteProgramNV
	(JNIEnv * env, jclass clazz, jint target, jint id, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glExecuteProgramNV(target, id, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglGetProgramParameterfvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetProgramParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramParameterfvNV(target, index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglGetTrackMatrixivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetTrackMatrixivNV
	(JNIEnv * env, jclass clazz, jint target, jint address, jint parameterName, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetTrackMatrixivNV(target, address, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglGetVertexAttribfvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribfvNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribfvNV(index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglGetVertexAttribivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribivNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribivNV(index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glGetVertexAttribPointerNV
 */
static jobject JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glGetVertexAttribPointerNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jint size)
{
        void *address;
        glGetVertexAttribPointervNV((GLuint)index, (GLuint)parameterName, &address);
        CHECK_GL_ERROR
        return safeNewBuffer(env, address, size);
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glProgramParameter4fNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glProgramParameter4fNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glProgramParameter4fNV(target, index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglProgramParameters4fvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglProgramParameters4fvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint count, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glProgramParameters4fvNV(target, index, count, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glTrackMatrixNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glTrackMatrixNV
	(JNIEnv * env, jclass clazz, jint target, jint address, jint matrix, jint transform)
{
	glTrackMatrixNV(target, address, matrix, transform);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglVertexAttribPointerNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNV
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jint stride, jobject buffer, jint bufferOffset)
{
	GLvoid *buffer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(buffer) + bufferOffset);
	glVertexAttribPointerNV(index, size, type, stride, buffer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	nglVertexAttribPointerNVVBO
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNVVBO
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jint stride, jint bufferOffset)
{
	glVertexAttribPointerNV(index, size, type, stride, (GLvoid *)bufferOffset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib1sNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x)
{
	glVertexAttrib1sNV(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib1fNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x)
{
	glVertexAttrib1fNV(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib2sNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y)
{
	glVertexAttrib2sNV(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib2fNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y)
{
	glVertexAttrib2fNV(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib3sNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z)
{
	glVertexAttrib3sNV(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib3fNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z)
{
	glVertexAttrib3fNV(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib4sNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w)
{
	glVertexAttrib4sNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib4fNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glVertexAttrib4fNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVVertexProgram
 * Method:	glVertexAttrib4ubNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4ubNV
	(JNIEnv * env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w)
{
	glVertexAttrib4ubNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1svNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLshort *v_ptr = (GLshort *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs1svNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1fvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLfloat *v_ptr = (GLfloat *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs1fvNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2svNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLshort *v_ptr = (GLshort *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs2svNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2fvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLfloat *v_ptr = (GLfloat *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs2fvNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3svNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLshort *v_ptr = (GLshort *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs3svNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3fvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLfloat *v_ptr = (GLfloat *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs3fvNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4svNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLshort *v_ptr = (GLshort *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs4svNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4fvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLfloat *v_ptr = (GLfloat *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs4fvNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4ubvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject v, jint v_offset)
{
	GLubyte *v_ptr = (GLubyte *)env->GetDirectBufferAddress(v) + v_offset;
	glVertexAttribs4ubvNV(index, n, v_ptr);
	CHECK_GL_ERROR
}

void extgl_InitNVVertexProgram(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"nglExecuteProgramNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglExecuteProgramNV, "glExecuteProgramNV", (void**)&glExecuteProgramNV},
		{"nglGetProgramParameterfvNV", "(IIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetProgramParameterfvNV, "glGetProgramParameterfvNV", (void**)&glGetProgramParameterfvNV},
		{"nglGetTrackMatrixivNV", "(IIILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetTrackMatrixivNV, "glGetTrackMatrixivNV", (void**)&glGetTrackMatrixivNV},
		{"nglGetVertexAttribfvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribfvNV, "glGetVertexAttribfvNV", (void**)&glGetVertexAttribfvNV},
		{"nglGetVertexAttribivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglGetVertexAttribivNV, "glGetVertexAttribivNV", (void**)&glGetVertexAttribivNV},
		{"glGetVertexAttribPointerNV", "(III)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glGetVertexAttribPointerNV, "glGetVertexAttribPointervNV", (void**)&glGetVertexAttribPointervNV},
		{"glProgramParameter4fNV", "(IIFFFF)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glProgramParameter4fNV, "glProgramParameter4fNV", (void**)&glProgramParameter4fNV},
		{"nglProgramParameters4fvNV", "(IIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglProgramParameters4fvNV, "glProgramParameters4fvNV", (void**)&glProgramParameters4fvNV},
		{"glTrackMatrixNV", "(IIII)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glTrackMatrixNV, "glTrackMatrixNV", (void**)&glTrackMatrixNV},
		{"nglVertexAttribPointerNV", "(IIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNV, "glVertexAttribPointerNV", (void**)&glVertexAttribPointerNV},
		{"nglVertexAttribPointerNVVBO", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribPointerNVVBO, NULL, NULL},
		{"glVertexAttrib1sNV", "(IS)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1sNV, "glVertexAttrib1sNV", (void**)&glVertexAttrib1sNV},
		{"glVertexAttrib1fNV", "(IF)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib1fNV, "glVertexAttrib1fNV", (void**)&glVertexAttrib1fNV},
		{"glVertexAttrib2sNV", "(ISS)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2sNV, "glVertexAttrib2sNV", (void**)&glVertexAttrib2sNV},
		{"glVertexAttrib2fNV", "(IFF)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib2fNV, "glVertexAttrib2fNV", (void**)&glVertexAttrib2fNV},
		{"glVertexAttrib3sNV", "(ISSS)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3sNV, "glVertexAttrib3sNV", (void**)&glVertexAttrib3sNV},
		{"glVertexAttrib3fNV", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib3fNV, "glVertexAttrib3fNV", (void**)&glVertexAttrib3fNV},
		{"glVertexAttrib4sNV", "(ISSSS)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4sNV, "glVertexAttrib4sNV", (void**)&glVertexAttrib4sNV},
		{"glVertexAttrib4fNV", "(IFFFF)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4fNV, "glVertexAttrib4fNV", (void**)&glVertexAttrib4fNV},
		{"glVertexAttrib4ubNV", "(IBBBB)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_glVertexAttrib4ubNV, "glVertexAttrib4ubNV", (void**)&glVertexAttrib4ubNV},
		{"nglVertexAttribs1svNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1svNV, "glVertexAttribs1svNV", (void**)&glVertexAttribs1svNV},
		{"nglVertexAttribs1fvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs1fvNV, "glVertexAttribs1fvNV", (void**)&glVertexAttribs1fvNV},
		{"nglVertexAttribs2svNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2svNV, "glVertexAttribs2svNV", (void**)&glVertexAttribs2svNV},
		{"nglVertexAttribs2fvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs2fvNV, "glVertexAttribs2fvNV", (void**)&glVertexAttribs2fvNV},
		{"nglVertexAttribs3svNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3svNV, "glVertexAttribs3svNV", (void**)&glVertexAttribs3svNV},
		{"nglVertexAttribs3fvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs3fvNV, "glVertexAttribs3fvNV", (void**)&glVertexAttribs3fvNV},
		{"nglVertexAttribs4svNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4svNV, "glVertexAttribs4svNV", (void**)&glVertexAttribs4svNV},
		{"nglVertexAttribs4fvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4fvNV, "glVertexAttribs4fvNV", (void**)&glVertexAttribs4fvNV},
		{"nglVertexAttribs4ubvNV", "(IILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexProgram_nglVertexAttribs4ubvNV, "glVertexAttribs4ubvNV", (void**)&glVertexAttribs4ubvNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/NVVertexProgram");
	if (extgl_Extensions.GL_NV_vertex_program)
		extgl_InitializeClass(env, clazz, ext_set, "GL_NV_vertex_program", num_functions, functions);
}
