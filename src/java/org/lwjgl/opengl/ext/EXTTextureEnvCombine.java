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
 * Insert the type's description here.
 * Creation date: (22/02/00 01:26:05)
 */
public interface EXTTextureEnvCombine {
	public static final int COMBINE_EXT				= 0x8570;
	public static final int COMBINE_RGB_EXT			= 0x8571;
	public static final int COMBINE_ALPHA_EXT		= 0x8572;
	public static final int SOURCE0_RGB_EXT			= 0x8580;
	public static final int SOURCE1_RGB_EXT			= 0x8581;
	public static final int SOURCE2_RGB_EXT			= 0x8582;
	public static final int SOURCE0_ALPHA_EXT		= 0x8588;
	public static final int SOURCE1_ALPHA_EXT		= 0x8589;
	public static final int SOURCE2_ALPHA_EXT		= 0x858A;
	public static final int OPERAND0_RGB_EXT			= 0x8590;
	public static final int OPERAND1_RGB_EXT			= 0x8591;
	public static final int OPERAND2_RGB_EXT			= 0x8592;
	public static final int OPERAND0_ALPHA_EXT		= 0x8598;
	public static final int OPERAND1_ALPHA_EXT		= 0x8599;
	public static final int OPERAND2_ALPHA_EXT		= 0x859A;
	public static final int RGB_SCALE_EXT			= 0x8573;
	public static final int ADD_SIGNED_EXT			= 0x8574;
	public static final int INTERPOLATE_EXT			= 0x8575;
	public static final int CONSTANT_EXT				= 0x8576;
	public static final int PRIMARY_COLOR_EXT		= 0x8577;
	public static final int PREVIOUS_EXT				= 0x8578;
}
