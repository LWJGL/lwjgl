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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ati.ATIVertexStreams
// ----------------------------------

#include "org_lwjgl_opengl_ati_ATIVertexStreams.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glClientActiveVertexStreamATIPROC) (GLenum stream);
typedef void (APIENTRY * glVertexBlendEnviATIPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glVertexBlendEnvfATIPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glVertexStream1sATIPROC) (GLenum stream, GLshort x);
typedef void (APIENTRY * glVertexStream1iATIPROC) (GLenum stream, GLint x);
typedef void (APIENTRY * glVertexStream1fATIPROC) (GLenum stream, GLfloat x);
typedef void (APIENTRY * glVertexStream2sATIPROC) (GLenum stream, GLshort x, GLshort y);
typedef void (APIENTRY * glVertexStream2iATIPROC) (GLenum stream, GLint x, GLint y);
typedef void (APIENTRY * glVertexStream2fATIPROC) (GLenum stream, GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertexStream3sATIPROC) (GLenum stream, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertexStream3iATIPROC) (GLenum stream, GLint x, GLint y, GLint z);
typedef void (APIENTRY * glVertexStream3fATIPROC) (GLenum stream, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertexStream4sATIPROC) (GLenum stream, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertexStream4iATIPROC) (GLenum stream, GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glVertexStream4fATIPROC) (GLenum stream, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glNormalStream3bATIPROC) (GLenum stream, GLbyte x, GLbyte y, GLbyte z);
typedef void (APIENTRY * glNormalStream3sATIPROC) (GLenum stream, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glNormalStream3iATIPROC) (GLenum stream, GLint x, GLint y, GLint z);
typedef void (APIENTRY * glNormalStream3fATIPROC) (GLenum stream, GLfloat x, GLfloat y, GLfloat z);

static glClientActiveVertexStreamATIPROC glClientActiveVertexStreamATI;
static glVertexBlendEnviATIPROC glVertexBlendEnviATI;
static glVertexBlendEnvfATIPROC glVertexBlendEnvfATI;
static glVertexStream1sATIPROC glVertexStream1sATI;
static glVertexStream1iATIPROC glVertexStream1iATI;
static glVertexStream1fATIPROC glVertexStream1fATI;
static glVertexStream2sATIPROC glVertexStream2sATI;
static glVertexStream2iATIPROC glVertexStream2iATI;
static glVertexStream2fATIPROC glVertexStream2fATI;
static glVertexStream3sATIPROC glVertexStream3sATI;
static glVertexStream3iATIPROC glVertexStream3iATI;
static glVertexStream3fATIPROC glVertexStream3fATI;
static glVertexStream4sATIPROC glVertexStream4sATI;
static glVertexStream4iATIPROC glVertexStream4iATI;
static glVertexStream4fATIPROC glVertexStream4fATI;
static glNormalStream3bATIPROC glNormalStream3bATI;
static glNormalStream3sATIPROC glNormalStream3sATI;
static glNormalStream3iATIPROC glNormalStream3iATI;
static glNormalStream3fATIPROC glNormalStream3fATI;

