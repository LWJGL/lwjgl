#include <stdio.h>
#include <dlfcn.h>
#include "extxcursor.h"

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
        load_success = true;
	xcursor_handle = dlopen(xcursor_lib_name, RTLD_GLOBAL | RTLD_LAZY);
	if (xcursor_handle == NULL) {
		printf("Could not load %s: %s\n", xcursor_lib_name, dlerror());
		return false;
	}
	loadFunctionPointers();
	return load_success;
}

bool isXcursorLoaded(void) {
	return load_success;
}

void closeXcursor(void) {
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
