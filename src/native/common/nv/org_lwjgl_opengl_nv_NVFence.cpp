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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVFence
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVFence.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.nv.NVFence
 * Method:	nglGenFencesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFence_nglGenFencesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piFences, jint piFences_offset)
{
	CHECK_EXISTS(glGenFencesNV)
	GLuint *piFences_ptr = (GLuint *)env->GetDirectBufferAddress(piFences) + piFences_offset;
	glGenFencesNV(n, piFences_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFence
 * Method:	nglDeleteFencesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFence_nglDeleteFencesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piFences, jint piFences_offset)
{
	CHECK_EXISTS(glDeleteFencesNV)
	GLuint *piFences_ptr = (GLuint *)env->GetDirectBufferAddress(piFences) + piFences_offset;
	glDeleteFencesNV(n, piFences_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFence
 * Method:	glSetFenceNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFence_glSetFenceNV
	(JNIEnv * env, jclass clazz, jint fence, jint condition)
{
	CHECK_EXISTS(glSetFenceNV)
	glSetFenceNV(fence, condition);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFence
 * Method:	glTestFenceNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_nv_NVFence_glTestFenceNV
	(JNIEnv * env, jclass clazz, jint fence)
{
	CHECK_EXISTS(glTestFenceNV)
	GLboolean result = glTestFenceNV(fence);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFence
 * Method:	glFinishFenceNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFence_glFinishFenceNV
	(JNIEnv * env, jclass clazz, jint fence)
{
	CHECK_EXISTS(glFinishFenceNV)
	glFinishFenceNV(fence);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFence
 * Method:	glIsFenceNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_nv_NVFence_glIsFenceNV
	(JNIEnv * env, jclass clazz, jint fence)
{
	CHECK_EXISTS(glIsFenceNV)
	GLboolean result = glIsFenceNV(fence);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.nv.NVFence
 * Method:	nglGetFenceivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVFence_nglGetFenceivNV
	(JNIEnv * env, jclass clazz, jint fence, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetFenceivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetFenceivNV(fence, pname, piParams_ptr);
	CHECK_GL_ERROR
}
