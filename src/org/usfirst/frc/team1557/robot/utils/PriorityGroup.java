package org.usfirst.frc.team1557.robot.utils;

import java.util.HashMap;

public class PriorityGroup {
	HashMap<Integer, PriorityObject> objectsInList = new HashMap<>();
	boolean didRun = false;

	/**
	 * Takes in a list of PriorityObjects. Call the run() method to run the list
	 * according to priorities.
	 * 
	 * @param objects
	 */
	public PriorityGroup(PriorityObject... objects) {
		for (PriorityObject o : objects) {
			objectsInList.put(o.priority, o);
		}
	}

	public void run() {
		for (int i = 0; i < objectsInList.size(); i++) {
			PriorityObject o = objectsInList.get(i);
			if (o != null && o.shouldRun()) {
				o.run();
				didRun = true;
				return;
			}
		}
		didRun = false;
	}
}
