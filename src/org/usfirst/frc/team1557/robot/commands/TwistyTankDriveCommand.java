package org.usfirst.frc.team1557.robot.commands;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.utils.PriorityGroup;
import org.usfirst.frc.team1557.robot.utils.PriorityObject;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TwistyTankDriveCommand extends Command {
	/**
	 * This value may never be exactly .5; however, it should probably be
	 * roughly .5.
	 */
	private double scaleSpeed = .501;
	private PriorityGroup driveAndTwist = new PriorityGroup(new PriorityObject(0) {

		@Override
		public boolean shouldRun() {

			return OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_ONE_ID) >= RobotMap.JOYSTICK_DEADZONE
					|| OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_ONE_ID) >= RobotMap.JOYSTICK_DEADZONE;
		}

		@Override
		public void run() {
			Robot.drive.tankDrive(OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_ONE_ID),
					OI.mainJoyTwo.getRawAxis(MAIN_JOY_AXIS_TWO_ID));
		}
	}, new PriorityObject(1) {

		@Override
		public boolean shouldRun() {

			return OI.mainJoyTwo.getRawAxis(MAIN_JOY_ROTATE_AXIS_ID) >= RobotMap.JOYSTICK_DEADZONE;
		}

		@Override
		public void run() {
			Robot.drive.tankDrive(OI.mainJoyTwo.getRawAxis(MAIN_JOY_ROTATE_AXIS_ID) * scaleSpeed,
					-OI.mainJoyTwo.getRawAxis(MAIN_JOY_ROTATE_AXIS_ID) * scaleSpeed);
		}
	});

	public TwistyTankDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.drive.tankDrive(0, 0);
		if (OI.mainJoyOne.getRawButton(2)) {
			Robot.drive.reverseMotors(true);
		} else {
			Robot.drive.reverseMotors(false);
		}
		driveAndTwist.run();

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
