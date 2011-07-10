package org.lwjgl.test.opengles.util;

import static org.lwjgl.opengles.GLES20.*;

public class ShaderProgram implements GLObject {

	private final int ID;

	public ShaderProgram(final Shader... shaders) {
		this.ID = glCreateProgram();

		for ( Shader shader : shaders )
			glAttachShader(ID, shader.getID());

		glLinkProgram(ID);

		if ( glGetProgram(ID, GL_LINK_STATUS) == GL_FALSE ) {
			printInfoLog();
			destroy();
			throw new RuntimeException("Failed to link a Shader Program: " + ID);
		}
	}

	public void validate() {
		glValidateProgram(ID);

		final boolean error = glGetProgram(ID, GL_VALIDATE_STATUS) == GL_FALSE;

		if ( error ) {
			printInfoLog();
			throw new RuntimeException("Failed to validate a Shader Program.");
		}
	}

	public int getID() {
		return ID;
	}

	public void destroy() {
		glDeleteProgram(ID);
	}

	public void enable() {
		glUseProgram(ID);
	}

	public static void disable() {
		glUseProgram(0);
	}

	public int getUniformLocation(final String uniform) {
		final int location = glGetUniformLocation(ID, uniform);

		if ( location == -1 )
			throw new IllegalArgumentException("Invalid uniform name specified: " + uniform);

		return location;
	}

	public int getAttributeLocation(final String attrib) {
		final int location = glGetAttribLocation(ID, attrib);

		if ( location == -1 )
			throw new IllegalArgumentException("Invalid attribute name specified: " + attrib);

		return location;
	}

	private void printInfoLog() {
		final int logLength = glGetProgram(ID, GL_INFO_LOG_LENGTH);

		System.out.println(logLength);
		if ( logLength <= 1 )
			return;

		System.out.println("\nInfo Log of Shader Program: " + ID);
		System.out.println("-------------------");
		System.out.println(glGetProgramInfoLog(ID, logLength));
		System.out.println("-------------------");
	}

}