package playerlib.items;

import playerlib.core.ValueChangeManager;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IProperty extends ValueChangeManager<IProperty> {

	/**
	 * 
	 * @return The name of this property.
	 */
	String name();
	/**
	 * 
	 * @return The description associated with this property.
	 */
	String description();
	/**
	 * 
	 * @return The value for this property.
	 */
	<T> T value();
	/**
	 * 
	 * @param value The new name for this property.
	 */
	void name(String value);
	/**
	 * 
	 * @param value The new description for this property.
	 */
	void description(String value);
	/**
	 * 
	 * @param value The new value for this property.
	 */
	<T> void value(T value);
}