package org.usfirst.frc.team1557.robot.commands;

import static org.usfirst.frc.team1557.robot.RobotMap.MAIN_JOY_AXIS_ONE_ID;
import static org.usfirst.frc.team1557.robot.RobotMap.MAIN_JOY_AXIS_TWO_ID;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TankDriveCommand extends Command {
	boolean rNewPress = false;
	boolean sNewPress = false;
	boolean doBatter = false;

	public TankDriveCommand() {

		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putBoolean("Button", OI.mainJoyOne.getRawButton(2));
		if (OI.mainJoyOne.getRawButton(2) && !rNewPress) {
			Robot.drive.reverseMotors();
			rNewPress = true;
		} else if (!OI.mainJoyOne.getRawButton(2) && rNewPress) {
			rNewPress = false;
		}

		if (OI.mainJoyTwo.getRawButton(RobotMap.ButtonId.STAY_ON_BATTER.getId()) && !sNewPress) {
			doBatter = !doBatter;
			sNewPress = true;
		} else if (!OI.mainJoyTwo.getRawButton(RobotMap.ButtonId.STAY_ON_BATTER.getId()) && sNewPress) {
			sNewPress = false;

			Robot.drive.tankDrive(OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_ONE_ID),
					OI.mainJoyTwo.getRawAxis(MAIN_JOY_AXIS_TWO_ID));
		} else {
		}
		if (doBatter) {
			Robot.drive.tankDrive(0.35, 0.35);
		} else {
			Robot.drive.tankDrive(OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_ONE_ID),
					OI.mainJoyTwo.getRawAxis(MAIN_JOY_AXIS_TWO_ID));

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
