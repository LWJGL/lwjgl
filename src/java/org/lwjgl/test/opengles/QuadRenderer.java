package org.lwjgl.test.opengles;

import org.lwjgl.BufferUtils;
import org.lwjgl.test.opengles.util.Shader;
import org.lwjgl.test.opengles.util.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengles.GLES20.*;
import static org.lwjgl.test.opengles.util.GLMatrix.*;

final class QuadRenderer {

	private final Shader        vsh;
	private final Shader        fsh;
	private final ShaderProgram program;

	private final int uniMVP;

	private final Matrix4f p   = new Matrix4f();
	private final Matrix4f mv  = new Matrix4f();
	private final Matrix4f mvp = new Matrix4f();

	private final FloatBuffer m4fBuffer = BufferUtils.createFloatBuffer(4 * 4);

	QuadRenderer() {
		vsh = new Shader(GL_VERTEX_SHADER, "uniform highp mat4 MODEL_VIEW_PROJECTION_MATRIX;\n" +
		                                   "attribute highp vec2 vPosition;\n" +
		                                   "void main(void) {\n" +
		                                   "\tgl_Position = MODEL_VIEW_PROJECTION_MATRIX * vec4(vPosition, 0.0, 1.0);\n" +
		                                   "}");

		fsh = new Shader(GL_FRAGMENT_SHADER, "void main(void) {\n" +
		                                     "\tgl_FragColor = vec4(1.0);\n" +
		                                     "}");

		program = new ShaderProgram(vsh, fsh);
		program.enable();

		uniMVP = program.getUniformLocation("MODEL_VIEW_PROJECTION_MATRIX");

		final int vPosition = program.getAttributeLocation("vPosition");
		glVertexAttribPointer(vPosition, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(vPosition);
	}

	void setMVPUniform() {
		// Get Projection and Modelview matrices
		glMatrixMode(GL_PROJECTION);
		glGetMatrix(p);

		glMatrixMode(GL_MODELVIEW);
		glGetMatrix(mv);

		// Set MVP uniform
		Matrix4f.mul(p, mv, mvp);
		mvp.store(m4fBuffer);
		m4fBuffer.flip();
		glUniformMatrix4(uniMVP, false, m4fBuffer);
	}

	void cleanup() {
		program.destroy();

		fsh.destroy();
		vsh.destroy();
	}

}