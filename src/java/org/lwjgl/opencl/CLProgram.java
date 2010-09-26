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

import java.io.Serializable;

/**
 * This class is a wrapper around a cl_program pointer.
 *
 * @author Spasi
 */
public final class CLProgram extends CLObjectChild<CLContext> {

	private final CLObjectRegistry<CLKernel> clKernels;

	CLProgram(final long pointer, final CLContext context) {
		super(pointer, context);

		if ( isValid() ) {
			context.getCLProgramRegistry().registerObject(this);
			clKernels = new CLObjectRegistry<CLKernel>();
		} else
			clKernels = null;
	}

	/**
	 * Returns a CLKernel associated with this program.
	 *
	 * @param id the kernel id
	 *
	 * @return the CLKernel object
	 */
	public CLKernel getCLKernel(final long id) {
		return clKernels.getObject(id);
	}

	// -------[ IMPLEMENTATION STUFF BELOW ]-------

	CLObjectRegistry<CLKernel> getCLKernelRegistry() { return clKernels; }

	/**
	 * Called from clCreateKernelsInProgram to register new CLKernels.
	 *
	 * @param kernels a buffer containing CLKernel pointers.
	 */
	void registerCLKernels(final PointerBuffer kernels) {
		for ( int i = kernels.position(); i < kernels.limit(); i++ ) {
			final long pointer = kernels.get(i);
			if ( pointer != 0 )
				new CLKernel(pointer, this);
		}
	}

	int release() {
		try {
			return super.release();
		} finally {
			if ( !isValid() )
				getParent().getCLProgramRegistry().unregisterObject(this);
		}
	}

}