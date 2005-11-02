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

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * $Id$
 * <p/>
 * An extensible GL class that contains all the GL11 through GL15 methods, and all the ARB and EXT extension methods - but this
 * time as instance methods, like JOGL, Magician, and GL4Java.
 *
 * @author $Author$
 * @version $Revision$
 */
public class GLImpl implements IGL {

	/**
	 * C'tor
	 */
	public GLImpl() {
	}

	/**
	 * @param op
	 * @param value
	 */
	public void glAccum(int op, float value) {
		GL.glAccum(op, value);
	}

	/**
	 * @param face
	 */
	public void glActiveStencilFaceEXT(int face) {
		GL.glActiveStencilFaceEXT(face);
	}

	/**
	 * @param texture
	 */
	public void glActiveTexture(int texture) {
		GL.glActiveTexture(texture);
	}

	/**
	 * @param texture
	 */
	public void glActiveTextureARB(int texture) {
		GL.glActiveTextureARB(texture);
	}

	/**
	 * @param func
	 * @param ref
	 */
	public void glAlphaFunc(int func, float ref) {
		GL.glAlphaFunc(func, ref);
	}

	/**
	 * @param i
	 */
	public void glArrayElement(int i) {
		GL.glArrayElement(i);
	}

	/**
	 * @param containerObj
	 * @param obj
	 */
	public void glAttachObjectARB(int containerObj, int obj) {
		GL.glAttachObjectARB(containerObj, obj);
	}

	/**
	 * @param mode
	 */
	public void glBegin(int mode) {
		GL.glBegin(mode);
	}

	/**
	 * @param target
	 * @param id
	 */
	public void glBeginQuery(int target, int id) {
		GL.glBeginQuery(target, id);
	}

	/**
	 * @param target
	 * @param id
	 */
	public void glBeginQueryARB(int target, int id) {
		GL.glBeginQueryARB(target, id);
	}

	/**
	 *
	 */
	public void glBeginVertexShaderEXT() {
		GL.glBeginVertexShaderEXT();
	}

	/**
	 * @param programObj
	 * @param index
	 * @param name
	 */
	public void glBindAttribLocationARB(int programObj, int index, ByteBuffer name) {
		GL.glBindAttribLocationARB(programObj, index, name);
	}

	/**
	 * @param target
	 * @param buffer
	 */
	public void glBindBuffer(int target, int buffer) {
		GL.glBindBuffer(target, buffer);
	}

	/**
	 * @param target
	 * @param buffer
	 */
	public void glBindBufferARB(int target, int buffer) {
		GL.glBindBufferARB(target, buffer);
	}

	/**
	 * @param light
	 * @param value
	 *
	 * @return
	 */
	public int glBindLightParameterEXT(int light, int value) {
		return GL.glBindLightParameterEXT(light, value);
	}

	/**
	 * @param face
	 * @param value
	 *
	 * @return
	 */
	public int glBindMaterialParameterEXT(int face, int value) {
		return GL.glBindMaterialParameterEXT(face, value);
	}

	/**
	 * @param value
	 *
	 * @return
	 */
	public int glBindParameterEXT(int value) {
		return GL.glBindParameterEXT(value);
	}

	/**
	 * @param target
	 * @param program
	 */
	public void glBindProgramARB(int target, int program) {
		GL.glBindProgramARB(target, program);
	}

	/**
	 * @param unit
	 * @param coord
	 * @param value
	 *
	 * @return
	 */
	public int glBindTexGenParameterEXT(int unit, int coord, int value) {
		return GL.glBindTexGenParameterEXT(unit, coord, value);
	}

	/**
	 * @param target
	 * @param texture
	 */
	public void glBindTexture(int target, int texture) {
		GL.glBindTexture(target, texture);
	}

	/**
	 * @param unit
	 * @param value
	 *
	 * @return
	 */
	public int glBindTextureUnitParameterEXT(int unit, int value) {
		return GL.glBindTextureUnitParameterEXT(unit, value);
	}

