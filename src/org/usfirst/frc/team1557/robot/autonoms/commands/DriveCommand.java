package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {
	double speed, timeToRun;
	long startTime;

	/**
	 * 
	 * @param speed
	 *            Speed to run the drive motors at. A double between 1 & -1.
	 * @param time
	 *            Time in milliseconds to run this command.
	 */
	public DriveCommand(double speed, double time) {
		requires(Robot.drive);
		this.speed = speed;
		this.timeToRun = time;

		startTime = System.currentTimeMillis();
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.drive.tankDrive(speed, speed);
	}

	protected boolean isFinished() {
		return System.currentTimeMillis() - startTime >= timeToRun;
	}

	protected void end() {
		Robot.drive.tankDrive(0, 0);
	}

	protected void interrupted() {
	}
}
