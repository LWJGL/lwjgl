/* 
 * Copyright (c) 2003 Shaven Puppy Ltd
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
 * * Neither the name of 'Shaven Puppy' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
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

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * $Id$
 * This is an interface describing an Object that can render using
 * OpenGL1.1, 1.2, 1.3, 1.4, 1.5, and all the EXT and ARB extensions
 * in the LWJGL library. Its main purpose is to stop object-oriented
 * zealots annoying us any more.
 * @author $Author$
 * @version $Revision$
 */
public interface IGL {
	/**
	 * @param op
	 * @param value
	 */
	public void glAccum(int op, float value);	

	/**
	 * @param func
	 * @param ref
	 */
	public void glAlphaFunc(int func, float ref);	

	/**
	 * @param i
	 */
	public void glArrayElement(int i);	

	/**
	 * @param mode
	 */
	public void glBegin(int mode);	

	/**
	 * @param target
	 * @param texture
	 */
	public void glBindTexture(int target, int texture);	

	/**
	 * @param width
	 * @param height
	 * @param xorig
	 * @param yorig
	 * @param xmove
	 * @param ymove
	 * @param bitmap
	 */
	public void glBitmap(int width, int height, float xorig,
			float yorig, float xmove, float ymove, ByteBuffer bitmap);	

	/**
	 * @param sfactor
	 * @param dfactor
	 */
	public void glBlendFunc(int sfactor, int dfactor);	

	/**
	 * @param list
	 */
	public void glCallList(int list);	

	/**
	 * @param lists
	 */
	public void glCallLists(ByteBuffer lists);	

	/**
	 * @param n
	 * @param lists
	 */
	public void glCallLists(int n, IntBuffer lists);	

	/**
	 * @param lists
	 */
	public void glCallLists(ShortBuffer lists);	

	/**
	 * @param mask
	 */
	public void glClear(int mask);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glClearAccum(float red, float green, float blue,
			float alpha);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glClearColor(float red, float green, float blue,
			float alpha);	

	/**
	 * @param depth
	 */
	public void glClearDepth(double depth);	

	/**
	 * @param c
	 */
	public void glClearIndex(float c);	

	/**
	 * @param s
	 */
	public void glClearStencil(int s);	

	/**
	 * @param plane
	 * @param equation
	 */
	public void glClipPlane(int plane, DoubleBuffer equation);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glColor3b(byte red, byte green, byte blue);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glColor3f(float red, float green, float blue);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glColor3ub(byte red, byte green, byte blue);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColor4b(byte red, byte green, byte blue, byte alpha);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColor4f(float red, float green, float blue, float alpha);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColor4ub(byte red, byte green, byte blue, byte alpha);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColorMask(boolean red, boolean green, boolean blue,
			boolean alpha);	

