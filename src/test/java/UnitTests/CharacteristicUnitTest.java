package UnitTests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.awt.image.BufferedImage;

import org.junit.Test;

import playerlib.characteristics.Characteristic;
import playerlib.characteristics.ICharacteristic;

public class CharacteristicUnitTest {

	private final String _name = "Hair";
	private final String _name2 = "Skin";
	private final String _value = "Brown";
	private final String _value2 = "Blue";
	private final String _description = "Something that covers the human body.";
	private final String _description2 = "A protective layer around the human skeleton";
	private final BufferedImage _content = new BufferedImage(10,10,10);
	private final String _content2 = "Some Content";
	
	@Test
	public void testCreateMinimalCharacteristic() {
		ICharacteristic characteristic = new Characteristic(_name, _value);
		assertEquals(_name, characteristic.name());
		assertEquals(_value, characteristic.value());
	}
	
	@Test
	public void testCreateFullCharacteristic() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		assertEquals(_name, characteristic.name());
		assertEquals(_value, characteristic.value());
		assertEquals(_description, characteristic.description());
		assertEquals(_content, characteristic.content());
	}

	@Test
	public void testSetValue() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		assertEquals(_value, characteristic.value());
		characteristic.value(_value2);
		assertEquals(_value2, characteristic.value());
	}
	
	@Test
	public void testSetName() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		assertEquals(_name, characteristic.name());
		characteristic.name(_name2);
		assertEquals(_name2, characteristic.name());
	}
	
	@Test
	public void testSetDescription() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		assertEquals(_description, characteristic.description());
		characteristic.description(_description2);
		assertEquals(_description2, characteristic.description());
	}
	
	@Test
	public void testSetContent() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		assertEquals(_content, characteristic.content());
		assertThat(characteristic.content(), instanceOf(BufferedImage.class));
		characteristic.content(_content2);
		assertThat(characteristic.content(), instanceOf(String.class));
		assertEquals(_content2, characteristic.content());
	}
	
	@Test
	public void testAddValueListener() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		ValueConsumer<String, ICharacteristic> consumer = new ValueConsumer<String, ICharacteristic>(_value2);
		characteristic.addChangeListener(consumer);
		characteristic.value(_value2);
		assertEquals(1, consumer.changeCount());
	}
	
	@Test
	public void testVerifyChange() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		ValueConsumer<String, ICharacteristic> consumer = new ValueConsumer<String, ICharacteristic>(_value2);
		characteristic.addChangeListener(consumer);
		characteristic.value(_value2);
		assertTrue(consumer.matched());
	}
	
	@Test
	public void testRemoveValueListener() {
		ICharacteristic characteristic = new Characteristic(_name, _value, _description, _content);
		ValueConsumer<String, ICharacteristic> consumer = new ValueConsumer<String, ICharacteristic>(_value2);
		characteristic.addChangeListener(consumer);
		characteristic.value(_value2);
		assertEquals(1, consumer.changeCount());
		characteristic.removeChangeListener(consumer);
		characteristic.value(_value);
		assertEquals(1, consumer.changeCount());
	}
}