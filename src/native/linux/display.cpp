/* 
 * Copyright (c) 2002-2004 LWJGL Project
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
 * * Neither the name of 'LWJGL' nor the names of 
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

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/extensions/xf86vmode.h>
#include <X11/extensions/Xrandr.h>
#include <X11/Xutil.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "display.h"
#include "common_tools.h"
#include "Window.h"

typedef struct {
	int width;
	int height;
	int freq;
	union {
		int size_index; // Data for Xrandr extension
		XF86VidModeModeInfo xf86vm_modeinfo; // Data for XF86VidMode extension
	} mode_data;
} mode_info;

static int saved_width;
static int saved_height;
static int saved_freq;
static int current_width;
static int current_height;
static int current_freq;
static int saved_gamma_ramp_length = 0;
static unsigned short *r_ramp;
static unsigned short *g_ramp;
static unsigned short *b_ramp;
static extension current_extension = NONE;

int getScreenModeWidth(void) {
	return current_width;
}

int getScreenModeHeight(void) {
	return current_height;
}

extension getCurrentDisplayModeExtension(void) {
	return current_extension;
}

static bool getXF86VidModeVersion(Display *disp, int *major, int *minor) {
	int event_base, error_base;

	if (!XF86VidModeQueryExtension(disp, &event_base, &error_base)) {
		printfDebug("XF86VidMode extension not available\n");
		return false;
	}
	if (!XF86VidModeQueryVersion(disp, major, minor)) {
		printfDebug("Could not query XF86VidMode version\n");
		return false;
	}
	printfDebug("XF86VidMode extension version %i.%i\n", *major, *minor);
	return true;
}

static bool getXrandrVersion(Display *disp, int *major, int *minor) {
	int event_base, error_base;

	if (!XRRQueryExtension(disp, &event_base, &error_base)) {
		printfDebug("Xrandr extension not available\n");
		return false;
	}
	if (!XRRQueryVersion(disp, major, minor)) {
		printfDebug("Could not query Xrandr version\n");
		return false;
	}
	printfDebug("Xrandr extension version %i.%i\n", *major, *minor);
	return true;
}

static bool isXrandrSupported(Display *disp) {
	int major, minor;
	if (!getXrandrVersion(disp, &major, &minor))
		return false;
	return major >= 1;
}

static bool isXF86VidModeSupported(Display *disp) {
	int minor_ver, major_ver;
	if (!getXF86VidModeVersion(disp, &major_ver, &minor_ver))
		return false;
	return major_ver >= 2;
}
	
static extension getBestDisplayModeExtension(Display *disp) {
	if (isXrandrSupported(disp))
		return XRANDR;
	else if (isXF86VidModeSupported(disp))
		return XF86VIDMODE;
	else
		return NONE;
}

static mode_info *getXrandrDisplayModes(Display *disp, int screen, int *num_modes) {
	int num_randr_sizes;
	XRRScreenSize *sizes = XRRSizes(disp, screen, &num_randr_sizes);
	/* Count number of modes */
	int num_randr_modes = 0;
	for (int i = 0; i < num_randr_sizes; i++) {
		int num_randr_rates;
		XRRRates(disp, screen, i, &num_randr_rates);
		num_randr_modes += num_randr_rates;
	}
	mode_info *avail_modes = (mode_info *)malloc(sizeof(mode_info)*num_randr_modes);
	if (avail_modes == NULL)
		return NULL;
	int mode = 0;
	for (int i = 0; i < num_randr_sizes; i++) {
		int num_randr_rates;
		short *freqs = XRRRates(disp, screen, i, &num_randr_rates);
		for (int j = 0; j < num_randr_rates; j++) {
			avail_modes[mode].width = sizes[i].width;
			avail_modes[mode].height = sizes[i].height;
			avail_modes[mode].freq = freqs[j];
			avail_modes[mode].mode_data.size_index = i;
			mode++;
		}
	}
	*num_modes = num_randr_modes;
	return avail_modes;
}

static mode_info *getXF86VidModeDisplayModes(Display *disp, int screen, int *num_modes) {
	int num_xf86vm_modes;
	XF86VidModeModeInfo **avail_xf86vm_modes;
	XF86VidModeGetAllModeLines(disp, screen, &num_xf86vm_modes, &avail_xf86vm_modes);
	mode_info *avail_modes = (mode_info *)malloc(sizeof(mode_info)*num_xf86vm_modes);
	if (avail_modes == NULL) {
		XFree(avail_xf86vm_modes);
		return NULL;
	}
	for (int i = 0; i < num_xf86vm_modes; i++) {
		avail_modes[i].width = avail_xf86vm_modes[i]->hdisplay;
		avail_modes[i].height = avail_xf86vm_modes[i]->vdisplay;
		avail_modes[i].freq = 0; // No frequency support in XF86VidMode
		avail_modes[i].mode_data.xf86vm_modeinfo = *avail_xf86vm_modes[i];
	}
	XFree(avail_xf86vm_modes);
	*num_modes = num_xf86vm_modes;
	return avail_modes;
}

