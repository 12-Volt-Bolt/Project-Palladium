package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClimbCommand extends Command {
	int count = 0;
	int countToFinish = 50;
	State currentState = null;

	enum State {

		START, EXTENDED, LIFTED, INVALID;
	}

	public ClimbCommand() {
		requires(Robot.climb);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (!Robot.climb.getInfitesimal() && !Robot.climb.getProdigious()) {
			currentState = State.START;
		} else if (Robot.climb.getInfitesimal() && Robot.climb.getProdigious()) {
			currentState = State.EXTENDED;
		} else if (Robot.climb.getInfitesimal() && !Robot.climb.getProdigious()) {
			currentState = State.LIFTED;
		} else {
			currentState = State.INVALID;
			DriverStation.getInstance().reportError("Climb Pistons are in an invalid position!", false);
		}
		switch (currentState) {
		case EXTENDED:

			break;
		case LIFTED:
			Robot.climb.toggleProdigious();
			break;
		case START:
			Robot.climb.toggleInfitesimal();
			break;
		default:
			break;

		}

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		count++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return count >= countToFinish;
	}

	// Called once after isFinished returns true
	protected void end() {
		switch (currentState) {
		case EXTENDED:
			Robot.climb.toggleProdigious();
			break;
		case LIFTED:
			Robot.climb.toggleInfitesimal();
			break;
		case START:
			Robot.climb.toggleProdigious();
			break;
		default:
			break;

		}

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
