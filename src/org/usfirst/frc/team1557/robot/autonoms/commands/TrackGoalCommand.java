package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.vision.TrackInterface;
import edu.wpi.first.wpilibj.command.Command;

public class TrackGoalCommand extends Command {
	TrackInterface track;

	public TrackGoalCommand(double timeout) {
		requires(Robot.drive);
		setTimeout(timeout);
	}

	@Override
	protected void initialize() {
		track = Robot.track;
		track.initialize();
		track.stopRunning();
	}

	@Override
	protected void execute() {
		track.run();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		track.stopRunning();
		Robot.drive.tankDrive(0, 0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}