/* ----------------------------------------------------------------------------
Copyright (c) 2001-2002, Lev Povalahev
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

	* Redistributions of source code must retain the above copyright notice,
	  this list of conditions and the following disclaimer.
	* Redistributions in binary form must reproduce the above copyright notice,
	  this list of conditions and the following disclaimer in the documentation
	  and/or other materials provided with the distribution.
	* The name of the author may be used to endorse or promote products
	  derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
THE POSSIBILITY OF SUCH DAMAGE.
------------------------------------------------------------------------------*/
/*
	Lev Povalahev

	levp@gmx.net

	http://www.uni-karlsruhe.de/~uli2/

*/

#include <stdio.h>
#include <string.h>
#include "extgl.h"
#include "common_tools.h"

/* turn off the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn -8064
#pragma warn -8065
#endif /* __BORLANDC__	*/

#ifdef _X11

#include <dlfcn.h>

glXGetFBConfigsPROC glXGetFBConfigs = NULL;
glXChooseFBConfigPROC glXChooseFBConfig = NULL;
glXGetFBConfigAttribPROC glXGetFBConfigAttrib = NULL;
glXGetVisualFromFBConfigPROC glXGetVisualFromFBConfig = NULL;
glXCreateWindowPROC glXCreateWindow = NULL;
glXDestroyWindowPROC glXDestroyWindow = NULL;
glXCreatePixmapPROC glXCreatePixmap = NULL;
glXDestroyPixmapPROC glXDestroyPixmap = NULL;
glXCreatePbufferPROC glXCreatePbuffer = NULL;
glXDestroyPbufferPROC glXDestroyPbuffer = NULL;
glXQueryDrawablePROC glXQueryDrawable = NULL;
glXCreateNewContextPROC glXCreateNewContext = NULL;
glXMakeContextCurrentPROC glXMakeContextCurrent = NULL;
glXGetCurrentReadDrawablePROC glXGetCurrentReadDrawable = NULL;
glXGetCurrentDisplayPROC glXGetCurrentDisplay = NULL;
glXQueryContextPROC glXQueryContext = NULL;
glXSelectEventPROC glXSelectEvent = NULL;
glXGetSelectedEventPROC glXGetSelectedEvent = NULL;
glXGetProcAddressARBPROC glXGetProcAddressARB = NULL;
glXChooseVisualPROC glXChooseVisual = NULL;
glXCopyContextPROC glXCopyContext = NULL;
glXCreateContextPROC glXCreateContext = NULL;
glXCreateGLXPixmapPROC glXCreateGLXPixmap = NULL;
glXDestroyContextPROC glXDestroyContext = NULL;
glXDestroyGLXPixmapPROC glXDestroyGLXPixmap = NULL;
glXGetConfigPROC glXGetConfig = NULL;
glXGetCurrentContextPROC glXGetCurrentContext = NULL;
glXGetCurrentDrawablePROC glXGetCurrentDrawable = NULL;
glXIsDirectPROC glXIsDirect = NULL;
glXMakeCurrentPROC glXMakeCurrent = NULL;
glXQueryExtensionPROC glXQueryExtension = NULL;
glXQueryVersionPROC glXQueryVersion = NULL;
glXSwapBuffersPROC glXSwapBuffers = NULL;
glXUseXFontPROC glXUseXFont = NULL;
glXWaitGLPROC glXWaitGL = NULL;
glXWaitXPROC glXWaitX = NULL;
glXGetClientStringPROC glXGetClientString = NULL;
glXQueryServerStringPROC glXQueryServerString = NULL;
glXQueryExtensionsStringPROC glXQueryExtensionsString = NULL;

/* GLX_SGI_swap_control */
glXSwapIntervalSGIPROC glXSwapIntervalSGI = NULL;
#endif

#ifdef _AGL
aglChoosePixelFormatPROC aglChoosePixelFormat = NULL;
aglDestroyPixelFormatPROC aglDestroyPixelFormat = NULL;
aglNextPixelFormatPROC aglNextPixelFormat = NULL;
aglDescribePixelFormatPROC aglDescribePixelFormat = NULL;
aglDevicesOfPixelFormatPROC aglDevicesOfPixelFormat = NULL;
aglQueryRendererInfoPROC aglQueryRendererInfo = NULL;
aglDestroyRendererInfoPROC aglDestroyRendererInfo = NULL;
aglNextRendererInfoPROC aglNextRendererInfo = NULL;
aglDescribeRendererPROC aglDescribeRenderer = NULL;
aglCreateContextPROC aglCreateContext = NULL;
aglDestroyContextPROC aglDestroyContext = NULL;
aglCopyContextPROC aglCopyContext = NULL;
aglUpdateContextPROC aglUpdateContext = NULL;
aglSetCurrentContextPROC aglSetCurrentContext = NULL;
aglGetCurrentContextPROC aglGetCurrentContext = NULL;
aglSetDrawablePROC aglSetDrawable = NULL;
aglSetOffScreenPROC aglSetOffScreen = NULL;
aglSetFullScreenPROC aglSetFullScreen = NULL;
aglGetDrawablePROC aglGetDrawable = NULL;
aglSetVirtualScreenPROC aglSetVirtualScreen = NULL;
aglGetVirtualScreenPROC aglGetVirtualScreen = NULL;
aglGetVersionPROC aglGetVersion = NULL;
aglSwapBuffersPROC aglSwapBuffers = NULL;
aglEnablePROC aglEnable = NULL;
aglDisablePROC aglDisable = NULL;
aglIsEnabledPROC aglIsEnabled = NULL;
aglSetIntegerPROC aglSetInteger = NULL;
aglGetIntegerPROC aglGetInteger = NULL;
aglUseFontPROC aglUseFont = NULL;
aglGetErrorPROC aglGetError = NULL;
aglErrorStringPROC aglErrorString = NULL;
aglResetLibraryPROC aglResetLibrary = NULL;
aglSurfaceTexturePROC aglSurfaceTexture = NULL;
#endif

/* function variables */

