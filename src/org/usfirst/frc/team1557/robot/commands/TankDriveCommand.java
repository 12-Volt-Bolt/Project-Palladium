package org.usfirst.frc.team1557.robot.commands;

import static org.usfirst.frc.team1557.robot.RobotMap.MAIN_JOY_AXIS_ONE_ID;
import static org.usfirst.frc.team1557.robot.RobotMap.MAIN_JOY_AXIS_TWO_ID;
import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveCommand extends Command {
	
	TrackCommand vision;

	public TankDriveCommand() {
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		vision = new TrackCommand();
		vision.initialize();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!OI.mainJoyOne.getRawButton(3)) {
			vision.notRunning();
			if (OI.mainJoyOne.getRawButton(2)) {
				Robot.drive.reverseMotors(true);
			} else {
				Robot.drive.reverseMotors(false);
			}
			Robot.drive.tankDrive(OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_ONE_ID),
					OI.mainJoyTwo.getRawAxis(MAIN_JOY_AXIS_TWO_ID));
		} else {
			vision.run();		
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
