/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 
/**
 * $Id$
 *
 * Base linux functionality for GL.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include "extgl.h"
#include "org_lwjgl_opengl_BaseGL.h"

GLXContext context = NULL; // OpenGL rendering context
extern XVisualInfo * vis_info;
extern Window win;
extern Display * disp;

void makeCurrent(void) {
	glXMakeCurrent(disp, win, context);
}

void releaseContext(void) {
	glXMakeCurrent(disp, None, NULL);
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nCreate
 * Signature: (IIII)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_BaseGL_nCreate
  (JNIEnv * env, jobject obj, jint colorBits, jint alphaBits, jint depthBits, jint stencilBits)
{

	if (!vis_info) {
#ifdef _DEBUG
		printf("No visual info\n");
#endif
		return JNI_FALSE;
	}
	context = glXCreateContext(disp, vis_info, NULL, True);
	if (!context) {
#ifdef _DEBUG
		printf("Could not create context\n");
#endif
		return JNI_FALSE;
	}
	
	makeCurrent();
	if (extgl_Initialize() != 0) {
#ifdef _DEBUG
		printf("Could not init gl function pointers\n");
#endif
		return JNI_FALSE;
	}
#ifdef _DEBUG
	const GLubyte * extensions = glGetString(GL_EXTENSIONS);
	printf("Supported extensions: %s\n", extensions);
#endif
	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nDestroy
  (JNIEnv * env, jobject obj)
{
	releaseContext();
	// Delete the rendering context
	if (context != NULL)
		glXDestroyContext(disp, context); 
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_swapBuffers(JNIEnv * env, jobject obj)
{
	glXSwapBuffers(disp, win);
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nMakeCurrent
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nMakeCurrent
  (JNIEnv * env, jobject obj)
{
	makeCurrent();
}

/*
 *  * Class:     org_lwjgl_opengl_BaseGL
 *   * Method:    nFreeContext
 *    * Signature: ()V
 *     */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nReleaseContext
  (JNIEnv *, jobject)
{
	releaseContext();
}
