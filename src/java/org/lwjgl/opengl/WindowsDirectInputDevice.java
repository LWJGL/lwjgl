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

/**
 * This is the DirectInputDevice interface
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.BufferUtils;

abstract class WindowsDirectInputDevice {
	public final static int DISCL_EXCLUSIVE		= 0x00000001;
	public final static int DISCL_NONEXCLUSIVE  = 0x00000002;
	public final static int DISCL_FOREGROUND	= 0x00000004;
	public final static int DISCL_BACKGROUND	= 0x00000008;
	public final static int DISCL_NOWINKEY		= 0x00000010;

	public final static int GUID_XAxis = 1;
	public final static int GUID_YAxis = 2;
	public final static int GUID_ZAxis = 3;
	public final static int GUID_Button = 4;
	public final static int GUID_Unknown = 5;

	public final static int DATA_SIZE = 3;

	private final long di_device;
	private ByteBuffer event_buffer;

	public WindowsDirectInputDevice(long di_device) {
		this.di_device = di_device;
	}

	public void release() {
		release(di_device);
	}
	protected abstract void release(long di_device);

	public int poll() {
		return poll(di_device);
	}
	protected abstract int poll(long di_device);

	public void setDataFormat(int type) throws LWJGLException {
		int ret = setDataFormat(di_device, type);
		if (ret != WindowsDirectInput.DI_OK)
			throw new LWJGLException("Failed to set data format (" + Integer.toHexString(ret) + ")");
	}
	protected abstract int setDataFormat(long di_device, int type);

	public void setCooperateLevel(long hwnd, int flags) throws LWJGLException {
		int ret = setCooperativeLevel(di_device, hwnd, flags);
		if (ret != WindowsDirectInput.DI_OK)
			throw new LWJGLException("Failed to set cooperative level (" + Integer.toHexString(ret) + ")");
	}
	protected abstract int setCooperativeLevel(long di_device, long hwnd, int flags);

	public int acquire() {
		return acquire(di_device);
	}
	protected abstract int acquire(long di_device);

	public void setBufferSize(int buffer_size) throws LWJGLException {
		int ret = setBufferSize(di_device, buffer_size);
		if (ret != WindowsDirectInput.DI_OK && ret != WindowsDirectInput.DI_PROPNOEFFECT && ret != WindowsDirectInput.DI_POLLEDDEVICE)
			throw new LWJGLException("Failed to set buffer size (" + Integer.toHexString(ret) + ")");
		int event_buffer_size = getEventSize()*buffer_size;
		event_buffer = BufferUtils.createByteBuffer(event_buffer_size);
	}
	protected abstract int setBufferSize(long di_device, int buffer_size);

	public int getDeviceData(IntBuffer buffer) {
		int events_remaining = buffer.remaining()/DATA_SIZE;
		if (event_buffer == null || events_remaining > event_buffer.remaining()/getEventSize())
			event_buffer = BufferUtils.createByteBuffer(events_remaining*getEventSize());
		return getDeviceData(di_device, event_buffer, event_buffer.capacity(), buffer, buffer.position(), buffer.remaining());
	}
	protected abstract int getDeviceData(long di_device, ByteBuffer event_buffer, int event_buffer_size, IntBuffer buffer, int position, int size);

	/**
	 * Device data is returned in tuples of the form <dwOfs, dwData>.
	 * buffer position() is moved accordingly to number of events.
	 */
	public int getDeviceState(ByteBuffer buffer) {
		return getDeviceState(di_device, buffer, buffer.position(), buffer.remaining());
	}
	protected abstract int getDeviceState(long di_device, ByteBuffer buffer, int position, int size);

	public void unacquire() {
		unacquire(di_device);
	}
	protected abstract int unacquire(long di_device);

	public int enumObjects(WindowsDirectInputDeviceObjectCallback enumerator) {
		return enumObjects(di_device, enumerator);
	}
	protected abstract int enumObjects(long di_device, WindowsDirectInputDeviceObjectCallback enumerator);

	protected abstract int getEventSize();
}
