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

/*-------------------------------------*/
/* WGL stuff */
/*-------------------------------------*/

#ifdef _WIN32

/* WGL_EXT_etxension_string */

wglGetExtensionsStringEXTPROC wglGetExtensionsStringEXT = NULL;

/* WGL_ARB_buffer_region */

/*wglCreateBufferRegionARBPROC wglCreateBufferRegionARB = NULL;
wglDeleteBufferRegionARBPROC wglDeleteBufferRegionARB = NULL;
wglSaveBufferRegionARBPROC wglSaveBufferRegionARB = NULL;
wglRestoreBufferRegionARBPROC wglRestoreBufferRegionARB = NULL;
*/
/* WGL_ARB_extension_string */


wglGetExtensionsStringARBPROC wglGetExtensionsStringARB = NULL;

/* WGL_ARB_pbuffer */

wglCreatePbufferARBPROC wglCreatePbufferARB = NULL;
wglGetPbufferDCARBPROC wglGetPbufferDCARB = NULL;
wglReleasePbufferDCARBPROC wglReleasePbufferDCARB = NULL;
wglDestroyPbufferARBPROC wglDestroyPbufferARB = NULL;
wglQueryPbufferARBPROC wglQueryPbufferARB = NULL;

/* WGL_ARB_pixel_format */

wglGetPixelFormatAttribivARBPROC wglGetPixelFormatAttribivARB = NULL;
wglGetPixelFormatAttribfvARBPROC wglGetPixelFormatAttribfvARB = NULL;
wglChoosePixelFormatARBPROC wglChoosePixelFormatARB = NULL;

/* WGL_ARB_render_texture */

wglBindTexImageARBPROC wglBindTexImageARB = NULL;
wglReleaseTexImageARBPROC wglReleaseTexImageARB = NULL;
wglSetPbufferAttribARBPROC wglSetPbufferAttribARB = NULL;

/* WGL_EXT_swap_control */

wglSwapIntervalEXTPROC wglSwapIntervalEXT = NULL;
wglGetSwapIntervalEXTPROC wglGetSwapIntervalEXT = NULL;

/* WGL_ARB_make_current_read */

wglMakeContextCurrentARBPROC wglMakeContextCurrentARB = NULL;
wglGetCurrentReadDCARBPROC wglGetCurrentReadDCARB = NULL;

/* VAR */

#endif /* WIN32 */

/*-------------------------------------*/
/*---WGL STUFF END---------------------*/
/*-------------------------------------*/


static bool extgl_error = false;

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
			extgl_error = true;
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
			extgl_error = true;
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
			extgl_error = true;
		}
	}
	CFRelease(str);
	return func_pointer;
#endif
}

bool extgl_InitializeClass(JNIEnv *env, jclass clazz, jobject ext_set, const char *ext_name, int num_functions, JavaMethodAndExtFunction *functions) {
	return ext_InitializeClass(env, clazz, ext_set, ext_name, &extgl_GetProcAddress, num_functions, functions);
}

bool extgl_InitializeFunctions(int num_functions, ExtFunction *functions) {
	return ext_InitializeFunctions(&extgl_GetProcAddress, num_functions, functions);
}

static void insertExtension(JNIEnv *env, jobject ext_set, const char *ext) {
	doExtension(env, ext_set, "add", ext);
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

bool extgl_QueryExtension(JNIEnv *env, jobject ext_set, const GLubyte*extensions, const char *name)
{
	const GLubyte *start;
	GLubyte *where, *terminator;

	if (extensions == NULL) {
		printfDebug("NULL extension string\n");
		extgl_error = true;
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
				if (ext_set != NULL) { 
					insertExtension(env, ext_set, name);
				}
				return true;
			}
		start = terminator;
	}
	return false;

}

/*-----------------------------------------------------*/
/* WGL stuff */
/*-----------------------------------------------------*/

#ifdef _WIN32

