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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBShaderObjects
// ----------------------------------

#include "org_lwjgl_opengl_ARBShaderObjects.h"
#include "extgl.h"
#include "checkGLerror.h"

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

void extgl_InitARBShaderObjects(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_shader_objects)
		return;

	glDeleteObjectARB = (glDeleteObjectARBPROC) extgl_GetProcAddress("glDeleteObjectARB");;
	glGetHandleARB = (glGetHandleARBPROC) extgl_GetProcAddress("glGetHandleARB");
	glDetachObjectARB = (glDetachObjectARBPROC) extgl_GetProcAddress("glDetachObjectARB");
	glCreateShaderObjectARB = (glCreateShaderObjectARBPROC) extgl_GetProcAddress("glCreateShaderObjectARB");
	glShaderSourceARB = (glShaderSourceARBPROC) extgl_GetProcAddress("glShaderSourceARB");
	glCompileShaderARB = (glCompileShaderARBPROC) extgl_GetProcAddress("glCompileShaderARB");
	glCreateProgramObjectARB = (glCreateProgramObjectARBPROC) extgl_GetProcAddress("glCreateProgramObjectARB");
	glAttachObjectARB = (glAttachObjectARBPROC) extgl_GetProcAddress("glAttachObjectARB");
	glLinkProgramARB = (glLinkProgramARBPROC) extgl_GetProcAddress("glLinkProgramARB");
	glUseProgramObjectARB = (glUseProgramObjectARBPROC) extgl_GetProcAddress("glUseProgramObjectARB");
	glValidateProgramARB = (glValidateProgramARBPROC) extgl_GetProcAddress("glValidateProgramARB");
	glUniform1fARB = (glUniform1fARBPROC) extgl_GetProcAddress("glUniform1fARB");
	glUniform2fARB = (glUniform2fARBPROC) extgl_GetProcAddress("glUniform2fARB");
	glUniform3fARB = (glUniform3fARBPROC) extgl_GetProcAddress("glUniform3fARB");
	glUniform4fARB = (glUniform4fARBPROC) extgl_GetProcAddress("glUniform4fARB");
	glUniform1iARB = (glUniform1iARBPROC) extgl_GetProcAddress("glUniform1iARB");
	glUniform2iARB = (glUniform2iARBPROC) extgl_GetProcAddress("glUniform2iARB");
	glUniform3iARB = (glUniform3iARBPROC) extgl_GetProcAddress("glUniform3iARB");
	glUniform4iARB = (glUniform4iARBPROC) extgl_GetProcAddress("glUniform4iARB");
	glUniform1fvARB = (glUniform1fvARBPROC) extgl_GetProcAddress("glUniform1fvARB");
	glUniform2fvARB = (glUniform2fvARBPROC) extgl_GetProcAddress("glUniform2fvARB");
	glUniform3fvARB = (glUniform3fvARBPROC) extgl_GetProcAddress("glUniform3fvARB");
	glUniform4fvARB = (glUniform4fvARBPROC) extgl_GetProcAddress("glUniform4fvARB");
	glUniform1ivARB = (glUniform1ivARBPROC ) extgl_GetProcAddress("glUniform1ivARB");
	glUniform2ivARB = (glUniform2ivARBPROC) extgl_GetProcAddress("glUniform2ivARB");
	glUniform3ivARB = (glUniform3ivARBPROC) extgl_GetProcAddress("glUniform3ivARB");
	glUniform4ivARB = (glUniform4ivARBPROC) extgl_GetProcAddress("glUniform4ivARB");
	glUniformMatrix2fvARB = (glUniformMatrix2fvARBPROC) extgl_GetProcAddress("glUniformMatrix2fvARB");
	glUniformMatrix3fvARB = (glUniformMatrix3fvARBPROC) extgl_GetProcAddress("glUniformMatrix3fvARB");
	glUniformMatrix4fvARB = (glUniformMatrix4fvARBPROC) extgl_GetProcAddress("glUniformMatrix4fvARB");
	glGetObjectParameterfvARB = (glGetObjectParameterfvARBPROC) extgl_GetProcAddress("glGetObjectParameterfvARB");
	glGetObjectParameterivARB = (glGetObjectParameterivARBPROC) extgl_GetProcAddress("glGetObjectParameterivARB");
	glGetInfoLogARB = (glGetInfoLogARBPROC) extgl_GetProcAddress("glGetInfoLogARB");
	glGetAttachedObjectsARB = (glGetAttachedObjectsARBPROC) extgl_GetProcAddress("glGetAttachedObjectsARB");
	glGetUniformLocationARB = (glGetUniformLocationARBPROC) extgl_GetProcAddress("glGetUniformLocationARB");
	glGetActiveUniformARB = (glGetActiveUniformARBPROC) extgl_GetProcAddress("glGetActiveUniformARB");
	glGetUniformfvARB = (glGetUniformfvARBPROC) extgl_GetProcAddress("glGetUniformfvARB");
	glGetUniformivARB = (glGetUniformivARBPROC) extgl_GetProcAddress("glGetUniformivARB");
	glGetShaderSourceARB = (glGetShaderSourceARBPROC) extgl_GetProcAddress("glGetShaderSourceARB");

	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_shader_objects)
}

