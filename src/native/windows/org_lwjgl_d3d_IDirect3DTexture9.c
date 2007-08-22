#include "org_lwjgl_d3d_IDirect3DTexture9.h"

/*
 * Class:     org_lwjgl_d3d_IDirect3DTexture9
 * Method:    nAddDirtyRect
 * Signature: (JLorg/lwjgl/d3d/Rectangle;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DTexture9_nAddDirtyRect
  (JNIEnv *env, jobject obj, jlong IDirect3DTexture9, jobject dirtyRect) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DTexture9
 * Method:    nGetLevelDesc
 * Signature: (JILorg/lwjgl/d3d/D3DSurfaceDesc;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DTexture9_nGetLevelDesc
  (JNIEnv *env, jobject obj, jlong IDirect3DTexture9, jint level, jobject desc) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DTexture9
 * Method:    nGetSurfaceLevel
 * Signature: (JILorg/lwjgl/d3d/IDirect3DSurface9;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DTexture9_nGetSurfaceLevel
  (JNIEnv *env, jobject obj, jlong IDirect3DTexture9, jint level, jobject surfaceLevel) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DTexture9
 * Method:    nLockRect
 * Signature: (JILorg/lwjgl/d3d/D3DLockedRect;Lorg/lwjgl/d3d/Rectangle;J)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DTexture9_nLockRect
  (JNIEnv *env, jobject obj, jlong IDirect3DTexture9, jint level, jobject lockedRect, jobject rect, jlong flags) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DTexture9
 * Method:    nUnlockRect
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DTexture9_nUnlockRect
  (JNIEnv *env, jobject obj, jlong IDirect3DTexture9, jint level) {
    return 0;
}
