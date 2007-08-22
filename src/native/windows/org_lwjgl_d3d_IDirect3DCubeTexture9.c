#include "org_lwjgl_d3d_IDirect3DCubeTexture9.h"

/*
 * Class:     org_lwjgl_d3d_IDirect3DCubeTexture9
 * Method:    nAddDirtyRect
 * Signature: (JILorg/lwjgl/d3d/Rectangle;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DCubeTexture9_nAddDirtyRect
  (JNIEnv *env, jobject obj, jlong IDirect3DCubeTexture9, jint faceType, jobject dirtyRect) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DCubeTexture9
 * Method:    nGetCubeMapSurface
 * Signature: (JIILorg/lwjgl/d3d/IDirect3DSurface9;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DCubeTexture9_nGetCubeMapSurface
  (JNIEnv *env, jobject obj, jlong IDirect3DCubeTexture9, jint faceType, jint level, jobject cubeMapSurface) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DCubeTexture9
 * Method:    nGetLevelDesc
 * Signature: (JILorg/lwjgl/d3d/D3DSsurfaceDesc;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DCubeTexture9_nGetLevelDesc
  (JNIEnv *env, jobject obj, jlong IDirect3DCubeTexture9, jint level, jobject desc) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DCubeTexture9
 * Method:    nLockRect
 * Signature: (JIILorg/lwjgl/d3d/D3DLockedRect;Lorg/lwjgl/d3d/Rectangle;J)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DCubeTexture9_nLockRect
  (JNIEnv *env, jobject obj, jlong IDirect3DCubeTexture9, jint faceType, jint level, jobject lockedRect, jobject rect, jlong flags) {
    return 0;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DCubeTexture9
 * Method:    nUnlockRect
 * Signature: (JII)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DCubeTexture9_nUnlockRect
  (JNIEnv *env, jobject obj, jlong IDirect3DCubeTexture9, jint faceType, jint level) {
    return 0;
}
