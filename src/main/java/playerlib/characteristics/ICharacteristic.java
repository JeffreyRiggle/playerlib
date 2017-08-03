package playerlib.characteristics;

import playerlib.core.ValueChangeManager;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface ICharacteristic extends ValueChangeManager<ICharacteristic>{

	/**
	 * 
	 * @return A String that represents the characteristics name.
	 */
	String name();
	/**
	 * 
	 * @return A String that represents the characteristics value.
	 */
	<T> T value();
	/**
	 * 
	 * @return A String that defines the characteristic.
	 */
	String description();
	/**
	 * 
	 * @return Content that represents the characteristic.
	 */
	<T> T content();
	/**
	 * 
	 * @param value The new name of the characteristic.
	 */
	void name(String value);
	/**
	 * 
	 * @param value The new description of the characteristic.
	 */
	void description(String value);
	/**
	 * 
	 * @param value The new content for the characteristic.
	 */
	<T> void content(T value);
	/**
	 * 
	 * @param value The new value for this characteristic
	 */
	<T> void value(T value);
}