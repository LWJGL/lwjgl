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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBVertexShader
// ----------------------------------

#include "extgl.h"


typedef unsigned char GLcharARB;
typedef unsigned int GLhandleARB;

typedef void (APIENTRY * glBindAttribLocationARBPROC) (GLhandleARB programObj, GLuint index, const GLcharARB *name);
typedef void (APIENTRY * glGetActiveAttribARBPROC) (GLhandleARB programObj, GLuint index, GLsizei maxLength, GLsizei *length, GLint *size, GLenum *type, GLcharARB *name);
typedef GLint (APIENTRY * glGetAttribLocationARBPROC) (GLhandleARB programObj, const GLcharARB *name);

static glBindAttribLocationARBPROC glBindAttribLocationARB;
static glGetActiveAttribARBPROC glGetActiveAttribARB;
static glGetAttribLocationARBPROC glGetAttribLocationARB;

/*
 * Class:	org.lwjgl.opengl.ARBVertexShader
 * Method:	nglBindAttribLocationARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglBindAttribLocationARB
	(JNIEnv * env, jclass clazz, jint programObj, jint index, jobject name, jint nameOffset)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	glBindAttribLocationARB(programObj, index, name_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexShader
 * Method:	nglGetActiveAttribARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglGetActiveAttribARB
	(JNIEnv * env, jclass clazz, jint programObj, jint index, jint maxLength, jobject length, jint lengthOffset, jobject size, jint sizeOffset, jobject type, jint typeOffset, jobject name, jint nameOffset)
{

	GLint *size_ptr = (GLint *)env->GetDirectBufferAddress(size) + sizeOffset;
	GLenum *type_ptr = (GLenum *)env->GetDirectBufferAddress(type) + typeOffset;
	GLcharARB *name_ptr = (GLcharARB *)env->GetDirectBufferAddress(name) + nameOffset;

	if ( length == NULL ) {
		glGetActiveAttribARB(programObj, index, maxLength, NULL, size_ptr, type_ptr, name_ptr);
	} else {
		GLsizei *length_ptr = (GLsizei *)env->GetDirectBufferAddress(length) + lengthOffset;
		glGetActiveAttribARB(programObj, index, maxLength, length_ptr, size_ptr, type_ptr, name_ptr);
	}

	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexShader
 * Method:	nglGetAttribLocationARB
 */
static jint JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglGetAttribLocationARB
	(JNIEnv * env, jclass clazz, jint programObj, jobject name, jint nameOffset)
{
	GLubyte *name_ptr = (GLubyte *)env->GetDirectBufferAddress(name) + nameOffset;
	GLuint result = glGetAttribLocationARB(programObj, name_ptr);
	
	return result;
}

extern "C" {
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglBindAttribLocationARB", "(IILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexShader_nglBindAttribLocationARB, "glBindAttribLocationARB", (void**)&glBindAttribLocationARB},
		{"nglGetActiveAttribARB", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexShader_nglGetActiveAttribARB, "glGetActiveAttribARB", (void**)&glGetActiveAttribARB},
		{"nglGetAttribLocationARB", "(ILjava/nio/ByteBuffer;I)I", (void*)&Java_org_lwjgl_opengl_ARBVertexShader_nglGetAttribLocationARB, "glGetAttribLocationARB", (void**)&glGetAttribLocationARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
}

