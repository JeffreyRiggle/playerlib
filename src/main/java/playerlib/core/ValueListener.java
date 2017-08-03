package playerlib.core;

/**
 * 
 * @author Jeff Riggle
 *
 * @param <V> The type of value to listen on.
 */
public interface ValueListener<V> {
	/**
	 * 
	 * @param source The changed object.
	 * @param value The new value.
	 */
	<T> void changed(V source, T value);
}
