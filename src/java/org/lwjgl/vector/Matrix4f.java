/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
package org.lwjgl.vector;

import java.nio.FloatBuffer;

/**
 * Holds a 4x4 float matrix.
 *
 * @author foo
 */
public class Matrix4f {
	/**
	 * Set this matrix to be the identity matrix.
	 * @return this
	 */
	public Matrix4f setIdentity() {
		m00 = 1.0f;
		m01 = 0.0f;
		m02 = 0.0f;
		m03 = 0.0f;
		m10 = 0.0f;
		m11 = 1.0f;
		m12 = 0.0f;
		m13 = 0.0f;
		m20 = 0.0f;
		m21 = 0.0f;
		m22 = 1.0f;
		m23 = 0.0f;
		m30 = 0.0f;
		m31 = 0.0f;
		m32 = 0.0f;
		m33 = 1.0f;
		
		return this;
	}


	/**
	 * Set this matrix to 0.
	 * @return this
	 */
	public Matrix4f setZero() {
		m00 = 0.0f;
		m01 = 0.0f;
		m02 = 0.0f;
		m03 = 0.0f;
		m10 = 0.0f;
		m11 = 0.0f;
		m12 = 0.0f;
		m13 = 0.0f;
		m20 = 0.0f;
		m21 = 0.0f;
		m22 = 0.0f;
		m23 = 0.0f;
		m30 = 0.0f;
		m31 = 0.0f;
		m32 = 0.0f;
		m33 = 0.0f;
		
		return this;
	}


	
	public float m00 = 1.0f, m01, m02, m03, m10, m11 = 1.0f, m12, m13, m20, m21, m22 = 1.0f, m23, m30, m31, m32, m33 = 1.0f;
	
	/**
	 * Construct a Matrix4f
	 */
	public Matrix4f() {
		super();
	}
	/**
	 * Load from another matrix4f
	 * @param src The source matrix
	 * @return this
	 */
	public Matrix4f load(Matrix4f src) {
		
		m00 = src.m00;
		m01 = src.m01;
		m02 = src.m02;
		m03 = src.m03;
		m10 = src.m10;
		m11 = src.m11;
		m12 = src.m12;
		m13 = src.m13;
		m20 = src.m20;
		m21 = src.m21;
		m22 = src.m22;
		m23 = src.m23;
		m30 = src.m30;
		m31 = src.m31;
		m32 = src.m32;
		m33 = src.m33;
		
		return this;
	}
	
	/**
	 * Load from a float buffer. The buffer stores the matrix in column major
	 * (OpenGL) order.
	 * 
	 * @param buf A float buffer to read from
	 * @return this
	 */
	public Matrix4f load(FloatBuffer buf) {
		
		m00 = buf.get();
		m10 = buf.get();
		m20 = buf.get();
		m30 = buf.get();
		m01 = buf.get();
		m11 = buf.get();
		m21 = buf.get();
		m31 = buf.get();
		m02 = buf.get();
		m12 = buf.get();
		m22 = buf.get();
		m32 = buf.get();
		m03 = buf.get();
		m13 = buf.get();
		m23 = buf.get();
		m33 = buf.get();
		
		return this;
	}
	
	/**
	 * Store this matrix in a float buffer. The matrix is stored in column
	 * major (openGL) order.
	 * @param buf The buffer to store this matrix in
	 */
	public void store(FloatBuffer buf) {
		buf.put(m00);
		buf.put(m10);
		buf.put(m20);
		buf.put(m30);
		buf.put(m01);
		buf.put(m11);
		buf.put(m21);
		buf.put(m31);
		buf.put(m02);
		buf.put(m12);
		buf.put(m22);
		buf.put(m32);
		buf.put(m03);
		buf.put(m13);
		buf.put(m23);
		buf.put(m33);
	}
	
	
	/**
	 * Add two matrices together and place the result in a third matrix.
	 * @param left The left source matrix
	 * @param right The right source matrix
	 * @param dest The destination matrix, or null if a new one is to be created
	 * @return the destination matrix
	 */
	public static Matrix4f add(Matrix4f left, Matrix4f right, Matrix4f dest) {
		
		Matrix4f temp = null;
		
		if (dest == null)
			dest = new Matrix4f();
		else if (dest == left || dest == right) {
			temp = dest;
			dest = new Matrix4f();
		}
		
		dest.m00 = left.m00 + right.m00;
		dest.m01 = left.m01 + right.m01;
		dest.m02 = left.m02 + right.m02;
		dest.m03 = left.m03 + right.m03;
		dest.m10 = left.m10 + right.m10;
		dest.m11 = left.m11 + right.m11;
		dest.m12 = left.m12 + right.m12;
		dest.m13 = left.m13 + right.m13;
		dest.m20 = left.m20 + right.m20;
		dest.m21 = left.m21 + right.m21;
		dest.m22 = left.m22 + right.m22;
		dest.m23 = left.m23 + right.m23;
		dest.m30 = left.m30 + right.m30;
		dest.m31 = left.m31 + right.m31;
		dest.m32 = left.m32 + right.m32;
		dest.m33 = left.m33 + right.m33;
			
		
		if (temp != null) {
			temp.load(dest);
			return temp;
		} else
			return dest;		
	} 

