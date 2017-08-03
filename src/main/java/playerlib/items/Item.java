package playerlib.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import playerlib.core.ChangeListener;
import playerlib.core.NotificationManager;
import playerlib.core.ValueListener;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class Item implements IItem{

	private List<IProperty> properties;
	private String name;
	private String description;
	private Object content;
	private NotificationManager<IProperty> propertyNotifier;
	private List<ValueListener<IItem>> changeListeners;
	
	/**
	 * 
	 * @param name The name of the item.
	 */
	public Item(String name) {
		this(new ArrayList<IProperty>(), name, new String(), null);
	}

	/**
	 * 
	 * @param properties The properties associated with this Item(re-word this).
	 * @param name The name of the item.
	 * @param description A description of the item.
	 */
	public <T>Item(List<IProperty> properties, String name, String description, T content) {
		this.properties = properties;
		this.name = name;
		this.description = description;
		this.content = content;
		propertyNotifier = new NotificationManager<IProperty>();
		changeListeners = new ArrayList<ValueListener<IItem>>();
		
		initialize();
	}
	
	private void initialize() {
		propertyNotifier.notifyListeners(properties, new ArrayList<IProperty>(), new ArrayList<IProperty>());
	}

	@Override
	public synchronized List<IProperty> properties() {
		return properties;
	}
	
	@Override
	public String name() {
		return name;
	}
	
	@Override
	public void name(String value) {
		name = value;
	}
	
	@Override
	public String description() {
		return description;
	}
	
	@Override
	public void description(String value) {
		description = value;
	}
	
	@Override
	public synchronized IProperty getProperty(String name) {
		IProperty retProperty = null;
		for (IProperty property : properties) {
			if (property.name() == name) {
				retProperty = property;
				break;
			}
		}
		return retProperty;
	}
	
	@Override
	public synchronized void addProperty(IProperty property) {
		properties.add(property);
		propertyNotifier.notifyListeners(new ArrayList<IProperty>(Arrays.asList(property)), new ArrayList<IProperty>(), new ArrayList<IProperty>());
		notifyChangeListeners();
	}
	
	@Override
	public synchronized void addProperties(List<IProperty> properties) {
		this.properties.addAll(properties);
		propertyNotifier.notifyListeners(properties, new ArrayList<IProperty>(), new ArrayList<IProperty>());
		notifyChangeListeners();
	}
	
	@Override
	public synchronized void removeProperty(IProperty property) {
		properties.remove(property);
		propertyNotifier.notifyListeners(new ArrayList<IProperty>(), new ArrayList<IProperty>(Arrays.asList(property)), new ArrayList<IProperty>());
		notifyChangeListeners();
	}
	
	@Override
	public synchronized void removeProperties(List<IProperty> properties) {
		this.properties.removeAll(properties);
		propertyNotifier.notifyListeners(new ArrayList<IProperty>(), properties, new ArrayList<IProperty>());
		notifyChangeListeners();
	}
	
	@Override
	public synchronized void clearProperties() {
		List<IProperty> temp = new ArrayList<IProperty>(properties);
		properties.clear();
		propertyNotifier.notifyListeners(new ArrayList<IProperty>(), temp, new ArrayList<IProperty>());
		notifyChangeListeners();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T content() {
		return (T)content;
	}
	
	@Override
	public <T> void content(T value) {
		content = value;
	}

	@Override
	public void addPropertyChangeListener(ChangeListener<IProperty> listener) {
		propertyNotifier.addListener(listener);
	}

	@Override
	public void removePropertyChangeListener(ChangeListener<IProperty> listener) {
		propertyNotifier.removeListener(listener);
	}

	@Override
	public void addChangeListener(ValueListener<IItem> listener) {
		changeListeners.add(listener);
	}

	@Override
	public void removeChangeListener(ValueListener<IItem> listener) {
		changeListeners.remove(listener);
	}
	
	private void notifyChangeListeners() {
		for (ValueListener<IItem> listener : changeListeners) {
			listener.changed(this, new ArrayList<IProperty>(properties));
		}
	}
}