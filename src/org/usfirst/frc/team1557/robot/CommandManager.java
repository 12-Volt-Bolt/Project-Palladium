package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.Joystick;

public class CommandManager {
	public static void manage() {
		intakeCommands();
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
		}
	}

	//
	private static double deadzone = 0.05;

	private static boolean isOutDeadzone(Joystick joy, int axis) {
		return joy.getRawAxis(axis) > deadzone || joy.getRawAxis(axis) < -deadzone;
	}
}
