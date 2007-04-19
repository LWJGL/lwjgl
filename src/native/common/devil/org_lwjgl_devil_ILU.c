#include "extilu.h"
#include "org_lwjgl_devil_ILNative.h"

typedef ILboolean		(ILAPIENTRY *iluAlienifyPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluBlurAvgPROC) (ILuint Iter);
typedef ILboolean		(ILAPIENTRY *iluBlurGaussianPROC) (ILuint Iter);
typedef ILboolean		(ILAPIENTRY *iluBuildMipmapsPROC) (ILvoid);
typedef ILuint			(ILAPIENTRY *iluColoursUsedPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluCompareImagePROC) (ILuint Comp);
typedef ILboolean		(ILAPIENTRY *iluContrastPROC) (ILfloat Contrast);
typedef ILboolean		(ILAPIENTRY *iluCropPROC) (ILuint XOff, ILuint YOff, ILuint ZOff, ILuint Width, ILuint Height, ILuint Depth);
typedef ILvoid			(ILAPIENTRY *iluDeleteImagePROC) (ILuint Id);
typedef ILboolean		(ILAPIENTRY *iluEdgeDetectEPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluEdgeDetectPPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluEdgeDetectSPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluEmbossPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluEnlargeCanvasPROC) (ILuint Width, ILuint Height, ILuint Depth);
typedef ILboolean		(ILAPIENTRY *iluEnlargeImagePROC) (ILfloat XDim, ILfloat YDim, ILfloat ZDim);
typedef ILboolean		(ILAPIENTRY *iluEqualizePROC) (ILvoid);
typedef const ILstring		(ILAPIENTRY *iluErrorStringPROC) (ILenum Error);
typedef ILboolean		(ILAPIENTRY *iluFlipImagePROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluGammaCorrectPROC) (ILfloat Gamma);
typedef ILuint			(ILAPIENTRY *iluGenImagePROC) (ILvoid);
typedef ILvoid			(ILAPIENTRY *iluGetImageInfoPROC) (ILinfo *Info);
typedef ILint			(ILAPIENTRY *iluGetIntegerPROC) (ILenum Mode);
typedef ILvoid			(ILAPIENTRY *iluGetIntegervPROC) (ILenum Mode, ILint *Param);
typedef const ILstring		(ILAPIENTRY *iluGetStringPROC) (ILenum StringName);
typedef ILvoid			(ILAPIENTRY *iluImageParameterPROC) (ILenum PName, ILenum Param);
typedef ILvoid			(ILAPIENTRY *iluInitPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluInvertAlphaPROC) (ILvoid);
typedef ILuint			(ILAPIENTRY *iluLoadImagePROC) (const ILstring FileName);
typedef ILboolean		(ILAPIENTRY *iluMirrorPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluNegativePROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluNoisifyPROC) (ILclampf Tolerance);
typedef ILboolean		(ILAPIENTRY *iluPixelizePROC) (ILuint PixSize);
typedef ILboolean		(ILAPIENTRY *iluReplaceColourPROC) (ILubyte Red, ILubyte Green, ILubyte Blue, ILfloat Tolerance);
typedef ILboolean		(ILAPIENTRY *iluRotatePROC) (ILfloat Angle);
typedef ILboolean		(ILAPIENTRY *iluSaturate1fPROC) (ILfloat Saturation);
typedef ILboolean		(ILAPIENTRY *iluSaturate4fPROC) (ILfloat r, ILfloat g, ILfloat b, ILfloat Saturation);
typedef ILboolean		(ILAPIENTRY *iluScalePROC) (ILuint Width, ILuint Height, ILuint Depth);
typedef ILboolean		(ILAPIENTRY *iluScaleColoursPROC) (ILfloat r, ILfloat g, ILfloat b);
typedef ILboolean		(ILAPIENTRY *iluSharpenPROC) (ILfloat Factor, ILuint Iter);
typedef ILboolean		(ILAPIENTRY *iluSwapColoursPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *iluWavePROC) (ILfloat Angle);

