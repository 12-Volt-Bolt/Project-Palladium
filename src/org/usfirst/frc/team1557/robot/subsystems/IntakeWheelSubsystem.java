package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.RobotMap.MotorId;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Dedicated subsystem that controls the intake wheels using a digitalinput
 */
public class IntakeWheelSubsystem extends Subsystem {
	CANTalon intakeMotor;
	DigitalInput boulderSwitch;

	public IntakeWheelSubsystem() {
		intakeMotor = new CANTalon(MotorId.INTAKE_ONE.getId());
		boulderSwitch = new DigitalInput(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ControlIntakeArmCommand());
	}

	public void runIntakeWheels() {
		if (boulderSwitch.get()) {
			intakeMotor.set(-1);
		} else {
			intakeMotor.set(1);
		}
	}

	public void stopMotors() {
		intakeMotor.set(0);
	}
}