static mode_info *getDisplayModes(Display *disp, int screen, int *num_modes) {
	switch (current_extension) {
		case XF86VIDMODE:
			return getXF86VidModeDisplayModes(disp, screen, num_modes);
		case XRANDR:
			return getXrandrDisplayModes(disp, screen, num_modes);
		case NONE:
			// fall through
		default:
			// Should never happen
			return NULL;
	}
}

static bool setXF86VidModeMode(Display *disp, int screen, mode_info *mode) {
	return True == XF86VidModeSwitchToMode(disp, screen, &mode->mode_data.xf86vm_modeinfo);
}

static bool setXrandrMode(Display *disp, int screen, mode_info *mode) {
	Status success;
	do {
		Time config_time;
		Drawable root_window = RootWindow(disp, screen);
		XRRScreenConfiguration *screen_configuration = XRRGetScreenInfo (disp, root_window);
		XRRConfigTimes(screen_configuration, &config_time);
		Rotation current_rotation;
		XRRConfigRotations(screen_configuration, &current_rotation);
		success = XRRSetScreenConfigAndRate(disp, screen_configuration, root_window, mode->mode_data.size_index, current_rotation, mode->freq, config_time);
		XRRFreeScreenConfigInfo(screen_configuration);
	} while (success != 0);
	return true;
}

static bool setMode(Display *disp, int screen, int width, int height, int freq/*, bool lock_mode*/) {
	if (current_extension == NONE)
		return false;
	int num_modes, i;
	mode_info *avail_modes = getDisplayModes(disp, screen, &num_modes);
	if (avail_modes == NULL) {
		printfDebug("Could not get display modes\n");
		return false;
	}
	bool result = false;
	for ( i = 0; i < num_modes; ++i ) {
		printfDebug("Mode %d: %dx%d @%d\n", i, avail_modes[i].width, avail_modes[i].height, avail_modes[i].freq);
		if (avail_modes[i].width == width && avail_modes[i].height == height && avail_modes[i].freq == freq) {
			switch (current_extension) {
				case XF86VIDMODE:
					if (!setXF86VidModeMode(disp, screen, &avail_modes[i])) {
						printfDebug("Could not switch mode\n");
						continue;
					}
					break;
				case XRANDR:
					if (!setXrandrMode(disp, screen, &avail_modes[i])) {
						printfDebug("Could not switch mode\n");
						continue;
					}
					break;
				case NONE: // Should never happen
				default:   // Should never happen
					continue;
			}
			result = true;
			break;
		}
	}
	free(avail_modes);
	XFlush(disp);
	return result;
}

static void freeSavedGammaRamps() {
	free(r_ramp);
	free(g_ramp);
	free(b_ramp);
	r_ramp = NULL;
	g_ramp = NULL;
	b_ramp = NULL;
	saved_gamma_ramp_length = 0;
}

static int getGammaRampLength(Display *disp, int screen) {
	int ramp_size;
	if (!isXF86VidModeSupported(disp)) {
		printfDebug("XF86VidMode extension version >= 2 not found\n");
		return 0;
	}
	if (XF86VidModeGetGammaRampSize(disp, screen, &ramp_size) == False) {
		printfDebug("XF86VidModeGetGammaRampSize call failed\n");
		return 0;
	}
	return ramp_size;
}

int getGammaRampLength(int screen) {
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		printfDebug("Could not open display");
		return 0;
	}
	return getGammaRampLength(disp, screen);
	XCloseDisplay(disp);
}

jobject initDisplay(JNIEnv *env, int screen) {
	int num_modes;
	mode_info *avail_modes;
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		throwException(env, "Could not open display");
		return NULL;
	}

	current_extension = getBestDisplayModeExtension(disp);
	if (current_extension == NONE) {
		throwException(env, "No display mode extension is available");
		XCloseDisplay(disp);
		return NULL;
	}
	avail_modes = getDisplayModes(disp, screen, &num_modes);
	if (avail_modes == NULL) {
		throwException(env, "Could not get display modes");
		XCloseDisplay(disp);
		return NULL;
	}
	saved_width = current_width = avail_modes[0].width;
	saved_height = current_height = avail_modes[0].height;
	saved_freq = current_freq = avail_modes[0].freq;
	int bpp = XDefaultDepth(disp, screen);
	printfDebug("Original display dimensions: width %d, height %d freq %d\n", saved_width, saved_height, saved_freq);
	jclass jclass_DisplayMode = env->FindClass("org/lwjgl/opengl/DisplayMode");
	jmethodID ctor = env->GetMethodID(jclass_DisplayMode, "<init>", "(IIII)V");
	jobject newMode = env->NewObject(jclass_DisplayMode, ctor, saved_width, saved_height, bpp, saved_freq);

	free(avail_modes);

	/* Fetch the current gamma ramp */
	saved_gamma_ramp_length = getGammaRampLength(disp, screen);
	if (saved_gamma_ramp_length > 0) {
		r_ramp = (unsigned short *)malloc(sizeof(unsigned short)*saved_gamma_ramp_length);
		g_ramp = (unsigned short *)malloc(sizeof(unsigned short)*saved_gamma_ramp_length);
		b_ramp = (unsigned short *)malloc(sizeof(unsigned short)*saved_gamma_ramp_length);
		if (!XF86VidModeGetGammaRamp(disp, screen, saved_gamma_ramp_length, r_ramp, g_ramp, b_ramp))
			freeSavedGammaRamps();
	}
	XCloseDisplay(disp);
	return newMode;
}

