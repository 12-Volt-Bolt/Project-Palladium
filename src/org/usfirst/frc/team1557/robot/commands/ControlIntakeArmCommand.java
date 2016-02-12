package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Both manual and automatic controlling of the intake arms are stored in this
 * command.
 */
public class ControlIntakeArmCommand extends Command {

	public ControlIntakeArmCommand() {
		requires(Robot.intake);
	}

	protected void initialize() {
	}

	protected void execute() {

		if (OI.altJoyOne.getRawButton(RobotMap.ButtonId.ARM.getId())
				&& Math.abs(OI.altJoyOne.getRawAxis(RobotMap.ALT_JOY_AXIS_ONE_ID)) > RobotMap.JOYSTICK_DEADZONE) {
			Robot.intake.set(OI.altJoyOne.getRawAxis(RobotMap.ALT_JOY_AXIS_ONE_ID));
		} else {
			Robot.intake.set(0);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
