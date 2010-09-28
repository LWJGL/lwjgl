/*
 * Copyright (c) 2002-2008 LWJGL Project
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

/**
 * This is an interface to the windows registry
 * @author elias_naur
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

final class WindowsRegistry {
	static final int HKEY_CLASSES_ROOT = 1;
	static final int HKEY_CURRENT_USER = 2;
	static final int HKEY_LOCAL_MACHINE = 3;
	static final int HKEY_USERS = 4;

	static {
		Sys.initialize();
	}

	/**
	 * Query the registry value specified by the root key, subkey, value tuple
	 */
	static String queryRegistrationKey(int root_key, String subkey, String value) throws LWJGLException {
		switch (root_key) {
			case HKEY_CLASSES_ROOT:
			case HKEY_CURRENT_USER:
			case HKEY_LOCAL_MACHINE:
			case HKEY_USERS:
				break;
			default:
				throw new IllegalArgumentException("Invalid enum: " + root_key);
		}
		return nQueryRegistrationKey(root_key, subkey, value);
	}

	private static native String nQueryRegistrationKey(int root_key, String subkey, String value) throws LWJGLException;
}
