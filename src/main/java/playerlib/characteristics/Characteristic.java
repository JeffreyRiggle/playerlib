package playerlib.characteristics;

import java.util.ArrayList;
import java.util.List;

import playerlib.core.ValueListener;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class Characteristic implements ICharacteristic {

	private String name;
	private Object value;
	private String description;
	private Object content;
	private List<ValueListener<ICharacteristic>> listeners;
	
	/**
	 * 
	 * @param name The name of the characteristic.
	 * @param value The value associated with the characteristic.
	 */
	public <T>Characteristic(String name, T value) {
		this(name, value, new String(), null);
	}
	
	/**
	 * 
	 * @param name The name of the characteristic.
	 * @param value The value associated with the characteristic.
	 * @param description A description of the characteristic (what is it)
	 * @param image An image that correlates to the characteristic
	 */
	public <T>Characteristic(String name, T value, String description, T content) {
		this.name = name;
		this.value = value;
		this.description = description;
		this.content = content;
		listeners = new ArrayList<ValueListener<ICharacteristic>>();
	}
	
	@Override
	public String name() {
		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T value() {
		return (T)value;
	}

	@Override
	public String description() {
		return description;
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
	public <T>void value(T value) {
		this.value = value;
		notifyListeners();
	}

	@Override
	public void addChangeListener(ValueListener<ICharacteristic> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeChangeListener(ValueListener<ICharacteristic> listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners() {
		for (ValueListener<ICharacteristic> listener : listeners) {
			listener.changed(this, value);
		}
	}
}