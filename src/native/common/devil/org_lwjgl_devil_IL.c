#include <IL\il.h>
#include "extgl.h"
#include "org_lwjgl_devil_IL.h"
#include "common_tools.h"

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
 * Method:    ilDeleteImages
 * Signature: (I[I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_nilDeleteImages(JNIEnv * env, jclass clazz, jint num, jobject lists_buffer, jint lists_offset) {
	ILbyte *lists = (ILbyte *)(*env)->GetDirectBufferAddress(env, lists_buffer);
	ilDeleteImages((ILsizei)num, (ILuint *)(lists + lists_offset));
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGenImages
 * Signature: (I[I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_nilGenImages(JNIEnv *env , jclass clazz, jint num, jobject lists_buffer, jint lists_offset) {
	ILbyte *lists = (ILbyte *)(*env)->GetDirectBufferAddress(env, lists_buffer);
	ilGenImages((ILsizei)num, (ILuint *)(lists + lists_offset));
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilGetData
 * Signature: ()[B
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_devil_IL_ilGetData(JNIEnv * env, jclass clazz) {
	int size;
	jobject result;

	ILubyte *data = ilGetData();

	size = ilGetInteger(IL_IMAGE_WIDTH) * ilGetInteger(IL_IMAGE_HEIGHT) * ilGetInteger(IL_IMAGE_BYTES_PER_PIXEL);
	result = safeNewBuffer(env, data, size);

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
 * Method:    ilInit
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_ilInit(JNIEnv * env, jclass clazz) {
	ilInit();
}

/*
 * Class:     org_lwjgl_devil_IL
 * Method:    ilLoadImage
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_devil_IL_ilLoadImage(JNIEnv *env, jclass clazz, jstring fileName) {
	const char *str = (*env)->GetStringUTFChars(env, fileName, 0);

	return ilLoadImage((char *)str);
}

JNIEXPORT void JNICALL Java_org_lwjgl_devil_IL_initNativeStubs(JNIEnv *env, jclass clazz) {
}
