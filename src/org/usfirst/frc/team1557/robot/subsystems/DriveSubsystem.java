package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.utils.MotorGroup;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;

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

	public DriveSubsystem() {

		motorOne = new CANTalon(DRIVE_MOTOR_ONE_ID);
		motorTwo = new CANTalon(DRIVE_MOTOR_TWO_ID);
		motorThree = new CANTalon(DRIVE_MOTOR_THREE_ID);
		motorFour = new CANTalon(DRIVE_MOTOR_FOUR_ID);
		right = new MotorGroup(motorOne, motorThree);
		left = new MotorGroup(motorTwo, motorFour);
		initGyro();

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

	public void reverseMotors(boolean re) {
		reverse = re;
	}

	public void assistedTankDrive(double rightSpeed, double leftSpeed, double angle) {
		if (gyroPID.onTarget()) {
			gyroPID.disable();
			right.set(rightSpeed);
			left.set(leftSpeed);
		} else if (!gyroPID.isEnabled()) {
			gyroPID.enable();
		}
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

	public void assistedTurn(double angle) {
		gyroPID.setSetpoint(angle);
		if (!gyroPID.isEnabled() && !gyroPID.onTarget()) {
			gyroPID.enable();
		}

		// Levy_J????_Huchingson
	}

	public boolean isOnTarget() {
		return gyroPID.onTarget();
	}

	public void disableGyroPID() {
		gyroPID.disable();
	}
}

// We know you're watching ;) Death be to the filthy kamikaze pigs. Long live
// volty.