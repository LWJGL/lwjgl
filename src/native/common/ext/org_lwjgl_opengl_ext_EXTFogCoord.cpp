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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ext.EXTFogCoord
// ----------------------------------

#include "org_lwjgl_opengl_ext_EXTFogCoord.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glFogCoordfEXTPROC) (GLfloat coord);
typedef void (APIENTRY * glFogCoordfvEXTPROC) (const GLfloat *coord);
typedef void (APIENTRY * glFogCoordPointerEXTPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);

static glFogCoordfEXTPROC glFogCoordfEXT;
static glFogCoordfvEXTPROC glFogCoordfvEXT;
static glFogCoordPointerEXTPROC glFogCoordPointerEXT;

void extgl_InitEXTFogCoord(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_fog_coord)
		return;
	glFogCoordfEXT = (glFogCoordfEXTPROC) extgl_GetProcAddress("glFogCoordfEXT");
	glFogCoordfvEXT = (glFogCoordfvEXTPROC) extgl_GetProcAddress("glFogCoordfvEXT");
	glFogCoordPointerEXT = (glFogCoordPointerEXTPROC) extgl_GetProcAddress("glFogCoordPointerEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_fog_coord)
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTFogCoord
 * Method:	glFogCoordfEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTFogCoord_glFogCoordfEXT
	(JNIEnv * env, jclass clazz, jfloat coord)
{
	CHECK_EXISTS(glFogCoordfEXT)
	glFogCoordfEXT(coord);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTFogCoord
 * Method:	nglFogCoordPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTFogCoord_nglFogCoordPointerEXT
	(JNIEnv * env, jclass clazz, jint type, jint stride, jobject data, jint data_offset)
{
	CHECK_EXISTS(glFogCoordPointerEXT)
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + data_offset);
	glFogCoordPointerEXT(type, stride, data_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTFogCoord
 * Method:	nglFogCoordPointerEXTVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTFogCoord_nglFogCoordPointerEXTVBO
	(JNIEnv * env, jclass clazz, jint type, jint stride, jint buffer_offset)
{
	CHECK_EXISTS(glFogCoordPointerEXT)
	glFogCoordPointerEXT(type, stride, (GLvoid *)buffer_offset);
	CHECK_GL_ERROR
}
