/*
 * Copyright 2002-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.lwjgl.opengl;

import java.util.Iterator;

/**
 * A hash map using primitive ints as keys rather than objects.
 *
 * @author Justin Couch
 * @author Alex Chaffee (alex@apache.org)
 * @author Stephen Colebourne
 * @author Nathan Sweet
 */
final class FastIntMap<V> implements Iterable<FastIntMap.Entry<V>> {
	/**
	 * Hash table to store and retrieve Entry buckets in constant time.
	 */
	private Entry[] table;
	/**
	 * Counter that keeps track of the number of entries in the FastIntMap.
	 */
	private int size;
	/**
	 * Bitwise AND mask used to hash keys (used to efficiently compute "key % capacity").
	 */
	private int mask;
	/**
	 * Maximum number of buckets to be stored in the hash table. 
	 */
	private int capacity;
	/**
	 * Number of entries to be stored before the hash table is expanded.
	 */
	private int threshold;


	/**
	 * Constructs an empty FastIntMap with the default initialCapacity (16) and load factor (0.75f).
	 */
	FastIntMap() {
		this(16, 0.75f);
	}

	/**
	 * Constructs an empty FastIntMap with the specified initialCapacity and the default load factor (0.75f).
	 */
	FastIntMap(int initialCapacity) {
		this(initialCapacity, 0.75f);
	}

	/**
	 * Constructs an empty FastIntMap with an initial capacity for 2^initialCapacity keys and the specified load factor.
	 *
	 * @param initialCapacity Initial capacity of the FastIntMap (space for 2^initialCapcity keys is allocated).
	 * @param loadFactor Ratio of stored keys to the number of available keys that will trigger a capacity expansion.  This
	 *                   value should fall within the range of (0, 1].
	 *
	 * @throws IllegalArgumentException At least one of the argument values is invalid.
	 */
	FastIntMap(int initialCapacity, float loadFactor) {
		// Check for invalid parameter values.
		if ( initialCapacity > 1 << 30 ) throw new IllegalArgumentException("initialCapacity is too large.");
		if ( initialCapacity < 0 ) throw new IllegalArgumentException("initialCapacity must be greater than zero.");
		if ( loadFactor <= 0 ) throw new IllegalArgumentException("loadFactor must be greater than zero.");

		// Set the capacity to 2^initialCapacity.
		capacity = 1;
		while ( capacity < initialCapacity )
			capacity <<= 1;

		this.threshold = (int)(capacity * loadFactor);
		this.table = new Entry[capacity];
		// Ignore bits greater than the largest hash table index 
		this.mask = capacity - 1;
	}

	/**
	 * Returns the index of the specified key.
	 * 
	 * @param key Key to be indexed.
	 * 
	 * @return The index of the key.
	 */
	private int index(final int key) {
		return index(key, mask);
	}

	/**
	 * Returns the index of the specified key using the given mask.
	 * 
	 * @param key  Key to be indexed.
	 * @param mask Mask to be applied.
	 * 
	 * @return The index of the key.
	 */
	private static int index(final int key, final int mask) {
		// Efficiently computes key % capacity, where the capacity is implied by the mask.
		return key & mask;
	}

	/**
	 * Associates the given key with the specified value in the FastIntMap.
	 * 
	 * @param key Key to be inserted.
	 * @param value Value to be associated with the key.
	 * 
	 * @return The value previously associated with the key.  If the key did not previously exist, null is returned.
	 */
	public V put(int key, V value) {
		final Entry<V>[] table = this.table;
		int index = index(key);

		// Check if the key already exists.
		for ( Entry<V> e = table[index]; e != null; e = e.next ) {
			if ( e.key != key ) continue;
			// Found the key.
			V oldValue = e.value;
			e.value = value;
			return oldValue;
		}

		// Insert the key into the bucket and increase the hash table capacity if necessary.
		table[index] = new Entry<V>(key, value, table[index]);

		if ( size++ >= threshold )
			rehash(table);

		// No previous Entry for the key was found.
		return null;
	}

