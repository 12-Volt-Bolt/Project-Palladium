package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceAtAngleCommand extends Command {
	PIDController leftEncoderPID, rightEncoderPID, gyroPID;
	double distanceToTravel = 0;
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
		leftEncoderPID.enable();
		rightEncoderPID = new PIDController(0.05, 0, 0, Robot.drive.rightEncoder, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				rightEncoderOutput = output;
			}
		});
		rightEncoderPID.enable();
		gyroPID = new PIDController(0.05, 0, 0, Robot.drive.gyro, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				gyroOutput = output;
			}
		});
		gyroPID.enable();
		
//		gyroPID.setInputRange(-360, 360);
//		gyroPID.setContinuous();
		
		// set tolerance to 10 degrees
		gyroPID.setAbsoluteTolerance(10);
		// set tolerarance to 1/20th of a rotation
		leftEncoderPID.setAbsoluteTolerance(RobotMap.ENCODER_PULSES_PER_ROTATION / 20);
		rightEncoderPID.setAbsoluteTolerance(RobotMap.ENCODER_PULSES_PER_ROTATION / 20);
		
		SmartDashboard.putData("leftEncoderPID", leftEncoderPID);
		SmartDashboard.putData("rightEncoderPID", rightEncoderPID);
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
		
		SmartDashboard.putNumber("Autonomous Left Side", leftSpeed * speed);
		SmartDashboard.putNumber("Autonomous Right Side", rightSpeed * speed);
		SmartDashboard.putNumber("Gyro Read Value", Robot.drive.gyro.getAngle());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return gyroPID.onTarget() && leftEncoderPID.onTarget() && rightEncoderPID.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		leftEncoderPID.disable();
		rightEncoderPID.disable();
		gyroPID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
