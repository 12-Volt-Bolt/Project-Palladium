package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {
	double rightSpeed, leftSpeed, timeToRun;

	/**
	 * 
	 * @param speed
	 *            Speed to run the drive motors at. A double between 1 & -1.
	 * @param time
	 *            Time in milliseconds to run this command.
	 */
	public DriveCommand(double rightSpeed, double leftSpeed, double time) {
		requires(Robot.drive);
		this.rightSpeed = rightSpeed;
		this.leftSpeed = leftSpeed;
		this.timeToRun = time;

		setTimeout(this.timeToRun);
	}

	protected void initialize() {
		System.err.println("Initializing " + this);
	}

	@Override
	public String toString() {
		return "DriveCommand [speed=" + leftSpeed + ":" + rightSpeed + ", timeToRun=" + timeToRun + "]";
	}

	protected void execute() {
		Robot.drive.tankDrive(-leftSpeed, -rightSpeed);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.drive.tankDrive(0, 0);
	}

	protected void interrupted() {
	}
}
