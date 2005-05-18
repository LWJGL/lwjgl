/* Handle to devil Library */
#ifdef _WIN32
#include "extil.h"
static HMODULE devILhandle;
#endif
#ifdef _X11
#include "extil.h"
static void* devILhandle;
#endif
#ifdef _MACOSX
#include <mach-o/dyld.h>
#include <stdlib.h>
#include <string.h>
const struct mach_header* devILhandle = NULL;
#include "extil.h"
#endif

/**
 * Retrieves a function pointer from the devil library
 * @param function Name of function to retrieve
 */
static void *NativeGetFunctionPointer(const char *function) {
#ifdef _WIN32
	return GetProcAddress(devILhandle, function);
#endif
#ifdef _X11
	return dlsym(devILhandle, function);
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
static void* extil_GetProcAddress(const char* function) {
	void *p = NativeGetFunctionPointer(function);
	if (p == NULL) {
		printfDebug("Could not locate symbol %s\n", function);
	}
	return p;
}

/**
 * Initializes all functions for class
 */
void extil_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions) {
    ext_InitializeClass(env, clazz, &extil_GetProcAddress, num_functions, functions);
}

/**
 * Opens the native library
 */
bool extil_Open(JNIEnv *env, jobjectArray ilPaths) {
	jsize pathcount = (*env)->GetArrayLength(env, ilPaths);
	int i;
	jstring path;
	char *path_str;

	printfDebug("Found %d devil paths\n", (int)pathcount);
	for(i=0;i<pathcount;i++) {
		path = (jstring) (*env)->GetObjectArrayElement(env, ilPaths, i);
		path_str = GetStringNativeChars(env, path);
		printfDebug("Testing '%s'\n", path_str);
#ifdef _WIN32
		devILhandle = LoadLibrary(path_str);
#endif
#ifdef _X11
		devILhandle = dlopen(path_str, RTLD_LAZY);
#endif
#ifdef _MACOSX
		devILhandle = NSAddImage(path_str, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
#endif
		if (devILhandle != NULL) {
			printfDebug("Found devil at '%s'\n", path_str);
		}

		free(path_str);
		if (devILhandle != NULL) {
			return true;
		}
	}
	throwException(env, "Could not load devil library.");
	return false;
}

/**
 * Closes the native library
 */
void extil_Close(void) {
#ifdef _WIN32
	FreeLibrary(devILhandle);
#endif
#ifdef _X11
	if (devILhandle != NULL) {
		dlclose(devILhandle);
	}
#endif
#ifdef _MACOSX
	// Cannot remove the image
#endif
	devILhandle = NULL;
}

