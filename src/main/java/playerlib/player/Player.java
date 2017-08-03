package playerlib.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import playerlib.attributes.IAttribute;
import playerlib.characteristics.ICharacteristic;
import playerlib.core.ChangeListener;
import playerlib.core.NotificationManager;
import playerlib.equipment.Equipment;
import playerlib.equipment.IBodyPart;
import playerlib.equipment.IEquipment;
import playerlib.inventory.IInventory;
import playerlib.inventory.Inventory;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class Player implements IPlayer {

	private String name;
	private Object content;
	private IInventory inventory;
	private IEquipment equipment;
	private List<IAttribute> attributes;
	private List<ICharacteristic> characteristics;
	private List<IBodyPart> bodyParts;
	private NotificationManager<IAttribute> attributeNotifier;
	private NotificationManager<ICharacteristic> characteristicNotifier;
	private NotificationManager<IBodyPart> bodyPartNotifier;
	
	/**
	 * 
	 * @param name The name of the player.
	 */
	public Player(String name) {
		this(name, null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
	}
	
	/**
	 * 
	 * @param name The name of the player.
	 * @param content The content to associate with the player.
	 * @param inventory The inventory to give to this player.
	 * @param equipment The equipment to give to this player.
	 * @param attributes The players attributes.
	 * @param characteristics The players characteristics.
	 * @param bodyParts The players body parts.
	 */
	public <T>Player(String name, T content, IInventory inventory, 
			IEquipment equipment, List<IAttribute> attributes, List<ICharacteristic> characteristics,
			List<IBodyPart> bodyParts) {
		this.name = name;
		this.content = content;
		this.inventory = inventory;
		this.equipment = equipment;
		this.attributes = attributes;
		this.characteristics = characteristics;
		this.bodyParts = bodyParts;
		attributeNotifier = new NotificationManager<IAttribute>();
		characteristicNotifier = new NotificationManager<ICharacteristic>();
		bodyPartNotifier = new NotificationManager<IBodyPart>();
		
		initialize();
	}
	
	private void initialize() {
		if (attributes != null) {
			attributeNotifier.notifyListeners(attributes, new ArrayList<IAttribute>(), new ArrayList<IAttribute>());
		}
		
		if (characteristics != null) {
			characteristicNotifier.notifyListeners(characteristics, new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>());
		}
		
		if (bodyParts != null) {
			bodyPartNotifier.notifyListeners(bodyParts, new ArrayList<IBodyPart>(), new ArrayList<IBodyPart>());
		}
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public void name(String value) {
		name = value;
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
	public IInventory inventory() {
		return inventory;
	}

	@Override
	public void inventory(IInventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public IEquipment equipment() {
		return equipment;
	}

	@Override
	public void equipment(IEquipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public List<IAttribute> attributes() {
		return attributes;
	}

	@Override
	public void addAttribute(IAttribute attribute) {
		attributes.add(attribute);
		attributeNotifier.notifyListeners(new ArrayList<IAttribute>(Arrays.asList(attribute)), new ArrayList<IAttribute>(), new ArrayList<IAttribute>());
	}

	@Override
	public void addAttributes(List<IAttribute> attributes) {
		this.attributes.addAll(attributes);
		attributeNotifier.notifyListeners(attributes, new ArrayList<IAttribute>(), new ArrayList<IAttribute>());
	}

	@Override
	public void removeAttribute(IAttribute attribute) {
		attributes.remove(attribute);
		attributeNotifier.notifyListeners(new ArrayList<IAttribute>(), new ArrayList<IAttribute>(Arrays.asList(attribute)), new ArrayList<IAttribute>());
	}

	@Override
	public void removeAttributes(List<IAttribute> attributes) {
		this.attributes.removeAll(attributes);
		attributeNotifier.notifyListeners(new ArrayList<IAttribute>(), attributes, new ArrayList<IAttribute>());
	}

	@Override
	public void clearAttributes() {
		List<IAttribute> temp = new ArrayList<IAttribute>(attributes);
		attributes.clear();
		attributeNotifier.notifyListeners(new ArrayList<IAttribute>(), temp, new ArrayList<IAttribute>());
	}

	@Override
	public List<ICharacteristic> characteristics() {
		return characteristics;
	}

	@Override
	public void addCharacteristic(ICharacteristic characteristic) {
		characteristics.add(characteristic);
		characteristicNotifier.notifyListeners(new ArrayList<ICharacteristic>(Arrays.asList(characteristic)), new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>());
	}

	@Override
	public void addCharacteristics(List<ICharacteristic> characteristics) {
		this.characteristics.addAll(characteristics);
		characteristicNotifier.notifyListeners(characteristics, new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>());
	}

	@Override
	public void removeCharacteristic(ICharacteristic characteristic) {
		characteristics.remove(characteristic);
		characteristicNotifier.notifyListeners(new ArrayList<ICharacteristic>(), new ArrayList<ICharacteristic>(Arrays.asList(characteristic)), new ArrayList<ICharacteristic>());
	}

	@Override
	public void removeCharacteristics(List<ICharacteristic> characteristics) {
		this.characteristics.removeAll(characteristics);
		characteristicNotifier.notifyListeners(new ArrayList<ICharacteristic>(), characteristics, new ArrayList<ICharacteristic>());
	}

	@Override
	public void clearCharacteristics() {
		List<ICharacteristic> temp = new ArrayList<ICharacteristic>(characteristics);
		characteristics.clear();
		characteristicNotifier.notifyListeners(new ArrayList<ICharacteristic>(), temp, new ArrayList<ICharacteristic>());
	}

	@Override
	public List<IBodyPart> bodyParts() {
		return bodyParts;
	}

	@Override
	public void addBodyPart(IBodyPart bodyPart) {
		bodyParts.add(bodyPart);
		bodyPartNotifier.notifyListeners(new ArrayList<IBodyPart>(Arrays.asList(bodyPart)), new ArrayList<IBodyPart>(), new ArrayList<IBodyPart>());
	}

	@Override
	public void addBodyParts(List<IBodyPart> bodyParts) {
		this.bodyParts.addAll(bodyParts);
		bodyPartNotifier.notifyListeners(bodyParts, new ArrayList<IBodyPart>(), new ArrayList<IBodyPart>());
	}

	@Override
	public void removeBodyPart(IBodyPart bodyPart) {
		bodyParts.remove(bodyPart);
		bodyPartNotifier.notifyListeners(new ArrayList<IBodyPart>(), new ArrayList<IBodyPart>(Arrays.asList(bodyPart)), new ArrayList<IBodyPart>());
	}

	@Override
	public void removeBodyParts(List<IBodyPart> bodyParts) {
		this.bodyParts.removeAll(bodyParts);
		bodyPartNotifier.notifyListeners(new ArrayList<IBodyPart>(), bodyParts, new ArrayList<IBodyPart>());
	}

	@Override
	public void clearBodyParts() {
		List<IBodyPart> temp = new ArrayList<IBodyPart>(bodyParts);
		bodyParts.clear();
		bodyPartNotifier.notifyListeners(new ArrayList<IBodyPart>(), temp, new ArrayList<IBodyPart>());
	}

	@Override
	public void addAttributeChangeListener(ChangeListener<IAttribute> listener) {
		attributeNotifier.addListener(listener);
	}

	@Override
	public void removeAttributeChangedListener(ChangeListener<IAttribute> listener) {
		attributeNotifier.removeListener(listener);
	}

	@Override
	public void addCharacteristicChangeListener(ChangeListener<ICharacteristic> listener) {
		characteristicNotifier.addListener(listener);
	}

	@Override
	public void removeCharacteristicChangeListener(ChangeListener<ICharacteristic> listener) {
		characteristicNotifier.removeListener(listener);
	}

	@Override
	public void addBodyPartChangeListener(ChangeListener<IBodyPart> listener) {
		bodyPartNotifier.addListener(listener);
	}

	@Override
	public void removeBodyPartChangeListener(ChangeListener<IBodyPart> listener) {
		bodyPartNotifier.removeListener(listener);
	}
}