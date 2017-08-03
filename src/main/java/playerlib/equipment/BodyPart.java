package playerlib.equipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import playerlib.characteristics.ICharacteristic;
import playerlib.core.ChangeListener;
import playerlib.core.NotificationManager;
import playerlib.core.ValueListener;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class BodyPart implements IBodyPart{

	private String name;
	private String description;
	private List<ICharacteristic> characteristics;
	private NotificationManager<ICharacteristic> charNotifier;
	private List<ValueListener<IBodyPart>> listeners;
	
	/**
	 * 
	 * @param name The name of the body part.
	 */
	public BodyPart(String name) {
		this(name, new String(), new ArrayList<ICharacteristic>());
	}
	
	/**
	 * 
	 * @param name The name of the body part.
	 * @param description A description of that body part.
	 * @param characteristics A list of characteristics associated with that body part.
	 */
	public BodyPart(String name, String description, List<ICharacteristic> characteristics) {
		this.name = name;
		this.description = description;
		this.characteristics = characteristics;
		charNotifier = new NotificationManager<ICharacteristic>();
		listeners = new ArrayList<ValueListener<IBodyPart>>();
		
		initialize();
	}
	
	private void initialize() {
		if (characteristics != null) {
			charNotifier.notifyListeners(characteristics, new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>());
		}
	}
	
	@Override
	public List<ICharacteristic> getCharacteristics() {
		return characteristics;
	}

	@Override
	public void addCharacteristic(ICharacteristic characteristic) {
		characteristics.add(characteristic);
		charNotifier.notifyListeners(new ArrayList<ICharacteristic>(Arrays.asList(characteristic)), new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>());
		notifyListeners();
	}

	@Override
	public void addCharacteristics(List<ICharacteristic> characteristics) {
		this.characteristics.addAll(characteristics);
		charNotifier.notifyListeners(characteristics, new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>());
		notifyListeners();
	}

	@Override
	public void removeCharacteristic(ICharacteristic characteristic) {
		characteristics.remove(characteristic);
		charNotifier.notifyListeners(new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>(Arrays.asList(characteristic)), new ArrayList<ICharacteristic>());
		notifyListeners();
	}

	@Override
	public void removeCharacteristics(List<ICharacteristic> characteristics) {
		this.characteristics.removeAll(characteristics);
		charNotifier.notifyListeners(new ArrayList<ICharacteristic>(), characteristics, new ArrayList<ICharacteristic>());
		notifyListeners();
	}

	@Override
	public ICharacteristic getCharacteristic(String name) {
		ICharacteristic retCharacteristic = null;
		for(ICharacteristic characteristic : characteristics) {
			if (characteristic.name().equalsIgnoreCase(name)) {
				retCharacteristic = characteristic;
				break;
			}
		}
		return retCharacteristic;
	}

	@Override
	public void clearCharacteristics() {
		List<ICharacteristic> temp = new ArrayList<ICharacteristic>(characteristics);
		characteristics.clear();
		charNotifier.notifyListeners(new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>(temp), new ArrayList<ICharacteristic>());
		notifyListeners();
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
	public int compareTo(IBodyPart arg0) {
		if (arg0.name().equals(name()) && arg0.description() == description()) {
			return 0;
		}
		
		return arg0.name().compareTo(name());
	}

	@Override
	public void addCharacteristicChangeListener(ChangeListener<ICharacteristic> listener) {
		charNotifier.addListener(listener);
	}

	@Override
	public void removeCharacteristicChangeListener(ChangeListener<ICharacteristic> listener) {
		charNotifier.removeListener(listener);
	}

	@Override
	public void addChangeListener(ValueListener<IBodyPart> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeChangeListener(ValueListener<IBodyPart> listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners() {
		for (ValueListener<IBodyPart> listener : listeners) {
			listener.changed(this, characteristics);
		}
	}
}