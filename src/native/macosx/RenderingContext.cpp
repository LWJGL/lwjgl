/*
 *  RenderingContext.cpp
 *  lwjgl
 *
 *  Created by Gregory Pierce on Wed Sep 04 2002.
 *  Copyright (c) 2002 __MyCompanyName__. All rights reserved.
 *
 */


#include "RenderingContext.h"

RenderingContext::RenderingContext()
{
}

RenderingContext::~RenderingContext()
{
}

/*
 ** OpenGL Setup
 */
//GetWindowPort(win)
bool RenderingContext::setupAGL( AGLDrawable pAGLDrawable )
{
    AGLPixelFormat 				fmt;
    GLboolean      				ok;
    GLint         			 	attrib[] = { AGL_RGBA, AGL_NONE };


    /* Choose an rgb pixel format */
    fmt = aglChoosePixelFormat(NULL, 0, attrib);
    if(fmt == NULL)
    {
        return false;
    }

    /* Create an AGL context */
    aglContext = aglCreateContext(fmt, NULL);
    if(aglContext == NULL)
    {
        return false;
    }

    /* Attach the window to the context */
    ok = aglSetDrawable(aglContext, pAGLDrawable );
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

    /* Pixel format is no longer needed */
    aglDestroyPixelFormat(fmt);

    return true;
}

void RenderingContext::destroy(void)
{
    cleanupAGL();

    // cleanup the window
    //
    DisposeWindow( windowPtr );
}

/*
 ** OpenGL Cleanup
 */
void RenderingContext::cleanupAGL()
{
    aglSetCurrentContext(NULL);
    aglSetDrawable(aglContext, NULL);
    aglDestroyContext(aglContext);
}