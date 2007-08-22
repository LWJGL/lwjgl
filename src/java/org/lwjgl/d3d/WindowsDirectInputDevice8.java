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
package org.lwjgl.d3d;

/**
 * This is the DirectInputDevice3 interface
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.LWJGLException;

final class WindowsDirectInputDevice8 extends WindowsDirectInputDevice {
	/** Re-declare to get the constants into the native headers */
	public final static int GUID_XAxis = WindowsDirectInputDevice.GUID_XAxis;
	public final static int GUID_YAxis = WindowsDirectInputDevice.GUID_YAxis;
	public final static int GUID_ZAxis = WindowsDirectInputDevice.GUID_ZAxis;
	public final static int GUID_Button = WindowsDirectInputDevice.GUID_Button;
	public final static int GUID_Unknown = WindowsDirectInputDevice.GUID_Unknown;

	public final static int DATA_SIZE = WindowsDirectInputDevice.DATA_SIZE;

	public WindowsDirectInputDevice8(long di_device) {
		super(di_device);
	}

	protected native int setDataFormat(long di_device, int type);

	protected native int setCooperativeLevel(long di_device, long hwnd, int flags);

	protected native int acquire(long di_device);

	protected native int getDeviceState(long di_device, ByteBuffer buffer, int position, int size);

	protected native int getDeviceData(long di_device, ByteBuffer event_buffer, int event_buffer_size, IntBuffer buffer, int position, int size);

	protected native int unacquire(long di_device);

	protected native int poll(long di_device);

	protected native int setBufferSize(long di_device, int buffer_size);
	protected native int getEventSize();

	protected native void release(long di_device);

	protected native int enumObjects(long di_device, WindowsDirectInputDeviceObjectCallback enumerator);
}
