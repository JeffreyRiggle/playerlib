package playerlib.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import playerlib.core.ChangeListener;
import playerlib.core.NotificationManager;
import playerlib.items.IItem;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class Inventory implements IInventory{
	
	private Map<IItem, Integer> items;
	private NotificationManager<IItem> itemNotifier;
	
	/**
	 * 
	 */
	public Inventory() {
		this(new HashMap<IItem, Integer>());
	}
	
	/**
	 * 
	 * @param items
	 */
	public Inventory(Map<IItem, Integer> items) {
		this.items = items;
		itemNotifier = new NotificationManager<IItem>();
		
		initialize();
	}
	
	private void initialize() {
		itemNotifier.notifyListeners(new ArrayList<IItem>(items.keySet()), new ArrayList<IItem>(), new ArrayList<IItem>());
	}
	
	@Override
	public synchronized List<IItem> items() {
		List<IItem> items = new ArrayList<IItem>(this.items.keySet());
		return items;
	}
	
	@Override
	public synchronized void addItem(IItem item, int amount) {
		items.put(item, amount);
		itemNotifier.notifyAdd(new ArrayList<IItem>(Arrays.asList(item)));
	}
	
	@Override
	public synchronized void addItems(Map<IItem, Integer> items) {
		this.items.putAll(items);
		itemNotifier.notifyAdd(new ArrayList<IItem>(items.keySet()));
	}
	
	@Override
	public synchronized void removeItem(IItem item) {
		items.remove(item);
		itemNotifier.notifyRemoved(new ArrayList<IItem>(Arrays.asList(item)));
	}
	
	@Override
	public synchronized void removeItems(List<IItem> items) {
		for (IItem item : items) {
			this.items.remove(item);
		}
		
		itemNotifier.notifyRemoved(new ArrayList<IItem>(items));
	}
	
	@Override
	public synchronized void clearItems() {
		ArrayList<IItem> temp = new ArrayList<IItem>(items.keySet());
		items.clear();
		itemNotifier.notifyRemoved(temp);
	}
	
	@Override
	public synchronized int getAmount(IItem item) {
		return items.get(item);
	}

	@Override
	public void setAmount(IItem item, int amount) {
		items.put(item, amount);
	}

	@Override
	public void addItemChangeListener(ChangeListener<IItem> listener) {
		itemNotifier.addListener(listener);
	}

	@Override
	public void removeItemChangeListener(ChangeListener<IItem> listener) {
		itemNotifier.removeListener(listener);
	}
}