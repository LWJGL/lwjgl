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

	/*
	 * Unary matrix operations
	 */
	private static abstract class UnaryMatrixOperation {
		private final int enum;
		private final String name;
		
		UnaryMatrixOperation(int enum, String name) {
			this.enum = enum;
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
		
		abstract MatrixOpClassification classify();
		
	}

	/**
	 * A matrix operation is unsafe if the source and destination overlap,
	 * and either the strides are not equal, or destination > source, which
	 * would give an undefined result
	 */
	private static final MatrixOpClassification MATRIXOP_UNSAFE = new MatrixOpClassification() {
		protected MatrixOpClassification unsafe() { return this; }
		protected MatrixOpClassification safe() { return this; }
		protected MatrixOpClassification direct() { return this; }
		boolean isSafe() { return false; }

		void execute(
			int leftSourceAddress,
			int leftSourceStride,
			int leftElements,
			int leftSourceWidth,
			int leftSourceHeight,
			boolean transposeLeftSource,
			int rightSourceAddress,
			int rightSourceStride,
			int rightElements,
			int rightSourceWidth,
			int rightSourceHeight,
			boolean transposeRightSource,
			int destAddress,
			int destStride,
			boolean transposeDest)
		{
			throw new IllegalArgumentException("Unsafe matrix operation.");
		}

		void execute(
			int sourceAddress,
			int sourceStride,
			int numElements,
			int sourceWidth,
			int sourceHeight,
			boolean transposeSource,
			int destAddress,
			int destStride,
			boolean transposeDest)
		{
			throw new IllegalArgumentException("Unsafe matrix operation.");
		}
	};

	/** Straight copy */
	private static final class MatrixOpCopy extends UnaryMatrixOperation {
		
		MatrixOpCopy() {
			super(0, "copy");
		}
				
		MatrixOpClassification classify() {
			return MATRIXOP_NONE;
		}
		
		/**
		 * Unclassified (initial state) matrix operation.
		 */
		private final MatrixOpClassification MATRIXOP_NONE = new MatrixOpClassification() {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return false; }
		};
		
		/**
		 * A matrix operation is direct if the source and destination addresses
		 * are the same, and the strides are the same.
		 */
		private final class MatrixOpDirect extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return this; }
			protected MatrixOpClassification direct() { return this; }
			boolean isSafe() { return false; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpDirect MATRIXOP_DIRECT = new MatrixOpDirect();
	
		/**
		 * A matrix operation is safe if the source and destination do not
		 * overlap in any way
		 */
		private final class MatrixOpSafe extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpSafe MATRIXOP_SAFE = new MatrixOpSafe();
	};
	public static final MatrixOpCopy MATRIXOP_COPY = new MatrixOpCopy();

	/** Negate the vector */
	private static final class MatrixOpNegate extends UnaryMatrixOperation {
		
		MatrixOpNegate() {
			super(1, "negate");
		}
		
		MatrixOpClassification classify() {
			return MATRIXOP_NONE;
		}
		
		/**
		 * Unclassified (initial state) matrix operation.
		 */
		private final MatrixOpClassification MATRIXOP_NONE = new MatrixOpClassification() {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return false; }
		};
		
		/**
		 * A matrix operation is direct if the source and destination addresses
		 * are the same, and the strides are the same.
		 */
		private final class MatrixOpDirect extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return this; }
			protected MatrixOpClassification direct() { return this; }
			boolean isSafe() { return false; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpDirect MATRIXOP_DIRECT = new MatrixOpDirect();
	
		/**
		 * A matrix operation is safe if the source and destination do not
		 * overlap in any way
		 */
		private final class MatrixOpSafe extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpSafe MATRIXOP_SAFE = new MatrixOpSafe();
	};
	public static final MatrixOpNegate MATRIXOP_NEGATE = new MatrixOpNegate();

	/** Normalise the vector (set to length 1) */
	private static final class MatrixOpNormalise extends UnaryMatrixOperation {
		
		MatrixOpNormalise() {
			super(2, "normalise");
		}
		
		MatrixOpClassification classify() {
			return MATRIXOP_NONE;
		}
		
		/**
		 * Unclassified (initial state) matrix operation.
		 */
		private final MatrixOpClassification MATRIXOP_NONE = new MatrixOpClassification() {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
		};
		
		/**
		 * A matrix operation is direct if the source and destination addresses
		 * are the same, and the strides are the same.
		 */
		private final class MatrixOpDirect extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return this; }
			protected MatrixOpClassification direct() { return this; }
			boolean isSafe() { return false; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpDirect MATRIXOP_DIRECT = new MatrixOpDirect();
	
		/**
		 * A matrix operation is safe if the source and destination do not
		 * overlap in any way
		 */
		private final class MatrixOpSafe extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpSafe MATRIXOP_SAFE = new MatrixOpSafe();
	};
	public static final MatrixOpNormalise MATRIXOP_NORMALISE = new MatrixOpNormalise();

	/** Compute the inverse matrix */
	private static final class MatrixOpInvert extends UnaryMatrixOperation {
		
		MatrixOpInvert() {
			super(3, "invert");
		}
		
		MatrixOpClassification classify() {
			return MATRIXOP_NONE;
		}
		
		/**
		 * Unclassified (initial state) matrix operation.
		 */
		private final MatrixOpClassification MATRIXOP_NONE = new MatrixOpClassification() {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return false; }
		};
		
		/**
		 * A matrix operation is direct if the source and destination addresses
		 * are the same, and the strides are the same.
		 */
		private final class MatrixOpDirect extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return this; }
			protected MatrixOpClassification direct() { return this; }
			boolean isSafe() { return false; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpDirect MATRIXOP_DIRECT = new MatrixOpDirect();
	
		/**
		 * A matrix operation is safe if the source and destination do not
		 * overlap in any way
		 */
		private final class MatrixOpSafe extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
			native void execute(
				int sourceAddress,
				int sourceStride,
				int numElements,
				int sourceWidth,
				int sourceHeight,
				boolean transposeSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpSafe MATRIXOP_SAFE = new MatrixOpSafe();
	};
	public static final MatrixOpInvert MATRIXOP_INVERT = new MatrixOpInvert();

	/*
	 * Binary matrix operations
	 */
	private static abstract class BinaryMatrixOperation {
		private final int enum;
		private final String name;
		
		BinaryMatrixOperation(int enum, String name) {
			this.enum = enum;
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
		
		/**
		 * Check the compatibility of a binary matrix operation.
		 * @return the miniumum stride, in bytes
		 * @throws IllegalArgumentException if the source and destinations are not
		 * compatible
		 */
		abstract int checkCompatibility(
			int leftSourceWidth,
			int leftSourceHeight,
			boolean transposeLeftSource,
			int rightSourceWidth,
			int rightSourceHeight,
			boolean transposeRightSource,
			boolean transposeDest);
			

			
		abstract MatrixOpClassification classify();
		
	}


	/** Multiply left source by right source */
	private static final class MatrixOpMultiply extends BinaryMatrixOperation {
		
		MatrixOpMultiply() {
			super(0, "multiply");
		}
		
		int checkCompatibility(
			int leftSourceWidth,
			int leftSourceHeight,
			boolean transposeLeftSource,
			int rightSourceWidth,
			int rightSourceHeight,
			boolean transposeRightSource,
			boolean transposeDest)
		{
			// Left matrix width must be the same as right matrix height.
			int leftWidth = transposeLeftSource ? leftSourceHeight : leftSourceWidth;
			int rightHeight = transposeRightSource ? rightSourceWidth : rightSourceHeight;
			if (leftWidth != rightHeight)
				throw new IllegalArgumentException("Left and right matrices are not compatible.");
			int leftHeight = transposeLeftSource ? leftSourceWidth : leftSourceHeight;
			int rightWidth = transposeRightSource ? rightSourceHeight : rightSourceWidth;
			return (rightWidth * leftHeight) << 2;
		}
		MatrixOpClassification classify() {
			return MATRIXOP_NONE;
		}
		
		/**
		 * Unclassified (initial state) matrix operation.
		 */
		private final MatrixOpClassification MATRIXOP_NONE = new MatrixOpClassification() {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return false; }
		};
		
		/**
		 * A matrix operation is direct if the source and destination addresses
		 * are the same, and the strides are the same.
		 */
		private final class MatrixOpDirect extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return this; }
			protected MatrixOpClassification direct() { return this; }
			boolean isSafe() { return false; }
			native void execute(
				int leftSourceAddress,
				int leftSourceStride,
				int leftElements,
				int leftSourceWidth,
				int leftSourceHeight,
				boolean transposeLeftSource,
				int rightSourceAddress,
				int rightSourceStride,
				int rightElements,
				int rightSourceWidth,
				int rightSourceHeight,
				boolean transposeRightSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpDirect MATRIXOP_DIRECT = new MatrixOpDirect();
	
	
		/**
		 * A matrix operation is safe if the source and destination do not
		 * overlap in any way
		 */
		private final class MatrixOpSafe extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
			native void execute(
				int leftSourceAddress,
				int leftSourceStride,
				int leftElements,
				int leftSourceWidth,
				int leftSourceHeight,
				boolean transposeLeftSource,
				int rightSourceAddress,
				int rightSourceStride,
				int rightElements,
				int rightSourceWidth,
				int rightSourceHeight,
				boolean transposeRightSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpSafe MATRIXOP_SAFE = new MatrixOpSafe();
	};
	public static final MatrixOpMultiply MATRIXOP_MULTIPLY = new MatrixOpMultiply();

	/** Add right source to left source */
	private static final class MatrixOpAdd extends BinaryMatrixOperation {
		
		MatrixOpAdd() {
			super(1, "add");
		}
		
		int checkCompatibility(
			int leftSourceWidth,
			int leftSourceHeight,
			boolean transposeLeftSource,
			int rightSourceWidth,
			int rightSourceHeight,
			boolean transposeRightSource,
			boolean transposeDest)
		{
			if (transposeLeftSource == transposeRightSource) {
				// Left and right must be the same size
				if (leftSourceWidth != rightSourceWidth || leftSourceHeight != rightSourceHeight)
					throw new IllegalArgumentException("Left and right matrices are not the same size.");
			} else {
				// Left and right must be the same size but one of them is transposed
				if (leftSourceWidth != rightSourceHeight || leftSourceHeight != rightSourceWidth)
					throw new IllegalArgumentException("Left and right matrices are not the same size.");
			}
			return (leftSourceWidth * leftSourceHeight) << 2;
		}
		
		MatrixOpClassification classify() {
			return MATRIXOP_NONE;
		}
		
		/**
		 * Unclassified (initial state) matrix operation.
		 */
		private final MatrixOpClassification MATRIXOP_NONE = new MatrixOpClassification() {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return false; }
		};
		
		/**
		 * A matrix operation is direct if the source and destination addresses
		 * are the same, and the strides are the same.
		 */
		private final class MatrixOpDirect extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return this; }
			protected MatrixOpClassification direct() { return this; }
			boolean isSafe() { return false; }
			native void execute(
				int leftSourceAddress,
				int leftSourceStride,
				int leftElements,
				int leftSourceWidth,
				int leftSourceHeight,
				boolean transposeLeftSource,
				int rightSourceAddress,
				int rightSourceStride,
				int rightElements,
				int rightSourceWidth,
				int rightSourceHeight,
				boolean transposeRightSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpDirect MATRIXOP_DIRECT = new MatrixOpDirect();
	
	
		/**
		 * A matrix operation is safe if the source and destination do not
		 * overlap in any way
		 */
		private final class MatrixOpSafe extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
			native void execute(
				int leftSourceAddress,
				int leftSourceStride,
				int leftElements,
				int leftSourceWidth,
				int leftSourceHeight,
				boolean transposeLeftSource,
				int rightSourceAddress,
				int rightSourceStride,
				int rightElements,
				int rightSourceWidth,
				int rightSourceHeight,
				boolean transposeRightSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpSafe MATRIXOP_SAFE = new MatrixOpSafe();
	};
	public static final MatrixOpAdd MATRIXOP_ADD = new MatrixOpAdd();

	/** Subtract right source from left source */
	private static final class MatrixOpSubtract extends BinaryMatrixOperation {
		
		MatrixOpSubtract() {
			super(2, "subtract");
		}
		
		int checkCompatibility(
			int leftSourceWidth,
			int leftSourceHeight,
			boolean transposeLeftSource,
			int rightSourceWidth,
			int rightSourceHeight,
			boolean transposeRightSource,
			boolean transposeDest)
		{
			// Same as for add, obviously...
			return MATRIXOP_ADD.checkCompatibility(
				leftSourceWidth,
				leftSourceHeight,
				transposeLeftSource,
				rightSourceWidth,
				rightSourceHeight,
				transposeRightSource,
				transposeDest
			);
		}
		MatrixOpClassification classify() {
			return MATRIXOP_NONE;
		}
		
		/**
		 * Unclassified (initial state) matrix operation.
		 */
		private final MatrixOpClassification MATRIXOP_NONE = new MatrixOpClassification() {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return false; }
		};
		
		/**
		 * A matrix operation is direct if the source and destination addresses
		 * are the same, and the strides are the same.
		 */
		private final class MatrixOpDirect extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return this; }
			protected MatrixOpClassification direct() { return this; }
			boolean isSafe() { return false; }
			native void execute(
				int leftSourceAddress,
				int leftSourceStride,
				int leftElements,
				int leftSourceWidth,
				int leftSourceHeight,
				boolean transposeLeftSource,
				int rightSourceAddress,
				int rightSourceStride,
				int rightElements,
				int rightSourceWidth,
				int rightSourceHeight,
				boolean transposeRightSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpDirect MATRIXOP_DIRECT = new MatrixOpDirect();
	
	
		/**
		 * A matrix operation is safe if the source and destination do not
		 * overlap in any way
		 */
		private final class MatrixOpSafe extends MatrixOpClassification {
			protected MatrixOpClassification unsafe() { return MATRIXOP_UNSAFE; }
			protected MatrixOpClassification safe() { return MATRIXOP_SAFE; }
			protected MatrixOpClassification direct() { return MATRIXOP_DIRECT; }
			boolean isSafe() { return true; }
			native void execute(
				int leftSourceAddress,
				int leftSourceStride,
				int leftElements,
				int leftSourceWidth,
				int leftSourceHeight,
				boolean transposeLeftSource,
				int rightSourceAddress,
				int rightSourceStride,
				int rightElements,
				int rightSourceWidth,
				int rightSourceHeight,
				boolean transposeRightSource,
				int destAddress,
				int destStride,
				boolean transposeDest);
		};
		private final MatrixOpSafe MATRIXOP_SAFE = new MatrixOpSafe();
	};
	public static final MatrixOpSubtract MATRIXOP_SUBTRACT = new MatrixOpSubtract();

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
	
	/**
	 * Return the square root of a value
	 * @param n the number for which you want the square root
	 * @return sqrt(n)
	 */
	public static float sqrt(float n) {
		return (float) java.lang.Math.sqrt(n);
	}

	/*
	 * Matrix operation classifications
	 */
	private static abstract class MatrixOpClassification {
		
		abstract MatrixOpClassification unsafe();
		abstract MatrixOpClassification safe();
		abstract MatrixOpClassification direct();
		/**
		 * Execute a unary matrix operation.
		 * The default implementation does nothing.
		 */
		void execute(
			int sourceAddress,
			int sourceStride,
			int numElements,
			int sourceWidth,
			int sourceHeight,
			boolean transposeSource,
			int destAddress,
			int destStride,
			boolean transposeDest) {
		}
		
		/**
		 * Execute a binary matrix operation.
		 * The default implementation does nothing.
		 */
		void execute(
			int leftSourceAddress,
			int leftSourceStride,
			int leftElements,
			int leftSourceWidth,
			int leftSourceHeight,
			boolean transposeLeftSource,
			int rightSourceAddress,
			int rightSourceStride,
			int rightElements,
			int rightSourceWidth,
			int rightSourceHeight,
			boolean transposeRightSource,
			int destAddress,
			int destStride,
			boolean transposeDest) {
		}
		
		/**
		 * Check a binary operation to make sure that when dealing with n x n
		 * result sets that when both n's are greater than 1 the operation must
		 * be safe; otherwise if is direct, then the side where n > 1 must be
		 * the destination.
		 */
		MatrixOpClassification checkBinaryOp(
			int leftSourceElements,
			int rightSourceElements,
			int leftSourceAddress,
			int rightSourceAddress,
			int destinationAddress
		) {
			
			if (leftSourceElements > 1 && rightSourceElements > 1) {
				if (isSafe())
					return this;
				else
					return MATRIXOP_UNSAFE;
			} else if (leftSourceElements == 1 && rightSourceElements > 1) {
				if (isSafe())
					return this;
				else if (destinationAddress == rightSourceAddress)
					return this;
				else
					return MATRIXOP_UNSAFE;
			} else if (rightSourceElements == 1 && leftSourceElements > 1) {
				if (isSafe())
					return this;
				else if (destinationAddress == leftSourceAddress)
					return this;
				else
					return MATRIXOP_UNSAFE;
			} else {
				return MATRIXOP_UNSAFE;
			}
		}
		
		/**
		 * @return true if this is a safe classification
		 */
		abstract boolean isSafe();		
		
		public final MatrixOpClassification check(
			int sourceAddress,
			int sourceStride,
			int numElements,
			int destAddress,
			int destStride) 
		{
			int sourceEnd = sourceAddress + sourceStride * numElements;
			int destEnd = destAddress + destStride * numElements;
			// Check to see if source is somewhere inside the destination
			if ((sourceAddress >= destAddress && sourceAddress <= destEnd)
				|| (sourceEnd >= destAddress && sourceEnd <= destEnd)) {
	
				// Check out the strides first
				if (destAddress > sourceAddress || sourceStride != destStride)
					return unsafe();
				else if (destAddress < sourceAddress)
					return safe();
				else
					return direct();
	
			} else {
	
				// Completely safe
				return safe();
	
			}
		}
	}
	

	
	/**
	 * Apply a unary matrix operation to an array of matrices.
	 * @param operation A unary vector operation which must be one of
	 * MATRIXOP_NOOP,
	 * MATRIXOP_NEGATE,
	 * MATRIXOP_NORMALIZE, etc.
	 * @param sourceAddress The address of the source matrices
	 * @param sourceStride The distance between source matrices, in bytes; if 0,
	 * it is assumed that the matrices are tightly packed. This is equivalent to
	 * sourceStride == sourceWidth * sourceHeight * 4
	 * @param numElements The number of matrices
	 * @param sourceWidth The width of the source matrix. Must be > 0.
	 * @param sourceHeight The height of the source matrix. Must be > 0.
	 * @param transposeSource The source can be transposed automatically before
	 * the operation is performed on it
	 * @param destAddress The results are placed here. This may overlap the
	 * source and can even be the same, which performs the unary operation
	 * in-situ
	 * @param destStride The distance between destination matrices, in bytes,
	 * similar to sourceStride. Note that if the source and destination address
	 * overlap and the strides are different then the result is undefined.
	 * @param transposeDest The destination can be transposed before being
	 * written
	 */
	public static void matrixOp(
		UnaryMatrixOperation operation,
		int sourceAddress,
		int sourceStride,
		int numElements,
		int sourceWidth,
		int sourceHeight,
		boolean transposeSource,
		int destAddress,
		int destStride,
		boolean transposeDest) {

		// Check that certain parameters are legal in the first place
		assert sourceAddress != 0 : "0 source address";
		assert sourceStride >= 0 : "Illegal source stride";
		assert numElements >= 0 : "Illegal number of elements";
		assert sourceWidth >= 1 : "Illegal source width";
		assert sourceHeight >= 1 : "Illegal source height";
		assert destAddress != 0 : "0 dest address";
		assert destStride >= 0 : "Illegal dest stride";

		// Calculate actual strides
		if (sourceStride == 0)
			sourceStride = sourceWidth * sourceHeight;
		if (destStride == 0)
			destStride = sourceStride;

		// Check unary matrix operation type
		MatrixOpClassification op = operation.classify().check(sourceAddress, sourceStride, numElements, destAddress, destStride);

		op.execute(
			sourceAddress,
			sourceStride,
			numElements,
			sourceWidth,
			sourceHeight,
			transposeSource,
			destAddress,
			destStride,
			transposeDest
		);
		
		
	}
	
	/**
	 * Apply a binary matrix operation to two arrays of matrices. The results
	 * are computed by applying the first right element to each of the left
	 * elements in turn; then the second right element to each left element;
	 * and so on.
	 * @param operation A binary vector operation which must be one of
	 * MATRIXOP_MUTLIPLY,
	 * MATRIXOP_ADD,
	 * MATRIXOP_SUBTRACT, etc.
	 * @param leftSourceAddress The address of the source matrices
	 * @param leftSourceStride The distance between source matrices, in bytes; if 0,
	 * it is assumed that the matrices are tightly packed. This is equivalent to
	 * sourceStride == sourceWidth * sourceHeight * 4
	 * @param leftElements The number of left-hand-side matrices
	 * @param leftSourceWidth The width of the left source matrix. Must be > 0.
	 * @param leftSourceHeight The height of the left source matrix. Must be > 0.
	 * @param transposeLeftSource The left source can be transposed automatically before
	 * the operation is performed on it
	 * @param rightSourceAddress The address of the right source matrices
	 * @param rightSourceStride The distance between right source matrices, in bytes; if 0,
	 * it is assumed that the matrices are tightly packed. This is equivalent to
	 * sourceStride == sourceWidth * sourceHeight * 4
	 * @param rightElements The number of right-hand-side matrices
	 * @param rightSourceWidth The width of the right source matrix. Must be > 0.
	 * @param rightSourceHeight The height of the right source matrix. Must be > 0.
	 * @param transposeRightSource The right source can be transposed automatically before
	 * the operation is performed on it
	 * @param destAddress The results are placed here. This may overlap the
	 * sources and can even be the same, which performs the binary operation
	 * in-situ
	 * @param destStride The distance between destination matrices, in bytes,
	 * similar to sourceStride. Note that if the source and destination address
	 * overlap and the strides are different then the result is undefined.
	 * @param transposeDest The destination can be transposed before being
	 * written
	 * @throws IllegalArgumentException if the source matrices are incompatible
	 */
	public static void matrixOp(
		BinaryMatrixOperation operation,
		int leftSourceAddress,
		int leftSourceStride,
		int leftElements,
		int leftSourceWidth,
		int leftSourceHeight,
		boolean transposeLeftSource,
		int rightSourceAddress,
		int rightSourceStride,
		int rightElements,
		int rightSourceWidth,
		int rightSourceHeight,
		boolean transposeRightSource,
		int destAddress,
		int destStride,
		boolean transposeDest) {
			
		// Check that certain parameters are legal in the first place
		assert leftSourceAddress != 0 : "0 left source address";
		assert leftSourceStride >= 0 : "Illegal left source stride";
		assert leftElements >= 0 : "Illegal number of left elements";
		assert leftSourceWidth >= 1 : "Illegal left source width";
		assert leftSourceHeight >= 1 : "Illegal left source height";
		assert rightSourceAddress != 0 : "0 right source address";
		assert rightSourceStride >= 0 : "Illegal right source stride";
		assert rightElements >= 0 : "Illegal number of right elements";
		assert rightSourceWidth >= 1 : "Illegal right source width";
		assert rightSourceHeight >= 1 : "Illegal right source height";
		assert destAddress != 0 : "0 dest address";
		assert destStride >= 0 : "Illegal dest stride";

		// Calculate actual strides
		if (leftSourceStride == 0)
			leftSourceStride = leftSourceWidth * leftSourceHeight;
		if (rightSourceStride == 0)
			rightSourceStride = rightSourceWidth * rightSourceHeight;

		// Ensure the source and destination matrices are compatible
		int minStride = operation.checkCompatibility(
			leftSourceWidth,
			leftSourceHeight,
			transposeLeftSource,
			rightSourceWidth,
			rightSourceWidth,
			transposeRightSource,
			transposeDest);

		if (destStride == 0)
			destStride = minStride;
		
		// Check unary matrix operation type
		MatrixOpClassification op = operation.classify().check(leftSourceAddress, leftSourceStride, leftElements, destAddress, destStride);
		op = op.check(rightSourceAddress, rightSourceStride, rightElements, destAddress, destStride);
		op = op.checkBinaryOp(leftElements, rightElements, leftSourceAddress, rightSourceAddress, destAddress);
		op.execute(
			leftSourceAddress,
			leftSourceStride,
			leftElements,
			leftSourceWidth,
			leftSourceHeight,
			transposeLeftSource,
			rightSourceAddress,
			rightSourceStride,
			rightElements,
			rightSourceWidth,
			rightSourceHeight,
			transposeRightSource,
			destAddress,
			destStride,
			transposeDest
		);

	}

}
