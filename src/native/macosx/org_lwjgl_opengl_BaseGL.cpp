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
 * Base OSX functionality for GL.
 *
 * @author Gregory Pierce <me@gregorypierce.com>
 * @version $Revision$
 */

#include "org_lwjgl_opengl_BaseGL.h"
#include <ApplicationServices/ApplicationServices.h>
#include <OpenGL/OpenGL.h>


static CGLContextObj			contextObj;
static CGDirectDisplayID		displayID = kCGDirectMainDisplay;

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nCreate
 * Signature: (Ljava/lang/String;IIIIIIIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nCreate
(JNIEnv * env, jobject obj, jstring title, jint x, jint y, jint width, jint height, jint bpp, jint alpha, jint depth, jint stencil, jboolean fullscreen)
{
    if ( CGDisplayCapture( displayID ) != kCGErrorSuccess )
    {
        return;
    }

    CGLPixelFormatObj pixelFormatObj;
    long numPixelFormats;

    CFDictionaryRef displayMode;
    displayMode = CGDisplayBestModeForParametersAndRefreshRate( displayID,
                                                                bpp,
                                                                width, height,
                                                                60,
                                                                NULL );

    CGDisplaySwitchToMode( displayID, displayMode );

    CGLPixelFormatAttribute attribs[2];
    attribs[0] = kCGLPFAFullScreen;
    attribs[1] = NULL;

    CGLChoosePixelFormat( attribs, &pixelFormatObj, &numPixelFormats );
    if ( pixelFormatObj != NULL )
    {
        CGLCreateContext( pixelFormatObj, NULL, &contextObj );
        CGLDestroyPixelFormat( pixelFormatObj );
    }

    CGLSetCurrentContext( contextObj );
    CGLSetFullScreen( contextObj );
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nDestroyGL
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nDestroyGL
(JNIEnv * env, jobject obj)
{
    if ( contextObj != NULL )
    {
        CGLSetCurrentContext( NULL );
        CGLClearDrawable( contextObj );
        CGLDestroyContext( contextObj );

        contextObj = NULL;
    }


    CGReleaseAllDisplays();        
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_swapBuffers
(JNIEnv *, jobject)
{
}



