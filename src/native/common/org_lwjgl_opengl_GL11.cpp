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
 * Core OpenGL functions.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include "org_lwjgl_opengl_GL11.h"
#include "checkGLerror.h"
#include "extgl.h"

typedef void (APIENTRY * glAccumPROC) (GLenum op, GLfloat value);
typedef void (APIENTRY * glAlphaFuncPROC) (GLenum func, GLclampf ref);
typedef GLboolean (APIENTRY * glAreTexturesResidentPROC) (GLsizei n, const GLuint *textures, GLboolean *residences);
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
typedef void (APIENTRY * glColor3bvPROC) (const GLbyte *v);
typedef void (APIENTRY * glColor3dPROC) (GLdouble red, GLdouble green, GLdouble blue);
typedef void (APIENTRY * glColor3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glColor3fPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY * glColor3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glColor3iPROC) (GLint red, GLint green, GLint blue);
typedef void (APIENTRY * glColor3ivPROC) (const GLint *v);
typedef void (APIENTRY * glColor3sPROC) (GLshort red, GLshort green, GLshort blue);
typedef void (APIENTRY * glColor3svPROC) (const GLshort *v);
typedef void (APIENTRY * glColor3ubPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY * glColor3ubvPROC) (const GLubyte *v);
typedef void (APIENTRY * glColor3uiPROC) (GLuint red, GLuint green, GLuint blue);
typedef void (APIENTRY * glColor3uivPROC) (const GLuint *v);
typedef void (APIENTRY * glColor3usPROC) (GLushort red, GLushort green, GLushort blue);
typedef void (APIENTRY * glColor3usvPROC) (const GLushort *v);
typedef void (APIENTRY * glColor4bPROC) (GLbyte red, GLbyte green, GLbyte blue, GLbyte alpha);
typedef void (APIENTRY * glColor4bvPROC) (const GLbyte *v);
typedef void (APIENTRY * glColor4dPROC) (GLdouble red, GLdouble green, GLdouble blue, GLdouble alpha);
typedef void (APIENTRY * glColor4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glColor4fPROC) (GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha);
typedef void (APIENTRY * glColor4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glColor4iPROC) (GLint red, GLint green, GLint blue, GLint alpha);
typedef void (APIENTRY * glColor4ivPROC) (const GLint *v);
typedef void (APIENTRY * glColor4sPROC) (GLshort red, GLshort green, GLshort blue, GLshort alpha);
typedef void (APIENTRY * glColor4svPROC) (const GLshort *v);
typedef void (APIENTRY * glColor4ubPROC) (GLubyte red, GLubyte green, GLubyte blue, GLubyte alpha);
typedef void (APIENTRY * glColor4ubvPROC) (const GLubyte *v);
typedef void (APIENTRY * glColor4uiPROC) (GLuint red, GLuint green, GLuint blue, GLuint alpha);
typedef void (APIENTRY * glColor4uivPROC) (const GLuint *v);
typedef void (APIENTRY * glColor4usPROC) (GLushort red, GLushort green, GLushort blue, GLushort alpha);
typedef void (APIENTRY * glColor4usvPROC) (const GLushort *v);
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
typedef void (APIENTRY * glEdgeFlagvPROC) (const GLboolean *flag);
typedef void (APIENTRY * glEnablePROC) (GLenum cap);
typedef void (APIENTRY * glEnableClientStatePROC) (GLenum array);
typedef void (APIENTRY * glEndPROC) (void);
typedef void (APIENTRY * glEndListPROC) (void);
typedef void (APIENTRY * glEvalCoord1dPROC) (GLdouble u);
typedef void (APIENTRY * glEvalCoord1dvPROC) (const GLdouble *u);
typedef void (APIENTRY * glEvalCoord1fPROC) (GLfloat u);
typedef void (APIENTRY * glEvalCoord1fvPROC) (const GLfloat *u);
typedef void (APIENTRY * glEvalCoord2dPROC) (GLdouble u, GLdouble v);
typedef void (APIENTRY * glEvalCoord2dvPROC) (const GLdouble *u);
typedef void (APIENTRY * glEvalCoord2fPROC) (GLfloat u, GLfloat v);
typedef void (APIENTRY * glEvalCoord2fvPROC) (const GLfloat *u);
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
//typedef GLenum (APIENTRY * glGetErrorPROC) (void);
typedef void (APIENTRY * glGetFloatvPROC) (GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetIntegervPROC) (GLenum pname, GLint *params);
typedef void (APIENTRY * glGetLightfvPROC) (GLenum light, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetLightivPROC) (GLenum light, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetMapdvPROC) (GLenum target, GLenum query, GLdouble *v);
typedef void (APIENTRY * glGetMapfvPROC) (GLenum target, GLenum query, GLfloat *v);
typedef void (APIENTRY * glGetMapivPROC) (GLenum target, GLenum query, GLint *v);
typedef void (APIENTRY * glGetMaterialfvPROC) (GLenum face, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetMaterialivPROC) (GLenum face, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetPixelMapfvPROC) (GLenum map, GLfloat *values);
typedef void (APIENTRY * glGetPixelMapuivPROC) (GLenum map, GLuint *values);
typedef void (APIENTRY * glGetPixelMapusvPROC) (GLenum map, GLushort *values);
typedef void (APIENTRY * glGetPointervPROC) (GLenum pname, GLvoid* *params);
typedef void (APIENTRY * glGetPolygonStipplePROC) (GLubyte *mask);
//typedef const GLubyte * (APIENTRY * glGetStringPROC) (GLenum name);
typedef void (APIENTRY * glGetTexEnvfvPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexEnvivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexGendvPROC) (GLenum coord, GLenum pname, GLdouble *params);
typedef void (APIENTRY * glGetTexGenfvPROC) (GLenum coord, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexGenivPROC) (GLenum coord, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexImagePROC) (GLenum target, GLint level, GLenum format, GLenum type, GLvoid *pixels);
typedef void (APIENTRY * glGetTexLevelParameterfvPROC) (GLenum target, GLint level, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexLevelParameterivPROC) (GLenum target, GLint level, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexParameterivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glHintPROC) (GLenum target, GLenum mode);
typedef void (APIENTRY * glIndexMaskPROC) (GLuint mask);
typedef void (APIENTRY * glIndexPointerPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glIndexdPROC) (GLdouble c);
typedef void (APIENTRY * glIndexdvPROC) (const GLdouble *c);
typedef void (APIENTRY * glIndexfPROC) (GLfloat c);
typedef void (APIENTRY * glIndexfvPROC) (const GLfloat *c);
typedef void (APIENTRY * glIndexiPROC) (GLint c);
typedef void (APIENTRY * glIndexivPROC) (const GLint *c);
typedef void (APIENTRY * glIndexsPROC) (GLshort c);
typedef void (APIENTRY * glIndexsvPROC) (const GLshort *c);
typedef void (APIENTRY * glIndexubPROC) (GLubyte c);
typedef void (APIENTRY * glIndexubvPROC) (const GLubyte *c);
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
typedef void (APIENTRY * glLoadMatrixdPROC) (const GLdouble *m);
typedef void (APIENTRY * glLoadMatrixfPROC) (const GLfloat *m);
typedef void (APIENTRY * glLoadNamePROC) (GLuint name);
typedef void (APIENTRY * glLogicOpPROC) (GLenum opcode);
typedef void (APIENTRY * glMap1dPROC) (GLenum target, GLdouble u1, GLdouble u2, GLint stride, GLint order, const GLdouble *points);
typedef void (APIENTRY * glMap1fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint stride, GLint order, const GLfloat *points);
typedef void (APIENTRY * glMap2dPROC) (GLenum target, GLdouble u1, GLdouble u2, GLint ustride, GLint uorder, GLdouble v1, GLdouble v2, GLint vstride, GLint vorder, const GLdouble *points);
typedef void (APIENTRY * glMap2fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint ustride, GLint uorder, GLfloat v1, GLfloat v2, GLint vstride, GLint vorder, const GLfloat *points);
typedef void (APIENTRY * glMapGrid1dPROC) (GLint un, GLdouble u1, GLdouble u2);
typedef void (APIENTRY * glMapGrid1fPROC) (GLint un, GLfloat u1, GLfloat u2);
typedef void (APIENTRY * glMapGrid2dPROC) (GLint un, GLdouble u1, GLdouble u2, GLint vn, GLdouble v1, GLdouble v2);
typedef void (APIENTRY * glMapGrid2fPROC) (GLint un, GLfloat u1, GLfloat u2, GLint vn, GLfloat v1, GLfloat v2);
typedef void (APIENTRY * glMaterialfPROC) (GLenum face, GLenum pname, GLfloat param);
typedef void (APIENTRY * glMaterialfvPROC) (GLenum face, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glMaterialiPROC) (GLenum face, GLenum pname, GLint param);
typedef void (APIENTRY * glMaterialivPROC) (GLenum face, GLenum pname, const GLint *params);
typedef void (APIENTRY * glMatrixModePROC) (GLenum mode);
typedef void (APIENTRY * glMultMatrixdPROC) (const GLdouble *m);
typedef void (APIENTRY * glMultMatrixfPROC) (const GLfloat *m);
typedef void (APIENTRY * glNewListPROC) (GLuint list, GLenum mode);
typedef void (APIENTRY * glNormal3bPROC) (GLbyte nx, GLbyte ny, GLbyte nz);
typedef void (APIENTRY * glNormal3bvPROC) (const GLbyte *v);
typedef void (APIENTRY * glNormal3dPROC) (GLdouble nx, GLdouble ny, GLdouble nz);
typedef void (APIENTRY * glNormal3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glNormal3fPROC) (GLfloat nx, GLfloat ny, GLfloat nz);
typedef void (APIENTRY * glNormal3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glNormal3iPROC) (GLint nx, GLint ny, GLint nz);
typedef void (APIENTRY * glNormal3ivPROC) (const GLint *v);
typedef void (APIENTRY * glNormal3sPROC) (GLshort nx, GLshort ny, GLshort nz);
typedef void (APIENTRY * glNormal3svPROC) (const GLshort *v);
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
typedef void (APIENTRY * glPrioritizeTexturesPROC) (GLsizei n, const GLuint *textures, const GLclampf *priorities);
typedef void (APIENTRY * glPushAttribPROC) (GLbitfield mask);
typedef void (APIENTRY * glPushClientAttribPROC) (GLbitfield mask);
typedef void (APIENTRY * glPushMatrixPROC) (void);
typedef void (APIENTRY * glPushNamePROC) (GLuint name);
typedef void (APIENTRY * glRasterPos2dPROC) (GLdouble x, GLdouble y);
typedef void (APIENTRY * glRasterPos2dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glRasterPos2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glRasterPos2fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glRasterPos2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glRasterPos2ivPROC) (const GLint *v);
typedef void (APIENTRY * glRasterPos2sPROC) (GLshort x, GLshort y);
typedef void (APIENTRY * glRasterPos2svPROC) (const GLshort *v);
typedef void (APIENTRY * glRasterPos3dPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glRasterPos3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glRasterPos3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glRasterPos3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glRasterPos3iPROC) (GLint x, GLint y, GLint z);

