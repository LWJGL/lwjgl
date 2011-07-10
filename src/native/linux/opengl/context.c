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

#include <jni.h>
#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include "extgl_glx.h"
#include "context.h"

XVisualInfo *getVisualInfoFromPeerInfo(JNIEnv *env, X11PeerInfo *peer_info) {
	XVisualInfo *vis_info;
	if (!peer_info->glx13) {
		XVisualInfo template;
		template.visualid = peer_info->config.glx_config.visualid;
		template.depth = peer_info->config.glx_config.depth;
		template.screen = peer_info->screen;
		int num_infos;
		vis_info = XGetVisualInfo(peer_info->display, VisualIDMask | VisualScreenMask | VisualDepthMask, &template, &num_infos);
		if (vis_info == NULL) {
			throwException(env, "Could not find VisualInfo from peer info");
			return NULL;
		}
		// Check the assumption from GLX 1.3 docs that a VisualInfo is uniquely identified by its
		// {VisualID, screen, depth} tuple
		if (num_infos != 1) {
			XFree(vis_info);
			throwException(env, "No unique VisualInfo matches peer info");
			return NULL;
		}
	} else {
		GLXFBConfig *configs = getFBConfigFromPeerInfo(env, peer_info);
		if (configs == NULL)
			return NULL;
		vis_info = lwjgl_glXGetVisualFromFBConfig(peer_info->display, configs[0]);
		if (vis_info == NULL)
			throwException(env, "Could not get VisualInfo from GLX 1.3 config");
		XFree(configs);
	}
	return vis_info;
}

GLXFBConfig *getFBConfigFromPeerInfo(JNIEnv *env, X11PeerInfo *peer_info) {
	int attribs[] = {GLX_FBCONFIG_ID, peer_info->config.glx13_config.config_id, None, None};
	int num_elements;
	GLXFBConfig *configs = lwjgl_glXChooseFBConfig(peer_info->display, peer_info->screen, attribs, &num_elements);
	if (configs == NULL) {
		throwException(env, "Could not find GLX 1.3 config from peer info");
		return NULL;
	}
	// Check that only one FBConfig matches the config id
	if (num_elements != 1) {
		XFree(configs);
		throwException(env, "No unique GLX 1.3 config matches peer info");
		return NULL;
	}
	return configs;
}

static int convertToBPE(int bpp) {
	int bpe;
	switch (bpp) {
		case 0:
			bpe = 0;
			break;
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

static GLXFBConfig *chooseVisualGLX13FromBPP(JNIEnv *env, Display *disp, int screen, jobject pixel_format, int bpp, int drawable_type, bool double_buffer) {
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "alpha", "I"));
	int depth = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "depth", "I"));
	int stencil = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stencil", "I"));
	int samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	int colorSamples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "colorSamples", "I"));
	int num_aux_buffers = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_alpha", "I"));
	
	bool stereo = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stereo", "Z"));
	bool floating_point = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point", "Z"));
	bool floating_point_packed = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point_packed", "Z"));
	bool sRGB = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "sRGB", "Z"));

	int bpe = convertToBPE(bpp);
	int accum_bpe = convertToBPE(accum_bpp);
	attrib_list_t attrib_list;
	initAttribList(&attrib_list);
	int render_type;
	
	if ( floating_point )
		render_type = GLX_RGBA_FLOAT_BIT;
	else if ( floating_point_packed )
		render_type = GLX_RGBA_UNSIGNED_FLOAT_BIT_EXT;
	else
		render_type = GLX_RGBA_BIT;
		
	putAttrib(&attrib_list, GLX_RENDER_TYPE); putAttrib(&attrib_list, render_type);
	putAttrib(&attrib_list, GLX_DOUBLEBUFFER); putAttrib(&attrib_list, double_buffer ? True : False);
	putAttrib(&attrib_list, GLX_DRAWABLE_TYPE); putAttrib(&attrib_list, drawable_type);
	putAttrib(&attrib_list, GLX_DEPTH_SIZE); putAttrib(&attrib_list, depth);
	putAttrib(&attrib_list, GLX_RED_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_GREEN_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_BLUE_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_ALPHA_SIZE); putAttrib(&attrib_list, alpha);
	putAttrib(&attrib_list, GLX_STENCIL_SIZE); putAttrib(&attrib_list, stencil);
	putAttrib(&attrib_list, GLX_AUX_BUFFERS); putAttrib(&attrib_list, num_aux_buffers);
	putAttrib(&attrib_list, GLX_ACCUM_RED_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_GREEN_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_BLUE_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_ALPHA_SIZE); putAttrib(&attrib_list, accum_alpha);
	if (stereo) {
		putAttrib(&attrib_list, GLX_STEREO); putAttrib(&attrib_list, True);
	}
	// Assume the caller has checked support for multisample
	if (samples > 0) {
		putAttrib(&attrib_list, GLX_SAMPLE_BUFFERS_ARB); putAttrib(&attrib_list, 1);
		putAttrib(&attrib_list, GLX_SAMPLES_ARB); putAttrib(&attrib_list, samples); // GLX_COVERAGE_SAMPLES_NV if colorSamples > 0
        if ( colorSamples > 0 ) {
            putAttrib(&attrib_list, GLX_COLOR_SAMPLES_NV); putAttrib(&attrib_list, colorSamples);
        }
	}
	if (sRGB) {
		putAttrib(&attrib_list, GLX_FRAMEBUFFER_SRGB_CAPABLE_ARB); putAttrib(&attrib_list, True);
	}
	putAttrib(&attrib_list, None); putAttrib(&attrib_list, None);
	int num_formats = 0;
	GLXFBConfig* configs = lwjgl_glXChooseFBConfig(disp, screen, attrib_list.attribs, &num_formats);
	if (num_formats > 0) {
		return configs;
	} else {
		if (configs != NULL)
			XFree(configs);
		return NULL;
	}
}

