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
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#import "context.h"

#import <Cocoa/Cocoa.h>
#import <Carbon/Carbon.h>

#include <mach-o/dyld.h>
static const struct mach_header *opengl_lib_handle = NULL;

void *extgl_GetProcAddress(const char *name)
{
	#define BUFFER_SIZE 1024
	char mach_name[BUFFER_SIZE] = "_";
	strncat(mach_name, name, BUFFER_SIZE - 1);

	NSSymbol sym = NSLookupSymbolInImage(opengl_lib_handle, mach_name, NSLOOKUPSYMBOLINIMAGE_OPTION_BIND | NSLOOKUPSYMBOLINIMAGE_OPTION_RETURN_ON_ERROR);
	void *address = NSAddressOfSymbol(sym);
	if (address == NULL)
		printfDebug("Could not locate symbol %s\n", name);
	return address;
}

static const struct mach_header *loadImage(const char *lib_name) {   
	return NSAddImage(lib_name, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
}

bool extgl_Open(JNIEnv *env) {
	if (opengl_lib_handle != NULL)
		return true;
	opengl_lib_handle = loadImage("/System/Library/Frameworks/OpenGL.framework/Libraries/libGL.dylib");
	if (opengl_lib_handle != NULL) {
		return true;
	} else {
		throwException(env, "Could not load OpenGL library");
		return false;
	}
}

void extgl_Close(void)
{
	opengl_lib_handle = NULL;
}

NSOpenGLPixelFormat *choosePixelFormat(JNIEnv *env, jobject pixel_format, bool use_display_bpp, bool support_window, bool support_pbuffer, bool double_buffered) {
	int bpp;
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	if (use_display_bpp)
		bpp = CGDisplayBitsPerPixel(kCGDirectMainDisplay);
	else
		bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "bpp", "I"));
	
	int alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "alpha", "I"));
	int depth = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "depth", "I"));
	int stencil = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stencil", "I"));
	int samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	int num_aux_buffers = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_alpha", "I"));
	bool stereo = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stereo", "Z"));

	attrib_list_t attribs;
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Display.allowSoftwareOpenGL");
	initAttribList(&attribs);
	if (!allow_software_acceleration)
		putAttrib(&attribs, NSOpenGLPFAAccelerated);
	if (double_buffered)
		putAttrib(&attribs, NSOpenGLPFADoubleBuffer);
	putAttrib(&attribs, NSOpenGLPFAMinimumPolicy);
	putAttrib(&attribs, NSOpenGLPFAColorSize); putAttrib(&attribs, bpp);
	putAttrib(&attribs, NSOpenGLPFAAlphaSize); putAttrib(&attribs, alpha);
	putAttrib(&attribs, NSOpenGLPFADepthSize); putAttrib(&attribs, depth);
	putAttrib(&attribs, NSOpenGLPFAStencilSize); putAttrib(&attribs, stencil);
	putAttrib(&attribs, NSOpenGLPFAAccumSize); putAttrib(&attribs, accum_bpp + accum_alpha);
	putAttrib(&attribs, NSOpenGLPFASampleBuffers); putAttrib(&attribs, samples > 0 ? 1 : 0);
	putAttrib(&attribs, NSOpenGLPFASamples); putAttrib(&attribs, samples);
	putAttrib(&attribs, NSOpenGLPFAAuxBuffers); putAttrib(&attribs, num_aux_buffers);
	if (support_window)
		putAttrib(&attribs, NSOpenGLPFAWindow);
	if (support_pbuffer)
		putAttrib(&attribs, NSOpenGLPFAPixelBuffer);
	if (stereo)
		putAttrib(&attribs, NSOpenGLPFAStereo);
	putAttrib(&attribs, 0);
	NSOpenGLPixelFormat* fmt = [[NSOpenGLPixelFormat alloc] initWithAttributes:(NSOpenGLPixelFormatAttribute *)attribs.attribs];

	if (fmt == nil) {
		throwException(env, "Could not create pixel format");
		return NULL;
	}
	return fmt;
}
