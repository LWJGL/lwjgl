#include "extil.h"

/* turn off the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn -8064
#pragma warn -8065
#endif /* __BORLANDC__  */

#ifdef _WIN32
HMODULE devILhandle = NULL;
#endif

/* getProcAddress */

void *extil_GetProcAddress(const char *name) {
#ifdef _WIN32
    void *t = GetProcAddress(devILhandle, name);


        if (t == NULL) {
            printfDebug("Could not locate symbol %s\n", name);
        }


    return t;
#endif
}

void extil_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions) {
    ext_InitializeClass(env, clazz, &extil_GetProcAddress, num_functions, functions);
}


bool extil_InitializeFunctions(int num_functions, ExtFunction *functions) {
    return ext_InitializeFunctions(&extil_GetProcAddress, num_functions, functions);
}

#ifdef _WIN32
bool extil_Open(JNIEnv *env) {
    bool result = true;

    if (devILhandle == NULL) {
        // load the dynamic libraries for DevIL
        devILhandle = LoadLibrary("DevIL.dll");
        if (devILhandle == NULL) {
            printf("\r\nfailed to load DevIL");
            result = false;
        }
    }

    return result;
}
#endif /* WIN32 */

void extgl_Close(void) {
#ifdef _WIN32
    FreeLibrary(devILhandle);
    devILhandle = NULL;
#endif
}

/* turn on the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn .8064
#pragma warn .8065
#endif /* __BORLANDC__  */

