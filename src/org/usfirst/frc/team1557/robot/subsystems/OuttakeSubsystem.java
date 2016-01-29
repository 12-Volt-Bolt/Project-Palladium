package org.usfirst.frc.team1557.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;

public class OuttakeSubsystem {
	CANTalon leftMotor;
	CANTalon rightMotor;

	public OuttakeSubsystem() {
		leftMotor = new CANTalon(0);
		rightMotor = new CANTalon(0);
	}

	public void fire() {
		rightMotor.set(1);
		leftMotor.set(1);
	}

	public void turnOff() {
		rightMotor.set(0);
		leftMotor.set(0);
	}

}
