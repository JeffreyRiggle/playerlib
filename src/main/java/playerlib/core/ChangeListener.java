package playerlib.core;

import java.util.List;

/**
 * 
 * @author Jeff Riggle
 *
 * @param <T> The type of object that can be changed.
 */
public interface ChangeListener<T> {
	/**
	 * 
	 * @param added The added values.
	 * @param removed The removed values.
	 * @param changed The changed values.
	 */
	void changed(List<T> added, List<T> removed, List<T> changed);
}