/*glAccumPROC glAccum = NULL;
glAlphaFuncPROC glAlphaFunc = NULL;
glAreTexturesResidentPROC glAreTexturesResident = NULL;
glArrayElementPROC glArrayElement = NULL;
glBeginPROC glBegin = NULL;
glBindTexturePROC glBindTexture = NULL;
glBitmapPROC glBitmap = NULL;
glBlendFuncPROC glBlendFunc = NULL;
glCallListPROC glCallList = NULL;
glCallListsPROC glCallLists = NULL;
glClearPROC glClear = NULL;
glClearAccumPROC glClearAccum = NULL;
glClearColorPROC glClearColor = NULL;
glClearDepthPROC glClearDepth = NULL;
glClearIndexPROC glClearIndex = NULL;
glClearStencilPROC glClearStencil = NULL;
glClipPlanePROC glClipPlane = NULL;
glColor3bPROC glColor3b = NULL;
glColor3bvPROC glColor3bv = NULL;
glColor3dPROC glColor3d = NULL;
glColor3dvPROC glColor3dv = NULL;
glColor3fPROC glColor3f = NULL;
glColor3fvPROC glColor3fv = NULL;
glColor3iPROC glColor3i = NULL;
glColor3ivPROC glColor3iv = NULL;
glColor3sPROC glColor3s = NULL;
glColor3svPROC glColor3sv = NULL;
glColor3ubPROC glColor3ub = NULL;
glColor3ubvPROC glColor3ubv = NULL;
glColor3uiPROC glColor3ui = NULL;
glColor3uivPROC glColor3uiv = NULL;
glColor3usPROC glColor3us = NULL;
glColor3usvPROC glColor3usv = NULL;
glColor4bPROC glColor4b = NULL;
glColor4bvPROC glColor4bv = NULL;
glColor4dPROC glColor4d = NULL;
glColor4dvPROC glColor4dv = NULL;
glColor4fPROC glColor4f = NULL;
glColor4fvPROC glColor4fv = NULL;
glColor4iPROC glColor4i = NULL;
glColor4ivPROC glColor4iv = NULL;
glColor4sPROC glColor4s = NULL;
glColor4svPROC glColor4sv = NULL;
glColor4ubPROC glColor4ub = NULL;
glColor4ubvPROC glColor4ubv = NULL;
glColor4uiPROC glColor4ui = NULL;
glColor4uivPROC glColor4uiv = NULL;
glColor4usPROC glColor4us = NULL;
glColor4usvPROC glColor4usv = NULL;
glColorMaskPROC glColorMask = NULL;
glColorMaterialPROC glColorMaterial = NULL;
glColorPointerPROC glColorPointer = NULL;
glCopyPixelsPROC glCopyPixels = NULL;
glCopyTexImage1DPROC glCopyTexImage1D = NULL;
glCopyTexImage2DPROC glCopyTexImage2D = NULL;
glCopyTexSubImage1DPROC glCopyTexSubImage1D = NULL;
glCopyTexSubImage2DPROC glCopyTexSubImage2D = NULL;
glCullFacePROC glCullFace = NULL;
glDeleteListsPROC glDeleteLists = NULL;
glDeleteTexturesPROC glDeleteTextures = NULL;
glDepthFuncPROC glDepthFunc = NULL;
glDepthMaskPROC glDepthMask = NULL;
glDepthRangePROC glDepthRange = NULL;
glDisablePROC glDisable = NULL;
glDisableClientStatePROC glDisableClientState = NULL;
glDrawArraysPROC glDrawArrays = NULL;
glDrawBufferPROC glDrawBuffer = NULL;
glDrawElementsPROC glDrawElements = NULL;
glDrawPixelsPROC glDrawPixels = NULL;
glEdgeFlagPROC glEdgeFlag = NULL;
glEdgeFlagPointerPROC glEdgeFlagPointer = NULL;
glEdgeFlagvPROC glEdgeFlagv = NULL;
glEnablePROC glEnable = NULL;
glEnableClientStatePROC glEnableClientState = NULL;
glEndPROC glEnd = NULL;
glEndListPROC glEndList = NULL;
glEvalCoord1dPROC glEvalCoord1d = NULL;
glEvalCoord1dvPROC glEvalCoord1dv = NULL;
glEvalCoord1fPROC glEvalCoord1f = NULL;
glEvalCoord1fvPROC glEvalCoord1fv = NULL;
glEvalCoord2dPROC glEvalCoord2d = NULL;
glEvalCoord2dvPROC glEvalCoord2dv = NULL;
glEvalCoord2fPROC glEvalCoord2f = NULL;
glEvalCoord2fvPROC glEvalCoord2fv = NULL;
glEvalMesh1PROC glEvalMesh1 = NULL;
glEvalMesh2PROC glEvalMesh2 = NULL;
glEvalPoint1PROC glEvalPoint1 = NULL;
glEvalPoint2PROC glEvalPoint2 = NULL;
glFeedbackBufferPROC glFeedbackBuffer = NULL;
glFinishPROC glFinish = NULL;
glFlushPROC glFlush = NULL;
glFogfPROC glFogf = NULL;
glFogfvPROC glFogfv = NULL;
glFogiPROC glFogi = NULL;
glFogivPROC glFogiv = NULL;
glFrontFacePROC glFrontFace = NULL;
glFrustumPROC glFrustum = NULL;
glGenListsPROC glGenLists = NULL;
glGenTexturesPROC glGenTextures = NULL;
glGetBooleanvPROC glGetBooleanv = NULL;
glGetClipPlanePROC glGetClipPlane = NULL;
glGetDoublevPROC glGetDoublev = NULL;
glGetErrorPROC glGetError = NULL;
glGetFloatvPROC glGetFloatv = NULL;
glGetIntegervPROC glGetIntegerv = NULL;
glGetLightfvPROC glGetLightfv = NULL;
glGetLightivPROC glGetLightiv = NULL;
glGetMapdvPROC glGetMapdv = NULL;
glGetMapfvPROC glGetMapfv = NULL;
glGetMapivPROC glGetMapiv = NULL;
glGetMaterialfvPROC glGetMaterialfv = NULL;
glGetMaterialivPROC glGetMaterialiv = NULL;
glGetPixelMapfvPROC glGetPixelMapfv = NULL;
glGetPixelMapuivPROC glGetPixelMapuiv = NULL;
glGetPixelMapusvPROC glGetPixelMapusv = NULL;
glGetPointervPROC glGetPointerv = NULL;
glGetPolygonStipplePROC glGetPolygonStipple = NULL;
glGetStringPROC glGetString = NULL;
glGetTexEnvfvPROC glGetTexEnvfv = NULL;
glGetTexEnvivPROC glGetTexEnviv = NULL;
glGetTexGendvPROC glGetTexGendv = NULL;
glGetTexGenfvPROC glGetTexGenfv = NULL;
glGetTexGenivPROC glGetTexGeniv = NULL;
glGetTexImagePROC glGetTexImage = NULL;
glGetTexLevelParameterfvPROC glGetTexLevelParameterfv = NULL;
glGetTexLevelParameterivPROC glGetTexLevelParameteriv = NULL;
glGetTexParameterfvPROC glGetTexParameterfv = NULL;
glGetTexParameterivPROC glGetTexParameteriv = NULL;
glHintPROC glHint = NULL;
glIndexMaskPROC glIndexMask = NULL;
glIndexPointerPROC glIndexPointer = NULL;
glIndexdPROC glIndexd = NULL;
glIndexdvPROC glIndexdv = NULL;
glIndexfPROC glIndexf = NULL;
glIndexfvPROC glIndexfv = NULL;
glIndexiPROC glIndexi = NULL;
glIndexivPROC glIndexiv = NULL;
glIndexsPROC glIndexs = NULL;
glIndexsvPROC glIndexsv = NULL;
glIndexubPROC glIndexub = NULL;
glIndexubvPROC glIndexubv = NULL;
glInitNamesPROC glInitNames = NULL;
glInterleavedArraysPROC glInterleavedArrays = NULL;
glIsEnabledPROC glIsEnabled = NULL;
glIsListPROC glIsList = NULL;
glIsTexturePROC glIsTexture = NULL;
glLightModelfPROC glLightModelf = NULL;
glLightModelfvPROC glLightModelfv = NULL;
glLightModeliPROC glLightModeli = NULL;
glLightModelivPROC glLightModeliv = NULL;
glLightfPROC glLightf = NULL;
glLightfvPROC glLightfv = NULL;
glLightiPROC glLighti = NULL;
glLightivPROC glLightiv = NULL;
glLineStipplePROC glLineStipple = NULL;
glLineWidthPROC glLineWidth = NULL;
glListBasePROC glListBase = NULL;
glLoadIdentityPROC glLoadIdentity = NULL;
glLoadMatrixdPROC glLoadMatrixd = NULL;
glLoadMatrixfPROC glLoadMatrixf = NULL;
glLoadNamePROC glLoadName = NULL;
glLogicOpPROC glLogicOp = NULL;
glMap1dPROC glMap1d = NULL;
glMap1fPROC glMap1f = NULL;
glMap2dPROC glMap2d = NULL;
glMap2fPROC glMap2f = NULL;
glMapGrid1dPROC glMapGrid1d = NULL;
glMapGrid1fPROC glMapGrid1f = NULL;
glMapGrid2dPROC glMapGrid2d = NULL;
glMapGrid2fPROC glMapGrid2f = NULL;
glMaterialfPROC glMaterialf = NULL;
glMaterialfvPROC glMaterialfv = NULL;
glMaterialiPROC glMateriali = NULL;
glMaterialivPROC glMaterialiv = NULL;
glMatrixModePROC glMatrixMode = NULL;
glMultMatrixdPROC glMultMatrixd = NULL;
glMultMatrixfPROC glMultMatrixf = NULL;
glNewListPROC glNewList = NULL;
glNormal3bPROC glNormal3b = NULL;
glNormal3bvPROC glNormal3bv = NULL;
glNormal3dPROC glNormal3d = NULL;
glNormal3dvPROC glNormal3dv = NULL;
glNormal3fPROC glNormal3f = NULL;
glNormal3fvPROC glNormal3fv = NULL;
glNormal3iPROC glNormal3i = NULL;
glNormal3ivPROC glNormal3iv = NULL;
glNormal3sPROC glNormal3s = NULL;
glNormal3svPROC glNormal3sv = NULL;
glNormalPointerPROC glNormalPointer = NULL;
glOrthoPROC glOrtho = NULL;
glPassThroughPROC glPassThrough = NULL;
glPixelMapfvPROC glPixelMapfv = NULL;
glPixelMapuivPROC glPixelMapuiv = NULL;
glPixelMapusvPROC glPixelMapusv = NULL;
glPixelStorefPROC glPixelStoref = NULL;
glPixelStoreiPROC glPixelStorei = NULL;
glPixelTransferfPROC glPixelTransferf = NULL;
glPixelTransferiPROC glPixelTransferi = NULL;
glPixelZoomPROC glPixelZoom = NULL;
glPointSizePROC glPointSize = NULL;
glPolygonModePROC glPolygonMode = NULL;
glPolygonOffsetPROC glPolygonOffset = NULL;
glPolygonStipplePROC glPolygonStipple = NULL;
glPopAttribPROC glPopAttrib = NULL;
glPopClientAttribPROC glPopClientAttrib = NULL;
glPopMatrixPROC glPopMatrix = NULL;
glPopNamePROC glPopName = NULL;
glPrioritizeTexturesPROC glPrioritizeTextures = NULL;
glPushAttribPROC glPushAttrib = NULL;
glPushClientAttribPROC glPushClientAttrib = NULL;
glPushMatrixPROC glPushMatrix = NULL;
glPushNamePROC glPushName = NULL;
glRasterPos2dPROC glRasterPos2d = NULL;
glRasterPos2dvPROC glRasterPos2dv = NULL;
glRasterPos2fPROC glRasterPos2f = NULL;
glRasterPos2fvPROC glRasterPos2fv = NULL;
glRasterPos2iPROC glRasterPos2i = NULL;
glRasterPos2ivPROC glRasterPos2iv = NULL;
glRasterPos2sPROC glRasterPos2s = NULL;
glRasterPos2svPROC glRasterPos2sv = NULL;
glRasterPos3dPROC glRasterPos3d = NULL;
glRasterPos3dvPROC glRasterPos3dv = NULL;
glRasterPos3fPROC glRasterPos3f = NULL;
glRasterPos3fvPROC glRasterPos3fv = NULL;
glRasterPos3iPROC glRasterPos3i = NULL;
glRasterPos3ivPROC glRasterPos3iv = NULL;
glRasterPos3sPROC glRasterPos3s = NULL;
glRasterPos3svPROC glRasterPos3sv = NULL;
glRasterPos4dPROC glRasterPos4d = NULL;
glRasterPos4dvPROC glRasterPos4dv = NULL;
glRasterPos4fPROC glRasterPos4f = NULL;
glRasterPos4fvPROC glRasterPos4fv = NULL;
glRasterPos4iPROC glRasterPos4i = NULL;
glRasterPos4ivPROC glRasterPos4iv = NULL;
glRasterPos4sPROC glRasterPos4s = NULL;
glRasterPos4svPROC glRasterPos4sv = NULL;
glReadBufferPROC glReadBuffer = NULL;
glReadPixelsPROC glReadPixels = NULL;
glRectdPROC glRectd = NULL;
glRectdvPROC glRectdv = NULL;
glRectfPROC glRectf = NULL;
glRectfvPROC glRectfv = NULL;
glRectiPROC glRecti = NULL;
glRectivPROC glRectiv = NULL;
glRectsPROC glRects = NULL;
glRectsvPROC glRectsv = NULL;
glRenderModePROC glRenderMode = NULL;
glRotatedPROC glRotated = NULL;
glRotatefPROC glRotatef = NULL;
glScaledPROC glScaled = NULL;
glScalefPROC glScalef = NULL;
glScissorPROC glScissor = NULL;
glSelectBufferPROC glSelectBuffer = NULL;
glShadeModelPROC glShadeModel = NULL;
glStencilFuncPROC glStencilFunc = NULL;
glStencilMaskPROC glStencilMask = NULL;
glStencilOpPROC glStencilOp = NULL;
glTexCoord1dPROC glTexCoord1d = NULL;
glTexCoord1dvPROC glTexCoord1dv = NULL;
glTexCoord1fPROC glTexCoord1f = NULL;
glTexCoord1fvPROC glTexCoord1fv = NULL;
glTexCoord1iPROC glTexCoord1i = NULL;
glTexCoord1ivPROC glTexCoord1iv = NULL;
glTexCoord1sPROC glTexCoord1s = NULL;
glTexCoord1svPROC glTexCoord1sv = NULL;
glTexCoord2dPROC glTexCoord2d = NULL;
glTexCoord2dvPROC glTexCoord2dv = NULL;
glTexCoord2fPROC glTexCoord2f = NULL;
glTexCoord2fvPROC glTexCoord2fv = NULL;
glTexCoord2iPROC glTexCoord2i = NULL;
glTexCoord2ivPROC glTexCoord2iv = NULL;
glTexCoord2sPROC glTexCoord2s = NULL;
glTexCoord2svPROC glTexCoord2sv = NULL;
glTexCoord3dPROC glTexCoord3d = NULL;
glTexCoord3dvPROC glTexCoord3dv = NULL;
glTexCoord3fPROC glTexCoord3f = NULL;
glTexCoord3fvPROC glTexCoord3fv = NULL;
glTexCoord3iPROC glTexCoord3i = NULL;
glTexCoord3ivPROC glTexCoord3iv = NULL;
glTexCoord3sPROC glTexCoord3s = NULL;
glTexCoord3svPROC glTexCoord3sv = NULL;
glTexCoord4dPROC glTexCoord4d = NULL;
glTexCoord4dvPROC glTexCoord4dv = NULL;
glTexCoord4fPROC glTexCoord4f = NULL;
glTexCoord4fvPROC glTexCoord4fv = NULL;
glTexCoord4iPROC glTexCoord4i = NULL;
glTexCoord4ivPROC glTexCoord4iv = NULL;
glTexCoord4sPROC glTexCoord4s = NULL;
glTexCoord4svPROC glTexCoord4sv = NULL;
glTexCoordPointerPROC glTexCoordPointer = NULL;
glTexEnvfPROC glTexEnvf = NULL;
glTexEnvfvPROC glTexEnvfv = NULL;
glTexEnviPROC glTexEnvi = NULL;
glTexEnvivPROC glTexEnviv = NULL;
glTexGendPROC glTexGend = NULL;
glTexGendvPROC glTexGendv = NULL;
glTexGenfPROC glTexGenf = NULL;
glTexGenfvPROC glTexGenfv = NULL;
glTexGeniPROC glTexGeni = NULL;
glTexGenivPROC glTexGeniv = NULL;
glTexImage1DPROC glTexImage1D = NULL;
glTexImage2DPROC glTexImage2D = NULL;
glTexParameterfPROC glTexParameterf = NULL;
glTexParameterfvPROC glTexParameterfv = NULL;
glTexParameteriPROC glTexParameteri = NULL;
glTexParameterivPROC glTexParameteriv = NULL;
glTexSubImage1DPROC glTexSubImage1D = NULL;
glTexSubImage2DPROC glTexSubImage2D = NULL;
glTranslatedPROC glTranslated = NULL;
glTranslatefPROC glTranslatef = NULL;
glVertex2dPROC glVertex2d = NULL;
glVertex2dvPROC glVertex2dv = NULL;
glVertex2fPROC glVertex2f = NULL;
glVertex2fvPROC glVertex2fv = NULL;
glVertex2iPROC glVertex2i = NULL;
glVertex2ivPROC glVertex2iv = NULL;
glVertex2sPROC glVertex2s = NULL;
glVertex2svPROC glVertex2sv = NULL;
glVertex3dPROC glVertex3d = NULL;
glVertex3dvPROC glVertex3dv = NULL;
glVertex3fPROC glVertex3f = NULL;
glVertex3fvPROC glVertex3fv = NULL;
glVertex3iPROC glVertex3i = NULL;
glVertex3ivPROC glVertex3iv = NULL;
glVertex3sPROC glVertex3s = NULL;
glVertex3svPROC glVertex3sv = NULL;
glVertex4dPROC glVertex4d = NULL;
glVertex4dvPROC glVertex4dv = NULL;
glVertex4fPROC glVertex4f = NULL;
glVertex4fvPROC glVertex4fv = NULL;
glVertex4iPROC glVertex4i = NULL;
glVertex4ivPROC glVertex4iv = NULL;
glVertex4sPROC glVertex4s = NULL;
glVertex4svPROC glVertex4sv = NULL;
glVertexPointerPROC glVertexPointer = NULL;
glViewportPROC glViewport = NULL;*/

