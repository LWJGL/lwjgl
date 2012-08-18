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
 * Date: 2009-12-04
 */

package org.lwjgl.test.opengl.shaders;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBUniformBufferObject.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

final class ShaderUNI extends Shader {

	final String file;
	final String source;

	final int shaderID;
	final int programID;

	final int bufferID;
	final FloatBuffer buffer;

	final int uniformA_index;
	final int uniformA_offset;

	final int uniformB_index;
	final int uniformB_offset;

	ShaderUNI(final String shaderFile) {
		file = shaderFile;
		source = getShaderText(shaderFile);

		shaderID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(shaderID, source);
		glCompileShader(shaderID);

		printShaderObjectInfoLog(file, shaderID);

		if ( glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE )
			ShadersTest.kill("A compilation error occured in a vertex shader.");

		programID = glCreateProgram();

		glAttachShader(programID, shaderID);
		glLinkProgram(programID);

		printShaderProgramInfoLog(programID);

		if ( glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE )
			ShadersTest.kill("A linking error occured in a shader program.");

		final String[] uniformNames = { "uniformA", "uniformB" };

		// Get uniform block index and data size
		final int blockIndex = glGetUniformBlockIndex(programID, "test");
		final int blockSize = glGetActiveUniformBlocki(programID, blockIndex, GL_UNIFORM_BLOCK_DATA_SIZE);

		System.out.println("blockSize = " + blockSize);

		// Create uniform buffer object and allocate a ByteBuffer
		bufferID = glGenBuffers();
		glBindBuffer(GL_UNIFORM_BUFFER, bufferID);
		glBufferData(GL_UNIFORM_BUFFER, blockSize, GL_DYNAMIC_DRAW);
		buffer = BufferUtils.createFloatBuffer(blockSize);

		// Attach UBO and associate uniform block to binding point 0
		glBindBufferBase(GL_UNIFORM_BUFFER, 0, bufferID);
		glUniformBlockBinding(programID, blockIndex, 0);

		// Get uniform information
		IntBuffer indexes = BufferUtils.createIntBuffer(uniformNames.length);
		IntBuffer params = BufferUtils.createIntBuffer(uniformNames.length);

		glGetUniformIndices(programID, uniformNames, indexes);
		uniformA_index = indexes.get(0);
		uniformB_index = indexes.get(1);

		glGetActiveUniforms(programID, indexes, GL_UNIFORM_OFFSET, params);
		uniformA_offset = params.get(0);
		uniformB_offset = params.get(1);

		System.out.println("\nuniformA index = " + uniformA_index);
		System.out.println("uniformB index = " + uniformB_index);

		System.out.println("\nuniformA offset = " + uniformA_offset + " - should be 0 for std140");
		System.out.println("uniformB offset = " + uniformB_offset + " - should be 16 for std140");

		Util.checkGLError();
	}

	void render() {
		glUseProgram(programID);

		//* -- std140 layout
		// Uniform A
		buffer.put(0, ShadersTest.getSin()).put(1, ShadersTest.getSpecularity() * 8.0f);
		// Uniform B - str140 alignment at 16 bytes
		buffer.put(4, 0.0f).put(5, 0.7f).put(6, 0.0f);

		glBindBuffer(GL_UNIFORM_BUFFER, bufferID);
		glBufferData(GL_UNIFORM_BUFFER, buffer, GL_DYNAMIC_DRAW);
		//*/

		/* -- non-std140 layout
		// Uniform A
		buffer.put(ShadersTest.getSin()).put(ShadersTest.getSpecularity() * 8.0f);
		buffer.flip();
		glBufferSubData(GL_UNIFORM_BUFFER, uniformA_offset, buffer);
		// Uniform B
		buffer.clear();
		buffer.put(0.0f).put(0.7f).put(0.0f);
		buffer.flip();
		glBufferSubData(GL_UNIFORM_BUFFER, uniformB_offset, buffer);
		//*/

		ShadersTest.renderObject();

		glUseProgram(0);
	}

	void cleanup() {
		glDeleteBuffers(bufferID);

		glDetachShader(programID, shaderID);

		glDeleteShader(shaderID);
		glDeleteProgram(programID);
	}

}