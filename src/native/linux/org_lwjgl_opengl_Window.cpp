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
 * Linux specific window functions.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */


#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <jni.h>
#include "common_tools.h"
#include "extgl.h"
#include "extgl_glx.h"
#include "Window.h"
#include "org_lwjgl_opengl_Window.h"

#define USEGLX13 extgl_Extensions.GLX13

static GLXContext context = NULL; // OpenGL rendering context
static GLXWindow glx_window;

static Atom delete_atom;
static Display *current_disp;
static Window current_win;
static int current_screen;
static bool current_fullscreen;
static int current_height;
static int current_width;

static bool input_released;

static bool dirty;
static bool vsync_enabled;
static bool minimized;
static bool focused;
static bool closerequested;

static void waitMapped(Display *disp, Window win) {
	XEvent event;

	do {
		XMaskEvent(disp, StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

static void acquireInput(void) {
	if (input_released) {
		acquireKeyboard();
		acquirePointer();
		input_released = false;
	}
}

static void doReleaseInput(void) {
	releaseKeyboard();
	releasePointer();
	input_released = true;
}

void updateInput(void) {
	if (!input_released) {
		doReleaseInput();
		acquireInput();
	}
}

bool releaseInput(void) {
	if (current_fullscreen || input_released)
		return false;
	doReleaseInput();
	return true;
}

bool isFullscreen(void) {
	return current_fullscreen;
}

static void handleMessages() {
	XEvent event;
	Window win;
	int revert_mode;
	while (XPending(current_disp) > 0) {
		XNextEvent(current_disp, &event);
		switch (event.type) {
			case ClientMessage:
				if ((event.xclient.format == 32) && ((Atom)event.xclient.data.l[0] == delete_atom))
					closerequested = true;
				break;
			case FocusOut:
				XGetInputFocus(current_disp, &win, &revert_mode);
				if (win != current_win) {
					releaseInput();
					focused = false;
				}
				break;
			case FocusIn:
				XGetInputFocus(current_disp, &win, &revert_mode);
				if (win == current_win) {
					acquireInput();
					focused = true;
				}
				break;
			case MapNotify:
				dirty = true;
				minimized = false;
				break;
			case UnmapNotify:
				minimized = true;
				break;
			case Expose:
//				XSetInputFocus(current_disp, current_win, RevertToParent, CurrentTime);
				dirty = true;
				break;
			case ButtonPress:
				handleButtonPress(&(event.xbutton));
				break;
			case ButtonRelease:
				handleButtonRelease(&(event.xbutton));
				break;
			case MotionNotify:
				handlePointerMotion(&(event.xmotion));
				break;
			case KeyPress:
			case KeyRelease:
				handleKeyEvent(&(event.xkey));
				break;
		}
	}
}

static void setWindowTitle(const char *title) {
	XStoreName(current_disp, current_win, title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetTitle
  (JNIEnv * env, jclass clazz, jstring title_obj)
{
	const char * title = env->GetStringUTFChars(title_obj, NULL);
	setWindowTitle(title);
	env->ReleaseStringUTFChars(title_obj, title);
}

static void createWindow(JNIEnv* env, Display *disp, int screen, XVisualInfo *vis_info, jstring title, int x, int y, int width, int height, bool fullscreen) {
	dirty = true;
	focused = true;
	minimized = false;
	closerequested = false;
	vsync_enabled = false;
	Window root_win;
	Window win;
	XSetWindowAttributes attribs;
	Colormap cmap;
	int attribmask;

	current_disp = disp;
	current_screen = screen;
	input_released = false;
	current_fullscreen = fullscreen;
	current_width = width;
	current_height = height;

	root_win = RootWindow(disp, screen);
	cmap = XCreateColormap(disp, root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = ExposureMask | FocusChangeMask | VisibilityChangeMask| StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (fullscreen) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, root_win, x, y, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
	XFreeColormap(disp, cmap);
	printfDebug("Created window\n");
	current_win = win;
	Java_org_lwjgl_opengl_Window_nSetTitle(env, NULL, title);
	XSizeHints * size_hints = XAllocSizeHints();
	size_hints->flags = PMinSize | PMaxSize;
	size_hints->min_width = width;
	size_hints->max_width = width;
	size_hints->min_height = height;
	size_hints->max_height = height;
	XSetWMNormalHints(disp, win, size_hints);
	XFree(size_hints);
	delete_atom = XInternAtom(disp, "WM_DELETE_WINDOW", False);
	XSetWMProtocols(disp, win, &delete_atom, 1);
	XMapRaised(disp, win);
	waitMapped(disp, win);
	XClearWindow(disp, win);
//	XSetInputFocus(current_disp, current_win, RevertToParent, CurrentTime);
	XSync(disp, True);
}

static void destroyWindow() {
	XDestroyWindow(current_disp, current_win);
	current_disp = NULL;
}

Display *getCurrentDisplay(void) {
	return current_disp;
}

int getCurrentScreen(void) {
	return current_screen;
}

Window getCurrentWindow(void) {
	return current_win;
}

int getWindowWidth(void) {
	return current_width;
}

int getWindowHeight(void) {
	return current_height;
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nUpdate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nUpdate
  (JNIEnv *env, jclass clazz)
{
	handleMessages();
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nMakeCurrent
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nMakeCurrent
  (JNIEnv *env, jclass clazz)
{
	makeCurrent();
}

void makeCurrent(void) {
	if (USEGLX13)
		glXMakeContextCurrent(getCurrentDisplay(), glx_window, glx_window, context);
	else
		glXMakeCurrent(getCurrentDisplay(), getCurrentWindow(), context);
}

static void releaseContext(void) {
	if (USEGLX13)
		glXMakeContextCurrent(getCurrentDisplay(), None, None, NULL);
	else
		glXMakeCurrent(getCurrentDisplay(), None, NULL);
}

int convertToBPE(int bpp) {
	int bpe = 4;
	switch (bpp) {
		case 32:
		case 24:
			bpe = 8;
			break;
		case 16: /* Fall through */
		default:
			break;
	}
	return bpe;
}

GLXContext getCurrentContext(void) {
	return context;
}

static GLXFBConfig *chooseVisualGLX13(Display *disp, int screen, int bpp, int depth, int alpha, int stencil, int samples) {
	int bpe = convertToBPE(bpp);
	int attriblist[] = {GLX_RENDER_TYPE, GLX_RGBA_BIT,
			    GLX_DOUBLEBUFFER, True,
			    GLX_DRAWABLE_TYPE, GLX_WINDOW_BIT,
			    GLX_DEPTH_SIZE, depth,
			    GLX_RED_SIZE, bpe,
			    GLX_GREEN_SIZE, bpe,
			    GLX_BLUE_SIZE, bpe,
			    GLX_ALPHA_SIZE, alpha,
			    GLX_STENCIL_SIZE, stencil,
			    None, None, /* For ARB_multisample */
			    None, None, /*                     */
			    None};
	int num_formats = 0;
	if (samples > 0 && extgl_Extensions.GLX_ARB_multisample) {
		attriblist[18] = GLX_SAMPLE_BUFFERS_ARB;
		attriblist[19] = 1;
		attriblist[20] = GLX_SAMPLES_ARB;
		attriblist[21] = samples;
	}
	GLXFBConfig* configs = glXChooseFBConfig(disp, screen, attriblist, &num_formats);
	if (num_formats > 0)
		return configs;
	else {
		if (configs != NULL)
			XFree(configs);
		return NULL;
	}
}

static XVisualInfo *chooseVisual(Display *disp, int screen, int bpp, int depth, int alpha, int stencil, int samples) {
	int bpe = convertToBPE(bpp);
	int attriblist[] = {GLX_RGBA,
			    GLX_DOUBLEBUFFER,
			    GLX_DEPTH_SIZE, depth,
			    GLX_RED_SIZE, bpe,
			    GLX_GREEN_SIZE, bpe,
			    GLX_BLUE_SIZE, bpe,
			    GLX_ALPHA_SIZE, alpha,
			    GLX_STENCIL_SIZE, stencil,
			    None, None, /* For ARB_multisample */
			    None, None, /*                     */
			    None};
	if (samples > 0 && extgl_Extensions.GLX_ARB_multisample) {
		attriblist[14] = GLX_SAMPLE_BUFFERS_ARB;
		attriblist[15] = 1;
		attriblist[16] = GLX_SAMPLES_ARB;
		attriblist[17] = samples;
	}
	return glXChooseVisual(disp, screen, attriblist);
}

static void dumpVisualInfo(Display *disp, XVisualInfo *vis_info) {
	int alpha, depth, stencil, r, g, b;
	int sample_buffers = 0;
	int samples = 0;
	glXGetConfig(disp, vis_info, GLX_RED_SIZE, &r);
	glXGetConfig(disp, vis_info, GLX_GREEN_SIZE, &g);
	glXGetConfig(disp, vis_info, GLX_BLUE_SIZE, &b);
	glXGetConfig(disp, vis_info, GLX_ALPHA_SIZE, &alpha);
	glXGetConfig(disp, vis_info, GLX_DEPTH_SIZE, &depth);
	glXGetConfig(disp, vis_info, GLX_STENCIL_SIZE, &stencil);
	if (extgl_Extensions.GLX_ARB_multisample) {
		glXGetConfig(disp, vis_info, GLX_SAMPLE_BUFFERS_ARB, &sample_buffers);
		glXGetConfig(disp, vis_info, GLX_SAMPLES_ARB, &samples);
	}
	printf("Pixel format info: r = %d, g = %d, b = %d, a = %d, depth = %d, stencil = %d, sample buffers = %d, samples = %d\n", r, g, b, alpha, depth, stencil, sample_buffers, samples);
}

static void destroy(void) {
	releaseContext();
	if (USEGLX13)
		glXDestroyWindow(getCurrentDisplay(), glx_window);
	glXDestroyContext(getCurrentDisplay(), context);
	context = NULL;
	Display *disp = getCurrentDisplay();
	destroyWindow();
	XCloseDisplay(disp);
	extgl_Close();
}

static bool initWindowGLX13(JNIEnv *env, Display *disp, int screen, jstring title, int x, int y, int width, int height, int bpp, int depth, int alpha, int stencil, int samples, bool fscreen) {
	GLXFBConfig *configs = chooseVisualGLX13(disp, screen, bpp, depth, alpha, stencil, samples);
	if (configs == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	context = glXCreateNewContext(disp, configs[0], GLX_RGBA_TYPE, NULL, True);
	if (context == NULL) {
		XFree(configs);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
	if (!allow_software_acceleration && (glXIsDirect(disp, context) == False)) {
		glXDestroyContext(disp, context);
		XFree(configs);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	XVisualInfo * vis_info = glXGetVisualFromFBConfig(disp, configs[0]);
	if (vis_info == NULL) {
		glXDestroyContext(disp, context);
		XFree(configs);
		throwException(env, "Could not create visual info from FB config");
		return false;
	}
	createWindow(env, disp, screen, vis_info, title, x, y, width, height, fscreen);
	glx_window = glXCreateWindow(disp, configs[0], getCurrentWindow(), NULL);
	makeCurrent();
	if (isDebugEnabled())
		dumpVisualInfo(disp, vis_info);
	XFree(configs);
	XFree(vis_info);
	return true;
}

static bool initWindowGLX(JNIEnv *env, Display *disp, int screen, jstring title, int x, int y, int width, int height, int bpp, int depth, int alpha, int stencil, int samples, bool fscreen) {
	XVisualInfo *vis_info = chooseVisual(disp, screen, bpp, depth, alpha, stencil, samples);
	if (vis_info == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	if (isDebugEnabled())
		dumpVisualInfo(disp, vis_info);
	context = glXCreateContext(disp, vis_info, NULL, True);
	if (context == NULL) {
		XFree(vis_info);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	if (glXIsDirect(disp, context) == False) {
		glXDestroyContext(disp, context);
		XFree(vis_info);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	createWindow(env, disp, screen, vis_info, title, x, y, width, height, fscreen);
	makeCurrent();
	XFree(vis_info);
	return true;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate
  (JNIEnv * env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil, jint samples)
{
	int screen;
	Display *disp;
	bool fscreen = false;
	if (fullscreen == JNI_TRUE)
		fscreen = true;

	if (!extgl_Open()) {
		throwException(env, "Could not load gl libs");
		return;
	}
	disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		throwException(env, "Could not open X display");
		return;
	}
	screen = XDefaultScreen(disp);
	if (!extgl_InitGLX(env, disp, screen)) {
		XCloseDisplay(disp);
		extgl_Close();
		throwException(env, "Could not init GLX");
		return;
	}
	bool create_success;
	if (USEGLX13) {
		create_success = initWindowGLX13(env, disp, screen, title, x, y, width, height, bpp, depth, alpha, stencil, samples, fscreen);
	} else {
		create_success = initWindowGLX(env, disp, screen, title, x, y, width, height, bpp, depth, alpha, stencil, samples, fscreen);
	}
	if (!create_success) {
		XCloseDisplay(disp);
		extgl_Close();
		return;
	}
}

/*
 * Class:     org_lwjgl_opengl_GLWindow
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nDestroy
  (JNIEnv *env, jclass clazz)
{
	destroy();
}

/*
 * Class:     org_lwjgl_opengl_GLWindow
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_swapBuffers(JNIEnv * env, jclass clazz)
{
	dirty = false;
	if (USEGLX13)
		glXSwapBuffers(getCurrentDisplay(), glx_window);
	else
		glXSwapBuffers(getCurrentDisplay(), getCurrentWindow());
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    minimize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_minimize
  (JNIEnv *env, jclass clazz) {
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    restore
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_restore
  (JNIEnv *env, jclass clazz) {
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsDirty
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsDirty
  (JNIEnv *env, jclass clazz) {
	bool result = dirty;
	dirty = false;
	return result;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsMinimized
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsMinimized
  (JNIEnv *env, jclass clazz) {
	return minimized;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsCloseRequested
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsCloseRequested
  (JNIEnv *, jclass) {
	bool saved = closerequested;
	closerequested = false;
	return saved;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsFocused
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsFocused
  (JNIEnv *env, jclass clazz) {
	return focused;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsVSyncEnabled
  (JNIEnv * env, jclass clazz)
{
	return vsync_enabled;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetVSyncEnabled
  (JNIEnv * env, jclass clazz, jboolean sync)
{
	if (extgl_Extensions.GLX_SGI_swap_control) {
		bool vsync = sync == JNI_TRUE ? true : false;
		if (vsync != vsync_enabled) {
			int interval = vsync ? 1 : 0;
			glXSwapIntervalSGI(interval);
			vsync_enabled = vsync;
		}
	}
}