/*-------------------------------------*/
/* WGL stuff */
/*-------------------------------------*/

#ifdef _WIN32

/* WGL_EXT_etxension_string */

wglGetExtensionsStringEXTPROC wglGetExtensionsStringEXT = NULL;

/* WGL_ARB_buffer_region */

wglCreateBufferRegionARBPROC wglCreateBufferRegionARB = NULL;
wglDeleteBufferRegionARBPROC wglDeleteBufferRegionARB = NULL;
wglSaveBufferRegionARBPROC wglSaveBufferRegionARB = NULL;
wglRestoreBufferRegionARBPROC wglRestoreBufferRegionARB = NULL;

/* WGL_ARB_extension_string */


wglGetExtensionsStringARBPROC wglGetExtensionsStringARB = NULL;

/* WGL_ARB_pbuffer */

wglCreatePbufferARBPROC wglCreatePbufferARB = NULL;
wglGetPbufferDCARBPROC wglGetPbufferDCARB = NULL;
wglReleasePbufferDCARBPROC wglReleasePbufferDCARB = NULL;
wglDestroyPbufferARBPROC wglDestroyPbufferARB = NULL;
wglQueryPbufferARBPROC wglQueryPbufferARB = NULL;

/* WGL_ARB_pixel_format */

wglGetPixelFormatAttribivARBPROC wglGetPixelFormatAttribivARB = NULL;
wglGetPixelFormatAttribfvARBPROC wglGetPixelFormatAttribfvARB = NULL;
wglChoosePixelFormatARBPROC wglChoosePixelFormatARB = NULL;

/* WGL_ARB_render_texture */

wglBindTexImageARBPROC wglBindTexImageARB = NULL;
wglReleaseTexImageARBPROC wglReleaseTexImageARB = NULL;
wglSetPbufferAttribARBPROC wglSetPbufferAttribARB = NULL;

/* WGL_EXT_swap_control */

wglSwapIntervalEXTPROC wglSwapIntervalEXT = NULL;
wglGetSwapIntervalEXTPROC wglGetSwapIntervalEXT = NULL;

/* WGL_ARB_make_current_read */

wglMakeContextCurrentARBPROC wglMakeContextCurrentARB = NULL;
wglGetCurrentReadDCARBPROC wglGetCurrentReadDCARB = NULL;

/* VAR */

#endif /* WIN32 */

/*-------------------------------------*/
/*---WGL STUFF END---------------------*/
/*-------------------------------------*/


/*glBlendColorPROC glBlendColor = NULL;
glBlendEquationPROC glBlendEquation = NULL;
glColorTablePROC glColorTable = NULL;
glColorTableParameterfvPROC glColorTableParameterfv = NULL;
glColorTableParameterivPROC glColorTableParameteriv = NULL;
glCopyColorTablePROC glCopyColorTable = NULL;
glGetColorTablePROC glGetColorTable = NULL;
glGetColorTableParameterfvPROC glGetColorTableParameterfv = NULL;
glGetColorTableParameterivPROC glGetColorTableParameteriv = NULL;
glColorSubTablePROC glColorSubTable = NULL;
glCopyColorSubTablePROC glCopyColorSubTable = NULL;
glConvolutionFilter1DPROC glConvolutionFilter1D = NULL;
glConvolutionFilter2DPROC glConvolutionFilter2D = NULL;
glConvolutionParameterfPROC glConvolutionParameterf = NULL;
glConvolutionParameterfvPROC glConvolutionParameterfv = NULL;
glConvolutionParameteriPROC glConvolutionParameteri = NULL;
glConvolutionParameterivPROC glConvolutionParameteriv = NULL;
glCopyConvolutionFilter1DPROC glCopyConvolutionFilter1D = NULL;
glCopyConvolutionFilter2DPROC glCopyConvolutionFilter2D = NULL;
glGetConvolutionFilterPROC glGetConvolutionFilter = NULL;
glGetConvolutionParameterfvPROC glGetConvolutionParameterfv = NULL;
glGetConvolutionParameterivPROC glGetConvolutionParameteriv = NULL;
glGetSeparableFilterPROC glGetSeparableFilter = NULL;
glSeparableFilter2DPROC glSeparableFilter2D = NULL;
glGetHistogramPROC glGetHistogram = NULL;
glGetHistogramParameterfvPROC glGetHistogramParameterfv = NULL;
glGetHistogramParameterivPROC glGetHistogramParameteriv = NULL;
glGetMinmaxPROC glGetMinmax = NULL;
glGetMinmaxParameterfvPROC glGetMinmaxParameterfv = NULL;
glGetMinmaxParameterivPROC glGetMinmaxParameteriv = NULL;
glHistogramPROC glHistogram = NULL;
glMinmaxPROC glMinmax = NULL;
glResetHistogramPROC glResetHistogram = NULL;
glResetMinmaxPROC glResetMinmax = NULL;*/

