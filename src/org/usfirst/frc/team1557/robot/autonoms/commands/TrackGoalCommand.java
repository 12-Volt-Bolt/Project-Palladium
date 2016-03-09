package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.vision.TrackInterface;
import edu.wpi.first.wpilibj.command.Command;

public class TrackGoalCommand extends Command {
	TrackInterface track;

	@Override
	protected void initialize() {
		requires(Robot.drive);
		setTimeout(2.5);
		track = Robot.track;
		track.initialize();
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