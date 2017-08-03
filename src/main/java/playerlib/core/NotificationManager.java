package playerlib.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Jeff Riggle
 *
 * @param <V> The type of value to manage notifications for.
 */
public class NotificationManager<V extends ValueChangeManager<V>> implements ValueListener<V> {

	private List<ChangeListener<V>> listeners;
	
	/**
	 * Creates a notification manager with no change listeners.
	 */
	public NotificationManager() {
		listeners = new ArrayList<ChangeListener<V>>();
	}

	/**
	 * 
	 * @param listener The listener to add to this manager.
	 */
	public void addListener(ChangeListener<V> listener) {
		listeners.add(listener);
	}
	
	/**
	 * 
	 * @param listener The listener to remove from this manager.
	 */
	public void removeListener(ChangeListener<V> listener) {
		listeners.remove(listener);
	}
	
	/**
	 * 
	 * @param added The added items to notify with.
	 * @param removed The removed items to notify with.
	 * @param changed The changed items to notify with.
	 */
	public void notifyListeners(List<V> added, List<V> removed, List<V> changed) {
		addChangeListeners(added);
		removeChangeListeners(removed);
		
		for (ChangeListener<V> listener : listeners) {
			listener.changed(added, removed, changed);
		}
	}
	
	/**
	 * 
	 * @param added The added items to notify with.
	 */
	public void notifyAdd(List<V> added) {
		notifyListeners(added, new ArrayList<V>(), new ArrayList<V>());
	}
	
	/**
	 * 
	 * @param removed The removed items to notify with.
	 */
	public void notifyRemoved(List<V> removed) {
		notifyListeners(new ArrayList<V>(), removed, new ArrayList<V>());
	}
	
	/**
	 * 
	 * @param changed The changed items to notify with.
	 */
	public void notifyChanged(List<V> changed) {
		notifyListeners(new ArrayList<V>(), new ArrayList<V>(), changed);
	}
	
	private void addChangeListeners(List<V> added) {
		for (V value : added) {
			value.addChangeListener(this);
		}
	}
	
	private void removeChangeListeners(List<V> removed) {
		for (V value : removed) {
			value.removeChangeListener(this);
		}
	}
	
	@Override
	public <T> void changed(V source, T value) {
		notifyListeners(new ArrayList<V>(), new ArrayList<V>(), new ArrayList<V>(Arrays.asList(source)));
	}
}
