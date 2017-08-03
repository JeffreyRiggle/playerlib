package playerlib.inventory;

import java.util.List;
import java.util.Map;

import playerlib.core.ChangeListener;
import playerlib.items.IItem;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IInventory {
	/**
	 * 
	 * @return All of the items in the inventory.
	 */
	List<IItem> items();
	/**
	 * 
	 * @param item The item you would like an amount for.
	 * @return How many of that item is in the inventory.
	 */
	int getAmount(IItem item);
	/**
	 * 
	 * @param item The item that you would like to change the amount of.
	 * @param amount The amount to change to.
	 */
	void setAmount(IItem item, int amount);
	/**
	 * 
	 * @param item The item to be added to the inventory.
	 * @param amount The amount of that item to add to the inventory.
	 */
	void addItem(IItem item, int amount);
	/**
	 * 
	 * @param items A map of items and there amounts to be added to the inventory.
	 */
	void addItems(Map<IItem, Integer> items);
	/**
	 * 
	 * @param item The item to remove from the inventory.
	 */
	void removeItem(IItem item);
	/**
	 * 
	 * @param items The items to remove from the inventory.
	 */
	void removeItems(List<IItem> items);
	/**
	 * Clears all of the items from the inventory.
	 */
	void clearItems();
	/**
	 * This is used to allow a consumer to be notified when any item is added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this item is added, changed or removed.
	 */
	void addItemChangeListener(ChangeListener<IItem> listener);
	/**
	 * This is used to stop a consumer from receiving notifications when items are added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this item is added, changed or removed.
	 */
	void removeItemChangeListener(ChangeListener<IItem> listener);
}