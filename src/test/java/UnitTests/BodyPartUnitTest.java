package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import playerlib.characteristics.Characteristic;
import playerlib.characteristics.ICharacteristic;
import playerlib.equipment.*;

public class BodyPartUnitTest {

	private final String _name = "Leg";
	private final String _bname = "Arm";
	private final String _description = "An appendage off of the lower part of the torso";
	private final String _description2 = "An appendage off of the middle part of the torso";
	private List<ICharacteristic> _characteristics;
	private final String _name1 = "Scar";
	private final String _name2 = "Tattoo";
	private final ICharacteristic _characteristic1 = new Characteristic(_name1, "Long", "A marking on the leg.", null);
	private final ICharacteristic _characteristic2 = new Characteristic(_name2, "dragon", "A ink marking under the skin.", null);
	
	@Test
	public void testCreateMinimalBodyPart() {
		IBodyPart bodyPart = new BodyPart(_name);
		assertEquals(_name, bodyPart.name());
	}
	
	@Test
	public void testCreateFullBodyPart() {
		_characteristics = new ArrayList<ICharacteristic>();
		
		_characteristics.add(_characteristic1);
		_characteristics.add(_characteristic2);
		
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		assertEquals(_name, bodyPart.name());
		assertEquals(_description, bodyPart.description());
		assertEquals(_characteristics, bodyPart.getCharacteristics());
		assertEquals(2, bodyPart.getCharacteristics().size());
		
		_characteristics.clear();
	}
	
	@Test
	public void testAddCharacteristic() {
		_characteristics = new ArrayList<ICharacteristic>();
		
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		
		bodyPart.addCharacteristic(_characteristic1);
		assertEquals(1, bodyPart.getCharacteristics().size());
		assertEquals(true, bodyPart.getCharacteristics().contains(_characteristic1));
		
		bodyPart.addCharacteristic(_characteristic2);
		assertEquals(2, bodyPart.getCharacteristics().size());
		assertEquals(true, bodyPart.getCharacteristics().contains(_characteristic2));
		
		_characteristics.clear();
	}
	
	@Test
	public void testAddCharacteristics() {
		_characteristics = new ArrayList<ICharacteristic>();
		
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		characteristics.add(_characteristic1);
		characteristics.add(_characteristic2);
		
		bodyPart.addCharacteristics(characteristics);
		assertEquals(2, bodyPart.getCharacteristics().size());
		assertEquals(true, bodyPart.getCharacteristics().contains(_characteristic1));
		assertEquals(true, bodyPart.getCharacteristics().contains(_characteristic2));
		
		_characteristics.clear();
	}
	
	@Test
	public void testRemoveCharacteristic() {
		_characteristics = new ArrayList<ICharacteristic>();
		_characteristics.add(_characteristic1);
		_characteristics.add(_characteristic2);
		
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		
		assertEquals(2, bodyPart.getCharacteristics().size());
		
		bodyPart.removeCharacteristic(_characteristic1);
		
		assertEquals(1, bodyPart.getCharacteristics().size());
		assertEquals(true, bodyPart.getCharacteristics().contains(_characteristic2));
		
		bodyPart.removeCharacteristic(_characteristic2);
		assertEquals(0, bodyPart.getCharacteristics().size());
		
		_characteristics.clear();
	}
	
	@Test
	public void testRemoveCharacteristics() {
		_characteristics = new ArrayList<ICharacteristic>();
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		_characteristics.add(_characteristic1);
		_characteristics.add(_characteristic2);
		characteristics.addAll(_characteristics);
		
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		assertEquals(2, bodyPart.getCharacteristics().size());
		
		bodyPart.removeCharacteristics(characteristics);
		assertEquals(0, bodyPart.getCharacteristics().size());
		
		_characteristics.clear();
	}
	
	@Test
	public void testGetCharacteristic() {
		_characteristics = new ArrayList<ICharacteristic>();
		_characteristics.add(_characteristic1);
		_characteristics.add(_characteristic2);
		
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		
		assertEquals(_characteristic1, bodyPart.getCharacteristic(_name1));
		assertEquals(_characteristic2, bodyPart.getCharacteristic(_name2));
	}
	
	@Test
	public void testClearCharacteristics() {
		_characteristics = new ArrayList<ICharacteristic>();
		_characteristics.add(_characteristic1);
		_characteristics.add(_characteristic2);
		
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		
		assertEquals(2, bodyPart.getCharacteristics().size());
		bodyPart.clearCharacteristics();
		assertEquals(0, bodyPart.getCharacteristics().size());
	}
	
