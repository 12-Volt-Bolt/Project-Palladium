package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.utils.MotorGroup;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.Robot;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
//	MotorGroup right;
//	MotorGroup left;
	CANTalon motorOne;
	CANTalon motorTwo;
	CANTalon motorThree;
	CANTalon motorFour;
	private boolean reverse = false;
	public ADXRS450_Gyro gyro;
//	PIDController gyroPID;
	
	
	public Encoder leftEncoder;
	public Encoder rightEncoder;

	public DriveSubsystem() {

		motorOne = new CANTalon(DRIVE_MOTOR_ONE_ID);
		motorTwo = new CANTalon(DRIVE_MOTOR_TWO_ID);
		motorThree = new CANTalon(DRIVE_MOTOR_THREE_ID);
		motorFour = new CANTalon(DRIVE_MOTOR_FOUR_ID);
		
//		right = new MotorGroup(motorOne, motorThree);
//		left = new MotorGroup(motorTwo, motorFour);
		
		gyro = new ADXRS450_Gyro();
		leftEncoder = new Encoder(ENCODER_LEFT_A, ENCODER_LEFT_B);
		rightEncoder = new Encoder(ENCODER_RIGHT_A, ENCODER_RIGHT_B);
		
		SmartDashboard.putData("gyro", gyro);
		SmartDashboard.putData("leftEncoder", leftEncoder);
		SmartDashboard.putData("rightEncoder", rightEncoder);
		
		rightEncoder.setDistancePerPulse((WHEEL_CIRCUMFERENCE_INCHES / ENCODER_PULSES_PER_ROTATION));
		leftEncoder.setDistancePerPulse((WHEEL_CIRCUMFERENCE_INCHES / ENCODER_PULSES_PER_ROTATION));
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveCommand());
	}
	
	private void setMotors(double leftSpeed, double rightSpeed) {
		motorOne.set(-rightSpeed);
		motorTwo.set(leftSpeed);
		motorThree.set(rightSpeed);
		motorFour.set(-leftSpeed);
	}

	/**
	 * 
	 * @param rightSpeed
	 *            Speed of the right motors.
	 * @param leftSpeed
	 *            Speed of the left motors.
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		setMotors((reverse) ? -rightSpeed : leftSpeed, (reverse) ? -leftSpeed : rightSpeed);
	}

	public void reverseMotors(boolean reverse) {
		this.reverse = reverse;
	}

}

// We know you're watching ;) Death be to the filthy kamikaze pigs. Long live
// volty.