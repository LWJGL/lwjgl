/* 
 * Copyright (c) 2002 Lighweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

import java.nio.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * $Id$
 *
 * A floating point math library, with vector processing capabilities.
 * This class differs significantly from its java.lang.Math counterpart
 * in that it a) makes no claims about strict accuracy and b) uses degrees
 * rather than radians for its functions which are more friendly to use.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public final class Math {

	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	/** Floating point version of pi */
	public static final float PI = (float) java.lang.Math.PI;

	/**
	 * No constructor for Math.
	 */
	private Math() {
		super();
	}

	/**
	 * Return the sine of theta
	 *
	 * @param theta angle in degrees
	 * @return sin(theta)
	 */
	public static float sin(float theta) {
		return (float) java.lang.Math.sin(java.lang.Math.toRadians(theta));
	}

	/**
	 * Return the cosine of theta
	 *
	 * @param theta angle in degrees
	 * @return cos(theta)
	 */
	public static float cos(float theta) {
		return (float) java.lang.Math.cos(java.lang.Math.toRadians(theta));
	}
	
	/**
	 * Return the tangent of theta
	 *
	 * @param theta angle in degrees
	 * @return tan(theta)
	 */
	public static float tan(float theta) {
		return (float) java.lang.Math.tan(java.lang.Math.toRadians(theta));
	}

	/**
	 * Return the inverse sine of theta
	 *
	 * @param theta angle in degrees
	 * @return asin(theta)
	 */
	public static float asin(float theta) {
		return (float) java.lang.Math.toDegrees(java.lang.Math.asin(theta));
	}

	/**
	 * Return the inverse cosine of theta
	 *
	 * @param theta angle in degrees
	 * @return acos(theta)
	 */
	public static float acos(float theta) {
		return (float) java.lang.Math.toDegrees(java.lang.Math.acos(theta));
	}
	
	/**
	 * Return the inverse tangent of theta
	 *
	 * @param theta angle in degrees
	 * @return atan(theta)
	 */
	public static float atan(float theta) {
		return (float) java.lang.Math.toDegrees(java.lang.Math.atan(theta));
	}
	
	/* We use NIO to do our bit fiddling */
	private static final ByteBuffer sqrtByteBuf = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
	private static final IntBuffer sqrtIntBuf = sqrtByteBuf.asIntBuffer();
	private static final FloatBuffer sqrtFloatBuf = sqrtByteBuf.asFloatBuffer();
	
	/**
	 * Approximate inverse square root function (Newton-Raphson?). This is a very approximate
	 * root, accurate to maybe 1 or 2 dp.
	 * @param x
	 * @return ~x^0.5
	 */
	public static float invsqrt(float x) {
		float xhalf = 0.5f * x;
		sqrtFloatBuf.put(0, x);
		int i = sqrtIntBuf.get(0);
		i = 0x5f375a86 - (i >> 1);
		sqrtIntBuf.put(0, i);
		x = sqrtFloatBuf.get(0);
		x *= (1.5f - xhalf * x * x); // This line may be duplicated for more accuracy.
		return x;
	}

	/**
	 * Approximate square root function (Newton-Raphson?). This is a very approximate
	 * root, accurate to maybe 1 or 2 dp.
	 * @param x
	 * @return ~x^0.5
	 */
	public static float sqrt(float x) {
		return 1.0f / invsqrt(x);
	}
}
