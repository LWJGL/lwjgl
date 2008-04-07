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
 * This is the DirectInput base class
 * @author elias_naur
 */

import org.lwjgl.LWJGLException;

abstract class WindowsDirectInput {
	public final static int KEYBOARD_TYPE = 1;
	public final static int MOUSE_TYPE = 2;

	/* DirectInput constants */
	public final static int DI_OK					= 0x00000000;
	public final static int DI_NOEFFECT             = 0x00000001;
	public final static int DI_PROPNOEFFECT         = 0x00000001;
	public final static int DI_POLLEDDEVICE         = 0x00000002;

	public final static int DI_DOWNLOADSKIPPED            = 0x00000003;
	public final static int DI_EFFECTRESTARTED            = 0x00000004;
	public final static int DI_TRUNCATED                    = 0x00000008;
	public final static int DI_SETTINGSNOTSAVED          = 0x0000000B;
	public final static int DI_TRUNCATEDANDRESTARTED        = 0x0000000C;

	public final static int DI_BUFFEROVERFLOW       = 0x00000001;
	public final static int DIERR_INPUTLOST         = 0x8007001E;
	public final static int DIERR_NOTACQUIRED       = 0x8007001C;
	public final static int DIERR_OTHERAPPHASPRIO   = 0x80070005;

	private final long di_interface;

	public WindowsDirectInput(long hinst) throws LWJGLException {
		di_interface = createDirectInput(hinst);
	}
	protected abstract long createDirectInput(long hinst) throws LWJGLException;

	public WindowsDirectInputDevice createDevice(int type) throws LWJGLException {
		return createDevice(di_interface, type);
	}
	protected abstract WindowsDirectInputDevice createDevice(long di_interface, int type) throws LWJGLException;

	public void release() {
		release(di_interface);
	}
	protected abstract void release(long di_interface);
}
