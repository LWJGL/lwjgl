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

import java.nio.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.Buffer;

/**
 * $Id: CoreGL.java,v 1.23 2003/07/23 14:51:19 elias_naur Exp $
 *
 * The core OpenGL1.1 API.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision: 1.23 $
 */
public class CoreGL11 implements CoreGL11Constants {
	
	public static native void glAccum(int op, float value);
	public static native void glAlphaFunc(int func, float ref);
	public static native void glClearColor(float red, float green, float blue, float alpha);
	public static native void glClearAccum(float red, float green, float blue, float alpha);
	public static native void glClear(int mask);
	public static void glCallLists(ByteBuffer lists) {
		nglCallLists(lists.remaining(), GL_UNSIGNED_BYTE, lists, lists.position());
	}
	public static void glCallLists(ShortBuffer lists) {
		nglCallLists(lists.remaining(), GL_UNSIGNED_SHORT, lists, lists.position() << 1);
	}
	public static void glCallLists(int n, IntBuffer lists) {
		nglCallLists(lists.remaining(), GL_UNSIGNED_INT, lists, lists.position() << 2);
	}
	private static native void nglCallLists(int n, int type, Buffer lists, int lists_offset);
	public static native void glCallList(int list);
	public static native void glBlendFunc(int sfactor, int dfactor);
	public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap) {
		nglBitmap(width, height, xorig, yorig, xmove, ymove, bitmap, bitmap.position());
	}
	private static native void nglBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap, int bitmap_offset);
	public static native void glBindTexture(int target, int texture);
	public static native void glBegin(int mode);
	public static native void glEnd();
	public static native void glArrayElement(int i);
	public static native void glClearDepth(double depth);
	public static native void glDeleteLists(int list, int range);
	public static void glDeleteTextures(IntBuffer textures) {
		nglDeleteTextures(textures.remaining(), textures, textures.position());
	}
	private static native void nglDeleteTextures(int n, IntBuffer textures, int textures_offset);
	public static native void glCullFace(int mode);
	public static native void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);
	public static native void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width);
	public static native void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border);
	public static native void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border);
	public static native void glCopyPixels(int x, int y, int width, int height, int type);
	public static void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer) {
		nglColorPointer(size, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, stride, pointer, pointer.position());
	}
	public static void glColorPointer(int size, int stride, FloatBuffer pointer) {
		nglColorPointer(size, GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglColorPointer(int size, int type, int stride, Buffer pointer, int pointer_offset);
	public static native void glColorMaterial(int face, int mode);
	public static native void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);
	public static native void glColor3b(byte red, byte green, byte blue);
	public static native void glColor3f(float red, float green, float blue);
	public static native void glColor3ub(byte red, byte green, byte blue);
	public static native void glColor4b(byte red, byte green, byte blue, byte alpha);
	public static native void glColor4f(float red, float green, float blue, float alpha);
	public static native void glColor4ub(byte red, byte green, byte blue, byte alpha);
	public static void glClipPlane(int plane, DoubleBuffer equation) {
		nglClipPlane(plane, equation, equation.position() << 3);
	}
	private static native void nglClipPlane(int plane, DoubleBuffer equation, int equation_offset);
	public static native void glClearStencil(int s);
	public static native void glClearIndex(float c);
	public static native void glEvalPoint1(int i);
	public static native void glEvalPoint2(int i, int j);
	public static native void glEvalMesh1(int mode, int i1, int i2);
	public static native void glEvalMesh2(int mode, int i1, int i2, int j1, int j2);
	public static native void glEvalCoord1f(float u);
	public static native void glEvalCoord2f(float u, float v);
	public static native void glEnableClientState(int cap);
	public static native void glDisableClientState(int cap);
	public static native void glEnable(int cap);
	public static native void glDisable(int cap);
	public static void glEdgeFlagPointer(int stride, ByteBuffer pointer) {
		nglEdgeFlagPointer(stride, pointer, pointer.position());
	}
	private static native void nglEdgeFlagPointer(int stride, Buffer pointer, int pointer_offset);
	public static native void glEdgeFlag(boolean flag);
	public static void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) {
		nglDrawPixels(width, height, format, type, pixels, pixels.position());
	}
	public static void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) {
		nglDrawPixels(width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) {
		nglDrawPixels(width, height, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglDrawPixels(int width, int height, int format, int type, Buffer pixels, int pixels_offset);
	public static void glDrawElements(int mode, ByteBuffer indices) {
		nglDrawElements(mode, indices.remaining(), GL_UNSIGNED_BYTE, indices, indices.position());
	}
	public static void glDrawElements(int mode, ShortBuffer indices) {
		nglDrawElements(mode, indices.remaining(), GL_UNSIGNED_SHORT, indices, indices.position() << 1);
	}
	public static void glDrawElements(int mode, IntBuffer indices) {
		nglDrawElements(mode, indices.remaining(), GL_UNSIGNED_INT, indices, indices.position() << 2);
	}
	private static native void nglDrawElements(int mode, int count, int type, Buffer indices, int indices_offset);
	public static native void glDrawBuffer(int mode);
	public static native void glDrawArrays(int mode, int first, int count);
	public static native void glDepthRange(double zNear, double zFar);
	public static native void glDepthMask(boolean flag);
	public static native void glDepthFunc(int func);
	public static void glFeedbackBuffer(int type, FloatBuffer buffer) {
		nglFeedbackBuffer(buffer.remaining(), type, buffer, buffer.position());
	}
	private static native void nglFeedbackBuffer(int size, int type, FloatBuffer buffer, int buffer_offset);
	public static void glGetPixelMap(int map, FloatBuffer values) {
		nglGetPixelMapfv(map, values, values.position());
	}
	private static native void nglGetPixelMapfv(int map, FloatBuffer values, int values_offset);
	public static void glGetPixelMap(int map, IntBuffer values) {
		nglGetPixelMapuiv(map, values, values.position());
	}
	private static native void nglGetPixelMapuiv(int map, IntBuffer values, int values_offset);
	public static void glGetPixelMap(int map, ShortBuffer values) {
		nglGetPixelMapusv(map, values, values.position());
	}
	private static native void nglGetPixelMapusv(int map, ShortBuffer values, int values_offset);
	public static void glGetMaterial(int face, int pname, FloatBuffer params) {
		nglGetMaterialfv(face, pname, params, params.position());
	}
	private static native void nglGetMaterialfv(int face, int pname, FloatBuffer params, int params_offset);
	public static void glGetMaterial(int face, int pname, IntBuffer params) {
		nglGetMaterialiv(face, pname, params, params.position());
	}
	private static native void nglGetMaterialiv(int face, int pname, IntBuffer params, int params_offset);
	public static void glGetMap(int target, int query, FloatBuffer v) {
		nglGetMapfv(target, query, v, v.position());
	}
	public static void glGetMap(int target, int query, IntBuffer v) {
		nglGetMapiv(target, query, v, v.position());
	}
	private static native void nglGetMapfv(int target, int query, FloatBuffer v, int v_offset);
	private static native void nglGetMapiv(int target, int query, IntBuffer v, int v_offset);
	public static void glGetLight(int light, int pname, FloatBuffer params) {
		nglGetLightfv(light, pname, params, params.position());
	}
	private static native void nglGetLightfv(int light, int pname, FloatBuffer params, int params_offset);
	public static void glGetLight(int light, int pname, IntBuffer params) {
		nglGetLightiv(light, pname, params, params.position());
	}
	private static native void nglGetLightiv(int light, int pname, IntBuffer params, int params_offset);
	public static native int glGetError();
	public static void glGetClipPlane(int plane, DoubleBuffer equation) {
		nglGetClipPlane(plane, equation, equation.position());
	}
	private static native void nglGetClipPlane(int plane, DoubleBuffer equation, int equation_offset);
	public static void glGetBoolean(int pname, ByteBuffer params) {
		nglGetBooleanv(pname, params, params.position());
	}
	private static native void nglGetBooleanv(int pname, ByteBuffer params, int params_offset);
	public static void glGetDouble(int pname, DoubleBuffer params) {
		nglGetDoublev(pname, params, params.position());
	}
	private static native void nglGetDoublev(int pname, DoubleBuffer params, int params_offset);
	public static void glGetFloat(int pname, FloatBuffer params) {
		nglGetFloatv(pname, params, params.position());
	}
	private static native void nglGetFloatv(int pname, FloatBuffer params, int params_offset);
	public static void glGetInteger(int pname, IntBuffer params) {
		nglGetIntegerv(pname, params, params.position());
	}
	private static native void nglGetIntegerv(int pname, IntBuffer params, int params_offset);
	public static void glGenTextures(IntBuffer textures) {
		nglGenTextures(textures.remaining(), textures, textures.position());
	}
	private static native void nglGenTextures(int n, IntBuffer textures, int textures_offset);
	public static native int glGenLists(int range);
	public static native void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar);
	public static native void glFrontFace(int mode);
	public static native void glFogf(int pname, float param);
	public static native void glFogi(int pname, int param);
	public static void glFog(int pname, FloatBuffer params) {
		nglFogfv(pname, params, params.position());
	}
	private static native void nglFogfv(int pname, FloatBuffer params, int params_offset);
	public static void glFog(int pname, IntBuffer params) {
		nglFogiv(pname, params, params.position());
	}
	private static native void nglFogiv(int pname, IntBuffer params, int params_offset);
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
	public static void glInterleavedArrays(int format, int stride, ByteBuffer pointer) {
		nglInterleavedArrays(format, stride, pointer, pointer.position());
	}
	public static void glInterleavedArrays(int format, int stride, ShortBuffer pointer) {
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 1);
	}
	public static void glInterleavedArrays(int format, int stride, IntBuffer pointer) {
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 2);
	}
	public static void glInterleavedArrays(int format, int stride, FloatBuffer pointer) {
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 2);
	}
	private static native void nglInterleavedArrays(int format, int stride, Buffer pointer, int pointer_offset);
	public static native void glInitNames();
	public static native void glHint(int target, int mode);
	public static void glGetTexParameter(int target, int pname, FloatBuffer params) {
		nglGetTexParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetTexParameterfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glGetTexParameter(int target, int pname, IntBuffer params) {
		nglGetTexParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetTexParameteriv(int target, int pname, IntBuffer params, int params_offset);
	public static void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params) {
		nglGetTexLevelParameterfv(target, level, pname, params, params.position());
	}
	private static native void nglGetTexLevelParameterfv(int target, int level, int pname, FloatBuffer params, int params_offset);
	public static void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params) {
		nglGetTexLevelParameteriv(target, level, pname, params, params.position());
	}
	private static native void nglGetTexLevelParameteriv(int target, int level, int pname, IntBuffer params, int params_offset);
	public static void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
		nglGetTexImage(target, level, format, type, pixels, pixels.position());
	}
	public static void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels) {
		nglGetTexImage(target, level, format, type, pixels, pixels.position() << 1);
	}
	public static void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) {
		nglGetTexImage(target, level, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglGetTexImage(int target, int level, int format, int type, Buffer pixels, int pixels_offset);
	
	public static void glGetTexGen(int coord, int pname, FloatBuffer params) {
		nglGetTexGenfv(coord, pname, params, params.position());
	}
	private static native void nglGetTexGenfv(int coord, int pname, FloatBuffer params, int params_offset);

	public static void glGetTexEnv(int coord, int pname, IntBuffer params) {
		nglGetTexEnviv(coord, pname, params, params.position());
	}
	private static native void nglGetTexEnviv(int coord, int pname, IntBuffer params, int params_offset);
	public static void glGetTexEnv(int coord, int pname, FloatBuffer params) {
		nglGetTexEnvfv(coord, pname, params, params.position());
	}
	private static native void nglGetTexEnvfv(int coord, int pname, FloatBuffer params, int params_offset);

	public static native String glGetString(int name);
	public static void glGetPolygonStipple(ByteBuffer mask) {
		nglGetPolygonStipple(mask, mask.position());
	}
	private static native void nglGetPolygonStipple(ByteBuffer mask, int mask_offset);
	public static native boolean glIsList(int list);
	public static native void glMaterialf(int face, int pname, float param);
	public static native void glMateriali(int face, int pname, int param);
	public static void glMaterial(int face, int pname, FloatBuffer params) {
		nglMaterialfv(face, pname, params, params.position());
	}
	private static native void nglMaterialfv(int face, int pname, FloatBuffer params, int params_offset);
	public static void glMaterial(int face, int pname, IntBuffer params) {
		nglMaterialiv(face, pname, params, params.position());
	}
	private static native void nglMaterialiv(int face, int pname, IntBuffer params, int params_offset);
	public static native void glMapGrid1f(int un, float u1, float u2);
	public static native void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);
	public static void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
		nglMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points, points.position());
	}
	private static native void nglMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points, int points_offset);
	public static void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) {
		nglMap1f(target, u1, u2, stride, order, points, points.position());
	}
	private static native void nglMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points, int points_offset);
	public static native void glLogicOp(int opcode);
	public static native void glLoadName(int name);
	public static void glLoadMatrixf(FloatBuffer m) {
		nglLoadMatrixf(m, m.position());
	}
	private static native void nglLoadMatrixf(FloatBuffer m, int m_offset);
	public static native void glLoadIdentity();
	public static native void glListBase(int base);
	public static native void glLineWidth(float width);
	public static native void glLineStipple(int factor, short pattern);
	public static native void glLightModelf(int pname, float param);
	public static native void glLightModeli(int pname, int param);
	public static void glLightModel(int pname, FloatBuffer params) {
		nglLightModelfv( pname, params, params.position());
	}
	private static native void nglLightModelfv(int pname, FloatBuffer params, int params_offset);
	public static void glLightModel(int pname, IntBuffer params) {
		nglLightModeliv(pname, params, params.position());
	}
	private static native void nglLightModeliv(int pname, IntBuffer params, int params_offset);
	public static native void glLightf(int light, int pname, float param);
	public static native void glLighti(int light, int pname, int param);
	public static void glLightfv(int light, int pname, FloatBuffer params) {
		nglLightfv(light, pname, params, params.position());
	}
	private static native void nglLightfv(int light, int pname, FloatBuffer params, int params_offset);
	public static void glLightiv(int light, int pname, IntBuffer params) {
		nglLightiv(light, pname, params, params.position());
	}
	private static native void nglLightiv(int light, int pname, IntBuffer params, int params_offset);
	public static native boolean glIsTexture(int texture);
	public static native void glMatrixMode(int mode);
	public static void glPolygonStipple(ByteBuffer mask) {
		nglPolygonStipple(mask, mask.position());
	}
	private static native void nglPolygonStipple(ByteBuffer mask, int mask_offset);
	public static native void glPolygonOffset(float factor, float units);
	public static native void glPolygonMode(int face, int mode);
	public static native void glPointSize(float size);
	public static native void glPixelZoom(float xfactor, float yfactor);
	public static native void glPixelTransferf(int pname, float param);
	public static native void glPixelTransferi(int pname, int param);
	public static native void glPixelStoref(int pname, float param);
	public static native void glPixelStorei(int pname, int param);
	public static void glPixelMap(int map, FloatBuffer values) {
		nglPixelMapfv(map, values.remaining(), values, values.position());
	}
	private static native void nglPixelMapfv(int map, int mapsize, FloatBuffer values, int values_offset);
	public static void glPixelMap(int map, IntBuffer values) {
		nglPixelMapuiv(map, values.remaining(), values, values.position());
	}
	private static native void nglPixelMapuiv(int map, int mapsize, IntBuffer values, int values_offset);
	public static void glPixelMap(int map, ShortBuffer values) {
		nglPixelMapusv(map, values.remaining(), values, values.position());
	}
	private static native void nglPixelMapusv(int map, int mapsize, ShortBuffer values, int values_offset);
	public static native void glPassThrough(float token);
	public static native void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar);
	public static void glNormalPointer(int stride, ByteBuffer pointer) {
		nglNormalPointer(GL_BYTE, stride, pointer, pointer.position());
	}
	public static void glNormalPointer(int stride, IntBuffer pointer) {
		nglNormalPointer(GL_INT, stride, pointer, pointer.position() << 2);
	}
	public static void glNormalPointer(int stride, FloatBuffer pointer) {
		nglNormalPointer(GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglNormalPointer(int type, int stride, Buffer pointer, int pointer_offset);
	public static native void glNormal3b(byte nx, byte ny, byte nz);
	public static native void glNormal3f(float nx, float ny, float nz);
	public static native void glNormal3i(int nx, int ny, int nz);
	public static native void glNewList(int list, int mode);
	public static native void glEndList();
	public static void glMultMatrixf(FloatBuffer m) {
		nglMultMatrixf(m, m.position());
	}
	private static native void nglMultMatrixf(FloatBuffer m, int m_offset);
	public static native void glShadeModel(int mode);
	public static void glSelectBuffer(IntBuffer buffer) {
		nglSelectBuffer(buffer.remaining(), buffer, buffer.position());
	}
	private static native void nglSelectBuffer(int size, IntBuffer buffer, int buffer_offset);
	public static native void glScissor(int x, int y, int width, int height);
	public static native void glScalef(float x, float y, float z);
	public static native void glRotatef(float angle, float x, float y, float z);
	public static native int glRenderMode(int mode);
	public static native void glRectf(float x1, float y1, float x2, float y2);
	public static native void glRecti(int x1, int y1, int x2, int y2);
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position());
	}
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels, int pixels_offset);
	public static native void glReadBuffer(int mode);
	public static native void glRasterPos2f(float x, float y);
	public static native void glRasterPos2i(int x, int y);
	public static native void glRasterPos3f(float x, float y, float z);
	public static native void glRasterPos3i(int x, int y, int z);
	public static native void glRasterPos4f(float x, float y, float z, float w);
	public static native void glRasterPos4i(int x, int y, int z, int w);
	public static native void glPushName(int name);
	public static native void glPopName();
	public static native void glPushMatrix();
	public static native void glPopMatrix();
	public static native void glPushClientAttrib(int mask);
	public static native void glPopClientAttrib();
	public static native void glPushAttrib(int mask);
	public static native void glPopAttrib();
	public static native void glStencilFunc(int func, int ref, int mask);
	public static void glVertexPointer(int size, int stride, FloatBuffer pointer) {
		nglVertexPointer(size, GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	public static void glVertexPointer(int size, int stride, IntBuffer pointer) {
		nglVertexPointer(size, GL_INT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglVertexPointer(int size, int type, int stride, Buffer pointer, int pointer_offset);
	public static native void glVertex2f(float x, float y);
	public static native void glVertex2i(int x, int y);
	public static native void glVertex3f(float x, float y, float z);
	public static native void glVertex3i(int x, int y, int z);
	public static native void glVertex4f(float x, float y, float z, float w);
	public static native void glVertex4i(int x, int y, int z, int w);
	public static native void glTranslatef(float x, float y, float z);
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position());
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels, int pixels_offset);
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position());
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, Buffer pixels, int pixels_offset);
	public static native void glTexParameterf(int target, int pname, float param);
	public static native void glTexParameteri(int target, int pname, int param);
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels.position());
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels, int pixels_offset);
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels.position());
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, Buffer pixels, int pixels_offset);
	public static native void glTexGenf(int coord, int pname, float param);
	public static void glTexGen(int coord, int pname, FloatBuffer params) {
		nglTexGenfv(coord, pname, params, params.position());
	}
	private static native void nglTexGenfv(int coord, int pname, FloatBuffer params, int params_offset);
	public static native void glTexGeni(int coord, int pname, int param);
	public static void glTexGen(int coord, int pname, IntBuffer params) {
		nglTexGenfi(coord, pname, params, params.position());
	}
	private static native void nglTexGenfi(int coord, int pname, IntBuffer params, int params_offset);
	public static native void glTexEnvf(int target, int pname, float param);
	public static native void glTexEnvi(int target, int pname, int param);
	public static void glTexEnv(int target, int pname, FloatBuffer params) {
		nglTexEnvfv(target, pname, params, params.position());
	}
	private static native void nglTexEnvfv(int target, int pname, FloatBuffer params, int params_offset);
	public static void glTexEnv(int target, int pname, IntBuffer params) {
		nglTexEnviv(target, pname, params, params.position());
	}
	private static native void nglTexEnviv(int target, int pname, IntBuffer params, int params_offset);
	public static void glTexCoordPointer(int size, int stride, FloatBuffer pointer) {
		nglTexCoordPointer(size, GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglTexCoordPointer(int size, int type, int stride, Buffer pointer, int pointer_offset);
	public static native void glTexCoord1f(float s);
	public static native void glTexCoord2f(float s, float t);
	public static native void glTexCoord3f(float s, float t, float r);
	public static native void glTexCoord4f(float s, float t, float r, float q);
	public static native void glStencilOp(int fail, int zfail, int zpass);
	public static native void glStencilMask(int mask);
	public static native void glViewport(int x, int y, int width, int height);
}

