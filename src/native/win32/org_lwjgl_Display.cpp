/*
 * org_lwjgl_Display.cpp
 *
 * Win32 specific library
 *
 * (C) 2002 Shaven Puppy Ltd
 *
 * Created 10 August 2002 by foo
 *
 */

#include <windows.h>
#include "org_lwjgl_Display.h"
#include <dinput.h>

#define WINDOWCLASSNAME "JGLIBWINDOW"

// Initialise static variables
bool			oneShotInitialised = false;
HWND			hwnd = NULL;						// Handle to the window
HDC				hdc = NULL;							// Device context
LPDIRECTINPUT	lpdi = NULL;

/*
 *	A dummy WindowProc which does nothing. Used so we can have an invisible OpenGL window
 */
LRESULT CALLBACK WindowProc(HWND hWnd,
							UINT msg,
							WPARAM wParam,
							LPARAM lParam)
{
	switch (msg) {
		// disable screen saver and monitor power down messages which wreak havoc
		case WM_SYSCOMMAND:
		{
			switch (wParam) {
			case SC_SCREENSAVE:
			case SC_MONITORPOWER:
				return 0;
				break;
			default:
				break;
			}
		}
	}

	// default action
    return DefWindowProc(hWnd, msg, wParam, lParam);
}

/*
 * Sets the fullscreen display mode.
 * Returns 1 for success and -1 for failure.
 */
int SetDisplayMode(int width, int height, int bpp, int freq)
{
	// Step 2: set display mode using OpenGL friendly tactics

	DEVMODE devmode;
	devmode.dmSize = sizeof(DEVMODE);
	devmode.dmBitsPerPel = bpp;
	devmode.dmPelsWidth = width;
	devmode.dmPelsHeight = height;
	devmode.dmDisplayFlags = 0;
	devmode.dmDisplayFrequency = freq;
	devmode.dmFields = DM_BITSPERPEL | DM_PELSWIDTH | DM_PELSHEIGHT | DM_DISPLAYFLAGS | DM_DISPLAYFREQUENCY;

	LONG cdsret = ChangeDisplaySettings(&devmode, CDS_FULLSCREEN);
	switch (cdsret) {
	case DISP_CHANGE_BADFLAGS :
		printf("Failed to set screen mode: bad flags\n");
		return -1;
	case DISP_CHANGE_FAILED:
		printf("Failed to set screen mode: change failed\n");
		return -1;
	case DISP_CHANGE_BADMODE:
		printf("Failed to set screen mode: bad mode\n");
		return -1;
	case DISP_CHANGE_SUCCESSFUL :
		// Success!
		break;
	default:
		printf("Failed to set screen mode: unknown error\n");
		return -1;
	}
	return 1;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_getAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{
	return NULL;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nCreate
 * Signature: (IIIIZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate
  (JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jboolean debug)
{
#ifdef _DEBUG
	printf("Creating display: size %dx%d %dhz %dbpp...\n", width, height, freq, bpp);
#endif

	if (!debug && SetDisplayMode(width, height, bpp, freq) != 1)
		return JNI_FALSE;

	/*
		Register a window. This window does nothing, it's just a requirement that we get
		a handle to it so we can do other things
	*/

	if (!oneShotInitialised) {
		WNDCLASS windowClass;

		windowClass.style = CS_GLOBALCLASS | CS_OWNDC;
		windowClass.lpfnWndProc = WindowProc;
		windowClass.cbClsExtra = 0;
		windowClass.cbWndExtra = 0;
		windowClass.hInstance = (HINSTANCE) GetCurrentProcess();
		windowClass.hIcon = LoadIcon(NULL, IDI_APPLICATION);
		windowClass.hCursor = LoadIcon(NULL, IDC_ARROW);
		windowClass.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
		windowClass.lpszMenuName = NULL;
		windowClass.lpszClassName = WINDOWCLASSNAME;

		if (RegisterClass(&windowClass) == 0) {
			printf("Failed to register window class\n");
			return JNI_FALSE;
		}
		oneShotInitialised = true;
	}

	// Create the window now, using that class:
	hwnd = CreateWindow(
		WINDOWCLASSNAME,
		"LWJGL",
		WS_POPUP, // | WS_MAXIMIZE,
		0, 0,
		width, height,
		NULL,
		NULL,
		(HINSTANCE) GetCurrentProcess(),
		NULL);
	// And we never look at windowClass again...

	ShowWindow(hwnd, SW_SHOW);
	UpdateWindow(hwnd);

	hdc = GetWindowDC(hwnd);  

	// Success! Now you need to initialize a GL object, which creates a GL rendering context;
	// and then to issue commands to it, you need to call gl::makeCurrent().
#ifdef _DEBUG
	printf("Created display\n");
#endif

	// Hide the mouse
	ShowCursor(FALSE);

	// Create input
	HRESULT ret = DirectInputCreate((HINSTANCE)GetCurrentProcess(), DIRECTINPUT_VERSION, &lpdi, NULL);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION ) {
		printf("Failed to create directinput\n");
		return JNI_FALSE;
	}

	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_nDestroy
  (JNIEnv * env, jclass clazz)
{
	// Reset the display if necessary
	ChangeDisplaySettings(NULL, 0);

	if (hwnd != NULL) {
		// Vape the window
		DestroyWindow(hwnd);
		hwnd = NULL;
	}

#ifdef _DEBUG
	printf("Destroyed display\n");
#endif

	// Show the mouse
	ShowCursor(TRUE);
}
