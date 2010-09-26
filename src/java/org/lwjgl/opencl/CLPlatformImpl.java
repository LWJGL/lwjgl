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
import org.lwjgl.opencl.api.Filter;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opencl.CL10.*;

/**
 * Implementation of CLPlatform helper methods.
 *
 * @author Spasi
 */
final class CLPlatformImpl implements CLPlatform.CLPlatformImpl {

	CLPlatformImpl() {
	}

	static CLPlatformCapabilities getCapabilities(final CLPlatform platform) {
		platform.checkNull();

		CLPlatformCapabilities caps = (CLPlatformCapabilities)platform.getCapabilities();
		if ( caps == null )
			platform.setCapabilities(caps = new CLPlatformCapabilities(platform));

		return caps;
	}

	public List<CLPlatform> getPlatforms(final Filter<CLPlatform> filter) {
		final IntBuffer numBuffer = APIUtil.getBufferInt();
		clGetPlatformIDs(null, numBuffer);

		final int num_platforms = numBuffer.get(0);
		if ( num_platforms == 0 )
			return null;

		final PointerBuffer platformIDs = APIUtil.getBufferPointer(num_platforms);
		clGetPlatformIDs(platformIDs, null);

		final List<CLPlatform> platforms = new ArrayList<CLPlatform>(num_platforms);
		for ( int i = 0; i < num_platforms; i++ ) {
			final CLPlatform platform = new CLPlatform(platformIDs.get(i));
			if ( filter == null || filter.accept(platform) )
				platforms.add(platform);
		}

		return platforms.size() == 0 ? null : platforms;
	}

	public String getInfoString(final CLPlatform platform, final int param_name) {
		platform.checkNull();

		final PointerBuffer bytes = APIUtil.getBufferPointer();
		clGetPlatformInfo(platform, param_name, null, bytes);

		final ByteBuffer versionBuffer = APIUtil.getBufferByte((int)bytes.get(0));
		clGetPlatformInfo(platform, param_name, versionBuffer, null);

		versionBuffer.limit((int)bytes.get(0) - 1); // Exclude null-termination
		return APIUtil.getString(versionBuffer);
	}

	public List<CLDevice> getDevices(final CLPlatform platform, final int device_type, final Filter<CLDevice> filter) {
		platform.checkNull();

		final IntBuffer numBuffer = APIUtil.getBufferInt();
		clGetDeviceIDs(platform, device_type, null, numBuffer);

		final int num_devices = numBuffer.get(0);
		if ( num_devices == 0 )
			return null;

		final PointerBuffer deviceIDs = APIUtil.getBufferPointer(num_devices);
		clGetDeviceIDs(platform, device_type, deviceIDs, null);

		final List<CLDevice> devices = new ArrayList<CLDevice>(num_devices);
		for ( int i = 0; i < num_devices; i++ ) {
			final CLDevice device = new CLDevice(deviceIDs.get(i));
			if ( filter == null || filter.accept(device) )
				devices.add(device);
		}

		return devices.size() == 0 ? null : devices;
	}

}