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

gluBeginCurvePROC gluBeginCurve = NULL;
gluBeginPolygonPROC gluBeginPolygon = NULL;
gluBeginSurfacePROC gluBeginSurface = NULL;
gluBeginTrimPROC gluBeginTrim = NULL;
gluBuild1DMipmapLevelsPROC gluBuild1DMipmapLevels = NULL;
gluBuild1DMipmapsPROC gluBuild1DMipmaps = NULL;
gluBuild2DMipmapLevelsPROC gluBuild2DMipmapLevels = NULL;
gluBuild2DMipmapsPROC gluBuild2DMipmaps = NULL;
gluBuild3DMipmapLevelsPROC gluBuild3DMipmapLevels = NULL;
gluBuild3DMipmapsPROC gluBuild3DMipmaps = NULL;
gluCheckExtensionPROC gluCheckExtension = NULL;
gluCylinderPROC gluCylinder = NULL;
gluDeleteNurbsRendererPROC gluDeleteNurbsRenderer = NULL;
gluDeleteQuadricPROC gluDeleteQuadric = NULL;
gluDeleteTessPROC gluDeleteTess = NULL;
gluDiskPROC gluDisk = NULL;
gluEndCurvePROC gluEndCurve = NULL;
gluEndPolygonPROC gluEndPolygon = NULL;
gluEndSurfacePROC gluEndSurface = NULL;
gluEndTrimPROC gluEndTrim = NULL;
gluErrorStringPROC gluErrorString = NULL;
gluGetNurbsPropertyPROC gluGetNurbsProperty = NULL;
gluGetStringPROC gluGetString = NULL;
gluGetTessPropertyPROC gluGetTessProperty = NULL;
gluLoadSamplingMatricesPROC gluLoadSamplingMatrices = NULL;
gluLookAtPROC gluLookAt = NULL;
gluNewNurbsRendererPROC gluNewNurbsRenderer = NULL;
gluNewQuadricPROC gluNewQuadric = NULL;
gluNewTessPROC gluNewTess = NULL;
gluNextContourPROC gluNextContour = NULL;
gluNurbsCallbackPROC gluNurbsCallback = NULL;
gluNurbsCallbackDataPROC gluNurbsCallbackData = NULL;
gluNurbsCallbackDataEXTPROC gluNurbsCallbackDataEXT = NULL;
gluNurbsCurvePROC gluNurbsCurve = NULL;
gluNurbsPropertyPROC gluNurbsProperty = NULL;
gluNurbsSurfacePROC gluNurbsSurface = NULL;
gluOrtho2DPROC gluOrtho2D = NULL;
gluPartialDiskPROC gluPartialDisk = NULL;
gluPerspectivePROC gluPerspective = NULL;
gluPickMatrixPROC gluPickMatrix = NULL;
gluProjectPROC gluProject = NULL;
gluPwlCurvePROC gluPwlCurve = NULL;
gluQuadricCallbackPROC gluQuadricCallback = NULL;
gluQuadricDrawStylePROC gluQuadricDrawStyle = NULL;
gluQuadricNormalsPROC gluQuadricNormals = NULL;
gluQuadricOrientationPROC gluQuadricOrientation = NULL;
gluQuadricTexturePROC gluQuadricTexture = NULL;
gluScaleImagePROC gluScaleImage = NULL;
gluSpherePROC gluSphere = NULL;
gluTessBeginContourPROC gluTessBeginContour = NULL;
gluTessBeginPolygonPROC gluTessBeginPolygon = NULL;
gluTessCallbackPROC gluTessCallback = NULL;
gluTessEndContourPROC gluTessEndContour = NULL;
gluTessEndPolygonPROC gluTessEndPolygon = NULL;
gluTessNormalPROC gluTessNormal = NULL;
gluTessPropertyPROC gluTessProperty = NULL;
gluTessVertexPROC gluTessVertex = NULL;
gluUnProjectPROC gluUnProject = NULL;
gluUnProject4PROC gluUnProject4 = NULL;

/* function variables */

glAccumPROC glAccum = NULL;
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
glViewportPROC glViewport = NULL;

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


glDrawRangeElementsPROC glDrawRangeElements = NULL;
glTexImage3DPROC glTexImage3D = NULL;
glTexSubImage3DPROC glTexSubImage3D = NULL;
glCopyTexSubImage3DPROC glCopyTexSubImage3D = NULL;

glBlendColorPROC glBlendColor = NULL;
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
glResetMinmaxPROC glResetMinmax = NULL;

/* 1.3 */

glActiveTexturePROC glActiveTexture = NULL;
glClientActiveTexturePROC glClientActiveTexture = NULL;
glMultiTexCoord1dPROC glMultiTexCoord1d = NULL;
glMultiTexCoord1dvPROC glMultiTexCoord1dv = NULL;
glMultiTexCoord1fPROC glMultiTexCoord1f = NULL;
glMultiTexCoord1fvPROC glMultiTexCoord1fv = NULL;
glMultiTexCoord1iPROC glMultiTexCoord1i = NULL;
glMultiTexCoord1ivPROC glMultiTexCoord1iv = NULL;
glMultiTexCoord1sPROC glMultiTexCoord1s = NULL;
glMultiTexCoord1svPROC glMultiTexCoord1sv = NULL;
glMultiTexCoord2dPROC glMultiTexCoord2d = NULL;
glMultiTexCoord2dvPROC glMultiTexCoord2dv = NULL;
glMultiTexCoord2fPROC glMultiTexCoord2f = NULL;
glMultiTexCoord2fvPROC glMultiTexCoord2fv = NULL;
glMultiTexCoord2iPROC glMultiTexCoord2i = NULL;
glMultiTexCoord2ivPROC glMultiTexCoord2iv = NULL;
glMultiTexCoord2sPROC glMultiTexCoord2s = NULL;
glMultiTexCoord2svPROC glMultiTexCoord2sv = NULL;
glMultiTexCoord3dPROC glMultiTexCoord3d = NULL;
glMultiTexCoord3dvPROC glMultiTexCoord3dv = NULL;
glMultiTexCoord3fPROC glMultiTexCoord3f = NULL;
glMultiTexCoord3fvPROC glMultiTexCoord3fv = NULL;
glMultiTexCoord3iPROC glMultiTexCoord3i = NULL;
glMultiTexCoord3ivPROC glMultiTexCoord3iv = NULL;
glMultiTexCoord3sPROC glMultiTexCoord3s = NULL;
glMultiTexCoord3svPROC glMultiTexCoord3sv = NULL;
glMultiTexCoord4dPROC glMultiTexCoord4d = NULL;
glMultiTexCoord4dvPROC glMultiTexCoord4dv = NULL;
glMultiTexCoord4fPROC glMultiTexCoord4f = NULL;
glMultiTexCoord4fvPROC glMultiTexCoord4fv = NULL;
glMultiTexCoord4iPROC glMultiTexCoord4i = NULL;
glMultiTexCoord4ivPROC glMultiTexCoord4iv = NULL;
glMultiTexCoord4sPROC glMultiTexCoord4s = NULL;
glMultiTexCoord4svPROC glMultiTexCoord4sv = NULL;
glLoadTransposeMatrixfPROC glLoadTransposeMatrixf = NULL;
glLoadTransposeMatrixdPROC glLoadTransposeMatrixd = NULL;
glMultTransposeMatrixfPROC glMultTransposeMatrixf = NULL;
glMultTransposeMatrixdPROC glMultTransposeMatrixd = NULL;
glCompressedTexImage3DPROC glCompressedTexImage3D = NULL;
glCompressedTexImage2DPROC glCompressedTexImage2D = NULL;
glCompressedTexImage1DPROC glCompressedTexImage1D = NULL;
glCompressedTexSubImage3DPROC glCompressedTexSubImage3D = NULL;
glCompressedTexSubImage2DPROC glCompressedTexSubImage2D = NULL;
glCompressedTexSubImage1DPROC glCompressedTexSubImage1D = NULL;
glGetCompressedTexImagePROC glGetCompressedTexImage = NULL;
glSampleCoveragePROC glSampleCoverage = NULL;

/* ARB_multitexture */

glActiveTextureARBPROC glActiveTextureARB = NULL;
glClientActiveTextureARBPROC glClientActiveTextureARB = NULL;
glMultiTexCoord1dARBPROC glMultiTexCoord1dARB = NULL;
glMultiTexCoord1dvARBPROC glMultiTexCoord1dvARB = NULL;
glMultiTexCoord1fARBPROC glMultiTexCoord1fARB = NULL;
glMultiTexCoord1fvARBPROC glMultiTexCoord1fvARB = NULL;
glMultiTexCoord1iARBPROC glMultiTexCoord1iARB = NULL;
glMultiTexCoord1ivARBPROC glMultiTexCoord1ivARB = NULL;
glMultiTexCoord1sARBPROC glMultiTexCoord1sARB = NULL;
glMultiTexCoord1svARBPROC glMultiTexCoord1svARB = NULL;
glMultiTexCoord2dARBPROC glMultiTexCoord2dARB = NULL;
glMultiTexCoord2dvARBPROC glMultiTexCoord2dvARB = NULL;
glMultiTexCoord2fARBPROC glMultiTexCoord2fARB = NULL;
glMultiTexCoord2fvARBPROC glMultiTexCoord2fvARB = NULL;
glMultiTexCoord2iARBPROC glMultiTexCoord2iARB = NULL;
glMultiTexCoord2ivARBPROC glMultiTexCoord2ivARB = NULL;
glMultiTexCoord2sARBPROC glMultiTexCoord2sARB = NULL;
glMultiTexCoord2svARBPROC glMultiTexCoord2svARB = NULL;
glMultiTexCoord3dARBPROC glMultiTexCoord3dARB = NULL;
glMultiTexCoord3dvARBPROC glMultiTexCoord3dvARB = NULL;
glMultiTexCoord3fARBPROC glMultiTexCoord3fARB = NULL;
glMultiTexCoord3fvARBPROC glMultiTexCoord3fvARB = NULL;
glMultiTexCoord3iARBPROC glMultiTexCoord3iARB = NULL;
glMultiTexCoord3ivARBPROC glMultiTexCoord3ivARB = NULL;
glMultiTexCoord3sARBPROC glMultiTexCoord3sARB = NULL;
glMultiTexCoord3svARBPROC glMultiTexCoord3svARB = NULL;
glMultiTexCoord4dARBPROC glMultiTexCoord4dARB = NULL;
glMultiTexCoord4dvARBPROC glMultiTexCoord4dvARB = NULL;
glMultiTexCoord4fARBPROC glMultiTexCoord4fARB = NULL;
glMultiTexCoord4fvARBPROC glMultiTexCoord4fvARB = NULL;
glMultiTexCoord4iARBPROC glMultiTexCoord4iARB = NULL;
glMultiTexCoord4ivARBPROC glMultiTexCoord4ivARB = NULL;
glMultiTexCoord4sARBPROC glMultiTexCoord4sARB = NULL;
glMultiTexCoord4svARBPROC glMultiTexCoord4svARB = NULL;

/* ARB_transpose_matrix */

glLoadTransposeMatrixfARBPROC glLoadTransposeMatrixfARB = NULL;
glLoadTransposeMatrixdARBPROC glLoadTransposeMatrixdARB = NULL;
glMultTransposeMatrixfARBPROC glMultTransposeMatrixfARB = NULL;
glMultTransposeMatrixdARBPROC glMultTransposeMatrixdARB = NULL;

/* ARB_texture_compression */

glCompressedTexImage3DARBPROC glCompressedTexImage3DARB = NULL;
glCompressedTexImage2DARBPROC glCompressedTexImage2DARB = NULL;
glCompressedTexImage1DARBPROC glCompressedTexImage1DARB = NULL; 
glCompressedTexSubImage3DARBPROC glCompressedTexSubImage3DARB = NULL;
glCompressedTexSubImage2DARBPROC glCompressedTexSubImage2DARB = NULL;
glCompressedTexSubImage1DARBPROC glCompressedTexSubImage1DARB = NULL;
glGetCompressedTexImageARBPROC glGetCompressedTexImageARB = NULL;

/* EXT_secondary_color */

glSecondaryColor3bEXTPROC glSecondaryColor3bEXT = NULL;
glSecondaryColor3bvEXTPROC glSecondaryColor3bvEXT = NULL;
glSecondaryColor3dEXTPROC glSecondaryColor3dEXT = NULL;
glSecondaryColor3dvEXTPROC glSecondaryColor3dvEXT = NULL;
glSecondaryColor3fEXTPROC glSecondaryColor3fEXT = NULL;
glSecondaryColor3fvEXTPROC glSecondaryColor3fvEXT = NULL;
glSecondaryColor3iEXTPROC glSecondaryColor3iEXT = NULL;
glSecondaryColor3ivEXTPROC glSecondaryColor3ivEXT = NULL;
glSecondaryColor3sEXTPROC glSecondaryColor3sEXT = NULL;
glSecondaryColor3svEXTPROC glSecondaryColor3svEXT = NULL;
glSecondaryColor3ubEXTPROC glSecondaryColor3ubEXT = NULL;
glSecondaryColor3ubvEXTPROC glSecondaryColor3ubvEXT = NULL;
glSecondaryColor3uiEXTPROC glSecondaryColor3uiEXT = NULL;
glSecondaryColor3uivEXTPROC glSecondaryColor3uivEXT = NULL;
glSecondaryColor3usEXTPROC glSecondaryColor3usEXT = NULL;
glSecondaryColor3usvEXTPROC glSecondaryColor3usvEXT = NULL;
glSecondaryColorPointerEXTPROC glSecondaryColorPointerEXT = NULL;

/* EXT_compiled_vertex_array */

glLockArraysEXTPROC glLockArraysEXT = NULL;
glUnlockArraysEXTPROC glUnlockArraysEXT = NULL;

/* EXT_fog_coord */

glFogCoordfEXTPROC glFogCoordfEXT = NULL;
glFogCoordfvEXTPROC glFogCoordfvEXT = NULL;
glFogCoorddEXTPROC glFogCoorddEXT = NULL;
glFogCoorddvEXTPROC glFogCoorddvEXT = NULL;
glFogCoordPointerEXTPROC glFogCoordPointerEXT = NULL;

/* NV_vertex_array_range */

glFlushVertexArrayRangeNVPROC glFlushVertexArrayRangeNV = NULL;
glVertexArrayRangeNVPROC glVertexArrayRangeNV = NULL;

#ifdef _WIN32
wglAllocateMemoryNVPROC wglAllocateMemoryNV = NULL;
wglFreeMemoryNVPROC wglFreeMemoryNV = NULL;
#endif /* WIN32 */

#ifdef _X11
glXAllocateMemoryNVPROC glXAllocateMemoryNV = NULL;
glXFreeMemoryNVPROC glXFreeMemoryNV = NULL;
#endif /* X11 */

#ifdef _AGL
// TODO: find the OSX equivalent of these functions
#endif /* _AGL */

/* EXT_point_parameters */

glPointParameterfEXTPROC glPointParameterfEXT = NULL;
glPointParameterfvEXTPROC glPointParameterfvEXT = NULL;

/* NV_register_combiners */

glCombinerParameterfvNVPROC glCombinerParameterfvNV = NULL;
glCombinerParameterfNVPROC  glCombinerParameterfNV = NULL;
glCombinerParameterivNVPROC glCombinerParameterivNV = NULL;
glCombinerParameteriNVPROC glCombinerParameteriNV = NULL;
glCombinerInputNVPROC glCombinerInputNV = NULL;
glCombinerOutputNVPROC glCombinerOutputNV = NULL;
glFinalCombinerInputNVPROC glFinalCombinerInputNV = NULL;
glGetCombinerInputParameterfvNVPROC glGetCombinerInputParameterfvNV = NULL;
glGetCombinerInputParameterivNVPROC glGetCombinerInputParameterivNV = NULL;
glGetCombinerOutputParameterfvNVPROC glGetCombinerOutputParameterfvNV = NULL;
glGetCombinerOutputParameterivNVPROC glGetCombinerOutputParameterivNV = NULL;
glGetFinalCombinerInputParameterfvNVPROC glGetFinalCombinerInputParameterfvNV = NULL;
glGetFinalCombinerInputParameterivNVPROC glGetFinalCombinerInputParameterivNV = NULL;

/* ARB_vertex_buffer_object */

glBindBufferARBPROC glBindBufferARB = NULL;
glDeleteBuffersARBPROC glDeleteBuffersARB = NULL;
glGenBuffersARBPROC glGenBuffersARB = NULL;
glIsBufferARBPROC glIsBufferARB = NULL;
glBufferDataARBPROC glBufferDataARB = NULL;
glBufferSubDataARBPROC glBufferSubDataARB = NULL;
glGetBufferSubDataARBPROC glGetBufferSubDataARB = NULL;
glMapBufferARBPROC glMapBufferARB = NULL;
glUnmapBufferARBPROC glUnmapBufferARB = NULL;
glGetBufferParameterivARBPROC glGetBufferParameterivARB = NULL;
glGetBufferPointervARBPROC glGetBufferPointervARB = NULL;
/* ARB_vertex_buffer_object */

glSampleCoverageARBPROC glSampleCoverageARB = NULL;

/* EXT_vertex_weighting */

glVertexWeightfEXTPROC glVertexWeightfEXT = NULL;
glVertexWeightfvEXTPROC glVertexWeightfvEXT = NULL;
glVertexWeightPointerEXTPROC glVertexWeightPointerEXT = NULL;

/* NV_vertex_program */

glBindProgramNVPROC glBindProgramNV = NULL;
glDeleteProgramsNVPROC glDeleteProgramsNV = NULL;
glExecuteProgramNVPROC glExecuteProgramNV = NULL;
glGenProgramsNVPROC glGenProgramsNV = NULL;
glAreProgramsResidentNVPROC glAreProgramsResidentNV = NULL;
glRequestResidentProgramsNVPROC glRequestResidentProgramsNV = NULL;
glGetProgramParameterfvNVPROC glGetProgramParameterfvNV = NULL;
glGetProgramParameterdvNVPROC glGetProgramParameterdvNV = NULL;
glGetProgramivNVPROC glGetProgramivNV = NULL;
glGetProgramStringNVPROC glGetProgramStringNV = NULL;
glGetTrackMatrixivNVPROC glGetTrackMatrixivNV = NULL;
glGetVertexAttribdvNVPROC glGetVertexAttribdvNV = NULL;
glGetVertexAttribfvNVPROC glGetVertexAttribfvNV = NULL;
glGetVertexAttribivNVPROC glGetVertexAttribivNV = NULL;
glGetVertexAttribPointervNVPROC glGetVertexAttribPointervNV = NULL;
glIsProgramNVPROC glIsProgramNV = NULL;
glLoadProgramNVPROC glLoadProgramNV = NULL;
glProgramParameter4fNVPROC glProgramParameter4fNV = NULL;
glProgramParameter4dNVPROC glProgramParameter4dNV = NULL;
glProgramParameter4dvNVPROC glProgramParameter4dvNV = NULL;
glProgramParameter4fvNVPROC glProgramParameter4fvNV = NULL;
glProgramParameters4dvNVPROC glProgramParameters4dvNV = NULL;
glProgramParameters4fvNVPROC glProgramParameters4fvNV = NULL;
glTrackMatrixNVPROC glTrackMatrixNV = NULL;
glVertexAttribPointerNVPROC glVertexAttribPointerNV = NULL;
glVertexAttrib1sNVPROC glVertexAttrib1sNV = NULL;
glVertexAttrib1fNVPROC glVertexAttrib1fNV = NULL;
glVertexAttrib1dNVPROC glVertexAttrib1dNV = NULL;
glVertexAttrib2sNVPROC glVertexAttrib2sNV = NULL;
glVertexAttrib2fNVPROC glVertexAttrib2fNV = NULL;
glVertexAttrib2dNVPROC glVertexAttrib2dNV = NULL;
glVertexAttrib3sNVPROC glVertexAttrib3sNV = NULL;
glVertexAttrib3fNVPROC glVertexAttrib3fNV = NULL;
glVertexAttrib3dNVPROC glVertexAttrib3dNV = NULL;
glVertexAttrib4sNVPROC glVertexAttrib4sNV = NULL;
glVertexAttrib4fNVPROC glVertexAttrib4fNV = NULL;
glVertexAttrib4dNVPROC glVertexAttrib4dNV = NULL;
glVertexAttrib4ubNVPROC glVertexAttrib4ubNV = NULL;
glVertexAttrib1svNVPROC glVertexAttrib1svNV = NULL;
glVertexAttrib1fvNVPROC glVertexAttrib1fvNV = NULL;
glVertexAttrib1dvNVPROC glVertexAttrib1dvNV = NULL;
glVertexAttrib2svNVPROC glVertexAttrib2svNV = NULL;
glVertexAttrib2fvNVPROC glVertexAttrib2fvNV = NULL;
glVertexAttrib2dvNVPROC glVertexAttrib2dvNV = NULL;
glVertexAttrib3svNVPROC glVertexAttrib3svNV = NULL;
glVertexAttrib3fvNVPROC glVertexAttrib3fvNV = NULL;
glVertexAttrib3dvNVPROC glVertexAttrib3dvNV = NULL;
glVertexAttrib4svNVPROC glVertexAttrib4svNV = NULL;
glVertexAttrib4fvNVPROC glVertexAttrib4fvNV = NULL;
glVertexAttrib4dvNVPROC glVertexAttrib4dvNV = NULL;
glVertexAttrib4ubvNVPROC glVertexAttrib4ubvNV = NULL;
glVertexAttribs1svNVPROC glVertexAttribs1svNV = NULL;
glVertexAttribs1fvNVPROC glVertexAttribs1fvNV = NULL;
glVertexAttribs1dvNVPROC glVertexAttribs1dvNV = NULL;
glVertexAttribs2svNVPROC glVertexAttribs2svNV = NULL;
glVertexAttribs2fvNVPROC glVertexAttribs2fvNV = NULL;
glVertexAttribs2dvNVPROC glVertexAttribs2dvNV = NULL;
glVertexAttribs3svNVPROC glVertexAttribs3svNV = NULL;
glVertexAttribs3fvNVPROC glVertexAttribs3fvNV = NULL;
glVertexAttribs3dvNVPROC glVertexAttribs3dvNV = NULL;
glVertexAttribs4svNVPROC glVertexAttribs4svNV = NULL;
glVertexAttribs4fvNVPROC glVertexAttribs4fvNV = NULL;
glVertexAttribs4dvNVPROC glVertexAttribs4dvNV = NULL;
glVertexAttribs4ubvNVPROC glVertexAttribs4ubvNV = NULL;

/* NV_fence */

glGenFencesNVPROC glGenFencesNV = NULL;
glDeleteFencesNVPROC glDeleteFencesNV = NULL;
glSetFenceNVPROC glSetFenceNV = NULL;
glTestFenceNVPROC glTestFenceNV = NULL;
glFinishFenceNVPROC glFinishFenceNV = NULL;
glIsFenceNVPROC glIsFenceNV = NULL;
glGetFenceivNVPROC glGetFenceivNV = NULL;

/* NV_register_combiners2 */

glCombinerStageParameterfvNVPROC glCombinerStageParameterfvNV = NULL;
glGetCombinerStageParameterfvNVPROC glGetCombinerStageParameterfvNV = NULL;

/* NV_evaluators */

glMapControlPointsNVPROC glMapControlPointsNV = NULL;
glMapParameterivNVPROC glMapParameterivNV = NULL;
glMapParameterfvNVPROC glMapParameterfvNV = NULL;
glGetMapControlPointsNVPROC glGetMapControlPointsNV = NULL;
glGetMapParameterivNVPROC glGetMapParameterivNV = NULL;
glGetMapParameterfvNVPROC glGetMapParameterfvNV = NULL;
glGetMapAttribParameterivNVPROC glGetMapAttribParameterivNV = NULL;
glGetMapAttribParameterfvNVPROC glGetMapAttribParameterfvNV = NULL;
glEvalMapsNVPROC glEvalMapsNV = NULL;

/* ATI_separate_stencil */

glStencilOpSeparateATIPROC glStencilOpSeparateATI = NULL;
glStencilFuncSeparateATIPROC glStencilFuncSeparateATI = NULL;

/* ATI_pn_triangles */

