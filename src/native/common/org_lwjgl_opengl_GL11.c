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

/**
 * $Id$
 *
 * Core OpenGL functions.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include "extgl.h"

typedef void (APIENTRY * glAccumPROC) (GLenum op, GLfloat value);
typedef void (APIENTRY * glAlphaFuncPROC) (GLenum func, GLclampf ref);
//typedef GLboolean (APIENTRY * glAreTexturesResidentPROC) (GLsizei n, const GLuint *textures, GLboolean *residences);
typedef void (APIENTRY * glArrayElementPROC) (GLint i);
typedef void (APIENTRY * glBeginPROC) (GLenum mode);
typedef void (APIENTRY * glBindTexturePROC) (GLenum target, GLuint texture);
typedef void (APIENTRY * glBitmapPROC) (GLsizei width, GLsizei height, GLfloat xorig, GLfloat yorig, GLfloat xmove, GLfloat ymove, const GLubyte *bitmap);
typedef void (APIENTRY * glBlendFuncPROC) (GLenum sfactor, GLenum dfactor);
typedef void (APIENTRY * glCallListPROC) (GLuint list);
typedef void (APIENTRY * glCallListsPROC) (GLsizei n, GLenum type, const GLvoid *lists);
typedef void (APIENTRY * glClearPROC) (GLbitfield mask);
typedef void (APIENTRY * glClearAccumPROC) (GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha);
typedef void (APIENTRY * glClearColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef void (APIENTRY * glClearDepthPROC) (GLclampd depth);
typedef void (APIENTRY * glClearIndexPROC) (GLfloat c);
typedef void (APIENTRY * glClearStencilPROC) (GLint s);
typedef void (APIENTRY * glClipPlanePROC) (GLenum plane, const GLdouble *equation);
typedef void (APIENTRY * glColor3bPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY * glColor3fPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY * glColor3ubPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY * glColor4bPROC) (GLbyte red, GLbyte green, GLbyte blue, GLbyte alpha);
typedef void (APIENTRY * glColor4fPROC) (GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha);
typedef void (APIENTRY * glColor4ubPROC) (GLubyte red, GLubyte green, GLubyte blue, GLubyte alpha);
typedef void (APIENTRY * glColorMaskPROC) (GLboolean red, GLboolean green, GLboolean blue, GLboolean alpha);
typedef void (APIENTRY * glColorMaterialPROC) (GLenum face, GLenum mode);
typedef void (APIENTRY * glColorPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glCopyPixelsPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum type);
typedef void (APIENTRY * glCopyTexImage1DPROC) (GLenum target, GLint level, GLenum internalFormat, GLint x, GLint y, GLsizei width, GLint border);
typedef void (APIENTRY * glCopyTexImage2DPROC) (GLenum target, GLint level, GLenum internalFormat, GLint x, GLint y, GLsizei width, GLsizei height, GLint border);
typedef void (APIENTRY * glCopyTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLint x, GLint y, GLsizei width);
typedef void (APIENTRY * glCopyTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY * glCullFacePROC) (GLenum mode);
typedef void (APIENTRY * glDeleteListsPROC) (GLuint list, GLsizei range);
typedef void (APIENTRY * glDeleteTexturesPROC) (GLsizei n, const GLuint *textures);
typedef void (APIENTRY * glDepthFuncPROC) (GLenum func);
typedef void (APIENTRY * glDepthMaskPROC) (GLboolean flag);
typedef void (APIENTRY * glDepthRangePROC) (GLclampd zNear, GLclampd zFar);
typedef void (APIENTRY * glDisablePROC) (GLenum cap);
typedef void (APIENTRY * glDisableClientStatePROC) (GLenum array);
typedef void (APIENTRY * glDrawArraysPROC) (GLenum mode, GLint first, GLsizei count);
typedef void (APIENTRY * glDrawBufferPROC) (GLenum mode);
typedef void (APIENTRY * glDrawElementsPROC) (GLenum mode, GLsizei count, GLenum type, const GLvoid *indices);
typedef void (APIENTRY * glDrawPixelsPROC) (GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glEdgeFlagPROC) (GLboolean flag);
typedef void (APIENTRY * glEdgeFlagPointerPROC) (GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glEnablePROC) (GLenum cap);
typedef void (APIENTRY * glEnableClientStatePROC) (GLenum array);
typedef void (APIENTRY * glEndPROC) (void);
typedef void (APIENTRY * glEndListPROC) (void);
typedef void (APIENTRY * glEvalCoord1fPROC) (GLfloat u);
typedef void (APIENTRY * glEvalCoord2fPROC) (GLfloat u, GLfloat v);
typedef void (APIENTRY * glEvalMesh1PROC) (GLenum mode, GLint i1, GLint i2);
typedef void (APIENTRY * glEvalMesh2PROC) (GLenum mode, GLint i1, GLint i2, GLint j1, GLint j2);
typedef void (APIENTRY * glEvalPoint1PROC) (GLint i);
typedef void (APIENTRY * glEvalPoint2PROC) (GLint i, GLint j);
typedef void (APIENTRY * glFeedbackBufferPROC) (GLsizei size, GLenum type, GLfloat *buffer);
typedef void (APIENTRY * glFinishPROC) (void);
typedef void (APIENTRY * glFlushPROC) (void);
typedef void (APIENTRY * glFogfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glFogfvPROC) (GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glFogiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glFogivPROC) (GLenum pname, const GLint *params);
typedef void (APIENTRY * glFrontFacePROC) (GLenum mode);
typedef void (APIENTRY * glFrustumPROC) (GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble zNear, GLdouble zFar);
typedef GLuint (APIENTRY * glGenListsPROC) (GLsizei range);
typedef void (APIENTRY * glGenTexturesPROC) (GLsizei n, GLuint *textures);
typedef void (APIENTRY * glGetBooleanvPROC) (GLenum pname, GLboolean *params);
typedef void (APIENTRY * glGetClipPlanePROC) (GLenum plane, GLdouble *equation);
typedef void (APIENTRY * glGetDoublevPROC) (GLenum pname, GLdouble *params);
typedef GLenum (APIENTRY * glGetErrorPROC) (void);
typedef void (APIENTRY * glGetFloatvPROC) (GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetIntegervPROC) (GLenum pname, GLint *params);
typedef void (APIENTRY * glGetLightfvPROC) (GLenum light, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetLightivPROC) (GLenum light, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetMapfvPROC) (GLenum target, GLenum query, GLfloat *v);
typedef void (APIENTRY * glGetMapivPROC) (GLenum target, GLenum query, GLint *v);
typedef void (APIENTRY * glGetMaterialfvPROC) (GLenum face, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetMaterialivPROC) (GLenum face, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetPixelMapfvPROC) (GLenum map, GLfloat *values);
typedef void (APIENTRY * glGetPixelMapuivPROC) (GLenum map, GLuint *values);
typedef void (APIENTRY * glGetPixelMapusvPROC) (GLenum map, GLushort *values);
typedef void (APIENTRY * glGetPointervPROC) (GLenum pname, GLvoid* *params);
typedef void (APIENTRY * glGetPolygonStipplePROC) (GLubyte *mask);
typedef const GLubyte * (APIENTRY * glGetStringPROC) (GLenum name);
typedef void (APIENTRY * glGetTexEnvfvPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexEnvivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexGenfvPROC) (GLenum coord, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexGenivPROC) (GLenum coord, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexImagePROC) (GLenum target, GLint level, GLenum format, GLenum type, GLvoid *pixels);
typedef void (APIENTRY * glGetTexLevelParameterfvPROC) (GLenum target, GLint level, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexLevelParameterivPROC) (GLenum target, GLint level, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexParameterivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glHintPROC) (GLenum target, GLenum mode);
typedef void (APIENTRY * glInitNamesPROC) (void);
typedef void (APIENTRY * glInterleavedArraysPROC) (GLenum format, GLsizei stride, const GLvoid *pointer);
typedef GLboolean (APIENTRY * glIsEnabledPROC) (GLenum cap);
typedef GLboolean (APIENTRY * glIsListPROC) (GLuint list);
typedef GLboolean (APIENTRY * glIsTexturePROC) (GLuint texture);
typedef void (APIENTRY * glLightModelfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glLightModelfvPROC) (GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glLightModeliPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glLightModelivPROC) (GLenum pname, const GLint *params);
typedef void (APIENTRY * glLightfPROC) (GLenum light, GLenum pname, GLfloat param);
typedef void (APIENTRY * glLightfvPROC) (GLenum light, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glLightiPROC) (GLenum light, GLenum pname, GLint param);
typedef void (APIENTRY * glLightivPROC) (GLenum light, GLenum pname, const GLint *params);
typedef void (APIENTRY * glLineStipplePROC) (GLint factor, GLushort pattern);
typedef void (APIENTRY * glLineWidthPROC) (GLfloat width);
typedef void (APIENTRY * glListBasePROC) (GLuint base);
typedef void (APIENTRY * glLoadIdentityPROC) (void);
typedef void (APIENTRY * glLoadMatrixfPROC) (const GLfloat *m);
typedef void (APIENTRY * glLoadNamePROC) (GLuint name);
typedef void (APIENTRY * glLogicOpPROC) (GLenum opcode);
typedef void (APIENTRY * glMap1fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint stride, GLint order, const GLfloat *points);
typedef void (APIENTRY * glMap2fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint ustride, GLint uorder, GLfloat v1, GLfloat v2, GLint vstride, GLint vorder, const GLfloat *points);
typedef void (APIENTRY * glMapGrid1fPROC) (GLint un, GLfloat u1, GLfloat u2);
typedef void (APIENTRY * glMapGrid2fPROC) (GLint un, GLfloat u1, GLfloat u2, GLint vn, GLfloat v1, GLfloat v2);
typedef void (APIENTRY * glMaterialfPROC) (GLenum face, GLenum pname, GLfloat param);
typedef void (APIENTRY * glMaterialfvPROC) (GLenum face, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glMaterialiPROC) (GLenum face, GLenum pname, GLint param);
typedef void (APIENTRY * glMaterialivPROC) (GLenum face, GLenum pname, const GLint *params);
typedef void (APIENTRY * glMatrixModePROC) (GLenum mode);
typedef void (APIENTRY * glMultMatrixfPROC) (const GLfloat *m);
typedef void (APIENTRY * glNewListPROC) (GLuint list, GLenum mode);
typedef void (APIENTRY * glNormal3bPROC) (GLbyte nx, GLbyte ny, GLbyte nz);
typedef void (APIENTRY * glNormal3fPROC) (GLfloat nx, GLfloat ny, GLfloat nz);
typedef void (APIENTRY * glNormal3iPROC) (GLint nx, GLint ny, GLint nz);
typedef void (APIENTRY * glNormalPointerPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glOrthoPROC) (GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble zNear, GLdouble zFar);
typedef void (APIENTRY * glPassThroughPROC) (GLfloat token);
typedef void (APIENTRY * glPixelMapfvPROC) (GLenum map, GLsizei mapsize, const GLfloat *values);
typedef void (APIENTRY * glPixelMapuivPROC) (GLenum map, GLsizei mapsize, const GLuint *values);
typedef void (APIENTRY * glPixelMapusvPROC) (GLenum map, GLsizei mapsize, const GLushort *values);
typedef void (APIENTRY * glPixelStorefPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPixelStoreiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glPixelTransferfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPixelTransferiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glPixelZoomPROC) (GLfloat xfactor, GLfloat yfactor);
typedef void (APIENTRY * glPointSizePROC) (GLfloat size);
typedef void (APIENTRY * glPolygonModePROC) (GLenum face, GLenum mode);
typedef void (APIENTRY * glPolygonOffsetPROC) (GLfloat factor, GLfloat units);
typedef void (APIENTRY * glPolygonStipplePROC) (const GLubyte *mask);
typedef void (APIENTRY * glPopAttribPROC) (void);
typedef void (APIENTRY * glPopClientAttribPROC) (void);
typedef void (APIENTRY * glPopMatrixPROC) (void);
typedef void (APIENTRY * glPopNamePROC) (void);
//typedef void (APIENTRY * glPrioritizeTexturesPROC) (GLsizei n, const GLuint *textures, const GLclampf *priorities);
typedef void (APIENTRY * glPushAttribPROC) (GLbitfield mask);
typedef void (APIENTRY * glPushClientAttribPROC) (GLbitfield mask);
typedef void (APIENTRY * glPushMatrixPROC) (void);
typedef void (APIENTRY * glPushNamePROC) (GLuint name);
typedef void (APIENTRY * glRasterPos2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glRasterPos2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glRasterPos3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glRasterPos3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY * glRasterPos4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glRasterPos4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glReadBufferPROC) (GLenum mode);
typedef void (APIENTRY * glReadPixelsPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, GLvoid *pixels);
typedef void (APIENTRY * glRectfPROC) (GLfloat x1, GLfloat y1, GLfloat x2, GLfloat y2);
typedef void (APIENTRY * glRectiPROC) (GLint x1, GLint y1, GLint x2, GLint y2);
typedef GLint (APIENTRY * glRenderModePROC) (GLenum mode);
typedef void (APIENTRY * glRotatefPROC) (GLfloat angle, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glScalefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glScissorPROC) (GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY * glSelectBufferPROC) (GLsizei size, GLuint *buffer);
typedef void (APIENTRY * glShadeModelPROC) (GLenum mode);
typedef void (APIENTRY * glStencilFuncPROC) (GLenum func, GLint ref, GLuint mask);
typedef void (APIENTRY * glStencilMaskPROC) (GLuint mask);
typedef void (APIENTRY * glStencilOpPROC) (GLenum fail, GLenum zfail, GLenum zpass);
typedef void (APIENTRY * glTexCoord1fPROC) (GLfloat s);
typedef void (APIENTRY * glTexCoord2fPROC) (GLfloat s, GLfloat t);
typedef void (APIENTRY * glTexCoord3fPROC) (GLfloat s, GLfloat t, GLfloat r);
typedef void (APIENTRY * glTexCoord4fPROC) (GLfloat s, GLfloat t, GLfloat r, GLfloat q);
typedef void (APIENTRY * glTexCoordPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glTexEnvfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef void (APIENTRY * glTexEnvfvPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glTexEnviPROC) (GLenum target, GLenum pname, GLint param);
typedef void (APIENTRY * glTexEnvivPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glTexGenfPROC) (GLenum coord, GLenum pname, GLfloat param);
typedef void (APIENTRY * glTexGenfvPROC) (GLenum coord, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glTexGeniPROC) (GLenum coord, GLenum pname, GLint param);
typedef void (APIENTRY * glTexGenivPROC) (GLenum coord, GLenum pname, const GLint *params);
typedef void (APIENTRY * glTexImage1DPROC) (GLenum target, GLint level, GLint internalformat, GLsizei width, GLint border, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTexImage2DPROC) (GLenum target, GLint level, GLint internalformat, GLsizei width, GLsizei height, GLint border, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTexParameterfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef void (APIENTRY * glTexParameterfvPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glTexParameteriPROC) (GLenum target, GLenum pname, GLint param);
typedef void (APIENTRY * glTexParameterivPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTranslatefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertex2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertex2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glVertex3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertex3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY * glVertex4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertex4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glVertexPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glViewportPROC) (GLint x, GLint y, GLsizei width, GLsizei height);

static glAccumPROC glAccum;
static glAlphaFuncPROC glAlphaFunc;
//static glAreTexturesResidentPROC glAreTexturesResident;
static glArrayElementPROC glArrayElement;
static glBeginPROC glBegin;
static glBindTexturePROC glBindTexture;
static glBitmapPROC glBitmap;
static glBlendFuncPROC glBlendFunc;
static glCallListPROC glCallList;
static glCallListsPROC glCallLists;
static glClearPROC glClear;
static glClearAccumPROC glClearAccum;
static glClearColorPROC glClearColor;
static glClearDepthPROC glClearDepth;
static glClearIndexPROC glClearIndex;
static glClearStencilPROC glClearStencil;
static glClipPlanePROC glClipPlane;
static glColor3bPROC glColor3b;
static glColor3fPROC glColor3f;
static glColor3ubPROC glColor3ub;
static glColor4bPROC glColor4b;
static glColor4fPROC glColor4f;
static glColor4ubPROC glColor4ub;
static glColorMaskPROC glColorMask;
static glColorMaterialPROC glColorMaterial;
static glColorPointerPROC glColorPointer;
static glCopyPixelsPROC glCopyPixels;
static glCopyTexImage1DPROC glCopyTexImage1D;
static glCopyTexImage2DPROC glCopyTexImage2D;
static glCopyTexSubImage1DPROC glCopyTexSubImage1D;
static glCopyTexSubImage2DPROC glCopyTexSubImage2D;
static glCullFacePROC glCullFace;
static glDeleteListsPROC glDeleteLists;
static glDeleteTexturesPROC glDeleteTextures;
static glDepthFuncPROC glDepthFunc;
static glDepthMaskPROC glDepthMask;
static glDepthRangePROC glDepthRange;
static glDisablePROC glDisable;
static glDisableClientStatePROC glDisableClientState;
static glDrawArraysPROC glDrawArrays;
static glDrawBufferPROC glDrawBuffer;
static glDrawElementsPROC glDrawElements;
static glDrawPixelsPROC glDrawPixels;
static glEdgeFlagPROC glEdgeFlag;
static glEdgeFlagPointerPROC glEdgeFlagPointer;
static glEnablePROC glEnable;
static glEnableClientStatePROC glEnableClientState;
static glEndPROC glEnd;
static glEndListPROC glEndList;
static glEvalCoord1fPROC glEvalCoord1f;
static glEvalCoord2fPROC glEvalCoord2f;
static glEvalMesh1PROC glEvalMesh1;
static glEvalMesh2PROC glEvalMesh2;
static glEvalPoint1PROC glEvalPoint1;
static glEvalPoint2PROC glEvalPoint2;
static glFeedbackBufferPROC glFeedbackBuffer;
static glFinishPROC glFinish;
static glFlushPROC glFlush;
static glFogfPROC glFogf;
static glFogfvPROC glFogfv;
static glFogiPROC glFogi;
static glFogivPROC glFogiv;
static glFrontFacePROC glFrontFace;
static glFrustumPROC glFrustum;
static glGenListsPROC glGenLists;
static glGenTexturesPROC glGenTextures;
static glGetBooleanvPROC glGetBooleanv;
static glGetClipPlanePROC glGetClipPlane;
static glGetDoublevPROC glGetDoublev;
static glGetErrorPROC glGetError;
static glGetFloatvPROC glGetFloatv;
static glGetIntegervPROC glGetIntegerv;
static glGetLightfvPROC glGetLightfv;
static glGetLightivPROC glGetLightiv;
static glGetMapfvPROC glGetMapfv;
static glGetMapivPROC glGetMapiv;
static glGetMaterialfvPROC glGetMaterialfv;
static glGetMaterialivPROC glGetMaterialiv;
static glGetPixelMapfvPROC glGetPixelMapfv;
static glGetPixelMapuivPROC glGetPixelMapuiv;
static glGetPixelMapusvPROC glGetPixelMapusv;
static glGetPointervPROC glGetPointerv;
static glGetPolygonStipplePROC glGetPolygonStipple;
static glGetStringPROC glGetString;
static glGetTexEnvfvPROC glGetTexEnvfv;
static glGetTexEnvivPROC glGetTexEnviv;
static glGetTexGenfvPROC glGetTexGenfv;
static glGetTexGenivPROC glGetTexGeniv;
static glGetTexImagePROC glGetTexImage;
static glGetTexLevelParameterfvPROC glGetTexLevelParameterfv;
static glGetTexLevelParameterivPROC glGetTexLevelParameteriv;
static glGetTexParameterfvPROC glGetTexParameterfv;
static glGetTexParameterivPROC glGetTexParameteriv;
static glHintPROC glHint;
static glInitNamesPROC glInitNames;
static glInterleavedArraysPROC glInterleavedArrays;
static glIsEnabledPROC glIsEnabled;
static glIsListPROC glIsList;
static glIsTexturePROC glIsTexture;
static glLightModelfPROC glLightModelf;
static glLightModelfvPROC glLightModelfv;
static glLightModeliPROC glLightModeli;
static glLightModelivPROC glLightModeliv;
static glLightfPROC glLightf;
static glLightfvPROC glLightfv;
static glLightiPROC glLighti;
static glLightivPROC glLightiv;
static glLineStipplePROC glLineStipple;
static glLineWidthPROC glLineWidth;
static glListBasePROC glListBase;
static glLoadIdentityPROC glLoadIdentity;
static glLoadMatrixfPROC glLoadMatrixf;
static glLoadNamePROC glLoadName;
static glLogicOpPROC glLogicOp;
static glMap1fPROC glMap1f;
static glMap2fPROC glMap2f;
static glMapGrid1fPROC glMapGrid1f;
static glMapGrid2fPROC glMapGrid2f;
static glMaterialfPROC glMaterialf;
static glMaterialfvPROC glMaterialfv;
static glMaterialiPROC glMateriali;
static glMaterialivPROC glMaterialiv;
static glMatrixModePROC glMatrixMode;
static glMultMatrixfPROC glMultMatrixf;
static glNewListPROC glNewList;
static glNormal3bPROC glNormal3b;
static glNormal3fPROC glNormal3f;
static glNormal3iPROC glNormal3i;
static glNormalPointerPROC glNormalPointer;
static glOrthoPROC glOrtho;
static glPassThroughPROC glPassThrough;
static glPixelMapfvPROC glPixelMapfv;
static glPixelMapuivPROC glPixelMapuiv;
static glPixelMapusvPROC glPixelMapusv;
static glPixelStorefPROC glPixelStoref;
static glPixelStoreiPROC glPixelStorei;
static glPixelTransferfPROC glPixelTransferf;
static glPixelTransferiPROC glPixelTransferi;
static glPixelZoomPROC glPixelZoom;
static glPointSizePROC glPointSize;
static glPolygonModePROC glPolygonMode;
static glPolygonOffsetPROC glPolygonOffset;
static glPolygonStipplePROC glPolygonStipple;
static glPopAttribPROC glPopAttrib;
static glPopClientAttribPROC glPopClientAttrib;
static glPopMatrixPROC glPopMatrix;
static glPopNamePROC glPopName;
//static glPrioritizeTexturesPROC glPrioritizeTextures;
static glPushAttribPROC glPushAttrib;
static glPushClientAttribPROC glPushClientAttrib;
static glPushMatrixPROC glPushMatrix;
static glPushNamePROC glPushName;
static glRasterPos2fPROC glRasterPos2f;
static glRasterPos2iPROC glRasterPos2i;
static glRasterPos3fPROC glRasterPos3f;
static glRasterPos3iPROC glRasterPos3i;

static glRasterPos4fPROC glRasterPos4f;
static glRasterPos4iPROC glRasterPos4i;
static glReadBufferPROC glReadBuffer;
static glReadPixelsPROC glReadPixels;
static glRectfPROC glRectf;
static glRectiPROC glRecti;
static glRenderModePROC glRenderMode;
static glRotatefPROC glRotatef;
static glScalefPROC glScalef;
static glScissorPROC glScissor;
static glSelectBufferPROC glSelectBuffer;
static glShadeModelPROC glShadeModel;
static glStencilFuncPROC glStencilFunc;
static glStencilMaskPROC glStencilMask;
static glStencilOpPROC glStencilOp;
static glTexCoord1fPROC glTexCoord1f;
static glTexCoord2fPROC glTexCoord2f;
static glTexCoord3fPROC glTexCoord3f;
static glTexCoord4fPROC glTexCoord4f;
static glTexCoordPointerPROC glTexCoordPointer;
static glTexEnvfPROC glTexEnvf;
static glTexEnvfvPROC glTexEnvfv;
static glTexEnviPROC glTexEnvi;
static glTexEnvivPROC glTexEnviv;
static glTexGenfPROC glTexGenf;
static glTexGenfvPROC glTexGenfv;
static glTexGeniPROC glTexGeni;
static glTexGenivPROC glTexGeniv;
static glTexImage1DPROC glTexImage1D;
static glTexImage2DPROC glTexImage2D;
static glTexParameterfPROC glTexParameterf;
static glTexParameterfvPROC glTexParameterfv;
static glTexParameteriPROC glTexParameteri;
static glTexParameterivPROC glTexParameteriv;
static glTexSubImage1DPROC glTexSubImage1D;
static glTexSubImage2DPROC glTexSubImage2D;
static glTranslatefPROC glTranslatef;
static glVertex2fPROC glVertex2f;
static glVertex2iPROC glVertex2i;
static glVertex3fPROC glVertex3f;
static glVertex3iPROC glVertex3i;
static glVertex4fPROC glVertex4f;
static glVertex4iPROC glVertex4i;
static glVertexPointerPROC glVertexPointer;
static glViewportPROC glViewport;

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glAccum
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glAccum(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAccum((GLint) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glAlphaFunc
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glAlphaFunc(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAlphaFunc((GLint) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearColor
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glClearColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearAccum
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glClearAccum(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearAccum((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClear
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glClear(JNIEnv * env, jclass clazz, jint p0)
{
	glClear((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCallLists
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglCallLists(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject lists_buffer, jint lists_offset)
{
	GLbyte *lists = (GLbyte *)(*env)->GetDirectBufferAddress(env, lists_buffer);
	glCallLists((GLint) p0, (GLint) p1, (const void *)(lists + lists_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCallList
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glCallList(JNIEnv * env, jclass clazz, jint p0)
{
	glCallList((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBlendFunc
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glBlendFunc(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBlendFunc((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBitmap
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglBitmap(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jobject image_buffer, jint image_offset)
{
	const GLubyte *image = (const GLubyte *)(*env)->GetDirectBufferAddress(env, image_buffer);
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, image + image_offset);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBitmap
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglBitmapBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jint buffer_offset)
{
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBindTexture
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glBindTexture(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBindTexture((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBegin
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glBegin(JNIEnv * env, jclass clazz, jint p0)
{
	glBegin((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEnd
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEnd(JNIEnv * env, jclass clazz)
{
	glEnd();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glArrayElement
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glArrayElement(JNIEnv * env, jclass clazz, jint p0)
{
	glArrayElement((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearDepth
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glClearDepth(JNIEnv * env, jclass clazz, jdouble p0)
{
	glClearDepth((GLdouble) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDeleteLists
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDeleteLists(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glDeleteLists((GLuint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDeleteTextures
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglDeleteTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLuint *address = (const GLuint *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glDeleteTextures((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCullFace
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glCullFace(JNIEnv * env, jclass clazz, jint p0)
{
	glCullFace((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexSubImage2D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexSubImage1D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCopyTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexImage2D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexImage1D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCopyTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyPixels
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glCopyPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglColorPointer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglColorPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	const GLbyte *address = (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer);
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) (address + buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglColorPointerVBO
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglColorPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColorMaterial
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColorMaterial(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glColorMaterial((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColorMask
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColorMask(JNIEnv * env, jclass clazz, jboolean p0, jboolean p1, jboolean p2, jboolean p3)
{
	glColorMask((GLboolean) p0, (GLboolean) p1, (GLboolean) p2, (GLboolean) p3);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor3b
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColor3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor3f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColor3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glColor3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor3ub
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColor3ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor4b
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColor4b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor4f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColor4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glColor4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor4ub
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glColor4ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClipPlane
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLdouble *address = offset + (const GLdouble *)(*env)->GetDirectBufferAddress(env, buffer);
	glClipPlane((GLint) p0, address + offset);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearStencil
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glClearStencil(JNIEnv * env, jclass clazz, jint p0)
{
	glClearStencil((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearIndex
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glClearIndex(JNIEnv * env, jclass clazz, jfloat p0)
{
	glClearIndex((GLfloat) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalPoint1
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalPoint1(JNIEnv * env, jclass clazz, jint p0)
{
	glEvalPoint1((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalPoint2
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalPoint2(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glEvalPoint2((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalMesh1
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalMesh1(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glEvalMesh1((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalMesh2
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalMesh2(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glEvalMesh2((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalCoord1f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glEvalCoord1f((GLfloat) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalCoord2f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glEvalCoord2f((GLfloat) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEnableClientState
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEnableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glEnableClientState((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDisableClientState
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDisableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glDisableClientState((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEnable
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEnable(JNIEnv * env, jclass clazz, jint p0)
{
	glEnable((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDisable
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDisable(JNIEnv * env, jclass clazz, jint p0)
{
	glDisable((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglEdgeFlagPointer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointer(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLbyte *address = offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer);
	glEdgeFlagPointer((GLint) p0, (const void *)address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglEdgeFlagPointerVBO
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint buffer_offset)
{
	glEdgeFlagPointer((GLint) p0, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEdgeFlag
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEdgeFlag(JNIEnv * env, jclass clazz, jboolean p0)
{
	glEdgeFlag((GLboolean) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDrawPixels
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint offset)
{
	const GLbyte *address = (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer);
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const void *)(address + offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDrawPixels
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawPixelsBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint buffer_offset)
{
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglDrawElements
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawElements(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const GLbyte *address = (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer);
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, (const void *)(address + offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglDrawElementsVBO
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawElementsVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDrawBuffer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDrawBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glDrawBuffer((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDrawArrays
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDrawArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glDrawArrays((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDepthRange
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDepthRange(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glDepthRange((GLdouble) p0, (GLdouble) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDepthMask
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDepthMask(JNIEnv * env, jclass clazz, jboolean p0)
{
	glDepthMask((GLboolean) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDepthFunc
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glDepthFunc(JNIEnv * env, jclass clazz, jint p0)
{
	glDepthFunc((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFeedbackBuffer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglFeedbackBuffer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glFeedbackBuffer((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetPixelMapfv((GLint) p0, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapfvBO(JNIEnv * env, jclass clazz, jint p0, jint buffer_offset)
{
	glGetPixelMapfv((GLint) p0, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapuiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetPixelMapuiv((GLint) p0, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapuiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapuivBO(JNIEnv * env, jclass clazz, jint p0, jint buffer_offset)
{
	glGetPixelMapuiv((GLint) p0, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapusv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLushort *address = (GLushort *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetPixelMapusv((GLint) p0, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapusv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapusvBO(JNIEnv * env, jclass clazz, jint p0, jint buffer_offset)
{
	glGetPixelMapusv((GLint) p0, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMaterialfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetMaterialfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMaterialiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetMaterialiv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMapfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetMapfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMapiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMapiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetMapiv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetLightfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetLightfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetLightiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetLightiv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetError
 */
