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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.EXTSecondaryColor
// ----------------------------------

#include "org_lwjgl_opengl_EXTSecondaryColor.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glSecondaryColor3bEXTPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY * glSecondaryColor3fEXTPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY * glSecondaryColor3iEXTPROC) (GLint red, GLint green, GLint blue);
typedef void (APIENTRY * glSecondaryColor3sEXTPROC) (GLshort red, GLshort green, GLshort blue);
typedef void (APIENTRY * glSecondaryColor3ubEXTPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY * glSecondaryColor3uiEXTPROC) (GLuint red, GLuint green, GLuint blue);
typedef void (APIENTRY * glSecondaryColor3usEXTPROC) (GLushort red, GLushort green, GLushort blue);
typedef void (APIENTRY * glSecondaryColorPointerEXTPROC) (GLint size, GLenum type, GLsizei stride, GLvoid *pointer);

static glSecondaryColor3bEXTPROC glSecondaryColor3bEXT;
static glSecondaryColor3fEXTPROC glSecondaryColor3fEXT;
static glSecondaryColor3iEXTPROC glSecondaryColor3iEXT;
static glSecondaryColor3sEXTPROC glSecondaryColor3sEXT;
static glSecondaryColor3ubEXTPROC glSecondaryColor3ubEXT;
static glSecondaryColor3uiEXTPROC glSecondaryColor3uiEXT;
static glSecondaryColor3usEXTPROC glSecondaryColor3usEXT;
static glSecondaryColorPointerEXTPROC glSecondaryColorPointerEXT;

void extgl_InitEXTSecondaryColor(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_secondary_color)
		return;
	glSecondaryColor3bEXT = (glSecondaryColor3bEXTPROC) extgl_GetProcAddress("glSecondaryColor3bEXT");
	glSecondaryColor3fEXT = (glSecondaryColor3fEXTPROC) extgl_GetProcAddress("glSecondaryColor3fEXT");
	glSecondaryColor3iEXT = (glSecondaryColor3iEXTPROC) extgl_GetProcAddress("glSecondaryColor3iEXT");
	glSecondaryColor3sEXT = (glSecondaryColor3sEXTPROC) extgl_GetProcAddress("glSecondaryColor3sEXT");
	glSecondaryColor3ubEXT = (glSecondaryColor3ubEXTPROC) extgl_GetProcAddress("glSecondaryColor3ubEXT");
	glSecondaryColor3uiEXT = (glSecondaryColor3uiEXTPROC) extgl_GetProcAddress("glSecondaryColor3uiEXT");
	glSecondaryColor3usEXT = (glSecondaryColor3usEXTPROC) extgl_GetProcAddress("glSecondaryColor3usEXT");
	glSecondaryColorPointerEXT = (glSecondaryColorPointerEXTPROC) extgl_GetProcAddress("glSecondaryColorPointerEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_secondary_color)
}

/*
 * Class:	org.lwjgl.opengl.EXTSecondaryColor
 * Method:	glSecondaryColor3bEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3bEXT
	(JNIEnv * env, jclass clazz, jbyte red, jbyte green, jbyte blue)
{
	CHECK_EXISTS(glSecondaryColor3bEXT)
	glSecondaryColor3bEXT(red, green, blue);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.EXTSecondaryColor
 * Method:	glSecondaryColor3fEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3fEXT
	(JNIEnv * env, jclass clazz, jfloat red, jfloat green, jfloat blue)
{
	CHECK_EXISTS(glSecondaryColor3fEXT)
	glSecondaryColor3fEXT(red, green, blue);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.EXTSecondaryColor
 * Method:	glSecondaryColor3ubEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3ubEXT
	(JNIEnv * env, jclass clazz, jbyte red, jbyte green, jbyte blue)
{
	CHECK_EXISTS(glSecondaryColor3ubEXT)
	glSecondaryColor3ubEXT(red, green, blue);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.EXTSecondaryColor
 * Method:	nglSecondaryColorPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_nglSecondaryColorPointerEXT
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_offset)
{
	CHECK_EXISTS(glSecondaryColorPointerEXT)
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glSecondaryColorPointerEXT(size, type, stride, pPointer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.EXTSecondaryColor
 * Method:	nglSecondaryColorPointerEXTVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_nglSecondaryColorPointerEXTVBO
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jint buffer_offset)
{
	CHECK_EXISTS(glSecondaryColorPointerEXT)
	glSecondaryColorPointerEXT(size, type, stride, (GLvoid *)buffer_offset);
	CHECK_GL_ERROR
}
