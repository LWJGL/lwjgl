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

#ifdef _AGL
aglChoosePixelFormatPROC aglChoosePixelFormat = NULL;
aglDestroyPixelFormatPROC aglDestroyPixelFormat = NULL;
aglNextPixelFormatPROC aglNextPixelFormat = NULL;
aglDescribePixelFormatPROC aglDescribePixelFormat = NULL;
aglDevicesOfPixelFormatPROC aglDevicesOfPixelFormat = NULL;
aglQueryRendererInfoPROC aglQueryRendererInfo = NULL;
aglDestroyRendererInfoPROC aglDestroyRendererInfo = NULL;
aglNextRendererInfoPROC aglNextRendererInfo = NULL;
aglDescribeRendererPROC aglDescribeRenderer = NULL;
aglCreateContextPROC aglCreateContext = NULL;
aglDestroyContextPROC aglDestroyContext = NULL;
aglCopyContextPROC aglCopyContext = NULL;
aglUpdateContextPROC aglUpdateContext = NULL;
aglSetCurrentContextPROC aglSetCurrentContext = NULL;
aglGetCurrentContextPROC aglGetCurrentContext = NULL;
aglSetDrawablePROC aglSetDrawable = NULL;
aglSetOffScreenPROC aglSetOffScreen = NULL;
aglSetFullScreenPROC aglSetFullScreen = NULL;
aglGetDrawablePROC aglGetDrawable = NULL;
aglSetVirtualScreenPROC aglSetVirtualScreen = NULL;
aglGetVirtualScreenPROC aglGetVirtualScreen = NULL;
aglGetVersionPROC aglGetVersion = NULL;
aglSwapBuffersPROC aglSwapBuffers = NULL;
aglEnablePROC aglEnable = NULL;
aglDisablePROC aglDisable = NULL;
aglIsEnabledPROC aglIsEnabled = NULL;
aglSetIntegerPROC aglSetInteger = NULL;
aglGetIntegerPROC aglGetInteger = NULL;
aglUseFontPROC aglUseFont = NULL;
aglGetErrorPROC aglGetError = NULL;
aglErrorStringPROC aglErrorString = NULL;
aglResetLibraryPROC aglResetLibrary = NULL;
aglSurfaceTexturePROC aglSurfaceTexture = NULL;
#endif


struct ExtensionTypes extgl_Extensions;

#ifdef _WIN32
HMODULE lib_gl_handle = NULL;
#endif

#ifdef _X11
void * lib_gl_handle = NULL;

typedef void * (APIENTRY * glXGetProcAddressARBPROC) (const GLubyte *procName);

static glXGetProcAddressARBPROC glXGetProcAddressARB;
#endif

#ifdef _AGL
CFBundleRef opengl_bundle_ref = NULL;
CFBundleRef agl_bundle_ref = NULL;
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

#ifdef _AGL
	CFStringRef str = CFStringCreateWithCStringNoCopy(NULL, name, kCFStringEncodingUTF8, kCFAllocatorNull);
	void *func_pointer = CFBundleGetFunctionPointerForName(opengl_bundle_ref, str);
	if (func_pointer == NULL) {
		func_pointer = CFBundleGetFunctionPointerForName(agl_bundle_ref, str);
		if (func_pointer == NULL) {
			printfDebug("Could not locate symbol %s\n", name);
		}
	}
	CFRelease(str);
	return func_pointer;
#endif
}

void extgl_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions) {
	ext_InitializeClass(env, clazz, &extgl_GetProcAddress, num_functions, functions);
}

bool extgl_InitializeFunctions(int num_functions, ExtFunction *functions) {
	return ext_InitializeFunctions(&extgl_GetProcAddress, num_functions, functions);
}

