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
 
 package org.lwjgl.opengl.ext;

/**
 * EXT_bgra_constants
 * 
 * @author cas
 */
public interface EXTPalettedTexture {
	public static final int GL_COLOR_TABLE_FORMAT_EXT         = 0x80D8;
	public static final int GL_COLOR_TABLE_WIDTH_EXT          = 0x80D9;
	public static final int GL_COLOR_TABLE_RED_SIZE_EXT       = 0x80DA;
	public static final int GL_COLOR_TABLE_GREEN_SIZE_EXT     = 0x80DB;
	public static final int GL_COLOR_TABLE_BLUE_SIZE_EXT      = 0x80DC;
	public static final int GL_COLOR_TABLE_ALPHA_SIZE_EXT     = 0x80DD;
	public static final int GL_COLOR_TABLE_LUMINANCE_SIZE_EXT = 0x80DE;
	public static final int GL_COLOR_TABLE_INTENSITY_SIZE_EXT = 0x80DF;

	public static final int GL_COLOR_INDEX1_EXT               = 0x80E2;
	public static final int GL_COLOR_INDEX2_EXT               = 0x80E3;
	public static final int GL_COLOR_INDEX4_EXT               = 0x80E4;
	public static final int GL_COLOR_INDEX8_EXT               = 0x80E5;
	public static final int GL_COLOR_INDEX12_EXT              = 0x80E6;
	public static final int GL_COLOR_INDEX16_EXT              = 0x80E7;
}
