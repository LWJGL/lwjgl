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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ati.ATIEnvmapBumpmap
// ----------------------------------

#include "org_lwjgl_opengl_ati_ATIEnvmapBumpmap.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.ati.ATIEnvmapBumpmap
 * Method:	nglTexBumpParameterfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIEnvmapBumpmap_nglTexBumpParameterfvATI
	(JNIEnv * env, jclass clazz, jint pname, jobject pfParam, jint pfParam_offset)
{
	CHECK_EXISTS(glTexBumpParameterfvATI)
	GLfloat *pfParam_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParam) + pfParam_offset;
	glTexBumpParameterfvATI(pname, pfParam_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIEnvmapBumpmap
 * Method:	nglTexBumpParameterivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIEnvmapBumpmap_nglTexBumpParameterivATI
	(JNIEnv * env, jclass clazz, jint pname, jobject piParam, jint piParam_offset)
{
	CHECK_EXISTS(glTexBumpParameterivATI)
	GLint *piParam_ptr = (GLint *)env->GetDirectBufferAddress(piParam) + piParam_offset;
	glTexBumpParameterivATI(pname, piParam_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIEnvmapBumpmap
 * Method:	nglGetTexBumpParameterfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIEnvmapBumpmap_nglGetTexBumpParameterfvATI
	(JNIEnv * env, jclass clazz, jint pname, jobject pfParam, jint pfParam_offset)
{
	CHECK_EXISTS(glGetTexBumpParameterfvATI)
	GLfloat *pfParam_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParam) + pfParam_offset;
	glGetTexBumpParameterfvATI(pname, pfParam_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIEnvmapBumpmap
 * Method:	nglGetTexBumpParameterivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIEnvmapBumpmap_nglGetTexBumpParameterivATI
	(JNIEnv * env, jclass clazz, jint pname, jobject piParam, jint piParam_offset)
{
	CHECK_EXISTS(glGetTexBumpParameterivATI)
	GLint *piParam_ptr = (GLint *)env->GetDirectBufferAddress(piParam) + piParam_offset;
	glGetTexBumpParameterivATI(pname, piParam_ptr);
	CHECK_GL_ERROR
}