/* EXT_cull_vertex */

glCullParameterfvEXTPROC glCullParameterfvEXT = NULL;
glCullParameterdvEXTPROC glCullParameterdvEXT = NULL;

//glBlendFuncSeparateEXTPROC glBlendFuncSeparateEXT = NULL;
//glBlendFuncSeparateINGRPROC glBlendFuncSeparateINGR = NULL;

glElementPointerNVPROC glElementPointerNV = NULL;
glDrawElementArrayNVPROC glDrawElementArrayNV = NULL;
glDrawRangeElementArrayNVPROC glDrawRangeElementArrayNV = NULL;
glMultiDrawElementArrayNVPROC glMultiDrawElementArrayNV = NULL;
glMultiDrawRangeElementArrayNVPROC glMultiDrawRangeElementArrayNV = NULL;

bool extgl_error = false;

struct ExtensionTypes extgl_Extensions;

#ifdef _WIN32
HMODULE lib_gl_handle = NULL;
#endif

#ifdef _X11
void * lib_gl_handle = NULL;
#endif

#ifdef _AGL
CFBundleRef opengl_bundle_ref = NULL;
CFBundleRef agl_bundle_ref = NULL;
#endif

static void doExtension(JNIEnv *env, jobject ext_set, const char *method_name, const char *ext) {
	jclass clazz = env->GetObjectClass(ext_set);
	jmethodID id = env->GetMethodID(clazz, method_name, "(Ljava/lang/Object;)Z");
	if (id == NULL)
		return;
	jstring ext_string = env->NewStringUTF(ext);
	if (ext_string == NULL) {
		printf("Could not allocate java string from %s\n", ext);
		return;
	}
	env->CallBooleanMethod(ext_set, id, ext_string);
}

void extgl_removeExtension(JNIEnv *env, jobject ext_set, const char *ext) {
	doExtension(env, ext_set, "remove", ext);
}

static void insertExtension(JNIEnv *env, jobject ext_set, const char *ext) {
	doExtension(env, ext_set, "add", ext);
}

#ifdef _AGL
// -------------------------
static CFBundleRef loadBundle(const Str255 frameworkName)
{
	OSStatus err = noErr;
	FSRefParam fileRefParam;
	FSRef fileRef;
	CFURLRef bundleURLOpenGL;
	CFBundleRef bundle_ref;

	memset(&fileRefParam, 0, sizeof(fileRefParam));
	memset(&fileRef, 0, sizeof(fileRef));
	fileRefParam.ioNamePtr  = frameworkName;
	fileRefParam.newRef = &fileRef;

	// Frameworks directory/folder
	//
	err = FindFolder (kSystemDomain, kFrameworksFolderType, false, &fileRefParam.ioVRefNum, &fileRefParam.ioDirID);
	if (noErr != err)
	{
		printfDebug("Could not find frameworks folder\n");
		return NULL;
	}

	// make FSRef for folder
	//
	err = PBMakeFSRefSync (&fileRefParam);


	if (noErr != err)
	{
		printfDebug("Could make FSref to frameworks folder\n");
		return NULL;
	}

	// create URL to folder
	//
	bundleURLOpenGL = CFURLCreateFromFSRef (kCFAllocatorDefault, &fileRef);
	if (!bundleURLOpenGL)
	{
		printfDebug("Could create framework URL\n");
		return NULL;
	}

	bundle_ref = CFBundleCreate(kCFAllocatorDefault,bundleURLOpenGL);
	CFRelease (bundleURLOpenGL);
	if (bundle_ref == NULL)
	{
		printfDebug("Could not load framework\n");
		return NULL;
	}

	// if the code was successfully loaded, look for our function.
	if (!CFBundleLoadExecutable(bundle_ref))
	{
		printfDebug("Could not load MachO executable\n");
		CFRelease(bundle_ref);
		return NULL;
	}

	return bundle_ref;
}

static void aglUnloadFramework(CFBundleRef f)
{
	CFBundleUnloadExecutable(f);
	CFRelease(f);
}

#endif

/* getProcAddress */

void *extgl_GetProcAddress(const char *name)
{
#ifdef _WIN32
	void *t = wglGetProcAddress(name);
	if (t == NULL)
	{
		t = GetProcAddress(lib_gl_handle, name);
		if (t == NULL)
		{
			printfDebug("Could not locate symbol %s\n", name);
			extgl_error = true;
		}
	}
	return t;
#endif

#ifdef _X11
	void *t = (void*)glXGetProcAddressARB((const GLubyte*)name);
	if (t == NULL)
	{
		t = dlsym(lib_gl_handle, name);
		if (t == NULL)
		{
			printfDebug("Could not locate symbol %s\n", name);
			extgl_error = true;
		}
	}
	return t;
#endif

#ifdef _AGL
	CFStringRef str = CFStringCreateWithCStringNoCopy(NULL, name, kCFStringEncodingUTF8, kCFAllocatorNull);
	void *func_pointer = CFBundleGetFunctionPointerForName(opengl_bundle_ref, str);
	if (func_pointer == NULL) {
		func_pointer = CFBundleGetFunctionPointerForName(agl_bundle_ref, str);
		if (func_pointer == NULL) {
			printfDebug("Could not locate symbol %s\n", name);
			extgl_error = true;
		}
	}
	CFRelease(str);
	return func_pointer;
#endif
}

static bool QueryExtension(JNIEnv *env, jobject ext_set, const GLubyte*extensions, const char *name)
{
	const GLubyte *start;
	GLubyte *where, *terminator;

	if (extensions == NULL) {
		printfDebug("NULL extension string\n");
		extgl_error = true;
		return false;
	}

	/* Extension names should not have spaces. */
	where = (GLubyte *) strchr(name, ' ');
	if (where || *name == '\0')
		return false;

	/* It takes a bit of care to be fool-proof about parsing the
		 OpenGL extensions string. Don't be fooled by sub-strings,
		etc. */
	start = extensions;
	for (;;)
	{
		where = (GLubyte *) strstr((const char *) start, name);
		if (!where)
			break;
		terminator = where + strlen(name);
		if (where == start || *(where - 1) == ' ')
			if (*terminator == ' ' || *terminator == '\0') {
				if (ext_set != NULL) { 
					insertExtension(env, ext_set, name);
				}
				return true;
			}
		start = terminator;
	}
	return false;

}

/*-----------------------------------------------------*/
/* WGL stuff */
/*-----------------------------------------------------*/

#ifdef _WIN32

/** returns true if the extention is available */
static bool WGLQueryExtension(JNIEnv *env, const char *name)
{
	const GLubyte *extensions;

	if (wglGetExtensionsStringARB == NULL)
		if (wglGetExtensionsStringEXT == NULL)
			return false;
		else
			extensions = (GLubyte*)wglGetExtensionsStringEXT();
	else
		extensions = (GLubyte*)wglGetExtensionsStringARB(wglGetCurrentDC());
	return QueryExtension(env, NULL, extensions, name);
}

static void extgl_InitWGLARBBufferRegion(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_buffer_region)
		return;
	wglCreateBufferRegionARB = (wglCreateBufferRegionARBPROC) extgl_GetProcAddress("wglCreateBufferRegionARB");
	wglDeleteBufferRegionARB = (wglDeleteBufferRegionARBPROC) extgl_GetProcAddress("wglDeleteBufferRegionARB");
	wglSaveBufferRegionARB = (wglSaveBufferRegionARBPROC) extgl_GetProcAddress("wglSaveBufferRegionARB");
	wglRestoreBufferRegionARB = (wglRestoreBufferRegionARBPROC) extgl_GetProcAddress("wglRestoreBufferRegionARB");

	EXTGL_SANITY_CHECK(env, NULL, WGL_ARB_buffer_region);
}

static void extgl_InitWGLARBPbuffer(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_pbuffer)
		return;
	wglCreatePbufferARB = (wglCreatePbufferARBPROC) extgl_GetProcAddress("wglCreatePbufferARB");
	wglGetPbufferDCARB = (wglGetPbufferDCARBPROC) extgl_GetProcAddress("wglGetPbufferDCARB");
	wglReleasePbufferDCARB = (wglReleasePbufferDCARBPROC) extgl_GetProcAddress("wglReleasePbufferDCARB");
	wglDestroyPbufferARB = (wglDestroyPbufferARBPROC) extgl_GetProcAddress("wglDestroyPbufferARB");
	wglQueryPbufferARB = (wglQueryPbufferARBPROC) extgl_GetProcAddress("wglQueryPbufferARB");
	EXTGL_SANITY_CHECK(env, NULL, WGL_ARB_pbuffer);

}

static void extgl_InitWGLARBPixelFormat(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_pixel_format)
		return;
	wglGetPixelFormatAttribivARB = (wglGetPixelFormatAttribivARBPROC) extgl_GetProcAddress("wglGetPixelFormatAttribivARB");
	wglGetPixelFormatAttribfvARB = (wglGetPixelFormatAttribfvARBPROC) extgl_GetProcAddress("wglGetPixelFormatAttribfvARB");
	wglChoosePixelFormatARB = (wglChoosePixelFormatARBPROC) extgl_GetProcAddress("wglChoosePixelFormatARB");
	EXTGL_SANITY_CHECK(env, NULL, WGL_ARB_pixel_format);

}

