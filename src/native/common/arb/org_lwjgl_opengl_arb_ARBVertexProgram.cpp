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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.arb.ARBVertexProgram
// ----------------------------------

#include "org_lwjgl_opengl_arb_ARBVertexProgram.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib1sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib1sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x)
{
	CHECK_EXISTS(glVertexAttrib1sARB)
	glVertexAttrib1sARB(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib1fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x)
{
	CHECK_EXISTS(glVertexAttrib1fARB)
	glVertexAttrib1fARB(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib2sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib2sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y)
{
	CHECK_EXISTS(glVertexAttrib2sARB)
	glVertexAttrib2sARB(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib2fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y)
{
	CHECK_EXISTS(glVertexAttrib2fARB)
	glVertexAttrib2fARB(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib3sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib3sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glVertexAttrib3sARB)
	glVertexAttrib3sARB(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib3fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z)
{
	CHECK_EXISTS(glVertexAttrib3fARB)
	glVertexAttrib3fARB(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib4sARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib4sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w)
{
	CHECK_EXISTS(glVertexAttrib4sARB)
	glVertexAttrib4sARB(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib4fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	CHECK_EXISTS(glVertexAttrib4fARB)
	glVertexAttrib4fARB(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glVertexAttrib4NubARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glVertexAttrib4NubARB
	(JNIEnv * env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w)
{
	CHECK_EXISTS(glVertexAttrib4NubARB)
	glVertexAttrib4NubARB(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib1svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib1svARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib1svARB)
	GLshort *values_ptr = (GLshort *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib1svARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib1fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib1fvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib1fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib1fvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib2svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib2svARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib2svARB)
	GLshort *values_ptr = (GLshort *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib2svARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib2fvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib2fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib2fvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib3svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib3svARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib3svARB)
	GLshort *values_ptr = (GLshort *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib3svARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib3fvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib3fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib3fvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4bvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4bvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4bvARB)
	GLbyte *values_ptr = (GLbyte *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4bvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4svARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4svARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4svARB)
	GLshort *values_ptr = (GLshort *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4svARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4ivARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4ivARB)
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4ivARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4ubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4ubvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4ubvARB)
	GLubyte *values_ptr = (GLubyte *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4ubvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4usvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4usvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4usvARB)
	GLushort *values_ptr = (GLushort *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4usvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4uivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4uivARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4uivARB)
	GLuint *values_ptr = (GLuint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4uivARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4fvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4fvARB)
	GLfloat *values_ptr = (GLfloat *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4fvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4NbvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4NbvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4NbvARB)
	GLbyte *values_ptr = (GLbyte *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4NbvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4NsvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4NsvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4NsvARB)
	GLshort *values_ptr = (GLshort *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4NsvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4NivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4NivARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4NivARB)
	GLint *values_ptr = (GLint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4NivARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4NubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4NubvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4NubvARB)
	GLubyte *values_ptr = (GLubyte *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4NubvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4NusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4NusvARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4NusvARB)
	GLushort *values_ptr = (GLushort *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4NusvARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttrib4NuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttrib4NuivARB
	(JNIEnv * env, jclass clazz, jint index, jobject values, jint valuesOffset)
{
	CHECK_EXISTS(glVertexAttrib4NuivARB)
	GLuint *values_ptr = (GLuint *)env->GetDirectBufferAddress(values) + valuesOffset;
	glVertexAttrib4NuivARB(index, values_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttribPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttribPointerARB
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jobject buffer, jint bufferOffset)
{
	CHECK_EXISTS(glVertexAttribPointerARB)
	GLvoid *buffer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(buffer) + bufferOffset);
	glVertexAttribPointerARB(index, size, type, normalized, stride, buffer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglVertexAttribPointerARBVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglVertexAttribPointerARBVBO
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jint bufferOffset)
{
	CHECK_EXISTS(glVertexAttribPointerARB)
	glVertexAttribPointerARB(index, size, type, normalized, stride, (GLvoid *)bufferOffset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glEnableVertexAttribArrayARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glEnableVertexAttribArrayARB
	(JNIEnv * env, jclass clazz, jint index)
{
	CHECK_EXISTS(glEnableVertexAttribArrayARB)
	glEnableVertexAttribArrayARB(index);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glDisableVertexAttribArrayARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glDisableVertexAttribArrayARB
	(JNIEnv * env, jclass clazz, jint index)
{
	CHECK_EXISTS(glDisableVertexAttribArrayARB)
	glDisableVertexAttribArrayARB(index);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglGetVertexAttribfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglGetVertexAttribfvARB
	(JNIEnv * env, jclass clazz, jint index, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribfvARB)
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribfvARB(index, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	nglGetVertexAttribivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_nglGetVertexAttribivARB
	(JNIEnv * env, jclass clazz, jint index, jint pname, jobject params, jint paramsOffset)
{
	CHECK_EXISTS(glGetVertexAttribivARB)
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribivARB(index, pname, params_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexProgram
 * Method:	glGetVertexAttribPointerARB
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_arb_ARBVertexProgram_glGetVertexAttribPointerARB
	(JNIEnv * env, jclass clazz, jint index, jint pname, jint size)
{
        CHECK_EXISTS(glGetVertexAttribPointervARB)
        void *address;
        glGetVertexAttribPointervARB((GLuint)index, (GLuint)pname, &address);
        CHECK_GL_ERROR
        return safeNewBuffer(env, address, size);
}
