package org.lwjgl.opengl.glu;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL;

/**
 * GLU.java
 * 
 * 
 * Created 23-dec-2003
 * @author Erik Duijs
 */
public class GLU extends Util implements GLUConstants {

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
		
		GL.glOrtho(left, right, bottom, top, -1.0, 1.0);
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
		int viewport[]) {
		
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
	 * @param string
	 * @param string1
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
	 * @param width
	 * @param height
	 * @param type
	 * @param data
	 * @param w
	 * @param h
	 * @param type1
	 * @param image
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

}
