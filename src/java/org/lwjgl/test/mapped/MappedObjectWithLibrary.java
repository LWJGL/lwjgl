/*
 * Created on Jul 12, 2011
 */

package org.lwjgl.test.mapped;

import org.lwjgl.opengl.Display;

import java.io.File;

public class MappedObjectWithLibrary {

	public static void testLWJGL() throws Exception {
		System.out.println(new File(System.getProperty("java.library.path")).getCanonicalPath());
		Display.create();
	}
}
