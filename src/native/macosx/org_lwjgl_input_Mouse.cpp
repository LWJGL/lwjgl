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
 * OSX mouse handling.
 *
 * @author gpierce <me@gregorypierce.com>
 * @version $Revision$
 */

#include "org_lwjgl_input_Mouse.h"
#include <ApplicationServices/ApplicationServices.h>

#define NUM_BUTTONS 2


static jfieldID fid_has_wheel = NULL;
static jfieldID fid_button_count = NULL;
static jfieldID fid_buttons = NULL;
static jfieldID fid_dx = NULL;
static jfieldID fid_dy = NULL;
static jfieldID fid_dwheel = NULL;

static unsigned char buttons[NUM_BUTTONS];

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs
(JNIEnv * env, jclass clazz)
{
    if (fid_has_wheel == NULL)
        fid_has_wheel = env->GetStaticFieldID(clazz, "hasWheel", "Z");
    if (fid_button_count == NULL)
        fid_button_count = env->GetStaticFieldID(clazz, "buttonCount", "I");
    if (fid_buttons == NULL)
        fid_buttons = env->GetStaticFieldID(clazz, "buttons", "[Z");
    if (fid_dx == NULL)
        fid_dx = env->GetStaticFieldID(clazz, "dx", "I");
    if (fid_dy == NULL)
        fid_dy = env->GetStaticFieldID(clazz, "dy", "I");
    if (fid_dwheel == NULL)
        fid_dwheel = env->GetStaticFieldID(clazz, "dwheel", "I");
}


/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nCreate
(JNIEnv * env, jclass clazz)
{
    env->SetStaticIntField(clazz, fid_button_count, NUM_BUTTONS);
    env->SetStaticBooleanField(clazz, fid_has_wheel, JNI_TRUE);
    
    CGDisplayHideCursor( kCGDirectMainDisplay );
    CGDisplayMoveCursorToPoint( kCGDirectMainDisplay, CGPointZero );
    CGAssociateMouseAndMouseCursorPosition( FALSE );
    
    return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy
(JNIEnv * env, jclass clazz)
{
    CGAssociateMouseAndMouseCursorPosition( TRUE );
    CGDisplayShowCursor( kCGDirectMainDisplay );
}


/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll
(JNIEnv * env, jclass clazz)
{

    CGMouseDelta dx, dy;
    CGGetLastMouseDelta( &dx, &dy );
    
    env->SetStaticIntField(clazz, fid_dx, (jint)dx);
    env->SetStaticIntField(clazz, fid_dy, (jint)dy);
    env->SetStaticIntField(clazz, fid_dwheel, (jint)0);

    jbooleanArray buttons_array = (jbooleanArray)env->GetStaticObjectField(clazz, fid_buttons);
    env->SetBooleanArrayRegion(buttons_array, 0, NUM_BUTTONS, buttons);
}
