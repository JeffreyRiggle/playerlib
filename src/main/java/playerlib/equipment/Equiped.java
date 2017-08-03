package playerlib.equipment;

import playerlib.core.ValueListener;
import playerlib.items.IItem;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class Equiped implements IEquiped{

	private IBodyPart bodyPart;
	private IItem item;
	
	/**
	 * 
	 * @param bodyPart The body part associated with this equipment.
	 * @param item The item associated with this equipment.
	 */
	public Equiped(IBodyPart bodyPart, IItem item) {
		this.bodyPart = bodyPart;
		this.item = item;
	}
	
	@Override
	public IBodyPart getBodyPart() {
		return bodyPart;
	}

	@Override
	public IItem getItem() {
		return item;
	}

	@Override
	public void addChangeListener(ValueListener<IEquiped> listener) {
		// Changes should never happen
	}

	@Override
	public void removeChangeListener(ValueListener<IEquiped> listener) {
		// Changes should never happen
	}
}
