package org.usfirst.frc.team1557.robot.utils;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * Use this class to treat multiple motors as the same. Primarily used for
 * setting each side of the robot.
 * 
 *
 */
public class MotorGroup {
	CANTalon[] talons;

	public MotorGroup(CANTalon... talons) {
		this.talons = talons;
	}

	public void set(double d) {
		for (CANTalon c : talons) {
			c.set(d);
		}
	}

}
