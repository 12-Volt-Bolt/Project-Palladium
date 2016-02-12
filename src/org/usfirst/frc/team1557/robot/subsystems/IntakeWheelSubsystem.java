package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.RobotMap.MotorId;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeWheelCommand;

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
		// boulderSwitch = new DigitalInput(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ControlIntakeWheelCommand());
	}

	public void stopMotors() {
		intakeMotor.set(0);
	}

	public void forward() {
		intakeMotor.set(1);
	}

	public void reverse() {
		intakeMotor.set(-1);
	}
}
