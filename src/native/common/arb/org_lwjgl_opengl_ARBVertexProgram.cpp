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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBVertexProgram
// ----------------------------------

#include "extgl.h"
#include "common_tools.h"

typedef void (APIENTRY * glVertexAttrib1sARBPROC) (GLuint index, GLshort x);
typedef void (APIENTRY * glVertexAttrib1fARBPROC) (GLuint index, GLfloat x);
typedef void (APIENTRY * glVertexAttrib2sARBPROC) (GLuint index, GLshort x, GLshort y);
typedef void (APIENTRY * glVertexAttrib2fARBPROC) (GLuint index, GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertexAttrib3sARBPROC) (GLuint index, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertexAttrib3fARBPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertexAttrib4sARBPROC) (GLuint index, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertexAttrib4fARBPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertexAttrib4NubARBPROC) (GLuint index, GLubyte x, GLubyte y, GLubyte z, GLubyte w);
typedef void (APIENTRY * glVertexAttrib1svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib1fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib2svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib2fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib2dvARBPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib3svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib3fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib3dvARBPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib4bvARBPROC) (GLuint index, const GLbyte *v);
typedef void (APIENTRY * glVertexAttrib4svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib4ivARBPROC) (GLuint index, const GLint *v);
typedef void (APIENTRY * glVertexAttrib4ubvARBPROC) (GLuint index, const GLubyte *v);
typedef void (APIENTRY * glVertexAttrib4usvARBPROC) (GLuint index, const GLushort *v);
typedef void (APIENTRY * glVertexAttrib4uivARBPROC) (GLuint index, const GLuint *v);
typedef void (APIENTRY * glVertexAttrib4fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib4dvARBPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib4NbvARBPROC) (GLuint index, const GLbyte *v);
typedef void (APIENTRY * glVertexAttrib4NsvARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib4NivARBPROC) (GLuint index, const GLint *v);
typedef void (APIENTRY * glVertexAttrib4NubvARBPROC) (GLuint index, const GLubyte *v);
typedef void (APIENTRY * glVertexAttrib4NusvARBPROC) (GLuint index, const GLushort *v);
typedef void (APIENTRY * glVertexAttrib4NuivARBPROC) (GLuint index, const GLuint *v);
typedef void (APIENTRY * glVertexAttribPointerARBPROC) (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glEnableVertexAttribArrayARBPROC) (GLuint index);
typedef void (APIENTRY * glDisableVertexAttribArrayARBPROC) (GLuint index);
typedef void (APIENTRY * glGetVertexAttribdvARBPROC) (GLuint index, GLenum pname, GLdouble *params);
typedef void (APIENTRY * glGetVertexAttribfvARBPROC) (GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVertexAttribivARBPROC) (GLuint index, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribPointervARBPROC) (GLuint index, GLenum pname, GLvoid **pointer);

