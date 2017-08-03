package UnitTests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.awt.image.BufferedImage;

import org.junit.Test;

import playerlib.attributes.Attribute;
import playerlib.attributes.IAttribute;

public class AttributeUnitTest {

	private final String _name = "Health Points";
	private final String _name2 = "Mana Points";
	private final String _description = "A counter that reperesents the amount of damage a player can take";
	private final String _description2 = "The amount of magic a player can cast";
	private final String _value = "100";
	private final Integer _value2 = 1000;
	private final BufferedImage _content = new BufferedImage(4,4,4);
	private final String _content2 = "Some content";
	
	@Test
	public void testCreateMinimalAttribute() {
		IAttribute attribute = new Attribute(_name, _value);
		assertEquals(_name, attribute.name());
		assertEquals(_value, attribute.value());
	}
	
	@Test
	public void testCreateFullAttribute() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		assertEquals(_name, attribute.name());
		assertEquals(_description, attribute.description());
		assertEquals(_value, attribute.value());
		assertEquals(_content, attribute.content());
	}
	
	@Test
	public void testSetValue() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		assertEquals(_value, attribute.value());
		assertThat(attribute.value(), instanceOf(String.class));
		attribute.value(_value2);
		assertEquals(_value2, attribute.value());
		assertThat(attribute.value(), instanceOf(Integer.class));
	}
	
	@Test
	public void testSetName() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		assertEquals(_name, attribute.name());
		attribute.name(_name2);
		assertEquals(_name2, attribute.name());		
	}
	
	@Test
	public void testSetDescription() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		assertEquals(_description, attribute.description());
		attribute.description(_description2);
		assertEquals(_description2, attribute.description());
	}
	
	@Test
	public void testSetContent() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		assertEquals(_content, attribute.content());
		assertThat(attribute.content(), instanceOf(BufferedImage.class));
		attribute.content(_content2);
		assertThat(attribute.content(), instanceOf(String.class));
		assertEquals(_content2, attribute.content());
	}
	
	@Test
	public void testAddValueListener() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		ValueConsumer<Integer, IAttribute> consumer = new ValueConsumer<Integer, IAttribute>(_value2);
		attribute.addChangeListener(consumer);
		attribute.value(_value2);
		assertEquals(1, consumer.changeCount());
	}
	
	@Test
	public void testVerifyChange() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		ValueConsumer<Integer, IAttribute> consumer = new ValueConsumer<Integer, IAttribute>(_value2);
		attribute.addChangeListener(consumer);
		attribute.value(_value2);
		assertTrue(consumer.matched());
	}
	
	@Test
	public void testRemoveValueListener() {
		IAttribute attribute = new Attribute(_name, _description, _value, _content);
		ValueConsumer<Integer, IAttribute> consumer = new ValueConsumer<Integer, IAttribute>(_value2);
		attribute.addChangeListener(consumer);
		attribute.value(_value2);
		assertEquals(1, consumer.changeCount());
		attribute.removeChangeListener(consumer);
		attribute.value(_value);
		assertEquals(1, consumer.changeCount());
	}
}