/* Handle to devil Library */
#ifdef _WIN32
 #include "extil.h"
 static HMODULE devILhandle;
#else
 #include <dlfcn.h>
 #include "extil.h"
 #include <libgen.h>
 static void* devILhandle;
#endif

static const char* VERSION = "1.0beta3";

/*
 * Class:     org_lwjgl_devil_ILNative
 * Method:    getNativeLibraryVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_devil_ILNative_getNativeLibraryVersion(JNIEnv *env, jclass clazz) {
  return NewStringNative(env, VERSION);
}

/**
 * Retrieves a function pointer from the devil library
 * @param function Name of function to retrieve
 */
static void *NativeGetFunctionPointer(const char *function) {
#ifdef _WIN32
	return GetProcAddress(devILhandle, function);
#else
	return dlsym(devILhandle, function);
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
#else
		char* directoryName = dirname(path_str);
		char* fileName = basename(path_str);
		char* currentDirectory = getwd(NULL);
		if(directoryName != NULL) {
			chdir(directoryName);
		} 
		devILhandle = dlopen(fileName, RTLD_LAZY);
		if(devILhandle == NULL) {
			printfDebug("dlopen failure: %s\n\n\n", dlerror());
		}
		chdir(currentDirectory);
		free(currentDirectory);
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
#else
	if (devILhandle != NULL) {
		dlclose(devILhandle);
	}
#endif
	devILhandle = NULL;
}

