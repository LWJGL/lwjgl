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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.GL20
// ----------------------------------

#include "extgl.h"
#include "common_tools.h"

typedef int GLintptr;
typedef unsigned int GLsizeiptr;

typedef unsigned char GLchar;

// ARB_shader_objects
typedef void (APIENTRY * glAttachShaderPROC) (GLuint program, GLuint shader);
typedef void (APIENTRY * glCompileShaderPROC) (GLuint shader);
typedef GLint (APIENTRY * glCreateProgramPROC) (void);
typedef GLint (APIENTRY * glCreateShaderPROC) (GLuint type);
typedef void (APIENTRY * glDeleteProgramPROC) (GLuint program);
typedef void (APIENTRY * glDeleteShaderPROC) (GLuint shader);
typedef void (APIENTRY * glDetachShaderPROC) (GLuint program, GLuint shader);
typedef void (APIENTRY * glGetActiveUniformPROC) (GLuint program, GLuint index, GLsizei maxLength, GLsizei *length, GLint *size, GLenum *type, const GLchar *name);
typedef void (APIENTRY * glGetAttachedShadersPROC) (GLuint program, GLsizei maxCount, GLsizei *count, GLuint *obj);
typedef void (APIENTRY * glGetProgramfvPROC) (GLuint program, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetProgramivPROC) (GLuint program, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetProgramInfoLogPROC) (GLuint program, GLsizei maxLength, GLsizei *length, GLchar *infoLog);
typedef void (APIENTRY * glGetShaderfvPROC) (GLuint shader, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetShaderivPROC) (GLuint shader, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetShaderInfoLogPROC) (GLuint shader, GLsizei maxLength, GLsizei *length, GLchar *infoLog);
typedef void (APIENTRY * glGetShaderSourcePROC) (GLuint shader, GLsizei maxLength, GLsizei *length, GLchar *source);
typedef void (APIENTRY * glGetUniformfvPROC) (GLuint program, GLint location, GLfloat *params);
typedef void (APIENTRY * glGetUniformivPROC) (GLuint program, GLint location, GLint *params);
typedef GLint (APIENTRY * glGetUniformLocationPROC) (GLuint program, const GLchar *name);
typedef GLboolean (APIENTRY * glIsProgramPROC) (GLuint program);
typedef GLboolean (APIENTRY * glIsShaderPROC) (GLuint shader);
typedef void (APIENTRY * glLinkProgramPROC) (GLuint program);
typedef void (APIENTRY * glShaderSourcePROC) (GLuint shader, GLsizei count, const GLchar **string, const GLint *length);
typedef void (APIENTRY * glUniform1fPROC) (GLint location, GLfloat v0);
typedef void (APIENTRY * glUniform2fPROC) (GLint location, GLfloat v0, GLfloat v1);
typedef void (APIENTRY * glUniform3fPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2);
typedef void (APIENTRY * glUniform4fPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2, GLfloat v3);
typedef void (APIENTRY * glUniform1iPROC) (GLint location, GLint v0);
typedef void (APIENTRY * glUniform2iPROC) (GLint location, GLint v0, GLint v1);
typedef void (APIENTRY * glUniform3iPROC) (GLint location, GLint v0, GLint v1, GLint v2);
typedef void (APIENTRY * glUniform4iPROC) (GLint location, GLint v0, GLint v1, GLint v2, GLint v3);
typedef void (APIENTRY * glUniform1fvPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform2fvPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform3fvPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform4fvPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform1ivPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniform2ivPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniform3ivPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniform4ivPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniformMatrix2fvPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat *value);
typedef void (APIENTRY * glUniformMatrix3fvPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat *value);
typedef void (APIENTRY * glUniformMatrix4fvPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat *value);
typedef void (APIENTRY * glUseProgramPROC) (GLuint program);
typedef void (APIENTRY * glValidateProgramPROC) (GLuint program);