/** returns true if the extention is available */
static bool WGLQueryExtension(JNIEnv *env, const char *name)
{
	const GLubyte *extensions;

	if (wglGetExtensionsStringARB == NULL)
		if (wglGetExtensionsStringEXT == NULL)
			return false;
		else
			extensions = (GLubyte*)wglGetExtensionsStringEXT();
	else
		extensions = (GLubyte*)wglGetExtensionsStringARB(wglGetCurrentDC());
	return extgl_QueryExtension(env, NULL, extensions, name);
}

/*static void extgl_InitWGLARBBufferRegion(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_buffer_region)
		return;
	wglCreateBufferRegionARB = (wglCreateBufferRegionARBPROC) extgl_GetProcAddress("wglCreateBufferRegionARB");
	wglDeleteBufferRegionARB = (wglDeleteBufferRegionARBPROC) extgl_GetProcAddress("wglDeleteBufferRegionARB");
	wglSaveBufferRegionARB = (wglSaveBufferRegionARBPROC) extgl_GetProcAddress("wglSaveBufferRegionARB");
	wglRestoreBufferRegionARB = (wglRestoreBufferRegionARBPROC) extgl_GetProcAddress("wglRestoreBufferRegionARB");

	EXTGL_SANITY_CHECK(env, WGL_ARB_buffer_region);
}
*/
static void extgl_InitWGLARBPbuffer(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_pbuffer)
		return;
	wglCreatePbufferARB = (wglCreatePbufferARBPROC) extgl_GetProcAddress("wglCreatePbufferARB");
	wglGetPbufferDCARB = (wglGetPbufferDCARBPROC) extgl_GetProcAddress("wglGetPbufferDCARB");
	wglReleasePbufferDCARB = (wglReleasePbufferDCARBPROC) extgl_GetProcAddress("wglReleasePbufferDCARB");
	wglDestroyPbufferARB = (wglDestroyPbufferARBPROC) extgl_GetProcAddress("wglDestroyPbufferARB");
	wglQueryPbufferARB = (wglQueryPbufferARBPROC) extgl_GetProcAddress("wglQueryPbufferARB");
	EXTGL_SANITY_CHECK(env, WGL_ARB_pbuffer);

}

static void extgl_InitWGLARBPixelFormat(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_pixel_format)
		return;
	wglGetPixelFormatAttribivARB = (wglGetPixelFormatAttribivARBPROC) extgl_GetProcAddress("wglGetPixelFormatAttribivARB");
	wglGetPixelFormatAttribfvARB = (wglGetPixelFormatAttribfvARBPROC) extgl_GetProcAddress("wglGetPixelFormatAttribfvARB");
	wglChoosePixelFormatARB = (wglChoosePixelFormatARBPROC) extgl_GetProcAddress("wglChoosePixelFormatARB");
	EXTGL_SANITY_CHECK(env, WGL_ARB_pixel_format);

}

static void extgl_InitWGLARBRenderTexture(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_render_texture)
		return;
	wglBindTexImageARB = (wglBindTexImageARBPROC) extgl_GetProcAddress("wglBindTexImageARB");
	wglReleaseTexImageARB = (wglReleaseTexImageARBPROC) extgl_GetProcAddress("wglReleaseTexImageARB");
	wglSetPbufferAttribARB = (wglSetPbufferAttribARBPROC) extgl_GetProcAddress("wglSetPbufferAttribARB");
	EXTGL_SANITY_CHECK(env, WGL_ARB_render_texture);

}

static void extgl_InitWGLEXTSwapControl(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_EXT_swap_control)
		return;
	wglSwapIntervalEXT = (wglSwapIntervalEXTPROC) extgl_GetProcAddress("wglSwapIntervalEXT");
	wglGetSwapIntervalEXT = (wglGetSwapIntervalEXTPROC) extgl_GetProcAddress("wglGetSwapIntervalEXT");
	EXTGL_SANITY_CHECK(env, WGL_EXT_swap_control);

}