glPNTrianglesiATIPROC glPNTrianglesiATI = NULL;
glPNTrianglesfATIPROC glPNTrianglesfATI = NULL;

/* ARB_point_parameters */

glPointParameterfARBPROC glPointParameterfARB = NULL;
glPointParameterfvARBPROC glPointParameterfvARB = NULL;

/* ARB_vertex_blend */

glWeightbvARBPROC glWeightbvARB = NULL;
glWeightsvARBPROC glWeightsvARB = NULL;
glWeightivARBPROC glWeightivARB = NULL;
glWeightfvARBPROC glWeightfvARB = NULL;
glWeightdvARBPROC glWeightdvARB = NULL;
glWeightubvARBPROC glWeightubvARB = NULL;
glWeightusvARBPROC glWeightusvARB = NULL;
glWeightuivARBPROC glWeightuivARB = NULL;
glWeightPointerARBPROC glWeightPointerARB = NULL;
glVertexBlendARBPROC glVertexBlendARB = NULL;

/* EXT_multi_draw_arrays */

glMultiDrawArraysEXTPROC glMultiDrawArraysEXT = NULL;
glMultiDrawElementsEXTPROC glMultiDrawElementsEXT = NULL;

/* ARB_matrix_palette */

glCurrentPaletteMatrixARBPROC glCurrentPaletteMatrixARB = NULL;
glMatrixIndexubvARBPROC glMatrixIndexubvARB = NULL;
glMatrixIndexusvARBPROC glMatrixIndexusvARB = NULL;
glMatrixIndexuivARBPROC glMatrixIndexuivARB = NULL;
glMatrixIndexPointerARBPROC glMatrixIndexPointerARB = NULL;

/* EXT_vertex_shader */

glBeginVertexShaderEXTPROC glBeginVertexShaderEXT = NULL;
glEndVertexShaderEXTPROC glEndVertexShaderEXT = NULL;
glBindVertexShaderEXTPROC glBindVertexShaderEXT = NULL;
glGenVertexShadersEXTPROC glGenVertexShadersEXT = NULL;
glDeleteVertexShaderEXTPROC glDeleteVertexShaderEXT = NULL;
glShaderOp1EXTPROC glShaderOp1EXT = NULL;
glShaderOp2EXTPROC glShaderOp2EXT = NULL;
glShaderOp3EXTPROC glShaderOp3EXT = NULL;
glSwizzleEXTPROC glSwizzleEXT = NULL;
glWriteMaskEXTPROC glWriteMaskEXT = NULL;
glInsertComponentEXTPROC glInsertComponentEXT = NULL;
glExtractComponentEXTPROC glExtractComponentEXT = NULL;
glGenSymbolsEXTPROC glGenSymbolsEXT = NULL;
glSetInvariantEXTPROC glSetInvariantEXT = NULL;
glSetLocalConstantEXTPROC glSetLocalConstantEXT = NULL;
glVariantbvEXTPROC glVariantbvEXT = NULL;
glVariantsvEXTPROC glVariantsvEXT = NULL;
glVariantivEXTPROC glVariantivEXT = NULL;
glVariantfvEXTPROC glVariantfvEXT = NULL;
glVariantdvEXTPROC glVariantdvEXT = NULL;
glVariantubvEXTPROC glVariantubvEXT = NULL;
glVariantusvEXTPROC glVariantusvEXT = NULL;
glVariantuivEXTPROC glVariantuivEXT = NULL;
glVariantPointerEXTPROC glVariantPointerEXT = NULL;
glEnableVariantClientStateEXTPROC glEnableVariantClientStateEXT = NULL;
glDisableVariantClientStateEXTPROC glDisableVariantClientStateEXT = NULL;
glBindLightParameterEXTPROC glBindLightParameterEXT = NULL;
glBindMaterialParameterEXTPROC glBindMaterialParameterEXT = NULL;
glBindTexGenParameterEXTPROC glBindTexGenParameterEXT = NULL;
glBindTextureUnitParameterEXTPROC glBindTextureUnitParameterEXT = NULL;
glBindParameterEXTPROC glBindParameterEXT = NULL;
glIsVariantEnabledEXTPROC glIsVariantEnabledEXT = NULL;
glGetVariantBooleanvEXTPROC glGetVariantBooleanvEXT = NULL;
glGetVariantIntegervEXTPROC glGetVariantIntegervEXT = NULL;
glGetVariantFloatvEXTPROC glGetVariantFloatvEXT = NULL;
glGetVariantPointervEXTPROC glGetVariantPointervEXT = NULL;
glGetInvariantBooleanvEXTPROC glGetInvariantBooleanvEXT = NULL;
glGetInvariantIntegervEXTPROC glGetInvariantIntegervEXT = NULL;
glGetInvariantFloatvEXTPROC glGetInvariantFloatvEXT = NULL;
glGetLocalConstantBooleanvEXTPROC glGetLocalConstantBooleanvEXT = NULL;
glGetLocalConstantIntegervEXTPROC glGetLocalConstantIntegervEXT = NULL;
glGetLocalConstantFloatvEXTPROC glGetLocalConstantFloatvEXT = NULL;

/* ATI_envmap_bumpmap */

glTexBumpParameterivATIPROC glTexBumpParameterivATI = NULL;
glTexBumpParameterfvATIPROC glTexBumpParameterfvATI = NULL;
glGetTexBumpParameterivATIPROC glGetTexBumpParameterivATI = NULL;
glGetTexBumpParameterfvATIPROC glGetTexBumpParameterfvATI = NULL;

/* ATI_fragment_shader */

glGenFragmentShadersATIPROC glGenFragmentShadersATI = NULL;
glBindFragmentShaderATIPROC glBindFragmentShaderATI = NULL;
glDeleteFragmentShaderATIPROC glDeleteFragmentShaderATI = NULL;
glBeginFragmentShaderATIPROC glBeginFragmentShaderATI = NULL;
glEndFragmentShaderATIPROC glEndFragmentShaderATI = NULL;
glPassTexCoordATIPROC glPassTexCoordATI = NULL;
glSampleMapATIPROC glSampleMapATI = NULL;
glColorFragmentOp1ATIPROC glColorFragmentOp1ATI = NULL;
glColorFragmentOp2ATIPROC glColorFragmentOp2ATI = NULL;
glColorFragmentOp3ATIPROC glColorFragmentOp3ATI = NULL;
glAlphaFragmentOp1ATIPROC glAlphaFragmentOp1ATI = NULL;
glAlphaFragmentOp2ATIPROC glAlphaFragmentOp2ATI = NULL;
glAlphaFragmentOp3ATIPROC glAlphaFragmentOp3ATI = NULL;
glSetFragmentShaderConstantATIPROC glSetFragmentShaderConstantATI = NULL;

/* ATI_element_array */

glElementPointerATIPROC glElementPointerATI = NULL;
glDrawElementArrayATIPROC glDrawElementArrayATI = NULL;
glDrawRangeElementArrayATIPROC glDrawRangeElementArrayATI = NULL;

/* ATI_vertex_streams */

glClientActiveVertexStreamATIPROC glClientActiveVertexStreamATI = NULL;
glVertexBlendEnviATIPROC glVertexBlendEnviATI = NULL;
glVertexBlendEnvfATIPROC glVertexBlendEnvfATI = NULL;
glVertexStream1sATIPROC glVertexStream1sATI = NULL;
glVertexStream1svATIPROC glVertexStream1svATI = NULL;
glVertexStream1iATIPROC glVertexStream1iATI = NULL;
glVertexStream1ivATIPROC glVertexStream1ivATI = NULL;
glVertexStream1fATIPROC glVertexStream1fATI = NULL;
glVertexStream1fvATIPROC glVertexStream1fvATI = NULL;
glVertexStream1dATIPROC glVertexStream1dATI = NULL;
glVertexStream2sATIPROC glVertexStream2sATI = NULL;
glVertexStream2svATIPROC glVertexStream2svATI = NULL;
glVertexStream2iATIPROC glVertexStream2iATI = NULL;
glVertexStream2ivATIPROC glVertexStream2ivATI = NULL;
glVertexStream2fATIPROC glVertexStream2fATI = NULL;
glVertexStream2fvATIPROC glVertexStream2fvATI = NULL;
glVertexStream2dATIPROC glVertexStream2dATI = NULL;
glVertexStream2dvATIPROC glVertexStream2dvATI = NULL;
glVertexStream3sATIPROC glVertexStream3sATI = NULL;
glVertexStream3svATIPROC glVertexStream3svATI = NULL;
glVertexStream3iATIPROC glVertexStream3iATI = NULL;
glVertexStream3ivATIPROC glVertexStream3ivATI = NULL;
glVertexStream3fATIPROC glVertexStream3fATI = NULL;
glVertexStream3fvATIPROC glVertexStream3fvATI = NULL;
glVertexStream3dATIPROC glVertexStream3dATI = NULL;
glVertexStream3dvATIPROC glVertexStream3dvATI = NULL;
glVertexStream4sATIPROC glVertexStream4sATI = NULL;
glVertexStream4svATIPROC glVertexStream4svATI = NULL;
glVertexStream4iATIPROC glVertexStream4iATI = NULL;
glVertexStream4ivATIPROC glVertexStream4ivATI = NULL;
glVertexStream4fATIPROC glVertexStream4fATI = NULL;
glVertexStream4fvATIPROC glVertexStream4fvATI = NULL;
glVertexStream4dATIPROC glVertexStream4dATI = NULL;
glVertexStream4dvATIPROC glVertexStream4dvATI = NULL;
glNormalStream3bATIPROC glNormalStream3bATI = NULL;
glNormalStream3bvATIPROC glNormalStream3bvATI = NULL;
glNormalStream3sATIPROC glNormalStream3sATI = NULL;
glNormalStream3svATIPROC glNormalStream3svATI = NULL;
glNormalStream3iATIPROC glNormalStream3iATI = NULL;
glNormalStream3ivATIPROC glNormalStream3ivATI = NULL;
glNormalStream3fATIPROC glNormalStream3fATI = NULL;
glNormalStream3fvATIPROC glNormalStream3fvATI = NULL;
glNormalStream3dATIPROC glNormalStream3dATI = NULL;
glNormalStream3dvATIPROC glNormalStream3dvATI = NULL;

/* ATI_vertex_array_object */

glNewObjectBufferATIPROC glNewObjectBufferATI = NULL;
glIsObjectBufferATIPROC glIsObjectBufferATI = NULL;
glUpdateObjectBufferATIPROC glUpdateObjectBufferATI = NULL;
glGetObjectBufferfvATIPROC glGetObjectBufferfvATI = NULL;
glGetObjectBufferivATIPROC glGetObjectBufferivATI = NULL;
glFreeObjectBufferATIPROC glFreeObjectBufferATI = NULL;
glArrayObjectATIPROC glArrayObjectATI = NULL;
glGetArrayObjectfvATIPROC glGetArrayObjectfvATI = NULL;
glGetArrayObjectivATIPROC glGetArrayObjectivATI = NULL;
glVariantArrayObjectATIPROC glVariantArrayObjectATI = NULL;
glGetVariantArrayObjectfvATIPROC glGetVariantArrayObjectfvATI = NULL;
glGetVariantArrayObjectivATIPROC glGetVariantArrayObjectivATI = NULL;

/* NV_occlusion_query */

glGenOcclusionQueriesNVPROC glGenOcclusionQueriesNV = NULL;
glDeleteOcclusionQueriesNVPROC glDeleteOcclusionQueriesNV = NULL;
glIsOcclusionQueryNVPROC glIsOcclusionQueryNV = NULL;
glBeginOcclusionQueryNVPROC glBeginOcclusionQueryNV = NULL;
glEndOcclusionQueryNVPROC glEndOcclusionQueryNV = NULL;
glGetOcclusionQueryivNVPROC glGetOcclusionQueryivNV = NULL;
glGetOcclusionQueryuivNVPROC glGetOcclusionQueryuivNV = NULL;

/* NV_point_sprite */

glPointParameteriNVPROC glPointParameteriNV = NULL;
glPointParameterivNVPROC glPointParameterivNV = NULL;

/* ARB_window_pos */

glWindowPos2dARBPROC glWindowPos2dARB = NULL;
glWindowPos2fARBPROC glWindowPos2fARB = NULL;
glWindowPos2iARBPROC glWindowPos2iARB = NULL;
glWindowPos2sARBPROC glWindowPos2sARB = NULL;
glWindowPos2dvARBPROC glWindowPos2dvARB = NULL;
glWindowPos2fvARBPROC glWindowPos2fvARB = NULL;
glWindowPos2ivARBPROC glWindowPos2ivARB = NULL;
glWindowPos2svARBPROC glWindowPos2svARB = NULL;
glWindowPos3dARBPROC glWindowPos3dARB = NULL;
glWindowPos3fARBPROC glWindowPos3fARB = NULL;
glWindowPos3iARBPROC glWindowPos3iARB = NULL;
glWindowPos3sARBPROC glWindowPos3sARB = NULL;
glWindowPos3dvARBPROC glWindowPos3dvARB = NULL;
glWindowPos3fvARBPROC glWindowPos3fvARB = NULL;
glWindowPos3ivARBPROC glWindowPos3ivARB = NULL;
glWindowPos3svARBPROC glWindowPos3svARB = NULL;

/* EXT_draw_range_elements */

glDrawRangeElementsEXTPROC glDrawRangeElementsEXT = NULL;

/* EXT_stencil_two_side */

glActiveStencilFaceEXTPROC glActiveStencilFaceEXT = NULL;

/* ARB_vertex_program */

glVertexAttrib1sARBPROC glVertexAttrib1sARB = NULL;
glVertexAttrib1fARBPROC glVertexAttrib1fARB = NULL;
glVertexAttrib1dARBPROC glVertexAttrib1dARB = NULL;
glVertexAttrib2sARBPROC glVertexAttrib2sARB = NULL;
glVertexAttrib2fARBPROC glVertexAttrib2fARB = NULL;
glVertexAttrib2dARBPROC glVertexAttrib2dARB = NULL;
glVertexAttrib3sARBPROC glVertexAttrib3sARB = NULL;
glVertexAttrib3fARBPROC glVertexAttrib3fARB = NULL;
glVertexAttrib3dARBPROC glVertexAttrib3dARB = NULL;
glVertexAttrib4sARBPROC glVertexAttrib4sARB = NULL;
glVertexAttrib4fARBPROC glVertexAttrib4fARB = NULL;
glVertexAttrib4dARBPROC glVertexAttrib4dARB = NULL;
glVertexAttrib4NubARBPROC glVertexAttrib4NubARB = NULL;
glVertexAttrib1svARBPROC glVertexAttrib1svARB = NULL;
glVertexAttrib1fvARBPROC glVertexAttrib1fvARB = NULL;
glVertexAttrib1dvARBPROC glVertexAttrib1dvARB = NULL;
glVertexAttrib2svARBPROC glVertexAttrib2svARB = NULL;
glVertexAttrib2fvARBPROC glVertexAttrib2fvARB = NULL;
glVertexAttrib2dvARBPROC glVertexAttrib2dvARB = NULL;
glVertexAttrib3svARBPROC glVertexAttrib3svARB = NULL;
glVertexAttrib3fvARBPROC glVertexAttrib3fvARB = NULL;
glVertexAttrib3dvARBPROC glVertexAttrib3dvARB = NULL;
glVertexAttrib4bvARBPROC glVertexAttrib4bvARB = NULL;
glVertexAttrib4svARBPROC glVertexAttrib4svARB = NULL;
glVertexAttrib4ivARBPROC glVertexAttrib4ivARB = NULL;
glVertexAttrib4ubvARBPROC glVertexAttrib4ubvARB = NULL;
glVertexAttrib4usvARBPROC glVertexAttrib4usvARB = NULL;
glVertexAttrib4uivARBPROC glVertexAttrib4uivARB = NULL;
glVertexAttrib4fvARBPROC glVertexAttrib4fvARB = NULL;
glVertexAttrib4dvARBPROC glVertexAttrib4dvARB = NULL;
glVertexAttrib4NbvARBPROC glVertexAttrib4NbvARB = NULL;
glVertexAttrib4NsvARBPROC glVertexAttrib4NsvARB = NULL;
glVertexAttrib4NivARBPROC glVertexAttrib4NivARB = NULL;
glVertexAttrib4NubvARBPROC glVertexAttrib4NubvARB = NULL;
glVertexAttrib4NusvARBPROC glVertexAttrib4NusvARB = NULL;
glVertexAttrib4NuivARBPROC glVertexAttrib4NuivARB = NULL;
glVertexAttribPointerARBPROC glVertexAttribPointerARB = NULL;
glEnableVertexAttribArrayARBPROC glEnableVertexAttribArrayARB = NULL;
glDisableVertexAttribArrayARBPROC glDisableVertexAttribArrayARB = NULL;
glProgramStringARBPROC glProgramStringARB = NULL;
glBindProgramARBPROC glBindProgramARB = NULL;
glDeleteProgramsARBPROC glDeleteProgramsARB = NULL;
glGenProgramsARBPROC glGenProgramsARB = NULL;
glProgramEnvParameter4dARBPROC glProgramEnvParameter4dARB = NULL;
glProgramEnvParameter4dvARBPROC glProgramEnvParameter4dvARB = NULL;
glProgramEnvParameter4fARBPROC glProgramEnvParameter4fARB = NULL;
glProgramEnvParameter4fvARBPROC glProgramEnvParameter4fvARB = NULL;
/*glProgramLocalParameter4dARBPROC glProgramLocalParameter4dARB = NULL;
glProgramLocalParameter4dvARBPROC glProgramLocalParameter4dvARB = NULL;
glProgramLocalParameter4fARBPROC glProgramLocalParameter4fARB = NULL;
glProgramLocalParameter4fvARBPROC glProgramLocalParameter4fvARB = NULL;*/
glGetProgramEnvParameterdvARBPROC glGetProgramEnvParameterdvARB = NULL;
glGetProgramEnvParameterfvARBPROC glGetProgramEnvParameterfvARB = NULL;
/*glGetProgramLocalParameterdvARBPROC glGetProgramLocalParameterdvARB = NULL;
glGetProgramLocalParameterfvARBPROC glGetProgramLocalParameterfvARB = NULL;*/
glGetProgramivARBPROC glGetProgramivARB = NULL;
glGetProgramStringARBPROC glGetProgramStringARB = NULL;
glGetVertexAttribdvARBPROC glGetVertexAttribdvARB = NULL;
glGetVertexAttribfvARBPROC glGetVertexAttribfvARB = NULL;
glGetVertexAttribivARBPROC glGetVertexAttribivARB = NULL;
glGetVertexAttribPointervARBPROC glGetVertexAttribPointervARB = NULL;
glIsProgramARBPROC glIsProgramARB = NULL;

/* EXT_cull_vertex */

glCullParameterfvEXTPROC glCullParameterfvEXT = NULL;
glCullParameterdvEXTPROC glCullParameterdvEXT = NULL;

//glBlendFuncSeparateEXTPROC glBlendFuncSeparateEXT = NULL;
//glBlendFuncSeparateINGRPROC glBlendFuncSeparateINGR = NULL;

glFogCoordfPROC glFogCoordf = NULL;
glFogCoordfvPROC glFogCoordfv = NULL;
glFogCoorddPROC glFogCoordd = NULL;
glFogCoorddvPROC glFogCoorddv = NULL;
glFogCoordPointerPROC glFogCoordPointer = NULL;
glMultiDrawArraysPROC glMultiDrawArrays = NULL;
glMultiDrawElementsPROC glMultiDrawElements = NULL;
glPointParameterfPROC glPointParameterf = NULL;
glPointParameterfvPROC glPointParameterfv = NULL;
glSecondaryColor3bPROC glSecondaryColor3b = NULL;
glSecondaryColor3bvPROC glSecondaryColor3bv = NULL;
glSecondaryColor3dPROC glSecondaryColor3d = NULL;
glSecondaryColor3dvPROC glSecondaryColor3dv = NULL;
glSecondaryColor3fPROC glSecondaryColor3f = NULL;
glSecondaryColor3fvPROC glSecondaryColor3fv = NULL;
glSecondaryColor3iPROC glSecondaryColor3i = NULL;
glSecondaryColor3ivPROC glSecondaryColor3iv = NULL;
glSecondaryColor3sPROC glSecondaryColor3s = NULL;
glSecondaryColor3svPROC glSecondaryColor3sv = NULL;
glSecondaryColor3ubPROC glSecondaryColor3ub = NULL;
glSecondaryColor3ubvPROC glSecondaryColor3ubv = NULL;
glSecondaryColor3uiPROC glSecondaryColor3ui = NULL;
glSecondaryColor3uivPROC glSecondaryColor3uiv = NULL;
glSecondaryColor3usPROC glSecondaryColor3us = NULL;
glSecondaryColor3usvPROC glSecondaryColor3usv = NULL;
glSecondaryColorPointerPROC glSecondaryColorPointer = NULL;
glBlendFuncSeparatePROC glBlendFuncSeparate = NULL;
glWindowPos2dPROC glWindowPos2d = NULL;
glWindowPos2fPROC glWindowPos2f = NULL;
glWindowPos2iPROC glWindowPos2i = NULL;
glWindowPos2sPROC glWindowPos2s = NULL;
glWindowPos2dvPROC glWindowPos2dv = NULL;
glWindowPos2fvPROC glWindowPos2fv = NULL;
glWindowPos2ivPROC glWindowPos2iv = NULL;
glWindowPos2svPROC glWindowPos2sv = NULL;
glWindowPos3dPROC glWindowPos3d = NULL;
glWindowPos3fPROC glWindowPos3f = NULL;
glWindowPos3iPROC glWindowPos3i = NULL;
glWindowPos3sPROC glWindowPos3s = NULL;
glWindowPos3dvPROC glWindowPos3dv = NULL;
glWindowPos3fvPROC glWindowPos3fv = NULL;
glWindowPos3ivPROC glWindowPos3iv = NULL;
glWindowPos3svPROC glWindowPos3sv = NULL;

glBlendFuncSeparateEXTPROC glBlendFuncSeparateEXT = NULL;

glElementPointerNVPROC glElementPointerNV = NULL;
glDrawElementArrayNVPROC glDrawElementArrayNV = NULL;
glDrawRangeElementArrayNVPROC glDrawRangeElementArrayNV = NULL;
glMultiDrawElementArrayNVPROC glMultiDrawElementArrayNV = NULL;
glMultiDrawRangeElementArrayNVPROC glMultiDrawRangeElementArrayNV = NULL;

glProgramNamedParameter4fNVPROC glProgramNamedParameter4fNV = NULL;
glProgramNamedParameter4dNVPROC glProgramNamedParameter4dNV = NULL;
glProgramNamedParameter4fvNVPROC glProgramNamedParameter4fvNV = NULL;
glProgramNamedParameter4dvNVPROC glProgramNamedParameter4dvNV = NULL;
glGetProgramNamedParameterfvNVPROC glGetProgramNamedParameterfvNV = NULL;
glGetProgramNamedParameterdvNVPROC glGetProgramNamedParameterdvNV = NULL;
glProgramLocalParameter4dNVPROC glProgramLocalParameter4dNV = NULL;
glProgramLocalParameter4dvNVPROC glProgramLocalParameter4dvNV = NULL;
glProgramLocalParameter4fNVPROC glProgramLocalParameter4fNV = NULL;
glProgramLocalParameter4fvNVPROC glProgramLocalParameter4fvNV = NULL;
glGetProgramLocalParameterdvNVPROC glGetProgramLocalParameterdvNV = NULL;
glGetProgramLocalParameterfvNVPROC glGetProgramLocalParameterfvNV = NULL;
glProgramLocalParameter4dARBPROC glProgramLocalParameter4dARB = NULL;
glProgramLocalParameter4dvARBPROC glProgramLocalParameter4dvARB = NULL;
glProgramLocalParameter4fARBPROC glProgramLocalParameter4fARB = NULL;
glProgramLocalParameter4fvARBPROC glProgramLocalParameter4fvARB = NULL;
glGetProgramLocalParameterdvARBPROC glGetProgramLocalParameterdvARB = NULL;
glGetProgramLocalParameterfvARBPROC glGetProgramLocalParameterfvARB = NULL;

