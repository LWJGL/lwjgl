#include "extil.h"

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutRenderer
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutRenderer(JNIEnv *env, jclass clazz, jint renderer){
    return ilutRenderer((ILenum)renderer);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutDisable
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutDisable(JNIEnv *env, jclass clazz, jint mode){
    return ilutDisable((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutEnable
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutEnable(JNIEnv *env, jclass clazz, jint mode){
    return ilutEnable((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetBoolean
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGetBoolean(JNIEnv *env, jclass clazz, jint mode){
    return ilutGetBoolean((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetInteger
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGetInteger(JNIEnv *env, jclass clazz, jint mode){
    return ilutGetInteger((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetString
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_devil_ILUT_ilutGetString(JNIEnv *env, jclass clazz, jint stringName){
    return NewStringNative(env, ilutGetString((ILenum)stringName));
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutInit
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILUT_ilutInit(JNIEnv *env, jclass clazz){
    ilutInit();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutIsDisabled
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutIsDisabled(JNIEnv *env, jclass clazz, jint mode){
    return ilutIsDisabled((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutIsEnabled
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutIsEnabled(JNIEnv *env, jclass clazz, jint mode){
    return ilutIsEnabled((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutPopAttrib
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILUT_ilutPopAttrib(JNIEnv *env, jclass clazz){
    ilutPopAttrib();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutPushAttrib
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILUT_ilutPushAttrib(JNIEnv *env, jclass clazz, jint bits){
    ilutPushAttrib((ILuint)bits);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutSetInteger
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILUT_ilutSetInteger(JNIEnv *env, jclass clazz, jint mode, jint param){
    ilutSetInteger((ILenum)mode, (ILint)param);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLBindTexImage
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGLBindTexImage(JNIEnv *env, jclass clazz){
    return ilutGLBindTexImage();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLBindMipmaps
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGLBindMipmaps(JNIEnv *env, jclass clazz){
    return ilutGLBindMipmaps();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLBuildMipmaps
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLBuildMipmaps(JNIEnv *env, jclass clazz){
    return ilutGLBuildMipmaps();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLLoadImage
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGLLoadImage(JNIEnv *env, jclass clazz, jstring fileName){
    char *strFileName = GetStringNativeChars(env, fileName);
    jint result = ilutGLLoadImage((const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLScreen
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLScreen(JNIEnv *env, jclass clazz){
    return ilutGLScreen();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLScreenie
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLScreenie(JNIEnv *env, jclass clazz){
    return ilutGLScreenie();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLSaveImage
 * Signature: (Ljava/lang/String;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLSaveImage(JNIEnv *env, jclass clazz, jstring fileName, jint texID){
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilutGLSaveImage((const ILstring)strFileName, texID);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLSetTex
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLSetTex(JNIEnv *env, jclass clazz, jint texID){
    return ilutGLSetTex(texID);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLTexImage
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLTexImage(JNIEnv *env, jclass clazz, jint level){
    return ilutGLTexImage(level);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    nCreate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILUT_nCreate(JNIEnv *env, jclass clazz){
    /*if (!extilut_Open(env)) {
        throwException(env, "Failed to load ILUT library");
        return;
    }*/
}

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    initNativeStubs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILUT_initNativeStubs(JNIEnv *env, jclass clazz){
}

#ifdef __cplusplus
}
#endif
