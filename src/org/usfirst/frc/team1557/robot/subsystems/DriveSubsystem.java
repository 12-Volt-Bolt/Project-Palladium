package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.utils.MotorGroup;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team1557.robot.RobotMap.*;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	MotorGroup right;
	MotorGroup left;
	CANTalon motorOne;
	CANTalon motorTwo;
	CANTalon motorThree;
	CANTalon motorFour;
	private boolean reverse = false;
	ADXRS450_Gyro gyro;
	PIDController gyroPID;
	PIDController encoderLeftPID;
	PIDController encoderRightPID;
	Encoder leftEncoder;
	Encoder rightEncoder;

	public DriveSubsystem() {

		motorOne = new CANTalon(DRIVE_MOTOR_ONE_ID);
		motorTwo = new CANTalon(DRIVE_MOTOR_TWO_ID);
		motorThree = new CANTalon(DRIVE_MOTOR_THREE_ID);
		motorFour = new CANTalon(DRIVE_MOTOR_FOUR_ID);
		right = new MotorGroup(motorOne, motorThree);
		left = new MotorGroup(motorTwo, motorFour);
		initGyro();
		initEncoders();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveCommand());

	}

	/**
	 * 
	 * @param rightSpeed
	 *            Speed of the right motors.
	 * @param leftSpeed
	 *            Speed of the left motors.
	 */
	public void tankDrive(double rightSpeed, double leftSpeed) {

		right.set((reverse) ? -leftSpeed : rightSpeed);
		left.set((reverse) ? -rightSpeed : leftSpeed);
	}

	public void reverseMotors(boolean reverse) {
		this.reverse = reverse;
	}

	public void gyroTankDrive(double rightSpeed, double leftSpeed, double angle) {
		if (gyroPID.onTarget()) {
			gyroPID.disable();
			right.set(rightSpeed);
			left.set(leftSpeed);
		} else if (!gyroPID.isEnabled()) {
			gyroPID.enable();
		}
	}

	public void encoderTankDrive(double distance) {

	}

	private void initGyro() {
		gyro = new ADXRS450_Gyro();

		gyroPID = new PIDController(0.05, 0, 0, gyro, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				right.set(-output);
				left.set(output);
			}
		});
		gyroPID.setContinuous();
		gyroPID.setAbsoluteTolerance(1);
	}

	private void initEncoders() {
		encoderLeftPID = new PIDController(0.05, 0.0, 0.0, leftEncoder, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				right.set(output);

			}
		});
		encoderRightPID = new PIDController(0.05, 0.0, 0.0, rightEncoder, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				left.set(output);

			}
		});
	}

	public void gyroTurn(double angle) {
		gyroPID.setSetpoint(angle);
		if (!gyroPID.isEnabled() && !gyroPID.onTarget()) {
			gyroPID.enable();
		}
	}

	public boolean isGyroOnTarget() {
		return gyroPID.onTarget();
	}

	public void disableGyroPID() {
		gyroPID.disable();
	}

	public void disableEncoderPIDs() {
		encoderLeftPID.disable();
		encoderRightPID.disable();
	}
}

// We know you're watching ;) Death be to the filthy kamikaze pigs. Long live
// volty.