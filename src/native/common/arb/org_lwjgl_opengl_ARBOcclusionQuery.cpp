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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBOcclusionQuery
// ----------------------------------

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

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	nglGenQueriesARB
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGenQueriesARB
	(JNIEnv * env, jclass clazz, jint n, jobject ids, jint idsOffset)
{
	GLuint *ids_ptr = (GLuint *)env->GetDirectBufferAddress(ids) + idsOffset;
	glGenQueriesARB(n, ids_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	nglDeleteQueriesARB
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglDeleteQueriesARB
	(JNIEnv * env, jclass clazz, jint n, jobject ids, jint idsOffset)
{
	GLuint *ids_ptr = (GLuint *)env->GetDirectBufferAddress(ids) + idsOffset;
	glDeleteQueriesARB(n, ids_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	glIsQueryARB
 */
static JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_glIsQueryARB
	(JNIEnv * env, jclass clazz, jint id)
{
	GLboolean result = glIsQueryARB(id);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	glBeginQueryARB
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_glBeginQueryARB
	(JNIEnv * env, jclass clazz, jint target, jint id)
{
	glBeginQueryARB(target, id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	glEndQueryARB
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_glEndQueryARB
	(JNIEnv * env, jclass clazz, jint target)
{
	glEndQueryARB(target);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	nglGetQueryivARB
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryivARB
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryivARB(target, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	nglGetQueryObjectivARB
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectivARB
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryObjectivARB(id, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBOcclusionQuery
 * Method:	nglGetQueryObjectuivARB
 */
static JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectuivARB
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject params, jint paramsOffset)
{
	GLuint *params_ptr = (GLuint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryObjectuivARB(id, pname, params_ptr);
	CHECK_GL_ERROR
}

void extgl_InitARBOcclusionQuery(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"nglGenQueriesARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGenQueriesARB, "glGenQueriesARB", (void**)&glGenQueriesARB},
		{"nglDeleteQueriesARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglDeleteQueriesARB, "glDeleteQueriesARB", (void**)&glDeleteQueriesARB},
		{"glIsQueryARB", "(I)Z", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_glIsQueryARB, "glIsQueryARB", (void**)&glIsQueryARB},
		{"glBeginQueryARB", "(II)V", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_glBeginQueryARB, "glBeginQueryARB", (void**)&glBeginQueryARB},
		{"glEndQueryARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_glEndQueryARB, "glEndQueryARB", (void**)&glEndQueryARB},
		{"nglGetQueryivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryivARB, "glGetQueryivARB", (void**)&glGetQueryivARB},
		{"nglGetQueryObjectivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectivARB, "glGetQueryObjectivARB", (void**)&glGetQueryObjectivARB},
		{"nglGetQueryObjectuivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBOcclusionQuery_nglGetQueryObjectuivARB, "glGetQueryObjectuivARB", (void**)&glGetQueryObjectuivARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/ARBOcclusionQuery");
	if (extgl_Extensions.GL_ARB_occlusion_query)
		extgl_InitializeClass(env, clazz, ext_set, "GL_ARB_occlusion_query", num_functions, functions);
}