static void extgl_InitWGLARBMakeCurrentRead(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_make_current_read)
		return;
	wglMakeContextCurrentARB = (wglMakeContextCurrentARBPROC) extgl_GetProcAddress("wglMakeContextCurrentARB");
	wglGetCurrentReadDCARB = (wglGetCurrentReadDCARBPROC) extgl_GetProcAddress("wglGetCurrentReadDCARB");
	EXTGL_SANITY_CHECK(env, WGL_ARB_make_current_read);

}

static void extgl_InitSupportedWGLExtensions(JNIEnv *env)
{
	extgl_Extensions.WGL_ARB_buffer_region = WGLQueryExtension(env, "WGL_ARB_buffer_region");
	extgl_Extensions.WGL_ARB_make_current_read = WGLQueryExtension(env, "WGL_ARB_make_current_read");
	extgl_Extensions.WGL_ARB_multisample = WGLQueryExtension(env, "WGL_ARB_multisample");
	extgl_Extensions.WGL_ARB_pbuffer = WGLQueryExtension(env, "WGL_ARB_pbuffer");
	extgl_Extensions.WGL_ARB_pixel_format = WGLQueryExtension(env, "WGL_ARB_pixel_format");
	extgl_Extensions.WGL_ARB_render_texture = WGLQueryExtension(env, "WGL_ARB_render_texture");
	extgl_Extensions.WGL_EXT_swap_control = WGLQueryExtension(env, "WGL_EXT_swap_control");
	extgl_Extensions.WGL_NV_render_depth_texture = WGLQueryExtension(env, "WGL_NV_render_depth_texture");
	extgl_Extensions.WGL_NV_render_texture_rectangle = WGLQueryExtension(env, "WGL_NV_render_texture_rectangle");
}

void extgl_InitWGL(JNIEnv *env)
{
	wglGetExtensionsStringARB = (wglGetExtensionsStringARBPROC) extgl_GetProcAddress("wglGetExtensionsStringARB");
	wglGetExtensionsStringEXT = (wglGetExtensionsStringEXTPROC) extgl_GetProcAddress("wglGetExtensionsStringEXT");
	extgl_Extensions.WGL_ARB_extensions_string = wglGetExtensionsStringARB != NULL;
	extgl_Extensions.WGL_EXT_extensions_string = wglGetExtensionsStringEXT != NULL;
	extgl_error = false;

	extgl_InitSupportedWGLExtensions(env);

	extgl_InitWGLARBMakeCurrentRead(env);
	extgl_InitWGLEXTSwapControl(env);
	extgl_InitWGLARBRenderTexture(env);
	extgl_InitWGLARBPixelFormat(env);
	extgl_InitWGLARBPbuffer(env);
	//extgl_InitWGLARBBufferRegion(env);
}

#endif /* WIN32 */

/*-----------------------------------------------------*/
/* WGL stuff END*/
/*-----------------------------------------------------*/

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
	return !extgl_error;
}

#endif
/*-----------------------------------------------------*/
/* AGL stuff END*/
/*-----------------------------------------------------*/

/** returns true if the extention is available */
static bool GLQueryExtension(JNIEnv *env, jobject ext_set, const char *name)
{
	return extgl_QueryExtension(env, ext_set, glGetString(GL_EXTENSIONS), name);
}

