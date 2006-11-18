/* Handle to ilut Library */
#ifdef _WIN32
 #include "extilut.h"
 static HMODULE devILUThandle;
#else
 #include <dlfcn.h>
 #include "extilut.h"
 #include <libgen.h>
 static void* devILUThandle;
#endif

/**
 * Retrieves a function pointer from the ilut library
 * @param function Name of function to retrieve
 */
static void *NativeGetFunctionPointer(const char *function) {
#ifdef _WIN32
	return GetProcAddress(devILUThandle, function);
#else
	return dlsym(devILUThandle, function);
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
#else
		char* directoryName = dirname(path_str);
		char* fileName = basename(path_str);
		char* currentDirectory = getwd(NULL);
		if(directoryName != NULL) {
			chdir(directoryName);
		} 
		devILUThandle = dlopen(fileName, RTLD_LAZY);
		if(devILUThandle == NULL) {
			printfDebug("dlopen failure: %s\n\n\n", dlerror());
		}
		chdir(currentDirectory);
		free(currentDirectory);
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
#else
	if (devILUThandle != NULL) {
		dlclose(devILUThandle);
	}
#endif
	devILUThandle = NULL;
}