	/**
	 * Rehashes all the entries in the FastIntMap and expands its capacity. 
	 * 
	 * @param table Table to be rehashed.
	 */
	private void rehash(final Entry<V>[] table) {
		// The capacity of the table must remain a power of 2 for the mask to be trivially adjusted.  
		final int newCapacity = 2 * capacity;
		final int newMask = newCapacity - 1;

		final Entry<V>[] newTable = new Entry[newCapacity];

		// Rehash all table entries that correspond to a non-null value.
		for ( int i = 0, index; i < table.length; i++ ) {
			Entry<V> e = table[i];
			if ( e == null ) continue;
			// Rehash all elements in the current bucket.
			do {
				final Entry<V> next = e.next;
				index = index(e.key, newMask);
				e.next = newTable[index];
				newTable[index] = e;
				e = next;
			} while ( e != null );
		}

		this.table = newTable;
		capacity = newCapacity;
		mask = newMask;
		// Adjust threshold to match new capacity.
		threshold *= 2;
	}

	/**
	 * Retrieves the value associated with the specified key.
	 * 
	 * @param key Key whose associated value should be returned. 
	 * 
	 * @return The value associated with the key.  If the key does not exist, null is returned.
	 */
	public V get(int key) {
		// Search the bucket corresponding to the key's index.
		final int index = index(key);
		for ( Entry<V> e = table[index]; e != null; e = e.next )
			if ( e.key == key ) return e.value;
		// No Entry for the key was found.
		return null;
	}

	/**
	 * Determines whether the specified value exists in the FastIntMap.
	 * 
	 * @param value Value whose presence should be tested.
	 * 
	 * @return True if the value is present and false otherwise.
	 */
	public boolean containsValue(Object value) {
		final Entry<V>[] table = this.table;
		// Every bucket is a candidate for storing the value. 
		for ( int i = table.length - 1; i >= 0; i-- )
			for ( Entry<V> e = table[i]; e != null; e = e.next )
				if ( e.value.equals(value) ) return true;
		// No Entry for the value was found.
		return false;
	}

	/**
	 * Determines whether the specified key exists in the FastIntMap.
	 * 
	 * @param key Key whose presence should be tested.
	 * 
	 * @return True if the key is present and false otherwise.
	 */
	public boolean containsKey(int key) {
		// Search the bucket corresponding to the key's index.
		final int index = index(key);
		for ( Entry<V> e = table[index]; e != null; e = e.next )
			if ( e.key == key ) return true;
		// No Entry for the key was found.
		return false;
	}

	/**
	 * Removes the specified key from the FastIntMap.
	 * 
	 * @param key Key to be removed.
	 * @return The value associated with the key.  If the key does not exist, null is returned.
	 */
	public V remove(int key) {
		final int index = index(key);

		// Remove the key by setting the previous Entry's "next" reference to the
		// Entry occurring after the key's Entry. Visually, 
		// ( ... -> E1 --> Ekey --> E2 --> ... ) becomes ( ... --> E1 --> E2 --> ... ).
		Entry<V> prev = table[index];
		Entry<V> e = prev;
		while ( e != null ) {
			Entry<V> next = e.next;
			if ( e.key == key ) {
				size--;
				// Key belongs to the first Entry in the bucket.
				if ( prev == e )
					table[index] = next;
				// Key belongs to a trailing Entry in the bucket.
				else
					prev.next = next;
				return e.value;
			}
			// Move previous and current Entry references to the next Entry in the bucket.
			prev = e;
			e = next;
		}
		// No Entry for the key was found.
		return null;
	}

	/**
	 * Returns the number of entries in the FastIntMap.
	 * 
	 * @return The number of entries.
	 */
	public int size() {
		return size;
	}

