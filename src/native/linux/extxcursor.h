/* 
 * Copyright (c) 2002-2004 LWJGL Project
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
 * * Neither the name of 'LWJGL' nor the names of 
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
 
#include <X11/Xlib.h>

#define XcursorTrue     1
#define XcursorFalse    0

typedef int             XcursorBool;

typedef int             XcursorBool;
typedef unsigned int    XcursorUInt;

typedef XcursorUInt     XcursorDim;
typedef XcursorUInt     XcursorPixel;

typedef struct _XcursorImage {
    XcursorUInt     version;    /* version of the image data */
    XcursorDim      size;       /* nominal size for matching */
    XcursorDim      width;      /* actual width */
    XcursorDim      height;     /* actual height */
    XcursorDim      xhot;       /* hot spot x (must be inside image) */
    XcursorDim      yhot;       /* hot spot y (must be inside image) */
    XcursorUInt     delay;      /* animation delay to next frame (ms) */
    XcursorPixel    *pixels;    /* pointer to pixels */
} XcursorImage;

/*
 * Other data structures exposed by the library API
 */
typedef struct _XcursorImages {
    int             nimage;     /* number of images */
    XcursorImage    **images;   /* array of XcursorImage pointers */
} XcursorImages;

typedef XcursorBool (* XcursorSupportsARGBPROC ) (Display *dpy);
typedef XcursorBool (* XcursorSupportsAnimPROC ) (Display *dpy);
typedef XcursorImage * (* XcursorImageCreatePROC) (int width, int height);
typedef void (* XcursorImageDestroyPROC) (XcursorImage *image);
typedef XcursorImages * (* XcursorImagesCreatePROC) (int size);
typedef void (* XcursorImagesDestroyPROC) (XcursorImages *images);
typedef Cursor (* XcursorImagesLoadCursorPROC) (Display *dpy, const XcursorImages *images);

extern XcursorSupportsARGBPROC XcursorSupportsARGB;
extern XcursorSupportsAnimPROC XcursorSupportsAnim;
extern XcursorImageCreatePROC XcursorImageCreate;
extern XcursorImageDestroyPROC XcursorImageDestroy;
extern XcursorImagesCreatePROC XcursorImagesCreate;
extern XcursorImagesDestroyPROC XcursorImagesDestroy;
extern XcursorImagesLoadCursorPROC XcursorImagesLoadCursor;

bool loadXcursor(void);
bool isXcursorLoaded(void);
void closeXcursor(void);
