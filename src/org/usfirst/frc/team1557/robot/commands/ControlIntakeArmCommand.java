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

		if (OI.intakeArmToggle.get()) {
			if (Robot.intake.getArmSetpoint() <= 0) {
				Robot.intake.setAngleUp();
			} else if (Robot.intake.getArmSetpoint() >= 90) {
				Robot.intake.setAngleDown();
			} else if (Robot.intake.getArmSetpoint() > 45) {
				Robot.intake.setAngleUp();
			} else {
				Robot.intake.setAngleDown();
			}
		} else if (Math.abs(OI.altJoyOne.getRawAxis(RobotMap.ALT_JOY_AXIS_ONE_ID)) > RobotMap.JOYSTICK_DEADZONE) {
			Robot.intake.setArmSetpoint(OI.altJoyOne.getRawAxis(RobotMap.ALT_JOY_AXIS_ONE_ID));
		}
	}

	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.rotatePID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
