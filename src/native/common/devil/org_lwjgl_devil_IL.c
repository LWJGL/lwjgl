#include "extil.h"

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilActiveImage
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilActiveImage(JNIEnv *env, jclass clazz, jint number) {
    return ilActiveImage((ILuint)number);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilActiveLayer
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilActiveLayer(JNIEnv *env, jclass clazz, jint number) {
    return ilActiveLayer((ILuint)number);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilActiveMipmap
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilActiveMipmap(JNIEnv *env, jclass clazz, jint number) {
    return ilActiveMipmap((ILuint)number);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilApplyPal
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilApplyPal(JNIEnv *env, jclass clazz, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilApplyPal((const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilApplyProfile
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilApplyProfile(JNIEnv *env, jclass clazz, jstring inProfile, jstring outProfile) {
    char *strInProfile = GetStringNativeChars(env, inProfile);
    char *strOutProfile = GetStringNativeChars(env, outProfile);
    jboolean result = ilApplyProfile((const ILstring)strInProfile, (const ILstring)strOutProfile);
    free(strInProfile);
    free(strOutProfile);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilBindImage
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilBindImage(JNIEnv *env, jclass clazz, jint image) {
    ilBindImage(image);
}


/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilBlit
 * Signature: (IIIIIIIIII)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilBlit(JNIEnv *env, jclass clazz, jint source, jint destX, jint destY, jint destZ, jint srcX, jint srcY, jint srcZ, jint width, jint height, jint depth) {
    return ilBlit((ILuint)source, (ILint)destX, (ILint)destY, (ILint)destZ, (ILuint)srcX, (ILuint)srcY, (ILuint)srcZ, (ILuint)width, (ILuint)height, (ILuint)depth);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilClearColour
 * Signature: (FFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilClearColour(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
    ilClearColour((ILclampf)red, (ILclampf)green, (ILclampf)blue, (ILclampf)alpha);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilClearImage
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilClearImage(JNIEnv *env, jclass clazz) {
    return ilClearImage();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilCloneCurImage
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_IL_ilCloneCurImage(JNIEnv *env, jclass clazz) {
    return ilCloneCurImage();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilCompressFunc
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilCompressFunc(JNIEnv *env, jclass clazz, jint mode) {
    return ilCompressFunc((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilConvertImage
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilConvertImage(JNIEnv *env, jclass clazz, jint destFormat, jint destType) {
    return ilConvertImage((ILenum)destFormat, (ILenum)destType);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilConvertPal
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilConvertPal(JNIEnv *env, jclass clazz, jint destFormat) {
    return ilConvertPal((ILenum)destFormat);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilCopyImage
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilCopyImage(JNIEnv *env, jclass clazz, jint src) {
    return ilCopyImage((ILuint)src);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilCopyPixels
 * Signature: (IIIIIIIILjava/nio/ByteBuffer;I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_IL_nilCopyPixels(JNIEnv *env, jclass clazz, jint xOff, jint yOff, jint zOff, jint width, jint height, jint depth, jint format, jint type, jobject data_buffer, jint data_offset) {
    ILbyte *data = (ILbyte *)(*env)->GetDirectBufferAddress(env, data_buffer);

    return ilCopyPixels((ILuint)xOff, (ILuint)yOff, (ILuint)zOff, (ILuint)width, (ILuint)height, (ILuint)depth, (ILenum)format, (ILenum)type, (ILvoid *)(data + data_offset));
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilCreateSubImage
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_IL_ilCreateSubImage(JNIEnv *env, jclass clazz, jint type, jint num) {
    return ilCreateSubImage((ILenum)type, (ILuint)num);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilDefaultImage
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilDefaultImage(JNIEnv *env, jclass clazz) {
    return ilDefaultImage();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilDeleteImages
 * Signature: (ILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_nilDeleteImages(JNIEnv * env, jclass clazz, jint num, jobject lists_buffer, jint lists_offset) {
    ILbyte *lists = (ILbyte *) safeGetBufferAddress(env, lists_buffer, lists_offset);
    ilDeleteImages((ILsizei)num, (ILuint *)lists);
}


/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilDisable
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilDisable(JNIEnv *env, jclass clazz, jint mode) {
    return ilDisable((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilEnable
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilEnable(JNIEnv *env, jclass clazz, jint mode) {
    return ilEnable((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilFormatFunc
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilFormatFunc(JNIEnv *env, jclass clazz, jint mode) {
    return ilFormatFunc((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilGenImages
 * Signature: (ILjava/nio/IntBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_nilGenImages(JNIEnv *env , jclass clazz, jint num, jobject lists_buffer, jint lists_offset) {
    ILbyte *lists = (ILbyte *) safeGetBufferAddress(env, lists_buffer, lists_offset);
    ilGenImages((ILsizei)num, (ILuint *)lists);
}


/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetAlpha
 * Signature: (I)[B
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_devil_IL_ilGetAlpha(JNIEnv *env, jclass clazz, jint type) {
    ILubyte *alpha = ilGetAlpha((ILenum)type);

    int size = sizeof(alpha);
    jobject result = safeNewBuffer(env, alpha, size);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilModAlpha
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilModAlpha(JNIEnv *env, jclass clazz, jint alphaValue) {
    ilModAlpha((ILint)alphaValue);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSetAlpha
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilSetAlpha(JNIEnv *env, jclass clazz, jint alphaValue) {
    ilSetAlpha((ILuint)alphaValue);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetBoolean
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilGetBoolean(JNIEnv *env, jclass clazz, jint mode) {
    return ilGetBoolean((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetData
 * Signature: ()[B
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_devil_IL_ilGetData(JNIEnv * env, jclass clazz) {
    ILubyte *data = ilGetData();

    int size = ilGetInteger(IL_IMAGE_WIDTH) * ilGetInteger(IL_IMAGE_HEIGHT) * ilGetInteger(IL_IMAGE_BYTES_PER_PIXEL);
    jobject result = safeNewBuffer(env, data, size);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetError
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_IL_ilGetError(JNIEnv *env, jclass clazz) {
    return ilGetError();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetInteger
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_IL_ilGetInteger(JNIEnv *env, jclass clazz, jint mode) {
    return ilGetInteger((ILenum)mode);
}


/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetLumpPos
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_IL_ilGetLumpPos(JNIEnv *env, jclass clazz) {
    return ilGetLumpPos();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetPalette
 * Signature: ()[B
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_devil_IL_ilGetPalette(JNIEnv *env, jclass clazz) {
    ILubyte *pal = ilGetPalette();

    int size = sizeof(pal);
    jobject result = safeNewBuffer(env, pal, size);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetString
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_devil_IL_ilGetString(JNIEnv *env, jclass clazz, jint stringName) {
    return NewStringNative(env, ilGetString((ILenum)stringName));
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilHint
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilHint(JNIEnv *env, jclass clazz, jint target, jint mode) {
    ilHint((ILenum)target, (ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilInit
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilInit(JNIEnv * env, jclass clazz) {
    ilInit();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilIsDisabled
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilIsDisabled(JNIEnv *env, jclass clazz, jint mode) {
    return ilIsDisabled((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilIsEnabled
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilIsEnabled(JNIEnv *env, jclass clazz, jint mode) {
    return ilIsEnabled((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilIsImage
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilIsImage(JNIEnv *env, jclass clazz, jint image) {
    return ilIsImage((ILuint)image);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilIsValid
 * Signature: (ILjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilIsValid(JNIEnv *env, jclass clazz, jint type, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilIsValid((ILuint)type, (const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilIsValidL
 * Signature: (ILjava/nio/ByteBuffer;II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_nilIsValidL(JNIEnv *env, jclass clazz, jint type, jobject lump_buffer, jint lump_offset, jint size) {
    ILbyte *lump = (ILbyte *)(*env)->GetDirectBufferAddress(env, lump_buffer);

    return ilIsValidL((ILenum)type, (ILvoid *)(lump + lump_offset), (ILuint)size);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilKeyColour
 * Signature: (FFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilKeyColour(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
    ilKeyColour((ILclampf)red, (ILclampf)green, (ILclampf)blue, (ILclampf)alpha);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilLoad
 * Signature: (ILjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilLoad(JNIEnv *env, jclass clazz, jint type, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilLoad((ILenum)type, (const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilLoadImage
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilLoadImage(JNIEnv *env, jclass clazz, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilLoadImage((const ILstring)strFileName);
    free(strFileName);

    return result;
}


/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilLoadL
 * Signature: (ILjava/nio/ByteBuffer;II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_nilLoadL(JNIEnv *env, jclass clazz, jint type, jobject lump_buffer, jint lump_offset, jint size) {
    ILbyte *lump = (ILbyte *)safeGetBufferAddress(env, lump_buffer, lump_offset);

    return ilLoadL((ILenum)type, (ILvoid *)(lump), (ILuint)size);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilLoadPal
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilLoadPal(JNIEnv *env, jclass clazz, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilLoadPal((const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilOriginFunc
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilOriginFunc(JNIEnv *env, jclass clazz, jint mode) {
    return ilOriginFunc((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilOverlayImage
 * Signature: (IIII)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilOverlayImage(JNIEnv *env, jclass clazz, jint source, jint xCoord, jint yCoord, jint zCoord) {
    return ilOverlayImage((ILuint)source, (ILint)xCoord, (ILint)yCoord, (ILint)zCoord);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilPopAttrib
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilPopAttrib(JNIEnv *env, jclass clazz) {
    ilPopAttrib();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilPushAttrib
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilPushAttrib(JNIEnv *env, jclass clazz, jint bits) {
    ilPushAttrib((ILuint)bits);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilRemoveLoad
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilRemoveLoad(JNIEnv *env, jclass clazz, jstring ext) {
    char *strExt = GetStringNativeChars(env, ext);
    jboolean result = ilRemoveLoad((const ILstring)strExt);
    free(strExt);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilRemoveSave
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilRemoveSave(JNIEnv *env, jclass clazz, jstring ext) {
    char *strExt = GetStringNativeChars(env, ext);
    jboolean result = ilRemoveSave((const ILstring)strExt);
    free(strExt);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilResetMemory
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilResetMemory(JNIEnv *env, jclass clazz) {
    ilResetMemory();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilResetRead
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilResetRead(JNIEnv *env, jclass clazz) {
    ilResetRead();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilResetWrite
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilResetWrite(JNIEnv *env, jclass clazz) {
    ilResetWrite();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSave
 * Signature: (ILjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilSave(JNIEnv *env, jclass clazz, jint type, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilSave((ILenum)type, (const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSaveImage
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilSaveImage(JNIEnv *env, jclass clazz, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilSaveImage((const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilSaveL
 * Signature: (ILjava/nio/ByteBuffer;II)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_devil_IL_nilSaveL(JNIEnv *env, jclass clazz, jint type, jobject lump_buffer, jint lump_offset, jint size) {
    ILbyte *lump = (ILbyte *)(*env)->GetDirectBufferAddress(env, lump_buffer);

    return ilSaveL((ILenum)type, (ILvoid *)(lump + lump_offset), (ILuint)size);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSavePal
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilSavePal(JNIEnv *env, jclass clazz, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilSavePal((const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilSetData
 * Signature: (Ljava/nio/ByteBuffer;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_nilSetData(JNIEnv *env, jclass clazz, jobject data_buffer, jint data_offset) {
    ILbyte *data = (ILbyte *)(*env)->GetDirectBufferAddress(env, data_buffer);

    return ilSetData((ILvoid *)(data + data_offset));
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSetDuration
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilSetDuration(JNIEnv *env, jclass clazz, jint duration) {
    return ilSetDuration((ILuint)duration);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSetInteger
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilSetInteger(JNIEnv *env, jclass clazz, jint mode, jint param) {
    ilSetInteger((ILenum)mode, (ILint)param);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilSetPixels
 * Signature: (IIIIIIIILjava/nio/ByteBuffer;I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_nilSetPixels(JNIEnv *env, jclass clazz, jint xOff, jint yOff, jint zOff, jint width, jint height, jint depth, jint format, jint type, jobject data_buffer, jint data_offset) {
    ILbyte *data = (ILbyte *)(*env)->GetDirectBufferAddress(env, data_buffer);

    ilSetPixels((ILint)xOff, (ILint)yOff, (ILint)zOff, (ILuint)width, (ILuint)height, (ILuint)depth, (ILenum)format, (ILenum)type, (ILvoid *)(data + data_offset));
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSetString
 * Signature: (ILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilSetString(JNIEnv *env, jclass clazz, jint mode, jstring string) {
    char *strString = GetStringNativeChars(env, string);

    ilSetString(mode, (const ILstring)strString);
    free(strString);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilShutDown
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilShutDown(JNIEnv *env, jclass clazz) {
    ilShutDown();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilTexImage
 * Signature: (IIIBIILjava/nio/ByteBuffer;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_nilTexImage(JNIEnv *env, jclass clazz, jint width, jint height, jint depth , jbyte bpp, jint format, jint type, jobject data_buffer, jint data_offset) {
    ILbyte *data = (ILbyte *)(*env)->GetDirectBufferAddress(env, data_buffer);

    return ilTexImage((ILuint)width, (ILuint)height, (ILuint)depth , (ILubyte)bpp, (ILenum)format, (ILenum)type, (ILvoid *)(data + data_offset));
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilTypeFunc
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilTypeFunc(JNIEnv *env, jclass clazz, jint mode) {
    return ilTypeFunc((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilLoadData
 * Signature: (Ljava/lang/String;IIIB)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilLoadData(JNIEnv *env, jclass clazz, jstring fileName, jint width, jint height, jint depth, jbyte bpp) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilLoadData((const ILstring)strFileName, (ILuint)width, (ILuint)height, (ILuint)depth, (ILubyte)bpp);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nilLoadDataL
 * Signature: (Ljava/nio/ByteBuffer;IIIIIB)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_nilLoadDataL(JNIEnv *env, jclass clazz, jobject lump_buffer, jint lump_offset, jint size, jint width, jint height, jint depth, jbyte bpp) {
    ILbyte *lump = (ILbyte *)(*env)->GetDirectBufferAddress(env, lump_buffer);

    return ilLoadDataL((ILvoid *)(lump + lump_offset), (ILuint)size, (ILuint)width, (ILuint)height, (ILuint)depth, (ILubyte)bpp);
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilSaveData
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilSaveData(JNIEnv *env, jclass clazz, jstring fileName) {
    char *strFileName = GetStringNativeChars(env, fileName);
    jboolean result = ilSaveData((const ILstring)strFileName);
    free(strFileName);

    return result;
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    nCreate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_nCreate(JNIEnv *env, jclass clazz) {
    if (!extil_Open(env)) {
        throwException(env, "Failed to load DevIL library");
        return;
    }
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_initNativeStubs(JNIEnv *env, jclass clazz) {
    JavaMethodAndExtFunction functions[] = {
        {"ilActiveImage", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilActiveImage, "ilActiveImage", (void*)&ilActiveImage},
        {"ilActiveLayer", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilActiveLayer, "ilActiveLayer", (void*)&ilActiveLayer},
        {"ilActiveMipmap", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilActiveMipmap, "ilActiveMipmap", (void*)&ilActiveMipmap},
        {"ilApplyPal", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilApplyPal, "ilApplyPal", (void*)&ilApplyPal},
        {"ilApplyProfile", "(Ljava/lang/String;Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilApplyProfile, "ilApplyProfile", (void*)&ilApplyProfile},
        {"ilBindImage", "(I)V", (void*)&Java_org_lwjgl_devil_IL_ilBindImage, "ilBindImage", (void*)&ilBindImage},
        {"ilBlit", "(IIIIIIIIII)Z", (void*)&Java_org_lwjgl_devil_IL_ilBlit, "ilBlit", (void*)&ilBlit},
        {"ilClearColour", "(FFFF)V", (void*)&Java_org_lwjgl_devil_IL_ilClearColour, "ilClearColour", (void*)&ilClearColour},
        {"ilClearImage", "()Z", (void*)&Java_org_lwjgl_devil_IL_ilClearImage, "ilClearImage", (void*)&ilClearImage},
        {"ilCloneCurImage", "()I", (void*)&Java_org_lwjgl_devil_IL_ilCloneCurImage, "ilCloneCurImage", (void*)&ilCloneCurImage},
        {"ilCompressFunc", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilCompressFunc, "ilCompressFunc", (void*)&ilCompressFunc},
        {"ilConvertImage", "(II)Z", (void*)&Java_org_lwjgl_devil_IL_ilConvertImage, "ilConvertImage", (void*)&ilConvertImage},
        {"ilConvertPal", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilConvertPal, "ilConvertPal", (void*)&ilConvertPal},
        {"ilCopyImage", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilCopyImage, "ilCopyImage", (void*)&ilCopyImage},
        {"nilCopyPixels", "(IIIIIIIILjava/nio/ByteBuffer;I)I", (void*)&Java_org_lwjgl_devil_IL_nilCopyPixels, "ilCopyPixels", (void*)&ilCopyPixels},
        {"ilCreateSubImage", "(II)I", (void*)&Java_org_lwjgl_devil_IL_ilCreateSubImage, "ilCreateSubImage", (void*)&ilCreateSubImage},
        {"ilDefaultImage", "()Z", (void*)&Java_org_lwjgl_devil_IL_ilDefaultImage, "ilDefaultImage", (void*)&ilDefaultImage},
        {"nilDeleteImages", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_devil_IL_nilDeleteImages, "ilDeleteImages", (void*)&ilDeleteImages},
        {"ilDisable", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilDisable, "ilDisable", (void*)&ilDisable},
        {"ilEnable", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilEnable, "ilEnable", (void*)&ilEnable},
        {"ilFormatFunc", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilFormatFunc, "ilFormatFunc", (void*)&ilFormatFunc},
        {"nilGenImages", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_devil_IL_nilGenImages, "ilGenImages", (void*)&ilGenImages},
        {"ilGetAlpha", "(I)[B", (void*)&Java_org_lwjgl_devil_IL_ilGetAlpha, "ilGetAlpha", (void*)&ilGetAlpha},
        {"ilModAlpha", "(I)V", (void*)&Java_org_lwjgl_devil_IL_ilModAlpha, "ilModAlpha", (void*)&ilModAlpha},
        {"ilSetAlpha", "(I)V", (void*)&Java_org_lwjgl_devil_IL_ilSetAlpha, "ilSetAlpha", (void*)&ilSetAlpha},
        {"ilGetBoolean", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilGetBoolean, "ilGetBoolean", (void*)&ilGetBoolean},
        {"ilGetData", "()[B", (void*)&Java_org_lwjgl_devil_IL_ilGetData, "ilGetData", (void*)&ilGetData},
        {"ilGetError", "()I", (void*)&Java_org_lwjgl_devil_IL_ilGetError, "ilGetError", (void*)&ilGetError},
        {"ilGetInteger", "(I)I", (void*)&Java_org_lwjgl_devil_IL_ilGetInteger, "ilGetInteger", (void*)&ilGetInteger},
        {"ilGetLumpPos", "()I", (void*)&Java_org_lwjgl_devil_IL_ilGetLumpPos, "ilGetLumpPos", (void*)&ilGetLumpPos},
        {"ilGetPalette", "()[B", (void*)&Java_org_lwjgl_devil_IL_ilGetPalette, "ilGetPalette", (void*)&ilGetPalette},
        {"ilGetString", "(I)Ljava/lang/String;", (void*)&Java_org_lwjgl_devil_IL_ilGetString, "ilGetString", (void*)&ilGetString},
        {"ilHint", "(II)V", (void*)&Java_org_lwjgl_devil_IL_ilHint, "ilHint", (void*)&ilHint},
        {"ilInit", "()V", (void*)&Java_org_lwjgl_devil_IL_ilInit, "ilInit", (void*)&ilInit},
        {"ilIsDisabled", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilIsDisabled, "ilIsDisabled", (void*)&ilIsDisabled},
        {"ilIsEnabled", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilIsEnabled, "ilIsEnabled", (void*)&ilIsEnabled},
        {"ilIsImage", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilIsImage, "ilIsImage", (void*)&ilIsImage},
        {"ilIsValid", "(ILjava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilIsValid, "ilIsValid", (void*)&ilIsValid},
        {"nilIsValidL", "(ILjava/nio/ByteBuffer;II)Z", (void*)&Java_org_lwjgl_devil_IL_nilIsValidL, "ilIsValidL", (void*)&ilIsValidL},
        {"ilKeyColour", "(FFFF)V", (void*)&Java_org_lwjgl_devil_IL_ilKeyColour, "ilKeyColour", (void*)&ilKeyColour},
        {"ilLoad", "(ILjava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilLoad, "ilLoad", (void*)&ilLoad},
        {"ilLoadImage", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilLoadImage, "ilLoadImage", (void*)&ilLoadImage},
        {"nilLoadL", "(ILjava/nio/ByteBuffer;II)Z", (void*)&Java_org_lwjgl_devil_IL_nilLoadL, "ilLoadL", (void*)&ilLoadL},
        {"ilLoadPal", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilLoadPal, "ilLoadPal", (void*)&ilLoadPal},
        {"ilOriginFunc", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilOriginFunc, "ilOriginFunc", (void*)&ilOriginFunc},
        {"ilOverlayImage", "(IIII)Z", (void*)&Java_org_lwjgl_devil_IL_ilOverlayImage, "ilOverlayImage", (void*)&ilOverlayImage},
        {"ilPopAttrib", "()V", (void*)&Java_org_lwjgl_devil_IL_ilPopAttrib, "ilPopAttrib", (void*)&ilPopAttrib},
        {"ilPushAttrib", "(I)V", (void*)&Java_org_lwjgl_devil_IL_ilPushAttrib, "ilPushAttrib", (void*)&ilPushAttrib},
        {"ilRemoveLoad", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilRemoveLoad, "ilRemoveLoad", (void*)&ilRemoveLoad},
        {"ilRemoveSave", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilRemoveSave, "ilRemoveSave", (void*)&ilRemoveSave},
        {"ilResetMemory", "()V", (void*)&Java_org_lwjgl_devil_IL_ilResetMemory, "ilResetMemory", (void*)&ilResetMemory},
        {"ilResetRead", "()V", (void*)&Java_org_lwjgl_devil_IL_ilResetRead, "ilResetRead", (void*)&ilResetRead},
        {"ilResetWrite", "()V", (void*)&Java_org_lwjgl_devil_IL_ilResetWrite, "ilResetWrite", (void*)&ilResetWrite},
        {"ilSave", "(ILjava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilSave, "ilSave", (void*)&ilSave},
        {"ilSaveImage", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilSaveImage, "ilSaveImage", (void*)&ilSaveImage},
        {"nilSaveL", "(ILjava/nio/ByteBuffer;II)I", (void*)&Java_org_lwjgl_devil_IL_nilSaveL, "ilSaveL", (void*)&ilSaveL},
        {"ilSavePal", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilSavePal, "ilSavePal", (void*)&ilSavePal},
        {"nilSetData", "(Ljava/nio/ByteBuffer;I)Z", (void*)&Java_org_lwjgl_devil_IL_nilSetData, "ilSetData", (void*)&ilSetData},
        {"ilSetDuration", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilSetDuration, "ilSetDuration", (void*)&ilSetDuration},
        {"ilSetInteger", "(II)V", (void*)&Java_org_lwjgl_devil_IL_ilSetInteger, "ilSetInteger", (void*)&ilSetInteger},
        {"nilSetPixels", "(IIIIIIIILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_devil_IL_nilSetPixels, "ilSetPixels", (void*)&ilSetPixels},
        {"ilSetString", "(ILjava/lang/String;)V", (void*)&Java_org_lwjgl_devil_IL_ilSetString, "ilSetString", (void*)&ilSetString},
        {"ilShutDown", "()V", (void*)&Java_org_lwjgl_devil_IL_ilShutDown, "ilShutDown", (void*)&ilShutDown},
        {"nilTexImage", "(IIIBIILjava/nio/ByteBuffer;I)Z", (void*)&Java_org_lwjgl_devil_IL_nilTexImage, "ilTexImage", (void*)&ilTexImage},
        {"ilTypeFunc", "(I)Z", (void*)&Java_org_lwjgl_devil_IL_ilTypeFunc, "ilTypeFunc", (void*)&ilTypeFunc},
        {"ilLoadData", "(Ljava/lang/String;IIIB)Z", (void*)&Java_org_lwjgl_devil_IL_ilLoadData, "ilLoadData", (void*)&ilLoadData},
        {"ilLoadDataL", "(Ljava/nio/ByteBuffer;IIIIIB)Z", (void*)&Java_org_lwjgl_devil_IL_nilLoadDataL, "ilLoadDataL", (void*)&ilLoadDataL},
        {"ilSaveData", "(Ljava/lang/String;)Z", (void*)&Java_org_lwjgl_devil_IL_ilSaveData, "ilSaveData", (void*)&ilSaveData}
    };
    int num_functions = NUMFUNCTIONS(functions);
    extil_InitializeFunctions(env, clazz, num_functions, functions);
    printf("\r\ninitNativeStubs");
}

#ifdef __cplusplus
}
#endif
