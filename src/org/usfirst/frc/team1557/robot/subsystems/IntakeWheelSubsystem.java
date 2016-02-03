package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeWheelSubsystem extends Subsystem {
	CANTalon intakeMotor;
	DigitalInput boulderSwitch;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public IntakeWheelSubsystem() {
		intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR_ONE_ID);
		boulderSwitch = new DigitalInput(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void runIntakeWheels() {
		if (boulderSwitch.get()) {
			intakeMotor.set(-1);
		} else {
			intakeMotor.set(1);
		}
	}
}
