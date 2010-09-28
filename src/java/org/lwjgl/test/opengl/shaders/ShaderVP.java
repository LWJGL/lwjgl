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

import static org.lwjgl.opengl.ARBVertexProgram.*;
import static org.lwjgl.opengl.GL11.*;

final class ShaderVP extends Shader {

	final String file;
	final String source;

	final int ID;

	ShaderVP(final String shaderFile) {
		file = shaderFile;
		source = getShaderText(shaderFile);

		ID = glGenProgramsARB();

		glBindProgramARB(GL_VERTEX_PROGRAM_ARB, ID);
		glProgramStringARB(GL_VERTEX_PROGRAM_ARB, GL_PROGRAM_FORMAT_ASCII_ARB, source);

		checkProgramError(file, source);
	}

	void render() {
		glEnable(GL_VERTEX_PROGRAM_ARB);
		glBindProgramARB(GL_VERTEX_PROGRAM_ARB, ID);

		glProgramLocalParameter4fARB(GL_VERTEX_PROGRAM_ARB, 0,
		                             ShadersTest.getSin(), ShadersTest.getSpecularity() * 8.0f, 0.0f, 0.0f);

		ShadersTest.renderObject();

		glDisable(GL_VERTEX_PROGRAM_ARB);
	}

	void cleanup() {
		glDeleteProgramsARB(ID);
	}

}