package org.usfirst.frc.team1557.robot;

public class CommandManager {
	public static void manage() {
	intakeCommands();
	}
	
	
	private static void intakeCommands(){
		if (OI.altJoyOne.getRawAxis(0) != 0 && !Robot.manualIntake.isRunning()) {
			Robot.intake.getCurrentCommand().cancel();
			Robot.manualIntake.start();
		} else if (OI.altJoyOne.getRawAxis(0) == 0) {
			Robot.manualIntake.cancel();
		}
		if (OI.openButton.get() && Robot.intake.getCurrentCommand() == null) {
			Robot.buttonIntakeUp.start();
		} else if (OI.closeButton.get() && Robot.intake.getCurrentCommand() == null) {
			Robot.buttonIntakeDown.start();
		}
	}
}
