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
 * Linux specific library for display handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */


#include "extgl.h"
#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/extensions/xf86vmode.h>
#include <X11/Xutil.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <jni.h>
#include "org_lwjgl_Display.h"

Display * disp;
int screen;
Window win;

static jfieldID fid_close;
static bool current_fullscreen;
static bool current_minimized;
static bool input_released;
static int win_width;
static int win_height;
static XF86VidModeModeInfo **avail_modes;
static XVisualInfo * vis_info;
static Atom delete_atom;
static bool gl_loaded = false;
static JNIEnv *saved_env;
static jclass saved_clazz;

extern void handlePointerMotion(XMotionEvent *);
extern void handleButtonPress(XButtonEvent *);
extern void handleButtonRelease(XButtonEvent *);
extern void handleKeyEvent(XKeyEvent *);
extern void releaseKeyboard(void);
extern void releasePointer(void);
extern void acquireKeyboard(void);
extern void acquirePointer(void);

struct pixelformat {
	int bpp;
	int depth;
	int alpha;
	int stencil;
};

int fillFormat(struct pixelformat *formats, int index, int bpp, int depth, int alpha, int stencil) {
	for (int i = 0; i < index; i++)
		if (formats[i].bpp == bpp &&
		    formats[i].depth == depth &&
		    formats[i].alpha == alpha &&
		    formats[i].stencil == stencil)
			return 0;
	formats[index].bpp = bpp;
	formats[index].depth = depth;
	formats[index].stencil = stencil;
	formats[index].alpha = alpha;
	return 1;
}

struct pixelformat *getGLXAvailablePixelFormats(Display *disp, int screen, int *length) {
	if (extgl_Extensions.glx.GLX13 == 1) {
		int num_formats;
		int attriblist[] = {GLX_DOUBLEBUFFER, True,
				   GLX_STEREO, False,
				   GLX_RENDER_TYPE, GLX_RGBA_BIT,
				   GLX_DRAWABLE_TYPE, GLX_WINDOW_BIT,
				   GLX_CONFIG_CAVEAT, GLX_NONE,
				   None};
		GLXFBConfig *configs = glXChooseFBConfig(disp, screen, attriblist, &num_formats);
		struct pixelformat *formats = (struct pixelformat *)malloc(num_formats*sizeof(struct pixelformat));
		*length = 0;
		for (int i = 0; i < num_formats; i++) {
			int bpp, depth, alpha, stencil;
			int val;
			if (glXGetFBConfigAttrib(disp, configs[i], GLX_RED_SIZE, &val) != 0) {
				free(formats);
				return NULL;
			}
			bpp = val;
			if (glXGetFBConfigAttrib(disp, configs[i], GLX_GREEN_SIZE, &val) != 0) {
				free(formats);
				return NULL;
			}
			bpp += val;
			if (glXGetFBConfigAttrib(disp, configs[i], GLX_BLUE_SIZE, &val) != 0) {
				free(formats);
				return NULL;
			}
			bpp += val;
			if (glXGetFBConfigAttrib(disp, configs[i], GLX_ALPHA_SIZE, &alpha) != 0) {
				free(formats);
				return NULL;
			}
			if (glXGetFBConfigAttrib(disp, configs[i], GLX_DEPTH_SIZE, &depth) != 0) {
				free(formats);
				return NULL;
			}
			if (glXGetFBConfigAttrib(disp, configs[i], GLX_STENCIL_SIZE, &stencil) != 0) {
				free(formats);
				return NULL;
			}
			if (fillFormat(formats, *length, bpp, depth, alpha, stencil) == 1)
				(*length)++;
		}
		return formats;
	}
	return NULL;
}

