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
 * Linux Pbuffer.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <stdlib.h>
#include "org_lwjgl_opengl_LinuxPbufferPeerInfo.h"
#include "org_lwjgl_opengl_Pbuffer.h"
#include "extgl.h"
#include "context.h"
#include "common_tools.h"

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetPbufferCapabilities
  (JNIEnv *env, jclass clazz, jlong display, jint screen)
{
	Display *disp = (Display *)(intptr_t)display;
	GLXExtensions extension_flags;
	if (!extgl_InitGLX(disp, screen, &extension_flags))
		return 0;
	// Only support the GLX 1.3 Pbuffers and ignore the GLX_SGIX_pbuffer extension
	return extension_flags.GLX13 ? org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED : 0;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxPbufferPeerInfo_nInitHandle
  (JNIEnv *env, jclass clazz, jlong display, jint screen, jobject peer_info_handle, jint width, jint height, jobject pixel_format) {
	Display *disp = (Display *)(intptr_t)display;
	GLXExtensions extension_flags;
	if (!extgl_InitGLX(disp, screen, &extension_flags) || !extension_flags.GLX13) {
		throwException(env, "No Pbuffer support");
		return;
	}
	bool result = initPeerInfo(env, peer_info_handle, disp, screen, pixel_format, false, GLX_PBUFFER_BIT, false, true);
	if (!result)
		return;
	const int buffer_attribs[] = {GLX_PBUFFER_WIDTH, width,
				      GLX_PBUFFER_HEIGHT, height,
				      GLX_PRESERVED_CONTENTS, True,
				      GLX_LARGEST_PBUFFER, False,
					  None, None};

	X11PeerInfo *peer_info = (X11PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	GLXFBConfig *config = getFBConfigFromPeerInfo(env, peer_info);
	if (config != NULL) {
		GLXPbuffer buffer = lwjgl_glXCreatePbuffer(peer_info->display, *config, buffer_attribs);
		XFree(config);
		peer_info->drawable = buffer;
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxPbufferPeerInfo_nDestroy
  (JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	X11PeerInfo *peer_info = (X11PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	lwjgl_glXDestroyPbuffer(peer_info->display, peer_info->drawable);
}
