package org.usfirst.frc.team1557.robot.autonoms;

import org.usfirst.frc.team1557.robot.autonoms.commands.ControlArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlPistonArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.FireCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.GyroTurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An autonomous routine that uses timed movements <b>TESTED</b> Lowers the arm,
 * drives forwards, and then retreats slightly.
 */
public class TimedAuto extends CommandGroup {

	public TimedAuto() {
		// addSequential(new AssistedDriveCommand(0.5, 1, 0));
		// addSequential(new DriveCommand(0.5, 0.6));
		// addSequential(new AssistedDriveCommand(0.5, 1.2, 0));
		// addSequential(new GyroTurnCommand(27.6));
		// addSequential(new AssistedDriveCommand(1, .75, 27.6));
		// addSequential(new DriveCommand(0.5, .34));
		// addSequential(new FireCommand());
		// addSequential(new DriveCommand(-0.5, .34));
		// addSequential(new AssistedDriveCommand(-1, .75, 27.6));
		// addSequential(new GyroTurnCommand(180));
		// addSequential(new AssistedDriveCommand(0.5, 1.2, 180));

		// this is a temporary custom drive which may or may not work
		System.out.println("Scheduled timed auto to run");
		addSequential(new ControlArmCommand(0.5, 1.25));
		addSequential(new ControlPistonArmCommand(true, 2.25));
		addSequential(new DriveCommand(-0.61, 2.5));
		// addSequential(new DriveCommand(0.61, 0.25));
		// addSequential(new DriveCommand(0.5, 0.6));
		// addSequential(new DriveCommand(0.5, 1.2));
		// addSequential(new GyroTurnCommand(27.6));
		// addSequential(new DriveCommand(1, .75));
		// addSequential(new DriveCommand(0.5, .34));
		// addSequential(new FireCommand());
		// addSequential(new DriveCommand(-0.5, .34));
		// addSequential(new DriveCommand(-1, .75));
		// addSequential(new GyroTurnCommand(180));
		// addSequential(new DriveCommand(0.5, 1.2));
	}

	@Override
	public String toString() {
		return "Lowbar Autonomous";
	}
}
