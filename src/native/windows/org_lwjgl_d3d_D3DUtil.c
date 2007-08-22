#include <D3dx9math.h>
#include "org_lwjgl_d3d_D3DUtil.h"

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixLookAtLH
 * Signature: (Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixLookAtLH
  (JNIEnv *env, jclass clazz, jobject outBuffer, jobject eyeBuffer, jobject atBuffer, jobject upBuffer) {
    D3DXMATRIX* out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));
    CONST D3DXVECTOR3 *eye = (D3DXVECTOR3*)((*env)->GetDirectBufferAddress(env, eyeBuffer));
    CONST D3DXVECTOR3 *at = (D3DXVECTOR3*)((*env)->GetDirectBufferAddress(env, atBuffer));
    CONST D3DXVECTOR3 *up = (D3DXVECTOR3*)((*env)->GetDirectBufferAddress(env, upBuffer));

    D3DXMatrixLookAtLH(out, eye, at, up);
}

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixPerspectiveFovLH
 * Signature: (Ljava/nio/ByteBuffer;FFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixPerspectiveFovLH
  (JNIEnv *env, jclass clazz, jobject outBuffer, jfloat fovy, jfloat aspect, jfloat zn, jfloat zf) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));

    D3DXMatrixPerspectiveFovLH(out, fovy, aspect, zn, zf);
}

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixIdentity
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixIdentity
  (JNIEnv *env, jclass clazz, jobject outBuffer) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));

    D3DXMatrixIdentity(out);
}

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixScaling
 * Signature: (Ljava/nio/ByteBuffer;FFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixScaling
  (JNIEnv *env, jclass clazz, jobject outBuffer, jfloat sx, jfloat sy, jfloat sz) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));

    D3DXMatrixScaling(out, sx, sy, sz);
}

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixTranslation
 * Signature: (Ljava/nio/ByteBuffer;FFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixTranslation
  (JNIEnv *env, jclass clazz, jobject outBuffer, jfloat x, jfloat y, jfloat z) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));

    D3DXMatrixTranslation(out, x, y, z);
}


/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixRotationX
 * Signature: (Ljava/nio/ByteBuffer;F)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixRotationX
  (JNIEnv *env, jclass clazz, jobject outBuffer, jfloat angle) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));

    D3DXMatrixRotationY(out, angle);
}

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixRotationY
 * Signature: (Ljava/nio/ByteBuffer;F)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixRotationY
  (JNIEnv *env, jclass clazz, jobject outBuffer, jfloat angle) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));

    D3DXMatrixRotationY(out, angle);
}

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixRotationZ
 * Signature: (Ljava/nio/ByteBuffer;F)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixRotationZ
  (JNIEnv *env, jclass clazz, jobject outBuffer, jfloat angle) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));

    D3DXMatrixRotationY(out, angle);
}

/*
 * Class:     org_lwjgl_d3d_D3DUtil
 * Method:    nD3DXMatrixMultiply
 * Signature: (Ljava/nio/ByteBuffer;Lorg/lwjgl/d3d/D3DMatrix;Lorg/lwjgl/d3d/D3DMatrix;)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_d3d_D3DUtil_nD3DXMatrixMultiply
  (JNIEnv *env, jclass clazz, jobject outBuffer, jobject m1Buffer, jobject m2Buffer) {
    D3DXMATRIX *out = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, outBuffer));
    CONST D3DXMATRIX *m1 = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, m1Buffer));
    CONST D3DXMATRIX *m2 = (D3DXMATRIX*)((*env)->GetDirectBufferAddress(env, m2Buffer));

    D3DXMATRIX *result = D3DXMatrixMultiply(out, m1, m2);

    jobject resultBuffer = (*env)->NewDirectByteBuffer(env, result, sizeof(D3DXMATRIX));

    return resultBuffer;
}
