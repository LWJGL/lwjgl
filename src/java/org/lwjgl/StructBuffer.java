/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * StructBuffer.java Created on Aug 10, 2002 by foo
 */
package org.lwjgl;

import java.nio.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * A StructBuffer is an abstract class for wrapping arrays of C-structures.
 * To use this class you should derive a class with methods to get individual
 * data out of arbitrary locations in the buffer.
 * 
 * The StructBuffer supports several simultaneously available datatypes which
 * are implemented by having one underlying native byte buffer over which
 * several views in different basic types are available.
 * 
 * Longs and doubles are not implemented by default because of their limited
 * usefulness in games.
 * 
 * Note that because StructBuffer uses a fairly hefty amount of storage and
 * setup overhead it should be used to store large and/or persistent allocations
 * of data.
 * 
 * @author foo
 */
public abstract class StructBuffer {

	/** The native address of the buffer */
	private final int address;
	
	/** The stride of the underlying elements */
	private final int stride;
	
	// Precalculated strides
	private final int strideBy2;
	private final int strideBy4;
	
	/** The current element position */
	private int position;
	
	/** The number of elements */
	private final int capacity;
	
	/** The current limit */
	private int limit;
	
	/** The current mark (-1 if no mark is defined) */
	private int mark = -1;
	
	/** The underlying native byte buffer */
	protected final ByteBuffer bytes;

	// Various views of the bytes
	protected final IntBuffer ints;
	protected final ShortBuffer shorts;
	protected final FloatBuffer floats;
	protected final CharBuffer chars;

	/**
	 * Constructor for StructBuffer using an existing byte buffer.
	 * 
	 * @param bytes An existing direct byte buffer, which must be in native endian order
	 * @param stride The distance between the starts of each element, in bytes
	 * @throws AssertionError if the byte buffer is not native-endian, or not direct 
	 * @throws IllegalArgumentException if the stride is less than or equal to 0.
	 * @throws NullPointerException if the incoming byte buffer is null
	 */
	public StructBuffer(ByteBuffer bytes, int stride) {
		
		assert bytes.order() == ByteOrder.nativeOrder() 
			: "Incoming byte buffer is not native-endian.";
		assert bytes.isDirect()
			: "Incoming byte buffer is not direct.";

		this.bytes = bytes;
		floats = bytes.asFloatBuffer();
		ints = bytes.asIntBuffer();
		shorts = bytes.asShortBuffer();
		chars = bytes.asCharBuffer();
		
		if (stride <= 0)
			throw new IllegalArgumentException("The stride must be > 0");
		else
			this.stride = stride;
			
		strideBy2 = stride / 2;
		strideBy4 = stride / 4;
			
		capacity = bytes.capacity() / stride;
		limit = capacity;
		address = Sys.getDirectBufferAddress(bytes);

	}
	
	/**
	 * Constructor for StructBuffer which takes an address and size, and which
	 * allocates a direct byte buffer at this location and size. In addition a
	 * stride is specified which gives the distance between the starts of each
	 * structure element. For example, the C-struct
	 * <code>
	 * struct vector {
	 * float x, y, z;
	 * } VECTOR_T;
	 * </code>
	 * has a stride of 12; but it may also have a stride of 16 to allow more
	 * efficient memory access.
	 * 
	 * <strong>Warning:</strong> No attempt is made to ensure that you are actually
	 * allowed to reference this memory or that the size is correct.
	 * 
	 * The number of bytes that will be referenced is size * stride.
	 * 
	 * @param address The address to allocate at
	 * @param capacity The number of elements to allocate
	 * @param stride The distance between the starts of each element, in bytes
	 * @throws AssertionError if the address is zero, the stride is &lt;=0, or
	 * the size is &lt;=0
	 */
	public StructBuffer(int address, int capacity, int stride) {
		assert address != 0 : "Illegal address";
		assert capacity > 0 : "Capacity must be > 0";
		assert stride > 0 : "Stride must be > 0";
		
		this.address = address;
		this.capacity = capacity;
		this.stride = stride;
		strideBy2 = stride / 2;
		strideBy4 = stride / 4;
		
		bytes = Sys.createDirectBuffer(address, capacity * stride);
		floats = bytes.asFloatBuffer();
		ints = bytes.asIntBuffer();
		shorts = bytes.asShortBuffer();
		chars = bytes.asCharBuffer();
		limit = capacity;
	}
	
