package org.usfirst.frc.team1557.robot.autonoms;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MainAuto extends CommandGroup {

	public MainAuto() {
		addSequential(new AssistedDriveCommand(0.5, 1, 0));
		addSequential(new DriveCommand(0.5, 0.6));
		addSequential(new AssistedDriveCommand(0.5, 1.2, 0));
		addSequential(new GyroTurnCommand(27.6));
		addSequential(new AssistedDriveCommand(1, .75, 27.6));
		addSequential(new DriveCommand(0.5, .34));
		addSequential(new FireCommand());
		addSequential(new DriveCommand(-0.5, .34));
		addSequential(new AssistedDriveCommand(-1, .75, 27.6));
		addSequential(new GyroTurnCommand(180));
		addSequential(new AssistedDriveCommand(0.5, 1.2, 180));

	}
}
