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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBShaderObjects
// ----------------------------------

#include "extgl.h"


typedef unsigned char GLcharARB;
typedef unsigned int GLhandleARB;

typedef void (APIENTRY * glDeleteObjectARBPROC) (GLhandleARB obj);
typedef GLhandleARB (APIENTRY * glGetHandleARBPROC) (GLenum pname);
typedef void (APIENTRY * glDetachObjectARBPROC) (GLhandleARB containerObj, GLhandleARB attachedObj);
typedef GLhandleARB (APIENTRY * glCreateShaderObjectARBPROC) (GLenum shaderType);
typedef void (APIENTRY * glShaderSourceARBPROC) (GLhandleARB shaderObj, GLsizei count, const GLcharARB **string, const GLint *length);
typedef void (APIENTRY * glCompileShaderARBPROC) (GLhandleARB shaderObj);
typedef GLhandleARB (APIENTRY * glCreateProgramObjectARBPROC) (GLvoid);
typedef void (APIENTRY * glAttachObjectARBPROC) (GLhandleARB containerObj, GLhandleARB obj);
typedef void (APIENTRY * glLinkProgramARBPROC) (GLhandleARB programObj);
typedef void (APIENTRY * glUseProgramObjectARBPROC) (GLhandleARB programObj);
typedef void (APIENTRY * glValidateProgramARBPROC) (GLhandleARB programObj);
typedef void (APIENTRY * glUniform1fARBPROC) (GLint location, GLfloat v0);
typedef void (APIENTRY * glUniform2fARBPROC) (GLint location, GLfloat v0, GLfloat v1);
typedef void (APIENTRY * glUniform3fARBPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2);
typedef void (APIENTRY * glUniform4fARBPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2, GLfloat v3);
typedef void (APIENTRY * glUniform1iARBPROC) (GLint location, GLint v0);
typedef void (APIENTRY * glUniform2iARBPROC) (GLint location, GLint v0, GLint v1);
typedef void (APIENTRY * glUniform3iARBPROC) (GLint location, GLint v0, GLint v1, GLint v2);
typedef void (APIENTRY * glUniform4iARBPROC) (GLint location, GLint v0, GLint v1, GLint v2, GLint v3);
typedef void (APIENTRY * glUniform1fvARBPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform2fvARBPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform3fvARBPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform4fvARBPROC) (GLint location, GLsizei count, GLfloat *value);
typedef void (APIENTRY * glUniform1ivARBPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniform2ivARBPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniform3ivARBPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniform4ivARBPROC) (GLint location, GLsizei count, GLint *value);
typedef void (APIENTRY * glUniformMatrix2fvARBPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat *value);
typedef void (APIENTRY * glUniformMatrix3fvARBPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat *value);
typedef void (APIENTRY * glUniformMatrix4fvARBPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat *value);
typedef void (APIENTRY * glGetObjectParameterfvARBPROC) (GLhandleARB obj, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetObjectParameterivARBPROC) (GLhandleARB obj, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetInfoLogARBPROC) (GLhandleARB obj, GLsizei maxLength, GLsizei *length, GLcharARB *infoLog);
typedef void (APIENTRY * glGetAttachedObjectsARBPROC) (GLhandleARB containerObj, GLsizei maxCount, GLsizei *count, GLhandleARB *obj);
typedef GLint (APIENTRY * glGetUniformLocationARBPROC) (GLhandleARB programObj, const GLcharARB *name);
typedef void (APIENTRY * glGetActiveUniformARBPROC) (GLhandleARB programObj, GLuint index, GLsizei maxLength, GLsizei *length, GLint *size, GLenum *type, GLcharARB *name);
typedef void (APIENTRY * glGetUniformfvARBPROC) (GLhandleARB programObj, GLint location, GLfloat *params);
typedef void (APIENTRY * glGetUniformivARBPROC) (GLhandleARB programObj, GLint location, GLint *params);
typedef void (APIENTRY * glGetShaderSourceARBPROC) (GLhandleARB obj, GLsizei maxLength, GLsizei *length, GLcharARB *source);

