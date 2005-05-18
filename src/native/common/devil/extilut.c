/* Handle to ilut Library */
#ifdef _WIN32
#include "extilut.h"
static HMODULE devILUThandle;
#endif
#ifdef _X11
#include "extilut.h"
static void* devILUThandle;
#endif
#ifdef _MACOSX
#include <mach-o/dyld.h>
#include <stdlib.h>
#include <string.h>
// note, we use the IL handle since it's all in one lib
extern const struct mach_header* devILhandle;
static const struct mach_header* devILUThandle; // never actually used, just makes it shut up
#include "extilut.h"
#endif

/**
 * Retrieves a function pointer from the ilut library
 * @param function Name of function to retrieve
 */
static void *NativeGetFunctionPointer(const char *function) {
#ifdef _WIN32
	return GetProcAddress(devILUThandle, function);
#endif
#ifdef _X11
	return dlsym(devILUThandle, function);
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
static void* extilut_GetProcAddress(const char* function) {
	void *p = NativeGetFunctionPointer(function);
	if (p == NULL) {
		printfDebug("Could not locate symbol %s\n", function);
	}
	return p;
}

/**
 * Initializes all functions for class
 */
void extilut_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions) {
    ext_InitializeClass(env, clazz, &extilut_GetProcAddress, num_functions, functions);
}

/**
 * Opens the native library
 */
bool extilut_Open(JNIEnv *env, jobjectArray ilPaths) {
	jsize pathcount = (*env)->GetArrayLength(env, ilPaths);
	int i;
	jstring path;
	char *path_str;

	printfDebug("Found %d ilut paths\n", (int)pathcount);
	for(i=0;i<pathcount;i++) {
		path = (jstring) (*env)->GetObjectArrayElement(env, ilPaths, i);
		path_str = GetStringNativeChars(env, path);
		printfDebug("Testing '%s'\n", path_str);
#ifdef _WIN32
		devILUThandle = LoadLibrary(path_str);
#endif
#ifdef _X11
		devILUThandle = dlopen(path_str, RTLD_LAZY);
#endif
#ifdef _MACOSX
		devILUThandle = NSAddImage(path_str, NSADDIMAGE_OPTION_RETURN_ON_ERROR);
#endif
		if (devILUThandle != NULL) {
			printfDebug("Found ilut at '%s'\n", path_str);
		}

		free(path_str);
		if (devILUThandle != NULL) {
			return true;
		}
	}
	throwException(env, "Could not load ilut library.");
	return false;
}

/**
 * Closes the native library
 */
void extilut_Close(void) {
#ifdef _WIN32
	FreeLibrary(devILUThandle);
#endif
#ifdef _X11
	if (devILUThandle != NULL) {
		dlclose(devILUThandle);
	}
#endif
#ifdef _MACOSX
	// Cannot remove the image
#endif
	devILUThandle = NULL;
}

