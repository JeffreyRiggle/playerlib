package UnitTests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import playerlib.items.*;

public class ItemUnitTest {

	private final String _name = "Potion";
	private final String _name2 = "Ether";
	private final String _description = "";
	private final String _description2 = "Increases mana";
	private final BufferedImage _content = new BufferedImage(5,5,5);
	private final String _content2 = "Some content";
	private List<IProperty> _properties = new ArrayList<IProperty>();
	private final String _p1Name = "Consumable";
	private final Integer _p1Value = -1;
	private final String _p2Name = "Breakable";
	private final Boolean _p2Value = true;
	private final String _p3Name = "Hp Up";
	private final Integer _p3Value = 20;
	
	@Test
	public void testCreateMinimalItem() {
		IItem item = new Item(_name);
		assertEquals(_name, item.name());
	}
	
	@Test
	public void testCreateFullItem() {
		IProperty prop1 = new Property(_p1Name, _p1Value);
		IProperty prop2 = new Property(_p2Name, _p2Value);
		
		_properties.add(prop1);
		_properties.add(prop2);
		IItem item = new Item(_properties, _name, _description, _content);
		
		assertEquals(prop1, item.properties().get(0));
		assertEquals(prop2, item.properties().get(1));
		assertEquals(_name, item.name());
		assertEquals(_description, item.description());
		assertEquals(_content, item.content());
		
		_properties.clear();
	}
	
	@Test
	public void testAddProperty() {
		IProperty prop1 = new Property(_p1Name, _p1Value);
		IProperty prop2 = new Property(_p2Name, _p2Value);
		IProperty prop3 = new Property(_p3Name, _p3Value);
		
		_properties.add(prop1);
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(prop1, item.properties().get(0));
		assertEquals(1, item.properties().size());
		
		item.addProperty(prop2);
		assertEquals(prop2, item.properties().get(1));
		assertEquals(2, item.properties().size());
		
		item.addProperty(prop3);
		assertEquals(prop3, item.properties().get(2));
		assertEquals(3, item.properties().size());
		
		_properties.clear();
	}
	
	@Test
	public void testAddProperties() {
		
		IProperty prop1 = new Property(_p1Name, _p1Value);
		IProperty prop2 = new Property(_p2Name, _p2Value);
		IProperty prop3 = new Property(_p3Name, _p3Value);
		List<IProperty> props = new ArrayList<IProperty>();
		props.add(prop2);
		props.add(prop3);
		
		_properties.add(prop1);
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(prop1, item.properties().get(0));
		assertEquals(1, item.properties().size());
		
		item.addProperties(props);
		assertEquals(prop2, item.properties().get(1));
		assertEquals(prop3, item.properties().get(2));
		assertEquals(3, item.properties().size());
		
		_properties.clear();
	}
	
	@Test
	public void testClearProperties() {
		IProperty prop1 = new Property(_p1Name, _p1Value);
		IProperty prop2 = new Property(_p2Name, _p2Value);
		IProperty prop3 = new Property(_p3Name, _p3Value);
		
		_properties.add(prop1);
		_properties.add(prop2);
		_properties.add(prop3);
		
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(3, item.properties().size());
		
		item.clearProperties();
		assertEquals(0, item.properties().size());
		
		_properties.clear();
	}
	
	@Test
	public void testRemoveProperty() {
		IProperty prop1 = new Property(_p1Name, _p1Value);
		IProperty prop2 = new Property(_p2Name, _p2Value);
		IProperty prop3 = new Property(_p3Name, _p3Value);
		
		_properties.add(prop1);
		_properties.add(prop2);
		_properties.add(prop3);
		
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(3, item.properties().size());
		
		item.removeProperty(prop1);
		assertEquals(2, item.properties().size());
		item.removeProperty(prop2);
		assertEquals(1, item.properties().size());
		item.removeProperty(prop3);
		assertEquals(0, item.properties().size());
		
		_properties.clear();
	}
	
	@Test
	public void testRemoveProperties() {
		IProperty prop1 = new Property(_p1Name, _p1Value);
		IProperty prop2 = new Property(_p2Name, _p2Value);
		IProperty prop3 = new Property(_p3Name, _p3Value);
		
		_properties.add(prop1);
		_properties.add(prop2);
		_properties.add(prop3);
		
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(3, item.properties().size());
		
		item.removeProperties(_properties);
		assertEquals(0, item.properties().size());
		
		_properties.clear();
	}
	
