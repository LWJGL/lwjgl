/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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

package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.Buffer;

/**
 * $Id$
 *
 * The core OpenGL1.4 API, with no extensions.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class CoreGL implements CoreGLConstants {
	public static native void glAccum(int op, float value);
	public static native void glAlphaFunc(int func, float ref);
	public static native void glColorTable(
		int target,
		int internalFormat,
		int width,
		int format,
		int type,
		Buffer data);

	public static native void glColorSubTable(
		int target,
		int start,
		int count,
		int format,
		int type,
		Buffer data);

	public static native void glGetColorTable(
		int target,
		int format,
		int type,
		Buffer data);

	public static native void glGetColorTableParameteriv(
		int target,
		int pname,
		IntBuffer params);

	public static native void glGetColorTableParameterfv(
		int target,
		int pname,
		FloatBuffer params);

	public static native void glClearColor(float red, float green, float blue, float alpha);
	public static native void glClearAccum(float red, float green, float blue, float alpha);
	public static native void glClear(int mask);
	public static native void glCallLists(int n, int type, Buffer lists);
	public static native void glCallList(int list);
	public static native void glBlendFunc(int sfactor, int dfactor);
	public static native void glBlendColor(float red, float green, float blue, float alpha);
	public static native void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap);
	public static native void glBindTexture(int target, int texture);
	public static native void glBegin(int mode);
	public static native void glEnd();
	public static native void glArrayElement(int i);
	public static native boolean glAreTexturesResident(int n, IntBuffer textureNames, ByteBuffer residences);
	public static native void glClearDepth(double depth);
	public static native void glDeleteLists(int list, int range);
	public static native void glDeleteTextures(int n, IntBuffer textures);
	public static native void glCullFace(int mode);
	public static native void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);
	public static native void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width);
	public static native void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border);
	public static native void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border);
        /* OpenGL 1.2 functions */
	public static native void glColorTableParameteriv(int target, int pname, int params);
	public static native void glColorTableParameterfv(int target, int pname, int params);
	public static native void glCopyColorSubTable(int target, int start, int x, int y, int width);
	public static native void glCopyColorTable(int target, int internalformat, int x, int y, int width);
	public static native void glBlendEquation(int mode);
	public static native void glHistogram(int target, int width, int internalformat, boolean sink);
	public static native void glResetHistogram(int target);
	public static native void glGetHistogram(int target, boolean reset, int format, int type, Buffer values);
	public static native void glGetHistogramParameterfv(int target, int pname, FloatBuffer params);
	public static native void glGetHistogramParameteriv(int target, int pname, IntBuffer params);
	public static native void glMinmax(int target, int internalformat, boolean sink);
	public static native void glResetMinmax(int target);
	public static native void glGetMinmax(int target, boolean reset, int format, int types, Buffer values);
	public static native void glGetMinmaxParameterfv(int target, int pname, FloatBuffer params);
	public static native void glGetMinmaxParameteriv(int target, int pname, IntBuffer params);
	public static native void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, Buffer image);
	public static native void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer image);
	public static native void glConvolutionParameterf(int target, int pname, float params);
	public static native void glConvolutionParameterfv(int target, int pname, FloatBuffer params);
	public static native void glConvolutionParameteri(int target, int pname, int params);
	public static native void glConvolutionParameteriv(int target, int pname, IntBuffer params);
	public static native void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width);
	public static native void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height);
	public static native void glGetConvolutionFilter(int target, int format, int type, Buffer image);
	public static native void glGetConvolutionParameterfv(int target, int pname, FloatBuffer params);
	public static native void glGetConvolutionParameteriv(int target, int pname, IntBuffer params);
	public static native void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, Buffer column);
	public static native void glGetSeparableFilter(int target, int format, int type, Buffer row, Buffer column, Buffer span);
	public static native void glDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices);
	public static native void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, Buffer pixels);
	public static native void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, Buffer pixels);
	public static native void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);
        /* OpenGL 1.3 funstions */
	public static native void glActiveTexture(int texture);
	public static native void glClientActiveTexture(int texture);
	public static native void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, Buffer data);
	public static native void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data);
	public static native void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, Buffer data);
	public static native void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, Buffer data);
	public static native void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data);
	public static native void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, Buffer data);
	public static native void glGetCompressedTexImage(int target, int lod, Buffer img);
	public static native void glMultiTexCoord1d(int target, double s);
	public static native void glMultiTexCoord1dv(int target, int v);
	public static native void glMultiTexCoord1f(int target, float s);
	public static native void glMultiTexCoord1fv(int target, int v);
	public static native void glMultiTexCoord1i(int target, int s);
	public static native void glMultiTexCoord1iv(int target, int v);
	public static native void glMultiTexCoord1s(int target, short s);
	public static native void glMultiTexCoord1sv(int target, int v);
	public static native void glMultiTexCoord2d(int target, double s, double t);
	public static native void glMultiTexCoord2dv(int target, int v);
	public static native void glMultiTexCoord2f(int target, float s, float t);
	public static native void glMultiTexCoord2fv(int target, int v);
	public static native void glMultiTexCoord2i(int target, int s, int t);
	public static native void glMultiTexCoord2iv(int target, int v);
	public static native void glMultiTexCoord2s(int target, short s, short t);
	public static native void glMultiTexCoord2sv(int target, int v);
	public static native void glMultiTexCoord3d(int target, double s, double t, double r);
	public static native void glMultiTexCoord3dv(int target, int v);
	public static native void glMultiTexCoord3f(int target, float s, float t, float r);
	public static native void glMultiTexCoord3fv(int target, int v);
	public static native void glMultiTexCoord3i(int target, int s, int t, int r);
	public static native void glMultiTexCoord3iv(int target, int v);
	public static native void glMultiTexCoord3s(int target, short s, short t, short r);
	public static native void glMultiTexCoord3sv(int target, int v);
	public static native void glMultiTexCoord4d(int target, double s, double t, double r, double q);
	public static native void glMultiTexCoord4dv(int target, int v);
	public static native void glMultiTexCoord4f(int target, float s, float t, float r, float q);
	public static native void glMultiTexCoord4fv(int target, int v);
	public static native void glMultiTexCoord4i(int target, int s, int t, int r, int q);
	public static native void glMultiTexCoord4iv(int target, int v);
	public static native void glMultiTexCoord4s(int target, short s, short t, short r, short q);
	public static native void glMultiTexCoord4sv(int target, int v);
	public static native void glLoadTransposeMatrixd(int m);
	public static native void glLoadTransposeMatrixf(int m);
	public static native void glMultTransposeMatrixd(int m);
	public static native void glMultTransposeMatrixf(int m);
	public static native void glSampleCoverage(float value, boolean invert);
	public static native void glCopyPixels(int x, int y, int width, int height, int type);
	public static native void glColorPointer(int size, int type, int stride, Buffer pointer);
	public static native void glColorMaterial(int face, int mode);
	public static native void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);
	public static native void glColor3b(byte red, byte green, byte blue);
	public static native void glColor3d(double red, double green, double blue);
	public static native void glColor3f(float red, float green, float blue);
	public static native void glColor3i(int red, int green, int blue);
	public static native void glColor3s(short red, short green, short blue);
	public static native void glColor3ub(byte red, byte green, byte blue);
	public static native void glColor3ui(int red, int green, int blue);
	public static native void glColor3us(short red, short green, short blue);
	public static native void glColor4b(byte red, byte green, byte blue, byte alpha);
	public static native void glColor4d(double red, double green, double blue, double alpha);
	public static native void glColor4f(float red, float green, float blue, float alpha);
	public static native void glColor4i(int red, int green, int blue, int alpha);
	public static native void glColor4s(short red, short green, short blue, short alpha);
	public static native void glColor4ub(byte red, byte green, byte blue, byte alpha);
	public static native void glColor4ui(int red, int green, int blue, int alpha);
	public static native void glColor4us(short red, short green, short blue, short alpha);
        public static native void glColor3bv(ByteBuffer v);
        public static native void glColor3dv(DoubleBuffer v);
        public static native void glColor3fv(FloatBuffer v);
        public static native void glColor3iv(IntBuffer v);
        public static native void glColor3sv(CharBuffer v);
        public static native void glColor3ubv(ByteBuffer v);
        public static native void glColor3uiv(IntBuffer v);
        public static native void glColor3usv(CharBuffer v);
        public static native void glColor4bv(ByteBuffer v);
        public static native void glColor4dv(DoubleBuffer v);
        public static native void glColor4fv(FloatBuffer v);
        public static native void glColor4iv(IntBuffer v);
        public static native void glColor4sv(CharBuffer v);
        public static native void glColor4ubv(ByteBuffer v);
        public static native void glColor4uiv(IntBuffer v);
	public static native void glClipPlane(int plane, DoubleBuffer equation);
	public static native void glClearStencil(int s);
	public static native void glClearIndex(float c);
	public static native void glEvalPoint1(int i);
	public static native void glEvalPoint2(int i, int j);
	public static native void glEvalMesh1(int mode, int i1, int i2);
	public static native void glEvalMesh2(int mode, int i1, int i2, int j1, int j2);
	public static native void glEvalCoord1d(double u);
	public static native void glEvalCoord1f(float u);
	public static native void glEvalCoord2d(double u, double v);
	public static native void glEvalCoord2f(float u, float v);
	public static native void glEvalCoord1dv(DoubleBuffer u);
	public static native void glEvalCoord1fv(FloatBuffer u);
	public static native void glEvalCoord2dv(DoubleBuffer u);
	public static native void glEvalCoord2fv(FloatBuffer u);
	public static native void glEnableClientState(int cap);
	public static native void glDisableClientState(int cap);
	public static native void glEnable(int cap);
	public static native void glDisable(int cap);
	public static native void glEdgeFlagPointer(int stride, Buffer pointer);
	public static native void glEdgeFlag(boolean flag);
	public static native void glEdgeFlagv(ByteBuffer flag);
	public static native void glDrawPixels(int width, int height, int format, int type, Buffer pixels);
	public static native void glDrawElements(int mode, int count, int type, Buffer indices);
	public static native void glDrawBuffer(int mode);
	public static native void glDrawArrays(int mode, int first, int count);
	public static native void glDepthRange(double zNear, double zFar);
	public static native void glDepthMask(boolean flag);
	public static native void glDepthFunc(int func);
	public static native void glFeedbackBuffer(int size, int type, FloatBuffer buffer);
	public static native void glGetPixelMapfv(int map, FloatBuffer values);
	public static native void glGetPixelMapuiv(int map, IntBuffer values);
	public static native void glGetPixelMapusv(int map, CharBuffer values);
	public static native void glGetMaterialfv(int face, int pname, FloatBuffer params);
	public static native void glGetMaterialiv(int face, int pname, IntBuffer params);
	public static native void glGetMapdv(int target, int query, DoubleBuffer v);
	public static native void glGetMapfv(int target, int query, FloatBuffer v);
	public static native void glGetMapiv(int target, int query, IntBuffer v);
	public static native void glGetLightfv(int light, int pname, FloatBuffer params);
	public static native void glGetLightiv(int light, int pname, IntBuffer params);
	public static native int glGetError();
	public static native void glGetClipPlane(int plane, int equation);
	public static native void glGetBooleanv(int pname, ByteBuffer params);
	public static native void glGetDoublev(int pname, DoubleBuffer params);
	public static native void glGetFloatv(int pname, FloatBuffer params);
	public static native void glGetIntegerv(int pname, IntBuffer params);
	public static native void glGenTextures(int n, IntBuffer textures);
	public static native int glGenLists(int range);
	public static native void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar);
	public static native void glFrontFace(int mode);
	public static native void glFogf(int pname, float param);
	public static native void glFogi(int pname, int param);
	public static native void glFogfv(int pname, FloatBuffer params);
	public static native void glFogiv(int pname, IntBuffer params);
	public static native void glFlush();
	public static native void glFinish();
	/**
	 * Fetch a pointer from OpenGL. Will return a ByteBuffer representing the pointer, where
	 * the size argument specifies the buffer size in bytes.
	 *
	 * @param size The size of the memory area pointed to. This is the size of the returned ByteBuffer.
	 * @return The ByteBuffer of the specified size pointing to the returned address.
	 */
	public static native ByteBuffer glGetPointerv(int pname, int size);
	public static native boolean glIsEnabled(int cap);
	public static native void glInterleavedArrays(int format, int stride, Buffer pointer);
	public static native void glInitNames();
	public static native void glIndexPointer(int type, int stride, Buffer pointer);
	public static native void glIndexMask(int mask);
	public static native void glIndexd(double c);
	public static native void glIndexf(float c);
	public static native void glIndexi(int c);
	public static native void glIndexs(short c);
	public static native void glIndexub(byte c);
	public static native void glIndexdv(DoubleBuffer c);
	public static native void glIndexfv(FloatBuffer c);
	public static native void glIndexiv(IntBuffer c);
	public static native void glIndexsv(CharBuffer c);
	public static native void glIndexubv(ByteBuffer c);
	public static native void glHint(int target, int mode);
	public static native void glGetTexParameterfv(int target, int pname, FloatBuffer params);
	public static native void glGetTexParameteriv(int target, int pname, IntBuffer params);
	public static native void glGetTexLevelParameterfv(int target, int level, int pname, FloatBuffer params);
	public static native void glGetTexLevelParameteriv(int target, int level, int pname, IntBuffer params);
	public static native void glGetTexImage(int target, int level, int format, int type, Buffer pixels);
	public static native void glGetTexGendv(int coord, int pname, DoubleBuffer params);
	public static native void glGetTexGenfv(int coord, int pname, FloatBuffer params);
	public static native void glGetTexGeniv(int coord, int pname, IntBuffer params);
	public static native void glGetTexEnvfv(int target, int pname, FloatBuffer params);
	public static native void glGetTexEnviv(int target, int pname, IntBuffer params);
	public static native String glGetString(int name);
	public static native void glGetPolygonStipple(ByteBuffer mask);
	public static native boolean glIsList(int list);
	public static native void glMaterialf(int face, int pname, float param);
	public static native void glMateriali(int face, int pname, int param);
	public static native void glMaterialfv(int face, int pname, FloatBuffer params);
	public static native void glMaterialiv(int face, int pname, IntBuffer params);
	public static native void glMapGrid1d(int un, double u1, double u2);
	public static native void glMapGrid1f(int un, float u1, float u2);
	public static native void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2);
	public static native void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);
	public static native void glMap2d(int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points);
	public static native void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points);
	public static native void glMap1d(int target, double u1, double u2, int stride, int order, DoubleBuffer points);
	public static native void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points);
	public static native void glLogicOp(int opcode);
	public static native void glLoadName(int name);
	public static native void glLoadMatrixd(DoubleBuffer m);
	public static native void glLoadMatrixf(FloatBuffer m);
	public static native void glLoadIdentity();
	public static native void glListBase(int base);
	public static native void glLineWidth(float width);
	public static native void glLineStipple(int factor, short pattern);
	public static native void glLightModelf(int pname, float param);
	public static native void glLightModeli(int pname, int param);
	public static native void glLightModelfv(int pname, FloatBuffer params);
	public static native void glLightModeliv(int pname, IntBuffer params);
	public static native void glLightf(int light, int pname, float param);
	public static native void glLighti(int light, int pname, int param);
	public static native void glLightfv(int light, int pname, FloatBuffer params);
	public static native void glLightiv(int light, int pname, IntBuffer params);
	public static native boolean glIsTexture(int texture);
	public static native void glMatrixMode(int mode);
	public static native void glPolygonStipple(ByteBuffer mask);
	public static native void glPolygonOffset(float factor, float units);
	public static native void glPolygonMode(int face, int mode);
	public static native void glPointSize(float size);
	public static native void glPixelZoom(float xfactor, float yfactor);
	public static native void glPixelTransferf(int pname, float param);
	public static native void glPixelTransferi(int pname, int param);
	public static native void glPixelStoref(int pname, float param);
	public static native void glPixelStorei(int pname, int param);
	public static native void glPixelMapfv(int map, int mapsize, FloatBuffer values);
	public static native void glPixelMapuiv(int map, int mapsize, IntBuffer values);
	public static native void glPixelMapusv(int map, int mapsize, CharBuffer values);
	public static native void glPassThrough(float token);
	public static native void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar);
	public static native void glNormalPointer(int type, int stride, Buffer pointer);
	public static native void glNormal3b(byte nx, byte ny, byte nz);
	public static native void glNormal3d(double nx, double ny, double nz);
	public static native void glNormal3f(float nx, float ny, float nz);
	public static native void glNormal3i(int nx, int ny, int nz);
	public static native void glNormal3s(short nx, short ny, short nz);
	public static native void glNormal3bv(ByteBuffer v);
	public static native void glNormal3dv(DoubleBuffer v);
	public static native void glNormal3fv(FloatBuffer v);
	public static native void glNormal3iv(IntBuffer v);
	public static native void glNormal3sv(CharBuffer v);
	public static native void glNewList(int list, int mode);
	public static native void glEndList();
	public static native void glMultMatrixd(DoubleBuffer m);
	public static native void glMultMatrixf(FloatBuffer m);
	public static native void glPrioritizeTextures(int n, IntBuffer textureNames, FloatBuffer priorities);
	public static native void glShadeModel(int mode);
	public static native void glSelectBuffer(int size, IntBuffer buffer);
	public static native void glScissor(int x, int y, int width, int height);
	public static native void glScaled(double x, double y, double z);
	public static native void glScalef(float x, float y, float z);
	public static native void glRotated(double angle, double x, double y, double z);
	public static native void glRotatef(float angle, float x, float y, float z);
	public static native int glRenderMode(int mode);
	public static native void glRectd(double x1, double y1, double x2, double y2);
	public static native void glRectf(float x1, float y1, float x2, float y2);
	public static native void glRecti(int x1, int y1, int x2, int y2);
	public static native void glRects(short x1, short y1, short x2, short y2);
	public static native void glRectdv(DoubleBuffer v1, DoubleBuffer v2);
	public static native void glRectfv(FloatBuffer v1, FloatBuffer v2);
	public static native void glRectiv(IntBuffer v1, IntBuffer v2);
	public static native void glRectsv(CharBuffer v1, CharBuffer v2);
	public static native void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels);
	public static native void glReadBuffer(int mode);
	public static native void glRasterPos2d(double x, double y);
	public static native void glRasterPos2f(float x, float y);
	public static native void glRasterPos2i(int x, int y);
	public static native void glRasterPos2s(short x, short y);
	public static native void glRasterPos3d(double x, double y, double z);
	public static native void glRasterPos3f(float x, float y, float z);
	public static native void glRasterPos3i(int x, int y, int z);
	public static native void glRasterPos3s(short x, short y, short z);
	public static native void glRasterPos4d(double x, double y, double z, double w);
	public static native void glRasterPos4f(float x, float y, float z, float w);
	public static native void glRasterPos4i(int x, int y, int z, int w);
	public static native void glRasterPos4s(short x, short y, short z, short w);
	public static native void glRasterPos2dv(DoubleBuffer v);
	public static native void glRasterPos2fv(FloatBuffer v);
	public static native void glRasterPos2iv(IntBuffer v);
	public static native void glRasterPos2sv(CharBuffer v);
	public static native void glRasterPos3dv(DoubleBuffer v);
	public static native void glRasterPos3fv(FloatBuffer v);
	public static native void glRasterPos3iv(IntBuffer v);
	public static native void glRasterPos3sv(CharBuffer v);
	public static native void glRasterPos4dv(DoubleBuffer v);
	public static native void glRasterPos4fv(FloatBuffer v);
	public static native void glRasterPos4iv(IntBuffer v);
	public static native void glRasterPos4sv(CharBuffer v);
	public static native void glPushName(int name);
	public static native void glPopName();
	public static native void glPushMatrix();
	public static native void glPopMatrix();
	public static native void glPushClientAttrib(int mask);
	public static native void glPopClientAttrib();
	public static native void glPushAttrib(int mask);
	public static native void glPopAttrib();
	public static native void glStencilFunc(int func, int ref, int mask);
	public static native void glVertexPointer(int size, int type, int stride, Buffer pointer);
	public static native void glVertex2d(double x, double y);
	public static native void glVertex2f(float x, float y);
	public static native void glVertex2i(int x, int y);
	public static native void glVertex2s(short x, short y);
	public static native void glVertex3d(double x, double y, double z);
	public static native void glVertex3f(float x, float y, float z);
	public static native void glVertex3i(int x, int y, int z);
	public static native void glVertex3s(short x, short y, short z);
	public static native void glVertex4d(double x, double y, double z, double w);
	public static native void glVertex4f(float x, float y, float z, float w);
	public static native void glVertex4i(int x, int y, int z, int w);
	public static native void glVertex4s(short x, short y, short z, short w);
	public static native void glVertex2dv(DoubleBuffer v);
	public static native void glVertex2fv(FloatBuffer v);
	public static native void glVertex2iv(IntBuffer v);
	public static native void glVertex2sv(CharBuffer v);
	public static native void glVertex3dv(DoubleBuffer v);
	public static native void glVertex3fv(FloatBuffer v);
	public static native void glVertex3iv(IntBuffer v);
	public static native void glVertex3sv(CharBuffer v);
	public static native void glVertex4dv(DoubleBuffer v);
	public static native void glVertex4fv(FloatBuffer v);
	public static native void glVertex4iv(IntBuffer v);
	public static native void glVertex4sv(CharBuffer v);
	public static native void glTranslated(double x, double y, double z);
	public static native void glTranslatef(float x, float y, float z);
	public static native void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels);
	public static native void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, Buffer pixels);
	public static native void glTexParameterf(int target, int pname, float param);
	public static native void glTexParameteri(int target, int pname, int param);
	public static native void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels);
	public static native void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, Buffer pixels);
	public static native void glTexGend(int coord, int pname, double param);
	public static native void glTexGenf(int coord, int pname, float param);
	public static native void glTexGeni(int coord, int pname, int param);
	public static native void glTexGendv(int coord, int pname, DoubleBuffer params);
	public static native void glTexGenfv(int coord, int pname, FloatBuffer params);
	public static native void glTexGeniv(int coord, int pname, IntBuffer params);
	public static native void glTexEnvf(int target, int pname, float param);
	public static native void glTexEnvi(int target, int pname, int param);
	public static native void glTexEnvfv(int target, int pname, FloatBuffer params);
	public static native void glTexEnviv(int target, int pname, IntBuffer params);
	public static native void glTexCoordPointer(int size, int type, int stride, Buffer pointer);
	public static native void glTexCoord1d(double s);
	public static native void glTexCoord1f(float s);
	public static native void glTexCoord1i(int s);
	public static native void glTexCoord1s(short s);
	public static native void glTexCoord2d(double s, double t);
	public static native void glTexCoord2f(float s, float t);
	public static native void glTexCoord2i(int s, int t);
	public static native void glTexCoord2s(short s, short t);
	public static native void glTexCoord3d(double s, double t, double r);
	public static native void glTexCoord3f(float s, float t, float r);
	public static native void glTexCoord3i(int s, int t, int r);
	public static native void glTexCoord3s(short s, short t, short r);
	public static native void glTexCoord4d(double s, double t, double r, double q);
	public static native void glTexCoord4f(float s, float t, float r, float q);
	public static native void glTexCoord4i(int s, int t, int r, int q);
	public static native void glTexCoord4s(short s, short t, short r, short q);
	public static native void glTexCoord1dv(DoubleBuffer v);
	public static native void glTexCoord1fv(FloatBuffer v);
	public static native void glTexCoord1iv(IntBuffer v);
	public static native void glTexCoord1sv(CharBuffer v);
	public static native void glTexCoord2dv(DoubleBuffer v);
	public static native void glTexCoord2fv(FloatBuffer v);
	public static native void glTexCoord2iv(IntBuffer v);
	public static native void glTexCoord2sv(CharBuffer v);
	public static native void glTexCoord3dv(DoubleBuffer v);
	public static native void glTexCoord3fv(FloatBuffer v);
	public static native void glTexCoord3iv(IntBuffer v);
	public static native void glTexCoord3sv(CharBuffer v);
	public static native void glTexCoord4dv(DoubleBuffer v);
	public static native void glTexCoord4fv(FloatBuffer v);
	public static native void glTexCoord4iv(IntBuffer v);
	public static native void glTexCoord4sv(CharBuffer v);
	public static native void glStencilOp(int fail, int zfail, int zpass);
	public static native void glStencilMask(int mask);
	public static native void glViewport(int x, int y, int width, int height);
        public static native void glMultiDrawArrays(
                int mode,
                ByteBuffer piFirst,
                ByteBuffer piCount,
                int primcount);

/*        public static native void glMultiDrawElements(
                int mode,
                int piCount,
                int type,
                int pIndices,
                int primcount);*/
}


