package playerlib.equipment;

import java.util.List;

import playerlib.characteristics.Characteristic;
import playerlib.characteristics.ICharacteristic;
import playerlib.core.ChangeListener;
import playerlib.core.ValueChangeManager;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IBodyPart extends Comparable<IBodyPart>, ValueChangeManager<IBodyPart> {

	/**
	 * 
	 * @return The name of the body part.
	 */
	String name();
	/**
	 * 
	 * @return  A description of the body part.
	 */
	String description();
	/**
	 * 
	 * @param value New name of the body part.
	 */
	void name(String value);
	/**
	 * 
	 * @param value New description for the body part.
	 */
	void description(String value);
	/**
	 * 
	 * @return A list of characteristics associated with this body part.
	 * @see ICharacteristic
	 */
	List<ICharacteristic> getCharacteristics();
	/**
	 * 
	 * @param characteristic The characteristic to add to this body part.
	 * @see Characteristic 
	 */
	void addCharacteristic(ICharacteristic characteristic);
	/**
	 * 
	 * @param characteristics The characteristics to add to this body part.
	 * @see ICharacteristic
	 */
	void addCharacteristics(List<ICharacteristic> characteristics);
	/**
	 * 
	 * @param characteristic The characteristic to remove from this body part.
	 * @see ICharacteristic
	 */
	void removeCharacteristic(ICharacteristic characteristic);
	/**
	 * 
	 * @param characteristics The characteristics to remove from this body part.
	 * @see Characteristics.ICharacteristic
	 */
	void removeCharacteristics(List<ICharacteristic> characteristics);
	/**
	 * Remove all characteristics from this body part.
	 */
	void clearCharacteristics();
	/**
	 * 
	 * @param name The name associated with the characteristic you want to find.
	 * @see ICharacteristic 
	 * @return The first attribute with that name.
	 */
	ICharacteristic getCharacteristic(String name);
	/**
	 * This is used to allow a consumer to be notified when any characteristic is added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever a characteristic is added, changed or removed.
	 */
	void addCharacteristicChangeListener(ChangeListener<ICharacteristic> listener);
	/**
	 * This is used to stop a consumer from receiving notifications when characteristics are added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever a characteristic is added, changed or removed.
	 */
	void removeCharacteristicChangeListener(ChangeListener<ICharacteristic> listener);
}