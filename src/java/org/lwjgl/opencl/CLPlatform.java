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

import org.lwjgl.PointerWrapperAbstract;
import org.lwjgl.opencl.api.Filter;

import java.util.List;

/**
 * This class is a wrapper around a cl_platform_id pointer.
 *
 * @author Spasi
 */
public final class CLPlatform extends PointerWrapperAbstract {

	private static final CLPlatformImpl impl = (CLPlatformImpl)getClassInstance("org.lwjgl.opencl.CLPlatformImpl");

	private Object caps;

	public CLPlatform(final long pointer) {
		super(pointer);
	}

	void setCapabilities(final Object caps) {
		this.caps = caps;
	}

	Object getCapabilities() {
		return caps;
	}

	// ---------------[ HELPER METHODS ]---------------

	static Object getClassInstance(final String className) {
		Object instance = null;
		try {
			instance = Class.forName(className).newInstance();
		} finally {
			return instance;
		}
	}

	public static List<CLPlatform> getPlatforms() {
		return getPlatforms(null);
	}

	public static List<CLPlatform> getPlatforms(final Filter<CLPlatform> filter) {
		return impl.getPlatforms(filter);
	}

	public String getInfoString(int param_name) {
		return impl.getInfoString(this, param_name);
	}

	public List<CLDevice> getDevices(final int device_type) {
		return getDevices(device_type, null);
	}

	public List<CLDevice> getDevices(final int device_type, final Filter<CLDevice> filter) {
		return impl.getDevices(this, device_type, filter);
	}

	/** CLPlatform helper methods implementation interface. */
	interface CLPlatformImpl {

		List<CLPlatform> getPlatforms(Filter<CLPlatform> filter);

		String getInfoString(CLPlatform platform, int param_name);

		List<CLDevice> getDevices(CLPlatform platform, int device_type, final Filter<CLDevice> filter);

	}

}