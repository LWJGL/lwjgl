package org.lwjgl.opengl.glu;

/**
 * Registry.java
 * 
 * 
 * Created 11-jan-2004
 * @author Erik Duijs
 */
public class Registry extends Util implements GLUConstants {

	private static final String versionString = "1.3";
	private static final String extensionString =
		"GLU_EXT_nurbs_tessellator " + "GLU_EXT_object_space_tess ";

	/**
	 * Method gluGetString
	 * @param name
	 * @return String
	 */
	public static String gluGetString(int name) {

		if (name == GLU_VERSION) {
			return versionString;
		} else if (name == GLU_EXTENSIONS) {
			return extensionString;
		}
		return null;
	}

	/**
	 * Method gluCheckExtension
	 * 
	 * @param extName is an extension name.
	 * @param extString is a string of extensions separated by blank(s). There may or 
	 * may not be leading or trailing blank(s) in extString.
	 * This works in cases of extensions being prefixes of another like
	 * GL_EXT_texture and GL_EXT_texture3D.
	 * @return boolean true if extName is found otherwise it returns false.
	 */
	public static boolean gluCheckExtension(String extName, String extString) {
		boolean flag = false;

		if (extString == null || extName == null)
			return false;

		return extString.indexOf(extName) != -1;
	}
}
