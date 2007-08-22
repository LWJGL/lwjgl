#include <d3d9.h>
#include "org_lwjgl_d3d_IDirect3DVertexBuffer9.h"

extern void setPointer(JNIEnv *env, jobject classInstance, const char* methodName, jlong pointer);
//void setPointer(JNIEnv *env, jobject classInstance, const char* methodName, jlong pointer) {
//    jclass clazz = (*env)->GetObjectClass(env, classInstance);
//    jmethodID mid = (*env)->GetMethodID(env, clazz, methodName, "(J)V");
//    if (mid != NULL) {
//        (*env)->CallVoidMethod(env, classInstance, mid, pointer);
//    }
//}

/*
 * Class:     org_lwjgl_d3d_IDirect3DVertexBuffer9
 * Method:    nGetDesc
 * Signature: (JLjava/nio/ByteBuffer;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DVertexBuffer9_nGetDesc
  (JNIEnv *env, jobject obj, jlong iDirect3DVertexBuffer9, jobject descBuffer) {
    D3DVERTEXBUFFER_DESC *desc = (D3DVERTEXBUFFER_DESC*)((*env)->GetDirectBufferAddress(env, descBuffer));

    return IDirect3DVertexBuffer9_GetDesc((IDirect3DVertexBuffer9*)iDirect3DVertexBuffer9, desc);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DVertexBuffer9
 * Method:    nLock
 * Signature: (JIILjava/nio/ByteBuffer;JI)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DVertexBuffer9_nLock
  (JNIEnv *env, jobject obj, jlong iDirect3DVertexBuffer9, jint offsetToLock, jint sizeToLock, jobject ataBuffer, jlong flags, jint bufferSize) {
    void *ata;

    HRESULT hResult = IDirect3DVertexBuffer9_Lock((IDirect3DVertexBuffer9*)iDirect3DVertexBuffer9, offsetToLock, sizeToLock, &ata, flags);

    void *ataAddress = (void*)((*env)->GetDirectBufferAddress(env, ataBuffer));
    memcpy(ata, ataAddress, bufferSize);

    return hResult;
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DVertexBuffer9
 * Method:    nUnlock
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_IDirect3DVertexBuffer9_nUnlock
  (JNIEnv *env, jobject obj, jlong iDirect3DVertexBuffer9) {
    return IDirect3DVertexBuffer9_Unlock((IDirect3DVertexBuffer9*)iDirect3DVertexBuffer9);
}

/*
 * Class:     org_lwjgl_d3d_IDirect3DVertexBuffer9
 * Method:    create
 * Signature: (Lorg/lwjgl/d3d/IDirect3DVertexBuffer9;)J
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_IDirect3DVertexBuffer9_create
  (JNIEnv *env, jobject obj, jobject iDirect3DVertexBuffer9) {
    IDirect3DVertexBuffer9 *buffer = NULL;

    setPointer(env, iDirect3DVertexBuffer9, "setIDirect3DVertexBuffer9", (jlong)buffer);
}

