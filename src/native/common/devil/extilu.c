/* Handle to ilu Library */
#ifdef _WIN32
static HMODULE devILUhandle;
#endif
#ifdef _X11
static void* devILUhandle;
#endif
#ifdef _MACOSX
#include <mach-o/dyld.h>
#include <stdlib.h>
#include <string.h>
// note, we use the IL handle since it's all in one lib
extern const struct mach_header* devILhandle;
static const struct mach_header* devILUhandle; // never actually used, just makes it shut up
#endif
#include "extilu.h"

/**
 * Retrieves a function pointer from the ilu library
 * @param function Name of function to retrieve
 */
static void *NativeGetFunctionPointer(const char *function) {
#ifdef _WIN32
	return GetProcAddress(devILUhandle, function);
#endif
#ifdef _X11
	return dlsym(devILUhandle, function);
#endif
#ifdef _MACOSX
	char *mac_symbol_name = (char *)malloc((strlen(function) + 2)*sizeof(char));
	if (mac_symbol_name == NULL)
		return NULL;
	mac_symbol_name[0] = '_';
	strcpy(&(mac_symbol_name[1]), function);
	NSSymbol symbol = NSLookupSymbolInImage(devILhandle, mac_symbol_name, NSLOOKUPSYMBOLINIMAGE_OPTION_RETURN_ON_ERROR);
	free(mac_symbol_name);
	if (symbol == NULL)
		return NULL;
	return NSAddressOfSymbol(symbol);
#endif
}

/**
 * Retrieves a pointer to the named function
 *
 * @param function Name of function
 * @return pointer to named function, or NULL if not found
 */
static void* extilu_GetProcAddress(const char* function) {
	void *p = NativeGetFunctionPointer(function);
	if (p == NULL) {
		printfDebug("Could not locate symbol %s\n", function);
	}
	return p;
}

/**
 * Initializes all functions for class
 */
void extilu_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions) {
    ext_InitializeClass(env, clazz, &extilu_GetProcAddress, num_functions, functions);
}

/**
 * Opens the native library
 */
bool extilu_Open(JNIEnv *env, jobjectArray ilPaths) {
	jsize pathcount = (*env)->GetArrayLength(env, ilPaths);
	int i;
	jstring path;
	char *path_str;

	printfDebug("Found %d ilu paths\n", (int)pathcount);
	for(i=0;i<pathcount;i++) {
		path = (jstring) (*env)->GetObjectArrayElement(env, ilPaths, i);
		path_str = GetStringNativeChars(env, path);
		printfDebug("Testing '%s'\n", path_str);
#ifdef _WIN32
		devILUhandle = LoadLibrary(path_str);
#endif
#ifdef _X11
		devILUhandle = dlopen(path_str, RTLD_LAZY);
#endif
#ifdef _MACOSX
		devILUhandle = NSAddImage(path_str, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
#endif
		if (devILUhandle != NULL) {
			printfDebug("Found ilu at '%s'\n", path_str);
		}

		free(path_str);
		if (devILUhandle != NULL) {
			return true;
		}
	}
	throwException(env, "Could not load ilu library.");
	return false;
}

/**
 * Closes the native library
 */
void extilu_Close(void) {
#ifdef _WIN32
	FreeLibrary(devILUhandle);
#endif
#ifdef _X11
	if (devILUhandle != NULL) {
		dlclose(devILUhandle);
	}
#endif
#ifdef _MACOSX
	// Cannot remove the image
#endif
	devILUhandle = NULL;
}
