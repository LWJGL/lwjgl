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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ext.EXTCompiledVertexArray
// ----------------------------------

#include "org_lwjgl_opengl_ext_EXTCompiledVertexArray.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glLockArraysEXTPROC) (GLint first, GLsizei count);
typedef void (APIENTRY * glUnlockArraysEXTPROC) ();

static glLockArraysEXTPROC glLockArraysEXT;
static glUnlockArraysEXTPROC glUnlockArraysEXT;

void extgl_InitEXTCompiledVertexArray(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_compiled_vertex_array)
		return;
	glLockArraysEXT = (glLockArraysEXTPROC) extgl_GetProcAddress("glLockArraysEXT");
	glUnlockArraysEXT = (glUnlockArraysEXTPROC) extgl_GetProcAddress("glUnlockArraysEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_compiled_vertex_array)
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTCompiledVertexArray
 * Method:	glLockArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTCompiledVertexArray_glLockArraysEXT
	(JNIEnv * env, jclass clazz, jint first, jint count)
{
	CHECK_EXISTS(glLockArraysEXT)
	glLockArraysEXT(first, count);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTCompiledVertexArray
 * Method:	glUnlockArraysEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTCompiledVertexArray_glUnlockArraysEXT
	(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glUnlockArraysEXT)
	glUnlockArraysEXT();
	CHECK_GL_ERROR
}
