package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeArmSubsystem extends Subsystem {
	boolean manualOverride = false;
	CANTalon intakeArmMotorOne;
	CANTalon intakeArmMotorTwo;
	/**
	 * Speed to scale the intake motor by.
	 */
	double speed = 0.34d;

	public IntakeArmSubsystem() {
		intakeArmMotorOne = new CANTalon(RobotMap.MotorId.INTAKE_ARM_ONE.getId());
		intakeArmMotorTwo = new CANTalon(RobotMap.MotorId.INTAKE_ARM_TWO.getId());

	}

	public void initDefaultCommand() {
		setDefaultCommand(new ControlIntakeArmCommand());
	}

	public void set(double output) {
		intakeArmMotorOne.set(output * speed);
		intakeArmMotorTwo.set(output * speed);
	}
}
