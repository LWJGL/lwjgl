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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.arb.ARBVertexBufferObject
// ----------------------------------

#include "org_lwjgl_opengl_arb_ARBVertexBufferObject.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglBindBufferARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglBindBufferARB
	(JNIEnv * env, jclass clazz, jint target, jint buffer)
{
	CHECK_EXISTS(glBindBufferARB)
	glBindBufferARB(target, buffer);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglDeleteBuffersARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglDeleteBuffersARB
	(JNIEnv * env, jclass clazz, jint n, jobject buffers, jint buffers_offset)
{
	CHECK_EXISTS(glDeleteBuffersARB)
	GLuint *buffers_ptr = (GLuint *)env->GetDirectBufferAddress(buffers) + buffers_offset;
	glDeleteBuffersARB(n, buffers_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglGenBuffersARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglGenBuffersARB
	(JNIEnv * env, jclass clazz, jint n, jobject buffers, jint buffers_offset)
{
	CHECK_EXISTS(glGenBuffersARB)
	GLuint *buffers_ptr = (GLuint *)env->GetDirectBufferAddress(buffers) + buffers_offset;
	glGenBuffersARB(n, buffers_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glIsBufferARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glIsBufferARB
	(JNIEnv * env, jclass clazz, jint buffer)
{
	CHECK_EXISTS(glIsBufferARB)
	GLboolean result = glIsBufferARB(buffer);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglBufferDataARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglBufferDataARB
	(JNIEnv * env, jclass clazz, jint target, jint size, jobject data, jint data_offset, jint usage)
{
	CHECK_EXISTS(glBufferDataARB)
	GLvoid *data_ptr = (GLvoid *)safeGetBufferAddress(env, data, data_offset);
	glBufferDataARB(target, size, data_ptr, usage);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglBufferSubDataARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglBufferSubDataARB
	(JNIEnv * env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_offset)
{
	CHECK_EXISTS(glBufferSubDataARB)
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + data_offset);
	glBufferSubDataARB(target, offset, size, data_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglGetBufferSubDataARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglGetBufferSubDataARB
	(JNIEnv * env, jclass clazz, jint target, jint offset, jint size, jobject data, jint data_offset)
{
	CHECK_EXISTS(glGetBufferSubDataARB)
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + data_offset);
	glGetBufferSubDataARB(target, offset, size, data_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glMapBufferARB
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glMapBufferARB
	(JNIEnv * env, jclass clazz, jint target, jint access, jint size, jobject oldBuffer)
{
        CHECK_EXISTS(glMapBufferARB)
        void *buffer_address = glMapBufferARB((GLenum)target, (GLenum)access);
        CHECK_GL_ERROR
        if (oldBuffer != NULL) {
                void *old_buffer_address = env->GetDirectBufferAddress(oldBuffer);
                if (old_buffer_address == buffer_address)
                        return oldBuffer;
        }
        return safeNewBuffer(env, buffer_address, size);
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glUnmapBufferARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glUnmapBufferARB
	(JNIEnv * env, jclass clazz, jint target)
{
	CHECK_EXISTS(glUnmapBufferARB)
	GLboolean result = glUnmapBufferARB(target);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglGetBufferParameterivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglGetBufferParameterivARB
	(JNIEnv * env, jclass clazz, jint target, jint pname, jobject params, jint params_offset)
{
	CHECK_EXISTS(glGetBufferParameterivARB)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + params_offset;
	glGetBufferParameterivARB(target, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glGetBufferPointerARB
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glGetBufferPointerARB
	(JNIEnv * env, jclass clazz, jint target, jint pname, jint size)
{
        CHECK_EXISTS(glGetBufferPointervARB)
        void *pointer;
        glGetBufferPointervARB((GLenum)target, (GLenum)pname, &pointer);
        CHECK_GL_ERROR
        return safeNewBuffer(env, pointer, size);
}
