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