static void extgl_InitSupportedExtensions(JNIEnv *env, jobject ext_set)
{
	char *s = (char*) glGetString(GL_VERSION);
	if (!s)
		return;
	s = strstr(s, "1.");
	extgl_Extensions.OpenGL12 = false;
	extgl_Extensions.OpenGL13 = false;
	extgl_Extensions.OpenGL14 = false;
	extgl_Extensions.OpenGL15 = false;
	if (s != NULL)
	{
		// Fall trhough
		switch (s[2]) {
			case '5':
				extgl_Extensions.OpenGL15 = true;
				insertExtension(env, ext_set, "OpenGL15");
			case '4':
				extgl_Extensions.OpenGL14 = true;
				insertExtension(env, ext_set, "OpenGL14");
			case '3':
				extgl_Extensions.OpenGL13 = true;
				insertExtension(env, ext_set, "OpenGL13");
			case '2':
				extgl_Extensions.OpenGL12 = true;
				insertExtension(env, ext_set, "OpenGL12");
				break;
		}
	}

	extgl_Extensions.GL_ARB_depth_texture = GLQueryExtension(env, ext_set, "GL_ARB_depth_texture");

	extgl_Extensions.GL_ARB_imaging = GLQueryExtension(env, ext_set, "GL_ARB_imaging");
	extgl_Extensions.GL_ARB_depth_texture = GLQueryExtension(env, ext_set, "GL_ARB_depth_texture");
	extgl_Extensions.GL_ARB_fragment_program = GLQueryExtension(env, ext_set, "GL_ARB_fragment_program");
	extgl_Extensions.GL_ARB_fragment_program_shadow = GLQueryExtension(env, ext_set, "GL_ARB_fragment_program_shadow");
	extgl_Extensions.GL_ARB_fragment_shader = GLQueryExtension(env, ext_set, "GL_ARB_fragment_shader");
	extgl_Extensions.GL_ARB_matrix_palette = GLQueryExtension(env, ext_set, "GL_ARB_matrix_palette");
	extgl_Extensions.GL_ARB_multisample = GLQueryExtension(env, ext_set, "GL_ARB_multisample");
	extgl_Extensions.GL_ARB_multitexture = GLQueryExtension(env, ext_set, "GL_ARB_multitexture");
	extgl_Extensions.GL_ARB_occlusion_query = GLQueryExtension(env, ext_set, "GL_ARB_occlusion_query");
	extgl_Extensions.GL_ARB_point_parameters = GLQueryExtension(env, ext_set, "GL_ARB_point_parameters");
	extgl_Extensions.GL_ARB_point_sprite = GLQueryExtension(env, ext_set, "GL_ARB_point_sprite");
	extgl_Extensions.GL_ARB_shading_language_100 = GLQueryExtension(env, ext_set, "GL_ARB_shading_language_100");
	extgl_Extensions.GL_ARB_shader_objects = GLQueryExtension(env, ext_set, "GL_ARB_shader_objects");
	extgl_Extensions.GL_ARB_shadow = GLQueryExtension(env, ext_set, "GL_ARB_shadow");
	extgl_Extensions.GL_ARB_shadow_ambient = GLQueryExtension(env, ext_set, "GL_ARB_shadow_ambient");
	extgl_Extensions.GL_ARB_texture_border_clamp = GLQueryExtension(env, ext_set, "GL_ARB_texture_border_clamp");
	extgl_Extensions.GL_ARB_texture_compression = GLQueryExtension(env, ext_set, "GL_ARB_texture_compression");
	extgl_Extensions.GL_ARB_texture_cube_map = GLQueryExtension(env, ext_set, "GL_ARB_texture_cube_map");
	extgl_Extensions.GL_ARB_texture_env_add = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_add");
	extgl_Extensions.GL_ARB_texture_env_combine = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_combine");
	extgl_Extensions.GL_ARB_texture_env_crossbar = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_crossbar");
	extgl_Extensions.GL_ARB_texture_env_dot3 = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_dot3");
	extgl_Extensions.GL_ARB_texture_mirrored_repeat = GLQueryExtension(env, ext_set, "GL_ARB_texture_mirrored_repeat");
	extgl_Extensions.GL_ARB_texture_non_power_of_two = GLQueryExtension(env, ext_set, "GL_ARB_texture_non_power_of_two");
	extgl_Extensions.GL_ARB_transpose_matrix = GLQueryExtension(env, ext_set, "GL_ARB_transpose_matrix");
	extgl_Extensions.GL_ARB_vertex_blend = GLQueryExtension(env, ext_set, "GL_ARB_vertex_blend");
	extgl_Extensions.GL_ARB_vertex_buffer_object = GLQueryExtension(env, ext_set, "GL_ARB_vertex_buffer_object");
	extgl_Extensions.GL_ARB_vertex_program = GLQueryExtension(env, ext_set, "GL_ARB_vertex_program");
	extgl_Extensions.GL_ARB_vertex_shader = GLQueryExtension(env, ext_set, "GL_ARB_vertex_shader");
	extgl_Extensions.GL_ARB_window_pos = GLQueryExtension(env, ext_set, "GL_ARB_window_pos");

	extgl_Extensions.GL_EXT_abgr = GLQueryExtension(env, ext_set, "GL_EXT_abgr");
	extgl_Extensions.GL_EXT_bgra = GLQueryExtension(env, ext_set, "GL_EXT_bgra");
	extgl_Extensions.GL_EXT_blend_func_separate = GLQueryExtension(env, ext_set, "GL_EXT_blend_func_separate");
	extgl_Extensions.GL_EXT_blend_subtract = GLQueryExtension(env, ext_set, "GL_EXT_blend_subtract");
	extgl_Extensions.GL_EXT_compiled_vertex_array = GLQueryExtension(env, ext_set, "GL_EXT_compiled_vertex_array");
	extgl_Extensions.GL_EXT_draw_range_elements = GLQueryExtension(env, ext_set, "GL_EXT_draw_range_elements");
	extgl_Extensions.GL_EXT_fog_coord = GLQueryExtension(env, ext_set, "GL_EXT_fog_coord");
	extgl_Extensions.GL_EXT_multi_draw_arrays = GLQueryExtension(env, ext_set, "GL_EXT_multi_draw_arrays");
	extgl_Extensions.GL_EXT_packed_pixels = GLQueryExtension(env, ext_set, "GL_EXT_packed_pixels");
	extgl_Extensions.GL_EXT_point_parameters = GLQueryExtension(env, ext_set, "GL_EXT_point_parameters");
	extgl_Extensions.GL_EXT_rescale_normal = GLQueryExtension(env, ext_set, "GL_EXT_rescale_normal");
	extgl_Extensions.GL_EXT_secondary_color = GLQueryExtension(env, ext_set, "GL_EXT_secondary_color");
	extgl_Extensions.GL_EXT_separate_specular_color = GLQueryExtension(env, ext_set, "GL_EXT_separate_specular_color");
	extgl_Extensions.GL_EXT_shadow_funcs = GLQueryExtension(env, ext_set, "GL_EXT_shadow_funcs");
	extgl_Extensions.GL_EXT_shared_texture_palette = GLQueryExtension(env, ext_set, "GL_EXT_shared_texture_palette");
	extgl_Extensions.GL_EXT_stencil_two_side = GLQueryExtension(env, ext_set, "GL_EXT_stencil_two_side");
	extgl_Extensions.GL_EXT_stencil_wrap = GLQueryExtension(env, ext_set, "GL_EXT_stencil_wrap");
	extgl_Extensions.GL_EXT_texture_compression_s3tc = GLQueryExtension(env, ext_set, "GL_EXT_texture_compression_s3tc");
	extgl_Extensions.GL_EXT_texture_env_combine = GLQueryExtension(env, ext_set, "GL_EXT_texture_env_combine");
	extgl_Extensions.GL_EXT_texture_env_dot3 = GLQueryExtension(env, ext_set, "GL_EXT_texture_env_dot3");
	extgl_Extensions.GL_EXT_texture_filter_anisotropic = GLQueryExtension(env, ext_set, "GL_EXT_texture_filter_anisotropic");
	extgl_Extensions.GL_EXT_texture_lod_bias = GLQueryExtension(env, ext_set, "GL_EXT_texture_lod_bias");
	extgl_Extensions.GL_EXT_texture_rectangle = GLQueryExtension(env, ext_set, "GL_EXT_texture_rectangle");
	extgl_Extensions.GL_EXT_vertex_shader = GLQueryExtension(env, ext_set, "GL_EXT_vertex_shader");
	extgl_Extensions.GL_EXT_vertex_weighting = GLQueryExtension(env, ext_set, "GL_EXT_vertex_weighting");

	extgl_Extensions.GL_ATI_draw_buffers = GLQueryExtension(env, ext_set, "GL_ATI_draw_buffers");
	extgl_Extensions.GL_ATI_element_array = GLQueryExtension(env, ext_set, "GL_ATI_element_array");
	extgl_Extensions.GL_ATI_envmap_bumpmap = GLQueryExtension(env, ext_set, "GL_ATI_envmap_bumpmap");
	extgl_Extensions.GL_ATI_fragment_shader = GLQueryExtension(env, ext_set, "GL_ATI_fragment_shader");
	extgl_Extensions.GL_ATI_map_object_buffer = GLQueryExtension(env, ext_set, "GL_ATI_map_object_buffer");
	extgl_Extensions.GL_ATI_pn_triangles = GLQueryExtension(env, ext_set, "GL_ATI_pn_triangles");
	extgl_Extensions.GL_ATI_separate_stencil = GLQueryExtension(env, ext_set, "GL_ATI_separate_stencil");
	extgl_Extensions.GL_ATI_texture_float = GLQueryExtension(env, ext_set, "GL_ATI_texture_float");
	extgl_Extensions.GL_ATI_texture_mirror_once = GLQueryExtension(env, ext_set, "GL_ATI_texture_mirror_once");
	extgl_Extensions.GL_ATI_vertex_array_object = GLQueryExtension(env, ext_set, "GL_ATI_vertex_array_object");
	extgl_Extensions.GL_ATI_vertex_streams = GLQueryExtension(env, ext_set, "GL_ATI_vertex_streams");
	extgl_Extensions.GL_ATI_vertex_attrib_array_object = GLQueryExtension(env, ext_set, "GL_ATI_vertex_attrib_array_object");

	extgl_Extensions.GL_NV_blend_square = GLQueryExtension(env, ext_set, "GL_NV_blend_square");
	extgl_Extensions.GL_NV_copy_depth_to_color = GLQueryExtension(env, ext_set, "GL_NV_copy_depth_to_color");
	extgl_Extensions.GL_NV_depth_clamp = GLQueryExtension(env, ext_set, "GL_NV_depth_clamp");
	extgl_Extensions.GL_NV_evaluators = GLQueryExtension(env, ext_set, "GL_NV_evaluators");
	extgl_Extensions.GL_NV_fence = GLQueryExtension(env, ext_set, "GL_NV_fence");
	extgl_Extensions.GL_NV_float_buffer = GLQueryExtension(env, ext_set, "GL_NV_float_buffer");
	extgl_Extensions.GL_NV_fog_distance = GLQueryExtension(env, ext_set, "GL_NV_fog_distance");
	extgl_Extensions.GL_NV_fragment_program = GLQueryExtension(env, ext_set, "GL_NV_fragment_program");
	extgl_Extensions.GL_NV_half_float = GLQueryExtension(env, ext_set, "GL_NV_half_float");
	extgl_Extensions.GL_NV_light_max_exponent = GLQueryExtension(env, ext_set, "GL_NV_light_max_exponent");
	extgl_Extensions.GL_NV_multisample_filter_hint = GLQueryExtension(env, ext_set, "GL_NV_multisample_filter_hint");
	extgl_Extensions.GL_NV_occlusion_query = GLQueryExtension(env, ext_set, "GL_NV_occlusion_query");
	extgl_Extensions.GL_NV_packed_depth_stencil = GLQueryExtension(env, ext_set, "GL_NV_packed_depth_stencil");
	extgl_Extensions.GL_NV_pixel_data_range = GLQueryExtension(env, ext_set, "GL_NV_pixel_data_range");
	extgl_Extensions.GL_NV_point_sprite = GLQueryExtension(env, ext_set, "GL_NV_point_sprite");
	extgl_Extensions.GL_NV_primitive_restart = GLQueryExtension(env, ext_set, "GL_NV_primitive_restart");
	extgl_Extensions.GL_NV_register_combiners = GLQueryExtension(env, ext_set, "GL_NV_register_combiners");
	extgl_Extensions.GL_NV_register_combiners2 = GLQueryExtension(env, ext_set, "GL_NV_register_combiners2");
	extgl_Extensions.GL_NV_texgen_reflection = GLQueryExtension(env, ext_set, "GL_NV_texgen_reflection");
	extgl_Extensions.GL_NV_texture_compression_vtc = GLQueryExtension(env, ext_set, "GL_NV_texture_compression_vtc");
	extgl_Extensions.GL_NV_texture_env_combine4 = GLQueryExtension(env, ext_set, "GL_NV_texture_env_combine4");
	extgl_Extensions.GL_NV_texture_expand_normal = GLQueryExtension(env, ext_set, "GL_NV_texture_expand_normal");
	extgl_Extensions.GL_NV_texture_rectangle = GLQueryExtension(env, ext_set, "GL_NV_texture_rectangle");
	extgl_Extensions.GL_NV_texture_shader = GLQueryExtension(env, ext_set, "GL_NV_texture_shader");
	extgl_Extensions.GL_NV_texture_shader2 = GLQueryExtension(env, ext_set, "GL_NV_texture_shader2");
	extgl_Extensions.GL_NV_texture_shader3 = GLQueryExtension(env, ext_set, "GL_NV_texture_shader3");
	extgl_Extensions.GL_NV_vertex_array_range = GLQueryExtension(env, ext_set, "GL_NV_vertex_array_range");
	extgl_Extensions.GL_NV_vertex_array_range2 = GLQueryExtension(env, ext_set, "GL_NV_vertex_array_range2");
	extgl_Extensions.GL_NV_vertex_program = GLQueryExtension(env, ext_set, "GL_NV_vertex_program");
	extgl_Extensions.GL_NV_vertex_program1_1 = GLQueryExtension(env, ext_set, "GL_NV_vertex_program1_1");
	extgl_Extensions.GL_NV_vertex_program2 = GLQueryExtension(env, ext_set, "GL_NV_vertex_program2");
}

