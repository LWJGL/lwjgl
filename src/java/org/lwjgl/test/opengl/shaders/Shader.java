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
 * Time: 8:41:42 pm
 */
package org.lwjgl.test.opengl.shaders;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBProgram;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

abstract class Shader {

	private static final IntBuffer int_buffer = BufferUtils.createIntBuffer(16);
	protected static IntBuffer programBuffer = BufferUtils.createIntBuffer(1);
	protected static ByteBuffer fileBuffer = BufferUtils.createByteBuffer(1024 * 10);

	protected Shader() {
	}

	abstract void render();

	abstract void cleanup();

	/**
	 * Obtain a GL integer value from the driver
	 *
	 * @param gl_enum The GL value you want
	 *
	 * @return the integer value
	 */
	public static int glGetInteger(int gl_enum) {
		GL11.glGetInteger(gl_enum, int_buffer);
		return int_buffer.get(0);
	}

	protected static ByteBuffer getShaderText(String file) {
		ByteBuffer shader = null;

		try {
			ClassLoader loader = ShadersTest.class.getClassLoader();
			InputStream inputStream = loader.getResourceAsStream("org/lwjgl/test/opengl/shaders/" + file);

			if ( inputStream == null )
				ShadersTest.kill("A shader source file could not be found: " + file);

			BufferedInputStream stream = new BufferedInputStream(inputStream);

			byte character;
			while ( (character = (byte)stream.read()) != -1 )
				fileBuffer.put(character);

			stream.close();

			fileBuffer.flip();

			shader = BufferUtils.createByteBuffer(fileBuffer.limit());
			shader.put(fileBuffer);

			shader.clear();
			fileBuffer.clear();
		} catch (IOException e) {
			ShadersTest.kill("Failed to read the shader source file: " + file, e);
		}

		return shader;
	}

	protected static void checkProgramError(String programFile, ByteBuffer programSource) {
		if ( GL11.glGetError() == GL11.GL_INVALID_OPERATION ) {
			programSource.clear();
			final byte[] bytes = new byte[programSource.capacity()];
			programSource.get(bytes);

			final int errorPos = glGetInteger(ARBProgram.GL_PROGRAM_ERROR_POSITION_ARB);
			int lineStart = 0;
			int lineEnd = -1;
			for ( int i = 0; i < bytes.length; i++ ) {
				if ( bytes[i] == '\n' ) {
					if ( i <= errorPos ) {
						lineStart = i + 1;
					} else {
						lineEnd = i;
						break;
					}
				}
			}

			if ( lineEnd == -1 )
				lineEnd = bytes.length;

			ShadersTest.kill("Low-level program error in file: " + programFile
			                 + "\n\tError line: " + new String(bytes, lineStart, lineEnd - lineStart)
			                 + "\n\tError message: " + GL11.glGetString(ARBProgram.GL_PROGRAM_ERROR_STRING_ARB));
		}
	}

	protected static int getUniformLocation(int ID, String name) {
		fileBuffer.clear();

		int length = name.length();

		char[] charArray = new char[length];
		name.getChars(0, length, charArray, 0);

		for ( int i = 0; i < length; i++ )
			fileBuffer.put((byte)charArray[i]);
		fileBuffer.put((byte)0); // Must be null-terminated.
		fileBuffer.flip();

		final int location = ARBShaderObjects.glGetUniformLocationARB(ID, fileBuffer);

		if ( location == -1 )
			throw new IllegalArgumentException("The uniform \"" + name + "\" does not exist in the Shader Program.");

		return location;
	}

	protected static void printShaderObjectInfoLog(String file, int ID) {
		ARBShaderObjects.glGetObjectParameterARB(ID, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB, programBuffer);

		final int logLength = programBuffer.get(0);

		if ( logLength <= 1 )
			return;

		final ByteBuffer log = BufferUtils.createByteBuffer(logLength);

		ARBShaderObjects.glGetInfoLogARB(ID, null, log);

		final char[] charArray = new char[logLength];
		for ( int i = 0; i < logLength; i++ )
			charArray[i] = (char)log.get();

		System.out.println("\nInfo Log of Shader Object: " + file);
		System.out.println("--------------------------");
		System.out.println(new String(charArray, 0, logLength));
	}

	protected static void printShaderProgramInfoLog(int ID) {
		ARBShaderObjects.glGetObjectParameterARB(ID, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB, programBuffer);

		final int logLength = programBuffer.get(0);

		if ( logLength <= 1 )
			return;

		final ByteBuffer log = BufferUtils.createByteBuffer(logLength);

		ARBShaderObjects.glGetInfoLogARB(ID, null, log);

		final char[] charArray = new char[logLength];
		for ( int i = 0; i < logLength; i++ )
			charArray[i] = (char)log.get();

		System.out.println("\nShader Program Info Log: ");
		System.out.println("--------------------------");
		System.out.println(new String(charArray, 0, logLength));
	}

}
