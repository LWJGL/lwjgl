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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVPixelDataRange
// ----------------------------------

#include "org_lwjgl_opengl_NVPixelDataRange.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glPixelDataRangeNVPROC) (GLenum target, GLsizei length, GLvoid *pointer);
typedef void (APIENTRY * glFlushPixelDataRangeNVPROC) (GLenum target);

static glPixelDataRangeNVPROC glPixelDataRangeNV;
static glFlushPixelDataRangeNVPROC glFlushPixelDataRangeNV;

void extgl_InitNVPixelDataRange(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_pixel_data_range)
		return;

	glPixelDataRangeNV = (glPixelDataRangeNVPROC) extgl_GetProcAddress("glPixelDataRangeNV");
	glFlushPixelDataRangeNV = (glFlushPixelDataRangeNVPROC) extgl_GetProcAddress("glFlushPixelDataRangeNV");

	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_pixel_data_range)
}

/*
 * Class:	org.lwjgl.opengl.NVPixelDataRange
 * Method:	nglPixelDataRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPixelDataRange_nglPixelDataRangeNV
	(JNIEnv * env, jclass clazz, jint target, jint length, jobject data, jint dataOffset)
{
	CHECK_EXISTS(glPixelDataRangeNV)
	GLvoid *data_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(data) + dataOffset);
	glPixelDataRangeNV(target, length, data_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVPixelDataRange
 * Method:	glFlushPixelDataRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPixelDataRange_glFlushPixelDataRangeNV
	(JNIEnv * env, jclass clazz, jint target)
{
	CHECK_EXISTS(glFlushPixelDataRangeNV)
	glFlushPixelDataRangeNV(target);
	CHECK_GL_ERROR
}
