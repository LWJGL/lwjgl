/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glViewportPROC) (GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY *glStencilMaskPROC) (GLuint mask);
typedef void (APIENTRY *glStencilOpPROC) (GLenum fail, GLenum zfail, GLenum zpass);
typedef void (APIENTRY *glTexCoord4fPROC) (GLfloat s, GLfloat t, GLfloat r, GLfloat q);
typedef void (APIENTRY *glTexCoord3fPROC) (GLfloat s, GLfloat t, GLfloat r);
typedef void (APIENTRY *glTexCoord2fPROC) (GLfloat s, GLfloat t);
typedef void (APIENTRY *glTexCoord1fPROC) (GLfloat s);
typedef void (APIENTRY *glTexCoordPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid * pointer);
typedef void (APIENTRY *glTexEnvivPROC) (GLenum target, GLenum pname, const GLint * params);
typedef void (APIENTRY *glTexEnvfvPROC) (GLenum target, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glTexEnviPROC) (GLenum target, GLenum pname, GLint param);
typedef void (APIENTRY *glTexEnvfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef void (APIENTRY *glTexGenivPROC) (GLenum coord, GLenum pname, const GLint * params);
typedef void (APIENTRY *glTexGeniPROC) (GLenum coord, GLenum pname, GLint param);
typedef void (APIENTRY *glTexGenfvPROC) (GLenum coord, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glTexGenfPROC) (GLenum coord, GLenum pname, GLfloat param);
typedef void (APIENTRY *glTexParameterivPROC) (GLenum target, GLenum pname, const GLint * param);
typedef void (APIENTRY *glTexParameterfvPROC) (GLenum target, GLenum pname, const GLfloat * param);
typedef void (APIENTRY *glTexParameteriPROC) (GLenum target, GLenum pname, GLint param);
typedef void (APIENTRY *glTexParameterfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef void (APIENTRY *glTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid * pixels);
typedef void (APIENTRY *glTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLenum type, const GLvoid * pixels);
typedef void (APIENTRY *glTexImage2DPROC) (GLenum target, GLint level, GLint internalformat, GLint width, GLint height, GLint border, GLenum format, GLenum type, const GLvoid * pixels);
typedef void (APIENTRY *glTexImage1DPROC) (GLenum target, GLint level, GLint internalformat, GLsizei width, GLint border, GLenum format, GLenum type, const GLvoid * pixels);
typedef void (APIENTRY *glTranslatefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glVertex4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY *glVertex4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glVertex3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY *glVertex3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glVertex2iPROC) (GLint x, GLint y);
typedef void (APIENTRY *glVertex2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY *glVertexPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid * pointer);
typedef void (APIENTRY *glStencilFuncPROC) (GLenum func, GLint ref, GLuint mask);
typedef void (APIENTRY *glPopAttribPROC) ();
typedef void (APIENTRY *glPushAttribPROC) (GLbitfield mask);
typedef void (APIENTRY *glPopClientAttribPROC) ();
typedef void (APIENTRY *glPushClientAttribPROC) (GLbitfield mask);
typedef void (APIENTRY *glPopMatrixPROC) ();
typedef void (APIENTRY *glPushMatrixPROC) ();
typedef void (APIENTRY *glPopNamePROC) ();
typedef void (APIENTRY *glPushNamePROC) (GLuint name);
typedef void (APIENTRY *glRasterPos4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY *glRasterPos4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glRasterPos3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY *glRasterPos3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glRasterPos2iPROC) (GLint x, GLint y);
typedef void (APIENTRY *glRasterPos2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY *glReadBufferPROC) (GLenum mode);
typedef void (APIENTRY *glReadPixelsPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, GLvoid * pixels);
typedef void (APIENTRY *glRectiPROC) (GLint x1, GLint y1, GLint x2, GLint y2);
typedef void (APIENTRY *glRectfPROC) (GLfloat x1, GLfloat y1, GLfloat x2, GLfloat y2);
typedef GLint (APIENTRY *glRenderModePROC) (GLenum mode);
typedef void (APIENTRY *glRotatefPROC) (GLfloat angle, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glScalefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glScissorPROC) (GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY *glSelectBufferPROC) (GLsizei size, GLuint * buffer);
typedef void (APIENTRY *glShadeModelPROC) (GLenum mode);
typedef void (APIENTRY *glMultMatrixfPROC) (const GLfloat * m);
typedef void (APIENTRY *glEndListPROC) ();
typedef void (APIENTRY *glNewListPROC) (GLuint list, GLenum mode);
typedef void (APIENTRY *glNormal3iPROC) (GLint nx, GLint ny, GLint nz);
typedef void (APIENTRY *glNormal3fPROC) (GLfloat nx, GLfloat ny, GLfloat nz);
typedef void (APIENTRY *glNormal3bPROC) (GLbyte nx, GLbyte ny, GLbyte nz);
typedef void (APIENTRY *glNormalPointerPROC) (GLenum type, GLsizei stride, const GLvoid * pointer);
typedef void (APIENTRY *glOrthoPROC) (GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble zNear, GLdouble zFar);
typedef void (APIENTRY *glPassThroughPROC) (GLfloat token);
typedef void (APIENTRY *glPixelMapusvPROC) (GLenum map, GLsizei mapsize, const GLushort * values);
typedef void (APIENTRY *glPixelMapuivPROC) (GLenum map, GLsizei mapsize, const GLuint * values);
typedef void (APIENTRY *glPixelMapfvPROC) (GLenum map, GLsizei mapsize, const GLfloat * values);
typedef void (APIENTRY *glPixelStoreiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glPixelStorefPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY *glPixelTransferiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glPixelTransferfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY *glPixelZoomPROC) (GLfloat xfactor, GLfloat yfactor);
typedef void (APIENTRY *glPointSizePROC) (GLfloat size);
typedef void (APIENTRY *glPolygonModePROC) (GLenum face, GLenum mode);
typedef void (APIENTRY *glPolygonOffsetPROC) (GLfloat factor, GLfloat units);
typedef void (APIENTRY *glPolygonStipplePROC) (const GLubyte * mask);
typedef void (APIENTRY *glMatrixModePROC) (GLenum mode);
typedef GLboolean (APIENTRY *glIsTexturePROC) (GLuint texture);
typedef void (APIENTRY *glLightivPROC) (GLenum light, GLenum pname, const GLint * params);
typedef void (APIENTRY *glLightfvPROC) (GLenum light, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glLightiPROC) (GLenum light, GLenum pname, GLint param);
typedef void (APIENTRY *glLightfPROC) (GLenum light, GLenum pname, GLfloat param);
typedef void (APIENTRY *glLightModelivPROC) (GLenum pname, const GLint * params);
typedef void (APIENTRY *glLightModelfvPROC) (GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glLightModeliPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glLightModelfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY *glLineStipplePROC) (GLint factor, GLushort pattern);
typedef void (APIENTRY *glLineWidthPROC) (GLfloat width);
typedef void (APIENTRY *glListBasePROC) (GLuint base);
typedef void (APIENTRY *glLoadIdentityPROC) ();
typedef void (APIENTRY *glLoadMatrixfPROC) (const GLfloat * m);
typedef void (APIENTRY *glLoadNamePROC) (GLuint name);
typedef void (APIENTRY *glLogicOpPROC) (GLenum opcode);
typedef void (APIENTRY *glMap1fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint stride, GLint order, const GLfloat * points);
typedef void (APIENTRY *glMap2fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint ustride, GLint uorder, GLfloat v1, GLfloat v2, GLint vstride, GLint vorder, const GLfloat * points);
typedef void (APIENTRY *glMapGrid2fPROC) (GLint un, GLfloat u1, GLfloat u2, GLint vn, GLfloat v1, GLfloat v2);
typedef void (APIENTRY *glMapGrid1fPROC) (GLint un, GLfloat u1, GLfloat u2);
typedef void (APIENTRY *glMaterialivPROC) (GLenum face, GLenum pname, const GLint * params);
typedef void (APIENTRY *glMaterialfvPROC) (GLenum face, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glMaterialiPROC) (GLenum face, GLenum pname, GLint param);
typedef void (APIENTRY *glMaterialfPROC) (GLenum face, GLenum pname, GLfloat param);
typedef GLboolean (APIENTRY *glIsListPROC) (GLuint list);
typedef void (APIENTRY *glGetPolygonStipplePROC) (GLubyte * mask);
typedef const GLubyte * (APIENTRY *glGetStringPROC) (GLint name);
typedef void (APIENTRY *glGetTexEnvfvPROC) (GLenum coord, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetTexEnvivPROC) (GLenum coord, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetTexGenfvPROC) (GLenum coord, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetTexGenivPROC) (GLenum coord, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetTexImagePROC) (GLenum target, GLint level, GLenum format, GLenum type, GLvoid * pixels);
typedef void (APIENTRY *glGetTexLevelParameterivPROC) (GLenum target, GLint level, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetTexLevelParameterfvPROC) (GLenum target, GLint level, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetTexParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetTexParameterfvPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glHintPROC) (GLenum target, GLenum mode);
typedef void (APIENTRY *glInitNamesPROC) ();
typedef void (APIENTRY *glInterleavedArraysPROC) (GLenum format, GLsizei stride, const GLvoid * pointer);
typedef GLboolean (APIENTRY *glIsEnabledPROC) (GLenum cap);
typedef void (APIENTRY *glGetPointervPROC) (GLenum pname, GLvoid ** result);
typedef void (APIENTRY *glFinishPROC) ();
typedef void (APIENTRY *glFlushPROC) ();
typedef void (APIENTRY *glFogivPROC) (GLenum pname, const GLint * params);
typedef void (APIENTRY *glFogfvPROC) (GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glFogiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glFogfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY *glFrontFacePROC) (GLenum mode);
typedef void (APIENTRY *glFrustumPROC) (GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble zNear, GLdouble zFar);
typedef GLuint (APIENTRY *glGenListsPROC) (GLsizei range);
typedef void (APIENTRY *glGenTexturesPROC) (GLsizei n, GLuint * textures);
typedef void (APIENTRY *glGetIntegervPROC) (GLenum pname, GLint * params);
typedef void (APIENTRY *glGetFloatvPROC) (GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetDoublevPROC) (GLenum pname, GLdouble * params);
typedef void (APIENTRY *glGetBooleanvPROC) (GLenum pname, GLboolean * params);
typedef void (APIENTRY *glGetClipPlanePROC) (GLenum plane, GLdouble * equation);
typedef GLint (APIENTRY *glGetErrorPROC) ();
typedef void (APIENTRY *glGetLightivPROC) (GLenum light, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetLightfvPROC) (GLenum light, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetMapivPROC) (GLenum target, GLenum query, GLint * v);
typedef void (APIENTRY *glGetMapfvPROC) (GLenum target, GLenum query, GLfloat * v);
typedef void (APIENTRY *glGetMaterialivPROC) (GLenum face, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetMaterialfvPROC) (GLenum face, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetPixelMapusvPROC) (GLenum map, GLushort * values);
typedef void (APIENTRY *glGetPixelMapuivPROC) (GLenum map, GLuint * values);
typedef void (APIENTRY *glGetPixelMapfvPROC) (GLenum map, GLfloat * values);
typedef void (APIENTRY *glFeedbackBufferPROC) (GLsizei size, GLenum type, GLfloat * buffer);
typedef void (APIENTRY *glDepthFuncPROC) (GLenum func);
typedef void (APIENTRY *glDepthMaskPROC) (GLboolean flag);
typedef void (APIENTRY *glDepthRangePROC) (GLclampd zNear, GLclampd zFar);
typedef void (APIENTRY *glDrawArraysPROC) (GLenum mode, GLint first, GLsizei count);
typedef void (APIENTRY *glDrawBufferPROC) (GLenum mode);
typedef void (APIENTRY *glDrawElementsPROC) (GLenum mode, GLsizei count, GLenum type, const GLvoid * indices);
typedef void (APIENTRY *glDrawPixelsPROC) (GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid * pixels);
typedef void (APIENTRY *glEdgeFlagPROC) (GLboolean flag);
typedef void (APIENTRY *glEdgeFlagPointerPROC) (GLint stride, const GLvoid * pointer);
typedef void (APIENTRY *glDisablePROC) (GLenum cap);
typedef void (APIENTRY *glEnablePROC) (GLenum cap);
typedef void (APIENTRY *glDisableClientStatePROC) (GLenum cap);
typedef void (APIENTRY *glEnableClientStatePROC) (GLenum cap);
typedef void (APIENTRY *glEvalCoord2fPROC) (GLfloat u, GLfloat v);
typedef void (APIENTRY *glEvalCoord1fPROC) (GLfloat u);
typedef void (APIENTRY *glEvalMesh2PROC) (GLenum mode, GLint i1, GLint i2, GLint j1, GLint j2);
typedef void (APIENTRY *glEvalMesh1PROC) (GLenum mode, GLint i1, GLint i2);
typedef void (APIENTRY *glEvalPoint2PROC) (GLint i, GLint j);
typedef void (APIENTRY *glEvalPoint1PROC) (GLint i);
typedef void (APIENTRY *glClearIndexPROC) (GLfloat c);
typedef void (APIENTRY *glClearStencilPROC) (GLint s);
typedef void (APIENTRY *glClipPlanePROC) (GLenum plane, const GLdouble * equation);
typedef void (APIENTRY *glColor4ubPROC) (GLubyte red, GLubyte green, GLubyte blue, GLubyte alpha);
typedef void (APIENTRY *glColor4fPROC) (GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha);
typedef void (APIENTRY *glColor4bPROC) (GLbyte red, GLbyte green, GLbyte blue, GLbyte alpha);
typedef void (APIENTRY *glColor3ubPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY *glColor3fPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY *glColor3bPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY *glColorMaskPROC) (GLboolean red, GLboolean green, GLboolean blue, GLboolean alpha);
typedef void (APIENTRY *glColorMaterialPROC) (GLenum face, GLenum mode);
typedef void (APIENTRY *glColorPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid * pointer);
typedef void (APIENTRY *glCopyPixelsPROC) (GLint x, GLint y, GLint width, GLint height, GLint type);
typedef void (APIENTRY *glCopyTexImage1DPROC) (GLenum target, GLint level, GLint internalFormat, GLint x, GLint y, GLsizei width, GLint border);
typedef void (APIENTRY *glCopyTexImage2DPROC) (GLenum target, GLint level, GLint internalFormat, GLint x, GLint y, GLsizei width, GLsizei height, GLint border);
typedef void (APIENTRY *glCopyTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLint x, GLint y, GLsizei width);
typedef void (APIENTRY *glCopyTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY *glCullFacePROC) (GLenum mode);
typedef void (APIENTRY *glDeleteTexturesPROC) (GLsizei n, const GLuint * textures);
typedef void (APIENTRY *glDeleteListsPROC) (GLuint list, GLsizei range);
typedef void (APIENTRY *glClearDepthPROC) (GLclampd depth);
typedef void (APIENTRY *glArrayElementPROC) (GLint i);
typedef void (APIENTRY *glEndPROC) ();
typedef void (APIENTRY *glBeginPROC) (GLenum mode);
typedef void (APIENTRY *glBindTexturePROC) (GLenum target, GLuint texture);
typedef void (APIENTRY *glBitmapPROC) (GLsizei width, GLsizei height, GLfloat xorig, GLfloat yorig, GLfloat xmove, GLfloat ymove, const GLubyte * bitmap);
typedef void (APIENTRY *glBlendFuncPROC) (GLenum sfactor, GLenum dfactor);
typedef void (APIENTRY *glCallListPROC) (GLuint list);
typedef void (APIENTRY *glCallListsPROC) (GLsizei n, GLenum type, const GLvoid * lists);
typedef void (APIENTRY *glClearPROC) (GLbitfield mask);
typedef void (APIENTRY *glClearAccumPROC) (GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha);
typedef void (APIENTRY *glClearColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef void (APIENTRY *glAlphaFuncPROC) (GLenum func, GLclampf ref);
typedef void (APIENTRY *glAccumPROC) (GLenum op, GLfloat value);

static glViewportPROC glViewport;
static glStencilMaskPROC glStencilMask;
static glStencilOpPROC glStencilOp;
static glTexCoord4fPROC glTexCoord4f;
static glTexCoord3fPROC glTexCoord3f;
static glTexCoord2fPROC glTexCoord2f;
static glTexCoord1fPROC glTexCoord1f;
static glTexCoordPointerPROC glTexCoordPointer;
static glTexEnvivPROC glTexEnviv;
static glTexEnvfvPROC glTexEnvfv;
static glTexEnviPROC glTexEnvi;
static glTexEnvfPROC glTexEnvf;
static glTexGenivPROC glTexGeniv;
static glTexGeniPROC glTexGeni;
static glTexGenfvPROC glTexGenfv;
static glTexGenfPROC glTexGenf;
static glTexParameterivPROC glTexParameteriv;
static glTexParameterfvPROC glTexParameterfv;
static glTexParameteriPROC glTexParameteri;
static glTexParameterfPROC glTexParameterf;
static glTexSubImage2DPROC glTexSubImage2D;
static glTexSubImage1DPROC glTexSubImage1D;
static glTexImage2DPROC glTexImage2D;
static glTexImage1DPROC glTexImage1D;
static glTranslatefPROC glTranslatef;
static glVertex4iPROC glVertex4i;
static glVertex4fPROC glVertex4f;
static glVertex3iPROC glVertex3i;
static glVertex3fPROC glVertex3f;
static glVertex2iPROC glVertex2i;
static glVertex2fPROC glVertex2f;
static glVertexPointerPROC glVertexPointer;
static glStencilFuncPROC glStencilFunc;
static glPopAttribPROC glPopAttrib;
static glPushAttribPROC glPushAttrib;
static glPopClientAttribPROC glPopClientAttrib;
static glPushClientAttribPROC glPushClientAttrib;
static glPopMatrixPROC glPopMatrix;
static glPushMatrixPROC glPushMatrix;
static glPopNamePROC glPopName;
static glPushNamePROC glPushName;
static glRasterPos4iPROC glRasterPos4i;
static glRasterPos4fPROC glRasterPos4f;
static glRasterPos3iPROC glRasterPos3i;
static glRasterPos3fPROC glRasterPos3f;
static glRasterPos2iPROC glRasterPos2i;
static glRasterPos2fPROC glRasterPos2f;
static glReadBufferPROC glReadBuffer;
static glReadPixelsPROC glReadPixels;
static glRectiPROC glRecti;
static glRectfPROC glRectf;
static glRenderModePROC glRenderMode;
static glRotatefPROC glRotatef;
static glScalefPROC glScalef;
static glScissorPROC glScissor;
static glSelectBufferPROC glSelectBuffer;
static glShadeModelPROC glShadeModel;
static glMultMatrixfPROC glMultMatrixf;
static glEndListPROC glEndList;
static glNewListPROC glNewList;
static glNormal3iPROC glNormal3i;
static glNormal3fPROC glNormal3f;
static glNormal3bPROC glNormal3b;
static glNormalPointerPROC glNormalPointer;
static glOrthoPROC glOrtho;
static glPassThroughPROC glPassThrough;
static glPixelMapusvPROC glPixelMapusv;
static glPixelMapuivPROC glPixelMapuiv;
static glPixelMapfvPROC glPixelMapfv;
static glPixelStoreiPROC glPixelStorei;
static glPixelStorefPROC glPixelStoref;
static glPixelTransferiPROC glPixelTransferi;
static glPixelTransferfPROC glPixelTransferf;
static glPixelZoomPROC glPixelZoom;
static glPointSizePROC glPointSize;
static glPolygonModePROC glPolygonMode;
static glPolygonOffsetPROC glPolygonOffset;
static glPolygonStipplePROC glPolygonStipple;
static glMatrixModePROC glMatrixMode;
static glIsTexturePROC glIsTexture;
static glLightivPROC glLightiv;
static glLightfvPROC glLightfv;
static glLightiPROC glLighti;
static glLightfPROC glLightf;
static glLightModelivPROC glLightModeliv;
static glLightModelfvPROC glLightModelfv;
static glLightModeliPROC glLightModeli;
static glLightModelfPROC glLightModelf;
static glLineStipplePROC glLineStipple;
static glLineWidthPROC glLineWidth;
static glListBasePROC glListBase;
static glLoadIdentityPROC glLoadIdentity;
static glLoadMatrixfPROC glLoadMatrixf;
static glLoadNamePROC glLoadName;
static glLogicOpPROC glLogicOp;
static glMap1fPROC glMap1f;
static glMap2fPROC glMap2f;
static glMapGrid2fPROC glMapGrid2f;
static glMapGrid1fPROC glMapGrid1f;
static glMaterialivPROC glMaterialiv;
static glMaterialfvPROC glMaterialfv;
static glMaterialiPROC glMateriali;
static glMaterialfPROC glMaterialf;
static glIsListPROC glIsList;
static glGetPolygonStipplePROC glGetPolygonStipple;
static glGetStringPROC glGetString;
static glGetTexEnvfvPROC glGetTexEnvfv;
static glGetTexEnvivPROC glGetTexEnviv;
static glGetTexGenfvPROC glGetTexGenfv;
static glGetTexGenivPROC glGetTexGeniv;
static glGetTexImagePROC glGetTexImage;
static glGetTexLevelParameterivPROC glGetTexLevelParameteriv;
static glGetTexLevelParameterfvPROC glGetTexLevelParameterfv;
static glGetTexParameterivPROC glGetTexParameteriv;
static glGetTexParameterfvPROC glGetTexParameterfv;
static glHintPROC glHint;
static glInitNamesPROC glInitNames;
static glInterleavedArraysPROC glInterleavedArrays;
static glIsEnabledPROC glIsEnabled;
static glGetPointervPROC glGetPointerv;
static glFinishPROC glFinish;
static glFlushPROC glFlush;
static glFogivPROC glFogiv;
static glFogfvPROC glFogfv;
static glFogiPROC glFogi;
static glFogfPROC glFogf;
static glFrontFacePROC glFrontFace;
static glFrustumPROC glFrustum;
static glGenListsPROC glGenLists;
static glGenTexturesPROC glGenTextures;
static glGetIntegervPROC glGetIntegerv;
static glGetFloatvPROC glGetFloatv;
static glGetDoublevPROC glGetDoublev;
static glGetBooleanvPROC glGetBooleanv;
static glGetClipPlanePROC glGetClipPlane;
static glGetErrorPROC glGetError;
static glGetLightivPROC glGetLightiv;
static glGetLightfvPROC glGetLightfv;
static glGetMapivPROC glGetMapiv;
static glGetMapfvPROC glGetMapfv;
static glGetMaterialivPROC glGetMaterialiv;
static glGetMaterialfvPROC glGetMaterialfv;
static glGetPixelMapusvPROC glGetPixelMapusv;
static glGetPixelMapuivPROC glGetPixelMapuiv;
static glGetPixelMapfvPROC glGetPixelMapfv;
static glFeedbackBufferPROC glFeedbackBuffer;
static glDepthFuncPROC glDepthFunc;
static glDepthMaskPROC glDepthMask;
static glDepthRangePROC glDepthRange;
static glDrawArraysPROC glDrawArrays;
static glDrawBufferPROC glDrawBuffer;
static glDrawElementsPROC glDrawElements;
static glDrawPixelsPROC glDrawPixels;
static glEdgeFlagPROC glEdgeFlag;
static glEdgeFlagPointerPROC glEdgeFlagPointer;
static glDisablePROC glDisable;
static glEnablePROC glEnable;
static glDisableClientStatePROC glDisableClientState;
static glEnableClientStatePROC glEnableClientState;
static glEvalCoord2fPROC glEvalCoord2f;
static glEvalCoord1fPROC glEvalCoord1f;
static glEvalMesh2PROC glEvalMesh2;
static glEvalMesh1PROC glEvalMesh1;
static glEvalPoint2PROC glEvalPoint2;
static glEvalPoint1PROC glEvalPoint1;
static glClearIndexPROC glClearIndex;
static glClearStencilPROC glClearStencil;
static glClipPlanePROC glClipPlane;
static glColor4ubPROC glColor4ub;
static glColor4fPROC glColor4f;
static glColor4bPROC glColor4b;
static glColor3ubPROC glColor3ub;
static glColor3fPROC glColor3f;
static glColor3bPROC glColor3b;
static glColorMaskPROC glColorMask;
static glColorMaterialPROC glColorMaterial;
static glColorPointerPROC glColorPointer;
static glCopyPixelsPROC glCopyPixels;
static glCopyTexImage1DPROC glCopyTexImage1D;
static glCopyTexImage2DPROC glCopyTexImage2D;
static glCopyTexSubImage1DPROC glCopyTexSubImage1D;
static glCopyTexSubImage2DPROC glCopyTexSubImage2D;
static glCullFacePROC glCullFace;
static glDeleteTexturesPROC glDeleteTextures;
static glDeleteListsPROC glDeleteLists;
static glClearDepthPROC glClearDepth;
static glArrayElementPROC glArrayElement;
static glEndPROC glEnd;
static glBeginPROC glBegin;
static glBindTexturePROC glBindTexture;
static glBitmapPROC glBitmap;
static glBlendFuncPROC glBlendFunc;
static glCallListPROC glCallList;
static glCallListsPROC glCallLists;
static glClearPROC glClear;
static glClearAccumPROC glClearAccum;
static glClearColorPROC glClearColor;
static glAlphaFuncPROC glAlphaFunc;
static glAccumPROC glAccum;

static void JNICALL Java_org_lwjgl_opengl_GL11_glViewport(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height) {
	glViewport(x, y, width, height);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glStencilMask(JNIEnv *env, jclass clazz, jint mask) {
	glStencilMask(mask);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glStencilOp(JNIEnv *env, jclass clazz, jint fail, jint zfail, jint zpass) {
	glStencilOp(fail, zfail, zpass);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord4f(JNIEnv *env, jclass clazz, jfloat s, jfloat t, jfloat r, jfloat q) {
	glTexCoord4f(s, t, r, q);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord3f(JNIEnv *env, jclass clazz, jfloat s, jfloat t, jfloat r) {
	glTexCoord3f(s, t, r);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord2f(JNIEnv *env, jclass clazz, jfloat s, jfloat t) {
	glTexCoord2f(s, t);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexCoord1f(JNIEnv *env, jclass clazz, jfloat s) {
	glTexCoord1f(s);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexCoordPointer(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pointer, jint pointer_position) {
	const GLvoid *pointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pointer)) + pointer_position));
	glTexCoordPointer(size, type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexCoordPointerBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint pointer_buffer_offset) {
	const GLvoid *pointer_address = ((const GLvoid *)offsetToPointer(pointer_buffer_offset));
	glTexCoordPointer(size, type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexEnviv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glTexEnviv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexEnvfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glTexEnvfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexEnvi(JNIEnv *env, jclass clazz, jint target, jint pname, jint param) {
	glTexEnvi(target, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexEnvf(JNIEnv *env, jclass clazz, jint target, jint pname, jfloat param) {
	glTexEnvf(target, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexGeniv(JNIEnv *env, jclass clazz, jint coord, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glTexGeniv(coord, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexGeni(JNIEnv *env, jclass clazz, jint coord, jint pname, jint param) {
	glTexGeni(coord, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexGenfv(JNIEnv *env, jclass clazz, jint coord, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glTexGenfv(coord, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexGenf(JNIEnv *env, jclass clazz, jint coord, jint pname, jfloat param) {
	glTexGenf(coord, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject param, jint param_position) {
	const GLint *param_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, param)) + param_position;
	glTexParameteriv(target, pname, param_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject param, jint param_position) {
	const GLfloat *param_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, param)) + param_position;
	glTexParameterfv(target, pname, param_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexParameteri(JNIEnv *env, jclass clazz, jint target, jint pname, jint param) {
	glTexParameteri(target, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTexParameterf(JNIEnv *env, jclass clazz, jint target, jint pname, jfloat param) {
	glTexParameterf(target, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint type, jobject pixels, jint pixels_position) {
	const GLvoid *pixels_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pixels)) + pixels_position));
	glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage2DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint type, jint pixels_buffer_offset) {
	const GLvoid *pixels_address = ((const GLvoid *)offsetToPointer(pixels_buffer_offset));
	glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage1D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint type, jobject pixels, jint pixels_position) {
	const GLvoid *pixels_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pixels)) + pixels_position));
	glTexSubImage1D(target, level, xoffset, width, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexSubImage1DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint width, jint format, jint type, jint pixels_buffer_offset) {
	const GLvoid *pixels_address = ((const GLvoid *)offsetToPointer(pixels_buffer_offset));
	glTexSubImage1D(target, level, xoffset, width, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint format, jint type, jobject pixels, jint pixels_position) {
	const GLvoid *pixels_address = ((const GLvoid *)(((char *)safeGetBufferAddress(env, pixels)) + pixels_position));
	glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage2DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint format, jint type, jint pixels_buffer_offset) {
	const GLvoid *pixels_address = ((const GLvoid *)offsetToPointer(pixels_buffer_offset));
	glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage1D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint format, jint type, jobject pixels, jint pixels_position) {
	const GLvoid *pixels_address = ((const GLvoid *)(((char *)safeGetBufferAddress(env, pixels)) + pixels_position));
	glTexImage1D(target, level, internalformat, width, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglTexImage1DBO(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint border, jint format, jint type, jint pixels_buffer_offset) {
	const GLvoid *pixels_address = ((const GLvoid *)offsetToPointer(pixels_buffer_offset));
	glTexImage1D(target, level, internalformat, width, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glTranslatef(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z) {
	glTranslatef(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex4i(JNIEnv *env, jclass clazz, jint x, jint y, jint z, jint w) {
	glVertex4i(x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex4f(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z, jfloat w) {
	glVertex4f(x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex3i(JNIEnv *env, jclass clazz, jint x, jint y, jint z) {
	glVertex3i(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex3f(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z) {
	glVertex3f(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex2i(JNIEnv *env, jclass clazz, jint x, jint y) {
	glVertex2i(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glVertex2f(JNIEnv *env, jclass clazz, jfloat x, jfloat y) {
	glVertex2f(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglVertexPointer(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pointer, jint pointer_position) {
	const GLvoid *pointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pointer)) + pointer_position));
	glVertexPointer(size, type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglVertexPointerBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint pointer_buffer_offset) {
	const GLvoid *pointer_address = ((const GLvoid *)offsetToPointer(pointer_buffer_offset));
	glVertexPointer(size, type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glStencilFunc(JNIEnv *env, jclass clazz, jint func, jint ref, jint mask) {
	glStencilFunc(func, ref, mask);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPopAttrib(JNIEnv *env, jclass clazz) {
	glPopAttrib();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPushAttrib(JNIEnv *env, jclass clazz, jint mask) {
	glPushAttrib(mask);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPopClientAttrib(JNIEnv *env, jclass clazz) {
	glPopClientAttrib();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPushClientAttrib(JNIEnv *env, jclass clazz, jint mask) {
	glPushClientAttrib(mask);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPopMatrix(JNIEnv *env, jclass clazz) {
	glPopMatrix();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPushMatrix(JNIEnv *env, jclass clazz) {
	glPushMatrix();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPopName(JNIEnv *env, jclass clazz) {
	glPopName();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPushName(JNIEnv *env, jclass clazz, jint name) {
	glPushName(name);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos4i(JNIEnv *env, jclass clazz, jint x, jint y, jint z, jint w) {
	glRasterPos4i(x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos4f(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z, jfloat w) {
	glRasterPos4f(x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos3i(JNIEnv *env, jclass clazz, jint x, jint y, jint z) {
	glRasterPos3i(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos3f(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z) {
	glRasterPos3f(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos2i(JNIEnv *env, jclass clazz, jint x, jint y) {
	glRasterPos2i(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRasterPos2f(JNIEnv *env, jclass clazz, jfloat x, jfloat y) {
	glRasterPos2f(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glReadBuffer(JNIEnv *env, jclass clazz, jint mode) {
	glReadBuffer(mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglReadPixels(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jint format, jint type, jobject pixels, jint pixels_position) {
	GLvoid *pixels_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pixels)) + pixels_position));
	glReadPixels(x, y, width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglReadPixelsBO(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jint format, jint type, jint pixels_buffer_offset) {
	GLvoid *pixels_address = ((GLvoid *)offsetToPointer(pixels_buffer_offset));
	glReadPixels(x, y, width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRecti(JNIEnv *env, jclass clazz, jint x1, jint y1, jint x2, jint y2) {
	glRecti(x1, y1, x2, y2);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRectf(JNIEnv *env, jclass clazz, jfloat x1, jfloat y1, jfloat x2, jfloat y2) {
	glRectf(x1, y1, x2, y2);
}

static jint JNICALL Java_org_lwjgl_opengl_GL11_glRenderMode(JNIEnv *env, jclass clazz, jint mode) {
	GLint __result = glRenderMode(mode);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glRotatef(JNIEnv *env, jclass clazz, jfloat angle, jfloat x, jfloat y, jfloat z) {
	glRotatef(angle, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glScalef(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z) {
	glScalef(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glScissor(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height) {
	glScissor(x, y, width, height);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglSelectBuffer(JNIEnv *env, jclass clazz, jint size, jobject buffer, jint buffer_position) {
	GLuint *buffer_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, buffer)) + buffer_position;
	glSelectBuffer(size, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glShadeModel(JNIEnv *env, jclass clazz, jint mode) {
	glShadeModel(mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglMultMatrixf(JNIEnv *env, jclass clazz, jobject m, jint m_position) {
	const GLfloat *m_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, m)) + m_position;
	glMultMatrixf(m_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEndList(JNIEnv *env, jclass clazz) {
	glEndList();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glNewList(JNIEnv *env, jclass clazz, jint list, jint mode) {
	glNewList(list, mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3i(JNIEnv *env, jclass clazz, jint nx, jint ny, jint nz) {
	glNormal3i(nx, ny, nz);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3f(JNIEnv *env, jclass clazz, jfloat nx, jfloat ny, jfloat nz) {
	glNormal3f(nx, ny, nz);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glNormal3b(JNIEnv *env, jclass clazz, jbyte nx, jbyte ny, jbyte nz) {
	glNormal3b(nx, ny, nz);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglNormalPointer(JNIEnv *env, jclass clazz, jint type, jint stride, jobject pointer, jint pointer_position) {
	const GLvoid *pointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pointer)) + pointer_position));
	glNormalPointer(type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglNormalPointerBO(JNIEnv *env, jclass clazz, jint type, jint stride, jint pointer_buffer_offset) {
	const GLvoid *pointer_address = ((const GLvoid *)offsetToPointer(pointer_buffer_offset));
	glNormalPointer(type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glOrtho(JNIEnv *env, jclass clazz, jdouble left, jdouble right, jdouble bottom, jdouble top, jdouble zNear, jdouble zFar) {
	glOrtho(left, right, bottom, top, zNear, zFar);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPassThrough(JNIEnv *env, jclass clazz, jfloat token) {
	glPassThrough(token);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapusv(JNIEnv *env, jclass clazz, jint map, jint mapsize, jobject values, jint values_position) {
	const GLushort *values_address = ((const GLushort *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glPixelMapusv(map, mapsize, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapusvBO(JNIEnv *env, jclass clazz, jint map, jint mapsize, jint values_buffer_offset) {
	const GLushort *values_address = ((const GLushort *)offsetToPointer(values_buffer_offset));
	glPixelMapusv(map, mapsize, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapuiv(JNIEnv *env, jclass clazz, jint map, jint mapsize, jobject values, jint values_position) {
	const GLuint *values_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glPixelMapuiv(map, mapsize, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapuivBO(JNIEnv *env, jclass clazz, jint map, jint mapsize, jint values_buffer_offset) {
	const GLuint *values_address = ((const GLuint *)offsetToPointer(values_buffer_offset));
	glPixelMapuiv(map, mapsize, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapfv(JNIEnv *env, jclass clazz, jint map, jint mapsize, jobject values, jint values_position) {
	const GLfloat *values_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glPixelMapfv(map, mapsize, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPixelMapfvBO(JNIEnv *env, jclass clazz, jint map, jint mapsize, jint values_buffer_offset) {
	const GLfloat *values_address = ((const GLfloat *)offsetToPointer(values_buffer_offset));
	glPixelMapfv(map, mapsize, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelStorei(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glPixelStorei(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelStoref(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glPixelStoref(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelTransferi(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glPixelTransferi(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelTransferf(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glPixelTransferf(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPixelZoom(JNIEnv *env, jclass clazz, jfloat xfactor, jfloat yfactor) {
	glPixelZoom(xfactor, yfactor);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPointSize(JNIEnv *env, jclass clazz, jfloat size) {
	glPointSize(size);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPolygonMode(JNIEnv *env, jclass clazz, jint face, jint mode) {
	glPolygonMode(face, mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glPolygonOffset(JNIEnv *env, jclass clazz, jfloat factor, jfloat units) {
	glPolygonOffset(factor, units);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPolygonStipple(JNIEnv *env, jclass clazz, jobject mask, jint mask_position) {
	const GLubyte *mask_address = ((const GLubyte *)(*env)->GetDirectBufferAddress(env, mask)) + mask_position;
	glPolygonStipple(mask_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglPolygonStippleBO(JNIEnv *env, jclass clazz, jint mask_buffer_offset) {
	const GLubyte *mask_address = ((const GLubyte *)offsetToPointer(mask_buffer_offset));
	glPolygonStipple(mask_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glMatrixMode(JNIEnv *env, jclass clazz, jint mode) {
	glMatrixMode(mode);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsTexture(JNIEnv *env, jclass clazz, jint texture) {
	GLboolean __result = glIsTexture(texture);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightiv(JNIEnv *env, jclass clazz, jint light, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glLightiv(light, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightfv(JNIEnv *env, jclass clazz, jint light, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glLightfv(light, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLighti(JNIEnv *env, jclass clazz, jint light, jint pname, jint param) {
	glLighti(light, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLightf(JNIEnv *env, jclass clazz, jint light, jint pname, jfloat param) {
	glLightf(light, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightModeliv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glLightModeliv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglLightModelfv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glLightModelfv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLightModeli(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glLightModeli(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLightModelf(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glLightModelf(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLineStipple(JNIEnv *env, jclass clazz, jint factor, jshort pattern) {
	glLineStipple(factor, pattern);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLineWidth(JNIEnv *env, jclass clazz, jfloat width) {
	glLineWidth(width);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glListBase(JNIEnv *env, jclass clazz, jint base) {
	glListBase(base);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLoadIdentity(JNIEnv *env, jclass clazz) {
	glLoadIdentity();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglLoadMatrixf(JNIEnv *env, jclass clazz, jobject m, jint m_position) {
	const GLfloat *m_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, m)) + m_position;
	glLoadMatrixf(m_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLoadName(JNIEnv *env, jclass clazz, jint name) {
	glLoadName(name);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glLogicOp(JNIEnv *env, jclass clazz, jint opcode) {
	glLogicOp(opcode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglMap1f(JNIEnv *env, jclass clazz, jint target, jfloat u1, jfloat u2, jint stride, jint order, jobject points, jint points_position) {
	const GLfloat *points_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, points)) + points_position;
	glMap1f(target, u1, u2, stride, order, points_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglMap2f(JNIEnv *env, jclass clazz, jint target, jfloat u1, jfloat u2, jint ustride, jint uorder, jfloat v1, jfloat v2, jint vstride, jint vorder, jobject points, jint points_position) {
	const GLfloat *points_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, points)) + points_position;
	glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glMapGrid2f(JNIEnv *env, jclass clazz, jint un, jfloat u1, jfloat u2, jint vn, jfloat v1, jfloat v2) {
	glMapGrid2f(un, u1, u2, vn, v1, v2);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glMapGrid1f(JNIEnv *env, jclass clazz, jint un, jfloat u1, jfloat u2) {
	glMapGrid1f(un, u1, u2);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglMaterialiv(JNIEnv *env, jclass clazz, jint face, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glMaterialiv(face, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglMaterialfv(JNIEnv *env, jclass clazz, jint face, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glMaterialfv(face, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glMateriali(JNIEnv *env, jclass clazz, jint face, jint pname, jint param) {
	glMateriali(face, pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glMaterialf(JNIEnv *env, jclass clazz, jint face, jint pname, jfloat param) {
	glMaterialf(face, pname, param);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsList(JNIEnv *env, jclass clazz, jint list) {
	GLboolean __result = glIsList(list);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPolygonStipple(JNIEnv *env, jclass clazz, jobject mask, jint mask_position) {
	GLubyte *mask_address = ((GLubyte *)(*env)->GetDirectBufferAddress(env, mask)) + mask_position;
	glGetPolygonStipple(mask_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPolygonStippleBO(JNIEnv *env, jclass clazz, jint mask_buffer_offset) {
	GLubyte *mask_address = ((GLubyte *)offsetToPointer(mask_buffer_offset));
	glGetPolygonStipple(mask_address);
}

static jobject JNICALL Java_org_lwjgl_opengl_GL11_glGetString(JNIEnv *env, jclass clazz, jint name) {
	const GLubyte * __result = glGetString(name);
	return NewStringNative(env, __result);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexEnvfv(JNIEnv *env, jclass clazz, jint coord, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexEnvfv(coord, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexEnviv(JNIEnv *env, jclass clazz, jint coord, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexEnviv(coord, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexGenfv(JNIEnv *env, jclass clazz, jint coord, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexGenfv(coord, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexGeniv(JNIEnv *env, jclass clazz, jint coord, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexGeniv(coord, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexImage(JNIEnv *env, jclass clazz, jint target, jint level, jint format, jint type, jobject pixels, jint pixels_position) {
	GLvoid *pixels_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pixels)) + pixels_position));
	glGetTexImage(target, level, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexImageBO(JNIEnv *env, jclass clazz, jint target, jint level, jint format, jint type, jint pixels_buffer_offset) {
	GLvoid *pixels_address = ((GLvoid *)offsetToPointer(pixels_buffer_offset));
	glGetTexImage(target, level, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameteriv(JNIEnv *env, jclass clazz, jint target, jint level, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexLevelParameteriv(target, level, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameterfv(JNIEnv *env, jclass clazz, jint target, jint level, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexLevelParameterfv(target, level, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetTexParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetTexParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glHint(JNIEnv *env, jclass clazz, jint target, jint mode) {
	glHint(target, mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glInitNames(JNIEnv *env, jclass clazz) {
	glInitNames();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglInterleavedArrays(JNIEnv *env, jclass clazz, jint format, jint stride, jobject pointer, jint pointer_position) {
	const GLvoid *pointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pointer)) + pointer_position));
	glInterleavedArrays(format, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglInterleavedArraysBO(JNIEnv *env, jclass clazz, jint format, jint stride, jint pointer_buffer_offset) {
	const GLvoid *pointer_address = ((const GLvoid *)offsetToPointer(pointer_buffer_offset));
	glInterleavedArrays(format, stride, pointer_address);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL11_glIsEnabled(JNIEnv *env, jclass clazz, jint cap) {
	GLboolean __result = glIsEnabled(cap);
	return __result;
}

static jobject JNICALL Java_org_lwjgl_opengl_GL11_nglGetPointerv(JNIEnv *env, jclass clazz, jint pname, jint result_size) {
	GLvoid * __result;
	glGetPointerv(pname, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glFinish(JNIEnv *env, jclass clazz) {
	glFinish();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glFlush(JNIEnv *env, jclass clazz) {
	glFlush();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglFogiv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glFogiv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglFogfv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glFogfv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glFogi(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glFogi(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glFogf(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glFogf(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glFrontFace(JNIEnv *env, jclass clazz, jint mode) {
	glFrontFace(mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glFrustum(JNIEnv *env, jclass clazz, jdouble left, jdouble right, jdouble bottom, jdouble top, jdouble zNear, jdouble zFar) {
	glFrustum(left, right, bottom, top, zNear, zFar);
}

static jint JNICALL Java_org_lwjgl_opengl_GL11_glGenLists(JNIEnv *env, jclass clazz, jint range) {
	GLuint __result = glGenLists(range);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGenTextures(JNIEnv *env, jclass clazz, jint n, jobject textures, jint textures_position) {
	GLuint *textures_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, textures)) + textures_position;
	glGenTextures(n, textures_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetIntegerv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetIntegerv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetFloatv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetFloatv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetDoublev(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	GLdouble *params_address = ((GLdouble *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetDoublev(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetBooleanv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	GLboolean *params_address = ((GLboolean *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetBooleanv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetClipPlane(JNIEnv *env, jclass clazz, jint plane, jobject equation, jint equation_position) {
	GLdouble *equation_address = ((GLdouble *)(*env)->GetDirectBufferAddress(env, equation)) + equation_position;
	glGetClipPlane(plane, equation_address);
}

static jint JNICALL Java_org_lwjgl_opengl_GL11_glGetError(JNIEnv *env, jclass clazz) {
	GLint __result = glGetError();
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetLightiv(JNIEnv *env, jclass clazz, jint light, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetLightiv(light, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetLightfv(JNIEnv *env, jclass clazz, jint light, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetLightfv(light, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMapiv(JNIEnv *env, jclass clazz, jint target, jint query, jobject v, jint v_position) {
	GLint *v_address = ((GLint *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glGetMapiv(target, query, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMapfv(JNIEnv *env, jclass clazz, jint target, jint query, jobject v, jint v_position) {
	GLfloat *v_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, v)) + v_position;
	glGetMapfv(target, query, v_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMaterialiv(JNIEnv *env, jclass clazz, jint face, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMaterialiv(face, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetMaterialfv(JNIEnv *env, jclass clazz, jint face, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMaterialfv(face, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapusv(JNIEnv *env, jclass clazz, jint map, jobject values, jint values_position) {
	GLushort *values_address = ((GLushort *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glGetPixelMapusv(map, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapusvBO(JNIEnv *env, jclass clazz, jint map, jint values_buffer_offset) {
	GLushort *values_address = ((GLushort *)offsetToPointer(values_buffer_offset));
	glGetPixelMapusv(map, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapuiv(JNIEnv *env, jclass clazz, jint map, jobject values, jint values_position) {
	GLuint *values_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glGetPixelMapuiv(map, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapuivBO(JNIEnv *env, jclass clazz, jint map, jint values_buffer_offset) {
	GLuint *values_address = ((GLuint *)offsetToPointer(values_buffer_offset));
	glGetPixelMapuiv(map, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapfv(JNIEnv *env, jclass clazz, jint map, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glGetPixelMapfv(map, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglGetPixelMapfvBO(JNIEnv *env, jclass clazz, jint map, jint values_buffer_offset) {
	GLfloat *values_address = ((GLfloat *)offsetToPointer(values_buffer_offset));
	glGetPixelMapfv(map, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglFeedbackBuffer(JNIEnv *env, jclass clazz, jint size, jint type, jobject buffer, jint buffer_position) {
	GLfloat *buffer_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, buffer)) + buffer_position;
	glFeedbackBuffer(size, type, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDepthFunc(JNIEnv *env, jclass clazz, jint func) {
	glDepthFunc(func);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDepthMask(JNIEnv *env, jclass clazz, jboolean flag) {
	glDepthMask(flag);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDepthRange(JNIEnv *env, jclass clazz, jdouble zNear, jdouble zFar) {
	glDepthRange(zNear, zFar);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDrawArrays(JNIEnv *env, jclass clazz, jint mode, jint first, jint count) {
	glDrawArrays(mode, first, count);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDrawBuffer(JNIEnv *env, jclass clazz, jint mode) {
	glDrawBuffer(mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawElements(JNIEnv *env, jclass clazz, jint mode, jint count, jint type, jobject indices, jint indices_position) {
	const GLvoid *indices_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, indices)) + indices_position));
	glDrawElements(mode, count, type, indices_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawElementsBO(JNIEnv *env, jclass clazz, jint mode, jint count, jint type, jint indices_buffer_offset) {
	const GLvoid *indices_address = ((const GLvoid *)offsetToPointer(indices_buffer_offset));
	glDrawElements(mode, count, type, indices_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawPixels(JNIEnv *env, jclass clazz, jint width, jint height, jint format, jint type, jobject pixels, jint pixels_position) {
	const GLvoid *pixels_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pixels)) + pixels_position));
	glDrawPixels(width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglDrawPixelsBO(JNIEnv *env, jclass clazz, jint width, jint height, jint format, jint type, jint pixels_buffer_offset) {
	const GLvoid *pixels_address = ((const GLvoid *)offsetToPointer(pixels_buffer_offset));
	glDrawPixels(width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEdgeFlag(JNIEnv *env, jclass clazz, jboolean flag) {
	glEdgeFlag(flag);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointer(JNIEnv *env, jclass clazz, jint stride, jobject pointer, jint pointer_position) {
	const GLvoid *pointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pointer)) + pointer_position));
	glEdgeFlagPointer(stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointerBO(JNIEnv *env, jclass clazz, jint stride, jint pointer_buffer_offset) {
	const GLvoid *pointer_address = ((const GLvoid *)offsetToPointer(pointer_buffer_offset));
	glEdgeFlagPointer(stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDisable(JNIEnv *env, jclass clazz, jint cap) {
	glDisable(cap);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEnable(JNIEnv *env, jclass clazz, jint cap) {
	glEnable(cap);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDisableClientState(JNIEnv *env, jclass clazz, jint cap) {
	glDisableClientState(cap);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEnableClientState(JNIEnv *env, jclass clazz, jint cap) {
	glEnableClientState(cap);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalCoord2f(JNIEnv *env, jclass clazz, jfloat u, jfloat v) {
	glEvalCoord2f(u, v);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalCoord1f(JNIEnv *env, jclass clazz, jfloat u) {
	glEvalCoord1f(u);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalMesh2(JNIEnv *env, jclass clazz, jint mode, jint i1, jint i2, jint j1, jint j2) {
	glEvalMesh2(mode, i1, i2, j1, j2);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalMesh1(JNIEnv *env, jclass clazz, jint mode, jint i1, jint i2) {
	glEvalMesh1(mode, i1, i2);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalPoint2(JNIEnv *env, jclass clazz, jint i, jint j) {
	glEvalPoint2(i, j);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEvalPoint1(JNIEnv *env, jclass clazz, jint i) {
	glEvalPoint1(i);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glClearIndex(JNIEnv *env, jclass clazz, jfloat c) {
	glClearIndex(c);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glClearStencil(JNIEnv *env, jclass clazz, jint s) {
	glClearStencil(s);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglClipPlane(JNIEnv *env, jclass clazz, jint plane, jobject equation, jint equation_position) {
	const GLdouble *equation_address = ((const GLdouble *)(*env)->GetDirectBufferAddress(env, equation)) + equation_position;
	glClipPlane(plane, equation_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColor4ub(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue, jbyte alpha) {
	glColor4ub(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColor4f(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
	glColor4f(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColor4b(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue, jbyte alpha) {
	glColor4b(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColor3ub(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue) {
	glColor3ub(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColor3f(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue) {
	glColor3f(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColor3b(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue) {
	glColor3b(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColorMask(JNIEnv *env, jclass clazz, jboolean red, jboolean green, jboolean blue, jboolean alpha) {
	glColorMask(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glColorMaterial(JNIEnv *env, jclass clazz, jint face, jint mode) {
	glColorMaterial(face, mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglColorPointer(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pointer, jint pointer_position) {
	const GLvoid *pointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pointer)) + pointer_position));
	glColorPointer(size, type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglColorPointerBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint pointer_buffer_offset) {
	const GLvoid *pointer_address = ((const GLvoid *)offsetToPointer(pointer_buffer_offset));
	glColorPointer(size, type, stride, pointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyPixels(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jint type) {
	glCopyPixels(x, y, width, height, type);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexImage1D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalFormat, jint x, jint y, jint width, jint border) {
	glCopyTexImage1D(target, level, internalFormat, x, y, width, border);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalFormat, jint x, jint y, jint width, jint height, jint border) {
	glCopyTexImage2D(target, level, internalFormat, x, y, width, height, border);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexSubImage1D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint x, jint y, jint width) {
	glCopyTexSubImage1D(target, level, xoffset, x, y, width);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glCopyTexSubImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint x, jint y, jint width, jint height) {
	glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glCullFace(JNIEnv *env, jclass clazz, jint mode) {
	glCullFace(mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglDeleteTextures(JNIEnv *env, jclass clazz, jint n, jobject textures, jint textures_position) {
	const GLuint *textures_address = ((const GLuint *)(*env)->GetDirectBufferAddress(env, textures)) + textures_position;
	glDeleteTextures(n, textures_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glDeleteLists(JNIEnv *env, jclass clazz, jint list, jint range) {
	glDeleteLists(list, range);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glClearDepth(JNIEnv *env, jclass clazz, jdouble depth) {
	glClearDepth(depth);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glArrayElement(JNIEnv *env, jclass clazz, jint i) {
	glArrayElement(i);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glEnd(JNIEnv *env, jclass clazz) {
	glEnd();
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glBegin(JNIEnv *env, jclass clazz, jint mode) {
	glBegin(mode);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glBindTexture(JNIEnv *env, jclass clazz, jint target, jint texture) {
	glBindTexture(target, texture);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglBitmap(JNIEnv *env, jclass clazz, jint width, jint height, jfloat xorig, jfloat yorig, jfloat xmove, jfloat ymove, jobject bitmap, jint bitmap_position) {
	const GLubyte *bitmap_address = ((const GLubyte *)(*env)->GetDirectBufferAddress(env, bitmap)) + bitmap_position;
	glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglBitmapBO(JNIEnv *env, jclass clazz, jint width, jint height, jfloat xorig, jfloat yorig, jfloat xmove, jfloat ymove, jint bitmap_buffer_offset) {
	const GLubyte *bitmap_address = ((const GLubyte *)offsetToPointer(bitmap_buffer_offset));
	glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glBlendFunc(JNIEnv *env, jclass clazz, jint sfactor, jint dfactor) {
	glBlendFunc(sfactor, dfactor);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glCallList(JNIEnv *env, jclass clazz, jint list) {
	glCallList(list);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_nglCallLists(JNIEnv *env, jclass clazz, jint n, jint type, jobject lists, jint lists_position) {
	const GLvoid *lists_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, lists)) + lists_position));
	glCallLists(n, type, lists_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glClear(JNIEnv *env, jclass clazz, jint mask) {
	glClear(mask);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glClearAccum(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
	glClearAccum(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glClearColor(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
	glClearColor(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glAlphaFunc(JNIEnv *env, jclass clazz, jint func, jfloat ref) {
	glAlphaFunc(func, ref);
}

static void JNICALL Java_org_lwjgl_opengl_GL11_glAccum(JNIEnv *env, jclass clazz, jint op, jfloat value) {
	glAccum(op, value);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL11_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glViewport", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glViewport, "glViewport", (void *)&glViewport},
		{"glStencilMask", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glStencilMask, "glStencilMask", (void *)&glStencilMask},
		{"glStencilOp", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glStencilOp, "glStencilOp", (void *)&glStencilOp},
		{"glTexCoord4f", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexCoord4f, "glTexCoord4f", (void *)&glTexCoord4f},
		{"glTexCoord3f", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexCoord3f, "glTexCoord3f", (void *)&glTexCoord3f},
		{"glTexCoord2f", "(FF)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexCoord2f, "glTexCoord2f", (void *)&glTexCoord2f},
		{"glTexCoord1f", "(F)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexCoord1f, "glTexCoord1f", (void *)&glTexCoord1f},
		{"nglTexCoordPointer", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexCoordPointer, "glTexCoordPointer", (void *)&glTexCoordPointer},
		{"nglTexCoordPointerBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexCoordPointerBO, "glTexCoordPointer", (void *)&glTexCoordPointer},
		{"nglTexEnviv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexEnviv, "glTexEnviv", (void *)&glTexEnviv},
		{"nglTexEnvfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexEnvfv, "glTexEnvfv", (void *)&glTexEnvfv},
		{"glTexEnvi", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexEnvi, "glTexEnvi", (void *)&glTexEnvi},
		{"glTexEnvf", "(IIF)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexEnvf, "glTexEnvf", (void *)&glTexEnvf},
		{"nglTexGeniv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexGeniv, "glTexGeniv", (void *)&glTexGeniv},
		{"glTexGeni", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexGeni, "glTexGeni", (void *)&glTexGeni},
		{"nglTexGenfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexGenfv, "glTexGenfv", (void *)&glTexGenfv},
		{"glTexGenf", "(IIF)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexGenf, "glTexGenf", (void *)&glTexGenf},
		{"nglTexParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexParameteriv, "glTexParameteriv", (void *)&glTexParameteriv},
		{"nglTexParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexParameterfv, "glTexParameterfv", (void *)&glTexParameterfv},
		{"glTexParameteri", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexParameteri, "glTexParameteri", (void *)&glTexParameteri},
		{"glTexParameterf", "(IIF)V", (void *)&Java_org_lwjgl_opengl_GL11_glTexParameterf, "glTexParameterf", (void *)&glTexParameterf},
		{"nglTexSubImage2D", "(IIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexSubImage2D, "glTexSubImage2D", (void *)&glTexSubImage2D},
		{"nglTexSubImage2DBO", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexSubImage2DBO, "glTexSubImage2D", (void *)&glTexSubImage2D},
		{"nglTexSubImage1D", "(IIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexSubImage1D, "glTexSubImage1D", (void *)&glTexSubImage1D},
		{"nglTexSubImage1DBO", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexSubImage1DBO, "glTexSubImage1D", (void *)&glTexSubImage1D},
		{"nglTexImage2D", "(IIIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexImage2D, "glTexImage2D", (void *)&glTexImage2D},
		{"nglTexImage2DBO", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexImage2DBO, "glTexImage2D", (void *)&glTexImage2D},
		{"nglTexImage1D", "(IIIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexImage1D, "glTexImage1D", (void *)&glTexImage1D},
		{"nglTexImage1DBO", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglTexImage1DBO, "glTexImage1D", (void *)&glTexImage1D},
		{"glTranslatef", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glTranslatef, "glTranslatef", (void *)&glTranslatef},
		{"glVertex4i", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glVertex4i, "glVertex4i", (void *)&glVertex4i},
		{"glVertex4f", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glVertex4f, "glVertex4f", (void *)&glVertex4f},
		{"glVertex3i", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glVertex3i, "glVertex3i", (void *)&glVertex3i},
		{"glVertex3f", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glVertex3f, "glVertex3f", (void *)&glVertex3f},
		{"glVertex2i", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glVertex2i, "glVertex2i", (void *)&glVertex2i},
		{"glVertex2f", "(FF)V", (void *)&Java_org_lwjgl_opengl_GL11_glVertex2f, "glVertex2f", (void *)&glVertex2f},
		{"nglVertexPointer", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglVertexPointer, "glVertexPointer", (void *)&glVertexPointer},
		{"nglVertexPointerBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglVertexPointerBO, "glVertexPointer", (void *)&glVertexPointer},
		{"glStencilFunc", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glStencilFunc, "glStencilFunc", (void *)&glStencilFunc},
		{"glPopAttrib", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glPopAttrib, "glPopAttrib", (void *)&glPopAttrib},
		{"glPushAttrib", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glPushAttrib, "glPushAttrib", (void *)&glPushAttrib},
		{"nglPopClientAttrib", "()V", (void *)&Java_org_lwjgl_opengl_GL11_nglPopClientAttrib, "glPopClientAttrib", (void *)&glPopClientAttrib},
		{"nglPushClientAttrib", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPushClientAttrib, "glPushClientAttrib", (void *)&glPushClientAttrib},
		{"glPopMatrix", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glPopMatrix, "glPopMatrix", (void *)&glPopMatrix},
		{"glPushMatrix", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glPushMatrix, "glPushMatrix", (void *)&glPushMatrix},
		{"glPopName", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glPopName, "glPopName", (void *)&glPopName},
		{"glPushName", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glPushName, "glPushName", (void *)&glPushName},
		{"glRasterPos4i", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glRasterPos4i, "glRasterPos4i", (void *)&glRasterPos4i},
		{"glRasterPos4f", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glRasterPos4f, "glRasterPos4f", (void *)&glRasterPos4f},
		{"glRasterPos3i", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glRasterPos3i, "glRasterPos3i", (void *)&glRasterPos3i},
		{"glRasterPos3f", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glRasterPos3f, "glRasterPos3f", (void *)&glRasterPos3f},
		{"glRasterPos2i", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glRasterPos2i, "glRasterPos2i", (void *)&glRasterPos2i},
		{"glRasterPos2f", "(FF)V", (void *)&Java_org_lwjgl_opengl_GL11_glRasterPos2f, "glRasterPos2f", (void *)&glRasterPos2f},
		{"glReadBuffer", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glReadBuffer, "glReadBuffer", (void *)&glReadBuffer},
		{"nglReadPixels", "(IIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglReadPixels, "glReadPixels", (void *)&glReadPixels},
		{"nglReadPixelsBO", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglReadPixelsBO, "glReadPixels", (void *)&glReadPixels},
		{"glRecti", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glRecti, "glRecti", (void *)&glRecti},
		{"glRectf", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glRectf, "glRectf", (void *)&glRectf},
		{"glRenderMode", "(I)I", (void *)&Java_org_lwjgl_opengl_GL11_glRenderMode, "glRenderMode", (void *)&glRenderMode},
		{"glRotatef", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glRotatef, "glRotatef", (void *)&glRotatef},
		{"glScalef", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glScalef, "glScalef", (void *)&glScalef},
		{"glScissor", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glScissor, "glScissor", (void *)&glScissor},
		{"nglSelectBuffer", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglSelectBuffer, "glSelectBuffer", (void *)&glSelectBuffer},
		{"glShadeModel", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glShadeModel, "glShadeModel", (void *)&glShadeModel},
		{"nglMultMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglMultMatrixf, "glMultMatrixf", (void *)&glMultMatrixf},
		{"glEndList", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glEndList, "glEndList", (void *)&glEndList},
		{"glNewList", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glNewList, "glNewList", (void *)&glNewList},
		{"glNormal3i", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glNormal3i, "glNormal3i", (void *)&glNormal3i},
		{"glNormal3f", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glNormal3f, "glNormal3f", (void *)&glNormal3f},
		{"glNormal3b", "(BBB)V", (void *)&Java_org_lwjgl_opengl_GL11_glNormal3b, "glNormal3b", (void *)&glNormal3b},
		{"nglNormalPointer", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglNormalPointer, "glNormalPointer", (void *)&glNormalPointer},
		{"nglNormalPointerBO", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_nglNormalPointerBO, "glNormalPointer", (void *)&glNormalPointer},
		{"glOrtho", "(DDDDDD)V", (void *)&Java_org_lwjgl_opengl_GL11_glOrtho, "glOrtho", (void *)&glOrtho},
		{"glPassThrough", "(F)V", (void *)&Java_org_lwjgl_opengl_GL11_glPassThrough, "glPassThrough", (void *)&glPassThrough},
		{"nglPixelMapusv", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPixelMapusv, "glPixelMapusv", (void *)&glPixelMapusv},
		{"nglPixelMapusvBO", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPixelMapusvBO, "glPixelMapusv", (void *)&glPixelMapusv},
		{"nglPixelMapuiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPixelMapuiv, "glPixelMapuiv", (void *)&glPixelMapuiv},
		{"nglPixelMapuivBO", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPixelMapuivBO, "glPixelMapuiv", (void *)&glPixelMapuiv},
		{"nglPixelMapfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPixelMapfv, "glPixelMapfv", (void *)&glPixelMapfv},
		{"nglPixelMapfvBO", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPixelMapfvBO, "glPixelMapfv", (void *)&glPixelMapfv},
		{"glPixelStorei", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glPixelStorei, "glPixelStorei", (void *)&glPixelStorei},
		{"glPixelStoref", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL11_glPixelStoref, "glPixelStoref", (void *)&glPixelStoref},
		{"glPixelTransferi", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glPixelTransferi, "glPixelTransferi", (void *)&glPixelTransferi},
		{"glPixelTransferf", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL11_glPixelTransferf, "glPixelTransferf", (void *)&glPixelTransferf},
		{"glPixelZoom", "(FF)V", (void *)&Java_org_lwjgl_opengl_GL11_glPixelZoom, "glPixelZoom", (void *)&glPixelZoom},
		{"glPointSize", "(F)V", (void *)&Java_org_lwjgl_opengl_GL11_glPointSize, "glPointSize", (void *)&glPointSize},
		{"glPolygonMode", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glPolygonMode, "glPolygonMode", (void *)&glPolygonMode},
		{"glPolygonOffset", "(FF)V", (void *)&Java_org_lwjgl_opengl_GL11_glPolygonOffset, "glPolygonOffset", (void *)&glPolygonOffset},
		{"nglPolygonStipple", "(Ljava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPolygonStipple, "glPolygonStipple", (void *)&glPolygonStipple},
		{"nglPolygonStippleBO", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglPolygonStippleBO, "glPolygonStipple", (void *)&glPolygonStipple},
		{"glMatrixMode", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glMatrixMode, "glMatrixMode", (void *)&glMatrixMode},
		{"glIsTexture", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL11_glIsTexture, "glIsTexture", (void *)&glIsTexture},
		{"nglLightiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglLightiv, "glLightiv", (void *)&glLightiv},
		{"nglLightfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglLightfv, "glLightfv", (void *)&glLightfv},
		{"glLighti", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glLighti, "glLighti", (void *)&glLighti},
		{"glLightf", "(IIF)V", (void *)&Java_org_lwjgl_opengl_GL11_glLightf, "glLightf", (void *)&glLightf},
		{"nglLightModeliv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglLightModeliv, "glLightModeliv", (void *)&glLightModeliv},
		{"nglLightModelfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglLightModelfv, "glLightModelfv", (void *)&glLightModelfv},
		{"glLightModeli", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glLightModeli, "glLightModeli", (void *)&glLightModeli},
		{"glLightModelf", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL11_glLightModelf, "glLightModelf", (void *)&glLightModelf},
		{"glLineStipple", "(IS)V", (void *)&Java_org_lwjgl_opengl_GL11_glLineStipple, "glLineStipple", (void *)&glLineStipple},
		{"glLineWidth", "(F)V", (void *)&Java_org_lwjgl_opengl_GL11_glLineWidth, "glLineWidth", (void *)&glLineWidth},
		{"glListBase", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glListBase, "glListBase", (void *)&glListBase},
		{"glLoadIdentity", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glLoadIdentity, "glLoadIdentity", (void *)&glLoadIdentity},
		{"nglLoadMatrixf", "(Ljava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglLoadMatrixf, "glLoadMatrixf", (void *)&glLoadMatrixf},
		{"glLoadName", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glLoadName, "glLoadName", (void *)&glLoadName},
		{"glLogicOp", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glLogicOp, "glLogicOp", (void *)&glLogicOp},
		{"nglMap1f", "(IFFIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglMap1f, "glMap1f", (void *)&glMap1f},
		{"nglMap2f", "(IFFIIFFIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglMap2f, "glMap2f", (void *)&glMap2f},
		{"glMapGrid2f", "(IFFIFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glMapGrid2f, "glMapGrid2f", (void *)&glMapGrid2f},
		{"glMapGrid1f", "(IFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glMapGrid1f, "glMapGrid1f", (void *)&glMapGrid1f},
		{"nglMaterialiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglMaterialiv, "glMaterialiv", (void *)&glMaterialiv},
		{"nglMaterialfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglMaterialfv, "glMaterialfv", (void *)&glMaterialfv},
		{"glMateriali", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glMateriali, "glMateriali", (void *)&glMateriali},
		{"glMaterialf", "(IIF)V", (void *)&Java_org_lwjgl_opengl_GL11_glMaterialf, "glMaterialf", (void *)&glMaterialf},
		{"glIsList", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL11_glIsList, "glIsList", (void *)&glIsList},
		{"nglGetPolygonStipple", "(Ljava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPolygonStipple, "glGetPolygonStipple", (void *)&glGetPolygonStipple},
		{"nglGetPolygonStippleBO", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPolygonStippleBO, "glGetPolygonStipple", (void *)&glGetPolygonStipple},
		{"glGetString", "(I)Ljava/lang/String;", (void *)&Java_org_lwjgl_opengl_GL11_glGetString, "glGetString", (void *)&glGetString},
		{"nglGetTexEnvfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexEnvfv, "glGetTexEnvfv", (void *)&glGetTexEnvfv},
		{"nglGetTexEnviv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexEnviv, "glGetTexEnviv", (void *)&glGetTexEnviv},
		{"nglGetTexGenfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexGenfv, "glGetTexGenfv", (void *)&glGetTexGenfv},
		{"nglGetTexGeniv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexGeniv, "glGetTexGeniv", (void *)&glGetTexGeniv},
		{"nglGetTexImage", "(IIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexImage, "glGetTexImage", (void *)&glGetTexImage},
		{"nglGetTexImageBO", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexImageBO, "glGetTexImage", (void *)&glGetTexImage},
		{"nglGetTexLevelParameteriv", "(IIILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameteriv, "glGetTexLevelParameteriv", (void *)&glGetTexLevelParameteriv},
		{"nglGetTexLevelParameterfv", "(IIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexLevelParameterfv, "glGetTexLevelParameterfv", (void *)&glGetTexLevelParameterfv},
		{"nglGetTexParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexParameteriv, "glGetTexParameteriv", (void *)&glGetTexParameteriv},
		{"nglGetTexParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetTexParameterfv, "glGetTexParameterfv", (void *)&glGetTexParameterfv},
		{"glHint", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glHint, "glHint", (void *)&glHint},
		{"glInitNames", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glInitNames, "glInitNames", (void *)&glInitNames},
		{"nglInterleavedArrays", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglInterleavedArrays, "glInterleavedArrays", (void *)&glInterleavedArrays},
		{"nglInterleavedArraysBO", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_nglInterleavedArraysBO, "glInterleavedArrays", (void *)&glInterleavedArrays},
		{"glIsEnabled", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL11_glIsEnabled, "glIsEnabled", (void *)&glIsEnabled},
		{"nglGetPointerv", "(II)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPointerv, "glGetPointerv", (void *)&glGetPointerv},
		{"glFinish", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glFinish, "glFinish", (void *)&glFinish},
		{"glFlush", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glFlush, "glFlush", (void *)&glFlush},
		{"nglFogiv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglFogiv, "glFogiv", (void *)&glFogiv},
		{"nglFogfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglFogfv, "glFogfv", (void *)&glFogfv},
		{"glFogi", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glFogi, "glFogi", (void *)&glFogi},
		{"glFogf", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL11_glFogf, "glFogf", (void *)&glFogf},
		{"glFrontFace", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glFrontFace, "glFrontFace", (void *)&glFrontFace},
		{"glFrustum", "(DDDDDD)V", (void *)&Java_org_lwjgl_opengl_GL11_glFrustum, "glFrustum", (void *)&glFrustum},
		{"glGenLists", "(I)I", (void *)&Java_org_lwjgl_opengl_GL11_glGenLists, "glGenLists", (void *)&glGenLists},
		{"nglGenTextures", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGenTextures, "glGenTextures", (void *)&glGenTextures},
		{"nglGetIntegerv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetIntegerv, "glGetIntegerv", (void *)&glGetIntegerv},
		{"nglGetFloatv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetFloatv, "glGetFloatv", (void *)&glGetFloatv},
		{"nglGetDoublev", "(ILjava/nio/DoubleBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetDoublev, "glGetDoublev", (void *)&glGetDoublev},
		{"nglGetBooleanv", "(ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetBooleanv, "glGetBooleanv", (void *)&glGetBooleanv},
		{"nglGetClipPlane", "(ILjava/nio/DoubleBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetClipPlane, "glGetClipPlane", (void *)&glGetClipPlane},
		{"glGetError", "()I", (void *)&Java_org_lwjgl_opengl_GL11_glGetError, "glGetError", (void *)&glGetError},
		{"nglGetLightiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetLightiv, "glGetLightiv", (void *)&glGetLightiv},
		{"nglGetLightfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetLightfv, "glGetLightfv", (void *)&glGetLightfv},
		{"nglGetMapiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetMapiv, "glGetMapiv", (void *)&glGetMapiv},
		{"nglGetMapfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetMapfv, "glGetMapfv", (void *)&glGetMapfv},
		{"nglGetMaterialiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetMaterialiv, "glGetMaterialiv", (void *)&glGetMaterialiv},
		{"nglGetMaterialfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetMaterialfv, "glGetMaterialfv", (void *)&glGetMaterialfv},
		{"nglGetPixelMapusv", "(ILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapusv, "glGetPixelMapusv", (void *)&glGetPixelMapusv},
		{"nglGetPixelMapusvBO", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapusvBO, "glGetPixelMapusv", (void *)&glGetPixelMapusv},
		{"nglGetPixelMapuiv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapuiv, "glGetPixelMapuiv", (void *)&glGetPixelMapuiv},
		{"nglGetPixelMapuivBO", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapuivBO, "glGetPixelMapuiv", (void *)&glGetPixelMapuiv},
		{"nglGetPixelMapfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapfv, "glGetPixelMapfv", (void *)&glGetPixelMapfv},
		{"nglGetPixelMapfvBO", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_nglGetPixelMapfvBO, "glGetPixelMapfv", (void *)&glGetPixelMapfv},
		{"nglFeedbackBuffer", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglFeedbackBuffer, "glFeedbackBuffer", (void *)&glFeedbackBuffer},
		{"glDepthFunc", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glDepthFunc, "glDepthFunc", (void *)&glDepthFunc},
		{"glDepthMask", "(Z)V", (void *)&Java_org_lwjgl_opengl_GL11_glDepthMask, "glDepthMask", (void *)&glDepthMask},
		{"glDepthRange", "(DD)V", (void *)&Java_org_lwjgl_opengl_GL11_glDepthRange, "glDepthRange", (void *)&glDepthRange},
		{"glDrawArrays", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glDrawArrays, "glDrawArrays", (void *)&glDrawArrays},
		{"glDrawBuffer", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glDrawBuffer, "glDrawBuffer", (void *)&glDrawBuffer},
		{"nglDrawElements", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglDrawElements, "glDrawElements", (void *)&glDrawElements},
		{"nglDrawElementsBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglDrawElementsBO, "glDrawElements", (void *)&glDrawElements},
		{"nglDrawPixels", "(IIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglDrawPixels, "glDrawPixels", (void *)&glDrawPixels},
		{"nglDrawPixelsBO", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglDrawPixelsBO, "glDrawPixels", (void *)&glDrawPixels},
		{"glEdgeFlag", "(Z)V", (void *)&Java_org_lwjgl_opengl_GL11_glEdgeFlag, "glEdgeFlag", (void *)&glEdgeFlag},
		{"nglEdgeFlagPointer", "(ILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointer, "glEdgeFlagPointer", (void *)&glEdgeFlagPointer},
		{"nglEdgeFlagPointerBO", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_nglEdgeFlagPointerBO, "glEdgeFlagPointer", (void *)&glEdgeFlagPointer},
		{"glDisable", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glDisable, "glDisable", (void *)&glDisable},
		{"glEnable", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glEnable, "glEnable", (void *)&glEnable},
		{"glDisableClientState", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glDisableClientState, "glDisableClientState", (void *)&glDisableClientState},
		{"glEnableClientState", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glEnableClientState, "glEnableClientState", (void *)&glEnableClientState},
		{"glEvalCoord2f", "(FF)V", (void *)&Java_org_lwjgl_opengl_GL11_glEvalCoord2f, "glEvalCoord2f", (void *)&glEvalCoord2f},
		{"glEvalCoord1f", "(F)V", (void *)&Java_org_lwjgl_opengl_GL11_glEvalCoord1f, "glEvalCoord1f", (void *)&glEvalCoord1f},
		{"glEvalMesh2", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glEvalMesh2, "glEvalMesh2", (void *)&glEvalMesh2},
		{"glEvalMesh1", "(III)V", (void *)&Java_org_lwjgl_opengl_GL11_glEvalMesh1, "glEvalMesh1", (void *)&glEvalMesh1},
		{"glEvalPoint2", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glEvalPoint2, "glEvalPoint2", (void *)&glEvalPoint2},
		{"glEvalPoint1", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glEvalPoint1, "glEvalPoint1", (void *)&glEvalPoint1},
		{"glClearIndex", "(F)V", (void *)&Java_org_lwjgl_opengl_GL11_glClearIndex, "glClearIndex", (void *)&glClearIndex},
		{"glClearStencil", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glClearStencil, "glClearStencil", (void *)&glClearStencil},
		{"nglClipPlane", "(ILjava/nio/DoubleBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglClipPlane, "glClipPlane", (void *)&glClipPlane},
		{"glColor4ub", "(BBBB)V", (void *)&Java_org_lwjgl_opengl_GL11_glColor4ub, "glColor4ub", (void *)&glColor4ub},
		{"glColor4f", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glColor4f, "glColor4f", (void *)&glColor4f},
		{"glColor4b", "(BBBB)V", (void *)&Java_org_lwjgl_opengl_GL11_glColor4b, "glColor4b", (void *)&glColor4b},
		{"glColor3ub", "(BBB)V", (void *)&Java_org_lwjgl_opengl_GL11_glColor3ub, "glColor3ub", (void *)&glColor3ub},
		{"glColor3f", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glColor3f, "glColor3f", (void *)&glColor3f},
		{"glColor3b", "(BBB)V", (void *)&Java_org_lwjgl_opengl_GL11_glColor3b, "glColor3b", (void *)&glColor3b},
		{"glColorMask", "(ZZZZ)V", (void *)&Java_org_lwjgl_opengl_GL11_glColorMask, "glColorMask", (void *)&glColorMask},
		{"glColorMaterial", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glColorMaterial, "glColorMaterial", (void *)&glColorMaterial},
		{"nglColorPointer", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglColorPointer, "glColorPointer", (void *)&glColorPointer},
		{"nglColorPointerBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL11_nglColorPointerBO, "glColorPointer", (void *)&glColorPointer},
		{"glCopyPixels", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glCopyPixels, "glCopyPixels", (void *)&glCopyPixels},
		{"glCopyTexImage1D", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glCopyTexImage1D, "glCopyTexImage1D", (void *)&glCopyTexImage1D},
		{"glCopyTexImage2D", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glCopyTexImage2D, "glCopyTexImage2D", (void *)&glCopyTexImage2D},
		{"glCopyTexSubImage1D", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glCopyTexSubImage1D, "glCopyTexSubImage1D", (void *)&glCopyTexSubImage1D},
		{"glCopyTexSubImage2D", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengl_GL11_glCopyTexSubImage2D, "glCopyTexSubImage2D", (void *)&glCopyTexSubImage2D},
		{"glCullFace", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glCullFace, "glCullFace", (void *)&glCullFace},
		{"nglDeleteTextures", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglDeleteTextures, "glDeleteTextures", (void *)&glDeleteTextures},
		{"glDeleteLists", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glDeleteLists, "glDeleteLists", (void *)&glDeleteLists},
		{"glClearDepth", "(D)V", (void *)&Java_org_lwjgl_opengl_GL11_glClearDepth, "glClearDepth", (void *)&glClearDepth},
		{"glArrayElement", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glArrayElement, "glArrayElement", (void *)&glArrayElement},
		{"glEnd", "()V", (void *)&Java_org_lwjgl_opengl_GL11_glEnd, "glEnd", (void *)&glEnd},
		{"glBegin", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glBegin, "glBegin", (void *)&glBegin},
		{"glBindTexture", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glBindTexture, "glBindTexture", (void *)&glBindTexture},
		{"nglBitmap", "(IIFFFFLjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglBitmap, "glBitmap", (void *)&glBitmap},
		{"nglBitmapBO", "(IIFFFFI)V", (void *)&Java_org_lwjgl_opengl_GL11_nglBitmapBO, "glBitmap", (void *)&glBitmap},
		{"glBlendFunc", "(II)V", (void *)&Java_org_lwjgl_opengl_GL11_glBlendFunc, "glBlendFunc", (void *)&glBlendFunc},
		{"glCallList", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glCallList, "glCallList", (void *)&glCallList},
		{"nglCallLists", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL11_nglCallLists, "glCallLists", (void *)&glCallLists},
		{"glClear", "(I)V", (void *)&Java_org_lwjgl_opengl_GL11_glClear, "glClear", (void *)&glClear},
		{"glClearAccum", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glClearAccum, "glClearAccum", (void *)&glClearAccum},
		{"glClearColor", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL11_glClearColor, "glClearColor", (void *)&glClearColor},
		{"glAlphaFunc", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL11_glAlphaFunc, "glAlphaFunc", (void *)&glAlphaFunc},
		{"glAccum", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL11_glAccum, "glAccum", (void *)&glAccum}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
