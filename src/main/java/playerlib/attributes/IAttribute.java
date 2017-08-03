package playerlib.attributes;

import playerlib.core.ValueChangeManager;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IAttribute extends ValueChangeManager<IAttribute>{
	/**
	 * 
	 * @return A String that represents the name of the Attribute.
	 */
	String name();
	/**
	 * 
	 * @return A String that helps the user understand what the attribute is.
	 */
	String description();
	/**
	 * 
	 * @return The value of the Attribute.
	 */
	<T> T value();
	/**
	 * 
	 * @return The content associated with this Attribute.
	 */
	<T> T content();
	/**
	 * 
	 * @param value The new name for this attribute.
	 */
	void name(String value);
	/**
	 * 
	 * @param value The new description for this attribute.
	 */
	void description(String value);
	/**
	 * 
	 * @param value The new Content for this attribute.
	 */
	<T> void content(T value);
	/**
	 * 
	 * @param value A new value to be associated with this attribute.
	 */
	<T> void value(T value);
}