static glVertexAttrib1sARBPROC glVertexAttrib1sARB;
static glVertexAttrib1fARBPROC glVertexAttrib1fARB;
static glVertexAttrib2sARBPROC glVertexAttrib2sARB;
static glVertexAttrib2fARBPROC glVertexAttrib2fARB;
static glVertexAttrib3sARBPROC glVertexAttrib3sARB;
static glVertexAttrib3fARBPROC glVertexAttrib3fARB;
static glVertexAttrib4sARBPROC glVertexAttrib4sARB;
static glVertexAttrib4fARBPROC glVertexAttrib4fARB;
static glVertexAttrib4NubARBPROC glVertexAttrib4NubARB;
static glVertexAttribPointerARBPROC glVertexAttribPointerARB;
static glEnableVertexAttribArrayARBPROC glEnableVertexAttribArrayARB;
static glDisableVertexAttribArrayARBPROC glDisableVertexAttribArrayARB;
static glGetVertexAttribfvARBPROC glGetVertexAttribfvARB;
static glGetVertexAttribivARBPROC glGetVertexAttribivARB;
static glGetVertexAttribPointervARBPROC glGetVertexAttribPointervARB;

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib1sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x)
{
	glVertexAttrib1sARB(index, x);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib1fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x)
{
	glVertexAttrib1fARB(index, x);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib2sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y)
{
	glVertexAttrib2sARB(index, x, y);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib2fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y)
{
	glVertexAttrib2fARB(index, x, y);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib3sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z)
{
	glVertexAttrib3sARB(index, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib3fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z)
{
	glVertexAttrib3fARB(index, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib4sARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4sARB
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w)
{
	glVertexAttrib4sARB(index, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib4fARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4fARB
	(JNIEnv * env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w)
{
	glVertexAttrib4fARB(index, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glVertexAttrib4NubARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4NubARB
	(JNIEnv * env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w)
{
	glVertexAttrib4NubARB(index, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	nglVertexAttribPointerARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARB
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jobject buffer, jint bufferOffset)
{
	GLvoid *buffer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(buffer) + bufferOffset);
	glVertexAttribPointerARB(index, size, type, normalized, stride, buffer_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	nglVertexAttribPointerARBVBO
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARBVBO
	(JNIEnv * env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jint bufferOffset)
{
	glVertexAttribPointerARB(index, size, type, normalized, stride, (GLvoid *)bufferOffset);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glEnableVertexAttribArrayARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glEnableVertexAttribArrayARB
	(JNIEnv * env, jclass clazz, jint index)
{
	glEnableVertexAttribArrayARB(index);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glDisableVertexAttribArrayARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glDisableVertexAttribArrayARB
	(JNIEnv * env, jclass clazz, jint index)
{
	glDisableVertexAttribArrayARB(index);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	nglGetVertexAttribfvARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribfvARB
	(JNIEnv * env, jclass clazz, jint index, jint pname, jobject params, jint paramsOffset)
{
	GLfloat *params_ptr = (GLfloat *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribfvARB(index, pname, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	nglGetVertexAttribivARB
 */
static void JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribivARB
	(JNIEnv * env, jclass clazz, jint index, jint pname, jobject params, jint paramsOffset)
{
	GLint *params_ptr = (GLint *)env->GetDirectBufferAddress(params) + paramsOffset;
	glGetVertexAttribivARB(index, pname, params_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ARBVertexProgram
 * Method:	glGetVertexAttribPointerARB
 */
static jobject JNICALL Java_org_lwjgl_opengl_ARBVertexProgram_glGetVertexAttribPointerARB
	(JNIEnv * env, jclass clazz, jint index, jint pname, jint size)
{
        void *address;
        glGetVertexAttribPointervARB((GLuint)index, (GLuint)pname, &address);
        
        return safeNewBuffer(env, address, size);
}

void extgl_InitARBVertexProgram(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"glVertexAttrib1sARB", "(IS)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1sARB, "glVertexAttrib1sARB", (void**)&glVertexAttrib1sARB},
		{"glVertexAttrib1fARB", "(IF)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib1fARB, "glVertexAttrib1fARB", (void**)&glVertexAttrib1fARB},
		{"glVertexAttrib2sARB", "(ISS)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2sARB, "glVertexAttrib2sARB", (void**)&glVertexAttrib2sARB},
		{"glVertexAttrib2fARB", "(IFF)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib2fARB, "glVertexAttrib2fARB", (void**)&glVertexAttrib2fARB},
		{"glVertexAttrib3sARB", "(ISSS)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3sARB, "glVertexAttrib3sARB", (void**)&glVertexAttrib3sARB},
		{"glVertexAttrib3fARB", "(IFFF)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib3fARB, "glVertexAttrib3fARB", (void**)&glVertexAttrib3fARB},
		{"glVertexAttrib4sARB", "(ISSSS)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4sARB, "glVertexAttrib4sARB", (void**)&glVertexAttrib4sARB},
		{"glVertexAttrib4fARB", "(IFFFF)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4fARB, "glVertexAttrib4fARB", (void**)&glVertexAttrib4fARB},
		{"glVertexAttrib4NubARB", "(IBBBB)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glVertexAttrib4NubARB, "glVertexAttrib4NubARB", (void**)&glVertexAttrib4NubARB},
		{"nglVertexAttribPointerARB", "(IIIZILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARB, "glVertexAttribPointerARB", (void**)&glVertexAttribPointerARB},
		{"nglVertexAttribPointerARBVBO", "(IIIZII)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_nglVertexAttribPointerARBVBO, NULL, NULL},
		{"glEnableVertexAttribArrayARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glEnableVertexAttribArrayARB, "glEnableVertexAttribArrayARB", (void**)&glEnableVertexAttribArrayARB},
		{"glDisableVertexAttribArrayARB", "(I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glDisableVertexAttribArrayARB, "glDisableVertexAttribArrayARB", (void**)&glDisableVertexAttribArrayARB},
		{"nglGetVertexAttribfvARB", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribfvARB, "glGetVertexAttribfvARB", (void**)&glGetVertexAttribfvARB},
		{"nglGetVertexAttribivARB", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_nglGetVertexAttribivARB, "glGetVertexAttribivARB", (void**)&glGetVertexAttribivARB},
		{"glGetVertexAttribPointerARB", "(III)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_ARBVertexProgram_glGetVertexAttribPointerARB, "glGetVertexAttribPointervARB", (void**)&glGetVertexAttribPointervARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/ARBVertexProgram");
	if (extgl_Extensions.GL_ARB_vertex_program)
		extgl_InitializeClass(env, clazz, ext_set, "GL_ARB_vertex_program", num_functions, functions);
}

