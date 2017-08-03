package UnitTests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import playerlib.player.*;
import playerlib.attributes.*;
import playerlib.characteristics.*;
import playerlib.inventory.*;
import playerlib.equipment.*;

public class PlayerUnitTest {

	private String _name = "Player1";
	private String _name2 = "Player2";
	private BufferedImage _content = new BufferedImage(1,2,3);
	private String _content2 = "Some Content";
	private IInventory _inventory = new Inventory();
	private IEquipment _equipment = new Equipment();
	private List<IAttribute> _attributes;
	private List<ICharacteristic> _characteristics;
	private List<IBodyPart> _bodyParts;
	private IBodyPart _bodyPart1 = new BodyPart("Leg");
	private IBodyPart _bodyPart2 = new BodyPart("Torso");
	private IAttribute _attribute1 = new Attribute("Health Points", 1000);
	private IAttribute _attribute2 = new Attribute("Mana Points", 500);
	private ICharacteristic _characteristic1 = new Characteristic("Tatoo", "Dragon");
	private ICharacteristic _characteristic2 = new Characteristic("Skin", "Pale");
	
	@Test
	public void createMinialPlayer() {
		IPlayer player = new Player(_name);
		
		assertEquals(_name, player.name());
		assertNull(player.content());
		assertEquals(0, player.attributes().size());
		assertEquals(0, player.characteristics().size());
		assertEquals(0, player.bodyParts().size());
		assertNotNull(player.inventory());
		assertNotNull(player.equipment());
	}
	