typedef void (APIENTRY * glRasterPos3ivPROC) (const GLint *v);
typedef void (APIENTRY * glRasterPos3sPROC) (GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glRasterPos3svPROC) (const GLshort *v);
typedef void (APIENTRY * glRasterPos4dPROC) (GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glRasterPos4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glRasterPos4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glRasterPos4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glRasterPos4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glRasterPos4ivPROC) (const GLint *v);
typedef void (APIENTRY * glRasterPos4sPROC) (GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glRasterPos4svPROC) (const GLshort *v);
typedef void (APIENTRY * glReadBufferPROC) (GLenum mode);
typedef void (APIENTRY * glReadPixelsPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, GLvoid *pixels);
typedef void (APIENTRY * glRectdPROC) (GLdouble x1, GLdouble y1, GLdouble x2, GLdouble y2);
typedef void (APIENTRY * glRectdvPROC) (const GLdouble *v1, const GLdouble *v2);
typedef void (APIENTRY * glRectfPROC) (GLfloat x1, GLfloat y1, GLfloat x2, GLfloat y2);
typedef void (APIENTRY * glRectfvPROC) (const GLfloat *v1, const GLfloat *v2);
typedef void (APIENTRY * glRectiPROC) (GLint x1, GLint y1, GLint x2, GLint y2);
typedef void (APIENTRY * glRectivPROC) (const GLint *v1, const GLint *v2);
typedef void (APIENTRY * glRectsPROC) (GLshort x1, GLshort y1, GLshort x2, GLshort y2);
typedef void (APIENTRY * glRectsvPROC) (const GLshort *v1, const GLshort *v2);
typedef GLint (APIENTRY * glRenderModePROC) (GLenum mode);
typedef void (APIENTRY * glRotatedPROC) (GLdouble angle, GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glRotatefPROC) (GLfloat angle, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glScaledPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glScalefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glScissorPROC) (GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY * glSelectBufferPROC) (GLsizei size, GLuint *buffer);
typedef void (APIENTRY * glShadeModelPROC) (GLenum mode);
typedef void (APIENTRY * glStencilFuncPROC) (GLenum func, GLint ref, GLuint mask);
typedef void (APIENTRY * glStencilMaskPROC) (GLuint mask);
typedef void (APIENTRY * glStencilOpPROC) (GLenum fail, GLenum zfail, GLenum zpass);
typedef void (APIENTRY * glTexCoord1dPROC) (GLdouble s);
typedef void (APIENTRY * glTexCoord1dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord1fPROC) (GLfloat s);
typedef void (APIENTRY * glTexCoord1fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord1iPROC) (GLint s);
typedef void (APIENTRY * glTexCoord1ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord1sPROC) (GLshort s);
typedef void (APIENTRY * glTexCoord1svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoord2dPROC) (GLdouble s, GLdouble t);
typedef void (APIENTRY * glTexCoord2dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord2fPROC) (GLfloat s, GLfloat t);
typedef void (APIENTRY * glTexCoord2fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord2iPROC) (GLint s, GLint t);
typedef void (APIENTRY * glTexCoord2ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord2sPROC) (GLshort s, GLshort t);
typedef void (APIENTRY * glTexCoord2svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoord3dPROC) (GLdouble s, GLdouble t, GLdouble r);
typedef void (APIENTRY * glTexCoord3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord3fPROC) (GLfloat s, GLfloat t, GLfloat r);
typedef void (APIENTRY * glTexCoord3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord3iPROC) (GLint s, GLint t, GLint r);
typedef void (APIENTRY * glTexCoord3ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord3sPROC) (GLshort s, GLshort t, GLshort r);
typedef void (APIENTRY * glTexCoord3svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoord4dPROC) (GLdouble s, GLdouble t, GLdouble r, GLdouble q);
typedef void (APIENTRY * glTexCoord4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord4fPROC) (GLfloat s, GLfloat t, GLfloat r, GLfloat q);
typedef void (APIENTRY * glTexCoord4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord4iPROC) (GLint s, GLint t, GLint r, GLint q);
typedef void (APIENTRY * glTexCoord4ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord4sPROC) (GLshort s, GLshort t, GLshort r, GLshort q);
typedef void (APIENTRY * glTexCoord4svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoordPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glTexEnvfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef void (APIENTRY * glTexEnvfvPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glTexEnviPROC) (GLenum target, GLenum pname, GLint param);
typedef void (APIENTRY * glTexEnvivPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glTexGendPROC) (GLenum coord, GLenum pname, GLdouble param);
typedef void (APIENTRY * glTexGendvPROC) (GLenum coord, GLenum pname, const GLdouble *params);
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
typedef void (APIENTRY * glTranslatedPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glTranslatefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertex2dPROC) (GLdouble x, GLdouble y);
typedef void (APIENTRY * glVertex2dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glVertex2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertex2fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glVertex2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glVertex2ivPROC) (const GLint *v);
typedef void (APIENTRY * glVertex2sPROC) (GLshort x, GLshort y);
typedef void (APIENTRY * glVertex2svPROC) (const GLshort *v);
typedef void (APIENTRY * glVertex3dPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glVertex3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glVertex3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertex3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glVertex3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY * glVertex3ivPROC) (const GLint *v);
typedef void (APIENTRY * glVertex3sPROC) (GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertex3svPROC) (const GLshort *v);
typedef void (APIENTRY * glVertex4dPROC) (GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glVertex4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glVertex4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertex4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glVertex4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glVertex4ivPROC) (const GLint *v);
typedef void (APIENTRY * glVertex4sPROC) (GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertex4svPROC) (const GLshort *v);
typedef void (APIENTRY * glVertexPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glViewportPROC) (GLint x, GLint y, GLsizei width, GLsizei height);

static glAccumPROC glAccum; 
static glAlphaFuncPROC glAlphaFunc; 
static glAreTexturesResidentPROC glAreTexturesResident; 
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
static glColor3bvPROC glColor3bv; 
static glColor3dPROC glColor3d; 
static glColor3dvPROC glColor3dv; 
static glColor3fPROC glColor3f; 
static glColor3fvPROC glColor3fv; 
static glColor3iPROC glColor3i; 
static glColor3ivPROC glColor3iv; 
static glColor3sPROC glColor3s; 
static glColor3svPROC glColor3sv; 
static glColor3ubPROC glColor3ub; 
static glColor3ubvPROC glColor3ubv; 
static glColor3uiPROC glColor3ui; 
static glColor3uivPROC glColor3uiv; 
static glColor3usPROC glColor3us; 
static glColor3usvPROC glColor3usv; 
static glColor4bPROC glColor4b; 
static glColor4bvPROC glColor4bv; 
static glColor4dPROC glColor4d; 
static glColor4dvPROC glColor4dv; 
static glColor4fPROC glColor4f; 
static glColor4fvPROC glColor4fv; 
static glColor4iPROC glColor4i; 
static glColor4ivPROC glColor4iv; 
static glColor4sPROC glColor4s; 
static glColor4svPROC glColor4sv; 
static glColor4ubPROC glColor4ub; 
static glColor4ubvPROC glColor4ubv; 
static glColor4uiPROC glColor4ui; 
static glColor4uivPROC glColor4uiv; 
static glColor4usPROC glColor4us; 
static glColor4usvPROC glColor4usv; 
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
static glEdgeFlagvPROC glEdgeFlagv; 
static glEnablePROC glEnable; 
static glEnableClientStatePROC glEnableClientState; 
static glEndPROC glEnd; 
static glEndListPROC glEndList; 
static glEvalCoord1dPROC glEvalCoord1d; 
static glEvalCoord1dvPROC glEvalCoord1dv; 
static glEvalCoord1fPROC glEvalCoord1f; 
static glEvalCoord1fvPROC glEvalCoord1fv; 
static glEvalCoord2dPROC glEvalCoord2d; 
static glEvalCoord2dvPROC glEvalCoord2dv; 
static glEvalCoord2fPROC glEvalCoord2f; 
static glEvalCoord2fvPROC glEvalCoord2fv; 
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
glGetErrorPROC glGetError;
static glGetFloatvPROC glGetFloatv; 
static glGetIntegervPROC glGetIntegerv; 
static glGetLightfvPROC glGetLightfv; 
static glGetLightivPROC glGetLightiv; 
static glGetMapdvPROC glGetMapdv; 
static glGetMapfvPROC glGetMapfv; 
static glGetMapivPROC glGetMapiv; 
static glGetMaterialfvPROC glGetMaterialfv; 
static glGetMaterialivPROC glGetMaterialiv; 
static glGetPixelMapfvPROC glGetPixelMapfv; 
static glGetPixelMapuivPROC glGetPixelMapuiv; 
static glGetPixelMapusvPROC glGetPixelMapusv; 
static glGetPointervPROC glGetPointerv; 
static glGetPolygonStipplePROC glGetPolygonStipple; 
glGetStringPROC glGetString;
static glGetTexEnvfvPROC glGetTexEnvfv; 
static glGetTexEnvivPROC glGetTexEnviv; 
static glGetTexGendvPROC glGetTexGendv; 
static glGetTexGenfvPROC glGetTexGenfv; 
static glGetTexGenivPROC glGetTexGeniv; 
static glGetTexImagePROC glGetTexImage; 
static glGetTexLevelParameterfvPROC glGetTexLevelParameterfv; 
static glGetTexLevelParameterivPROC glGetTexLevelParameteriv; 
static glGetTexParameterfvPROC glGetTexParameterfv; 
static glGetTexParameterivPROC glGetTexParameteriv; 
static glHintPROC glHint; 
static glIndexMaskPROC glIndexMask; 
static glIndexPointerPROC glIndexPointer; 
static glIndexdPROC glIndexd; 
static glIndexdvPROC glIndexdv; 
static glIndexfPROC glIndexf; 
static glIndexfvPROC glIndexfv; 
static glIndexiPROC glIndexi; 
static glIndexivPROC glIndexiv; 
static glIndexsPROC glIndexs; 
static glIndexsvPROC glIndexsv; 
static glIndexubPROC glIndexub; 
static glIndexubvPROC glIndexubv; 
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
static glLoadMatrixdPROC glLoadMatrixd; 
static glLoadMatrixfPROC glLoadMatrixf; 
static glLoadNamePROC glLoadName; 
static glLogicOpPROC glLogicOp; 
static glMap1dPROC glMap1d; 
static glMap1fPROC glMap1f; 
static glMap2dPROC glMap2d; 
static glMap2fPROC glMap2f; 
static glMapGrid1dPROC glMapGrid1d; 
static glMapGrid1fPROC glMapGrid1f; 
static glMapGrid2dPROC glMapGrid2d; 
static glMapGrid2fPROC glMapGrid2f; 
static glMaterialfPROC glMaterialf; 
static glMaterialfvPROC glMaterialfv; 
static glMaterialiPROC glMateriali; 
static glMaterialivPROC glMaterialiv; 
static glMatrixModePROC glMatrixMode; 
static glMultMatrixdPROC glMultMatrixd; 
static glMultMatrixfPROC glMultMatrixf; 
static glNewListPROC glNewList; 
static glNormal3bPROC glNormal3b; 
static glNormal3bvPROC glNormal3bv; 
static glNormal3dPROC glNormal3d; 
static glNormal3dvPROC glNormal3dv; 
static glNormal3fPROC glNormal3f; 
static glNormal3fvPROC glNormal3fv; 
static glNormal3iPROC glNormal3i; 
static glNormal3ivPROC glNormal3iv; 
static glNormal3sPROC glNormal3s; 
static glNormal3svPROC glNormal3sv; 
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
static glPrioritizeTexturesPROC glPrioritizeTextures; 
static glPushAttribPROC glPushAttrib; 
static glPushClientAttribPROC glPushClientAttrib; 
static glPushMatrixPROC glPushMatrix; 
static glPushNamePROC glPushName; 
static glRasterPos2dPROC glRasterPos2d; 
static glRasterPos2dvPROC glRasterPos2dv; 
static glRasterPos2fPROC glRasterPos2f; 
static glRasterPos2fvPROC glRasterPos2fv; 
static glRasterPos2iPROC glRasterPos2i; 
static glRasterPos2ivPROC glRasterPos2iv; 
static glRasterPos2sPROC glRasterPos2s; 
static glRasterPos2svPROC glRasterPos2sv; 
static glRasterPos3dPROC glRasterPos3d; 
static glRasterPos3dvPROC glRasterPos3dv; 
static glRasterPos3fPROC glRasterPos3f; 
static glRasterPos3fvPROC glRasterPos3fv; 
static glRasterPos3iPROC glRasterPos3i; 

static glRasterPos3ivPROC glRasterPos3iv; 
static glRasterPos3sPROC glRasterPos3s; 
static glRasterPos3svPROC glRasterPos3sv; 
static glRasterPos4dPROC glRasterPos4d; 
static glRasterPos4dvPROC glRasterPos4dv; 
static glRasterPos4fPROC glRasterPos4f; 
static glRasterPos4fvPROC glRasterPos4fv; 
static glRasterPos4iPROC glRasterPos4i; 
static glRasterPos4ivPROC glRasterPos4iv; 
static glRasterPos4sPROC glRasterPos4s; 
static glRasterPos4svPROC glRasterPos4sv; 
static glReadBufferPROC glReadBuffer; 
static glReadPixelsPROC glReadPixels; 
static glRectdPROC glRectd; 
static glRectdvPROC glRectdv; 
static glRectfPROC glRectf; 
static glRectfvPROC glRectfv; 
static glRectiPROC glRecti; 
static glRectivPROC glRectiv; 
static glRectsPROC glRects; 
static glRectsvPROC glRectsv; 
static glRenderModePROC glRenderMode; 
static glRotatedPROC glRotated; 
static glRotatefPROC glRotatef; 
static glScaledPROC glScaled; 
static glScalefPROC glScalef; 
static glScissorPROC glScissor; 
static glSelectBufferPROC glSelectBuffer; 
static glShadeModelPROC glShadeModel; 
static glStencilFuncPROC glStencilFunc; 
static glStencilMaskPROC glStencilMask; 
static glStencilOpPROC glStencilOp; 
static glTexCoord1dPROC glTexCoord1d; 
static glTexCoord1dvPROC glTexCoord1dv; 
static glTexCoord1fPROC glTexCoord1f; 
static glTexCoord1fvPROC glTexCoord1fv; 
static glTexCoord1iPROC glTexCoord1i; 
static glTexCoord1ivPROC glTexCoord1iv; 
static glTexCoord1sPROC glTexCoord1s; 
static glTexCoord1svPROC glTexCoord1sv; 
static glTexCoord2dPROC glTexCoord2d; 
static glTexCoord2dvPROC glTexCoord2dv; 
static glTexCoord2fPROC glTexCoord2f; 
static glTexCoord2fvPROC glTexCoord2fv; 
static glTexCoord2iPROC glTexCoord2i; 
static glTexCoord2ivPROC glTexCoord2iv; 
static glTexCoord2sPROC glTexCoord2s; 
static glTexCoord2svPROC glTexCoord2sv; 
static glTexCoord3dPROC glTexCoord3d; 
static glTexCoord3dvPROC glTexCoord3dv; 
static glTexCoord3fPROC glTexCoord3f; 
static glTexCoord3fvPROC glTexCoord3fv; 
static glTexCoord3iPROC glTexCoord3i; 
static glTexCoord3ivPROC glTexCoord3iv; 
static glTexCoord3sPROC glTexCoord3s; 
static glTexCoord3svPROC glTexCoord3sv; 
static glTexCoord4dPROC glTexCoord4d; 
static glTexCoord4dvPROC glTexCoord4dv; 
static glTexCoord4fPROC glTexCoord4f; 
static glTexCoord4fvPROC glTexCoord4fv; 
static glTexCoord4iPROC glTexCoord4i; 
static glTexCoord4ivPROC glTexCoord4iv; 
static glTexCoord4sPROC glTexCoord4s; 
static glTexCoord4svPROC glTexCoord4sv; 
static glTexCoordPointerPROC glTexCoordPointer; 
static glTexEnvfPROC glTexEnvf; 
static glTexEnvfvPROC glTexEnvfv; 
static glTexEnviPROC glTexEnvi; 
static glTexEnvivPROC glTexEnviv; 
static glTexGendPROC glTexGend; 
static glTexGendvPROC glTexGendv; 
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
static glTranslatedPROC glTranslated; 
static glTranslatefPROC glTranslatef; 
static glVertex2dPROC glVertex2d; 
static glVertex2dvPROC glVertex2dv; 
static glVertex2fPROC glVertex2f; 
static glVertex2fvPROC glVertex2fv; 
static glVertex2iPROC glVertex2i; 
static glVertex2ivPROC glVertex2iv; 
static glVertex2sPROC glVertex2s; 
static glVertex2svPROC glVertex2sv; 
static glVertex3dPROC glVertex3d; 
static glVertex3dvPROC glVertex3dv; 
static glVertex3fPROC glVertex3f; 
static glVertex3fvPROC glVertex3fv; 
static glVertex3iPROC glVertex3i; 
static glVertex3ivPROC glVertex3iv; 
static glVertex3sPROC glVertex3s; 
static glVertex3svPROC glVertex3sv; 
static glVertex4dPROC glVertex4d; 
static glVertex4dvPROC glVertex4dv; 
static glVertex4fPROC glVertex4f; 
static glVertex4fvPROC glVertex4fv; 
static glVertex4iPROC glVertex4i; 
static glVertex4ivPROC glVertex4iv; 
static glVertex4sPROC glVertex4s; 
static glVertex4svPROC glVertex4sv; 
static glVertexPointerPROC glVertexPointer; 
static glViewportPROC glViewport; 

void extgl_InitOpenGL1_1(void)
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

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glAccum(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAccum((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glAlphaFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glAlphaFunc(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glAlphaFunc((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glClearColor(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glClearAccum(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearAccum((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClear
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glClear(JNIEnv * env, jclass clazz, jint p0)
{
	glClear((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCallLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglCallLists(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject lists_buffer, jint lists_offset)
{
	GLbyte *lists = (GLbyte *)env->GetDirectBufferAddress(lists_buffer);
	glCallLists((GLint) p0, (GLint) p1, (const void *)(lists + lists_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCallList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glCallList(JNIEnv * env, jclass clazz, jint p0)
{
	glCallList((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBlendFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glBlendFunc(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBlendFunc((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBitmap
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglBitmap(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jobject image_buffer, jint image_offset)
{
	const GLubyte *image = (const GLubyte *)env->GetDirectBufferAddress(image_buffer);
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, image + image_offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBindTexture
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glBindTexture(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glBindTexture((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glBegin
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glBegin(JNIEnv * env, jclass clazz, jint p0)
{
	glBegin((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEnd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEnd(JNIEnv * env, jclass clazz)
{
	glEnd();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glArrayElement
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glArrayElement(JNIEnv * env, jclass clazz, jint p0)
{
	glArrayElement((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearDepth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glClearDepth(JNIEnv * env, jclass clazz, jdouble p0)
{
	glClearDepth((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDeleteLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDeleteLists(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glDeleteLists((GLuint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDeleteTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglDeleteTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLuint *address = (const GLuint *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glDeleteTextures((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCullFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glCullFace(JNIEnv * env, jclass clazz, jint p0)
{
	glCullFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCopyTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCopyTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glCopyPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glCopyPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glCopyPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglColorPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglColorPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint buffer_offset)
{
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) (address + buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglColorPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglColorPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColorMaterial
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColorMaterial(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glColorMaterial((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColorMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColorMask(JNIEnv * env, jclass clazz, jboolean p0, jboolean p1, jboolean p2, jboolean p3)
{
	glColorMask((GLboolean) p0, (GLboolean) p1, (GLboolean) p2, (GLboolean) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColor3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColor3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glColor3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor3ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColor3ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor4b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColor4b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColor4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glColor4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glColor4ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glColor4ub(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLdouble *address = offset + (const GLdouble *)env->GetDirectBufferAddress(buffer);	
	glClipPlane((GLint) p0, address + offset);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearStencil
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glClearStencil(JNIEnv * env, jclass clazz, jint p0)
{
	glClearStencil((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glClearIndex
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glClearIndex(JNIEnv * env, jclass clazz, jfloat p0)
{
	glClearIndex((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalPoint1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEvalPoint1(JNIEnv * env, jclass clazz, jint p0)
{
	glEvalPoint1((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalPoint2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEvalPoint2(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glEvalPoint2((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalMesh1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEvalMesh1(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glEvalMesh1((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalMesh2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEvalMesh2(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glEvalMesh2((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEvalCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glEvalCoord1f((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEvalCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEvalCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glEvalCoord2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEnableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEnableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glEnableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDisableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDisableClientState(JNIEnv * env, jclass clazz, jint p0)
{
	glDisableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEnable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEnable(JNIEnv * env, jclass clazz, jint p0)
{
	glEnable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDisable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDisable(JNIEnv * env, jclass clazz, jint p0)
{
	glDisable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglEdgeFlagPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointer(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLbyte *address = offset + (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glEdgeFlagPointer((GLint) p0, (const void *)address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglEdgeFlagPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint buffer_offset)
{
	glEdgeFlagPointer((GLint) p0, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEdgeFlag
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEdgeFlag(JNIEnv * env, jclass clazz, jboolean p0)
{
	glEdgeFlag((GLboolean) p0);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDrawPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint offset)
{
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const void *)(address + offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglDrawElements
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawElements(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const GLbyte *address = (const GLbyte *)env->GetDirectBufferAddress(buffer);
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, (const void *)(address + offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglDrawElementsVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawElementsVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDrawBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDrawBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glDrawBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDrawArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDrawArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glDrawArrays((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDepthRange
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDepthRange(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1)
{
	glDepthRange((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDepthMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDepthMask(JNIEnv * env, jclass clazz, jboolean p0)
{
	glDepthMask((GLboolean) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glDepthFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glDepthFunc(JNIEnv * env, jclass clazz, jint p0)
{
	glDepthFunc((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFeedbackBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglFeedbackBuffer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glFeedbackBuffer((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapuiv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLushort *address = (GLushort *)env->GetDirectBufferAddress(buffer);
	glGetPixelMapusv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMaterialfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMaterialiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetMapfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetMapiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMapiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetMapiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetLightfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetLightiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetError
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL11_glGetError(JNIEnv * env, jclass clazz)
{
	jint ret = (jint) glGetError();
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetClipPlane(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLdouble *address = offset + (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetClipPlane((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetBooleanv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetBooleanv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLubyte *address = offset + (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetBooleanv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetDoublev
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetDoublev(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLdouble *address = offset + (GLdouble *)env->GetDirectBufferAddress(buffer);
	glGetDoublev((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetFloatv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetFloatv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetFloatv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetIntegerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetIntegerv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetIntegerv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGenTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGenTextures(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)env->GetDirectBufferAddress(buffer);
	glGenTextures((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGenLists
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL11_glGenLists(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glGenLists((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFrustum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glFrustum(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glFrustum((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFrontFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glFrontFace(JNIEnv * env, jclass clazz, jint p0)
{
	glFrontFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glFogf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glFogf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glFogi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glFogi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglFogfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glFogfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFogiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglFogiv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glFogiv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFlush
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glFlush(JNIEnv * env, jclass clazz)
{
	glFlush();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glFinish
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glFinish(JNIEnv * env, jclass clazz)
{
	glFinish();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPointerv
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GL11_glGetPointerv(JNIEnv * env, jclass clazz, jint p0, int size)
{
	void *pointer;
	glGetPointerv((GLint) p0, &pointer);
	CHECK_GL_ERROR
	return safeNewBuffer(env, pointer, size);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glIsEnabled
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsEnabled(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsEnabled((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglInterleavedArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglInterleavedArrays(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glInterleavedArrays((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglInterleavedArraysVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglInterleavedArraysVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glInterleavedArrays((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glInitNames
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glInitNames(JNIEnv * env, jclass clazz)
{
	glInitNames();
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glHint
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glHint(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glHint((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexParameterfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexParameteriv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexLevelParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameterfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexLevelParameterfv((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexLevelParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameteriv(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexLevelParameteriv((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexImage
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexImage(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexGenfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLfloat *address = offset + (GLfloat *)env->GetDirectBufferAddress(buffer);
	glGetTexEnvfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	GLint *address = offset + (GLint *)env->GetDirectBufferAddress(buffer);
	glGetTexEnviv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GL11_glGetString(JNIEnv * env, jclass clazz, jint p0)
{
	const GLubyte * string = glGetString((GLenum) p0);
	return env->NewStringUTF((const char *) string);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glGetPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	GLubyte *address = offset + (GLubyte *)env->GetDirectBufferAddress(buffer);
	glGetPolygonStipple(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glIsList
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsList(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsList((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMaterialf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glMaterialf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glMaterialf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMateriali
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glMateriali(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glMateriali((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglMaterialfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMaterialfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglMaterialiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glMaterialiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMapGrid1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glMapGrid1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2)
{
	glMapGrid1f((GLint) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMapGrid2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glMapGrid2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jfloat p4, jfloat p5)
{
	glMapGrid2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMap2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglMap2f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jfloat p5, jfloat p6, jint p7, jint p8, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMap2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (GLfloat) p5, (GLfloat) p6, (GLint) p7, (GLint) p8, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMap1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglMap1f(JNIEnv * env, jclass clazz, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMap1f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLogicOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLogicOp(JNIEnv * env, jclass clazz, jint p0)
{
	glLogicOp((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLoadName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLoadName(JNIEnv * env, jclass clazz, jint p0)
{
	glLoadName((GLint) p0);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLoadMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglLoadMatrixf(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLoadMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLoadIdentity
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLoadIdentity(JNIEnv * env, jclass clazz)
{
	glLoadIdentity();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glListBase
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glListBase(JNIEnv * env, jclass clazz, jint p0)
{
	glListBase((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLineWidth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLineWidth(JNIEnv * env, jclass clazz, jfloat p0)
{
	glLineWidth((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLineStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLineStipple(JNIEnv * env, jclass clazz, jint p0, jshort p1)
{
	glLineStipple((GLint) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModelf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLightModelf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glLightModelf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModeli
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLightModeli(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glLightModeli((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModelfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglLightModelfv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLightModelfv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightModeliv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglLightModeliv(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glLightModeliv((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLightf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glLightf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLighti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glLighti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glLighti((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglLightfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glLightfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglLightiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glLightiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glIsTexture
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsTexture(JNIEnv * env, jclass clazz, jint p0)
{
	jboolean ret = (jboolean) glIsTexture((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMatrixMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glMatrixMode(JNIEnv * env, jclass clazz, jint p0)
{
	glMatrixMode((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglPolygonStipple(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLubyte *address = offset + (const GLubyte *)env->GetDirectBufferAddress(buffer);
	glPolygonStipple(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPolygonOffset
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPolygonOffset(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPolygonOffset((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPolygonMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPolygonMode(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPolygonMode((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPointSize
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPointSize(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPointSize((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelZoom
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPixelZoom(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glPixelZoom((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelTransferf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPixelTransferf(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelTransferf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelTransferi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPixelTransferi(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelTransferi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelStoref
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPixelStoref(JNIEnv * env, jclass clazz, jint p0, jfloat p1)
{
	glPixelStoref((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelStorei
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPixelStorei(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glPixelStorei((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glPixelMapfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapuiv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLuint *address = (const GLuint *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glPixelMapuiv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapusv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLushort *address = (const GLushort *)(offset + (const GLbyte *)env->GetDirectBufferAddress(buffer));
	glPixelMapusv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPassThrough
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPassThrough(JNIEnv * env, jclass clazz, jfloat p0)
{
	glPassThrough((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glOrtho
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glOrtho(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glOrtho((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglNormalPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglNormalPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glNormalPointer((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglNormalPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglNormalPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint buffer_offset)
{
	glNormalPointer((GLint) p0, (GLint) p1, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNormal3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3b(JNIEnv * env, jclass clazz, jbyte p0, jbyte p1, jbyte p2)
{
	glNormal3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNormal3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glNormal3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNormal3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glNormal3i((GLint) p0, (GLint) p1, (GLint) p2);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glNewList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glNewList(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glNewList((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glEndList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glEndList(JNIEnv * env, jclass clazz)
{
	glEndList();
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glMultMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglMultMatrixf(JNIEnv * env, jclass clazz, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glMultMatrixf(address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glShadeModel
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glShadeModel(JNIEnv * env, jclass clazz, jint p0)
{
	glShadeModel((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glSelectBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglSelectBuffer(JNIEnv * env, jclass clazz, jint p0, jobject buffer, jint offset)
{
	GLuint *address = offset + (GLuint *)env->GetDirectBufferAddress(buffer);
	glSelectBuffer((GLint) p0, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glScissor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glScissor(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glScissor((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glScalef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glScalef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glScalef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRotatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRotatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRotatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRenderMode
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GL11_glRenderMode(JNIEnv * env, jclass clazz, jint p0)
{
	jint ret = (jint) glRenderMode((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRectf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRectf(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRectf((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRecti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRecti(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRecti((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glReadPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglReadPixels(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint offset)
{
	void *address = (void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glReadBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glReadBuffer(JNIEnv * env, jclass clazz, jint p0)
{
	glReadBuffer((GLint) p0);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glRasterPos2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glRasterPos2i((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glRasterPos3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glRasterPos3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRasterPos4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glRasterPos4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glRasterPos4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPushName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPushName(JNIEnv * env, jclass clazz, jint p0)
{
	glPushName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPopName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPopName(JNIEnv * env, jclass clazz)
{
	glPopName();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPushMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPushMatrix(JNIEnv * env, jclass clazz)
{
	glPushMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPopMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPopMatrix(JNIEnv * env, jclass clazz)
{
	glPopMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglPushClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglPushClientAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushClientAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglPopClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglPopClientAttrib(JNIEnv * env, jclass clazz)
{
	glPopClientAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPushAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPushAttrib(JNIEnv * env, jclass clazz, jint p0)
{
	glPushAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glPopAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glPopAttrib(JNIEnv * env, jclass clazz)
{
	glPopAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glStencilFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glStencilFunc(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilFunc((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglVertexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglVertexPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglVertexPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglVertexPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glVertex2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glVertex2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glVertex2i(JNIEnv * env, jclass clazz, jint p0, jint p1)
{
	glVertex2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glVertex3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glVertex3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glVertex3i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glVertex3i((GLint) p0, (GLint) p1, (GLint) p2);
}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glVertex4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertex4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glVertex4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glVertex4i(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glVertex4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTranslatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTranslatef(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTranslatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
	CHECK_GL_ERROR
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexParameterfv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject param, jint param_offset) {
	GLfloat *address = param_offset + (GLfloat *)env->GetDirectBufferAddress(param);
	glTexParameterfv(target, pname, address);
}
  
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexParameteriv
  (JNIEnv *env, jclass clazz, jint target, jint pname, jobject param, jint param_offset) {
	GLint *address = param_offset + (GLint *)env->GetDirectBufferAddress(param);
	glTexParameteriv(target, pname, address);
}
    
/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexParameterf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexParameterf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexParameterf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexParameteri
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexParameteri(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexParameteri((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage2D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage1D(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, address);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexGenf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexGenf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexGenf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexGenfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glTexGenfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexGeni
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexGeni(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexGeni((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexGeniv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glTexGeniv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}



/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnvf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexEnvf(JNIEnv * env, jclass clazz, jint p0, jint p1, jfloat p2)
{
	glTexEnvf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnvi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexEnvi(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glTexEnvi((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexEnvfv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLfloat *address = offset + (const GLfloat *)env->GetDirectBufferAddress(buffer);
	glTexEnvfv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexEnviv(JNIEnv * env, jclass clazz, jint p0, jint p1, jobject buffer, jint offset)
{
	const GLint *address = offset + (const GLint *)env->GetDirectBufferAddress(buffer);
	glTexEnviv((GLint) p0, (GLint) p1, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexCoordPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexCoordPointer(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jobject buffer, jint offset)
{
	const void *address = (const void *)(offset + (GLbyte *)env->GetDirectBufferAddress(buffer));
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    nglTexCoordPointerVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_nglTexCoordPointerVBO(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint buffer_offset)
{
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, offsetToPointer(buffer_offset));
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord1f(JNIEnv * env, jclass clazz, jfloat p0)
{
	glTexCoord1f((GLfloat) p0);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord2f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1)
{
	glTexCoord2f((GLfloat) p0, (GLfloat) p1);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord3f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2)
{
	glTexCoord3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glTexCoord4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord4f(JNIEnv * env, jclass clazz, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glTexCoord4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}


/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glStencilOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glStencilOp(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2)
{
	glStencilOp((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glStencilMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glStencilMask(JNIEnv * env, jclass clazz, jint p0)
{
	glStencilMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GL11
 * Method:    glViewport
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_glViewport(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3)
{
	glViewport((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