glPrimitiveRestartNVPROC glPrimitiveRestartNV = NULL;
glPrimitiveRestartIndexNVPROC glPrimitiveRestartIndexNV = NULL;

static bool extgl_error = false;

struct ExtensionTypes extgl_Extensions;

#ifdef _WIN32
HMODULE lib_gl_handle = NULL;
HMODULE lib_glu_handle = NULL;
#endif

#ifdef _X11
void * lib_gl_handle = NULL;
void * lib_glu_handle = NULL;
#endif

#ifdef _AGL
CFBundleRef opengl_bundle_ref = NULL;
CFBundleRef agl_bundle_ref = NULL;
#endif

#define EXTGL_SANITY_CHECK(e,h,x) 	if (extgl_error) { \
						extgl_Extensions.x = 0; \
						printf("NOTICE: %s disabled because of missing driver symbols\n", #x); \
						extgl_error = false; \
						removeExtension(e, h, #x); \
					}

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

static void removeExtension(JNIEnv *env, jobject ext_set, const char *ext) {
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

static void *extgl_GetProcAddress(char *name)
{
#ifdef _WIN32
	void *t = wglGetProcAddress(name);
	if (t == NULL)
	{
		t = GetProcAddress(lib_gl_handle, name);
		if (t == NULL)
		{
			t = GetProcAddress(lib_glu_handle, name);
			if (t == NULL) {
				printfDebug("Could not locate symbol %s\n", name);
				extgl_error = true;
			}
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
			t = dlsym(lib_glu_handle, name);
			if (t == NULL) {
				printfDebug("Could not locate symbol %s\n", name);
				extgl_error = true;
			}
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
				insertExtension(env, ext_set, name);
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
static bool WGLQueryExtension(JNIEnv *env, jobject ext_set, const char *name)
{
	const GLubyte *extensions;

	if (wglGetExtensionsStringARB == NULL)
		if (wglGetExtensionsStringEXT == NULL)
			return false;
		else
			extensions = (GLubyte*)wglGetExtensionsStringEXT();
	else
		extensions = (GLubyte*)wglGetExtensionsStringARB(wglGetCurrentDC());
	return QueryExtension(env, ext_set, extensions, name);
}

static void extgl_InitWGLARBBufferRegion(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.WGL_ARB_buffer_region)
		return;
	wglCreateBufferRegionARB = (wglCreateBufferRegionARBPROC) extgl_GetProcAddress("wglCreateBufferRegionARB");
	wglDeleteBufferRegionARB = (wglDeleteBufferRegionARBPROC) extgl_GetProcAddress("wglDeleteBufferRegionARB");
	wglSaveBufferRegionARB = (wglSaveBufferRegionARBPROC) extgl_GetProcAddress("wglSaveBufferRegionARB");
	wglRestoreBufferRegionARB = (wglRestoreBufferRegionARBPROC) extgl_GetProcAddress("wglRestoreBufferRegionARB");
	EXTGL_SANITY_CHECK(env, ext_set, WGL_ARB_buffer_region);
}

static void extgl_InitWGLARBPbuffer(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.WGL_ARB_pbuffer)
		return;
	wglCreatePbufferARB = (wglCreatePbufferARBPROC) extgl_GetProcAddress("wglCreatePbufferARB");
	wglGetPbufferDCARB = (wglGetPbufferDCARBPROC) extgl_GetProcAddress("wglGetPbufferDCARB");
	wglReleasePbufferDCARB = (wglReleasePbufferDCARBPROC) extgl_GetProcAddress("wglReleasePbufferDCARB");
	wglDestroyPbufferARB = (wglDestroyPbufferARBPROC) extgl_GetProcAddress("wglDestroyPbufferARB");
	wglQueryPbufferARB = (wglQueryPbufferARBPROC) extgl_GetProcAddress("wglQueryPbufferARB");
	EXTGL_SANITY_CHECK(env, ext_set, WGL_ARB_pbuffer);
}

static void extgl_InitWGLARBPixelFormat(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.WGL_ARB_pixel_format)
		return;
	wglGetPixelFormatAttribivARB = (wglGetPixelFormatAttribivARBPROC) extgl_GetProcAddress("wglGetPixelFormatAttribivARB");
	wglGetPixelFormatAttribfvARB = (wglGetPixelFormatAttribfvARBPROC) extgl_GetProcAddress("wglGetPixelFormatAttribfvARB");
	wglChoosePixelFormatARB = (wglChoosePixelFormatARBPROC) extgl_GetProcAddress("wglChoosePixelFormatARB");
	EXTGL_SANITY_CHECK(env, ext_set, WGL_ARB_pixel_format);
}

static void extgl_InitWGLARBRenderTexture(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.WGL_ARB_render_texture)
		return;
	wglBindTexImageARB = (wglBindTexImageARBPROC) extgl_GetProcAddress("wglBindTexImageARB");
	wglReleaseTexImageARB = (wglReleaseTexImageARBPROC) extgl_GetProcAddress("wglReleaseTexImageARB");
	wglSetPbufferAttribARB = (wglSetPbufferAttribARBPROC) extgl_GetProcAddress("wglSetPbufferAttribARB");
	EXTGL_SANITY_CHECK(env, ext_set, WGL_ARB_render_texture);
}

static void extgl_InitWGLEXTSwapControl(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.WGL_EXT_swap_control)
		return;
	wglSwapIntervalEXT = (wglSwapIntervalEXTPROC) extgl_GetProcAddress("wglSwapIntervalEXT");
	wglGetSwapIntervalEXT = (wglGetSwapIntervalEXTPROC) extgl_GetProcAddress("wglGetSwapIntervalEXT");
	EXTGL_SANITY_CHECK(env, ext_set, WGL_EXT_swap_control);
}

static void extgl_InitWGLARBMakeCurrentRead(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.WGL_ARB_make_current_read)
		return;
	wglMakeContextCurrentARB = (wglMakeContextCurrentARBPROC) extgl_GetProcAddress("wglMakeContextCurrentARB");
	wglGetCurrentReadDCARB = (wglGetCurrentReadDCARBPROC) extgl_GetProcAddress("wglGetCurrentReadDCARB");
	EXTGL_SANITY_CHECK(env, ext_set, WGL_ARB_make_current_read);
}

static void extgl_InitSupportedWGLExtensions(JNIEnv *env, jobject ext_set)
{
	extgl_Extensions.WGL_ARB_buffer_region = WGLQueryExtension(env, ext_set, "WGL_ARB_buffer_region");
	extgl_Extensions.WGL_ARB_make_current_read = WGLQueryExtension(env, ext_set, "WGL_ARB_make_current_read");
	extgl_Extensions.WGL_ARB_multisample = WGLQueryExtension(env, ext_set, "WGL_ARB_multisample");
	extgl_Extensions.WGL_ARB_pbuffer = WGLQueryExtension(env, ext_set, "WGL_ARB_pbuffer");
	extgl_Extensions.WGL_ARB_pixel_format = WGLQueryExtension(env, ext_set, "WGL_ARB_pixel_format");
	extgl_Extensions.WGL_ARB_render_texture = WGLQueryExtension(env, ext_set, "WGL_ARB_render_texture");
	extgl_Extensions.WGL_EXT_swap_control = WGLQueryExtension(env, ext_set, "WGL_EXT_swap_control");
	extgl_Extensions.WGL_NV_render_depth_texture = WGLQueryExtension(env, ext_set, "WGL_NV_render_depth_texture");
	extgl_Extensions.WGL_NV_render_texture_rectangle = WGLQueryExtension(env, ext_set, "WGL_NV_render_texture_rectangle");
}

static void extgl_InitializeWGL(JNIEnv *env, jobject ext_set)
{
	wglGetExtensionsStringARB = (wglGetExtensionsStringARBPROC) extgl_GetProcAddress("wglGetExtensionsStringARB");
	wglGetExtensionsStringEXT = (wglGetExtensionsStringEXTPROC) extgl_GetProcAddress("wglGetExtensionsStringEXT");
	extgl_Extensions.WGL_ARB_extensions_string = wglGetExtensionsStringARB != NULL;
	extgl_Extensions.WGL_EXT_extensions_string = wglGetExtensionsStringEXT != NULL;
	extgl_error = false;

	extgl_InitSupportedWGLExtensions(env, ext_set);

	extgl_InitWGLARBMakeCurrentRead(env, ext_set);
	extgl_InitWGLEXTSwapControl(env, ext_set);
	extgl_InitWGLARBRenderTexture(env, ext_set);
	extgl_InitWGLARBPixelFormat(env, ext_set);
	extgl_InitWGLARBPbuffer(env, ext_set);
	extgl_InitWGLARBBufferRegion(env, ext_set);
}

#endif /* WIN32 */

/*-----------------------------------------------------*/
/* WGL stuff END*/
/*-----------------------------------------------------*/

/*-----------------------------------------------------*/
/* AGL stuff BEGIN*/
/*-----------------------------------------------------*/
#ifdef _AGL

bool extgl_InitAGL(JNIEnv *env, jobject ext_set)
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
static bool GLXQueryExtension(JNIEnv* env, jobject ext_set, Display *disp, int screen, const char *name)
{
	const GLubyte *exts = (const GLubyte *)glXQueryExtensionsString(disp, screen);
	return QueryExtension(env, ext_set, exts, name);
}
#endif

/** returns true if the extention is available */
static bool GLUQueryExtension(JNIEnv *env, jobject ext_set, const char *name)
{
	return QueryExtension(env, ext_set, gluGetString(GLU_EXTENSIONS), name);
}

/** returns true if the extention is available */
static bool GLQueryExtension(JNIEnv *env, jobject ext_set, const char *name)
{
	return QueryExtension(env, ext_set, glGetString(GL_EXTENSIONS), name);
}