int sourceCount;
int sourcesSize = 8;
GLcharARB** sources = new GLcharARB*[sourcesSize];
GLint* sourcesLengths = new GLint[sourcesSize];

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glDeleteObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDeleteObjectARB
	(JNIEnv * env, jclass clazz, jint obj)
{
	CHECK_EXISTS(glDeleteObjectARB)
	glDeleteObjectARB(obj);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glGetHandleARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glGetHandleARB
	(JNIEnv * env, jclass clazz, jint pname)
{
	CHECK_EXISTS(glGetHandleARB)
	GLhandleARB result = glGetHandleARB(pname);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glDetachObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDetachObjectARB
	(JNIEnv * env, jclass clazz, jint containerObj, jint attachedObj)
{
	CHECK_EXISTS(glDetachObjectARB)
	glDetachObjectARB(containerObj, attachedObj);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCreateShaderObjectARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateShaderObjectARB
	(JNIEnv * env, jclass clazz, jint shaderType)
{
	CHECK_EXISTS(glCreateShaderObjectARB)
	GLhandleARB result = glCreateShaderObjectARB(shaderType);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	initShaderSource
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_initShaderSource
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_setShaderString
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglShaderSourceARB
	(JNIEnv * env, jclass clazz, jint shaderObj)
{
	CHECK_EXISTS(glShaderSourceARB)
	glShaderSourceARB(shaderObj, sourceCount, (const GLcharARB **)sources, (const GLint *)sourcesLengths);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCompileShaderARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCompileShaderARB
	(JNIEnv * env, jclass clazz, jint shaderObj)
{
	CHECK_EXISTS(glCompileShaderARB)
	glCompileShaderARB(shaderObj);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCreateProgramObjectARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateProgramObjectARB
	(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glCreateProgramObjectARB)
	GLuint result = glCreateProgramObjectARB();
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glAttachObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glAttachObjectARB
	(JNIEnv * env, jclass clazz, jint containerObj, jint obj)
{
	CHECK_EXISTS(glAttachObjectARB)
	glAttachObjectARB(containerObj, obj);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glLinkProgramARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glLinkProgramARB
	(JNIEnv * env, jclass clazz, jint programObj)
{
	CHECK_EXISTS(glLinkProgramARB)
	glLinkProgramARB(programObj);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUseProgramObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUseProgramObjectARB
	(JNIEnv * env, jclass clazz, jint programObj)
{
	CHECK_EXISTS(glUseProgramObjectARB)
	glUseProgramObjectARB(programObj);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glValidateProgramARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glValidateProgramARB
	(JNIEnv * env, jclass clazz, jint programObj)
{
	CHECK_EXISTS(glValidateProgramARB)
	glValidateProgramARB(programObj);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0)
{
	CHECK_EXISTS(glUniform1fARB)
	glUniform1fARB(location, v0);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1)
{
	CHECK_EXISTS(glUniform2fARB)
	glUniform2fARB(location, v0, v1);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2)
{
	CHECK_EXISTS(glUniform3fARB)
	glUniform3fARB(location, v0, v1, v2);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4fARB
	(JNIEnv * env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2, jfloat v3)
{
	CHECK_EXISTS(glUniform4fARB)
	glUniform4fARB(location, v0, v1, v2, v3);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform1iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0)
{
	CHECK_EXISTS(glUniform1iARB)
	glUniform1iARB(location, v0);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1)
{
	CHECK_EXISTS(glUniform2iARB)
	glUniform2iARB(location, v0, v1);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1, jint v2)
{
	CHECK_EXISTS(glUniform3iARB)
	glUniform3iARB(location, v0, v1, v2);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform4iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4iARB
	(JNIEnv * env, jclass clazz, jint location, jint v0, jint v1, jint v2, jint v3)
{
	CHECK_EXISTS(glUniform4iARB)
	glUniform4iARB(location, v0, v1, v2, v3);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform1fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform1fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform1fvARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform2fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform2fvARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform3fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform3fvARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform4fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform4fvARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform1ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform1ivARB)
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform1ivARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform2ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform2ivARB)
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform2ivARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform3ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform3ivARB)
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform3ivARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform4ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4ivARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glUniform4ivARB)
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glUniform4ivARB(location, count, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix2fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	CHECK_EXISTS(glUniformMatrix2fvARB)
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix2fvARB(location, count, transpose, matrices_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix3fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	CHECK_EXISTS(glUniformMatrix3fvARB)
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix3fvARB(location, count, transpose, matrices_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix4fvARB
	(JNIEnv * env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matricesOffset)
{
	CHECK_EXISTS(glUniformMatrix4fvARB)
	GLfloat *matrices_ptr = (GLfloat *)env->GetDirectBufferAddress(matrices) + matricesOffset;
	glUniformMatrix4fvARB(location, count, transpose, matrices_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetObjectParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterfvARB
	(JNIEnv * env, jclass clazz, jint obj, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetObjectParameterfvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetObjectParameterfvARB(obj, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetObjectParameterivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterivARB
	(JNIEnv * env, jclass clazz, jint obj, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetObjectParameterivARB)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetObjectParameterivARB(obj, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetInfoLogARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetInfoLogARB
	(JNIEnv * env, jclass clazz, jint obj, jint maxLength, jobject length, jint lengthOffset, jobject infoLog, jint infoLogOffset)
{
	CHECK_EXISTS(glGetInfoLogARB)

	GLubyte *infoLog_ptr = (GLubyte *)env->GetDirectBufferAddress(infoLog) + infoLogOffset;

	if ( length == NULL ) {
		glGetInfoLogARB(obj, maxLength, NULL, infoLog_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetInfoLogARB(obj, maxLength, length_ptr, infoLog_ptr);
	}

	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetAttachedObjectsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetAttachedObjectsARB
	(JNIEnv * env, jclass clazz, jint containerObj, jint maxCount, jobject count, jint countOffset, jobject obj, jint objOffset)
{
	CHECK_EXISTS(glGetAttachedObjectsARB)

	GLuint *obj_ptr = (GLuint *)env->GetDirectBufferAddress(obj) + objOffset;

	if ( count == NULL ) {
		glGetAttachedObjectsARB(containerObj, maxCount, NULL, obj_ptr);
	} else {
		GLsizei *count_ptr = (GLsizei *)env->GetDirectBufferAddress(count) + countOffset;
		glGetAttachedObjectsARB(containerObj, maxCount, count_ptr, obj_ptr);
	}

	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformLocationARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformLocationARB
	(JNIEnv * env, jclass clazz, jint programObj, jobject name, jint nameOffset)
{
	CHECK_EXISTS(glGetUniformLocationARB)
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	GLuint result = glGetUniformLocationARB(programObj, name_ptr);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetActiveUniformARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetActiveUniformARB
	(JNIEnv * env, jclass clazz, jint programObj, jint index, jint maxLength, jobject length, jint lengthOffset, jobject size, jint sizeOffset, jobject type, jint typeOffset, jobject name, jint nameOffset)
{
	CHECK_EXISTS(glGetActiveUniformARB)

	GLint *size_ptr = (GLint *)env->GetDirectBufferAddress(size) + sizeOffset;
	GLenum *type_ptr = (GLenum *)env->GetDirectBufferAddress(type) + typeOffset;
	GLcharARB *name_ptr = (GLcharARB *)env->GetDirectBufferAddress(name) + nameOffset;

	if ( length == NULL ) {
		glGetActiveUniformARB(programObj, index, maxLength, (GLsizei *)NULL, size_ptr, type_ptr, name_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetActiveUniformARB(programObj, index, maxLength, length_ptr, size_ptr, type_ptr, name_ptr);
	}

	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformfvARB
	(JNIEnv * env, jclass clazz, jint programObj, jint location, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetUniformfvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetUniformfvARB(programObj, location, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformivARB
	(JNIEnv * env, jclass clazz, jint programObj, jint location, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetUniformivARB)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetUniformivARB(programObj, location, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetShaderSourceARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetShaderSourceARB
	(JNIEnv * env, jclass clazz, jint obj, jint maxLength, jobject length, jint lengthOffset, jobject source, jint sourceOffset)
{
	CHECK_EXISTS(glGetShaderSourceARB)

	GLubyte *source_ptr = (GLubyte *)env->GetDirectBufferAddress(source) + sourceOffset;

	if ( length == NULL ) {
		glGetShaderSourceARB(obj, maxLength, NULL, source_ptr);
	} else {
		GLint *length_ptr = (GLint *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetShaderSourceARB(obj, maxLength, length_ptr, source_ptr);
	}

	CHECK_GL_ERROR
}
