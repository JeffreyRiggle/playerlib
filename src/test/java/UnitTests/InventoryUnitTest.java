package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import playerlib.inventory.*;
import playerlib.items.IItem;
import playerlib.items.Item;
import playerlib.items.Property;

public class InventoryUnitTest {

	private Map<IItem, Integer> _itemMap;
	private final IItem _item1 = new Item("Potion");
	private final IItem _item2 = new Item("Ether");
	private final IItem _item3 = new Item("Red key");
	private final int _amount1 = 10;
	private final int _amount2 = 1;
	private final int _amount3 = 35;
	
	@Test
	public void createMinimalInventory() {
		IInventory inventory = new Inventory();
		
		assertEquals(0, inventory.items().size());
	}
	
	@Test
	public void createFullInventory() {
		_itemMap = new HashMap<IItem, Integer>();
		_itemMap.put(_item1, _amount1);
		_itemMap.put(_item2, _amount2);
		_itemMap.put(_item3, _amount3);
		IInventory inventory = new Inventory(_itemMap);
		
		assertEquals(3, inventory.items().size());
		_itemMap.clear();
	}
	
	@Test
	public void testGetAmount() {
		_itemMap = new HashMap<IItem, Integer>();
		_itemMap.put(_item1, _amount1);
		_itemMap.put(_item2, _amount2);
		_itemMap.put(_item3, _amount3);
		IInventory inventory = new Inventory(_itemMap);
		
		assertEquals(_amount1, inventory.getAmount(_item1));
		assertEquals(_amount2, inventory.getAmount(_item2));
		assertEquals(_amount3, inventory.getAmount(_item3));
		
		_itemMap.clear();		
	}
	
	@Test
	public void testAddItem() {
		IInventory inventory = new Inventory();
		
		assertEquals(0, inventory.items().size());
		inventory.addItem(_item1, _amount1);
		assertEquals(1, inventory.items().size());
		assertEquals(true, inventory.items().contains(_item1));
		
		inventory.addItem(_item2, _amount2);
		assertEquals(2, inventory.items().size());
		assertEquals(true, inventory.items().contains(_item2));
		
		inventory.addItem(_item3, _amount3);
		assertEquals(3, inventory.items().size());
		assertEquals(true, inventory.items().contains(_item3));
		
	}
	
	@Test
	public void testAddItems() {
		_itemMap = new HashMap<IItem, Integer>();
		IInventory inventory = new Inventory();
		
		_itemMap.put(_item1, _amount1);
		_itemMap.put(_item2, _amount2);
		_itemMap.put(_item3, _amount3);
		
		inventory.addItems(_itemMap);
		assertEquals(3, inventory.items().size());
		assertEquals(true, inventory.items().contains(_item1));
		assertEquals(true, inventory.items().contains(_item2));
		assertEquals(true, inventory.items().contains(_item3));
		
		_itemMap.clear();
	}
	
	@Test
	public void testRemoveItem() {
		_itemMap = new HashMap<IItem, Integer>();
		_itemMap.put(_item1, _amount1);
		_itemMap.put(_item2, _amount2);
		_itemMap.put(_item3, _amount3);

		IInventory inventory = new Inventory(_itemMap);
		assertEquals(3, inventory.items().size());
		
		inventory.removeItem(_item1);
		assertEquals(2, inventory.items().size());
		assertEquals(false, inventory.items().contains(_item1));
		
		inventory.removeItem(_item2);
		assertEquals(1, inventory.items().size());
		assertEquals(false, inventory.items().contains(_item2));
		
		inventory.removeItem(_item3);
		assertEquals(0, inventory.items().size());
		assertEquals(false, inventory.items().contains(_item3));
		
		_itemMap.clear();
	}
	
