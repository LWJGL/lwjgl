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
/*
 * Created by LWJGL.
 * User: spasi
 * Date: 29 …бн 2004
 * Time: 1:42:31 рм
 */

package org.lwjgl.opengl;

import java.nio.IntBuffer;

public class ARBOcclusionQuery {

	/*
	 * Accepted by the <target> parameter of BeginQueryARB, EndQueryARB,
	 * and GetQueryivARB:
	*/
	public static final int GL_SAMPLES_PASSED_ARB = 0x8914;

	/*
	Accepted by the <pname> parameter of GetQueryivARB:
	*/
	public static final int GL_QUERY_COUNTER_BITS_ARB = 0x8864;
	public static final int GL_CURRENT_QUERY_ARB = 0x8865;

	/*
	Accepted by the <pname> parameter of GetQueryObjectivARB and
	GetQueryObjectuivARB:
	*/
	public static final int GL_QUERY_RESULT_ARB = 0x8866;
	public static final int GL_QUERY_RESULT_AVAILABLE_ARB = 0x8867;
	
	// ---------------------------
	public static void glGenQueriesARB(IntBuffer ids) {
		nglGenQueriesARB(ids.remaining(), ids, ids.position());
	}

	private static native void nglGenQueriesARB(int n, IntBuffer ids, int idsOffset);
	// ---------------------------

	// ---------------------------
	public static void glDeleteQueriesARB(IntBuffer ids) {
		nglDeleteQueriesARB(ids.remaining(), ids, ids.position());
	}

	private static native void nglDeleteQueriesARB(int n, IntBuffer ids, int idsOffset);
	// ---------------------------

	public static native boolean glIsQueryARB(int id);

	public static native void glBeginQueryARB(int target, int id);

	public static native void glEndQueryARB(int target);

	// ---------------------------
	public static void glGetQueryARB(int target, int pname, IntBuffer params) {
		nglGetQueryivARB(target, pname, params, params.position());
	}

	private static native void nglGetQueryivARB(int target, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetQueryObjectiARB(int id, int pname, IntBuffer params) {
		// TODO: check buffer size
		nglGetQueryObjectivARB(id, pname, params, params.position());
	}

	private static native void nglGetQueryObjectivARB(int id, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetQueryObjectuiARB(int id, int pname, IntBuffer params) {
		// TODO: check buffer size
		nglGetQueryObjectuivARB(id, pname, params, params.position());
	}

	private static native void nglGetQueryObjectuivARB(int id, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

}