static void extgl_InitARBFragmentProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_fragment_program)
		return;
	glProgramStringARB = (glProgramStringARBPROC) extgl_GetProcAddress("glProgramStringARB");
	glBindProgramARB = (glBindProgramARBPROC) extgl_GetProcAddress("glBindProgramARB");
	glDeleteProgramsARB = (glDeleteProgramsARBPROC) extgl_GetProcAddress("glDeleteProgramsARB");
	glGenProgramsARB = (glGenProgramsARBPROC) extgl_GetProcAddress("glGenProgramsARB");
	glProgramEnvParameter4dARB = (glProgramEnvParameter4dARBPROC) extgl_GetProcAddress("glProgramEnvParameter4dARB");
	glProgramEnvParameter4dvARB = (glProgramEnvParameter4dvARBPROC) extgl_GetProcAddress("glProgramEnvParameter4dvARB");
	glProgramEnvParameter4fARB = (glProgramEnvParameter4fARBPROC) extgl_GetProcAddress("glProgramEnvParameter4fARB");
	glProgramEnvParameter4fvARB = (glProgramEnvParameter4fvARBPROC) extgl_GetProcAddress("glProgramEnvParameter4fvARB");
	glProgramLocalParameter4dARB = (glProgramLocalParameter4dARBPROC) extgl_GetProcAddress("glProgramLocalParameter4dARB");
	glProgramLocalParameter4dvARB = (glProgramLocalParameter4dvARBPROC) extgl_GetProcAddress("glProgramLocalParameter4dvARB");
	glProgramLocalParameter4fARB = (glProgramLocalParameter4fARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fARB");
	glProgramLocalParameter4fvARB = (glProgramLocalParameter4fvARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fvARB");
	glGetProgramEnvParameterdvARB = (glGetProgramEnvParameterdvARBPROC) extgl_GetProcAddress("glGetProgramEnvParameterdvARB");
	glGetProgramEnvParameterfvARB = (glGetProgramEnvParameterfvARBPROC) extgl_GetProcAddress("glGetProgramEnvParameterfvARB");
	glGetProgramLocalParameterdvARB = (glGetProgramLocalParameterdvARBPROC) extgl_GetProcAddress("glGetProgramLocalParameterdvARB");
	glGetProgramLocalParameterfvARB = (glGetProgramLocalParameterfvARBPROC) extgl_GetProcAddress("glGetProgramLocalParameterfvARB");
	glGetProgramivARB = (glGetProgramivARBPROC) extgl_GetProcAddress("glGetProgramivARB");
	glGetProgramStringARB = (glGetProgramStringARBPROC) extgl_GetProcAddress("glGetProgramStringARB");
	glIsProgramARB = (glIsProgramARBPROC) extgl_GetProcAddress("glIsProgramARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_fragment_program)
}

static void extgl_InitNVPrimitiveRestart(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_primitive_restart)
		return;
	glPrimitiveRestartNV = (glPrimitiveRestartNVPROC) extgl_GetProcAddress("glPrimitiveRestartNV");
	glPrimitiveRestartIndexNV = (glPrimitiveRestartIndexNVPROC) extgl_GetProcAddress("glPrimitiveRestartIndexNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_primitive_restart)
}

static void extgl_InitNVFragmentProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_fragment_program)
		return;
	glProgramNamedParameter4fNV = (glProgramNamedParameter4fNVPROC) extgl_GetProcAddress("glProgramNamedParameter4fNV");
	glProgramNamedParameter4dNV = (glProgramNamedParameter4dNVPROC) extgl_GetProcAddress("glProgramNamedParameter4dNV");
	glProgramNamedParameter4fvNV = (glProgramNamedParameter4fvNVPROC) extgl_GetProcAddress("glProgramNamedParameter4fvNV");
	glProgramNamedParameter4dvNV = (glProgramNamedParameter4dvNVPROC) extgl_GetProcAddress("glProgramNamedParameter4dvNV");
	glGetProgramNamedParameterfvNV = (glGetProgramNamedParameterfvNVPROC) extgl_GetProcAddress("glGetProgramNamedParameterfvNV");
	glGetProgramNamedParameterdvNV = (glGetProgramNamedParameterdvNVPROC) extgl_GetProcAddress("glGetProgramNamedParameterdvNV");
	glProgramLocalParameter4dNV = (glProgramLocalParameter4dNVPROC) extgl_GetProcAddress("glProgramLocalParameter4dNV");
	glProgramLocalParameter4dvNV = (glProgramLocalParameter4dvNVPROC) extgl_GetProcAddress("glProgramLocalParameter4dvNV");
	glProgramLocalParameter4fNV = (glProgramLocalParameter4fNVPROC) extgl_GetProcAddress("glProgramLocalParameter4fNV");
	glProgramLocalParameter4fvNV = (glProgramLocalParameter4fvNVPROC) extgl_GetProcAddress("glProgramLocalParameter4fvNV");
	glGetProgramLocalParameterdvNV = (glGetProgramLocalParameterdvNVPROC) extgl_GetProcAddress("glGetProgramLocalParameterdvNV");
	glGetProgramLocalParameterfvNV = (glGetProgramLocalParameterfvNVPROC) extgl_GetProcAddress("glGetProgramLocalParameterfvNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_fragment_program)
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


static void extgl_InitEXTBlendFuncSeparate(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_blend_func_separate)
		return;
	glBlendFuncSeparateEXT = (glBlendFuncSeparateEXTPROC) extgl_GetProcAddress("glBlendFuncSeparateEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_blend_func_separate)
}

static void extgl_InitEXTCullVertex(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_cull_vertex)
		return;
	glCullParameterfvEXT = (glCullParameterfvEXTPROC) extgl_GetProcAddress("glCullParameterfvEXT");
	glCullParameterdvEXT = (glCullParameterdvEXTPROC) extgl_GetProcAddress("glCullParameterdvEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_cull_vertex)
}

static void extgl_InitARBVertexBufferObject(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_vertex_buffer_object)
		return;
	glBindBufferARB = (glBindBufferARBPROC) extgl_GetProcAddress("glBindBufferARB");
	glDeleteBuffersARB = (glDeleteBuffersARBPROC) extgl_GetProcAddress("glDeleteBuffersARB");
	glGenBuffersARB = (glGenBuffersARBPROC) extgl_GetProcAddress("glGenBuffersARB");
	glIsBufferARB = (glIsBufferARBPROC) extgl_GetProcAddress("glIsBufferARB");
	glBufferDataARB = (glBufferDataARBPROC) extgl_GetProcAddress("glBufferDataARB");
	glBufferSubDataARB = (glBufferSubDataARBPROC) extgl_GetProcAddress("glBufferSubDataARB");
	glGetBufferSubDataARB = (glGetBufferSubDataARBPROC) extgl_GetProcAddress("glGetBufferSubDataARB");
	glMapBufferARB = (glMapBufferARBPROC) extgl_GetProcAddress("glMapBufferARB");
	glUnmapBufferARB = (glUnmapBufferARBPROC) extgl_GetProcAddress("glUnmapBufferARB");
	glGetBufferParameterivARB = (glGetBufferParameterivARBPROC) extgl_GetProcAddress("glGetBufferParameterivARB");
	glGetBufferPointervARB = (glGetBufferPointervARBPROC) extgl_GetProcAddress("glGetBufferPointervARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_vertex_buffer_object)
}

static void extgl_InitARBVertexProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_vertex_program)
		return;
	glVertexAttrib1sARB = (glVertexAttrib1sARBPROC) extgl_GetProcAddress("glVertexAttrib1sARB");
	glVertexAttrib1fARB = (glVertexAttrib1fARBPROC) extgl_GetProcAddress("glVertexAttrib1fARB");
	glVertexAttrib1dARB = (glVertexAttrib1dARBPROC) extgl_GetProcAddress("glVertexAttrib1dARB");
	glVertexAttrib2sARB = (glVertexAttrib2sARBPROC) extgl_GetProcAddress("glVertexAttrib2sARB");
	glVertexAttrib2fARB = (glVertexAttrib2fARBPROC) extgl_GetProcAddress("glVertexAttrib2fARB");
	glVertexAttrib2dARB = (glVertexAttrib2dARBPROC) extgl_GetProcAddress("glVertexAttrib2dARB");
	glVertexAttrib3sARB = (glVertexAttrib3sARBPROC) extgl_GetProcAddress("glVertexAttrib3sARB");
	glVertexAttrib3fARB = (glVertexAttrib3fARBPROC) extgl_GetProcAddress("glVertexAttrib3fARB");
	glVertexAttrib3dARB = (glVertexAttrib3dARBPROC) extgl_GetProcAddress("glVertexAttrib3dARB");
	glVertexAttrib4sARB = (glVertexAttrib4sARBPROC) extgl_GetProcAddress("glVertexAttrib4sARB");
	glVertexAttrib4fARB = (glVertexAttrib4fARBPROC) extgl_GetProcAddress("glVertexAttrib4fARB");
	glVertexAttrib4dARB = (glVertexAttrib4dARBPROC) extgl_GetProcAddress("glVertexAttrib4dARB");
	glVertexAttrib4NubARB = (glVertexAttrib4NubARBPROC) extgl_GetProcAddress("glVertexAttrib4NubARB");
	glVertexAttrib1svARB = (glVertexAttrib1svARBPROC) extgl_GetProcAddress("glVertexAttrib1svARB");
	glVertexAttrib1fvARB = (glVertexAttrib1fvARBPROC) extgl_GetProcAddress("glVertexAttrib1fvARB");
	glVertexAttrib1dvARB = (glVertexAttrib1dvARBPROC) extgl_GetProcAddress("glVertexAttrib1dvARB");
	glVertexAttrib2svARB = (glVertexAttrib2svARBPROC) extgl_GetProcAddress("glVertexAttrib2svARB");
	glVertexAttrib2fvARB = (glVertexAttrib2fvARBPROC) extgl_GetProcAddress("glVertexAttrib2fvARB");
	glVertexAttrib2dvARB = (glVertexAttrib2dvARBPROC) extgl_GetProcAddress("glVertexAttrib2dvARB");
	glVertexAttrib3svARB = (glVertexAttrib3svARBPROC) extgl_GetProcAddress("glVertexAttrib3svARB");
	glVertexAttrib3fvARB = (glVertexAttrib3fvARBPROC) extgl_GetProcAddress("glVertexAttrib3fvARB");
	glVertexAttrib3dvARB = (glVertexAttrib3dvARBPROC) extgl_GetProcAddress("glVertexAttrib3dvARB");
	glVertexAttrib4bvARB = (glVertexAttrib4bvARBPROC) extgl_GetProcAddress("glVertexAttrib4bvARB");
	glVertexAttrib4svARB = (glVertexAttrib4svARBPROC) extgl_GetProcAddress("glVertexAttrib4svARB");
	glVertexAttrib4ivARB = (glVertexAttrib4ivARBPROC) extgl_GetProcAddress("glVertexAttrib4ivARB");
	glVertexAttrib4ubvARB = (glVertexAttrib4ubvARBPROC) extgl_GetProcAddress("glVertexAttrib4ubvARB");
	glVertexAttrib4usvARB = (glVertexAttrib4usvARBPROC) extgl_GetProcAddress("glVertexAttrib4usvARB");
	glVertexAttrib4uivARB = (glVertexAttrib4uivARBPROC) extgl_GetProcAddress("glVertexAttrib4uivARB");
	glVertexAttrib4fvARB = (glVertexAttrib4fvARBPROC) extgl_GetProcAddress("glVertexAttrib4fvARB");
	glVertexAttrib4dvARB = (glVertexAttrib4dvARBPROC) extgl_GetProcAddress("glVertexAttrib4dvARB");
	glVertexAttrib4NbvARB = (glVertexAttrib4NbvARBPROC) extgl_GetProcAddress("glVertexAttrib4NbvARB");
	glVertexAttrib4NsvARB = (glVertexAttrib4NsvARBPROC) extgl_GetProcAddress("glVertexAttrib4NsvARB");
	glVertexAttrib4NivARB = (glVertexAttrib4NivARBPROC) extgl_GetProcAddress("glVertexAttrib4NivARB");
	glVertexAttrib4NubvARB = (glVertexAttrib4NubvARBPROC) extgl_GetProcAddress("glVertexAttrib4NubvARB");
	glVertexAttrib4NusvARB = (glVertexAttrib4NusvARBPROC) extgl_GetProcAddress("glVertexAttrib4NusvARB");
	glVertexAttrib4NuivARB = (glVertexAttrib4NuivARBPROC) extgl_GetProcAddress("glVertexAttrib4NuivARB");
	glVertexAttribPointerARB = (glVertexAttribPointerARBPROC) extgl_GetProcAddress("glVertexAttribPointerARB");
	glEnableVertexAttribArrayARB = (glEnableVertexAttribArrayARBPROC) extgl_GetProcAddress("glEnableVertexAttribArrayARB");
	glDisableVertexAttribArrayARB = (glDisableVertexAttribArrayARBPROC) extgl_GetProcAddress("glDisableVertexAttribArrayARB");
	glProgramStringARB = (glProgramStringARBPROC) extgl_GetProcAddress("glProgramStringARB");
	glBindProgramARB = (glBindProgramARBPROC) extgl_GetProcAddress("glBindProgramARB");
	glDeleteProgramsARB = (glDeleteProgramsARBPROC) extgl_GetProcAddress("glDeleteProgramsARB");
	glGenProgramsARB = (glGenProgramsARBPROC) extgl_GetProcAddress("glGenProgramsARB");
	glProgramEnvParameter4dARB = (glProgramEnvParameter4dARBPROC) extgl_GetProcAddress("glProgramEnvParameter4dARB");
	glProgramEnvParameter4dvARB = (glProgramEnvParameter4dvARBPROC) extgl_GetProcAddress("glProgramEnvParameter4dvARB");
	glProgramEnvParameter4fARB = (glProgramEnvParameter4fARBPROC) extgl_GetProcAddress("glProgramEnvParameter4fARB");
	glProgramEnvParameter4fvARB = (glProgramEnvParameter4fvARBPROC) extgl_GetProcAddress("glProgramEnvParameter4fvARB");
	glProgramLocalParameter4dARB = (glProgramLocalParameter4dARBPROC) extgl_GetProcAddress("glProgramLocalParameter4dARB");
	glProgramLocalParameter4dvARB = (glProgramLocalParameter4dvARBPROC) extgl_GetProcAddress("glProgramLocalParameter4dvARB");
	glProgramLocalParameter4fARB = (glProgramLocalParameter4fARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fARB");
	glProgramLocalParameter4fvARB = (glProgramLocalParameter4fvARBPROC) extgl_GetProcAddress("glProgramLocalParameter4fvARB");
	glGetProgramEnvParameterdvARB = (glGetProgramEnvParameterdvARBPROC) extgl_GetProcAddress("glGetProgramEnvParameterdvARB");
	glGetProgramEnvParameterfvARB = (glGetProgramEnvParameterfvARBPROC) extgl_GetProcAddress("glGetProgramEnvParameterfvARB");
	glGetProgramLocalParameterdvARB = (glGetProgramLocalParameterdvARBPROC) extgl_GetProcAddress("glGetProgramLocalParameterdvARB");
	glGetProgramLocalParameterfvARB = (glGetProgramLocalParameterfvARBPROC) extgl_GetProcAddress("glGetProgramLocalParameterfvARB");
	glGetProgramivARB = (glGetProgramivARBPROC) extgl_GetProcAddress("glGetProgramivARB");
	glGetProgramStringARB = (glGetProgramStringARBPROC) extgl_GetProcAddress("glGetProgramStringARB");
	glGetVertexAttribdvARB = (glGetVertexAttribdvARBPROC) extgl_GetProcAddress("glGetVertexAttribdvARB");
	glGetVertexAttribfvARB = (glGetVertexAttribfvARBPROC) extgl_GetProcAddress("glGetVertexAttribfvARB");
	glGetVertexAttribivARB = (glGetVertexAttribivARBPROC) extgl_GetProcAddress("glGetVertexAttribivARB");
	glGetVertexAttribPointervARB = (glGetVertexAttribPointervARBPROC) extgl_GetProcAddress("glGetVertexAttribPointervARB");
	glIsProgramARB = (glIsProgramARBPROC) extgl_GetProcAddress("glIsProgramARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_vertex_program)
}

static void extgl_InitEXTStencilTwoSide(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_stencil_two_side)
		return;
	glActiveStencilFaceEXT = (glActiveStencilFaceEXTPROC) extgl_GetProcAddress("glActiveStencilFaceEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_stencil_two_side)
}

static void extgl_InitARBWindowPos(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_window_pos)
		return;
	glWindowPos2dARB = (glWindowPos2dARBPROC) extgl_GetProcAddress("glWindowPos2dARB");
	glWindowPos2fARB = (glWindowPos2fARBPROC) extgl_GetProcAddress("glWindowPos2fARB");
	glWindowPos2iARB = (glWindowPos2iARBPROC) extgl_GetProcAddress("glWindowPos2iARB");
	glWindowPos2sARB = (glWindowPos2sARBPROC) extgl_GetProcAddress("glWindowPos2sARB");
	glWindowPos2dvARB = (glWindowPos2dvARBPROC) extgl_GetProcAddress("glWindowPos2dvARB");
	glWindowPos2fvARB = (glWindowPos2fvARBPROC) extgl_GetProcAddress("glWindowPos2fvARB");
	glWindowPos2ivARB = (glWindowPos2ivARBPROC) extgl_GetProcAddress("glWindowPos2ivARB");
	glWindowPos2svARB = (glWindowPos2svARBPROC) extgl_GetProcAddress("glWindowPos2svARB");
	glWindowPos3dARB = (glWindowPos3dARBPROC) extgl_GetProcAddress("glWindowPos3dARB");
	glWindowPos3fARB = (glWindowPos3fARBPROC) extgl_GetProcAddress("glWindowPos3fARB");
	glWindowPos3iARB = (glWindowPos3iARBPROC) extgl_GetProcAddress("glWindowPos3iARB");
	glWindowPos3sARB = (glWindowPos3sARBPROC) extgl_GetProcAddress("glWindowPos3sARB");
	glWindowPos3dvARB = (glWindowPos3dvARBPROC) extgl_GetProcAddress("glWindowPos3dvARB");
	glWindowPos3fvARB = (glWindowPos3fvARBPROC) extgl_GetProcAddress("glWindowPos3fvARB");
	glWindowPos3ivARB = (glWindowPos3ivARBPROC) extgl_GetProcAddress("glWindowPos3ivARB");
	glWindowPos3svARB = (glWindowPos3svARBPROC) extgl_GetProcAddress("glWindowPos3svARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_window_pos)
}

static void extgl_InitARBTextureCompression(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_texture_compression)
		return;
	glCompressedTexImage3DARB = (glCompressedTexImage3DARBPROC) extgl_GetProcAddress("glCompressedTexImage3DARB");
	glCompressedTexImage2DARB = (glCompressedTexImage2DARBPROC) extgl_GetProcAddress("glCompressedTexImage2DARB");
	glCompressedTexImage1DARB = (glCompressedTexImage1DARBPROC) extgl_GetProcAddress("glCompressedTexImage1DARB");
	glCompressedTexSubImage3DARB = (glCompressedTexSubImage3DARBPROC) extgl_GetProcAddress("glCompressedTexSubImage3DARB");
	glCompressedTexSubImage2DARB = (glCompressedTexSubImage2DARBPROC) extgl_GetProcAddress("glCompressedTexSubImage2DARB");
	glCompressedTexSubImage1DARB = (glCompressedTexSubImage1DARBPROC) extgl_GetProcAddress("glCompressedTexSubImage1DARB");
	glGetCompressedTexImageARB = (glGetCompressedTexImageARBPROC) extgl_GetProcAddress("glGetCompressedTexImageARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_texture_compression)
}

static void extgl_InitNVPointSprite(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_point_sprite)
		return;
	glPointParameteriNV = (glPointParameteriNVPROC) extgl_GetProcAddress("glPointParameteriNV");
	glPointParameterivNV = (glPointParameterivNVPROC) extgl_GetProcAddress("glPointParameterivNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_point_sprite)
}

static void extgl_InitNVOcclusionQuery(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_occlusion_query)
		return;
	glGenOcclusionQueriesNV = (glGenOcclusionQueriesNVPROC) extgl_GetProcAddress("glGenOcclusionQueriesNV");
	glDeleteOcclusionQueriesNV = (glDeleteOcclusionQueriesNVPROC) extgl_GetProcAddress("glDeleteOcclusionQueriesNV");
	glIsOcclusionQueryNV = (glIsOcclusionQueryNVPROC) extgl_GetProcAddress("glIsOcclusionQueryNV");
	glBeginOcclusionQueryNV = (glBeginOcclusionQueryNVPROC) extgl_GetProcAddress("glBeginOcclusionQueryNV");
	glEndOcclusionQueryNV = (glEndOcclusionQueryNVPROC) extgl_GetProcAddress("glEndOcclusionQueryNV");
	glGetOcclusionQueryivNV = (glGetOcclusionQueryivNVPROC) extgl_GetProcAddress("glGetOcclusionQueryivNV");
	glGetOcclusionQueryuivNV = (glGetOcclusionQueryuivNVPROC) extgl_GetProcAddress("glGetOcclusionQueryuivNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_occlusion_query)
}

static void extgl_InitATIVertexArrayObject(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_vertex_array_object)
		return;
	glNewObjectBufferATI = (glNewObjectBufferATIPROC) extgl_GetProcAddress("glNewObjectBufferATI");
	glIsObjectBufferATI = (glIsObjectBufferATIPROC) extgl_GetProcAddress("glIsObjectBufferATI");
	glUpdateObjectBufferATI = (glUpdateObjectBufferATIPROC) extgl_GetProcAddress("glUpdateObjectBufferATI");
	glGetObjectBufferfvATI = (glGetObjectBufferfvATIPROC) extgl_GetProcAddress("glGetObjectBufferfvATI");
	glGetObjectBufferivATI = (glGetObjectBufferivATIPROC) extgl_GetProcAddress("glGetObjectBufferivATI");
	glFreeObjectBufferATI = (glFreeObjectBufferATIPROC) extgl_GetProcAddress("glFreeObjectBufferATI");
	glArrayObjectATI = (glArrayObjectATIPROC) extgl_GetProcAddress("glArrayObjectATI");
	glGetArrayObjectfvATI = (glGetArrayObjectfvATIPROC) extgl_GetProcAddress("glGetArrayObjectfvATI");
	glGetArrayObjectivATI = (glGetArrayObjectivATIPROC) extgl_GetProcAddress("glGetArrayObjectivATI");
	glVariantArrayObjectATI = (glVariantArrayObjectATIPROC) extgl_GetProcAddress("glVariantArrayObjectATI");
	glGetVariantArrayObjectfvATI = (glGetVariantArrayObjectfvATIPROC) extgl_GetProcAddress("glGetVariantArrayObjectfvATI");
	glGetVariantArrayObjectivATI = (glGetVariantArrayObjectivATIPROC) extgl_GetProcAddress("glGetVariantArrayObjectivATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_vertex_array_object)
}

static void extgl_InitATIVertexStreams(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_vertex_streams)
		return;
	glClientActiveVertexStreamATI = (glClientActiveVertexStreamATIPROC) extgl_GetProcAddress("glClientActiveVertexStreamATI");
	glVertexBlendEnviATI = (glVertexBlendEnviATIPROC) extgl_GetProcAddress("glVertexBlendEnviATI");
	glVertexBlendEnvfATI = (glVertexBlendEnvfATIPROC) extgl_GetProcAddress("glVertexBlendEnvfATI");
	glVertexStream1sATI = (glVertexStream1sATIPROC) extgl_GetProcAddress("glVertexStream1sATI");
	glVertexStream1svATI = (glVertexStream1svATIPROC) extgl_GetProcAddress("glVertexStream1svATI");
	glVertexStream1iATI = (glVertexStream1iATIPROC) extgl_GetProcAddress("glVertexStream1iATI");
	glVertexStream1ivATI = (glVertexStream1ivATIPROC) extgl_GetProcAddress("glVertexStream1ivATI");
	glVertexStream1fATI = (glVertexStream1fATIPROC) extgl_GetProcAddress("glVertexStream1fATI");
	glVertexStream1fvATI = (glVertexStream1fvATIPROC) extgl_GetProcAddress("glVertexStream1fvATI");
	glVertexStream1dATI = (glVertexStream1dATIPROC) extgl_GetProcAddress("glVertexStream1dATI");
	glVertexStream1dvATI = (glVertexStream1dvATIPROC) extgl_GetProcAddress("glVertexStream1dvATI");
	glVertexStream2sATI = (glVertexStream2sATIPROC) extgl_GetProcAddress("glVertexStream2sATI");
	glVertexStream2svATI = (glVertexStream2svATIPROC) extgl_GetProcAddress("glVertexStream2svATI");
	glVertexStream2iATI = (glVertexStream2iATIPROC) extgl_GetProcAddress("glVertexStream2iATI");
	glVertexStream2ivATI = (glVertexStream2ivATIPROC) extgl_GetProcAddress("glVertexStream2ivATI");
	glVertexStream2fATI = (glVertexStream2fATIPROC) extgl_GetProcAddress("glVertexStream2fATI");
	glVertexStream2fvATI = (glVertexStream2fvATIPROC) extgl_GetProcAddress("glVertexStream2fvATI");
	glVertexStream2dATI = (glVertexStream2dATIPROC) extgl_GetProcAddress("glVertexStream2dATI");
	glVertexStream2dvATI = (glVertexStream2dvATIPROC) extgl_GetProcAddress("glVertexStream2dvATI");
	glVertexStream3sATI = (glVertexStream3sATIPROC) extgl_GetProcAddress("glVertexStream3sATI");
	glVertexStream3svATI = (glVertexStream3svATIPROC) extgl_GetProcAddress("glVertexStream3svATI");
	glVertexStream3iATI = (glVertexStream3iATIPROC) extgl_GetProcAddress("glVertexStream3iATI");
	glVertexStream3ivATI = (glVertexStream3ivATIPROC) extgl_GetProcAddress("glVertexStream3ivATI");
	glVertexStream3fATI = (glVertexStream3fATIPROC) extgl_GetProcAddress("glVertexStream3fATI");
	glVertexStream3fvATI = (glVertexStream3fvATIPROC) extgl_GetProcAddress("glVertexStream3fvATI");
	glVertexStream3dATI = (glVertexStream3dATIPROC) extgl_GetProcAddress("glVertexStream3dATI");
	glVertexStream3dvATI = (glVertexStream3dvATIPROC) extgl_GetProcAddress("glVertexStream3dvATI");
	glVertexStream4sATI = (glVertexStream4sATIPROC) extgl_GetProcAddress("glVertexStream4sATI");
	glVertexStream4svATI = (glVertexStream4svATIPROC) extgl_GetProcAddress("glVertexStream4svATI");
	glVertexStream4iATI = (glVertexStream4iATIPROC) extgl_GetProcAddress("glVertexStream4iATI");
	glVertexStream4ivATI = (glVertexStream4ivATIPROC) extgl_GetProcAddress("glVertexStream4ivATI");
	glVertexStream4fATI = (glVertexStream4fATIPROC) extgl_GetProcAddress("glVertexStream4fATI");
	glVertexStream4fvATI = (glVertexStream4fvATIPROC) extgl_GetProcAddress("glVertexStream4fvATI");
	glVertexStream4dATI = (glVertexStream4dATIPROC) extgl_GetProcAddress("glVertexStream4dATI");
	glVertexStream4dvATI = (glVertexStream4dvATIPROC) extgl_GetProcAddress("glVertexStream4dvATI");
	glNormalStream3bATI = (glNormalStream3bATIPROC) extgl_GetProcAddress("glNormalStream3bATI");
	glNormalStream3bvATI = (glNormalStream3bvATIPROC) extgl_GetProcAddress("glNormalStream3bvATI");
	glNormalStream3sATI = (glNormalStream3sATIPROC) extgl_GetProcAddress("glNormalStream3sATI");
	glNormalStream3svATI = (glNormalStream3svATIPROC) extgl_GetProcAddress("glNormalStream3svATI");
	glNormalStream3iATI = (glNormalStream3iATIPROC) extgl_GetProcAddress("glNormalStream3iATI");
	glNormalStream3ivATI = (glNormalStream3ivATIPROC) extgl_GetProcAddress("glNormalStream3ivATI");
	glNormalStream3fATI = (glNormalStream3fATIPROC) extgl_GetProcAddress("glNormalStream3fATI");
	glNormalStream3fvATI = (glNormalStream3fvATIPROC) extgl_GetProcAddress("glNormalStream3fvATI");
	glNormalStream3dATI = (glNormalStream3dATIPROC) extgl_GetProcAddress("glNormalStream3dATI");
	glNormalStream3dvATI = (glNormalStream3dvATIPROC) extgl_GetProcAddress("glNormalStream3dvATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_vertex_streams)
}

static void extgl_InitATIElementArray(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_element_array)
		return;
	glElementPointerATI = (glElementPointerATIPROC) extgl_GetProcAddress("glElementPointerATI");
	glDrawElementArrayATI = (glDrawElementArrayATIPROC) extgl_GetProcAddress("glDrawElementArrayATI");
	glDrawRangeElementArrayATI = (glDrawRangeElementArrayATIPROC) extgl_GetProcAddress("glDrawRangeElementArrayATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_element_array)
}

static void extgl_InitATIFragmentShader(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_fragment_shader)
		return;
	glGenFragmentShadersATI = (glGenFragmentShadersATIPROC) extgl_GetProcAddress("glGenFragmentShadersATI");
	glBindFragmentShaderATI = (glBindFragmentShaderATIPROC) extgl_GetProcAddress("glBindFragmentShaderATI");
	glDeleteFragmentShaderATI = (glDeleteFragmentShaderATIPROC) extgl_GetProcAddress("glDeleteFragmentShaderATI");
	glBeginFragmentShaderATI = (glBeginFragmentShaderATIPROC) extgl_GetProcAddress("glBeginFragmentShaderATI");
	glEndFragmentShaderATI = (glEndFragmentShaderATIPROC) extgl_GetProcAddress("glEndFragmentShaderATI");
	glPassTexCoordATI = (glPassTexCoordATIPROC) extgl_GetProcAddress("glPassTexCoordATI");
	glSampleMapATI = (glSampleMapATIPROC) extgl_GetProcAddress("glSampleMapATI");
	glColorFragmentOp1ATI = (glColorFragmentOp1ATIPROC) extgl_GetProcAddress("glColorFragmentOp1ATI");
	glColorFragmentOp2ATI = (glColorFragmentOp2ATIPROC) extgl_GetProcAddress("glColorFragmentOp2ATI");
	glColorFragmentOp3ATI = (glColorFragmentOp3ATIPROC) extgl_GetProcAddress("glColorFragmentOp3ATI");
	glAlphaFragmentOp1ATI = (glAlphaFragmentOp1ATIPROC) extgl_GetProcAddress("glAlphaFragmentOp1ATI");
	glAlphaFragmentOp2ATI = (glAlphaFragmentOp2ATIPROC) extgl_GetProcAddress("glAlphaFragmentOp2ATI");
	glAlphaFragmentOp3ATI = (glAlphaFragmentOp3ATIPROC) extgl_GetProcAddress("glAlphaFragmentOp3ATI");
	glSetFragmentShaderConstantATI = (glSetFragmentShaderConstantATIPROC) extgl_GetProcAddress("glSetFragmentShaderConstantATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_fragment_shader)
}


static void extgl_InitATIEnvmapBumpmap(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_envmap_bumpmap)
		return;
	glTexBumpParameterivATI = (glTexBumpParameterivATIPROC) extgl_GetProcAddress("glTexBumpParameterivATI");
	glTexBumpParameterfvATI = (glTexBumpParameterfvATIPROC) extgl_GetProcAddress("glTexBumpParameterfvATI");
	glGetTexBumpParameterivATI = (glGetTexBumpParameterivATIPROC) extgl_GetProcAddress("glGetTexBumpParameterivATI");
	glGetTexBumpParameterfvATI = (glGetTexBumpParameterfvATIPROC) extgl_GetProcAddress("glGetTexBumpParameterfvATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_envmap_bumpmap)
}

static void extgl_InitEXTVertexShader(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_vertex_shader)
		return;
	glBeginVertexShaderEXT = (glBeginVertexShaderEXTPROC) extgl_GetProcAddress("glBeginVertexShaderEXT");
	glEndVertexShaderEXT = (glEndVertexShaderEXTPROC) extgl_GetProcAddress("glEndVertexShaderEXT");
	glBindVertexShaderEXT = (glBindVertexShaderEXTPROC) extgl_GetProcAddress("glBindVertexShaderEXT");
	glGenVertexShadersEXT = (glGenVertexShadersEXTPROC) extgl_GetProcAddress("glGenVertexShadersEXT");
	glDeleteVertexShaderEXT = (glDeleteVertexShaderEXTPROC) extgl_GetProcAddress("glDeleteVertexShaderEXT");
	glShaderOp1EXT = (glShaderOp1EXTPROC) extgl_GetProcAddress("glShaderOp1EXT");
	glShaderOp2EXT = (glShaderOp2EXTPROC) extgl_GetProcAddress("glShaderOp2EXT");
	glShaderOp3EXT = (glShaderOp3EXTPROC) extgl_GetProcAddress("glShaderOp3EXT");
	glSwizzleEXT = (glSwizzleEXTPROC) extgl_GetProcAddress("glSwizzleEXT");
	glWriteMaskEXT = (glWriteMaskEXTPROC) extgl_GetProcAddress("glWriteMaskEXT");
	glInsertComponentEXT = (glInsertComponentEXTPROC) extgl_GetProcAddress("glInsertComponentEXT");
	glExtractComponentEXT = (glExtractComponentEXTPROC) extgl_GetProcAddress("glExtractComponentEXT");
	glGenSymbolsEXT = (glGenSymbolsEXTPROC) extgl_GetProcAddress("glGenSymbolsEXT");
	glSetInvariantEXT = (glSetInvariantEXTPROC) extgl_GetProcAddress("glSetInvarianceEXT");
	glSetLocalConstantEXT = (glSetLocalConstantEXTPROC) extgl_GetProcAddress("glSetLocalConstantEXT");
	glVariantbvEXT = (glVariantbvEXTPROC) extgl_GetProcAddress("glVariantbvEXT");
	glVariantsvEXT = (glVariantsvEXTPROC) extgl_GetProcAddress("glVariantsvEXT");
	glVariantivEXT = (glVariantivEXTPROC) extgl_GetProcAddress("glVariantivEXT");
	glVariantfvEXT = (glVariantfvEXTPROC) extgl_GetProcAddress("glVariantfvEXT");
	glVariantdvEXT = (glVariantdvEXTPROC) extgl_GetProcAddress("glVariantdvEXT");
	glVariantubvEXT = (glVariantubvEXTPROC) extgl_GetProcAddress("glVariantubvEXT");
	glVariantusvEXT = (glVariantusvEXTPROC) extgl_GetProcAddress("glVariantusvEXT");
	glVariantuivEXT = (glVariantuivEXTPROC) extgl_GetProcAddress("glVariantuivEXT");
	glVariantPointerEXT = (glVariantPointerEXTPROC) extgl_GetProcAddress("glVariantPointerEXT");
	glEnableVariantClientStateEXT = (glEnableVariantClientStateEXTPROC) extgl_GetProcAddress("glEnableVariantClientStateEXT");
	glDisableVariantClientStateEXT = (glDisableVariantClientStateEXTPROC) extgl_GetProcAddress("glDisableVariantClientStateEXT");
	glBindLightParameterEXT = (glBindLightParameterEXTPROC) extgl_GetProcAddress("glBindLightParameterEXT");
	glBindMaterialParameterEXT = (glBindMaterialParameterEXTPROC) extgl_GetProcAddress("glBindMaterialParameterEXT");
	glBindTexGenParameterEXT = (glBindTexGenParameterEXTPROC) extgl_GetProcAddress("glBindTexGenParameterEXT");
	glBindTextureUnitParameterEXT = (glBindTextureUnitParameterEXTPROC) extgl_GetProcAddress("glBindTextureUnitParameterEXT");
	glBindParameterEXT = (glBindParameterEXTPROC) extgl_GetProcAddress("glBindParameterEXT");
	glIsVariantEnabledEXT = (glIsVariantEnabledEXTPROC) extgl_GetProcAddress("glIsVariantEnabledEXT");
	glGetVariantBooleanvEXT = (glGetVariantBooleanvEXTPROC) extgl_GetProcAddress("glGetVariantBooleanvEXT");
	glGetVariantIntegervEXT = (glGetVariantIntegervEXTPROC) extgl_GetProcAddress("glGetVariantIntegervEXT");
	glGetVariantFloatvEXT = (glGetVariantFloatvEXTPROC) extgl_GetProcAddress("glGetVariantFloatvEXT");
	glGetVariantPointervEXT = (glGetVariantPointervEXTPROC) extgl_GetProcAddress("glGetVariantPointervEXT");
	glGetInvariantBooleanvEXT = (glGetInvariantBooleanvEXTPROC) extgl_GetProcAddress("glGetInvariantBooleanvEXT");
	glGetInvariantIntegervEXT = (glGetInvariantIntegervEXTPROC) extgl_GetProcAddress("glGetInvariantIntegervEXT");
	glGetInvariantFloatvEXT = (glGetInvariantFloatvEXTPROC) extgl_GetProcAddress("glGetInvariantFloatvEXT");
	glGetLocalConstantBooleanvEXT = (glGetLocalConstantBooleanvEXTPROC) extgl_GetProcAddress("glGetLocalConstantBooleanvEXT");
	glGetLocalConstantIntegervEXT = (glGetLocalConstantIntegervEXTPROC) extgl_GetProcAddress("glGetLocalConstantIntegervEXT");
	glGetLocalConstantFloatvEXT = (glGetLocalConstantFloatvEXTPROC) extgl_GetProcAddress("glGetLocalConstantFloatvEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_vertex_shader)
}

static void extgl_InitARBMatrixPalette(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_matrix_palette)
		return;
	glCurrentPaletteMatrixARB = (glCurrentPaletteMatrixARBPROC) extgl_GetProcAddress("glCurrentPaletteMatrixARB");
	glMatrixIndexubvARB = (glMatrixIndexubvARBPROC) extgl_GetProcAddress("glMatrixIndexubvARB");
	glMatrixIndexusvARB = (glMatrixIndexusvARBPROC) extgl_GetProcAddress("glMatrixIndexusvARB");
	glMatrixIndexuivARB = (glMatrixIndexuivARBPROC) extgl_GetProcAddress("glMatrixIndexuivARB");
	glMatrixIndexPointerARB = (glMatrixIndexPointerARBPROC) extgl_GetProcAddress("glMatrixIndexPointerARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_matrix_palette)
}

static void extgl_InitEXTMultiDrawArrays(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_multi_draw_arrays)
		return;
	glMultiDrawArraysEXT = (glMultiDrawArraysEXTPROC) extgl_GetProcAddress("glMultiDrawArraysEXT");
	glMultiDrawElementsEXT = (glMultiDrawElementsEXTPROC) extgl_GetProcAddress("glMultiDrawElementsEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_multi_draw_arrays)
}

static void extgl_InitARBVertexBlend(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_vertex_blend)
		return;
	glWeightbvARB = (glWeightbvARBPROC) extgl_GetProcAddress("glWeightbvARB");
	glWeightsvARB = (glWeightsvARBPROC) extgl_GetProcAddress("glWeightsvARB");
	glWeightivARB = (glWeightivARBPROC) extgl_GetProcAddress("glWeightivARB");
	glWeightfvARB = (glWeightfvARBPROC) extgl_GetProcAddress("glWeightfvARB");
	glWeightdvARB = (glWeightdvARBPROC) extgl_GetProcAddress("glWeightdvARB");
	glWeightubvARB = (glWeightubvARBPROC) extgl_GetProcAddress("glWeightubvARB");
	glWeightusvARB = (glWeightusvARBPROC) extgl_GetProcAddress("glWeightusvARB");
	glWeightuivARB = (glWeightuivARBPROC) extgl_GetProcAddress("glWeightuivARB");
	glWeightPointerARB = (glWeightPointerARBPROC) extgl_GetProcAddress("glWeightPointerARB");
	glVertexBlendARB = (glVertexBlendARBPROC) extgl_GetProcAddress("glVertexBlendARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_vertex_blend)
}

static void extgl_InitARBPointParameters(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_point_parameters)
		return;
	glPointParameterfARB = (glPointParameterfARBPROC) extgl_GetProcAddress("glPointParameterfARB");
	glPointParameterfvARB = (glPointParameterfvARBPROC) extgl_GetProcAddress("glPointParameterfvARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_point_parameters)
}

static void extgl_InitATIPNTriangles(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_pn_triangles)
		return;
	glPNTrianglesiATI = (glPNTrianglesiATIPROC) extgl_GetProcAddress("glPNTrianglesiATI");
	glPNTrianglesfATI = (glPNTrianglesfATIPROC) extgl_GetProcAddress("glPNTrianglesfATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_pn_triangles)
}

static void extgl_InitATISeparateStencil(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_separate_stencil)
		return;
	glStencilOpSeparateATI = (glStencilOpSeparateATIPROC)extgl_GetProcAddress("glStencilOpSeparateATI");
	glStencilFuncSeparateATI = (glStencilFuncSeparateATIPROC)extgl_GetProcAddress("glStencilFuncSeparateATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_separate_stencil)
}

static void extgl_InitNVEvaluators(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_evaluators)
		return;
	glMapControlPointsNV = (glMapControlPointsNVPROC) extgl_GetProcAddress("glMapControlPointsNV");
	glMapParameterivNV = (glMapParameterivNVPROC) extgl_GetProcAddress("glMapParameterivNV");
	glMapParameterfvNV = (glMapParameterfvNVPROC) extgl_GetProcAddress("glMapParameterfvNV");
	glGetMapControlPointsNV = (glGetMapControlPointsNVPROC) extgl_GetProcAddress("glGetMapControlPointsNV");
	glGetMapParameterivNV = (glGetMapParameterivNVPROC) extgl_GetProcAddress("glGetMapParameterivNV");
	glGetMapParameterfvNV = (glGetMapParameterfvNVPROC) extgl_GetProcAddress("glGetMapParameterfvNV");
	glGetMapAttribParameterivNV = (glGetMapAttribParameterivNVPROC) extgl_GetProcAddress("glGetMapAttribParameterivNV");
	glGetMapAttribParameterfvNV = (glGetMapAttribParameterfvNVPROC) extgl_GetProcAddress("glGetMapAttribParameterfvNV");
	glEvalMapsNV = (glEvalMapsNVPROC) extgl_GetProcAddress("glEvalMapsNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_evaluators)
}

static void extgl_InitNVRegisterCombiners2(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_register_combiners2)
		return;
	glCombinerStageParameterfvNV = (glCombinerStageParameterfvNVPROC) extgl_GetProcAddress("glCombinerStageParameterfvNV");
	glGetCombinerStageParameterfvNV = (glGetCombinerStageParameterfvNVPROC) extgl_GetProcAddress("glGetCombinerStageParameterfvNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_register_combiners2)
}

static void extgl_InitNVFence(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_fence)
		return;
	glGenFencesNV = (glGenFencesNVPROC) extgl_GetProcAddress("glGenFencesNV");
	glDeleteFencesNV = (glDeleteFencesNVPROC) extgl_GetProcAddress("glDeleteFencesNV");
	glSetFenceNV = (glSetFenceNVPROC) extgl_GetProcAddress("glSetFenceNV");
	glTestFenceNV = (glTestFenceNVPROC) extgl_GetProcAddress("glTestFenceNV");
	glFinishFenceNV = (glFinishFenceNVPROC) extgl_GetProcAddress("glFinishFenceNV");
	glIsFenceNV = (glIsFenceNVPROC) extgl_GetProcAddress("glIsFenceNV");
	glGetFenceivNV = (glGetFenceivNVPROC) extgl_GetProcAddress("glGetFenceivNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_fence)
}

static void extgl_InitNVVertexProgram(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_vertex_program)
		return;
	glBindProgramNV = (glBindProgramNVPROC) extgl_GetProcAddress("glBindProgramNV");
	glDeleteProgramsNV = (glDeleteProgramsNVPROC) extgl_GetProcAddress("glDeleteProgramsNV");
	glExecuteProgramNV = (glExecuteProgramNVPROC) extgl_GetProcAddress("glExecuteProgramNV");
	glGenProgramsNV = (glGenProgramsNVPROC) extgl_GetProcAddress("glGenProgramsNV");
	glAreProgramsResidentNV = (glAreProgramsResidentNVPROC) extgl_GetProcAddress("glAreProgramsResidentNV");
	glRequestResidentProgramsNV = (glRequestResidentProgramsNVPROC) extgl_GetProcAddress("glRequestResidentProgramsNV");
	glGetProgramParameterfvNV = (glGetProgramParameterfvNVPROC) extgl_GetProcAddress("glGetProgramParameterfvNV");
	glGetProgramParameterdvNV = (glGetProgramParameterdvNVPROC) extgl_GetProcAddress("glGetProgramParameterdvNV");
	glGetProgramivNV = (glGetProgramivNVPROC) extgl_GetProcAddress("glGetProgramivNV");
	glGetProgramStringNV = (glGetProgramStringNVPROC) extgl_GetProcAddress("glGetProgramStringNV");
	glGetTrackMatrixivNV = (glGetTrackMatrixivNVPROC) extgl_GetProcAddress("glGetTrackMatrixivNV");
	glGetVertexAttribdvNV = (glGetVertexAttribdvNVPROC) extgl_GetProcAddress("glGetVertexAttribdvNV");
	glGetVertexAttribfvNV = (glGetVertexAttribfvNVPROC) extgl_GetProcAddress("glGetVertexAttribfvNV");
	glGetVertexAttribivNV = (glGetVertexAttribivNVPROC) extgl_GetProcAddress("glGetVertexAttribivNV");
	glGetVertexAttribPointervNV = (glGetVertexAttribPointervNVPROC) extgl_GetProcAddress("glGetVertexAttribPointervNV");
	glIsProgramNV = (glIsProgramNVPROC) extgl_GetProcAddress("glIsProgramNV");
	glLoadProgramNV = (glLoadProgramNVPROC) extgl_GetProcAddress("glLoadProgramNV");
	glProgramParameter4fNV = (glProgramParameter4fNVPROC) extgl_GetProcAddress("glProgramParameter4fNV");
	glProgramParameter4dNV = (glProgramParameter4dNVPROC) extgl_GetProcAddress("glProgramParameter4dNV");
	glProgramParameter4dvNV = (glProgramParameter4dvNVPROC) extgl_GetProcAddress("glProgramParameter4dvNV");
	glProgramParameter4fvNV = (glProgramParameter4fvNVPROC) extgl_GetProcAddress("glProgramParameter4fvNV");
	glProgramParameters4dvNV = (glProgramParameters4dvNVPROC) extgl_GetProcAddress("glProgramParameters4dvNV");
	glProgramParameters4fvNV = (glProgramParameters4fvNVPROC) extgl_GetProcAddress("glProgramParameters4fvNV");
	glTrackMatrixNV = (glTrackMatrixNVPROC) extgl_GetProcAddress("glTrackMatrixNV");
	glVertexAttribPointerNV = (glVertexAttribPointerNVPROC) extgl_GetProcAddress("glVertexAttribPointerNV");
	glVertexAttrib1sNV = (glVertexAttrib1sNVPROC) extgl_GetProcAddress("glVertexAttrib1sNV");
	glVertexAttrib1fNV = (glVertexAttrib1fNVPROC) extgl_GetProcAddress("glVertexAttrib1fNV");
	glVertexAttrib1dNV = (glVertexAttrib1dNVPROC) extgl_GetProcAddress("glVertexAttrib1dNV");
	glVertexAttrib2sNV = (glVertexAttrib2sNVPROC) extgl_GetProcAddress("glVertexAttrib2sNV");
	glVertexAttrib2fNV = (glVertexAttrib2fNVPROC) extgl_GetProcAddress("glVertexAttrib2fNV");
	glVertexAttrib2dNV = (glVertexAttrib2dNVPROC) extgl_GetProcAddress("glVertexAttrib2dNV");
	glVertexAttrib3sNV = (glVertexAttrib3sNVPROC) extgl_GetProcAddress("glVertexAttrib3sNV");
	glVertexAttrib3fNV = (glVertexAttrib3fNVPROC) extgl_GetProcAddress("glVertexAttrib3fNV");
	glVertexAttrib3dNV = (glVertexAttrib3dNVPROC) extgl_GetProcAddress("glVertexAttrib3dNV");
	glVertexAttrib4sNV = (glVertexAttrib4sNVPROC) extgl_GetProcAddress("glVertexAttrib4sNV");
	glVertexAttrib4fNV = (glVertexAttrib4fNVPROC) extgl_GetProcAddress("glVertexAttrib4fNV");
	glVertexAttrib4dNV = (glVertexAttrib4dNVPROC) extgl_GetProcAddress("glVertexAttrib4dNV");
	glVertexAttrib4ubNV = (glVertexAttrib4ubNVPROC) extgl_GetProcAddress("glVertexAttrib4ubNV");
	glVertexAttrib1svNV = (glVertexAttrib1svNVPROC) extgl_GetProcAddress("glVertexAttrib1svNV");
	glVertexAttrib1fvNV = (glVertexAttrib1fvNVPROC) extgl_GetProcAddress("glVertexAttrib1fvNV");
	glVertexAttrib1dvNV = (glVertexAttrib1dvNVPROC) extgl_GetProcAddress("glVertexAttrib1dvNV");
	glVertexAttrib2svNV = (glVertexAttrib2svNVPROC) extgl_GetProcAddress("glVertexAttrib2svNV");
	glVertexAttrib2fvNV = (glVertexAttrib2fvNVPROC) extgl_GetProcAddress("glVertexAttrib2fvNV");
	glVertexAttrib2dvNV = (glVertexAttrib2dvNVPROC) extgl_GetProcAddress("glVertexAttrib2dvNV");
	glVertexAttrib3svNV = (glVertexAttrib3svNVPROC) extgl_GetProcAddress("glVertexAttrib3svNV");
	glVertexAttrib3fvNV = (glVertexAttrib3fvNVPROC) extgl_GetProcAddress("glVertexAttrib3fvNV");
	glVertexAttrib3dvNV = (glVertexAttrib3dvNVPROC) extgl_GetProcAddress("glVertexAttrib3dvNV");
	glVertexAttrib4svNV = (glVertexAttrib4svNVPROC) extgl_GetProcAddress("glVertexAttrib4svNV");
	glVertexAttrib4fvNV = (glVertexAttrib4fvNVPROC) extgl_GetProcAddress("glVertexAttrib4fvNV");
	glVertexAttrib4dvNV = (glVertexAttrib4dvNVPROC) extgl_GetProcAddress("glVertexAttrib4dvNV");
	glVertexAttrib4ubvNV = (glVertexAttrib4ubvNVPROC) extgl_GetProcAddress("glVertexAttrib4ubvNV");
	glVertexAttribs1svNV = (glVertexAttribs1svNVPROC) extgl_GetProcAddress("glVertexAttribs1svNV");
	glVertexAttribs1fvNV = (glVertexAttribs1fvNVPROC) extgl_GetProcAddress("glVertexAttribs1fvNV");
	glVertexAttribs1dvNV = (glVertexAttribs1dvNVPROC) extgl_GetProcAddress("glVertexAttribs1dvNV");
	glVertexAttribs2svNV = (glVertexAttribs2svNVPROC) extgl_GetProcAddress("glVertexAttribs2svNV");
	glVertexAttribs2fvNV = (glVertexAttribs2fvNVPROC) extgl_GetProcAddress("glVertexAttribs2fvNV");
	glVertexAttribs2dvNV = (glVertexAttribs2dvNVPROC) extgl_GetProcAddress("glVertexAttribs2dvNV");
	glVertexAttribs3svNV = (glVertexAttribs3svNVPROC) extgl_GetProcAddress("glVertexAttribs3svNV");
	glVertexAttribs3fvNV = (glVertexAttribs3fvNVPROC) extgl_GetProcAddress("glVertexAttribs3fvNV");
	glVertexAttribs3dvNV = (glVertexAttribs3dvNVPROC) extgl_GetProcAddress("glVertexAttribs3dvNV");
	glVertexAttribs4svNV = (glVertexAttribs4svNVPROC) extgl_GetProcAddress("glVertexAttribs4svNV");
	glVertexAttribs4fvNV = (glVertexAttribs4fvNVPROC) extgl_GetProcAddress("glVertexAttribs4fvNV");
	glVertexAttribs4dvNV = (glVertexAttribs4dvNVPROC) extgl_GetProcAddress("glVertexAttribs4dvNV");
	glVertexAttribs4ubvNV = (glVertexAttribs4ubvNVPROC) extgl_GetProcAddress("glVertexAttribs4ubvNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_vertex_program)
}

static void extgl_InitEXTVertexWeighting(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_vertex_weighting)
		return;
	glVertexWeightfEXT = (glVertexWeightfEXTPROC) extgl_GetProcAddress("glVertexWeightfEXT");
	glVertexWeightfvEXT = (glVertexWeightfvEXTPROC) extgl_GetProcAddress("glVertexWeightfvEXT");
	glVertexWeightPointerEXT = (glVertexWeightPointerEXTPROC) extgl_GetProcAddress("glVertexWeightPointerEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_vertex_weighting)
}

static void extgl_InitARBMultisample(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_multisample)
		return;
	glSampleCoverageARB = (glSampleCoverageARBPROC) extgl_GetProcAddress("glSampleCoverageARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_multisample)
}

static void extgl_InitNVRegisterCombiners(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_register_combiners)
		return;
	glCombinerParameterfvNV = (glCombinerParameterfvNVPROC) extgl_GetProcAddress("glCombinerParameterfvNV");
	glCombinerParameterfNV = (glCombinerParameterfNVPROC) extgl_GetProcAddress("glCombinerParameterfNV");
	glCombinerParameterivNV = (glCombinerParameterivNVPROC) extgl_GetProcAddress("glCombinerParameterivNV");
	glCombinerParameteriNV = (glCombinerParameteriNVPROC) extgl_GetProcAddress("glCombinerParameteriNV");
	glCombinerInputNV = (glCombinerInputNVPROC) extgl_GetProcAddress("glCombinerInputNV");
	glCombinerOutputNV = (glCombinerOutputNVPROC) extgl_GetProcAddress("glCombinerOutputNV");
	glFinalCombinerInputNV = (glFinalCombinerInputNVPROC) extgl_GetProcAddress("glFinalCombinerInputNV");
	glGetCombinerInputParameterfvNV = (glGetCombinerInputParameterfvNVPROC) extgl_GetProcAddress("glGetCombinerInputParameterfvNV");
	glGetCombinerInputParameterivNV = (glGetCombinerInputParameterivNVPROC) extgl_GetProcAddress("glGetCombinerInputParameterivNV");
	glGetCombinerOutputParameterfvNV = (glGetCombinerOutputParameterfvNVPROC) extgl_GetProcAddress("glGetCombinerOutputParameterfvNV");
	glGetCombinerOutputParameterivNV = (glGetCombinerOutputParameterivNVPROC) extgl_GetProcAddress("glGetCombinerOutputParameterivNV");
	glGetFinalCombinerInputParameterfvNV = (glGetFinalCombinerInputParameterfvNVPROC) extgl_GetProcAddress("glGetFinalCombinerInputParameterfvNV");
	glGetFinalCombinerInputParameterivNV = (glGetFinalCombinerInputParameterivNVPROC) extgl_GetProcAddress("glGetFinalCombinerInputParameterivNV");
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_register_combiners)
}

