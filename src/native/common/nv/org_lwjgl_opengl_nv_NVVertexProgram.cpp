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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVVertexProgram
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVVertexProgram.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglExecuteProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglExecuteProgramNV
	(JNIEnv * env, jclass clazz, jint target, jint id, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glExecuteProgramNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glExecuteProgramNV(target, id, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetProgramParameterfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetProgramParameterfvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetProgramParameterfvNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetProgramParameterfvNV(target, index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetTrackMatrixivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetTrackMatrixivNV
	(JNIEnv * env, jclass clazz, jint target, jint address, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetTrackMatrixivNV)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetTrackMatrixivNV(target, address, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetVertexAttribfvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetVertexAttribfvNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribfvNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribfvNV(index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglGetVertexAttribivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglGetVertexAttribivNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribivNV)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribivNV(index, parameterName, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glGetVertexAttribPointerNV
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glGetVertexAttribPointerNV
	(JNIEnv * env, jclass clazz, jint index, jint parameterName, jint size)
{
        CHECK_EXISTS(glGetVertexAttribPointervNV)
        void *address;
        glGetVertexAttribPointervNV((GLuint)index, (GLuint)parameterName, &address);
        CHECK_GL_ERROR
        return safeNewBuffer(env, address, size);
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glProgramParameter4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glProgramParameter4fNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glProgramParameter4fNV)
	glProgramParameter4fNV(target, index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglProgramParameters4fvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglProgramParameters4fvNV
	(JNIEnv * env, jclass clazz, jint target, jint index, jint count, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glProgramParameters4fvNV)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glProgramParameters4fvNV(target, index, count, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glTrackMatrixNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glTrackMatrixNV
	(JNIEnv * env, jclass clazz, jint target, jint address, jint matrix, jint transform)
{
	CHECK_EXISTS(glTrackMatrixNV)
	glTrackMatrixNV(target, address, matrix, transform);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglVertexAttribPointerNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglVertexAttribPointerNV
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jint stride, jobject buffer, jint bufferOffset)
{
	CHECK_EXISTS(glVertexAttribPointerNV)
	GLvoid *buffer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(buffer) + bufferOffset);
	glVertexAttribPointerNV(index, size, type, stride, buffer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	nglVertexAttribPointerNVVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_nglVertexAttribPointerNVVBO
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jint stride, jint bufferOffset)
{
	CHECK_EXISTS(glVertexAttribPointerNV)
	glVertexAttribPointerNV(index, size, type, stride, (GLvoid *)bufferOffset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib1sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib1sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x)
{
	CHECK_EXISTS(glVertexAttrib1sNV)
	glVertexAttrib1sNV(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib1fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib1fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x)
{
	CHECK_EXISTS(glVertexAttrib1fNV)
	glVertexAttrib1fNV(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib2sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib2sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y)
{
	CHECK_EXISTS(glVertexAttrib2sNV)
	glVertexAttrib2sNV(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib2fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib2fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y)
{
	CHECK_EXISTS(glVertexAttrib2fNV)
	glVertexAttrib2fNV(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib3sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib3sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glVertexAttrib3sNV)
	glVertexAttrib3sNV(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib3fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib3fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z)
{
	CHECK_EXISTS(glVertexAttrib3fNV)
	glVertexAttrib3fNV(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib4sNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib4sNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w)
{
	CHECK_EXISTS(glVertexAttrib4sNV)
	glVertexAttrib4sNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib4fNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib4fNV
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glVertexAttrib4fNV)
	glVertexAttrib4fNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexProgram
 * Method:	glVertexAttrib4ubNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexProgram_glVertexAttrib4ubNV
	(JNIEnv * env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w)
{
	CHECK_EXISTS(glVertexAttrib4ubNV)
	glVertexAttrib4ubNV(index, x, y, z, w);
	CHECK_GL_ERROR
}
