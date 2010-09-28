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

import java.nio.ByteBuffer;

/**
 * This class is a wrapper around a cl_mem pointer.
 *
 * @author Spasi
 */
public final class CLMem extends CLObjectChild<CLContext> {

	private static final CLMemUtil util = (CLMemUtil)CLPlatform.getInfoUtilInstance(CLMem.class, "CL_MEM_UTIL");

	CLMem(final long pointer, final CLContext context) {
		super(pointer, context);
		if ( isValid() )
			context.getCLMemRegistry().registerObject(this);
	}

	// ---------------[ UTILITY METHODS ]---------------

	/**
	 * Returns the integer value of the specified parameter.
	 *
	 * @param param_name the parameter
	 *
	 * @return the parameter value
	 */
	public int getInfoInt(int param_name) {
		return util.getInfoInt(this, param_name);
	}

	/**
	 * Returns the size_t value of the specified parameter.
	 *
	 * @param param_name the parameter
	 *
	 * @return the parameter value
	 */
	public long getInfoSize(int param_name) {
		return util.getInfoSize(this, param_name);
	}

	/**
	 * Returns the long value of the specified parameter. Can be used
	 * for both cl_ulong and cl_bitfield parameters.
	 *
	 * @param param_name the parameter
	 *
	 * @return the parameter value
	 */
	public long getInfoLong(int param_name) {
		return util.getInfoLong(this, param_name);
	}

	/**
	 * Returns a direct ByteBuffer instance that points to the host
	 * memory that backs this CLMem object. Applicable only to CLMem
	 * objects that were created with the CL_MEM_USE_HOST_PTR flag.
	 *
	 * @return the host memory ByteBuffer
	 */
	public ByteBuffer getInfoHostBuffer() {
		return util.getInfoHostBuffer(this);
	}

	// clGetImageInfo methods

	/**
	 * Returns the size_t value of the specified parameter. Applicable to image objects only.
	 *
	 * @param param_name the parameter
	 *
	 * @return the parameter value
	 */
	public long getImageInfoSize(int param_name) {
		return util.getImageInfoSize(this, param_name);
	}

	/**
	 * Returns the image channel order. Applicable to image objects only.
	 *
	 * @return the parameter value
	 */
	public int getImageChannelOrder() {
		return util.getImageInfoFormat(this, 0);
	}

	/**
	 * Returns the image channel type. Applicable to image objects only.
	 *
	 * @return the parameter value
	 */
	public int getImageChannelType() {
		return util.getImageInfoFormat(this, 1);
	}

	// clGetGLObjectInfo methods

	/**
	 * Returns the GL object type. Applicable to CLMem objects
	 * that have been created GL objects only.
	 *
	 * @return the parameter value
	 */
	public int getGLObjectType() {
		return util.getGLObjectType(this);
	}

	/**
	 * Returns the GL object name. Applicable to CLMem objects
	 * that have been created GL objects only.
	 *
	 * @return the parameter value
	 */
	public int getGLObjectName() {
		return util.getGLObjectName(this);
	}

	// clGetGLTextureInfo methods

	/**
	 * Returns the int value of the specified parameter. Applicable to CLMem objects
	 * that have been created by GL textures only.
	 *
	 * @param param_name the parameter
	 *
	 * @return the parameter value
	 */
	public int getGLTextureInfoInt(int param_name) {
		return util.getGLTextureInfoInt(this, param_name);
	}

	/** CLMem utility methods interface. */
	interface CLMemUtil extends InfoUtil<CLMem> {

		ByteBuffer getInfoHostBuffer(CLMem mem);

		long getImageInfoSize(CLMem mem, int param_name);

		int getImageInfoFormat(CLMem mem, int index);

		int getGLObjectType(CLMem mem);

		int getGLObjectName(CLMem mem);

		int getGLTextureInfoInt(CLMem mem, int param_name);

	}

	// -------[ IMPLEMENTATION STUFF BELOW ]-------

	/**
	 * Sub-buffer factory. clCreateSubBuffer may return an existing CLMem.
	 *
	 * @param pointer the sub-buffer id
	 * @param context the context in which the sub-buffer was created
	 *
	 * @return the CLMem that represents the sub-buffer
	 */
	static CLMem create(final long pointer, final CLContext context) {
		CLMem clMem = context.getCLMemRegistry().getObject(pointer);
		if ( clMem == null )
			clMem = new CLMem(pointer, context);
		else
			clMem.retain();

		return clMem;
	}

	int release() {
		try {
			return super.release();
		} finally {
			if ( !isValid() )
				getParent().getCLMemRegistry().unregisterObject(this);
		}
	}

}