extern bool extgl_InitOpenGL1_1(JNIEnv *env);
//extern void extgl_InitARBFragmentProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBImaging(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBMatrixPalette(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBMultisample(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBMultitexture(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBOcclusionQuery(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBPointParameters(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBShaderObjects(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBTextureCompression(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBTransposeMatrix(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexBlend(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexBufferObject(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexShader(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBWindowPos(JNIEnv *env, jobject ext_set);

extern void extgl_InitEXTBlendFuncSeparate(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTCompiledVertexArray(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTDrawRangeElements(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTFogCoord(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTMultiDrawArrays(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTPointParameters(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTSecondaryColor(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTStencilTwoSide(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTVertexShader(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTVertexWeighting(JNIEnv *env, jobject ext_set);

extern void extgl_InitNVEvaluators(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVFragmentProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVFence(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVHalfFloat(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVOcclusionQuery(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVPixelDataRange(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVPointSprite(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVPrimitiveRestart(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVRegisterCombiners(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVRegisterCombiners2(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVVertexArrayRange(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVVertexProgram(JNIEnv *env, jobject ext_set);

extern void extgl_InitATIDrawBuffers(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIElementArray(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIEnvmapBumpmap(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIFragmentShader(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIMapObjectBuffer(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIPNTriangles(JNIEnv *env, jobject ext_set);
extern void extgl_InitATISeparateStencil(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIVertexArrayObject(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIVertexAttribArrayObject(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIVertexStreams(JNIEnv *env, jobject ext_set);

extern void extgl_InitOpenGL1_2(JNIEnv *env, jobject ext_set);
extern void extgl_InitOpenGL1_3(JNIEnv *env, jobject ext_set);
extern void extgl_InitOpenGL1_4(JNIEnv *env, jobject ext_set);
extern void extgl_InitOpenGL1_5(JNIEnv *env, jobject ext_set);

/* extgl_Init the extensions and load all the functions */
bool extgl_Initialize(JNIEnv *env, jobject ext_set)
{
	extgl_error = false;
	bool result = extgl_InitOpenGL1_1(env);
	if (!result)
		return false;

	extgl_InitSupportedExtensions(env, ext_set);

	//extgl_InitEXTNurbsTesselator(env, ext_set);

	/* first load the extensions */
	//	extgl_InitARBFragmentProgram(env, ext_set);
	extgl_InitARBImaging(env, ext_set);
	extgl_InitARBMatrixPalette(env, ext_set);
	extgl_InitARBMultisample(env, ext_set);
	extgl_InitARBMultitexture(env, ext_set);
	extgl_InitARBOcclusionQuery(env, ext_set);
	extgl_InitARBPointParameters(env, ext_set);
	extgl_InitARBProgram(env, ext_set);
	extgl_InitARBShaderObjects(env, ext_set);
	extgl_InitARBTextureCompression(env, ext_set);
	extgl_InitARBTransposeMatrix(env, ext_set);
	extgl_InitARBVertexBlend(env, ext_set);
	extgl_InitARBVertexBufferObject(env, ext_set);
	extgl_InitARBVertexProgram(env, ext_set);
	extgl_InitARBVertexShader(env, ext_set);
	extgl_InitARBWindowPos(env, ext_set);

	extgl_InitEXTBlendFuncSeparate(env, ext_set);
	extgl_InitEXTCompiledVertexArray(env, ext_set);
	//extgl_InitEXTCullVertex(env, ext_set);
	extgl_InitEXTDrawRangeElements(env, ext_set);
	extgl_InitEXTFogCoord(env, ext_set);
	extgl_InitEXTMultiDrawArrays(env, ext_set);
	extgl_InitEXTPointParameters(env, ext_set);
	extgl_InitEXTSecondaryColor(env, ext_set);
	extgl_InitEXTStencilTwoSide(env, ext_set);
	extgl_InitEXTVertexShader(env, ext_set);
	extgl_InitEXTVertexWeighting(env, ext_set);

	//extgl_InitNVElementArray(env, ext_set);
	extgl_InitNVEvaluators(env, ext_set);
	extgl_InitNVFence(env, ext_set);
	extgl_InitNVFragmentProgram(env, ext_set);
	extgl_InitNVHalfFloat(env, ext_set);
	extgl_InitNVOcclusionQuery(env, ext_set);
	extgl_InitNVPixelDataRange(env, ext_set);
	extgl_InitNVPointSprite(env, ext_set);
	extgl_InitNVPrimitiveRestart(env, ext_set);
	extgl_InitNVProgram(env, ext_set);
	extgl_InitNVRegisterCombiners(env, ext_set);
	extgl_InitNVRegisterCombiners2(env, ext_set);
	extgl_InitNVVertexArrayRange(env, ext_set);
	extgl_InitNVVertexProgram(env, ext_set);
	extgl_InitATIDrawBuffers(env, ext_set);
	extgl_InitATIElementArray(env, ext_set);
	extgl_InitATIEnvmapBumpmap(env, ext_set);
	extgl_InitATIFragmentShader(env, ext_set);
	extgl_InitATIMapObjectBuffer(env, ext_set);
	extgl_InitATIPNTriangles(env, ext_set);
	extgl_InitATISeparateStencil(env, ext_set);
	extgl_InitATIVertexArrayObject(env, ext_set);
	extgl_InitATIVertexAttribArrayObject(env, ext_set);
	extgl_InitATIVertexStreams(env, ext_set);

   /* now load core opengl */
	extgl_InitOpenGL1_2(env, ext_set);
	extgl_InitOpenGL1_3(env, ext_set);
	extgl_InitOpenGL1_4(env, ext_set);
	extgl_InitOpenGL1_5(env, ext_set);

	return true;
}

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

