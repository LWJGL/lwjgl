#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/extensions/xf86vmode.h>
#include <GL/glx.h>
#include <X11/Xutil.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <stdio.h>
#include <jni.h>
#include "org_lwjgl_Display.h"


Display * disp;
int screen;
Window win;
XF86VidModeModeInfo **avail_modes;
XVisualInfo * vis_info;

void waitMapped(Display *disp, Window win) {
	XEvent event;

	do {
		XMaskEvent(disp, StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

int getDisplayModes(Display *disp, int screen, int *num_modes, XF86VidModeModeInfo ***avail_modes) {
	unsigned int event_base, error_base, xvid_ver, xvid_rev;
	
	if (!XF86VidModeQueryExtension(disp, &event_base, &error_base)) {
#ifdef _DEBUG
		printf("XF86VidMode extention not available\n");
#endif
		return 0;
	}
	XF86VidModeQueryVersion(disp, &xvid_ver, &xvid_rev);
#ifdef _DEBUG
	printf("XF86VidMode extention version %i.%i\n", xvid_ver, xvid_rev);
#endif
	XF86VidModeGetAllModeLines(disp, screen, num_modes, avail_modes);
	return 1;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate(JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jboolean fullscreen) {
	Window root_win;
	XSetWindowAttributes attribs;
	Colormap cmap;
	int attribmask;
	int bpe = bpp/4;
	int attriblist[] = {GLX_RGBA, GLX_DOUBLEBUFFER, GLX_DEPTH_SIZE, 24, GLX_RED_SIZE, bpe, GLX_GREEN_SIZE, bpe, GLX_BLUE_SIZE, bpe, GLX_ALPHA_SIZE, bpe, None};
	int num_modes, i;

	disp = XOpenDisplay(NULL);
	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		return JNI_FALSE;
	}
	screen = DefaultScreen(disp);
	root_win = RootWindow(disp, screen);

	vis_info = glXChooseVisual(disp, screen, attriblist);
	if (vis_info == NULL) {
#ifdef _DEBUG
		printf("Could not choose glx visual\n");
#endif
		return JNI_FALSE;
	}

	cmap = XCreateColormap(disp, root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = ExposureMask | FocusChangeMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribs.event_mask = StructureNotifyMask;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (fullscreen) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, root_win, 0, 0, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
#ifdef _DEBUG
	printf("Created window\n");
#endif
	if (fullscreen) {
		XMapRaised(disp, win);
		waitMapped(disp, win);
		if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
#ifdef _DEBUG
			printf("Could not get display modes\n");
#endif
			return JNI_FALSE;
		}
		for ( i = 0; i < num_modes; ++i ) {
#ifdef _DEBUG
			printf("Mode %d: %dx%d\n", i, avail_modes[i]->hdisplay, avail_modes[i]->vdisplay);
#endif
			if (avail_modes[i]->hdisplay == width && avail_modes[i]->vdisplay == height) {
				if (!XF86VidModeSwitchToMode(disp, screen, avail_modes[i])) {
#ifdef _DEBUG
					printf("Could not switch mode\n");
#endif
					return JNI_FALSE;
				}
			}
		}
		XF86VidModeSetViewPort(disp, screen, 0, 0);
	} else {
		XMapWindow(disp, win);
		waitMapped(disp, win);
	}
	XClearWindow(disp, win);
	XSync(disp, True);

	return JNI_TRUE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_Display_nDestroy(JNIEnv * env, jclass clazz) {
	XDestroyWindow(disp, win);
	if (!XF86VidModeSwitchToMode(disp, screen, avail_modes[0])) {
#ifdef _DEBUG
		printf("Could not switch mode\n");
#endif
	}
	XFree(vis_info);
	XFree(avail_modes);
	XCloseDisplay(disp);
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

	int depth = DefaultDepth(disp, screen);

	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		return NULL;
	}
	if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
#ifdef _DEBUG
		printf("Could not get display modes\n");
#endif
		return NULL;
	}
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = (*env)->FindClass(env, "org/lwjgl/DisplayMode");
	jobjectArray ret = (*env)->NewObjectArray(env, num_modes, displayModeClass, NULL);
	jmethodID displayModeConstructor = (*env)->GetMethodID(env, displayModeClass, "<init>", "(IIII)V");
	
	for (i = 0; i < num_modes; i++) {
		jobject displayMode = (*env)->NewObject(env, displayModeClass, displayModeConstructor, avail_modes[i]->hdisplay, avail_modes[i]->vdisplay, depth, 0);
		(*env)->SetObjectArrayElement(env, ret, i, displayMode);
	}
	XFree(avail_modes);
	XCloseDisplay(disp);
	return ret;
}

