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
package org.lwjgl.opengl;

public interface ARB_fragment_shader {
	/*
	 * Accepted by the <shaderType> argument of CreateShaderObjectARB and
	 * returned by the <params> parameter of GetObjectParameter{fi}vARB:
	*/
	public static final int GL_FRAGMENT_SHADER_ARB = 0x8B30;

	/*
	 * Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS_ARB = 0x8B49;
	public static final int GL_MAX_TEXTURE_COORDS_ARB = 0x8871;
	public static final int GL_MAX_TEXTURE_IMAGE_UNITS_ARB = 0x8872;

	/*
	 * Accepted by the <target> parameter of Hint and the <pname> parameter of
	 * GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev:
	*/
	public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT_ARB = 0x8B8B;
}
