package playerlib.items;

import java.util.List;

import playerlib.core.ChangeListener;
import playerlib.core.ValueChangeManager;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IItem extends ValueChangeManager<IItem> {
	/**
	 * 
	 * @return The list of properties associated with this item.
	 */
	List<IProperty> properties();
	/**
	 * 
	 * @return A String value representing the name of the item.
	 */
	String name();
	/**
	 * 
	 * @return A String value representing the description of the item.
	 */
	String description();
	/**
	 * 
	 * @param value The new String value representing the name of the item.
	 */
	void name(String value);
	/**
	 * 
	 * @param value The new String value representing the description of the item.
	 */
	void description(String value);
	/**
	 * 
	 * @return Content that is associated with the item.
	 */
	<T> T content();
	/**
	 * 
	 * @param value New content to be associated with the item.
	 */
	<T> void content(T value);
	/**
	 * 
	 * @param name The name of the property to get.
	 * @return The first property that matches this name.
	 */
	IProperty getProperty(String name);
	/**
	 * 
	 * @param property The property to add to this item.
	 */
	void addProperty(IProperty property);
	/**
	 * 
	 * @param properties The properties to add to this item.
	 */
	void addProperties(List<IProperty> properties);
	/**
	 * 
	 * @param property The property to remove from this item.
	 */
	void removeProperty(IProperty property);
	/**
	 * 
	 * @param properties The properties to remove from this item.
	 */
	void removeProperties(List<IProperty> properties);
	/**
	 * Remove all properties from this item.
	 */
	void clearProperties();
	/**
	 * This is used to allow a consumer to be notified when any property is added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever a property is added, changed or removed.
	 */
	void addPropertyChangeListener(ChangeListener<IProperty> listener);
	/**
	 * This is used to stop a consumer from receiving notifications when property are added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever a property is added, changed or removed.
	 */
	void removePropertyChangeListener(ChangeListener<IProperty> listener);
}