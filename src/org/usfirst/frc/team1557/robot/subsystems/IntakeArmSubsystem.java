package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;
import org.usfirst.frc.team1557.robot.utils.Mramp;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeArmSubsystem extends Subsystem {
	CANTalon intakeArmMotorOne;
	CANTalon intakeArmMotorTwo;
//	Mramp armOneMramp = new Mramp(200, 0.05);
//	Mramp armTwoMramp = new Mramp(200, 0.05);
	/**
	 * Speed to scale the intake motor by.
	 */
	double speed = 1d;

	public IntakeArmSubsystem() {
		intakeArmMotorOne = new CANTalon(RobotMap.MotorId.INTAKE_ARM_ONE.getId());
		intakeArmMotorTwo = new CANTalon(RobotMap.MotorId.INTAKE_ARM_TWO.getId());

	}

	public void initDefaultCommand() {
		setDefaultCommand(new ControlIntakeArmCommand());
	}

	public void set(double output) {
//		armOneMramp.addSpeed(output);
//		armTwoMramp.addSpeed(output);
		intakeArmMotorOne.set(output * speed);
		intakeArmMotorTwo.set(output * speed);
	}

}
