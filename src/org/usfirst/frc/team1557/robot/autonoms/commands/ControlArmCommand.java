package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlArmCommand extends Command {
	double speed;

	/**
	 * Moves the intake arm at the given speed for the given time; if no time is
	 * given it defaults to 1 second
	 * 
	 * @param speed
	 *            speed to run the arm at. Positive takes the arm out. Negative
	 *            pulls the arm into the robot.
	 * @param time
	 */
	public ControlArmCommand(double speed, double time) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intakeArm);
		this.setTimeout(time);
	}

	public ControlArmCommand(double speed) {
		this(speed, 1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intakeArm.set(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intakeArm.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
