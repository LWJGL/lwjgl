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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVEvaluators
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVEvaluators.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglGetMapControlPointsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglGetMapControlPointsNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jboolean packed, jobject pPoints, jint pPoints_offset)
{
	CHECK_EXISTS(glGetMapControlPointsNV)
	GLvoid *pPoints_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPoints) + pPoints_offset);
	glGetMapControlPointsNV(target, index, type, ustride, vstride, packed, pPoints_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglMapControlPointsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglMapControlPointsNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint type, jint ustride, jint vstride, jint uorder, jint vorder, jboolean packed, jobject pPoints, jint pPoints_offset)
{
	CHECK_EXISTS(glMapControlPointsNV)
	GLvoid *pPoints_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPoints) + pPoints_offset);
	glMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, pPoints_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglMapParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglMapParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glMapParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glMapParameterfvNV(target, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglMapParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglMapParameterivNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glMapParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glMapParameterivNV(target, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglGetMapParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglGetMapParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetMapParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetMapParameterfvNV(target, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglGetMapParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglGetMapParameterivNV
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetMapParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetMapParameterivNV(target, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglGetMapAttribParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglGetMapAttribParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetMapAttribParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetMapAttribParameterfvNV(target, index, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	nglGetMapAttribParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_nglGetMapAttribParameterivNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetMapAttribParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetMapAttribParameterivNV(target, index, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVEvaluators
 * Method:	glEvalMapsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVEvaluators_glEvalMapsNV
	(JNIEnv * env, jclass clazz, jint target, jint mode)
{
	CHECK_EXISTS(glEvalMapsNV)
	glEvalMapsNV(target, mode);
	CHECK_GL_ERROR
}
