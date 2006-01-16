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
package org.lwjgl;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/**
 * $Id$
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
class MacOSXSysImplementation extends J2SESysImplementation {
	public String[] getNativeLibraryNames() {
		/* If we're on 10.4, fine, we'll just try the default library name. For
		 * earlier versions of Mac OS X, try the legacy library first.
		 *
		 * Having a kludge like this is unfortunate, but necessary for the following reasons:
		 * 1. We need two libraries to support Mac OS X 10.2, 10.3 and 10.4. We could
		 *    cover 10.2, 10.3 and 10.4 with one gcc 3 compiled library, but then we
		 *    loose intel mac support. Instead, we'll distribute two versions of the lwjgl
		 *    native library, the default and a legacy one.
		 * 2. The default library will be universal ('fat') with both intel and powerpc support
		 *    compiled in. This requires gcc 4, and makes the library unusable on Mac OS X 10.3
		 *    and earlier (actually 10.3.9 has the required gcc 4 libraries, but we'll ignore that).
		 *    We could still choose to load the default library first, and the legacy one later,
		 *    but a bug in the Mac OS X java implementation forces a java program to exit
		 *    if the loaded library has a missing dependency (The correct behaviour is to throw
		 *    an UnsatisfiedLinkError, like on linux and windows).
		 * 3. If the LWJGL program is launched with an intelligent ClassLoader, this issue can be avoided
		 *    altogether, and the legacy library naming can be avoided too. For example, when
		 *    using webstart, one can supply two nativelib references, one for Mac OS X 10.4
		 *    (the default library), and one for earlier Mac OS X (the legacy library). This is the
		 *    preferred way to deploy the libraries. The legacy naming is for the users that don't want to
		 *    mess around with libraries and classloaders. They can simply supply make sure that lwjgl.jar
		 *    is in the classpath and that both the default library and the legacy library is in the native
		 *    library path (java.library.path).
		 */
		if (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 4))
			return super.getNativeLibraryNames();
		else
			return new String[]{LIBRARY_NAME + "-legacy", LIBRARY_NAME};
	}
	
	public boolean openURL(String url) {
		try {
			Method openURL_method = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					try {
						Class com_apple_eio_FileManager = Class.forName("com.apple.eio.FileManager");
						return com_apple_eio_FileManager.getMethod("openURL", new Class[]{String.class});
					} catch (Exception e) {
						LWJGLUtil.log("Exception occurred while trying to invoke browser: " + e);
						return null;
					}
				}
			});
			openURL_method.invoke(null, new Object[]{url});
			return true;
		} catch (Exception e) {
			LWJGLUtil.log("Exception occurred while trying to invoke browser: " + e);
			return false;
		}
	}
}
