package org.lwjgl.util.generator;

/**
 * $Id$
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

import java.io.PrintWriter;

public enum Platform {
	WGL,
	GLX,
	ALL;
	
	public void printPrologue(PrintWriter writer) {
		if (this == ALL)
			return;
		writer.print("#ifdef ");
		switch (this) {
			case WGL:
				writer.println("_WIN32");
				break;
			case GLX:
				writer.println("_X11");
				break;
			default:
				throw new RuntimeException(this + " is not supported");
		}
	}

	public void printEpilogue(PrintWriter writer) {
		if (this == ALL)
			return;
		writer.println("#endif");
	}
	
	public String getOSPrefix() {
		switch (this) {
			case WGL:
				return "Windows";
			case GLX:
				return "Linux";
			default:
				throw new RuntimeException(this + " has no OS specific prefix");
		}
	}
	
	public String getPrefix() {
		switch (this) {
			case WGL:
				return "wgl";
			case GLX:
				return "glX";
			case ALL:
				return "gl";
			default:
				throw new RuntimeException(this + " is not supported");
		}
	}
}
