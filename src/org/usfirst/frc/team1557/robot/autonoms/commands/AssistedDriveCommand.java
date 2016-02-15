package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AssistedDriveCommand extends Command {
	double speed, angle;
	
	public AssistedDriveCommand(double speed, double time, double angle) {
		requires(Robot.drive);
		this.speed = speed;
		this.angle = angle;
		
		setTimeout(time);
	}

	protected void initialize() {
	}

	protected void execute() {
		//Robot.drive.gyroTankDrive(speed, speed, angle);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.drive.tankDrive(0, 0);
	}

	protected void interrupted() {
		Robot.drive.tankDrive(0, 0);
	}
}
