/* Handle to ilu Library */
#ifdef _WIN32
 #include "extilu.h"
 static HMODULE devILUhandle;
#else
 #include <dlfcn.h>
 #include "extilu.h"
 static void* devILUhandle;
#endif

/**
 * Retrieves a function pointer from the ilu library
 * @param function Name of function to retrieve
 */
static void *NativeGetFunctionPointer(const char *function) {
#ifdef _WIN32
	return GetProcAddress(devILUhandle, function);
#else
	return dlsym(devILUhandle, function);
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
#elif __APPLE__ && __MACH__
		char* directoryName = dirname(path_str);
		char* fileName = basename(path_str);
		char* currentDirectory = getwd(NULL);
		if(directoryName != NULL) {
			chdir(directoryName);
		} 
		devILUhandle = dlopen(fileName, RTLD_LAZY);
		if(devILUhandle == NULL) {
			printfDebug("dlopen failure: %s\n", dlerror());
		}
		chdir(currentDirectory);
		free(currentDirectory);
#else
		devILUhandle = dlopen(path_str, RTLD_LAZY);
		if(devILUhandle == NULL) {
			printfDebug("dlopen failure: %s\n", dlerror());
		}
#endif
		if (devILUhandle != NULL) {
			printfDebug("Found ILU at '%s'\n", path_str);
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
#else
	if (devILUhandle != NULL) {
		dlclose(devILUhandle);
	}
#endif
	devILUhandle = NULL;
}
