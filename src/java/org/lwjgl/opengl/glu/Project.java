package org.lwjgl.opengl.glu;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

/**
 * Project.java
 * 
 * 
 * Created 11-jan-2004
 * @author Erik Duijs
 */
public class Project extends Util implements GLUConstants {

	private static FloatBuffer matrix = createFloatBuffer(16);

	/**
	 * Make m an identity matrix
	 */
	private static void __gluMakeIdentityf() {
		matrix.put(0 + 4 * 0, 1);
		matrix.put(0 + 4 * 1, 0);
		matrix.put(0 + 4 * 2, 0);
		matrix.put(0 + 4 * 3, 0);
		matrix.put(1 + 4 * 0, 0);
		matrix.put(1 + 4 * 1, 1);
		matrix.put(1 + 4 * 2, 0);
		matrix.put(1 + 4 * 3, 0);
		matrix.put(2 + 4 * 0, 0);
		matrix.put(2 + 4 * 1, 0);
		matrix.put(2 + 4 * 2, 1);
		matrix.put(2 + 4 * 3, 0);
		matrix.put(3 + 4 * 0, 0);
		matrix.put(3 + 4 * 1, 0);
		matrix.put(3 + 4 * 2, 0);
		matrix.put(3 + 4 * 3, 1);
	}

	/**
	 * Method gluPerspective.
	 * @param fovy
	 * @param aspect
	 * @param zNear
	 * @param zFar
	 */
	public static void gluPerspective(float fovy, float aspect, float zNear, float zFar) {
		float sine, cotangent, deltaZ;
		float radians = fovy / 2 * PI / 180;

		deltaZ = zFar - zNear;
		sine = (float) Math.sin(radians);
		if ((deltaZ == 0) || (sine == 0) || (aspect == 0)) {
			return;
		}
		cotangent = (float) Math.cos(radians) / sine;

		__gluMakeIdentityf();

		matrix.put(0 * 4 + 0, cotangent / aspect);
		matrix.put(1 * 4 + 1, cotangent);
		matrix.put(2 * 4 + 2, - (zFar + zNear) / deltaZ);
		matrix.put(2 * 4 + 3, -1);
		matrix.put(3 * 4 + 2, -2 * zNear * zFar / deltaZ);
		matrix.put(3 * 4 + 3, 0);

		GL11.glMultMatrixf(matrix);
	}

	/**
	 * Method gluLookAt
	 * @param eyex
	 * @param eyey
	 * @param eyez
	 * @param centerx
	 * @param centery
	 * @param centerz
	 * @param upx
	 * @param upy
	 * @param upz
	 */
	public static void gluLookAt(
		float eyex,
		float eyey,
		float eyez,
		float centerx,
		float centery,
		float centerz,
		float upx,
		float upy,
		float upz) {

		int i;
		float[] forward = new float[3];
		float[] side = new float[3];
		float[] up = new float[3];

		forward[0] = centerx - eyex;
		forward[1] = centery - eyey;
		forward[2] = centerz - eyez;

		up[0] = upx;
		up[1] = upy;
		up[2] = upz;

		normalize(forward);

		/* Side = forward x up */
		cross(forward, up, side);
		normalize(side);

		/* Recompute up as: up = side x forward */
		cross(side, forward, up);

		__gluMakeIdentityf();
		matrix.put(0 * 4 + 0, side[0]);
		matrix.put(1 * 4 + 0, side[1]);
		matrix.put(2 * 4 + 0, side[2]);

		matrix.put(0 * 4 + 1, up[0]);
		matrix.put(1 * 4 + 1, up[1]);
		matrix.put(2 * 4 + 1, up[2]);

		matrix.put(0 * 4 + 2, -forward[0]);
		matrix.put(1 * 4 + 2, -forward[1]);
		matrix.put(2 * 4 + 2, -forward[2]);

		GL11.glMultMatrixf(matrix);
		GL11.glTranslatef(-eyex, -eyey, -eyez);
	}

	/**
	 * Method gluPickMatrix
	 * @param x
	 * @param y
	 * @param deltax
	 * @param deltay
	 * @param viewport
	 */
	public static void gluPickMatrix(
		float x,
		float y,
		float deltax,
		float deltay,
		int[] viewport) {
		if (deltax <= 0 || deltay <= 0) {
			return;
		}

		/* Translate and scale the picked region to the entire window */
		GL11.glTranslatef(
			(viewport[2] - 2 * (x - viewport[0])) / deltax,
			(viewport[3] - 2 * (y - viewport[1])) / deltay,
			0);
		GL11.glScalef(viewport[2] / deltax, viewport[3] / deltay, 1.0f);
	}
}
