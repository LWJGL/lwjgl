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

static Display *display_connection = NULL;
static int display_connection_usage = 0;

Display *getDisplay(void) {
	return display_connection;
}

Display *incDisplay(JNIEnv *env) {
	if (display_connection_usage == 0) {
		display_connection = XOpenDisplay(NULL);
		if (display_connection == NULL) {
			throwException(env, "Could not open X display");
			return NULL;
		}
	}
	display_connection_usage++;
	return display_connection;
}

void decDisplay(void) {
	display_connection_usage--;
	if (display_connection_usage == 0)
		XCloseDisplay(display_connection);
}

static void waitMapped(Window win) {
	XEvent event;

	do {
		XMaskEvent(getDisplay(), StructureNotifyMask, &event);
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
	while (XPending(getDisplay()) > 0) {
		XNextEvent(getDisplay(), &event);
		switch (event.type) {
			case ClientMessage:
				if ((event.xclient.format == 32) && ((Atom)event.xclient.data.l[0] == delete_atom))
					closerequested = true;
				break;
			case FocusOut:
				XGetInputFocus(getDisplay(), &win, &revert_mode);
				if (win != current_win) {
					releaseInput();
					focused = false;
				}
				break;
			case FocusIn:
				XGetInputFocus(getDisplay(), &win, &revert_mode);
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
	XStoreName(getDisplay(), current_win, title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetTitle
  (JNIEnv * env, jclass clazz, jstring title_obj)
{
	const char * title = env->GetStringUTFChars(title_obj, NULL);
	setWindowTitle(title);
	env->ReleaseStringUTFChars(title_obj, title);
}

static void createWindow(JNIEnv* env, int screen, XVisualInfo *vis_info, jstring title, int x, int y, int width, int height, bool fullscreen, bool undecorated) {
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

	current_screen = screen;
	input_released = false;
	current_fullscreen = fullscreen;
	current_width = width;
	current_height = height;

	root_win = RootWindow(getDisplay(), screen);
	cmap = XCreateColormap(getDisplay(), root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = ExposureMask | FocusChangeMask | VisibilityChangeMask| StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (fullscreen || undecorated) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(getDisplay(), root_win, x, y, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
	XFreeColormap(getDisplay(), cmap);
	printfDebug("Created window\n");
	current_win = win;
	Java_org_lwjgl_opengl_Window_nSetTitle(env, NULL, title);
	XSizeHints * size_hints = XAllocSizeHints();
	size_hints->flags = PMinSize | PMaxSize;
	size_hints->min_width = width;
	size_hints->max_width = width;
	size_hints->min_height = height;
	size_hints->max_height = height;
	XSetWMNormalHints(getDisplay(), win, size_hints);
	XFree(size_hints);
	delete_atom = XInternAtom(getDisplay(), "WM_DELETE_WINDOW", False);
	XSetWMProtocols(getDisplay(), win, &delete_atom, 1);
	XMapRaised(getDisplay(), win);
	waitMapped(win);
	XClearWindow(getDisplay(), win);
	XSync(getDisplay(), True);
}

static void destroyWindow() {
	XDestroyWindow(getDisplay(), current_win);
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
		glXMakeContextCurrent(getDisplay(), glx_window, glx_window, context);
	else
		glXMakeCurrent(getDisplay(), getCurrentWindow(), context);
}

static void releaseContext(void) {
	if (USEGLX13)
		glXMakeContextCurrent(getDisplay(), None, None, NULL);
	else
		glXMakeCurrent(getDisplay(), None, NULL);
}

int convertToBPE(int bpp) {
	int bpe;
	switch (bpp) {
		case 32:
		case 24:
			bpe = 8;
			break;
		case 16: /* Fall through */
		default:
			bpe = 4;
			break;
	}
	return bpe;
}

GLXContext getCurrentContext(void) {
	return context;
}

static GLXFBConfig *chooseVisualGLX13(int screen, int bpp, int depth, int alpha, int stencil, int samples) {
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
	GLXFBConfig* configs = glXChooseFBConfig(getDisplay(), screen, attriblist, &num_formats);
	if (num_formats > 0)
		return configs;
	else {
		if (configs != NULL)
			XFree(configs);
		return NULL;
	}
}

static XVisualInfo *chooseVisual(int screen, int bpp, int depth, int alpha, int stencil, int samples) {
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
	return glXChooseVisual(getDisplay(), screen, attriblist);
}

static void dumpVisualInfo(XVisualInfo *vis_info) {
	int alpha, depth, stencil, r, g, b;
	int sample_buffers = 0;
	int samples = 0;
	glXGetConfig(getDisplay(), vis_info, GLX_RED_SIZE, &r);
	glXGetConfig(getDisplay(), vis_info, GLX_GREEN_SIZE, &g);
	glXGetConfig(getDisplay(), vis_info, GLX_BLUE_SIZE, &b);
	glXGetConfig(getDisplay(), vis_info, GLX_ALPHA_SIZE, &alpha);
	glXGetConfig(getDisplay(), vis_info, GLX_DEPTH_SIZE, &depth);
	glXGetConfig(getDisplay(), vis_info, GLX_STENCIL_SIZE, &stencil);
	if (extgl_Extensions.GLX_ARB_multisample) {
		glXGetConfig(getDisplay(), vis_info, GLX_SAMPLE_BUFFERS_ARB, &sample_buffers);
		glXGetConfig(getDisplay(), vis_info, GLX_SAMPLES_ARB, &samples);
	}
	printf("Pixel format info: r = %d, g = %d, b = %d, a = %d, depth = %d, stencil = %d, sample buffers = %d, samples = %d\n", r, g, b, alpha, depth, stencil, sample_buffers, samples);
}

static void destroy(void) {
	releaseContext();
	if (USEGLX13)
		glXDestroyWindow(getDisplay(), glx_window);
	glXDestroyContext(getDisplay(), context);
	context = NULL;
	destroyWindow();
	decDisplay();
	extgl_Close();
}

static bool initWindowGLX13(JNIEnv *env, int screen, jstring title, int x, int y, int width, int height, int bpp, int depth, int alpha, int stencil, int samples, bool fscreen, bool undecorated) {
	GLXFBConfig *configs = chooseVisualGLX13(screen, bpp, depth, alpha, stencil, samples);
	if (configs == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	context = glXCreateNewContext(getDisplay(), configs[0], GLX_RGBA_TYPE, NULL, True);
	if (context == NULL) {
		XFree(configs);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
	if (!allow_software_acceleration && (glXIsDirect(getDisplay(), context) == False)) {
		glXDestroyContext(getDisplay(), context);
		XFree(configs);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	XVisualInfo * vis_info = glXGetVisualFromFBConfig(getDisplay(), configs[0]);
	if (vis_info == NULL) {
		glXDestroyContext(getDisplay(), context);
		XFree(configs);
		throwException(env, "Could not create visual info from FB config");
		return false;
	}
	createWindow(env, screen, vis_info, title, x, y, width, height, fscreen, undecorated);
	glx_window = glXCreateWindow(getDisplay(), configs[0], getCurrentWindow(), NULL);
	makeCurrent();
	if (isDebugEnabled())
		dumpVisualInfo(vis_info);
	XFree(configs);
	XFree(vis_info);
	return true;
}

static bool initWindowGLX(JNIEnv *env, int screen, jstring title, int x, int y, int width, int height, int bpp, int depth, int alpha, int stencil, int samples, bool fscreen, bool undecorated) {
	XVisualInfo *vis_info = chooseVisual(screen, bpp, depth, alpha, stencil, samples);
	if (vis_info == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	if (isDebugEnabled())
		dumpVisualInfo(vis_info);
	context = glXCreateContext(getDisplay(), vis_info, NULL, True);
	if (context == NULL) {
		XFree(vis_info);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
	if (!allow_software_acceleration && glXIsDirect(getDisplay(), context) == False) {
		glXDestroyContext(getDisplay(), context);
		XFree(vis_info);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	createWindow(env, screen, vis_info, title, x, y, width, height, fscreen, undecorated);
	makeCurrent();
	XFree(vis_info);
	return true;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate
  (JNIEnv * env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil, jint samples)
{
	int screen;
	bool fscreen = false;
	if (fullscreen == JNI_TRUE)
		fscreen = true;
	bool isUndecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");

	if (!extgl_Open()) {
		throwException(env, "Could not load gl libs");
		return;
	}
	Display *disp = incDisplay(env);
	if (disp == NULL)
		return;
	screen = XDefaultScreen(disp);
	if (!extgl_InitGLX(env, disp, screen)) {
		decDisplay();
		extgl_Close();
		throwException(env, "Could not init GLX");
		return;
	}
	bool create_success;
	if (USEGLX13) {
		create_success = initWindowGLX13(env, screen, title, x, y, width, height, bpp, depth, alpha, stencil, samples, fscreen, isUndecorated);
	} else {
		create_success = initWindowGLX(env, screen, title, x, y, width, height, bpp, depth, alpha, stencil, samples, fscreen, isUndecorated);
	}
	if (!create_success) {
		decDisplay();
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
		glXSwapBuffers(getDisplay(), glx_window);
	else
		glXSwapBuffers(getDisplay(), getCurrentWindow());
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
	return result ? JNI_TRUE : JNI_FALSE;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsVisible
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsVisible
  (JNIEnv *env, jclass clazz) {
	return minimized ? JNI_FALSE : JNI_TRUE;
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
 * Method:    nIsActive
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsActive
  (JNIEnv *env, jclass clazz) {
	return focused ? JNI_TRUE : JNI_FALSE;
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