GLXFBConfig *chooseVisualGLX13(JNIEnv *env, Display *disp, int screen, jobject pixel_format, bool use_display_bpp, int drawable_type, bool double_buffer) {
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int bpp;
	if (use_display_bpp) {
		bpp = XDefaultDepthOfScreen(XScreenOfDisplay(disp, screen));
		GLXFBConfig *configs = chooseVisualGLX13FromBPP(env, disp, screen, pixel_format, bpp, drawable_type, double_buffer);
		if (configs != NULL)
			return configs;
		else
			bpp = 16;
	} else
		bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "bpp", "I"));
	return chooseVisualGLX13FromBPP(env, disp, screen, pixel_format, bpp, drawable_type, double_buffer);
}

static XVisualInfo *chooseVisualGLXFromBPP(JNIEnv *env, Display *disp, int screen, jobject pixel_format, int bpp, bool double_buffer) {
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "alpha", "I"));
	int depth = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "depth", "I"));
	int stencil = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stencil", "I"));
	int samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	int colorSamples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "colorSamples", "I"));
	int num_aux_buffers = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_alpha", "I"));

	bool stereo = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stereo", "Z"));
	bool sRGB = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "sRGB", "Z"));

	int bpe = convertToBPE(bpp);
	int accum_bpe = convertToBPE(accum_bpp);
	attrib_list_t attrib_list;
	initAttribList(&attrib_list);
	putAttrib(&attrib_list, GLX_RGBA);
	putAttrib(&attrib_list, GLX_DOUBLEBUFFER);
	putAttrib(&attrib_list, GLX_DEPTH_SIZE); putAttrib(&attrib_list, depth);
	putAttrib(&attrib_list, GLX_RED_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_GREEN_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_BLUE_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_ALPHA_SIZE); putAttrib(&attrib_list, alpha);
	putAttrib(&attrib_list, GLX_STENCIL_SIZE); putAttrib(&attrib_list, stencil);
	putAttrib(&attrib_list, GLX_AUX_BUFFERS); putAttrib(&attrib_list, num_aux_buffers);
	putAttrib(&attrib_list, GLX_ACCUM_RED_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_GREEN_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_BLUE_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_ALPHA_SIZE); putAttrib(&attrib_list, accum_alpha);
	if (stereo)
		putAttrib(&attrib_list, GLX_STEREO);
	// Assume the caller has checked support for multisample
	if (samples > 0) {
		putAttrib(&attrib_list, GLX_SAMPLE_BUFFERS_ARB); putAttrib(&attrib_list, 1);
		putAttrib(&attrib_list, GLX_SAMPLES_ARB); putAttrib(&attrib_list, samples); // GLX_COVERAGE_SAMPLES_NV if colorSamples > 0
        if ( colorSamples > 0 )
            putAttrib(&attrib_list, GLX_COLOR_SAMPLES_NV); putAttrib(&attrib_list, colorSamples);
	}
	if (sRGB)
		putAttrib(&attrib_list, GLX_FRAMEBUFFER_SRGB_CAPABLE_ARB);
	putAttrib(&attrib_list, None);
	return lwjgl_glXChooseVisual(disp, screen, attrib_list.attribs);
}

XVisualInfo *chooseVisualGLX(JNIEnv *env, Display *disp, int screen, jobject pixel_format, bool use_display_bpp, bool double_buffer) {
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int bpp;
	if (use_display_bpp) {
		bpp = XDefaultDepthOfScreen(XScreenOfDisplay(disp, screen));
		XVisualInfo *vis_info = chooseVisualGLXFromBPP(env, disp, screen, pixel_format, bpp, double_buffer);
		if (vis_info != NULL)
			return vis_info;
		else
			bpp = 16;
	} else
		bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "bpp", "I"));
	return chooseVisualGLXFromBPP(env, disp, screen, pixel_format, bpp, double_buffer);
}

