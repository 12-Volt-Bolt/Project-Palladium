package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.ENCODER_LEFT_PULSES_PER_ROTATION;
import static org.usfirst.frc.team1557.robot.RobotMap.ENCODER_RIGHT_PULSES_PER_ROTATION;
import static org.usfirst.frc.team1557.robot.RobotMap.WHEEL_CIRCUMFERENCE_INCHES;

import org.usfirst.frc.team1557.robot.RobotMap.EncoderId;
import org.usfirst.frc.team1557.robot.RobotMap.MotorId;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	CANTalon motorOne;
	CANTalon motorTwo;
	CANTalon motorThree;
	CANTalon motorFour;
	private boolean reverse = false;
	public ADXRS450_Gyro gyro;

	public Encoder leftEncoder;
	public Encoder rightEncoder;

	public DriveSubsystem() {
		motorOne = new CANTalon(MotorId.DRIVE_ONE.getId());
		motorTwo = new CANTalon(MotorId.DRIVE_TWO.getId());
		motorThree = new CANTalon(MotorId.DRIVE_THREE.getId());
		motorFour = new CANTalon(MotorId.DRIVE_FOUR.getId());

		gyro = new ADXRS450_Gyro();
		leftEncoder = new Encoder(EncoderId.LEFT_A.getId(), EncoderId.LEFT_B.getId());
		rightEncoder = new Encoder(EncoderId.RIGHT_A.getId(), EncoderId.RIGHT_B.getId());

		SmartDashboard.putData("gyro", gyro);
		SmartDashboard.putData("leftEncoder", leftEncoder);
		SmartDashboard.putData("rightEncoder", rightEncoder);

		rightEncoder.setDistancePerPulse((WHEEL_CIRCUMFERENCE_INCHES / ENCODER_LEFT_PULSES_PER_ROTATION));
		leftEncoder.setDistancePerPulse((WHEEL_CIRCUMFERENCE_INCHES / ENCODER_RIGHT_PULSES_PER_ROTATION));
	}

	public void initDefaultCommand() {

	}

	/**
	 * Sets the speed of the motors for the drive. Positive is forwards for both
	 * sides.
	 * 
	 * @param leftSpeed
	 * @param rightSpeed
	 */
	private void setMotors(double leftSpeed, double rightSpeed) {
		motorOne.set(-rightSpeed);
		motorTwo.set(leftSpeed);
		motorThree.set(rightSpeed);
		motorFour.set(-leftSpeed);
	}

	/**
	 * Sets the speed of the motors for the drive. Positive is forwards for both
	 * sides.
	 * 
	 * @param leftSpeed
	 *            Speed of the left motors.
	 * @param rightSpeed
	 *            Speed of the right motors.
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		setMotors((reverse) ? -rightSpeed : leftSpeed, (reverse) ? -leftSpeed : rightSpeed);
	}

	public void reverseMotors(boolean reverse) {
		this.reverse = reverse;
	}

}