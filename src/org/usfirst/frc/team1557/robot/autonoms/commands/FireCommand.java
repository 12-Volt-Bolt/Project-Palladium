package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FireCommand extends Command {

	public FireCommand() {
		requires(Robot.intakeWheel);
		this.setTimeout(1);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.intakeWheel.reverse();
	}

	protected boolean isFinished() {

		return isTimedOut();
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
