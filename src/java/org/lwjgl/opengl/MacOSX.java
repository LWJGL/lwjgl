/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

import java.lang.reflect.*;

import org.lwjgl.Sys;

/**
 * $Id$
 *
 * Mac OS X specific hacks
 * 
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
class MacOSX {
	/** 
	 * Initializes the Mac OS X specific hack 
	 */
	public static void initMacOSX() {

		java.awt.Toolkit.getDefaultToolkit();

		// Add ourselves to quit requested, using reflection to allow
		// compiling on other platforms
		try {
			Class appClass = Class.forName("com.apple.eawt.Application");
			Class listenerClass = Class.forName("com.apple.eawt.ApplicationListener");
			Object appInstance = appClass.newInstance();
			// create proxy for adapter
			Object proxyInvoker = Proxy.newProxyInstance(listenerClass.getClassLoader(), new Class[]{listenerClass}, new Invokee());
			Method addApplicationListener = appClass.getMethod("addApplicationListener", new Class[]{listenerClass});
			addApplicationListener.invoke(appInstance, new Object[]{proxyInvoker});
		} catch (Exception e) {
			// validate success
			if (Sys.DEBUG) {
				System.out.println("Unable to invoke 'addApplicationListener' method because of " + e);
			}
		}
	}

	/**
	 * Even more hackish proxy class for allowing mac os x to be compiled on all platforms
	 *
	 * @author Brian Matzon <brian@matzon.dk>
	 */
	static private class Invokee implements InvocationHandler {
		/**
		 * Called when the actual method of the proxied class is called
		 * 
		 * @param proxy Object being proxied
		 * @param method Method being invoked
		 * @param args Arguments for that specific method
		 */
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
				// Return if we were not called through handleQuit
				Class applicationEventClass = Class.forName("com.apple.eawt.ApplicationEvent");
				Class applicationListenerClass = Class.forName("com.apple.eawt.ApplicationListener");
				Method handleQuitMethod = applicationListenerClass.getMethod("handleQuit", new Class[]{applicationEventClass});
				if (!method.equals(handleQuitMethod))
					return null;
				// invoke setHandled(false); 
				Method setHandled = args[0].getClass().getMethod("setHandled", new Class[] {boolean.class}); 
				setHandled.invoke(args[0], new Object[]{new Boolean(false)});
				// just call setQuitRequested
				setQuitRequested();
			} catch (Exception e) {
				if (Sys.DEBUG) {
					System.out.println("Unable to invoke 'setHandled' because of " + e);
				}
			}
			return null;
		}
	}

	/** Notifies the native implementation that a quit event has been received */
	private static native void setQuitRequested();
}
