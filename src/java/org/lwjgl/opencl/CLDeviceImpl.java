/*
 * Copyright (c) 2002-2010 LWJGL Project
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
package org.lwjgl.opencl;

import org.lwjgl.PointerBuffer;

import java.nio.ByteBuffer;

import static org.lwjgl.opencl.CL10.*;

/**
 * Implementation of CLDevice helper methods.
 *
 * @author Spasi
 */
final class CLDeviceImpl implements CLDevice.CLDeviceImpl {

	CLDeviceImpl() {
	}

	static CLDeviceCapabilities getCapabilities(final CLDevice device) {
		device.checkValid();

		CLDeviceCapabilities caps = (CLDeviceCapabilities)device.getCapabilities();
		if ( caps == null )
			device.setCapabilities(caps = new CLDeviceCapabilities(device));

		return caps;
	}

	private static int getInfoBytes(final CLDevice device, final int param_name) {
		final PointerBuffer bytes = APIUtil.getBufferPointer();
		clGetDeviceInfo(device, param_name, null, bytes);
		return (int)bytes.get(0);
	}

	public String getInfoString(final CLDevice device, final int param_name) {
		device.checkValid();

		final int bytes = getInfoBytes(device, param_name);

		final ByteBuffer versionBuffer = APIUtil.getBufferByte(bytes);
		clGetDeviceInfo(device, param_name, versionBuffer, null);

		versionBuffer.limit(bytes - 1); // Exclude null-termination
		return APIUtil.getString(versionBuffer);
	}

	public int getInfoInt(final CLDevice device, final int param_name) {
		device.checkValid();

		final ByteBuffer versionBuffer = APIUtil.getBufferByte(4);
		clGetDeviceInfo(device, param_name, versionBuffer, null);

		return versionBuffer.getInt(0);
	}

	public long getInfoSize(final CLDevice device, final int param_name) {
		device.checkValid();

		final PointerBuffer pointerBuffer = APIUtil.getBufferPointer();
		clGetDeviceInfo(device, param_name, pointerBuffer.getBuffer(), null);

		return pointerBuffer.get(0);
	}

	public long getInfoLong(final CLDevice device, final int param_name) {
		device.checkValid();

		final ByteBuffer versionBuffer = APIUtil.getBufferByte(8);
		clGetDeviceInfo(device, param_name, versionBuffer, null);

		return versionBuffer.getLong(0);
	}
}