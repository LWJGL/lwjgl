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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVRegisterCombiners
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVRegisterCombiners.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	glCombinerParameterfNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_glCombinerParameterfNV
	(JNIEnv * env, jclass clazz, jint pname, jfloat param)
{
	CHECK_EXISTS(glCombinerParameterfNV)
	glCombinerParameterfNV(pname, param);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglCombinerParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglCombinerParameterfvNV
	(JNIEnv * env, jclass clazz, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glCombinerParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glCombinerParameterfvNV(pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	glCombinerParameteriNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_glCombinerParameteriNV
	(JNIEnv * env, jclass clazz, jint pname, jint param)
{
	CHECK_EXISTS(glCombinerParameteriNV)
	glCombinerParameteriNV(pname, param);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglCombinerParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglCombinerParameterivNV
	(JNIEnv * env, jclass clazz, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glCombinerParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glCombinerParameterivNV(pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	glCombinerInputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_glCombinerInputNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint variable, jint input, jint mapping, jint componentUsage)
{
	CHECK_EXISTS(glCombinerInputNV)
	glCombinerInputNV(stage, portion, variable, input, mapping, componentUsage);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	glCombinerOutputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_glCombinerOutputNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint abOutput, jint cdOutput, jint sumOutput, jint scale, jint bias, jboolean abDotProduct, jboolean cdDotProduct, jboolean muxSum)
{
	CHECK_EXISTS(glCombinerOutputNV)
	glCombinerOutputNV(stage, portion, abOutput, cdOutput, sumOutput, scale, bias, abDotProduct, cdDotProduct, muxSum);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	glFinalCombinerInputNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_glFinalCombinerInputNV
	(JNIEnv * env, jclass clazz, jint variable, jint input, jint mapping, jint componentUsage)
{
	CHECK_EXISTS(glFinalCombinerInputNV)
	glFinalCombinerInputNV(variable, input, mapping, componentUsage);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglGetCombinerInputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglGetCombinerInputParameterfvNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetCombinerInputParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetCombinerInputParameterfvNV(stage, portion, variable, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglGetCombinerInputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglGetCombinerInputParameterivNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetCombinerInputParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetCombinerInputParameterivNV(stage, portion, variable, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglGetCombinerOutputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglGetCombinerOutputParameterfvNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetCombinerOutputParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetCombinerOutputParameterfvNV(stage, portion, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglGetCombinerOutputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglGetCombinerOutputParameterivNV
	(JNIEnv * env, jclass clazz, jint stage, jint portion, jint pname, jobject piParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetCombinerOutputParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + pfParams_offset;
	glGetCombinerOutputParameterivNV(stage, portion, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglGetFinalCombinerInputParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglGetFinalCombinerInputParameterfvNV
	(JNIEnv * env, jclass clazz, jint variable, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetFinalCombinerInputParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetFinalCombinerInputParameterfvNV(variable, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglGetFinalCombinerInputParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglGetFinalCombinerInputParameterivNV
	(JNIEnv * env, jclass clazz, jint variable, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetFinalCombinerInputParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetFinalCombinerInputParameterivNV(variable, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglCombinerStageParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglCombinerStageParameterfvNV
	(JNIEnv * env, jclass clazz, jint stage, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glCombinerStageParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glCombinerStageParameterfvNV(stage, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVRegisterCombiners
 * Method:	nglGetCombinerStageParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVRegisterCombiners_nglGetCombinerStageParameterfvNV
	(JNIEnv * env, jclass clazz, jint stage, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetCombinerStageParameterfvNV)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetCombinerStageParameterfvNV(stage, pname, pfParams_ptr);
	CHECK_GL_ERROR
}