static void extgl_InitEXTPointParameters(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_point_parameters)
		return;
	glPointParameterfEXT = (glPointParameterfEXTPROC) extgl_GetProcAddress("glPointParameterfEXT");
	glPointParameterfvEXT = (glPointParameterfvEXTPROC) extgl_GetProcAddress("glPointParameterfvEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_point_parameters)
}

static void extgl_InitNVVertexArrayRange(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_vertex_array_range)
		return;
	glFlushVertexArrayRangeNV = (glFlushVertexArrayRangeNVPROC) extgl_GetProcAddress("glFlushVertexArrayRangeNV");
	glVertexArrayRangeNV = (glVertexArrayRangeNVPROC) extgl_GetProcAddress("glVertexArrayRangeNV");
#ifdef _WIN32
	wglAllocateMemoryNV = (wglAllocateMemoryNVPROC) extgl_GetProcAddress("wglAllocateMemoryNV");
	wglFreeMemoryNV = (wglFreeMemoryNVPROC) extgl_GetProcAddress("wglFreeMemoryNV");
#endif /* WIN32 */
#ifdef _X11
	glXAllocateMemoryNV = (glXAllocateMemoryNVPROC) extgl_GetProcAddress("glXAllocateMemoryNV");
	glXFreeMemoryNV = (glXFreeMemoryNVPROC) extgl_GetProcAddress("glXFreeMemoryNV");
#endif /* X11 */
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_vertex_array_range)
}
 
static void extgl_InitEXTFogCoord(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_fog_coord)
		return;
	glFogCoordfEXT = (glFogCoordfEXTPROC) extgl_GetProcAddress("glFogCoordfEXT");
	glFogCoordfvEXT = (glFogCoordfvEXTPROC) extgl_GetProcAddress("glFogCoordfvEXT");
	glFogCoorddEXT = (glFogCoorddEXTPROC) extgl_GetProcAddress("glFogCoorddEXT");
	glFogCoorddvEXT = (glFogCoorddvEXTPROC) extgl_GetProcAddress("glFogCoorddvEXT");
	glFogCoordPointerEXT = (glFogCoordPointerEXTPROC) extgl_GetProcAddress("glFogCoordPointerEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_fog_coord)
}

static void extgl_InitEXTSecondaryColor(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_secondary_color)
		return;
	glSecondaryColor3bEXT = (glSecondaryColor3bEXTPROC) extgl_GetProcAddress("glSecondaryColor3bEXT");
	glSecondaryColor3bvEXT = (glSecondaryColor3bvEXTPROC) extgl_GetProcAddress("glSecondaryColor3bvEXT");
	glSecondaryColor3dEXT = (glSecondaryColor3dEXTPROC) extgl_GetProcAddress("glSecondaryColor3dEXT");
	glSecondaryColor3dvEXT = (glSecondaryColor3dvEXTPROC) extgl_GetProcAddress("glSecondaryColor3dvEXT");
	glSecondaryColor3fEXT = (glSecondaryColor3fEXTPROC) extgl_GetProcAddress("glSecondaryColor3fEXT");
	glSecondaryColor3fvEXT = (glSecondaryColor3fvEXTPROC) extgl_GetProcAddress("glSecondaryColor3fvEXT");
	glSecondaryColor3iEXT = (glSecondaryColor3iEXTPROC) extgl_GetProcAddress("glSecondaryColor3iEXT");
	glSecondaryColor3ivEXT = (glSecondaryColor3ivEXTPROC) extgl_GetProcAddress("glSecondaryColor3ivEXT");
	glSecondaryColor3sEXT = (glSecondaryColor3sEXTPROC) extgl_GetProcAddress("glSecondaryColor3sEXT");
	glSecondaryColor3svEXT = (glSecondaryColor3svEXTPROC) extgl_GetProcAddress("glSecondaryColor3svEXT");
	glSecondaryColor3ubEXT = (glSecondaryColor3ubEXTPROC) extgl_GetProcAddress("glSecondaryColor3ubEXT");
	glSecondaryColor3ubvEXT = (glSecondaryColor3ubvEXTPROC) extgl_GetProcAddress("glSecondaryColor3ubvEXT");
	glSecondaryColor3uiEXT = (glSecondaryColor3uiEXTPROC) extgl_GetProcAddress("glSecondaryColor3uiEXT");
	glSecondaryColor3uivEXT = (glSecondaryColor3uivEXTPROC) extgl_GetProcAddress("glSecondaryColor3uivEXT");
	glSecondaryColor3usEXT = (glSecondaryColor3usEXTPROC) extgl_GetProcAddress("glSecondaryColor3usEXT");
	glSecondaryColor3usvEXT = (glSecondaryColor3usvEXTPROC) extgl_GetProcAddress("glSecondaryColor3usvEXT");
	glSecondaryColorPointerEXT = (glSecondaryColorPointerEXTPROC) extgl_GetProcAddress("glSecondaryColorPointerEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_secondary_color)
}

static void extgl_InitEXTCompiledVertexArray(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_compiled_vertex_array)
		return;
	glLockArraysEXT = (glLockArraysEXTPROC) extgl_GetProcAddress("glLockArraysEXT");
	glUnlockArraysEXT = (glUnlockArraysEXTPROC) extgl_GetProcAddress("glUnlockArraysEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_compiled_vertex_array)
}

static void extgl_InitARBTransposeMatrix(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_transpose_matrix)
		return;
	glLoadTransposeMatrixfARB = (glLoadTransposeMatrixfARBPROC) extgl_GetProcAddress("glLoadTransposeMatrixfARB");
	glLoadTransposeMatrixdARB = (glLoadTransposeMatrixdARBPROC) extgl_GetProcAddress("glLoadTransposeMatrixdARB");
	glMultTransposeMatrixfARB = (glMultTransposeMatrixfARBPROC) extgl_GetProcAddress("glMultTransposeMatrixfARB");
	glMultTransposeMatrixdARB = (glMultTransposeMatrixdARBPROC) extgl_GetProcAddress("glMultTransposeMatrixdARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_transpose_matrix)
}

static void extgl_InitEXTDrawRangeElements(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_EXT_draw_range_elements)
		return;
	glDrawRangeElementsEXT = (glDrawRangeElementsEXTPROC) extgl_GetProcAddress("glDrawRangeElementsEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GL_EXT_draw_range_elements)
}