#ifdef _AGL
// -------------------------
static CFBundleRef loadBundle(const Str255 frameworkName)
{
	OSStatus err = noErr;
	FSRefParam fileRefParam;
	FSRef fileRef;
	CFURLRef bundleURLOpenGL;
	CFBundleRef bundle_ref;

	memset(&fileRefParam, 0, sizeof(fileRefParam));
	memset(&fileRef, 0, sizeof(fileRef));
	fileRefParam.ioNamePtr  = frameworkName;
	fileRefParam.newRef = &fileRef;

	// Frameworks directory/folder
	//
	err = FindFolder (kSystemDomain, kFrameworksFolderType, false, &fileRefParam.ioVRefNum, &fileRefParam.ioDirID);
	if (noErr != err)
	{
		printfDebug("Could not find frameworks folder\n");
		return NULL;
	}

	// make FSRef for folder
	//
	err = PBMakeFSRefSync (&fileRefParam);


	if (noErr != err)
	{
		printfDebug("Could make FSref to frameworks folder\n");
		return NULL;
	}

	// create URL to folder
	//
	bundleURLOpenGL = CFURLCreateFromFSRef (kCFAllocatorDefault, &fileRef);
	if (!bundleURLOpenGL)
	{
		printfDebug("Could create framework URL\n");
		return NULL;
	}

	bundle_ref = CFBundleCreate(kCFAllocatorDefault,bundleURLOpenGL);
	CFRelease (bundleURLOpenGL);
	if (bundle_ref == NULL)
	{
		printfDebug("Could not load framework\n");
		return NULL;
	}

	// if the code was successfully loaded, look for our function.
	if (!CFBundleLoadExecutable(bundle_ref))
	{
		printfDebug("Could not load MachO executable\n");
		CFRelease(bundle_ref);
		return NULL;
	}

	return bundle_ref;
}