static jint JNICALL Java_org_lwjgl_opengl_GL11_glGetError(JNIEnv * env, jclass clazz)
{
	jint ret = (jint) glGetError();

	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetClipPlane
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLdouble *address = offset + (GLdouble *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetClipPlane((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetBooleanv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetBooleanv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLubyte *address = offset + (GLubyte *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetBooleanv((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetDoublev
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetDoublev(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLdouble *address = offset + (GLdouble *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetDoublev((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetFloatv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetFloatv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetFloatv((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetIntegerv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetIntegerv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetIntegerv((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGenTextures
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGenTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGenTextures((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGenLists
 */
static jint JNICALL Java_org_lwjgl_opengl_GL11_glGenLists(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glGenLists((GLint) p0);

	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFrustum
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glFrustum(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glFrustum((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFrontFace
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glFrontFace(JNIEnv * env, jclass clazz, jint p0)
{
	glFrontFace((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glFogf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glFogf((GLint) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogi
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glFogi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glFogi((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglFogfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glFogfv((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglFogiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glFogiv((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFlush
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glFlush(JNIEnv * env, jclass clazz)
{
	glFlush();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFinish
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glFinish(JNIEnv * env, jclass clazz)
{
	glFinish();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPointerv
 */
static jobject JNICALL Java_org_lwjgl_opengl_GL11_glGetPointerv(JNIEnv * env, jclass clazz, jint p0, int size)
{
	void *pointer;
	glGetPointerv((GLint) p0, &pointer);

	return safeNewBuffer(env, pointer, size);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glIsEnabled
 */
static jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsEnabled(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsEnabled((GLint) p0);

	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglInterleavedArrays
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglInterleavedArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glInterleavedArrays((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglInterleavedArraysVBO
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglInterleavedArraysVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glInterleavedArrays((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glInitNames
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glInitNames(JNIEnv * env, jclass clazz)
{
	glInitNames();

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glHint
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glHint(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glHint((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexParameterfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexParameterfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexParameteriv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexParameteriv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexLevelParameterfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexLevelParameterfv((GLint) p0, (GLint) p1, (GLint) p2, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexLevelParameteriv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexLevelParameteriv((GLint) p0, (GLint) p1, (GLint) p2, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexImage
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexImage(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexImage
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexImageBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint buffer_offset)
{
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, offsetToPointer(buffer_offset));
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexGenfv((GLint) p0, (GLint) p1, address);

}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexGeniv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexGeniv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexEnvfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexEnvfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexEnviv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetTexEnviv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetString
 */
static jstring JNICALL Java_org_lwjgl_opengl_GL11_glGetString(JNIEnv * env, jclass clazz, jint p0)
{
	const GLubyte * string = glGetString((GLenum) p0);
	return NewStringNative(env, string);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPolygonStipple
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	GLubyte *address = offset + (GLubyte *)(*env)->GetDirectBufferAddress(env, buffer);
	glGetPolygonStipple(address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPolygonStipple
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPolygonStippleBO(JNIEnv * env, jclass clazz, jint buffer_offset)
{
	glGetPolygonStipple(offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glIsList
 */
static jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsList(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsList((GLint) p0);

	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMaterialf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glMaterialf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glMaterialf((GLint) p0, (GLint) p1, (GLfloat) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMateriali
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glMateriali(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glMateriali((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMaterialfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glMaterialfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMaterialiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glMaterialiv((GLint) p0, (GLint) p1, address);

}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMapGrid1f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glMapGrid1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	glMapGrid1f((GLint) p0, (GLfloat) p1, (GLfloat) p2);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMapGrid2f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glMapGrid2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jfloat p4, jfloat p5)
{
	glMapGrid2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLfloat) p4, (GLfloat) p5);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMap2f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglMap2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jfloat p5, jfloat p6, jint p7, jint p8, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glMap2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (GLfloat) p5, (GLfloat) p6, (GLint) p7, (GLint) p8, address);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMap1f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglMap1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glMap1f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLogicOp
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLogicOp(JNIEnv * env, jclass clazz, jint p0)
{
	glLogicOp((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLoadName
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLoadName(JNIEnv * env, jclass clazz, jint p0)
{
	glLoadName((GLint) p0);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLoadMatrixf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglLoadMatrixf(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glLoadMatrixf(address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLoadIdentity
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLoadIdentity(JNIEnv * env, jclass clazz)
{
	glLoadIdentity();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glListBase
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glListBase(JNIEnv * env, jclass clazz, jint p0)
{
	glListBase((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLineWidth
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLineWidth(JNIEnv * env, jclass clazz, jfloat p0)
{
	glLineWidth((GLfloat) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLineStipple
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLineStipple(JNIEnv * env, jclass clazz, jint p0, jshort p1)
{
	glLineStipple((GLint) p0, (GLshort) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModelf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLightModelf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glLightModelf((GLint) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModeli
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLightModeli(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glLightModeli((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModelfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightModelfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glLightModelfv((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModeliv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightModeliv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glLightModeliv((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLightf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glLightf((GLint) p0, (GLint) p1, (GLfloat) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLighti
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glLighti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glLighti((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glLightfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glLightiv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glIsTexture
 */
static jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsTexture(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsTexture((GLint) p0);

	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMatrixMode
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glMatrixMode(JNIEnv * env, jclass clazz, jint p0)
{
	glMatrixMode((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPolygonStipple
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLubyte *address = offset + (const GLubyte *)(*env)->GetDirectBufferAddress(env, buffer);
	glPolygonStipple(address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPolygonStipple
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPolygonStippleBO(JNIEnv * env, jclass clazz, jint buffer_offset)
{
	glPolygonStipple(offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPolygonOffset
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPolygonOffset(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPolygonOffset((GLfloat) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPolygonMode
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPolygonMode(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPolygonMode((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPointSize
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPointSize(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPointSize((GLfloat) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelZoom
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelZoom(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPixelZoom((GLfloat) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelTransferf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelTransferf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelTransferf((GLint) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelTransferi
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelTransferi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelTransferi((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelStoref
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelStoref(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelStoref((GLint) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelStorei
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelStorei(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelStorei((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glPixelMapfv((GLint) p0, (GLint) p1, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapfvBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glPixelMapfv((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapuiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLuint *address = (const GLuint *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glPixelMapuiv((GLint) p0, (GLint) p1, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapuiv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapuivBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glPixelMapuiv((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapusv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLushort *address = (const GLushort *)(offset + (const GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glPixelMapusv((GLint) p0, (GLint) p1, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapusv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapusvBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glPixelMapusv((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));
}
/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPassThrough
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPassThrough(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPassThrough((GLfloat) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glOrtho
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glOrtho(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glOrtho((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglNormalPointer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglNormalPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glNormalPointer((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglNormalPointerVBO
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglNormalPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glNormalPointer((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNormal3b
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glNormal3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNormal3f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glNormal3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNormal3i
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glNormal3i((GLint) p0, (GLint) p1, (GLint) p2);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNewList
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glNewList(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glNewList((GLint) p0, (GLint) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEndList
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glEndList(JNIEnv * env, jclass clazz)
{
	glEndList();

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMultMatrixf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglMultMatrixf(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glMultMatrixf(address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glShadeModel
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glShadeModel(JNIEnv * env, jclass clazz, jint p0)
{
	glShadeModel((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glSelectBuffer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglSelectBuffer(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)(*env)->GetDirectBufferAddress(env, buffer);
	glSelectBuffer((GLint) p0, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glScissor
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glScissor(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glScissor((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glScalef
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glScalef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glScalef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRotatef
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRotatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRotatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRenderMode
 */
static jint JNICALL Java_org_lwjgl_opengl_GL11_glRenderMode(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glRenderMode((GLint) p0);

	return ret;
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRectf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRectf(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRectf((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRecti
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRecti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRecti((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glReadPixels
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglReadPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glReadPixels
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglReadPixelsBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint buffer_offset)
{
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glReadBuffer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glReadBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glReadBuffer((GLint) p0);

}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos2f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glRasterPos2f((GLfloat) p0, (GLfloat) p1);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos2i
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glRasterPos2i((GLint) p0, (GLint) p1);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos3f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glRasterPos3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos3i
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glRasterPos3i((GLint) p0, (GLint) p1, (GLint) p2);

}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos4f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRasterPos4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos4i
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRasterPos4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPushName
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPushName(JNIEnv * env, jclass clazz, jint p0)
{
	glPushName((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPopName
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPopName(JNIEnv * env, jclass clazz)
{
	glPopName();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPushMatrix
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPushMatrix(JNIEnv * env, jclass clazz)
{
	glPushMatrix();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPopMatrix
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPopMatrix(JNIEnv * env, jclass clazz)
{
	glPopMatrix();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglPushClientAttrib
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPushClientAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushClientAttrib((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglPopClientAttrib
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglPopClientAttrib(JNIEnv * env, jclass clazz)
{
	glPopClientAttrib();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPushAttrib
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPushAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushAttrib((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPopAttrib
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glPopAttrib(JNIEnv * env, jclass clazz)
{
	glPopAttrib();

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glStencilFunc
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glStencilFunc(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilFunc((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglVertexPointer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglVertexPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglVertexPointerVBO
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglVertexPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex2f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glVertex2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex2i
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glVertex2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex3f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glVertex3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex3i
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glVertex3i((GLint) p0, (GLint) p1, (GLint) p2);
}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex4f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertex4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex4i
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glVertex4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTranslatef
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTranslatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTranslatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexSubImage2D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexSubImage2D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage2DBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint buffer_offset)
{
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexSubImage1D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexSubImage1D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage1DBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint buffer_offset)
{
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, offsetToPointer(buffer_offset));
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject param, jint param_offset) {
	GLfloat *address = param_offset + (GLfloat *)(*env)->GetDirectBufferAddress(env, param);
	glTexParameterfv(target, pname, address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject param, jint param_offset) {
	GLint *address = param_offset + (GLint *)(*env)->GetDirectBufferAddress(env, param);
	glTexParameteriv(target, pname, address);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexParameterf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexParameterf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexParameterf((GLint) p0, (GLint) p1, (GLfloat) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexParameteri
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexParameteri(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexParameteri((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexImage2D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint offset)
{
	GLvoid *buffer_ptr = (GLvoid *)((char *)safeGetBufferAddress(env, buffer) + offset);
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, buffer_ptr);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexImage2D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage2DBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint buffer_offset)
{
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexImage1D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jobject buffer, jint offset)
{
	GLvoid *buffer_ptr = (GLvoid *)((char *)safeGetBufferAddress(env, buffer) + offset);
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, buffer_ptr);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexImage1D
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage1DBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint buffer_offset)
{
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, offsetToPointer(buffer_offset));
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexGenf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexGenf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexGenf((GLint) p0, (GLint) p1, (GLfloat) p2);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexGenfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glTexGenfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexGeni
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexGeni(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexGeni((GLint) p0, (GLint) p1, (GLint) p2);

}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexGeniv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexGeniv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glTexGeniv((GLint) p0, (GLint) p1, address);

}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnvf
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexEnvf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexEnvf((GLint) p0, (GLint) p1, (GLfloat) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnvi
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexEnvi(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexEnvi((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnvfv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)(*env)->GetDirectBufferAddress(env, buffer);
	glTexEnvfv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnviv
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)(*env)->GetDirectBufferAddress(env, buffer);
	glTexEnviv((GLint) p0, (GLint) p1, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexCoordPointer
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexCoordPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)(*env)->GetDirectBufferAddress(env, buffer));
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, address);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexCoordPointerVBO
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexCoordPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord1f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glTexCoord1f((GLfloat) p0);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord2f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glTexCoord2f((GLfloat) p0, (GLfloat) p1);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord3f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTexCoord3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord4f
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glTexCoord4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glStencilOp
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glStencilOp(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilOp((GLint) p0, (GLint) p1, (GLint) p2);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glStencilMask
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glStencilMask(JNIEnv * env, jclass clazz, jint p0)
{
	glStencilMask((GLint) p0);

}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glViewport
 */
static void JNICALL Java_org_lwjgl_opengl_GL11_glViewport(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glViewport((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);

}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glAccum", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL11_glAccum, "glAccum", (void*)&glAccum},
		{"glAlphaFunc", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL11_glAlphaFunc, "glAlphaFunc", (void*)&glAlphaFunc},
		{"glClearColor", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glClearColor, "glClearColor", (void*)&glClearColor},
		{"glClearAccum", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glClearAccum, "glClearAccum", (void*)&glClearAccum},
		{"glClear", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glClear, "glClear", (void*)&glClear},
		{"nglCallLists", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglCallLists, "glCallLists", (void*)&glCallLists},
		{"glCallList", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glCallList, "glCallList", (void*)&glCallList},
		{"glBlendFunc", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glBlendFunc, "glBlendFunc", (void*)&glBlendFunc},
		{"nglBitmap", "(IIFFFFLjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglBitmap, "glBitmap", (void*)&glBitmap},
		{"nglBitmapBO", "(IIFFFFI)V", (void*)&Java_org_lwjgl_opengl_GL11_nglBitmapBO, NULL, NULL},
		{"glBindTexture", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glBindTexture, "glBindTexture", (void*)&glBindTexture},
		{"glBegin", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glBegin, "glBegin", (void*)&glBegin},
		{"glEnd", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glEnd, "glEnd", (void*)&glEnd},
		{"glArrayElement", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glArrayElement, "glArrayElement", (void*)&glArrayElement},
		{"glClearDepth", "(D)V", (void*)&Java_org_lwjgl_opengl_GL11_glClearDepth, "glClearDepth", (void*)&glClearDepth},
		{"glDeleteLists", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glDeleteLists, "glDeleteLists", (void*)&glDeleteLists},
		{"nglDeleteTextures", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglDeleteTextures, "glDeleteTextures", (void*)&glDeleteTextures},
		{"glCullFace", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glCullFace, "glCullFace", (void*)&glCullFace},
		{"glCopyTexSubImage2D", "(IIIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glCopyTexSubImage2D, "glCopyTexSubImage2D", (void*)&glCopyTexSubImage2D},
		{"glCopyTexSubImage1D", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glCopyTexSubImage1D, "glCopyTexSubImage1D", (void*)&glCopyTexSubImage1D},
		{"glCopyTexImage2D", "(IIIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glCopyTexImage2D, "glCopyTexImage2D", (void*)&glCopyTexImage2D},
		{"glCopyTexImage1D", "(IIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glCopyTexImage1D, "glCopyTexImage1D", (void*)&glCopyTexImage1D},
		{"glCopyPixels", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glCopyPixels, "glCopyPixels", (void*)&glCopyPixels},
		{"nglColorPointer", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglColorPointer, "glColorPointer", (void*)&glColorPointer},
		{"nglColorPointerVBO", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglColorPointerVBO, NULL, NULL},
		{"glColorMaterial", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glColorMaterial, "glColorMaterial", (void*)&glColorMaterial},
		{"glColorMask", "(ZZZZ)V", (void*)&Java_org_lwjgl_opengl_GL11_glColorMask, "glColorMask", (void*)&glColorMask},
		{"glColor3b", "(BBB)V", (void*)&Java_org_lwjgl_opengl_GL11_glColor3b, "glColor3b", (void*)&glColor3b},
		{"glColor3f", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glColor3f, "glColor3f", (void*)&glColor3f},
		{"glColor3ub", "(BBB)V", (void*)&Java_org_lwjgl_opengl_GL11_glColor3ub, "glColor3ub", (void*)&glColor3ub},
		{"glColor4b", "(BBBB)V", (void*)&Java_org_lwjgl_opengl_GL11_glColor4b, "glColor4b", (void*)&glColor4b},
		{"glColor4f", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glColor4f, "glColor4f", (void*)&glColor4f},
		{"glColor4ub", "(BBBB)V", (void*)&Java_org_lwjgl_opengl_GL11_glColor4ub, "glColor4ub", (void*)&glColor4ub},
		{"nglClipPlane", "(ILjava/nio/DoubleBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglClipPlane, "glClipPlane", (void*)&glClipPlane},
		{"glClearStencil", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glClearStencil, "glClearStencil", (void*)&glClearStencil},
		{"glClearIndex", "(F)V", (void*)&Java_org_lwjgl_opengl_GL11_glClearIndex, "glClearIndex", (void*)&glClearIndex},
		{"glEvalPoint1", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glEvalPoint1, "glEvalPoint1", (void*)&glEvalPoint1},
		{"glEvalPoint2", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glEvalPoint2, "glEvalPoint2", (void*)&glEvalPoint2},
		{"glEvalMesh1", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glEvalMesh1, "glEvalMesh1", (void*)&glEvalMesh1},
		{"glEvalMesh2", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glEvalMesh2, "glEvalMesh2", (void*)&glEvalMesh2},
		{"glEvalCoord1f", "(F)V", (void*)&Java_org_lwjgl_opengl_GL11_glEvalCoord1f, "glEvalCoord1f", (void*)&glEvalCoord1f},
		{"glEvalCoord2f", "(FF)V", (void*)&Java_org_lwjgl_opengl_GL11_glEvalCoord2f, "glEvalCoord2f", (void*)&glEvalCoord2f},
		{"glEnableClientState", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glEnableClientState, "glEnableClientState", (void*)&glEnableClientState},
		{"glDisableClientState", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glDisableClientState, "glDisableClientState", (void*)&glDisableClientState},
		{"glEnable", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glEnable, "glEnable", (void*)&glEnable},
		{"glDisable", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glDisable, "glDisable", (void*)&glDisable},
		{"nglEdgeFlagPointer", "(ILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointer, "glEdgeFlagPointer", (void*)&glEdgeFlagPointer},
		{"nglEdgeFlagPointerVBO", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointerVBO, NULL, NULL},
		{"glEdgeFlag", "(Z)V", (void*)&Java_org_lwjgl_opengl_GL11_glEdgeFlag, "glEdgeFlag", (void*)&glEdgeFlag},
		{"nglDrawPixels", "(IIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglDrawPixels, "glDrawPixels", (void*)&glDrawPixels},
		{"nglDrawPixelsBO", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglDrawPixelsBO, NULL, NULL},
		{"nglDrawElements", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglDrawElements, "glDrawElements", (void*)&glDrawElements},
		{"nglDrawElementsVBO", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglDrawElementsVBO, NULL, NULL},
		{"glDrawBuffer", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glDrawBuffer, "glDrawBuffer", (void*)&glDrawBuffer},
		{"glDrawArrays", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glDrawArrays, "glDrawArrays", (void*)&glDrawArrays},
		{"glDepthRange", "(DD)V", (void*)&Java_org_lwjgl_opengl_GL11_glDepthRange, "glDepthRange", (void*)&glDepthRange},
		{"glDepthMask", "(Z)V", (void*)&Java_org_lwjgl_opengl_GL11_glDepthMask, "glDepthMask", (void*)&glDepthMask},
		{"glDepthFunc", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glDepthFunc, "glDepthFunc", (void*)&glDepthFunc},
		{"nglFeedbackBuffer", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglFeedbackBuffer, "glFeedbackBuffer", (void*)&glFeedbackBuffer},
		{"nglGetPixelMapfv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapfv, "glGetPixelMapfv", (void*)&glGetPixelMapfv},
		{"nglGetPixelMapfvBO", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapfvBO, NULL, NULL},
		{"nglGetPixelMapuiv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapuiv, "glGetPixelMapuiv", (void*)&glGetPixelMapuiv},
		{"nglGetPixelMapuivBO", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapuivBO, NULL, NULL},
		{"nglGetPixelMapusv", "(ILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapusv, "glGetPixelMapusv", (void*)&glGetPixelMapusv},
		{"nglGetPixelMapusvBO", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapusvBO, NULL, NULL},
		{"nglGetMaterialfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetMaterialfv, "glGetMaterialfv", (void*)&glGetMaterialfv},
		{"nglGetMaterialiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetMaterialiv, "glGetMaterialiv", (void*)&glGetMaterialiv},
		{"nglGetMapfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetMapfv, "glGetMapfv", (void*)&glGetMapfv},
		{"nglGetMapiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetMapiv, "glGetMapiv", (void*)&glGetMapiv},
		{"nglGetLightfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetLightfv, "glGetLightfv", (void*)&glGetLightfv},
		{"nglGetLightiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetLightiv, "glGetLightiv", (void*)&glGetLightiv},
		{"glGetError", "()I", (void*)&Java_org_lwjgl_opengl_GL11_glGetError, "glGetError", (void*)&glGetError},
		{"nglGetClipPlane", "(ILjava/nio/DoubleBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetClipPlane, "glGetClipPlane", (void*)&glGetClipPlane},
		{"nglGetBooleanv", "(ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetBooleanv, "glGetBooleanv", (void*)&glGetBooleanv},
		{"nglGetDoublev", "(ILjava/nio/DoubleBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetDoublev, "glGetDoublev", (void*)&glGetDoublev},
		{"nglGetFloatv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetFloatv, "glGetFloatv", (void*)&glGetFloatv},
		{"nglGetIntegerv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetIntegerv, "glGetIntegerv", (void*)&glGetIntegerv},
		{"nglGenTextures", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGenTextures, "glGenTextures", (void*)&glGenTextures},
		{"glGenLists", "(I)I", (void*)&Java_org_lwjgl_opengl_GL11_glGenLists, "glGenLists", (void*)&glGenLists},
		{"glFrustum", "(DDDDDD)V", (void*)&Java_org_lwjgl_opengl_GL11_glFrustum, "glFrustum", (void*)&glFrustum},
		{"glFrontFace", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glFrontFace, "glFrontFace", (void*)&glFrontFace},
		{"glFogf", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL11_glFogf, "glFogf", (void*)&glFogf},
		{"glFogi", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glFogi, "glFogi", (void*)&glFogi},
		{"nglFogfv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglFogfv, "glFogfv", (void*)&glFogfv},
		{"nglFogiv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglFogiv, "glFogiv", (void*)&glFogiv},
		{"glFlush", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glFlush, "glFlush", (void*)&glFlush},
		{"glFinish", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glFinish, "glFinish", (void*)&glFinish},
		{"glGetPointerv", "(II)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_GL11_glGetPointerv, "glGetPointerv", (void*)&glGetPointerv},
		{"glIsEnabled", "(I)Z", (void*)&Java_org_lwjgl_opengl_GL11_glIsEnabled, "glIsEnabled", (void*)&glIsEnabled},
		{"nglInterleavedArrays", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglInterleavedArrays, "glInterleavedArrays", (void*)&glInterleavedArrays},
		{"nglInterleavedArraysVBO", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_nglInterleavedArraysVBO, NULL, NULL},
		{"glInitNames", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glInitNames, "glInitNames", (void*)&glInitNames},
		{"glHint", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glHint, "glHint", (void*)&glHint},
		{"nglGetTexParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexParameterfv, "glGetTexParameterfv", (void*)&glGetTexParameterfv},
		{"nglGetTexParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexParameteriv, "glGetTexParameteriv", (void*)&glGetTexParameteriv},
		{"nglGetTexLevelParameterfv", "(IIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameterfv, "glGetTexLevelParameterfv", (void*)&glGetTexLevelParameterfv},
		{"nglGetTexLevelParameteriv", "(IIILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameteriv, "glGetTexLevelParameteriv", (void*)&glGetTexLevelParameteriv},
		{"nglGetTexImage", "(IIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexImage, "glGetTexImage", (void*)&glGetTexImage},
		{"nglGetTexImageBO", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexImageBO, NULL, NULL},
		{"nglGetTexGeniv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexGeniv, "glGetTexGeniv", (void*)&glGetTexGeniv},
		{"nglGetTexGenfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexGenfv, "glGetTexGenfv", (void*)&glGetTexGenfv},
		{"nglGetTexEnviv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexEnviv, "glGetTexEnviv", (void*)&glGetTexEnviv},
		{"nglGetTexEnvfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetTexEnvfv, "glGetTexEnvfv", (void*)&glGetTexEnvfv},
		{"glGetString", "(I)Ljava/lang/String;", (void*)&Java_org_lwjgl_opengl_GL11_glGetString, "glGetString", (void*)&glGetString},
		{"nglGetPolygonStipple", "(Ljava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPolygonStipple, "glGetPolygonStipple", (void*)&glGetPolygonStipple},
		{"nglGetPolygonStippleBO", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglGetPolygonStippleBO, NULL, NULL},
		{"glIsList", "(I)Z", (void*)&Java_org_lwjgl_opengl_GL11_glIsList, "glIsList", (void*)&glIsList},
		{"glMaterialf", "(IIF)V", (void*)&Java_org_lwjgl_opengl_GL11_glMaterialf, "glMaterialf", (void*)&glMaterialf},
		{"glMateriali", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glMateriali, "glMateriali", (void*)&glMateriali},
		{"nglMaterialfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglMaterialfv, "glMaterialfv", (void*)&glMaterialfv},
		{"nglMaterialiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglMaterialiv, "glMaterialiv", (void*)&glMaterialiv},
		{"glMapGrid1f", "(IFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glMapGrid1f, "glMapGrid1f", (void*)&glMapGrid1f},
		{"glMapGrid2f", "(IFFIFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glMapGrid2f, "glMapGrid2f", (void*)&glMapGrid2f},
		{"nglMap2f", "(IFFIIFFIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglMap2f, "glMap2f", (void*)&glMap2f},
		{"nglMap1f", "(IFFIILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglMap1f, "glMap1f", (void*)&glMap1f},
		{"glLogicOp", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glLogicOp, "glLogicOp", (void*)&glLogicOp},
		{"glLoadName", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glLoadName, "glLoadName", (void*)&glLoadName},
		{"nglLoadMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglLoadMatrixf, "glLoadMatrixf", (void*)&glLoadMatrixf},
		{"glLoadIdentity", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glLoadIdentity, "glLoadIdentity", (void*)&glLoadIdentity},
		{"glListBase", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glListBase, "glListBase", (void*)&glListBase},
		{"glLineWidth", "(F)V", (void*)&Java_org_lwjgl_opengl_GL11_glLineWidth, "glLineWidth", (void*)&glLineWidth},
		{"glLineStipple", "(IS)V", (void*)&Java_org_lwjgl_opengl_GL11_glLineStipple, "glLineStipple", (void*)&glLineStipple},
		{"glLightModelf", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL11_glLightModelf, "glLightModelf", (void*)&glLightModelf},
		{"glLightModeli", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glLightModeli, "glLightModeli", (void*)&glLightModeli},
		{"nglLightModelfv", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglLightModelfv, "glLightModelfv", (void*)&glLightModelfv},
		{"nglLightModeliv", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglLightModeliv, "glLightModeliv", (void*)&glLightModeliv},
		{"glLightf", "(IIF)V", (void*)&Java_org_lwjgl_opengl_GL11_glLightf, "glLightf", (void*)&glLightf},
		{"glLighti", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glLighti, "glLighti", (void*)&glLighti},
		{"nglLightfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglLightfv, "glLightfv", (void*)&glLightfv},
		{"nglLightiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglLightiv, "glLightiv", (void*)&glLightiv},
		{"glIsTexture", "(I)Z", (void*)&Java_org_lwjgl_opengl_GL11_glIsTexture, "glIsTexture", (void*)&glIsTexture},
		{"glMatrixMode", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glMatrixMode, "glMatrixMode", (void*)&glMatrixMode},
		{"nglPolygonStipple", "(Ljava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPolygonStipple, "glPolygonStipple", (void*)&glPolygonStipple},
		{"nglPolygonStippleBO", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPolygonStippleBO, NULL, NULL},
		{"glPolygonOffset", "(FF)V", (void*)&Java_org_lwjgl_opengl_GL11_glPolygonOffset, "glPolygonOffset", (void*)&glPolygonOffset},
		{"glPolygonMode", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glPolygonMode, "glPolygonMode", (void*)&glPolygonMode},
		{"glPointSize", "(F)V", (void*)&Java_org_lwjgl_opengl_GL11_glPointSize, "glPointSize", (void*)&glPointSize},
		{"glPixelZoom", "(FF)V", (void*)&Java_org_lwjgl_opengl_GL11_glPixelZoom, "glPixelZoom", (void*)&glPixelZoom},
		{"glPixelTransferf", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL11_glPixelTransferf, "glPixelTransferf", (void*)&glPixelTransferf},
		{"glPixelTransferi", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glPixelTransferi, "glPixelTransferi", (void*)&glPixelTransferi},
		{"glPixelStoref", "(IF)V", (void*)&Java_org_lwjgl_opengl_GL11_glPixelStoref, "glPixelStoref", (void*)&glPixelStoref},
		{"glPixelStorei", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glPixelStorei, "glPixelStorei", (void*)&glPixelStorei},
		{"nglPixelMapfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPixelMapfv, "glPixelMapfv", (void*)&glPixelMapfv},
		{"nglPixelMapfvBO", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPixelMapfvBO, NULL, NULL},
		{"nglPixelMapuiv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPixelMapuiv, "glPixelMapuiv", (void*)&glPixelMapuiv},
		{"nglPixelMapuivBO", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPixelMapuivBO, NULL, NULL},
		{"nglPixelMapusv", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPixelMapusv, "glPixelMapusv", (void*)&glPixelMapusv},
		{"nglPixelMapusvBO", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPixelMapusvBO, NULL, NULL},
		{"glPassThrough", "(F)V", (void*)&Java_org_lwjgl_opengl_GL11_glPassThrough, "glPassThrough", (void*)&glPassThrough},
		{"glOrtho", "(DDDDDD)V", (void*)&Java_org_lwjgl_opengl_GL11_glOrtho, "glOrtho", (void*)&glOrtho},
		{"nglNormalPointer", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglNormalPointer, "glNormalPointer", (void*)&glNormalPointer},
		{"nglNormalPointerVBO", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_nglNormalPointerVBO, NULL, NULL},
		{"glNormal3b", "(BBB)V", (void*)&Java_org_lwjgl_opengl_GL11_glNormal3b, "glNormal3b", (void*)&glNormal3b},
		{"glNormal3f", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glNormal3f, "glNormal3f", (void*)&glNormal3f},
		{"glNormal3i", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glNormal3i, "glNormal3i", (void*)&glNormal3i},
		{"glNewList", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glNewList, "glNewList", (void*)&glNewList},
		{"glEndList", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glEndList, "glEndList", (void*)&glEndList},
		{"nglMultMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglMultMatrixf, "glMultMatrixf", (void*)&glMultMatrixf},
		{"glShadeModel", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glShadeModel, "glShadeModel", (void*)&glShadeModel},
		{"nglSelectBuffer", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglSelectBuffer, "glSelectBuffer", (void*)&glSelectBuffer},
		{"glScissor", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glScissor, "glScissor", (void*)&glScissor},
		{"glScalef", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glScalef, "glScalef", (void*)&glScalef},
		{"glRotatef", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glRotatef, "glRotatef", (void*)&glRotatef},
		{"glRenderMode", "(I)I", (void*)&Java_org_lwjgl_opengl_GL11_glRenderMode, "glRenderMode", (void*)&glRenderMode},
		{"glRectf", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glRectf, "glRectf", (void*)&glRectf},
		{"glRecti", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glRecti, "glRecti", (void*)&glRecti},
		{"nglReadPixels", "(IIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglReadPixels, "glReadPixels", (void*)&glReadPixels},
		{"nglReadPixelsBO", "(IIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglReadPixelsBO, NULL, NULL},
		{"glReadBuffer", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glReadBuffer, "glReadBuffer", (void*)&glReadBuffer},
		{"glRasterPos2f", "(FF)V", (void*)&Java_org_lwjgl_opengl_GL11_glRasterPos2f, "glRasterPos2f", (void*)&glRasterPos2f},
		{"glRasterPos2i", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glRasterPos2i, "glRasterPos2i", (void*)&glRasterPos2i},
		{"glRasterPos3f", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glRasterPos3f, "glRasterPos3f", (void*)&glRasterPos3f},
		{"glRasterPos3i", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glRasterPos3i, "glRasterPos3i", (void*)&glRasterPos3i},
		{"glRasterPos4f", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glRasterPos4f, "glRasterPos4f", (void*)&glRasterPos4f},
		{"glRasterPos4i", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glRasterPos4i, "glRasterPos4i", (void*)&glRasterPos4i},
		{"glPushName", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glPushName, "glPushName", (void*)&glPushName},
		{"glPopName", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glPopName, "glPopName", (void*)&glPopName},
		{"glPushMatrix", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glPushMatrix, "glPushMatrix", (void*)&glPushMatrix},
		{"glPopMatrix", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glPopMatrix, "glPopMatrix", (void*)&glPopMatrix},
		{"nglPushClientAttrib", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglPushClientAttrib, "glPushClientAttrib", (void*)&glPushClientAttrib},
		{"nglPopClientAttrib", "()V", (void*)&Java_org_lwjgl_opengl_GL11_nglPopClientAttrib, "glPopClientAttrib", (void*)&glPopClientAttrib},
		{"glPushAttrib", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glPushAttrib, "glPushAttrib", (void*)&glPushAttrib},
		{"glPopAttrib", "()V", (void*)&Java_org_lwjgl_opengl_GL11_glPopAttrib, "glPopAttrib", (void*)&glPopAttrib},
		{"glStencilFunc", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glStencilFunc, "glStencilFunc", (void*)&glStencilFunc},
		{"nglVertexPointer", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglVertexPointer, "glVertexPointer", (void*)&glVertexPointer},
		{"nglVertexPointerVBO", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglVertexPointerVBO, NULL, NULL},
		{"glVertex2f", "(FF)V", (void*)&Java_org_lwjgl_opengl_GL11_glVertex2f, "glVertex2f", (void*)&glVertex2f},
		{"glVertex2i", "(II)V", (void*)&Java_org_lwjgl_opengl_GL11_glVertex2i, "glVertex2i", (void*)&glVertex2i},
		{"glVertex3f", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glVertex3f, "glVertex3f", (void*)&glVertex3f},
		{"glVertex3i", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glVertex3i, "glVertex3i", (void*)&glVertex3i},
		{"glVertex4f", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glVertex4f, "glVertex4f", (void*)&glVertex4f},
		{"glVertex4i", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glVertex4i, "glVertex4i", (void*)&glVertex4i},
		{"glTranslatef", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glTranslatef, "glTranslatef", (void*)&glTranslatef},
		{"nglTexSubImage2D", "(IIIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexSubImage2D, "glTexSubImage2D", (void*)&glTexSubImage2D},
		{"nglTexSubImage2DBO", "(IIIIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexSubImage2DBO, NULL, NULL},
		{"nglTexSubImage1D", "(IIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexSubImage1D, "glTexSubImage1D", (void*)&glTexSubImage1D},
		{"nglTexSubImage1DBO", "(IIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexSubImage1DBO, NULL, NULL},
		{"glTexParameterf", "(IIF)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexParameterf, "glTexParameterf", (void*)&glTexParameterf},
		{"glTexParameteri", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexParameteri, "glTexParameteri", (void*)&glTexParameteri},
		{"nglTexParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexParameterfv, "glTexParameterfv", (void*)&glTexParameterfv},
		{"nglTexParameteriv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexParameteriv, "glTexParameteriv", (void*)&glTexParameteriv},
		{"nglTexImage2D", "(IIIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexImage2D, "glTexImage2D", (void*)&glTexImage2D},
		{"nglTexImage2DBO", "(IIIIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexImage2DBO, NULL, NULL},
		{"nglTexImage1D", "(IIIIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexImage1D, "glTexImage1D", (void*)&glTexImage1D},
		{"nglTexImage1DBO", "(IIIIIIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexImage1DBO, NULL, NULL},
		{"glTexGenf", "(IIF)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexGenf, "glTexGenf", (void*)&glTexGenf},
		{"nglTexGenfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexGenfv, "glTexGenfv", (void*)&glTexGenfv},
		{"glTexGeni", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexGeni, "glTexGeni", (void*)&glTexGeni},
		{"nglTexGeniv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexGeniv, "glTexGeniv", (void*)&glTexGeniv},
		{"glTexEnvf", "(IIF)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexEnvf, "glTexEnvf", (void*)&glTexEnvf},
		{"glTexEnvi", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexEnvi, "glTexEnvi", (void*)&glTexEnvi},
		{"nglTexEnvfv", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexEnvfv, "glTexEnvfv", (void*)&glTexEnvfv},
		{"nglTexEnviv", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexEnviv, "glTexEnviv", (void*)&glTexEnviv},
		{"nglTexCoordPointer", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexCoordPointer, "glTexCoordPointer", (void*)&glTexCoordPointer},
		{"nglTexCoordPointerVBO", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_nglTexCoordPointerVBO, NULL, NULL},
		{"glTexCoord1f", "(F)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexCoord1f, "glTexCoord1f", (void*)&glTexCoord1f},
		{"glTexCoord2f", "(FF)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexCoord2f, "glTexCoord2f", (void*)&glTexCoord2f},
		{"glTexCoord3f", "(FFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexCoord3f, "glTexCoord3f", (void*)&glTexCoord3f},
		{"glTexCoord4f", "(FFFF)V", (void*)&Java_org_lwjgl_opengl_GL11_glTexCoord4f, "glTexCoord4f", (void*)&glTexCoord4f},
		{"glStencilOp", "(III)V", (void*)&Java_org_lwjgl_opengl_GL11_glStencilOp, "glStencilOp", (void*)&glStencilOp},
		{"glStencilMask", "(I)V", (void*)&Java_org_lwjgl_opengl_GL11_glStencilMask, "glStencilMask", (void*)&glStencilMask},
		{"glViewport", "(IIII)V", (void*)&Java_org_lwjgl_opengl_GL11_glViewport, "glViewport", (void*)&glViewport}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif

