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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBBufferObject
// ----------------------------------

#include "extgl.h"
#include "common_tools.h"

typedef int GLintptrARB;
typedef unsigned int GLsizeiptrARB;

typedef void (APIENTRY * glBindBufferARBPROC) (GLenum target, GLuint buffer);
typedef void (APIENTRY * glDeleteBuffersARBPROC) (GLsizei n, const GLuint *buffers);
typedef void (APIENTRY * glGenBuffersARBPROC) (GLsizei n, GLuint *buffers);
typedef GLboolean (APIENTRY * glIsBufferARBPROC) (GLuint buffer);
typedef void (APIENTRY * glBufferDataARBPROC) (GLenum target, GLsizeiptrARB size, const GLvoid *data, GLenum usage);
typedef void (APIENTRY * glBufferSubDataARBPROC) (GLenum target, GLintptrARB offset, GLsizeiptrARB size, const GLvoid *data);
typedef void (APIENTRY * glGetBufferSubDataARBPROC) (GLenum target, GLintptrARB offset, GLsizeiptrARB size, GLvoid *data);
typedef void * (APIENTRY * glMapBufferARBPROC) (GLenum target, GLenum access);
typedef GLboolean (APIENTRY * glUnmapBufferARBPROC) (GLenum target);
typedef void (APIENTRY * glGetBufferParameterivARBPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetBufferPointervARBPROC) (GLenum target, GLenum pname, GLvoid **params);

static glBindBufferARBPROC glBindBufferARB;
static glDeleteBuffersARBPROC glDeleteBuffersARB;
static glGenBuffersARBPROC glGenBuffersARB;
static glIsBufferARBPROC glIsBufferARB;
static glBufferDataARBPROC glBufferDataARB;
static glBufferSubDataARBPROC glBufferSubDataARB;
static glGetBufferSubDataARBPROC glGetBufferSubDataARB;
static glMapBufferARBPROC glMapBufferARB;
static glUnmapBufferARBPROC glUnmapBufferARB;
static glGetBufferParameterivARBPROC glGetBufferParameterivARB;
static glGetBufferPointervARBPROC glGetBufferPointervARB;

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	nglBindBufferARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglBindBufferARB
	(JNIEnv * env, jclass clazz, jint target, jint buffer)
{
	glBindBufferARB(target, buffer);
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	nglDeleteBuffersARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglDeleteBuffersARB
	(JNIEnv * env, jclass clazz, jint n, jobject buffers, jint buffers_offset)
{
	GLuint *buffers_ptr = (GLuint *)env->GetDirectBufferAddress(buffers) + buffers_offset;
	glDeleteBuffersARB(n, buffers_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	nglGenBuffersARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglGenBuffersARB
	(JNIEnv * env, jclass clazz, jint n, jobject buffers, jint buffers_offset)
{
	GLuint *buffers_ptr = (GLuint *)env->GetDirectBufferAddress(buffers) + buffers_offset;
	glGenBuffersARB(n, buffers_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	glIsBufferARB
 */
static jboolean JNICALL Java_org_lwjgl_opengl_ARBBufferObject_glIsBufferARB
	(JNIEnv * env, jclass clazz, jint buffer)
{
	GLboolean result = glIsBufferARB(buffer);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	nglBufferDataARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglBufferDataARB
	(JNIEnv * env, jclass clazz, jint target, jint size, jobject data, jint data_offset, jint usage)
{
	GLvoid *data_ptr = (GLvoid *)safeGetBufferAddress(env, data, data_offset);
	glBufferDataARB(target, size, data_ptr, usage);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	nglBufferSubDataARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglBufferSubDataARB
	(JNIEnv * env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_offset)
{
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + data_offset);
	glBufferSubDataARB(target, offset, size, data_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	nglGetBufferSubDataARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferSubDataARB
	(JNIEnv * env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_offset)
{
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + data_offset);
	glGetBufferSubDataARB(target, offset, size, data_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	glMapBufferARB
 */
static jobject JNICALL Java_org_lwjgl_opengl_ARBBufferObject_glMapBufferARB
	(JNIEnv * env, jclass clazz, jint target, jint access, jint size, jobject oldBuffer)
{
	void *buffer_address = glMapBufferARB((GLenum)target, (GLenum)access);
	
	void *old_buffer_address = safeGetBufferAddress(env, oldBuffer, 0);
	if (old_buffer_address == buffer_address)
		return oldBuffer;
	else
		return safeNewBuffer(env, buffer_address, size);
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	glUnmapBufferARB
 */
static jboolean JNICALL Java_org_lwjgl_opengl_ARBBufferObject_glUnmapBufferARB
	(JNIEnv * env, jclass clazz, jint target)
{
	GLboolean result = glUnmapBufferARB(target);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	nglGetBufferParameterivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferParameterivARB
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint params_offset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + params_offset;
	glGetBufferParameterivARB(target, pname, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBBufferObject
 * Method:	glGetBufferPointerARB
 */
static jobject JNICALL Java_org_lwjgl_opengl_ARBBufferObject_glGetBufferPointerARB
	(JNIEnv * env, jclass clazz, jint target, jint pname, jint size)
{
        void *pointer;
        glGetBufferPointervARB((GLenum)target, (GLenum)pname, &pointer);
        
        return safeNewBuffer(env, pointer, size);
}

void extgl_InitARBBufferObject(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"nglBindBufferARB", "(II)V", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_nglBindBufferARB, "glBindBufferARB", (void**)&glBindBufferARB},
		{"nglDeleteBuffersARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_nglDeleteBuffersARB, "glDeleteBuffersARB", (void**)&glDeleteBuffersARB},
		{"nglGenBuffersARB", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_nglGenBuffersARB, "glGenBuffersARB", (void**)&glGenBuffersARB},
		{"glIsBufferARB", "(I)Z", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_glIsBufferARB, "glIsBufferARB", (void**)&glIsBufferARB},
		{"nglBufferDataARB", "(IILjava/nio/Buffer;II)V", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_nglBufferDataARB, "glBufferDataARB", (void**)&glBufferDataARB},
		{"nglBufferSubDataARB", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_nglBufferSubDataARB, "glBufferSubDataARB", (void**)&glBufferSubDataARB},
		{"nglGetBufferSubDataARB", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferSubDataARB, "glGetBufferSubDataARB", (void**)&glGetBufferSubDataARB},
		{"glMapBufferARB", "(IIILjava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_glMapBufferARB, "glMapBufferARB", (void**)&glMapBufferARB},
		{"glUnmapBufferARB", "(I)Z", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_glUnmapBufferARB, "glUnmapBufferARB", (void**)&glUnmapBufferARB},
		{"nglGetBufferParameterivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_nglGetBufferParameterivARB, "glGetBufferParameterivARB", (void**)&glGetBufferParameterivARB},
		{"glGetBufferPointerARB", "(III)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_ARBBufferObject_glGetBufferPointerARB, "glGetBufferPointervARB", (void**)&glGetBufferPointervARB}
	};

	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/ARBBufferObject");

	extgl_InitializeClass(env, clazz, NULL, "<ARBBufferObject>", num_functions, functions);
}