static void extgl_InitWGLARBRenderTexture(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_render_texture)
		return;
	wglBindTexImageARB = (wglBindTexImageARBPROC) extgl_GetProcAddress("wglBindTexImageARB");
	wglReleaseTexImageARB = (wglReleaseTexImageARBPROC) extgl_GetProcAddress("wglReleaseTexImageARB");
	wglSetPbufferAttribARB = (wglSetPbufferAttribARBPROC) extgl_GetProcAddress("wglSetPbufferAttribARB");
	EXTGL_SANITY_CHECK(env, NULL, WGL_ARB_render_texture);

}

static void extgl_InitWGLEXTSwapControl(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_EXT_swap_control)
		return;
	wglSwapIntervalEXT = (wglSwapIntervalEXTPROC) extgl_GetProcAddress("wglSwapIntervalEXT");
	wglGetSwapIntervalEXT = (wglGetSwapIntervalEXTPROC) extgl_GetProcAddress("wglGetSwapIntervalEXT");
	EXTGL_SANITY_CHECK(env, NULL, WGL_EXT_swap_control);

}

static void extgl_InitWGLARBMakeCurrentRead(JNIEnv *env)
{
	if (!extgl_Extensions.WGL_ARB_make_current_read)
		return;
	wglMakeContextCurrentARB = (wglMakeContextCurrentARBPROC) extgl_GetProcAddress("wglMakeContextCurrentARB");
	wglGetCurrentReadDCARB = (wglGetCurrentReadDCARBPROC) extgl_GetProcAddress("wglGetCurrentReadDCARB");
	EXTGL_SANITY_CHECK(env, NULL, WGL_ARB_make_current_read);

}

static void extgl_InitSupportedWGLExtensions(JNIEnv *env)
{
	extgl_Extensions.WGL_ARB_buffer_region = WGLQueryExtension(env, "WGL_ARB_buffer_region");
	extgl_Extensions.WGL_ARB_make_current_read = WGLQueryExtension(env, "WGL_ARB_make_current_read");
	extgl_Extensions.WGL_ARB_multisample = WGLQueryExtension(env, "WGL_ARB_multisample");
	extgl_Extensions.WGL_ARB_pbuffer = WGLQueryExtension(env, "WGL_ARB_pbuffer");
	extgl_Extensions.WGL_ARB_pixel_format = WGLQueryExtension(env, "WGL_ARB_pixel_format");
	extgl_Extensions.WGL_ARB_render_texture = WGLQueryExtension(env, "WGL_ARB_render_texture");
	extgl_Extensions.WGL_EXT_swap_control = WGLQueryExtension(env, "WGL_EXT_swap_control");
	extgl_Extensions.WGL_NV_render_depth_texture = WGLQueryExtension(env, "WGL_NV_render_depth_texture");
	extgl_Extensions.WGL_NV_render_texture_rectangle = WGLQueryExtension(env, "WGL_NV_render_texture_rectangle");
}

void extgl_InitWGL(JNIEnv *env)
{
	wglGetExtensionsStringARB = (wglGetExtensionsStringARBPROC) extgl_GetProcAddress("wglGetExtensionsStringARB");
	wglGetExtensionsStringEXT = (wglGetExtensionsStringEXTPROC) extgl_GetProcAddress("wglGetExtensionsStringEXT");
	extgl_Extensions.WGL_ARB_extensions_string = wglGetExtensionsStringARB != NULL;
	extgl_Extensions.WGL_EXT_extensions_string = wglGetExtensionsStringEXT != NULL;
	extgl_error = false;

	extgl_InitSupportedWGLExtensions(env);

	extgl_InitWGLARBMakeCurrentRead(env);
	extgl_InitWGLEXTSwapControl(env);
	extgl_InitWGLARBRenderTexture(env);
	extgl_InitWGLARBPixelFormat(env);
	extgl_InitWGLARBPbuffer(env);
	extgl_InitWGLARBBufferRegion(env);
}

#endif /* WIN32 */

/*-----------------------------------------------------*/
/* WGL stuff END*/
/*-----------------------------------------------------*/

/*-----------------------------------------------------*/
/* AGL stuff BEGIN*/
/*-----------------------------------------------------*/
#ifdef _AGL

bool extgl_InitAGL(JNIEnv *env)
{
	aglChoosePixelFormat = (aglChoosePixelFormatPROC)extgl_GetProcAddress("aglChoosePixelFormat");
	aglDestroyPixelFormat = (aglDestroyPixelFormatPROC)extgl_GetProcAddress("aglDestroyPixelFormat");
	aglNextPixelFormat = (aglNextPixelFormatPROC)extgl_GetProcAddress("aglNextPixelFormat");
	aglDescribePixelFormat = (aglDescribePixelFormatPROC)extgl_GetProcAddress("aglDescribePixelFormat");
	aglDevicesOfPixelFormat = (aglDevicesOfPixelFormatPROC)extgl_GetProcAddress("aglDevicesOfPixelFormat");
	aglQueryRendererInfo = (aglQueryRendererInfoPROC)extgl_GetProcAddress("aglQueryRendererInfo");
	aglDestroyRendererInfo = (aglDestroyRendererInfoPROC)extgl_GetProcAddress("aglDestroyRendererInfo");
	aglNextRendererInfo = (aglNextRendererInfoPROC)extgl_GetProcAddress("aglNextRendererInfo");
	aglDescribeRenderer = (aglDescribeRendererPROC)extgl_GetProcAddress("aglDescribeRenderer");
	aglCreateContext = (aglCreateContextPROC)extgl_GetProcAddress("aglCreateContext");
	aglDestroyContext = (aglDestroyContextPROC)extgl_GetProcAddress("aglDestroyContext");
	aglCopyContext = (aglCopyContextPROC)extgl_GetProcAddress("aglCopyContext");
	aglUpdateContext = (aglUpdateContextPROC)extgl_GetProcAddress("aglUpdateContext");
	aglSetCurrentContext = (aglSetCurrentContextPROC)extgl_GetProcAddress("aglSetCurrentContext");
	aglGetCurrentContext = (aglGetCurrentContextPROC)extgl_GetProcAddress("aglGetCurrentContext");
	aglSetDrawable = (aglSetDrawablePROC)extgl_GetProcAddress("aglSetDrawable");
	aglSetOffScreen = (aglSetOffScreenPROC)extgl_GetProcAddress("aglSetOffScreen");
	aglSetFullScreen = (aglSetFullScreenPROC)extgl_GetProcAddress("aglSetFullScreen");
	aglGetDrawable = (aglGetDrawablePROC)extgl_GetProcAddress("aglGetDrawable");
	aglSetVirtualScreen = (aglSetVirtualScreenPROC)extgl_GetProcAddress("aglSetVirtualScreen");
	aglGetVirtualScreen = (aglGetVirtualScreenPROC)extgl_GetProcAddress("aglGetVirtualScreen");
	aglGetVersion = (aglGetVersionPROC)extgl_GetProcAddress("aglGetVersion");
	aglSwapBuffers = (aglSwapBuffersPROC)extgl_GetProcAddress("aglSwapBuffers");
	aglEnable = (aglEnablePROC)extgl_GetProcAddress("aglEnable");
	aglDisable = (aglDisablePROC)extgl_GetProcAddress("aglDisable");
	aglIsEnabled = (aglIsEnabledPROC)extgl_GetProcAddress("aglIsEnabled");
	aglSetInteger = (aglSetIntegerPROC)extgl_GetProcAddress("aglSetInteger");
	aglGetInteger = (aglGetIntegerPROC)extgl_GetProcAddress("aglGetInteger");
	aglUseFont = (aglUseFontPROC)extgl_GetProcAddress("aglUseFont");
	aglGetError = (aglGetErrorPROC)extgl_GetProcAddress("aglGetError");
	aglErrorString = (aglErrorStringPROC)extgl_GetProcAddress("aglErrorString");
	aglResetLibrary = (aglResetLibraryPROC)extgl_GetProcAddress("aglResetLibrary");
	aglSurfaceTexture = (aglSurfaceTexturePROC)extgl_GetProcAddress("aglSurfaceTexture");
	return !extgl_error;
}

#endif
/*-----------------------------------------------------*/
/* AGL stuff END*/
/*-----------------------------------------------------*/

#ifdef _X11
/** returns true if the extention is available */
static bool GLXQueryExtension(JNIEnv* env, Display *disp, int screen, const char *name)
{
	const GLubyte *exts = (const GLubyte *)glXQueryExtensionsString(disp, screen);
	return QueryExtension(env, NULL, exts, name);
}
#endif

/** returns true if the extention is available */
static bool GLQueryExtension(JNIEnv *env, jobject ext_set, const char *name)
{
	return QueryExtension(env, ext_set, glGetString(GL_EXTENSIONS), name);
}

