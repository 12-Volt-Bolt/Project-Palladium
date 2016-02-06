package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

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

	/**
	 * Attempts to drive the robot forward for the given distance in inches, at the given relative rotation, at the given speed.
	 * @param distance
	 * @param angle
	 * @param speed
	 */
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
		
		SmartDashboard.putNumber("DIST_PER_PULSE", 250);
		
//		gyroPID.setInputRange(-360, 360);
//		gyroPID.setContinuous();
		
		// set tolerance to 10 degrees
		gyroPID.setAbsoluteTolerance(10);
		// set tolerarance to 1/20th of a rotation
		leftEncoderPID.setAbsoluteTolerance(RobotMap.ENCODER_LEFT_PULSES_PER_ROTATION / 20);
		rightEncoderPID.setAbsoluteTolerance(RobotMap.ENCODER_RIGHT_PULSES_PER_ROTATION / 20);
		
		SmartDashboard.putData("leftEncoderPID", leftEncoderPID);
		SmartDashboard.putData("rightEncoderPID", rightEncoderPID);
	}

	public DriveDistanceAtAngleCommand(double distance, double angle) {
		this(distance, angle, 1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		gyroPID.setSetpoint(Robot.drive.gyro.getAngle() + angle);
		Robot.drive.leftEncoder.reset();
		Robot.drive.rightEncoder.reset();
		leftEncoderPID.setSetpoint(distanceToTravel);
		rightEncoderPID.setSetpoint(distanceToTravel);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		double rightSpeed = rightEncoderOutput - gyroOutput;
//		double leftSpeed = leftEncoderOutput + gyroOutput;
//		double scale = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
//		if (scale > 1) {
//			rightSpeed /= scale;
//			leftSpeed /= scale;
//		}
		
//		Robot.drive.tankDrive(leftSpeed * speed, rightSpeed * speed);
//		
//		SmartDashboard.putNumber("Autonomous Left Side", leftSpeed * speed);
//		SmartDashboard.putNumber("Autonomous Right Side", rightSpeed * speed);
		
		Robot.drive.tankDrive(leftEncoderOutput, rightEncoderOutput);
		SmartDashboard.putNumber("Left Encoder PID Output", leftEncoderOutput);
		SmartDashboard.putNumber("Right Encoder PID Output", rightEncoderOutput);
		
		SmartDashboard.putNumber("Gyro Read Value", Robot.drive.gyro.getAngle());
		
		SmartDashboard.putNumber("leftEncoder", Robot.drive.leftEncoder.get());
		SmartDashboard.putNumber("rightEncoder", Robot.drive.rightEncoder.get());
		
		double prot = SmartDashboard.getNumber("DIST_PER_PULSE", 250);
		Robot.drive.rightEncoder.setDistancePerPulse((prot));
		Robot.drive.leftEncoder.setDistancePerPulse((prot));
		
//		Robot.drive.rightEncoder.setDistancePerPulse((WHEEL_CIRCUMFERENCE_INCHES / ENCODER_PULSES_PER_ROTATION));
//		Robot.drive.leftEncoder.setDistancePerPulse((WHEEL_CIRCUMFERENCE_INCHES / ENCODER_PULSES_PER_ROTATION));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
//		return gyroPID.onTarget() && leftEncoderPID.onTarget() && rightEncoderPID.onTarget();
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
