package org.lwjgl.test.opengles.util;

import org.lwjgl.BufferUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengles.GLES20.*;

public class Shader implements GLObject {

	protected static ByteBuffer fileBuffer = BufferUtils.createByteBuffer(1024 * 10);

	private int type;
	private int ID;

	public Shader() {
	}

	public Shader(final int type, final CharSequence source) {
		createFromSource(type, source);
	}

	public int getID() {
		return ID;
	}

	public int getType() {
		return type;
	}

	public void destroy() {
		if ( ID == 0 )
			throw new IllegalStateException("The shader has not been created");

		glDeleteShader(ID);
		ID = 0;
	}

	public void createFromFile(final int type, final ClassLoader loader, final String file) throws IOException {
		final InputStream inputStream = loader.getResourceAsStream(file);

		if ( inputStream == null )
			throw new IllegalArgumentException("A shader source file could not be found: " + file);

		final BufferedInputStream stream = new BufferedInputStream(inputStream);

		byte character;
		while ( (character = (byte)stream.read()) != -1 )
			fileBuffer.put(character);
		fileBuffer.flip();

		stream.close();

		final byte[] array = new byte[fileBuffer.remaining()];
		fileBuffer.get(array);

		final String source = new String(array);

		fileBuffer.clear();

		createFromSource(type, source);
	}

	public void createFromSource(final int type, final CharSequence source) {
		if ( ID != 0 )
			throw new IllegalStateException("The shader has already been created");

		this.type = type;
		this.ID = glCreateShader(type);

		glShaderSource(ID, source);

		glCompileShader(ID);

		if ( glGetShaderi(ID, GL_COMPILE_STATUS) == GL_FALSE ) {
			printInfoLog();
			destroy();
			throw new RuntimeException("A compilation error occured in a shader.");
		}
	}

	public void printInfoLog() {
		if ( ID == 0 )
			throw new IllegalStateException("The shader has not been created");

		final int logLength = glGetShaderi(ID, GL_INFO_LOG_LENGTH);
		if ( logLength <= 1 )
			return;

		System.out.println("\nInfo Log of Shader Object: " + ID);
		System.out.println("--------------------------");
		System.out.println(glGetShaderInfoLog(ID, logLength));
	}

}