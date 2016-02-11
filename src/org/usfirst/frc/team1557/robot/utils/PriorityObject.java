package org.usfirst.frc.team1557.robot.utils;

public abstract class PriorityObject {
	int priority = 0;

	/**
	 * 
	 * @param priority
	 *            The priority of the object. Start counting at 0. Do <b>NOT</b>
	 *            create two objects in one group with the same priority.
	 */
	public PriorityObject(int priority) {
		this.priority = priority;
	}

	/**
	 * The run() method will only run if shouldRun() returns true;
	 * 
	 * @return
	 */
	public abstract boolean shouldRun();

	public abstract void run();
}
