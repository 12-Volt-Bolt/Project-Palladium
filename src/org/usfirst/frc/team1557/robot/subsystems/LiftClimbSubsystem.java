package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap.MotorId;
import org.usfirst.frc.team1557.robot.commands.LiftClimbCommand;
import org.usfirst.frc.team1557.robot.utils.MotorGroup;
import org.usfirst.frc.team1557.robot.utils.Mramp;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftClimbSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static MotorGroup climber;

	public static CANTalon climberMotorOne;
	public static CANTalon climberMotorTwo;

	public LiftClimbSubsystem() {
		climber = new MotorGroup(CANTalon.class, MotorId.LIFT_CLIMB_ONE.getId(), MotorId.LIFT_CLIMB_TWO.getId());

		//climberMotorOne = new CANTalon(RobotMap.MotorId.LIFT_CLIMB_ONE.getId());
		//climberMotorTwo = new CANTalon(RobotMap.MotorId.LIFT_CLIMB_TWO.getId());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new LiftClimbCommand());
	}

	public void stopMotors() {
		climber.set(0);
		//climberMotorOne.set(0);
		//climberMotorTwo.set(0);
	}

	public void up() {
		climber.set(0.67);
		//climberMotorOne.set(0.67);
		//climberMotorTwo.set(0.67);
	}

	public void down() {
		climber.set(-0.67);
		//climberMotorOne.set(-0.67);
		//climberMotorTwo.set(-0.67);
	}

}
