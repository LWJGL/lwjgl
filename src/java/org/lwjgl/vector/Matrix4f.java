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
 * Holds a 4x4 float matrix.
 *
 * @author foo
 */
public class Matrix4f extends Matrix implements Serializable {
	
	public float m00 = 1.0f, m01, m02, m03, m10, m11 = 1.0f, m12, m13, m20, m21, m22 = 1.0f, m23, m30, m31, m32, m33 = 1.0f;
	
	/**
	 * Construct a Matrix4f
	 */
	public Matrix4f() {
		super();
	}

	/**
	 * Returns a string representation of this matrix
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(m00).append(' ').append(m10).append(' ').append(m20).append(' ').append(m30).append('\n');
		buf.append(m01).append(' ').append(m11).append(' ').append(m21).append(' ').append(m31).append('\n');
		buf.append(m02).append(' ').append(m12).append(' ').append(m22).append(' ').append(m32).append('\n');
		buf.append(m03).append(' ').append(m13).append(' ').append(m23).append(' ').append(m33).append('\n');
		return buf.toString();
	}

	/**
	 * Set this matrix to be the identity matrix.
	 * @return this
	 */
	public Matrix setIdentity() {
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
	public Matrix setZero() {
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
	public Matrix load(FloatBuffer buf) {
		
		m00 = buf.get();
		m01 = buf.get();
		m02 = buf.get();
		m03 = buf.get();
		m10 = buf.get();
		m11 = buf.get();
		m12 = buf.get();
		m13 = buf.get();
		m20 = buf.get();
		m21 = buf.get();
		m22 = buf.get();
		m23 = buf.get();
		m30 = buf.get();
		m31 = buf.get();
		m32 = buf.get();
		m33 = buf.get();
		
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
	public Matrix store(FloatBuffer buf) {
		buf.put(m00);
		buf.put(m01);
		buf.put(m02);
		buf.put(m03);
		buf.put(m10);
		buf.put(m11);
		buf.put(m12);
		buf.put(m13);
		buf.put(m20);
		buf.put(m21);
		buf.put(m22);
		buf.put(m23);
		buf.put(m30);
		buf.put(m31);
		buf.put(m32);
		buf.put(m33);
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
		return this;
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
	public Matrix transpose() {

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
	 * Translate this matrix
	 * @param vec The vector to translate by
	 * @return this
	 */
	public Matrix4f translate(Vector2f vec) {
		m30 += m00 * vec.x + m10 * vec.y;
		m31 += m01 * vec.x + m11 * vec.y;
		m32 += m02 * vec.x + m12 * vec.y;
		m33 += m03 * vec.x + m13 * vec.y;
		return this;
	}
	
	/**
	 * Translate this matrix
	 * @param vec The vector to translate by
	 * @return this
	 */
	public Matrix4f translate(Vector3f vec) {
		m30 += m00 * vec.x + m10 * vec.y + m20 * vec.z;
		m31 += m01 * vec.x + m11 * vec.y + m21 * vec.z;
		m32 += m02 * vec.x + m12 * vec.y + m22 * vec.z;
		m33 += m03 * vec.x + m13 * vec.y + m23 * vec.z;
		return this;
	}

	/**
	 * Scales this matrix
	 * @param vec The vector to scale by
	 * @return this
	 */
	public Matrix4f scale(Vector3f vec) {
		m00 *= vec.x;
		m01 *= vec.x;
		m02 *= vec.x;
		m03 *= vec.x;
		m10 *= vec.y;
		m11 *= vec.y;
		m12 *= vec.y;
		m13 *= vec.y;
		m20 *= vec.z;
		m21 *= vec.z;
		m22 *= vec.z;
		m23 *= vec.z;
		return this;
	}
	
	/**
	 * Rotates the matrix around the given axis the specified angle
	 * @param angle the angle, in degrees.
	 * @param axis The vector representing the rotation axis. Must be normalized.
	 * @return this
	 */
	public Matrix4f rotate(float angle, Vector3f axis) {
		float c = (float) Math.cos(angle);
		float s = (float) Math.sin(angle);
		float oneminusc = 1.0f - c;
		float xy = axis.x*axis.y;
		float yz = axis.y*axis.z;
		float xz = axis.x*axis.z;
		float xs = axis.x*s;
		float ys = axis.y*s;
		float zs = axis.z*s;

		float f00 = axis.x*axis.x*oneminusc+c;
		float f01 = xy*oneminusc+zs;
		float f02 = xz*oneminusc-ys;
		// n[3] not used
		float f10 = xy*oneminusc-zs;
		float f11 = axis.y*axis.y*oneminusc+c;
		float f12 = yz*oneminusc+xs;
		// n[7] not used
		float f20 = xz*oneminusc+ys;
		float f21 = yz*oneminusc-xs;
		float f22 = axis.z*axis.z*oneminusc+c;

		float t00 = m00 * f00 + m10 * f01 + m20 * f02;
		float t01 = m01 * f00 + m11 * f01 + m21 * f02;
		float t02 = m02 * f00 + m12 * f01 + m22 * f02;
		float t03 = m03 * f00 + m13 * f01 + m23 * f02;
		float t10 = m00 * f10 + m10 * f11 + m20 * f12;
		float t11 = m01 * f10 + m11 * f11 + m21 * f12;
		float t12 = m02 * f10 + m12 * f11 + m22 * f12;
		float t13 = m03 * f10 + m13 * f11 + m23 * f12;
		m20 = m00 * f20 + m10 * f21 + m20 * f22;
		m21 = m01 * f20 + m11 * f21 + m21 * f22;
		m22 = m02 * f20 + m12 * f21 + m22 * f22;
		m23 = m03 * f20 + m13 * f21 + m23 * f22;
                m00 = t00;
                m01 = t01;
                m02 = t02;
                m03 = t03;
                m10 = t10;
                m11 = t11;
                m12 = t12;
                m13 = t13;
                return this;
	}

	/**
	 * Rotates the matrix around the given axis the specified angle, and stores it in the specified destination
	 * @param angle the angle, in degrees.
	 * @param axis The vector representing the rotation axis. Must be normalized.
	 * @param dest The destination matrix or null if a new matrix is to be created
	 * @return The rotated matrix
	 */
	public Matrix4f rotate(float angle, Vector3f axis, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();
		else if (dest == this)
			return rotate(angle, axis);
		float c = (float) Math.cos(angle);
		float s = (float) Math.sin(angle);
		float oneminusc = 1.0f - c;
		float xy = axis.x*axis.y;
		float yz = axis.y*axis.z;
		float xz = axis.x*axis.z;
		float xs = axis.x*s;
		float ys = axis.y*s;
		float zs = axis.z*s;

		float f0 = axis.x*axis.x*oneminusc+c;
		float f1 = xy*oneminusc+zs;
		float f2 = xz*oneminusc-ys;
		// n[3] not used
		float f4 = xy*oneminusc-zs;
		float f5 = axis.y*axis.y*oneminusc+c;
		float f6 = yz*oneminusc+xs;
		// n[7] not used
		float f8 = xz*oneminusc+ys;
		float f9 = yz*oneminusc-xs;
		float f10 = axis.z*axis.z*oneminusc+c;

		/* m[12] to m[15] are not changed by a rotate */
		dest.m00 = m00 * f0 + m10 * f1 + m20 * f2;
		dest.m01 = m01 * f0 + m11 * f1 + m21 * f2;
		dest.m02 = m02 * f0 + m12 * f1 + m22 * f2;
		dest.m03 = m03 * f0 + m13 * f1 + m23 * f2;
		dest.m10 = m00 * f4 + m10 * f5 + m20 * f6;
		dest.m11 = m01 * f4 + m11 * f5 + m21 * f6;
		dest.m12 = m02 * f4 + m12 * f5 + m22 * f6;
		dest.m13 = m03 * f4 + m13 * f5 + m23 * f6;
		dest.m20 = m00 * f8 + m10 * f9 + m20 * f10;
		dest.m21 = m01 * f8 + m11 * f9 + m21 * f10;
		dest.m22 = m02 * f8 + m12 * f9 + m22 * f10;
		dest.m23 = m03 * f8 + m13 * f9 + m23 * f10;
                return dest;
	}

	/**
	 * Translate this matrix and stash the result in another matrix
	 * @param vec The vector to translate by
	 * @param dest The destination matrix or null if a new matrix is to be created
	 * @return the translated matrix
	 */
	public Matrix4f translate(Vector3f vec, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();
		else if (dest == this)
			return translate(vec);
		
		dest.m30 += m00 * vec.x + m10 * vec.y + m20 * vec.z;
		dest.m31 += m01 * vec.x + m11 * vec.y + m21 * vec.z;
		dest.m32 += m02 * vec.x + m12 * vec.y + m22 * vec.z;
		dest.m33 += m03 * vec.x + m13 * vec.y + m23 * vec.z;
		
		return dest;
	}
	
	/**
	 * Translate this matrix and stash the result in another matrix
	 * @param vec The vector to translate by
	 * @param dest The destination matrix or null if a new matrix is to be created
	 * @return the translated matrix
	 */
	public Matrix4f translate(Vector2f vec, Matrix4f dest) {
		if (dest == null)
			dest = new Matrix4f();
		else if (dest == this)
			return translate(vec);
		
		dest.m30 += m00 * vec.x + m10 * vec.y;
		dest.m31 += m01 * vec.x + m11 * vec.y;
		dest.m32 += m02 * vec.x + m12 * vec.y;
		dest.m33 += m03 * vec.x + m13 * vec.y;
		
		return dest;
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
         * Calculate the determinant of a 3x3 matrix
         * @return result
         */

        private float determinant3x3(float t00, float t01, float t02,
                                    float t10, float t11, float t12,
                                    float t20, float t21, float t22)
        {
                    return    t00 * (t11 * t22 - t12 * t21) 
                            + t01 * (t12 * t20 - t10 * t22)
                            + t02 * (t10 * t21 - t11 * t20);
        }

	/**
	 * Invert this matrix
	 * @return this
	 */
	public Matrix invert() {
		
                float determinant = determinant();
                
                if (determinant != 0)
                {
                    /*
                        m00 m01 m02 m03
                        m10 m11 m12 m13
                        m20 m21 m22 m23
                        m30 m31 m32 m33    
                    */
                    float determinant_inv = 1f/determinant;
                    
                    // first row
                    float t00 = determinant3x3(	m11, m12, m13, m21, m22, m23, m31, m32, m33 );
                    float t01 = -determinant3x3(m10, m12, m13, m20, m22, m23, m30, m32, m33 );
                    float t02 = determinant3x3(	m10, m11, m13, m20, m21, m23, m30, m31, m33 );
                    float t03 = -determinant3x3(m10, m11, m12, m20, m21, m22, m30, m31, m32 );   
                    // second row
                    float t10 = -determinant3x3(m01, m02, m03, m21, m22, m23, m31, m32, m33 );
                    float t11 = determinant3x3( m00, m02, m03, m20, m22, m23, m30, m32, m33 );
                    float t12 = -determinant3x3(m00, m01, m03, m20, m21, m23, m30, m31, m33 );
                    float t13 = determinant3x3( m00, m01, m02, m20, m21, m22, m30, m31, m32 );  
                    // third row
                    float t20 = determinant3x3( m01, m02, m03, m11, m12, m13, m31, m32, m33 );
                    float t21 = -determinant3x3(m00, m02, m03, m10, m12, m13, m30, m32, m33 );
                    float t22 = determinant3x3( m00, m01, m03, m10, m11, m13, m30, m31, m33 );
                    float t23 = -determinant3x3(m00, m01, m02, m10, m11, m12, m30, m31, m32 );  
                    // fourth row
                    float t30 = -determinant3x3(m01, m02, m03, m11, m12, m13, m21, m22, m23 );
                    float t31 = determinant3x3( m00, m02, m03, m10, m12, m13, m20, m22, m23 );
                    float t32 = -determinant3x3(m00, m01, m03, m10, m11, m13, m20, m21, m23 );
                    float t33 = determinant3x3( m00, m01, m02, m10, m11, m12, m20, m21, m22 );  
        
                    // transpose and divide by the determinant
                    m00 = t00*determinant_inv;
                    m11 = t11*determinant_inv;
                    m22 = t22*determinant_inv;
                    m33 = t33*determinant_inv;
                    m01 = t10*determinant_inv;
                    m10 = t01*determinant_inv;
                    m20 = t02*determinant_inv;
                    m02 = t20*determinant_inv;
                    m12 = t21*determinant_inv;
                    m21 = t12*determinant_inv;
                    m03 = t30*determinant_inv;
                    m30 = t03*determinant_inv;
                    m13 = t31*determinant_inv;
                    m31 = t13*determinant_inv;
                    m32 = t23*determinant_inv;
                    m23 = t32*determinant_inv;
                }
		return this;
	}
	
	/**
	 * Negate this matrix
	 * @return this
	 */
	public Matrix negate() {
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