static void aglUnloadFramework(CFBundleRef f)
{
	CFBundleUnloadExecutable(f);
	CFRelease(f);
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
/* AGL stuff BEGIN*/
/*-----------------------------------------------------*/
#ifdef _AGL

bool extgl_InitAGL(JNIEnv *env)
{
	aglChoosePixelFormat = (aglChoosePixelFormatPROC)extgl_GetProcAddress("aglChoosePixelFormat");
	aglDestroyPixelFormat = (aglDestroyPixelFormatPROC)extgl_GetProcAddress("aglDestroyPixelFormat");
	aglNextPixelFormat = (aglNextPixelFormatPROC)extgl_GetProcAddress("aglNextPixelFormat");
	aglDescribePixelFormat = (aglDescribePixelFormatPROC)extgl_GetProcAddress("aglDescribePixelFormat");
	aglDevicesOfPixelFormat = (aglDevicesOfPixelFormatPROC)extgl_GetProcAddress("aglDevicesOfPixelFormat");
	aglQueryRendererInfo = (aglQueryRendererInfoPROC)extgl_GetProcAddress("aglQueryRendererInfo");
	aglDestroyRendererInfo = (aglDestroyRendererInfoPROC)extgl_GetProcAddress("aglDestroyRendererInfo");
	aglNextRendererInfo = (aglNextRendererInfoPROC)extgl_GetProcAddress("aglNextRendererInfo");
	aglDescribeRenderer = (aglDescribeRendererPROC)extgl_GetProcAddress("aglDescribeRenderer");
	aglCreateContext = (aglCreateContextPROC)extgl_GetProcAddress("aglCreateContext");
	aglDestroyContext = (aglDestroyContextPROC)extgl_GetProcAddress("aglDestroyContext");
	aglCopyContext = (aglCopyContextPROC)extgl_GetProcAddress("aglCopyContext");
	aglUpdateContext = (aglUpdateContextPROC)extgl_GetProcAddress("aglUpdateContext");
	aglSetCurrentContext = (aglSetCurrentContextPROC)extgl_GetProcAddress("aglSetCurrentContext");
	aglGetCurrentContext = (aglGetCurrentContextPROC)extgl_GetProcAddress("aglGetCurrentContext");
	aglSetDrawable = (aglSetDrawablePROC)extgl_GetProcAddress("aglSetDrawable");
	aglSetOffScreen = (aglSetOffScreenPROC)extgl_GetProcAddress("aglSetOffScreen");
	aglSetFullScreen = (aglSetFullScreenPROC)extgl_GetProcAddress("aglSetFullScreen");
	aglGetDrawable = (aglGetDrawablePROC)extgl_GetProcAddress("aglGetDrawable");
	aglSetVirtualScreen = (aglSetVirtualScreenPROC)extgl_GetProcAddress("aglSetVirtualScreen");
	aglGetVirtualScreen = (aglGetVirtualScreenPROC)extgl_GetProcAddress("aglGetVirtualScreen");
	aglGetVersion = (aglGetVersionPROC)extgl_GetProcAddress("aglGetVersion");
	aglSwapBuffers = (aglSwapBuffersPROC)extgl_GetProcAddress("aglSwapBuffers");
	aglEnable = (aglEnablePROC)extgl_GetProcAddress("aglEnable");
	aglDisable = (aglDisablePROC)extgl_GetProcAddress("aglDisable");
	aglIsEnabled = (aglIsEnabledPROC)extgl_GetProcAddress("aglIsEnabled");
	aglSetInteger = (aglSetIntegerPROC)extgl_GetProcAddress("aglSetInteger");
	aglGetInteger = (aglGetIntegerPROC)extgl_GetProcAddress("aglGetInteger");
	aglUseFont = (aglUseFontPROC)extgl_GetProcAddress("aglUseFont");
	aglGetError = (aglGetErrorPROC)extgl_GetProcAddress("aglGetError");
	aglErrorString = (aglErrorStringPROC)extgl_GetProcAddress("aglErrorString");
	aglResetLibrary = (aglResetLibraryPROC)extgl_GetProcAddress("aglResetLibrary");
	aglSurfaceTexture = (aglSurfaceTexturePROC)extgl_GetProcAddress("aglSurfaceTexture");
	return !extgl_error; // Split out AGL into extgl_agl.cpp like wgl and glx and replace with InitializeFunctions
}

#endif
/*-----------------------------------------------------*/
/* AGL stuff END*/
/*-----------------------------------------------------*/

#ifdef _AGL
bool extgl_Open(void) {
	if (opengl_bundle_ref != NULL)
		return true;
	opengl_bundle_ref = loadBundle("\pOpenGL.framework");
	if (opengl_bundle_ref == NULL)
		return false;
	agl_bundle_ref = loadBundle("\pAGL.framework");
	if (agl_bundle_ref == NULL) {
		aglUnloadFramework(opengl_bundle_ref);
		return false;
	}
	return true;
}
#endif

#ifdef _X11
bool extgl_Open()
{
	if (lib_gl_handle != NULL)
		return true;
	lib_gl_handle = dlopen("libGL.so.1", RTLD_LAZY | RTLD_GLOBAL);
	if (lib_gl_handle == NULL) {
		printfDebug("Error loading libGL.so.1: %s\n", dlerror());
		return false;
	}
	glXGetProcAddressARB = (glXGetProcAddressARBPROC) dlsym(lib_gl_handle, "glXGetProcAddressARB");
	if (glXGetProcAddressARB == NULL) {
		extgl_Close();
		return false;
	}
	return true;
}

#endif /* X11 */

#ifdef _WIN32
bool extgl_Open(void)
{
	if (lib_gl_handle != NULL)
		return true;
	// load the dynamic libraries for OpenGL
	lib_gl_handle = LoadLibrary("opengl32.dll");
	if (lib_gl_handle == NULL)
		return false;
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
#ifdef _AGL
	aglUnloadFramework(opengl_bundle_ref);
	aglUnloadFramework(agl_bundle_ref);
	opengl_bundle_ref = NULL;
#endif
}

/* turn on the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn .8064
#pragma warn .8065
#endif /* __BORLANDC__	*/

