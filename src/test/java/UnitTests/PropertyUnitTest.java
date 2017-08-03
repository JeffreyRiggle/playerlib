package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import playerlib.items.IProperty;
import playerlib.items.Property;

public class PropertyUnitTest {

	private final String _name = "Consumable";
	private final String _name2 = "Health up";
	private final String _sValue1 = "-1";
	private final String _sValue2 = "-2";
	private final Integer _iValue = 42;
	private final Double _dValue = 2.3;
	private final Boolean _bValue = true;
	private final String _description = "Can be consumed, consuming this will get rid of it";
	private final String _description2 = "Increases a players health";
	
	@Test
	public void testCreateMinimalProperty() {
		IProperty property = new Property(_name, _sValue1);
		assertEquals(_name, property.name());
		assertEquals(_sValue1, property.value());
	}
	
	@Test
	public void testCreateFullProperty() {
		IProperty property = new Property(_name, _description, _sValue1);
		assertEquals(_name, property.name());
		assertEquals(_sValue1, property.value());
		assertEquals(_description, property.description());
	}
	
	@Test
	public void testSetValue() {
		IProperty property = new Property(_name, _description, _sValue1);
		SomeTestClass testC = new SomeTestClass(true);
		
		assertEquals(_sValue1, property.value());
		property.<String>value(_sValue2);
		assertEquals(_sValue2, property.value());
		property.<Integer>value(_iValue);
		assertEquals(_iValue, property.value());
		property.<Double>value(_dValue);
		assertEquals(_dValue, property.value());
		property.<Boolean>value(_bValue);
		assertEquals(_bValue, property.value());
		
		property.<SomeTestClass>value(testC);
		assertEquals(true, property.<SomeTestClass>value().retVal());
	}

	@Test
	public void testSetName() {
		IProperty property = new Property(_name, _description, _sValue1);
		assertEquals(_name, property.name());
		property.name(_name2);
		assertEquals(_name2, property.name());
	}
	
	@Test
	public void testSetDescription() {
		IProperty property = new Property(_name, _description, _sValue1);
		assertEquals(_description, property.description());
		property.description(_description2);
		assertEquals(_description2, property.description());
	}
	
	@Test
	public void testAddValueListener() {
		IProperty property = new Property(_name, _description, _sValue1);
		ValueConsumer<String, IProperty> consumer = new ValueConsumer<String, IProperty>(_sValue2);
		property.addChangeListener(consumer);
		property.value(_sValue2);
		assertEquals(1, consumer.changeCount());
	}
	
	@Test
	public void testVerifyChange() {
		IProperty property = new Property(_name, _description, _sValue1);
		ValueConsumer<String, IProperty> consumer = new ValueConsumer<String, IProperty>(_sValue2);
		property.addChangeListener(consumer);
		property.value(_sValue2);
		assertTrue(consumer.matched());
	}
	
	@Test
	public void testRemoveValueListener() {
		IProperty property = new Property(_name, _description, _sValue1);
		ValueConsumer<String, IProperty> consumer = new ValueConsumer<String, IProperty>(_sValue2);
		property.addChangeListener(consumer);
		property.value(_sValue2);
		assertEquals(1, consumer.changeCount());
		property.removeChangeListener(consumer);
		property.value(_sValue1);
		assertEquals(1, consumer.changeCount());
	}
	
	public class SomeTestClass {
		private boolean _bool;
		
		public SomeTestClass(Boolean bool) {
			_bool = bool;
		}
		
		public boolean retVal() {
			return _bool;
		}
	}
}