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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVEvaluators
// ----------------------------------

#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLint uorder, GLint vorder, GLboolean packed, const GLvoid *points);
typedef void (APIENTRY * glMapParameterivNVPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glMapParameterfvNVPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glGetMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLboolean packed, GLvoid *points);
typedef void (APIENTRY * glGetMapParameterivNVPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetMapParameterfvNVPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetMapAttribParameterivNVPROC) (GLenum target, GLuint index, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetMapAttribParameterfvNVPROC) (GLenum target, GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glEvalMapsNVPROC) (GLenum target, GLenum mode);

static glMapControlPointsNVPROC glMapControlPointsNV;
static glMapParameterivNVPROC glMapParameterivNV;
static glMapParameterfvNVPROC glMapParameterfvNV;
static glGetMapControlPointsNVPROC glGetMapControlPointsNV;
static glGetMapParameterivNVPROC glGetMapParameterivNV;
static glGetMapParameterfvNVPROC glGetMapParameterfvNV;
static glGetMapAttribParameterivNVPROC glGetMapAttribParameterivNV;
static glGetMapAttribParameterfvNVPROC glGetMapAttribParameterfvNV;
static glEvalMapsNVPROC glEvalMapsNV;

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglGetMapControlPointsNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapControlPointsNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jboolean packed, jobject pPoints, jint pPoints_offset)
{
	GLvoid *pPoints_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPoints) + pPoints_offset);
	glGetMapControlPointsNV(target, index, type, ustride, vstride, packed, pPoints_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglMapControlPointsNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapControlPointsNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jint uorder, jint vorder, jboolean packed, jobject pPoints, jint pPoints_offset)
{
	GLvoid *pPoints_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPoints) + pPoints_offset);
	glMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, pPoints_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglMapParameterfvNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glMapParameterfvNV(target, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglMapParameterivNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterivNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glMapParameterivNV(target, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglGetMapParameterfvNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetMapParameterfvNV(target, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglGetMapParameterivNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterivNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetMapParameterivNV(target, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglGetMapAttribParameterfvNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetMapAttribParameterfvNV(target, index, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	nglGetMapAttribParameterivNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterivNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetMapAttribParameterivNV(target, index, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVEvaluators
 * Method:	glEvalMapsNV
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVEvaluators_glEvalMapsNV
	(JNIEnv * env, jclass clazz, jint target, jint mode)
{
	glEvalMapsNV(target, mode);
	CHECK_GL_ERROR
}

void extgl_InitNVEvaluators(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"nglGetMapControlPointsNV", "(IIIIIZLjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapControlPointsNV, "glGetMapControlPointsNV", (void**)&glGetMapControlPointsNV},
		{"nglMapControlPointsNV", "(IIIIIIIZLjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglMapControlPointsNV, "glMapControlPointsNV", (void**)&glMapControlPointsNV},
		{"nglMapParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterfvNV, "glMapParameterfvNV", (void**)&glMapParameterfvNV},
		{"nglMapParameterivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglMapParameterivNV, "glMapParameterivNV", (void**)&glMapParameterivNV},
		{"nglGetMapParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterfvNV, "glGetMapParameterfvNV", (void**)&glGetMapParameterfvNV},
		{"nglGetMapParameterivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapParameterivNV, "glGetMapParameterivNV", (void**)&glGetMapParameterivNV},
		{"nglGetMapAttribParameterfvNV", "(IIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterfvNV, "glGetMapAttribParameterfvNV", (void**)&glGetMapAttribParameterfvNV},
		{"nglGetMapAttribParameterivNV", "(IIILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_nglGetMapAttribParameterivNV, "glGetMapAttribParameterivNV", (void**)&glGetMapAttribParameterivNV},
		{"glEvalMapsNV", "(II)V", (void*)&Java_org_lwjgl_opengl_NVEvaluators_glEvalMapsNV, "glEvalMapsNV", (void**)&glEvalMapsNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/NVEvaluators");
	if (extgl_Extensions.GL_NV_evaluators)
		extgl_InitializeClass(env, clazz, ext_set, "GL_NV_evaluators", num_functions, functions);
}
