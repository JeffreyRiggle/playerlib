package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import playerlib.equipment.*;
import playerlib.items.*;

public class EquipmentUnitTest {

	private Map<IBodyPart, IItem> _equipmentMap;
	private final IItem _item1 = new Item("Sword");
	private final IItem _item2 = new Item("Chest Plate");
	private final IItem _item3 = new Item("Steel Boots");
	private final IBodyPart _bodyPart1 = new BodyPart("Left Hand");
	private final IBodyPart _bodyPart2 = new BodyPart("Torso");
	private final IBodyPart _bodyPart3 = new BodyPart("Feet");
	
	@Test
	public void createMinimalEquipment() {
		IEquipment equipment = new Equipment();
		
		assertEquals(0, equipment.equipted().size());
	}
	
	@Test
	public void createFullEquipment() {
		_equipmentMap = new HashMap<IBodyPart, IItem>();
		_equipmentMap.put(_bodyPart1, _item1);
		IEquipment equipment = new Equipment(_equipmentMap);
		
		assertEquals(1, equipment.equipted().size());
		_equipmentMap.clear();
	}
	
	@Test
	public void getEquipment() {
		_equipmentMap = new HashMap<IBodyPart, IItem>();
		_equipmentMap.put(_bodyPart1, _item1);
		_equipmentMap.put(_bodyPart2, _item2);
		_equipmentMap.put(_bodyPart3, _item3);
		
		IEquipment equipment = new Equipment(_equipmentMap);
		
		assertEquals(3, equipment.equipted().size());
		assertEquals(_item1, equipment.equipted(_bodyPart1));
		assertEquals(_item2, equipment.equipted(_bodyPart2));
		assertEquals(_item3, equipment.equipted(_bodyPart3));
		
		_equipmentMap.clear();
	}
	
	@Test
	public void equip() {
		IEquipment equipment = new Equipment();
		

		equipment.equip(_bodyPart1, _item1);
		equipment.equip(_bodyPart2, _item2);
		equipment.equip(_bodyPart3, _item3);
		
		assertEquals(3, equipment.equipted().size());
		assertEquals(_item1, equipment.equipted(_bodyPart1));
		assertEquals(_item2, equipment.equipted(_bodyPart2));
		assertEquals(_item3, equipment.equipted(_bodyPart3));
	}
	
	@Test
	public void equipRange() {
		_equipmentMap = new HashMap<IBodyPart, IItem>();
		_equipmentMap.put(_bodyPart1, _item1);
		_equipmentMap.put(_bodyPart2, _item2);
		_equipmentMap.put(_bodyPart3, _item3);
	
		Equipment equipment = new Equipment();
		equipment.equip(_equipmentMap);
		
		assertEquals(3, equipment.equipted().size());
		assertEquals(_item1, equipment.equipted(_bodyPart1));
		assertEquals(_item2, equipment.equipted(_bodyPart2));
		assertEquals(_item3, equipment.equipted(_bodyPart3));
	}
	
	@Test
	public void unEquip() {
		_equipmentMap = new HashMap<IBodyPart, IItem>();
		_equipmentMap.put(_bodyPart1, _item1);
		_equipmentMap.put(_bodyPart2, _item2);
		_equipmentMap.put(_bodyPart3, _item3);
		
		IEquipment equipment = new Equipment(_equipmentMap);
		
		assertEquals(3, equipment.equipted().size());
		assertEquals(_item1, equipment.unEquip(_bodyPart1));
		assertEquals(_item2, equipment.unEquip(_bodyPart2));
		assertEquals(_item3, equipment.unEquip(_bodyPart3));
		assertEquals(0, equipment.equipted().size());
	}
	
	@Test
	public void unEquipRange() {
		_equipmentMap = new HashMap<IBodyPart, IItem>();
		_equipmentMap.put(_bodyPart1, _item1);
		_equipmentMap.put(_bodyPart2, _item2);
		_equipmentMap.put(_bodyPart3, _item3);
		
		IEquipment equipment = new Equipment(_equipmentMap);
		
		List<IBodyPart> removeList = new ArrayList<IBodyPart>();
		removeList.add(_bodyPart1);
		removeList.add(_bodyPart2);
		List<IItem> removedItems = equipment.unEquip(removeList);
		assertEquals(2, removedItems.size());
		assertEquals(_item1, removedItems.get(0));
		assertEquals(_item2, removedItems.get(1));
		
		assertEquals(1, equipment.equipted().size());
		assertEquals(_item3, equipment.equipted().get(0));
		_equipmentMap.clear();
	}
	
