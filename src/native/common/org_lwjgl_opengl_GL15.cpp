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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.GL15
// ----------------------------------

#include "org_lwjgl_opengl_GL15.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef int GLintptr;
typedef unsigned int GLsizeiptr;

typedef void (APIENTRY * glBindBufferPROC) (GLenum target, GLuint buffer);
typedef void (APIENTRY * glDeleteBuffersPROC) (GLsizei n, const GLuint *buffers);
typedef void (APIENTRY * glGenBuffersPROC) (GLsizei n, GLuint *buffers);
typedef GLboolean (APIENTRY * glIsBufferPROC) (GLuint buffer);
typedef void (APIENTRY * glBufferDataPROC) (GLenum target, GLsizeiptr size, const GLvoid *data, GLenum usage);
typedef void (APIENTRY * glBufferSubDataPROC) (GLenum target, GLintptr offset, GLsizeiptr size, const GLvoid *data);
typedef void (APIENTRY * glGetBufferSubDataPROC) (GLenum target, GLintptr offset, GLsizeiptr size, GLvoid *data);
typedef void * (APIENTRY * glMapBufferPROC) (GLenum target, GLenum access);
typedef GLboolean (APIENTRY * glUnmapBufferPROC) (GLenum target);
typedef void (APIENTRY * glGetBufferParameterivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetBufferPointervPROC) (GLenum target, GLenum pname, GLvoid **params);

typedef void (APIENTRY * glGenQueriesPROC) (GLsizei n, GLuint *ids);
typedef void (APIENTRY * glDeleteQueriesPROC) (GLsizei n, const GLuint *ids);
typedef GLboolean (APIENTRY * glIsQueryPROC) (GLuint id);
typedef void (APIENTRY * glBeginQueryPROC) (GLenum target, GLuint id);
typedef void (APIENTRY * glEndQueryPROC) (GLenum target);
typedef void (APIENTRY * glGetQueryivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetQueryObjectivPROC) (GLuint id, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetQueryObjectuivPROC) (GLuint id, GLenum pname, GLuint *params);

static glBindBufferPROC glBindBuffer;
static glDeleteBuffersPROC glDeleteBuffers;
static glGenBuffersPROC glGenBuffers;
static glIsBufferPROC glIsBuffer;
static glBufferDataPROC glBufferData;
static glBufferSubDataPROC glBufferSubData;
static glGetBufferSubDataPROC glGetBufferSubData;
static glMapBufferPROC glMapBuffer;
static glUnmapBufferPROC glUnmapBuffer;
static glGetBufferParameterivPROC glGetBufferParameteriv;
static glGetBufferPointervPROC glGetBufferPointerv;

static glGenQueriesPROC glGenQueries;
static glDeleteQueriesPROC glDeleteQueries;
static glIsQueryPROC glIsQuery;
static glBeginQueryPROC glBeginQuery;
static glEndQueryPROC glEndQuery;
static glGetQueryivPROC glGetQueryiv;
static glGetQueryObjectivPROC glGetQueryObjectiv;
static glGetQueryObjectuivPROC glGetQueryObjectuiv;

