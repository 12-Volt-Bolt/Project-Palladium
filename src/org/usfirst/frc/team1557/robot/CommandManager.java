package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.Joystick;

import static org.usfirst.frc.team1557.robot.RobotMap.*;
import static org.usfirst.frc.team1557.robot.Robot.*;

public class CommandManager {

	public static void manage() {
		//intakeCommands();
		//catapultCommands();
		//climbCommands();
	}

	private static void intakeCommands() {
		if (isOutDeadzone(OI.altJoyOne, 0) && !Robot.manualIntake.isRunning()) {
			if (Robot.intake.getCurrentCommand() != null)
				Robot.intake.getCurrentCommand().cancel();
			Robot.manualIntake.start();
		} else if (!isOutDeadzone(OI.altJoyOne, 0)) {
			if (Robot.intake.getCurrentCommand() != null)
				Robot.manualIntake.cancel();
		}
		if (OI.intakeArmToggle.get() && Robot.intake.getCurrentCommand() == null) {
			Robot.buttonIntakeUp.start();
		} else if (OI.intakeArmToggle.get() && Robot.intake.getCurrentCommand() == null) {
			Robot.buttonIntakeDown.start();

			if (OI.altJoyOne.getRawAxis(0) != 0 && !manualIntake.isRunning()) {
				if (intake.getCurrentCommand() != null)
					intake.getCurrentCommand().cancel();
				manualIntake.start();
			} else if (OI.altJoyOne.getRawAxis(0) == 0) {
				if (intake.getCurrentCommand() != null)
					manualIntake.cancel();

			}
			if (OI.openButton.get() && intake.getCurrentCommand() == null) {
				buttonIntakeUp.start();
			} else if (OI.closeButton.get() && intake.getCurrentCommand() == null) {
				buttonIntakeDown.start();
			}
		}
	}

	private static void catapultCommands() {
		if (OI.altJoyOne.getRawButton(CATAPULT_FIRE_BUTTON_ID) && !catapultFire.isRunning()) {
			catapultFire.start();
		}
	}

	private static void climbCommands() {
		if (OI.climbButton.get() && !climbCommand.isRunning()) {
			climbCommand.start();

		}
	}

	//
	private static double deadzone = 0.05;

	private static boolean isOutDeadzone(Joystick joy, int axis) {
		return joy.getRawAxis(axis) > deadzone || joy.getRawAxis(axis) < -deadzone;
	}
}