static void extgl_InitARBMultitexture(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_multitexture)
		return;
	glActiveTextureARB = (glActiveTextureARBPROC) extgl_GetProcAddress("glActiveTextureARB");
	glClientActiveTextureARB = (glClientActiveTextureARBPROC) extgl_GetProcAddress("glClientActiveTextureARB");

	glMultiTexCoord1dARB = (glMultiTexCoord1dARBPROC) extgl_GetProcAddress("glMultiTexCoord1dARB");
	glMultiTexCoord1dvARB = (glMultiTexCoord1dvARBPROC) extgl_GetProcAddress("glMultiTexCoord1dvARB");
	glMultiTexCoord1fARB = (glMultiTexCoord1fARBPROC) extgl_GetProcAddress("glMultiTexCoord1fARB");
	glMultiTexCoord1fvARB = (glMultiTexCoord1fvARBPROC) extgl_GetProcAddress("glMultiTexCoord1fvARB");
	glMultiTexCoord1iARB = (glMultiTexCoord1iARBPROC) extgl_GetProcAddress("glMultiTexCoord1iARB");
	glMultiTexCoord1ivARB = (glMultiTexCoord1ivARBPROC) extgl_GetProcAddress("glMultiTexCoord1ivARB");
	glMultiTexCoord1sARB = (glMultiTexCoord1sARBPROC) extgl_GetProcAddress("glMultiTexCoord1sARB");
	glMultiTexCoord1svARB = (glMultiTexCoord1svARBPROC) extgl_GetProcAddress("glMultiTexCoord1svARB");

	glMultiTexCoord2dARB = (glMultiTexCoord2dARBPROC) extgl_GetProcAddress("glMultiTexCoord2dARB");
	glMultiTexCoord2dvARB = (glMultiTexCoord2dvARBPROC) extgl_GetProcAddress("glMultiTexCoord2dvARB");
	glMultiTexCoord2fARB = (glMultiTexCoord2fARBPROC) extgl_GetProcAddress("glMultiTexCoord2fARB");
	glMultiTexCoord2fvARB = (glMultiTexCoord2fvARBPROC) extgl_GetProcAddress("glMultiTexCoord2fvARB");
	glMultiTexCoord2iARB = (glMultiTexCoord2iARBPROC) extgl_GetProcAddress("glMultiTexCoord2iARB");
	glMultiTexCoord2ivARB = (glMultiTexCoord2ivARBPROC) extgl_GetProcAddress("glMultiTexCoord2ivARB");
	glMultiTexCoord2sARB = (glMultiTexCoord2sARBPROC) extgl_GetProcAddress("glMultiTexCoord2sARB");
	glMultiTexCoord2svARB = (glMultiTexCoord2svARBPROC) extgl_GetProcAddress("glMultiTexCoord2svARB");

	glMultiTexCoord3dARB = (glMultiTexCoord3dARBPROC) extgl_GetProcAddress("glMultiTexCoord3dARB");
	glMultiTexCoord3dvARB = (glMultiTexCoord3dvARBPROC) extgl_GetProcAddress("glMultiTexCoord3dvARB");
	glMultiTexCoord3fARB = (glMultiTexCoord3fARBPROC) extgl_GetProcAddress("glMultiTexCoord3fARB");
	glMultiTexCoord3fvARB = (glMultiTexCoord3fvARBPROC) extgl_GetProcAddress("glMultiTexCoord3fvARB");
	glMultiTexCoord3iARB = (glMultiTexCoord3iARBPROC) extgl_GetProcAddress("glMultiTexCoord3iARB");
	glMultiTexCoord3ivARB = (glMultiTexCoord3ivARBPROC) extgl_GetProcAddress("glMultiTexCoord3ivARB");
	glMultiTexCoord3sARB = (glMultiTexCoord3sARBPROC) extgl_GetProcAddress("glMultiTexCoord3sARB");
	glMultiTexCoord3svARB = (glMultiTexCoord3svARBPROC) extgl_GetProcAddress("glMultiTexCoord3svARB");

	glMultiTexCoord4dARB = (glMultiTexCoord4dARBPROC) extgl_GetProcAddress("glMultiTexCoord4dARB");
	glMultiTexCoord4dvARB = (glMultiTexCoord4dvARBPROC) extgl_GetProcAddress("glMultiTexCoord4dvARB");
	glMultiTexCoord4fARB = (glMultiTexCoord4fARBPROC) extgl_GetProcAddress("glMultiTexCoord4fARB");
	glMultiTexCoord4fvARB = (glMultiTexCoord4fvARBPROC) extgl_GetProcAddress("glMultiTexCoord4fvARB");
	glMultiTexCoord4iARB = (glMultiTexCoord4iARBPROC) extgl_GetProcAddress("glMultiTexCoord4iARB");
	glMultiTexCoord4ivARB = (glMultiTexCoord4ivARBPROC) extgl_GetProcAddress("glMultiTexCoord4ivARB");
	glMultiTexCoord4sARB = (glMultiTexCoord4sARBPROC) extgl_GetProcAddress("glMultiTexCoord4sARB");
	glMultiTexCoord4svARB = (glMultiTexCoord4svARBPROC) extgl_GetProcAddress("glMultiTexCoord4svARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_multitexture)
}

static void extgl_InitGLU12(void)
{
	gluBeginCurve = (gluBeginCurvePROC) extgl_GetProcAddress("gluBeginCurve");
	gluBeginPolygon = (gluBeginPolygonPROC) extgl_GetProcAddress("gluBeginPolygon");
	gluBeginSurface = (gluBeginSurfacePROC) extgl_GetProcAddress("gluBeginSurface");
	gluBeginTrim = (gluBeginTrimPROC) extgl_GetProcAddress("gluBeginTrim");
	gluBuild1DMipmaps = (gluBuild1DMipmapsPROC) extgl_GetProcAddress("gluBuild1DMipmaps");
	gluBuild2DMipmaps = (gluBuild2DMipmapsPROC) extgl_GetProcAddress("gluBuild2DMipmaps");
	gluCylinder = (gluCylinderPROC) extgl_GetProcAddress("gluCylinder");
	gluDeleteNurbsRenderer = (gluDeleteNurbsRendererPROC) extgl_GetProcAddress("gluDeleteNurbsRenderer");
	gluDeleteQuadric = (gluDeleteQuadricPROC) extgl_GetProcAddress("gluDeleteQuadric");
	gluDeleteTess = (gluDeleteTessPROC) extgl_GetProcAddress("gluDeleteTess");
	gluDisk = (gluDiskPROC) extgl_GetProcAddress("gluDisk");
	gluEndCurve = (gluEndCurvePROC) extgl_GetProcAddress("gluEndCurve");
	gluEndPolygon = (gluEndPolygonPROC) extgl_GetProcAddress("gluEndPolygon");
	gluEndSurface = (gluEndSurfacePROC) extgl_GetProcAddress("gluEndSurface");
	gluEndTrim = (gluEndTrimPROC) extgl_GetProcAddress("gluEndTrim");
	gluErrorString = (gluErrorStringPROC) extgl_GetProcAddress("gluErrorString");
	gluGetNurbsProperty = (gluGetNurbsPropertyPROC) extgl_GetProcAddress("gluGetNurbsProperty");
	gluGetString = (gluGetStringPROC) extgl_GetProcAddress("gluGetString");
	gluGetTessProperty = (gluGetTessPropertyPROC) extgl_GetProcAddress("gluGetTessProperty");
	gluLoadSamplingMatrices = (gluLoadSamplingMatricesPROC) extgl_GetProcAddress("gluLoadSamplingMatrices");
	gluLookAt = (gluLookAtPROC) extgl_GetProcAddress("gluLookAt");
	gluNewNurbsRenderer = (gluNewNurbsRendererPROC) extgl_GetProcAddress("gluNewNurbsRenderer");
	gluNewQuadric = (gluNewQuadricPROC) extgl_GetProcAddress("gluNewQuadric");
	gluNewTess = (gluNewTessPROC) extgl_GetProcAddress("gluNewTess");
	gluNextContour = (gluNextContourPROC) extgl_GetProcAddress("gluNextContour");
	gluNurbsCallback = (gluNurbsCallbackPROC) extgl_GetProcAddress("gluNurbsCallback");
	gluNurbsCurve = (gluNurbsCurvePROC) extgl_GetProcAddress("gluNurbsCurve");
	gluNurbsProperty = (gluNurbsPropertyPROC) extgl_GetProcAddress("gluNurbsProperty");
	gluNurbsSurface = (gluNurbsSurfacePROC) extgl_GetProcAddress("gluNurbsSurface");
	gluOrtho2D = (gluOrtho2DPROC) extgl_GetProcAddress("gluOrtho2D");
	gluPartialDisk = (gluPartialDiskPROC) extgl_GetProcAddress("gluPartialDisk");
	gluPerspective = (gluPerspectivePROC) extgl_GetProcAddress("gluPerspective");
	gluPickMatrix = (gluPickMatrixPROC) extgl_GetProcAddress("gluPickMatrix");
	gluProject = (gluProjectPROC) extgl_GetProcAddress("gluProject");
	gluPwlCurve = (gluPwlCurvePROC) extgl_GetProcAddress("gluPwlCurve");
	gluQuadricCallback = (gluQuadricCallbackPROC) extgl_GetProcAddress("gluQuadricCallback");
	gluQuadricDrawStyle = (gluQuadricDrawStylePROC) extgl_GetProcAddress("gluQuadricDrawStyle");
	gluQuadricNormals = (gluQuadricNormalsPROC) extgl_GetProcAddress("gluQuadricNormals");
	gluQuadricOrientation = (gluQuadricOrientationPROC) extgl_GetProcAddress("gluQuadricOrientation");
	gluQuadricTexture = (gluQuadricTexturePROC) extgl_GetProcAddress("gluQuadricTexture");
	gluScaleImage = (gluScaleImagePROC) extgl_GetProcAddress("gluScaleImage");
	gluSphere = (gluSpherePROC) extgl_GetProcAddress("gluSphere");
	gluTessBeginContour = (gluTessBeginContourPROC) extgl_GetProcAddress("gluTessBeginContour");
	gluTessBeginPolygon = (gluTessBeginPolygonPROC) extgl_GetProcAddress("gluTessBeginPolygon");
	gluTessCallback = (gluTessCallbackPROC) extgl_GetProcAddress("gluTessCallback");
	gluTessEndContour = (gluTessEndContourPROC) extgl_GetProcAddress("gluTessEndContour");
	gluTessEndPolygon = (gluTessEndPolygonPROC) extgl_GetProcAddress("gluTessEndPolygon");
	gluTessNormal = (gluTessNormalPROC) extgl_GetProcAddress("gluTessNormal");
	gluTessProperty = (gluTessPropertyPROC) extgl_GetProcAddress("gluTessProperty");
	gluTessVertex = (gluTessVertexPROC) extgl_GetProcAddress("gluTessVertex");
	gluUnProject = (gluUnProjectPROC) extgl_GetProcAddress("gluUnProject");
}

static void extgl_InitGLU13(JNIEnv *env, jobject ext_set)
{
	if (extgl_Extensions.GLU13 != 1)
		return;
	gluUnProject4 = (gluUnProject4PROC) extgl_GetProcAddress("gluUnProject4");
	gluBuild1DMipmapLevels = (gluBuild1DMipmapLevelsPROC) extgl_GetProcAddress("gluBuild1DMipmapLevels");
	gluBuild2DMipmapLevels = (gluBuild2DMipmapLevelsPROC) extgl_GetProcAddress("gluBuild2DMipmapLevels");
	gluBuild3DMipmapLevels = (gluBuild3DMipmapLevelsPROC) extgl_GetProcAddress("gluBuild3DMipmapLevels");
	gluBuild3DMipmaps = (gluBuild3DMipmapsPROC) extgl_GetProcAddress("gluBuild3DMipmaps");
	gluNurbsCallbackData = (gluNurbsCallbackDataPROC) extgl_GetProcAddress("gluNurbsCallbackData");
	gluCheckExtension = (gluCheckExtensionPROC) extgl_GetProcAddress("gluCheckExtension");
	EXTGL_SANITY_CHECK(env, ext_set, GLU13)
}

static void extgl_InitEXTNurbsTesselator(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GLU_EXT_nurbs_tessellator)
		return;
	gluNurbsCallbackDataEXT = (gluNurbsCallbackDataEXTPROC) extgl_GetProcAddress("gluNurbsCallbackDataEXT");
	EXTGL_SANITY_CHECK(env, ext_set, GLU_EXT_nurbs_tessellator)
}

#ifdef _X11
static void extgl_InitGLX13(JNIEnv *env, jobject ext_set)
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
	EXTGL_SANITY_CHECK(env, ext_set, GLX13)
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

static void extgl_InitGLXSupportedExtensions(JNIEnv *env, jobject ext_set, Display *disp, int screen)
{
	extgl_Extensions.GLX_EXT_visual_info = GLXQueryExtension(env, ext_set, disp, screen, "GLX_EXT_visual_info");
	extgl_Extensions.GLX_EXT_visual_rating = GLXQueryExtension(env, ext_set, disp, screen, "GLX_EXT_visual_rating");
	extgl_Extensions.GLX_SGI_swap_control = GLXQueryExtension(env, ext_set, disp, screen, "GLX_SGI_swap_control");
}

static void extgl_InitGLXSGISwapControl(JNIEnv *env, jobject ext_set)
{
	if (extgl_Extensions.GLX_SGI_swap_control != 1)
		return;
	glXSwapIntervalSGI = (glXSwapIntervalSGIPROC)extgl_GetProcAddress("glXSwapIntervalSGI");
	EXTGL_SANITY_CHECK(env, ext_set, GLX_SGI_swap_control)
}

bool extgl_InitGLX(JNIEnv *env, jobject ext_set, Display *disp, int screen)
{
	int major, minor;
	/* Assume glx ver >= 1.2 */
	extgl_Extensions.GLX12 = true;
	glXGetProcAddressARB = (glXGetProcAddressARBPROC) dlsym(lib_gl_handle, "glXGetProcAddressARB");
	if (glXGetProcAddressARB == NULL)
		return false;
	if (!extgl_InitGLX12())
		return false;
	extgl_InitGLXSupportedExtensions(env, ext_set, disp, screen);
	if (glXQueryVersion(disp, &major, &minor) != True)
		return false;
	if (major > 1 || (major == 1 && minor >= 3))
		extgl_Extensions.GLX13 = true;
	extgl_InitGLX13(env, ext_set);
	extgl_InitGLXSGISwapControl(env, ext_set);
	return true;
}
#endif

