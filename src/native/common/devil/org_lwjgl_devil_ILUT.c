#include "extilut.h"
#include "org_lwjgl_devil_ILNative.h"

typedef ILboolean		(ILAPIENTRY *ilutRendererPROC) (ILenum Renderer);
typedef ILboolean		(ILAPIENTRY *ilutDisablePROC) (ILenum Mode);
typedef ILboolean		(ILAPIENTRY *ilutEnablePROC) (ILenum Mode);
typedef ILboolean		(ILAPIENTRY *ilutGetBooleanPROC) (ILenum Mode);
typedef ILint			(ILAPIENTRY *ilutGetIntegerPROC) (ILenum Mode);
typedef void			(ILAPIENTRY *ilutGetBooleanvPROC) (ILenum Mode, ILboolean *Param);
typedef void			(ILAPIENTRY *ilutGetIntegervPROC) (ILenum Mode, ILint *Param);
typedef const ILstring	(ILAPIENTRY *ilutGetStringPROC) (ILenum StringName);
typedef ILvoid			(ILAPIENTRY *ilutInitPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *ilutIsDisabledPROC) (ILenum Mode);
typedef ILboolean		(ILAPIENTRY *ilutIsEnabledPROC) (ILenum Mode);
typedef ILvoid			(ILAPIENTRY *ilutPopAttribPROC) (ILvoid);
typedef ILvoid			(ILAPIENTRY *ilutPushAttribPROC) (ILuint Bits);
typedef ILvoid			(ILAPIENTRY *ilutSetIntegerPROC) (ILenum Mode, ILint Param);
typedef GLuint			(ILAPIENTRY *ilutGLBindTexImagePROC) ();
typedef GLuint			(ILAPIENTRY *ilutGLBindMipmapsPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *ilutGLBuildMipmapsPROC) (ILvoid);
typedef GLuint			(ILAPIENTRY *ilutGLLoadImagePROC) (const ILstring FileName);
typedef ILboolean		(ILAPIENTRY *ilutGLScreenPROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *ilutGLScreeniePROC) (ILvoid);
typedef ILboolean		(ILAPIENTRY *ilutGLSaveImagePROC) (const ILstring FileName, GLuint TexID);
typedef ILboolean		(ILAPIENTRY *ilutGLSetTexPROC) (GLuint TexID);
typedef ILboolean		(ILAPIENTRY *ilutGLTexImagePROC) (GLuint Level);

