package org.lwjgl.test.opengles.util;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.Stack;

import static java.lang.Math.*;

/** Emulates the matrix stack in fixed-function OpenGL. */
public final class GLMatrix {

	public static final int GL_MODELVIEW = 0x1700;
	public static final int GL_PROJECTION = 0x1701;

	private static final float PI = (float)Math.PI;

	/** The model/view matrix stack. */
	private static final Stack<Matrix4f> mvMatrixStack = new Stack<Matrix4f>();

	/** The projection matrix stack. */
	private static final Stack<Matrix4f> pMatrixStack = new Stack<Matrix4f>();

	private static final Matrix4f m4f = new Matrix4f();
	private static final Vector3f v3f = new Vector3f();

	private static int mode = GL_MODELVIEW;

	static {
		mvMatrixStack.push(new Matrix4f());
		pMatrixStack.push(new Matrix4f());
	}

	private GLMatrix() {
	}

	private static Stack<Matrix4f> getCurrentStack() {
		switch ( mode ) {
			case GL_MODELVIEW:
				return mvMatrixStack;
			case GL_PROJECTION:
				return pMatrixStack;
			default:
				return null; // Cannot happen
		}
	}

	private static Matrix4f getCurrentMatrix() {
		return getCurrentStack().peek();
	}

	public static void glMatrixMode(final int mode) {
		if ( mode != GL_MODELVIEW && mode != GL_PROJECTION )
			throw new IllegalArgumentException("Invalid matrix mode specified: " + mode);

		GLMatrix.mode = mode;
	}

	public static void glPushMatrix() {
		final Stack<Matrix4f> stack = getCurrentStack();
		stack.push(new Matrix4f(stack.peek()));
	}

	public static void glPopMatrix() {
		final Stack<Matrix4f> stack = getCurrentStack();

		if ( stack.size() == 1 )
			throw new IllegalStateException("The last matrix in the stack cannot be popped.");

		getCurrentStack().pop();
	}

	public static void glLoadIdentity() {
		final Matrix4f m = getCurrentMatrix();
		m.setIdentity();
	}

	public static void glLoadMatrix(final Matrix4f s) {
		getCurrentMatrix().load(s);
	}

	public static void glMultMatrix(final Matrix4f m) {
		final Matrix4f c = getCurrentMatrix();
		Matrix4f.mul(c, m, c);
	}

	public static void glTranslatef(final float x, final float y, final float z) {
		final Matrix4f m = getCurrentMatrix();
		v3f.set(x, y, z);
		m.translate(v3f);
	}

	public static void glRotatef(final float angle, final float x, final float y, final float z) {
		final Matrix4f m = getCurrentMatrix();
		v3f.set(x, y, z);
		m.rotate((float)toRadians(angle), v3f);
	}

	public static void glOrtho(final float l, final float r, final float b, final float t, final float n, final float f) {
		final Matrix4f m = m4f;
		m.setIdentity();

		m.m00 = 2.0f / (r - l);
		m.m30 = -(r + l) / (r - l);

		m.m11 = 2.0f / (t - b);
		m.m31 = -(t + b) / (t - b);

		m.m22 = -2.0f / (f - n);
		m.m32 = -(f + n) / (f - n);

		glMultMatrix(m);
	}

	public static void glFrustum(final float l, final float r, final float b, final float t, final float n, final float f) {
		final Matrix4f m = m4f;
		m.setIdentity();

		m.m00 = 2.0f * n / (r - l);
		m.m20 = (r + l) / (r - l);

		m.m11 = 2.0f * n / (t - b);
		m.m21 = (t + b) / (t - b);

		m.m22 = -(f + n) / (f - n);
		m.m32 = -(2.0f * f * n) / (f - n);

		m.m23 = -1.0f;
		m.m33 = 0.0f;

		glMultMatrix(m);
	}

	public static void gluPerspective(final float fovy, final float aspect, final float zNear, final float zFar) {
		final float radians = fovy / 2.0f * PI / 180.0f;

		final float deltaZ = zFar - zNear;
		final float sine = (float)sin(radians);

		if ( (deltaZ == 0) || (sine == 0) || (aspect == 0) ) {
			return;
		}

		final float cotangent = (float)cos(radians) / sine;

		final Matrix4f m = m4f;
		m.setIdentity();

		m.m00 = cotangent / aspect;
		m.m11 = cotangent;
		m.m22 = -(zFar + zNear) / deltaZ;
		m.m23 = -1.0f;
		m.m32 = -2 * zNear * zFar / deltaZ;
		m.m33 = 0.0f;

		glMultMatrix(m);
	}

	public static void glGetMatrix(final Matrix4f d) {
		d.load(getCurrentMatrix());
	}

}