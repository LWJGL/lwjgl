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
// MACHINE GENERATED HEADER OF CLASS: org.lwjgl.opengl.nv.NVProgram
// ----------------------------------

#include <jni.h>

#ifndef _Included_org_lwjgl_opengl_nv_NVProgram
#define _Included_org_lwjgl_opengl_nv_NVProgram

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglLoadProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglLoadProgramNV
	(JNIEnv *, jclass, jint, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	glBindProgramNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_glBindProgramNV
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglDeleteProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglDeleteProgramsNV
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglGenProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglGenProgramsNV
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglGetProgramivNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglGetProgramivNV
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglGetProgramStringNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglGetProgramStringNV
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	glIsProgramNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_nv_NVProgram_glIsProgramNV
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglAreProgramsResidentNV
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglAreProgramsResidentNV
	(JNIEnv *, jclass, jint, jobject, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.nv.NVProgram
 * Method:	nglRequestResidentProgramsNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVProgram_nglRequestResidentProgramsNV
	(JNIEnv *, jclass, jint, jobject, jint);

#ifdef __cplusplus
}
#endif

#endif