XVisualInfo *chooseVisual(Display *disp, int screen, int bpp, int depth, int alpha, int stencil) {
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

struct pixelformat *getAvailablePixelFormats(Display *disp, int screen, int *length) {
	struct pixelformat *formats = getGLXAvailablePixelFormats(disp, screen, length);
	if (formats != NULL)
		return formats;
	*length = 16;
       	formats = (struct pixelformat *)malloc((*length)*sizeof(struct pixelformat));
	*length = 0;
	for (int bpp = 16; bpp <= 24; bpp += 8)
		for (int depth = 16; depth <= 24; depth += 8)
			for (int alpha = 0; alpha <= 8; alpha += 8)
				for (int stencil = 0; stencil <= 8; stencil += 8) {
					XVisualInfo * visual = chooseVisual(disp, screen, bpp, depth, alpha, stencil);
					if (visual != NULL) {
						if (fillFormat(formats, *length, bpp, depth, alpha, stencil) == 1)
							(*length)++;
						XFree(visual);
					}
				}
	return formats;
}

void waitMapped(Display *disp, Window win) {
	XEvent event;

	do {
		XMaskEvent(disp, StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

bool releaseInput(void) {
	if (current_fullscreen)
		return false;
	releaseKeyboard();
	releasePointer();
	input_released = true;
	return true;
}

void handleMessages(void) {
	XEvent event;
	while (XPending(disp) > 0) {
		XNextEvent(disp, &event);
		switch (event.type) {
			case ClientMessage:
				if ((event.xclient.format == 32) && (event.xclient.data.l[0] == delete_atom))
					saved_env->SetStaticBooleanField(saved_clazz, fid_close, JNI_TRUE);
				break;
			case FocusIn:
				if (input_released) {
					acquireKeyboard();
					acquirePointer();
				}
				break;
			case MapNotify:
				current_minimized = false;
				break;
			case UnmapNotify:
				current_minimized = true;
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

bool loadGL(Display *disp, int screen) {
	if (gl_loaded == true)
		return true;
	if (extgl_Open(disp, screen) != 0) {
#ifdef _DEBUG
		printf("Could not load gl libs\n");
#endif
		return false;
	}
	gl_loaded = true;
	return true;
}

void closeGL(void) {
	gl_loaded = false;
	extgl_Close();
}

int getDisplayModes(Display *disp, int screen, int *num_modes, XF86VidModeModeInfo ***avail_modes) {
	int event_base, error_base, xvid_ver, xvid_rev;
	
	if (!XF86VidModeQueryExtension(disp, &event_base, &error_base)) {
#ifdef _DEBUG
		printf("XF86VidMode extension not available\n");
#endif
		return 0;
	}
	XF86VidModeQueryVersion(disp, &xvid_ver, &xvid_rev);
#ifdef _DEBUG
	printf("XF86VidMode extension version %i.%i\n", xvid_ver, xvid_rev);
#endif
	XF86VidModeGetAllModeLines(disp, screen, num_modes, avail_modes);
	return 1;
}

bool isMinimized() {
	handleMessages();
	return current_minimized;
}

int getWindowHeight(void) {
	return win_height;
}

int getWindowWidth(void) {
	return win_width;
}

XVisualInfo *getVisualInfo(void) {
	return vis_info;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    isMinimized
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_isMinimized(JNIEnv *env, jclass clazz) {
	return isMinimized() ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate(JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jint alpha_bits, jint depth_bits, jint stencil_bits, jboolean fullscreen, jstring title) {
	saved_env = env;
	saved_clazz = clazz;
	fid_close = env->GetStaticFieldID(clazz, "closeRequested", "Z");
	
	Window root_win;
	XSetWindowAttributes attribs;
	Colormap cmap;
	int attribmask;
        int num_modes, i;

	win_width = width;
	win_height = height;
	if (fullscreen == JNI_TRUE)
		current_fullscreen = true;
	else
		current_fullscreen = false;
	current_minimized = false;
	input_released = false;
	disp = XOpenDisplay(NULL);
	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		return JNI_FALSE;
	}
	screen = DefaultScreen(disp);
	if (!loadGL(disp, screen)) {
#ifdef _DEBUG
		printf("Could not load GL libs\n");
#endif
		return JNI_FALSE;
	}
	if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
		XCloseDisplay(disp);
#ifdef _DEBUG
		printf("Could not get display modes\n");
#endif
		return JNI_FALSE;
	}
	root_win = RootWindow(disp, screen);
	vis_info = chooseVisual(disp, screen, bpp, depth_bits, alpha_bits, stencil_bits);
        if (vis_info == NULL) {
		XCloseDisplay(disp);
#ifdef _DEBUG
		printf("Could not choose glx visual\n");
#endif
		return JNI_FALSE;
	}

	cmap = XCreateColormap(disp, root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = FocusChangeMask | VisibilityChangeMask| StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (fullscreen) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, root_win, 0, 0, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
#ifdef _DEBUG
	printf("Created window\n");
#endif
	const char * title_str = env->GetStringUTFChars(title, NULL);
	XStoreName(disp, win, title_str);
	env->ReleaseStringUTFChars(title, title_str);
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
	if (fullscreen) {
		for ( i = 0; i < num_modes; ++i ) {
#ifdef _DEBUG
			printf("Mode %d: %dx%d\n", i, avail_modes[i]->hdisplay, avail_modes[i]->vdisplay);
#endif
			if (avail_modes[i]->hdisplay == width && avail_modes[i]->vdisplay == height) {
				if (!XF86VidModeSwitchToMode(disp, screen, avail_modes[i])) {
					XFree(vis_info);
					XFree(avail_modes);
					XDestroyWindow(disp, win);
					XCloseDisplay(disp);
#ifdef _DEBUG
					printf("Could not switch mode\n");
#endif
					return JNI_FALSE;
				}
			}
		}
		XF86VidModeSetViewPort(disp, screen, 0, 0);
	}
	XClearWindow(disp, win);
	XSync(disp, True);
	return JNI_TRUE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_Display_nDestroy(JNIEnv * env, jclass clazz) {
	XDestroyWindow(disp, win);
	if (current_fullscreen) {
		if (!XF86VidModeSwitchToMode(disp, screen, avail_modes[0])) {
#ifdef _DEBUG
			printf("Could not switch mode\n");
#endif
		}
	}
	XFree(avail_modes);
	XFree(vis_info);
	XCloseDisplay(disp);
	closeGL();
#ifdef _DEBUG
	printf("Closed X connection\n");
#endif
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_getAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{
	int num_modes, i;

	Display *disp = XOpenDisplay(NULL);
	int screen = DefaultScreen(disp);
	XF86VidModeModeInfo **avail_modes;

	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		XCloseDisplay(disp);
		return NULL;
	}
	if (!loadGL(disp, screen)) {
#ifdef _DEBUG
		printf("Could not load GL\n");
#endif
		XCloseDisplay(disp);
		return NULL;
	}
	if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
#ifdef _DEBUG
		printf("Could not get display modes\n");
#endif
		XCloseDisplay(disp);
		return NULL;
	}
	int num_pixelformats;
	struct pixelformat *formats = getAvailablePixelFormats(disp, screen, &num_pixelformats);
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");
	jobjectArray ret = env->NewObjectArray(num_modes*num_pixelformats, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIIIIII)V");
	
	for (i = 0; i < num_modes; i++) {
		for (int j = 0; j < num_pixelformats; j++) {
			jobject displayMode = env->NewObject(displayModeClass, displayModeConstructor, avail_modes[i]->hdisplay, avail_modes[i]->vdisplay, formats[j].bpp, 0, formats[j].alpha, formats[j].depth, formats[j].stencil);
			env->SetObjectArrayElement(ret, i*num_pixelformats + j, displayMode);
		}
	}
	free(formats);
	XFree(avail_modes);
	XCloseDisplay(disp);
	return ret;
}

