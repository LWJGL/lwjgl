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
 * OSX rendering context management.
 *
 * @author Gregory Pierce <me@gregorypierce.com>
 * @version $Revision$
 */

#include "RenderingContext.h"

RenderingContext::RenderingContext()
{
}

bool RenderingContext::createDisplay( int width, int height, int bpp, int freq  )
{
    printf("Creating display");

    CGDisplayCapture( kCGDirectMainDisplay ) ;
    CGDisplaySwitchToMode( kCGDirectMainDisplay,
                           CGDisplayBestModeForParameters( kCGDirectMainDisplay,
                                                           bpp, width, height, freq ) ) ;
    
    CGOpenGLDisplayMask displayMask = CGDisplayIDToOpenGLDisplayMask( kCGDirectMainDisplay ) ;
    CGLPixelFormatAttribute attribs[] =
    {
        kCGLPFAFullScreen,
        kCGLPFADisplayMask,
        displayMask,
        NULL
    } ;

    CGLPixelFormatObj pixelFormatObj ;
    long numPixelFormats ;

    CGLChoosePixelFormat( attribs, &pixelFormatObj, &numPixelFormats );

    long swapInterval ;

    CGLCreateContext( pixelFormatObj, NULL, &contextObj ) ;
    CGLDestroyPixelFormat( pixelFormatObj ) ;

    swapInterval = 1 ;
    CGLSetParameter( contextObj, kCGLCPSwapInterval, &swapInterval ) ;

    CGLSetCurrentContext( contextObj ) ;
    CGLSetFullScreen( contextObj ) ;
    

    return true;
}

void RenderingContext::destroyDisplay()
{
    CGLClearDrawable( contextObj ) ;
    CGLDestroyContext( contextObj ) ;

    CGReleaseAllDisplays();

}

void RenderingContext::swap()
{
    // swap the rendering buffer
    //
    CGLFlushDrawable( contextObj )    
}

void RenderingContext::makeContextCurrent()
{
    // make the current context the one we have stored
    //
    CGLSetCurrentContext( contextObj ) ;
}

void RenderingContext::releaseContext()
{
    // release the context
    //
    CGLSetCurrentContext( NULL ) ;
}

RenderingContext::~RenderingContext()
{
}