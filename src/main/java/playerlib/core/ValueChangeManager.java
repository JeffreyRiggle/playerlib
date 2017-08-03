package playerlib.core;

/**
 * 
 * @author Jeff Riggle
 *
 * @param <C> The type of value to watch.
 */
public interface ValueChangeManager<C> {
	/**
	 * This is used to allow a consumer to be notified when an @see C value is changed.
	 * 
	 * @param listener A @see ValueListener to be updated whenever this @see C value is updated.
	 */
	void addChangeListener(ValueListener<C> listener);
	/**
	 * This is used to stop a consumer from being notified when an @see C value is changed.
	 * 
	 * @param listener A @see ValueListener to be updated whenever this @see C value is updated.
	 */
	void removeChangeListener(ValueListener<C> listener);
}