static const int initialSourcesSize = 8;
static int sourcesSize = initialSourcesSize;
static int sourceCount;
static GLchar** sources = new GLchar*[initialSourcesSize];
static GLint* sourcesLengths = new GLint[initialSourcesSize];
// ARB_vertex_shader
typedef void (APIENTRY * glBindAttribLocationPROC) (GLuint program, GLuint index, const GLchar *name);
typedef void (APIENTRY * glGetActiveAttribPROC) (GLuint program, GLuint index, GLsizei maxLength, GLsizei *length, GLint *size, GLenum *type, const GLchar *name);
typedef GLint (APIENTRY * glGetAttribLocationPROC) (GLuint program, const GLchar *name);
// ARB_draw_buffers
typedef void (APIENTRY * glDrawBuffersPROC) (GLsizei n, const GLenum *bufs);
// Two-Sided Stencil
typedef void (APIENTRY * glStencilFuncSeparatePROC) (GLenum face, GLenum func, GLint ref, GLuint mask);
typedef void (APIENTRY * glStencilOpSeparatePROC) (GLenum face, GLenum sfail, GLenum dpfail, GLenum dppass);

// ARB_shader_objects
static glAttachShaderPROC glAttachShader;
static glCompileShaderPROC glCompileShader;
static glCreateProgramPROC glCreateProgram;
static glCreateShaderPROC glCreateShader;
static glDeleteProgramPROC glDeleteProgram;
static glDeleteShaderPROC glDeleteShader;
static glDetachShaderPROC glDetachShader;
static glGetActiveUniformPROC glGetActiveUniform;
static glGetAttachedShadersPROC glGetAttachedShaders;
static glGetProgramfvPROC glGetProgramfv;
static glGetProgramivPROC glGetProgramiv;
static glGetProgramInfoLogPROC glGetProgramInfoLog;
static glGetShaderfvPROC glGetShaderfv;
static glGetShaderivPROC glGetShaderiv;
static glGetShaderInfoLogPROC glGetShaderInfoLog;
static glGetShaderSourcePROC glGetShaderSource;
static glGetUniformfvPROC glGetUniformfv;
static glGetUniformivPROC glGetUniformiv;
static glGetUniformLocationPROC glGetUniformLocation;
static glIsProgramPROC glIsProgram;
static glIsShaderPROC glIsShader;
static glLinkProgramPROC glLinkProgram;
static glShaderSourcePROC glShaderSource;
static glUniform1fPROC glUniform1f;
static glUniform2fPROC glUniform2f;
static glUniform3fPROC glUniform3f;
static glUniform4fPROC glUniform4f;
static glUniform1iPROC glUniform1i;
static glUniform2iPROC glUniform2i;
static glUniform3iPROC glUniform3i;
static glUniform4iPROC glUniform4i;
static glUniform1fvPROC glUniform1fv;
static glUniform2fvPROC glUniform2fv;
static glUniform3fvPROC glUniform3fv;
static glUniform4fvPROC glUniform4fv;
static glUniform1ivPROC glUniform1iv;
static glUniform2ivPROC glUniform2iv;
static glUniform3ivPROC glUniform3iv;
static glUniform4ivPROC glUniform4iv;
static glUniformMatrix2fvPROC glUniformMatrix2fv;
static glUniformMatrix3fvPROC glUniformMatrix3fv;
static glUniformMatrix4fvPROC glUniformMatrix4fv;
static glUseProgramPROC glUseProgram;
static glValidateProgramPROC glValidateProgram;
// ARB_vertex_shader
static glBindAttribLocationPROC glBindAttribLocation;
static glGetActiveAttribPROC glGetActiveAttrib;
static glGetAttribLocationPROC glGetAttribLocation;
// ARB_draw_buffers
static glDrawBuffersPROC glDrawBuffers;
// Two-Sided Stencil
static glStencilFuncSeparatePROC glStencilFuncSeparate;
static glStencilOpSeparatePROC glStencilOpSeparate;

