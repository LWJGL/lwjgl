#include <jni.h>
#include <windows.h>
#include "common_tools.h"
#include "org_lwjgl_d3d_WindowsDisplay.h"


#define WINDOWCLASSNAME "LWJGL"

static HICON small_icon = NULL;
static HICON large_icon = NULL;
static HWND displayHwnd = NULL;            // Handle to the window
static HDC displayHdc = NULL;             // Device context
extern HINSTANCE dll_handle;                     // Handle to the LWJGL dll

HDC getD3DHDC() {
    return displayHdc;
}

HWND getD3DHWND() {
    return displayHwnd;
}

static void freeLargeIcon() {
    if (large_icon != NULL) {
        DestroyIcon(large_icon);
        large_icon = NULL;
    }
}

static void freeSmallIcon() {
    if (small_icon != NULL) {
        DestroyIcon(small_icon);
        small_icon = NULL;
    }
}

static void copyBufferToRect(JNIEnv *env, jobject buffer_handle, RECT *rect) {
    jint *buffer = (jint *)(*env)->GetDirectBufferAddress(env, buffer_handle);
    jlong size = (*env)->GetDirectBufferCapacity(env, buffer_handle);
    if (size < 4) {
        throwFormattedRuntimeException(env, "Buffer size < 4", size);
        return;
    }
    rect->top = buffer[0];
    rect->bottom = buffer[1];
    rect->left = buffer[2];
    rect->right = buffer[3];
}
void d3dSetGammaRamp(JNIEnv * env, jobject gammaRampBuffer) {
    HDC screenDC;
    WORD *gammaRamp = (WORD *)(*env)->GetDirectBufferAddress(env, gammaRampBuffer);

    screenDC = GetDC(NULL);
    if (SetDeviceGammaRamp(screenDC, gammaRamp) == FALSE) {
        throwException(env, "Failed to set device gamma.");
    }
    ReleaseDC(NULL, screenDC);
}
/*
 * Create a window with the specified title, position, size, and
 * fullscreen attribute. The window will have DirectInput associated
 * with it.
 *
 * Returns true for success, or false for failure
 */
