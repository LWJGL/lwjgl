/* 

 * Copyright (c) 2002 Light Weight Java Game Library Project

 * All rights reserved.

 * 

 * Redistribution and use in source and binary forms, with or without

 * modification, are permitted provided that the following conditions are 

 * met:

 * 

 * * Redistributions of source code must retain the above copyright 

 *   notice, this list of conditions and the following disclaimer.

 *

 * * Redistributions in binary form must reproduce the above copyright

 *   notice, this list of conditions and the following disclaimer in the

 *   documentation and/or other materials provided with the distribution.

 *

 * * Neither the name of 'Light Weight Java Game Library' nor the names of 

 *   its contributors may be used to endorse or promote products derived 

 *   from this software without specific prior written permission.

 * 

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS

 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED

 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR

 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 

 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 

 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 

 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 

 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF

 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 

 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS

 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */

 

/**
 * $Id$
 *
 * Win32 digital (Atari) joystick handling. These days it's a gamepad.
 *
 * @author gregorypierce <me@gregorypierce.com>
 * @version $Revision$
 */





#include "org_lwjgl_input_GamePad.h"



/*

 * Class:     org_lwjgl_input_GamePad

 * Method:    initIDs

 * Signature: ()V

 */

JNIEXPORT void JNICALL Java_org_lwjgl_input_GamePad_initIDs

  (JNIEnv * env, jclass clazz) {}



/*

 * Class:     org_lwjgl_input_GamePad

 * Method:    nCreate

 * Signature: ()Z

 */

JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_GamePad_nCreate

  (JNIEnv * env, jclass clazz)

{

	printf("GamePad not implemented yet!\n");

	return JNI_FALSE;

}



/*

 * Class:     org_lwjgl_input_GamePad

 * Method:    nDestroy

 * Signature: ()V

 */

JNIEXPORT void JNICALL Java_org_lwjgl_input_GamePad_nDestroy

  (JNIEnv * env, jclass clazz)

{

}



/*

 * Class:     org_lwjgl_input_GamePad

 * Method:    nPoll

 * Signature: (I)V

 */

JNIEXPORT void JNICALL Java_org_lwjgl_input_GamePad_nPoll

  (JNIEnv * env, jclass clazz, jint buf)

{

}



/*

 * Class:     org_lwjgl_input_GamePad

 * Method:    nRead

 * Signature: (I)I

 */

JNIEXPORT jint JNICALL Java_org_lwjgl_input_GamePad_nRead

  (JNIEnv * env, jclass clazz, jint buf)

{

	return 0;

}



/*

 * Class:     org_lwjgl_input_GamePad

 * Method:    nEnableBuffer

 * Signature: ()I

 */

JNIEXPORT jint JNICALL Java_org_lwjgl_input_GamePad_nEnableBuffer

  (JNIEnv * env, jclass clazz)

{

	return 0;

}



/*

 * Class:     org_lwjgl_input_GamePad

 * Method:    nGetNumButtons

 * Signature: ()I

 */

JNIEXPORT jint JNICALL Java_org_lwjgl_input_GamePad_nGetNumButtons

  (JNIEnv * env, jclass clazz)

{

	return 0;

}