void extgl_InitATIVertexStreams(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_vertex_streams)
		return;
	glClientActiveVertexStreamATI = (glClientActiveVertexStreamATIPROC) extgl_GetProcAddress("glClientActiveVertexStreamATI");
	glVertexBlendEnviATI = (glVertexBlendEnviATIPROC) extgl_GetProcAddress("glVertexBlendEnviATI");
	glVertexBlendEnvfATI = (glVertexBlendEnvfATIPROC) extgl_GetProcAddress("glVertexBlendEnvfATI");
	glVertexStream1sATI = (glVertexStream1sATIPROC) extgl_GetProcAddress("glVertexStream1sATI");
	glVertexStream1iATI = (glVertexStream1iATIPROC) extgl_GetProcAddress("glVertexStream1iATI");
	glVertexStream1fATI = (glVertexStream1fATIPROC) extgl_GetProcAddress("glVertexStream1fATI");
	glVertexStream2sATI = (glVertexStream2sATIPROC) extgl_GetProcAddress("glVertexStream2sATI");
	glVertexStream2iATI = (glVertexStream2iATIPROC) extgl_GetProcAddress("glVertexStream2iATI");
	glVertexStream2fATI = (glVertexStream2fATIPROC) extgl_GetProcAddress("glVertexStream2fATI");
	glVertexStream3sATI = (glVertexStream3sATIPROC) extgl_GetProcAddress("glVertexStream3sATI");
	glVertexStream3iATI = (glVertexStream3iATIPROC) extgl_GetProcAddress("glVertexStream3iATI");
	glVertexStream3fATI = (glVertexStream3fATIPROC) extgl_GetProcAddress("glVertexStream3fATI");
	glVertexStream4sATI = (glVertexStream4sATIPROC) extgl_GetProcAddress("glVertexStream4sATI");
	glVertexStream4iATI = (glVertexStream4iATIPROC) extgl_GetProcAddress("glVertexStream4iATI");
	glVertexStream4fATI = (glVertexStream4fATIPROC) extgl_GetProcAddress("glVertexStream4fATI");
	glNormalStream3bATI = (glNormalStream3bATIPROC) extgl_GetProcAddress("glNormalStream3bATI");
	glNormalStream3sATI = (glNormalStream3sATIPROC) extgl_GetProcAddress("glNormalStream3sATI");
	glNormalStream3iATI = (glNormalStream3iATIPROC) extgl_GetProcAddress("glNormalStream3iATI");
	glNormalStream3fATI = (glNormalStream3fATIPROC) extgl_GetProcAddress("glNormalStream3fATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_vertex_streams)
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream1fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream1fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x)
{
	CHECK_EXISTS(glVertexStream1fATI)
	glVertexStream1fATI(stream, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream1iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream1iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x)
{
	CHECK_EXISTS(glVertexStream1iATI)
	glVertexStream1iATI(stream, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream1sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream1sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x)
{
	CHECK_EXISTS(glVertexStream1sATI)
	glVertexStream1sATI(stream, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream2fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream2fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y)
{
	CHECK_EXISTS(glVertexStream2fATI)
	glVertexStream2fATI(stream, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream2iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream2iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y)
{
	CHECK_EXISTS(glVertexStream2iATI)
	glVertexStream2iATI(stream, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream2sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream2sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y)
{
	CHECK_EXISTS(glVertexStream2sATI)
	glVertexStream2sATI(stream, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream3fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream3fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y, jfloat z)
{
	CHECK_EXISTS(glVertexStream3fATI)
	glVertexStream3fATI(stream, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream3iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream3iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y, jint z)
{
	CHECK_EXISTS(glVertexStream3iATI)
	glVertexStream3iATI(stream, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream3sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream3sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glVertexStream3sATI)
	glVertexStream3sATI(stream, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream4fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream4fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glVertexStream4fATI)
	glVertexStream4fATI(stream, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream4iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream4iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y, jint z, jint w)
{
	CHECK_EXISTS(glVertexStream4iATI)
	glVertexStream4iATI(stream, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexStream4sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexStream4sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y, jshort z, jshort w)
{
	CHECK_EXISTS(glVertexStream4sATI)
	glVertexStream4sATI(stream, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glNormalStream3bATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glNormalStream3bATI
	(JNIEnv * env, jclass clazz, jint stream, jbyte x, jbyte y, jbyte z)
{
	CHECK_EXISTS(glNormalStream3bATI)
	glNormalStream3bATI(stream, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glNormalStream3fATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glNormalStream3fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y, jfloat z)
{
	CHECK_EXISTS(glNormalStream3fATI)
	glNormalStream3fATI(stream, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glNormalStream3iATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glNormalStream3iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y, jint z)
{
	CHECK_EXISTS(glNormalStream3iATI)
	glNormalStream3iATI(stream, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glNormalStream3sATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glNormalStream3sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glNormalStream3sATI)
	glNormalStream3sATI(stream, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glClientActiveVertexStreamATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glClientActiveVertexStreamATI
	(JNIEnv * env, jclass clazz, jint stream)
{
	CHECK_EXISTS(glClientActiveVertexStreamATI)
	glClientActiveVertexStreamATI(stream);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexBlendEnvfATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexBlendEnvfATI
	(JNIEnv * env, jclass clazz, jint pname, jfloat param)
{
	CHECK_EXISTS(glVertexBlendEnvfATI)
	glVertexBlendEnvfATI(pname, param);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexStreams
 * Method:	glVertexBlendEnviATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexStreams_glVertexBlendEnviATI
	(JNIEnv * env, jclass clazz, jint pname, jint param)
{
	CHECK_EXISTS(glVertexBlendEnviATI)
	glVertexBlendEnviATI(pname, param);
	CHECK_GL_ERROR
}