// not bound
//typedef ILvoid		(ILAPIENTRY *iluRegionfvPROC) (ILpointf *Points, ILuint n);
//typedef ILvoid		(ILAPIENTRY *iluRegionivPROC) (ILpointi *Points, ILuint n);
//typedef ILboolean		(ILAPIENTRY *iluRotate3DPROC) (ILfloat x, ILfloat y, ILfloat z, ILfloat Angle);

static iluAlienifyPROC iluAlienify;
static iluBlurAvgPROC iluBlurAvg;
static iluBlurGaussianPROC iluBlurGaussian;
static iluBuildMipmapsPROC iluBuildMipmaps;
static iluColoursUsedPROC iluColoursUsed;
static iluCompareImagePROC iluCompareImage;
static iluContrastPROC iluContrast;
static iluCropPROC iluCrop;
static iluDeleteImagePROC iluDeleteImage;
static iluEdgeDetectEPROC iluEdgeDetectE;
static iluEdgeDetectPPROC iluEdgeDetectP;
static iluEdgeDetectSPROC iluEdgeDetectS;
static iluEmbossPROC iluEmboss;
static iluEnlargeCanvasPROC iluEnlargeCanvas;
static iluEnlargeImagePROC iluEnlargeImage;
static iluEqualizePROC iluEqualize;
static iluErrorStringPROC iluErrorString;
static iluFlipImagePROC iluFlipImage;
static iluGammaCorrectPROC iluGammaCorrect;
static iluGenImagePROC iluGenImage;
static iluGetImageInfoPROC iluGetImageInfo;
static iluGetIntegerPROC iluGetInteger;
static iluGetIntegervPROC iluGetIntegerv;
static iluGetStringPROC iluGetString;
static iluImageParameterPROC iluImageParameter;
static iluInitPROC iluInit;
static iluInvertAlphaPROC iluInvertAlpha;
static iluLoadImagePROC iluLoadImage;
static iluMirrorPROC iluMirror;
static iluNegativePROC iluNegative;
static iluNoisifyPROC iluNoisify;
static iluPixelizePROC iluPixelize;
static iluReplaceColourPROC iluReplaceColour;
static iluRotatePROC iluRotate;
static iluSaturate1fPROC iluSaturate1f;
static iluSaturate4fPROC iluSaturate4f;
static iluScalePROC iluScale;
static iluScaleColoursPROC iluScaleColours;
static iluSharpenPROC iluSharpen;
static iluSwapColoursPROC iluSwapColours;
static iluWavePROC iluWave;

