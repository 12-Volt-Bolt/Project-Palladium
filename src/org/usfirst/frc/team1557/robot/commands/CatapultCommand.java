package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CatapultCommand extends Command {
	int count = 0;
	int countToFinish = 50;

	public CatapultCommand() {
		requires(Robot.catapult);
	}

	@Override
	protected void initialize() {
		Robot.catapult.set(true);
	}

	@Override
	protected void execute() {
		count++;
	}

	@Override
	protected boolean isFinished() {

		return count >= countToFinish;
	}

	@Override
	protected void end() {
		Robot.catapult.set(false);

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
