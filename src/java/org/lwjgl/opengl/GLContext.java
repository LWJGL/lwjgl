/*
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * $Id$
 * <p/>
 * Manages GL contexts. Before any rendering is done by a LWJGL system, a call should be made to GLContext.useContext() with a
 * context. This will ensure that GLContext has an accurate reflection of the current context's capabilities and function
 * pointers.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
public final class GLContext {
	private final static ThreadLocal current_capabilities = new ThreadLocal();

	/** Map of classes that have native stubs loaded */
	private static int gl_ref_count;
	private static boolean did_auto_load;

	static {
		Sys.initialize();
	}

	/**
	 * Get the current capabilities instance. It contains the flags used
	 * to test for support of a particular extension.
	 *
	 * @return The current capabilities instance.
	 */
	public static ContextCapabilities getCapabilities() {
		return ((ContextCapabilities)current_capabilities.get());
	}
	
	/**
	 * Set the current capabilities instance. It contains the flags used
	 * to test for support of a particular extension.
	 *
	 * @return The current capabilities instance.
	 */
	static void setCapabilities(ContextCapabilities capabilities) {
		current_capabilities.set(capabilities);
	}
	
	/**
	 * Helper method to get a pointer to a named function in the OpenGL library
	 * with a name dependent on the current platform
	 */
	static long getPlatformSpecificFunctionAddress(String function_prefix, String[] os_prefixes, String[] os_function_prefixes, String function) {
		String os_name = System.getProperty("os.name");
		for (int i = 0; i < os_prefixes.length; i++)
			if (os_name.startsWith(os_prefixes[i])) {
				String platform_function_name = function.replaceFirst(function_prefix, os_function_prefixes[i]);
				long address = getFunctionAddress(platform_function_name);
				return address;
			}
		return 0;
	}

	/**
	 * Helper method to get a pointer to a named function in the OpenGL library
	 */
	static native long getFunctionAddress(String name);

	/**
	 * Determine which extensions are available. Helper method to ContextCapabilities.
	 *
	 * @return A Set containing all available extension strings.
	 */
	static Set getSupportedExtensions() {
		Set supported_extensions = new HashSet();
		String extensions_string = GL11.glGetString(GL11.GL_EXTENSIONS);
		StringTokenizer tokenizer = new StringTokenizer(extensions_string);
		while ( tokenizer.hasMoreTokens() ) {
			String extension_string = tokenizer.nextToken();
			supported_extensions.add(extension_string);
		}
		String version = GL11.glGetString(GL11.GL_VERSION);
		if (version == null)
			throw new IllegalStateException("glGetString(GL_VERSION) returned null - possibly caused by missing current context.");
		StringTokenizer version_tokenizer = new StringTokenizer(version, ". ");
		String major_string = version_tokenizer.nextToken();
		String minor_string = version_tokenizer.nextToken();

		int majorVersion = Integer.parseInt(major_string);
		int minorVersion = Integer.parseInt(minor_string);

		if (majorVersion == 2) {
			// ----------------------[ 2.X ]----------------------
			supported_extensions.add("OpenGL20");
			// ----------------------[ 1.X ]----------------------
			supported_extensions.add("OpenGL11");
			supported_extensions.add("OpenGL12");
			supported_extensions.add("OpenGL13");
			supported_extensions.add("OpenGL14");
			supported_extensions.add("OpenGL15");
		} else {
			switch (minorVersion) {
				case 5:
					supported_extensions.add("OpenGL15");
					// Intentional fall through
				case 4:
					supported_extensions.add("OpenGL14");
					// Intentional fall through
				case 3:
					supported_extensions.add("OpenGL13");
					// Intentional fall through
				case 2:
					supported_extensions.add("OpenGL12");
					// Intentional fall through
				case 1:
					supported_extensions.add("OpenGL11");
			}
		}
		return supported_extensions;
	}

	/**
	 * Helper method to ContextCapabilities. It will try to initialize the native stubs,
	 * and remove the given extension name from the extension set if the initialization fails.
	 */
	static void initNativeStubs(Class extension_class, Set supported_extensions, String ext_name) {
		resetNativeStubs(extension_class);
		if (supported_extensions.contains(ext_name)) {
			try {
				Method init_stubs_method = extension_class.getDeclaredMethod("initNativeStubs", null);
				init_stubs_method.invoke(null, null);
			} catch (Exception e) {
				Sys.log("Failed to initialize extension " + extension_class + " - exception: " + e);
				supported_extensions.remove(ext_name);
			}
		}
	}

	private static void loadStubs() throws LWJGLException {
		new ContextCapabilities();
	}

	/**
	 * Makes a GL context the current LWJGL context by loading GL function pointers. The context must be current before a call to
	 * this method! Instead it simply ensures that the current context is reflected accurately by GLContext's extension caps and
	 * function pointers. Use useContext(null) when no context is active. <p>If the context is the same as last time, then this is
	 * a no-op. <p>If the context has not been encountered before it will be fully initialized from scratch. Otherwise a cached set
	 * of caps and function pointers will be used. <p>The reference to the context is held in a weak reference; therefore if no
	 * strong reference exists to the GL context it will automatically be forgotten by the VM at an indeterminate point in the
	 * future, freeing up a little RAM.
	 *
	 * @param context The context object, which uniquely identifies a GL context. If context is null, the native stubs are
	 *                unloaded.
	 *
	 * @throws LWJGLException if context non-null, and the gl library can't be loaded or the basic GL11 functions can't be loaded
	 */
	public static void useContext(Object context) throws LWJGLException {
		if (context == null) {
			ContextCapabilities.unloadAllStubs();
			setCapabilities(null);
			if (did_auto_load)
				unloadOpenGLLibrary();
			BufferObjectTracker.setCurrent(null);
			return;
		}
		if (gl_ref_count == 0) {
			loadOpenGLLibrary();
			did_auto_load = true;
		}
		try {
			loadStubs();
			BufferObjectTracker.setCurrent(context);
		} catch (LWJGLException e) {
			if ( did_auto_load )
				unloadOpenGLLibrary();
			throw e;
		}
	}

	/** If the OpenGL reference count is 0, the library is loaded. The reference count is then incremented. */
	public static void loadOpenGLLibrary() throws LWJGLException {
		if ( gl_ref_count == 0 )
			nLoadOpenGLLibrary();
		gl_ref_count++;
	}

	private static native void nLoadOpenGLLibrary() throws LWJGLException;

	/** The OpenGL library reference count is decremented, and if it reaches 0, the library is unloaded. */
	public static void unloadOpenGLLibrary() {
		gl_ref_count--;
		if ( gl_ref_count == 0 )
			nUnloadOpenGLLibrary();
	}

	private static native void nUnloadOpenGLLibrary();

	/** Native method to clear native stub bindings */
	static native void resetNativeStubs(Class clazz);
}
