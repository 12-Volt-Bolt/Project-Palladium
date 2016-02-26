package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap.MotorId;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;
import org.usfirst.frc.team1557.robot.utils.MotorGroup;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeArmSubsystem extends Subsystem {
	MotorGroup intakeArm;
	//CANTalon intakeArmMotorOne;
	//CANTalon intakeArmMotorTwo;
//	Mramp armOneMramp = new Mramp(200, 0.05);
//	Mramp armTwoMramp = new Mramp(200, 0.05);
	/**
	 * Speed to scale the intake motor by.
	 */
	double speed = 1d;

	public IntakeArmSubsystem() {
		intakeArm = new MotorGroup(CANTalon.class, MotorId.INTAKE_ARM_ONE.getId(), MotorId.INTAKE_ARM_TWO.getId());
		//intakeArmMotorOne = new CANTalon(RobotMap.MotorId.INTAKE_ARM_ONE.getId());
		//intakeArmMotorTwo = new CANTalon(RobotMap.MotorId.INTAKE_ARM_TWO.getId());

	}

	public void initDefaultCommand() {
		setDefaultCommand(new ControlIntakeArmCommand());
	}

	public void set(double output) {
		intakeArm.set(output * speed);
//		armOneMramp.addSpeed(output);
//		armTwoMramp.addSpeed(output);
		//intakeArmMotorOne.set(output * speed);
		//intakeArmMotorTwo.set(output * speed);
	}

}