static ilutRendererPROC ilutRenderer;
static ilutDisablePROC ilutDisable;
static ilutEnablePROC ilutEnable;
static ilutGetBooleanPROC ilutGetBoolean;
static ilutGetIntegerPROC ilutGetInteger;
static ilutGetBooleanvPROC ilutGetBooleanv;
static ilutGetIntegervPROC ilutGetIntegerv;
static ilutGetStringPROC ilutGetString;
static ilutInitPROC ilutInit;
static ilutIsDisabledPROC  ilutIsDisabled;
static ilutIsEnabledPROC ilutIsEnabled;
static ilutPopAttribPROC ilutPopAttrib;
static ilutPushAttribPROC ilutPushAttrib;
static ilutSetIntegerPROC ilutSetInteger;
static ilutGLBindTexImagePROC ilutGLBindTexImage;
static ilutGLBindMipmapsPROC ilutGLBindMipmaps;
static ilutGLBuildMipmapsPROC ilutGLBuildMipmaps;
static ilutGLLoadImagePROC ilutGLLoadImage;
static ilutGLScreenPROC ilutGLScreen;
static ilutGLScreeniePROC ilutGLScreenie;
static ilutGLSaveImagePROC ilutGLSaveImage;
static ilutGLSetTexPROC ilutGLSetTex;
static ilutGLTexImagePROC ilutGLTexImage;

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutRenderer
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutRenderer(JNIEnv *env, jclass clazz, jint renderer){
    return ilutRenderer((ILenum)renderer);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutDisable
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutDisable(JNIEnv *env, jclass clazz, jint mode){
    return ilutDisable((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutEnable
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutEnable(JNIEnv *env, jclass clazz, jint mode){
    return ilutEnable((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetBoolean
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGetBoolean(JNIEnv *env, jclass clazz, jint mode){
    return ilutGetBoolean((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetInteger
 * Signature: (I)I
 */
static jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGetInteger(JNIEnv *env, jclass clazz, jint mode){
    return ilutGetInteger((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetBooleanv
 * Signature: (ILjava/nio/CharBuffer;)V
 */
static void JNICALL Java_org_lwjgl_devil_ILUT_ilutGetBooleanv(JNIEnv *env, jclass clazz, jint mode, jobject param) {
	ILboolean* destination = (ILboolean*) safeGetBufferAddress(env, param);
	ilutGetBooleanv(mode, destination);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetIntegerv
 * Signature: (ILjava/nio/IntBuffer;)V
 */
static void JNICALL Java_org_lwjgl_devil_ILUT_ilutGetIntegerv(JNIEnv *env, jclass clazz, jint mode, jobject param) {
	ILint* destination = (ILint*) safeGetBufferAddress(env, param);
	ilutGetIntegerv(mode, destination);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGetString
 * Signature: (I)Ljava/lang/String;
 */
static jstring JNICALL Java_org_lwjgl_devil_ILUT_ilutGetString(JNIEnv *env, jclass clazz, jint stringName){
		char * name = ilutGetString((ILenum)stringName);
    return NewStringNativeWithLength(env, name, strlen(name));
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutInit
 * Signature: ()V
 */
static void JNICALL Java_org_lwjgl_devil_ILUT_ilutInit(JNIEnv *env, jclass clazz){
    ilutInit();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutIsDisabled
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutIsDisabled(JNIEnv *env, jclass clazz, jint mode){
    return ilutIsDisabled((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutIsEnabled
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutIsEnabled(JNIEnv *env, jclass clazz, jint mode){
    return ilutIsEnabled((ILenum)mode);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutPopAttrib
 * Signature: ()V
 */
static void JNICALL Java_org_lwjgl_devil_ILUT_ilutPopAttrib(JNIEnv *env, jclass clazz){
    ilutPopAttrib();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutPushAttrib
 * Signature: (I)V
 */
static void JNICALL Java_org_lwjgl_devil_ILUT_ilutPushAttrib(JNIEnv *env, jclass clazz, jint bits){
    ilutPushAttrib((ILuint)bits);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutSetInteger
 * Signature: (II)V
 */
static void JNICALL Java_org_lwjgl_devil_ILUT_ilutSetInteger(JNIEnv *env, jclass clazz, jint mode, jint param){
    ilutSetInteger((ILenum)mode, (ILint)param);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLBindTexImage
 * Signature: ()I
 */
static jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGLBindTexImage(JNIEnv *env, jclass clazz){
    return ilutGLBindTexImage();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLBindMipmaps
 * Signature: ()I
 */
static jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGLBindMipmaps(JNIEnv *env, jclass clazz){
    return ilutGLBindMipmaps();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLBuildMipmaps
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLBuildMipmaps(JNIEnv *env, jclass clazz){
    return ilutGLBuildMipmaps();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLLoadImage
 * Signature: (Ljava/lang/String;)I
 */
static jint JNICALL Java_org_lwjgl_devil_ILUT_ilutGLLoadImage(JNIEnv *env, jclass clazz, jstring fileName){
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
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLScreen(JNIEnv *env, jclass clazz){
    return ilutGLScreen();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLScreenie
 * Signature: ()Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLScreenie(JNIEnv *env, jclass clazz){
    return ilutGLScreenie();
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLSaveImage
 * Signature: (Ljava/lang/String;I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLSaveImage(JNIEnv *env, jclass clazz, jstring fileName, jint texID){
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
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLSetTex(JNIEnv *env, jclass clazz, jint texID){
    return ilutGLSetTex(texID);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    ilutGLTexImage
 * Signature: (I)Z
 */
static jboolean JNICALL Java_org_lwjgl_devil_ILUT_ilutGLTexImage(JNIEnv *env, jclass clazz, jint level){
    return ilutGLTexImage(level);
}

/*
 * Class:     org_lwjgl_devil_ILUT
 * Method:    nCreate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_nCreateILUT(JNIEnv *env, jclass clazz, jobjectArray ilutPaths){
    if (!extilut_Open(env, ilutPaths)) {
        throwException(env, "Failed to load ILUT library");
        return;
    }
}

JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_nDestroyILUT(JNIEnv *env, jclass clazz) {
	extilut_Close();
}

JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_resetNativeStubsILUT(JNIEnv *env, jclass clazz, jclass ilut_class) {
	(*env)->UnregisterNatives(env, ilut_class);
}

JNIEXPORT void JNICALL Java_org_lwjgl_devil_ILNative_initNativeStubsILUT(JNIEnv *env, jclass clazz, jclass ilut_class){
JavaMethodAndExtFunction functions[] = {
        {"ilutRenderer", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutRenderer, "ilutRenderer", (void*)&ilutRenderer},
		{"ilutDisable", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutDisable, "ilutDisable", (void*)&ilutDisable},
		{"ilutEnable", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutEnable, "ilutEnable", (void*)&ilutEnable},
		{"ilutGetBoolean", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutGetBoolean, "ilutGetBoolean", (void*)&ilutGetBoolean},
		{"ilutGetInteger", "(I)I", (void*)&Java_org_lwjgl_devil_ILUT_ilutGetInteger, "ilutGetInteger", (void*)&ilutGetInteger},
		{"ilutGetBooleanv", "(ILjava/nio/ByteBuffer;)V", (void*)&Java_org_lwjgl_devil_ILUT_ilutGetBooleanv, "ilutGetBooleanv", (void*)&ilutGetBooleanv},
		{"ilutGetIntegerv", "(ILjava/nio/IntBuffer;)V", (void*)&Java_org_lwjgl_devil_ILUT_ilutGetIntegerv, "ilutGetIntegerv", (void*)&ilutGetIntegerv},
		{"ilutGetString", "(I)Ljava/lang/String;", (void*)&Java_org_lwjgl_devil_ILUT_ilutGetString, "ilutGetString", (void*)&ilutGetString},
		{"ilutInit", "()V", (void*)&Java_org_lwjgl_devil_ILUT_ilutInit, "ilutInit", (void*)&ilutInit},
		{"ilutIsDisabled", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutIsDisabled, "ilutIsDisabled", (void*)&ilutIsDisabled},
		{"ilutIsEnabled", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutIsDisabled, "ilutIsDisabled", (void*)&ilutIsDisabled},
		{"ilutPopAttrib", "()V", (void*)&Java_org_lwjgl_devil_ILUT_ilutPopAttrib, "ilutPopAttrib", (void*)&ilutPopAttrib},
		{"ilutPushAttrib", "(I)V", (void*)&Java_org_lwjgl_devil_ILUT_ilutPushAttrib, "ilutPushAttrib", (void*)&ilutPushAttrib},
		{"ilutSetInteger", "(II)V", (void*)&Java_org_lwjgl_devil_ILUT_ilutSetInteger, "ilutSetInteger", (void*)&ilutSetInteger},

		{"ilutGLBindTexImage", "()I", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLBindTexImage, "ilutGLBindTexImage", (void*)&ilutGLBindTexImage},
		{"ilutGLBindMipmaps", "()I", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLBindMipmaps, "ilutGLBindMipmaps", (void*)&ilutGLBindMipmaps},
		{"ilutGLBuildMipmaps", "()Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLBuildMipmaps, "ilutGLBuildMipmaps", (void*)&ilutGLBuildMipmaps},
		{"ilutGLLoadImage", "(Ljava/lang/String;)I", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLLoadImage, "ilutGLLoadImage", (void*)&ilutGLLoadImage},
		{"ilutGLScreen", "()Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLScreen, "ilutGLScreen", (void*)&ilutGLScreen},
		{"ilutGLScreenie", "()Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLScreenie, "ilutGLScreenie", (void*)&ilutGLScreenie},
		{"ilutGLSaveImage", "(Ljava/lang/String;I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLSaveImage, "ilutGLSaveImage", (void*)&ilutGLSaveImage},
		{"ilutGLSetTex", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLSetTex, "ilutGLSetTex", (void*)&ilutGLSetTex},
		{"ilutGLTexImage", "(I)Z", (void*)&Java_org_lwjgl_devil_ILUT_ilutGLTexImage, "ilutGLTexImage", (void*)&ilutGLTexImage},
    };
    int num_functions = NUMFUNCTIONS(functions);
    extilut_InitializeClass(env, ilut_class, num_functions, functions);
}
