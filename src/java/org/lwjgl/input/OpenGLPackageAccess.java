package org.lwjgl.input;

import org.lwjgl.opengl.InputImplementation;
import org.lwjgl.opengl.Display;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.security.PrivilegedActionException;

/**
 * This class contains utilities for accessing the org.lwjgl.opengl
 * package through (privileged) reflection.
 */
final class OpenGLPackageAccess {
	final static Object global_lock;

	static {
		try {
			global_lock = AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					Field lock_field = Class.forName("org.lwjgl.opengl.GlobalLock").getDeclaredField("lock");
					lock_field.setAccessible(true);
					return lock_field.get(null);
				}
			});
		} catch (PrivilegedActionException e) {
			throw new Error(e);
		}
	}

	static InputImplementation createImplementation() {
		/* Use reflection since we can't make Display.getImplementation
		 * public
		 */
		try {
			return (InputImplementation)AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					Method getImplementation_method = Display.class.getDeclaredMethod("getImplementation", null);
					getImplementation_method.setAccessible(true);
					return getImplementation_method.invoke(null, null);
				}
			});
		} catch (PrivilegedActionException e) {
			throw new Error(e);
		}
	}
}
