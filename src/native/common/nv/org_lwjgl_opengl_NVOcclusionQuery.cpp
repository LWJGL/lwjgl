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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVOcclusionQuery
// ----------------------------------

#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glGenOcclusionQueriesNVPROC) (GLsizei n, GLuint *ids);
typedef void (APIENTRY * glDeleteOcclusionQueriesNVPROC) (GLsizei n, const GLuint *ids);
typedef GLboolean (APIENTRY * glIsOcclusionQueryNVPROC) (GLuint id);
typedef void (APIENTRY * glBeginOcclusionQueryNVPROC) (GLuint id);
typedef void (APIENTRY * glEndOcclusionQueryNVPROC) (void);
typedef void (APIENTRY * glGetOcclusionQueryivNVPROC) (GLuint id, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetOcclusionQueryuivNVPROC) (GLuint id, GLenum pname, GLuint *params);

static glGenOcclusionQueriesNVPROC glGenOcclusionQueriesNV;
static glDeleteOcclusionQueriesNVPROC glDeleteOcclusionQueriesNV;
static glIsOcclusionQueryNVPROC glIsOcclusionQueryNV;
static glBeginOcclusionQueryNVPROC glBeginOcclusionQueryNV;
static glEndOcclusionQueryNVPROC glEndOcclusionQueryNV;
static glGetOcclusionQueryivNVPROC glGetOcclusionQueryivNV;
static glGetOcclusionQueryuivNVPROC glGetOcclusionQueryuivNV;

/*
 * Class:	org.lwjgl.opengl.NVOcclusionQuery
 * Method:	nglGenOcclusionQueriesNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglGenOcclusionQueriesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piIDs, jint piIDs_offset)
{
	GLuint *piIDs_ptr = (GLuint *)env->GetDirectBufferAddress(piIDs) + piIDs_offset;
	glGenOcclusionQueriesNV(n, piIDs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVOcclusionQuery
 * Method:	nglDeleteOcclusionQueriesNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglDeleteOcclusionQueriesNV
	(JNIEnv * env, jclass clazz, jint n, jobject piIDs, jint piIDs_offset)
{
	GLuint *piIDs_ptr = (GLuint *)env->GetDirectBufferAddress(piIDs) + piIDs_offset;
	glDeleteOcclusionQueriesNV(n, piIDs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVOcclusionQuery
 * Method:	glIsOcclusionQueryNV
 */
static jboolean JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_glIsOcclusionQueryNV
	(JNIEnv * env, jclass clazz, jint id)
{
	GLboolean result = glIsOcclusionQueryNV(id);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.NVOcclusionQuery
 * Method:	glBeginOcclusionQueryNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_glBeginOcclusionQueryNV
	(JNIEnv * env, jclass clazz, jint id)
{
	glBeginOcclusionQueryNV(id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVOcclusionQuery
 * Method:	glEndOcclusionQueryNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_glEndOcclusionQueryNV
	(JNIEnv * env, jclass clazz)
{
	glEndOcclusionQueryNV();
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVOcclusionQuery
 * Method:	nglGetOcclusionQueryivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryivNV
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetOcclusionQueryivNV(id, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVOcclusionQuery
 * Method:	nglGetOcclusionQueryuivNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryuivNV
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject piParams, jint piParams_offset)
{
	GLuint *piParams_ptr = (GLuint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetOcclusionQueryuivNV(id, pname, piParams_ptr);
	CHECK_GL_ERROR
}

void extgl_InitNVOcclusionQuery(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"nglGenOcclusionQueriesNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglGenOcclusionQueriesNV, "glGenOcclusionQueriesNV", (void**)&glGenOcclusionQueriesNV},
		{"nglDeleteOcclusionQueriesNV", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglDeleteOcclusionQueriesNV, "glDeleteOcclusionQueriesNV", (void**)&glDeleteOcclusionQueriesNV},
		{"glIsOcclusionQueryNV", "(I)Z", (void*)&Java_org_lwjgl_opengl_NVOcclusionQuery_glIsOcclusionQueryNV, "glIsOcclusionQueryNV", (void**)&glIsOcclusionQueryNV},
		{"glBeginOcclusionQueryNV", "(I)V", (void*)&Java_org_lwjgl_opengl_NVOcclusionQuery_glBeginOcclusionQueryNV, "glBeginOcclusionQueryNV", (void**)&glBeginOcclusionQueryNV},
		{"glEndOcclusionQueryNV", "()V", (void*)&Java_org_lwjgl_opengl_NVOcclusionQuery_glEndOcclusionQueryNV, "glEndOcclusionQueryNV", (void**)&glEndOcclusionQueryNV},
		{"nglGetOcclusionQueryivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryivNV, "glGetOcclusionQueryivNV", (void**)&glGetOcclusionQueryivNV},
		{"nglGetOcclusionQueryuivNV", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVOcclusionQuery_nglGetOcclusionQueryuivNV, "glGetOcclusionQueryuivNV", (void**)&glGetOcclusionQueryuivNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/NVOcclusionQuery");
	if (extgl_Extensions.GL_NV_occlusion_query)
		extgl_InitializeClass(env, clazz, ext_set, "GL_NV_occlusion_query", num_functions, functions);
}