	@Test
	public void testGetProperty() {
		IProperty prop1 = new Property(_p1Name, _p1Value);
		IProperty prop2 = new Property(_p2Name, _p2Value);
		IProperty prop3 = new Property(_p3Name, _p3Value);
		
		_properties.add(prop1);
		_properties.add(prop2);
		_properties.add(prop3);

		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(prop1, item.getProperty(_p1Name));
		assertEquals(prop2, item.getProperty(_p2Name));
		assertEquals(prop3, item.getProperty(_p3Name));
		assertEquals(null, item.getProperty("fudruckers"));
	}
	
	@Test
	public void testSetName() {
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(_name, item.name());
		item.name(_name2);
		assertEquals(_name2, item.name());
	}
	
	@Test
	public void testSetDescription() {
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(_description, item.description());
		item.description(_description2);
		assertEquals(_description2, item.description());
	}
	
	@Test
	public void testSetContent() {
		IItem item = new Item(_properties, _name, _description, _content);
		assertEquals(_content, item.content());
		assertThat(item.content(), instanceOf(BufferedImage.class));
		item.content(_content2);
		assertEquals(_content2, item.content());
		assertThat(item.content(), instanceOf(String.class));
	}
	
	@Test
	public void propertyAddedWithListener() {
		IItem item = new Item(new ArrayList<IProperty>(), "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		item.addProperty(new Property("Name", "Value"));
		assertEquals(1, listener.added());
	}
	
	@Test
	public void propertysAddedWithListener() {
		IItem item = new Item(new ArrayList<IProperty>(), "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		List<IProperty> properties = new ArrayList<IProperty>();
		properties.add(new Property("Att1", "Val1"));
		properties.add(new Property("Att2", "Val2"));
		properties.add(new Property("Att3", "Val3"));
		item.addProperties(properties);
		assertEquals(3, listener.added());
	}
	
	@Test
	public void propertyAddedWithNoListener() {
		IItem item = new Item(new ArrayList<IProperty>(), "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		item.removePropertyChangeListener(listener);
		item.addProperty(new Property("Name", "Value"));
		assertEquals(0, listener.added());
	}
	
	@Test
	public void propertyRemovedWithListener() {
		IProperty prop = new Property("Name", "Value");
		IItem item = new Item(new ArrayList<IProperty>(Arrays.asList(prop)), "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		item.removeProperty(prop);
		assertEquals(1, listener.removed());
	}
	
	@Test
	public void propertiesRemovedWithListener() {
		List<IProperty> props = new ArrayList<IProperty>();
		props.add(new Property("Att1", "Val1"));
		props.add(new Property("Att2", "Val2"));
		props.add(new Property("Att3", "Val3"));
		IItem item = new Item(props, "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		item.removeProperties(new ArrayList<IProperty>(props));
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void propertyClearedWithListener() {
		List<IProperty> props = new ArrayList<IProperty>();
		props.add(new Property("Att1", "Val1"));
		props.add(new Property("Att2", "Val2"));
		props.add(new Property("Att3", "Val3"));
		IItem item = new Item(props, "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		item.clearProperties();
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void propertyRemovedWithNoListener() {
		IProperty prop = new Property("Name", "Value");
		IItem item = new Item(new ArrayList<IProperty>(Arrays.asList(prop)), "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		item.removePropertyChangeListener(listener);
		item.removeProperty(prop);
		assertEquals(0, listener.removed());
	}
	
	@Test
	public void propertyChangedWithListener() {
		IProperty prop = new Property("Name", "Value");
		IItem item = new Item(new ArrayList<IProperty>(Arrays.asList(prop)), "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		prop.value("Value2");
		assertEquals(1, listener.changed());
	}
	
	@Test
	public void propertyChangedWithoutListener() {
		IProperty prop = new Property("Name", "Value");
		IItem item = new Item(new ArrayList<IProperty>(Arrays.asList(prop)), "Test", "TestItem", null);
		TestChangeListener<IProperty> listener = new TestChangeListener<IProperty>();
		item.addPropertyChangeListener(listener);
		item.removePropertyChangeListener(listener);
		prop.value("Value2");
		assertEquals(0, listener.changed());
	}
}