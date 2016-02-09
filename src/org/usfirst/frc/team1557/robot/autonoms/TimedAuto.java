package org.usfirst.frc.team1557.robot.autonoms;

import org.usfirst.frc.team1557.robot.autonoms.commands.AssistedDriveCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.FireCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.GyroTurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An autonomous routine that uses timed movements and a gyro. <b>NOT TESTED</b>
 */
public class TimedAuto extends CommandGroup {

	public TimedAuto() {
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
