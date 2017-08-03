package playerlib.attributes;

import java.util.ArrayList;
import java.util.List;

import playerlib.core.ValueListener;

/**
 * 
 * @author Jeff Riggle
 * 
 */
public class Attribute implements IAttribute{

	private String name;
	private String description;
	private Object value;
	private Object content;
	private List<ValueListener<IAttribute>> listeners;
	
	/**
	 * 
	 * @param name The name of the Attribute.
	 * @param value The value for the Attribute.
	 */
	public <T>Attribute(String name, T value) {
		this(name, new String(), value, null);
	}
	
	/**
	 * 
	 * @param name The name of the Attribute.
	 * @param description A description of the Attribute.
	 * @param value The value associated with the Attribute.
	 * @param image An image for the Attribute.
	 */
	public <T>Attribute(String name, String description, T value, T content) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.content = content;
		listeners = new ArrayList<ValueListener<IAttribute>>();
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public String description() {
		return description;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T value() {
		return (T)value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T content() {
		return (T)content;
	}

	@Override
	public void name(String value) {
		name = value;
	}
	
	@Override
	public void description(String value) {
		description = value;
	}
	
	@Override
	public <T> void content(T value) {
		content = value;
	}
	
	@Override
	public <T> void value(T value) {
		this.value = value;
		notifyListeners();
	}
	
	@Override
	public void addChangeListener(ValueListener<IAttribute> listener) {
		listeners.add(listener);
	}
	
	@Override
	public void removeChangeListener(ValueListener<IAttribute> listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners() {
		for (ValueListener<IAttribute> listener : listeners) {
			listener.changed(this, value);
		}
	}
}