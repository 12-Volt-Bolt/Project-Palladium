package org.usfirst.frc.team1557.robot.utils;

import java.util.HashMap;

import edu.wpi.first.wpilibj.DriverStation;

public class PriorityGroup {
	HashMap<Integer, PriorityObject> objectsInList;
	boolean didRun = false;

	/**
	 * Takes in a list of PriorityObjects. Call the run() method to run the list
	 * according to priorities.
	 * 
	 * @param objects
	 */
	public PriorityGroup(PriorityObject... objects) {
		objectsInList = new HashMap<>(objects.length);
		for (PriorityObject o : objects) {
			objectsInList.put(o.priority, o);
		}
	}

	public void run() {
		didRun = false;
		for (int i = 0; i < objectsInList.size(); i++) {
			PriorityObject o = objectsInList.get(i);
			if (o != null && o.shouldRun() && didRun == false) {

				o.run();
				didRun = true;
				return;
			}
		}
		didRun = false;
	}
}