	@Test
	public void unEquipAll() {
		_equipmentMap = new HashMap<IBodyPart, IItem>();
		_equipmentMap.put(_bodyPart1, _item1);
		_equipmentMap.put(_bodyPart2, _item2);
		_equipmentMap.put(_bodyPart3, _item3);
		
		IEquipment equipment = new Equipment(_equipmentMap);
		
		List<IItem> unequiptedItems = equipment.unEquipAll();
		assertEquals(3, unequiptedItems.size());
		assertEquals(true, unequiptedItems.contains(_item1));
		assertEquals(true, unequiptedItems.contains(_item2));
		assertEquals(true, unequiptedItems.contains(_item3));
		assertEquals(0, equipment.equipted().size());
	}
	
	@Test
	public void equipedWithListener() {
		IEquipment equipment = new Equipment();
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		equipment.equip(new BodyPart("Head"), new Item("Hat"));
		assertEquals(1, listener.added());
	}
	
	@Test
	public void multiEquipWithListener() {
		IEquipment equipment = new Equipment();
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		Map<IBodyPart, IItem> equiplist = new HashMap<IBodyPart, IItem>();
		equiplist.put(new BodyPart("Head"), new Item("Hat"));
		equiplist.put(new BodyPart("Left Arm"), new Item("Sheild"));
		equiplist.put(new BodyPart("Right Arm"), new Item("Sword"));
		equipment.equip(equiplist);
		assertEquals(3, listener.added());
	}
	
	@Test
	public void equipedWithNoListener() {
		IEquipment equipment = new Equipment();
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		equipment.removeEquipmentChangeListener(listener);
		equipment.equip(new BodyPart("Head"), new Item("Hat"));
		assertEquals(0, listener.added());
	}
	
	@Test
	public void unequipWithListener() {
		Map<IBodyPart, IItem> equiplist = new HashMap<IBodyPart, IItem>();
		IBodyPart head = new BodyPart("Head");
		equiplist.put(head, new Item("Hat"));
		IEquipment equipment = new Equipment(equiplist);
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		equipment.unEquip(head);
		assertEquals(1, listener.removed());
	}
	
	@Test
	public void multiUnequipWithListener() {
		Map<IBodyPart, IItem> equiplist = new HashMap<IBodyPart, IItem>();
		equiplist.put(new BodyPart("Head"), new Item("Hat"));
		equiplist.put(new BodyPart("Left Arm"), new Item("Sheild"));
		equiplist.put(new BodyPart("Right Arm"), new Item("Sword"));
		IEquipment equipment = new Equipment(equiplist);
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		equipment.unEquip(new ArrayList<IBodyPart>(equiplist.keySet()));
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void unequipAllWithListener() {
		Map<IBodyPart, IItem> equiplist = new HashMap<IBodyPart, IItem>();
		equiplist.put(new BodyPart("Head"), new Item("Hat"));
		equiplist.put(new BodyPart("Left Arm"), new Item("Sheild"));
		equiplist.put(new BodyPart("Right Arm"), new Item("Sword"));
		IEquipment equipment = new Equipment(equiplist);
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		equipment.unEquipAll();
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void unequipWithNoListener() {
		Map<IBodyPart, IItem> equiplist = new HashMap<IBodyPart, IItem>();
		IBodyPart head = new BodyPart("Head");
		equiplist.put(head, new Item("Hat"));
		IEquipment equipment = new Equipment(equiplist);
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		equipment.removeEquipmentChangeListener(listener);
		equipment.unEquip(head);
		assertEquals(0, listener.removed());
	}
	
	@Test
	public void equipedChangedWithListener() {
		IEquipment equipment = new Equipment();
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		IBodyPart head = new BodyPart("Head");
		equipment.equip(head, new Item("Hat"));
		equipment.equip(head, new Item("HairBand"));
		assertEquals(1, listener.changed());
	}
	
	@Test
	public void equipedChangedWithNoListener() {
		IEquipment equipment = new Equipment();
		TestChangeListener<IEquiped> listener = new TestChangeListener<IEquiped>();
		equipment.addEquipmentChangeListener(listener);
		equipment.removeEquipmentChangeListener(listener);
		IBodyPart head = new BodyPart("Head");
		equipment.equip(head, new Item("Hat"));
		equipment.equip(head, new Item("HairBand"));
		assertEquals(0, listener.changed());
	}
}