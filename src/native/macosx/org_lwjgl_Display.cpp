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

#include "org_lwjgl_Display.h"
#include <JavaVM/jni.h>
#include <ApplicationServices/ApplicationServices.h>
#include <OpenGL/OpenGL.h>

bool modeSet = false;

static long _getDictLong (CFDictionaryRef refDict, CFStringRef key)
{
    long int_value;

    CFNumberRef number_value = (CFNumberRef) CFDictionaryGetValue(refDict, key);

    if (!number_value)
    {
        // if can't get a number for the dictionary
        return -1;  // fail
    }
    
    if (!CFNumberGetValue(number_value, kCFNumberLongType, &int_value))
    {
        // or if cant convert it
        return -1; // fail
    }
    
    return int_value; // otherwise return the long value
}

static double _getDictDouble (CFDictionaryRef refDict, CFStringRef key)
{
    double double_value;

    CFNumberRef number_value = (CFNumberRef) CFDictionaryGetValue(refDict, key);

    if (!number_value)
    {
        // if can't get a number for the dictionary
        return -1;  // fail
    }
    
    if (!CFNumberGetValue(number_value, kCFNumberDoubleType, &double_value)) // or if cant convert it
    {
        return -1; // fail
    }
    
    return double_value; // otherwise return the long value
}

jobjectArray GetAvailableDisplayModesOSX(JNIEnv * env)
{
    CFIndex		i, count, availableModes;
    CFArrayRef		displayModes = NULL;
    int			n = 0;


    displayModes = CGDisplayAvailableModes( kCGDirectMainDisplay );
    count = CFArrayGetCount( displayModes );

    printf("Found %d displaymodes\n", count );

    // get a count of the number of display modes on this machine with a bpp greater than 8
    //
    for ( i = 0; i < count; i++ )
    {
        CFDictionaryRef modeDict = CFArrayGetValueAtIndex( displayModes, i );
        long bpp = _getDictLong( modeDict, kCGDisplayBitsPerPixel );

        if ( bpp > 8 )
        {
            availableModes ++;
        }
    }
    
    // now that we have the count create the classes, and add 'em all - we'll remove dups in Java
    // Allocate an array of DisplayModes big enough
    jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");

    jobjectArray ret = env->NewObjectArray( availableModes, displayModeClass, NULL);
    jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");
    
    for ( i = 0; i < count; i++ )
    {
        CFDictionaryRef modeDict = CFArrayGetValueAtIndex( displayModes, i );
        long width = _getDictLong( modeDict, kCGDisplayWidth );
        long height = _getDictLong( modeDict, kCGDisplayHeight );
        long freq = (long)(_getDictDouble( modeDict, kCGDisplayRefreshRate ) + 0.5 );
        long bpp = _getDictLong( modeDict, kCGDisplayBitsPerPixel );

        if ( bpp > 8 )
        {
            jobject displayMode;
            displayMode = env->NewObject(displayModeClass, displayModeConstructor,
                                         width, height,
                                         bpp, freq);

            env->SetObjectArrayElement(ret, n++, displayMode);
        }
    }

    return ret;
}




/*
 * Class:     org_lwjgl_Display
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_init
(JNIEnv * env, jclass clazz)
{
    //TODO Get the current display mode from the system
    //
    int width = 640;
    int height = 480;
    int bpp = 32;
    int freq = 60;
    
    jclass jclass_DisplayMode = env->FindClass("org/lwjgl/DisplayMode");
    jmethodID ctor = env->GetMethodID(jclass_DisplayMode, "<init>", "(IIII)V");
    jobject newMode = env->NewObject(jclass_DisplayMode, ctor, width, height, bpp, freq);
    jfieldID fid_initialMode = env->GetStaticFieldID(clazz, "mode", "Lorg/lwjgl/DisplayMode;");
    env->SetStaticObjectField(clazz, fid_initialMode, newMode);
    env->DeleteLocalRef(newMode);
}


/*
 * Class:     org_lwjgl_Display
 * Method:    setDisplayMode
 * Signature: (Lorg/lwjgl/DisplayMode;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_setDisplayMode
(JNIEnv * env, jclass clazz, jobject mode)
{
    jclass cls_displayMode = env->FindClass("org/lwjgl/DisplayMode");
    jfieldID fid_width = env->GetFieldID(cls_displayMode, "width", "I");
    jfieldID fid_height = env->GetFieldID(cls_displayMode, "height", "I");
    jfieldID fid_bpp = env->GetFieldID(cls_displayMode, "bpp", "I");
    jfieldID fid_freq = env->GetFieldID(cls_displayMode, "freq", "I");

    int width = env->GetIntField(mode, fid_width);
    int height = env->GetIntField(mode, fid_height);
    int bpp = env->GetIntField(mode, fid_bpp);
    int freq = env->GetIntField(mode, fid_freq);

    CFDictionaryRef displayMode;
    displayMode = CGDisplayBestModeForParametersAndRefreshRate( kCGDirectMainDisplay,
                                                                bpp,
                                                                width, height,
                                                                freq,
                                                                NULL );
    CGDisplaySwitchToMode( kCGDirectMainDisplay, displayMode ) ;
    
    // The change was successful but might not be the exact change we were expecting.
    // Now we'll construct a new DisplayMode instance and stash it back in the Display
    // class's mode instance variable.

    jmethodID ctor = env->GetMethodID(cls_displayMode, "<init>", "(IIII)V");
    jobject newMode = env->NewObject(cls_displayMode, ctor, width, height, bpp, freq);
    jfieldID fid_initialMode = env->GetStaticFieldID(clazz, "mode", "Lorg/lwjgl/DisplayMode;");
    env->SetStaticObjectField(clazz, fid_initialMode, newMode);
    env->DeleteLocalRef(newMode);

    modeSet = true;

}

/*
 * Class:     org_lwjgl_Display
 * Method:    resetDisplayMode
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_resetDisplayMode
(JNIEnv *, jclass)
{
    // the purpose of this method is to return the display mode to whatever it was before
    // the application takes over. OSX is smart enough to not require any of this
    // foolishness :)
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nGetAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
     */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_nGetAvailableDisplayModes
(JNIEnv * env, jclass clazz)
{
    return GetAvailableDisplayModesOSX( env );
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getPlatform
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_Display_getPlatform
(JNIEnv * env, jclass clazz)
{
    return org_lwjgl_Display_PLATFORM_AGL;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getGammaRamp
 * Signature: ([I[I[I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_getGammaRamp
(JNIEnv *, jclass, jintArray, jintArray, jintArray)
{
    return false;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    setGammaRamp
 * Signature: ([I[I[I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_setGammaRamp
(JNIEnv *, jclass, jintArray, jintArray, jintArray)
{
    return false;
}





