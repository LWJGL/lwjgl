package org.lwjgl.opengl;

/**
 * This class contains the global lock that LWJGL will use to
 * synchronize access to Display.
 */
final class GlobalLock {
	final static Object lock = new Object();
}