	/**
	 * @param id
	 */
	public void glBindVertexShaderEXT(int id) {
		GL.glBindVertexShaderEXT(id);
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
	public void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap) {
		GL.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap);
	}

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
	public void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, int buffer_offect) {
		GL.glBitmap(width, height, xorig, yorig, xmove, ymove, buffer_offect);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glBlendColor(float red, float green, float blue, float alpha) {
		GL.glBlendColor(red, green, blue, alpha);
	}

	/**
	 * @param mode
	 */
	public void glBlendEquation(int mode) {
		GL.glBlendEquation(mode);
	}

	/**
	 * @param sfactor
	 * @param dfactor
	 */
	public void glBlendFunc(int sfactor, int dfactor) {
		GL.glBlendFunc(sfactor, dfactor);
	}

	/**
	 * @param modeRGB
	 * @param modeAlpha
	 */
	public void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha) {
		GL.glBlendEquationSeparateEXT(modeRGB, modeAlpha);
	}

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	public void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
		GL.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
	}

	/**
	 * @param sfactorRGB
	 * @param dfactorRGB
	 * @param sfactorAlpha
	 * @param dfactorAlpha
	 */
	public void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
		GL.glBlendFuncSeparateEXT(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
	}

	/**
	 * @param target
	 * @param size
	 * @param usage
	 */
	public void glBufferData(int target, int size, int usage) {
		GL.glBufferData(target, size, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, ByteBuffer data, int usage) {
		GL.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, FloatBuffer data, int usage) {
		GL.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, IntBuffer data, int usage) {
		GL.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferData(int target, ShortBuffer data, int usage) {
		GL.glBufferData(target, data, usage);
	}

	/**
	 * @param target
	 * @param size
	 * @param usage
	 */
	public void glBufferDataARB(int target, int size, int usage) {
		GL.glBufferDataARB(target, size, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, ByteBuffer data, int usage) {
		GL.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, FloatBuffer data, int usage) {
		GL.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, IntBuffer data, int usage) {
		GL.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param data
	 * @param usage
	 */
	public void glBufferDataARB(int target, ShortBuffer data, int usage) {
		GL.glBufferDataARB(target, data, usage);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, ByteBuffer data) {
		GL.glBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, FloatBuffer data) {
		GL.glBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, IntBuffer data) {
		GL.glBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubData(int target, int offset, ShortBuffer data) {
		GL.glBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset, ByteBuffer data) {
		GL.glBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset, FloatBuffer data) {
		GL.glBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset, IntBuffer data) {
		GL.glBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glBufferSubDataARB(int target, int offset, ShortBuffer data) {
		GL.glBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param list
	 */
	public void glCallList(int list) {
		GL.glCallList(list);
	}

	/**
	 * @param lists
	 */
	public void glCallLists(ByteBuffer lists) {
		GL.glCallLists(lists);
	}

	/**
	 * @param lists
	 */
	public void glCallLists(IntBuffer lists) {
		GL.glCallLists(lists);
	}

	/**
	 * @param lists
	 */
	public void glCallLists(ShortBuffer lists) {
		GL.glCallLists(lists);
	}

	/**
	 * @param mask
	 */
	public void glClear(int mask) {
		GL.glClear(mask);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glClearAccum(float red, float green, float blue, float alpha) {
		GL.glClearAccum(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glClearColor(float red, float green, float blue, float alpha) {
		GL.glClearColor(red, green, blue, alpha);
	}

	/**
	 * @param depth
	 */
	public void glClearDepth(double depth) {
		GL.glClearDepth(depth);
	}

	/**
	 * @param c
	 */
/*	public void glClearIndex(float c) {
		GL.glClearIndex(c);
	}
*/
	/**
	 * @param s
	 */
	public void glClearStencil(int s) {
		GL.glClearStencil(s);
	}

	/**
	 * @param texture
	 */
	public void glClientActiveTexture(int texture) {
		GL.glClientActiveTexture(texture);
	}

	/**
	 * @param texture
	 */
	public void glClientActiveTextureARB(int texture) {
		GL.glClientActiveTextureARB(texture);
	}

	/**
	 * @param plane
	 * @param equation
	 */
	public void glClipPlane(int plane, DoubleBuffer equation) {
		GL.glClipPlane(plane, equation);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glColor3b(byte red, byte green, byte blue) {
		GL.glColor3b(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glColor3f(float red, float green, float blue) {
		GL.glColor3f(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glColor3ub(byte red, byte green, byte blue) {
		GL.glColor3ub(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColor4b(byte red, byte green, byte blue, byte alpha) {
		GL.glColor4b(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColor4f(float red, float green, float blue, float alpha) {
		GL.glColor4f(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColor4ub(byte red, byte green, byte blue, byte alpha) {
		GL.glColor4ub(red, green, blue, alpha);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		GL.glColorMask(red, green, blue, alpha);
	}

	/**
	 * @param face
	 * @param mode
	 */
	public void glColorMaterial(int face, int mode) {
		GL.glColorMaterial(face, mode);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pointer
	 */
	public void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer) {
		GL.glColorPointer(size, unsigned, stride, pointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glColorPointer(int size, int stride, FloatBuffer pointer) {
		GL.glColorPointer(size, stride, pointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glColorPointer(int size, int type, int stride, int buffer_offset) {
		GL.glColorPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data) {
		GL.glColorSubTable(target, start, count, format, type, data);
	}

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data) {
		GL.glColorSubTable(target, start, count, format, type, data);
	}

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data) {
		GL.glColorTable(target, internalFormat, width, format, type, data);
	}

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
		GL.glColorTable(target, internalFormat, width, format, type, data);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glColorTableParameter(int target, int pname, FloatBuffer params) {
		GL.glColorTableParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glColorTableParameter(int target, int pname, IntBuffer params) {
		GL.glColorTableParameter(target, pname, params);
	}

	/**
	 * @param shaderObj
	 */
	public void glCompileShaderARB(int shaderObj) {
		GL.glCompileShaderARB(shaderObj);
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
	public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer data) {
		GL.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
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
	public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer data) {
		GL.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
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
	public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer data) {
		GL.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
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
	public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer data) {
		GL.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
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
	public void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer pData) {
		GL.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
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
	public void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer pData) {
		GL.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
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
	public void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer pData) {
		GL.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
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
	public void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer pData) {
		GL.glCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData);
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
	public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer data) {
		GL.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
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
	public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer data) {
		GL.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
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
	public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer data) {
		GL.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
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
	public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer data) {
		GL.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
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
	public void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer pData) {
		GL.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
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
	public void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer pData) {
		GL.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
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
	public void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer pData) {
		GL.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
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
	public void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer pData) {
		GL.glCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData);
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
	public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer data) {
		GL.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
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
	public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer data) {
		GL.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
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
	public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer data) {
		GL.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
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
	public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer data) {
		GL.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
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
	public void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		GL.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
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
	public void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		GL.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
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
	public void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		GL.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
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
	public void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		GL.glCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData);
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
	public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ByteBuffer data) {
		GL.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
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
	public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, FloatBuffer data) {
		GL.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
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
	public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, IntBuffer data) {
		GL.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
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
	public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ShortBuffer data) {
		GL.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
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
	public void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ByteBuffer pData) {
		GL.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
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
	public void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, FloatBuffer pData) {
		GL.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
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
	public void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, IntBuffer pData) {
		GL.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
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
	public void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ShortBuffer pData) {
		GL.glCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData);
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
	public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ByteBuffer data) {
		GL.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
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
	public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, FloatBuffer data) {
		GL.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
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
	public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, IntBuffer data) {
		GL.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
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
	public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ShortBuffer data) {
		GL.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
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
	public void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ByteBuffer pData) {
		GL.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
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
	public void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, FloatBuffer pData) {
		GL.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
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
	public void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, IntBuffer pData) {
		GL.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
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
	public void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ShortBuffer pData) {
		GL.glCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData);
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
	public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ByteBuffer data) {
		GL.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
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
	public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, FloatBuffer data) {
		GL.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
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
	public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, IntBuffer data) {
		GL.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
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
	public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ShortBuffer data) {
		GL.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
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
	public void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		GL.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
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
	public void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		GL.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
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
	public void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		GL.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
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
	public void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		GL.glCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image) {
		GL.glConvolutionFilter1D(target, internalformat, width, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image) {
		GL.glConvolutionFilter1D(target, internalformat, width, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image) {
		GL.glConvolutionFilter1D(target, internalformat, width, format, type, image);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image) {
		GL.glConvolutionFilter1D(target, internalformat, width, format, type, image);
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
	public void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer image) {
		GL.glConvolutionFilter2D(target, internalformat, width, height, format, type, image);
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
	public void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer image) {
		GL.glConvolutionFilter2D(target, internalformat, width, height, format, type, image);
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
	public void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer image) {
		GL.glConvolutionFilter2D(target, internalformat, width, height, format, type, image);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameter(int target, int pname, FloatBuffer params) {
		GL.glConvolutionParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameterf(int target, int pname, float params) {
		GL.glConvolutionParameterf(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameteri(int target, int pname, int params) {
		GL.glConvolutionParameteri(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glConvolutionParameter(int target, int pname, IntBuffer params) {
		GL.glConvolutionParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param start
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyColorSubTable(int target, int start, int x, int y, int width) {
		GL.glCopyColorSubTable(target, start, x, y, width);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyColorTable(int target, int internalformat, int x, int y, int width) {
		GL.glCopyColorTable(target, internalformat, x, y, width);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width) {
		GL.glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height) {
		GL.glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param type
	 */
	public void glCopyPixels(int x, int y, int width, int height, int type) {
		GL.glCopyPixels(x, y, width, height, type);
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
	public void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border) {
		GL.glCopyTexImage1D(target, level, internalFormat, x, y, width, border);
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
	public void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border) {
		GL.glCopyTexImage2D(target, level, internalFormat, x, y, width, height, border);
	}

	/**
	 * @param target
	 * @param level
	 * @param xoffset
	 * @param x
	 * @param y
	 * @param width
	 */
	public void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width) {
		GL.glCopyTexSubImage1D(target, level, xoffset, x, y, width);
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
	public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
		GL.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
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
	public void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
		GL.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
	}

	/**
	 * @return
	 */
	public int glCreateProgramObjectARB() {
		return GL.glCreateProgramObjectARB();
	}

	/**
	 * @param shaderType
	 *
	 * @return
	 */
	public int glCreateShaderObjectARB(int shaderType) {
		return GL.glCreateShaderObjectARB(shaderType);
	}

	/**
	 * @param mode
	 */
	public void glCullFace(int mode) {
		GL.glCullFace(mode);
	}

	/**
	 * @param index
	 */
	public void glCurrentPaletteMatrixARB(int index) {
		GL.glCurrentPaletteMatrixARB(index);
	}

	/**
	 * @param buffers
	 */
	public void glDeleteBuffers(IntBuffer buffers) {
		GL.glDeleteBuffers(buffers);
	}

	/**
	 * @param buffers
	 */
	public void glDeleteBuffersARB(IntBuffer buffers) {
		GL.glDeleteBuffersARB(buffers);
	}

	/**
	 * @param list
	 * @param range
	 */
	public void glDeleteLists(int list, int range) {
		GL.glDeleteLists(list, range);
	}

	/**
	 * @param obj
	 */
	public void glDeleteObjectARB(int obj) {
		GL.glDeleteObjectARB(obj);
	}

	/**
	 * @param programs
	 */
	public void glDeleteProgramsARB(IntBuffer programs) {
		GL.glDeleteProgramsARB(programs);
	}

	/**
	 * @param ids
	 */
	public void glDeleteQueries(IntBuffer ids) {
		GL.glDeleteQueries(ids);
	}

	/**
	 * @param ids
	 */
	public void glDeleteQueriesARB(IntBuffer ids) {
		GL.glDeleteQueriesARB(ids);
	}

	/**
	 * @param textures
	 */
	public void glDeleteTextures(IntBuffer textures) {
		GL.glDeleteTextures(textures);
	}

	/**
	 * @param id
	 */
	public void glDeleteVertexShaderEXT(int id) {
		GL.glDeleteVertexShaderEXT(id);
	}

	/**
	 * @param zmin
	 * @param zmax
	 */
	public void glDepthBoundsEXT(float zmin, float zmax) {
		GL.glDepthBoundsEXT(zmin, zmax);
	}

	/**
	 * @param func
	 */
	public void glDepthFunc(int func) {
		GL.glDepthFunc(func);
	}

	/**
	 * @param flag
	 */
	public void glDepthMask(boolean flag) {
		GL.glDepthMask(flag);
	}

	/**
	 * @param zNear
	 * @param zFar
	 */
	public void glDepthRange(double zNear, double zFar) {
		GL.glDepthRange(zNear, zFar);
	}

	/**
	 * @param containerObj
	 * @param attachedObj
	 */
	public void glDetachObjectARB(int containerObj, int attachedObj) {
		GL.glDetachObjectARB(containerObj, attachedObj);
	}

	/**
	 * @param cap
	 */
	public void glDisable(int cap) {
		GL.glDisable(cap);
	}

	/**
	 * @param cap
	 */
	public void glDisableClientState(int cap) {
		GL.glDisableClientState(cap);
	}

	/**
	 * @param id
	 */
	public void glDisableVariantClientStateEXT(int id) {
		GL.glDisableVariantClientStateEXT(id);
	}

	/**
	 * @param index
	 */
	public void glDisableVertexAttribArrayARB(int index) {
		GL.glDisableVertexAttribArrayARB(index);
	}

	/**
	 * @param mode
	 * @param first
	 * @param count
	 */
	public void glDrawArrays(int mode, int first, int count) {
		GL.glDrawArrays(mode, first, count);
	}

	/**
	 * @param mode
	 */
	public void glDrawBuffer(int mode) {
		GL.glDrawBuffer(mode);
	}

	/**
	 * @param mode
	 * @param indices
	 */
	public void glDrawElements(int mode, ByteBuffer indices) {
		GL.glDrawElements(mode, indices);
	}

	/**
	 * @param mode
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public void glDrawElements(int mode, int count, int type, int buffer_offset) {
		GL.glDrawElements(mode, count, type, buffer_offset);
	}

	/**
	 * @param mode
	 * @param indices
	 */
	public void glDrawElements(int mode, IntBuffer indices) {
		GL.glDrawElements(mode, indices);
	}

	/**
	 * @param mode
	 * @param indices
	 */
	public void glDrawElements(int mode, ShortBuffer indices) {
		GL.glDrawElements(mode, indices);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) {
		GL.glDrawPixels(width, height, format, type, pixels);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) {
		GL.glDrawPixels(width, height, format, type, pixels);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) {
		GL.glDrawPixels(width, height, format, type, pixels);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices) {
		GL.glDrawRangeElements(mode, start, end, indices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public void glDrawRangeElements(int mode, int start, int end, int count, int type, int buffer_offset) {
		GL.glDrawRangeElements(mode, start, end, count, type, buffer_offset);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) {
		GL.glDrawRangeElements(mode, start, end, indices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param indices
	 */
	public void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) {
		GL.glDrawRangeElements(mode, start, end, indices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices) {
		GL.glDrawRangeElementsEXT(mode, start, end, pIndices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param count
	 * @param type
	 * @param buffer_offset
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end, int count, int type, int buffer_offset) {
		GL.glDrawRangeElementsEXT(mode, start, end, count, type, buffer_offset);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) {
		GL.glDrawRangeElementsEXT(mode, start, end, pIndices);
	}

	/**
	 * @param mode
	 * @param start
	 * @param end
	 * @param pIndices
	 */
	public void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) {
		GL.glDrawRangeElementsEXT(mode, start, end, pIndices);
	}

	/**
	 * @param flag
	 */
	public void glEdgeFlag(boolean flag) {
		GL.glEdgeFlag(flag);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glEdgeFlagPointer(int stride, ByteBuffer pointer) {
		GL.glEdgeFlagPointer(stride, pointer);
	}

	/**
	 * @param stride
	 * @param buffer_offset
	 */
	public void glEdgeFlagPointer(int stride, int buffer_offset) {
		GL.glEdgeFlagPointer(stride, buffer_offset);
	}

	/**
	 * @param cap
	 */
	public void glEnable(int cap) {
		GL.glEnable(cap);
	}

	/**
	 * @param cap
	 */
	public void glEnableClientState(int cap) {
		GL.glEnableClientState(cap);
	}

	/**
	 * @param id
	 */
	public void glEnableVariantClientStateEXT(int id) {
		GL.glEnableVariantClientStateEXT(id);
	}

	/**
	 * @param index
	 */
	public void glEnableVertexAttribArrayARB(int index) {
		GL.glEnableVertexAttribArrayARB(index);
	}

	/**
	 *
	 */
	public void glEnd() {
		GL.glEnd();
	}

	/**
	 *
	 */
	public void glEndList() {
		GL.glEndList();
	}

	/**
	 * @param target
	 */
	public void glEndQuery(int target) {
		GL.glEndQuery(target);
	}

	/**
	 * @param target
	 */
	public void glEndQueryARB(int target) {
		GL.glEndQueryARB(target);
	}

	/**
	 *
	 */
	public void glEndVertexShaderEXT() {
		GL.glEndVertexShaderEXT();
	}

	/**
	 * @param u
	 */
	public void glEvalCoord1f(float u) {
		GL.glEvalCoord1f(u);
	}

	/**
	 * @param u
	 * @param v
	 */
	public void glEvalCoord2f(float u, float v) {
		GL.glEvalCoord2f(u, v);
	}

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 */
	public void glEvalMesh1(int mode, int i1, int i2) {
		GL.glEvalMesh1(mode, i1, i2);
	}

	/**
	 * @param mode
	 * @param i1
	 * @param i2
	 * @param j1
	 * @param j2
	 */
	public void glEvalMesh2(int mode, int i1, int i2, int j1, int j2) {
		GL.glEvalMesh2(mode, i1, i2, j1, j2);
	}

	/**
	 * @param i
	 */
	public void glEvalPoint1(int i) {
		GL.glEvalPoint1(i);
	}

	/**
	 * @param i
	 * @param j
	 */
	public void glEvalPoint2(int i, int j) {
		GL.glEvalPoint2(i, j);
	}

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	public void glExtractComponentEXT(int res, int src, int num) {
		GL.glExtractComponentEXT(res, src, num);
	}

	/**
	 * @param type
	 * @param buffer
	 */
	public void glFeedbackBuffer(int type, FloatBuffer buffer) {
		GL.glFeedbackBuffer(type, buffer);
	}

	/**
	 *
	 */
	public void glFinish() {
		GL.glFinish();
	}

	/**
	 *
	 */
	public void glFlush() {
		GL.glFlush();
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glFog(int pname, FloatBuffer params) {
		GL.glFog(pname, params);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glFog(int pname, IntBuffer params) {
		GL.glFog(pname, params);
	}

	/**
	 * @param coord
	 */
	public void glFogCoordf(float coord) {
		GL.glFogCoordf(coord);
	}

	/**
	 * @param coord
	 */
	public void glFogCoordfEXT(float coord) {
		GL.glFogCoordfEXT(coord);
	}

	/**
	 * @param stride
	 * @param data
	 */
	public void glFogCoordPointer(int stride, FloatBuffer data) {
		GL.glFogCoordPointer(stride, data);
	}

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glFogCoordPointer(int type, int stride, int buffer_offset) {
		GL.glFogCoordPointer(type, stride, buffer_offset);
	}

	/**
	 * @param stride
	 * @param data
	 */
	public void glFogCoordPointerEXT(int stride, FloatBuffer data) {
		GL.glFogCoordPointerEXT(stride, data);
	}

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glFogCoordPointerEXT(int type, int stride, int buffer_offset) {
		GL.glFogCoordPointerEXT(type, stride, buffer_offset);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glFogf(int pname, float param) {
		GL.glFogf(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glFogi(int pname, int param) {
		GL.glFogi(pname, param);
	}

	/**
	 * @param mode
	 */
	public void glFrontFace(int mode) {
		GL.glFrontFace(mode);
	}

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	public void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL.glFrustum(left, right, bottom, top, zNear, zFar);
	}

	/**
	 * @param buffers
	 */
	public void glGenBuffers(IntBuffer buffers) {
		GL.glGenBuffers(buffers);
	}

	/**
	 * @param buffers
	 */
	public void glGenBuffersARB(IntBuffer buffers) {
		GL.glGenBuffersARB(buffers);
	}

	/**
	 * @param range
	 *
	 * @return
	 */
	public int glGenLists(int range) {
		return GL.glGenLists(range);
	}

	/**
	 * @param programs
	 */
	public void glGenProgramsARB(IntBuffer programs) {
		GL.glGenProgramsARB(programs);
	}

	/**
	 * @param ids
	 */
	public void glGenQueries(IntBuffer ids) {
		GL.glGenQueries(ids);
	}

	/**
	 * @param ids
	 */
	public void glGenQueriesARB(IntBuffer ids) {
		GL.glGenQueriesARB(ids);
	}

	/**
	 * @param dataType
	 * @param storageType
	 * @param range
	 * @param components
	 *
	 * @return
	 */
	public int glGenSymbolsEXT(int dataType, int storageType, int range, int components) {
		return GL.glGenSymbolsEXT(dataType, storageType, range, components);
	}

	/**
	 * @param textures
	 */
	public void glGenTextures(IntBuffer textures) {
		GL.glGenTextures(textures);
	}

	/**
	 * @param range
	 *
	 * @return
	 */
	public int glGenVertexShadersEXT(int range) {
		return GL.glGenVertexShadersEXT(range);
	}

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public void glGetActiveAttribARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		GL.glGetActiveAttribARB(programObj, index, length, size, type, name);
	}

	/**
	 * @param programObj
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public void glGetActiveUniformARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		GL.glGetActiveUniformARB(programObj, index, length, size, type, name);
	}

	/**
	 * @param containerObj
	 * @param count
	 * @param obj
	 */
	public void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj) {
		GL.glGetAttachedObjectsARB(containerObj, count, obj);
	}

	/**
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	public int glGetAttribLocationARB(int programObj, ByteBuffer name) {
		return GL.glGetAttribLocationARB(programObj, name);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetBoolean(int pname, ByteBuffer params) {
		GL.glGetBoolean(pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetBufferParameter(int target, int pname, IntBuffer params) {
		GL.glGetBufferParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetBufferParameterARB(int target, int pname, IntBuffer params) {
		GL.glGetBufferParameterARB(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public ByteBuffer glGetBufferPointer(int target, int pname, int size) {
		return GL.glGetBufferPointer(target, pname, size);
	}

	/**
	 * @param target
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public ByteBuffer glGetBufferPointerARB(int target, int pname, int size) {
		return GL.glGetBufferPointerARB(target, pname, size);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset, ByteBuffer data) {
		GL.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset, FloatBuffer data) {
		GL.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset, IntBuffer data) {
		GL.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubData(int target, int offset, ShortBuffer data) {
		GL.glGetBufferSubData(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset, ByteBuffer data) {
		GL.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset, FloatBuffer data) {
		GL.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset, IntBuffer data) {
		GL.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param target
	 * @param offset
	 * @param data
	 */
	public void glGetBufferSubDataARB(int target, int offset, ShortBuffer data) {
		GL.glGetBufferSubDataARB(target, offset, data);
	}

	/**
	 * @param plane
	 * @param equation
	 */
	public void glGetClipPlane(int plane, DoubleBuffer equation) {
		GL.glGetClipPlane(plane, equation);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glGetColorTable(int target, int format, int type, ByteBuffer data) {
		GL.glGetColorTable(target, format, type, data);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param data
	 */
	public void glGetColorTable(int target, int format, int type, FloatBuffer data) {
		GL.glGetColorTable(target, format, type, data);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetColorTableParameter(int target, int pname, FloatBuffer params) {
		GL.glGetColorTableParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetColorTableParameter(int target, int pname, IntBuffer params) {
		GL.glGetColorTableParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public void glGetCompressedTexImage(int target, int lod, ByteBuffer img) {
		GL.glGetCompressedTexImage(target, lod, img);
	}

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public void glGetCompressedTexImage(int target, int lod, IntBuffer img) {
		GL.glGetCompressedTexImage(target, lod, img);
	}

	/**
	 * @param target
	 * @param lod
	 * @param img
	 */
	public void glGetCompressedTexImage(int target, int lod, ShortBuffer img) {
		GL.glGetCompressedTexImage(target, lod, img);
	}

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg) {
		GL.glGetCompressedTexImageARB(target, lod, pImg);
	}

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public void glGetCompressedTexImageARB(int target, int lod, IntBuffer pImg) {
		GL.glGetCompressedTexImageARB(target, lod, pImg);
	}

	/**
	 * @param target
	 * @param lod
	 * @param pImg
	 */
	public void glGetCompressedTexImageARB(int target, int lod, ShortBuffer pImg) {
		GL.glGetCompressedTexImageARB(target, lod, pImg);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image) {
		GL.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image) {
		GL.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type, IntBuffer image) {
		GL.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param image
	 */
	public void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image) {
		GL.glGetConvolutionFilter(target, format, type, image);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetConvolutionParameter(int target, int pname, FloatBuffer params) {
		GL.glGetConvolutionParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetConvolutionParameter(int target, int pname, IntBuffer params) {
		GL.glGetConvolutionParameter(target, pname, params);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetDouble(int pname, DoubleBuffer params) {
		GL.glGetDouble(pname, params);
	}

	/**
	 * @return
	 */
	public int glGetError() {
		return GL.glGetError();
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetFloat(int pname, FloatBuffer params) {
		GL.glGetFloat(pname, params);
	}

	/**
	 * @param pname
	 *
	 * @return
	 */
	public int glGetHandleARB(int pname) {
		return GL.glGetHandleARB(pname);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
		GL.glGetHistogram(target, reset, format, type, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values) {
		GL.glGetHistogram(target, reset, format, type, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values) {
		GL.glGetHistogram(target, reset, format, type, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param values
	 */
	public void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values) {
		GL.glGetHistogram(target, reset, format, type, values);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetHistogramParameter(int target, int pname, FloatBuffer params) {
		GL.glGetHistogramParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetHistogramParameter(int target, int pname, IntBuffer params) {
		GL.glGetHistogramParameter(target, pname, params);
	}

	/**
	 * @param obj
	 * @param length
	 * @param infoLog
	 */
	public void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog) {
		GL.glGetInfoLogARB(obj, length, infoLog);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glGetInteger(int pname, IntBuffer params) {
		GL.glGetInteger(pname, params);
	}

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public void glGetInvariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		GL.glGetInvariantBooleanEXT(id, value, pbData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public void glGetInvariantFloatEXT(int id, int value, FloatBuffer pfData) {
		GL.glGetInvariantFloatEXT(id, value, pfData);
	}

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public void glGetInvariantIntegerEXT(int id, int value, IntBuffer piData) {
		GL.glGetInvariantIntegerEXT(id, value, piData);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glGetLight(int light, int pname, FloatBuffer params) {
		GL.glGetLight(light, pname, params);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glGetLight(int light, int pname, IntBuffer params) {
		GL.glGetLight(light, pname, params);
	}

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public void glGetLocalConstantBooleanEXT(int id, int value, ByteBuffer pbData) {
		GL.glGetLocalConstantBooleanEXT(id, value, pbData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pfData) {
		GL.glGetLocalConstantFloatEXT(id, value, pfData);
	}

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public void glGetLocalConstantIntegerEXT(int id, int value, IntBuffer piData) {
		GL.glGetLocalConstantIntegerEXT(id, value, piData);
	}

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	public void glGetMap(int target, int query, FloatBuffer v) {
		GL.glGetMap(target, query, v);
	}

	/**
	 * @param target
	 * @param query
	 * @param v
	 */
	public void glGetMap(int target, int query, IntBuffer v) {
		GL.glGetMap(target, query, v);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glGetMaterial(int face, int pname, FloatBuffer params) {
		GL.glGetMaterial(face, pname, params);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glGetMaterial(int face, int pname, IntBuffer params) {
		GL.glGetMaterial(face, pname, params);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values) {
		GL.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values) {
		GL.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values) {
		GL.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param values
	 */
	public void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values) {
		GL.glGetMinmax(target, reset, format, types, values);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetMinmaxParameter(int target, int pname, FloatBuffer params) {
		GL.glGetMinmaxParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetMinmaxParameter(int target, int pname, IntBuffer params) {
		GL.glGetMinmaxParameter(target, pname, params);
	}

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	public void glGetObjectParameterARB(int obj, int pname, FloatBuffer params) {
		GL.glGetObjectParameterARB(obj, pname, params);
	}

	/**
	 * @param obj
	 * @param pname
	 * @param params
	 */
	public void glGetObjectParameterARB(int obj, int pname, IntBuffer params) {
		GL.glGetObjectParameterARB(obj, pname, params);
	}

	/**
	 * @param map
	 * @param values
	 */
	public void glGetPixelMap(int map, FloatBuffer values) {
		GL.glGetPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param values
	 */
	public void glGetPixelMapu(int map, IntBuffer values) {
		GL.glGetPixelMapu(map, values);
	}

	/**
	 * @param map
	 * @param values
	 */
	public void glGetPixelMapu(int map, ShortBuffer values) {
		GL.glGetPixelMapu(map, values);
	}

	/**
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public ByteBuffer glGetPointer(int pname, int size) {
		return GL.glGetPointer(pname, size);
	}

	/**
	 * @param mask
	 */
	public void glGetPolygonStipple(ByteBuffer mask) {
		GL.glGetPolygonStipple(mask);
	}

	/**
	 * @param target
	 * @param parameterName
	 * @param params
	 */
	public void glGetProgramARB(int target, int parameterName, IntBuffer params) {
		GL.glGetProgramARB(target, parameterName, params);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glGetProgramEnvParameterARB(int target, int index, FloatBuffer params) {
		GL.glGetProgramEnvParameterARB(target, index, params);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glGetProgramLocalParameterARB(int target, int index, FloatBuffer params) {
		GL.glGetProgramLocalParameterARB(target, index, params);
	}

	/**
	 * @param target
	 * @param parameterName
	 * @param paramString
	 */
	public void glGetProgramStringARB(int target, int parameterName, ByteBuffer paramString) {
		GL.glGetProgramStringARB(target, parameterName, paramString);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetQuery(int target, int pname, IntBuffer params) {
		GL.glGetQuery(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetQueryARB(int target, int pname, IntBuffer params) {
		GL.glGetQueryARB(target, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObject(int id, int pname, IntBuffer params) {
		GL.glGetQueryObject(id, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObjectARB(int id, int pname, IntBuffer params) {
		GL.glGetQueryObjectARB(id, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObjectu(int id, int pname, IntBuffer params) {
		GL.glGetQueryObjectu(id, pname, params);
	}

	/**
	 * @param id
	 * @param pname
	 * @param params
	 */
	public void glGetQueryObjectuARB(int id, int pname, IntBuffer params) {
		GL.glGetQueryObjectuARB(id, pname, params);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param row
	 * @param column
	 * @param span
	 */
/*	public void glGetSeparableFilter(int target, int format, int type, Buffer row, Buffer column, Buffer span) {
		GL.glGetSeparableFilter(target, format, type, row, column, span);
	}
*/
	/**
	 * @param obj
	 * @param length
	 * @param source
	 */
	public void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source) {
		GL.glGetShaderSourceARB(obj, length, source);
	}

	/**
	 * @param name
	 *
	 * @return
	 */
	public String glGetString(int name) {
		return GL.glGetString(name);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexEnv(int coord, int pname, FloatBuffer params) {
		GL.glGetTexEnv(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexEnv(int coord, int pname, IntBuffer params) {
		GL.glGetTexEnv(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexGen(int coord, int pname, FloatBuffer params) {
		GL.glGetTexGen(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glGetTexGen(int coord, int pname, IntBuffer params) {
		GL.glGetTexGen(coord, pname, params);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
		GL.glGetTexImage(target, level, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) {
		GL.glGetTexImage(target, level, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param pixels
	 */
	public void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels) {
		GL.glGetTexImage(target, level, format, type, pixels);
	}

	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params) {
		GL.glGetTexLevelParameter(target, level, pname, params);
	}

	/**
	 * @param target
	 * @param level
	 * @param pname
	 * @param params
	 */
	public void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params) {
		GL.glGetTexLevelParameter(target, level, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetTexParameter(int target, int pname, FloatBuffer params) {
		GL.glGetTexParameter(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glGetTexParameter(int target, int pname, IntBuffer params) {
		GL.glGetTexParameter(target, pname, params);
	}

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	public void glGetUniformARB(int programObj, int location, FloatBuffer params) {
		GL.glGetUniformARB(programObj, location, params);
	}

	/**
	 * @param programObj
	 * @param location
	 * @param params
	 */
	public void glGetUniformARB(int programObj, int location, IntBuffer params) {
		GL.glGetUniformARB(programObj, location, params);
	}

	/**
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	public int glGetUniformLocationARB(int programObj, ByteBuffer name) {
		return GL.glGetUniformLocationARB(programObj, name);
	}

	/**
	 * @param id
	 * @param value
	 * @param pbData
	 */
	public void glGetVariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		GL.glGetVariantBooleanEXT(id, value, pbData);
	}

	/**
	 * @param id
	 * @param value
	 * @param pfData
	 */
	public void glGetVariantFloatEXT(int id, int value, FloatBuffer pfData) {
		GL.glGetVariantFloatEXT(id, value, pfData);
	}

	/**
	 * @param id
	 * @param value
	 * @param piData
	 */
	public void glGetVariantIntegerEXT(int id, int value, IntBuffer piData) {
		GL.glGetVariantIntegerEXT(id, value, piData);
	}

	/**
	 * @param id
	 * @param value
	 * @param size
	 *
	 * @return
	 */
	public ByteBuffer glGetVariantPointerEXT(int id, int value, int size) {
		return GL.glGetVariantPointerEXT(id, value, size);
	}

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	public void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
		GL.glGetVertexAttribARB(index, pname, params);
	}

	/**
	 * @param index
	 * @param pname
	 * @param params
	 */
	public void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
		GL.glGetVertexAttribARB(index, pname, params);
	}

	/**
	 * @param index
	 * @param pname
	 * @param size
	 *
	 * @return
	 */
	public ByteBuffer glGetVertexAttribPointerARB(int index, int pname, int size) {
		return GL.glGetVertexAttribPointerARB(index, pname, size);
	}

	/**
	 * @param target
	 * @param mode
	 */
	public void glHint(int target, int mode) {
		GL.glHint(target, mode);
	}

	/**
	 * @param target
	 * @param width
	 * @param internalformat
	 * @param sink
	 */
	public void glHistogram(int target, int width, int internalformat, boolean sink) {
		GL.glHistogram(target, width, internalformat, sink);
	}

	/**
	 *
	 */
	public void glInitNames() {
		GL.glInitNames();
	}

	/**
	 * @param res
	 * @param src
	 * @param num
	 */
	public void glInsertComponentEXT(int res, int src, int num) {
		GL.glInsertComponentEXT(res, src, num);
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride, ByteBuffer pointer) {
		GL.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride, FloatBuffer pointer) {
		GL.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param format
	 * @param stride
	 * @param buffer_offset
	 */
	public void glInterleavedArrays(int format, int stride, int buffer_offset) {
		GL.glInterleavedArrays(format, stride, buffer_offset);
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride, IntBuffer pointer) {
		GL.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param format
	 * @param stride
	 * @param pointer
	 */
	public void glInterleavedArrays(int format, int stride, ShortBuffer pointer) {
		GL.glInterleavedArrays(format, stride, pointer);
	}

	/**
	 * @param buffer
	 *
	 * @return
	 */
	public boolean glIsBuffer(int buffer) {
		return GL.glIsBuffer(buffer);
	}

	/**
	 * @param buffer
	 *
	 * @return
	 */
	public boolean glIsBufferARB(int buffer) {
		return GL.glIsBufferARB(buffer);
	}

	/**
	 * @param cap
	 *
	 * @return
	 */
	public boolean glIsEnabled(int cap) {
		return GL.glIsEnabled(cap);
	}

	/**
	 * @param list
	 *
	 * @return
	 */
	public boolean glIsList(int list) {
		return GL.glIsList(list);
	}

	/**
	 * @param program
	 *
	 * @return
	 */
	public boolean glIsProgramARB(int program) {
		return GL.glIsProgramARB(program);
	}

	/**
	 * @param id
	 *
	 * @return
	 */
	public boolean glIsQuery(int id) {
		return GL.glIsQuery(id);
	}

	/**
	 * @param id
	 *
	 * @return
	 */
	public boolean glIsQueryARB(int id) {
		return GL.glIsQueryARB(id);
	}

	/**
	 * @param texture
	 *
	 * @return
	 */
	public boolean glIsTexture(int texture) {
		return GL.glIsTexture(texture);
	}

	/**
	 * @param id
	 * @param cap
	 *
	 * @return
	 */
	public boolean glIsVariantEnabledEXT(int id, int cap) {
		return GL.glIsVariantEnabledEXT(id, cap);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glLight(int light, int pname, FloatBuffer params) {
		GL.glLight(light, pname, params);
	}

	/**
	 * @param light
	 * @param pname
	 * @param params
	 */
	public void glLight(int light, int pname, IntBuffer params) {
		GL.glLight(light, pname, params);
	}

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	public void glLightf(int light, int pname, float param) {
		GL.glLightf(light, pname, param);
	}

	/**
	 * @param light
	 * @param pname
	 * @param param
	 */
	public void glLighti(int light, int pname, int param) {
		GL.glLighti(light, pname, param);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glLightModel(int pname, FloatBuffer params) {
		GL.glLightModel(pname, params);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glLightModel(int pname, IntBuffer params) {
		GL.glLightModel(pname, params);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glLightModelf(int pname, float param) {
		GL.glLightModelf(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glLightModeli(int pname, int param) {
		GL.glLightModeli(pname, param);
	}

	/**
	 * @param factor
	 * @param pattern
	 */
	public void glLineStipple(int factor, short pattern) {
		GL.glLineStipple(factor, pattern);
	}

	/**
	 * @param width
	 */
	public void glLineWidth(float width) {
		GL.glLineWidth(width);
	}

	/**
	 * @param programObj
	 */
	public void glLinkProgramARB(int programObj) {
		GL.glLinkProgramARB(programObj);
	}

	/**
	 * @param base
	 */
	public void glListBase(int base) {
		GL.glListBase(base);
	}

	/**
	 *
	 */
	public void glLoadIdentity() {
		GL.glLoadIdentity();
	}

	/**
	 * @param m
	 */
	public void glLoadMatrix(FloatBuffer m) {
		GL.glLoadMatrix(m);
	}

	/**
	 * @param name
	 */
	public void glLoadName(int name) {
		GL.glLoadName(name);
	}

	/**
	 * @param m
	 */
	public void glLoadTransposeMatrix(FloatBuffer m) {
		GL.glLoadTransposeMatrix(m);
	}

	/**
	 * @param pfMtx
	 */
	public void glLoadTransposeMatrixARB(FloatBuffer pfMtx) {
		GL.glLoadTransposeMatrixARB(pfMtx);
	}

	/**
	 * @param first
	 * @param count
	 */
	public void glLockArraysEXT(int first, int count) {
		GL.glLockArraysEXT(first, count);
	}

	/**
	 * @param opcode
	 */
	public void glLogicOp(int opcode) {
		GL.glLogicOp(opcode);
	}

	/**
	 * @param target
	 * @param u1
	 * @param u2
	 * @param stride
	 * @param order
	 * @param points
	 */
	public void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) {
		GL.glMap1f(target, u1, u2, stride, order, points);
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
	public void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
		GL.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
	}

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 *
	 * @return
	 */
	public ByteBuffer glMapBuffer(int target, int access, int size, ByteBuffer oldBuffer) {
		return GL.glMapBuffer(target, access, size, oldBuffer);
	}

	/**
	 * @param target
	 * @param access
	 * @param size
	 * @param oldBuffer
	 *
	 * @return
	 */
	public ByteBuffer glMapBufferARB(int target, int access, int size, ByteBuffer oldBuffer) {
		return GL.glMapBufferARB(target, access, size, oldBuffer);
	}

	/**
	 * @param un
	 * @param u1
	 * @param u2
	 */
	public void glMapGrid1f(int un, float u1, float u2) {
		GL.glMapGrid1f(un, u1, u2);
	}

	/**
	 * @param un
	 * @param u1
	 * @param u2
	 * @param vn
	 * @param v1
	 * @param v2
	 */
	public void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2) {
		GL.glMapGrid2f(un, u1, u2, vn, v1, v2);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glMaterial(int face, int pname, FloatBuffer params) {
		GL.glMaterial(face, pname, params);
	}

	/**
	 * @param face
	 * @param pname
	 * @param params
	 */
	public void glMaterial(int face, int pname, IntBuffer params) {
		GL.glMaterial(face, pname, params);
	}

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	public void glMaterialf(int face, int pname, float param) {
		GL.glMaterialf(face, pname, param);
	}

	/**
	 * @param face
	 * @param pname
	 * @param param
	 */
	public void glMateriali(int face, int pname, int param) {
		GL.glMateriali(face, pname, param);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) {
		GL.glMatrixIndexPointerARB(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glMatrixIndexPointerARB(int size, int type, int stride, int buffer_offset) {
		GL.glMatrixIndexPointerARB(size, type, stride, buffer_offset);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) {
		GL.glMatrixIndexPointerARB(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) {
		GL.glMatrixIndexPointerARB(size, stride, pPointer);
	}

	/**
	 * @param pIndices
	 */
	public void glMatrixIndexuARB(ByteBuffer pIndices) {
		GL.glMatrixIndexuARB(pIndices);
	}

	/**
	 * @param piIndices
	 */
	public void glMatrixIndexuARB(IntBuffer piIndices) {
		GL.glMatrixIndexuARB(piIndices);
	}

	/**
	 * @param psIndices
	 */
	public void glMatrixIndexuARB(ShortBuffer psIndices) {
		GL.glMatrixIndexuARB(psIndices);
	}

	/**
	 * @param mode
	 */
	public void glMatrixMode(int mode) {
		GL.glMatrixMode(mode);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param sink
	 */
	public void glMinmax(int target, int internalformat, boolean sink) {
		GL.glMinmax(target, internalformat, sink);
	}

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	public void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount) {
		GL.glMultiDrawArrays(mode, piFirst, piCount);
	}

	/**
	 * @param mode
	 * @param piFirst
	 * @param piCount
	 */
	public void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount) {
		GL.glMultiDrawArraysEXT(mode, piFirst, piCount);
	}

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1f(int target, float s) {
		GL.glMultiTexCoord1f(target, s);
	}

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1fARB(int target, float s) {
		GL.glMultiTexCoord1fARB(target, s);
	}

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1iARB(int target, int s) {
		GL.glMultiTexCoord1iARB(target, s);
	}

	/**
	 * @param target
	 * @param s
	 */
	public void glMultiTexCoord1sARB(int target, short s) {
		GL.glMultiTexCoord1sARB(target, s);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2f(int target, float s, float t) {
		GL.glMultiTexCoord2f(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2fARB(int target, float s, float t) {
		GL.glMultiTexCoord2fARB(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2iARB(int target, int s, int t) {
		GL.glMultiTexCoord2iARB(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 */
	public void glMultiTexCoord2sARB(int target, short s, short t) {
		GL.glMultiTexCoord2sARB(target, s, t);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3f(int target, float s, float t, float r) {
		GL.glMultiTexCoord3f(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3fARB(int target, float s, float t, float r) {
		GL.glMultiTexCoord3fARB(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3iARB(int target, int s, int t, int r) {
		GL.glMultiTexCoord3iARB(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glMultiTexCoord3sARB(int target, short s, short t, short r) {
		GL.glMultiTexCoord3sARB(target, s, t, r);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
		GL.glMultiTexCoord4f(target, s, t, r, q);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4fARB(int target, float s, float t, float r, float q) {
		GL.glMultiTexCoord4fARB(target, s, t, r, q);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4iARB(int target, int s, int t, int r, int q) {
		GL.glMultiTexCoord4iARB(target, s, t, r, q);
	}

	/**
	 * @param target
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glMultiTexCoord4sARB(int target, short s, short t, short r, short q) {
		GL.glMultiTexCoord4sARB(target, s, t, r, q);
	}

	/**
	 * @param m
	 */
	public void glMultMatrix(FloatBuffer m) {
		GL.glMultMatrix(m);
	}

	/**
	 * @param m
	 */
	public void glMultTransposeMatrix(FloatBuffer m) {
		GL.glMultTransposeMatrix(m);
	}

	/**
	 * @param pfMtx
	 */
	public void glMultTransposeMatrixARB(FloatBuffer pfMtx) {
		GL.glMultTransposeMatrixARB(pfMtx);
	}

	/**
	 * @param list
	 * @param mode
	 */
	public void glNewList(int list, int mode) {
		GL.glNewList(list, mode);
	}

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public void glNormal3b(byte nx, byte ny, byte nz) {
		GL.glNormal3b(nx, ny, nz);
	}

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public void glNormal3f(float nx, float ny, float nz) {
		GL.glNormal3f(nx, ny, nz);
	}

	/**
	 * @param nx
	 * @param ny
	 * @param nz
	 */
	public void glNormal3i(int nx, int ny, int nz) {
		GL.glNormal3i(nx, ny, nz);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glNormalPointer(int stride, ByteBuffer pointer) {
		GL.glNormalPointer(stride, pointer);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glNormalPointer(int stride, FloatBuffer pointer) {
		GL.glNormalPointer(stride, pointer);
	}

	/**
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glNormalPointer(int type, int stride, int buffer_offset) {
		GL.glNormalPointer(type, stride, buffer_offset);
	}

	/**
	 * @param stride
	 * @param pointer
	 */
	public void glNormalPointer(int stride, IntBuffer pointer) {
		GL.glNormalPointer(stride, pointer);
	}

	/**
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param zNear
	 * @param zFar
	 */
	public void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL.glOrtho(left, right, bottom, top, zNear, zFar);
	}

	/**
	 * @param token
	 */
	public void glPassThrough(float token) {
		GL.glPassThrough(token);
	}

	/**
	 * @param map
	 * @param values
	 */
	public void glPixelMap(int map, FloatBuffer values) {
		GL.glPixelMap(map, values);
	}

	/**
	 * @param map
	 * @param values
	 */
	public void glPixelMapu(int map, IntBuffer values) {
		GL.glPixelMapu(map, values);
	}

	/**
	 * @param map
	 * @param values
	 */
	public void glPixelMapu(int map, ShortBuffer values) {
		GL.glPixelMapu(map, values);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelStoref(int pname, float param) {
		GL.glPixelStoref(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelStorei(int pname, int param) {
		GL.glPixelStorei(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelTransferf(int pname, float param) {
		GL.glPixelTransferf(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glPixelTransferi(int pname, int param) {
		GL.glPixelTransferi(pname, param);
	}

	/**
	 * @param xfactor
	 * @param yfactor
	 */
	public void glPixelZoom(float xfactor, float yfactor) {
		GL.glPixelZoom(xfactor, yfactor);
	}

	/**
	 * @param pname
	 * @param params
	 */
	public void glPointParameter(int pname, FloatBuffer params) {
		GL.glPointParameter(pname, params);
	}

	/**
	 * @param pname
	 * @param pfParams
	 */
	public void glPointParameterARB(int pname, FloatBuffer pfParams) {
		GL.glPointParameterARB(pname, pfParams);
	}

	/**
	 * @param pname
	 * @param pfParams
	 */
	public void glPointParameterEXT(int pname, FloatBuffer pfParams) {
		GL.glPointParameterEXT(pname, pfParams);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glPointParameterf(int pname, float param) {
		GL.glPointParameterf(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glPointParameterfARB(int pname, float param) {
		GL.glPointParameterfARB(pname, param);
	}

	/**
	 * @param pname
	 * @param param
	 */
	public void glPointParameterfEXT(int pname, float param) {
		GL.glPointParameterfEXT(pname, param);
	}

	/**
	 * @param size
	 */
	public void glPointSize(float size) {
		GL.glPointSize(size);
	}

	/**
	 * @param face
	 * @param mode
	 */
	public void glPolygonMode(int face, int mode) {
		GL.glPolygonMode(face, mode);
	}

	/**
	 * @param factor
	 * @param units
	 */
	public void glPolygonOffset(float factor, float units) {
		GL.glPolygonOffset(factor, units);
	}

	/**
	 * @param mask
	 */
	public void glPolygonStipple(ByteBuffer mask) {
		GL.glPolygonStipple(mask);
	}

	/**
	 *
	 */
	public void glPopAttrib() {
		GL.glPopAttrib();
	}

	/**
	 *
	 */
	public void glPopClientAttrib() {
		GL.glPopClientAttrib();
	}

	/**
	 *
	 */
	public void glPopMatrix() {
		GL.glPopMatrix();
	}

	/**
	 *
	 */
	public void glPopName() {
		GL.glPopName();
	}

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glProgramEnvParameter4fARB(int target, int index, float x, float y, float z, float w) {
		GL.glProgramEnvParameter4fARB(target, index, x, y, z, w);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glProgramEnvParameter4ARB(int target, int index, FloatBuffer params) {
		GL.glProgramEnvParameter4ARB(target, index, params);
	}

	/**
	 * @param target
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glProgramLocalParameter4fARB(int target, int index, float x, float y, float z, float w) {
		GL.glProgramLocalParameter4fARB(target, index, x, y, z, w);
	}

	/**
	 * @param target
	 * @param index
	 * @param params
	 */
	public void glProgramLocalParameter4ARB(int target, int index, FloatBuffer params) {
		GL.glProgramLocalParameter4ARB(target, index, params);
	}

	/**
	 * @param target
	 * @param format
	 * @param string
	 */
	public void glProgramStringARB(int target, int format, ByteBuffer string) {
		GL.glProgramStringARB(target, format, string);
	}

	/**
	 * @param mask
	 */
	public void glPushAttrib(int mask) {
		GL.glPushAttrib(mask);
	}

	/**
	 * @param mask
	 */
	public void glPushClientAttrib(int mask) {
		GL.glPushClientAttrib(mask);
	}

	/**
	 *
	 */
	public void glPushMatrix() {
		GL.glPushMatrix();
	}

	/**
	 * @param name
	 */
	public void glPushName(int name) {
		GL.glPushName(name);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glRasterPos2f(float x, float y) {
		GL.glRasterPos2f(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glRasterPos2i(int x, int y) {
		GL.glRasterPos2i(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glRasterPos3f(float x, float y, float z) {
		GL.glRasterPos3f(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glRasterPos3i(int x, int y, int z) {
		GL.glRasterPos3i(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glRasterPos4f(float x, float y, float z, float w) {
		GL.glRasterPos4f(x, y, z, w);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glRasterPos4i(int x, int y, int z, int w) {
		GL.glRasterPos4i(x, y, z, w);
	}

	/**
	 * @param mode
	 */
	public void glReadBuffer(int mode) {
		GL.glReadBuffer(mode);
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
	public void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
		GL.glReadPixels(x, y, width, height, format, type, pixels);
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
	public void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
		GL.glReadPixels(x, y, width, height, format, type, pixels);
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
	public void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
		GL.glReadPixels(x, y, width, height, format, type, pixels);
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void glRectf(float x1, float y1, float x2, float y2) {
		GL.glRectf(x1, y1, x2, y2);
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void glRecti(int x1, int y1, int x2, int y2) {
		GL.glRecti(x1, y1, x2, y2);
	}

	/**
	 * @param mode
	 *
	 * @return
	 */
	public int glRenderMode(int mode) {
		return GL.glRenderMode(mode);
	}

	/**
	 * @param target
	 */
	public void glResetHistogram(int target) {
		GL.glResetHistogram(target);
	}

	/**
	 * @param target
	 */
	public void glResetMinmax(int target) {
		GL.glResetMinmax(target);
	}

	/**
	 * @param angle
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glRotatef(float angle, float x, float y, float z) {
		GL.glRotatef(angle, x, y, z);
	}

	/**
	 * @param value
	 * @param invert
	 */
	public void glSampleCoverage(float value, boolean invert) {
		GL.glSampleCoverage(value, invert);
	}

	/**
	 * @param value
	 * @param invert
	 */
	public void glSampleCoverageARB(float value, boolean invert) {
		GL.glSampleCoverageARB(value, invert);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glScalef(float x, float y, float z) {
		GL.glScalef(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void glScissor(int x, int y, int width, int height) {
		GL.glScissor(x, y, width, height);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3b(byte red, byte green, byte blue) {
		GL.glSecondaryColor3b(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3bEXT(byte red, byte green, byte blue) {
		GL.glSecondaryColor3bEXT(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3f(float red, float green, float blue) {
		GL.glSecondaryColor3f(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3fEXT(float red, float green, float blue) {
		GL.glSecondaryColor3fEXT(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3ub(byte red, byte green, byte blue) {
		GL.glSecondaryColor3ub(red, green, blue);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void glSecondaryColor3ubEXT(byte red, byte green, byte blue) {
		GL.glSecondaryColor3ubEXT(red, green, blue);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param data
	 */
	public void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data) {
		GL.glSecondaryColorPointer(size, unsigned, stride, data);
	}

	/**
	 * @param size
	 * @param stride
	 * @param data
	 */
	public void glSecondaryColorPointer(int size, int stride, FloatBuffer data) {
		GL.glSecondaryColorPointer(size, stride, data);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glSecondaryColorPointer(int size, int type, int stride, int buffer_offset) {
		GL.glSecondaryColorPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glSecondaryColorPointerEXT(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		GL.glSecondaryColorPointerEXT(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glSecondaryColorPointerEXT(int size, int stride, FloatBuffer pPointer) {
		GL.glSecondaryColorPointerEXT(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glSecondaryColorPointerEXT(int size, int type, int stride, int buffer_offset) {
		GL.glSecondaryColorPointerEXT(size, type, stride, buffer_offset);
	}

	/**
	 * @param buffer
	 */
	public void glSelectBuffer(IntBuffer buffer) {
		GL.glSelectBuffer(buffer);
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
/*	public void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, Buffer row, Buffer column) {
		GL.glSeparableFilter2D(target, internalformat, width, height, format, type, row, column);
	}
*/
	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		GL.glSetInvariantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		GL.glSetInvariantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		GL.glSetInvariantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glSetInvariantEXT(int id, FloatBuffer pAddr) {
		GL.glSetInvariantEXT(id, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		GL.glSetLocalConstantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		GL.glSetLocalConstantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		GL.glSetLocalConstantEXT(id, unsigned, pAddr);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glSetLocalConstantEXT(int id, FloatBuffer pAddr) {
		GL.glSetLocalConstantEXT(id, pAddr);
	}

	/**
	 * @param mode
	 */
	public void glShadeModel(int mode) {
		GL.glShadeModel(mode);
	}

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 */
	public void glShaderOp1EXT(int op, int res, int arg1) {
		GL.glShaderOp1EXT(op, res, arg1);
	}

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 */
	public void glShaderOp2EXT(int op, int res, int arg1, int arg2) {
		GL.glShaderOp2EXT(op, res, arg1, arg2);
	}

	/**
	 * @param op
	 * @param res
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public void glShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3) {
		GL.glShaderOp3EXT(op, res, arg1, arg2, arg3);
	}

	/**
	 * @param shaderObj
	 * @param string
	 */
	public void glShaderSourceARB(int shaderObj, ByteBuffer string) {
		GL.glShaderSourceARB(shaderObj, string);
	}

	/**
	 * @param shaderObj
	 * @param strings
	 */
/*	public void glShaderSourceARB(int shaderObj, ByteBuffer[] strings) {
		GL.glShaderSourceARB(shaderObj, strings);
	}
*/
	/**
	 * @param func
	 * @param ref
	 * @param mask
	 */
	public void glStencilFunc(int func, int ref, int mask) {
		GL.glStencilFunc(func, ref, mask);
	}

	/**
	 * @param mask
	 */
	public void glStencilMask(int mask) {
		GL.glStencilMask(mask);
	}

	/**
	 * @param fail
	 * @param zfail
	 * @param zpass
	 */
	public void glStencilOp(int fail, int zfail, int zpass) {
		GL.glStencilOp(fail, zfail, zpass);
	}

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	public void glSwizzleEXT(int res, int in, int outX, int outY, int outZ, int outW) {
		GL.glSwizzleEXT(res, in, outX, outY, outZ, outW);
	}

	/**
	 * @param s
	 */
	public void glTexCoord1f(float s) {
		GL.glTexCoord1f(s);
	}

	/**
	 * @param s
	 * @param t
	 */
	public void glTexCoord2f(float s, float t) {
		GL.glTexCoord2f(s, t);
	}

	/**
	 * @param s
	 * @param t
	 * @param r
	 */
	public void glTexCoord3f(float s, float t, float r) {
		GL.glTexCoord3f(s, t, r);
	}

	/**
	 * @param s
	 * @param t
	 * @param r
	 * @param q
	 */
	public void glTexCoord4f(float s, float t, float r, float q) {
		GL.glTexCoord4f(s, t, r, q);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glTexCoordPointer(int size, int stride, FloatBuffer pointer) {
		GL.glTexCoordPointer(size, stride, pointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glTexCoordPointer(int size, int type, int stride, int buffer_offset) {
		GL.glTexCoordPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glTexEnv(int target, int pname, FloatBuffer params) {
		GL.glTexEnv(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param params
	 */
	public void glTexEnv(int target, int pname, IntBuffer params) {
		GL.glTexEnv(target, pname, params);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexEnvf(int target, int pname, float param) {
		GL.glTexEnvf(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexEnvi(int target, int pname, int param) {
		GL.glTexEnvi(target, pname, param);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glTexGen(int coord, int pname, FloatBuffer params) {
		GL.glTexGen(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param params
	 */
	public void glTexGen(int coord, int pname, IntBuffer params) {
		GL.glTexGen(coord, pname, params);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	public void glTexGenf(int coord, int pname, float param) {
		GL.glTexGenf(coord, pname, param);
	}

	/**
	 * @param coord
	 * @param pname
	 * @param param
	 */
	public void glTexGeni(int coord, int pname, int param) {
		GL.glTexGeni(coord, pname, param);
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
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
		GL.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
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
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
		GL.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
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
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
		GL.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
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
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
		GL.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
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
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
		GL.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
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
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
		GL.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
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
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
		GL.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
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
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
		GL.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
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
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
		GL.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
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
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
		GL.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
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
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
		GL.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
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
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
		GL.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameter(int target, int pname, FloatBuffer param) {
		GL.glTexParameter(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameter(int target, int pname, IntBuffer param) {
		GL.glTexParameter(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameterf(int target, int pname, float param) {
		GL.glTexParameterf(target, pname, param);
	}

	/**
	 * @param target
	 * @param pname
	 * @param param
	 */
	public void glTexParameteri(int target, int pname, int param) {
		GL.glTexParameteri(target, pname, param);
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
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
		GL.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
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
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
		GL.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
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
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
		GL.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
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
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
		GL.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
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
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
		GL.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
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
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
		GL.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
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
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
		GL.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
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
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
		GL.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
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
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
		GL.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
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
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
		GL.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glTranslatef(float x, float y, float z) {
		GL.glTranslatef(x, y, z);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform1ARB(int location, FloatBuffer values) {
		GL.glUniform1ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform1ARB(int location, IntBuffer values) {
		GL.glUniform1ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public void glUniform1fARB(int location, float v0) {
		GL.glUniform1fARB(location, v0);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public void glUniform1iARB(int location, int v0) {
		GL.glUniform1iARB(location, v0);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform2ARB(int location, FloatBuffer values) {
		GL.glUniform2ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform2ARB(int location, IntBuffer values) {
		GL.glUniform2ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public void glUniform2fARB(int location, float v0, float v1) {
		GL.glUniform2fARB(location, v0, v1);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public void glUniform2iARB(int location, int v0, int v1) {
		GL.glUniform2iARB(location, v0, v1);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform3ARB(int location, FloatBuffer values) {
		GL.glUniform3ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform3ARB(int location, IntBuffer values) {
		GL.glUniform3ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public void glUniform3fARB(int location, float v0, float v1, float v2) {
		GL.glUniform3fARB(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public void glUniform3iARB(int location, int v0, int v1, int v2) {
		GL.glUniform3iARB(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform4ARB(int location, FloatBuffer values) {
		GL.glUniform4ARB(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform4ARB(int location, IntBuffer values) {
		GL.glUniform4ARB(location, values);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public void glUniform4fARB(int location, float v0, float v1, float v2, float v3) {
		GL.glUniform4fARB(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public void glUniform4iARB(int location, int v0, int v1, int v2, int v3) {
		GL.glUniform4iARB(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices) {
		GL.glUniformMatrix2ARB(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices) {
		GL.glUniformMatrix3ARB(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices) {
		GL.glUniformMatrix4ARB(location, transpose, matrices);
	}

	/**
	 *
	 */
	public void glUnlockArraysEXT() {
		GL.glUnlockArraysEXT();
	}

	/**
	 * @param target
	 *
	 * @return
	 */
	public boolean glUnmapBuffer(int target) {
		return GL.glUnmapBuffer(target);
	}

	/**
	 * @param target
	 *
	 * @return
	 */
	public boolean glUnmapBufferARB(int target) {
		return GL.glUnmapBufferARB(target);
	}

	/**
	 * @param programObj
	 */
	public void glUseProgramObjectARB(int programObj) {
		GL.glUseProgramObjectARB(programObj);
	}

	/**
	 * @param programObj
	 */
	public void glValidateProgramARB(int programObj) {
		GL.glValidateProgramARB(programObj);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glVariantEXT(int id, ByteBuffer pAddr) {
		GL.glVariantEXT(id, pAddr);
	}

	/**
	 * @param id
	 * @param pfAddr
	 */
	public void glVariantEXT(int id, FloatBuffer pfAddr) {
		GL.glVariantEXT(id, pfAddr);
	}

	/**
	 * @param id
	 * @param piAddr
	 */
	public void glVariantEXT(int id, IntBuffer piAddr) {
		GL.glVariantEXT(id, piAddr);
	}

	/**
	 * @param id
	 * @param psAddr
	 */
	public void glVariantEXT(int id, ShortBuffer psAddr) {
		GL.glVariantEXT(id, psAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, boolean unsigned, int stride, ByteBuffer pAddr) {
		GL.glVariantPointerEXT(id, unsigned, stride, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, boolean unsigned, int stride, IntBuffer pAddr) {
		GL.glVariantPointerEXT(id, unsigned, stride, pAddr);
	}

	/**
	 * @param id
	 * @param unsigned
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr) {
		GL.glVariantPointerEXT(id, unsigned, stride, pAddr);
	}

	/**
	 * @param id
	 * @param stride
	 * @param pAddr
	 */
	public void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr) {
		GL.glVariantPointerEXT(id, stride, pAddr);
	}

	/**
	 * @param id
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glVariantPointerEXT(int id, int type, int stride, int buffer_offset) {
		GL.glVariantPointerEXT(id, type, stride, buffer_offset);
	}

	/**
	 * @param id
	 * @param pAddr
	 */
	public void glVariantuEXT(int id, ByteBuffer pAddr) {
		GL.glVariantuEXT(id, pAddr);
	}

	/**
	 * @param id
	 * @param piAddr
	 */
	public void glVariantuEXT(int id, IntBuffer piAddr) {
		GL.glVariantuEXT(id, piAddr);
	}

	/**
	 * @param id
	 * @param psAddr
	 */
	public void glVariantuEXT(int id, ShortBuffer psAddr) {
		GL.glVariantuEXT(id, psAddr);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glVertex2f(float x, float y) {
		GL.glVertex2f(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glVertex2i(int x, int y) {
		GL.glVertex2i(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertex3f(float x, float y, float z) {
		GL.glVertex3f(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertex3i(int x, int y, int z) {
		GL.glVertex3i(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertex4f(float x, float y, float z, float w) {
		GL.glVertex4f(x, y, z, w);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertex4i(int x, int y, int z, int w) {
		GL.glVertex4i(x, y, z, w);
	}

	/**
	 * @param index
	 * @param x
	 */
	public void glVertexAttrib1fARB(int index, float x) {
		GL.glVertexAttrib1fARB(index, x);
	}

	/**
	 * @param index
	 * @param x
	 */
	public void glVertexAttrib1sARB(int index, short x) {
		GL.glVertexAttrib1sARB(index, x);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	public void glVertexAttrib2fARB(int index, float x, float y) {
		GL.glVertexAttrib2fARB(index, x, y);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 */
	public void glVertexAttrib2sARB(int index, short x, short y) {
		GL.glVertexAttrib2sARB(index, x, y);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertexAttrib3fARB(int index, float x, float y, float z) {
		GL.glVertexAttrib3fARB(index, x, y, z);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glVertexAttrib3sARB(int index, short x, short y, short z) {
		GL.glVertexAttrib3sARB(index, x, y, z);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertexAttrib4fARB(int index, float x, float y, float z, float w) {
		GL.glVertexAttrib4fARB(index, x, y, z, w);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
		GL.glVertexAttrib4NubARB(index, x, y, z, w);
	}

	/**
	 * @param index
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public void glVertexAttrib4sARB(int index, short x, short y, short z, short w) {
		GL.glVertexAttrib4sARB(index, x, y, z, w);
	}

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		GL.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		GL.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param unsigned
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		GL.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param normalized
	 * @param stride
	 * @param buffer
	 */
	public void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		GL.glVertexAttribPointerARB(index, size, normalized, stride, buffer);
	}

	/**
	 * @param index
	 * @param size
	 * @param type
	 * @param normalized
	 * @param stride
	 * @param bufferOffset
	 */
	public void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, int bufferOffset) {
		GL.glVertexAttribPointerARB(index, size, type, normalized, stride, bufferOffset);
	}

	/**
	 * @param count
	 */
	public void glVertexBlendARB(int count) {
		GL.glVertexBlendARB(count);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glVertexPointer(int size, int stride, FloatBuffer pointer) {
		GL.glVertexPointer(size, stride, pointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glVertexPointer(int size, int type, int stride, int buffer_offset) {
		GL.glVertexPointer(size, type, stride, buffer_offset);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pointer
	 */
	public void glVertexPointer(int size, int stride, IntBuffer pointer) {
		GL.glVertexPointer(size, stride, pointer);
	}

	/**
	 * @param weight
	 */
	public void glVertexWeightfEXT(float weight) {
		GL.glVertexWeightfEXT(weight);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glVertexWeightPointerEXT(int size, int stride, FloatBuffer pPointer) {
		GL.glVertexWeightPointerEXT(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glVertexWeightPointerEXT(int size, int type, int stride, int buffer_offset) {
		GL.glVertexWeightPointerEXT(size, type, stride, buffer_offset);
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void glViewport(int x, int y, int width, int height) {
		GL.glViewport(x, y, width, height);
	}

	/**
	 * @param pWeights
	 */
	public void glWeightARB(ByteBuffer pWeights) {
		GL.glWeightARB(pWeights);
	}

	/**
	 * @param pfWeights
	 */
	public void glWeightARB(FloatBuffer pfWeights) {
		GL.glWeightARB(pfWeights);
	}

	/**
	 * @param piWeights
	 */
	public void glWeightARB(IntBuffer piWeights) {
		GL.glWeightARB(piWeights);
	}

	/**
	 * @param psWeights
	 */
	public void glWeightARB(ShortBuffer psWeights) {
		GL.glWeightARB(psWeights);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		GL.glWeightPointerARB(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer) {
		GL.glWeightPointerARB(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param unsigned
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, boolean unsigned, int stride, ShortBuffer pPointer) {
		GL.glWeightPointerARB(size, unsigned, stride, pPointer);
	}

	/**
	 * @param size
	 * @param stride
	 * @param pPointer
	 */
	public void glWeightPointerARB(int size, int stride, FloatBuffer pPointer) {
		GL.glWeightPointerARB(size, stride, pPointer);
	}

	/**
	 * @param size
	 * @param type
	 * @param stride
	 * @param buffer_offset
	 */
	public void glWeightPointerARB(int size, int type, int stride, int buffer_offset) {
		GL.glWeightPointerARB(size, type, stride, buffer_offset);
	}

	/**
	 * @param pWeights
	 */
	public void glWeightuARB(ByteBuffer pWeights) {
		GL.glWeightuARB(pWeights);
	}

	/**
	 * @param piWeights
	 */
	public void glWeightuARB(IntBuffer piWeights) {
		GL.glWeightuARB(piWeights);
	}

	/**
	 * @param psWeights
	 */
	public void glWeightuARB(ShortBuffer psWeights) {
		GL.glWeightuARB(psWeights);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2f(float x, float y) {
		GL.glWindowPos2f(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2fARB(float x, float y) {
		GL.glWindowPos2fARB(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2i(int x, int y) {
		GL.glWindowPos2i(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2iARB(int x, int y) {
		GL.glWindowPos2iARB(x, y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void glWindowPos2sARB(short x, short y) {
		GL.glWindowPos2sARB(x, y);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3f(float x, float y, float z) {
		GL.glWindowPos3f(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3fARB(float x, float y, float z) {
		GL.glWindowPos3fARB(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3i(int x, int y, int z) {
		GL.glWindowPos3i(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3iARB(int x, int y, int z) {
		GL.glWindowPos3iARB(x, y, z);
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void glWindowPos3sARB(short x, short y, short z) {
		GL.glWindowPos3sARB(x, y, z);
	}

	/**
	 * @param res
	 * @param in
	 * @param outX
	 * @param outY
	 * @param outZ
	 * @param outW
	 */
	public void glWriteMaskEXT(int res, int in, int outX, int outY, int outZ, int outW) {
		GL.glWriteMaskEXT(res, in, outX, outY, outZ, outW);
	}

	// ----------------------------------------------------------
	// ----------------------[ OpenGL 2.0 ]----------------------
	// ----------------------------------------------------------

	/**
	 * @param shader
	 * @param string
	 */
	public void glShaderSource(int shader, ByteBuffer string) {
		GL.glShaderSource(shader, string);
	}

	/**
	 * @param shader
	 * @param strings
	 */
/*	public void glShaderSource(int shader, ByteBuffer[] strings) {
		GL.glShaderSource(shader, strings);
	}
*/
	/**
	 * @param type
	 *
	 * @return
	 */
	public int glCreateShader(int type) {
		return GL.glCreateShader(type);
	}

	/**
	 * @param shader
	 *
	 * @return
	 */
	public boolean glIsShader(int shader) {
		return GL.glIsShader(shader);
	}

	/**
	 * @param shader
	 */
	public void glCompileShader(int shader) {
		GL.glCompileShader(shader);
	}

	/**
	 * @param shader
	 */
	public void glDeleteShader(int shader) {
		GL.glDeleteShader(shader);
	}

	/**
	 * @return
	 */
	public int glCreateProgram() {
		return GL.glCreateProgram();
	}

	/**
	 * @param program
	 *
	 * @return
	 */
	public boolean glIsProgram(int program) {
		return GL.glIsProgram(program);
	}

	/**
	 * @param program
	 * @param shader
	 */
	public void glAttachShader(int program, int shader) {
		GL.glAttachShader(program, shader);
	}

	/**
	 * @param program
	 * @param shader
	 */
	public void glDetachShader(int program, int shader) {
		GL.glDetachShader(program, shader);
	}

	/**
	 * @param program
	 */
	public void glLinkProgram(int program) {
		GL.glLinkProgram(program);
	}

	/**
	 * @param program
	 */
	public void glUseProgram(int program) {
		GL.glUseProgram(program);
	}

	/**
	 * @param program
	 */
	public void glValidateProgram(int program) {
		GL.glValidateProgram(program);
	}

	/**
	 * @param program
	 */
	public void glDeleteProgram(int program) {
		GL.glDeleteProgram(program);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public void glUniform1f(int location, float v0) {
		GL.glUniform1f(location, v0);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public void glUniform2f(int location, float v0, float v1) {
		GL.glUniform2f(location, v0, v1);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public void glUniform3f(int location, float v0, float v1, float v2) {
		GL.glUniform3f(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public void glUniform4f(int location, float v0, float v1, float v2, float v3) {
		GL.glUniform4f(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param v0
	 */
	public void glUniform1i(int location, int v0) {
		GL.glUniform1i(location, v0);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 */
	public void glUniform2i(int location, int v0, int v1) {
		GL.glUniform2i(location, v0, v1);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	public void glUniform3i(int location, int v0, int v1, int v2) {
		GL.glUniform3i(location, v0, v1, v2);
	}

	/**
	 * @param location
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public void glUniform4i(int location, int v0, int v1, int v2, int v3) {
		GL.glUniform4i(location, v0, v1, v2, v3);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform1(int location, FloatBuffer values) {
		GL.glUniform1(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform2(int location, FloatBuffer values) {
		GL.glUniform2(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform3(int location, FloatBuffer values) {
		GL.glUniform3(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform4(int location, FloatBuffer values) {
		GL.glUniform4(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform1(int location, IntBuffer values) {
		GL.glUniform1(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform2(int location, IntBuffer values) {
		GL.glUniform2(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform3(int location, IntBuffer values) {
		GL.glUniform3(location, values);
	}

	/**
	 * @param location
	 * @param values
	 */
	public void glUniform4(int location, IntBuffer values) {
		GL.glUniform4(location, values);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices) {
		GL.glUniformMatrix2(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices) {
		GL.glUniformMatrix3(location, transpose, matrices);
	}

	/**
	 * @param location
	 * @param transpose
	 * @param matrices
	 */
	public void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {
		GL.glUniformMatrix4(location, transpose, matrices);
	}

	/**
	 * @param shader
	 * @param pname
	 * @param params
	 */
	public void glGetShader(int shader, int pname, IntBuffer params) {
		GL.glGetShader(shader, pname, params);
	}

	/**
	 * @param program
	 * @param pname
	 * @param params
	 */
	public void glGetProgram(int program, int pname, IntBuffer params) {
		GL.glGetProgram(program, pname, params);
	}

	/**
	 * @param shader
	 * @param length
	 * @param infoLog
	 */
	public void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
		GL.glGetShaderInfoLog(shader, length, infoLog);
	}

	/**
	 * @param program
	 * @param length
	 * @param infoLog
	 */
	public void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
		GL.glGetProgramInfoLog(program, length, infoLog);
	}

	/**
	 * @param program
	 * @param count
	 * @param shaders
	 */
	public void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
		GL.glGetAttachedShaders(program, count, shaders);
	}

	/**
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	public int glGetUniformLocation(int program, ByteBuffer name) {
		return GL.glGetUniformLocation(program, name);
	}

	/**
	 * @param program
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public void glGetActiveUniform(int program, int index,
	                                      IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		GL.glGetActiveUniform(program, index, length, size, type, name);
	}

	/**
	 * @param program
	 * @param location
	 * @param params
	 */
	public void glGetUniform(int program, int location, FloatBuffer params) {
		GL.glGetUniform(program, location, params);
	}

	/**
	 * @param program
	 * @param location
	 * @param params
	 */
	public void glGetUniform(int program, int location, IntBuffer params) {
		GL.glGetUniform(program, location, params);
	}

	/**
	 * @param shader
	 * @param length
	 * @param source
	 */
	public void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) {
		GL.glGetShaderSource(shader, length, source);
	}

	/**
	 * @param program
	 * @param index
	 * @param name
	 */
	public void glBindAttribLocation(int program, int index, ByteBuffer name) {
		GL.glBindAttribLocation(program, index, name);
	}

	/**
	 * @param program
	 * @param index
	 * @param length
	 * @param size
	 * @param type
	 * @param name
	 */
	public void glGetActiveAttrib(int program, int index,
	                                     IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		GL.glGetActiveAttrib(program, index, length, size, type, name);
	}

	/**
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	public int glGetAttribLocation(int program, ByteBuffer name) {
		return GL.glGetAttribLocation(program, name);
	}

	/**
	 * @param buffers
	 */
	public void glDrawBuffers(IntBuffer buffers) {
		GL.glDrawBuffers(buffers);
	}

	/**
	 * @param face
	 * @param func
	 * @param ref
	 * @param mask
	 */
	public void glStencilFuncSeparate(int face, int func, int ref, int mask) {
		GL.glStencilFuncSeparate(face, func, ref, mask);
	}

	/**
	 * @param face
	 * @param sfail
	 * @param dpfail
	 * @param dppass
	 */
	public void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		GL.glStencilOpSeparate(face, sfail, dpfail, dppass);
	}

	/**
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public void glDrawPixels(int width, int height, int format, int type, int buffer_offset) {
		GL.glDrawPixels(width, height, format, type, buffer_offset);
	}

	/**
	 * @param map
	 * @param buffer_offset
	 */
	public void glGetPixelMapfv(int map, int buffer_offset) {
		GL.glGetPixelMapfv(map, buffer_offset);
	}

	/**
	 * @param map
	 * @param buffer_offset
	 */
	public void glGetPixelMapuiv(int map, int buffer_offset) {
		GL.glGetPixelMapuiv(map, buffer_offset);
	}

	/**
	 * @param map
	 * @param buffer_offset
	 */
	public void glGetPixelMapusv(int map, int buffer_offset) {
		GL.glGetPixelMapusv(map, buffer_offset);
	}

	/**
	 * @param buffer_offset
	 */
	public void glGetPolygonStipple(int buffer_offset) {
		GL.glGetPolygonStipple(buffer_offset);
	}

	/**
	 * @param target
	 * @param level
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public void glGetTexImage(int target, int level, int format, int type, int buffer_offset) {
		GL.glGetTexImage(target, level, format, type, buffer_offset);
	}

	/**
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	public void glPixelMapfv(int map, int mapsize, int buffer_offset) {
		GL.glPixelMapfv(map, mapsize, buffer_offset);
	}

	/**
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	public void glPixelMapuiv(int map, int mapsize, int buffer_offset) {
		GL.glPixelMapuiv(map, mapsize, buffer_offset);
	}

	/**
	 * @param map
	 * @param mapsize
	 * @param buffer_offset
	 */
	public void glPixelMapusv(int map, int mapsize, int buffer_offset) {
		GL.glPixelMapusv(map, mapsize, buffer_offset);
	}

	/**
	 * @param buffer_offset
	 */
	public void glPolygonStipple(int buffer_offset) {
		GL.glPolygonStipple(buffer_offset);
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
	public void glReadPixels(int x, int y, int width, int height, int format, int type, int buffer_offset) {
		GL.glReadPixels(x, y, width, height, format, type, buffer_offset);
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
	public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, int buffer_offset) {
		GL.glTexImage1D(target, level, internalformat, width, border, format, type, buffer_offset);
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
	public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, int buffer_offset) {
		GL.glTexImage2D(target, level, internalformat, width, height, border, format, type, buffer_offset);
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
	public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, int buffer_offset) {
		GL.glTexSubImage1D(target, level, xoffset, width, format, type, buffer_offset);
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
	public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int buffer_offset) {
		GL.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, buffer_offset);
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
	public void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, int buffer_offset) {
		GL.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, buffer_offset);
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
	public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int buffer_offset) {
		GL.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, buffer_offset);
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
	public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, int buffer_offset) {
		GL.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, buffer_offset);
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
	public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, int buffer_offset) {
		GL.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, buffer_offset);
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
	public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, int buffer_offset) {
		GL.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, buffer_offset);
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
	public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, int buffer_offset) {
		GL.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, buffer_offset);
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
	public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, int buffer_offset) {
		GL.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, buffer_offset);
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
	public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, int buffer_offset) {
		GL.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, buffer_offset);
	}

	/**
	 * @param target
	 * @param lod
	 * @param buffer_offset
	 */
	public void glGetCompressedTexImage(int target, int lod, int buffer_offset) {
		GL.glGetCompressedTexImage(target, lod, buffer_offset);
	}

	/**
	 * @param target
	 * @param start
	 * @param count
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public void glColorSubTable(int target, int start, int count, int format, int type, int buffer_offset) {
		GL.glColorSubTable(target, start, count, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param internalFormat
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public void glColorTable(int target, int internalFormat, int width, int format, int type, int buffer_offset) {
		GL.glColorTable(target, internalFormat, width, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param internalformat
	 * @param width
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, int buffer_offset) {
		GL.glConvolutionFilter1D(target, internalformat, width, format, type, buffer_offset);
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
	public void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, int buffer_offset) {
		GL.glConvolutionFilter2D(target, internalformat, width, height, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public void glGetConvolutionFilter(int target, int format, int type, int buffer_offset) {
		GL.glGetConvolutionFilter(target, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param type
	 * @param buffer_offset
	 */
	public void glGetHistogram(int target, boolean reset, int format, int type, int buffer_offset) {
		GL.glGetHistogram(target, reset, format, type, buffer_offset);
	}

	/**
	 * @param target
	 * @param reset
	 * @param format
	 * @param types
	 * @param buffer_offset
	 */
	public void glGetMinmax(int target, boolean reset, int format, int types, int buffer_offset) {
		GL.glGetMinmax(target, reset, format, types, buffer_offset);
	}

	/**
	 * @param target
	 * @param format
	 * @param type
	 * @param row_offset
	 * @param column_offset
	 * @param span_offset
	 */
	public void glGetSeparableFilter(int target, int format, int type, int row_offset, int column_offset, int span_offset) {
		GL.glGetSeparableFilter(target, format, type, row_offset, column_offset, span_offset);
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
	public void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, int row_offset, int column_offset) {
		GL.glSeparableFilter2D(target, internalformat, width, height, format, type, row_offset, column_offset);
	}

}
