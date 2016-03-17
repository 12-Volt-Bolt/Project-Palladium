package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap.ButtonId;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleClimbPistonCommand extends Command {

	public ToggleClimbPistonCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.climbPiston);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if (System.currentTimeMillis() - Robot.START_TIME >= (60 + 60) *
		// 1_000) {

		if (OI.altJoyOne.getRawButton(ButtonId.EXTEND_CLIMB_PISTON.getId())) {
			Robot.climbPiston.prodigious.set(Value.kForward);
			System.out.println("Extends the pistons");
		} else if (OI.altJoyOne.getRawButton(ButtonId.RETRACT_CLIMB_PISTON.getId())) {
			Robot.climbPiston.prodigious.set(Value.kReverse);
			System.out.println("Retracted the pistons!");
		}
		// }

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
