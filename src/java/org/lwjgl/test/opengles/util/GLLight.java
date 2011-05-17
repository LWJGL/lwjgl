package org.lwjgl.test.opengles.util;

import static org.lwjgl.opengles.GLES20.*;

/** Emulates the light state in fixed-function OpenGL. */
public class GLLight {

	public static final int GL_LIGHT0 = 0x4000;
	public static final int GL_LIGHT1 = 0x4001;
	public static final int GL_LIGHT2 = 0x4002;
	public static final int GL_LIGHT3 = 0x4003;
	public static final int GL_LIGHT4 = 0x4004;
	public static final int GL_LIGHT5 = 0x4005;
	public static final int GL_LIGHT6 = 0x4006;
	public static final int GL_LIGHT7 = 0x4007;

	public static final int GL_AMBIENT = 0x1200;
	public static final int GL_DIFFUSE = 0x1201;
	public static final int GL_SPECULAR = 0x1202;
	public static final int GL_POSITION = 0x1203;
	public static final int GL_SPOT_DIRECTION = 0x1204;
	public static final int GL_SPOT_EXPONENT = 0x1205;
	public static final int GL_SPOT_CUTOFF = 0x1206;
	public static final int GL_CONSTANT_ATTENUATION = 0x1207;
	public static final int GL_LINEAR_ATTENUATION = 0x1208;
	public static final int GL_QUADRATIC_ATTENUATION = 0x1209;

	private static final Light[] lights = new Light[8];

	static {
		for ( int i = 0; i < lights.length; i++ )
			lights[i] = new Light();

		System.arraycopy(new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0, lights[0].diffuse, 0, 4);
		System.arraycopy(new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, 0, lights[0].specular, 0, 4);
	}

	private GLLight() {
	}

	public static void glLight(final int light, final int pname, final float v) {
		if ( light < GL_LIGHT0 || GL_LIGHT7 < light )
			throw new IllegalArgumentException("Invalid light specified: " + light);

		final Light l = lights[light - GL_LIGHT0];

		switch ( pname ) {
			case GL_SPOT_EXPONENT:
				l.s = v;
				break;
			case GL_SPOT_CUTOFF:
				l.c = v;
				break;
			case GL_CONSTANT_ATTENUATION:
				l.k0 = v;
				break;
			case GL_LINEAR_ATTENUATION:
				l.k1 = v;
				break;
			case GL_QUADRATIC_ATTENUATION:
				l.k2 = v;
				break;
			default:
				throw new IllegalArgumentException("Invalid light parameter specified: " + pname);
		}
	}

	public static void glLight(final int light, final int pname,
	                           final float x, final float y, final float z) {
		if ( light < GL_LIGHT0 || GL_LIGHT7 < light )
			throw new IllegalArgumentException("Invalid light specified: " + light);

		if ( pname != GL_SPOT_DIRECTION )
			throw new IllegalArgumentException("Invalid light parameter specified: " + pname);

		final float[] param = lights[light - GL_LIGHT0].direction;

		param[0] = x;
		param[1] = y;
		param[2] = z;
	}

	private static float[] getParam4f(final int light, final int pname) {
		if ( light < GL_LIGHT0 || GL_LIGHT7 < light )
			throw new IllegalArgumentException("Invalid light specified: " + light);

		final Light l = lights[light - GL_LIGHT0];
		switch ( pname ) {
			case GL_AMBIENT:
				return l.ambient;
			case GL_DIFFUSE:
				return l.diffuse;
			case GL_SPECULAR:
				return l.specular;
			case GL_POSITION:
				return l.position;
			default:
				throw new IllegalArgumentException("Invalid light parameter specified: " + pname);
		}
	}

	public static void glLight(final int light, final int pname,
	                           final float x, final float y, final float z, final float w) {
		final float[] param = getParam4f(light, pname);
		param[0] = x;
		param[1] = y;
		param[2] = z;
		param[3] = w;
	}

	public static void setUniform1f(final int location, final int light, final int pname) {
		if ( light < GL_LIGHT0 || GL_LIGHT7 < light )
			throw new IllegalArgumentException("Invalid light specified: " + light);

		final Light l = lights[light - GL_LIGHT0];

		switch ( pname ) {
			case GL_SPOT_EXPONENT:
				glUniform1f(location, l.s);
				break;
			case GL_SPOT_CUTOFF:
				glUniform1f(location, l.c);
				break;
			case GL_CONSTANT_ATTENUATION:
				glUniform1f(location, l.k0);
				break;
			case GL_LINEAR_ATTENUATION:
				glUniform1f(location, l.k1);
				break;
			case GL_QUADRATIC_ATTENUATION:
				glUniform1f(location, l.k2);
				break;
			default:
				throw new IllegalArgumentException("Invalid light parameter specified: " + pname);
		}
	}

	public static void setUniform3f(final int location, final int light, final int pname) {
		if ( pname != GL_SPOT_DIRECTION )
			throw new IllegalArgumentException("Invalid light parameter specified: " + pname);

		final float[] param = lights[light - GL_LIGHT0].direction;
		glUniform3f(location, param[0], param[1], param[2]);
	}

	public static void setUniform4f(final int location, final int light, final int pname) {
		final float[] param = getParam4f(light, pname);
		glUniform4f(location, param[0], param[1], param[2], param[3]);
	}

	private static class Light {

		float[] ambient = { 0.0f, 0.0f, 0.0f, 1.0f };
		float[] diffuse = { 0.0f, 0.0f, 0.0f, 1.0f };
		float[] specular = { 0.0f, 0.0f, 0.0f, 1.0f };

		float[] position = { 0.0f, 0.0f, 1.0f, 0.0f };
		float[] direction = { 0.0f, 0.0f, -1.0f };

		float s, c = 180.0f;
		float k0 = 1.0f, k1, k2;

	}

}