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
package org.lwjgl.util;

import java.nio.*;

/**
 * $Id$ This is an interface describing an Object that can render using
 * OpenGL1.1, 1.2, 1.3, 1.4, 1.5, and all the EXT and ARB extensions in the LWJGL library. Its main purpose is to stop
 * object-oriented zealots annoying us any more.
 *
 * @author $Author$
 * @version $Revision$
 */
public interface IGL {

	/**
	 * @param op
	 * @param value
	 */
	void glAccum(int op, float value);

	/**
	 * @param func
	 * @param ref
	 */
	void glAlphaFunc(int func, float ref);

	/**
	 * @param i
	 */
	void glArrayElement(int i);

	/**
	 * @param mode
	 */
	void glBegin(int mode);

	/**
	 * @param target
	 * @param texture
	 */
	void glBindTexture(int target, int texture);

	/**
	 * @param width
	 * @param height
	 * @param xorig
	 * @param yorig
	 * @param xmove
	 * @param ymove
	 * @param bitmap
	 */
	void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap);

	/**
	 *
	 * @param width
	 * @param height
	 * @param xorig
	 * @param yorig
	 * @param xmove
	 * @param ymove
	 * @param buffer_offect
	 */
	void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, int buffer_offect);

	/**
	 * @param sfactor
	 * @param dfactor
	 */
	void glBlendFunc(int sfactor, int dfactor);

	/**
	 * @param list
	 */
	void glCallList(int list);

	/**
	 * @param lists
	 */
	void glCallLists(ByteBuffer lists);

	/**
	 * @param lists
	 */
	void glCallLists(IntBuffer lists);

	/**
	 * @param lists
	 */
	void glCallLists(ShortBuffer lists);

	/**
	 * @param mask
	 */
	void glClear(int mask);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void glClearAccum(float red, float green, float blue, float alpha);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void glClearColor(float red, float green, float blue, float alpha);

	/**
	 * @param depth
	 */
	void glClearDepth(double depth);

	/**
	 * @param c
	 */
	void glClearIndex(float c);

	/**
	 * @param s
	 */
	void glClearStencil(int s);

	/**
	 * @param plane
	 * @param equation
	 */
	void glClipPlane(int plane, DoubleBuffer equation);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glColor3b(byte red, byte green, byte blue);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glColor3f(float red, float green, float blue);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glColor3ub(byte red, byte green, byte blue);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void glColor4b(byte red, byte green, byte blue, byte alpha);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void glColor4f(float red, float green, float blue, float alpha);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void glColor4ub(byte red, byte green, byte blue, byte alpha);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);

	/**
	 * @param face
	 * @param mode
	 */
	void glColorMaterial(int face, int mode);

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pointer
	 */
	void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer);

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	void glColorPointer(int size, int stride, FloatBuffer pointer);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glColorPointer(int size, int type, int stride, int buffer_offset);

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param type
	 */
	void glCopyPixels(int x, int y, int width, int height, int type);

	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param x
	 * @param y
	 * @param width
	 * @param border
	 */
	void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border);


	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param border
	 */
	void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param x
	 * @param y
	 * @param width
	 */
	void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);


	/**
	 * @param mode
	 */
	void glCullFace(int mode);

	/**
	 * @param list
	 * @param range
	 */
	void glDeleteLists(int list, int range);

	/**
	 * @param textures
	 */
	void glDeleteTextures(IntBuffer textures);

	/**
	 * @param func
	 */
	void glDepthFunc(int func);

	/**
	 * @param flag
	 */
	void glDepthMask(boolean flag);

	/**
	 * @param zNear
	 * @param zFar
	 */
	void glDepthRange(double zNear, double zFar);

	/**
	 * @param cap
	 */
	void glDisable(int cap);

	/**
	 * @param cap
	 */
	void glDisableClientState(int cap);

	/**
	 * @param mode
	 * @param first
	 * @param count
	 */
	void glDrawArrays(int mode, int first, int count);

	/**
	 * @param mode
	 */
	void glDrawBuffer(int mode);

	/**
	 * @param mode
	 * @param indices
	 */
	void glDrawElements(int mode, ByteBuffer indices);

	/**
	 * @param mode
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	void glDrawElements(int mode, int count, int type, int buffer_offset);

	/**
	 * @param mode
	 * @param indices
	 */
	void glDrawElements(int mode, IntBuffer indices);

	/**
	 * @param mode
	 * @param indices
	 */
	void glDrawElements(int mode, ShortBuffer indices);

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels);

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels);

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels);

	/**
	 *
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glDrawPixels(int width, int height, int format, int type, int buffer_offset);

	/**
	 * @param flag
	 */
	void glEdgeFlag(boolean flag);

	/**
	 * @param stride
	 * @param pointer
	 */
	void glEdgeFlagPointer(int stride, ByteBuffer pointer);

	/**
	 * @param stride
	 * @param buffer_offset
	 */
	void glEdgeFlagPointer(int stride, int buffer_offset);

	/**
	 * @param cap
	 */
	void glEnable(int cap);

	/**
	 * @param cap
	 */
	void glEnableClientState(int cap);

	/**
	 *
	 */
	void glEnd();

	/**
	 *
	 */
	void glEndList();

	/**
	 * @param u
	 */
	void glEvalCoord1f(float u);

	/**
	 * @param u
	 * @param v
	 */
	void glEvalCoord2f(float u, float v);

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 */
	void glEvalMesh1(int mode, int i1, int i2);

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 * @param j1
	 * @param j2
	 */
	void glEvalMesh2(int mode, int i1, int i2, int j1, int j2);

	/**
	 * @param i
	 */
	void glEvalPoint1(int i);

	/**
	 * @param i
	 * @param j
	 */
	void glEvalPoint2(int i, int j);

	/**
	 * @param type
	 * @param buffer
	 */
	void glFeedbackBuffer(int type, FloatBuffer buffer);

	/**
	 *
	 */
	void glFinish();

	/**
	 *
	 */
	void glFlush();

	/**
	 * @param pname
	 * @param params
	 */
	void glFog(int pname, FloatBuffer params);

	/**
	 * @param pname
	 * @param params
	 */
	void glFog(int pname, IntBuffer params);

	/**
	 * @param pname
	 * @param param
	 */
	void glFogf(int pname, float param);

	/**
	 * @param pname
	 * @param param
	 */
	void glFogi(int pname, int param);

	/**
	 * @param mode
	 */
	void glFrontFace(int mode);

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar);

	/**
	 * @param range
	 *
	 * @return
	 */
	int glGenLists(int range);

	/**
	 * @param textures
	 */
	void glGenTextures(IntBuffer textures);

	/**
	 * @param pname
	 * @param params
	 */
	void glGetBoolean(int pname, ByteBuffer params);

	/**
	 * @param plane
	 * @param equation
	 */
	void glGetClipPlane(int plane, DoubleBuffer equation);

	/**
	 * @param pname
	 * @param params
	 */
	void glGetDouble(int pname, DoubleBuffer params);

	/**
	 * @return
	 */
	int glGetError();

	/**
	 * @param pname
	 * @param params
	 */
	void glGetFloat(int pname, FloatBuffer params);

	/**
	 * @param pname
	 * @param params
	 */
	void glGetInteger(int pname, IntBuffer params);

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	void glGetLight(int light, int pname, FloatBuffer params);

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	void glGetLight(int light, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	void glGetMap(int target, int query, FloatBuffer v);

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	void glGetMap(int target, int query, IntBuffer v);

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	void glGetMaterial(int face, int pname, FloatBuffer params);

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	void glGetMaterial(int face, int pname, IntBuffer params);

	/**
	 * @param map
	 * @param values
	 */
	void glGetPixelMap(int map, FloatBuffer values);

	/**
	 * @param map
	 * @param buffer_offset
	 */
	void glGetPixelMapfv(int map, int buffer_offset);

	/**
	 * @param map
	 * @param values
	 */
	void glGetPixelMap(int map, IntBuffer values);

	/**
	 * @param map
	 * @param buffer_offset
	 */
	void glGetPixelMapuiv(int map, int buffer_offset);

	/**
	 * @param map
	 * @param values
	 */
	void glGetPixelMap(int map, ShortBuffer values);

	/**
	 * @param map
	 * @param buffer_offset
	 */
	void glGetPixelMapusv(int map, int buffer_offset);

	/**
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	ByteBuffer glGetPointerv(int pname, int size);

	/**
	 * @param mask
	 */
	void glGetPolygonStipple(ByteBuffer mask);

	/**
	 * @param buffer_offset
	 */
	void glGetPolygonStipple(int buffer_offset);

	/**
	 * @param name
	 *
	 * @return
	 */
	String glGetString(int name);

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	void glGetTexEnv(int coord, int pname, FloatBuffer params);

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	void glGetTexEnv(int coord, int pname, IntBuffer params);

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	void glGetTexGen(int coord, int pname, FloatBuffer params);

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	void glGetTexGen(int coord, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels);

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels);

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels);

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glGetTexImage(int target, int level, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetTexParameter(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetTexParameter(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param mode
	 */
	void glHint(int target, int mode);

	/**
	 *
	 */
	void glInitNames();

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	void glInterleavedArrays(int format, int stride, ByteBuffer pointer);

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	void glInterleavedArrays(int format, int stride, FloatBuffer pointer);

	/**
	 * @param format
	 * @param stride
	 * @param buffer_offset
	 */
	void glInterleavedArrays(int format, int stride, int buffer_offset);

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	void glInterleavedArrays(int format, int stride, IntBuffer pointer);

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	void glInterleavedArrays(int format, int stride, ShortBuffer pointer);

	/**
	 * @param cap
	 *
	 * @return
	 */
	boolean glIsEnabled(int cap);

	/**
	 * @param list
	 *
	 * @return
	 */
	boolean glIsList(int list);

	/**
	 * @param texture
	 *
	 * @return
	 */
	boolean glIsTexture(int texture);

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	void glLight(int light, int pname, FloatBuffer params);

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	void glLight(int light, int pname, IntBuffer params);

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	void glLightf(int light, int pname, float param);

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	void glLighti(int light, int pname, int param);

	/**
	 * @param pname
	 * @param params
	 */
	void glLightModel(int pname, FloatBuffer params);

	/**
	 * @param pname
	 * @param params
	 */
	void glLightModel(int pname, IntBuffer params);

	/**
	 * @param pname
	 * @param param
	 */
	void glLightModelf(int pname, float param);

	/**
	 * @param pname
	 * @param param
	 */
	void glLightModeli(int pname, int param);

	/**
	 * @param factor
	 * @param pattern
	 */
	void glLineStipple(int factor, short pattern);

	/**
	 * @param width
	 */
	void glLineWidth(float width);

	/**
	 * @param base
	 */
	void glListBase(int base);

	/**
	 *
	 */
	void glLoadIdentity();

	/**
	 * @param m
	 */
	void glLoadMatrix(FloatBuffer m);

	/**
	 * @param name
	 */
	void glLoadName(int name);

	/**
	 * @param opcode
	 */
	void glLogicOp(int opcode);

	/**
	 * @param target
	 * @param u1
	 * @param u2
	 * @param stride
	 * @param order
	 * @param points
	 */
	void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points);

	/**
	 * @param target
	 * @param u1
	 * @param u2
	 * @param ustride
	 * @param uorder
	 * @param v1
	 * @param v2
	 * @param vstride
	 * @param vorder
	 * @param points
	 */
	void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points);


	/**
	 * @param un
	 * @param u1
	 * @param u2
	 */
	void glMapGrid1f(int un, float u1, float u2);

	/**
	 * @param un
	 * @param u1
	 * @param u2
	 * @param vn
	 * @param v1
	 * @param v2
	 */
	void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	void glMaterial(int face, int pname, FloatBuffer params);

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	void glMaterial(int face, int pname, IntBuffer params);

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	void glMaterialf(int face, int pname, float param);

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	void glMateriali(int face, int pname, int param);

	/**
	 * @param mode
	 */
	void glMatrixMode(int mode);

	/**
	 * @param m
	 */
	void glMultMatrix(FloatBuffer m);

	/**
	 * @param list
	 * @param mode
	 */
	void glNewList(int list, int mode);

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	void glNormal3b(byte nx, byte ny, byte nz);

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	void glNormal3f(float nx, float ny, float nz);

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	void glNormal3i(int nx, int ny, int nz);

	/**
	 * @param stride
	 * @param pointer
	 */
	void glNormalPointer(int stride, ByteBuffer pointer);

	/**
	 * @param stride
	 * @param pointer
	 */
	void glNormalPointer(int stride, FloatBuffer pointer);

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glNormalPointer(int type, int stride, int buffer_offset);

	/**
	 * @param stride
	 * @param pointer
	 */
	void glNormalPointer(int stride, IntBuffer pointer);

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar);

	/**
	 * @param token
	 */
	void glPassThrough(float token);

	/**
	 * @param map
	 * @param values
	 */
	void glPixelMap(int map, FloatBuffer values);

	/**
	 * @param map
	 * @param values
	 */
	void glPixelMap(int map, IntBuffer values);

	/**
	 * @param map
	 * @param values
	 */
	void glPixelMap(int map, ShortBuffer values);

	/**
	 *
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	void glPixelMapfv(int map, int mapsize, int buffer_offset);

	/**
	 *
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	void glPixelMapuiv(int map, int mapsize, int buffer_offset);

	/**
	 *
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	void glPixelMapusv(int map, int mapsize, int buffer_offset);

	/**
	 * @param pname
	 * @param param
	 */
	void glPixelStoref(int pname, float param);

	/**
	 * @param pname
	 * @param param
	 */
	void glPixelStorei(int pname, int param);

	/**
	 * @param pname
	 * @param param
	 */
	void glPixelTransferf(int pname, float param);

	/**
	 * @param pname
	 * @param param
	 */
	void glPixelTransferi(int pname, int param);

	/**
	 * @param xfactor
	 * @param yfactor
	 */
	void glPixelZoom(float xfactor, float yfactor);

	/**
	 * @param size
	 */
	void glPointSize(float size);

	/**
	 * @param face
	 * @param mode
	 */
	void glPolygonMode(int face, int mode);

	/**
	 * @param factor
	 * @param units
	 */
	void glPolygonOffset(float factor, float units);

	/**
	 * @param mask
	 */
	void glPolygonStipple(ByteBuffer mask);

	/**
	 *
	 * @param buffer_offset
	 */
	void glPolygonStipple(int buffer_offset);

	/**
	 *
	 */
	void glPopAttrib();

	/**
	 *
	 */
	void glPopClientAttrib();

	/**
	 *
	 */
	void glPopMatrix();

	/**
	 *
	 */
	void glPopName();

	/**
	 * @param mask
	 */
	void glPushAttrib(int mask);

	/**
	 * @param mask
	 */
	void glPushClientAttrib(int mask);

	/**
	 *
	 */
	void glPushMatrix();

	/**
	 * @param name
	 */
	void glPushName(int name);

	/**
	 * @param x
	 * @param y
	 */
	void glRasterPos2f(float x, float y);

	/**
	 * @param x
	 * @param y
	 */
	void glRasterPos2i(int x, int y);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glRasterPos3f(float x, float y, float z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glRasterPos3i(int x, int y, int z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glRasterPos4f(float x, float y, float z, float w);

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glRasterPos4i(int x, int y, int z, int w);

	/**
	 * @param mode
	 */
	void glReadBuffer(int mode);

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels);

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels);

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels);

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glReadPixels(int x, int y, int width, int height, int format, int type, int buffer_offset);

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	void glRectf(float x1, float y1, float x2, float y2);

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	void glRecti(int x1, int y1, int x2, int y2);

	/**
	 * @param mode
	 *
	 * @return
	 */
	int glRenderMode(int mode);

	/**
	 * @param angle
	 * @param x
	 * @param y
	 * @param z
	 */
	void glRotatef(float angle, float x, float y, float z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glScalef(float x, float y, float z);

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void glScissor(int x, int y, int width, int height);

	/**
	 * @param buffer
	 */
	void glSelectBuffer(IntBuffer buffer);

	/**
	 * @param mode
	 */
	void glShadeModel(int mode);

	/**
	 * @param func
	 * @param ref
	 * @param mask
	 */
	void glStencilFunc(int func, int ref, int mask);

	/**
	 * @param mask
	 */
	void glStencilMask(int mask);

	/**
	 * @param fail
	 * @param zfail
	 * @param zpass
	 */
	void glStencilOp(int fail, int zfail, int zpass);

	/**
	 * @param s
	 */
	void glTexCoord1f(float s);

	/**
	 * @param s
	 * @param t
	 */
	void glTexCoord2f(float s, float t);

	/**
	 * @param s
	 * @param t
	 * @param r
	 */
	void glTexCoord3f(float s, float t, float r);

	/**
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	void glTexCoord4f(float s, float t, float r, float q);

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	void glTexCoordPointer(int size, int stride, FloatBuffer pointer);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glTexCoordPointer(int size, int type, int stride, int buffer_offset);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glTexEnv(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glTexEnv(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	void glTexEnvf(int target, int pname, float param);

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	void glTexEnvi(int target, int pname, int param);

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	void glTexGen(int coord, int pname, FloatBuffer params);

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	void glTexGen(int coord, int pname, IntBuffer params);

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	void glTexGenf(int coord, int pname, float param);

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	void glTexGeni(int coord, int pname, int param);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels);

	/**
	 *
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels);

	/**
	 *
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	void glTexParameter(int target, int pname, FloatBuffer param);

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	void glTexParameter(int target, int pname, IntBuffer param);

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	void glTexParameterf(int target, int pname, float param);

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	void glTexParameteri(int target, int pname, int param);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels);

	/**
	 *
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels);

	/**
	 *
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int buffer_offset);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glTranslatef(float x, float y, float z);

	/**
	 * @param x
	 * @param y
	 */
	void glVertex2f(float x, float y);

	/**
	 * @param x
	 * @param y
	 */
	void glVertex2i(int x, int y);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glVertex3f(float x, float y, float z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glVertex3i(int x, int y, int z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glVertex4f(float x, float y, float z, float w);

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glVertex4i(int x, int y, int z, int w);

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	void glVertexPointer(int size, int stride, FloatBuffer pointer);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glVertexPointer(int size, int type, int stride, int buffer_offset);

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	void glVertexPointer(int size, int stride, IntBuffer pointer);

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void glViewport(int x, int y, int width, int height);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);


	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices);

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	void glDrawRangeElements(int mode, int start, int end, int count, int type, int buffer_offset);

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	void glDrawRangeElements(int mode, int start, int end, IntBuffer indices);

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices);

	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels);

	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels);

	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param type
	 * @param pixels
	 */
	void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels);

	/**
	 *
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int buffer_offset);

	/**
	 * @param texture
	 */
	void glActiveTexture(int texture);

	/**
	 * @param texture
	 */
	void glClientActiveTexture(int texture);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param buffer_offset
	 */
	void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param buffer_offset
	 */
	void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param buffer_offset
	 */
	void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ByteBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, FloatBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, IntBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ShortBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param buffer_offset
	 */
	void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ByteBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, FloatBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, IntBuffer data);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ShortBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param format
	 * @param imageSize
	 * @param buffer_offset
	 */
	void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, int buffer_offset);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ByteBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, FloatBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, IntBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ShortBuffer data);

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param format
	 * @param imageSize
	 * @param buffer_offset
	 */
	void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, int buffer_offset);

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	void glGetCompressedTexImage(int target, int lod, ByteBuffer img);

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	void glGetCompressedTexImage(int target, int lod, IntBuffer img);

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	void glGetCompressedTexImage(int target, int lod, ShortBuffer img);

	/**
	 * @param target
	 * @param lod
	 * @param buffer_offset
	 */
	void glGetCompressedTexImage(int target, int lod, int buffer_offset);

	/**
	 * @param m
	 */
	void glLoadTransposeMatrix(FloatBuffer m);

	/**
	 * @param target
	 * @param s
	 */
	void glMultiTexCoord1f(int target, float s);

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	void glMultiTexCoord2f(int target, float s, float t);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	void glMultiTexCoord3f(int target, float s, float t, float r);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	void glMultiTexCoord4f(int target, float s, float t, float r, float q);

	/**
	 * @param m
	 */
	void glMultTransposeMatrix(FloatBuffer m);

	/**
	 * @param value
	 * @param invert
	 */
	void glSampleCoverage(float value, boolean invert);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	void glBlendColor(float red, float green, float blue, float alpha);

	/**
	 * @param mode
	 */
	void glBlendEquation(int mode);

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);


	/**
	 * @param coord
	 */
	void glFogCoordf(float coord);

	/**
	 * @param stride
	 * @param data
	 */
	void glFogCoordPointer(int stride, FloatBuffer data);

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glFogCoordPointer(int type, int stride, int buffer_offset);

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount);

	/**
	 * @param pname
	 * @param params
	 */
	void glPointParameter(int pname, FloatBuffer params);

	/**
	 * @param pname
	 * @param param
	 */
	void glPointParameterf(int pname, float param);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glSecondaryColor3b(byte red, byte green, byte blue);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glSecondaryColor3f(float red, float green, float blue);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glSecondaryColor3ub(byte red, byte green, byte blue);

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param data
	 */
	void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data);

	/**
	 * @param size
	 * @param stride
	 * @param data
	 */
	void glSecondaryColorPointer(int size, int stride, FloatBuffer data);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glSecondaryColorPointer(int size, int type, int stride, int buffer_offset);

	/**
	 * @param x
	 * @param y
	 */
	void glWindowPos2f(float x, float y);

	/**
	 * @param x
	 * @param y
	 */
	void glWindowPos2i(int x, int y);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glWindowPos3f(float x, float y, float z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glWindowPos3i(int x, int y, int z);

	/**
	 * @param target
	 * @param id
	 */
	void glBeginQuery(int target, int id);

	/**
	 * @param target
	 * @param buffer
	 */
	void glBindBuffer(int target, int buffer);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferData(int target, int size, ByteBuffer data, int usage);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferData(int target, int size, FloatBuffer data, int usage);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferData(int target, int size, IntBuffer data, int usage);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferData(int target, int size, ShortBuffer data, int usage);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubData(int target, int offset, ByteBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubData(int target, int offset, FloatBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubData(int target, int offset, IntBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubData(int target, int offset, ShortBuffer data);

	/**
	 * @param buffers
	 */
	void glDeleteBuffers(IntBuffer buffers);

	/**
	 * @param ids
	 */
	void glDeleteQueries(IntBuffer ids);

	/**
	 * @param target
	 */
	void glEndQuery(int target);

	/**
	 * @param buffers
	 */
	void glGenBuffers(IntBuffer buffers);

	/**
	 * @param ids
	 */
	void glGenQueries(IntBuffer ids);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetBufferParameter(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	ByteBuffer glGetBufferPointer(int target, int pname, int size);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubData(int target, int offset, ByteBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubData(int target, int offset, FloatBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubData(int target, int offset, IntBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubData(int target, int offset, ShortBuffer data);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetQuery(int target, int pname, IntBuffer params);

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	void glGetQueryObject(int id, int pname, IntBuffer params);

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	void glGetQueryObjectu(int id, int pname, IntBuffer params);

	/**
	 * @param buffer
	 *
	 * @return
	 */
	boolean glIsBuffer(int buffer);

	/**
	 * @param id
	 *
	 * @return
	 */
	boolean glIsQuery(int id);

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 *
	 * @return
	 */
	ByteBuffer glMapBuffer(int target, int access, int size, ByteBuffer oldBuffer);

	/**
	 * @param target
	 *
	 * @return
	 */
	boolean glUnmapBuffer(int target);

	/**
	 * @param target
	 * @param buffer
	 */
	void glBindBufferARB(int target, int buffer);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferDataARB(int target, int size, ByteBuffer data, int usage);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferDataARB(int target, int size, FloatBuffer data, int usage);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferDataARB(int target, int size, IntBuffer data, int usage);

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	void glBufferDataARB(int target, int size, ShortBuffer data, int usage);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubDataARB(int target, int offset, ByteBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubDataARB(int target, int offset, FloatBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubDataARB(int target, int offset, IntBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glBufferSubDataARB(int target, int offset, ShortBuffer data);

	/**
	 * @param buffers
	 */
	void glDeleteBuffersARB(IntBuffer buffers);

	/**
	 * @param buffers
	 */
	void glGenBuffersARB(IntBuffer buffers);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetBufferParameterARB(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	ByteBuffer glGetBufferPointerARB(int target, int pname, int size);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubDataARB(int target, int offset, ByteBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubDataARB(int target, int offset, FloatBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubDataARB(int target, int offset, IntBuffer data);

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	void glGetBufferSubDataARB(int target, int offset, ShortBuffer data);

	/**
	 * @param buffer
	 *
	 * @return
	 */
	boolean glIsBufferARB(int buffer);

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 *
	 * @return
	 */
	ByteBuffer glMapBufferARB(int target, int access, int size, ByteBuffer oldBuffer);

	/**
	 * @param target
	 *
	 * @return
	 */
	boolean glUnmapBufferARB(int target);

	/**
	 * @param target
	 * @param program
	 */
	void glBindProgramARB(int target, int program);

	/**
	 * @param programs
	 */
	void glDeleteProgramsARB(IntBuffer programs);

	/**
	 * @param programs
	 */
	void glGenProgramsARB(IntBuffer programs);

	/**
	 * @param target
	 * @param parameterName
	 * @param params
	 */
	void glGetProgramARB(int target, int parameterName, IntBuffer params);

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	void glGetProgramEnvParameterARB(int target, int index, FloatBuffer params);

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	void glGetProgramLocalParameterARB(int target, int index, FloatBuffer params);

	/**
	 * @param target
	 * @param parameterName
	 * @param paramString
	 */
	void glGetProgramStringARB(int target, int parameterName, ByteBuffer paramString);


	/**
	 * @param program
	 *
	 * @return
	 */
	boolean glIsProgramARB(int program);

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glProgramEnvParameter4fARB(int target, int index, float x, float y, float z, float w);


	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	void glProgramEnvParameterARB(int target, int index, FloatBuffer params);

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glProgramLocalParameter4fARB(int target, int index, float x, float y, float z, float w);


	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	void glProgramLocalParameterARB(int target, int index, FloatBuffer params);

	/**
	 * @param target
	 * @param format
	 * @param string
	 */
	void glProgramStringARB(int target, int format, ByteBuffer string);

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data);

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data);

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glColorSubTable(int target, int start, int count, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data);


	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data);

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glColorTable(int target, int internalFormat, int width, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glColorTableParameter(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glColorTableParameter(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer image);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer image);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer image);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glConvolutionParameter(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glConvolutionParameterf(int target, int pname, float params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glConvolutionParameteri(int target, int pname, int params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glConvolutionParameteriv(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param start
	 * @param x
	 * @param y
	 * @param width
	 */
	void glCopyColorSubTable(int target, int start, int x, int y, int width);

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	void glCopyColorTable(int target, int internalformat, int x, int y, int width);

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width);


	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height);


	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	void glGetColorTable(int target, int format, int type, ByteBuffer data);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	void glGetColorTable(int target, int format, int type, FloatBuffer data);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetColorTableParameter(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetColorTableParameter(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	void glGetConvolutionFilter(int target, int format, int type, IntBuffer image);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glGetConvolutionFilter(int target, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetConvolutionParameter(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetConvolutionParameter(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	void glGetHistogram(int target, boolean reset, int format, int type, int buffer_offset);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetHistogramParameter(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetHistogramParameter(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values);

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param buffer_offset
	 */
	void glGetMinmax(int target, boolean reset, int format, int types, int buffer_offset);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetMinmaxParameter(int target, int pname, FloatBuffer params);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetMinmaxParameter(int target, int pname, IntBuffer params);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param row
	 * @param column
	 * @param span
	 */
	void glGetSeparableFilter(int target, int format, int type, Buffer row, Buffer column, Buffer span);

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param row_offset
	 * @param column_offset
	 * @param span_offset
	 */
	void glGetSeparableFilter(int target, int format, int type, int row_offset, int column_offset, int span_offset);

	/**
	 * @param target
	 * @param width
	 * @param internalformat
	 * @param sink
	 */
	void glHistogram(int target, int width, int internalformat, boolean sink);

	/**
	 * @param target
	 * @param internalformat
	 * @param sink
	 */
	void glMinmax(int target, int internalformat, boolean sink);

	/**
	 * @param target
	 */
	void glResetHistogram(int target);

	/**
	 * @param target
	 */
	void glResetMinmax(int target);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param row
	 * @param column
	 */
	void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, Buffer column);

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param row_offset
	 * @param column_offset
	 */
	void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, int row_offset, int column_offset);

	/**
	 * @param index
	 */
	void glCurrentPaletteMatrixARB(int index);

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glMatrixIndexPointerARB(int size, int type, int stride, int buffer_offset);


	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer);

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer);

	/**
	 * @param pIndices
	 */
	void glMatrixIndexuARB(ByteBuffer pIndices);

	/**
	 * @param piIndices
	 */
	void glMatrixIndexuARB(IntBuffer piIndices);

	/**
	 * @param psIndices
	 */
	void glMatrixIndexuARB(ShortBuffer psIndices);

	/**
	 * @param value
	 * @param invert
	 */
	void glSampleCoverageARB(float value, boolean invert);

	/**
	 * @param texture
	 */
	void glActiveTextureARB(int texture);

	/**
	 * @param texture
	 */
	void glClientActiveTextureARB(int texture);

	/**
	 * @param target
	 * @param s
	 */
	void glMultiTexCoord1fARB(int target, float s);

	/**
	 * @param target
	 * @param s
	 */
	void glMultiTexCoord1iARB(int target, int s);

	/**
	 * @param target
	 * @param s
	 */
	void glMultiTexCoord1sARB(int target, short s);

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	void glMultiTexCoord2fARB(int target, float s, float t);

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	void glMultiTexCoord2iARB(int target, int s, int t);

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	void glMultiTexCoord2sARB(int target, short s, short t);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	void glMultiTexCoord3fARB(int target, float s, float t, float r);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	void glMultiTexCoord3iARB(int target, int s, int t, int r);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	void glMultiTexCoord3sARB(int target, short s, short t, short r);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	void glMultiTexCoord4fARB(int target, float s, float t, float r, float q);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	void glMultiTexCoord4iARB(int target, int s, int t, int r, int q);

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	void glMultiTexCoord4sARB(int target, short s, short t, short r, short q);

	/**
	 * @param target
	 * @param id
	 */
	void glBeginQueryARB(int target, int id);

	/**
	 * @param ids
	 */
	void glDeleteQueriesARB(IntBuffer ids);

	/**
	 * @param target
	 */
	void glEndQueryARB(int target);

	/**
	 * @param ids
	 */
	void glGenQueriesARB(IntBuffer ids);

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	void glGetQueryARB(int target, int pname, IntBuffer params);

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	void glGetQueryObjectiARB(int id, int pname, IntBuffer params);

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	void glGetQueryObjectuiARB(int id, int pname, IntBuffer params);

	/**
	 * @param id
	 *
	 * @return
	 */
	boolean glIsQueryARB(int id);

	/**
	 * @param pname
	 * @param pfParams
	 */
	void glPointParameterARB(int pname, FloatBuffer pfParams);

	/**
	 * @param pname
	 * @param param
	 */
	void glPointParameterfARB(int pname, float param);

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ByteBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, FloatBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, IntBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ShortBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ByteBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, FloatBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, IntBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param width
	 * @param height
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ShortBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ByteBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, FloatBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, IntBuffer pData);


	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param yoffset
	 * @param zoffset
	 * @param width
	 * @param height
	 * @param depth
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ShortBuffer pData);


	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg);

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	void glGetCompressedTexImageARB(int target, int lod, IntBuffer pImg);

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	void glGetCompressedTexImageARB(int target, int lod, ShortBuffer pImg);

	/**
	 * @param pfMtx
	 */
	void glLoadTransposeMatrixARB(FloatBuffer pfMtx);

	/**
	 * @param pfMtx
	 */
	void glMultTransposeMatrixfARB(FloatBuffer pfMtx);

	/**
	 * @param count
	 */
	void glVertexBlendARB(int count);

	/**
	 * @param pWeights
	 */
	void glWeightARB(ByteBuffer pWeights);

	/**
	 * @param pfWeights
	 */
	void glWeightARB(FloatBuffer pfWeights);

	/**
	 * @param piWeights
	 */
	void glWeightARB(IntBuffer piWeights);

	/**
	 * @param psWeights
	 */
	void glWeightARB(ShortBuffer psWeights);

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer);

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer);

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	void glWeightPointerARB(int size, boolean unsigned, int stride, ShortBuffer pPointer);

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	void glWeightPointerARB(int size, int stride, FloatBuffer pPointer);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glWeightPointerARB(int size, int type, int stride, int buffer_offset);

	/**
	 * @param pWeights
	 */
	void glWeightuARB(ByteBuffer pWeights);

	/**
	 * @param piWeights
	 */
	void glWeightuARB(IntBuffer piWeights);

	/**
	 * @param psWeights
	 */
	void glWeightuARB(ShortBuffer psWeights);

	/**
	 * @param programObj
	 * @param index
	 * @param name
	 */
	void glBindAttribLocationARB(int programObj, int index, ByteBuffer name);

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	void glGetActiveAttribARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name);


	/**
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	int glGetAttribLocationARB(int programObj, ByteBuffer name);

	/**
	 * @param x
	 * @param y
	 */
	void glWindowPos2fARB(float x, float y);

	/**
	 * @param x
	 * @param y
	 */
	void glWindowPos2iARB(int x, int y);

	/**
	 * @param x
	 * @param y
	 */
	void glWindowPos2sARB(short x, short y);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glWindowPos3fARB(float x, float y, float z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glWindowPos3iARB(int x, int y, int z);

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	void glWindowPos3sARB(short x, short y, short z);

	/**
	 * @param containerObj
	 * @param obj
	 */
	void glAttachObjectARB(int containerObj, int obj);

	/**
	 * @param shaderObj
	 */
	void glCompileShaderARB(int shaderObj);

	/**
	 * @return
	 */
	int glCreateProgramObjectARB();

	/**
	 * @param shaderType
	 *
	 * @return
	 */
	int glCreateShaderObjectARB(int shaderType);

	/**
	 * @param obj
	 */
	void glDeleteObjectARB(int obj);

	/**
	 * @param containerObj
	 * @param attachedObj
	 */
	void glDetachObjectARB(int containerObj, int attachedObj);

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	void glGetActiveUniformARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name);


	/**
	 * @param containerObj
	 * @param count
	 * @param obj
	 */
	void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj);

	/**
	 * @param pname
	 *
	 * @return
	 */
	int glGetHandleARB(int pname);

	/**
	 * @param obj
	 * @param length
	 * @param infoLog
	 */
	void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog);

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	void glGetObjectParameterARB(int obj, int pname, FloatBuffer params);

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	void glGetObjectParameterARB(int obj, int pname, IntBuffer params);

	/**
	 * @param obj
	 * @param length
	 * @param source
	 */
	void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source);

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	void glGetUniformARB(int programObj, int location, FloatBuffer params);

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	void glGetUniformARB(int programObj, int location, IntBuffer params);

	/**
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	int glGetUniformLocationARB(int programObj, ByteBuffer name);

	/**
	 * @param programObj
	 */
	void glLinkProgramARB(int programObj);

	/**
	 * @param shaderObj
	 * @param string
	 */
	void glShaderSourceARB(int shaderObj, ByteBuffer string);

	/**
	 * @param shaderObj
	 * @param strings
	 */
	void glShaderSourceARB(int shaderObj, ByteBuffer[] strings);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform1ARB(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform1ARB(int location, IntBuffer values);

	/**
	 * @param location
	 * @param v0
	 */
	void glUniform1fARB(int location, float v0);

	/**
	 * @param location
	 * @param v0
	 */
	void glUniform1iARB(int location, int v0);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform2ARB(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform2ARB(int location, IntBuffer values);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	void glUniform2fARB(int location, float v0, float v1);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	void glUniform2iARB(int location, int v0, int v1);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform3ARB(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform3ARB(int location, IntBuffer values);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	void glUniform3fARB(int location, float v0, float v1, float v2);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	void glUniform3iARB(int location, int v0, int v1, int v2);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform4ARB(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform4ARB(int location, IntBuffer values);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	void glUniform4fARB(int location, float v0, float v1, float v2, float v3);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	void glUniform4iARB(int location, int v0, int v1, int v2, int v3);

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices);

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices);

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices);

	/**
	 * @param programObj
	 */
	void glUseProgramObjectARB(int programObj);

	/**
	 * @param programObj
	 */
	void glValidateProgramARB(int programObj);

	/**
	 * @param index
	 */
	void glDisableVertexAttribArrayARB(int index);

	/**
	 * @param index
	 */
	void glEnableVertexAttribArrayARB(int index);

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	void glGetVertexAttribARB(int index, int pname, FloatBuffer params);

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	void glGetVertexAttribARB(int index, int pname, IntBuffer params);

	/**
	 * @param index
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	ByteBuffer glGetVertexAttribPointerARB(int index, int pname, int size);

	/**
	 * @param index
	 * @param x
	 */
	void glVertexAttrib1fARB(int index, float x);

	/**
	 * @param index
	 * @param x
	 */
	void glVertexAttrib1sARB(int index, short x);

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	void glVertexAttrib2fARB(int index, float x, float y);

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	void glVertexAttrib2sARB(int index, short x, short y);

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	void glVertexAttrib3fARB(int index, float x, float y, float z);

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	void glVertexAttrib3sARB(int index, short x, short y, short z);

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glVertexAttrib4fARB(int index, float x, float y, float z, float w);

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w);

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	void glVertexAttrib4sARB(int index, short x, short y, short z, short w);

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer);


	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer);


	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer);


	/**
	 * @param index
	 * @param size
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer);


	/**
	 * @param index
	 * @param size
	 * @param type
	 * @param normalized
	 * @param stride
	 * @param bufferOffset
	 */
	void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, int bufferOffset);


	/**
	 * @param modeRGB
	 * @param modeAlpha
	 */
	void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha);

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);


	/**
	 * @param first
	 * @param count
	 */
	void glLockArraysEXT(int first, int count);

	/**
	 *
	 */
	void glUnlockArraysEXT();

	/**
	 * @param zmin
	 * @param zmax
	 */
	void glDepthBoundsEXT(float zmin, float zmax);

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices);

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	void glDrawRangeElementsEXT(int mode, int start, int end, int count, int type, int buffer_offset);


	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices);

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices);

	/**
	 * @param coord
	 */
	void glFogCoordfEXT(float coord);

	/**
	 * @param stride
	 * @param data
	 */
	void glFogCoordPointerEXT(int stride, FloatBuffer data);

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glFogCoordPointerEXT(int type, int stride, int buffer_offset);

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount);

	/**
	 * @param pname
	 * @param pfParams
	 */
	void glPointParameterEXT(int pname, FloatBuffer pfParams);

	/**
	 * @param pname
	 * @param param
	 */
	void glPointParameterfEXT(int pname, float param);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glSecondaryColor3bEXT(byte red, byte green, byte blue);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glSecondaryColor3fEXT(float red, float green, float blue);

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	void glSecondaryColor3ubEXT(byte red, byte green, byte blue);

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	void glSecondaryColorPointerEXT(int size, boolean unsigned, int stride, ByteBuffer pPointer);


	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	void glSecondaryColorPointerEXT(int size, int stride, FloatBuffer pPointer);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glSecondaryColorPointerEXT(int size, int type, int stride, int buffer_offset);


	/**
	 * @param face
	 */
	void glActiveStencilFaceEXT(int face);

	/**
	 *
	 */
	void glBeginVertexShaderEXT();

	/**
	 * @param light
	 * @param value
	 *
	 * @return
	 */
	int glBindLightParameterEXT(int light, int value);

	/**
	 * @param face
	 * @param value
	 *
	 * @return
	 */
	int glBindMaterialParameterEXT(int face, int value);

	/**
	 * @param value
	 *
	 * @return
	 */
	int glBindParameterEXT(int value);

	/**
	 * @param unit
	 * @param coord
	 * @param value
	 *
	 * @return
	 */
	int glBindTexGenParameterEXT(int unit, int coord, int value);

	/**
	 * @param unit
	 * @param value
	 *
	 * @return
	 */
	int glBindTextureUnitParameterEXT(int unit, int value);

	/**
	 * @param id
	 */
	void glBindVertexShaderEXT(int id);

	/**
	 * @param id
	 */
	void glDeleteVertexShaderEXT(int id);

	/**
	 * @param id
	 */
	void glDisableVariantClientStateEXT(int id);

	/**
	 * @param id
	 */
	void glEnableVariantClientStateEXT(int id);

	/**
	 *
	 */
	void glEndVertexShaderEXT();

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	void glExtractComponentEXT(int res, int src, int num);

	/**
	 * @param dataType
	 * @param storageType
	 * @param range
	 * @param components
	 *
	 * @return
	 */
	int glGenSymbolsEXT(int dataType, int storageType, int range, int components);


	/**
	 * @param range
	 *
	 * @return
	 */
	int glGenVertexShadersEXT(int range);

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	void glGetInvariantBooleanEXT(int id, int value, ByteBuffer pbData);

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	void glGetInvariantFloatEXT(int id, int value, FloatBuffer pfData);

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	void glGetInvariantIntegerEXT(int id, int value, IntBuffer piData);

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	void glGetLocalConstantBooleanEXT(int id, int value, ByteBuffer pbData);

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pfData);

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	void glGetLocalConstantIntegerEXT(int id, int value, IntBuffer piData);

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	void glGetVariantBooleanEXT(int id, int value, ByteBuffer pbData);

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	void glGetVariantFloatEXT(int id, int value, FloatBuffer pfData);

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	void glGetVariantIntegerEXT(int id, int value, IntBuffer piData);

	/**
	 * @param id
	 * @param value
	 * @param size
	 *
	 * @return
	 */
	ByteBuffer glGetVariantPointerEXT(int id, int value, int size);

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	void glInsertComponentEXT(int res, int src, int num);

	/**
	 * @param id
	 * @param cap
	 *
	 * @return
	 */
	boolean glIsVariantEnabledEXT(int id, int cap);

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	void glSetInvariantEXT(int id, boolean unsigned, ByteBuffer pAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	void glSetInvariantEXT(int id, boolean unsigned, IntBuffer pAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr);

	/**
	 * @param id
	 * @param pAddr
	 */
	void glSetInvariantEXT(int id, FloatBuffer pAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	void glSetLocalConstantEXT(int id, boolean unsigned, ByteBuffer pAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	void glSetLocalConstantEXT(int id, boolean unsigned, IntBuffer pAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr);

	/**
	 * @param id
	 * @param pAddr
	 */
	void glSetLocalConstantEXT(int id, FloatBuffer pAddr);

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 */
	void glShaderOp1EXT(int op, int res, int arg1);

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 */
	void glShaderOp2EXT(int op, int res, int arg1, int arg2);

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	void glShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3);

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	void glSwizzleEXT(int res, int in, int outX, int outY, int outZ, int outW);

	/**
	 * @param id
	 * @param pAddr
	 */
	void glVariantEXT(int id, ByteBuffer pAddr);

	/**
	 * @param id
	 * @param pfAddr
	 */
	void glVariantEXT(int id, FloatBuffer pfAddr);

	/**
	 * @param id
	 * @param piAddr
	 */
	void glVariantEXT(int id, IntBuffer piAddr);

	/**
	 * @param id
	 * @param psAddr
	 */
	void glVariantEXT(int id, ShortBuffer psAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	void glVariantPointerEXT(int id, boolean unsigned, int stride, ByteBuffer pAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	void glVariantPointerEXT(int id, boolean unsigned, int stride, IntBuffer pAddr);

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr);

	/**
	 * @param id
	 * @param stride
	 * @param pAddr
	 */
	void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr);

	/**
	 * @param id
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glVariantPointerEXT(int id, int type, int stride, int buffer_offset);

	/**
	 * @param id
	 * @param pAddr
	 */
	void glVariantuEXT(int id, ByteBuffer pAddr);

	/**
	 * @param id
	 * @param piAddr
	 */
	void glVariantuEXT(int id, IntBuffer piAddr);

	/**
	 * @param id
	 * @param psAddr
	 */
	void glVariantuEXT(int id, ShortBuffer psAddr);

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	void glWriteMaskEXT(int res, int in, int outX, int outY, int outZ, int outW);

	/**
	 * @param weight
	 */
	void glVertexWeightfEXT(float weight);

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	void glVertexWeightPointerEXT(int size, int stride, FloatBuffer pPointer);

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	void glVertexWeightPointerEXT(int size, int type, int stride, int buffer_offset);

	// ----------------------------------------------------------
	// ----------------------[ OpenGL 2.0 ]----------------------
	// ----------------------------------------------------------

	/**
	 * @param shader
	 * @param string
	 */
	void glShaderSource(int shader, ByteBuffer string);

	/**
	 * @param shader
	 * @param strings
	 */
	void glShaderSource(int shader, ByteBuffer[] strings);

	/**
	 * @param type
	 *
	 * @return
	 */
	int glCreateShader(int type);

	/**
	 * @param shader
	 *
	 * @return
	 */
	boolean glIsShader(int shader);

	/**
	 * @param shader
	 */
	void glCompileShader(int shader);

	/**
	 * @param shader
	 */
	void glDeleteShader(int shader);

	/**
	 * @return
	 */
	int glCreateProgram();

	/**
	 * @param program
	 *
	 * @return
	 */
	boolean glIsProgram(int program);

	/**
	 * @param program
	 * @param shader
	 */
	void glAttachShader(int program, int shader);

	/**
	 * @param program
	 * @param shader
	 */
	void glDetachShader(int program, int shader);

	/**
	 * @param program
	 */
	void glLinkProgram(int program);

	/**
	 * @param program
	 */
	void glUseProgram(int program);

	/**
	 * @param program
	 */
	void glValidateProgram(int program);

	/**
	 * @param program
	 */
	void glDeleteProgram(int program);

	/**
	 * @param location
	 * @param v0
	 */
	void glUniform1f(int location, float v0);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	void glUniform2f(int location, float v0, float v1);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	void glUniform3f(int location, float v0, float v1, float v2);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	void glUniform4f(int location, float v0, float v1, float v2, float v3);

	/**
	 * @param location
	 * @param v0
	 */
	void glUniform1i(int location, int v0);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	void glUniform2i(int location, int v0, int v1);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	void glUniform3i(int location, int v0, int v1, int v2);

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	void glUniform4i(int location, int v0, int v1, int v2, int v3);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform1(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform2(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform3(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform4(int location, FloatBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform1(int location, IntBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform2(int location, IntBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform3(int location, IntBuffer values);

	/**
	 * @param location
	 * @param values
	 */
	void glUniform4(int location, IntBuffer values);

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices);

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices);

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices);

	/**
	 * @param shader
	 * @param pname
	 * @param params
	 */
	void glGetShader(int shader, int pname, FloatBuffer params);

	/**
	 * @param shader
	 * @param pname
	 * @param params
	 */
	void glGetShader(int shader, int pname, IntBuffer params);

	/**
	 * @param program
	 * @param pname
	 * @param params
	 */
	void glGetProgram(int program, int pname, FloatBuffer params);

	/**
	 * @param program
	 * @param pname
	 * @param params
	 */
	void glGetProgram(int program, int pname, IntBuffer params);

	/**
	 * @param shader
	 * @param length
	 * @param infoLog
	 */
	void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog);

	/**
	 * @param program
	 * @param length
	 * @param infoLog
	 */
	void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog);

	/**
	 * @param program
	 * @param count
	 * @param shaders
	 */
	void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders);

	/**
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	int glGetUniformLocation(int program, ByteBuffer name);

	/**
	 * @param program
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	void glGetActiveUniform(int program, int index,
	                        IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name);

	/**
	 * @param program
	 * @param location
	 * @param params
	 */
	void glGetUniform(int program, int location, FloatBuffer params);

	/**
	 * @param program
	 * @param location
	 * @param params
	 */
	void glGetUniform(int program, int location, IntBuffer params);

	/**
	 * @param shader
	 * @param length
	 * @param source
	 */
	void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source);

	/**
	 * @param program
	 * @param index
	 * @param name
	 */
	void glBindAttribLocation(int program, int index, ByteBuffer name);

	/**
	 * @param program
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	void glGetActiveAttrib(int program, int index,
	                       IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name);

	/**
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	int glGetAttribLocation(int program, ByteBuffer name);

	/**
	 * @param buffers
	 */
	void glDrawBuffers(IntBuffer buffers);

	/**
	 * @param face
	 * @param func
	 * @param ref
	 * @param mask
	 */
	void glStencilFuncSeparate(int face, int func, int ref, int mask);

	/**
	 * @param face
	 * @param sfail
	 * @param dpfail
	 * @param dppass
	 */
	void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass);

}