package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurnCommand extends Command {
	double angle;

	public GyroTurnCommand(double angle) {
		this.angle = angle;
		this.setTimeout(5);
	}

	protected void initialize() {
		requires(Robot.drive);
		Robot.drive.assistedTurn(angle);

	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return Robot.drive.isOnTarget() || isTimedOut();
	}

	protected void end() {
		Robot.drive.disableGyroPID();
	}

	protected void interrupted() {
	}
}
