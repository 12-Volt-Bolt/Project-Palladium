package org.usfirst.frc.team1557.robot;

import static org.usfirst.frc.team1557.robot.RobotMap.*;
import static org.usfirst.frc.team1557.robot.Robot.*;

public class CommandManager {
	public static void manage() {
		intakeCommands();
		catapultCommands();
	}

	private static void intakeCommands() {
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

	private static void catapultCommands() {
		if (OI.altJoyOne.getRawButton(CATAPULT_FIRE_BUTTON_ID) && !catapultFire.isRunning()) {
			catapultFire.start();
			System.out.println("Michael, You printerhead!");
		}
	}
}
