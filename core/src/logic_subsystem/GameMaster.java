package logic_subsystem;

import java.util.ArrayList;
import java.util.Map;

import xidecsc.XIDECSCComponent;
import xidecsc.XIDECSCComponentType;
import xidecsc.XiabweenInternalDatabase;

/**
 * Even though {@link XiabweenInternalDatabase} holds data, this
 * {@link GameMaster} class too will hold data, so it can't be abstract. After
 * all, {@link GameMaster} will contain a {@link XiabweenInternalDatabase} and
 * the other {@link XIDECSCComponent}s.
 *
 * @author sorcerer
 *
 */
public class GameMaster {

	protected Map<XIDECSCComponentType, XIDECSCComponent> components;

	public GameMaster(ArrayList<XIDECSCComponent> componentArray) {
		for (XIDECSCComponent component : componentArray) {
			this.components.put(component.type, component);
		}
	}

}
