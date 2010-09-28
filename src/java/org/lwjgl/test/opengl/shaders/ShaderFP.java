/*
 * Copyright (c) 2002-2008 LWJGL Project
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
/*
 * Created by LWJGL.
 * User: spasi
 * Date: 2004-03-30
 * Time: 9:55:38 pm
 */

package org.lwjgl.test.opengl.shaders;

import org.lwjgl.opengl.ARBFragmentProgram;
import org.lwjgl.opengl.ARBVertexProgram;

import static org.lwjgl.opengl.ARBProgram.*;
import static org.lwjgl.opengl.GL11.*;

final class ShaderFP extends Shader {

	final String vpFile;
	final String vpSource;

	final int vpID;

	final String fpFile;
	final String fpSource;

	final int fpID;

	ShaderFP(final String vpShaderFile, final String fpShaderFile) {
		// Initialize the vertex program.
		vpFile = vpShaderFile;
		vpSource = getShaderText(vpShaderFile);

		vpID = glGenProgramsARB();

		glBindProgramARB(ARBVertexProgram.GL_VERTEX_PROGRAM_ARB, vpID);
		glProgramStringARB(ARBVertexProgram.GL_VERTEX_PROGRAM_ARB, GL_PROGRAM_FORMAT_ASCII_ARB, vpSource);

		checkProgramError(vpFile, vpSource);

		// Initialize the fragment program.
		fpFile = fpShaderFile;
		fpSource = getShaderText(fpShaderFile);

		fpID = glGenProgramsARB();

		glBindProgramARB(ARBFragmentProgram.GL_FRAGMENT_PROGRAM_ARB, fpID);
		glProgramStringARB(ARBFragmentProgram.GL_FRAGMENT_PROGRAM_ARB, GL_PROGRAM_FORMAT_ASCII_ARB, fpSource);

		checkProgramError(fpFile, fpSource);
	}

	void render() {
		glEnable(ARBVertexProgram.GL_VERTEX_PROGRAM_ARB);
		glBindProgramARB(ARBVertexProgram.GL_VERTEX_PROGRAM_ARB, vpID);

		glEnable(ARBFragmentProgram.GL_FRAGMENT_PROGRAM_ARB);
		glBindProgramARB(ARBFragmentProgram.GL_FRAGMENT_PROGRAM_ARB, fpID);

		glProgramLocalParameter4fARB(ARBVertexProgram.GL_VERTEX_PROGRAM_ARB, 0,
		                             ShadersTest.getSin(), ShadersTest.getSpecularity() * 8.0f, 0.0f, 0.0f);

		glProgramLocalParameter4fARB(ARBFragmentProgram.GL_FRAGMENT_PROGRAM_ARB, 0,
		                             ShadersTest.getSin(), ShadersTest.getSpecularity() * 8.0f,
		                             -ShadersTest.getDisplayWidth() * 0.5f, -ShadersTest.getDisplayHeight() * 0.5f);

		ShadersTest.renderObject();

		glDisable(ARBVertexProgram.GL_VERTEX_PROGRAM_ARB);
		glDisable(ARBFragmentProgram.GL_FRAGMENT_PROGRAM_ARB);
	}

	void cleanup() {
		glDeleteProgramsARB(vpID);
		glDeleteProgramsARB(fpID);
	}

}