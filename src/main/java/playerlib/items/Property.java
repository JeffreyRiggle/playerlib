package playerlib.items;

import java.util.ArrayList;
import java.util.List;

import playerlib.core.ValueListener;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class Property implements IProperty{

	private String name;
	private String description;
	private Object value;
	private List<ValueListener<IProperty>> listeners;
	
	/**
	 * 
	 * @param name
	 * The name of the Property.
	 * @param value
	 * The Stat/Attribute/Characteristic the Property modifies.
	 */
	public <T> Property(String name, T value) {
		this(name, new String(), value);
	}
	
	/**
	 * 
	 * @param name
	 * The name of the Property.
	 * @param description
	 * The description associated with the Property.
	 * @param value
	 * The Stat/Attribute/Characteristic the Property modifies.
	 */
	public <T> Property(String name, String description, T value) {
		this.name = name;
		this.description = description;
		this.value = value;
		
		listeners = new ArrayList<ValueListener<IProperty>>();
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
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T value() {
		return (T)value;
	}
	
	@Override
	public <T> void value(T value) {
		this.value = value;
		notifyListeners();
	}

	@Override
	public void addChangeListener(ValueListener<IProperty> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeChangeListener(ValueListener<IProperty> listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners() {
		for (ValueListener<IProperty> listener : listeners) {
			listener.changed(this, value);
		}
	}
}