	/**
	 * Sets this buffer's position. If the mark is defined and larger than the
	 * new position then it is discarded.
	 * @param newPosition The new position value; must be non-negative and no
	 * larger than the current limit
	 * @return this
	 * @throws IllegalArgumentException If the preconditions on newPosition do
	 * not hold
	 */
	public final int position(int newPosition) {
		if (newPosition != position) {
			if (newPosition < 0 || newPosition > limit)
				throw new IllegalArgumentException("Position "+newPosition+" not valid");
			if (mark > position)
				mark = -1;
			position = newPosition;
			bytes.position(newPosition * stride);
			chars.position(newPosition * strideBy2);
			shorts.position(newPosition * strideBy2);
			ints.position(newPosition * strideBy4);
			floats.position(newPosition * strideBy4);
		}		
		return position;
	}
	
	/**
	 * @return the current position
	 */
	public final int position() {
		return position;
	}
	
	/**
	 * @return the capacity
	 */
	public final int capacity() {
		return capacity;
	}
	
	/**
	 * Sets this buffer's limit. If the position is larger than the new limit
	 * then it is set to the new limit. If the mark is defined and larger than
	 * the new limit then it is discarded.
	 * 
	 * @param newLimit The new limit value; must be non-negative and no larger
	 * than this buffer's capacity
	 * @return this
	 * @throws If the preconditions on newLimit do not hold
	 */
	public final StructBuffer limit(int newLimit) {
		if (newLimit != limit) {
			if (newLimit < 0 || newLimit > capacity)
				throw new IllegalArgumentException("Illegal new limit "+newLimit);
			if (position > newLimit)
				position = newLimit;
			if (mark > newLimit)
				mark = -1;
			newLimit = limit;
		}
		
		return this;
			
	}
	
	/**
	 * @return the current limit
	 */
	public final int limit() {
		return limit;
	}
	
	/**
	 * Mark the current position.
	 * @return this
	 */
	public final StructBuffer mark() {
		mark = position;
		return this;
	}

	/**
	 * Resets this buffer's position to the previously-marked position.
	 * Invoking this method neither changes nor discards the mark's value.
	 * 
	 * @return this
	 * @throws InvalidMarkException if the mark has not been defined
	 */
	public final StructBuffer reset() {
		if (mark == -1)
			throw new InvalidMarkException();
		position = mark;
		return this;
	}
	
	/**
	 * Clears this buffer. The position is set to zero, the limit is set to the
	 * capacity, and the mark is discarded.
	 * 
	 * Invoke this method before using a sequence of channel-read or put
	 * operations to fill this buffer. This method does not actually erase the
	 * data in the buffer, but it is named as if it did because it will most
	 * often be used in situations in which that might as well be the case.
	 *
	 * @return this
	 */
	public final StructBuffer clear() {
		position = 0;
		limit = capacity;
		mark = -1;
		return this;
	}
	
	/**
	 * Flips this buffer. The limit is set to the current position and then the
	 * position is set to zero. If the mark is defined then it is discarded.
	 * 
	 * After a sequence of channel-read or put operations, invoke this method to
	 * prepare for a sequence of channel-write or relative get operations.
	 * 
	 * @return this
	 */
	public final StructBuffer flip() {
		limit = position;
		position = 0;
		mark = -1;
		return this;
	}
	
	/**
	 * Rewinds this buffer. The position is set to zero and the mark is
	 * discarded.
	 * 
	 * Invoke this method before using a sequence of get operations to read the
	 * content of this buffer, assuming that the limit has already been set
	 * appropriately.
	 * 
	 * @return this
	 */
	public final StructBuffer rewind() {
		position = 0;
		mark = -1;
		return this;
	}

	/**
	 * @return the number of elements between the current position and the limit.
	 */
	public final int remaining() {
		return limit - position;
	}
	
	/**
	 * @return true if there are any elements between the current position and
	 * the limit (ie. remaining() &gt; 0)
	 */
	public final boolean hasRemaining() {
		return remaining() > 0;
	}
	
	/**
	 * Determine whether this buffer is read-only. This method is not abstract
	 * unlike the java.nio.Buffer version; by default we'll get the read-only
	 * status out of the bytes buffer. However this method can be overridden
	 * to force a StructBuffer to be read-only (or not).
	 * 
	 * @return true if this buffer is read-only
	 */
	public boolean isReadOnly() {
		return bytes.isReadOnly();
	}
	
	/**
	 * @return a String representation of the buffer
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(32);
		
		sb.append("StructBuffer[capacity=");
		sb.append(capacity);
		sb.append(", position=");
		sb.append(position);
		sb.append(", limit=");
		sb.append(limit);
		sb.append(", mark=");
		sb.append(mark);
		sb.append(']');
		
		return sb.toString();
	}
	
	/**
	 * @return a hashcode
	 */
	public int hashCode() {
		return address;
	}
	
	/**
	 * @return the address used by this StructBuffer
	 */
	public final int getAddress() {
		return address;
	}
}