	@Test
	public void testSetName() {
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		assertEquals(_name, bodyPart.name());
		bodyPart.name(_bname);
		assertEquals(_bname, bodyPart.name());
	}
	
	@Test
	public void testSetDescription() {
		IBodyPart bodyPart = new BodyPart(_name, _description, _characteristics);
		assertEquals(_description, bodyPart.description());
		bodyPart.description(_description2);
		assertEquals(_description2, bodyPart.description());
	}
	
	@Test
	public void characteristicAddedWithListener() {
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		bodyPart.addCharacteristic(new Characteristic("Name", "Value"));
		assertEquals(1, listener.added());
	}
	
	@Test
	public void characteristicsAddedWithListener() {
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		characteristics.add(new Characteristic("Att1", "Val1"));
		characteristics.add(new Characteristic("Att2", "Val2"));
		characteristics.add(new Characteristic("Att3", "Val3"));
		bodyPart.addCharacteristics(characteristics);
		assertEquals(3, listener.added());
	}
	
	@Test
	public void characteristicAddedWithNoListener() {
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>());
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		bodyPart.removeCharacteristicChangeListener(listener);
		bodyPart.addCharacteristic(new Characteristic("Name", "Value"));
		assertEquals(0, listener.added());
	}
	
	@Test
	public void characteristicRemovedWithListener() {
		ICharacteristic character = new Characteristic("Name", "Value");
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>(Arrays.asList(character)));
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		bodyPart.removeCharacteristic(character);
		assertEquals(1, listener.removed());
	}
	
	@Test
	public void characteristicsRemovedWithListener() {
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		characteristics.add(new Characteristic("Att1", "Val1"));
		characteristics.add(new Characteristic("Att2", "Val2"));
		characteristics.add(new Characteristic("Att3", "Val3"));
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", characteristics);
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		bodyPart.removeCharacteristics(new ArrayList<ICharacteristic>(characteristics));
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void characteristicClearedWithListener() {
		List<ICharacteristic> characteristics = new ArrayList<ICharacteristic>();
		characteristics.add(new Characteristic("Att1", "Val1"));
		characteristics.add(new Characteristic("Att2", "Val2"));
		characteristics.add(new Characteristic("Att3", "Val3"));
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", characteristics);
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		bodyPart.clearCharacteristics();
		assertEquals(3, listener.removed());
	}
	
	@Test
	public void characteristicRemovedWithNoListener() {
		ICharacteristic character = new Characteristic("Name", "Value");
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>(Arrays.asList(character)));
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		bodyPart.removeCharacteristicChangeListener(listener);
		bodyPart.removeCharacteristic(character);
		assertEquals(0, listener.removed());
	}
	
	@Test
	public void characteristicChangedWithListener() {
		ICharacteristic character = new Characteristic("Name", "Value");
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>(Arrays.asList(character)));
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		character.value("Value2");
		assertEquals(1, listener.changed());
	}
	
	@Test
	public void characteristicChangedWithoutListener() {
		ICharacteristic character = new Characteristic("Name", "Value");
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>(Arrays.asList(character)));
		TestChangeListener<ICharacteristic> listener = new TestChangeListener<ICharacteristic>();
		bodyPart.addCharacteristicChangeListener(listener);
		bodyPart.removeCharacteristicChangeListener(listener);
		character.value("Value2");
		assertEquals(0, listener.changed());
	}
	
	@Test
	public void bodyPartChangedWithListener() {
		ICharacteristic character = new Characteristic("Name", "Value");
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>());
		ValueConsumer<List<ICharacteristic>, IBodyPart> listener = new ValueConsumer<List<ICharacteristic>, IBodyPart>(Arrays.asList(character));
		bodyPart.addChangeListener(listener);
		bodyPart.addCharacteristic(character);
		assertEquals(1, listener.changeCount());
		assertTrue(listener.matched());
	}
	
	@Test
	public void bodyPartChangedWithoutListener() {
		ICharacteristic character = new Characteristic("Name", "Value");
		IBodyPart bodyPart = new BodyPart("Test", "TestPart", new ArrayList<ICharacteristic>());
		ValueConsumer<List<ICharacteristic>, IBodyPart> listener = new ValueConsumer<List<ICharacteristic>, IBodyPart>(Arrays.asList(character));
		bodyPart.addChangeListener(listener);
		bodyPart.removeChangeListener(listener);
		bodyPart.addCharacteristic(character);
		assertEquals(0, listener.changeCount());
	}
}