#include "org_lwjgl_d3d_IDirect3D9Ex.h"

/*
 * Class:     org_lwjgl_d3d_IDirect3D9Ex
 * Method:    nCreateDeviceEx
 * Signature: (JIIJJLorg/lwjgl/d3d/D3DPresentParameters;Lorg/lwjgl/d3d/D3DDisplaymodeEx;Lorg/lwjgl/d3d/IDirect3DDevice9Ex;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9Ex_nCreateDeviceEx
  (JNIEnv *env, jobject obj, jlong IDirect3D9Ex, jint adapter, jint deviceType, jlong focusWindow, jlong behaviorFlags, jobject presentationParameters, jobject fullscreenDisplayMode, jobject returnedDeviceInterface) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9Ex
 * Method:    nEnumAdapterModesEx
 * Signature: (JILorg/lwjgl/d3d/D3DDisplaymodeFilter;ILorg/lwjgl/d3d/D3DDisplaymodeEx;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9Ex_nEnumAdapterModesEx
  (JNIEnv *env, jobject obj, jlong IDirect3D9Ex, jint adapter, jobject filter, jint mode, jobject displayMode) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9Ex
 * Method:    nGetAdapterDisplayModeEx
 * Signature: (JILorg/lwjgl/d3d/D3DDisplaymodeEx;I)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9Ex_nGetAdapterDisplayModeEx
  (JNIEnv *env, jobject obj, jlong IDirect3D9Ex, jint adapter, jobject mode, jint rotation) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9Ex
 * Method:    nGetAdapterLUID
 * Signature: (JILorg/lwjgl/d3d/LUID;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3D9Ex_nGetAdapterLUID
  (JNIEnv *env, jobject obj, jlong IDirect3D9Ex, jint adapter, jobject LUID) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3D9Ex
 * Method:    nGetAdapterModeCountEx
 * Signature: (JILorg/lwjgl/d3d/D3DDisplaymodeFilter;)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_d3d_IDirect3D9Ex_nGetAdapterModeCountEx
  (JNIEnv *env, jobject obj, jlong IDirect3D9Ex, jint adapter, jobject filter) {
    return 0;
}
