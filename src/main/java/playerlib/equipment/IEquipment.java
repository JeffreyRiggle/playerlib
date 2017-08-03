package playerlib.equipment;

import java.util.List;
import java.util.Map;

import playerlib.core.ChangeListener;
import playerlib.items.IItem;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IEquipment {
	/**
	 * 
	 * @return A list of items that are equipped.
	 * @see IItem
	 */
	List<IItem> equipted();
	/**
	 * 
	 * @param bodyPart The body part you would like to get the equipment from.
	 * @return The item that is attached to that body part.
	 * @See IItem
	 * @See IBodyPart
	 */
	IItem equipted(IBodyPart bodyPart);
	/**
	 * 
	 * @param bodyPart The body part you would like to equip to.
	 * @param item The item you would like to equip.
	 * @see IBodyPart
	 * @see IItem
	 */
	void equip(IBodyPart bodyPart, IItem item);
	/**
	 * 
	 * @param items A map of body parts and items to equip.
	 * @see IBodyPart
	 * @see IItem
	 */
	void equip(Map<IBodyPart, IItem> items);
	/**
	 * 
	 * @param bodyPart The body part you would like to un-equip from.
	 * @return The Item on that body part.
	 * @see IBodyPart
	 * @see IItem
	 */
	IItem unEquip(IBodyPart bodyPart);
	/**
	 * 
	 * @param bodyParts A map of body parts and to un-equip.
	 * @return The items attached to those body parts.
	 * @see IBodyPart
	 * @see IItem
	 */
	List<IItem> unEquip(List<IBodyPart> bodyParts);
	/**
	 * 
	 * @return All Items that were un-equipped.
	 * @see IItem
	 */
	List<IItem> unEquipAll();
	/**
	 * This is used to allow a consumer to be notified when any body part is equipped to or un-equipped from.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever any body part is equipped to or un-equiped from.
	 */
	void addEquipmentChangeListener(ChangeListener<IEquiped> listener);
	/**
	 * This is used to stop a consumer from receiving notifications when any body part is equipped to or un-equiped from.
	 * 
	 * @param listener A @see ChangeListener to be notified whenever any body part is equipped to or un-equiped from.
	 */
	void removeEquipmentChangeListener(ChangeListener<IEquiped> listener);
}