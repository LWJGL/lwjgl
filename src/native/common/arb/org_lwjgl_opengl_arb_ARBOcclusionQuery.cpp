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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.arb.ARBOcclusionQuery
// ----------------------------------

#include "org_lwjgl_opengl_arb_ARBOcclusionQuery.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glGenQueriesARBPROC) (GLsizei n, GLuint *ids);
typedef void (APIENTRY * glDeleteQueriesARBPROC) (GLsizei n, const GLuint *ids);
typedef GLboolean (APIENTRY * glIsQueryARBPROC) (GLuint id);
typedef void (APIENTRY * glBeginQueryARBPROC) (GLenum target, GLuint id);
typedef void (APIENTRY * glEndQueryARBPROC) (GLenum target);
typedef void (APIENTRY * glGetQueryivARBPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetQueryObjectivARBPROC) (GLuint id, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetQueryObjectuivARBPROC) (GLuint id, GLenum pname, GLuint *params);

static glGenQueriesARBPROC glGenQueriesARB;
static glDeleteQueriesARBPROC glDeleteQueriesARB;
static glIsQueryARBPROC glIsQueryARB;
static glBeginQueryARBPROC glBeginQueryARB;
static glEndQueryARBPROC glEndQueryARB;
static glGetQueryivARBPROC glGetQueryivARB;
static glGetQueryObjectivARBPROC glGetQueryObjectivARB;
static glGetQueryObjectuivARBPROC glGetQueryObjectuivARB;

void extgl_InitARBOcclusionQuery(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_occlusion_query)
		return;

	glGenQueriesARB = (glGenQueriesARBPROC) extgl_GetProcAddress("glGenQueriesARB");
	glDeleteQueriesARB = (glDeleteQueriesARBPROC) extgl_GetProcAddress("glDeleteQueriesARB");
	glIsQueryARB = (glIsQueryARBPROC) extgl_GetProcAddress("glIsQueryARB");
	glBeginQueryARB = (glBeginQueryARBPROC) extgl_GetProcAddress("glBeginQueryARB");
	glEndQueryARB = (glEndQueryARBPROC) extgl_GetProcAddress("glEndQueryARB");
	glGetQueryivARB = (glGetQueryivARBPROC) extgl_GetProcAddress("glGetQueryivARB");
	glGetQueryObjectivARB = (glGetQueryObjectivARBPROC) extgl_GetProcAddress("glGetQueryObjectivARB");
	glGetQueryObjectuivARB = (glGetQueryObjectuivARBPROC) extgl_GetProcAddress("glGetQueryObjectuivARB");

	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_occlusion_query)
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	nglGenQueriesARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_nglGenQueriesARB
	(JNIEnv * env, jclass clazz, jint n, jobject ids, jint idsOffset)
{
	CHECK_EXISTS(glGenQueriesARB)
	GLuint *ids_ptr = (GLuint *)env->GetDirectBufferAddress(ids) + idsOffset;
	glGenQueriesARB(n, ids_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	nglDeleteQueriesARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_nglDeleteQueriesARB
	(JNIEnv * env, jclass clazz, jint n, jobject ids, jint idsOffset)
{
	CHECK_EXISTS(glDeleteQueriesARB)
	GLuint *ids_ptr = (GLuint *)env->GetDirectBufferAddress(ids) + idsOffset;
	glDeleteQueriesARB(n, ids_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	glIsQueryARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_glIsQueryARB
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glIsQueryARB)
	GLboolean result = glIsQueryARB(id);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	glBeginQueryARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_glBeginQueryARB
	(JNIEnv * env, jclass clazz, jint target, jint id)
{
	CHECK_EXISTS(glBeginQueryARB)
	glBeginQueryARB(target, id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	glEndQueryARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_glEndQueryARB
	(JNIEnv * env, jclass clazz, jint target)
{
	CHECK_EXISTS(glEndQueryARB)
	glEndQueryARB(target);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	nglGetQueryivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_nglGetQueryivARB
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetQueryivARB)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryivARB(target, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	nglGetQueryObjectivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_nglGetQueryObjectiv
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetQueryObjectivARB)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryObjectivARB(id, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBOcclusionQuery
 * Method:	nglGetQueryObjectuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBOcclusionQuery_nglGetQueryObjectuivARB
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetQueryObjectuivARB)
	GLuint *params_ptr = (GLuint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryObjectuivARB(id, pname, params_ptr);
	CHECK_GL_ERROR
}