HWND d3dCreateWindow(LPCTSTR window_class_name, int x, int y, int width, int height, bool fullscreen, bool undecorated)
{
    RECT clientSize;
    DWORD exstyle, windowflags;
    HWND new_hwnd;

    getWindowFlags(&windowflags, &exstyle, fullscreen, undecorated);

    // If we're not a fullscreen window, adjust the height to account for the
    // height of the title bar (unless undecorated)
    clientSize.bottom = height;
    clientSize.left = 0;
    clientSize.right = width;
    clientSize.top = 0;

    AdjustWindowRectEx(
      &clientSize,    // client-rectangle structure
      windowflags,    // window styles
      FALSE,       // menu-present option
      exstyle   // extended window style
    );
    // Create the window now, using that class:
    new_hwnd = CreateWindowEx (
            exstyle,
            window_class_name,
            "",
            windowflags,
            x, y, clientSize.right - clientSize.left, clientSize.bottom - clientSize.top,
            NULL,
            NULL,
            dll_handle,
            NULL);

    return new_hwnd;
}
static HICON createWindowIcon(JNIEnv *env, jint *pixels, jint width, jint height) {
    unsigned char col;
    unsigned char mask;
    BITMAPINFO bitmapInfo;
    HBITMAP cursorMask;
    HBITMAP colorBitmap;
    ICONINFO iconInfo;
    HICON icon;
    char *ptrCursorImage;
    int x, y;
    char *dstPtr;
    int wordAlignedWidth;
    int imageSize;
    unsigned char *maskPixels;
    int widthInBytes;
    int leftShift;
    int maskPixelsOff;
    int scanlineWidth;
    HBITMAP colorDIB;

    memset(&bitmapInfo, 0, sizeof(BITMAPINFO));
    bitmapInfo.bmiHeader.biSize              = sizeof(BITMAPINFOHEADER);
    bitmapInfo.bmiHeader.biWidth             = width;
    bitmapInfo.bmiHeader.biHeight            = -height;
    bitmapInfo.bmiHeader.biPlanes            = 1;
    bitmapInfo.bmiHeader.biBitCount          = 24;
    bitmapInfo.bmiHeader.biCompression       = BI_RGB;

    colorDIB = CreateDIBSection(GetDC(NULL), (BITMAPINFO*)&(bitmapInfo),
            DIB_RGB_COLORS, (void*)&(ptrCursorImage), NULL, 0);
    if (!ptrCursorImage) {
        throwException(env, "Could not allocate DIB section.");
    }

    wordAlignedWidth = width * 3;
    if (wordAlignedWidth % sizeof(long) != 0) {
        wordAlignedWidth = (wordAlignedWidth / sizeof(long)) * sizeof(long) + sizeof(long);
    }
    for (y = 0; y < height; y++ ) {
        dstPtr = ptrCursorImage + wordAlignedWidth*y;;
        for (x = 0; x < width; x++ ) {
            if ((pixels[y*width+x] & 0xFF000000) != 0)
            {
                dstPtr[0] = (pixels[y*width+x] >> 0x10) & 0xFF;
                dstPtr[1] = (pixels[y*width+x] >> 0x08) & 0xFF;
                dstPtr[2] = pixels[y*width+x] & 0xFF;
            }
            else
            {
                dstPtr[0] = 0;
                dstPtr[1] = 0;
                dstPtr[2] = 0;
            }

            dstPtr += 3;
        }
    }


    colorBitmap = CreateDIBitmap(GetDC(NULL),
            (BITMAPINFOHEADER*)&bitmapInfo.bmiHeader,
            CBM_INIT,
            (void *)ptrCursorImage,
            (BITMAPINFO*)&bitmapInfo,
            DIB_RGB_COLORS);

    DeleteObject(colorDIB);

    // Convert alpha map to pixel packed mask

    // number of bytes it takes to fit a bit packed scan line.
    widthInBytes = (width & 0x7) != 0 ? (width >> 3) + 1 : (width >> 3);

    // number of bytes it takes to fit WORD padded scan line.
    scanlineWidth = widthInBytes;
    if (scanlineWidth % sizeof(WORD) != 0) {
        scanlineWidth = (scanlineWidth / sizeof(WORD)) * sizeof(WORD) + sizeof(WORD);
    }
    imageSize = scanlineWidth*height;
    maskPixels = (unsigned char*)malloc(sizeof(unsigned char)*imageSize);
    memset(maskPixels, 0, imageSize);

    for (y = 0; y < height; y++) {
        leftShift = 7;
        mask = 0;
        maskPixelsOff = scanlineWidth*y;
        for (x = 0; x < width; x++) {
            col = (((pixels[(width*y)+x] & 0xFF000000) != 0) ? 1 : 0) << leftShift;
            mask = mask | col;
            leftShift--;
            if (leftShift == -1) {
                maskPixels[maskPixelsOff++] = ~mask;
                leftShift = 7;
                mask = 0;
            }
        }
        // write what is left over
        if (leftShift != 7) {
            maskPixels[maskPixelsOff++] = ~mask;
        }
    }

    cursorMask = CreateBitmap(width, height, 1, 1, maskPixels);

    memset(&iconInfo, 0, sizeof(ICONINFO));
    iconInfo.hbmMask = cursorMask;
    iconInfo.hbmColor = colorBitmap;
    iconInfo.fIcon = TRUE;
    iconInfo.xHotspot = 0;
    iconInfo.yHotspot = 0;
    icon = CreateIconIndirect(&iconInfo);
    DeleteObject(colorBitmap);
    DeleteObject(cursorMask);
    free(maskPixels);

    return icon;
}
/*
 *  WindowProc for the GL window.
 */
