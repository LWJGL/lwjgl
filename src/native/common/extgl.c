/* ----------------------------------------------------------------------------
Copyright (c) 2001-2002, Lev Povalahev
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

	* Redistributions of source code must retain the above copyright notice,
	  this list of conditions and the following disclaimer.
	* Redistributions in binary form must reproduce the above copyright notice,
	  this list of conditions and the following disclaimer in the documentation
	  and/or other materials provided with the distribution.
	* The name of the author may be used to endorse or promote products
	  derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
THE POSSIBILITY OF SUCH DAMAGE.
------------------------------------------------------------------------------*/
/*
	Lev Povalahev

	levp@gmx.net

	http://www.uni-karlsruhe.de/~uli2/

*/

#include <stdio.h>
#include <string.h>
#include "extgl.h"
#include "common_tools.h"

/* turn off the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn -8064
#pragma warn -8065
#endif /* __BORLANDC__	*/

#ifdef _X11

#include <dlfcn.h>
#endif

#ifndef _MACOSX
struct ExtensionTypes extgl_Extensions;
#endif

#ifdef _WIN32
HMODULE lib_gl_handle = NULL;
#endif

#ifdef _X11
void * lib_gl_handle = NULL;

typedef void * (APIENTRY * glXGetProcAddressARBPROC) (const GLubyte *procName);

static glXGetProcAddressARBPROC glXGetProcAddressARB;
#endif

#ifdef _MACOSX
#include <mach-o/dyld.h>
static const struct mach_header *opengl_lib_handle = NULL;
#endif

/* getProcAddress */

void *extgl_GetProcAddress(const char *name)
{
#ifdef _WIN32
	void *t = wglGetProcAddress(name);
	if (t == NULL)
	{
		t = GetProcAddress(lib_gl_handle, name);
		if (t == NULL)
		{
			printfDebug("Could not locate symbol %s\n", name);
		}
	}
	return t;
#endif

#ifdef _X11
	void *t = (void*)glXGetProcAddressARB((const GLubyte*)name);
	if (t == NULL)
	{
		t = dlsym(lib_gl_handle, name);
		if (t == NULL)
		{
			printfDebug("Could not locate symbol %s\n", name);
		}
	}
	return t;
#endif

#ifdef _MACOSX
	#define BUFFER_SIZE 1024
	char mach_name[BUFFER_SIZE] = "_";
	strncat(mach_name, name, BUFFER_SIZE - 1);

	if (NSIsSymbolNameDefinedInImage(opengl_lib_handle, mach_name)) {           
		NSSymbol sym = NSLookupSymbolInImage(opengl_lib_handle, mach_name, NSLOOKUPSYMBOLINIMAGE_OPTION_BIND | NSLOOKUPSYMBOLINIMAGE_OPTION_RETURN_ON_ERROR);
		void *address = NSAddressOfSymbol(sym);
		return address;
	} else {
		printfDebug("Could not locate symbol %s\n", name);
		return NULL;
	}
#endif
}

void extgl_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions) {
	ext_InitializeClass(env, clazz, &extgl_GetProcAddress, num_functions, functions);
}

bool extgl_InitializeFunctions(int num_functions, ExtFunction *functions) {
	return ext_InitializeFunctions(&extgl_GetProcAddress, num_functions, functions);
}

#ifdef _MACOSX
static const struct mach_header *loadImage(const char *lib_name) {   
	return NSAddImage(lib_name, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
}
#endif

bool extgl_QueryExtension(JNIEnv *env, const GLubyte*extensions, const char *name)
{
	const GLubyte *start;
	GLubyte *where, *terminator;

	if (extensions == NULL) {
		printfDebug("NULL extension string\n");
		return false;
	}

	/* Extension names should not have spaces. */
	where = (GLubyte *) strchr(name, ' ');
	if (where || *name == '\0')
		return false;

	/* It takes a bit of care to be fool-proof about parsing the
		 OpenGL extensions string. Don't be fooled by sub-strings,
		etc. */
	start = extensions;
	for (;;)
	{
		where = (GLubyte *) strstr((const char *) start, name);
		if (!where)
			break;
		terminator = where + strlen(name);
		if (where == start || *(where - 1) == ' ')
			if (*terminator == ' ' || *terminator == '\0') {
				return true;
			}
		start = terminator;
	}
	return false;

}

/*-----------------------------------------------------*/
/* AGL stuff END*/
/*-----------------------------------------------------*/

#ifdef _MACOSX
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
#endif

#ifdef _X11
bool extgl_Open(JNIEnv *env)
{
#define BUFFER_SIZE 2000
	static char buffer[BUFFER_SIZE];
	if (lib_gl_handle != NULL)
		return true;
	lib_gl_handle = dlopen("libGL.so.1", RTLD_LAZY | RTLD_GLOBAL);
	if (lib_gl_handle == NULL) {
		snprintf(buffer, BUFFER_SIZE, "Error loading libGL.so.1: %s", dlerror());
		buffer[BUFFER_SIZE - 1] = '\0';
		throwException(env, buffer);
		return false;
	}
	glXGetProcAddressARB = (glXGetProcAddressARBPROC)dlsym(lib_gl_handle, "glXGetProcAddressARB");
	if (glXGetProcAddressARB == NULL) {
		extgl_Close();
		throwException(env, "Could not get address of glXGetProcAddressARB");
		return false;
	}
	return true;
}

#endif /* X11 */

#ifdef _WIN32
bool extgl_Open(JNIEnv *env)
{
	if (lib_gl_handle != NULL)
		return true;
	// load the dynamic libraries for OpenGL
	lib_gl_handle = LoadLibrary("opengl32.dll");
	if (lib_gl_handle == NULL) {
		throwException(env, "Could not load OpenGL library");
		return false;
	}
	return true;
}
#endif /* WIN32 */

void extgl_Close(void)
{
#ifdef _X11
	dlclose(lib_gl_handle);
	lib_gl_handle = NULL;
#endif
#ifdef _WIN32
	FreeLibrary(lib_gl_handle);
	lib_gl_handle = NULL;
#endif
#ifdef _MACOSX
	opengl_lib_handle = NULL;
#endif
}

/* turn on the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn .8064
#pragma warn .8065
#endif /* __BORLANDC__	*/

