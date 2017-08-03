package playerlib.equipment;

import playerlib.core.ValueChangeManager;
import playerlib.items.IItem;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IEquiped extends ValueChangeManager<IEquiped>{
	/**
	 * 
	 * @return A @see IBodyPart that the item is equiped to.
	 */
	IBodyPart getBodyPart();
	/**
	 * 
	 * @return A @see IItem that is equiped to the body part.
	 */
	IItem getItem();
}
