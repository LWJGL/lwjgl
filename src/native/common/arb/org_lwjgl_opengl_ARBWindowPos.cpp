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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBWindowPos
// ----------------------------------

#include "org_lwjgl_opengl_ARBWindowPos.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glWindowPos2fARBPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glWindowPos2iARBPROC) (GLint x, GLint y);
typedef void (APIENTRY * glWindowPos2sARBPROC) (GLshort x, GLshort y);
typedef void (APIENTRY * glWindowPos3fARBPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glWindowPos3iARBPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY * glWindowPos3sARBPROC) (GLshort x, GLshort y, GLshort z);

static glWindowPos2fARBPROC glWindowPos2fARB;
static glWindowPos2iARBPROC glWindowPos2iARB;
static glWindowPos2sARBPROC glWindowPos2sARB;
static glWindowPos3fARBPROC glWindowPos3fARB;
static glWindowPos3iARBPROC glWindowPos3iARB;
static glWindowPos3sARBPROC glWindowPos3sARB;

void extgl_InitARBWindowPos(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_window_pos)
		return;
	glWindowPos2fARB = (glWindowPos2fARBPROC) extgl_GetProcAddress("glWindowPos2fARB");
	glWindowPos2iARB = (glWindowPos2iARBPROC) extgl_GetProcAddress("glWindowPos2iARB");
	glWindowPos2sARB = (glWindowPos2sARBPROC) extgl_GetProcAddress("glWindowPos2sARB");
	glWindowPos3fARB = (glWindowPos3fARBPROC) extgl_GetProcAddress("glWindowPos3fARB");
	glWindowPos3iARB = (glWindowPos3iARBPROC) extgl_GetProcAddress("glWindowPos3iARB");
	glWindowPos3sARB = (glWindowPos3sARBPROC) extgl_GetProcAddress("glWindowPos3sARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_window_pos)
}

/*
 * Class:	org.lwjgl.opengl.ARBWindowPos
 * Method:	glWindowPos2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2fARB
	(JNIEnv * env, jclass clazz, jfloat x, jfloat y)
{
	CHECK_EXISTS(glWindowPos2fARB)
	glWindowPos2fARB(x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBWindowPos
 * Method:	glWindowPos2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2iARB
	(JNIEnv * env, jclass clazz, jint x, jint y)
{
	CHECK_EXISTS(glWindowPos2iARB)
	glWindowPos2iARB(x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBWindowPos
 * Method:	glWindowPos2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2sARB
	(JNIEnv * env, jclass clazz, jshort x, jshort y)
{
	CHECK_EXISTS(glWindowPos2sARB)
	glWindowPos2sARB(x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBWindowPos
 * Method:	glWindowPos3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3fARB
	(JNIEnv * env, jclass clazz, jfloat x, jfloat y, jfloat z)
{
	CHECK_EXISTS(glWindowPos3fARB)
	glWindowPos3fARB(x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBWindowPos
 * Method:	glWindowPos3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3iARB
	(JNIEnv * env, jclass clazz, jint x, jint y, jint z)
{
	CHECK_EXISTS(glWindowPos3iARB)
	glWindowPos3iARB(x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBWindowPos
 * Method:	glWindowPos3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3sARB
	(JNIEnv * env, jclass clazz, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glWindowPos3sARB)
	glWindowPos3sARB(x, y, z);
	CHECK_GL_ERROR
}