/*static void extgl_InitNVPrimitiveRestart(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_primitive_restart)
		return;
	glPrimitiveRestartNV = (glPrimitiveRestartNVPROC) extgl_GetProcAddress("glPrimitiveRestartNV");
	glPrimitiveRestartIndexNV = (glPrimitiveRestartIndexNVPROC) extgl_GetProcAddress("glPrimitiveRestartIndexNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_primitive_restart)
}

static void extgl_InitNVElementArray(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_element_array)
		return;
	glElementPointerNV = (glElementPointerNVPROC) extgl_GetProcAddress("glElementPointerNV");
	glDrawElementArrayNV = (glDrawElementArrayNVPROC) extgl_GetProcAddress("glDrawElementArrayNV");
	glDrawRangeElementArrayNV = (glDrawRangeElementArrayNVPROC) extgl_GetProcAddress("glDrawRangeElementArrayNV");
	glMultiDrawElementArrayNV = (glMultiDrawElementArrayNVPROC) extgl_GetProcAddress("glMultiDrawElementArrayNV");
	glMultiDrawRangeElementArrayNV = (glMultiDrawRangeElementArrayNVPROC) extgl_GetProcAddress("glMultiDrawRangeElementArrayNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_element_array)
}


static void extgl_InitEXTCullVertex(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_cull_vertex)
		return;
	glCullParameterfvEXT = (glCullParameterfvEXTPROC) extgl_GetProcAddress("glCullParameterfvEXT");
	glCullParameterdvEXT = (glCullParameterdvEXTPROC) extgl_GetProcAddress("glCullParameterdvEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_cull_vertex)
}
*/

#ifdef _X11
static void extgl_InitGLX13(JNIEnv *env)
{
	if (extgl_Extensions.GLX13 != 1)
		return;
	glXGetFBConfigs = (glXGetFBConfigsPROC) extgl_GetProcAddress("glXGetFBConfigs");
	glXChooseFBConfig = (glXChooseFBConfigPROC) extgl_GetProcAddress("glXChooseFBConfig");
	glXGetFBConfigAttrib = (glXGetFBConfigAttribPROC) extgl_GetProcAddress("glXGetFBConfigAttrib");
	glXGetVisualFromFBConfig = (glXGetVisualFromFBConfigPROC) extgl_GetProcAddress("glXGetVisualFromFBConfig");
	glXCreateWindow = (glXCreateWindowPROC) extgl_GetProcAddress("glXCreateWindow");
	glXDestroyWindow = (glXDestroyWindowPROC) extgl_GetProcAddress("glXDestroyWindow");
	glXCreatePixmap = (glXCreatePixmapPROC) extgl_GetProcAddress("glXCreatePixmap");
	glXDestroyPixmap = (glXDestroyPixmapPROC) extgl_GetProcAddress("glXDestroyPixmap");
	glXCreatePbuffer = (glXCreatePbufferPROC) extgl_GetProcAddress("glXCreatePbuffer");
	glXDestroyPbuffer = (glXDestroyPbufferPROC) extgl_GetProcAddress("glXDestroyPbuffer");
	glXQueryDrawable = (glXQueryDrawablePROC) extgl_GetProcAddress("glXQueryDrawable");
	glXCreateNewContext = (glXCreateNewContextPROC) extgl_GetProcAddress("glXCreateNewContext");
	glXMakeContextCurrent = (glXMakeContextCurrentPROC) extgl_GetProcAddress("glXMakeContextCurrent");
	glXGetCurrentReadDrawable = (glXGetCurrentReadDrawablePROC) extgl_GetProcAddress("glXGetCurrentReadDrawable");
	glXGetCurrentDisplay = (glXGetCurrentDisplayPROC) extgl_GetProcAddress("glXGetCurrentDisplay");
	glXQueryContext = (glXQueryContextPROC) extgl_GetProcAddress("glXQueryContext");
	glXSelectEvent = (glXSelectEventPROC) extgl_GetProcAddress("glXSelectEvent");
	glXGetSelectedEvent = (glXGetSelectedEventPROC) extgl_GetProcAddress("glXGetSelectedEvent");
	EXTGL_SANITY_CHECK(env, (jobject)NULL, GLX13);
}

static bool extgl_InitGLX12(void)
{
	glXChooseVisual = (glXChooseVisualPROC) extgl_GetProcAddress("glXChooseVisual");
	glXCopyContext = (glXCopyContextPROC) extgl_GetProcAddress("glXCopyContext");
	glXCreateContext = (glXCreateContextPROC) extgl_GetProcAddress("glXCreateContext");
	glXCreateGLXPixmap = (glXCreateGLXPixmapPROC) extgl_GetProcAddress("glXCreateGLXPixmap");
	glXDestroyContext = (glXDestroyContextPROC) extgl_GetProcAddress("glXDestroyContext");
	glXDestroyGLXPixmap = (glXDestroyGLXPixmapPROC) extgl_GetProcAddress("glXDestroyGLXPixmap");
	glXGetConfig = (glXGetConfigPROC) extgl_GetProcAddress("glXGetConfig");
	glXGetCurrentContext = (glXGetCurrentContextPROC) extgl_GetProcAddress("glXGetCurrentContext");
	glXGetCurrentDrawable = (glXGetCurrentDrawablePROC) extgl_GetProcAddress("glXGetCurrentDrawable");
	glXIsDirect = (glXIsDirectPROC) extgl_GetProcAddress("glXIsDirect");
	glXMakeCurrent = (glXMakeCurrentPROC) extgl_GetProcAddress("glXMakeCurrent");
	glXQueryExtension = (glXQueryExtensionPROC) extgl_GetProcAddress("glXQueryExtension");
	glXQueryVersion = (glXQueryVersionPROC) extgl_GetProcAddress("glXQueryVersion");
	glXSwapBuffers = (glXSwapBuffersPROC) extgl_GetProcAddress("glXSwapBuffers");
	glXUseXFont = (glXUseXFontPROC) extgl_GetProcAddress("glXUseXFont");
	glXWaitGL = (glXWaitGLPROC) extgl_GetProcAddress("glXWaitGL");
	glXWaitX = (glXWaitXPROC) extgl_GetProcAddress("glXWaitX");
	glXGetClientString = (glXGetClientStringPROC) extgl_GetProcAddress("glXGetClientString");
	glXQueryServerString = (glXQueryServerStringPROC) extgl_GetProcAddress("glXQueryServerString");
	glXQueryExtensionsString = (glXQueryExtensionsStringPROC) extgl_GetProcAddress("glXQueryExtensionsString");
	return !extgl_error;
}

static void extgl_InitGLXSupportedExtensions(JNIEnv *env, Display *disp, int screen)
{
	extgl_Extensions.GLX_EXT_visual_info = GLXQueryExtension(env, disp, screen, "GLX_EXT_visual_info");
	extgl_Extensions.GLX_EXT_visual_rating = GLXQueryExtension(env, disp, screen, "GLX_EXT_visual_rating");
	extgl_Extensions.GLX_SGI_swap_control = GLXQueryExtension(env, disp, screen, "GLX_SGI_swap_control");
	extgl_Extensions.GLX_ARB_multisample = GLXQueryExtension(env, disp, screen, "GLX_ARB_multisample");
}

static void extgl_InitGLXSGISwapControl(JNIEnv *env)
{
	if (extgl_Extensions.GLX_SGI_swap_control != 1)
		return;
	glXSwapIntervalSGI = (glXSwapIntervalSGIPROC)extgl_GetProcAddress("glXSwapIntervalSGI");
	EXTGL_SANITY_CHECK(env, (jobject)NULL, GLX_SGI_swap_control);
}

bool extgl_InitGLX(JNIEnv *env, Display *disp, int screen)
{
	int major, minor;
	/* Assume glx ver >= 1.2 */
	extgl_Extensions.GLX12 = true;
	if (!extgl_InitGLX12())
		return false;
	extgl_InitGLXSupportedExtensions(env, disp, screen);
	if (glXQueryVersion(disp, &major, &minor) != True)
		return false;
	if (major > 1 || (major == 1 && minor >= 3))
		extgl_Extensions.GLX13 = true;
	extgl_InitGLX13(env);
	extgl_InitGLXSGISwapControl(env);
	return true;
}
#endif


