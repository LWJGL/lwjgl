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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ATIEnvmapBumpmap
// ----------------------------------

#include "extgl.h"


typedef void (APIENTRY * glTexBumpParameterivATIPROC) (GLenum pname, GLint *param);
typedef void (APIENTRY * glTexBumpParameterfvATIPROC) (GLenum pname, GLfloat *param);
typedef void (APIENTRY * glGetTexBumpParameterivATIPROC) (GLenum pname, GLint *param);
typedef void (APIENTRY * glGetTexBumpParameterfvATIPROC) (GLenum pname, GLfloat *param);

static glTexBumpParameterivATIPROC glTexBumpParameterivATI;
static glTexBumpParameterfvATIPROC glTexBumpParameterfvATI;
static glGetTexBumpParameterivATIPROC glGetTexBumpParameterivATI;
static glGetTexBumpParameterfvATIPROC glGetTexBumpParameterfvATI;

/*
 * Class:	org.lwjgl.opengl.ATIEnvmapBumpmap
 * Method:	nglTexBumpParameterfvATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterfvATI
	(JNIEnv * env, jclass clazz, jint pname, jobject pfParam, jint pfParam_offset)
{
	GLfloat *pfParam_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParam) + pfParam_offset;
	glTexBumpParameterfvATI(pname, pfParam_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIEnvmapBumpmap
 * Method:	nglTexBumpParameterivATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterivATI
	(JNIEnv * env, jclass clazz, jint pname, jobject piParam, jint piParam_offset)
{
	GLint *piParam_ptr = (GLint *)env->GetDirectBufferAddress(piParam) + piParam_offset;
	glTexBumpParameterivATI(pname, piParam_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIEnvmapBumpmap
 * Method:	nglGetTexBumpParameterfvATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterfvATI
	(JNIEnv * env, jclass clazz, jint pname, jobject pfParam, jint pfParam_offset)
{
	GLfloat *pfParam_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParam) + pfParam_offset;
	glGetTexBumpParameterfvATI(pname, pfParam_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIEnvmapBumpmap
 * Method:	nglGetTexBumpParameterivATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterivATI
	(JNIEnv * env, jclass clazz, jint pname, jobject piParam, jint piParam_offset)
{
	GLint *piParam_ptr = (GLint *)env->GetDirectBufferAddress(piParam) + piParam_offset;
	glGetTexBumpParameterivATI(pname, piParam_ptr);
	
}

void extgl_InitATIEnvmapBumpmap(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"nglTexBumpParameterfvATI", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterfvATI, "glTexBumpParameterfvATI", (void**)&glTexBumpParameterfvATI},
		{"nglTexBumpParameterivATI", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterivATI, "glTexBumpParameterivATI", (void**)&glTexBumpParameterivATI},
		{"nglGetTexBumpParameterfvATI", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterfvATI, "glGetTexBumpParameterfvATI", (void**)&glGetTexBumpParameterfvATI},
		{"nglGetTexBumpParameterivATI", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterivATI, "glGetTexBumpParameterivATI", (void**)&glGetTexBumpParameterivATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/ATIEnvmapBumpmap");
	if (extgl_Extensions.GL_ATI_envmap_bumpmap)
		extgl_InitializeClass(env, clazz, ext_set, "GL_ATI_envmap_bumpmap", num_functions, functions);
}
