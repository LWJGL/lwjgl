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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.arb.ARBMultitexture
// ----------------------------------

#include "org_lwjgl_opengl_arb_ARBMultitexture.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glActiveTextureARBPROC) (GLenum texture );
typedef void (APIENTRY * glClientActiveTextureARBPROC) (GLenum texture );
typedef void (APIENTRY * glMultiTexCoord1fARBPROC) (GLenum target, GLfloat s );
typedef void (APIENTRY * glMultiTexCoord1iARBPROC) (GLenum target, GLint s );
typedef void (APIENTRY * glMultiTexCoord1sARBPROC) (GLenum target, GLshort s );
typedef void (APIENTRY * glMultiTexCoord2fARBPROC) (GLenum target, GLfloat s, GLfloat t );
typedef void (APIENTRY * glMultiTexCoord2iARBPROC) (GLenum target, GLint s, GLint t );
typedef void (APIENTRY * glMultiTexCoord2sARBPROC) (GLenum target, GLshort s, GLshort t );
typedef void (APIENTRY * glMultiTexCoord3fARBPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r );
typedef void (APIENTRY * glMultiTexCoord3iARBPROC) (GLenum target, GLint s, GLint t, GLint r );
typedef void (APIENTRY * glMultiTexCoord3sARBPROC) (GLenum target, GLshort s, GLshort t, GLshort r );
typedef void (APIENTRY * glMultiTexCoord4fARBPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r, GLfloat q );
typedef void (APIENTRY * glMultiTexCoord4iARBPROC) (GLenum target, GLint s, GLint t, GLint r, GLint q );
typedef void (APIENTRY * glMultiTexCoord4sARBPROC) (GLenum target, GLshort s, GLshort t, GLshort r, GLshort q );

static glActiveTextureARBPROC glActiveTextureARB;
static glClientActiveTextureARBPROC glClientActiveTextureARB;
static glMultiTexCoord1fARBPROC glMultiTexCoord1fARB;
static glMultiTexCoord1iARBPROC glMultiTexCoord1iARB;
static glMultiTexCoord1sARBPROC glMultiTexCoord1sARB;
static glMultiTexCoord2fARBPROC glMultiTexCoord2fARB;
static glMultiTexCoord2iARBPROC glMultiTexCoord2iARB;
static glMultiTexCoord2sARBPROC glMultiTexCoord2sARB;
static glMultiTexCoord3fARBPROC glMultiTexCoord3fARB;
static glMultiTexCoord3iARBPROC glMultiTexCoord3iARB;
static glMultiTexCoord3sARBPROC glMultiTexCoord3sARB;
static glMultiTexCoord4fARBPROC glMultiTexCoord4fARB;
static glMultiTexCoord4iARBPROC glMultiTexCoord4iARB;
static glMultiTexCoord4sARBPROC glMultiTexCoord4sARB;

void extgl_InitARBMultitexture(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_multitexture)
		return;
	glActiveTextureARB = (glActiveTextureARBPROC) extgl_GetProcAddress("glActiveTextureARB");
	glClientActiveTextureARB = (glClientActiveTextureARBPROC) extgl_GetProcAddress("glClientActiveTextureARB");

	glMultiTexCoord1fARB = (glMultiTexCoord1fARBPROC) extgl_GetProcAddress("glMultiTexCoord1fARB");
	glMultiTexCoord1iARB = (glMultiTexCoord1iARBPROC) extgl_GetProcAddress("glMultiTexCoord1iARB");
	glMultiTexCoord1sARB = (glMultiTexCoord1sARBPROC) extgl_GetProcAddress("glMultiTexCoord1sARB");

	glMultiTexCoord2fARB = (glMultiTexCoord2fARBPROC) extgl_GetProcAddress("glMultiTexCoord2fARB");
	glMultiTexCoord2iARB = (glMultiTexCoord2iARBPROC) extgl_GetProcAddress("glMultiTexCoord2iARB");
	glMultiTexCoord2sARB = (glMultiTexCoord2sARBPROC) extgl_GetProcAddress("glMultiTexCoord2sARB");

	glMultiTexCoord3fARB = (glMultiTexCoord3fARBPROC) extgl_GetProcAddress("glMultiTexCoord3fARB");
	glMultiTexCoord3iARB = (glMultiTexCoord3iARBPROC) extgl_GetProcAddress("glMultiTexCoord3iARB");
	glMultiTexCoord3sARB = (glMultiTexCoord3sARBPROC) extgl_GetProcAddress("glMultiTexCoord3sARB");

	glMultiTexCoord4fARB = (glMultiTexCoord4fARBPROC) extgl_GetProcAddress("glMultiTexCoord4fARB");
	glMultiTexCoord4iARB = (glMultiTexCoord4iARBPROC) extgl_GetProcAddress("glMultiTexCoord4iARB");
	glMultiTexCoord4sARB = (glMultiTexCoord4sARBPROC) extgl_GetProcAddress("glMultiTexCoord4sARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_multitexture)
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glClientActiveTextureARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glClientActiveTextureARB
	(JNIEnv * env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glClientActiveTextureARB)
	glClientActiveTextureARB(texture);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glActiveTextureARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glActiveTextureARB
	(JNIEnv * env, jclass clazz, jint texture)
{
	CHECK_EXISTS(glActiveTextureARB)
	glActiveTextureARB(texture);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord1fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s)
{
	CHECK_EXISTS(glMultiTexCoord1fARB)
	glMultiTexCoord1fARB(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord1iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord1iARB
	(JNIEnv * env, jclass clazz, jint target, jint s)
{
	CHECK_EXISTS(glMultiTexCoord1iARB)
	glMultiTexCoord1iARB(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord1sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord1sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s)
{
	CHECK_EXISTS(glMultiTexCoord1sARB)
	glMultiTexCoord1sARB(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord2fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s, jfloat t)
{
	CHECK_EXISTS(glMultiTexCoord2fARB)
	glMultiTexCoord2fARB(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord2iARB
	(JNIEnv * env, jclass clazz, jint target, jint s, jint t)
{
	CHECK_EXISTS(glMultiTexCoord2iARB)
	glMultiTexCoord2iARB(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord2sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t)
{
	CHECK_EXISTS(glMultiTexCoord2sARB)
	glMultiTexCoord2sARB(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord3fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r)
{
	CHECK_EXISTS(glMultiTexCoord3fARB)
	glMultiTexCoord3fARB(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord3iARB
	(JNIEnv * env, jclass clazz, jint target, jint s, jint t, jint r)
{
	CHECK_EXISTS(glMultiTexCoord3iARB)
	glMultiTexCoord3iARB(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord3sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r)
{
	CHECK_EXISTS(glMultiTexCoord3sARB)
	glMultiTexCoord3sARB(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord4fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q)
{
	CHECK_EXISTS(glMultiTexCoord4fARB)
	glMultiTexCoord4fARB(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord4iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord4iARB
	(JNIEnv * env, jclass clazz, jint target, jint s, jint t, jint r, jint q)
{
	CHECK_EXISTS(glMultiTexCoord4iARB)
	glMultiTexCoord4iARB(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBMultitexture
 * Method:	glMultiTexCoord4sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBMultitexture_glMultiTexCoord4sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q)
{
	CHECK_EXISTS(glMultiTexCoord4sARB)
	glMultiTexCoord4sARB(target, s, t, r, q);
	CHECK_GL_ERROR
}
