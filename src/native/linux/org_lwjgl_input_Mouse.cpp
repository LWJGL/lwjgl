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
 * Linux mouse handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */


#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/extensions/xf86vmode.h>
#include <assert.h>
#include <string.h>
#include "org_lwjgl_input_Mouse.h"

#define NUM_BUTTONS 3

#define POINTER_WARP_BORDER 10
#define WARP_RETRY 5

extern Display *disp;
extern Window win;
extern int screen;
extern int current_fullscreen;
extern int win_width;
extern int win_height;

bool pointer_grabbed;

jfieldID fid_button;
jfieldID fid_dx;
jfieldID fid_dy;
jfieldID fid_dz;

int last_x;
int last_y;
int last_z;
int current_x;
int current_y;
int current_z;
unsigned char buttons[NUM_BUTTONS];

Cursor blank_cursor;

extern int isFocused(void);

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs
  (JNIEnv * env, jclass clazz)
{
	// Get a global class instance, just to be sure
	static jobject globalClassLock = NULL;

	if (globalClassLock == NULL) {
		globalClassLock = env->NewGlobalRef(clazz);
	}

	// Now cache the field IDs:
	if (fid_button == NULL) {
		fid_button = env->GetStaticFieldID(clazz, "button", "[Z");
	}
	if (fid_dx == NULL) {
		fid_dx = env->GetStaticFieldID(clazz, "dx", "I");
	}
	if (fid_dy == NULL) {
		fid_dy = env->GetStaticFieldID(clazz, "dy", "I");
	}
	if (fid_dz == NULL) {
		fid_dz = env->GetStaticFieldID(clazz, "dz", "I");
	}
}

int blankCursor(void) {
	unsigned int best_width, best_height;
	if (XQueryBestCursor(disp, win, 1, 1, &best_width, &best_height) == 0) {
#ifdef _DEBUG
		printf("Could not query best cursor size\n");
#endif
		return 0;
	}
	Pixmap mask = XCreatePixmap(disp, win, best_width, best_height, 1);
	XGCValues gc_values;
	gc_values.foreground = 0;
	GC gc = XCreateGC(disp, mask, GCForeground, &gc_values);
	XFillRectangle(disp, mask, gc, 0, 0, best_width, best_height);
	XFreeGC(disp, gc);
	XColor dummy_color;
	blank_cursor = XCreatePixmapCursor(disp, mask, mask, &dummy_color, &dummy_color, 0, 0);
	XFreePixmap(disp, mask);
	return 1;
}

int grabPointer(void) {
	int result;
	int mask = EnterWindowMask | LeaveWindowMask | PointerMotionMask | ButtonPressMask | ButtonReleaseMask;
	if (current_fullscreen) {
		result = XGrabPointer(disp, win, False, mask, GrabModeAsync, GrabModeAsync, win, blank_cursor, CurrentTime);
		XWarpPointer(disp, None, win, 0, 0, 0, 0, current_x, current_y);
		XF86VidModeSetViewPort(disp, screen, 0, 0); // make sure we have a centered window
	} else
		result = XGrabPointer(disp, win, False, mask, GrabModeAsync, GrabModeAsync, None, blank_cursor, CurrentTime);
	if (result == GrabSuccess)
		pointer_grabbed = true;
	return result;
}

void ungrabPointer(void) {
	pointer_grabbed = false;
	XUngrabPointer(disp, CurrentTime);
}