static void extgl_InitOpenGL1_1(void)
{
	glAccum = (glAccumPROC) extgl_GetProcAddress("glAccum");
	glAlphaFunc = (glAlphaFuncPROC) extgl_GetProcAddress("glAlphaFunc");
	glAreTexturesResident = (glAreTexturesResidentPROC) extgl_GetProcAddress("glAreTexturesResident");
	glArrayElement = (glArrayElementPROC) extgl_GetProcAddress("glArrayElement");
	glBegin = (glBeginPROC) extgl_GetProcAddress("glBegin");
	glBindTexture = (glBindTexturePROC) extgl_GetProcAddress("glBindTexture");
	glBitmap = (glBitmapPROC) extgl_GetProcAddress("glBitmap");
	glBlendFunc = (glBlendFuncPROC) extgl_GetProcAddress("glBlendFunc");
	glCallList = (glCallListPROC) extgl_GetProcAddress("glCallList");
	glCallLists = (glCallListsPROC) extgl_GetProcAddress("glCallLists");
	glClear = (glClearPROC) extgl_GetProcAddress("glClear");
	glClearAccum = (glClearAccumPROC) extgl_GetProcAddress("glClearAccum");
	glClearColor = (glClearColorPROC) extgl_GetProcAddress("glClearColor");
	glClearDepth = (glClearDepthPROC) extgl_GetProcAddress("glClearDepth");
	glClearIndex = (glClearIndexPROC) extgl_GetProcAddress("glClearIndex");
	glClearStencil = (glClearStencilPROC) extgl_GetProcAddress("glClearStencil");
	glClipPlane = (glClipPlanePROC) extgl_GetProcAddress("glClipPlane");
	glColor3b = (glColor3bPROC) extgl_GetProcAddress("glColor3b");
	glColor3bv = (glColor3bvPROC) extgl_GetProcAddress("glColor3bv");
	glColor3d = (glColor3dPROC) extgl_GetProcAddress("glColor3d");
	glColor3dv = (glColor3dvPROC) extgl_GetProcAddress("glColor3dv");
	glColor3f = (glColor3fPROC) extgl_GetProcAddress("glColor3f");
	glColor3fv = (glColor3fvPROC) extgl_GetProcAddress("glColor3fv");
	glColor3i = (glColor3iPROC) extgl_GetProcAddress("glColor3i");
	glColor3iv = (glColor3ivPROC) extgl_GetProcAddress("glColor3iv");
	glColor3s = (glColor3sPROC) extgl_GetProcAddress("glColor3s");
	glColor3sv = (glColor3svPROC) extgl_GetProcAddress("glColor3sv");
	glColor3ub = (glColor3ubPROC) extgl_GetProcAddress("glColor3ub");
	glColor3ubv = (glColor3ubvPROC) extgl_GetProcAddress("glColor3ubv");
	glColor3ui = (glColor3uiPROC) extgl_GetProcAddress("glColor3ui");
	glColor3uiv = (glColor3uivPROC) extgl_GetProcAddress("glColor3uiv");
	glColor3us = (glColor3usPROC) extgl_GetProcAddress("glColor3us");
	glColor3usv = (glColor3usvPROC) extgl_GetProcAddress("glColor3usv");
	glColor4b = (glColor4bPROC) extgl_GetProcAddress("glColor4b");
	glColor4bv = (glColor4bvPROC) extgl_GetProcAddress("glColor4bv");
	glColor4d = (glColor4dPROC) extgl_GetProcAddress("glColor4d");
	glColor4dv = (glColor4dvPROC) extgl_GetProcAddress("glColor4dv");
	glColor4f = (glColor4fPROC) extgl_GetProcAddress("glColor4f");
	glColor4fv = (glColor4fvPROC) extgl_GetProcAddress("glColor4fv");
	glColor4i = (glColor4iPROC) extgl_GetProcAddress("glColor4i");
	glColor4iv = (glColor4ivPROC) extgl_GetProcAddress("glColor4iv");
	glColor4s = (glColor4sPROC) extgl_GetProcAddress("glColor4s");
	glColor4sv = (glColor4svPROC) extgl_GetProcAddress("glColor4sv");
	glColor4ub = (glColor4ubPROC) extgl_GetProcAddress("glColor4ub");
	glColor4ubv = (glColor4ubvPROC) extgl_GetProcAddress("glColor4ubv");
	glColor4ui = (glColor4uiPROC) extgl_GetProcAddress("glColor4ui");
	glColor4uiv = (glColor4uivPROC) extgl_GetProcAddress("glColor4uiv");
	glColor4us = (glColor4usPROC) extgl_GetProcAddress("glColor4us");
	glColor4usv = (glColor4usvPROC) extgl_GetProcAddress("glColor4usv");
	glColorMask = (glColorMaskPROC) extgl_GetProcAddress("glColorMask");
	glColorMaterial = (glColorMaterialPROC) extgl_GetProcAddress("glColorMaterial");
	glColorPointer = (glColorPointerPROC) extgl_GetProcAddress("glColorPointer");
	glCopyPixels = (glCopyPixelsPROC) extgl_GetProcAddress("glCopyPixels");
	glCopyTexImage1D = (glCopyTexImage1DPROC) extgl_GetProcAddress("glCopyTexImage1D");
	glCopyTexImage2D = (glCopyTexImage2DPROC) extgl_GetProcAddress("glCopyTexImage2D");
	glCopyTexSubImage1D = (glCopyTexSubImage1DPROC) extgl_GetProcAddress("glCopyTexSubImage1D");
	glCopyTexSubImage2D = (glCopyTexSubImage2DPROC) extgl_GetProcAddress("glCopyTexSubImage2D");
	glCullFace = (glCullFacePROC) extgl_GetProcAddress("glCullFace");
	glDeleteLists = (glDeleteListsPROC) extgl_GetProcAddress("glDeleteLists");
	glDeleteTextures = (glDeleteTexturesPROC) extgl_GetProcAddress("glDeleteTextures");
	glDepthFunc = (glDepthFuncPROC) extgl_GetProcAddress("glDepthFunc");
	glDepthMask = (glDepthMaskPROC) extgl_GetProcAddress("glDepthMask");
	glDepthRange = (glDepthRangePROC) extgl_GetProcAddress("glDepthRange");
	glDisable = (glDisablePROC) extgl_GetProcAddress("glDisable");
	glDisableClientState = (glDisableClientStatePROC) extgl_GetProcAddress("glDisableClientState");
	glDrawArrays = (glDrawArraysPROC) extgl_GetProcAddress("glDrawArrays");
	glDrawBuffer = (glDrawBufferPROC) extgl_GetProcAddress("glDrawBuffer");
	glDrawElements = (glDrawElementsPROC) extgl_GetProcAddress("glDrawElements");
	glDrawPixels = (glDrawPixelsPROC) extgl_GetProcAddress("glDrawPixels");
	glEdgeFlag = (glEdgeFlagPROC) extgl_GetProcAddress("glEdgeFlag");
	glEdgeFlagPointer = (glEdgeFlagPointerPROC) extgl_GetProcAddress("glEdgeFlagPointer");
	glEdgeFlagv = (glEdgeFlagvPROC) extgl_GetProcAddress("glEdgeFlagv");
	glEnable = (glEnablePROC) extgl_GetProcAddress("glEnable");
	glEnableClientState = (glEnableClientStatePROC) extgl_GetProcAddress("glEnableClientState");
	glEnd = (glEndPROC) extgl_GetProcAddress("glEnd");
	glEndList = (glEndListPROC) extgl_GetProcAddress("glEndList");
	glEvalCoord1d = (glEvalCoord1dPROC) extgl_GetProcAddress("glEvalCoord1d");
	glEvalCoord1dv = (glEvalCoord1dvPROC) extgl_GetProcAddress("glEvalCoord1dv");
	glEvalCoord1f = (glEvalCoord1fPROC) extgl_GetProcAddress("glEvalCoord1f");
	glEvalCoord1fv = (glEvalCoord1fvPROC) extgl_GetProcAddress("glEvalCoord1fv");
	glEvalCoord2d = (glEvalCoord2dPROC) extgl_GetProcAddress("glEvalCoord2d");
	glEvalCoord2dv = (glEvalCoord2dvPROC) extgl_GetProcAddress("glEvalCoord2dv");
	glEvalCoord2f = (glEvalCoord2fPROC) extgl_GetProcAddress("glEvalCoord2f");
	glEvalCoord2fv = (glEvalCoord2fvPROC) extgl_GetProcAddress("glEvalCoord2fv");
	glEvalMesh1 = (glEvalMesh1PROC) extgl_GetProcAddress("glEvalMesh1");
	glEvalMesh2 = (glEvalMesh2PROC) extgl_GetProcAddress("glEvalMesh2");
	glEvalPoint1 = (glEvalPoint1PROC) extgl_GetProcAddress("glEvalPoint1");
	glEvalPoint2 = (glEvalPoint2PROC) extgl_GetProcAddress("glEvalPoint2");
	glFeedbackBuffer = (glFeedbackBufferPROC) extgl_GetProcAddress("glFeedbackBuffer");
	glFinish = (glFinishPROC) extgl_GetProcAddress("glFinish");
	glFlush = (glFlushPROC) extgl_GetProcAddress("glFlush");
	glFogf = (glFogfPROC) extgl_GetProcAddress("glFogf");
	glFogfv = (glFogfvPROC) extgl_GetProcAddress("glFogfv");
	glFogi = (glFogiPROC) extgl_GetProcAddress("glFogi");
	glFogiv = (glFogivPROC) extgl_GetProcAddress("glFogiv");
	glFrontFace = (glFrontFacePROC) extgl_GetProcAddress("glFrontFace");
	glFrustum = (glFrustumPROC) extgl_GetProcAddress("glFrustum");
	glGenLists = (glGenListsPROC) extgl_GetProcAddress("glGenLists");
	glGenTextures = (glGenTexturesPROC) extgl_GetProcAddress("glGenTextures");
	glGetBooleanv = (glGetBooleanvPROC) extgl_GetProcAddress("glGetBooleanv");
	glGetClipPlane = (glGetClipPlanePROC) extgl_GetProcAddress("glGetClipPlane");
	glGetDoublev = (glGetDoublevPROC) extgl_GetProcAddress("glGetDoublev");
	glGetError = (glGetErrorPROC) extgl_GetProcAddress("glGetError");
	glGetFloatv = (glGetFloatvPROC) extgl_GetProcAddress("glGetFloatv");
	glGetIntegerv = (glGetIntegervPROC) extgl_GetProcAddress("glGetIntegerv");
	glGetLightfv = (glGetLightfvPROC) extgl_GetProcAddress("glGetLightfv");
	glGetLightiv = (glGetLightivPROC) extgl_GetProcAddress("glGetLightiv");
	glGetMapdv = (glGetMapdvPROC) extgl_GetProcAddress("glGetMapdv");
	glGetMapfv = (glGetMapfvPROC) extgl_GetProcAddress("glGetMapfv");
	glGetMapiv = (glGetMapivPROC) extgl_GetProcAddress("glGetMapiv");
	glGetMaterialfv = (glGetMaterialfvPROC) extgl_GetProcAddress("glGetMaterialfv");
	glGetMaterialiv = (glGetMaterialivPROC) extgl_GetProcAddress("glGetMaterialiv");
	glGetPixelMapfv = (glGetPixelMapfvPROC) extgl_GetProcAddress("glGetPixelMapfv");
	glGetPixelMapuiv = (glGetPixelMapuivPROC) extgl_GetProcAddress("glGetPixelMapuiv");
	glGetPixelMapusv = (glGetPixelMapusvPROC) extgl_GetProcAddress("glGetPixelMapusv");
	glGetPointerv = (glGetPointervPROC) extgl_GetProcAddress("glGetPointerv");
	glGetPolygonStipple = (glGetPolygonStipplePROC) extgl_GetProcAddress("glGetPolygonStipple");
	glGetString = (glGetStringPROC) extgl_GetProcAddress("glGetString");
	glGetTexEnvfv = (glGetTexEnvfvPROC) extgl_GetProcAddress("glGetTexEnvfv");
	glGetTexEnviv = (glGetTexEnvivPROC) extgl_GetProcAddress("glGetTexEnviv");
	glGetTexGendv = (glGetTexGendvPROC) extgl_GetProcAddress("glGetTexGendv");
	glGetTexGenfv = (glGetTexGenfvPROC) extgl_GetProcAddress("glGetTexGenfv");
	glGetTexGeniv = (glGetTexGenivPROC) extgl_GetProcAddress("glGetTexGeniv");
	glGetTexImage = (glGetTexImagePROC) extgl_GetProcAddress("glGetTexImage");
	glGetTexLevelParameterfv = (glGetTexLevelParameterfvPROC) extgl_GetProcAddress("glGetTexLevelParameterfv");
	glGetTexLevelParameteriv = (glGetTexLevelParameterivPROC) extgl_GetProcAddress("glGetTexLevelParameteriv");
	glGetTexParameterfv = (glGetTexParameterfvPROC) extgl_GetProcAddress("glGetTexParameterfv");
	glGetTexParameteriv = (glGetTexParameterivPROC) extgl_GetProcAddress("glGetTexParameteriv");
	glHint = (glHintPROC) extgl_GetProcAddress("glHint");
	glIndexMask = (glIndexMaskPROC) extgl_GetProcAddress("glIndexMask");
	glIndexPointer = (glIndexPointerPROC) extgl_GetProcAddress("glIndexPointer");
	glIndexd = (glIndexdPROC) extgl_GetProcAddress("glIndexd");
	glIndexdv = (glIndexdvPROC) extgl_GetProcAddress("glIndexdv");
	glIndexf = (glIndexfPROC) extgl_GetProcAddress("glIndexf");
	glIndexfv = (glIndexfvPROC) extgl_GetProcAddress("glIndexfv");
	glIndexi = (glIndexiPROC) extgl_GetProcAddress("glIndexi");
	glIndexiv = (glIndexivPROC) extgl_GetProcAddress("glIndexiv");
	glIndexs = (glIndexsPROC) extgl_GetProcAddress("glIndexs");
	glIndexsv = (glIndexsvPROC) extgl_GetProcAddress("glIndexsv");
	glIndexub = (glIndexubPROC) extgl_GetProcAddress("glIndexub");
	glIndexubv = (glIndexubvPROC) extgl_GetProcAddress("glIndexubv");
	glInitNames = (glInitNamesPROC) extgl_GetProcAddress("glInitNames");
	glInterleavedArrays = (glInterleavedArraysPROC) extgl_GetProcAddress("glInterleavedArrays");
	glIsEnabled = (glIsEnabledPROC) extgl_GetProcAddress("glIsEnabled");
	glIsList = (glIsListPROC) extgl_GetProcAddress("glIsList");
	glIsTexture = (glIsTexturePROC) extgl_GetProcAddress("glIsTexture");
	glLightModelf = (glLightModelfPROC) extgl_GetProcAddress("glLightModelf");
	glLightModelfv = (glLightModelfvPROC) extgl_GetProcAddress("glLightModelfv");
	glLightModeli = (glLightModeliPROC) extgl_GetProcAddress("glLightModeli");
	glLightModeliv = (glLightModelivPROC) extgl_GetProcAddress("glLightModeliv");
	glLightf = (glLightfPROC) extgl_GetProcAddress("glLightf");
	glLightfv = (glLightfvPROC) extgl_GetProcAddress("glLightfv");
	glLighti = (glLightiPROC) extgl_GetProcAddress("glLighti");
	glLightiv = (glLightivPROC) extgl_GetProcAddress("glLightiv");
	glLineStipple = (glLineStipplePROC) extgl_GetProcAddress("glLineStipple");
	glLineWidth = (glLineWidthPROC) extgl_GetProcAddress("glLineWidth");
	glListBase = (glListBasePROC) extgl_GetProcAddress("glListBase");
	glLoadIdentity = (glLoadIdentityPROC) extgl_GetProcAddress("glLoadIdentity");
	glLoadMatrixd = (glLoadMatrixdPROC) extgl_GetProcAddress("glLoadMatrixd");
	glLoadMatrixf = (glLoadMatrixfPROC) extgl_GetProcAddress("glLoadMatrixf");
	glLoadName = (glLoadNamePROC) extgl_GetProcAddress("glLoadName");
	glLogicOp = (glLogicOpPROC) extgl_GetProcAddress("glLogicOp");
	glMap1d = (glMap1dPROC) extgl_GetProcAddress("glMap1d");
	glMap1f = (glMap1fPROC) extgl_GetProcAddress("glMap1f");
	glMap2d = (glMap2dPROC) extgl_GetProcAddress("glMap2d");
	glMap2f = (glMap2fPROC) extgl_GetProcAddress("glMap2f");
	glMapGrid1d = (glMapGrid1dPROC) extgl_GetProcAddress("glMapGrid1d");
	glMapGrid1f = (glMapGrid1fPROC) extgl_GetProcAddress("glMapGrid1f");
	glMapGrid2d = (glMapGrid2dPROC) extgl_GetProcAddress("glMapGrid2d");
	glMapGrid2f = (glMapGrid2fPROC) extgl_GetProcAddress("glMapGrid2f");
	glMaterialf = (glMaterialfPROC) extgl_GetProcAddress("glMaterialf");
	glMaterialfv = (glMaterialfvPROC) extgl_GetProcAddress("glMaterialfv");
	glMateriali = (glMaterialiPROC) extgl_GetProcAddress("glMateriali");
	glMaterialiv = (glMaterialivPROC) extgl_GetProcAddress("glMaterialiv");
	glMatrixMode = (glMatrixModePROC) extgl_GetProcAddress("glMatrixMode");
	glMultMatrixd = (glMultMatrixdPROC) extgl_GetProcAddress("glMultMatrixd");
	glMultMatrixf = (glMultMatrixfPROC) extgl_GetProcAddress("glMultMatrixf");
	glNewList = (glNewListPROC) extgl_GetProcAddress("glNewList");
	glNormal3b = (glNormal3bPROC) extgl_GetProcAddress("glNormal3b");
	glNormal3bv = (glNormal3bvPROC) extgl_GetProcAddress("glNormal3bv");
	glNormal3d = (glNormal3dPROC) extgl_GetProcAddress("glNormal3d");
	glNormal3dv = (glNormal3dvPROC) extgl_GetProcAddress("glNormal3dv");
	glNormal3f = (glNormal3fPROC) extgl_GetProcAddress("glNormal3f");
	glNormal3fv = (glNormal3fvPROC) extgl_GetProcAddress("glNormal3fv");
	glNormal3i = (glNormal3iPROC) extgl_GetProcAddress("glNormal3i");
	glNormal3iv = (glNormal3ivPROC) extgl_GetProcAddress("glNormal3iv");
	glNormal3s = (glNormal3sPROC) extgl_GetProcAddress("glNormal3s");
	glNormal3sv = (glNormal3svPROC) extgl_GetProcAddress("glNormal3sv");
	glNormalPointer = (glNormalPointerPROC) extgl_GetProcAddress("glNormalPointer");
	glOrtho = (glOrthoPROC) extgl_GetProcAddress("glOrtho");
	glPassThrough = (glPassThroughPROC) extgl_GetProcAddress("glPassThrough");
	glPixelMapfv = (glPixelMapfvPROC) extgl_GetProcAddress("glPixelMapfv");
	glPixelMapuiv = (glPixelMapuivPROC) extgl_GetProcAddress("glPixelMapuiv");
	glPixelMapusv = (glPixelMapusvPROC) extgl_GetProcAddress("glPixelMapusv");
	glPixelStoref = (glPixelStorefPROC) extgl_GetProcAddress("glPixelStoref");
	glPixelStorei = (glPixelStoreiPROC) extgl_GetProcAddress("glPixelStorei");
	glPixelTransferf = (glPixelTransferfPROC) extgl_GetProcAddress("glPixelTransferf");
	glPixelTransferi = (glPixelTransferiPROC) extgl_GetProcAddress("glPixelTransferi");
	glPixelZoom = (glPixelZoomPROC) extgl_GetProcAddress("glPixelZoom");
	glPointSize = (glPointSizePROC) extgl_GetProcAddress("glPointSize");
	glPolygonMode = (glPolygonModePROC) extgl_GetProcAddress("glPolygonMode");
	glPolygonOffset = (glPolygonOffsetPROC) extgl_GetProcAddress("glPolygonOffset");
	glPolygonStipple = (glPolygonStipplePROC) extgl_GetProcAddress("glPolygonStipple");
	glPopAttrib = (glPopAttribPROC) extgl_GetProcAddress("glPopAttrib");
	glPopClientAttrib = (glPopClientAttribPROC) extgl_GetProcAddress("glPopClientAttrib");
	glPopMatrix = (glPopMatrixPROC) extgl_GetProcAddress("glPopMatrix");
	glPopName = (glPopNamePROC) extgl_GetProcAddress("glPopName");
	glPrioritizeTextures = (glPrioritizeTexturesPROC) extgl_GetProcAddress("glPrioritizeTextures");
	glPushAttrib = (glPushAttribPROC) extgl_GetProcAddress("glPushAttrib");
	glPushClientAttrib = (glPushClientAttribPROC) extgl_GetProcAddress("glPushClientAttrib");
	glPushMatrix = (glPushMatrixPROC) extgl_GetProcAddress("glPushMatrix");
	glPushName = (glPushNamePROC) extgl_GetProcAddress("glPushName");
	glRasterPos2d = (glRasterPos2dPROC) extgl_GetProcAddress("glRasterPos2d");
	glRasterPos2dv = (glRasterPos2dvPROC) extgl_GetProcAddress("glRasterPos2dv");
	glRasterPos2f = (glRasterPos2fPROC) extgl_GetProcAddress("glRasterPos2f");
	glRasterPos2fv = (glRasterPos2fvPROC) extgl_GetProcAddress("glRasterPos2fv");
	glRasterPos2i = (glRasterPos2iPROC) extgl_GetProcAddress("glRasterPos2i");
	glRasterPos2iv = (glRasterPos2ivPROC) extgl_GetProcAddress("glRasterPos2iv");
	glRasterPos2s = (glRasterPos2sPROC) extgl_GetProcAddress("glRasterPos2s");
	glRasterPos2sv = (glRasterPos2svPROC) extgl_GetProcAddress("glRasterPos2sv");
	glRasterPos3d = (glRasterPos3dPROC) extgl_GetProcAddress("glRasterPos3d");
	glRasterPos3dv = (glRasterPos3dvPROC) extgl_GetProcAddress("glRasterPos3dv");
	glRasterPos3f = (glRasterPos3fPROC) extgl_GetProcAddress("glRasterPos3f");
	glRasterPos3fv = (glRasterPos3fvPROC) extgl_GetProcAddress("glRasterPos3fv");
	glRasterPos3i = (glRasterPos3iPROC) extgl_GetProcAddress("glRasterPos3i");

	glRasterPos3iv = (glRasterPos3ivPROC) extgl_GetProcAddress("glRasterPos3iv");
	glRasterPos3s = (glRasterPos3sPROC) extgl_GetProcAddress("glRasterPos3s");
	glRasterPos3sv = (glRasterPos3svPROC) extgl_GetProcAddress("glRasterPos3sv");
	glRasterPos4d = (glRasterPos4dPROC) extgl_GetProcAddress("glRasterPos4d");
	glRasterPos4dv = (glRasterPos4dvPROC) extgl_GetProcAddress("glRasterPos4dv");
	glRasterPos4f = (glRasterPos4fPROC) extgl_GetProcAddress("glRasterPos4f");
	glRasterPos4fv = (glRasterPos4fvPROC) extgl_GetProcAddress("glRasterPos4fv");
	glRasterPos4i = (glRasterPos4iPROC) extgl_GetProcAddress("glRasterPos4i");
	glRasterPos4iv = (glRasterPos4ivPROC) extgl_GetProcAddress("glRasterPos4iv");
	glRasterPos4s = (glRasterPos4sPROC) extgl_GetProcAddress("glRasterPos4s");
	glRasterPos4sv = (glRasterPos4svPROC) extgl_GetProcAddress("glRasterPos4sv");
	glReadBuffer = (glReadBufferPROC) extgl_GetProcAddress("glReadBuffer");
	glReadPixels = (glReadPixelsPROC) extgl_GetProcAddress("glReadPixels");
	glRectd = (glRectdPROC) extgl_GetProcAddress("glRectd");
	glRectdv = (glRectdvPROC) extgl_GetProcAddress("glRectdv");
	glRectf = (glRectfPROC) extgl_GetProcAddress("glRectf");
	glRectfv = (glRectfvPROC) extgl_GetProcAddress("glRectfv");
	glRecti = (glRectiPROC) extgl_GetProcAddress("glRecti");
	glRectiv = (glRectivPROC) extgl_GetProcAddress("glRectiv");
	glRects = (glRectsPROC) extgl_GetProcAddress("glRects");
	glRectsv = (glRectsvPROC) extgl_GetProcAddress("glRectsv");
	glRenderMode = (glRenderModePROC) extgl_GetProcAddress("glRenderMode");
	glRotated = (glRotatedPROC) extgl_GetProcAddress("glRotated");
	glRotatef = (glRotatefPROC) extgl_GetProcAddress("glRotatef");
	glScaled = (glScaledPROC) extgl_GetProcAddress("glScaled");
	glScalef = (glScalefPROC) extgl_GetProcAddress("glScalef");
	glScissor = (glScissorPROC) extgl_GetProcAddress("glScissor");
	glSelectBuffer = (glSelectBufferPROC) extgl_GetProcAddress("glSelectBuffer");
	glShadeModel = (glShadeModelPROC) extgl_GetProcAddress("glShadeModel");
	glStencilFunc = (glStencilFuncPROC) extgl_GetProcAddress("glStencilFunc");
	glStencilMask = (glStencilMaskPROC) extgl_GetProcAddress("glStencilMask");
	glStencilOp = (glStencilOpPROC) extgl_GetProcAddress("glStencilOp");
	glTexCoord1d = (glTexCoord1dPROC) extgl_GetProcAddress("glTexCoord1d");
	glTexCoord1dv = (glTexCoord1dvPROC) extgl_GetProcAddress("glTexCoord1dv");
	glTexCoord1f = (glTexCoord1fPROC) extgl_GetProcAddress("glTexCoord1f");
	glTexCoord1fv = (glTexCoord1fvPROC) extgl_GetProcAddress("glTexCoord1fv");
	glTexCoord1i = (glTexCoord1iPROC) extgl_GetProcAddress("glTexCoord1i");
	glTexCoord1iv = (glTexCoord1ivPROC) extgl_GetProcAddress("glTexCoord1iv");
	glTexCoord1s = (glTexCoord1sPROC) extgl_GetProcAddress("glTexCoord1s");
	glTexCoord1sv = (glTexCoord1svPROC) extgl_GetProcAddress("glTexCoord1sv");
	glTexCoord2d = (glTexCoord2dPROC) extgl_GetProcAddress("glTexCoord2d");
	glTexCoord2dv = (glTexCoord2dvPROC) extgl_GetProcAddress("glTexCoord2dv");
	glTexCoord2f = (glTexCoord2fPROC) extgl_GetProcAddress("glTexCoord2f");
	glTexCoord2fv = (glTexCoord2fvPROC) extgl_GetProcAddress("glTexCoord2fv");
	glTexCoord2i = (glTexCoord2iPROC) extgl_GetProcAddress("glTexCoord2i");
	glTexCoord2iv = (glTexCoord2ivPROC) extgl_GetProcAddress("glTexCoord2iv");
	glTexCoord2s = (glTexCoord2sPROC) extgl_GetProcAddress("glTexCoord2s");
	glTexCoord2sv = (glTexCoord2svPROC) extgl_GetProcAddress("glTexCoord2sv");
	glTexCoord3d = (glTexCoord3dPROC) extgl_GetProcAddress("glTexCoord3d");
	glTexCoord3dv = (glTexCoord3dvPROC) extgl_GetProcAddress("glTexCoord3dv");
	glTexCoord3f = (glTexCoord3fPROC) extgl_GetProcAddress("glTexCoord3f");
	glTexCoord3fv = (glTexCoord3fvPROC) extgl_GetProcAddress("glTexCoord3fv");
	glTexCoord3i = (glTexCoord3iPROC) extgl_GetProcAddress("glTexCoord3i");
	glTexCoord3iv = (glTexCoord3ivPROC) extgl_GetProcAddress("glTexCoord3iv");
	glTexCoord3s = (glTexCoord3sPROC) extgl_GetProcAddress("glTexCoord3s");
	glTexCoord3sv = (glTexCoord3svPROC) extgl_GetProcAddress("glTexCoord3sv");
	glTexCoord4d = (glTexCoord4dPROC) extgl_GetProcAddress("glTexCoord4d");
	glTexCoord4dv = (glTexCoord4dvPROC) extgl_GetProcAddress("glTexCoord4dv");
	glTexCoord4f = (glTexCoord4fPROC) extgl_GetProcAddress("glTexCoord4f");
	glTexCoord4fv = (glTexCoord4fvPROC) extgl_GetProcAddress("glTexCoord4fv");
	glTexCoord4i = (glTexCoord4iPROC) extgl_GetProcAddress("glTexCoord4i");
	glTexCoord4iv = (glTexCoord4ivPROC) extgl_GetProcAddress("glTexCoord4iv");
	glTexCoord4s = (glTexCoord4sPROC) extgl_GetProcAddress("glTexCoord4s");
	glTexCoord4sv = (glTexCoord4svPROC) extgl_GetProcAddress("glTexCoord4sv");
	glTexCoordPointer = (glTexCoordPointerPROC) extgl_GetProcAddress("glTexCoordPointer");
	glTexEnvf = (glTexEnvfPROC) extgl_GetProcAddress("glTexEnvf");
	glTexEnvfv = (glTexEnvfvPROC) extgl_GetProcAddress("glTexEnvfv");
	glTexEnvi = (glTexEnviPROC) extgl_GetProcAddress("glTexEnvi");
	glTexEnviv = (glTexEnvivPROC) extgl_GetProcAddress("glTexEnviv");
	glTexGend = (glTexGendPROC) extgl_GetProcAddress("glTexGend");
	glTexGendv = (glTexGendvPROC) extgl_GetProcAddress("glTexGendv");
	glTexGenf = (glTexGenfPROC) extgl_GetProcAddress("glTexGenf");
	glTexGenfv = (glTexGenfvPROC) extgl_GetProcAddress("glTexGenfv");
	glTexGeni = (glTexGeniPROC) extgl_GetProcAddress("glTexGeni");
	glTexGeniv = (glTexGenivPROC) extgl_GetProcAddress("glTexGeniv");
	glTexImage1D = (glTexImage1DPROC) extgl_GetProcAddress("glTexImage1D");
	glTexImage2D = (glTexImage2DPROC) extgl_GetProcAddress("glTexImage2D");
	glTexParameterf = (glTexParameterfPROC) extgl_GetProcAddress("glTexParameterf");
	glTexParameterfv = (glTexParameterfvPROC) extgl_GetProcAddress("glTexParameterfv");
	glTexParameteri = (glTexParameteriPROC) extgl_GetProcAddress("glTexParameteri");
	glTexParameteriv = (glTexParameterivPROC) extgl_GetProcAddress("glTexParameteriv");
	glTexSubImage1D = (glTexSubImage1DPROC) extgl_GetProcAddress("glTexSubImage1D");
	glTexSubImage2D = (glTexSubImage2DPROC) extgl_GetProcAddress("glTexSubImage2D");
	glTranslated = (glTranslatedPROC) extgl_GetProcAddress("glTranslated");
	glTranslatef = (glTranslatefPROC) extgl_GetProcAddress("glTranslatef");
	glVertex2d = (glVertex2dPROC) extgl_GetProcAddress("glVertex2d");
	glVertex2dv = (glVertex2dvPROC) extgl_GetProcAddress("glVertex2dv");
	glVertex2f = (glVertex2fPROC) extgl_GetProcAddress("glVertex2f");
	glVertex2fv = (glVertex2fvPROC) extgl_GetProcAddress("glVertex2fv");
	glVertex2i = (glVertex2iPROC) extgl_GetProcAddress("glVertex2i");
	glVertex2iv = (glVertex2ivPROC) extgl_GetProcAddress("glVertex2iv");
	glVertex2s = (glVertex2sPROC) extgl_GetProcAddress("glVertex2s");
	glVertex2sv = (glVertex2svPROC) extgl_GetProcAddress("glVertex2sv");
	glVertex3d = (glVertex3dPROC) extgl_GetProcAddress("glVertex3d");
	glVertex3dv = (glVertex3dvPROC) extgl_GetProcAddress("glVertex3dv");
	glVertex3f = (glVertex3fPROC) extgl_GetProcAddress("glVertex3f");
	glVertex3fv = (glVertex3fvPROC) extgl_GetProcAddress("glVertex3fv");
	glVertex3i = (glVertex3iPROC) extgl_GetProcAddress("glVertex3i");
	glVertex3iv = (glVertex3ivPROC) extgl_GetProcAddress("glVertex3iv");
	glVertex3s = (glVertex3sPROC) extgl_GetProcAddress("glVertex3s");
	glVertex3sv = (glVertex3svPROC) extgl_GetProcAddress("glVertex3sv");
	glVertex4d = (glVertex4dPROC) extgl_GetProcAddress("glVertex4d");
	glVertex4dv = (glVertex4dvPROC) extgl_GetProcAddress("glVertex4dv");
	glVertex4f = (glVertex4fPROC) extgl_GetProcAddress("glVertex4f");
	glVertex4fv = (glVertex4fvPROC) extgl_GetProcAddress("glVertex4fv");
	glVertex4i = (glVertex4iPROC) extgl_GetProcAddress("glVertex4i");
	glVertex4iv = (glVertex4ivPROC) extgl_GetProcAddress("glVertex4iv");
	glVertex4s = (glVertex4sPROC) extgl_GetProcAddress("glVertex4s");
	glVertex4sv = (glVertex4svPROC) extgl_GetProcAddress("glVertex4sv");
	glVertexPointer = (glVertexPointerPROC) extgl_GetProcAddress("glVertexPointer");
	glViewport = (glViewportPROC) extgl_GetProcAddress("glViewport");
}

static void extgl_InitOpenGL1_2(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.OpenGL12)
		return;
	glTexImage3D = (glTexImage3DPROC) extgl_GetProcAddress("glTexImage3D");
	glTexSubImage3D = (glTexSubImage3DPROC) extgl_GetProcAddress("glTexSubImage3D");
	glCopyTexSubImage3D = (glCopyTexSubImage3DPROC) extgl_GetProcAddress("glCopyTexSubImage3D");
	glDrawRangeElements = (glDrawRangeElementsPROC) extgl_GetProcAddress("glDrawRangeElements");
	EXTGL_SANITY_CHECK(env, ext_set, OpenGL12)
}

