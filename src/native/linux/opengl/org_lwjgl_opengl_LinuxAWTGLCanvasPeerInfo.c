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
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <jni.h>
#include <jawt_md.h>
#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include "awt_tools.h"
#include "org_lwjgl_opengl_LinuxAWTGLCanvasPeerInfo.h"
#include "context.h"

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxAWTGLCanvasPeerInfo_getScreenFromSurfaceInfo
  (JNIEnv *env, jclass clazz, jobject lock_buffer_handle) {
	const AWTSurfaceLock *awt_lock = (AWTSurfaceLock *)(*env)->GetDirectBufferAddress(env, lock_buffer_handle);
	// Get the platform-specific drawing info
	JAWT_X11DrawingSurfaceInfo *dsi_x11 = (JAWT_X11DrawingSurfaceInfo*)awt_lock->dsi->platformInfo;

	XVisualInfo template;
	int num_infos;
	template.visualid = dsi_x11->visualID;
	template.depth = dsi_x11->depth;
	XVisualInfo *vis_info = XGetVisualInfo(dsi_x11->display, VisualIDMask | VisualDepthMask, &template, &num_infos);
	if (vis_info == NULL) {
		throwException(env, "Could not determine screen");
		return -1;
	}
	int screen = vis_info[0].screen;
	XFree(vis_info);
	return screen;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxAWTGLCanvasPeerInfo_nInitHandle
  (JNIEnv *env, jclass clazz, int screen, jobject lock_buffer_handle, jobject peer_info_handle) {
	const AWTSurfaceLock *awt_lock = (AWTSurfaceLock *)(*env)->GetDirectBufferAddress(env, lock_buffer_handle);
	X11PeerInfo *peer_info = (X11PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	// Get the platform-specific drawing info
	JAWT_X11DrawingSurfaceInfo *dsi_x11 = (JAWT_X11DrawingSurfaceInfo*)awt_lock->dsi->platformInfo;

	peer_info->display = dsi_x11->display;
	peer_info->screen = screen;
	peer_info->drawable = dsi_x11->drawable;
	peer_info->glx13 = false;
	peer_info->config.glx_config.visualid = dsi_x11->visualID;
	peer_info->config.glx_config.depth = dsi_x11->depth;
}
