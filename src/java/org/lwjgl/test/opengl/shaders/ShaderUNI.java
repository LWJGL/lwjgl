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
import org.lwjgl.opengl.ARBUniformBufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

final class ShaderUNI extends Shader {

	final String file;
	final ByteBuffer source;

	final int shaderID;
	final int programID;

	final int uniformA;
	final int uniformB;

	ShaderUNI(final String shaderFile) {
		file = shaderFile;
		source = getShaderText(shaderFile);

		shaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(shaderID, source);
		GL20.glCompileShader(shaderID);

		printShaderObjectInfoLog(file, shaderID);

		GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS, programBuffer);
		if ( programBuffer.get(0) == GL11.GL_FALSE )
			ShadersTest.kill("A compilation error occured in a vertex shader.");

		programID = GL20.glCreateProgram();

		GL20.glAttachShader(programID, shaderID);
		GL20.glLinkProgram(programID);

		printShaderProgramInfoLog(programID);

		GL20.glGetProgram(programID, GL20.GL_LINK_STATUS, programBuffer);
		if ( programBuffer.get(0) == GL11.GL_FALSE )
			ShadersTest.kill("A linking error occured in a shader program.");

		uniformA = getUniformLocation(programID, "uniformA");
		uniformB = getUniformLocation(programID, "uniformB");

		String[] uniformNames = { "uniformA", "uniformB" };
		IntBuffer tmp = BufferUtils.createIntBuffer(uniformNames.length);

		ARBUniformBufferObject.glGetUniformIndices(programID, toByteBuffer(uniformNames), tmp);

		System.out.println("uniformA index = " + tmp.get(0));
		System.out.println("uniformB index = " + tmp.get(1));
	}

	private static ByteBuffer toByteBuffer(String[] strs) {
		int length = 0;
		for ( int i = 0; i < strs.length; i++ )
			length += strs[i].length() + 1; // +1 for the NULL-character

		final ByteBuffer buff = BufferUtils.createByteBuffer(length);
		for ( int i = 0; i < strs.length; i++ ) {
			buff.put(strs[i].getBytes());
			buff.put((byte)0); // The ending NULL-character
		}
		buff.flip();

		return buff;
	}

	void render() {
		GL20.glUseProgram(programID);

		GL20.glUniform2f(uniformA, ShadersTest.getSin(), ShadersTest.getSpecularity() * 8.0f);
		GL20.glUniform3f(uniformB, 0.0f, 0.7f, 0.0f);

		ShadersTest.renderObject();

		GL20.glUseProgram(0);
	}

	void cleanup() {
		GL20.glDetachShader(programID, shaderID);

		GL20.glDeleteShader(shaderID);
		GL20.glDeleteProgram(programID);
	}

}