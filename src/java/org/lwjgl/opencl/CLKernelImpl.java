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
import org.lwjgl.PointerWrapper;

import static org.lwjgl.opencl.CL10.*;

/**
 * Implementation of CLKernel helper methods.
 *
 * @author Spasi
 */
final class CLKernelImpl implements CLKernel.CLKernelImpl {

	CLKernelImpl() {
	}

	public void setArg(final CLKernel clKernel, final int index, final byte value) {
		clSetKernelArg(clKernel, index, 1, APIUtil.getBufferByte(1).put(0, value));
	}

	public void setArg(final CLKernel clKernel, final int index, final short value) {
		clSetKernelArg(clKernel, index, 2, APIUtil.getBufferShort().put(0, value));
	}

	public void setArg(final CLKernel clKernel, final int index, final int value) {
		clSetKernelArg(clKernel, index, 4, APIUtil.getBufferInt().put(0, value));
	}

	public void setArg(final CLKernel clKernel, final int index, final long value) {
		clSetKernelArg(clKernel, index, 8, APIUtil.getBufferLong().put(0, value));
	}

	public void setArg(final CLKernel clKernel, final int index, final float value) {
		clSetKernelArg(clKernel, index, 4, APIUtil.getBufferFloat().put(0, value));
	}

	public void setArg(final CLKernel clKernel, final int index, final double value) {
		clSetKernelArg(clKernel, index, 8, APIUtil.getBufferDouble().put(0, value));
	}

	public void setArg(final CLKernel clKernel, final int index, final PointerWrapper pointer) {
		clSetKernelArg(clKernel, index, PointerBuffer.getPointerSize(), APIUtil.getBufferPointer().put(0, pointer).getBuffer());
	}

}