void temporaryRestoreMode(int screen) {
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		printfDebug("Could not open display");
		return;
	}
	if (!setMode(disp, screen, current_width, current_height, current_freq))
		printfDebug("Could not restore mode\n");
	XCloseDisplay(disp);
	// Don't propagate error to caller
}

void temporaryResetMode(int screen) {
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		printfDebug("Could not open display");
		return;
	}
	if (!setMode(disp, screen, saved_width, saved_height, saved_freq))
		printfDebug("Could not reset mode\n");
	XCloseDisplay(disp);
	// Don't propagate error to caller
}

void switchDisplayMode(JNIEnv * env, jobject mode, int screen) {
	if (mode == NULL) {
		throwException(env, "mode must be non-null");
		return;
	}
	jclass cls_displayMode = env->GetObjectClass(mode);
	jfieldID fid_width = env->GetFieldID(cls_displayMode, "width", "I");
	jfieldID fid_height = env->GetFieldID(cls_displayMode, "height", "I");
	jfieldID fid_freq = env->GetFieldID(cls_displayMode, "freq", "I");
	int width = env->GetIntField(mode, fid_width);
	int height = env->GetIntField(mode, fid_height);
	int freq = env->GetIntField(mode, fid_freq);
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		throwException(env, "Could not open display");
		return;
	}
	if (setMode(disp, screen, width, height, freq)) {
		current_width = width;
		current_height = height;
		current_freq = freq;
	} else
		throwException(env, "Could not switch mode.");
	XCloseDisplay(disp);
}

void resetDisplayMode(JNIEnv *env, int screen) {
	Display *disp = XOpenDisplay(NULL);
//	Display *disp = incDisplay(env);
	if (disp == NULL)
		return;
	if (!setMode(disp, screen, saved_width, saved_height, saved_freq)) {
		printfDebug("Failed to reset mode");
	}
	if (saved_gamma_ramp_length > 0) {
		XF86VidModeSetGammaRamp(disp, screen, saved_gamma_ramp_length, r_ramp, g_ramp, b_ramp);
		freeSavedGammaRamps();
	}
//	decDisplay();
	XCloseDisplay(disp);
}

jobjectArray getAvailableDisplayModes(JNIEnv * env, int screen) {
	int num_modes, i;
	mode_info *avail_modes;
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		throwException(env, "Could not open display");
		return NULL;
	}

	int bpp = XDefaultDepth(disp, screen);
	avail_modes = getDisplayModes(disp, screen, &num_modes);
	if (avail_modes == NULL) {
		printfDebug("Could not get display modes\n");
		XCloseDisplay(disp);
		return NULL;
	}
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/opengl/DisplayMode");
	jobjectArray ret = env->NewObjectArray(num_modes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");

	for (i = 0; i < num_modes; i++) {
		jobject displayMode = env->NewObject(displayModeClass, displayModeConstructor, avail_modes[i].width, avail_modes[i].height, bpp, avail_modes[i].freq);
		env->SetObjectArrayElement(ret, i, displayMode);
	}
	free(avail_modes);
	XCloseDisplay(disp);
	return ret;
}

void setGammaRamp(JNIEnv *env, jobject gamma_ramp_buffer, int screen) {
	Display * disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		throwException(env, "Could not open display");
		return;
	}
	int gamma_ramp_length = getGammaRampLength(disp, screen);
	if (gamma_ramp_length == 0) {
		throwException(env, "Gamma ramp not supported");
		return;
	}
	const float *gamma_ramp = (const float *)env->GetDirectBufferAddress(gamma_ramp_buffer);
	unsigned short *ramp;
	ramp = (unsigned short *)malloc(sizeof(unsigned short)*gamma_ramp_length);
	for (int i = 0; i < gamma_ramp_length; i++) {
		float scaled_gamma = gamma_ramp[i]*0xffff;
		ramp[i] = (unsigned short)round(scaled_gamma);
	}
	if (XF86VidModeSetGammaRamp(disp, screen, gamma_ramp_length, ramp, ramp, ramp) == False) {
		throwException(env, "Could not set gamma ramp.");
	}
	XCloseDisplay(disp);
}
