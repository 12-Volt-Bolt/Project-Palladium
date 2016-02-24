
package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PushupCommand extends Command {

	public PushupCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.pushup);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		DriverStation.getInstance().reportError("Pushup Command runs!", false);
		if (Robot.pushup.pushupGroup.getState() == Value.kOff) {
			Robot.pushup.set(Value.kForward);
		} else {
			Robot.pushup.set(Robot.pushup.pushupGroup.reverseState(Robot.pushup.pushupGroup.getState()));
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