int updatePointerGrab(void) {
	if (current_fullscreen) {
		if (!pointer_grabbed)
			return grabPointer();
	} else {
		if (isFocused()) {
			if (!pointer_grabbed)
				return grabPointer();
		} else {
			if (pointer_grabbed)
				ungrabPointer();
		}
	}
	return GrabSuccess;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nCreate
  (JNIEnv * env, jclass clazz)
{
	int i;
	
	current_x = current_y = current_z = last_x = last_y = last_z = pointer_grabbed = 0;
	for (i = 0; i < NUM_BUTTONS; i++)
		buttons[i] = 0;
	if (!blankCursor()) {
#ifdef _DEBUG
		printf("Could create blank cursor\n");
#endif
		return JNI_FALSE;
	}
	if (updatePointerGrab() != GrabSuccess) {
#ifdef _DEBUG
		printf("Could not grab pointer\n");
#endif
		return JNI_FALSE;
	}
	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nGetNumButtons
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNumButtons(JNIEnv *env, jclass clazz) {
	return (jint)NUM_BUTTONS;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nHasZValue
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nHasZValue(JNIEnv *env, jclass clazz) {
	return JNI_TRUE;
}

    
/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy
  (JNIEnv * env, jclass clazz)
{
	if (pointer_grabbed)
		ungrabPointer();
	XFreeCursor(disp, blank_cursor);
}

int checkPointer() {
	XEvent event;
	int count = 0;
	updatePointerGrab();
	while (XCheckMaskEvent(disp, ButtonPressMask | ButtonReleaseMask | PointerMotionMask, &event)) {
		count++;
		switch (event.type) {
			case ButtonPress:
				switch (event.xbutton.button) {
					case Button1:
						buttons[0] = 1;
						break;
					case Button2:
						buttons[1] = 1;
						break;
					case Button3:
						buttons[2] = 1;
						break;
					case Button4:
						current_z--;
						break;
					case Button5:
						current_z++;
						break;
					default: assert(0);
				}
				break;
			case ButtonRelease:
				switch (event.xbutton.button) {
					case Button1:
						buttons[0] = 0;
						break;
					case Button2:
						buttons[1] = 0;
						break;
					case Button3:
						buttons[2] = 0;
						break;
					case Button4: /* Fall through */
					case Button5:
						break;
					default: assert(0);
				}
				break;
			case MotionNotify:
				current_x = event.xmotion.x;
				current_y = event.xmotion.y;
				break;
			default: assert(0);
		}
	}
	return count;
}

void warpPointer(void) {
	int i;
	// Reset pointer to middle of screen if inside a certain inner border
	if (current_x < POINTER_WARP_BORDER || current_y < POINTER_WARP_BORDER || 
			current_x > win_width - POINTER_WARP_BORDER || current_y > win_height - POINTER_WARP_BORDER) {
		current_x = last_x = win_width/2;
		current_y = last_y = win_height/2;
		XWarpPointer(disp, None, win, 0, 0, 0, 0, current_x, current_y);
		XEvent event;
		// Try to catch the warp pointer event
		for (i = 0; i < WARP_RETRY; i++) {
			XMaskEvent(disp, PointerMotionMask, &event);
			if (event.xmotion.x > current_x - POINTER_WARP_BORDER &&
				event.xmotion.x < current_x + POINTER_WARP_BORDER &&
				event.xmotion.y > current_y - POINTER_WARP_BORDER &&
				event.xmotion.y < current_y + POINTER_WARP_BORDER)
				break;
#ifdef _DEBUG
			printf("Skipped event searching for warp event %d, %d\n", event.xmotion.x, event.xmotion.y);
#endif
		}
#ifdef _DEBUG
		if (i == WARP_RETRY)
			printf("Never got warp event\n");
#endif
	}
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll
  (JNIEnv * env, jclass clazz)
{
	checkPointer();
	int moved_x = current_x - last_x;
	int moved_y = current_y - last_y;
	int moved_z = current_z - last_z;
	env->SetStaticIntField(clazz, fid_dx, (jint)moved_x);
	env->SetStaticIntField(clazz, fid_dy, (jint)moved_y);
	env->SetStaticIntField(clazz, fid_dz, (jint)moved_z);
	last_x = current_x;
	last_y = current_y;
	last_z = current_z;
	jbooleanArray buttonsArray = (jbooleanArray) env->GetStaticObjectField(clazz, fid_button);
	unsigned char * class_buttons = (unsigned char *) env->GetPrimitiveArrayCritical(buttonsArray, NULL);
	memcpy(class_buttons, buttons, NUM_BUTTONS*sizeof(unsigned char));
	env->ReleasePrimitiveArrayCritical(buttonsArray, class_buttons, 0);
	if (current_fullscreen)
		warpPointer();
}


/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer
  (JNIEnv * env, jclass clazz) {
      printf("*** FIXME: nEnableBuffer not implemented!\n*");
}
