package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FireCommand extends Command {
	long startTime;

	public FireCommand() {
		requires(Robot.intakeWheel);
		requires(Robot.launch);
		startTime = System.currentTimeMillis();
	}

	protected void initialize() {
	}

	protected void execute() {
		if (System.currentTimeMillis() - startTime < 2) {
			Robot.intakeWheel.forward();
		} else {
			Robot.intakeWheel.stopMotors();
			Robot.launch.startMotors();
		}
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.launch.stopMotors();
		Robot.intakeWheel.stopMotors();
	}

	protected void interrupted() {
	this.end();
	}
}
