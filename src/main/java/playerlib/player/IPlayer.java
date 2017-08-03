package playerlib.player;

import java.util.List;

import playerlib.attributes.*;
import playerlib.characteristics.*;
import playerlib.core.ChangeListener;
import playerlib.inventory.*;
import playerlib.equipment.*;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IPlayer {

	/**
	 * 
	 * @return The name of the player.
	 */
	String name();
	/**
	 * 
	 * @param value The new name of the player.
	 */
	void name(String value);
	/**
	 * 
	 * @return The content associated with this player.
	 */
	<T> T content();
	/**
	 * 
	 * @param value The new content associated with this player.
	 */
	<T> void content(T value);
	/**
	 * 
	 * @return The inventory associated with this player.
	 */
	IInventory inventory();
	/**
	 * 
	 * @param inventory The new inventory for the player.
	 */
	void inventory(IInventory inventory);
	/**
	 * 
	 * @return The equipment associated with this player.
	 */
	IEquipment equipment();
	/**
	 * 
	 * @param equipment The new equipment for this player.
	 */
	void equipment(IEquipment equipment);
	/**
	 * 
	 * @return The attributes associated with this player.
	 */
	List<IAttribute> attributes();
	/**
	 * 
	 * @param attribute An attribute to add to this player.
	 */
	void addAttribute(IAttribute attribute);
	/**
	 * 
	 * @param attributes The attributes to add to this player.
	 */
	void addAttributes(List<IAttribute> attributes);
	/**
	 * 
	 * @param attribute The attribute to remove from this player.
	 */
	void removeAttribute(IAttribute attribute);
	/**
	 * 
	 * @param attributes The attributes to remove from this player.
	 */
	void removeAttributes(List<IAttribute> attributes);
	/**
	 *  Remove all attributes from this player.
	 */
	void clearAttributes();
	/**
	 * 
	 * @return The characteristics associated with this player.
	 */
	List<ICharacteristic> characteristics();
	/**
	 * 
	 * @param characteristic The characteristic to add to this player.
	 */
	void addCharacteristic(ICharacteristic characteristic);
	/**
	 * 
	 * @param characteristics The characteristics to add to this player.
	 */
	void addCharacteristics(List<ICharacteristic> characteristics);
	/**
	 * 
	 * @param characteristic The characteristic to remove from this player.
	 */
	void removeCharacteristic(ICharacteristic characteristic);
	/**
	 * 
	 * @param characteristics The characteristics to remove from this player.
	 */
	void removeCharacteristics(List<ICharacteristic> characteristics);
	/**
	 * Remove all characteristics from this player.
	 */
	void clearCharacteristics();
	/**
	 * 
	 * @return The body parts associated with this player.
	 */
	List<IBodyPart> bodyParts();
	/**
	 * 
	 * @param bodyPart The body part to add to this player.
	 */
	void addBodyPart(IBodyPart bodyPart);
	/**
	 * 
	 * @param bodyParts The body parts to add to this player.
	 */
	void addBodyParts(List<IBodyPart> bodyParts);
	/**
	 * 
	 * @param bodyPart The body part to remove from this player.
	 */
	void removeBodyPart(IBodyPart bodyPart);
	/**
	 * 
	 * @param bodyParts The body parts to remove from this player.
	 */
	void removeBodyParts(List<IBodyPart> bodyParts);
	/**
	 * Remove all body parts from this player.
	 */
	void clearBodyParts();
	/**
	 * This is used to allow a consumer to be notified when any attribute is added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this attribute is added, changed or removed.
	 */
	void addAttributeChangeListener(ChangeListener<IAttribute> listener);
	/**
	 * This is used to stop a consumer from receiving notifications when attributes are added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this attribute is added, changed or removed.
	 */
	void removeAttributeChangedListener(ChangeListener<IAttribute> listener);
	/**
	 * This is used to allow a consumer to be notified when any characteristic is added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this characteristic is added, changed or removed.
	 */
	void addCharacteristicChangeListener(ChangeListener<ICharacteristic> listener);
	/**
	 * This is used to stop a consumer from receiving notifications when characteristics are added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this characteristic is added, changed or removed.
	 */
	void removeCharacteristicChangeListener(ChangeListener<ICharacteristic> listener);
	/**
	 * This is used to allow a consumer to be notified when any body part is added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this body part is added, changed or removed.
	 */
	void addBodyPartChangeListener(ChangeListener<IBodyPart> listener);
	/**
	 * This is used to stop a consumer from receiving notifications when body parts are added, changed or removed.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever this body part is added, changed or removed.
	 */
	void removeBodyPartChangeListener(ChangeListener<IBodyPart> listener);
}