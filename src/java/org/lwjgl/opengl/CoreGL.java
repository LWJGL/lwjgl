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

package org.lwjgl.opengl;

/**
 * $Id$
 *
 * The core OpenGL1.4 API, with no extensions.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class CoreGL extends BaseGL implements CoreGLConstants {
	
	/**
	 * Constructor for CoreGL.
	 */
	public CoreGL() {
		super();
	}

	public native void accum(int op, float value);
	public native void alphaFunc(int func, float ref);
	public native void colorTable(
		int target,
		int internalFormat,
		int width,
		int format,
		int type,
		int data);

	public native void colorSubTable(
		int target,
		int start,
		int count,
		int format,
		int type,
		int data);

	public native void getColorTable(
		int target,
		int format,
		int type,
		int data);

	public native void getColorTableParameteriv(
		int target,
		int pname,
		int params);

	public native void getColorTableParameterfv(
		int target,
		int pname,
		int params);

	public native void clearColor(float red, float green, float blue, float alpha);
	public native void clearAccum(float red, float green, float blue, float alpha);
	public native void clear(int mask);
	public native void callLists(int n, int type, int lists);
	public native void callList(int list);
	public native void blendFunc(int sfactor, int dfactor);
	public native void blendColor(float red, float green, float blue, float alpha);
	public native void bitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, int bitmap);
	public native void bindTexture(int target, int texture);
	public native void begin(int mode);
	public native void end();
	public native void arrayElement(int i);
	public native boolean areTexturesResident(int n, int textureNames, int residences);
	public native void clearDepth(double depth);
	public native void deleteLists(int list, int range);
	public native void deleteTextures(int n, int textures);
	public native void cullFace(int mode);
	public native void copyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);
	public native void copyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width);
	public native void copyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border);
	public native void copyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border);
        /* OpenGL 1.2 functions */
	public native void colorTableParameteriv(int target, int pname, int params);
	public native void colorTableParameterfv(int target, int pname, int params);
	public native void copyColorSubTable(int target, int start, int x, int y, int width);
	public native void copyColorTable(int target, int internalformat, int x, int y, int width);
	public native void blendEquation(int mode);
	public native void histogram(int target, int width, int internalformat, boolean sink);
	public native void resetHistogram(int target);
	public native void getHistogram(int target, boolean reset, int format, int type, int values);
	public native void getHistogramParameterfv(int target, int pname, int params);
	public native void getHistogramParameteriv(int target, int pname, int params);
	public native void minmax(int target, int internalformat, boolean sink);
	public native void resetMinmax(int target);
	public native void getMinmax(int target, boolean reset, int format, int types, int values);
	public native void getMinmaxParameterfv(int target, int pname, int params);
	public native void getMinmaxParameteriv(int target, int pname, int params);
	public native void convolutionFilter1D(int target, int internalformat, int width, int format, int type, int image);
	public native void convolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, int image);
	public native void convolutionParameterf(int target, int pname, float params);
	public native void convolutionParameterfv(int target, int pname, int params);
	public native void convolutionParameteri(int target, int pname, int params);
	public native void convolutionParameteriv(int target, int pname, int params);
	public native void copyConvolutionFilter1D(int target, int internalformat, int x, int y, int width);
	public native void copyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height);
	public native void getConvolutionFilter(int target, int format, int type, int image);
	public native void getConvolutionParameterfv(int target, int pname, int params);
	public native void getConvolutionParameteriv(int target, int pname, int params);
	public native void separableFilter2D(int target, int internalformat, int width, int height, int format, int type, int row, int column);
	public native void getSeparableFilter(int target, int format, int type, int row, int column, int span);
	public native void drawRangeElements(int mode, int start, int end, int count, int type, int indices);
	public native void texImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, int pixels);
	public native void texSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int pixels);
	public native void copyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);
        /* OpenGL 1.3 funstions */
	public native void activeTexture(int texture);
	public native void clientActiveTexture(int texture);
	public native void compressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, int data);
	public native void compressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, int data);
	public native void compressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, int data);
	public native void compressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, int data);
	public native void compressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, int data);
	public native void compressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, int data);
	public native void getCompressedTexImage(int target, int lod, int img);
	public native void multiTexCoord1d(int target, double s);
	public native void multiTexCoord1dv(int target, int v);
	public native void multiTexCoord1f(int target, float s);
	public native void multiTexCoord1fv(int target, int v);
	public native void multiTexCoord1i(int target, int s);
	public native void multiTexCoord1iv(int target, int v);
	public native void multiTexCoord1s(int target, short s);
	public native void multiTexCoord1sv(int target, int v);
	public native void multiTexCoord2d(int target, double s, double t);
	public native void multiTexCoord2dv(int target, int v);
	public native void multiTexCoord2f(int target, float s, float t);
	public native void multiTexCoord2fv(int target, int v);
	public native void multiTexCoord2i(int target, int s, int t);
	public native void multiTexCoord2iv(int target, int v);
	public native void multiTexCoord2s(int target, short s, short t);
	public native void multiTexCoord2sv(int target, int v);
	public native void multiTexCoord3d(int target, double s, double t, double r);
	public native void multiTexCoord3dv(int target, int v);
	public native void multiTexCoord3f(int target, float s, float t, float r);
	public native void multiTexCoord3fv(int target, int v);
	public native void multiTexCoord3i(int target, int s, int t, int r);
	public native void multiTexCoord3iv(int target, int v);
	public native void multiTexCoord3s(int target, short s, short t, short r);
	public native void multiTexCoord3sv(int target, int v);
	public native void multiTexCoord4d(int target, double s, double t, double r, double q);
	public native void multiTexCoord4dv(int target, int v);
	public native void multiTexCoord4f(int target, float s, float t, float r, float q);
	public native void multiTexCoord4fv(int target, int v);
	public native void multiTexCoord4i(int target, int s, int t, int r, int q);
	public native void multiTexCoord4iv(int target, int v);
	public native void multiTexCoord4s(int target, short s, short t, short r, short q);
	public native void multiTexCoord4sv(int target, int v);
	public native void loadTransposeMatrixd(int m);
	public native void loadTransposeMatrixf(int m);
	public native void multTransposeMatrixd(int m);
	public native void multTransposeMatrixf(int m);
	public native void sampleCoverage(float value, boolean invert);
	public native void copyPixels(int x, int y, int width, int height, int type);
	public native void colorPointer(int size, int type, int stride, int pointer);
	public native void colorMaterial(int face, int mode);
	public native void colorMask(boolean red, boolean green, boolean blue, boolean alpha);
	public native void color3b(byte red, byte green, byte blue);
	public native void color3d(double red, double green, double blue);
	public native void color3f(float red, float green, float blue);
	public native void color3i(int red, int green, int blue);
	public native void color3s(short red, short green, short blue);
	public native void color3ub(byte red, byte green, byte blue);
	public native void color3ui(int red, int green, int blue);
	public native void color3us(short red, short green, short blue);
	public native void color4b(byte red, byte green, byte blue, byte alpha);
	public native void color4d(double red, double green, double blue, double alpha);
	public native void color4f(float red, float green, float blue, float alpha);
	public native void color4i(int red, int green, int blue, int alpha);
	public native void color4s(short red, short green, short blue, short alpha);
	public native void color4ub(byte red, byte green, byte blue, byte alpha);
	public native void color4ui(int red, int green, int blue, int alpha);
	public native void color4us(short red, short green, short blue, short alpha);
        public native void color3bv(int v);
        public native void color3dv(int v);
        public native void color3fv(int v);
        public native void color3iv(int v);
        public native void color3sv(int v);
        public native void color3ubv(int v);
        public native void color3uiv(int v);
        public native void color3usv(int v);
        public native void color4bv(int v);
        public native void color4dv(int v);
        public native void color4fv(int v);
        public native void color4iv(int v);
        public native void color4sv(int v);
        public native void color4ubv(int v);
        public native void color4uiv(int v);
	public native void clipPlane(int plane, int equation);
	public native void clearStencil(int s);
	public native void clearIndex(float c);
	public native void evalPoint1(int i);
	public native void evalPoint2(int i, int j);
	public native void evalMesh1(int mode, int i1, int i2);
	public native void evalMesh2(int mode, int i1, int i2, int j1, int j2);
	public native void evalCoord1d(double u);
	public native void evalCoord1f(float u);
	public native void evalCoord2d(double u, double v);
	public native void evalCoord2f(float u, float v);
	public native void evalCoord1dv(int u);
	public native void evalCoord1fv(int u);
	public native void evalCoord2dv(int u);
	public native void evalCoord2fv(int u);
	public native void enableClientState(int cap);
	public native void disableClientState(int cap);
	public native void enable(int cap);
	public native void disable(int cap);
	public native void edgeFlagPointer(int stride, int pointer);
	public native void edgeFlag(boolean flag);
	public native void edgeFlagv(int flag);
	public native void drawPixels(int width, int height, int format, int type, int pixels);
	public native void drawElements(int mode, int count, int type, int indices);
	public native void drawBuffer(int mode);
	public native void drawArrays(int mode, int first, int count);
	public native void depthRange(double zNear, double zFar);
	public native void depthMask(boolean flag);
	public native void depthFunc(int func);
	public native void feedbackBuffer(int size, int type, int buffer);
	public native void getPixelMapfv(int map, int values);
	public native void getPixelMapuiv(int map, int values);
	public native void getPixelMapusv(int map, int values);
	public native void getMaterialfv(int face, int pname, int params);
	public native void getMaterialiv(int face, int pname, int params);
	public native void getMapdv(int target, int query, int v);
	public native void getMapfv(int target, int query, int v);
	public native void getMapiv(int target, int query, int v);
	public native void getLightfv(int light, int pname, int params);
	public native void getLightiv(int light, int pname, int params);
	public native int getError();
	public native void getClipPlane(int plane, int equation);
	public native void getBooleanv(int pname, int params);
	public native void getDoublev(int pname, int params);
	public native void getFloatv(int pname, int params);
	public native void getIntegerv(int pname, int params);
	public native void genTextures(int n, int textures);
	public native int genLists(int range);
	public native void frustum(double left, double right, double bottom, double top, double zNear, double zFar);
	public native void frontFace(int mode);
	public native void fogf(int pname, float param);
	public native void fogi(int pname, int param);
	public native void fogfv(int pname, int params);
	public native void fogiv(int pname, int params);
	public native void flush();
	public native void finish();
	public native void getPointerv(int pname, int params);
	public native boolean isEnabled(int cap);
	public native void interleavedArrays(int format, int stride, int pointer);
	public native void initNames();
	public native void indexPointer(int type, int stride, int pointer);
	public native void indexMask(int mask);
	public native void indexd(double c);
	public native void indexf(float c);
	public native void indexi(int c);
	public native void indexs(short c);
	public native void indexub(byte c);
	public native void indexdv(int c);
	public native void indexfv(int c);
	public native void indexiv(int c);
	public native void indexsv(int c);
	public native void indexubv(int c);
	public native void hint(int target, int mode);
	public native void getTexParameterfv(int target, int pname, int params);
	public native void getTexParameteriv(int target, int pname, int params);
	public native void getTexLevelParameterfv(int target, int level, int pname, int params);
	public native void getTexLevelParameteriv(int target, int level, int pname, int params);
	public native void getTexImage(int target, int level, int format, int type, int pixels);
	public native void getTexGendv(int coord, int pname, int params);
	public native void getTexGenfv(int coord, int pname, int params);
	public native void getTexGeniv(int coord, int pname, int params);
	public native void getTexEnvfv(int target, int pname, int params);
	public native void getTexEnviv(int target, int pname, int params);
	public native String getString(int name);
	public native void getPolygonStipple(int mask);
	public native boolean isList(int list);
	public native void materialf(int face, int pname, float param);
	public native void materiali(int face, int pname, int param);
	public native void materialfv(int face, int pname, int params);
	public native void materialiv(int face, int pname, int params);
	public native void mapGrid1d(int un, double u1, double u2);
	public native void mapGrid1f(int un, float u1, float u2);
	public native void mapGrid2d(int un, double u1, double u2, int vn, double v1, double v2);
	public native void mapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);
	public native void map2d(int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, int points);
	public native void map2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, int points);
	public native void map1d(int target, double u1, double u2, int stride, int order, int points);
	public native void map1f(int target, float u1, float u2, int stride, int order, int points);
	public native void logicOp(int opcode);
	public native void loadName(int name);
	public native void loadMatrixd(int m);
	public native void loadMatrixf(int m);
	public native void loadIdentity();
	public native void listBase(int base);
	public native void lineWidth(float width);
	public native void lineStipple(int factor, short pattern);
	public native void lightModelf(int pname, float param);
	public native void lightModeli(int pname, int param);
	public native void lightModelfv(int pname, int params);
	public native void lightModeliv(int pname, int params);
	public native void lightf(int light, int pname, float param);
	public native void lighti(int light, int pname, int param);
	public native void lightfv(int light, int pname, int params);
	public native void lightiv(int light, int pname, int params);
	public native boolean isTexture(int texture);
	public native void matrixMode(int mode);
	public native void polygonStipple(int mask);
	public native void polygonOffset(float factor, float units);
	public native void polygonMode(int face, int mode);
	public native void pointSize(float size);
	public native void pixelZoom(float xfactor, float yfactor);
	public native void pixelTransferf(int pname, float param);
	public native void pixelTransferi(int pname, int param);
	public native void pixelStoref(int pname, float param);
	public native void pixelStorei(int pname, int param);
	public native void pixelMapfv(int map, int mapsize, int values);
	public native void pixelMapuiv(int map, int mapsize, int values);
	public native void pixelMapusv(int map, int mapsize, int values);
	public native void passThrough(float token);
	public native void ortho(double left, double right, double bottom, double top, double zNear, double zFar);
	public native void normalPointer(int type, int stride, int pointer);
	public native void normal3b(byte nx, byte ny, byte nz);
	public native void normal3d(double nx, double ny, double nz);
	public native void normal3f(float nx, float ny, float nz);
	public native void normal3i(int nx, int ny, int nz);
	public native void normal3s(short nx, short ny, short nz);
	public native void normal3bv(int v);
	public native void normal3dv(int v);
	public native void normal3fv(int v);
	public native void normal3iv(int v);
	public native void normal3sv(int v);
	public native void newList(int list, int mode);
	public native void endList();
	public native void multMatrixd(int m);
	public native void multMatrixf(int m);
	public native void prioritizeTextures(int n, int textureNames, int priorities);
	public native void shadeModel(int mode);
	public native void selectBuffer(int size, int buffer);
	public native void scissor(int x, int y, int width, int height);
	public native void scaled(double x, double y, double z);
	public native void scalef(float x, float y, float z);
	public native void rotated(double angle, double x, double y, double z);
	public native void rotatef(float angle, float x, float y, float z);
	public native int renderMode(int mode);
	public native void rectd(double x1, double y1, double x2, double y2);
	public native void rectf(float x1, float y1, float x2, float y2);
	public native void recti(int x1, int y1, int x2, int y2);
	public native void rects(short x1, short y1, short x2, short y2);
	public native void rectdv(int v1, int v2);
	public native void rectfv(int v1, int v2);
	public native void rectiv(int v1, int v2);
	public native void rectsv(int v1, int v2);
	public native void readPixels(int x, int y, int width, int height, int format, int type, int pixels);
	public native void readBuffer(int mode);
	public native void rasterPos2d(double x, double y);
	public native void rasterPos2f(float x, float y);
	public native void rasterPos2i(int x, int y);
	public native void rasterPos2s(short x, short y);
	public native void rasterPos3d(double x, double y, double z);
	public native void rasterPos3f(float x, float y, float z);
	public native void rasterPos3i(int x, int y, int z);
	public native void rasterPos3s(short x, short y, short z);
	public native void rasterPos4d(double x, double y, double z, double w);
	public native void rasterPos4f(float x, float y, float z, float w);
	public native void rasterPos4i(int x, int y, int z, int w);
	public native void rasterPos4s(short x, short y, short z, short w);
	public native void rasterPos2dv(int v);
	public native void rasterPos2fv(int v);
	public native void rasterPos2iv(int v);
	public native void rasterPos2sv(int v);
	public native void rasterPos3dv(int v);
	public native void rasterPos3fv(int v);
	public native void rasterPos3iv(int v);
	public native void rasterPos3sv(int v);
	public native void rasterPos4dv(int v);
	public native void rasterPos4fv(int v);
	public native void rasterPos4iv(int v);
	public native void rasterPos4sv(int v);
	public native void pushName(int name);
	public native void popName();
	public native void pushMatrix();
	public native void popMatrix();
	public native void pushClientAttrib(int mask);
	public native void popClientAttrib();
	public native void pushAttrib(int mask);
	public native void popAttrib();
	public native void stencilFunc(int func, int ref, int mask);
	public native void vertexPointer(int size, int type, int stride, int pointer);
	public native void vertex2d(double x, double y);
	public native void vertex2f(float x, float y);
	public native void vertex2i(int x, int y);
	public native void vertex2s(short x, short y);
	public native void vertex3d(double x, double y, double z);
	public native void vertex3f(float x, float y, float z);
	public native void vertex3i(int x, int y, int z);
	public native void vertex3s(short x, short y, short z);
	public native void vertex4d(double x, double y, double z, double w);
	public native void vertex4f(float x, float y, float z, float w);
	public native void vertex4i(int x, int y, int z, int w);
	public native void vertex4s(short x, short y, short z, short w);
	public native void vertex2dv(int v);
	public native void vertex2fv(int v);
	public native void vertex2iv(int v);
	public native void vertex2sv(int v);
	public native void vertex3dv(int v);
	public native void vertex3fv(int v);
	public native void vertex3iv(int v);
	public native void vertex3sv(int v);
	public native void vertex4dv(int v);
	public native void vertex4fv(int v);
	public native void vertex4iv(int v);
	public native void vertex4sv(int v);
	public native void translated(double x, double y, double z);
	public native void translatef(float x, float y, float z);
	public native void texSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int pixels);
	public native void texSubImage1D(int target, int level, int xoffset, int width, int format, int type, int pixels);
	public native void texParameterf(int target, int pname, float param);
	public native void texParameteri(int target, int pname, int param);
	public native void texImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, int pixels);
	public native void texImage1D(int target, int level, int internalformat, int width, int border, int format, int type, int pixels);
	public native void texGend(int coord, int pname, double param);
	public native void texGenf(int coord, int pname, float param);
	public native void texGeni(int coord, int pname, int param);
	public native void texGendv(int coord, int pname, int params);
	public native void texGenfv(int coord, int pname, int params);
	public native void texGeniv(int coord, int pname, int params);
	public native void texEnvf(int target, int pname, float param);
	public native void texEnvi(int target, int pname, int param);
	public native void texEnvfv(int target, int pname, int params);
	public native void texEnviv(int target, int pname, int params);
	public native void texCoordPointer(int size, int type, int stride, int pointer);
	public native void texCoord1d(double s);
	public native void texCoord1f(float s);
	public native void texCoord1i(int s);
	public native void texCoord1s(short s);
	public native void texCoord2d(double s, double t);
	public native void texCoord2f(float s, float t);
	public native void texCoord2i(int s, int t);
	public native void texCoord2s(short s, short t);
	public native void texCoord3d(double s, double t, double r);
	public native void texCoord3f(float s, float t, float r);
	public native void texCoord3i(int s, int t, int r);
	public native void texCoord3s(short s, short t, short r);
	public native void texCoord4d(double s, double t, double r, double q);
	public native void texCoord4f(float s, float t, float r, float q);
	public native void texCoord4i(int s, int t, int r, int q);
	public native void texCoord4s(short s, short t, short r, short q);
	public native void texCoord1dv(int v);
	public native void texCoord1fv(int v);
	public native void texCoord1iv(int v);
	public native void texCoord1sv(int v);
	public native void texCoord2dv(int v);
	public native void texCoord2fv(int v);
	public native void texCoord2iv(int v);
	public native void texCoord2sv(int v);
	public native void texCoord3dv(int v);
	public native void texCoord3fv(int v);
	public native void texCoord3iv(int v);
	public native void texCoord3sv(int v);
	public native void texCoord4dv(int v);
	public native void texCoord4fv(int v);
	public native void texCoord4iv(int v);
	public native void texCoord4sv(int v);
	public native void stencilOp(int fail, int zfail, int zpass);
	public native void stencilMask(int mask);
	public native void viewport(int x, int y, int width, int height);

}


