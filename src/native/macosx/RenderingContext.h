/*
 *  RenderingContext.h
 *  lwjglOSX
 *
 *  Created by Gregory Pierce on Sat Dec 28 2002.
 *  Copyright (c) 2002 __MyCompanyName__. All rights reserved.
 *
 */

#pragma once
#include <Carbon/Carbon.h>
#include <AGL/agl.h>
#include <OpenGL/gl.h>

class RenderingContext
{
public:
    AGLContext					aglContext;
    WindowPtr					windowPtr;
    Rect					rect;      
    

    RenderingContext();
    ~RenderingContext();
};

