/*
 *  RenderingContext.h
 *  lwjgl
 *
 *  Created by Gregory Pierce on Wed Sep 04 2002.
 *  Copyright (c) 2002 __MyCompanyName__. All rights reserved.
 *
 */

#pragma once

#include <Carbon/Carbon.h>
#include <AGL/agl.h>
#include <DrawSprocket/DrawSprocket.h>

class RenderingContext
{
    AGLContext 				aglContext;
    Rect				rect;
    WindowPtr				windowPtr;

public:
    RenderingContext();
    ~RenderingContext();
    
    void RenderingContext::cleanupAGL(void);
    void RenderingContext::destroy(void);
    bool RenderingContext::setupAGL( AGLDrawable pAGLDrawable );
};