/*
 * org_lwjgl_Math.cpp
 *
 * Win32 specific library
 *
 * (C) 2002 Shaven Puppy Ltd
 *
 * Created 10 August 2002 by foo
 *
 */

#include <windows.h>
#include "org_lwjgl_Math.h"
#include "math.h"

/*
 * Class:     org_lwjgl_Math
 * Method:    random
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_random
  (JNIEnv * env, jclass clazz)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    sin
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_sin
  (JNIEnv * env, jclass clazz, jfloat theta)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    cos
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_cos
  (JNIEnv * env, jclass clazz, jfloat theta)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    tan
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_tan
  (JNIEnv * env, jclass clazz, jfloat theta)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    asin
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_asin
  (JNIEnv * env, jclass clazz, jfloat theta)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    acos
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_acos
  (JNIEnv * env, jclass clazz, jfloat theta)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    atan
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_atan
  (JNIEnv * env, jclass clazz, jfloat theta)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    sqrt
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_org_lwjgl_Math_sqrt
  (JNIEnv * env, jclass clazz, jfloat value)
{
	return 0.0f;
}

/*
 * Class:     org_lwjgl_Math
 * Method:    matrixOp
 * Signature: (IIIIIIZIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Math_matrixOp__IIIIIIZIIZ
  (JNIEnv * env, jclass clazz, jint, jint, jint, jint, jint, jint, jboolean, jint, jint, jboolean)
{
}

/*
 * Class:     org_lwjgl_Math
 * Method:    matrixOp
 * Signature: (IIIIIZIIIIZIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Math_matrixOp__IIIIIZIIIIZIIZ
  (JNIEnv * env, jclass clazz, jint, jint, jint, jint, jint, jboolean, jint, jint, jint, jint, jboolean, jint, jint, jboolean)
{
}

