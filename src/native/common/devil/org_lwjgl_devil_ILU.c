#include "extil.h"

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluAlienify
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluAlienify(JNIEnv *env, jclass clazz) {
    return iluAlienify();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluBlurAvg
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluBlurAvg(JNIEnv *env, jclass clazz, jint iter) {
    return iluBlurAvg((ILuint)iter);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluBlurGaussian
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluBlurGaussian(JNIEnv *env, jclass clazz, jint iter) {
    return iluBlurGaussian((ILuint)iter);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluBuildMipmaps
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluBuildMipmaps(JNIEnv *env, jclass clazz) {
    return iluBuildMipmaps();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluColoursUsed
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILU_iluColoursUsed(JNIEnv *env, jclass clazz) {
    return iluColoursUsed();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluCompareImage
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluCompareImage(JNIEnv *env, jclass clazz, jint comp) {
    return iluCompareImage((ILuint)comp);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluContrast
 * Signature: (F)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluContrast(JNIEnv *env, jclass clazz, jfloat contrast) {
    return iluContrast((ILfloat)contrast);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluCrop
 * Signature: (IIIIII)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluCrop(JNIEnv *env, jclass clazz, jint xOff, jint yOff, jint zOff, jint width, jint height, jint depth) {
    return iluCrop((ILuint)xOff, (ILuint)yOff, (ILuint)zOff, (ILuint)width, (ILuint)height, (ILuint)depth);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluDeleteImage
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_iluDeleteImage(JNIEnv *env, jclass clazz, jint id) {
    iluDeleteImage((ILuint)id);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEdgeDetectE
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEdgeDetectE(JNIEnv *env, jclass clazz) {
    return iluEdgeDetectE();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEdgeDetectP
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEdgeDetectP(JNIEnv *env, jclass clazz) {
    return iluEdgeDetectP();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEdgeDetectS
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEdgeDetectS(JNIEnv *env, jclass clazz) {
    return iluEdgeDetectS();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEmboss
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEmboss(JNIEnv *env, jclass clazz) {
    return iluEmboss();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEnlargeCanvas
 * Signature: (III)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEnlargeCanvas(JNIEnv *env, jclass clazz, jint width, jint height, jint depth) {
    return iluEnlargeCanvas((ILuint)width, (ILuint)height, (ILuint)depth);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEnlargeImage
 * Signature: (FFF)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEnlargeImage(JNIEnv *env, jclass clazz, jfloat xDim, jfloat yDim, jfloat zDim) {
    return iluEnlargeImage((ILfloat)xDim, (ILfloat)yDim, (ILfloat)zDim);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEqualize
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEqualize(JNIEnv *env, jclass clazz) {
    return iluEqualize();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluErrorString
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_devil_ILU_iluErrorString(JNIEnv *env, jclass clazz, jint error) {
    return NewStringNative(env, iluErrorString((ILenum)error));
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluFlipImage
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluFlipImage(JNIEnv *env, jclass clazz) {
    return iluFlipImage();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGammaCorrect
 * Signature: (F)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluGammaCorrect(JNIEnv *env, jclass clazz, jfloat gamma) {
    return iluGammaCorrect((ILfloat)gamma);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGenImage
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILU_iluGenImage(JNIEnv *env, jclass clazz) {
    return iluGenImage();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetImageInfo
 * Signature: ([Lorg/lwjgl/devil/ILinfo;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_iluGetImageInfo(JNIEnv *env, jclass clazz, jobjectArray info) {
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetInteger
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILU_iluGetInteger(JNIEnv *env, jclass clazz, jint mode) {
    return iluGetInteger((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetIntegerv
 * Signature: (ILjava/nio/IntBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_iluGetIntegerv(JNIEnv *env, jclass clazz, jint mode, jobject param_buffer, jint param_offset) {
    ILbyte *lists = (ILbyte *) safeGetBufferAddress(env, param_buffer, param_offset);
    ilGenImages((ILsizei)mode, (ILuint *)lists);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetString
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_devil_ILU_iluGetString(JNIEnv *env, jclass clazz, jint stringName) {
    return NewStringNative(env, iluGetString((ILenum)stringName));
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluImageParameter
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_iluImageParameter(JNIEnv *env, jclass clazz, jint pName, jint param) {
    iluImageParameter((ILenum)pName, (ILenum)param);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluInit
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_iluInit(JNIEnv *env, jclass clazz) {
    iluInit();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluInvertAlpha
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluInvertAlpha(JNIEnv *env, jclass clazz) {
    return iluInvertAlpha();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluLoadImage
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_ILU_iluLoadImage(JNIEnv *env, jclass clazz, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jint result = iluLoadImage((const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluMirror
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluMirror(JNIEnv *env, jclass clazz) {
    return iluMirror();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluNegative
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluNegative(JNIEnv *env, jclass clazz) {
    return iluNegative();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluNoisify
 * Signature: (F)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluNoisify(JNIEnv *env, jclass clazz, jfloat factor) {
    return iluNoisify((ILclampf)factor);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluPixelize
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluPixelize(JNIEnv *env, jclass clazz, jint pixSize) {
    return iluPixelize((ILuint)pixSize);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluRegionfv
 * Signature: ([Lorg/lwjgl/devil/ILpointf;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_iluRegionfv(JNIEnv *env, jclass clazz, jobjectArray points, jint n) {
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluRegioniv
 * Signature: ([Lorg/lwjgl/devil/ILpointi;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_iluRegioniv(JNIEnv *env, jclass clazz, jobjectArray points, jint n) {
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluReplaceColour
 * Signature: (BBBF)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluReplaceColour(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue, jfloat tolerence) {
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluRotate
 * Signature: (F)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluRotate(JNIEnv *env, jclass clazz, jfloat angle) {
    return iluRotate((ILfloat)angle);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSaturate1f
 * Signature: (F)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSaturate1f(JNIEnv *env, jclass clazz, jfloat saturation) {
    return iluSaturate1f((ILfloat)saturation);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSaturate4f
 * Signature: (FFFF)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSaturate4f(JNIEnv *env, jclass clazz, jfloat r, jfloat g, jfloat b, jfloat saturation) {
    return iluSaturate4f((ILfloat)r, (ILfloat)g, (ILfloat)b, (ILfloat)saturation);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluScale
 * Signature: (III)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluScale(JNIEnv *env, jclass clazz, jint width, jint height, jint depth) {
    return iluScale((ILuint)width, (ILuint)height, (ILuint)depth);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluScaleColours
 * Signature: (FFF)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluScaleColours(JNIEnv *env, jclass clazz, jfloat r, jfloat g, jfloat b) {
    return iluScaleColours((ILfloat)r, (ILfloat)g, (ILfloat)b);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSharpen
 * Signature: (FI)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSharpen(JNIEnv *env, jclass clazz, jfloat factor, jint iter) {
    return iluSharpen((ILfloat)factor, (ILuint)iter);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSwapColours
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSwapColours(JNIEnv *env, jclass clazz) {
    return iluSwapColours();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluWave
 * Signature: (F)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_ILU_iluWave(JNIEnv *env, jclass clazz, jfloat wave) {
    return iluWave((ILfloat)wave);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    nCreate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_nCreate(JNIEnv *env, jclass clazz) {
    /*if (!extilu_Open(env)) {
        throwException(env, "Failed to load DevIL library");
        return;
    }*/
}

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    initNativeStubs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILU_initNativeStubs(JNIEnv *env, jclass clazz) {
/*
    JavaMethodAndExtFunction functions[] = {
        {"iluAlienify", "()Z", (void*)&Java_org_lwjgl_devil_IL_iluAlienify, "iluAlienify", (void*)&iluAlienify},
    };
    int num_functions = NUMFUNCTIONS(functions);
    extil_InitializeClass(env, clazz, num_functions, functions);
*/
}

#ifdef __cplusplus
}
#endif