static glDeleteObjectARBPROC glDeleteObjectARB;
static glGetHandleARBPROC glGetHandleARB;
static glDetachObjectARBPROC glDetachObjectARB;
static glCreateShaderObjectARBPROC glCreateShaderObjectARB;
static glShaderSourceARBPROC glShaderSourceARB;
static glCompileShaderARBPROC glCompileShaderARB;
static glCreateProgramObjectARBPROC glCreateProgramObjectARB;
static glAttachObjectARBPROC glAttachObjectARB;
static glLinkProgramARBPROC glLinkProgramARB;
static glUseProgramObjectARBPROC glUseProgramObjectARB;
static glValidateProgramARBPROC glValidateProgramARB;
static glUniform1fARBPROC glUniform1fARB;
static glUniform2fARBPROC glUniform2fARB;
static glUniform3fARBPROC glUniform3fARB;
static glUniform4fARBPROC glUniform4fARB;
static glUniform1iARBPROC glUniform1iARB;
static glUniform2iARBPROC glUniform2iARB;
static glUniform3iARBPROC glUniform3iARB;
static glUniform4iARBPROC glUniform4iARB;
static glUniform1fvARBPROC glUniform1fvARB;
static glUniform2fvARBPROC glUniform2fvARB;
static glUniform3fvARBPROC glUniform3fvARB;
static glUniform4fvARBPROC glUniform4fvARB;
static glUniform1ivARBPROC glUniform1ivARB;
static glUniform2ivARBPROC glUniform2ivARB;
static glUniform3ivARBPROC glUniform3ivARB;
static glUniform4ivARBPROC glUniform4ivARB;
static glUniformMatrix2fvARBPROC glUniformMatrix2fvARB;
static glUniformMatrix3fvARBPROC glUniformMatrix3fvARB;
static glUniformMatrix4fvARBPROC glUniformMatrix4fvARB;
static glGetObjectParameterfvARBPROC glGetObjectParameterfvARB;
static glGetObjectParameterivARBPROC glGetObjectParameterivARB;
static glGetInfoLogARBPROC glGetInfoLogARB;
static glGetAttachedObjectsARBPROC glGetAttachedObjectsARB;
static glGetUniformLocationARBPROC glGetUniformLocationARB;
static glGetActiveUniformARBPROC glGetActiveUniformARB;
static glGetUniformfvARBPROC glGetUniformfvARB;
static glGetUniformivARBPROC glGetUniformivARB;
static glGetShaderSourceARBPROC glGetShaderSourceARB;

