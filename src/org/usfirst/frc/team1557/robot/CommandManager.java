package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.Joystick;

import static org.usfirst.frc.team1557.robot.RobotMap.*;
import static org.usfirst.frc.team1557.robot.Robot.*;

/**
 * Dedicated class to handle custom command starts and stops.
 * 
 *
 */
public class CommandManager {
	/**
	 * Call in teleopPeriodic to run all custom cammond functions.
	 */
	public static void manage() {
		// intakeCommands();
		// climbCommands();
	}

	private static void intakeCommands() {
		if (isOutDeadzone(OI.altJoyOne, 0, 0.5) && !Robot.manualIntake.isRunning()) {
			if (Robot.intake.getCurrentCommand() != null)
				Robot.intake.getCurrentCommand().cancel();
			Robot.manualIntake.start();
		} else if (!isOutDeadzone(OI.altJoyOne, 0, 0.5)) {
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

	private static void climbCommands() {
		if (OI.climbButton.get() && !climbCommand.isRunning()) {
			climbCommand.start();

		}
	}

	/**
	 * 
	 * @param joy
	 *            The joystick to check
	 * @param axis
	 *            The raw axis ID to check
	 * @param deadzone
	 *            The positive double to represent the joystick deadzone.
	 * @return Returns if the given axis on the given joystick is outside of the
	 *         given +- deadzone
	 */
	private static boolean isOutDeadzone(Joystick joy, int axis, double deadzone) {

		return joy.getRawAxis(axis) > deadzone || joy.getRawAxis(axis) < -deadzone;
	}
}
