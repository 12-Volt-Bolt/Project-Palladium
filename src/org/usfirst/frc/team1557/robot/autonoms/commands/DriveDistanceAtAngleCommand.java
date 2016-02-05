package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceAtAngleCommand extends Command {
	PIDController leftEncoderPID, rightEncoderPID, gyroPID;
	double distanceToTravel = 0_0_0_0_0_0_0_0_0_0_0_0_0;
	double speed = 0;
	double leftEncoderOutput, rightEncoderOutput, gyroOutput;
	double angle = 0;

	public DriveDistanceAtAngleCommand(double distance, double angle, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		this.distanceToTravel = distance;
		this.angle = angle;
		this.speed = speed;
		leftEncoderPID = new PIDController(0.05, 0, 0, Robot.drive.leftEncoder, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				leftEncoderOutput = output;
			}
		});
		rightEncoderPID = new PIDController(0.05, 0, 0, Robot.drive.rightEncoder, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				rightEncoderOutput = output;
			}
		});
		gyroPID = new PIDController(0.05, 0, 0, Robot.drive.gyro, new PIDOutput() {

			@Override
			public void pidWrite(double output) {

				gyroOutput = output;
			}

		});
		gyroPID.setInputRange(-360, 360);
		gyroPID.setContinuous();
		gyroPID.setAbsoluteTolerance(5);
		leftEncoderPID.setAbsoluteTolerance(36);
		rightEncoderPID.setAbsoluteTolerance(36);
	}

	public DriveDistanceAtAngleCommand(double distance, double angle) {
		this(distance, angle, 1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		gyroPID.setSetpoint(angle);
		leftEncoderPID.setSetpoint(distanceToTravel);
		rightEncoderPID.setSetpoint(distanceToTravel);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double rightSpeed = rightEncoderOutput - gyroOutput;
		double leftSpeed = leftEncoderOutput + gyroOutput;
		double scale = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
		if (scale > 1) {
			rightSpeed /= scale;
			leftSpeed /= scale;
		}
		Robot.drive.tankDrive(leftSpeed * speed, rightSpeed * speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return gyroPID.onTarget() && leftEncoderPID.onTarget() && rightEncoderPID.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