	@Test
	public void testRemoveItems() {
		_itemMap = new HashMap<IItem, Integer>();
		_itemMap.put(_item1, _amount1);
		_itemMap.put(_item2, _amount2);
		_itemMap.put(_item3, _amount3);

		List<IItem> items = new ArrayList<IItem>();
		items.add(_item1);
		items.add(_item2);
		
		IInventory inventory = new Inventory(_itemMap);
		assertEquals(3, inventory.items().size());
		
		inventory.removeItems(items);
		assertEquals(1, inventory.items().size());
		assertEquals(true, inventory.items().contains(_item3));
		assertEquals(false, inventory.items().contains(_item2));
		assertEquals(false, inventory.items().contains(_item1));
		
		_itemMap.clear();
	}
	
	@Test
	public void testClearItems() {
		_itemMap = new HashMap<IItem, Integer>();
		_itemMap.put(_item1, _amount1);
		_itemMap.put(_item2, _amount2);
		_itemMap.put(_item3, _amount3);
		IInventory inventory = new Inventory(_itemMap);
		
		assertEquals(3, inventory.items().size());
		
		inventory.clearItems();
		assertEquals(0, inventory.items().size());
		
		_itemMap.clear();
	}
	
	@Test
	public void itemAddedWithListener() {
		IInventory inv = new Inventory();
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		inv.addItem(new Item("Name"), 1);
		assertEquals(1, listener.added());
	}
	
	@Test
	public void itemsAddedWithListener() {
		IInventory inv = new Inventory();
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		Map<IItem, Integer> items = new HashMap<IItem, Integer>();
		items.put(new Item("item1"), 1);
		items.put(new Item("item2"), 1);
		items.put(new Item("item3"), 1);
		inv.addItems(items);
		assertEquals(3, listener.added());
	}
	
	@Test
	public void itemAddedWithNoListener() {
		IInventory inv = new Inventory();
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		inv.removeItemChangeListener(listener);
		inv.addItem(new Item("Name"), 1);
		assertEquals(0, listener.added());
	}
	
	@Test
	public void itemRemovedWithListener() {
		Map<IItem, Integer> items = new HashMap<IItem, Integer>();
		IItem item = new Item("Name"); 
		items.put(item, 1);
		IInventory inv = new Inventory(items);
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		inv.removeItem(item);
		assertEquals(1, listener.removed());
	}
	
	@Test
	public void itemsRemovedWithListener() {
		Map<IItem, Integer> items = new HashMap<IItem, Integer>();
		items.put(new Item("item1"), 1);
		items.put(new Item("item2"), 1);
		items.put(new Item("item3"), 1);
		IInventory inv = new Inventory(items);
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		inv.removeItems(new ArrayList<IItem>(items.keySet()));
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void itemsClearedWithListener() {
		Map<IItem, Integer> items = new HashMap<IItem, Integer>();
		items.put(new Item("item1"), 1);
		items.put(new Item("item2"), 1);
		items.put(new Item("item3"), 1);
		IInventory inv = new Inventory(items);
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		inv.clearItems();
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void itemRemovedWithNoListener() {
		Map<IItem, Integer> items = new HashMap<IItem, Integer>();
		IItem item = new Item("Name"); 
		items.put(item, 1);
		IInventory inv = new Inventory(items);
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		inv.removeItemChangeListener(listener);
		inv.removeItem(item);
		assertEquals(0, listener.removed());
	}
	
	@Test
	public void itemChangedWithListener() {
		Map<IItem, Integer> items = new HashMap<IItem, Integer>();
		IItem item = new Item("Name"); 
		items.put(item, 1);
		IInventory inv = new Inventory(items);
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		item.addProperty(new Property("Prop", "Value"));
		assertEquals(1, listener.changed());
	}
	
	@Test
	public void itemChangedWithoutListener() {
		Map<IItem, Integer> items = new HashMap<IItem, Integer>();
		IItem item = new Item("Name"); 
		items.put(item, 1);
		IInventory inv = new Inventory(items);
		TestChangeListener<IItem> listener = new TestChangeListener<IItem>();
		inv.addItemChangeListener(listener);
		inv.removeItemChangeListener(listener);
		item.addProperty(new Property("Prop", "Value"));
		assertEquals(0, listener.changed());
	}
}