static const int initialSourcesSize = 8;
static int sourcesSize = initialSourcesSize;
static int sourceCount;
static GLcharARB** sources = new GLcharARB*[initialSourcesSize];
static GLint* sourcesLengths = new GLint[initialSourcesSize];

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glDeleteObjectARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDeleteObjectARB
	(JNIEnv * env, jclass clazz, jint obj)
{
	glDeleteObjectARB(obj);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glGetHandleARB
 */
static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glGetHandleARB
	(JNIEnv * env, jclass clazz, jint pname)
{
	GLhandleARB result = glGetHandleARB(pname);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glDetachObjectARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDetachObjectARB
	(JNIEnv * env, jclass clazz, jint containerObj, jint attachedObj)
{
	glDetachObjectARB(containerObj, attachedObj);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCreateShaderObjectARB
 */
static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateShaderObjectARB
	(JNIEnv * env, jclass clazz, jint shaderType)
{
	GLhandleARB result = glCreateShaderObjectARB(shaderType);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	initShaderSource
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_initShaderSource
	(JNIEnv * env, jclass clazz, jint count)
{
	sourceCount = count;

	if ( sourceCount > sourcesSize ) {
		sourcesSize = sourceCount * 2;

		delete sources;
		delete sourcesLengths;

		sources = new GLcharARB*[sourcesSize];
		sourcesLengths = new GLint[sourcesSize];
	}
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	setShaderString
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_setShaderString
	(JNIEnv * env, jclass clazz, jint index, jobject string, jint stringOffset, jint stringLength)
{
	GLcharARB *string_ptr = (GLcharARB *)((GLubyte *)env->GetDirectBufferAddress(string) + stringOffset);

	sources[index] = string_ptr;
	sourcesLengths[index] = stringLength;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglShaderSourceARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglShaderSourceARB
	(JNIEnv * env, jclass clazz, jint shaderObj)
{
	glShaderSourceARB(shaderObj, sourceCount, (const GLcharARB **)sources, (const GLint *)sourcesLengths);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCompileShaderARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCompileShaderARB
	(JNIEnv * env, jclass clazz, jint shaderObj)
{
	glCompileShaderARB(shaderObj);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCreateProgramObjectARB
 */
static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateProgramObjectARB
	(JNIEnv * env, jclass clazz)
{
	GLuint result = glCreateProgramObjectARB();
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glAttachObjectARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glAttachObjectARB
	(JNIEnv * env, jclass clazz, jint containerObj, jint obj)
{
	glAttachObjectARB(containerObj, obj);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glLinkProgramARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glLinkProgramARB
	(JNIEnv * env, jclass clazz, jint programObj)
{
	glLinkProgramARB(programObj);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUseProgramObjectARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUseProgramObjectARB
	(JNIEnv * env, jclass clazz, jint programObj)
{
	glUseProgramObjectARB(programObj);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glValidateProgramARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glValidateProgramARB
	(JNIEnv * env, jclass clazz, jint programObj)
{
	glValidateProgramARB(programObj);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform1fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0)
{
	glUniform1fARB(location, v0);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform2fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1)
{
	glUniform2fARB(location, v0, v1);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform3fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2)
{
	glUniform3fARB(location, v0, v1, v2);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform4fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2, jfloat v3)
{
	glUniform4fARB(location, v0, v1, v2, v3);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform1iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0)
{
	glUniform1iARB(location, v0);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform2iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1)
{
	glUniform2iARB(location, v0, v1);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform3iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1, jint v2)
{
	glUniform3iARB(location, v0, v1, v2);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform4iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1, jint v2, jint v3)
{
	glUniform4iARB(location, v0, v1, v2, v3);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform1fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform1fvARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform2fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform2fvARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform3fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform3fvARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform4fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform4fvARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform1ivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform1ivARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform2ivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform2ivARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform3ivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform3ivARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform4ivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform4ivARB(location, count, values_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix2fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix2fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix2fvARB(location, count, transpose, matrices_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix3fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix3fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix3fvARB(location, count, transpose, matrices_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix4fvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix4fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix4fvARB(location, count, transpose, matrices_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetObjectParameterfvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterfvARB
	(JNIEnv * env, jclass clazz, jint obj, jint pname, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetObjectParameterfvARB(obj, pname, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetObjectParameterivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterivARB
	(JNIEnv * env, jclass clazz, jint obj, jint pname, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetObjectParameterivARB(obj, pname, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetInfoLogARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetInfoLogARB
	(JNIEnv * env, jclass clazz, jint obj, jint maxLength, jobject length, jint lengthOffset, jobject infoLog, jint infoLogOffset)
{

	GLubyte *infoLog_ptr = (GLubyte *)env->GetDirectBufferAddress(infoLog) + infoLogOffset;

	if ( length == NULL ) {
		glGetInfoLogARB(obj, maxLength, NULL, infoLog_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetInfoLogARB(obj, maxLength, length_ptr, infoLog_ptr);
	}

	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetAttachedObjectsARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetAttachedObjectsARB
	(JNIEnv * env, jclass clazz, jint containerObj, jint maxCount, jobject count, jint countOffset, jobject obj, jint objOffset)
{

	GLuint *obj_ptr = (GLuint *)env->GetDirectBufferAddress(obj) + objOffset;

	if ( count == NULL ) {
		glGetAttachedObjectsARB(containerObj, maxCount, NULL, obj_ptr);
	} else {
		GLsizei *count_ptr = (GLsizei *)env->GetDirectBufferAddress(count) + countOffset;
		glGetAttachedObjectsARB(containerObj, maxCount, count_ptr, obj_ptr);
	}

	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformLocationARB
 */
static jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformLocationARB
	(JNIEnv * env, jclass clazz, jint programObj, jobject name, jint nameOffset)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	GLuint result = glGetUniformLocationARB(programObj, name_ptr);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetActiveUniformARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetActiveUniformARB
	(JNIEnv * env, jclass clazz, jint programObj, jint index, jint maxLength, jobject length, jint lengthOffset, jobject size, jint sizeOffset, jobject type, jint typeOffset, jobject name, jint nameOffset)
{

	GLint *size_ptr = (GLint *)env->GetDirectBufferAddress(size) + sizeOffset;
	GLenum *type_ptr = (GLenum *)env->GetDirectBufferAddress(type) + typeOffset;
	GLcharARB *name_ptr = (GLcharARB *)env->GetDirectBufferAddress(name) + nameOffset;

	if ( length == NULL ) {
		glGetActiveUniformARB(programObj, index, maxLength, (GLsizei *)NULL, size_ptr, type_ptr, name_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetActiveUniformARB(programObj, index, maxLength, length_ptr, size_ptr, type_ptr, name_ptr);
	}

	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformfvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformfvARB
	(JNIEnv * env, jclass clazz, jint programObj, jint location, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetUniformfvARB(programObj, location, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformivARB
	(JNIEnv * env, jclass clazz, jint programObj, jint location, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetUniformivARB(programObj, location, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetShaderSourceARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetShaderSourceARB
	(JNIEnv * env, jclass clazz, jint obj, jint maxLength, jobject length, jint lengthOffset, jobject source, jint sourceOffset)
{
	GLubyte *source_ptr = (GLubyte *)env->GetDirectBufferAddress(source) + sourceOffset;

	if ( length == NULL ) {
		glGetShaderSourceARB(obj, maxLength, NULL, source_ptr);
	} else {
		GLint *length_ptr = (GLint *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetShaderSourceARB(obj, maxLength, length_ptr, source_ptr);
	}

	
}

void extgl_InitARBShaderObjects(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"glDeleteObjectARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glDeleteObjectARB, "glDeleteObjectARB", (void**)&glDeleteObjectARB},
		{"glGetHandleARB", "(I)I", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glGetHandleARB, "glGetHandleARB", (void**)&glGetHandleARB},
		{"glDetachObjectARB", "(II)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glDetachObjectARB, "glDetachObjectARB", (void**)&glDetachObjectARB},
		{"glCreateShaderObjectARB", "(I)I", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glCreateShaderObjectARB, "glCreateShaderObjectARB", (void**)&glCreateShaderObjectARB},
		{"initShaderSource", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_initShaderSource, NULL, NULL},
		{"setShaderString", "(ILjava/nio/ByteBuffer;II)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_setShaderString, NULL, NULL},
		{"nglShaderSourceARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglShaderSourceARB, "glShaderSourceARB", (void**)&glShaderSourceARB},
		{"glCompileShaderARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glCompileShaderARB, "glCompileShaderARB", (void**)&glCompileShaderARB},
		{"glCreateProgramObjectARB", "()I", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glCreateProgramObjectARB, "glCreateProgramObjectARB", (void**)&glCreateProgramObjectARB},
		{"glAttachObjectARB", "(II)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glAttachObjectARB, "glAttachObjectARB", (void**)&glAttachObjectARB},
		{"glLinkProgramARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glLinkProgramARB, "glLinkProgramARB", (void**)&glLinkProgramARB},
		{"glUseProgramObjectARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUseProgramObjectARB, "glUseProgramObjectARB", (void**)&glUseProgramObjectARB},
		{"glValidateProgramARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glValidateProgramARB, "glValidateProgramARB", (void**)&glValidateProgramARB},
		{"glUniform1fARB", "(IF)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1fARB, "glUniform1fARB", (void**)&glUniform1fARB},
		{"glUniform2fARB", "(IFF)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2fARB, "glUniform2fARB", (void**)&glUniform2fARB},
		{"glUniform3fARB", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3fARB, "glUniform3fARB", (void**)&glUniform3fARB},
		{"glUniform4fARB", "(IFFFF)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4fARB, "glUniform4fARB", (void**)&glUniform4fARB},
		{"glUniform1iARB", "(II)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1iARB, "glUniform1iARB", (void**)&glUniform1iARB},
		{"glUniform2iARB", "(III)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2iARB, "glUniform2iARB", (void**)&glUniform2iARB},
		{"glUniform3iARB", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3iARB, "glUniform3iARB", (void**)&glUniform3iARB},
		{"glUniform4iARB", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4iARB, "glUniform4iARB", (void**)&glUniform4iARB},
		{"nglUniform1fvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fvARB, "glUniform1fvARB", (void**)&glUniform1fvARB},
		{"nglUniform2fvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fvARB, "glUniform2fvARB", (void**)&glUniform2fvARB},
		{"nglUniform3fvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fvARB, "glUniform3fvARB", (void**)&glUniform3fvARB},
		{"nglUniform4fvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fvARB, "glUniform4fvARB", (void**)&glUniform4fvARB},
		{"nglUniform1ivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1ivARB, "glUniform1ivARB", (void**)&glUniform1ivARB},
		{"nglUniform2ivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2ivARB, "glUniform2ivARB", (void**)&glUniform2ivARB},
		{"nglUniform3ivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3ivARB, "glUniform3ivARB", (void**)&glUniform3ivARB},
		{"nglUniform4ivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4ivARB, "glUniform4ivARB", (void**)&glUniform4ivARB},
		{"nglUniformMatrix2fvARB", "(IIZLjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix2fvARB, "glUniformMatrix2fvARB", (void**)&glUniformMatrix2fvARB},
		{"nglUniformMatrix3fvARB", "(IIZLjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix3fvARB, "glUniformMatrix3fvARB", (void**)&glUniformMatrix3fvARB},
		{"nglUniformMatrix4fvARB", "(IIZLjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix4fvARB, "glUniformMatrix4fvARB", (void**)&glUniformMatrix4fvARB},
		{"nglGetObjectParameterfvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterfvARB, "glGetObjectParameterfvARB", (void**)&glGetObjectParameterfvARB},
		{"nglGetObjectParameterivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterivARB, "glGetObjectParameterivARB", (void**)&glGetObjectParameterivARB},
		{"nglGetInfoLogARB", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetInfoLogARB, "glGetInfoLogARB", (void**)&glGetInfoLogARB},
		{"nglGetAttachedObjectsARB", "(IILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetAttachedObjectsARB, "glGetAttachedObjectsARB", (void**)&glGetAttachedObjectsARB},
		{"nglGetUniformLocationARB", "(ILjava/nio/ByteBuffer;I)I", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformLocationARB, "glGetUniformLocationARB", (void**)&glGetUniformLocationARB},
		{"nglGetActiveUniformARB", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetActiveUniformARB, "glGetActiveUniformARB", (void**)&glGetActiveUniformARB},
		{"nglGetUniformfvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformfvARB, "glGetUniformfvARB", (void**)&glGetUniformfvARB},
		{"nglGetUniformivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformivARB, "glGetUniformivARB", (void**)&glGetUniformivARB},
		{"nglGetShaderSourceARB", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBShaderObjects_nglGetShaderSourceARB, "glGetShaderSourceARB", (void**)&glGetShaderSourceARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/ARBShaderObjects");
	if (extgl_Extensions.GL_ARB_shader_objects)
		extgl_InitializeClass(env, clazz, ext_set, "GL_ARB_shader_objects", num_functions, functions);
}

