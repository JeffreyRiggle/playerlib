package playerlib.equipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import playerlib.core.ChangeListener;
import playerlib.core.NotificationManager;
import playerlib.items.IItem;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class Equipment implements IEquipment {

	private Map<IBodyPart, IItem> items;
	private NotificationManager<IEquiped> equipNotifier;
	
	/**
	 * 
	 */
	public Equipment() {
		this(new TreeMap<IBodyPart, IItem>());
	}
	
	/**
	 * 
	 * @param items The initial Map to use.
	 */
	public Equipment(Map<IBodyPart, IItem> items) {
		this.items = items;
		equipNotifier = new NotificationManager<IEquiped>();
	}
	
	@Override
	public synchronized List<IItem> equipted() {
		List<IItem> retVal = new ArrayList<IItem>();
		retVal.addAll(items.values());
		return retVal;
	}

	@Override
	public synchronized IItem equipted(IBodyPart bodyPart) {
		return items.get(bodyPart);
	}
	
	@Override
	public synchronized void equip(IBodyPart bodyPart, IItem item) {
		boolean isChange = items.containsKey(bodyPart);
		items.put(bodyPart, item);
		
		IEquiped equiped = new Equiped(bodyPart, item);
		
		if (isChange) {
			equipNotifier.notifyChanged(new ArrayList<IEquiped>(Arrays.asList(equiped)));
		} else {
			equipNotifier.notifyAdd(new ArrayList<IEquiped>(Arrays.asList(equiped)));
		}
	}

	@Override
	public synchronized void equip(Map<IBodyPart, IItem> items) {
		this.items.putAll(items);
		
		List<IEquiped> added = new ArrayList<IEquiped>();
		for (Entry<IBodyPart, IItem> entry : items.entrySet()) {
			added.add(new Equiped(entry.getKey(), entry.getValue()));
		}
		equipNotifier.notifyAdd(added);
	}

	@Override
	public synchronized IItem unEquip(IBodyPart bodyPart) {
		IItem item = items.get(bodyPart);
		items.remove(bodyPart);
		
		equipNotifier.notifyRemoved(new ArrayList<IEquiped>(Arrays.asList(new Equiped(bodyPart, item))));
		return item;
	}

	@Override
	public synchronized List<IItem> unEquip(List<IBodyPart> bodyParts) {
		List<IItem> retItems = new ArrayList<IItem>();
		List<IEquiped> removed = new ArrayList<IEquiped>();
		
		for (IBodyPart bodyPart : bodyParts) {
			IItem item = items.get(bodyPart);
			
			retItems.add(item);
			removed.add(new Equiped(bodyPart, item));
			
			items.remove(bodyPart);
		}
		
		equipNotifier.notifyRemoved(removed);
		return retItems;
	}

	@Override
	public synchronized List<IItem> unEquipAll() {
		List<IBodyPart> parts = new ArrayList<IBodyPart>();
		parts.addAll(items.keySet());
		
		return unEquip(parts);
	}

	@Override
	public void addEquipmentChangeListener(ChangeListener<IEquiped> listener) {
		equipNotifier.addListener(listener);
	}

	@Override
	public void removeEquipmentChangeListener(ChangeListener<IEquiped> listener) {
		equipNotifier.removeListener(listener);
	}

}