// ------------------------------------------------------------------
// ----------------------[ ARB_shader_objects ]----------------------
// ------------------------------------------------------------------

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	initShaderSource
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_initShaderSource
	(JNIEnv * env, jclass clazz, jint count)
{
	sourceCount = count;

	if ( sourceCount > sourcesSize ) {
		sourcesSize = sourceCount * 2;

		delete sources;
		delete sourcesLengths;

		sources = new GLchar*[sourcesSize];
		sourcesLengths = new GLint[sourcesSize];
	}
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	setShaderString
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_setShaderString
	(JNIEnv * env, jclass clazz, jint index, jobject string, jint stringOffset, jint stringLength)
{
	GLchar *string_ptr = (GLchar *)((GLubyte *)env->GetDirectBufferAddress(string) + stringOffset);

	sources[index] = string_ptr;
	sourcesLengths[index] = stringLength;
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglShaderSource
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglShaderSource
	(JNIEnv * env, jclass clazz, jint shader)
{
	glShaderSource(shader, sourceCount, (const GLchar **)sources, (const GLint *)sourcesLengths);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glCreateShader
 */
static jint JNICALL Java_org_lwjgl_opengl_GL20_glCreateShader
	(JNIEnv * env, jclass clazz, jint type)
{
	return glCreateShader(type);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glIsShader
 */
static jboolean JNICALL Java_org_lwjgl_opengl_GL20_glIsShader
	(JNIEnv * env, jclass clazz, jint shader)
{
	return glIsShader(shader);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glCompileShader
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glCompileShader(JNIEnv * env, jclass clazz, jint shader)
{
	glCompileShader(shader);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glDeleteShader
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glDeleteShader
	(JNIEnv * env, jclass clazz, jint shader)
{
	glDeleteShader(shader);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glCreateProgram
 */
static jint JNICALL Java_org_lwjgl_opengl_GL20_glCreateProgram
	(JNIEnv * env, jclass clazz)
{
	return glCreateProgram();
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glIsProgram
 */
static jboolean JNICALL Java_org_lwjgl_opengl_GL20_glIsProgram
	(JNIEnv * env, jclass clazz, jint program)
{
	return glIsProgram(program);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glAttachShader
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glAttachShader
	(JNIEnv * env, jclass clazz, jint program, jint shader)
{
	glAttachShader(program, shader);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glDetachShader
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glDetachShader
	(JNIEnv * env, jclass clazz, jint program, jint shader)
{
	glDetachShader(program, shader);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glLinkProgram
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glLinkProgram
	(JNIEnv * env, jclass clazz, jint program)
{
	glLinkProgram(program);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUseProgram
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUseProgram
	(JNIEnv * env, jclass clazz, jint program)
{
	glUseProgram(program);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glValidateProgram
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glValidateProgram
	(JNIEnv * env, jclass clazz, jint program)
{
	glValidateProgram(program);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glDeleteProgram
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glDeleteProgram
	(JNIEnv * env, jclass clazz, jint program)
{
	glDeleteProgram(program);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform1f
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform1f
	(JNIEnv * env, jclass clazz, jint location, jfloat v0)
{
	glUniform1f(location, v0);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform2f
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform2f
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1)
{
	glUniform2f(location, v0, v1);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform3f
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform3f
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2)
{
	glUniform3f(location, v0, v1, v2);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform4f
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform4f
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2, jfloat v3)
{
	glUniform4f(location, v0, v1, v2, v3);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform1i
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform1i
	(JNIEnv * env, jclass clazz, jint location, jint v0)
{
	glUniform1i(location, v0);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform2i
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform2i
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1)
{
	glUniform2i(location, v0, v1);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform3i
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform3i
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1, jint v2)
{
	glUniform3i(location, v0, v1, v2);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glUniform4i
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform4i
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1, jint v2, jint v3)
{
	glUniform4i(location, v0, v1, v2, v3);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform1fv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform1fv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform1fv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform2fv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform2fv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform2fv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform3fv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform3fv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform3fv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform4fv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform4fv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform4fv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform1iv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform1iv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform1iv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform2iv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform2iv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform2iv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform3iv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform3iv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform3iv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniform4iv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform4iv
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform4iv(location, count, values_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniformMatrix2fv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniformMatrix2fv
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix2fv(location, count, transpose, matrices_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniformMatrix3fv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniformMatrix3fv
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix3fv(location, count, transpose, matrices_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglUniformMatrix4fv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniformMatrix4fv
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix4fv(location, count, transpose, matrices_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetShaderfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderfv
	(JNIEnv * env, jclass clazz, jint shader, jint pname, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetShaderfv(shader, pname, params_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetShaderiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderiv
	(JNIEnv * env, jclass clazz, jint shader, jint pname, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetShaderiv(shader, pname, params_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetProgramfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetProgramfv
	(JNIEnv * env, jclass clazz, jint program, jint pname, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramfv(program, pname, params_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetProgramiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetProgramiv
	(JNIEnv * env, jclass clazz, jint program, jint pname, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramiv(program, pname, params_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetShaderInfoLog
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderInfoLog
	(JNIEnv * env, jclass clazz, jint shader, jint maxLength, jobject length, jint lengthOffset, jobject infoLog, jint infoLogOffset)
{
	GLubyte *infoLog_ptr = (GLubyte *)env->GetDirectBufferAddress(infoLog) + infoLogOffset;

	if ( length == NULL ) {
		glGetShaderInfoLog(shader, maxLength, NULL, infoLog_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetShaderInfoLog(shader, maxLength, length_ptr, infoLog_ptr);
	}
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetProgramInfoLog
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetProgramInfoLog
	(JNIEnv * env, jclass clazz, jint program, jint maxLength, jobject length, jint lengthOffset, jobject infoLog, jint infoLogOffset)
{
	GLubyte *infoLog_ptr = (GLubyte *)env->GetDirectBufferAddress(infoLog) + infoLogOffset;

	if ( length == NULL ) {
		glGetProgramInfoLog(program, maxLength, NULL, infoLog_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetProgramInfoLog(program, maxLength, length_ptr, infoLog_ptr);
	}
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetAttachedShaders
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetAttachedShaders
	(JNIEnv * env, jclass clazz, jint program, jint maxCount, jobject count, jint countOffset, jobject shaders, jint shadersOffset)
{
	GLuint *shaders_ptr = (GLuint *)env->GetDirectBufferAddress(shaders) + shadersOffset;

	if ( count == NULL ) {
		glGetAttachedShaders(program, maxCount, NULL, shaders_ptr);
	} else {
		GLsizei *count_ptr = (GLsizei *)env->GetDirectBufferAddress(count) + countOffset;
		glGetAttachedShaders(program, maxCount, count_ptr, shaders_ptr);
	}
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetUniformLocation
 */
static jint JNICALL Java_org_lwjgl_opengl_GL20_nglGetUniformLocation
	(JNIEnv * env, jclass clazz, jint program, jobject name, jint nameOffset)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	return glGetUniformLocation(program, name_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetActiveUniform
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetActiveUniform
	(JNIEnv * env, jclass clazz, jint program, jint index, jint maxLength, jobject length, jint lengthOffset, jobject size, jint sizeOffset, jobject type, jint typeOffset, jobject name, jint nameOffset)
{
	GLint *size_ptr = (GLint *)env->GetDirectBufferAddress(size) + sizeOffset;
	GLenum *type_ptr = (GLenum *)env->GetDirectBufferAddress(type) + typeOffset;
	GLchar *name_ptr = (GLchar *)env->GetDirectBufferAddress(name) + nameOffset;

	if ( length == NULL ) {
		glGetActiveUniform(program, index, maxLength, (GLsizei *)NULL, size_ptr, type_ptr, name_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetActiveUniform(program, index, maxLength, length_ptr, size_ptr, type_ptr, name_ptr);
	}
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetUniformfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetUniformfv
	(JNIEnv * env, jclass clazz, jint program, jint location, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetUniformfv(program, location, params_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetUniformiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetUniformiv
	(JNIEnv * env, jclass clazz, jint program, jint location, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetUniformiv(program, location, params_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetShaderSource
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderSource
	(JNIEnv * env, jclass clazz, jint shader, jint maxLength, jobject length, jint lengthOffset, jobject source, jint sourceOffset)
{
	GLubyte *source_ptr = (GLubyte *)env->GetDirectBufferAddress(source) + sourceOffset;

	if ( length == NULL ) {
		glGetShaderSource(shader, maxLength, NULL, source_ptr);
	} else {
		GLint *length_ptr = (GLint *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetShaderSource(shader, maxLength, length_ptr, source_ptr);
	}
}

// ------------------------------------------------------------------
// ----------------------[ ARB_vertex_shaders ]----------------------
// ------------------------------------------------------------------
/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglBindAttribLocation
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglBindAttribLocation
	(JNIEnv * env, jclass clazz, jint program, jint index, jobject name, jint nameOffset)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	glBindAttribLocation(program, index, name_ptr);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetActiveAttrib
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetActiveAttrib
	(JNIEnv * env, jclass clazz, jint program, jint index, jint maxLength, jobject length, jint lengthOffset, jobject size, jint sizeOffset, jobject type, jint typeOffset, jobject name, jint nameOffset)
{
	GLint *size_ptr = (GLint *)env->GetDirectBufferAddress(size) + sizeOffset;
	GLenum *type_ptr = (GLenum *)env->GetDirectBufferAddress(type) + typeOffset;
	GLchar *name_ptr = (GLchar *)env->GetDirectBufferAddress(name) + nameOffset;

	if ( length == NULL ) {
		glGetActiveAttrib(program, index, maxLength, NULL, size_ptr, type_ptr, name_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetActiveAttrib(program, index, maxLength, length_ptr, size_ptr, type_ptr, name_ptr);
	}
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglGetAttribLocation
 */
static jint JNICALL Java_org_lwjgl_opengl_GL20_nglGetAttribLocation
	(JNIEnv * env, jclass clazz, jint program, jobject name, jint nameOffset)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	return glGetAttribLocation(program, name_ptr);
}

// ----------------------------------------------------------------
// ----------------------[ ARB_draw_buffers ]----------------------
// ----------------------------------------------------------------

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	nglDrawBuffers
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_nglDrawBuffers
	(JNIEnv * env, jclass clazz, jint size, jobject buffers, jint buffersOffset)
{
	GLuint *buffers_ptr = (GLuint *)env->GetDirectBufferAddress(buffers) + buffersOffset;
	glDrawBuffers(size, buffers_ptr);
}

// -----------------------------------------------------------------
// ----------------------[ Two-Sided Stencil ]----------------------
// -----------------------------------------------------------------

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glStencilFuncSeparate
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glStencilFuncSeparate
	(JNIEnv * env, jclass clazz, jint face, jint func, jint ref, jint mask)
{
	glStencilFuncSeparate(face, func, ref, mask);
}

/*
 * Class:	org.lwjgl.opengl.GL20
 * Method:	glStencilOpSeparate
 */
static void JNICALL Java_org_lwjgl_opengl_GL20_glStencilOpSeparate
	(JNIEnv * env, jclass clazz, jint face, jint sfail, jint dpfail, jint dppass)
{
	glStencilOpSeparate(face, sfail, dpfail, dppass);
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL20_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		// ARB_shader_objects
		{"glIsShader", "(I)Z", (void*)&Java_org_lwjgl_opengl_GL20_glIsShader, "glIsShader", (void**)&glIsShader},
		{"glIsProgram", "(I)Z", (void*)&Java_org_lwjgl_opengl_GL20_glIsProgram, "glIsProgram", (void**)&glIsProgram},
		{"glDeleteShader", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_glDeleteShader, "glDeleteShader", (void**)&glDeleteShader},
		{"glDeleteProgram", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_glDeleteProgram, "glDeleteProgram", (void**)&glDeleteProgram},
		{"glDetachShader", "(II)V", (void*)&Java_org_lwjgl_opengl_GL20_glDetachShader, "glDetachShader", (void**)&glDetachShader},
		{"glCreateShader", "(I)I", (void*)&Java_org_lwjgl_opengl_GL20_glCreateShader, "glCreateShader", (void**)&glCreateShader},
		{"initShaderSource", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_initShaderSource, NULL, NULL},
		{"setShaderString", "(ILjava/nio/ByteBuffer;II)V", (void*)&Java_org_lwjgl_opengl_GL20_setShaderString, NULL, NULL},
		{"nglShaderSource", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglShaderSource, "glShaderSource", (void**)&glShaderSource},
		{"glCompileShader", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_glCompileShader, "glCompileShader", (void**)&glCompileShader},
		{"glCreateProgram", "()I", (void*)&Java_org_lwjgl_opengl_GL20_glCreateProgram, "glCreateProgram", (void**)&glCreateProgram},
		{"glAttachShader", "(II)V", (void*)&Java_org_lwjgl_opengl_GL20_glAttachShader, "glAttachShader", (void**)&glAttachShader},
		{"glLinkProgram", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_glLinkProgram, "glLinkProgram", (void**)&glLinkProgram},
		{"glUseProgram", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_glUseProgram, "glUseProgram", (void**)&glUseProgram},
		{"glValidateProgram", "(I)V", (void*)&Java_org_lwjgl_opengl_GL20_glValidateProgram, "glValidateProgram", (void**)&glValidateProgram},
		{"glUniform1f", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform1f, "glUniform1f", (void**)&glUniform1f},
		{"glUniform2f", "(IFF)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform2f, "glUniform2f", (void**)&glUniform2f},
		{"glUniform3f", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform3f, "glUniform3f", (void**)&glUniform3f},
		{"glUniform4f", "(IFFFF)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform4f, "glUniform4f", (void**)&glUniform4f},
		{"glUniform1i", "(II)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform1i, "glUniform1i", (void**)&glUniform1i},
		{"glUniform2i", "(III)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform2i, "glUniform2i", (void**)&glUniform2i},
		{"glUniform3i", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform3i, "glUniform3i", (void**)&glUniform3i},
		{"glUniform4i", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_GL20_glUniform4i, "glUniform4i", (void**)&glUniform4i},
		{"nglUniform1fv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform1fv, "glUniform1fv", (void**)&glUniform1fv},
		{"nglUniform2fv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform2fv, "glUniform2fv", (void**)&glUniform2fv},
		{"nglUniform3fv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform3fv, "glUniform3fv", (void**)&glUniform3fv},
		{"nglUniform4fv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform4fv, "glUniform4fv", (void**)&glUniform4fv},
		{"nglUniform1iv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform1iv, "glUniform1iv", (void**)&glUniform1iv},
		{"nglUniform2iv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform2iv, "glUniform2iv", (void**)&glUniform2iv},
		{"nglUniform3iv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform3iv, "glUniform3iv", (void**)&glUniform3iv},
		{"nglUniform4iv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniform4iv, "glUniform4iv", (void**)&glUniform4iv},
		{"nglUniformMatrix2fv", "(IIZLjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniformMatrix2fv, "glUniformMatrix2fv", (void**)&glUniformMatrix2fv},
		{"nglUniformMatrix3fv", "(IIZLjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniformMatrix3fv, "glUniformMatrix3fv", (void**)&glUniformMatrix3fv},
		{"nglUniformMatrix4fv", "(IIZLjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglUniformMatrix4fv, "glUniformMatrix4fv", (void**)&glUniformMatrix4fv},
		{"nglGetShaderfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetShaderfv, "glGetShaderfv", (void**)&glGetShaderfv},
		{"nglGetShaderiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetShaderiv, "glGetShaderiv", (void**)&glGetShaderiv},
		{"nglGetProgramfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetProgramfv, "glGetProgramfv", (void**)&glGetProgramfv},
		{"nglGetProgramiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetProgramiv, "glGetProgramiv", (void**)&glGetProgramiv},
		{"nglGetShaderInfoLog", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetShaderInfoLog, "glGetShaderInfoLog", (void**)&glGetShaderInfoLog},
		{"nglGetProgramInfoLog", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetProgramInfoLog, "glGetProgramInfoLog", (void**)&glGetProgramInfoLog},
		{"nglGetAttachedShaders", "(IILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetAttachedShaders, "glGetAttachedShaders", (void**)&glGetAttachedShaders},
		{"nglGetUniformLocation", "(ILjava/nio/ByteBuffer;I)I", (void*)&Java_org_lwjgl_opengl_GL20_nglGetUniformLocation, "glGetUniformLocation", (void**)&glGetUniformLocation},
		{"nglGetActiveUniform", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetActiveUniform, "glGetActiveUniform", (void**)&glGetActiveUniform},
		{"nglGetUniformfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetUniformfv, "glGetUniformfv", (void**)&glGetUniformfv},
		{"nglGetUniformiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetUniformiv, "glGetUniformiv", (void**)&glGetUniformiv},
		{"nglGetShaderSource", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetShaderSource, "glGetShaderSource", (void**)&glGetShaderSource}
		// ARB_vertex_shader
		{"nglBindAttribLocation", "(IILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglBindAttribLocation, "glBindAttribLocation", (void**)&glBindAttribLocation},
		{"nglGetActiveAttrib", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglGetActiveAttrib, "glGetActiveAttrib", (void**)&glGetActiveAttrib},
		{"nglGetAttribLocation", "(ILjava/nio/ByteBuffer;I)I", (void*)&Java_org_lwjgl_opengl_GL20_nglGetAttribLocation, "glGetAttribLocation", (void**)&glGetAttribLocation}
		// ARB_draw_buffers
		{"nglDrawBuffers", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL20_nglDrawBuffers, "glDrawBuffers", (void**)&glDrawBuffers}
		// Two-Sided Stencil
		{"glStencilFuncSeparate", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL20_glStencilFuncSeparate, "glStencilFuncSeparate", (void**)&glStencilFuncSeparate},
		{"glStencilOpSeparate", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL20_glStencilOpSeparate, "glStencilOpSeparate", (void**)&glStencilOpSeparate}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}