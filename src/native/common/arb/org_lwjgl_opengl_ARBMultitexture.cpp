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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBMultitexture
// ----------------------------------

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

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glClientActiveTextureARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glClientActiveTextureARB
	(JNIEnv * env, jclass clazz, jint texture)
{
	glClientActiveTextureARB(texture);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glActiveTextureARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glActiveTextureARB
	(JNIEnv * env, jclass clazz, jint texture)
{
	glActiveTextureARB(texture);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord1fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s)
{
	glMultiTexCoord1fARB(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord1iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1iARB
	(JNIEnv * env, jclass clazz, jint target, jint s)
{
	glMultiTexCoord1iARB(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord1sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s)
{
	glMultiTexCoord1sARB(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord2fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s, jfloat t)
{
	glMultiTexCoord2fARB(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord2iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2iARB
	(JNIEnv * env, jclass clazz, jint target, jint s, jint t)
{
	glMultiTexCoord2iARB(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord2sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t)
{
	glMultiTexCoord2sARB(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord3fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r)
{
	glMultiTexCoord3fARB(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord3iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3iARB
	(JNIEnv * env, jclass clazz, jint target, jint s, jint t, jint r)
{
	glMultiTexCoord3iARB(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord3sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r)
{
	glMultiTexCoord3sARB(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord4fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4fARB
	(JNIEnv * env, jclass clazz, jint target, jfloat s, jfloat t, jfloat r, jfloat q)
{
	glMultiTexCoord4fARB(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord4iARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4iARB
	(JNIEnv * env, jclass clazz, jint target, jint s, jint t, jint r, jint q)
{
	glMultiTexCoord4iARB(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMultitexture
 * Method:	glMultiTexCoord4sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4sARB
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q)
{
	glMultiTexCoord4sARB(target, s, t, r, q);
	CHECK_GL_ERROR
}

void extgl_InitARBMultitexture(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"glClientActiveTextureARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glClientActiveTextureARB, "glClientActiveTextureARB", (void**)&glClientActiveTextureARB},
		{"glActiveTextureARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glActiveTextureARB, "glActiveTextureARB", (void**)&glActiveTextureARB},
		{"glMultiTexCoord1fARB", "(IF)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1fARB, "glMultiTexCoord1fARB", (void**)&glMultiTexCoord1fARB},
		{"glMultiTexCoord1iARB", "(II)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1iARB, "glMultiTexCoord1iARB", (void**)&glMultiTexCoord1iARB},
		{"glMultiTexCoord1sARB", "(IS)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord1sARB, "glMultiTexCoord1sARB", (void**)&glMultiTexCoord1sARB},
		{"glMultiTexCoord2fARB", "(IFF)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2fARB, "glMultiTexCoord2fARB", (void**)&glMultiTexCoord2fARB},
		{"glMultiTexCoord2iARB", "(III)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2iARB, "glMultiTexCoord2iARB", (void**)&glMultiTexCoord2iARB},
		{"glMultiTexCoord2sARB", "(ISS)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord2sARB, "glMultiTexCoord2sARB", (void**)&glMultiTexCoord2sARB},
		{"glMultiTexCoord3fARB", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3fARB, "glMultiTexCoord3fARB", (void**)&glMultiTexCoord3fARB},
		{"glMultiTexCoord3iARB", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3iARB, "glMultiTexCoord3iARB", (void**)&glMultiTexCoord3iARB},
		{"glMultiTexCoord3sARB", "(ISSS)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord3sARB, "glMultiTexCoord3sARB", (void**)&glMultiTexCoord3sARB},
		{"glMultiTexCoord4fARB", "(IFFFF)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4fARB, "glMultiTexCoord4fARB", (void**)&glMultiTexCoord4fARB},
		{"glMultiTexCoord4iARB", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4iARB, "glMultiTexCoord4iARB", (void**)&glMultiTexCoord4iARB},
		{"glMultiTexCoord4sARB", "(ISSSS)V", (void*)&Java_org_lwjgl_opengl_ARBMultitexture_glMultiTexCoord4sARB, "glMultiTexCoord4sARB", (void**)&glMultiTexCoord4sARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/ARBMultitexture");
	if (extgl_Extensions.GL_ARB_multitexture)
		extgl_InitializeClass(env, clazz, ext_set, "GL_ARB_multitexture", num_functions, functions);
}

