package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWheelCommand extends Command {

	public IntakeWheelCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.buttonIntakeWheel);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	
	protected void execute() {
		if (OI.altJoyOne.getRawButton(RobotMap.INTAKE_WHEEL_BUTTON_ID)) {
			Robot.buttonIntakeWheel.runIntakeWheels();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