static void dumpVisualInfo(JNIEnv *env, Display *display, GLXExtensions *extension_flags, XVisualInfo *vis_info) {
	int alpha, depth, stencil, r, g, b;
	int sample_buffers = 0;
	int samples = 0;
	lwjgl_glXGetConfig(display, vis_info, GLX_RED_SIZE, &r);
	lwjgl_glXGetConfig(display, vis_info, GLX_GREEN_SIZE, &g);
	lwjgl_glXGetConfig(display, vis_info, GLX_BLUE_SIZE, &b);
	lwjgl_glXGetConfig(display, vis_info, GLX_ALPHA_SIZE, &alpha);
	lwjgl_glXGetConfig(display, vis_info, GLX_DEPTH_SIZE, &depth);
	lwjgl_glXGetConfig(display, vis_info, GLX_STENCIL_SIZE, &stencil);
	if (extension_flags->GLX_ARB_multisample) {
		lwjgl_glXGetConfig(display, vis_info, GLX_SAMPLE_BUFFERS_ARB, &sample_buffers);
		lwjgl_glXGetConfig(display, vis_info, GLX_SAMPLES_ARB, &samples);
	}
	printfDebugJava(env, "Pixel format info: r = %d, g = %d, b = %d, a = %d, depth = %d, stencil = %d, sample buffers = %d, samples = %d", r, g, b, alpha, depth, stencil, sample_buffers, samples);
}

bool initPeerInfo(JNIEnv *env, jobject peer_info_handle, Display *display, int screen, jobject pixel_format, bool use_display_bpp, int drawable_type, bool double_buffered, bool force_glx13) {
	if ((*env)->GetDirectBufferCapacity(env, peer_info_handle) < sizeof(X11PeerInfo)) {
		throwException(env, "Handle too small");
		return false;
	}
	X11PeerInfo *peer_info = (*env)->GetDirectBufferAddress(env, peer_info_handle);
	GLXExtensions extension_flags;
	if (!extgl_InitGLX(display, screen, &extension_flags)) {
		throwException(env, "Could not init GLX");
		return false;
	}
	if (!extension_flags.GLX13 && force_glx13) {
		throwException(env, "GLX13 is required, but is not available");
		return false;
	}
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	if (samples > 0 && !extension_flags.GLX_ARB_multisample) {
		throwException(env, "Samples > 0 specified but there's no support for GLX_ARB_multisample");
		return false;
	}
	int colorSamples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "colorSamples", "I"));
	if (colorSamples > 0 && !extension_flags.GLX_NV_multisample_coverage) {
		throwException(env, "Color samples > 0 specified but there's no support for GLX_NV_multisample_coverage");
		return false;
	}
	bool floating_point = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point", "Z"));
	if (floating_point && !(extension_flags.GLX13 && extension_flags.GLX_ARB_fbconfig_float)) { // We need GLX13 to support floating point
		throwException(env, "Floating point specified but there's no support for GLX_ARB_fbconfig_float");
		return false;
	}
	bool floating_point_packed = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point_packed", "Z"));
	if (floating_point_packed && !(extension_flags.GLX13 && extension_flags.GLX_EXT_fbconfig_packed_float)) { // We need GLX13 to support packed floating point
		throwException(env, "Packed floating point specified but there's no support for GLX_EXT_fbconfig_packed_float");
		return false;
	}
	bool sRGB = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "sRGB", "Z"));
		if (sRGB && !extension_flags.GLX_ARB_framebuffer_sRGB) {
			throwException(env, "sRGB specified but there's no support for GLX_ARB_framebuffer_sRGB");
			return false;
	}

	peer_info->glx13 = extension_flags.GLX13;
	if (peer_info->glx13) {
		GLXFBConfig *configs = chooseVisualGLX13(env, display, screen, pixel_format, use_display_bpp, drawable_type, double_buffered);
		if (configs == NULL) {
			throwException(env, "Could not choose GLX13 config");
			return false;
		}
		if (isDebugEnabled()) {
			XVisualInfo *vis_info = lwjgl_glXGetVisualFromFBConfig(display, configs[0]);
			if (vis_info != NULL) {
				dumpVisualInfo(env, display, &extension_flags, vis_info);
				XFree(vis_info);
			}
		}
		int config_id;
		int result = lwjgl_glXGetFBConfigAttrib(display, configs[0], GLX_FBCONFIG_ID, &config_id);
		XFree(configs);
		if (result != Success) {
			throwException(env, "Could not get GLX_FBCONFIG_ID from GLXFBConfig");
			return false;
		}
		peer_info->config.glx13_config.config_id = config_id;
	} else {
		XVisualInfo *vis_info = chooseVisualGLX(env, display, screen, pixel_format, use_display_bpp, double_buffered);
		if (vis_info == NULL) {
			throwException(env, "Could not choose visual");
			return false;
		}
		peer_info->config.glx_config.visualid = vis_info->visualid;
		peer_info->config.glx_config.depth = vis_info->depth;
		peer_info->screen = vis_info->screen;
		if (isDebugEnabled())
			dumpVisualInfo(env, display, &extension_flags, vis_info);
		XFree(vis_info);
	}
	peer_info->display = display;
	peer_info->screen = screen;
	peer_info->drawable = None;
	return true;
}
