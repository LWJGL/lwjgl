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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVOcclusionQuery
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVOcclusionQuery.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.nv.NVOcclusionQuery
 * Method:	nglGenOcclusionQueriesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVOcclusionQuery_nglGenOcclusionQueriesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piIDs, jint piIDs_offset)
{
	CHECK_EXISTS(glGenOcclusionQueriesNV)
	GLuint *piIDs_ptr = (GLuint *)env->GetDirectBufferAddress(piIDs) + piIDs_offset;
	glGenOcclusionQueriesNV(n, piIDs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVOcclusionQuery
 * Method:	nglDeleteOcclusionQueriesNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVOcclusionQuery_nglDeleteOcclusionQueriesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piIDs, jint piIDs_offset)
{
	CHECK_EXISTS(glDeleteOcclusionQueriesNV)
	GLuint *piIDs_ptr = (GLuint *)env->GetDirectBufferAddress(piIDs) + piIDs_offset;
	glDeleteOcclusionQueriesNV(n, piIDs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVOcclusionQuery
 * Method:	glIsOcclusionQueryNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_nv_NVOcclusionQuery_glIsOcclusionQueryNV
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glIsOcclusionQueryNV)
	GLboolean result = glIsOcclusionQueryNV(id);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.nv.NVOcclusionQuery
 * Method:	glBeginOcclusionQueryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVOcclusionQuery_glBeginOcclusionQueryNV
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glBeginOcclusionQueryNV)
	glBeginOcclusionQueryNV(id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVOcclusionQuery
 * Method:	glEndOcclusionQueryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVOcclusionQuery_glEndOcclusionQueryNV
	(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glEndOcclusionQueryNV)
	glEndOcclusionQueryNV();
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVOcclusionQuery
 * Method:	nglGetOcclusionQueryivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVOcclusionQuery_nglGetOcclusionQueryivNV
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetOcclusionQueryivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetOcclusionQueryivNV(id, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVOcclusionQuery
 * Method:	nglGetOcclusionQueryuivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVOcclusionQuery_nglGetOcclusionQueryuivNV
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetOcclusionQueryuivNV)
	GLuint *piParams_ptr = (GLuint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetOcclusionQueryuivNV(id, pname, piParams_ptr);
	CHECK_GL_ERROR
}
