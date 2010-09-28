package org.lwjgl.opencl;

import org.lwjgl.LWJGLUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A CLObjectChild container.
 *
 * @author Spasi
 */
class CLObjectRegistry<T extends CLObjectChild> {

	private Map<Long, T> registry;

	CLObjectRegistry() {
	}

	final boolean isEmpty() {
		return registry == null || registry.isEmpty();
	}

	final T getObject(final long id) {
		return registry == null ? null : registry.get(id);
	}

	final boolean hasObject(final long id) {
		return registry != null && registry.containsKey(id);
	}

	final List<T> getAll() {
		return registry == null ? null : new ArrayList<T>(registry.values());
	}

	void registerObject(final T object) {
		final Map<Long, T> map = getMap();
		final Long key = object.getPointer();

		if ( LWJGLUtil.DEBUG && map.containsKey(key) )
			throw new IllegalStateException("Duplicate object found: " + object.getClass() + " - " + key);

		getMap().put(object.getPointer(), object);
	}

	void unregisterObject(final T object) {
		getMap().remove(object.getPointerUnsafe());
	}

	private Map<Long, T> getMap() {
		if ( registry == null )
			registry = new HashMap<Long, T>();

		return registry;
	}

}