#include "org_lwjgl_d3d_IDirect3DDevice9Ex.h"

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nCheckDeviceState
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nCheckDeviceState
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jlong window) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nCheckResourceResidency
 * Signature: (JLorg/lwjgl/d3d/IDirect3DResource9;J)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nCheckResourceResidency
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jobject resourceArray, jlong numResources) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nCreateDepthStencilSurfaceEx
 * Signature: (JIIIIJZLorg/lwjgl/d3d/IDirect3DSurface9;JJ)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nCreateDepthStencilSurfaceEx
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint width, jint height, jint format, jint multiSample, jlong multisampleQuality, jboolean discard, jobject surface, jlong sharedHandle, jlong usage) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nCreateOffscreenPlainSurfaceEx
 * Signature: (JIIIJLorg/lwjgl/d3d/IDirect3DSurface9;JJ)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nCreateOffscreenPlainSurfaceEx
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint width, jint height, jint format, jlong pool, jobject surface, jlong sharedHandle, jlong usage) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nCreateRenderTargetEx
 * Signature: (JIIIIJZLorg/lwjgl/d3d/IDirect3DSurface9;JJ)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nCreateRenderTargetEx
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint width, jint height, jint format, jint multiSample, jlong multisampleQuality, jboolean lockable, jobject surface, jlong sharedHandle, jlong usage) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nComposeRect
 * Signature: (JLorg/lwjgl/d3d/IDirect3DSurface9;Lorg/lwjgl/d3d/IDirect3DSurface9;Lorg/lwjgl/d3d/IDirect3DVertexBuffer9;ILorg/lwjgl/d3d/IDirect3DVertexBuffer9;III)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nComposeRect
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jobject source, jobject destination, jobject srcRectDescriptors, jint numRects, jobject dstRectDescriptors, jint operation, jint xOffset, jint yOffset) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nGetDisplayModeEx
 * Signature: (JILorg/lwjgl/d3d/D3DDisplaymodeEx;I)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nGetDisplayModeEx
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint swapChain, jobject mode, jint rotation) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nGetGPUThreadPriority
 * Signature: (JLjava/nio/IntBuffer;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nGetGPUThreadPriority
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jobject priority) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nGetMaximumFrameLatency
 * Signature: (JLjava/nio/IntBuffer;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nGetMaximumFrameLatency
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jobject maxLatency) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nPresentEx
 * Signature: (JLorg/lwjgl/d3d/Rectangle;Lorg/lwjgl/d3d/Rectangle;JLorg/lwjgl/d3d/D3DRegionData;J)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nPresentEx
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jobject sourceRect, jobject destRect, jlong destWindowOverride, jobject dirtyRegion, jlong flags) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nResetEx
 * Signature: (JLorg/lwjgl/d3d/D3DPresentParameters;Lorg/lwjgl/d3d/D3DDisplaymodeEx;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nResetEx
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jobject presentationParameters, jobject fullscreenDisplayMode) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nSetConvolutionMonoKernel
 * Signature: (JIILjava/nio/FloatBuffer;Ljava/nio/FloatBuffer;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nSetConvolutionMonoKernel
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint width, jint height, jobject rowWeights, jobject columnWeights) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nSetGPUThreadPriority
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nSetGPUThreadPriority
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint  priority) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nSetMaximumFrameLatency
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nSetMaximumFrameLatency
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint maxLatency) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nTestCooperativeLevel
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nTestCooperativeLevel
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DDevice9Ex
 * Method:    nWaitForVBlank
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DDevice9Ex_nWaitForVBlank
  (JNIEnv *env, jobject obj, jlong IDirect3DDevice9Ex, jint swapChainIndex) {
    return 0;
}
