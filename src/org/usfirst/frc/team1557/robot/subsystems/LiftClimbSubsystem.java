package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.commands.LiftClimbCommand;
import org.usfirst.frc.team1557.robot.utils.Mramp;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftClimbSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static CANTalon climberMotorOne;
	public static CANTalon climberMotorTwo;

	public LiftClimbSubsystem() {
		climberMotorOne = new CANTalon(RobotMap.MotorId.LIFT_CLIMB_ONE.getId());
		climberMotorTwo = new CANTalon(RobotMap.MotorId.LIFT_CLIMB_TWO.getId());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new LiftClimbCommand());
	}

	public void stopMotors() {
		climberMotorOne.set(0);
		climberMotorTwo.set(0);
	}

	public void up() {
		climberMotorOne.set(0.67);
		climberMotorTwo.set(0.67);
	}

	public void down() {

		climberMotorOne.set(-0.67);
		climberMotorTwo.set(-0.67);
	}

}
