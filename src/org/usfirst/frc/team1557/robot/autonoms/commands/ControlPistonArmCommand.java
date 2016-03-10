package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ControlPistonArmCommand extends Command {
	boolean down = false;

	public ControlPistonArmCommand(boolean down, double time) {
		// TODO Auto-generated constructor stub
		this.setTimeout(time);
		requires(Robot.liftClimb);
		this.down = down;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if (down) {
			Robot.liftClimb.down();
		} else {
			Robot.liftClimb.up();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.liftClimb.stopMotors();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
