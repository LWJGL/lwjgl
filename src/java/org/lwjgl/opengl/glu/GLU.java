package org.lwjgl.opengl.glu;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * GLU.java
 *
 *
 * Created 23-dec-2003
 * @author Erik Duijs
 */
public class GLU implements GLUConstants {

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

		Project.gluLookAt(eyex, eyey, eyez, centerx, centery, centerz, upx, upy, upz);
	}

	/**
	 * Method gluOrtho2D
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 */
	public static void gluOrtho2D(
			float left,
			float right,
			float bottom,
			float top) {

		GL11.glOrtho(left, right, bottom, top, -1.0, 1.0);
	}

	/**
	 * Method gluPerspective
	 * @param fovy
	 * @param aspect
	 * @param zNear
	 * @param zFar
	 */
	public static void gluPerspective(
			float fovy,
			float aspect,
			float zNear,
			float zFar) {

		Project.gluPerspective(fovy, aspect, zNear, zFar);
	}

	/**
	 * Method gluProject
	 * @param objx
	 * @param objy
	 * @param objz
	 * @param modelMatrix
	 * @param projMatrix
	 * @param viewport
	 * @param win_pos
	 * @return
	 */
	public static boolean gluProject(float objx, float objy, float objz,
			FloatBuffer modelMatrix,
			FloatBuffer projMatrix,
			IntBuffer viewport,
			FloatBuffer win_pos)
	{
		return Project.gluProject(objx, objy, objz, modelMatrix, projMatrix, viewport, win_pos);
	}

	/**
	 * Method gluUnproject
	 * @param winx
	 * @param winy
	 * @param winz
	 * @param modelMatrix
	 * @param projMatrix
	 * @param viewport
	 * @param obj_pos
	 * @return
	 */
	public static boolean gluUnProject(float winx, float winy, float winz,
			FloatBuffer modelMatrix,
			FloatBuffer projMatrix,
			IntBuffer viewport,
			FloatBuffer obj_pos)
	{
		return Project.gluUnProject(winx, winy, winz, modelMatrix, projMatrix, viewport, obj_pos);
	}

	/**
	 * Method gluPickMatrix
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param viewport
	 */
	public static void gluPickMatrix(
			float x,
			float y,
			float width,
			float height,
			IntBuffer viewport) {

		Project.gluPickMatrix(x, y, width, height, viewport);
	}

	/**
	 * Method gluGetString.
	 * @param name
	 * @return String
	 */
	public static String gluGetString(int name) {
		return Registry.gluGetString(name);
	}

	/**
	 * Method gluCheckExtension.
	 * @param extName
	 * @param extString
	 * @return boolean
	 */
	public static boolean gluCheckExtension(String extName, String extString) {
		return Registry.gluCheckExtension(extName, extString);
	}

	/**
	 * Method gluBuild2DMipmaps
	 * @param target
	 * @param components
	 * @param width
	 * @param height
	 * @param format
	 * @param type
	 * @param data
	 * @return int
	 */
	public static int gluBuild2DMipmaps(
			int target,
			int components,
			int width,
			int height,
			int format,
			int type,
			ByteBuffer data) {

		return MipMap.gluBuild2DMipmaps(target, components, width, height, format, type, data);
	}

	/**
	 * Method gluScaleImage.
	 * @param format
	 * @param widthIn
	 * @param heightIn
	 * @param typeIn
	 * @param dataIn
	 * @param widthOut
	 * @param heightOut
	 * @param typeOut
	 * @param dataOut
	 * @return int
	 */
	public static int gluScaleImage(
			int format,
			int widthIn,
			int heightIn,
			int typeIn,
			ByteBuffer dataIn,
			int widthOut,
			int heightOut,
			int typeOut,
			ByteBuffer dataOut) {

		return MipMap.gluScaleImage(format, widthIn, heightIn, typeIn, dataIn, widthOut, heightOut, typeOut, dataOut);
	}

	public static String gluErrorString(int error_code) {
		switch (error_code) {
			case GL11.GL_NO_ERROR:
				return "No error";
			case GL11.GL_INVALID_ENUM:
				return "Invalid enum";
			case GL11.GL_INVALID_VALUE:
				return "Invalid value";
			case GL11.GL_INVALID_OPERATION:
				return "Invalid operation";
			case GL11.GL_STACK_OVERFLOW:
				return "Stack overflow";
			case GL11.GL_STACK_UNDERFLOW:
				return "Stack underflow";
			case GL11.GL_OUT_OF_MEMORY:
				return "Out of memory";
			case GL12.GL_TABLE_TOO_LARGE:
				return "Table too large";
			case GLU.GLU_INVALID_ENUM:
				return "Invalid enum (glu)";
			case GLU.GLU_INVALID_VALUE:
				return "Invalid value (glu)";
			case GLU.GLU_OUT_OF_MEMORY:
				return "Out of memory (glu)";
			default:
				return null;
		}
	}
}