static void extgl_InitSupportedExtensions(JNIEnv *env, jobject ext_set)
{
	char *s = (char*) glGetString(GL_VERSION);
	if (!s)
		return;
	s = strstr(s, "1.");
	extgl_Extensions.OpenGL12 = false;
	extgl_Extensions.OpenGL13 = false;
	extgl_Extensions.OpenGL14 = false;
	extgl_Extensions.OpenGL15 = false;
	if (s != NULL)
	{
		// Fall trhough
		switch (s[2]) {
			case '5':
				extgl_Extensions.OpenGL15 = true;
				insertExtension(env, ext_set, "OpenGL15");
			case '4':
				extgl_Extensions.OpenGL14 = true;
				insertExtension(env, ext_set, "OpenGL14");
			case '3':
				extgl_Extensions.OpenGL13 = true;
				insertExtension(env, ext_set, "OpenGL13");
			case '2':
				extgl_Extensions.OpenGL12 = true;
				insertExtension(env, ext_set, "OpenGL12");
				break;
		}
	}
	extgl_Extensions.GL_ARB_depth_texture = GLQueryExtension(env, ext_set, "GL_ARB_depth_texture");
	extgl_Extensions.GL_ARB_fragment_program = GLQueryExtension(env, ext_set, "GL_ARB_fragment_program");
	extgl_Extensions.GL_ARB_imaging = GLQueryExtension(env, ext_set, "GL_ARB_imaging");
	extgl_Extensions.GL_ARB_matrix_palette = GLQueryExtension(env, ext_set, "GL_ARB_matrix_palette");
	extgl_Extensions.GL_ARB_multisample = GLQueryExtension(env, ext_set, "GL_ARB_multisample");
	extgl_Extensions.GL_ARB_multitexture = GLQueryExtension(env, ext_set, "GL_ARB_multitexture");
	extgl_Extensions.GL_ARB_occlusion_query = GLQueryExtension(env, ext_set, "GL_ARB_occlusion_query");
	extgl_Extensions.GL_ARB_point_parameters = GLQueryExtension(env, ext_set, "GL_ARB_point_parameters");
	extgl_Extensions.GL_ARB_shader_objects = GLQueryExtension(env, ext_set, "GL_ARB_shader_objects");
	extgl_Extensions.GL_ARB_shadow = GLQueryExtension(env, ext_set, "GL_ARB_shadow");
	extgl_Extensions.GL_ARB_shadow_ambient = GLQueryExtension(env, ext_set, "GL_ARB_shadow_ambient");
	extgl_Extensions.GL_ARB_texture_border_clamp = GLQueryExtension(env, ext_set, "GL_ARB_texture_border_clamp");
	extgl_Extensions.GL_ARB_texture_compression = GLQueryExtension(env, ext_set, "GL_ARB_texture_compression");
	extgl_Extensions.GL_ARB_texture_cube_map = GLQueryExtension(env, ext_set, "GL_ARB_texture_cube_map");
	extgl_Extensions.GL_ARB_texture_env_add = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_add");
	extgl_Extensions.GL_ARB_texture_env_combine = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_combine");
	extgl_Extensions.GL_ARB_texture_env_crossbar = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_crossbar");
	extgl_Extensions.GL_ARB_texture_env_dot3 = GLQueryExtension(env, ext_set, "GL_ARB_texture_env_dot3");
	extgl_Extensions.GL_ARB_texture_mirrored_repeat = GLQueryExtension(env, ext_set, "GL_ARB_texture_mirrored_repeat");
	extgl_Extensions.GL_ARB_transpose_matrix = GLQueryExtension(env, ext_set, "GL_ARB_transpose_matrix");
	extgl_Extensions.GL_ARB_vertex_blend = GLQueryExtension(env, ext_set, "GL_ARB_vertex_blend");
	extgl_Extensions.GL_ARB_vertex_buffer_object = GLQueryExtension(env, ext_set, "GL_ARB_vertex_buffer_object");
	extgl_Extensions.GL_ARB_vertex_program = GLQueryExtension(env, ext_set, "GL_ARB_vertex_program");
	extgl_Extensions.GL_ARB_vertex_shader = GLQueryExtension(env, ext_set, "GL_ARB_vertex_shaders");
	extgl_Extensions.GL_ARB_window_pos = GLQueryExtension(env, ext_set, "GL_ARB_window_pos");

	extgl_Extensions.GL_EXT_abgr = GLQueryExtension(env, ext_set, "GL_EXT_abgr");
	extgl_Extensions.GL_EXT_bgra = GLQueryExtension(env, ext_set, "GL_EXT_bgra");
	extgl_Extensions.GL_EXT_blend_func_separate = GLQueryExtension(env, ext_set, "GL_EXT_blend_function_separate");
	extgl_Extensions.GL_EXT_compiled_vertex_array = GLQueryExtension(env, ext_set, "GL_EXT_compiled_vertex_array");
	extgl_Extensions.GL_EXT_cull_vertex = GLQueryExtension(env, ext_set, "GL_EXT_cull_vertex");
	extgl_Extensions.GL_EXT_draw_range_elements = GLQueryExtension(env, ext_set, "GL_EXT_draw_range_elements");
	extgl_Extensions.GL_EXT_fog_coord = GLQueryExtension(env, ext_set, "GL_EXT_fog_coord");
	extgl_Extensions.GL_EXT_multi_draw_arrays = GLQueryExtension(env, ext_set, "GL_EXT_multi_draw_arrays");
	extgl_Extensions.GL_EXT_point_parameters = GLQueryExtension(env, ext_set, "GL_EXT_point_parameters");
	extgl_Extensions.GL_EXT_secondary_color = GLQueryExtension(env, ext_set, "GL_EXT_secondary_color");
	extgl_Extensions.GL_EXT_separate_specular_color = GLQueryExtension(env, ext_set, "GL_EXT_separate_specular_color");
	extgl_Extensions.GL_EXT_shadow_funcs = GLQueryExtension(env, ext_set, "GL_EXT_shadow_funcs");
	extgl_Extensions.GL_EXT_stencil_two_side = GLQueryExtension(env, ext_set, "GL_EXT_stencil_two_side");
	extgl_Extensions.GL_EXT_stencil_wrap = GLQueryExtension(env, ext_set, "GL_EXT_stencil_wrap");
	extgl_Extensions.GL_EXT_texture_compression_s3tc = GLQueryExtension(env, ext_set, "GL_EXT_texture_compression_s3tc");
	extgl_Extensions.GL_EXT_texture_env_combine = GLQueryExtension(env, ext_set, "GL_EXT_texture_env_combine");
	extgl_Extensions.GL_EXT_texture_filter_anisotropic = GLQueryExtension(env, ext_set, "GL_EXT_texture_filter_anisotropic");
	extgl_Extensions.GL_EXT_texture_lod_bias = GLQueryExtension(env, ext_set, "GL_EXT_texture_lod_bias");
	extgl_Extensions.GL_EXT_vertex_shader = GLQueryExtension(env, ext_set, "GL_EXT_vertex_shader");
	extgl_Extensions.GL_EXT_vertex_weighting = GLQueryExtension(env, ext_set, "GL_EXT_vertex_weighting");

	extgl_Extensions.GL_ATI_draw_buffers = GLQueryExtension(env, ext_set, "GL_ATI_draw_buffers");
	extgl_Extensions.GL_ATI_element_array = GLQueryExtension(env, ext_set, "GL_ATI_element_array");
	extgl_Extensions.GL_ATI_envmap_bumpmap = GLQueryExtension(env, ext_set, "GL_ATI_envmap_bumpmap");
	extgl_Extensions.GL_ATI_fragment_shader = GLQueryExtension(env, ext_set, "GL_ATI_fragment_shader");
	extgl_Extensions.GL_ATI_map_object_buffer = GLQueryExtension(env, ext_set, "GL_ATI_map_object_buffer");
	extgl_Extensions.GL_ATI_pn_triangles = GLQueryExtension(env, ext_set, "GL_ATI_pn_triangles");
	extgl_Extensions.GL_ATI_point_cull_mode = GLQueryExtension(env, ext_set, "GL_ATI_point_cull_mode");
	extgl_Extensions.GL_ATI_separate_stencil = GLQueryExtension(env, ext_set, "GL_ATI_separate_stencil");
	extgl_Extensions.GL_ATI_text_fragment_shader = GLQueryExtension(env, ext_set, "GL_ATI_text_fragment_shader");
	extgl_Extensions.GL_ATI_texture_mirror_once = GLQueryExtension(env, ext_set, "GL_ATI_texture_mirror_once");
	extgl_Extensions.GL_ATI_vertex_array_object = GLQueryExtension(env, ext_set, "GL_ATI_vertex_array_object");
	extgl_Extensions.GL_ATI_vertex_attrib_array_object = GLQueryExtension(env, ext_set, "GL_ATI_vertex_attrib_array_object");
	extgl_Extensions.GL_ATI_vertex_streams = GLQueryExtension(env, ext_set, "GL_ATI_vertex_streams");

	extgl_Extensions.GL_ATIX_point_sprites = GLQueryExtension(env, ext_set, "GL_ATIX_point_sprites");
	extgl_Extensions.GL_ATIX_texture_env_route = GLQueryExtension(env, ext_set, "GL_ATIX_texture_env_route");

	extgl_Extensions.GL_HP_occlusion_test = GLQueryExtension(env, ext_set, "GL_HP_occlusion_test");

	extgl_Extensions.GL_NV_blend_square = GLQueryExtension(env, ext_set, "GL_NV_blend_square");
	extgl_Extensions.GL_NV_copy_depth_to_color = GLQueryExtension(env, ext_set, "GL_NV_copy_depth_to_color");
	extgl_Extensions.GL_NV_depth_clamp = GLQueryExtension(env, ext_set, "GL_NV_depth_clamp");
	extgl_Extensions.GL_NV_element_array = GLQueryExtension(env, ext_set, "GL_NV_element_array");
	extgl_Extensions.GL_NV_evaluators = GLQueryExtension(env, ext_set, "GL_NV_evaluators");
	extgl_Extensions.GL_NV_fence = GLQueryExtension(env, ext_set, "GL_NV_fence");
	extgl_Extensions.GL_NV_float_buffer = GLQueryExtension(env, ext_set, "GL_NV_float_buffer");
	extgl_Extensions.GL_NV_fog_distance = GLQueryExtension(env, ext_set, "GL_NV_fog_distance");
	extgl_Extensions.GL_NV_fragment_program = GLQueryExtension(env, ext_set, "GL_NV_fragment_program");
	extgl_Extensions.GL_NV_half_float = GLQueryExtension(env, ext_set, "GL_NV_half_float");
	extgl_Extensions.GL_NV_light_max_exponent = GLQueryExtension(env, ext_set, "GL_NV_light_max_exponent");
	extgl_Extensions.GL_NV_occlusion_query = GLQueryExtension(env, ext_set, "GL_NV_occlusion_query");
	extgl_Extensions.GL_NV_packed_depth_stencil = GLQueryExtension(env, ext_set, "GL_NV_packed_depth_stencil");
	extgl_Extensions.GL_NV_pixel_data_range = GLQueryExtension(env, ext_set, "GL_NV_pixel_data_range");
	extgl_Extensions.GL_NV_point_sprite = GLQueryExtension(env, ext_set, "GL_NV_point_sprite");
	extgl_Extensions.GL_NV_primitive_restart = GLQueryExtension(env, ext_set, "GL_NV_primitive_restart");
	extgl_Extensions.GL_NV_register_combiners = GLQueryExtension(env, ext_set, "GL_NV_register_combiners");
	extgl_Extensions.GL_NV_register_combiners2 = GLQueryExtension(env, ext_set, "GL_NV_register_combiners2");
	extgl_Extensions.GL_NV_texgen_reflection = GLQueryExtension(env, ext_set, "GL_NV_texgen_reflection");
	extgl_Extensions.GL_NV_texture_env_combine4 = GLQueryExtension(env, ext_set, "GL_NV_texture_env_combine4");
	extgl_Extensions.GL_NV_texture_rectangle = GLQueryExtension(env, ext_set, "GL_NV_texture_rectangle");
	extgl_Extensions.GL_NV_texture_shader = GLQueryExtension(env, ext_set, "GL_NV_texture_shader");
	extgl_Extensions.GL_NV_texture_shader2 = GLQueryExtension(env, ext_set, "GL_NV_texture_shader2");
	extgl_Extensions.GL_NV_texture_shader3 = GLQueryExtension(env, ext_set, "GL_NV_texture_shader3");
	extgl_Extensions.GL_NV_vertex_array_range = GLQueryExtension(env, ext_set, "GL_NV_vertex_array_range");
	extgl_Extensions.GL_NV_vertex_array_range2 = GLQueryExtension(env, ext_set, "GL_NV_vertex_array_range2");
	extgl_Extensions.GL_NV_vertex_program = GLQueryExtension(env, ext_set, "GL_NV_vertex_program");
	extgl_Extensions.GL_NV_vertex_program1_1 = GLQueryExtension(env, ext_set, "GL_NV_vertex_program1_1");
	extgl_Extensions.GL_NV_vertex_program2 = GLQueryExtension(env, ext_set, "GL_NV_vertex_program2");

	extgl_Extensions.GL_SGIS_generate_mipmap = GLQueryExtension(env, ext_set, "GL_SGIS_generate_mipmap");
	extgl_Extensions.GL_SGIX_depth_texture = GLQueryExtension(env, ext_set, "GL_SGIX_depth_texture");
	extgl_Extensions.GL_SGIX_shadow = GLQueryExtension(env, ext_set, "GL_SGIX_shadow");
}