	/**
	 * Subtract the right matrix from the left and place the result in a third matrix.
	 * @param left The left source matrix
	 * @param right The right source matrix
	 * @param dest The destination matrix, or null if a new one is to be created
	 * @return the destination matrix
	 */
	public static Matrix4f sub(Matrix4f left, Matrix4f right, Matrix4f dest) {
		
		Matrix4f temp = null;
		
		if (dest == null)
			dest = new Matrix4f();
		else if (dest == left || dest == right) {
			temp = dest;
			dest = new Matrix4f();
		}
			
		dest.m00 = left.m00 - right.m00;
		dest.m01 = left.m01 - right.m01;
		dest.m02 = left.m02 - right.m02;
		dest.m03 = left.m03 - right.m03;
		dest.m10 = left.m10 - right.m10;
		dest.m11 = left.m11 - right.m11;
		dest.m12 = left.m12 - right.m12;
		dest.m13 = left.m13 - right.m13;
		dest.m20 = left.m20 - right.m20;
		dest.m21 = left.m21 - right.m21;
		dest.m22 = left.m22 - right.m22;
		dest.m23 = left.m23 - right.m23;
		dest.m30 = left.m30 - right.m30;
		dest.m31 = left.m31 - right.m31;
		dest.m32 = left.m32 - right.m32;
		dest.m33 = left.m33 - right.m33;
		
		if (temp != null) {
			temp.load(dest);
			return temp;
		} else
			return dest;		
	} 

	/**
	 * Multiply the right matrix by the left and place the result in a third matrix.
	 * @param left The left source matrix
	 * @param right The right source matrix
	 * @param dest The destination matrix, or null if a new one is to be created
	 * @return the destination matrix
	 */
	public static Matrix4f mul(Matrix4f left, Matrix4f right, Matrix4f dest) {
		
		Matrix4f temp = null;
		
		if (dest == null)
			dest = new Matrix4f();
		else if (dest == left || dest == right) {
			temp = dest;
			dest = new Matrix4f();
		}
			
			
		dest.m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02 + left.m30 * right.m03;
		dest.m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02 + left.m31 * right.m03;
		dest.m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02 + left.m32 * right.m03;
		dest.m03 = left.m03 * right.m00 + left.m13 * right.m01 + left.m23 * right.m02 + left.m33 * right.m03;
		dest.m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12 + left.m30 * right.m13;
		dest.m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12 + left.m31 * right.m13;
		dest.m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12 + left.m32 * right.m13;
		dest.m13 = left.m03 * right.m10 + left.m13 * right.m11 + left.m23 * right.m12 + left.m33 * right.m13;
		dest.m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22 + left.m30 * right.m23;
		dest.m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22 + left.m31 * right.m23;
		dest.m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22 + left.m32 * right.m23;
		dest.m23 = left.m03 * right.m20 + left.m13 * right.m21 + left.m23 * right.m22 + left.m33 * right.m23;
		dest.m30 = left.m00 * right.m30 + left.m10 * right.m31 + left.m20 * right.m32 + left.m30 * right.m33;
		dest.m31 = left.m01 * right.m30 + left.m11 * right.m31 + left.m21 * right.m32 + left.m31 * right.m33;
		dest.m32 = left.m02 * right.m30 + left.m12 * right.m31 + left.m22 * right.m32 + left.m32 * right.m33;
		dest.m33 = left.m03 * right.m30 + left.m13 * right.m31 + left.m23 * right.m32 + left.m33 * right.m33;

		if (temp != null) {
			temp.load(dest);
			return temp;
		} else
			return dest;		
	}
	
