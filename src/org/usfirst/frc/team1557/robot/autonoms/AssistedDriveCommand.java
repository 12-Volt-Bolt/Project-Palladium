package org.usfirst.frc.team1557.robot.autonoms;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AssistedDriveCommand extends Command {
	double speed, timeToRun, angle;
	long startTime;

	public AssistedDriveCommand(double speed, double time, double angle) {
		requires(Robot.drive);
		this.speed = speed;
		this.timeToRun = time;
		this.angle = angle;
		startTime = System.currentTimeMillis();

	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.drive.assistedTankDrive(speed, speed, angle);
	}

	protected boolean isFinished() {
		return System.currentTimeMillis() - startTime >= timeToRun;

	}

	protected void end() {
		Robot.drive.tankDrive(0, 0);
	}

	protected void interrupted() {
		Robot.drive.tankDrive(0, 0);
	}
}
