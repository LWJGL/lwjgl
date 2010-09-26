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

import org.lwjgl.PointerWrapper;

/**
 * This class is a wrapper around a cl_kernel pointer.
 *
 * @author Spasi
 */
public final class CLKernel extends CLObjectChild<CLProgram> {

	private static final CLKernelImpl impl = (CLKernelImpl)CLPlatform.getClassInstance("org.lwjgl.opencl.CLKernelImpl");

	CLKernel(final long pointer, final CLProgram program) {
		super(pointer, program);
		if ( isValid() )
			program.getCLKernelRegistry().registerObject(this);
	}

	int release() {
		try {
			return super.release();
		} finally {
			if ( !isValid() )
				getParent().getCLKernelRegistry().unregisterObject(this);
		}
	}

	public CLKernel setArg(final int index, final byte value) {
		impl.setArg(this, index, value);
		return this;
	}

	public CLKernel setArg(final int index, final short value) {
		impl.setArg(this, index, value);
		return this;
	}

	public CLKernel setArg(final int index, final int value) {
		impl.setArg(this, index, value);
		return this;
	}

	public CLKernel setArg(final int index, final long value) {
		impl.setArg(this, index, value);
		return this;
	}

	public CLKernel setArg(final int index, final float value) {
		impl.setArg(this, index, value);
		return this;
	}

	public CLKernel setArg(final int index, final double value) {
		impl.setArg(this, index, value);
		return this;
	}

	public CLKernel setArg(final int index, final PointerWrapper value) {
		impl.setArg(this, index, value);
		return this;
	}

	interface CLKernelImpl {

		void setArg(CLKernel clKernel, int index, byte value);

		void setArg(CLKernel clKernel, int index, short value);

		void setArg(CLKernel clKernel, int index, int value);

		void setArg(CLKernel clKernel, int index, long value);

		void setArg(CLKernel clKernel, int index, float value);

		void setArg(CLKernel clKernel, int index, double value);

		void setArg(CLKernel clKernel, int index, PointerWrapper pointer);

	}

}