	/**
	 * Transform a Vector by a matrix and return the result in a destination
	 * vector. 
	 * @param left The left matrix
	 * @param right The right vector
	 * @param dest The destination vector, or null if a new one is to be created
	 * @return the destination vector
	 */
	public static Vector4f transform(Matrix4f left, Vector4f right, Vector4f dest) {
		
		Vector4f temp = null;
		
		if (dest == null)
			dest = new Vector4f();
		else if (dest == right) {
			temp = dest;
			dest = new Vector4f();
		}
		
			
		dest.x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z + left.m30 * right.w;
		dest.y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z + left.m31 * right.w;
		dest.z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z + left.m32 * right.w;
		dest.w = left.m03 * right.x + left.m13 * right.y + left.m23 * right.z + left.m33 * right.w;

		if (temp != null) {
			temp.set(dest);
			return temp;
		} else
			return dest;		
	}
	
	/**
	 * Transpose this matrix
	 * @return this
	 */
	public Matrix4f transpose() {

		float f = m10;
		m10 = m01;
		m01 = f;
		f = m20;
		m20 = m02;
		m02 = f;
		f = m30;
		m30 = m03;
		m03 = f;
		f = m21;
		m21 = m12;
		m12 = f;
		f = m31;
		m31 = m13;
		m13 = f;
		f = m32;
		m32 = m23;
		m23 = f;
		
		return this;
	}
	
	/**
	 * Transpose this matrix and place the result in another matrix
	 * @param dest The destination matrix or null if a new matrix is to be created
	 * @return the transposed matrix
	 */
	public Matrix4f transpose(Matrix4f dest) {

		if (this != dest) {
			m00 = dest.m00;
			m01 = dest.m10;
			m02 = dest.m20;
			m03 = dest.m30;
			m10 = dest.m01;
			m11 = dest.m11;
			m12 = dest.m21;
			m13 = dest.m31;
			m20 = dest.m02;
			m21 = dest.m12;
			m22 = dest.m22;
			m23 = dest.m32;
			m30 = dest.m03;
			m31 = dest.m13;
			m32 = dest.m23;
			m33 = dest.m33;
		} else
			transpose();

		return dest;
	}

	/**
	 * @return the determinant of the matrix
	 */
	public float determinant() {
		float f =
			m00
				* ((m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32)
					- m13 * m22 * m31
					- m11 * m23 * m32
					- m12 * m21 * m33);
		f -= m01
			* ((m10 * m22 * m33 + m12 * m23 * m30 + m13 * m20 * m32)
				- m13 * m22 * m30
				- m10 * m23 * m32
				- m12 * m20 * m33);
		f += m02
			* ((m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31)
				- m13 * m21 * m30
				- m10 * m23 * m31
				- m11 * m20 * m33);
		f -= m03
			* ((m10 * m21 * m32 + m11 * m22 * m30 + m12 * m20 * m31)
				- m12 * m21 * m30
				- m10 * m22 * m31
				- m11 * m20 * m32);
		return f;
	}
		

	/**
	 * Invert this matrix
	 * @return this
	 */
	public Matrix4f invert() {
		return this;
	}
	
	/**
	 * Negate this matrix
	 * @return this
	 */
	public Matrix4f negate() {
		m00 = -m00;
		m01 = -m01;
		m02 = -m02;
		m03 = -m03;
		m10 = -m10;
		m11 = -m11;
		m12 = -m12;
		m13 = -m13;
		m20 = -m20;
		m21 = -m21;
		m22 = -m22;
		m23 = -m23;
		m30 = -m30;
		m31 = -m31;
		m32 = -m32;
		m33 = -m33;
		return this;
	}
	
	/**
	 * Negate this matrix and place the result in a destination matrix.
	 * @param dest The destination matrix, or null if a new matrix is to be created
	 * @return the negated matrix
	 */
	public Matrix4f negate(Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();

		dest.m00 = -m00;
		dest.m01 = -m01;
		dest.m02 = -m02;
		dest.m03 = -m03;
		dest.m10 = -m10;
		dest.m11 = -m11;
		dest.m12 = -m12;
		dest.m13 = -m13;
		dest.m20 = -m20;
		dest.m21 = -m21;
		dest.m22 = -m22;
		dest.m23 = -m23;
		dest.m30 = -m30;
		dest.m31 = -m31;
		dest.m32 = -m32;
		dest.m33 = -m33;

		return dest;
	}

}
