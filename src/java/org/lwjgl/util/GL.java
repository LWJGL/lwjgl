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

import org.lwjgl.opengl.*;

import java.nio.*;

/**
 * $Id$
 * <p/>
 * An extensible GL class that contains all the GL11 through GL15 methods, and all the ARB and EXT extension methods.
 *
 * @author $Author$
 * @version $Revision$
 */
public class GL {

	/** C'tor */
	public GL() {
	}

	/**
	 * @param op
	 * @param value
	 */
	public static void glAccum(int op, float value) {
		GL11.glAccum(op, value);
	}

	/**
	 * @param func
	 * @param ref
	 */
	public static void glAlphaFunc(int func, float ref) {
		GL11.glAlphaFunc(func, ref);
	}

	/** @param i  */
	public static void glArrayElement(int i) {
		GL11.glArrayElement(i);
	}

	/** @param mode  */
	public static void glBegin(int mode) {
		GL11.glBegin(mode);
	}

	/**
	 * @param target
	 * @param texture
	 */
	public static void glBindTexture(int target, int texture) {
		GL11.glBindTexture(target, texture);
	}

	/**
	 * @param width
	 * @param height
	 * @param xorig
	 * @param yorig
	 * @param xmove
	 * @param ymove
	 * @param bitmap
	 */
	public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap) {
		GL11.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap);
	}

	/**
	 * @param width
	 * @param height
	 * @param xorig
	 * @param yorig
	 * @param xmove
	 * @param ymove
	 * @param buffer_offect
	 */
	public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, int buffer_offect) {
		GL11.glBitmap(width, height, xorig, yorig, xmove, ymove, buffer_offect);
	}

	/**
	 * @param sfactor
	 * @param dfactor
	 */
	public static void glBlendFunc(int sfactor, int dfactor) {
		GL11.glBlendFunc(sfactor, dfactor);
	}

	/** @param list  */
	public static void glCallList(int list) {
		GL11.glCallList(list);
	}

	/** @param lists  */
	public static void glCallLists(ByteBuffer lists) {
		GL11.glCallLists(lists);
	}

	/** @param lists  */
	public static void glCallLists(ShortBuffer lists) {
		GL11.glCallLists(lists);
	}

	/** @param lists  */
	public static void glCallLists(IntBuffer lists) {
		GL11.glCallLists(lists);
	}

	/** @param mask  */
	public static void glClear(int mask) {
		GL11.glClear(mask);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glClearAccum(float red, float green, float blue, float alpha) {
		GL11.glClearAccum(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glClearColor(float red, float green, float blue, float alpha) {
		GL11.glClearColor(red, green, blue, alpha);
	}

	/** @param depth  */
	public static void glClearDepth(double depth) {
		GL11.glClearDepth(depth);
	}

	/** @param c  */
	public static void glClearIndex(float c) {
		GL11.glClearIndex(c);
	}

	/** @param s  */
	public static void glClearStencil(int s) {
		GL11.glClearStencil(s);
	}

	/**
	 * @param plane
	 * @param equation
	 */
	public static void glClipPlane(int plane, DoubleBuffer equation) {
		GL11.glClipPlane(plane, equation);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glColor3b(byte red, byte green, byte blue) {
		GL11.glColor3b(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glColor3f(float red, float green, float blue) {
		GL11.glColor3f(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glColor3ub(byte red, byte green, byte blue) {
		GL11.glColor3ub(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glColor4b(byte red, byte green, byte blue, byte alpha) {
		GL11.glColor4b(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glColor4f(float red, float green, float blue, float alpha) {
		GL11.glColor4f(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glColor4ub(byte red, byte green, byte blue, byte alpha) {
		GL11.glColor4ub(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		GL11.glColorMask(red, green, blue, alpha);
	}

	/**
	 * @param face
	 * @param mode
	 */
	public static void glColorMaterial(int face, int mode) {
		GL11.glColorMaterial(face, mode);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pointer
	 */
	public static void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer) {
		GL11.glColorPointer(size, unsigned, stride, pointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public static void glColorPointer(int size, int stride, FloatBuffer pointer) {
		GL11.glColorPointer(size, stride, pointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glColorPointer(int size, int type, int stride, int buffer_offset) {
		GL11.glColorPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param type
	 */
	public static void glCopyPixels(int x, int y, int width, int height, int type) {
		GL11.glCopyPixels(x, y, width, height, type);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalFormat
	 * @param x
	 * @param y
	 * @param width
	 * @param border
	 */
	public static void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border) {
		GL11.glCopyTexImage1D(target, level, internalFormat, x, y, width, border);
	}

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
	public static void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border) {
		GL11.glCopyTexImage2D(target, level, internalFormat, x, y, width, height, border);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width) {
		GL11.glCopyTexSubImage1D(target, level, xoffset, x, y, width);
	}

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
	public static void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
		GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
	}

	/** @param mode  */
	public static void glCullFace(int mode) {
		GL11.glCullFace(mode);
	}

	/**
	 * @param list
	 * @param range
	 */
	public static void glDeleteLists(int list, int range) {
		GL11.glDeleteLists(list, range);
	}

	/** @param textures  */
	public static void glDeleteTextures(IntBuffer textures) {
		GL11.glDeleteTextures(textures);
	}

	/** @param func  */
	public static void glDepthFunc(int func) {
		GL11.glDepthFunc(func);
	}

	/** @param flag  */
	public static void glDepthMask(boolean flag) {
		GL11.glDepthMask(flag);
	}

	/**
	 * @param zNear
	 * @param zFar
	 */
	public static void glDepthRange(double zNear, double zFar) {
		GL11.glDepthRange(zNear, zFar);
	}

	/** @param cap  */
	public static void glDisable(int cap) {
		GL11.glDisable(cap);
	}

	/** @param cap  */
	public static void glDisableClientState(int cap) {
		GL11.glDisableClientState(cap);
	}

	/**
	 * @param mode
	 * @param first
	 * @param count
	 */
	public static void glDrawArrays(int mode, int first, int count) {
		GL11.glDrawArrays(mode, first, count);
	}

	/** @param mode  */
	public static void glDrawBuffer(int mode) {
		GL11.glDrawBuffer(mode);
	}

	/**
	 * @param mode
	 * @param indices
	 */
	public static void glDrawElements(int mode, ByteBuffer indices) {
		GL11.glDrawElements(mode, indices);
	}

	/**
	 * @param mode
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public static void glDrawElements(int mode, int count, int type, int buffer_offset) {
		GL11.glDrawElements(mode, count, type, buffer_offset);
	}

	/**
	 * @param mode
	 * @param indices
	 */
	public static void glDrawElements(int mode, IntBuffer indices) {
		GL11.glDrawElements(mode, indices);
	}

	/**
	 * @param mode
	 * @param indices
	 */
	public static void glDrawElements(int mode, ShortBuffer indices) {
		GL11.glDrawElements(mode, indices);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glDrawPixels(int width, int height, int format, int type, int buffer_offset) {
		GL11.glDrawPixels(width, height, format, type, buffer_offset);
	}

	/** @param flag  */
	public static void glEdgeFlag(boolean flag) {
		GL11.glEdgeFlag(flag);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public static void glEdgeFlagPointer(int stride, ByteBuffer pointer) {
		GL11.glEdgeFlagPointer(stride, pointer);
	}

	/**
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glEdgeFlagPointer(int stride, int buffer_offset) {
		GL11.glEdgeFlagPointer(stride, buffer_offset);
	}

	/** @param cap  */
	public static void glEnable(int cap) {
		GL11.glEnable(cap);
	}

	/** @param cap  */
	public static void glEnableClientState(int cap) {
		GL11.glEnableClientState(cap);
	}

	/**
	 *
	 */
	public static void glEnd() {
		GL11.glEnd();
	}

	/**
	 *
	 */
	public static void glEndList() {
		GL11.glEndList();
	}

	/** @param u  */
	public static void glEvalCoord1f(float u) {
		GL11.glEvalCoord1f(u);
	}

	/**
	 * @param u
	 * @param v
	 */
	public static void glEvalCoord2f(float u, float v) {
		GL11.glEvalCoord2f(u, v);
	}

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 */
	public static void glEvalMesh1(int mode, int i1, int i2) {
		GL11.glEvalMesh1(mode, i1, i2);
	}

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 * @param j1
	 * @param j2
	 */
	public static void glEvalMesh2(int mode, int i1, int i2, int j1, int j2) {
		GL11.glEvalMesh2(mode, i1, i2, j1, j2);
	}

	/** @param i  */
	public static void glEvalPoint1(int i) {
		GL11.glEvalPoint1(i);
	}

	/**
	 * @param i
	 * @param j
	 */
	public static void glEvalPoint2(int i, int j) {
		GL11.glEvalPoint2(i, j);
	}

	/**
	 * @param type
	 * @param buffer
	 */
	public static void glFeedbackBuffer(int type, FloatBuffer buffer) {
		GL11.glFeedbackBuffer(type, buffer);
	}

	/**
	 *
	 */
	public static void glFinish() {
		GL11.glFinish();
	}

	/**
	 *
	 */
	public static void glFlush() {
		GL11.glFlush();
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glFog(int pname, FloatBuffer params) {
		GL11.glFog(pname, params);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glFog(int pname, IntBuffer params) {
		GL11.glFog(pname, params);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glFogf(int pname, float param) {
		GL11.glFogf(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glFogi(int pname, int param) {
		GL11.glFogi(pname, param);
	}

	/** @param mode  */
	public static void glFrontFace(int mode) {
		GL11.glFrontFace(mode);
	}

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	public static void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL11.glFrustum(left, right, bottom, top, zNear, zFar);
	}

	/**
	 * @param range
	 *
	 * @return
	 */
	public static int glGenLists(int range) {
		return GL11.glGenLists(range);
	}

	/** @param textures  */
	public static void glGenTextures(IntBuffer textures) {
		GL11.glGenTextures(textures);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glGetBoolean(int pname, ByteBuffer params) {
		GL11.glGetBoolean(pname, params);
	}

	/**
	 * @param plane
	 * @param equation
	 */
	public static void glGetClipPlane(int plane, DoubleBuffer equation) {
		GL11.glGetClipPlane(plane, equation);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glGetDouble(int pname, DoubleBuffer params) {
		GL11.glGetDouble(pname, params);
	}

	/** @return  */
	public static int glGetError() {
		return GL11.glGetError();
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glGetFloat(int pname, FloatBuffer params) {
		GL11.glGetFloat(pname, params);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glGetInteger(int pname, IntBuffer params) {
		GL11.glGetInteger(pname, params);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public static void glGetLight(int light, int pname, FloatBuffer params) {
		GL11.glGetLight(light, pname, params);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public static void glGetLight(int light, int pname, IntBuffer params) {
		GL11.glGetLight(light, pname, params);
	}

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	public static void glGetMap(int target, int query, FloatBuffer v) {
		GL11.glGetMap(target, query, v);
	}

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	public static void glGetMap(int target, int query, IntBuffer v) {
		GL11.glGetMap(target, query, v);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public static void glGetMaterial(int face, int pname, FloatBuffer params) {
		GL11.glGetMaterial(face, pname, params);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public static void glGetMaterial(int face, int pname, IntBuffer params) {
		GL11.glGetMaterial(face, pname, params);
	}

	/**
	 * @param map
	 * @param values
	 */
	public static void glGetPixelMap(int map, FloatBuffer values) {
		GL11.glGetPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param buffer_offset
	 */
	public static void glGetPixelMapfv(int map, int buffer_offset) {
		GL11.glGetPixelMapfv(map, buffer_offset);
	}

	/**
	 * @param map
	 * @param values
	 */
	public static void glGetPixelMap(int map, IntBuffer values) {
		GL11.glGetPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param buffer_offset
	 */
	public static void glGetPixelMapuiv(int map, int buffer_offset) {
		GL11.glGetPixelMapuiv(map, buffer_offset);
	}

	/**
	 * @param map
	 * @param values
	 */
	public static void glGetPixelMap(int map, ShortBuffer values) {
		GL11.glGetPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param buffer_offset
	 */
	public static void glGetPixelMapusv(int map, int buffer_offset) {
		GL11.glGetPixelMapusv(map, buffer_offset);
	}

	/**
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public static ByteBuffer glGetPointerv(int pname, int size) {
		return GL11.glGetPointerv(pname, size);
	}

	/** @param mask  */
	public static void glGetPolygonStipple(ByteBuffer mask) {
		GL11.glGetPolygonStipple(mask);
	}

	/**
	 * @param buffer_offset
	 */
	public static void glGetPolygonStipple(int buffer_offset) {
		GL11.glGetPolygonStipple(buffer_offset);
	}

	/**
	 * @param name
	 *
	 * @return
	 */
	public static String glGetString(int name) {
		return GL11.glGetString(name);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public static void glGetTexEnv(int coord, int pname, FloatBuffer params) {
		GL11.glGetTexEnv(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public static void glGetTexEnv(int coord, int pname, IntBuffer params) {
		GL11.glGetTexEnv(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public static void glGetTexGen(int coord, int pname, FloatBuffer params) {
		GL11.glGetTexGen(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public static void glGetTexGen(int coord, int pname, IntBuffer params) {
		GL11.glGetTexGen(coord, pname, params);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glGetTexImage(int target, int level, int format, int type, int buffer_offset) {
		GL11.glGetTexImage(target, level, format, type, buffer_offset);
	}
	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public static void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params) {
		GL11.glGetTexLevelParameter(target, level, pname, params);
	}

	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public static void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params) {
		GL11.glGetTexLevelParameter(target, level, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetTexParameter(int target, int pname, FloatBuffer params) {
		GL11.glGetTexParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetTexParameter(int target, int pname, IntBuffer params) {
		GL11.glGetTexParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param mode
	 */
	public static void glHint(int target, int mode) {
		GL11.glHint(target, mode);
	}

	/**
	 *
	 */
	public static void glInitNames() {
		GL11.glInitNames();
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public static void glInterleavedArrays(int format, int stride, ByteBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public static void glInterleavedArrays(int format, int stride, FloatBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param format
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glInterleavedArrays(int format, int stride, int buffer_offset) {
		GL11.glInterleavedArrays(format, stride, buffer_offset);
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public static void glInterleavedArrays(int format, int stride, IntBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public static void glInterleavedArrays(int format, int stride, ShortBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param cap
	 *
	 * @return
	 */
	public static boolean glIsEnabled(int cap) {
		return GL11.glIsEnabled(cap);
	}

	/**
	 * @param list
	 *
	 * @return
	 */
	public static boolean glIsList(int list) {
		return GL11.glIsList(list);
	}

	/**
	 * @param texture
	 *
	 * @return
	 */
	public static boolean glIsTexture(int texture) {
		return GL11.glIsTexture(texture);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public static void glLight(int light, int pname, FloatBuffer params) {
		GL11.glLight(light, pname, params);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public static void glLight(int light, int pname, IntBuffer params) {
		GL11.glLight(light, pname, params);
	}

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	public static void glLightf(int light, int pname, float param) {
		GL11.glLightf(light, pname, param);
	}

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	public static void glLighti(int light, int pname, int param) {
		GL11.glLighti(light, pname, param);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glLightModel(int pname, FloatBuffer params) {
		GL11.glLightModel(pname, params);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glLightModel(int pname, IntBuffer params) {
		GL11.glLightModel(pname, params);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glLightModelf(int pname, float param) {
		GL11.glLightModelf(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glLightModeli(int pname, int param) {
		GL11.glLightModeli(pname, param);
	}

	/**
	 * @param factor
	 * @param pattern
	 */
	public static void glLineStipple(int factor, short pattern) {
		GL11.glLineStipple(factor, pattern);
	}

	/** @param width  */
	public static void glLineWidth(float width) {
		GL11.glLineWidth(width);
	}

	/** @param base  */
	public static void glListBase(int base) {
		GL11.glListBase(base);
	}

	/**
	 *
	 */
	public static void glLoadIdentity() {
		GL11.glLoadIdentity();
	}

	/** @param m  */
	public static void glLoadMatrix(FloatBuffer m) {
		GL11.glLoadMatrix(m);
	}

	/** @param name  */
	public static void glLoadName(int name) {
		GL11.glLoadName(name);
	}

	/** @param opcode  */
	public static void glLogicOp(int opcode) {
		GL11.glLogicOp(opcode);
	}

	/**
	 * @param target
	 * @param u1
	 * @param u2
	 * @param stride
	 * @param order
	 * @param points
	 */
	public static void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) {
		GL11.glMap1f(target, u1, u2, stride, order, points);
	}

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
	public static void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
		GL11.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
	}

	/**
	 * @param un
	 * @param u1
	 * @param u2
	 */
	public static void glMapGrid1f(int un, float u1, float u2) {
		GL11.glMapGrid1f(un, u1, u2);
	}

	/**
	 * @param un
	 * @param u1
	 * @param u2
	 * @param vn
	 * @param v1
	 * @param v2
	 */
	public static void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2) {
		GL11.glMapGrid2f(un, u1, u2, vn, v1, v2);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public static void glMaterial(int face, int pname, FloatBuffer params) {
		GL11.glMaterial(face, pname, params);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public static void glMaterial(int face, int pname, IntBuffer params) {
		GL11.glMaterial(face, pname, params);
	}

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	public static void glMaterialf(int face, int pname, float param) {
		GL11.glMaterialf(face, pname, param);
	}

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	public static void glMateriali(int face, int pname, int param) {
		GL11.glMateriali(face, pname, param);
	}

	/** @param mode  */
	public static void glMatrixMode(int mode) {
		GL11.glMatrixMode(mode);
	}

	/** @param m  */
	public static void glMultMatrix(FloatBuffer m) {
		GL11.glMultMatrix(m);
	}

	/**
	 * @param list
	 * @param mode
	 */
	public static void glNewList(int list, int mode) {
		GL11.glNewList(list, mode);
	}

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public static void glNormal3b(byte nx, byte ny, byte nz) {
		GL11.glNormal3b(nx, ny, nz);
	}

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public static void glNormal3f(float nx, float ny, float nz) {
		GL11.glNormal3f(nx, ny, nz);
	}

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public static void glNormal3i(int nx, int ny, int nz) {
		GL11.glNormal3i(nx, ny, nz);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public static void glNormalPointer(int stride, ByteBuffer pointer) {
		GL11.glNormalPointer(stride, pointer);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public static void glNormalPointer(int stride, FloatBuffer pointer) {
		GL11.glNormalPointer(stride, pointer);
	}

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glNormalPointer(int type, int stride, int buffer_offset) {
		GL11.glNormalPointer(type, stride, buffer_offset);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public static void glNormalPointer(int stride, IntBuffer pointer) {
		GL11.glNormalPointer(stride, pointer);
	}

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	public static void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL11.glOrtho(left, right, bottom, top, zNear, zFar);
	}

	/** @param token  */
	public static void glPassThrough(float token) {
		GL11.glPassThrough(token);
	}

	/**
	 * @param map
	 * @param values
	 */
	public static void glPixelMap(int map, FloatBuffer values) {
		GL11.glPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	public static void glPixelMapfv(int map, int mapsize, int buffer_offset) {
		GL11.glPixelMapfv(map, mapsize, buffer_offset);
	}

	/**
	 * @param map
	 * @param values
	 */
	public static void glPixelMap(int map, IntBuffer values) {
		GL11.glPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	public static void glPixelMapuiv(int map, int mapsize, int buffer_offset) {
		GL11.glPixelMapuiv(map, mapsize, buffer_offset);
	}

	/**
	 * @param map
	 * @param values
	 */
	public static void glPixelMap(int map, ShortBuffer values) {
		GL11.glPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	public static void glPixelMapusv(int map, int mapsize, int buffer_offset) {
		GL11.glPixelMapusv(map, mapsize, buffer_offset);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glPixelStoref(int pname, float param) {
		GL11.glPixelStoref(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glPixelStorei(int pname, int param) {
		GL11.glPixelStorei(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glPixelTransferf(int pname, float param) {
		GL11.glPixelTransferf(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glPixelTransferi(int pname, int param) {
		GL11.glPixelTransferi(pname, param);
	}

	/**
	 * @param xfactor
	 * @param yfactor
	 */
	public static void glPixelZoom(float xfactor, float yfactor) {
		GL11.glPixelZoom(xfactor, yfactor);
	}

	/** @param size  */
	public static void glPointSize(float size) {
		GL11.glPointSize(size);
	}

	/**
	 * @param face
	 * @param mode
	 */
	public static void glPolygonMode(int face, int mode) {
		GL11.glPolygonMode(face, mode);
	}

	/**
	 * @param factor
	 * @param units
	 */
	public static void glPolygonOffset(float factor, float units) {
		GL11.glPolygonOffset(factor, units);
	}

	/** @param mask  */
	public static void glPolygonStipple(ByteBuffer mask) {
		GL11.glPolygonStipple(mask);
	}

	/**
	 * @param buffer_offset
	 */
	public static void glPolygonStipple(int buffer_offset) {
		GL11.glPolygonStipple(buffer_offset);
	}

	/**
	 *
	 */
	public static void glPopAttrib() {
		GL11.glPopAttrib();
	}

	/**
	 *
	 */
	public static void glPopClientAttrib() {
		GL11.glPopClientAttrib();
	}

	/**
	 *
	 */
	public static void glPopMatrix() {
		GL11.glPopMatrix();
	}

	/**
	 *
	 */
	public static void glPopName() {
		GL11.glPopName();
	}

	/** @param mask  */
	public static void glPushAttrib(int mask) {
		GL11.glPushAttrib(mask);
	}

	/** @param mask  */
	public static void glPushClientAttrib(int mask) {
		GL11.glPushClientAttrib(mask);
	}

	/**
	 *
	 */
	public static void glPushMatrix() {
		GL11.glPushMatrix();
	}

	/** @param name  */
	public static void glPushName(int name) {
		GL11.glPushName(name);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glRasterPos2f(float x, float y) {
		GL11.glRasterPos2f(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glRasterPos2i(int x, int y) {
		GL11.glRasterPos2i(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glRasterPos3f(float x, float y, float z) {
		GL11.glRasterPos3f(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glRasterPos3i(int x, int y, int z) {
		GL11.glRasterPos3i(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glRasterPos4f(float x, float y, float z, float w) {
		GL11.glRasterPos4f(x, y, z, w);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glRasterPos4i(int x, int y, int z, int w) {
		GL11.glRasterPos4i(x, y, z, w);
	}

	/** @param mode  */
	public static void glReadBuffer(int mode) {
		GL11.glReadBuffer(mode);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, int buffer_offset) {
		GL11.glReadPixels(x, y, width, height, format, type, buffer_offset);
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public static void glRectf(float x1, float y1, float x2, float y2) {
		GL11.glRectf(x1, y1, x2, y2);
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public static void glRecti(int x1, int y1, int x2, int y2) {
		GL11.glRecti(x1, y1, x2, y2);
	}

	/**
	 * @param mode
	 *
	 * @return
	 */
	public static int glRenderMode(int mode) {
		return GL11.glRenderMode(mode);
	}

	/**
	 * @param angle
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glRotatef(float angle, float x, float y, float z) {
		GL11.glRotatef(angle, x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glScalef(float x, float y, float z) {
		GL11.glScalef(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void glScissor(int x, int y, int width, int height) {
		GL11.glScissor(x, y, width, height);
	}

	/** @param buffer  */
	public static void glSelectBuffer(IntBuffer buffer) {
		GL11.glSelectBuffer(buffer);
	}

	/** @param mode  */
	public static void glShadeModel(int mode) {
		GL11.glShadeModel(mode);
	}

	/**
	 * @param func
	 * @param ref
	 * @param mask
	 */
	public static void glStencilFunc(int func, int ref, int mask) {
		GL11.glStencilFunc(func, ref, mask);
	}

	/** @param mask  */
	public static void glStencilMask(int mask) {
		GL11.glStencilMask(mask);
	}

	/**
	 * @param fail
	 * @param zfail
	 * @param zpass
	 */
	public static void glStencilOp(int fail, int zfail, int zpass) {
		GL11.glStencilOp(fail, zfail, zpass);
	}

	/** @param s  */
	public static void glTexCoord1f(float s) {
		GL11.glTexCoord1f(s);
	}

	/**
	 * @param s
	 * @param t
	 */
	public static void glTexCoord2f(float s, float t) {
		GL11.glTexCoord2f(s, t);
	}

	/**
	 * @param s
	 * @param t
	 * @param r
	 */
	public static void glTexCoord3f(float s, float t, float r) {
		GL11.glTexCoord3f(s, t, r);
	}

	/**
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public static void glTexCoord4f(float s, float t, float r, float q) {
		GL11.glTexCoord4f(s, t, r, q);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public static void glTexCoordPointer(int size, int stride, FloatBuffer pointer) {
		GL11.glTexCoordPointer(size, stride, pointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glTexCoordPointer(int size, int type, int stride, int buffer_offset) {
		GL11.glTexCoordPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glTexEnv(int target, int pname, FloatBuffer params) {
		GL11.glTexEnv(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glTexEnv(int target, int pname, IntBuffer params) {
		GL11.glTexEnv(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public static void glTexEnvf(int target, int pname, float param) {
		GL11.glTexEnvf(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public static void glTexEnvi(int target, int pname, int param) {
		GL11.glTexEnvi(target, pname, param);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public static void glTexGen(int coord, int pname, FloatBuffer params) {
		GL11.glTexGen(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public static void glTexGen(int coord, int pname, IntBuffer params) {
		GL11.glTexGen(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	public static void glTexGenf(int coord, int pname, float param) {
		GL11.glTexGenf(coord, pname, param);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	public static void glTexGeni(int coord, int pname, int param) {
		GL11.glTexGeni(coord, pname, param);
	}

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
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}

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
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}

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
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}

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
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, int buffer_offset) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format, type, buffer_offset);
	}

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
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}

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
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}

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
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}

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
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
	}

	/**
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
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, int buffer_offset) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public static void glTexParameter(int target, int pname, FloatBuffer param) {
		GL11.glTexParameter(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public static void glTexParameter(int target, int pname, IntBuffer param) {
		GL11.glTexParameter(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public static void glTexParameterf(int target, int pname, float param) {
		GL11.glTexParameterf(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public static void glTexParameteri(int target, int pname, int param) {
		GL11.glTexParameteri(target, pname, param);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, int buffer_offset) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type, buffer_offset);
	}

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
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}

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
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}

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
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
	}

	/**
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
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int buffer_offset) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, buffer_offset);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glTranslatef(float x, float y, float z) {
		GL11.glTranslatef(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glVertex2f(float x, float y) {
		GL11.glVertex2f(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glVertex2i(int x, int y) {
		GL11.glVertex2i(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glVertex3f(float x, float y, float z) {
		GL11.glVertex3f(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glVertex3i(int x, int y, int z) {
		GL11.glVertex3i(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glVertex4f(float x, float y, float z, float w) {
		GL11.glVertex4f(x, y, z, w);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glVertex4i(int x, int y, int z, int w) {
		GL11.glVertex4i(x, y, z, w);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public static void glVertexPointer(int size, int stride, FloatBuffer pointer) {
		GL11.glVertexPointer(size, stride, pointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glVertexPointer(int size, int type, int stride, int buffer_offset) {
		GL11.glVertexPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public static void glVertexPointer(int size, int stride, IntBuffer pointer) {
		GL11.glVertexPointer(size, stride, pointer);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void glViewport(int x, int y, int width, int height) {
		GL11.glViewport(x, y, width, height);
	}

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
	public static void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
		GL12.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public static void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices) {
		GL12.glDrawRangeElements(mode, start, end, indices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public static void glDrawRangeElements(int mode, int start, int end, int count, int type, int buffer_offset) {
		GL12.glDrawRangeElements(mode, start, end, count, type, buffer_offset);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public static void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) {
		GL12.glDrawRangeElements(mode, start, end, indices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public static void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) {
		GL12.glDrawRangeElements(mode, start, end, indices);
	}

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
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}

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
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}

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
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}

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
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}

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
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, int buffer_offset) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, buffer_offset);
	}

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
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}

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
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}

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
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}

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
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}

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
	 * @param buffer_offset
	 */
	public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int buffer_offset) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, buffer_offset);
	}

	/** @param texture  */
	public static void glActiveTexture(int texture) {
		GL13.glActiveTexture(texture);
	}

	/** @param texture  */
	public static void glClientActiveTexture(int texture) {
		GL13.glClientActiveTexture(texture);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param buffer_offset
	 */
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, int buffer_offset) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, buffer_offset);
	}
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
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
	}

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
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
	}

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
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
	}

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
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
	}

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
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, int buffer_offset) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, buffer_offset);
	}
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
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
	}

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
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
	}

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
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
	}

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
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
	}

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
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, int buffer_offset) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, buffer_offset);
	}
	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, IntBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param data
	 */
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param format
	 * @param imageSize
	 * @param buffer_offset
	 */
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, int buffer_offset) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, buffer_offset);
	}
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
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, IntBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, int buffer_offset) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, buffer_offset);
	}
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
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, IntBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
	}

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
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, int buffer_offset) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, buffer_offset);
	}
	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public static void glGetCompressedTexImage(int target, int lod, ByteBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public static void glGetCompressedTexImage(int target, int lod, IntBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public static void glGetCompressedTexImage(int target, int lod, ShortBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}

	/**
	 * @param target
	 * @param lod
	 * @param buffer_offset
	 */
	public static void glGetCompressedTexImage(int target, int lod, int buffer_offset) {
		GL13.glGetCompressedTexImage(target, lod, buffer_offset);
	}

	/** @param m  */
	public static void glLoadTransposeMatrix(FloatBuffer m) {
		GL13.glLoadTransposeMatrix(m);
	}

	/**
	 * @param target
	 * @param s
	 */
	public static void glMultiTexCoord1f(int target, float s) {
		GL13.glMultiTexCoord1f(target, s);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public static void glMultiTexCoord2f(int target, float s, float t) {
		GL13.glMultiTexCoord2f(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public static void glMultiTexCoord3f(int target, float s, float t, float r) {
		GL13.glMultiTexCoord3f(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public static void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
		GL13.glMultiTexCoord4f(target, s, t, r, q);
	}

	/** @param m  */
	public static void glMultTransposeMatrix(FloatBuffer m) {
		GL13.glMultTransposeMatrix(m);
	}

	/**
	 * @param value
	 * @param invert
	 */
	public static void glSampleCoverage(float value, boolean invert) {
		GL13.glSampleCoverage(value, invert);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glBlendColor(float red, float green, float blue, float alpha) {
		GL14.glBlendColor(red, green, blue, alpha);
	}

	/** @param mode  */
	public static void glBlendEquation(int mode) {
		GL14.glBlendEquation(mode);
	}

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
		GL14.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
	}

	/** @param coord  */
	public static void glFogCoordf(float coord) {
		GL14.glFogCoordf(coord);
	}

	/**
	 * @param stride
	 * @param data
	 */
	public static void glFogCoordPointer(int stride, FloatBuffer data) {
		GL14.glFogCoordPointer(stride, data);
	}

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glFogCoordPointer(int type, int stride, int buffer_offset) {
		GL14.glFogCoordPointer(type, stride, buffer_offset);
	}

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	public static void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount) {
		GL14.glMultiDrawArrays(mode, piFirst, piCount);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public static void glPointParameter(int pname, FloatBuffer params) {
		GL14.glPointParameter(pname, params);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glPointParameterf(int pname, float param) {
		GL14.glPointParameterf(pname, param);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glSecondaryColor3b(byte red, byte green, byte blue) {
		GL14.glSecondaryColor3b(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glSecondaryColor3f(float red, float green, float blue) {
		GL14.glSecondaryColor3f(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glSecondaryColor3ub(byte red, byte green, byte blue) {
		GL14.glSecondaryColor3ub(red, green, blue);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param data
	 */
	public static void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data) {
		GL14.glSecondaryColorPointer(size, unsigned, stride, data);
	}

	/**
	 * @param size
	 * @param stride
	 * @param data
	 */
	public static void glSecondaryColorPointer(int size, int stride, FloatBuffer data) {
		GL14.glSecondaryColorPointer(size, stride, data);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glSecondaryColorPointer(int size, int type, int stride, int buffer_offset) {
		GL14.glSecondaryColorPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glWindowPos2f(float x, float y) {
		GL14.glWindowPos2f(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glWindowPos2i(int x, int y) {
		GL14.glWindowPos2i(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glWindowPos3f(float x, float y, float z) {
		GL14.glWindowPos3f(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glWindowPos3i(int x, int y, int z) {
		GL14.glWindowPos3i(x, y, z);
	}

	/**
	 * @param target
	 * @param id
	 */
	public static void glBeginQuery(int target, int id) {
		GL15.glBeginQuery(target, id);
	}

	/**
	 * @param target
	 * @param buffer
	 */
	public static void glBindBuffer(int target, int buffer) {
		GL15.glBindBuffer(target, buffer);
	}

	/**
	 * @param target
	 * @param size
	 * @param usage
	 */ 
	public static void glBufferData(int target, int size, int usage) {
		GL15.glBufferData(target, size, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, ByteBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, FloatBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, IntBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, ShortBuffer data, int usage) {
		GL15.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubData(int target, int offset, ByteBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubData(int target, int offset, FloatBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubData(int target, int offset, IntBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubData(int target, int offset, ShortBuffer data) {
		GL15.glBufferSubData(target, offset, data);
	}

	/** @param buffers  */
	public static void glDeleteBuffers(IntBuffer buffers) {
		GL15.glDeleteBuffers(buffers);
	}

	/** @param ids  */
	public static void glDeleteQueries(IntBuffer ids) {
		GL15.glDeleteQueries(ids);
	}

	/** @param target  */
	public static void glEndQuery(int target) {
		GL15.glEndQuery(target);
	}

	/** @param buffers  */
	public static void glGenBuffers(IntBuffer buffers) {
		GL15.glGenBuffers(buffers);
	}

	/** @param ids  */
	public static void glGenQueries(IntBuffer ids) {
		GL15.glGenQueries(ids);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetBufferParameter(int target, int pname, IntBuffer params) {
		GL15.glGetBufferParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public static ByteBuffer glGetBufferPointer(int target, int pname, int size) {
		return GL15.glGetBufferPointer(target, pname, size);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubData(int target, int offset, ByteBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubData(int target, int offset, FloatBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubData(int target, int offset, IntBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubData(int target, int offset, ShortBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetQuery(int target, int pname, IntBuffer params) {
		GL15.glGetQuery(target, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public static void glGetQueryObject(int id, int pname, IntBuffer params) {
		GL15.glGetQueryObject(id, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public static void glGetQueryObjectu(int id, int pname, IntBuffer params) {
		GL15.glGetQueryObjectu(id, pname, params);
	}

	/**
	 * @param buffer
	 *
	 * @return
	 */
	public static boolean glIsBuffer(int buffer) {
		return GL15.glIsBuffer(buffer);
	}

	/**
	 * @param id
	 *
	 * @return
	 */
	public static boolean glIsQuery(int id) {
		return GL15.glIsQuery(id);
	}

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 *
	 * @return
	 */
	public static ByteBuffer glMapBuffer(int target, int access, int size, ByteBuffer oldBuffer) {
		return GL15.glMapBuffer(target, access, size, oldBuffer);
	}

	/**
	 * @param target
	 *
	 * @return
	 */
	public static boolean glUnmapBuffer(int target) {
		return GL15.glUnmapBuffer(target);
	}

	/**
	 * @param target
	 * @param buffer
	 */
	public static void glBindBufferARB(int target, int buffer) {
		ARBBufferObject.glBindBufferARB(target, buffer);
	}

	/**
	 * @param target
	 * @param size
	 * @param usage
	 */
	public static void glBufferDataARB(int target, int size, int usage) {
		ARBBufferObject.glBufferDataARB(target, size, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferDataARB(int target, ByteBuffer data, int usage) {
		ARBBufferObject.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferDataARB(int target, FloatBuffer data, int usage) {
		ARBBufferObject.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferDataARB(int target, IntBuffer data, int usage) {
		ARBBufferObject.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public static void glBufferDataARB(int target, ShortBuffer data, int usage) {
		ARBBufferObject.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubDataARB(int target, int offset, ByteBuffer data) {
		ARBBufferObject.glBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubDataARB(int target, int offset, FloatBuffer data) {
		ARBBufferObject.glBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubDataARB(int target, int offset, IntBuffer data) {
		ARBBufferObject.glBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glBufferSubDataARB(int target, int offset, ShortBuffer data) {
		ARBBufferObject.glBufferSubDataARB(target, offset, data);
	}

	/** @param buffers  */
	public static void glDeleteBuffersARB(IntBuffer buffers) {
		ARBBufferObject.glDeleteBuffersARB(buffers);
	}

	/** @param buffers  */
	public static void glGenBuffersARB(IntBuffer buffers) {
		ARBBufferObject.glGenBuffersARB(buffers);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetBufferParameterARB(int target, int pname, IntBuffer params) {
		ARBBufferObject.glGetBufferParameterARB(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public static ByteBuffer glGetBufferPointerARB(int target, int pname, int size) {
		return ARBBufferObject.glGetBufferPointerARB(target, pname, size);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubDataARB(int target, int offset, ByteBuffer data) {
		ARBBufferObject.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubDataARB(int target, int offset, FloatBuffer data) {
		ARBBufferObject.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubDataARB(int target, int offset, IntBuffer data) {
		ARBBufferObject.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubDataARB(int target, int offset, ShortBuffer data) {
		ARBBufferObject.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param buffer
	 *
	 * @return
	 */
	public static boolean glIsBufferARB(int buffer) {
		return ARBBufferObject.glIsBufferARB(buffer);
	}

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 *
	 * @return
	 */
	public static ByteBuffer glMapBufferARB(int target, int access, int size, ByteBuffer oldBuffer) {
		return ARBBufferObject.glMapBufferARB(target, access, size, oldBuffer);
	}

	/**
	 * @param target
	 *
	 * @return
	 */
	public static boolean glUnmapBufferARB(int target) {
		return ARBBufferObject.glUnmapBufferARB(target);
	}

	/**
	 * @param target
	 * @param program
	 */
	public static void glBindProgramARB(int target, int program) {
		ARBFragmentProgram.glBindProgramARB(target, program);
	}

	/** @param programs  */
	public static void glDeleteProgramsARB(IntBuffer programs) {
		ARBFragmentProgram.glDeleteProgramsARB(programs);
	}

	/** @param programs  */
	public static void glGenProgramsARB(IntBuffer programs) {
		ARBFragmentProgram.glGenProgramsARB(programs);
	}

	/**
	 * @param target
	 * @param parameterName
	 * @param params
	 */
	public static void glGetProgramARB(int target, int parameterName, IntBuffer params) {
		ARBFragmentProgram.glGetProgramARB(target, parameterName, params);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public static void glGetProgramEnvParameterARB(int target, int index, FloatBuffer params) {
		ARBFragmentProgram.glGetProgramEnvParameterARB(target, index, params);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public static void glGetProgramLocalParameterARB(int target, int index, FloatBuffer params) {
		ARBFragmentProgram.glGetProgramLocalParameterARB(target, index, params);
	}

	/**
	 * @param target
	 * @param parameterName
	 * @param paramString
	 */
	public static void glGetProgramStringARB(int target, int parameterName, ByteBuffer paramString) {
		ARBFragmentProgram.glGetProgramStringARB(target, parameterName, paramString);
	}

	/**
	 * @param program
	 *
	 * @return
	 */
	public static boolean glIsProgramARB(int program) {
		return ARBFragmentProgram.glIsProgramARB(program);
	}

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glProgramEnvParameter4fARB(int target, int index, float x, float y, float z, float w) {
		ARBFragmentProgram
		        .glProgramEnvParameter4fARB(target, index, x, y, z, w);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public static void glProgramEnvParameterARB(int target, int index, FloatBuffer params) {
		ARBFragmentProgram.glProgramEnvParameterARB(target, index, params);
	}

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glProgramLocalParameter4fARB(int target, int index, float x, float y, float z, float w) {
		ARBFragmentProgram.glProgramLocalParameter4fARB(target, index, x, y, z, w);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public static void glProgramLocalParameterARB(int target, int index, FloatBuffer params) {
		ARBFragmentProgram.glProgramLocalParameterARB(target, index, params);
	}

	/**
	 * @param target
	 * @param format
	 * @param string
	 */
	public static void glProgramStringARB(int target, int format, ByteBuffer string) {
		ARBFragmentProgram.glProgramStringARB(target, format, string);
	}

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	public static void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data) {
		ARBImaging.glColorSubTable(target, start, count, format, type, data);
	}

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	public static void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data) {
		ARBImaging.glColorSubTable(target, start, count, format, type, data);
	}

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glColorSubTable(int target, int start, int count, int format, int type, int buffer_offset) {
		ARBImaging.glColorSubTable(target, start, count, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data) {
		ARBImaging.glColorTable(target, internalFormat, width, format, type, data);
	}

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
		ARBImaging.glColorTable(target, internalFormat, width, format, type, data);
	}

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glColorTable(int target, int internalFormat, int width, int format, int type, int buffer_offset) {
		ARBImaging.glColorTable(target, internalFormat, width, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glColorTableParameter(int target, int pname, FloatBuffer params) {
		ARBImaging.glColorTableParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glColorTableParameter(int target, int pname, IntBuffer params) {
		ARBImaging.glColorTableParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image) {
		ARBImaging.glConvolutionFilter1D(target, internalformat, width, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image) {
		ARBImaging.glConvolutionFilter1D(target, internalformat, width, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image) {
		ARBImaging.glConvolutionFilter1D(target, internalformat, width, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image) {
		ARBImaging.glConvolutionFilter1D(target, internalformat, width, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, int buffer_offset) {
		ARBImaging.glConvolutionFilter1D(target, internalformat, width, format, type, buffer_offset);

	}
	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer image) {
		ARBImaging.glConvolutionFilter2D(target, internalformat, width, height, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer image) {
		ARBImaging.glConvolutionFilter2D(target, internalformat, width, height, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer image) {
		ARBImaging.glConvolutionFilter2D(target, internalformat, width, height, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, int buffer_offset) {
		ARBImaging.glConvolutionFilter2D(target, internalformat, width, height, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glConvolutionParameter(int target, int pname, FloatBuffer params) {
		ARBImaging.glConvolutionParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glConvolutionParameterf(int target, int pname, float params) {
		ARBImaging.glConvolutionParameterf(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glConvolutionParameteri(int target, int pname, int params) {
		ARBImaging.glConvolutionParameteri(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glConvolutionParameteriv(int target, int pname, IntBuffer params) {
		ARBImaging.glConvolutionParameteriv(target, pname, params);
	}

	/**
	 * @param target
	 * @param start
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void glCopyColorSubTable(int target, int start, int x, int y, int width) {
		ARBImaging.glCopyColorSubTable(target, start, x, y, width);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void glCopyColorTable(int target, int internalformat, int x, int y, int width) {
		ARBImaging.glCopyColorTable(target, internalformat, x, y, width);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width) {
		ARBImaging.glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height) {
		ARBImaging.glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	public static void glGetColorTable(int target, int format, int type, ByteBuffer data) {
		ARBImaging.glGetColorTable(target, format, type, data);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	public static void glGetColorTable(int target, int format, int type, FloatBuffer data) {
		ARBImaging.glGetColorTable(target, format, type, data);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetColorTableParameter(int target, int pname, FloatBuffer params) {
		ARBImaging.glGetColorTableParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetColorTableParameter(int target, int pname, IntBuffer params) {
		ARBImaging.glGetColorTableParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image) {
		ARBImaging.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image) {
		ARBImaging.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glGetConvolutionFilter(int target, int format, int type, IntBuffer image) {
		ARBImaging.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public static void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image) {
		ARBImaging.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public static void glGetConvolutionFilter(int target, int format, int type, int buffer_offset) {
		ARBImaging.glGetConvolutionFilter(target, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetConvolutionParameter(int target, int pname, FloatBuffer params) {
		ARBImaging.glGetConvolutionParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetConvolutionParameter(int target, int pname, IntBuffer params) {
		ARBImaging.glGetConvolutionParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public static void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
		ARBImaging.glGetHistogram(target, reset, format, type, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public static void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values) {
		ARBImaging.glGetHistogram(target, reset, format, type, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public static void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values) {
		ARBImaging.glGetHistogram(target, reset, format, type, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public static void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values) {
		ARBImaging.glGetHistogram(target, reset, format, type, values);
	}

	public static void glGetHistogram(int target, boolean reset, int format, int type, int buffer_offset) {
		ARBImaging.glGetHistogram(target, reset, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetHistogramParameter(int target, int pname, FloatBuffer params) {
		ARBImaging.glGetHistogramParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetHistogramParameter(int target, int pname, IntBuffer params) {
		ARBImaging.glGetHistogramParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public static void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values) {
		ARBImaging.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public static void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values) {
		ARBImaging.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public static void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values) {
		ARBImaging.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public static void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values) {
		ARBImaging.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param buffer_offset
	 */
	public static void glGetMinmax(int target, boolean reset, int format, int types, int buffer_offset) {
		ARBImaging.glGetMinmax(target, reset, format, types, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetMinmaxParameter(int target, int pname, FloatBuffer params) {
		ARBImaging.glGetMinmaxParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetMinmaxParameter(int target, int pname, IntBuffer params) {
		ARBImaging.glGetMinmaxParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param row
	 * @param column
	 * @param span
	 */
	public static void glGetSeparableFilter(int target, int format, int type, Buffer row, Buffer column, Buffer span) {
		ARBImaging.glGetSeparableFilter(target, format, type, row, column, span);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param row_offset
	 * @param column_offset
	 * @param span_offset
	 */
	public static void glGetSeparableFilter(int target, int format, int type, int row_offset, int column_offset, int span_offset) {
		ARBImaging.glGetSeparableFilter(target, format, type, row_offset, column_offset, span_offset);
	}

	/**
	 * @param target
	 * @param width
	 * @param internalformat
	 * @param sink
	 */
	public static void glHistogram(int target, int width, int internalformat, boolean sink) {
		ARBImaging.glHistogram(target, width, internalformat, sink);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param sink
	 */
	public static void glMinmax(int target, int internalformat, boolean sink) {
		ARBImaging.glMinmax(target, internalformat, sink);
	}

	/** @param target  */
	public static void glResetHistogram(int target) {
		ARBImaging.glResetHistogram(target);
	}

	/** @param target  */
	public static void glResetMinmax(int target) {
		ARBImaging.glResetMinmax(target);
	}

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
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, Buffer column) {
		ARBImaging.glSeparableFilter2D(target, internalformat, width, height, format, type, row, column);
	}

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
	public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, int row_offset, int column_offset) {
		ARBImaging.glSeparableFilter2D(target, internalformat, width, height, format, type, row_offset, column_offset);
	}

	/** @param index  */
	public static void glCurrentPaletteMatrixARB(int index) {
		ARBMatrixPalette.glCurrentPaletteMatrixARB(index);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public static void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) {
		ARBMatrixPalette.glMatrixIndexPointerARB(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glMatrixIndexPointerARB(int size, int type, int stride, int buffer_offset) {
		ARBMatrixPalette.glMatrixIndexPointerARB(size, type, stride, buffer_offset);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public static void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) {
		ARBMatrixPalette.glMatrixIndexPointerARB(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public static void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) {
		ARBMatrixPalette.glMatrixIndexPointerARB(size, stride, pPointer);
	}

	/** @param pIndices  */
	public static void glMatrixIndexuARB(ByteBuffer pIndices) {
		ARBMatrixPalette.glMatrixIndexuARB(pIndices);
	}

	/** @param piIndices  */
	public static void glMatrixIndexuARB(IntBuffer piIndices) {
		ARBMatrixPalette.glMatrixIndexuARB(piIndices);
	}

	/** @param psIndices  */
	public static void glMatrixIndexuARB(ShortBuffer psIndices) {
		ARBMatrixPalette.glMatrixIndexuARB(psIndices);
	}

	/**
	 * @param value
	 * @param invert
	 */
	public static void glSampleCoverageARB(float value, boolean invert) {
		ARBMultisample.glSampleCoverageARB(value, invert);
	}

	/** @param texture  */
	public static void glActiveTextureARB(int texture) {
		ARBMultitexture.glActiveTextureARB(texture);
	}

	/** @param texture  */
	public static void glClientActiveTextureARB(int texture) {
		ARBMultitexture.glClientActiveTextureARB(texture);
	}

	/**
	 * @param target
	 * @param s
	 */
	public static void glMultiTexCoord1fARB(int target, float s) {
		ARBMultitexture.glMultiTexCoord1fARB(target, s);
	}

	/**
	 * @param target
	 * @param s
	 */
	public static void glMultiTexCoord1iARB(int target, int s) {
		ARBMultitexture.glMultiTexCoord1iARB(target, s);
	}

	/**
	 * @param target
	 * @param s
	 */
	public static void glMultiTexCoord1sARB(int target, short s) {
		ARBMultitexture.glMultiTexCoord1sARB(target, s);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public static void glMultiTexCoord2fARB(int target, float s, float t) {
		ARBMultitexture.glMultiTexCoord2fARB(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public static void glMultiTexCoord2iARB(int target, int s, int t) {
		ARBMultitexture.glMultiTexCoord2iARB(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public static void glMultiTexCoord2sARB(int target, short s, short t) {
		ARBMultitexture.glMultiTexCoord2sARB(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public static void glMultiTexCoord3fARB(int target, float s, float t, float r) {
		ARBMultitexture.glMultiTexCoord3fARB(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public static void glMultiTexCoord3iARB(int target, int s, int t, int r) {
		ARBMultitexture.glMultiTexCoord3iARB(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public static void glMultiTexCoord3sARB(int target, short s, short t, short r) {
		ARBMultitexture.glMultiTexCoord3sARB(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public static void glMultiTexCoord4fARB(int target, float s, float t, float r, float q) {
		ARBMultitexture.glMultiTexCoord4fARB(target, s, t, r, q);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public static void glMultiTexCoord4iARB(int target, int s, int t, int r, int q) {
		ARBMultitexture.glMultiTexCoord4iARB(target, s, t, r, q);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public static void glMultiTexCoord4sARB(int target, short s, short t, short r, short q) {
		ARBMultitexture.glMultiTexCoord4sARB(target, s, t, r, q);
	}

	/**
	 * @param target
	 * @param id
	 */
	public static void glBeginQueryARB(int target, int id) {
		ARBOcclusionQuery.glBeginQueryARB(target, id);
	}

	/** @param ids  */
	public static void glDeleteQueriesARB(IntBuffer ids) {
		ARBOcclusionQuery.glDeleteQueriesARB(ids);
	}

	/** @param target  */
	public static void glEndQueryARB(int target) {
		ARBOcclusionQuery.glEndQueryARB(target);
	}

	/** @param ids  */
	public static void glGenQueriesARB(IntBuffer ids) {
		ARBOcclusionQuery.glGenQueriesARB(ids);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetQueryARB(int target, int pname, IntBuffer params) {
		ARBOcclusionQuery.glGetQueryARB(target, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public static void glGetQueryObjectiARB(int id, int pname, IntBuffer params) {
		ARBOcclusionQuery.glGetQueryObjectiARB(id, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public static void glGetQueryObjectuiARB(int id, int pname, IntBuffer params) {
		ARBOcclusionQuery.glGetQueryObjectuiARB(id, pname, params);
	}

	/**
	 * @param id
	 *
	 * @return
	 */
	public static boolean glIsQueryARB(int id) {
		return ARBOcclusionQuery.glIsQueryARB(id);
	}

	/**
	 * @param pname
	 * @param pfParams
	 */
	public static void glPointParameterARB(int pname, FloatBuffer pfParams) {
		ARBPointParameters.glPointParameterARB(pname, pfParams);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glPointParameterfARB(int pname, float param) {
		ARBPointParameters.glPointParameterfARB(pname, param);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer pData) {
		ARBTextureCompression.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer pData) {
		ARBTextureCompression.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer pData) {
		ARBTextureCompression.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param level
	 * @param internalformat
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer pData) {
		ARBTextureCompression.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer pData) {
		ARBTextureCompression.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer pData) {
		ARBTextureCompression.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer pData) {
		ARBTextureCompression.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer pData) {
		ARBTextureCompression.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		ARBTextureCompression.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		ARBTextureCompression.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		ARBTextureCompression.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
	}

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
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		ARBTextureCompression.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ByteBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, FloatBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, IntBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param width
	 * @param border
	 * @param imageSize
	 * @param pData
	 */
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ShortBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ByteBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, FloatBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, IntBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ShortBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
	}

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
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		ARBTextureCompression.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public static void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg) {
		ARBTextureCompression.glGetCompressedTexImageARB(target, lod, pImg);
	}

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public static void glGetCompressedTexImageARB(int target, int lod, IntBuffer pImg) {
		ARBTextureCompression.glGetCompressedTexImageARB(target, lod, pImg);
	}

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public static void glGetCompressedTexImageARB(int target, int lod, ShortBuffer pImg) {
		ARBTextureCompression.glGetCompressedTexImageARB(target, lod, pImg);
	}

	/** @param pfMtx  */
	public static void glLoadTransposeMatrixARB(FloatBuffer pfMtx) {
		ARBTransposeMatrix.glLoadTransposeMatrixARB(pfMtx);
	}

	/** @param pfMtx  */
	public static void glMultTransposeMatrixfARB(FloatBuffer pfMtx) {
		ARBTransposeMatrix.glMultTransposeMatrixfARB(pfMtx);
	}

	/** @param count  */
	public static void glVertexBlendARB(int count) {
		ARBVertexBlend.glVertexBlendARB(count);
	}

	/** @param pWeights  */
	public static void glWeightARB(ByteBuffer pWeights) {
		ARBVertexBlend.glWeightARB(pWeights);
	}

	/** @param pfWeights  */
	public static void glWeightARB(FloatBuffer pfWeights) {
		ARBVertexBlend.glWeightARB(pfWeights);
	}

	/** @param piWeights  */
	public static void glWeightARB(IntBuffer piWeights) {
		ARBVertexBlend.glWeightARB(piWeights);
	}

	/** @param psWeights  */
	public static void glWeightARB(ShortBuffer psWeights) {
		ARBVertexBlend.glWeightARB(psWeights);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		ARBVertexBlend.glWeightPointerARB(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer) {
		ARBVertexBlend.glWeightPointerARB(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ShortBuffer pPointer) {
		ARBVertexBlend.glWeightPointerARB(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public static void glWeightPointerARB(int size, int stride, FloatBuffer pPointer) {
		ARBVertexBlend.glWeightPointerARB(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glWeightPointerARB(int size, int type, int stride, int buffer_offset) {
		ARBVertexBlend.glWeightPointerARB(size, type, stride, buffer_offset);
	}

	/** @param pWeights  */
	public static void glWeightuARB(ByteBuffer pWeights) {
		ARBVertexBlend.glWeightuARB(pWeights);
	}

	/** @param piWeights  */
	public static void glWeightuARB(IntBuffer piWeights) {
		ARBVertexBlend.glWeightuARB(piWeights);
	}

	/** @param psWeights  */
	public static void glWeightuARB(ShortBuffer psWeights) {
		ARBVertexBlend.glWeightuARB(psWeights);
	}

	/**
	 * @param programObj
	 * @param index
	 * @param name
	 */
	public static void glBindAttribLocationARB(int programObj, int index, ByteBuffer name) {
		ARBVertexShader.glBindAttribLocationARB(programObj, index, name);
	}

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public static void glGetActiveAttribARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		ARBVertexShader.glGetActiveAttribARB(programObj, index, length, size, type, name);
	}

	/**
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	public static int glGetAttribLocationARB(int programObj, ByteBuffer name) {
		return ARBVertexShader.glGetAttribLocationARB(programObj, name);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glWindowPos2fARB(float x, float y) {
		ARBWindowPos.glWindowPos2fARB(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glWindowPos2iARB(int x, int y) {
		ARBWindowPos.glWindowPos2iARB(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public static void glWindowPos2sARB(short x, short y) {
		ARBWindowPos.glWindowPos2sARB(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glWindowPos3fARB(float x, float y, float z) {
		ARBWindowPos.glWindowPos3fARB(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glWindowPos3iARB(int x, int y, int z) {
		ARBWindowPos.glWindowPos3iARB(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glWindowPos3sARB(short x, short y, short z) {
		ARBWindowPos.glWindowPos3sARB(x, y, z);
	}

	/**
	 * @param containerObj
	 * @param obj
	 */
	public static void glAttachObjectARB(int containerObj, int obj) {
		ARBShaderObjects.glAttachObjectARB(containerObj, obj);
	}

	/** @param shaderObj  */
	public static void glCompileShaderARB(int shaderObj) {
		ARBShaderObjects.glCompileShaderARB(shaderObj);
	}

	/** @return  */
	public static int glCreateProgramObjectARB() {
		return ARBShaderObjects.glCreateProgramObjectARB();
	}

	/**
	 * @param shaderType
	 *
	 * @return
	 */
	public static int glCreateShaderObjectARB(int shaderType) {
		return ARBShaderObjects.glCreateShaderObjectARB(shaderType);
	}

	/** @param obj  */
	public static void glDeleteObjectARB(int obj) {
		ARBShaderObjects.glDeleteObjectARB(obj);
	}

	/**
	 * @param containerObj
	 * @param attachedObj
	 */
	public static void glDetachObjectARB(int containerObj, int attachedObj) {
		ARBShaderObjects.glDetachObjectARB(containerObj, attachedObj);
	}

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public static void glGetActiveUniformARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		ARBShaderObjects.glGetActiveUniformARB(programObj, index, length, size, type, name);
	}

	/**
	 * @param containerObj
	 * @param count
	 * @param obj
	 */
	public static void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj) {
		ARBShaderObjects.glGetAttachedObjectsARB(containerObj, count, obj);
	}

	/**
	 * @param pname
	 *
	 * @return
	 */
	public static int glGetHandleARB(int pname) {
		return ARBShaderObjects.glGetHandleARB(pname);
	}

	/**
	 * @param obj
	 * @param length
	 * @param infoLog
	 */
	public static void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog) {
		ARBShaderObjects.glGetInfoLogARB(obj, length, infoLog);
	}

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	public static void glGetObjectParameterARB(int obj, int pname, FloatBuffer params) {
		ARBShaderObjects.glGetObjectParameterARB(obj, pname, params);
	}

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	public static void glGetObjectParameterARB(int obj, int pname, IntBuffer params) {
		ARBShaderObjects.glGetObjectParameterARB(obj, pname, params);
	}

	/**
	 * @param obj
	 * @param length
	 * @param source
	 */
	public static void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source) {
		ARBShaderObjects.glGetShaderSourceARB(obj, length, source);
	}

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	public static void glGetUniformARB(int programObj, int location, FloatBuffer params) {
		ARBShaderObjects.glGetUniformARB(programObj, location, params);
	}

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	public static void glGetUniformARB(int programObj, int location, IntBuffer params) {
		ARBShaderObjects.glGetUniformARB(programObj, location, params);
	}

	/**
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	public static int glGetUniformLocationARB(int programObj, ByteBuffer name) {
		return ARBShaderObjects.glGetUniformLocationARB(programObj, name);
	}

	/** @param programObj  */
	public static void glLinkProgramARB(int programObj) {
		ARBShaderObjects.glLinkProgramARB(programObj);
	}

	/**
	 * @param shaderObj
	 * @param string
	 */
	public static void glShaderSourceARB(int shaderObj, ByteBuffer string) {
		ARBShaderObjects.glShaderSourceARB(shaderObj, string);
	}

	/**
	 * @param shaderObj
	 * @param strings
	 */
	public static void glShaderSourceARB(int shaderObj, ByteBuffer[] strings) {
		ARBShaderObjects.glShaderSourceARB(shaderObj, strings);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform1ARB(int location, FloatBuffer values) {
		ARBShaderObjects.glUniform1ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform1ARB(int location, IntBuffer values) {
		ARBShaderObjects.glUniform1ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public static void glUniform1fARB(int location, float v0) {
		ARBShaderObjects.glUniform1fARB(location, v0);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public static void glUniform1iARB(int location, int v0) {
		ARBShaderObjects.glUniform1iARB(location, v0);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform2ARB(int location, FloatBuffer values) {
		ARBShaderObjects.glUniform2ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform2ARB(int location, IntBuffer values) {
		ARBShaderObjects.glUniform2ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public static void glUniform2fARB(int location, float v0, float v1) {
		ARBShaderObjects.glUniform2fARB(location, v0, v1);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public static void glUniform2iARB(int location, int v0, int v1) {
		ARBShaderObjects.glUniform2iARB(location, v0, v1);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform3ARB(int location, FloatBuffer values) {
		ARBShaderObjects.glUniform3ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform3ARB(int location, IntBuffer values) {
		ARBShaderObjects.glUniform3ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public static void glUniform3fARB(int location, float v0, float v1, float v2) {
		ARBShaderObjects.glUniform3fARB(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public static void glUniform3iARB(int location, int v0, int v1, int v2) {
		ARBShaderObjects.glUniform3iARB(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform4ARB(int location, FloatBuffer values) {
		ARBShaderObjects.glUniform4ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform4ARB(int location, IntBuffer values) {
		ARBShaderObjects.glUniform4ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public static void glUniform4fARB(int location, float v0, float v1, float v2, float v3) {
		ARBShaderObjects.glUniform4fARB(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public static void glUniform4iARB(int location, int v0, int v1, int v2, int v3) {
		ARBShaderObjects.glUniform4iARB(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public static void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices) {
		ARBShaderObjects.glUniformMatrix2ARB(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public static void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices) {
		ARBShaderObjects.glUniformMatrix3ARB(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public static void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices) {
		ARBShaderObjects.glUniformMatrix4ARB(location, transpose, matrices);
	}

	/** @param programObj  */
	public static void glUseProgramObjectARB(int programObj) {
		ARBShaderObjects.glUseProgramObjectARB(programObj);
	}

	/** @param programObj  */
	public static void glValidateProgramARB(int programObj) {
		ARBShaderObjects.glValidateProgramARB(programObj);
	}

	/** @param index  */
	public static void glDisableVertexAttribArrayARB(int index) {
		ARBVertexProgram.glDisableVertexAttribArrayARB(index);
	}

	/** @param index  */
	public static void glEnableVertexAttribArrayARB(int index) {
		ARBVertexProgram.glEnableVertexAttribArrayARB(index);
	}

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
		ARBVertexProgram.glGetVertexAttribARB(index, pname, params);
	}

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
		ARBVertexProgram.glGetVertexAttribARB(index, pname, params);
	}

	/**
	 * @param index
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public static ByteBuffer glGetVertexAttribPointerARB(int index, int pname, int size) {
		return ARBVertexProgram.glGetVertexAttribPointerARB(index, pname, size);
	}

	/**
	 * @param index
	 * @param x
	 */
	public static void glVertexAttrib1fARB(int index, float x) {
		ARBVertexProgram.glVertexAttrib1fARB(index, x);
	}

	/**
	 * @param index
	 * @param x
	 */
	public static void glVertexAttrib1sARB(int index, short x) {
		ARBVertexProgram.glVertexAttrib1sARB(index, x);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	public static void glVertexAttrib2fARB(int index, float x, float y) {
		ARBVertexProgram.glVertexAttrib2fARB(index, x, y);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	public static void glVertexAttrib2sARB(int index, short x, short y) {
		ARBVertexProgram.glVertexAttrib2sARB(index, x, y);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glVertexAttrib3fARB(int index, float x, float y, float z) {
		ARBVertexProgram.glVertexAttrib3fARB(index, x, y, z);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void glVertexAttrib3sARB(int index, short x, short y, short z) {
		ARBVertexProgram.glVertexAttrib3sARB(index, x, y, z);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glVertexAttrib4fARB(int index, float x, float y, float z, float w) {
		ARBVertexProgram.glVertexAttrib4fARB(index, x, y, z, w);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
		ARBVertexProgram.glVertexAttrib4NubARB(index, x, y, z, w);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public static void glVertexAttrib4sARB(int index, short x, short y, short z, short w) {
		ARBVertexProgram.glVertexAttrib4sARB(index, x, y, z, w);
	}

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		ARBVertexProgram.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		ARBVertexProgram.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		ARBVertexProgram.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		ARBVertexProgram.glVertexAttribPointerARB(index, size, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param type
	 * @param normalized
	 * @param stride
	 * @param bufferOffset
	 */
	public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, int bufferOffset) {
		ARBVertexProgram.glVertexAttribPointerARB(index, size, type, normalized, stride, bufferOffset);
	}

	/**
	 * @param modeRGB
	 * @param modeAlpha
	 */
	public static void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha) {
		EXTBlendEquationSeparate.glBlendEquationSeparateEXT(modeRGB, modeAlpha);
	}

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	public static void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
		EXTBlendFuncSeparate.glBlendFuncSeparateEXT(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
	}

	/**
	 * @param first
	 * @param count
	 */
	public static void glLockArraysEXT(int first, int count) {
		EXTCompiledVertexArray.glLockArraysEXT(first, count);
	}

	/**
	 *
	 */
	public static void glUnlockArraysEXT() {
		EXTCompiledVertexArray.glUnlockArraysEXT();
	}

	/**
	 * @param zmin
	 * @param zmax
	 */
	public static void glDepthBoundsEXT(float zmin, float zmax) {
		EXTDepthBoundsTest.glDepthBoundsEXT(zmin, zmax);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public static void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices) {
		EXTDrawRangeElements.glDrawRangeElementsEXT(mode, start, end, pIndices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public static void glDrawRangeElementsEXT(int mode, int start, int end, int count, int type, int buffer_offset) {
		EXTDrawRangeElements.glDrawRangeElementsEXT(mode, start, end, count, type, buffer_offset);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public static void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) {
		EXTDrawRangeElements.glDrawRangeElementsEXT(mode, start, end, pIndices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public static void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) {
		EXTDrawRangeElements.glDrawRangeElementsEXT(mode, start, end, pIndices);
	}

	/** @param coord  */
	public static void glFogCoordfEXT(float coord) {
		EXTFogCoord.glFogCoordfEXT(coord);
	}

	/**
	 * @param stride
	 * @param data
	 */
	public static void glFogCoordPointerEXT(int stride, FloatBuffer data) {
		EXTFogCoord.glFogCoordPointerEXT(stride, data);
	}

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glFogCoordPointerEXT(int type, int stride, int buffer_offset) {
		EXTFogCoord.glFogCoordPointerEXT(type, stride, buffer_offset);
	}

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	public static void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount) {
		EXTMultiDrawArrays.glMultiDrawArraysEXT(mode, piFirst, piCount);
	}

	/**
	 * @param pname
	 * @param pfParams
	 */
	public static void glPointParameterEXT(int pname, FloatBuffer pfParams) {
		EXTPointParameters.glPointParameterEXT(pname, pfParams);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public static void glPointParameterfEXT(int pname, float param) {
		EXTPointParameters.glPointParameterfEXT(pname, param);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glSecondaryColor3bEXT(byte red, byte green, byte blue) {
		EXTSecondaryColor.glSecondaryColor3bEXT(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glSecondaryColor3fEXT(float red, float green, float blue) {
		EXTSecondaryColor.glSecondaryColor3fEXT(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public static void glSecondaryColor3ubEXT(byte red, byte green, byte blue) {
		EXTSecondaryColor.glSecondaryColor3ubEXT(red, green, blue);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public static void glSecondaryColorPointerEXT(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		EXTSecondaryColor.glSecondaryColorPointerEXT(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public static void glSecondaryColorPointerEXT(int size, int stride, FloatBuffer pPointer) {
		EXTSecondaryColor.glSecondaryColorPointerEXT(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glSecondaryColorPointerEXT(int size, int type, int stride, int buffer_offset) {
		EXTSecondaryColor.glSecondaryColorPointerEXT(size, type, stride, buffer_offset);
	}

	/** @param face  */
	public static void glActiveStencilFaceEXT(int face) {
		EXTStencilTwoSide.glActiveStencilFaceEXT(face);
	}

	/**
	 *
	 */
	public static void glBeginVertexShaderEXT() {
		EXTVertexShader.glBeginVertexShaderEXT();
	}

	/**
	 * @param light
	 * @param value
	 *
	 * @return
	 */
	public static int glBindLightParameterEXT(int light, int value) {
		return EXTVertexShader.glBindLightParameterEXT(light, value);
	}

	/**
	 * @param face
	 * @param value
	 *
	 * @return
	 */
	public static int glBindMaterialParameterEXT(int face, int value) {
		return EXTVertexShader.glBindMaterialParameterEXT(face, value);
	}

	/**
	 * @param value
	 *
	 * @return
	 */
	public static int glBindParameterEXT(int value) {
		return EXTVertexShader.glBindParameterEXT(value);
	}

	/**
	 * @param unit
	 * @param coord
	 * @param value
	 *
	 * @return
	 */
	public static int glBindTexGenParameterEXT(int unit, int coord, int value) {
		return EXTVertexShader.glBindTexGenParameterEXT(unit, coord, value);
	}

	/**
	 * @param unit
	 * @param value
	 *
	 * @return
	 */
	public static int glBindTextureUnitParameterEXT(int unit, int value) {
		return EXTVertexShader.glBindTextureUnitParameterEXT(unit, value);
	}

	/** @param id  */
	public static void glBindVertexShaderEXT(int id) {
		EXTVertexShader.glBindVertexShaderEXT(id);
	}

	/** @param id  */
	public static void glDeleteVertexShaderEXT(int id) {
		EXTVertexShader.glDeleteVertexShaderEXT(id);
	}

	/** @param id  */
	public static void glDisableVariantClientStateEXT(int id) {
		EXTVertexShader.glDisableVariantClientStateEXT(id);
	}

	/** @param id  */
	public static void glEnableVariantClientStateEXT(int id) {
		EXTVertexShader.glEnableVariantClientStateEXT(id);
	}

	/**
	 *
	 */
	public static void glEndVertexShaderEXT() {
		EXTVertexShader.glEndVertexShaderEXT();
	}

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	public static void glExtractComponentEXT(int res, int src, int num) {
		EXTVertexShader.glExtractComponentEXT(res, src, num);
	}

	/**
	 * @param dataType
	 * @param storageType
	 * @param range
	 * @param components
	 *
	 * @return
	 */
	public static int glGenSymbolsEXT(int dataType, int storageType, int range, int components) {
		return EXTVertexShader.glGenSymbolsEXT(dataType, storageType, range, components);
	}

	/**
	 * @param range
	 *
	 * @return
	 */
	public static int glGenVertexShadersEXT(int range) {
		return EXTVertexShader.glGenVertexShadersEXT(range);
	}

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public static void glGetInvariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		EXTVertexShader.glGetInvariantBooleanEXT(id, value, pbData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public static void glGetInvariantFloatEXT(int id, int value, FloatBuffer pfData) {
		EXTVertexShader.glGetInvariantFloatEXT(id, value, pfData);
	}

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public static void glGetInvariantIntegerEXT(int id, int value, IntBuffer piData) {
		EXTVertexShader.glGetInvariantIntegerEXT(id, value, piData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public static void glGetLocalConstantBooleanEXT(int id, int value, ByteBuffer pbData) {
		EXTVertexShader.glGetLocalConstantBooleanEXT(id, value, pbData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public static void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pfData) {
		EXTVertexShader.glGetLocalConstantFloatEXT(id, value, pfData);
	}

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public static void glGetLocalConstantIntegerEXT(int id, int value, IntBuffer piData) {
		EXTVertexShader.glGetLocalConstantIntegerEXT(id, value, piData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public static void glGetVariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		EXTVertexShader.glGetVariantBooleanEXT(id, value, pbData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public static void glGetVariantFloatEXT(int id, int value, FloatBuffer pfData) {
		EXTVertexShader.glGetVariantFloatEXT(id, value, pfData);
	}

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public static void glGetVariantIntegerEXT(int id, int value, IntBuffer piData) {
		EXTVertexShader.glGetVariantIntegerEXT(id, value, piData);
	}

	/**
	 * @param id
	 * @param value
	 * @param size
	 *
	 * @return
	 */
	public static ByteBuffer glGetVariantPointerEXT(int id, int value, int size) {
		return EXTVertexShader.glGetVariantPointerEXT(id, value, size);
	}

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	public static void glInsertComponentEXT(int res, int src, int num) {
		EXTVertexShader.glInsertComponentEXT(res, src, num);
	}

	/**
	 * @param id
	 * @param cap
	 *
	 * @return
	 */
	public static boolean glIsVariantEnabledEXT(int id, int cap) {
		return EXTVertexShader.glIsVariantEnabledEXT(id, cap);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public static void glSetInvariantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		EXTVertexShader.glSetInvariantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public static void glSetInvariantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		EXTVertexShader.glSetInvariantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public static void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		EXTVertexShader.glSetInvariantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public static void glSetInvariantEXT(int id, FloatBuffer pAddr) {
		EXTVertexShader.glSetInvariantEXT(id, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public static void glSetLocalConstantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		EXTVertexShader.glSetLocalConstantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public static void glSetLocalConstantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		EXTVertexShader.glSetLocalConstantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public static void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		EXTVertexShader.glSetLocalConstantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public static void glSetLocalConstantEXT(int id, FloatBuffer pAddr) {
		EXTVertexShader.glSetLocalConstantEXT(id, pAddr);
	}

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 */
	public static void glShaderOp1EXT(int op, int res, int arg1) {
		EXTVertexShader.glShaderOp1EXT(op, res, arg1);
	}

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 */
	public static void glShaderOp2EXT(int op, int res, int arg1, int arg2) {
		EXTVertexShader.glShaderOp2EXT(op, res, arg1, arg2);
	}

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public static void glShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3) {
		EXTVertexShader.glShaderOp3EXT(op, res, arg1, arg2, arg3);
	}

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	public static void glSwizzleEXT(int res, int in, int outX, int outY, int outZ, int outW) {
		EXTVertexShader.glSwizzleEXT(res, in, outX, outY, outZ, outW);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public static void glVariantEXT(int id, ByteBuffer pAddr) {
		EXTVertexShader.glVariantEXT(id, pAddr);
	}

	/**
	 * @param id
	 * @param pfAddr
	 */
	public static void glVariantEXT(int id, FloatBuffer pfAddr) {
		EXTVertexShader.glVariantEXT(id, pfAddr);
	}

	/**
	 * @param id
	 * @param piAddr
	 */
	public static void glVariantEXT(int id, IntBuffer piAddr) {
		EXTVertexShader.glVariantEXT(id, piAddr);
	}

	/**
	 * @param id
	 * @param psAddr
	 */
	public static void glVariantEXT(int id, ShortBuffer psAddr) {
		EXTVertexShader.glVariantEXT(id, psAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ByteBuffer pAddr) {
		EXTVertexShader.glVariantPointerEXT(id, unsigned, stride, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, IntBuffer pAddr) {
		EXTVertexShader.glVariantPointerEXT(id, unsigned, stride, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr) {
		EXTVertexShader.glVariantPointerEXT(id, unsigned, stride, pAddr);
	}

	/**
	 * @param id
	 * @param stride
	 * @param pAddr
	 */
	public static void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr) {
		EXTVertexShader.glVariantPointerEXT(id, stride, pAddr);
	}

	/**
	 * @param id
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glVariantPointerEXT(int id, int type, int stride, int buffer_offset) {
		EXTVertexShader.glVariantPointerEXT(id, type, stride, buffer_offset);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public static void glVariantuEXT(int id, ByteBuffer pAddr) {
		EXTVertexShader.glVariantuEXT(id, pAddr);
	}

	/**
	 * @param id
	 * @param piAddr
	 */
	public static void glVariantuEXT(int id, IntBuffer piAddr) {
		EXTVertexShader.glVariantuEXT(id, piAddr);
	}

	/**
	 * @param id
	 * @param psAddr
	 */
	public static void glVariantuEXT(int id, ShortBuffer psAddr) {
		EXTVertexShader.glVariantuEXT(id, psAddr);
	}

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	public static void glWriteMaskEXT(int res, int in, int outX, int outY, int outZ, int outW) {
		EXTVertexShader.glWriteMaskEXT(res, in, outX, outY, outZ, outW);
	}

	/** @param weight  */
	public static void glVertexWeightfEXT(float weight) {
		EXTVertexWeighting.glVertexWeightfEXT(weight);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public static void glVertexWeightPointerEXT(int size, int stride, FloatBuffer pPointer) {
		EXTVertexWeighting.glVertexWeightPointerEXT(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glVertexWeightPointerEXT(int size, int type, int stride, int buffer_offset) {
		EXTVertexWeighting.glVertexWeightPointerEXT(size, type, stride, buffer_offset);
	}

	// ----------------------------------------------------------
	// ----------------------[ OpenGL 2.0 ]----------------------
	// ----------------------------------------------------------

	/**
	 * @param shader
	 * @param string
	 */
	public static void glShaderSource(int shader, ByteBuffer string) {
		GL20.glShaderSource(shader, string);
	}

	/**
	 * @param shader
	 * @param strings
	 */
	public static void glShaderSource(int shader, ByteBuffer[] strings) {
		GL20.glShaderSource(shader, strings);
	}

	/**
	 * @param type
	 *
	 * @return
	 */
	public static int glCreateShader(int type) {
		return GL20.glCreateShader(type);
	}

	/**
	 * @param shader
	 *
	 * @return
	 */
	public static boolean glIsShader(int shader) {
		return GL20.glIsShader(shader);
	}

	/** @param shader  */
	public static void glCompileShader(int shader) {
		GL20.glCompileShader(shader);
	}

	/** @param shader  */
	public static void glDeleteShader(int shader) {
		GL20.glDeleteShader(shader);
	}

	/** @return  */
	public static int glCreateProgram() {
		return GL20.glCreateProgram();
	}

	/**
	 * @param program
	 *
	 * @return
	 */
	public static boolean glIsProgram(int program) {
		return GL20.glIsProgram(program);
	}

	/**
	 * @param program
	 * @param shader
	 */
	public static void glAttachShader(int program, int shader) {
		GL20.glAttachShader(program, shader);
	}

	/**
	 * @param program
	 * @param shader
	 */
	public static void glDetachShader(int program, int shader) {
		GL20.glDetachShader(program, shader);
	}

	/** @param program  */
	public static void glLinkProgram(int program) {
		GL20.glLinkProgram(program);
	}

	/** @param program  */
	public static void glUseProgram(int program) {
		GL20.glUseProgram(program);
	}

	/** @param program  */
	public static void glValidateProgram(int program) {
		GL20.glValidateProgram(program);
	}

	/** @param program  */
	public static void glDeleteProgram(int program) {
		GL20.glDeleteProgram(program);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public static void glUniform1f(int location, float v0) {
		GL20.glUniform1f(location, v0);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public static void glUniform2f(int location, float v0, float v1) {
		GL20.glUniform2f(location, v0, v1);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public static void glUniform3f(int location, float v0, float v1, float v2) {
		GL20.glUniform3f(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public static void glUniform4f(int location, float v0, float v1, float v2, float v3) {
		GL20.glUniform4f(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public static void glUniform1i(int location, int v0) {
		GL20.glUniform1i(location, v0);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public static void glUniform2i(int location, int v0, int v1) {
		GL20.glUniform2i(location, v0, v1);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public static void glUniform3i(int location, int v0, int v1, int v2) {
		GL20.glUniform3i(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public static void glUniform4i(int location, int v0, int v1, int v2, int v3) {
		GL20.glUniform4i(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform1(int location, FloatBuffer values) {
		GL20.glUniform1(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform2(int location, FloatBuffer values) {
		GL20.glUniform2(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform3(int location, FloatBuffer values) {
		GL20.glUniform3(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform4(int location, FloatBuffer values) {
		GL20.glUniform4(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform1(int location, IntBuffer values) {
		GL20.glUniform1(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform2(int location, IntBuffer values) {
		GL20.glUniform2(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform3(int location, IntBuffer values) {
		GL20.glUniform3(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public static void glUniform4(int location, IntBuffer values) {
		GL20.glUniform4(location, values);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public static void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices) {
		GL20.glUniformMatrix2(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public static void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices) {
		GL20.glUniformMatrix3(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {
		GL20.glUniformMatrix4(location, transpose, matrices);
	}

	/**
	 * @param shader
	 * @param pname
	 * @param params
	 */
	public static void glGetShader(int shader, int pname, FloatBuffer params) {
		GL20.glGetShader(shader, pname, params);
	}

	/**
	 * @param shader
	 * @param pname
	 * @param params
	 */
	public static void glGetShader(int shader, int pname, IntBuffer params) {
		GL20.glGetShader(shader, pname, params);
	}

	/**
	 * @param program
	 * @param pname
	 * @param params
	 */
	public static void glGetProgram(int program, int pname, FloatBuffer params) {
		GL20.glGetProgram(program, pname, params);
	}

	/**
	 * @param program
	 * @param pname
	 * @param params
	 */
	public static void glGetProgram(int program, int pname, IntBuffer params) {
		GL20.glGetProgram(program, pname, params);
	}

	/**
	 * @param shader
	 * @param length
	 * @param infoLog
	 */
	public static void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
		GL20.glGetShaderInfoLog(shader, length, infoLog);
	}

	/**
	 * @param program
	 * @param length
	 * @param infoLog
	 */
	public static void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
		GL20.glGetProgramInfoLog(program, length, infoLog);
	}

	/**
	 * @param program
	 * @param count
	 * @param shaders
	 */
	public static void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
		GL20.glGetAttachedShaders(program, count, shaders);
	}

	/**
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	public static int glGetUniformLocation(int program, ByteBuffer name) {
		return GL20.glGetUniformLocation(program, name);
	}

	/**
	 * @param program
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public static void glGetActiveUniform(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		GL20.glGetActiveUniform(program, index, length, size, type, name);
	}

	/**
	 * @param program
	 * @param location
	 * @param params
	 */
	public static void glGetUniform(int program, int location, FloatBuffer params) {
		GL20.glGetUniform(program, location, params);
	}

	/**
	 * @param program
	 * @param location
	 * @param params
	 */
	public static void glGetUniform(int program, int location, IntBuffer params) {
		GL20.glGetUniform(program, location, params);
	}

	/**
	 * @param shader
	 * @param length
	 * @param source
	 */
	public static void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) {
		GL20.glGetShaderSource(shader, length, source);
	}

	/**
	 * @param program
	 * @param index
	 * @param name
	 */
	public static void glBindAttribLocation(int program, int index, ByteBuffer name) {
		GL20.glBindAttribLocation(program, index, name);
	}

	/**
	 * @param program
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public static void glGetActiveAttrib(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		GL20.glGetActiveAttrib(program, index, length, size, type, name);
	}

	/**
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	public static int glGetAttribLocation(int program, ByteBuffer name) {
		return GL20.glGetAttribLocation(program, name);
	}

	/** @param buffers  */
	public static void glDrawBuffers(IntBuffer buffers) {
		GL20.glDrawBuffers(buffers);
	}

	/**
	 * @param face
	 * @param func
	 * @param ref
	 * @param mask
	 */
	public static void glStencilFuncSeparate(int face, int func, int ref, int mask) {
		GL20.glStencilFuncSeparate(face, func, ref, mask);
	}

	/**
	 * @param face
	 * @param sfail
	 * @param dpfail
	 * @param dppass
	 */
	public static void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		GL20.glStencilOpSeparate(face, sfail, dpfail, dppass);
	}

}