package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;

/**
 *
 */
public class DriveSubsystem extends Subsystem{
	CANTalon moterOne = new CANTalon(DRIVE_MOTOR_ONE_ID);
	CANTalon moterTwo = new CANTalon(DRIVE_MOTOR_TWO_ID);
	CANTalon moterThree = new CANTalon(DRIVE_MOTOR_THREE_ID);
	CANTalon moterFour = new CANTalon(DRIVE_MOTOR_FOUR_ID);
	
	public DriveSubsystem(){
	moterFour.reverseOutput(true);
	moterOne.reverseOutput(true);
	}
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
