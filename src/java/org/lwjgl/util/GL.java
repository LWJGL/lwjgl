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

package org.lwjgl.util;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;

/**
 * $Id$
 *
 * An extensible GL class that contains all the GL11 through GL15 methods.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class GL {
	/**
	 * C'tor
	 */
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
	/**
	 * @param i
	 */
	public static void glArrayElement(int i) {
		GL11.glArrayElement(i);
	}
	/**
	 * @param mode
	 */
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
	public static void glBitmap(int width, int height, float xorig,
			float yorig, float xmove, float ymove, ByteBuffer bitmap) {
		GL11.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap);
	}
	/**
	 * @param sfactor
	 * @param dfactor
	 */
	public static void glBlendFunc(int sfactor, int dfactor) {
		GL11.glBlendFunc(sfactor, dfactor);
	}
	/**
	 * @param list
	 */
	public static void glCallList(int list) {
		GL11.glCallList(list);
	}
	/**
	 * @param lists
	 */
	public static void glCallLists(ByteBuffer lists) {
		GL11.glCallLists(lists);
	}
	/**
	 * @param n
	 * @param lists
	 */
	public static void glCallLists(int n, IntBuffer lists) {
		GL11.glCallLists(n, lists);
	}
	/**
	 * @param lists
	 */
	public static void glCallLists(ShortBuffer lists) {
		GL11.glCallLists(lists);
	}
	/**
	 * @param mask
	 */
	public static void glClear(int mask) {
		GL11.glClear(mask);
	}
	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glClearAccum(float red, float green, float blue,
			float alpha) {
		GL11.glClearAccum(red, green, blue, alpha);
	}
	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public static void glClearColor(float red, float green, float blue,
			float alpha) {
		GL11.glClearColor(red, green, blue, alpha);
	}
	/**
	 * @param depth
	 */
	public static void glClearDepth(double depth) {
		GL11.glClearDepth(depth);
	}
	/**
	 * @param c
	 */
	public static void glClearIndex(float c) {
		GL11.glClearIndex(c);
	}
	/**
	 * @param s
	 */
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
	public static void glColorMask(boolean red, boolean green, boolean blue,
			boolean alpha) {
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
	public static void glColorPointer(int size, boolean unsigned, int stride,
			ByteBuffer pointer) {
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
	public static void glColorPointer(int size, int type, int stride,
			int buffer_offset) {
		GL11.glColorPointer(size, type, stride, buffer_offset);
	}
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param type
	 */
	public static void glCopyPixels(int x, int y, int width, int height,
			int type) {
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
	public static void glCopyTexImage1D(int target, int level,
			int internalFormat, int x, int y, int width, int border) {
		GL11.glCopyTexImage1D(target, level, internalFormat, x, y, width,
				border);
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
	public static void glCopyTexImage2D(int target, int level,
			int internalFormat, int x, int y, int width, int height, int border) {
		GL11.glCopyTexImage2D(target, level, internalFormat, x, y, width,
				height, border);
	}
	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void glCopyTexSubImage1D(int target, int level, int xoffset,
			int x, int y, int width) {
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
	public static void glCopyTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int x, int y, int width, int height) {
		GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width,
				height);
	}
	/**
	 * @param mode
	 */
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
	/**
	 * @param textures
	 */
	public static void glDeleteTextures(IntBuffer textures) {
		GL11.glDeleteTextures(textures);
	}
	/**
	 * @param func
	 */
	public static void glDepthFunc(int func) {
		GL11.glDepthFunc(func);
	}
	/**
	 * @param flag
	 */
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
	/**
	 * @param cap
	 */
	public static void glDisable(int cap) {
		GL11.glDisable(cap);
	}
	/**
	 * @param cap
	 */
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
	/**
	 * @param mode
	 */
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
	public static void glDrawElements(int mode, int count, int type,
			int buffer_offset) {
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
	public static void glDrawPixels(int width, int height, int format,
			int type, ByteBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}
	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glDrawPixels(int width, int height, int format,
			int type, IntBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}
	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glDrawPixels(int width, int height, int format,
			int type, ShortBuffer pixels) {
		GL11.glDrawPixels(width, height, format, type, pixels);
	}
	/**
	 * @param flag
	 */
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
	/**
	 * @param cap
	 */
	public static void glEnable(int cap) {
		GL11.glEnable(cap);
	}
	/**
	 * @param cap
	 */
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
	/**
	 * @param u
	 */
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
	/**
	 * @param i
	 */
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
	/**
	 * @param mode
	 */
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
	public static void glFrustum(double left, double right, double bottom,
			double top, double zNear, double zFar) {
		GL11.glFrustum(left, right, bottom, top, zNear, zFar);
	}
	/**
	 * @param range
	 * @return
	 */
	public static int glGenLists(int range) {
		return GL11.glGenLists(range);
	}
	/**
	 * @param textures
	 */
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
	/**
	 * @return
	 */
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
	 * @param values
	 */
	public static void glGetPixelMap(int map, IntBuffer values) {
		GL11.glGetPixelMap(map, values);
	}
	/**
	 * @param map
	 * @param values
	 */
	public static void glGetPixelMap(int map, ShortBuffer values) {
		GL11.glGetPixelMap(map, values);
	}
	/**
	 * @param pname
	 * @param size
	 * @return
	 */
	public static ByteBuffer glGetPointerv(int pname, int size) {
		return GL11.glGetPointerv(pname, size);
	}
	/**
	 * @param mask
	 */
	public static void glGetPolygonStipple(ByteBuffer mask) {
		GL11.glGetPolygonStipple(mask);
	}
	/**
	 * @param name
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
	public static void glGetTexImage(int target, int level, int format,
			int type, ByteBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glGetTexImage(int target, int level, int format,
			int type, IntBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public static void glGetTexImage(int target, int level, int format,
			int type, ShortBuffer pixels) {
		GL11.glGetTexImage(target, level, format, type, pixels);
	}
	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public static void glGetTexLevelParameter(int target, int level, int pname,
			FloatBuffer params) {
		GL11.glGetTexLevelParameter(target, level, pname, params);
	}
	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public static void glGetTexLevelParameter(int target, int level, int pname,
			IntBuffer params) {
		GL11.glGetTexLevelParameter(target, level, pname, params);
	}
	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetTexParameter(int target, int pname,
			FloatBuffer params) {
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
	public static void glInterleavedArrays(int format, int stride,
			ByteBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public static void glInterleavedArrays(int format, int stride,
			FloatBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	/**
	 * @param format
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glInterleavedArrays(int format, int stride,
			int buffer_offset) {
		GL11.glInterleavedArrays(format, stride, buffer_offset);
	}
	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public static void glInterleavedArrays(int format, int stride,
			IntBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public static void glInterleavedArrays(int format, int stride,
			ShortBuffer pointer) {
		GL11.glInterleavedArrays(format, stride, pointer);
	}
	/**
	 * @param cap
	 * @return
	 */
	public static boolean glIsEnabled(int cap) {
		return GL11.glIsEnabled(cap);
	}
	/**
	 * @param list
	 * @return
	 */
	public static boolean glIsList(int list) {
		return GL11.glIsList(list);
	}
	/**
	 * @param texture
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
	/**
	 * @param width
	 */
	public static void glLineWidth(float width) {
		GL11.glLineWidth(width);
	}
	/**
	 * @param base
	 */
	public static void glListBase(int base) {
		GL11.glListBase(base);
	}
	/**
	 * 
	 */
	public static void glLoadIdentity() {
		GL11.glLoadIdentity();
	}
	/**
	 * @param m
	 */
	public static void glLoadMatrix(FloatBuffer m) {
		GL11.glLoadMatrix(m);
	}
	/**
	 * @param name
	 */
	public static void glLoadName(int name) {
		GL11.glLoadName(name);
	}
	/**
	 * @param opcode
	 */
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
	public static void glMap1f(int target, float u1, float u2, int stride,
			int order, FloatBuffer points) {
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
	public static void glMap2f(int target, float u1, float u2, int ustride,
			int uorder, float v1, float v2, int vstride, int vorder,
			FloatBuffer points) {
		GL11.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder,
				points);
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
	public static void glMapGrid2f(int un, float u1, float u2, int vn,
			float v1, float v2) {
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
	/**
	 * @param mode
	 */
	public static void glMatrixMode(int mode) {
		GL11.glMatrixMode(mode);
	}
	/**
	 * @param m
	 */
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
	public static void glOrtho(double left, double right, double bottom,
			double top, double zNear, double zFar) {
		GL11.glOrtho(left, right, bottom, top, zNear, zFar);
	}
	/**
	 * @param token
	 */
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
	 * @param values
	 */
	public static void glPixelMap(int map, IntBuffer values) {
		GL11.glPixelMap(map, values);
	}
	/**
	 * @param map
	 * @param values
	 */
	public static void glPixelMap(int map, ShortBuffer values) {
		GL11.glPixelMap(map, values);
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
	/**
	 * @param size
	 */
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
	/**
	 * @param mask
	 */
	public static void glPolygonStipple(ByteBuffer mask) {
		GL11.glPolygonStipple(mask);
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
	/**
	 * @param mask
	 */
	public static void glPushAttrib(int mask) {
		GL11.glPushAttrib(mask);
	}
	/**
	 * @param mask
	 */
	public static void glPushClientAttrib(int mask) {
		GL11.glPushClientAttrib(mask);
	}
	/**
	 * 
	 */
	public static void glPushMatrix() {
		GL11.glPushMatrix();
	}
	/**
	 * @param name
	 */
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
	/**
	 * @param mode
	 */
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
	public static void glReadPixels(int x, int y, int width, int height,
			int format, int type, ByteBuffer pixels) {
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
	public static void glReadPixels(int x, int y, int width, int height,
			int format, int type, IntBuffer pixels) {
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
	public static void glReadPixels(int x, int y, int width, int height,
			int format, int type, ShortBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
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
	/**
	 * @param buffer
	 */
	public static void glSelectBuffer(IntBuffer buffer) {
		GL11.glSelectBuffer(buffer);
	}
	/**
	 * @param mode
	 */
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
	/**
	 * @param mask
	 */
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
	/**
	 * @param s
	 */
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
	public static void glTexCoordPointer(int size, int stride,
			FloatBuffer pointer) {
		GL11.glTexCoordPointer(size, stride, pointer);
	}
	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glTexCoordPointer(int size, int type, int stride,
			int buffer_offset) {
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
	public static void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, ByteBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format,
				type, pixels);
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
	public static void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, FloatBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format,
				type, pixels);
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
	public static void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, IntBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format,
				type, pixels);
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
	public static void glTexImage1D(int target, int level, int internalformat,
			int width, int border, int format, int type, ShortBuffer pixels) {
		GL11.glTexImage1D(target, level, internalformat, width, border, format,
				type, pixels);
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
	public static void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			ByteBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border,
				format, type, pixels);
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
	public static void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			FloatBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border,
				format, type, pixels);
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
	public static void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			IntBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border,
				format, type, pixels);
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
	public static void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			ShortBuffer pixels) {
		GL11.glTexImage2D(target, level, internalformat, width, height, border,
				format, type, pixels);
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
	public static void glTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int type, ByteBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type,
				pixels);
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
	public static void glTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int type, IntBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type,
				pixels);
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
	public static void glTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int type, ShortBuffer pixels) {
		GL11.glTexSubImage1D(target, level, xoffset, width, format, type,
				pixels);
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
	public static void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			ByteBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height,
				format, type, pixels);
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
	public static void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			IntBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height,
				format, type, pixels);
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
	public static void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			ShortBuffer pixels) {
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height,
				format, type, pixels);
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
	public static void glVertexPointer(int size, int type, int stride,
			int buffer_offset) {
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
	public static void glCopyTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int x, int y, int width, int height) {
		GL12.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x,
				y, width, height);
	}
	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public static void glDrawRangeElements(int mode, int start, int end,
			ByteBuffer indices) {
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
	public static void glDrawRangeElements(int mode, int start, int end,
			int count, int type, int buffer_offset) {
		GL12.glDrawRangeElements(mode, start, end, count, type, buffer_offset);
	}
	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public static void glDrawRangeElements(int mode, int start, int end,
			IntBuffer indices) {
		GL12.glDrawRangeElements(mode, start, end, indices);
	}
	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public static void glDrawRangeElements(int mode, int start, int end,
			ShortBuffer indices) {
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
	public static void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			ByteBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth,
				border, format, type, pixels);
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
	public static void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			FloatBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth,
				border, format, type, pixels);
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
	public static void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			IntBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth,
				border, format, type, pixels);
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
	public static void glTexImage3D(int target, int level, int internalFormat,
			int width, int height, int depth, int border, int format, int type,
			ShortBuffer pixels) {
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth,
				border, format, type, pixels);
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
	public static void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, ByteBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width,
				height, depth, format, type, pixels);
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
	public static void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, FloatBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width,
				height, depth, format, type, pixels);
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
	public static void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, IntBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width,
				height, depth, format, type, pixels);
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
	public static void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, ShortBuffer pixels) {
		GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width,
				height, depth, format, type, pixels);
	}
	/**
	 * @param texture
	 */
	public static void glActiveTexture(int texture) {
		GL13.glActiveTexture(texture);
	}
	/**
	 * @param texture
	 */
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
	public static void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			ByteBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width,
				border, imageSize, data);
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
	public static void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			FloatBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width,
				border, imageSize, data);
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
	public static void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			IntBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width,
				border, imageSize, data);
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
	public static void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			ShortBuffer data) {
		GL13.glCompressedTexImage1D(target, level, internalformat, width,
				border, imageSize, data);
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
	public static void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, ByteBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width,
				height, border, imageSize, data);
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
	public static void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, FloatBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width,
				height, border, imageSize, data);
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
	public static void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, IntBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width,
				height, border, imageSize, data);
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
	public static void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, ShortBuffer data) {
		GL13.glCompressedTexImage2D(target, level, internalformat, width,
				height, border, imageSize, data);
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
	public static void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, ByteBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width,
				height, depth, border, imageSize, data);
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
	public static void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, FloatBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width,
				height, depth, border, imageSize, data);
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
	public static void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, IntBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width,
				height, depth, border, imageSize, data);
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
	public static void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, ShortBuffer data) {
		GL13.glCompressedTexImage3D(target, level, internalformat, width,
				height, depth, border, imageSize, data);
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
	public static void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format,
				imageSize, data);
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
	public static void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format,
				imageSize, data);
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
	public static void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, IntBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format,
				imageSize, data);
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
	public static void glCompressedTexSubImage1D(int target, int level,
			int xoffset, int width, int format, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format,
				imageSize, data);
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
	public static void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, ByteBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, imageSize, data);
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
	public static void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, FloatBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, imageSize, data);
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
	public static void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, IntBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, imageSize, data);
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
	public static void glCompressedTexSubImage2D(int target, int level,
			int xoffset, int yoffset, int width, int height, int format,
			int imageSize, ShortBuffer data) {
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, imageSize, data);
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
	public static void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, ByteBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset,
				zoffset, width, height, depth, format, imageSize, data);
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
	public static void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, FloatBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset,
				zoffset, width, height, depth, format, imageSize, data);
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
	public static void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, IntBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset,
				zoffset, width, height, depth, format, imageSize, data);
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
	public static void glCompressedTexSubImage3D(int target, int level,
			int xoffset, int yoffset, int zoffset, int width, int height,
			int depth, int format, int imageSize, ShortBuffer data) {
		GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset,
				zoffset, width, height, depth, format, imageSize, data);
	}
	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public static void glGetCompressedTexImage(int target, int lod,
			ByteBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}
	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public static void glGetCompressedTexImage(int target, int lod,
			IntBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}
	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public static void glGetCompressedTexImage(int target, int lod,
			ShortBuffer img) {
		GL13.glGetCompressedTexImage(target, lod, img);
	}
	/**
	 * @param m
	 */
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
	public static void glMultiTexCoord4f(int target, float s, float t, float r,
			float q) {
		GL13.glMultiTexCoord4f(target, s, t, r, q);
	}
	/**
	 * @param m
	 */
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
	public static void glBlendColor(float red, float green, float blue,
			float alpha) {
		GL14.glBlendColor(red, green, blue, alpha);
	}
	/**
	 * @param mode
	 */
	public static void glBlendEquation(int mode) {
		GL14.glBlendEquation(mode);
	}
	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		GL14.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}
	/**
	 * @param coord
	 */
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
	public static void glMultiDrawArrays(int mode, IntBuffer piFirst,
			IntBuffer piCount) {
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
	public static void glSecondaryColorPointer(int size, boolean unsigned,
			int stride, ByteBuffer data) {
		GL14.glSecondaryColorPointer(size, unsigned, stride, data);
	}
	/**
	 * @param size
	 * @param stride
	 * @param data
	 */
	public static void glSecondaryColorPointer(int size, int stride,
			FloatBuffer data) {
		GL14.glSecondaryColorPointer(size, stride, data);
	}
	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public static void glSecondaryColorPointer(int size, int type, int stride,
			int buffer_offset) {
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
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, int size, ByteBuffer data,
			int usage) {
		GL15.glBufferData(target, size, data, usage);
	}
	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, int size, FloatBuffer data,
			int usage) {
		GL15.glBufferData(target, size, data, usage);
	}
	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, int size, IntBuffer data,
			int usage) {
		GL15.glBufferData(target, size, data, usage);
	}
	/**
	 * @param target
	 * @param size
	 * @param data
	 * @param usage
	 */
	public static void glBufferData(int target, int size, ShortBuffer data,
			int usage) {
		GL15.glBufferData(target, size, data, usage);
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
	/**
	 * @param buffers
	 */
	public static void glDeleteBuffers(IntBuffer buffers) {
		GL15.glDeleteBuffers(buffers);
	}
	/**
	 * @param ids
	 */
	public static void glDeleteQueries(IntBuffer ids) {
		GL15.glDeleteQueries(ids);
	}
	/**
	 * @param target
	 */
	public static void glEndQuery(int target) {
		GL15.glEndQuery(target);
	}
	/**
	 * @param buffers
	 */
	public static void glGenBuffers(IntBuffer buffers) {
		GL15.glGenBuffers(buffers);
	}
	/**
	 * @param ids
	 */
	public static void glGenQueries(IntBuffer ids) {
		GL15.glGenQueries(ids);
	}
	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public static void glGetBufferParameter(int target, int pname,
			IntBuffer params) {
		GL15.glGetBufferParameter(target, pname, params);
	}
	/**
	 * @param target
	 * @param pname
	 * @param size
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
	public static void glGetBufferSubData(int target, int offset,
			ByteBuffer data) {
		GL15.glGetBufferSubData(target, offset, data);
	}
	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public static void glGetBufferSubData(int target, int offset,
			FloatBuffer data) {
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
	public static void glGetBufferSubData(int target, int offset,
			ShortBuffer data) {
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
	 * @return
	 */
	public static boolean glIsBuffer(int buffer) {
		return GL15.glIsBuffer(buffer);
	}
	/**
	 * @param id
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
	 * @return
	 */
	public static ByteBuffer glMapBuffer(int target, int access, int size,
			ByteBuffer oldBuffer) {
		return GL15.glMapBuffer(target, access, size, oldBuffer);
	}
	/**
	 * @param target
	 * @return
	 */
	public static boolean glUnmapBuffer(int target) {
		return GL15.glUnmapBuffer(target);
	}
}
