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

// -------------------------------------------
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.EXTPalettedTexture
// -------------------------------------------

#include "extgl.h"
#include "common_tools.h"

typedef void (APIENTRY * glColorTableEXTPROC) (GLenum target, GLenum internalFormat, GLsizei width, GLenum format, GLenum type, const GLvoid *data);
typedef void (APIENTRY * glColorSubTableEXTPROC) (GLenum target, GLsizei start, GLsizei count, GLenum format, GLenum type, const GLvoid *data);
typedef void (APIENTRY * glGetColorTableEXTPROC) (GLenum target, GLenum format, GLenum type, GLvoid *data);
typedef void (APIENTRY * glGetColorTableParameterivEXTPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetColorTableParameterfvEXTPROC) (GLenum target, GLenum pname, GLfloat *params);

static glColorTableEXTPROC glColorTableEXT;
static glColorSubTableEXTPROC glColorSubTableEXT;
static glGetColorTableEXTPROC glGetColorTableEXT;
static glGetColorTableParameterivEXTPROC glGetColorTableParameterivEXT;
static glGetColorTableParameterfvEXTPROC glGetColorTableParameterfvEXT;

/*
 * Class:	org.lwjgl.opengl.EXTPalettedTexture
 * Method:	nglColorTableEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorTableEXT
	(JNIEnv * env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject data, jint dataOffset)
{
	const void *address = (const void *)(dataOffset + (GLbyte *)(*env)->GetDirectBufferAddress(env, data));
	glColorTableEXT(target, internalFormat, width, format, type, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTPalettedTexture
 * Method:	nglColorSubTableEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorSubTableEXT
	(JNIEnv * env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject data, jint dataOffset)
{
	const void *address = (const void *)(dataOffset + (GLbyte *)(*env)->GetDirectBufferAddress(env, data));
	glColorSubTableEXT(target, start, count, format, type, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTPalettedTexture
 * Method:	nglGetColorTableEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableEXT
	(JNIEnv * env, jclass clazz, jint target, jint format, jint type, jobject data, jint dataOffset)
{
	void *address = (void *)(dataOffset + (GLbyte *)(*env)->GetDirectBufferAddress(env, data));
	glGetColorTableEXT(target, format, type, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTPalettedTexture
 * Method:	nglGetColorTableParameterivEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterivEXT
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint paramsOffset)
{
	GLint *address = paramsOffset + (GLint *)(*env)->GetDirectBufferAddress(env, params);
	glGetColorTableParameterivEXT(target, pname, address);
}

/*
 * Class:	org.lwjgl.opengl.EXTPalettedTexture
 * Method:	nglGetColorTableParameterfvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterfvEXT
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint paramsOffset)
{
	GLfloat *address = paramsOffset + (GLfloat *)(*env)->GetDirectBufferAddress(env, params);
	glGetColorTableParameterfvEXT(target, pname, address);
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTPalettedTexture_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglColorTableEXT", "(IIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorTableEXT, "glColorTableEXT", (void*)&glColorTableEXT},
		{"nglColorSubTableEXT", "(IIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglColorSubTableEXT, "glColorSubTableEXT", (void*)&glColorSubTableEXT},
		{"nglGetColorTableEXT", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableEXT, "glGetColorTableEXT", (void*)&glGetColorTableEXT},
		{"nglGetColorTableParameterivEXT", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterivEXT, "glGetColorTableParameterivEXT", (void*)&glGetColorTableParameterivEXT},
		{"nglGetColorTableParameterfvEXT", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTPalettedTexture_nglGetColorTableParameterfvEXT, "glGetColorTableParameterfvEXT", (void*)&glGetColorTableParameterfvEXT},
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif