/* 
 * Copyright (c) 2002-2008 LWJGL Project
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
 * Include file to access public window features
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#ifndef _LWJGL_CONTEXT_H_INCLUDED_
#define _LWJGL_CONTEXT_H_INCLUDED_

#include <jni.h>
#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include "extgl_glx.h"

typedef struct {
	VisualID visualid;
	int depth;
} GLXConfig;

typedef struct {
	GLXFBConfigID config_id;
} GLX13Config;

typedef struct {
	Display *display;
	int screen;
	GLXDrawable drawable;
	// This flag determines the appropriate glx struct
	bool glx13;
	union {
		GLXConfig glx_config;
		GLX13Config glx13_config;
	} config;
} X11PeerInfo;

/* GLX 1.3 chooser */
extern GLXFBConfig *chooseVisualGLX13(JNIEnv *env, Display *disp, int screen, jobject pixel_format, bool use_display_bpp, int drawable_type, bool double_buffer);

/* Default GLX chooser*/
extern XVisualInfo *chooseVisualGLX(JNIEnv *env, Display *disp, int screen, jobject pixel_format, bool use_display_bpp, bool double_buffer);

extern XVisualInfo *getVisualInfoFromPeerInfo(JNIEnv *env, X11PeerInfo *peer_info);
extern GLXFBConfig *getFBConfigFromPeerInfo(JNIEnv *env, X11PeerInfo *peer_info);

extern bool initPeerInfo(JNIEnv *env, jobject peer_info_handle, Display *display, int screen, jobject pixel_format, bool use_display_bpp, int drawable_type, bool double_buffered, bool force_glx13);

#endif /* _LWJGL_CONTEXT_H_INCLUDED_ */
