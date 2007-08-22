#include <windows.h>
#include <windowsx.h>
#include <d3d9.h>
#include <stdio.h>
#include "org_lwjgl_d3d_IDirect3D9.h"
#include "context.h"

extern void setPointer(JNIEnv *env, jobject classInstance, const char* methodName, jlong pointer);
//void setPointer(JNIEnv *env, jobject classInstance, const char* methodName, jlong pointer) {
//    jclass clazz = (*env)->GetObjectClass(env, classInstance);
//    jmethodID mid = (*env)->GetMethodID(env, clazz, methodName, "(J)V");
//    if (mid != NULL) {
//        (*env)->CallVoidMethod(env, classInstance, mid, pointer);
//    }
//}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nCheckDepthStencilMatch
 * Signature: (IIIII)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nCheckDepthStencilMatch
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint deviceType, jint adapterFormat, jint renderTargetFormat, jint depthStencilFormat) {
    return IDirect3D9_CheckDepthStencilMatch((IDirect3D9 *)iDirect3D9, adapter, deviceType, adapterFormat, renderTargetFormat, depthStencilFormat);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nCheckDeviceFormat
 * Signature: (IIIJII)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nCheckDeviceFormat
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint deviceType, jint adapterFormat, jint usage, jint resoruceType, jint checkFormat) {
    return IDirect3D9_CheckDeviceFormat((IDirect3D9 *)iDirect3D9, adapter, deviceType, adapterFormat, usage, resoruceType, checkFormat);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nCheckDeviceFormatConversion
 * Signature: (IIII)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nCheckDeviceFormatConversion
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint deviceType, jint sourceFormat, jint targetFormat) {
    return IDirect3D9_CheckDeviceFormatConversion((IDirect3D9 *)iDirect3D9, adapter, deviceType, sourceFormat, targetFormat);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nCheckDeviceMultiSampleType
 * Signature: (IIIZILjava/nio/IntBuffer;)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nCheckDeviceMultiSampleType
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint deviceType, jint surfaceFormat, boolean windowed, jint multiSampleType, jobject qualityLevels) {
    DWORD * quality = NULL;

    if(qualityLevels != NULL) {
        DWORD * quality = (DWORD *)((*env)->GetDirectBufferAddress(env, qualityLevels));
    }

    return IDirect3D9_CheckDeviceMultiSampleType((IDirect3D9 *)iDirect3D9, adapter, deviceType, surfaceFormat, windowed, multiSampleType, quality);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nCheckDeviceType
 * Signature: (IIIIZ)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nCheckDeviceType
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint deviceType, jint displayFormat, jint backBufferFormat, boolean windowed) {
    return IDirect3D9_CheckDeviceType((IDirect3D9 *)iDirect3D9, adapter, deviceType, displayFormat, backBufferFormat, windowed);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nCreateDevice
 * Signature: (IIJJLorg/lwjgl/d3d/IDirect3D9$D3DPRESENT_PARAMETERS;Lorg/lwjgl/d3d/IDirect3DDevice9;)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nCreateDevice
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint deviceType, jlong focusWindow, jint behaviorFlags, jobject presentationParameters, jobject returnedDeviceInterface) {
    IDirect3DDevice9 *iDirect3DDevice9;
    HWND myHwnd = (HWND)(INT_PTR)focusWindow;
    D3DPRESENT_PARAMETERS* pPresentationParameters = (D3DPRESENT_PARAMETERS*)((*env)->GetDirectBufferAddress(env, presentationParameters));

    HRESULT hResult = IDirect3D9_CreateDevice((IDirect3D9 *)iDirect3D9, adapter, deviceType,
        myHwnd, behaviorFlags, pPresentationParameters, &iDirect3DDevice9);

    setPointer(env, returnedDeviceInterface, "setIDirect3DDevice9", (jlong)iDirect3DDevice9);

    return hResult;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nEnumAdapterModes
 * Signature: (IIILorg/lwjgl/d3d/IDirect3D9$D3DDISPLAYMODE;)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nEnumAdapterModes
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint format, jint modeIndex, jobject mode) {
    D3DDISPLAYMODE * displayMode = (D3DDISPLAYMODE *)((*env)->GetDirectBufferAddress(env, mode));

    return IDirect3D9_EnumAdapterModes((IDirect3D9 *)iDirect3D9, adapter, format, modeIndex, displayMode);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nGetAdapterCount
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_d3d_IDirect3D9_nGetAdapterCount
  (JNIEnv *env, jobject object, jlong iDirect3D9) {
    return IDirect3D9_GetAdapterCount((IDirect3D9 *)iDirect3D9);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nGetAdapterDisplayMode
 * Signature: (ILorg/lwjgl/d3d/IDirect3D9$D3DDISPLAYMODE;)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nGetAdapterDisplayMode
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jobject mode) {
    D3DDISPLAYMODE * displayMode = (D3DDISPLAYMODE *)((*env)->GetDirectBufferAddress(env, mode));

    return IDirect3D9_GetAdapterDisplayMode((IDirect3D9 *)iDirect3D9, adapter, displayMode);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nGetAdapterIdentifier
 * Signature: (IJLorg/lwjgl/d3d/IDirect3D9$D3DADAPTER_IDENTIFIER9;)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nGetAdapterIdentifier
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint flags, jobject identifier) {
    D3DADAPTER_IDENTIFIER9 * adapterIdentifier = (D3DADAPTER_IDENTIFIER9 *)((*env)->GetDirectBufferAddress(env, identifier));

    return IDirect3D9_GetAdapterIdentifier((IDirect3D9 *)iDirect3D9, adapter, flags, adapterIdentifier);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nGetAdapterModeCount
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_d3d_IDirect3D9_nGetAdapterModeCount
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint format) {
    return IDirect3D9_GetAdapterModeCount((IDirect3D9 *)iDirect3D9, adapter, format);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nGetAdapterMonitor
 * Signature: (I)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nGetAdapterMonitor
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter) {
    return (jlong)IDirect3D9_GetAdapterMonitor((IDirect3D9 *)iDirect3D9, adapter);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nGetDeviceCaps
 * Signature: (IILorg/lwjgl/d3d/IDirect3D9$D3DCAPS9;)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nGetDeviceCaps
  (JNIEnv *env, jobject object, jlong iDirect3D9, jint adapter, jint deviceType, jobject caps) {
    D3DCAPS9 * d3dCaps = (D3DCAPS9 *)((*env)->GetDirectBufferAddress(env, caps));

    return IDirect3D9_GetDeviceCaps((IDirect3D9 *)iDirect3D9, adapter, deviceType, d3dCaps);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nRegisterSoftwareDevice
 * Signature: ()I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nRegisterSoftwareDevice
  (JNIEnv *env, jobject object, jlong iDirect3D9) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nRelease
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_IDirect3D9_nRelease
  (JNIEnv *env, jobject object, jlong iDirect3D9) {
      IDirect3D9_Release((IDirect3D9 *)iDirect3D9);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9
 * Method:    nCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9_nCreate
  (JNIEnv *env, jclass clazz) {
    IDirect3D9 *direct3DInterface = Direct3DCreate9(D3D_SDK_VERSION);

    return (jlong)direct3DInterface;
}
