/* 
 * Copyright (c) 2002-2004 LWJGL Project
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
 * * Neither the name of 'LWJGL' nor the names of 
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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ATIVertexStreams
// ----------------------------------

#include "extgl.h"

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

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream1fATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream1fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x)
{
	glVertexStream1fATI(stream, x);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream1iATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream1iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x)
{
	glVertexStream1iATI(stream, x);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream1sATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream1sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x)
{
	glVertexStream1sATI(stream, x);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream2fATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream2fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y)
{
	glVertexStream2fATI(stream, x, y);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream2iATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream2iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y)
{
	glVertexStream2iATI(stream, x, y);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream2sATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream2sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y)
{
	glVertexStream2sATI(stream, x, y);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream3fATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream3fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y, jfloat z)
{
	glVertexStream3fATI(stream, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream3iATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream3iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y, jint z)
{
	glVertexStream3iATI(stream, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream3sATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream3sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y, jshort z)
{
	glVertexStream3sATI(stream, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream4fATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream4fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glVertexStream4fATI(stream, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream4iATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream4iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y, jint z, jint w)
{
	glVertexStream4iATI(stream, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexStream4sATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream4sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y, jshort z, jshort w)
{
	glVertexStream4sATI(stream, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glNormalStream3bATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3bATI
	(JNIEnv * env, jclass clazz, jint stream, jbyte x, jbyte y, jbyte z)
{
	glNormalStream3bATI(stream, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glNormalStream3fATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3fATI
	(JNIEnv * env, jclass clazz, jint stream, jfloat x, jfloat y, jfloat z)
{
	glNormalStream3fATI(stream, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glNormalStream3iATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3iATI
	(JNIEnv * env, jclass clazz, jint stream, jint x, jint y, jint z)
{
	glNormalStream3iATI(stream, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glNormalStream3sATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3sATI
	(JNIEnv * env, jclass clazz, jint stream, jshort x, jshort y, jshort z)
{
	glNormalStream3sATI(stream, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glClientActiveVertexStreamATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glClientActiveVertexStreamATI
	(JNIEnv * env, jclass clazz, jint stream)
{
	glClientActiveVertexStreamATI(stream);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexBlendEnvfATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexBlendEnvfATI
	(JNIEnv * env, jclass clazz, jint pname, jfloat param)
{
	glVertexBlendEnvfATI(pname, param);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexStreams
 * Method:	glVertexBlendEnviATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_glVertexBlendEnviATI
	(JNIEnv * env, jclass clazz, jint pname, jint param)
{
	glVertexBlendEnviATI(pname, param);
	
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIVertexStreams_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glVertexStream1fATI", "(IF)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream1fATI, "glVertexStream1fATI", (void*)&glVertexStream1fATI},
		{"glVertexStream1iATI", "(II)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream1iATI, "glVertexStream1iATI", (void*)&glVertexStream1iATI},
		{"glVertexStream1sATI", "(IS)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream1sATI, "glVertexStream1sATI", (void*)&glVertexStream1sATI},
		{"glVertexStream2fATI", "(IFF)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream2fATI, "glVertexStream2fATI", (void*)&glVertexStream2fATI},
		{"glVertexStream2iATI", "(III)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream2iATI, "glVertexStream2iATI", (void*)&glVertexStream2iATI},
		{"glVertexStream2sATI", "(ISS)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream2sATI, "glVertexStream2sATI", (void*)&glVertexStream2sATI},
		{"glVertexStream3fATI", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream3fATI, "glVertexStream3fATI", (void*)&glVertexStream3fATI},
		{"glVertexStream3iATI", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream3iATI, "glVertexStream3iATI", (void*)&glVertexStream3iATI},
		{"glVertexStream3sATI", "(ISSS)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream3sATI, "glVertexStream3sATI", (void*)&glVertexStream3sATI},
		{"glVertexStream4fATI", "(IFFFF)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream4fATI, "glVertexStream4fATI", (void*)&glVertexStream4fATI},
		{"glVertexStream4iATI", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream4iATI, "glVertexStream4iATI", (void*)&glVertexStream4iATI},
		{"glVertexStream4sATI", "(ISSSS)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexStream4sATI, "glVertexStream4sATI", (void*)&glVertexStream4sATI},
		{"glNormalStream3bATI", "(IBBB)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3bATI, "glNormalStream3bATI", (void*)&glNormalStream3bATI},
		{"glNormalStream3fATI", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3fATI, "glNormalStream3fATI", (void*)&glNormalStream3fATI},
		{"glNormalStream3iATI", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3iATI, "glNormalStream3iATI", (void*)&glNormalStream3iATI},
		{"glNormalStream3sATI", "(ISSS)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glNormalStream3sATI, "glNormalStream3sATI", (void*)&glNormalStream3sATI},
		{"glClientActiveVertexStreamATI", "(I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glClientActiveVertexStreamATI, "glClientActiveVertexStreamATI", (void*)&glClientActiveVertexStreamATI},
		{"glVertexBlendEnvfATI", "(IF)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexBlendEnvfATI, "glVertexBlendEnvfATI", (void*)&glVertexBlendEnvfATI},
		{"glVertexBlendEnviATI", "(II)V", (void*)&Java_org_lwjgl_opengl_ATIVertexStreams_glVertexBlendEnviATI, "glVertexBlendEnviATI", (void*)&glVertexBlendEnviATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif

