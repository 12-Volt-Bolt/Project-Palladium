package org.usfirst.frc.team1557.robot.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidGroup {
	private String name = "nullName";
	Solenoid[] solenoids;

	public SolenoidGroup(Solenoid... solenoids) {
		this.solenoids = solenoids;
	}

	public SolenoidGroup(String name, Solenoid... solenoids) {
		this(solenoids);
		this.name = name;

	}

	public void set(boolean state) {
		for (Solenoid s : solenoids) {
			s.set(state);
		}
	}

	public boolean getState() {
		boolean firstBoolean = solenoids[0].get();

		for (Solenoid s : solenoids) {
			if (s.get() != firstBoolean) {
				DriverStation.getInstance().reportError(
						"Solenoids " + name
								+ " states do not match! \n Using the value of the first solenoid. Code will break.",
						false);
			}
		}

		return firstBoolean;
	}

}