	@Test
	public void createFullPlayer() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		
		assertEquals(_name, player.name());
		assertEquals(_content, player.content());
		assertEquals(2, player.attributes().size());
		assertEquals(2, player.characteristics().size());
		assertEquals(2, player.bodyParts().size());
		assertEquals(_inventory, player.inventory());
		assertEquals(_equipment, player.equipment());
	}
	
	@Test
	public void setInventory() {
		IPlayer player = new Player(_name);
		assertNotNull(player.inventory());
		
		player.inventory(_inventory);
		assertEquals(_inventory, player.inventory());
	}
	
	@Test
	public void setEquipment() {
		IPlayer player = new Player(_name);
		assertNotNull(player.equipment());
		
		player.equipment(_equipment);
		assertEquals(_equipment, player.equipment());
	}
	
	@Test
	public void addAttribute() {
		IPlayer player = new Player(_name);
		assertEquals(0, player.attributes().size());
		
		player.addAttribute(_attribute1);
		assertEquals(1, player.attributes().size());
		assertEquals(true, player.attributes().contains(_attribute1));
		
		player.addAttribute(_attribute2);
		assertEquals(2, player.attributes().size());
		assertEquals(true, player.attributes().contains(_attribute2));
	}
	
	@Test
	public void addAttributes() {
		IPlayer player = new Player(_name);
		assertEquals(0, player.attributes().size());
		
		_attributes = generateAttributes();
		player.addAttributes(_attributes);
		assertEquals(2, player.attributes().size());
		assertEquals(true, player.attributes().contains(_attribute1));
		assertEquals(true, player.attributes().contains(_attribute2));
	}
	
	@Test
	public void removeAttribute() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.attributes().size());
		
		player.removeAttribute(_attribute1);
		assertEquals(1, player.attributes().size());
		assertEquals(false, player.attributes().contains(_attribute1));
		
		player.removeAttribute(_attribute2);
		assertEquals(0, player.attributes().size());
		assertEquals(false, player.attributes().contains(_attribute2));
	}
	
	@Test
	public void removeAttributes() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.attributes().size());
		
		List<IAttribute> attribs = generateAttributes();
		player.removeAttributes(attribs);
		assertEquals(0, player.attributes().size());
		assertEquals(false, player.attributes().contains(_attribute1));
		assertEquals(false, player.attributes().contains(_attribute2));
	}
	
	@Test
	public void clearAttributes() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.attributes().size());
		
		player.clearAttributes();
		assertEquals(0, player.attributes().size());
		assertEquals(false, player.attributes().contains(_attribute1));
		assertEquals(false, player.attributes().contains(_attribute2));
	}
	
	@Test
	public void addCharacteristic() {
		IPlayer player = new Player(_name);
		assertEquals(0, player.characteristics().size());
		
		player.addCharacteristic(_characteristic1);
		assertEquals(1, player.characteristics().size());
		assertEquals(true, player.characteristics().contains(_characteristic1));
		
		player.addCharacteristic(_characteristic2);
		assertEquals(2, player.characteristics().size());
		assertEquals(true, player.characteristics().contains(_characteristic2));
	}
	
	@Test
	public void addCharacteristics() {
		IPlayer player = new Player(_name);
		assertEquals(0, player.characteristics().size());
		
		_characteristics = generateCharacteristics();
		player.addCharacteristics(_characteristics);
		assertEquals(2, player.characteristics().size());
		assertEquals(true, player.characteristics().contains(_characteristic1));
		assertEquals(true, player.characteristics().contains(_characteristic2));
	}
	
	@Test
	public void removeCharacteristic() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.characteristics().size());
		
		player.removeCharacteristic(_characteristic1);
		assertEquals(1, player.characteristics().size());
		assertEquals(false, player.characteristics().contains(_characteristic1));
		
		player.removeCharacteristic(_characteristic2);
		assertEquals(0, player.characteristics().size());
		assertEquals(false, player.characteristics().contains(_characteristic2));
	}
	
	@Test
	public void removeCharacteristics() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.characteristics().size());
		
		List<ICharacteristic> characters = generateCharacteristics();
		player.removeCharacteristics(characters);
		assertEquals(0, player.characteristics().size());
		assertEquals(false, player.characteristics().contains(_characteristic1));
		assertEquals(false, player.characteristics().contains(_characteristic2));
	}
	
	@Test
	public void clearCharacteristics() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.characteristics().size());
		
		player.clearCharacteristics();
		assertEquals(0, player.characteristics().size());
		assertEquals(false, player.characteristics().contains(_characteristic1));
		assertEquals(false, player.characteristics().contains(_characteristic2));
	}
	
	@Test
	public void addBodyPart() {
		IPlayer player = new Player(_name);
		assertEquals(0, player.bodyParts().size());
		
		player.addBodyPart(_bodyPart1);
		assertEquals(1, player.bodyParts().size());
		assertEquals(true, player.bodyParts().contains(_bodyPart1));
		
		player.addBodyPart(_bodyPart2);
		assertEquals(2, player.bodyParts().size());
		assertEquals(true, player.bodyParts().contains(_bodyPart2));
	}
	
	@Test
	public void addBodyParts() {
		IPlayer player = new Player(_name);
		assertEquals(0, player.bodyParts().size());
		
		_bodyParts = generateBodyParts();
		player.addBodyParts(_bodyParts);
		assertEquals(2, player.bodyParts().size());
		assertEquals(true, player.bodyParts().contains(_bodyPart1));
		assertEquals(true, player.bodyParts().contains(_bodyPart2));
	}
	
	@Test
	public void removeBodyPart() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.bodyParts().size());
		
		player.removeBodyPart(_bodyPart1);
		assertEquals(1, player.bodyParts().size());
		assertEquals(false, player.bodyParts().contains(_bodyPart1));
		
		player.removeBodyPart(_bodyPart2);
		assertEquals(0, player.bodyParts().size());
		assertEquals(false, player.bodyParts().contains(_bodyPart2));
	}
	
	@Test
	public void removeBodyParts() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.bodyParts().size());
		
		List<IBodyPart> bods = generateBodyParts();
		player.removeBodyParts(bods);
		assertEquals(0, player.bodyParts().size());
		assertEquals(false, player.bodyParts().contains(_bodyPart1));
		assertEquals(false, player.bodyParts().contains(_bodyPart2));
	}
	
	@Test
	public void clearBodyParts() {
		_attributes = generateAttributes();
		_characteristics = generateCharacteristics();
		_bodyParts = generateBodyParts();
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(2, player.bodyParts().size());
		
		player.clearBodyParts();
		assertEquals(0, player.bodyParts().size());
		assertEquals(false, player.bodyParts().contains(_bodyPart1));
		assertEquals(false, player.bodyParts().contains(_bodyPart2));
	}
	
	@Test
	public void setName() {
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(_name, player.name());
		player.name(_name2);
		assertEquals(_name2, player.name());
	}
	
	@Test
	public void setContent() {
		IPlayer player = new Player(_name, _content, _inventory, _equipment, _attributes, _characteristics, _bodyParts);
		assertEquals(_content, player.content());
		assertThat(player.content(), instanceOf(BufferedImage.class));
		player.content(_content2);
		assertEquals(_content2, player.content());
		assertThat(player.content(), instanceOf(String.class));
	}
	
	@Test
	public void attributeAddedWithListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		player.addAttribute(new Attribute("Name", "Value"));
		assertEquals(1, listener.added());
	}
	
	@Test
	public void attributesAddedWithListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		List<IAttribute> attributes = new ArrayList<IAttribute>();
		attributes.add(new Attribute("Att1", "Val1"));
		attributes.add(new Attribute("Att2", "Val2"));
		attributes.add(new Attribute("Att3", "Val3"));
		player.addAttributes(attributes);
		assertEquals(3, listener.added());
	}
	
	@Test
	public void attributeAddedWithNoListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		player.removeAttributeChangedListener(listener);
		player.addAttribute(new Attribute("Name", "Value"));
		assertEquals(0, listener.added());
	}
	
	@Test
	public void attributeRemovedWithListener() {
		IAttribute att = new Attribute("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(Arrays.asList(att)), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		player.removeAttribute(att);
		assertEquals(1, listener.removed());
	}
	
	@Test
	public void attributesRemovedWithListener() {
		List<IAttribute> attributes = new ArrayList<IAttribute>();
		attributes.add(new Attribute("Att1", "Val1"));
		attributes.add(new Attribute("Att2", "Val2"));
		attributes.add(new Attribute("Att3", "Val3"));
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), attributes, new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		player.removeAttributes(new ArrayList<IAttribute>(attributes));
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void attributesClearedWithListener() {
		List<IAttribute> attributes = new ArrayList<IAttribute>();
		attributes.add(new Attribute("Att1", "Val1"));
		attributes.add(new Attribute("Att2", "Val2"));
		attributes.add(new Attribute("Att3", "Val3"));
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), attributes, new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		player.clearAttributes();
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void attributeRemovedWithNoListener() {
		IAttribute att = new Attribute("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(Arrays.asList(att)), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		player.removeAttributeChangedListener(listener);
		player.removeAttribute(att);
		assertEquals(0, listener.removed());
	}
	
	@Test
	public void attributeChangedWithListener() {
		IAttribute att = new Attribute("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(Arrays.asList(att)), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		att.value("Value2");
		assertEquals(1, listener.changed());
	}
	
	@Test
	public void attributeChangedWithoutListener() {
		IAttribute att = new Attribute("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(Arrays.asList(att)), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IAttribute> listener = new TestChangeListener<IAttribute>();
		player.addAttributeChangeListener(listener);
		player.removeAttributeChangedListener(listener);
		att.value("Value2");
		assertEquals(0, listener.changed());
	}
	
	@Test
	public void characteristicAddedWithListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		player.addCharacteristic(new Characteristic("Name", "Value"));
		assertEquals(1, listener.added());
	}
	
	@Test
	public void characteristicsAddedWithListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		characteristics.add(new Characteristic("Att1", "Val1"));
		characteristics.add(new Characteristic("Att2", "Val2"));
		characteristics.add(new Characteristic("Att3", "Val3"));
		player.addCharacteristics(characteristics);
		assertEquals(3, listener.added());
	}
	
	@Test
	public void characteristicAddedWithNoListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		player.removeCharacteristicChangeListener(listener);
		player.addCharacteristic(new Characteristic("Name", "Value"));
		assertEquals(0, listener.added());
	}
	
	@Test
	public void characteristicRemovedWithListener() {
		ICharacteristic charc = new Characteristic("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(Arrays.asList(charc)), new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		player.removeCharacteristic(charc);
		assertEquals(1, listener.removed());
	}
	
	@Test
	public void characteristicsRemovedWithListener() {
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		characteristics.add(new Characteristic("Att1", "Val1"));
		characteristics.add(new Characteristic("Att2", "Val2"));
		characteristics.add(new Characteristic("Att3", "Val3"));
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), characteristics, new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		player.removeCharacteristics(new ArrayList<ICharacteristic>(characteristics));
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void characteristicsClearedWithListener() {
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		characteristics.add(new Characteristic("Att1", "Val1"));
		characteristics.add(new Characteristic("Att2", "Val2"));
		characteristics.add(new Characteristic("Att3", "Val3"));
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), characteristics, new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		player.clearCharacteristics();
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void characteristicRemovedWithNoListener() {
		ICharacteristic charc = new Characteristic("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(Arrays.asList(charc)), new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		player.removeCharacteristicChangeListener(listener);
		player.removeCharacteristic(charc);
		assertEquals(0, listener.removed());
	}
	
	@Test
	public void characteristicChangedWithListener() {
		ICharacteristic charc = new Characteristic("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(Arrays.asList(charc)), new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		charc.value("Value2");
		assertEquals(1, listener.changed());
	}
	
	@Test
	public void characteristicChangedWithoutListener() {
		ICharacteristic charc = new Characteristic("Name", "Value");
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(Arrays.asList(charc)), new ArrayList<IBodyPart>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		player.addCharacteristicChangeListener(listener);
		player.removeCharacteristicChangeListener(listener);
		charc.value("Value2");
		assertEquals(0, listener.changed());
	}
	
	@Test
	public void bodyPartAddedWithListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		player.addBodyPart(new BodyPart("Name", "Description", new ArrayList<ICharacteristic>()));
		assertEquals(1, listener.added());
	}
	
	@Test
	public void bodyPartsAddedWithListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		List<IBodyPart> bodyParts = new ArrayList<IBodyPart>();
		bodyParts.add(new BodyPart("Att1", "Val1", new ArrayList<ICharacteristic>()));
		bodyParts.add(new BodyPart("Att2", "Val2", new ArrayList<ICharacteristic>()));
		bodyParts.add(new BodyPart("Att3", "Val3", new ArrayList<ICharacteristic>()));
		player.addBodyParts(bodyParts);
		assertEquals(3, listener.added());
	}
	
	@Test
	public void bodyPartAddedWithNoListener() {
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>());
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		player.removeBodyPartChangeListener(listener);
		player.addBodyPart(new BodyPart("Name", "Description", new ArrayList<ICharacteristic>()));
		assertEquals(0, listener.added());
	}
	
	@Test
	public void bodyPartRemovedWithListener() {
		IBodyPart bodyPart = new BodyPart("Name", "Description", new ArrayList<ICharacteristic>());
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>(Arrays.asList(bodyPart)));
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		player.removeBodyPart(bodyPart);
		assertEquals(1, listener.removed());
	}
	
	@Test
	public void bodyPartsRemovedWithListener() {
		List<IBodyPart> bodyParts = new ArrayList<IBodyPart>();
		bodyParts.add(new BodyPart("Att1", "Val1", new ArrayList<ICharacteristic>()));
		bodyParts.add(new BodyPart("Att2", "Val2", new ArrayList<ICharacteristic>()));
		bodyParts.add(new BodyPart("Att3", "Val3", new ArrayList<ICharacteristic>()));
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), bodyParts);
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		player.removeBodyParts(new ArrayList<IBodyPart>(bodyParts));
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void bodyPartsClearedWithListener() {
		List<IBodyPart> bodyParts = new ArrayList<IBodyPart>();
		bodyParts.add(new BodyPart("Att1", "Val1", new ArrayList<ICharacteristic>()));
		bodyParts.add(new BodyPart("Att2", "Val2", new ArrayList<ICharacteristic>()));
		bodyParts.add(new BodyPart("Att3", "Val3", new ArrayList<ICharacteristic>()));
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), bodyParts);
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		player.clearBodyParts();
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void bodyPartRemovedWithNoListener() {
		IBodyPart bodyPart = new BodyPart("Name", "Value", new ArrayList<ICharacteristic>());
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>(Arrays.asList(bodyPart)));
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		player.removeBodyPartChangeListener(listener);
		player.removeBodyPart(bodyPart);
		assertEquals(0, listener.removed());
	}
	
	@Test
	public void bodyPartChangedWithListener() {
		IBodyPart bodyPart = new BodyPart("Name", "Value", new ArrayList<ICharacteristic>());
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>(Arrays.asList(bodyPart)));
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		bodyPart.addCharacteristic(new Characteristic("Name", "Value"));
		assertEquals(1, listener.changed());
	}
	
	@Test
	public void bodyPartChangedWithoutListener() {
		IBodyPart bodyPart = new BodyPart("Name", "Value", new ArrayList<ICharacteristic>());
		IPlayer player = new Player("Player1", null, new Inventory(), new Equipment(), new ArrayList<IAttribute>(), new ArrayList<ICharacteristic>(), new ArrayList<IBodyPart>(Arrays.asList(bodyPart)));
		TestChangeListener<IBodyPart> listener = new TestChangeListener<IBodyPart>();
		player.addBodyPartChangeListener(listener);
		player.removeBodyPartChangeListener(listener);
		bodyPart.addCharacteristic(new Characteristic("Name", "Value"));
		assertEquals(0, listener.changed());
	}
	
	private List<IAttribute> generateAttributes() {
		List<IAttribute> list = new ArrayList<IAttribute>();
		list.add(_attribute1);
		list.add(_attribute2);
		return list;
	}
	
	private List<ICharacteristic> generateCharacteristics() {
		List<ICharacteristic> list = new ArrayList<ICharacteristic>();
		list.add(_characteristic1);
		list.add(_characteristic2);
		return list;
	}
	
	private List<IBodyPart> generateBodyParts() {
		List<IBodyPart> list = new ArrayList<IBodyPart>();
		list.add(_bodyPart1);
		list.add(_bodyPart2);
		return list;
	}
}