	/**
	 * @param face
	 * @param mode
	 */
	public void glColorMaterial(int face, int mode);	

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pointer
	 */
	public void glColorPointer(int size, boolean unsigned, int stride,
			ByteBuffer pointer);	

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glColorPointer(int size, int stride, FloatBuffer pointer);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glColorPointer(int size, int type, int stride,
			int buffer_offset);	

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param type
	 */
	public void glCopyPixels(int x, int y, int width, int height,
			int type);	

	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param x
	 * @param y
	 * @param width
	 * @param border
	 */
	public void glCopyTexImage1D(int target, int level,
			int internalFormat, int x, int y, int width, int border);
	

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
	public void glCopyTexImage2D(int target, int level,
			int internalFormat, int x, int y, int width, int height, int border);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyTexSubImage1D(int target, int level, int xoffset,
			int x, int y, int width);	

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
	public void glCopyTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int x, int y, int width, int height);
	

	/**
	 * @param mode
	 */
	public void glCullFace(int mode);	

	/**
	 * @param list
	 * @param range
	 */
	public void glDeleteLists(int list, int range);	

	/**
	 * @param textures
	 */
	public void glDeleteTextures(IntBuffer textures);	

	/**
	 * @param func
	 */
	public void glDepthFunc(int func);	

	/**
	 * @param flag
	 */
	public void glDepthMask(boolean flag);	

	/**
	 * @param zNear
	 * @param zFar
	 */
	public void glDepthRange(double zNear, double zFar);	

	/**
	 * @param cap
	 */
	public void glDisable(int cap);	

	/**
	 * @param cap
	 */
	public void glDisableClientState(int cap);	

	/**
	 * @param mode
	 * @param first
	 * @param count
	 */
	public void glDrawArrays(int mode, int first, int count);	

	/**
	 * @param mode
	 */
	public void glDrawBuffer(int mode);	

	/**
	 * @param mode
	 * @param indices
	 */
	public void glDrawElements(int mode, ByteBuffer indices);	

	/**
	 * @param mode
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public void glDrawElements(int mode, int count, int type,
			int buffer_offset);	

	/**
	 * @param mode
	 * @param indices
	 */
	public void glDrawElements(int mode, IntBuffer indices);	

	/**
	 * @param mode
	 * @param indices
	 */
	public void glDrawElements(int mode, ShortBuffer indices);	

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glDrawPixels(int width, int height, int format,
			int type, ByteBuffer pixels);	

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glDrawPixels(int width, int height, int format,
			int type, IntBuffer pixels);	

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glDrawPixels(int width, int height, int format,
			int type, ShortBuffer pixels);	

	/**
	 * @param flag
	 */
	public void glEdgeFlag(boolean flag);	

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glEdgeFlagPointer(int stride, ByteBuffer pointer);	

	/**
	 * @param stride
	 * @param buffer_offset
	 */
	public void glEdgeFlagPointer(int stride, int buffer_offset);	

	/**
	 * @param cap
	 */
	public void glEnable(int cap);	

	/**
	 * @param cap
	 */
	public void glEnableClientState(int cap);	

	/**
	 * 
	 */
	public void glEnd();	

	/**
	 * 
	 */
	public void glEndList();	

	/**
	 * @param u
	 */
	public void glEvalCoord1f(float u);	

	/**
	 * @param u
	 * @param v
	 */
	public void glEvalCoord2f(float u, float v);	

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 */
	public void glEvalMesh1(int mode, int i1, int i2);	

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 * @param j1
	 * @param j2
	 */
	public void glEvalMesh2(int mode, int i1, int i2, int j1, int j2);	

	/**
	 * @param i
	 */
	public void glEvalPoint1(int i);	

	/**
	 * @param i
	 * @param j
	 */
	public void glEvalPoint2(int i, int j);	

	/**
	 * @param type
	 * @param buffer
	 */
	public void glFeedbackBuffer(int type, FloatBuffer buffer);	

	/**
	 * 
	 */
	public void glFinish();	

	/**
	 * 
	 */
	public void glFlush();	

	/**
	 * @param pname
	 * @param params
	 */
	public void glFog(int pname, FloatBuffer params);	

	/**
	 * @param pname
	 * @param params
	 */
	public void glFog(int pname, IntBuffer params);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glFogf(int pname, float param);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glFogi(int pname, int param);	

	/**
	 * @param mode
	 */
	public void glFrontFace(int mode);	

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	public void glFrustum(double left, double right, double bottom,
			double top, double zNear, double zFar);	

	/**
	 * @param range
	 * @return
	 */
	public int glGenLists(int range);	

	/**
	 * @param textures
	 */
	public void glGenTextures(IntBuffer textures);	

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetBoolean(int pname, ByteBuffer params);	

	/**
	 * @param plane
	 * @param equation
	 */
	public void glGetClipPlane(int plane, DoubleBuffer equation);	

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetDouble(int pname, DoubleBuffer params);	

	/**
	 * @return
	 */
	public int glGetError();	

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetFloat(int pname, FloatBuffer params);	

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetInteger(int pname, IntBuffer params);	

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glGetLight(int light, int pname, FloatBuffer params);	

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glGetLight(int light, int pname, IntBuffer params);	

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	public void glGetMap(int target, int query, FloatBuffer v);	

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	public void glGetMap(int target, int query, IntBuffer v);	

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glGetMaterial(int face, int pname, FloatBuffer params);	

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glGetMaterial(int face, int pname, IntBuffer params);	

	/**
	 * @param map
	 * @param values
	 */
	public void glGetPixelMap(int map, FloatBuffer values);	

	/**
	 * @param map
	 * @param values
	 */
	public void glGetPixelMap(int map, IntBuffer values);	

	/**
	 * @param map
	 * @param values
	 */
	public void glGetPixelMap(int map, ShortBuffer values);	

	/**
	 * @param pname
	 * @param size
	 * @return
	 */
	public ByteBuffer glGetPointerv(int pname, int size);	

	/**
	 * @param mask
	 */
	public void glGetPolygonStipple(ByteBuffer mask);	

	/**
	 * @param name
	 * @return
	 */
	public String glGetString(int name);	

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexEnv(int coord, int pname, FloatBuffer params);	

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexEnv(int coord, int pname, IntBuffer params);	

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexGen(int coord, int pname, FloatBuffer params);	

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexGen(int coord, int pname, IntBuffer params);	

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glGetTexImage(int target, int level, int format,
			int type, ByteBuffer pixels);	

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glGetTexImage(int target, int level, int format,
			int type, IntBuffer pixels);	

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glGetTexImage(int target, int level, int format,
			int type, ShortBuffer pixels);	

	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public void glGetTexLevelParameter(int target, int level, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public void glGetTexLevelParameter(int target, int level, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetTexParameter(int target, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetTexParameter(int target, int pname, IntBuffer params);	

	/**
	 * @param target
	 * @param mode
	 */
	public void glHint(int target, int mode);	

	/**
	 * 
	 */
	public void glInitNames();	

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride,
			ByteBuffer pointer);	

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride,
			FloatBuffer pointer);	

	/**
	 * @param format
	 * @param stride
	 * @param buffer_offset
	 */
	public void glInterleavedArrays(int format, int stride,
			int buffer_offset);	

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride,
			IntBuffer pointer);	

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride,
			ShortBuffer pointer);	

	/**
	 * @param cap
	 * @return
	 */
	public boolean glIsEnabled(int cap);	

	/**
	 * @param list
	 * @return
	 */
	public boolean glIsList(int list);	

	/**
	 * @param texture
	 * @return
	 */
	public boolean glIsTexture(int texture);	

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glLight(int light, int pname, FloatBuffer params);	

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glLight(int light, int pname, IntBuffer params);	

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	public void glLightf(int light, int pname, float param);	

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	public void glLighti(int light, int pname, int param);	

	/**
	 * @param pname
	 * @param params
	 */
	public void glLightModel(int pname, FloatBuffer params);	

	/**
	 * @param pname
	 * @param params
	 */
	public void glLightModel(int pname, IntBuffer params);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glLightModelf(int pname, float param);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glLightModeli(int pname, int param);	

	/**
	 * @param factor
	 * @param pattern
	 */
	public void glLineStipple(int factor, short pattern);	

	/**
	 * @param width
	 */
	public void glLineWidth(float width);	

	/**
	 * @param base
	 */
	public void glListBase(int base);	

	/**
	 * 
	 */
	public void glLoadIdentity();	

	/**
	 * @param m
	 */
	public void glLoadMatrix(FloatBuffer m);	

	/**
	 * @param name
	 */
	public void glLoadName(int name);	

	/**
	 * @param opcode
	 */
	public void glLogicOp(int opcode);	

	/**
	 * @param target
	 * @param u1
	 * @param u2
	 * @param stride
	 * @param order
	 * @param points
	 */
	public void glMap1f(int target, float u1, float u2, int stride,
			int order, FloatBuffer points);	

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
	public void glMap2f(int target, float u1, float u2, int ustride,
			int uorder, float v1, float v2, int vstride, int vorder,
			FloatBuffer points);
	

	/**
	 * @param un
	 * @param u1
	 * @param u2
	 */
	public void glMapGrid1f(int un, float u1, float u2);	

	/**
	 * @param un
	 * @param u1
	 * @param u2
	 * @param vn
	 * @param v1
	 * @param v2
	 */
	public void glMapGrid2f(int un, float u1, float u2, int vn,
			float v1, float v2);	

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glMaterial(int face, int pname, FloatBuffer params);	

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glMaterial(int face, int pname, IntBuffer params);	

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	public void glMaterialf(int face, int pname, float param);	

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	public void glMateriali(int face, int pname, int param);	

	/**
	 * @param mode
	 */
	public void glMatrixMode(int mode);	

	/**
	 * @param m
	 */
	public void glMultMatrix(FloatBuffer m);	

	/**
	 * @param list
	 * @param mode
	 */
	public void glNewList(int list, int mode);	

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public void glNormal3b(byte nx, byte ny, byte nz);	

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public void glNormal3f(float nx, float ny, float nz);	

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public void glNormal3i(int nx, int ny, int nz);	

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glNormalPointer(int stride, ByteBuffer pointer);	

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glNormalPointer(int stride, FloatBuffer pointer);	

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glNormalPointer(int type, int stride, int buffer_offset);	

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glNormalPointer(int stride, IntBuffer pointer);	

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	public void glOrtho(double left, double right, double bottom,
			double top, double zNear, double zFar);	

	/**
	 * @param token
	 */
	public void glPassThrough(float token);	

	/**
	 * @param map
	 * @param values
	 */
	public void glPixelMap(int map, FloatBuffer values);	

	/**
	 * @param map
	 * @param values
	 */
	public void glPixelMap(int map, IntBuffer values);	

	/**
	 * @param map
	 * @param values
	 */
	public void glPixelMap(int map, ShortBuffer values);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelStoref(int pname, float param);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelStorei(int pname, int param);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelTransferf(int pname, float param);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelTransferi(int pname, int param);	

	/**
	 * @param xfactor
	 * @param yfactor
	 */
	public void glPixelZoom(float xfactor, float yfactor);	

	/**
	 * @param size
	 */
	public void glPointSize(float size);	

	/**
	 * @param face
	 * @param mode
	 */
	public void glPolygonMode(int face, int mode);	

	/**
	 * @param factor
	 * @param units
	 */
	public void glPolygonOffset(float factor, float units);	

	/**
	 * @param mask
	 */
	public void glPolygonStipple(ByteBuffer mask);	

	/**
	 * 
	 */
	public void glPopAttrib();	

	/**
	 * 
	 */
	public void glPopClientAttrib();	

	/**
	 * 
	 */
	public void glPopMatrix();	

	/**
	 * 
	 */
	public void glPopName();	

	/**
	 * @param mask
	 */
	public void glPushAttrib(int mask);	

	/**
	 * @param mask
	 */
	public void glPushClientAttrib(int mask);	

	/**
	 * 
	 */
	public void glPushMatrix();	

	/**
	 * @param name
	 */
	public void glPushName(int name);	

	/**
	 * @param x
	 * @param y
	 */
	public void glRasterPos2f(float x, float y);	

	/**
	 * @param x
	 * @param y
	 */
	public void glRasterPos2i(int x, int y);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glRasterPos3f(float x, float y, float z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glRasterPos3i(int x, int y, int z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glRasterPos4f(float x, float y, float z, float w);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glRasterPos4i(int x, int y, int z, int w);	

	/**
	 * @param mode
	 */
	public void glReadBuffer(int mode);	

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glReadPixels(int x, int y, int width, int height,
			int format, int type, ByteBuffer pixels);	

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glReadPixels(int x, int y, int width, int height,
			int format, int type, IntBuffer pixels);	

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glReadPixels(int x, int y, int width, int height,
			int format, int type, ShortBuffer pixels);	

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void glRectf(float x1, float y1, float x2, float y2);	

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void glRecti(int x1, int y1, int x2, int y2);	

	/**
	 * @param mode
	 * @return
	 */
	public int glRenderMode(int mode);	

	/**
	 * @param angle
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glRotatef(float angle, float x, float y, float z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glScalef(float x, float y, float z);	

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void glScissor(int x, int y, int width, int height);	

	/**
	 * @param buffer
	 */
	public void glSelectBuffer(IntBuffer buffer);	

	/**
	 * @param mode
	 */
	public void glShadeModel(int mode);	

	/**
	 * @param func
	 * @param ref
	 * @param mask
	 */
	public void glStencilFunc(int func, int ref, int mask);	

	/**
	 * @param mask
	 */
	public void glStencilMask(int mask);	

	/**
	 * @param fail
	 * @param zfail
	 * @param zpass
	 */
	public void glStencilOp(int fail, int zfail, int zpass);	

	/**
	 * @param s
	 */
	public void glTexCoord1f(float s);	

	/**
	 * @param s
	 * @param t
	 */
	public void glTexCoord2f(float s, float t);	

	/**
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glTexCoord3f(float s, float t, float r);	

	/**
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glTexCoord4f(float s, float t, float r, float q);	

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glTexCoordPointer(int size, int stride,
			FloatBuffer pointer);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glTexCoordPointer(int size, int type, int stride,
			int buffer_offset);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glTexEnv(int target, int pname, FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glTexEnv(int target, int pname, IntBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexEnvf(int target, int pname, float param);	

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexEnvi(int target, int pname, int param);	

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glTexGen(int coord, int pname, FloatBuffer params);	

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glTexGen(int coord, int pname, IntBuffer params);	

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	public void glTexGenf(int coord, int pname, float param);	

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	public void glTexGeni(int coord, int pname, int param);	

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
	public void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, ByteBuffer pixels);
	

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
	public void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, FloatBuffer pixels);
	

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
	public void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, IntBuffer pixels);
	

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
	public void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, ShortBuffer pixels);
	

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
	public void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			ByteBuffer pixels);
	

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
	public void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			FloatBuffer pixels);
	

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
	public void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			IntBuffer pixels);
	

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
	public void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			ShortBuffer pixels);
	

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameter(int target, int pname, FloatBuffer param);	

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameter(int target, int pname, IntBuffer param);	

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameterf(int target, int pname, float param);	

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameteri(int target, int pname, int param);	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int type, ByteBuffer pixels);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int type, IntBuffer pixels);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int type, ShortBuffer pixels);
	

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
	public void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			ByteBuffer pixels);
	

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
	public void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			IntBuffer pixels);
	

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
	public void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			ShortBuffer pixels);
	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glTranslatef(float x, float y, float z);	

	/**
	 * @param x
	 * @param y
	 */
	public void glVertex2f(float x, float y);	

	/**
	 * @param x
	 * @param y
	 */
	public void glVertex2i(int x, int y);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertex3f(float x, float y, float z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertex3i(int x, int y, int z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertex4f(float x, float y, float z, float w);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertex4i(int x, int y, int z, int w);	

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glVertexPointer(int size, int stride, FloatBuffer pointer);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glVertexPointer(int size, int type, int stride,
			int buffer_offset);	

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glVertexPointer(int size, int stride, IntBuffer pointer);	

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void glViewport(int x, int y, int width, int height);	

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
	public void glCopyTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int x, int y, int width, int height);
	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public void glDrawRangeElements(int mode, int start, int end,
			ByteBuffer indices);	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public void glDrawRangeElements(int mode, int start, int end,
			int count, int type, int buffer_offset);	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public void glDrawRangeElements(int mode, int start, int end,
			IntBuffer indices);	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public void glDrawRangeElements(int mode, int start, int end,
			ShortBuffer indices);	

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
	public void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			ByteBuffer pixels);
	

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
	public void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			FloatBuffer pixels);
	

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
	public void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			IntBuffer pixels);
	

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
	public void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			ShortBuffer pixels);
	

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
	public void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, ByteBuffer pixels);
	

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
	public void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, FloatBuffer pixels);
	

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
	public void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, IntBuffer pixels);
	

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
	public void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, ShortBuffer pixels);
	

	/**
	 * @param texture
	 */
	public void glActiveTexture(int texture);	

	/**
	 * @param texture
	 */
	public void glClientActiveTexture(int texture);	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			ByteBuffer data);
	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			FloatBuffer data);
	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			IntBuffer data);
	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			ShortBuffer data);
	

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
	public void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, ByteBuffer data);
	

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
	public void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, FloatBuffer data);
	

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
	public void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, IntBuffer data);
	

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
	public void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, ShortBuffer data);
	

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
	public void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, ByteBuffer data);
	

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
	public void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, FloatBuffer data);
	

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
	public void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, IntBuffer data);
	

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
	public void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, ShortBuffer data);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, ByteBuffer data);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, FloatBuffer data);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, IntBuffer data);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, ShortBuffer data);
	

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
	public void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, ByteBuffer data);
	

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
	public void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, FloatBuffer data);
	

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
	public void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, IntBuffer data);
	

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
	public void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, ShortBuffer data);
	

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
	public void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, ByteBuffer data);
	

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
	public void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, FloatBuffer data);
	

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
	public void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, IntBuffer data);
	

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
	public void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, ShortBuffer data);
	

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public void glGetCompressedTexImage(int target, int lod,
			ByteBuffer img);	

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public void glGetCompressedTexImage(int target, int lod,
			IntBuffer img);	

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public void glGetCompressedTexImage(int target, int lod,
			ShortBuffer img);	

	/**
	 * @param m
	 */
	public void glLoadTransposeMatrix(FloatBuffer m);	

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1f(int target, float s);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2f(int target, float s, float t);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3f(int target, float s, float t, float r);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4f(int target, float s, float t, float r,
			float q);	

	/**
	 * @param m
	 */
	public void glMultTransposeMatrix(FloatBuffer m);	

	/**
	 * @param value
	 * @param invert
	 */
	public void glSampleCoverage(float value, boolean invert);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glBlendColor(float red, float green, float blue,
			float alpha);	

	/**
	 * @param mode
	 */
	public void glBlendEquation(int mode);	

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	public void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha);
	

	/**
	 * @param coord
	 */
	public void glFogCoordf(float coord);	

	/**
	 * @param stride
	 * @param data
	 */
	public void glFogCoordPointer(int stride, FloatBuffer data);	

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glFogCoordPointer(int type, int stride, int buffer_offset);	

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	public void glMultiDrawArrays(int mode, IntBuffer piFirst,
			IntBuffer piCount);	

	/**
	 * @param pname
	 * @param params
	 */
	public void glPointParameter(int pname, FloatBuffer params);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glPointParameterf(int pname, float param);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3b(byte red, byte green, byte blue);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3f(float red, float green, float blue);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3ub(byte red, byte green, byte blue);	

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param data
	 */
	public void glSecondaryColorPointer(int size, boolean unsigned,
			int stride, ByteBuffer data);	

	/**
	 * @param size
	 * @param stride
	 * @param data
	 */
	public void glSecondaryColorPointer(int size, int stride,
			FloatBuffer data);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glSecondaryColorPointer(int size, int type, int stride,
			int buffer_offset);	

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2f(float x, float y);	

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2i(int x, int y);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3f(float x, float y, float z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3i(int x, int y, int z);	

	/**
	 * @param target
	 * @param id
	 */
	public void glBeginQuery(int target, int id);	

	/**
	 * @param target
	 * @param buffer
	 */
	public void glBindBuffer(int target, int buffer);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, int size, ByteBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, int size, FloatBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, int size, IntBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, int size, ShortBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, ByteBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, FloatBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, IntBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, ShortBuffer data);	

	/**
	 * @param buffers
	 */
	public void glDeleteBuffers(IntBuffer buffers);	

	/**
	 * @param ids
	 */
	public void glDeleteQueries(IntBuffer ids);	

	/**
	 * @param target
	 */
	public void glEndQuery(int target);	

	/**
	 * @param buffers
	 */
	public void glGenBuffers(IntBuffer buffers);	

	/**
	 * @param ids
	 */
	public void glGenQueries(IntBuffer ids);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetBufferParameter(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param size
	 * @return
	 */
	public ByteBuffer glGetBufferPointer(int target, int pname, int size);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset,
			ByteBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset,
			FloatBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset, IntBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset,
			ShortBuffer data);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetQuery(int target, int pname, IntBuffer params);	

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObject(int id, int pname, IntBuffer params);	

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObjectu(int id, int pname, IntBuffer params);	

	/**
	 * @param buffer
	 * @return
	 */
	public boolean glIsBuffer(int buffer);	

	/**
	 * @param id
	 * @return
	 */
	public boolean glIsQuery(int id);	

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 * @return
	 */
	public ByteBuffer glMapBuffer(int target, int access, int size,
			ByteBuffer oldBuffer);	

	/**
	 * @param target
	 * @return
	 */
	public boolean glUnmapBuffer(int target);	

	/**
	 * @param target
	 * @param buffer
	 */
	public void glBindBufferARB(int target, int buffer);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, int size, ByteBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, int size, FloatBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, int size, IntBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, int size, ShortBuffer data,
			int usage);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset,
			ByteBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset,
			FloatBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset, IntBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset,
			ShortBuffer data);	

	/**
	 * @param buffers
	 */
	public void glDeleteBuffersARB(IntBuffer buffers);	

	/**
	 * @param buffers
	 */
	public void glGenBuffersARB(IntBuffer buffers);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetBufferParameterARB(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param size
	 * @return
	 */
	public ByteBuffer glGetBufferPointerARB(int target, int pname,
			int size);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset,
			ByteBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset,
			FloatBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset,
			IntBuffer data);	

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset,
			ShortBuffer data);	

	/**
	 * @param buffer
	 * @return
	 */
	public boolean glIsBufferARB(int buffer);	

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 * @return
	 */
	public ByteBuffer glMapBufferARB(int target, int access, int size,
			ByteBuffer oldBuffer);	

	/**
	 * @param target
	 * @return
	 */
	public boolean glUnmapBufferARB(int target);	

	/**
	 * @param target
	 * @param program
	 */
	public void glBindProgramARB(int target, int program);	

	/**
	 * @param programs
	 */
	public void glDeleteProgramsARB(IntBuffer programs);	

	/**
	 * @param programs
	 */
	public void glGenProgramsARB(IntBuffer programs);	

	/**
	 * @param target
	 * @param parameterName
	 * @param params
	 */
	public void glGetProgramARB(int target, int parameterName,
			IntBuffer params);	

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glGetProgramEnvParameterARB(int target, int index,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glGetProgramLocalParameterARB(int target, int index,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param parameterName
	 * @param paramString
	 */
	public void glGetProgramStringARB(int target, int parameterName,
			ByteBuffer paramString);
	

	/**
	 * @param program
	 * @return
	 */
	public boolean glIsProgramARB(int program);	

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glProgramEnvParameter4fARB(int target, int index,
			float x, float y, float z, float w);
	

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glProgramEnvParameterARB(int target, int index,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glProgramLocalParameter4fARB(int target, int index,
			float x, float y, float z, float w);
	

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glProgramLocalParameterARB(int target, int index,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param format
	 * @param string
	 */
	public void glProgramStringARB(int target, int format,
			ByteBuffer string);	

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorSubTable(int target, int start, int count,
			int format, int type, ByteBuffer data);	

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorSubTable(int target, int start, int count,
			int format, int type, FloatBuffer data);	

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorTable(int target, int internalFormat, int width,
			int format, int type, ByteBuffer data);
	

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorTable(int target, int internalFormat, int width,
			int format, int type, FloatBuffer data);
	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glColorTableParameter(int target, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glColorTableParameter(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat,
			int width, int format, int type, ByteBuffer image);	
	

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat,
			int width, int format, int type, FloatBuffer image);
	

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat,
			int width, int format, int type, IntBuffer image);
	

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat,
			int width, int format, int type, ShortBuffer image);
	

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter2D(int target, int internalformat,
			int width, int height, int format, int type, ByteBuffer image);
	

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter2D(int target, int internalformat,
			int width, int height, int format, int type, IntBuffer image);
	

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter2D(int target, int internalformat,
			int width, int height, int format, int type, ShortBuffer image);
	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameter(int target, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameterf(int target, int pname,
			float params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameteri(int target, int pname, int params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameteriv(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param start
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyColorSubTable(int target, int start, int x, int y,
			int width);	

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyColorTable(int target, int internalformat, int x,
			int y, int width);	

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyConvolutionFilter1D(int target,
			int internalformat, int x, int y, int width);
	

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void glCopyConvolutionFilter2D(int target,
			int internalformat, int x, int y, int width, int height);
	

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glGetColorTable(int target, int format, int type,
			ByteBuffer data);	

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glGetColorTable(int target, int format, int type,
			FloatBuffer data);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetColorTableParameter(int target, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetColorTableParameter(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type,
			ByteBuffer image);	

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type,
			FloatBuffer image);	

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type,
			IntBuffer image);	

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type,
			ShortBuffer image);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetConvolutionParameter(int target, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetConvolutionParameter(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format,
			int type, ByteBuffer values);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format,
			int type, FloatBuffer values);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format,
			int type, IntBuffer values);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format,
			int type, ShortBuffer values);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetHistogramParameter(int target, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetHistogramParameter(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format,
			int types, ByteBuffer values);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format,
			int types, FloatBuffer values);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format,
			int types, IntBuffer values);	

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format,
			int types, ShortBuffer values);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetMinmaxParameter(int target, int pname,
			FloatBuffer params);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetMinmaxParameter(int target, int pname,
			IntBuffer params);	

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param row
	 * @param column
	 * @param span
	 */
	public void glGetSeparableFilter(int target, int format, int type,
			Buffer row, Buffer column, Buffer span);
	

	/**
	 * @param target
	 * @param width
	 * @param internalformat
	 * @param sink
	 */
	public void glHistogram(int target, int width, int internalformat,
			boolean sink);	

	/**
	 * @param target
	 * @param internalformat
	 * @param sink
	 */
	public void glMinmax(int target, int internalformat, boolean sink);	

	/**
	 * @param target
	 */
	public void glResetHistogram(int target);	

	/**
	 * @param target
	 */
	public void glResetMinmax(int target);	

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
	public void glSeparableFilter2D(int target, int internalformat,
			int width, int height, int format, int type, Buffer row,
			Buffer column);
	

	/**
	 * @param index
	 */
	public void glCurrentPaletteMatrixARB(int index);	

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glMatrixIndexPointerARB(int size, int stride,
			ByteBuffer pPointer);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glMatrixIndexPointerARB(int size, int type, int stride,
			int buffer_offset);
	

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glMatrixIndexPointerARB(int size, int stride,
			IntBuffer pPointer);	

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glMatrixIndexPointerARB(int size, int stride,
			ShortBuffer pPointer);	

	/**
	 * @param pIndices
	 */
	public void glMatrixIndexuARB(ByteBuffer pIndices);	

	/**
	 * @param piIndices
	 */
	public void glMatrixIndexuARB(IntBuffer piIndices);	

	/**
	 * @param psIndices
	 */
	public void glMatrixIndexuARB(ShortBuffer psIndices);	

	/**
	 * @param value
	 * @param invert
	 */
	public void glSampleCoverageARB(float value, boolean invert);	

	/**
	 * @param texture
	 */
	public void glActiveTextureARB(int texture);	

	/**
	 * @param texture
	 */
	public void glClientActiveTextureARB(int texture);	

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1fARB(int target, float s);	

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1iARB(int target, int s);	

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1sARB(int target, short s);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2fARB(int target, float s, float t);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2iARB(int target, int s, int t);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2sARB(int target, short s, short t);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3fARB(int target, float s, float t,
			float r);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3iARB(int target, int s, int t, int r);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3sARB(int target, short s, short t,
			short r);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4fARB(int target, float s, float t,
			float r, float q);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4iARB(int target, int s, int t, int r,
			int q);	

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4sARB(int target, short s, short t,
			short r, short q);	

	/**
	 * @param target
	 * @param id
	 */
	public void glBeginQueryARB(int target, int id);	

	/**
	 * @param ids
	 */
	public void glDeleteQueriesARB(IntBuffer ids);	

	/**
	 * @param target
	 */
	public void glEndQueryARB(int target);	

	/**
	 * @param ids
	 */
	public void glGenQueriesARB(IntBuffer ids);	

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetQueryARB(int target, int pname, IntBuffer params);	

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObjectiARB(int id, int pname, IntBuffer params);	

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObjectuiARB(int id, int pname, IntBuffer params);	

	/**
	 * @param id
	 * @return
	 */
	public boolean glIsQueryARB(int id);	

	/**
	 * @param pname
	 * @param pfParams
	 */
	public void glPointParameterARB(int pname, FloatBuffer pfParams);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glPointParameterfARB(int pname, float param);	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexImage1DARB(int target, int level,
			int internalformat, int width, int border, int imageSize,
			ByteBuffer pData);
	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexImage1DARB(int target, int level,
			int internalformat, int width, int border, int imageSize,
			FloatBuffer pData);
	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexImage1DARB(int target, int level,
			int internalformat, int width, int border, int imageSize,
			IntBuffer pData);
	

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexImage1DARB(int target, int level,
			int internalformat, int width, int border, int imageSize,
			ShortBuffer pData);
	

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
	public void glCompressedTexImage2DARB(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, ByteBuffer pData);
	

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
	public void glCompressedTexImage2DARB(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, FloatBuffer pData);
	

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
	public void glCompressedTexImage2DARB(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, IntBuffer pData);
	

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
	public void glCompressedTexImage2DARB(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, ShortBuffer pData);
	

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
	public void glCompressedTexImage3DARB(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, ByteBuffer pData);
	

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
	public void glCompressedTexImage3DARB(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, FloatBuffer pData);
	

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
	public void glCompressedTexImage3DARB(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, IntBuffer pData);
	

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
	public void glCompressedTexImage3DARB(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, ShortBuffer pData);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexSubImage1DARB(int target, int level,
			int xoffset, int width, int border, int imageSize, ByteBuffer pData);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexSubImage1DARB(int target, int level,
			int xoffset, int width, int border, int imageSize, FloatBuffer pData);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexSubImage1DARB(int target, int level,
			int xoffset, int width, int border, int imageSize, IntBuffer pData);
	

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public void glCompressedTexSubImage1DARB(int target, int level,
			int xoffset, int width, int border, int imageSize, ShortBuffer pData);
	

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
	public void glCompressedTexSubImage2DARB(int target, int level,
			int xoffset, int yoffset, int width, int height, int border,
			int imageSize, ByteBuffer pData);
	

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
	public void glCompressedTexSubImage2DARB(int target, int level,
			int xoffset, int yoffset, int width, int height, int border,
			int imageSize, FloatBuffer pData);
	

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
	public void glCompressedTexSubImage2DARB(int target, int level,
			int xoffset, int yoffset, int width, int height, int border,
			int imageSize, IntBuffer pData);
	

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
	public void glCompressedTexSubImage2DARB(int target, int level,
			int xoffset, int yoffset, int width, int height, int border,
			int imageSize, ShortBuffer pData);
	

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
	public void glCompressedTexSubImage3DARB(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int border, int imageSize, ByteBuffer pData);
	

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
	public void glCompressedTexSubImage3DARB(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int border, int imageSize, FloatBuffer pData);
	

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
	public void glCompressedTexSubImage3DARB(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int border, int imageSize, IntBuffer pData);
	

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
	public void glCompressedTexSubImage3DARB(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int border, int imageSize, ShortBuffer pData);
	

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public void glGetCompressedTexImageARB(int target, int lod,
			ByteBuffer pImg);	

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public void glGetCompressedTexImageARB(int target, int lod,
			IntBuffer pImg);	

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public void glGetCompressedTexImageARB(int target, int lod,
			ShortBuffer pImg);	

	/**
	 * @param pfMtx
	 */
	public void glLoadTransposeMatrixARB(FloatBuffer pfMtx);	

	/**
	 * @param pfMtx
	 */
	public void glMultTransposeMatrixfARB(FloatBuffer pfMtx);	

	/**
	 * @param count
	 */
	public void glVertexBlendARB(int count);	

	/**
	 * @param pWeights
	 */
	public void glWeightARB(ByteBuffer pWeights);	

	/**
	 * @param pfWeights
	 */
	public void glWeightARB(FloatBuffer pfWeights);	

	/**
	 * @param piWeights
	 */
	public void glWeightARB(IntBuffer piWeights);	

	/**
	 * @param psWeights
	 */
	public void glWeightARB(ShortBuffer psWeights);	

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, boolean unsigned,
			int stride, ByteBuffer pPointer);	

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, boolean unsigned,
			int stride, IntBuffer pPointer);	

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, boolean unsigned,
			int stride, ShortBuffer pPointer);	

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, int stride,
			FloatBuffer pPointer);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glWeightPointerARB(int size, int type, int stride,
			int buffer_offset);	

	/**
	 * @param pWeights
	 */
	public void glWeightuARB(ByteBuffer pWeights);	

	/**
	 * @param piWeights
	 */
	public void glWeightuARB(IntBuffer piWeights);	

	/**
	 * @param psWeights
	 */
	public void glWeightuARB(ShortBuffer psWeights);	

	/**
	 * @param programObj
	 * @param index
	 * @param name
	 */
	public void glBindAttribLocationARB(int programObj, int index,
			ByteBuffer name);	

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public void glGetActiveAttribARB(int programObj, int index,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name);	
	

	/**
	 * @param programObj
	 * @param name
	 * @return
	 */
	public int glGetAttribLocationARB(int programObj, ByteBuffer name);	

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2fARB(float x, float y);	

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2iARB(int x, int y);	

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2sARB(short x, short y);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3fARB(float x, float y, float z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3iARB(int x, int y, int z);	

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3sARB(short x, short y, short z);	

	/**
	 * @param containerObj
	 * @param obj
	 */
	public void glAttachObjectARB(int containerObj, int obj);	

	/**
	 * @param shaderObj
	 */
	public void glCompileShaderARB(int shaderObj);	

	/**
	 * @return
	 */
	public int glCreateProgramObjectARB();	

	/**
	 * @param shaderType
	 * @return
	 */
	public int glCreateShaderObjectARB(int shaderType);	

	/**
	 * @param obj
	 */
	public void glDeleteObjectARB(int obj);	

	/**
	 * @param containerObj
	 * @param attachedObj
	 */
	public void glDetachObjectARB(int containerObj, int attachedObj);	

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public void glGetActiveUniformARB(int programObj, int index,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name);
	

	/**
	 * @param containerObj
	 * @param count
	 * @param obj
	 */
	public void glGetAttachedObjectsARB(int containerObj,
			IntBuffer count, IntBuffer obj);	

	/**
	 * @param pname
	 * @return
	 */
	public int glGetHandleARB(int pname);	

	/**
	 * @param obj
	 * @param length
	 * @param infoLog
	 */
	public void glGetInfoLogARB(int obj, IntBuffer length,
			ByteBuffer infoLog);	

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	public void glGetObjectParameterARB(int obj, int pname,
			FloatBuffer params);	

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	public void glGetObjectParameterARB(int obj, int pname,
			IntBuffer params);	

	/**
	 * @param obj
	 * @param length
	 * @param source
	 */
	public void glGetShaderSourceARB(int obj, IntBuffer length,
			ByteBuffer source);	

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	public void glGetUniformARB(int programObj, int location,
			FloatBuffer params);	

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	public void glGetUniformARB(int programObj, int location,
			IntBuffer params);	

	/**
	 * @param programObj
	 * @param name
	 * @return
	 */
	public int glGetUniformLocationARB(int programObj, ByteBuffer name);	

	/**
	 * @param programObj
	 */
	public void glLinkProgramARB(int programObj);	

	/**
	 * @param shaderObj
	 * @param string
	 */
	public void glShaderSourceARB(int shaderObj, ByteBuffer string);	

	/**
	 * @param shaderObj
	 * @param strings
	 */
	public void glShaderSourceARB(int shaderObj, ByteBuffer[] strings);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform1ARB(int location, FloatBuffer values);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform1ARB(int location, IntBuffer values);	

	/**
	 * @param location
	 * @param v0
	 */
	public void glUniform1fARB(int location, float v0);	

	/**
	 * @param location
	 * @param v0
	 */
	public void glUniform1iARB(int location, int v0);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform2ARB(int location, FloatBuffer values);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform2ARB(int location, IntBuffer values);	

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public void glUniform2fARB(int location, float v0, float v1);	

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public void glUniform2iARB(int location, int v0, int v1);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform3ARB(int location, FloatBuffer values);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform3ARB(int location, IntBuffer values);	

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public void glUniform3fARB(int location, float v0, float v1, float v2);	

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public void glUniform3iARB(int location, int v0, int v1, int v2);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform4ARB(int location, FloatBuffer values);	

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform4ARB(int location, IntBuffer values);	

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public void glUniform4fARB(int location, float v0, float v1,
			float v2, float v3);	

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public void glUniform4iARB(int location, int v0, int v1, int v2,
			int v3);	

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix2ARB(int location, boolean transpose,
			FloatBuffer matrices);	

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix3ARB(int location, boolean transpose,
			FloatBuffer matrices);	

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix4ARB(int location, boolean transpose,
			FloatBuffer matrices);	

	/**
	 * @param programObj
	 */
	public void glUseProgramObjectARB(int programObj);	

	/**
	 * @param programObj
	 */
	public void glValidateProgramARB(int programObj);	

	/**
	 * @param index
	 */
	public void glDisableVertexAttribArrayARB(int index);	

	/**
	 * @param index
	 */
	public void glEnableVertexAttribArrayARB(int index);	

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	public void glGetVertexAttribARB(int index, int pname,
			FloatBuffer params);	

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	public void glGetVertexAttribARB(int index, int pname,
			IntBuffer params);	

	/**
	 * @param index
	 * @param pname
	 * @param size
	 * @return
	 */
	public ByteBuffer glGetVertexAttribPointerARB(int index, int pname,
			int size);	

	/**
	 * @param index
	 * @param x
	 */
	public void glVertexAttrib1fARB(int index, float x);	

	/**
	 * @param index
	 * @param x
	 */
	public void glVertexAttrib1sARB(int index, short x);	

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	public void glVertexAttrib2fARB(int index, float x, float y);	

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	public void glVertexAttrib2sARB(int index, short x, short y);	

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertexAttrib3fARB(int index, float x, float y, float z);	

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertexAttrib3sARB(int index, short x, short y, short z);	

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertexAttrib4fARB(int index, float x, float y,
			float z, float w);	

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertexAttrib4NubARB(int index, byte x, byte y, byte z,
			byte w);	

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertexAttrib4sARB(int index, short x, short y,
			short z, short w);	

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size,
			boolean unsigned, boolean normalized, int stride, ByteBuffer buffer);
	

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size,
			boolean unsigned, boolean normalized, int stride, IntBuffer buffer);
	

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size,
			boolean unsigned, boolean normalized, int stride, ShortBuffer buffer);
	

	/**
	 * @param index
	 * @param size
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size,
			boolean normalized, int stride, FloatBuffer buffer);
	

	/**
	 * @param index
	 * @param size
	 * @param type
	 * @param normalized
	 * @param stride
	 * @param bufferOffset
	 */
	public void glVertexAttribPointerARB(int index, int size, int type,
			boolean normalized, int stride, int bufferOffset);
	

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	public void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha);
	

	/**
	 * @param first
	 * @param count
	 */
	public void glLockArraysEXT(int first, int count);	

	/**
	 * 
	 */
	public void glUnlockArraysEXT();	

	/**
	 * @param zmin
	 * @param zmax
	 */
	public void glDepthBoundsEXT(float zmin, float zmax);	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end,
			ByteBuffer pIndices);	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end,
			int count, int type, int buffer_offset);
	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end,
			IntBuffer pIndices);	

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end,
			ShortBuffer pIndices);	

	/**
	 * @param coord
	 */
	public void glFogCoordfEXT(float coord);	

	/**
	 * @param stride
	 * @param data
	 */
	public void glFogCoordPointerEXT(int stride, FloatBuffer data);	

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glFogCoordPointerEXT(int type, int stride,
			int buffer_offset);	

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	public void glMultiDrawArraysEXT(int mode, IntBuffer piFirst,
			IntBuffer piCount);	

	/**
	 * @param pname
	 * @param pfParams
	 */
	public void glPointParameterEXT(int pname, FloatBuffer pfParams);	

	/**
	 * @param pname
	 * @param param
	 */
	public void glPointParameterfEXT(int pname, float param);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3bEXT(byte red, byte green, byte blue);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3fEXT(float red, float green, float blue);	

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3ubEXT(byte red, byte green, byte blue);	

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glSecondaryColorPointerEXT(int size, boolean unsigned,
			int stride, ByteBuffer pPointer);
	

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glSecondaryColorPointerEXT(int size, int stride,
			FloatBuffer pPointer);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glSecondaryColorPointerEXT(int size, int type,
			int stride, int buffer_offset);
	

	/**
	 * @param face
	 */
	public void glActiveStencilFaceEXT(int face);	

	/**
	 * 
	 */
	public void glBeginVertexShaderEXT();	

	/**
	 * @param light
	 * @param value
	 * @return
	 */
	public int glBindLightParameterEXT(int light, int value);	

	/**
	 * @param face
	 * @param value
	 * @return
	 */
	public int glBindMaterialParameterEXT(int face, int value);	

	/**
	 * @param value
	 * @return
	 */
	public int glBindParameterEXT(int value);	

	/**
	 * @param unit
	 * @param coord
	 * @param value
	 * @return
	 */
	public int glBindTexGenParameterEXT(int unit, int coord, int value);	

	/**
	 * @param unit
	 * @param value
	 * @return
	 */
	public int glBindTextureUnitParameterEXT(int unit, int value);	

	/**
	 * @param id
	 */
	public void glBindVertexShaderEXT(int id);	

	/**
	 * @param id
	 */
	public void glDeleteVertexShaderEXT(int id);	

	/**
	 * @param id
	 */
	public void glDisableVariantClientStateEXT(int id);	

	/**
	 * @param id
	 */
	public void glEnableVariantClientStateEXT(int id);	

	/**
	 * 
	 */
	public void glEndVertexShaderEXT();	

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	public void glExtractComponentEXT(int res, int src, int num);	

	/**
	 * @param dataType
	 * @param storageType
	 * @param range
	 * @param components
	 * @return
	 */
	public int glGenSymbolsEXT(int dataType, int storageType, int range,
			int components);
	

	/**
	 * @param range
	 * @return
	 */
	public int glGenVertexShadersEXT(int range);	

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public void glGetInvariantBooleanEXT(int id, int value,
			ByteBuffer pbData);	

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public void glGetInvariantFloatEXT(int id, int value,
			FloatBuffer pfData);	

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public void glGetInvariantIntegerEXT(int id, int value,
			IntBuffer piData);	

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public void glGetLocalConstantBooleanEXT(int id, int value,
			ByteBuffer pbData);	

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public void glGetLocalConstantFloatEXT(int id, int value,
			FloatBuffer pfData);	

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public void glGetLocalConstantIntegerEXT(int id, int value,
			IntBuffer piData);	

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public void glGetVariantBooleanEXT(int id, int value,
			ByteBuffer pbData);	

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public void glGetVariantFloatEXT(int id, int value,
			FloatBuffer pfData);	

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public void glGetVariantIntegerEXT(int id, int value,
			IntBuffer piData);	

	/**
	 * @param id
	 * @param value
	 * @param size
	 * @return
	 */
	public ByteBuffer glGetVariantPointerEXT(int id, int value, int size);	

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	public void glInsertComponentEXT(int res, int src, int num);	

	/**
	 * @param id
	 * @param cap
	 * @return
	 */
	public boolean glIsVariantEnabledEXT(int id, int cap);	

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, boolean unsigned,
			ByteBuffer pAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, boolean unsigned,
			IntBuffer pAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, boolean unsigned,
			ShortBuffer pAddr);	

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, FloatBuffer pAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, boolean unsigned,
			ByteBuffer pAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, boolean unsigned,
			IntBuffer pAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, boolean unsigned,
			ShortBuffer pAddr);	

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, FloatBuffer pAddr);	

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 */
	public void glShaderOp1EXT(int op, int res, int arg1);	

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 */
	public void glShaderOp2EXT(int op, int res, int arg1, int arg2);	

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public void glShaderOp3EXT(int op, int res, int arg1, int arg2,
			int arg3);	

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	public void glSwizzleEXT(int res, int in, int outX, int outY,
			int outZ, int outW);	

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glVariantEXT(int id, ByteBuffer pAddr);	

	/**
	 * @param id
	 * @param pfAddr
	 */
	public void glVariantEXT(int id, FloatBuffer pfAddr);	

	/**
	 * @param id
	 * @param piAddr
	 */
	public void glVariantEXT(int id, IntBuffer piAddr);	

	/**
	 * @param id
	 * @param psAddr
	 */
	public void glVariantEXT(int id, ShortBuffer psAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, boolean unsigned,
			int stride, ByteBuffer pAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, boolean unsigned,
			int stride, IntBuffer pAddr);	

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, boolean unsigned,
			int stride, ShortBuffer pAddr);	

	/**
	 * @param id
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr);	

	/**
	 * @param id
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glVariantPointerEXT(int id, int type, int stride,
			int buffer_offset);	

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glVariantuEXT(int id, ByteBuffer pAddr);	

	/**
	 * @param id
	 * @param piAddr
	 */
	public void glVariantuEXT(int id, IntBuffer piAddr);	

	/**
	 * @param id
	 * @param psAddr
	 */
	public void glVariantuEXT(int id, ShortBuffer psAddr);	

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	public void glWriteMaskEXT(int res, int in, int outX, int outY,
			int outZ, int outW);	

	/**
	 * @param weight
	 */
	public void glVertexWeightfEXT(float weight);	

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glVertexWeightPointerEXT(int size, int stride,
			FloatBuffer pPointer);	

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glVertexWeightPointerEXT(int size, int type, int stride,
			int buffer_offset);
	



}