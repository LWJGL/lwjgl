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
 
#include <stdio.h>
#include <dlfcn.h>
#include "extxcursor.h"
#include "common_tools.h"

static void * xcursor_handle = NULL;
static const char *xcursor_lib_name = "libXcursor.so.1";
static bool load_success;

XcursorSupportsARGBPROC XcursorSupportsARGB = NULL;
XcursorSupportsAnimPROC XcursorSupportsAnim = NULL;
XcursorImageCreatePROC XcursorImageCreate = NULL;
XcursorImageDestroyPROC XcursorImageDestroy = NULL;
XcursorImagesCreatePROC XcursorImagesCreate = NULL;
XcursorImagesDestroyPROC XcursorImagesDestroy = NULL;
XcursorImagesLoadCursorPROC XcursorImagesLoadCursor = NULL;

static void * loadHandle(const char * func_name) {
	void * func_pointer = dlsym(xcursor_handle, func_name);
	if (func_pointer == NULL) {
		load_success = false;
		return NULL;
	}
	return func_pointer;
}	

static bool loadFunctionPointers(void) {
        load_success = true;
	XcursorSupportsARGB = (XcursorSupportsARGBPROC)loadHandle("XcursorSupportsARGB");
	XcursorSupportsAnim = (XcursorSupportsAnimPROC)loadHandle("XcursorSupportsAnim");
	XcursorImageCreate = (XcursorImageCreatePROC)loadHandle("XcursorImageCreate");
	XcursorImageDestroy = (XcursorImageDestroyPROC)loadHandle("XcursorImageDestroy");
	XcursorImagesCreate = (XcursorImagesCreatePROC)loadHandle("XcursorImagesCreate");
	XcursorImagesDestroy = (XcursorImagesDestroyPROC)loadHandle("XcursorImagesDestroy");
	XcursorImagesLoadCursor = (XcursorImagesLoadCursorPROC)loadHandle("XcursorImagesLoadCursor");
	return load_success;
}

bool loadXcursor(void) {
	load_success = false;
	xcursor_handle = dlopen(xcursor_lib_name, RTLD_GLOBAL | RTLD_LAZY);
	if (xcursor_handle == NULL) {
		printfDebug("Could not load %s: %s\n", xcursor_lib_name, dlerror());
		return load_success;
	}
	loadFunctionPointers();
	return load_success;
}

bool isXcursorLoaded(void) {
	return load_success;
}

void closeXcursor(void) {
	if (load_success) {
		load_success = false;
		dlclose(xcursor_handle);
		xcursor_handle = NULL;
		XcursorSupportsARGB = NULL;
		XcursorSupportsAnim = NULL;
		XcursorImageCreate = NULL;
		XcursorImageDestroy = NULL;
		XcursorImagesCreate = NULL;
		XcursorImagesDestroy = NULL;
		XcursorImagesLoadCursor = NULL;
	}
}