	/**
	 * Determines whether the FastIntMap is empty (contains no entries).
	 * 
	 * @return True if the FastIntMap is empty and false otherwise. 
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all entries from the FastIntMap.
	 */
	public void clear() {
		final Entry<V>[] table = this.table;
		for ( int index = table.length - 1; index >= 0; index-- )
			table[index] = null;
		size = 0;
	}

	/**
	 * Constructs a new EntryIterator object.
	 * 
	 * @return The constructed EntryIterator object.
	 */
	public EntryIterator iterator() {
		return new EntryIterator();
	}

	/**
	 * An iterator that traverses the entries of the FastIntMap.
	 */
	public class EntryIterator implements Iterator<Entry<V>> {
		/**
		 * Index of the bucket corresponding to the next Entry.
		 */
		private int nextIndex;
		/**
		 * Current Entry in the iteration.
		 */
		private Entry<V> current;

		/**
		 * Constructs a reset EntryIterator.
		 */
		EntryIterator() {
			reset();
		}

		/**
		 * Resets the current Entry reference and sets nextIndex to the first bucket in the FastIntMap with an Entry.
		 */
		public void reset() {
			current = null;
			final Entry<V>[] table = FastIntMap.this.table;
			int i;
			// Find the first bucket with an Entry.
			for ( i = table.length - 1; i >= 0; i-- )
				if ( table[i] != null ) break;
			nextIndex = i;
		}

		/**
		 * Determines if there are any entries in the FastIntMap that have not been traversed.
		 * 
		 * @return True if there are entries that have not been traversed and false otherwise.
		 */
		public boolean hasNext() {
			// Check if the next Entry belongs to a valid bucket.
			if ( nextIndex >= 0 ) return true;
			// Check if the current Entry exists and has a successor (next Entry).
			Entry e = current;
			return e != null && e.next != null;
		}

		/**
		 * Retrieves the next Entry in the iteration.  If the final Entry is already traversed,
		 * an ArrayIndexOutOfBoundsException will be thrown.
		 * 
		 * @return The next Entry in the FastIntMap.
		 */
		public Entry<V> next() {
			Entry<V> e = current;
			// Check if the next Entry belongs to the same bucket as the current Entry.
			if ( e != null ) {
				e = e.next;
				// Check if nextIndex needs to be updated.
				if ( e != null ) {
					current = e;
					return e;
				}
			}
			// Set the next Entry to the bucket at nextIndex.
			final Entry<V>[] table = FastIntMap.this.table;
			int i = nextIndex;
			e = current = table[i];
			// Find the next nextIndex.
			while ( --i >= 0 )
				if ( table[i] != null ) break;
			nextIndex = i;
			return e;
		}

		/**
		 * Removes the current iteration Entry from FastIntMap.
		 */
		public void remove() {
			FastIntMap.this.remove(current.key);
		}
	}

	/**
	 * A structure to represent an entry in the FastIntMap.  Composed of a key and value, as
	 * well as a reference to the next entry belonging to the same bucket.
	 */
	static final class Entry<T> {
		/**
		 * Key corresponding to this Entry.
		 */
		final int key;
		/**
		 * Value to be associated with the key in the FastIntMap.
		 */
		T value;
		/**
		 * Reference to the next Entry in the FastIntMap bucket.
		 */
		Entry<T> next;

		/**
		 * Constructs an Entry with the specified key, value, and Entry reference.
		 * 
		 * @param key Key corresponding to this Entry.
		 * @param value Value corresponding to this Entry.
		 * @param next Reference to the next Entry in the FastIntMap bucket.
		 */
		Entry(int key, T value, Entry<T> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		/**
		 * Returns the key corresponding to this Entry.
		 * 
		 * @return The key corresponding to this Entry.
		 */
		public int getKey() {
			return key;
		}

		/**
		 * Returns the value corresponding to this Entry.
		 * 
		 * @return The value corresponding to this Entry.
		 */
		public T getValue() {
			return value;
		}

	}

}