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
#include "Window.h"
#include "org_lwjgl_opengl_BaseGL.h"

static GLXContext context = NULL; // OpenGL rendering context

static void makeCurrent(void) {
	glXMakeCurrent(getCurrentDisplay(), getCurrentWindow(), context);
}

static void releaseContext(void) {
	glXMakeCurrent(getCurrentDisplay(), None, NULL);
}

static XVisualInfo *chooseVisual(Display *disp, int screen, int bpp, int depth, int alpha, int stencil) {
	int bpe;
	switch (bpp) {
		case 32:
		case 24:
			bpe = 8;
			break;
		case 16:
			bpe = 4;
			break;
		default:
			return JNI_FALSE;
	}

	int attriblist[] = { GLX_RGBA,
		GLX_DOUBLEBUFFER,
		GLX_DEPTH_SIZE, depth,
		GLX_RED_SIZE, bpe,
		GLX_GREEN_SIZE, bpe,
		GLX_BLUE_SIZE, bpe,
		GLX_ALPHA_SIZE, alpha,
		GLX_STENCIL_SIZE, stencil,
		None };
	return glXChooseVisual(disp, screen, attriblist);
}

static void dumpVisualInfo(Display *disp, XVisualInfo *vis_info) {
	int alpha, depth, stencil, r, g, b;
	glXGetConfig(disp, vis_info, GLX_RED_SIZE, &r);
	glXGetConfig(disp, vis_info, GLX_GREEN_SIZE, &g);
	glXGetConfig(disp, vis_info, GLX_BLUE_SIZE, &b);
	glXGetConfig(disp, vis_info, GLX_ALPHA_SIZE, &alpha);
	glXGetConfig(disp, vis_info, GLX_DEPTH_SIZE, &depth);
	glXGetConfig(disp, vis_info, GLX_STENCIL_SIZE, &stencil);
	printf("Pixel format chosen sizes: r = %d, g = %d, b = %d, a = %d, depth = %d, stencil = %d\n", r, g, b, alpha, depth, stencil);
}

static void destroy(void) {
	releaseContext();
	glXDestroyContext(getCurrentDisplay(), context); 
	context = NULL;
	Display *disp = getCurrentDisplay();
	destroyWindow();
	XCloseDisplay(disp);
	extgl_Close();
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nCreate
 * Signature: (IIII)Z
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nCreate
  (JNIEnv * env, jobject obj, jstring title, jint x, jint y, jint width, jint height, jint bpp, jint alpha, jint depth, jint stencil, jboolean fullscreen)
{
	int screen;
	Display *disp;
	XVisualInfo *vis_info;
	bool fscreen = false;
	if (fullscreen == JNI_TRUE)
		fscreen = true;

	if (extgl_Open() != 0) {
		throwException(env, "Could not load gl libs");
		return;
	}
	disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		XCloseDisplay(disp);
		throwException(env, "Could not open X display");
		return;
	}
	screen = XDefaultScreen(disp);
	if (extgl_InitGLX(disp, screen) != 0) {
		XCloseDisplay(disp);
		extgl_Close();
		throwException(env, "Could not init GLX");
		return;
	}
	vis_info = chooseVisual(disp, screen, bpp, depth, alpha, stencil);
	if (vis_info == NULL) {
		XCloseDisplay(disp);
		extgl_Close();
		throwException(env, "Could not find a matching pixel format");
		return;
	}
#ifdef _DEBUG
	dumpVisualInfo(disp, vis_info);
#endif
	context = glXCreateContext(disp, vis_info, NULL, True);
	if (context == NULL) {
		XFree(vis_info);
		XCloseDisplay(disp);
		extgl_Close();
		throwException(env, "Could not create a GLX context");
		return;
	}
	createWindow(env, disp, screen, vis_info, title, x, y, width, height, fscreen);
	XFree(vis_info);
	makeCurrent();
	if (extgl_Initialize() != 0) {
		destroy();
		throwException(env, "Could not init gl function pointers");
		return;
	}
#ifdef _DEBUG
	const GLubyte * extensions = glGetString(GL_EXTENSIONS);
	printf("Supported extensions: %s\n", extensions);
#endif
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nDestroyGL
  (JNIEnv * env, jobject obj)
{
	destroy();
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_swapBuffers(JNIEnv * env, jobject obj)
{
	glXSwapBuffers(getCurrentDisplay(), getCurrentWindow());
}