// not bound
//static iluRegionfvPROC iluRegionfv;
//static iluRegionivPROC iluRegioniv;
//static iluRotate3DPROC iluRotate3D;

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluAlienify
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluAlienify(JNIEnv *env, jclass clazz) {
    return iluAlienify();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluBlurAvg
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluBlurAvg(JNIEnv *env, jclass clazz, jint iter) {
    return iluBlurAvg((ILuint)iter);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluBlurGaussian
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluBlurGaussian(JNIEnv *env, jclass clazz, jint iter) {
    return iluBlurGaussian((ILuint)iter);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluBuildMipmaps
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluBuildMipmaps(JNIEnv *env, jclass clazz) {
    return iluBuildMipmaps();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluColoursUsed
 * Signature: ()I
 */
static jint JNICALL Java_org_lwjgl_devil_ILU_iluColoursUsed(JNIEnv *env, jclass clazz) {
    return iluColoursUsed();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluCompareImage
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluCompareImage(JNIEnv *env, jclass clazz, jint comp) {
    return iluCompareImage((ILuint)comp);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluContrast
 * Signature: (F)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluContrast(JNIEnv *env, jclass clazz, jfloat contrast) {
    return iluContrast((ILfloat)contrast);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluCrop
 * Signature: (IIIIII)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluCrop(JNIEnv *env, jclass clazz, jint xOff, jint yOff, jint zOff, jint width, jint height, jint depth) {
    return iluCrop((ILuint)xOff, (ILuint)yOff, (ILuint)zOff, (ILuint)width, (ILuint)height, (ILuint)depth);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluDeleteImage
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_devil_ILU_iluDeleteImage(JNIEnv *env, jclass clazz, jint id) {
    iluDeleteImage((ILuint)id);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEdgeDetectE
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEdgeDetectE(JNIEnv *env, jclass clazz) {
    return iluEdgeDetectE();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEdgeDetectP
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEdgeDetectP(JNIEnv *env, jclass clazz) {
    return iluEdgeDetectP();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEdgeDetectS
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEdgeDetectS(JNIEnv *env, jclass clazz) {
    return iluEdgeDetectS();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEmboss
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEmboss(JNIEnv *env, jclass clazz) {
    return iluEmboss();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEnlargeCanvas
 * Signature: (III)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEnlargeCanvas(JNIEnv *env, jclass clazz, jint width, jint height, jint depth) {
    return iluEnlargeCanvas((ILuint)width, (ILuint)height, (ILuint)depth);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEnlargeImage
 * Signature: (FFF)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEnlargeImage(JNIEnv *env, jclass clazz, jfloat xDim, jfloat yDim, jfloat zDim) {
    return iluEnlargeImage((ILfloat)xDim, (ILfloat)yDim, (ILfloat)zDim);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluEqualize
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluEqualize(JNIEnv *env, jclass clazz) {
    return iluEqualize();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluErrorString
 * Signature: (I)Ljava/lang/String;
 */
static jstring JNICALL Java_org_lwjgl_devil_ILU_iluErrorString(JNIEnv *env, jclass clazz, jint error) {
		char * errorstring = iluErrorString((ILenum)error);
    return NewStringNativeWithLength(env, errorstring, strlen(errorstring));
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluFlipImage
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluFlipImage(JNIEnv *env, jclass clazz) {
    return iluFlipImage();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGammaCorrect
 * Signature: (F)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluGammaCorrect(JNIEnv *env, jclass clazz, jfloat gamma) {
    return iluGammaCorrect((ILfloat)gamma);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGenImage
 * Signature: ()I
 */
static jint JNICALL Java_org_lwjgl_devil_ILU_iluGenImage(JNIEnv *env, jclass clazz) {
    return iluGenImage();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetImageInfo
 * Signature: (Lorg/lwjgl/devil/ILinfo;)V
 */
static void JNICALL Java_org_lwjgl_devil_ILU_iluGetImageInfo(JNIEnv *env, jclass clazz, jobject info) {
    jfieldID fieldId;
    ILinfo *imageInfo;

    if(info == 0) {
        throwException(env, "ILinfo object must not be null.");
    }

    imageInfo = (ILinfo *)malloc(sizeof(ILinfo));
    iluGetImageInfo(imageInfo);

    clazz = (*env)->GetObjectClass(env, info);

    fieldId = (*env)->GetFieldID(env, clazz, "id", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->Id);

    fieldId = (*env)->GetFieldID(env, clazz, "width", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->Width);

    fieldId = (*env)->GetFieldID(env, clazz, "height", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->Height);

    fieldId = (*env)->GetFieldID(env, clazz, "depth", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->Depth);

    fieldId = (*env)->GetFieldID(env, clazz, "bpp", "B");
    (*env)->SetByteField(env, info, fieldId, (jbyte)imageInfo->Bpp);

    fieldId = (*env)->GetFieldID(env, clazz, "sizeOfData", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->SizeOfData);

    fieldId = (*env)->GetFieldID(env, clazz, "format", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->Format);

    fieldId = (*env)->GetFieldID(env, clazz, "type", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->Type);

    fieldId = (*env)->GetFieldID(env, clazz, "origin", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->Origin);

    fieldId = (*env)->GetFieldID(env, clazz, "palType", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->PalType);

    fieldId = (*env)->GetFieldID(env, clazz, "palSize", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->PalSize);

    fieldId = (*env)->GetFieldID(env, clazz, "cubeFlags", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->CubeFlags);

    fieldId = (*env)->GetFieldID(env, clazz, "numNext", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->NumNext);

    fieldId = (*env)->GetFieldID(env, clazz, "numMips", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->NumMips);

    fieldId = (*env)->GetFieldID(env, clazz, "numLayers", "I");
    (*env)->SetIntField(env, info, fieldId, (jint)imageInfo->NumLayers);

    free(imageInfo);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetInteger
 * Signature: (I)I
 */
static jint JNICALL Java_org_lwjgl_devil_ILU_iluGetInteger(JNIEnv *env, jclass clazz, jint mode) {
    return iluGetInteger((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetIntegerv
 * Signature: (ILjava/nio/IntBuffer;)V
 */
static void JNICALL Java_org_lwjgl_devil_ILU_niluGetIntegerv(JNIEnv *env, jclass clazz, jint mode, jobject param_buffer, jint param_offset) {
    ILbyte *lists = (ILbyte *) safeGetBufferAddress(env, param_buffer) + param_offset;
    iluGetIntegerv((ILsizei)mode, (ILuint *)lists);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluGetString
 * Signature: (I)Ljava/lang/String;
 */
static jstring JNICALL Java_org_lwjgl_devil_ILU_iluGetString(JNIEnv *env, jclass clazz, jint stringName) {
		char * name = iluGetString((ILenum)stringName);
    return NewStringNativeWithLength(env, name, strlen(name));
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluImageParameter
 * Signature: (II)V
 */
static void JNICALL Java_org_lwjgl_devil_ILU_iluImageParameter(JNIEnv *env, jclass clazz, jint pName, jint param) {
    iluImageParameter((ILenum)pName, (ILenum)param);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluInit
 * Signature: ()V
 */
static void JNICALL Java_org_lwjgl_devil_ILU_iluInit(JNIEnv *env, jclass clazz) {
    iluInit();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluInvertAlpha
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluInvertAlpha(JNIEnv *env, jclass clazz) {
    return iluInvertAlpha();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluLoadImage
 * Signature: (Ljava/lang/String;)I
 */
static jint JNICALL Java_org_lwjgl_devil_ILU_iluLoadImage(JNIEnv *env, jclass clazz, jstring fileName) {
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
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluMirror(JNIEnv *env, jclass clazz) {
    return iluMirror();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluNegative
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluNegative(JNIEnv *env, jclass clazz) {
    return iluNegative();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluNoisify
 * Signature: (F)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluNoisify(JNIEnv *env, jclass clazz, jfloat factor) {
    return iluNoisify((ILclampf)factor);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluPixelize
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluPixelize(JNIEnv *env, jclass clazz, jint pixSize) {
    return iluPixelize((ILuint)pixSize);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluReplaceColour
 * Signature: (BBBF)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluReplaceColour(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue, jfloat tolerence) {
	return false;
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluRotate
 * Signature: (F)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluRotate(JNIEnv *env, jclass clazz, jfloat angle) {
    return iluRotate((ILfloat)angle);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSaturate1f
 * Signature: (F)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSaturate1f(JNIEnv *env, jclass clazz, jfloat saturation) {
    return iluSaturate1f((ILfloat)saturation);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSaturate4f
 * Signature: (FFFF)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSaturate4f(JNIEnv *env, jclass clazz, jfloat r, jfloat g, jfloat b, jfloat saturation) {
    return iluSaturate4f((ILfloat)r, (ILfloat)g, (ILfloat)b, (ILfloat)saturation);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluScale
 * Signature: (III)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluScale(JNIEnv *env, jclass clazz, jint width, jint height, jint depth) {
    return iluScale((ILuint)width, (ILuint)height, (ILuint)depth);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluScaleColours
 * Signature: (FFF)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluScaleColours(JNIEnv *env, jclass clazz, jfloat r, jfloat g, jfloat b) {
    return iluScaleColours((ILfloat)r, (ILfloat)g, (ILfloat)b);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSharpen
 * Signature: (FI)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSharpen(JNIEnv *env, jclass clazz, jfloat factor, jint iter) {
    return iluSharpen((ILfloat)factor, (ILuint)iter);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluSwapColours
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluSwapColours(JNIEnv *env, jclass clazz) {
    return iluSwapColours();
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    iluWave
 * Signature: (F)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILU_iluWave(JNIEnv *env, jclass clazz, jfloat wave) {
    return iluWave((ILfloat)wave);
}

/*
 * Class:     org_lwjgl_devil_ILU
 * Method:    nCreate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_nCreateILU(JNIEnv *env, jclass clazz, jobjectArray iluPaths) {
	if (!extilu_Open(env, iluPaths)) {
        throwException(env, "Failed to load ILU library");
        return;
    }
}

JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_nDestroyILU(JNIEnv *env, jclass clazz) {
	extilu_Close();
}

JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_resetNativeStubsILU(JNIEnv *env, jclass clazz, jclass ilu_class) {
	(*env)->UnregisterNatives(env, ilu_class);
}

JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_initNativeStubsILU(JNIEnv *env, jclass clazz, jclass ilu_class) {
    JavaMethodAndExtFunction functions[] = {
        {"iluAlienify", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluAlienify, "iluAlienify", (void*)&iluAlienify},
		{"iluBlurAvg", "(I)Z", (void*)&Java_org_lwjgl_devil_ILU_iluBlurAvg, "iluBlurAvg", (void*)&iluBlurAvg},
		{"iluBlurGaussian", "(I)Z", (void*)&Java_org_lwjgl_devil_ILU_iluBlurGaussian, "iluBlurGaussian", (void*)&iluBlurGaussian},
		{"iluBuildMipmaps", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluBuildMipmaps, "iluBuildMipmaps", (void*)&iluBuildMipmaps},
		{"iluColoursUsed", "()I", (void*)&Java_org_lwjgl_devil_ILU_iluColoursUsed, "iluColoursUsed", (void*)&iluColoursUsed},
		{"iluCompareImage", "(I)Z", (void*)&Java_org_lwjgl_devil_ILU_iluCompareImage, "iluCompareImage", (void*)&iluCompareImage},
		{"iluContrast", "(F)Z", (void*)&Java_org_lwjgl_devil_ILU_iluContrast, "iluContrast", (void*)&iluContrast},
		{"iluCrop", "(IIIIII)Z", (void*)&Java_org_lwjgl_devil_ILU_iluCrop, "iluCrop", (void*)&iluCrop},
		{"iluDeleteImage", "(I)V", (void*)&Java_org_lwjgl_devil_ILU_iluDeleteImage, "iluDeleteImage", (void*)&iluDeleteImage},
		{"iluEdgeDetectE", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluEdgeDetectE, "iluEdgeDetectE", (void*)&iluEdgeDetectE},
		{"iluEdgeDetectP", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluEdgeDetectP, "iluEdgeDetectP", (void*)&iluEdgeDetectP},
		{"iluEdgeDetectS", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluEdgeDetectS, "iluEdgeDetectS", (void*)&iluEdgeDetectS},
		{"iluEmboss", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluEmboss, "iluEmboss", (void*)&iluEmboss},
		{"iluEnlargeCanvas", "(III)Z", (void*)&Java_org_lwjgl_devil_ILU_iluEnlargeCanvas, "iluEnlargeCanvas", (void*)&iluEnlargeCanvas},
		{"iluEnlargeImage", "(FFF)Z", (void*)&Java_org_lwjgl_devil_ILU_iluEnlargeImage, "iluEnlargeImage", (void*)&iluEnlargeImage},
		{"iluEqualize", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluEqualize, "iluEqualize", (void*)&iluEqualize},
		{"iluErrorString", "(I)Ljava/lang/String;", (void*)&Java_org_lwjgl_devil_ILU_iluErrorString, "iluErrorString", (void*)&iluErrorString},
		{"iluFlipImage", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluFlipImage, "iluFlipImage", (void*)&iluFlipImage},
		{"iluGammaCorrect", "(F)Z", (void*)&Java_org_lwjgl_devil_ILU_iluGammaCorrect, "iluGammaCorrect", (void*)&iluGammaCorrect},
		{"iluGenImage", "()I", (void*)&Java_org_lwjgl_devil_ILU_iluGenImage, "iluGenImage", (void*)&iluGenImage},
		{"iluGetImageInfo", "(Lorg/lwjgl/devil/ILinfo;)V", (void*)&Java_org_lwjgl_devil_ILU_iluGetImageInfo, "iluGetImageInfo", (void*)&iluGetImageInfo},
		{"iluGetInteger", "(I)I", (void*)&Java_org_lwjgl_devil_ILU_iluGetInteger, "iluGetInteger", (void*)&iluGetInteger},
		{"niluGetIntegerv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_devil_ILU_niluGetIntegerv, "iluGetIntegerv", (void*)&iluGetIntegerv},
		{"iluGetString", "(I)Ljava/lang/String;", (void*)&Java_org_lwjgl_devil_ILU_iluGetString, "iluGetString", (void*)&iluGetString},
		{"iluImageParameter", "(II)V", (void*)&Java_org_lwjgl_devil_ILU_iluImageParameter, "iluImageParameter", (void*)&iluImageParameter},
		{"iluInit", "()V", (void*)&Java_org_lwjgl_devil_ILU_iluInit, "iluInit", (void*)&iluInit},
		{"iluInvertAlpha", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluInvertAlpha, "iluInvertAlpha", (void*)&iluInvertAlpha},
		{"iluLoadImage", "(Ljava/lang/String;)I", (void*)&Java_org_lwjgl_devil_ILU_iluLoadImage, "iluLoadImage", (void*)&iluLoadImage},
		{"iluMirror", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluMirror, "iluMirror", (void*)&iluMirror},
		{"iluNegative", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluNegative, "iluNegative", (void*)&iluNegative},
		{"iluNoisify", "(F)Z", (void*)&Java_org_lwjgl_devil_ILU_iluNoisify, "iluNoisify", (void*)&iluNoisify},
		{"iluPixelize", "(I)Z", (void*)&Java_org_lwjgl_devil_ILU_iluPixelize, "iluPixelize", (void*)&iluPixelize},
		{"iluReplaceColour", "(BBBF)Z", (void*)&Java_org_lwjgl_devil_ILU_iluReplaceColour, "iluReplaceColour", (void*)&iluReplaceColour},
		{"iluRotate", "(F)Z", (void*)&Java_org_lwjgl_devil_ILU_iluRotate, "iluRotate", (void*)&iluRotate},
		{"iluSaturate1f", "(F)Z", (void*)&Java_org_lwjgl_devil_ILU_iluSaturate1f, "iluSaturate1f", (void*)&iluSaturate1f},
		{"iluSaturate4f", "(FFFF)Z", (void*)&Java_org_lwjgl_devil_ILU_iluSaturate4f, "iluSaturate4f", (void*)&iluSaturate4f},
		{"iluScale", "(III)Z", (void*)&Java_org_lwjgl_devil_ILU_iluScale, "iluScale", (void*)&iluScale},
		{"iluScaleColours", "(FFF)Z", (void*)&Java_org_lwjgl_devil_ILU_iluScaleColours, "iluScaleColours", (void*)&iluScaleColours},
		{"iluSharpen", "(FI)Z", (void*)&Java_org_lwjgl_devil_ILU_iluSharpen, "iluSharpen", (void*)&iluSharpen},
		{"iluSwapColours", "()Z", (void*)&Java_org_lwjgl_devil_ILU_iluSwapColours, "iluSwapColours", (void*)&iluSwapColours},
		{"iluWave", "(F)Z", (void*)&Java_org_lwjgl_devil_ILU_iluWave, "iluWave", (void*)&iluWave},
    };
    int num_functions = NUMFUNCTIONS(functions);
    extilu_InitializeClass(env, ilu_class, num_functions, functions);
}
