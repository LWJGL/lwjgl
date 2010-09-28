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

import org.lwjgl.BufferUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.ARBProgram.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.GL11.*;

abstract class Shader {

	protected static ByteBuffer fileBuffer = BufferUtils.createByteBuffer(1024 * 10);

	protected Shader() {
	}

	abstract void render();

	abstract void cleanup();

	protected static String getShaderText(String file) {
		String shader = null;

		try {
			InputStream source = ShadersTest.class.getResourceAsStream(file);
			if ( source == null ) // dev-mode
				source = new FileInputStream("src/java/org/lwjgl/test/opengl/shaders/" + file);

			BufferedInputStream stream = new BufferedInputStream(source);

			byte character;
			while ( (character = (byte)stream.read()) != -1 )
				fileBuffer.put(character);

			stream.close();

			fileBuffer.flip();

			byte[] array = new byte[fileBuffer.remaining()];
			fileBuffer.get(array);
			shader = new String(array);

			fileBuffer.clear();
		} catch (IOException e) {
			ShadersTest.kill("Failed to read the shader source file: " + file, e);
		}

		return shader;
	}

	protected static void checkProgramError(String programFile, String programSource) {
		if ( glGetError() == GL_INVALID_OPERATION ) {
			final int errorPos = glGetInteger(GL_PROGRAM_ERROR_POSITION_ARB);
			int lineStart = 0;
			int lineEnd = -1;
			for ( int i = 0; i < programSource.length(); i++ ) {
				if ( programSource.charAt(i) == '\n' ) {
					if ( i <= errorPos ) {
						lineStart = i + 1;
					} else {
						lineEnd = i;
						break;
					}
				}
			}

			if ( lineEnd == -1 )
				lineEnd = programSource.length();

			ShadersTest.kill("Low-level program error in file: " + programFile
			                 + "\n\tError line: " + programSource.substring(lineStart, lineEnd)
			                 + "\n\tError message: " + glGetString(GL_PROGRAM_ERROR_STRING_ARB));
		}
	}

	protected static int getUniformLocation(int ID, String name) {
		final int location = glGetUniformLocationARB(ID, name);

		if ( location == -1 )
			throw new IllegalArgumentException("The uniform \"" + name + "\" does not exist in the Shader Program.");

		return location;
	}

	protected static void printShaderObjectInfoLog(String file, int ID) {
		final int logLength = glGetObjectParameteriARB(ID, GL_OBJECT_INFO_LOG_LENGTH_ARB);
		if ( logLength <= 1 )
			return;

		System.out.println("\nInfo Log of Shader Object: " + file);
		System.out.println("--------------------------");
		System.out.println(glGetInfoLogARB(ID, logLength));

	}

	protected static void printShaderProgramInfoLog(int ID) {
		final int logLength = glGetObjectParameteriARB(ID, GL_OBJECT_INFO_LOG_LENGTH_ARB);
		if ( logLength <= 1 )
			return;

		System.out.println("\nShader Program Info Log: ");
		System.out.println("--------------------------");
		System.out.println(glGetInfoLogARB(ID, logLength));
	}

}
