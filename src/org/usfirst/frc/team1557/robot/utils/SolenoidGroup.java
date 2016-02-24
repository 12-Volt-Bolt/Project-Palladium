package org.usfirst.frc.team1557.robot.utils;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidGroup {
	private String name = "nullName";
	DoubleSolenoid[] solenoids;

	public SolenoidGroup(DoubleSolenoid... solenoids) {
		this.solenoids = solenoids;
	}

	public SolenoidGroup(String name, DoubleSolenoid... solenoids) {
		this(solenoids);
		this.name = name;

	}

	public void set(DoubleSolenoid.Value state) {
		for (DoubleSolenoid s : solenoids) {
			System.err.println("Solenoids were set");
			s.set(state);
		}

	}

	public DoubleSolenoid.Value getState() {
		Value firstState = solenoids[0].get();

		for (DoubleSolenoid s : solenoids) {
			if (s.get() != firstState) {
				DriverStation.getInstance().reportError(
						"Solenoids " + name
								+ " states do not match! \n Using the value of the first solenoid. Code will break.",
						false);
			}
		}

		return firstState;
	}

	public DoubleSolenoid.Value reverseState(DoubleSolenoid.Value value) {
		switch (value) {
		case kForward:
			return Value.kReverse;

		case kReverse:
			return Value.kForward;
		}
		return Value.kOff;
	}
}