static LRESULT CALLBACK lwjglWindowProc(HWND hWnd,
                                 UINT msg,
                                 WPARAM wParam,
                                 LPARAM lParam)
{
    jclass display_class;
    jclass display_class_global;
    jmethodID handleMessage_method;
    LONG message_time;
    JNIEnv *env = getThreadEnv();
    if (env != NULL && !(*env)->ExceptionOccurred(env)) {
        /*
         * We'll cache a global reference to the WindowsDisplay class in the window's user data.
         * This is not so much to avoid lookup overhead as it is to avoid problems
         * with AWT. Specifically, awt code can indirectly call this message handler
         * when it does a SendMessage on the main thread to the currently focused window,
         * which could be a LWJGL window. The FindClass will then fail because the calling
         * internal awt class is using the system class loader, not the application loader
         * where lwjgl is found.
         *
         * The very first message sent to this handler is sent when
         * a window is created, where we are sure that the calling class' classloader has
         * LWJGL classes in it.
         */
        display_class_global = (jclass)(LONG_PTR)GetWindowLongPtr(hWnd, GWLP_USERDATA);
        if (display_class_global == NULL) {
            display_class = (*env)->FindClass(env, "org/lwjgl/d3d/WindowsDisplay");
            if (display_class != NULL) {
                display_class_global = (*env)->NewGlobalRef(env, display_class);
                if (display_class_global != NULL)
                    SetWindowLongPtr(hWnd, GWLP_USERDATA, (LONG_PTR)display_class_global);
            }
        }
        if (display_class_global != NULL) {
            message_time = GetMessageTime();
            handleMessage_method = (*env)->GetStaticMethodID(env, display_class_global, "handleMessage", "(JIJJJ)Z");
            if (handleMessage_method != NULL)
                if ((*env)->CallStaticBooleanMethod(env, display_class_global, handleMessage_method, (jlong)(intptr_t)hWnd, (jint)msg, (jlong)wParam, (jlong)lParam, (jlong)message_time))
                    return 0;
        }
    }

    // default action
    return DefWindowProc(hWnd, msg, wParam, lParam);
}

/*
 * Handle native Windows messages
 */