static void extgl_InitARBImaging(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_imaging)
		return;
	glBlendColor = (glBlendColorPROC) extgl_GetProcAddress("glBlendColor");
	glBlendEquation = (glBlendEquationPROC) extgl_GetProcAddress("glBlendEquation");
	glColorTable = (glColorTablePROC) extgl_GetProcAddress("glColorTable");
	glColorTableParameterfv = (glColorTableParameterfvPROC) extgl_GetProcAddress("glColorTableParameterfv");
	glColorTableParameteriv = (glColorTableParameterivPROC) extgl_GetProcAddress("glColorTableParameteriv");
	glCopyColorTable = (glCopyColorTablePROC) extgl_GetProcAddress("glCopyColorTable");
	glGetColorTable = (glGetColorTablePROC) extgl_GetProcAddress("glGetColorTable");
	glGetColorTableParameterfv = (glGetColorTableParameterfvPROC) extgl_GetProcAddress("glGetColorTableParameterfv");
	glGetColorTableParameteriv = (glGetColorTableParameterivPROC) extgl_GetProcAddress("glGetColorTableParameteriv");
	glColorSubTable = (glColorSubTablePROC) extgl_GetProcAddress("glColorSubTable");
	glCopyColorSubTable = (glCopyColorSubTablePROC) extgl_GetProcAddress("glCopyColorSubTable");
	glConvolutionFilter1D = (glConvolutionFilter1DPROC) extgl_GetProcAddress("glConvolutionFilter1D");
	glConvolutionFilter2D = (glConvolutionFilter2DPROC) extgl_GetProcAddress("glConvolutionFilter2D");
	glConvolutionParameterf = (glConvolutionParameterfPROC) extgl_GetProcAddress("glConvolutionParameterf");
	glConvolutionParameterfv = (glConvolutionParameterfvPROC) extgl_GetProcAddress("glConvolutionParameterfv");
	glConvolutionParameteri = (glConvolutionParameteriPROC) extgl_GetProcAddress("glConvolutionParameteri");
	glConvolutionParameteriv = (glConvolutionParameterivPROC) extgl_GetProcAddress("glConvolutionParameteriv");
	glCopyConvolutionFilter1D = (glCopyConvolutionFilter1DPROC) extgl_GetProcAddress("glCopyConvolutionFilter1D");
	glCopyConvolutionFilter2D = (glCopyConvolutionFilter2DPROC) extgl_GetProcAddress("glCopyConvolutionFilter2D");
	glGetConvolutionFilter = (glGetConvolutionFilterPROC) extgl_GetProcAddress("glGetConvolutionFilter");
	glGetConvolutionParameterfv = (glGetConvolutionParameterfvPROC) extgl_GetProcAddress("glGetConvolutionParameterfv");
	glGetConvolutionParameteriv = (glGetConvolutionParameterivPROC) extgl_GetProcAddress("glGetConvolutionParameteriv");
	glGetSeparableFilter = (glGetSeparableFilterPROC) extgl_GetProcAddress("glGetSeparableFilter");
	glSeparableFilter2D = (glSeparableFilter2DPROC) extgl_GetProcAddress("glSeparableFilter2D");
	glGetHistogram = (glGetHistogramPROC) extgl_GetProcAddress("glGetHistogram");
	glGetHistogramParameterfv = (glGetHistogramParameterfvPROC) extgl_GetProcAddress("glGetHistogramParameterfv");
	glGetHistogramParameteriv = (glGetHistogramParameterivPROC) extgl_GetProcAddress("glGetHistogramParameteriv");
	glGetMinmax = (glGetMinmaxPROC) extgl_GetProcAddress("glGetMinmax");
	glGetMinmaxParameterfv = (glGetMinmaxParameterfvPROC) extgl_GetProcAddress("glGetMinmaxParameterfv");
	glGetMinmaxParameteriv = (glGetMinmaxParameterivPROC) extgl_GetProcAddress("glGetMinmaxParameteriv");
	glHistogram = (glHistogramPROC) extgl_GetProcAddress("glHistogram");
	glMinmax = (glMinmaxPROC) extgl_GetProcAddress("glMinmax");
	glResetHistogram = (glResetHistogramPROC) extgl_GetProcAddress("glResetHistogram");
	glResetMinmax = (glResetMinmaxPROC) extgl_GetProcAddress("glResetMinmax");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_imaging)
}

static void extgl_InitOpenGL1_3(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.OpenGL13)
		return;
	glActiveTexture = (glActiveTexturePROC) extgl_GetProcAddress("glActiveTexture");
	glClientActiveTexture = (glClientActiveTexturePROC) extgl_GetProcAddress("glClientActiveTexture");

	glMultiTexCoord1d = (glMultiTexCoord1dPROC) extgl_GetProcAddress("glMultiTexCoord1d");
	glMultiTexCoord1dv = (glMultiTexCoord1dvPROC) extgl_GetProcAddress("glMultiTexCoord1dv");
	glMultiTexCoord1f = (glMultiTexCoord1fPROC) extgl_GetProcAddress("glMultiTexCoord1f");
	glMultiTexCoord1fv = (glMultiTexCoord1fvPROC) extgl_GetProcAddress("glMultiTexCoord1fv");
	glMultiTexCoord1i = (glMultiTexCoord1iPROC) extgl_GetProcAddress("glMultiTexCoord1i");
	glMultiTexCoord1iv = (glMultiTexCoord1ivPROC) extgl_GetProcAddress("glMultiTexCoord1iv");
	glMultiTexCoord1s = (glMultiTexCoord1sPROC) extgl_GetProcAddress("glMultiTexCoord1s");
	glMultiTexCoord1sv = (glMultiTexCoord1svPROC) extgl_GetProcAddress("glMultiTexCoord1sv");

	glMultiTexCoord2d = (glMultiTexCoord2dPROC) extgl_GetProcAddress("glMultiTexCoord2d");
	glMultiTexCoord2dv = (glMultiTexCoord2dvPROC) extgl_GetProcAddress("glMultiTexCoord2dv");
	glMultiTexCoord2f = (glMultiTexCoord2fPROC) extgl_GetProcAddress("glMultiTexCoord2f");
	glMultiTexCoord2fv = (glMultiTexCoord2fvPROC) extgl_GetProcAddress("glMultiTexCoord2fv");
	glMultiTexCoord2i = (glMultiTexCoord2iPROC) extgl_GetProcAddress("glMultiTexCoord2i");
	glMultiTexCoord2iv = (glMultiTexCoord2ivPROC) extgl_GetProcAddress("glMultiTexCoord2iv");
	glMultiTexCoord2s = (glMultiTexCoord2sPROC) extgl_GetProcAddress("glMultiTexCoord2s");
	glMultiTexCoord2sv = (glMultiTexCoord2svPROC) extgl_GetProcAddress("glMultiTexCoord2sv");

	glMultiTexCoord3d = (glMultiTexCoord3dPROC) extgl_GetProcAddress("glMultiTexCoord3d");
	glMultiTexCoord3dv = (glMultiTexCoord3dvPROC) extgl_GetProcAddress("glMultiTexCoord3dv");
	glMultiTexCoord3f = (glMultiTexCoord3fPROC) extgl_GetProcAddress("glMultiTexCoord3f");
	glMultiTexCoord3fv = (glMultiTexCoord3fvPROC) extgl_GetProcAddress("glMultiTexCoord3fv");
	glMultiTexCoord3i = (glMultiTexCoord3iPROC) extgl_GetProcAddress("glMultiTexCoord3i");
	glMultiTexCoord3iv = (glMultiTexCoord3ivPROC) extgl_GetProcAddress("glMultiTexCoord3iv");
	glMultiTexCoord3s = (glMultiTexCoord3sPROC) extgl_GetProcAddress("glMultiTexCoord3s");
	glMultiTexCoord3sv = (glMultiTexCoord3svPROC) extgl_GetProcAddress("glMultiTexCoord3sv");

	glMultiTexCoord4d = (glMultiTexCoord4dPROC) extgl_GetProcAddress("glMultiTexCoord4d");
	glMultiTexCoord4dv = (glMultiTexCoord4dvPROC) extgl_GetProcAddress("glMultiTexCoord4dv");
	glMultiTexCoord4f = (glMultiTexCoord4fPROC) extgl_GetProcAddress("glMultiTexCoord4f");
	glMultiTexCoord4fv = (glMultiTexCoord4fvPROC) extgl_GetProcAddress("glMultiTexCoord4fv");
	glMultiTexCoord4i = (glMultiTexCoord4iPROC) extgl_GetProcAddress("glMultiTexCoord4i");
	glMultiTexCoord4iv = (glMultiTexCoord4ivPROC) extgl_GetProcAddress("glMultiTexCoord4iv");
	glMultiTexCoord4s = (glMultiTexCoord4sPROC) extgl_GetProcAddress("glMultiTexCoord4s");
	glMultiTexCoord4sv = (glMultiTexCoord4svPROC) extgl_GetProcAddress("glMultiTexCoord4sv");

	glLoadTransposeMatrixf = (glLoadTransposeMatrixfPROC) extgl_GetProcAddress("glLoadTransposeMatrixf");
	glLoadTransposeMatrixd = (glLoadTransposeMatrixdPROC) extgl_GetProcAddress("glLoadTransposeMatrixd");
	glMultTransposeMatrixf = (glMultTransposeMatrixfPROC) extgl_GetProcAddress("glMultTransposeMatrixf");
	glMultTransposeMatrixd = (glMultTransposeMatrixdPROC) extgl_GetProcAddress("glMultTransposeMatrixd");
	glCompressedTexImage3D = (glCompressedTexImage3DPROC) extgl_GetProcAddress("glCompressedTexImage3D");
	glCompressedTexImage2D = (glCompressedTexImage2DPROC) extgl_GetProcAddress("glCompressedTexImage2D");
	glCompressedTexImage1D = (glCompressedTexImage1DPROC) extgl_GetProcAddress("glCompressedTexImage1D");
	glCompressedTexSubImage3D = (glCompressedTexSubImage3DPROC) extgl_GetProcAddress("glCompressedTexSubImage3D");
	glCompressedTexSubImage2D = (glCompressedTexSubImage2DPROC) extgl_GetProcAddress("glCompressedTexSubImage2D");
	glCompressedTexSubImage1D = (glCompressedTexSubImage1DPROC) extgl_GetProcAddress("glCompressedTexSubImage1D");
	glGetCompressedTexImage = (glGetCompressedTexImagePROC) extgl_GetProcAddress("glGetCompressedTexImage");

	glSampleCoverage = (glSampleCoveragePROC) extgl_GetProcAddress("glSampleCoverage");
	EXTGL_SANITY_CHECK(env, ext_set, OpenGL13)
}

static void extgl_InitOpenGL1_4(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.OpenGL14)
		return;
	glBlendColor = (glBlendColorPROC) extgl_GetProcAddress("glBlendColor");
	glBlendEquation = (glBlendEquationPROC) extgl_GetProcAddress("glBlendEquation");
	glFogCoordf = (glFogCoordfPROC) extgl_GetProcAddress("glFogCoordf");
	glFogCoordfv = (glFogCoordfvPROC) extgl_GetProcAddress("glFogCoordfv");
	glFogCoordd = (glFogCoorddPROC) extgl_GetProcAddress("glFogCoordd");
	glFogCoorddv = (glFogCoorddvPROC) extgl_GetProcAddress("glFogCoorddv");
	glFogCoordPointer = (glFogCoordPointerPROC) extgl_GetProcAddress("glFogCoordPointer");
	glMultiDrawArrays = (glMultiDrawArraysPROC) extgl_GetProcAddress("glMultiDrawArrays");
	glMultiDrawElements = (glMultiDrawElementsPROC) extgl_GetProcAddress("glMultiDrawElements");
	glPointParameterf = (glPointParameterfPROC) extgl_GetProcAddress("glPointParameterf");
	glPointParameterfv = (glPointParameterfvPROC) extgl_GetProcAddress("glPointParameterfv");
	glSecondaryColor3b = (glSecondaryColor3bPROC) extgl_GetProcAddress("glSecondaryColor3b");
	glSecondaryColor3bv = (glSecondaryColor3bvPROC) extgl_GetProcAddress("glSecondaryColor3bv");
	glSecondaryColor3d = (glSecondaryColor3dPROC) extgl_GetProcAddress("glSecondaryColor3d");
	glSecondaryColor3dv = (glSecondaryColor3dvPROC) extgl_GetProcAddress("glSecondaryColor3dv");
	glSecondaryColor3f = (glSecondaryColor3fPROC) extgl_GetProcAddress("glSecondaryColor3f");
	glSecondaryColor3fv = (glSecondaryColor3fvPROC) extgl_GetProcAddress("glSecondaryColor3fv");
	glSecondaryColor3i = (glSecondaryColor3iPROC) extgl_GetProcAddress("glSecondaryColor3i");
	glSecondaryColor3iv = (glSecondaryColor3ivPROC) extgl_GetProcAddress("glSecondaryColor3iv");
	glSecondaryColor3s = (glSecondaryColor3sPROC) extgl_GetProcAddress("glSecondaryColor3s");
	glSecondaryColor3sv = (glSecondaryColor3svPROC) extgl_GetProcAddress("glSecondaryColor3sv");
	glSecondaryColor3ub = (glSecondaryColor3ubPROC) extgl_GetProcAddress("glSecondaryColor3ub");
	glSecondaryColor3ubv = (glSecondaryColor3ubvPROC) extgl_GetProcAddress("glSecondaryColor3ubv");
	glSecondaryColor3ui = (glSecondaryColor3uiPROC) extgl_GetProcAddress("glSecondaryColor3ui");
	glSecondaryColor3uiv = (glSecondaryColor3uivPROC) extgl_GetProcAddress("glSecondaryColor3uiv");
	glSecondaryColor3us = (glSecondaryColor3usPROC) extgl_GetProcAddress("glSecondaryColor3us");
	glSecondaryColor3usv = (glSecondaryColor3usvPROC) extgl_GetProcAddress("glSecondaryColor3usv");
	glSecondaryColorPointer = (glSecondaryColorPointerPROC) extgl_GetProcAddress("glSecondaryColorPointer");
	glBlendFuncSeparate = (glBlendFuncSeparatePROC) extgl_GetProcAddress("glBlendFuncSeparate");
	glWindowPos2d = (glWindowPos2dPROC) extgl_GetProcAddress("glWindowPos2d");
	glWindowPos2f = (glWindowPos2fPROC) extgl_GetProcAddress("glWindowPos2f");
	glWindowPos2i = (glWindowPos2iPROC) extgl_GetProcAddress("glWindowPos2i");
	glWindowPos2s = (glWindowPos2sPROC) extgl_GetProcAddress("glWindowPos2s");
	glWindowPos2dv = (glWindowPos2dvPROC) extgl_GetProcAddress("glWindowPos2dv");
	glWindowPos2fv = (glWindowPos2fvPROC) extgl_GetProcAddress("glWindowPos2fv");
	glWindowPos2iv = (glWindowPos2ivPROC) extgl_GetProcAddress("glWindowPos2iv");
	glWindowPos2sv = (glWindowPos2svPROC) extgl_GetProcAddress("glWindowPos2sv");
	glWindowPos3d = (glWindowPos3dPROC) extgl_GetProcAddress("glWindowPos3d");
	glWindowPos3f = (glWindowPos3fPROC) extgl_GetProcAddress("glWindowPos3f");
	glWindowPos3i = (glWindowPos3iPROC) extgl_GetProcAddress("glWindowPos3i");
	glWindowPos3s = (glWindowPos3sPROC) extgl_GetProcAddress("glWindowPos3s");
	glWindowPos3dv = (glWindowPos3dvPROC) extgl_GetProcAddress("glWindowPos3dv");
	glWindowPos3fv = (glWindowPos3fvPROC) extgl_GetProcAddress("glWindowPos3fv");
	glWindowPos3iv = (glWindowPos3ivPROC) extgl_GetProcAddress("glWindowPos3iv");
	glWindowPos3sv = (glWindowPos3svPROC) extgl_GetProcAddress("glWindowPos3sv");
	EXTGL_SANITY_CHECK(env, ext_set, OpenGL14)
}


static void extgl_InitGLUSupportedExtensions(JNIEnv *env, jobject ext_set)
{
	const char *s = (const char *)gluGetString(GLU_VERSION);
	if (!s)
		return;
	s = strstr(s, "1.");
	extgl_Extensions.GLU12 = 0;
	extgl_Extensions.GLU13 = 0;
	if (s != NULL)
	{
		if( s[2] >= '3' )
		{
			extgl_Extensions.GLU12 = 1;
			extgl_Extensions.GLU13 = 1;
		}
		if( s[2] == '2' )
		{
			extgl_Extensions.GLU12 = 1;
		}
	}
	extgl_Extensions.GLU_EXT_nurbs_tessellator = GLUQueryExtension(env, ext_set, "GLU_EXT_nurbs_tessellator");
	extgl_Extensions.GLU_EXT_object_space_tess = GLUQueryExtension(env, ext_set, "GLU_EXT_object_space_tess");
}

static void extgl_InitSupportedExtensions(JNIEnv *env, jobject ext_set)
{
	char *s = (char*) glGetString(GL_VERSION);
	if (!s)
		return;
	s = strstr(s, "1.");
	extgl_Extensions.OpenGL12 = false;
	extgl_Extensions.OpenGL13 = false;
	extgl_Extensions.OpenGL14 = false;
	if (s != NULL)
	{
		// Fall trhough
		switch (s[2]) {
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
	extgl_Extensions.GL_ARB_point_parameters = GLQueryExtension(env, ext_set, "GL_ARB_point_parameters");
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
	extgl_Extensions.GL_ATI_element_array = GLQueryExtension(env, ext_set, "GL_ATI_element_array");
	extgl_Extensions.GL_ATI_envmap_bumpmap = GLQueryExtension(env, ext_set, "GL_ATI_envmap_bumpmap");
	extgl_Extensions.GL_ATI_fragment_shader = GLQueryExtension(env, ext_set, "GL_ATI_fragment_shader");
	extgl_Extensions.GL_ATI_pn_triangles = GLQueryExtension(env, ext_set, "GL_ATI_pn_triangles");
	extgl_Extensions.GL_ATI_point_cull_mode = GLQueryExtension(env, ext_set, "GL_ATI_point_cull_mode");
	extgl_Extensions.GL_ATI_separate_stencil = GLQueryExtension(env, ext_set, "GL_ATI_separate_stencil");
	extgl_Extensions.GL_ATI_text_fragment_shader = GLQueryExtension(env, ext_set, "GL_ATI_text_fragment_shader");
	extgl_Extensions.GL_ATI_texture_mirror_once = GLQueryExtension(env, ext_set, "GL_ATI_texture_mirror_once");
	extgl_Extensions.GL_ATI_vertex_array_object = GLQueryExtension(env, ext_set, "GL_ATI_vertex_array_object");
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
	extgl_Extensions.GL_NV_light_max_exponent = GLQueryExtension(env, ext_set, "GL_NV_light_max_exponent");
	extgl_Extensions.GL_NV_occlusion_query = GLQueryExtension(env, ext_set, "GL_NV_occlusion_query");
	extgl_Extensions.GL_NV_packed_depth_stencil = GLQueryExtension(env, ext_set, "GL_NV_packed_depth_stencil");
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

/* extgl_Init the extensions and load all the functions */
bool extgl_Initialize(JNIEnv *env, jobject ext_set)
{
	extgl_error = false;
	extgl_InitOpenGL1_1();
	extgl_InitGLU12();
	if (extgl_error)
		return false;

	extgl_InitGLUSupportedExtensions(env, ext_set);
	extgl_InitSupportedExtensions(env, ext_set);
	
	extgl_InitEXTNurbsTesselator(env, ext_set);

	/* first load the extensions */
	extgl_InitARBTransposeMatrix(env, ext_set);
	extgl_InitARBMultisample(env, ext_set);
	extgl_InitEXTCompiledVertexArray(env, ext_set);
	extgl_InitEXTSecondaryColor(env, ext_set);
	extgl_InitEXTFogCoord(env, ext_set);
	extgl_InitNVVertexArrayRange(env, ext_set);
	extgl_InitEXTPointParameters(env, ext_set);
	extgl_InitNVRegisterCombiners(env, ext_set);
	extgl_InitEXTVertexWeighting(env, ext_set);
	extgl_InitNVVertexProgram(env, ext_set);
	extgl_InitNVFence(env, ext_set);
	extgl_InitNVRegisterCombiners2(env, ext_set);
	extgl_InitATIPNTriangles(env, ext_set);
	extgl_InitATISeparateStencil(env, ext_set);
	extgl_InitARBPointParameters(env, ext_set);
	extgl_InitARBVertexBlend(env, ext_set);
	extgl_InitEXTMultiDrawArrays(env, ext_set);
	extgl_InitARBMatrixPalette(env, ext_set);
	extgl_InitEXTVertexShader(env, ext_set);
	extgl_InitATIEnvmapBumpmap(env, ext_set);
	extgl_InitATIFragmentShader(env, ext_set);
	extgl_InitATIElementArray(env, ext_set);
	extgl_InitATIVertexStreams(env, ext_set);
	extgl_InitATIVertexArrayObject(env, ext_set);
	extgl_InitNVOcclusionQuery(env, ext_set);
	extgl_InitNVPointSprite(env, ext_set);
	extgl_InitARBWindowPos(env, ext_set);
	extgl_InitARBTextureCompression(env, ext_set);
	extgl_InitEXTDrawRangeElements(env, ext_set);
	extgl_InitEXTStencilTwoSide(env, ext_set);
	extgl_InitARBVertexProgram(env, ext_set);
	extgl_InitARBVertexBufferObject(env, ext_set);
	extgl_InitEXTCullVertex(env, ext_set);
	extgl_InitEXTBlendFuncSeparate(env, ext_set);
	extgl_InitARBImaging(env, ext_set);
	extgl_InitARBMultitexture(env, ext_set);
	extgl_InitNVElementArray(env, ext_set);
	extgl_InitNVEvaluators(env, ext_set);
	extgl_InitNVFragmentProgram(env, ext_set);
	extgl_InitNVPrimitiveRestart(env, ext_set);
	extgl_InitARBFragmentProgram(env, ext_set);
	
   /* now load core opengl */
	extgl_InitOpenGL1_2(env, ext_set);
	extgl_InitOpenGL1_3(env, ext_set);
	extgl_InitOpenGL1_4(env, ext_set);
	
	extgl_InitGLU13(env, ext_set);

#ifdef _WIN32
	/* load WGL extensions */
	extgl_InitializeWGL(env, ext_set);
#endif
	return true;
}

#ifdef _AGL
bool extgl_Open(void) {
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
	lib_gl_handle = dlopen("libGL.so.1", RTLD_LAZY | RTLD_GLOBAL);
	if (lib_gl_handle == NULL) {
		printfDebug("Error loading libGL.so.1: %s\n", dlerror());
		return false;
	}
	lib_glu_handle = dlopen("libGLU.so.1", RTLD_LAZY | RTLD_GLOBAL);
	if (lib_glu_handle == NULL) {
		printfDebug("Error loading libGLU.so.1: %s\n", dlerror());
		dlclose(lib_gl_handle);
		return false;
	}
	return true;
}

#endif /* X11 */

#ifdef _WIN32
bool extgl_Open(void)
{
	// load the dynamic libraries for OpenGL
	//
	lib_gl_handle = LoadLibrary("opengl32.dll");
	if (lib_gl_handle == NULL)
		return false;
	lib_glu_handle = LoadLibrary("glu32.dll");
	if (lib_glu_handle == NULL) {
		FreeLibrary(lib_gl_handle);
		return false;
	}
	return true;
}
#endif /* WIN32 */

void extgl_Close(void)
{
#ifdef _X11
	dlclose(lib_glu_handle);
	dlclose(lib_gl_handle);
#endif
#ifdef _WIN32
	FreeLibrary(lib_gl_handle);
	FreeLibrary(lib_glu_handle);
#endif
#ifdef _AGL
	aglUnloadFramework(opengl_bundle_ref);
	aglUnloadFramework(agl_bundle_ref);
#endif 
}

/* turn on the warning for the borland compiler*/
#ifdef __BORLANDC__
#pragma warn .8064
#pragma warn .8065
#endif /* __BORLANDC__	*/

