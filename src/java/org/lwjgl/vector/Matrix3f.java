/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Lightweight Java Game Library' nor the names of 
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

import java.io.Serializable;
import java.nio.FloatBuffer;

/**
 * $Id$
 *
 * Holds a 3x3 matrix.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

public class Matrix3f extends Matrix implements Serializable {

	public float m00 = 1.0f,
		m01,
		m02,
		m10,
		m11 = 1.0f,
		m12,
		m20,
		m21,
		m22 = 1.0f;

	/**
	 * Constructor for Matrix3f.
	 */
	public Matrix3f() {
		super();
	}
	/**
	 * Load from another matrix3f
	 * @param src The source matrix
	 * @return this
	 */
	public Matrix3f load(Matrix3f src) {

		m00 = src.m00;
		m10 = src.m10;
		m20 = src.m20;
		m01 = src.m01;
		m11 = src.m11;
		m21 = src.m21;
		m02 = src.m02;
		m12 = src.m12;
		m22 = src.m22;

		return this;
	}
	
	/**
	 * Load from a float buffer. The buffer stores the matrix in column major
	 * (OpenGL) order.
	 * 
	 * @param buf A float buffer to read from
	 * @return this
	 */
	public Matrix load(FloatBuffer buf) {
		
		m00 = buf.get();
		m01 = buf.get();
		m02 = buf.get();
		m10 = buf.get();
		m11 = buf.get();
		m12 = buf.get();
		m20 = buf.get();
		m21 = buf.get();
		m22 = buf.get();
		
		return this;
	}
	
	/**
	 * Load from a float buffer. The buffer stores the matrix in row major
	 * (maths) order.
	 * 
	 * @param buf A float buffer to read from
	 * @return this
	 */
	public Matrix loadTranspose(FloatBuffer buf) {
		
		m00 = buf.get();
		m10 = buf.get();
		m20 = buf.get();
		m01 = buf.get();
		m11 = buf.get();
		m21 = buf.get();
		m02 = buf.get();
		m12 = buf.get();
		m22 = buf.get();
		
		return this;
	}	
	
	/**
	 * Store this matrix in a float buffer. The matrix is stored in column
	 * major (openGL) order.
	 * @param buf The buffer to store this matrix in
	 */
	public Matrix store(FloatBuffer buf) {
		buf.put(m00);
		buf.put(m01);
		buf.put(m02);
		buf.put(m10);
		buf.put(m11);
		buf.put(m12);
		buf.put(m20);
		buf.put(m21);
		buf.put(m22);
		return this;
	}
	
	/**
	 * Store this matrix in a float buffer. The matrix is stored in row
	 * major (maths) order.
	 * @param buf The buffer to store this matrix in
	 */
	public Matrix storeTranspose(FloatBuffer buf) {
		buf.put(m00);
		buf.put(m10);
		buf.put(m20);
		buf.put(m01);
		buf.put(m11);
		buf.put(m21);
		buf.put(m02);
		buf.put(m12);
		buf.put(m22);
		return this;
	}	
	
	

	/**
	 * Add two matrices together and place the result in a third matrix.
	 * @param left The left source matrix
	 * @param right The right source matrix
	 * @param dest The destination matrix, or null if a new one is to be created
	 * @return the destination matrix
	 */
	public static Matrix3f add(Matrix3f left, Matrix3f right, Matrix3f dest) {

		Matrix3f temp = null;

		if (dest == null)
			dest = new Matrix3f();
		else if (dest == left || dest == right) {
			temp = dest;
			dest = new Matrix3f();
		}

		dest.m00 = left.m00 + right.m00;
		dest.m01 = left.m01 + right.m01;
		dest.m02 = left.m02 + right.m02;
		dest.m10 = left.m10 + right.m10;
		dest.m11 = left.m11 + right.m11;
		dest.m12 = left.m12 + right.m12;
		dest.m20 = left.m20 + right.m20;
		dest.m21 = left.m21 + right.m21;
		dest.m22 = left.m22 + right.m22;

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
	public static Matrix3f sub(Matrix3f left, Matrix3f right, Matrix3f dest) {

		Matrix3f temp = null;

		if (dest == null)
			dest = new Matrix3f();
		else if (dest == left || dest == right) {
			temp = dest;
			dest = new Matrix3f();
		}

		dest.m00 = left.m00 - right.m00;
		dest.m01 = left.m01 - right.m01;
		dest.m02 = left.m02 - right.m02;
		dest.m10 = left.m10 - right.m10;
		dest.m11 = left.m11 - right.m11;
		dest.m12 = left.m12 - right.m12;
		dest.m20 = left.m20 - right.m20;
		dest.m21 = left.m21 - right.m21;
		dest.m22 = left.m22 - right.m22;

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
	public static Matrix3f mul(Matrix3f left, Matrix3f right, Matrix3f dest) {

		Matrix3f temp = null;

		if (dest == null)
			dest = new Matrix3f();
		else if (dest == left || dest == right) {
			temp = dest;
			dest = new Matrix3f();
		}

		dest.m00 =
			left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02;
		dest.m01 =
			left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02;
		dest.m02 =
			left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02;
		dest.m10 =
			left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12;
		dest.m11 =
			left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12;
		dest.m12 =
			left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12;
		dest.m20 =
			left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22;
		dest.m21 =
			left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22;
		dest.m22 =
			left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22;

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
	public static Vector3f transform(Matrix3f left, Vector3f right, Vector3f dest) {

		Vector3f temp = null;

		if (dest == null)
			dest = new Vector3f();
		else if (dest == right) {
			temp = dest;
			dest = new Vector3f();
		}

		dest.x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z;
		dest.y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z;
		dest.z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z;

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
	public Matrix transpose() {
		float f = m10;
		m10 = m01;
		m01 = f;
		f = m20;
		m20 = m02;
		m02 = f;
		f = m21;
		m21 = m12;
		m12 = f;
		return this;
	}

	/**
	 * Transpose this matrix and place the result in another matrix
	 * @param dest The destination matrix or null if a new matrix is to be created
	 * @return the transposed matrix
	 */
	public Matrix3f transpose(Matrix3f dest) {
		if (dest == null) { 
		   // New matrix needed to store transpose 
		   dest = new Matrix3f(); 
		} 
		if (this == dest) { 
		   // Destination and source are the same!  Run the in-place 
		   // transpose instead as the copy transpose will be destructive. 
		   transpose(); 
		} else { 
		   // Destination differs from source.  Perform copy transpose 
		   dest.m00 = m00; 
		   dest.m01 = m10; 
		   dest.m02 = m20; 
		   dest.m10 = m01; 
		   dest.m11 = m11; 
		   dest.m12 = m21; 
		   dest.m20 = m02; 
		   dest.m21 = m12; 
		   dest.m22 = m22; 
		} 
		return dest; 
	}

	/**
	 * @return the determinant of the matrix
	 */
	public float determinant() {
		float f =
			m00 * (m11 * m22 - m12 * m21)
				+ m01 * (m12 * m20 - m10 * m22)
				+ m02 * (m10 * m21 - m11 * m20);
		return f;
	}

	/**
	 * Returns a string representation of this matrix
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(m00).append(' ').append(m10).append(' ').append(m20).append(' ').append('\n');
		buf.append(m01).append(' ').append(m11).append(' ').append(m21).append(' ').append('\n');
		buf.append(m02).append(' ').append(m12).append(' ').append(m22).append(' ').append('\n');
		return buf.toString();
	}

	/**
	 * Invert this matrix
	 * @return this if successful, null otherwise
	 */
	public Matrix invert() 
	{
		float determinant = determinant();
		
		if (determinant != 0)
		{
			 /* do it the ordinary way
			  *
			  * inv(A) = 1/det(A) * adj(T), where adj(T) = transpose(Conjugate Matrix)
			  *
			  * m00 m01 m02 
			  * m10 m11 m12
			  * m20 m21 m22  
			  */
			 float determinant_inv = 1f/determinant;
			 
			 // get the conjugate matrix
			 float t00 = m11 * m22 - m12* m21;
			 float t01 = - m10 * m22 + m12 *m20;
			 float t02 = m10 * m21 - m11 * m20;
			 float t10 = - m01 * m22 + m02 * m21;
			 float t11 = m00 * m22 - m02 * m20;
			 float t12 = - m00 * m21 + m01 * m20;
			 float t20 = m01 * m12 - m02 * m11;
			 float t21 = -m00 * m12 + m02 * m10;
			 float t22 = m00 * m11 - m01 * m10;
			 
			 
			 m00 = t00*determinant_inv;
			 m11 = t11*determinant_inv;
			 m22 = t22*determinant_inv;
			 m01 = t10*determinant_inv;
			 m10 = t01*determinant_inv;
			 m20 = t02*determinant_inv;
			 m02 = t20*determinant_inv;
			 m12 = t21*determinant_inv;
			 m21 = t12*determinant_inv;
			 return this;
		} else
			 return null;
	}

	/**
	 * Negate this matrix
	 * @return this
	 */
	public Matrix negate() {
		m00 = -m00;
		m01 = -m02;
		m02 = -m01;
		m10 = -m10;
		m11 = -m12;
		m12 = -m11;
		m20 = -m20;
		m21 = -m22;
		m22 = -m21;
		return this;
	}

	/**
	 * Negate this matrix and place the result in a destination matrix.
	 * @param dest The destination matrix, or null if a new matrix is to be created
	 * @return the negated matrix
	 */
	public Matrix3f negate(Matrix3f dest) {
		if (dest == null)
			dest = new Matrix3f();

		dest.m00 = -m00;
		dest.m01 = -m02;
		dest.m02 = -m01;
		dest.m10 = -m10;
		dest.m11 = -m12;
		dest.m12 = -m11;
		dest.m20 = -m20;
		dest.m21 = -m22;
		dest.m22 = -m21;
		return dest;
	}

	/**
	 * Set this matrix to be the identity matrix.
	 * @return this
	 */
	public Matrix setIdentity() {
		m00 = 1.0f;
		m01 = 0.0f;
		m02 = 0.0f;
		m10 = 0.0f;
		m11 = 1.0f;
		m12 = 0.0f;
		m20 = 0.0f;
		m21 = 0.0f;
		m22 = 1.0f;
		return this;
	}

	/**
	 * Set this matrix to 0.
	 * @return this
	 */
	public Matrix setZero() {
		m00 = 0.0f;
		m01 = 0.0f;
		m02 = 0.0f;
		m10 = 0.0f;
		m11 = 0.0f;
		m12 = 0.0f;
		m20 = 0.0f;
		m21 = 0.0f;
		m22 = 0.0f;
		return this;
	}

}