void extgl_InitOpenGL1_5(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.OpenGL15)
		return;
	glBindBuffer = (glBindBufferPROC) extgl_GetProcAddress("glBindBuffer");
	glDeleteBuffers = (glDeleteBuffersPROC) extgl_GetProcAddress("glDeleteBuffers");
	glGenBuffers = (glGenBuffersPROC) extgl_GetProcAddress("glGenBuffers");
	glIsBuffer = (glIsBufferPROC) extgl_GetProcAddress("glIsBuffer");
	glBufferData = (glBufferDataPROC) extgl_GetProcAddress("glBufferData");
	glBufferSubData = (glBufferSubDataPROC) extgl_GetProcAddress("glBufferSubData");
	glGetBufferSubData = (glGetBufferSubDataPROC) extgl_GetProcAddress("glGetBufferSubData");
	glMapBuffer = (glMapBufferPROC) extgl_GetProcAddress("glMapBuffer");
	glUnmapBuffer = (glUnmapBufferPROC) extgl_GetProcAddress("glUnmapBuffer");
	glGetBufferParameteriv = (glGetBufferParameterivPROC) extgl_GetProcAddress("glGetBufferParameteriv");
	glGetBufferPointerv = (glGetBufferPointervPROC) extgl_GetProcAddress("glGetBufferPointerv");

	glGenQueries = (glGenQueriesPROC) extgl_GetProcAddress("glGenQueries");
	glDeleteQueries = (glDeleteQueriesPROC) extgl_GetProcAddress("glDeleteQueries");
	glIsQuery = (glIsQueryPROC) extgl_GetProcAddress("glIsQuery");
	glBeginQuery = (glBeginQueryPROC) extgl_GetProcAddress("glBeginQuery");
	glEndQuery = (glEndQueryPROC) extgl_GetProcAddress("glEndQuery");
	glGetQueryiv = (glGetQueryivPROC) extgl_GetProcAddress("glGetQueryiv");
	glGetQueryObjectiv = (glGetQueryObjectivPROC) extgl_GetProcAddress("glGetQueryObjectiv");
	glGetQueryObjectuiv = (glGetQueryObjectuivPROC) extgl_GetProcAddress("glGetQueryObjectuiv");

	EXTGL_SANITY_CHECK(env, ext_set, OpenGL15)
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglBindBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglBindBuffer
	(JNIEnv * env, jclass clazz, jint target, jint buffer)
{
	CHECK_EXISTS(glBindBuffer)
	glBindBuffer(target, buffer);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglDeleteBuffers
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglDeleteBuffers
	(JNIEnv * env, jclass clazz, jint n, jobject buffers, jint buffers_offset)
{
	CHECK_EXISTS(glDeleteBuffers)
	GLuint *buffers_ptr = (GLuint *)env->GetDirectBufferAddress(buffers) + buffers_offset;
	glDeleteBuffers(n, buffers_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGenBuffers
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGenBuffers
	(JNIEnv * env, jclass clazz, jint n, jobject buffers, jint buffers_offset)
{
	CHECK_EXISTS(glGenBuffers)
	GLuint *buffers_ptr = (GLuint *)env->GetDirectBufferAddress(buffers) + buffers_offset;
	glGenBuffers(n, buffers_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glIsBuffer
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL15_glIsBuffer
	(JNIEnv * env, jclass clazz, jint buffer)
{
	CHECK_EXISTS(glIsBuffer)
	GLboolean result = glIsBuffer(buffer);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglBufferData
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglBufferData
	(JNIEnv * env, jclass clazz, jint target, jint size, jobject data, jint data_offset, jint usage)
{
	CHECK_EXISTS(glBufferData)
	GLvoid *data_ptr = (GLvoid *)safeGetBufferAddress(env, data, data_offset);
	glBufferData(target, size, data_ptr, usage);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglBufferSubData
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglBufferSubData
	(JNIEnv * env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_offset)
{
	CHECK_EXISTS(glBufferSubData)
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + data_offset);
	glBufferSubData(target, offset, size, data_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGetBufferSubData
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetBufferSubData
	(JNIEnv * env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_offset)
{
	CHECK_EXISTS(glGetBufferSubData)
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + data_offset);
	glGetBufferSubData(target, offset, size, data_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glMapBuffer
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL15_glMapBuffer
	(JNIEnv * env, jclass clazz, jint target, jint access, jint size, jobject oldBuffer)
{
        CHECK_EXISTS(glMapBuffer)
        void *buffer_address = glMapBuffer((GLenum)target, (GLenum)access);
        CHECK_GL_ERROR
        if (oldBuffer != NULL) {
                void *old_buffer_address = env->GetDirectBufferAddress(oldBuffer);
                if (old_buffer_address == buffer_address)
                        return oldBuffer;
        }
        return safeNewBuffer(env, buffer_address, size);
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glUnmapBuffer
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL15_glUnmapBuffer
	(JNIEnv * env, jclass clazz, jint target)
{
	CHECK_EXISTS(glUnmapBuffer)
	GLboolean result = glUnmapBuffer(target);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGetBufferParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetBufferParameteriv
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint params_offset)
{
	CHECK_EXISTS(glGetBufferParameteriv)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + params_offset;
	glGetBufferParameteriv(target, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glGetBufferPointer
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL15_glGetBufferPointer
	(JNIEnv * env, jclass clazz, jint target, jint pname, jint size)
{
        CHECK_EXISTS(glGetBufferPointerv)
        void *pointer;
        glGetBufferPointerv((GLenum)target, (GLenum)pname, &pointer);
        CHECK_GL_ERROR
        return safeNewBuffer(env, pointer, size);
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGenQueries
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGenQueries
	(JNIEnv * env, jclass clazz, jint n, jobject ids, jint idsOffset)
{
	CHECK_EXISTS(glGenQueries)
	GLuint *ids_ptr = (GLuint *)env->GetDirectBufferAddress(ids) + idsOffset;
	glGenQueries(n, ids_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglDeleteQueries
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglDeleteQueries
	(JNIEnv * env, jclass clazz, jint n, jobject ids, jint idsOffset)
{
	CHECK_EXISTS(glDeleteQueries)
	GLuint *ids_ptr = (GLuint *)env->GetDirectBufferAddress(ids) + idsOffset;
	glDeleteQueries(n, ids_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glIsQuery
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL15_glIsQuery
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glIsQuery)
	GLboolean result = glIsQuery(id);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glBeginQuery
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_glBeginQuery
	(JNIEnv * env, jclass clazz, jint target, jint id)
{
	CHECK_EXISTS(glBeginQuery)
	glBeginQuery(target, id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glEndQuery
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_glEndQuery
	(JNIEnv * env, jclass clazz, jint target)
{
	CHECK_EXISTS(glEndQuery)
	glEndQuery(target);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGetQueryiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryiv
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetQueryiv)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryiv(target, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGetQueryObjectiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryObjectiv
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetQueryObjectiv)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryObjectiv(id, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGetQueryObjectuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryObjectuiv
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetQueryObjectuiv)
	GLuint *params_ptr = (GLuint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetQueryObjectuiv(id, pname, params_ptr);
	CHECK_GL_ERROR
}