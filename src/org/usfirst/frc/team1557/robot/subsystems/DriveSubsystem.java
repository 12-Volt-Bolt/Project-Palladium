package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team1557.robot.RobotMap.*;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	TalonSRX moterOne = new TalonSRX(driveMotorOne_ID);
	TalonSRX moterTwo = new TalonSRX(driveMotorTwo_ID);
	TalonSRX moterThree = new TalonSRX(driveMotorThree_ID);
	TalonSRX moterFour = new TalonSRX(driveMotorTwo_ID);
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TankDriveCommand());
	}

	public void tankDrive(double x, double y) {
		moterOne.set(x);
		moterTwo.set(y);
		moterThree.set(x);
		moterFour.set(y);
	}

}
