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
    InitCursor();

    SetRect( &rect, 0, 0, width, height );
    windowPtr = NewCWindow( NULL, &rect, "LWJGL", true, kWindowShadowDialogProc, (WindowPtr) -1L, true, 0L );

    SetPortWindowPort( windowPtr );

    if ( windowPtr == NULL )
    {
        printf("Failed to create a window\n");
        return false;
    }

    ShowWindow( windowPtr );

    return true;
}

void RenderingContext::destroyDisplay()
{
    // cleanup the AGL context
    //
    aglSetCurrentContext(NULL);
    aglSetDrawable(aglContext, NULL);
    aglDestroyContext(aglContext);

    // cleanup the window
    //
    DisposeWindow( windowPtr );
}

bool RenderingContext::createGL( int colorBits, int alphaBits, int depthBits, int stencilBits )
{
    AGLPixelFormat 				fmt;
    GLboolean      				ok;
    GLint         			 	attrib[] = { AGL_RGBA, AGL_NONE };


    if ( extgl_Open() != 0 )
    {
        printf("extgl_Open failed");
        return false;
    }

    /* Choose an rgb pixel format */
    fmt = aglChoosePixelFormat(NULL, 0, attrib);
    if(fmt == NULL)
    {
        return false;
    }

    /* Create an AGL context */
    aglContext = aglCreateContext(fmt, NULL);
    if( aglContext == NULL)
    {
        return false;
    }

    /* Attach the window to the context */
    ok = aglSetDrawable(aglContext, GetWindowPort(windowPtr) );
    if(!ok)
    {
        return false;
    }

    /* Make the context the current context */
    ok = aglSetCurrentContext(aglContext);
    if(!ok)
    {
        return false;
    }

    if ( extgl_Initialize() != 0 )
    {
        printf("Failed to initialize GL [extgl_Initialize()]\n");
        return false;
    }

    /* Pixel format is no longer needed */
    aglDestroyPixelFormat(fmt);

#ifdef _DEBUG
    char * p = (char * ) glGetString( GL_EXTENSIONS );
    if ( NULL == p )
    {
        printf("NO extensions available");
    }
    else
    {
        printf("Available extensions:\n%s\n", p);
    }
#endif /* DEBUG */

    return true;
    
}

void RenderingContext::destroyGL()
{
    // clear out the current rendering context
    //
    aglSetCurrentContext( NULL );

    // destroy the context
    //
    aglDestroyContext( aglContext );

    // close the gl extension context
    //
    extgl_Close();
}

void RenderingContext::swap()
{
    // swap the rendering buffer
    //
    aglSwapBuffers( aglContext );    
}

void RenderingContext::makeContextCurrent()
{
    // make the current context the one we have stored
    //
    aglSetCurrentContext( aglContext );
}

void RenderingContext::releaseContext()
{
    // release the context
    //
    aglSetCurrentContext( NULL );
}

RenderingContext::~RenderingContext()
{
}