extern void extgl_InitOpenGL1_1();
//extern void extgl_InitARBFragmentProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBImaging(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBMatrixPalette(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBMultisample(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBMultitexture(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBOcclusionQuery(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBPointParameters(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBShaderObjects(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBTextureCompression(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBTransposeMatrix(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexBlend(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexBufferObject(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBVertexShader(JNIEnv *env, jobject ext_set);
extern void extgl_InitARBWindowPos(JNIEnv *env, jobject ext_set);

extern void extgl_InitEXTBlendFuncSeparate(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTCompiledVertexArray(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTDrawRangeElements(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTFogCoord(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTMultiDrawArrays(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTPointParameters(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTSecondaryColor(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTStencilTwoSide(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTVertexShader(JNIEnv *env, jobject ext_set);
extern void extgl_InitEXTVertexWeighting(JNIEnv *env, jobject ext_set);

extern void extgl_InitNVEvaluators(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVFragmentProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVFence(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVHalfFloat(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVOcclusionQuery(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVPixelDataRange(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVPointSprite(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVPrimitiveRestart(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVProgram(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVRegisterCombiners(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVRegisterCombiners2(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVVertexArrayRange(JNIEnv *env, jobject ext_set);
extern void extgl_InitNVVertexProgram(JNIEnv *env, jobject ext_set);

extern void extgl_InitATIDrawBuffers(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIElementArray(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIEnvmapBumpmap(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIFragmentShader(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIMapObjectBuffer(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIPNTriangles(JNIEnv *env, jobject ext_set);
extern void extgl_InitATISeparateStencil(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIVertexArrayObject(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIVertexAttribArrayObject(JNIEnv *env, jobject ext_set);
extern void extgl_InitATIVertexStreams(JNIEnv *env, jobject ext_set);

extern void extgl_InitOpenGL1_2(JNIEnv *env, jobject ext_set);
extern void extgl_InitOpenGL1_3(JNIEnv *env, jobject ext_set);
extern void extgl_InitOpenGL1_4(JNIEnv *env, jobject ext_set);
extern void extgl_InitOpenGL1_5(JNIEnv *env, jobject ext_set);

/* extgl_Init the extensions and load all the functions */
bool extgl_Initialize(JNIEnv *env, jobject ext_set)
{
	extgl_error = false;
	extgl_InitOpenGL1_1();
	if (extgl_error)
		return false;

	extgl_InitSupportedExtensions(env, ext_set);

	//extgl_InitEXTNurbsTesselator(env, ext_set);

	/* first load the extensions */
	//	extgl_InitARBFragmentProgram(env, ext_set);
	extgl_InitARBImaging(env, ext_set);
	extgl_InitARBMatrixPalette(env, ext_set);
	extgl_InitARBMultisample(env, ext_set);
	extgl_InitARBMultitexture(env, ext_set);
	extgl_InitARBOcclusionQuery(env, ext_set);
	extgl_InitARBPointParameters(env, ext_set);
	extgl_InitARBProgram(env, ext_set);
	extgl_InitARBShaderObjects(env, ext_set);
	extgl_InitARBTextureCompression(env, ext_set);
	extgl_InitARBTransposeMatrix(env, ext_set);
	extgl_InitARBVertexBlend(env, ext_set);
	extgl_InitARBVertexBufferObject(env, ext_set);
	extgl_InitARBVertexProgram(env, ext_set);
	extgl_InitARBVertexShader(env, ext_set);
	extgl_InitARBWindowPos(env, ext_set);

	extgl_InitEXTBlendFuncSeparate(env, ext_set);
	extgl_InitEXTCompiledVertexArray(env, ext_set);
	//extgl_InitEXTCullVertex(env, ext_set);
	extgl_InitEXTDrawRangeElements(env, ext_set);
	extgl_InitEXTFogCoord(env, ext_set);
	extgl_InitEXTMultiDrawArrays(env, ext_set);
	extgl_InitEXTPointParameters(env, ext_set);
	extgl_InitEXTSecondaryColor(env, ext_set);
	extgl_InitEXTStencilTwoSide(env, ext_set);
	extgl_InitEXTVertexShader(env, ext_set);
	extgl_InitEXTVertexWeighting(env, ext_set);

	//extgl_InitNVElementArray(env, ext_set);
	extgl_InitNVEvaluators(env, ext_set);
	extgl_InitNVFence(env, ext_set);
	extgl_InitNVFragmentProgram(env, ext_set);
	extgl_InitNVHalfFloat(env, ext_set);
	extgl_InitNVOcclusionQuery(env, ext_set);
	extgl_InitNVPixelDataRange(env, ext_set);
	extgl_InitNVPointSprite(env, ext_set);
	extgl_InitNVPrimitiveRestart(env, ext_set);
	extgl_InitNVProgram(env, ext_set);
	extgl_InitNVRegisterCombiners(env, ext_set);
	extgl_InitNVRegisterCombiners2(env, ext_set);
	extgl_InitNVVertexArrayRange(env, ext_set);
	extgl_InitNVVertexProgram(env, ext_set);

	extgl_InitATIDrawBuffers(env, ext_set);
	extgl_InitATIElementArray(env, ext_set);
	extgl_InitATIEnvmapBumpmap(env, ext_set);
	extgl_InitATIFragmentShader(env, ext_set);
	extgl_InitATIMapObjectBuffer(env, ext_set);
	extgl_InitATIPNTriangles(env, ext_set);
	extgl_InitATISeparateStencil(env, ext_set);
	extgl_InitATIVertexArrayObject(env, ext_set);
	extgl_InitATIVertexAttribArrayObject(env, ext_set);
	extgl_InitATIVertexStreams(env, ext_set);

   /* now load core opengl */
	extgl_InitOpenGL1_2(env, ext_set);
	extgl_InitOpenGL1_3(env, ext_set);
	extgl_InitOpenGL1_4(env, ext_set);
	extgl_InitOpenGL1_5(env, ext_set);

	return true;
}

#ifdef _AGL
bool extgl_Open(void) {
	if (opengl_bundle_ref != NULL)
		return true;
	opengl_bundle_ref = loadBundle("\pOpenGL.framework");
	if (opengl_bundle_ref == NULL)
		return false;
	agl_bundle_ref = loadBundle("\pAGL.framework");
	if (agl_bundle_ref == NULL) {
		aglUnloadFramework(opengl_bundle_ref);
		return false;
	}
	return true;
}
#endif

#ifdef _X11
bool extgl_Open()
{
	if (lib_gl_handle != NULL)
		return true;
	lib_gl_handle = dlopen("libGL.so.1", RTLD_LAZY | RTLD_GLOBAL);
	if (lib_gl_handle == NULL) {
		printfDebug("Error loading libGL.so.1: %s\n", dlerror());
		return false;
	}
	glXGetProcAddressARB = (glXGetProcAddressARBPROC) dlsym(lib_gl_handle, "glXGetProcAddressARB");
	if (glXGetProcAddressARB == NULL) {
		extgl_Close();
		return false;
	}
	return true;
}

#endif /* X11 */

#ifdef _WIN32
bool extgl_Open(void)
{
	if (lib_gl_handle != NULL)
		return true;
	// load the dynamic libraries for OpenGL
	lib_gl_handle = LoadLibrary("opengl32.dll");
	if (lib_gl_handle == NULL)
		return false;
	return true;
}
#endif /* WIN32 */

void extgl_Close(void)
{
#ifdef _X11
	dlclose(lib_gl_handle);
	lib_gl_handle = NULL;
#endif
#ifdef _WIN32
	FreeLibrary(lib_gl_handle);
	lib_gl_handle = NULL;
#endif
#ifdef _AGL
	aglUnloadFramework(opengl_bundle_ref);
	aglUnloadFramework(agl_bundle_ref);
	opengl_bundle_ref = NULL;
#endif
}

/* turn on the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn .8064
#pragma warn .8065
#endif /* __BORLANDC__	*/