static void handleMessages(JNIEnv *env) {
    /*
     * Now's our chance to deal with Windows messages that are
     * otherwise just piling up and causing everything not to
     * work properly
     */
    MSG msg;
    if (displayHwnd != NULL) {
        while (!(*env)->ExceptionOccurred(env) && PeekMessage(
                    &msg,         // message information
                    NULL,           // handle to window
                    0,  // first message
                    0,  // last message
                    PM_REMOVE      // removal options
                    ))
        {
            DispatchMessage(&msg);
            TranslateMessage(&msg);
        }
    }
}
static jobject createDisplayMode(JNIEnv *env, DEVMODE *devmode) {
    jclass displayModeClass;

    jmethodID displayModeConstructor;

    displayModeClass = (*env)->FindClass(env, "org/lwjgl/d3d/DisplayMode");

    if (displayModeClass == NULL)
        return NULL;
    displayModeConstructor = (*env)->GetMethodID(env, displayModeClass, "<init>", "(IIII)V");
    if (displayModeConstructor == NULL)
        return NULL;

    return (*env)->NewObject(env, displayModeClass, displayModeConstructor,
            devmode->dmPelsWidth, devmode->dmPelsHeight,
            devmode->dmBitsPerPel, devmode->dmDisplayFrequency);
}
/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nCreateWindow
 * Signature: (Lorg/lwjgl/d3d/DisplayMode;ZII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nCreateWindow
  (JNIEnv *env, jobject obj, jobject mode, jboolean fullscreen, jint x, jint y) {
    jclass cls_displayMode = (*env)->GetObjectClass(env, mode);
    jfieldID fid_width = (*env)->GetFieldID(env, cls_displayMode, "width", "I");
    jfieldID fid_height = (*env)->GetFieldID(env, cls_displayMode, "height", "I");
    int width = (*env)->GetIntField(env, mode, fid_width);
    int height = (*env)->GetIntField(env, mode, fid_height);
    bool isUndecorated;          // Whether we're undecorated or not
    static bool oneShotInitialised = false;
    if (!oneShotInitialised) {
        if (!registerWindow(lwjglWindowProc, WINDOWCLASSNAME)) {
            throwException(env, "Could not register window class");
            return;
        }
        oneShotInitialised = true;
    }

    isUndecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");
    displayHwnd = d3dCreateWindow(WINDOWCLASSNAME, x, y, width, height, fullscreen, isUndecorated);
    if (displayHwnd == NULL) {
        throwException(env, "Failed to create the window.");
        return;
    }
    displayHdc = GetDC(displayHwnd);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nDestroyWindow
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nDestroyWindow
  (JNIEnv *env, jclass clazz) {
    jclass display_class_global = (jclass)(LONG_PTR)GetWindowLongPtr(displayHwnd, GWLP_USERDATA);
    closeWindow(&displayHwnd, &displayHdc);
    if (display_class_global != NULL) {
        (*env)->DeleteGlobalRef(env, display_class_global);
    }
    freeLargeIcon();
    freeSmallIcon();
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    clipCursor
 * Signature: (JLjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_clipCursor
  (JNIEnv *env, jclass clazz, jlong hwndPtr, jobject handleBuffer) {
    HWND hwnd = (HWND)(INT_PTR)hwndPtr;
    RECT clip_rect;
    LPRECT clip_rect_ptr;
    if (handleBuffer != NULL) {
        copyBufferToRect(env, handleBuffer, &clip_rect);
        clip_rect_ptr = &clip_rect;
    } else
        clip_rect_ptr = NULL;
    if (ClipCursor(clip_rect_ptr) == 0)
        throwFormattedException(env, "ClipCursor failed (%d)", GetLastError());
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nSwitchDisplayMode
 * Signature: (Lorg/lwjgl/d3d/DisplayMode;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nSwitchDisplayMode
  (JNIEnv *env, jclass clazz, jobject mode) {
    DEVMODE devmode;

    jclass cls_displayMode = (*env)->GetObjectClass(env, mode);
    jfieldID fid_width = (*env)->GetFieldID(env, cls_displayMode, "width", "I");
    jfieldID fid_height = (*env)->GetFieldID(env, cls_displayMode, "height", "I");
    jfieldID fid_bpp = (*env)->GetFieldID(env, cls_displayMode, "bpp", "I");
    jfieldID fid_freq = (*env)->GetFieldID(env, cls_displayMode, "freq", "I");

    int width = (*env)->GetIntField(env, mode, fid_width);
    int height = (*env)->GetIntField(env, mode, fid_height);
    int bpp = (*env)->GetIntField(env, mode, fid_bpp);
    int freq = (*env)->GetIntField(env, mode, fid_freq);
    LONG cdsret;

    ZeroMemory(&devmode, sizeof(DEVMODE));
    devmode.dmSize = sizeof(DEVMODE);
    devmode.dmBitsPerPel = bpp;
    devmode.dmPelsWidth = width;
    devmode.dmPelsHeight = height;
    devmode.dmDisplayFrequency = freq;
    devmode.dmFields = DM_BITSPERPEL | DM_PELSWIDTH | DM_PELSHEIGHT;
    if (freq != 0)
        devmode.dmFields |= DM_DISPLAYFREQUENCY;
    cdsret = ChangeDisplaySettings(&devmode, CDS_FULLSCREEN);

    if (cdsret != DISP_CHANGE_SUCCESSFUL) {
        throwFormattedException(env, "Failed to set display mode (%ld).", cdsret);
        return;
    }
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    showWindow
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_showWindow
  (JNIEnv *env, jclass clazz, jlong hwndPtr, jint mode) {
    HWND hwnd = (HWND)(INT_PTR)hwndPtr;
    ShowWindow(hwnd, mode);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    setForegroundWindow
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_setForegroundWindow
  (JNIEnv *env, jclass clazz, jlong hwndPtr) {
    HWND hwnd = (HWND)(INT_PTR)hwndPtr;
    SetForegroundWindow(hwnd);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    setFocus
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_setFocus
  (JNIEnv *env, jclass clazz, jlong hwndPtr) {
    HWND hwnd = (HWND)(INT_PTR)hwndPtr;
    SetFocus(hwnd);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nResetDisplayMode
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nResetDisplayMode
  (JNIEnv *env, jclass clazz) {
    ChangeDisplaySettings(NULL, 0);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    convertToNativeRamp
 * Signature: (Ljava/nio/FloatBuffer;)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_d3d_WindowsDisplay_convertToNativeRamp
  (JNIEnv *env, jclass clazz, jobject gammaRamp) {
    return NULL;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getCurrentGammaRamp
 * Signature: ()Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getCurrentGammaRamp
  (JNIEnv *env, jclass clazz) {
    return NULL;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nSetGammaRamp
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nSetGammaRamp
  (JNIEnv *env, jclass clazz, jobject nativeRamp) {
    d3dSetGammaRamp(env, nativeRamp);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nGetVersion
 * Signature: (Ljava/lang/String;)Lorg/lwjgl/d3d/WindowsFileVersion;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nGetVersion
  (JNIEnv *env, jobject obj, jstring driver) {
    return NULL;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getCurrentDisplayMode
 * Signature: ()Lorg/lwjgl/d3d/DisplayMode;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getCurrentDisplayMode
  (JNIEnv *env, jclass clazz) {
    DEVMODE devmode;
    if (!EnumDisplaySettings(NULL, ENUM_CURRENT_SETTINGS, &devmode)) {
        throwFormattedException(env, "Couldn't get current display settings (%ld)", GetLastError());
        return NULL;
    }
    return createDisplayMode(env, &devmode);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    setTitle
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_setTitle
  (JNIEnv *env, jobject obj, jstring title_obj) {
    char * title = GetStringNativeChars(env, title_obj);
    SetWindowText(displayHwnd, title);
    free(title);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nUpdate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nUpdate
  (JNIEnv *env, jclass clazz) {
    handleMessages(env);
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nReshape
 * Signature: (JIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nReshape
  (JNIEnv *env, jclass clazz, jlong hwndPtr, jint x, jint y, jint width, jint height) {
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/d3d/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getAvailableDisplayModes
  (JNIEnv *env, jobject obj) {
    int i = 0, j = 0, n = 0;

    DEVMODE DevMode;
    jobject *display_mode_objects = NULL;
    int list_size = 0;

    jclass displayModeClass;
    jobjectArray ret;
    displayModeClass = (*env)->FindClass(env, "org/lwjgl/d3d/DisplayMode");

    ZeroMemory(&DevMode, sizeof(DEVMODE));

    DevMode.dmSize = sizeof(DEVMODE);

    while(EnumDisplaySettings(NULL, j++, &DevMode) != 0) {
        // Filter out indexed modes
        if (DevMode.dmBitsPerPel > 8 && ChangeDisplaySettings(&DevMode, CDS_FULLSCREEN | CDS_TEST) == DISP_CHANGE_SUCCESSFUL) {
            jobject displayMode;
            if (list_size <= n) {
                list_size += 1;
                display_mode_objects = (jobject *)realloc(display_mode_objects, sizeof(jobject)*list_size);
                if (display_mode_objects == NULL) {
                    return NULL;
                }
            }
            displayMode = createDisplayMode(env, &DevMode);
            display_mode_objects[n++] = displayMode;
        }
    }
    printfDebugJava(env, "Found %d displaymodes", n);

    ret = (*env)->NewObjectArray(env, n, displayModeClass, NULL);
    for (i = 0; i < n; i++) {
        (*env)->SetObjectArrayElement(env, ret, i, display_mode_objects[i]);
    }
    free(display_mode_objects);
    return ret;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nSetCursorPosition
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nSetCursorPosition
  (JNIEnv *env, jclass clazz, jint x, jint y) {
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nSetNativeCursor
 * Signature: (JLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nSetNativeCursor
  (JNIEnv *env, jclass clazz, jlong hwndPtr, jobject handle) {
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getSystemMetrics
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getSystemMetrics
  (JNIEnv *env, jclass clazz, jint index) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getDllInstance
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getDllInstance
  (JNIEnv *env, jclass clazz) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getHwnd
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getHwnd
  (JNIEnv *env, jclass clazz) {
    return (jlong)getD3DHWND();
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getDesktopWindow
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getDesktopWindow
  (JNIEnv *env, jclass clazz) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nSetWindowIcon16
 * Signature: (Ljava/nio/IntBuffer;)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nSetWindowIcon16
  (JNIEnv *env, jclass clazz, jobject icon) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    nSetWindowIcon32
 * Signature: (Ljava/nio/IntBuffer;)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_d3d_WindowsDisplay_nSetWindowIcon32
  (JNIEnv *env, jclass clazz, jobject icon) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    getClientRect
 * Signature: (JLjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_getClientRect
  (JNIEnv *env, jclass clazz, jlong hwndPtr, jobject rect) {
}

/*
 * Class:     org_lwjgl_d3d_WindowsDisplay
 * Method:    clientToScreen
 * Signature: (JLjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDisplay_clientToScreen
  (JNIEnv *env, jclass clazz, jlong hwndPtr, jobject point) {
}
