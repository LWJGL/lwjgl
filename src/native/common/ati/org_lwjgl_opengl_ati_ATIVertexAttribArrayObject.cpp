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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ati.ATIVertexAttribArrayObject
// ----------------------------------

#include "org_lwjgl_opengl_ati_ATIVertexAttribArrayObject.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glVertexAttribArrayObjectATIPROC) (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, GLuint buffer, GLuint offset);
typedef void (APIENTRY * glGetVertexAttribArrayObjectfvATIPROC) (GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVertexAttribArrayObjectivATIPROC) (GLuint index, GLenum pname, GLint *params);

static glVertexAttribArrayObjectATIPROC glVertexAttribArrayObjectATI;
static glGetVertexAttribArrayObjectfvATIPROC glGetVertexAttribArrayObjectfvATI;
static glGetVertexAttribArrayObjectivATIPROC glGetVertexAttribArrayObjectivATI;

void extgl_InitATIVertexAttribArrayObject(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_vertex_attrib_array_object)
		return;

	glVertexAttribArrayObjectATI = (glVertexAttribArrayObjectATIPROC) extgl_GetProcAddress("glVertexAttribArrayObjectATI");
	glGetVertexAttribArrayObjectfvATI = (glGetVertexAttribArrayObjectfvATIPROC) extgl_GetProcAddress("glGetVertexAttribArrayObjectfvATI");
	glGetVertexAttribArrayObjectivATI = (glGetVertexAttribArrayObjectivATIPROC) extgl_GetProcAddress("glGetVertexAttribArrayObjectivATI");

	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_vertex_attrib_array_object)
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexAttribArrayObject
 * Method:	glVertexAttribArrayObjectATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexAttribArrayObject_glVertexAttribArrayObjectATI
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jint buffer, jint offset)
{
	CHECK_EXISTS(glVertexAttribArrayObjectATI)
	glVertexAttribArrayObjectATI(index, size, type, normalized, stride, buffer, offset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexAttribArrayObject
 * Method:	nglGetVertexAttribArrayObjectfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexAttribArrayObject_nglGetVertexAttribArrayObjectfvATI
	(JNIEnv * env, jclass clazz, jint index, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribArrayObjectfvATI)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribArrayObjectfvATI(index, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexAttribArrayObject
 * Method:	nglGetVertexAttribArrayObjectivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexAttribArrayObject_nglGetVertexAttribArrayObjectivATI
	(JNIEnv * env, jclass clazz, jint index, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribArrayObjectivATI)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribArrayObjectivATI(index, pname, params_ptr);
	CHECK_GL_ERROR
}
