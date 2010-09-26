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
package org.lwjgl;

/**
 * Base PointerWrapper implementation.
 *
 * @author Spasi
 */
public abstract class PointerWrapperAbstract implements PointerWrapper {

	protected final long pointer;

	protected PointerWrapperAbstract(final long pointer) {
		this.pointer = pointer;
	}

	public final boolean isNull() {
		return pointer == 0;
	}

	public final void checkNull() {
		if ( LWJGLUtil.DEBUG && pointer == 0 )
			throw new IllegalStateException("This pointer is null.");
	}

	public long getPointer() {
		return pointer;
	}

	public boolean equals(final Object o) {
		if ( this == o ) return true;
		if ( !(o instanceof PointerWrapperAbstract) ) return false;

		final PointerWrapperAbstract that = (PointerWrapperAbstract)o;

		if ( pointer != that.pointer ) return false;

		return true;
	}

	public int hashCode() {
		return (int)(pointer ^ (pointer >>> 32));
	}

}