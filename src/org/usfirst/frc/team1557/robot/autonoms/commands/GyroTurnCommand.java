package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurnCommand extends Command {
	double angle;
	
	// result created while gyroPID runs
	double gyroOutput;

	PIDController gyroPID;
	
	public GyroTurnCommand(double angle) {
		requires(Robot.drive);
		
		this.angle = angle;
		this.setTimeout(5);
		
		gyroPID = new PIDController(0.05, 0, 0, Robot.drive.gyro, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				gyroOutput = output;
			}
		});
		gyroPID.enable();
		gyroPID.setAbsoluteTolerance(10);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		gyroTurn(angle);
	}

	protected boolean isFinished() {
		return isGyroOnTarget() || isTimedOut();
	}

	protected void end() {
		gyroPID.disable();
	}

	protected void interrupted() {
	}
	
	
	
	public void gyroTankDrive(double rightSpeed, double leftSpeed, double angle) {
		if (gyroPID.onTarget()) {
			gyroPID.disable();
			Robot.drive.tankDrive(leftSpeed, rightSpeed);
		} else if (!gyroPID.isEnabled()) {
			gyroPID.enable();
		}
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

}
