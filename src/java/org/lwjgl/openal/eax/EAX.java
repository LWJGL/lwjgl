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

package org.lwjgl.openal.eax;

import org.lwjgl.Sys;
import org.lwjgl.LWJGLException;

/**
 * $Id$
 *
 * This is the OpenAL EAX class. It extends the latest core.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAX {
	/** Has the EAX object been created? */
	protected static boolean created;
	
	static {
		Sys.initialize();
	}

	/**
	 * Loads the EAX functions
	 * 
	 * @throws LWJGLException if the EAX extensions couldn't be loaded
	 */
	public static void create() throws LWJGLException {
		if (created) {
			return;
		}

		if (!nCreate()) {
			throw new LWJGLException("EAX instance could not be created.");
		}
		EAX20.init();
		created = true;
	}

	/**
	 * Native method to create EAX instance
	 * 
	 * @return true if the EAX extensions could be found
	 */
	protected static native boolean nCreate();

	/**
	 * "Destroy" the EAX object
	 */
	public static void destroy() {
		if (!created) {
			return;
		}
		created = false;
		nDestroy();
	}

	/**
	 * Native method the destroy the EAX
	 */
	protected static native void nDestroy();
	
	/**
	 * @return true if EAX has been created
	 */
	public static boolean isCreated() {
		return created;
	}	
}
