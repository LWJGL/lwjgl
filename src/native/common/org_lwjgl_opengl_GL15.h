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
// MACHINE GENERATED HEADER OF CLASS: org.lwjgl.opengl.GL15
// ----------------------------------

#include <jni.h>

#ifndef _Included_org_lwjgl_opengl_GL15
#define _Included_org_lwjgl_opengl_GL15

#ifdef __cplusplus
extern "C" {
#endif

// ----------------------------------------------------------------------
// ---------------------- ARB_vertex_buffer_object ----------------------
// ----------------------------------------------------------------------

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglBindBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglBindBuffer
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglDeleteBuffers
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglDeleteBuffers
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGenBuffers
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGenBuffers
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glIsBuffer
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL15_glIsBuffer
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglBufferData
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglBufferData
	(JNIEnv *, jclass, jint, jint, jobject, jint, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglBufferSubData
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglBufferSubData
	(JNIEnv *, jclass, jint, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGetBufferSubData
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetBufferSubData
	(JNIEnv *, jclass, jint, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glMapBuffer
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL15_glMapBuffer
	(JNIEnv *, jclass, jint, jint, jint, jobject);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glUnmapBuffer
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL15_glUnmapBuffer
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	nglGetBufferParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetBufferParameteriv
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.GL15
 * Method:	glGetBufferPointer
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL15_glGetBufferPointer
	(JNIEnv *, jclass, jint, jint, jint);

// -----------------------------------------------------------------
// ---------------------- ARB_occlusion_query ----------------------
// -----------------------------------------------------------------

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    nglGenQueries
 * Signature: (ILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGenQueries
  (JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    nglDeleteQueries
 * Signature: (ILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglDeleteQueries
  (JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    glIsQuery
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL15_glIsQuery
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    glBeginQuery
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_glBeginQuery
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    glEndQuery
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_glEndQuery
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    nglGetQueryiv
 * Signature: (IILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryiv
  (JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    nglGetQueryObjectiv
 * Signature: (IILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryObjectiv
  (JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:     org_lwjgl_opengl_GL15
 * Method:    nglGetQueryObjectuiv
 * Signature: (IILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL15_nglGetQueryObjectuiv
  (JNIEnv *, jclass, jint, jint, jobject, jint);

#ifdef __cplusplus
}
#endif

#endif
