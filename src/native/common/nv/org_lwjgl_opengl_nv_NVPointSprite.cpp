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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVPointSprite
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVPointSprite.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glPointParameteriNVPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glPointParameterivNVPROC) (GLenum pname, const GLint *params);

static glPointParameteriNVPROC glPointParameteriNV;
static glPointParameterivNVPROC glPointParameterivNV;

void extgl_InitNVPointSprite(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_point_sprite)
		return;
	glPointParameteriNV = (glPointParameteriNVPROC) extgl_GetProcAddress("glPointParameteriNV");
	glPointParameterivNV = (glPointParameterivNVPROC) extgl_GetProcAddress("glPointParameterivNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_point_sprite)
}

/*
 * Class:	org.lwjgl.opengl.nv.NVPointSprite
 * Method:	glPointParameteriNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVPointSprite_glPointParameteriNV
	(JNIEnv * env, jclass clazz, jint pname, jint param)
{
	CHECK_EXISTS(glPointParameteriNV)
	glPointParameteriNV(pname, param);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVPointSprite
 * Method:	nglPointParameterivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVPointSprite_nglPointParameterivNV
	(JNIEnv * env, jclass clazz, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glPointParameterivNV)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glPointParameterivNV(pname, piParams_ptr);
	CHECK_GL_ERROR
}
